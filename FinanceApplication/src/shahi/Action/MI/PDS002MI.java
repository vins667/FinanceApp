/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.Beans.OpCompBean;

/**
 *
 * @author Utkarsh.
 */
public class PDS002MI extends BaseMI {

    public PDS002MI() {
        setProgram("PDS002MI");
    }

    public List getLstOperation(String FACI, String PRNO) {
        List<OpCompBean> OC = new ArrayList<OpCompBean>();
        int recFlag;
        String identity = "LstOperation";
        javaMI.mvxClearFields();


        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("PRNO", PRNO);
        javaMI.mvxSetField("STRT", "001");
        recFlag = javaMI.mvxAccess("LstOperation");
        if (recFlag > 0) {
        } else {
            while (javaMI.mvxMore()) {

                OC.add(new OpCompBean(Integer.parseInt(javaMI.mvxGetField("OPNO")) + "", javaMI.mvxGetField("PLGR"), javaMI.mvxGetField("OPDS"), (Double.parseDouble(javaMI.mvxGetField("PITI")) / 100) + ""));
                javaMI.mvxAccess(null);
            }

        }

        return OC;
    }
}
