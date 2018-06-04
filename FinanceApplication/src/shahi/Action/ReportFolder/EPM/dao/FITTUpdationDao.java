package shahi.Action.ReportFolder.EPM.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.ReportFolder.EPM.beans.FITTUpdation;
import shahi.Action.ReportFolder.EPM.beans.SearchBean;

public class FITTUpdationDao {

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	private String sql;
	private SimpleDateFormat fromDate;
	private SimpleDateFormat toDate;
	private String date;
	
	public FITTUpdationDao(){
		fromDate=new SimpleDateFormat("dd/MM/yyyy");
		toDate=new SimpleDateFormat("yyyyMMdd");
	}
	
	public int update(FITTUpdation update,SearchBean formData) throws SQLException{
		sql=null;
		try {
			date=toDate.format(fromDate.parse(formData.getFSFTDT()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(formData!=null && formData.getFSFTDT()!=null && formData.getFSFTDT().length()>1  && formData.getFSCURT()!=null && formData.getFSCURT().length()>0 ){
			sql="update cinfdbprd.xsftno set FSCURT=?,FSFTDT=? where FSCONO=? and FSDIVI=? and FSIVNO=? and FSYEA4=? and FSRENP=?";
			return jdbcTemplate.update(sql, new Object[]{
					formData.getFSCURT(),date,update.getFSCONO().trim(),update.getFSDIVI().trim(),update.getFSIVNO().trim(),update.getFSYEA4(),update.getFSRENP()
			});
		}else if(formData!=null && formData.getFSFTDT()!=null && formData.getFSFTDT().length()>0){
			sql="update cinfdbprd.xsftno set FSFTDT=? where FSCONO=? and FSDIVI=? and FSIVNO=? and FSYEA4=? and FSRENP=?";
			return jdbcTemplate.update(sql, new Object[]{
					date,update.getFSCONO().trim(),update.getFSDIVI().trim(),update.getFSIVNO().trim(),update.getFSYEA4(),update.getFSRENP()
			});
		}else if(formData!=null && formData.getFSCURT()!=null && formData.getFSCURT().length()>0){
			sql="update cinfdbprd.xsftno set FSCURT=? where FSCONO=? and FSDIVI=? and FSIVNO=? and FSYEA4=? and FSRENP=?";
			return jdbcTemplate.update(sql, new Object[]{
					formData.getFSCURT(),update.getFSCONO().trim(),update.getFSDIVI().trim(),update.getFSIVNO().trim(),update.getFSYEA4(),update.getFSRENP()
			});
		}
		
		return 0;
	}
	
	public List<FITTUpdation>loadAll(String FSFTNO){
		String sql="select * from cinfdbprd.xsftno where FSCONO='111' and FSFTNO=?";
		return jdbcTemplate.query(sql, new Object[]{FSFTNO}, new BeanPropertyRowMapper(FITTUpdation.class));
	}

	
}
