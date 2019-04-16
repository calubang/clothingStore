package kitri.foodCourt.management.order;

import java.awt.Color;
import java.awt.Component;
import java.util.*;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class OrderService {
	StringTokenizer st;
	OrderController orderController;
	OrderListFrame main;

	public OrderService() {
	}

	public OrderService(OrderController orderController) {
		this.orderController = orderController;
		main = orderController.orderListFrame;
	}

	// order����Ʈ�� ������ ���
	public boolean addOrderList(String requestNumber, String orderFood) {
		OrderList order = new OrderList();

		order.setRequestNumber(requestNumber);
		st = new StringTokenizer(orderFood, "|");
		while (st.hasMoreTokens()) {
			order.addMenuName(st.nextToken());
			order.addMenuCount(st.nextToken());
		}
		main.list.add(order);
		main.map.put(requestNumber, order);
		main.panel.removeAll();
		main.addOrder();
		// �г� ��ư ����� ����
		return false;
	}

	// �ֹ����� ������ ����
	public void visibleOrder() {

	}

	// �ֹ���ư �Ϸ� ó�� (��ư�� ���� �ٲ��ش�)
	public void completeOrder() {
		
	}

	// �Ϸ�� ��ư ����� (�Ϸ� ó�� �� ��ư�� Ŭ���������� ��������)
	public void removeOrder() {
		
	}

	// ���ư�� Ŭ���ߴ��� ���������� ���
	// ��ư�� ������ check�� �Ǵ��ؼ� ���Ź�ư�� Ȱ��/ ��Ȱ�� ���Ѿ���
	public void setGlobalOrderButton(Object object) {
		OrderListButton orderListButton = (OrderListButton) object;
		if (main.map.get(orderListButton.getName()).isCheck())
			main.btnRemove.setVisible(true);
		else
			main.btnRemove.setVisible(false);
		if (main.selectedRequestNumber != "") {
//			if (orderListButton != main.) { //�̺κ��� ó���ؾ��Ѵ� �ֳ��ϸ� ��ư���� ���ΰ�ħ�Ҷ� ���� �����Ǳ⶧��
//				orderListButton.setBorder(new LineBorder(new Color(0, 0, 0), 3));
//				main.btnOrder.setBorder(null);
//			} else {
//				orderListButton.setBorder(new LineBorder(new Color(0, 0, 0), 3));
//			}
		}
	}
}
