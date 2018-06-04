package shahi.Action.FundReq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shahi.Action.FundReq.Query;
import shahi.Action.FundReq.Beans.FaPayrollTypeMastBean;
import shahi.Action.FundReq.Beans.FundReqBean;
import shahi.Action.FundReq.Beans.FundReqQuery;
import shahi.Action.FundReq.Beans.UTRNoAlreadyExist;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class FundReqReversalDao {

	@Autowired
	@Qualifier("scanTemplate")
	private JdbcTemplate scanTemplate;

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate db2;
	
	public List<FaPayrollTypeMastBean> loadRequesTypes(){
		String sql="SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTP' AND FLAG='Y'";
		return scanTemplate.query(sql, new BeanPropertyRowMapper(FaPayrollTypeMastBean.class));
	}
	public List<FaPayrollTypeMastBean>loadPayList(){
		String sql="SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTO' AND FLAG='Y'";
		return scanTemplate.query(sql, new BeanPropertyRowMapper(FaPayrollTypeMastBean.class));
	}

	public List<FundReqBean>getRequestByNo(String reqNo){
		String sql="select REQNO,to_char(REQDT,'dd/mm/yyyy') REQDT,REQSUNO, case when REQTYP='M' THEN 'Movex PO' when REQTYP='G' then 'General PO' when REQTYP='B' then 'BillWise' ELSE 'Employee' END REQTYP,"
				+" REQTXT,DLVPLC,REQSTS,PAYLOCT,RQCHID"
				+" from finacbi.REQMST a"
				+" where REQNO=? order by REQSTS DESC,REQNO DESC";
		return scanTemplate.query(sql,new Object[]{reqNo},new BeanPropertyRowMapper(FundReqBean.class));
		
	}
	public List<FundReqBean>loadAllFundRequests(Query query){
		FundReqQuery fundQuery=(FundReqQuery)query;
		String sql=null;
		if(fundQuery.getPoNo()!=null && fundQuery.getPoNo().isEmpty()){
			sql="select REQNO,to_char(REQDT,'dd/mm/yyyy') REQDT,REQSUNO, case when REQTYP='M' THEN 'Movex PO' when REQTYP='G' then 'General PO' when REQTYP='B' then 'BillWise' ELSE 'Employee' END REQTYP,"
					+" REQTXT,DLVPLC,REQSTS,PAYLOCT,RQCHID"
					+" from finacbi.REQMST a"
					+" where "+getFilterQuery(fundQuery)+" order by REQSTS DESC,REQNO DESC";
	
			return scanTemplate.query(sql,new BeanPropertyRowMapper(FundReqBean.class));
		}else{
			sql="select a.REQNO,to_char(a.REQDT,'dd/mm/yyyy') REQDT,a.REQSUNO,case when a.REQTYP='M' THEN 'Movex PO' when a.REQTYP='G' then 'General PO' when a.REQTYP='B' then 'BillWise' ELSE 'Employee' END REQTYP,"
                        +" a.REQTXT,a.DLVPLC,a.REQSTS,a.PAYLOCT,a.RQCHID"
                        +" from finacbi.REQMST a,finacbi.REQDTL b"
                        +" where a.REQNO=b.REQNO and "+getFilterQuery(fundQuery)+" order by a.REQSTS DESC,a.REQNO DESC";
			return scanTemplate.query(sql,new BeanPropertyRowMapper(FundReqBean.class));
		}
	}

	public boolean validateVoucher(String vtype,String vono,String year){
		String sql="select count(*) from FAPCHK where ckyea4=? and ckvser=? and ckvono=?";
		Integer result=db2.queryForInt(sql,new Object[]{year,vtype,vono});
		if(result>0)
			return true;
		return false;
	}
	public List<FundReqBean>loadAllFundRequestsForUTRUpdation(String fromDate,String toDate ){
		String  sql="select reqno,to_char(reqdt,'dd/mm/yy') reqdt,reqtyp,reqsuno,substr(reqtxt,1,80) reqtxt,reqchq,reqsts,chqdelv,REQCHQDT,REQDIVI from finacbi.reqmst where reqsts=8 and reqdt between ? and ? order by reqno";
		return scanTemplate.query(sql,new Object[]{DateUtil.convertToDDMMMYY(fromDate),DateUtil.convertToDDMMMYY(toDate)},new BeanPropertyRowMapper(FundReqBean.class));
	}	
	
	public void updateUTRNo(FundReqBean update) throws UTRNoAlreadyExist {
		if(isChequeExist(update)){
			throw new RuntimeException ("UTR NO: "+update.getREQCHQ()+" is already exist");
		}
		String sql="update finacbi.reqmst set REQCHQ=?,REQSTS='9',REQCHQDT=?,RQLMDT=?,CHQDELV=? where REQNO=?";
		scanTemplate.update(sql,new Object[]{
				update.getREQCHQ().trim(),DateUtil.convertToDDMMMYY(update.getREQCHQDT()),
				DateUtil.getCurrentDateInDDMMMYY(),update.getVOTYPE()+"/"+update.getREQVONO()+"-"+DateUtil.convertToDDMMMYY(update.getREQCHQDT()),update.getREQNO().trim()
		});
	}
	public boolean isChequeExist(FundReqBean bean){
		String sql="select count(*) from finacbi.reqmst where  REQCHQ=?";
		Integer result=scanTemplate.queryForInt(sql,new Object[]{bean.getREQCHQ().trim()});
		
		if(result>0)
			return true;
		return false;
	}
	public boolean cancelRequest(FundReqBean fundReqBean,String userId){
		updateHeader(fundReqBean,userId);
		String sql="update finacbi.REQdtl set REQPOAMT=0 where REQNO=?";
		return scanTemplate.update(sql,new Object[]{fundReqBean.getREQNO().trim()})>0?true:false;
	}
	
	public void updateHeader(FundReqBean fundReqBean,String userId){
		String sql ="update finacbi.REQMST set REQTXT='"+getReqText(fundReqBean,userId)+"'"+"where REQNO=?";
		 scanTemplate.update(sql,new Object[]{fundReqBean.getREQNO().trim()});
	}
	
	private String getReqText(FundReqBean fundReqBean,String userId){
		String text="Cancelled by "+userId+" on dated " +new java.sql.Date(new Date().getTime());
		return text;
	}
	private String getFilterQuery(FundReqQuery fundQuery){
	
		String sql="";
		if(fundQuery!=null){
			if(fundQuery.getReqNo()!=null && !fundQuery.getReqNo().isEmpty()){
				sql+="a.REQNO like '"+fundQuery.getReqNo()+"%'";
			}else{
				sql+="a.REQNO like '%"+fundQuery.getReqNo()+"'";
							}
			if(fundQuery.getParty()!=null && fundQuery.getParty().length()>1){
				sql+=" and trim(a.REQSUNO)='"+fundQuery.getParty().trim()+"'";
			}
			if(fundQuery.getDateFrom() !=null  &&  fundQuery.getDateTo()!=null){
				sql+=" and to_char(a.REQDT,'yyyy-MM-dd') between '"+new java.sql.Date(fundQuery.getDateFrom().getTime())+"' and '"+ new java.sql.Date(fundQuery.getDateTo().getTime()) +"'";
			}
			
			if(fundQuery.getReqType() !=null && !fundQuery.getReqType().isEmpty()){
				sql+=" and a.REQTYP='"+fundQuery.getReqType()+"'";            
			}
			if(fundQuery.getStatus()!=null && !fundQuery.getStatus().isEmpty()){
				sql+=" and a.REQSTS='"+fundQuery.getStatus()+"'"; 
			}
			if(fundQuery.getRequestedTo()!=null && !fundQuery.getRequestedTo().isEmpty()){
				sql+=" and a.PAYLOCT='"+fundQuery.getRequestedTo()+"'"; 

			}
			if(fundQuery.getPoNo()!=null && !fundQuery.getPoNo().isEmpty()){
				sql+=" and b.REQPONO='"+fundQuery.getPoNo()+"'";
			}
			if(fundQuery.getUserId()!=null && !fundQuery.getUserId().isEmpty()){
				sql+=" and a.RQCHID='"+fundQuery.getUserId()+"'"; 
			}
		}
		return sql;

	}
	
	public String getEmailId(String cardNo){
		String sql="select EMAILID from employeelogin where trim(cardno)=?";
		List<String>result=scanTemplate.query(sql,new Object[]{cardNo.trim()}, new RowMapper<String>(){
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
	/*private String getFilterQuery(Query query){
		FundReqQuery fundQuery=(FundReqQuery)query;
		List<SearchCriteria>criteriaList=new ArrayList<SearchCriteria>();
		if(fundQuery!=null){
			if(fundQuery.getReqNo()!=null && !fundQuery.getReqNo().isEmpty()){
				criteriaList.add(new SearchCriteria(" REQNO","like",fundQuery.getReqNo()));
			}
			if(fundQuery.getParty()!=null && !fundQuery.getParty().isEmpty()){
				criteriaList.add(new SearchCriteria(" trim(REQSUNO)","=",fundQuery.getParty()));
			}
			if(fundQuery.getReqType()!=null && !fundQuery.getReqType().isEmpty()){
				criteriaList.add(new SearchCriteria(" trim(REQTYP)","=",fundQuery.getReqType()));
			}
			if(fundQuery.getStatus()!=null && !fundQuery.getStatus().isEmpty()){
				criteriaList.add(new SearchCriteria(" trim(REQSTS)","=",fundQuery.getStatus()));
			}
			if(fundQuery.getDateFrom()!=null ){
				criteriaList.add(new SearchCriteria(" trim(REQDT)","between",fundQuery.getDateFrom()));
			}
			if(fundQuery.getDateTo()!=null ){
				criteriaList.add(new SearchCriteria(" trim(REQDT)",":",fundQuery.getDateTo()));
			}
			if(fundQuery.getPoNo()!=null && !fundQuery.getPoNo().isEmpty()){
				criteriaList.add(new SearchCriteria(" trim(REQPONO)","=",fundQuery.getPoNo()));
			}
			return FundRequestSpecification.getSpecification(criteriaList);
		}

		return null;
	}*/
}
