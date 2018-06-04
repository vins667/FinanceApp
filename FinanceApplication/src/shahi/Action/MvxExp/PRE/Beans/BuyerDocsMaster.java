package shahi.Action.MvxExp.PRE.Beans;

public class BuyerDocsMaster {

	private String buyerGroup;
	private String destinationCountry;
	private String documentDesc;
	private  String tDate;
	private  String sehUser;
	private  Integer printOrder;
	
	private String closeDate;
	
	private String modUser;

	
	public BuyerDocsMaster(String buyerGroup, String destinationCountry, String documentDesc, String tDate,
			String sehUser, Integer printOrder, String closeDate, String modUser) {
		super();
		this.buyerGroup = buyerGroup;
		this.destinationCountry = destinationCountry;
		this.documentDesc = documentDesc;
		this.tDate = tDate;
		this.sehUser = sehUser;
		this.printOrder = printOrder;
		this.closeDate = closeDate;
		this.modUser = modUser;
	}

	public String getBuyerGroup() {
		return buyerGroup;
	}

	public void setBuyerGroup(String buyerGroup) {
		this.buyerGroup = buyerGroup;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getDocumentDesc() {
		return documentDesc;
	}

	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public String getSehUser() {
		return sehUser;
	}

	public void setSehUser(String sehUser) {
		this.sehUser = sehUser;
	}

	public Integer getPrintOrder() {
		return printOrder;
	}

	public void setPrintOrder(Integer printOrder) {
		this.printOrder = printOrder;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	@Override
	public String toString() {
		return "BuyerDocsMaster [buyerGroup=" + buyerGroup + ", destinationCountry=" + destinationCountry
				+ ", documentDesc=" + documentDesc + ", tDate=" + tDate + ", sehUser=" + sehUser + ", printOrder="
				+ printOrder + ", closeDate=" + closeDate + ", modUser=" + modUser + "]";
	}
	
}
