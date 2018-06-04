package shahi.Action.ReportFolder.EPM.TEMP;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceTempDetail;
import shahi.Action.ReportFolder.EPM.dao.ScanInvoiceDetailDao;
@Configuration
public class ScanInvoiceUpdation {

	/*@Scheduled(fixedDelay=300000)*/
	
	public static void updateScanInvoice(){
		
		ScanInvoiceDetailDao dao=new ScanInvoiceDetailDao();
		//List<ScanInvoiceDetail> list=dao.loadAllScanInvoices();
		
		List<ScanInvoiceTempDetail>scanInvoiceList=dao.loadAllTempInvoiceDetails();
		for(ScanInvoiceTempDetail temp:scanInvoiceList){
			try {
				dao.saveIntoMovex(temp);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String args[]){
		updateScanInvoice();
	}
}
