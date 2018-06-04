/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;

import shahi.Action.Beans.MMS015MIListBean;

/**
 *
 * @author Jitendra Kothari
 */
public class MMS015MI extends BaseMI {

    public MMS015MI() {
        setProgram("MMS015MI");
    }

    public String getCovfactorList(String ITNO) {

        int recFlag;
        String curr = "1";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("List");
        if (recFlag > 0) {
        } else {
            while (javaMI.mvxMore()) {
                if (javaMI.mvxGetField("AUTP") != null && javaMI.mvxGetField("AUTP").trim().equals("1")) {
                    curr = javaMI.mvxGetField("COFA");
                }
                javaMI.mvxAccess(null);
            }
        }
        return curr;
    }

    public List<MMS015MIListBean> getCovfactorListNew(String ITNO) {

        int recFlag;
        List<MMS015MIListBean> list = new ArrayList<MMS015MIListBean>();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);

        recFlag = javaMI.mvxAccess("List");
        if (recFlag > 0) {
        } else {
            while (javaMI.mvxMore()) {
                list.add(new MMS015MIListBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("ALUN"), javaMI.mvxGetField("AUTP"), javaMI.mvxGetField("DCCD"), javaMI.mvxGetField("COFA"), javaMI.mvxGetField("DMCF"), javaMI.mvxGetField("PCOF"), javaMI.mvxGetField("AUS1"), javaMI.mvxGetField("AUS2"), javaMI.mvxGetField("AUS3"), javaMI.mvxGetField("AUS4"), javaMI.mvxGetField("AUS5"), javaMI.mvxGetField("AUS6"), javaMI.mvxGetField("AUS9"), javaMI.mvxGetField("UNMU")));
                javaMI.mvxAccess(null);
            }
        }
        return list;
    }
}
