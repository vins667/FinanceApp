/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.FundReq.Beans;

/**
 *
 * @author Vivek
 */
public class SupplierBean {
    private String SUPP_ID;
    private String SUPP_NAME;

    public SupplierBean(String SUPP_ID, String SUPP_NAME) {
        this.SUPP_ID = SUPP_ID;
        this.SUPP_NAME = SUPP_NAME;
    }
    
    public String getSUPP_ID() {
        return SUPP_ID;
    }

    public void setSUPP_ID(String SUPP_ID) {
        this.SUPP_ID = SUPP_ID;
    }

    public String getSUPP_NAME() {
        return SUPP_NAME;
    }

    public void setSUPP_NAME(String SUPP_NAME) {
        this.SUPP_NAME = SUPP_NAME;
    }   
}
