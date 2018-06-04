package shahi.Action.ReportFolder.EPM.beans;

public class ScanVendorMaster {

	private String vendorCode;
	private String vendorName;
	private String vendorGSTN;
	
	public ScanVendorMaster(){
		
	}
	public ScanVendorMaster(String vendorCode, String vendorName, String vendorGSTN) {
		super();
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.vendorGSTN = vendorGSTN;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorGSTN() {
		return vendorGSTN;
	}
	public void setVendorGSTN(String vendorGSTN) {
		this.vendorGSTN = vendorGSTN;
	}
	@Override
	public String toString() {
		return "ScanVendorMaster [vendorCode=" + vendorCode + ", vendorName=" + vendorName + ", vendorGSTN="
				+ vendorGSTN + "]";
	}
	
}
