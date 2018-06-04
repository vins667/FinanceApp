/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill.Beans;

import java.util.List;

/**
 *
 * @author Shilpa
 */
public class M3BillReportBean {
    
    private String DEPT_SL_NO;
    private String DEPT_DESC;
    private String TYPE_SL_NO;
    private String TYPE_DESC;
    private String SUB_TYPE_SL_NO;
    private String SUB_TYPE_DESC;
    private String PCH;
    private String CC_CODE_DESC;
    private String PRODUCT_SL_NO;
    private String PRODUCT_DESC;
    private double PRODUCT_AMOUNT;
    private String BILL_NO;
    private List INV_LIST;

    public List getINV_LIST() {
        return INV_LIST;
    }

    public void setINV_LIST(List INV_LIST) {
        this.INV_LIST = INV_LIST;
    }
    
    

    public String getDEPT_SL_NO() {
        return DEPT_SL_NO;
    }

    public void setDEPT_SL_NO(String DEPT_SL_NO) {
        this.DEPT_SL_NO = DEPT_SL_NO;
    }

    public String getDEPT_DESC() {
        return DEPT_DESC;
    }

    public void setDEPT_DESC(String DEPT_DESC) {
        this.DEPT_DESC = DEPT_DESC;
    }

    public String getTYPE_SL_NO() {
        return TYPE_SL_NO;
    }

    public void setTYPE_SL_NO(String TYPE_SL_NO) {
        this.TYPE_SL_NO = TYPE_SL_NO;
    }

    public String getTYPE_DESC() {
        return TYPE_DESC;
    }

    public void setTYPE_DESC(String TYPE_DESC) {
        this.TYPE_DESC = TYPE_DESC;
    }

    public String getSUB_TYPE_SL_NO() {
        return SUB_TYPE_SL_NO;
    }

    public void setSUB_TYPE_SL_NO(String SUB_TYPE_SL_NO) {
        this.SUB_TYPE_SL_NO = SUB_TYPE_SL_NO;
    }

    public String getSUB_TYPE_DESC() {
        return SUB_TYPE_DESC;
    }

    public void setSUB_TYPE_DESC(String SUB_TYPE_DESC) {
        this.SUB_TYPE_DESC = SUB_TYPE_DESC;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getCC_CODE_DESC() {
        return CC_CODE_DESC;
    }

    public void setCC_CODE_DESC(String CC_CODE_DESC) {
        this.CC_CODE_DESC = CC_CODE_DESC;
    }

    public String getPRODUCT_SL_NO() {
        return PRODUCT_SL_NO;
    }

    public void setPRODUCT_SL_NO(String PRODUCT_SL_NO) {
        this.PRODUCT_SL_NO = PRODUCT_SL_NO;
    }

    public String getPRODUCT_DESC() {
        return PRODUCT_DESC;
    }

    public void setPRODUCT_DESC(String PRODUCT_DESC) {
        this.PRODUCT_DESC = PRODUCT_DESC;
    }

    public double getPRODUCT_AMOUNT() {
        return PRODUCT_AMOUNT;
    }

    public void setPRODUCT_AMOUNT(double PRODUCT_AMOUNT) {
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
    }

    public String getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    
}
