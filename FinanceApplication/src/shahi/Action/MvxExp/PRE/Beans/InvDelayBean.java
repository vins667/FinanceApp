/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class InvDelayBean {
    private String  YEAR;
    private String  COMPANY;
    private String  INV_NO;
    private String  EXCS_INV_NO;
    private String  TTO_DATE;
    private String  TO_DATE;
    private String  ETD_DATE;
    private String  BUYER;
    private String AC_HOLDER;

    public InvDelayBean(String YEAR, String COMPANY, String INV_NO, String EXCS_INV_NO, String TTO_DATE, String TO_DATE, String ETD_DATE, String BUYER,String AC_HOLDER) {
        this.YEAR = YEAR;
        this.COMPANY = COMPANY;
        this.INV_NO = INV_NO;
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.TTO_DATE = TTO_DATE;
        this.TO_DATE = TO_DATE;
        this.ETD_DATE = ETD_DATE;
        this.BUYER = BUYER;
        this.AC_HOLDER=AC_HOLDER;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
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

    public String getETD_DATE() {
        return ETD_DATE;
    }

    public void setETD_DATE(String ETD_DATE) {
        this.ETD_DATE = ETD_DATE;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getAC_HOLDER() {
        return AC_HOLDER;
    }

    public void setAC_HOLDER(String AC_HOLDER) {
        this.AC_HOLDER = AC_HOLDER;
    }
    
    
    
}
