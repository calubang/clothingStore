package kitri.foodCourt.management.regit;

import kitri.foodCourt.management.regit.ManagermentDao;;


public class DbFactory {
	public ConnectionMaker connectionMaker() { 
		return new OracleConnectionMaker(); // �п����� ��� �����Ҷ�

	}

	public ManagermentDao managermentDao() {
		ConnectionMaker connectionMaker = connectionMaker();
		ManagermentDao managermentDao = new ManagermentDao(connectionMaker);
		return managermentDao;
	}
}