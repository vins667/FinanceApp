package shahi.Action.ReportFolder.EPM.beans;

public class DataTexSearchBean {

	private String division;
	private String voucherType;
	private String supplierCode;
	private String invoiceNo;
	private String invoiceDate;
	private String eventCode;
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	@Override
	public String toString() {
		return "DataTexSearchBean [division=" + division + ", voucherType=" + voucherType + ", supplierCode="
				+ supplierCode + ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", eventCode="
				+ eventCode + "]";
	}
	
	
}
