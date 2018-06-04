/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import shahi.Action.MI.Beans.TXZ050MIGetTaxRateBean;

/**
 *
 * @author Sanjeev
 */
public class TXZ050MI extends BaseMI {

    public TXZ050MI() {
        setProgram("TXZ050MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public TXZ050MIGetTaxRateBean GetTaxRate(String CONO, String DIVI, String GEOC, String TYPE, String PRIO, String OBV1, String OBV2, String OBV3, String OBV4, String DLDT) {
        TXZ050MIGetTaxRateBean bean = null;
        int recFlag;
        String identity = "GetTaxRate";
        javaMI.mvxClearFields();

        javaMI.mvxSetField("CONO", CONO);//Company
        javaMI.mvxSetField("DIVI", DIVI);//Division
        javaMI.mvxSetField("GEOC", GEOC);//Geographical code
        javaMI.mvxSetField("TYPE", TYPE);//Transaction type=1

        javaMI.mvxSetField("PRIO", PRIO);//Priority =2

        javaMI.mvxSetField("OBV1", OBV1);//Start value 1

        javaMI.mvxSetField("OBV2", OBV2);//Start value 2

        javaMI.mvxSetField("OBV3", OBV3);//Start value 3

        javaMI.mvxSetField("OBV4", OBV4);//Start value 4

        javaMI.mvxSetField("DLDT", DLDT);//Delivery date


        recFlag = javaMI.mvxAccess("GetTaxRate");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {
            bean = new TXZ050MIGetTaxRateBean();
            bean.setTAXC(javaMI.mvxGetField("TAXC"));
            bean.setTAX1(javaMI.mvxGetField("TAR1"));
            bean.setTAX2(javaMI.mvxGetField("TAR2"));
            bean.setTAX3(javaMI.mvxGetField("TAR3"));
        }
        return bean;
    }
}
