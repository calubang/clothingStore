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

	public void allDelete() {
		int result = SwingFactory.getOptionPane("warning",basketMain, "��ٱ��� ����", "��ٱ����� ��� �׸��� �����Ͻðڽ��ϱ�?");
		if(result == 0) {
			basketMain.user.getBasket().removeAll();
			basketMain.pMiddle.removeAll();
			basketMain.dataSetting();
		}
		
	}
	public void payment() {
		if(basketMain.user.getBasket().getDetailList().size() == 0) {
			SwingFactory.getOptionPane("warning", basketMain, "��������", "��ٱ��ϰ� ������ϴ�.");
			return;
		}
		basketMain.payment.dataSetting();
		basketMain.card.show(basketMain.pChangePanel, "payment");
		//basketMain.
	}

	public void delete(int index) {
		basketMain.user.getBasket().remove(index);
		//System.out.println(basketMain.user.getBasket());
		basketMain.pMiddle.removeAll();
		basketMain.dataSetting();
	}

	public void goFoodDetail() {
		//food�� ������ �̵�
		JOptionPane.showMessageDialog(basketMain, "���Ļ������� �̵�(�����ؾ���)", "���", JOptionPane.WARNING_MESSAGE);
	}

}
