package shahi.Action.PayTracker.bean;

public class PaymentTracker {

	private String division;
	private String partyName;
	private Double reqAmount;
	private String reqPurpose;
	private Long   reqNumber;
	private String reqDate;
	private String noOfDays;
	private String chequeNo;
	private String chequeDate;
	private String utrDate;
	private String paymentLeadTimePending;
	private String age;
	private String paymentStatus;
	private String reqStatus;
	private boolean zeroToSixDays;
	private boolean sixToFifteenDays;
	private boolean fiteenAndAboveDays;
	public PaymentTracker(){
		
	}


	public PaymentTracker(String division, String partyName, Double reqAmount, String reqPurpose, Long reqNumber,
			String reqDate, String noOfDays, String chequeNo, String chequeDate, String utrDate,
			String paymentLeadTimePending, String age) {
		super();
		this.division = division;
		this.partyName = partyName;
		this.reqAmount = reqAmount;
		this.reqPurpose = reqPurpose;
		this.reqNumber = reqNumber;
		this.reqDate = reqDate;
		this.noOfDays = noOfDays;
		this.chequeNo = chequeNo;
		this.chequeDate = chequeDate;
		this.utrDate = utrDate;
		this.paymentLeadTimePending = paymentLeadTimePending;
		this.age = age;
	}


	public String getDivision() {
		return division;
	}


	public void setDivision(String division) {
		this.division = division;
	}


	public String getPartyName() {
		return partyName;
	}


	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	public Double getReqAmount() {
		return reqAmount;
	}


	public void setReqAmount(Double reqAmount) {
		this.reqAmount = reqAmount;
	}


	public String getReqPurpose() {
		return reqPurpose;
	}


	public void setReqPurpose(String reqPurpose) {
		this.reqPurpose = reqPurpose;
	}


	public String getReqDate() {
		return reqDate;
	}


	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}


	public String getNoOfDays() {
		return noOfDays;
	}


	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}


	public String getChequeNo() {
		return chequeNo;
	}


	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}


	public String getChequeDate() {
		return chequeDate;
	}


	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}


	public String getUtrDate() {
		return utrDate;
	}


	public void setUtrDate(String utrDate) {
		this.utrDate = utrDate;
	}


	public String getPaymentLeadTimePending() {
		return paymentLeadTimePending;
	}


	public void setPaymentLeadTimePending(String paymentLeadTimePending) {
		this.paymentLeadTimePending = paymentLeadTimePending;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public Long getReqNumber() {
		return reqNumber;
	}


	public void setReqNumber(Long reqNumber) {
		this.reqNumber = reqNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reqNumber == null) ? 0 : reqNumber.hashCode());
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
		PaymentTracker other = (PaymentTracker) obj;
		if (reqNumber == null) {
			if (other.reqNumber != null)
				return false;
		} else if (!reqNumber.equals(other.reqNumber))
			return false;
		return true;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	

	public boolean isZeroToSixDays() {
		return zeroToSixDays;
	}


	public void setZeroToSixDays(boolean zeroToSixDays) {
		this.zeroToSixDays = zeroToSixDays;
	}


	public boolean isSixToFifteenDays() {
		return sixToFifteenDays;
	}


	public void setSixToFifteenDays(boolean sixToFifteenDays) {
		this.sixToFifteenDays = sixToFifteenDays;
	}


	public boolean isFiteenAndAboveDays() {
		return fiteenAndAboveDays;
	}


	public void setFiteenAndAboveDays(boolean fiteenAndAboveDays) {
		this.fiteenAndAboveDays = fiteenAndAboveDays;
	}


	public String getReqStatus() {
		return reqStatus;
	}


	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}


	@Override
	public String toString() {
		return "PayTracker [division=" + division + ", partyName=" + partyName + ", reqAmount=" + reqAmount
				+ ", reqPurpose=" + reqPurpose + ", reqNumber=" + reqNumber + ", reqDate=" + reqDate + ", noOfDays="
				+ noOfDays + ", chequeNo=" + chequeNo + ", chequeDate=" + chequeDate + ", utrDate=" + utrDate
				+ ", paymentLeadTimePending=" + paymentLeadTimePending + ", age=" + age + "]";
	}

	
}
