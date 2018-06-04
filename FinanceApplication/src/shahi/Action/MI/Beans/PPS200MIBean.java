/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;

import java.util.List;

import shahi.Action.CRMAPP.Beans.TextBean;

/**
 *
 * @author Ranjeet Singh
 */
public class PPS200MIBean {
 
private String	CONO	;                     //	Company
private String	PUNO	;                     //	Purchase order number
private String	DONR	;                     //	Document number
private String	DOVA	;                     //	Document variant
private String	DATE	;                     //	Entry date
private String	PUDT	;                     //	Order date
private String	DWDT	;                     //	Requested delivery date
private String	LEDT	;                     //	First printout date
private String	CONM	;                     //	Company name
private String	REVN	;                     //	Revision number
private String	RENM	;                     //	Name of user responsible
private String	SUNO	;                     //	Supplier number
private String	CSCD	;                     //	Country
private String	BUYE	;                     //	Buyer
private String	CUCD	;                     //	Currency
private String	TXCU	;                     //	Currency name
private String	TXPY	;                     //	Terms of Payment name
private String	TXMO	;                     //	Delivery Mode name
private String	TXDL	;                     //	Terms of Delivery name
private String	TXAF	;                     //	Terms of Freight name
private String	TXPA	;                     //	Terms of Packaging name
private String	TXHA	;                     //	Harbor or airport name
private String	YRE1	;                     //	Your reference
private String	PHNO	;                     //	Telephone number
private String	TFNO	;                     //	Facsimile transmission number
private String	REVV	;                     //	Revision amount
			
  
   
private String REMK;    //Remark	   
private String REV1;    //Amount before revision	   
private String COAM;    //     //Total order cost	   
private String DIVI;    ////Division	   
private String FACI;    //Facility	   
private String WHLO;    //Warehouse	   
private String ORTY;    //Order type	   
private String LNCD;    //Language	   
private String TEPY;    //Payment terms	   
private String MODL;    //Delivery method	   
private String TEDL;    //Delivery terms	   
private String TEAF;    //Freight terms	   
private String TEPA;    //Packaging terms	   
private String RFID;    //Reference	   
private String TEL1;    //Terms text	   
private String HAFE;    //Harbor or airport	   
private String POTC;    //Purchase order category	   
private String PYAD;    //Our invoicing address	   
private String MTDP;    //Multiple delivery addresses	   
private String MTWP;    //Multiple warehouses	   
private String OURR;    //Our reference number	   
private String OURT;    //Reference type	   
private String PRSU;    //Payee	   
private String AGNT;    //Agent	   
private String XSTY;    //Sales Tax Type	   
private String XSTF;    //Sales Tax Form	   
private String PUSL;    //Lowest Status	 
private String TEOR;
private PPS200MIGetAddressesBean bean;
private List  LstLinedata;
private List LstHeadTxtdata;
private List LstHeadTxtdataB;

private String BACKTEXT;
private String PCH;
private String LST;
private String CST;
private String TINNO;
private String QUOTNO;
private String TOTALCHG;

private String HEADTEXTNEW;
private List<TextBean> HEADTEXTLIST;
private String IAPROJ;
private String SLST;
private List  LstChargedata;

    public PPS200MIBean(String CONO, String PUNO, String DONR, String DOVA, String DATE, String PUDT, String DWDT, String LEDT, String CONM, String REVN, String RENM, String SUNO, String CSCD, String BUYE, String CUCD, String TXCU, String TXPY, String TXMO, String TXDL, String TXAF, String TXPA, String TXHA, String YRE1, String PHNO, String TFNO, String REVV, String REMK, String REV1, String COAM, String DIVI, String FACI, String WHLO, String ORTY, String LNCD, String TEPY, String MODL, String TEDL, String TEAF, String TEPA, String RFID, String TEL1, String HAFE, String POTC, String PYAD, String MTDP, String MTWP, String OURR, String OURT, String PRSU, String AGNT, String XSTY, String XSTF, String PUSL,String TEOR) {
        this.CONO = CONO;
        this.PUNO = PUNO;
        this.DONR = DONR;
        this.DOVA = DOVA;
        this.DATE = DATE;
        this.PUDT = PUDT;
        this.DWDT = DWDT;
        this.LEDT = LEDT;
        this.CONM = CONM;
        this.REVN = REVN;
        this.RENM = RENM;
        this.SUNO = SUNO;
        this.CSCD = CSCD;
        this.BUYE = BUYE;
        this.CUCD = CUCD;
        this.TXCU = TXCU;
        this.TXPY = TXPY;
        this.TXMO = TXMO;
        this.TXDL = TXDL;
        this.TXAF = TXAF;
        this.TXPA = TXPA;
        this.TXHA = TXHA;
        this.YRE1 = YRE1;
        this.PHNO = PHNO;
        this.TFNO = TFNO;
        this.REVV = REVV;
        this.REMK = REMK;
        this.REV1 = REV1;
        this.COAM = COAM;
        this.DIVI = DIVI;
        this.FACI = FACI;
        this.WHLO = WHLO;
        this.ORTY = ORTY;
        this.LNCD = LNCD;
        this.TEPY = TEPY;
        this.MODL = MODL;
        this.TEDL = TEDL;
        this.TEAF = TEAF;
        this.TEPA = TEPA;
        this.RFID = RFID;
        this.TEL1 = TEL1;
        this.HAFE = HAFE;
        this.POTC = POTC;
        this.PYAD = PYAD;
        this.MTDP = MTDP;
        this.MTWP = MTWP;
        this.OURR = OURR;
        this.OURT = OURT;
        this.PRSU = PRSU;
        this.AGNT = AGNT;
        this.XSTY = XSTY;
        this.XSTF = XSTF;
        this.PUSL = PUSL;
        this.TEOR=TEOR;
    }






    public PPS200MIBean(String REMK, String REV1, String COAM, String DIVI, String FACI, String WHLO, String ORTY, String LNCD, String TEPY, String MODL, String TEDL, String TEAF, String TEPA, String RFID, String TEL1, String HAFE, String POTC, String PYAD, String MTDP, String MTWP, String OURR, String OURT, String PRSU, String AGNT, String XSTY, String XSTF, String PUSL) {
        this.REMK = REMK;
        this.REV1 = REV1;
        this.COAM = COAM;
        this.DIVI = DIVI;
        this.FACI = FACI;
        this.WHLO = WHLO;
        this.ORTY = ORTY;
        this.LNCD = LNCD;
        this.TEPY = TEPY;
        this.MODL = MODL;
        this.TEDL = TEDL;
        this.TEAF = TEAF;
        this.TEPA = TEPA;
        this.RFID = RFID;
        this.TEL1 = TEL1;
        this.HAFE = HAFE;
        this.POTC = POTC;
        this.PYAD = PYAD;
        this.MTDP = MTDP;
        this.MTWP = MTWP;
        this.OURR = OURR;
        this.OURT = OURT;
        this.PRSU = PRSU;
        this.AGNT = AGNT;
        this.XSTY = XSTY;
        this.XSTF = XSTF;
        this.PUSL = PUSL;
    }

    public String getBUYE() {
        return BUYE;
    }

    public void setBUYE(String BUYE) {
        this.BUYE = BUYE;
    }

    public String getCONM() {
        return CONM;
    }

    public void setCONM(String CONM) {
        this.CONM = CONM;
    }

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getCSCD() {
        return CSCD;
    }

    public void setCSCD(String CSCD) {
        this.CSCD = CSCD;
    }

    public String getCUCD() {
        return CUCD;
    }

    public void setCUCD(String CUCD) {
        this.CUCD = CUCD;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getDONR() {
        return DONR;
    }

    public void setDONR(String DONR) {
        this.DONR = DONR;
    }

    public String getDOVA() {
        return DOVA;
    }

    public void setDOVA(String DOVA) {
        this.DOVA = DOVA;
    }

    public String getDWDT() {
        return DWDT;
    }

    public void setDWDT(String DWDT) {
        this.DWDT = DWDT;
    }

    public String getLEDT() {
        return LEDT;
    }

    public void setLEDT(String LEDT) {
        this.LEDT = LEDT;
    }

    public String getPHNO() {
        return PHNO;
    }

    public void setPHNO(String PHNO) {
        this.PHNO = PHNO;
    }

    public String getPUDT() {
        return PUDT;
    }

    public void setPUDT(String PUDT) {
        this.PUDT = PUDT;
    }

    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public String getRENM() {
        return RENM;
    }

    public void setRENM(String RENM) {
        this.RENM = RENM;
    }

    public String getREVN() {
        return REVN;
    }

    public void setREVN(String REVN) {
        this.REVN = REVN;
    }

    public String getREVV() {
        return REVV;
    }

    public void setREVV(String REVV) {
        this.REVV = REVV;
    }

    public String getSUNO() {
        return SUNO;
    }

    public void setSUNO(String SUNO) {
        this.SUNO = SUNO;
    }

    public String getTFNO() {
        return TFNO;
    }

    public void setTFNO(String TFNO) {
        this.TFNO = TFNO;
    }

    public String getTXAF() {
        return TXAF;
    }

    public void setTXAF(String TXAF) {
        this.TXAF = TXAF;
    }

    public String getTXCU() {
        return TXCU;
    }

    public void setTXCU(String TXCU) {
        this.TXCU = TXCU;
    }

    public String getTXDL() {
        return TXDL;
    }

    public void setTXDL(String TXDL) {
        this.TXDL = TXDL;
    }

    public String getTXHA() {
        return TXHA;
    }

    public void setTXHA(String TXHA) {
        this.TXHA = TXHA;
    }

    public String getTXMO() {
        return TXMO;
    }

    public void setTXMO(String TXMO) {
        this.TXMO = TXMO;
    }

    public String getTXPA() {
        return TXPA;
    }

    public void setTXPA(String TXPA) {
        this.TXPA = TXPA;
    }

    public String getTXPY() {
        return TXPY;
    }

    public void setTXPY(String TXPY) {
        this.TXPY = TXPY;
    }

    public String getYRE1() {
        return YRE1;
    }

    public void setYRE1(String YRE1) {
        this.YRE1 = YRE1;
    }



    
    



    public String getAGNT() {
        return AGNT;
    }

    public void setAGNT(String AGNT) {
        this.AGNT = AGNT;
    }

    public String getCOAM() {
        return COAM;
    }

    public void setCOAM(String COAM) {
        this.COAM = COAM;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getFACI() {
        return FACI;
    }

    public void setFACI(String FACI) {
        this.FACI = FACI;
    }

    public String getHAFE() {
        return HAFE;
    }

    public void setHAFE(String HAFE) {
        this.HAFE = HAFE;
    }

    public String getLNCD() {
        return LNCD;
    }

    public void setLNCD(String LNCD) {
        this.LNCD = LNCD;
    }

    public String getMODL() {
        return MODL;
    }

    public void setMODL(String MODL) {
        this.MODL = MODL;
    }

    public String getMTDP() {
        return MTDP;
    }

    public void setMTDP(String MTDP) {
        this.MTDP = MTDP;
    }

    public String getMTWP() {
        return MTWP;
    }

    public void setMTWP(String MTWP) {
        this.MTWP = MTWP;
    }

    public String getORTY() {
        return ORTY;
    }

    public void setORTY(String ORTY) {
        this.ORTY = ORTY;
    }

    public String getOURR() {
        return OURR;
    }

    public void setOURR(String OURR) {
        this.OURR = OURR;
    }

    public String getOURT() {
        return OURT;
    }

    public void setOURT(String OURT) {
        this.OURT = OURT;
    }

    public String getPOTC() {
        return POTC;
    }

    public void setPOTC(String POTC) {
        this.POTC = POTC;
    }

    public String getPRSU() {
        return PRSU;
    }

    public void setPRSU(String PRSU) {
        this.PRSU = PRSU;
    }

    public String getPUSL() {
        return PUSL;
    }

    public void setPUSL(String PUSL) {
        this.PUSL = PUSL;
    }

    public String getPYAD() {
        return PYAD;
    }

    public void setPYAD(String PYAD) {
        this.PYAD = PYAD;
    }

    public String getREMK() {
        return REMK;
    }

    public void setREMK(String REMK) {
        this.REMK = REMK;
    }

    public String getREV1() {
        return REV1;
    }

    public void setREV1(String REV1) {
        this.REV1 = REV1;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getTEAF() {
        return TEAF;
    }

    public void setTEAF(String TEAF) {
        this.TEAF = TEAF;
    }

    public String getTEDL() {
        return TEDL;
    }

    public void setTEDL(String TEDL) {
        this.TEDL = TEDL;
    }

    public String getTEL1() {
        return TEL1;
    }

    public void setTEL1(String TEL1) {
        this.TEL1 = TEL1;
    }

    public String getTEPA() {
        return TEPA;
    }

    public void setTEPA(String TEPA) {
        this.TEPA = TEPA;
    }

    public String getTEPY() {
        return TEPY;
    }

    public void setTEPY(String TEPY) {
        this.TEPY = TEPY;
    }

    public String getWHLO() {
        return WHLO;
    }

    public void setWHLO(String WHLO) {
        this.WHLO = WHLO;
    }

    public String getXSTF() {
        return XSTF;
    }

    public void setXSTF(String XSTF) {
        this.XSTF = XSTF;
    }

    public String getXSTY() {
        return XSTY;
    }

    public void setXSTY(String XSTY) {
        this.XSTY = XSTY;
    }

    public List getLstLinedata() {
        return LstLinedata;
    }

    public void setLstLinedata(List LstLinedata) {
        this.LstLinedata = LstLinedata;
    }

    

    public PPS200MIGetAddressesBean getBean() {
        return bean;
    }

    public void setBean(PPS200MIGetAddressesBean bean) {
        this.bean = bean;
    }

    public List getLstHeadTxtdata() {
        return LstHeadTxtdata;
    }

    public void setLstHeadTxtdata(List LstHeadTxtdata) {
        this.LstHeadTxtdata = LstHeadTxtdata;
    }

    public List getLstHeadTxtdataB() {
        return LstHeadTxtdataB;
    }

    public void setLstHeadTxtdataB(List LstHeadTxtdataB) {
        this.LstHeadTxtdataB = LstHeadTxtdataB;
    }

    public String getBACKTEXT() {
        return BACKTEXT;
    }

    public void setBACKTEXT(String BACKTEXT) {
        this.BACKTEXT = BACKTEXT;
    }

    public String getTEOR() {
        return TEOR;
    }

    public void setTEOR(String TEOR) {
        this.TEOR = TEOR;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getCST() {
        return CST;
    }

    public void setCST(String CST) {
        this.CST = CST;
    }

    public String getLST() {
        return LST;
    }

    public void setLST(String LST) {
        this.LST = LST;
    }

    public String getTINNO() {
        return TINNO;
    }

    public void setTINNO(String TINNO) {
        this.TINNO = TINNO;
    }

    public String getQUOTNO() {
        return QUOTNO;
    }

    public void setQUOTNO(String QUOTNO) {
        this.QUOTNO = QUOTNO;
    }

    public String getTOTALCHG() {
        return TOTALCHG;
    }

    public void setTOTALCHG(String TOTALCHG) {
        this.TOTALCHG = TOTALCHG;
    }

    public String getHEADTEXTNEW() {
        return HEADTEXTNEW;
    }

    public void setHEADTEXTNEW(String HEADTEXTNEW) {
        this.HEADTEXTNEW = HEADTEXTNEW;
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

    public String getSLST() {
        return SLST;
    }

    public void setSLST(String SLST) {
        this.SLST = SLST;
    }

    public List getLstChargedata() {
        return LstChargedata;
    }

    public void setLstChargedata(List LstChargedata) {
        this.LstChargedata = LstChargedata;
    }

   


}
