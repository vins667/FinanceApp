package shahi.Action.FundReq;

import java.util.ArrayList;
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
import shahi.Action.FundReq.Beans.UTRNoAlreadyExist;
import shahi.Action.FundReq.service.FundReqReversalService;
import shahi.Action.ReportFolder.EPM.ajax.AjaxResult;
import shahi.Action.ReportFolder.EPM.util.EPMDB2UtilNM;

public class UTRNOUpdationAction extends ActionSupport {

	private static final long serialVersionUID = -5563757895700880747L;
	private List<FaPayrollTypeMastBean> reqtyplist;
	private List<FaPayrollTypeMastBean> payloctlist;
	private FundReqQuery query;
	private List<FundReqBean>detaillst;
	private List  saveList;
	private String userId;
	private String voDate;
	private String voucherExist;
	private String fromDate;
	private String toDate;
	private List<UTRNoAlreadyExist>exceptionList;
	private List<AjaxResult> result;
	public String execute(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String userId=request.getParameter("aausrid");
		populateMaster();
		return SUCCESS;
	}

	public String query(){
		if(query!=null){
			try{
				getFundRequests();
			}catch(Exception ex){
				addActionError(ex.getLocalizedMessage());
			}
			populateMaster();
		}
		return SUCCESS;
	}

	
	private void getFundRequests(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						ServletActionContext.getServletContext());
		FundReqReversalService service=(FundReqReversalService)context.getBean("fundReqReversalService");
		EPMDB2UtilNM epmUtil=(EPMDB2UtilNM)context.getBean(EPMDB2UtilNM.class);
		List<FundReqBean> result=service.getAllFundRequestsForUTRUpdation(fromDate,toDate);
		for(FundReqBean bean :result){
			bean.setVOTYPES(epmUtil.getPurchaseVoucherListByDivision(bean.getREQDIVI().trim()));
		}
		setDetaillst(result);
	}
	private FundReqReversalService getServiceBean(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						ServletActionContext.getServletContext());
		FundReqReversalService service=(FundReqReversalService)context.getBean("fundReqReversalService");

		return service;
	}
	public String validateVoucher(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String vtype=request.getParameter("votype");
		String vono=request.getParameter("vono");
		String year=request.getParameter("year");
		voucherExist=String.valueOf(getServiceBean().validateVoucher(vtype, vono, year));
		return SUCCESS;
	}
	
	public String updateCheque(){
		System.out.println(getDetaillst());
		int index=-1;
		FundReqReversalService service=getServiceBean();
		exceptionList=new ArrayList<>(getSaveList().size());
		FundReqBean bean=null;
		for(int i=0;i<getSaveList().size();i++){
			index=-1;
			try{
				String value=getSaveList().get(i).toString();
				index=Integer.parseInt(value);
				bean=getDetaillst().get(index);
				service.updateUTRNo(bean);
				exceptionList.add(new UTRNoAlreadyExist("Req No: "+bean.getREQNO()+" updated successfully"));
			}catch(RuntimeException  ex){
				exceptionList.add(new UTRNoAlreadyExist(ex.getLocalizedMessage()));
			}
		}
		getFundRequests();
		populateMaster();
		System.out.println(getSaveList().size());
		return "save";
	}
	
	private void populateMaster(){
		FundReqReversalService service=getServiceBean();
		setReqtyplist(service.getAllRequests());
		setPayloctlist(service.getAllPayList());
		setUserId(getUserId());
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

	public List  getSaveList() {
		return saveList;
	}

	public void setSaveList(List saveList) {
		this.saveList = saveList;
	}

	public String getVoDate() {
		return voDate;
	}

	public void setVoDate(String voDate) {
		this.voDate = voDate;
	}

	public List<AjaxResult> getResult() {
		return result;
	}

	public void setResult(List<AjaxResult> result) {
		this.result = result;
	}

	public String getVoucherExist() {
		return voucherExist;
	}

	public void setVoucherExist(String voucherExist) {
		this.voucherExist = voucherExist;
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

	public List<UTRNoAlreadyExist> getExceptionList() {
		return exceptionList;
	}

	public void setExceptionList(List<UTRNoAlreadyExist> exceptionList) {
		this.exceptionList = exceptionList;
	}


}
