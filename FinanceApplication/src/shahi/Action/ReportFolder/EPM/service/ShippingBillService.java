package shahi.Action.ReportFolder.EPM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import shahi.Action.ReportFolder.EPM.beans.ShippingBill;
import shahi.Action.ReportFolder.EPM.dao.ShippingBillDao;

public class ShippingBillService {

	@Autowired
	private ShippingBillDao  shippingBillDao;
	
	public List<ShippingBill>getShippingBillByInvoiceNo(String exciseInvoiceNo){
		  return shippingBillDao.loadAllShippingBills(exciseInvoiceNo);
	}
	
	public int update(String shippingBillNo,String shippingBillDate,String exciseInvoiceNo) throws DataAccessException{
		return shippingBillDao.update(shippingBillNo, shippingBillDate,exciseInvoiceNo);
	}
	
}
