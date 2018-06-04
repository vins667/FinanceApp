package shahi.Action.FundReq.Beans;

import java.util.Date;

import shahi.Action.FundReq.Query;

public class FundReqQuery implements Query{

	private String reqNo;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String sDateFrom;
	private String sDateTo;
	private String party;
	
	private String status;
	
	private String reqType;
	
	private String requestedTo;
	private String requestedBy;
	private String poNo;
	private String userId;
	
	public FundReqQuery(){
		
	}
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getRequestedTo() {
		return requestedTo;
	}
	public void setRequestedTo(String requestedTo) {
		this.requestedTo = requestedTo;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public String getsDateFrom() {
		return sDateFrom;
	}
	public void setsDateFrom(String sDateFrom) {
		this.sDateFrom = sDateFrom;
	}
	public String getsDateTo() {
		return sDateTo;
	}
	public void setsDateTo(String sDateTo) {
		this.sDateTo = sDateTo;
	}
	
	
}
