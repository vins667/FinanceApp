package shahi.Action.ReportFolder.EPM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import shahi.Action.ReportFolder.EPM.beans.Invoice;
import shahi.Action.ReportFolder.EPM.beans.InvoiceDeleteAuth;
import shahi.Action.ReportFolder.EPM.beans.InvoiceSearch;
import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceDetail;
import shahi.Action.ReportFolder.EPM.mapper.ScanInvoiceHistoryMapper;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class InvoiceDeleteDao {

	@Qualifier("scanTemplate")
	@Autowired
	private JdbcTemplate  oracleTemplate;
	@Qualifier("jdbcTemplate")
	@Autowired
	private JdbcTemplate  db2Template;
	@Autowired
	@Qualifier("simpleTemplate")
	private SimpleJdbcInsert simpleTemplate;
	@Autowired
	@Qualifier("shahiTemplate")
	private JdbcTemplate shahiTemplate;
	
	public Invoice getInvoiceByNo(InvoiceSearch bean){
		String sql="select APCONO, APSUNO,APSINO,APPYST,APIVDT,APCUAM,APCUCD,APRGTM,APDIVI,APWHLO,APUSID from cinfdbprd.supinv where APCONO='111' AND  APDIVI=? and APSUNO= ? and upper(APSINO)=? and APIVDT=?";
		List<Invoice> list=db2Template.query(sql,new Object[]{bean.getWarehouse(),bean.getSupplierCode().toUpperCase(),bean.getInvoiceNo().toUpperCase(),DateUtil.convertToDB2DateFormat(bean.getInvoiceDate())},new BeanPropertyRowMapper(Invoice.class));
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

  public int delete(InvoiceSearch delete){
	  String sql="select * from SEPLSCAN.SCAN_INV_DETAIL where inv_no=? and inv_date=? and vender_code=? and divi=?";
	  int result=0;
	  List<ScanInvoiceDetail>list=oracleTemplate.query(sql, new Object[]{delete.getInvoiceNo().trim(),DateUtil.converToDDMMMYYYY(delete.getInvoiceDate()),delete.getSupplierCode().trim(),delete.getDivision().trim()},new RowMapper<ScanInvoiceDetail>(){
			@Override
			public ScanInvoiceDetail mapRow(ResultSet rs, int arg1) throws SQLException {
				return new ScanInvoiceDetail(rs.getString("INV_NO"),rs.getDate("INV_DATE"),rs.getString("VENDER_CODE"),Double.valueOf(rs.getString("INV_AMOUNT")),
						rs.getString("BOA"),rs.getString("LOCATION_CODE"),rs.getString("VENDOR_GSTN"),rs.getString("SHAHI_GSTN"),rs.getString("AUTHORIZER"),
						rs.getString("USER_TYPE"),rs.getString("USER_ID"),rs.getDate("TDATE"),rs.getString("DIVI"),rs.getString("FIN_YEAR"),rs.getString("UPD_FLAG"),
						rs.getString("PO_NO"));
			}

		});
	  if(list!=null && list.size()>0){
		  result=moveToHistory(list.get(0));
		  if(result>0){
			  return deleteFromScanDetail (delete);
		  }
	  }
	  return 0;
  }
	
  
  public int deleteFromScanDetail(InvoiceSearch bean){
	  String sql="delete from SEPLSCAN.SCAN_INV_DETAIL where inv_no=? and inv_date=? and vender_code=? and divi=?";//Removed upd_flag in ('Y','D') on dated 20/03/2018
	  return oracleTemplate.update(sql,new Object[]{bean.getInvoiceNo().trim(),DateUtil.converToDDMMMYYYY(bean.getInvoiceDate()),bean.getSupplierCode().trim(),bean.getDivision()});

  }
  public int deleteFromMovex(InvoiceSearch bean){
	  String sql="delete  from cinfdbprd.supinv where APCONO='111' AND  APDIVI=? and APSUNO= ? and APSINO=? and APIVDT=?";
	 return  db2Template.update(sql,new Object[]{bean.getDivision().trim(),bean.getSupplierCode().trim(),bean.getInvoiceNo().trim(),DateUtil.converToYYYYMMDD(bean.getInvoiceDate())});
  }
  public boolean isUserAuthorised(InvoiceSearch bean,String empCode){
	  String sql="select * from M4_AUTH_DEL where 	EMPCODE=? and DLUSID='ALL'";
	  List<InvoiceDeleteAuth>list=oracleTemplate.query(sql, new Object[]{empCode},new BeanPropertyRowMapper(InvoiceDeleteAuth.class));
	  
	  if(list!=null && list.size()>0){
		  return true;
	    }else{
	    	String sql1="select * from M4_AUTH_DEL where EMPCODE=? and DLUSID=?";
	  	    List<InvoiceDeleteAuth>exist=oracleTemplate.query(sql1, new Object[]{empCode,bean.getUserId().trim()},new BeanPropertyRowMapper(InvoiceDeleteAuth.class));
            if(exist!=null && exist.size()>0){
		    	if(exist.get(0).getWarehouse().contains(bean.getDivision())||exist.get(0).getWarehouse().equals("ALL")){
					  return true;
				  }
            }
	    }
	  
	  return false;	
  }
  
  public int moveToHistory(ScanInvoiceDetail invoiceDetail){
	  if(simpleTemplate.getTableName()==null)
		  simpleTemplate.withSchemaName("seplscan").withTableName("scan_inv_detail_delete_history");
	  return ScanInvoiceHistoryMapper.insert(simpleTemplate, invoiceDetail);

  }
  public int isM3ControlNoGenerated(InvoiceSearch bean){
	  String sql="select count(*) from shahiweb.m3_bill_master where  supplier_code=? and bill_no=? and bill_Date=?";
	  return shahiTemplate.queryForInt(sql,new Object[]{bean.getSupplierCode().trim(),bean.getInvoiceNo().trim(),DateUtil.converToDDMMMYYYY(bean.getInvoiceDate())});
  }
  public int isM4ControlNoGenerated(InvoiceSearch bean){
	  String sql="select count(*) from seplweb.m4_bill_master where  supplier_code=? and bill_no=? and bill_Date=?";
	  return shahiTemplate.queryForInt(sql,new Object[]{bean.getSupplierCode().trim(),bean.getInvoiceNo().trim(),DateUtil.converToDDMMMYYYY(bean.getInvoiceDate())});
  }
}
