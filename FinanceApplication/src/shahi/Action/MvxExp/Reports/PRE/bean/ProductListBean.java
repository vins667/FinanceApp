/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author User
 */
public class ProductListBean {
    
    private String PRODUCT;
    private String SLNO;

    public ProductListBean(String PRODUCT, String SLNO) {
        this.PRODUCT = PRODUCT;
        this.SLNO = SLNO;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public String getSLNO() {
        return SLNO;
    }

    public void setSLNO(String SLNO) {
        this.SLNO = SLNO;
    }
    
    
    
}
