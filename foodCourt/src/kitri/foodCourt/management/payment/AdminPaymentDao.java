package kitri.foodCourt.management.payment;

import java.sql.*;

import kitri.foodCourt.db.ConnectionMaker;
import kitri.foodCourt.db.DbFactory;

public class AdminPaymentDao {
	
	public AdminPaymentService service;
	private ConnectionMaker connectionMaker;
	
	public AdminPaymentDao(AdminPaymentService service) {
		this.service = service;
		connectionMaker = DbFactory.connectionMaker("oracle");
	}
	
	//��������
	public int delete(String paymentId) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = 
				"delete from fook_payment\r\n" + 
				"where \r\n" + 
				"    payment_id= ? \r\n";
		try {
			con = connectionMaker.makeConnection();
			con.setAutoCommit(false);
			
			System.out.println(paymentId);
			//���� detail ����
			if(deleteDetail(con, paymentId) > 0) {
				System.out.println(paymentId);
				//�����ϸ� payment ����
				ps = con.prepareStatement(sql);
				ps.setString(1, paymentId);
				
				result = ps.executeUpdate();
				if(result == 1) {
					con.commit();
					return result;
				}else {
					return result;
				}
			} else {
				return -1;
			}
		} catch (SQLException e){	
			e.printStackTrace();
		} finally {
			
			try {
				if(ps != null) {
					ps.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			if(con != null) {
				try {
					con.rollback();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return -1;
	}
	
	//���������� ����
	public int deleteDetail(Connection con, String paymentId) throws SQLException{
		PreparedStatement ps = null;
		String sql = 
				"delete from fook_payment_detail where payment_id= ? ";
		
		ps = con.prepareStatement(sql);
		ps.setString(1, paymentId);
		
		System.out.println(paymentId);
		int result = ps.executeUpdate();
		System.out.println(result);
		return result;
		
	}
	
	
}
