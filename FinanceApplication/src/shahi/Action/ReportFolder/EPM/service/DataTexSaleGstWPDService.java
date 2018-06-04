package shahi.Action.ReportFolder.EPM.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.ReportFolder.EPM.beans.DataTexSaleBean;
import shahi.Action.ReportFolder.EPM.dao.DataTexSaleGstWPDDao;

public class DataTexSaleGstWPDService {

	@Autowired
	private DataTexSaleGstWPDDao dataTexSaleGstWPDDao;
	
	public List<DataTexSaleBean>getAllSaleInvoice(String fromDate,String toDate,String division) throws SQLException{
		List<DataTexSaleBean>result=dataTexSaleGstWPDDao.loadAllSaleInvoices(fromDate, toDate, division);
		return result;
	}
	
	
}
