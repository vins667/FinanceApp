/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class PreInvLicBean {
    
    private String LIC_TYPE;
    private String LIC_NO;
    private String LIC_DATE;
    private String LIC_LOCT;
    private String LIC_COMP;
    private String EXP_CTRL_NO;
    private String EXP_ITEM_DESC;
    private String IO_NORMS;
    private double LIC_AMT_OB;
    private double LIC_AMT_ISSUE;
    private double LIC_QTY_OB;
    private double LIC_QTY_ISSUE;

    public PreInvLicBean(String LIC_TYPE, String LIC_NO, String LIC_DATE, String LIC_LOCT, String LIC_COMP, String EXP_CTRL_NO, String EXP_ITEM_DESC,String IO_NORMS, Double LIC_AMT_OB, Double LIC_AMT_ISSUE,Double LIC_QTY_OB,Double LIC_QTY_ISSUE) {
        this.LIC_TYPE = LIC_TYPE;
        this.LIC_NO = LIC_NO;
        this.LIC_DATE = LIC_DATE;
        this.LIC_LOCT = LIC_LOCT;
        this.LIC_COMP = LIC_COMP;
        this.EXP_CTRL_NO = EXP_CTRL_NO;
        this.EXP_ITEM_DESC = EXP_ITEM_DESC;
        this.IO_NORMS= IO_NORMS;
        this.LIC_AMT_OB = LIC_AMT_OB;
        this.LIC_AMT_ISSUE = LIC_AMT_ISSUE;
        this.LIC_QTY_OB = LIC_QTY_OB;
        this.LIC_QTY_ISSUE = LIC_QTY_ISSUE;
    }

      
    public String getLIC_TYPE() {
        return LIC_TYPE;
    }

    public void setLIC_TYPE(String LIC_TYPE) {
        this.LIC_TYPE = LIC_TYPE;
    }

    public String getLIC_NO() {
        return LIC_NO;
    }

    public void setLIC_NO(String LIC_NO) {
        this.LIC_NO = LIC_NO;
    }

    public String getLIC_DATE() {
        return LIC_DATE;
    }

    public void setLIC_DATE(String LIC_DATE) {
        this.LIC_DATE = LIC_DATE;
    }

    public String getLIC_LOCT() {
        return LIC_LOCT;
    }

    public void setLIC_LOCT(String LIC_LOCT) {
        this.LIC_LOCT = LIC_LOCT;
    }

    public String getLIC_COMP() {
        return LIC_COMP;
    }

    public void setLIC_COMP(String LIC_COMP) {
        this.LIC_COMP = LIC_COMP;
    }

    public String getEXP_CTRL_NO() {
        return EXP_CTRL_NO;
    }

    public void setEXP_CTRL_NO(String EXP_CTRL_NO) {
        this.EXP_CTRL_NO = EXP_CTRL_NO;
    }

    public String getEXP_ITEM_DESC() {
        return EXP_ITEM_DESC;
    }

    public void setEXP_ITEM_DESC(String EXP_ITEM_DESC) {
        this.EXP_ITEM_DESC = EXP_ITEM_DESC;
    }

    public String getIO_NORMS() {
        return IO_NORMS;
    }

    public void setIO_NORMS(String IO_NORMS) {
        this.IO_NORMS = IO_NORMS;
    }

    public double getLIC_AMT_OB() {
        return LIC_AMT_OB;
    }

    public void setLIC_AMT_OB(double LIC_AMT_OB) {
        this.LIC_AMT_OB = LIC_AMT_OB;
    }

    public double getLIC_AMT_ISSUE() {
        return LIC_AMT_ISSUE;
    }

    public void setLIC_AMT_ISSUE(double LIC_AMT_ISSUE) {
        this.LIC_AMT_ISSUE = LIC_AMT_ISSUE;
    }

    public double getLIC_QTY_OB() {
        return LIC_QTY_OB;
    }

    public void setLIC_QTY_OB(double LIC_QTY_OB) {
        this.LIC_QTY_OB = LIC_QTY_OB;
    }

    public double getLIC_QTY_ISSUE() {
        return LIC_QTY_ISSUE;
    }

    public void setLIC_QTY_ISSUE(double LIC_QTY_ISSUE) {
        this.LIC_QTY_ISSUE = LIC_QTY_ISSUE;
    }

    
    
}
