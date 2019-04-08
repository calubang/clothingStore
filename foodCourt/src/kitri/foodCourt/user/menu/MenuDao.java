package kitri.foodCourt.user.menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import kitri.foodCourt.user.menu.jdbc.ConnectionMaker;

public class MenuDao {

	private ConnectionMaker connectionMaker;
	public MenuDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	//ī�װ� �޴� Ŭ���� ���� �޴� ������
	public List<FoodDto> getMenubyCategory(int categori_id) {
		List<FoodDto> list = new ArrayList<FoodDto>();
		Connection c = null; 
		PreparedStatement ps = null; 
		ResultSet rs = null;
		try {
			c = connectionMaker.makeConnection();
			ps = c.prepareStatement("select * from fook_food where category_id = ?");
			ps.setInt(1, categori_id);
			rs = ps.executeQuery();
			//���� �̸�, �̹����ּ�, ���̵� (�޴� ����Ʈ�� �������� ������)
			while (rs.next()) {
				FoodDto food = new FoodDto();
				food.setFoodName("food_name");
				food.setImageAddress("image_address");
				food.setFoodId("food_id");
				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
	// �˻����� �޴� ������
	public List<FoodDto> getMenubyName(String foodName) {
		List<FoodDto> list = new ArrayList<FoodDto>();
		Connection c = null; 
		PreparedStatement ps = null; 
		ResultSet rs = null;
		try {
			c = connectionMaker.makeConnection();
			ps = c.prepareStatement("select * from fook_food where food_name = ?");
			ps.setString(1, foodName);
			rs = ps.executeQuery();
			//���� �̸�, �̹����ּ�, ���̵� (�޴� ����Ʈ�� �������� ������)
			while (rs.next()) {
				FoodDto food = new FoodDto();
				food.setFoodName("food_name");
				food.setImageAddress("image_address");
				food.setFoodId("food_id");
				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
	
}