package shahi.Action.ReportFolder.EPM.beans;
/**
 *
 * @author Vivek
 */
public class SupplierPaymentUploadBean {
    private String CKDIVI;
    private String CKYEA4;
    private String CKSPYN;
    private String CKSUNM;
    private String CKVSER;
    private String CKVONO;
    private String CKDTPR;
    private String CKCHKN;
    private String CKEXIT;
    private String CKAIT1;
    private String REMARKS;

    public String getCKCHKN() {
        return CKCHKN;
    }

    public void setCKCHKN(String CKCHKN) {
        this.CKCHKN = CKCHKN;
    }

    public String getCKDIVI() {
        return CKDIVI;
    }

    public void setCKDIVI(String CKDIVI) {
        this.CKDIVI = CKDIVI;
    }

    public String getCKDTPR() {
        return CKDTPR;
    }

    public void setCKDTPR(String CKDTPR) {
        this.CKDTPR = CKDTPR;
    }

    public String getCKSPYN() {
        return CKSPYN;
    }

    public void setCKSPYN(String CKSPYN) {
        this.CKSPYN = CKSPYN;
    }

    public String getCKSUNM() {
        return CKSUNM;
    }

    public void setCKSUNM(String CKSUNM) {
        this.CKSUNM = CKSUNM;
    }

    public String getCKVONO() {
        return CKVONO;
    }

    public void setCKVONO(String CKVONO) {
        this.CKVONO = CKVONO;
    }

    public String getCKVSER() {
        return CKVSER;
    }

    public void setCKVSER(String CKVSER) {
        this.CKVSER = CKVSER;
    }

    public String getCKYEA4() {
        return CKYEA4;
    }

    public void setCKYEA4(String CKYEA4) {
        this.CKYEA4 = CKYEA4;
    }

    public String getCKEXIT() {
        return CKEXIT;
    }

    public void setCKEXIT(String CKEXIT) {
        this.CKEXIT = CKEXIT;
    }

    public String getCKAIT1() {
        return CKAIT1;
    }

    public void setCKAIT1(String CKAIT1) {
        this.CKAIT1 = CKAIT1;
    }

    public SupplierPaymentUploadBean(String CKDIVI, String CKYEA4, String CKSPYN, String CKSUNM, String CKVSER, String CKVONO, String CKDTPR, String CKCHKN,String CKEXIT) {
        this.CKDIVI = CKDIVI;
        this.CKYEA4 = CKYEA4;
        this.CKSPYN = CKSPYN;
        this.CKSUNM = CKSUNM;
        this.CKVSER = CKVSER;
        this.CKVONO = CKVONO;
        this.CKDTPR = CKDTPR;
        this.CKCHKN = CKCHKN;
        this.CKEXIT = CKEXIT;
    }
    public SupplierPaymentUploadBean(String CKDIVI, String CKYEA4, String CKSPYN, String CKSUNM, String CKVSER, String CKVONO, String CKDTPR, String CKCHKN,String CKEXIT, String CKAIT1) {
        this.CKDIVI = CKDIVI;
        this.CKYEA4 = CKYEA4;
        this.CKSPYN = CKSPYN;
        this.CKSUNM = CKSUNM;
        this.CKVSER = CKVSER;
        this.CKVONO = CKVONO;
        this.CKDTPR = CKDTPR;
        this.CKCHKN = CKCHKN;
        this.CKEXIT = CKEXIT;
        this.CKAIT1 = CKAIT1;
    }

    public SupplierPaymentUploadBean(String CKDIVI, String CKYEA4, String CKSPYN, String CKSUNM, String CKVSER, String CKVONO, String CKDTPR, String CKCHKN,String CKEXIT, String CKAIT1,String REMARKS) {
        this.CKDIVI = CKDIVI;
        this.CKYEA4 = CKYEA4;
        this.CKSPYN = CKSPYN;
        this.CKSUNM = CKSUNM;
        this.CKVSER = CKVSER;
        this.CKVONO = CKVONO;
        this.CKDTPR = CKDTPR;
        this.CKCHKN = CKCHKN;
        this.CKEXIT = CKEXIT;
        this.CKAIT1 = CKAIT1;
        this.REMARKS = REMARKS;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

}
