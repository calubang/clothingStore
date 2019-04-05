package kitri.foodCourt.dto;

import java.util.*;

public class PaymentDto implements Payment{
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
	private char paymentState;
	//������ ���Ե� ����
	private List<PaymentDetailDto> detailList;
	
	//������
	public PaymentDto(UserDto user) {
		super();
		this.user = user;
		totalPrice = 0;
		savePoint = 0;
		usedPoint = 0;
		card = 0;
		cash = 0;
		detailList = new ArrayList<PaymentDetailDto>();
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
	public char getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(char paymentState) {
		this.paymentState = paymentState;
	}
	public List<PaymentDetailDto> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<PaymentDetailDto> detailList) {
		this.detailList = detailList;
	}
	
	//���� �޼ҵ�
	//�߰�
	public int add(PaymentDetailDto detail) {
		detailList.add(detail);
		totalPrice += detail.getFood().getPrice();
		savePoint += detail.getFood().getPoint();
		return detailList.size();
	}
	//����(index�� ����)
	public PaymentDetailDto remove(int index) {
		PaymentDetailDto detail = remove(index);
		totalPrice -= detail.getFood().getPrice();
		savePoint -= detail.getFood().getPoint();
		return detailList.remove(index);
	}
	//����(���ؼ� ����)
	//������ null ������ ������ ��ü
	public PaymentDetailDto remove(PaymentDetailDto detailRemove) {
		int size = detailList.size();
		PaymentDetailDto detail = null;
		for(int i = 0 ; i<size ; i++) {
			if(detailList.get(i) == detailRemove) {
				remove(i);
			}
		}
		return detail;
	}
	//��� ����
	public int removeAll() {
		Iterator<PaymentDetailDto> iter = detailList.iterator();
		int size = detailList.size();
		while(iter.hasNext()) {
			iter.remove();
		}
		totalPrice = 0;
		savePoint = 0;
		return size;
	}
	
}
