package kitri.foodCourt.management.regit;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import kitri.foodCourt.dto.UserDto;
import kitri.foodCourt.management.regit.AdminUserDao;
import kitri.foodCourt.user.swing.SwingFactory;



public class MemberTable extends JPanel {

	JScrollPane scrollPane = new JScrollPane();
	DefaultTableModel tableModel = new DefaultTableModel();
	public JTable table = new JTable(tableModel){
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		};
	};
	public AdminUserDao dao;
	
	String[] column = { "유저ID", "패스워드", "이름", "핸드폰번호", "사용가능포인트", "비밀번호 찾기용질문", "비밀번호 찾기용답변",  "가입일", "탈퇴일", "활성화여부" };
	
	public MemberTable() {
		dao = new AdminUserDao();
		initView();
		initData();
		
	}
	
	public void initView() {
		setSize(new Dimension(780, 640));
		setLayout(null);

		scrollPane.setBounds(0, 0, 780, 640);
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		for (int i = 0; i < column.length; i++) {
			tableModel.addColumn(column[i]);
		}
		
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		//table.setBounds(0, 356, 791, 366);
		table.setShowGrid(true);
		table.setShowVerticalLines(true);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
	}
	
	public void initData() {
		int size = tableModel.getRowCount();
		for(int i =0 ; i<size ; i++) {
			tableModel.removeRow(size-i-1);
		}
		
		Vector<UserDto> vector = dao.allSelect();
		
		if(vector == null) {
			SwingFactory.getOptionPane("errorMessage", table, "DB오류", "DB연결 오류");
			return;
		} else {
			insertTable(vector);
		}
	}
	
	public void insertTable(Vector<UserDto> vector) {
		
		int size = vector.size();
		for(int i=0 ; i<size ; i++) {
			Vector<Object> temp = new Vector<Object>();
			//{ "유저ID", "패스워드", "이름", "핸드폰번호", "사용가능포인트", "비밀번호 찾기용질문", "비밀번호 찾기용답변",  "가입일", "탈퇴일", "활성화여부" };
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

