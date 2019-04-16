package kitri.foodCourt.management.order;

import java.awt.Color;
import java.util.*;

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
		int size = main.tmap.size();
		OrderList order = new OrderList();
		order.setRequestNumber(requestNumber);
		st = new StringTokenizer(orderFood, "|");
		while (st.hasMoreTokens()) {
			order.addMenuName(st.nextToken());
			order.addMenuCount(st.nextToken());
		}
		main.tmap.put(requestNumber, order);
		// �г� ��ư ����� �����
		main.panel.removeAll();
		// �����
		main.addOrder();
		if(!main.isVisible())
			main.setVisible(true);
		if(size+1 == main.tmap.size())
			return true;
		else {
			return false;
		}
	}

	// �ֹ����� ������ ����
	public void visibleOrder() {
		main.setVisible(true);
	}

	// �ֹ���ư �Ϸ� ó�� (��ư�� ���� �ٲ��ش�)
	public void completeOrder() {
		main.tmap.get(main.selectedRequestNumber).setComplete();
		main.tmap.get(main.selectedRequestNumber).getButton().setBackground(Color.GREEN);
	}

	// �Ϸ�� ��ư ����� (�Ϸ� ó�� �� ��ư�� Ŭ���������� ��������)
	public void removeOrder() {
		
	}

	// ���ư�� Ŭ���ߴ��� ���������� ���
	// ��ư�� ������ check�� �Ǵ��ؼ� ���Ź�ư�� Ȱ��/ ��Ȱ�� ���Ѿ���
	public void setGlobalOrderButton(Object object) {
		OrderListButton orderListButton = (OrderListButton) object;
		if (main.tmap.get(orderListButton.getName()).isComplete())
			main.btnRemove.setVisible(true);
		else
			main.btnRemove.setVisible(false);
		if (main.selectedRequestNumber != "") { 
			if (!orderListButton.getName().equals(main.selectedRequestNumber)) { // orderlist�ȿ� ��ư �־ �ּҰ� �������� ����
				setClick(orderListButton);
			} 
		} else { //�ƹ��͵� ���õǾ����� ������
			setClick(orderListButton);
		}
	}
	private void setClick(OrderListButton button) {
		main.tmap.get(main.selectedRequestNumber).getButton().setBorder(null);
		button.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		main.selectedRequestNumber = button.getName();
	}
}
