package shahi.Action.ReportFolder.EPM.ajax;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.service.PopulateListService;

public class AjaxAction extends ActionSupport {
	private List<Code>cmpList;
	private List<Code>fileList;
	private List<Code>divisionList;
	
	private List<Code>yearList;
    
	private List<Code>vendorList;
	 
	private List<Code>voucherTypeList;
	public String populateDivision(){
		String company = ServletActionContext.getRequest().getParameter("company"); 
		divisionList=getService().getDivisionListByCompany(company);
		return SUCCESS;
	}
	public String populateYear(){
		String division = ServletActionContext.getRequest().getParameter("division"); 
		yearList=getService().getYearList(division);
		return SUCCESS;
	}
	
	public String searchVendor(){
		String customer = ServletActionContext.getRequest().getParameter("customer");
		vendorList=getService().getCustomers(customer.toUpperCase().trim());
		return SUCCESS;
	}
	public String populateVoucherType(){
		String division = ServletActionContext.getRequest().getParameter("division"); 
		voucherTypeList=getService().getVoucherByDivision(division);
		return SUCCESS;
	}
	public String populateFileType(){
		String division = ServletActionContext.getRequest().getParameter("division"); 
		setFileList(getService().getAllFileTypes(division));
		setCmpList(getService().getAllCompanies());
		return SUCCESS;
	}

	private PopulateListService getService(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext());
		PopulateListService service=(PopulateListService)context.getBean(PopulateListService.class);
		
		return service;
	}
	public List<Code> getCmpList() {
		return cmpList;
	}

	public void setCmpList(List<Code> cmpList) {
		this.cmpList = cmpList;
	}

	public List<Code> getFileList() {
		return fileList;
	}

	public void setFileList(List<Code> fileList) {
		this.fileList = fileList;
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
	public List<Code> getVoucherTypeList() {
		return voucherTypeList;
	}
	public void setVoucherTypeList(List<Code> voucherTypeList) {
		this.voucherTypeList = voucherTypeList;
	}
	public List<Code> getVendorList() {
		return vendorList;
	}
	public void setVendorList(List<Code> vendorList) {
		this.vendorList = vendorList;
	}
	
}
