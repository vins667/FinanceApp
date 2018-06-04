/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import shahi.Action.MI.Beans.PPS280MIGetElementBean;

/**
 *
 * @author VIVEK
 */
public class PPS280MI extends BaseMI {

    public PPS280MI() {
        setProgram("PPS280MI");
    }

    public PPS280MIGetElementBean GetElement(String CEID) {
        PPS280MIGetElementBean pPS280MIGetElementBean = new PPS280MIGetElementBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetElement";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CEID", CEID);

        recFlag = javaMI.mvxAccess("GetElement");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            pPS280MIGetElementBean.setCONO(javaMI.mvxGetField("CONO"));
            pPS280MIGetElementBean.setCEID(javaMI.mvxGetField("CEID"));
            pPS280MIGetElementBean.setTX30(javaMI.mvxGetField("TX30"));
            pPS280MIGetElementBean.setTX15(javaMI.mvxGetField("TX15"));
            pPS280MIGetElementBean.setOBT1(javaMI.mvxGetField("OBT1"));
            pPS280MIGetElementBean.setOBT2(javaMI.mvxGetField("OBT2"));
            pPS280MIGetElementBean.setOBT3(javaMI.mvxGetField("OBT3"));
            pPS280MIGetElementBean.setWSOP(javaMI.mvxGetField("WSOP"));
            pPS280MIGetElementBean.setPOOV(javaMI.mvxGetField("POOV"));
            pPS280MIGetElementBean.setEXTY(javaMI.mvxGetField("EXTY"));
            pPS280MIGetElementBean.setDIMT(javaMI.mvxGetField("DIMT"));
            pPS280MIGetElementBean.setDIUN(javaMI.mvxGetField("DIUN"));
            pPS280MIGetElementBean.setEXIC(javaMI.mvxGetField("EXIC"));
            pPS280MIGetElementBean.setACRF(javaMI.mvxGetField("ACRF"));
            pPS280MIGetElementBean.setVTCD(javaMI.mvxGetField("VTCD"));
            pPS280MIGetElementBean.setATI1(javaMI.mvxGetField("ATI1"));
            pPS280MIGetElementBean.setATI2(javaMI.mvxGetField("ATI2"));
            pPS280MIGetElementBean.setATI3(javaMI.mvxGetField("ATI3"));
            pPS280MIGetElementBean.setMRF1(javaMI.mvxGetField("MRF1"));
            pPS280MIGetElementBean.setMRF2(javaMI.mvxGetField("MRF2"));
            pPS280MIGetElementBean.setMRF3(javaMI.mvxGetField("MRF3"));
            pPS280MIGetElementBean.setIVCQ(javaMI.mvxGetField("IVCQ"));
            pPS280MIGetElementBean.setTHPR(javaMI.mvxGetField("THPR"));
            pPS280MIGetElementBean.setHELV(javaMI.mvxGetField("HELV"));
            pPS280MIGetElementBean.setTXID(javaMI.mvxGetField("TXID"));
            pPS280MIGetElementBean.setRGDT(javaMI.mvxGetField("RGDT"));
            pPS280MIGetElementBean.setRGTM(javaMI.mvxGetField("RGTM"));
            pPS280MIGetElementBean.setLMDT(javaMI.mvxGetField("LMDT"));
            pPS280MIGetElementBean.setCHNO(javaMI.mvxGetField("CHNO"));
            pPS280MIGetElementBean.setCHID(javaMI.mvxGetField("CHID"));
        }
        return pPS280MIGetElementBean;
    }
}
