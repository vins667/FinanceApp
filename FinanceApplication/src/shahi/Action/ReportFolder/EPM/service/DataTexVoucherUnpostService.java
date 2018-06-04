package shahi.Action.ReportFolder.EPM.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import shahi.Action.ReportFolder.EPM.beans.DataTexPurchaseBean;
import shahi.Action.ReportFolder.EPM.beans.DataTexSearchBean;
import shahi.Action.ReportFolder.EPM.dao.DataTexVoucherUnpostDao;

public class DataTexVoucherUnpostService {

	@Autowired
	private DataTexVoucherUnpostDao dataTexVoucherUnpostDao;
	
	public DataTexPurchaseBean getInvoice(DataTexSearchBean search) throws SQLException{
		List<DataTexPurchaseBean>list=dataTexVoucherUnpostDao.loadAllInvoices(search);
		return getTaxAmountBean(list,search.getInvoiceNo());
	}
	
	public DataTexPurchaseBean getUnpostedInvoice(DataTexSearchBean search) throws SQLException{
		List<DataTexPurchaseBean>list=dataTexVoucherUnpostDao.loadUnpostedInvoice(search);
		return getTaxAmountBean(list,search.getInvoiceNo());
	}
	 
    private DataTexPurchaseBean getTaxAmountBean(List<DataTexPurchaseBean> list,String invoiceNo){
    	Map<String,DataTexPurchaseBean> map=new LinkedHashMap<String,DataTexPurchaseBean>();
    	double invoiceAmount=0;
    	
		for(DataTexPurchaseBean bean:list){
			if(bean.getGLCODE()==null){
				invoiceAmount+=Double.valueOf(bean.getINVAMT());
			}
			
			if(Double.valueOf(bean.getGLAMT())>0){	
				DataTexPurchaseBean lastBean=map.get(invoiceNo.trim());
				if(lastBean!=null){
					bean.setNETAMT(Double.toString(Double.valueOf(bean.getINVAMT())+Double.valueOf(bean.getGLAMT())+Double.valueOf(lastBean.getGLAMT())));
					bean.setGLAMT(Double.toString(Double.valueOf(bean.getGLAMT())+Double.valueOf(lastBean.getGLAMT())));
				}else{
					bean.setNETAMT(Double.toString(Double.valueOf(bean.getINVAMT())+Double.valueOf(bean.getGLAMT())));
					bean.setGLAMT(Double.toString(Double.valueOf(bean.getGLAMT())));
				}
				bean.setINVAMT(Double.toString(invoiceAmount));
				
				//map.put(invoiceNo.trim(), bean);
			}
			map.put(invoiceNo.trim(), bean);
		}
		
		return map.get(invoiceNo.trim());
    }
	public void reverse(DataTexSearchBean search) throws RuntimeException {
		try{
			if(search.getEventCode().equals("4")){
				isPurchaseVoucherPosted(search);
			}else if(search.getEventCode().equals("5")){
				isSaleVoucherPosted(search);
			}
			 unPostVoucher(search);
		}catch(RuntimeException ex){
			throw new RuntimeException(ex.getLocalizedMessage());
		}
		
	}
	
	private void  isPurchaseVoucherPosted(DataTexSearchBean search)throws RuntimeException{
	    if(dataTexVoucherUnpostDao.isPurchaseVoucherPosted(search)){
	    	throw new RuntimeException("Voucher has been posted in movex");
	    }
	}
	
	private void  isSaleVoucherPosted(DataTexSearchBean search)throws RuntimeException{
		  if(dataTexVoucherUnpostDao.isSaleVoucherPosted(search)){
		    	throw new RuntimeException("Voucher has been posted in movex");
		    }
	}
	
	private void unPostVoucher(DataTexSearchBean search ){
		 dataTexVoucherUnpostDao.unPostVoucher(search);
	}
}
