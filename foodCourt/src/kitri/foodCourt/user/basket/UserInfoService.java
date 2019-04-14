package kitri.foodCourt.user.basket;

import java.awt.Color;

import kitri.foodCourt.dto.UserDto;
import kitri.foodCourt.user.User;
import kitri.foodCourt.user.main.FoodMain;
import kitri.foodCourt.user.swing.FOptionPane;

public class UserInfoService {

	public UserInfoController controller;
	public UserInfo userInfo;
	public FoodMain foodMain;
	public UserInfoDao dao;
	
	public UserInfoService(UserInfoController controller) {
		this.controller = controller;
		this.userInfo = controller.userInfo;
		this.foodMain = userInfo.foodMain;
		dao = new UserInfoDao(this);
	}

	public void isCorrectPassword() {
		
		String newPassword = new String(userInfo.pfNewPassword.getPassword());
		String rePassword = new String(userInfo.pfRePassword.getPassword());
		
		//��� ��ĭ�̸� ��й�ȣ ���� ���ϴ°ɷ� ����
		if(newPassword.isEmpty() && rePassword.isEmpty()) {
			userInfo.setModifiable(true);
			return;
		}
		
		if(newPassword.length() < 6) {
			userInfo.setModifiable(false);
			userInfo.lbPasswordInfo.setText("�н����尡 �ʹ� ª���ϴ�.");
			return;
		}
			
		if(!newPassword.equals(rePassword)) {
			userInfo.lbPasswordInfo.setForeground(Color.RED);
			userInfo.lbPasswordInfo.setText("�н����尡 ��ġ���� �ʽ��ϴ�.");
			userInfo.setModifiable(false);
		}else {
			userInfo.lbPasswordInfo.setForeground(Color.BLACK);
			userInfo.lbPasswordInfo.setText("�н����尡 ��ġ�մϴ�.");
			userInfo.setModifiable(true);
		}
	}

	//ȸ������
	public void modify() {
		User user = foodMain.user;
		String newPassword = new String(userInfo.pfNewPassword.getPassword());
		
		//��ȿ�� �˻�
		if(!userInfo.getModifiable()) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��й�ȣ ����", "��й�ȣ�� �ٽ� Ȯ�����ּ���.");
			return;
		}
		if(allSameData()) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "ȸ������ ����", "����� ȸ������������ �����ϴ�.");
			return;
		}
		
		//��ҹ��� or ����
		int size = newPassword.length();
		for(int i = 0 ; i<size ; i++) {
			char key = newPassword.charAt(i);
			if(  !((key >= '0' && key <= '9') || ( key >= 'A' && key <= 'Z' ) || (key >= 'a' && key <= 'z')) ) {
				SwingFactory.getOptionPane("errorMessage", userInfo, "��й�ȣ ����", "��й�ȣ�� �����ҹ��� or ���ڸ� ����� �����մϴ�.");
				return;
			}
		}

		//��ȭ��ȣ 4�ڸ� üũ
		if(userInfo.tfPhoneMiddle.getText().length() > 4 || userInfo.tfPhoneLast.getText().length() > 4) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��ȭ��ȣ ����", "��ȭ��ȣ�� 4�ڸ����� �����մϴ�.");
			return;
		}
		
		//ȸ������ ����
		//�����ݿ��� �������� ����
		UserDto userDto = user.getDto();
		userDto.setName(userInfo.tfName.getText());
		if( !new String(userInfo.pfRePassword.getPassword()).isEmpty()) {
			userDto.setPassword(new String(userInfo.pfRePassword.getPassword()));
		}
		userDto.setPhoneNumberFirst(userInfo.cbPhoneFirst.getSelectedItem().toString());
		userDto.setPhoneNumberMiddle(userInfo.tfPhoneMiddle.getText());
		userDto.setPhoneNumberlast(userInfo.tfPhoneLast.getText());
		
		if(dao.modify(userDto) != 1) {
			return;
		}
		
		//�ڹ�user �� ����
		user.setDto(userDto);
		
		//ȭ����ȯ
		int result = SwingFactory.getOptionPane("message", userInfo, "ȸ������ ����", "ȸ�������� �����Ǿ����ϴ�.");
		if(result == 0 ) {
			foodMain.labName.setText(user.getName() +"��");
			foodMain.foodMainController.foodMainService.userInfo();
		}
	}
	
	public boolean allSameData() {
		User user = foodMain.user;
		//�̸�
		if( !userInfo.tfName.getText().equals(user.getName())) {
			return false;
		}
		
		//��ȭ��ȣ
		if( !userInfo.cbPhoneFirst.getSelectedItem().equals(user.getPhoneNumberFirst())) {
			return false;
		}
		if( !userInfo.tfPhoneMiddle.getText().equals(user.getPhoneNumberMiddle()) ) {
			return false;
		}
		if( !userInfo.tfPhoneLast.getText().equals(user.getPhoneNumberlast())) {
			return false;
		}
		
		//��й�ȣ
		String newPassword = new String(userInfo.pfNewPassword.getPassword());
		if(!newPassword.isEmpty() && !newPassword.equals(user.getPassword()) ) {
			return false;
		}

		return true;
	}

	public void cancel() {
		foodMain.foodMainController.foodMainService.clickMain();
	}
}
