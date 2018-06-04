package shahi.Action.ReportFolder.EPM.Invoice;

import java.io.Serializable;

public class Voucher implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2415828789421611000L;
	private String EPCONO;
	private String EPDIVI;
	private String EPYEA4;
	private String EPVSER;
	private String EPVONO;
	private String EPSUNO;
	private String EPSINO;
	private String EPIVDT;
	private String EPCUAM;
	private String EPCHID;
	
	private String EPPYST; 
	private String EPAPRV;
	private String EPCUCD;
    private String EPLMDT;
    public Voucher(){
    	
    }
    
	public Voucher(String ePCONO, String ePDIVI, String ePYEA4, String ePVSER, String ePVONO, String ePSUNO,
			String ePSINO, String ePIVDT, String ePCUAM, String ePCHID) {
		super();
		EPCONO = ePCONO;
		EPDIVI = ePDIVI;
		EPYEA4 = ePYEA4;
		EPVSER = ePVSER;
		EPVONO = ePVONO;
		EPSUNO = ePSUNO;
		EPSINO = ePSINO;
		EPIVDT = ePIVDT;
		EPCUAM = ePCUAM;
		EPCHID = ePCHID;
	}

	public Voucher(String ePCONO, String ePDIVI, String ePYEA4, String ePVSER, String ePVONO, String ePSUNO,
			String ePSINO, String ePIVDT, String ePCUAM, String ePCHID, String ePPYST, String ePAPRV, String ePCUCD,
			String ePLMDT) {
		super();
		EPCONO = ePCONO;
		EPDIVI = ePDIVI;
		EPYEA4 = ePYEA4;
		EPVSER = ePVSER;
		EPVONO = ePVONO;
		EPSUNO = ePSUNO;
		EPSINO = ePSINO;
		EPIVDT = ePIVDT;
		EPCUAM = ePCUAM;
		EPCHID = ePCHID;
		EPPYST = ePPYST;
		EPAPRV = ePAPRV;
		EPCUCD = ePCUCD;
		EPLMDT = ePLMDT;
	}
	public String getEPCONO() {
		return EPCONO;
	}
	public void setEPCONO(String ePCONO) {
		EPCONO = ePCONO;
	}
	public String getEPDIVI() {
		return EPDIVI;
	}
	public void setEPDIVI(String ePDIVI) {
		EPDIVI = ePDIVI;
	}
	public String getEPYEA4() {
		return EPYEA4;
	}
	public void setEPYEA4(String ePYEA4) {
		EPYEA4 = ePYEA4;
	}
	public String getEPVSER() {
		return EPVSER;
	}
	public void setEPVSER(String ePVSER) {
		EPVSER = ePVSER;
	}
	public String getEPVONO() {
		return EPVONO;
	}
	public void setEPVONO(String ePVONO) {
		EPVONO = ePVONO;
	}
	public String getEPSUNO() {
		return EPSUNO;
	}
	public void setEPSUNO(String ePSUNO) {
		EPSUNO = ePSUNO;
	}
	public String getEPSINO() {
		return EPSINO;
	}
	public void setEPSINO(String ePSINO) {
		EPSINO = ePSINO;
	}
	public String getEPIVDT() {
		return EPIVDT;
	}
	public void setEPIVDT(String ePIVDT) {
		EPIVDT = ePIVDT;
	}
	public String getEPCUAM() {
		return EPCUAM;
	}
	public void setEPCUAM(String ePCUAM) {
		EPCUAM = ePCUAM;
	}
	public String getEPCHID() {
		return EPCHID;
	}
	public void setEPCHID(String ePCHID) {
		EPCHID = ePCHID;
	}
	public String getEPPYST() {
		return EPPYST;
	}
	public void setEPPYST(String ePPYST) {
		EPPYST = ePPYST;
	}
	public String getEPAPRV() {
		return EPAPRV;
	}
	public void setEPAPRV(String ePAPRV) {
		EPAPRV = ePAPRV;
	}
	public String getEPCUCD() {
		return EPCUCD;
	}
	public void setEPCUCD(String ePCUCD) {
		EPCUCD = ePCUCD;
	}
	public String getEPLMDT() {
		return EPLMDT;
	}
	public void setEPLMDT(String ePLMDT) {
		EPLMDT = ePLMDT;
	}
	@Override
	public String toString() {
		return "Voucher [EPCONO=" + EPCONO + ", EPDIVI=" + EPDIVI + ", EPYEA4=" + EPYEA4 + ", EPVSER=" + EPVSER
				+ ", EPVONO=" + EPVONO + ", EPSUNO=" + EPSUNO + ", EPSINO=" + EPSINO + ", EPIVDT=" + EPIVDT
				+ ", EPCUAM=" + EPCUAM + ", EPCHID=" + EPCHID + ", EPPYST=" + EPPYST + ", EPAPRV=" + EPAPRV
				+ ", EPCUCD=" + EPCUCD + ", EPLMDT=" + EPLMDT + "]";
	}
	
	public String convertToString(){
		return EPCONO + "," + EPDIVI + "," + EPYEA4 + "," + EPVSER
				+ "," + EPVONO + "," + EPSUNO + "," + EPSINO + "," + EPIVDT
				+ "," + EPCUAM + "," + EPCHID;
	}
	
}
