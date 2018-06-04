/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author Sushil
 */
public class OIS103MI extends BaseMI {

    public OIS103MI() {
        setProgram("OIS103MI");
    }

    public String AddCharges(String DIVI, String ORNO, String DLIX, String CRID, String CRAM, String ACRF, String CRDO) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("CRID", CRID);
        javaMI.mvxSetField("CRAM", CRAM);
        javaMI.mvxSetField("ACRF", ACRF);
        javaMI.mvxSetField("CRDO", CRDO);

        recFlag = javaMI.mvxAccess("AddCharges");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            // status=javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String AddTCharges(String DIVI, String ORNO, String DLIX, String CRID) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("CRID", CRID);

        recFlag = javaMI.mvxAccess("AddCharges");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String UpdCharges(String DIVI, String ORNO, String DLIX, String CRID, String CRAM, String ACRF, String CRDO) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("CRID", CRID);
        javaMI.mvxSetField("CRAM", CRAM);
        javaMI.mvxSetField("ACRF", ACRF);
        javaMI.mvxSetField("CRDO", CRDO);

        recFlag = javaMI.mvxAccess("UpdCharges");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String UpdTCharges(String DIVI, String ORNO, String DLIX, String CRID) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("CRID", CRID);

        recFlag = javaMI.mvxAccess("UpdCharges");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }

    public String DelCharges(String DIVI, String ORNO, String DLIX, String CRID) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("CRID", CRID);


        recFlag = javaMI.mvxAccess("DelCharges");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            status = javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }
}
