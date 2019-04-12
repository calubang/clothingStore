package kitri.foodCourt.management.member;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MemberTable extends JPanel {

	JScrollPane scrollPane = new JScrollPane();
	DefaultTableModel dtm = new DefaultTableModel();
	private JTable memberTable = new JTable(dtm);
	
	String[] column = { "����ID", "�̸�", "�ڵ�����ȣ", "��밡������Ʈ", "������", "Ż����", "Ȱ��ȭ����" };
	/**
	 * Create the panel.
	 */
	public MemberTable() {
		
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
