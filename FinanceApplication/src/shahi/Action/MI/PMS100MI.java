/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 * @author Vivek
 * @Category MMS100MI
 * @since 10/07/2011
 */
public class PMS100MI extends BaseMI {

    public PMS100MI() {
        setProgram("PMS100MI");
    }

    public String UpdateMO(String FACI, String MFNO, String BANO) {
        int recFlag;
        String identity = "UpdateMO";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("MFNO", MFNO);
        javaMI.mvxSetField("BANO", BANO);

        recFlag = javaMI.mvxAccess("UpdateMO");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            status = "NOK-M";
        } else {
            status = "OK-M";
        }
        return status;
    }

    public boolean Get(String FACI, String PRNO, String MFNO) {
        int recFlag;
        String identity = "Get";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("PRNO", PRNO);
        javaMI.mvxSetField("MFNO", MFNO);
        recFlag = javaMI.mvxAccess("Get");

        if (recFlag > 0) {
            return false;
        } else {
            if (javaMI.mvxGetField("WHST") != null && javaMI.mvxGetField("WHST").length() > 0 && Integer.parseInt(javaMI.mvxGetField("WHST").trim()) < 90) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String GetMAUN(String FACI, String PRNO, String MFNO) {
        int recFlag;
        String st = " ";
        String identity = "Get";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("PRNO", PRNO);
        javaMI.mvxSetField("MFNO", MFNO);
        recFlag = javaMI.mvxAccess("Get");

        if (recFlag > 0) {
            st = " ";
        } else {
            st = javaMI.mvxGetField("MAUN");
        }

        return st;
    }

    public String CloseMtrlLine(String FACI, String PRNO, String MFNO, String OPNO, String vmwosq) {

        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("PRNO", PRNO);
        javaMI.mvxSetField("MFNO", MFNO);
        // javaMI.mvxSetField("MTNO", OPNO);
        javaMI.mvxSetField("MSEQ", vmwosq);

        recFlag = javaMI.mvxAccess("CloseMtrlLine");
        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from application server" + javaMI.mvxGetLastError());
        } else {
            status = "Yes";
        }
        return status;
    }
}
