/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/**
 *
 * @author Sanjeev
 */
public class LicSearchBean {
    
    private String LIC_NO;
    private String LIC_DATE;
    private String LIC_PORT;
    private String LIC_PRCT;
    private String LIC_AMT;
    private String SALE_AMT;

    public LicSearchBean(String LIC_NO, String LIC_DATE, String LIC_PORT, String LIC_PRCT, String LIC_AMT, String SALE_AMT) {
        this.LIC_NO = LIC_NO;
        this.LIC_DATE = LIC_DATE;
        this.LIC_PORT = LIC_PORT;
        this.LIC_PRCT = LIC_PRCT;
        this.LIC_AMT = LIC_AMT;
        this.SALE_AMT = SALE_AMT;
    }

    public String getLIC_NO() {
        return LIC_NO;
    }

    public void setLIC_NO(String LIC_NO) {
        this.LIC_NO = LIC_NO;
    }

    public String getLIC_DATE() {
        return LIC_DATE;
    }

    public void setLIC_DATE(String LIC_DATE) {
        this.LIC_DATE = LIC_DATE;
    }

    public String getLIC_PORT() {
        return LIC_PORT;
    }

    public void setLIC_PORT(String LIC_PORT) {
        this.LIC_PORT = LIC_PORT;
    }

    public String getLIC_PRCT() {
        return LIC_PRCT;
    }

    public void setLIC_PRCT(String LIC_PRCT) {
        this.LIC_PRCT = LIC_PRCT;
    }

    public String getLIC_AMT() {
        return LIC_AMT;
    }

    public void setLIC_AMT(String LIC_AMT) {
        this.LIC_AMT = LIC_AMT;
    }

    public String getSALE_AMT() {
        return SALE_AMT;
    }

    public void setSALE_AMT(String SALE_AMT) {
        this.SALE_AMT = SALE_AMT;
    }
    
    
    
    
}
