package shahi.Action.MvxExp.PRE;
 

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
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.POST.Beans.PostChargeBean;


  

public class PreAjaxAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private String INVOICE_NO_S;
    private String LIC_NO;
    private String AWBDATE_AJ;
    private String SBDATE_AJ;
    private String LETEXP_AJ;
    private String SBNO_AJ;
    private String FACT_S;
    private List   CHRGLIST = new ArrayList();
    
    private String searchdate;
    private String searchterm;
    private String searchawb;
    private String aausrid;
    private ByteArrayInputStream inputStream;
    private String P_YEAR;
    private String P_LINK_NO;
  
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
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        try {

            Connection conn = null;
            Connection connBI = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
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
                {LOCATION_CODE=result1.getString("location_code");
                } 
                         
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : PostAjaxAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : PostAjaxAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                    if (result2 != null) {
                        result2.close();
                    }
                    if (result3 != null) {
                        result3.close();
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
                    if (stat3 != null) {
                        stat3.close();
                    }
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (connBI != null) {
                        connBI.close();
                    }
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    conn = null;
                    connBI=null;
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : PreAjaxAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
           
            searchawb=null;
            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");
             
            return ERROR;
        }
    } 
  
   
       
       
       
      
      
  
  
            ///////////////// Check GSTCode
        public String ajaxGSTCODE() throws SQLException {
        String ls = "";
         
              Connection conn = null;
              PreparedStatement st=null,stat2=null;
              ResultSet rs=null,result2=null;
            try {
                 
                     
                
                if (FACT_S != null && FACT_S.length() > 0) {
                    
                    conn = new connection().getConnection();
                    st = conn.prepareStatement("select OAGEOC,oaadK1 FROM CIADDR_m4off WHERE OACONO=111 AND OAADTH=4 and oaadK1=?");
                    st.setString(1,FACT_S);
                
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls=rs.getString("OAGEOC")+"#"+rs.getString("oaadK1");
                          System.out.println(ls+"good"+FACT_S);  
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        
        return "success";
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

    public List getCHRGLIST() {
        return CHRGLIST;
    }

    public void setCHRGLIST(List CHRGLIST) {
        this.CHRGLIST = CHRGLIST;
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
 
    
    public String getSearchdate() {
        return searchdate;
    }

    public void setSearchdate(String searchdate) {
        this.searchdate = searchdate;
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String getSearchawb() {
        return searchawb;
    }

    public void setSearchawb(String searchawb) {
        this.searchawb = searchawb;
    }

    public String getINVOICE_NO_S() {
        return INVOICE_NO_S;
    }

    public void setINVOICE_NO_S(String INVOICE_NO_S) {
        this.INVOICE_NO_S = INVOICE_NO_S;
    }

    public String getAWBDATE_AJ() {
        return AWBDATE_AJ;
    }

    public void setAWBDATE_AJ(String AWBDATE_AJ) {
        this.AWBDATE_AJ = AWBDATE_AJ;
    }

    

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getLIC_NO() {
        return LIC_NO;
    }

    public void setLIC_NO(String LIC_NO) {
        this.LIC_NO = LIC_NO;
    }

    public String getSBDATE_AJ() {
        return SBDATE_AJ;
    }

    public void setSBDATE_AJ(String SBDATE_AJ) {
        this.SBDATE_AJ = SBDATE_AJ;
    }

    public String getSBNO_AJ() {
        return SBNO_AJ;
    }

    public void setSBNO_AJ(String SBNO_AJ) {
        this.SBNO_AJ = SBNO_AJ;
    }

    public String getLETEXP_AJ() {
        return LETEXP_AJ;
    }

    public void setLETEXP_AJ(String LETEXP_AJ) {
        this.LETEXP_AJ = LETEXP_AJ;
    }

    public String getP_YEAR() {
        return P_YEAR;
    } 

    public void setP_YEAR(String P_YEAR) {
        this.P_YEAR = P_YEAR;
    }

    public String getP_LINK_NO() {
        return P_LINK_NO;
    }

    public void setP_LINK_NO(String P_LINK_NO) {
        this.P_LINK_NO = P_LINK_NO;
    }

    public String getFACT_S() {
        return FACT_S;
    }

    public void setFACT_S(String FACT_S) {
        this.FACT_S = FACT_S;
    }
     
      
}
  