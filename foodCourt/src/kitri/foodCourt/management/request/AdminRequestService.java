package kitri.foodCourt.management.request;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class AdminRequestService {

	AdminRequestControl arc;
	AdminRequest ar;
	
	AdminViewOrder avo;
	
	JFrame jfV = new JFrame("�ֹ� ��� Ȯ��");
	
	String[] option = {"��", "�ƴϿ�"};
	
	
	public AdminRequestService(AdminRequestControl arc) {
		this.arc = arc;
		ar = this.arc.ar;
		
		avo = new AdminViewOrder();
	}
	
	
	public void showOrderWindow() {
		jfV.getContentPane().add(avo);
		jfV.setSize(395, 580);
		jfV.setVisible(true);
	}
	
	public void processOrderWindow() {
		JOptionPane.showOptionDialog(ar.processOrderBtn, "ó���Ͻðڽ��ϱ�?", "ó�� Ȯ��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	}
}
