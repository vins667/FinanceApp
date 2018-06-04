package shahi.Action.MI.Beans;

public class PPS170MIGetPlannedPOBean {
	
	private String PLPS;	//Subnumber - planned order
	private String CONO;	//Company
	private String FACI;	//Facility
	private String WHLO;	//Warehouse
	private String ITNO;	//Item number
	private String PLPN;	//Planned order
	private String PLP2;	//Subnumber - order proposal
	private String GETY;	//Generation reference
	private String PRIF;	//Priority value
	private String RPLQ;	//Shortage quantity
	private String RELD;	//Release date
	private String DLDT;	//Planned delivery date
	private String PLDT;	//Planning date
	private String FUDT;	//Follow-up date
	private String PPQT;	//Planned quantity
	private String RESP;	//Responsible
	private String BUYE;	//Buyer
	private String PUSL;	//Lowest status - purchase order
	private String PURC;	//Requisition by
	private String RFID;	//Reference
	private String YRE1;	//Your reference
	private String AURE;	//Authorized
	private String ORTY;	//Order type
	private String POTC;	//Purchase order category
	private String RORN;	//Reference order number
	private String RORL;	//Reference order line
	private String RORX;	//Line suffix
	private String RORC;	//Reference order category
	private String OSHV;	//Via address
	private String OFID;	//Final destination
	private String TEDL;	//Delivery terms
	private String MODL;	//Delivery method
	private String TEPY;	//Payment terms
	private String PRIP;	//Priority
	private String SUNO;	//Supplier
	private String PUNO;	//Purchase order number
	private String CUCD;	//Currency
	private String PITD;	//Purchase order item name
	private String PITT;	//Purchase order item description
	private String PUPR;	//Purchase price
	private String PPUN;	//Purchase price U/M
	private String PUUN;	//Purchase order U/M
	private String PUCD;	//Purchase price quantity
	private String PTCD;	//Purchase price text
	private String ODI1;	//Discount 1
	private String ODI2;	//Discount 2
	private String ODI3;	//Discount 3
	private String PROD;	//Manufacturer
	private String PACT;	//Packaging
	private String GRMT;	//Goods receiving method
	private String ADVI;	//Ship-via address
	private String WHL1;	//Warehouse
	private String OSH1;	//Via address
	private String BANO;	//Lot number
	private String APRB;	//Approved by
	private String RSCD;	//Transaction reason
	private String LOCD;	//Local currency
	private String CUPR;	//Sales price in local currency
	private String PSTS;	//Status - planned PO
	private String ACRF;	//User-defined accounting control object
	private String COCE;	//Cost center
	private String IRCV;	//Recipient
	private String SITE;	//Supplier item number
	private String PTXT;	//Note
	private String TEOR;	//Telephone order
	private String ATNR;	//Attribute number
	private String TRQT;	//Transaction quantity - basic U/M
	private String UNMS;	//Basic unit of measure
	private String RGDT;	//Entry date
	private String RGTM;	//Entry time
        private String SUNM;    //Supplier Desc
        private String TX15;    //Order Type Desc
        private String CONV;
        private String AUOM;
        private String BUOM;
        private String PRGP;
        private String FUDS;
        
	
	public PPS170MIGetPlannedPOBean() {
		super();
	}
	
	
	
	public PPS170MIGetPlannedPOBean(String pLPS, String cONO, String fACI,
			String wHLO, String iTNO, String pLPN, String pLP2, String gETY,
			String pRIF, String rPLQ, String rELD, String dLDT, String pLDT,
			String fUDT, String pPQT, String rESP, String bUYE, String pUSL,
			String pURC, String rFID, String yRE1, String aURE, String oRTY,
			String pOTC, String rORN, String rORL, String rORX, String rORC,
			String oSHV, String oFID, String tEDL, String mODL, String tEPY,
			String pRIP, String sUNO, String pUNO, String cUCD, String pITD,
			String pITT, String pUPR, String pPUN, String pUUN, String pUCD,
			String pTCD, String oDI1, String oDI2, String oDI3, String pROD,
			String pACT, String gRMT, String aDVI, String wHL1, String oSH1,
			String bANO, String aPRB, String rSCD, String lOCD, String cUPR,
			String pSTS, String aCRF, String cOCE, String iRCV, String sITE,
			String pTXT, String tEOR, String aTNR, String tRQT, String uNMS,
			String rGDT, String rGTM) {
		super();
		PLPS = pLPS;
		CONO = cONO;
		FACI = fACI;
		WHLO = wHLO;
		ITNO = iTNO;
		PLPN = pLPN;
		PLP2 = pLP2;
		GETY = gETY;
		PRIF = pRIF;
		RPLQ = rPLQ;
		RELD = rELD;
		DLDT = dLDT;
		PLDT = pLDT;
		FUDT = fUDT;
		PPQT = pPQT;
		RESP = rESP;
		BUYE = bUYE;
		PUSL = pUSL;
		PURC = pURC;
		RFID = rFID;
		YRE1 = yRE1;
		AURE = aURE;
		ORTY = oRTY;
		POTC = pOTC;
		RORN = rORN;
		RORL = rORL;
		RORX = rORX;
		RORC = rORC;
		OSHV = oSHV;
		OFID = oFID;
		TEDL = tEDL;
		MODL = mODL;
		TEPY = tEPY;
		PRIP = pRIP;
		SUNO = sUNO;
		PUNO = pUNO;
		CUCD = cUCD;
		PITD = pITD;
		PITT = pITT;
		PUPR = pUPR;
		PPUN = pPUN;
		PUUN = pUUN;
		PUCD = pUCD;
		PTCD = pTCD;
		ODI1 = oDI1;
		ODI2 = oDI2;
		ODI3 = oDI3;
		PROD = pROD;
		PACT = pACT;
		GRMT = gRMT;
		ADVI = aDVI;
		WHL1 = wHL1;
		OSH1 = oSH1;
		BANO = bANO;
		APRB = aPRB;
		RSCD = rSCD;
		LOCD = lOCD;
		CUPR = cUPR;
		PSTS = pSTS;
		ACRF = aCRF;
		COCE = cOCE;
		IRCV = iRCV;
		SITE = sITE;
		PTXT = pTXT;
		TEOR = tEOR;
		ATNR = aTNR;
		TRQT = tRQT;
		UNMS = uNMS;
		RGDT = rGDT;
		RGTM = rGTM;
	}



	/**
	 * @return the pLPS
	 */
	public String getPLPS() {
		return PLPS;
	}
	/**
	 * @param pLPS the pLPS to set
	 */
	public void setPLPS(String pLPS) {
		PLPS = pLPS;
	}
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
	 * @return the iTNO
	 */
	public String getITNO() {
		return ITNO;
	}
	/**
	 * @param iTNO the iTNO to set
	 */
	public void setITNO(String iTNO) {
		ITNO = iTNO;
	}
	/**
	 * @return the pLPN
	 */
	public String getPLPN() {
		return PLPN;
	}
	/**
	 * @param pLPN the pLPN to set
	 */
	public void setPLPN(String pLPN) {
		PLPN = pLPN;
	}
	/**
	 * @return the pLP2
	 */
	public String getPLP2() {
		return PLP2;
	}
	/**
	 * @param pLP2 the pLP2 to set
	 */
	public void setPLP2(String pLP2) {
		PLP2 = pLP2;
	}
	/**
	 * @return the gETY
	 */
	public String getGETY() {
		return GETY;
	}
	/**
	 * @param gETY the gETY to set
	 */
	public void setGETY(String gETY) {
		GETY = gETY;
	}
	/**
	 * @return the pRIF
	 */
	public String getPRIF() {
		return PRIF;
	}
	/**
	 * @param pRIF the pRIF to set
	 */
	public void setPRIF(String pRIF) {
		PRIF = pRIF;
	}
	/**
	 * @return the rPLQ
	 */
	public String getRPLQ() {
		return RPLQ;
	}
	/**
	 * @param rPLQ the rPLQ to set
	 */
	public void setRPLQ(String rPLQ) {
		RPLQ = rPLQ;
	}
	/**
	 * @return the rELD
	 */
	public String getRELD() {
		return RELD;
	}
	/**
	 * @param rELD the rELD to set
	 */
	public void setRELD(String rELD) {
		RELD = rELD;
	}
	/**
	 * @return the dLDT
	 */
	public String getDLDT() {
		return DLDT;
	}
	/**
	 * @param dLDT the dLDT to set
	 */
	public void setDLDT(String dLDT) {
		DLDT = dLDT;
	}
	/**
	 * @return the pLDT
	 */
	public String getPLDT() {
		return PLDT;
	}
	/**
	 * @param pLDT the pLDT to set
	 */
	public void setPLDT(String pLDT) {
		PLDT = pLDT;
	}
	/**
	 * @return the fUDT
	 */
	public String getFUDT() {
		return FUDT;
	}
	/**
	 * @param fUDT the fUDT to set
	 */
	public void setFUDT(String fUDT) {
		FUDT = fUDT;
	}
	/**
	 * @return the pPQT
	 */
	public String getPPQT() {
		return PPQT;
	}
	/**
	 * @param pPQT the pPQT to set
	 */
	public void setPPQT(String pPQT) {
		PPQT = pPQT;
	}
	/**
	 * @return the rESP
	 */
	public String getRESP() {
		return RESP;
	}
	/**
	 * @param rESP the rESP to set
	 */
	public void setRESP(String rESP) {
		RESP = rESP;
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
	 * @return the pURC
	 */
	public String getPURC() {
		return PURC;
	}
	/**
	 * @param pURC the pURC to set
	 */
	public void setPURC(String pURC) {
		PURC = pURC;
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
	 * @return the aURE
	 */
	public String getAURE() {
		return AURE;
	}
	/**
	 * @param aURE the aURE to set
	 */
	public void setAURE(String aURE) {
		AURE = aURE;
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
	 * @return the rORN
	 */
	public String getRORN() {
		return RORN;
	}
	/**
	 * @param rORN the rORN to set
	 */
	public void setRORN(String rORN) {
		RORN = rORN;
	}
	/**
	 * @return the rORL
	 */
	public String getRORL() {
		return RORL;
	}
	/**
	 * @param rORL the rORL to set
	 */
	public void setRORL(String rORL) {
		RORL = rORL;
	}
	/**
	 * @return the rORX
	 */
	public String getRORX() {
		return RORX;
	}
	/**
	 * @param rORX the rORX to set
	 */
	public void setRORX(String rORX) {
		RORX = rORX;
	}
	/**
	 * @return the rORC
	 */
	public String getRORC() {
		return RORC;
	}
	/**
	 * @param rORC the rORC to set
	 */
	public void setRORC(String rORC) {
		RORC = rORC;
	}
	/**
	 * @return the oSHV
	 */
	public String getOSHV() {
		return OSHV;
	}
	/**
	 * @param oSHV the oSHV to set
	 */
	public void setOSHV(String oSHV) {
		OSHV = oSHV;
	}
	/**
	 * @return the oFID
	 */
	public String getOFID() {
		return OFID;
	}
	/**
	 * @param oFID the oFID to set
	 */
	public void setOFID(String oFID) {
		OFID = oFID;
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
	 * @return the pRIP
	 */
	public String getPRIP() {
		return PRIP;
	}
	/**
	 * @param pRIP the pRIP to set
	 */
	public void setPRIP(String pRIP) {
		PRIP = pRIP;
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
	 * @return the pITD
	 */
	public String getPITD() {
		return PITD;
	}
	/**
	 * @param pITD the pITD to set
	 */
	public void setPITD(String pITD) {
		PITD = pITD;
	}
	/**
	 * @return the pITT
	 */
	public String getPITT() {
		return PITT;
	}
	/**
	 * @param pITT the pITT to set
	 */
	public void setPITT(String pITT) {
		PITT = pITT;
	}
	/**
	 * @return the pUPR
	 */
	public String getPUPR() {
		return PUPR;
	}
	/**
	 * @param pUPR the pUPR to set
	 */
	public void setPUPR(String pUPR) {
		PUPR = pUPR;
	}
	/**
	 * @return the pPUN
	 */
	public String getPPUN() {
		return PPUN;
	}
	/**
	 * @param pPUN the pPUN to set
	 */
	public void setPPUN(String pPUN) {
		PPUN = pPUN;
	}
	/**
	 * @return the pUUN
	 */
	public String getPUUN() {
		return PUUN;
	}
	/**
	 * @param pUUN the pUUN to set
	 */
	public void setPUUN(String pUUN) {
		PUUN = pUUN;
	}
	/**
	 * @return the pUCD
	 */
	public String getPUCD() {
		return PUCD;
	}
	/**
	 * @param pUCD the pUCD to set
	 */
	public void setPUCD(String pUCD) {
		PUCD = pUCD;
	}
	/**
	 * @return the pTCD
	 */
	public String getPTCD() {
		return PTCD;
	}
	/**
	 * @param pTCD the pTCD to set
	 */
	public void setPTCD(String pTCD) {
		PTCD = pTCD;
	}
	/**
	 * @return the oDI1
	 */
	public String getODI1() {
		return ODI1;
	}
	/**
	 * @param oDI1 the oDI1 to set
	 */
	public void setODI1(String oDI1) {
		ODI1 = oDI1;
	}
	/**
	 * @return the oDI2
	 */
	public String getODI2() {
		return ODI2;
	}
	/**
	 * @param oDI2 the oDI2 to set
	 */
	public void setODI2(String oDI2) {
		ODI2 = oDI2;
	}
	/**
	 * @return the oDI3
	 */
	public String getODI3() {
		return ODI3;
	}
	/**
	 * @param oDI3 the oDI3 to set
	 */
	public void setODI3(String oDI3) {
		ODI3 = oDI3;
	}
	/**
	 * @return the pROD
	 */
	public String getPROD() {
		return PROD;
	}
	/**
	 * @param pROD the pROD to set
	 */
	public void setPROD(String pROD) {
		PROD = pROD;
	}
	/**
	 * @return the pACT
	 */
	public String getPACT() {
		return PACT;
	}
	/**
	 * @param pACT the pACT to set
	 */
	public void setPACT(String pACT) {
		PACT = pACT;
	}
	/**
	 * @return the gRMT
	 */
	public String getGRMT() {
		return GRMT;
	}
	/**
	 * @param gRMT the gRMT to set
	 */
	public void setGRMT(String gRMT) {
		GRMT = gRMT;
	}
	/**
	 * @return the aDVI
	 */
	public String getADVI() {
		return ADVI;
	}
	/**
	 * @param aDVI the aDVI to set
	 */
	public void setADVI(String aDVI) {
		ADVI = aDVI;
	}
	/**
	 * @return the wHL1
	 */
	public String getWHL1() {
		return WHL1;
	}
	/**
	 * @param wHL1 the wHL1 to set
	 */
	public void setWHL1(String wHL1) {
		WHL1 = wHL1;
	}
	/**
	 * @return the oSH1
	 */
	public String getOSH1() {
		return OSH1;
	}
	/**
	 * @param oSH1 the oSH1 to set
	 */
	public void setOSH1(String oSH1) {
		OSH1 = oSH1;
	}
	/**
	 * @return the bANO
	 */
	public String getBANO() {
		return BANO;
	}
	/**
	 * @param bANO the bANO to set
	 */
	public void setBANO(String bANO) {
		BANO = bANO;
	}
	/**
	 * @return the aPRB
	 */
	public String getAPRB() {
		return APRB;
	}
	/**
	 * @param aPRB the aPRB to set
	 */
	public void setAPRB(String aPRB) {
		APRB = aPRB;
	}
	/**
	 * @return the rSCD
	 */
	public String getRSCD() {
		return RSCD;
	}
	/**
	 * @param rSCD the rSCD to set
	 */
	public void setRSCD(String rSCD) {
		RSCD = rSCD;
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
	 * @return the cUPR
	 */
	public String getCUPR() {
		return CUPR;
	}
	/**
	 * @param cUPR the cUPR to set
	 */
	public void setCUPR(String cUPR) {
		CUPR = cUPR;
	}
	/**
	 * @return the pSTS
	 */
	public String getPSTS() {
		return PSTS;
	}
	/**
	 * @param pSTS the pSTS to set
	 */
	public void setPSTS(String pSTS) {
		PSTS = pSTS;
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
	 * @return the cOCE
	 */
	public String getCOCE() {
		return COCE;
	}
	/**
	 * @param cOCE the cOCE to set
	 */
	public void setCOCE(String cOCE) {
		COCE = cOCE;
	}
	/**
	 * @return the iRCV
	 */
	public String getIRCV() {
		return IRCV;
	}
	/**
	 * @param iRCV the iRCV to set
	 */
	public void setIRCV(String iRCV) {
		IRCV = iRCV;
	}
	/**
	 * @return the sITE
	 */
	public String getSITE() {
		return SITE;
	}
	/**
	 * @param sITE the sITE to set
	 */
	public void setSITE(String sITE) {
		SITE = sITE;
	}
	/**
	 * @return the pTXT
	 */
	public String getPTXT() {
		return PTXT;
	}
	/**
	 * @param pTXT the pTXT to set
	 */
	public void setPTXT(String pTXT) {
		PTXT = pTXT;
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
	 * @return the aTNR
	 */
	public String getATNR() {
		return ATNR;
	}
	/**
	 * @param aTNR the aTNR to set
	 */
	public void setATNR(String aTNR) {
		ATNR = aTNR;
	}
	/**
	 * @return the tRQT
	 */
	public String getTRQT() {
		return TRQT;
	}
	/**
	 * @param tRQT the tRQT to set
	 */
	public void setTRQT(String tRQT) {
		TRQT = tRQT;
	}
	/**
	 * @return the uNMS
	 */
	public String getUNMS() {
		return UNMS;
	}
	/**
	 * @param uNMS the uNMS to set
	 */
	public void setUNMS(String uNMS) {
		UNMS = uNMS;
	}
	/**
	 * @return the rGDT
	 */
	public String getRGDT() {
		return RGDT;
	}
	/**
	 * @param rGDT the rGDT to set
	 */
	public void setRGDT(String rGDT) {
		RGDT = rGDT;
	}
	/**
	 * @return the rGTM
	 */
	public String getRGTM() {
		return RGTM;
	}
	/**
	 * @param rGTM the rGTM to set
	 */
	public void setRGTM(String rGTM) {
		RGTM = rGTM;
	}

    public String getSUNM() {
        return SUNM;
    }

    public void setSUNM(String SUNM) {
        this.SUNM = SUNM;
    }

    public String getTX15() {
        return TX15;
    }

    public void setTX15(String TX15) {
        this.TX15 = TX15;
    }

    public String getCONV() {
        return CONV;
    }

    public void setCONV(String CONV) {
        this.CONV = CONV;
    }

    public String getAUOM() {
        return AUOM;
    }

    public void setAUOM(String AUOM) {
        this.AUOM = AUOM;
    }

    public String getBUOM() {
        return BUOM;
    }

    public void setBUOM(String BUOM) {
        this.BUOM = BUOM;
    }

    public String getPRGP() {
        return PRGP;
    }

    public void setPRGP(String PRGP) {
        this.PRGP = PRGP;
    }

    public String getFUDS() {
        return FUDS;
    }

    public void setFUDS(String FUDS) {
        this.FUDS = FUDS; 
    }
	
}
