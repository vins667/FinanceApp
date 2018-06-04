package shahi.Action.MI.Beans;

import java.util.List;

import shahi.Action.MI.Beans.TextBean;
import java.util.Comparator;

public class PPS200MIGetHeadBean{

    private String CONO;  //Company
    private String PUNO;  //Purchase order number
    private String DONR;  //Document number
    private String DOVA;  //Document variant
    private String DATE;  //Entry date
    private String PUDT;  //Order date
    private String DWDT;  //Requested delivery date
    private String LEDT;  //First printout date
    private String CONM;  //Company name
    private String REVN;  //Revision number
    private String RENM;  //Name of user responsible
    private String SUNO;  //Supplier number
    private String CSCD;  //Country
    private String BUYE;  //Buyer
    private String CUCD;  //Currency
    private String TXCU;  //Currency name
    private String TXPY;  //Terms of Payment name
    private String TXMO;  //Delivery Mode name
    private String TXDL;  //Terms of Delivery name
    private String TXAF;  //Terms of Freight name
    private String TXPA;  //Terms of Packaging name
    private String TXHA;  //Harbor or airport name
    private String YRE1;  //Your reference
    private String PHNO;  //Telephone number
    private String TFNO;  //Facsimile transmission number
    private String REVV;  //Revision amount
    private String REMK;  //Remark
    private String REV1;  //Amount before revision
    private String COAM;  //Total order cost
    private String DIVI;  //Division
    private String FACI;  //Facility
    private String WHLO;  //Warehouse
    private String ORTY;  //Order type
    private String LNCD;  //Language
    private String TEPY;  //Payment terms
    private String MODL;  //Delivery method
    private String TEDL;  //Delivery terms
    private String TEAF;  //Freight terms
    private String TEPA;  //Packaging terms
    private String RFID;  //Reference
    private String TEL1;  //Terms text
    private String HAFE;  //Harbor or airport
    private String POTC;  //Purchase order category
    private String PYAD;  //Our invoicing address
    private String MTDP;  //Multiple delivery addresses
    private String MTWP;  //Multiple warehouses
    private String OURR;  //Our reference number
    private String OURT;  //Reference type
    private String PRSU;  //Payee
    private String AGNT;  //Agent
    private String NTAM;  //Net order value
    private String TOQT;  //Total quantity
    private String SAAM;  //Total order cost - local currency
    private String LOCD;  //Local currency
    private String RASN;  //Rail station
    private String EXAT;  //Monitoring active
    private String FUSC;  //Monitoring activity list
    private String IAPROJ;
    private String TINNO;
    private String SLST;
    private String PUSL;
    private String PUST;
    private String TEOR;
    private String XSTF;
    private String ACRF;
    private String TOTALCHG;
    private String BACKTEXT;    
    private PPS200MIGetAddressesBean bean;
    private List LstHeadTxtdata;
    private List<TextBean> HEADTEXTLIST;
    private List LSTHEADTXTDATAB;
    private List<PPS200MILstLineBean> LSTLINEDATA;
    private List<PPS200MILstPoLineChargeBean> LSTCHARGEDATA;
    
    
    /**
     * @return the cONO
     */
    public String getCONO() {
        return CONO;
    }

    /**
     * @param cONO the cONO to set
     */
    public void setCONO(String cONO) {
        CONO = cONO;
    }

    /**
     * @return the pUNO
     */
    public String getPUNO() {
        return PUNO;
    }

    /**
     * @param pUNO the pUNO to set
     */
    public void setPUNO(String pUNO) {
        PUNO = pUNO;
    }

    /**
     * @return the dONR
     */
    public String getDONR() {
        return DONR;
    }

    /**
     * @param dONR the dONR to set
     */
    public void setDONR(String dONR) {
        DONR = dONR;
    }

    /**
     * @return the dOVA
     */
    public String getDOVA() {
        return DOVA;
    }

    /**
     * @param dOVA the dOVA to set
     */
    public void setDOVA(String dOVA) {
        DOVA = dOVA;
    }

    /**
     * @return the dATE
     */
    public String getDATE() {
        return DATE;
    }

    /**
     * @param dATE the dATE to set
     */
    public void setDATE(String dATE) {
        DATE = dATE;
    }

    /**
     * @return the pUDT
     */
    public String getPUDT() {
        return PUDT;
    }

    /**
     * @param pUDT the pUDT to set
     */
    public void setPUDT(String pUDT) {
        PUDT = pUDT;
    }

    /**
     * @return the dWDT
     */
    public String getDWDT() {
        return DWDT;
    }

    /**
     * @param dWDT the dWDT to set
     */
    public void setDWDT(String dWDT) {
        DWDT = dWDT;
    }

    /**
     * @return the lEDT
     */
    public String getLEDT() {
        return LEDT;
    }

    /**
     * @param lEDT the lEDT to set
     */
    public void setLEDT(String lEDT) {
        LEDT = lEDT;
    }

    /**
     * @return the cONM
     */
    public String getCONM() {
        return CONM;
    }

    /**
     * @param cONM the cONM to set
     */
    public void setCONM(String cONM) {
        CONM = cONM;
    }

    /**
     * @return the rEVN
     */
    public String getREVN() {
        return REVN;
    }

    /**
     * @param rEVN the rEVN to set
     */
    public void setREVN(String rEVN) {
        REVN = rEVN;
    }

    /**
     * @return the rENM
     */
    public String getRENM() {
        return RENM;
    }

    /**
     * @param rENM the rENM to set
     */
    public void setRENM(String rENM) {
        RENM = rENM;
    }

    /**
     * @return the sUNO
     */
    public String getSUNO() {
        return SUNO;
    }

    /**
     * @param sUNO the sUNO to set
     */
    public void setSUNO(String sUNO) {
        SUNO = sUNO;
    }

    /**
     * @return the cSCD
     */
    public String getCSCD() {
        return CSCD;
    }

    /**
     * @param cSCD the cSCD to set
     */
    public void setCSCD(String cSCD) {
        CSCD = cSCD;
    }

    /**
     * @return the bUYE
     */
    public String getBUYE() {
        return BUYE;
    }

    /**
     * @param bUYE the bUYE to set
     */
    public void setBUYE(String bUYE) {
        BUYE = bUYE;
    }

    /**
     * @return the cUCD
     */
    public String getCUCD() {
        return CUCD;
    }

    /**
     * @param cUCD the cUCD to set
     */
    public void setCUCD(String cUCD) {
        CUCD = cUCD;
    }

    /**
     * @return the tXCU
     */
    public String getTXCU() {
        return TXCU;
    }

    /**
     * @param tXCU the tXCU to set
     */
    public void setTXCU(String tXCU) {
        TXCU = tXCU;
    }

    /**
     * @return the tXPY
     */
    public String getTXPY() {
        return TXPY;
    }

    /**
     * @param tXPY the tXPY to set
     */
    public void setTXPY(String tXPY) {
        TXPY = tXPY;
    }

    /**
     * @return the tXMO
     */
    public String getTXMO() {
        return TXMO;
    }

    /**
     * @param tXMO the tXMO to set
     */
    public void setTXMO(String tXMO) {
        TXMO = tXMO;
    }

    /**
     * @return the tXDL
     */
    public String getTXDL() {
        return TXDL;
    }

    /**
     * @param tXDL the tXDL to set
     */
    public void setTXDL(String tXDL) {
        TXDL = tXDL;
    }

    /**
     * @return the tXAF
     */
    public String getTXAF() {
        return TXAF;
    }

    /**
     * @param tXAF the tXAF to set
     */
    public void setTXAF(String tXAF) {
        TXAF = tXAF;
    }

    /**
     * @return the tXPA
     */
    public String getTXPA() {
        return TXPA;
    }

    /**
     * @param tXPA the tXPA to set
     */
    public void setTXPA(String tXPA) {
        TXPA = tXPA;
    }

    /**
     * @return the tXHA
     */
    public String getTXHA() {
        return TXHA;
    }

    /**
     * @param tXHA the tXHA to set
     */
    public void setTXHA(String tXHA) {
        TXHA = tXHA;
    }

    /**
     * @return the yRE1
     */
    public String getYRE1() {
        return YRE1;
    }

    /**
     * @param yRE1 the yRE1 to set
     */
    public void setYRE1(String yRE1) {
        YRE1 = yRE1;
    }

    /**
     * @return the pHNO
     */
    public String getPHNO() {
        return PHNO;
    }

    /**
     * @param pHNO the pHNO to set
     */
    public void setPHNO(String pHNO) {
        PHNO = pHNO;
    }

    /**
     * @return the tFNO
     */
    public String getTFNO() {
        return TFNO;
    }

    /**
     * @param tFNO the tFNO to set
     */
    public void setTFNO(String tFNO) {
        TFNO = tFNO;
    }

    /**
     * @return the rEVV
     */
    public String getREVV() {
        return REVV;
    }

    /**
     * @param rEVV the rEVV to set
     */
    public void setREVV(String rEVV) {
        REVV = rEVV;
    }

    /**
     * @return the rEMK
     */
    public String getREMK() {
        return REMK;
    }

    /**
     * @param rEMK the rEMK to set
     */
    public void setREMK(String rEMK) {
        REMK = rEMK;
    }

    /**
     * @return the rEV1
     */
    public String getREV1() {
        return REV1;
    }

    /**
     * @param rEV1 the rEV1 to set
     */
    public void setREV1(String rEV1) {
        REV1 = rEV1;
    }

    /**
     * @return the cOAM
     */
    public String getCOAM() {
        return COAM;
    }

    /**
     * @param cOAM the cOAM to set
     */
    public void setCOAM(String cOAM) {
        COAM = cOAM;
    }

    /**
     * @return the dIVI
     */
    public String getDIVI() {
        return DIVI;
    }

    /**
     * @param dIVI the dIVI to set
     */
    public void setDIVI(String dIVI) {
        DIVI = dIVI;
    }

    /**
     * @return the fACI
     */
    public String getFACI() {
        return FACI;
    }

    /**
     * @param fACI the fACI to set
     */
    public void setFACI(String fACI) {
        FACI = fACI;
    }

    /**
     * @return the wHLO
     */
    public String getWHLO() {
        return WHLO;
    }

    /**
     * @param wHLO the wHLO to set
     */
    public void setWHLO(String wHLO) {
        WHLO = wHLO;
    }

    /**
     * @return the oRTY
     */
    public String getORTY() {
        return ORTY;
    }

    /**
     * @param oRTY the oRTY to set
     */
    public void setORTY(String oRTY) {
        ORTY = oRTY;
    }

    /**
     * @return the lNCD
     */
    public String getLNCD() {
        return LNCD;
    }

    /**
     * @param lNCD the lNCD to set
     */
    public void setLNCD(String lNCD) {
        LNCD = lNCD;
    }

    /**
     * @return the tEPY
     */
    public String getTEPY() {
        return TEPY;
    }

    /**
     * @param tEPY the tEPY to set
     */
    public void setTEPY(String tEPY) {
        TEPY = tEPY;
    }

    /**
     * @return the mODL
     */
    public String getMODL() {
        return MODL;
    }

    /**
     * @param mODL the mODL to set
     */
    public void setMODL(String mODL) {
        MODL = mODL;
    }

    /**
     * @return the tEDL
     */
    public String getTEDL() {
        return TEDL;
    }

    /**
     * @param tEDL the tEDL to set
     */
    public void setTEDL(String tEDL) {
        TEDL = tEDL;
    }

    /**
     * @return the tEAF
     */
    public String getTEAF() {
        return TEAF;
    }

    /**
     * @param tEAF the tEAF to set
     */
    public void setTEAF(String tEAF) {
        TEAF = tEAF;
    }

    /**
     * @return the tEPA
     */
    public String getTEPA() {
        return TEPA;
    }

    /**
     * @param tEPA the tEPA to set
     */
    public void setTEPA(String tEPA) {
        TEPA = tEPA;
    }

    /**
     * @return the rFID
     */
    public String getRFID() {
        return RFID;
    }

    /**
     * @param rFID the rFID to set
     */
    public void setRFID(String rFID) {
        RFID = rFID;
    }

    /**
     * @return the tEL1
     */
    public String getTEL1() {
        return TEL1;
    }

    /**
     * @param tEL1 the tEL1 to set
     */
    public void setTEL1(String tEL1) {
        TEL1 = tEL1;
    }

    /**
     * @return the hAFE
     */
    public String getHAFE() {
        return HAFE;
    }

    /**
     * @param hAFE the hAFE to set
     */
    public void setHAFE(String hAFE) {
        HAFE = hAFE;
    }

    /**
     * @return the pOTC
     */
    public String getPOTC() {
        return POTC;
    }

    /**
     * @param pOTC the pOTC to set
     */
    public void setPOTC(String pOTC) {
        POTC = pOTC;
    }

    /**
     * @return the pYAD
     */
    public String getPYAD() {
        return PYAD;
    }

    /**
     * @param pYAD the pYAD to set
     */
    public void setPYAD(String pYAD) {
        PYAD = pYAD;
    }

    /**
     * @return the mTDP
     */
    public String getMTDP() {
        return MTDP;
    }

    /**
     * @param mTDP the mTDP to set
     */
    public void setMTDP(String mTDP) {
        MTDP = mTDP;
    }

    /**
     * @return the mTWP
     */
    public String getMTWP() {
        return MTWP;
    }

    /**
     * @param mTWP the mTWP to set
     */
    public void setMTWP(String mTWP) {
        MTWP = mTWP;
    }

    /**
     * @return the oURR
     */
    public String getOURR() {
        return OURR;
    }

    /**
     * @param oURR the oURR to set
     */
    public void setOURR(String oURR) {
        OURR = oURR;
    }

    /**
     * @return the oURT
     */
    public String getOURT() {
        return OURT;
    }

    /**
     * @param oURT the oURT to set
     */
    public void setOURT(String oURT) {
        OURT = oURT;
    }

    /**
     * @return the pRSU
     */
    public String getPRSU() {
        return PRSU;
    }

    /**
     * @param pRSU the pRSU to set
     */
    public void setPRSU(String pRSU) {
        PRSU = pRSU;
    }

    /**
     * @return the aGNT
     */
    public String getAGNT() {
        return AGNT;
    }

    /**
     * @param aGNT the aGNT to set
     */
    public void setAGNT(String aGNT) {
        AGNT = aGNT;
    }

    /**
     * @return the nTAM
     */
    public String getNTAM() {
        return NTAM;
    }

    /**
     * @param nTAM the nTAM to set
     */
    public void setNTAM(String nTAM) {
        NTAM = nTAM;
    }

    /**
     * @return the tOQT
     */
    public String getTOQT() {
        return TOQT;
    }

    /**
     * @param tOQT the tOQT to set
     */
    public void setTOQT(String tOQT) {
        TOQT = tOQT;
    }

    /**
     * @return the sAAM
     */
    public String getSAAM() {
        return SAAM;
    }

    /**
     * @param sAAM the sAAM to set
     */
    public void setSAAM(String sAAM) {
        SAAM = sAAM;
    }

    /**
     * @return the lOCD
     */
    public String getLOCD() {
        return LOCD;
    }

    /**
     * @param lOCD the lOCD to set
     */
    public void setLOCD(String lOCD) {
        LOCD = lOCD;
    }

    /**
     * @return the rASN
     */
    public String getRASN() {
        return RASN;
    }

    /**
     * @param rASN the rASN to set
     */
    public void setRASN(String rASN) {
        RASN = rASN;
    }

    /**
     * @return the eXAT
     */
    public String getEXAT() {
        return EXAT;
    }

    /**
     * @param eXAT the eXAT to set
     */
    public void setEXAT(String eXAT) {
        EXAT = eXAT;
    }

    /**
     * @return the fUSC
     */
    public String getFUSC() {
        return FUSC;
    }

    /**
     * @param fUSC the fUSC to set
     */
    public void setFUSC(String fUSC) {
        FUSC = fUSC;
    }

    /**
     * @return the iAPROJ
     */
    public String getIAPROJ() {
        return IAPROJ;
    }

    /**
     * @param iAPROJ the iAPROJ to set
     */
    public void setIAPROJ(String iAPROJ) {
        IAPROJ = iAPROJ;
    }

    /**
     * @return the tINNO
     */
    public String getTINNO() {
        return TINNO;
    }

    /**
     * @param tINNO the tINNO to set
     */
    public void setTINNO(String tINNO) {
        TINNO = tINNO;
    }

    /**
     * @return the sLST
     */
    public String getSLST() {
        return SLST;
    }

    /**
     * @param sLST the sLST to set
     */
    public void setSLST(String sLST) {
        SLST = sLST;
    }

    /**
     * @return the pUSL
     */
    public String getPUSL() {
        return PUSL;
    }

    /**
     * @param pUSL the pUSL to set
     */
    public void setPUSL(String pUSL) {
        PUSL = pUSL;
    }

    /**
     * @return the pUST
     */
    public String getPUST() {
        return PUST;
    }

    /**
     * @param pUST the pUST to set
     */
    public void setPUST(String pUST) {
        PUST = pUST;
    }

    /**
     * @return the tEOR
     */
    public String getTEOR() {
        return TEOR;
    }

    /**
     * @param tEOR the tEOR to set
     */
    public void setTEOR(String tEOR) {
        TEOR = tEOR;
    }

    /**
     * @return the xSTF
     */
    public String getXSTF() {
        return XSTF;
    }

    /**
     * @param xSTF the xSTF to set
     */
    public void setXSTF(String xSTF) {
        XSTF = xSTF;
    }

    /**
     * @return the aCRF
     */
    public String getACRF() {
        return ACRF;
    }

    /**
     * @param aCRF the aCRF to set
     */
    public void setACRF(String aCRF) {
        ACRF = aCRF;
    }

    /**
     * @return the tOTALCHG
     */
    public String getTOTALCHG() {
        return TOTALCHG;
    }

    /**
     * @param tOTALCHG the tOTALCHG to set
     */
    public void setTOTALCHG(String tOTALCHG) {
        TOTALCHG = tOTALCHG;
    }

    /**
     * @return the bean
     */
    public PPS200MIGetAddressesBean getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(PPS200MIGetAddressesBean bean) {
        this.bean = bean;
    }

    /**
     * @return the lstHeadTxtdata
     */
    public List getLstHeadTxtdata() {
        return LstHeadTxtdata;
    }

    /**
     * @param lstHeadTxtdata the lstHeadTxtdata to set
     */
    public void setLstHeadTxtdata(List lstHeadTxtdata) {
        LstHeadTxtdata = lstHeadTxtdata;
    }

    /**
     * @return the hEADTEXTLIST
     */
    public List<TextBean> getHEADTEXTLIST() {
        return HEADTEXTLIST;
    }

    /**
     * @param hEADTEXTLIST the hEADTEXTLIST to set
     */
    public void setHEADTEXTLIST(List<TextBean> hEADTEXTLIST) {
        HEADTEXTLIST = hEADTEXTLIST;
    }

    /**
     * @return the lSTHEADTXTDATAB
     */
    public List getLSTHEADTXTDATAB() {
        return LSTHEADTXTDATAB;
    }

    /**
     * @param lSTHEADTXTDATAB the lSTHEADTXTDATAB to set
     */
    public void setLSTHEADTXTDATAB(List lSTHEADTXTDATAB) {
        LSTHEADTXTDATAB = lSTHEADTXTDATAB;
    }

    /**
     * @return the lSTLINEDATA
     */
    public List<PPS200MILstLineBean> getLSTLINEDATA() {
        return LSTLINEDATA;
    }

    /**
     * @param lSTLINEDATA the lSTLINEDATA to set
     */
    public void setLSTLINEDATA(List<PPS200MILstLineBean> lSTLINEDATA) {
        LSTLINEDATA = lSTLINEDATA;
    }

    /**
     * @return the lSTCHARGEDATA
     */
    public List<PPS200MILstPoLineChargeBean> getLSTCHARGEDATA() {
        return LSTCHARGEDATA;
    }

    /**
     * @param lSTCHARGEDATA the lSTCHARGEDATA to set
     */
    public void setLSTCHARGEDATA(List<PPS200MILstPoLineChargeBean> lSTCHARGEDATA) {
        LSTCHARGEDATA = lSTCHARGEDATA;
    }

    /**
     * @return the bACKTEXT
     */
    public String getBACKTEXT() {
        return BACKTEXT;
    }

    /**
     * @param bACKTEXT the bACKTEXT to set
     */
    public void setBACKTEXT(String bACKTEXT) {
        BACKTEXT = bACKTEXT;
    }    
}
