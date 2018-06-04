package shahi.Action.ReportFolder.EPM.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.M4.M4WhoMaster;
import shahi.Action.ReportFolder.EPM.beans.SaleLedger;

public class SaleLedgerDao {

	@Qualifier("db2")
	@Autowired
	private JdbcTemplate db2;
	
	public  void removeTempTable(){
		String sql="drop table m3fdbprd.kd ";
		db2.execute(sql);
	}
	public  void createTempTable(){
		//removeTempTable();
		String sql="create table m3fdbprd.kd as (select * from m3fdbprd.fsledg where escono=111 and esdivi='100'  and esivtp<>'LC'  and esyea4>=2017 and (escono||esdivi||esyea4||esvser||esvono) in"
				+"(select distinct escono||esdivi||esyea4||esvser||esvono from m3fdbprd.fsledg where escono=111 and esdivi='100' and esyea4>=2017 and substr(esvser,3,1)='9' and esivtp='LC'  )) with data";
		db2.execute(sql);
	}
	public List<SaleLedger>loadAllSaleLedger(){
		//createTempTable();
		/*String sql="(select * from m3fdbprd.fsledg where escono=111 and esdivi='100'  and esivtp<>'LC'  and esyea4=2017 and (escono||esdivi||esyea4||esvser||esvono) in"+
					" (select distinct escono||esdivi||esyea4||esvser||esvono from m3fdbprd.fsledg where escono=111 and esdivi='100' and esyea4=2017 and esvser='409' and esivtp='LC'  ))";*/
		String sql="select * from m3fdbprd.kd";
		return db2.query(sql, new BeanPropertyRowMapper(SaleLedger.class));
	}
	
	public void deleteUpdatedData(){
		String sql="delete from m3fdbprd.kd  where  (escono||esdivi||esyea4||espyno||escino) in (select t3cono||t3divi||t3yea4||t3cuno||t3exin from m3fdbPRD.ctaxln  where t3cono=111 and t3divi='100' and t3yea4>=2017)";
	}
	public void updateTempTable(){
		//createTempTable();
		String sql="update m3fdbprd.kd k set k.esblby=(select distinct escino from  m3fdbprd.fsledg s where s.escono=111 and s.esdivi='100' and s.esyea4>=2017 and substr(s.esvser,3,1)='9' "
				+ " and s.esivtp='LC' and s.escono=k.escono and s.esdivi=k.esdivi and s.esyea4=k.esyea4 and s.esvser=k.esvser and s.esvono=k.esvono)";
		db2.update(sql);
	}
	
	public int updateVoucher(){
		//update();
		String sql="update m3fdbPRD.ctaxln set t3txt5=3,t3exin=(select escino from m3fdbprd.kd where escono=t3cono and esdivi=t3divi and esyea4=t3yea4 and esblby=t3exin) where t3cono=111 and t3divi='100' and t3yea4>=2017 "
				+" and (t3cono||t3divi||t3yea4||t3cuno||t3exin) in (select escono||esdivi||esyea4||escuno||esblby from  m3fdbprd.kd  )";
		return db2.update(sql);
	}
	
	/*public void updateGEOCode(M4WhoMaster geoCode){
		String sql="update m3fdbPRD.ctaxln set t3geof=? where t3cono=111 and t3divi='100' and  t3geof<>? and (t3cono||t3divi||t3yea4||trim(t3cuno)||trim(t3exin)) in"
					+"(select escono||esdivi||esyea4||trim(escuno)||trim(escino) from m3fdbprd.fsledg where escono=111 and esyea4=2017  and esvser=?)";	
		     db2.update(sql,new Object[]{geoCode.getM4GEOC().trim(),geoCode.getM4GEOC().trim(),geoCode.getWHVSER()});
	}
	
	public List<M4WhoMaster>loadAllWarehouse(){
		String sql="select distinct M4WHLO,M4GEOC from seplweb.GST_GEO_UPDATE where whvser is not null";
		return shahiWeb.query(sql,new BeanPropertyRowMapper(M4WhoMaster.class));
	}*/
}
