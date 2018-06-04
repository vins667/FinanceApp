package shahi.Action.ReportFolder.EPM.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.ReportFolder.EPM.beans.DataTexPurchaseBean;
import shahi.Action.ReportFolder.EPM.beans.DataTexSearchBean;
import shahi.Action.ReportFolder.EPM.mapper.JdbcTemplateFactory;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class DataTexVoucherUnpostDao {

	
	@Autowired
	private JdbcTemplateFactory  jdbcTemplateFactory;
   
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	public List<DataTexPurchaseBean>loadAllInvoices(DataTexSearchBean search) throws SQLException{
		String sql="select COMPANYCODE,ACCOUNTINGDATE,DIVISIONCODE,CUSTOMERSUPPLIERCODE,INVOICEDATE,INVAMOUTINR INVAMT ,GLAMOUNT GLAMT,CURRENCYCODE,QUANTITY QTY,NARRATION,CHARCOL1 INVOICENO,GLCODE from movexposting where eventcode=?"
				+ " and INVOICEDATE=? and trim(CUSTOMERSUPPLIERCODE)=? and DIVISIONCODE=? and nvl(trim(CHARCOL1),trim(invoiceno))=? and flag=2";
		return getJdbcTemplate(search.getDivision().trim()).query(sql, new Object[]{search.getEventCode().trim(),DateUtil.getSFLDateFormat(search.getInvoiceDate().trim()),search.getSupplierCode().trim(),search.getDivision().trim(),generateInvoiceNo(search.getInvoiceNo().trim(),search.getDivision())},new BeanPropertyRowMapper(DataTexPurchaseBean.class));
	}

	public List<DataTexPurchaseBean>loadUnpostedInvoice(DataTexSearchBean search) throws SQLException {
		String sql="select COMPANYCODE,ACCOUNTINGDATE,DIVISIONCODE,CUSTOMERSUPPLIERCODE,INVOICEDATE,INVAMOUTINR INVAMT ,GLAMOUNT GLAMT,CURRENCYCODE,QUANTITY QTY,NARRATION,CHARCOL1 INVOICENO,GLCODEfrom movexposting where eventcode=?"
				+ " and INVOICEDATE=? and trim(CUSTOMERSUPPLIERCODE)=? and DIVISIONCODE=? and nvl(trim(CHARCOL1),trim(invoiceno))=? and flag=1";
		return getJdbcTemplate(search.getDivision().trim()).query(sql, new Object[]{search.getEventCode().trim(),DateUtil.getSFLDateFormat(search.getInvoiceDate().trim()),search.getSupplierCode().trim(),search.getDivision().trim(),generateInvoiceNo(search.getInvoiceNo().trim(),search.getDivision())},new BeanPropertyRowMapper(DataTexPurchaseBean.class));
	}

	public boolean isPurchaseVoucherPosted(DataTexSearchBean search ) {

		String sql="select count(*) from m3fdbprd.fpledg where epcono=111 and epdivi=?  and epsuno=? and epsino=? and epivdt=?";
		return jdbcTemplate.queryForInt(sql,new Object[]{getMovexDivision(search.getDivision().trim()),search.getSupplierCode().trim(),search.getInvoiceNo(),DateUtil.convertToDB2DateFormat(search.getInvoiceDate())})>0?true:false;

	}

	public boolean isSaleVoucherPosted(DataTexSearchBean search ) {

		String sql="select count(*) from m3fdbprd.fsledg where escono=111 and esdivi=?  and escuno=? and escino=? and esivdt=?";

		return jdbcTemplate.queryForInt(sql,new Object[]{getMovexDivision(search.getDivision().trim()),search.getSupplierCode().trim(),search.getInvoiceNo(),DateUtil.convertToDB2DateFormat(search.getInvoiceDate())})>0?true:false;

	}

	public boolean unPostVoucher(DataTexSearchBean search) {
		if(search.getEventCode().equals("4")){
			
		}
		String sql="update movexposting set flag=1 where eventcode=? and INVOICEDATE=? and trim(CUSTOMERSUPPLIERCODE)=? and DIVISIONCODE=? and nvl(trim(CHARCOL1),trim(invoiceno))=? and flag=2";
		int result= getJdbcTemplate(search.getDivision().trim()).update(sql, new Object[]{search.getEventCode().trim(),DateUtil.getSFLDateFormat(search.getInvoiceDate().trim()),search.getSupplierCode().trim(),search.getDivision().trim(),generateInvoiceNo(search.getInvoiceNo().trim(),search.getDivision())});
		
		if(result>0){
			return true;
		}else{
			return false;
		}

	}
	private String getMovexDivision(String division){
		if(division.equals("121")){
			return "550";
		}else if(division.equals("101")){
			return "500";
		}
		return null;
	}
	
	private JdbcTemplate getJdbcTemplate(String division){
		return jdbcTemplateFactory.getDatatexTemplate(division);
	}
	
	private String generateInvoiceNo(String invoiceNo,String division){
		if(division.equals("101")){
			return "SAL"+invoiceNo;
		}
		return invoiceNo;
	}
}
