package shahi.Action.compcal.bean;

public class UserLinkDetailLoctBean {
	private String CODE;
	private String NAME;
	
	public UserLinkDetailLoctBean() {
		super();
	}
	public UserLinkDetailLoctBean(String cODE, String nAME) {
		super();
		CODE = cODE;
		NAME = nAME;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
}
