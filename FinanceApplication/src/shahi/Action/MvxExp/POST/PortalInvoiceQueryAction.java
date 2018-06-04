package shahi.Action.MvxExp.POST;

 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport; 
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.POST.Beans.PUQueryBean;
  

public class PortalInvoiceQueryAction extends ActionSupport {
    private String currentdate;
    private String aausrid;
    private String viewFlag;
    private List showList;
    private String searchinv;
    private String SEARCH_BUYER;
    private String DATE_FROM;
    private String DATE_TO;
    private String LOCT;   
    private String SEARCH_LOCT;
    private String SEARCHTYPE;
    
    private List ShowDetail=new ArrayList();
    
    
    @Override
    public String execute() {
        showList = new ArrayList();
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
       // usrid = "227350"; 
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }  
        try { 

            Connection conn = null;
            Connection conndb2 = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            try {
                conndb2 = new connectiondb2().getConnection();
                conndb2.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            PreparedStatement stat = null;
            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            PreparedStatement stat3 = null;
            
            ResultSet result = null;
            ResultSet result1 = null;
            ResultSet result2 = null;
            ResultSet result3 = null;
            
            try {
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                 stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                stat1.setString(1,usrid);
                result1=stat1.executeQuery();
                while (result1.next())
                {LOCT=result1.getString("location_code");
                } 
                System.out.println(" date from "+DATE_FROM+" TODATE "+DATE_TO);
                if (viewFlag != null ) 
                {  
                      String query="";
                                if(SEARCH_BUYER!=null && SEARCH_BUYER.length()>0){
                                    query+=" and A.BUYER like '"+"%"+SEARCH_BUYER.toUpperCase()+"%"+"'";
                                }
                                if (SEARCHTYPE.equals("PEND"))
                                {
                                   query+=" and c.tr_date is null ";
                                }
                                if (SEARCHTYPE.equals("APRV"))
                                {
                                   query+=" and c.tr_date is not null ";
                                }
                    stat1 = conn.prepareStatement("select a.BUYER,a.excs_inv_no,a.DESTI_CNTRY,a.COST_CENTRE,a.CRNCY_CODE,a.ship_TERM,to_char(a.T_O_DATE,'dd/mm/yyyy') T_O_DATE,to_char(a.ETD_DATE,'dd/mm/yyyy') ETD_DATE,to_char(a.DOC_SEND,'dd/mm/yyyy') ftpdate,a.AC_HOLDER,a.MERCHANT_NAME,decode(nvl(self_tp,'N'),'N','Bulk','Sampmle') invtype,pre_print_no,sum(qty_endors) invqty,sum(qty_endors*(price_fc+nvl(price_misc,0)))-sum(gr_decl_amt) fob_value,min(to_char(C.tr_date,'dd/mm/yyyy')) pu_date," +
                                " min(to_char(C.tr_date+nvl(DUE_CAL_DAYS,0),'dd/mm/yyyy')) due_date,min(to_char(D.tr_date,'dd/mm/yyyy')) PD1,min(to_char(E.tr_date,'dd/mm/yyyy')) PD2,min(to_char(E.tr_date,'dd/mm/yyyy')) PDA,g.DUE_CAL_DAYS " +
                                " from ei_endors_mast a,ei_endors_dtls b ,ei_truckout_track c,ei_truckout_track d,ei_truckout_track e,ei_truckout_track F,ei_buyer_req_mast G " +
                                " where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and" +
                                " a.year=c.year(+) and a.company=c.company(+) and a.inv_no=c.inv_no(+) and c.tr_type(+)='PU'  and" +
                                " a.year=d.year(+) and a.company=d.company(+) and a.inv_no=d.inv_no(+) and d.tr_type(+)='PD1' and" +
                                " a.year=E.year(+) and a.company=E.company(+) and a.inv_no=E.inv_no(+) and E.tr_type(+)='PD2' and" +
                                " a.year=F.year(+) and a.company=F.company(+) and a.inv_no=F.inv_no(+) and F.tr_type(+)='PDA' and" +
                                " a.LOCATION=? and a.T_O_DATE between ? and ?" +
                                " and a.buyer=G.buyer and g.portal_app='Y' " +query+
                                " group by " +
                                " a.BUYER,a.excs_inv_no,a.DESTI_CNTRY,a.COST_CENTRE,a.CRNCY_CODE,a.ship_TERM,to_char(a.T_O_DATE,'dd/mm/yyyy') ,to_char(a.ETD_DATE,'dd/mm/yyyy'),to_char(a.DOC_SEND,'dd/mm/yyyy'),a.AC_HOLDER,a.MERCHANT_NAME,pre_print_no,g.DUE_CAL_DAYS, " +
                                " decode(nvl(self_tp,'N'),'N','Bulk','Sampmle') " +
                                " order by 1,2               ");
                    stat1.setString(1,SEARCH_LOCT);
                    stat1.setString(2, DATE_FROM);
                    stat1.setString(3,DATE_TO);
                    
                     result1 = stat1.executeQuery(); 
                    while (result1.next()) 
                    { 
                          
                      PUQueryBean Bean=new PUQueryBean();
                        
                        Bean.setBUYER(result1.getString("buyer"));
                        Bean.setEXCS_INV_NO(result1.getString("excs_inv_no"));
                        Bean.setPCH(result1.getString("COST_CENTRE"));
                        Bean.setCRNCY(result1.getString("CRNCY_CODE"));       
                        Bean.setINVTYPE(result1.getString("invtype"));
                        Bean.setPONO(result1.getString("pre_print_no"));
                        Bean.setINVQTY(result1.getDouble("INVQTY"));
                        Bean.setINVFOB(result1.getDouble("fob_value"));
                        Bean.setTODATE(result1.getString("T_O_DATE"));
                        Bean.setETDDATE(result1.getString("ETD_DATE"));
                        Bean.setFTPDATE(result1.getString("ftpdate"));
                        Bean.setPUDATE(result1.getString("pu_date"));
                        Bean.setSUB1(result1.getString("PD1"));
                        Bean.setSUB2(result1.getString("PD2"));  
                        Bean.setDOCSRECVDATE(result1.getString("PDA"));
                        Bean.setDUE_CAL_DAYS(result1.getInt("DUE_CAL_DAYS"));
                        Bean.setDUEDATE((result1.getString("due_date")));
                          ShowDetail.add(Bean);
                         
                     }  
                } // View Flg Close      
            
  
                        
                                
                                
                    
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    
                    System.out.print("1 file name : InvTrackAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : InvTrackAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                    if (result2 != null) {
                        result2.close();
                    }
                    
                    if (result != null) {
                        result.close();
                    }


                    if (stat1 != null) {
                        stat1.close();
                    }
                    if (stat2 != null) {
                        stat2.close();
                    }
                    
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (conndb2 != null) {
                        conndb2.close();
                    }
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    conn = null;
                    conndb2=null;
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : PreInvQueryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
             
            searchinv=null;
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

    public String getSearchinv() {
        return searchinv;
    }

    public void setSearchinv(String searchinv) {
        this.searchinv = searchinv;
    }
 
   

    public List getShowList() {
        return showList;
    }

    public void setShowList(List showList) {
        this.showList = showList;
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

    public String getSEARCH_BUYER() {
        return SEARCH_BUYER;
    }

    public void setSEARCH_BUYER(String SEARCH_BUYER) {
        this.SEARCH_BUYER = SEARCH_BUYER;
    }

    public String getDATE_FROM() {
        return DATE_FROM;
    }

    public void setDATE_FROM(String DATE_FROM) {
        this.DATE_FROM = DATE_FROM;
    }

    public String getDATE_TO() {
        return DATE_TO;
    }

    public void setDATE_TO(String DATE_TO) {
        this.DATE_TO = DATE_TO;
    }

    public List getShowDetail() {
        return ShowDetail;
    }

    public void setShowDetail(List ShowDetail) {
        this.ShowDetail = ShowDetail;
    }

    public String getLOCT() {
        return LOCT;
    }

    public void setLOCT(String LOCT) {
        this.LOCT = LOCT;
    }

    public String getSEARCH_LOCT() {
        return SEARCH_LOCT;
    }

    public void setSEARCH_LOCT(String SEARCH_LOCT) {
        this.SEARCH_LOCT = SEARCH_LOCT;
    }

    public String getSEARCHTYPE() {
        return SEARCHTYPE;
    }

    public void setSEARCHTYPE(String SEARCHTYPE) {
        this.SEARCHTYPE = SEARCHTYPE;
    }

      
             
      
}
