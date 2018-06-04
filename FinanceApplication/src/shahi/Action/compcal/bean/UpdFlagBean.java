package shahi.Action.compcal.bean;

public class UpdFlagBean {
	private String CODE;
	private String UPDFLAG;
	
	public UpdFlagBean() {
		super();
	}
	public UpdFlagBean(String cODE, String uPDFLAG) {
		super();
		CODE = cODE;
		UPDFLAG = uPDFLAG;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getUPDFLAG() {
		return UPDFLAG;
	}
	public void setUPDFLAG(String uPDFLAG) {
		UPDFLAG = uPDFLAG;
	}
	
}
