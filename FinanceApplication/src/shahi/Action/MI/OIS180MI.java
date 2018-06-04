/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

/**
 *
 * @author Sushil
 */
public class OIS180MI extends BaseMI {

    public OIS180MI() {
        setProgram("OIS180MI");
    }

    public String UpdateCO(String DIVI, String BCCC, String IVDT, String ACDT, String FACI, String ORNO, String DLIX, String WHLO,
            String AWNO, String AWDT, String SBNO, String SBDT, String EXIV, String REM1, String REM2,
            String DAT1, String DAT2, String EDDT, String ADFC, String DSFC, String FRFC, String OTFC,
            String XPCH, String REM3, String REM4, String DUDT, String TODT, String FIDT, String NODY,
            String CUCD, String TEPY, String CLDT) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("BCCC", BCCC);
        javaMI.mvxSetField("IVDT", IVDT);
        javaMI.mvxSetField("ACDT", ACDT);
        javaMI.mvxSetField("ORNO", ORNO);
        javaMI.mvxSetField("DLIX", DLIX);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("AWNO", AWNO);
        javaMI.mvxSetField("AWDT", AWDT);
        javaMI.mvxSetField("SBNO", SBNO);
        javaMI.mvxSetField("SBDT", SBDT);
        javaMI.mvxSetField("EXIV", EXIV);
        javaMI.mvxSetField("REM1", REM1);
        javaMI.mvxSetField("REM2", REM2);
        javaMI.mvxSetField("DAT1", DAT1);
        javaMI.mvxSetField("DAT2", DAT2);
        javaMI.mvxSetField("EDDT", EDDT);
        javaMI.mvxSetField("ADFC", ADFC);
        javaMI.mvxSetField("DSFC", DSFC);
        javaMI.mvxSetField("FRFC", FRFC);
        javaMI.mvxSetField("OTFC", OTFC);
        javaMI.mvxSetField("XPCH", XPCH);
        javaMI.mvxSetField("REM3", REM3);
        javaMI.mvxSetField("REM4", REM4);
        javaMI.mvxSetField("DUDT", DUDT);
        javaMI.mvxSetField("TODT", TODT);
        javaMI.mvxSetField("77DT", FIDT);
        javaMI.mvxSetField("NODY", NODY);
        javaMI.mvxSetField("CUCD", CUCD);
        javaMI.mvxSetField("TEPY", TEPY);
        javaMI.mvxSetField("CLDT", CLDT);
        recFlag = javaMI.mvxAccess("UpdateCO");
        if (recFlag > 0) {
            status = "NO";
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
        } else {
            status = "Yes";
        }
        return status;

    }
}
