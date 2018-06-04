/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Beans;

/**
 *
 * @author Sanjeev
 */
public class ChqDetailBean {
    
    private String SHP_BILL_NO;
    private String SHP_BILL_DATE;
    private double DBK_ADMS;
    private double DBK_RECV;
    private double STR_ADMS;
    private double STR_RECV;
    private double W_OFF;

    public ChqDetailBean(String SHP_BILL_NO, String SHP_BILL_DATE, double DBK_ADMS, double DBK_RECV, double STR_ADMS, double STR_RECV, double W_OFF) {
        this.SHP_BILL_NO = SHP_BILL_NO;
        this.SHP_BILL_DATE = SHP_BILL_DATE;
        this.DBK_ADMS = DBK_ADMS;
        this.DBK_RECV = DBK_RECV;
        this.STR_ADMS = STR_ADMS;
        this.STR_RECV = STR_RECV;
        this.W_OFF = W_OFF;
    }

    public String getSHP_BILL_NO() {
        return SHP_BILL_NO;
    }

    public void setSHP_BILL_NO(String SHP_BILL_NO) {
        this.SHP_BILL_NO = SHP_BILL_NO;
    }

    public String getSHP_BILL_DATE() {
        return SHP_BILL_DATE;
    }

    public void setSHP_BILL_DATE(String SHP_BILL_DATE) {
        this.SHP_BILL_DATE = SHP_BILL_DATE;
    }

    public double getDBK_ADMS() {
        return DBK_ADMS;
    }

    public void setDBK_ADMS(double DBK_ADMS) {
        this.DBK_ADMS = DBK_ADMS;
    }

    public double getDBK_RECV() {
        return DBK_RECV;
    }

    public void setDBK_RECV(double DBK_RECV) {
        this.DBK_RECV = DBK_RECV;
    }

    public double getSTR_ADMS() {
        return STR_ADMS;
    }

    public void setSTR_ADMS(double STR_ADMS) {
        this.STR_ADMS = STR_ADMS;
    }

    public double getSTR_RECV() {
        return STR_RECV;
    }

    public void setSTR_RECV(double STR_RECV) {
        this.STR_RECV = STR_RECV;
    }

    public double getW_OFF() {
        return W_OFF;
    }

    public void setW_OFF(double W_OFF) {
        this.W_OFF = W_OFF;
    }
    
    
}
