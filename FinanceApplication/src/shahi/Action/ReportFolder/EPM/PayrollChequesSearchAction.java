package shahi.Action.ReportFolder.EPM;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.PayrollCheques;
import shahi.Action.ReportFolder.EPM.service.MovexService;

public class PayrollChequesSearchAction extends ActionSupport {

	private static final long serialVersionUID = -3779884395939056570L;
	
	private PayrollCheques payrollCheque;
	private String batchNo;

	public PayrollCheques getPayrollCheque() {
		return payrollCheque;
	}

	public void setPayrollCheque(PayrollCheques payrollCheque) {
		this.payrollCheque = payrollCheque;
	}
	
	@Override
	public String execute(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext());
		MovexService service=(MovexService)context.getBean(MovexService.class);
		try{
			payrollCheque=new PayrollCheques();
			payrollCheque.setChequeList(service.getAllPayrollCheques(getBatchNo()));
			payrollCheque.setLedgerList(service.getAllAllLedgers(getBatchNo()));
			if(payrollCheque.getLedgerList().size()==0){
				addActionError("No Record Found");
			}
		}catch(Exception ex){
			addActionError(ex.getLocalizedMessage());
		}
		return SUCCESS;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
}
