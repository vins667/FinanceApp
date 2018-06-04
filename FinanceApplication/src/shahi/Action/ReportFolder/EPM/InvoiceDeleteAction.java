package shahi.Action.ReportFolder.EPM;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.Invoice;
import shahi.Action.ReportFolder.EPM.beans.InvoiceSearch;
import shahi.Action.ReportFolder.EPM.service.InvoiceDeleteService;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class InvoiceDeleteAction extends ActionSupport {

	private Invoice invoiceDetail;
	private InvoiceSearch searchBean;
	private InvoiceSearch deleteBean;
	private String userId;
	
	public String execute(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		userId=request.getParameter("aausrid");
		if(searchBean!=null){
			WebApplicationContext context =
					WebApplicationContextUtils.getRequiredWebApplicationContext(
							ServletActionContext.getServletContext());
			InvoiceDeleteService service=(InvoiceDeleteService)context.getBean("invoiceDeleteService");
			
			setInvoiceDetail(service.getInvoiceNo(getSearchBean()));
		}
		return SUCCESS;
	}

    public String delete(){
    	WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						ServletActionContext.getServletContext());
		InvoiceDeleteService service=(InvoiceDeleteService)context.getBean("invoiceDeleteService");
		
		try{
		    int result=service.delete(getDeleteBean(), getUserId());
			if(result!=0){
				addActionError("Invoice has been deleted");
			}else{
				addActionError("Not Authorize to delete this invoice. ");
			}
		}catch(Exception ex){
			addActionError(ex.getLocalizedMessage());
		}finally{
			setInvoiceDetail(service.getInvoiceNo(getDeleteBean()));
		}
    	//System.out.println(getDeleteBean());
    	return SUCCESS;
    }

   /* private String getUserId(){
    	 Map session = ActionContext.getContext().getSession();
	     String usrid =(String) session.get("sessUserId");
	     return usrid;
    }*/
	public InvoiceSearch getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(InvoiceSearch searchBean) {
		this.searchBean = searchBean;
	}



	public Invoice getInvoiceDetail() {
		return invoiceDetail;
	}



	public void setInvoiceDetail(Invoice invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}



	public InvoiceSearch getDeleteBean() {
		return deleteBean;
	}



	public void setDeleteBean(InvoiceSearch deleteBean) {
		this.deleteBean = deleteBean;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
