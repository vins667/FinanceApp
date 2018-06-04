/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class BosBillBreakupBean {
    
     private String LOCT;
     private String EXCS_INV_NO;
     private String BOS_NO;
     private String BOS_DATE;
     private String UNIT;
     private String PORT;
     private String TRANSPORTER;
     private String DISPATCH_VIA;
     private String PLANCFT;
     private String QNTY;
     private String CTNS;
     private String PCH;

    public BosBillBreakupBean(String LOCT, String EXCS_INV_NO, String BOS_NO, String BOS_DATE, String UNIT, String PORT, String TRANSPORTER, String DISPATCH_VIA, String PLANCFT, String QNTY, String CTNS, String PCH) {
        this.LOCT = LOCT;
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.BOS_NO = BOS_NO;
        this.BOS_DATE = BOS_DATE;
        this.UNIT = UNIT;
        this.PORT = PORT;
        this.TRANSPORTER = TRANSPORTER;
        this.DISPATCH_VIA = DISPATCH_VIA;
        this.PLANCFT = PLANCFT;
        this.QNTY = QNTY;
        this.CTNS = CTNS;
        this.PCH = PCH;
    }

     
     
     
     
    public String getLOCT() {
        return LOCT;
    }

    public void setLOCT(String LOCT) {
        this.LOCT = LOCT;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getBOS_NO() {
        return BOS_NO;
    }

    public void setBOS_NO(String BOS_NO) {
        this.BOS_NO = BOS_NO;
    }

    public String getBOS_DATE() {
        return BOS_DATE;
    }

    public void setBOS_DATE(String BOS_DATE) {
        this.BOS_DATE = BOS_DATE;
    }

    public String getUNIT() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getTRANSPORTER() {
        return TRANSPORTER;
    }

    public void setTRANSPORTER(String TRANSPORTER) {
        this.TRANSPORTER = TRANSPORTER;
    }

    public String getDISPATCH_VIA() {
        return DISPATCH_VIA;
    }

    public void setDISPATCH_VIA(String DISPATCH_VIA) {
        this.DISPATCH_VIA = DISPATCH_VIA;
    }

    public String getPLANCFT() {
        return PLANCFT;
    }

    public void setPLANCFT(String PLANCFT) {
        this.PLANCFT = PLANCFT;
    }

    public String getQNTY() {
        return QNTY;
    }

    public void setQNTY(String QNTY) {
        this.QNTY = QNTY;
    }

    public String getCTNS() {
        return CTNS;
    }

    public void setCTNS(String CTNS) {
        this.CTNS = CTNS;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }
  
  
  
  
  
     
}
