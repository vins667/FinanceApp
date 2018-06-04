/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class BuyerPortalMasterBean {
    
    private String BUYR;
    private String BYRGRUP;
    private String BEDI_APP;
    private String BPORTAL_APP;
    private String BIC_APP;
    private String DUE_DAYS;
    
    public BuyerPortalMasterBean(){
        
    }

    public BuyerPortalMasterBean(String BUYR, String BYRGRUP, String BEDI_APP, String BPORTAL_APP, String BIC_APP,String DUE_DAYS) {
        this.BUYR = BUYR;
        this.BYRGRUP = BYRGRUP;
        this.BEDI_APP = BEDI_APP;
        this.BPORTAL_APP = BPORTAL_APP;
        this.BIC_APP = BIC_APP;
        this.DUE_DAYS=DUE_DAYS;
    }

    public String getBUYR() {
        return BUYR;
    }

    public void setBUYR(String BUYR) {
        this.BUYR = BUYR;
    }

    public String getBYRGRUP() {
        return BYRGRUP;
    }

    public void setBYRGRUP(String BYRGRUP) {
        this.BYRGRUP = BYRGRUP;
    }

    public String getBEDI_APP() {
        return BEDI_APP;
    }

    public void setBEDI_APP(String BEDI_APP) {
        this.BEDI_APP = BEDI_APP;
    }

    public String getBPORTAL_APP() {
        return BPORTAL_APP;
    }

    public void setBPORTAL_APP(String BPORTAL_APP) {
        this.BPORTAL_APP = BPORTAL_APP;
    }

    public String getBIC_APP() {
        return BIC_APP;
    }

    public void setBIC_APP(String BIC_APP) {
        this.BIC_APP = BIC_APP;
    }

    public String getDUE_DAYS() {
        return DUE_DAYS;
    }

    public void setDUE_DAYS(String DUE_DAYS) {
        this.DUE_DAYS = DUE_DAYS;
    }
    
    
     
    
}
