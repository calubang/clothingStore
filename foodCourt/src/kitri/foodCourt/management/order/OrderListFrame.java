package kitri.foodCourt.management.order;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class OrderListFrame extends JFrame {

	JPanel contentPane;
	JScrollPane scrollPane = new JScrollPane();
	JPanel panel = new JPanel();
	GridBagLayout gbl_panel = new GridBagLayout();
	JButton btnOrderview = new JButton("\uC8FC\uBB38 \uD655\uC778");
	JButton btnComplete = new JButton("\uC644\uB8CC");
	JButton btnRemove = new JButton("\uC81C\uAC70");

//	List<OrderList> list = new Vector<OrderList>();	
//	Map<String, OrderList> map = new HashMap<String, OrderList>();
	Map<String, OrderList> tmap = new TreeMap<String, OrderList>(Collections.reverseOrder()); // �������� ������ �ϱ����� �� ���п�
																								// list�Ƚᵵ��
	Iterator<String> iteratorKey; // Ű�� �������� ����(�⺻)

	OrderController orderController;
	String selectedRequestNumber = ""; // ���� ���� ��ư ��������

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void addOrder() { // ���⼭ ����Ʈ�� �޾ƿ´�
		iteratorKey = tmap.keySet().iterator();
		int gridx = 0;
		int gridy = 0;
		while (iteratorKey.hasNext()) {
			String key = iteratorKey.next();
			System.out.println(key + "," + tmap.get(key));
			OrderListButton orderListButton = new OrderListButton(tmap.get(key).getRequestNumber());
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 9, 17);
			gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton.gridx = gridx;
			gbc_btnNewButton.gridy = gridy;
			panel.add(orderListButton, gbc_btnNewButton);

			orderListButton.setName(tmap.get(key).getRequestNumber());
			orderListButton.addActionListener(orderController);
			// ���⼭ ���� Ŭ���� �Ǿ������� ó���Ϸᰡ ���־����� �Ǵ��ؼ� ������ �ְų� ���� ĥ���ش�
			if (tmap.get(key).isComplete()) {
				orderListButton.setBackground(Color.gray);
			}
			if (tmap.get(key).getRequestNumber().equals(selectedRequestNumber))
				orderListButton.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			gridx++;
			if (gridx > 4) {
				gridx = 0;
				gridy++;
			}
			// �̹�ư�� �ּҰ��� �־���� �̺�Ʈó���� �����ϴ� (�׷��Ƿ� orderlist�� button �ʵ� �ϳ� �߰�??
			// �׷��� ��ư�� ���� �����ɶ����� �־�����Ѵ�
			tmap.get(key).setButton(orderListButton);
		}
	}

	/**
	 * Create the frame.
	 */
	public OrderListFrame() {

		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		panel.setLayout(gbl_panel);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(null);

		scrollPane.setViewportView(panel);
		scrollPane.setBounds(0, 0, 650, 590);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane);
		btnOrderview.setBounds(662, 91, 110, 87);
		getContentPane().add(btnOrderview);
		btnComplete.setBounds(662, 230, 110, 87);
		getContentPane().add(btnComplete);
		btnRemove.setBounds(662, 365, 110, 87);
		btnRemove.setVisible(false);
		getContentPane().add(btnRemove);
		setBounds(200, 200, 800, 620);
		// �̺�Ʈ ���--------------------------
		orderController = new OrderController(this);
		btnOrderview.addActionListener(orderController);
		btnComplete.addActionListener(orderController);
		btnRemove.addActionListener(orderController);
	}
}
