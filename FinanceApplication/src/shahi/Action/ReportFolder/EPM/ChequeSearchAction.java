package shahi.Action.ReportFolder.EPM;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.ChequeSearch;
import shahi.Action.ReportFolder.EPM.beans.Query;
import shahi.Action.ReportFolder.EPM.service.ChequeSearchService;

public class ChequeSearchAction extends ActionSupport {

	
	private static final long serialVersionUID = 7598816466718375504L;
	private List<ChequeSearch>checkList;
	private Query query;

	
	public String execute(){
		if(query!=null){
			WebApplicationContext context =
					WebApplicationContextUtils.getRequiredWebApplicationContext(
		                                    ServletActionContext.getServletContext());
			ChequeSearchService  service=(ChequeSearchService)context.getBean(ChequeSearchService.class);
			List<ChequeSearch> result=service.getAllCheques(getQuery());
			if(result!=null && result.size()>0){
				setCheckList(result);
			}else{
				addActionError("No Record Found");
			}
			
		}
		return SUCCESS;
	}

	public List<ChequeSearch> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<ChequeSearch> checkList) {
		this.checkList = checkList;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}
}
