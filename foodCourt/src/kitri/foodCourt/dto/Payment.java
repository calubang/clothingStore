package kitri.foodCourt.dto;

import java.util.Iterator;

public interface Payment {
	
	//���� �޼ҵ�
	//�߰� : list�߰��� �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public int add(PaymentDetailDto detail);
	//����(index�� ����) : list������ �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public PaymentDetailDto remove(int index);
	//����(���ؼ� ����)
	//������ null ������ ������ ��ü
	public PaymentDetailDto remove(PaymentDetailDto detailRemove);
	//��� ���� : ��� 0����
	public int removeAll();
	
	
}
