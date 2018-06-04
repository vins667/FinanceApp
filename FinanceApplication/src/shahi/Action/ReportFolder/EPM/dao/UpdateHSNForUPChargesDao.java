package shahi.Action.ReportFolder.EPM.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateHSNForUPChargesDao {

	@Qualifier("db2")
	@Autowired
	private JdbcTemplate db2;
	
	public  void removeTempTables(){
		String sql="drop table m3fdbprd.kd ";
		db2.execute(sql);
		sql="drop table m3fdbprd.kk";
		db2.execute(sql);
		sql=null;
	}
	
	public void createTempTables(){
		String sql="create table m3fdbprd.kd as (select * from cinfdbprd.zctaxl where  t3cono=111 and t3divi='100' and t3yea4>=2018  and t3rgdt>=20180201 AND T3CSNO='') with data";
		db2.execute(sql);
		 sql="create table m3fdbprd.kk as (select  t3cono,t3divi,t3yea4,t3ivno,t3unms,max(t3csno) t3csno from cinfdbprd.zctaxl where  t3cono=111 and t3divi='100' and t3yea4>=2018 and t3rgdt>=20180201 AND T3CSNO<>'' "+
			" and (t3cono||t3divi||t3yea4||t3ivno) in (select (t3cono||t3divi||t3yea4||t3ivno) from m3fdbprd.kd) "+
			" group by t3cono,t3divi,t3yea4,t3ivno,t3unms ) with data";
			db2.execute(sql);
			sql=null;
	}
	
	public void updateFirstTempTable(){
		String sql="update m3fdbprd.kd k set k.t3csno=(select b.t3csno from m3fdbprd.kk b where b.t3cono=k.t3cono and b.t3divi=k.t3divi and b.t3yea4=k.t3yea4 and b.t3ivno=k.t3ivno) "+
					" where (t3cono||t3divi||t3yea4||t3ivno) in (select (t3cono||t3divi||t3yea4||t3ivno) from m3fdbprd.kk)";
		db2.update(sql);
	}
	public void updateSecondTempTable(){
		String sql="update m3fdbprd.kd k set k.t3unms=(select b.t3unms from m3fdbprd.kk b where b.t3cono=k.t3cono and b.t3divi=k.t3divi and b.t3yea4=k.t3yea4 and b.t3ivno=k.t3ivno)"+
					" where (t3cono||t3divi||t3yea4||t3ivno) in (select (t3cono||t3divi||t3yea4||t3ivno) from m3fdbprd.kk)";
		db2.update(sql);
	}
	
	public void updateFirstTaxTable(){
		String sql="update cinfdbprd.zctaxl z set z.t3unms=(select t3unms from m3fdbprd.kd k where k.t3cono=z.t3cono and k.t3divi=z.t3divi and k.t3yea4=z.t3yea4 and k.t3ivno=z.t3ivno) "+
					" where t3cono=111 and t3divi='100'  and t3rgdt>20180101 AND t3unms='' and (t3cono||t3divi||t3yea4||t3ivno) in (select (t3cono||t3divi||t3yea4||t3ivno) from m3fdbprd.kk)";
		db2.update(sql);
	}
	
	public void updateSecondTaxTable(){
		String sql="update cinfdbprd.zctaxl z set z.t3ridx=3, z.t3csno=(select t3csno from m3fdbprd.kd k where k.t3cono=z.t3cono and k.t3divi=z.t3divi and k.t3yea4=z.t3yea4 and k.t3ivno=z.t3ivno) "+
					" where t3cono=111 and t3divi='100'  and t3rgdt>20180101 AND T3CSNO='' and (t3cono||t3divi||t3yea4||t3ivno) in (select (t3cono||t3divi||t3yea4||t3ivno) from m3fdbprd.kk)";
		db2.update(sql);
	}
}
