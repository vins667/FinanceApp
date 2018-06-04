package shahi.Action.compcal.bean;

public class UserLinkBean {
	private String ID;
	private String EMP_CODE;
	private String EMP_NAME;
	private String ALERT_TYPE;
        private String FLAG;
	
	public UserLinkBean() {
		super();
	}
	public UserLinkBean(String iD,String aLERT_TYPE, String eMP_CODE, String eMP_NAME) {
		super();
		ID = iD;
		ALERT_TYPE=aLERT_TYPE;
		EMP_CODE = eMP_CODE;
		EMP_NAME = eMP_NAME;
	}

    public UserLinkBean(String EMP_CODE, String EMP_NAME) {
        this.EMP_CODE = EMP_CODE;
        this.EMP_NAME = EMP_NAME;
    }

    public UserLinkBean(String ID, String EMP_CODE, String EMP_NAME, String ALERT_TYPE, String FLAG) {
        this.ID = ID;
        this.EMP_CODE = EMP_CODE;
        this.EMP_NAME = EMP_NAME;
        this.ALERT_TYPE = ALERT_TYPE;
        this.FLAG = FLAG;
    }
        
        
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
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
	public String getALERT_TYPE() {
		return ALERT_TYPE;
	}
	public void setALERT_TYPE(String aLERT_TYPE) {
		ALERT_TYPE = aLERT_TYPE;
	}

    public String getFLAG() {
        return FLAG;
    }

    public void setFLAG(String FLAG) {
        this.FLAG = FLAG;
    }
        
        
	
}
