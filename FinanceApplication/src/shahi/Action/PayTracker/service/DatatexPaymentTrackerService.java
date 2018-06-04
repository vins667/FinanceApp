package shahi.Action.PayTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.PayTracker.bean.DatatexPaymentTracker;
import shahi.Action.PayTracker.bean.PaymentSearch;
import shahi.Action.PayTracker.dao.DatatexPaymentTrackerDao;

public class DatatexPaymentTrackerService {

	@Autowired
    private DatatexPaymentTrackerDao paymentTrackerDao;
  
	public List<DatatexPaymentTracker>getAllDatatexPayments(PaymentSearch search){
       return paymentTrackerDao.loadAllDatatexPayments(search);
	}
}
