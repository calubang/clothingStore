package kitri.foodCourt.management.order;

public interface OrderConatance {
	public static final int PORT = 9876;
	public static final String IP = "192.168.14.11";
	//192.168.14.52
	//192.168.14.11
//	Protocol ����
	
//	client >> server (client�� ������ �����ϰ� ����Ѵ�)
//	���� �� �ֹ����� ����				100|�����̸�^����^�����̸�^����^�����̸�^����| - �ݺ�
	public static final int CS_ORDER = 100;
	
//	server >> client(�������� �� �� �ִ�)
//	�ֹ����� ���� ���� ����				200|
//	�ֹ����� ���� ���� ����				200|orderNumber|
	public static final int SC_ORDER_RESULT = 200;
	
}
