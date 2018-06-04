package shahi.Action.ReportFolder.EPM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import shahi.Action.ReportFolder.EPM.beans.Invoice;
import shahi.Action.ReportFolder.EPM.beans.InvoiceSearch;
import shahi.Action.ReportFolder.EPM.dao.InvoiceDeleteDao;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class InvoiceDeleteService {

	@Autowired
	private InvoiceDeleteDao invoiceDeleteDao;
	
	public Invoice getInvoiceNo(InvoiceSearch bean){
		Invoice invoice= invoiceDeleteDao.getInvoiceByNo(bean);
		if(invoice!=null){
			invoice.setAPIVDT(DateUtil.convertToDDMMYYYY(invoice.getAPIVDT()));
		}
		return invoice;
	}
	@Transactional
	public int  delete(InvoiceSearch delete,String empCode) throws RuntimeException{
		boolean isAuthorised=invoiceDeleteDao.isUserAuthorised(delete, empCode);
		int result=0;
		if(isAuthorised){
			checkM4Status(delete);
			result= invoiceDeleteDao.delete(delete);
			if(result>0){
				return invoiceDeleteDao.deleteFromMovex(delete);
			}
			
		}
		return 0;
	}
	private void checkM4Status(InvoiceSearch delete)throws RuntimeException{
		int result=invoiceDeleteDao.isM4ControlNoGenerated(delete);
		if(result!=0){
			throw new RuntimeException("Control No already generated in M4, First delete Invoice.");
		}
	}
}
