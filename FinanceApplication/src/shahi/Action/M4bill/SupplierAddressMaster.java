package shahi.Action.M4bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import java.util.ArrayList;
import shahi.Action.M4bill.Beans.SupplierAddressBean;
import shahi.Action.database.connectiondb2;

public class SupplierAddressMaster {
	public static SupplierAddressBean getSupplierAddress(String CONO,String SUNO,String ADTE) throws SQLException{
		SupplierAddressBean supplierAddressBean = new SupplierAddressBean();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet resultSet = null;
		try{
			conn = new connectiondb2().getConnection();
			stat = conn.prepareStatement("SELECT SAADID,SASUNM,SAADR1,SAADR2,SAADR3,SAADR4,SATOWN,SAECAR,SAPONO,SACSCD,SASTDT SARGDT FROM CIDADR WHERE SACONO=? AND SASUNO=? AND SAADTE=?");
			stat.setString(1, CONO);
			stat.setString(2, SUNO);
			stat.setString(3, ADTE);
			resultSet = stat.executeQuery();
			while(resultSet.next()){
				supplierAddressBean.setADID(resultSet.getString("SAADID"));
				supplierAddressBean.setCONM(resultSet.getString("SASUNM"));
				supplierAddressBean.setADR1(resultSet.getString("SAADR1"));
				supplierAddressBean.setADR2(resultSet.getString("SAADR2"));
				supplierAddressBean.setADR3(resultSet.getString("SAADR3"));
				supplierAddressBean.setADR4(resultSet.getString("SAADR4"));
				supplierAddressBean.setTOWN(resultSet.getString("SATOWN"));
				supplierAddressBean.setECAR(resultSet.getString("SAECAR"));
				supplierAddressBean.setPONO(resultSet.getString("SAPONO"));
				supplierAddressBean.setCSCD(resultSet.getString("SACSCD"));
                                supplierAddressBean.setRGDT(resultSet.getString("SARGDT"));
			}
					
		}
		catch(SQLException se){
			System.out.println("com.shahi.SupplierAddressMaster "+se);
		}
		finally{
                        if(resultSet!=null) resultSet.close();
			if(stat!=null) stat.close();
                        if(conn!=null) conn.close();
		}
		return supplierAddressBean;
	}
        
        
        public static List<SupplierAddressBean> getSupplierAddressList(String CONO,String SUNO,String ADTE) throws SQLException{
		List<SupplierAddressBean> supplierAddressBeans = new ArrayList<SupplierAddressBean>();
		PreparedStatement stat = null;
		ResultSet resultSet = null;
                Connection conn = null;
		try{
                        conn = new connectiondb2().getConnection();
			stat = conn.prepareStatement("SELECT SAADID,SASUNM,SAADR1,SAADR2,SAADR3,SAADR4,SATOWN,SAECAR,SAPONO,SACSCD,SASTDT SARGDT FROM CIDADR WHERE SACONO=? AND SASUNO=? AND SAADTE=?");
			stat.setString(1, CONO);
			stat.setString(2, SUNO);
			stat.setString(3, ADTE);
			resultSet = stat.executeQuery();
			while(resultSet.next()){
                            SupplierAddressBean supplierAddressBean = new SupplierAddressBean();
				supplierAddressBean.setADID(resultSet.getString("SAADID"));
				supplierAddressBean.setCONM(resultSet.getString("SASUNM"));
				supplierAddressBean.setADR1(resultSet.getString("SAADR1"));
				supplierAddressBean.setADR2(resultSet.getString("SAADR2"));
				supplierAddressBean.setADR3(resultSet.getString("SAADR3"));
				supplierAddressBean.setADR4(resultSet.getString("SAADR4"));
				supplierAddressBean.setTOWN(resultSet.getString("SATOWN"));
				supplierAddressBean.setECAR(resultSet.getString("SAECAR"));
				supplierAddressBean.setPONO(resultSet.getString("SAPONO"));
				supplierAddressBean.setCSCD(resultSet.getString("SACSCD"));
                                supplierAddressBean.setRGDT(resultSet.getString("SARGDT"));
                                supplierAddressBeans.add(supplierAddressBean);
			}
					
		}
		catch(SQLException se){
			System.out.println("com.shahi.SupplierAddressMaster "+se);
		}
		finally{
			if(stat!=null) stat.close();
			if(resultSet!=null) resultSet.close();
		}
		return supplierAddressBeans;
	}
	
}
