package shahi.Action.ReportFolder.EPM.beans;

import java.sql.Date;

public class ScanInvoiceDeleteHistory {
	private String invoiceNo;
	private Date invoiceDate;
	private String vendorCode;
	private Double invoiceAmount;
	private String voucherType;
	private String locationCode;
	private String vendorGSTN;
	private String shahiGSTN;
	private String authorizer;
	private String userType;
	private String userId;
	private Date tDate;
	private String division;
	private String financialYear;
	private String updFlag;
	private Long poNo;
	public ScanInvoiceDeleteHistory(){
		
	}
	
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getVendorGSTN() {
		return vendorGSTN;
	}
	public void setVendorGSTN(String vendorGSTN) {
		this.vendorGSTN = vendorGSTN;
	}
	public String getShahiGSTN() {
		return shahiGSTN;
	}
	public void setShahiGSTN(String shahiGSTN) {
		this.shahiGSTN = shahiGSTN;
	}
	public String getAuthorizer() {
		return authorizer;
	}
	public void setAuthorizer(String authorizer) {
		this.authorizer = authorizer;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date gettDate() {
		return tDate;
	}
	public void settDate(Date tDate) {
		this.tDate = tDate;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getUpdFlag() {
		return updFlag;
	}
	public void setUpdFlag(String updFlag) {
		this.updFlag = updFlag;
	}
	public Long getPoNo() {
		return poNo;
	}
	public void setPoNo(Long poNo) {
		this.poNo = poNo;
	}
	@Override
	public String toString() {
		return "ScanInvoiceDeleteHistory [invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", vendorCode="
				+ vendorCode + ", invoiceAmount=" + invoiceAmount + ", voucherType=" + voucherType + ", locationCode="
				+ locationCode + ", vendorGSTN=" + vendorGSTN + ", shahiGSTN=" + shahiGSTN + ", authorizer="
				+ authorizer + ", userType=" + userType + ", userId=" + userId + ", tDate=" + tDate + ", division="
				+ division + ", financialYear=" + financialYear + ", updFlag=" + updFlag + ", poNo=" + poNo + "]";
	}

}
