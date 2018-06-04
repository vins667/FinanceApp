package shahi.Action.MvxExp.PRE.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shahi.Action.MvxExp.PRE.Beans.BuyerDocsMaster;
import shahi.Action.MvxExp.PRE.Beans.CodeList;
import shahi.Action.database.connectiondb2;

public class BuyerDocMasterDao {
  private Connection conndb2 = null;
  
  public BuyerDocMasterDao(){
	  conndb2=new connectiondb2().getConnection();
  }
  
  public List<CodeList>loadAllBuyerGroups() throws SQLException {
	  PreparedStatement stmt=null;
	  ResultSet rs=null;
	  List<CodeList>list=new ArrayList<>();
	  String sql="select OKCUCL,OKCUNM from OCUSMA";
	  try {
		  stmt=conndb2.prepareStatement(sql);
		 rs=stmt.executeQuery();
		 while(rs.next()){
			  list.add(new CodeList(rs.getString(0),rs.getString(1)));
		 }
		 return list;
	} catch (SQLException e) {
		System.out.println(e.getLocalizedMessage());
		return null;
	}finally{
		if(stmt!=null){
			stmt.close();
		}
		if(rs!=null){
			rs.close();
		}
		
		
	}
  }
  public List<CodeList>loadAllBuyerCountry() throws SQLException {
	  PreparedStatement stmt=null;
	  ResultSet rs=null;
	  List<CodeList>list=new ArrayList<>();
	  String sql="select CTSTKY,CTTX40 from CSYTAB where ctcono=111 and ctstco='CSCD'";
	  try {
		  stmt=conndb2.prepareStatement(sql);
		 rs=stmt.executeQuery();
		 while(rs.next()){
			  list.add(new CodeList(rs.getString(0),rs.getString(1)));
		 }
		 return list;
	} catch (SQLException e) {
		System.out.println(e.getLocalizedMessage());
		return null;
	}finally{
		if(stmt!=null){
			stmt.close();
		}
		if(rs!=null){
			rs.close();
		}
		
		
	}
  }
  
  public void closeConnection() throws SQLException{
	  if(conndb2!=null){
		  conndb2.close();
	  }
  }
  
  public List<BuyerDocsMaster>loadAllBuyerDocs(String buyerGroup,String destCountry) throws SQLException{
	  PreparedStatement stmt=null;
	  ResultSet rs=null;
	  List<BuyerDocsMaster>list=new ArrayList<>();
 	  String sql="select * from EI_BUYER_DOCS_SET where buyer_grp=? and DEST_CNTRY=?";
 	 try {
		  stmt=conndb2.prepareStatement(sql);
		  stmt.setString(1, buyerGroup.toString());
		  stmt.setString(2, destCountry.toString());
		 rs=stmt.executeQuery();
		 while(rs.next()){
			  list.add(new BuyerDocsMaster(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(5)),rs.getString(6),rs.getString(7)));
		 }
		 return list;
	} catch (SQLException e) {
		System.out.println(e.getLocalizedMessage());
		return null;
	}finally{
		if(stmt!=null){
			stmt.close();
		}
		if(rs!=null){
			rs.close();
		}
		
  }
}
  
}
