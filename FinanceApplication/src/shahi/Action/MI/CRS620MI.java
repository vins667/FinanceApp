/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI;

import java.util.ArrayList;
import java.util.List;
import shahi.Action.Beans.LstAddressesBeans;
import shahi.Action.MI.Beans.CRS620MIGetBasicDataBean;
import shahi.Action.MailProvider.MailProvider;

/**
 *
 * @author Ranjeet
 */
public class CRS620MI extends BaseMI {

    public CRS620MI() {
        setProgram("CRS620MI");
    }
    String identity = null;
//, String IDTIZO, String IDTXID

    public String AddSupplier(String IDSUNO, String IDSUNM, String IDPHNO, String IDPHN2, String IDTFNO, String IDCSCD, String IDECAR,
            String IDVRNO, String IDSUCO, String IDSUCM, String IICUCD, String IIORTY, String IITEDL, String IIMODL, String IITEAF, String IITEPY,
            String IIPYME, String IITECD, String IIACRF, String IISUSY, String IISUST, String IISUCL, String COST_METHOD, String XSTA,
            String XEXA, String XPAN, String TEPA, String ALSU) {
        identity = "AddSupplier";
        int recFlag;
        javaMI.mvxClearFields();
        String CFI5 = "U";
        if (XSTA != null && XSTA.equals("1")) {
            CFI5 = "R";
        }

        if (IIACRF == null) {
            IIACRF = "";
        }

        String CFI3 = "";
        if (XEXA != null && XEXA.equals("1")) {
            CFI3 = "NOT";
        }

        if (IDPHNO == null) {
            IDPHNO = "";
        }
        if (IDPHN2 == null) {
            IDPHN2 = "";
        }
        if (IDTFNO == null) {
            IDTFNO = "";
        }
        if (IDCSCD == null) {
            IDCSCD = "";
        }
        if (IDECAR == null) {
            IDECAR = "";
        }
        if (IDVRNO == null) {
            IDVRNO = "";
        }
        if (IDSUCO == null) {
            IDSUCO = "";
        }
        if (IDSUCM == null) {
            IDSUCM = "";
        }
        if (IICUCD == null) {
            IICUCD = "";
        }
        if (IIORTY == null) {
            IIORTY = "";
        }
        if (IITEDL == null) {
            IITEDL = "";
        }
        if (IIMODL == null) {
            IIMODL = "";
        }
        if (IITEAF == null) {
            IITEAF = "";
        }
        if (IITEPY == null) {
            IITEPY = "";
        }
        if (IIPYME == null) {
            IIPYME = "";
        }
        if (IITECD == null) {
            IITECD = "";
        }
        if (IISUSY == null) {
            IISUSY = "";
        }
        if (IISUST == null) {
            IISUST = "";
        }
        if (IISUCL == null) {
            IISUCL = "";
        }
        if (COST_METHOD == null) {
            COST_METHOD = "";
        }
        if (XEXA == null) {
            XEXA = "";
        }
        if (XPAN == null) {
            XPAN = "";
        }
        if (ALSU == null) {
            ALSU = "";
        }

        if (TEPA == null || (TEPA != null && TEPA.length() == 0)) {
            TEPA = "NA";
        }

        if (IDSUCM != null && IDSUCM.length() > 20) {
            IDSUCM = IDSUCM.substring(0, 20);
        }

        javaMI.mvxSetField("SUNO", IDSUNO);
        javaMI.mvxSetField("SUTY", "0");
        javaMI.mvxSetField("SUNM", IDSUNM);
        //javaMI.mvxSetField("STAT","20");
        javaMI.mvxSetField("LNCD", "GB");
        javaMI.mvxSetField("PHNO", IDPHNO);
        javaMI.mvxSetField("PHN2", IDPHN2);
        javaMI.mvxSetField("TFNO", IDTFNO);
        javaMI.mvxSetField("CSCD", IDCSCD);
        javaMI.mvxSetField("ECAR", IDECAR);
        javaMI.mvxSetField("VRNO", IDVRNO);
        javaMI.mvxSetField("SUCO", IDSUCO);
        javaMI.mvxSetField("SUAL", "NA");
        javaMI.mvxSetField("SUCM", IDSUCM);
        javaMI.mvxSetField("MEPF", "M1");
        javaMI.mvxSetField("DESV", "GB");
        javaMI.mvxSetField("CUCD", IICUCD);
        javaMI.mvxSetField("ORTY", IIORTY);
        javaMI.mvxSetField("TEDL", IITEDL);
        javaMI.mvxSetField("MODL", IIMODL);
        javaMI.mvxSetField("TEAF", IITEAF);
        javaMI.mvxSetField("TEPY", IITEPY);
        javaMI.mvxSetField("PYME", IIPYME);
        javaMI.mvxSetField("TECD", IITECD);
        javaMI.mvxSetField("ACRF", IIACRF);
        javaMI.mvxSetField("ATPR", "2");
        javaMI.mvxSetField("SUSY", IISUSY);
        javaMI.mvxSetField("SUST", IISUST);
        javaMI.mvxSetField("SUCL", IISUCL);
        javaMI.mvxSetField("WSCA", COST_METHOD);
        javaMI.mvxSetField("XSTA", XSTA);
        javaMI.mvxSetField("TLNO", XPAN);

        javaMI.mvxSetField("ABSK", "A");
        javaMI.mvxSetField("ABSM", "1");
        javaMI.mvxSetField("QUCL", "A");
        javaMI.mvxSetField("DT4T", "1");
        javaMI.mvxSetField("DTCD", "1");
        javaMI.mvxSetField("SHST", "0");
        javaMI.mvxSetField("CRTP", "1");
        javaMI.mvxSetField("DTFM", "DMY");
        javaMI.mvxSetField("TEPA", TEPA);
        javaMI.mvxSetField("TXAP", "0");
        javaMI.mvxSetField("TAXC", "000");
        javaMI.mvxSetField("CFI5", CFI5);
        javaMI.mvxSetField("VTCD", "0");
        javaMI.mvxSetField("XEXA", XEXA);
        javaMI.mvxSetField("IAPT", "1");
        javaMI.mvxSetField("IAPE", "1");
        javaMI.mvxSetField("IAPF", "3");
        javaMI.mvxSetField("SUST", "1");
        javaMI.mvxSetField("DTDY", "2");
        javaMI.mvxSetField("CFI3", CFI3);
        javaMI.mvxSetField("ALSU", ALSU);



        recFlag = javaMI.mvxAccess("AddSupplier");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("AddSupplier " + IDSUNO + status);

            //sendError("AddSupplier " + IDSUNO + status);

            status = "0";

        }
        return status;
    }

    public CRS620MIGetBasicDataBean getGetBasicData(String CONO, String SUNO) {
        CRS620MIGetBasicDataBean crs620miGetBasicDataBean = new CRS620MIGetBasicDataBean();
        int recFlag;
        String ERROR = null;
        String identity = "GetBasicData";

        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", CONO);
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("GetBasicData");

        if (recFlag > 0) {
            ERROR = javaMI.mvxGetLastError();
        } else {
            crs620miGetBasicDataBean.setCONO(javaMI.mvxGetField("CONO"));
            crs620miGetBasicDataBean.setSUNO(javaMI.mvxGetField("SUNO"));
            crs620miGetBasicDataBean.setSUTY(javaMI.mvxGetField("SUTY"));
            crs620miGetBasicDataBean.setSUNM(javaMI.mvxGetField("SUNM"));
            crs620miGetBasicDataBean.setALSU(javaMI.mvxGetField("ALSU"));
            crs620miGetBasicDataBean.setSTAT(javaMI.mvxGetField("STAT"));
            crs620miGetBasicDataBean.setCORG(javaMI.mvxGetField("CORG"));
            crs620miGetBasicDataBean.setCOR2(javaMI.mvxGetField("COR2"));
            crs620miGetBasicDataBean.setLNCD(javaMI.mvxGetField("LNCD"));
            crs620miGetBasicDataBean.setPHNO(javaMI.mvxGetField("PHNO"));
            crs620miGetBasicDataBean.setPHN2(javaMI.mvxGetField("PHN2"));
            crs620miGetBasicDataBean.setTLNO(javaMI.mvxGetField("TLNO"));
            crs620miGetBasicDataBean.setTFNO(javaMI.mvxGetField("TFNO"));
            crs620miGetBasicDataBean.setCSCD(javaMI.mvxGetField("CSCD"));
            crs620miGetBasicDataBean.setDTFM(javaMI.mvxGetField("DTFM"));
            crs620miGetBasicDataBean.setEDIT(javaMI.mvxGetField("EDIT"));
            crs620miGetBasicDataBean.setVRNO(javaMI.mvxGetField("VRNO"));
            crs620miGetBasicDataBean.setSUCO(javaMI.mvxGetField("SUCO"));
            crs620miGetBasicDataBean.setSUAL(javaMI.mvxGetField("SUAL"));
            crs620miGetBasicDataBean.setSUCM(javaMI.mvxGetField("SUCM"));
            crs620miGetBasicDataBean.setMEPF(javaMI.mvxGetField("MEPF"));
            crs620miGetBasicDataBean.setPODA(javaMI.mvxGetField("PODA"));
            crs620miGetBasicDataBean.setSFI1(javaMI.mvxGetField("SFI1"));
            crs620miGetBasicDataBean.setSFI2(javaMI.mvxGetField("SFI2"));
            crs620miGetBasicDataBean.setSFI3(javaMI.mvxGetField("SFI3"));
            crs620miGetBasicDataBean.setSFI4(javaMI.mvxGetField("SFI4"));
            crs620miGetBasicDataBean.setSFI5(javaMI.mvxGetField("SFI5"));
            crs620miGetBasicDataBean.setHAFE(javaMI.mvxGetField("HAFE"));
            crs620miGetBasicDataBean.setTXID(javaMI.mvxGetField("TXID"));
            crs620miGetBasicDataBean.setECAR(javaMI.mvxGetField("ECAR"));
            crs620miGetBasicDataBean.setFWSC(javaMI.mvxGetField("FWSC"));
            crs620miGetBasicDataBean.setSUCL(javaMI.mvxGetField("SUCL"));
            crs620miGetBasicDataBean.setQUCL(javaMI.mvxGetField("QUCL"));
            crs620miGetBasicDataBean.setORTY(javaMI.mvxGetField("ORTY"));
            crs620miGetBasicDataBean.setTEDL(javaMI.mvxGetField("TEDL"));
            crs620miGetBasicDataBean.setMODL(javaMI.mvxGetField("MODL"));
            crs620miGetBasicDataBean.setTEAF(javaMI.mvxGetField("TEAF"));
            crs620miGetBasicDataBean.setTEPA(javaMI.mvxGetField("TEPA"));
            crs620miGetBasicDataBean.setDT4T(javaMI.mvxGetField("DT4T"));
            crs620miGetBasicDataBean.setDTCD(javaMI.mvxGetField("DTCD"));
            crs620miGetBasicDataBean.setVTCD(javaMI.mvxGetField("VTCD"));
            crs620miGetBasicDataBean.setTXAP(javaMI.mvxGetField("TXAP"));
            crs620miGetBasicDataBean.setTAXC(javaMI.mvxGetField("TAXC"));
            crs620miGetBasicDataBean.setCUCD(javaMI.mvxGetField("CUCD"));
            crs620miGetBasicDataBean.setCRTP(javaMI.mvxGetField("CRTP"));
            crs620miGetBasicDataBean.setTEPY(javaMI.mvxGetField("TEPY"));
            crs620miGetBasicDataBean.setPYME(javaMI.mvxGetField("PYME"));
            crs620miGetBasicDataBean.setATPR(javaMI.mvxGetField("ATPR"));
            crs620miGetBasicDataBean.setACRF(javaMI.mvxGetField("ACRF"));
            crs620miGetBasicDataBean.setBUYE(javaMI.mvxGetField("BUYE"));
            crs620miGetBasicDataBean.setRESP(javaMI.mvxGetField("RESP"));
            crs620miGetBasicDataBean.setAGNT(javaMI.mvxGetField("AGNT"));
            crs620miGetBasicDataBean.setABSK(javaMI.mvxGetField("ABSK"));
            crs620miGetBasicDataBean.setABSM(javaMI.mvxGetField("ABSM"));
            crs620miGetBasicDataBean.setPWMT(javaMI.mvxGetField("PWMT"));
            crs620miGetBasicDataBean.setDCSM(javaMI.mvxGetField("DCSM"));
            crs620miGetBasicDataBean.setFUSC(javaMI.mvxGetField("FUSC"));
            crs620miGetBasicDataBean.setSPFC(javaMI.mvxGetField("SPFC"));
            crs620miGetBasicDataBean.setCOBI(javaMI.mvxGetField("COBI"));
            crs620miGetBasicDataBean.setSCNO(javaMI.mvxGetField("SCNO"));
            crs620miGetBasicDataBean.setSUGR(javaMI.mvxGetField("SUGR"));
            crs620miGetBasicDataBean.setSHST(javaMI.mvxGetField("SHST"));
            crs620miGetBasicDataBean.setPOOT(javaMI.mvxGetField("POOT"));
            crs620miGetBasicDataBean.setOUCN(javaMI.mvxGetField("OUCN"));
            crs620miGetBasicDataBean.setTINO(javaMI.mvxGetField("TINO"));
            crs620miGetBasicDataBean.setPRSU(javaMI.mvxGetField("PRSU"));
            crs620miGetBasicDataBean.setSERS(javaMI.mvxGetField("SERS"));
            crs620miGetBasicDataBean.setSBPE(javaMI.mvxGetField("SBPE"));
            crs620miGetBasicDataBean.setPACD(javaMI.mvxGetField("PACD"));
            crs620miGetBasicDataBean.setPTDY(javaMI.mvxGetField("PTDY"));
            crs620miGetBasicDataBean.setSUST(javaMI.mvxGetField("SUST"));
            crs620miGetBasicDataBean.setDTDY(javaMI.mvxGetField("DTDY"));
            crs620miGetBasicDataBean.setLIDT(javaMI.mvxGetField("LIDT"));
            crs620miGetBasicDataBean.setTECD(javaMI.mvxGetField("TECD"));
            crs620miGetBasicDataBean.setREGR(javaMI.mvxGetField("REGR"));
            crs620miGetBasicDataBean.setSUSY(javaMI.mvxGetField("SUSY"));
            crs620miGetBasicDataBean.setSHAC(javaMI.mvxGetField("SHAC"));
            crs620miGetBasicDataBean.setAVCD(javaMI.mvxGetField("AVCD"));
            crs620miGetBasicDataBean.setTAME(javaMI.mvxGetField("TAME"));
            crs620miGetBasicDataBean.setTDCD(javaMI.mvxGetField("TDCD"));
            crs620miGetBasicDataBean.setIAPT(javaMI.mvxGetField("IAPT"));
            crs620miGetBasicDataBean.setIAPC(javaMI.mvxGetField("IAPC"));
            crs620miGetBasicDataBean.setIAPE(javaMI.mvxGetField("IAPE"));
            crs620miGetBasicDataBean.setIAPF(javaMI.mvxGetField("IAPF"));
            crs620miGetBasicDataBean.setCFI1(javaMI.mvxGetField("CFI1"));
            crs620miGetBasicDataBean.setCFI2(javaMI.mvxGetField("CFI2"));
            crs620miGetBasicDataBean.setCFI3(javaMI.mvxGetField("CFI3"));
            crs620miGetBasicDataBean.setCFI4(javaMI.mvxGetField("CFI4"));
            crs620miGetBasicDataBean.setCFI5(javaMI.mvxGetField("CFI5"));
            crs620miGetBasicDataBean.setCGRP(javaMI.mvxGetField("CGRP"));
            crs620miGetBasicDataBean.setRASN(javaMI.mvxGetField("RASN"));
        }
        return crs620miGetBasicDataBean;
    }

    public String AddAddress(String SUNO, String SAADID, String SAADTE, String SAADR1, String SAADR2, String SAADR3, String SAADR4, String SACSCD, String SAECAR,
            String SAPONO, String SASTDT, String SAPRIA) {
        identity = "AddAddress";
        int recFlag;
        javaMI.mvxClearFields();

        if (SAADTE != null && SAADTE.trim().length() > 0 && Integer.parseInt(SAADTE.trim()) == 10) {
            SAADID = "";
        }
        if (SAADTE == null) {
            SAADTE = "";
        }
        if (SAADR1 == null) {
            SAADR1 = "";
        }
        if (SAADR2 == null) {
            SAADR2 = "";
        }
        if (SAADR3 == null) {
            SAADR3 = "";
        }
        if (SAADR4 == null) {
            SAADR4 = "";
        }
        if (SACSCD == null) {
            SACSCD = "";
        }
        if (SAECAR == null) {
            SAECAR = "";
        }
        if (SAPONO == null) {
            SAPONO = "";
        }
        if (SASTDT == null) {
            SASTDT = "";
        }
        if (SAPRIA == null) {
            SAPRIA = "";
        }

        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("ADID", SAADID);
        javaMI.mvxSetField("ADTE", SAADTE);
        javaMI.mvxSetField("ADR1", SAADR1);
        javaMI.mvxSetField("ADR2", SAADR2);
        javaMI.mvxSetField("ADR3", SAADR3);
        javaMI.mvxSetField("ADR4", SAADR4);
        javaMI.mvxSetField("CSCD", SACSCD);
        javaMI.mvxSetField("ECAR", SAECAR);
        javaMI.mvxSetField("PONO", SAPONO);
        javaMI.mvxSetField("STDT", SASTDT);
        javaMI.mvxSetField("PRIA", "0");
        recFlag = javaMI.mvxAccess("AddAddress");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("AddAddress" + SUNO + status);
            // sendError("AddAddress"+  SUNO  + status);
            status = "0";

        }
        return status;
    }
    //GetBasicData
    public boolean FindGetBasicData(String SUNO) {
        int recFlag;
        identity = "GetBasicData";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("GetBasicData");
        if (recFlag > 0) {
            return false;
        } else {
            return true;
        }
    }

    public String FindGetTXTID(String SUNO) {
        int recFlag;
        identity = "GetBasicData";
        String ID = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("GetBasicData");
        if (recFlag > 0) {
        } else {


            ID = javaMI.mvxGetField("TXID");


        }
        return ID;
    }

    public String FindGetSUNM(String SUNO) {
        int recFlag;
        identity = "GetBasicData";
        String ID = null;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("CONO", "111");
        javaMI.mvxSetField("SUNO", SUNO);
        recFlag = javaMI.mvxAccess("GetBasicData");
        if (recFlag > 0) {
        } else {


            ID = javaMI.mvxGetField("SUNM");


        }
        return ID;
    }

    public String AddSupplierRef(String SUNO, String RFID, String YRE1, String EMAL) {
        identity = "AddSupplierRef";
        int recFlag;
        javaMI.mvxClearFields();


        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("RFTY", "10");
        javaMI.mvxSetField("RFID", RFID);
        javaMI.mvxSetField("YRE1", YRE1);
        javaMI.mvxSetField("EMAL", EMAL);

        recFlag = javaMI.mvxAccess("AddSupplierRef");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("AddSupplierRef" + SUNO + status);
            //sendError("AddSupplierRef" + SUNO +  status);
            status = "0";

        }
        return status;
    }

    public String UpdSupplierRef(String SUNO, String RFID, String YRE1, String EMAL) {
        identity = "UpdSupplierRef";
        int recFlag;
        javaMI.mvxClearFields();


        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("RFTY", "10");
        javaMI.mvxSetField("RFID", RFID);
        javaMI.mvxSetField("YRE1", YRE1);
        javaMI.mvxSetField("EMAL", EMAL);

        recFlag = javaMI.mvxAccess("UpdSupplierRef");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("UpdSupplierRef " + SUNO + status);
            //sendError("UpdSupplierRef " + SUNO + status);
            status = "0";

        }
        return status;
    }

    public String DeleteSupplierRef(String SUNO, String RFTY, String RFID) {
        identity = "DelSupplierRef";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("RFTY", RFTY);
        javaMI.mvxSetField("RFID", RFID);
        recFlag = javaMI.mvxAccess("DelSupplierRef");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            //System.out.println("UpdSupplierRef " + SUNO+status);
            // sendError("UpdSupplierRef " + SUNO + status);
            status = "0";

        }
        return status;
    }

    public boolean FindGetSupplierRef(String SUNO, String RFID) {
        int recFlag;
        identity = "GetSupplierRef";


        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("RFTY", "10");
        javaMI.mvxSetField("RFID", RFID);

        recFlag = javaMI.mvxAccess("GetSupplierRef");
        if (recFlag > 0) {

            //System.out.println("GetSupplierRef " +SUNO +javaMI.mvxGetLastError());
            return false;
        } else {
            return true;
        }
    }

    public String UpdSupplierSTAT(String IDSUNO) {
        identity = "UpdSupplier";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", IDSUNO);
        javaMI.mvxSetField("STAT", "90");
        recFlag = javaMI.mvxAccess("UpdSupplier");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            //sendError("UpdSupplier 90 " + IDSUNO + status);
            status = "0";

        }
        return status;
    }

    public String UpdSupplierTXID(String IDSUNO, String TXID) {
        identity = "UpdSupplier";
        int recFlag;
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", IDSUNO);
        javaMI.mvxSetField("TXID", TXID);
        recFlag = javaMI.mvxAccess("UpdSupplier");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println(status);
            //sendError("UpdSupplierTXID " + IDSUNO + status);
            status = "0";

        }
        return status;
    }

    public String UpdSupplier(String IDSUNO, String IDSUNM, String IDPHNO, String IDPHN2, String IDTFNO, String IDCSCD, String IDECAR,
            String IDVRNO, String IDSUCO, String IDSUCM, String IICUCD, String IIORTY, String IITEDL, String IIMODL, String IITEAF, String IITEPY, String IIPYME,
            String IITECD, String IIACRF, String IISUSY, String IISUST, String IISUCL, String COST_METHOD, String XSTA, String XEXA, String XPAN, String TEPA, String ALSU) {
        identity = "UpdSupplier";
        int recFlag;
        javaMI.mvxClearFields();

        if (IIACRF == null) {
            IIACRF = "";
        }
        String CFI5 = "U";
        if (XSTA != null && XSTA.equals("1")) {
            CFI5 = "R";
        }
        String CFI3 = "NOT";
        if (XEXA != null && XEXA.equals("1")) {
            CFI3 = "REG";
        }
        if (IDPHNO == null) {
            IDPHNO = "";
        }
        if (IDPHN2 == null) {
            IDPHN2 = "";
        }
        if (IDTFNO == null) {
            IDTFNO = "";
        }
        if (IDCSCD == null) {
            IDCSCD = "";
        }
        if (IDECAR == null) {
            IDECAR = "";
        }
        if (IDVRNO == null) {
            IDVRNO = "";
        }
        if (IDSUCO == null) {
            IDSUCO = "";
        }
        if (IDSUCM == null) {
            IDSUCM = "";
        }
        if (IICUCD == null) {
            IICUCD = "";
        }
        if (IIORTY == null) {
            IIORTY = "";
        }
        if (IITEDL == null) {
            IITEDL = "";
        }
        if (IIMODL == null) {
            IIMODL = "";
        }
        if (IITEAF == null) {
            IITEAF = "";
        }
        if (IITEPY == null) {
            IITEPY = "";
        }
        if (IIPYME == null) {
            IIPYME = "";
        }
        if (IITECD == null) {
            IITECD = "";
        }
        if (IISUSY == null) {
            IISUSY = "";
        }
        if (IISUST == null) {
            IISUST = "";
        }
        if (IISUCL == null) {
            IISUCL = "";
        }
        if (COST_METHOD == null) {
            COST_METHOD = "";
        }
        if (XEXA == null) {
            XEXA = "";
        }
        if (XPAN == null) {
            XPAN = "";
        }
        if (ALSU == null) {
            ALSU = "";
        }

        if (TEPA == null || (TEPA != null && TEPA.length() == 0)) {
            TEPA = "NA";
        }

        if (IDSUCM != null && IDSUCM.length() > 20) {
            IDSUCM = IDSUCM.substring(0, 20);
        }
        javaMI.mvxSetField("SUNO", IDSUNO);
        javaMI.mvxSetField("SUTY", "0");
        javaMI.mvxSetField("SUNM", IDSUNM);
        javaMI.mvxSetField("STAT", "20");
        javaMI.mvxSetField("LNCD", "GB");
        javaMI.mvxSetField("PHNO", IDPHNO);
        javaMI.mvxSetField("PHN2", IDPHN2);
        javaMI.mvxSetField("TFNO", IDTFNO);
        javaMI.mvxSetField("CSCD", IDCSCD);
        javaMI.mvxSetField("ECAR", IDECAR);
        javaMI.mvxSetField("VRNO", IDVRNO);
        javaMI.mvxSetField("SUCO", IDSUCO);
        javaMI.mvxSetField("SUAL", "NA");
        javaMI.mvxSetField("SUCM", IDSUCM);
        javaMI.mvxSetField("MEPF", "M1");
        javaMI.mvxSetField("DESV", "GB");
        javaMI.mvxSetField("CUCD", IICUCD);
        javaMI.mvxSetField("ORTY", IIORTY);
        javaMI.mvxSetField("TEDL", IITEDL);
        javaMI.mvxSetField("MODL", IIMODL);
        javaMI.mvxSetField("TEAF", IITEAF);
        javaMI.mvxSetField("TEPY", IITEPY);
        javaMI.mvxSetField("PYME", IIPYME);
        javaMI.mvxSetField("TECD", IITECD);
        javaMI.mvxSetField("ACRF", IIACRF);
        javaMI.mvxSetField("ATPR", "2");
        javaMI.mvxSetField("SUSY", IISUSY);
        javaMI.mvxSetField("SUST", IISUST);
        javaMI.mvxSetField("SUCL", IISUCL);
        javaMI.mvxSetField("WSCA", COST_METHOD);
        javaMI.mvxSetField("XSTA", XSTA);
        javaMI.mvxSetField("CFI5", CFI5);
        javaMI.mvxSetField("XEXA", XEXA);
        javaMI.mvxSetField("TLNO", XPAN);
        javaMI.mvxSetField("TEPA", TEPA);
        javaMI.mvxSetField("CFI3", CFI3);
        javaMI.mvxSetField("ALSU", ALSU);




        recFlag = javaMI.mvxAccess("UpdSupplier");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("UpdSupplier " + IDSUNO + status);
            //sendError("UpdSupplier " + IDSUNO +status);
            status = "0";

        }
        return status;
    }

    public String UpdAddress(String SUNO, String SAADID, String SAADTE, String SAADR1, String SAADR2, String SAADR3, String SAADR4, String SACSCD, String SAECAR,
            String SAPONO, String SASTDT, String SAPRIA, String SUNM) {
        identity = "UpdAddress";
        int recFlag;
        javaMI.mvxClearFields();

        if (SAADTE != null && SAADTE.trim().length() > 0 && Integer.parseInt(SAADTE.trim()) == 10) {
            SAADID = "";
        }

        if (SAADTE == null) {
            SAADTE = "";
        }
        if (SAADR1 == null) {
            SAADR1 = "";
        }
        if (SAADR2 == null) {
            SAADR2 = "";
        }
        if (SAADR3 == null) {
            SAADR3 = "";
        }
        if (SAADR4 == null) {
            SAADR4 = "";
        }
        if (SACSCD == null) {
            SACSCD = "";
        }
        if (SAECAR == null) {
            SAECAR = "";
        }
        if (SAPONO == null) {
            SAPONO = "";
        }
        if (SASTDT == null) {
            SASTDT = "";
        }
        if (SAPRIA == null) {
            SAPRIA = "";
        }

        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("SUNM", SUNM);
        javaMI.mvxSetField("ADID", SAADID);
        javaMI.mvxSetField("ADTE", SAADTE);
        javaMI.mvxSetField("ADR1", SAADR1);
        javaMI.mvxSetField("ADR2", SAADR2);
        javaMI.mvxSetField("ADR3", SAADR3);
        javaMI.mvxSetField("ADR4", SAADR4);
        javaMI.mvxSetField("CSCD", SACSCD);
        javaMI.mvxSetField("ECAR", SAECAR);
        javaMI.mvxSetField("PONO", SAPONO);
        javaMI.mvxSetField("STDT", SASTDT);
        javaMI.mvxSetField("PRIA", "0");
        recFlag = javaMI.mvxAccess("UpdAddress");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("UpdAddress " + SUNO + status);
            //sendError("UpdAddress "+ SUNO +status);
            status = "0";

        }
        return status;
    }

    public List<LstAddressesBeans> LstSupplierRefList(String SUNO) {
        List<LstAddressesBeans> list1 = new ArrayList<LstAddressesBeans>();
        LstAddressesBeans olb = null;
        int recFlag;
        identity = "LstSupplierRef";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", SUNO);

        recFlag = javaMI.mvxAccess("LstSupplierRef");
        if (recFlag > 0) {
        } else {

            while (javaMI.mvxMore()) {

                olb = new LstAddressesBeans(javaMI.mvxGetField("SUNO"), javaMI.mvxGetField("RFTY"), javaMI.mvxGetField("RFID"), "");

                list1.add(olb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public List<LstAddressesBeans> LstAddressesList(String SUNO) {
        List<LstAddressesBeans> list1 = new ArrayList<LstAddressesBeans>();
        LstAddressesBeans olb = null;
        int recFlag;
        identity = "LstAddresses";
        javaMI.mvxClearFields();
        javaMI.mvxSetField("SUNO", SUNO);

        recFlag = javaMI.mvxAccess("LstAddresses");
        if (recFlag > 0) {
            //CRMLogHelper.logMessage(HelperConstantsFnl.SYSTEM_EXCEPTION, getClass().getName(), "Returned from applicationserver " + identity + javaMI.mvxGetLastError(), CRMLogHelper.STR_ERROR_PRIORITY);
        } else {

            while (javaMI.mvxMore()) {

                olb = new LstAddressesBeans(javaMI.mvxGetField("SUNO"), javaMI.mvxGetField("ADTE"), javaMI.mvxGetField("STDT"), javaMI.mvxGetField("ADID"));

                list1.add(olb);
                javaMI.mvxAccess(null);
            }
        }
        return list1;
    }

    public String DelAddress(String SUNO, String SAADID, String SAADTE, String SASTDT) {
        identity = "DelAddress";
        int recFlag;
        javaMI.mvxClearFields();

        if (SAADTE != null && SAADTE.trim().length() > 0 && Integer.parseInt(SAADTE.trim()) == 10) {
            SAADID = "";
        }
        javaMI.mvxSetField("SUNO", SUNO);
        javaMI.mvxSetField("ADID", SAADID);
        javaMI.mvxSetField("ADTE", SAADTE);
        javaMI.mvxSetField("STDT", SASTDT);

        recFlag = javaMI.mvxAccess("DelAddress");
        String status = "1";
        if (recFlag > 0) {
            status = javaMI.mvxGetLastError();
            System.out.println("DelAddress" + status);
            //sendError("DelAddress "+ SUNO +status);
            status = "0";

        }
        return status;
    }

    public String sendError(String Erro) {


        MailProvider mail = new MailProvider();
        String[] emailList = {"ranjeet.gautam@shahi.co.in", "bal.singh@shahi.co.in"};
        String emailSubjectTxt = "Update Supplier In Movex Error";
        String emailFromAddress = "movex@shahi.co.in";
        String emailMsgTxt = "<div style='background-color:#f2f2f2;width:500pt;border-style:solid;border-width:1pt;border-color:blue'>";
        emailMsgTxt += "<table border='0' bgcolor='#f2f2f2' width='100%' cellpadding='5'    >";
        emailMsgTxt += "<tr><td style='color:#006699;font-weight:bold' >Dear Sir/Madam,<br></td></tr><tr><td style='color:#006699;font-weight:bold'  >Supplier Not Updated In Movex </br>Error</br>" + Erro + "</td></tr>";
        emailMsgTxt += "<tr><td style='color:#006699;font-weight:bold' ><br></td></tr><tr><td>This is an auto generated mail from Movex BI</td></tr>";
        emailMsgTxt += "</table>";
        try {

            mail.postMail(emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress, null);


        } catch (Exception ee) {
            System.out.println(ee.toString());
        }

        return "a";
    }
}
