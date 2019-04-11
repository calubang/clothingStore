package kitri.foodCourt.user.menu;

import java.util.List;

import javax.swing.JOptionPane;

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

	// ���� �޴� ��� �������� (���θ޴�)
	public void clickMain() {
		foodMain.card.show(foodMain.panChangePanel, "foodMainView");
	}

	// �޴� ��� �������� (�ѽ�, �߽�, �Ͻ�, ���)
	public void searchCategory(String category) {
		List<FoodDto> list = menuDao.getMenubyCategory(Integer.parseInt(category));
//		foodMain.panChangePanel.remove(foodMain.userMenuView);
		foodMain.panChangePanel.add(new UserMenuView(list, this), "userMenuView");
		foodMain.card.show(foodMain.panChangePanel, "userMenuView");
	}

	// �ֹ���� �������� (�˻� ��ư)
	public void searchMenuName() {
		String foodName = foodMain.searchField.getText();
		List<FoodDto> list = menuDao.getMenubyName(foodName);
		if(list.size() != 0) {
//			foodMain.panChangePanel.remove(foodMain.userMenuView);
			foodMain.panChangePanel.add(new UserMenuView(list, this), "userMenuView");
			foodMain.card.show(foodMain.panChangePanel, "userMenuView");
		} else {
			foodMain.card.show(foodMain.panChangePanel, "noSearchMenuImg");
		}
	}

	// �޴� �� �������� (�޴� �̹��� ��ư)
	public void searchMenuDetail(String food_id) {
		FoodDto foodDto = menuDao.getMenubyId(food_id);
//		foodMain.panChangePanel.remove(foodMain.UserMenuDetailView);
		foodMain.panChangePanel.add(new UserMenuDetailView(foodDto, this), "UserMenuDetailView");
		foodMain.card.show(foodMain.panChangePanel, "UserMenuDetailView");
	}
	
	// �������� (�������� ��ư)
	public void backMenu() {
		foodMain.card.show(foodMain.panChangePanel, "userMenuView");
	}
	
	// �ֹ���� �߰� (��ٱ��� �߰� ��ư)
	public void addMenuInbasket() {
		int result;
		result = JOptionPane.showConfirmDialog(foodMain.UserMenuDetailView, "�ֹ���Ͽ� �߰��Ͻðڽ��ϱ�?", "�ֹ� �޴� �߰�", 
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if(result == 0) { // Ȯ���� ������ ��
			// ��ٱ��� List�� ���� �޴��� �߰�
			JOptionPane.showMessageDialog(foodMain.UserMenuDetailView, "�߰��Ǿ����ϴ�");
			backMenu();
		}
	}
	
	// ���� �������� (���� ���� ��ư)
	public void userInfo() {
	}

	// �ֹ���� �������� (�ֹ���� ��ư)
	public void orderList() {
		foodMain.basketMain.dataSetting();
		foodMain.card.show(foodMain.panChangePanel, "basketMain");
		foodMain.basketMain.card.show(foodMain.basketMain.pChangePanel, "basket");
	}

}
