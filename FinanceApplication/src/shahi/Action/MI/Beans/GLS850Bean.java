package shahi.Action.MI.Beans;

/** * * @author aswal 
 */
public class GLS850Bean {
    private String CONO;
    private String DIVI;
    private String INTN;
    private String RGDT;
    private String RGTM;
    private String RNNO;
    private String IFST;
    private String ERRS;
    private String PCDN;
    private String FLDR;

    public GLS850Bean(String CONO, String DIVI, String INTN, String RGDT, String RGTM, String RNNO, String IFST, String ERRS, String PCDN, String FLDR) {
        this.CONO = CONO;
        this.DIVI = DIVI;
        this.INTN = INTN;
        this.RGDT = RGDT;
        this.RGTM = RGTM;
        this.RNNO = RNNO;
        this.IFST = IFST;
        this.ERRS = ERRS;
        this.PCDN = PCDN;
        this.FLDR = FLDR;
    }

   

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getERRS() {
        return ERRS;
    }

    public void setERRS(String ERRS) {
        this.ERRS = ERRS;
    }

    public String getFLDR() {
        return FLDR;
    }

    public void setFLDR(String FLDR) {
        this.FLDR = FLDR;
    }

    public String getIFST() {
        return IFST;
    }

    public void setIFST(String IFST) {
        this.IFST = IFST;
    }

    public String getINTN() {
        return INTN;
    }

    public void setINTN(String INTN) {
        this.INTN = INTN;
    }

    public String getPCDN() {
        return PCDN;
    }

    public void setPCDN(String PCDN) {
        this.PCDN = PCDN;
    }

    public String getRGDT() {
        return RGDT;
    }

    public void setRGDT(String RGDT) {
        this.RGDT = RGDT;
    }

    public String getRGTM() {
        return RGTM;
    }

    public void setRGTM(String RGTM) {
        this.RGTM = RGTM;
    }

    public String getRNNO() {
        return RNNO;
    }

    public void setRNNO(String RNNO) {
        this.RNNO = RNNO;
    }


}


