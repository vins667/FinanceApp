package shahi.Action.MvxExp.PRE;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import shahi.Action.database.connection;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import shahi.Action.MvxExp.PRE.dao.BillOfSalesMasterDao;


/**
 *
 * @author Ranjeet Gautam
 */
public class SessionCallAction extends ActionSupport implements ServletRequestAware  {

    private HttpServletRequest servletRequest;
    private String aausrid;
    private List warehouselist=new ArrayList();
    private java.util.Date irfromdate;
    private java.util.Date irtodate;
    private java.util.Date SEARCH_PLAN_DATE;
    private java.util.Date SEARCH_TOPLAN_DATE;
    private String FYBOS="NO";
    @Override
    public String execute() throws Exception {
            
     int flag=0;
        Connection conn = null;
      
        try {
            conn = new connection().getConnection();
            
          
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE=(String)session.get("sessLocationCode");
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
        try {
               stat1=conn.prepareStatement("select * from seh_web_users where USER_ID=?");
               stat1.setString(1,aausrid);
                result1=stat1.executeQuery();
                if(result1.next())
                {
                flag=1;
                session.put("sessUserName", result1.getString("USER_NAME"));
                session.put("sessUserId", result1.getString("USER_ID"));
                session.put("sessLocationCode",result1.getString("LOCATION_CODE"));
                }
                
                          
            stat1=conn.prepareStatement("Select * from pa_auth_mast where prog_name='FYBOS' and user_id=?  ");
            stat1.setString(1, aausrid.trim());
             result1 = stat1.executeQuery();
            if(result1.next())
            {
              FYBOS="YES";
            }else{FYBOS="NO";}
               
                
                
               warehouselist=new BillOfSalesMasterDao().getWareHouseList(aausrid);
               irfromdate=new Date();
               irtodate=new Date();
               SEARCH_PLAN_DATE=new Date();
               SEARCH_TOPLAN_DATE=new Date();
               
         } catch (Exception e) {
            
            System.out.print("1 File Name : SessionCallAction.java" + e);

            System.out.println(e.toString());
        } finally {

            try {

                if (result1 != null) {
                    result1.close();
                }


                if (stat1 != null) {
                    stat1.close();
                }

                if (conn != null) {
                    conn.close();
                }
               
                result1 = null;
                stat1 = null;
                conn = null;

            } catch (Exception e) {
                System.out.print("File Name : SessionCallAction.java Exception in finally block");
                e.printStackTrace();
            }
        }
        // end catch
        if(flag==0)
        {
             addActionMessage("Session not Available !!");
        }
        return SUCCESS;
    }

   public void setServletRequest(HttpServletRequest servletRequest) {

        this.servletRequest = servletRequest;

    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public List getWarehouselist() {
        return warehouselist;
    }

    public void setWarehouselist(List warehouselist) {
        this.warehouselist = warehouselist;
    }

    public Date getIrfromdate() {
        return irfromdate;
    }

    public void setIrfromdate(Date irfromdate) {
        this.irfromdate = irfromdate;
    }

    public Date getIrtodate() {
        return irtodate;
    }

    public void setIrtodate(Date irtodate) {
        this.irtodate = irtodate;
    }

    public Date getSEARCH_PLAN_DATE() {
        return SEARCH_PLAN_DATE;
    }

    public void setSEARCH_PLAN_DATE(Date SEARCH_PLAN_DATE) {
        this.SEARCH_PLAN_DATE = SEARCH_PLAN_DATE;
    }

    public Date getSEARCH_TOPLAN_DATE() {
        return SEARCH_TOPLAN_DATE;
    }

    public void setSEARCH_TOPLAN_DATE(Date SEARCH_TOPLAN_DATE) {
        this.SEARCH_TOPLAN_DATE = SEARCH_TOPLAN_DATE;
    }

    public String getFYBOS() {
        return FYBOS;
    }

    public void setFYBOS(String FYBOS) {
        this.FYBOS = FYBOS;
    }

   
   

}
