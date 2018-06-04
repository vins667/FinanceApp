/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author Sanjeev
 */
public class InvTempBean {
    
    
    private String UNIT;
    private String INV_DESC;
    private String INV_QTY;

    
    public InvTempBean(String UNIT, String INV_DESC, String INV_QTY) {
       
        this.UNIT = UNIT;
        this.INV_DESC = INV_DESC;
        this.INV_QTY = INV_QTY;
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
    
    
    
    
}
