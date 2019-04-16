package kitri.foodCourt.user.service;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.crypto.Data;

import kitri.foodCourt.dto.PaymentDto;
import kitri.foodCourt.management.order.OrderConatance;
import kitri.foodCourt.user.BasketDetail;
import kitri.foodCourt.user.User;
import kitri.foodCourt.user.controller.PaymentController;
import kitri.foodCourt.user.dao.PaymentDao;
import kitri.foodCourt.user.swing.SwingFactory;
import kitri.foodCourt.user.view.Payment;
import kitri.foodCourt.user.view.Receipt;

public class PaymentService {

	public PaymentController controller;
	public Payment payment;
	public PaymentDao paymentDao;
	public User user;

	public PaymentService(PaymentController paymentController) {
		this.controller = paymentController;
		this.payment = controller.payment;
		this.paymentDao = new PaymentDao(this);
		this.user = payment.basketMain.user;
	}

	public void receiptConfirm() {
		Receipt receipt = payment.receipt;
		receipt.setBounds(payment.getX() + 100, payment.getY() + 100, receipt.getWidth(), receipt.getHeight());
		receipt.dataSetting();
		payment.receipt.setVisible(true);
		payment.setEnabled(false);

	}

	public void receiptOK() {
		payment.setEnabled(true);
		Receipt receipt = payment.receipt;
		receipt.setVisible(false);
	}

	public void payment() {
		// ���� �����κ�
		// ���������� ������ ���������� ���� Ȯ��
		// ��ٱ��� -> ���� Ŭ������ ����
		// paymentDao.payment();
		int cash = 0, card = 0, point = 0;
		int total = 0;
		int savePoint = 0;
		int usedPoint = 0;

		if (payment.tfCash.getText().isEmpty()) {
			cash = 0;
		} else {
			cash = Integer.parseInt(payment.tfCash.getText());
		}
		if (payment.tfCard.getText().isEmpty()) {
			card = 0;
		} else {
			card = Integer.parseInt(payment.tfCard.getText());
		}
		if (payment.tfPoint.getText().isEmpty()) {
			point = 0;
		} else {
			point = Integer.parseInt(payment.tfPoint.getText());
		}
		total = card + cash + point;
		if (total < user.getBasket().getTotalPrice()) {
			SwingFactory.getOptionPane("message", payment, "��������", "�Է±ݾ� ������ �����ϴ�.");
			return;
		} else if (total > user.getBasket().getTotalPrice()) {
			SwingFactory.getOptionPane("message", payment, "��������", "�Է±ݾ� ������ �����ϴ�.");
			return;
		}
		// ����Ʈ ����
		savePoint = user.getBasket().getSavePoint();
		usedPoint = point;
		user.setUserPoint(user.getUserPoint() - usedPoint + savePoint);

		// DB����
		user.getBasket().setRequestNumber(payment.requestNumber);
		int dbResult = paymentDao.insertPayment(user, card, cash, point);
		if (dbResult == -1) {
			// ������
			System.out.println("����������");
			SwingFactory.getOptionPane("errorMessage", payment, "��������", "��������, �����ڸ� ȣ�����ּ���.");
			return;
		}

		// ��Ʈ��ũ ���
		// ---------------------------------------------------
		Socket socket;
		String orderStr = Integer.toString(user.getBasket().getRequestNumber()) + "|";
		boolean flag = true;
		try {
			socket = new Socket(OrderConatance.IP, OrderConatance.PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStream out = socket.getOutputStream();
			List<BasketDetail> list = user.getBasket().getDetailList();
			for (BasketDetail basketDetail : list) {
				orderStr += basketDetail.getFood().getFoodName() + "^" + basketDetail.getCount() + "^";
			}
//			100|��û��ȣ|�����̸�|����~
			sendMassage(OrderConatance.CS_ORDER + "|" + orderStr, out);

			while (flag) {
				String msg = in.readLine();
				System.out.println("������ ���� �޼��� : " + msg); 
				StringTokenizer st = new StringTokenizer(msg, "|"); 
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case OrderConatance.SC_ORDER_RESULT: {
//				200|true or false
					String tmp = st.nextToken();
					if(tmp.equals("false")) {
						flag = false;
//						JOptionPane.showMessageDialog(parentComponent, "�ֹ� ��û ������ �߻��߽��ϴ�. �������� �������ּ���", "�ֹ� ����", JOptionPane.ERROR_MESSAGE);
					}
					else
						flag = false;
				}
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ---------------------------------------------------

		// ��� ������
		int result = SwingFactory.getOptionPane("message", payment, "����Ȯ��", payment.requestNumber + "������ �����Ϸ� �Ǿ����ϴ�.");
		if (result == 0) {
			payment.requestNumber++;
			payment.basketMain.foodMain.card.show(payment.basketMain.foodMain.panChangePanel, "foodMainView");
			// ��ٱ��� �ʱ�ȭ
			payment.basketMain.user.getBasket().removeAll();
		}
	}

	public void cancel() {
		// ���� ���
		int select = SwingFactory.getOptionPane("warning", payment, "�������", "��ٱ��� ȭ������ ���ư��ðڽ��ϱ�?");
		if (select == 0) {
			// ��
			payment.basketMain.dataSetting();
			payment.basketMain.card.show(payment.basketMain.pChangePanel, "basket");
		}
	}

	// �Է¹��� ����Ȯ��
	public void checkValue(JTextField tp, char input) {
		if (input > '9' || input < '0') {
			if (tp.getText().isEmpty()) {
				return;
			}
			tp.setText(tp.getText().substring(0, tp.getText().length() - 1));
			// SwingFactory.getOptionPane("warning", payment, "�߸��� �Է�", "���ڸ� �Է����ּ���");
			return;
		}

		// ����Ʈ�� ���� �Է� �� ��������Ʈ�� ���ϴ� �۾�
		if (tp.getName().equals("point")) {
			int point = 0;
			if (payment.tfPoint.getText().isEmpty()) {
				point = 0;
				return;
			} else {
				point = Integer.parseInt(payment.tfPoint.getText());
			}
			if (point > payment.basketMain.user.getUserPoint()) {
				tp.setText(tp.getText().substring(0, tp.getText().length() - 1));
				SwingFactory.getOptionPane("warning", payment, "�߸��� �Է�", "��밡���� ����Ʈ�� �Ѿ����ϴ�.");
				return;
			}
		}

	}

	private void sendMassage(String msg, OutputStream out) {
		try {
			out.write((msg + "|\n").getBytes()); // BufferedReader�� enter key ����
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
