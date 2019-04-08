package kitri.foodCourt.user.menu;

import java.util.List;

import javax.swing.JButton;

import kitri.foodCourt.user.menu.jdbc.DaoFactory;

public class FoodMainService {
	FoodMainController foodMainController = null;
	FoodMain foodMain = null;
	List<FoodDto> foodList = null;
	MenuDao foodDao = new DaoFactory().menuDao();

	public FoodMainService(FoodMainController foodMainController) {
		this.foodMainController = foodMainController;
		this.foodMain = foodMainController.foodMain;
	}

	public void clickMain() {
		// ���� �޴� ��� �������� (���θ޴�)
		System.out.println("mainMenu");
		foodMain.card.show(foodMain.panChangePanel, "mainMenu");
	}

	public void searchMenu(JButton button) {
		// �޴� ��� �������� (�ѽ�, �߽�, �Ͻ�, ���)
		System.out.println("menuList");
		foodList = foodDao.getMenubyCategory(Integer.parseInt(button.getName()));
		if (Integer.parseInt(button.getName()) == 1) {
			foodMain.userMenuList.setMenu(2, "/kitri/foodCourt/user/menu/menuImage/junjoobibimbab.jpg", foodList);
			foodMain.card.show(foodMain.panChangePanel, "menuList");
		} else {
			foodMain.userMenuList.setMenu(17, "/kitri/foodCourt/user/menu/menuImage/junjoobibimbab.jpg", foodList);
			foodMain.card.show(foodMain.panChangePanel, "menuList");
		}

	}

	public void searchMenuName() {
		// �ֹ���� �������� (�˻� ��ư)
		System.out.println("menuNameList");
		String foodName = foodMain.textField.getText();
		foodList = foodDao.getMenubyName(foodName);
//		foodMain.userMenuList.setMenu(27, foodList);
		foodMain.card.show(foodMain.panChangePanel, "menuList");
	}

	public void userInfo() {
		// ���� �������� (���� ���� ��ư)
		// ������ �ӽ÷� �޴� ������ ����
		System.out.println("menuList");
		foodMain.card.show(foodMain.panChangePanel, "menuDetail");
	}

	public void orderList() {
		// �ֹ���� �������� (�ֹ���� ��ư)

	}

}
