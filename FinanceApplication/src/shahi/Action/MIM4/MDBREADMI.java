/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MIM4;

import shahi.Action.MI.Beans.MDBREADMIGetCGEOEX00Bean;
import shahi.Action.MI.Beans.MDBREADMIGetCIDVEN00Bean;
import shahi.Action.MI.Beans.MDBREADMIGetCSYSTS00Bean;
import shahi.Action.MI.Beans.MDBREADMIGetGetMPHEADBean;
import shahi.Action.MI.Beans.MDBREADMIGetMGDADRBean;
import shahi.Action.MI.Beans.MDBREADMIGetMGHEADBean;
import shahi.Action.MI.Beans.MDBREADMIGetMILOMA00Bean;
import shahi.Action.MI.Beans.MDBREADMIGetMITFAC00Bean;
import shahi.Action.MI.Beans.MDBREADMIGetMPLINEBean;
import shahi.Action.MI.Beans.MDBREADMIGetXINADRBean;
import shahi.Action.MI.Beans.MDBREADMIGetXINDDRBean;
import shahi.Action.MI.Beans.MDBREADMIGettMPORDTBean;
import shahi.Action.MI.Beans.MDBREADMILstCGEORTBean;
import shahi.Action.MI.Beans.MDBREADMILstMPLINEBean;
import shahi.Action.MI.Beans.MDBREADMILstXINADRBean;
import shahi.Action.MI.Beans.MDREADMILstMPDOPEBean;
import java.util.ArrayList;
import java.util.List;
import shahi.Action.MI.Beans.MDBREADMIGetCSYTAB20Bean;

/**
 *
 * @author Vivek
 */
public class MDBREADMI extends BaseMI {

    public MDBREADMI() {
        setProgram("MDBREADMI");
    }

    public int SetLstMaxRec() {
        int recFlag = 0;
        javaMI.mvxClearFields();
        recFlag = javaMI.mvxAccess("SetLstMaxRec   0");
        return recFlag;
    }

    public MDBREADMIGetMPLINEBean GetMPLINE00(String PUNO, String PNLI, String PNLS) {
        MDBREADMIGetMPLINEBean bean = new MDBREADMIGetMPLINEBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetMPLINE00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);
        recFlag = javaMI.mvxAccess("GetMPLINE00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            bean.setFACI(javaMI.mvxGetField("FACI"));
            bean.setWHLO(javaMI.mvxGetField("WHLO"));
            bean.setPUNO(javaMI.mvxGetField("PUNO"));
            bean.setPNLI(javaMI.mvxGetField("PNLI"));
            bean.setPNLS(javaMI.mvxGetField("PNLS"));
            bean.setORTY(javaMI.mvxGetField("ORTY"));
            bean.setPOTC(javaMI.mvxGetField("POTC"));
            bean.setPUST(javaMI.mvxGetField("PUST"));
            bean.setPUSL(javaMI.mvxGetField("PUSL"));
            bean.setSUNO(javaMI.mvxGetField("SUNO"));
            bean.setITNO(javaMI.mvxGetField("ITNO"));
            bean.setSUFI(javaMI.mvxGetField("SUFI"));
            bean.setSITE(javaMI.mvxGetField("SITE"));
            bean.setPITD(javaMI.mvxGetField("PITD"));
            bean.setPITT(javaMI.mvxGetField("PITT"));
            bean.setPUPR(javaMI.mvxGetField("PUPR"));
            bean.setODI1(javaMI.mvxGetField("ODI1"));
            bean.setPUUN(javaMI.mvxGetField("PUUN"));
            bean.setPPUN(javaMI.mvxGetField("PPUN"));
            bean.setPUCD(javaMI.mvxGetField("PUCD"));
            bean.setLNAM(javaMI.mvxGetField("LNAM"));
            bean.setDWDT(javaMI.mvxGetField("DWDT"));
            bean.setORQA(javaMI.mvxGetField("ORQA"));
            bean.setCFQA(javaMI.mvxGetField("CFQA"));
            bean.setADQA(javaMI.mvxGetField("ADQA"));
            bean.setTNQA(javaMI.mvxGetField("TNQA"));
            bean.setRVQA(javaMI.mvxGetField("RVQA"));
            bean.setCAQA(javaMI.mvxGetField("CAQA"));
            bean.setRJQA(javaMI.mvxGetField("RJQA"));
            bean.setSDQA(javaMI.mvxGetField("SDQA"));
            bean.setIVQA(javaMI.mvxGetField("IVQA"));
            bean.setRORC(javaMI.mvxGetField("RORC"));
            bean.setRORN(javaMI.mvxGetField("RORN"));
            bean.setRORL(javaMI.mvxGetField("RORL"));
            bean.setOURR(javaMI.mvxGetField("OURR"));
            bean.setUPAV(javaMI.mvxGetField("UPAV"));
            bean.setPLPN(javaMI.mvxGetField("PLPN"));
            bean.setPURC(javaMI.mvxGetField("PURC"));
            bean.setBUYE(javaMI.mvxGetField("BUYE"));
            bean.setGRMT(javaMI.mvxGetField("GRMT"));
            bean.setEXEP(javaMI.mvxGetField("EXEP"));
            bean.setINEP(javaMI.mvxGetField("INEP"));
            bean.setACRF(javaMI.mvxGetField("ACRF"));
            bean.setCOCE(javaMI.mvxGetField("COCE"));
            bean.setPROJ(javaMI.mvxGetField("PROJ"));
            bean.setMODL(javaMI.mvxGetField("MODL"));
            bean.setTEDL(javaMI.mvxGetField("TEDL"));
            bean.setGEOC(javaMI.mvxGetField("GEOC"));
        }
        return bean;
    }

    public List<MDBREADMILstMPLINEBean> LstMPLINE00(String PUNO, String PNLI, String PNLS) {
        List<MDBREADMILstMPLINEBean> mdbreadmilmplinebeans = new ArrayList<MDBREADMILstMPLINEBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstMPLINE00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("PUNO", PUNO);
        javaMI.mvxSetField("PNLI", PNLI);
        javaMI.mvxSetField("PNLS", PNLS);
        recFlag = javaMI.mvxAccess("LstMPLINE00");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                MDBREADMILstMPLINEBean mdbreadmilmplinebean = new MDBREADMILstMPLINEBean();
                mdbreadmilmplinebean.setFACI(javaMI.mvxGetField("FACI"));
                mdbreadmilmplinebean.setWHLO(javaMI.mvxGetField("WHLO"));
                mdbreadmilmplinebean.setPUNO(javaMI.mvxGetField("PUNO"));
                mdbreadmilmplinebean.setPNLI(javaMI.mvxGetField("PNLI"));
                mdbreadmilmplinebean.setPNLS(javaMI.mvxGetField("PNLS"));
                mdbreadmilmplinebean.setPOTC(javaMI.mvxGetField("POTC"));
                mdbreadmilmplinebean.setPUST(javaMI.mvxGetField("PUST"));
                mdbreadmilmplinebean.setPUSL(javaMI.mvxGetField("PUSL"));
                mdbreadmilmplinebean.setSUNO(javaMI.mvxGetField("SUNO"));
                mdbreadmilmplinebean.setITNO(javaMI.mvxGetField("ITNO"));
                mdbreadmilmplinebean.setSUFI(javaMI.mvxGetField("SUFI"));
                mdbreadmilmplinebean.setSITE(javaMI.mvxGetField("SITE"));
                mdbreadmilmplinebean.setPITD(javaMI.mvxGetField("PITD"));
                mdbreadmilmplinebean.setPITT(javaMI.mvxGetField("PITT"));
                mdbreadmilmplinebean.setPUPR(javaMI.mvxGetField("PUPR"));
                mdbreadmilmplinebean.setODI1(javaMI.mvxGetField("ODI1"));
                mdbreadmilmplinebean.setLNAM(javaMI.mvxGetField("LNAM"));
                mdbreadmilmplinebean.setDWDT(javaMI.mvxGetField("DWDT"));
                mdbreadmilmplinebean.setORQA(javaMI.mvxGetField("ORQA"));
                mdbreadmilmplinebean.setCFQA(javaMI.mvxGetField("CFQA"));
                mdbreadmilmplinebean.setADQA(javaMI.mvxGetField("ADQA"));
                mdbreadmilmplinebean.setTNQA(javaMI.mvxGetField("TNQA"));
                mdbreadmilmplinebean.setRVQA(javaMI.mvxGetField("RVQA"));
                mdbreadmilmplinebean.setCAQA(javaMI.mvxGetField("CAQA"));
                mdbreadmilmplinebean.setRJQA(javaMI.mvxGetField("RJQA"));
                mdbreadmilmplinebean.setSDQA(javaMI.mvxGetField("SDQA"));
                mdbreadmilmplinebean.setIVQA(javaMI.mvxGetField("IVQA"));
                mdbreadmilmplinebean.setRORC(javaMI.mvxGetField("RORC"));
                mdbreadmilmplinebean.setRORN(javaMI.mvxGetField("RORN"));
                mdbreadmilmplinebean.setRORL(javaMI.mvxGetField("RORL"));
                mdbreadmilmplinebean.setOURR(javaMI.mvxGetField("OURR"));
                mdbreadmilmplinebean.setUPAV(javaMI.mvxGetField("UPAV"));
                mdbreadmilmplinebean.setPLPN(javaMI.mvxGetField("PLPN"));
                mdbreadmilmplinebean.setPLPS(javaMI.mvxGetField("PLPS"));
                mdbreadmilmplinebean.setPURC(javaMI.mvxGetField("PURC"));
                mdbreadmilmplinebean.setBUYE(javaMI.mvxGetField("BUYE"));
                mdbreadmilmplinebean.setGRMT(javaMI.mvxGetField("GRMT"));
                mdbreadmilmplinebean.setEXEP(javaMI.mvxGetField("EXEP"));
                mdbreadmilmplinebean.setINEP(javaMI.mvxGetField("INEP"));
                mdbreadmilmplinebean.setACRF(javaMI.mvxGetField("ACRF"));
                mdbreadmilmplinebean.setCOCE(javaMI.mvxGetField("COCE"));
                mdbreadmilmplinebean.setPROJ(javaMI.mvxGetField("PROJ"));
                mdbreadmilmplinebean.setORTY(javaMI.mvxGetField("ORTY"));
                mdbreadmilmplinebean.setGEOC(javaMI.mvxGetField("GEOC"));
                mdbreadmilmplinebean.setPUCD(javaMI.mvxGetField("PUCD"));
                mdbreadmilmplinebean.setPUUN(javaMI.mvxGetField("PUUN"));
                mdbreadmilmplinebean.setPPUN(javaMI.mvxGetField("PPUN"));
                mdbreadmilmplinebean.setMODL(javaMI.mvxGetField("MODL"));
                mdbreadmilmplinebean.setTEDL(javaMI.mvxGetField("TEDL"));

                mdbreadmilmplinebeans.add(mdbreadmilmplinebean);
                javaMI.mvxAccess(null);
            }
        }
        return mdbreadmilmplinebeans;
    }

    public MDBREADMIGetGetMPHEADBean GetMPHEAD00(String PUNO) {
        MDBREADMIGetGetMPHEADBean bean = new MDBREADMIGetGetMPHEADBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetMPHEAD00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("PUNO", PUNO);
        recFlag = javaMI.mvxAccess("GetMPHEAD00");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            bean.setDIVI(javaMI.mvxGetField("DIVI"));
            bean.setFACI(javaMI.mvxGetField("FACI"));
            bean.setWHLO(javaMI.mvxGetField("WHLO"));
            bean.setPUNO(javaMI.mvxGetField("PUNO"));
            bean.setORTY(javaMI.mvxGetField("ORTY"));
            bean.setPUSL(javaMI.mvxGetField("PUSL"));
            bean.setPUST(javaMI.mvxGetField("PUST"));
            bean.setPUDT(javaMI.mvxGetField("PUDT"));
            bean.setSUNO(javaMI.mvxGetField("SUNO"));
            bean.setCUCD(javaMI.mvxGetField("CUCD"));
            bean.setTEPY(javaMI.mvxGetField("TEPY"));
            bean.setPYME(javaMI.mvxGetField("PYME"));
            bean.setMODL(javaMI.mvxGetField("MODL"));
            bean.setTEDL(javaMI.mvxGetField("TEDL"));
            bean.setTEAF(javaMI.mvxGetField("TEAF"));
            bean.setTEPA(javaMI.mvxGetField("TEPA"));
            bean.setYRE1(javaMI.mvxGetField("YRE1"));
            bean.setOURR(javaMI.mvxGetField("OURR"));
            bean.setOURT(javaMI.mvxGetField("OURT"));
            bean.setTEOR(javaMI.mvxGetField("TEOR"));
            bean.setPROJ(javaMI.mvxGetField("PROJ"));
            bean.setBUYE(javaMI.mvxGetField("BUYE"));
            bean.setPURC(javaMI.mvxGetField("PURC"));
            bean.setHAFE(javaMI.mvxGetField("HAFE"));
        }
        return bean;
    }

    public MDBREADMIGetXINADRBean GetXINADR00(String SUNO, String ADTE, String ADID, String STDT) {
        MDBREADMIGetXINADRBean bean = new MDBREADMIGetXINADRBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetXINADR00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("ADTE", ADTE);
        javaMI.mvxSetField("ADID", ADID);
        javaMI.mvxSetField("STDT", STDT);
        recFlag = javaMI.mvxAccess("GetXINADR00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            bean.setSUNO(javaMI.mvxGetField("SUNO"));
            bean.setADTE(javaMI.mvxGetField("ADTE"));
            bean.setADID(javaMI.mvxGetField("ADID"));
            bean.setSTDT(javaMI.mvxGetField("STDT"));
            bean.setXLSN(javaMI.mvxGetField("XLSN"));
            bean.setXCSN(javaMI.mvxGetField("XCSN"));
            bean.setXPLN(javaMI.mvxGetField("XPLN"));
            bean.setXLCN(javaMI.mvxGetField("XLCN"));
            bean.setSERS(javaMI.mvxGetField("SERS"));
            bean.setFRE2(javaMI.mvxGetField("FRE2"));
        }
        return bean;
    }

    public List<MDBREADMILstXINADRBean> LstXINADR00(String SUNO, String ADTE, String ADID, String STDT) {
        List<MDBREADMILstXINADRBean> beans = new ArrayList<MDBREADMILstXINADRBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstXINADR00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("ADTE", ADTE);
        javaMI.mvxSetField("ADID", ADID);
        javaMI.mvxSetField("STDT", STDT);
        recFlag = javaMI.mvxAccess("LstXINADR00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                MDBREADMILstXINADRBean bean = new MDBREADMILstXINADRBean();
                bean.setSUNO(javaMI.mvxGetField("SUNO"));
                bean.setADTE(javaMI.mvxGetField("ADTE"));
                bean.setADID(javaMI.mvxGetField("ADID"));
                bean.setSTDT(javaMI.mvxGetField("STDT"));
                bean.setXLSN(javaMI.mvxGetField("XLSN"));
                bean.setXCSN(javaMI.mvxGetField("XCSN"));
                bean.setXPLN(javaMI.mvxGetField("XPLN"));
                bean.setXLCN(javaMI.mvxGetField("XLCN"));
                bean.setSERS(javaMI.mvxGetField("SERS"));
                bean.setFRE2(javaMI.mvxGetField("FRE2"));
                beans.add(bean);
                javaMI.mvxAccess(null);
            }
        }
        return beans;
    }

    public MDBREADMIGetXINDDRBean GetXINDDR00(String ADTH, String ADK1, String ADK2, String ADK3) {
        MDBREADMIGetXINDDRBean bean = new MDBREADMIGetXINDDRBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetXINDDR00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("ADTH", ADTH);
        javaMI.mvxSetField("ADK1", ADK1);
        javaMI.mvxSetField("ADK2", ADK2);
        javaMI.mvxSetField("ADK3", ADK3);
        recFlag = javaMI.mvxAccess("GetXINDDR00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            bean.setADTH(javaMI.mvxGetField("ADTH"));
            bean.setADK1(javaMI.mvxGetField("ADK1"));
            bean.setADK2(javaMI.mvxGetField("ADK2"));
            bean.setADK3(javaMI.mvxGetField("ADK3"));
            bean.setXLSN(javaMI.mvxGetField("XLSN"));
            bean.setXCSN(javaMI.mvxGetField("XCSN"));
            bean.setXPLN(javaMI.mvxGetField("XPLN"));
            bean.setXLCN(javaMI.mvxGetField("XLCN"));
            bean.setFRE1(javaMI.mvxGetField("FRE1"));
            bean.setFRE2(javaMI.mvxGetField("FRE2"));

        }
        return bean;
    }

    public MDBREADMIGetMGHEADBean GetMGHEAD00(String TRNR) {
        MDBREADMIGetMGHEADBean mdbGetMGHEAD00Bean = new MDBREADMIGetMGHEADBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetMGHEAD00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("TRNR", TRNR);
        recFlag = javaMI.mvxAccess("GetMGHEAD00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbGetMGHEAD00Bean.setTRNR(javaMI.mvxGetField("TRNR"));   //Order no
            mdbGetMGHEAD00Bean.setTRTP(javaMI.mvxGetField("TRTP"));   //Order type
            mdbGetMGHEAD00Bean.setFACI(javaMI.mvxGetField("FACI"));   //Facility
            mdbGetMGHEAD00Bean.setRORC(javaMI.mvxGetField("RORC"));   //Ref order cat
            mdbGetMGHEAD00Bean.setRORN(javaMI.mvxGetField("RORN"));   //Ref order no
            mdbGetMGHEAD00Bean.setRORL(javaMI.mvxGetField("RORL"));   //Ref order line
            mdbGetMGHEAD00Bean.setRESP(javaMI.mvxGetField("RESP"));   //Responsible
            mdbGetMGHEAD00Bean.setTRSL(javaMI.mvxGetField("TRSL"));   //Lowest status
            mdbGetMGHEAD00Bean.setTRSH(javaMI.mvxGetField("TRSH"));   //Highest status
            mdbGetMGHEAD00Bean.setTRDT(javaMI.mvxGetField("TRDT"));   //Trans date
            mdbGetMGHEAD00Bean.setTRTM(javaMI.mvxGetField("TRTM"));   //Trans time
            mdbGetMGHEAD00Bean.setTWSL(javaMI.mvxGetField("TWSL"));   //Location
            mdbGetMGHEAD00Bean.setGSR1(javaMI.mvxGetField("GSR1"));   //Job reference 1
            mdbGetMGHEAD00Bean.setGSR2(javaMI.mvxGetField("GSR2"));   //Job reference 2
            mdbGetMGHEAD00Bean.setTOFP(javaMI.mvxGetField("TOFP"));   //Process type
            mdbGetMGHEAD00Bean.setOPNO(javaMI.mvxGetField("OPNO"));   //Operation no
            mdbGetMGHEAD00Bean.setTWLO(javaMI.mvxGetField("TWLO"));   //To warehouse
            mdbGetMGHEAD00Bean.setREMK(javaMI.mvxGetField("REMK"));   //Remark
            mdbGetMGHEAD00Bean.setDEPT(javaMI.mvxGetField("DEPT"));   //Department
            mdbGetMGHEAD00Bean.setWHLO(javaMI.mvxGetField("WHLO"));   //Warehouse
            mdbGetMGHEAD00Bean.setGRWE(javaMI.mvxGetField("GRWE"));   //Gross weight
            mdbGetMGHEAD00Bean.setNTAM(javaMI.mvxGetField("NTAM"));   //Net order value
            mdbGetMGHEAD00Bean.setIRCV(javaMI.mvxGetField("IRCV"));   //Recipient
        }
        return mdbGetMGHEAD00Bean;
    }

    public MDBREADMIGetMGDADRBean GetMGDADR00(String TRNR) {
        MDBREADMIGetMGDADRBean mdbGetMGDADR00Bean = new MDBREADMIGetMGDADRBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetMGDADR00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("TRNR", TRNR);
        recFlag = javaMI.mvxAccess("GetMGDADR00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbGetMGDADR00Bean.setTRNR(javaMI.mvxGetField("TRNR"));   //Order no
            mdbGetMGDADR00Bean.setADK1(javaMI.mvxGetField("ADK1"));   //Address key 1
            mdbGetMGDADR00Bean.setSUNO(javaMI.mvxGetField("SUNO"));   //Supplier
            mdbGetMGDADR00Bean.setADID(javaMI.mvxGetField("ADID"));   //Address no
            mdbGetMGDADR00Bean.setCONM(javaMI.mvxGetField("CONM"));   //Company name
            mdbGetMGDADR00Bean.setADR1(javaMI.mvxGetField("ADR1"));   //Address line 1
            mdbGetMGDADR00Bean.setADR2(javaMI.mvxGetField("ADR2"));   //Address 2
            mdbGetMGDADR00Bean.setADR3(javaMI.mvxGetField("ADR3"));   //Address 3
            mdbGetMGDADR00Bean.setADR4(javaMI.mvxGetField("ADR4"));   //Address 4
            mdbGetMGDADR00Bean.setPONO(javaMI.mvxGetField("PONO"));   //Postal code
            mdbGetMGDADR00Bean.setCSCD(javaMI.mvxGetField("CSCD"));   //Country
            mdbGetMGDADR00Bean.setOREF(javaMI.mvxGetField("OREF"));   //Our reference
            mdbGetMGDADR00Bean.setTOWN(javaMI.mvxGetField("TOWN"));   //City
            mdbGetMGDADR00Bean.setECAR(javaMI.mvxGetField("ECAR"));   //State
            mdbGetMGDADR00Bean.setRASN(javaMI.mvxGetField("RASN"));   //Rail station
            mdbGetMGDADR00Bean.setSPLE(javaMI.mvxGetField("SPLE"));   //SPLC code
        }
        return mdbGetMGDADR00Bean;
    }

    public MDREADMILstMPDOPEBean LstMPDOPE00(String FACI, String PRNO, String STRT, String OPNO) {
        MDREADMILstMPDOPEBean bean = new MDREADMILstMPDOPEBean();
        int recflag;
        String ERROR = null;
        String identity = "LstMPDOPE00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("PRNO", PRNO);
        javaMI.mvxSetField("STRT", STRT);
        javaMI.mvxSetField("OPNO", OPNO);
        recflag = javaMI.mvxAccess("LstMPDOPE00");
        if (recflag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                bean.setFACI(javaMI.mvxGetField("FACI"));                    //Facility
                bean.setFDAT(javaMI.mvxGetField("FDAT"));
                bean.setMSEQ(javaMI.mvxGetField("MSEQ"));
                bean.setOPDS(javaMI.mvxGetField("OPDS"));
                bean.setOPNO(javaMI.mvxGetField("OPNO"));                     //Operation No
                bean.setPIPR(javaMI.mvxGetField("PIPR"));
                bean.setPITI(javaMI.mvxGetField("PITI"));                     //RunTime
                bean.setPLGR(javaMI.mvxGetField("PLGR"));
                bean.setPRNO(javaMI.mvxGetField("PRNO"));
                bean.setSTRT(javaMI.mvxGetField("STRT"));
                bean.setTDAT(javaMI.mvxGetField("TDAT"));
                bean.setTXT1(javaMI.mvxGetField("TXT1"));                     //Efficiency
                bean.setTXT2(javaMI.mvxGetField("TXT2"));                     //Remarks
                bean.setWCRF(javaMI.mvxGetField("WCRF"));
                javaMI.mvxAccess(null);
            }
            //Time Reference
        }
        return bean;
    }

    public List<MDBREADMILstCGEORTBean> LstCGEORT00(String DIVI, String GEOC, String TAXT, String FRDT) {
        List<MDBREADMILstCGEORTBean> mdbreadmilmplinebeans = new ArrayList<MDBREADMILstCGEORTBean>();
        int recFlag;
        String ERROR = null;
        String identity = "LstCGEORT00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("GEOC", GEOC);
        javaMI.mvxSetField("TAXT", TAXT);
        javaMI.mvxSetField("FRDT", FRDT);
        recFlag = javaMI.mvxAccess("LstCGEORT00");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            while (javaMI.mvxMore()) {
                MDBREADMILstCGEORTBean mdbreadmiLstCGEORTBean = new MDBREADMILstCGEORTBean();
                mdbreadmiLstCGEORTBean.setDIVI(javaMI.mvxGetField("DIVI"));
                mdbreadmiLstCGEORTBean.setGEOC(javaMI.mvxGetField("GEOC"));
                mdbreadmiLstCGEORTBean.setTAXT(javaMI.mvxGetField("TAXT"));
                mdbreadmiLstCGEORTBean.setFRDT(javaMI.mvxGetField("FRDT"));
                mdbreadmiLstCGEORTBean.setSTAT(javaMI.mvxGetField("STAT"));
                mdbreadmiLstCGEORTBean.setTAR1(javaMI.mvxGetField("TAR1"));

                mdbreadmilmplinebeans.add(mdbreadmiLstCGEORTBean);
                javaMI.mvxAccess(null);
            }
        }
        return mdbreadmilmplinebeans;
    }

    public MDBREADMIGettMPORDTBean GetMPORDT00(String ORTY) {
        MDBREADMIGettMPORDTBean mdbreadmigmpordtBean = new MDBREADMIGettMPORDTBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetMPORDT00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("ORTY", ORTY);
        recFlag = javaMI.mvxAccess("GetMPORDT00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbreadmigmpordtBean.setORTY(javaMI.mvxGetField("ORTY"));   //Order type
            mdbreadmigmpordtBean.setTX15(javaMI.mvxGetField("TX15"));   //Name
            mdbreadmigmpordtBean.setTX40(javaMI.mvxGetField("TX40"));   //Description
            mdbreadmigmpordtBean.setPOTC(javaMI.mvxGetField("POTC"));   //Purch order cat
            mdbreadmigmpordtBean.setP150(javaMI.mvxGetField("P150"));   //150 Update mate
        }
        return mdbreadmigmpordtBean;
    }
    
    
    
    public MDBREADMIGetMILOMA00Bean GetMILOMA00(String ITNO,String BANO) {
        MDBREADMIGetMILOMA00Bean mdbreadmilotcostbean = new MDBREADMIGetMILOMA00Bean();
        int recFlag;
        String ERROR = null;
        String identity = "GetMILOMA00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("ITNO", ITNO);
        javaMI.mvxSetField("BANO", BANO);
        recFlag = javaMI.mvxAccess("GetMILOMA00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbreadmilotcostbean.setITNO(javaMI.mvxGetField("ITNO"));   //Item number	
            mdbreadmilotcostbean.setBANO(javaMI.mvxGetField("BANO"));   //Lot number
            mdbreadmilotcostbean.setLOTC(javaMI.mvxGetField("LOTC"));   //Lot cost
        }
        return mdbreadmilotcostbean;
    }
    
    public MDBREADMIGetCGEOEX00Bean GetCGEOEX00(String DIVI,String GEOC,String TAXT,String FRDT,String TAXC) {
        MDBREADMIGetCGEOEX00Bean mdbreadmiGetCGEOEX00Bean = new MDBREADMIGetCGEOEX00Bean();
        int recFlag;
        String ERROR = null;
        String identity = "GetCGEOEX00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("DIVI", DIVI);
        javaMI.mvxSetField("GEOC", GEOC);
        javaMI.mvxSetField("TAXT", TAXT);
        javaMI.mvxSetField("FRDT", FRDT);
        javaMI.mvxSetField("TAXC", TAXC);
        recFlag = javaMI.mvxAccess("GetCGEOEX00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbreadmiGetCGEOEX00Bean.setGEOC(javaMI.mvxGetField("GEOC"));   //Order type
            mdbreadmiGetCGEOEX00Bean.setTAR1(javaMI.mvxGetField("TAR1"));   //Name
            mdbreadmiGetCGEOEX00Bean.setTAXC(javaMI.mvxGetField("TAXC"));   //Description
            mdbreadmiGetCGEOEX00Bean.setTAXT(javaMI.mvxGetField("TAXT"));   //Purch order cat
        }
        return mdbreadmiGetCGEOEX00Bean;
    }
    
    public MDBREADMIGetCSYSTS00Bean GetCSYSTS00(String ECAR, String CSCD){
        MDBREADMIGetCSYSTS00Bean mdbreadmiGetCSYSTS00Bean = new MDBREADMIGetCSYSTS00Bean();
        int recFlag;
        String ERROR = null;
        String identity = "GetCSYSTS00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("ECAR", ECAR);
        javaMI.mvxSetField("CSCD", CSCD);
        recFlag = javaMI.mvxAccess("GetCSYSTS00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbreadmiGetCSYSTS00Bean.setECAR(javaMI.mvxGetField("ECAR"));//State
            mdbreadmiGetCSYSTS00Bean.setCSCD(javaMI.mvxGetField("CSCD"));//Country
            mdbreadmiGetCSYSTS00Bean.setTX40(javaMI.mvxGetField("TX40"));//Description
            mdbreadmiGetCSYSTS00Bean.setTX15(javaMI.mvxGetField("TX15"));//Name
            mdbreadmiGetCSYSTS00Bean.setTXID(javaMI.mvxGetField("TXID"));//TextID
            mdbreadmiGetCSYSTS00Bean.setRGDT(javaMI.mvxGetField("RGDT"));//Entrydate
            mdbreadmiGetCSYSTS00Bean.setRGTM(javaMI.mvxGetField("RGTM"));//Entrytime
            mdbreadmiGetCSYSTS00Bean.setLMDT(javaMI.mvxGetField("LMDT"));//Changedate
            mdbreadmiGetCSYSTS00Bean.setCHNO(javaMI.mvxGetField("CHNO"));//Chgno
            mdbreadmiGetCSYSTS00Bean.setCHID(javaMI.mvxGetField("CHID"));//Changedby
            mdbreadmiGetCSYSTS00Bean.setLMTS(javaMI.mvxGetField("LMTS"));//Timestamp
            mdbreadmiGetCSYSTS00Bean.setPENO(javaMI.mvxGetField("PENO"));//PST exempt no
            mdbreadmiGetCSYSTS00Bean.setORDE(javaMI.mvxGetField("ORDE"));//Destin/Origin
        }
        return mdbreadmiGetCSYSTS00Bean;
    }
    
    public MDBREADMIGetMITFAC00Bean GetMITFAC00(String FACI, String ITNO) {
        MDBREADMIGetMITFAC00Bean mDBREADMIGetMITFAC00Bean = new MDBREADMIGetMITFAC00Bean();
        int recFlag;
        String ERROR = null;
        String identity = "GetMITFAC00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("FACI", FACI);
        javaMI.mvxSetField("ITNO", ITNO);
        recFlag = javaMI.mvxAccess("GetMITFAC00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mDBREADMIGetMITFAC00Bean.setFACI(javaMI.mvxGetField("FACI"));//State
            mDBREADMIGetMITFAC00Bean.setITNO(javaMI.mvxGetField("ITNO"));//Country
            mDBREADMIGetMITFAC00Bean.setCSNO(javaMI.mvxGetField("CSNO"));//Description
        }
        return mDBREADMIGetMITFAC00Bean;
    }
    
    public MDBREADMIGetCIDVEN00Bean GetCIDVEN00(String SUNO){
        MDBREADMIGetCIDVEN00Bean mdbreadmiGetCIDVEN00Bean = new MDBREADMIGetCIDVEN00Bean();
        int recFlag;
        String ERROR = null;
        String identity = "GetCIDVEN00";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("GetCIDVEN00");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbreadmiGetCIDVEN00Bean.setSUNO(javaMI.mvxGetField("SUNO"));//Supplier
            mdbreadmiGetCIDVEN00Bean.setCFI1(javaMI.mvxGetField("CFI1"));//User Defined Field
        }
        return mdbreadmiGetCIDVEN00Bean;
    }
    public MDBREADMIGetCSYTAB20Bean GetCSYTAB20(String STCO, String STKY, String LNCD, String DIVI) {
        MDBREADMIGetCSYTAB20Bean mdbreadmiGetCSYTAB20Bean = new MDBREADMIGetCSYTAB20Bean();
        int recFlag;
        String ERROR = null;
        String identity = "GetCSYTAB20";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("STCO", STCO);
        javaMI.mvxSetField("STKY", STKY);
        javaMI.mvxSetField("LNCD", LNCD);
        javaMI.mvxSetField("DIVI", DIVI);
        recFlag = javaMI.mvxAccess("GetCSYTAB20");
        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            mdbreadmiGetCSYTAB20Bean.setDIVI(javaMI.mvxGetField("DIVI")); //Division
            mdbreadmiGetCSYTAB20Bean.setSTCO(javaMI.mvxGetField("STCO")); //Constant value
            mdbreadmiGetCSYTAB20Bean.setSTKY(javaMI.mvxGetField("STKY")); //Key value
            mdbreadmiGetCSYTAB20Bean.setLNCD(javaMI.mvxGetField("LNCD")); //Language
            mdbreadmiGetCSYTAB20Bean.setTX40(javaMI.mvxGetField("TX40")); //Description
            mdbreadmiGetCSYTAB20Bean.setTX15(javaMI.mvxGetField("TX15")); //Name
        }
        return mdbreadmiGetCSYTAB20Bean;
    }
}
