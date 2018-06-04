/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;

/**
 *
 * @author Sanjeev
 */
public class EBRCBEAN {
    
    private String PORT;
    private String SB_NO;
    private String SB_DATE;
    private String BRC_NO;
    private String BRC_DATE;
    private double RL_AMT;
    private String RL_DATE;
    private String CRNCY_CODE;

    public EBRCBEAN(String PORT, String SB_NO, String SB_DATE, String BRC_NO, String BRC_DATE, double RL_AMT, String RL_DATE, String CRNCY_CODE) {
        this.PORT = PORT;
        this.SB_NO = SB_NO;
        this.SB_DATE = SB_DATE;
        this.BRC_NO = BRC_NO;
        this.BRC_DATE = BRC_DATE;
        this.RL_AMT = RL_AMT;
        this.RL_DATE = RL_DATE;
        this.CRNCY_CODE = CRNCY_CODE;
    }

    
    
    
    
    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
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

    public String getBRC_NO() {
        return BRC_NO;
    }

    public void setBRC_NO(String BRC_NO) {
        this.BRC_NO = BRC_NO;
    }

    public String getBRC_DATE() {
        return BRC_DATE;
    }

    public void setBRC_DATE(String BRC_DATE) {
        this.BRC_DATE = BRC_DATE;
    }

    public double getRL_AMT() {
        return RL_AMT;
    }

    public void setRL_AMT(double RL_AMT) {
        this.RL_AMT = RL_AMT;
    }

    public String getRL_DATE() {
        return RL_DATE;
    }

    public void setRL_DATE(String RL_DATE) {
        this.RL_DATE = RL_DATE;
    }

    
 

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }
    
    
    
    
}
