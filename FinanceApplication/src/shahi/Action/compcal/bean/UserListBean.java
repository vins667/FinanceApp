package shahi.Action.compcal.bean;

public class UserListBean {
	private String EMP_CODE;
	private String EMP_NAME;
	
	public UserListBean() {
		super();
	}
	public UserListBean(String eMP_CODE, String eMP_NAME) {
		super();
		EMP_CODE = eMP_CODE;
		EMP_NAME = eMP_NAME;
	}
	public String getEMP_CODE() {
		return EMP_CODE;
	}
	public void setEMP_CODE(String eMP_CODE) {
		EMP_CODE = eMP_CODE;
	}
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}
	
}
