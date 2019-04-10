package kitri.foodCourt.user.basket;

import javax.xml.crypto.Data;

import kitri.foodCourt.dto.PaymentDto;

public class PaymentService {
	
	public PaymentController controller;
	public Payment payment;
	public PaymentDao paymentDao;
	
	public PaymentService(PaymentController paymentController) {
		this.controller = paymentController;
		this.payment = controller.payment;
		this.paymentDao = new PaymentDao(this);
	}

	public void receiptConfirm() {
		Receipt receipt = payment.receipt;
		receipt.setBounds(payment.getX()+100, payment.getY()+100, receipt.getWidth(), receipt.getHeight());
		payment.receipt.setVisible(true);
		payment.setEnabled(false);
		
	}

	public void receiptOK() {
		payment.setEnabled(true);
		Receipt receipt = payment.receipt;
		receipt.setVisible(false);
	}

	public void payment() {
		//���� �����κ�
		//���������� ������ ���������� ���� Ȯ��
		//��ٱ��� -> ���� Ŭ������ ����
		//paymentDao.payment();
		int cash = Integer.parseInt(payment.tpCash.getText());
		
		SwingFactory.getOptionPane("message", payment, "����Ȯ��", payment.requestNumber + "������ �����Ϸ� �Ǿ����ϴ�.");
		payment.requestNumber++;
		//��ٱ��� �ʱ�ȭ
		payment.basketMain.user.getBasket().removeAll();
		//��Ʈ��ũ ���
	}

	public void isCorrectValue() {
		
	}

	public void cancel() {
		//���� ���
		int select = SwingFactory.getOptionPane("warning", payment, "�������", "��ٱ��� ȭ������ ���ư��ðڽ��ϱ�?");
		if(select == 0) {
			//��
			payment.basketMain.card.show(payment.basketMain.pChangePanel, "basket");
		}
	}
	
}
