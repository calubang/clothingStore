package kitri.foodCourt.management.order;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class OrderListServer implements Runnable {
	// ������ ���� ���� ����
	OrderService service = new OrderService();
	// �������� ����
	ServerSocket ss;

	public OrderListServer() {
		try {
			ss = new ServerSocket(OrderConatance.PORT); // ��Ʈ ������
			System.out.println("Ŭ���̾�Ʈ ���� �����............");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() { // ���� ó��
		while (true) { // ���� �Ǵ� ���� ���� Ŭ���̾�Ʈ ������ ��ٸ���
			try {
				Socket socket = ss.accept(); // socket�� Ŭ���̾�Ʈ ���� ����
				System.out.println("Ŭ���̾�Ʈ ���� ���� : " + socket);
				BufferedReader in;
				OutputStream out;
				String requestNumber;
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = socket.getOutputStream();
				String msg = in.readLine();
				System.out.println("Ŭ���̾�Ʈ�� ���� �޼��� : " + msg); // protocol|�޼�������
				StringTokenizer st = new StringTokenizer(msg, "|");
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case OrderConatance.CS_ORDER: {
//						100|requestNumber|�����̸�|����|�����̸�|���� ~~
					requestNumber = st.nextToken();
					if (service.addOrderList(requestNumber, st.nextToken())) {
						unicast(OrderConatance.SC_ORDER_RESULT + "|" + "true", out);
					} else {
						unicast(OrderConatance.SC_ORDER_RESULT + "|" + "false", out);
					}
					break;
				}
				}
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