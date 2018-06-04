/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill.Beans;

/**
 *
 * @author Ranjeet
 */
public class GetTaxRateBean {
   
    private String TAXC;//Tax code customer/address
    private String TAX1;//SGST
    private String TAX2;//CGST
    private String TAX3;//IGST
    private String CALVAL;
    private String TAX_CODE;

    public String getTAXC() {
        return TAXC;
    }

    public void setTAXC(String TAXC) {
        this.TAXC = TAXC;
    }

    public String getTAX1() {
        return TAX1;
    }

    public void setTAX1(String TAX1) {
        this.TAX1 = TAX1;
    }

    public String getTAX2() {
        return TAX2;
    }

    public void setTAX2(String TAX2) {
        this.TAX2 = TAX2;
    }

    public String getTAX3() {
        return TAX3;
    }

    public void setTAX3(String TAX3) {
        this.TAX3 = TAX3;
    }

    public String getCALVAL() {
        return CALVAL;
    }

    public void setCALVAL(String CALVAL) {
        this.CALVAL = CALVAL;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }
    
    
}
