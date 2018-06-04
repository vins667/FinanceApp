/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MIM4;

import shahi.Action.MI.Beans.CUSEXTMIGetFieldValueBean;

/**
 *
 * @author Sanjeev
 */
public class CUSEXTMI extends BaseMI{
    public CUSEXTMI() {
        setProgram("CUSEXTMI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public CUSEXTMIGetFieldValueBean GetFieldValue(String FILE, String PK01, String PK02, String PK03, String PK04, String PK05, String PK06, String PK07, String PK08) {
        CUSEXTMIGetFieldValueBean bean = null;
        int recFlag;
        String ERROR = null;
        String identity = "GetFieldValue";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("FILE", FILE);
        javaMI.mvxSetField("PK01", PK01);
        javaMI.mvxSetField("PK02", PK02);
        javaMI.mvxSetField("PK03", PK03);
        javaMI.mvxSetField("PK04", PK04);
        javaMI.mvxSetField("PK05", PK05);
        javaMI.mvxSetField("PK06", PK06);
        javaMI.mvxSetField("PK07", PK07);
        javaMI.mvxSetField("PK08", PK08);

        recFlag = javaMI.mvxAccess("GetFieldValue");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            bean = new CUSEXTMIGetFieldValueBean();
            bean.setFILE(javaMI.mvxGetField("FILE"));
            bean.setPK01(javaMI.mvxGetField("PK01"));
            bean.setPK02(javaMI.mvxGetField("PK02"));
            bean.setPK03(javaMI.mvxGetField("PK03"));
            bean.setPK04(javaMI.mvxGetField("PK04"));
            bean.setPK05(javaMI.mvxGetField("PK05"));
            bean.setPK06(javaMI.mvxGetField("PK06"));
            bean.setPK07(javaMI.mvxGetField("PK07"));
            bean.setPK08(javaMI.mvxGetField("PK08"));
            bean.setA030(javaMI.mvxGetField("A030"));
            bean.setA130(javaMI.mvxGetField("A130"));
            bean.setA230(javaMI.mvxGetField("A230"));
            bean.setA330(javaMI.mvxGetField("A330"));
            bean.setA430(javaMI.mvxGetField("A430"));
            bean.setA530(javaMI.mvxGetField("A530"));
            bean.setA630(javaMI.mvxGetField("A630"));
            bean.setA730(javaMI.mvxGetField("A730"));
            bean.setA830(javaMI.mvxGetField("A830"));
            bean.setA930(javaMI.mvxGetField("A930"));
            bean.setN096(javaMI.mvxGetField("N096"));
            bean.setN196(javaMI.mvxGetField("N196"));
            bean.setN296(javaMI.mvxGetField("N296"));
            bean.setN396(javaMI.mvxGetField("N396"));
            bean.setN496(javaMI.mvxGetField("N496"));
            bean.setN596(javaMI.mvxGetField("N596"));
            bean.setN696(javaMI.mvxGetField("N696"));
            bean.setN796(javaMI.mvxGetField("N796"));
            bean.setN896(javaMI.mvxGetField("N896"));
            bean.setN996(javaMI.mvxGetField("N996"));
            bean.setMIGR(javaMI.mvxGetField("MIGR"));
            bean.setLMTS(javaMI.mvxGetField("LMTS"));
            bean.setTXID(javaMI.mvxGetField("TXID"));
            bean.setRGDT(javaMI.mvxGetField("RGDT"));
            bean.setRGTM(javaMI.mvxGetField("RGTM"));
            bean.setLMDT(javaMI.mvxGetField("LMDT"));
            bean.setCHNO(javaMI.mvxGetField("CHNO"));
            bean.setCHID(javaMI.mvxGetField("CHID"));
            bean.setDTID(javaMI.mvxGetField("DTID"));
            bean.setA121(javaMI.mvxGetField("A121"));
        }
        return bean;
    } 
}
