/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.GVTINC.Beans;

/**
 *
 * @author User
 */
public class datelistbean {
    private String DATELSTCODE;
    private String DATELSTDISPLAY;

    public datelistbean(String DATELSTCODE, String DATELSTDISPLAY) {
        this.DATELSTCODE = DATELSTCODE;
        this.DATELSTDISPLAY = DATELSTDISPLAY;
    }

    public String getDATELSTCODE() {
        return DATELSTCODE;
    }

    public void setDATELSTCODE(String DATELSTCODE) {
        this.DATELSTCODE = DATELSTCODE;
    }

    public String getDATELSTDISPLAY() {
        return DATELSTDISPLAY;
    }

    public void setDATELSTDISPLAY(String DATELSTDISPLAY) {
        this.DATELSTDISPLAY = DATELSTDISPLAY;
    }

    
    
    
}
