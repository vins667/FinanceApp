/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author Shilpa
 */
public class PMS170MI extends BaseMI {

    public PMS170MI() {
        setProgram("PMS170MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public String update(String PLPN) {
        String identity = "Update";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PLPN", PLPN);
        javaMI.mvxSetField("PSTS", "90");
        recFlag = javaMI.mvxAccess("Updat");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }
}
