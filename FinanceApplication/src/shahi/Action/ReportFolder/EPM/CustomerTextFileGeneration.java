package shahi.Action.ReportFolder.EPM;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.service.PopulateListService;

public class CustomerTextFileGeneration extends ActionSupport{

	private static final long serialVersionUID = 2829281862930302676L;

	private List<Code>companyList;
	private List<Code>divisionList;
	private List<Code>yearList;
	private List<Code>voucherList;
	private List<Code>voucherTypeList;
	private List<String>vchtype;
	private List<Code>vendorList;
	private List<String>selectedVendors;
	private String voucherType;
	private String vendorCodes;
	private String fromDate;
	private String toDate;
	private String year;
	private String division;
	private PopulateListService getUtility(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						ServletActionContext.getServletContext());
		PopulateListService  service=(PopulateListService)context.getBean(PopulateListService.class);

		return service;
	}
	@Override
	public String execute(){
		populateMaster();
		return SUCCESS;
	}

	private void populateMaster(){
		divisionList=new ArrayList<>(0);
		yearList=new ArrayList<>(0);
		divisionList.add(new Code("0","Select Division"));
		yearList.add(new Code("0","Select Year"));
		voucherList=getUtility().getAllVouchers();
		voucherTypeList=new ArrayList<>(0);
		vendorList=new ArrayList<>(0);
	}
	public String generateTextFile(){
		System.out.println("Voucher Type"+getVchtype().size());
		System.out.println("Vendors Type"+getVendorCodes());
		System.out.println("Divsion"+getDivision());
		System.out.println("Year "+getYear());
		System.out.println("Voucher Types "+getVoucherType());
		String voucherTypes="";
		String vendorCodes="";
		if(getVendorCodes()!=null){
			String temp[]=getVendorCodes().split("#");
			for(int i=0;i<temp.length;i++){
				if(i!=temp.length-1){
					vendorCodes+=temp[i]+",";
				}else{
					vendorCodes+=temp[i];
				}
			}
		}
		if(getVoucherType()!=null){
			String temp[]=getVoucherType().split("#");
			for(int i=0;i<temp.length;i++){
				if(i!=temp.length-1){
					voucherTypes+=temp[i]+",";
				}else{
					voucherTypes+=temp[i];
				}
			}
		}
		System.out.println("vendorCodes"+vendorCodes);
		System.out.println("voucherTypes"+voucherTypes);
		populateMaster();
		return SUCCESS;
	}
	public List<Code> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Code> companyList) {
		this.companyList = companyList;
	}

	public List<Code> getDivisionList() {
		return divisionList;
	}

	public void setDivisionList(List<Code> divisionList) {
		this.divisionList = divisionList;
	}

	public List<Code> getYearList() {
		return yearList;
	}

	public void setYearList(List<Code> yearList) {
		this.yearList = yearList;
	}
	public List<Code> getVoucherList() {
		return voucherList;
	}
	public void setVoucherList(List<Code> voucherList) {
		this.voucherList = voucherList;
	}
	public List<Code> getVoucherTypeList() {
		return voucherTypeList;
	}
	public void setVoucherTypeList(List<Code> voucherTypeList) {
		this.voucherTypeList = voucherTypeList;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public List<String> getVchtype() {
		return vchtype;
	}
	public void setVchtype(List<String> vchtype) {
		this.vchtype = vchtype;
	}
	public List<Code> getVendorList() {
		return vendorList;
	}
	public void setVendorList(List<Code> vendorList) {
		this.vendorList = vendorList;
	}
	public String getVendorCodes() {
		return vendorCodes;
	}
	public void setVendorCodes(String vendorCodes) {
		this.vendorCodes = vendorCodes;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDivision() {
		return division;
	}
	public List<String> getSelectedVendors() {
		return selectedVendors;
	}
	public void setSelectedVendors(List<String> selectedVendors) {
		this.selectedVendors = selectedVendors;
	}
	public void setDivision(String division) {
		this.division = division;
	}

}
