/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/** 
 *
 * @author Sanjeev
 */
public class MultipleInvAccrBean {
    
    private String EXCS_INV_NO;
    private String ACCR_DESC;
    private String ACCR_PRICE;
    private Double ACCR_QTY;
    private Double ACCR_FOB;
    private String ACCR_DBKSLNO;
    private String ACCR_STRSLNO;

    public MultipleInvAccrBean(String EXCS_INV_NO, String ACCR_DESC, String ACCR_PRICE, Double ACCR_QTY, Double ACCR_FOB, String ACCR_DBKSLNO, String ACCR_STRSLNO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
        this.ACCR_DESC = ACCR_DESC;
        this.ACCR_PRICE = ACCR_PRICE;
        this.ACCR_QTY = ACCR_QTY;
        this.ACCR_FOB = ACCR_FOB;
        this.ACCR_DBKSLNO = ACCR_DBKSLNO;
        this.ACCR_STRSLNO = ACCR_STRSLNO;
    }

    public String getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(String EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getACCR_DESC() {
        return ACCR_DESC;
    }

    public void setACCR_DESC(String ACCR_DESC) {
        this.ACCR_DESC = ACCR_DESC;
    }

    public String getACCR_PRICE() {
        return ACCR_PRICE;
    }

    public void setACCR_PRICE(String ACCR_PRICE) {
        this.ACCR_PRICE = ACCR_PRICE;
    }

    public Double getACCR_QTY() {
        return ACCR_QTY;
    }

    public void setACCR_QTY(Double ACCR_QTY) {
        this.ACCR_QTY = ACCR_QTY;
    }

    public Double getACCR_FOB() {
        return ACCR_FOB;
    }

    public void setACCR_FOB(Double ACCR_FOB) {
        this.ACCR_FOB = ACCR_FOB;
    }

    public String getACCR_DBKSLNO() {
        return ACCR_DBKSLNO;
    }

    public void setACCR_DBKSLNO(String ACCR_DBKSLNO) {
        this.ACCR_DBKSLNO = ACCR_DBKSLNO;
    }

    public String getACCR_STRSLNO() {
        return ACCR_STRSLNO;
    }

    public void setACCR_STRSLNO(String ACCR_STRSLNO) {
        this.ACCR_STRSLNO = ACCR_STRSLNO;
    }
   
    
    
}
