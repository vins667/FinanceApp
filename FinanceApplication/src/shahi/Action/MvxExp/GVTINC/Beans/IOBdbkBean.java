/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;

/**
 *
 * @author Sanjeev
 */
public class IOBdbkBean {
    private String TRDATE;
    private String SB_NO;
    private String SB_DATE;
    private String SCROLL_NO;
    private String REMAKS;
    private String AMT_DR;
    private String AMT_CR;

    public IOBdbkBean(String TRDATE, String SB_NO, String SB_DATE, String SCROLL_NO, String REMAKS, String AMT_DR, String AMT_CR) {
        this.TRDATE = TRDATE;
        this.SB_NO = SB_NO;
        this.SB_DATE = SB_DATE;
        this.SCROLL_NO = SCROLL_NO;
        this.REMAKS = REMAKS;
        this.AMT_DR = AMT_DR;
        this.AMT_CR = AMT_CR;
    }

    
    
    
    
    
    public String getTRDATE() {
        return TRDATE;
    }

    public void setTRDATE(String TRDATE) {
        this.TRDATE = TRDATE;
    }

    public String getSB_NO() {
        return SB_NO;
    }

    public void setSB_NO(String SB_NO) {
        this.SB_NO = SB_NO;
    }

    public String getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(String SB_DATE) {
        this.SB_DATE = SB_DATE;
    }

    public String getSCROLL_NO() {
        return SCROLL_NO;
    }

    public void setSCROLL_NO(String SCROLL_NO) {
        this.SCROLL_NO = SCROLL_NO;
    }

    public String getREMAKS() {
        return REMAKS;
    }

    public void setREMAKS(String REMAKS) {
        this.REMAKS = REMAKS;
    }

    public String getAMT_DR() {
        return AMT_DR;
    }

    public void setAMT_DR(String AMT_DR) {
        this.AMT_DR = AMT_DR;
    }

    public String getAMT_CR() {
        return AMT_CR;
    }

    public void setAMT_CR(String AMT_CR) {
        this.AMT_CR = AMT_CR;
    }

    

     
    
    
    
     
    
    
}
