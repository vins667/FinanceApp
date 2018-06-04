package shahi.Action.ReportFolder.EPM.beans;

import java.util.Date;

public class GSTReconciliation {

	private Integer  COMPANY;
	private String DIVISION;
	private Integer YEAR;
	private String SHAHIGSTN;
	private String SUPLGSTN;
	private String CNTPTYST;
	private String CUSTNAME;
	private String CRNOTE;
	private String CRDATE;
	private String INVOICENO;
	private String INVOICEDT;
	private String  LINE;
	private String lineItemAmount;
	private String TAXVALUE;
	private String TAXRATE;
	private String IGST;
	private String CGST;
	private String SGST;
	private String CESS;
	private String INVVALUE;
	private String INVTYPE;
	private String POS;
	private String REVCHG;
	private String REASON;
	private String CHID;
	private String ANX_TYPE;
	private String DOC_TYPE;
	private Date recoDate;
	public GSTReconciliation(){
		
	}
	
	public GSTReconciliation(Integer cOMPANY, String dIVISION, Integer yEAR, String sHAHIGSTN, String sUPLGSTN,
			String cNTPTYST, String cUSTNAME, String cRNOTE, String cRDATE, String iNVOICENO, String iNVOICEDT,
			String lINE, String tAXVALUE, String tAXRATE, String iGST, String cGST, String sGST, String cESS,
			String iNVVALUE, String iNVTYPE, String pOS, String rEVCHG, String rEASON, String cHID, String tDATE) {
		super();
		COMPANY = cOMPANY;
		DIVISION = dIVISION;
		YEAR = yEAR;
		SHAHIGSTN = sHAHIGSTN;
		SUPLGSTN = sUPLGSTN;
		CNTPTYST = cNTPTYST;
		CUSTNAME = cUSTNAME;
		CRNOTE = cRNOTE;
		CRDATE = cRDATE;
		INVOICENO = iNVOICENO;
		INVOICEDT = iNVOICEDT;
		LINE = lINE;
		TAXVALUE = tAXVALUE;
		TAXRATE = tAXRATE;
		IGST = iGST;
		CGST = cGST;
		SGST = sGST;
		CESS = cESS;
		INVVALUE = iNVVALUE;
		INVTYPE = iNVTYPE;
		POS = pOS;
		REVCHG = rEVCHG;
		REASON = rEASON;
		CHID = cHID;
		TDATE = tDATE;
	}

	public Integer getCOMPANY() {
		return COMPANY;
	}
	public void setCOMPANY(Integer cOMPANY) {
		COMPANY = cOMPANY;
	}
	public String getDIVISION() {
		return DIVISION;
	}
	public void setDIVISION(String dIVISION) {
		DIVISION = dIVISION;
	}
	public Integer getYEAR() {
		return YEAR;
	}
	public void setYEAR(Integer yEAR) {
		YEAR = yEAR;
	}
	public String getSHAHIGSTN() {
		return SHAHIGSTN;
	}
	public void setSHAHIGSTN(String sHAHIGSTN) {
		SHAHIGSTN = sHAHIGSTN;
	}
	public String getSUPLGSTN() {
		return SUPLGSTN;
	}
	public void setSUPLGSTN(String sUPLGSTN) {
		SUPLGSTN = sUPLGSTN;
	}
	public String getCNTPTYST() {
		return CNTPTYST;
	}
	public void setCNTPTYST(String cNTPTYST) {
		CNTPTYST = cNTPTYST;
	}
	public String getCUSTNAME() {
		return CUSTNAME;
	}
	public void setCUSTNAME(String cUSTNAME) {
		CUSTNAME = cUSTNAME;
	}
	public String getCRNOTE() {
		return CRNOTE;
	}
	public void setCRNOTE(String cRNOTE) {
		CRNOTE = cRNOTE;
	}
	public String getCRDATE() {
		return CRDATE;
	}
	public void setCRDATE(String cRDATE) {
		CRDATE = cRDATE;
	}
	public String getINVOICENO() {
		return INVOICENO;
	}
	public void setINVOICENO(String iNVOICENO) {
		INVOICENO = iNVOICENO;
	}
	public String getINVOICEDT() {
		return INVOICEDT;
	}
	public void setINVOICEDT(String iNVOICEDT) {
		INVOICEDT = iNVOICEDT;
	}
	public String getLINE() {
		return LINE;
	}
	public void setLINE(String lINE) {
		LINE = lINE;
	}
	public String getTAXVALUE() {
		return TAXVALUE;
	}
	public void setTAXVALUE(String tAXVALUE) {
		TAXVALUE = tAXVALUE;
	}
	public String getTAXRATE() {
		return TAXRATE;
	}
	public void setTAXRATE(String tAXRATE) {
		TAXRATE = tAXRATE;
	}
	public String getIGST() {
		return IGST;
	}
	public void setIGST(String iGST) {
		IGST = iGST;
	}
	public String getCGST() {
		return CGST;
	}
	public void setCGST(String cGST) {
		CGST = cGST;
	}
	public String getSGST() {
		return SGST;
	}
	public void setSGST(String sGST) {
		SGST = sGST;
	}
	public String getCESS() {
		return CESS;
	}
	public void setCESS(String cESS) {
		CESS = cESS;
	}
	public String getINVVALUE() {
		return INVVALUE;
	}
	public void setINVVALUE(String iNVVALUE) {
		INVVALUE = iNVVALUE;
	}
	public String getINVTYPE() {
		return INVTYPE;
	}
	public void setINVTYPE(String iNVTYPE) {
		INVTYPE = iNVTYPE;
	}
	public String getPOS() {
		return POS;
	}
	public void setPOS(String pOS) {
		POS = pOS;
	}
	public String getREVCHG() {
		return REVCHG;
	}
	public void setREVCHG(String rEVCHG) {
		REVCHG = rEVCHG;
	}
	public String getREASON() {
		return REASON;
	}
	public void setREASON(String rEASON) {
		REASON = rEASON;
	}
	public String getCHID() {
		return CHID;
	}
	public void setCHID(String cHID) {
		CHID = cHID;
	}
	public String getTDATE() {
		return TDATE;
	}
	public void setTDATE(String tDATE) {
		TDATE = tDATE;
	}
	private String TDATE;
	public String getANX_TYPE() {
		return ANX_TYPE;
	}

	public void setANX_TYPE(String aNX_TYPE) {
		ANX_TYPE = aNX_TYPE;
	}

	public String getDOC_TYPE() {
		return DOC_TYPE;
	}

	public void setDOC_TYPE(String dOC_TYPE) {
		DOC_TYPE = dOC_TYPE;
	}

	public String getLineItemAmount() {
		return lineItemAmount;
	}

	public void setLineItemAmount(String lineItemAmount) {
		this.lineItemAmount = lineItemAmount;
	}

	

	public Date getRecoDate() {
		return recoDate;
	}

	public void setRecoDate(Date recoDate) {
		this.recoDate = recoDate;
	}

	@Override
	public String toString() {
		return "GSTReconciliation [COMPANY=" + COMPANY + ", DIVISION=" + DIVISION + ", YEAR=" + YEAR + ", SHAHIGSTN="
				+ SHAHIGSTN + ", SUPLGSTN=" + SUPLGSTN + ", CNTPTYST=" + CNTPTYST + ", CUSTNAME=" + CUSTNAME
				+ ", CRNOTE=" + CRNOTE + ", CRDATE=" + CRDATE + ", INVOICENO=" + INVOICENO + ", INVOICEDT=" + INVOICEDT
				+ ", LINE=" + LINE + ", TAXVALUE=" + TAXVALUE + ", TAXRATE=" + TAXRATE + ", IGST=" + IGST + ", CGST="
				+ CGST + ", SGST=" + SGST + ", CESS=" + CESS + ", INVVALUE=" + INVVALUE + ", INVTYPE=" + INVTYPE
				+ ", POS=" + POS + ", REVCHG=" + REVCHG + ", REASON=" + REASON + ", CHID=" + CHID + ", TDATE=" + TDATE
				+ "]";
	}	
}
