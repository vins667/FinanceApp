/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/**
 *
 * @author Sanjeev
 */
public class PostFinSBBean {
    private String SHP_BILL_NO;
    private String SHP_BILL_DATE;
    private Double DBK_DUE;
    private Double STR_DUE;
    private Double ROSL_DUE;

    public PostFinSBBean(String SHP_BILL_NO, String SHP_BILL_DATE, Double DBK_DUE, Double STR_DUE, Double ROSL_DUE) {
        this.SHP_BILL_NO = SHP_BILL_NO;
        this.SHP_BILL_DATE = SHP_BILL_DATE;
        this.DBK_DUE = DBK_DUE;
        this.STR_DUE = STR_DUE;
        this.ROSL_DUE = ROSL_DUE;
    }

    
    
    
    
    public String getSHP_BILL_NO() {
        return SHP_BILL_NO;
    }

    public void setSHP_BILL_NO(String SHP_BILL_NO) {
        this.SHP_BILL_NO = SHP_BILL_NO;
    }

    public String getSHP_BILL_DATE() {
        return SHP_BILL_DATE;
    }

    public void setSHP_BILL_DATE(String SHP_BILL_DATE) {
        this.SHP_BILL_DATE = SHP_BILL_DATE;
    }

    public Double getDBK_DUE() {
        return DBK_DUE;
    }

    public void setDBK_DUE(Double DBK_DUE) {
        this.DBK_DUE = DBK_DUE;
    }

    public Double getSTR_DUE() {
        return STR_DUE;
    }

    public void setSTR_DUE(Double STR_DUE) {
        this.STR_DUE = STR_DUE;
    }

    public Double getROSL_DUE() {
        return ROSL_DUE;
    }

    public void setROSL_DUE(Double ROSL_DUE) {
        this.ROSL_DUE = ROSL_DUE;
    }
    
    
    
}
