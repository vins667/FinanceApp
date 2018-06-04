/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.FundReq.Beans;

/**
 *
 * @author Vivek
 */
public class FaPayrollTypeMastBean {

    private String REQ_TYPE;
    private String REQ_CODE;
    private String REQ_NAME;
    private String FLAG;
    private String USER_ID;
    private String TDATE;

    public String getREQ_TYPE() {
        return REQ_TYPE;
    }

    public void setREQ_TYPE(String REQ_TYPE) {
        this.REQ_TYPE = REQ_TYPE;
    }

    public String getREQ_CODE() {
        return REQ_CODE;
    }

    public void setREQ_CODE(String REQ_CODE) {
        this.REQ_CODE = REQ_CODE;
    }

    public String getREQ_NAME() {
        return REQ_NAME;
    }

    public void setREQ_NAME(String REQ_NAME) {
        this.REQ_NAME = REQ_NAME;
    }

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getTDATE() {
        return TDATE;
    }

    public void setTDATE(String TDATE) {
        this.TDATE = TDATE;
    }

    public FaPayrollTypeMastBean() {
    }

    public FaPayrollTypeMastBean(String REQ_TYPE, String REQ_CODE, String REQ_NAME, String FLAG, String USER_ID, String TDATE) {
        this.REQ_TYPE = REQ_TYPE;
        this.REQ_CODE = REQ_CODE;
        this.REQ_NAME = REQ_NAME;
        this.FLAG = FLAG;
        this.USER_ID = USER_ID;
        this.TDATE = TDATE;
    }

    public FaPayrollTypeMastBean(String REQ_CODE, String REQ_NAME) {
        this.REQ_CODE = REQ_CODE;
        this.REQ_NAME = REQ_NAME;
    }
    
}
