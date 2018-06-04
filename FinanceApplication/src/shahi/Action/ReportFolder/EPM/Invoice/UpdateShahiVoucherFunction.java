package shahi.Action.ReportFolder.EPM.Invoice;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;

import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceDetail;
import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceTempDetail;
import shahi.Action.ReportFolder.EPM.dao.ScanInvoiceDetailDao;
import shahi.Action.ReportFolder.EPM.dao.ShahiWebDao;
import shahi.Action.ReportFolder.EPM.service.SaleLedgerService;
import shahi.Action.ReportFolder.EPM.service.UpdateHSNForUPChargesService;
import shahi.Action.ReportFolder.invoicecorrection.InvoiceDao;

public class UpdateShahiVoucherFunction {

	@Autowired
	private ScanInvoiceDetailDao scanInvoiceDetailDao;

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private ShahiWebDao shahiWebDao;
    @Autowired
    private SaleLedgerService saleLedgerService;
    
    @Autowired
    private UpdateHSNForUPChargesService updateHSNForUPChargesService;
    
	public UpdateShahiVoucherFunction(){

	}
	@Scheduled(cron="0 0 22 * * ?")
	public   void updateInvoice(){
		VoucherMail mail=new VoucherMail();
		mail.sendMail();
		mail=null;// eligible for garbage collection
		shahiWebDao.updateProcedureHistory("Auto_Voucher_Approval","SUCCESS",null);
	}	

	/*@Scheduled(fixedDelay=480000)*/
	public  void updateScanInvoices(){

		System.out.println("updateScanInvoices () Schedular has started:"+new Date());
		try {
			List<ScanInvoiceDetail>scanInvoiceList=scanInvoiceDetailDao.loadAllScanInvoices();
			scanInvoiceDetailDao.insertIntoScanInvTemp(scanInvoiceList);
			scanInvoiceList.clear();

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		List<ScanInvoiceTempDetail>scanInvoiceList=scanInvoiceDetailDao.loadAllTempInvoiceDetails();
		for(ScanInvoiceTempDetail temp:scanInvoiceList){
			try {
				scanInvoiceDetailDao.saveIntoMovex(temp);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		scanInvoiceDetailDao.emptyTempScanTable();
		scanInvoiceList.clear();
		scanInvoiceList=null;

	}
	/*@Scheduled(cron="0 45 19 * * ?")
	@Scheduled(fixedDelay=300000)*/
	public void updateTaxValue(){
		boolean isUpdated=false;
		try{
			isUpdated=scanInvoiceDetailDao.updateTax();
			shahiWebDao.updateProcedureHistory("GST_DN_Update","SUCCESS",null);
		}catch(DataAccessException  ex){
			shahiWebDao.updateProcedureHistory("GST_DN_Update","FAIL",ex.getLocalizedMessage());
			ex.printStackTrace();
		}

	}
	/*@Scheduled(fixedDelay=300000)
	public   void correctInvoiceDate(){
		System.out.println("correct invoice date () Schedular has started:"+new Date());
		Invoice existedInvoice=null;
		List<Invoice>invoiceList=invoiceDao.loadAllInvoices();
		for(Invoice invoice:invoiceList){
			existedInvoice=invoiceDao.isInvoiceExist(invoice);
			if(existedInvoice==null){
				invoiceDao.updateInvoice(invoice);
			}
		}
	}*/// commented on 02/04/2018
	/*@Scheduled(fixedDelay=9000000)
	public void insertIntoNewScanner(){
		System.out.println("insertIntoNewScanner() Schedular has started:"+new Date());
		OldToNewScannerService service=new OldToNewScannerService();
		service.save();
	}*/

	/*@Scheduled(cron="0 50 19 * * ?")
	@Scheduled(fixedDelay=480000)*/
	public void updateVoucher(){
		System.out.println("updateVoucher () Schedular has started:"+new Date());
		//System.out.println(saleLedgerService.getAllSaleLedger());
		try{
			saleLedgerService.update();
			shahiWebDao.updateProcedureHistory("Update_LC_CINo","SUCCESS",null);
		}catch(DataAccessException ex){
			shahiWebDao.updateProcedureHistory("Update_LC_CINo","FAIL",ex.getLocalizedMessage());
			ex.printStackTrace();
		}
	}
	/*@Scheduled(cron="0 50 17 * * ?")*/
	@Scheduled(fixedDelay=480000)
	public void updateHSNUPCharges(){
		try{
			updateHSNForUPChargesService.updateCharges();
			shahiWebDao.updateProcedureHistory("Update_UP_CHGS","SUCCESS",null);
		}catch(DataAccessException ex){
			shahiWebDao.updateProcedureHistory("Update_UP_CHGS","SUCCESS",ex.getLocalizedMessage());
			ex.printStackTrace();
		}
	}
}
