/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.Mis.Beans;

/**
 *
 * @author RANJEET SINGH
 */
public class ShahiInformationBean {
    
    private String WHLO;
    private String WHNM;
    
    private String SLTP;
    private String SSLTP;
    private String SSLDS;
    private String WHSL;
    

    public ShahiInformationBean(String WHLO, String WHNM) {
        this.WHLO = WHLO;
        this.WHNM = WHNM;
    }

    public ShahiInformationBean(String WHLO, String WHSL, String SLTP, String SSLTP, String SSLDS) {
        this.WHLO = WHLO;
        this.WHSL = WHSL;
        this.SLTP = SLTP;
        this.SSLTP = SSLTP;
        this.SSLDS = SSLDS;
    }

    
   
    
    
    public String getWHLO() {
        return WHLO;
    }

    public void setWHLO(String WHLO) {
        this.WHLO = WHLO;
    }

    public String getWHNM() {
        return WHNM;
    }

    public void setWHNM(String WHNM) {
        this.WHNM = WHNM;
    }

    public String getSLTP() {
        return SLTP;
    }

    public void setSLTP(String SLTP) {
        this.SLTP = SLTP;
    }

    public String getSSLDS() {
        return SSLDS;
    }

    public void setSSLDS(String SSLDS) {
        this.SSLDS = SSLDS;
    }

    public String getSSLTP() {
        return SSLTP;
    }

    public void setSSLTP(String SSLTP) {
        this.SSLTP = SSLTP;
    }

    public String getWHSL() {
        return WHSL;
    }

    public void setWHSL(String WHSL) {
        this.WHSL = WHSL;
    }
    
    
    
    
}
