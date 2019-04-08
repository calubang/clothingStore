package kitri.foodCourt.user.basket;

import kitri.foodCourt.dto.PaymentDto;

public class PaymentService {
	
	public PaymentController controller;
	public PaymentMain paymentMain;
	public PaymentDao paymentDao;
	
	public PaymentService(PaymentController paymentController) {
		this.controller = paymentController;
		this.paymentMain = controller.paymentMain;
		this.paymentDao = new PaymentDao(this);
	}

	public void receiptConfirm() {
		Receipt receipt = paymentMain.receipt;
		receipt.setBounds(paymentMain.getX()+100, paymentMain.getY()+100, receipt.getWidth(), receipt.getHeight());
		paymentMain.receipt.setVisible(true);
		paymentMain.setEnabled(false);
		
	}

	public void receiptOK() {
		paymentMain.setEnabled(true);
		Receipt receipt = paymentMain.receipt;
		receipt.setVisible(false);
		
	}

	public void payment() {
		//���� �����κ� 
		//���������� ������ ���������� ���� Ȯ��
		//��ٱ��� -> ���� Ŭ������ ����
		//paymentDao.payment();
		
		SwingFactory.getOptionPane("message", paymentMain, "����Ȯ��", "�����Ϸ� �Ǿ����ϴ�.");
	}

	public void isCorrectValue() {
		
	}

	public void cancel() {
		//���� ���
		SwingFactory.getOptionPane("warning", paymentMain, "�������", "��ٱ��� ȭ������ ���ư��ðڽ��ϱ�?");
	}
	
}
