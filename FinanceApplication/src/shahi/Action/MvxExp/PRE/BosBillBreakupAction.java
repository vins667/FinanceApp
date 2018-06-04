package shahi.Action.MvxExp.PRE;

 
import shahi.Action.MvxExp.PRE.Beans.BosBillPchBean;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection; 
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.math.BigDecimal; 
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.PRE.Beans.BosBillPchBean;
 import shahi.Action.MvxExp.PRE.Beans.BosBillBreakupBean;
 
 
   

public class BosBillBreakupAction extends ActionSupport {
   
    private String currentdate;
    private String aausrid;
    private String searchloct;
    private String userloct;
    private Double searchamount=0.0;
    private String searchbos;
    private String viewFlag;
    private List InvList = new ArrayList();
    private List PchList = new ArrayList();
    private Double TCFT=0.0;
    private Double TCTNS=0.0;
    private Double TQTY=0.0;
     private Double PCFT=0.0;
    private Double PCTNS=0.0;
    private Double PQTY=0.0;
    @Override
    public String execute() {
        
     try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }
        
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
     //   aausrid = "227350";
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
            } // end catch 
            PreparedStatement stat = null;
            PreparedStatement stat1 = null;
          
            ResultSet result = null;
            ResultSet result1 = null;
           
            try {
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
            
               stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
               stat1.setString(1,usrid);
               result1=stat1.executeQuery();
               while (result1.next())
               {   
                   userloct=result1.getString("location_code");
                } 
                
                String sqlstr="";
                 BigDecimal tcf = new BigDecimal("0.00");
                if (viewFlag.equals("Yes")) 
                {    
                     String[] arr1 = searchbos.split(","); 
                     String str1="";
                     String pInv = "";
                   if (arr1 != null && arr1.length > 0) 
                   {
                       String faciQry = " ";
                   for (int i = 0; i < arr1.length; i++) 
                    {
                         faciQry += "'" + arr1[i] + "',";
                    }
                    faciQry = faciQry.substring(0, (faciQry.length() - 1));
                    pInv = faciQry;
                      str1 += " and b.bos_no in (" + pInv + ")";
                      } 
                     TQTY=0.0; TCTNS=0.0;TCFT=0.0;PQTY=0.0;PCFT=0.0;PCTNS=0.0; 
                        stat=conn.prepareStatement("select a.year,a.company,a.inv_no,a.excs_inv_no,cost_centre,transporter,dispatch_via,port,unit,b.bos_no,to_char(b.bos_date,'dd/mm/yyyy') bos_date,decode(fy_user,null,c.pkgs,c.fy_pkgs) pkgs,decode(fy_user,null,c.qnty,c.fy_qnty) qnty,c.cft_plan,a.location from ei_endors_mast a,ei_bos_mast b,ei_bos_dtls c	"+
                                                   " where a.year=c.year and a.company=c.company and a.inv_no=c.inv_no  and b.bos_no=c.bos_no and b.bos_loct=c.bos_location and b.cancel_date is null and  c.dispatch_yn='Y'  and b.bos_loct=? "+str1+" order by b.bos_no");
                   
                       stat.setString(1,searchloct.toUpperCase().trim());
                        result = stat.executeQuery(); 
                       while (result.next()) 
                        {
                           TQTY=TQTY+result.getDouble("qnty");
                           TCTNS=TCTNS+result.getDouble("pkgs");
                           TCFT=TCFT+result.getDouble("cft_plan");
                           InvList.add(new BosBillBreakupBean(result.getString("location"), result.getString("excs_inv_no"), result.getString("BOS_NO"),result.getString("bos_date"),result.getString("unit"),result.getString("port"), result.getString("transporter"),result.getString("dispatch_via"),result.getString("cft_plan"),result.getString("qnty"),result.getString("pkgs"),result.getString("cost_centre")));
                        }
                        result.close();
                        stat.close();
                         
                        double pcft_amt=0.0; double pqty_amt=0.0; double pctn_amt=0.0;
                        stat=conn.prepareStatement("select b.bos_loct,cost_centre, sum(decode(fy_user,null,c.qnty,c.fy_qnty)) pch_qty,sum(decode(fy_user,null,c.pkgs,c.fy_pkgs)) pch_pkgs,sum(c.cft_plan) pch_cft  from ei_endors_mast a,ei_bos_mast b,ei_bos_dtls c	"+
                                                   " where a.year=c.year and a.company=c.company and a.inv_no=c.inv_no  and b.bos_no=c.bos_no and b.bos_loct=c.bos_location and b.cancel_date is null and  c.dispatch_yn='Y' and  b.bos_loct=? "+str1+" group by b.bos_loct,cost_centre");
                     //  stat.setString(1,searchbos.toUpperCase().trim());
                       stat.setString(1,searchloct.toUpperCase().trim());
                       result = stat.executeQuery(); 
                       while (result.next()) 
                        {   
                         if (TCFT>0)   
                         { pcft_amt=roundTwoDecimals(searchamount/TCFT*result.getDouble("pch_cft"));}
                           pqty_amt=roundTwoDecimals(searchamount/TQTY*result.getDouble("pch_qty"));
                           pctn_amt=roundTwoDecimals(searchamount/TCTNS*result.getDouble("pch_pkgs"));
                           PQTY=PQTY+ pqty_amt;
                           PCTNS=PCTNS+pctn_amt;
                           PCFT=PCFT+pcft_amt;
                           PchList.add(new BosBillPchBean(result.getString("bos_loct"),result.getString("cost_centre"),pcft_amt,pcft_amt,pqty_amt,pqty_amt,pctn_amt,pctn_amt));
                        
                        }
                } // View Flg Close      
 

           
                     
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : BosBillBreakupAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : BosBillBreakupAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                    

                    if (stat1 != null) {
                        stat1.close();
                    }
                     
                    if (stat != null) {
                        stat.close();
                    }
                    
                    if (conn != null) {
                        conn.close(); 
                    }
                     
                    result1 = null;
                    stat1 = null;
                    stat =null;
                 
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : BosBillBreakupAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
            
            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");
             
            return ERROR;
        }
    }

    double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
}

    public String getAausrid() {
        return aausrid;
    } 

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    
    

    public String getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(String viewFlag) {
        this.viewFlag = viewFlag;
    }

  
 
    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    

    public List getInvList() {
        return InvList;
    }

    public void setInvList(List InvList) {
        this.InvList = InvList; 
    }

    public String getSearchloct() {
        return searchloct;
    }

    public void setSearchloct(String searchloct) {
        this.searchloct = searchloct;
    }

    public Double getSearchamount() {
        return searchamount;
    }

    public void setSearchamount(Double searchamount) {
        this.searchamount = searchamount;
    }

   

    public String getSearchbos() {
        return searchbos;
    }

    public void setSearchbos(String searchbos) {
        this.searchbos = searchbos;
    }

   
    public Double getTCTNS() {
        return TCTNS;
    }

    public void setTCTNS(Double TCTNS) {
        this.TCTNS = TCTNS;
    }

    public Double getTQTY() {
        return TQTY;
    }

    public void setTQTY(Double TQTY) {
        this.TQTY = TQTY;
    }

    public Double getTCFT() {
        return TCFT;
    }

    public void setTCFT(Double TCFT) {
        this.TCFT = TCFT;
    }

    public List getPchList() {
        return PchList;
    }

    public void setPchList(List PchList) {
        this.PchList = PchList;
    }

    public Double getPCFT() {
        return PCFT;
    }

    public void setPCFT(Double PCFT) {
        this.PCFT = PCFT;
    }

    public Double getPCTNS() {
        return PCTNS;
    }

    public void setPCTNS(Double PCTNS) {
        this.PCTNS = PCTNS;
    }

    public Double getPQTY() {
        return PQTY;
    }

    public void setPQTY(Double PQTY) {
        this.PQTY = PQTY;
    }

    public String getUserloct() {
        return userloct;
    }

    public void setUserloct(String userloct) {
        this.userloct = userloct;
    }
 
 
 
     

   

   
      
} 
