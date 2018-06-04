package shahi.Action.PayTracker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import shahi.Action.PayTracker.bean.DatatexPaymentTracker;
import shahi.Action.PayTracker.bean.PaymentSearch;
import shahi.Action.PayTracker.bean.PaymentTracker;
import shahi.Action.PayTracker.service.DatatexPaymentTrackerService;
import shahi.Action.PayTracker.service.PaymentTrackerService;
import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.service.PopulateListService;

public class PaymentTrackerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String division;
	private String status;
	private String fromDate;
	private String toDate;
	private List<PaymentTracker>paymentList;
	private String age;
	private List<Code>cmpList;
	private List<Code>payrollList;
	private List<Code>accountList;
	private String account;
	private String payroll;
	private String supplier;
	private PaymentSearch search;
	private List<DatatexPaymentTracker> datatexPaymentList;
	@Override
	public String execute(){
		populateMaster();
		return SUCCESS;

	}
	public String dataTex(){
		try{
			if(search!=null){
				datatexPaymentList=getService().getAllDatatexPayments(search);
				if(datatexPaymentList==null){
					addActionError("No Record Found");
				}
			}
		}catch(Exception ex){
			addActionError(ex.getMessage());
		}
		
		return "DATATEX";
	}
	
	
	private DatatexPaymentTrackerService getService(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext());
		DatatexPaymentTrackerService service=(DatatexPaymentTrackerService)context.getBean(DatatexPaymentTrackerService.class);
		return service;
	}
	private void populateMaster(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
	                                    ServletActionContext.getServletContext());
		PopulateListService service=(PopulateListService)context.getBean(PopulateListService.class);
		setCmpList(service.getAllDivisions());
		setPayrollList(service.getAllPayrollTypes());
		setAccountList(service.getAllAccounts());
	}
	public String search(){
		PaymentTrackerService service=new PaymentTrackerService();

		try {
			populateMaster();
			paymentList=service.getAllPaymentsByCriteria(search);
		} catch (NullPointerException | SQLException ex) {
			ex.printStackTrace();
			addActionError(ex.getLocalizedMessage());
		}finally{
			service=null;
		}
		return SUCCESS;
	}
	
	public void dataTexReport(){
		try{
			datatexPaymentList=getService().getAllDatatexPayments(search);
			generateDatatexReport(datatexPaymentList);
		}catch(Exception ex){
			addActionError(ex.getLocalizedMessage());
		}
	}
	public void report(){
		PaymentTrackerService service=new PaymentTrackerService();
		try {
			populateMaster();
			paymentList=service.getAllPaymentsByCriteria(search);
			generateReport(paymentList);
		} catch (NullPointerException | SQLException ex) {
		//	ex.printStackTrace();
			addActionError(ex.getLocalizedMessage());
		}finally{
			service=null;
		}
		//System.out.println(getPaymentList());
	}
	private void generateDatatexReport(List<DatatexPaymentTracker> paymentList){
		 if (paymentList != null) {
            ActionContext ac = ActionContext.getContext();
            ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
            String path = sc.getRealPath("/shahiwebpages/PayTracker");
            Map param1 = new HashMap();


            InputStream input;
            param1.put("SUBREPORT_DIR", path);
           try {
				 input = new FileInputStream(new File(path + "/DatatexPaymentTracker.jrxml"));
				 JasperDesign design = JRXmlLoader.load(input);
	             JasperReport rep = JasperCompileManager.compileReport(design);


	             JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(paymentList));

	             HttpServletResponse response =ServletActionContext.getResponse();
	             ServletOutputStream out1 = response.getOutputStream();
	             response.reset();
	             byte[] bytes = null;
	             JRXlsExporter exporter = new JRXlsExporter();
	             ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
	             exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	             exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
	             exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	             exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	             exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	             exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	             exporter.setParameter(JRExporterParameter.OUTPUT_FILE, path + "\\");
	             exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "DatatexPaymentTracker.xls");
	             exporter.exportReport();

	             bytes = xlsReport.toByteArray();
	             response.setContentType("application/vnd.ms-excel");
	             response.setHeader("Content-Disposition", "attachment; filename=Datatex Payment Tracker.xls;");
	             response.setContentLength(bytes.length);
	             xlsReport.close();
	             out1.write(bytes, 0, bytes.length);
			} catch (JRException |IOException e) {
				addActionError(e.getLocalizedMessage());
				e.printStackTrace();
			}
       }
	}
	private void generateReport(List<PaymentTracker> paymentList){
		 if (paymentList != null) {
             ActionContext ac = ActionContext.getContext();
             ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
             String path = sc.getRealPath("/shahiwebpages/PayTracker");
             Map param1 = new HashMap();


             InputStream input;
             param1.put("SUBREPORT_DIR", path);
            try {
				 input = new FileInputStream(new File(path + "/PaymentTracker.jrxml"));
				 JasperDesign design = JRXmlLoader.load(input);
	             JasperReport rep = JasperCompileManager.compileReport(design);


	             JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(paymentList));

	             HttpServletResponse response =ServletActionContext.getResponse();
	             ServletOutputStream out1 = response.getOutputStream();
	             response.reset();
	             byte[] bytes = null;
	             JRXlsExporter exporter = new JRXlsExporter();
	             ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
	             exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	             exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
	             exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	             exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	             exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	             exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	             exporter.setParameter(JRExporterParameter.OUTPUT_FILE, path + "\\");
	             exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "PaymentTracker.xls");
	             exporter.exportReport();

	             bytes = xlsReport.toByteArray();
	             response.setContentType("application/vnd.ms-excel");
	             response.setHeader("Content-Disposition", "attachment; filename=Payment Tracker.xls;");
	             response.setContentLength(bytes.length);
	             xlsReport.close();
	             out1.write(bytes, 0, bytes.length);
			} catch (JRException |IOException e) {
				addActionError(e.getLocalizedMessage());
				e.printStackTrace();
			}
        }
	}
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public List<PaymentTracker> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentTracker> paymentList) {
		this.paymentList = paymentList;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<Code> getCmpList() {
		return cmpList;
	}

	public void setCmpList(List<Code> cmpList) {
		this.cmpList = cmpList;
	}
	public List<Code> getPayrollList() {
		return payrollList;
	}
	public void setPayrollList(List<Code> payrollList) {
		this.payrollList = payrollList;
	}
	public String getPayroll() {
		return payroll;
	}
	public void setPayroll(String payroll) {
		this.payroll = payroll;
	}
	public List<Code> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Code> accountList) {
		this.accountList = accountList;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public PaymentSearch getSearch() {
		return search;
	}
	public void setSearch(PaymentSearch search) {
		this.search = search;
	}
	public List<DatatexPaymentTracker> getDatatexPaymentList() {
		return datatexPaymentList;
	}
	public void setDatatexPaymentList(List<DatatexPaymentTracker> datatexPaymentList) {
		this.datatexPaymentList = datatexPaymentList;
	}

}
