package shahi.Action.ReportFolder.EPM.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.ReportFolder.EPM.beans.ChequeSearch;
import shahi.Action.ReportFolder.EPM.beans.Query;

public class ChequeSearchDao {
	@Qualifier("jdbcTemplate")
	@Autowired
	private JdbcTemplate  db2Template;
	
	public List<ChequeSearch> loadAllCheques(Query query){
		
		String sql="select ckcono,ckdivi,ckyea4,ckbkid,ckchkn,ckspyn,cksunm,ckpycu,ckait1,ckdtpr,ckvser,ckvono from m3fdbprd.fapchk where ";
		if(query.getSearchKey().equals("1")){
			sql+="cksunm like '%"+query.getSearchValue().toUpperCase()+ "%'";
		}else if(query.getSearchKey().equals("2")){
			sql+="ckpycu="+query.getSearchValue().trim();
		}else if(query.getSearchKey().equals("3")){
			sql+="ckspyn='"+query.getSearchValue().trim()+ "'";
		}
		return db2Template.query(sql, new BeanPropertyRowMapper(ChequeSearch.class));
		
	}
}
