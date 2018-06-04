/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE.bean;

/**
 *
 * @author User
 */
public class B_TYPE_LISTBean {
    private String  BL_CODE;
    private String  SLNO;

    public B_TYPE_LISTBean(String BL_CODE, String SLNO) {
        this.BL_CODE = BL_CODE;
        this.SLNO = SLNO;
    }

    public String getBL_CODE() {
        return BL_CODE;
    }

    public void setBL_CODE(String BL_CODE) {
        this.BL_CODE = BL_CODE;
    }

    public String getSLNO() {
        return SLNO;
    }

    public void setSLNO(String SLNO) {
        this.SLNO = SLNO;
    }
    
    
    
}
