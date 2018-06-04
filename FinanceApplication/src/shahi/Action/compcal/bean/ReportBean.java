/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.compcal.bean;

/**
 *
 * @author Ranjeet
 */
public class ReportBean {
    private String COMPANY;
    private String LOCT;
    private String DEPT;
    private String SUBDEPT;
    private String ACTIVITY;
    private String DUE_DATE;

    public ReportBean(String COMPANY, String LOCT, String DEPT, String SUBDEPT, String ACTIVITY, String DUE_DATE) {
        this.COMPANY = COMPANY;
        this.LOCT = LOCT;
        this.DEPT = DEPT;
        this.SUBDEPT = SUBDEPT;
        this.ACTIVITY = ACTIVITY;
        this.DUE_DATE = DUE_DATE;
    }

    
    
    
    
    public String getACTIVITY() {
        return ACTIVITY;
    }

    public void setACTIVITY(String ACTIVITY) {
        this.ACTIVITY = ACTIVITY;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getDEPT() {
        return DEPT;
    }

    public void setDEPT(String DEPT) {
        this.DEPT = DEPT;
    }

    public String getDUE_DATE() {
        return DUE_DATE;
    }

    public void setDUE_DATE(String DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }

    public String getLOCT() {
        return LOCT;
    }

    public void setLOCT(String LOCT) {
        this.LOCT = LOCT;
    }

    public String getSUBDEPT() {
        return SUBDEPT;
    }

    public void setSUBDEPT(String SUBDEPT) {
        this.SUBDEPT = SUBDEPT;
    }
    
    
    
    
}
