package shahi.Action.PayTracker.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.PayTracker.bean.DatatexPaymentTracker;
import shahi.Action.PayTracker.bean.PaymentSearch;
import shahi.Action.PayTracker.bean.PaymentTracker;
import shahi.Action.PayTracker.dao.PaymentTrackerDao;
import shahi.Action.database.ConnectionShahiHrisNew;

public class PaymentTrackerService {

   private Connection conn;
	public List<PaymentTracker>getAllPaymentsByCriteria(PaymentSearch search) throws SQLException{
		try{
			conn=new ConnectionShahiHrisNew().getConnection();
			PaymentTrackerDao dao=new PaymentTrackerDao(conn);
			return dao.loadAllPaymentsByCriteria(search);
		}catch(SQLException ex){
			System.out.println(ex.getLocalizedMessage());
			return null;
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
}
