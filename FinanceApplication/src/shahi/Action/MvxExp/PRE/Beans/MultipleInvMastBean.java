/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class MultipleInvMastBean {
    
    private String PLAN_NO;
    private String EXCS_INV_NO;
    private String INV_DATE;
    private String DISCHARGE;
    private String FWD_CUSTOM;
    private int CTNS;
    private Double GRWT;
    private Double NETWT;

    public MultipleInvMastBean(String PLAN_NO, String EXCS_INV_NO, String INV_DATE, String DISCHARGE, String FWD_CUSTOM, int CTNS, Double GRWT, Double NETWT) {
        this.PLAN_NO = PLAN_NO;
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.INV_DATE = INV_DATE;
        this.DISCHARGE = DISCHARGE;
        this.FWD_CUSTOM = FWD_CUSTOM;
        this.CTNS = CTNS;
        this.GRWT = GRWT;
        this.NETWT = NETWT;
    }

    public String getPLAN_NO() {
        return PLAN_NO;
    }

    public void setPLAN_NO(String PLAN_NO) {
        this.PLAN_NO = PLAN_NO;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getINV_DATE() {
        return INV_DATE;
    }

    public void setINV_DATE(String INV_DATE) {
        this.INV_DATE = INV_DATE;
    }

    public String getDISCHARGE() {
        return DISCHARGE;
    }

    public void setDISCHARGE(String DISCHARGE) {
        this.DISCHARGE = DISCHARGE;
    }

    public String getFWD_CUSTOM() {
        return FWD_CUSTOM;
    }

    public void setFWD_CUSTOM(String FWD_CUSTOM) {
        this.FWD_CUSTOM = FWD_CUSTOM;
    }

    public int getCTNS() {
        return CTNS;
    }

    public void setCTNS(int CTNS) {
        this.CTNS = CTNS;
    }

    
 
    public Double getGRWT() {
        return GRWT;
    }

    public void setGRWT(Double GRWT) {
        this.GRWT = GRWT;
    }

    public Double getNETWT() {
        return NETWT;
    }

    public void setNETWT(Double NETWT) {
        this.NETWT = NETWT;
    }
     
    
    
}
