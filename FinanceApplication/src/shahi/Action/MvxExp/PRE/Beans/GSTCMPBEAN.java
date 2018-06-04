/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author Sanjeev
 */
public class GSTCMPBEAN { 
    
        private String UNIT_CODE;
	private String UNIT_DESC;
	private String UNIT_ADDRESS;
        private String UNIT_ZONE;
        private String UNIT_GSTIN;

    public GSTCMPBEAN(String UNIT_CODE, String UNIT_DESC, String UNIT_ADDRESS, String UNIT_ZONE,String UNIT_GSTIN) {
        this.UNIT_CODE = UNIT_CODE;
        this.UNIT_DESC = UNIT_DESC;
        this.UNIT_ADDRESS = UNIT_ADDRESS;
        this.UNIT_ZONE = UNIT_ZONE;
        this.UNIT_GSTIN=UNIT_GSTIN;
    }

    public String getUNIT_CODE() {
        return UNIT_CODE;
    }

    public void setUNIT_CODE(String UNIT_CODE) {
        this.UNIT_CODE = UNIT_CODE;
    }

    public String getUNIT_DESC() {
        return UNIT_DESC;
    }

    public void setUNIT_DESC(String UNIT_DESC) {
        this.UNIT_DESC = UNIT_DESC;
    }

    public String getUNIT_ADDRESS() {
        return UNIT_ADDRESS;
    }

    public void setUNIT_ADDRESS(String UNIT_ADDRESS) {
        this.UNIT_ADDRESS = UNIT_ADDRESS;
    }

    public String getUNIT_ZONE() {
        return UNIT_ZONE;
    }

    public void setUNIT_ZONE(String UNIT_ZONE) {
        this.UNIT_ZONE = UNIT_ZONE;
    }

    public String getUNIT_GSTIN() {
        return UNIT_GSTIN;
    }

    public void setUNIT_GSTIN(String UNIT_GSTIN) {
        this.UNIT_GSTIN = UNIT_GSTIN;
    }
         
          
    
}
