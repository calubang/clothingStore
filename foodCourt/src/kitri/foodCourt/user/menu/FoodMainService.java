package kitri.foodCourt.user.menu;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
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
		foodMain.card.show(foodMain.panChangePanel, "mainMenu");
	}

	public void searchCategory(String category) {
		// �޴� ��� �������� (�ѽ�, �߽�, �Ͻ�, ���)
		System.out.println("menuList");
		List<FoodDto> list = menuDao.getMenubyCategory(Integer.parseInt(category));
//		JPanel userMenuList = new UserMenuView(list, this);
//		userMenuList.setBounds(160, 118, 1012, 634);
		foodMain.panChangePanel.add(new UserMenuView(list, this), "userMenuList");
		foodMain.card.show(foodMain.panChangePanel, "userMenuList");
	}

	public void searchMenuName() {
		// �ֹ���� �������� (�˻� ��ư)
		System.out.println("menuNameList");
		String foodName = foodMain.searchField.getText();
		List<FoodDto> list = menuDao.getMenubyName(foodName);
//		JPanel userMenuList = new UserMenuView(list, this);
		foodMain.panChangePanel.add(new UserMenuView(list, this), "menuNameList");
		foodMain.card.show(foodMain.panChangePanel, "menuNameList");
	}

	public void searchMenuDetail(String food_id) {
		// �޴� �� �������� (�޴� �̹��� ��ư)
		System.out.println("menuDetail");
		FoodDto foodDto = menuDao.getMenubyId(food_id);
//		JPanel userMenuList = new UserMenuDetailView(foodDto);
		foodMain.panChangePanel.add(new UserMenuDetailView(foodDto), "menuNameList");
		foodMain.card.show(foodMain.panChangePanel, "menuNameList");
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
