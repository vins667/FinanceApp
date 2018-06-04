package shahi.Action.ReportFolder.EPM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import shahi.Action.ReportFolder.EPM.dao.UpdateHSNForUPChargesDao;

public class UpdateHSNForUPChargesService {

	@Autowired 
	private UpdateHSNForUPChargesDao updateHSNForUPChargesDao;
	
	@Transactional(value="db2TxManager",isolation=Isolation.READ_COMMITTED,rollbackFor={DataAccessException.class,Exception.class})
	public void updateCharges(){
		updateHSNForUPChargesDao.removeTempTables();
		updateHSNForUPChargesDao.createTempTables();
		updateHSNForUPChargesDao.updateFirstTempTable();
		updateHSNForUPChargesDao.updateSecondTempTable();
		updateHSNForUPChargesDao.updateFirstTaxTable();
		updateHSNForUPChargesDao.updateSecondTaxTable();
	}
}
