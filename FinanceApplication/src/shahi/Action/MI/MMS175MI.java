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
public class MMS175MI extends BaseMI {

    public MMS175MI() {
        setProgram("MMS175MI");
    }

    public String Update(String WHLO, String WHSL, String ITNO, String BANO, String TRQT, String TWSL) {
        String identity = "Update";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("TRQT", TRQT);
        javaMI.mvxSetField("TWSL", TWSL);
        javaMI.mvxSetField("WROU", "1");


        recFlag = javaMI.mvxAccess("Update");
        String status;
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
            status = "NOT-L";

        } else {
            status = "OK-L";
        }
        return status;
    }
}
