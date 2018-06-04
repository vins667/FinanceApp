/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class KohlsStoreBean {
    
    private String BPONO;
    private String BSTYLENO;
    private String BCATEGRY;
    private String BINVDESC;
    private String BQNTY;
    private String BCURNCY;
    private String BRATE;
    private String BHNGRRATE;
    private String BVALU;
    private String BCBMPCS;
    
    public KohlsStoreBean(){
    
      }

     public KohlsStoreBean(String BPONO, String BSTYLENO, String BCATEGRY, String BINVDESC, String BQNTY, String BCURNCY, String BRATE, String BHNGRRATE, String BVALU, String BCBMPCS) {
        this.BPONO = BPONO;
        this.BSTYLENO = BSTYLENO;
        this.BCATEGRY = BCATEGRY;
        this.BINVDESC = BINVDESC;
        this.BQNTY = BQNTY;
        this.BCURNCY = BCURNCY;
        this.BRATE = BRATE;
        this.BHNGRRATE = BHNGRRATE;
        this.BVALU = BVALU;
        this.BCBMPCS = BCBMPCS;
    }

    public String getBPONO() {
        return BPONO;
    }

    public void setBPONO(String BPONO) {
        this.BPONO = BPONO;
    }

    public String getBSTYLENO() {
        return BSTYLENO;
    }

    public void setBSTYLENO(String BSTYLENO) {
        this.BSTYLENO = BSTYLENO;
    }

    public String getBCATEGRY() {
        return BCATEGRY;
    }

    public void setBCATEGRY(String BCATEGRY) {
        this.BCATEGRY = BCATEGRY;
    }

    public String getBINVDESC() {
        return BINVDESC;
    }

    public void setBINVDESC(String BINVDESC) {
        this.BINVDESC = BINVDESC;
    }

    public String getBQNTY() {
        return BQNTY;
    }

    public void setBQNTY(String BQNTY) {
        this.BQNTY = BQNTY;
    }

    public String getBCURNCY() {
        return BCURNCY;
    }

    public void setBCURNCY(String BCURNCY) {
        this.BCURNCY = BCURNCY;
    }

    public String getBRATE() {
        return BRATE;
    }

    public void setBRATE(String BRATE) {
        this.BRATE = BRATE;
    }

    public String getBHNGRRATE() {
        return BHNGRRATE;
    }

    public void setBHNGRRATE(String BHNGRRATE) {
        this.BHNGRRATE = BHNGRRATE;
    }

    public String getBVALU() {
        return BVALU;
    }

    public void setBVALU(String BVALU) {
        this.BVALU = BVALU;
    }

    public String getBCBMPCS() {
        return BCBMPCS;
    }

    public void setBCBMPCS(String BCBMPCS) {
        this.BCBMPCS = BCBMPCS;
    }

   
    
    
}
