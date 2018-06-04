/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM.beans;

/**
 *
 * @author Vivek
 */
public class PayrollPaymentsBeans {
    private String CKDIVI;
    private String CKYEA4;
    private String CKSPYN;
    private String CKSUNM;
    private String CKVSER;
    private String CKVONO;
    private String CKDTPR;
    private String CKCHKN;    
    private String CKAIT1;
    private String CKPAYI;
    private double CKPAYAMT;
    private String CHBANKNM;
    private String COMPANY_CODE;

    public PayrollPaymentsBeans(String CKDIVI, String CKYEA4, String CKSPYN, String CKSUNM, String CKVSER, String CKVONO, String CKDTPR, String CKCHKN, String CKAIT1) {
        this.CKDIVI = CKDIVI;
        this.CKYEA4 = CKYEA4;
        this.CKSPYN = CKSPYN;
        this.CKSUNM = CKSUNM;
        this.CKVSER = CKVSER;
        this.CKVONO = CKVONO;
        this.CKDTPR = CKDTPR;
        this.CKCHKN = CKCHKN;
        this.CKAIT1 = CKAIT1;
    }
    
     public PayrollPaymentsBeans(String CKDIVI, String CKYEA4, String CKSPYN, String CKSUNM, String CKVSER, String CKVONO, String CKDTPR, String CKCHKN, String CKAIT1,String CKPAYI,double CKPAYAMT,String CHBANKNM) {
        this.CKDIVI = CKDIVI;
        this.CKYEA4 = CKYEA4;
        this.CKSPYN = CKSPYN;
        this.CKSUNM = CKSUNM;
        this.CKVSER = CKVSER;
        this.CKVONO = CKVONO;
        this.CKDTPR = CKDTPR;
        this.CKCHKN = CKCHKN;
        this.CKAIT1 = CKAIT1;
        this.CKPAYI = CKPAYI;
        this.CKPAYAMT = CKPAYAMT;
        this.CHBANKNM = CHBANKNM;
    }

    public PayrollPaymentsBeans(String CKDIVI, String CKYEA4, String CKSPYN, String CKSUNM, String CKVSER, String CKVONO, String CKDTPR, String CKCHKN, String CKAIT1, String CKPAYI, double CKPAYAMT, String CHBANKNM, String COMPANY_CODE) {
        this.CKDIVI = CKDIVI;
        this.CKYEA4 = CKYEA4;
        this.CKSPYN = CKSPYN;
        this.CKSUNM = CKSUNM;
        this.CKVSER = CKVSER;
        this.CKVONO = CKVONO;
        this.CKDTPR = CKDTPR;
        this.CKCHKN = CKCHKN;
        this.CKAIT1 = CKAIT1;
        this.CKPAYI = CKPAYI;
        this.CKPAYAMT = CKPAYAMT;
        this.CHBANKNM = CHBANKNM;
        this.COMPANY_CODE = COMPANY_CODE;
    }
    
    
    public String getCKDIVI() {
        return CKDIVI;
    }

    public void setCKDIVI(String CKDIVI) {
        this.CKDIVI = CKDIVI;
    }

    public String getCKYEA4() {
        return CKYEA4;
    }

    public void setCKYEA4(String CKYEA4) {
        this.CKYEA4 = CKYEA4;
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

    public String getCKVSER() {
        return CKVSER;
    }

    public void setCKVSER(String CKVSER) {
        this.CKVSER = CKVSER;
    }

    public String getCKVONO() {
        return CKVONO;
    }

    public void setCKVONO(String CKVONO) {
        this.CKVONO = CKVONO;
    }

    public String getCKDTPR() {
        return CKDTPR;
    }

    public void setCKDTPR(String CKDTPR) {
        this.CKDTPR = CKDTPR;
    }

    public String getCKCHKN() {
        return CKCHKN;
    }

    public void setCKCHKN(String CKCHKN) {
        this.CKCHKN = CKCHKN;
    }

    public String getCKAIT1() {
        return CKAIT1;
    }

    public void setCKAIT1(String CKAIT1) {
        this.CKAIT1 = CKAIT1;
    }

    public String getCKPAYI() {
        return CKPAYI;
    }

    public void setCKPAYI(String CKPAYI) {
        this.CKPAYI = CKPAYI;
    }

    public double getCKPAYAMT() {
        return CKPAYAMT;
    }

    public void setCKPAYAMT(double CKPAYAMT) {
        this.CKPAYAMT = CKPAYAMT;
    }

    public String getCHBANKNM() {
        return CHBANKNM;
    }

    public void setCHBANKNM(String CHBANKNM) {
        this.CHBANKNM = CHBANKNM;
    }

    public String getCOMPANY_CODE() {
        return COMPANY_CODE;
    }

    public void setCOMPANY_CODE(String COMPANY_CODE) {
        this.COMPANY_CODE = COMPANY_CODE;
    }
    
}
