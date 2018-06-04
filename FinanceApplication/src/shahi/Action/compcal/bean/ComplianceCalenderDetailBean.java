package shahi.Action.compcal.bean;

public class ComplianceCalenderDetailBean {
	private long ID;
	private String ACTIVITY_NAME;
	private long COMPLIANCE_ID;
	private String DUE_DATE;
	private String SUBMISSION_DATE;
	private String ACTUAL_DATE;
	private String AMOUNT;
	private String CHALLAN_NO;
	private String BANK_CODE;
	private String REMARKS;
	private String USER_ID;
	private String TDATE;
	private String MUSER_ID;
	private String MDATE;
	private String LOCATION_NAME;
	private String DEPT_NAME;
	private String SUBDEPT_NAME;
	private String COMPANY_NAME; 
        private String ACCESS_FLAG;
        private String EDIT_FLAG;
        private String ACTIVITY_MONTH;
	
	public ComplianceCalenderDetailBean() {
		super();
	}
	public ComplianceCalenderDetailBean(long iD, String aCTIVITY_NAME,
			long cOMPLIANCE_ID, String dUE_DATE, String sUBMISSION_DATE,
			String aCTUAL_DATE, String aMOUNT, String cHALLAN_NO,
			String bANK_CODE, String rEMARKS, String uSER_ID, String tDATE,
			String mUSER_ID, String mDATE, String lOCATION_NAME,
			String dEPT_NAME, String sUBDEPT_NAME, String cOMPANY_NAME ) {
		super();
		ID = iD;
		ACTIVITY_NAME = aCTIVITY_NAME;
		COMPLIANCE_ID = cOMPLIANCE_ID;
		DUE_DATE = dUE_DATE;
		SUBMISSION_DATE = sUBMISSION_DATE;
		ACTUAL_DATE = aCTUAL_DATE;
		AMOUNT = aMOUNT;
		CHALLAN_NO = cHALLAN_NO;
		BANK_CODE = bANK_CODE;
		REMARKS = rEMARKS;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		MUSER_ID = mUSER_ID;
		MDATE = mDATE;
		LOCATION_NAME = lOCATION_NAME;
		DEPT_NAME = dEPT_NAME;
		SUBDEPT_NAME = sUBDEPT_NAME;
		COMPANY_NAME = cOMPANY_NAME;
                
	}
	public ComplianceCalenderDetailBean(long iD, String aCTIVITY_NAME,
			long cOMPLIANCE_ID, String dUE_DATE, String sUBMISSION_DATE,
			String aCTUAL_DATE, String aMOUNT, String cHALLAN_NO,
			String bANK_CODE, String rEMARKS, String uSER_ID, String tDATE,
			String mUSER_ID, String mDATE, String lOCATION_NAME,
			String dEPT_NAME, String sUBDEPT_NAME, String cOMPANY_NAME,
                        String aCCESS_FLAG,String eDIT_FLAG,String aCTIVITY_MONTH ) {
		super();
		ID = iD;
		ACTIVITY_NAME = aCTIVITY_NAME;
		COMPLIANCE_ID = cOMPLIANCE_ID;
		DUE_DATE = dUE_DATE;
		SUBMISSION_DATE = sUBMISSION_DATE;
		ACTUAL_DATE = aCTUAL_DATE;
		AMOUNT = aMOUNT;
		CHALLAN_NO = cHALLAN_NO;
		BANK_CODE = bANK_CODE;
		REMARKS = rEMARKS;
		USER_ID = uSER_ID;
		TDATE = tDATE;
		MUSER_ID = mUSER_ID;
		MDATE = mDATE;
		LOCATION_NAME = lOCATION_NAME;
		DEPT_NAME = dEPT_NAME;
		SUBDEPT_NAME = sUBDEPT_NAME;
		COMPANY_NAME = cOMPANY_NAME;
                ACCESS_FLAG=aCCESS_FLAG;
                EDIT_FLAG=eDIT_FLAG;
                ACTIVITY_MONTH=aCTIVITY_MONTH;
	}

	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getACTIVITY_NAME() {
		return ACTIVITY_NAME;
	}
	public void setACTIVITY_NAME(String aCTIVITY_NAME) {
		ACTIVITY_NAME = aCTIVITY_NAME;
	}
	public long getCOMPLIANCE_ID() {
		return COMPLIANCE_ID;
	}
	public void setCOMPLIANCE_ID(long cOMPLIANCE_ID) {
		COMPLIANCE_ID = cOMPLIANCE_ID;
	}
	public String getDUE_DATE() {
		return DUE_DATE;
	}
	public void setDUE_DATE(String dUE_DATE) {
		DUE_DATE = dUE_DATE;
	}
	public String getSUBMISSION_DATE() {
		return SUBMISSION_DATE;
	}
	public void setSUBMISSION_DATE(String sUBMISSION_DATE) {
		SUBMISSION_DATE = sUBMISSION_DATE;
	}
	public String getACTUAL_DATE() {
		return ACTUAL_DATE;
	}
	public void setACTUAL_DATE(String aCTUAL_DATE) {
		ACTUAL_DATE = aCTUAL_DATE;
	}
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getCHALLAN_NO() {
		return CHALLAN_NO;
	}
	public void setCHALLAN_NO(String cHALLAN_NO) {
		CHALLAN_NO = cHALLAN_NO;
	}
	public String getBANK_CODE() {
		return BANK_CODE;
	}
	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
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
	public String getLOCATION_NAME() {
		return LOCATION_NAME;
	}
	public void setLOCATION_NAME(String lOCATION_NAME) {
		LOCATION_NAME = lOCATION_NAME;
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
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}
	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}

    public String getACCESS_FLAG() {
        return ACCESS_FLAG;
    }

    public void setACCESS_FLAG(String ACCESS_FLAG) {
        this.ACCESS_FLAG = ACCESS_FLAG;
    }

    public String getEDIT_FLAG() {
        return EDIT_FLAG;
    }

    public void setEDIT_FLAG(String EDIT_FLAG) {
        this.EDIT_FLAG = EDIT_FLAG;
    }

    public String getACTIVITY_MONTH() {
        return ACTIVITY_MONTH;
    }

    public void setACTIVITY_MONTH(String ACTIVITY_MONTH) {
        this.ACTIVITY_MONTH = ACTIVITY_MONTH;
    }
    
    
	
}
