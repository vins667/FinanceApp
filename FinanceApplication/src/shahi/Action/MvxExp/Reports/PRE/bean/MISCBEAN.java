/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author Sanjeev
 */
public class MISCBEAN {
    
    private String MISC_DESC;
    private String MISC_QTY;
    private double MISC_RATE;
    private double MISC_FOB;

    public MISCBEAN(String MISC_DESC, String MISC_QTY, double MISC_RATE, double MISC_FOB) {
        this.MISC_DESC = MISC_DESC;
        this.MISC_QTY = MISC_QTY;
        this.MISC_RATE = MISC_RATE;
        this.MISC_FOB = MISC_FOB;
    }

    
    
    
    
    public String getMISC_DESC() {
        return MISC_DESC;
    }

    public void setMISC_DESC(String MISC_DESC) {
        this.MISC_DESC = MISC_DESC;
    }

    public String getMISC_QTY() {
        return MISC_QTY;
    }

    public void setMISC_QTY(String MISC_QTY) {
        this.MISC_QTY = MISC_QTY;
    }

    

    public double getMISC_RATE() {
        return MISC_RATE;
    }

    public void setMISC_RATE(double MISC_RATE) {
        this.MISC_RATE = MISC_RATE;
    }

    public double getMISC_FOB() {
        return MISC_FOB;
    }

    public void setMISC_FOB(double MISC_FOB) {
        this.MISC_FOB = MISC_FOB;
    }
    
    
    
}
