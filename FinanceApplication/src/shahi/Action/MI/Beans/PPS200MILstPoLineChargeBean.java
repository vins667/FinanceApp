/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

/**
 *
 * @author Ranjeet Singh
 */
public class PPS200MILstPoLineChargeBean {
private String	CONO	;                 //	Company
private String	PUNO	;                 //	Purchase order number
private String	PNLI	;                 //	Purchase order line
private String	PNLS	;                 //	Purchase order line subnumber
private String	CDSE	;                 //	Sequence number - costing element
private String	REPN	;                 //	Receiving number
private String	BANO	;                 //	Lot number
private String	EXTY	;                 //	Charge type
private String	CEID	;                 //	Purchase costing element
private String	EXIC	;                 //	Charge invoicing
private String	WSOP	;                 //	Costing operator
private String	CUCD	;                 //	Currency
private String	CEVA	;                 //	Costing element amount
private String	CEVJ	;                 //	Adjusted costing element amount
private String	ACIN	;                 //	Acc invoiced
private String	IVCQ	;                 //	Invoice charge quantity
private String	THPR	;                 //	Third part charge
private String	POOV	;                 //	Markup
private String	OVHE	;                 //	Costing markup

    public PPS200MILstPoLineChargeBean() {
    }




    public PPS200MILstPoLineChargeBean(String CONO, String PUNO, String PNLI, String PNLS, String CDSE, String REPN, String BANO, String EXTY, String CEID, String EXIC, String WSOP, String CUCD, String CEVA, String CEVJ, String ACIN, String IVCQ, String THPR, String POOV, String OVHE) {
        this.CONO = CONO;
        this.PUNO = PUNO;
        this.PNLI = PNLI;
        this.PNLS = PNLS;
        this.CDSE = CDSE;
        this.REPN = REPN;
        this.BANO = BANO;
        this.EXTY = EXTY;
        this.CEID = CEID;
        this.EXIC = EXIC;
        this.WSOP = WSOP;
        this.CUCD = CUCD;
        this.CEVA = CEVA;
        this.CEVJ = CEVJ;
        this.ACIN = ACIN;
        this.IVCQ = IVCQ;
        this.THPR = THPR;
        this.POOV = POOV;
        this.OVHE = OVHE;
    }







    public String getACIN() {
        return ACIN;
    }

    public void setACIN(String ACIN) {
        this.ACIN = ACIN;
    }

    public String getBANO() {
        return BANO;
    }

    public void setBANO(String BANO) {
        this.BANO = BANO;
    }

    public String getCDSE() {
        return CDSE;
    }

    public void setCDSE(String CDSE) {
        this.CDSE = CDSE;
    }

    public String getCEID() {
        return CEID;
    }

    public void setCEID(String CEID) {
        this.CEID = CEID;
    }

    public String getCEVA() {
        return CEVA;
    }

    public void setCEVA(String CEVA) {
        this.CEVA = CEVA;
    }

    public String getCEVJ() {
        return CEVJ;
    }

    public void setCEVJ(String CEVJ) {
        this.CEVJ = CEVJ;
    }

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getCUCD() {
        return CUCD;
    }

    public void setCUCD(String CUCD) {
        this.CUCD = CUCD;
    }

    public String getEXIC() {
        return EXIC;
    }

    public void setEXIC(String EXIC) {
        this.EXIC = EXIC;
    }

    public String getEXTY() {
        return EXTY;
    }

    public void setEXTY(String EXTY) {
        this.EXTY = EXTY;
    }

    public String getIVCQ() {
        return IVCQ;
    }

    public void setIVCQ(String IVCQ) {
        this.IVCQ = IVCQ;
    }

    public String getOVHE() {
        return OVHE;
    }

    public void setOVHE(String OVHE) {
        this.OVHE = OVHE;
    }

    public String getPNLI() {
        return PNLI;
    }

    public void setPNLI(String PNLI) {
        this.PNLI = PNLI;
    }

    public String getPNLS() {
        return PNLS;
    }

    public void setPNLS(String PNLS) {
        this.PNLS = PNLS;
    }

    public String getPOOV() {
        return POOV;
    }

    public void setPOOV(String POOV) {
        this.POOV = POOV;
    }

    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public String getREPN() {
        return REPN;
    }

    public void setREPN(String REPN) {
        this.REPN = REPN;
    }

    public String getTHPR() {
        return THPR;
    }

    public void setTHPR(String THPR) {
        this.THPR = THPR;
    }

    public String getWSOP() {
        return WSOP;
    }

    public void setWSOP(String WSOP) {
        this.WSOP = WSOP;
    }
  



}
