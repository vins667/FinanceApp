package shahi.Action.compcal.bean;

public class ComplianceCalenderBean {
	private long ACTIVITY_ID;
	private String ACTIVITY_NAME;
	private String LOCATION_CODE;
	private long DEPT_ID;
	private String DEPT_NAME;
	private long SUBDEPT_ID;
	private String SUBDEPT_NAME;
	private String CALENDER_TYPE;
	private String USER_ID;
	private String USER_NAME;
	private String ACT_FLAG;
	
	public ComplianceCalenderBean() {
		super();
	}
	public ComplianceCalenderBean(long aCTIVITY_ID, String aCTIVITY_NAME, String lOCATION_CODE, long dEPT_ID,
			String dEPT_NAME, long sUBDEPT_ID, String sUBDEPT_NAME, String cALENDER_TYPE, String uSER_ID,
			String uSER_NAME,String aCT_FLAG) {
		super();
		ACTIVITY_ID = aCTIVITY_ID;
		ACTIVITY_NAME = aCTIVITY_NAME;
		LOCATION_CODE = lOCATION_CODE;
		DEPT_ID = dEPT_ID;
		DEPT_NAME = dEPT_NAME;
		SUBDEPT_ID = sUBDEPT_ID;
		SUBDEPT_NAME = sUBDEPT_NAME;
		CALENDER_TYPE = cALENDER_TYPE;
		USER_ID = uSER_ID;
		USER_NAME = uSER_NAME;
		ACT_FLAG = aCT_FLAG;
	}
	public long getACTIVITY_ID() {
		return ACTIVITY_ID;
	}
	public void setACTIVITY_ID(long aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}
	public String getACTIVITY_NAME() {
		return ACTIVITY_NAME;
	}
	public void setACTIVITY_NAME(String aCTIVITY_NAME) {
		ACTIVITY_NAME = aCTIVITY_NAME;
	}
	public String getLOCATION_CODE() {
		return LOCATION_CODE;
	}
	public void setLOCATION_CODE(String lOCATION_CODE) {
		LOCATION_CODE = lOCATION_CODE;
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
	public long getSUBDEPT_ID() {
		return SUBDEPT_ID;
	}
	public void setSUBDEPT_ID(long sUBDEPT_ID) {
		SUBDEPT_ID = sUBDEPT_ID;
	}
	public String getSUBDEPT_NAME() {
		return SUBDEPT_NAME;
	}
	public void setSUBDEPT_NAME(String sUBDEPT_NAME) {
		SUBDEPT_NAME = sUBDEPT_NAME;
	}
	
	public String getCALENDER_TYPE() {
		return CALENDER_TYPE;
	}
	public void setCALENDER_TYPE(String cALENDER_TYPE) {
		CALENDER_TYPE = cALENDER_TYPE;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	
}
