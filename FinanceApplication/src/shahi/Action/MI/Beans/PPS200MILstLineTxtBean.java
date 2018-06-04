/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

/**
 *
 * @author Ranjeet Singh
 */
public class PPS200MILstLineTxtBean {
private String	CONO	;                //	Company
private String	PUNO	;                //	Purchase order number
private String	PNLI	;                //	Purchase order line
private String	PNLS	;                //	Purchase order line subnumber
private String	LNCD	;                //	Language
private String	TXID	;                //	Text identity
private String	TXEI	;                //	External/internal text
private String	TYTR	;                //	Transaction type
private String	TXVR	;                //	Text block
private String	LINO	;                //	Line number
private String	TX60	;                //	Text

    public PPS200MILstLineTxtBean() {
    }

    public PPS200MILstLineTxtBean(String TX60) {
        this.TX60 = TX60;
    }




    public PPS200MILstLineTxtBean(String CONO, String PUNO, String PNLI, String PNLS, String LNCD, String TXID, String TXEI, String TYTR, String TXVR, String LINO, String TX60) {
        this.CONO = CONO;
        this.PUNO = PUNO;
        this.PNLI = PNLI;
        this.PNLS = PNLS;
        this.LNCD = LNCD;
        this.TXID = TXID;
        this.TXEI = TXEI;
        this.TYTR = TYTR;
        this.TXVR = TXVR;
        this.LINO = LINO;
        this.TX60 = TX60;
    }

  




    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getLINO() {
        return LINO;
    }

    public void setLINO(String LINO) {
        this.LINO = LINO;
    }

    public String getLNCD() {
        return LNCD;
    }

    public void setLNCD(String LNCD) {
        this.LNCD = LNCD;
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

    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public String getTX60() {
        return TX60;
    }

    public void setTX60(String TX60) {
        this.TX60 = TX60;
    }

    public String getTXEI() {
        return TXEI;
    }

    public void setTXEI(String TXEI) {
        this.TXEI = TXEI;
    }

    public String getTXID() {
        return TXID;
    }

    public void setTXID(String TXID) {
        this.TXID = TXID;
    }

    public String getTXVR() {
        return TXVR;
    }

    public void setTXVR(String TXVR) {
        this.TXVR = TXVR;
    }

    public String getTYTR() {
        return TYTR;
    }

    public void setTYTR(String TYTR) {
        this.TYTR = TYTR;
    }



}
