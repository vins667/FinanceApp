package shahi.Action.ReportFolder.invoicecorrection;

import java.io.Serializable;

public class Invoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2415828789421611000L;
	private String APCONO;
	private String APDIVI;
	private String APYEA4;
	private String APSPYN;
	private String APSUNO;
	private String APINYR;
	private String APSINO;
	private String APPYST;
	private String APIVDT;
	private String APCUAM;
	
	private String APCUCD; 
	private String APWHLO;
	private String APPONP;
    private String EPLMDT;
    public Invoice(){
    	
    }
    
	public Invoice(String aPCONO, String aPDIVI, String aPYEA4, String aPSPYN, String aPSUNO, String aPINYR,
			String aPSINO, String aPPYST, String aPIVDT, String aPCUAM, String aPCUCD, String aPWHLO, String aPPONP,
			String ePLMDT) {
		super();
		APCONO = aPCONO;
		APDIVI = aPDIVI;
		APYEA4 = aPYEA4;
		APSPYN = aPSPYN;
		APSUNO = aPSUNO;
		APINYR = aPINYR;
		APSINO = aPSINO;
		APPYST = aPPYST;
		APIVDT = aPIVDT;
		APCUAM = aPCUAM;
		APCUCD = aPCUCD;
		APWHLO = aPWHLO;
		APPONP = aPPONP;
		EPLMDT = ePLMDT;
	}

	public String getAPCONO() {
		return APCONO;
	}
	public void setAPCONO(String aPCONO) {
		APCONO = aPCONO;
	}
	public String getAPDIVI() {
		return APDIVI;
	}
	public void setAPDIVI(String aPDIVI) {
		APDIVI = aPDIVI;
	}
	public String getAPYEA4() {
		return APYEA4;
	}
	public void setAPYEA4(String aPYEA4) {
		APYEA4 = aPYEA4;
	}
	public String getAPSPYN() {
		return APSPYN;
	}
	public void setAPSPYN(String aPSPYN) {
		APSPYN = aPSPYN;
	}
	public String getAPSUNO() {
		return APSUNO;
	}
	public void setAPSUNO(String aPSUNO) {
		APSUNO = aPSUNO;
	}
	public String getAPINYR() {
		return APINYR;
	}
	public void setAPINYR(String aPINYR) {
		APINYR = aPINYR;
	}
	public String getAPSINO() {
		return APSINO;
	}
	public void setAPSINO(String aPSINO) {
		APSINO = aPSINO;
	}
	public String getAPPYST() {
		return APPYST;
	}
	public void setAPPYST(String aPPYST) {
		APPYST = aPPYST;
	}
	public String getAPIVDT() {
		return APIVDT;
	}
	public void setAPIVDT(String aPIVDT) {
		APIVDT = aPIVDT;
	}
	public String getAPCUAM() {
		return APCUAM;
	}
	public void setAPCUAM(String aPCUAM) {
		APCUAM = aPCUAM;
	}
	public String getAPCUCD() {
		return APCUCD;
	}
	public void setAPCUCD(String aPCUCD) {
		APCUCD = aPCUCD;
	}
	public String getAPWHLO() {
		return APWHLO;
	}
	public void setAPWHLO(String aPWHLO) {
		APWHLO = aPWHLO;
	}
	public String getAPPONP() {
		return APPONP;
	}
	public void setAPPONP(String aPPONP) {
		APPONP = aPPONP;
	}
	public String getEPLMDT() {
		return EPLMDT;
	}
	public void setEPLMDT(String ePLMDT) {
		EPLMDT = ePLMDT;
	}

	@Override
	public String toString() {
		return "Invoice [APCONO=" + APCONO + ", APDIVI=" + APDIVI + ", APYEA4=" + APYEA4 + ", APSPYN=" + APSPYN
				+ ", APSUNO=" + APSUNO + ", APINYR=" + APINYR + ", APSINO=" + APSINO + ", APPYST=" + APPYST
				+ ", APIVDT=" + APIVDT + ", APCUAM=" + APCUAM + ", APCUCD=" + APCUCD + ", APWHLO=" + APWHLO
				+ ", APPONP=" + APPONP + ", EPLMDT=" + EPLMDT + "]";
	}
	
    
	
}
