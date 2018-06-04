package shahi.Action.ReportFolder.EPM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import shahi.Action.ReportFolder.EPM.beans.PayrollPaymentsBeans;
import shahi.pagination.PaginationHelper;

public class PayrollPaymentDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("scanTemplate")
	private JdbcTemplate scanTemplate;

	private final int PAGE_SIZE=500;
	private  double CHQ_AMT=0;
	
	public Map<String,Object> loadAllCheques(String conditions,String divi,int pageNo,String period){
		PaginationHelper<PayrollPaymentsBeans> ph = new PaginationHelper<PayrollPaymentsBeans>();
		Map<String,Object>page=new LinkedHashMap<>();
		String sql="select pay_id,company_code,divi,chq_no,chq_date,acc_data_Desc,year,vser,vono,emp_code,acc_data_Desc,pros_date,fin_upd,chq_amount,bank_name from AMSNOW.fa_payroll_chq where CONO=111 AND divi=? " + conditions + " AND to_char(pros_date,'MM/YYYY')=? AND PYMT_DATE is null and nvl(chq_amount,0)>0  order by BANK_CODE,COMPANY_CODE,PYMT_TYPE,pay_id";
		
		 page.put(divi,ph.fetchPage(scanTemplate, getRowCount(conditions), sql, new Object[]{divi,period}, pageNo, PAGE_SIZE,  new ParameterizedRowMapper<PayrollPaymentsBeans>(){
			
			@Override
			public PayrollPaymentsBeans mapRow(ResultSet result, int arg1) throws SQLException {
				CHQ_AMT += result.getDouble("chq_amount");
				 return new PayrollPaymentsBeans(result.getString("divi"), result.getString("year"), result.getString("emp_code"), result.getString("acc_data_Desc"), result.getString("VSER"), result.getString("VONO"), result.getString("chq_date"), result.getString("chq_no"), result.getString("pros_date"), result.getString("pay_id"), result.getDouble("chq_amount"), result.getString("bank_name"), result.getString("company_code"));
			}

			

		}));
		 page.put("CHQ_AMT", CHQ_AMT);
		 
		 return page;
	}
	
	private String getRowCount(String conditions){
		String sql="select count(*) from AMSNOW.fa_payroll_chq where CONO=111 AND divi=? " + conditions + " AND to_char(pros_date,'MM/YYYY')=? AND PYMT_DATE is null and nvl(chq_amount,0)>0  order by BANK_CODE,COMPANY_CODE,PYMT_TYPE,pay_id";
		
		return sql;
	}
}
