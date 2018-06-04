
package shahi.Action.MvxExp;
 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.Master.Beans.GetListBean;
import shahi.Action.MvxExp.Beans.DbkChqBean;
import shahi.Action.MvxExp.Beans.ChqDetailBean;
 
 
public class DbkChqViewAction extends ActionSupport{

    
    private String UPDCODE;

    private String aausrid;
    private String PAMenu;
    private String currentdate;

    
    private String saveFlag;
    private String delFlag;
    private String fwdFlag;
    private List chkdel;
    private List fwdac;
    private String showFlag;
    private String updFlag;
    private String sstate;
    private String schqno;
    private String sbank;
    private String CHQ_NO;
    private String CHQ_DATE;
    private String CHQ_AMT;
    private String RECV_DATE;
    private String BANK;
    private String REMARK;
    private String schqdate;
    private String IOBFlag;
    private String Ainsert="YES";
    private String Aupdate="YES";
    private String Adelete="YES";
    private String Aview="YES";
    private String Taccess="YES";
    
    private List ChqList   = new ArrayList();
    private List BankList = new ArrayList();
    private List ChqDetail   = new ArrayList();
    
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
 
             stat1 = conn.prepareStatement("select distinct nvl(bank,'NA') bank from ei_Dbk_chq_mast  order by 1" );
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
                  
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'dd/mm/yyyy') chq_date,chq_amount,to_char(chq_recv_date,'dd/mm/yyyy') recv_date,to_char(ac_send_date,'dd/mm/yyyy') fwd_date,remark from  EI_DBK_CHQ_MAST where  NVL(BANK,'NA') like  ? and chq_date=to_date(?,'yyyy-mm-dd') "+sqlstr+" order by 1,2" );
                stat1.setString(1,sbank);
               stat1.setString(2,schqdate.substring(0,10));
              }else{
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'dd/mm/yyyy') chq_date,chq_amount,to_char(chq_recv_date,'dd/mm/yyyy') recv_date,to_char(ac_send_date,'dd/mm/yyyy') fwd_date,remark from  EI_DBK_CHQ_MAST where  NVL(BANK,'NA') like  ?  "+sqlstr+" order by 1,2" );
                stat1.setString(1,sbank);
              }
                result1 = stat1.executeQuery();
               while(result1.next())
               {   
                  
                   ChqList.add(new DbkChqBean(result1.getString("chq_no"),result1.getString("chq_date"),result1.getString("chq_amount"),result1.getString("recv_date"),result1.getString("remark"),result1.getString("fwd_date")));
              } // end while


              }  //end showflag

             if(chkdel!=null && chkdel.size()>0 && delFlag!=null)
             {
                for(int i=0; i<chkdel.size(); i++)
                {
                    stat1 = conn.prepareStatement("delete from EI_DBK_CHQ_MAST where  CHQ_NO=?");
                    stat1.setString(1,chkdel.get(i).toString());
                    stat1.executeUpdate();
                    conn.commit();
                    falg = 1;
                }
              } // end delete
                if(saveFlag!=null && saveFlag.length()>0)
              {
                                  
                       stat1 = conn.prepareStatement("select * from EI_DBK_CHQ_MAST where CHQ_NO=? ");
                       stat1.setString(1,CHQ_NO.toUpperCase());
                       result1 = stat1.executeQuery();
                       if(result1.next())
                       {
                                      addActionMessage("CHQ NO Already Exist..");
                                       
                                    }else{
                                      stat1 = conn.prepareStatement("insert into SD_PROJ_MAST(PROJ_CODE,PROJ_NAME,PROJ_STATE,FLAG,USER_ID,TDATE,PROJ_REF,REF_DATE) values(?,?,?,?,?,sysdate,?,to_date(?,'yyyy-mm-dd'))");
                                      
                                      stat1.setString(4, "A");
                                      stat1.setString(5,aausrid);
                                     
                                      
                                      stat1.executeUpdate();
                                      conn.commit();
                                      falg = 1;
                                  }
               } // end save insert

              if(fwdac!=null && fwdac.size()>0 && fwdFlag!=null)
             {
                for(int i=0; i<fwdac.size(); i++)
                {
                    stat1 = conn.prepareStatement("update ei_dbk_chq_mast set ac_send_Date=sysdate where  CHQ_NO=?");
                    stat1.setString(1,fwdac.get(i).toString());
                    stat1.executeUpdate();
                    conn.commit();
                    falg = 1;
                }
              } // end fwd
             
              if (IOBFlag!=null && IOBFlag.length()>0 )
              {
                 stat = conn.prepareStatement("select a.shp_bill_no,to_char(a.shp_bill_date,'dd/mm/yyyy') shp_bill_date,amount,str_amt,w_off,dbk_admisable,str_due from ei_dbk_mast a,ei_dbk_chq_dtls b where a.shp_bill_no=b.shp_bill_no and a.shp_bill_date=b.shp_bill_date and b.chq_no=? " );
                 stat.setString(1,UPDCODE);
                 result = stat.executeQuery();
                 while(result.next())
                 {
                     ChqDetail.add(new ChqDetailBean(result.getString("shp_bill_no"),result.getString("shp_bill_date"),result.getDouble("dbk_admisable"),result.getDouble("amount"),result.getDouble("str_due"),result.getDouble("str_amt"),result.getDouble("w_off")));
                 }
              
              }

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

//////////////////////////////    Call Entry Method


     public String CallEntry()
    {
      try{
            PreparedStatement stat1=null,stat=null;
            ResultSet RTaccess = null,result1 =null,result=null;
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            
            
                       pisdate.closeConnection();
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
         
            try{
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
                } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
               } //
               stat1 = conn.prepareStatement("select chq_no,to_char(chq_date,'dd/mm/yyyy') chq_date,chq_amount,to_char(chq_recv_date,'dd/mm/yyyy') recv_date,to_char(ac_send_date,'dd/mm/yyyy') fwd_date,remark,bank from  EI_DBK_CHQ_MAST where chq_no=?" );
               stat1.setString(1,UPDCODE);
               result1 = stat1.executeQuery();
               while(result1.next())
               {
                CHQ_DATE=result1.getString("chq_date");
                RECV_DATE=result1.getString("recv_date");
                CHQ_AMT=result1.getString("chq_amount");
                REMARK=result1.getString("remark");
                BANK=result1.getString("bank");
                 stat = conn.prepareStatement("select a.shp_bill_no,to_char(a.shp_bill_date,'dd/mm/yyyy') shp_bill_date,amount,str_amt,w_off,dbk_admisable,str_due from ei_dbk_mast a,ei_dbk_chq_dtls b where a.shp_bill_no=b.shp_bill_no and a.shp_bill_date=b.shp_bill_date and b.chq_no=? " );
                 stat.setString(1,UPDCODE);
                 result = stat.executeQuery();
                 while(result.next())
                 {
                    
                     ChqDetail.add(new ChqDetailBean(result.getString("shp_bill_no"),result.getString("shp_bill_date"),result.getDouble("dbk_admisable"),result.getDouble("amount"),result.getDouble("str_due"),result.getDouble("str_amt"),result.getDouble("w_off")));
                   
                  }
                
               } // end while
 
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

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
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

    public List getFwdac() {
        return fwdac;
    }

    public void setFwdac(List fwdac) {
        this.fwdac = fwdac;
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

       

}
