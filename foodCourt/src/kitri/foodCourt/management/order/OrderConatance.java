package kitri.foodCourt.management.order;

public interface OrderConatance {
	public static final int PORT = 9876;
//	Protocol ����
	
//	client >> server (client�� ������ �����ϰ� ����Ѵ�)
//	���� �� �ֹ����� ����				100|��û��ȣ|��������
	public static final int CS_ORDER = 100;
	
//	server >> client(�������� �� �� �ִ�)
//	�ֹ����� ���� ���� ����				200|��������
	public static final int SC_ORDER = 200;
	
}
