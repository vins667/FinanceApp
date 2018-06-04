/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;

/**
 *
 * @author Guddu kumar
 */
public class ChqDetailBean {
    
    private String BSHP_BILL_NO;
    private String BSHP_BILL_DATE;
    private String BCLAIM_PORT;
    private double BDBK_ADMISABLE;
    private double BDBK_RECEIVED;
    private double BDBK_ADJUSTED;
    private double BDBK_SUPL_RECV;
    private double BDBK_WOFF;
    private double BSTR_RECV;
    private double BSTR_DUE;
    private double BSTR_WOFF;
    private double BROSL_DUE;
         
     private String BPAY_TYP;
     private double BAMOUNT;
     private double BBANK_CR;
     private double BR_AMONT;
     private double BW_OFF;
         
         
        
         
         
         public ChqDetailBean(){
             
         }

    public ChqDetailBean(String BSHP_BILL_NO, String BSHP_BILL_DATE, String BCLAIM_PORT, double BDBK_ADMISABLE, double BDBK_RECEIVED, double BDBK_ADJUSTED, double BDBK_SUPL_RECV, double BDBK_WOFF, double BSTR_RECV, double BSTR_DUE, double BSTR_WOFF, double BROSL_DUE, String BPAY_TYP, double BAMOUNT, double BBANK_CR, double BR_AMONT, double BW_OFF) {
        this.BSHP_BILL_NO = BSHP_BILL_NO;
        this.BSHP_BILL_DATE = BSHP_BILL_DATE;
        this.BCLAIM_PORT = BCLAIM_PORT;
        this.BDBK_ADMISABLE = BDBK_ADMISABLE;
        this.BDBK_RECEIVED = BDBK_RECEIVED;
        this.BDBK_ADJUSTED = BDBK_ADJUSTED;
        this.BDBK_SUPL_RECV = BDBK_SUPL_RECV;
        this.BDBK_WOFF = BDBK_WOFF;
        this.BSTR_RECV = BSTR_RECV;
        this.BSTR_DUE = BSTR_DUE;
        this.BSTR_WOFF = BSTR_WOFF;
        this.BROSL_DUE = BROSL_DUE;
        this.BPAY_TYP = BPAY_TYP;
        this.BAMOUNT = BAMOUNT;
        this.BBANK_CR = BBANK_CR;
        this.BR_AMONT = BR_AMONT;
        this.BW_OFF = BW_OFF;
    }

    public String getBSHP_BILL_NO() {
        return BSHP_BILL_NO;
    }

    public void setBSHP_BILL_NO(String BSHP_BILL_NO) {
        this.BSHP_BILL_NO = BSHP_BILL_NO;
    }

    public String getBSHP_BILL_DATE() {
        return BSHP_BILL_DATE;
    }

    public void setBSHP_BILL_DATE(String BSHP_BILL_DATE) {
        this.BSHP_BILL_DATE = BSHP_BILL_DATE;
    }

    public String getBCLAIM_PORT() {
        return BCLAIM_PORT;
    }

    public void setBCLAIM_PORT(String BCLAIM_PORT) {
        this.BCLAIM_PORT = BCLAIM_PORT;
    }

    public double getBDBK_ADMISABLE() {
        return BDBK_ADMISABLE;
    }

    public void setBDBK_ADMISABLE(double BDBK_ADMISABLE) {
        this.BDBK_ADMISABLE = BDBK_ADMISABLE;
    }

    public double getBDBK_RECEIVED() {
        return BDBK_RECEIVED;
    }

    public void setBDBK_RECEIVED(double BDBK_RECEIVED) {
        this.BDBK_RECEIVED = BDBK_RECEIVED;
    }

    public double getBDBK_ADJUSTED() {
        return BDBK_ADJUSTED;
    }

    public void setBDBK_ADJUSTED(double BDBK_ADJUSTED) {
        this.BDBK_ADJUSTED = BDBK_ADJUSTED;
    }

    public double getBDBK_SUPL_RECV() {
        return BDBK_SUPL_RECV;
    }

    public void setBDBK_SUPL_RECV(double BDBK_SUPL_RECV) {
        this.BDBK_SUPL_RECV = BDBK_SUPL_RECV;
    }

    public double getBDBK_WOFF() {
        return BDBK_WOFF;
    }

    public void setBDBK_WOFF(double BDBK_WOFF) {
        this.BDBK_WOFF = BDBK_WOFF;
    }

    public double getBSTR_RECV() {
        return BSTR_RECV;
    }

    public void setBSTR_RECV(double BSTR_RECV) {
        this.BSTR_RECV = BSTR_RECV;
    }

    public double getBSTR_DUE() {
        return BSTR_DUE;
    }

    public void setBSTR_DUE(double BSTR_DUE) {
        this.BSTR_DUE = BSTR_DUE;
    }

    public double getBSTR_WOFF() {
        return BSTR_WOFF;
    }

    public void setBSTR_WOFF(double BSTR_WOFF) {
        this.BSTR_WOFF = BSTR_WOFF;
    }

    public double getBROSL_DUE() {
        return BROSL_DUE;
    }

    public void setBROSL_DUE(double BROSL_DUE) {
        this.BROSL_DUE = BROSL_DUE;
    }

    public String getBPAY_TYP() {
        return BPAY_TYP;
    }

    public void setBPAY_TYP(String BPAY_TYP) {
        this.BPAY_TYP = BPAY_TYP;
    }

    public double getBAMOUNT() {
        return BAMOUNT;
    }

    public void setBAMOUNT(double BAMOUNT) {
        this.BAMOUNT = BAMOUNT;
    }

    public double getBBANK_CR() {
        return BBANK_CR;
    }

    public void setBBANK_CR(double BBANK_CR) {
        this.BBANK_CR = BBANK_CR;
    }

    public double getBR_AMONT() {
        return BR_AMONT;
    }

    public void setBR_AMONT(double BR_AMONT) {
        this.BR_AMONT = BR_AMONT;
    }

    public double getBW_OFF() {
        return BW_OFF;
    }

    public void setBW_OFF(double BW_OFF) {
        this.BW_OFF = BW_OFF;
    }

    
    
}
