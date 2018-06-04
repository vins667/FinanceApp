/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author Vivek
 */
public class PPS200USERMI extends UserBaseMI {

    public PPS200USERMI() {
        setProgram("PPS200MI");
    }

    public String ApprovePo(String PUNO, String DIVI, String AUTD) {
        String status;
        int recFlag;
        String identity = "ApprovePo";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("AUTD", AUTD);
        recFlag = javaMI.mvxAccess("ApprovePo");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status + PUNO + AUTD);
            status = "NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }
}
