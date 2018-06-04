package shahi.Action.PayTracker.bean;

import java.util.Date;

public class DatatexPaymentTracker {

	private String divisionCode;
	private String status;
	private String gateDate;
	private String mrnDate;
	private String recvAtAcct;
	private String piCreation;
	private String partyName;
	private String piInvno;
	private String piInvDate;
	private String purchaseOrderCode;
	private String invoiceCurrencyCode;
	private String invoiceAmount;
	private String remarks;
	private String responsible;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((piInvno == null) ? 0 : piInvno.hashCode());
		result = prime * result + ((purchaseOrderCode == null) ? 0 : purchaseOrderCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatatexPaymentTracker other = (DatatexPaymentTracker) obj;
		if (piInvno == null) {
			if (other.piInvno != null)
				return false;
		} else if (!piInvno.equals(other.piInvno))
			return false;
		if (purchaseOrderCode == null) {
			if (other.purchaseOrderCode != null)
				return false;
		} else if (!purchaseOrderCode.equals(other.purchaseOrderCode))
			return false;
		return true;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRecvAtAcct() {
		return recvAtAcct;
	}
	public void setRecvAtAcct(String recvAtAcct) {
		this.recvAtAcct = recvAtAcct;
	}
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPiInvno() {
		return piInvno;
	}
	public void setPiInvno(String piInvno) {
		this.piInvno = piInvno;
	}
	
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}
	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}
	public String getInvoiceCurrencyCode() {
		return invoiceCurrencyCode;
	}
	public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
		this.invoiceCurrencyCode = invoiceCurrencyCode;
	}
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	
	public String getGateDate() {
		return gateDate;
	}
	public void setGateDate(String gateDate) {
		this.gateDate = gateDate;
	}
	public String getMrnDate() {
		return mrnDate;
	}
	public void setMrnDate(String mrnDate) {
		this.mrnDate = mrnDate;
	}
	public String getPiCreation() {
		return piCreation;
	}
	public void setPiCreation(String piCreation) {
		this.piCreation = piCreation;
	}
	public String getPiInvDate() {
		return piInvDate;
	}
	public void setPiInvDate(String piInvDate) {
		this.piInvDate = piInvDate;
	}
	@Override
	public String toString() {
		return "DatatexPaymentTracker [divisionCode=" + divisionCode + ", status=" + status + ", gateDate=" + gateDate
				+ ", mrnDate=" + mrnDate + ", recvAtAcct=" + recvAtAcct + ", piCreation=" + piCreation + ", partyName="
				+ partyName + ", piInvno=" + piInvno + ", piInvDate=" + piInvDate + ", purchaseOrderCode="
				+ purchaseOrderCode + ", invoiceCurrencyCode=" + invoiceCurrencyCode + ", invoiceAmount="
				+ invoiceAmount + ", remarks=" + remarks + ", responsible=" + responsible + "]";
	}
	
}
