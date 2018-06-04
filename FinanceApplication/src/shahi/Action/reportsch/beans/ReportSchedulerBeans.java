/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.reportsch.beans;

/**
 *
 * @author Vivek
 */
public class ReportSchedulerBeans {

    private String SRNO;
    private String REPORT_TYPE;
    private String REPORT_NAME;
    private String FILE_NAME;
    private String TRIGGER_NAME;
    private String EMAIL_ID;
    private String RESULT_TYPE;
    private String SUBJECT;
    private String BODY_CONTENT;
    private String CC_EMAIL_ID;
    private String BCC_EMAIL_ID;
    private String CONNECTION_TYPE;
    private String TDATE;
    private String THOUR;
    private String TMINUTE;
    private String QUERYCHK;

    public ReportSchedulerBeans(String SRNO, String REPORT_TYPE, String REPORT_NAME, String FILE_NAME, String TRIGGER_NAME,String EMAIL_ID, String RESULT_TYPE, String SUBJECT, String BODY_CONTENT, String CC_EMAIL_ID, String BCC_EMAIL_ID, String CONNECTION_TYPE, String TDATE, String THOUR, String TMINUTE,String QUERYCHK) {
        this.SRNO = SRNO;
        this.REPORT_TYPE = REPORT_TYPE;
        this.REPORT_NAME = REPORT_NAME;
        this.FILE_NAME = FILE_NAME;
        this.TRIGGER_NAME =TRIGGER_NAME;
        this.EMAIL_ID = EMAIL_ID;
        this.RESULT_TYPE = RESULT_TYPE;
        this.SUBJECT = SUBJECT;
        this.BODY_CONTENT = BODY_CONTENT;
        this.CC_EMAIL_ID = CC_EMAIL_ID;
        this.BCC_EMAIL_ID = BCC_EMAIL_ID;
        this.CONNECTION_TYPE = CONNECTION_TYPE;
        this.TDATE = TDATE;
        this.THOUR = THOUR;
        this.TMINUTE = TMINUTE;
        this.QUERYCHK = QUERYCHK;
    }

    
    
    public String getSRNO() {
        return SRNO;
    }

    public void setSRNO(String SRNO) {
        this.SRNO = SRNO;
    }

    public String getREPORT_TYPE() {
        return REPORT_TYPE;
    }

    public void setREPORT_TYPE(String REPORT_TYPE) {
        this.REPORT_TYPE = REPORT_TYPE;
    }

    public String getREPORT_NAME() {
        return REPORT_NAME;
    }

    public void setREPORT_NAME(String REPORT_NAME) {
        this.REPORT_NAME = REPORT_NAME;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getEMAIL_ID() {
        return EMAIL_ID;
    }

    public void setEMAIL_ID(String EMAIL_ID) {
        this.EMAIL_ID = EMAIL_ID;
    }

    public String getRESULT_TYPE() {
        return RESULT_TYPE;
    }

    public void setRESULT_TYPE(String RESULT_TYPE) {
        this.RESULT_TYPE = RESULT_TYPE;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getBODY_CONTENT() {
        return BODY_CONTENT;
    }

    public void setBODY_CONTENT(String BODY_CONTENT) {
        this.BODY_CONTENT = BODY_CONTENT;
    }

    public String getCC_EMAIL_ID() {
        return CC_EMAIL_ID;
    }

    public void setCC_EMAIL_ID(String CC_EMAIL_ID) {
        this.CC_EMAIL_ID = CC_EMAIL_ID;
    }

    public String getBCC_EMAIL_ID() {
        return BCC_EMAIL_ID;
    }

    public void setBCC_EMAIL_ID(String BCC_EMAIL_ID) {
        this.BCC_EMAIL_ID = BCC_EMAIL_ID;
    }

    public String getCONNECTION_TYPE() {
        return CONNECTION_TYPE;
    }

    public void setCONNECTION_TYPE(String CONNECTION_TYPE) {
        this.CONNECTION_TYPE = CONNECTION_TYPE;
    }

    public String getTDATE() {
        return TDATE;
    }

    public void setTDATE(String TDATE) {
        this.TDATE = TDATE;
    }

    public String getTHOUR() {
        return THOUR;
    }

    public void setTHOUR(String THOUR) {
        this.THOUR = THOUR;
    }

    public String getTMINUTE() {
        return TMINUTE;
    }

    public void setTMINUTE(String TMINUTE) {
        this.TMINUTE = TMINUTE;
    }    

    public String getTRIGGER_NAME() {
        return TRIGGER_NAME;
    }

    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
    }

    public String getQUERYCHK() {
        return QUERYCHK;
    }

    public void setQUERYCHK(String QUERYCHK) {
        this.QUERYCHK = QUERYCHK;
    }
}
