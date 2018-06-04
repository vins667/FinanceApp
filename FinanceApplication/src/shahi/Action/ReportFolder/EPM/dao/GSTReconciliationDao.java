package shahi.Action.ReportFolder.EPM.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import shahi.Action.ReportFolder.EPM.beans.Code;
import shahi.Action.ReportFolder.EPM.beans.GSTReconciliation;
import shahi.Action.ReportFolder.EPM.mapper.GSTReconciliationMapper;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class GSTReconciliationDao {

	@Autowired
	private SimpleJdbcInsert excelUpload;

	@Autowired
	private SimpleJdbcInsert shahiGSTN;

	@Autowired
	private JdbcTemplate scanTemplate ;

	@Autowired
	private JdbcTemplate shahiWeb;

	@Autowired
	private SimpleJdbcInsert gstReco;

	public boolean save(List<GSTReconciliation> list)throws DataAccessException{
		if(shahiGSTN.getTableName()==null)
			shahiGSTN.withSchemaName("MOVEX").withTableName("FA_GST_RECO");

		deleteExistInvoices(list);
		int result[]=GSTReconciliationMapper.insert(list, shahiGSTN);
		return result.length>0?true:false;
	}

	private void deleteExistInvoices(List<GSTReconciliation> list){
		Iterator<GSTReconciliation>itr=list.iterator();
		while(itr.hasNext()){
			GSTReconciliation bean=(GSTReconciliation)itr.next();
			if(isInvoiceExist(bean)){
				itr.remove();
			}
		}
	}
	private boolean isInvoiceExist(GSTReconciliation bean){
		String sql="select count(*) from movex.FA_GST_RECO where SUPLGSTN=? and INVOICENO=? and INVOICEDT=?";
		int result=scanTemplate.queryForInt(sql,new Object[]{bean.getSUPLGSTN().trim(),bean.getINVOICENO(),DateUtil.converToDDMMYY(bean.getINVOICEDT())});
		if(result>0){
			return true;
		}
		return false;
	}
	public boolean save(List<GSTReconciliation> list,String shahiData) throws DataAccessException{
		doEmptyTempTable();
		if(excelUpload.getTableName()==null)
			excelUpload.withSchemaName("MOVEX").withTableName("FA_GST_SHAHI_RECO");
		int result[]=GSTReconciliationMapper.insert(list, excelUpload);
		return result.length>0?true:false;
	}

	public List<GSTReconciliation> loadVendorGSTNInvoices(String gstn,String userId,String divi,String fromDate,String toDate){

		String sql="select max(a.COMPANY)COMPANY ,max(a.DIVISION)DIVISION ,max(a.year) year ,max(a.SUPLGSTN) SUPLGSTN,max(a.INVVALUE) INVVALUE,"+
					" max(a.SHAHIGSTN) SHAHIGSTN,max(a.CNTPTYST) CNTPTYST,max(a.invoicedt) invoicedt,max(a.line) line,max(a.lineitemamount) LINEITEMAMOUNT,"+
					" max(a.INVOICENO) INVOICENO,max(a.invvalue)invvalue,max(a.invtype) invtype,max(a.pos) pos,max(a.revchg) revchg,max(a.chid) chid,"+
					" sum(a.LINEITEMAMOUNT) Line_Item_TOTAL,sum(a.TAXVALUE) TAXVALUE from movex.FA_GST_RECO a"+
					" where RECODT is null  and  SHAHIGSTN=?"+
					" group by SUPLGSTN, INVOICENO,INVOICEDT  ";

		List<GSTReconciliation>supplierGSTNList=scanTemplate.query(sql, new Object[]{gstn},new RowMapper<GSTReconciliation>(){

			@Override
			public GSTReconciliation mapRow(ResultSet rs, int arg1) throws SQLException {
				GSTReconciliation bean=new GSTReconciliation();
				bean.setCOMPANY(Integer.parseInt(rs.getString("COMPANY")));
				bean.setYEAR(Integer.parseInt(rs.getString("YEAR")));
				bean.setDIVISION(rs.getString("DIVISION"));
				bean.setCUSTNAME(rs.getString("SHAHIGSTN"));
				bean.setINVOICENO(rs.getString("INVOICENO"));
				bean.setINVOICEDT(rs.getString("INVOICEDT"));
				bean.setTAXVALUE(rs.getString("TAXVALUE"));
				bean.setLINE(rs.getString("LINE"));
				bean.setINVTYPE(rs.getString("INVTYPE"));
/*				bean.setIGST(rs.getString("IGST"));
				bean.setCGST(rs.getString("CGST"));
				bean.setSGST(rs.getString("SGST"));
*/				bean.setSHAHIGSTN(rs.getString("SHAHIGSTN"));
				bean.setSUPLGSTN(rs.getString("SUPLGSTN"));
				bean.setINVVALUE(rs.getString("INVVALUE"));
				bean.setPOS(rs.getString("POS"));
				bean.setCHID(userId);
				bean.setTDATE(DateUtil.getCurrentDateInString());
				//bean.setRecoDate(DateUtil.convertFromYYMMDD(toDate));
				return bean;
			}
		});

		return supplierGSTNList;
	}
	public List<GSTReconciliation> loadAllGSTNInvoices(String gstn,String userId,String divi,String fromDate,String toDate)throws RuntimeException {
		String sql="select M4GEOC  from seplweb.M4_WHLO_MASTER where gstn=? and m4divi=?  and  m4cono=111 ";
		List<GSTReconciliation>shahiGSTNList=null;
		List<String> codeList= shahiWeb.query(sql,new Object[]{gstn,divi},new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return  rs.getString(1);
			}
		});
		
		if(codeList!=null && codeList.size()>0){
			shahiGSTNList=load(codeList.get(0),userId,divi,fromDate,toDate,gstn);
		}else{
			throw new RuntimeException("No GeoCode Found for given GSTN "+gstn);
		}

		if(save(shahiGSTNList,"shahi")){
			return  shahiGSTNList;
		}else{
			throw new RuntimeException("Couldn't not save GSTN Data");
		}
	}
	private List<GSTReconciliation> load(String geoCode,String userId,String divi,String fromDate, String toDate,String gstn)throws DataAccessException{
		
		String sql=" select s.t4cono,s.t4divi,s.t4inyr,s.t4ivdt,s.t4suno,s.t4sino,t4geof,ARXLCN, sum(t4txba) t4txba,sum(t4ttxa) t4ttxa,sum(t4txa1) cgst,sum(t4txa2) sgst,sum(t4txa3) igst,p.epacdt,t4geot"+
					" from prodbi.staxln s,prodbi.zstaxl z,"+
					" (select distinct epcono,epdivi,epyea4,epvser,epvono,epsuno,epsino,epacdt from prodbi.fpledg where eptrcd='40' and eppyst<>4) p,"+
					" (SELECT SACONO,SASUNO,SAGEOC,SAECAR,max(ARXLCN) ARXLCN FROM PRODBI.CIDADR, PRODBI.XINADR "+
					" where SACONO=ARCONO AND SASUNO=ARSUNO AND SAADTE=ARADTE AND SAADID=ARADID AND SASTDT=ARSTDT AND "+
					" SACONO='111' GROUP BY SACONO,SASUNO,SAGEOC,SAECAR) G "+
					" where s.t4cono=z.t4cono and s.t4divi=z.t4divi and s.t4inyr=z.t4inyr and s.t4suno=z.t4suno and s.t4sino=z.t4sino and s.t4ivsq=z.t4ivsq "+
					" and z.t4stat='01' "+
					" and s.t4cono=p.epcono and s.t4divi=p.epdivi and s.t4inyr=p.epyea4 and s.t4suno=p.epsuno and s.t4sino=p.epsino "+
					" and  s.T4CONO=G.SACONO(+) AND s.T4SUNO=G.SASUNO(+) AND s.T4GEOF=G.SAGEOC(+)  "+
					" and s.t4cono=111 and s.t4divi=? and s.t4inyr>=2017 and s.t4taxt='9' and t4geot=? and EPACDT between ? and ? "+
					" group by s.t4cono,s.t4divi,s.t4inyr,s.t4ivdt,s.t4suno,s.t4sino,t4geof,ARXLCN,p.epacdt,t4geot "+
					" order by s.t4cono,s.t4divi,s.t4inyr,s.t4suno,s.t4sino" ;

					List<GSTReconciliation> list=scanTemplate.query(sql, new Object[]{divi,geoCode,fromDate,toDate}, new RowMapper<GSTReconciliation>(){

			@Override
			public GSTReconciliation mapRow(ResultSet rs, int arg1) throws SQLException {
				GSTReconciliation bean=new GSTReconciliation();
				bean.setCOMPANY(Integer.parseInt(rs.getString("T4CONO")));
				bean.setYEAR(Integer.parseInt(rs.getString("T4INYR")));
				bean.setDIVISION(rs.getString("T4DIVI"));
				bean.setINVOICENO(rs.getString("T4SINO"));
				bean.setINVOICEDT(DateUtil.convertFromYYYYMMDD((rs.getString("T4IVDT"))));
				bean.setIGST(rs.getString("IGST"));
				bean.setCGST(rs.getString("CGST"));
				bean.setSGST(rs.getString("SGST"));
				bean.setSHAHIGSTN(gstn);
				bean.setTAXVALUE(rs.getString("t4ttxa"));
				bean.setSUPLGSTN(rs.getString("ARXLCN"));
				bean.setINVVALUE(rs.getString("T4TXBA"));
				bean.setLineItemAmount(rs.getString("T4TXBA"));
				bean.setCHID(userId);
				bean.setTDATE(DateUtil.getCurrentDateInString());
			//	bean.setRecoDate(DateUtil.convertFromYYMMDD(toDate));
				return bean;
			}

		});
		return list;
	}
	public List<Code>getAllCompanies(){
		String sql="select ccdivi,ccconm,cccono from prodbi.cmndiv where cccono=111 order by ccdivi";

		List<Code> codeList= scanTemplate.query(sql, new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2)+"-"+rs.getString(1));
			}
		});
		codeList.add(0, new Code("0","Select Company"));
		return codeList;
	}
	public List<Code>getShahiGSTNByDivision(String division){
		String sql="select m4divi,gstn from seplweb.M4_WHLO_MASTER where m4divi=? and m4cono=111 and gstn is not null";
		List<Code> codeList= shahiWeb.query(sql,new Object[]{division},new RowMapper<Code>(){
			@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(2));
			}
		});

		return codeList;
	}

	public List<Code>getAllVendorsGSTN(){
		String sql="select distinct SUPLGSTN  from  movex.FA_GST_RECO ";
		return scanTemplate.query(sql, new RowMapper<Code>(){
		@Override
			public Code mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Code(rs.getString(1),rs.getString(1));
			}
			
		});
	}
	private String getAnnexure(String annexure){
		switch(annexure){
			case "1": return "1";
			case "2": return "2";
			default: return "'1','2'";
		}
	}
	private void doEmptyTempTable(){
		String sql="delete from movex.FA_GST_SHAHI_RECO_TEMP";
		scanTemplate.update(sql);
	}
	public List<GSTReconciliation> getReconcilliationData(String vendorGSTN,String annexure,String fromDate,String toDate){
		String sql=null;
		if(vendorGSTN!=null && annexure!=null ){
            sql="select * from movex.FA_GST_SHAHI_RECO_TEMP where suplgstn=? and ANX_TYPE in ("+getAnnexure(annexure)+") and DOC_TYPE in ('S','C') and invoicedt between ? and ?";
		}else if(vendorGSTN!=null){
			sql="select * from movex.FA_GST_SHAHI_RECO_TEMP where suplgstn=? and invoicedt between ? and ?";
		}else if(annexure!=null){
			sql="select * from movex.FA_GST_SHAHI_RECO_TEMP where ANX_TYPE in ("+getAnnexure(annexure)+") and DOC_TYPE in ('S','C') and invoicedt between ? and ?";
		}
		return scanTemplate.query(sql, new Object[]{vendorGSTN,DateUtil.converToDDMMMYYYY(fromDate),DateUtil.converToDDMMMYYYY(toDate)},new RowMapper<GSTReconciliation>(){

			@Override
			public GSTReconciliation mapRow(ResultSet rs, int arg1) throws SQLException {
				GSTReconciliation bean=new GSTReconciliation();
				bean.setCOMPANY(Integer.parseInt(rs.getString("COMPANY")));
				bean.setYEAR(Integer.parseInt(rs.getString("YEAR")));
				bean.setDIVISION(rs.getString("DIVISION"));
				bean.setCUSTNAME(rs.getString("SHAHIGSTN"));
				bean.setINVOICENO(rs.getString("INVOICENO"));
				bean.setINVOICEDT(DateUtil.covertToDDMMMYYYY(rs.getString("INVOICEDT")));
				bean.setTAXVALUE(rs.getString("TAXVALUE"));
				bean.setLINE(rs.getString("LINE"));
				bean.setINVTYPE(rs.getString("INVTYPE"));
				bean.setSHAHIGSTN(rs.getString("SHAHIGSTN"));
				bean.setSUPLGSTN(rs.getString("SUPLGSTN"));
				bean.setINVVALUE(rs.getString("INVVALUE"));
				bean.setPOS(rs.getString("POS"));
				bean.setANX_TYPE(rs.getString("ANX_TYPE"));
				bean.setDOC_TYPE(rs.getString("DOC_TYPE"));
				bean.setCHID(rs.getString("CHID"));
				bean.setRecoDate(rs.getDate("RECODT"));
				return bean;
			}
			
		});
	}
	/*public int insertIntoTemp(GSTReconciliation bean)throws RuntimeException{
		String sql="insert into movex.FA_GST_SHAHI_RECO_TEMP values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		return scanTemplate.update(sql,new Object[]{
				bean.getCOMPANY(),bean.getDIVISION(),bean.getYEAR(),bean.getSHAHIGSTN(),bean.getSUPLGSTN(),bean.getCNTPTYST(),bean.getCUSTNAME(),
				bean.getCRNOTE(),bean.getCRDATE(),bean.getINVOICENO(),DateUtil.converToDDMMYYYY(bean.getINVOICEDT()),bean.getLINE(),bean.getTAXVALUE(),bean.getTAXRATE(),bean.getIGST(),
				bean.getCGST(),bean.getSGST(),bean.getCESS(),bean.getINVVALUE(),bean.getINVTYPE(),bean.getPOS(),bean.getREVCHG(),bean.getREASON(),
				bean.getCHID(),DateUtil.getCurrentDateInString(),DateUtil.getCurrentDateInString(),bean.getANX_TYPE(),bean.getDOC_TYPE()
		});
	}*/

	public int insertIntoTemp(GSTReconciliation bean){
		if(gstReco.getTableName()==null)
			gstReco.withSchemaName("MOVEX").withTableName("FA_GST_SHAHI_RECO_TEMP");

		return GSTReconciliationMapper.save(bean, gstReco);
	}
	
	public boolean updateShahiInvoice(GSTReconciliation shahiInvoice,Date recoDate){
		String sql="update movex.FA_GST_SHAHI_RECO set RECODT=? where SUPLGSTN=? and invoiceno=? and invoicedt=? ";
		int result= scanTemplate.update(sql,new Object[]{
				recoDate,shahiInvoice.getSUPLGSTN(),shahiInvoice.getINVOICENO(),shahiInvoice.getINVOICEDT()
		});
		if(result>0){
			remove(shahiInvoice);
			return true;
		}
		return false;
		
	}
	public boolean updateManualShahiInvoice(GSTReconciliation shahiInvoice,Date recoDate){
		String sql="update movex.FA_GST_SHAHI_RECO set RECODT=?,man_date=? where SUPLGSTN=? and invoiceno=? and invoicedt=? ";
		int result= scanTemplate.update(sql,new Object[]{
				recoDate,DateUtil.getCurrentDateInDDMMMYY(),shahiInvoice.getSUPLGSTN(),shahiInvoice.getINVOICENO(),shahiInvoice.getINVOICEDT()
		});
		if(result>0){
			remove(shahiInvoice);
			return true;
		}
		return false;
		
	}
	public boolean updateManualVendorInvoice(GSTReconciliation vendorInvoice,Date recoDate){
		String sql="update movex.FA_GST_RECO set RECODT=?,man_date=? where SUPLGSTN=? and invoiceno=? and invoicedt=? ";
		int result=scanTemplate.update(sql,new Object[]{
				recoDate,DateUtil.getCurrentDateInDDMMMYY(),vendorInvoice.getSUPLGSTN(),vendorInvoice.getINVOICENO(),vendorInvoice.getINVOICEDT()
		});
		if(result>0){
			remove(vendorInvoice);
			return true;
		}
		return false;
	}
	public boolean updateVendorInvoice(GSTReconciliation vendorInvoice,Date recoDate){
		String sql="update movex.FA_GST_RECO set RECODT=? where SUPLGSTN=? and invoiceno=? and invoicedt=? ";
		int result=scanTemplate.update(sql,new Object[]{
				recoDate,vendorInvoice.getSUPLGSTN(),vendorInvoice.getINVOICENO(),vendorInvoice.getINVOICEDT()
		});
		if(result>0){
			remove(vendorInvoice);
			return true;
		}
		return false;
	}
	
	public void remove(GSTReconciliation invoice){
		String sql="delete from movex.FA_GST_SHAHI_RECO_TEMP where SUPLGSTN=? and invoiceno=? and invoicedt=?";
		scanTemplate.update(sql,invoice.getSUPLGSTN(),invoice.getINVOICENO(),invoice.getINVOICEDT());
	}
	public List<GSTReconciliation>loadUnReconciliedInvoices(String division,String shahigstn,String fromDate,String toDate){
		String sql="select company,division,year,shahigstn,anx_type,doc_type,suplgstn,invoiceno,invoicedt,lineitemamount,invvalue,taxvalue,igst,cgst,sgst from movex.FA_GST_SHAHI_RECO_TEMP where company=111 and division=? "+ 
					" and shahigstn=? and invoicedt between ? and ? "+
					" and recodt is null "+
					" order by company,division,year,shahigstn,anx_type,doc_type,invoicedt";
		
		return scanTemplate.query(sql, new Object[]{
				division,shahigstn,DateUtil.converToDDMMMYYYY(fromDate),DateUtil.converToDDMMMYYYY(toDate)},new RowMapper<GSTReconciliation>(){

					@Override
					public GSTReconciliation mapRow(ResultSet rs, int arg1) throws SQLException {
						GSTReconciliation bean=new GSTReconciliation();
						bean.setCOMPANY(Integer.parseInt(rs.getString("COMPANY")));
						bean.setYEAR(Integer.parseInt(rs.getString("YEAR")));
						bean.setDIVISION(rs.getString("DIVISION"));
						bean.setSHAHIGSTN(rs.getString("SHAHIGSTN"));
						bean.setINVOICENO(rs.getString("INVOICENO"));
						bean.setINVOICEDT(DateUtil.covertToDDMMMYYYY(rs.getString("INVOICEDT")));
						bean.setTAXVALUE(rs.getString("TAXVALUE"));
						bean.setSUPLGSTN(rs.getString("SUPLGSTN"));
						bean.setINVVALUE(rs.getString("INVVALUE"));
						bean.setANX_TYPE(rs.getString("ANX_TYPE"));
						bean.setDOC_TYPE(rs.getString("DOC_TYPE"));
						return bean;
					}
		});
	}
}
