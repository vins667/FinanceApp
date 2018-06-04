/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MvxExp.Beans;

/**
 *
 * @author Vivek
 */
public class PreShipPlanDtBeans {
     private String COMPANY_CODE;
     private String LOCATION_CODE;
     private String PLAN_NUMB;
     private String PLAN_DATE;
     private String CO_NUMB;
     private String CO_DATE;
     private String CO_LINE;
     private String ITEM_NUMB;
     private String ITEM_DESC;
     private String PLAN_QNTY;
     private String PCH;
     private String WEIGHT;
     private String UOM;
     private String CONTROL_NUMB;
     private String YEAR;
     private String INV_NO;
     private String SEH_USER;
     private String TDATE;
     private String SERVER_LOCATION_CODE;
     private String CO_WH;
     private String BUYER;
     private String EXTRA_QNTY;
     private String MODE_OF_SHIP;
     private String DEL_NUMB;
     private String CONV_FACTR;

    public PreShipPlanDtBeans(String PLAN_NUMB, String PLAN_DATE, String CO_NUMB, String CO_DATE, String CO_LINE, String ITEM_NUMB, String ITEM_DESC, String PLAN_QNTY, String PCH, String WEIGHT, String UOM, String CONTROL_NUMB, String INV_NO, String BUYER, String EXTRA_QNTY, String MODE_OF_SHIP, String DEL_NUMB, String CONV_FACTR) {
        this.PLAN_NUMB = PLAN_NUMB;
        this.PLAN_DATE = PLAN_DATE;
        this.CO_NUMB = CO_NUMB;
        this.CO_DATE = CO_DATE;
        this.CO_LINE = CO_LINE;
        this.ITEM_NUMB = ITEM_NUMB;
        this.ITEM_DESC = ITEM_DESC;
        this.PLAN_QNTY = PLAN_QNTY;
        this.PCH = PCH;
        this.WEIGHT = WEIGHT;
        this.UOM = UOM;
        this.CONTROL_NUMB = CONTROL_NUMB;
        this.INV_NO = INV_NO;
        this.BUYER = BUYER;
        this.EXTRA_QNTY = EXTRA_QNTY;
        this.MODE_OF_SHIP = MODE_OF_SHIP;
        this.DEL_NUMB = DEL_NUMB;
        this.CONV_FACTR = CONV_FACTR;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
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

    public String getCONV_FACTR() {
        return CONV_FACTR;
    }

    public void setCONV_FACTR(String CONV_FACTR) {
        this.CONV_FACTR = CONV_FACTR;
    }

    public String getCO_DATE() {
        return CO_DATE;
    }

    public void setCO_DATE(String CO_DATE) {
        this.CO_DATE = CO_DATE;
    }

    public String getCO_LINE() {
        return CO_LINE;
    }

    public void setCO_LINE(String CO_LINE) {
        this.CO_LINE = CO_LINE;
    }

    public String getCO_NUMB() {
        return CO_NUMB;
    }

    public void setCO_NUMB(String CO_NUMB) {
        this.CO_NUMB = CO_NUMB;
    }

    public String getCO_WH() {
        return CO_WH;
    }

    public void setCO_WH(String CO_WH) {
        this.CO_WH = CO_WH;
    }

    public String getDEL_NUMB() {
        return DEL_NUMB;
    }

    public void setDEL_NUMB(String DEL_NUMB) {
        this.DEL_NUMB = DEL_NUMB;
    }

    public String getEXTRA_QNTY() {
        return EXTRA_QNTY;
    }

    public void setEXTRA_QNTY(String EXTRA_QNTY) {
        this.EXTRA_QNTY = EXTRA_QNTY;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getITEM_DESC() {
        return ITEM_DESC;
    }

    public void setITEM_DESC(String ITEM_DESC) {
        this.ITEM_DESC = ITEM_DESC;
    }

    public String getITEM_NUMB() {
        return ITEM_NUMB;
    }

    public void setITEM_NUMB(String ITEM_NUMB) {
        this.ITEM_NUMB = ITEM_NUMB;
    }

    public String getLOCATION_CODE() {
        return LOCATION_CODE;
    }

    public void setLOCATION_CODE(String LOCATION_CODE) {
        this.LOCATION_CODE = LOCATION_CODE;
    }

    public String getMODE_OF_SHIP() {
        return MODE_OF_SHIP;
    }

    public void setMODE_OF_SHIP(String MODE_OF_SHIP) {
        this.MODE_OF_SHIP = MODE_OF_SHIP;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
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

    public String getPLAN_QNTY() {
        return PLAN_QNTY;
    }

    public void setPLAN_QNTY(String PLAN_QNTY) {
        this.PLAN_QNTY = PLAN_QNTY;
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

    public String getTDATE() {
        return TDATE;
    }

    public void setTDATE(String TDATE) {
        this.TDATE = TDATE;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getWEIGHT() {
        return WEIGHT;
    }

    public void setWEIGHT(String WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

}
