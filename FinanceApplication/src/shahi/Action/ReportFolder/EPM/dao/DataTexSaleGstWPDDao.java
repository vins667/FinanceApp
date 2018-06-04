package shahi.Action.ReportFolder.EPM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shahi.Action.ReportFolder.EPM.beans.DataTexSaleBean;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class DataTexSaleGstWPDDao {

	@Autowired
	@Qualifier("wpdTemplate")
	private JdbcTemplate wpdTemplate;
	
	public List<DataTexSaleBean> loadAllSaleInvoices(String fromDate,String toDate,String division)throws SQLException{
		String qry="";
		if(fromDate!=null && !fromDate.isEmpty()&& toDate !=null && !toDate.isEmpty()){
			qry+="and accountingdate between '"+DateUtil.getSFLDateFormat(fromDate)+"' and '"+DateUtil.getSFLDateFormat(toDate)+"'";
		}
		String sql="select a.ABSUNIQUEID,a.companycode,a.divisioncode,a.finyear,VARCHAR_FORMAT(a.accountingdate,'DD-MM-YYYY') accountingdate,a.invoiceno,VARCHAR_FORMAT(a.invoicedate,'DD-MM-YYYY') invoicedate,a.customersuppliercode,a.taxform,a.invamt,b.glamt,nvl(a.invamt,0)+nvl(b.glamt,0) netamt,a.qty,VARCHAR_FORMAT(a.duedate,'DD-MM-YYYY') duedate,a.currencycode,a.currencyconversionrate,a.narration  from"
					+" (select ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,taxform,duedate,currencycode,"
					+" currencyconversionrate,narration ,sum(QUANTITY) qty,sum(INVAMOUTINR) invamt"
					+" from movexposting a where  trim(a.companycode)='100' and trim(a.divisioncode)=105 "+qry+" and (fromstatecode is not null or fromstatecode is not null) and a.eventcode=5 and a.glcode is null and flag=1"
					+" group by ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,taxform,duedate,currencycode,"
					+" currencyconversionrate,narration ) a left outer join"
					+" (select ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,sum(glamount) glamt"
					+" from movexposting   where trim(companycode)='100' and trim(divisioncode)=105 "+qry+" and (fromstatecode is not null or fromstatecode is not null) and eventcode=5 and glcode is not null and flag=1"
					+" group by ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode) b"
					+" on a.ABSUNIQUEID=b.ABSUNIQUEID order by a.accountingdate,a.invoiceno";
		
		       return wpdTemplate.query(sql, new BeanPropertyRowMapper(DataTexSaleBean.class));
	}
	
	
	private List<String>getUniqueInvoiceNo(String fromDate,String toDate,String division){
		String sql="select invoiceno from movexposting where trim(a.companycode)='100' and trim(a.divisioncode)="+division+" and accountingdate between '"+DateUtil.getSFLDateFormat(fromDate)+"' and '"+DateUtil.getSFLDateFormat(toDate)+"'";
		return wpdTemplate.query(sql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("invoiceno");
		}
		});
	}
}
