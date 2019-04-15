package kitri.foodCourt.management.member;

import java.awt.Dimension;
import java.sql.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kitri.foodCourt.management.regit.DBConnection;

public class MemberTable extends JPanel {

	JScrollPane scrollPane = new JScrollPane();
	DefaultTableModel dtm = new DefaultTableModel();
	private JTable memberTable = new JTable(dtm);
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	String[] column = { "����ID", "�̸�", "�ڵ�����ȣ", "��밡������Ʈ", "������", "Ż����", "Ȱ��ȭ����" };
	/**
	 * Create the panel.
	 */
	public MemberTable() {
		Object[] rowData = new Object[11];
		try {

			String quary = "SELECT user_id,password,phone_first,phone_middle,phone_last,user_point,password_quiz,password_answer,join_date,secession_date,enable FROM FOOK_USER";
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
			
			
			
			while (rs.next()) {

				rowData[0] = rs.getString("user_id");
				rowData[1] = rs.getString("password");
				rowData[2] = rs.getString("phone_first");
				rowData[3] = rs.getString("phone_middle");
				rowData[4] = rs.getString("phone_last");
				rowData[5] = rs.getInt("user_point");
				rowData[6] = rs.getString("password_quiz");
				rowData[7] = rs.getString("password_answer");
				rowData[8] = rs.getDate("join_date");
				rowData[9] = rs.getDate("secession_date");
				rowData[10] = rs.getString("enable");
				
				
				dtm.addRow(rowData);
			
			}
			
		} catch (SQLException sqle) {
			System.out.println("SELECT������ ���� �߻�");
			sqle.printStackTrace();

		} finally {
			// DB ������ �����Ѵ�.
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			
		}
		
		
		for (int i = 0; i < column.length; i++) {
			dtm.addColumn(column[i]);
		}
		
		setSize(new Dimension(780, 640));
		setLayout(null);
		
		scrollPane.setBounds(0, 0, 780, 640);
		scrollPane.setViewportView(memberTable);
		add(scrollPane);
	}

}
