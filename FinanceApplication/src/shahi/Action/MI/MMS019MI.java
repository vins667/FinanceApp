/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import shahi.Action.MI.Beans.MMS019MIGetItemBean;

/**
 *
 * @author User
 */
public class MMS019MI extends BaseMI {

    public MMS019MI() {
        setProgram("MMS019MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec 0");
        return recFlag;
    }

    public MMS019MIGetItemBean getItemGrn(String ITNO) {

        MMS019MIGetItemBean mms019miGetItemBean = new MMS019MIGetItemBean();
        int recFlag;
        String ERROR = null;
        String identity = "Get"; //transation name
        javaMI.mvxClearFields();
        javaMI.mvxSetField("ITNO", ITNO); //set field in parameter
        recFlag = javaMI.mvxAccess("Get");
        if (recFlag > 0) {

            ERROR = javaMI.mvxGetLastError();
        } else {
            mms019miGetItemBean.setCHID(javaMI.mvxGetField("CHID"));
            mms019miGetItemBean.setFTIX(javaMI.mvxGetField("FTIX"));
            mms019miGetItemBean.setFTIY(javaMI.mvxGetField("FTIY"));
            mms019miGetItemBean.setFTIZ(javaMI.mvxGetField("FTIZ"));
            mms019miGetItemBean.setITNO(javaMI.mvxGetField("ITNO"));
            mms019miGetItemBean.setLMDT(javaMI.mvxGetField("LMDT"));
            mms019miGetItemBean.setOPTX(javaMI.mvxGetField("OPTX"));
            mms019miGetItemBean.setOPTY(javaMI.mvxGetField("OPTY"));
            mms019miGetItemBean.setOPTZ(javaMI.mvxGetField("OPTZ"));
            mms019miGetItemBean.setRGDT(javaMI.mvxGetField("RGDT"));
            mms019miGetItemBean.setRGTM(javaMI.mvxGetField("RGTM"));
            mms019miGetItemBean.setSECH(javaMI.mvxGetField("SECH"));
            mms019miGetItemBean.setSQFX(javaMI.mvxGetField("SQFX"));
            mms019miGetItemBean.setSQFY(javaMI.mvxGetField("SQFY"));
            mms019miGetItemBean.setSQFZ(javaMI.mvxGetField("SQFZ"));
            mms019miGetItemBean.setSQNX(javaMI.mvxGetField("SQNX"));
            mms019miGetItemBean.setSQNY(javaMI.mvxGetField("SQNY"));
            mms019miGetItemBean.setSQNZ(javaMI.mvxGetField("SQNZ"));
            mms019miGetItemBean.setSTYN(javaMI.mvxGetField("STYN"));
            mms019miGetItemBean.setTX15(javaMI.mvxGetField("TX15"));
            mms019miGetItemBean.setTY15(javaMI.mvxGetField("TY15"));
            mms019miGetItemBean.setTZ15(javaMI.mvxGetField("TZ15"));

        }

        return mms019miGetItemBean;
    }
}
