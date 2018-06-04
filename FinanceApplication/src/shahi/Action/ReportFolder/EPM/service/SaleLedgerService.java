package shahi.Action.ReportFolder.EPM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import shahi.Action.ReportFolder.EPM.beans.SaleLedger;
import shahi.Action.ReportFolder.EPM.dao.SaleLedgerDao;
import shahi.Action.ReportFolder.EPM.dao.ShahiWebDao;


public class SaleLedgerService {

	@Autowired
	private SaleLedgerDao saleLedgerDao;

	public List<SaleLedger>getAllSaleLedger(){
		 return saleLedgerDao.loadAllSaleLedger();
	}
	@Transactional(value="db2TxManager",isolation=Isolation.READ_COMMITTED,rollbackFor={DataAccessException.class,Exception.class})
	public void update(){
		/*return saleLedgerDao.updateVoucher();*/
		 saleLedgerDao.removeTempTable();
		 saleLedgerDao.createTempTable();
		 saleLedgerDao.deleteUpdatedData();
		 saleLedgerDao.updateTempTable();
		 saleLedgerDao.updateVoucher();
	}
	
	
	/*public void updateGEOCode(){
		List<M4WhoMaster>wareHouseList=saleLedgerDao.loadAllWarehouse();
		for(M4WhoMaster geoCode:wareHouseList){
			saleLedgerDao.updateGEOCode(geoCode);
		}
	}*/
}
