package kitri.foodCourt.user.basket;

import java.awt.Font;

import javax.swing.*;

import kitri.foodCourt.user.swing.FOptionPane;

public class BasketService {

	public BasketController controller;
	public BasketMain basketMain;
	String options[] = {"��", "�ƴϿ�"};
	
	public BasketService(BasketController basketController) {
		this.controller = basketController;
		basketMain = controller.basketMain;
	}

	public void allCancel() {
		int result = SwingFactory.getOptionPane("warning",basketMain, "��ٱ��� ����", "��ٱ����� ��� �׸��� �����Ͻðڽ��ϱ�?");
		
	}

	public void receiptOK() {
		
	}

	public void payment() {
		basketMain.payment = new Payment(basketMain);
		basketMain.pChangePanel.add(basketMain.payment, "payment");
		basketMain.card.show(basketMain.pChangePanel, "payment");
		//basketMain.
	}

}
