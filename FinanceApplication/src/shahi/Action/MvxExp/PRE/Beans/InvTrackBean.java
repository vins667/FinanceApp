/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class InvTrackBean {
    
    private String TR_DATE;
    private String TR_TYPE;
    private String TR_DESC;
    private String REMARK;
    private String USER_ID;

    public InvTrackBean(String TR_DATE, String TR_TYPE, String TR_DESC, String REMARK, String USER_ID) {
        this.TR_DATE = TR_DATE;
        this.TR_TYPE = TR_TYPE;
        this.TR_DESC = TR_DESC;
        this.REMARK = REMARK;
        this.USER_ID = USER_ID;
    }

    
    
    
    
    public String getTR_DATE() {
        return TR_DATE;
    }

    public void setTR_DATE(String TR_DATE) {
        this.TR_DATE = TR_DATE;
    }

    public String getTR_TYPE() {
        return TR_TYPE;
    }

    public void setTR_TYPE(String TR_TYPE) {
        this.TR_TYPE = TR_TYPE;
    }

    public String getTR_DESC() {
        return TR_DESC;
    }

    public void setTR_DESC(String TR_DESC) {
        this.TR_DESC = TR_DESC;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
    
    
    
}
