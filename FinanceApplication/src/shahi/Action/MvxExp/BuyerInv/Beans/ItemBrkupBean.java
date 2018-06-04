/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class ItemBrkupBean {
    
    private String ITEMNO;
    private String ITEMID;
    private String ITEMRATIO;
    private String ITEMPCKGS;
    private double ITEMQTY;
   
    public ItemBrkupBean(){
        
    }

    public ItemBrkupBean(String ITEMNO, String ITEMID, String ITEMRATIO, String ITEMPCKGS, double ITEMQTY) {
        this.ITEMNO = ITEMNO;
        this.ITEMID = ITEMID;
        this.ITEMRATIO = ITEMRATIO;
        this.ITEMPCKGS = ITEMPCKGS;
        this.ITEMQTY = ITEMQTY;
    }
    public ItemBrkupBean(String ITEMNO) {
        this.ITEMNO = ITEMNO;
      
    }

    public String getITEMNO() {
        return ITEMNO;
    }

    public void setITEMNO(String ITEMNO) {
        this.ITEMNO = ITEMNO;
    }

    public String getITEMID() {
        return ITEMID;
    }

    public void setITEMID(String ITEMID) {
        this.ITEMID = ITEMID;
    }

    public String getITEMRATIO() {
        return ITEMRATIO;
    }

    public void setITEMRATIO(String ITEMRATIO) {
        this.ITEMRATIO = ITEMRATIO;
    }

    public String getITEMPCKGS() {
        return ITEMPCKGS;
    }

    public void setITEMPCKGS(String ITEMPCKGS) {
        this.ITEMPCKGS = ITEMPCKGS;
    }

    public double getITEMQTY() {
        return ITEMQTY;
    }

    public void setITEMQTY(double ITEMQTY) {
        this.ITEMQTY = ITEMQTY;
    }

    
    
}
