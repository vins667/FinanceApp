package shahi.Action.ReportFolder.EPM;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.ShippingBill;
import shahi.Action.ReportFolder.EPM.service.ShippingBillService;

public class ShippingBillAction extends ActionSupport{

	private String exciseInvoiceNo;
	private List<ShippingBill>billList;
	
	private String shippingBillNo;
	private String shippingBillDate;
	
	public String execute(){
		if(exciseInvoiceNo!=null && !exciseInvoiceNo.isEmpty()){
			shippingBillNo=null;
			shippingBillDate=null;
			setBillList(getShippingBill().getShippingBillByInvoiceNo(exciseInvoiceNo));
			if(getBillList()!=null && getBillList().size()==0){
				addActionError("No Record Found");
			}
		}
		return SUCCESS;
	}
	public String update(){
		int result=-1;
		if((shippingBillNo!=null && !shippingBillNo.isEmpty())||(shippingBillDate!=null && !shippingBillDate.isEmpty())){
			try{
				result=getShippingBill().update(shippingBillNo, shippingBillDate, exciseInvoiceNo);
			}catch(DataAccessException ex ){
				addActionError("Couldn't Update Record");
			}
			if(result>0){
				addActionError("Record updated Successfully");
			}
			setBillList(getShippingBill().getShippingBillByInvoiceNo(exciseInvoiceNo));
		}
		return SUCCESS;	
	}
	private  ShippingBillService getShippingBill(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext());
		ShippingBillService service=(ShippingBillService)context.getBean("shippingBillService");
		return service;
	}
	public String getExciseInvoiceNo() {
		return exciseInvoiceNo;
	}
	public void setExciseInvoiceNo(String exciseInvoiceNo) {
		this.exciseInvoiceNo = exciseInvoiceNo;
	}
	public List<ShippingBill> getBillList() {
		return billList;
	}
	public void setBillList(List<ShippingBill> billList) {
		this.billList = billList;
	}
	public String getShippingBillNo() {
		return shippingBillNo;
	}
	public void setShippingBillNo(String shippingBillNo) {
		this.shippingBillNo = shippingBillNo;
	}
	public String getShippingBillDate() {
		return shippingBillDate;
	}
	public void setShippingBillDate(String shippingBillDate) {
		this.shippingBillDate = shippingBillDate;
	}
	
}
