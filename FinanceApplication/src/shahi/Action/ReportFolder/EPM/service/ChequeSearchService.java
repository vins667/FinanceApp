package shahi.Action.ReportFolder.EPM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.ReportFolder.EPM.beans.ChequeSearch;
import shahi.Action.ReportFolder.EPM.beans.Query;
import shahi.Action.ReportFolder.EPM.dao.ChequeSearchDao;

public class ChequeSearchService {
	
	@Autowired
	private ChequeSearchDao chequeSearchDao;
	
	public List<ChequeSearch> getAllCheques(Query query){
		return chequeSearchDao.loadAllCheques(query);
	}
}
