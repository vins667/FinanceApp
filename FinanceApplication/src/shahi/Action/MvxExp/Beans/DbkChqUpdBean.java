/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Beans;

/**
 *
 * @author Sanjeev
 */
public class DbkChqUpdBean {
    private String TR_DATE;
    private String SB_NO;
    private String SB_DATE;
    private String CLAIM_PORT;
    private Double DBK_DUE;
    private Double STR_DUE;
    private Double STR_WOFF;
    private Double CR_AMT;
    private Double DBK_RECV;
    private Double STR_RECV;
    private Double DBK_WOFF;
    private Double AMT_BAL;
    

    public DbkChqUpdBean(String TR_DATE, String SB_NO, String SB_DATE, String CLAIM_PORT, Double DBK_DUE, Double STR_DUE, Double STR_WOFF, Double CR_AMT, Double DBK_RECV, Double STR_RECV, Double DBK_WOFF, Double AMT_BAL) {
        this.TR_DATE = TR_DATE;
        this.SB_NO = SB_NO;
        this.SB_DATE = SB_DATE;
        this.CLAIM_PORT = CLAIM_PORT;
        this.DBK_DUE = DBK_DUE;
        this.STR_DUE = STR_DUE;
        this.STR_WOFF = STR_WOFF;
        this.CR_AMT = CR_AMT;
        this.DBK_RECV = DBK_RECV;
        this.STR_RECV = STR_RECV;
        this.DBK_WOFF = DBK_WOFF;
        this.AMT_BAL = AMT_BAL;
    }

    
    
    
    
    public String getTR_DATE() {
        return TR_DATE;
    }

    public void setTR_DATE(String TR_DATE) {
        this.TR_DATE = TR_DATE;
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

    public Double getDBK_DUE() {
        return DBK_DUE;
    }

    public void setDBK_DUE(Double DBK_DUE) {
        this.DBK_DUE = DBK_DUE;
    }

    public Double getSTR_DUE() {
        return STR_DUE;
    }

    public void setSTR_DUE(Double STR_DUE) {
        this.STR_DUE = STR_DUE;
    }

    public Double getSTR_WOFF() {
        return STR_WOFF;
    }

    public void setSTR_WOFF(Double STR_WOFF) {
        this.STR_WOFF = STR_WOFF;
    }

    public Double getCR_AMT() {
        return CR_AMT;
    }

    public void setCR_AMT(Double CR_AMT) {
        this.CR_AMT = CR_AMT;
    }

    public Double getDBK_RECV() {
        return DBK_RECV;
    }

    public void setDBK_RECV(Double DBK_RECV) {
        this.DBK_RECV = DBK_RECV;
    }

    public Double getSTR_RECV() {
        return STR_RECV;
    }

    public void setSTR_RECV(Double STR_RECV) {
        this.STR_RECV = STR_RECV;
    }

    public Double getDBK_WOFF() {
        return DBK_WOFF;
    }

    public void setDBK_WOFF(Double DBK_WOFF) {
        this.DBK_WOFF = DBK_WOFF;
    }

    public Double getAMT_BAL() {
        return AMT_BAL;
    }

    public void setAMT_BAL(Double AMT_BAL) {
        this.AMT_BAL = AMT_BAL;
    }
    
    
    
}
