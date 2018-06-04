/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class InvETDBean {
    private String EXCS_INV_NO;
    private String PORT;
    private String BUYER;
    private String INV_DATE;
    private String TTO_DATE;
    private String TO_DATE;
    private String PCH;
    private String MOS;
    private String PLAN_NO;
    private String AC_HOLDER;
    private String CRNCY;
    private String CNTRY;
    private String QTY;
    private String ETD_DATE;

    public InvETDBean(String EXCS_INV_NO, String PORT, String BUYER, String INV_DATE, String TTO_DATE, String TO_DATE, String PCH, String MOS, String PLAN_NO, String AC_HOLDER, String CRNCY, String CNTRY, String QTY, String ETD_DATE) {
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.PORT = PORT;
        this.BUYER = BUYER;
        this.INV_DATE = INV_DATE;
        this.TTO_DATE = TTO_DATE;
        this.TO_DATE = TO_DATE;
        this.PCH = PCH;
        this.MOS = MOS;
        this.PLAN_NO = PLAN_NO;
        this.AC_HOLDER = AC_HOLDER;
        this.CRNCY = CRNCY;
        this.CNTRY = CNTRY;
        this.QTY = QTY;
        this.ETD_DATE = ETD_DATE;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getINV_DATE() {
        return INV_DATE;
    }

    public void setINV_DATE(String INV_DATE) {
        this.INV_DATE = INV_DATE;
    }

    public String getTTO_DATE() {
        return TTO_DATE;
    }

    public void setTTO_DATE(String TTO_DATE) {
        this.TTO_DATE = TTO_DATE;
    }

    public String getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(String TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getMOS() {
        return MOS;
    }

    public void setMOS(String MOS) {
        this.MOS = MOS;
    }

    public String getPLAN_NO() {
        return PLAN_NO;
    }

    public void setPLAN_NO(String PLAN_NO) {
        this.PLAN_NO = PLAN_NO;
    }

    public String getAC_HOLDER() {
        return AC_HOLDER;
    }

    public void setAC_HOLDER(String AC_HOLDER) {
        this.AC_HOLDER = AC_HOLDER;
    }

    public String getCRNCY() {
        return CRNCY;
    }

    public void setCRNCY(String CRNCY) {
        this.CRNCY = CRNCY;
    }

    public String getCNTRY() {
        return CNTRY;
    }

    public void setCNTRY(String CNTRY) {
        this.CNTRY = CNTRY;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getETD_DATE() {
        return ETD_DATE;
    }

    public void setETD_DATE(String ETD_DATE) {
        this.ETD_DATE = ETD_DATE;
    }
    
    
    
    
}
