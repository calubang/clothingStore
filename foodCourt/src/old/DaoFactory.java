package old;

import kitri.foodCourt.user.dao.MenuDao;

public class DaoFactory {
	public ConnectionMaker connectionMaker() { 
		return new OracleConnectionMaker(); // �п����� ��� �����Ҷ�
//		return new HomeOracleConnectionMaker(); ������ ��� �׽�Ʈ �Ҷ�
	}
	public MenuDao menuDao() {
		ConnectionMaker connectionMaker = connectionMaker();
		MenuDao userDao = new MenuDao();
		return userDao;
	}
}