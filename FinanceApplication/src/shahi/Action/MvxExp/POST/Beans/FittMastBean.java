/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/**
 *
 * @author Sanjeev
 */
public class FittMastBean {
    
    private String FITTNO;
    private String FITTDATE;
    private String BUYER;
    private String CRNCY_CODE;
    private String FOB_AMT;
    private String ADV_AMT;
    private String REM1;   

    public FittMastBean(String FITTNO, String FITTDATE, String BUYER, String CRNCY_CODE, String FOB_AMT, String ADV_AMT, String REM1) {
        this.FITTNO = FITTNO;
        this.FITTDATE = FITTDATE;
        this.BUYER = BUYER;
        this.CRNCY_CODE = CRNCY_CODE;
        this.FOB_AMT = FOB_AMT;
        this.ADV_AMT = ADV_AMT;
        this.REM1 = REM1;
    }

    public String getFITTNO() {
        return FITTNO;
    }

    public void setFITTNO(String FITTNO) {
        this.FITTNO = FITTNO;
    }

    public String getFITTDATE() {
        return FITTDATE;
    }

    public void setFITTDATE(String FITTDATE) {
        this.FITTDATE = FITTDATE;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public String getFOB_AMT() {
        return FOB_AMT;
    }

    public void setFOB_AMT(String FOB_AMT) {
        this.FOB_AMT = FOB_AMT;
    }

    public String getADV_AMT() {
        return ADV_AMT;
    }

    public void setADV_AMT(String ADV_AMT) {
        this.ADV_AMT = ADV_AMT;
    }

    public String getREM1() {
        return REM1;
    }

    public void setREM1(String REM1) {
        this.REM1 = REM1;
    }
    
    
     
    
}
