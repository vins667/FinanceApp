package shahi.Action.MvxExp.PRE.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.PRE.Beans.PreInvLineBean;
import shahi.connection.GenericConnection;
import shahi.pagination.Page;
import shahi.pagination.PaginationHelper;

public class PreInvioiceNewDao {

	/*private JdbcTemplate orlaceTemplate=GenericConnection.getOracleConnection();

	private JdbcTemplate db2Template=GenericConnection.getDB2Connection();

    private static final int PAGE_SIZE=20;
    
	public List<PreInvLineBean>getAllItemsByPlanNo(String planNo,String userId,int pageNo){
		PaginationHelper<PreInvLineBean> ph = new PaginationHelper<PreInvLineBean>();

		String sql="select CO_NO,CO_LINE,ITEM_NO, SET_PCS,QTY_PCS,ITEM_DESC,FACILITY,BPO, STY,UOM,ITEM_QTY,SALE_PR,NET_PRICE,GR_DECL_PER,GR_DECL_AMT,GR_DECL_PER,PCH,EXP_TYPE,COMM_PER,mmitgr,hsn_code,igst_per,cgst_per,sgst_per from exports.ei_get_coline where plan_no=? and userid=? order by co_no,co_line";
		String rowCount="select count(*) from  exports.ei_get_coline where plan_no=? and userid=?";
		return ph.fetchPage(orlaceTemplate, rowCount, sql, new Object[]{planNo,userId}, pageNo, PAGE_SIZE, new ParameterizedRowMapper<PreInvLineBean>(){

			@Override
			public PreInvLineBean mapRow(ResultSet result, int arg1) throws SQLException {
				return new PreInvLineBean("", result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), 
											result.getString("uom"), result.getDouble("item_qty"),null, null, null, null, result.getBigDecimal("NET_PRICE"), 
											null, result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), 
											null, null, null, result.getString("bpo"), result.getString("sty"), null, null, null, 
											result.getBigDecimal("sale_pr"), result.getString("exp_type"), result.getString("ITEM_DESC"), null, null, 
											null, null, null, result.getString("hsn_code"), result.getDouble("IGST_PER"), result.getDouble("CGST_PER"), 
											result.getDouble("SGST_PER"));
			}

		});
		return orlaceTemplate.query(sql, new Object[]{planNo,userId},  new RowMapper<PreInvLineBean>(){

			@Override
			public PreInvLineBean mapRow(ResultSet result, int arg1) throws SQLException {
				return new PreInvLineBean("", result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), 
											result.getString("uom"), result.getDouble("item_qty"),null, null, null, null, result.getBigDecimal("NET_PRICE"), 
											null, result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), 
											null, null, null, result.getString("bpo"), result.getString("sty"), null, null, null, 
											result.getBigDecimal("sale_pr"), result.getString("exp_type"), result.getString("ITEM_DESC"), null, null, 
											null, null, null, result.getString("hsn_code"), result.getDouble("IGST_PER"), result.getDouble("CGST_PER"), 
											result.getDouble("SGST_PER"));
			}

		});

	}   
	 
	public List<PreInvLineBean>getLineByYearComAndInvoiceNo(String year,String company,String invoiceNo,int pageNo){
		PaginationHelper<PreInvLineBean> ph = new PaginationHelper<PreInvLineBean>();
		String sql="select sr_no,co_no,co_line,item_no,unit,qty_endors,qty_kgs,price_fc-nvl(adjust_fc,0) price_fc,price_misc,hngr_cost,tag_cost,adjust_fc,net_price,made_for,temp_cat,"
                + " qty_endors*(price_fc+nvl(price_misc,0)) fob_fc,gr_decl_amt,GR_DECL_PER,dbk_slno,str_slno,str_misc,pre_print_no,token_no,category,description,rosl_slno,scheme_code,HSCODE1,nvl(hngr_cost,0) hngr_cost,nvl(tag_cost,0) tag_cost,HSN_CODE,IGST_PER,CGST_PER,SGST_PER "
                + " from ei_endors_dtls a  where year=? and company=? and inv_no=?  order by sr_no,co_no,co_line";
		
		String rowCount="select count(*) from  ei_endors_dtls a  where year=? and company=? and inv_no=?";
		
		return ph.fetchPage(orlaceTemplate, rowCount, sql, new Object[]{year,company,invoiceNo}, pageNo, PAGE_SIZE, new ParameterizedRowMapper<PreInvLineBean>(){

			@Override
			public PreInvLineBean mapRow(ResultSet result, int arg1) throws SQLException {
				 return new PreInvLineBean(result.getString("sr_no"), result.getString("co_no"), 
						result.getString("co_line"), result.getString("item_no"), 
						result.getString("unit"), result.getDouble("qty_endors"), 
						result.getDouble("qty_kgs"), result.getBigDecimal("price_fc"), 
						result.getBigDecimal("price_misc"), result.getBigDecimal("adjust_fc"), 
						result.getBigDecimal("net_price"), roundTwoDecimals(result.getDouble("fob_fc")), 
						result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), 
						result.getString("dbk_slno"), result.getString("str_slno"), result.getString("str_misc"), 
						result.getString("pre_print_no"), result.getString("token_no"), 
						result.getString("category"), result.getString("description"), 
						result.getString("made_for"), null, result.getString("temp_cat"), 
						null, result.getString("rosl_slno"), result.getString("scheme_code"), 
						result.getString("HSCODE1"), result.getBigDecimal("hngr_cost"), 
						result.getBigDecimal("tag_cost"), result.getString("HSN_CODE"), 
						result.getDouble("igst_per"), result.getDouble("cgst_per"), 
						result.getDouble("sgst_per"));
			}

		});
		return orlaceTemplate.query( sql, new Object[]{year,company,invoiceNo}, new RowMapper<PreInvLineBean>(){

			@Override
			public PreInvLineBean mapRow(ResultSet result, int arg1) throws SQLException {
				 return new PreInvLineBean(result.getString("sr_no"), result.getString("co_no"), 
						result.getString("co_line"), result.getString("item_no"), 
						result.getString("unit"), result.getDouble("qty_endors"), 
						result.getDouble("qty_kgs"), result.getBigDecimal("price_fc"), 
						result.getBigDecimal("price_misc"), result.getBigDecimal("adjust_fc"), 
						result.getBigDecimal("net_price"), roundTwoDecimals(result.getDouble("fob_fc")), 
						result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), 
						result.getString("dbk_slno"), result.getString("str_slno"), result.getString("str_misc"), 
						result.getString("pre_print_no"), result.getString("token_no"), 
						result.getString("category"), result.getString("description"), 
						result.getString("made_for"), null, result.getString("temp_cat"), 
						null, result.getString("rosl_slno"), result.getString("scheme_code"), 
						result.getString("HSCODE1"), result.getBigDecimal("hngr_cost"), 
						result.getBigDecimal("tag_cost"), result.getString("HSN_CODE"), 
						result.getDouble("igst_per"), result.getDouble("cgst_per"), 
						result.getDouble("sgst_per"));
			}

		});
	}
	public List<PreInvLineBean>getExciseDeatilPerLineItem(String year,String company,String invoiceNo,int pageNo){
		PaginationHelper<PreInvLineBean> ph = new PaginationHelper<PreInvLineBean>();
		String sql="select excise_unit,ex_inv_slno,to_char(ex_inv_date,'dd/mm/yyyy') ex_inv_date,sr_no,a.co_no,a.co_line,a.item_no,unit,qty_endors,qty_kgs,price_fc-nvl(adjust_fc,0) price_fc,nvl(price_misc,0) price_misc,adjust_fc,net_price,made_for,temp_cat,"
				+ " qty_endors*(price_fc+nvl(price_misc,0)) fob_fc,gr_decl_amt,GR_DECL_PER,dbk_slno,str_slno,str_misc,pre_print_no,token_no,category,description,MRP_RATE,rosl_slno,SCHEME_CODE,hscode1,nvl(hngr_cost,0) hngr_cost,nvl(tag_cost,0) tag_cost,hsn_code,igst_per,cgst_per,sgst_per "
				+ " from ei_endors_mast a1,ei_endors_dtls a where a1.year=a.year and a1.company=a.company and a1.inv_no=a.inv_no and a1.year=? and a1.company=? and a1.inv_no=? order by sr_no,a.co_no,a.co_line";
		
		String rowCount="select count(*) from  ei_endors_mast a  where year=? and company=? and inv_no=?";
		
		return ph.fetchPage(orlaceTemplate, rowCount, sql, new Object[]{year,company,invoiceNo}, pageNo, PAGE_SIZE, new ParameterizedRowMapper<PreInvLineBean>(){

			@Override
			public PreInvLineBean mapRow(ResultSet result, int arg1) throws SQLException {
				 return new PreInvLineBean(result.getString("sr_no"), result.getString("co_no"), 
						result.getString("co_line"), result.getString("item_no"), 
						result.getString("unit"), result.getDouble("qty_endors"), 
						result.getDouble("qty_kgs"), result.getBigDecimal("price_fc"), 
						result.getBigDecimal("price_misc"), result.getBigDecimal("adjust_fc"), 
						result.getBigDecimal("net_price"), roundTwoDecimals(result.getDouble("fob_fc")), 
						result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), 
						result.getString("dbk_slno"), result.getString("str_slno"), result.getString("str_misc"), 
						result.getString("pre_print_no"), result.getString("token_no"), 
						result.getString("category"), result.getString("description"), 
						result.getString("made_for"), null, result.getString("temp_cat"), 
						null, result.getString("rosl_slno"), result.getString("scheme_code"), 
						result.getString("HSCODE1"), result.getBigDecimal("hngr_cost"), 
						result.getBigDecimal("tag_cost"), result.getString("HSN_CODE"), 
						result.getDouble("igst_per"), result.getDouble("cgst_per"), 
						result.getDouble("sgst_per"),result.getString("excise_unit"),result.getString("ex_inv_slno"),result.getString("ex_inv_date"));
			}

		});
		return orlaceTemplate.query(sql, new Object[]{year,company,invoiceNo},  new RowMapper<PreInvLineBean>(){

			@Override
			public PreInvLineBean mapRow(ResultSet result, int arg1) throws SQLException {
				 return new PreInvLineBean(result.getString("sr_no"), result.getString("co_no"), 
						result.getString("co_line"), result.getString("item_no"), 
						result.getString("unit"), result.getDouble("qty_endors"), 
						result.getDouble("qty_kgs"), result.getBigDecimal("price_fc"), 
						result.getBigDecimal("price_misc"), result.getBigDecimal("adjust_fc"), 
						result.getBigDecimal("net_price"), roundTwoDecimals(result.getDouble("fob_fc")), 
						result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), 
						result.getString("dbk_slno"), result.getString("str_slno"), result.getString("str_misc"), 
						result.getString("pre_print_no"), result.getString("token_no"), 
						result.getString("category"), result.getString("description"), 
						result.getString("made_for"), null, result.getString("temp_cat"), 
						null, result.getString("rosl_slno"), result.getString("scheme_code"), 
						result.getString("HSCODE1"), result.getBigDecimal("hngr_cost"), 
						result.getBigDecimal("tag_cost"), result.getString("HSN_CODE"), 
						result.getDouble("igst_per"), result.getDouble("cgst_per"), 
						result.getDouble("sgst_per"),result.getString("excise_unit"),result.getString("ex_inv_slno"),result.getString("ex_inv_date"));
			}

		});
	}
	double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
	
	public BigDecimal getLineItemPrice(String coNo,String coLineNo){
		String sql="select obsapr from M3FDBPRD.ooline where obcono=111 and oborno=? and obponr=?";
		return db2Template.queryForObject(sql,new Object[]{coNo,coLineNo}, BigDecimal.class);
	}
	
	public String getItemDescription(String planNo,String coNo,String coLineNo){
		
		String sql="select substr(item_desc,21,50) item_desc from pr_ship_plan_detail where plan_numb=? and co_numb=? and co_line=?";
		List<String>result=orlaceTemplate.query(sql,new Object[]{planNo,coNo,coLineNo}, new RowMapper<String>(){
		@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				//
				return  rs.getString(1);
			}
			
		});
		
		if(result!=null && !result.isEmpty()){
			return result.get(0);
		}
		return null;
	}
	
	public List<GetListBean>getAllSchemeCodes(){
		
		String sql="select scheme_code||'-'||short_desc SCH_desc,scheme_code from  ei_dbk_scheme order by scheme_code";
		return orlaceTemplate.query(sql, new RowMapper<GetListBean>(){

			@Override
			public GetListBean mapRow(ResultSet result, int arg1) throws SQLException {
				
				return new GetListBean(result.getString("scheme_code"), result.getString("SCH_desc"));
			}
			
		});
		
	}
	
	public List<GetListBean>getAllPaymentTerms(){
		String sql=" select type_Desc,type_code from ei_grup_type_dtls where grup_type_code='SHT' order by 1";
		return orlaceTemplate.query(sql, new RowMapper<GetListBean>(){

			@Override
			public GetListBean mapRow(ResultSet result, int arg1) throws SQLException {
				
				return new GetListBean(result.getString("type_code"), result.getString("type_desc"));
			}
			
		});
	}
	public List<GetListBean>getAllModels(){
		String sql="select  cttx15,trim(ctstky) ctstky from csytab  where ctcono=111 and ctstco='MODL'  order by 1";
		return db2Template.query(sql, new RowMapper<GetListBean>(){

			@Override
			public GetListBean mapRow(ResultSet result, int arg1) throws SQLException {
				
				return new GetListBean(result.getString("ctstky"), result.getString("cttx15"));
			}
			
		});
	}
	public List<GetListBean>getAllTeds(){
		String sql="select  cttx15||' - '||ctstky cttx15,trim(ctstky) ctstky from csytab  where ctcono=111 and ctstco='TEDL'  order by 1";
		return db2Template.query(sql, new RowMapper<GetListBean>(){

			@Override
			public GetListBean mapRow(ResultSet result, int arg1) throws SQLException {
				
				return new GetListBean(result.getString("ctstky"), result.getString("cttx15"));
			}
			
		});
	}
	public BigDecimal getMRP(String year,String company,String invoiceNo,String coNo,String coLineNo){
		String sql="select MRP_RATE from ei_endors_DTLS where year=? and company=? and inv_no=? and co_no=? and co_line=? ";
		return orlaceTemplate.queryForObject(sql,new Object[]{year,company,invoiceNo,coNo,coLineNo}, BigDecimal.class);
	}
	
	public List<String>getAllShipmentTypes(){
		List<String>shipmentTypes=new ArrayList<>(5);
		shipmentTypes.add("DBK");
		shipmentTypes.add("DEEC");
		shipmentTypes.add("DBKDEEC");
		shipmentTypes.add("DOMESTIC");
		shipmentTypes.add("SAMPLE");
		return shipmentTypes;
		}*/
}
