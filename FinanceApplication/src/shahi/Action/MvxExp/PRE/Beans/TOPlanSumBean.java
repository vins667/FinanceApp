/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class TOPlanSumBean {
    
    private String FWD_DATE;
    private String EX_FY_DATE;
    private String DEL_DATE;
    private String PORT;
    private String BUYER;
    private String CNTRY;
    private String INV_NO;
    private String FACTORY;
    private String INVQTY;
    private String CTNS;
    private Double CBM;
    private Double CFT;
    private String AC_HOLDER;
   

    public TOPlanSumBean(String FWD_DATE, String EX_FY_DATE, String DEL_DATE, String PORT, String BUYER, String CNTRY, String INV_NO, String FACTORY, String INVQTY,String CTNS, Double CBM, Double CFT,String AC_HOLDER) {
        this.FWD_DATE = FWD_DATE;
        this.EX_FY_DATE = EX_FY_DATE;
        this.DEL_DATE = DEL_DATE;
        this.PORT = PORT;
        this.BUYER = BUYER;
        this.CNTRY = CNTRY;
        this.INV_NO = INV_NO;
        this.FACTORY = FACTORY;
        this.INVQTY = INVQTY;
        this.CTNS = CTNS;
        this.CBM = CBM;
        this.CFT = CFT;
        this.AC_HOLDER=AC_HOLDER;
    }

    
    
    
    
    public String getFWD_DATE() {
        return FWD_DATE;
    }

    public void setFWD_DATE(String FWD_DATE) {
        this.FWD_DATE = FWD_DATE;
    }

    public String getEX_FY_DATE() {
        return EX_FY_DATE;
    }

    public void setEX_FY_DATE(String EX_FY_DATE) {
        this.EX_FY_DATE = EX_FY_DATE;
    }

    public String getDEL_DATE() {
        return DEL_DATE;
    }

    public void setDEL_DATE(String DEL_DATE) {
        this.DEL_DATE = DEL_DATE;
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

    public String getCNTRY() {
        return CNTRY;
    }

    public void setCNTRY(String CNTRY) {
        this.CNTRY = CNTRY;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getFACTORY() {
        return FACTORY;
    }

    public void setFACTORY(String FACTORY) {
        this.FACTORY = FACTORY;
    }

    public String getINVQTY() {
        return INVQTY;
    }

    public void setINVQTY(String INVQTY) {
        this.INVQTY = INVQTY;
    }

    public String getCTNS() {
        return CTNS;
    }

    public void setCTNS(String CTNS) {
        this.CTNS = CTNS;
    }

    

    public Double getCBM() {
        return CBM;
    }

    public void setCBM(Double CBM) {
        this.CBM = CBM;
    }

    public Double getCFT() {
        return CFT;
    }

    public void setCFT(Double CFT) {
        this.CFT = CFT;
    }

    public String getAC_HOLDER() {
        return AC_HOLDER;
    }

    public void setAC_HOLDER(String AC_HOLDER) {
        this.AC_HOLDER = AC_HOLDER;
    }
    
    
    
    
}
