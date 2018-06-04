package shahi.Action.ReportFolder.EPM.beans;

import java.sql.Date;

public class ScanInvoiceDetail {

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
	private String poNo;
	public ScanInvoiceDetail(){
		
	}
	public ScanInvoiceDetail(String invoiceNo, Date invoiceDate, String vendorCode, Double invoiceAmount,
			String voucherType, String locationCode, String vendorGSTN, String shahiGSTN, String authorizer,
			String userType, String userId, Date tDate, String division, String financialYear, String updFlag,String poNo) {
		super();
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.vendorCode = vendorCode;
		this.invoiceAmount = invoiceAmount;
		this.voucherType = voucherType;
		this.locationCode = locationCode;
		this.vendorGSTN = vendorGSTN;
		this.shahiGSTN = shahiGSTN;
		this.authorizer = authorizer;
		this.userType = userType;
		this.userId = userId;
		this.tDate = tDate;
		this.division = division;
		this.financialYear = financialYear;
		this.updFlag = updFlag;
		this.poNo=poNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date gettDate() {
		return tDate;
	}
	public void settDate(Date tDate) {
		this.tDate = tDate;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	@Override
	public String toString() {
		return "ScanInvoiceDetail [invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", vendorCode="
				+ vendorCode + ", invoiceAmount=" + invoiceAmount + ", voucherType=" + voucherType + ", locationCode="
				+ locationCode + ", vendorGSTN=" + vendorGSTN + ", shahiGSTN=" + shahiGSTN + ", authorizer="
				+ authorizer + ", userType=" + userType + ", userId=" + userId + ", tDate=" + tDate + ", division="
				+ division + ", financialYear=" + financialYear + ", updFlag=" + updFlag + ", poNo=" + poNo + "]";
	}
	
	
}
