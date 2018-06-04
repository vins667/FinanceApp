/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.budget.Beans;

/**
 *
 * @author Ranjeet Singh
 */
public class BudgetexpenseBean {
private String DIVISION        ;    
private String FIN_YEAR       ;
private String DEPT_CODE      ;
private String SUB_DEPT_CODE  ;
private String BUDGET_AMOUNT;
private String EXPENSE_HEAD   ;
private String QTR1_BDGT_AMNT ;
private String QTR1_ACT_AMNT  ;
private String QTR2_BDGT_AMNT ;
private String QTR2_ACT_AMNT  ;
private String QTR3_BDGT_AMNT ;
private String QTR3_ACT_AMNT  ;
private String QTR4_BDGT_AMNT ;
private String QTR4_ACT_AMNT  ;
private String SL_NO          ;

private String DEPT_CODE_DESC      ;
private String SUB_DEPT_CODE_DESC  ;

private String FIN_YEAR_DESC;

    public BudgetexpenseBean(String FIN_YEAR, String FIN_YEAR_DESC) {
        this.FIN_YEAR = FIN_YEAR;
        this.FIN_YEAR_DESC = FIN_YEAR_DESC;
    }




    public BudgetexpenseBean(String DIVISION, String FIN_YEAR, String DEPT_CODE, String SUB_DEPT_CODE, String BUDGET_AMOUNT,String DEPT_CODE_DESC,String SUB_DEPT_CODE_DESC) {
        this.DIVISION = DIVISION;
        this.FIN_YEAR = FIN_YEAR;
        this.DEPT_CODE = DEPT_CODE;
        this.SUB_DEPT_CODE = SUB_DEPT_CODE;
        this.BUDGET_AMOUNT = BUDGET_AMOUNT;
        this.DEPT_CODE_DESC=DEPT_CODE_DESC;
        this.SUB_DEPT_CODE_DESC=SUB_DEPT_CODE_DESC;
        
    }

    public BudgetexpenseBean(String DIVISION, String FIN_YEAR, String DEPT_CODE, String SUB_DEPT_CODE, String BUDGET_AMOUNT, String EXPENSE_HEAD, String QTR1_BDGT_AMNT, String QTR1_ACT_AMNT, String QTR2_BDGT_AMNT, String QTR2_ACT_AMNT, String QTR3_BDGT_AMNT, String QTR3_ACT_AMNT, String QTR4_BDGT_AMNT, String QTR4_ACT_AMNT, String SL_NO) {
        this.DIVISION = DIVISION;
        this.FIN_YEAR = FIN_YEAR;
        this.DEPT_CODE = DEPT_CODE;
        this.SUB_DEPT_CODE = SUB_DEPT_CODE;
        this.BUDGET_AMOUNT = BUDGET_AMOUNT;
        this.EXPENSE_HEAD = EXPENSE_HEAD;
        this.QTR1_BDGT_AMNT = QTR1_BDGT_AMNT;
        this.QTR1_ACT_AMNT = QTR1_ACT_AMNT;
        this.QTR2_BDGT_AMNT = QTR2_BDGT_AMNT;
        this.QTR2_ACT_AMNT = QTR2_ACT_AMNT;
        this.QTR3_BDGT_AMNT = QTR3_BDGT_AMNT;
        this.QTR3_ACT_AMNT = QTR3_ACT_AMNT;
        this.QTR4_BDGT_AMNT = QTR4_BDGT_AMNT;
        this.QTR4_ACT_AMNT = QTR4_ACT_AMNT;
        this.SL_NO = SL_NO;
    }





    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public String getDIVISION() {
        return DIVISION;
    }

    public void setDIVISION(String DIVISION) {
        this.DIVISION = DIVISION;
    }

    public String getEXPENSE_HEAD() {
        return EXPENSE_HEAD;
    }

    public void setEXPENSE_HEAD(String EXPENSE_HEAD) {
        this.EXPENSE_HEAD = EXPENSE_HEAD;
    }

    public String getFIN_YEAR() {
        return FIN_YEAR;
    }

    public void setFIN_YEAR(String FIN_YEAR) {
        this.FIN_YEAR = FIN_YEAR;
    }

    public String getQTR1_ACT_AMNT() {
        return QTR1_ACT_AMNT;
    }

    public void setQTR1_ACT_AMNT(String QTR1_ACT_AMNT) {
        this.QTR1_ACT_AMNT = QTR1_ACT_AMNT;
    }

    public String getQTR1_BDGT_AMNT() {
        return QTR1_BDGT_AMNT;
    }

    public void setQTR1_BDGT_AMNT(String QTR1_BDGT_AMNT) {
        this.QTR1_BDGT_AMNT = QTR1_BDGT_AMNT;
    }

    public String getQTR2_ACT_AMNT() {
        return QTR2_ACT_AMNT;
    }

    public void setQTR2_ACT_AMNT(String QTR2_ACT_AMNT) {
        this.QTR2_ACT_AMNT = QTR2_ACT_AMNT;
    }

    public String getQTR2_BDGT_AMNT() {
        return QTR2_BDGT_AMNT;
    }

    public void setQTR2_BDGT_AMNT(String QTR2_BDGT_AMNT) {
        this.QTR2_BDGT_AMNT = QTR2_BDGT_AMNT;
    }

    public String getQTR3_ACT_AMNT() {
        return QTR3_ACT_AMNT;
    }

    public void setQTR3_ACT_AMNT(String QTR3_ACT_AMNT) {
        this.QTR3_ACT_AMNT = QTR3_ACT_AMNT;
    }

    public String getQTR3_BDGT_AMNT() {
        return QTR3_BDGT_AMNT;
    }

    public void setQTR3_BDGT_AMNT(String QTR3_BDGT_AMNT) {
        this.QTR3_BDGT_AMNT = QTR3_BDGT_AMNT;
    }

    public String getQTR4_ACT_AMNT() {
        return QTR4_ACT_AMNT;
    }

    public void setQTR4_ACT_AMNT(String QTR4_ACT_AMNT) {
        this.QTR4_ACT_AMNT = QTR4_ACT_AMNT;
    }

    public String getQTR4_BDGT_AMNT() {
        return QTR4_BDGT_AMNT;
    }

    public void setQTR4_BDGT_AMNT(String QTR4_BDGT_AMNT) {
        this.QTR4_BDGT_AMNT = QTR4_BDGT_AMNT;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getSUB_DEPT_CODE() {
        return SUB_DEPT_CODE;
    }

    public void setSUB_DEPT_CODE(String SUB_DEPT_CODE) {
        this.SUB_DEPT_CODE = SUB_DEPT_CODE;
    }

    public String getBUDGET_AMOUNT() {
        return BUDGET_AMOUNT;
    }

    public void setBUDGET_AMOUNT(String BUDGET_AMOUNT) {
        this.BUDGET_AMOUNT = BUDGET_AMOUNT;
    }

    public String getDEPT_CODE_DESC() {
        return DEPT_CODE_DESC;
    }

    public void setDEPT_CODE_DESC(String DEPT_CODE_DESC) {
        this.DEPT_CODE_DESC = DEPT_CODE_DESC;
    }

    public String getSUB_DEPT_CODE_DESC() {
        return SUB_DEPT_CODE_DESC;
    }

    public void setSUB_DEPT_CODE_DESC(String SUB_DEPT_CODE_DESC) {
        this.SUB_DEPT_CODE_DESC = SUB_DEPT_CODE_DESC;
    }

    public String getFIN_YEAR_DESC() {
        return FIN_YEAR_DESC;
    }

    public void setFIN_YEAR_DESC(String FIN_YEAR_DESC) {
        this.FIN_YEAR_DESC = FIN_YEAR_DESC;
    }




}
