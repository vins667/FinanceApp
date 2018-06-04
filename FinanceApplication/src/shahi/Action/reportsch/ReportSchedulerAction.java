/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.reportsch;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHris;
import shahi.Action.reportsch.beans.ReportSchedulerBeans;
import shahi.Action.reportsch.beans.UploadBean;

/**
 *
 * @author Vivek
 */
public class ReportSchedulerAction extends ActionSupport {

    private String JOBNAME;
    private String FILENAME;
    private String JOBDESC;
    private String STARTDT;
    private String CKDTPRF;
    private String CHMNT;
    private List MNTLST;
    private String CHDAY;
    private List DAYLST;
    private String CHMDTS;
    private String CHHRS;
    private String CHMINTS;
    private String OUTFRMT;
    private String EMAILTO;
    private String EMAILCC;
    private String EMAILBCC;
    private String EMAILSUB;
    private String EMAILBODY;
    private String CONNTYPE;
    private String TRIGGER_NAME;
    private String aausrid;
    private String REPORT_NAME_SER;
    private String TRIGGER_NAME_SER;
    private String QUERYCHK;
    private List DETAIL_LIST;
    private String TSRNO;
    private String TCODE;
    private List chkdel;
    private String FOLDER_ID;
    private List UP_LIST;
    /*private static final DateFormat TWELVE_TF = new SimpleDateFormat("hh:mma");
     private static final DateFormat TWENTY_FOUR_TF = new SimpleDateFormat("HH:mm");
    
    
     public static String convertTo24HoursFormat(String twelveHourTime) throws ParseException {
     return TWENTY_FOUR_TF.format(
     TWELVE_TF.parse(twelveHourTime));
     }*/
    public void lstreport() throws SQLException{
        UP_LIST = new ArrayList();
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement statsr = null;
        ResultSet resultsr=null;
        try {
            conn = new ConnectionShahiHris().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            addActionError("ReportSchedulerAction qry(): " + e);
        }
        try{            
        statsr = conn.prepareStatement("SELECT DISTINCT SRNO,REPORT_NAME,FILE_NAME FROM REPORT_SCHEDULER_UPLOAD where ACTIVE='Y' order by SRNO");
            resultsr = statsr.executeQuery();
            while(resultsr.next()){
                UP_LIST.add(new UploadBean(resultsr.getString("FILE_NAME"), resultsr.getString("REPORT_NAME")));
            }
        }
        catch(SQLException se){
            addActionError(se.getMessage());
        }
        catch(Exception e){
            addActionError(e.getMessage());
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
    }
    public String view() throws SQLException, Exception {
        chkdel=null;
        Map session = ActionContext.getContext().getSession();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
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
            conn.commit();
        } catch (Exception e) {
            System.out.println("ReportSchedulerAction view(): " + e);
            addActionError("ReportSchedulerAction view(): "+e.getMessage());
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return "qry";
    }
    
    public String qry() throws SQLException{
        chkdel=null;
        DETAIL_LIST = new ArrayList();
        Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        String LOCATION = (String) session.get("sessLocationCode");
        if (USER_ID == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement statsr = null;
        ResultSet resultsr=null;
        try {
            conn = new ConnectionShahiHris().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            addActionError("ReportSchedulerAction qry(): " + e);
        }

        try {
            String qry=" ";
        
            if(REPORT_NAME_SER!=null && REPORT_NAME_SER.length()>0){
                qry+=" and REPORT_NAME like '"+REPORT_NAME_SER+"%'";
            }
            
            if(TRIGGER_NAME_SER!=null && TRIGGER_NAME_SER.length()>0){
                qry+=" and TRIGGER_NAME like '"+TRIGGER_NAME_SER+"%'";
            }
            
            statsr = conn.prepareStatement("SELECT DISTINCT SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,TRIGGER_NAME,EMAIL_ID,RESULT_TYPE,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,CONNECTION_TYPE,to_number(to_char(TDATE,'dd')) TDATE,to_number(to_char(TTIME,'HH24')) THOUR,to_number(to_char(TTIME,'MI')) TMINUTE,QUERYCHK FROM REPORT_SCHEDULER where 1=1 "+qry +" order by SRNO");
            resultsr = statsr.executeQuery();
            while(resultsr.next()){
                DETAIL_LIST.add(new ReportSchedulerBeans(resultsr.getString("SRNO"), resultsr.getString("REPORT_TYPE"), resultsr.getString("REPORT_NAME"), resultsr.getString("FILE_NAME"), resultsr.getString("TRIGGER_NAME"), resultsr.getString("EMAIL_ID"), resultsr.getString("RESULT_TYPE"), resultsr.getString("SUBJECT"), resultsr.getString("BODY_CONTENT"), resultsr.getString("CC_EMAIL_ID"), resultsr.getString("BCC_EMAIL_ID"), resultsr.getString("CONNECTION_TYPE"), resultsr.getString("TDATE"), resultsr.getString("THOUR"), resultsr.getString("TMINUTE"), resultsr.getString("QUERYCHK")));
            }
        }
        catch(SQLException se){
            addActionError(se.getMessage());
        }
        catch(Exception e){
            addActionError(e.getMessage());
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
        return "qry";
    }
    
    @Override
    public String execute() throws Exception {
        JOBNAME=null;
        FILENAME=null;
        JOBDESC=null;
        STARTDT=null;
        CKDTPRF=null;
        CHMNT=null;
        MNTLST=null;
        CHDAY=null;
        DAYLST=null;
        CHMDTS=null;
        CHHRS=null;
        CHMINTS=null;
        OUTFRMT=null;
        EMAILTO=null;
        EMAILCC=null;
        EMAILBCC=null;
        EMAILSUB=null;
        EMAILBODY=null;
        CONNTYPE=null;
        TRIGGER_NAME=null;
        REPORT_NAME_SER=null;
        TRIGGER_NAME_SER=null;
        DETAIL_LIST=null;
        TSRNO=null;
        TCODE=null;
        chkdel=null;
        lstreport();
        return SUCCESS;
    }   
    
    public String resetq() throws Exception {
        JOBNAME=null;
        FILENAME=null;
        JOBDESC=null;
        STARTDT=null;
        CKDTPRF=null;
        CHMNT=null;
        MNTLST=null;
        CHDAY=null;
        DAYLST=null;
        CHMDTS=null;
        CHHRS=null;
        CHMINTS=null;
        OUTFRMT=null;
        EMAILTO=null;
        EMAILCC=null;
        EMAILBCC=null;
        EMAILSUB=null;
        EMAILBODY=null;
        CONNTYPE=null;
        TRIGGER_NAME=null;
        REPORT_NAME_SER=null;
        TRIGGER_NAME_SER=null;
        DETAIL_LIST=null;
        TSRNO=null;
        TCODE=null;
        chkdel=null;
        return "qry";
    }   
    

    public String edit() throws SQLException{
        chkdel=null;
        if(TSRNO!=null && TSRNO.length()>0){
            DAYLST = new ArrayList();
            Map session = ActionContext.getContext().getSession();
            String USER_ID = (String) session.get("sessUserId");
            String LOCATION = (String) session.get("sessLocationCode");
            if (USER_ID == null) {
                addActionError("Session Not Valid!!!");
                return ERROR;
            }
            Connection conn = null;
            PreparedStatement stat = null;
            PreparedStatement statsr = null;
            ResultSet result=null;
            ResultSet resultsr=null;
            try {
                conn = new ConnectionShahiHris().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                addActionError("ReportSchedulerAction edit(): " + e);
            }

            try {
                statsr = conn.prepareStatement("SELECT DISTINCT SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,TRIGGER_NAME,EMAIL_ID,RESULT_TYPE,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,CONNECTION_TYPE,to_number(to_char(TDATE,'dd')) TDATE,to_number(to_char(TTIME,'HH24')) THOUR,to_number(to_char(TTIME,'MI')) TMINUTE,QUERYCHK FROM REPORT_SCHEDULER where SRNO=?");
                statsr.setString(1, TSRNO);
                resultsr = statsr.executeQuery();
                while(resultsr.next()){                    
                    JOBNAME = resultsr.getString("REPORT_NAME");
                    FILENAME = resultsr.getString("FILE_NAME");
                    TCODE = resultsr.getString("REPORT_TYPE");
                    if(TCODE!=null && (TCODE.equals("WD")||TCODE.equals("WS"))){
                        stat = conn.prepareStatement("SELECT TDAY FROM REPORT_SCHEDULER where SRNO=?");
                        stat.setString(1, TSRNO);
                        result = stat.executeQuery();
                        while(result.next()){
                            DAYLST.add(result.getInt("TDAY")+"");
                        }
                    }
                    if(TCODE!=null && TCODE.equals("FD")){
                        CHMDTS = resultsr.getString("TDATE");
                    }
                    if(TCODE!=null &&  (TCODE.equals("WD")||TCODE.equals("WS")||TCODE.equals("FD")||TCODE.equals("FS")||TCODE.equals("ED")||TCODE.equals("ES"))){
                        CHHRS = resultsr.getInt("THOUR")+""; 
                        CHMINTS = resultsr.getInt("TMINUTE")+""; 
                    }
                    OUTFRMT = resultsr.getString("RESULT_TYPE");
                    CONNTYPE = resultsr.getString("CONNECTION_TYPE");
                    EMAILTO = resultsr.getString("EMAIL_ID");
                    EMAILCC = resultsr.getString("CC_EMAIL_ID");
                    EMAILBCC = resultsr.getString("BCC_EMAIL_ID");
                    EMAILSUB = resultsr.getString("SUBJECT");
                    EMAILBODY = resultsr.getString("BODY_CONTENT");
                    TRIGGER_NAME = resultsr.getString("TRIGGER_NAME");
                    QUERYCHK = resultsr.getString("QUERYCHK");
                }
            }
            catch(SQLException se){
                addActionError(se.getMessage());
            }
            catch(Exception e){
                addActionError(e.getMessage());
            }
            finally{
                if(conn!=null){
                    conn.close();
                }
            }
        }
        lstreport();
        return "edit";
    }
    public String del() throws SQLException{
        if(chkdel!=null && chkdel.size()>0){
            Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        String LOCATION = (String) session.get("sessLocationCode");
        if (USER_ID == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection conn = null;
        PreparedStatement stat = null;
        try {
                conn = new ConnectionShahiHris().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                addActionError("ReportSchedulerAction del(): " + e);
            }
         try {
             for(int i=0;i<chkdel.size();i++){
                stat = conn.prepareStatement("DELETE REPORT_SCHEDULER where SRNO=?");
                stat.setString(1, chkdel.get(i).toString());
                stat.executeUpdate();         
             }
            conn.commit();
        } catch (Exception e) {
            addActionError("ReportSchedulerAction del(): " + e);
            conn.rollback();
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
        }
        return "qry";
    }
    public String save() throws SQLException {
        chkdel=null;
        int srno=0;
        Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        String LOCATION = (String) session.get("sessLocationCode");
        if (USER_ID == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement statsr = null;
        ResultSet resultsr=null;
        try {
            conn = new ConnectionShahiHris().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            addActionError("ReportSchedulerAction save(): " + e);
        }

        try {
            if(TSRNO!=null && TSRNO.length()>0){
                stat = conn.prepareStatement("DELETE REPORT_SCHEDULER where SRNO=?");
                stat.setString(1, TSRNO);
                stat.executeUpdate();
                srno=Integer.parseInt(TSRNO);
            }
            else{
                statsr = conn.prepareStatement("SELECT NVL(MAX(SRNO),0)+1 SRNO FROM REPORT_SCHEDULER");
                resultsr = statsr.executeQuery();
                if(resultsr.next()){
                    srno=resultsr.getInt("SRNO");
                }
            }
            if (STARTDT != null && STARTDT.equals("1")) {
                if (CHMNT != null && CHMNT.equals("1")) {
                    if (CHDAY != null && CHDAY.equals("1")) {
                        stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,TTIME,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'ED',?,?,?,to_date(?,'HH24:MI'),sysdate,?,?,?,?,?,?,?,?) ");
                        stat.setInt(1, srno);
                        stat.setString(2, JOBNAME);
                        stat.setString(3, FILENAME);
                        stat.setString(4, EMAILTO);
                        stat.setString(5, CHHRS.trim() + ":" + CHMINTS.trim());
                        stat.setString(6, USER_ID);
                        stat.setString(7, EMAILSUB);
                        stat.setString(8, EMAILBODY);
                        stat.setString(9, EMAILCC);
                        stat.setString(10, EMAILBCC);
                        stat.setString(11, OUTFRMT);
                        stat.setString(12, CONNTYPE);
                        stat.setString(13, QUERYCHK);
                        stat.executeQuery();
                    } else if (CHDAY != null && CHDAY.equals("2")) {
                        if (DAYLST != null && DAYLST.size() > 0) {
                            for (int i = 0; i < DAYLST.size(); i++) {
                                stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,TTIME,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,TDAY,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'WD',?,?,?,to_date(?,'HH24:MI'),sysdate,?,?,?,?,?,?,?,?,?) ");
                                stat.setInt(1, srno);
                                stat.setString(2, JOBNAME);
                                stat.setString(3, FILENAME);
                                stat.setString(4, EMAILTO);
                                stat.setString(5, CHHRS + ":" + CHMINTS);
                                stat.setString(6, USER_ID);
                                stat.setString(7, EMAILSUB);
                                stat.setString(8, EMAILBODY);
                                stat.setString(9, EMAILCC);
                                stat.setString(10, EMAILBCC);
                                stat.setString(11, DAYLST.get(i).toString());
                                stat.setString(12, OUTFRMT);
                                stat.setString(13, CONNTYPE);
                                stat.setString(14, QUERYCHK);
                                stat.executeQuery();
                                if (stat != null) {
                                    stat.close();
                                }
                            }
                        }
                    } else if (CHDAY != null && CHDAY.equals("3")) {
                        stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,TTIME,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,TDATE,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'FD',?,?,?,to_date(?,'HH24:MI'),sysdate,?,?,?,?,?,to_date(?,'dd'),?,?,?) ");
                        stat.setInt(1, srno);
                        stat.setString(2, JOBNAME);
                        stat.setString(3, FILENAME);
                        stat.setString(4, EMAILTO);
                        stat.setString(5, CHHRS + ":" + CHMINTS);
                        stat.setString(6, USER_ID);
                        stat.setString(7, EMAILSUB);
                        stat.setString(8, EMAILBODY);
                        stat.setString(9, EMAILCC);
                        stat.setString(10, EMAILBCC);
                        stat.setString(11, CHMDTS);
                        stat.setString(12, OUTFRMT);
                        stat.setString(13, CONNTYPE);
                        stat.setString(14, QUERYCHK);
                        stat.executeQuery();
                    }
                } else if (CHMNT != null && CHMNT.equals("2")) {
                    if (MNTLST != null && MNTLST.size() > 0) {
                        for (int i = 0; i < MNTLST.size(); i++) {
                            if (CHDAY != null && CHDAY.equals("1")) {
                                stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,TTIME,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,TMONTH,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'ES',?,?,?,to_date(?,'HH24:MI'),sysdate,?,?,?,?,?,to_date(?,'mm'),?,?,?) ");
                                stat.setInt(1, srno);
                                stat.setString(2, JOBNAME);
                                stat.setString(3, FILENAME);
                                stat.setString(4, EMAILTO);
                                stat.setString(5, CHHRS + ":" + CHMINTS);
                                stat.setString(6, USER_ID);
                                stat.setString(7, EMAILSUB);
                                stat.setString(8, EMAILBODY);
                                stat.setString(9, EMAILCC);
                                stat.setString(10, EMAILBCC);
                                stat.setString(11, MNTLST.get(i).toString());
                                stat.setString(12, OUTFRMT);
                                stat.setString(13, CONNTYPE);
                                stat.setString(14, QUERYCHK);
                                stat.executeQuery();
                            } else if (CHDAY != null && CHDAY.equals("2")) {
                                if (DAYLST != null && DAYLST.size() > 0) {
                                    for (int j = 0; i < DAYLST.size(); j++) {
                                        stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,TTIME,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,TDAY,TMONTH,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'WS',?,?,?,to_date(?,'HH24:MI'),sysdate,?,?,?,?,?,?,to_date(?,'mm'),?,?,?) ");
                                        stat.setInt(1, srno);
                                        stat.setString(2, JOBNAME);
                                        stat.setString(3, FILENAME);
                                        stat.setString(4, EMAILTO);
                                        stat.setString(5, CHHRS + ":" + CHMINTS);
                                        stat.setString(6, USER_ID);
                                        stat.setString(7, EMAILSUB);
                                        stat.setString(8, EMAILBODY);
                                        stat.setString(9, EMAILCC);
                                        stat.setString(10, EMAILBCC);
                                        stat.setString(11, DAYLST.get(j).toString());
                                        stat.setString(12, MNTLST.get(i).toString());
                                        stat.setString(13, OUTFRMT);
                                        stat.setString(14, CONNTYPE);
                                        stat.setString(15, QUERYCHK);
                                        stat.executeQuery();
                                        if (stat != null) {
                                            stat.close();
                                        }
                                    }
                                }
                            } else if (CHDAY != null && CHDAY.equals("3")) {
                                stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,TTIME,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,TDATE,TMONTH,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'FS',?,?,?,to_date(?,'HH24:MI'),sysdate,?,?,?,?,?,to_date(?,'dd'),to_date(?,'mm'),?,?,?)");
                                stat.setInt(1, srno);
                                stat.setString(2, JOBNAME);
                                stat.setString(3, FILENAME);
                                stat.setString(4, EMAILTO);
                                stat.setString(5, CHHRS + ":" + CHMINTS);
                                stat.setString(6, USER_ID);
                                stat.setString(7, EMAILSUB);
                                stat.setString(8, EMAILBODY);
                                stat.setString(9, EMAILCC);
                                stat.setString(10, EMAILBCC);
                                stat.setString(11, CHMDTS);
                                stat.setString(12, MNTLST.get(i).toString());
                                stat.setString(13, OUTFRMT);
                                stat.setString(14, CONNTYPE);
                                stat.setString(15, QUERYCHK);
                                stat.executeQuery();
                            }
                        }
                    }
                }
            } else if (STARTDT != null && STARTDT.equals("2")) {
                stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,TTIME,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,ONDATE,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'OD',?,?,?,to_date(?,'HH24:MI'),sysdate,?,?,?,?,?,to_date(?,'dd/mm/yyyy'),?,?,?) ");
                stat.setInt(1, srno);
                stat.setString(2, JOBNAME);
                stat.setString(3, FILENAME);
                stat.setString(4, EMAILTO);
                stat.setString(5, CHHRS + ":" + CHMINTS);
                stat.setString(6, USER_ID);
                stat.setString(7, EMAILSUB);
                stat.setString(8, EMAILBODY);
                stat.setString(9, EMAILCC);
                stat.setString(10, EMAILBCC);
                stat.setString(11, CKDTPRF);
                stat.setString(12, OUTFRMT);
                stat.setString(13, CONNTYPE);
                stat.setString(14, QUERYCHK);
                stat.executeQuery();
            } else if (STARTDT != null && STARTDT.equals("3")) {
                stat = conn.prepareStatement("INSERT INTO REPORT_SCHEDULER (SRNO,REPORT_TYPE,REPORT_NAME,FILE_NAME,EMAIL_ID,SDATE,USER_ID,SUBJECT,BODY_CONTENT,CC_EMAIL_ID,BCC_EMAIL_ID,TRIGGER_NAME,RESULT_TYPE,CONNECTION_TYPE,QUERYCHK) values(?,'TR',?,?,?,sysdate,?,?,?,?,?,?,?,?,?) ");
                stat.setInt(1, srno);
                stat.setString(2, JOBNAME);
                stat.setString(3, FILENAME);
                stat.setString(4, EMAILTO);
                stat.setString(5, USER_ID);
                stat.setString(6, EMAILSUB);
                stat.setString(7, EMAILBODY);
                stat.setString(8, EMAILCC);
                stat.setString(9, EMAILBCC);
                stat.setString(10, TRIGGER_NAME);
                stat.setString(11, OUTFRMT);
                stat.setString(12, CONNTYPE);
                stat.setString(13, QUERYCHK);
                stat.executeQuery();
            }
            conn.commit();
        } catch (Exception e) {
            addActionError("ReportSchedulerAction save(): " + e);
            conn.rollback();
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
        TSRNO=srno+"";
        edit();
        addActionError("Record Save Successfully");        
        return "edit";
    }

    public String getJOBNAME() {
        return JOBNAME;
    }

    public void setJOBNAME(String JOBNAME) {
        this.JOBNAME = JOBNAME;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public String getJOBDESC() {
        return JOBDESC;
    }

    public void setJOBDESC(String JOBDESC) {
        this.JOBDESC = JOBDESC;
    }

    public String getSTARTDT() {
        return STARTDT;
    }

    public void setSTARTDT(String STARTDT) {
        this.STARTDT = STARTDT;
    }

    public String getCKDTPRF() {
        return CKDTPRF;
    }

    public void setCKDTPRF(String CKDTPRF) {
        this.CKDTPRF = CKDTPRF;
    }

    public String getCHMNT() {
        return CHMNT;
    }

    public void setCHMNT(String CHMNT) {
        this.CHMNT = CHMNT;
    }

    public String getCHDAY() {
        return CHDAY;
    }

    public void setCHDAY(String CHDAY) {
        this.CHDAY = CHDAY;
    }    

    public List getMNTLST() {
        return MNTLST;
    }

    public void setMNTLST(List MNTLST) {
        this.MNTLST = MNTLST;
    }

    public List getDAYLST() {
        return DAYLST;
    }

    public void setDAYLST(List DAYLST) {
        this.DAYLST = DAYLST;
    }

    public String getCONNTYPE() {
        return CONNTYPE;
    }

    public void setCONNTYPE(String CONNTYPE) {
        this.CONNTYPE = CONNTYPE;
    }

    public String getTSRNO() {
        return TSRNO;
    }

    public void setTSRNO(String TSRNO) {
        this.TSRNO = TSRNO;
    }

    public String getTCODE() {
        return TCODE;
    }

    public void setTCODE(String TCODE) {
        this.TCODE = TCODE;
    }

    public String getCHMDTS() {
        return CHMDTS;
    }

    public void setCHMDTS(String CHMDTS) {
        this.CHMDTS = CHMDTS;
    }

    public String getCHHRS() {
        return CHHRS;
    }

    public void setCHHRS(String CHHRS) {
        this.CHHRS = CHHRS;
    }

    public String getCHMINTS() {
        return CHMINTS;
    }

    public void setCHMINTS(String CHMINTS) {
        this.CHMINTS = CHMINTS;
    }

    public String getOUTFRMT() {
        return OUTFRMT;
    }

    public void setOUTFRMT(String OUTFRMT) {
        this.OUTFRMT = OUTFRMT;
    }

    public String getEMAILTO() {
        return EMAILTO;
    }

    public void setEMAILTO(String EMAILTO) {
        this.EMAILTO = EMAILTO;
    }

    public String getEMAILCC() {
        return EMAILCC;
    }

    public void setEMAILCC(String EMAILCC) {
        this.EMAILCC = EMAILCC;
    }

    public String getEMAILBCC() {
        return EMAILBCC;
    }

    public void setEMAILBCC(String EMAILBCC) {
        this.EMAILBCC = EMAILBCC;
    }

    public String getEMAILSUB() {
        return EMAILSUB;
    }

    public void setEMAILSUB(String EMAILSUB) {
        this.EMAILSUB = EMAILSUB;
    }

    public String getEMAILBODY() {
        return EMAILBODY;
    }

    public void setEMAILBODY(String EMAILBODY) {
        this.EMAILBODY = EMAILBODY;
    }

    public String getTRIGGER_NAME() {
        return TRIGGER_NAME;
    }

    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getREPORT_NAME_SER() {
        return REPORT_NAME_SER;
    }

    public void setREPORT_NAME_SER(String REPORT_NAME_SER) {
        this.REPORT_NAME_SER = REPORT_NAME_SER;
    }

    public String getTRIGGER_NAME_SER() {
        return TRIGGER_NAME_SER;
    }

    public void setTRIGGER_NAME_SER(String TRIGGER_NAME_SER) {
        this.TRIGGER_NAME_SER = TRIGGER_NAME_SER;
    }

    public List getDETAIL_LIST() {
        return DETAIL_LIST;
    }

    public void setDETAIL_LIST(List DETAIL_LIST) {
        this.DETAIL_LIST = DETAIL_LIST;
    }

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
    }

    public String getFOLDER_ID() {
        return FOLDER_ID;
    }

    public void setFOLDER_ID(String FOLDER_ID) {
        this.FOLDER_ID = FOLDER_ID;
    }

    public List getUP_LIST() {
        return UP_LIST;
    }

    public void setUP_LIST(List UP_LIST) {
        this.UP_LIST = UP_LIST;
    }

    public String getQUERYCHK() {
        return QUERYCHK;
    }

    public void setQUERYCHK(String QUERYCHK) {
        this.QUERYCHK = QUERYCHK;
    }
}
