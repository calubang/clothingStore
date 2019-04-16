package kitri.foodCourt.management.order;

import java.awt.*;
import javax.swing.*;

public class OrderListPanel extends JPanel {
	JButton btnImg;
	JLabel labName;
	Dimension ds = new Dimension(185, 190);
	Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	/**
	 * Create the panel.
	 */

	public OrderListPanel(ImageIcon roperImg, String foodName) {
		setBackground(Color.WHITE);
		
		//btnImg = new OrderListButton("order");
		labName = new JLabel(foodName);
		labName.setFont(new Font("����", Font.PLAIN, 16));
		labName.setHorizontalAlignment(SwingConstants.CENTER);
		btnImg.setCursor(cursor);
		setMaximumSize(ds);
		setPreferredSize(ds);
		setLayout(new BorderLayout());
		add(btnImg, "Center");
		add(labName, "South");
		
		setVisible(true);
		btnImg.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// �̺�Ʈ ��Ϻ�
		
//		MenuClickController
//		label.addMouseListener(l);
	}
}
