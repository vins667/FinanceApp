/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;

/**
 *
 * @author Sanjeev
 */
public class DBKREFUNDBEAN {
    
    
    private String SB_NO;
    private String SB_DATE;
    private String CLAIM_PORT;
    private String DBK_DUE;
    private String DBK_RECV;
    private String STR_DUE;
    private String STR_RECV;
    private String CHALLAN_NO;
    private String CHALLAN_DATE;
    private String BANK;
    private String AMT;

    public DBKREFUNDBEAN(String SB_NO, String SB_DATE, String CLAIM_PORT, String DBK_DUE, String DBK_RECV, String STR_DUE, String STR_RECV, String CHALLAN_NO, String CHALLAN_DATE, String BANK,String AMT) {
        this.SB_NO = SB_NO;
        this.SB_DATE = SB_DATE;
        this.CLAIM_PORT = CLAIM_PORT;
        this.DBK_DUE = DBK_DUE;
        this.DBK_RECV = DBK_RECV;
        this.STR_DUE = STR_DUE;
        this.STR_RECV = STR_RECV;
        this.CHALLAN_NO = CHALLAN_NO;
        this.CHALLAN_DATE = CHALLAN_DATE;
        this.BANK = BANK;
        this.AMT=AMT;
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

    public String getCHALLAN_NO() {
        return CHALLAN_NO;
    }

    public void setCHALLAN_NO(String CHALLAN_NO) {
        this.CHALLAN_NO = CHALLAN_NO;
    }

    public String getCHALLAN_DATE() {
        return CHALLAN_DATE;
    }

    public void setCHALLAN_DATE(String CHALLAN_DATE) {
        this.CHALLAN_DATE = CHALLAN_DATE;
    }

    public String getBANK() {
        return BANK;
    }

    public void setBANK(String BANK) {
        this.BANK = BANK;
    }

    public String getAMT() {
        return AMT;
    }

    public void setAMT(String AMT) {
        this.AMT = AMT;
    }
    
     
    
}
