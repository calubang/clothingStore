package kitri.foodCourt.user;

import java.util.*;

import kitri.foodCourt.dto.UserDto;

public class PaymentImpl implements Payment{
	//��������
	//private String paymentId;
	private String requestNumber;				//��û��ȣ
	private UserDto user;							//��������
	private String paymentDate;					//������
	private int totalPrice;							//�Ѱ���
	private int savePoint;
	//������ ����� �ݾ�
	private int usedPoint;
	private int card;
	private int cash;
	//������ ���Ե� ����
	private List<PaymentDetail> detailList;
	
	//������
	public PaymentImpl(UserDto user) {
		super();
		this.user = user;
		totalPrice = 0;
		savePoint = 0;
		usedPoint = 0;
		card = 0;
		cash = 0;
		detailList = new ArrayList<PaymentDetail>();
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public UserDto getUser() {
		return user;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getSavePoint() {
		return savePoint;
	}
	public void setSavePoint(int savePoint) {
		this.savePoint = savePoint;
	}
	public int getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}
	public int getCard() {
		return card;
	}
	public void setCard(int card) {
		this.card = card;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public List<PaymentDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<PaymentDetail> detailList) {
		this.detailList = detailList;
	}
	
	//���� �޼ҵ�
	//������
	//�߰�
	public int add(PaymentDetail detail) {
		detailList.add(detail);
		totalPrice += detail.getFood().getPrice();
		savePoint += detail.getFood().getPoint();
		return detailList.size();
	}
	//����(index�� ����)
	public PaymentDetail remove(int index) {
		PaymentDetail detail = remove(index);
		totalPrice -= detail.getFood().getPrice();
		savePoint -= detail.getFood().getPoint();
		return detailList.remove(index);
	}
	//����(���ؼ� ����)
	//������ null ������ ������ ��ü
	public PaymentDetail remove(PaymentDetail detailRemove) {
		int size = detailList.size();
		PaymentDetail detail = null;
		for(int i = 0 ; i<size ; i++) {
			if(detailList.get(i) == detailRemove) {
				remove(i);
			}
		}
		return detail;
	}
	//��� ����
	public int removeAll() {
		Iterator<PaymentDetail> iter = detailList.iterator();
		int size = detailList.size();
		while(iter.hasNext()) {
			iter.remove();
		}
		totalPrice = 0;
		savePoint = 0;
		return size;
	}
	
	@Override
	public String toString() {
		return "PaymentImpl [requestNumber=" + requestNumber + ", user=" + user + ", paymentDate=" + paymentDate
				+ ", totalPrice=" + totalPrice + ", savePoint=" + savePoint + ", usedPoint=" + usedPoint + ", card="
				+ card + ", cash=" + cash + ", detailList=" + detailList + "]";
	}
}
