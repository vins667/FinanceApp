package shahi.Action.Compliance;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import shahi.Action.database.connection;
import java.util.*;

/**
 *
 * @author Vivek
 */
public class ComplianceMsAction extends ActionSupport {

    private String aausrid;
    private String COMPCD;
    private String COMPNAME;
    private String COMPDAYS;
    private String COMPFMAIL;
    private String COMPSMAIL;
    private String COMPDEPT;
    private String COMPFLAG;
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
            stat = conn.prepareStatement("select CMPLCD,CMPLNM,REMDAYS,FEMAIL,SEMAIL,SUBDEPT,ACTFLG from COMPLIANCE_MAST where trim(CMPLCD)=?");
            stat.setString(1, COMPCD.trim());
            result = stat.executeQuery();
            while (result.next()) {
                COMPNAME=result.getString("CMPLNM");
                COMPDAYS=result.getString("REMDAYS");
                COMPDEPT=result.getString("SUBDEPT");
                COMPFMAIL=result.getString("FEMAIL");
                COMPSMAIL=result.getString("SEMAIL");
                COMPFLAG=result.getString("ACTFLG");
            }           
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : ComplianceMsAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : ComplianceMsAction.java" + e);

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
                System.out.print("File Name : ComplianceMsAction.java Exception in finally block");
                e.printStackTrace();
            }
        }
        // end catch
        if (flag == 0) {
            // addActionMessage("2.Record Not Found !!");
        }
        return SUCCESS;
    }

    public String update(){
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
            stat = conn.prepareStatement("update COMPLIANCE_MAST set REMDAYS=?,FEMAIL=?,SEMAIL=?,SUBDEPT=?,ACTFLG=? where trim(CMPLCD)=? AND trim(CMPLNM)=? AND DEPTCD =?");
            stat.setString(1, COMPDAYS);
            stat.setString(2, COMPFMAIL);
            stat.setString(3, COMPSMAIL);
            stat.setString(4, COMPDEPT);
            stat.setString(5, COMPFLAG);
            stat.setString(6, COMPCD.trim());
            stat.setString(7, COMPNAME.trim());
            stat.setString(8, deptcode);
            int status = stat.executeUpdate();
            if(status>0){
                addActionError("Record updated");
            }
            else{
                addActionError("Record not updated");
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : ComplianceMsAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : ComplianceMsAction.java" + e);

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
                System.out.print("File Name : ComplianceMsAction.java Exception in finally block");
                e.printStackTrace();
            }
        }
        // end catch
        if (flag == 0) {
            // addActionMessage("2.Record Not Found !!");
        }
        return SUCCESS;
    }

    public String getCOMPCD() {
        return COMPCD;
    }

    public void setCOMPCD(String COMPCD) {
        this.COMPCD = COMPCD;
    }

    public String getCOMPDAYS() {
        return COMPDAYS;
    }

    public void setCOMPDAYS(String COMPDAYS) {
        this.COMPDAYS = COMPDAYS;
    }

    public String getCOMPDEPT() {
        return COMPDEPT;
    }

    public void setCOMPDEPT(String COMPDEPT) {
        this.COMPDEPT = COMPDEPT;
    }

    public String getCOMPFLAG() {
        return COMPFLAG;
    }

    public void setCOMPFLAG(String COMPFLAG) {
        this.COMPFLAG = COMPFLAG;
    }

    public String getCOMPFMAIL() {
        return COMPFMAIL;
    }

    public void setCOMPFMAIL(String COMPFMAIL) {
        this.COMPFMAIL = COMPFMAIL;
    }

    public String getCOMPNAME() {
        return COMPNAME;
    }

    public void setCOMPNAME(String COMPNAME) {
        this.COMPNAME = COMPNAME;
    }

    public String getCOMPSMAIL() {
        return COMPSMAIL;
    }

    public void setCOMPSMAIL(String COMPSMAIL) {
        this.COMPSMAIL = COMPSMAIL;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }
    
    
}
