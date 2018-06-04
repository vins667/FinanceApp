/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author Guddu Kumar
 */
public class ItemBean {
    private String PCH;
    private String ITM;
    private String ORDERQTY;
    private String DLVQTY;
    private String INVQTY;
    private String SHIPQTY;
    private double ORDERPER;
    private String BUYER;
    private String FOBAMT;
    private double TOT_ORDRQTY;
    private double TOT_DLVQTY;
    private double TOT_INVQTY;
    private double TOT_SHIPQTY;
    private double TOT_PERORDRQTY;
    private double TOT_FOBQTY;
    
    
    
    public ItemBean(){
        
    }

    public ItemBean(String PCH, String ITM, String ORDERQTY, String DLVQTY, String INVQTY, String SHIPQTY, double ORDERPER, String BUYER, String FOBAMT, double TOT_ORDRQTY, double TOT_DLVQTY, double TOT_INVQTY, double TOT_SHIPQTY, double TOT_PERORDRQTY, double TOT_FOBQTY) {
        this.PCH = PCH;
        this.ITM = ITM;
        this.ORDERQTY = ORDERQTY;
        this.DLVQTY = DLVQTY;
        this.INVQTY = INVQTY;
        this.SHIPQTY = SHIPQTY;
        this.ORDERPER = ORDERPER;
        this.BUYER = BUYER;
        this.FOBAMT = FOBAMT;
        this.TOT_ORDRQTY = TOT_ORDRQTY;
        this.TOT_DLVQTY = TOT_DLVQTY;
        this.TOT_INVQTY = TOT_INVQTY;
        this.TOT_SHIPQTY = TOT_SHIPQTY;
        this.TOT_PERORDRQTY = TOT_PERORDRQTY;
        this.TOT_FOBQTY = TOT_FOBQTY;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getITM() {
        return ITM;
    }

    public void setITM(String ITM) {
        this.ITM = ITM;
    }

    public String getORDERQTY() {
        return ORDERQTY;
    }

    public void setORDERQTY(String ORDERQTY) {
        this.ORDERQTY = ORDERQTY;
    }

    public String getDLVQTY() {
        return DLVQTY;
    }

    public void setDLVQTY(String DLVQTY) {
        this.DLVQTY = DLVQTY;
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

    public double getORDERPER() {
        return ORDERPER;
    }

    public void setORDERPER(double ORDERPER) {
        this.ORDERPER = ORDERPER;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getFOBAMT() {
        return FOBAMT;
    }

    public void setFOBAMT(String FOBAMT) {
        this.FOBAMT = FOBAMT;
    }

    public double getTOT_ORDRQTY() {
        return TOT_ORDRQTY;
    }

    public void setTOT_ORDRQTY(double TOT_ORDRQTY) {
        this.TOT_ORDRQTY = TOT_ORDRQTY;
    }

    public double getTOT_DLVQTY() {
        return TOT_DLVQTY;
    }

    public void setTOT_DLVQTY(double TOT_DLVQTY) {
        this.TOT_DLVQTY = TOT_DLVQTY;
    }

    public double getTOT_INVQTY() {
        return TOT_INVQTY;
    }

    public void setTOT_INVQTY(double TOT_INVQTY) {
        this.TOT_INVQTY = TOT_INVQTY;
    }

    public double getTOT_SHIPQTY() {
        return TOT_SHIPQTY;
    }

    public void setTOT_SHIPQTY(double TOT_SHIPQTY) {
        this.TOT_SHIPQTY = TOT_SHIPQTY;
    }

    public double getTOT_PERORDRQTY() {
        return TOT_PERORDRQTY;
    }

    public void setTOT_PERORDRQTY(double TOT_PERORDRQTY) {
        this.TOT_PERORDRQTY = TOT_PERORDRQTY;
    }

    public double getTOT_FOBQTY() {
        return TOT_FOBQTY;
    }

    public void setTOT_FOBQTY(double TOT_FOBQTY) {
        this.TOT_FOBQTY = TOT_FOBQTY;
    }

   
    

    
    
}
