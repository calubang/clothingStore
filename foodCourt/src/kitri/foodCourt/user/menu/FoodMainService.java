package kitri.foodCourt.user.menu;

import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.*;

import kitri.foodCourt.dto.FoodDto;
import kitri.foodCourt.user.BasketDetail;
import kitri.foodCourt.user.main.FoodMain;
import kitri.foodCourt.user.menu.jdbc.DaoFactory;

public class FoodMainService {
	FoodMainController foodMainController;
	FoodMain foodMain;
	MenuDao menuDao = new DaoFactory().menuDao();
	ImageIcon icon = new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/basket.png"));

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
		foodMain.panChangePanel.add(new UserMenuDetailView(foodDto, this), "UserMenuDetailView");
		foodMain.setPanelName("UserMenuDetailView");
		foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
		System.out.println("ī�� ���̾ƿ� ����Ʈ�� : " + foodMain.card.toString());
	}

	// �������� (�������� ��ư)
	public void backMenu() {
		foodMain.setPanelName("UserMenuDetailView");
		foodMain.card.show(foodMain.panChangePanel, foodMain.getPanelName());
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
}