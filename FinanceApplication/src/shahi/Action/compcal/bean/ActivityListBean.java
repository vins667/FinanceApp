package shahi.Action.compcal.bean;

public class ActivityListBean {
	private String ID;
	private String ACT_NAME;
	
	public ActivityListBean() {
		super();
	}
	public ActivityListBean(String iD, String aCT_NAME) {
		super();
		ID = iD;
		ACT_NAME = aCT_NAME;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getACT_NAME() {
		return ACT_NAME;
	}
	public void setACT_NAME(String aCT_NAME) {
		ACT_NAME = aCT_NAME;
	}
	
}
