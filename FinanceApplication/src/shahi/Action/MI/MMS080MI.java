package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;

import shahi.Action.Beans.SelMtrlTransBean;

public class MMS080MI extends BaseMI {

    public MMS080MI() {
        setProgram("MMS080MI");
    }

    public List SelMtrlTrans(String WHLO, String ITNO) {
        List list = new ArrayList();
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        recFlag = javaMI.mvxAccess("SelMtrlTrans");
        if (recFlag > 0) {
            System.out.println("MMS080MI[SelMtrlTrans]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                list.add(new SelMtrlTransBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("WHLO"), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("ORCA"), Double.parseDouble(javaMI.mvxGetField("TRQT")), javaMI.mvxGetField("PLDT"), javaMI.mvxGetField("TIHM"), javaMI.mvxGetField("NNDT"), javaMI.mvxGetField("STAT"), javaMI.mvxGetField("RIDN"), javaMI.mvxGetField("RIDL"), javaMI.mvxGetField("RIDX"), javaMI.mvxGetField("RIDI"), javaMI.mvxGetField("PRIO"), javaMI.mvxGetField("RFTX"), Double.parseDouble(javaMI.mvxGetField("ALQT")), javaMI.mvxGetField("ACTP"), javaMI.mvxGetField("RORC"), javaMI.mvxGetField("RORN"), javaMI.mvxGetField("RORL"), javaMI.mvxGetField("RORX"), javaMI.mvxGetField("RNQT"), javaMI.mvxGetField("ATNR"), javaMI.mvxGetField("CUNO"), javaMI.mvxGetField("DWDT"), javaMI.mvxGetField("DWHM"), javaMI.mvxGetField("CODT"), javaMI.mvxGetField("COHM"), javaMI.mvxGetField("FACI"), javaMI.mvxGetField("CUDT"), javaMI.mvxGetField("RGDT")));
                javaMI.mvxAccess(null);
            }
        }
        return list;
    }
}
