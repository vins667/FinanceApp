package shahi.Action.ReportFolder.EPM.beans;

public class RequestBean {
	private Supplier supplier;
	private String tdsCode;
	private String freight;
	private Integer lcFG;
	private Integer accountingFlag;
	private String updateFlag;
	private Integer poNonFlag;
	private String  poCurrency;
	
	public RequestBean(Supplier supplier, String tdsCode, String freight,Integer lcfg,Integer accountingFlag) {
		super();
		this.supplier = supplier;
		this.tdsCode = tdsCode;
		this.freight = freight;
		this.lcFG=lcfg;
		this.accountingFlag=accountingFlag;
	}

	public String getTdsCode() {
		return tdsCode;
	}

	public void setTdsCode(String tdsCode) {
		this.tdsCode = tdsCode;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public RequestBean(){

	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getLcFG() {
		return lcFG;
	}

	public void setLcFG(Integer lcFG) {
		this.lcFG = lcFG;
	}

	public Integer getAccountingFlag() {
		return accountingFlag;
	}

	public void setAccountingFlag(Integer accountingFlag) {
		this.accountingFlag = accountingFlag;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Integer getPoNonFlag() {
		return poNonFlag;
	}

	public void setPoNonFlag(Integer poNonFlag) {
		this.poNonFlag = poNonFlag;
	}

	public String getPoCurrency() {
		return poCurrency;
	}

	public void setPoCurrency(String poCurrency) {
		this.poCurrency = poCurrency;
	}

	@Override
	public String toString() {
		return "RequestBean [supplier=" + supplier + ", tdsCode=" + tdsCode + ", freight=" + freight + ", lcFG=" + lcFG
				+ ", accountingFlag=" + accountingFlag + ", updateFlag=" + updateFlag + ", poNonFlag=" + poNonFlag
				+ "]";
	}

	


}
