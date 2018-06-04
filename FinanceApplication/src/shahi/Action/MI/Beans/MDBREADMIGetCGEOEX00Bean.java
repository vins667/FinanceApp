/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

/**
 *
 * @author Vivek
 */
public class MDBREADMIGetCGEOEX00Bean {

    private String GEOC; //Geograph code
    private String TAXT; //Tax type
    private String TAR1; //Tax rate 1
    private String TAXC; //Tax cust/addr

    public String getGEOC() {
        return GEOC;
    }

    public void setGEOC(String GEOC) {
        this.GEOC = GEOC;
    }

    public String getTAXT() {
        return TAXT;
    }

    public void setTAXT(String TAXT) {
        this.TAXT = TAXT;
    }

    public String getTAR1() {
        return TAR1;
    }

    public void setTAR1(String TAR1) {
        this.TAR1 = TAR1;
    }

    public String getTAXC() {
        return TAXC;
    }

    public void setTAXC(String TAXC) {
        this.TAXC = TAXC;
    }
}
