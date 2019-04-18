package kitri.foodCourt.user.service;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.*;

import kitri.foodCourt.dto.FoodDto;
import kitri.foodCourt.user.BasketDetail;
import kitri.foodCourt.user.User;
import kitri.foodCourt.user.controller.FoodMainController;
import kitri.foodCourt.user.dao.MenuDao;
import kitri.foodCourt.user.swing.*;
import kitri.foodCourt.user.view.*;

public class FoodMainService {
	public FoodMainController foodMainController;
	public FoodMain foodMain;
	public MenuDao menuDao = new MenuDao();
	public ImageIcon icon = new ImageIcon(FoodMain.class.getResource("/img/user/basket.png"));

	public FoodMainService(FoodMainController foodMainController) {
		this.foodMainController = foodMainController;
		this.foodMain = foodMainController.foodMain;
	}

	// ���� �޴� ��� �������� (���θ޴�)
	public void clickMain() {
		foodMain.setPanelName("foodMainView");
		foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
	}

	// �޴� ��� �������� (�ѽ�, �߽�, �Ͻ�, ���)
	public void searchCategory(String category) {
		List<FoodDto> list = menuDao.getMenubyCategory(Integer.parseInt(category));
		// foodMain.panChangePanel.remove(foodMain.userMenuView);
		foodMain.panChangePanel.add(new UserMenuView(list, this), "userMenuView");
		foodMain.setPanelName("userMenuView");
		foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
	}

	// �ֹ���� �������� (�˻� ��ư)
	public void searchMenuName() {
		if (!foodMain.getPanelName().equals("basketMain")) {
			searchMenuNameImpl();
		} else {
			// ���� ���������� �����ڽ��ϱ� (��ٱ��� Ȥ�� ���� ����������)
			int result;
			result = JOptionPane.showConfirmDialog(foodMain.UserMenuDetailView, "�������������� �����ðڽ��ϱ�?", "������",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(result==0) {
				searchMenuNameImpl();
			} 
		}
	}

	// �޴� �� �������� (�޴� �̹��� ��ư)
	public void searchMenuDetail(String food_id) {
		FoodDto foodDto = menuDao.getMenubyId(food_id);
		// foodMain.panChangePanel.remove(foodMain.UserMenuDetailView);
		foodMain.panChangePanel.add(new UserMenuDetailView(foodDto, this), "userMenuDetailView");
		foodMain.setPanelName("userMenuDetailView");
		foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
		System.out.println("ī�� ���̾ƿ� ����Ʈ�� : " + foodMain.card.toString());
	}

	// �������� (�޴� �� ���������� �������� ��ư)
	public void backMenu() {
		foodMain.card.show(foodMain.panChangePanel, "userMenuView");
	}

	// �ֹ���� �߰� (��ٱ��� �߰� ��ư)
	public void addMenuInbasket(FoodDto foodDto, int count) {
		if (count != 0) {
			System.out.println("���� ���� : " + count);
			int result;
			int subResult;
			result = JOptionPane.showConfirmDialog(foodMain.UserMenuDetailView, "�ֹ���Ͽ� �߰��Ͻðڽ��ϱ�?", "�ֹ� �޴� �߰�",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (result == 0) { // Ȯ���� ������ ��
				// ��ٱ��� List�� ���� �޴��� �߰�
				BasketDetail detail = new BasketDetail(foodDto, count);
				foodMain.user.getBasket().add(detail);
				foodMain.basketMain.dataSetting();
				subResult = JOptionPane.showConfirmDialog(foodMain.UserMenuDetailView, "�߰��Ǿ����ϴ�\n��ٱ��Ϸ� ���ðڽ��ϱ�?",
						"titme", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
				System.out.println(subResult);
				if (subResult == 1 || subResult == -1) {
					backMenu();
				} else {
					foodMain.setPanelName("basketMain");
					foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
					foodMain.basketMain.card.show(foodMain.basketMain.pChangePanel, "basket");
				}
			}
		} else {
			JOptionPane.showMessageDialog(foodMain.UserMenuDetailView, "������ 1�� �̻� �Է����ּ���", "",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// ���� �������� (���� ���� ��ư)
	public void userInfo() {
		foodMain.pfPassword.setText("");
		if(foodMain.currentPanelName.equals("userInfo")) {
			foodMain.currentPanelName = "userInfo";
			foodMain.userInfo.dataSetting();
			foodMain.card.show(foodMain.panChangePanel, "userInfo");
			return;
		}
		Object option;
		FOptionPane fOptionPane = foodMain.fOptionPane;
		UIManager.put("OptionPane.messageFont", new Font("���� ���", Font.PLAIN, 15));
		UIManager.put("OptionPane.buttonFont", new Font("���� ���", Font.PLAIN, 15));
		ImageIcon optionIcon = new ImageIcon(SwingFactory.class.getResource("/img/user/password.png"));
		fOptionPane.setMessage(foodMain.pPassword);
		fOptionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		fOptionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
		fOptionPane.setIcon(optionIcon);
		fOptionPane.setOptions(new String[]{"Ȯ��", "���"});
		fOptionPane.setInitialValue(foodMain.pPassword);
		foodMain.dgPassword.setVisible(true);
		
		option = fOptionPane.getValue();
		if(option.equals("Ȯ��")) {
			isCorrectPassword();
		}
	}
	public void isCorrectPassword() {
		if(foodMain.user.getPassword().equals(new String(foodMain.pfPassword.getPassword()))) {
			foodMain.dgPassword.setVisible(false);
			foodMain.currentPanelName = "userInfo";
			foodMain.userInfo.dataSetting();
			foodMain.card.show(foodMain.panChangePanel, "userInfo");
		} else {
			SwingFactory.getOptionPane("message", foodMain.dgPassword, "��й�ȣ ����", "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
	}

	// �ֹ���� �������� (�ֹ���� ��ư)
	public void orderList() {
		foodMain.basketMain.dataSetting();
		foodMain.setPanelName("basketMain");
		foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
		foodMain.basketMain.card.show(foodMain.basketMain.pChangePanel, "basket");
	}

	// ��ȿ�� �˻�
	public void validationCheck(KeyEvent e) {
		char keyValue = e.getKeyChar();
		if (!Character.isDigit(keyValue)) {
			e.consume();
			return;
		}
	}
	private void searchMenuNameImpl() {
		String foodName = foodMain.searchField.getText();
		if (foodName.isEmpty())
			JOptionPane.showMessageDialog(foodMain, "��ü �޴��� �˻��մϴ�", "", JOptionPane.OK_CANCEL_OPTION);
		List<FoodDto> list = menuDao.getMenubyName(foodName);
		if (list.size() != 0) {
			// foodMain.panChangePanel.remove(foodMain.userMenuView);
			foodMain.panChangePanel.add(new UserMenuView(list, this), "userMenuView");
			foodMain.setPanelName("userMenuView");
			foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
		} else {
			foodMain.setPanelName("noSearchMenuImg");
			foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
		}
	}

	public void logout() {
		int input = SwingFactory.getOptionPane("messageChoice", foodMain, "�α׾ƿ�", "�α׾ƿ� �Ͻðڽ��ϱ�?");
		if(input == 0) {
			User user = User.getInstance();
			user.logout();
			foodMain.card.show(foodMain.panChangePanel, "foodMainView");
			foodMain.currentPanelName = "foodMainView";
			foodMain.labName.setText("��");
			foodMain.setVisible(false);
			foodMain.loginMain.card.show(foodMain.loginMain.panMain, "panLogin");
			foodMain.loginMain.setVisible(true);
		}
	}

	
}