/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE.Beans;

/**
 *
 * @author User
 */
public class TaxtypeBean {
     private String TAXTYPE_CODE;
     private String TAXTYPE_DESC;
     private String TAX_PERCENT;

    public TaxtypeBean(String TAXTYPE_CODE, String TAXTYPE_DESC, String TAX_PERCENT) {
        this.TAXTYPE_CODE = TAXTYPE_CODE;
        this.TAXTYPE_DESC = TAXTYPE_DESC;
        this.TAX_PERCENT = TAX_PERCENT;
    }

     
     
     
     
    public String getTAXTYPE_CODE() {
        return TAXTYPE_CODE;
    }

    public void setTAXTYPE_CODE(String TAXTYPE_CODE) {
        this.TAXTYPE_CODE = TAXTYPE_CODE;
    }

    public String getTAXTYPE_DESC() {
        return TAXTYPE_DESC;
    }

    public void setTAXTYPE_DESC(String TAXTYPE_DESC) {
        this.TAXTYPE_DESC = TAXTYPE_DESC;
    }

    public String getTAX_PERCENT() {
        return TAX_PERCENT;
    }

    public void setTAX_PERCENT(String TAX_PERCENT) {
        this.TAX_PERCENT = TAX_PERCENT;
    }


    
    
     
     
    
}
