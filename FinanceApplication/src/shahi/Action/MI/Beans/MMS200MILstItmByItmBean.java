/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author VIVEK
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class MMS200MILstItmByItmBean {
private String STAT;  //Status
private String ITNO;  //Item number
private String ITDS;  //Name
private String FUDS;  //Description 2
private String DWNO;  //Drawing number
private String RESP;  //Responsible
private String RENM;  //Name of user responsible
private String UNMS;  //Basic unit of measure
private String DS01;  //Description - Basic unit of measure
private String ITGR;  //Item group
private String DS02;  //Description - Item group
private String ITCL;  //Product group
private String DS03;  //Description - Product group
private String BUAR;  //Business area
private String DS04;  //Description - Business area
private String ITTY;  //Item type
private String DS05;  //Description - Item type
private String TPCD;  //Item category
private String MABU;  //Make/buy code
private String CHCD;  //Configuration code
private String STCD;  //Inventory accounting
private String BACD;  //Lot numbering method
private String VOL3;  //Volume
private String NEWE;  //Net weight
private String GRWE;  //Gross weight
private String PPUN;  //Purchase price U/M
private String DS06;  //Description - Purchase price U/M
private String BYPR;  //By/co-product code
private String WAPC;  //Normal waste percentage
private String QACD;  //Inspection code
private String EPCD;  //Yield calculation
private String POCY;  //Normal potency
private String ACTI;  //Active or catch weight item
private String HIE1;  //Hierachy level 1
private String HIE2;  //Hierachy level 2
private String HIE3;  //Hierachy level 3
private String HIE4;  //Hierachy level 4
private String HIE5;  //Hierachy level 5
private String GRP1;  //Search group 1
private String GRP2;  //Search group 2
private String GRP3;  //Search group 3
private String GRP4;  //Search group 4
private String GRP5;  //Search group 5
private String CFI1;  //User-defined field 1 - item
private String CFI2;  //User-defined field 2 - item
private String CFI3;  //User-defined field 3 - item
private String CFI4;  //User-defined field 4 - item
private String CFI5;  //User-defined field 5 - item
private String TXID;  //Text identity
private String ECMA;  //ECO managed
private String PRGP;  //Procurement group
private String DS07;  //Description - Procurement group
private String INDI;  //Lot control method
private String PUUN;  //Purchase order U/M
private String DS08;  //Description - Purchase order U/M
private String ALUC;  //Alternate U/M in use
private String IEAA;  //Item exists as alias identity
private String EXPD;  //Expiration date method
private String GRMT;  //Goods receiving method
private String HAZI;  //Danger indicator
private String SALE;  //Sales item
private String TAXC;  //Tax code customer/address
private String DS09;  //Description - Tax code customer/address
private String ATMO;  //Attribute model
private String ATMN;  //Attribute managed
private String TPLI;  //Template item number
private String FCU1;  //Estimated free unit of assigned goods
private String ALUN;  //Alternate U/M
private String IACP;  //Attribute-controlled item
private String HDPR;  //Main product
private String AAD0;  //Extended QA used
private String AAD1;  //Generate txt from text template
private String CHCL;  //Charge calculation
private String ITRC;  //Individual item tracing
private String VTCP;  //VAT code - purchase
private String DS10;  //Description - VAT code - purchase
private String VTCS;  //VAT code - sales
private String DS11;  //Description - VAT code - sales
private String LMDT;  //Change date
private String DCCD;  //Number of decimal places
private String PDCC;  //Number of price decimal places
private String SPUN;  //Sales price unit of measure
private String GRTI;  //Group technology class
private String GRTS;  //Distribution group technology
private String CAWP;  //Catch weight mode
private String CWUN;  //Catch weight unit of measure
private String CPUN;  //Standard cost price unit of measure
private String ITRU;  //Included in territorial roll-up
private String TECR;  //Core terms
private String EXCA;  //Exchangeable
private String ACCG;  //Acceptance group
private String CCCM;  //Costing model - core charge
private String CCI1;  //Core charge item number
private String CRI1;  //Core remain item number
private String HVMT;  //Harvest Method
private String ITNE;  //Extended item number
private String AUTC;  //Issue message
private String RIDE;  //Reference identity
private String RIDC;  //Reference identity code
private String RNRI;  //Returnable / non returnable indicator
private String SAFC;  //Suppliers ABC code
private String RMSG;  //Returnable message
private String VAMT;
private String UCOS;
private String ACRF;
private String APPR;
private String PUPR;

    public String getPUPR() {
        return PUPR;
    }

    public void setPUPR(String PUPR) {
        this.PUPR = PUPR;
    }


    public String getVAMT() {
        return VAMT;
    }

    public void setVAMT(String VAMT) {
        this.VAMT = VAMT;
    }

    public String getUCOS() {
        return UCOS;
    }

    public void setUCOS(String UCOS) {
        this.UCOS = UCOS;
    }

    public String getACRF() {
        return ACRF;
    }

    public void setACRF(String ACRF) {
        this.ACRF = ACRF;
    }

    public String getAPPR() {
        return APPR;
    }

    public void setAPPR(String APPR) {
        this.APPR = APPR;
    }



    public String getSTAT() {
        return STAT;
    }

    public void setSTAT(String STAT) {
        this.STAT = STAT;
    }

    public String getITNO() {
        return ITNO;
    }

    public void setITNO(String ITNO) {
        this.ITNO = ITNO;
    }

    public String getITDS() {
        return ITDS;
    }

    public void setITDS(String ITDS) {
        this.ITDS = ITDS;
    }

    public String getFUDS() {
        return FUDS;
    }

    public void setFUDS(String FUDS) {
        this.FUDS = FUDS;
    }

    public String getDWNO() {
        return DWNO;
    }

    public void setDWNO(String DWNO) {
        this.DWNO = DWNO;
    }

    public String getRESP() {
        return RESP;
    }

    public void setRESP(String RESP) {
        this.RESP = RESP;
    }

    public String getRENM() {
        return RENM;
    }

    public void setRENM(String RENM) {
        this.RENM = RENM;
    }

    public String getUNMS() {
        return UNMS;
    }

    public void setUNMS(String UNMS) {
        this.UNMS = UNMS;
    }

    public String getDS01() {
        return DS01;
    }

    public void setDS01(String DS01) {
        this.DS01 = DS01;
    }

    public String getITGR() {
        return ITGR;
    }

    public void setITGR(String ITGR) {
        this.ITGR = ITGR;
    }

    public String getDS02() {
        return DS02;
    }

    public void setDS02(String DS02) {
        this.DS02 = DS02;
    }

    public String getITCL() {
        return ITCL;
    }

    public void setITCL(String ITCL) {
        this.ITCL = ITCL;
    }

    public String getDS03() {
        return DS03;
    }

    public void setDS03(String DS03) {
        this.DS03 = DS03;
    }

    public String getBUAR() {
        return BUAR;
    }

    public void setBUAR(String BUAR) {
        this.BUAR = BUAR;
    }

    public String getDS04() {
        return DS04;
    }

    public void setDS04(String DS04) {
        this.DS04 = DS04;
    }

    public String getITTY() {
        return ITTY;
    }

    public void setITTY(String ITTY) {
        this.ITTY = ITTY;
    }

    public String getDS05() {
        return DS05;
    }

    public void setDS05(String DS05) {
        this.DS05 = DS05;
    }

    public String getTPCD() {
        return TPCD;
    }

    public void setTPCD(String TPCD) {
        this.TPCD = TPCD;
    }

    public String getMABU() {
        return MABU;
    }

    public void setMABU(String MABU) {
        this.MABU = MABU;
    }

    public String getCHCD() {
        return CHCD;
    }

    public void setCHCD(String CHCD) {
        this.CHCD = CHCD;
    }

    public String getSTCD() {
        return STCD;
    }

    public void setSTCD(String STCD) {
        this.STCD = STCD;
    }

    public String getBACD() {
        return BACD;
    }

    public void setBACD(String BACD) {
        this.BACD = BACD;
    }

    public String getVOL3() {
        return VOL3;
    }

    public void setVOL3(String VOL3) {
        this.VOL3 = VOL3;
    }

    public String getNEWE() {
        return NEWE;
    }

    public void setNEWE(String NEWE) {
        this.NEWE = NEWE;
    }

    public String getGRWE() {
        return GRWE;
    }

    public void setGRWE(String GRWE) {
        this.GRWE = GRWE;
    }

    public String getPPUN() {
        return PPUN;
    }

    public void setPPUN(String PPUN) {
        this.PPUN = PPUN;
    }

    public String getDS06() {
        return DS06;
    }

    public void setDS06(String DS06) {
        this.DS06 = DS06;
    }

    public String getBYPR() {
        return BYPR;
    }

    public void setBYPR(String BYPR) {
        this.BYPR = BYPR;
    }

    public String getWAPC() {
        return WAPC;
    }

    public void setWAPC(String WAPC) {
        this.WAPC = WAPC;
    }

    public String getQACD() {
        return QACD;
    }

    public void setQACD(String QACD) {
        this.QACD = QACD;
    }

    public String getEPCD() {
        return EPCD;
    }

    public void setEPCD(String EPCD) {
        this.EPCD = EPCD;
    }

    public String getPOCY() {
        return POCY;
    }

    public void setPOCY(String POCY) {
        this.POCY = POCY;
    }

    public String getACTI() {
        return ACTI;
    }

    public void setACTI(String ACTI) {
        this.ACTI = ACTI;
    }

    public String getHIE1() {
        return HIE1;
    }

    public void setHIE1(String HIE1) {
        this.HIE1 = HIE1;
    }

    public String getHIE2() {
        return HIE2;
    }

    public void setHIE2(String HIE2) {
        this.HIE2 = HIE2;
    }

    public String getHIE3() {
        return HIE3;
    }

    public void setHIE3(String HIE3) {
        this.HIE3 = HIE3;
    }

    public String getHIE4() {
        return HIE4;
    }

    public void setHIE4(String HIE4) {
        this.HIE4 = HIE4;
    }

    public String getHIE5() {
        return HIE5;
    }

    public void setHIE5(String HIE5) {
        this.HIE5 = HIE5;
    }

    public String getGRP1() {
        return GRP1;
    }

    public void setGRP1(String GRP1) {
        this.GRP1 = GRP1;
    }

    public String getGRP2() {
        return GRP2;
    }

    public void setGRP2(String GRP2) {
        this.GRP2 = GRP2;
    }

    public String getGRP3() {
        return GRP3;
    }

    public void setGRP3(String GRP3) {
        this.GRP3 = GRP3;
    }

    public String getGRP4() {
        return GRP4;
    }

    public void setGRP4(String GRP4) {
        this.GRP4 = GRP4;
    }

    public String getGRP5() {
        return GRP5;
    }

    public void setGRP5(String GRP5) {
        this.GRP5 = GRP5;
    }

    public String getCFI1() {
        return CFI1;
    }

    public void setCFI1(String CFI1) {
        this.CFI1 = CFI1;
    }

    public String getCFI2() {
        return CFI2;
    }

    public void setCFI2(String CFI2) {
        this.CFI2 = CFI2;
    }

    public String getCFI3() {
        return CFI3;
    }

    public void setCFI3(String CFI3) {
        this.CFI3 = CFI3;
    }

    public String getCFI4() {
        return CFI4;
    }

    public void setCFI4(String CFI4) {
        this.CFI4 = CFI4;
    }

    public String getCFI5() {
        return CFI5;
    }

    public void setCFI5(String CFI5) {
        this.CFI5 = CFI5;
    }

    public String getTXID() {
        return TXID;
    }

    public void setTXID(String TXID) {
        this.TXID = TXID;
    }

    public String getECMA() {
        return ECMA;
    }

    public void setECMA(String ECMA) {
        this.ECMA = ECMA;
    }

    public String getPRGP() {
        return PRGP;
    }

    public void setPRGP(String PRGP) {
        this.PRGP = PRGP;
    }

    public String getDS07() {
        return DS07;
    }

    public void setDS07(String DS07) {
        this.DS07 = DS07;
    }

    public String getINDI() {
        return INDI;
    }

    public void setINDI(String INDI) {
        this.INDI = INDI;
    }

    public String getPUUN() {
        return PUUN;
    }

    public void setPUUN(String PUUN) {
        this.PUUN = PUUN;
    }

    public String getDS08() {
        return DS08;
    }

    public void setDS08(String DS08) {
        this.DS08 = DS08;
    }

    public String getALUC() {
        return ALUC;
    }

    public void setALUC(String ALUC) {
        this.ALUC = ALUC;
    }

    public String getIEAA() {
        return IEAA;
    }

    public void setIEAA(String IEAA) {
        this.IEAA = IEAA;
    }

    public String getEXPD() {
        return EXPD;
    }

    public void setEXPD(String EXPD) {
        this.EXPD = EXPD;
    }

    public String getGRMT() {
        return GRMT;
    }

    public void setGRMT(String GRMT) {
        this.GRMT = GRMT;
    }

    public String getHAZI() {
        return HAZI;
    }

    public void setHAZI(String HAZI) {
        this.HAZI = HAZI;
    }

    public String getSALE() {
        return SALE;
    }

    public void setSALE(String SALE) {
        this.SALE = SALE;
    }

    public String getTAXC() {
        return TAXC;
    }

    public void setTAXC(String TAXC) {
        this.TAXC = TAXC;
    }

    public String getDS09() {
        return DS09;
    }

    public void setDS09(String DS09) {
        this.DS09 = DS09;
    }

    public String getATMO() {
        return ATMO;
    }

    public void setATMO(String ATMO) {
        this.ATMO = ATMO;
    }

    public String getATMN() {
        return ATMN;
    }

    public void setATMN(String ATMN) {
        this.ATMN = ATMN;
    }

    public String getTPLI() {
        return TPLI;
    }

    public void setTPLI(String TPLI) {
        this.TPLI = TPLI;
    }

    public String getFCU1() {
        return FCU1;
    }

    public void setFCU1(String FCU1) {
        this.FCU1 = FCU1;
    }

    public String getALUN() {
        return ALUN;
    }

    public void setALUN(String ALUN) {
        this.ALUN = ALUN;
    }

    public String getIACP() {
        return IACP;
    }

    public void setIACP(String IACP) {
        this.IACP = IACP;
    }

    public String getHDPR() {
        return HDPR;
    }

    public void setHDPR(String HDPR) {
        this.HDPR = HDPR;
    }

    public String getAAD0() {
        return AAD0;
    }

    public void setAAD0(String AAD0) {
        this.AAD0 = AAD0;
    }

    public String getAAD1() {
        return AAD1;
    }

    public void setAAD1(String AAD1) {
        this.AAD1 = AAD1;
    }

    public String getCHCL() {
        return CHCL;
    }

    public void setCHCL(String CHCL) {
        this.CHCL = CHCL;
    }

    public String getITRC() {
        return ITRC;
    }

    public void setITRC(String ITRC) {
        this.ITRC = ITRC;
    }

    public String getVTCP() {
        return VTCP;
    }

    public void setVTCP(String VTCP) {
        this.VTCP = VTCP;
    }

    public String getDS10() {
        return DS10;
    }

    public void setDS10(String DS10) {
        this.DS10 = DS10;
    }

    public String getVTCS() {
        return VTCS;
    }

    public void setVTCS(String VTCS) {
        this.VTCS = VTCS;
    }

    public String getDS11() {
        return DS11;
    }

    public void setDS11(String DS11) {
        this.DS11 = DS11;
    }

    public String getLMDT() {
        return LMDT;
    }

    public void setLMDT(String LMDT) {
        this.LMDT = LMDT;
    }

    public String getDCCD() {
        return DCCD;
    }

    public void setDCCD(String DCCD) {
        this.DCCD = DCCD;
    }

    public String getPDCC() {
        return PDCC;
    }

    public void setPDCC(String PDCC) {
        this.PDCC = PDCC;
    }

    public String getSPUN() {
        return SPUN;
    }

    public void setSPUN(String SPUN) {
        this.SPUN = SPUN;
    }

    public String getGRTI() {
        return GRTI;
    }

    public void setGRTI(String GRTI) {
        this.GRTI = GRTI;
    }

    public String getGRTS() {
        return GRTS;
    }

    public void setGRTS(String GRTS) {
        this.GRTS = GRTS;
    }

    public String getCAWP() {
        return CAWP;
    }

    public void setCAWP(String CAWP) {
        this.CAWP = CAWP;
    }

    public String getCWUN() {
        return CWUN;
    }

    public void setCWUN(String CWUN) {
        this.CWUN = CWUN;
    }

    public String getCPUN() {
        return CPUN;
    }

    public void setCPUN(String CPUN) {
        this.CPUN = CPUN;
    }

    public String getITRU() {
        return ITRU;
    }

    public void setITRU(String ITRU) {
        this.ITRU = ITRU;
    }

    public String getTECR() {
        return TECR;
    }

    public void setTECR(String TECR) {
        this.TECR = TECR;
    }

    public String getEXCA() {
        return EXCA;
    }

    public void setEXCA(String EXCA) {
        this.EXCA = EXCA;
    }

    public String getACCG() {
        return ACCG;
    }

    public void setACCG(String ACCG) {
        this.ACCG = ACCG;
    }

    public String getCCCM() {
        return CCCM;
    }

    public void setCCCM(String CCCM) {
        this.CCCM = CCCM;
    }

    public String getCCI1() {
        return CCI1;
    }

    public void setCCI1(String CCI1) {
        this.CCI1 = CCI1;
    }

    public String getCRI1() {
        return CRI1;
    }

    public void setCRI1(String CRI1) {
        this.CRI1 = CRI1;
    }

    public String getHVMT() {
        return HVMT;
    }

    public void setHVMT(String HVMT) {
        this.HVMT = HVMT;
    }

    public String getITNE() {
        return ITNE;
    }

    public void setITNE(String ITNE) {
        this.ITNE = ITNE;
    }

    public String getAUTC() {
        return AUTC;
    }

    public void setAUTC(String AUTC) {
        this.AUTC = AUTC;
    }

    public String getRIDE() {
        return RIDE;
    }

    public void setRIDE(String RIDE) {
        this.RIDE = RIDE;
    }

    public String getRIDC() {
        return RIDC;
    }

    public void setRIDC(String RIDC) {
        this.RIDC = RIDC;
    }

    public String getRNRI() {
        return RNRI;
    }

    public void setRNRI(String RNRI) {
        this.RNRI = RNRI;
    }

    public String getSAFC() {
        return SAFC;
    }

    public void setSAFC(String SAFC) {
        this.SAFC = SAFC;
    }

    public String getRMSG() {
        return RMSG;
    }

    public void setRMSG(String RMSG) {
        this.RMSG = RMSG;
    }

    public MMS200MILstItmByItmBean() {
    }

    public MMS200MILstItmByItmBean(String STAT, String ITNO, String ITDS, String FUDS, String DWNO, String RESP, String RENM, String UNMS, String DS01, String ITGR, String DS02, String ITCL, String DS03, String BUAR, String DS04, String ITTY, String DS05, String TPCD, String MABU, String CHCD, String STCD, String BACD, String VOL3, String NEWE, String GRWE, String PPUN, String DS06, String BYPR, String WAPC, String QACD, String EPCD, String POCY, String ACTI, String HIE1, String HIE2, String HIE3, String HIE4, String HIE5, String GRP1, String GRP2, String GRP3, String GRP4, String GRP5, String CFI1, String CFI2, String CFI3, String CFI4, String CFI5, String TXID, String ECMA, String PRGP, String DS07, String INDI, String PUUN, String DS08, String ALUC, String IEAA, String EXPD, String GRMT, String HAZI, String SALE, String TAXC, String DS09, String ATMO, String ATMN, String TPLI, String FCU1, String ALUN, String IACP, String HDPR, String AAD0, String AAD1, String CHCL, String ITRC, String VTCP, String DS10, String VTCS, String DS11, String LMDT, String DCCD, String PDCC, String SPUN, String GRTI, String GRTS, String CAWP, String CWUN, String CPUN, String ITRU, String TECR, String EXCA, String ACCG, String CCCM, String CCI1, String CRI1, String HVMT, String ITNE, String AUTC, String RIDE, String RIDC, String RNRI, String SAFC, String RMSG) {
        this.STAT = STAT;
        this.ITNO = ITNO;
        this.ITDS = ITDS;
        this.FUDS = FUDS;
        this.DWNO = DWNO;
        this.RESP = RESP;
        this.RENM = RENM;
        this.UNMS = UNMS;
        this.DS01 = DS01;
        this.ITGR = ITGR;
        this.DS02 = DS02;
        this.ITCL = ITCL;
        this.DS03 = DS03;
        this.BUAR = BUAR;
        this.DS04 = DS04;
        this.ITTY = ITTY;
        this.DS05 = DS05;
        this.TPCD = TPCD;
        this.MABU = MABU;
        this.CHCD = CHCD;
        this.STCD = STCD;
        this.BACD = BACD;
        this.VOL3 = VOL3;
        this.NEWE = NEWE;
        this.GRWE = GRWE;
        this.PPUN = PPUN;
        this.DS06 = DS06;
        this.BYPR = BYPR;
        this.WAPC = WAPC;
        this.QACD = QACD;
        this.EPCD = EPCD;
        this.POCY = POCY;
        this.ACTI = ACTI;
        this.HIE1 = HIE1;
        this.HIE2 = HIE2;
        this.HIE3 = HIE3;
        this.HIE4 = HIE4;
        this.HIE5 = HIE5;
        this.GRP1 = GRP1;
        this.GRP2 = GRP2;
        this.GRP3 = GRP3;
        this.GRP4 = GRP4;
        this.GRP5 = GRP5;
        this.CFI1 = CFI1;
        this.CFI2 = CFI2;
        this.CFI3 = CFI3;
        this.CFI4 = CFI4;
        this.CFI5 = CFI5;
        this.TXID = TXID;
        this.ECMA = ECMA;
        this.PRGP = PRGP;
        this.DS07 = DS07;
        this.INDI = INDI;
        this.PUUN = PUUN;
        this.DS08 = DS08;
        this.ALUC = ALUC;
        this.IEAA = IEAA;
        this.EXPD = EXPD;
        this.GRMT = GRMT;
        this.HAZI = HAZI;
        this.SALE = SALE;
        this.TAXC = TAXC;
        this.DS09 = DS09;
        this.ATMO = ATMO;
        this.ATMN = ATMN;
        this.TPLI = TPLI;
        this.FCU1 = FCU1;
        this.ALUN = ALUN;
        this.IACP = IACP;
        this.HDPR = HDPR;
        this.AAD0 = AAD0;
        this.AAD1 = AAD1;
        this.CHCL = CHCL;
        this.ITRC = ITRC;
        this.VTCP = VTCP;
        this.DS10 = DS10;
        this.VTCS = VTCS;
        this.DS11 = DS11;
        this.LMDT = LMDT;
        this.DCCD = DCCD;
        this.PDCC = PDCC;
        this.SPUN = SPUN;
        this.GRTI = GRTI;
        this.GRTS = GRTS;
        this.CAWP = CAWP;
        this.CWUN = CWUN;
        this.CPUN = CPUN;
        this.ITRU = ITRU;
        this.TECR = TECR;
        this.EXCA = EXCA;
        this.ACCG = ACCG;
        this.CCCM = CCCM;
        this.CCI1 = CCI1;
        this.CRI1 = CRI1;
        this.HVMT = HVMT;
        this.ITNE = ITNE;
        this.AUTC = AUTC;
        this.RIDE = RIDE;
        this.RIDC = RIDC;
        this.RNRI = RNRI;
        this.SAFC = SAFC;
        this.RMSG = RMSG;
    }

	@Override
	public String toString() {
		return "MMS200MILstItmByItmBean [STAT=" + STAT + ", ITNO=" + ITNO + ", ITDS=" + ITDS + ", FUDS=" + FUDS
				+ ", DWNO=" + DWNO + ", RESP=" + RESP + ", RENM=" + RENM + ", UNMS=" + UNMS + ", DS01=" + DS01
				+ ", ITGR=" + ITGR + ", DS02=" + DS02 + ", ITCL=" + ITCL + ", DS03=" + DS03 + ", BUAR=" + BUAR
				+ ", DS04=" + DS04 + ", ITTY=" + ITTY + ", DS05=" + DS05 + ", TPCD=" + TPCD + ", MABU=" + MABU
				+ ", CHCD=" + CHCD + ", STCD=" + STCD + ", BACD=" + BACD + ", VOL3=" + VOL3 + ", NEWE=" + NEWE
				+ ", GRWE=" + GRWE + ", PPUN=" + PPUN + ", DS06=" + DS06 + ", BYPR=" + BYPR + ", WAPC=" + WAPC
				+ ", QACD=" + QACD + ", EPCD=" + EPCD + ", POCY=" + POCY + ", ACTI=" + ACTI + ", HIE1=" + HIE1
				+ ", HIE2=" + HIE2 + ", HIE3=" + HIE3 + ", HIE4=" + HIE4 + ", HIE5=" + HIE5 + ", GRP1=" + GRP1
				+ ", GRP2=" + GRP2 + ", GRP3=" + GRP3 + ", GRP4=" + GRP4 + ", GRP5=" + GRP5 + ", CFI1=" + CFI1
				+ ", CFI2=" + CFI2 + ", CFI3=" + CFI3 + ", CFI4=" + CFI4 + ", CFI5=" + CFI5 + ", TXID=" + TXID
				+ ", ECMA=" + ECMA + ", PRGP=" + PRGP + ", DS07=" + DS07 + ", INDI=" + INDI + ", PUUN=" + PUUN
				+ ", DS08=" + DS08 + ", ALUC=" + ALUC + ", IEAA=" + IEAA + ", EXPD=" + EXPD + ", GRMT=" + GRMT
				+ ", HAZI=" + HAZI + ", SALE=" + SALE + ", TAXC=" + TAXC + ", DS09=" + DS09 + ", ATMO=" + ATMO
				+ ", ATMN=" + ATMN + ", TPLI=" + TPLI + ", FCU1=" + FCU1 + ", ALUN=" + ALUN + ", IACP=" + IACP
				+ ", HDPR=" + HDPR + ", AAD0=" + AAD0 + ", AAD1=" + AAD1 + ", CHCL=" + CHCL + ", ITRC=" + ITRC
				+ ", VTCP=" + VTCP + ", DS10=" + DS10 + ", VTCS=" + VTCS + ", DS11=" + DS11 + ", LMDT=" + LMDT
				+ ", DCCD=" + DCCD + ", PDCC=" + PDCC + ", SPUN=" + SPUN + ", GRTI=" + GRTI + ", GRTS=" + GRTS
				+ ", CAWP=" + CAWP + ", CWUN=" + CWUN + ", CPUN=" + CPUN + ", ITRU=" + ITRU + ", TECR=" + TECR
				+ ", EXCA=" + EXCA + ", ACCG=" + ACCG + ", CCCM=" + CCCM + ", CCI1=" + CCI1 + ", CRI1=" + CRI1
				+ ", HVMT=" + HVMT + ", ITNE=" + ITNE + ", AUTC=" + AUTC + ", RIDE=" + RIDE + ", RIDC=" + RIDC
				+ ", RNRI=" + RNRI + ", SAFC=" + SAFC + ", RMSG=" + RMSG + ", VAMT=" + VAMT + ", UCOS=" + UCOS
				+ ", ACRF=" + ACRF + ", APPR=" + APPR + ", PUPR=" + PUPR + "]";
	}

}
