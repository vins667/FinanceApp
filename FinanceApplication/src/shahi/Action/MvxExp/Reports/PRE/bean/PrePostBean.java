/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author Sanjeev
 */
public class PrePostBean {
    private String BUYER;
    private String DESTI;
    private String PCH;
    private String TO_DATE;
    private String AC_HOLDER;
    private String EXCS_INV_NO;
    private String BUYER_GRP;

    public PrePostBean(String BUYER, String DESTI, String PCH, String TO_DATE, String AC_HOLDER, String EXCS_INV_NO,String BUYER_GRP) {
        this.BUYER = BUYER;
        this.DESTI = DESTI;
        this.PCH = PCH;
        this.TO_DATE = TO_DATE;
        this.AC_HOLDER = AC_HOLDER;
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.BUYER_GRP = BUYER_GRP;
    }

    
    
    
    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getDESTI() {
        return DESTI;
    }

    public void setDESTI(String DESTI) {
        this.DESTI = DESTI;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(String TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public String getAC_HOLDER() {
        return AC_HOLDER;
    }

    public void setAC_HOLDER(String AC_HOLDER) {
        this.AC_HOLDER = AC_HOLDER;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getBUYER_GRP() {
        return BUYER_GRP;
    }

    public void setBUYER_GRP(String BUYER_GRP) {
        this.BUYER_GRP = BUYER_GRP;
    }
    
    
    
    
}
