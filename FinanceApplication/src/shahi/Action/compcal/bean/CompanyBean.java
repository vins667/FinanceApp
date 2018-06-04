package shahi.Action.compcal.bean;

public class CompanyBean {
	private long ID;
	private String NAME;
	private String FLAG;
	private String ORDERBY;
	private String USER_ID;
	private String TDATE;
	private String MUSER_ID;
	private String MDATE;
	private String DFLAG;
	
	public CompanyBean() {
		super();
	}
	
	public CompanyBean(long iD, String nAME, String fLAG, String oRDERBY,
			String uSER_ID, String tDATE,String mUSER_ID, String mDATE) {
		super();
		ID = iD;
		NAME = nAME;
		FLAG = fLAG;
		ORDERBY = oRDERBY;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		MUSER_ID = mUSER_ID;
		MDATE = mDATE;
	}
	
	

	public CompanyBean(long iD, String nAME, String fLAG, String oRDERBY,
			String uSER_ID, String tDATE, String mUSER_ID, String mDATE,
			String dFLAG) {
		super();
		ID = iD;
		NAME = nAME;
		FLAG = fLAG;
		ORDERBY = oRDERBY;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		MUSER_ID = mUSER_ID;
		MDATE = mDATE;
		DFLAG = dFLAG;
	}

	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getORDERBY() {
		return ORDERBY;
	}
	public void setORDERBY(String oRDERBY) {
		ORDERBY = oRDERBY;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	
	public String getMUSER_ID() {
		return MUSER_ID;
	}

	public void setMUSER_ID(String mUSER_ID) {
		MUSER_ID = mUSER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getTDATE() {
		return TDATE;
	}
	public void setTDATE(String tDATE) {
		TDATE = tDATE;
	}
	public String getMDATE() {
		return MDATE;
	}
	public void setMDATE(String mDATE) {
		MDATE = mDATE;
	}

	public String getDFLAG() {
		return DFLAG;
	}

	public void setDFLAG(String dFLAG) {
		DFLAG = dFLAG;
	}
	
}
