/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;


public class PvhSubLstBean {
    private String PONO;
    private String STYLENO;
    private String HTSNO;
    private String DESC;
    private String QNTYPCS;
    private double RATE;
    private double AMT;
    private int CRTN;
    private String COLORCODE;
    private String COLORDESC;
    
   public PvhSubLstBean(){
        
    }

    public PvhSubLstBean(String PONO, String STYLENO, String HTSNO, String DESC, String QNTYPCS, double RATE, double AMT, int CRTN, String COLORCODE, String COLORDESC) {
        this.PONO = PONO;
        this.STYLENO = STYLENO;
        this.HTSNO = HTSNO;
        this.DESC = DESC;
        this.QNTYPCS = QNTYPCS;
        this.RATE = RATE;
        this.AMT = AMT;
        this.CRTN = CRTN;
        this.COLORCODE = COLORCODE;
        this.COLORDESC = COLORDESC;
    }

   
    public String getPONO() {
        return PONO;
    }

    public void setPONO(String PONO) {
        this.PONO = PONO;
    }

    public String getSTYLENO() {
        return STYLENO;
    }

    public void setSTYLENO(String STYLENO) {
        this.STYLENO = STYLENO;
    }

    public String getHTSNO() {
        return HTSNO;
    }

    public void setHTSNO(String HTSNO) {
        this.HTSNO = HTSNO;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    public String getQNTYPCS() {
        return QNTYPCS;
    }

    public void setQNTYPCS(String QNTYPCS) {
        this.QNTYPCS = QNTYPCS;
    }

    public double getRATE() {
        return RATE;
    }

    public void setRATE(double RATE) {
        this.RATE = RATE;
    }

    public double getAMT() {
        return AMT;
    }

    public void setAMT(double AMT) {
        this.AMT = AMT;
    }

    public int getCRTN() {
        return CRTN;
    }

    public void setCRTN(int CRTN) {
        this.CRTN = CRTN;
    }

    

     

    public String getCOLORCODE() {
        return COLORCODE;
    }

    public void setCOLORCODE(String COLORCODE) {
        this.COLORCODE = COLORCODE;
    }

    public String getCOLORDESC() {
        return COLORDESC;
    }

    public void setCOLORDESC(String COLORDESC) {
        this.COLORDESC = COLORDESC;
    }
   
   

    

    
   
    
}
