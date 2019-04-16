package kitri.foodCourt.management.login;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kitri.foodCourt.db.ConnectionMaker;
import kitri.foodCourt.db.DbFactory;
import kitri.foodCourt.management.main.AdminMainFrame;


public class LoginService {

	LoginControl lc;
	LoginMain lm;
	Login l;
	
	private ConnectionMaker connectionMaker;
	
    // For OracleDB
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
	
	public LoginService(LoginControl lc) {
		this.lc = lc;
		lm = lc.lm;
		l = lm.panLogin;
		
		connectionMaker = DbFactory.connectionMaker("oracle");
	}


	public void loginAdmin() {
		String id = l.idtextField.getText().trim();
		if (id.isEmpty()) {
			warningMessage(l.idtextField, "���̵� �Է��ϼ���.", "������ �α��� ����");
		}
		
		String pw = String.valueOf(l.pwtextfd.getPassword());
		if (pw.isEmpty()) {
			warningMessage(l.pwtextfd, "�н����带 �Է��ϼ���.", "������ �α��� ����");
		}
		
		String adminID = null;
		try {
			c = connectionMaker.makeConnection();
			
			ps = c.prepareStatement("select manager_id "
								  + "from fook_manager "
								  + "where name = (?) and password = (?)");
			
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				adminID = rs.getString("manager_id");
			}
		} catch (SQLException e) {
			warningMessage(l.loginbtn, e.getMessage(), "������ �α��� ����");
		} finally {
			closeOracleConnection(l.loginbtn, c, ps, rs);
			
			if (adminID == null) {
				warningMessage(l.loginbtn, "���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�.", "������ �α��� ����");
				return;
			}
			
			new AdminMainFrame(adminID).setVisible(true);
			lm.dispose();
		}
	}
	
	private void closeOracleConnection(Component comp, Connection c, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				warningMessage(comp, e.getMessage(), "SQL ���� ����");
			} finally {
				rs = null;
			}
		}
		
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				warningMessage(comp, e.getMessage(), "SQL ���� ����");
			} finally {
				ps = null;
			}
		}
		
		if(c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				warningMessage(comp, e.getMessage(), "SQL ���� ����");
			} finally {
				c = null;
			}
		}
	}
	
	private void warningMessage(Component component, Object msg, String title) {
		JOptionPane.showMessageDialog(component, msg, title, JOptionPane.WARNING_MESSAGE);
	}
}
