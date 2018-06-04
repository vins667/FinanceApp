package shahi.Action.MvxExp.PRE.Beans;

public class PreInvoiceHeader {

	private String locationCode;
	private String ciNo;
	private String ciDate;
	private String invoiceNo;
	private String invoiceDate;
	private String invoiceState;
	private String planNo;
	private String accountHolder;
	private String pprqDate;
	private String shipCnxl;
	private String place;
	private String merchant;
	private String fwdToCustom;
	private String fwdToCustomDate;
	private String removed;
	private String outHouse;
	private String clearingPort;
	private String lcNo;
	private String ttoDate;
	private String upcharge;
	private String loadingPort;
	private String invoiceQty;
	private String notify;
	private String toDate;
	private String commision;
	private String discharge;
	private String invoiceType;
	private String agent;
	private String etdDate;
	private String transportRate;
	private String dischargeCntry;
	private String shipMode;
	private String forwarder;
	private String taxCode;
	private String taxCalFob;
	private String originCntry;
	private String preCarriage;
	private String manufacturer;
	private String taxDesc;
	private String taxPer;
	private String totalCartons;
	private String destination;
	private String pch;
	private String coPayterm;
	private String hsCode;
	private String totalGrossWeight;
	private String destinationCntry;
	private String currency;
	private String deliveryTerms;
	private String fowardDate;
	private String totalNetWeight;
	private String fobfc;
	private String grDecl;
	private String expType;
	private String paymentTerm;
	private String remarks;
	private String netFob;
	private String lutIGST;
	private String company;
	private String year;
	private String invNo;
	private BuyerBean buyer;
	private BuyerBean consignee;
	private String facility;
	private String itemGroup;
	private String gstInState;
	private String geoCode;
	private String inrConversion;
	
	public PreInvoiceHeader(){
		buyer=new BuyerBean();
		consignee=new BuyerBean();
	}
	
	

	public PreInvoiceHeader(String locationCode, String ciNo, String ciDate, String invoiceNo, String invoiceDate,
			String invoiceState, String planNo, String accountHolder, String pprqDate, String shipCnxl, String place,
			String merchant, String fwdToCustom, String fwdToCustomDate, String removed, String outHouse,
			String clearingPort, String lcNo, String ttoDate, String upcharge, String loadingPort, String invoiceQty,
			String notify, String toDate, String commision, String discharge, String invoiceType, String agent,
			String etdDate, String transportRate, String dischargeCntry, String shipMode, String forwarder,
			String taxCode, String taxCalFob, String originCntry, String preCarriage, String manufacturer,
			String taxDesc, String taxPer, String totalCartons, String destination, String pch, String coPayterm,
			String hsCode, String totalGrossWeight, String destinationCntry, String currency, String deliveryTerms,
			String fowardDate, String totalNetWeight, String fobfc, String grDecl, String expType, String paymentTerm,
			String remarks, String netFob, String lutIGST, String company, String year, String invNo) {
		super();
		this.locationCode = locationCode;
		this.ciNo = ciNo;
		this.ciDate = ciDate;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.invoiceState = invoiceState;
		this.planNo = planNo;
		this.accountHolder = accountHolder;
		this.pprqDate = pprqDate;
		this.shipCnxl = shipCnxl;
		this.place = place;
		this.merchant = merchant;
		this.fwdToCustom = fwdToCustom;
		this.fwdToCustomDate = fwdToCustomDate;
		this.removed = removed;
		this.outHouse = outHouse;
		this.clearingPort = clearingPort;
		this.lcNo = lcNo;
		this.ttoDate = ttoDate;
		this.upcharge = upcharge;
		this.loadingPort = loadingPort;
		this.invoiceQty = invoiceQty;
		this.notify = notify;
		this.toDate = toDate;
		this.commision = commision;
		this.discharge = discharge;
		this.invoiceType = invoiceType;
		this.agent = agent;
		this.etdDate = etdDate;
		this.transportRate = transportRate;
		this.dischargeCntry = dischargeCntry;
		this.shipMode = shipMode;
		this.forwarder = forwarder;
		this.taxCode = taxCode;
		this.taxCalFob = taxCalFob;
		this.originCntry = originCntry;
		this.preCarriage = preCarriage;
		this.manufacturer = manufacturer;
		this.taxDesc = taxDesc;
		this.taxPer = taxPer;
		this.totalCartons = totalCartons;
		this.destination = destination;
		this.pch = pch;
		this.coPayterm = coPayterm;
		this.hsCode = hsCode;
		this.totalGrossWeight = totalGrossWeight;
		this.destinationCntry = destinationCntry;
		this.currency = currency;
		this.deliveryTerms = deliveryTerms;
		this.fowardDate = fowardDate;
		this.totalNetWeight = totalNetWeight;
		this.fobfc = fobfc;
		this.grDecl = grDecl;
		this.expType = expType;
		this.paymentTerm = paymentTerm;
		this.remarks = remarks;
		this.netFob = netFob;
		this.lutIGST = lutIGST;
		this.company = company;
		this.year = year;
		this.invNo = invNo;
	}



	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getCiNo() {
		return ciNo;
	}
	public void setCiNo(String ciNo) {
		this.ciNo = ciNo;
	}
	public String getCiDate() {
		return ciDate;
	}
	public void setCiDate(String ciDate) {
		this.ciDate = ciDate;
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
	public String getPlanNo() {
		return planNo;
	}
	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getPprqDate() {
		return pprqDate;
	}
	public void setPprqDate(String pprqDate) {
		this.pprqDate = pprqDate;
	}
	public String getShipCnxl() {
		return shipCnxl;
	}
	public void setShipCnxl(String shipCnxl) {
		this.shipCnxl = shipCnxl;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getFwdToCustom() {
		return fwdToCustom;
	}
	public void setFwdToCustom(String fwdToCustom) {
		this.fwdToCustom = fwdToCustom;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public String getOutHouse() {
		return outHouse;
	}
	public void setOutHouse(String outHouse) {
		this.outHouse = outHouse;
	}
	public String getClearingPort() {
		return clearingPort;
	}
	public void setClearingPort(String clearingPort) {
		this.clearingPort = clearingPort;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getTtoDate() {
		return ttoDate;
	}
	public void setTtoDate(String ttoDate) {
		this.ttoDate = ttoDate;
	}
	public String getUpcharge() {
		return upcharge;
	}
	public void setUpcharge(String upcharge) {
		this.upcharge = upcharge;
	}
	public String getLoadingPort() {
		return loadingPort;
	}
	public void setLoadingPort(String loadingPort) {
		this.loadingPort = loadingPort;
	}
	public String getInvoiceQty() {
		return invoiceQty;
	}
	public void setInvoiceQty(String invoiceQty) {
		this.invoiceQty = invoiceQty;
	}
	public String getNotify() {
		return notify;
	}
	public void setNotify(String notify) {
		this.notify = notify;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getCommision() {
		return commision;
	}
	public void setCommision(String commision) {
		this.commision = commision;
	}
	public String getDischarge() {
		return discharge;
	}
	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getEtdDate() {
		return etdDate;
	}
	public void setEtdDate(String etdDate) {
		this.etdDate = etdDate;
	}
	public String getTransportRate() {
		return transportRate;
	}
	public void setTransportRate(String transportRate) {
		this.transportRate = transportRate;
	}
	public String getDischargeCntry() {
		return dischargeCntry;
	}
	public void setDischargeCntry(String dischargeCntry) {
		this.dischargeCntry = dischargeCntry;
	}
	public String getShipMode() {
		return shipMode;
	}
	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}
	public String getForwarder() {
		return forwarder;
	}
	public void setForwarder(String forwarder) {
		this.forwarder = forwarder;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getTaxCalFob() {
		return taxCalFob;
	}
	public void setTaxCalFob(String taxCalFob) {
		this.taxCalFob = taxCalFob;
	}
	public String getOriginCntry() {
		return originCntry;
	}
	public void setOriginCntry(String originCntry) {
		this.originCntry = originCntry;
	}
	public String getPreCarriage() {
		return preCarriage;
	}
	public void setPreCarriage(String preCarriage) {
		this.preCarriage = preCarriage;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getTaxDesc() {
		return taxDesc;
	}
	public void setTaxDesc(String taxDesc) {
		this.taxDesc = taxDesc;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getPch() {
		return pch;
	}
	public void setPch(String pch) {
		this.pch = pch;
	}
	public String getCoPayterm() {
		return coPayterm;
	}
	public void setCoPayterm(String coPayterm) {
		this.coPayterm = coPayterm;
	}
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public String getTotalGrossWeight() {
		return totalGrossWeight;
	}
	public void setTotalGrossWeight(String totalGrossWeight) {
		this.totalGrossWeight = totalGrossWeight;
	}
	public String getDestinationCntry() {
		return destinationCntry;
	}
	public void setDestinationCntry(String destinationCntry) {
		this.destinationCntry = destinationCntry;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDeliveryTerms() {
		return deliveryTerms;
	}
	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}
	
	public String getTotalNetWeight() {
		return totalNetWeight;
	}
	public void setTotalNetWeight(String totalNetWeight) {
		this.totalNetWeight = totalNetWeight;
	}
	public String getFobfc() {
		return fobfc;
	}
	public void setFobfc(String fobfc) {
		this.fobfc = fobfc;
	}
	public String getGrDecl() {
		return grDecl;
	}
	public void setGrDecl(String grDecl) {
		this.grDecl = grDecl;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getNetFob() {
		return netFob;
	}
	public void setNetFob(String netFob) {
		this.netFob = netFob;
	}
	public String getLutIGST() {
		return lutIGST;
	}
	public void setLutIGST(String lutIGST) {
		this.lutIGST = lutIGST;
	}

	public String getInvoiceState() {
		return invoiceState;
	}

	public void setInvoiceState(String invoiceState) {
		this.invoiceState = invoiceState;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getTotalCartons() {
		return totalCartons;
	}

	public void setTotalCartons(String totalCartons) {
		this.totalCartons = totalCartons;
	}

	public String getFowardDate() {
		return fowardDate;
	}

	public void setFowardDate(String fowardDate) {
		this.fowardDate = fowardDate;
	}

	public String getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(String taxPer) {
		this.taxPer = taxPer;
	}



	

	public String getFwdToCustomDate() {
		return fwdToCustomDate;
	}



	public void setFwdToCustomDate(String fwdToCustomDate) {
		this.fwdToCustomDate = fwdToCustomDate;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getInvNo() {
		return invNo;
	}



	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}



	public BuyerBean getBuyer() {
		return buyer;
	}



	public BuyerBean getConsignee() {
		return consignee;
	}



	public void setConsignee(BuyerBean consignee) {
		this.consignee = consignee;
	}



	public void setBuyer(BuyerBean buyer) {
		this.buyer = buyer;
	}



	public String getFacility() {
		return facility;
	}



	public void setFacility(String facility) {
		this.facility = facility;
	}



	public String getItemGroup() {
		return itemGroup;
	}



	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}



	public String getGstInState() {
		return gstInState;
	}



	public void setGstInState(String gstInState) {
		this.gstInState = gstInState;
	}



	public String getGeoCode() {
		return geoCode;
	}



	public void setGeoCode(String geoCode) {
		this.geoCode = geoCode;
	}



	public String getInrConversion() {
		return inrConversion;
	}



	public void setInrConversion(String inrConversion) {
		this.inrConversion = inrConversion;
	}



	@Override
	public String toString() {
		return "PreInvoiceHeader [locationCode=" + locationCode + ", ciNo=" + ciNo + ", ciDate=" + ciDate
				+ ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", invoiceState=" + invoiceState
				+ ", planNo=" + planNo + ", accountHolder=" + accountHolder + ", pprqDate=" + pprqDate + ", shipCnxl="
				+ shipCnxl + ", place=" + place + ", merchant=" + merchant + ", fwdToCustom=" + fwdToCustom
				+ ", fwdToCustomDate=" + fwdToCustomDate + ", removed=" + removed + ", outHouse=" + outHouse
				+ ", clearingPort=" + clearingPort + ", lcNo=" + lcNo + ", ttoDate=" + ttoDate + ", upcharge="
				+ upcharge + ", loadingPort=" + loadingPort + ", invoiceQty=" + invoiceQty + ", notify=" + notify
				+ ", toDate=" + toDate + ", commision=" + commision + ", discharge=" + discharge + ", invoiceType="
				+ invoiceType + ", agent=" + agent + ", etdDate=" + etdDate + ", transportRate=" + transportRate
				+ ", dischargeCntry=" + dischargeCntry + ", shipMode=" + shipMode + ", forwarder=" + forwarder
				+ ", taxCode=" + taxCode + ", taxCalFob=" + taxCalFob + ", originCntry=" + originCntry
				+ ", preCarriage=" + preCarriage + ", manufacturer=" + manufacturer + ", taxDesc=" + taxDesc
				+ ", taxPer=" + taxPer + ", totalCartons=" + totalCartons + ", destination=" + destination + ", pch="
				+ pch + ", coPayterm=" + coPayterm + ", hsCode=" + hsCode + ", totalGrossWeight=" + totalGrossWeight
				+ ", destinationCntry=" + destinationCntry + ", currency=" + currency + ", deliveryTerms="
				+ deliveryTerms + ", fowardDate=" + fowardDate + ", totalNetWeight=" + totalNetWeight + ", fobfc="
				+ fobfc + ", grDecl=" + grDecl + ", expType=" + expType + ", paymentTerm=" + paymentTerm + ", remarks="
				+ remarks + ", netFob=" + netFob + ", lutIGST=" + lutIGST + ", company=" + company + ", year=" + year
				+ ", invNo=" + invNo + ", buyer=" + buyer + ", consignee=" + consignee + ", facility=" + facility
				+ ", itemGroup=" + itemGroup + ", gstInState=" + gstInState + ", geoCode=" + geoCode
				+ ", inrConversion=" + inrConversion + "]";
	}


}
