/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;

/**
 *
 * @author Sanjeev
 */
public class MEISAPPBEAN {
    
    private String APP_NO;
    private String APP_DATE;
    private String PORT_CODE;
    private String APP_TYPE;
    private String LIC_NO;
    private String LIC_DATE;
    private String LIC_AMT;
    private String FWD_PORT;
    private String FWD_AC;
    private String SALE_AMT;
    private String SALE_DATE;

    public MEISAPPBEAN(String APP_NO, String APP_DATE, String PORT_CODE, String APP_TYPE, String LIC_NO, String LIC_DATE, 
                       String LIC_AMT, String FWD_PORT, String FWD_AC,String SALE_AMT,String SALE_DATE) {
        this.APP_NO = APP_NO;
        this.APP_DATE = APP_DATE;
        this.PORT_CODE = PORT_CODE;
        this.APP_TYPE = APP_TYPE;
        this.LIC_NO = LIC_NO;
        this.LIC_DATE = LIC_DATE;
        this.LIC_AMT = LIC_AMT;
        this.FWD_PORT = FWD_PORT;
        this.FWD_AC = FWD_AC;
        this.SALE_AMT=SALE_AMT;
        this.SALE_DATE=SALE_DATE;
    }

    
    
    
    
    
    public String getAPP_NO() {
        return APP_NO;
    }

    public void setAPP_NO(String APP_NO) {
        this.APP_NO = APP_NO;
    }

    public String getAPP_DATE() {
        return APP_DATE;
    }

    public void setAPP_DATE(String APP_DATE) {
        this.APP_DATE = APP_DATE;
    }

    public String getPORT_CODE() {
        return PORT_CODE;
    }

    public void setPORT_CODE(String PORT_CODE) {
        this.PORT_CODE = PORT_CODE;
    }

    public String getAPP_TYPE() {
        return APP_TYPE;
    }

    public void setAPP_TYPE(String APP_TYPE) {
        this.APP_TYPE = APP_TYPE;
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

    public String getLIC_AMT() {
        return LIC_AMT;
    }

    public void setLIC_AMT(String LIC_AMT) {
        this.LIC_AMT = LIC_AMT;
    }

    public String getFWD_PORT() {
        return FWD_PORT;
    }

    public void setFWD_PORT(String FWD_PORT) {
        this.FWD_PORT = FWD_PORT;
    }

    public String getFWD_AC() {
        return FWD_AC;
    }

    public void setFWD_AC(String FWD_AC) {
        this.FWD_AC = FWD_AC;
    }

    public String getSALE_AMT() {
        return SALE_AMT;
    }

    public void setSALE_AMT(String SALE_AMT) {
        this.SALE_AMT = SALE_AMT;
    }

    public String getSALE_DATE() {
        return SALE_DATE;
    }

    public void setSALE_DATE(String SALE_DATE) {
        this.SALE_DATE = SALE_DATE;
    }
    
    
    
    
}
