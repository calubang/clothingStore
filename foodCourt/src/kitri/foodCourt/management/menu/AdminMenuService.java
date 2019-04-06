package kitri.foodCourt.management.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class AdminMenuService {

	AdminMenuControl amc;
	AdminMenuManagement amm;
	
	AdminRegisterMenu arp;
	AdminModifyMenu amp;
	
	JFrame jfR = new JFrame("�޴����");
	JFrame jfM = new JFrame("�޴�����");
	
	String[] option = {"��", "�ƴϿ�"};
	
	
	public AdminMenuService(AdminMenuControl amc) {
		this.amc = amc;
		amm = this.amc.amm;
		
		arp = new AdminRegisterMenu();
		amp = new AdminModifyMenu();
	}
	
	
	public void showRegisterWindow() {
		jfM.setVisible(false);
		
		jfR.getContentPane().add(arp);
		jfR.setSize(750, 650);
		jfR.setVisible(true);
	}
	
	public void showModifyWindow() {
		jfR.setVisible(false);
		
		jfM.getContentPane().add(amp);
		jfM.setSize(750, 650);
		jfM.setVisible(true);
	}
	
	public void showDeleteWindow() {
		JOptionPane.showOptionDialog(amm.deleteBtn, "���� �����Ͻðڽ��ϱ�?\n(�����ϸ� �ٽ� �ǵ��� �� �����ϴ�.)", "���� Ȯ��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	}
}
