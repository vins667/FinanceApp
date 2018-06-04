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
 * @author Vivek
 */
public class MMS235MI extends BaseMI {

    public MMS235MI() {
        setProgram("MMS235MI");
    }

    public String AddItmLot(String ITNO, String BANO, String BREF, String BRE2, String FACI) {
        String identity = "AddItmLot";
        int recFlag;
        javaMI.mvxClearFields();

        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("BREF", BREF);
        javaMI.mvxSetField("BRE2", BRE2);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("STAS", "2");
        javaMI.mvxSetField("RORC", "2");
        javaMI.mvxSetField("ORCO", "IN");


        recFlag = javaMI.mvxAccess("AddItmLot");
        String status;
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            status = "NOT-L";

        } else {
            status = "OK-L";
        }
        return status;
    }

    public String GetItmLot(String ITNO, String BANO) {
        String identity = "GetItmLot";
        int recFlag;
        javaMI.mvxClearFields();

        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("BANO", BANO);
        recFlag = javaMI.mvxAccess("GetItmLot");
        String status;
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            status = "No";

        } else {
            status = "Yes";
        }
        return status;
    }
}
