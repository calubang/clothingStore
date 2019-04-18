package kitri.foodCourt.user.service;

import java.awt.Color;

import kitri.foodCourt.dto.UserDto;
import kitri.foodCourt.user.User;
import kitri.foodCourt.user.controller.UserInfoController;
import kitri.foodCourt.user.dao.UserInfoDao;
import kitri.foodCourt.user.swing.FOptionPane;
import kitri.foodCourt.user.swing.SwingFactory;
import kitri.foodCourt.user.view.FoodMain;
import kitri.foodCourt.user.view.UserInfo;

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
			userInfo.lbPasswordInfo.setText("");
			return;
		}
		
		if(newPassword.length() < 6) {
			userInfo.setModifiable(false);
			userInfo.lbPasswordInfo.setText("�н����尡 �ʹ� ª���ϴ�.");
			return;
		}
		if(newPassword.length() > 20) {
			userInfo.setModifiable(false);
			userInfo.lbPasswordInfo.setText("�н����尡 �ʹ� ��ϴ�.");
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
		//�̸�
		if(userInfo.tfName.getText().trim().isEmpty())  {
			SwingFactory.getOptionPane("errorMessage", userInfo, "�Է� ����", "�̸��� �Է����ּ���.");
			return;
		}
		if(userInfo.tfName.getText().length() > 20)  {
			SwingFactory.getOptionPane("errorMessage", userInfo, "�Է� ����", "�̸��� �ʹ� ��ϴ�.");
			return;
		}
		
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
		if(userInfo.tfPhoneMiddle.getText().trim().isEmpty()) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��ȭ��ȣ ����", "��ȭ��ȣ�� Ȯ�����ּ���.");
			return;
		}
		if(userInfo.tfPhoneLast.getText().trim().isEmpty()) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��ȭ��ȣ ����", "��ȭ��ȣ�� Ȯ�����ּ���.");
			return;
		}
		
		//��й�ȣ ã��
		if(userInfo.tfPasswordQuiz.getText().trim().isEmpty()) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��й�ȣ ã�� ����", "��й�ȣ ã���� ������ �Է����ּ���.");
			return;
		}
		if(userInfo.tfPasswordQuiz.getText().length() > 40) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��й�ȣ ã�� ����", "��й�ȣ ã���� ������ �ʹ� ��ϴ�.");
			return;
		}
		if(userInfo.tfPasswordAnswer.getText().trim().isEmpty()) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��й�ȣ ã�� ����", "��й�ȣ ã���� �亯�� �Է����ּ���.");
			return;
		}
		if(userInfo.tfPasswordAnswer.getText().length() > 40) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "��й�ȣ ã�� ����", "��й�ȣ ã���� �亯�� �ʹ� ��ϴ�.");
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
		userDto.setPasswordQuiz(userInfo.tfPasswordQuiz.getText());
		userDto.setPasswordAnswer(userInfo.tfPasswordAnswer.getText());
		
		if(dao.modify(userDto) != 1) {
			SwingFactory.getOptionPane("errorMessage", userInfo, "�ý��� ����", "�ý��� �����Դϴ�. �����ڿ��� �����ϼ���.");
			return;
		}
		
		//�ڹ�user �� ����
		user.setDto(userDto);
		
		//ȭ����ȯ
		int result = SwingFactory.getOptionPane("message", userInfo, "ȸ������ ����", "ȸ�������� �����Ǿ����ϴ�.");
		if(result == 0 ) {
			foodMain.labName.setText(user.getName() +"��");
			foodMain.foodMainController.foodMainService.userInfo();
			foodMain.card.show(foodMain.panChangePanel, "foodMainView");
			foodMain.currentPanelName = "foodMainView";
		}
	}
	
	public boolean allSameData() {
		User user = User.getInstance();
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
		
		//��й�ȣ ã��
		if( !userInfo.tfPasswordQuiz.getText().equals(user.getPasswordQuiz())) {
			return false;
		}
		if( !userInfo.tfPasswordAnswer.getText().equals(user.getPasswordAnswer()) ) {
			return false;
		}

		return true;
	}

	public void cancel() {
		foodMain.foodMainController.foodMainService.clickMain();
	}
	
	//ȸ��Ż��
	public void secession() {
		int result = SwingFactory.getOptionPane("warning", userInfo, "Ż�� Ȯ��", "Ż���ϸ� ��� ����Ʈ�� �Ҹ�˴ϴ�.\n���� Ż�� �Ͻðڽ��ϱ�?");
		if(result == 0) {
			//Ż��
			User user = User.getInstance();
			if(dao.secession(user.getUserId()) == 1) {
				//����Ż��
				user.logout();
				foodMain.card.show(foodMain.panChangePanel, "foodMainView");
				foodMain.currentPanelName = "foodMainView";
				foodMain.setVisible(false);
				foodMain.loginMain.card.show(foodMain.loginMain.panMain, "panLogin");
				foodMain.loginMain.setVisible(true);
			}
		} else {
			SwingFactory.getOptionPane("errorMessage", userInfo, "�ý��� ����", "�ý��� �����Դϴ�. �����ڿ��� �����ϼ���.");
			return;
		}
	}
}
