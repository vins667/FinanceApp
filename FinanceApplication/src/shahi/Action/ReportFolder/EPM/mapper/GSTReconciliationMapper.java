package shahi.Action.ReportFolder.EPM.mapper;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import shahi.Action.ReportFolder.EPM.beans.GSTReconciliation;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class GSTReconciliationMapper {

	public static int[] insert(final List<GSTReconciliation> list,SimpleJdbcInsert simpleJdbcInsert){
		List<Map<String, Object>> batchValues = new ArrayList<>(list.size());


		int[] ints=null;
		try{
			
			for(GSTReconciliation detail:list){
				Map<String,Object>parameters=new LinkedHashMap<>();
			    parameters.put("COMPANY",detail.getCOMPANY());
				parameters.put("DIVISION", detail.getDIVISION());
				parameters.put("YEAR", detail.getYEAR());
				parameters.put("SHAHIGSTN", detail.getSHAHIGSTN());
				parameters.put("SUPLGSTN", detail.getSUPLGSTN());
				parameters.put("CNTPTYST", detail.getCNTPTYST());
				parameters.put("CUSTNAME", detail.getCUSTNAME());
				parameters.put("INVOICENO", detail.getINVOICENO());
				parameters.put("INVOICEDT",new java.sql.Date(DateUtil.getOracleDate(detail.getINVOICEDT()).getTime()));
				if(detail.getLINE()!=null && !detail.getLINE().isEmpty()){
					parameters.put("LINE", BigDecimal.valueOf(Double.valueOf(detail.getLINE().toString())));
				}
				if(detail.getTAXVALUE()!=null && !detail.getTAXVALUE().isEmpty()){
					parameters.put("TAXVALUE", BigDecimal.valueOf(Double.valueOf(detail.getTAXVALUE().toString())));
				}
				if(detail.getLineItemAmount()!=null && !detail.getLineItemAmount().isEmpty()){
					parameters.put("lineItemAmount", BigDecimal.valueOf(Double.valueOf(detail.getLineItemAmount().toString())));
				}
				if(detail.getTAXRATE()!=null && !detail.getTAXRATE().isEmpty()){
					parameters.put("TAXRATE", BigDecimal.valueOf(Double.valueOf(detail.getTAXRATE().toString())));
				}
				
				
				if(detail.getINVVALUE()!=null && !detail.getINVVALUE().isEmpty()){
					parameters.put("INVVALUE", BigDecimal.valueOf(Double.valueOf(detail.getINVVALUE().toString())));
				}
				if(detail.getINVTYPE()!=null){
					parameters.put("INVTYPE", detail.getINVTYPE().trim());
				}
				if(detail.getPOS()!=null){
					parameters.put("POS", detail.getPOS().trim());
				}
				if(detail.getREVCHG()!=null){
					parameters.put("REVCHG", detail.getREVCHG().trim());
				}
				parameters.put("REASON", detail.getREASON());
				parameters.put("CHID", detail.getCHID());
				//parameters.put("RECODATE", new java.sql.Date(DateUtil.getCurrentDateInDDMMMYY().getTime()));
				parameters.put("TDATE", new java.sql.Date(DateUtil.getCurrentDateInDDMMMYY().getTime()));
				//System.out.println("Invoice No :"+detail.getINVOICENO());
				//simpleJdbcInsert.execute(parameters);
				batchValues.add(parameters);
			}
			
			
			ints = simpleJdbcInsert.executeBatch(batchValues.toArray(new Map[list.size()]));
		}catch(NullPointerException |DataAccessException ex){
			try {
				simpleJdbcInsert.getJdbcTemplate().getDataSource().getConnection().rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(ex.getLocalizedMessage());
		}
		return ints;
	}

	public static int save(final GSTReconciliation detail,SimpleJdbcInsert simpleJdbcInsert){
		try{
				Map<String,Object>parameters=new LinkedHashMap<>();
			    parameters.put("COMPANY",detail.getCOMPANY());
				parameters.put("DIVISION", detail.getDIVISION());
				parameters.put("YEAR", detail.getYEAR());
				parameters.put("SHAHIGSTN", detail.getSHAHIGSTN());
				parameters.put("SUPLGSTN", detail.getSUPLGSTN());
				parameters.put("CNTPTYST", detail.getCNTPTYST());
				parameters.put("CUSTNAME", detail.getCUSTNAME());
				parameters.put("INVOICENO", detail.getINVOICENO());
				parameters.put("INVOICEDT",new java.sql.Date(DateUtil.getOracleDate(detail.getINVOICEDT()).getTime()));
				if(detail.getLINE()!=null && !detail.getLINE().isEmpty()){
					parameters.put("LINE", BigDecimal.valueOf(Double.valueOf(detail.getLINE().toString())));
				}
				if(detail.getTAXVALUE()!=null && !detail.getTAXVALUE().isEmpty()){
					parameters.put("TAXVALUE", BigDecimal.valueOf(Double.valueOf(detail.getTAXVALUE().toString())));
				}
				if(detail.getLineItemAmount()!=null && !detail.getLineItemAmount().isEmpty()){
					parameters.put("lineItemAmount", BigDecimal.valueOf(Double.valueOf(detail.getLineItemAmount().toString())));
				}
				if(detail.getTAXRATE()!=null && !detail.getTAXRATE().isEmpty()){
					parameters.put("TAXRATE", BigDecimal.valueOf(Double.valueOf(detail.getTAXRATE().toString())));
				}
				
				if(detail.getINVVALUE()!=null && !detail.getINVVALUE().isEmpty()){
					parameters.put("INVVALUE", BigDecimal.valueOf(Double.valueOf(detail.getINVVALUE().toString())));
				}
				if(detail.getINVTYPE()!=null){
					parameters.put("INVTYPE", detail.getINVTYPE().trim());
				}
				if(detail.getPOS()!=null){
					parameters.put("POS", detail.getPOS().trim());
				}
				if(detail.getREVCHG()!=null){
					parameters.put("REVCHG", detail.getREVCHG().trim());
				}
				parameters.put("REASON", detail.getREASON());
				parameters.put("CHID", detail.getCHID());
				parameters.put("ANX_TYPE", detail.getANX_TYPE());
				parameters.put("DOC_TYPE", detail.getDOC_TYPE());
				parameters.put("RECODT", detail.getRecoDate());
				parameters.put("TDATE", new java.sql.Date(DateUtil.getCurrentDateInDDMMMYY().getTime()));
				return  simpleJdbcInsert.execute(parameters);
		}catch(NullPointerException |DataAccessException ex){
			try {
				simpleJdbcInsert.getJdbcTemplate().getDataSource().getConnection().rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(ex.getLocalizedMessage());
		}
		return 0;
	}
}
