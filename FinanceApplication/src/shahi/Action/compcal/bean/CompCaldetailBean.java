package shahi.Action.compcal.bean;

public class CompCaldetailBean {
	private String ACTIVITY_ID;
	private String COMPANY_ID; 
	private String CALENDER_TYPE_CODE;
	private String ACT_FLAG;
	
	public CompCaldetailBean() {
		super();
	}
	public CompCaldetailBean(String aCTIVITY_ID,
			String cOMPANY_ID, String cALENDER_TYPE_CODE,String aCT_FLAG) {
		super();
		ACTIVITY_ID = aCTIVITY_ID;
		COMPANY_ID = cOMPANY_ID;
		CALENDER_TYPE_CODE = cALENDER_TYPE_CODE;
		ACT_FLAG = aCT_FLAG;
	}
	public String getACTIVITY_ID() {
		return ACTIVITY_ID;
	}
	public void setACTIVITY_ID(String aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}	
	public String getCOMPANY_ID() {
		return COMPANY_ID;
	}
	public void setCOMPANY_ID(String cOMPANY_ID) {
		COMPANY_ID = cOMPANY_ID;
	}
	public String getCALENDER_TYPE_CODE() {
		return CALENDER_TYPE_CODE;
	}
	public void setCALENDER_TYPE_CODE(String cALENDER_TYPE_CODE) {
		CALENDER_TYPE_CODE = cALENDER_TYPE_CODE;
	}
	public String getACT_FLAG() {
		return ACT_FLAG;
	}
	public void setACT_FLAG(String aCT_FLAG) {
		ACT_FLAG = aCT_FLAG;
	}
	
}
