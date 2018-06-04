/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.LstDelivery.beans.GetLotNoBean;
import shahi.Action.ShipmentSet.Benas.MMS060MIBean;

/**
 *
 * @author Ranjeet
 */
public class MMS850MI extends BaseMI {

    public MMS850MI() {
        setProgram("MMS850MI");
    }

    public String AddReclass(String WHLO, String WHSL, String ITNO, String BANO, String BREF, String BRE2, String QLQT, String NITN) {
        String identity = "AddReclass";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("PRFL", "*EXE");
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("E0PA", "R100");
        javaMI.mvxSetField("E065", "DESADV");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("BREF", BREF);
        javaMI.mvxSetField("BRE2", BRE2);
        javaMI.mvxSetField("QLQT", QLQT);
        javaMI.mvxSetField("NITN", NITN);
        javaMI.mvxSetField("NBAN", BANO);
        javaMI.mvxSetField("ALOC", "1");
        javaMI.mvxSetField("STAS", "2");

        recFlag = javaMI.mvxAccess("AddReclass");
        String status;
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            status = "NOT-R";

        } else {
            status = "OK-R";
        }
        return status;
    }

    public String AddRclLotSts(String WHLO, String WHSL, String ITNO, String BANO, String BREF, String BRE2, String QLQT, String STAS) {
        String identity = "AddRclLotSts";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("PRFL", "*EXE");
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("E0PA", "R100");
        javaMI.mvxSetField("E065", "DESADV");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("ALOC", "1");
        javaMI.mvxSetField("STAS", STAS);

        recFlag = javaMI.mvxAccess("AddRclLotSts");
        String status;
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            status = "NOT-R";

        } else {
            status = "OK-R";
        }
        return status;
    }
}
