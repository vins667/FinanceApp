package shahi.Action.ReportFolder.EPM.beans;

public class Supplier {
	private String supplierOne;
	private String supplierTwo;
	private String currency;
	private String vendorType;
	
	public Supplier(String supplierOne, String supplierTwo) {
		super();
		this.supplierOne = supplierOne;
		this.supplierTwo = supplierTwo;
	}
	public Supplier(String supplierOne, String supplierTwo, String currency, String vendorType) {
		super();
		this.supplierOne = supplierOne;
		this.supplierTwo = supplierTwo;
		this.currency = currency;
		this.vendorType = vendorType;
	}
	public String getSupplierOne() {
		return supplierOne;
	}
	public void setSupplierOne(String supplierOne) {
		this.supplierOne = supplierOne;
	}
	public String getSupplierTwo() {
		return supplierTwo;
	}
	public void setSupplierTwo(String supplierTwo) {
		this.supplierTwo = supplierTwo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public Supplier(){
		
	}
	@Override
	public String toString() {
		return "Supplier [supplierOne=" + supplierOne + ", supplierTwo=" + supplierTwo + ", currency=" + currency
				+ ", vendorType=" + vendorType + "]";
	}
	
	

}
