package shahi.Action.MvxExp.PRE.Beans;

public class Parameters {

	private String year;
	private String company;
	private String invoiceNo;
	private String locationCode;
	private String invoiceDate;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	@Override
	public String toString() {
		return "Parameters [year=" + year + ", company=" + company + ", invoiceNo=" + invoiceNo + ", locationCode="
				+ locationCode + "]";
	}
	
}
