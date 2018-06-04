package shahi.Action.ReportFolder.EPM.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class InvoiceMapper {
	public static int createInvoice(SimpleJdbcInsert jdbc,ScanInvoiceTempDetail temp,RequestBean conditions){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyymmdd");
		Date currentDate=new Date();
		Map<String,Object>parameters=new HashMap<>();
		parameters.put("APCONO","111");
		parameters.put("APDIVI",temp.getDivision());
		parameters.put("APYEA4",temp.getFinancialYear());
		parameters.put("APSPYN",temp.getVendorCode());
		parameters.put("APSUNO",temp.getVendorCode());
		parameters.put("APINYR",temp.getFinancialYear());
		parameters.put("APSINO",temp.getInvoiceNo());
		parameters.put("APPYST","00");
		parameters.put("APIVDT",sdf.format(temp.getInvoiceDate()));
		parameters.put("APCUAM",temp.getInvoiceAmount());
		parameters.put("APCUCD",conditions.getSupplier().getCurrency());
		parameters.put("APWHLO",temp.getLocationCode());
		parameters.put("APPONP",conditions.getAccountingFlag());
		parameters.put("APAIT1","");
		parameters.put("APAIT2","");
		parameters.put("APAIT3","");
		parameters.put("APAIT4","");
		parameters.put("APAIT5","");
		parameters.put("APAIT6","");
		parameters.put("APAIT7","");
		parameters.put("APLMDT",sdf.format(currentDate));
		parameters.put("APRGDT",sdf.format(currentDate));
		parameters.put("APRGTM",currentDate.getTime());
		parameters.put("APCHID","Movex");
		parameters.put("APCHNO","0");
		parameters.put("aptx40","");
		parameters.put("apxtds",conditions.getTdsCode());
		parameters.put("apxnta","0");
		parameters.put("aplrnm","");
		parameters.put("APATDS","0");
		parameters.put("APARAT","0");
		parameters.put("APUSID",temp.getUserId());
		parameters.put("APFRGR",conditions.getFreight());
		parameters.put("APLCFG",conditions.getLcFG());
		parameters.put("APREF1","");
		parameters.put("APSCDT",sdf.format(temp.gettDate()));
		parameters.put("APACFG",conditions.getAccountingFlag());
		parameters.put("apbtno","0");
		return  jdbc.execute(parameters);
	}
}
