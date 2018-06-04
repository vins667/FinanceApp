/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class WalmartItmBean {
    private String BITMNO;
    private String BITMID;
    private String BQNTYRATIO;
    private double BTOT;
    
    public WalmartItmBean(){
        
    }

    public WalmartItmBean(String BITMNO, String BITMID, String BQNTYRATIO, double BTOT) {
        this.BITMNO = BITMNO;
        this.BITMID = BITMID;
        this.BQNTYRATIO = BQNTYRATIO;
        this.BTOT = BTOT;
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

    public String getBQNTYRATIO() {
        return BQNTYRATIO;
    }

    public void setBQNTYRATIO(String BQNTYRATIO) {
        this.BQNTYRATIO = BQNTYRATIO;
    }

    public double getBTOT() {
        return BTOT;
    }

    public void setBTOT(double BTOT) {
        this.BTOT = BTOT;
    }

   
    

    
   
    
    
}
