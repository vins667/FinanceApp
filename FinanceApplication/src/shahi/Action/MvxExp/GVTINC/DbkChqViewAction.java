
package shahi.Action.MvxExp.GVTINC;
 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.*; 
import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.Master.Beans.GetListBean; 
import shahi.Action.MvxExp.GVTINC.Beans.DbkChqBean;
import shahi.Action.MvxExp.GVTINC.Beans.ChqDetailBean;


 
public class DbkChqViewAction extends ActionSupport{

    
    private String UPDCODE;
    private String UPDYEAR;
    private String aausrid;
    private String PAMenu;
    private String currentdate;

    private String indexname;
    private String saveFlag;
    private String delFlag;
    private String fwdFlag;
    private List chkdel;
    private List fwdac;
    private String showFlag;
    private String updFlag;
    private String sstate;
    private String sedate;
    private String schqno;
    private String sbank;
    private String CHQ_NO;
    private String CHQ_DATE;
    private String CHQ_AMT;
    private String RECV_DATE;
    private String BANK;
    private String REMARK;
    private String schqdate;
    private String Ainsert="YES";
    private String Aupdate="YES";
    private String Adelete="YES";
    private String Aview="YES";
    private String Taccess="YES";
    private String SEARCH_CODE;
    private List ChqList   = new ArrayList();
    private List BankList = new ArrayList();
    private List ChqDetail   = new ArrayList();
    private List SBLIST   = new ArrayList();
    
    private List SHP_BILL_NO;
    private List SHP_BILL_DATE;
    private List CLAIM_PORT;
    private List DBK_ADMS;
    private List DBK_RECV;
    private List DBK_ADJUST;
    private List DBK_SUPL;
    private List DBK_WOFF;
    private List STR_ADMS;
    private List STR_RECV;
    private List STR_WOFF;
    private List ROSL_DUE;
    
    private List PAY_TYP;
    private List AMOUNT;
    private List BANK_CR;
    private List CHRG;
    private List R_AMONT;
    private List W_OFF;
    
    private String flag4;
    private String flagVAL;
    
    private double TOTAMOUNT;
    private double TBANK_CR;
    private double TSTRAMT;
    private double TWOFF;
    
    private String FWDDATE;
    
    
    
    public String execute()
    {
       

        try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }

        int falg = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if(usrid==null)
        {
           session.put("sessUserId",aausrid);
           usrid=aausrid;
        }

        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        try {
            Connection conn = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
            } // end catch

            PreparedStatement stat1=null,stat=null;
            ResultSet RTaccess = null,result1 =null,result=null;
           try
           {
             if (Taccess==null)
             {
               EisUtil pisutil = new EisUtil();
               RTaccess  = pisutil.getTableAccess(PAMenu, usrid);
               while(RTaccess.next())
               {
                    Taccess = RTaccess.getString("ACCESS_FLAG");
                    Ainsert = RTaccess.getString("ENTRY_FLAG");
                    Aupdate = RTaccess.getString("UPDATE_FLAG");
                    Adelete = RTaccess.getString("DELETE_FLAG");
                    Aview   = RTaccess.getString("SHOW_FLAG");
               }
               pisutil.closeConnection();
           }
 
             stat1 = conn.prepareStatement("select distinct nvl(bank,'NA') bank from finacsys.ei_dbk_chq_mast_test  order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 BankList.add(new GetListBean(result1.getString("bank"),result1.getString("bank")));      
              }
             if (showFlag!=null && showFlag.length()>0)
             {
                String sqlstr="";
                if(schqno!=null && schqno.length()>0 )
                {
                sqlstr=" and chq_no like '"+schqno.toUpperCase()+"%'";
                }
              if (schqdate!=null && schqdate.length()>0)
              {
                  
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'dd/mm/yyyy') chq_date,chq_amount,to_char(chq_recv_date,'dd/mm/yyyy') recv_date,to_char(ac_send_date,'dd/mm/yyyy') fwd_date,remark,year from  ei_dbk_chq_mast where  NVL(BANK,'NA') like  ? and chq_date=to_date(?,'yyyy-mm-dd') "+sqlstr+" order by 1,2" );
                stat1.setString(1,sbank);
               stat1.setString(2,schqdate.substring(0,10));
              }
              else if ((schqdate!=null && schqdate.length()>0) && (sedate!=null && sedate.length()>0) )
              {
                  
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'dd/mm/yyyy') chq_date,chq_amount,to_char(chq_recv_date,'dd/mm/yyyy') recv_date,to_char(ac_send_date,'dd/mm/yyyy') fwd_date,remark,year from  ei_dbk_chq_mast where  NVL(BANK,'NA') like  ? and chq_date=to_date(?,'yyyy-mm-dd') and  trunc(tdate)=to_date(?,'yyyy-mm-dd')  "+sqlstr+" order by 1,2" );
                stat1.setString(1,sbank);
               stat1.setString(2,schqdate.substring(0,10));
               stat1.setString(3,sedate.substring(0,10));
              }
              else if (sedate!=null && sedate.length()>0)
              {
                  
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'dd/mm/yyyy') chq_date,chq_amount,to_char(chq_recv_date,'dd/mm/yyyy') recv_date,to_char(ac_send_date,'dd/mm/yyyy') fwd_date,remark,year from  ei_dbk_chq_mast where  NVL(BANK,'NA') like  ? and trunc(tdate)=to_date(?,'yyyy-mm-dd') "+sqlstr+" order by 1,2" );
                stat1.setString(1,sbank);
               stat1.setString(2,sedate.substring(0,10));
              }
                  
              else{
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'dd/mm/yyyy') chq_date,chq_amount,to_char(chq_recv_date,'dd/mm/yyyy') recv_date,to_char(ac_send_date,'dd/mm/yyyy') fwd_date,remark,year from  ei_dbk_chq_mast where  NVL(BANK,'NA') like  ?  "+sqlstr+" order by 1,2" );
                stat1.setString(1,sbank);
              }
                result1 = stat1.executeQuery();
               while(result1.next())
               {   
                  
                   ChqList.add(new DbkChqBean(result1.getString("chq_no"),result1.getString("chq_date"),result1.getString("chq_amount"),result1.getString("recv_date"),result1.getString("remark"),result1.getString("fwd_date"),result1.getString("year")));
              } // end while


              }  //end showflag

             if(chkdel!=null && chkdel.size()>0 && delFlag!=null)
             {
                for(int i=0; i<chkdel.size(); i++)
                {
                     
                    String arr[] = chkdel.get(i).toString().split("--");   
                     
                    stat1 = conn.prepareStatement("delete from ei_dbk_chq_mast where year=? and  CHQ_NO=?");
                    stat1.setString(1,arr[0].trim());
                    stat1.setString(2,arr[1].trim());
                    stat1.executeUpdate();
                    
                    stat1 = conn.prepareStatement("delete from ei_dbk_chq_dtls where year=? and  CHQ_NO=?");
                    stat1.setString(1,arr[0].trim());
                    stat1.setString(2,arr[1].trim());
                    stat1.executeUpdate();
                    
                    conn.commit();
                    falg = 1;
                }
              } // end delete
              

              if(fwdac!=null && fwdac.size()>0 && fwdFlag!=null)
             {
                for(int i=0; i<fwdac.size(); i++)
                {   String arr[] = fwdac.get(i).toString().split("--"); 
                 
                    stat1 = conn.prepareStatement("update ei_dbk_chq_mast set ac_send_Date=sysdate where year=? and  CHQ_NO=?");
                    stat1.setString(1,arr[0].trim());
                    stat1.setString(2,arr[1].trim());
                    stat1.executeUpdate();
                    conn.commit();
                    falg = 1;
                }
              } // end fwd
             
              

          }catch (Exception e) {
            System.out.println(e.toString());
            addActionMessage("Error In  Statement !!");
            try{
                   conn.rollback();
            }catch(Exception ee) {System.out.println(ee.toString());}

            }finally {
                try
                {
                    if (stat1 != null) { stat1.close(); }
                    if (stat != null) { stat.close(); }

                    if (RTaccess != null) { RTaccess.close(); }
                    if (result != null) { result.close(); }
                    if (result1 != null) { result1.close(); }

                    if (conn != null) { conn.close(); }
                    RTaccess = null;
                    conn = null;
                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : DbkChqViewAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : DbkChqViewAction.java Exception in finally block");
                    return ERROR;
                }
            } /// end Finally Block
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        }
        if (falg == 1) {
            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        }
        else {
           
            return ERROR;
        }
    }  // execute

    //////////////////////////////    save/update Method
    public String update1() throws SQLException {
        Connection conn = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        PreparedStatement stat4 = null;
        ResultSet result4 = null;
        PreparedStatement stat5 = null;
        ResultSet result5 = null;
        PreparedStatement stat6 = null;
        ResultSet result6 = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch   

 
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       // LOCATION_CODE = "100";
        
        
       
               SimpleDateFormat fromUser  = new SimpleDateFormat("yyyy-MM-dd");
               SimpleDateFormat fromUser1  = new SimpleDateFormat("dd/MM/yyyy");
               SimpleDateFormat myFormat  = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0, z = 0;
                
                stat2 = conn.prepareStatement("select * from  ei_dbk_chq_mast where YEAR=? AND  CHQ_NO=? ");
                stat2.setString(1,UPDYEAR);
                stat2.setString(2, CHQ_NO.toUpperCase());
              //  stat2.setString(2, CHQ_DATE!=null && CHQ_DATE.length()>0?myFormat.format(fromUser.parse(CHQ_DATE)):"");
                result2 = stat2.executeQuery();
                if (result2.next()) {
                     
                    if(UPDCODE!=null && UPDCODE.length()>0){
                    stat4 = conn.prepareStatement("UPDATE  ei_dbk_chq_mast set CHQ_DATE=?,CHQ_AMOUNT=?,CHQ_RECV_DATE=?,BANK=?,"
                            + "REMARK=?,TDATE=sysdate,SEH_USER=?,LOCATION=? WHERE YEAR=? and CHQ_NO=?");
                    stat4.setString(1, CHQ_DATE!=null && CHQ_DATE.length()>0?myFormat.format(fromUser.parse(CHQ_DATE.trim())):"");
                    stat4.setString(2, CHQ_AMT);
                    stat4.setString(3, RECV_DATE!=null && RECV_DATE.length()>0?myFormat.format(fromUser.parse(RECV_DATE.trim())):"");
                    stat4.setString(4, BANK);
                    stat4.setString(5, REMARK);
                    stat4.setString(6, usrid);
                    stat4.setString(7, LOCATION_CODE);
                    stat4.setString(8, UPDYEAR);
                    stat4.setString(9, CHQ_NO.toUpperCase());
                    //stat4.setString(10,CHQ_DATE!=null && CHQ_DATE.length()>0?myFormat.format(fromUser.parse(CHQ_DATE.trim())):"");
                    x = stat4.executeUpdate();
                    if (x > 0) {
                        ++x;
                        conn.commit();
                    }
                    }
                }
                else{
                    try{
                    stat4 = conn.prepareStatement("insert into  ei_dbk_chq_mast(CHQ_NO,CHQ_DATE,CHQ_AMOUNT,CHQ_RECV_DATE,BANK,REMARK,TDATE,SEH_USER,LOCATION,year) values(?,?,?,?,?,?,trunc(sysdate),?,?,pay_fin_year(?))");
                    stat4.setString(1, CHQ_NO.toUpperCase());
                    stat4.setString(2, CHQ_DATE!=null && CHQ_DATE.length()>0?myFormat.format(fromUser.parse(CHQ_DATE.trim())):"");
                    stat4.setString(3, CHQ_AMT);
                    stat4.setString(4, RECV_DATE!=null && RECV_DATE.length()>0?myFormat.format(fromUser.parse(RECV_DATE.trim())):"");
                    stat4.setString(5, BANK);
                    stat4.setString(6, REMARK);
                    stat4.setString(7, usrid);
                    stat4.setString(8, LOCATION_CODE);
                    stat4.setString(9, CHQ_DATE!=null && CHQ_DATE.length()>0?myFormat.format(fromUser.parse(CHQ_DATE.trim())):"");
                    z = stat4.executeUpdate();
                    if (z > 0) {
                        ++z;
                        conn.commit();
                    }
                    }
                    catch(Exception e){
                       System.out.println(e.toString()); 
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
              
                int j=0;
                for (int i = 0; i < SHP_BILL_NO.size(); i++) {
                    ++j;
                    if (SHP_BILL_NO != null && SHP_BILL_NO.get(i).toString().length() > 0) {
                        stat = conn.prepareStatement("select * from  ei_dbk_chq_dtls where SHP_BILL_NO=? and SHP_BILL_DATE=? and pay_type=?");
                        stat.setString(1, SHP_BILL_NO.get(i).toString());
                        stat.setString(2, myFormat.format(fromUser1.parse(SHP_BILL_DATE.get(i).toString().trim())));
                        stat.setString(3, PAY_TYP.get(i).toString().trim());
                        result = stat.executeQuery();
                        if (result.next()) {
                            if(UPDCODE!=null && UPDCODE.length()>0){
                            stat4 = conn.prepareStatement("update ei_dbk_chq_dtls set AMOUNT=?,BANK_CR=?,"
                                    + "STR_AMT=?,W_OFF=?,TDATE=sysdate,SEH_USER=?,LOCATION=? where SHP_BILL_NO=? and SHP_BILL_DATE=?");
                            stat4.setString(1, AMOUNT.get(i).toString().trim());
                            stat4.setString(2, BANK_CR.get(i).toString().trim());
//                            stat4.setString(6, CHRG.get(i).toString().trim());
                            stat4.setString(3, R_AMONT.get(i).toString().trim());
                            stat4.setString(4, W_OFF.get(i).toString().trim());
                            stat4.setString(5, usrid);
                            stat4.setString(6, LOCATION_CODE);
                            stat4.setString(7, SHP_BILL_NO.get(i).toString());
                            stat4.setString(8, SHP_BILL_DATE.get(i).toString().trim()!=null && SHP_BILL_DATE.get(i).toString().trim().length()>0?myFormat.format(fromUser1.parse(SHP_BILL_DATE.get(i).toString().trim())):"");
                            x = stat4.executeUpdate();
                            if (x > 0) {
                                ++x;
                                conn.commit();
                            }
                            }
                        }
                        else{
                            stat4 = conn.prepareStatement("insert into  ei_dbk_chq_dtls(SHP_BILL_NO,SHP_BILL_DATE,PAY_TYPE,AMOUNT,BANK_CR,STR_AMT,W_OFF,TDATE,SEH_USER,LOCATION,year,CHQ_NO) values(?,?,?,?,?,?,?,trunc(sysdate),?,?,pay_fin_year(?),?)");
                            stat4.setString(1, SHP_BILL_NO.get(i).toString());
                            stat4.setString(2, SHP_BILL_DATE.get(i).toString().trim()!=null && SHP_BILL_DATE.get(i).toString().trim().length()>0?myFormat.format(fromUser1.parse(SHP_BILL_DATE.get(i).toString().trim())):"");
                            stat4.setString(3, PAY_TYP.get(i).toString().trim());
                            stat4.setString(4, AMOUNT.get(i).toString().trim());
                            stat4.setString(5, BANK_CR.get(i).toString().trim());
//                          stat4.setString(6, CHRG.get(i).toString().trim());
                            stat4.setString(6, R_AMONT.get(i).toString().trim());
                            stat4.setString(7, W_OFF.get(i).toString().trim());
                            stat4.setString(8, usrid);
                            stat4.setString(9, LOCATION_CODE);
                            stat4.setString(10, CHQ_DATE!=null && CHQ_DATE.length()>0?myFormat.format(fromUser.parse(CHQ_DATE.trim())):"");
                            stat4.setString(11, CHQ_NO);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                                conn.commit();
                            }
                            
                        }
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result != null) {
                    result.close();
                }
                

                if (x > 0 || z > 0) {
                    addActionMessage("Record Updated succcessfully ");
                    conn.commit();
                    
                    CHQ_NO=null;
                    CHQ_DATE=null;
                    CHQ_AMT=null;
                    RECV_DATE=null;
                    BANK=null;
                    REMARK=null;
                    
                    SHP_BILL_NO = null;
                    SHP_BILL_DATE = null;
                    CLAIM_PORT=null;
                    DBK_ADMS=null;
                    DBK_RECV=null;
                    DBK_ADJUST=null;
                    DBK_SUPL=null;
                    DBK_WOFF=null;
                    STR_ADMS=null;
                    STR_RECV=null;
                    STR_WOFF=null;
                    ROSL_DUE=null;
                    PAY_TYP = null;
                    AMOUNT = null;
                    BANK_CR = null;
                    R_AMONT = null;
                    W_OFF = null;
                    

                }

                if (stat2 != null) {
                    stat2.close();
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                if (result4 != null) {
                    result4.close();
                }
                if (result5 != null) {
                    result5.close();
                }
                if (stat5 != null) {
                    stat5.close();
                }
   
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
              conn.close();
            }
        }

       
         
         execute();

        return "updte";
    }
    
//////////////////////////////    Call Entry Method


     public String CallEntry()
    {
      try{
          
             Connection conn = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
            } // end catch

            PreparedStatement stat1=null,stat=null;
            ResultSet RTaccess = null,result1 =null,result=null;
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
            
             stat1 = conn.prepareStatement("select bank_desc||' - '||bank_code bankdesc ,bank_code  from  eis_dbk_bank where close_date is null order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 BankList.add(new GetListBean(result1.getString("bank_code"),result1.getString("bankdesc")));      
              }
            
            
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }
       return "NEW";
    }

//////////////////////////////    Call Update Method
    public String CallMstUpdate()
    {
        
      try{ 
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
            
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }
       try {
            Connection conn = null;
            PreparedStatement stat1=null;
            ResultSet result1 =null;
            PreparedStatement stat=null;
            ResultSet result =null;
            
            double a1=0.0;
            double a2=0.0;
            double a3=0.0;
            double a4=0.0;
            double a5=0.0;
         
            try{
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
                } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
               } //
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'yyyy-MM-dd') chq_date,chq_amount,to_char(chq_recv_date,'yyyy-MM-dd') recv_date,to_char(ac_send_date,'yyyy-MM-dd') fwd_date,remark,bank from  ei_dbk_chq_mast where chq_no=?" );
               stat1.setString(1,UPDCODE);
               result1 = stat1.executeQuery();
               while(result1.next())
               {
                CHQ_DATE=result1.getString("chq_date");
                RECV_DATE=result1.getString("recv_date");
                CHQ_AMT=result1.getString("chq_amount");
                REMARK=result1.getString("remark");
                BANK=result1.getString("bank");
                 stat = conn.prepareStatement("select a.shp_bill_no,to_char(a.shp_bill_date,'dd/mm/yyyy') sb_date,claim_port,dbk_admisable,"
                         + "dbk_received,dbk_adjusted,DBK_SUPL_RECV,woff_amt dbk_woff,str_recv,str_due,str_woff,rosl_due,amount,str_amt,"
                         + "w_off,b.PAY_TYPE,b.AMOUNT,b.BANK_CR,b.STR_AMT,b.W_OFF from ei_dbk_mast a,ei_dbk_chq_dtls b where a.shp_bill_no=b.shp_bill_no "
                         + "and a.shp_bill_date=b.shp_bill_date and b.chq_no=?" );
                 stat.setString(1,UPDCODE);
                 result = stat.executeQuery();
                 while(result.next())
                 {
                     ChqDetail.add(new ChqDetailBean(result.getString("shp_bill_no"),result.getString("sb_date"),result.getString("claim_port"),
                             result.getDouble("dbk_admisable"),result.getDouble("dbk_received"),result.getDouble("dbk_adjusted"),
                             result.getDouble("DBK_SUPL_RECV"),result.getDouble("dbk_woff"),result.getDouble("str_due"),
                             result.getDouble("str_recv"),result.getDouble("str_woff"),result.getDouble("rosl_due"),
                             result.getString("PAY_TYPE"),result.getDouble("AMOUNT"),result.getDouble("BANK_CR"),
                             result.getDouble("STR_AMT"),result.getDouble("W_OFF")));
                   
                  a1=a1+result.getDouble("AMOUNT");
                  a2=a2+result.getDouble("BANK_CR");
                  a4=a4+result.getDouble("STR_AMT");
                  a5=a5+result.getDouble("W_OFF");
                 
                 }
                
               } // end while
               
               TOTAMOUNT=a1;
               TBANK_CR=a2;
               TSTRAMT=a4;
               TWOFF=a5;
               
               
            PreparedStatement stat2=null;
            ResultSet result2 =null;
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
            
             stat2 = conn.prepareStatement("select bank_desc||' - '||bank_code bankdesc ,bank_code  from  eis_dbk_bank where close_date is null order by 1" );
             result2 = stat2.executeQuery();
             while(result2.next())
             {  
                 BankList.add(new GetListBean(result2.getString("bank_code"),result2.getString("bankdesc")));      
              }
               
 
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Error in Select Statement !!");
                return ERROR;
            } // end catch
            finally {
                try
                {
                    if (stat1 != null) { stat1.close(); }
                    if (result1 != null) { result1.close(); }
                    if (conn != null) { conn.close(); }
                    conn = null;
                } catch (Exception e) {
                    System.out.print("File Name : DbkChqViewAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : DbkChqViewAction.java Exception in finally block");
                    return ERROR;
                }
            } /// end Fina

        }
        catch (Exception e) { 
            e.printStackTrace();
            addActionError(e.getMessage());
            return ERROR;
        }

              return "NEW";
    }  // end methods updated

    
    
       public String SBVIEW() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        if (usrid == null && loc == null) {
            addActionError("Session not valid!!!");
            return SUCCESS;
        } 

        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        ResultSet result = null;
        ResultSet result1 = null;
        try {
            conn = new connection().getConnection();

             if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                  System.out.println("SEARCHCODE "+SEARCH_CODE);   
                        stat = conn.prepareStatement("select a.shp_bill_no,to_char(a.shp_bill_date,'dd/mm/yyyy') sb_date,claim_port,dbk_admisable,dbk_received,dbk_adjusted,DBK_SUPL_RECV,woff_amt dbk_woff,str_recv,str_due,str_woff,rosl_due,amount,str_amt,w_off,b.PAY_TYPE,b.AMOUNT,b.BANK_CR,b.STR_AMT,b.W_OFF from ei_dbk_mast a,ei_dbk_chq_dtls b where a.shp_bill_no=b.shp_bill_no and a.shp_bill_date=b.shp_bill_date and b.shp_bill_no like ? order by 1 ");
                        stat.setString(1, "%" + SEARCH_CODE.toUpperCase().trim() + "%");
                        result = stat.executeQuery();
                        while (result.next()) {
                             SBLIST.add(new ChqDetailBean(result.getString("shp_bill_no"),result.getString("sb_date"),result.getString("claim_port"),
                             result.getDouble("dbk_admisable"),result.getDouble("dbk_received"),result.getDouble("dbk_adjusted"),
                             result.getDouble("DBK_SUPL_RECV"),result.getDouble("dbk_woff"),result.getDouble("str_due"),
                             result.getDouble("str_recv"),result.getDouble("str_woff"),result.getDouble("rosl_due"),
                             result.getString("PAY_TYPE"),result.getDouble("AMOUNT"),result.getDouble("BANK_CR"),
                             result.getDouble("STR_AMT"),result.getDouble("W_OFF")));
                                 
                        }
                    }
 
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn!= null) {
                conn.close();
            }
            if (stat != null) {stat.close();}
            if (result != null){result.close();}
             if (stat1 != null) {stat1.close();}
            if (result1 != null){result1.close();}
        }

       
        return "searchsb";
    }

    public String getAdelete() {
        return Adelete;
    }

    public void setAdelete(String Adelete) {
        this.Adelete = Adelete;
    }

    public String getAinsert() {
        return Ainsert;
    }

    public void setAinsert(String Ainsert) {
        this.Ainsert = Ainsert;
    }

    public String getAupdate() {
        return Aupdate;
    }

    public void setAupdate(String Aupdate) {
        this.Aupdate = Aupdate;
    }

    public String getAview() {
        return Aview;
    }

    public void setAview(String Aview) {
        this.Aview = Aview;
    }

    public String getPAMenu() {
        return PAMenu;
    }

    public void setPAMenu(String PAMenu) {
        this.PAMenu = PAMenu;
    }

    public String getTaccess() {
        return Taccess;
    }

    public void setTaccess(String Taccess) {
        this.Taccess = Taccess;
    }

     

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

 
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

     
    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }
 
 
    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }
 

    public String getUPDCODE() {
        return UPDCODE;
    }

    public void setUPDCODE(String UPDCODE) {
        this.UPDCODE = UPDCODE;
    }

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getSstate() {
        return sstate;
    }

    public void setSstate(String sstate) {
        this.sstate = sstate;
    }

    public String getFwdFlag() {
        return fwdFlag;
    }

    public void setFwdFlag(String fwdFlag) {
        this.fwdFlag = fwdFlag;
    }

   

    public String getSchqno() {
        return schqno;
    }

    public void setSchqno(String schqno) {
        this.schqno = schqno;
    }

    public String getSbank() {
        return sbank;
    }

    public void setSbank(String sbank) {
        this.sbank = sbank;
    }

    public String getCHQ_NO() {
        return CHQ_NO;
    }

    public void setCHQ_NO(String CHQ_NO) {
        this.CHQ_NO = CHQ_NO;
    }

    public String getCHQ_DATE() {
        return CHQ_DATE;
    }

    public void setCHQ_DATE(String CHQ_DATE) {
        this.CHQ_DATE = CHQ_DATE;
    }

    public String getCHQ_AMT() {
        return CHQ_AMT;
    }

    public void setCHQ_AMT(String CHQ_AMT) {
        this.CHQ_AMT = CHQ_AMT;
    }

    public String getRECV_DATE() {
        return RECV_DATE;
    }

    public void setRECV_DATE(String RECV_DATE) {
        this.RECV_DATE = RECV_DATE;
    }

    public String getBANK() {
        return BANK;
    }

    public void setBANK(String BANK) {
        this.BANK = BANK;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getSchqdate() {
        return schqdate;
    }

    public void setSchqdate(String schqdate) {
        this.schqdate = schqdate;
    }

    public List getChqList() {
        return ChqList;
    }

    public void setChqList(List ChqList) {
        this.ChqList = ChqList;
    }

    public List getBankList() {
        return BankList;
    }

    public void setBankList(List BankList) {
        this.BankList = BankList;
    }

    public List getChqDetail() {
        return ChqDetail;
    }

    public void setChqDetail(List ChqDetail) {
        this.ChqDetail = ChqDetail;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public List getSBLIST() {
        return SBLIST;
    }

    public void setSBLIST(List SBLIST) {
        this.SBLIST = SBLIST;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }

    public List getSHP_BILL_NO() {
        return SHP_BILL_NO;
    }

    public void setSHP_BILL_NO(List SHP_BILL_NO) {
        this.SHP_BILL_NO = SHP_BILL_NO;
    }

    public List getSHP_BILL_DATE() {
        return SHP_BILL_DATE;
    }

    public void setSHP_BILL_DATE(List SHP_BILL_DATE) {
        this.SHP_BILL_DATE = SHP_BILL_DATE;
    }

    public List getCLAIM_PORT() {
        return CLAIM_PORT;
    }

    public void setCLAIM_PORT(List CLAIM_PORT) {
        this.CLAIM_PORT = CLAIM_PORT;
    }

    public List getDBK_ADMS() {
        return DBK_ADMS;
    }

    public void setDBK_ADMS(List DBK_ADMS) {
        this.DBK_ADMS = DBK_ADMS;
    }

    public List getDBK_RECV() {
        return DBK_RECV;
    }

    public void setDBK_RECV(List DBK_RECV) {
        this.DBK_RECV = DBK_RECV;
    }

    public List getDBK_ADJUST() {
        return DBK_ADJUST;
    }

    public void setDBK_ADJUST(List DBK_ADJUST) {
        this.DBK_ADJUST = DBK_ADJUST;
    }

    public List getDBK_SUPL() {
        return DBK_SUPL;
    }

    public void setDBK_SUPL(List DBK_SUPL) {
        this.DBK_SUPL = DBK_SUPL;
    }

    public List getDBK_WOFF() {
        return DBK_WOFF;
    }

    public void setDBK_WOFF(List DBK_WOFF) {
        this.DBK_WOFF = DBK_WOFF;
    }

    public List getSTR_ADMS() {
        return STR_ADMS;
    }

    public void setSTR_ADMS(List STR_ADMS) {
        this.STR_ADMS = STR_ADMS;
    }

    public List getSTR_RECV() {
        return STR_RECV;
    }

    public void setSTR_RECV(List STR_RECV) {
        this.STR_RECV = STR_RECV;
    }

    public List getSTR_WOFF() {
        return STR_WOFF;
    }

    public void setSTR_WOFF(List STR_WOFF) {
        this.STR_WOFF = STR_WOFF;
    }

    public List getROSL_DUE() {
        return ROSL_DUE;
    }

    public void setROSL_DUE(List ROSL_DUE) {
        this.ROSL_DUE = ROSL_DUE;
    }

    public List getPAY_TYP() {
        return PAY_TYP;
    }

    public void setPAY_TYP(List PAY_TYP) {
        this.PAY_TYP = PAY_TYP;
    }

    public List getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(List AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public List getBANK_CR() {
        return BANK_CR;
    }

    public void setBANK_CR(List BANK_CR) {
        this.BANK_CR = BANK_CR;
    }

    public List getCHRG() {
        return CHRG;
    }

    public void setCHRG(List CHRG) {
        this.CHRG = CHRG;
    }

    public List getR_AMONT() {
        return R_AMONT;
    }

    public void setR_AMONT(List R_AMONT) {
        this.R_AMONT = R_AMONT;
    }

    public List getW_OFF() {
        return W_OFF;
    }

    public void setW_OFF(List W_OFF) {
        this.W_OFF = W_OFF;
    }

    public String getFlag4() {
        return flag4;
    }

    public void setFlag4(String flag4) {
        this.flag4 = flag4;
    }

    public String getFlagVAL() {
        return flagVAL;
    }

    public void setFlagVAL(String flagVAL) {
        this.flagVAL = flagVAL;
    }

    public double getTOTAMOUNT() {
        return TOTAMOUNT;
    }

    public void setTOTAMOUNT(double TOTAMOUNT) {
        this.TOTAMOUNT = TOTAMOUNT;
    }

    public double getTBANK_CR() {
        return TBANK_CR;
    }

    public void setTBANK_CR(double TBANK_CR) {
        this.TBANK_CR = TBANK_CR;
    }


    public double getTSTRAMT() {
        return TSTRAMT;
    }

    public void setTSTRAMT(double TSTRAMT) {
        this.TSTRAMT = TSTRAMT;
    }

    public double getTWOFF() {
        return TWOFF;
    }

    public void setTWOFF(double TWOFF) {
        this.TWOFF = TWOFF;
    }

    public String getFWDDATE() {
        return FWDDATE;
    }

    public void setFWDDATE(String FWDDATE) {
        this.FWDDATE = FWDDATE;
    }

    public String getUPDYEAR() {
        return UPDYEAR;
    }

    public void setUPDYEAR(String UPDYEAR) {
        this.UPDYEAR = UPDYEAR;
    }

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
    }

    public List getFwdac() {
        return fwdac;
    }

    public void setFwdac(List fwdac) {
        this.fwdac = fwdac;
    }

    public String getSedate() {
        return sedate;
    }

    public void setSedate(String sedate) {
        this.sedate = sedate;
    }

    
  
 
           
  
}
