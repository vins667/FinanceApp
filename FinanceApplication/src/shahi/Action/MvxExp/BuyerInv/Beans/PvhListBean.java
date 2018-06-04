/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.BuyerInv.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class PvhListBean {
    private String BPONO;
    private String BSTYLENO;
    private String BHTSCODE;
    private String BCOLORCODE;
    private String BCOLORDESC;
    private String BINVDESC;
    private String BCTN;
    private String BQNTY;
    private String BCURRENCY;
    private String BINVRATE; 
    private String BFOB; 
    
    public PvhListBean(){
        
    }

    public PvhListBean(String BPONO, String BSTYLENO, String BHTSCODE, String BCOLORCODE, String BCOLORDESC, String BINVDESC, String BCTN, String BQNTY, String BCURRENCY, String BINVRATE, String BFOB) {
        this.BPONO = BPONO;
        this.BSTYLENO = BSTYLENO;
        this.BHTSCODE = BHTSCODE;
        this.BCOLORCODE = BCOLORCODE;
        this.BCOLORDESC = BCOLORDESC;
        this.BINVDESC = BINVDESC;
        this.BCTN = BCTN;
        this.BQNTY = BQNTY;
        this.BCURRENCY = BCURRENCY;
        this.BINVRATE = BINVRATE;
        this.BFOB = BFOB;
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

    public String getBHTSCODE() {
        return BHTSCODE;
    }

    public void setBHTSCODE(String BHTSCODE) {
        this.BHTSCODE = BHTSCODE;
    }

    public String getBCOLORCODE() {
        return BCOLORCODE;
    }

    public void setBCOLORCODE(String BCOLORCODE) {
        this.BCOLORCODE = BCOLORCODE;
    }

    public String getBCOLORDESC() {
        return BCOLORDESC;
    }

    public void setBCOLORDESC(String BCOLORDESC) {
        this.BCOLORDESC = BCOLORDESC;
    }

    public String getBINVDESC() {
        return BINVDESC;
    }

    public void setBINVDESC(String BINVDESC) {
        this.BINVDESC = BINVDESC;
    }

    public String getBCTN() {
        return BCTN;
    }

    public void setBCTN(String BCTN) {
        this.BCTN = BCTN;
    }

    public String getBQNTY() {
        return BQNTY;
    }

    public void setBQNTY(String BQNTY) {
        this.BQNTY = BQNTY;
    }

    public String getBCURRENCY() {
        return BCURRENCY;
    }

    public void setBCURRENCY(String BCURRENCY) {
        this.BCURRENCY = BCURRENCY;
    }

    public String getBINVRATE() {
        return BINVRATE;
    }

    public void setBINVRATE(String BINVRATE) {
        this.BINVRATE = BINVRATE;
    }

    public String getBFOB() {
        return BFOB;
    }

    public void setBFOB(String BFOB) {
        this.BFOB = BFOB;
    }

   

    
    
    
    
}
