package shahi.Action.M4bill;

import shahi.Action.FabricCoding.*;
import shahi.Action.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import shahi.Action.database.connection;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import shahi.Action.database.ConnectionMovexBi;


/**
 *
 * @author Ranjeet Gautam
 */
public class SessionCallAction extends ActionSupport implements ServletRequestAware  {

    private Date todayDate;
    private HttpServletRequest servletRequest;
    private String aausrid;
    private List unitlist;
    private String typename="1";
    private String ACCESS_FLAG="No";



    @Override
    public String execute() throws Exception {
         setTodayDate(new Date());
         unitlist=new ArrayList();
     int flag=0;
        Connection conn = null;
        Connection connbi = null;
        try {
            conn = new connection().getConnection();
             connbi = new ConnectionMovexBi().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

            Map session = ActionContext.getContext().getSession();
            //session.put("sessUserName", "ranjeet");
            //session.put("sessUserId", "228570");
           // session.put("sessLocationCode", "100");

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
            
            
                stat1 = conn.prepareStatement("select distinct ACCESS_FLAG from M3_BILL_USER_MASTER where EMP_CODE=? and ACCESS_FLAG='Y'");
                stat1.setString(1, result1.getString("USER_ID"));
               ResultSet result2=stat1.executeQuery();
                if(result2.next())
                {
                ACCESS_FLAG="Yes";
                }
                if(result2!=null)
                {
                result2.close();
                }
                
                if(stat1!=null)
                {
                stat1.close();
                }
                }
           
         if(flag==1)
         {

        
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
                 if (conn != null) {
                    connbi.close();
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
        addActionMessage("System Error !!");
        }
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

    public List getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List unitlist) {
        this.unitlist = unitlist;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getACCESS_FLAG() {
        return ACCESS_FLAG;
    }

    public void setACCESS_FLAG(String ACCESS_FLAG) {
        this.ACCESS_FLAG = ACCESS_FLAG;
    }


    
}
