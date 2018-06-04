/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE;

/**
 *
 * @author Sanjeev
 */
public class InvLineBean1 {
    private String HSNCODE;
    private String INV_DESC;
    private String INV_QTY;
     private String UNIT;
    private double INV_RATE;
    private double FOB_FC;

   

    public InvLineBean1() {
      
    }

    public InvLineBean1(String HSNCODE, String INV_DESC, String INV_QTY, String UNIT, double INV_RATE, double FOB_FC) {
        this.HSNCODE = HSNCODE;
        this.INV_DESC = INV_DESC;
        this.INV_QTY = INV_QTY;
        this.UNIT = UNIT;
        this.INV_RATE = INV_RATE;
        this.FOB_FC = FOB_FC;
    }

    public String getHSNCODE() {
        return HSNCODE;
    }

    public void setHSNCODE(String HSNCODE) {
        this.HSNCODE = HSNCODE;
    }

    public String getINV_DESC() {
        return INV_DESC;
    }

    public void setINV_DESC(String INV_DESC) {
        this.INV_DESC = INV_DESC;
    }

    public String getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(String INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public String getUNIT() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
    }

    public double getINV_RATE() {
        return INV_RATE;
    }

    public void setINV_RATE(double INV_RATE) {
        this.INV_RATE = INV_RATE;
    }

    public double getFOB_FC() {
        return FOB_FC;
    }

    public void setFOB_FC(double FOB_FC) {
        this.FOB_FC = FOB_FC;
    }

   
    
     
    
}
