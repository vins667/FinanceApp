package shahi.Action.budget;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import shahi.Action.database.connection;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;


/**
 *
 * @author Ranjeet Gautam
 */
public class SessionCallAction extends ActionSupport implements ServletRequestAware  {

    private Date todayDate;
    private HttpServletRequest servletRequest;
    private String aausrid;
    private String FABRCODE;
    private String SUBSTRATCODE;
    private String RECDDATE;
    private String BUYERCODE;
    private String SAMPLEID;
    
    public String execute() throws Exception {
         setTodayDate(new Date());
       
     int flag=0;
        Connection conn = null;
        Connection connbi = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

            Map session = ActionContext.getContext().getSession();
            

       String LOCATION_CODE=(String)session.get("sessLocationCode");
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
        try {
 //aausrid="227351";

//System.out.println("session - aausrid - "+aausrid+" - "+FABRCODE);
            stat1 = conn.prepareStatement("select * from seh_web_users where USER_ID=?");
            stat1.setString(1, aausrid);
            result1 = stat1.executeQuery();
            if (result1.next()) {
                flag = 1;
                session.put("sessUserName", result1.getString("USER_NAME"));
                session.put("sessUserId", result1.getString("USER_ID"));
                session.put("sessLocationCode", result1.getString("LOCATION_CODE"));
            }



          conn.commit();
        } catch (Exception e) {
            try {

                conn.rollback();

            } catch (Exception ee) {
                System.out.print("1 File Name : SessionCallAction.java" + ee);

                System.out.println(ee.toString());
            }
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
       // if(flag==0)
       // {
        //   addActionMessage("System Error !!");
       // }
        return SUCCESS;
    }

  


    public Date getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
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

    public String getFABRCODE() {
        return FABRCODE;
    }

    public void setFABRCODE(String FABRCODE) {
        this.FABRCODE = FABRCODE;
    }

    public String getSUBSTRATCODE() {
        return SUBSTRATCODE;
    }

    public void setSUBSTRATCODE(String SUBSTRATCODE) {
        this.SUBSTRATCODE = SUBSTRATCODE;
    }

    public String getBUYERCODE() {
        return BUYERCODE;
    }

    public void setBUYERCODE(String BUYERCODE) {
        this.BUYERCODE = BUYERCODE;
    }

    public String getRECDDATE() {
        return RECDDATE;
    }

    public void setRECDDATE(String RECDDATE) {
        this.RECDDATE = RECDDATE;
    }

    public String getSAMPLEID() {
        return SAMPLEID;
    }

    public void setSAMPLEID(String SAMPLEID) {
        this.SAMPLEID = SAMPLEID;
    }
}
