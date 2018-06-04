package shahi.Action.compcal.bean;

public class YearBean {
	private long ID;
	private String ACTIVITY_ID;
	private String  ACTIVITY_YEAR;
	private String FLAG;
	private String USER_ID;
	private String TDATE;
	private String M_USER_ID;
	private String MDATE;
	
	public YearBean() {
		super();
	}
	public YearBean(long iD, String aCTIVITY_ID, String aCTIVITY_YEAR,
			String fLAG, String uSER_ID, String tDATE, String m_USER_ID,
			String mDATE) {
		super();
		ID = iD;
		ACTIVITY_ID = aCTIVITY_ID;
		ACTIVITY_YEAR = aCTIVITY_YEAR;
		FLAG = fLAG;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		M_USER_ID = m_USER_ID;
		MDATE = mDATE;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getACTIVITY_ID() {
		return ACTIVITY_ID;
	}
	public void setACTIVITY_ID(String aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}
	public String getACTIVITY_YEAR() {
		return ACTIVITY_YEAR;
	}
	public void setACTIVITY_YEAR(String aCTIVITY_YEAR) {
		ACTIVITY_YEAR = aCTIVITY_YEAR;
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
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getTDATE() {
		return TDATE;
	}
	public void setTDATE(String tDATE) {
		TDATE = tDATE;
	}
	public String getM_USER_ID() {
		return M_USER_ID;
	}
	public void setM_USER_ID(String m_USER_ID) {
		M_USER_ID = m_USER_ID;
	}
	public String getMDATE() {
		return MDATE;
	}
	public void setMDATE(String mDATE) {
		MDATE = mDATE;
	}
	
}
