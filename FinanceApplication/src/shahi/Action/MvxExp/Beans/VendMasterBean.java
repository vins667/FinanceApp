/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shahi.Action.MvxExp.Beans;

/**
 *
 * @author Sanjeev
 */
public class VendMasterBean {
     private String VEND_TYPECODE ;
    private String VEND_TYPEDESC ;

    public VendMasterBean(String VEND_TYPECODE, String VEND_TYPEDESC) {
        this.VEND_TYPECODE = VEND_TYPECODE;
        this.VEND_TYPEDESC = VEND_TYPEDESC;
    }

    public String getVEND_TYPECODE() {
        return VEND_TYPECODE;
    }

    public void setVEND_TYPECODE(String VEND_TYPECODE) {
        this.VEND_TYPECODE = VEND_TYPECODE;
    }

    public String getVEND_TYPEDESC() {
        return VEND_TYPEDESC;
    }

    public void setVEND_TYPEDESC(String VEND_TYPEDESC) {
        this.VEND_TYPEDESC = VEND_TYPEDESC;
    }



}
