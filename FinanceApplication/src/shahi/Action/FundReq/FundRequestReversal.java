package shahi.Action.FundReq;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.FundReq.Beans.FaPayrollTypeMastBean;
import shahi.Action.FundReq.Beans.FundReqBean;
import shahi.Action.FundReq.Beans.FundReqQuery;
import shahi.Action.FundReq.service.FundReqReversalService;

public class FundRequestReversal extends ActionSupport {

	private static final long serialVersionUID = -5563757895700880747L;
	private List<FaPayrollTypeMastBean> reqtyplist;
	private List<FaPayrollTypeMastBean> payloctlist;
	private FundReqQuery query;
	private List<FundReqBean>detaillst;
	private String userId;
	public String execute(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String userId=request.getParameter("aausrid");
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						ServletActionContext.getServletContext());
		FundReqReversalService service=(FundReqReversalService)context.getBean("fundReqReversalService");
		setUserId(userId);
		setReqtyplist(service.getAllRequests());
		setPayloctlist(service.getAllPayList());
		return SUCCESS;
	}

	public String query(){
		if(query!=null){
			WebApplicationContext context =
					WebApplicationContextUtils.getRequiredWebApplicationContext(
							ServletActionContext.getServletContext());
			FundReqReversalService service=(FundReqReversalService)context.getBean("fundReqReversalService");
			try{
				setDetaillst(service.getAllFundRequests(getQuery()));
			}catch(Exception ex){
				addActionError(ex.getLocalizedMessage());
			}
			setReqtyplist(service.getAllRequests());
			setPayloctlist(service.getAllPayList());
			setUserId(getUserId());
		}
		return SUCCESS;
	}

	public String cancel(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						ServletActionContext.getServletContext());
		FundReqReversalService service=(FundReqReversalService)context.getBean("fundReqReversalService");
		try{
			if(service.reverse(getDetaillst(),getUserId())){
				addActionError("Request has been completed");
			}
			setDetaillst(service.getRequestByNo(getDetaillst().get(0).getREQNO()));
		}catch(Exception ex){
			addActionError(ex.getLocalizedMessage());
		}
		setReqtyplist(service.getAllRequests());
		setPayloctlist(service.getAllPayList());
		setUserId(getUserId());
		return SUCCESS;
	}
	public List<FaPayrollTypeMastBean> getReqtyplist() {
		return reqtyplist;
	}

	public void setReqtyplist(List<FaPayrollTypeMastBean> reqtyplist) {
		this.reqtyplist = reqtyplist;
	}

	public List<FaPayrollTypeMastBean> getPayloctlist() {
		return payloctlist;
	}

	public void setPayloctlist(List<FaPayrollTypeMastBean> payloctlist) {
		this.payloctlist = payloctlist;
	}

	public FundReqQuery getQuery() {
		return query;
	}

	public void setQuery(FundReqQuery query) {
		this.query = query;
	}

	public List<FundReqBean> getDetaillst() {
		return detaillst;
	}

	public void setDetaillst(List<FundReqBean> detaillst) {
		this.detaillst = detaillst;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
