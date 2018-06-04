package shahi.Action.ReportFolder.EPM.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.ReportFolder.EPM.beans.ShippingBill;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class ShippingBillDao {
	@Autowired
	private  JdbcTemplate jdbcTemplate;
	private String currentDate=null;
	public List<ShippingBill>loadAllShippingBills(String exciseInvoiceNo){
		String sql="select XSIVNO,XSCUNO,XSEXIV,XSSBNO,XSSBDT,XSREM2,XSCUAM,XSCUCD from CINFDBPRD.XSHPIN where XSEXIV=?";
		return jdbcTemplate.query(sql,new Object[]{exciseInvoiceNo.trim()},new BeanPropertyRowMapper(ShippingBill.class));
		
	}
	
	public int update(String shippingBillNo,String shippingBillDate,String exciseInvoiceNo) throws DataAccessException{
		currentDate=null;
        currentDate=!shippingBillDate.isEmpty()?DateUtil.converToYYYYMMDD(shippingBillDate.trim()):"";
		if(shippingBillNo!=null && !shippingBillNo.isEmpty() && shippingBillDate!=null && !shippingBillDate.isEmpty()){
			String sql="update CINFDBPRD.XSHPIN set XSSBNO=?,XSSBDT=?,XSLMDT=? where XSEXIV=?";
			return jdbcTemplate.update(sql,new Object[]{shippingBillNo.trim(),currentDate,DateUtil.getCurrentDateInYYYYMMDD(),exciseInvoiceNo.trim()});
		}else if(shippingBillNo!=null && !shippingBillNo.isEmpty()){
			String sql="update CINFDBPRD.XSHPIN set XSSBNO=? ,XSLMDT=? where XSEXIV=?";
			return jdbcTemplate.update(sql,new Object[]{shippingBillNo.trim(),DateUtil.getCurrentDateInYYYYMMDD(),exciseInvoiceNo.trim()});
		}else if(shippingBillDate!=null && !shippingBillDate.isEmpty()){
			String sql="update CINFDBPRD.XSHPIN set XSSBDT=?,XSLMDT=? where XSEXIV=?";
			return jdbcTemplate.update(sql,new Object[]{currentDate,DateUtil.getCurrentDateInYYYYMMDD(),exciseInvoiceNo.trim()});
		}
		return 0;
	}
}
