/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.cashbill.bean;

import java.util.List;
import shahi.Action.M4bill.Beans.GetTaxRateBean;
import shahi.Action.M4bill.Beans.M3BILLBean;

/**
 *
 * @author Vivek
 */
public class CashBillDetailBean {
    private String SL_NO;
    private String BILL_SL_NO;
    private String CC_CODE;
    private String CC_CODE_DESC;
    private String TYPE_SL_NO;
    private String TYPE_SL_NO_DESC;
    private String SUB_TYPE_SL_NO;
    private String SUB_TYPE_SL_NO_DESC;
    private String PRODUCT_SL_NO;
    private String PRODUCT_SL_NO_DESC;
    private String PRODUCT_AMOUNT;
    private String OPERATION_DESC;
    private String VOUCHER_DESC;
    private String PARTNER_DESC;
    private String TDATE;
    private String USER_ID;
    private String PCH;
    private String BILL_DATE1;
    private String BILL_DATE2;
    private String REMARKS;
    private String TAXABLE;
    private String NON_GST_AMOUNT;
    private String PRODUCT_QUANTITY;
    private String UOM;
    private String HSN_CODE;
    
    private List<GetTaxRateBean> GST_LIST;
    private List<M3BILLBean> BILLSUBTYPE_LIST;
    private List<M3BILLBean> BILLCC_LIST;
    private List<M3BILLBean> BILLPRODUCT_TYPE_LIST;

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getBILL_SL_NO() {
        return BILL_SL_NO;
    }

    public void setBILL_SL_NO(String BILL_SL_NO) {
        this.BILL_SL_NO = BILL_SL_NO;
    }

    public String getCC_CODE() {
        return CC_CODE;
    }

    public void setCC_CODE(String CC_CODE) {
        this.CC_CODE = CC_CODE;
    }

    public String getCC_CODE_DESC() {
        return CC_CODE_DESC;
    }

    public void setCC_CODE_DESC(String CC_CODE_DESC) {
        this.CC_CODE_DESC = CC_CODE_DESC;
    }

    public String getTYPE_SL_NO() {
        return TYPE_SL_NO;
    }

    public void setTYPE_SL_NO(String TYPE_SL_NO) {
        this.TYPE_SL_NO = TYPE_SL_NO;
    }

    public String getTYPE_SL_NO_DESC() {
        return TYPE_SL_NO_DESC;
    }

    public void setTYPE_SL_NO_DESC(String TYPE_SL_NO_DESC) {
        this.TYPE_SL_NO_DESC = TYPE_SL_NO_DESC;
    }

    public String getSUB_TYPE_SL_NO() {
        return SUB_TYPE_SL_NO;
    }

    public void setSUB_TYPE_SL_NO(String SUB_TYPE_SL_NO) {
        this.SUB_TYPE_SL_NO = SUB_TYPE_SL_NO;
    }

    public String getSUB_TYPE_SL_NO_DESC() {
        return SUB_TYPE_SL_NO_DESC;
    }

    public void setSUB_TYPE_SL_NO_DESC(String SUB_TYPE_SL_NO_DESC) {
        this.SUB_TYPE_SL_NO_DESC = SUB_TYPE_SL_NO_DESC;
    }

    public String getPRODUCT_SL_NO() {
        return PRODUCT_SL_NO;
    }

    public void setPRODUCT_SL_NO(String PRODUCT_SL_NO) {
        this.PRODUCT_SL_NO = PRODUCT_SL_NO;
    }

    public String getPRODUCT_SL_NO_DESC() {
        return PRODUCT_SL_NO_DESC;
    }

    public void setPRODUCT_SL_NO_DESC(String PRODUCT_SL_NO_DESC) {
        this.PRODUCT_SL_NO_DESC = PRODUCT_SL_NO_DESC;
    }

    public String getPRODUCT_AMOUNT() {
        return PRODUCT_AMOUNT;
    }

    public void setPRODUCT_AMOUNT(String PRODUCT_AMOUNT) {
        this.PRODUCT_AMOUNT = PRODUCT_AMOUNT;
    }

    public String getTDATE() {
        return TDATE;
    }

    public void setTDATE(String TDATE) {
        this.TDATE = TDATE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getBILL_DATE1() {
        return BILL_DATE1;
    }

    public void setBILL_DATE1(String BILL_DATE1) {
        this.BILL_DATE1 = BILL_DATE1;
    }

    public String getBILL_DATE2() {
        return BILL_DATE2;
    }

    public void setBILL_DATE2(String BILL_DATE2) {
        this.BILL_DATE2 = BILL_DATE2;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getTAXABLE() {
        return TAXABLE;
    }

    public void setTAXABLE(String TAXABLE) {
        this.TAXABLE = TAXABLE;
    }

    public String getNON_GST_AMOUNT() {
        return NON_GST_AMOUNT;
    }

    public void setNON_GST_AMOUNT(String NON_GST_AMOUNT) {
        this.NON_GST_AMOUNT = NON_GST_AMOUNT;
    }

    public String getHSN_CODE() {
        return HSN_CODE;
    }

    public void setHSN_CODE(String HSN_CODE) {
        this.HSN_CODE = HSN_CODE;
    }

    public String getVOUCHER_DESC() {
        return VOUCHER_DESC;
    }

    public void setVOUCHER_DESC(String VOUCHER_DESC) {
        this.VOUCHER_DESC = VOUCHER_DESC;
    }

    public List<M3BILLBean> getBILLSUBTYPE_LIST() {
        return BILLSUBTYPE_LIST;
    }

    public String getOPERATION_DESC() {
        return OPERATION_DESC;
    }

    public void setOPERATION_DESC(String OPERATION_DESC) {
        this.OPERATION_DESC = OPERATION_DESC;
    }

    public String getPRODUCT_QUANTITY() {
        return PRODUCT_QUANTITY;
    }

    public void setPRODUCT_QUANTITY(String PRODUCT_QUANTITY) {
        this.PRODUCT_QUANTITY = PRODUCT_QUANTITY;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public void setBILLSUBTYPE_LIST(List<M3BILLBean> BILLSUBTYPE_LIST) {
        this.BILLSUBTYPE_LIST = BILLSUBTYPE_LIST;
    }

    public List<M3BILLBean> getBILLCC_LIST() {
        return BILLCC_LIST;
    }

    public void setBILLCC_LIST(List<M3BILLBean> BILLCC_LIST) {
        this.BILLCC_LIST = BILLCC_LIST;
    }

    public List<M3BILLBean> getBILLPRODUCT_TYPE_LIST() {
        return BILLPRODUCT_TYPE_LIST;
    }

    public void setBILLPRODUCT_TYPE_LIST(List<M3BILLBean> BILLPRODUCT_TYPE_LIST) {
        this.BILLPRODUCT_TYPE_LIST = BILLPRODUCT_TYPE_LIST;
    }

    public List<GetTaxRateBean> getGST_LIST() {
        return GST_LIST;
    }

    public void setGST_LIST(List<GetTaxRateBean> GST_LIST) {
        this.GST_LIST = GST_LIST;
    }

    public String getPARTNER_DESC() {
        return PARTNER_DESC;
    }

    public void setPARTNER_DESC(String PARTNER_DESC) {
        this.PARTNER_DESC = PARTNER_DESC;
    }
    
}
