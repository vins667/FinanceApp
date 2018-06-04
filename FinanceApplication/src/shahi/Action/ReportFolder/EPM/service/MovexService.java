package shahi.Action.ReportFolder.EPM.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.ReportFolder.EPM.dao.MovexDao;
import shahi.Action.ReportFolder.EPM.movex.bean.FCR040;
import shahi.Action.ReportFolder.EPM.movex.bean.FDLEDG;

public class MovexService {

	@Autowired
	private MovexDao movexDao;
	
	public List<FCR040>getAllPayrollCheques(String batchNo) throws SQLException{
		return movexDao.loadAllPayrollCheques(batchNo);
	}
	public List<FDLEDG>getAllAllLedgers(String batchNo)throws SQLException {
		return movexDao.loadAllLedgers(batchNo);
	}
}
