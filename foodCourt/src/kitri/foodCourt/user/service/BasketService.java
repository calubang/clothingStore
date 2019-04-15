package kitri.foodCourt.user.service;

import java.awt.Font;

import javax.swing.*;

import kitri.foodCourt.user.controller.BasketController;
import kitri.foodCourt.user.swing.FOptionPane;
import kitri.foodCourt.user.swing.SwingFactory;
import kitri.foodCourt.user.view.BasketMain;

public class BasketService {

	public BasketController controller;
	public BasketMain basketMain;
	
	public BasketService(BasketController basketController) {
		this.controller = basketController;
		basketMain = controller.basketMain;
	}

	public void allDelete() {
		if(basketMain.user.getBasket().getDetailList().size() == 0) {
			return;
		}
		int result = SwingFactory.getOptionPane("warning",basketMain, "��ٱ��� ����", "��ٱ����� ��� �׸��� �����Ͻðڽ��ϱ�?");
		if(result == 0) {
			basketMain.user.getBasket().removeAll();
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
	}

	public void delete(int index) {
		basketMain.user.getBasket().remove(index);
		basketMain.dataSetting();
	}

	public void goFoodDetail(String foodId) {
		//food�� ������ �̵�
		int result = SwingFactory.getOptionPane("messageChoice", basketMain, "������", "���� �������� �̵��Ͻðڽ��ϱ�?");
		if(result == 0) {
			basketMain.foodMain.foodMainController.foodMainService.searchMenuDetail(foodId);
		}
	}

}
