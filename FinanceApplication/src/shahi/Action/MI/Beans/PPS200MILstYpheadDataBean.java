/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

/**
 *
 * @author VIVEK
 */
public class PPS200MILstYpheadDataBean {
    private String CONO;  //Company
    private String DIVI;  //Division
    private String PUNO;  //Purcahse Order Number
    private String SFAP;  //Sent for Approval
    private String APPR;  //Approved
    private String FACI;  //Facilty
    private String WHLO;  //Warehouse
    private String ORTY;  //Order Type1

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public String getSFAP() {
        return SFAP;
    }

    public void setSFAP(String SFAP) {
        this.SFAP = SFAP;
    }

    public String getAPPR() {
        return APPR;
    }

    public void setAPPR(String APPR) {
        this.APPR = APPR;
    }

    public String getFACI() {
        return FACI;
    }

    public void setFACI(String FACI) {
        this.FACI = FACI;
    }

    public String getWHLO() {
        return WHLO;
    }

    public void setWHLO(String WHLO) {
        this.WHLO = WHLO;
    }

    public String getORTY() {
        return ORTY;
    }

    public void setORTY(String ORTY) {
        this.ORTY = ORTY;
    }

    public PPS200MILstYpheadDataBean() {
    }

    public PPS200MILstYpheadDataBean(String CONO, String DIVI, String PUNO, String SFAP, String APPR, String FACI, String WHLO, String ORTY) {
        this.CONO = CONO;
        this.DIVI = DIVI;
        this.PUNO = PUNO;
        this.SFAP = SFAP;
        this.APPR = APPR;
        this.FACI = FACI;
        this.WHLO = WHLO;
        this.ORTY = ORTY;
    }


}
