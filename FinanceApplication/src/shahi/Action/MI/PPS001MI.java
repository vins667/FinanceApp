/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.MoClose.Beans.FacilityBean;

/**
 *
 * @author Ranjeet Gautam
 */
public class PPS001MI extends BaseMI {

    public PPS001MI() {
        setProgram("PPS001MI");
    }

    public String FinishMarkbyPO(String PUNO) {

        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);


        recFlag = javaMI.mvxAccess("FinishMark");
        String status11;
        if (recFlag > 0) {
            status11 = javaMI.mvxGetLastError();
        } else {
            status11 = "Yes";
        }
        return status11;
    }

    public String FinishMarkbyPOLine(String PUNO, String PNLI, String PNLS) {

        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);


        recFlag = javaMI.mvxAccess("FinishMark");
        String status11;
        if (recFlag > 0) {
            status11 = javaMI.mvxGetLastError();
        } else {
            status11 = "Yes";
        }
        return status11;
    }
}
