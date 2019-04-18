package kitri.foodCourt.user.service;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.*;
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
		payment.receipt.setVisible(false);
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

		// ��Ʈ��ũ ���
		// ---------------------------------------------------
		Socket socket = null;
		BufferedReader in = null;
		OutputStream out = null;
		String orderStr = "";
		boolean flag = true;
		String orderNumber=""; // �������� �������� request ��ȣ
		try {
			socket = new Socket(OrderConatance.IP, OrderConatance.PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();
			List<BasketDetail> list = user.getBasket().getDetailList();
			for (BasketDetail basketDetail : list) {
				orderStr += basketDetail.getFood().getFoodName() + "^" + basketDetail.getCount() + "^";
			}
//			100|�����̸�^����~|
			sendMassage(OrderConatance.CS_ORDER + "|" + orderStr, out);

			while (flag) {
				String msg = in.readLine();
				System.out.println("������ ���� �޼��� : " + msg);
				StringTokenizer st = new StringTokenizer(msg, "|");
				int protocol = Integer.parseInt(st.nextToken());
				switch (protocol) {
				case OrderConatance.SC_ORDER_RESULT: {
//					200|true or false
					orderNumber = st.nextToken();
					System.out.println("�ֹ� ��ȣ"+orderNumber);
					flag = false;
				}
				}
			}
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(controller.payment.basketMain.foodMain, "�ֹ� ������ �߻��߽��ϴ� \n �������� �������ּ���", "���� ����", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(controller.payment.basketMain.foodMain, "�ֹ� ������ �߻��߽��ϴ� \n �������� �������ּ���", "���� ����", JOptionPane.ERROR_MESSAGE);
			return;
		} finally {
			if (out != null) 		{try {out.close();} 	catch (IOException e) {e.printStackTrace();}}
			if (in != null) 		{try {in.close();} 		catch (IOException e) {e.printStackTrace();}}
			if (socket != null) 	{try {socket.close();} 	catch (IOException e) {e.printStackTrace();}}

		}
		// ---------------------------------------------------
		
		// DB����
		user.getBasket().setRequestNumber(Integer.valueOf(orderNumber));
		int dbResult = paymentDao.insertPayment(user, card, cash, point);
		if (dbResult == -1) {
			// ������
			System.out.println("����������");
			SwingFactory.getOptionPane("errorMessage", payment, "��������", "��������, �����ڸ� ȣ�����ּ���.");
			return;
		}
		
		// ����Ʈ ����
		savePoint = user.getBasket().getSavePoint();
		usedPoint = point;
		user.setUserPoint(user.getUserPoint() - usedPoint + savePoint);
		
		// ��� ������
		int result = SwingFactory.getOptionPane("message", payment, "����Ȯ��", orderNumber + "������ �����Ϸ� �Ǿ����ϴ�.");
		if (result == 0) {
			//payment.requestNumber++;
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
		if(input == KeyEvent.VK_BACK_SPACE) {
			return;
		}
		if( input < 32 || input > 126 ) {
			return;
		}
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
