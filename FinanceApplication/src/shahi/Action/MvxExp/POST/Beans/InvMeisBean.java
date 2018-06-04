/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

import java.math.BigDecimal;

/**
 *
 * @author Guddu Kumar
 */
public class InvMeisBean {
    
    private String LICNO;
    private String LICDATE;
    private String LICPORT;
    private BigDecimal LICVAL;
    private BigDecimal LICAMT;
    
    public InvMeisBean(){
        
    } 

    public InvMeisBean(String LICNO, String LICDATE, String LICPORT, BigDecimal LICVAL, BigDecimal LICAMT) {
        this.LICNO = LICNO;
        this.LICDATE = LICDATE;
        this.LICPORT = LICPORT;
        this.LICVAL = LICVAL;
        this.LICAMT = LICAMT;
    }

    public String getLICNO() {
        return LICNO;
    }

    public void setLICNO(String LICNO) {
        this.LICNO = LICNO;
    }

    public String getLICDATE() {
        return LICDATE;
    }

    public void setLICDATE(String LICDATE) {
        this.LICDATE = LICDATE;
    }

    public String getLICPORT() {
        return LICPORT;
    }

    public void setLICPORT(String LICPORT) {
        this.LICPORT = LICPORT;
    }

    public BigDecimal getLICVAL() {
        return LICVAL;
    }

    public void setLICVAL(BigDecimal LICVAL) {
        this.LICVAL = LICVAL;
    }

    public BigDecimal getLICAMT() {
        return LICAMT;
    }

    public void setLICAMT(BigDecimal LICAMT) {
        this.LICAMT = LICAMT;
    }

    
     
    
    
}
