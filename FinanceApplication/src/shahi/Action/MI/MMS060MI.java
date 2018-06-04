/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shahi.Action.CRMAPP.Reclassification.LoctTransferAction;
import shahi.Action.CRMAPP.Reclassification.RclassificationAction;
import shahi.Action.CRMAPP.Reclassification.RejTransferAction;
import shahi.Action.LstDelivery.beans.GetLotNoBean;
import shahi.Action.MI.Beans.MMS060LstViaItemBean;
import shahi.Action.MI.Beans.MMS060MIGetBean;
import shahi.Action.ShipmentSet.Benas.MMS060MIBean;

/**
 *
 * @author Ranjeet
 */
public class MMS060MI extends BaseMI {

    public MMS060MI() {
        setProgram("MMS060MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public GetLotNoBean Get(String WHLO, String ITNO, String WHSL, String BANO, String REPN) {
        int recFlag;
        GetLotNoBean getLotNoBean;
        String identity = "Get";
        List getList = new ArrayList();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", WHSL);
        javaMI.mvxSetField("BANO", BANO);
        //javaMI.mvxSetField("REPN", REPN);
        recFlag = javaMI.mvxAccess("Get");
        if (recFlag > 0) {
            getLotNoBean = new GetLotNoBean(javaMI.mvxGetLastError());
        } else {

            getLotNoBean = new GetLotNoBean(javaMI.mvxGetField("WHSL"), javaMI.mvxGetField("BANO"), Double.parseDouble(javaMI.mvxGetField("STQT")), Double.parseDouble(javaMI.mvxGetField("ALQT")));
            javaMI.mvxAccess(null);
        }
        return getLotNoBean;
    }

    public MMS060MIBean getLstViaItem(String ITNO, String BANO, String WHLO) {
        MMS060MIBean list1 = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("WHLO", WHLO);
        recFlag = javaMI.mvxAccess("LstViaItem");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {

            list1 = new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + "");
            javaMI.mvxAccess(null);
        }

        return list1;

    }

    public List getLstViaItemList(String ITNO, String BANO, String WHLO) {
        List list1 = new ArrayList();
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("BANO", BANO);
        javaMI.mvxSetField("WHLO", WHLO);
        recFlag = javaMI.mvxAccess("LstViaItem");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + ""));
                javaMI.mvxAccess(null);
            }
        }

        return list1;

    }

    public List<MMS060MIBean> GetShip(String WHLO, String ITNO) {
        List<MMS060MIBean> list1 = new ArrayList<MMS060MIBean>();

        int recFlag;


        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        recFlag = javaMI.mvxAccess("List");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {

                if (javaMI.mvxGetField("WHLT") != null && javaMI.mvxGetField("WHLT").length() > 0 && javaMI.mvxGetField("STAS") != null && javaMI.mvxGetField("STAS").equals("2") && Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT")) > 0) {

                    list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + ""));

                }
                javaMI.mvxAccess(null);
            }

        }
        return list1;
    }

    public List<MMS060MIBean> GetList(String WHLO, String ITNO, String LOCT, String SLOAT) {
        List<MMS060MIBean> list1 = new ArrayList<MMS060MIBean>();

        int recFlag;


        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", LOCT);

        //javaMI.mvxSetField("BANO", SLOAT);
        recFlag = javaMI.mvxAccess("List");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {
            RclassificationAction obj = new RclassificationAction();
            while (javaMI.mvxMore()) {
                boolean lotquery = true;
                if (SLOAT != null && SLOAT.length() > 0) {
                    lotquery = javaMI.mvxGetField("BANO").startsWith(SLOAT);
                }
                if (javaMI.mvxGetField("WHLT") != null && javaMI.mvxGetField("WHLT").length() > 0 && javaMI.mvxGetField("STAS") != null && javaMI.mvxGetField("STAS").equals("2") && LOCT != null && LOCT.equals(javaMI.mvxGetField("WHSL").trim()) && lotquery && Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT")) > 0) {
                    if (obj.uploadflag(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO")) == 0) {
                        list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + "", javaMI.mvxGetField("BREF"), javaMI.mvxGetField("BRE2")));
                    } else {

                        String[] uploaddetail = obj.uploaddetail(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"));

                        if (uploaddetail != null && uploaddetail.length > 0) {
                            list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + "", javaMI.mvxGetField("BREF"), javaMI.mvxGetField("BRE2"), uploaddetail[0], uploaddetail[1], uploaddetail[2], uploaddetail[3], "Yes"));
                        }
                    }
                }
                javaMI.mvxAccess(null);
            }

        }
        return list1;
    }

    public List<MMS060MIBean> GetListtrnsloct(String WHLO, String ITNO, String LOCT, String SLOAT) {
        List<MMS060MIBean> list1 = new ArrayList<MMS060MIBean>();

        int recFlag;


        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", LOCT);

        //javaMI.mvxSetField("BANO", SLOAT);
        recFlag = javaMI.mvxAccess("List");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {
            LoctTransferAction obj = new LoctTransferAction();
            while (javaMI.mvxMore()) {
                boolean lotquery = true;
                if (SLOAT != null && SLOAT.length() > 0) {
                    lotquery = javaMI.mvxGetField("BANO").startsWith(SLOAT);
                }
                if (javaMI.mvxGetField("WHLT") != null && javaMI.mvxGetField("WHLT").length() > 0 && javaMI.mvxGetField("STAS") != null && javaMI.mvxGetField("STAS").equals("2") && LOCT != null && LOCT.equals(javaMI.mvxGetField("WHSL").trim()) && lotquery && Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT")) > 0) {
                    if (obj.uploadflag(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO")) == 0) {
                        list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + "", javaMI.mvxGetField("BREF"), javaMI.mvxGetField("BRE2")));
                    } else {

                        String[] uploaddetail = obj.uploaddetail(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"));

                        if (uploaddetail != null && uploaddetail.length > 0) {
                            list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + "", javaMI.mvxGetField("BREF"), javaMI.mvxGetField("BRE2"), uploaddetail[0], uploaddetail[1], uploaddetail[2], uploaddetail[3], "Yes"));
                        }
                    }
                }
                javaMI.mvxAccess(null);
            }

        }
        return list1;
    }

    public List<MMS060MIBean> GetListtrnsRej(String WHLO, String ITNO, String LOCT, String SLOAT, String S_STAS) {
        List<MMS060MIBean> list1 = new ArrayList<MMS060MIBean>();

        int recFlag;


        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("WHSL", LOCT);

        //javaMI.mvxSetField("BANO", SLOAT);
        recFlag = javaMI.mvxAccess("List");
        if (recFlag > 0) {
            System.out.println(javaMI.mvxGetLastError());
        } else {
            RejTransferAction obj = new RejTransferAction();
            while (javaMI.mvxMore()) {
                boolean lotquery = true;
                boolean stquery = true;
                if (SLOAT != null && SLOAT.length() > 0) {
                    lotquery = javaMI.mvxGetField("BANO").startsWith(SLOAT);
                }
                if (S_STAS != null && S_STAS.length() > 0) {
                    stquery = javaMI.mvxGetField("STAS").equals(S_STAS);
                }
                //&& javaMI.mvxGetField("STAS")!=null && javaMI.mvxGetField("STAS").equals("2")
                if (javaMI.mvxGetField("WHLT") != null && javaMI.mvxGetField("WHLT").length() > 0 && LOCT != null && LOCT.equals(javaMI.mvxGetField("WHSL").trim()) && lotquery && stquery && Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT")) > 0) {
                    if (obj.uploadflag(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO")) == 0) {
                        list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + "", javaMI.mvxGetField("BREF"), javaMI.mvxGetField("BRE2"), javaMI.mvxGetField("STAS")));
                    } else {

                        String[] uploaddetail = obj.uploaddetail(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"));

                        if (uploaddetail != null && uploaddetail.length > 0) {
                            list1.add(new MMS060MIBean(javaMI.mvxGetField("WHLO").trim(), javaMI.mvxGetField("ITNO"), javaMI.mvxGetField("WHSL").trim(), javaMI.mvxGetField("BANO"), (Double.parseDouble(javaMI.mvxGetField("STQT")) - Double.parseDouble(javaMI.mvxGetField("ALQT"))) + "", javaMI.mvxGetField("BREF"), javaMI.mvxGetField("BRE2"), uploaddetail[0], uploaddetail[1], uploaddetail[2], uploaddetail[3], "Yes"));
                        }
                    }
                }
                javaMI.mvxAccess(null);
            }

        }
        return list1;
    }

    public List<MMS060LstViaItemBean> getLstViaItemForIssu(String CONO, String ITNO, List loclst) throws SQLException {
        List<MMS060LstViaItemBean> ls = new ArrayList<MMS060LstViaItemBean>();

        int recFlag;
        String ERROR = null;
        String identity = "LstViaItem";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("ITNO", ITNO.trim());
        recFlag = javaMI.mvxAccess("LstViaItem");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {

            while (javaMI.mvxMore()) {
                MMS060LstViaItemBean bn = new MMS060LstViaItemBean();

                if (javaMI.mvxGetField("STAS").trim().equals("2") && loclst != null && loclst.contains(javaMI.mvxGetField("WHSL").trim())) {
                    bn.setCONO(javaMI.mvxGetField("CONO"));
                    bn.setITNO(javaMI.mvxGetField("ITNO"));
                    bn.setITDS(javaMI.mvxGetField("ITDS"));
                    bn.setBANO(javaMI.mvxGetField("BANO"));
                    bn.setWHLO(javaMI.mvxGetField("WHLO"));
                    bn.setWHNM(javaMI.mvxGetField("WHNM"));
                    bn.setWHSL(javaMI.mvxGetField("WHSL"));
                    bn.setCAMU(javaMI.mvxGetField("CAMU"));
                    bn.setREPN(javaMI.mvxGetField("REPN"));
                    bn.setABCD(javaMI.mvxGetField("ABCD"));
                    bn.setITTY(javaMI.mvxGetField("ITTY"));
                    bn.setRESP(javaMI.mvxGetField("RESP"));
                    bn.setUNMS(javaMI.mvxGetField("UNMS"));
                    bn.setITGR(javaMI.mvxGetField("ITGR"));
                    bn.setPLCD(javaMI.mvxGetField("PLCD"));
                    bn.setSLTP(javaMI.mvxGetField("SLTP"));
                    bn.setWHLT(javaMI.mvxGetField("WHLT"));
                    bn.setIDDT(javaMI.mvxGetField("IDDT"));
                    bn.setODDT(javaMI.mvxGetField("ODDT"));
                    bn.setINDT(javaMI.mvxGetField("INDT"));
                    bn.setINON(javaMI.mvxGetField("INON"));
                    bn.setSTAS(javaMI.mvxGetField("STAS"));
                    bn.setRCLS(javaMI.mvxGetField("RCLS"));
                    bn.setPRDT(javaMI.mvxGetField("PRDT"));
                    bn.setSTQT(javaMI.mvxGetField("STQT"));
                    bn.setPHSQ(javaMI.mvxGetField("PHSQ"));
                    bn.setALOC(javaMI.mvxGetField("ALOC"));
                    bn.setALQT(javaMI.mvxGetField("ALQT"));
                    bn.setPHAQ(javaMI.mvxGetField("PHAQ"));
                    bn.setAUDE(javaMI.mvxGetField("AUDE"));
                    bn.setAUDD(javaMI.mvxGetField("AUDD"));
                    bn.setSTAQ(javaMI.mvxGetField("STAQ"));
                    bn.setPHA2(javaMI.mvxGetField("PHA2"));
                    bn.setPOCY(javaMI.mvxGetField("POCY"));
                    bn.setCAWE(javaMI.mvxGetField("CAWE"));
                    bn.setLPCY(javaMI.mvxGetField("LPCY"));
                    bn.setPPUN(javaMI.mvxGetField("PPUN"));
                    bn.setBREF(javaMI.mvxGetField("BREF"));
                    bn.setPACT(javaMI.mvxGetField("PACT"));
                    bn.setBRE2(javaMI.mvxGetField("BRE2"));
                    bn.setBREM(javaMI.mvxGetField("BREM"));
                    bn.setREOP(javaMI.mvxGetField("REOP"));
                    bn.setATNR(javaMI.mvxGetField("ATNR"));
                    bn.setATNB(javaMI.mvxGetField("ATNB"));
                    bn.setSTAT(javaMI.mvxGetField("STAT"));
                    bn.setFLG("Yes");
                    bn.setRORN("");
                    bn.setRORL("");

                    ls.add(bn);
                }

                javaMI.mvxAccess(null);
            }

        }
        return ls;
    }

    public List<MMS060LstViaItemBean> getLstViaItemForIssu(String CONO, String ITNO, List loclst, String STOCK_LOC, String STOCK_LOT) throws SQLException {
        List<MMS060LstViaItemBean> ls = new ArrayList<MMS060LstViaItemBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstViaItem";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("ITNO", ITNO.trim());
        recFlag = javaMI.mvxAccess("LstViaItem");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {

            while (javaMI.mvxMore()) {
                MMS060LstViaItemBean bn = new MMS060LstViaItemBean();

                if (javaMI.mvxGetField("STAS").trim().equals("2") && loclst != null && loclst.contains(javaMI.mvxGetField("WHSL").trim())) {
                    if ((STOCK_LOT == null || STOCK_LOT.length() == 0) && (STOCK_LOC == null || STOCK_LOC.length() == 0)) {
                        bn.setCONO(javaMI.mvxGetField("CONO"));
                        bn.setITNO(javaMI.mvxGetField("ITNO"));
                        bn.setITDS(javaMI.mvxGetField("ITDS"));
                        bn.setBANO(javaMI.mvxGetField("BANO"));
                        bn.setWHLO(javaMI.mvxGetField("WHLO"));
                        bn.setWHNM(javaMI.mvxGetField("WHNM"));
                        bn.setWHSL(javaMI.mvxGetField("WHSL"));
                        bn.setCAMU(javaMI.mvxGetField("CAMU"));
                        bn.setREPN(javaMI.mvxGetField("REPN"));
                        bn.setABCD(javaMI.mvxGetField("ABCD"));
                        bn.setITTY(javaMI.mvxGetField("ITTY"));
                        bn.setRESP(javaMI.mvxGetField("RESP"));
                        bn.setUNMS(javaMI.mvxGetField("UNMS"));
                        bn.setITGR(javaMI.mvxGetField("ITGR"));
                        bn.setPLCD(javaMI.mvxGetField("PLCD"));
                        bn.setSLTP(javaMI.mvxGetField("SLTP"));
                        bn.setWHLT(javaMI.mvxGetField("WHLT"));
                        bn.setIDDT(javaMI.mvxGetField("IDDT"));
                        bn.setODDT(javaMI.mvxGetField("ODDT"));
                        bn.setINDT(javaMI.mvxGetField("INDT"));
                        bn.setINON(javaMI.mvxGetField("INON"));
                        bn.setSTAS(javaMI.mvxGetField("STAS"));
                        bn.setRCLS(javaMI.mvxGetField("RCLS"));
                        bn.setPRDT(javaMI.mvxGetField("PRDT"));
                        bn.setSTQT(javaMI.mvxGetField("STQT"));
                        bn.setPHSQ(javaMI.mvxGetField("PHSQ"));
                        bn.setALOC(javaMI.mvxGetField("ALOC"));
                        bn.setALQT(javaMI.mvxGetField("ALQT"));
                        bn.setPHAQ(javaMI.mvxGetField("PHAQ"));
                        bn.setAUDE(javaMI.mvxGetField("AUDE"));
                        bn.setAUDD(javaMI.mvxGetField("AUDD"));
                        bn.setSTAQ(javaMI.mvxGetField("STAQ"));
                        bn.setPHA2(javaMI.mvxGetField("PHA2"));
                        bn.setPOCY(javaMI.mvxGetField("POCY"));
                        bn.setCAWE(javaMI.mvxGetField("CAWE"));
                        bn.setLPCY(javaMI.mvxGetField("LPCY"));
                        bn.setPPUN(javaMI.mvxGetField("PPUN"));
                        bn.setBREF(javaMI.mvxGetField("BREF"));
                        bn.setPACT(javaMI.mvxGetField("PACT"));
                        bn.setBRE2(javaMI.mvxGetField("BRE2"));
                        bn.setBREM(javaMI.mvxGetField("BREM"));
                        bn.setREOP(javaMI.mvxGetField("REOP"));
                        bn.setATNR(javaMI.mvxGetField("ATNR"));
                        bn.setATNB(javaMI.mvxGetField("ATNB"));
                        bn.setSTAT(javaMI.mvxGetField("STAT"));
                        bn.setFLG("Yes");
                        bn.setRORN("");
                        bn.setRORL("");
                        ls.add(bn);
                    } else if (STOCK_LOT != null && STOCK_LOT.length() > 0 && STOCK_LOC != null && STOCK_LOC.length() > 0 && (javaMI.mvxGetField("WHSL") + javaMI.mvxGetField("BANO")).contains(STOCK_LOC + STOCK_LOT)) {
                        bn.setCONO(javaMI.mvxGetField("CONO"));
                        bn.setITNO(javaMI.mvxGetField("ITNO"));
                        bn.setITDS(javaMI.mvxGetField("ITDS"));
                        bn.setBANO(javaMI.mvxGetField("BANO"));
                        bn.setWHLO(javaMI.mvxGetField("WHLO"));
                        bn.setWHNM(javaMI.mvxGetField("WHNM"));
                        bn.setWHSL(javaMI.mvxGetField("WHSL"));
                        bn.setCAMU(javaMI.mvxGetField("CAMU"));
                        bn.setREPN(javaMI.mvxGetField("REPN"));
                        bn.setABCD(javaMI.mvxGetField("ABCD"));
                        bn.setITTY(javaMI.mvxGetField("ITTY"));
                        bn.setRESP(javaMI.mvxGetField("RESP"));
                        bn.setUNMS(javaMI.mvxGetField("UNMS"));
                        bn.setITGR(javaMI.mvxGetField("ITGR"));
                        bn.setPLCD(javaMI.mvxGetField("PLCD"));
                        bn.setSLTP(javaMI.mvxGetField("SLTP"));
                        bn.setWHLT(javaMI.mvxGetField("WHLT"));
                        bn.setIDDT(javaMI.mvxGetField("IDDT"));
                        bn.setODDT(javaMI.mvxGetField("ODDT"));
                        bn.setINDT(javaMI.mvxGetField("INDT"));
                        bn.setINON(javaMI.mvxGetField("INON"));
                        bn.setSTAS(javaMI.mvxGetField("STAS"));
                        bn.setRCLS(javaMI.mvxGetField("RCLS"));
                        bn.setPRDT(javaMI.mvxGetField("PRDT"));
                        bn.setSTQT(javaMI.mvxGetField("STQT"));
                        bn.setPHSQ(javaMI.mvxGetField("PHSQ"));
                        bn.setALOC(javaMI.mvxGetField("ALOC"));
                        bn.setALQT(javaMI.mvxGetField("ALQT"));
                        bn.setPHAQ(javaMI.mvxGetField("PHAQ"));
                        bn.setAUDE(javaMI.mvxGetField("AUDE"));
                        bn.setAUDD(javaMI.mvxGetField("AUDD"));
                        bn.setSTAQ(javaMI.mvxGetField("STAQ"));
                        bn.setPHA2(javaMI.mvxGetField("PHA2"));
                        bn.setPOCY(javaMI.mvxGetField("POCY"));
                        bn.setCAWE(javaMI.mvxGetField("CAWE"));
                        bn.setLPCY(javaMI.mvxGetField("LPCY"));
                        bn.setPPUN(javaMI.mvxGetField("PPUN"));
                        bn.setBREF(javaMI.mvxGetField("BREF"));
                        bn.setPACT(javaMI.mvxGetField("PACT"));
                        bn.setBRE2(javaMI.mvxGetField("BRE2"));
                        bn.setBREM(javaMI.mvxGetField("BREM"));
                        bn.setREOP(javaMI.mvxGetField("REOP"));
                        bn.setATNR(javaMI.mvxGetField("ATNR"));
                        bn.setATNB(javaMI.mvxGetField("ATNB"));
                        bn.setSTAT(javaMI.mvxGetField("STAT"));
                        bn.setFLG("Yes");
                        bn.setRORN("");
                        bn.setRORL("");
                        ls.add(bn);
                    } else if ((STOCK_LOC == null || STOCK_LOC.length() == 0) && STOCK_LOT != null && STOCK_LOT.length() > 0 && javaMI.mvxGetField("BANO").contains(STOCK_LOT)) {
                        bn.setCONO(javaMI.mvxGetField("CONO"));
                        bn.setITNO(javaMI.mvxGetField("ITNO"));
                        bn.setITDS(javaMI.mvxGetField("ITDS"));
                        bn.setBANO(javaMI.mvxGetField("BANO"));
                        bn.setWHLO(javaMI.mvxGetField("WHLO"));
                        bn.setWHNM(javaMI.mvxGetField("WHNM"));
                        bn.setWHSL(javaMI.mvxGetField("WHSL"));
                        bn.setCAMU(javaMI.mvxGetField("CAMU"));
                        bn.setREPN(javaMI.mvxGetField("REPN"));
                        bn.setABCD(javaMI.mvxGetField("ABCD"));
                        bn.setITTY(javaMI.mvxGetField("ITTY"));
                        bn.setRESP(javaMI.mvxGetField("RESP"));
                        bn.setUNMS(javaMI.mvxGetField("UNMS"));
                        bn.setITGR(javaMI.mvxGetField("ITGR"));
                        bn.setPLCD(javaMI.mvxGetField("PLCD"));
                        bn.setSLTP(javaMI.mvxGetField("SLTP"));
                        bn.setWHLT(javaMI.mvxGetField("WHLT"));
                        bn.setIDDT(javaMI.mvxGetField("IDDT"));
                        bn.setODDT(javaMI.mvxGetField("ODDT"));
                        bn.setINDT(javaMI.mvxGetField("INDT"));
                        bn.setINON(javaMI.mvxGetField("INON"));
                        bn.setSTAS(javaMI.mvxGetField("STAS"));
                        bn.setRCLS(javaMI.mvxGetField("RCLS"));
                        bn.setPRDT(javaMI.mvxGetField("PRDT"));
                        bn.setSTQT(javaMI.mvxGetField("STQT"));
                        bn.setPHSQ(javaMI.mvxGetField("PHSQ"));
                        bn.setALOC(javaMI.mvxGetField("ALOC"));
                        bn.setALQT(javaMI.mvxGetField("ALQT"));
                        bn.setPHAQ(javaMI.mvxGetField("PHAQ"));
                        bn.setAUDE(javaMI.mvxGetField("AUDE"));
                        bn.setAUDD(javaMI.mvxGetField("AUDD"));
                        bn.setSTAQ(javaMI.mvxGetField("STAQ"));
                        bn.setPHA2(javaMI.mvxGetField("PHA2"));
                        bn.setPOCY(javaMI.mvxGetField("POCY"));
                        bn.setCAWE(javaMI.mvxGetField("CAWE"));
                        bn.setLPCY(javaMI.mvxGetField("LPCY"));
                        bn.setPPUN(javaMI.mvxGetField("PPUN"));
                        bn.setBREF(javaMI.mvxGetField("BREF"));
                        bn.setPACT(javaMI.mvxGetField("PACT"));
                        bn.setBRE2(javaMI.mvxGetField("BRE2"));
                        bn.setBREM(javaMI.mvxGetField("BREM"));
                        bn.setREOP(javaMI.mvxGetField("REOP"));
                        bn.setATNR(javaMI.mvxGetField("ATNR"));
                        bn.setATNB(javaMI.mvxGetField("ATNB"));
                        bn.setSTAT(javaMI.mvxGetField("STAT"));
                        bn.setFLG("Yes");
                        bn.setRORN("");
                        bn.setRORL("");
                        ls.add(bn);
                    } else if ((STOCK_LOT == null || STOCK_LOT.length() == 0) && STOCK_LOC != null && STOCK_LOC.length() > 0 && javaMI.mvxGetField("WHSL").equals(STOCK_LOC)) {
                        bn.setCONO(javaMI.mvxGetField("CONO"));
                        bn.setITNO(javaMI.mvxGetField("ITNO"));
                        bn.setITDS(javaMI.mvxGetField("ITDS"));
                        bn.setBANO(javaMI.mvxGetField("BANO"));
                        bn.setWHLO(javaMI.mvxGetField("WHLO"));
                        bn.setWHNM(javaMI.mvxGetField("WHNM"));
                        bn.setWHSL(javaMI.mvxGetField("WHSL"));
                        bn.setCAMU(javaMI.mvxGetField("CAMU"));
                        bn.setREPN(javaMI.mvxGetField("REPN"));
                        bn.setABCD(javaMI.mvxGetField("ABCD"));
                        bn.setITTY(javaMI.mvxGetField("ITTY"));
                        bn.setRESP(javaMI.mvxGetField("RESP"));
                        bn.setUNMS(javaMI.mvxGetField("UNMS"));
                        bn.setITGR(javaMI.mvxGetField("ITGR"));
                        bn.setPLCD(javaMI.mvxGetField("PLCD"));
                        bn.setSLTP(javaMI.mvxGetField("SLTP"));
                        bn.setWHLT(javaMI.mvxGetField("WHLT"));
                        bn.setIDDT(javaMI.mvxGetField("IDDT"));
                        bn.setODDT(javaMI.mvxGetField("ODDT"));
                        bn.setINDT(javaMI.mvxGetField("INDT"));
                        bn.setINON(javaMI.mvxGetField("INON"));
                        bn.setSTAS(javaMI.mvxGetField("STAS"));
                        bn.setRCLS(javaMI.mvxGetField("RCLS"));
                        bn.setPRDT(javaMI.mvxGetField("PRDT"));
                        bn.setSTQT(javaMI.mvxGetField("STQT"));
                        bn.setPHSQ(javaMI.mvxGetField("PHSQ"));
                        bn.setALOC(javaMI.mvxGetField("ALOC"));
                        bn.setALQT(javaMI.mvxGetField("ALQT"));
                        bn.setPHAQ(javaMI.mvxGetField("PHAQ"));
                        bn.setAUDE(javaMI.mvxGetField("AUDE"));
                        bn.setAUDD(javaMI.mvxGetField("AUDD"));
                        bn.setSTAQ(javaMI.mvxGetField("STAQ"));
                        bn.setPHA2(javaMI.mvxGetField("PHA2"));
                        bn.setPOCY(javaMI.mvxGetField("POCY"));
                        bn.setCAWE(javaMI.mvxGetField("CAWE"));
                        bn.setLPCY(javaMI.mvxGetField("LPCY"));
                        bn.setPPUN(javaMI.mvxGetField("PPUN"));
                        bn.setBREF(javaMI.mvxGetField("BREF"));
                        bn.setPACT(javaMI.mvxGetField("PACT"));
                        bn.setBRE2(javaMI.mvxGetField("BRE2"));
                        bn.setBREM(javaMI.mvxGetField("BREM"));
                        bn.setREOP(javaMI.mvxGetField("REOP"));
                        bn.setATNR(javaMI.mvxGetField("ATNR"));
                        bn.setATNB(javaMI.mvxGetField("ATNB"));
                        bn.setSTAT(javaMI.mvxGetField("STAT"));
                        bn.setFLG("Yes");
                        bn.setRORN("");
                        bn.setRORL("");
                        ls.add(bn);

                    }

                }

                javaMI.mvxAccess(null);
            }

        }
        return ls;
    }

    public HashMap<String, Double> getLstViaItemForStock(String CONO, String ITNO, List loclst) throws SQLException {
        HashMap<String, Double> map = new HashMap<String, Double>();

        int recFlag;
        String ERROR = null;
        String identity = "LstViaItem";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("ITNO", ITNO.trim());
        recFlag = javaMI.mvxAccess("LstViaItem");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();

        } else {

            while (javaMI.mvxMore()) {
                MMS060LstViaItemBean bn = new MMS060LstViaItemBean();
                //  System.out.println(javaMI.mvxGetField("STAS") +":::"+javaMI.mvxGetField("WHSL").trim()+":::"+loclst);
                if (javaMI.mvxGetField("STAS").trim().equals("2") && loclst != null && loclst.contains(javaMI.mvxGetField("WHSL").trim())) {
                    try {
                        map.put(javaMI.mvxGetField("ITNO"), map.get(javaMI.mvxGetField("ITNO")) + Double.parseDouble(javaMI.mvxGetField("STAQ")));
                    } catch (Exception ee) {
                        map.put(javaMI.mvxGetField("ITNO"), Double.parseDouble(javaMI.mvxGetField("STAQ")));
                    }
                }

                javaMI.mvxAccess(null);
            }

        }
        return map;
    }

    public HashMap<String, Double> getLstViaItemForCorrDel(String CONO, String ITNO, List loclst) throws SQLException {
        HashMap<String, Double> map = new HashMap<String, Double>();

        int recFlag;
        String ERROR = null;
        String identity = "LstViaItem";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("ITNO", ITNO.trim());
        recFlag = javaMI.mvxAccess("LstViaItem");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();

        } else {

            while (javaMI.mvxMore()) {
                MMS060LstViaItemBean bn = new MMS060LstViaItemBean();

                if (javaMI.mvxGetField("STAS").trim().equals("2") && loclst != null && loclst.contains(javaMI.mvxGetField("WHSL").trim())) {
                    String str = javaMI.mvxGetField("ITNO") + ":" + javaMI.mvxGetField("BANO") + ":" + javaMI.mvxGetField("WHSL");
                    try {
                        map.put(str, map.get(str) + Double.parseDouble(javaMI.mvxGetField("STAQ")));
                    } catch (Exception ee) {
                        map.put(str, Double.parseDouble(javaMI.mvxGetField("STAQ")));
                    }
                }

                javaMI.mvxAccess(null);
            }

        }
        return map;
    }

    public HashMap<String, Double> getLstViaItemWithLoctanditem(String CONO, String ITNO, List loclst) throws SQLException {
        HashMap<String, Double> map = new HashMap<String, Double>();

        int recFlag;
        String ERROR = null;
        String identity = "LstViaItem";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("ITNO", ITNO.trim());
        recFlag = javaMI.mvxAccess("LstViaItem");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();

        } else {

            while (javaMI.mvxMore()) {


                if (javaMI.mvxGetField("STAS").trim().equals("2") && loclst != null && loclst.contains(javaMI.mvxGetField("WHSL").trim())) {
                    String str = javaMI.mvxGetField("ITNO") + ":" + javaMI.mvxGetField("WHSL");
                    try {
                        map.put(str, map.get(str) + Double.parseDouble(javaMI.mvxGetField("STAQ")));
                    } catch (Exception ee) {
                        map.put(str, Double.parseDouble(javaMI.mvxGetField("STAQ")));
                    }
                }

                javaMI.mvxAccess(null);
            }

        }
        return map;
    }

    public MMS060MIGetBean Get(String CONO, String WHLO, String ITNO, String WHSL, String BANO, String CAMU, String REPN, List loclst) {
        MMS060MIGetBean mms060GetBean = new MMS060MIGetBean();
        int recFlag;
        String ERROR = null;
        String identity = "Get";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);//Company
        javaMI.mvxSetField("WHLO", WHLO);//Warehouse
        javaMI.mvxSetField("ITNO", ITNO);//Item number
        javaMI.mvxSetField("WHSL", WHSL);//Location
        javaMI.mvxSetField("BANO", BANO);//Lot number
        javaMI.mvxSetField("CAMU", CAMU);//Container
        javaMI.mvxSetField("REPN", REPN);//Receiving number
        recFlag = javaMI.mvxAccess("Get");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            if (javaMI.mvxGetField("STAS").trim().equals("2") && loclst != null && loclst.contains(javaMI.mvxGetField("WHSL").trim())) {
                mms060GetBean.setCONO(javaMI.mvxGetField("CONO"));   //Company
                mms060GetBean.setWHLO(javaMI.mvxGetField("WHLO"));   //Warehouse
                mms060GetBean.setWHNM(javaMI.mvxGetField("WHNM"));   //Warehouse description
                mms060GetBean.setITNO(javaMI.mvxGetField("ITNO"));   //Item number
                mms060GetBean.setITDS(javaMI.mvxGetField("ITDS"));   //Name
                mms060GetBean.setWHSL(javaMI.mvxGetField("WHSL"));   //Location
                mms060GetBean.setBANO(javaMI.mvxGetField("BANO"));   //Lot number
                mms060GetBean.setCAMU(javaMI.mvxGetField("CAMU"));   //Container
                mms060GetBean.setREPN(javaMI.mvxGetField("REPN"));   //Receiving number
                mms060GetBean.setABCD(javaMI.mvxGetField("ABCD"));   //ABC class - volume
                mms060GetBean.setITTY(javaMI.mvxGetField("ITTY"));   //Item type
                mms060GetBean.setRESP(javaMI.mvxGetField("RESP"));   //Responsible
                mms060GetBean.setUNMS(javaMI.mvxGetField("UNMS"));   //Basic unit of measure
                mms060GetBean.setITGR(javaMI.mvxGetField("ITGR"));   //Item group
                mms060GetBean.setPLCD(javaMI.mvxGetField("PLCD"));   //Planning policy
                mms060GetBean.setSLTP(javaMI.mvxGetField("SLTP"));   //Stock zone
                mms060GetBean.setWHLT(javaMI.mvxGetField("WHLT"));   //Location type
                mms060GetBean.setIDDT(javaMI.mvxGetField("IDDT"));   //Last receipt date
                mms060GetBean.setODDT(javaMI.mvxGetField("ODDT"));   //Last issue date
                mms060GetBean.setINDT(javaMI.mvxGetField("INDT"));   //Latest physical inventory date
                mms060GetBean.setINON(javaMI.mvxGetField("INON"));   //Physical inventory in progress
                mms060GetBean.setSTAS(javaMI.mvxGetField("STAS"));   //Status - balance ID
                mms060GetBean.setRCLS(javaMI.mvxGetField("RCLS"));   //Reclassification date
                mms060GetBean.setPRDT(javaMI.mvxGetField("PRDT"));   //Priority date
                mms060GetBean.setSTQT(javaMI.mvxGetField("STQT"));   //On-hand balance approved
                mms060GetBean.setPHSQ(javaMI.mvxGetField("PHSQ"));   //On-hand balance
                mms060GetBean.setALOC(javaMI.mvxGetField("ALOC"));   //Allocatable
                mms060GetBean.setALQT(javaMI.mvxGetField("ALQT"));   //Allocated quantity - basic U/M
                mms060GetBean.setPHAQ(javaMI.mvxGetField("PHAQ"));   //Allocated quantity
                mms060GetBean.setAUDE(javaMI.mvxGetField("AUDE"));   //Automatic deletion
                mms060GetBean.setAUDD(javaMI.mvxGetField("AUDD"));   //Automatic deletion delay
                mms060GetBean.setSTAQ(javaMI.mvxGetField("STAQ"));   //Active physical inventory lines
                mms060GetBean.setPHA2(javaMI.mvxGetField("PHA2"));   //Active physical
                mms060GetBean.setPOCY(javaMI.mvxGetField("POCY"));   //Normal potency
                mms060GetBean.setCAWE(javaMI.mvxGetField("CAWE"));   //Catch weight
                mms060GetBean.setLPCY(javaMI.mvxGetField("LPCY"));   //Potency
                mms060GetBean.setPPUN(javaMI.mvxGetField("PPUN"));   //Purchase price U/M
                mms060GetBean.setBREF(javaMI.mvxGetField("BREF"));   //Lot reference 1
                mms060GetBean.setPACT(javaMI.mvxGetField("PACT"));   //Packaging
                mms060GetBean.setBRE2(javaMI.mvxGetField("BRE2"));   //Lot reference 2
                mms060GetBean.setBREM(javaMI.mvxGetField("BREM"));   //Remark
                mms060GetBean.setREOP(javaMI.mvxGetField("REOP"));   //Reorder point
                mms060GetBean.setATNR(javaMI.mvxGetField("ATNR"));   //Attribute number
                mms060GetBean.setATNB(javaMI.mvxGetField("ATNB"));   //Attribute number lot
                mms060GetBean.setSTAT(javaMI.mvxGetField("STAT"));   //Status
                mms060GetBean.setCAW2(javaMI.mvxGetField("CAW2"));   //Catch weight
                mms060GetBean.setINDI(javaMI.mvxGetField("INDI"));   //Lot control method
                mms060GetBean.setBACD(javaMI.mvxGetField("BACD"));   //Lot numbering method
                mms060GetBean.setEXPD(javaMI.mvxGetField("EXPD"));   //Expiration date method
                mms060GetBean.setACTI(javaMI.mvxGetField("ACTI"));   //Active or catch weight item
                mms060GetBean.setATMN(javaMI.mvxGetField("ATMN"));   //Attribute managed
                mms060GetBean.setCWUN(javaMI.mvxGetField("CWUN"));   //Catch weight unit of measure
                mms060GetBean.setCOMG(javaMI.mvxGetField("COMG"));   //Container management
            }
        }
        return mms060GetBean;
    }
}
