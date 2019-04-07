package kitri.foodCourt.management.regit;

import javax.swing.JFrame;

public class AdminRegisterService {

	AdminRegisterControl arc;
	AdminMemberInfo ami;
	
	JFrame jfA = new JFrame("�����ڵ��");
	JFrame jfM = new JFrame("ȸ�����");
	JFrame jfMo = new JFrame("����");
	JFrame jfD = new JFrame("����");
	
	
	AdminRegister ar;
	MemberRegister mr;
	ModifyRegit mR;
	RemoveMember rm;
	
	
	public AdminRegisterService(AdminRegisterControl arc) {
		this.arc = arc;
		ami = this.arc.ami;
		
		ar = new AdminRegister();
		mr = new MemberRegister();
		mR = new ModifyRegit();
		rm = new RemoveMember();
		
	}

	public void showadminRegister() {
		jfA.getContentPane().add(ar);
		jfA.setSize(600,500);
		jfA.setVisible(true);
	}

	public void showmemberRegister() {
		jfM.getContentPane().add(mr);
		jfM.setSize(600,500);
		jfM.setVisible(true);
	}

	public void showmodify() {
		jfM.getContentPane().add(mR);
		jfM.setSize(600,500);
		jfM.setVisible(true);
	}

	public void showdelete() {
		jfD.getContentPane().add(rm);
		jfD.setSize(450,220);
		jfD.setVisible(true);
	}
	
	
	
}
