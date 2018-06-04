/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author Sanjeev
 */
public class TOSumBean {
    private String ORDBY;
    private String MNTH;
    private String CRNCY_CODE;
    private Double QTY;
    private Double FOBFC;

    public TOSumBean(String ORDBY, String MNTH, String CRNCY_CODE, Double QTY, Double FOBFC) {
        this.ORDBY = ORDBY;
        this.MNTH = MNTH;
        this.CRNCY_CODE = CRNCY_CODE;
        this.QTY = QTY;
        this.FOBFC = FOBFC;
    }

    public String getORDBY() {
        return ORDBY;
    }

    public void setORDBY(String ORDBY) {
        this.ORDBY = ORDBY;
    }

    public String getMNTH() {
        return MNTH;
    }

    public void setMNTH(String MNTH) {
        this.MNTH = MNTH;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public Double getQTY() {
        return QTY;
    }

    public void setQTY(Double QTY) {
        this.QTY = QTY;
    }

    public Double getFOBFC() {
        return FOBFC;
    }

    public void setFOBFC(Double FOBFC) {
        this.FOBFC = FOBFC;
    }
    
    
    
    
}
