package kitri.foodCourt.management.member;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kitri.foodCourt.dto.UserDto;
import kitri.foodCourt.management.regit.AdminUserDao;
import kitri.foodCourt.user.swing.SwingFactory;



public class MemberTable extends JPanel {

	JScrollPane scrollPane = new JScrollPane();
	DefaultTableModel tableModel = new DefaultTableModel();
	private JTable memberTable = new JTable(tableModel);
	private AdminUserDao dao;
	

	String[] column = { "����ID", "�н�����", "�̸�", "�ڵ�����ȣ", "��밡������Ʈ", "��й�ȣ ã�������", "��й�ȣ ã���亯",  "������", "Ż����", "Ȱ��ȭ����" };

	
	public MemberTable() {

		
<<<<<<< HEAD
		dao = new AdminUserDao();
		initView();
		
		initData();
		
	}
	
	public void initView() {
		for (int i = 0; i < column.length; i++) {
			tableModel.addColumn(column[i]);
		}
=======
		Object[] rowData = new Object[9];
		try {
>>>>>>> branch 'master' of https://github.com/calubang/foodCourt.git

<<<<<<< HEAD
		
	}
	
	public void initData() {
		Vector<UserDto> vector = dao.allSelect();
		
		if(vector == null) {
			SwingFactory.getOptionPane("errorMessage", memberTable, "DB����", "DB���� ����");
			return;
		} else {
			insertTable(vector);
		}
	}
	
	public void insertTable(Vector<UserDto> vector) {
		
		int size = vector.size();
		for(int i=0 ; i<size ; i++) {
			Vector<Object> temp = new Vector<Object>();
			//{ "����ID", "�н�����", "�̸�", "�ڵ�����ȣ", "��밡������Ʈ", "��й�ȣ ã�������", "��й�ȣ ã���亯",  "������", "Ż����", "Ȱ��ȭ����" };
			UserDto dto = vector.get(i);
			temp.add(dto.getUserId());
			temp.add(dto.getPassword());
			temp.add(dto.getName());
			temp.add(dto.getPhoneNumberFirst() + "-" + dto.getPhoneNumberMiddle() + "-" + dto.getPhoneNumberlast());
			temp.add(dto.getUserPoint());
			temp.add(dto.getPasswordQuiz());
			temp.add(dto.getPasswordAnswer());
			temp.add(dto.getJoinDate());
			temp.add(dto.getSecessionDate());
			temp.add(dto.getEnable());
=======
			String quary = "SELECT user_id,password,phone_first||phone_middle||phone_last as pn,user_point,password_quiz,password_answer,join_date,secession_date,enable FROM FOOK_USER";
			conn = connectionMaker.makeConnection();
			pstm = conn.prepareStatement(quary);
			rs = pstm.executeQuery();
>>>>>>> branch 'master' of https://github.com/calubang/foodCourt.git
			
<<<<<<< HEAD
			tableModel.addRow(temp);
=======
			
			
			while (rs.next()) {

				rowData[0] = rs.getString("user_id");
				rowData[1] = rs.getString("password");
				rowData[2] = rs.getString("pn");
//				rowData[2] = rs.getString("phone_first");
//				rowData[3] = rs.getString("phone_middle");
//				rowData[4] = rs.getString("phone_last");
				rowData[3] = rs.getInt("user_point");
				rowData[4] = rs.getString("password_quiz");
				rowData[5] = rs.getString("password_answer");
				rowData[6] = rs.getDate("join_date");
				rowData[7] = rs.getDate("secession_date");
				rowData[8] = rs.getString("enable");
				
				
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
>>>>>>> branch 'master' of https://github.com/calubang/foodCourt.git
			
		}
	}

}
