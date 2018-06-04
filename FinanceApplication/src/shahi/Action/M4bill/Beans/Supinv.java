/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill.Beans;

/**
 *
 * @author RANJEET
 */
public class Supinv {
//APCONO                                    NOT NULL NUMBER(3)
//APDIVI                                    NOT NULL NVARCHAR2(3)
//APYEA4                                    NOT NULL NUMBER(4)
//APSPYN                                             NVARCHAR2(10)
private String APSUNO;
private String APSINO;
private String APIVDT;
private double APCUAM;
private String IDSUNM;
private String CYEAR;
private String WAREHOUSE;
private String REVERSE_SRVTAX;
private double  REVERSE_SRVTAX_RATE;
private String INV_ST;

    public Supinv(String APSUNO, String APSINO, String APIVDT, double APCUAM, String IDSUNM) {
        this.APSUNO = APSUNO;
        this.APSINO = APSINO;
        this.APIVDT = APIVDT;
        this.APCUAM = APCUAM;
        this.IDSUNM = IDSUNM;
    }

    public Supinv(String APSUNO, String APSINO, String APIVDT, double APCUAM, String IDSUNM, String CYEAR, 
            String WAREHOUSE,String REVERSE_SRVTAX,double  REVERSE_SRVTAX_RATE) {
        this.APSUNO = APSUNO;
        this.APSINO = APSINO;
        this.APIVDT = APIVDT;
        this.APCUAM = APCUAM;
        this.IDSUNM = IDSUNM;
        this.CYEAR = CYEAR;
        this.WAREHOUSE = WAREHOUSE;
        this.REVERSE_SRVTAX=REVERSE_SRVTAX;
        this.REVERSE_SRVTAX_RATE=REVERSE_SRVTAX_RATE;
        
    }
    
       public Supinv(String APSUNO, String APSINO, String APIVDT, double APCUAM, String IDSUNM, String CYEAR, 
            String WAREHOUSE,String REVERSE_SRVTAX,double  REVERSE_SRVTAX_RATE,String INV_ST) {
        this.APSUNO = APSUNO;
        this.APSINO = APSINO;
        this.APIVDT = APIVDT;
        this.APCUAM = APCUAM;
        this.IDSUNM = IDSUNM;
        this.CYEAR = CYEAR;
        this.WAREHOUSE = WAREHOUSE;
        this.REVERSE_SRVTAX=REVERSE_SRVTAX;
        this.REVERSE_SRVTAX_RATE=REVERSE_SRVTAX_RATE;
        this.INV_ST=INV_ST;
        
    }

 public Supinv(String APSUNO, String APSINO, String APIVDT, double APCUAM, String IDSUNM, String CYEAR, String WAREHOUSE) {
        this.APSUNO = APSUNO;
        this.APSINO = APSINO;
        this.APIVDT = APIVDT;
        this.APCUAM = APCUAM;
        this.IDSUNM = IDSUNM;
        this.CYEAR = CYEAR;
        this.WAREHOUSE = WAREHOUSE;
    }


    public String getAPSUNO() {
        return APSUNO;
    }

    public void setAPSUNO(String APSUNO) {
        this.APSUNO = APSUNO;
    }

    public String getAPSINO() {
        return APSINO;
    }

    public void setAPSINO(String APSINO) {
        this.APSINO = APSINO;
    }

    public String getAPIVDT() {
        return APIVDT;
    }

    public void setAPIVDT(String APIVDT) {
        this.APIVDT = APIVDT;
    }

    public double getAPCUAM() {
        return APCUAM;
    }

    public void setAPCUAM(double APCUAM) {
        this.APCUAM = APCUAM;
    }

    public String getIDSUNM() {
        return IDSUNM;
    }

    public void setIDSUNM(String IDSUNM) {
        this.IDSUNM = IDSUNM;
    }

    public String getCYEAR() {
        return CYEAR;
    }

    public void setCYEAR(String CYEAR) {
        this.CYEAR = CYEAR;
    }

    public String getWAREHOUSE() {
        return WAREHOUSE;
    }

    public void setWAREHOUSE(String WAREHOUSE) {
        this.WAREHOUSE = WAREHOUSE;
    }

    public String getREVERSE_SRVTAX() {
        return REVERSE_SRVTAX;
    }

    public void setREVERSE_SRVTAX(String REVERSE_SRVTAX) {
        this.REVERSE_SRVTAX = REVERSE_SRVTAX;
    }

    public double getREVERSE_SRVTAX_RATE() {
        return REVERSE_SRVTAX_RATE;
    }

    public void setREVERSE_SRVTAX_RATE(double REVERSE_SRVTAX_RATE) {
        this.REVERSE_SRVTAX_RATE = REVERSE_SRVTAX_RATE;
    }

    public String getINV_ST() {
        return INV_ST;
    }

    public void setINV_ST(String INV_ST) {
        this.INV_ST = INV_ST;
    }

    

}
