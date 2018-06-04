/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author User
 */
public class HsnCodeBean {
     private String HSN_CODE;
     private String HSN_CODE_PER;
     private String BCGST_PER;
     private String BSGST_PER;
     private String BIGST_PER;
     
     public HsnCodeBean(){
        
     }

    public HsnCodeBean(String HSN_CODE, String HSN_CODE_PER, String BCGST_PER, String BSGST_PER, String BIGST_PER) {
        this.HSN_CODE = HSN_CODE;
        this.HSN_CODE_PER = HSN_CODE_PER;
        this.BCGST_PER = BCGST_PER;
        this.BSGST_PER = BSGST_PER;
        this.BIGST_PER = BIGST_PER;
    }

    public String getHSN_CODE() {
        return HSN_CODE;
    }

    public void setHSN_CODE(String HSN_CODE) {
        this.HSN_CODE = HSN_CODE;
    }

    public String getHSN_CODE_PER() {
        return HSN_CODE_PER;
    }

    public void setHSN_CODE_PER(String HSN_CODE_PER) {
        this.HSN_CODE_PER = HSN_CODE_PER;
    }

    public String getBCGST_PER() {
        return BCGST_PER;
    }

    public void setBCGST_PER(String BCGST_PER) {
        this.BCGST_PER = BCGST_PER;
    }

    public String getBSGST_PER() {
        return BSGST_PER;
    }

    public void setBSGST_PER(String BSGST_PER) {
        this.BSGST_PER = BSGST_PER;
    }

    public String getBIGST_PER() {
        return BIGST_PER;
    }

    public void setBIGST_PER(String BIGST_PER) {
        this.BIGST_PER = BIGST_PER;
    }

   
     
     
    
}
