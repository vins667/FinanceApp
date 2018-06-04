/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author Sushil
 */
public class PCS270MI extends BaseMI {

    public PCS270MI() {
        setProgram("PCS270MI");
    }

    public String ProductCstArch(String FFAC, String TFAC, String FPCD, String TPCD, String FITN, String TITN) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FFAC", FFAC);
        javaMI.mvxSetField("TFAC", TFAC);
        javaMI.mvxSetField("FPCT", "2");
        javaMI.mvxSetField("TPCT", "3");
        javaMI.mvxSetField("FPCD", FPCD);
        javaMI.mvxSetField("TPCD", TPCD);
        javaMI.mvxSetField("FITN", FITN);
        javaMI.mvxSetField("TITN", TITN);
//        javaMI.mvxSetField("FSTR", "   ");
//        javaMI.mvxSetField("TSTR", "   ");
        javaMI.mvxSetField("FCRO", "0");
        javaMI.mvxSetField("TCRO", "6");
//        javaMI.mvxSetField("FROR", "          ");
//        javaMI.mvxSetField("TROR", "          ");
//        javaMI.mvxSetField("FSTA", "  ");
//        javaMI.mvxSetField("TSTA", "  ");
        javaMI.mvxSetField("FIDL", "2");
        javaMI.mvxSetField("DLTP", "3");
//        javaMI.mvxSetField("SVLA", " ");


        recFlag = javaMI.mvxAccess("ProductCstArch");

        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
            // status=javaMI.mvxGetLastError();
        } else {
            status = "Yes";
        }
        return status;
    }
}
