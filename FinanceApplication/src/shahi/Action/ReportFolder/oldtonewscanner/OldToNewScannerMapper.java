package shahi.Action.ReportFolder.oldtonewscanner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class OldToNewScannerMapper {

	private static SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
	private static SimpleDateFormat cdf=new SimpleDateFormat("dd/MM/yyyy");
	private static Date date,tdate;
	
	public static int insert(OldToNewScanner  oldToNewScanner,SimpleJdbcInsert simpleInsert){
		try {
			date=cdf.parse(oldToNewScanner.getDATE1());
			tdate=cdf.parse(oldToNewScanner.getTDATE());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<String,Object>parameters=new LinkedHashMap<>();
		parameters.put("INV_NO",oldToNewScanner.getINVOICENUMBER());
		try {
			parameters.put("INV_DATE",new java.sql.Date(sdf.parse(sdf.format(date)).getTime()));
			parameters.put("TDATE",new java.sql.Date(sdf.parse(sdf.format(tdate)).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parameters.put("VENDER_CODE", oldToNewScanner.getMVXSUNO());
		parameters.put("INV_AMOUNT", new BigDecimal(oldToNewScanner.getTOTAL()));
		parameters.put("BOA", null);
		parameters.put("LOCATION_CODE", oldToNewScanner.getWAREHOUSE());
		parameters.put("VENDOR_GSTN", null);
		parameters.put("SHAHI_GSTN", null);
		parameters.put("AUTHORIZER", null);
		parameters.put("USER_TYPE", null);
		parameters.put("USER_ID", oldToNewScanner.getSEH_USER());
		
		parameters.put("DIVI", null);
		parameters.put("FIN_YEAR", oldToNewScanner.getCYEAR());
		parameters.put("UPD_FLAG", 'K');
		if(isAlphaNumberic(oldToNewScanner.getPO())){
			parameters.put("PO_NO", Long.valueOf(oldToNewScanner.getPO().substring(1)));
		}else{
			parameters.put("PO_NO", Long.valueOf(oldToNewScanner.getPO()));
		}
		
		return simpleInsert.execute(parameters);
	}
	
	public static boolean isAlphaNumberic(String po){
		int firstLetter=(int)po.charAt(0);
		if((firstLetter>=65 && firstLetter<=90 )||(firstLetter>=97 && firstLetter<=122)){
			return true;
		}
		return false;
	}
}
