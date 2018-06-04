package shahi.Action.compcal.bean;

public class SubDeptBean {
	private long ID;
	private long DEPT_ID;
	private String DEPT_NAME;
	private String NAME;
	private String FLAG;;
	private String USER_ID;
	private String TDATE;
	private String MUSER_ID;
	private String MDATE;
	private String DFLAG;
	
	public SubDeptBean() {
		super();
	}
	
	
	
	public SubDeptBean(long iD, long dEPT_ID, String dEPT_NAME, String nAME,
			String fLAG, String uSER_ID, String tDATE, String mUSER_ID,
			String mDATE, String dFLAG) {
		super();
		ID = iD;
		DEPT_ID = dEPT_ID;
		DEPT_NAME = dEPT_NAME;
		NAME = nAME;
		FLAG = fLAG;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		MUSER_ID = mUSER_ID;
		MDATE = mDATE;
		DFLAG = dFLAG;
	}



	public SubDeptBean(long iD,long dEPT_ID,String dEPT_NAME, String nAME, String fLAG,
			String uSER_ID, String tDATE,String mUSER_ID, String mDATE) {
		super();
		ID = iD;
		DEPT_ID=dEPT_ID;
		DEPT_NAME=dEPT_NAME;
		NAME = nAME;
		FLAG = fLAG;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		MUSER_ID = mUSER_ID;
		MDATE = mDATE;
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

	public long getDEPT_ID() {
		return DEPT_ID;
	}

	public void setDEPT_ID(long dEPT_ID) {
		DEPT_ID = dEPT_ID;
	}

	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}

	public String getDFLAG() {
		return DFLAG;
	}

	public void setDFLAG(String dFLAG) {
		DFLAG = dFLAG;
	}
	
}
