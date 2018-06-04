
package shahi.Action.MI;

import MvxAPI.MvxSockJ;
import java.util.ResourceBundle;


/**
 *
 * @author user
 */
public class BaseSMAMI {

    protected MvxSockJ javaMI;
    public int rc;
    private String program;
     ResourceBundle aResourcBundle = null;
 public BaseSMAMI()
    {
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
        rc = javaMI.mvxConnect(getValue("MISMAIP"),Integer.parseInt(getValue("MISMAPORT")),getValue("UserNameSMADB2"),getValue("PasswordSMADB2"),program, null);
        if (rc > 0) {
            System.out.println("Failed to initiate " + javaMI.mvxGetLastError());
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