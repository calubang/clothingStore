package kitri.foodCourt.user;

import java.util.Iterator;

public interface Payment {
	
	//���� �޼ҵ�
	//�߰� : list�߰��� �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public int add(PaymentDetail detail);
	//����(index�� ����) : list������ �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public PaymentDetail remove(int index);
	//����(���ؼ� ����)
	//������ null ������ ������ ��ü
	public PaymentDetail remove(PaymentDetail detailRemove);
	//��� ���� : ��� 0����
	public int removeAll();
	
	
}
