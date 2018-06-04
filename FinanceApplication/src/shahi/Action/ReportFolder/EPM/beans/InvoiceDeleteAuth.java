package shahi.Action.ReportFolder.EPM.beans;

public class InvoiceDeleteAuth {

	private String EMPCODE;
	private String DLUSID;
	private String TDATE;
	private String warehouse;
	public InvoiceDeleteAuth(){
		
	}

	public String getEMPCODE() {
		return EMPCODE;
	}

	public void setEMPCODE(String eMPCODE) {
		EMPCODE = eMPCODE;
	}

	public String getDLUSID() {
		return DLUSID;
	}

	public void setDLUSID(String dLUSID) {
		DLUSID = dLUSID;
	}

	public String getTDATE() {
		return TDATE;
	}

	public void setTDATE(String tDATE) {
		TDATE = tDATE;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public String toString() {
		return "InvoiceDeleteAuth [EMPCODE=" + EMPCODE + ", DLUSID=" + DLUSID + ", TDATE=" + TDATE + "]";
	}
	
}
