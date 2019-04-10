package kitri.foodCourt.user.menu;

import java.util.List;
import kitri.foodCourt.user.main.FoodMain;
import kitri.foodCourt.user.menu.jdbc.DaoFactory;

public class FoodMainService {
	FoodMainController foodMainController;
	FoodMain foodMain;
	MenuDao menuDao = new DaoFactory().menuDao();
	
	public FoodMainService(FoodMainController foodMainController) {
		this.foodMainController = foodMainController;
		this.foodMain = foodMainController.foodMain;
	}

	public void clickMain() {
		// ���� �޴� ��� �������� (���θ޴�)
		System.out.println("mainMenu");
		foodMain.card.show(foodMain.panChangePanel, "foodMainView");
	}

	public void searchCategory(String category) {
		// �޴� ��� �������� (�ѽ�, �߽�, �Ͻ�, ���)
		System.out.println("menuList");
		List<FoodDto> list = menuDao.getMenubyCategory(Integer.parseInt(category));
		foodMain.panChangePanel.remove(foodMain.userMenuView);
		foodMain.panChangePanel.add(new UserMenuView(list, this), "userMenuView");
		foodMain.card.show(foodMain.panChangePanel, "userMenuView");
	}

	public void searchMenuName() {
		// �ֹ���� �������� (�˻� ��ư)
		System.out.println("menuNameList");
		String foodName = foodMain.searchField.getText();
		List<FoodDto> list = menuDao.getMenubyName(foodName);
		foodMain.panChangePanel.remove(foodMain.userMenuView);
		foodMain.panChangePanel.add(new UserMenuView(list, this), "userMenuView");
		foodMain.card.show(foodMain.panChangePanel, "userMenuView");
	}

	public void searchMenuDetail(String food_id) {
		// �޴� �� �������� (�޴� �̹��� ��ư)
		System.out.println("menuDetail");
		FoodDto foodDto = menuDao.getMenubyId(food_id);
		foodMain.panChangePanel.remove(foodMain.UserMenuDetailView);
		foodMain.panChangePanel.add(new UserMenuDetailView(foodDto, this), "UserMenuDetailView");
		foodMain.card.show(foodMain.panChangePanel, "UserMenuDetailView");
	}
	
	public void userInfo() {
		// ���� �������� (���� ���� ��ư)
	}

	public void orderList() {
		// �ֹ���� �������� (�ֹ���� ��ư)
	}

	public void backMenu() {
		// ��������
		System.out.println("��������");
		foodMain.card.show(foodMain.panChangePanel, "userMenuView");
	}
}
