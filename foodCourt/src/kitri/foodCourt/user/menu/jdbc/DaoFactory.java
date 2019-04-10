package kitri.foodCourt.user.menu.jdbc;

import kitri.foodCourt.user.menu.MenuDao;

public class DaoFactory {
	public ConnectionMaker connectionMaker() { 
		return new OracleConnectionMaker(); // �п����� ��� �����Ҷ�
//		return new HomeOracleConnectionMaker(); ������ ��� �׽�Ʈ �Ҷ�
	}
	public MenuDao menuDao() {
		ConnectionMaker connectionMaker = connectionMaker();
		MenuDao userDao = new MenuDao(connectionMaker);
		return userDao;
	}
}