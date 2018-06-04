package shahi.Action.MI;

/**
 *
 * @author Ranjeet
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import shahi.Action.Beans.LstLineTransBean;
import shahi.Action.Beans.PCZ300MIListCostInfoBean;
import shahi.Action.Beans.PPS200MIGetHeadBean;
import shahi.Action.Beans.PoApprovalBean;
import shahi.Action.Beans.YPHeadBean;
import shahi.Action.MI.Beans.*;
import shahi.Action.PcdEntry.Beans.MMS200MIGetStyleBasicBean;
import shahi.Action.Poapp.beans.PoListPchBean;

public class PPS200MI extends BaseMI {

    public PPS200MI() {
        setProgram("PPS200MI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public PPS200MILstHeadBean LstHeadSingle(String CONO, String FACI, String PUNO, String PUSL, String PUST) {
        PPS200MILstHeadBean pps200MILstHeadBean = new PPS200MILstHeadBean();
        int recFlag;
        String ERROR = null;
        String identity = "LstHead";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PUSL", PUSL);
        javaMI.mvxSetField("PUST", PUST);

        recFlag = javaMI.mvxAccess("LstHead");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                pps200MILstHeadBean = new PPS200MILstHeadBean();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                pps200MILstHeadBean.setCONO(javaMI.mvxGetField("CONO"));
                pps200MILstHeadBean.setPUNO(javaMI.mvxGetField("PUNO"));
                pps200MILstHeadBean.setDONR(javaMI.mvxGetField("DONR"));
                pps200MILstHeadBean.setDOVA(javaMI.mvxGetField("DOVA"));
                pps200MILstHeadBean.setDATE(javaMI.mvxGetField("DATE"));
                pps200MILstHeadBean.setPUDT(javaMI.mvxGetField("PUDT"));
                if (pps200MILstHeadBean.getPUDT() != null && pps200MILstHeadBean.getPUDT().length() == 8) {
                    try {
                        pps200MILstHeadBean.setDPUDT(formatter1.format(formatter.parse(javaMI.mvxGetField("PUDT"))));
                    } catch (ParseException ex) {
                        Logger.getLogger(PPS200MI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                pps200MILstHeadBean.setDWDT(javaMI.mvxGetField("DWDT"));
                pps200MILstHeadBean.setLEDT(javaMI.mvxGetField("LEDT"));
                pps200MILstHeadBean.setCONM(javaMI.mvxGetField("CONM"));
                pps200MILstHeadBean.setREVN(javaMI.mvxGetField("REVN"));
                pps200MILstHeadBean.setRENM(javaMI.mvxGetField("RENM"));
                pps200MILstHeadBean.setSUNO(javaMI.mvxGetField("SUNO"));
                pps200MILstHeadBean.setCSCD(javaMI.mvxGetField("CSCD"));
                pps200MILstHeadBean.setBUYE(javaMI.mvxGetField("BUYE"));
                pps200MILstHeadBean.setCUCD(javaMI.mvxGetField("CUCD"));
                pps200MILstHeadBean.setTXCU(javaMI.mvxGetField("TXCU"));
                pps200MILstHeadBean.setTXPY(javaMI.mvxGetField("TXPY"));
                pps200MILstHeadBean.setTXMO(javaMI.mvxGetField("TXMO"));
                pps200MILstHeadBean.setTXDL(javaMI.mvxGetField("TXDL"));
                pps200MILstHeadBean.setTXAF(javaMI.mvxGetField("TXAF"));
                pps200MILstHeadBean.setTXPA(javaMI.mvxGetField("TXPA"));
                pps200MILstHeadBean.setTXHA(javaMI.mvxGetField("TXHA"));
                pps200MILstHeadBean.setYRE1(javaMI.mvxGetField("YRE1"));
                pps200MILstHeadBean.setPHNO(javaMI.mvxGetField("PHNO"));
                pps200MILstHeadBean.setTFNO(javaMI.mvxGetField("TFNO"));
                pps200MILstHeadBean.setREVV(javaMI.mvxGetField("REVV"));
                pps200MILstHeadBean.setREMK(javaMI.mvxGetField("REMK"));
                pps200MILstHeadBean.setREV1(javaMI.mvxGetField("REV1"));
                pps200MILstHeadBean.setCOAM(javaMI.mvxGetField("COAM"));
                pps200MILstHeadBean.setDIVI(javaMI.mvxGetField("DIVI"));
                pps200MILstHeadBean.setFACI(javaMI.mvxGetField("FACI"));
                pps200MILstHeadBean.setWHLO(javaMI.mvxGetField("WHLO"));
                pps200MILstHeadBean.setORTY(javaMI.mvxGetField("ORTY"));
                pps200MILstHeadBean.setLNCD(javaMI.mvxGetField("LNCD"));
                pps200MILstHeadBean.setTEPY(javaMI.mvxGetField("TEPY"));
                pps200MILstHeadBean.setMODL(javaMI.mvxGetField("MODL"));
                pps200MILstHeadBean.setTEDL(javaMI.mvxGetField("TEDL"));
                pps200MILstHeadBean.setTEAF(javaMI.mvxGetField("TEAF"));
                pps200MILstHeadBean.setTEPA(javaMI.mvxGetField("TEPA"));
                pps200MILstHeadBean.setRFID(javaMI.mvxGetField("RFID"));
                pps200MILstHeadBean.setTEL1(javaMI.mvxGetField("TEL1"));
                pps200MILstHeadBean.setHAFE(javaMI.mvxGetField("HAFE"));
                pps200MILstHeadBean.setPOTC(javaMI.mvxGetField("POTC"));
                pps200MILstHeadBean.setPYAD(javaMI.mvxGetField("PYAD"));
                pps200MILstHeadBean.setMTDP(javaMI.mvxGetField("MTDP"));
                pps200MILstHeadBean.setMTWP(javaMI.mvxGetField("MTWP"));
                pps200MILstHeadBean.setOURR(javaMI.mvxGetField("OURR"));
                pps200MILstHeadBean.setOURT(javaMI.mvxGetField("OURT"));
                pps200MILstHeadBean.setPRSU(javaMI.mvxGetField("PRSU"));
                pps200MILstHeadBean.setAGNT(javaMI.mvxGetField("AGNT"));
                pps200MILstHeadBean.setNTAM(javaMI.mvxGetField("NTAM"));
                pps200MILstHeadBean.setTOQT(javaMI.mvxGetField("TOQT"));
                pps200MILstHeadBean.setSAAM(javaMI.mvxGetField("SAAM"));
                pps200MILstHeadBean.setLOCD(javaMI.mvxGetField("LOCD"));
                pps200MILstHeadBean.setRASN(javaMI.mvxGetField("RASN"));
                pps200MILstHeadBean.setEXAT(javaMI.mvxGetField("EXAT"));
                pps200MILstHeadBean.setFUSC(javaMI.mvxGetField("FUSC"));
                pps200MILstHeadBean.setPYME(javaMI.mvxGetField("PYME"));
                pps200MILstHeadBean.setPURC(javaMI.mvxGetField("PURC"));
                pps200MILstHeadBean.setTEOR(javaMI.mvxGetField("TEOR"));
                pps200MILstHeadBean.setTLEX(javaMI.mvxGetField("TLEX"));
                pps200MILstHeadBean.setTLIN(javaMI.mvxGetField("TLIN"));
                pps200MILstHeadBean.setPUSL(javaMI.mvxGetField("PUSL"));
                pps200MILstHeadBean.setPUST(javaMI.mvxGetField("PUST"));
                javaMI.mvxAccess(null);
            }
        }
        return pps200MILstHeadBean;
    }

    public String[] GetLineTransList(String REPN) {
        int recFlag;
        String identity = "GetLineTransList";
        String par[] = new String[8];

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("REPN", REPN);
        javaMI.mvxSetField("PRTR", "4");

        recFlag = javaMI.mvxAccess("GetLineTrans");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            par[3] = javaMI.mvxGetLastError();
        } else {
            par[0] = javaMI.mvxGetField("CONO");
            par[1] = javaMI.mvxGetField("PUNO");
            par[2] = javaMI.mvxGetField("REPN");
            par[3] = javaMI.mvxGetField("RPQA");
            par[4] = javaMI.mvxGetField("SUDO");
            par[5] = javaMI.mvxGetField("PNLI");
            par[6] = javaMI.mvxGetField("PNLS");
            par[7] = javaMI.mvxGetField("WHLO");
        }
        return par;
    }

    public String[] GetLineList(String PUNO, String PNLI, String PNLS) {
        int recFlag;
        String identity = "GetLineList";
        String par[] = new String[9];

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);

        recFlag = javaMI.mvxAccess("GetLine");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            par[3] = javaMI.mvxGetLastError();

        } else {
            par[0] = javaMI.mvxGetField("SUNO");
            par[1] = javaMI.mvxGetField("ITNO");
            par[2] = javaMI.mvxGetField("PITD");
            par[3] = javaMI.mvxGetField("PUPR");
            par[4] = javaMI.mvxGetField("ORTY");
            par[5] = javaMI.mvxGetField("FACI");
            par[6] = javaMI.mvxGetField("RORN");
            par[7] = javaMI.mvxGetField("GRMT");
            par[8] = javaMI.mvxGetField("PUSL");


        }
        return par;
    }

    public boolean GetHead(String PUNO) {
        int recFlag;
        String identity = "GetHead";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            return false;
        } else {

            if (javaMI.mvxGetField("PUSL") != null && javaMI.mvxGetField("PUSL").length() > 0 && Integer.parseInt(javaMI.mvxGetField("PUSL").trim()) > 12) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String[] GetHeadList(String PUNO) {
        int recFlag;
        String identity = "GetHead";
        String par[] = new String[1];
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
        } else {
            par[0] = javaMI.mvxGetField("POTC");

        }
        return par;
    }

    public String[] GetAddressesList(String PUNO) {
        int recFlag;
        String identity = "GetAddressesList";
        String par[] = new String[1];

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetAddresses");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            par[0] = javaMI.mvxGetLastError();

        } else {
            par[0] = javaMI.mvxGetField("SNAM");
        }
        return par;
    }

    public List LstYpheadData(String SFAP, String APPR, String DIVI) {
        int recFlag;
        String identity = "LstYpheadData";
        List lstypheadList = new ArrayList();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SFAP", SFAP);
        javaMI.mvxSetField("APPR", APPR);
        recFlag = javaMI.mvxAccess("LstYpheadData");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            //System.out.println("PPS200MI "+javaMI.mvxGetLastError());
            //lstypheadList.add(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                lstypheadList.add(javaMI.mvxGetField("PUNO"));
                //lstypheadList.add(javaMI.mvxGetField("PONO"));
                //System.out.println(javaMI.mvxGetField("PONO"));
                javaMI.mvxAccess(null);
            }
        }
        return lstypheadList;
    }

    public List LstYpheadDataNew(String SFAP, String APPR, String DIVI, String FACI, String WHLO, String ORTY) {
        int recFlag;
        String identity = "LstYpheadData";
        List lstypheadList = new ArrayList();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SFAP", SFAP);
        javaMI.mvxSetField("APPR", APPR);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ORTY", ORTY);

        recFlag = javaMI.mvxAccess("LstYpheadData");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            //System.out.println("PPS200MI "+javaMI.mvxGetLastError());
            //lstypheadList.add(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                lstypheadList.add(javaMI.mvxGetField("PUNO"));
                //lstypheadList.add(javaMI.mvxGetField("PONO"));
                javaMI.mvxAccess(null);
            }
        }
        return lstypheadList;
    }

    public List LstYpheadDataNewbyPch(String SFAP, String APPR, String DIVI, String FACI, String WHLO, String ORTY, String USER_TYPE, Map<String, String> pchmap) {
        int recFlag;
        String identity = "LstYpheadData";
        List lstypheadList = new ArrayList();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SFAP", SFAP);
        javaMI.mvxSetField("APPR", APPR);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ORTY", ORTY);

        recFlag = javaMI.mvxAccess("LstYpheadData");
        if (recFlag > 0) {
        } else {
            while (javaMI.mvxMore()) {
                PPS200MI MIPCH = new PPS200MI();
                MIPCH.connect();
                List<String> pchlist = new ArrayList(MIPCH.getPCHLIST(javaMI.mvxGetField("PUNO")));
                MIPCH.destroyMI();
                MIPCH = null;
                if (USER_TYPE != null && USER_TYPE.equals("F")) {
                    boolean pchvalid = false;
                    for (int i = 0; i < pchlist.size(); i++) {
                        if (pchmap.get(pchlist.get(i)) != null) {
                            pchvalid = true;
                            break;
                        }
                    }
                    if (pchvalid == true) {
                        lstypheadList.add(new PoListPchBean(javaMI.mvxGetField("PUNO"), pchlist));
                    }
                } else {
                    lstypheadList.add(new PoListPchBean(javaMI.mvxGetField("PUNO"), pchlist));
                }


                javaMI.mvxAccess(null);
            }
        }
        return lstypheadList;
    }

    public List LstYpheadDataNew(String SFAP, String APPR, String DIVI, String PUNO, String FACI, String WHLO, String ORTY) {
        int recFlag;
        String identity = "LstYpheadData";
        List lstypheadList = new ArrayList();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SFAP", SFAP);
        javaMI.mvxSetField("APPR", APPR);
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("WHLO", WHLO);
        javaMI.mvxSetField("ORTY", ORTY);
        recFlag = javaMI.mvxAccess("LstYpheadData");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            //System.out.println("PPS200MI "+javaMI.mvxGetLastError());
            //lstypheadList.add(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                if (javaMI.mvxGetField("FACI").equals(FACI)) {
                    lstypheadList.add(javaMI.mvxGetField("PUNO"));
                }
                //lstypheadList.add(javaMI.mvxGetField("PONO"));
                javaMI.mvxAccess(null);
            }
        }
        return lstypheadList;
    }

    public PoApprovalBean GetHeadNew(String PUNO) throws Exception {
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        PoApprovalBean bean = null;
        int recFlag;
        String identity = "GetHead";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            System.out.println("PPS200MI[GetHeadNew]- " + javaMI.mvxGetLastError());
        } else {
            String mydate = myFormat.format(fromUser.parse(javaMI.mvxGetField("PUDT")));
            String edate = myFormat.format(fromUser.parse(javaMI.mvxGetField("DATE")));
            //System.out.println(javaMI.mvxGetField("TOER"));
            bean = new PoApprovalBean();
            bean.setFACI(javaMI.mvxGetField("FACI"));
            bean.setWHLO(javaMI.mvxGetField("WHLO"));
            bean.setPUNO(javaMI.mvxGetField("PUNO"));
            bean.setORTY(javaMI.mvxGetField("ORTY"));
            bean.setPUDT(mydate);
            bean.setPUDT1(javaMI.mvxGetField("PUDT"));
            bean.setSUNO(javaMI.mvxGetField("SUNO"));
            bean.setTOER(javaMI.mvxGetField("TEOR"));
            bean.setCOAM(javaMI.mvxGetField("COAM"));
            bean.setCUCD(javaMI.mvxGetField("CUCD"));
            bean.setPUSL(javaMI.mvxGetField("PUSL"));
            bean.setPUST(javaMI.mvxGetField("PUST"));
            bean.setDIVI(javaMI.mvxGetField("DIVI"));
            bean.setOURT(javaMI.mvxGetField("OURT"));
            bean.setOURR(javaMI.mvxGetField("OURR"));
            bean.setBUYE(javaMI.mvxGetField("BUYE"));
            bean.setORTY(javaMI.mvxGetField("ORTY"));
            bean.setDATE(edate);
        }
        return bean;
    }

    public PPS200MIGetHeadBean GetHeadALL(String PUNO) throws Exception {
        PPS200MIGetHeadBean bean = null;
        int recFlag;
        String identity = "GetHead";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            System.out.println("PPS200MI[GetHeadNew]- " + javaMI.mvxGetLastError());
        } else {
            bean = new PPS200MIGetHeadBean();
            bean.setCONO(javaMI.mvxGetField("CONO"));
            bean.setPUNO(javaMI.mvxGetField("PUNO"));
            bean.setDONR(javaMI.mvxGetField("DONR"));
            bean.setDOVA(javaMI.mvxGetField("DOVA"));
            bean.setDATE(javaMI.mvxGetField("DATE"));
            bean.setPUDT(javaMI.mvxGetField("PUDT"));
            bean.setDWDT(javaMI.mvxGetField("DWDT"));
            bean.setLEDT(javaMI.mvxGetField("LEDT"));
            bean.setCONM(javaMI.mvxGetField("CONM"));
            bean.setREVN(javaMI.mvxGetField("REVN"));
            bean.setRENM(javaMI.mvxGetField("RENM"));
            bean.setSUNO(javaMI.mvxGetField("SUNO"));
            bean.setCSCD(javaMI.mvxGetField("CSCD"));
            bean.setBUYE(javaMI.mvxGetField("BUYE"));
            bean.setCUCD(javaMI.mvxGetField("CUCD"));
            bean.setTXCU(javaMI.mvxGetField("TXCU"));
            bean.setTXPY(javaMI.mvxGetField("TXPY"));
            bean.setTXMO(javaMI.mvxGetField("TXMO"));
            bean.setTXDL(javaMI.mvxGetField("TXDL"));
            bean.setTXAF(javaMI.mvxGetField("TXAF"));
            bean.setTXPA(javaMI.mvxGetField("TXPA"));
            bean.setTXHA(javaMI.mvxGetField("TXHA"));
            bean.setYRE1(javaMI.mvxGetField("YRE1"));
            bean.setPHNO(javaMI.mvxGetField("PHNO"));
            bean.setTFNO(javaMI.mvxGetField("TFNO"));
            bean.setREVV(javaMI.mvxGetField("REVV"));
            bean.setREMK(javaMI.mvxGetField("REMK"));
            bean.setREV1(javaMI.mvxGetField("REV1"));
            bean.setCOAM(javaMI.mvxGetField("COAM"));
            bean.setDIVI(javaMI.mvxGetField("DIVI"));
            bean.setFACI(javaMI.mvxGetField("FACI"));
            bean.setWHLO(javaMI.mvxGetField("WHLO"));
            bean.setORTY(javaMI.mvxGetField("ORTY"));
            bean.setLNCD(javaMI.mvxGetField("LNCD"));
            bean.setTEPY(javaMI.mvxGetField("TEPY"));
            bean.setMODL(javaMI.mvxGetField("MODL"));
            bean.setTEDL(javaMI.mvxGetField("TEDL"));
            bean.setTEAF(javaMI.mvxGetField("TEAF"));
            bean.setTEPA(javaMI.mvxGetField("TEPA"));
            bean.setRFID(javaMI.mvxGetField("RFID"));
            bean.setTEL1(javaMI.mvxGetField("TEL1"));
            bean.setHAFE(javaMI.mvxGetField("HAFE"));
            bean.setPOTC(javaMI.mvxGetField("POTC"));
            bean.setPYAD(javaMI.mvxGetField("PYAD"));
            bean.setMTDP(javaMI.mvxGetField("MTDP"));
            bean.setMTWP(javaMI.mvxGetField("MTWP"));
            bean.setOURR(javaMI.mvxGetField("OURR"));
            bean.setOURT(javaMI.mvxGetField("OURT"));
            bean.setPRSU(javaMI.mvxGetField("PRSU"));
            bean.setAGNT(javaMI.mvxGetField("AGNT"));
            bean.setXSTY(javaMI.mvxGetField("XSTY"));
            bean.setXSTF(javaMI.mvxGetField("XSTF"));
            bean.setPUSL(javaMI.mvxGetField("PUSL"));
            bean.setPUST(javaMI.mvxGetField("PUST"));
            bean.setTOER(javaMI.mvxGetField("TEOR"));



        }
        return bean;
    }

    public String ApprovePo(String PUNO, String DIVI, String AUTD) {
        String status;
        int recFlag;
        String identity = "ApprovePo";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("AUTD", AUTD);
        recFlag = javaMI.mvxAccess("ApprovePo");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();

            status = "NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }

    public String DeApprovePo(String PUNO, String DIVI) {
        String status;
        int recFlag;
        String identity = "DeApprovePo";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("DeApprovePo");
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();

            status = "NOK-P";
        } else {
            status = "OK-P";
        }
        return status;
    }

    public List LstYpheadData(String SFAP, String APPR, String DIVI, String PUNO) {
        int recFlag;
        String identity = "LstYpheadData";
        List<YPHeadBean> lstypheadList = new ArrayList();
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SFAP", SFAP);
        javaMI.mvxSetField("APPR", APPR);
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("LstYpheadData");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            //System.out.println("PPS200MI "+javaMI.mvxGetLastError());
            //lstypheadList.add(javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                //lstypheadList.add(new YPHeadBean(javaMI.mvxGetField("PONO"),javaMI.mvxGetField("FACI"),javaMI.mvxGetField("WHLO"),javaMI.mvxGetField("ORTY")));
                lstypheadList.add(new YPHeadBean(javaMI.mvxGetField("PUNO"), javaMI.mvxGetField("FACI"), javaMI.mvxGetField("WHLO"), javaMI.mvxGetField("ORTY")));
                //System.out.println(javaMI.mvxGetField("PONO"));
                javaMI.mvxAccess(null);
            }
        }
        return lstypheadList;
    }

    //for po print 
    public PPS200MIBean getHeadPo(String PUNO) throws Exception {

        PPS200MIBean bean = null;
        int recFlag;
        String identity = "GetHead";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetHead");
        if (recFlag > 0) {
            System.out.println("PPS200MI[getHeadPo]- " + javaMI.mvxGetLastError());
        } else {

            bean = new PPS200MIBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("PUNO"), javaMI.mvxGetField("DONR"), javaMI.mvxGetField("DOVA"), javaMI.mvxGetField("DATE"), javaMI.mvxGetField("PUDT"), javaMI.mvxGetField("DWDT"), javaMI.mvxGetField("LEDT"), javaMI.mvxGetField("CONM"), javaMI.mvxGetField("REVN"), javaMI.mvxGetField("RENM"), javaMI.mvxGetField("SUNO"), javaMI.mvxGetField("CSCD"), javaMI.mvxGetField("BUYE"), javaMI.mvxGetField("CUCD"), javaMI.mvxGetField("TXCU"), javaMI.mvxGetField("TXPY"), javaMI.mvxGetField("TXMO"), javaMI.mvxGetField("TXDL"), javaMI.mvxGetField("TXAF"), javaMI.mvxGetField("TXPA"), javaMI.mvxGetField("TXHA"), javaMI.mvxGetField("YRE1"), javaMI.mvxGetField("PHNO"), javaMI.mvxGetField("TFNO"), javaMI.mvxGetField("REVV"), javaMI.mvxGetField("REMK"), javaMI.mvxGetField("REV1"), javaMI.mvxGetField("COAM"), javaMI.mvxGetField("DIVI"), javaMI.mvxGetField("FACI"), javaMI.mvxGetField("WHLO"), javaMI.mvxGetField("ORTY"), javaMI.mvxGetField("LNCD"), javaMI.mvxGetField("TEPY"), javaMI.mvxGetField("MODL"),
                    javaMI.mvxGetField("TEDL"), javaMI.mvxGetField("TEAF"), javaMI.mvxGetField("TEPA"), javaMI.mvxGetField("RFID"), javaMI.mvxGetField("TEL1"), javaMI.mvxGetField("HAFE"), javaMI.mvxGetField("POTC"), javaMI.mvxGetField("PYAD"), javaMI.mvxGetField("MTDP"), javaMI.mvxGetField("MTWP"), javaMI.mvxGetField("OURR"), javaMI.mvxGetField("OURT"), javaMI.mvxGetField("PRSU"), javaMI.mvxGetField("AGNT"), javaMI.mvxGetField("XSTY"), javaMI.mvxGetField("XSTF"), javaMI.mvxGetField("PUSL"), javaMI.mvxGetField("TEOR"));
        }
        return bean;
    }

    public List<PPS200MILstHeadTxtBean> getLstHeadTxtPo(String PUNO, int st) {
        List<PPS200MILstHeadTxtBean> list1 = new ArrayList<PPS200MILstHeadTxtBean>();

        PPS200MILstHeadTxtBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);


        recFlag = javaMI.mvxAccess("LstHeadTxt");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstHeadTxt]- " + javaMI.mvxGetLastError());

        } else {

            while (javaMI.mvxMore()) {
                if (javaMI.mvxGetField("TYTR") != null && Integer.parseInt(javaMI.mvxGetField("TYTR").trim()) == st) {


                    ovb = new PPS200MILstHeadTxtBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("PUNO"), javaMI.mvxGetField("LNCD"), javaMI.mvxGetField("TXID"), javaMI.mvxGetField("TYTR"), javaMI.mvxGetField("TXVR"), javaMI.mvxGetField("TXCC"), javaMI.mvxGetField("TXEI"), javaMI.mvxGetField("LINO"), javaMI.mvxGetField("TX60"));

                    list1.add(ovb);


                }
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<PPS200MILstHeadTxtBean> getLstHeadTxtPoNew(String PUNO, int st) {
        List<PPS200MILstHeadTxtBean> list1 = new ArrayList<PPS200MILstHeadTxtBean>();

        PPS200MILstHeadTxtBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);


        recFlag = javaMI.mvxAccess("LstHeadTxt");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstHeadTxt]- " + javaMI.mvxGetLastError());

        } else {

            while (javaMI.mvxMore()) {
                if (javaMI.mvxGetField("TYTR") != null) {


                    ovb = new PPS200MILstHeadTxtBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("PUNO"), javaMI.mvxGetField("LNCD"), javaMI.mvxGetField("TXID"), javaMI.mvxGetField("TYTR"), javaMI.mvxGetField("TXVR"), javaMI.mvxGetField("TXCC"), javaMI.mvxGetField("TXEI"), javaMI.mvxGetField("LINO"), javaMI.mvxGetField("TX60"));

                    list1.add(ovb);


                }
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public PPS200MIGetAddressesBean getAddressesPo(String PUNO) throws Exception {

        PPS200MIGetAddressesBean bean = null;
        int recFlag;
        String identity = "GetAddresses";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetAddresses");
        if (recFlag > 0) {
            System.out.println("PPS200MI[GetAddresses]- " + javaMI.mvxGetLastError());
        } else {

            bean = new PPS200MIGetAddressesBean(javaMI.mvxGetField("CONO"), javaMI.mvxGetField("PUNO"), javaMI.mvxGetField("SAK2"), javaMI.mvxGetField("SAK3"), javaMI.mvxGetField("SNAM"), javaMI.mvxGetField("SAD1"), javaMI.mvxGetField("SAD2"),
                    javaMI.mvxGetField("SAD3"), javaMI.mvxGetField("SAD4"), javaMI.mvxGetField("VAK2"), javaMI.mvxGetField("VAK3"), javaMI.mvxGetField("VNAM"), javaMI.mvxGetField("VAD1"), javaMI.mvxGetField("VAD2"), javaMI.mvxGetField("VAD3"), javaMI.mvxGetField("VAD4"), javaMI.mvxGetField("INAM"), javaMI.mvxGetField("IAD1"), javaMI.mvxGetField("IAD2"), javaMI.mvxGetField("IAD3"), javaMI.mvxGetField("IAD4"), javaMI.mvxGetField("DAK2"), javaMI.mvxGetField("DAK3"), javaMI.mvxGetField("DNAM"), javaMI.mvxGetField("DAD1"), javaMI.mvxGetField("DAD2"), javaMI.mvxGetField("DAD3"), javaMI.mvxGetField("DAD4"), javaMI.mvxGetField("STOW"), javaMI.mvxGetField("SECA"), javaMI.mvxGetField("SPON"), javaMI.mvxGetField("SCSC"), javaMI.mvxGetField("VTOW"), javaMI.mvxGetField("VECA"), javaMI.mvxGetField("VPON"), javaMI.mvxGetField("VCSC"), javaMI.mvxGetField("ITOW"), javaMI.mvxGetField("IECA"), javaMI.mvxGetField("IPON"), javaMI.mvxGetField("ICSC"), javaMI.mvxGetField("DTOW"), javaMI.mvxGetField("DECA"), javaMI.mvxGetField("DPON"), javaMI.mvxGetField("DCSC"));
        }
        return bean;
    }

    public List<PPS200MILstLineBean> getLstLinePo(String PUNO) {
        List<PPS200MILstLineBean> list1 = new ArrayList<PPS200MILstLineBean>();

        PPS200MILstLineBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);

        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLine]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                int temppnls = 0;
                if (javaMI.mvxGetField("PNLS") != null && javaMI.mvxGetField("PNLS").trim().length() > 0 && Integer.parseInt(javaMI.mvxGetField("PNLS").trim()) < 500) {
                    temppnls = 0;
                } else {
                    temppnls = 1;
                }
                if (temppnls == 0) {
                    ovb = new PPS200MILstLineBean();

                    ovb.setCONO(javaMI.mvxGetField("CONO"));
                    ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                    ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                    ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                    ovb.setITNO(javaMI.mvxGetField("ITNO"));
                    ovb.setSITE(javaMI.mvxGetField("SITE"));
                    ovb.setCODT(javaMI.mvxGetField("CODT"));
                    ovb.setCFQA(javaMI.mvxGetField("CFQA"));
                    ovb.setPUUN(javaMI.mvxGetField("PUUN"));
                    ovb.setITDS(javaMI.mvxGetField("ITDS"));
                    ovb.setEACD(javaMI.mvxGetField("EACD"));
                    ovb.setFACI(javaMI.mvxGetField("FACI"));
                    ovb.setWHLO(javaMI.mvxGetField("WHLO"));
                    ovb.setPOTC(javaMI.mvxGetField("POTC"));
                    ovb.setPUST(javaMI.mvxGetField("PUST"));
                    ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                    ovb.setSUNO(javaMI.mvxGetField("SUNO"));
                    ovb.setPRCS(javaMI.mvxGetField("PRCS"));
                    ovb.setSUFI(javaMI.mvxGetField("SUFI"));
                    ovb.setPITD(javaMI.mvxGetField("PITD"));
                    ovb.setPITT(javaMI.mvxGetField("PITT"));
                    ovb.setSORN(javaMI.mvxGetField("SORN"));
                    ovb.setPUPR(javaMI.mvxGetField("PUPR"));
                    ovb.setODI1(javaMI.mvxGetField("ODI1"));
                    ovb.setODI2(javaMI.mvxGetField("ODI2"));
                    ovb.setODI3(javaMI.mvxGetField("ODI3"));
                    ovb.setCPPR(javaMI.mvxGetField("CPPR"));
                    ovb.setCFD1(javaMI.mvxGetField("CFD1"));
                    ovb.setCFD2(javaMI.mvxGetField("CFD2"));
                    ovb.setCFD3(javaMI.mvxGetField("CFD3"));
                    ovb.setPPUN(javaMI.mvxGetField("PPUN"));
                    ovb.setPUCD(javaMI.mvxGetField("PUCD"));
                    ovb.setCPUC(javaMI.mvxGetField("CPUC"));
                    ovb.setLNAM(javaMI.mvxGetField("LNAM"));
                    ovb.setPTCD(javaMI.mvxGetField("PTCD"));
                    ovb.setDWDT(javaMI.mvxGetField("DWDT"));
                    ovb.setORQA(javaMI.mvxGetField("ORQA"));
                    ovb.setADQA(javaMI.mvxGetField("ADQA"));
                    ovb.setTNQA(javaMI.mvxGetField("TNQA"));
                    ovb.setRVQA(javaMI.mvxGetField("RVQA"));
                    ovb.setRCDT(javaMI.mvxGetField("RCDT"));
                    ovb.setCAQA(javaMI.mvxGetField("CAQA"));
                    ovb.setRJQA(javaMI.mvxGetField("RJQA"));
                    ovb.setSDQA(javaMI.mvxGetField("SDQA"));
                    ovb.setIVQA(javaMI.mvxGetField("IVQA"));
                    ovb.setIDAT(javaMI.mvxGetField("IDAT"));
                    ovb.setPLPN(javaMI.mvxGetField("PLPN"));
                    ovb.setPLPS(javaMI.mvxGetField("PLPS"));
                    ovb.setPURC(javaMI.mvxGetField("PURC"));
                    ovb.setBUYE(javaMI.mvxGetField("BUYE"));
                    ovb.setGRMT(javaMI.mvxGetField("GRMT"));
                    ovb.setPACT(javaMI.mvxGetField("PACT"));
                    ovb.setTXID(javaMI.mvxGetField("TXID"));
                    ovb.setECVE(javaMI.mvxGetField("ECVE"));
                    ovb.setOURR(javaMI.mvxGetField("OURR"));
                    ovb.setOURT(javaMI.mvxGetField("OURT"));
                    ovb.setVTCD(javaMI.mvxGetField("VTCD"));
                    ovb.setATNR(javaMI.mvxGetField("ATNR"));
                    ovb.setRORC(javaMI.mvxGetField("RORC"));
                    ovb.setRORN(javaMI.mvxGetField("RORN"));
                    ovb.setRORL(javaMI.mvxGetField("RORL"));
                    ovb.setRORX(javaMI.mvxGetField("RORX"));
                    ovb.setPRIP(javaMI.mvxGetField("PRIP"));
                    ovb.setIRCV(javaMI.mvxGetField("IRCV"));
                    ovb.setPROD(javaMI.mvxGetField("PROD"));
                    ovb.setSDPC(javaMI.mvxGetField("SDPC"));
                    ovb.setEXEP(javaMI.mvxGetField("EXEP"));
                    ovb.setINEP(javaMI.mvxGetField("INEP"));
                    ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                    ovb.setUPAV(javaMI.mvxGetField("UPAV"));
                    list1.add(ovb);
                }
                javaMI.mvxAccess(null);

            }
        }
        return list1;
    }

    public List<PPS200MILstLineBean> getLstLinePoNew(String PUNO) {
        List<PPS200MILstLineBean> list1 = new ArrayList<PPS200MILstLineBean>();

        PPS200MILstLineBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);

        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLine]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                int temppnls = 0;
                if (javaMI.mvxGetField("PNLS") != null && javaMI.mvxGetField("PNLS").trim().length() > 0 && Integer.parseInt(javaMI.mvxGetField("PNLS").trim()) < 500) {
                    temppnls = 0;
                } else {
                    temppnls = 1;
                }
                if (temppnls == 0) {
                    ovb = new PPS200MILstLineBean();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                    String DWDT = "";
                    if (javaMI.mvxGetField("DWDT") != null) {
                        Date dt;
                        try {
                            dt = formatter.parse(javaMI.mvxGetField("DWDT"));
                            DWDT = formatter1.format(dt);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    ovb.setCONO(javaMI.mvxGetField("CONO"));
                    ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                    ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                    ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                    ovb.setITNO(javaMI.mvxGetField("ITNO"));
                    ovb.setSITE(javaMI.mvxGetField("SITE"));
                    ovb.setCODT(javaMI.mvxGetField("CODT"));
                    ovb.setCFQA(javaMI.mvxGetField("CFQA"));
                    ovb.setPUUN(javaMI.mvxGetField("PUUN"));
                    ovb.setITDS(javaMI.mvxGetField("ITDS"));
                    ovb.setEACD(javaMI.mvxGetField("EACD"));
                    ovb.setFACI(javaMI.mvxGetField("FACI"));
                    ovb.setWHLO(javaMI.mvxGetField("WHLO"));
                    ovb.setPOTC(javaMI.mvxGetField("POTC"));
                    ovb.setPUST(javaMI.mvxGetField("PUST"));
                    ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                    ovb.setSUNO(javaMI.mvxGetField("SUNO"));
                    ovb.setPRCS(javaMI.mvxGetField("PRCS"));
                    ovb.setSUFI(javaMI.mvxGetField("SUFI"));
                    ovb.setPITD(javaMI.mvxGetField("PITD"));
                    ovb.setPITT(javaMI.mvxGetField("PITT"));
                    ovb.setSORN(javaMI.mvxGetField("SORN"));
                    ovb.setPUPR(javaMI.mvxGetField("PUPR"));
                    ovb.setODI1(javaMI.mvxGetField("ODI1"));
                    ovb.setODI2(javaMI.mvxGetField("ODI2"));
                    ovb.setODI3(javaMI.mvxGetField("ODI3"));
                    ovb.setCPPR(javaMI.mvxGetField("CPPR"));
                    ovb.setCFD1(javaMI.mvxGetField("CFD1"));
                    ovb.setCFD2(javaMI.mvxGetField("CFD2"));
                    ovb.setCFD3(javaMI.mvxGetField("CFD3"));
                    ovb.setPPUN(javaMI.mvxGetField("PPUN"));
                    ovb.setPUCD(javaMI.mvxGetField("PUCD"));
                    ovb.setCPUC(javaMI.mvxGetField("CPUC"));
                    ovb.setLNAM(javaMI.mvxGetField("LNAM"));
                    ovb.setPTCD(javaMI.mvxGetField("PTCD"));
                    ovb.setDWDT(DWDT);
                    ovb.setORQA(javaMI.mvxGetField("ORQA"));
                    ovb.setADQA(javaMI.mvxGetField("ADQA"));
                    ovb.setTNQA(javaMI.mvxGetField("TNQA"));
                    ovb.setRVQA(javaMI.mvxGetField("RVQA"));
                    ovb.setRCDT(javaMI.mvxGetField("RCDT"));
                    ovb.setCAQA(javaMI.mvxGetField("CAQA"));
                    ovb.setRJQA(javaMI.mvxGetField("RJQA"));
                    ovb.setSDQA(javaMI.mvxGetField("SDQA"));
                    ovb.setIVQA(javaMI.mvxGetField("IVQA"));
                    ovb.setIDAT(javaMI.mvxGetField("IDAT"));
                    ovb.setPLPN(javaMI.mvxGetField("PLPN"));
                    ovb.setPLPS(javaMI.mvxGetField("PLPS"));
                    ovb.setPURC(javaMI.mvxGetField("PURC"));
                    ovb.setBUYE(javaMI.mvxGetField("BUYE"));
                    ovb.setGRMT(javaMI.mvxGetField("GRMT"));
                    ovb.setPACT(javaMI.mvxGetField("PACT"));
                    ovb.setTXID(javaMI.mvxGetField("TXID"));
                    ovb.setECVE(javaMI.mvxGetField("ECVE"));
                    ovb.setOURR(javaMI.mvxGetField("OURR"));
                    ovb.setOURT(javaMI.mvxGetField("OURT"));
                    ovb.setVTCD(javaMI.mvxGetField("VTCD"));
                    ovb.setATNR(javaMI.mvxGetField("ATNR"));
                    ovb.setRORC(javaMI.mvxGetField("RORC"));
                    ovb.setRORN(javaMI.mvxGetField("RORN"));
                    ovb.setRORL(javaMI.mvxGetField("RORL"));
                    ovb.setRORX(javaMI.mvxGetField("RORX"));
                    ovb.setPRIP(javaMI.mvxGetField("PRIP"));
                    ovb.setIRCV(javaMI.mvxGetField("IRCV"));
                    ovb.setPROD(javaMI.mvxGetField("PROD"));
                    ovb.setSDPC(javaMI.mvxGetField("SDPC"));
                    ovb.setEXEP(javaMI.mvxGetField("EXEP"));
                    ovb.setINEP(javaMI.mvxGetField("INEP"));
                    ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                    list1.add(ovb);
                }
                javaMI.mvxAccess(null);

            }
        }
        return list1;
    }

    public Set<String> getPCHLIST(String PUNO) {

        Set<String> pchlist = new HashSet<String>();
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);

        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLine]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {

                pchlist.add(javaMI.mvxGetField("ACRF"));
                javaMI.mvxAccess(null);
                break;
            }
        }
        return pchlist;
    }

    public List<PPS200MILstLineBean> getLstLinePoNew1(String PUNO) {
        List<PPS200MILstLineBean> list1 = new ArrayList<PPS200MILstLineBean>();

        PPS200MILstLineBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);

        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLine]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                int temppnls = 0;

                ovb = new PPS200MILstLineBean();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                String DWDT = "";
                if (javaMI.mvxGetField("DWDT") != null) {
                    Date dt;
                    try {
                        dt = formatter.parse(javaMI.mvxGetField("DWDT"));
                        DWDT = formatter1.format(dt);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                //System.out.println(javaMI.mvxGetField("ITNO"));
                ovb.setCONO(javaMI.mvxGetField("CONO"));
                ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                ovb.setITNO(javaMI.mvxGetField("ITNO"));
                ovb.setSITE(javaMI.mvxGetField("SITE"));
                ovb.setCODT(javaMI.mvxGetField("CODT"));
                ovb.setCFQA(javaMI.mvxGetField("CFQA"));
                ovb.setPUUN(javaMI.mvxGetField("PUUN"));
                ovb.setITDS(javaMI.mvxGetField("ITDS"));
                ovb.setEACD(javaMI.mvxGetField("EACD"));
                ovb.setFACI(javaMI.mvxGetField("FACI"));
                ovb.setWHLO(javaMI.mvxGetField("WHLO"));
                ovb.setPOTC(javaMI.mvxGetField("POTC"));
                ovb.setPUST(javaMI.mvxGetField("PUST"));
                ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                ovb.setSUNO(javaMI.mvxGetField("SUNO"));
                ovb.setPRCS(javaMI.mvxGetField("PRCS"));
                ovb.setSUFI(javaMI.mvxGetField("SUFI"));
                ovb.setPITD(javaMI.mvxGetField("PITD"));
                ovb.setPITT(javaMI.mvxGetField("PITT"));
                ovb.setSORN(javaMI.mvxGetField("SORN"));
                ovb.setPUPR(javaMI.mvxGetField("PUPR"));
                ovb.setODI1(javaMI.mvxGetField("ODI1"));
                ovb.setODI2(javaMI.mvxGetField("ODI2"));
                ovb.setODI3(javaMI.mvxGetField("ODI3"));
                ovb.setCPPR(javaMI.mvxGetField("CPPR"));
                ovb.setCFD1(javaMI.mvxGetField("CFD1"));
                ovb.setCFD2(javaMI.mvxGetField("CFD2"));
                ovb.setCFD3(javaMI.mvxGetField("CFD3"));
                ovb.setPPUN(javaMI.mvxGetField("PPUN"));
                ovb.setPUCD(javaMI.mvxGetField("PUCD"));
                ovb.setCPUC(javaMI.mvxGetField("CPUC"));
                ovb.setLNAM(javaMI.mvxGetField("LNAM"));

                ovb.setPTCD(javaMI.mvxGetField("PTCD"));
                ovb.setDWDT(DWDT);
                ovb.setORQA(javaMI.mvxGetField("ORQA"));
                ovb.setADQA(javaMI.mvxGetField("ADQA"));
                ovb.setTNQA(javaMI.mvxGetField("TNQA"));
                ovb.setRVQA(javaMI.mvxGetField("RVQA"));
                ovb.setRCDT(javaMI.mvxGetField("RCDT"));
                ovb.setCAQA(javaMI.mvxGetField("CAQA"));
                ovb.setRJQA(javaMI.mvxGetField("RJQA"));
                ovb.setSDQA(javaMI.mvxGetField("SDQA"));
                ovb.setIVQA(javaMI.mvxGetField("IVQA"));
                ovb.setIDAT(javaMI.mvxGetField("IDAT"));
                ovb.setPLPN(javaMI.mvxGetField("PLPN"));
                ovb.setPLPS(javaMI.mvxGetField("PLPS"));
                ovb.setPURC(javaMI.mvxGetField("PURC"));
                ovb.setBUYE(javaMI.mvxGetField("BUYE"));
                ovb.setGRMT(javaMI.mvxGetField("GRMT"));
                ovb.setPACT(javaMI.mvxGetField("PACT"));
                ovb.setTXID(javaMI.mvxGetField("TXID"));
                ovb.setECVE(javaMI.mvxGetField("ECVE"));
                ovb.setOURR(javaMI.mvxGetField("OURR"));
                ovb.setOURT(javaMI.mvxGetField("OURT"));
                ovb.setVTCD(javaMI.mvxGetField("VTCD"));
                ovb.setATNR(javaMI.mvxGetField("ATNR"));
                ovb.setRORC(javaMI.mvxGetField("RORC"));
                ovb.setRORN(javaMI.mvxGetField("RORN"));
                ovb.setRORL(javaMI.mvxGetField("RORL"));
                ovb.setRORX(javaMI.mvxGetField("RORX"));
                ovb.setPRIP(javaMI.mvxGetField("PRIP"));
                ovb.setIRCV(javaMI.mvxGetField("IRCV"));
                ovb.setPROD(javaMI.mvxGetField("PROD"));
                ovb.setSDPC(javaMI.mvxGetField("SDPC"));
                ovb.setEXEP(javaMI.mvxGetField("EXEP"));
                ovb.setINEP(javaMI.mvxGetField("INEP"));
                ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                ovb.setUPAV(javaMI.mvxGetField("UPAV"));
                ovb.setACRF(javaMI.mvxGetField("ACRF"));
                MMS200MI mms200mi = new MMS200MI();
                mms200mi.connect();

                String itemtst = javaMI.mvxGetField("ITNO");

                if (itemtst.length() > 4) {
                    itemtst = itemtst.substring(0, 4);
                } else {
                    itemtst = itemtst.substring(0, itemtst.length());
                }

                MMS200MIGetStyleBasicBean mms200miGetStyleBasicBean = mms200mi.GetStyleItmBasicViewDetail(itemtst);
                mms200mi.destroyMI();
                mms200mi = null;
                String val = "YES";
                if (mms200miGetStyleBasicBean != null && (mms200miGetStyleBasicBean.getPRGP().equals("GMT") || mms200miGetStyleBasicBean.getPRGP().equals("HME"))) {
                    PCZ300MI pcz300mi = new PCZ300MI();
                    pcz300mi.connect();
                    ovb.setPRGP(mms200miGetStyleBasicBean.getPRGP());
                    List<PCZ300MIListCostInfoBean> beans = pcz300mi.ListCostInfo(itemtst);
                    if (beans != null && beans.size() > 0) {
                        for (PCZ300MIListCostInfoBean bean : beans) {
                            if (bean.getCCNF() != null && bean.getCCNF().trim().equals("0")) {
                                val = "NO";
                            }
                        }
                    } else {
                        val = "NO";
                    }
                    pcz300mi.destroyMI();
                    pcz300mi = null;
                }
                ovb.setTEMP(val);
                list1.add(ovb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<PPS200MILstLineTxtBean> getLstLineTxtPo(String PUNO, String PNLI, String PNLS) {
        List<PPS200MILstLineTxtBean> list1 = new ArrayList<PPS200MILstLineTxtBean>();

        PPS200MILstLineTxtBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);

        recFlag = javaMI.mvxAccess("LstLineTxt");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLineTxt]- " + javaMI.mvxGetLastError());

        } else {
            while (javaMI.mvxMore()) {
                ovb = new PPS200MILstLineTxtBean();
                ovb.setCONO(javaMI.mvxGetField("CONO"));
                ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                ovb.setLNCD(javaMI.mvxGetField("LNCD"));
                ovb.setTXID(javaMI.mvxGetField("TXID"));
                ovb.setTXEI(javaMI.mvxGetField("TXEI"));
                ovb.setTYTR(javaMI.mvxGetField("TYTR"));
                ovb.setTXVR(javaMI.mvxGetField("TXVR"));
                ovb.setLINO(javaMI.mvxGetField("LINO"));
                ovb.setTX60(javaMI.mvxGetField("TX60"));



                list1.add(ovb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public PPS200MILstLineTxtBean getLstLineTxtPo1(String PUNO, String PNLI, String PNLS) {


        PPS200MILstLineTxtBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);
        String temptx60 = "";
        recFlag = javaMI.mvxAccess("LstLineTxt");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLineTxt]- " + javaMI.mvxGetLastError());

        } else {
            while (javaMI.mvxMore()) {

                temptx60 = temptx60 + " " + javaMI.mvxGetField("TX60");

                javaMI.mvxAccess(null);
            }
        }
        ovb = new PPS200MILstLineTxtBean(temptx60);

        return ovb;
    }

    public List<PPS200MILstLineTxtBean> getLstLineTxtPoNew(String PUNO, String PNLI, String PNLS) {


        List<PPS200MILstLineTxtBean> list = new ArrayList<PPS200MILstLineTxtBean>();
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);
        String temptx60 = "";
        recFlag = javaMI.mvxAccess("LstLineTxt");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLineTxt]- " + javaMI.mvxGetLastError());

        } else {
            while (javaMI.mvxMore()) {
                if (javaMI.mvxGetField("TX60") != null && javaMI.mvxGetField("TX60").trim().length() > 0) {
                    PPS200MILstLineTxtBean ovb = new PPS200MILstLineTxtBean(javaMI.mvxGetField("TX60"));
                    list.add(ovb);
                }

                javaMI.mvxAccess(null);
            }
        }


        return list;
    }

    public List<PPS200MILstPoLineChargeBean> getLstPoLineChargePo(String PUNO, String PNLI, String PNLS) {
        List<PPS200MILstPoLineChargeBean> list1 = new ArrayList<PPS200MILstPoLineChargeBean>();

        PPS200MILstPoLineChargeBean ovb = null;
        int recFlag1;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);

        recFlag1 = javaMI.mvxAccess("LstPoLineCharge");
        if (recFlag1 > 0) {
            System.out.println("PPS200MI[LstPoLineCharge]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {

                if (javaMI.mvxGetField("EXTY") != null && Double.parseDouble(javaMI.mvxGetField("EXTY").trim()) == 2
                        && javaMI.mvxGetField("CEVA") != null && Double.parseDouble(javaMI.mvxGetField("CEVA").trim()) > 0
                        && javaMI.mvxGetField("THPR") != null && Double.parseDouble(javaMI.mvxGetField("THPR").trim()) == 0) {

                    ovb = new PPS200MILstPoLineChargeBean();
                    ovb.setCONO(javaMI.mvxGetField("CONO"));
                    ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                    ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                    ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                    ovb.setCDSE(javaMI.mvxGetField("CDSE"));
                    ovb.setREPN(javaMI.mvxGetField("REPN"));
                    ovb.setBANO(javaMI.mvxGetField("BANO"));
                    ovb.setEXTY(javaMI.mvxGetField("EXTY"));
                    ovb.setCEID(javaMI.mvxGetField("CEID"));
                    ovb.setEXIC(javaMI.mvxGetField("EXIC"));
                    ovb.setWSOP(javaMI.mvxGetField("WSOP"));
                    ovb.setCUCD(javaMI.mvxGetField("CUCD"));
                    ovb.setCEVA(javaMI.mvxGetField("CEVA"));
                    ovb.setCEVJ(javaMI.mvxGetField("CEVJ"));
                    ovb.setACIN(javaMI.mvxGetField("ACIN"));
                    ovb.setIVCQ(javaMI.mvxGetField("IVCQ"));
                    ovb.setTHPR(javaMI.mvxGetField("THPR"));
                    ovb.setPOOV(javaMI.mvxGetField("POOV"));
                    ovb.setOVHE(javaMI.mvxGetField("OVHE"));


                    list1.add(ovb);
                }
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<PPS200MILstPoLineChargeBean> getLstPoLineChargePoNew(String PUNO, String PNLI, String PNLS) {
        List<PPS200MILstPoLineChargeBean> list1 = new ArrayList<PPS200MILstPoLineChargeBean>();

        PPS200MILstPoLineChargeBean ovb = null;
        int recFlag1;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);

        recFlag1 = javaMI.mvxAccess("LstPoLineCharge");
        if (recFlag1 > 0) {
            System.out.println("PPS200MI[LstPoLineCharge]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {



                ovb = new PPS200MILstPoLineChargeBean();
                ovb.setCONO(javaMI.mvxGetField("CONO"));
                ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                ovb.setCDSE(javaMI.mvxGetField("CDSE"));
                ovb.setREPN(javaMI.mvxGetField("REPN"));
                ovb.setBANO(javaMI.mvxGetField("BANO"));
                ovb.setEXTY(javaMI.mvxGetField("EXTY"));
                ovb.setCEID(javaMI.mvxGetField("CEID"));
                ovb.setEXIC(javaMI.mvxGetField("EXIC"));
                ovb.setWSOP(javaMI.mvxGetField("WSOP"));
                ovb.setCUCD(javaMI.mvxGetField("CUCD"));
                ovb.setCEVA(javaMI.mvxGetField("CEVA"));
                ovb.setCEVJ(javaMI.mvxGetField("CEVJ"));
                ovb.setACIN(javaMI.mvxGetField("ACIN"));
                ovb.setIVCQ(javaMI.mvxGetField("IVCQ"));
                ovb.setTHPR(javaMI.mvxGetField("THPR"));
                ovb.setPOOV(javaMI.mvxGetField("POOV"));
                ovb.setOVHE(javaMI.mvxGetField("OVHE"));


                list1.add(ovb);

                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public String getLstYpheadDatapo(String DIVI, String PUNO, String SFAP, String APPR) {
        String bean = null;
        int recFlag;
        String identity = "LstYpheadData";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("SFAP", SFAP);
        javaMI.mvxSetField("APPR", APPR);
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("LstYpheadData");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstYpheadData]- " + javaMI.mvxGetLastError());
        } else {

            bean = javaMI.mvxGetField("APPR");
        }

        return bean;
    }

    public List<LstLineTransBean> getLstLineTrans(String PUNO, String PNLI, String PNLS) {
        List<LstLineTransBean> list1 = new ArrayList<LstLineTransBean>();
        LstLineTransBean ovb = null;
        int recFlag1;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);
        javaMI.mvxSetField("PRTR", "4");
        recFlag1 = javaMI.mvxAccess("LstLineTrans");
        if (recFlag1 > 0) {
            System.out.println("PPS200MI[LstLineTrans]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                ovb = new LstLineTransBean();
                ovb.setCONO(javaMI.mvxGetField("CONO"));
                ovb.setFACI(javaMI.mvxGetField("FACI"));
                ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                ovb.setPNLX(javaMI.mvxGetField("PNLX"));
                ovb.setREPN(javaMI.mvxGetField("REPN"));
                ovb.setWHLO(javaMI.mvxGetField("WHLO"));
                ovb.setWHSL(javaMI.mvxGetField("WHSL"));
                ovb.setBANO(javaMI.mvxGetField("BANO"));
                ovb.setCAMU(javaMI.mvxGetField("CAMU"));
                ovb.setSUDO(javaMI.mvxGetField("SUDO"));
                ovb.setPUOS(javaMI.mvxGetField("PUOS"));
                ovb.setNEAC(javaMI.mvxGetField("NEAC"));
                ovb.setUGCD(javaMI.mvxGetField("UGCD"));
                ovb.setREOR(javaMI.mvxGetField("REOR"));
                ovb.setCHPO(javaMI.mvxGetField("CHPO"));
                ovb.setRPQA(javaMI.mvxGetField("RPQA"));
                ovb.setRPQT(javaMI.mvxGetField("RPQT"));
                ovb.setTRDT(javaMI.mvxGetField("TRDT"));
                ovb.setPLDT(javaMI.mvxGetField("PLDT"));
                ovb.setRENE(javaMI.mvxGetField("RENE"));
                ovb.setRESP(javaMI.mvxGetField("RESP"));
                ovb.setBOLN(javaMI.mvxGetField("BOLN"));
                ovb.setCARN(javaMI.mvxGetField("CARN"));
                ovb.setSMDT(javaMI.mvxGetField("SMDT"));
                ovb.setARDT(javaMI.mvxGetField("ARDT"));
                ovb.setNOPK(javaMI.mvxGetField("NOPK"));
                ovb.setFWRF(javaMI.mvxGetField("FWRF"));
                ovb.setOEND(javaMI.mvxGetField("OEND"));
                ovb.setSCOP(javaMI.mvxGetField("SCOP"));
                ovb.setSERA(javaMI.mvxGetField("SERA"));
                ovb.setDTID(javaMI.mvxGetField("DTID"));
                ovb.setTIHM(javaMI.mvxGetField("TIHM"));
                ovb.setDLIX(javaMI.mvxGetField("DLIX"));
                ovb.setAGNB(javaMI.mvxGetField("AGNB"));
                ovb.setRGDT(javaMI.mvxGetField("RGDT"));
                ovb.setRGTM(javaMI.mvxGetField("RGTM"));
                list1.add(ovb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<PPS200MILstPoLineChargeBean> getLstPoChargePo(String PUNO) {
        List<PPS200MILstPoLineChargeBean> list1 = new ArrayList<PPS200MILstPoLineChargeBean>();

        PPS200MILstPoLineChargeBean ovb = null;
        int recFlag1;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);


        recFlag1 = javaMI.mvxAccess("LstPoLineCharge");
        if (recFlag1 > 0) {
            System.out.println("PPS200MI[LstPoLineCharge]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {

                if (javaMI.mvxGetField("EXTY") != null && Double.parseDouble(javaMI.mvxGetField("EXTY").trim()) == 2
                        && javaMI.mvxGetField("CEVA") != null && Double.parseDouble(javaMI.mvxGetField("CEVA").trim()) > 0
                        && javaMI.mvxGetField("THPR") != null && Double.parseDouble(javaMI.mvxGetField("THPR").trim()) == 0
                        && javaMI.mvxGetField("PNLS") != null && javaMI.mvxGetField("PNLS").trim().length() > 0 && Integer.parseInt(javaMI.mvxGetField("PNLS").trim()) < 500) {

                    ovb = new PPS200MILstPoLineChargeBean();
                    ovb.setCONO(javaMI.mvxGetField("CONO"));
                    ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                    ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                    ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                    ovb.setCDSE(javaMI.mvxGetField("CDSE"));
                    ovb.setREPN(javaMI.mvxGetField("REPN"));
                    ovb.setBANO(javaMI.mvxGetField("BANO"));
                    ovb.setEXTY(javaMI.mvxGetField("EXTY"));
                    ovb.setCEID(javaMI.mvxGetField("CEID"));
                    ovb.setEXIC(javaMI.mvxGetField("EXIC"));
                    ovb.setWSOP(javaMI.mvxGetField("WSOP"));
                    ovb.setCUCD(javaMI.mvxGetField("CUCD"));
                    ovb.setCEVA(javaMI.mvxGetField("CEVA"));
                    ovb.setCEVJ(javaMI.mvxGetField("CEVJ"));
                    ovb.setACIN(javaMI.mvxGetField("ACIN"));
                    ovb.setIVCQ(javaMI.mvxGetField("IVCQ"));
                    ovb.setTHPR(javaMI.mvxGetField("THPR"));
                    ovb.setPOOV(javaMI.mvxGetField("POOV"));
                    ovb.setOVHE(javaMI.mvxGetField("OVHE"));


                    list1.add(ovb);


                }
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public String[] GetLineListNew(String PUNO, String PNLI, String PNLS) {
        int recFlag;
        String identity = "GetLineList";
        String par[] = new String[10];

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);

        recFlag = javaMI.mvxAccess("GetLine");

        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
            par[3] = javaMI.mvxGetLastError();

        } else {
            par[0] = javaMI.mvxGetField("SUNO");
            par[1] = javaMI.mvxGetField("ITNO");
            par[2] = javaMI.mvxGetField("PITD");
            par[3] = javaMI.mvxGetField("PUPR");
            par[4] = javaMI.mvxGetField("ORTY");
            par[5] = javaMI.mvxGetField("FACI");
            par[6] = javaMI.mvxGetField("RORN");
            par[7] = javaMI.mvxGetField("GRMT");
            par[8] = javaMI.mvxGetField("PUSL");
            par[9] = javaMI.mvxGetField("PUUN");


        }
        return par;
    }

    public List<PPS200MILstLineBean> getLstLinePoNewPOclose(String PUNO, String itno) {
        List<PPS200MILstLineBean> list1 = new ArrayList<PPS200MILstLineBean>();

        PPS200MILstLineBean ovb = null;
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("PUNO", PUNO);

        recFlag = javaMI.mvxAccess("LstLine");
        if (recFlag > 0) {
            System.out.println("PPS200MI[LstLine]- " + javaMI.mvxGetLastError());
        } else {
            while (javaMI.mvxMore()) {
                int temppnls = 0;

                ovb = new PPS200MILstLineBean();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                String DWDT = "";
                if (javaMI.mvxGetField("DWDT") != null) {
                    Date dt;
                    try {
                        dt = formatter.parse(javaMI.mvxGetField("DWDT"));
                        DWDT = formatter1.format(dt);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                //System.out.println(javaMI.mvxGetField("ITNO"));
                if (javaMI.mvxGetField("ITNO").startsWith(itno) && Integer.parseInt(javaMI.mvxGetField("PUSL")) < 75) {
                    ovb.setCONO(javaMI.mvxGetField("CONO"));
                    ovb.setPUNO(javaMI.mvxGetField("PUNO"));
                    ovb.setPNLI(javaMI.mvxGetField("PNLI"));
                    ovb.setPNLS(javaMI.mvxGetField("PNLS"));
                    ovb.setITNO(javaMI.mvxGetField("ITNO"));
                    ovb.setSITE(javaMI.mvxGetField("SITE"));
                    ovb.setCODT(javaMI.mvxGetField("CODT"));
                    ovb.setCFQA(javaMI.mvxGetField("CFQA"));
                    ovb.setPUUN(javaMI.mvxGetField("PUUN"));
                    ovb.setITDS(javaMI.mvxGetField("ITDS"));
                    ovb.setEACD(javaMI.mvxGetField("EACD"));
                    ovb.setFACI(javaMI.mvxGetField("FACI"));
                    ovb.setWHLO(javaMI.mvxGetField("WHLO"));
                    ovb.setPOTC(javaMI.mvxGetField("POTC"));
                    ovb.setPUST(javaMI.mvxGetField("PUST"));
                    ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                    ovb.setSUNO(javaMI.mvxGetField("SUNO"));
                    ovb.setPRCS(javaMI.mvxGetField("PRCS"));
                    ovb.setSUFI(javaMI.mvxGetField("SUFI"));
                    ovb.setPITD(javaMI.mvxGetField("PITD"));
                    ovb.setPITT(javaMI.mvxGetField("PITT"));
                    ovb.setSORN(javaMI.mvxGetField("SORN"));
                    ovb.setPUPR(javaMI.mvxGetField("PUPR"));
                    ovb.setODI1(javaMI.mvxGetField("ODI1"));
                    ovb.setODI2(javaMI.mvxGetField("ODI2"));
                    ovb.setODI3(javaMI.mvxGetField("ODI3"));
                    ovb.setCPPR(javaMI.mvxGetField("CPPR"));
                    ovb.setCFD1(javaMI.mvxGetField("CFD1"));
                    ovb.setCFD2(javaMI.mvxGetField("CFD2"));
                    ovb.setCFD3(javaMI.mvxGetField("CFD3"));
                    ovb.setPPUN(javaMI.mvxGetField("PPUN"));
                    ovb.setPUCD(javaMI.mvxGetField("PUCD"));
                    ovb.setCPUC(javaMI.mvxGetField("CPUC"));
                    ovb.setLNAM(javaMI.mvxGetField("LNAM"));
                    ovb.setPTCD(javaMI.mvxGetField("PTCD"));
                    ovb.setDWDT(DWDT);
                    ovb.setORQA(javaMI.mvxGetField("ORQA"));
                    ovb.setADQA(javaMI.mvxGetField("ADQA"));
                    ovb.setTNQA(javaMI.mvxGetField("TNQA"));
                    ovb.setRVQA(javaMI.mvxGetField("RVQA"));
                    ovb.setRCDT(javaMI.mvxGetField("RCDT"));
                    ovb.setCAQA(javaMI.mvxGetField("CAQA"));
                    ovb.setRJQA(javaMI.mvxGetField("RJQA"));
                    ovb.setSDQA(javaMI.mvxGetField("SDQA"));
                    ovb.setIVQA(javaMI.mvxGetField("IVQA"));
                    ovb.setIDAT(javaMI.mvxGetField("IDAT"));
                    ovb.setPLPN(javaMI.mvxGetField("PLPN"));
                    ovb.setPLPS(javaMI.mvxGetField("PLPS"));
                    ovb.setPURC(javaMI.mvxGetField("PURC"));
                    ovb.setBUYE(javaMI.mvxGetField("BUYE"));
                    ovb.setGRMT(javaMI.mvxGetField("GRMT"));
                    ovb.setPACT(javaMI.mvxGetField("PACT"));
                    ovb.setTXID(javaMI.mvxGetField("TXID"));
                    ovb.setECVE(javaMI.mvxGetField("ECVE"));
                    ovb.setOURR(javaMI.mvxGetField("OURR"));
                    ovb.setOURT(javaMI.mvxGetField("OURT"));
                    ovb.setVTCD(javaMI.mvxGetField("VTCD"));
                    ovb.setATNR(javaMI.mvxGetField("ATNR"));
                    ovb.setRORC(javaMI.mvxGetField("RORC"));
                    ovb.setRORN(javaMI.mvxGetField("RORN"));
                    ovb.setRORL(javaMI.mvxGetField("RORL"));
                    ovb.setRORX(javaMI.mvxGetField("RORX"));
                    ovb.setPRIP(javaMI.mvxGetField("PRIP"));
                    ovb.setIRCV(javaMI.mvxGetField("IRCV"));
                    ovb.setPROD(javaMI.mvxGetField("PROD"));
                    ovb.setSDPC(javaMI.mvxGetField("SDPC"));
                    ovb.setEXEP(javaMI.mvxGetField("EXEP"));
                    ovb.setINEP(javaMI.mvxGetField("INEP"));
                    ovb.setPUSL(javaMI.mvxGetField("PUSL"));
                    ovb.setUPAV(javaMI.mvxGetField("UPAV"));
                    ovb.setACRF(javaMI.mvxGetField("ACRF"));
                    MMS200MI mms200mi = new MMS200MI();
                    mms200mi.connect();
                    String itemtst = javaMI.mvxGetField("ITNO");
                    if (itemtst.length() > 4) {
                        itemtst = itemtst.substring(0, 4);
                    } else {
                        itemtst = itemtst.substring(0, itemtst.length());
                    }

                    MMS200MIGetStyleBasicBean mms200miGetStyleBasicBean = mms200mi.GetStyleItmBasicViewDetail(itemtst);
                    mms200mi.destroyMI();
                    mms200mi = null;
                    String val = "YES";
                    if (mms200miGetStyleBasicBean != null && (mms200miGetStyleBasicBean.getPRGP().equals("GMT") || mms200miGetStyleBasicBean.getPRGP().equals("HME"))) {
                        PCZ300MI pcz300mi = new PCZ300MI();
                        pcz300mi.connect();
                        List<PCZ300MIListCostInfoBean> beans = pcz300mi.ListCostInfo(itemtst);
                        if (beans != null && beans.size() > 0) {
                            for (PCZ300MIListCostInfoBean bean : beans) {
                                if (bean.getCCNF() != null && bean.getCCNF().trim().equals("0")) {
                                    val = "NO";
                                }
                            }
                        } else {
                            val = "NO";
                        }
                        pcz300mi.destroyMI();
                        pcz300mi = null;
                    }
                    ovb.setTEMP(val);
                    list1.add(ovb);
                }
                javaMI.mvxAccess(null);

            }
        }
        return list1;
    }
}
