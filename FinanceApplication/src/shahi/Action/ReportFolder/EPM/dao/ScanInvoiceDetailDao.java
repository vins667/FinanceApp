package shahi.Action.ReportFolder.EPM.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import shahi.Action.ReportFolder.EPM.beans.RequestBean;
import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceDetail;
import shahi.Action.ReportFolder.EPM.beans.ScanInvoiceTempDetail;
import shahi.Action.ReportFolder.EPM.beans.Supplier;
import shahi.Action.ReportFolder.EPM.mapper.InvoiceMapper;

public class ScanInvoiceDetailDao {

	@Autowired
	@Qualifier("scan")
	private JdbcTemplate scan;
	@Autowired
	@Qualifier("db2")
	private JdbcTemplate db2;
	@Autowired
	@Qualifier("namedTemplate")
	private NamedParameterJdbcTemplate  namedTemplate;
	
	public ScanInvoiceDetailDao(){
		/*simpleJdbcInsert=ScanInvoiceConnection.getSimpleJdbcInsert().withSchemaName("cinfdbtst").withTableName("supinv").usingColumns("APCONO","APDIVI","APYEA4","APSPYN","APSUNO","APINYR","APSINO","APPYST","APIVDT","APCUAM","APCUCD","APWHLO","APPONP",
				"APAIT1","APAIT2","APAIT3","APAIT4","APAIT5","APAIT6","APAIT7","APLMDT","APRGDT","APRGTM","APCHID","APCHNO","aptx40","apxtds",
				"apxnta","aplrnm","APATDS","APARAT","APUSID","APFRGR","APLCFG","APSCDT","APACFG","apbtno");*/
		         //namedTemplate=ScanInvoiceConnection.getSimpleJdbcInsert();
	}
	public List<ScanInvoiceDetail>loadAllScanInvoices(){
		String sql="select * from seplscan.scan_inv_detail  where  upd_flag is null and inv_amount<>0 and length(inv_no)<25  and user_id not in ('TESTUSER','Sudeep_PO_HR','Sudeep_NPO_HR') ";
		return scan.query(sql, new RowMapper<ScanInvoiceDetail>(){
			@Override
			public ScanInvoiceDetail mapRow(ResultSet rs, int arg1) throws SQLException {
				return new ScanInvoiceDetail(rs.getString("INV_NO").trim(),rs.getDate("INV_DATE"),rs.getString("VENDER_CODE"),Double.valueOf(rs.getString("INV_AMOUNT")),
						rs.getString("BOA"),rs.getString("LOCATION_CODE"),rs.getString("VENDOR_GSTN"),rs.getString("SHAHI_GSTN"),rs.getString("AUTHORIZER"),
						rs.getString("USER_TYPE"),rs.getString("USER_ID"),rs.getDate("TDATE"),rs.getString("DIVI"),rs.getString("FIN_YEAR"),rs.getString("UPD_FLAG"),
						rs.getString("PO_NO"));
			}

		});
	}

	public boolean insertIntoScanInvTemp(final List<ScanInvoiceDetail> scanInvoiceDetailList) throws SQLException{
		String sql="insert into seplscan.scan_inv_temp values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		final SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
		try{
				return scan.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ScanInvoiceDetail record = scanInvoiceDetailList.get(i);
					if(record!=null){
						//System.out.println(record);
						ps.setString(1,record.getInvoiceNo().toUpperCase().trim());
						ps.setString(2, sdf.format(record.getInvoiceDate()));
						ps.setString(3, record.getVendorCode());
						ps.setDouble(4, record.getInvoiceAmount());
						ps.setString(5, record.getVoucherType());
						ps.setString(6, record.getLocationCode());
						ps.setString(7, record.getVendorGSTN());
						ps.setString(8, record.getShahiGSTN());
						ps.setString(9, record.getAuthorizer());
						ps.setString(10, record.getUserType());
						ps.setString(11, record.getUserId());
						if(record.gettDate()==null){
							ps.setString(12, sdf.format(new Date()));
						}else{
							ps.setString(12, sdf.format(record.gettDate()));
						}
						ps.setString(13, record.getDivision());
						ps.setString(14, record.getFinancialYear());
						ps.setString(15, record.getUpdFlag());
						
						ps.setLong(16, record.getPoNo()!=null?Long.valueOf(record.getPoNo().trim()):0);
						
					}

				}

				@Override
				public int getBatchSize() {
					return scanInvoiceDetailList.size();
				}

			}).length==scanInvoiceDetailList.size() ?true:false;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error in creating record, rolling back");
			return false;
		}
	}
	public List<ScanInvoiceTempDetail>loadAllTempInvoiceDetails(){
		String sql="select * from seplscan.scan_inv_temp  where  upd_flag is null and inv_amount<>0 and length(inv_no)<25  and user_id not in ('TESTUSER','Sudeep_PO_HR','Sudeep_NPO_HR') ";
		return scan.query(sql, new RowMapper<ScanInvoiceTempDetail>(){
			@Override
			public ScanInvoiceTempDetail mapRow(ResultSet rs, int arg1) throws SQLException {
				return new ScanInvoiceTempDetail(rs.getString("INV_NO"),rs.getDate("INV_DATE"),rs.getString("VENDER_CODE"),Double.valueOf(rs.getString("INV_AMOUNT")),
						rs.getString("BOA"),rs.getString("LOCATION_CODE"),rs.getString("VENDOR_GSTN"),rs.getString("SHAHI_GSTN"),rs.getString("AUTHORIZER"),
						rs.getString("USER_TYPE"),rs.getString("USER_ID"),rs.getDate("TDATE"),rs.getString("DIVI"),rs.getString("FIN_YEAR"),rs.getString("UPD_FLAG"),
						rs.getLong("PO_NO"));
			}

		});
	}

	public RequestBean getConditionalBean(ScanInvoiceTempDetail tempDetail){
		RequestBean bean=new RequestBean();
		Supplier supplier=getSupplier(tempDetail);
		bean.setTdsCode(getTdsCode(tempDetail));
		if (supplier != null && (supplier.equals("TPT") || supplier.equals("TPF"))) {
			bean.setFreight("1");
		} else {
			bean.setFreight("0");
		}

		bean.setSupplier(supplier);
		bean.setLcFG(getLCFGCount(tempDetail)>0?1:0);
		if(tempDetail.getUserType().equals("PO")){
			bean.setAccountingFlag(0);
			bean.setPoNonFlag(1);
			bean.setPoCurrency(getCurrency(tempDetail));
		}else{
			bean.setAccountingFlag(1);
			bean.setPoNonFlag(0);
		}
		Integer result=checkDuplicateInvoice(tempDetail);
		if(supplier==null){
			updateFlag(tempDetail,"M");
			bean.setUpdateFlag("M");
		}
		else if(result==0 && supplier!=null){
			updateFlag(tempDetail,"Y");
			bean.setUpdateFlag("Y");
		}else{
			updateFlag(tempDetail,"D");
			bean.setUpdateFlag("D");
		}	  
       
		return bean;
	}
	public Supplier getSupplier(ScanInvoiceTempDetail tempDetail){
		String sql="select distinct VENDOR_CODE,IICUCD,substr(VENDOR_CODE,1,3) vendorType  from seplscan.scan_vendor_master where VENDOR_CODE=?";
		List<Supplier>supplierList=	scan.query(sql,new Object[]{tempDetail.getVendorCode()},new RowMapper<Supplier>(){
			@Override
			public Supplier mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Supplier(rs.getString("VENDOR_CODE"),rs.getString("VENDOR_CODE"),rs.getString("IICUCD"),rs.getString("vendorType"));
			}

		});

		if(supplierList!=null && supplierList.size()>0){
			return supplierList.get(0);
		}
		return null;
	}

	public boolean updateTax(){
		String sql="update m3fdbPRD.ctaxln  set t3vtam=0-t3vtam,t3txa1=0-t3txa1,t3txa2=0-t3txa2,t3txa3=0-t3txa3 where t3cono=111 and t3vtam<0  and (t3cono||t3divi||t3yea4||trim(t3cuno)||trim(t3exin)) in"+
					"(select escono||esdivi||esyea4||trim(escuno)||trim(escino) from m3fdbprd.fsledg where escono=111 and esyea4>=2017 and substr(esvser,3,1)='2' )";
		int result=db2.update(sql);
		if(result>0){
			return true;
		}
		return false;
	}

	public String getTdsCode(ScanInvoiceTempDetail temp){
		String sql="select iisers  from m3fdbprd.cidven where iicono=111 and iisuno=?";

		List<String>result=db2.query(sql,new Object[]{temp.getVendorCode()}, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return  rs.getString(1);
			}

		});

		if(result!=null && !result.isEmpty()){
			return result.get(0);
		}
		return null;
	}


	public Integer getLCFGCount(ScanInvoiceTempDetail temp){
		String sql="select count(*)  lcfg from m3fdbprd.cloord where orcono=111 and ordivi=? and orridn=?";
		return db2.queryForInt(sql, new Object[]{temp.getDivision(),temp.getPoNo()});
	}

	public Integer checkDuplicateInvoice(ScanInvoiceTempDetail temp){
		String sql="select count(*)  cnt from cinfdbprd.supinv where apcono=111 and apdivi=? and APSUNO=? and APSINO=? and apyea4=?";
		return db2.queryForInt(sql,new Object[]{temp.getDivision(),temp.getVendorCode(),temp.getInvoiceNo(),temp.getFinancialYear()});

	}
    public String getCurrency(ScanInvoiceTempDetail temp){
    	String sql="select max(vendor_cucd) from  seplscan.SCAN_PO_MASTER where po_no=?";
     	List<String>result=scan.query(sql,new Object[]{temp.getPoNo()}, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return  rs.getString(1);
			}

		});

		if(result!=null && !result.isEmpty()){
			return result.get(0);
		}
		
		return null;
    	
    }
	public void updateFlag(ScanInvoiceTempDetail temp,String updateFlag){
		String sql="update seplscan.scan_inv_temp set UPD_FLAG=? where inv_no=? and VENDER_CODE=? and divi=? and FIN_YEAR=? ";
		scan.update(sql, new Object[]{updateFlag,temp.getInvoiceNo(),temp.getVendorCode(),temp.getDivision(),temp.getFinancialYear()});
	}

	public void saveIntoMovex(ScanInvoiceTempDetail temp) throws SQLException {
		RequestBean flags=getConditionalBean(temp);
		int result=0;
		if(flags!=null){
			result=InvoiceMapper.createInvoice(namedTemplate,temp, flags);
			updateScanTableFlag(flags,temp);
			/*if(result>0){
				updateScanTableFlag(flags,temp);
			}*/
		}

	}
	public void updateScanTableFlag(RequestBean flag,ScanInvoiceTempDetail temp){
		String sql="update seplscan.scan_inv_detail  set UPD_FLAG=? where inv_no=? and VENDER_CODE=? and divi=? and FIN_YEAR=?";
		scan.update(sql, new Object[]{flag.getUpdateFlag(),temp.getInvoiceNo(),
						temp.getVendorCode(),temp.getDivision(),temp.getFinancialYear()});
	}	

	public int  emptyTempScanTable(){
		String sql="delete from  seplscan.scan_inv_temp";
		return scan.update(sql);
	}
}
