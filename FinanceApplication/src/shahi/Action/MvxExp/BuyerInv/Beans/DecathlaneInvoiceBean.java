/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class DecathlaneInvoiceBean {
    
   
    private String INVOICE;
    private String INVOICEDATE;
     private String COMPANY;
    private String PLANNO;
    private String BUYER;
    private String TTODATE;
    private String TODATE;
    private String NDATE;
    private String INVQTY;
    private String SHIPQTY;
     private String YEARB;
    
    public DecathlaneInvoiceBean(){
        
    }

    public DecathlaneInvoiceBean(String INVOICE, String INVOICEDATE, String COMPANY, String PLANNO, String BUYER, String TTODATE, String TODATE, String NDATE, String INVQTY, String SHIPQTY, String YEARB) {
        this.INVOICE = INVOICE;
        this.INVOICEDATE = INVOICEDATE;
        this.COMPANY = COMPANY;
        this.PLANNO = PLANNO;
        this.BUYER = BUYER;
        this.TTODATE = TTODATE;
        this.TODATE = TODATE;
        this.NDATE = NDATE;
        this.INVQTY = INVQTY;
        this.SHIPQTY = SHIPQTY;
        this.YEARB = YEARB;
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

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getPLANNO() {
        return PLANNO;
    }

    public void setPLANNO(String PLANNO) {
        this.PLANNO = PLANNO;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getTTODATE() {
        return TTODATE;
    }

    public void setTTODATE(String TTODATE) {
        this.TTODATE = TTODATE;
    }

    public String getTODATE() {
        return TODATE;
    }

    public void setTODATE(String TODATE) {
        this.TODATE = TODATE;
    }

    public String getNDATE() {
        return NDATE;
    }

    public void setNDATE(String NDATE) {
        this.NDATE = NDATE;
    }

    public String getINVQTY() {
        return INVQTY;
    }

    public void setINVQTY(String INVQTY) {
        this.INVQTY = INVQTY;
    }

    public String getSHIPQTY() {
        return SHIPQTY;
    }

    public void setSHIPQTY(String SHIPQTY) {
        this.SHIPQTY = SHIPQTY;
    }

    public String getYEARB() {
        return YEARB;
    }

    public void setYEARB(String YEARB) {
        this.YEARB = YEARB;
    }

    
    
    
    
    
}
