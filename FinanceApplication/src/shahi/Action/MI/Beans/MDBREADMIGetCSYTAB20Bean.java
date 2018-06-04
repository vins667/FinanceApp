/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

/**
 *
 * @author Vivek
 */
public class MDBREADMIGetCSYTAB20Bean {
    private String DIVI; //Division
    private String STCO; //Constant value
    private String STKY; //Key value
    private String LNCD; //Language
    private String TX40; //Description
    private String TX15; //Name

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getSTCO() {
        return STCO;
    }

    public void setSTCO(String STCO) {
        this.STCO = STCO;
    }

    public String getSTKY() {
        return STKY;
    }

    public void setSTKY(String STKY) {
        this.STKY = STKY;
    }

    public String getLNCD() {
        return LNCD;
    }

    public void setLNCD(String LNCD) {
        this.LNCD = LNCD;
    }

    public String getTX40() {
        return TX40;
    }

    public void setTX40(String TX40) {
        this.TX40 = TX40;
    }

    public String getTX15() {
        return TX15;
    }

    public void setTX15(String TX15) {
        this.TX15 = TX15;
    }

	@Override
	public String toString() {
		return "MDBREADMIGetCSYTAB20Bean [DIVI=" + DIVI + ", STCO=" + STCO + ", STKY=" + STKY + ", LNCD=" + LNCD
				+ ", TX40=" + TX40 + ", TX15=" + TX15 + "]";
	}
}
