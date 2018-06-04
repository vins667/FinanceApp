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
public class MMS100MI extends BaseMI {

    public MMS100MI() {
        setProgram("MMS100MI");
    }

    public String ChgOrderLine(String CONO, String TRNR, String PONR, String ITNO, String WHLO, String TRQT) {
        int recFlag;
        String identity = "ChgOrderLine";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("TRNR", TRNR);
        javaMI.mvxSetField("PONR", PONR);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("TRQT", TRQT);
        recFlag = javaMI.mvxAccess("ChgOrderLine");
        String status;
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            //status="NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }
}
