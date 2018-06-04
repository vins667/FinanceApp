/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
  
import shahi.Action.MvxExp.PRE.Beans.AgentBean;
import shahi.Action.MvxExp.PRE.Beans.BuyerBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;

/** 
 *
 * @author Ravindra
 */
public class BillOfSalesMasterDao {
    
    private PreparedStatement stat;
    private ResultSet result;
    private PreparedStatement statement1;
    private ResultSet result1;
    private PreparedStatement statement2;
    private ResultSet resultSet;
    //private connection dbConn=new connection();
    Connection conn=null;
    
     public String getBuyerAddress(String para)
    {
         String address="";
         try{
            if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
                }
            else{
             para="%";
         }
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("Select distinct OKCUNM BUYER,trim(OKCUA1)||','||trim(OKCUA2)||','||trim(OKCUA3)||','||trim(OKCUA4)  BUYER_ADDRESS, OKCUNO BUYER_CODE,OKADID BUYER_ID FROM OCUSMA WHERE okcono=111 and (OKCUNO like ? or OKCUNM  like ?) order by 1 ");
            stat.setString(1, para);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next())
            {
               address+=resultSet.getString("BUYER_ADDRESS")+"#"+resultSet.getString("BUYER")+"#"+resultSet.getString("BUYER_CODE");
               
               //buyerlist.add(new BuyerBean(resultSet.getString("BUYER"),resultSet.getString("BUYER_ADDRESS"),resultSet.getString("BUYER_CODE"),resultSet.getString("BUYER_ID")));
            }
         }    
        catch(SQLException se){System.out.println("BuyerAdd"+se);}
        catch(Exception e){System.out.println("BuyerAdd "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();        
                    if(stat!=null)stat.close();
                    conn.close(); 
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return address;
    }
    
     public String getUnitAddress(String para){
         
        String address="";
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
         try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select oaadk1 Location,oaadr4 Location_Name,oaadr1||',   '||oaadr2 Address from ciaddr  where oaadth='4' and (oaadk1 like ? or oaadr4 like ?)");
            stat.setString(1, para);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                 address+=resultSet.getString("Location")+"#"+resultSet.getString("Location_Name")+"#"+resultSet.getString("Address");
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                     conn.close();                
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return address;
    }
    

public String getVendorAddress(String para){
    
       String address="";
      
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        List<AgentBean> agentlist = new ArrayList<AgentBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("Select DISTINCT sasunm AGENT_NAME ,trim(SAADR1)||trim(SAADR2)||trim(SAADR3)||trim(SAADR4) AGENT_ADDRESS, SASUNO AGENT_CODE ,SAADID VEND_ID  from cidadr where saadte=1 and sacono=111 and (sasuno like ? or sasunm like ?) order by sasunm");
            stat.setString(1, para);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                   address+=resultSet.getString("AGENT_NAME")+"#"+resultSet.getString("AGENT_ADDRESS")+"#"+resultSet.getString("AGENT_CODE")+"#"+resultSet.getString("VEND_ID");
            }
        }
        catch(SQLException se){System.out.println("AgentBean"+se);}
        catch(Exception e){System.out.println("AgentBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                     conn.close();                
                   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return address;
    }

public List<UnitBean> getBuyeraddList(String para){
       
	 if(para!=null && para.length()>0){
         para=para.toUpperCase().trim()+"%";
     }
     else{
         para="%";
     }
	 
        List<UnitBean> unitlist = new ArrayList<UnitBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from OCUSAD where OPCONO =111 and OPADRT ='1' and (OPADID like ? or OPCUNO like ? or OPCUNM like ?) order by OPCUNO,OPADID");
            stat.setString(1, para);
            stat.setString(2, para);
            stat.setString(3, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                String address="";
                if(resultSet.getString("OPCUA1")!=null && resultSet.getString("OPCUA1").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA1").trim() +", ";
                }
                 if(resultSet.getString("OPCUA2")!=null && resultSet.getString("OPCUA2").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA2").trim()+", ";
                }
                  if(resultSet.getString("OPCUA3")!=null && resultSet.getString("OPCUA3").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA3").trim()+", ";
                }
                   if(resultSet.getString("OPCUA4")!=null && resultSet.getString("OPCUA4").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA4").trim();
                }
                unitlist.add(new UnitBean(resultSet.getString("OPADID").trim(),resultSet.getString("OPCUNM").trim(),address));
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                                        
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }

public String getBuyeraddListAjax(String para){
	
	if(para!=null && para.length()>0){
        para=para.toUpperCase().trim()+"%";
    }
    else{
        para="%";
    }
        String unitlist ="";
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from OCUSAD where OPCONO =111 and OPADRT ='1' and (OPADID like ? or OPCUNO like ? or OPCUNM like ?) order by OPCUNO,OPADID");
            stat.setString(1, para);
            stat.setString(2, para);
            stat.setString(3, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                String address="";
                if(resultSet.getString("OPCUA1")!=null && resultSet.getString("OPCUA1").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA1").trim() +", ";
                }
                 if(resultSet.getString("OPCUA2")!=null && resultSet.getString("OPCUA2").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA2").trim()+", ";
                }
                  if(resultSet.getString("OPCUA3")!=null && resultSet.getString("OPCUA3").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA3").trim()+", ";
                }
                   if(resultSet.getString("OPCUA4")!=null && resultSet.getString("OPCUA4").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA4").trim();
                }
                   unitlist+=resultSet.getString("OPADID").trim()+"#"+resultSet.getString("OPCUNM").trim()+"#"+address;
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                                        
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }


public List<BuyerBean> getBuyeraddListbyName(String para){
       
    
        List<BuyerBean> unitlist = new ArrayList<BuyerBean>();
        if(para!=null && para.length()>0)
            {
        try{
            conn=new connectiondb2().getConnection();
            
                String sqlstr=" and (OPADID='"+para+"' or opcuno like '"+para.toUpperCase()+"%' or OPCUNM like '"+para.toUpperCase()+"%')";
            stat=conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from OCUSAD where OPCONO =111 and OPADRT ='1' "+sqlstr+" order by OPCUNO,OPADID");
            
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                String address="";
                if(resultSet.getString("OPCUA1")!=null && resultSet.getString("OPCUA1").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA1").trim() +", ";
                }
                 if(resultSet.getString("OPCUA2")!=null && resultSet.getString("OPCUA2").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA2").trim()+", ";
                }
                  if(resultSet.getString("OPCUA3")!=null && resultSet.getString("OPCUA3").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA3").trim()+", ";
                }
                   if(resultSet.getString("OPCUA4")!=null && resultSet.getString("OPCUA4").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA4").trim();
                }
                //String BUYER, String BUYER_ADDRESS,String BUYER_CODE,String BUYER_ID
                
                unitlist.add(new BuyerBean(resultSet.getString("OPCUNM").trim(),address,resultSet.getString("OPCUNO").trim(),resultSet.getString("OPADID").trim()));
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                        conn.close();                
                    
                   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
            }
        return unitlist;
    }



public String getBuyeraddListbyNameAjax(String para,String para1){
       
    
        String unitlist = "";
        if(para!=null && para.length()>0)
            {
        try{
            conn=new connectiondb2().getConnection();
            String  sqlstr="";
            
             //System.out.println("para"+para+" para1"+para1);
            
            if(para1!=null && para1.length()>0)
            {
            	sqlstr=" and OPADID='"+para1+"' and (opcuno like '"+para.toUpperCase()+"%' or OPCUNM like '"+para.toUpperCase()+"%')";
            }
            else
            {
                sqlstr=" and (OPADID='"+para+"' or opcuno like '"+para.toUpperCase()+"%' or OPCUNM like '"+para.toUpperCase()+"%')";
            }
            stat=conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from OCUSAD where OPCONO =111 and OPADRT ='1' "+sqlstr+" order by OPCUNO,OPADID");
            
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                String address="";
                if(resultSet.getString("OPCUA1")!=null && resultSet.getString("OPCUA1").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA1").trim() +", ";
                }
                 if(resultSet.getString("OPCUA2")!=null && resultSet.getString("OPCUA2").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA2").trim()+", ";
                }
                  if(resultSet.getString("OPCUA3")!=null && resultSet.getString("OPCUA3").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA3").trim()+", ";
                }
                   if(resultSet.getString("OPCUA4")!=null && resultSet.getString("OPCUA4").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA4").trim();
                }
                //String BUYER, String BUYER_ADDRESS,String BUYER_CODE,String BUYER_ID
                
                unitlist+=resultSet.getString("OPCUNM").trim()+"#"+address+"#"+resultSet.getString("OPCUNO").trim()+"#"+resultSet.getString("OPADID").trim();
            }
        }
        catch(SQLException se){System.out.println("UnitBeanAJAX"+se);}
        catch(Exception e){System.out.println("UnitBeanAJAX "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                        conn.close();                
                    
                   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
            }
        return unitlist;
    }




 public List<UnitBean> getUnitListByName(String para){
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        List<UnitBean> unitlist = new ArrayList<UnitBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select oaadK1 code,oaconm name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM ciaddr where oaadth='4' and (oaadk1 like ? or oaconm like ?) order by 2");
            stat.setString(1, para);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.add(new UnitBean(resultSet.getString("code"),resultSet.getString("name"),resultSet.getString("UADDR")));
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                    
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
 
 public String getUnitListByNameAjax(String para){
     if(para!=null && para.length()>0){
         para=para.toUpperCase()+"%";
     }
     else{
         para="%";
     }
     
    String unitlist = "";
     try{
         conn=new connectiondb2().getConnection();
         stat=conn.prepareStatement("select oaadK1 code,oaconm name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM ciaddr where oaadth='4' and (oaadk1 like ? or oaconm like ?) order by 2");
         stat.setString(1, para);
         stat.setString(2, para);
         resultSet = stat.executeQuery();
         while(resultSet.next()){
             unitlist+=resultSet.getString("code")+"#"+resultSet.getString("name")+"#"+resultSet.getString("UADDR");
         }
     }
     catch(SQLException se){System.out.println("UnitBean"+se);}
     catch(Exception e){System.out.println("UnitBean "+e);}
     finally{
         if(conn!=null)
             try {
                 if(resultSet!=null)resultSet.close();
                 if(stat!=null)stat.close();
                 conn.close();                
                 
                 
             } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
     }
     return unitlist;
 }


 public UnitBean getUnitByName(String para){
       
        UnitBean unitlist = new UnitBean();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select oaadK1 code,oaconm name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM ciaddr where oaadth='4' and oaadK1=?");
            stat.setString(1, para);
            
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.setUNIT_DESC(resultSet.getString("name"));
                unitlist.setUNIT_ADDRESS(resultSet.getString("UADDR"));
                unitlist.setUNIT_CODE(resultSet.getString("code"));
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                     conn.close();                
                    
                   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
 //ShipMode
 
  //destination / PORT
 public List<UnitBean> getCsytabByCode(String para,String type){
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        
        //and ctstco='CSCD'
        List<UnitBean> unitlist = new ArrayList<UnitBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select cttx15,trim(ctstky) ctstky from m3fdbprd.csytab  where ctcono=111 and ctstco=? and (ctstky like ? or cttx40 like ?)  order by 1");
            stat.setString(1, type);
            stat.setString(2, para);
            stat.setString(3, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.add(new UnitBean(resultSet.getString("ctstky"),resultSet.getString("ctstky"),""));
            }
        } 
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                    conn.close();                
                    
                   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
 
 public String getCsytabByCodeAjax(String para,String type){
     if(para!=null && para.length()>0){
         para=para.toUpperCase()+"%";
     }
     else{
         para="%";
     }
     
     //and ctstco='CSCD'
     String unitlist = "";
     try{
         conn=new connectiondb2().getConnection();
         stat=conn.prepareStatement("select cttx15,trim(ctstky) ctstky from m3fdbprd.csytab  where ctcono=111 and ctstco=? and (ctstky like ? or cttx40 like ?)  order by 1");
         stat.setString(1, type);
         stat.setString(2, para);
         stat.setString(3, para);
         resultSet = stat.executeQuery();
         while(resultSet.next()){
             unitlist+=resultSet.getString("ctstky")+"#"+resultSet.getString("ctstky");
         }
     } 
     catch(SQLException se){System.out.println("UnitBean"+se);}
     catch(Exception e){System.out.println("UnitBean "+e);}
     finally{
         if(conn!=null)
             try {
                  if(resultSet!=null)resultSet.close();
                  if(stat!=null)stat.close();
                 conn.close();                
                 
                
             } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
     }
     return unitlist;
 }
 
 
 //destination / PORT
 public List<UnitBean> getCsytabByName(String para,String type){
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        
        //and ctstco='CSCD'
        List<UnitBean> unitlist = new ArrayList<UnitBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select cttx40,ctstky from m3fdbprd.csytab  where ctcono=111 and ctstco=? and (ctstky like ? or cttx40 like ?)  order by 1");
            stat.setString(1, type);
            stat.setString(2, para);
            stat.setString(3, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.add(new UnitBean(resultSet.getString("ctstky"),resultSet.getString("cttx40"),""));
            }
        } 
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                    conn.close();                
                    
                   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
 
 public String getCsytabByNameAjax(String para,String type){
     if(para!=null && para.length()>0){
         para=para.toUpperCase()+"%";
     }
     else{
         para="%";
     }
     
     //and ctstco='CSCD'
     String unitlist ="";
     try{
         conn=new connectiondb2().getConnection();
         stat=conn.prepareStatement("select cttx40,ctstky from m3fdbprd.csytab  where ctcono=111 and ctstco=? and (ctstky like ? or cttx40 like ?)  order by 1");
         stat.setString(1, type);
         stat.setString(2, para);
         stat.setString(3, para);
         resultSet = stat.executeQuery();
         while(resultSet.next()){
             unitlist+=resultSet.getString("ctstky")+"#"+resultSet.getString("cttx40");
         }
     } 
     catch(SQLException se){System.out.println("getCsytabByNameAjax"+se);}
     catch(Exception e){System.out.println("getCsytabByNameAjax "+e);}
     finally{
         if(conn!=null)
             try {
                  if(resultSet!=null)resultSet.close();
                  if(stat!=null)stat.close();
                 conn.close();                
                
             } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
     }
     return unitlist;
 }

 
 public UnitBean getCsytabBeanByName(String para,String type){
        
         
        //and ctstco='CSCD'
       UnitBean unitlist = new UnitBean();
        try{
            conn=new connectiondb2().getConnection();
            
            stat=conn.prepareStatement("select cttx40,ctstky from m3fdbprd.csytab  where ctcono=111 and ctstco=? and ctstky=?");
            stat.setString(1, type);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.setUNIT_CODE(resultSet.getString("ctstky"));
                unitlist.setUNIT_DESC(resultSet.getString("cttx40"));
               
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                     if(stat!=null)stat.close();
                        conn.close();                
                    
                   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
 
 
  public List<UnitBean> getCFS(String para,String type){
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        
        //and ctstco='CSCD'
        List<UnitBean> unitlist = new ArrayList<UnitBean>();
        try{
            conn=new connection().getConnection();
            stat=conn.prepareStatement("select type_Desc,type_code from ei_grup_type_dtls where grup_type_code=?  and (type_Desc like ? or type_code like ?) order by 1 ");
            stat.setString(1, type);
            stat.setString(2, para);
            stat.setString(3, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.add(new UnitBean(resultSet.getString("type_code"),resultSet.getString("type_Desc"),""));
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                    
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
  
  public String getCFSAjax(String para,String type){
      if(para!=null && para.length()>0){
          para=para.toUpperCase()+"%";
      }
      else{
          para="%";
      }
      
      //and ctstco='CSCD'
    String unitlist = "";
      try{
          conn=new connection().getConnection();
          stat=conn.prepareStatement("select type_Desc,type_code from ei_grup_type_dtls where grup_type_code=?  and (type_Desc like ? or type_code like ?) order by 1 ");
          stat.setString(1, type);
          stat.setString(2, para);
          stat.setString(3, para);
          resultSet = stat.executeQuery();
          while(resultSet.next()){
              unitlist+=resultSet.getString("type_code")+"#"+resultSet.getString("type_Desc");
          }
      }
      catch(SQLException se){System.out.println("UnitBean"+se);}
      catch(Exception e){System.out.println("UnitBean "+e);}
      finally{
          if(conn!=null)
              try {
                  if(resultSet!=null)resultSet.close();
                  if(stat!=null)stat.close();
                  conn.close();                
                  
                  
              } catch (SQLException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
      }
      return unitlist;
  }

  
  
  public UnitBean getCFSBean(String para,String type){
        
        UnitBean unitlist = new UnitBean();
        try{
            conn=new connection().getConnection();
            stat=conn.prepareStatement("select type_Desc,type_code from ei_grup_type_dtls where grup_type_code=?  and type_code=? order by 1 ");
            stat.setString(1, type);
            stat.setString(2, para);
           
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.setUNIT_CODE(resultSet.getString("type_code"));
                unitlist.setUNIT_DESC(resultSet.getString("type_Desc"));
              
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                    
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
  
 
public UnitBean getBuyeraddress(String para1,String para2){
       
    
        UnitBean unitlist = new UnitBean();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select OPADID, OPCUNM, OPCUNO,OPCUA1,OPCUA2,OPCUA3,OPCUA4 from OCUSAD where OPCONO =111 and OPADRT ='1' and opcuno=? and OPADID=? order by OPCUNO,OPADID");
            stat.setString(1, para1);
            stat.setString(2, para2);
            resultSet = stat.executeQuery();
            if(resultSet.next()){
                String address="";
                if(resultSet.getString("OPCUA1")!=null && resultSet.getString("OPCUA1").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA1").trim() +", ";
                }
                 if(resultSet.getString("OPCUA2")!=null && resultSet.getString("OPCUA2").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA2").trim()+", ";
                }
                  if(resultSet.getString("OPCUA3")!=null && resultSet.getString("OPCUA3").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA3").trim()+", ";
                }
                   if(resultSet.getString("OPCUA4")!=null && resultSet.getString("OPCUA4").trim().length()>0)
                {
                address=address+resultSet.getString("OPCUA4").trim();
                }
                unitlist.setUNIT_CODE(resultSet.getString("OPADID"));
                unitlist.setUNIT_DESC(resultSet.getString("OPCUNM"));
                unitlist.setUNIT_ADDRESS(address);
                //

         }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                                        
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }

    public List<UnitBean> getUnitList(String para){
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        List<UnitBean> unitlist = new ArrayList<UnitBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("select oaadk1 Location,oaadr4 Location_Name,oaadr1||',   '||oaadr2 Address from ciaddr  where oaadth='4' and (oaadk1 like ? or oaadr4 like ?)");
            stat.setString(1, para);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                unitlist.add(new UnitBean(resultSet.getString("Location"),resultSet.getString("Location_Name"),resultSet.getString("Address")));
            }
        }
        catch(SQLException se){System.out.println("UnitBean"+se);}
        catch(Exception e){System.out.println("UnitBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                             
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return unitlist;
    }
 
public List<AgentBean> getAgentList(String para){
    
      
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        List<AgentBean> agentlist = new ArrayList<AgentBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("Select DISTINCT sasunm AGENT_NAME ,trim(SAADR1)||trim(SAADR2)||trim(SAADR3)||trim(SAADR4) AGENT_ADDRESS, SASUNO AGENT_CODE ,SAADID VEND_ID  from cidadr where saadte=1 and sacono=111 and (sasuno like ? or sasunm like ?) order by sasunm");
            stat.setString(1, para);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                    agentlist.add(new AgentBean(resultSet.getString("AGENT_NAME"),resultSet.getString("AGENT_ADDRESS"),resultSet.getString("AGENT_CODE"),resultSet.getString("VEND_ID")));
            }
        }
        catch(SQLException se){System.out.println("AgentBean"+se);}
        catch(Exception e){System.out.println("AgentBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();                
                    
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return agentlist;
    }


public String getAgentListAjax(String para){
    
    
    if(para!=null && para.length()>0){
        para=para.toUpperCase()+"%";
    }
    else{
        para="%";
    }
    String agentlist ="";
    try{
        conn=new connectiondb2().getConnection();
        stat=conn.prepareStatement("Select DISTINCT sasunm AGENT_NAME ,trim(SAADR1)||trim(SAADR2)||trim(SAADR3)||trim(SAADR4) AGENT_ADDRESS, SASUNO AGENT_CODE ,SAADID VEND_ID  from cidadr where saadte=1 and sacono=111 and (sasuno like ? or sasunm like ?) order by sasunm");
        stat.setString(1, para);
        stat.setString(2, para);
        resultSet = stat.executeQuery();
        while(resultSet.next()){
                agentlist=resultSet.getString("AGENT_NAME")+"#"+resultSet.getString("AGENT_ADDRESS")+"#"+resultSet.getString("AGENT_CODE")+"#"+resultSet.getString("VEND_ID");
        }
    }
    catch(SQLException se){System.out.println("AgentBean"+se);}
    catch(Exception e){System.out.println("AgentBean "+e);}
    finally{
        if(conn!=null)
            try {
                if(resultSet!=null)resultSet.close();
                if(stat!=null)stat.close();
                conn.close();                
                
                
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    return agentlist;
}


public AgentBean getAgentName(String para1,String para2){
    
      
        
        AgentBean agentlist = new AgentBean();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("Select DISTINCT sasunm AGENT_NAME ,trim(SAADR1)||trim(SAADR2)||trim(SAADR3)||trim(SAADR4) AGENT_ADDRESS, SASUNO AGENT_CODE ,SAADID VEND_ID  from cidadr where saadte=1 and sacono=111 and sasuno=? and SAADID=? ");
            stat.setString(1, para1);
            stat.setString(2, para2);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                agentlist.setAgentName(resultSet.getString("AGENT_NAME"));
                agentlist.setAgentAdd(resultSet.getString("AGENT_ADDRESS"));
                agentlist.setAgentCode(resultSet.getString("AGENT_CODE"));
                agentlist.setVendId(resultSet.getString("VEND_ID"));
           }
        }
        catch(SQLException se){System.out.println("AgentBean"+se);}
        catch(Exception e){System.out.println("AgentBean "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return agentlist;
    }

public List getWareHouseList(String userId){
    
      
        List whlist = new ArrayList();
        try{
            conn=new connection().getConnection();
          
            stat=conn.prepareStatement("select distinct warehouse from production.user_para_mast where user_id=? order by 1");
            stat.setString(1, userId);
            
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                
                    whlist.add(resultSet.getString("warehouse"));
            }
        } 
        catch(SQLException se){System.out.println("wh"+se);}
        catch(Exception e){System.out.println("wh "+e);}
        finally{
            if(conn!=null)
                try {
                     if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return whlist;
    }
public List<BuyerBean> getBuyerList(String para){
    
      
        if(para!=null && para.length()>0){
            para=para.toUpperCase()+"%";
        }
        else{
            para="%";
        }
        List<BuyerBean> buyerlist = new ArrayList<BuyerBean>();
        try{
            conn=new connectiondb2().getConnection();
            stat=conn.prepareStatement("Select distinct OKCUNM BUYER,trim(OKCUA1)||','||trim(OKCUA2)||','||trim(OKCUA3)||','||trim(OKCUA4)  BUYER_ADDRESS, OKCUNO BUYER_CODE,OKADID BUYER_ID FROM OCUSMA WHERE okcono=111 and (OKCUNO like ? or OKCUNM  like ?) order by 1 ");
            stat.setString(1, para);
            stat.setString(2, para);
            resultSet = stat.executeQuery();
            while(resultSet.next()){
                    buyerlist.add(new BuyerBean(resultSet.getString("BUYER"),resultSet.getString("BUYER_ADDRESS"),resultSet.getString("BUYER_CODE"),resultSet.getString("BUYER_ID")));
            }
        }
        catch(SQLException se){System.out.println("BuyerBean"+se);}
        catch(Exception e){System.out.println("BuyerBean "+e);}
        finally{
            if(conn!=null)
                try {
                    if(resultSet!=null)resultSet.close();
                    if(stat!=null)stat.close();
                    conn.close();   
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return buyerlist;
    }


 

    
}
