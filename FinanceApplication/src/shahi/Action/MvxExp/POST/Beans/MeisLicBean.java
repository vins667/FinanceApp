/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/**
 *
 * @author Sanjeev
 */
public class MeisLicBean {
    
    private String FACILITY;
    private String EXCS_INV_NO;
    private String BUYER;
    private String INV_DATE;
    private String REMARK;
    private String TAX_CODE;
    private String TAX_TYPE;
    private String TAX_PERCENT;
    private String FINAL_PRINT;
    private String COMPANY;

    
    public MeisLicBean(String FACILITY, String EXCS_INV_NO, String BUYER,  String INV_DATE, String REMARK, String TAX_CODE, String TAX_TYPE, String TAX_PERCENT, String FINAL_PRINT,String COMPANY) {
        this.FACILITY = FACILITY;
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.BUYER = BUYER;
        this.INV_DATE = INV_DATE;
        this.REMARK = REMARK;
        this.TAX_CODE = TAX_CODE;
        this.TAX_TYPE = TAX_TYPE;
        this.TAX_PERCENT = TAX_PERCENT;
        this.FINAL_PRINT = FINAL_PRINT;
        this.COMPANY=COMPANY;
    }
    
    

    public String getFACILITY() {
        return FACILITY;
    }

    public void setFACILITY(String FACILITY) {
        this.FACILITY = FACILITY;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
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

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public String getTAX_TYPE() {
        return TAX_TYPE;
    }

    public void setTAX_TYPE(String TAX_TYPE) {
        this.TAX_TYPE = TAX_TYPE;
    }

    public String getTAX_PERCENT() {
        return TAX_PERCENT;
    }

    public void setTAX_PERCENT(String TAX_PERCENT) {
        this.TAX_PERCENT = TAX_PERCENT;
    }

    public String getFINAL_PRINT() {
        return FINAL_PRINT;
    }

    public void setFINAL_PRINT(String FINAL_PRINT) {
        this.FINAL_PRINT = FINAL_PRINT;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }
     
    
}
