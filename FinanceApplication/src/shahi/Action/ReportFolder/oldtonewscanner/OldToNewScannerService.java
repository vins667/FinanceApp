package shahi.Action.ReportFolder.oldtonewscanner;



import java.util.List;

import shahi.Action.ReportFolder.EPM.dao.OldToNewScannerDao;

public class OldToNewScannerService {
	public   void save(){
		
		OldToNewScannerDao dao=new OldToNewScannerDao();
		List<OldToNewScanner>scannerList=dao.loadAllScannerBills();
		for(OldToNewScanner scanner:scannerList){
			dao.insertIntoNewScanner(scanner);
		}
	}
}
