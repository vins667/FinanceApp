package shahi.Action.Compliance;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import shahi.Action.database.connection;
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author Vivek
 */
public class ComplianceMsSaveAction extends ActionSupport {

    private String aausrid;
    private List COMP_CODE;
    private List COMP_NAME;
    private List COMP_DAYS;
    private List COMP_FMAIL;
    private List COMP_SMAIL;
    private List COMP_DEPT;

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String deptcode = (String) session.get("sessDeptCode");
        if(usrid==null && deptcode==null){
            addActionError("Session is not Available");
            return ERROR;
        }
        int flag = 0;
        Connection conn = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            if (COMP_CODE != null && COMP_CODE.size() > 0) {
                for(int i=0;i<COMP_CODE.size();i++){
                    if(COMP_CODE.get(i).toString()!=null && COMP_CODE.get(i).toString().length()>0){
                        stat = conn.prepareStatement("select * from COMPLIANCE_MAST where trim(CMPLCD)=?");
                        stat.setString(1, COMP_CODE.get(i).toString().toUpperCase());
                        result = stat.executeQuery();
                        if (result.next()) {
                            addActionError("Comp Code already exist "+COMP_CODE.get(i).toString());
                        }
                        else{
                            stat=conn.prepareStatement("INSERT INTO COMPLIANCE_MAST(CMPLCD,CMPLNM,SEH_USER,TDATE,REMDAYS,DEPTCD,FEMAIL,SEMAIL,SUBDEPT,ACTFLG) VALUES(?,?,?,sysdate,?,?,?,?,?,'Y')");
                            stat.setString(1, COMP_CODE.get(i).toString().toUpperCase());
                            stat.setString(2, COMP_NAME.get(i).toString().toUpperCase());
                            stat.setString(3, usrid);
                            stat.setString(4, COMP_DAYS.get(i).toString());
                            stat.setString(5, deptcode);
                            stat.setString(6, COMP_FMAIL.get(i).toString().toUpperCase());
                            stat.setString(7, COMP_SMAIL.get(i).toString().toUpperCase());
                            stat.setString(8, COMP_DEPT.get(i).toString());
                            stat.executeUpdate();
                        }
                    }
                }
            }
            addActionError("Record saved");
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : ComplianceMsSaveAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : ComplianceMsSaveAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
                result = null;
                stat = null;
                conn = null;

            } catch (Exception e) {
                System.out.print("File Name : ComplianceMsSaveAction.java Exception in finally block");
                e.printStackTrace();
            }
        }
        COMP_CODE   = null;
        COMP_NAME   = null;
        COMP_DAYS   = null;
        COMP_DEPT   = null;
        COMP_FMAIL  = null;
        COMP_SMAIL  = null;
        return SUCCESS;
    }

    public List getCOMP_CODE() {
        return COMP_CODE;
    }

    public void setCOMP_CODE(List COMP_CODE) {
        this.COMP_CODE = COMP_CODE;
    }

    public List getCOMP_DAYS() {
        return COMP_DAYS;
    }

    public void setCOMP_DAYS(List COMP_DAYS) {
        this.COMP_DAYS = COMP_DAYS;
    }

    public List getCOMP_DEPT() {
        return COMP_DEPT;
    }

    public void setCOMP_DEPT(List COMP_DEPT) {
        this.COMP_DEPT = COMP_DEPT;
    }

    public List getCOMP_FMAIL() {
        return COMP_FMAIL;
    }

    public void setCOMP_FMAIL(List COMP_FMAIL) {
        this.COMP_FMAIL = COMP_FMAIL;
    }

    public List getCOMP_NAME() {
        return COMP_NAME;
    }

    public void setCOMP_NAME(List COMP_NAME) {
        this.COMP_NAME = COMP_NAME;
    }

    public List getCOMP_SMAIL() {
        return COMP_SMAIL;
    }

    public void setCOMP_SMAIL(List COMP_SMAIL) {
        this.COMP_SMAIL = COMP_SMAIL;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }
    
}
