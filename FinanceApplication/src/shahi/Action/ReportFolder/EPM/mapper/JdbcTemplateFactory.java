package shahi.Action.ReportFolder.EPM.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateFactory {

	@Autowired
	@Qualifier("datatexTemplate")
	private JdbcTemplate datatexTemplate;
	
	@Autowired
	@Qualifier("shimogaTemplate")
	private JdbcTemplate shimogaTemplate;
	
	public  JdbcTemplate getDatatexTemplate(String division){
		if(division.equals("121")){
			return datatexTemplate;
		}else if(division.equals("101")){
			return shimogaTemplate;
		}
		return null;
	}
}
