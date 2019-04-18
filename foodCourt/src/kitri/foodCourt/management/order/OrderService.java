package kitri.foodCourt.management.order;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.border.LineBorder;

public class OrderService {
	StringTokenizer st;
	OrderController orderController;
	OrderListFrame main;
	
	public OrderService(OrderController orderController) {
		this.orderController = orderController;
		main = orderController.orderListFrame;
	}

	// order����Ʈ�� ������ ���
	public int addOrderList(String orderFood) {
		main.orderNumber++;
		System.out.println("addOrderList");
		OrderList order = new OrderList();
		order.setRequestNumber(main.orderNumber);
		System.out.println(orderFood);
		st = new StringTokenizer(orderFood, "^");
		while (st.hasMoreTokens()) {
			order.addMenuName(st.nextToken());
			order.addMenuCount(st.nextToken());
		}
		System.out.println("order�� ���� ��� ��");
		for (int i = 0; i < order.getMenuCountSize(); i++) {
			System.out.println("order : " + order.getRequestNumber() + " : " + order.getMenuName(i) + " : "
					+ order.getMenuCount(i));
		}
		main.tmap.put(main.orderNumber, order);
		// �ֹ� ��� �гο� ������
		constructOrderList();
		System.out.println("�ֹ���� ����� �Ϸ�");
		if (!main.isVisible())
			main.setVisible(true);
		return main.orderNumber;
	}

	// �ֹ����� ������ ����
	public void visibleOrder() {
		// ���� �ð� ���ϱ�
		if (isRequestNotZero()) {
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd a h�� mm�� ss��");
			String str = dayTime.format(new Date(time));

//		1. ���� orderlist�����ͼ� 
//		2. main detail jtable�� �ѷ��ְ�
//		3. setvisible �����ֱ�
			System.out.println("visibleOrder");
			OrderList orderList = getSelectedOrderList();
			int len = orderList.getMenuNameSize();
			Vector<String[]> menuList = new Vector<String[]>();
			for (int i = 0; i < len; i++) {
				String menu[] = new String[2];
				menu[0] = orderList.getMenuName(i);
				menu[1] = orderList.getMenuCount(i);
				System.out.println(orderList.getMenuName(i));
				System.out.println(orderList.getMenuCount(i));
				menuList.add(menu);
			}
			main.orderDetailDialog.labRequestNum.setText("�ֹ���ȣ : " + Integer.toString(main.selectedRequestNumber));
			main.orderDetailDialog.labTimeNow.setText(str);
			System.out.println("�ֹ��������� : "+len);
			main.orderDetailDialog.setOrderDetail(len, menuList);
			main.orderDetailDialog.revalidate();
			main.orderDetailDialog.setVisible(true);
			
		}
	}

	// �ֹ���ư �Ϸ� ó�� (��ư�� ���� �ٲ��ش�)
	public void completeOrder() {
		System.out.println("completeOrder");
		System.out.println(main.selectedRequestNumber);
		if (isRequestNotZero() && !getSelectedOrderList().isComplete()) {
			getSelectedOrderList().setComplete();
			getSelectedButton().setBackground(Color.GREEN);
			refresh();
			main.btnRemove.setVisible(true);
		}
	}

	// �Ϸ�� ��ư ����� (�Ϸ� ó�� �� ��ư�� Ŭ���������� ��������)
	public void removeOrder() {
		System.out.println("removeOrder");
		System.out.println(main.selectedRequestNumber);
		main.tmap.remove(main.selectedRequestNumber);
		main.selectedRequestNumber = 0;
		constructOrderList();
		main.btnRemove.setVisible(false);
		main.btnOrderview.setVisible(false);
		main.btnComplete.setVisible(false);
	}

	// ���ư�� Ŭ���ߴ��� ���������� ���
	// ��ư�� ������ check�� �Ǵ��ؼ� ���� ��ư�� Ȱ��/ ��Ȱ�� ���Ѿ���
	public void setGlobalOrderButton(Object object) {
		System.out.println("setGlobalOrderButton");
		System.out.println("requestNumber : " + main.selectedRequestNumber);
		OrderListButton orderListButton = (OrderListButton) object;
		if (isRequestNotZero()) {
			if (!orderListButton.getName().equals(Integer.toString(main.selectedRequestNumber))) { // orderlist�ȿ� ��ư �־
																									// �ּҰ� �������� ����
				getSelectedButton().setBorder(main.defualt);
				setClick(orderListButton);
			}
		} else { // �ƹ��͵� ���õǾ����� ������
			setClick(orderListButton);
		}
		main.btnOrderview.setVisible(true);
		if (main.tmap.get(Integer.parseInt(orderListButton.getName())).isCheckOrder())
			main.btnComplete.setVisible(true);
		else
			main.btnComplete.setVisible(false);
		if (main.tmap.get(Integer.parseInt(orderListButton.getName())).isComplete())
			main.btnRemove.setVisible(true);
		else
			main.btnRemove.setVisible(false);
	}

	private void setClick(OrderListButton button) {
		System.out.println("setClick");
		// TODO ���� ���⼭ ���� ã�ƾ��� �Ϸ�Ǿ��ִ� ��ư ������ ���Ź�ư�� Ȱ��ȭ�ȵǴ� �����߰�
		button.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		main.selectedRequestNumber = Integer.parseInt(button.getName());
		refresh();
	}

	private void constructOrderList() {
		// �г� ��ư ����� �����
		main.panOrder.removeAll();
		// �����
		main.addOrder();
		if (!isRequestNotZero()) {
			main.btnOrderview.setVisible(false);
		}
		if (isRequestNotZero()) {
			if (!getSelectedOrderList().isCheckOrder())
				main.btnComplete.setVisible(false);
			else
				main.btnComplete.setVisible(true);
		}
		if (isRequestNotZero()) {
			if (!getSelectedOrderList().isComplete())
				main.btnRemove.setVisible(false);
			else
				main.btnRemove.setVisible(true);
		}
		// ��������
		refresh();
	}

	private void refresh() {
		main.panOrder.revalidate();
		main.panOrder.repaint();
	}

	private OrderListButton getSelectedButton() {
		return main.tmap.get(main.selectedRequestNumber).getButton();
	}

	private OrderList getSelectedOrderList() {
		return main.tmap.get(main.selectedRequestNumber);
	}
	private boolean isRequestNotZero() {
		if(main.selectedRequestNumber != 0)
			return true;
		else 
			return false;
	}

	public void closingOrderDetail() {
		OrderList orderList = getSelectedOrderList();
		main.orderDetailDialog.setVisible(false);
		main.orderDetailDialog.labRequestNum.setText("");
		main.orderDetailDialog.labTimeNow.setText("");
		main.btnComplete.setVisible(true);
		orderList.setCheckOrder();
		if(!orderList.isComplete())
			orderList.getButton().setBackground(main.color);
		refresh();
	}
}
