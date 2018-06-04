/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author Ranjeet
 */
import java.util.ArrayList;
import java.util.List;
import shahi.Action.ReportFolder.EPM.beans.SupplierAdditionalInfoBean;

public class XRS620MI extends BaseMI {

    public XRS620MI() {
        setProgram("XRS620MI");
    }
    String identity = null;

    public String AddFreeFields(String SUNO, String MSSUNO, String VRNO, String SUCM, String SUCO, String SWIFT,
            String SXFRE1, String s1, String s2) {
        identity = "AddFreeFields";
        int recFlag;
        javaMI.mvxClearFields();

        if (MSSUNO == null) {
            MSSUNO = "";
        }
        if (VRNO == null) {
            VRNO = "";
        }
        if (SUCM == null) {
            SUCM = "";
        }
        if (SUCO == null) {
            SUCO = "";
        }
        if (SWIFT == null) {
            SWIFT = "";
        }
        if (SXFRE1 == null) {
            SXFRE1 = "";
        }
        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }

        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("CFR1", MSSUNO);
        javaMI.mvxSetField("CFR2", s1);
        javaMI.mvxSetField("CFR3", s2);

        javaMI.mvxSetField("CFR4", SXFRE1);
        javaMI.mvxSetField("CFR5", SUCO);
        javaMI.mvxSetField("CFR6", SUCM);
        javaMI.mvxSetField("CFR7", VRNO);
        javaMI.mvxSetField("CFR8", SWIFT);




        recFlag = javaMI.mvxAccess("AddFreeFields");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("AddFreeFields" + SUNO + status);
            // sendError("AddAddress"+  SUNO  + status);
            status = "0";

        }
        return status;
    }

    public String UpdFreeFields(String SUNO, String MSSUNO, String VRNO, String SUCM, String SUCO, String SWIFT, String SXFRE1, String s1, String s2) {
        identity = "UpdFreeFields";
        int recFlag;
        javaMI.mvxClearFields();

        if (MSSUNO == null) {
            MSSUNO = "";
        }
        if (VRNO == null) {
            VRNO = "";
        }
        if (SUCM == null) {
            SUCM = "";
        }
        if (SUCO == null) {
            SUCO = "";
        }
        if (SWIFT == null) {
            SWIFT = "";
        }
        if (SXFRE1 == null) {
            SXFRE1 = "";
        }
        if (s1 == null) {
            s1 = "";
        }
        if (s2 == null) {
            s2 = "";
        }

        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("CFR1", MSSUNO);
        javaMI.mvxSetField("CFR2", s1);
        javaMI.mvxSetField("CFR3", s2);

        javaMI.mvxSetField("CFR4", SXFRE1);
        javaMI.mvxSetField("CFR5", SUCO);
        javaMI.mvxSetField("CFR6", SUCM);
        javaMI.mvxSetField("CFR7", VRNO);
        javaMI.mvxSetField("CFR8", SWIFT);

        recFlag = javaMI.mvxAccess("UpdFreeFields");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("UpdFreeFields" + SUNO + status);
            // sendError("AddAddress"+  SUNO  + status);
            status = "0";

        }
        return status;
    }

    public boolean GetFreeFields(String SUNO) {
        int recFlag;
        identity = "GetFreeFields";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("GetFreeFields");
        if (recFlag > 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean DelFreeFields(String SUNO) {
        int recFlag;
        identity = "DelFreeFields";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("DelFreeFields");
        if (recFlag > 0) {
            return false;
        } else {
            return true;
        }
    }

    public SupplierAdditionalInfoBean GetAllFreeFields(String SUNO) {
        String status = "";
        SupplierAdditionalInfoBean bean = new SupplierAdditionalInfoBean();
        int recFlag;
        identity = "GetFreeFields";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("GetFreeFields");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("GetFreeFields" + SUNO + status);
        } else {
            return new SupplierAdditionalInfoBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("SUNO"), javaMI.mvxGetField("CFR1"), javaMI.mvxGetField("CFR2"), javaMI.mvxGetField("CFR3"), javaMI.mvxGetField("CFR4"), javaMI.mvxGetField("CFR5"), javaMI.mvxGetField("CFR6"), javaMI.mvxGetField("CFR7"), javaMI.mvxGetField("CFR8"));
        }
        return bean;
    }
}
