/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MI.Beans;
import java.util.List;

/**
 *
 * @author Ranjeet Singh
 */
public class PPS200MILstLineBean {
private String	CONO	;                 //	Company	
private String	PUNO	;                 //	Purchase order number	
private String	PNLI	;                 //	Purchase order line	
private String	PNLS	;                 //	Purchase order line subnumber	
private String	ITNO	;                 //	Item number	
private String	SITE	;                 //	Supplier item number	
private String	CODT	;                 //	Confirmed delivery date	
private String	CFQA	;                 //	Confirmed quantity	
private String	PUUN	;                 //	Purchase order U/M	
private String	ITDS	;                 //	Name	
private String	EACD	;                 //	EAN code	
private String	FACI	;                 //	Facility	
private String	WHLO	;                 //	Warehouse	
private String	POTC	;                 //	Purchase order category	
private String	PUST	;                 //	Highest status - purchase order	
private String	PUSL	;                 //	Lowest status - purchase order	
private String	SUNO	;                 //	Supplier number	
private String	PRCS	;                 //	Service process	
private String	SUFI	;                 //	Service	
private String	PITD	;                 //	Purchase order item name	
private String	PITT	;                 //	Purchase order item description	
private String	SORN	;                 //	Supplier order number	
private String	PUPR	;                 //	Purchase price	
private String	ODI1	;                 //	Discount 1	
private String	ODI2	;                 //	Discount 2	
private String	ODI3	;                 //	Discount 3	
private String	CPPR	;                 //	Confirmed purchase price	
private String	CFD1	;                 //	Confirmed discount 1	
private String	CFD2	;                 //	Confirmed discount 2	
private String	CFD3	;                 //	Confirmed discount 3	
private String	PPUN	;                 //	Purchase price U/M	
private String	PUCD	;                 //	Purchase price quantity	
private String	CPUC	;                 //	Confirmed purchase price quantity	
private String	LNAM	;                 //	Line amount - order currency	
private String	PTCD	;                 //	Purchase price text	
private String	DWDT	;                 //	Requested delivery date	
private String	ORQA	;                 //	Ordered quantity - alternate U/M	
private String	ADQA	;                 //	Advised quantity alternate U/M	
private String	TNQA	;                 //	Transport notified quantity	
private String	RVQA	;                 //	Received quantity	
private String	RCDT	;                 //	Receipt date	
private String	CAQA	;                 //	Approved quantity - alternate U/M	
private String	RJQA	;                 //	Rejected quantity	
private String	SDQA	;                 //	Stored quantity	
private String	IVQA	;                 //	Invoiced quantity - alternate U/M	
private String	IDAT	;                 //	Invoice date	
private String	PLPN	;                 //	Order proposal number	
private String	PLPS	;                 //	Subnumber - order proposal	
private String	PURC	;                 //	Requisition by	
private String	BUYE	;                 //	Buyer	
private String	GRMT	;                 //	Goods receiving method	
private String	PACT	;                 //	Packaging	
private String	TXID	;                 //	Text identity	
private String	ECVE	;                 //	Revision number	
private String	OURR	;                 //	Our reference number	
private String	OURT	;                 //	Reference type	
private String	VTCD	;                 //	VAT code	
private String	ATNR	;                 //	Attribute number	
private String	RORC	;                 //	Reference order category	
private String	RORN	;                 //	Reference order number	
private String	RORL	;                 //	Reference order line	
private String	RORX	;                 //	Line suffix	
private String	PRIP	;                 //	Priority	
private String	IRCV	;                 //	Recipient	
private String	PROD	;                 //	Manufacturer	
private String	SDPC	;                 //	Discount	
private String	EXEP	;                 //	External Charges	
private String	INEP	;                 //	Internal Charges
private String TEMP;						// Temp add for costing LOCK Check
private List PoLineCharge;
private List LstLineTxtlist;
PPS200MILstLineTxtBean text60;
List<PPS200MILstLineTxtBean> text60list;
private String UPAV;
private String ACRF;
private String FABDESC;
private String PRGP;



    public PPS200MILstLineBean() {
    }



    public PPS200MILstLineBean(String CONO, String PUNO, String PNLI, String PNLS, String ITNO, String SITE, String CODT, String CFQA, String PUUN, String ITDS, String EACD, String FACI, String WHLO, String POTC, String PUST, String PUSL, String SUNO, String PRCS, String SUFI, String PITD, String PITT, String SORN, String PUPR, String ODI1, String ODI2, String ODI3, String CPPR, String CFD1, String CFD2, String CFD3, String PPUN, String PUCD, String CPUC, String LNAM, String PTCD, String DWDT, String ORQA, String ADQA, String TNQA, String RVQA, String RCDT, String CAQA, String RJQA, String SDQA, String IVQA, String IDAT, String PLPN, String PLPS, String PURC, String BUYE, String GRMT, String PACT, String TXID, String ECVE, String OURR, String OURT, String VTCD, String ATNR, String RORC, String RORN, String RORL, String RORX, 
            String PRIP, String IRCV, String PROD, String SDPC, String EXEP, String INEP,List PoLineCharge,List LstLineTxtlist) {
        this.CONO = CONO;
        this.PUNO = PUNO;
        this.PNLI = PNLI;
        this.PNLS = PNLS;
        this.ITNO = ITNO;
        this.SITE = SITE;
        this.CODT = CODT;
        this.CFQA = CFQA;
        this.PUUN = PUUN;
        this.ITDS = ITDS;
        this.EACD = EACD;
        this.FACI = FACI;
        this.WHLO = WHLO;
        this.POTC = POTC;
        this.PUST = PUST;
        this.PUSL = PUSL;
        this.SUNO = SUNO;
        this.PRCS = PRCS;
        this.SUFI = SUFI;
        this.PITD = PITD;
        this.PITT = PITT;
        this.SORN = SORN;
        this.PUPR = PUPR;
        this.ODI1 = ODI1;
        this.ODI2 = ODI2;
        this.ODI3 = ODI3;
        this.CPPR = CPPR;
        this.CFD1 = CFD1;
        this.CFD2 = CFD2;
        this.CFD3 = CFD3;
        this.PPUN = PPUN;
        this.PUCD = PUCD;
        this.CPUC = CPUC;
        this.LNAM = LNAM;
        this.PTCD = PTCD;
        this.DWDT = DWDT;
        this.ORQA = ORQA;
        this.ADQA = ADQA;
        this.TNQA = TNQA;
        this.RVQA = RVQA;
        this.RCDT = RCDT;
        this.CAQA = CAQA;
        this.RJQA = RJQA;
        this.SDQA = SDQA;
        this.IVQA = IVQA;
        this.IDAT = IDAT;
        this.PLPN = PLPN;
        this.PLPS = PLPS;
        this.PURC = PURC;
        this.BUYE = BUYE;
        this.GRMT = GRMT;
        this.PACT = PACT;
        this.TXID = TXID;
        this.ECVE = ECVE;
        this.OURR = OURR;
        this.OURT = OURT;
        this.VTCD = VTCD;
        this.ATNR = ATNR;
        this.RORC = RORC;
        this.RORN = RORN;
        this.RORL = RORL;
        this.RORX = RORX;
        this.PRIP = PRIP;
        this.IRCV = IRCV;
        this.PROD = PROD;
        this.SDPC = SDPC;
        this.EXEP = EXEP;
        this.INEP = INEP;
        this.PoLineCharge=PoLineCharge;
         this.LstLineTxtlist=LstLineTxtlist;
    }


public PPS200MILstLineBean(String CONO, String PUNO, String PNLI, String PNLS, String ITNO, String SITE, String CODT, String CFQA, String PUUN, String ITDS, String EACD, String FACI, String WHLO, String POTC, String PUST, String PUSL, String SUNO, String PRCS, String SUFI, String PITD, String PITT, String SORN, String PUPR, String ODI1, String ODI2, String ODI3, String CPPR, String CFD1, String CFD2, String CFD3, String PPUN, String PUCD, String CPUC, String LNAM, String PTCD, String DWDT, String ORQA, String ADQA, String TNQA, String RVQA, String RCDT, String CAQA, String RJQA, String SDQA, String IVQA, String IDAT, String PLPN, String PLPS, String PURC, String BUYE, String GRMT, String PACT, String TXID, String ECVE, String OURR, String OURT, String VTCD, String ATNR, String RORC, String RORN, String RORL, String RORX, 
            String PRIP, String IRCV, String PROD, String SDPC, String EXEP, String INEP) {
        this.CONO = CONO;
        this.PUNO = PUNO;
        this.PNLI = PNLI;
        this.PNLS = PNLS;
        this.ITNO = ITNO;
        this.SITE = SITE;
        this.CODT = CODT;
        this.CFQA = CFQA;
        this.PUUN = PUUN;
        this.ITDS = ITDS;
        this.EACD = EACD;
        this.FACI = FACI;
        this.WHLO = WHLO;
        this.POTC = POTC;
        this.PUST = PUST;
        this.PUSL = PUSL;
        this.SUNO = SUNO;
        this.PRCS = PRCS;
        this.SUFI = SUFI;
        this.PITD = PITD;
        this.PITT = PITT;
        this.SORN = SORN;
        this.PUPR = PUPR;
        this.ODI1 = ODI1;
        this.ODI2 = ODI2;
        this.ODI3 = ODI3;
        this.CPPR = CPPR;
        this.CFD1 = CFD1;
        this.CFD2 = CFD2;
        this.CFD3 = CFD3;
        this.PPUN = PPUN;
        this.PUCD = PUCD;
        this.CPUC = CPUC;
        this.LNAM = LNAM;
        this.PTCD = PTCD;
        this.DWDT = DWDT;
        this.ORQA = ORQA;
        this.ADQA = ADQA;
        this.TNQA = TNQA;
        this.RVQA = RVQA;
        this.RCDT = RCDT;
        this.CAQA = CAQA;
        this.RJQA = RJQA;
        this.SDQA = SDQA;
        this.IVQA = IVQA;
        this.IDAT = IDAT;
        this.PLPN = PLPN;
        this.PLPS = PLPS;
        this.PURC = PURC;
        this.BUYE = BUYE;
        this.GRMT = GRMT;
        this.PACT = PACT;
        this.TXID = TXID;
        this.ECVE = ECVE;
        this.OURR = OURR;
        this.OURT = OURT;
        this.VTCD = VTCD;
        this.ATNR = ATNR;
        this.RORC = RORC;
        this.RORN = RORN;
        this.RORL = RORL;
        this.RORX = RORX;
        this.PRIP = PRIP;
        this.IRCV = IRCV;
        this.PROD = PROD;
        this.SDPC = SDPC;
        this.EXEP = EXEP;
        this.INEP = INEP;
       
    }





    public String getADQA() {
        return ADQA;
    }

    public void setADQA(String ADQA) {
        this.ADQA = ADQA;
    }

    public String getATNR() {
        return ATNR;
    }

    public void setATNR(String ATNR) {
        this.ATNR = ATNR;
    }

    public String getBUYE() {
        return BUYE;
    }

    public void setBUYE(String BUYE) {
        this.BUYE = BUYE;
    }

    public String getCAQA() {
        return CAQA;
    }

    public void setCAQA(String CAQA) {
        this.CAQA = CAQA;
    }

    public String getCFD1() {
        return CFD1;
    }

    public void setCFD1(String CFD1) {
        this.CFD1 = CFD1;
    }

    public String getCFD2() {
        return CFD2;
    }

    public void setCFD2(String CFD2) {
        this.CFD2 = CFD2;
    }

    public String getCFD3() {
        return CFD3;
    }

    public void setCFD3(String CFD3) {
        this.CFD3 = CFD3;
    }

    public String getCFQA() {
        return CFQA;
    }

    public void setCFQA(String CFQA) {
        this.CFQA = CFQA;
    }

    public String getCODT() {
        return CODT;
    }

    public void setCODT(String CODT) {
        this.CODT = CODT;
    }

    public String getCONO() {
        return CONO;
    }

    public void setCONO(String CONO) {
        this.CONO = CONO;
    }

    public String getCPPR() {
        return CPPR;
    }

    public void setCPPR(String CPPR) {
        this.CPPR = CPPR;
    }

    public String getCPUC() {
        return CPUC;
    }

    public void setCPUC(String CPUC) {
        this.CPUC = CPUC;
    }

    public String getDWDT() {
        return DWDT;
    }

    public void setDWDT(String DWDT) {
        this.DWDT = DWDT;
    }

    public String getEACD() {
        return EACD;
    }

    public void setEACD(String EACD) {
        this.EACD = EACD;
    }

    public String getECVE() {
        return ECVE;
    }

    public void setECVE(String ECVE) {
        this.ECVE = ECVE;
    }

    public String getEXEP() {
        return EXEP;
    }

    public void setEXEP(String EXEP) {
        this.EXEP = EXEP;
    }

    public String getFACI() {
        return FACI;
    }

    public void setFACI(String FACI) {
        this.FACI = FACI;
    }

    public String getGRMT() {
        return GRMT;
    }

    public void setGRMT(String GRMT) {
        this.GRMT = GRMT;
    }

    public String getIDAT() {
        return IDAT;
    }

    public void setIDAT(String IDAT) {
        this.IDAT = IDAT;
    }

    public String getINEP() {
        return INEP;
    }

    public void setINEP(String INEP) {
        this.INEP = INEP;
    }

    public String getIRCV() {
        return IRCV;
    }

    public void setIRCV(String IRCV) {
        this.IRCV = IRCV;
    }

    public String getITDS() {
        return ITDS;
    }

    public void setITDS(String ITDS) {
        this.ITDS = ITDS;
    }

    public String getITNO() {
        return ITNO;
    }

    public void setITNO(String ITNO) {
        this.ITNO = ITNO;
    }

    public String getIVQA() {
        return IVQA;
    }

    public void setIVQA(String IVQA) {
        this.IVQA = IVQA;
    }

    public String getLNAM() {
        return LNAM;
    }

    public void setLNAM(String LNAM) {
        this.LNAM = LNAM;
    }

    public String getODI1() {
        return ODI1;
    }

    public void setODI1(String ODI1) {
        this.ODI1 = ODI1;
    }

    public String getODI2() {
        return ODI2;
    }

    public void setODI2(String ODI2) {
        this.ODI2 = ODI2;
    }

    public String getODI3() {
        return ODI3;
    }

    public void setODI3(String ODI3) {
        this.ODI3 = ODI3;
    }

    public String getORQA() {
        return ORQA;
    }

    public void setORQA(String ORQA) {
        this.ORQA = ORQA;
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

    public String getPACT() {
        return PACT;
    }

    public void setPACT(String PACT) {
        this.PACT = PACT;
    }

    public String getPITD() {
        return PITD;
    }

    public void setPITD(String PITD) {
        this.PITD = PITD;
    }

    public String getPITT() {
        return PITT;
    }

    public void setPITT(String PITT) {
        this.PITT = PITT;
    }

    public String getPLPN() {
        return PLPN;
    }

    public void setPLPN(String PLPN) {
        this.PLPN = PLPN;
    }

    public String getPLPS() {
        return PLPS;
    }

    public void setPLPS(String PLPS) {
        this.PLPS = PLPS;
    }

    public String getPNLI() {
        return PNLI;
    }

    public void setPNLI(String PNLI) {
        this.PNLI = PNLI;
    }

    public String getPNLS() {
        return PNLS;
    }

    public void setPNLS(String PNLS) {
        this.PNLS = PNLS;
    }

    public String getPOTC() {
        return POTC;
    }

    public void setPOTC(String POTC) {
        this.POTC = POTC;
    }

    public String getPPUN() {
        return PPUN;
    }

    public void setPPUN(String PPUN) {
        this.PPUN = PPUN;
    }

    public String getPRCS() {
        return PRCS;
    }

    public void setPRCS(String PRCS) {
        this.PRCS = PRCS;
    }

    public String getPRIP() {
        return PRIP;
    }

    public void setPRIP(String PRIP) {
        this.PRIP = PRIP;
    }

    public String getPROD() {
        return PROD;
    }

    public void setPROD(String PROD) {
        this.PROD = PROD;
    }

    public String getPTCD() {
        return PTCD;
    }

    public void setPTCD(String PTCD) {
        this.PTCD = PTCD;
    }

    public String getPUCD() {
        return PUCD;
    }

    public void setPUCD(String PUCD) {
        this.PUCD = PUCD;
    }

    public String getPUNO() {
        return PUNO;
    }

    public void setPUNO(String PUNO) {
        this.PUNO = PUNO;
    }

    public String getPUPR() {
        return PUPR;
    }

    public void setPUPR(String PUPR) {
        this.PUPR = PUPR;
    }

    public String getPURC() {
        return PURC;
    }

    public void setPURC(String PURC) {
        this.PURC = PURC;
    }

    public String getPUSL() {
        return PUSL;
    }

    public void setPUSL(String PUSL) {
        this.PUSL = PUSL;
    }

    public String getPUST() {
        return PUST;
    }

    public void setPUST(String PUST) {
        this.PUST = PUST;
    }

    public String getPUUN() {
        return PUUN;
    }

    public void setPUUN(String PUUN) {
        this.PUUN = PUUN;
    }

    public String getRCDT() {
        return RCDT;
    }

    public void setRCDT(String RCDT) {
        this.RCDT = RCDT;
    }

    public String getRJQA() {
        return RJQA;
    }

    public void setRJQA(String RJQA) {
        this.RJQA = RJQA;
    }

    public String getRORC() {
        return RORC;
    }

    public void setRORC(String RORC) {
        this.RORC = RORC;
    }

    public String getRORL() {
        return RORL;
    }

    public void setRORL(String RORL) {
        this.RORL = RORL;
    }

    public String getRORN() {
        return RORN;
    }

    public void setRORN(String RORN) {
        this.RORN = RORN;
    }

    public String getRORX() {
        return RORX;
    }

    public void setRORX(String RORX) {
        this.RORX = RORX;
    }

    public String getRVQA() {
        return RVQA;
    }

    public void setRVQA(String RVQA) {
        this.RVQA = RVQA;
    }

    public String getSDPC() {
        return SDPC;
    }

    public void setSDPC(String SDPC) {
        this.SDPC = SDPC;
    }

    public String getSDQA() {
        return SDQA;
    }

    public void setSDQA(String SDQA) {
        this.SDQA = SDQA;
    }

    public String getSITE() {
        return SITE;
    }

    public void setSITE(String SITE) {
        this.SITE = SITE;
    }

    public String getSORN() {
        return SORN;
    }

    public void setSORN(String SORN) {
        this.SORN = SORN;
    }

    public String getSUFI() {
        return SUFI;
    }

    public void setSUFI(String SUFI) {
        this.SUFI = SUFI;
    }

    public String getSUNO() {
        return SUNO;
    }

    public void setSUNO(String SUNO) {
        this.SUNO = SUNO;
    }

    public String getTNQA() {
        return TNQA;
    }

    public void setTNQA(String TNQA) {
        this.TNQA = TNQA;
    }

    public String getTXID() {
        return TXID;
    }

    public void setTXID(String TXID) {
        this.TXID = TXID;
    }

    public String getVTCD() {
        return VTCD;
    }

    public void setVTCD(String VTCD) {
        this.VTCD = VTCD;
    }

    public String getWHLO() {
        return WHLO;
    }

    public void setWHLO(String WHLO) {
        this.WHLO = WHLO;
    }

    public List getPoLineCharge() {
        return PoLineCharge;
    }

    public void setPoLineCharge(List PoLineCharge) {
        this.PoLineCharge = PoLineCharge;
    }

    public List getLstLineTxtlist() {
        return LstLineTxtlist;
    }

    public void setLstLineTxtlist(List LstLineTxtlist) {
        this.LstLineTxtlist = LstLineTxtlist;
    }

    public PPS200MILstLineTxtBean getText60() {
        return text60;
    }

    public void setText60(PPS200MILstLineTxtBean text60) {
        this.text60 = text60;
    }



	public String getTEMP() {
		return TEMP;
	}



	public void setTEMP(String tEMP) {
		TEMP = tEMP;
	}



	/**
	 * @return the uPAV
	 */
	public String getUPAV() {
		return UPAV;
	}



	/**
	 * @param uPAV the uPAV to set
	 */
	public void setUPAV(String uPAV) {
		UPAV = uPAV;
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
	 * @return the text60list
	 */
	public List<PPS200MILstLineTxtBean> getText60list() {
		return text60list;
	}



	/**
	 * @param text60list the text60list to set
	 */
	public void setText60list(List<PPS200MILstLineTxtBean> text60list) {
		this.text60list = text60list;
	}

    public String getFABDESC() {
        return FABDESC;
    }

    public void setFABDESC(String FABDESC) {
        this.FABDESC = FABDESC;
    }

    public String getPRGP() {
        return PRGP;
    }

    public void setPRGP(String PRGP) {
        this.PRGP = PRGP;
    }
    


}
