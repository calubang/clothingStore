package kitri.foodCourt.user.controller;

import java.awt.Component;
import java.awt.event.*;

import javax.swing.JPasswordField;

import kitri.foodCourt.user.service.UserInfoService;
import kitri.foodCourt.user.view.UserInfo;

public class UserInfoController implements KeyListener, ActionListener {

	public UserInfo userInfo;
	public UserInfoService service;
	
	public UserInfoController(UserInfo userInfo) {
		this.userInfo = userInfo;
		
		service = new UserInfoService(this);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Component component = e.getComponent();
		//��й�ȣ �Է�ĭ�� �ϳ��� ������ üũ
		if(component instanceof JPasswordField) {
			service.isCorrectPassword();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == userInfo.btnModify) {
			//����
			service.modify();
		} else if(obj == userInfo.btnCancel) {
			//���
			service.cancel();
		}
	}


}
