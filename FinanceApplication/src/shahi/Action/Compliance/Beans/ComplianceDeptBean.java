/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.Compliance.Beans;

/**
 *
 * @author Vivek
 */
public class ComplianceDeptBean {
    private String SUBDEPT;
    private String FEMAIL;
    private String SEMAIL;
    private String SEH_USER;
    private String TDATE;
    private String FLAG;

    public ComplianceDeptBean(String SUBDEPT, String FEMAIL, String SEMAIL) {
        this.SUBDEPT = SUBDEPT;
        this.FEMAIL = FEMAIL;
        this.SEMAIL = SEMAIL;
    }    
    public String getFEMAIL() {
        return FEMAIL;
    }

    public void setFEMAIL(String FEMAIL) {
        this.FEMAIL = FEMAIL;
    }

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }

    public String getSEH_USER() {
        return SEH_USER;
    }

    public void setSEH_USER(String SEH_USER) {
        this.SEH_USER = SEH_USER;
    }

    public String getSEMAIL() {
        return SEMAIL;
    }

    public void setSEMAIL(String SEMAIL) {
        this.SEMAIL = SEMAIL;
    }

    public String getSUBDEPT() {
        return SUBDEPT;
    }

    public void setSUBDEPT(String SUBDEPT) {
        this.SUBDEPT = SUBDEPT;
    }

    public String getTDATE() {
        return TDATE;
    }

    public void setTDATE(String TDATE) {
        this.TDATE = TDATE;
    }

}
