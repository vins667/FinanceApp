/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST.Beans;

/**
 *
 * @author Guddu Kumar
 */
public class ByrPortalListBean {
    private String BREASON_L;
    private String BENDDATE_L;
    private String BENDUSER_L;
    
    public ByrPortalListBean(){
        
    } 

    public ByrPortalListBean(String BREASON_L, String BENDDATE_L, String BENDUSER_L) {
        this.BREASON_L = BREASON_L;
        this.BENDDATE_L = BENDDATE_L;
        this.BENDUSER_L = BENDUSER_L;
    }

    public String getBREASON_L() {
        return BREASON_L;
    }

    public void setBREASON_L(String BREASON_L) {
        this.BREASON_L = BREASON_L;
    }

    public String getBENDDATE_L() {
        return BENDDATE_L;
    }

    public void setBENDDATE_L(String BENDDATE_L) {
        this.BENDDATE_L = BENDDATE_L;
    }

    public String getBENDUSER_L() {
        return BENDUSER_L;
    }

    public void setBENDUSER_L(String BENDUSER_L) {
        this.BENDUSER_L = BENDUSER_L;
    }
    
    
    
}
