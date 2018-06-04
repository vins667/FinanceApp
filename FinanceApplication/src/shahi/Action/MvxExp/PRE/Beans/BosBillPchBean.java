/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class BosBillPchBean {
    
    private String LOCT;
    private String PCH; 
    private double PLAN_CFT;
    private double CFT_PER;
    private double QTY;
    private double QTY_PER;
    private double CTNS;
    private double CTNS_PER;

    public BosBillPchBean(String LOCT,String PCH, double PLAN_CFT, double CFT_PER, double QTY, double QTY_PER, double CTNS, double CTNS_PER) {
        this.LOCT=LOCT;
        this.PCH = PCH;
        this.PLAN_CFT = PLAN_CFT;
        this.CFT_PER = CFT_PER;
        this.QTY = QTY;
        this.QTY_PER = QTY_PER;
        this.CTNS = CTNS;
        this.CTNS_PER = CTNS_PER;
    }

    public String getLOCT() {
        return LOCT;
    }

    public void setLOCT(String LOCT) {
        this.LOCT = LOCT;
    }

    
      
    
    
    
    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public double getPLAN_CFT() {
        return PLAN_CFT;
    }

    public void setPLAN_CFT(double PLAN_CFT) {
        this.PLAN_CFT = PLAN_CFT;
    }

    public double getCFT_PER() {
        return CFT_PER;
    }

    public void setCFT_PER(double CFT_PER) {
        this.CFT_PER = CFT_PER;
    }

    public double getQTY() {
        return QTY;
    }

    public void setQTY(double QTY) {
        this.QTY = QTY;
    }

    public double getQTY_PER() {
        return QTY_PER;
    }

    public void setQTY_PER(double QTY_PER) {
        this.QTY_PER = QTY_PER;
    }

    public double getCTNS() {
        return CTNS;
    }

    public void setCTNS(double CTNS) {
        this.CTNS = CTNS;
    }

    public double getCTNS_PER() {
        return CTNS_PER;
    }

    public void setCTNS_PER(double CTNS_PER) {
        this.CTNS_PER = CTNS_PER;
    }

    
     
    
}
