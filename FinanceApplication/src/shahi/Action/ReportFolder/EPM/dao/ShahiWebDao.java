package shahi.Action.ReportFolder.EPM.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class ShahiWebDao {

	@Qualifier("shahiWeb")
	@Autowired
	private JdbcTemplate shahiWeb;
	
	public int  updateProcedureHistory(String procName,String status,String message){
		String sql="insert into seplweb.m4_gst_upd_hist values(?,?,?,?,?)";
		return shahiWeb.update(sql,new Object[]{DateUtil.getCurrentDateInString(),procName,DateUtil.getCurrentDateInString(),
				status,message!=null?message.substring(0,99):""});
	}
}
