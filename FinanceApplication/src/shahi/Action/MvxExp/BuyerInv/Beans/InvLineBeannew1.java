/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class InvLineBeannew1 {
    private String HSNCODE;
    private String UNIT;
    private String INV_DESC;
    private String INV_QTY;
    private double KGS_QTY;
    private double INV_RATE;
    private double FOB_FC;
    private String TRIF_HSN;
    
    public InvLineBeannew1(){
        
    }

    public InvLineBeannew1(String HSNCODE, String UNIT, String INV_DESC, String INV_QTY, double KGS_QTY, double INV_RATE, double FOB_FC, String TRIF_HSN) {
        this.HSNCODE = HSNCODE;
        this.UNIT = UNIT;
        this.INV_DESC = INV_DESC;
        this.INV_QTY = INV_QTY;
        this.KGS_QTY = KGS_QTY;
        this.INV_RATE = INV_RATE;
        this.FOB_FC = FOB_FC;
        this.TRIF_HSN = TRIF_HSN;
    }

    public String getHSNCODE() {
        return HSNCODE;
    }

    public void setHSNCODE(String HSNCODE) {
        this.HSNCODE = HSNCODE;
    }

    public String getUNIT() {
        return UNIT;
    }

    public void setUNIT(String UNIT) {
        this.UNIT = UNIT;
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

    public double getKGS_QTY() {
        return KGS_QTY;
    }

    public void setKGS_QTY(double KGS_QTY) {
        this.KGS_QTY = KGS_QTY;
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

    public String getTRIF_HSN() {
        return TRIF_HSN;
    }

    public void setTRIF_HSN(String TRIF_HSN) {
        this.TRIF_HSN = TRIF_HSN;
    }

    
    
    
}
