/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.FundReq.Beans;

/**
 *
 * @author Vivek
 */
public class RemarkBean {
    private String SL_NO;
    private String USER_ID;
    private String USER_NAME;
    private String FORWORDED_TO;
    private String FORWORDED_NAME;
    private String REMARK;
    private String FORWORD_DATE;

    public RemarkBean(String SL_NO, String USER_ID, String USER_NAME, String FORWORDED_TO, String FORWORDED_NAME, String REMARK,String FORWORD_DATE) {
        this.SL_NO = SL_NO;
        this.USER_ID = USER_ID;
        this.USER_NAME = USER_NAME;
        this.FORWORDED_TO = FORWORDED_TO;
        this.FORWORDED_NAME = FORWORDED_NAME;
        this.REMARK = REMARK;
        this.FORWORD_DATE = FORWORD_DATE;
    }   

    public String getFORWORD_DATE() {
        return FORWORD_DATE;
    }

    public void setFORWORD_DATE(String FORWORD_DATE) {
        this.FORWORD_DATE = FORWORD_DATE;
    }
    

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getFORWORDED_TO() {
        return FORWORDED_TO;
    }

    public void setFORWORDED_TO(String FORWORDED_TO) {
        this.FORWORDED_TO = FORWORDED_TO;
    }

    public String getFORWORDED_NAME() {
        return FORWORDED_NAME;
    }

    public void setFORWORDED_NAME(String FORWORDED_NAME) {
        this.FORWORDED_NAME = FORWORDED_NAME;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
    
}
