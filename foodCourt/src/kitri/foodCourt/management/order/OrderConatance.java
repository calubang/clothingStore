package kitri.foodCourt.management.order;

public interface OrderConatance {
	public static final int PORT = 9876;
	public static final String IP = "192.168.14.89";
//	Protocol ����
	
//	client >> server (client�� ������ �����ϰ� ����Ѵ�)
//	���� �� �ֹ����� ����				100|��û��ȣ|�����̸�|����|�����̸�|����|�����̸�|���� - �ݺ�
	public static final int CS_ORDER = 100;
	
//	server >> client(�������� �� �� �ִ�)
//	�ֹ����� ���� ���� ����				200|
	public static final int SC_ORDER_RESULT = 200;
	
}
