package shahi.Action.MvxExp.PRE;

 
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
import shahi.Action.MvxExp.PRE.Beans.MultipleInvBean;
import shahi.Action.MvxExp.PRE.Beans.MultipleInvAccrBean;
import shahi.Action.MvxExp.PRE.Beans.MultipleInvMastBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
 
 
  
   

public class MultipleInvViewAction extends ActionSupport {
   
    private String currentdate;
    private String aausrid;
    private String searchloct;
    private String userloct;
    private Double searchamount=0.0;
    private String searchbos;
    private String viewFlag;
    private List InvList = new ArrayList();
    private List InvMast = new ArrayList();
    private List AccrList = new ArrayList();
      private List unitList = new ArrayList<UnitBean>();
    private String searchitem;
    private String searchtype="INV";
    private Double IGRWT=0.0;
    private Double INETWT=0.0;
    private int ICTNS=0;
    
    private Double TFOB=0.0;
    private Double TQTY=0.0;
    private Double TGR=0.0;
    private Double TACCRQTY=0.0;
    private Double TACCRFOB=0.0;
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
                            if (searchtype.equals("INV")){
                              str1 += " and a.excs_inv_no in (" + pInv + ")";}
                            else{
                                 str1 += " and a.plan_no in (" + pInv + ")";
                                 }
                       
                      } 
                     String discharge_desc="";
                     stat=conn.prepareStatement(" select plan_no,excs_inv_no,to_char(inv_date,'dd/mm/yyyy') inv_date,to_char(fwd_custom,'dd/mm/yyyy') FWD_date,discharge,ctns,grwt,netwt from ei_endors_mast a where company='111' " + str1 +" order by plan_no,excs_inv_no ");
                     result=stat.executeQuery();
                     while (result.next())
                     {     UnitBean bn = new PreInvoiceDao().getCsytabBeanByName(result.getString("discharge"),"SDST");
                            discharge_desc = bn.getUNIT_DESC();
                            
                        INETWT=INETWT+result.getDouble("netwt");
                        IGRWT=IGRWT+result.getDouble("grwt");
                        ICTNS=ICTNS+result.getInt("ctns");
                        
                        InvMast.add(new MultipleInvMastBean(result.getString("plan_no"),result.getString("excs_inv_no"),result.getString("inv_date"),discharge_desc,result.getString("FWD_date"),result.getInt("ctns"), result.getDouble("grwt"),result.getDouble("netwt")));
                         
                     }
                     
                      TQTY=0.0;TFOB=0.0;TGR=0.0; 
                       stat=conn.prepareStatement("select a.location,a.plan_no,a.excs_inv_no,to_char(inv_date,'dd/mm/yyyy') inv_date,hscode1,price_fc,price_misc,crncy_code,description,dbk_slno,scheme_code,discharge,igst_per,cgst_per,sgst_per,sum(qty_endors) lineqty, " +
                                                    " sum(qty_endors)*(nvl(price_fc,0)+nvl(price_misc,0))-sum(gr_decl_amt) linefob,sum(gr_decl_amt) gr_decl from ei_endors_mast a,ei_endors_dtls B  where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no "+str1 +
                                                    " group by a.location,a.plan_no,a.excs_inv_no,to_char(inv_date,'dd/mm/yyyy'),hscode1,price_fc,price_misc,crncy_code,description,dbk_slno,scheme_code,discharge,igst_per,cgst_per,sgst_per order by 1,2,3");
                        result = stat.executeQuery(); 
                       while (result.next()) 
                        {
                               
                           TQTY=TQTY+result.getDouble("lineqty");
                           TFOB=TFOB+result.getDouble("linefob");
                           TGR=TGR+result.getDouble("GR_DECL");
                           InvList.add(new MultipleInvBean(result.getString("location"),result.getString("plan_no"), result.getString("excs_inv_no"), result.getString("hscode1"),result.getString("inv_date"),result.getString("price_fc"),result.getString("price_misc"), result.getString("crncy_code"),result.getString("description"),result.getString("dbk_slno"),discharge_desc,result.getString("scheme_code"),result.getString("lineqty"),roundTwoDecimals(result.getDouble("gr_decl")),roundTwoDecimals(result.getDouble("linefob")),null,result.getDouble("IGST_PER"),result.getDouble("CGST_PER"),result.getDouble("SGST_PER")));
                        }
                        result.close();
                        stat.close();
                   TFOB=roundTwoDecimals(TFOB);
                   TGR=roundTwoDecimals(TGR);
                   
                            TACCRQTY=0.0;TACCRFOB=0.0;
                      
                            stat=conn.prepareStatement("select EXCS_INV_NO,accr_desc,sum(accr_qty) accr_qty,accr_price,sum(accr_qty*accr_price) accr_fob,accr_dbkslno,accr_strslno from ei_endors_mast a,ei_endors_accr_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no " +str1 +
                                                       "group by excs_inv_no,accr_desc,accr_price,accr_dbkslno,accr_strslno");
                            result=stat.executeQuery();
                            while (result.next())
                            {  
                             TACCRQTY=TACCRQTY+result.getDouble("accr_qty");
                             TACCRFOB=TACCRFOB+result.getDouble("accr_fob");   
                             
                                   AccrList.add(new MultipleInvAccrBean(result.getString("excs_inv_no"), result.getString("accr_desc"),result.getString("accr_price"),roundTwoDecimals(result.getDouble("accr_qty")),roundTwoDecimals(result.getDouble("accr_fob")),result.getString("accr_dbkslno"),result.getString("accr_strslno")));
                   
                            }
                            
                            TACCRQTY=roundTwoDecimals(TACCRQTY);
                            TACCRFOB=roundTwoDecimals(TACCRFOB);
                            
                             result.close();
                        stat.close();
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

   
   

    public Double getTQTY() {
        return TQTY;
    }

    public void setTQTY(Double TQTY) {
        this.TQTY = TQTY;
    }

    public List getAccrList() {
        return AccrList;
    }

    public void setAccrList(List AccrList) {
        this.AccrList = AccrList;
    }

   
    public String getUserloct() {
        return userloct;
    }

    public void setUserloct(String userloct) {
        this.userloct = userloct;
    }

    public Double getTFOB() {
        return TFOB;
    }

    public void setTFOB(Double TFOB) {
        this.TFOB = TFOB;
    }

    public Double getTGR() {
        return TGR;
    }

    public void setTGR(Double TGR) {
        this.TGR = TGR;
    }

    public Double getTACCRQTY() {
        return TACCRQTY;
    }

    public void setTACCRQTY(Double TACCRQTY) {
        this.TACCRQTY = TACCRQTY;
    }

    public Double getTACCRFOB() {
        return TACCRFOB;
    }

    public void setTACCRFOB(Double TACCRFOB) {
        this.TACCRFOB = TACCRFOB;
    }
 
 
 
          public  String roundToDecimals(double d, int c) {
        double temp = (double) ((d * Math.pow(10, c)));
        temp = Math.round(temp);
        
        double aa= (((double) temp) / Math.pow(10, c));
       
        return String.format( "%."+c+"f", aa );
    } 

    public String getSearchitem() {
        return searchitem;
    }

    public void setSearchitem(String searchitem) {
        this.searchitem = searchitem;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public List getInvMast() {
        return InvMast;
    }

    public void setInvMast(List InvMast) {
        this.InvMast = InvMast;
    }

    public List getUnitList() {
        return unitList;
    }

    public void setUnitList(List unitList) {
        this.unitList = unitList;
    }

    public Double getIGRWT() {
        return IGRWT;
    }

    public void setIGRWT(Double IGRWT) {
        this.IGRWT = IGRWT;
    }

    public Double getINETWT() {
        return INETWT;
    }

    public void setINETWT(Double INETWT) {
        this.INETWT = INETWT;
    }

    public int getICTNS() {
        return ICTNS;
    }

    public void setICTNS(int ICTNS) {
        this.ICTNS = ICTNS;
    }

     

    
 
   
      
} 
