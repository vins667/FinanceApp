package shahi.Action.ReportFolder.EPM.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.ReportFolder.EPM.dao.PayrollPaymentDao;



public class PayrollPaymentService {

	@Autowired
	private PayrollPaymentDao  payrollPaymentDao;
	
	
	public Map<String,Object>loadAllCheques(String conditions,String divi,int pageNo,String period){
		return payrollPaymentDao.loadAllCheques(conditions, divi, pageNo, period);
	}
}
