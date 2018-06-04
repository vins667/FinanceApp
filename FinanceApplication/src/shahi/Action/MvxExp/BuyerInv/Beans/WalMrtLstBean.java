/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class WalMrtLstBean {
    private String BITMNO;
    private String BITMID;
    
    public WalMrtLstBean(){
        
    }

    public WalMrtLstBean(String BITMNO, String BITMID) {
        this.BITMNO = BITMNO;
        this.BITMID = BITMID;
    }

    public String getBITMNO() {
        return BITMNO;
    }

    public void setBITMNO(String BITMNO) {
        this.BITMNO = BITMNO;
    }

    public String getBITMID() {
        return BITMID;
    }

    public void setBITMID(String BITMID) {
        this.BITMID = BITMID;
    }
    
    
    
    
    
}
