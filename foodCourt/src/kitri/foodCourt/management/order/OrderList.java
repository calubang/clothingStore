package kitri.foodCourt.management.order;

import java.util.Vector;

public class OrderList { 
	private String  requestNumber;
	private boolean checkComplete = false; // �� ������ �������� ���ΰ�ħ �ϸ鼭 üũ�� �ֹ� ��� ǥ��
	private Vector<String> menuName = new Vector<String>();
	private Vector<String> menucount = new Vector<String>();
	public void addMenuName(String name) {
		menuName.add(name);
	}
	public void addMenuCount(String count) {
		menucount.add(count);
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getRequestNumber() {
		return this.requestNumber;
	}
	
	public String getMenuName(int i) {
		return menuName.get(i);
	}
	
	public String getMenuCount(int i) {
		return menucount.get(i);
	}
	public int getMenuNameSize() {
		return menuName.size();
	}
	
	public int getMenuCountSize() {
		return menucount.size();
	}
	public void setCheck() {
		this.checkComplete = true;
	}
	public boolean isCheck() {
		return this.checkComplete;
	}
}
