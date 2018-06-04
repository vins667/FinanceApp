/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.GVTINC.Beans;

/**
 *
 * @author Sanjeev
 */
public class DbkChqBean {
   private String CHQ_NO ;
   private String CHQ_DATE;
   private String CHQ_AMT;
   private String CHQ_RECV;
   private String REMARKS;
   private String FWD_AC;
   private String BYEAR;
   
   public DbkChqBean(){
       
   }

    public DbkChqBean(String CHQ_NO, String CHQ_DATE, String CHQ_AMT, String CHQ_RECV, String REMARKS, String FWD_AC, String BYEAR) {
        this.CHQ_NO = CHQ_NO;
        this.CHQ_DATE = CHQ_DATE;
        this.CHQ_AMT = CHQ_AMT;
        this.CHQ_RECV = CHQ_RECV;
        this.REMARKS = REMARKS;
        this.FWD_AC = FWD_AC;
        this.BYEAR = BYEAR;
    }

    public String getCHQ_NO() {
        return CHQ_NO;
    }

    public void setCHQ_NO(String CHQ_NO) {
        this.CHQ_NO = CHQ_NO;
    }

    public String getCHQ_DATE() {
        return CHQ_DATE;
    }

    public void setCHQ_DATE(String CHQ_DATE) {
        this.CHQ_DATE = CHQ_DATE;
    }

    public String getCHQ_AMT() {
        return CHQ_AMT;
    }

    public void setCHQ_AMT(String CHQ_AMT) {
        this.CHQ_AMT = CHQ_AMT;
    }

    public String getCHQ_RECV() {
        return CHQ_RECV;
    }

    public void setCHQ_RECV(String CHQ_RECV) {
        this.CHQ_RECV = CHQ_RECV;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getFWD_AC() {
        return FWD_AC;
    }

    public void setFWD_AC(String FWD_AC) {
        this.FWD_AC = FWD_AC;
    }

    public String getBYEAR() {
        return BYEAR;
    }

    public void setBYEAR(String BYEAR) {
        this.BYEAR = BYEAR;
    }

    
    
    
    
}


