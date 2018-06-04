/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class PreInvBEBean {
    
    private String BE_NO;
    private String BE_DESC;
    private String BE_QTY;
    private String BE_BAL_QTY;
    private String IMP_CTRL_NO;

    public PreInvBEBean(String BE_NO, String BE_DESC, String BE_QTY, String BE_BAL_QTY, String IMP_CTRL_NO) {
        this.BE_NO = BE_NO;
        this.BE_DESC = BE_DESC;
        this.BE_QTY = BE_QTY;
        this.BE_BAL_QTY = BE_BAL_QTY;
        this.IMP_CTRL_NO = IMP_CTRL_NO;
    }

    
    
    
    
    public String getBE_NO() {
        return BE_NO;
    }

    public void setBE_NO(String BE_NO) {
        this.BE_NO = BE_NO;
    }

    public String getBE_DESC() {
        return BE_DESC;
    }

    public void setBE_DESC(String BE_DESC) {
        this.BE_DESC = BE_DESC;
    }

    public String getBE_QTY() {
        return BE_QTY;
    }

    public void setBE_QTY(String BE_QTY) {
        this.BE_QTY = BE_QTY;
    }

    public String getBE_BAL_QTY() {
        return BE_BAL_QTY;
    }

    public void setBE_BAL_QTY(String BE_BAL_QTY) {
        this.BE_BAL_QTY = BE_BAL_QTY;
    }

    public String getIMP_CTRL_NO() {
        return IMP_CTRL_NO;
    }

    public void setIMP_CTRL_NO(String IMP_CTRL_NO) {
        this.IMP_CTRL_NO = IMP_CTRL_NO;
    }
    
    
    
}
