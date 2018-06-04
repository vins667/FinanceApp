/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.budget.Beans;

/**
 *
 * @author Ranjeet Singh
 */
public class BudgetmasterBean {
       private String SL_NO;
       private String EXPENSE_HEAD;
       private String EXPENSE_DESC;
       private String STATUS;

    public BudgetmasterBean(String SL_NO, String EXPENSE_HEAD, String EXPENSE_DESC) {
        this.SL_NO = SL_NO;
        this.EXPENSE_HEAD = EXPENSE_HEAD;
        this.EXPENSE_DESC = EXPENSE_DESC;
    }

    public BudgetmasterBean(String SL_NO, String EXPENSE_HEAD, String EXPENSE_DESC, String STATUS) {
        this.SL_NO = SL_NO;
        this.EXPENSE_HEAD = EXPENSE_HEAD;
        this.EXPENSE_DESC = EXPENSE_DESC;
        this.STATUS = STATUS;
    }

       
       
       
       
    public String getEXPENSE_DESC() {
        return EXPENSE_DESC;
    }

    public void setEXPENSE_DESC(String EXPENSE_DESC) {
        this.EXPENSE_DESC = EXPENSE_DESC;
    }

    public String getEXPENSE_HEAD() {
        return EXPENSE_HEAD;
    }

    public void setEXPENSE_HEAD(String EXPENSE_HEAD) {
        this.EXPENSE_HEAD = EXPENSE_HEAD;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
       
       
       
}
