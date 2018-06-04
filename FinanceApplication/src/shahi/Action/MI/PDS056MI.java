/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.MI.SMAMI.Beans.PDS056MIListBean;

/**
 *
 * @author user
 */
public class PDS056MI extends BaseMI {

    public PDS056MI() {
        setProgram("PDS056MI");
    }

    public List getList(String FTID) {
        List<PDS056MIListBean> list1 = new ArrayList<PDS056MIListBean>();
        int recFlag;
        String identity = "getList";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("FTID", FTID);

        recFlag = javaMI.mvxAccess("List");

        if (recFlag > 0) {
            // CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {
            int i = 1;
            while (javaMI.mvxMore()) {

                list1.add(new PDS056MIListBean(i, javaMI.mvxGetField("OPTN"), javaMI.mvxGetField("OPTX"), javaMI.mvxGetField("SQNU")));
                javaMI.mvxAccess(null);
                i++;
            }
        }
        return list1;
    }
}
