package shahi.Action.ReportFolder.EPM.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.ReportFolder.EPM.movex.bean.FCR040;
import shahi.Action.ReportFolder.EPM.movex.bean.FDLEDG;

public class MovexDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<FCR040>loadAllPayrollCheques(String batchNo) throws SQLException{
		String sql="select acdivi,acyea4,acvser,acvono,acacdt,acacam,acait1,acait6,acait7 from m3fdbprd.fcr040 where acait6=?";
		
		return jdbcTemplate.query(sql, new Object[]{batchNo},new BeanPropertyRowMapper(FCR040.class));
	}
	public List<FDLEDG>loadAllLedgers(String batchNo) throws SQLException{
		String sql="select egdivi,egyea4,egvser,egvono,egacdt,egacam,egait1,egait6,egait7 from m3fdbprd.fgledg where egcono=111  and egait6=?";
		return jdbcTemplate.query(sql, new Object[]{batchNo},new BeanPropertyRowMapper(FDLEDG.class));
	}
}
