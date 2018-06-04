package shahi.Action.MvxExp.PRE;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.util.Strings;

import shahi.Action.MI.Beans.MDBREADMIGetMITFAC00Bean;
import shahi.Action.MI.Beans.TXZ050MIGetTaxRateBean;
import shahi.Action.MIM4.MDBREADMI;
import shahi.Action.MIM4.TXZ050MI;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.Beans.AccessoriesSearchBean;
import shahi.Action.MvxExp.PRE.Beans.Parameters;
import shahi.Action.MvxExp.PRE.Beans.PreInvAccrBean;
import shahi.Action.MvxExp.PRE.Beans.PreInvLicenceBean;
import shahi.Action.MvxExp.PRE.Beans.PreInvLineBean;
import shahi.Action.MvxExp.PRE.Beans.PreInvoiceHeader;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvioiceNewDao;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;

public class PreInvoiceMvxAction extends ActionSupport implements SessionAware {

	private String currentdate;
	private String viewFlag;
	private String saveFlag;
	private String exciseFlag;
	private List showList;
	private String searchplan;
	private String searchplan1;
	private String searchinv1;
	private String searchfrom;
	private String searchloct;
	private String GetDataFlag;
	private String searchto;
	private String premast;
	private String searchinv;
	private String aausrid;
	private String INV_COMP;
	private String excs_inv_no;
	private String ex_inv_slno;
	private String ex_inv_date;
	private String plan_no;
	private String inv_date;
	private String inv_state;
	private String gstin_state;
	private String self_tp;
	private String exp_type;
	private String inv_qty;
	private String cost_centre;
	private String buyer;
	private String mode_of_ship;
	private String tto_date;
	private String pre_carriage;
	private String lcno;
	private String to_date;
	private String comm_per;
	private String mmitgr;
	private String upcharge_per;
	private String etd_date;
	private String fin_date;
	private String fwd_custom;
	private String fwd_date;
	private String location;
	private String buyer_addr;
	private String buyer_name;
	private String buyer_address;
	private String cons_addr;
	private String cons_name;
	private String cons_address;
	private String ac_holder;
	private String hs_code;
	private String merchant;
	private String pprq_date;
	private String CLR_PORT;
	private String CLR_PORT_DESC;
	private String LOADING_PORT;
	private String LOADING_PORT_DESC;
	private String first_sale;
	private String facility;
	private String agent;
	private String fwd_code;
	private String notify;
	private String NOTIFY_NAME;
	private String CHA_NAME;
	private String FWD_NAME;
	private String DESTI_CNTRY;
	private String DESTI_STATE;
	private int DESTI_GEO;
	private String DESTI_CNTRY_DESC;
	private String DIS_CNTRY;
	private String DIS_CNTRY_DESC;
	private String DISCHARGE;
	private String DISCHARGE_DESC;
	private String DESTI_CODE;
	private String DESTI_CODE_DESC;
	private String CNTRY_ORIGIN = "IN";
	private String transport_cost;
	private String CTNS;
	private String CNTRY_ORIGIN_DESC = "INDIA";
	private String PLACE;
	private String PLACE_DESC;
	private String EXCISE_UNIT;
	private String EX_GEN_ALLOW;
	private String EX_RATE_ALLOW;
	private String SHIP_DESC = null;
	private String CRNCY_CODE = null;
	private String MANUF_STATE = null;
	private String MANUF_CODE;
	private String MANUF_DESC;
	private String TAX_TYPE = null;
	private String TAX_PERCENT = null;
	private String TAX_CODE = null;
	private String TAX_CAL_PER = null;
	private String ship_term;
	private String payment_term;
	private String pay_term;
	private String ship_cancel;//invoice cancel
	private String customfwd_auth;
	private String outhouse;
	private String pay_term_desc;
	private String to_inr_conv;
	private String unitparam;
	private String PARAA;
	private String PARAB;
	private String TYPE_CODE;
	private String LIC_TYPE;
	private String LIC_NO;
	private List unitList = new ArrayList<UnitBean>();
	private String YEAR;
	private String COMPANY;
	private String INV_NO;
	private String GRWT;
	private String NETWT;
	private String TOT_GR;
	private String TOT_FOB;
	private String NET_FOB;
	private String INVDATE;
	private String dbkslnocopy;
	private String accrFlag;
	private int sdate;
	private List SHIP_TYPE_LIST = new ArrayList();
	private List SCHEME_CODE_LIST = new ArrayList();
	private List EXCISE_UNIT_LIST = new ArrayList();
	private List ACCR_TYPE_LIST = new ArrayList();
	private List CATG_LIST = new ArrayList();
	private List LicenceList = new ArrayList();
	private List BEList = new ArrayList();
	private List InvLineList = new ArrayList();
	private List InvLicenceList = new ArrayList();
	private List PaymenttermList = new ArrayList();
	private List AccrLineList = new ArrayList();
	private List modlList = new ArrayList();
	private List tedlList = new ArrayList();
	private String CO_MOS;
	private String INV_GEO;
	private String INV_GSTIN;
	private List CO_NO_E;
	private List SR_NO;
	private List CO_LINE_E;
	private List ITEM_NO_E;
	private List QTY_ENDORS_E;
	private List CO_UOM_E;
	private List QTY_KGS;
	private List PRICE_FC_E;
	private List PRICE_MISC;
	private List HNGR_COST;
	private List TAG_COST;
	private List ADJUST_FC;
	private List CATG_CODE;
	private List INV_DESC;
	private List STR_MISC;
	private List DBK_SLNO;
	private List ROSL_SLNO;
	private List SCHEME_CODE;
	private List STR_SLNO;
	private List GR_DECL_AMT_E;
	private List NET_PRICE_E;
	private List TOKEN_NO;
	private List PRE_PRINT_NO;
	private List GR_DECL_PER;
	private List MRP_RATE;
	private List TEMP_CAT;
	private List MADE_FOR;
	private List HSN_CODE_E;
	private List IGST_PER_E;
	private List CGST_PER_E;
	private List SGST_PER_E;
	private String GETREFRESH;
	private List ACCR_CO_NO;
	private List ACCR_CO_LINE;
	private List ACCR_ITEM_NO;
	private List ACCR_TYPE;
	private List ACCR_DBKSLNO;
	private List ACCR_STRSLNO;
	private List ACCR_QTY;
	private List ACCR_PRICE;
	private List REF_NO;
	private List REF_TYPE;
	private List LIC_DATE;
	private List LIC_COMP;
	private List LIC_LOCT;
	private List QTY;
	private List ADJS_QTY;
	private List QTY_SQM;
	private List FOB_AMT;
	private List BE_NO;
	private List IO_NORMS;
	private List EXP_CTRL_NO;
	private List IMP_CTRL_NO;
	private List HSCODE1;
	private String MSG;
	private List CO_NO_EXCISE;
	private List CO_LINE_EXCISE;
	private String FCTOTAL;
	private String QTYTOTAL;
	private String LUT_IGST; 
	private InputStream inputStream;
	BigDecimal bd = new BigDecimal("0.0");
	BigDecimal bd1 = new BigDecimal("0.0");
	private AccessoriesSearchBean searchBean;
	private Map<String,Object> map=new LinkedHashMap<>(0);
	private String ciNo;
	private String ciDate;
	private PreInvoiceHeader header;
	private boolean isDisabled=true;
	private String locationCode;
	private Parameters parameter=new Parameters();

	@Override
	public String execute() {

		showList = new ArrayList();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			EisUtil pisdate = new EisUtil();
			currentdate = pisdate.GetDate();
			pisdate.closeConnection();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		int flag = 0;

		if(getAccrLineList()!=null){
			getAccrLineList().clear();
		}

		Map session = ActionContext.getContext().getSession();

		getMap().put("isDisabled",isDisabled);

		String LOCATION_CODE = (String) session.get("sessLocationCode");
		String usrid = (String) session.get("sessUserId");

		if (usrid == null) {
			session.put("sessUserId", aausrid);
			usrid = aausrid;

		}

		//usrid = "227350";
		if (usrid == null) {
			addActionMessage("Session Not Valid !!");
			return ERROR;
		}

		try {

			Connection conn = null;
			Connection conndb2 = null;
			PreparedStatement stat = null;
			PreparedStatement stat1 = null;
			PreparedStatement stat2 = null;
			PreparedStatement stat3 = null;

			ResultSet result = null;
			ResultSet result1 = null;
			ResultSet result2 = null;
			ResultSet result3 = null;

			try {
				conn = new connection().getConnection();
				conn.setAutoCommit(false);
				conndb2 = new connectiondb2().getConnection();
				Date todaysDate = new Date();
				SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");

				stat1 = conn.prepareStatement("select location_code,to_char(sysdate,'yyyymmdd') sdate from seh_web_users where user_id=?");
				stat1.setString(1, usrid);
				result1 = stat1.executeQuery();
				while (result1.next()) {
					LOCATION_CODE = result1.getString("location_code");
					searchloct = result1.getString("location_code");
					sdate = result1.getInt("sdate");
				}

				stat = conn.prepareStatement(" select type_Desc,type_code from ei_grup_type_dtls where grup_type_code='SHT' order by 1");
				result = stat.executeQuery();
				while (result.next()) {
					PaymenttermList.add(new GetListBean(result.getString("type_code"), result.getString("type_desc")));
				}
				stat = conndb2.prepareStatement("select  cttx15,trim(ctstky) ctstky from csytab  where ctcono=111 and ctstco='MODL'  order by 1");
				result = stat.executeQuery();
				while (result.next()) {
					modlList.add(new GetListBean(result.getString("ctstky"), result.getString("cttx15")));
				}
				stat = conndb2.prepareStatement("select  cttx15||' - '||ctstky cttx15,trim(ctstky) ctstky from csytab  where ctcono=111 and ctstco='TEDL'  order by 1");
				result = stat.executeQuery();
				while (result.next()) {
					tedlList.add(new GetListBean(result.getString("ctstky"), result.getString("cttx15")));
				}

				if (viewFlag.equals("YES")) {
					if (GETREFRESH == null) {
						stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(a.inv_date,'dd-Mon-yyyy') inv_date,to_char(a.inv_date,'yyyymmdd') invdt,a.exp_type,nvl(a.self_tp,'N') self_tp,to_char(a.fwd_custom,'dd/mm/yyyy') fwd_custom,to_char(a.tto_date,'dd/mm/yyyy') tto_date,to_char(a.etd_date,'yyyy-mm-dd') etd_date,notify,trim(agent) agent,trim(fwd_code) fwd_code,hs_code,trim(manuf_code) MANUF_CODE,"
								+ " to_char(a.t_o_date,'yyyy-mm-dd') to_date,to_char(a.doc_send,'dd/mm/yyyy') fwd_date,to_char(a.fin_date,'dd/mm/yyyy') fin_date,a.ac_holder,a.cost_centre,trim(a.mode_of_ship) mode_of_ship,a.inv_qty,a.buyer,a.buyer_addr,a.cons_addr,a.year,a.company,a.inv_no,LOADING_port,LOADING CLR_port,pre_carriage,UPCHARGE_PER,comm_per,mmitgr,payment_term,trim(ship_term) ship_term,trim(pay_term) pay_term,place,excise_unit,"
								+ " DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,MERCHANT_NAME,to_char(a.pprq_date,'dd/mm/yyyy') pprq_date,a.crncy_code,a.lcno,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,a.tax_cal_per,ex_inv_slno,to_char(ex_inv_date,'dd/mm/yyyy') ex_inv_date,"
								+ " a.transport_cost,facility,CTNS,GRWT,NETWT,inv_state,DESTI_STATE,LUT_IGST,ci_no,to_char(a.ci_date,'dd-Mon-yyyy') ci_date  from ei_endors_mast a  where  a.excs_inv_no like nvl(?,'%') and  a.plan_no like nvl(?,'%') and nvl(ci_no,9) like nvl(?,'%') ");
						stat1.setString(1, searchinv);
						stat1.setString(2, searchplan);
						stat1.setString(3, getCiNo());
						result1 = stat1.executeQuery();
						if (result1.next()) {
							getHeader().setLocationCode(result1.getString("location"));
							getHeader().setInvoiceNo(result1.getString("excs_inv_no"));
							getHeader().setInvoiceDate(result1.getString("inv_date"));
							getHeader().setCiDate(result1.getString("ci_date"));
							getHeader().setCiNo(result1.getString("ci_no"));
							getHeader().setPlanNo(result1.getString("plan_no"));
							getHeader().setInvoiceType(result1.getString("self_tp"));
							getHeader().setExpType(result1.getString("exp_type"));
							getHeader().setHsCode(result1.getString("hs_code"));
							getHeader().setLcNo(result1.getString("lcno"));
							getHeader().setCommision(result1.getString("comm_per"));
							getHeader().setUpcharge(result1.getString("upcharge_per"));
							getHeader().setPreCarriage(result1.getString("pre_carriage"));
							if(result1.getString("fwd_custom")!=null){
								getHeader().setFwdToCustom("Y");
							}
							getHeader().setFwdToCustomDate(result1.getString("fwd_custom"));
							getHeader().setTtoDate(result1.getString("tto_date"));
							getHeader().setEtdDate(result1.getString("etd_date"));
							getHeader().setToDate(result1.getString("to_date"));
							getHeader().setFowardDate(result1.getString("fwd_date"));
							getHeader().setAccountHolder(result1.getString("ac_holder"));
							getHeader().setMerchant(result1.getString("MERCHANT_NAME"));
							getHeader().setPprqDate(result1.getString("pprq_date"));
							getHeader().setPch(result1.getString("cost_centre"));
							getHeader().setShipMode(result1.getString("mode_of_ship"));
							getHeader().setShipMode(result1.getString("mode_of_ship"));
							getHeader().setInvoiceQty(result1.getString("inv_qty"));
							getHeader().setAgent(result1.getString("agent"));
							getHeader().setForwarder(result1.getString("fwd_code"));
							getHeader().setManufacturer(result1.getString("MANUF_CODE"));
							getHeader().setNotify(result1.getString("notify"));
							getHeader().setPaymentTerm(result1.getString("payment_term"));
							getHeader().setTransportRate(result1.getString("transport_cost"));
							getHeader().setTotalCartons(result1.getString("CTNS"));
							getHeader().setLoadingPort(result1.getString("LOADING_PORT"));
							getHeader().setClearingPort(result1.getString("CLR_PORT"));
							getHeader().setDestinationCntry(result1.getString("DESTI_CNTRY"));
							getHeader().setDischargeCntry(result1.getString("DIS_CNTRY"));
							getHeader().setOriginCntry(result1.getString("CNTRY_ORIGIN"));
							getHeader().setDischarge(result1.getString("DISCHARGE"));
							getHeader().setDestination(result1.getString("DESTI_CODE"));
							getHeader().setPlace(result1.getString("place"));
							getHeader().setCurrency(result1.getString("crncy_code"));
							getHeader().setTaxCode(result1.getString("tax_code"));
							getHeader().setOutHouse(result1.getString("outhouse"));
							getHeader().setShipCnxl(result1.getString("ship_cancel"));
							getHeader().setTotalNetWeight(result1.getString("netwt"));
							getHeader().setTotalGrossWeight(result1.getString("grwt"));
							getHeader().setInvoiceState(result1.getString("inv_state"));
							getHeader().setLutIGST(result1.getString("lut_igst"));
							getHeader().setTaxCalFob(result1.getString("tax_cal_per"));
							getHeader().setYear(result1.getString("year"));
							getHeader().setCompany(result1.getString("company"));
							getHeader().setInvNo(result1.getString("inv_no"));
							getHeader().getBuyer().setBUYER(result1.getString("buyer"));
							getHeader().getBuyer().setAddressId(result1.getString("buyer_addr"));
							getHeader().getConsignee().setBUYER(result1.getString("buyer"));
							getHeader().getConsignee().setAddressId(result1.getString("cons_addr"));
							getHeader().getBuyer().setDestinationState(result1.getString("DESTI_STATE"));

							/*buyer = result1.getString("buyer");
							buyer_addr = result1.getString("buyer_addr");
							cons_addr = result1.getString("cons_addr");*/
							/*
							searchloct = result1.getString("location");
							searchplan = result1.getString("plan_no");
							searchinv = result1.getString("excs_inv_no");*/
							excs_inv_no = result1.getString("excs_inv_no");
							ex_inv_slno = result1.getString("ex_inv_slno");
							ex_inv_date = result1.getString("ex_inv_date");
							location = result1.getString("location");
							plan_no = result1.getString("plan_no");
							inv_date = result1.getString("inv_date");
							sdate = result1.getInt("invdt");
							self_tp = result1.getString("self_tp");
							exp_type = result1.getString("exp_type");
							hs_code = result1.getString("hs_code");
							lcno = result1.getString("lcno");
							facility = result1.getString("facility");
							comm_per = result1.getString("comm_per");
							mmitgr = result1.getString("mmitgr");
							upcharge_per = result1.getString("upcharge_per");
							pre_carriage = result1.getString("pre_carriage");
							fwd_custom = result1.getString("fwd_custom");
							tto_date = result1.getString("tto_date");
							etd_date = result1.getString("etd_date");
							to_date = result1.getString("to_date");
							fwd_date = result1.getString("fwd_date");
							fin_date = result1.getString("fin_date");
							ac_holder = result1.getString("ac_holder");
							merchant = result1.getString("MERCHANT_NAME");
							pprq_date = result1.getString("pprq_date");
							cost_centre = result1.getString("cost_centre");
							mode_of_ship = result1.getString("mode_of_ship");
							inv_qty = result1.getString("inv_qty");
							buyer = result1.getString("buyer");
							buyer_addr = result1.getString("buyer_addr");
							cons_addr = result1.getString("cons_addr");
							first_sale = result1.getString("ship_type");
							ship_term = result1.getString("ship_term");
							agent = result1.getString("agent");
							fwd_code = result1.getString("fwd_code");
							MANUF_CODE = result1.getString("MANUF_CODE");
							notify = result1.getString("notify");
							pay_term = result1.getString("pay_term");
							transport_cost = result1.getString("transport_cost");
							CTNS = result1.getString("CTNS");
							payment_term = result1.getString("payment_term");
							LOADING_PORT = result1.getString("LOADING_PORT");
							CLR_PORT = result1.getString("CLR_PORT");
							DESTI_CNTRY = result1.getString("DESTI_CNTRY");
							DIS_CNTRY = result1.getString("DIS_CNTRY");
							CNTRY_ORIGIN = result1.getString("CNTRY_ORIGIN");
							DISCHARGE = result1.getString("DISCHARGE");
							DESTI_CODE = result1.getString("DESTI_CODE");
							DESTI_STATE = result1.getString("DESTI_STATE");
							PLACE = result1.getString("place");
							CRNCY_CODE = result1.getString("crncy_code");
							MANUF_STATE = result1.getString("manuf_state");
							TAX_TYPE = result1.getString("tax_type");
							TAX_PERCENT = result1.getString("tax_percent");
							TAX_CODE = result1.getString("tax_code");
							TAX_CAL_PER = result1.getString("tax_cal_per");
							SHIP_DESC = result1.getString("SHIP_DESC");
							ship_cancel = result1.getString("ship_cancel");
							outhouse = result1.getString("outhouse");
							GRWT = result1.getString("grwt");
							NETWT = result1.getString("netwt");
							EXCISE_UNIT = result1.getString("excise_unit");
							inv_state = result1.getString("inv_state");
							DESTI_STATE = result1.getString("DESTI_STATE");
							LUT_IGST = result1.getString("lut_igst");
							ciNo=result1.getString("ci_no");
							ciDate=result1.getString("ci_date");
							GetDataFlag = "NO";

							UnitBean bn = new PreInvoiceDao().getCsytabBeanByName(DIS_CNTRY, "CSCD");
							DIS_CNTRY_DESC = bn.getUNIT_DESC();

							bn = new PreInvoiceDao().getCsytabBeanByName(CNTRY_ORIGIN, "CSCD");
							CNTRY_ORIGIN_DESC = bn.getUNIT_DESC();

							bn = new PreInvoiceDao().getCsytabBeanByName(DESTI_CNTRY, "CSCD");
							DESTI_CNTRY_DESC = bn.getUNIT_DESC();

							bn = new PreInvoiceDao().getCsytabBeanByName(LOADING_PORT, "HAFE");
							LOADING_PORT_DESC = bn.getUNIT_ADDRESS();

							bn = new PreInvoiceDao().getCsytabBeanByName(CLR_PORT, "HAFE");
							CLR_PORT_DESC = bn.getUNIT_ADDRESS();

							bn = new PreInvoiceDao().getCsytabBeanByName(DISCHARGE, "SDST");
							DISCHARGE_DESC = bn.getUNIT_DESC();

							bn = new PreInvoiceDao().getCsytabBeanByName(PLACE, "EDES");
							PLACE_DESC = bn.getUNIT_DESC();


							bn = new PreInvoiceDao().getCsytabBeanByName(pay_term, "TEPY");
							pay_term_desc = bn.getUNIT_ADDRESS();

							bn = new PreInvoiceDao().getCHAName(result1.getString("agent"));
							CHA_NAME = bn.getUNIT_DESC();

							bn = new PreInvoiceDao().getNotifyName(result1.getString("notify"));
							NOTIFY_NAME = bn.getUNIT_DESC();

							bn = new PreInvoiceDao().getCHAName(result1.getString("fwd_code"));
							FWD_NAME = bn.getUNIT_DESC();

							bn = new PreInvoiceDao().getUnitByName(MANUF_CODE);
							MANUF_DESC = bn.getUNIT_DESC();
							INV_GEO=bn.getUNIT_CODE();
							YEAR = result1.getString("year");
							COMPANY = result1.getString("company");
							INV_NO = result1.getString("inv_no");

							stat = conn.prepareStatement("select sum(gr_decl_amt) grdecl,sum(qty_endors*(price_fc+nvl(price_misc,0))) fobfc ,sum((qty_endors*(price_fc+nvl(price_misc,0))-gr_decl_amt)) netfob from ei_endors_dtls where year=? and company=? and inv_no=?");
							stat.setString(1, YEAR);
							stat.setString(2, COMPANY);
							stat.setString(3, INV_NO);
							result = stat.executeQuery();
							if (result.next()) {
								TOT_FOB = result.getString("fobfc");
								TOT_GR = result.getString("grdecl");
								NET_FOB = result.getString("netfob");

								getHeader().setNetFob(result.getString("netfob"));
								getHeader().setFobfc(TOT_FOB);

							}


							GETREFRESH = "NO";
						} else {
							GETREFRESH = "YES";
						}
					}
					/////////   New Plan to Generate Invoice........      
					// Check Planing for Refresh

					stat = conn.prepareStatement("select * from seplweb.pr_ship_plan_master where inv_no is not null and year is not null and plan_numb=? ");
					stat.setString(1, searchplan);
					result = stat.executeQuery();
					if (result.next() == true) {
						GetDataFlag = "NO";
					}

					if (GETREFRESH.equals("YES")) {
						plan_no = searchplan;
						GetDataFlag = "YES";


						// stat = conn.prepareStatement("select a.company_code,a.location_code,nvl(a.sale_type,'N') sale_type,sum(round((nvl(plan_qnty,0)+nvl(extra_qnty,0))/nvl(conv_factr,1),2)) planqty,TO_CHAR(decode(to_char(sysdate,'MM'),'03',ADD_MONTHS(SYSDATE,-1),SYSDATE),'dd-Mon-yyyy') idate  from  sepl.pr_ship_plan_master a,sepl.pr_ship_plan_detail b  where a.plan_numb=b.plan_numb and a.plan_numb=? group by a.company_code,a.location_code,nvl(a.sale_type,'N'),to_char(trunc(sysdate),'dd-Mon-yyyy')");
						stat = conn.prepareStatement("select a.company_code,a.location_code,nvl(a.sale_type,'N') sale_type,a.STATE_CODE,GST_STATE_CODE ,"
								+ "  sum(round((nvl(plan_qnty,0)+nvl(extra_qnty,0)-nvl(short_close_qty,0))/nvl(conv_factr,1),2)) planqty,to_char(trunc(sysdate),'dd-Mon-yyyy') idate  from  seplweb.pr_ship_plan_master a,seplweb.pr_ship_plan_detail b,seplvportal.state_master c"
								+ "   where a.plan_numb=b.plan_numb and a.state_code=c.state_code and c.COUNTRY_MASTER_ID=35 and a.plan_numb=? " 
								+ " group by a.company_code,a.location_code,nvl(a.sale_type,'N'),to_char(trunc(sysdate),'dd-Mon-yyyy'),a.STATE_CODE,GST_STATE_CODE ");
						stat.setString(1, searchplan);
						result = stat.executeQuery();
						if (result.next() == true) {

							inv_qty = result.getString("planqty");
							first_sale = result.getString("sale_type");
							facility = result.getString("location_code");
							inv_date = result.getString("idate");
							inv_state = result.getString("state_code");
							gstin_state = result.getString("GST_STATE_CODE");


							if (facility == null) {
								addActionMessage("Check Plan Entry not Found... !!!");
								return ERROR;
							}
							if (inv_state == null || gstin_state == null) {
								addActionMessage("Check  State Code Found... !!!");
								return ERROR;
							}


						} else {
							addActionMessage("Check Plan Entry not Found... !!!");
							return ERROR;

						}
						stat = conn.prepareStatement(" select to_char(inv_date,'dd-Mon-yyyy') inv_date,t_o_date,tto_date,etd_date,to_char(inv_date,'yyyymmdd') invdt,lut_igst from ei_endors_mast where plan_no=?");
						stat.setString(1, searchplan);
						result = stat.executeQuery();
						if (result.next() == true) {
							to_date = result.getString("t_o_date");
							etd_date = result.getString("etd_date");
							tto_date = result.getString("tto_date");
							sdate = result.getInt("invdt");
							inv_date = result.getString("inv_date");
							LUT_IGST=result.getString("LUT_IGST");


						} 
						// Checking Duplicate Delv No.
						stat = conn.prepareStatement("select del_numb  from seplweb.pr_ship_plan_detail where inv_no is not null and del_numb in (select distinct del_numb from seplweb.pr_ship_plan_detail where del_numb not in (999999,888888) and plan_numb=? and (nvl(plan_qnty,0)+nvl(extra_qnty,0)-nvl(short_close_qty,0)>0)) and plan_numb <> ?");
						stat.setString(1, searchplan);
						stat.setString(2, searchplan);
						result = stat.executeQuery();
						if (result.next() == true) {
							addActionMessage("Delevery No Already Linked with other invoice. Pls change the Delv No " + result.getString("del_numb"));
							return ERROR;
						}

						///  Checking CO Header  
						String p_error = "";

						/*CallableStatement RsStat =conn.prepareCall("{call  exports.proc_cocheck_hd115(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                         RsStat.setString(1,searchplan);
                         RsStat.registerOutParameter(2, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(3, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(4, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(5, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(6, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(7, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(8, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(9, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(10, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(11, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(12, java.sql.Types.VARCHAR);
                         RsStat.registerOutParameter(13, java.sql.Types.VARCHAR);
                         RsStat.executeUpdate();
                         p_error = cocheck(searchplan,conn,conndb2);
                         buyer = RsStat.getString(3);
                         pay_term=RsStat.getString(4); 
                         pay_term_desc=RsStat.getString(5);
                         CRNCY_CODE=RsStat.getString(6);
                         buyer_addr=RsStat.getString(7);
                         cons_addr=RsStat.getString(8); 
                         mode_of_ship=RsStat.getString(9);
                         CO_MOS=RsStat.getString(9);
                         ship_term=RsStat.getString(10);
                         DESTI_CODE=RsStat.getString(11).trim();
                         DESTI_CNTRY=RsStat.getString(12).trim(); 
                         to_inr_conv=RsStat.getString(13);*/

						p_error = cocheck(searchplan, conn, conndb2);

						if (p_error.length() > 0) {
							addActionMessage(" Error: " + p_error);
							return ERROR;
						}
						if (to_inr_conv == null) {
							addActionMessage("Check Movex Inr Conv.... ");
							return ERROR;
						}

						/* CallableStatement RsStat =conn.prepareCall("{call  exports.proc_cocheck_line115(?,?,?)}");
                         RsStat.setString(1,searchplan);
                         RsStat.setString(2,usrid);
                         RsStat.registerOutParameter(3, java.sql.Types.VARCHAR);
                         RsStat.executeUpdate();
                         p_error = RsStat.getString(3);
                         if (p_error!=null)
                         {    addActionMessage(" Error: "+p_error);
                         return  ERROR;
                         }*/
						p_error = cocheckline(searchplan, usrid, conn, conndb2);
						if (p_error.length() > 0) {
							addActionMessage(" Error: " + p_error);
							return ERROR;
						}

						stat = conn.prepareStatement("select distinct PCH,EXP_TYPE,COMM_PER,mmitgr from exports.ei_get_coline where plan_no=? and userid=? ");
						stat.setString(1, plan_no);
						stat.setString(2, usrid);
						result = stat.executeQuery();

						while (result.next()) {

							cost_centre = result.getString("pch");
							exp_type = result.getString("exp_type");
							comm_per = result.getString("comm_per");
							mmitgr = result.getString("mmitgr");
						}


					}////// New Plan Closed......

					if (fwd_custom != null && fwd_date == null) {
						stat = conn.prepareStatement("select *  from pa_auth_mast where user_id=? and prog_name='INV_FWD' ");
						stat.setString(1, usrid);
						result = stat.executeQuery();
						if (result.next() == true) {

							customfwd_auth = "YES";
						}
					}


					stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
					stat.setString(1, buyer.trim());
					stat.setString(2, buyer_addr.trim());
					result = stat.executeQuery();
					if (result.next() == true) {
						getHeader().getBuyer().setBuyerName( result.getString("opcunm"));
						getHeader().getBuyer().setBUYER_ADDRESS(result.getString("opadd"));
						buyer_name = result.getString("opcunm");
						buyer_address = result.getString("opadd");
					}

					stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
					stat.setString(1, buyer.trim());
					stat.setString(2, cons_addr.trim());
					result = stat.executeQuery();
					if (result.next() == true) {
						getHeader().getConsignee().setBuyerName(result.getString("opcunm"));
						getHeader().getConsignee().setBUYER_ADDRESS(result.getString("opadd"));
						cons_name = result.getString("opcunm");
						cons_address = result.getString("opadd");
					}


					EisUtil eisutil = new EisUtil();
					eisutil.openConnection();
					result = eisutil.getCSYTAB("EDES", DESTI_CODE);
					while (result.next()) {
						DESTI_CODE_DESC = result.getString("cttx40");
					}
					/*eisutil.closeConnection();
					eisutil = new EisUtil();*/

					result = eisutil.getCSYTAB("CSCD", DESTI_CNTRY);
					while (result.next()) {
						DESTI_CNTRY_DESC = result.getString("cttx40");
					}
					eisutil.closeConnection();
				}/// View Flag Closed...
				//System.out.println("check flag"+CHECK_APRV);
				//getMap().put(getHeader().getPlanNo(),getHeader().getInvoiceQty()+","+TOT_FOB+","+getHeader().getLocationCode());
				//System.out.println("Header Bean"+getHeader());

			} catch (Exception e) {

				flag = 0;
				try {
					flag = 0;
					conn.rollback();

				} catch (Exception ee) {
					System.out.print("1 file name : PreInvoiceMvxAction.java" + ee);

					System.out.println(ee.toString());
				}
				System.out.print("1 file name : PreInvoiceMvxAction.java" + e);

				System.out.println(e.toString());
			} finally {

				try {

					if (result1 != null) {
						result1.close();
					}
					if (result2 != null) {
						result2.close();
					}
					if (result3 != null) {
						result3.close();
					}
					if (result != null) {
						result.close();
					}


					if (stat1 != null) {
						stat1.close();
					}
					if (stat2 != null) {
						stat2.close();
					}
					if (stat3 != null) {
						stat3.close();
					}
					if (stat != null) {
						stat.close();
					}
					if (conn != null) {
						conn.close();
					}
					if (conndb2 != null) {
						conndb2.close();
					}
					result1 = null;
					stat1 = null;
					stat = null;
					stat2 = null;
					conn = null;

				} catch (Exception e) {
					flag = 0;
					System.out.print("File Name : PreInvoiceMvxAction.java Exception in finally block");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();

			addActionError(e.getMessage());

			return INPUT;

		}

		if (flag == 1) {

			addActionMessage("Records Save(s) !!");
			return SUCCESS;
		} else {

			// addActionMessage("Records Not Save(s) !!");
			return ERROR;
		}
	}

	public String getBuyerCode(){
		return "";
	}
	public String cocheck(String P_PLAN, Connection conn, Connection conndb2) {
		/* Adding below code for replacement of procedure*/
		PreparedStatement pst2 = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String p_error = "";
		String v_buyer = "";
		String v_error = "";
		String v_payterm = "";
		String v_paymethod = "";
		String v_baddr = "";
		String v_crncy = "";
		String v_payterm_desc = "";
		String v_inrconv = "";
		String v_cotype = "";
		String err1 = "";
		String v_cons_addr = "";
		String v_desti_desc = "";
		String v_desti_name = "";
		String v_mode = "";
		String v_ship_term = "";
		String v_caddr = "";
		String v_desti_code = "";
		String v_desti_cntry = "";
		String v_desti_state = "";
		int v_desti_geo = 0;


		try {


			String Sql1 = "select distinct co_numb from seplweb.pr_ship_plan_detail where plan_numb=? and (nvl(plan_qnty,0)+nvl(extra_qnty,0)-nvl(short_close_qty,0))>0";
			pst = conn.prepareStatement(Sql1);
			pst.setString(1, P_PLAN);
			rs = pst.executeQuery();
			while (rs.next()) {
				String Sql2 = "select oafaci,oaortp,oatepy,oapycd,oacuno okcuno,oacucd ,oaadid,c.cttx15 from  m3fdbprd.oohead a, "
						+ " m3fdbprd.csytab c where a.oacono=c.ctcono and a.oatepy=c.ctstky and	c.ctstco=rpad('TEPY',10,' ')  and "
						+ " a.oacono='111' and  a.oaorno=?";
				pst1 = conndb2.prepareStatement(Sql2);
				pst1.setString(1, rs.getString("co_numb"));
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					if (v_buyer != null && v_buyer.length() > 0) {
						if (!v_buyer.equals(rs1.getString("okcuno"))) {
							v_error = "Buyer ";
						}
						if (!v_payterm.equals(rs1.getString("oatepy"))) {
							v_error = v_error + " & Payment Term ";
						}

						if (!v_paymethod.equals(rs1.getString("oapycd"))) {
							v_error = v_error + " & Payment Method ";
						}
						if (!v_baddr.equals(rs1.getString("oaadid"))) {
							v_error = v_error + " & Buyer Address No ";
						}
						if (!v_crncy.equals(rs1.getString("oacucd"))) {
							v_error = v_error + " & Currency ";
						}
						if (!v_cotype.equals(rs1.getString("oaortp"))) {
							v_error = v_error + " & CO Type ";
						}
						if (v_error != null && v_error.length() > 0) {
							p_error = "All CO Must be Same " + v_error + "  !! Check [OIS300";
						}

					} else {
						v_payterm = rs1.getString("oatepy");
						v_paymethod = rs1.getString("oapycd");
						v_buyer = rs1.getString("okcuno");
						v_crncy = rs1.getString("oacucd");
						v_baddr = rs1.getString("oaadid");
						v_payterm_desc = rs1.getString("cttx15");
						v_cotype = rs1.getString("oaortp");
					}

				}

			}
			String Sql3 = " select  cuarat from  m3fdbprd.ccurra c where c.cucutd = (select max(y.cucutd) "
					+ " from m3fdbprd.ccurra  y where c.cucono = y.cucono and c.cucucd = y.cucucd "
					+ " and c.cucrtp = y.cucrtp and c.culocd = y.culocd and y.CUCUTD<=? )"
					+ " and c.cucucd = ? and cucrtp =1 and c.cucono=111 and culocd = 'INR'";
			pst = conndb2.prepareStatement(Sql3);

			Date sysdate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fromdate = sdf.format(sysdate);
			pst.setString(1, fromdate);
			pst.setString(2, v_crncy);
			rs = pst.executeQuery();
			if (rs.next()) {
				v_inrconv = rs.getString("cuarat");
			}

			String p_cons_addr="";
			String Sql4 = "select distinct co_numb,co_line,item_numb,DEL_ADD_NO from seplweb.pr_ship_plan_detail a"
					+ " where a.plan_numb=? and  (nvl(plan_qnty,0)+nvl(extra_qnty,0)-nvl(short_close_qty,0))>0";
			pst = conn.prepareStatement(Sql4);
			pst.setString(1, P_PLAN);
			rs = pst.executeQuery();
			while (rs.next()) {
				p_cons_addr = rs.getString("DEL_ADD_NO");
				String Sql5 = "select  obadid,obmodl,obtedl, 'A' d_desc, 'A' ds_desc from m3fdbprd.ooline a"
						+ " where obcono=111 and oborno=? and obponr=? and obitno=?  ";
				pst1 = conndb2.prepareStatement(Sql5);
				pst1.setString(1, rs.getString("co_numb"));
				pst1.setString(2, rs.getString("co_line"));
				pst1.setString(3, rs.getString("item_numb"));
				rs1 = pst1.executeQuery();
				while (rs1.next()) {
					// System.out.println(p_cons_addr+rs.getString("co_numb")+" Line "+rs.getString("co_line")+" Addr"+"cons addr "+rs1.getString("obadid"));
					if (v_mode != null && v_mode.length() > 0) {
						//   if (p_cons_addr != null && p_cons_addr.length() > 0) {
						if (p_cons_addr==null || p_cons_addr.length() == 0) {
							if (!v_cons_addr.equals(rs1.getString("obadid"))) {
								err1 = err1 + " & Cons Address No";
							}
						} 
						if (err1 != null && err1.length() > 0) {
							p_error = "All CO Order Lines must be same " + err1 + " Check [OIS300 Lines]   ";
						}
					} else { 
						v_cons_addr = rs1.getString("obadid");
						v_desti_desc = rs1.getString("d_desc");
						v_desti_name = rs1.getString("ds_desc");
						v_mode = rs1.getString("obmodl");
						v_ship_term = rs1.getString("obtedl");
						if (p_cons_addr == null || p_cons_addr.length() == 0) {
							v_caddr = v_cons_addr;
						} else {

							v_caddr = p_cons_addr;
						}

					}

				}
				String Sql6 = "select  opedes,opcscd,OPECAR,opgeoc from m3fdbprd.ocusad where opcono=111 and "
						+ " opcuno=? and opadid=? and opadrt=1";
				pst2 = conndb2.prepareStatement(Sql6);
				pst2.setString(1, v_buyer);
				pst2.setString(2, v_caddr);
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					v_desti_code = rs2.getString("opedes");
					v_desti_cntry = rs2.getString("opcscd");
					//   v_desti_state = rs2.getString("opecar");
					//   v_desti_geo = rs2.getInt("opgeoc");
				}

				String Sql7 = "select  opedes,opcscd,OPECAR,opgeoc from m3fdbprd.ocusad where opcono=111 and "
						+ " opcuno=? and opadid=? and opadrt=1";
				pst2 = conndb2.prepareStatement(Sql7);
				pst2.setString(1, v_buyer);
				pst2.setString(2, v_baddr);
				rs2 = pst2.executeQuery();
				if (rs2.next()) {

					v_desti_state = rs2.getString("opecar");
					v_desti_geo = rs2.getInt("opgeoc");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			try {
				if (rs2 != null) {
					rs2.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (pst2 != null) {
					pst2.close();
				}
				if (pst1 != null) {
					pst1.close();
				}
				if (pst != null) {
					pst.close();
				}


			} catch (Exception ee) {
			}
		}
		buyer = v_buyer;
		pay_term = v_payterm;
		pay_term_desc = v_payterm_desc;
		CRNCY_CODE = v_crncy;
		buyer_addr = v_baddr;
		cons_addr = v_caddr;
		mode_of_ship = v_mode;

		CO_MOS = v_mode;
		ship_term = v_ship_term;
		DESTI_CODE = v_desti_code;
		DESTI_CNTRY = v_desti_cntry;
		DESTI_STATE = v_desti_state;
		DESTI_GEO = v_desti_geo;
		to_inr_conv = v_inrconv;
		return p_error;

	}

	public String cocheckline(String P_PLAN, String user_id, Connection conn, Connection conndb2) {
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String p_error = "";
		String v_agent_comm = "";
		String v_hsncode = "";
		String v_igst_per = "";
		String v_sgst_per = "";
		String v_cgst_per = "";
		double v_gr_Amt = 0;
		ResultSet rs3 = null;

		String v_exp_type = "";
		String v_pch = "";
		String v_unms = "";
		String v_comm = "";
		String v_mmitgr = "";

		String v_auom = "";
		int v_err2 = 0;
		TXZ050MI txz050mi  = null;
		MDBREADMI mdbreadmi = null;

		try {             

			String Sql0 = "delete from ei_get_coline where  plan_no=? and userid=? ";
			pst1 = conn.prepareStatement(Sql0);
			pst1.setString(1, P_PLAN);
			pst1.setString(2, user_id);
			int i = pst1.executeUpdate();
			//   System.out.println("ok-1"); 
			String Sql1 = " select a.company_code, co_numb,co_line,item_numb,uom,conv_factr,item_desc,sum(((nvl(plan_qnty,0)+nvl(extra_qnty,0))-nvl(short_close_qty,0))) plan_qnty "
					+ " from seplweb.pr_ship_plan_master a,seplweb.pr_ship_plan_detail b where a.plan_numb=b.plan_numb and  a.plan_numb=? and "
					+ " (nvl(plan_qnty,0)+nvl(extra_qnty,0)-nvl(short_close_qty,0))>0 group by a.company_code, co_numb,co_line,item_numb,uom,conv_factr,item_desc";
			pst = conn.prepareStatement(Sql1);
			pst.setString(1, P_PLAN);
			rs = pst.executeQuery();
			while (rs.next()) {
				String Sql2 = " select  oafaci,oaortp,obsapr,obnepr,obdip1,obcofa,obalun,obspun,oacuor bpo,oaoref sty "
						+ " from m3fdbprd.ooline a,m3fdbprd.oohead b where oacono=obcono and oaorno=oborno and "
						+ " obcono=? and oborno=? and obponr=? and obitno=? ";
				pst1 = conndb2.prepareStatement(Sql2);
				pst1.setString(1, rs.getString("company_code"));
				pst1.setString(2, rs.getString("co_numb"));
				pst1.setString(3, rs.getString("co_line"));
				pst1.setString(4, rs.getString("item_numb"));
				rs1 = pst1.executeQuery();
				while (rs1.next()) {

					if (rs1.getString("obdip1") != null && rs1.getString("obdip1").length() > 0) {
						//(round(M.plan_qnty/nvl(M.conv_factr,1),2)*nvl(N.obsapr,0))*nvl(N.obdip1,0)/100;
						String a = roundToDecimals(Double.parseDouble(rs.getString("plan_qnty")) / Double.parseDouble(rs.getString("conv_factr") != null ? rs.getString("conv_factr") : "1"), 2);
						double b = Double.parseDouble(rs1.getString("obsapr") != null ? rs1.getString("obsapr") : "0");
						double c = Double.parseDouble(rs1.getString("obdip1") != null ? rs1.getString("obdip1") : "0") / 100;
						v_gr_Amt = Double.parseDouble(a) * b * c;
					}
					String k = roundToDecimals(Double.parseDouble(rs1.getString("obsapr") != null ? rs1.getString("obsapr") : "0"), 2);
					String l = roundToDecimals(Double.parseDouble(rs1.getString("obnepr") != null ? rs1.getString("obnepr") : "0"), 2);
					double m = Double.parseDouble(rs1.getString("obdip1") != null ? rs1.getString("obdip1") : "0");
					if (!k.equals(l) && m == 0) {
						String a = roundToDecimals(Double.parseDouble(rs.getString("plan_qnty")) / Double.parseDouble(rs.getString("conv_factr") != null ? rs.getString("conv_factr") : "1"), 2);
						double b = Double.parseDouble(rs1.getString("obsapr") != null ? rs1.getString("obsapr") : "0");
						double c = Double.parseDouble(rs1.getString("obnepr") != null ? rs1.getString("obnepr") : "0");
						v_gr_Amt = Double.parseDouble(a) * b - c;
					}

					if (!(rs.getString("uom").trim()).equals(rs1.getString("obspun").trim())) {
						v_err2 = 1;
					}

					if (!(rs1.getString("OBALUN").trim()).equals(rs1.getString("OBSPUN").trim())) {
						v_err2 = 1;
					}


					String Sql3 = "select mmprgp,mmbuar,mmunms,mmcfi5,MMGRTI,trim(mmacrf) mmacrf,mmitgr  from m3fdbprd.mitmas "
							+ " where mmcono=111 and mmitno=? ";
					pst2 = conndb2.prepareStatement(Sql3);
					pst2.setString(1, rs.getString("item_numb"));
					rs2 = pst2.executeQuery();
					while (rs2.next()) {
						List l1 = new ArrayList();
						l1.add("TRM");
						l1.add("FAB");
						l1.add("FTP");
						l1.add("FFC");
						l1.add("TTP");
						l1.add("TFS");

						v_exp_type = rs2.getString("mmprgp");

						v_mmitgr = rs2.getString("mmitgr");
						// v_pch      =rs2.getString("mmbuar"); stop on 19/10-2016 changed by sheetal.
						v_pch = rs2.getString("mmacrf");
						v_unms = rs2.getString("mmunms");
						//  v_comm    =rs2.getString("mmcfi5");
						v_comm = rs2.getString("MMGRTI");
						if (!v_pch.equals(rs2.getString("mmacrf"))) {
							p_error = "More the One PCH Check Item Master";
						} else {
							if (!l1.contains(v_exp_type) && !l1.contains(rs2.getString("mmprgp")) && !v_exp_type.equals(rs2.getString("mmprgp"))) {
								p_error = "More the  one Proc. Group Item Master";
							}
							//   if(!v_comm.equals(rs2.getString("mmcfi5")))
							if (!v_comm.equals(rs2.getString("MMGRTI"))) {
								p_error = "Commission should be equal for all Items.";
							}
						}

						//// PCH change for Sample

						if (v_exp_type.equals("YAN") || v_exp_type.equals("FAN") || v_exp_type.equals("TRN") || v_exp_type.equals("GMN")) {
							v_pch = rs1.getString("oaortp");
						}
						///////////


						if (!rs1.getString("obalun").equals(rs2.getString("mmunms"))) {
							String Sql4 = "select mucofa, mudmcf, muaus2,muaus9  from m3fdbprd.mitaun "
									+ " where mucono = 111 and muitno = ? and MUAUTP = 1 and MUALUN = ? ";
							pst3 = conndb2.prepareStatement(Sql4);
							pst3.setString(1, rs.getString("item_numb"));
							pst3.setString(2, rs.getString("uom"));
							rs3 = pst3.executeQuery();
							while (rs3.next()) {
								if (rs3.getString("muaus2").equals("1")) {
									if (rs.getDouble("conv_factr") != rs3.getDouble("mucofa")) {
										p_error = "..... Set Pcs Qty  Mismatch ......";
									}
								} else {
									p_error = ".....Alternate UOM Not applicable in Movex ......";
								}
								v_auom = rs3.getString("mudmcf");
							}
							if (v_pch == null || v_pch.length() == 0 || v_exp_type == null || v_exp_type.length() == 0) {
								p_error = "Check PCH/Exp Type Blank..........";
							}

							if (v_auom.equals("0")) {
								p_error = ".....Alternate UOM Not Define ......";
							} else if (!v_auom.equals("1")) {
								p_error = ".....Alternate UOM Factor mismatch......";
							}
						}

						String Sqlfn = "select  khpope v_comm from    m3fdbprd.MCPRCO  c "
								+ " where c.khfrdt = (select max(y.khfrdt) from  m3fdbprd.MCPRCO y "
								+ " where c.khcono = y.khcono and c.khfaci=y.khfaci and c.khobv1 = y.khobv1 and"
								+ " KHFRDT<=? ) and c.KHcono=111 and  khfaci =? and khelco = 'E07'  and khobv1 =? ";
						pst3 = conndb2.prepareStatement(Sqlfn);

						Date sysdate = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						String fromdate = sdf.format(sysdate);
						pst3.setString(1, fromdate);
						pst3.setString(2, rs1.getString("oafaci"));
						pst3.setString(3, v_comm);


						rs3 = pst3.executeQuery();
						while (rs3.next()) {
							v_agent_comm = rs3.getString("v_comm");
						}
						if (v_pch.equals("LSO")) {
							v_agent_comm = null;
						}


						String qtyins = roundToDecimals(Double.parseDouble(rs.getString("plan_qnty")) / Double.parseDouble(rs.getString("conv_factr") != null ? rs.getString("conv_factr") : "1"), 2);
						mdbreadmi = new MDBREADMI();
						mdbreadmi.connect();
						mdbreadmi.SetLstMaxRec();
						MDBREADMIGetMITFAC00Bean mDBREADMIGetMITFAC00Bean = mdbreadmi.GetMITFAC00(rs1.getString("oafaci"), rs.getString("item_numb"));

						if (mDBREADMIGetMITFAC00Bean != null) {
							v_hsncode = mDBREADMIGetMITFAC00Bean.getCSNO();
						}
						if(mdbreadmi!=null){
							mdbreadmi.destroyMI();
							mdbreadmi = null;
						}

						//  DESTI_GEO=300000001;
						//  DESTI_STATE="HR"; 
						//  inv_state="KA";  

						if (sdate >= 20170701)
						{
							if (CRNCY_CODE.trim().equals("INR"))  {
								txz050mi = new TXZ050MI();
								txz050mi.connect();
								TXZ050MIGetTaxRateBean mTXZ050MIGetTaxRateBean = txz050mi.GetTaxRate("111", rs1.getString("oafaci"), Integer.toString(DESTI_GEO), "1", "2", inv_state, DESTI_STATE, v_hsncode, "", Integer.toString(sdate));


								if (mTXZ050MIGetTaxRateBean != null) {
									v_cgst_per = mTXZ050MIGetTaxRateBean.getTAX1();
									v_sgst_per = mTXZ050MIGetTaxRateBean.getTAX2();
									v_igst_per = mTXZ050MIGetTaxRateBean.getTAX3();
								}
								if(txz050mi!=null){
									txz050mi.destroyMI();
									txz050mi = null;
								}
							}
							else
							{
								txz050mi = new TXZ050MI();
								txz050mi.connect();

								Date sysdate1 = new Date();
								SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
								String fromdate1 = sdf1.format(sysdate1);

								TXZ050MIGetTaxRateBean mTXZ050MIGetTaxRateBean = txz050mi.GetTaxRate("111", rs1.getString("oafaci"),INV_GEO, "1", "4",v_hsncode,"","", "",fromdate1);


								if (mTXZ050MIGetTaxRateBean != null) {
									v_cgst_per = mTXZ050MIGetTaxRateBean.getTAX1();
									v_sgst_per = mTXZ050MIGetTaxRateBean.getTAX2();
									v_igst_per = mTXZ050MIGetTaxRateBean.getTAX3();
								}
								if(txz050mi!=null){
									txz050mi.destroyMI();
									txz050mi = null;
								}




							}
						}


						if (sdate >= 20170701) {
							if (v_hsncode == null || v_hsncode.length() == 0) {
								p_error = "HSN Code Not Updated..Please contact Logistics/Sourcing...";
							}

							if (CRNCY_CODE.trim().equals("INR") && DESTI_GEO < 1) {
								p_error = "Buyer Geographical Code Not Defined....";
							}
							if (CRNCY_CODE.trim().equals("INR") && !inv_state.equals(DESTI_STATE) && (Double.parseDouble(v_igst_per) < 1)) {
								p_error = "Error with GST Tax Structure...";
							}
							if (CRNCY_CODE.trim().equals("INR") && inv_state.equals(DESTI_STATE) && (Double.parseDouble(v_cgst_per) < 1) && (Double.parseDouble(v_sgst_per) < 1)) {
								p_error = "Error with GST Tax Structure...";
							}

						}


						String Sqlinsert = "insert into exports.ei_get_coline (PLAN_NO,CO_NO,CO_LINE,ITEM_NO, SET_PCS,QTY_PCS,ITEM_DESC,FACILITY,"
								+ " BPO, STY,UOM,ITEM_QTY,SALE_PR,NET_PRICE,GR_DECL_PER,GR_DECL_AMT, PCH, EXP_TYPE , COMM_PER,mmitgr,hsn_code,igst_per,cgst_per,sgst_per,USERID,tdate)"
								+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";

						pst3 = conn.prepareStatement(Sqlinsert);
						pst3.setString(1, P_PLAN);
						pst3.setString(2, rs.getString("co_numb"));
						pst3.setString(3, rs.getString("co_line"));
						pst3.setString(4, rs.getString("item_numb"));
						pst3.setString(5, rs.getString("conv_factr"));
						pst3.setString(6, rs.getString("plan_qnty"));
						pst3.setString(7, rs.getString("item_desc"));
						pst3.setString(8, rs1.getString("oafaci"));
						pst3.setString(9, rs1.getString("bpo"));
						pst3.setString(10, rs1.getString("sty"));
						pst3.setString(11, rs.getString("uom"));
						pst3.setString(12, qtyins);
						pst3.setString(13, rs1.getString("obsapr"));
						pst3.setString(14, rs1.getString("obnepr"));
						pst3.setString(15, rs1.getString("obdip1"));
						pst3.setString(16, String.valueOf(v_gr_Amt));
						pst3.setString(17, v_pch);
						pst3.setString(18, v_exp_type);
						pst3.setString(19, v_agent_comm);
						pst3.setString(20, v_mmitgr);
						pst3.setString(21, v_hsncode);
						pst3.setString(22, v_igst_per);
						pst3.setString(23, v_cgst_per);
						pst3.setString(24, v_sgst_per);
						pst3.setString(25, user_id);
						int a = pst3.executeUpdate();
						conn.commit();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			try {
				if(mdbreadmi!=null){
					mdbreadmi.destroyMI();
					mdbreadmi = null;
				}
				if (txz050mi != null) {
					txz050mi.destroyMI();
					txz050mi = null;
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (pst2 != null) {
					pst2.close();
				}
				if (pst1 != null) {
					pst1.close();
				}
				if (pst != null) {
					pst.close();
				}


			} catch (Exception ee) {
			}
		}

		return p_error;

	} 

	public String prelineprice() throws SQLException {

		InvLineList = new ArrayList();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String planNo=request. getParameter("plan_no");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo");
		String desti_cntry=request. getParameter("desti_cntry");
		String inv_date=request. getParameter("inv_date");

		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}

		if(desti_cntry!=null){
			DESTI_CNTRY=desti_cntry;
		}

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		Connection conn = null;
		Connection conndb2 = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		PreparedStatement stat1 = null;
		ResultSet result1 = null;
		try {


			try {
				conn = new connection().getConnection();
				conn.setAutoCommit(false);

				conndb2 = new connectiondb2().getConnection();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			int flag = 0;

			SHIP_TYPE_LIST.add("DBK");
			SHIP_TYPE_LIST.add("DEEC");
			SHIP_TYPE_LIST.add("DBKDEEC");
			SHIP_TYPE_LIST.add("DOMESTIC");
			SHIP_TYPE_LIST.add("SAMPLE");
			String PLAN_DESC = null;
			stat = conn.prepareStatement("select scheme_code||'-'||short_desc SCH_desc,scheme_code from  ei_dbk_scheme order by scheme_code ");
			result = stat.executeQuery();
			while (result.next()) {
				SCHEME_CODE_LIST.add(new GetListBean(result.getString("scheme_code"), result.getString("SCH_desc")));
			}
			result.close();
			stat.close();
			int chk;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//  System.out.println("Line Start ....." + dateFormat.format(new Date()));
			if (GetDataFlag != null && GetDataFlag.equals("YES")) {

				stat = conn.prepareStatement("select CO_NO,CO_LINE,ITEM_NO, SET_PCS,QTY_PCS,ITEM_DESC,FACILITY,BPO, STY,UOM,ITEM_QTY,SALE_PR,NET_PRICE,GR_DECL_PER,GR_DECL_AMT,GR_DECL_PER,PCH,EXP_TYPE,COMM_PER,mmitgr,hsn_code,igst_per,cgst_per,sgst_per from exports.ei_get_coline where plan_no=? and userid=? order by co_no,co_line");
				stat.setString(1, plan_no);
				stat.setString(2, usrid);
				result = stat.executeQuery();
				double fob_fc = 0.0;
				Double invkgs = 0.0;
				String invcat = "";
				String invdesc = "";
				String invdbk = "";
				String invstr = "";
				String invstrm = "";
				String invmadefor = "";
				String invrosl = "";
				String invscheme = "";
				String invhs = "";
				BigDecimal miscprice = new BigDecimal("0.0");
				BigDecimal mhngrcost = new BigDecimal("0.0");
				BigDecimal mtagcost = new BigDecimal("0.0");
				BigDecimal adjustfc = new BigDecimal("0.0");
				BigDecimal mprice = new BigDecimal("0.0");
				while (result.next()) {
					mprice = result.getBigDecimal("sale_pr");

					stat1 = conn.prepareStatement("select b.qty_kgs,b.category,b.description,b.dbk_slno,b.str_slno,nvl(price_misc,0) price_misc,str_misc,made_for,adjust_fc,rosl_slno,scheme_code,hscode1,nvl(hngr_cost,0) hngr_cost,nvl(tag_cost,0) tag_cost from ei_endors_mast a,ei_endors_dtls b where  a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.plan_no=? and  co_no=? and co_line=?   ");
					stat1.setString(1, plan_no);
					stat1.setString(2, result.getString("co_no"));
					stat1.setString(3, result.getString("co_line"));
					result1 = stat1.executeQuery();
					while (result1.next()) {
						invkgs = result1.getDouble("qty_kgs");
						invcat = result1.getString("category");
						invdesc = result1.getString("description");
						invdbk = result1.getString("dbk_slno");
						invstr = result1.getString("str_slno");
						invstrm = result1.getString("str_misc");
						invmadefor = result1.getString("made_for");
						adjustfc = result1.getBigDecimal("adjust_fc");

						miscprice = result1.getBigDecimal("price_misc");
						mhngrcost = result1.getBigDecimal("hngr_cost");
						mtagcost = result1.getBigDecimal("tag_cost");
						invrosl = result1.getString("rosl_slno");
						invscheme = result1.getString("scheme_code");
						invhs = result1.getString("hscode1");
						mprice = mprice.subtract(miscprice);

					}
					fob_fc = result.getInt("item_qty") * result.getDouble("sale_pr");
					InvLineList.add(new PreInvLineBean("", result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), result.getString("uom"), result.getDouble("item_qty"), invkgs, mprice, miscprice, adjustfc, result.getBigDecimal("NET_PRICE"), roundTwoDecimals(fob_fc), result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), invdbk, invstr, invstrm, result.getString("bpo"), result.getString("sty"), invcat, invdesc, invmadefor, result.getBigDecimal("sale_pr"), result.getString("exp_type"), result.getString("ITEM_DESC"), invrosl, invscheme, invhs, mhngrcost, mtagcost, result.getString("hsn_code"), result.getDouble("IGST_PER"), result.getDouble("CGST_PER"), result.getDouble("SGST_PER")));

				}
			} else {

				stat = conn.prepareStatement("select sr_no,co_no,co_line,item_no,unit,qty_endors,qty_kgs,price_fc-nvl(adjust_fc,0) price_fc,price_misc,hngr_cost,tag_cost,adjust_fc,net_price,made_for,temp_cat,"
						+ " qty_endors*(price_fc+nvl(price_misc,0)) fob_fc,gr_decl_amt,GR_DECL_PER,dbk_slno,str_slno,str_misc,pre_print_no,token_no,category,description,rosl_slno,scheme_code,HSCODE1,nvl(hngr_cost,0) hngr_cost,nvl(tag_cost,0) tag_cost,HSN_CODE,IGST_PER,CGST_PER,SGST_PER "
						+ " from ei_endors_dtls a  where year=? and company=? and inv_no=?  order by sr_no,co_no,co_line");
				stat.setString(1, YEAR);
				stat.setString(2, COMPANY);
				stat.setString(3, INV_NO);
				result = stat.executeQuery();
				while (result.next()) {

					BigDecimal movexpr = new BigDecimal("0.00");
					stat1 = conndb2.prepareStatement(" select obsapr from M3FDBPRD.ooline where obcono=111 and oborno=? and obponr=?");
					stat1.setString(1, result.getString("co_no").trim());
					stat1.setString(2, result.getString("co_line").trim());
					result1 = stat1.executeQuery();

					if (result1.next()) {

						movexpr = result1.getBigDecimal("obsapr");
					}
					if (stat1 != null) {
						stat1.close();
					}
					if (result1 != null) {
						result1.close();
					}

					stat1 = conn.prepareStatement("select substr(item_desc,21,50) item_desc from seplweb.pr_ship_plan_detail where plan_numb=? and co_numb=? and co_line=?");
					stat1.setString(1, plan_no);
					stat1.setString(2, result.getString("co_no").trim());
					stat1.setString(3, result.getString("co_line").trim());
					result1 = stat1.executeQuery();
					while (result1.next()) {
						PLAN_DESC = result1.getString("item_desc");
					}
					if (stat1 != null) {
						stat1.close();
					}
					if (result1 != null) {
						result1.close();
					}

					//  System.out.println(" list  #" + result.getString("sr_no") + "  " + dateFormat.format(new Date()));
					InvLineList.add(new PreInvLineBean(result.getString("sr_no"), result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), result.getString("unit"), result.getDouble("qty_endors"), result.getDouble("qty_kgs"), result.getBigDecimal("price_fc"), result.getBigDecimal("price_misc"), result.getBigDecimal("adjust_fc"), result.getBigDecimal("net_price"), roundTwoDecimals(result.getDouble("fob_fc")), result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), result.getString("dbk_slno"), result.getString("str_slno"), result.getString("str_misc"), result.getString("pre_print_no"), result.getString("token_no"), result.getString("category"), result.getString("description"), result.getString("made_for"), movexpr, result.getString("temp_cat"), PLAN_DESC, result.getString("rosl_slno"), result.getString("scheme_code"), result.getString("HSCODE1"), result.getBigDecimal("hngr_cost"), result.getBigDecimal("tag_cost"), result.getString("HSN_CODE"), result.getDouble("igst_per"), result.getDouble("cgst_per"), result.getDouble("sgst_per")));

				}

			}

			//System.out.println("Line Closed ....." + dateFormat.format(new Date()));
		} catch (Exception ee) {
			System.out.println(ee.toString());
		} finally {
			if (result != null) {
				result.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (conndb2 != null) {
				conndb2.close();
			}

		}

		return "prelineprice";
	}

	public String preinvaccr() throws SQLException {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		PreInvioiceNewDao dao=new PreInvioiceNewDao();
		String planNo=request. getParameter("plan_no");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 
		String GetDataFlag=request. getParameter("GetDataFlag");
		String inv_date=request. getParameter("inv_date");


		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		try {


			try {
				conn = new connection().getConnection();
				conn.setAutoCommit(false);

			} catch (Exception e) {
				System.out.println(e.toString());
			}
			int flag = 0;
			setACCR_TYPE_LIST(getAccessTypeList());
			/*ACCR_TYPE_LIST.add("BELT");
			ACCR_TYPE_LIST.add("DIAPER");
			ACCR_TYPE_LIST.add("SCARVES");
			ACCR_TYPE_LIST.add("SHORTS");
			ACCR_TYPE_LIST.add("SUSPENDERS");*/
			//CCR_TYPE_LIST.add("EAS-TAG"); 

			if (GetDataFlag != null && GetDataFlag.equals("YES")) {
				stat = conn.prepareStatement("select CO_NO,CO_LINE,ITEM_NO, SET_PCS,QTY_PCS,ITEM_DESC,FACILITY,BPO, STY,UOM,ITEM_QTY,SALE_PR,NET_PRICE,GR_DECL_PER,GR_DECL_AMT,PCH,EXP_TYPE,COMM_PER,mmitgr,hsn_code,igst_per,cgst_per,sgst_per from exports.ei_get_coline where plan_no=? and userid=? order by co_no,co_line");
				stat.setString(1, plan_no);
				stat.setString(2, usrid);

				result = stat.executeQuery();
				double fob_fc = 0.0;

				while (result.next()) {

					fob_fc = result.getInt("item_qty") * roundFourDecimals(result.getDouble("sale_pr"));
					InvLineList.add(new PreInvLineBean("", result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), result.getString("uom"), result.getDouble("item_qty"), result.getDouble("item_qty"), bd, bd, bd, result.getBigDecimal("NET_PRICE"), fob_fc, result.getBigDecimal("gr_decl_amt"), result.getDouble("gr_decl_amt"), null, null, null, result.getString("bpo"), result.getString("sty"), result.getString("exp_type"), result.getString("item_desc"), "", bd, result.getString("exp_type"), result.getString("item_desc"), "", "", "", bd1, bd1, result.getString("hsn_code"), result.getDouble("igst_per"), result.getDouble("cgst_per"), result.getDouble("sgst_per")));

				}
				map.put(GetDataFlag, InvLineList);
			} else {

				stat = conn.prepareStatement("select co_no,co_line,item_no,ACCR_DESC,ACCR_QTY,ACCR_PRICE,ACCR_DBKSLNO,ACCR_STRSLNO from ei_endors_ACCR_DTLS where year=? and company=? and inv_no=? order by ACCR_DESC,co_no,co_line");
				stat.setString(1, YEAR);
				stat.setString(2, COMPANY);
				stat.setString(3, INV_NO);
				result = stat.executeQuery();
				String ACCR_DESC=null;
				while (result.next()) {
					AccrLineList.add(new PreInvAccrBean(result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), result.getString("ACCR_DESC"), result.getString("ACCR_DBKSLNO"), result.getString("ACCR_STRSLNO"), result.getString("ACCR_QTY"), result.getString("ACCR_price")));
				}
				map.put("No", AccrLineList);
				//map.remove("sessUserId");
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
		} finally {
			if (result != null) {
				result.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return "preinvaccr";
	}

	public String removeItem(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String accType=request. getParameter("accType");
		String rowNo=request. getParameter("row");
		String planNo=request. getParameter("plan_no");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 
		String inv_date=request. getParameter("inv_date");
		List list=(List)getMap().get(accType);
		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}


		if(rowNo!=null){
			list.remove(Integer.parseInt(rowNo));
		}
		getMap().put(accType, list);
		setACCR_TYPE_LIST(getAccessTypeList());
		return "preinvaccr";
	}
	public String loadAllAccessoriesItems() throws SQLException{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String planNo=request. getParameter("plan_no");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 
		String GetDataFlag=request. getParameter("GetDataFlag");
		String accType=request. getParameter("accType");
		String inv_date=request. getParameter("inv_date");
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");

		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		try {
			conn = new connection().getConnection();
			conn.setAutoCommit(false);
			int flag = 0;
			setACCR_TYPE_LIST(getAccessTypeList());

			if (GetDataFlag != null && GetDataFlag.equals("NO")) {
				stat = conn.prepareStatement("select CO_NO,CO_LINE,ITEM_NO, SET_PCS,QTY_PCS,ITEM_DESC,FACILITY,BPO, STY,UOM,ITEM_QTY,SALE_PR,NET_PRICE,GR_DECL_PER,GR_DECL_AMT,PCH,EXP_TYPE,COMM_PER,mmitgr,hsn_code,igst_per,cgst_per,sgst_per from exports.ei_get_coline where plan_no=? and userid=? order by co_no,co_line");
				stat.setString(1, plan_no);
				stat.setString(2, usrid);
				//stat.setString(2, "732225");
				result = stat.executeQuery();
				double fob_fc = 0.0;

				while (result.next()) {
					flag=1;
					fob_fc = result.getInt("item_qty") * roundFourDecimals(result.getDouble("sale_pr"));
					//InvLineList.add(new PreInvLineBean("", result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), result.getString("uom"), result.getDouble("item_qty"), result.getDouble("item_qty"), bd, bd, bd, result.getBigDecimal("NET_PRICE"), fob_fc, result.getBigDecimal("gr_decl_amt"), result.getDouble("gr_decl_amt"), null, null, null, result.getString("bpo"), result.getString("sty"), result.getString("exp_type"), result.getString("item_desc"), "", bd, result.getString("exp_type"), result.getString("item_desc"), "", "", "", bd1, bd1, result.getString("hsn_code"), result.getDouble("igst_per"), result.getDouble("cgst_per"), result.getDouble("sgst_per")));
					AccrLineList.add(new PreInvAccrBean(result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), getSearchBean().getAccType(), getSearchBean().getDbkSlNo(), getSearchBean().getStrSlNo(), result.getString("item_qty"),getSearchBean().getPrice() ));

				}
				map.put(getSearchBean().getAccType(), AccrLineList);
			} 
			if(AccrLineList.size()==0||flag==1) {
				stat = conn.prepareStatement("select co_no,co_line,item_no,ACCR_DESC,ACCR_QTY,ACCR_PRICE,ACCR_DBKSLNO,ACCR_STRSLNO from ei_endors_ACCR_DTLS where year=? and company=? and inv_no=?  and ACCR_DESC=? order by ACCR_DESC,co_no,co_line");
				stat.setString(1, YEAR);
				stat.setString(2, COMPANY);
				stat.setString(3, INV_NO);
				stat.setString(4,accType);
				result = stat.executeQuery();
				while (result.next()) {
					flag=2;
					AccrLineList.add(new PreInvAccrBean(result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), getSearchBean().getAccType(), getSearchBean().getDbkSlNo(), getSearchBean().getStrSlNo(), result.getString("ACCR_QTY"),getSearchBean().getPrice() ));
				}
				map.put(getSearchBean().getAccType(), AccrLineList);
			}
			if(AccrLineList.size()==0||flag==1){
				stat = conn.prepareStatement("select sr_no,co_no,co_line,item_no,qty_endors"
						+ " from ei_endors_dtls a  where year=? and company=? and inv_no=?  order by sr_no,co_no,co_line");
				stat.setString(1, YEAR);
				stat.setString(2, COMPANY);
				stat.setString(3, INV_NO);
				result = stat.executeQuery();
				while (result.next()) {
					AccrLineList.add(new PreInvAccrBean(result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), getSearchBean().getAccType(), getSearchBean().getDbkSlNo(), getSearchBean().getStrSlNo(), result.getString("qty_endors"),getSearchBean().getPrice() ));
				}
				map.put(getSearchBean().getAccType(), AccrLineList);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
		} finally {
			if (result != null) {
				result.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return "preinvaccr";
	}
	private List getAccessTypeList(){
		ACCR_TYPE_LIST.add("BELT");
		ACCR_TYPE_LIST.add("DIAPER");
		ACCR_TYPE_LIST.add("SCARVES");
		ACCR_TYPE_LIST.add("SHORTS");
		ACCR_TYPE_LIST.add("SUSPENDERS");
		return ACCR_TYPE_LIST;
	}
	private void initilizeKey(){
		COMPANY=parameter.getCompany();
		YEAR=parameter.getYear();
		INV_NO=parameter.getInvoiceNo();
		inv_date=parameter.getInvoiceDate();
	}
	public String saveAccessoriesItem() throws SQLException{
		PreparedStatement stat=null;
		Connection conn = null;
		String tempKey=null;
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String planNo=request. getParameter("plan_no");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 
		String inv_date=request. getParameter("inv_date"); 
		Set<String>keys=getMap().keySet();
		keys.remove("sessUserId");
		try{
			parameter.setYear(year);
			parameter.setCompany(company);
			parameter.setInvoiceNo(invNo);
			parameter.setInvoiceDate(inv_date);
			conn = new connection().getConnection();
			conn.setAutoCommit(false);
			deleteExistItems(company,invNo,year,conn);
			for(String key:keys){
				if(getMap().get(key) instanceof List){
					setAccrLineList(saveItems((List)getMap().get(key),key,conn));
					map.put(key,getAccrLineList());
				}
			}
			initilizeKey();
			setACCR_TYPE_LIST(getAccessTypeList());
			addActionError("Data saved Successfully");
		}catch(SQLException sql){
			addActionError(sql.getLocalizedMessage());
			sql.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
			addActionError(ex.getLocalizedMessage());
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		return "preinvaccr";
	}

	private List saveItems(List ACCR_CO_NO,String key,Connection conn) throws SQLException {
		System.out.println(ACCR_CO_NO);
		PreparedStatement stat=null;
		PreInvAccrBean bean=null;
		boolean result=false;
		try{
			if (ACCR_CO_NO != null && ACCR_CO_NO.size() > 0) {
				for (int i = 0; i < ACCR_CO_NO.size(); i++) {
					bean=(PreInvAccrBean) ACCR_CO_NO.get(i);
					stat = conn.prepareStatement("insert into ei_endors_accr_dtls (YEAR,COMPANY,INV_NO,CO_NO,CO_LINE,ITEM_NO, ACCR_DESC, ACCR_QTY, ACCR_PRICE, ACCR_DBKSLNO, ACCR_STRSLNO,SEH_USER,TDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)");
					stat.setString(1, parameter.getYear());
					stat.setString(2, parameter.getCompany());
					stat.setString(3, parameter.getInvoiceNo());
					stat.setString(4, bean.getACCR_CO_NO().trim());
					stat.setString(5, bean.getACCR_CO_LINE().trim());
					stat.setString(6, bean.getACCR_ITEM_NO().trim());
					stat.setString(7, bean.getACCR_TYPE().trim());
					stat.setString(8, bean.getACCR_QTY().trim());
					stat.setString(9, bean.getACCR_PRICE().trim());
					stat.setString(10, bean.getACCR_DBKSLNO().trim());
					stat.setString(11, bean.getACCR_STRSLNO());
					stat.setString(12, getuserId());
					stat.executeUpdate();
				}
				conn.commit();
			}
		}catch(SQLException sqlex){
			conn.rollback();
			sqlex.printStackTrace();
		}catch(Exception ex){
			conn.rollback();
			ex.printStackTrace();
		}finally{
			if(stat!=null){
				stat.close();
			}

		}
		return ACCR_CO_NO;
	}
	private void deleteExistItems(String year,String company,String invo,Connection con) throws SQLException {
		String sql="delete from ei_endors_accr_dtls where year=? and company=? and inv_no=? ";
		PreparedStatement  stmt=null;
		stmt=con.prepareStatement(sql);
		stmt.setString(1, parameter.getYear());
		stmt.setString(2, parameter.getCompany());
		stmt.setString(3, parameter.getInvoiceNo());
		stmt.executeUpdate();
	}
	private String getuserId(){
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		return usrid;
	}
	public String preexcise() throws SQLException {

		//List unitList = new ArrayList<UnitBean>();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		PreInvioiceNewDao dao=new PreInvioiceNewDao();
		String planNo=request. getParameter("planNo");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 
		String GetDataFlag=request. getParameter("GetDataFlag");
		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}
		InvLineList = new ArrayList<PreInvLineBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		Connection conn = null;
		PreparedStatement stat = null;
		PreparedStatement stat1 = null;
		ResultSet result1 = null;
		ResultSet result = null;
		try {


			try {
				conn = new connection().getConnection();
				conn.setAutoCommit(false);

			} catch (Exception e) {
				System.out.println(e.toString());
			}
			int flag = 0;
			String PLAN_DESC = "";

			EXCISE_UNIT_LIST.add("U2");
			EXCISE_UNIT_LIST.add("U14");
			EXCISE_UNIT_LIST.add("U99");


			if (GetDataFlag != null && GetDataFlag.equals("YES")) {
				stat = conn.prepareStatement("select CO_NO,CO_LINE,ITEM_NO, SET_PCS,QTY_PCS,ITEM_DESC,FACILITY,BPO, STY,UOM,ITEM_QTY,SALE_PR,NET_PRICE,GR_DECL_PER,GR_DECL_AMT,GR_DECL_PER,PCH,EXP_TYPE,COMM_PER,mmitgr,hsn_code,igst_per,cgst_per,sgst_per from exports.ei_get_coline where plan_no=? and userid=? order by co_no,co_line");
				stat.setString(1, plan_no);
				stat.setString(2, usrid);
				result = stat.executeQuery();
				double fob_fc = 0.0;
				while (result.next()) {
					Double vmrp = 0.0;
					String vhscode = "";
					stat1 = conn.prepareStatement("select MRP_RATE from ei_endors_DTLS where year=? and company=? and inv_no=? and co_no=? and co_line=? ");
					stat1.setString(1, YEAR);
					stat1.setString(2, COMPANY);
					stat1.setString(3, INV_NO);
					stat1.setString(4, result.getString("co_no"));
					stat1.setString(5, result.getString("co_line"));
					result1 = stat1.executeQuery();
					while (result1.next()) {
						vmrp = result1.getDouble("MRP_RATE");
					}
					fob_fc = result.getInt("item_qty") * result.getDouble("sale_pr");

					InvLineList.add(new PreInvLineBean("", result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), result.getString("uom"), result.getDouble("item_qty"), vmrp, result.getBigDecimal("sale_pr"), bd, bd1, result.getBigDecimal("NET_PRICE"), roundTwoDecimals(fob_fc), result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_PER")), null, null, null, result.getString("bpo"), result.getString("STY"), result.getString("exp_type"), result.getString("item_desc"), "", bd, result.getString("exp_type"), result.getString("item_desc"), "", "", "", bd1, bd1, result.getString("hsn_code"), result.getDouble("igst_per"), result.getDouble("cgst_per"), result.getDouble("sgst_per")));

				}
			} else {

				stat = conn.prepareStatement("select excise_unit,ex_inv_slno,to_char(ex_inv_date,'dd/mm/yyyy') ex_inv_date,sr_no,a.co_no,a.co_line,a.item_no,unit,qty_endors,qty_kgs,price_fc-nvl(adjust_fc,0) price_fc,nvl(price_misc,0) price_misc,adjust_fc,net_price,made_for,temp_cat,"
						+ " qty_endors*(price_fc+nvl(price_misc,0)) fob_fc,gr_decl_amt,GR_DECL_PER,dbk_slno,str_slno,str_misc,pre_print_no,token_no,category,description,MRP_RATE,rosl_slno,SCHEME_CODE,hscode1,nvl(hngr_cost,0) hngr_cost,nvl(tag_cost,0) tag_cost,hsn_code,igst_per,cgst_per,sgst_per "
						+ " from ei_endors_mast a1,ei_endors_dtls a where a1.year=a.year and a1.company=a.company and a1.inv_no=a.inv_no and a1.year=? and a1.company=? and a1.inv_no=? order by sr_no,a.co_no,a.co_line");
				stat.setString(1, YEAR);
				stat.setString(2, COMPANY);
				stat.setString(3, INV_NO);
				result = stat.executeQuery();
				while (result.next()) {
					EXCISE_UNIT = result.getString("excise_unit");
					ex_inv_slno = result.getString("ex_inv_slno");
					ex_inv_date = result.getString("ex_inv_date");

					InvLineList.add(new PreInvLineBean(result.getString("sr_no"), result.getString("co_no"), result.getString("co_line"), result.getString("item_no"), result.getString("unit"), result.getDouble("qty_endors"), result.getDouble("MRP_RATE"), result.getBigDecimal("price_fc"), result.getBigDecimal("price_misc"), result.getBigDecimal("adjust_fc"), result.getBigDecimal("net_price"), roundTwoDecimals(result.getDouble("fob_fc")), result.getBigDecimal("gr_decl_amt"), roundTwoDecimals(result.getDouble("gr_decl_per")), result.getString("dbk_slno"), result.getString("str_slno"), result.getString("str_misc"), result.getString("pre_print_no"), result.getString("token_no"), result.getString("category"), result.getString("description"), result.getString("made_for"), bd, result.getString("temp_cat"), PLAN_DESC, result.getString("rosl_slno"), result.getString("SCHEME_CODE"), result.getString("hscode1"), result.getBigDecimal("hngr_cost"), result.getBigDecimal("tag_cost"), result.getString("hsn_code"), result.getDouble("igst_per"), result.getDouble("cgst_per"), result.getDouble("sgst_per")));

				}


				stat = conn.prepareStatement("select *  from pa_auth_mast where user_id=? and prog_name='EXCISE_CANCEL' ");
				stat.setString(1, usrid);
				result = stat.executeQuery();
				if (result.next() == true) {
					EX_GEN_ALLOW = "YES";
				}
				stat = conn.prepareStatement("select *  from pa_auth_mast where user_id=? and prog_name='EXCISE_RATE' ");
				stat.setString(1, usrid);
				result = stat.executeQuery();
				if (result.next() == true) {
					EX_RATE_ALLOW = "YES";
				}
				if (ex_inv_slno == null && EX_GEN_ALLOW.equals("YES") && EXCISE_UNIT == null) {
					EX_GEN_ALLOW = "YES";
				} else {
					EX_GEN_ALLOW = "NO";
				}

			}



		} catch (Exception ee) {
			System.out.println(ee.toString());
		} finally {
			if (result != null) {
				result.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return "preexcise";
	}

	public String[] checkDuplicateDeliveryNo(PreInvoiceHeader header,Connection conn) throws SQLException{
		PreparedStatement stat;
		ResultSet result;
		String results[]=new String[2];
		stat = conn.prepareStatement("select del_numb  from seplweb.pr_ship_plan_detail where inv_no is not null and del_numb in (select distinct del_numb from seplweb.pr_ship_plan_detail where del_numb not in (999999,888888) and plan_numb=? and nvl(plan_qnty,0)+nvl(extra_qnty,0)-nvl(short_close_qty,0)>0) and plan_numb <> ?");
		stat.setString(1, header.getPlanNo());
		stat.setString(2, header.getPlanNo());
		result = stat.executeQuery();
		if(result.next()){
			results[0]="true";
			results[1]=result.getString("del_numb");
		}
		if(stat!=null){
			stat.close();
		}
		if(result!=null){
			result.close();
		}
		stat=null;
		result=null;
		return results;

	}
	public String[] checkCOApproval(PreInvoiceHeader header,Connection conn)throws SQLException{
		PreparedStatement stat;
		ResultSet result;
		String results[]=new String[2];
		stat = conn.prepareStatement("select exports.FUN_COAPRV115(?,?,?) aa from dual ");
		stat.setString(1, header.getYear());
		stat.setString(2, header.getCompany());
		stat.setString(3, header.getInvNo());
		result = stat.executeQuery();
		if(result.next()){
			results[0]="true";
			results[1]=result.getString("aa");
		}
		if(stat!=null){
			stat.close();
		}
		if(result!=null){
			result.close();
		}
		stat=null;
		result=null;
		return results;
	}

	public String[] checkDuplicatePlanNo(PreInvoiceHeader header,Connection conn)throws SQLException{
		PreparedStatement stat;
		ResultSet result;
		String results[]=new String[2];
		stat = conn.prepareStatement("select * from ei_endors_mast where plan_no=? ");
		stat.setString(1, header.getPlanNo());
		result = stat.executeQuery();
		if(result.next()){
			results[0]="true";
			parameter.setYear(result.getString("YEAR"));
			parameter.setCompany(result.getString("COMPANY"));
			parameter.setInvoiceNo(result.getString("INV_NO"));
			parameter.setLocationCode(result.getString("location"));
		}
		if(stat!=null){
			stat.close();
		}
		if(result!=null){
			result.close();
		}
		stat=null;
		result=null;
		return results;
	}

	public int updateMaster(PreInvoiceHeader header,Connection conn)throws SQLException{
		PreparedStatement stat1;
		ResultSet result;
		String results[]=new String[2];
		stat1 = conn.prepareStatement("update ei_endors_mast set buyer=?,buyer_addr=?,cons_addr=?,NOTIFY=?,MODE_OF_SHIP=?,PLACE=?,LOADING =?,DISCHARGE =?,DESTI_CNTRY=?,PAY_TERM=?,INV_QTY=?,ENDORS_QTY =?,T_O_DATE=to_date(?,'yyyy-mm-dd'),AGENT =?,SHIP_DESC=?,COMM_PER=?,SELF_TP=?,EXP_TYPE=?,PRE_CARRIAGE=?,SURRENDER_YN=?,COST_CENTRE=?,MANUF_CODE =?,TAX_TYPE=?,TAX_PERCENT=?,"
				+ " FACILITY=?,ship_term=?,DESTI_CODE =?,FWD_CUSTOM=decode(?,'Y',sysdate,'NN',null,fwd_custom),HS_CODE =?,OH_WORK =?,TTO_DATE=decode(?,'Y',sysdate,tto_Date),AC_HOLDER =?,TAX_CODE=?,"
				+ " FWD_CODE=?,PAYMENT_TERM=?,TAX_CAL_PER =?,ETD_DATE=to_date(?,'yyyy-mm-dd'),to_inr_conv=?,CRNCY_CODE=?,LOADING_PORT=?,LCNO=?,MERCHANT_NAME=?,PPRQ_DATE=decode(?,'Y',sysdate,pprq_date),"
				+ " UPCHARGE_PER=?,DIS_CNTRY=?,CTNS=?,GRWT=?,transport_cost=?,CONSIGNEE=?,mmitgr=?,netwt=?,desti_state=?,LUT_IGST=?,desti_geo=?,inv_geo=?  where plan_no=? ");
		stat1.setString(1, header.getBuyer().getBUYER());
		stat1.setString(2, header.getBuyer().getAddressId());
		stat1.setString(3, header.getConsignee().getAddressId());
		stat1.setString(4, header.getNotify());
		stat1.setString(5, header.getShipMode());
		stat1.setString(6, header.getPlace());
		stat1.setString(7, header.getClearingPort());
		stat1.setString(8, header.getDischarge());
		stat1.setString(9, header.getDestinationCntry());
		stat1.setString(10, header.getPaymentTerm());
		stat1.setString(11, header.getInvoiceQty());
		stat1.setString(12, header.getInvoiceQty());
		if (header.getToDate()!=null && header.getToDate().length() > 8) {
			stat1.setString(13, header.getToDate().substring(0, 10));
		} else {
			stat1.setString(13, header.getToDate());
		}

		stat1.setString(14, header.getAgent());
		stat1.setString(15, header.getRemarks());
		stat1.setString(16, header.getCommision());
		stat1.setString(17, header.getInvoiceType()); 
		stat1.setString(18, header.getExpType());
		stat1.setString(19, header.getPreCarriage());
		stat1.setString(20, header.getShipCnxl());
		stat1.setString(21, header.getPch().trim());
		stat1.setString(22, header.getManufacturer());
		stat1.setString(23, TAX_TYPE);
		stat1.setString(24, header.getTaxPer());
		stat1.setString(25, header.getFacility());
		stat1.setString(26, header.getShipMode());
		stat1.setString(27, header.getDestination().trim().toUpperCase());
		stat1.setString(28, header.getFwdToCustom());
		stat1.setString(29, header.getHsCode().toUpperCase());
		stat1.setString(30, header.getOutHouse());
		stat1.setString(31, header.getTtoDate());
		stat1.setString(32, header.getAccountHolder().toUpperCase());
		stat1.setString(33, header.getTaxCode().toUpperCase());
		//    stat1.setString(35,CNTRY_ORIGIN.trim().toUpperCase());
		stat1.setString(34, header.getForwarder().toUpperCase());
		stat1.setString(35, header.getPaymentTerm());
		stat1.setString(36, header.getTaxPer());
		if (header.getEtdDate()!=null && header.getEtdDate().length() > 8) {
			stat1.setString(37, header.getEtdDate().substring(0, 10));
		} else {
			stat1.setString(37, header.getEtdDate());
		}
		stat1.setString(38, to_inr_conv);
		stat1.setString(39, header.getCurrency().trim());
		stat1.setString(40, header.getLoadingPort().trim().toUpperCase());
		stat1.setString(41, header.getLcNo());
		stat1.setString(42, header.getMerchant());

		stat1.setString(43, header.getPprqDate());
		stat1.setString(44, header.getUpcharge());
		stat1.setString(45, header.getDischargeCntry().trim().toUpperCase());
		stat1.setString(46, header.getTotalCartons());
		stat1.setString(47, header.getTotalGrossWeight());
		stat1.setString(48, header.getTransportRate());
		stat1.setString(49, header.getBuyer().getBUYER());
		stat1.setString(50, header.getInvoiceType());
		stat1.setString(51, header.getTotalNetWeight());
		stat1.setString(52, header.getBuyer().getDestinationState());
		stat1.setString(53, header.getLutIGST());
		stat1.setString(54,Integer.toString(DESTI_GEO)); 
		stat1.setString(55,INV_GEO);

		stat1.setString(56, plan_no);

		return stat1.executeUpdate();

	}
	public void deleteInvoiceDetail(Connection conn) throws SQLException{
		PreparedStatement stat;
		ResultSet result;
		stat = conn.prepareStatement("delete from ei_endors_dtls where year=? and company=? and inv_no=?");
		stat.setString(1, parameter.getYear());
		stat.setString(2, parameter.getCompany());
		stat.setString(3, parameter.getInvoiceNo());
		stat.executeUpdate();
		stat=null;
		result=null;

	}
	public void deleteAccessoriesDetail(Connection conn) throws SQLException{
		PreparedStatement stat;
		ResultSet result;
		stat = conn.prepareStatement("delete from ei_endors_accr_dtls where year=? and company=? and inv_no=?");
		stat.setString(1, parameter.getYear());
		stat.setString(2, parameter.getCompany());
		stat.setString(3, parameter.getInvoiceNo());
		stat.executeUpdate();
		stat=null;
		result=null;

	}

	public void deleteLicenceDetail(Connection conn,Parameters parameter) throws SQLException{
		PreparedStatement stat;
		ResultSet result;
		stat = conn.prepareStatement("delete from ei_endors_lc_lic_dtls where year=? and company=? and inv_no=?");
		stat.setString(1, parameter.getYear());
		stat.setString(2, parameter.getCompany());
		stat.setString(3, parameter.getInvoiceNo());
		stat.executeUpdate();
		stat=null;
		result=null;

	}

	public void deleteLicenceDeclaration(Connection conn,Parameters parameter) throws SQLException{
		PreparedStatement stat;
		ResultSet result;
		stat = conn.prepareStatement("delete from ei_licence_decl where year=? and company=? and inv_no=?");
		stat.setString(1, parameter.getYear());
		stat.setString(2, parameter.getCompany());
		stat.setString(3, parameter.getInvoiceNo());
		stat.executeUpdate();
		stat=null;
		result=null;

	}

	public String saveInvoiceDetail(Connection conn,String usrid) throws SQLException{
		PreparedStatement stat,stat1;
		ResultSet result;
		double linefob = 0.0;
		int sr = 0;
		if (CO_NO_E != null && CO_NO_E.size() > 0) {
			for (int i = 0; i < CO_NO_E.size(); i++) {
				if (CO_NO_E.get(i).toString() != null && CO_NO_E.get(i).toString().length() > 0) {
					stat = conn.prepareStatement(" select * from ei_opc_check where loct_code=? and desti_cntry=? and pch=? and cat_code=? and end_date is null ");
					stat.setString(1, parameter.getLocationCode());
					stat.setString(2, DESTI_CNTRY.trim().toUpperCase());
					stat.setString(3, cost_centre.trim().toUpperCase());
					stat.setString(4, TEMP_CAT.get(i).toString().trim());
					result = stat.executeQuery();
					if (result.next()) {

						stat = conn.prepareStatement("select * from pa_auth_mast where user_id=? and prog_name='OPC_CHECK'");
						stat.setString(1, usrid.trim());
						result = stat.executeQuery();

						linefob = 0;
						if (result.next()) {
						} else {
							linefob = Double.parseDouble(QTY_ENDORS_E.get(i).toString()) * (Double.parseDouble(PRICE_FC_E.get(i).toString()) + Double.parseDouble(HNGR_COST.get(i).toString()) + Double.parseDouble(TAG_COST.get(i).toString()));
							stat = conn.prepareStatement(" select nvl(aprv_amt,0)-nvl(alot_amt,0)  v_aprvamt from shahiweb.tp_Style_mat_mast where style_code =? and mmprgp=?");
							stat.setString(1, ITEM_NO_E.get(i).toString().trim().substring(0, 4));
							stat.setString(2, TEMP_CAT.get(i).toString().trim());

							result = stat.executeQuery();
							while (result.next()) {
								if (result.getDouble("v_aprvamt") < roundTwoDecimals(linefob)) {
									addActionMessage(" Error: CO -> " + CO_NO_E.get(i).toString() + " Line -> " + CO_LINE_E.get(i).toString() + " OPC Aproved Amount not sufficient Balance Amt is (" + result.getDouble("v_aprvamt") + ") Ag CO Amt " + roundTwoDecimals(linefob));
									conn.rollback();
									return ERROR;

								}
							}
						}
					}

					sr = sr + 1;
					Double p1 = 0.0;
					Double p2 = 0.0;
					Double iamt=0.0;
					iamt=Double.parseDouble(IGST_PER_E.get(i).toString());

					if (!LUT_IGST.equals("IGST") && iamt>0 && !CRNCY_CODE.equals("INR"))
					{
						iamt=0.0;
					}
					if (LUT_IGST.equals("IGST") && iamt<1)
					{
						addActionMessage(" Error: Check IGST Percent...  ");
						conn.rollback();
						return ERROR;

					}

					stat1 = conn.prepareStatement("insert into ei_endors_dtls (year,type,company,inv_no,all_no,all_date,sr_no,co_no,co_line,item_no,unit,qty_endors,qty_pcs,qty_kgs,price_fc,price_misc,ADJUST_FC,CURRENCY,COUNTRY,CATEGORY,DESCRIPTION,DBK_SLNO,STR_SLNO,STR_MISC,"
							+ "                  NET_PRICE,GR_DECL_AMT,PRE_PRINT_NO,TOKEN_NO,GR_DECL_PER,TEMP_CAT,MADE_FOR ,location,INR_CONV,faci,seh_user,rosl_slno,scheme_code,HSCODE1,HNGR_COST,TAG_COST,hsn_code,igst_per,cgst_per,sgst_per,tdate)"
							+ "                  values(?,'E',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,upper(?),?,?,?,?,?,?,sysdate)");
					stat1.setString(1, YEAR);
					stat1.setString(2, COMPANY);
					stat1.setString(3, INV_NO);


					stat1.setString(4, excs_inv_no);
					stat1.setString(5, inv_date);

					stat1.setInt(6, sr);
					//    stat1.setString(6,SR_NO.get(i).toString());
					stat1.setString(7, CO_NO_E.get(i).toString().trim());
					stat1.setString(8, CO_LINE_E.get(i).toString().trim());
					stat1.setString(9, ITEM_NO_E.get(i).toString().trim());
					stat1.setString(10, CO_UOM_E.get(i).toString().trim());
					stat1.setString(11, QTY_ENDORS_E.get(i).toString());
					stat1.setString(12, QTY_ENDORS_E.get(i).toString());
					stat1.setString(13, QTY_KGS.get(i).toString());
					p1 = Double.parseDouble(PRICE_FC_E.get(i).toString()) + Double.parseDouble(ADJUST_FC.get(i).toString());

					// stat1.setString(14,PRICE_FC_E.get(i).toString());
					stat1.setDouble(14, p1);
					p1 = Double.parseDouble(HNGR_COST.get(i).toString()) + Double.parseDouble(TAG_COST.get(i).toString());
					p2 = Double.parseDouble(HNGR_COST.get(i).toString()) + Double.parseDouble(TAG_COST.get(i).toString());


					//   stat1.setString(15,PRICE_MISC.get(i).toString());
					stat1.setDouble(15, p2);
					stat1.setString(16, ADJUST_FC.get(i).toString());

					stat1.setString(17, CRNCY_CODE.trim());
					stat1.setString(18, DESTI_CNTRY.trim().toUpperCase().toUpperCase());
					stat1.setString(19, CATG_CODE.get(i).toString().toUpperCase());
					stat1.setString(20, INV_DESC.get(i).toString().toUpperCase());
					stat1.setString(21, DBK_SLNO.get(i).toString().toUpperCase());
					stat1.setString(22, STR_SLNO.get(i).toString().toUpperCase());

					stat1.setString(23, STR_MISC.get(i).toString().toUpperCase());
					stat1.setString(24, NET_PRICE_E.get(i).toString());
					stat1.setString(25, GR_DECL_AMT_E.get(i).toString());
					stat1.setString(26, PRE_PRINT_NO.get(i).toString());
					stat1.setString(27, TOKEN_NO.get(i).toString());
					stat1.setString(28, GR_DECL_PER.get(i).toString());
					stat1.setString(29, TEMP_CAT.get(i).toString());
					stat1.setString(30, MADE_FOR.get(i).toString());

					stat1.setString(31, parameter.getLocationCode());
					stat1.setString(32, to_inr_conv);
					stat1.setString(33, facility);
					stat1.setString(34, usrid);
					stat1.setString(35, ROSL_SLNO.get(i).toString().toUpperCase());
					stat1.setString(36, SCHEME_CODE.get(i).toString());
					stat1.setString(37, HSCODE1.get(i).toString());
					stat1.setString(38, HNGR_COST.get(i).toString());
					stat1.setString(39, TAG_COST.get(i).toString());
					stat1.setString(40, HSN_CODE_E.get(i).toString());
					stat1.setDouble(41, iamt);
					stat1.setString(42, CGST_PER_E.get(i).toString());
					stat1.setString(43, SGST_PER_E.get(i).toString());
					stat1.executeUpdate();

				}
			}
		}
		return SUCCESS;
	}

	public void saveInvoiceAccessories(Connection conn,String usrid) throws SQLException{
		PreparedStatement stat1;
		if (ACCR_CO_NO != null && ACCR_CO_NO.size() > 0) {
			for (int i = 0; i < ACCR_CO_NO.size(); i++) {
				if (ACCR_CO_NO.get(i).toString() != null && ACCR_CO_NO.get(i).toString().length() > 0) {
					stat1 = conn.prepareStatement("insert into ei_endors_accr_dtls (YEAR,COMPANY,INV_NO,CO_NO,CO_LINE,ITEM_NO, ACCR_DESC, ACCR_QTY, ACCR_PRICE, ACCR_DBKSLNO, ACCR_STRSLNO,SEH_USER,TDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)");
					stat1.setString(1, YEAR);
					stat1.setString(2, COMPANY);
					stat1.setString(3, INV_NO);
					stat1.setString(4, ACCR_CO_NO.get(i).toString().trim());
					stat1.setString(5, ACCR_CO_LINE.get(i).toString().trim());
					stat1.setString(6, ACCR_ITEM_NO.get(i).toString().trim());
					stat1.setString(7, ACCR_TYPE.get(i).toString());
					stat1.setString(8, ACCR_QTY.get(i).toString());
					stat1.setString(9, ACCR_PRICE.get(i).toString());
					stat1.setString(10, ACCR_DBKSLNO.get(i).toString());
					stat1.setString(11, ACCR_STRSLNO.get(i).toString());
					stat1.setString(12, usrid);
					stat1.executeUpdate();

				}
			}
		}
		stat1=null;
	}

	public String  saveLicence() throws SQLException{
		
		String vissue=null;
		String vlic=null;
		String vlicno=null;
		String vliccomp=null;
		String vtext=null;
		Connection conn = null;
		PreparedStatement stat1 = null;
		ResultSet result = null;
		Parameters parameter=new Parameters();
		
		System.out.println(getInvLicenceList());
		try {
			parameter.setCompany(getCOMPANY());
			parameter.setInvoiceNo(getINV_NO());
			parameter.setYear(getYEAR());
			parameter.setLocationCode(getLocationCode());
			conn = new connection().getConnection();
			conn.setAutoCommit(false);
			deleteLicenceDetail(conn,parameter);
			deleteLicenceDeclaration(conn,parameter);
			if (REF_NO != null && REF_NO.size() > 0) {
				int j = 1;
				for (int i = 0; i < REF_NO.size(); i++) {
					if (REF_NO.get(i).toString() != null && REF_NO.get(i).toString().length() > 0) {

						if (REF_TYPE.get(i).toString().substring(0, 1).equals("07")) {
							vissue = "Issued by RJDG, Bangalore";
						} else {
							vissue = "Issued by JDGFT (CLA), New Delhi";
						}


						if (vlic == "") {
							vlic = REF_NO.get(i).toString();
							vlicno = REF_TYPE.get(i).toString().trim() + "-" + REF_NO.get(i).toString().trim() + " Dated:" + LIC_DATE.get(i).toString().substring(0, 10);
						} else if (vlic != REF_NO.get(i).toString().trim()) {
							vlicno = vlicno + ", " + REF_TYPE.get(i).toString().trim() + "-" + REF_NO.get(i).toString().trim() + " Dated:" + LIC_DATE.get(i).toString().substring(0, 10);
						}

						if (LIC_COMP.get(i).toString().trim().equals("111") || LIC_COMP.get(i).toString().trim().equals("SCGA0119") || LIC_COMP.get(i).toString().trim().equals("FD001090") || LIC_COMP.get(i).toString().trim().equals("SEPL")) {
							vliccomp = "111";
						}
						if (COMPANY.equals(vliccomp)) {
							vtext = "THIS IS IN DISCHARGE OF EXPORT OBLIGATION UNDER LICENCE NO: " + vlicno + ' ' + vissue;
						}


						stat1 = conn.prepareStatement("insert into ei_endors_LC_LIC_dtls (YEAR,TYPE,COMPANY,INV_NO,REF_TYPE,REF_NO,QTY,QTY_SQM,B_E_NO,EXP_REF_CTRL_NO,IMP_REF_CTRL_NO ,FOB_AMT,IO_NORMS,LOCATION ,SR_NO,ADJS_QTY,LIC_DATE,LIC_LOCT,LIC_COMP ,SEH_USER ,TDATE) "
								+ "                  values(?,'E',?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,sysdate)");
						stat1.setString(1, YEAR);
						stat1.setString(2, COMPANY);
						stat1.setString(3, INV_NO);
						stat1.setString(4, REF_TYPE.get(i).toString().trim());
						stat1.setString(5, REF_NO.get(i).toString().trim());
						stat1.setString(6, QTY.get(i).toString());
						stat1.setString(7, QTY_SQM.get(i).toString());
						stat1.setString(8, BE_NO.get(i).toString().trim());
						stat1.setString(9, EXP_CTRL_NO.get(i).toString());
						stat1.setString(10, IMP_CTRL_NO.get(i).toString());
						stat1.setString(11, FOB_AMT.get(i).toString());
						stat1.setString(12, IO_NORMS.get(i).toString());
						stat1.setString(13, parameter.getLocationCode());
						stat1.setInt(14, j);
						stat1.setString(15, ADJS_QTY.get(i).toString());
						stat1.setString(16, LIC_DATE.get(i).toString().substring(0, 10));
						stat1.setString(17, LIC_LOCT.get(i).toString());
						stat1.setString(18, LIC_COMP.get(i).toString());
						stat1.setString(19, getUserId());
						stat1.executeUpdate();
						j++;
					}
				}
			}

			if (vtext != null) {
				stat1 = conn.prepareStatement("insert into ei_licence_decl (year,company,inv_no,DECL_TEXT,seh_user,tdate) values ( ?,?,?,?,?,sysdate)");
				stat1.setString(1, YEAR);
				stat1.setString(2, COMPANY);
				stat1.setString(3, INV_NO);
				stat1.setString(4, vtext);
				stat1.setString(5, getUserId());
				stat1.executeUpdate();
			}
			conn.commit();
			addActionError("Licence Detail has been saved!!!");
			
		}catch(Exception ex){
			ex.printStackTrace();
			addActionError(ex.getLocalizedMessage());
			
		}finally{
          if(stat1!=null){
        	  stat1.close();
          }
          if(conn!=null){
        	  conn.close();
          }
		}
		return "prelicence"; 
	}
	public String insertInvoice(Connection conn,String usrid) throws SQLException{
		PreparedStatement stat1,stat;
		ResultSet result1,result;
		String vissue=null;
		String vlic=null;
		String vlicno=null;
		String vliccomp=null;
		String vtext=null;
		COMPANY = "111";
		String newno = "";
		String subtype = "";
		String prefix = "";
		String vloct = "";
		String FINYR = "";
		if (facility.equals("400"))
		{
			if (CRNCY_CODE.trim().equals("INR")) 
			{
				subtype = "SAD";
				prefix = "4";
			}
			else  
			{
				subtype = "SAE";
				prefix = "7";
			}
		}else{ 
			if (CNTRY_ORIGIN.trim().equals("IN")) {
				if (CRNCY_CODE.trim().equals("INR")) {
					subtype = "DOM";
					prefix = "1";
				} else {
					subtype = "EXP";
					prefix = "2";
				}
			} else {
				subtype = "TCS";
				prefix = "3";
			}
		} 
		stat1 = conn.prepareStatement("select vou_numb+1 vno ,lpad(nvl(vou_numb,0)+1,5,'0') newex5,SUBSTR(FIN_YEAR,3,2) FIN_YEAR from ei_vou_numb_mast where location_code=? and fin_year=pay_fin_year(?) and  VOU_TYPE='LG' AND SUB_TYPE=? for update NOWAIT");
		stat1.setString(1, inv_state);
		stat1.setString(2, inv_date);
		stat1.setString(3, subtype);
		result1 = stat1.executeQuery();
		if (result1.next() == true) {
			newno = result1.getString("vno");
			FINYR = result1.getString("FIN_YEAR");

			excs_inv_no = prefix + gstin_state + FINYR + result1.getString("newex5");

		}
		stat1 = conn.prepareStatement("select nvl(vou_numb,0)+1 vno from ei_vou_numb_mast WHERE FIN_YEAR=pay_fin_year(?) AND VOU_TYPE='LG' AND SUB_TYPE='111' for update NOWAIT ");
		stat1.setString(1, inv_date);
		result1 = stat1.executeQuery();
		if (result1.next() == true) {
			INV_NO = result1.getString("vno");
		}
		if (newno == null || excs_inv_no == null || INV_NO == null) {
			addActionMessage("Error in Invoice Series......");
			return ERROR;
		}



		// Excs Invoice Series clsoed..


		stat1 = conn.prepareStatement("insert into  ei_endors_mast ( YEAR,TYPE,COMPANY,INV_NO,INV_DATE,EXCS_INV_NO,PLAN_NO,BUYER,BUYER_ADDR,CONSIGNEE,CONS_ADDR,NOTIFY,MODE_OF_SHIP,PLACE,LOADING, DISCHARGE,DESTI_CNTRY,PAY_TERM,INV_QTY,ENDORS_QTY,T_O_DATE,AGENT,SHIP_DESC,COMM_PER,LOCATION,SELF_TP,"
				+ "                  EXP_TYPE,FIN_YEAR,PRE_CARRIAGE,SURRENDER_YN,COST_CENTRE, MANUF_CODE,TAX_TYPE,TAX_PERCENT, TAX_CODE ,TAX_CAL_PER,FACILITY, SHIP_TERM,DESTI_CODE, HS_CODE,OH_WORK,TTO_DATE,AC_HOLDER , CNTRY_ORIGIN,FWD_CODE,PAYMENT_TERM,ETD_DATE,TO_INR_CONV,CRNCY_CODE,LOADING_PORT,"
				+ "                  LCNO, MERCHANT_NAME, CO_MOS,PPRQ_DATE,UPCHARGE_PER,DIS_CNTRY,CTNS,GRWT,transport_cost,SEH_USER,TDATE,t_mod,EXCISE_UNIT,mmitgr,netwt,inv_state,desti_state,gstin_state,LUT_IGST,desti_geo,inv_geo ) "
				+ "                  values (pay_fin_year(?),'E',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,pay_fin_year(?),?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,SYSDATE,'LGM4',?,?,?,?,?,?,?,?,?)");

		stat1.setString(1, inv_date);
		stat1.setString(2, COMPANY);
		stat1.setString(3, INV_NO);
		stat1.setString(4, inv_date);
		stat1.setString(5, excs_inv_no);
		stat1.setString(6, plan_no);
		stat1.setString(7, buyer.trim());
		stat1.setString(8, buyer_addr.trim());
		stat1.setString(9, buyer.trim());
		stat1.setString(10, cons_addr.trim());
		stat1.setString(11, notify.toUpperCase());
		stat1.setString(12, mode_of_ship.trim().toUpperCase());
		stat1.setString(13, PLACE.trim().toUpperCase());
		stat1.setString(14, CLR_PORT.trim().toUpperCase());
		stat1.setString(15, DISCHARGE.trim().toUpperCase());
		stat1.setString(16, DESTI_CNTRY.trim().toUpperCase());
		stat1.setString(17, pay_term);
		stat1.setString(18, inv_qty);
		stat1.setString(19, inv_qty);
		if (to_date.length() > 8) {
			stat1.setString(20, to_date.substring(0, 10));
		} else {
			stat1.setString(20, to_date);
		}
		stat1.setString(21, agent.toUpperCase());
		stat1.setString(22, SHIP_DESC.toUpperCase());
		stat1.setString(23, comm_per);
		stat1.setString(24, parameter.getLocationCode());
		stat1.setString(25, self_tp);
		stat1.setString(26, exp_type.trim());
		stat1.setString(27, inv_date);
		stat1.setString(28, pre_carriage.toUpperCase());
		stat1.setString(29, ship_cancel);
		stat1.setString(30, cost_centre.trim());
		stat1.setString(31, MANUF_CODE.toUpperCase());
		stat1.setString(32, TAX_TYPE);
		stat1.setString(33, TAX_PERCENT);
		stat1.setString(34, TAX_CODE);
		stat1.setString(35, TAX_CAL_PER);
		stat1.setString(36, facility);
		stat1.setString(37, ship_term.toUpperCase());
		stat1.setString(38, DESTI_CODE.trim().toUpperCase());
		stat1.setString(39, hs_code.toUpperCase());
		stat1.setString(40, outhouse);
		if (tto_date != null) {
			stat1.setString(41, tto_date.substring(0, 10));
		} else {
			stat1.setString(41, tto_date);
		}
		stat1.setString(42, ac_holder.toUpperCase());
		stat1.setString(43, CNTRY_ORIGIN.trim().toUpperCase());
		stat1.setString(44, fwd_code.toUpperCase());
		stat1.setString(45, payment_term.toUpperCase());
		if (etd_date.length() > 8) {
			stat1.setString(46, etd_date.substring(0, 10));
		} else {
			stat1.setString(46, etd_date);
		}
		stat1.setString(47, to_inr_conv);
		stat1.setString(48, CRNCY_CODE.trim());
		stat1.setString(49, LOADING_PORT.trim().toUpperCase());
		stat1.setString(50, lcno.toUpperCase());
		stat1.setString(51, merchant.toUpperCase());
		stat1.setString(52, CO_MOS);
		if (pprq_date != null) {
			stat1.setString(53, pprq_date.substring(0, 10));
		} else {
			stat1.setString(53, pprq_date);
		}
		stat1.setString(54, upcharge_per);
		stat1.setString(55, DIS_CNTRY.trim().toUpperCase());
		stat1.setString(56, CTNS);
		stat1.setString(57, GRWT);
		stat1.setString(58, transport_cost);
		stat1.setString(59, usrid);
		stat1.setString(60, EXCISE_UNIT);
		stat1.setString(61, mmitgr);
		stat1.setString(62, NETWT);
		stat1.setString(63, inv_state);
		stat1.setString(64, DESTI_STATE);
		stat1.setString(65, gstin_state);
		stat1.setString(66, LUT_IGST);
		stat1.setString(67,Integer.toString(DESTI_GEO));
		stat1.setString(68,INV_GEO);

		if (excs_inv_no.length() < 6) {
			addActionMessage("Error in Invoice Series......");
			return ERROR;
		} 

		stat1.executeUpdate();
		//  Insert into Detail File
		int sr = 0;
		if (CO_NO_E != null && CO_NO_E.size() > 0) {
			for (int i = 0; i < CO_NO_E.size(); i++) {
				if (CO_NO_E.get(i).toString() != null && CO_NO_E.get(i).toString().length() > 0) {
					Double linefob = 0.00;
					Double p1 = 0.0;
					Double p2 = 0.0;
					stat = conn.prepareStatement(" select * from ei_opc_check where loct_code=? and desti_cntry=? and pch=? and cat_code=? and end_date is null ");
					stat.setString(1, parameter.getLocationCode());
					stat.setString(2, DESTI_CNTRY.trim().toUpperCase());
					stat.setString(3, cost_centre.trim().toUpperCase());
					stat.setString(4, TEMP_CAT.get(i).toString().trim().toUpperCase());
					result = stat.executeQuery();
					if (result.next()) {
						stat = conn.prepareStatement("select * from pa_auth_mast where user_id=? and prog_name='OPC_CHECK'");
						stat.setString(1, usrid);
						result = stat.executeQuery();
						if (result.next()) {
						} else {
							linefob = 0.00;
							linefob = Double.parseDouble(QTY_ENDORS_E.get(i).toString()) * (Double.parseDouble(PRICE_FC_E.get(i).toString()) + Double.parseDouble(HNGR_COST.get(i).toString()) + Double.parseDouble(TAG_COST.get(i).toString()) + Double.parseDouble(ADJUST_FC.get(i).toString()));

							stat = conn.prepareStatement(" select nvl(aprv_amt,0)-nvl(alot_amt,0)  v_aprvamt from shahiweb.tp_Style_mat_mast where style_code =? and mmprgp=?");
							stat.setString(1, ITEM_NO_E.get(i).toString().trim().substring(0, 3));
							stat.setString(2, TEMP_CAT.get(i).toString().trim());
							result = stat.executeQuery();
							while (result.next()) {
								if (result.getDouble("v_aprvamt") < roundTwoDecimals(linefob)) {
									addActionMessage(" Error: CO -> " + CO_NO_E.get(i).toString() + " Line -> " + CO_LINE_E.get(i).toString() + "OPC Aproved Amount not sufficient Balance Amt is " + result.getDouble("v_aprvamt") + " - " + roundTwoDecimals(linefob));
									return ERROR;
								}
							}
						}
					} 

					sr = sr + 1;
					double san_pr = 0.0;
					double iamt=0.0;
					iamt=Double.parseDouble(IGST_PER_E.get(i).toString());
					if (!LUT_IGST.equals("IGST") && iamt>0 && !CRNCY_CODE.equals("INR"))
					{
						iamt=0.0;
					}
					if (LUT_IGST.equals("IGST") && iamt<1)
					{
						addActionMessage(" Error: Check IGST Percent...  ");
						conn.rollback();
						return ERROR;

					}
					stat1 = conn.prepareStatement("insert into ei_endors_dtls (year,type,company,inv_no,all_no,all_date,sr_no,co_no,co_line,item_no,unit,qty_endors,qty_pcs,qty_kgs,price_fc,price_misc,ADJUST_FC,CURRENCY,COUNTRY,CATEGORY,DESCRIPTION,DBK_SLNO,STR_SLNO,STR_MISC,"
							+ "                  NET_PRICE,GR_DECL_AMT,PRE_PRINT_NO,TOKEN_NO,GR_DECL_PER,TEMP_CAT,MADE_FOR,location,inr_conv,faci, seh_user,rosl_slno,SCHEME_CODE,HSCODE1,hngr_cost,tag_cost,HSN_CODE,IGST_PER,CGST_PER,SGST_PER,tdate) values(pay_fin_year(?),'E',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,upper(?),?,?,?,?,?,?,sysdate)");
					stat1.setString(1, inv_date);
					stat1.setString(2, COMPANY);
					stat1.setString(3, INV_NO);
					stat1.setString(4, excs_inv_no);
					stat1.setString(5, inv_date);
					stat1.setInt(6, sr);
					// stat1.setString(6,SR_NO.get(i).toString());
					stat1.setString(7, CO_NO_E.get(i).toString().trim());
					stat1.setString(8, CO_LINE_E.get(i).toString().trim());
					stat1.setString(9, ITEM_NO_E.get(i).toString().trim());
					stat1.setString(10, CO_UOM_E.get(i).toString().trim());
					stat1.setString(11, QTY_ENDORS_E.get(i).toString());
					stat1.setString(12, QTY_ENDORS_E.get(i).toString());
					stat1.setString(13, QTY_KGS.get(i).toString());

					p1 = Double.parseDouble(PRICE_FC_E.get(i).toString()) + Double.parseDouble(ADJUST_FC.get(i).toString());
					// stat1.setString(14,PRICE_FC_E.get(i).toString());
					stat1.setDouble(14, p1);
					p2 = Double.parseDouble(HNGR_COST.get(i).toString()) + Double.parseDouble(TAG_COST.get(i).toString());
					// stat1.setString(15,PRICE_MISC.get(i).toString());
					stat1.setDouble(15, p2);
					stat1.setString(16, ADJUST_FC.get(i).toString());
					stat1.setString(17, CRNCY_CODE.trim());
					stat1.setString(18, DESTI_CNTRY.toUpperCase());
					stat1.setString(19, CATG_CODE.get(i).toString().toUpperCase());
					stat1.setString(20, INV_DESC.get(i).toString().toUpperCase());
					stat1.setString(21, DBK_SLNO.get(i).toString().toUpperCase());
					stat1.setString(22, STR_SLNO.get(i).toString().toUpperCase());
					stat1.setString(23, STR_MISC.get(i).toString().toUpperCase());
					stat1.setString(24, NET_PRICE_E.get(i).toString());
					stat1.setString(25, GR_DECL_AMT_E.get(i).toString());
					stat1.setString(26, PRE_PRINT_NO.get(i).toString());
					stat1.setString(27, TOKEN_NO.get(i).toString());
					stat1.setString(28, GR_DECL_PER.get(i).toString());
					stat1.setString(29, TEMP_CAT.get(i).toString());
					stat1.setString(30, MADE_FOR.get(i).toString());
					stat1.setString(31, parameter.getLocationCode());
					stat1.setString(32, to_inr_conv);
					stat1.setString(33, facility);
					stat1.setString(34, usrid);
					stat1.setString(35, ROSL_SLNO.get(i).toString().toUpperCase());
					stat1.setString(36, SCHEME_CODE.get(i).toString());
					stat1.setString(37, HSCODE1.get(i).toString());
					stat1.setString(38, HNGR_COST.get(i).toString());
					stat1.setString(39, TAG_COST.get(i).toString());

					stat1.setString(40, HSN_CODE_E.get(i).toString());
					stat1.setDouble(41, iamt);
					stat1.setString(42, CGST_PER_E.get(i).toString());
					stat1.setString(43, SGST_PER_E.get(i).toString());
					stat1.executeUpdate();

				}
			}  
		}  

		if (ACCR_CO_NO != null && ACCR_CO_NO.size() > 0) {
			for (int i = 0; i < ACCR_CO_NO.size(); i++) {
				if (ACCR_CO_NO.get(i).toString() != null && ACCR_CO_NO.get(i).toString().length() > 0) {
					stat1 = conn.prepareStatement("insert into ei_endors_accr_dtls (YEAR,COMPANY,INV_NO,CO_NO,CO_LINE,ITEM_NO, ACCR_DESC, ACCR_QTY, ACCR_PRICE, ACCR_DBKSLNO, ACCR_STRSLNO,TDATE,SEH_USER) values(pay_fin_year(?),?,?,?,?,?,?,?,?,?,?,?,sysdate)");
					stat1.setString(1, inv_date);
					stat1.setString(2, COMPANY);
					stat1.setString(3, INV_NO);
					stat1.setString(4, ACCR_CO_NO.get(i).toString().trim());
					stat1.setString(5, ACCR_CO_LINE.get(i).toString().trim());
					stat1.setString(6, ACCR_ITEM_NO.get(i).toString().trim());
					stat1.setString(7, ACCR_TYPE.get(i).toString().trim());
					stat1.setString(8, ACCR_QTY.get(i).toString());
					stat1.setString(9, ACCR_PRICE.get(i).toString());
					stat1.setString(10, ACCR_DBKSLNO.get(i).toString());
					stat1.setString(11, ACCR_STRSLNO.get(i).toString());
					stat1.setString(12, usrid);
					stat1.executeUpdate();
				}
			}
		}// Insert Accessories closed

		// Insert into Licence Table

		if (REF_NO != null && REF_NO.size() > 0) {
			int j = 1;
			for (int i = 0; i < REF_NO.size(); i++) {
				if (REF_NO.get(i).toString() != null && REF_NO.get(i).toString().length() > 0) {

					if (REF_TYPE.get(i).toString().substring(0, 1).equals("07")) {
						vissue = "Issued by RJDG, Bangalore";
					} else {
						vissue = "Issued by JDGFT (CLA), New Delhi";
					}


					if (vlic == "") {
						vlic = REF_NO.get(i).toString();
						vlicno = REF_TYPE.get(i).toString().trim() + "-" + REF_NO.get(i).toString().trim() + " Dated:" + LIC_DATE.get(i).toString().substring(0, 10);
					} else if (vlic != REF_NO.get(i).toString().trim()) {
						vlicno = vlicno + ", " + REF_TYPE.get(i).toString().trim() + "-" + REF_NO.get(i).toString().trim() + " Dated:" + LIC_DATE.get(i).toString().substring(0, 10);
					}

					if (LIC_COMP.get(i).toString().trim().equals("111") || LIC_COMP.get(i).toString().trim().equals("SCGA0119") || LIC_COMP.get(i).toString().trim().equals("FD001090") || LIC_COMP.get(i).toString().trim().equals("SEPL")) {
						vliccomp = "111";
					}
					if (COMPANY.equals(vliccomp)) {
						vtext = "THIS IS IN DISCHARGE OF EXPORT OBLIGATION UNDER LICENCE NO: " + vlicno + ' ' + vissue;
					}


					stat1 = conn.prepareStatement("insert into ei_endors_LC_LIC_dtls (YEAR,TYPE,COMPANY,INV_NO,REF_TYPE,REF_NO,QTY,QTY_SQM,B_E_NO,EXP_REF_CTRL_NO,IMP_REF_CTRL_NO ,FOB_AMT,IO_NORMS,LOCATION ,SR_NO,ADJS_QTY,LIC_DATE,LIC_LOCT,LIC_COMP ,SEH_USER ,TDATE) "
							+ "                  values(pay_fin_year(?),'E'?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)");
					stat1.setString(1, inv_date);
					stat1.setString(2, COMPANY);
					stat1.setString(3, INV_NO);
					stat1.setString(4, REF_TYPE.get(i).toString().trim());
					stat1.setString(5, REF_NO.get(i).toString().trim());
					stat1.setString(6, QTY.get(i).toString());
					stat1.setString(7, QTY_SQM.get(i).toString());
					stat1.setString(8, BE_NO.get(i).toString().trim());
					stat1.setString(9, EXP_CTRL_NO.get(i).toString());
					stat1.setString(10, IMP_CTRL_NO.get(i).toString());
					stat1.setString(11, FOB_AMT.get(i).toString());
					stat1.setString(12, IO_NORMS.get(i).toString());
					stat1.setString(13, parameter.getLocationCode());
					stat1.setInt(14, j);
					stat1.setString(15, ADJS_QTY.get(i).toString());
					stat1.setString(16, LIC_DATE.get(i).toString());
					stat1.setString(17, LIC_LOCT.get(i).toString());
					stat1.setString(18, LIC_COMP.get(i).toString());
					stat1.setString(19, usrid);
					j++;
				}
			}
		}
		if (vtext != null) {
			stat1 = conn.prepareStatement("insert into ei_licence_decl (year,company,inv_no,DECL_TEXT,seh_user,tdate) values ( pay_fin_year(?),?,?,?,?,sysdate)");
			stat1.setString(1, inv_date);
			stat1.setString(2, COMPANY);
			stat1.setString(3, INV_NO);
			stat1.setString(4, vtext);
			stat1.setString(5, usrid);
			stat1.executeUpdate();
		}
		// Licence Insert closed. 
		// Update Vou Num Master

		stat1 = conn.prepareStatement("update ei_vou_numb_mast set vou_numb=?  where location_code=? and fin_year=pay_fin_year(?) and  VOU_TYPE='LG' AND SUB_TYPE=? ");
		stat1.setString(1, newno);
		stat1.setString(2, inv_state);
		stat1.setString(3, inv_date);
		stat1.setString(4, subtype);
		stat1.executeUpdate();

		stat1 = conn.prepareStatement("update ei_vou_numb_mast set vou_numb=?  where fin_year=pay_fin_year(?) and VOU_TYPE='LG' AND SUB_TYPE='111' ");
		stat1.setString(1, INV_NO);
		stat1.setString(2, inv_date);
		stat1.executeUpdate();


		conn.commit();
		// Update vou numb mast completed.
		return SUCCESS;


	}
	public String saveInvoice() throws SQLException{

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);

		String planNo=request. getParameter("plan_no");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 

		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}
		PreInvoiceHeader header=(PreInvoiceHeader)getMap().get(planNo);

		System.out.println("Inside saveInvoice()"+header);

		Connection conn = null;
		Connection conndb2 = null;
		PreparedStatement stat = null;
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		ResultSet result = null;
		ResultSet result1 = null;
		ResultSet result2 = null;
		String results[]=null;
		int flag = 0;
		String p_error = "";
		try {

			conn = new connection().getConnection();
			conndb2 = new connectiondb2().getConnection();
			conn.setAutoCommit(false);
			
			String LOCATION_CODE = null;
			stat1 = conn.prepareStatement("select location_code from seh_web_users where user_id=?");
			stat1.setString(1, usrid);
			result1 = stat1.executeQuery();
			while (result1.next()) {
				LOCATION_CODE = result1.getString("location_code");
				searchloct = result1.getString("location_code");
			}
			if (usrid == null || LOCATION_CODE == null) {
				addActionMessage("Session Not Valid !!");
				return ERROR;
			}


			if(header.getFwdToCustom()!=null && header.getFwdToCustom().equals("Y")){
				results=checkDuplicateDeliveryNo(header,conn);
				if(results[0]!=null && results[0].equals("true")){
					addActionMessage("Delevery No Already Linked with other invoice. Pls change the Delv No " +results[1] );
					return ERROR;
				}

				p_error = cocheck(header.getPlanNo(), conn, conndb2);
				if ((p_error.length() > 0)) {
					addActionMessage(" Error: " + p_error);
					return ERROR;
				}

				if (to_inr_conv == null) {
					addActionMessage("Check Movex Inr Conv.... ");
					return ERROR;
				}
				results=checkCOApproval(header,conn);
				if(results[0]!=null && results[0].equals("true") && results[1]!=null){
					addActionMessage(" Error :- " + results[1]);
					return ERROR;
				}
				p_error = cocheckline(header.getPlanNo(), usrid, conn, conndb2);
				if ((p_error.length() > 0)) {
					addActionMessage(" Error: " + p_error);
					return ERROR;
				}
				results=checkDuplicatePlanNo(header,conn);
				if(results[0]!=null && results[0].equals("true")){
					if(updateMaster(header,conn)>0){
						deleteInvoiceDetail(conn);
						deleteAccessoriesDetail(conn);
						saveInvoiceDetail(conn,usrid);
						saveInvoiceAccessories(conn,usrid);
					}else{
						insertInvoice(conn,usrid);
					}
				}
			}
			return SUCCESS;
		}catch(SQLException ex){
			conn.rollback();
			return ERROR;
		}finally{
			conn.close();
			conndb2.close();
		}
	}			

	private String getUserId(){
		Map session = ActionContext.getContext().getSession();
		String LOCATION_CODE = (String) session.get("sessLocationCode");
		String usrid = (String) session.get("sessUserId");
		return usrid;
	}
	public String saveinv() throws SQLException {
		getMap().put(getHeader().getPlanNo(), getHeader());
		COMPANY=getHeader().getCompany();
		YEAR=getHeader().getYear();
		INV_NO=getHeader().getInvNo();
		plan_no=getHeader().getPlanNo();
		isDisabled=false;
		
		getMap().put("isDisabled", isDisabled);
		addActionMessage("Records Save !!  Invoice No " +getHeader().getInvoiceNo());
  
		return SUCCESS;

	}

	public String exciseinv() throws SQLException {

		//List unitList = new ArrayList<UnitBean>();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		PreInvioiceNewDao dao=new PreInvioiceNewDao();
		String planNo=request. getParameter("planNo");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 
		String GetDataFlag=request. getParameter("GetDataFlag");
		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}		
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		Connection conn = null;
		Connection conndb2 = null;
		PreparedStatement stat = null;
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		ResultSet result = null;
		ResultSet result1 = null;
		ResultSet result2 = null;
		int flag = 0;
		try {


			try {
				conn = new connection().getConnection();
				conndb2 = new connectiondb2().getConnection();
				conn.setAutoCommit(false);

			} catch (Exception e) {
				System.out.println(e.toString());
			} 

			String LOCATION_CODE = null;
			stat1 = conn.prepareStatement("select location_code from seh_web_users where user_id=?");
			stat1.setString(1, usrid);
			result1 = stat1.executeQuery();
			while (result1.next()) {
				LOCATION_CODE = result1.getString("location_code");
				searchloct = result1.getString("location_code");
			}

			if (usrid == null || LOCATION_CODE == null) {
				addActionMessage("Session Not Valid !!");
				return ERROR;
			}
			String newno = "";
			String subtype = "";
			String prefix = "";
			String vloct = "";
			String FINYR = "";

			if (exciseFlag != null && exciseFlag.equals("YES")) {
				subtype = EXCISE_UNIT;

				stat1 = conn.prepareStatement("select vou_numb+1 vno ,lpad(nvl(vou_numb,0)+1,4,'0') newex4 ,SUBSTR(FIN_YEAR,3,2) FIN_YEAR from ei_vou_numb_mast where fin_year=pay_fin_year(sysdate) and  VOU_TYPE='EI' AND SUB_TYPE=? for update NOWAIT");
				// stat1.setString(1,inv_date); 
				stat1.setString(1, subtype);
				result1 = stat1.executeQuery();

				if (result1.next() == true) {
					newno = result1.getString("vno");
					FINYR = result1.getString("FIN_YEAR");

					if (subtype.equals("U2")) {
						ex_inv_slno = "30" + FINYR + "5" + result1.getString("newex4");
					}
					if (subtype.equals("U14")) {
						ex_inv_slno = "30" + FINYR + "4" + result1.getString("newex4");
					}
					if (subtype.equals("U99")) {
						ex_inv_slno = "30" + FINYR + "2" + result1.getString("newex4");
					}
				}
				stat1 = conn.prepareStatement("update ei_endors_mast set excise_unit=?,ex_inv_slno=?,ex_inv_date=sysdate where year=? and company=? and inv_no=?");
				stat1.setString(1, subtype);
				stat1.setString(2, ex_inv_slno);
				stat1.setString(3, YEAR);
				stat1.setString(4, COMPANY);
				stat1.setString(5, INV_NO);
				stat1.executeUpdate();

				stat1 = conn.prepareStatement("update ei_vou_numb_mast set vou_numb=?  where fin_year=pay_fin_year(sysdate) and  VOU_TYPE='EI' AND SUB_TYPE=? ");
				stat1.setString(1, newno);
				//   stat1.setString(2,inv_date);
				stat1.setString(2, subtype);
				stat1.executeUpdate();
				flag = 1;


			}


		} catch (Exception ee) {
			System.out.println(ee.toString());
			conn.rollback();
			flag = 0;
		} finally {
			if (result != null) {
				result.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (result1 != null) {
				result1.close();
			}
			if (stat1 != null) {
				stat1.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (conndb2 != null) {
				conndb2.close();
			}

		}


		if (flag == 1) {

			addActionMessage("Records Save !!  Invoice No " + excs_inv_no + "  Excise Inv - " + ex_inv_slno);
			searchplan = null;
			searchinv = null;
			location = null;
			searchloct = null;
			excs_inv_no = null;
			plan_no = null;
			inv_date = null;
			self_tp = null;
			exp_type = null;
			inv_state = null;
			gstin_state = null;
			hs_code = null;
			lcno = null;
			facility = null;
			comm_per = null;
			upcharge_per = null;
			pre_carriage = null;
			fwd_custom = null;
			tto_date = null;
			etd_date = null;
			to_date = null;
			ac_holder = null;
			merchant = null;
			pprq_date = null;
			cost_centre = null;
			mode_of_ship = null;
			inv_qty = null;
			buyer = null;
			buyer_addr = null;
			cons_addr = null;
			first_sale = null;
			ship_term = null;
			agent = null;
			fwd_code = null;
			MANUF_CODE = null;
			notify = null;
			pay_term = null;
			transport_cost = null;
			CTNS = null;
			GRWT = null;
			payment_term = null;
			LOADING_PORT = null;
			CLR_PORT = null;
			DESTI_CNTRY = null;
			DIS_CNTRY = null;
			CNTRY_ORIGIN = null;
			DISCHARGE = null;
			DESTI_CODE = null;
			DESTI_STATE = null;
			PLACE = null;
			CRNCY_CODE = null;
			MANUF_STATE = null;
			TAX_TYPE = null;
			TAX_PERCENT = null;
			TAX_CODE = null;
			TAX_CAL_PER = null;
			SHIP_DESC = null;
			ship_cancel = null;
			outhouse = null;
			AccrLineList = null;
			InvLineList = null;
			DIS_CNTRY_DESC = null;
			CNTRY_ORIGIN_DESC = null;
			DESTI_CNTRY_DESC = null;
			LOADING_PORT_DESC = null;
			CLR_PORT_DESC = null;
			DISCHARGE_DESC = null;
			PLACE_DESC = null;
			pay_term_desc = null;
			CHA_NAME = null;
			NOTIFY_NAME = null;
			FWD_NAME = null;

			MANUF_DESC = null;
			customfwd_auth = null;
			YEAR = null;
			COMPANY = null;
			INV_NO = null;
			LUT_IGST=null;
			return SUCCESS;
		} else {

			// addActionMessage("Records Not Save(s) !!");
			return ERROR;
		}


	}

	public String prelicence() throws SQLException {

		// List InvLicenceList = new ArrayList<PreInvLicenceBean>();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String planNo=request. getParameter("plan_no");
		String company=request. getParameter("company");
		String year=request. getParameter("year");
		String invNo=request. getParameter("invNo"); 
		String GetDataFlag=request. getParameter("GetDataFlag");
		String header[]=null;
		if(getMap().get(planNo)!=null){
			PreInvoiceHeader invoice=(PreInvoiceHeader)getMap().get(planNo);
			if(invoice!=null){
			 //header=invoice.split(",");
			 	setFCTOTAL(invoice.getFobfc());
				setQTYTOTAL(invoice.getInvoiceQty());
		        setLocationCode(invoice.getLocationCode());
			}
		}
		
		if(planNo!=null){
			plan_no=planNo;
		}
		if(company!=null){
			COMPANY=company;

		}
		if(year!=null){
			YEAR=year;

		}
		if(invNo!=null){
			INV_NO=invNo;
		}
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		Connection conn = null;
		PreparedStatement stat = null;
		PreparedStatement stat1 = null;
		ResultSet result = null;
		ResultSet result1 = null;
		try {


			try {
				conn = new connection().getConnection();
				conn.setAutoCommit(false);

			} catch (Exception e) {
				System.out.println(e.toString());
			}
			int flag = 0;
			String expdesc = "";
			String bedesc = "";
			double lic_amt_ob = 0;
			double lic_qty_ob = 0;
			double lic_amt_issue = 0;
			double lic_qty_issue = 0;
			stat = conn.prepareStatement("select ref_type,ref_no,qty,adjs_qty,fob_amt,B_E_NO,QTY_SQM,EXP_REF_CTRL_NO,IMP_REF_CTRL_NO,IO_NORMS,LIC_DATE,LIC_LOCT,LIC_COMP from ei_endors_lc_lic_dtls a where a.year=? and a.company=? and a.inv_no=? ");
			stat.setString(1, YEAR);
			stat.setString(2, COMPANY);
			stat.setString(3, INV_NO);
			result = stat.executeQuery();
			while (result.next()) {
				stat1 = conn.prepareStatement(" select b.item_desc,round(b.fc_amt+(b.fc_amt*nvl(ADD_VALUE,0))/100,2) exp_ob,b.amt_issue,b.qty,b.qty_issue from pi_imp_lic_mast a,pi_imp_lic_exp b where  a.REF_TYPE<>'LC' AND a.ref_type=b.ref_type(+) and a.ref_no=B.ref_no(+)  and (is_epc = 'N' and  is_lc = 'N')  and b.ref_type=? and b.ref_no=?  and b.item_no  =?");
				stat1.setString(1, result.getString("ref_type"));
				stat1.setString(2, result.getString("ref_no"));
				stat1.setString(3, result.getString("EXP_REF_CTRL_NO"));
				result1 = stat1.executeQuery();
				while (result1.next()) {
					expdesc = result1.getString("item_desc");
					lic_amt_ob = result1.getDouble("exp_ob");
					lic_qty_ob = result1.getDouble("qty");
					lic_amt_issue = result1.getDouble("amt_issue");
					lic_qty_issue = result1.getDouble("qty_issue");
				}

				stat1 = conn.prepareStatement(" select distinct B.be_desc from pi_imp_awbl_mast A, pi_imp_cinv_lic_dtls b, pi_imp_awbl_cinv c, pi_imp_boe_dtls d , pi_imp_lic_dtls e  where  a.ref_no=c.ref_no and b.ind_no = c.ind_no and b.cinv_no = c.cinv_no AND d.ref_no = a.ref_No "
						+ "and e.ref_type = b.lic_type and e.ref_no = b.lic_no and e.item_no = b.item_no and b.lic_type=? and b.lic_no=? and b.item_no = ? and d.be_no = ? ");
				stat1.setString(1, result.getString("ref_type"));
				stat1.setString(2, result.getString("ref_no"));
				stat1.setString(3, result.getString("IMP_REF_CTRL_NO"));
				stat1.setString(4, result.getString("B_E_NO"));
				result1 = stat1.executeQuery();
				while (result1.next()) {
					bedesc = result1.getString("be_desc");

				}
				InvLicenceList.add(new PreInvLicenceBean(result.getString("ref_type"), result.getString("ref_no"), result.getString("B_E_NO"), result.getString("lic_date"), result.getString("lic_comp"), result.getString("lic_loct"), roundTwoDecimals(result.getDouble("qty")), roundTwoDecimals(result.getDouble("adjs_qty")), roundTwoDecimals(result.getDouble("FOB_AMT")), roundTwoDecimals(result.getDouble("QTY_SQM")), roundFourDecimals(result.getDouble("IO_NORMS")), result.getString("EXP_REF_CTRL_NO"), result.getString("IMP_REF_CTRL_NO"), bedesc, expdesc, lic_amt_ob, lic_amt_issue, lic_qty_ob, lic_qty_issue));

			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
		} finally {
			if (result != null) {
				result.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (result1 != null) {
				result1.close();
			}
			if (stat1 != null) {
				stat1.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return "prelicence";
	}

	public String prelicview() throws SQLException {

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {
			LicenceList = new PreInvoiceDao().getPreLicence(unitparam);

		}
		return "prelicview";
	}

	public String predbkslview() throws SQLException {

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {

			unitList = new PreInvoiceDao().getPreDBKSL(unitparam, INVDATE);

		}
		return "predbkslview";
	}

	public String preroslslview() throws SQLException {

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {

			unitList = new PreInvoiceDao().getPreROSLSL(unitparam, INVDATE);

		}
		return "preroslslview";
	}

	public String prestrslview() throws SQLException {

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		if (INVDATE != null && INVDATE.length() > 0) {
			if (PARAB.equals("STRMISC")) {

				unitList = new PreInvoiceDao().getPreSTRSL(unitparam, PARAB, INVDATE);
			} else {
				unitList = new PreInvoiceDao().getPreSTRSL(dbkslnocopy, PARAB, INVDATE);
			}

		}
		return "prestrslview";
	}

	public String prebeview() throws SQLException {
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (LIC_TYPE != null && LIC_TYPE.length() > 0) {
			BEList = new PreInvoiceDao().getPreBE(unitparam, LIC_TYPE, LIC_NO);
		}
		return "prebeview";
	}

	public String CsytabView() throws SQLException {

		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		if (unitparam != null && unitparam.length() > 0 && TYPE_CODE != null && TYPE_CODE.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
			unitList = dao.getCsytabByName(unitparam, TYPE_CODE);
		}
		return "csytab";
	}

	public String GruptypeView() throws SQLException {
		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		if (unitparam != null && unitparam.length() > 0 && TYPE_CODE != null && TYPE_CODE.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
			unitList = dao.getGruptype(unitparam, TYPE_CODE);
		}
		return "gruptype";
	}

	public String chaView() throws SQLException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");

		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
			unitList = dao.getCHAList(unitparam);
		}
		return "chaview";
	}

	public String notifyView() throws SQLException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");

		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
			unitList = dao.getNotifyList(unitparam);
		}
		return "notifyview";
	}

	public String lcView() throws SQLException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");

		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
			unitList = dao.getlcList(unitparam);
		}
		return "lcview";
	}

	public String unitView() throws SQLException {

		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		if (unitparam != null && unitparam.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();

			unitList = dao.getUnitListByName(unitparam, TYPE_CODE);
		}
		return "unitview";
	}

	public String gstcmpView() throws SQLException {

		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();

			unitList = dao.getgstcmpListByName(unitparam, unitparam);
		}
		return "gstcmpView";
	}

	public String taxcodeView() throws SQLException {

		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		if (unitparam != null && unitparam.length() > 0) {
			shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
			unitList = dao.getTaxCodeList(unitparam);
		}
		return "taxcodeview";
	}

	public String catgView() throws SQLException {

		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		CATG_LIST = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao().getcatg(unitparam, DESTI_CNTRY);
		return "catg";
	}

	public String ajxcatgView() throws SQLException, UnsupportedEncodingException {
		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();

		String placedesc = dao.getcatgAjax(unitparam, TYPE_CODE);

		if ((placedesc != null) && (!placedesc.equals(""))) {
			this.inputStream = new ByteArrayInputStream(placedesc.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxdbkslnoView() throws SQLException, UnsupportedEncodingException {

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();

		String placedesc = dao.getdbkslnoAjax(unitparam, PARAA, INVDATE);

		if ((placedesc != null) && (!placedesc.equals(""))) {
			this.inputStream = new ByteArrayInputStream(placedesc.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxroslslnoView() throws SQLException, UnsupportedEncodingException {

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();

		String placedesc = dao.getroslslnoAjax(unitparam, PARAA, INVDATE);

		if ((placedesc != null) && (!placedesc.equals(""))) {
			this.inputStream = new ByteArrayInputStream(placedesc.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxstrslnoView() throws SQLException, UnsupportedEncodingException {

		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}

		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		String placedesc = dao.getstrslnoAjax(unitparam, PARAA, PARAB, dbkslnocopy, INVDATE);

		if ((placedesc != null) && (!placedesc.equals(""))) {
			this.inputStream = new ByteArrayInputStream(placedesc.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String coappr() throws SQLException, UnsupportedEncodingException {

		//List unitList = new ArrayList<UnitBean>();
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
		if (usrid == null) {
			addActionError("Session is not Available");

			return ERROR;
		}
		MSG = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao().getCoAppr(YEAR, COMPANY, INV_NO);
		if ((this.MSG != null) && (!this.MSG.equals(""))) {
			this.inputStream = new ByteArrayInputStream(this.MSG.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return SUCCESS;
	}

	public String GSTCMP() throws SQLException {
		String ls = "";


		if (INV_COMP != null && INV_COMP.length() > 0) {
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			try {

				if (INV_COMP != null && INV_COMP.length() > 0) {
					conn = new connection().getConnection();
					st = conn.prepareStatement(" select  OAADK3,OACONM,trim(OAADR1)||' '||trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,aRXCSN cst,aRXLSN tin, "
							+ " arxlcn gstin,arfre1 statecode,OAGEOC  from seplweb.ciaddr_VIEW115 a,seplweb.xinddr_view115 b ,seplweb.pr_ship_plan_master P "
							+ " where oacono=111 and oaadth=1 and  oaadk2 in ('100','210','200') and "
							+ " oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and OAECAR=P.state_code and p.plan_numb=? order by 1   ");
					st.setString(1, INV_COMP);

					rs = st.executeQuery();
					while (rs.next()) {

						ls += rs.getString("OAADK3") + "#" + rs.getString("OACONM") + rs.getString("addr") + "#" + rs.getString("OAGEOC") + "#" + rs.getString("gstin") + "#" + INV_COMP;

					}

					if ((ls != null) && (!ls.equals(""))) {
						this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
					} else {
						this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
					}
				}
			} catch (Exception ee) {
				System.out.println(ee.toString());

			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}

			}
		}
		return "success";
	}

	public String roundToDecimals(double d, int c) {
		double temp = (double) ((d * Math.pow(10, c)));
		temp = Math.round(temp);

		double aa = (((double) temp) / Math.pow(10, c));

		return String.format("%." + c + "f", aa);
	}

	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}

	double roundFourDecimals(double d) {
		DecimalFormat fourDForm = new DecimalFormat("#######.####");
		return Double.valueOf(fourDForm.format(d));
	}

	public String ajxplace() throws SQLException, ParseException, UnsupportedEncodingException {
		Map session = ActionContext.getContext().getSession();
		String usrid = ((String) session.get("sessUserId"));
		if (usrid == null) {
			addActionError("Session is not Available");
			return "error";
		}
		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		String placedesc = dao.getCsytabByNameAjax(unitparam, TYPE_CODE);
		//System.out.println(placedesc);
		if ((placedesc != null) && (!placedesc.equals(""))) {
			this.inputStream = new ByteArrayInputStream(placedesc.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxgrpview() throws SQLException, ParseException, UnsupportedEncodingException {
		Map session = ActionContext.getContext().getSession();
		String usrid = ((String) session.get("sessUserId"));
		if (usrid == null) {
			addActionError("Session is not Available");
			return "error";
		}
		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		String value = dao.getGruptypeAjax(unitparam, TYPE_CODE);
		//System.out.println(value);
		if ((value != null) && (!value.equals(""))) {
			this.inputStream = new ByteArrayInputStream(value.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxglcview() throws SQLException, ParseException, UnsupportedEncodingException {
		Map session = ActionContext.getContext().getSession();
		String usrid = ((String) session.get("sessUserId"));
		if (usrid == null) {
			addActionError("Session is not Available");
			return "error";
		}
		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		String value = dao.getlcListAjax(unitparam);
		//System.out.println(value);
		if ((value != null) && (!value.equals(""))) {
			this.inputStream = new ByteArrayInputStream(value.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxglchaiew() throws SQLException, ParseException, UnsupportedEncodingException {
		Map session = ActionContext.getContext().getSession();
		String usrid = ((String) session.get("sessUserId"));
		if (usrid == null) {
			addActionError("Session is not Available");
			return "error";
		}
		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		String value = dao.getCHAListAjax(unitparam);

		if ((value != null) && (!value.equals(""))) {
			this.inputStream = new ByteArrayInputStream(value.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxnotifyview() throws SQLException, ParseException, UnsupportedEncodingException {
		Map session = ActionContext.getContext().getSession();
		String usrid = ((String) session.get("sessUserId"));
		if (usrid == null) {
			addActionError("Session is not Available");
			return "error";
		}
		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		String value = dao.getnotifyListAjax(unitparam);
		//System.out.println(value);
		if ((value != null) && (!value.equals(""))) {
			this.inputStream = new ByteArrayInputStream(value.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxunitview() throws SQLException, ParseException, UnsupportedEncodingException {
		Map session = ActionContext.getContext().getSession();
		String usrid = ((String) session.get("sessUserId"));
		if (usrid == null) {
			addActionError("Session is not Available");
			return "error";
		}
		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		//System.out.println("Ajax unitparam " + unitparam + " TYPE " + TYPE_CODE);
		String value = dao.getUnitListByNameAjax(unitparam, TYPE_CODE);
		//System.out.println(value); 
		if ((value != null) && (!value.equals(""))) {
			this.inputStream = new ByteArrayInputStream(value.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String ajxtaxview() throws SQLException, ParseException, UnsupportedEncodingException {
		Map session = ActionContext.getContext().getSession();
		String usrid = ((String) session.get("sessUserId"));
		if (usrid == null) {
			addActionError("Session is not Available");
			return "error";
		}
		shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
		String value = dao.getTaxCodeListAjax(unitparam);
		if ((value != null) && (!value.equals(""))) {
			this.inputStream = new ByteArrayInputStream(value.getBytes("UTF-8"));
		} else {
			this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
		}
		return "success";
	}

	public String getAausrid() {
		return aausrid;
	}

	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}

	public String getSearchinv() {
		return searchinv;
	}

	public void setSearchinv(String searchinv) {
		this.searchinv = searchinv;
	}

	public String getSearchplan() {
		return searchplan;
	}

	public void setSearchplan(String searchplan) {
		this.searchplan = searchplan;
	}

	public List getShowList() {
		return showList;
	}

	public void setShowList(List showList) {
		this.showList = showList;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	public String getBuyer_address() {
		return buyer_address;
	}

	public void setBuyer_address(String buyer_address) {
		this.buyer_address = buyer_address;
	}

	public String getAc_holder() {
		return ac_holder;
	}

	public void setAc_holder(String ac_holder) {
		this.ac_holder = ac_holder;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getCost_centre() {
		return cost_centre;
	}

	public void setCost_centre(String cost_centre) {
		this.cost_centre = cost_centre;
	}

	public String getEtd_date() {
		return etd_date;
	}

	public void setEtd_date(String etd_date) {
		this.etd_date = etd_date;
	}

	public String getExcs_inv_no() {
		return excs_inv_no;
	}

	public void setExcs_inv_no(String excs_inv_no) {
		this.excs_inv_no = excs_inv_no;
	}

	public String getFin_date() {
		return fin_date;
	}

	public void setFin_date(String fin_date) {
		this.fin_date = fin_date;
	}

	public String getFwd_custom() {
		return fwd_custom;
	}

	public void setFwd_custom(String fwd_custom) {
		this.fwd_custom = fwd_custom;
	}

	public String getFwd_date() {
		return fwd_date;
	}

	public void setFwd_date(String fwd_date) {
		this.fwd_date = fwd_date;
	}

	public String getInv_date() {
		return inv_date;
	}

	public void setInv_date(String inv_date) {
		this.inv_date = inv_date;
	}

	public String getInv_qty() {
		return inv_qty;
	}

	public void setInv_qty(String inv_qty) {
		this.inv_qty = inv_qty;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMode_of_ship() {
		return mode_of_ship;
	}

	public void setMode_of_ship(String mode_of_ship) {
		this.mode_of_ship = mode_of_ship;
	}

	public String getPlan_no() {
		return plan_no;
	}

	public void setPlan_no(String plan_no) {
		this.plan_no = plan_no;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getTto_date() {
		return tto_date;
	}

	public void setTto_date(String tto_date) {
		this.tto_date = tto_date;
	}

	public String getBuyer_addr() {
		return buyer_addr;
	}

	public void setBuyer_addr(String buyer_addr) {
		this.buyer_addr = buyer_addr;
	}

	public String getCons_addr() {
		return cons_addr;
	}

	public void setCons_addr(String cons_addr) {
		this.cons_addr = cons_addr;
	}

	public String getCons_address() {
		return cons_address;
	}

	public void setCons_address(String cons_address) {
		this.cons_address = cons_address;
	}

	public String getCons_name() {
		return cons_name;
	}

	public void setCons_name(String cons_name) {
		this.cons_name = cons_name;
	}

	public String getFirst_sale() {
		return first_sale;
	}

	public void setFirst_sale(String first_sale) {
		this.first_sale = first_sale;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getCRNCY_CODE() {
		return CRNCY_CODE;
	}

	public void setCRNCY_CODE(String CRNCY_CODE) {
		this.CRNCY_CODE = CRNCY_CODE;
	}

	public String getMANUF_STATE() {
		return MANUF_STATE;
	}

	public void setMANUF_STATE(String MANUF_STATE) {
		this.MANUF_STATE = MANUF_STATE;
	}

	public String getTAX_CAL_PER() {
		return TAX_CAL_PER;
	}

	public void setTAX_CAL_PER(String TAX_CAL_PER) {
		this.TAX_CAL_PER = TAX_CAL_PER;
	}

	public String getTAX_CODE() {
		return TAX_CODE;
	}

	public void setTAX_CODE(String TAX_CODE) {
		this.TAX_CODE = TAX_CODE;
	}

	public String getTAX_PERCENT() {
		return TAX_PERCENT;
	}

	public void setTAX_PERCENT(String TAX_PERCENT) {
		this.TAX_PERCENT = TAX_PERCENT;
	}

	public String getTAX_TYPE() {
		return TAX_TYPE;
	}

	public void setTAX_TYPE(String TAX_TYPE) {
		this.TAX_TYPE = TAX_TYPE;
	}

	public String getSHIP_DESC() {
		return SHIP_DESC;
	}

	public void setSHIP_DESC(String SHIP_DESC) {
		this.SHIP_DESC = SHIP_DESC;
	}

	public String getShip_cancel() {
		return ship_cancel;
	}

	public void setShip_cancel(String ship_cancel) {
		this.ship_cancel = ship_cancel;
	}

	public String getOuthouse() {
		return outhouse;
	}

	public void setOuthouse(String outhouse) {
		this.outhouse = outhouse;
	}

	public String getPremast() {
		return premast;
	}

	public void setPremast(String premast) {
		this.premast = premast;
	}

	public String getSearchfrom() {
		return searchfrom;
	}

	public void setSearchfrom(String searchfrom) {
		this.searchfrom = searchfrom;
	}

	public String getSearchinv1() {
		return searchinv1;
	}

	public void setSearchinv1(String searchinv1) {
		this.searchinv1 = searchinv1;
	}

	public String getSearchplan1() {
		return searchplan1;
	}

	public void setSearchplan1(String searchplan1) {
		this.searchplan1 = searchplan1;
	}

	public String getSearchto() {
		return searchto;
	}

	public void setSearchto(String searchto) {
		this.searchto = searchto;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getPprq_date() {
		return pprq_date;
	}

	public void setPprq_date(String pprq_date) {
		this.pprq_date = pprq_date;
	}

	public String getCHA_NAME() {
		return CHA_NAME;
	}

	public void setCHA_NAME(String CHA_NAME) {
		this.CHA_NAME = CHA_NAME;
	}

	public String getFWD_NAME() {
		return FWD_NAME;
	}

	public void setFWD_NAME(String FWD_NAME) {
		this.FWD_NAME = FWD_NAME;
	}

	public String getCLR_PORT() {
		return CLR_PORT;
	}

	public void setCLR_PORT(String CLR_PORT) {
		this.CLR_PORT = CLR_PORT;
	}

	public String getLOADING_PORT() {
		return LOADING_PORT;
	}

	public void setLOADING_PORT(String LOADING_PORT) {
		this.LOADING_PORT = LOADING_PORT;
	}

	public String getDESTI_CNTRY() {
		return DESTI_CNTRY;
	}

	public void setDESTI_CNTRY(String DESTI_CNTRY) {
		this.DESTI_CNTRY = DESTI_CNTRY;
	}

	public String getCNTRY_ORIGIN() {
		return CNTRY_ORIGIN;
	}

	public void setCNTRY_ORIGIN(String CNTRY_ORIGIN) {
		this.CNTRY_ORIGIN = CNTRY_ORIGIN;
	}

	public String getDISCHARGE() {
		return DISCHARGE;
	}

	public void setDISCHARGE(String DISCHARGE) {
		this.DISCHARGE = DISCHARGE;
	}

	public String getDESTI_CODE() {
		return DESTI_CODE;
	}

	public void setDESTI_CODE(String DESTI_CODE) {
		this.DESTI_CODE = DESTI_CODE;
	}

	public String getDIS_CNTRY() {
		return DIS_CNTRY;
	}

	public void setDIS_CNTRY(String DIS_CNTRY) {
		this.DIS_CNTRY = DIS_CNTRY;
	}

	public String getCurrentdate() {
		return currentdate;
	}

	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}

	public String getSearchloct() {
		return searchloct;
	}

	public void setSearchloct(String searchloct) {
		this.searchloct = searchloct;
	}

	public String getGetDataFlag() {
		return GetDataFlag;
	}

	public void setGetDataFlag(String GetDataFlag) {
		this.GetDataFlag = GetDataFlag;
	}

	public String getSelf_tp() {
		return self_tp;
	}

	public void setSelf_tp(String self_tp) {
		this.self_tp = self_tp;
	}

	public String getExp_type() {
		return exp_type;
	}

	public void setExp_type(String exp_type) {
		this.exp_type = exp_type;
	}

	public String getHs_code() {
		return hs_code;
	}

	public void setHs_code(String hs_code) {
		this.hs_code = hs_code;
	}

	public String getLcno() {
		return lcno;
	}

	public void setLcno(String lcno) {
		this.lcno = lcno;
	}

	public String getPre_carriage() {
		return pre_carriage;
	}

	public void setPre_carriage(String pre_carriage) {
		this.pre_carriage = pre_carriage;
	}

	public String getComm_per() {
		return comm_per;
	}

	public void setComm_per(String comm_per) {
		this.comm_per = comm_per;
	}

	public String getUpcharge_per() {
		return upcharge_per;
	}

	public void setUpcharge_per(String upcharge_per) {
		this.upcharge_per = upcharge_per;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getFwd_code() {
		return fwd_code;
	}

	public void setFwd_code(String fwd_code) {
		this.fwd_code = fwd_code;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public String getShip_term() {
		return ship_term;
	}

	public void setShip_term(String ship_term) {
		this.ship_term = ship_term;
	}

	public String getPayment_term() {
		return payment_term;
	}

	public void setPayment_term(String payment_term) {
		this.payment_term = payment_term;
	}

	public String getPay_term() {
		return pay_term;
	}

	public void setPay_term(String pay_term) {
		this.pay_term = pay_term;
	}

	public List getPaymenttermList() {
		return PaymenttermList;
	}

	public void setPaymenttermList(List PaymenttermList) {
		this.PaymenttermList = PaymenttermList;
	}

	public String getPay_term_desc() {
		return pay_term_desc;
	}

	public void setPay_term_desc(String pay_term_desc) {
		this.pay_term_desc = pay_term_desc;
	}

	public String getPLACE() {
		return PLACE;
	}

	public void setPLACE(String PLACE) {
		this.PLACE = PLACE;
	}

	public String getMANUF_CODE() {
		return MANUF_CODE;
	}

	public void setMANUF_CODE(String MANUF_CODE) {
		this.MANUF_CODE = MANUF_CODE;
	}

	public String getPLACE_DESC() {
		return PLACE_DESC;
	}

	public void setPLACE_DESC(String PLACE_DESC) {
		this.PLACE_DESC = PLACE_DESC;
	}

	public String getUnitparam() {
		return unitparam;
	}

	public void setUnitparam(String unitparam) {
		this.unitparam = unitparam;
	}

	public String getPARAA() {
		return PARAA;
	}

	public void setPARAA(String PARAA) {
		this.PARAA = PARAA;
	}

	public String getPARAB() {
		return PARAB;
	}

	public void setPARAB(String PARAB) {
		this.PARAB = PARAB;
	}

	public String getTYPE_CODE() {
		return TYPE_CODE;
	}

	public void setTYPE_CODE(String TYPE_CODE) {
		this.TYPE_CODE = TYPE_CODE;
	}

	public List getUnitList() {
		return unitList;
	}

	public void setUnitList(List unitList) {
		this.unitList = unitList;
	}

	public String getCLR_PORT_DESC() {
		return CLR_PORT_DESC;
	}

	public void setCLR_PORT_DESC(String CLR_PORT_DESC) {
		this.CLR_PORT_DESC = CLR_PORT_DESC;
	}

	public String getLOADING_PORT_DESC() {
		return LOADING_PORT_DESC;
	}

	public void setLOADING_PORT_DESC(String LOADING_PORT_DESC) {
		this.LOADING_PORT_DESC = LOADING_PORT_DESC;
	}

	public String getDISCHARGE_DESC() {
		return DISCHARGE_DESC;
	}

	public void setDISCHARGE_DESC(String DISCHARGE_DESC) {
		this.DISCHARGE_DESC = DISCHARGE_DESC;
	}

	public String getDESTI_CNTRY_DESC() {
		return DESTI_CNTRY_DESC;
	}

	public void setDESTI_CNTRY_DESC(String DESTI_CNTRY_DESC) {
		this.DESTI_CNTRY_DESC = DESTI_CNTRY_DESC;
	}

	public String getDIS_CNTRY_DESC() {
		return DIS_CNTRY_DESC;
	}

	public void setDIS_CNTRY_DESC(String DIS_CNTRY_DESC) {
		this.DIS_CNTRY_DESC = DIS_CNTRY_DESC;
	}

	public String getDESTI_CODE_DESC() {
		return DESTI_CODE_DESC;
	}

	public void setDESTI_CODE_DESC(String DESTI_CODE_DESC) {
		this.DESTI_CODE_DESC = DESTI_CODE_DESC;
	}

	public String getCNTRY_ORIGIN_DESC() {
		return CNTRY_ORIGIN_DESC;
	}

	public void setCNTRY_ORIGIN_DESC(String CNTRY_ORIGIN_DESC) {
		this.CNTRY_ORIGIN_DESC = CNTRY_ORIGIN_DESC;
	}

	public String getNOTIFY_NAME() {
		return NOTIFY_NAME;
	}

	public void setNOTIFY_NAME(String NOTIFY_NAME) {
		this.NOTIFY_NAME = NOTIFY_NAME;
	}

	public String getMANUF_DESC() {
		return MANUF_DESC;
	}

	public void setMANUF_DESC(String MANUF_DESC) {
		this.MANUF_DESC = MANUF_DESC;
	}

	public String getTo_inr_conv() {
		return to_inr_conv;
	}

	public void setTo_inr_conv(String to_inr_conv) {
		this.to_inr_conv = to_inr_conv;
	}

	public String getYEAR() {
		return YEAR;
	}

	public void setYEAR(String YEAR) {
		this.YEAR = YEAR;
	}

	public String getCOMPANY() {
		return COMPANY;
	}

	public void setCOMPANY(String COMPANY) {
		this.COMPANY = COMPANY;
	}

	public String getINV_NO() {
		return INV_NO;
	}

	public void setINV_NO(String INV_NO) {
		this.INV_NO = INV_NO;
	}

	public List getSHIP_TYPE_LIST() {
		return SHIP_TYPE_LIST;
	}

	public void setSHIP_TYPE_LIST(List SHIP_TYPE_LIST) {
		this.SHIP_TYPE_LIST = SHIP_TYPE_LIST;
	}

	public List getCATG_LIST() {
		return CATG_LIST;
	}

	public void setCATG_LIST(List CATG_LIST) {
		this.CATG_LIST = CATG_LIST;
	}

	public List getInvLineList() {
		return InvLineList;
	}

	public void setInvLineList(List InvLineList) {
		this.InvLineList = InvLineList;
	}

	public List getInvLicenceList() {
		return InvLicenceList;
	}

	public void setInvLicenceList(List InvLicenceList) {
		this.InvLicenceList = InvLicenceList;
	}

	public List getLicenceList() {
		return LicenceList;
	}

	public void setLicenceList(List LicenceList) {
		this.LicenceList = LicenceList;
	}

	public List getACCR_TYPE_LIST() {
		return ACCR_TYPE_LIST;
	}

	public void setACCR_TYPE_LIST(List ACCR_TYPE_LIST) {
		this.ACCR_TYPE_LIST = ACCR_TYPE_LIST;
	}

	public List getBEList() {
		return BEList;
	}

	public void setBEList(List BEList) {
		this.BEList = BEList;
	}

	public String getDbkslnocopy() {
		return dbkslnocopy;
	}

	public void setDbkslnocopy(String dbkslnocopy) {
		this.dbkslnocopy = dbkslnocopy;
	}

	public String getINVDATE() {
		return INVDATE;
	}

	public void setINVDATE(String INVDATE) {
		this.INVDATE = INVDATE;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	public String getAccrFlag() {
		return accrFlag;
	}

	public void setAccrFlag(String accrFlag) {
		this.accrFlag = accrFlag;
	}

	public String getTransport_cost() {
		return transport_cost;
	}

	public void setTransport_cost(String transport_cost) {
		this.transport_cost = transport_cost;
	}

	public String getCO_MOS() {
		return CO_MOS;
	}

	public void setCO_MOS(String CO_MOS) {
		this.CO_MOS = CO_MOS;
	}

	public List getCO_NO_E() {
		return CO_NO_E;
	}

	public void setCO_NO_E(List CO_NO_E) {
		this.CO_NO_E = CO_NO_E;
	}

	public List getCO_LINE_E() {
		return CO_LINE_E;
	}

	public void setCO_LINE_E(List CO_LINE_E) {
		this.CO_LINE_E = CO_LINE_E;
	}

	public List getITEM_NO_E() {
		return ITEM_NO_E;
	}

	public void setITEM_NO_E(List ITEM_NO_E) {
		this.ITEM_NO_E = ITEM_NO_E;
	}

	public List getQTY_ENDORS_E() {
		return QTY_ENDORS_E;
	}

	public void setQTY_ENDORS_E(List QTY_ENDORS_E) {
		this.QTY_ENDORS_E = QTY_ENDORS_E;
	}

	public List getCO_UOM_E() {
		return CO_UOM_E;
	}

	public void setCO_UOM_E(List CO_UOM_E) {
		this.CO_UOM_E = CO_UOM_E;
	}

	public List getQTY_KGS() {
		return QTY_KGS;
	}

	public void setQTY_KGS(List QTY_KGS) {
		this.QTY_KGS = QTY_KGS;
	}

	public List getPRICE_FC_E() {
		return PRICE_FC_E;
	}

	public void setPRICE_FC_E(List PRICE_FC_E) {
		this.PRICE_FC_E = PRICE_FC_E;
	}

	public List getPRICE_MISC() {
		return PRICE_MISC;
	}

	public void setPRICE_MISC(List PRICE_MISC) {
		this.PRICE_MISC = PRICE_MISC;
	}

	public List getADJUST_FC() {
		return ADJUST_FC;
	}

	public void setADJUST_FC(List ADJUST_FC) {
		this.ADJUST_FC = ADJUST_FC;
	}

	public List getSR_NO() {
		return SR_NO;
	}

	public void setSR_NO(List SR_NO) {
		this.SR_NO = SR_NO;
	}

	public List getCATG_CODE() {
		return CATG_CODE;
	}

	public void setCATG_CODE(List CATG_CODE) {
		this.CATG_CODE = CATG_CODE;
	}

	public List getINV_DESC() {
		return INV_DESC;
	}

	public void setINV_DESC(List INV_DESC) {
		this.INV_DESC = INV_DESC;
	}

	public List getSTR_MISC() {
		return STR_MISC;
	}

	public void setSTR_MISC(List STR_MISC) {
		this.STR_MISC = STR_MISC;
	}

	public List getDBK_SLNO() {
		return DBK_SLNO;
	}

	public void setDBK_SLNO(List DBK_SLNO) {
		this.DBK_SLNO = DBK_SLNO;
	}

	public List getSTR_SLNO() {
		return STR_SLNO;
	}

	public void setSTR_SLNO(List STR_SLNO) {
		this.STR_SLNO = STR_SLNO;
	}

	public List getGR_DECL_AMT_E() {
		return GR_DECL_AMT_E;
	}

	public void setGR_DECL_AMT_E(List GR_DECL_AMT_E) {
		this.GR_DECL_AMT_E = GR_DECL_AMT_E;
	}

	public List getNET_PRICE_E() {
		return NET_PRICE_E;
	}

	public void setNET_PRICE_E(List NET_PRICE_E) {
		this.NET_PRICE_E = NET_PRICE_E;
	}

	public List getTOKEN_NO() {
		return TOKEN_NO;
	}

	public void setTOKEN_NO(List TOKEN_NO) {
		this.TOKEN_NO = TOKEN_NO;
	}

	public List getPRE_PRINT_NO() {
		return PRE_PRINT_NO;
	}

	public void setPRE_PRINT_NO(List PRE_PRINT_NO) {
		this.PRE_PRINT_NO = PRE_PRINT_NO;
	}

	public List getGR_DECL_PER() {
		return GR_DECL_PER;
	}

	public void setGR_DECL_PER(List GR_DECL_PER) {
		this.GR_DECL_PER = GR_DECL_PER;
	}

	public List getTEMP_CAT() {
		return TEMP_CAT;
	}

	public void setTEMP_CAT(List TEMP_CAT) {
		this.TEMP_CAT = TEMP_CAT;
	}

	public List getMADE_FOR() {
		return MADE_FOR;
	}

	public void setMADE_FOR(List MADE_FOR) {
		this.MADE_FOR = MADE_FOR;
	}

	public String getGETREFRESH() {
		return GETREFRESH;
	}

	public void setGETREFRESH(String GETREFRESH) {
		this.GETREFRESH = GETREFRESH;
	}

	public List getACCR_CO_NO() {
		return ACCR_CO_NO;
	}

	public void setACCR_CO_NO(List ACCR_CO_NO) {
		this.ACCR_CO_NO = ACCR_CO_NO;
	}

	public List getACCR_CO_LINE() {
		return ACCR_CO_LINE;
	}

	public void setACCR_CO_LINE(List ACCR_CO_LINE) {
		this.ACCR_CO_LINE = ACCR_CO_LINE;
	}

	public List getACCR_ITEM_NO() {
		return ACCR_ITEM_NO;
	}

	public void setACCR_ITEM_NO(List ACCR_ITEM_NO) {
		this.ACCR_ITEM_NO = ACCR_ITEM_NO;
	}

	public List getACCR_TYPE() {
		return ACCR_TYPE;
	}

	public void setACCR_TYPE(List ACCR_TYPE) {
		this.ACCR_TYPE = ACCR_TYPE;
	}

	public List getACCR_DBKSLNO() {
		return ACCR_DBKSLNO;
	}

	public void setACCR_DBKSLNO(List ACCR_DBKSLNO) {
		this.ACCR_DBKSLNO = ACCR_DBKSLNO;
	}

	public List getACCR_STRSLNO() {
		return ACCR_STRSLNO;
	}

	public void setACCR_STRSLNO(List ACCR_STRSLNO) {
		this.ACCR_STRSLNO = ACCR_STRSLNO;
	}

	public List getACCR_QTY() {
		return ACCR_QTY;
	}

	public void setACCR_QTY(List ACCR_QTY) {
		this.ACCR_QTY = ACCR_QTY;
	}

	public List getACCR_PRICE() {
		return ACCR_PRICE;
	}

	public void setACCR_PRICE(List ACCR_PRICE) {
		this.ACCR_PRICE = ACCR_PRICE;
	}

	public List getAccrLineList() {
		return AccrLineList;
	}

	public void setAccrLineList(List AccrLineList) {
		this.AccrLineList = AccrLineList;
	}

	public String getLIC_TYPE() {
		return LIC_TYPE;
	}

	public void setLIC_TYPE(String LIC_TYPE) {
		this.LIC_TYPE = LIC_TYPE;
	}

	public String getLIC_NO() {
		return LIC_NO;
	}

	public void setLIC_NO(String LIC_NO) {
		this.LIC_NO = LIC_NO;
	}

	public List getLIC_DATE() {
		return LIC_DATE;
	}

	public void setLIC_DATE(List LIC_DATE) {
		this.LIC_DATE = LIC_DATE;
	}

	public List getLIC_COMP() {
		return LIC_COMP;
	}

	public void setLIC_COMP(List LIC_COMP) {
		this.LIC_COMP = LIC_COMP;
	}

	public List getLIC_LOCT() {
		return LIC_LOCT;
	}

	public void setLIC_LOCT(List LIC_LOCT) {
		this.LIC_LOCT = LIC_LOCT;
	}

	public List getQTY() {
		return QTY;
	}

	public void setQTY(List QTY) {
		this.QTY = QTY;
	}

	public List getADJS_QTY() {
		return ADJS_QTY;
	}

	public void setADJS_QTY(List ADJS_QTY) {
		this.ADJS_QTY = ADJS_QTY;
	}

	public List getQTY_SQM() {
		return QTY_SQM;
	}

	public void setQTY_SQM(List QTY_SQM) {
		this.QTY_SQM = QTY_SQM;
	}

	public List getFOB_AMT() {
		return FOB_AMT;
	}

	public void setFOB_AMT(List FOB_AMT) {
		this.FOB_AMT = FOB_AMT;
	}

	public List getBE_NO() {
		return BE_NO;
	}

	public void setBE_NO(List BE_NO) {
		this.BE_NO = BE_NO;
	}

	public List getIO_NORMS() {
		return IO_NORMS;
	}

	public void setIO_NORMS(List IO_NORMS) {
		this.IO_NORMS = IO_NORMS;
	}

	public List getEXP_CTRL_NO() {
		return EXP_CTRL_NO;
	}

	public void setEXP_CTRL_NO(List EXP_CTRL_NO) {
		this.EXP_CTRL_NO = EXP_CTRL_NO;
	}

	public List getIMP_CTRL_NO() {
		return IMP_CTRL_NO;
	}

	public void setIMP_CTRL_NO(List IMP_CTRL_NO) {
		this.IMP_CTRL_NO = IMP_CTRL_NO;
	}

	public List getREF_NO() {
		return REF_NO;
	}

	public void setREF_NO(List REF_NO) {
		this.REF_NO = REF_NO;
	}

	public List getREF_TYPE() {
		return REF_TYPE;
	}

	public void setREF_TYPE(List REF_TYPE) {
		this.REF_TYPE = REF_TYPE;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String MSG) {
		this.MSG = MSG;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getCustomfwd_auth() {
		return customfwd_auth;
	}

	public void setCustomfwd_auth(String customfwd_auth) {
		this.customfwd_auth = customfwd_auth;
	}

	public String getCTNS() {
		return CTNS;
	}

	public void setCTNS(String CTNS) {
		this.CTNS = CTNS;
	}

	public String getGRWT() {
		return GRWT;
	}

	public void setGRWT(String GRWT) {
		this.GRWT = GRWT;
	}

	public List getModlList() {
		return modlList;
	}

	public void setModlList(List modlList) {
		this.modlList = modlList;
	}

	public BigDecimal getBd() {
		return bd;
	}

	public void setBd(BigDecimal bd) {
		this.bd = bd;
	}

	public BigDecimal getBd1() {
		return bd1;
	}

	public void setBd1(BigDecimal bd1) {
		this.bd1 = bd1;
	}

	public List getTedlList() {
		return tedlList;
	}

	public void setTedlList(List tedlList) {
		this.tedlList = tedlList;
	}

	public String getTOT_GR() {
		return TOT_GR;
	}

	public void setTOT_GR(String TOT_GR) {
		this.TOT_GR = TOT_GR;
	}

	public String getTOT_FOB() {
		return TOT_FOB;
	}

	public void setTOT_FOB(String TOT_FOB) {
		this.TOT_FOB = TOT_FOB;
	}

	public String getNET_FOB() {
		return NET_FOB;
	}

	public void setNET_FOB(String NET_FOB) {
		this.NET_FOB = NET_FOB;
	}

	public List getHSCODE1() {
		return HSCODE1;
	}

	public void setHSCODE1(List HSCODE1) {
		this.HSCODE1 = HSCODE1;
	}

	public String getEXCISE_UNIT() {
		return EXCISE_UNIT;
	}

	public void setEXCISE_UNIT(String EXCISE_UNIT) {
		this.EXCISE_UNIT = EXCISE_UNIT;
	}

	public List getEXCISE_UNIT_LIST() {
		return EXCISE_UNIT_LIST;
	}

	public void setEXCISE_UNIT_LIST(List EXCISE_UNIT_LIST) {
		this.EXCISE_UNIT_LIST = EXCISE_UNIT_LIST;
	}

	public List getCO_NO_EXCISE() {
		return CO_NO_EXCISE;
	}

	public void setCO_NO_EXCISE(List CO_NO_EXCISE) {
		this.CO_NO_EXCISE = CO_NO_EXCISE;
	}

	public List getCO_LINE_EXCISE() {
		return CO_LINE_EXCISE;
	}

	public void setCO_LINE_EXCISE(List CO_LINE_EXCISE) {
		this.CO_LINE_EXCISE = CO_LINE_EXCISE;
	}

	public List getMRP_RATE() {
		return MRP_RATE;
	}

	public void setMRP_RATE(List MRP_RATE) {
		this.MRP_RATE = MRP_RATE;
	}

	public String getEx_inv_slno() {
		return ex_inv_slno;
	}

	public void setEx_inv_slno(String ex_inv_slno) {
		this.ex_inv_slno = ex_inv_slno;
	}

	public String getEx_inv_date() {
		return ex_inv_date;
	}

	public void setEx_inv_date(String ex_inv_date) {
		this.ex_inv_date = ex_inv_date;
	}

	public String getEX_GEN_ALLOW() {
		return EX_GEN_ALLOW;
	}

	public void setEX_GEN_ALLOW(String EX_GEN_ALLOW) {
		this.EX_GEN_ALLOW = EX_GEN_ALLOW;
	}

	public String getExciseFlag() {
		return exciseFlag;
	}

	public void setExciseFlag(String exciseFlag) {
		this.exciseFlag = exciseFlag;
	}

	public List getROSL_SLNO() {
		return ROSL_SLNO;
	}

	public void setROSL_SLNO(List ROSL_SLNO) {
		this.ROSL_SLNO = ROSL_SLNO;
	}

	public List getSCHEME_CODE_LIST() {
		return SCHEME_CODE_LIST;
	}

	public void setSCHEME_CODE_LIST(List SCHEME_CODE_LIST) {
		this.SCHEME_CODE_LIST = SCHEME_CODE_LIST;
	}

	public List getSCHEME_CODE() {
		return SCHEME_CODE;
	}

	public void setSCHEME_CODE(List SCHEME_CODE) {
		this.SCHEME_CODE = SCHEME_CODE;
	}

	public String getEX_RATE_ALLOW() {
		return EX_RATE_ALLOW;
	}

	public void setEX_RATE_ALLOW(String EX_RATE_ALLOW) {
		this.EX_RATE_ALLOW = EX_RATE_ALLOW;
	}

	public String getMmitgr() {
		return mmitgr;
	}

	public void setMmitgr(String mmitgr) {
		this.mmitgr = mmitgr;
	}

	public String getNETWT() {
		return NETWT;
	}

	public void setNETWT(String NETWT) {
		this.NETWT = NETWT;
	}

	public List getHNGR_COST() {
		return HNGR_COST;
	}

	public void setHNGR_COST(List HNGR_COST) {
		this.HNGR_COST = HNGR_COST;
	}

	public List getTAG_COST() {
		return TAG_COST;
	}

	public void setTAG_COST(List TAG_COST) {
		this.TAG_COST = TAG_COST;
	}

	public String getInv_state() {
		return inv_state;
	}

	public void setInv_state(String inv_state) {
		this.inv_state = inv_state;
	}

	public String getDESTI_STATE() {
		return DESTI_STATE;
	}

	public void setDESTI_STATE(String DESTI_STATE) {
		this.DESTI_STATE = DESTI_STATE;
	}

	public List getHSN_CODE_E() {
		return HSN_CODE_E;
	}

	public void setHSN_CODE_E(List HSN_CODE_E) {
		this.HSN_CODE_E = HSN_CODE_E;
	}

	public List getIGST_PER_E() {
		return IGST_PER_E;
	}

	public void setIGST_PER_E(List IGST_PER_E) {
		this.IGST_PER_E = IGST_PER_E;
	}

	public List getCGST_PER_E() {
		return CGST_PER_E;
	}

	public void setCGST_PER_E(List CGST_PER_E) {
		this.CGST_PER_E = CGST_PER_E;
	}

	public List getSGST_PER_E() {
		return SGST_PER_E;
	}

	public void setSGST_PER_E(List SGST_PER_E) {
		this.SGST_PER_E = SGST_PER_E;
	}

	public String getGstin_state() {
		return gstin_state;
	}

	public void setGstin_state(String gstin_state) {
		this.gstin_state = gstin_state;
	}

	public String getINV_COMP() {
		return INV_COMP;
	}

	public void setINV_COMP(String INV_COMP) {
		this.INV_COMP = INV_COMP;
	}

	public String getINV_GEO() {
		return INV_GEO;
	}

	public void setINV_GEO(String INV_GEO) {
		this.INV_GEO = INV_GEO;
	}

	public int getSdate() {
		return sdate;
	}

	public void setSdate(int sdate) {
		this.sdate = sdate;
	}

	public String getINV_GSTIN() {
		return INV_GSTIN;
	}

	public void setINV_GSTIN(String INV_GSTIN) {
		this.INV_GSTIN = INV_GSTIN;
	}

	public int getDESTI_GEO() {
		return DESTI_GEO;
	}

	public void setDESTI_GEO(int DESTI_GEO) {
		this.DESTI_GEO = DESTI_GEO;
	}



	public String getLUT_IGST() {
		return LUT_IGST;
	}

	public void setLUT_IGST(String LUT_IGST) {
		this.LUT_IGST = LUT_IGST;
	}

	public AccessoriesSearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(AccessoriesSearchBean searchBean) {
		this.searchBean = searchBean;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		map=arg0;

	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getCiNo() {
		return ciNo;
	}

	public void setCiNo(String ciNo) {
		this.ciNo = ciNo;
	}

	public String getCiDate() {
		return ciDate;
	}

	public void setCiDate(String ciDate) {
		this.ciDate = ciDate;
	}

	public PreInvoiceHeader getHeader() {
		return header;
	}

	public void setHeader(PreInvoiceHeader header) {
		this.header = header;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getFCTOTAL() {
		return FCTOTAL;
	}

	public void setFCTOTAL(String fCTOTAL) {
		FCTOTAL = fCTOTAL;
	}

	public String getQTYTOTAL() {
		return QTYTOTAL;
	}

	public void setQTYTOTAL(String qTYTOTAL) {
		QTYTOTAL = qTYTOTAL;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Parameters getParameter() {
		return parameter;
	}

	public void setParameter(Parameters parameter) {
		this.parameter = parameter;
	}


}
