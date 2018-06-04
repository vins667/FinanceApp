package shahi.Action.Compliance.Beans;

/**
 *
 * @author Vivek
 */
public class ComplianceMsBean {
    private String COMP_CODE;
    private String COMP_NAME;
    private String DEPTCD;
    private String COMP_DAYS;
    private String COMP_FMAIL;
    private String COMP_SMAIL;
    private String COMP_DEPT;

    public ComplianceMsBean(String COMP_CODE, String COMP_NAME, String DEPTCD, String COMP_DAYS, String COMP_FMAIL, String COMP_SMAIL, String COMP_DEPT) {
        this.COMP_CODE = COMP_CODE;
        this.COMP_NAME = COMP_NAME;
        this.DEPTCD = DEPTCD;
        this.COMP_DAYS = COMP_DAYS;
        this.COMP_FMAIL = COMP_FMAIL;
        this.COMP_SMAIL = COMP_SMAIL;
        this.COMP_DEPT = COMP_DEPT;
    }
    public String getCOMP_CODE() {
        return COMP_CODE;
    }

    public void setCOMP_CODE(String COMP_CODE) {
        this.COMP_CODE = COMP_CODE;
    }

    public String getCOMP_DAYS() {
        return COMP_DAYS;
    }

    public void setCOMP_DAYS(String COMP_DAYS) {
        this.COMP_DAYS = COMP_DAYS;
    }

    public String getCOMP_DEPT() {
        return COMP_DEPT;
    }

    public void setCOMP_DEPT(String COMP_DEPT) {
        this.COMP_DEPT = COMP_DEPT;
    }

    public String getCOMP_FMAIL() {
        return COMP_FMAIL;
    }

    public void setCOMP_FMAIL(String COMP_FMAIL) {
        this.COMP_FMAIL = COMP_FMAIL;
    }

    public String getCOMP_NAME() {
        return COMP_NAME;
    }

    public void setCOMP_NAME(String COMP_NAME) {
        this.COMP_NAME = COMP_NAME;
    }

    public String getCOMP_SMAIL() {
        return COMP_SMAIL;
    }

    public void setCOMP_SMAIL(String COMP_SMAIL) {
        this.COMP_SMAIL = COMP_SMAIL;
    }

    public String getDEPTCD() {
        return DEPTCD;
    }

    public void setDEPTCD(String DEPTCD) {
        this.DEPTCD = DEPTCD;
    }
    
    
}
