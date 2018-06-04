package shahi.Action.ReportFolder.oldtonewscanner;

import java.sql.Date;

public class OldToNewScanner {

	private String INVOICENUMBER;
	private String PO;
	private String DATE1;
	private String TOTAL;
	private String WAREHOUSE;
	private String STATUS_FLAG;
	private String MVXSUNO;
	private String TDATE;
	private String DOC_FLAG;
	private String CYEAR;
	private String SEH_USER;
	
	public OldToNewScanner(){
		
	}

	public String getINVOICENUMBER() {
		return INVOICENUMBER;
	}

	public void setINVOICENUMBER(String iNVOICENUMBER) {
		INVOICENUMBER = iNVOICENUMBER;
	}

	public String getPO() {
		return PO;
	}

	public void setPO(String pO) {
		PO = pO;
	}

	
	

	public String getWAREHOUSE() {
		return WAREHOUSE;
	}

	public void setWAREHOUSE(String wAREHOUSE) {
		WAREHOUSE = wAREHOUSE;
	}

	public String getSTATUS_FLAG() {
		return STATUS_FLAG;
	}

	public void setSTATUS_FLAG(String sTATUS_FLAG) {
		STATUS_FLAG = sTATUS_FLAG;
	}

	public String getMVXSUNO() {
		return MVXSUNO;
	}

	public void setMVXSUNO(String mVXSUNO) {
		MVXSUNO = mVXSUNO;
	}



	public String getDOC_FLAG() {
		return DOC_FLAG;
	}

	public void setDOC_FLAG(String dOC_FLAG) {
		DOC_FLAG = dOC_FLAG;
	}

	public String getCYEAR() {
		return CYEAR;
	}

	public void setCYEAR(String cYEAR) {
		CYEAR = cYEAR;
	}

	public String getSEH_USER() {
		return SEH_USER;
	}

	public void setSEH_USER(String sEH_USER) {
		SEH_USER = sEH_USER;
	}

	
	public String getDATE1() {
		return DATE1;
	}

	public void setDATE1(String dATE1) {
		DATE1 = dATE1;
	}

	public String getTDATE() {
		return TDATE;
	}

	public void setTDATE(String tDATE) {
		TDATE = tDATE;
	}

	public String getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(String tOTAL) {
		TOTAL = tOTAL;
	}

	@Override
	public String toString() {
		return "OldToNewScanner [INVOICENUMBER=" + INVOICENUMBER + ", PO=" + PO + ", DATE1=" + DATE1 + ", TOTAL="
				+ TOTAL + ", WAREHOUSE=" + WAREHOUSE + ", STATUS_FLAG=" + STATUS_FLAG + ", MVXSUNO=" + MVXSUNO
				+ ", TDATE=" + TDATE + ", DOC_FLAG=" + DOC_FLAG + ", CYEAR=" + CYEAR + ", SEH_USER=" + SEH_USER + "]";
	}

	

}
