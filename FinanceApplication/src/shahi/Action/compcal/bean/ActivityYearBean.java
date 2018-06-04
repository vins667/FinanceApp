package shahi.Action.compcal.bean;

public class ActivityYearBean {
	private long ID;
	private long ACTIVITY_ID;
	private String ACTIVITY_NAME;
	private String LOCATION_CODE;
	private String DEPT_ID;
	private String DEPT_NAME;
	private String SUBDEPT_ID;
	private String SUBDEPT_NAME;
	private String ALERT_TYPE1;
	private String ALERT_TYPE2;
	private String ALERT_TYPE3;
	private String FLAG;
	private String USER_ID;
	private String TDATE;
	private String MUSER_ID;
	private String MDATE;
	private String UPD_FLAG;
	
	public ActivityYearBean() {
		super();
	}
	
	public ActivityYearBean(long iD,long aCTIVITY_ID, String aCTIVITY_NAME, String lOCATION_CODE,
			String dEPT_ID,String dEPT_NAME, String sUBDEPT_ID,String sUBDEPT_NAME, String aLERT_TYPE1,
			String aLERT_TYPE2, String aLERT_TYPE3, String fLAG,
			String uSER_ID, String tDATE, String mUSER_ID, String mDATE,String uPD_FLAG) {
		super();
		ID = iD;
		ACTIVITY_ID=aCTIVITY_ID;
		ACTIVITY_NAME = aCTIVITY_NAME;
		LOCATION_CODE = lOCATION_CODE;
		DEPT_ID = dEPT_ID;
		DEPT_NAME = dEPT_NAME;
		SUBDEPT_ID = sUBDEPT_ID;
		SUBDEPT_NAME = sUBDEPT_NAME;
		ALERT_TYPE1 = aLERT_TYPE1;
		ALERT_TYPE2 = aLERT_TYPE2;
		ALERT_TYPE3 = aLERT_TYPE3;
		FLAG = fLAG;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		MUSER_ID = mUSER_ID;
		MDATE = mDATE;
		UPD_FLAG = uPD_FLAG;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
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
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(String dEPT_ID) {
		DEPT_ID = dEPT_ID;
	}
	public String getSUBDEPT_ID() {
		return SUBDEPT_ID;
	}
	public void setSUBDEPT_ID(String sUBDEPT_ID) {
		SUBDEPT_ID = sUBDEPT_ID;
	}
	public String getALERT_TYPE1() {
		return ALERT_TYPE1;
	}
	public void setALERT_TYPE1(String aLERT_TYPE1) {
		ALERT_TYPE1 = aLERT_TYPE1;
	}
	public String getALERT_TYPE2() {
		return ALERT_TYPE2;
	}
	public void setALERT_TYPE2(String aLERT_TYPE2) {
		ALERT_TYPE2 = aLERT_TYPE2;
	}
	public String getALERT_TYPE3() {
		return ALERT_TYPE3;
	}
	public void setALERT_TYPE3(String aLERT_TYPE3) {
		ALERT_TYPE3 = aLERT_TYPE3;
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
	public String getMUSER_ID() {
		return MUSER_ID;
	}
	public void setMUSER_ID(String mUSER_ID) {
		MUSER_ID = mUSER_ID;
	}
	public String getMDATE() {
		return MDATE;
	}
	public void setMDATE(String mDATE) {
		MDATE = mDATE;
	}

	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}

	public String getSUBDEPT_NAME() {
		return SUBDEPT_NAME;
	}

	public void setSUBDEPT_NAME(String sUBDEPT_NAME) {
		SUBDEPT_NAME = sUBDEPT_NAME;
	}

	public String getUPD_FLAG() {
		return UPD_FLAG;
	}

	public void setUPD_FLAG(String uPD_FLAG) {
		UPD_FLAG = uPD_FLAG;
	}	
}
