package kitri.foodCourt.management.member;

import java.awt.Dimension;
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
		dao = new AdminUserDao();
		initView();
		initData();
		
	}
	
	public void initView() {
		setSize(new Dimension(780, 640));
		setLayout(null);

		scrollPane.setBounds(0, 0, 780, 640);
		scrollPane.setViewportView(memberTable);
		add(scrollPane);
		
		for (int i = 0; i < column.length; i++) {
			tableModel.addColumn(column[i]);
		}
		
		memberTable.setEnabled(false);
		
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

		
			tableModel.addRow(temp);
		}
	}

}
