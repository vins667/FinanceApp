package shahi.Action.MvxExp.POST;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat; 
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import shahi.Action.MvxExp.POST.Beans.ByrPortalListBean;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class ByrPrtlDlyUpdtionAction extends ActionSupport {

    private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private String INVOICE_S; 
    private String YEAR;
    private String COMPANY;
    private String INVOICENO;
    private String INVOICEDATE;
      private String BUYER;
    private String BUYER_DESC;
    private String BUYER_ADDR;
    
    private String aausrid;
    
    private ByteArrayInputStream inputStream;
    private List listdata = new ArrayList();
    private String SEARCH_CODE;
    private String PLANNO_N;
    private String TTO_DATE;
    private String CNTRY;
    private String TO_DATE;
    private String FIN_DATE;
    private String INV_QTY;
    private String SHIP_QTY;
    private String INVNO;
    private String LOCATION;

    private List ByrPortalList = new ArrayList();
   
    private List SearchList=new ArrayList();
       
     private List REASON_L;
     private List ENDDATE_L;
     private List ENDUSER_L;
     private String FLAG1;
     private List REASON_L1;
     private List ENDDATE_L1;
     private List ENDUSER_L1;
     
     
    
     
     
     
    @Override
    public String execute() throws SQLException {
        Connection conn = null;
        PreparedStatement stat2 = null;
                ResultSet result2 = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch  
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
            
                stat2 = conn.prepareStatement("select type_desc,type_code,type_code||'--'||type_desc remrk from ei_grup_type_dtls where grup_type_code='PUR' order by 1");
                result2 = stat2.executeQuery();
                while (result2.next()) {
                   
                    SearchList.add(new GetListBean(result2.getString("type_desc"),result2.getString("type_desc")));  
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
            
             
            if (FLAG1.equals("Yes") && FLAG1.length() > 0) {
                stat2 = conn.prepareStatement("select a.EXCS_INV_NO,to_char(a.INV_DATE,'yyyy-MM-dd') INV_DATE,a.COMPANY,a.PLAN_NO,a.BUYER,a.BUYER_ADDR,to_char(a.TTO_DATE,'yyyy-MM-dd') TTO_DATE,a.DESTI_CNTRY,to_char(a.T_O_DATE,'yyyy-MM-dd') T_O_DATE,to_char(a.FIN_DATE,'yyyy-MM-dd') fin_date,a.INV_QTY,a.SHIP_QTY,a.BUYER_ADDR,a.year,a.LOCATION from ei_endors_mast a  where a.EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                if (result2.next()) {

                    INVOICENO = result2.getString("EXCS_INV_NO");
                    INVOICEDATE = result2.getString("INV_DATE");
                    COMPANY = result2.getString("COMPANY");
                    PLANNO_N = result2.getString("PLAN_NO");
                    BUYER = result2.getString("BUYER");
                    BUYER_DESC = result2.getString("BUYER_ADDR");
                    TTO_DATE = result2.getString("TTO_DATE");
                    CNTRY = result2.getString("DESTI_CNTRY");
                    TO_DATE = result2.getString("T_O_DATE");
                    FIN_DATE=result2.getString("fin_date");
                    INV_QTY = result2.getString("INV_QTY");
                    SHIP_QTY = result2.getString("SHIP_QTY");
                    YEAR = result2.getString("year");
                    LOCATION = result2.getString("LOCATION");
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }

                
                stat2 = conn.prepareStatement("select a.REASON_CODE,a.REMARKS,to_char(a.TDATE,'dd/mm/yyyy hh24:mi') tdate,a.SEH_USER from EI_BUYER_PORTAL_REASON a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and trim(b.EXCS_INV_NO) =? order by a.REMARKS");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                int p=0;
                while (result2.next()) {
                    ++p;
                    
                    ByrPortalList.add(new ByrPortalListBean(result2.getString("REMARKS"),result2.getString("TDATE"),result2.getString("SEH_USER")));  
                }
                   if(p==0) {
                    REASON_L = null;
                    ENDDATE_L = null;
                    ENDUSER_L = null;
                    
                   }
                    REASON_L = null;
                    ENDDATE_L = null;
                    ENDUSER_L = null;
                    
                   
                
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                }
            
                
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
              conn.close();
            }
        }


        return SUCCESS;
    }

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
         if(usrid==null)
        {
           session.put("sessUserId",aausrid); 
           usrid=aausrid;
        }

        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        
         SimpleDateFormat fromUser  = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat myFormat  = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0, z = 0;
            String newno = "";
            String FINYR = "";
            String vex = "";
            String t1 = "";
            String t2 = "";
            String yr = null;
            String comp = null;
            String invn = null;
            String byr = null;
            String byradd = null;
            String loc = null;
           
               stat5 = conn.prepareStatement("select year,COMPANY,INV_NO from  ei_endors_mast where EXCS_INV_NO=?");
                stat5.setString(1, INVOICE_S);
                result5 = stat5.executeQuery();
                if (result5.next()) {
                    yr = result5.getString("year");
                    comp = result5.getString("COMPANY");
                    invn = result5.getString("INV_NO");
                }
                
                int j=0;
                for (int i = 0; i < REASON_L.size(); i++) {
                    if (REASON_L != null && REASON_L.get(i).toString().length() > 0) {
                         

                            stat4 = conn.prepareStatement("insert into   EI_BUYER_PORTAL_REASON(REMARKS,TDATE,inv_no,year,company,SEH_USER,grup_code) values(?,trunc(sysdate),?,?,?,?,'PU')");
                            stat4.setString(1, REASON_L.get(i).toString());
                            stat4.setString(2, invn);
                            stat4.setString(3, yr);
                            stat4.setString(4, comp);
                            stat4.setString(5, usrid);
                            j = stat4.executeUpdate();
                            if (j > 0) {
                                ++j;
                                conn.commit();
                            }
 
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result != null) {
                    result.close();
                }
                
                
                if (j > 0) {
                    addActionMessage("Record inserted succcessfully ");

                    REASON_L = null;
                    ENDDATE_L = null;
                    ENDUSER_L = null;
                   // INVOICE_S = null;

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

       
         FLAG1="Yes";
         execute();

        return "updte";
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

    

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }


    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List getListdata() {
        return listdata;
    }

    public void setListdata(List listdata) {
        this.listdata = listdata;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public String getINVOICE_S() {
        return INVOICE_S;
    }

    public void setINVOICE_S(String INVOICE_S) {
        this.INVOICE_S = INVOICE_S;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getINVOICENO() {
        return INVOICENO;
    }

    public void setINVOICENO(String INVOICENO) {
        this.INVOICENO = INVOICENO;
    }

    public String getINVOICEDATE() {
        return INVOICEDATE;
    }

    public void setINVOICEDATE(String INVOICEDATE) {
        this.INVOICEDATE = INVOICEDATE;
    }
 

    public void setServletRequest(HttpServletRequest servletRequest) {

        this.servletRequest = servletRequest;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getBUYER_DESC() {
        return BUYER_DESC;
    }

    public void setBUYER_DESC(String BUYER_DESC) {
        this.BUYER_DESC = BUYER_DESC;
    }

    public String getBUYER_ADDR() {
        return BUYER_ADDR;
    }

    public void setBUYER_ADDR(String BUYER_ADDR) {
        this.BUYER_ADDR = BUYER_ADDR;
    }

    public String getPLANNO_N() {
        return PLANNO_N;
    }

    public void setPLANNO_N(String PLANNO_N) {
        this.PLANNO_N = PLANNO_N;
    }

    public String getTTO_DATE() {
        return TTO_DATE;
    }

    public void setTTO_DATE(String TTO_DATE) {
        this.TTO_DATE = TTO_DATE;
    }

    public String getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(String TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public String getFIN_DATE() {
        return FIN_DATE;
    }

    public void setFIN_DATE(String FIN_DATE) {
        this.FIN_DATE = FIN_DATE;
    }

    

    public String getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(String INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public String getSHIP_QTY() {
        return SHIP_QTY;
    }

    public void setSHIP_QTY(String SHIP_QTY) {
        this.SHIP_QTY = SHIP_QTY;
    }

    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    
    public List getSearchList() {
        return SearchList;
    }

    public void setSearchList(List SearchList) {
        this.SearchList = SearchList;
    }

    

    public String getCNTRY() {
        return CNTRY;
    }

    public void setCNTRY(String CNTRY) {
        this.CNTRY = CNTRY;
    }

    
    public List getITMID_L() {
        return REASON_L;
    }

    public void setITMID_L(List REASON_L) {
        this.REASON_L = REASON_L;
    }

    public List getITMIDDESC_L() {
        return ENDDATE_L;
    }

    public void setITMIDDESC_L(List ENDDATE_L) {
        this.ENDDATE_L = ENDDATE_L;
    }

    public List getPCKGES_L() {
        return ENDUSER_L;
    }

    public void setPCKGES_L(List ENDUSER_L) {
        this.ENDUSER_L = ENDUSER_L;
    }

   
 
    public List getByrPortalList() {
        return ByrPortalList;
    }

    public void setByrPortalList(List ByrPortalList) {
        this.ByrPortalList = ByrPortalList;
    }

    public List getREASON_L() {
        return REASON_L;
    }

    public void setREASON_L(List REASON_L) {
        this.REASON_L = REASON_L;
    }

    public List getENDDATE_L() {
        return ENDDATE_L;
    }

    public void setENDDATE_L(List ENDDATE_L) {
        this.ENDDATE_L = ENDDATE_L;
    }

    public List getENDUSER_L() {
        return ENDUSER_L;
    }

    public void setENDUSER_L(List ENDUSER_L) {
        this.ENDUSER_L = ENDUSER_L;
    }

    public List getREASON_L1() {
        return REASON_L1;
    }

    public void setREASON_L1(List REASON_L1) {
        this.REASON_L1 = REASON_L1;
    }

    public List getENDDATE_L1() {
        return ENDDATE_L1;
    }

    public void setENDDATE_L1(List ENDDATE_L1) {
        this.ENDDATE_L1 = ENDDATE_L1;
    }

    public List getENDUSER_L1() {
        return ENDUSER_L1;
    }

    public void setENDUSER_L1(List ENDUSER_L1) {
        this.ENDUSER_L1 = ENDUSER_L1;
    }

    public String getFLAG1() {
        return FLAG1;
    }

    public void setFLAG1(String FLAG1) {
        this.FLAG1 = FLAG1;
    }

    

     
  

    

   
}