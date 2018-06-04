package shahi.Action.ReportFolder.EPM;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.FITTUpdation;
import shahi.Action.ReportFolder.EPM.beans.SearchBean;
import shahi.Action.ReportFolder.EPM.service.FITTUpdationService;

public class FITTUpdationAction extends ActionSupport {

	private static final long serialVersionUID = 8710394681227673031L;
	private SearchBean searchBean;
	private List<FITTUpdation>list;

	
	public String execute(){
		
		if(searchBean!=null && getSearchBean().getFSFTNO()!=null){
			WebApplicationContext context =
					WebApplicationContextUtils.getRequiredWebApplicationContext(
		                                    ServletActionContext.getServletContext());
			FITTUpdationService service=(FITTUpdationService)context.getBean("service");
			List<FITTUpdation>updateList=service.loadAll(getSearchBean().getFSFTNO().trim());
			if(updateList!=null && updateList.size()>0){
				setList(updateList);
			}
			
		}
		return SUCCESS;
	}

	public String update(){
		try{
			if(searchBean!=null && getSearchBean().getFSFTNO()!=null){
				WebApplicationContext context =
						WebApplicationContextUtils.getRequiredWebApplicationContext(
			                                    ServletActionContext.getServletContext());
				FITTUpdationService service=(FITTUpdationService)context.getBean("service");
				service.update(getList(),getSearchBean());
				setList(service.loadAll(searchBean.getFSFTNO()));
				addActionMessage("Records have been updated successfully");
			}
			return SUCCESS;
		}catch(NumberFormatException ex){
			addActionError(ex.getLocalizedMessage());
			return ERROR;
		}catch(SQLException ex){
			addActionError(ex.getLocalizedMessage());
			return ERROR;
		}catch(Exception ex){
			addActionError(ex.getLocalizedMessage());
			return ERROR;
		}
	}
	
	public String clear(){
		return SUCCESS;
	}
	public List<FITTUpdation> getList() {
		return list;
	}

	public void setList(List<FITTUpdation> list) {
		this.list = list;
	}


	public SearchBean getSearchBean() {
		return searchBean;
	}


	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}
	
	
}
