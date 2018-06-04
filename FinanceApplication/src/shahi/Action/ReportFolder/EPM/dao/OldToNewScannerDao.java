package shahi.Action.ReportFolder.EPM.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import shahi.Action.ReportFolder.oldtonewscanner.OldToNewScanner;
import shahi.Action.ReportFolder.oldtonewscanner.OldToNewScannerMapper;
import shahi.connection.NewScannerConnection;

public class OldToNewScannerDao {

	private JdbcTemplate oldMovex;
	private SimpleJdbcInsert newScannerInsert;
	private String date;
	private int day,month,year;
	public OldToNewScannerDao(){
		oldMovex=NewScannerConnection.getOldMovex();
		newScannerInsert=NewScannerConnection.getNewScannerInsert().withSchemaName("seplscan").withTableName("scan_inv_detail");
		//oracle=GenericConnection.getScanConnection();
	}
	
	public List<OldToNewScanner>loadAllScannerBills(){
		String sql="select INVOICENUMBER,PO,DATE1,TOTAL,WAREHOUSE,STATUS_FLAG,MVXSUNO,TDATE,SEH_USER,DOC_FLAG,CYEAR from mvxcdtshah.shahi_invoice where cyear=2017 and status_flag='Y' and DOC_FLAG is null ";
        return (List<OldToNewScanner>)oldMovex.query(sql, new BeanPropertyRowMapper(OldToNewScanner.class));
	}
	public void insertIntoNewScanner(OldToNewScanner oldToNewScanner){
		//String sql="insert into seplscan.scan_inv_detail values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,'K',?)";
		int result=OldToNewScannerMapper.insert(oldToNewScanner, newScannerInsert);
		//int result=0;
		/*try{
			result=oracle.update(sql,new Object[]{
				oldToNewScanner.getINVOICENUMBER(),oldToNewScanner.getDATE1(),oldToNewScanner.getMVXSUNO(),new BigDecimal(oldToNewScanner.getTOTAL()),
				null,oldToNewScanner.getWAREHOUSE(),null,null,null,null,oldToNewScanner.getSEH_USER(),oldToNewScanner.getTDATE(),null,
				oldToNewScanner.getCYEAR(),Long.valueOf(oldToNewScanner.getPO())
		});
		}catch(UncategorizedSQLException ex){
			
		}*/
		if(result>0){
			updateOldScannerStatus(oldToNewScanner);
		}
	}
	public void  updateDate(OldToNewScanner old){
		date=getDate(old.getDATE1());
		String sql="update mvxcdtshah.shahi_invoice set date1=?,cyear=2017 where INVOICENUMBER=? and PO=? and MVXSUNO=?";
		oldMovex.update(sql,new Object[]{date,old.getINVOICENUMBER(),old.getPO(),old.getMVXSUNO()});
	}
	public void updateOldScannerStatus(OldToNewScanner old){
		String sql="update mvxcdtshah.shahi_invoice set DOC_FLAG='Y' where cyear=2017 and INVOICENUMBER=? and PO=? and MVXSUNO=?";
		oldMovex.update(sql,new Object[]{old.getINVOICENUMBER(),old.getPO(),old.getMVXSUNO()});
	}
	public String getDate(String date){
		String[]dparts=null;
		if(date!=null){
			dparts=date.split("/");
			day=Integer.parseInt(dparts[0]);
			month=Integer.parseInt(dparts[1]);
			year=Integer.parseInt(dparts[2])-1;
			date=day+"/"+month+"/"+year;
		}
		return date;
	}
}
