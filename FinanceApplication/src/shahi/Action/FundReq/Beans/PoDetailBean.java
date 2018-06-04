/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.FundReq.Beans;

/**
 *
 * @author Vivek
 */
public class PoDetailBean {
    private String PO_NO;
    private String PO_DATE;
    private String PO_AMT;
    private String PO_ADV_AMT;

    public PoDetailBean(String PO_NO, String PO_AMT, String PO_ADV_AMT) {
        this.PO_NO = PO_NO;
        this.PO_AMT = PO_AMT;
        this.PO_ADV_AMT = PO_ADV_AMT;
    }

    public PoDetailBean(String PO_AMT, String PO_ADV_AMT) {
        this.PO_AMT = PO_AMT;
        this.PO_ADV_AMT = PO_ADV_AMT;
    }
    
    public String getPO_ADV_AMT() {
        return PO_ADV_AMT;
    }

    public void setPO_ADV_AMT(String PO_ADV_AMT) {
        this.PO_ADV_AMT = PO_ADV_AMT;
    }

    public String getPO_AMT() {
        return PO_AMT;
    }

    public void setPO_AMT(String PO_AMT) {
        this.PO_AMT = PO_AMT;
    }

    public String getPO_DATE() {
        return PO_DATE;
    }

    public void setPO_DATE(String PO_DATE) {
        this.PO_DATE = PO_DATE;
    }

    public String getPO_NO() {
        return PO_NO;
    }

    public void setPO_NO(String PO_NO) {
        this.PO_NO = PO_NO;
    }
    
}
