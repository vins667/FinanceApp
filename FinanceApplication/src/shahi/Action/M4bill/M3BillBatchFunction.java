/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import shahi.Action.MIM4.APZ100MI;
import shahi.Action.MIM4.XPZ100MI;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author RANJEET
 */
public class M3BillBatchFunction {
    
     public void UpdateBatch(){
     
         Connection conn = null;
         Connection conn1 = null;
       
        int flag = 0;
        try {
            conn =  new ConnectionSeplWeb().getConnection();
             conn1 = new connectiondb2().getConnection();
           

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
        ResultSet result2 = null;
        
     
                  
        try { 
            CallableStatement   statcall=conn.prepareCall("{call seplweb.m4_bill_breakup }");
            statcall.executeUpdate();

            statcall=conn.prepareCall("{call seplweb.m4_bill_breakup_gst }");
            statcall.executeUpdate();
            
            stat1=conn.prepareStatement("select sl_no,XACONO,XADIVI,XAYEA4,XASUNO,XASINO,nvl(XAINDX,0) XAINDX, nvl(XACUAM,0) XACUAM,nvl(XAAIT1,' ') XAAIT1,nvl(XAAIT2,' ') XAAIT2,nvl(XAAIT3,' ') XAAIT3,nvl(XAAIT4,' ') XAAIT4, "+
" nvl(XAAIT5,' ') XAAIT5,nvl(XAAIT6,' ') XAAIT6,nvl(XAAIT7,' ') XAAIT7, nvl(XAPRCT,0) XAPRCT,to_char(XARGDT,'yyyymmdd') XARGDT, nvl(XACHID,' ')  XACHID ,REPORT_NO" +
",nvl(XACSNO,' ') XACSNO,nvl(XAGEOT,' ') XAGEOT, nvl(XAGEOF,' ') XAGEOF,nvl(XAZGST,'0') XAZGST,nvl(XATAXC,' ') XATAXC,nvl(XAZNTD,'0') XAZNTD,nvl(qty,'0') qty,nvl(uom,' ') uom  from m4_bill_detail_breakup where UPD_STATUS is null   order by sl_no");
       result1=stat1.executeQuery();
            while(result1.next())
            {
            String st=null;           
            XPZ100MI MI = new XPZ100MI();
            MI.connect();
            System.out.println(result1.getString("XASUNO")+"::"+result1.getString("XASINO"));
            st = MI.InsertRecord(result1.getString("XACONO"),result1.getString("XADIVI"),result1.getString("XASUNO"),result1.getString("XASINO"),result1.getString("XAYEA4"),result1.getString("XAINDX"),result1.getString("XACUAM"),result1.getString("XAPRCT"),result1.getString("XAAIT1"),result1.getString("XAAIT2"),result1.getString("XAAIT3"),result1.getString("XAAIT4"),result1.getString("XAAIT5")
                    ,result1.getString("XAAIT6"),result1.getString("XAAIT7"),result1.getString("XACSNO"),result1.getString("XAGEOT"),result1.getString("XAGEOF"),result1.getString("XAZGST"),result1.getString("XATAXC"),result1.getString("XAZNTD"),result1.getString("qty"),result1.getString("uom"));
            MI.destroyMI();
            MI = null;
            System.out.println(st);
            if (st != null) {
               
                stat1=conn.prepareStatement("update m4_bill_detail_breakup set UPD_STATUS=?,UPD_DATE=sysdate where sl_no=?");
                stat1.setString(1, st);
                stat1.setString(2, result1.getString("sl_no"));
                stat1.executeUpdate();
                
                
                
                APZ100MI MIUP=new APZ100MI();
                MIUP.connect();
                MIUP.UpdRecord(result1.getString("XADIVI"), result1.getString("XASUNO"), result1.getString("XASINO"), result1.getString("XAYEA4"), result1.getString("REPORT_NO"));
                MIUP.destroyMI();
                MIUP = null;
                
                
                // stat1=conn1.prepareStatement("update shacdtprod.supinv set apacfg=0 where  apsuno=?  and apsino=? and apinyr=?");
                /// stat1.setString(1, result1.getString("XASUNO"));
                // stat1.setString(2, result1.getString("XASINO"));
                //stat1.setString(3, result1.getString("XAYEA4"));
                // stat1.executeUpdate();
                
                
            }


            }
            
            
            
            
        }catch (Exception e) {

            System.out.print("2 File Name : M4BillBatchFunction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (result != null) {
                    result.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (conn != null) {
                    conn.close();
                }
               if (conn1 != null) {
                    conn1.close();
                }
                
                result1 = null;
                stat1 = null;
                conn = null;
                conn1=null;
               


            } catch (Exception e) {
                System.out.print("File Name : M3BillBatchFunction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
       
       
       }
}
