/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Transport.Beans;

/**
 *
 * @author Sanjeev
 */
public class FrtRateBean {
   private String CTRL_NO;
   private String PROS_DATE;
   private String FRT_RATE;
   private String HD_PER;

    public FrtRateBean(String CTRL_NO, String PROS_DATE, String FRT_RATE, String HD_PER) {
        this.CTRL_NO = CTRL_NO;
        this.PROS_DATE = PROS_DATE;
        this.FRT_RATE = FRT_RATE;
        this.HD_PER = HD_PER;
    }

    public String getCTRL_NO() {
        return CTRL_NO;
    }

    public void setCTRL_NO(String CTRL_NO) {
        this.CTRL_NO = CTRL_NO;
    }

    public String getPROS_DATE() {
        return PROS_DATE;
    }

    public void setPROS_DATE(String PROS_DATE) {
        this.PROS_DATE = PROS_DATE;
    }

    public String getFRT_RATE() {
        return FRT_RATE;
    }

    public void setFRT_RATE(String FRT_RATE) {
        this.FRT_RATE = FRT_RATE;
    }

    public String getHD_PER() {
        return HD_PER;
    }

    public void setHD_PER(String HD_PER) {
        this.HD_PER = HD_PER;
    }
   
   
    
}
