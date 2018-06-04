/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author User
 */
public class ScrapSaleInvoiceBean {
     private String WHO;
    private String COMPANY;
    private String INVOICE;
    private String INVOICEDATE;
    private String FACTORYUNIT;
    private String BUYER;
    private String CRNCYCODE;
    private String PMNTMETHOD;
    private String PMNTTRM;
    private String SALEGLCODE;
    private String FINALPRINT;
    private String BUYERADD;

    public ScrapSaleInvoiceBean(String WHO, String COMPANY, String INVOICE, String INVOICEDATE, String FACTORYUNIT, String BUYER, String CRNCYCODE, String PMNTMETHOD, String PMNTTRM, String SALEGLCODE, String FINALPRINT, String BUYERADD) {
        this.WHO = WHO;
        this.COMPANY = COMPANY;
        this.INVOICE = INVOICE;
        this.INVOICEDATE = INVOICEDATE;
        this.FACTORYUNIT = FACTORYUNIT;
        this.BUYER = BUYER;
        this.CRNCYCODE = CRNCYCODE;
        this.PMNTMETHOD = PMNTMETHOD;
        this.PMNTTRM = PMNTTRM;
        this.SALEGLCODE = SALEGLCODE;
        this.FINALPRINT = FINALPRINT;
        this.BUYERADD = BUYERADD;
    }

    public String getWHO() {
        return WHO;
    }

    public void setWHO(String WHO) {
        this.WHO = WHO;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getINVOICE() {
        return INVOICE;
    }

    public void setINVOICE(String INVOICE) {
        this.INVOICE = INVOICE;
    }

    public String getINVOICEDATE() {
        return INVOICEDATE;
    }

    public void setINVOICEDATE(String INVOICEDATE) {
        this.INVOICEDATE = INVOICEDATE;
    }

    public String getFACTORYUNIT() {
        return FACTORYUNIT;
    }

    public void setFACTORYUNIT(String FACTORYUNIT) {
        this.FACTORYUNIT = FACTORYUNIT;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getCRNCYCODE() {
        return CRNCYCODE;
    }

    public void setCRNCYCODE(String CRNCYCODE) {
        this.CRNCYCODE = CRNCYCODE;
    }

    public String getPMNTMETHOD() {
        return PMNTMETHOD;
    }

    public void setPMNTMETHOD(String PMNTMETHOD) {
        this.PMNTMETHOD = PMNTMETHOD;
    }

    public String getPMNTTRM() {
        return PMNTTRM;
    }

    public void setPMNTTRM(String PMNTTRM) {
        this.PMNTTRM = PMNTTRM;
    }

    public String getSALEGLCODE() {
        return SALEGLCODE;
    }

    public void setSALEGLCODE(String SALEGLCODE) {
        this.SALEGLCODE = SALEGLCODE;
    }

    public String getFINALPRINT() {
        return FINALPRINT;
    }

    public void setFINALPRINT(String FINALPRINT) {
        this.FINALPRINT = FINALPRINT;
    }

    public String getBUYERADD() {
        return BUYERADD;
    }

    public void setBUYERADD(String BUYERADD) {
        this.BUYERADD = BUYERADD;
    }

   
    
    
    
    
   
}
