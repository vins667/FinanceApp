/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.SMAMI.Beans;

/**
 *
 * @author Expression User is undefined on line 12, column 14 in Templates/Classes/Class.java.
 */
public class MaterialBean {

    private String ITNO = "";
    private String ITDS = "";
    private String FUDS = "";
    private String CHCD = "";
    private String UNMS = "";
    private String BUAR = "";
    private String ITTY = "";
    private String PRGP = "";
    private String SKUE = "";
    private String STAT = "";
    private String WAPC="";
    private String PPUN="";
     private String PUPR="";
     private String CUCD="";
     private String STGS="";
     private String TEMPPRGP="";

    public MaterialBean(String ITNO, String ITDS, String CHCD, String UNMS, String BUAR, String ITTY, String PRGP, String STAT,String WAPC,String FUDS,String STGS) {
        this.ITNO = ITNO;
        this.ITDS = ITDS;
        this.CHCD = CHCD;
        this.UNMS = UNMS;
        this.BUAR = BUAR;
        this.ITTY = ITTY;
        this.PRGP = PRGP;
        this.STAT = STAT;
        this.WAPC = WAPC;
        this.FUDS = FUDS;
        this.STGS = STGS;



    }

     public MaterialBean(String ITNO, String ITDS, String CHCD, String UNMS, String BUAR, String ITTY, String PRGP, String STAT,String WAPC,String FUDS,String STGS,String PUPR) {
        this.ITNO = ITNO;
        this.ITDS = ITDS;
        this.CHCD = CHCD;
        this.UNMS = UNMS;
        this.BUAR = BUAR;
        this.ITTY = ITTY;
        this.PRGP = PRGP;
        this.STAT = STAT;
        this.WAPC = WAPC;
        this.FUDS = FUDS;
        this.STGS = STGS;
        this.PUPR = PUPR;


    }

     public MaterialBean(String ITNO, String ITDS, String CHCD, String UNMS, String BUAR, String ITTY, String PRGP,
            String STAT,String WAPC,String PPUN,String PUPR,String CUCD ,String FUDS,String STGS,String TEMPPRGP) {
        this.ITNO = ITNO;
        this.ITDS = ITDS;
        this.CHCD = CHCD;
        this.UNMS = UNMS;
        this.BUAR = BUAR;
        this.ITTY = ITTY;
        this.PRGP = PRGP;
        this.STAT = STAT;
        this.WAPC = WAPC;
        this.PPUN = PPUN;
        this.PUPR = PUPR;
        this.CUCD = CUCD;
        this.FUDS = FUDS;
        this.STGS = STGS;
        this.TEMPPRGP = TEMPPRGP;





    }

    public String getBUAR() {
        return BUAR;
    }

    public void setBUAR(String BUAR) {
        this.BUAR = BUAR;
    }

    public String getCHCD() {
        return CHCD;
    }

    public void setCHCD(String CHCD) {
        this.CHCD = CHCD;
    }

    public String getITDS() {
        return ITDS;
    }

    public void setITDS(String ITDS) {
        this.ITDS = ITDS;
    }

    public String getITNO() {
        return ITNO;
    }

    public void setITNO(String ITNO) {
        this.ITNO = ITNO;
    }

    public String getITTY() {
        return ITTY;
    }

    public void setITTY(String ITTY) {
        this.ITTY = ITTY;
    }

    public String getPRGP() {
        return PRGP;
    }

    public void setPRGP(String PRGP) {
        this.PRGP = PRGP;
    }

    public String getUNMS() {
        return UNMS;
    }

    public void setUNMS(String UNMS) {
        this.UNMS = UNMS;
    }

    public String getSKUE() {
        return SKUE;
    }

    public void setSKUE(String SKUE) {
        if (SKUE != null) {
            this.SKUE = SKUE;
        }
    }

    public String getSTAT() {
        return STAT;
    }

    public void setSTAT(String STAT) {
        this.STAT = STAT;
    }

    public String getWAPC() {
        return WAPC;
    }

    public void setWAPC(String WAPC) {
        this.WAPC = WAPC;
    }

    public String getCUCD() {
        return CUCD;
    }

    public void setCUCD(String CUCD) {
        this.CUCD = CUCD;
    }

    public String getPPUN() {
        return PPUN;
    }

    public void setPPUN(String PPUN) {
        this.PPUN = PPUN;
    }

    public String getPUPR() {
        return PUPR;
    }

    public void setPUPR(String PUPR) {
        this.PUPR = PUPR;
    }

    public String getFUDS() {
        return FUDS;
    }

    public void setFUDS(String FUDS) {
        this.FUDS = FUDS;
    }

    public String getSTGS() {
        return STGS;
    }

    public void setSTGS(String STGS) {
        this.STGS = STGS;
    }

    public String getTEMPPRGP() {
        return TEMPPRGP;
    }

    public void setTEMPPRGP(String TEMPPRGP) {
        this.TEMPPRGP = TEMPPRGP;
    }

    
}
