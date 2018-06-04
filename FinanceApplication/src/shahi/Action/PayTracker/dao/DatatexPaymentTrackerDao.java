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

public class DatatexPaymentTrackerDao {
	@Autowired
	private JdbcTemplate shimogaTemplate;
	public DatatexPaymentTrackerDao(){
		
	}
	public List<DatatexPaymentTracker>loadAllDatatexPayments(PaymentSearch search){

		String query="";
		if(search!=null && search.getDivision()!=null && !search.getDivision().isEmpty()){
			query+=" AND PO.DIVISIONCODE="+search.getDivision().trim();
		}
		if(search!=null && search.getFromDate()!=null && !search.getFromDate().isEmpty() &&  search.getToDate()!=null && !search.getToDate().isEmpty()){
			query+=" AND GE.MAINGATEENTRYDATE BETWEEN '"+DateUtil.getSFLDateFormat(search.getFromDate())+"'";
			query+=" AND '"+DateUtil.getSFLDateFormat(search.getToDate())+"'";
		}
		if(search!=null && search.getResponsible()!=null && !search.getResponsible().isEmpty()){
			query+=" and AD2.VALUESTRING like '"+search.getResponsible().toUpperCase().trim()+"%'";
		}
		if(search!=null && search.getPaymentStatus()!=null && search.getPaymentStatus().equals("1")){
			query+=" and PI.FLAG='SUCCESS'";
		}else if(search!=null && search.getPaymentStatus()!=null && search.getPaymentStatus().equals("2")){
			query+=" and PI.FLAG is null";
		}
		String sql="SELECT  PO.DIVISIONCODE,nvl(PI.FLAG,null,'Pending',PI.FLAG) status ,to_char(GE.MAINGATEENTRYDATE,'dd-mm-yyyy') GATE_DATE,to_char(MH.MRNDATE,'dd-mm-yyyy') mrndate,"+
				" to_char(AD3.VALUEDATE,'dd-mm-yyyy') RECV_AT_ACCT,to_char(PI.CREATIONDATETIME,'dd-mm-yyyy hh24:mi') PI_CREATION, "+
				" BP.LEGALNAME1 PARTY_NAME,PI.CODE PI_INVNO,to_char(PI.INVOICEDATE,'dd-mm-yyyy') PI_INV_DATE,ge.PURCHASEORDERCODE, "+
				" PI.INVOICECURRENCYCODE,PI.INVOICEAMOUNT,AD1.VALUESTRING REMARKS,AD2.VALUESTRING RESPONSIBLE "+
				" FROM   GATEENTRY GE LEFT OUTER JOIN MRNHEADER MH ON GE.MAINGATEENTRYSRNO=MH.MAINGATEENTRYSRNO "+
				" LEFT OUTER JOIN PURCHASEINVOICE PI ON (PI.CODE=MH.PURCHASEINVOICECODE AND PI.ORDPRNCUSTOMERSUPPLIERCODE=MH.ORDPRNCUSTOMERSUPPLIERCODE AND PI.PURCHASEORDERCODE=MH.PURCHASEORDERCODE) "+
				" LEFT OUTER JOIN adstorage AD1  ON (AD1.UNIQUEID=MH.ABSUNIQUEID AND ad1.NAMEENTITYNAME='PurchaseInvoice' and ad1.namename='Remarks')  "+
				" LEFT OUTER JOIN adstorage AD2  ON (AD2.UNIQUEID=MH.ABSUNIQUEID AND ad2.NAMEENTITYNAME='PurchaseInvoice' and ad2.namename='Responsible') "+
				" LEFT OUTER JOIN adstorage AD3  ON (AD3.UNIQUEID=MH.ABSUNIQUEID AND ad3.NAMEENTITYNAME='PurchaseInvoice' and ad3.namename='ReceivedAtAccount') "+
				" ,PURCHASEORDER PO,ORDERPARTNER OP, BUSINESSPARTNER BP "+
				" WHERE "+
				" GE.COMPANYCODE=PO.COMPANYCODE "+
				" AND GE.PURCHASEORDERCOUNTERCODE=PO.COUNTERCODE "+
				" AND GE.PURCHASEORDERCODE=PO.CODE "+
				" AND GE.ORDPRNCUSTOMERSUPPLIERCODE     =OP.CUSTOMERSUPPLIERCODE "+
				" AND OP.ORDERBUSINESSPARTNERNUMBERID =BP.NUMBERID "+query+
				"  order by GE.MAINGATEENTRYDATE desc ";
				//" AND  GE.MAINGATEENTRYDATE BETWEEN '2018-03-01' AND '2018-03-01'";
		
			return shimogaTemplate.query(sql, new BeanPropertyRowMapper(DatatexPaymentTracker.class));
	}
}
