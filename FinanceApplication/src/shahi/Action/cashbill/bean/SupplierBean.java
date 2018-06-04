/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.cashbill.bean;

/**
 *
 * @author Vivek
 */
public class SupplierBean {
    private String SUPPLIER_CODE;
    private String SUPPLIER_DESC;
    private String GSTF;
    private String GSTD;
    private String CURR;
    private SupplierAddressBean supplierAddressBean;

    public SupplierBean() {
        super();
    }

    public SupplierBean(String sUPPLIER_CODE, String sUPPLIER_DESC) {
        super();
        SUPPLIER_CODE = sUPPLIER_CODE;
        SUPPLIER_DESC = sUPPLIER_DESC;
    }

    public SupplierBean(String sUPPLIER_CODE, String sUPPLIER_DESC,
            SupplierAddressBean supplierAddressBean) {
        super();
        SUPPLIER_CODE = sUPPLIER_CODE;
        SUPPLIER_DESC = sUPPLIER_DESC;
        this.supplierAddressBean = supplierAddressBean;
    }

    public SupplierBean(String SUPPLIER_CODE, String SUPPLIER_DESC, String GSTF, String GSTD, SupplierAddressBean supplierAddressBean) {
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.SUPPLIER_DESC = SUPPLIER_DESC;
        this.GSTF = GSTF;
        this.GSTD = GSTD;
        this.supplierAddressBean = supplierAddressBean;
    }

    public SupplierBean(String SUPPLIER_CODE, String SUPPLIER_DESC, String GSTF, String GSTD, String CURR, SupplierAddressBean supplierAddressBean) {
        this.SUPPLIER_CODE = SUPPLIER_CODE;
        this.SUPPLIER_DESC = SUPPLIER_DESC;
        this.GSTF = GSTF;
        this.GSTD = GSTD;
        this.CURR = CURR;
        this.supplierAddressBean = supplierAddressBean;
    }

    public String getSUPPLIER_CODE() {
        return SUPPLIER_CODE;
    }

    public void setSUPPLIER_CODE(String SUPPLIER_CODE) {
        this.SUPPLIER_CODE = SUPPLIER_CODE;
    }

    public String getSUPPLIER_DESC() {
        return SUPPLIER_DESC;
    }

    public void setSUPPLIER_DESC(String SUPPLIER_DESC) {
        this.SUPPLIER_DESC = SUPPLIER_DESC;
    }

    public String getGSTF() {
        return GSTF;
    }

    public void setGSTF(String GSTF) {
        this.GSTF = GSTF;
    }

    public String getGSTD() {
        return GSTD;
    }

    public void setGSTD(String GSTD) {
        this.GSTD = GSTD;
    }

    public SupplierAddressBean getSupplierAddressBean() {
        return supplierAddressBean;
    }

    public void setSupplierAddressBean(SupplierAddressBean supplierAddressBean) {
        this.supplierAddressBean = supplierAddressBean;
    }

    public String getCURR() {
        return CURR;
    }

    public void setCURR(String CURR) {
        this.CURR = CURR;
    }
    
}
