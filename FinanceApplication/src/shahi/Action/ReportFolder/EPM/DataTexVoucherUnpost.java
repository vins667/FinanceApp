package shahi.Action.ReportFolder.EPM;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.DataTexPurchaseBean;
import shahi.Action.ReportFolder.EPM.beans.DataTexSearchBean;
import shahi.Action.ReportFolder.EPM.service.DataTexVoucherUnpostService;

public class DataTexVoucherUnpost extends ActionSupport {

	private static final long serialVersionUID = 2327140124414964753L;
    private DataTexSearchBean search;
    private DataTexPurchaseBean detail;
	
    @Override
	public String execute(){
		
		if(search!=null){
			WebApplicationContext context =
					WebApplicationContextUtils.getRequiredWebApplicationContext(
		                                    ServletActionContext.getServletContext());
			DataTexVoucherUnpostService service=(DataTexVoucherUnpostService)context.getBean(DataTexVoucherUnpostService.class);
			DataTexPurchaseBean result;
			try {
				result = service.getInvoice(getSearch());
				if(result!=null){
					setDetail(result);
				}else{
					addActionError("No Record Found!!!");
				}
			} catch (SQLException ex) {
				addActionError(ex.getLocalizedMessage());
			}
			
			
		}
		return SUCCESS;
	}
	
	public String cancel(){
		if(search!=null){
			WebApplicationContext context =
					WebApplicationContextUtils.getRequiredWebApplicationContext(
		                                    ServletActionContext.getServletContext());
			DataTexVoucherUnpostService service=(DataTexVoucherUnpostService)context.getBean(DataTexVoucherUnpostService.class);
			try{
				service.reverse(getSearch());
				addActionError("Voucher has been cancelled");
				//setDetail(service.getUnpostedInvoice(getSearch()));
			}catch(Exception ex){
				addActionError(ex.getLocalizedMessage());
			}
			
		}
		return SUCCESS;
	}
	public String clear(){
		return SUCCESS;
	}
	public DataTexSearchBean getSearch() {
		return search;
	}
	public void setSearch(DataTexSearchBean search) {
		this.search = search;
	}

	public DataTexPurchaseBean getDetail() {
		return detail;
	}

	public void setDetail(DataTexPurchaseBean detail) {
		this.detail = detail;
	}

	
}
