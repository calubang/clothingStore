package kitri.foodCourt.management.member;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminTable extends JPanel {

	JScrollPane scrollPane = new JScrollPane();
	
	private JTable adminTable = new JTable(60, 5);
	
	String[] column = { "���̵�", "�̸�", "��й�ȣ", "��ȣ1","��ȣ2","��ȣ3", "�����ڵ�", "�Ի���", "�����ȣ", "�ּ�", "�̸���", "�̸��ϵ�����" };
	/**
	 * Create the panel.
	 */
	public AdminTable() {
		
//		for (int i = 0; i < column.length; i++) {
//			adminTable.addColumn(column[i]);
//		}
		
		setSize(new Dimension(780, 640));
		setLayout(null);
		
		scrollPane.setBounds(0, 0, 780, 640);
		scrollPane.setViewportView(adminTable);
		add(scrollPane);
	}
}
