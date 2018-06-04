/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.Compliance;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shahi.Action.database.connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.Compliance.Beans.ComplianceDeptBean;
import shahi.Action.Compliance.Beans.ComplianceMsBean;

/**
 *
 * @author Vivek
 */
public class ComplianceDeptQueryAction  extends ActionSupport {
    private String DEPTCODE;
    private String aausrid;
    private List detaillist;

    public String makesession() throws SQLException{
        Map session = ActionContext.getContext().getSession();        
        Connection conn=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            stat = conn.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE,DEPT_CODE from EMPLOYEE_VIEW where emp_code=?");
            stat.setString(1, aausrid);
            result = stat.executeQuery();
            if (result.next()) {
                session.put("sessUserName", result.getString("EMP_NAME"));
                session.put("sessUserId", result.getString("emp_code"));
                session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                session.put("sessUnitCode", result.getString("UNIT_CODE"));
                session.put("sessDeptCode", result.getString("DEPT_CODE"));
            }
        } catch (Exception e) {
            System.out.println("SupplierPaymentUploadAction " + e);
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
        return SUCCESS;
    }
    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String deptcode = (String) session.get("sessDeptCode");
        if(usrid==null && deptcode==null){
            addActionError("Session is not Available");
            return ERROR;
        }
        detaillist = new ArrayList();  
        int flag = 0;
        String code="";        
        if(DEPTCODE != null && DEPTCODE.length()>0){
            code=DEPTCODE+"%";
        }
        else{
            code="%";
        }
        
        Connection con = null;
        try {
            con = new connection().getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            stat = con.prepareStatement("select SUBDEPT,FEMAIL,SEMAIL from COMPLIANCE_DEPT where SUBDEPT LIKE ? order by 1");
            stat.setString(1, code);            
            result = stat.executeQuery();
            while (result.next()) {
                detaillist.add(new ComplianceDeptBean(result.getString("SUBDEPT"), result.getString("FEMAIL"), result.getString("SEMAIL")));
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : ComplianceDeptQueryAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : ComplianceDeptQueryAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }                
                if (con != null) {
                    con.close();
                }
                result = null;
                stat = null;                
                con = null;

            } catch (Exception e) {
                System.out.print("File Name : ComplianceDeptQueryAction.java Exception in finally block");
                e.printStackTrace();
            }
        }
        // end catch        
        if (detaillist.isEmpty()) {
            addActionMessage("Record Not Found !!");
        }
        return SUCCESS;
    }

    public String newpage() {        
        return "newpage";
    }

    public String backpage() {
        return "backpage";
    }

    public String getDEPTCODE() {
        return DEPTCODE;
    }

    public void setDEPTCODE(String DEPTCODE) {
        this.DEPTCODE = DEPTCODE;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public List getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(List detaillist) {
        this.detaillist = detaillist;
    }
    
}
