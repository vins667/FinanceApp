package shahi.Action.ReportFolder.EPM.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.ReportFolder.EPM.beans.FITTUpdation;
import shahi.Action.ReportFolder.EPM.beans.SearchBean;
import shahi.Action.ReportFolder.EPM.dao.FITTUpdationDao;

public class FITTUpdationService {

	@Autowired
	private FITTUpdationDao dao;
	
	public List<FITTUpdation>loadAll(String FSFTNO){
		return dao.loadAll(FSFTNO);
	}
	public void  update(List<FITTUpdation> beanList,SearchBean formData) throws SQLException{
		for(FITTUpdation bean:beanList)
		  dao.update(bean,formData);
	}
	
}
