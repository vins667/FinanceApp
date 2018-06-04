package shahi.Action.compcal.bean;

public class AlertBean {
	private String ALERTTYPE;
	private String ALERTDESC;
	
	public AlertBean() {
		super();
	}
	
	public AlertBean(String aLERTTYPE, String aLERTDESC) {
		super();
		ALERTTYPE = aLERTTYPE;
		ALERTDESC = aLERTDESC;
	}
	public String getALERTTYPE() {
		return ALERTTYPE;
	}
	public void setALERTTYPE(String aLERTTYPE) {
		ALERTTYPE = aLERTTYPE;
	}
	public String getALERTDESC() {
		return ALERTDESC;
	}
	public void setALERTDESC(String aLERTDESC) {
		ALERTDESC = aLERTDESC;
	}
	
}
