package shahi.Action.ReportFolder.EPM;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.beans.GSTReconciliation;
import shahi.Action.ReportFolder.EPM.beans.IGSTConstants;
import shahi.Action.ReportFolder.EPM.service.GSTReconciliationService;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class GSTReconciliationAction extends ActionSupport {

	private File file;
	private List<GSTReconciliation>list=null;
	private List<GSTReconciliation>shahiGSTNList=null;
	private List<Code> gstnList;
	private List<Code> companyList;
	private List<Code>vendotGSTNList;
	private String userId;
	private String company;
	private String shahiGSTN;
	private String toDate;
	private String fromDate;
	private String vendorGSTN;
	private String annexure;
	private List<String>adjustmentList;
	private Date recoDate;
	@Override
	public String execute(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		userId=request.getParameter("aausrid");
		populateMasters();
		return SUCCESS;
	}

	private void populateMasters(){
		companyList=getService().loadAllCompanies();
		//vendotGSTNList=getService().loadAllVendorGSTN();
		gstnList=new ArrayList<>(1);
		gstnList.add(new Code("-1","Select Shahi GSTN"));
	}

	public String show(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		userId=request.getParameter("aausrid");
		populateMasters();
		return "GSTRECON";
	}

	public String adjust(){
		populateMasters();
		return "ADJUST";
	}
	public String load(){ 		
		String division = ServletActionContext.getRequest().getParameter("division"); 
		gstnList=getService().loadShahiGSTNByDivision(division);
		return SUCCESS;
	}
	public String search(){
		populateMasters();
		shahiGSTNList=getService().filterData(vendorGSTN, annexure,fromDate,toDate);
		setRecoDate(DateUtil.convertToDDMMMYY(toDate));
		return "ADJUST";
	}

	public String adjustReco(){
		populateMasters();
		Double annx1_amount=0d;
		Double annx2_amount=0d;
		for(int i=0;i<adjustmentList.size();i++){
			String temp[]=adjustmentList.get(i).split("#");
			if(temp!=null && temp.length==3){
				GSTReconciliation bean=getShahiGSTNList().get(Integer.parseInt(temp[2]));
				if(bean!=null && bean.getANX_TYPE().equals("1") && bean.getDOC_TYPE().equals("S")){
					annx1_amount+=Double.valueOf(bean.getTAXVALUE().trim());
				}else if(bean!=null && bean.getANX_TYPE().equals("1") && bean.getDOC_TYPE().equals("C")){
					annx2_amount+=Double.valueOf(bean.getTAXVALUE().trim());
				}
			}
		}
		if(!annx1_amount.equals(annx2_amount)){
			addActionError("Reconciled Entries not matched.");
		}else{
			// Date recoDate=DateUtil.convertToDDMMMYY(toDate);
			for(int i=0;i<adjustmentList.size();i++){
				String temp[]=adjustmentList.get(i).split("#");
				if(temp!=null && temp.length==3){
					GSTReconciliation bean=getShahiGSTNList().get(Integer.parseInt(temp[2]));
					getService().updateRecoDate(bean, recoDate);
				}
			}
			shahiGSTNList=getService().filterData(vendorGSTN, annexure,fromDate,toDate);
		}
		return "ADJUST";
	}
	public void download(){
		List<GSTReconciliation> list=getService().getUnReconciliedInvoices(company, shahiGSTN, fromDate, toDate);
		generateExcel(list,shahiGSTN,fromDate,toDate);
	}

	private void generateExcel(List<GSTReconciliation>list,String shahiGSTN,String fromDate,String toDate){
		String []columns={"Company","Division","Year","SHAHIGSTN","SUPLGSTN","INVOICE NO","INVOICE DATE","LINE ITEM AMOUNT",
				"TAX VALUE","INVOICE TOTAL"};
		List<GSTReconciliation>vendorInvoices=new ArrayList<>();
		List<GSTReconciliation>shahiInvoices=new ArrayList<>();
		List<GSTReconciliation>vendorTaxMismatch=new ArrayList<>();
		List<GSTReconciliation>shahiTaxMismatch=new ArrayList<>();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy hh:mm");
		String date=sdf.format(new Date());
		XSSFWorkbook  workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("GST Reconciliation");

		// Create a Font for styling header cells======================
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setBoldweight((short)12);
		headerFont.setColor(IndexedColors.BLUE.getIndex());
	    
		
		// Create a CellStyle with the font============================
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setAlignment((short)1);
		
		//========
        CellStyle borderStyle = workbook.createCellStyle();

        borderStyle.setBorderBottom(CellStyle.BORDER_THICK);
        borderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setBorderLeft(CellStyle.BORDER_THICK);
        borderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setBorderRight(CellStyle.BORDER_THICK);
        borderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setBorderTop(CellStyle.BORDER_THICK);
        borderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        
		//=======Creating Header========
	        Row header = sheet.createRow((short) 0);
	        Cell headerCell = header.createCell((short) 0);
	        headerCell.setCellStyle(borderStyle);
	        headerCell.setCellValue(new XSSFRichTextString(" Shahi Exports Private Limited - ")+shahiGSTN+"              Print Date:"+date+"\n  Reconciliation as on "+fromDate+" To "+toDate);
	        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:J2"));
		// Create a Row================================================
		Row headerRow = sheet.createRow(2);
		int rowNum=2;

		for(int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
			
		}  
		for(GSTReconciliation bean:list){
			if(bean.getANX_TYPE().equals("1") && bean.getDOC_TYPE().equals("S")){
				vendorInvoices.add(bean);
			}else if(bean.getANX_TYPE().equals("1") && bean.getDOC_TYPE().equals("C")){
				shahiInvoices.add(bean);
			}else if(bean.getANX_TYPE().equals("2") && bean.getDOC_TYPE().equals("S")){
				vendorTaxMismatch.add(bean);
			}else if(bean.getANX_TYPE().equals("2") && bean.getDOC_TYPE().equals("C")){
				shahiTaxMismatch.add(bean);
			}
		}
		if(vendorInvoices!=null && vendorInvoices.size()>0){
			rowNum=makeHeader(sheet,rowNum,vendorInvoices,"ANNEX -I Invoice Not in Our Books ");
		}
		if(shahiInvoices!=null && shahiInvoices.size()>0){
			makeHeader(sheet,rowNum,shahiInvoices,"ANNEX -I Invoice not in Vendor's Books");
		}
		
		if(vendorTaxMismatch!=null && vendorTaxMismatch.size()>0){
			makeHeader(sheet,rowNum,vendorTaxMismatch,"ANNEX -II  Tax Value is Mismatch in Our Books");
		}
		if(shahiTaxMismatch!=null && shahiTaxMismatch.size()>0){
			makeHeader(sheet,rowNum,shahiTaxMismatch,"ANNEX -II  Tax Value is Mismatch in Vendor Books");
		}
		HttpServletResponse response =ServletActionContext.getResponse();

		response.reset();
		response.setHeader("Content-disposition", "attachment;filename=GST Reconciliation.xlsx");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream in=null;
		OutputStream out=null;
		try {
			workbook.write(baos);
			in=new ByteArrayInputStream(baos.toByteArray());
			out=response.getOutputStream();
			byte[] content = new byte[1024];
			int readByteLength = 0;
			while ((readByteLength = in.read(content)) != -1)
				out.write(content, 0, readByteLength);
			in.close();
			out.flush();
			//  workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int  makeHeader(XSSFSheet sheet,int rowNo,List<GSTReconciliation> list,String message){
		Double lineItemAmount=0d;
		rowNo+=1;
        Row header = sheet.createRow((short) (rowNo));
        Cell headerCell = header.createCell((short) 0);
        headerCell.setCellValue(new XSSFRichTextString(message));
    	rowNo+=1;
        sheet.addMergedRegion(CellRangeAddress.valueOf("A"+rowNo+":J"+rowNo));

		try{
			for(GSTReconciliation bean:list){
				if(bean.getINVVALUE()!=null && bean.getTAXVALUE()!=null){
					lineItemAmount=Math.abs(Double.valueOf(bean.getINVVALUE()))-Math.abs(Double.valueOf(bean.getTAXVALUE()));
					Row row = sheet.createRow(rowNo++);
					row.createCell(0)
					.setCellValue(bean.getCOMPANY());
					row.createCell(1)
					.setCellValue(bean.getDIVISION());
					row.createCell(2)
					.setCellValue(bean.getYEAR());
					row.createCell(3)
					.setCellValue(bean.getSHAHIGSTN());
					row.createCell(4)
					.setCellValue(bean.getSUPLGSTN());
					row.createCell(5)
					.setCellValue(bean.getINVOICENO());
					row.createCell(6)
					.setCellValue(bean.getINVOICEDT());
					row.createCell(7)
					.setCellValue(String.valueOf(lineItemAmount));
					row.createCell(8)
					.setCellValue(bean.getTAXVALUE());
					row.createCell(9)
					.setCellValue(bean.getINVVALUE());
				}

			}
		}catch(NullPointerException ex){
			ex.printStackTrace();
			addActionError(ex.getLocalizedMessage());
		}
		return rowNo;
	}
	public  String  upload(){
		XSSFSheet sheet=null;
		XSSFWorkbook  workbook;
		/*		String IGST=null;
		String SCGT=null;
		String CGST=null;
		String CESS=null;
		 */		String INVVALUE=null;
		 try {
			 String path=getFile().getAbsolutePath();
			 workbook = new XSSFWorkbook(path);
			 sheet= workbook.getSheetAt(2);
		 } catch (Exception e) {
			 addActionError(e.getLocalizedMessage());
			 //e.printStackTrace();
		 }
		 populateMasters();
		 list=new ArrayList<>(sheet.getLastRowNum());
		 Iterator<Row> iterator = sheet.iterator();

		 if(iterator.hasNext()){
			 iterator.next();
		 }
		 for(;iterator.hasNext();){
			 Row row=(Row)iterator.next();
			 GSTReconciliation bean=new GSTReconciliation();
			 try{
				 bean.setSUPLGSTN(String.valueOf(row.getCell(IGSTConstants.GSTIN)));
				 bean.setCNTPTYST(String.valueOf(row.getCell(IGSTConstants.CounterPartyStatus)));
				 bean.setCUSTNAME(String.valueOf(row.getCell(IGSTConstants.CustomerName)));
				 bean.setINVOICENO(String.valueOf(row.getCell(IGSTConstants.InvoiceNumber)));
				 bean.setINVOICEDT(String.valueOf(row.getCell(IGSTConstants.InvoiceDate)));
				 bean.setLINE(String.valueOf(row.getCell(IGSTConstants.LineNumber)));
				 bean.setLineItemAmount(String.valueOf(row.getCell(IGSTConstants.TaxableValue)));
				 bean.setTAXVALUE(String.valueOf(row.getCell(IGSTConstants.TaxAmount)).replace(",", ""));
				 bean.setTAXRATE(String.valueOf(row.getCell(IGSTConstants.Rate)));
				 INVVALUE=String.valueOf(row.getCell(IGSTConstants.InvoiceValue)).replace(",", "");
				 if(INVVALUE!=null && !INVVALUE.isEmpty()){
					 bean.setINVVALUE(INVVALUE);
				 }
				 bean.setINVTYPE(String.valueOf(row.getCell(IGSTConstants.InvoiceType)));
				 bean.setPOS(String.valueOf(row.getCell(IGSTConstants.POS)));
				 bean.setREVCHG(String.valueOf(row.getCell(IGSTConstants.ReverseCharge)));
				 bean.setCHID(userId.trim());
				 bean.setCOMPANY(111);
				 bean.setDIVISION(getCompany());
				 bean.setYEAR(DateUtil.getCurrentFinancialYear());
				 bean.setSHAHIGSTN(getShahiGSTN());
			 }catch(NullPointerException |ArrayIndexOutOfBoundsException  ex){
				 addActionError(ex.getLocalizedMessage());
				 return SUCCESS;
			 }
			 list.add(bean);
		 }
		 try{
			 if(save()){
				 addActionError("Excel has been uploaded successfully");
				 return SUCCESS;
			 }
		 }catch(DataAccessException ex){
			 addActionError(ex.getLocalizedMessage());
		 }
		 return SUCCESS;
	}

	private boolean save() throws DataAccessException {

		return getService().save(list);
	}

	public  String reconciliation(){
		try{
			populateMasters();
			shahiGSTNList=getService().loadAllShahiGSTNData(shahiGSTN, userId, getCompany(),fromDate,toDate);
		}catch(RuntimeException ex){
			addActionError(ex.getMessage());
		}
		return "GSTRECON";
	}
	private GSTReconciliationService getService(){
		WebApplicationContext context =
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						ServletActionContext.getServletContext());
		GSTReconciliationService service=(GSTReconciliationService)context.getBean(GSTReconciliationService.class);
		return service;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<GSTReconciliation> getList() {
		return list;
	}

	public void setList(List<GSTReconciliation> list) {
		this.list = list;
	}

	public List<Code> getGstnList() {
		return gstnList;
	}

	public void setGstnList(List<Code> gstnList) {
		this.gstnList = gstnList;
	}

	public List<Code> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Code> companyList) {
		this.companyList = companyList;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getShahiGSTN() {
		return shahiGSTN;
	}

	public void setShahiGSTN(String shahiGSTN) {
		this.shahiGSTN = shahiGSTN;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public List<GSTReconciliation> getShahiGSTNList() {
		return shahiGSTNList;
	}

	public void setShahiGSTNList(List<GSTReconciliation> shahiGSTNList) {
		this.shahiGSTNList = shahiGSTNList;
	}

	public List<Code> getVendotGSTNList() {
		return vendotGSTNList;
	}

	public void setVendotGSTNList(List<Code> vendotGSTNList) {
		this.vendotGSTNList = vendotGSTNList;
	}

	public String getVendorGSTN() {
		return vendorGSTN;
	}

	public void setVendorGSTN(String vendorGSTN) {
		this.vendorGSTN = vendorGSTN;
	}

	public String getAnnexure() {
		return annexure;
	}

	public void setAnnexure(String annexure) {
		this.annexure = annexure;
	}

	public List<String> getAdjustmentList() {
		return adjustmentList;
	}

	public void setAdjustmentList(List<String> adjustmentList) {
		this.adjustmentList = adjustmentList;
	}

	public Date getRecoDate() {
		return recoDate;
	}

	public void setRecoDate(Date recoDate) {
		this.recoDate = recoDate;
	}


}
