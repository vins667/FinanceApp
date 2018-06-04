package shahi.Action.ReportFolder.EPM.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceDetail;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class ScanInvoiceHistoryMapper {

	public static int insert(SimpleJdbcInsert insert,ScanInvoiceDetail detail){
		Map<String,Object>parameters=new LinkedHashMap<>();
		try{
			parameters.put("INV_NO", detail.getInvoiceNo());
			parameters.put("INV_DATE", detail.getInvoiceDate());
			parameters.put("VENDER_CODE", detail.getVendorCode());
			parameters.put("INV_AMOUNT", BigDecimal.valueOf(Double.valueOf(detail.getInvoiceAmount().toString())));
			parameters.put("BOA", detail.getVoucherType());
			parameters.put("LOCATION_CODE", detail.getLocationCode());
			parameters.put("VENDOR_GSTN", detail.getVendorGSTN());
			parameters.put("SHAHI_GSTN", detail.getShahiGSTN());
			parameters.put("AUTHORIZER", detail.getAuthorizer());
			parameters.put("USER_TYPE", detail.getUserType());
			parameters.put("USER_ID", detail.getUserId());
			parameters.put("TDATE", new Date());
			parameters.put("DIVI", detail.getDivision());
			parameters.put("FIN_YEAR", detail.getFinancialYear());
			parameters.put("UPD_FLAG", detail.getUpdFlag());
			parameters.put("PO_NO", detail.getPoNo());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return insert.execute(parameters);
	}

}
