package kitri.foodCourt.management.order;

import java.io.*;
import java.net.*;
import java.util.*;

public class OrderListServer implements Runnable {
	// ������ ���� ���� ����
	public OrderService orderService;
	// �������� ����
	public ServerSocket serverSocket = null;
	public Socket socket = null;
	public BufferedReader in = null;
	public OutputStream out = null;
	public boolean flag = true;
	public OrderListServer(OrderService orderService) {
		this.orderService = orderService;
		try {
			serverSocket = new ServerSocket(OrderConatance.PORT); // ��Ʈ ������
			System.out.println("Ŭ���̾�Ʈ ���� �����............");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() { // ���� ó��
		while (flag) { // ���� �Ǵ� ���� ���� Ŭ���̾�Ʈ ������ ��ٸ���
			try {
				socket = serverSocket.accept(); // socket�� Ŭ���̾�Ʈ ���� ����
				System.out.println("Ŭ���̾�Ʈ ���� ���� : " + socket);
				String requestNumber;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = socket.getOutputStream();
				String msg = in.readLine();
				System.out.println("Ŭ���̾�Ʈ�� ���� �޼��� : " + msg); // protocol|�޼�������
				StringTokenizer st = new StringTokenizer(msg, "|");
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case OrderConatance.CS_ORDER: {
//					100|requestNumber|�����̸�^����^�����̸�^����| ~~
					requestNumber = st.nextToken();
					orderService.addOrderList(Integer.parseInt(requestNumber), st.nextToken());
						System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�");
						unicast(Integer.toString(OrderConatance.SC_ORDER_RESULT), out);
					break;
				}
				}
			} catch (SocketException e) {
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void unicast(String msg, OutputStream out) {
		try {
			out.write((msg + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}