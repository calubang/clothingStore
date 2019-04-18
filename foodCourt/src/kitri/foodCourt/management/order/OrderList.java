package kitri.foodCourt.management.order;

import java.util.List;
import java.util.Vector;

public class OrderList { 
	private OrderListButton button;
	private int requestNumber;
	private boolean checkComplete = false; // �� ������ �������� ���ΰ�ħ �ϸ鼭 üũ�� �ֹ� ��� ǥ��
	private boolean checkOrder = false; // �� ������ �������� �ֹ������� Ȯ���ߴ��� üũ
	private Vector<String> menuName = new Vector<String>();
	private Vector<String> menuCount = new Vector<String>();
	public void addMenuName(String name) {
		System.out.println("");
		menuName.add(name);
	}
	public void addMenuCount(String count) {
		menuCount.add(count);
	}
	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}
	public int getRequestNumber() {
		return this.requestNumber;
	}
	
	public String getMenuName(int i) {
		return menuName.get(i);
	}
	
	public String getMenuCount(int i) {
		return menuCount.get(i);
	}
	public int getMenuNameSize() {
		return menuName.size();
	}
	
	public int getMenuCountSize() {
		return menuCount.size();
	}
	public void setComplete() {
		this.checkComplete = true;
	}
	public boolean isComplete() {
		return this.checkComplete;
	}
	public void setButton(OrderListButton button) {
		this.button = button; 
	}
	public OrderListButton getButton() {
		return this.button;
	}
	public void setCheckOrder() {
		this.checkOrder = true;
	}
	public boolean isCheckOrder() {
		return this.checkOrder;
	}
}
