package shahi.Action.MIM4;

import MvxAPI.MvxSockJ;
import java.util.ResourceBundle;

/**
 *
 * @author user
 */

public class BaseMI {

    protected MvxSockJ javaMI;
    public int rc;
    private String program;
    ResourceBundle aResourcBundle = null;

    public BaseMI() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");

    }

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    public int connect() {
        javaMI = new MvxSockJ();
        rc = javaMI.mvxConnect(getValue("MIIP"), Integer.parseInt(getValue("MIPORT")), getValue("UserNameMI"), getValue("PassworMI"), program, null);
        if (rc > 0) {
            System.out.println("Failed to initiate " + javaMI.mvxGetLastError());
        }
        return rc;
    }

    public int connect(String CONO, String DIVI) {
       /* javaMI = new MvxSockJ();
        rc = javaMI.mvxConnect(getValue("MIIP"), Integer.parseInt(getValue("MIPORT")), getValue("UserNameMI"), getValue("PassworMI"), program, null);
        int i = javaMI.mvxInit(CONO + DIVI, getValue("UserNameMI"), getValue("PassworMI"), program);
        if (rc > 0) {
            System.out.println("Failed to initiate " + javaMI.mvxGetLastError());
        }*/
        
           javaMI = new MvxSockJ(getValue("MIIP"), Integer.parseInt(getValue("MIPORT")), program, 0, "NoCrypto");
          
        int rc = javaMI.mvxInit(CONO + DIVI, getValue("UserNameMI"), getValue("PassworMI"), program);
        if (rc > 0) {
            System.out.println("Failed to initiate " + javaMI.mvxGetLastError()+":::with division and company");
        }
        
        
        return rc;
    }

    public void destroyMI() {
        javaMI.mvxClose();
        javaMI = null;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}