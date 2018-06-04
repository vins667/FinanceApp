package shahi.Action.PayTracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import shahi.Action.PayTracker.bean.DatatexPaymentTracker;
import shahi.Action.PayTracker.bean.PaymentSearch;
import shahi.Action.PayTracker.bean.PaymentTracker;
import shahi.Action.ReportFolder.EPM.util.DateUtil;

public class PaymentTrackerDao {

	private Connection conn;
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	private PaymentTracker payment;
	int noOfDays=0;
	public PaymentTrackerDao(){
		
	}
	public PaymentTrackerDao(Connection conn){
		this.conn=conn;
	}
	public List<PaymentTracker>loadAllPaymentsByCriteria(PaymentSearch search) throws SQLException{
		List<PaymentTracker> list=new ArrayList<PaymentTracker>();
		SimpleDateFormat fDate=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tDate=new SimpleDateFormat("dd/MM/yyyy");
		String query="";

		if(search!=null && search.getPaymentStatus().equals("1")){
			query+=" and chq_no is not null";
		}else if(search!=null && search.getPaymentStatus().equals("2")){
			query+=" and chq_no is  null";
		}
		if(search!=null && search.getFromDate() !=null && !search.getFromDate().isEmpty() && search.getToDate()!=null && !search.getToDate().isEmpty()){
			query+=" and reqdt between '"+DateUtil.converToDDMMMYYYY(search.getFromDate())+"'";
			query+=" and '"+DateUtil.converToDDMMMYYYY(search.getToDate())+"'";
		}

		if(search!=null && search.getAge()!=null && !search.getAge().isEmpty()&&!search.getAge().equals("0")){
			String []dayRange=search.getAge().split("-");
			query+=" and  round(nvl(chq_date,trunc(sysdate))-trunc(reqdt),0) between "+dayRange[0];
			query+=" and   "+dayRange[1];
		}
		if(search!=null && search.getSupplier()!=null && !search.getSupplier().isEmpty()){
			query+=" and a.reqsuno='"+search.getSupplier()+"'";
		}
		if(search!=null && search.getPayroll()!=null && !search.getPayroll().equals("0")){
			query+=" and a.REQTYP='"+search.getPayroll()+"'";
		}
		if(search!=null && search.getAccount()!=null && !search.getAccount().equals("0")){
			query+=" and a.PAYLOCT="+search.getAccount();
		}
		if(search!=null && search.getReqStatus()!=null && !search.getReqStatus().equals("0")){
			query+=" and a.REQSTS="+search.getReqStatus();
		}
		String sql=" select reqdivi division,idsunm party_name,sum(REQPOAMT) req_amt,reqtxt Req_Purpose ,a.reqno,reqdt,REQSTS,trunc(sysdate)-trunc(reqdt) no_of_days,"+
				" chq_no UTR_NO,chq_date UTR_Date,round(nvl(chq_date,trunc(sysdate))-trunc(reqdt),0) payment_leadTime_Pending,"+
				" case when round(nvl(chq_date,trunc(sysdate))-trunc(reqdt),0) between 0 and 7 then '0 - 7 Days'"+
				" when round(nvl(chq_date,trunc(sysdate))-trunc(reqdt),0) between 8 and 14 then '8 - 14 Days'"+
				" when round(nvl(chq_date,trunc(sysdate))-trunc(reqdt),0) between 15 and 21 then '15 - 21 Days'"+
				"  when round(nvl(chq_date,trunc(sysdate))-trunc(reqdt),0) between 22 and 28 then '22 - 28 Days'"+
				" else 'More than 28 Days' end age "+
				" from finacbi.reqmst a "+
				" left outer join prodbi.FGLEDG D on (egcono=111 and egdivi=a.reqdivi and d.egait4=to_char(a.reqno)) "+
				" left outer join movex.fa_bank_statement_dummy E on (E.comp_id=222 and E.year=D.EGYEA4 and E.vser=D.egvser and E.vono=D.egvono) ,"+
				" finacbi.reqdtl b,prodbi.cidmas c "+
				" where a.reqdivi=? and a.reqno=b.reqno  "
				+ query +
				" and c.idcono=111 and c.idsuno=TRIM(a.reqsuno) "+
				" GROUP BY reqdivi,idsunm,reqchq,reqtxt ,a.reqno,reqdt,REQSTS,trunc(sysdate)-trunc(reqdt) , chq_no,chq_date, "+
				" round(nvl(chq_date,trunc(sysdate))-trunc(reqdt),0) order by reqdt desc";

		stmt=conn.prepareStatement(sql);
		stmt.setString(1, search.getDivision());
		rs=stmt.executeQuery();
		while(rs.next()){
			noOfDays=0;
			payment=new PaymentTracker();
			payment.setAge(rs.getString("age"));
			payment.setReqStatus(rs.getString("REQSTS"));
			payment.setChequeDate(rs.getString("UTR_Date"));
			payment.setChequeNo(rs.getString("UTR_NO"));
			payment.setDivision(rs.getString("division"));
			payment.setNoOfDays(rs.getString("no_of_days"));
			payment.setPartyName(rs.getString("party_name"));
			payment.setPaymentLeadTimePending(rs.getString("payment_leadTime_Pending"));
			payment.setReqAmount(Double.valueOf(rs.getString("req_amt")));
			try{
				if(rs.getString("reqdt")!=null){
					payment.setReqDate(tDate.format(fDate.parse(rs.getString("reqdt"))));
				}
				if(rs.getString("UTR_Date")!=null){
					payment.setUtrDate(tDate.format(fDate.parse(rs.getString("UTR_Date"))));
				}
			}catch(ParseException  ex){

			}
			if(rs.getString("UTR_NO")!=null){
				payment.setPaymentStatus("Yes");
			}else{
				payment.setPaymentStatus("No");
			}
			payment.setReqNumber(Long.valueOf(rs.getString("reqno")));
			payment.setReqPurpose(rs.getString("Req_Purpose")!=null ?rs.getString("Req_Purpose").trim():"");
			if(rs.getString("no_of_days")!=null){
				noOfDays=Integer.parseInt(rs.getString("no_of_days"));
				if(noOfDays>0 && noOfDays<6){
					payment.setZeroToSixDays(true);
				}else if(noOfDays > 6 && noOfDays<15){
					payment.setSixToFifteenDays(true);
				}else if(noOfDays>15){
					payment.setFiteenAndAboveDays(true);
				}
			}
			list.add(payment);
			payment=null;
		}
		if(stmt!=null){
			stmt.close();
		}
		if(rs!=null){
			rs.close();
		}

		return list;
	}

	
}
