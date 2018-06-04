/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class InvCbmBean {
    
    private String BOX_LN;
    private String BOX_WD;
    private String BOX_HT;
    private String CT_UOM;
    private Double CBM;
    private Double CFT;
    private Double BOX_VOL;
    private Double INV_PKGS;

    public InvCbmBean(String BOX_LN, String BOX_WD, String BOX_HT, String CT_UOM, Double CBM, Double CFT, Double BOX_VOL, Double INV_PKGS) {
        this.BOX_LN = BOX_LN;
        this.BOX_WD = BOX_WD;
        this.BOX_HT = BOX_HT;
        this.CT_UOM = CT_UOM;
        this.CBM = CBM;
        this.CFT = CFT;
        this.BOX_VOL = BOX_VOL;
        this.INV_PKGS = INV_PKGS;
    }

    public String getBOX_LN() {
        return BOX_LN;
    }

    public void setBOX_LN(String BOX_LN) {
        this.BOX_LN = BOX_LN;
    }

    public String getBOX_WD() {
        return BOX_WD;
    }

    public void setBOX_WD(String BOX_WD) {
        this.BOX_WD = BOX_WD;
    }

    public String getBOX_HT() {
        return BOX_HT;
    }

    public void setBOX_HT(String BOX_HT) {
        this.BOX_HT = BOX_HT;
    }

    public String getCT_UOM() {
        return CT_UOM;
    }

    public void setCT_UOM(String CT_UOM) {
        this.CT_UOM = CT_UOM;
    }

    public Double getCBM() {
        return CBM;
    }

    public void setCBM(Double CBM) {
        this.CBM = CBM;
    }

    public Double getCFT() {
        return CFT;
    }

    public void setCFT(Double CFT) {
        this.CFT = CFT;
    }

    public Double getBOX_VOL() {
        return BOX_VOL;
    }

    public void setBOX_VOL(Double BOX_VOL) {
        this.BOX_VOL = BOX_VOL;
    }

    public Double getINV_PKGS() {
        return INV_PKGS;
    }

    public void setINV_PKGS(Double INV_PKGS) {
        this.INV_PKGS = INV_PKGS;
    }
    
    
           
    
}
