package shahi.Action.ReportFolder.EPM.beans;

public class ScanPOMaster {

	private String poNo;
	private String poDate;
	private String vendorCode;
	private String vendorName;
	private String billToGSTN;
	private String vendorGSTN;
	private String voucherType;
	private String poWarehouse;
	
	public ScanPOMaster(){
		
	}
	
	public ScanPOMaster(String poNo, String poDate, String vendorCode, String vendorName, String billToGSTN,
			String vendorGSTN, String voucherType, String poWarehouse) {
		super();
		this.poNo = poNo;
		this.poDate = poDate;
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.billToGSTN = billToGSTN;
		this.vendorGSTN = vendorGSTN;
		this.voucherType = voucherType;
		this.poWarehouse = poWarehouse;
	}

	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getPoDate() {
		return poDate;
	}
	public void setPoDate(String poDate) {
		this.poDate = poDate;
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
	public String getBillToGSTN() {
		return billToGSTN;
	}
	public void setBillToGSTN(String billToGSTN) {
		this.billToGSTN = billToGSTN;
	}
	public String getVendorGSTN() {
		return vendorGSTN;
	}
	public void setVendorGSTN(String vendorGSTN) {
		this.vendorGSTN = vendorGSTN;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getPoWarehouse() {
		return poWarehouse;
	}
	public void setPoWarehouse(String poWarehouse) {
		this.poWarehouse = poWarehouse;
	}

	@Override
	public String toString() {
		return "ScanPOMaster [poNo=" + poNo + ", poDate=" + poDate + ", vendorCode=" + vendorCode + ", vendorName="
				+ vendorName + ", billToGSTN=" + billToGSTN + ", vendorGSTN=" + vendorGSTN + ", voucherType="
				+ voucherType + ", poWarehouse=" + poWarehouse + "]";
	}
	
}
