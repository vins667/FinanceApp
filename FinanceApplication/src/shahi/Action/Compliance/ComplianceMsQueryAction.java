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
import shahi.Action.Compliance.Beans.ComplianceMsBean;

/**
 *
 * @author Vivek
 */
public class ComplianceMsQueryAction  extends ActionSupport {
    private String COMPCODE;
    private String COMPNAME;
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
            
            if(result!=null) result.close();
            if(stat!=null) stat.close();
            
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
        String desc="";
        if(COMPCODE != null && COMPCODE.length()>0){
            code=COMPCODE+"%";
        }
        else{
            code="%";
        }
        if(COMPNAME != null && COMPNAME.length()>0){
            desc=COMPNAME+"%";
        }
        else{
            desc="%";
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
            stat = con.prepareStatement("select CMPLCD, CMPLNM, REMDAYS, DEPTCD, FEMAIL, SEMAIL, SUBDEPT from COMPLIANCE_MAST where CMPLCD LIKE ? AND CMPLNM LIKE ? AND DEPTCD=? order by 1");
            stat.setString(1, code);
            stat.setString(2, desc);
            stat.setString(3, deptcode);
            result = stat.executeQuery();
            while (result.next()) {
                detaillist.add(new ComplianceMsBean(result.getString("CMPLCD"), result.getString("CMPLNM"), result.getString("DEPTCD"),result.getString("REMDAYS"), result.getString("FEMAIL"), result.getString("SEMAIL"), result.getString("SUBDEPT")));
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : ComplianceMsQueryAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : ComplianceMsQueryAction.java" + e);

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
                System.out.print("File Name : ComplianceMsQueryAction.java Exception in finally block");
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
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getCOMPCODE() {
        return COMPCODE;
    }

    public void setCOMPCODE(String COMPCODE) {
        this.COMPCODE = COMPCODE;
    }

    public String getCOMPNAME() {
        return COMPNAME;
    }

    public void setCOMPNAME(String COMPNAME) {
        this.COMPNAME = COMPNAME;
    }

    public List getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(List detaillist) {
        this.detaillist = detaillist;
    }
}
