/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author Sanjeev
 */
public class BEListBean {
    private String BE_DESC;
    private String BE_NO;
    private String BE_DATE;
    private double BE_SQM;

    public BEListBean(String BE_DESC, String BE_NO, String BE_DATE, double BE_SQM) {
        this.BE_DESC = BE_DESC;
        this.BE_NO = BE_NO;
        this.BE_DATE = BE_DATE;
        this.BE_SQM = BE_SQM;
    }

    
    
    public String getBE_DESC() {
        return BE_DESC;
    }

    public void setBE_DESC(String BE_DESC) {
        this.BE_DESC = BE_DESC;
    }

    public String getBE_NO() {
        return BE_NO;
    }

    public void setBE_NO(String BE_NO) {
        this.BE_NO = BE_NO;
    }

    public String getBE_DATE() {
        return BE_DATE;
    }

    public void setBE_DATE(String BE_DATE) {
        this.BE_DATE = BE_DATE;
    }

    public double getBE_SQM() {
        return BE_SQM;
    }

    public void setBE_SQM(double BE_SQM) {
        this.BE_SQM = BE_SQM;
    }
    
    
    
}
