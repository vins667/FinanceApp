/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class DBKQueryBean {
    
    private String SB_NO;
    private String SB_DATE;
    private String CLAIM_PORT;
    private String DBK_DUE;
    private String DBK_RECV;
    private String DBK_SUPL;
    private String DBK_WOFF;
    private String STR_DUE;
    private String STR_RECV;
    private String STR_WOFF;

    public DBKQueryBean(String SB_NO, String SB_DATE, String CLAIM_PORT, String DBK_DUE, String DBK_RECV, String DBK_SUPL, String DBK_WOFF, String STR_DUE, String STR_RECV, String STR_WOFF) {
        this.SB_NO = SB_NO;
        this.SB_DATE = SB_DATE;
        this.CLAIM_PORT = CLAIM_PORT;
        this.DBK_DUE = DBK_DUE;
        this.DBK_RECV = DBK_RECV;
        this.DBK_SUPL = DBK_SUPL;
        this.DBK_WOFF = DBK_WOFF;
        this.STR_DUE = STR_DUE;
        this.STR_RECV = STR_RECV;
        this.STR_WOFF = STR_WOFF;
    }

    public String getSB_NO() {
        return SB_NO;
    }

    public void setSB_NO(String SB_NO) {
        this.SB_NO = SB_NO;
    }

    public String getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(String SB_DATE) {
        this.SB_DATE = SB_DATE;
    }

    public String getCLAIM_PORT() {
        return CLAIM_PORT;
    }

    public void setCLAIM_PORT(String CLAIM_PORT) {
        this.CLAIM_PORT = CLAIM_PORT;
    }

    public String getDBK_DUE() {
        return DBK_DUE;
    }

    public void setDBK_DUE(String DBK_DUE) {
        this.DBK_DUE = DBK_DUE;
    }

    public String getDBK_RECV() {
        return DBK_RECV;
    }

    public void setDBK_RECV(String DBK_RECV) {
        this.DBK_RECV = DBK_RECV;
    }

    public String getDBK_SUPL() {
        return DBK_SUPL;
    }

    public void setDBK_SUPL(String DBK_SUPL) {
        this.DBK_SUPL = DBK_SUPL;
    }

    public String getDBK_WOFF() {
        return DBK_WOFF;
    }

    public void setDBK_WOFF(String DBK_WOFF) {
        this.DBK_WOFF = DBK_WOFF;
    }

    public String getSTR_DUE() {
        return STR_DUE;
    }

    public void setSTR_DUE(String STR_DUE) {
        this.STR_DUE = STR_DUE;
    }

    public String getSTR_RECV() {
        return STR_RECV;
    }

    public void setSTR_RECV(String STR_RECV) {
        this.STR_RECV = STR_RECV;
    }

    public String getSTR_WOFF() {
        return STR_WOFF;
    }

    public void setSTR_WOFF(String STR_WOFF) {
        this.STR_WOFF = STR_WOFF;
    }
           
    
    
}
