/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;

import shahi.Action.Beans.PCZ300MIListCostInfoBean;

/**
 *
 * @author Ranjeet Singh
 */
public class PCZ300MI extends BaseMI {

    public PCZ300MI() {
        setProgram("PCZ300MI");
    }

    public String AddCostInfo(String CONO, String FACI, String CPRN, String CCDT, String CCNF, String RMRK) {
        int recFlag;
        String status = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("CPRN", CPRN);
        javaMI.mvxSetField("CCDT", CCDT);
        javaMI.mvxSetField("CCNF", CCNF);
        javaMI.mvxSetField("RMRK", RMRK);

        recFlag = javaMI.mvxAccess("AddCostInfo");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("Returned from applicationserver" + javaMI.mvxGetLastError());
        } else {
            status = "Yes";
        }
        return status;
    }

    public String StrCostInfo(String CPRN) {
        int recFlag;
        String identity = "ListCostInfo";
        String CCDT = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("CPRN", CPRN);
        javaMI.mvxSetField("CCNF", "1");
        int tst = 0;

        recFlag = javaMI.mvxAccess("ListCostInfo");
        if (recFlag > 0) {
            tst = 0;
        } else {
            while (javaMI.mvxMore()) {
                CCDT = javaMI.mvxGetField("CCDT");
                CCDT = CCDT.substring(6, 8) + "/" + CCDT.substring(4, 6) + "/" + CCDT.substring(0, 4);

                javaMI.mvxAccess(null);
            }

        }

        return CCDT;

    }

    public List<PCZ300MIListCostInfoBean> ListCostInfo(String CPRN) {
        List<PCZ300MIListCostInfoBean> list = new ArrayList<PCZ300MIListCostInfoBean>();
        int recFlag;
        String identity = "ListCostInfo";
        String CCDT = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("CPRN", CPRN);
        javaMI.mvxSetField("CCNF", "1");

        int tst = 0;

        recFlag = javaMI.mvxAccess("ListCostInfo");
        if (recFlag > 0) {
            tst = 0;
        } else {
            while (javaMI.mvxMore()) {
                list.add(new PCZ300MIListCostInfoBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("FACI"), javaMI.mvxGetField("CPRN"), javaMI.mvxGetField("CCDT"), javaMI.mvxGetField("CCNF"), javaMI.mvxGetField("RMRK")));
                javaMI.mvxAccess(null);
            }

        }

        return list;

    }
}
