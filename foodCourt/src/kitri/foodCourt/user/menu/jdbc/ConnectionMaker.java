package kitri.foodCourt.user.menu.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker { //�������̽��� ����
	public Connection makeConnection();
}
