package shahi.Action.ReportFolder.invoicecorrection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class InvoiceDao {

	@Autowired
	@Qualifier("db2")
	private  JdbcTemplate prod;
	public InvoiceDao(){
		//this.prod=CorrectInvoiceConnection.getDB2Connection();
	}
	public List<Invoice>loadAllInvoices(){
		String sql="select apcono,apdivi,apsuno,apsino,APIVDT from cinfdbprd.supinv where apyea4=2016 and apscdt>='20180101' and apivdt>=20170101";
		return (List<Invoice>)prod.query(sql,new BeanPropertyRowMapper(Invoice.class));
	}
	
	public Invoice isInvoiceExist(Invoice invoice){
		String sql="select * from  cinfdbprd.supinv where apcono=111 and apyea4=2017 and apcono=? and apdivi=? and apsuno=? and apsino=? ";
		List<Invoice>list=prod.query(sql, new Object[]{invoice.getAPCONO(),invoice.getAPDIVI(),invoice.getAPSUNO(),invoice.getAPSINO()},new BeanPropertyRowMapper(Invoice.class) );
		if(list!=null && list.size()>0 ){
			return list.get(0);
		}
		return null;
	}
	
	public void updateInvoice(Invoice invoice){
		String date="2018"+invoice.getAPIVDT().substring(4);
		String sql="update cinfdbprd.supinv set apyea4=2017,apinyr=2017,apivdt="+date+" where apcono=? and apdivi=? and apsuno=? and apsino=? and apyea4=2016 and apscdt>='20180101' and apivdt>=20170101";
		
		prod.update(sql,new Object[]{invoice.getAPCONO(),invoice.getAPDIVI(),invoice.getAPSUNO(),invoice.getAPSINO()});
	}
}
