/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MvxExp.Beans;

/**
 *
 * @author Vivek
 */
public class PreShipPlanMsBeans {
private String COMPANY_CODE;
private String LOCATION_CODE;
private String PLAN_NUMB;
private String PLAN_DATE;
private String YEAR;
private String INV_NO;
private String CUST_DATE;
private String INV_QNTY;
private String CONTROL_NUMB;
private String HANG_DTLS;
private String AWB_NUMB;
private String SEH_USER;
private String TDATE;
private String SERVER_LOCATION_CODE;
private String EXCS_INV_NO;
private String SET_PCS;
private String EMAIL_FLAG;
private String SALE_TYPE;
    public PreShipPlanMsBeans(String PLAN_NUMB, String PLAN_DATE, String INV_QNTY, String EXCS_INV_NO) {
        this.PLAN_NUMB = PLAN_NUMB;
        this.PLAN_DATE = PLAN_DATE;
        this.INV_QNTY = INV_QNTY;
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getAWB_NUMB() {
        return AWB_NUMB;
    }

    public void setAWB_NUMB(String AWB_NUMB) {
        this.AWB_NUMB = AWB_NUMB;
    }

    public String getCOMPANY_CODE() {
        return COMPANY_CODE;
    }

    public void setCOMPANY_CODE(String COMPANY_CODE) {
        this.COMPANY_CODE = COMPANY_CODE;
    }

    public String getCONTROL_NUMB() {
        return CONTROL_NUMB;
    }

    public void setCONTROL_NUMB(String CONTROL_NUMB) {
        this.CONTROL_NUMB = CONTROL_NUMB;
    }

    public String getCUST_DATE() {
        return CUST_DATE;
    }

    public void setCUST_DATE(String CUST_DATE) {
        this.CUST_DATE = CUST_DATE;
    }

    public String getEMAIL_FLAG() {
        return EMAIL_FLAG;
    }

    public void setEMAIL_FLAG(String EMAIL_FLAG) {
        this.EMAIL_FLAG = EMAIL_FLAG;
    }

    public String getHANG_DTLS() {
        return HANG_DTLS;
    }

    public void setHANG_DTLS(String HANG_DTLS) {
        this.HANG_DTLS = HANG_DTLS;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getLOCATION_CODE() {
        return LOCATION_CODE;
    }

    public void setLOCATION_CODE(String LOCATION_CODE) {
        this.LOCATION_CODE = LOCATION_CODE;
    }

    public String getSALE_TYPE() {
        return SALE_TYPE;
    }

    public void setSALE_TYPE(String SALE_TYPE) {
        this.SALE_TYPE = SALE_TYPE;
    }

    public String getSEH_USER() {
        return SEH_USER;
    }

    public void setSEH_USER(String SEH_USER) {
        this.SEH_USER = SEH_USER;
    }

    public String getSERVER_LOCATION_CODE() {
        return SERVER_LOCATION_CODE;
    }

    public void setSERVER_LOCATION_CODE(String SERVER_LOCATION_CODE) {
        this.SERVER_LOCATION_CODE = SERVER_LOCATION_CODE;
    }

    public String getSET_PCS() {
        return SET_PCS;
    }

    public void setSET_PCS(String SET_PCS) {
        this.SET_PCS = SET_PCS;
    }

    public String getTDATE() {
        return TDATE;
    }

    public void setTDATE(String TDATE) {
        this.TDATE = TDATE;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getINV_QNTY() {
        return INV_QNTY;
    }

    public void setINV_QNTY(String INV_QNTY) {
        this.INV_QNTY = INV_QNTY;
    }

    public String getPLAN_DATE() {
        return PLAN_DATE;
    }

    public void setPLAN_DATE(String PLAN_DATE) {
        this.PLAN_DATE = PLAN_DATE;
    }

    public String getPLAN_NUMB() {
        return PLAN_NUMB;
    }

    public void setPLAN_NUMB(String PLAN_NUMB) {
        this.PLAN_NUMB = PLAN_NUMB;
    }

}
