/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.FundReq.Beans;

/**
 *
 * @author Sanjeev
 */
public class FundReqdtlBean {
    private String reqpost;
    private String reqpono;
    private String reqpodt;
    private double rpoamt;
    private double advamt;
    private double reqpoamt;
    private double srvtax;

    public FundReqdtlBean(String reqpost, String reqpono, String reqpodt, double rpoamt, double advamt, double reqpoamt, double srvtax) {
        this.reqpost = reqpost;
        this.reqpono = reqpono;
        this.reqpodt = reqpodt;
        this.rpoamt = rpoamt;
        this.advamt = advamt;
        this.reqpoamt = reqpoamt;
        this.srvtax = srvtax;
    }

    public double getAdvamt() {
        return advamt;
    }

    public void setAdvamt(double advamt) {
        this.advamt = advamt;
    }

    public double getReqpoamt() {
        return reqpoamt;
    }

    public void setReqpoamt(double reqpoamt) {
        this.reqpoamt = reqpoamt;
    }

    public String getReqpodt() {
        return reqpodt;
    }

    public void setReqpodt(String reqpodt) {
        this.reqpodt = reqpodt;
    }

    public String getReqpono() {
        return reqpono;
    }

    public void setReqpono(String reqpono) {
        this.reqpono = reqpono;
    }

    public String getReqpost() {
        return reqpost;
    }

    public void setReqpost(String reqpost) {
        this.reqpost = reqpost;
    }

    public double getRpoamt() {
        return rpoamt;
    }

    public void setRpoamt(double rpoamt) {
        this.rpoamt = rpoamt;
    }

    public double getSrvtax() {
        return srvtax;
    }

    public void setSrvtax(double srvtax) {
        this.srvtax = srvtax;
    }    
    

}
