package shahi.Action.ReportFolder.EPM.mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import shahi.Action.ReportFolder.EPM.beans.RequestBean;
import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceTempDetail;

public class InvoiceMapper {

	public static String getQuery(){
		String query="insert into cinfdbprd.supinv(APCONO,APDIVI,APYEA4,APSPYN,APSUNO,APINYR,APSINO,APPYST,APIVDT,APCUAM,APCUCD,APWHLO,APPONP,"
				+"APAIT1,APAIT2,APAIT3,APAIT4,APAIT5,APAIT6,APAIT7,APLMDT,APRGDT,APRGTM,APCHID,APCHNO,aptx40,apxtds,"
				+"apxnta,aplrnm,APATDS,APARAT,APUSID,APFRGR,APLCFG,APSCDT,APACFG,apbtno) values(:APCONO,:APDIVI,:APYEA4,:APSPYN,:APSUNO,:APINYR,:APSINO,:APPYST,:APIVDT,:APCUAM,:APCUCD,:APWHLO,:APPONP,"
				+ ":APAIT1,:APAIT2,:APAIT3,:APAIT4,:APAIT5,:APAIT6,:APAIT7,:APLMDT,:APRGDT,:APRGTM,:APCHID,:APCHNO,:aptx40,:apxtds,"
				+ ":apxnta,:aplrnm,:APATDS,:APARAT,:APUSID,:APFRGR,:APLCFG,:APSCDT,:APACFG,:apbtno)";
		return query;
	}
	public static int createInvoice(NamedParameterJdbcTemplate jdbc,ScanInvoiceTempDetail temp,RequestBean conditions){
		try{
			if(!(conditions.getUpdateFlag().equals("D")||conditions.getUpdateFlag().equals("M"))){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				Date currentDate=new Date();
				Map<String,Object>parameters=new LinkedHashMap<String,Object>();
				parameters.put("APCONO","111");
				parameters.put("APDIVI",temp.getDivision());
				parameters.put("APYEA4",Integer.valueOf(temp.getFinancialYear()));
				parameters.put("APSPYN",temp.getVendorCode());
				parameters.put("APSUNO",temp.getVendorCode());
				parameters.put("APINYR",temp.getFinancialYear());
				parameters.put("APSINO",temp.getInvoiceNo());
				parameters.put("APPYST","00");
				parameters.put("APIVDT",sdf.format(temp.getInvoiceDate()));
				parameters.put("APCUAM",temp.getInvoiceAmount());
				if(temp.getUserType().equals("PO")){
					parameters.put("APCUCD",conditions.getPoCurrency());
					System.out.println("PO No"+temp.getPoNo());
				}else{
					parameters.put("APCUCD",conditions.getSupplier().getCurrency());
				}
				parameters.put("APWHLO",temp.getLocationCode());
				parameters.put("APPONP",conditions.getPoNonFlag());
				parameters.put("APAIT1","");
				parameters.put("APAIT2","");
				parameters.put("APAIT3","");
				parameters.put("APAIT4","");
				parameters.put("APAIT5","");
				parameters.put("APAIT6","");
				parameters.put("APAIT7","");
				parameters.put("APLMDT",sdf.format(currentDate));
				parameters.put("APRGDT",sdf.format(currentDate));
				parameters.put("APRGTM",new SimpleDateFormat("HHmmss").format(currentDate.getTime()));
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
				if(temp.gettDate()==null){
					parameters.put("APSCDT",sdf.format(currentDate));
				}else{
					parameters.put("APSCDT",sdf.format(temp.gettDate()));
				}
				parameters.put("APACFG",conditions.getAccountingFlag());
				parameters.put("apbtno","0");
				return  (int) jdbc.execute(getQuery(),parameters,new PreparedStatementCallback(){
					@Override
					public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

						return ps.executeUpdate();  
					}

				});	
			}
		}catch(DataIntegrityViolationException ex){
			System.out.println("ex:"+temp.getPoNo());
		}
		return 0;
	}
}
