package kitri.foodCourt.user;

import java.util.Iterator;

public interface Basket {
	
	//���� �޼ҵ�
	//�߰� : list�߰��� �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public int add(BasketDetail detail);
	//����(index�� ����) : list������ �ƴ϶� totalPrice�� savePoint ���� ���� ����
	public BasketDetail remove(int index);
	//����(���ؼ� ����)
	//������ null ������ ������ ��ü
	public BasketDetail remove(BasketDetail detailRemove);
	//��� ���� : ��� 0����
	public int removeAll();
	
	
}
