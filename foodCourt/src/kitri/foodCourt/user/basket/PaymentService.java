package kitri.foodCourt.user.basket;

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
		
		SwingFactory.getOptionPane("message", payment, "����Ȯ��", "�����Ϸ� �Ǿ����ϴ�.");
	}

	public void isCorrectValue() {
		
	}

	public void cancel() {
		//���� ���
		int select = SwingFactory.getOptionPane("warning", payment, "�������", "��ٱ��� ȭ������ ���ư��ðڽ��ϱ�?");
		if(select == 0) {
			//��
			payment.basketMain.payment = null;
			payment.basketMain.card.show(payment.basketMain.pChangePanel, "basket");
		}
	}
	
}
