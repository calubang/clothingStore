package kitri.foodCourt.management.order;

import java.awt.Color;
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
	public boolean addOrderList(int requestNumber, String orderFood) {
		System.out.println("addOrderList");
//		System.out.println(main.tmap);
//		int size;
//		if(!(main.tmap.isEmpty())) {
//			size = main.tmap.size();
//		} else {
//			size = 0;
//		}
		OrderList order = new OrderList();
		order.setRequestNumber(requestNumber);
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
		main.tmap.put(requestNumber, order);
		// �ֹ� ��� �гο� ������
		constructOrderList();
		System.out.println("�ֹ���� ����� �Ϸ�");
		if (!main.isVisible())
			main.setVisible(true);
//		if (size + 1 == main.tmap.size())
//			return true;
//		else {
//			return false;
//		}
		return true;
	}

	// �ֹ����� ������ ����
	public void visibleOrder() {
		System.out.println("visibleOrder");
		System.out.println(main.selectedRequestNumber);
		main.setVisible(true);
	}

	// �ֹ���ư �Ϸ� ó�� (��ư�� ���� �ٲ��ش�)
	public void completeOrder() {
		System.out.println("completeOrder");
		System.out.println(main.selectedRequestNumber);
		if (!main.tmap.get(main.selectedRequestNumber).isComplete()) {
			main.tmap.get(main.selectedRequestNumber).setComplete();
			main.tmap.get(main.selectedRequestNumber).getButton().setBackground(Color.GREEN);
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
	}

	// ���ư�� Ŭ���ߴ��� ���������� ���
	// ��ư�� ������ check�� �Ǵ��ؼ� ���� ��ư�� Ȱ��/ ��Ȱ�� ���Ѿ���
	public void setGlobalOrderButton(Object object) {
		System.out.println("setGlobalOrderButton");
		System.out.println("requestNumber : " + main.selectedRequestNumber);
		OrderListButton orderListButton = (OrderListButton) object;
		if (main.selectedRequestNumber != 0) {
			if (!orderListButton.getName().equals(Integer.toString(main.selectedRequestNumber))) { // orderlist�ȿ� ��ư �־ �ּҰ� �������� ����
				main.tmap.get(main.selectedRequestNumber).getButton().setBorder(null);
				setClick(orderListButton);
			}
		} else { // �ƹ��͵� ���õǾ����� ������
			setClick(orderListButton);
		}
		if (main.tmap.get(Integer.parseInt(orderListButton.getName())).isComplete())
			main.btnRemove.setVisible(true);
		else
			main.btnRemove.setVisible(false);
	}

	private void setClick(OrderListButton button) {
		System.out.println("setClick");
		//TODO ���� ���⼭ ���� ã�ƾ��� �Ϸ�Ǿ��ִ� ��ư ������ ���Ź�ư�� Ȱ��ȭ�ȵǴ� �����߰�
		button.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		main.selectedRequestNumber = Integer.parseInt(button.getName());
		refresh();
	}

	private void constructOrderList() {
		// �г� ��ư ����� �����
		main.panOrder.removeAll();
		// �����
		main.addOrder();
		if (main.selectedRequestNumber!=0) {
			if (!main.tmap.get(main.selectedRequestNumber).isComplete())
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
}
