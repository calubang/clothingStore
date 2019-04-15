package kitri.foodCourt.user.service;

import java.awt.Component;
import java.awt.Font;

import javax.swing.*;

import kitri.foodCourt.user.BasketDetail;
import kitri.foodCourt.user.controller.BasketController;
import kitri.foodCourt.user.swing.*;
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
	
	//��������
	public void countUp(int index) {
		Component components[] = basketMain.pMiddle.getComponents();
		
		//��ٱ��� �ȿ��� �����ͺ���
		int price = basketMain.user.getBasket().upCount(index);
		
		//�ش� ����ġ�� �����ؼ� ������
		FLabel temp = (FLabel)components[index].getComponentAt(570, 50);
		int count = Integer.parseInt(temp.getText()) + 1;
		temp.setText(count+"");
		
		//�����Ѱ��� ����
		temp = (FLabel)components[index].getComponentAt(880, 50);
		temp.setText(price+"");
		
		//���� �ѱݾ� ����
		basketMain.lblTotalPrice.setText(basketMain.user.getBasket().getTotalPrice()+"");
		basketMain.lblTotalPoint.setText(basketMain.user.getBasket().getSavePoint()+"");

	}
	
	//��������
	public void countDown(int index) {
		Component components[] = basketMain.pMiddle.getComponents();
		FLabel temp = (FLabel)components[index].getComponentAt(570, 50);
		int count = Integer.parseInt(temp.getText());
		
		if(count == 1) {
			SwingFactory.getOptionPane("message", basketMain, "���� ����", "�ּ� 1���� �ֹ��ؾ��մϴ�.");
			return;
		}
		
		//��ٱ��� �ȿ��� �����ͺ���
		int price = basketMain.user.getBasket().downCount(index);
		
		//�ش� ����ġ�� �����ؼ� ������
		count--;
		temp.setText(count+"");
		
		temp = (FLabel)components[index].getComponentAt(880, 50);
		temp.setText(price+"");
		
		//���� �ѱݾ� ����
		basketMain.lblTotalPrice.setText(basketMain.user.getBasket().getTotalPrice()+"");
		basketMain.lblTotalPoint.setText(basketMain.user.getBasket().getSavePoint()+"");
	}

}
