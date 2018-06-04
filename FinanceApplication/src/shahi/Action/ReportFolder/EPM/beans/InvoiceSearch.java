package shahi.Action.ReportFolder.EPM.beans;

public class InvoiceSearch {
	private String division;
	private String supplierCode;
	private String invoiceNo;
	private String invoiceDate;
	private String warehouse;
	private String userId;
	public InvoiceSearch(){
		
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
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
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "InvoiceSearch [division=" + division + ", supplierCode=" + supplierCode + ", invoiceNo=" + invoiceNo
				+ ", invoiceDate=" + invoiceDate + ", warehouse=" + warehouse + ", userId=" + userId + "]";
	}
	
	
}
