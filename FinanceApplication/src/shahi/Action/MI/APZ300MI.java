/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author kuldeep
 */
public class APZ300MI extends BaseMI {

    String identity = null;

    public APZ300MI() {
        setProgram("APZ300MI");
    }

    public String UpdateChkNo(String CHKN, String CONO, String DIVI, String BKID, String YEA4, String VSER, String VONO) {
        identity = "UpdateChkNo";
        int recFlag;
        javaMI.mvxClearFields();
        
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("BKID", BKID);
        //  javaMI.mvxSetField("BKID","AXIS");
        javaMI.mvxSetField("YEA4", YEA4);
        javaMI.mvxSetField("VSER", VSER);
        javaMI.mvxSetField("VONO", VONO);
        javaMI.mvxSetField("CHKN", CHKN);

        recFlag = javaMI.mvxAccess("UpdateChkNo");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("UpdateChkNo " + CHKN + status);
            //sendError("UpdateChkNo " + SUNO + status);
            status = CHKN + status;

        }
        return status;
    }

    public String UpdateChkNoNew(String CHKN, String CONO, String DIVI, String YEA4, String VSER, String VONO, String BKID) {
        identity = "UpdateChkNo";
        int recFlag;
        javaMI.mvxClearFields();
        
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("BKID", BKID);
        javaMI.mvxSetField("YEA4", YEA4);
        javaMI.mvxSetField("VSER", VSER);
        javaMI.mvxSetField("VONO", VONO);
        javaMI.mvxSetField("CHKN", CHKN);

        recFlag = javaMI.mvxAccess("UpdateChkNo");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("UpdateChkNo " + CHKN + status);
            //sendError("UpdateChkNo " + SUNO + status);
            status = CHKN + status;
            
        }
        return status;
    }
}
