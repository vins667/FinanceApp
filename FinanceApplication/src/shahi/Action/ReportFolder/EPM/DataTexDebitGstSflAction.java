package shahi.Action.ReportFolder.EPM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.Common.Ftpfileupload;
import shahi.Action.MI.GLS850MI;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.ReportFolder.EPM.beans.DataTexSflPurchaseBean;
import shahi.Action.database.ConnectionDataTexSfl;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;

public class DataTexDebitGstSflAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aausrid;
	private String DATEFROM;
	private String DATETO;
	private String DIVI;
	private List chkdel;
	private List CHKTDS;
	private List<DataTexSflPurchaseBean> detaillist;
	public static ResourceBundle aResourcBundle = null;

	private String getValue(String key) {
		return aResourcBundle.getString(key);
	}

	private int getIntValue(String key) {
		return Integer.parseInt(getValue(key));
	}



	public DataTexDebitGstSflAction() {
		aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
	}

	public String makesession() throws SQLException, Exception {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        if (usrid != null) {
            session.remove("sessUserName");
            session.remove("sessUserId");
            session.remove("sessLocationCode");
            session.remove("sessUnitCode");
            session.remove("sessDeptCode");
            try {
                conn = new ConnectionShahiHrisNew().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
                return SUCCESS;
            }
            try {
                stat = conn.prepareStatement("select CARDNO emp_code,longdescription EMP_NAME,'100' LOCATION_CODE,FACTORYCODE UNIT_CODE,DEPARTMENTDEPARTMENTCODE DEPT_CODE from amsnow.emptrimmatview where  trim(code)=?  or trim(cardno)=? ");
                stat.setString(1, aausrid);
                stat.setString(2, aausrid);
                result = stat.executeQuery();
                if (result.next()) {
                    session.put("sessUserName", result.getString("EMP_NAME"));
                    session.put("sessUserId", result.getString("emp_code"));
                    session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                    session.put("sessUnitCode", result.getString("UNIT_CODE"));
                    session.put("sessDeptCode", result.getString("DEPT_CODE"));
                }
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }
                result = null;
                stat = null;
            } catch (Exception e) {
                System.out.println("CashBillEntryAction + mksess() " + e);
                return SUCCESS;
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
                result = null;
                stat = null;
                conn = null;
            }
        } else {
            try {
                conn = new ConnectionShahiHrisNew().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
                return SUCCESS;
            }
            try {
                stat = conn.prepareStatement("select CARDNO emp_code,longdescription EMP_NAME,'100' LOCATION_CODE,FACTORYCODE UNIT_CODE,DEPARTMENTDEPARTMENTCODE DEPT_CODE from amsnow.emptrimmatview where trim(code)=?  or trim(cardno)=?");
                stat.setString(1, aausrid);
                stat.setString(2, aausrid);
                result = stat.executeQuery();
                if (result.next()) {
                    session.put("sessUserName", result.getString("EMP_NAME"));
                    session.put("sessUserId", result.getString("emp_code"));
                    session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                    session.put("sessUnitCode", result.getString("UNIT_CODE"));
                    session.put("sessDeptCode", result.getString("DEPT_CODE"));
                }
                if (result != null) {
                    result.close();
                }
                if (stat != null) {
                    stat.close();
                }
                result = null;
                stat = null;
            } catch (Exception e) {
                System.out.println("CashBillEntryAction  + mksess() " + e);
                return SUCCESS;
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
                result = null;
                stat = null;
                conn = null;
            }
        }
        execute();
        return SUCCESS;
    }
	@Override
	public String execute() throws Exception {
		chkdel=null;
		detaillist = new ArrayList<DataTexSflPurchaseBean>();
		Connection conn = null;
		Connection conn1=null;
		PreparedStatement stat = null;
		ResultSet result = null;
		PreparedStatement stat1 = null;
		ResultSet result1 = null;
		PreparedStatement stat2 = null;
		ResultSet result2 = null;
		try {
			conn = new ConnectionDataTexSfl().getConnection();
			conn1 = new connectiondb2().getConnection();
		} catch (Exception e) {
			System.out.println(e.toString());
			addActionError(e.getMessage());
		}
		try {
			String qry="" ;
			//and INVOICENO='01-379/1617' ";
			if((DATEFROM!=null && DATEFROM.length()>0) && (DATETO!=null && DATETO.length()>0)){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				String datefrom=simpleDateFormat1.format(simpleDateFormat.parse(DATEFROM));
				String dateto=simpleDateFormat1.format(simpleDateFormat.parse(DATETO));
				qry+="  and finyear>2015 and accountingdate>'2017-06-30'   and accountingdate between '"+datefrom+"' and '"+dateto+"'";
			}  
			// System.out.println(qry);
			stat = conn.prepareStatement("select a.companycode,a.divisioncode,a.finyear,a.accountingdate,trim(a.INVOICENO) INVOICENO,a.invoicedate,trim(a.customersuppliercode) customersuppliercode,a.invamt,b.glamt,nvl"
					+" (a.invamt,0)+nvl(b.glamt,0) netamt,a.qty,a.duedate,a.currencycode,a.currencyconversionrate,a.taxform,a.narration,a.numcol3 from"
					+" (select companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode,currencycode,"
					+" currencyconversionrate,sum(QUANTITY) qty,decode(currencycode,'INR',sum(INVAMOUTINR),sum(CURRENCYAMOUNT)) invamt,max(duedate) duedate,max(taxform) taxform,max(narration) narration,max(numcol3) numcol3"
					+" from movexposting a where a.companycode=100 and a.divisioncode=? and (fromstatecode is not null or fromstatecode is not null) and a.eventcode=18 and flag=1   and nvl(numcol2,0)=0 and a.glcode is not null"
					+" group by companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode,currencycode,"
					+" currencyconversionrate) a left outer join "
					+" (select companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode,sum(glamount) glamt"
					+" from movexposting   where companycode=100 and divisioncode=? and (fromstatecode is not null or fromstatecode is not null) and eventcode=18 and flag=1  and nvl(numcol2,0)=0 and glcode is not null"
					+" group by companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode) b"
					+" on a.companycode=b.companycode and a.divisioncode=b.divisioncode and a.finyear=b.finyear and a.customersuppliercode=b.customersuppliercode and a.INVOICENO=b.INVOICENO where a.companycode='100' order by a.accountingdate");
			stat.setString(1, DIVI);
			stat.setString(2, DIVI);
			result = stat.executeQuery();
			while(result.next()){
				DataTexSflPurchaseBean dataTexSflPurchaseBean = new DataTexSflPurchaseBean();
				//dataTexSflPurchaseBean.setABSUNIQUEID(result.getString("ABSUNIQUEID"));
				dataTexSflPurchaseBean.setCOMPANYCODE(result.getString("COMPANYCODE"));
				dataTexSflPurchaseBean.setDIVISIONCODE(result.getString("DIVISIONCODE"));
				dataTexSflPurchaseBean.setFINYEAR(result.getString("FINYEAR"));
				dataTexSflPurchaseBean.setACCOUNTINGDATE(result.getString("ACCOUNTINGDATE"));
				dataTexSflPurchaseBean.setINVOICENO(result.getString("INVOICENO"));
				dataTexSflPurchaseBean.setINVOICEDATE(result.getString("INVOICEDATE"));
				dataTexSflPurchaseBean.setCUSTOMERSUPPLIERCODE(result.getString("CUSTOMERSUPPLIERCODE"));
				dataTexSflPurchaseBean.setTAXFORM(result.getString("TAXFORM"));
				dataTexSflPurchaseBean.setINVAMT(result.getString("INVAMT"));
				dataTexSflPurchaseBean.setGLAMT(result.getString("GLAMT"));
				dataTexSflPurchaseBean.setNETAMT(result.getString("NETAMT"));
				dataTexSflPurchaseBean.setQTY(result.getString("QTY"));
				dataTexSflPurchaseBean.setDUEDATE(result.getString("DUEDATE"));
				dataTexSflPurchaseBean.setCURRENCYCODE(result.getString("CURRENCYCODE"));
				dataTexSflPurchaseBean.setCURRENCYCONVERSIONRATE(result.getString("CURRENCYCONVERSIONRATE"));
				dataTexSflPurchaseBean.setNARRATION(result.getString("NARRATION"));
				if(result.getString("numcol3")!=null){
					dataTexSflPurchaseBean.setNontdsAmount(result.getString("numcol3"));
				}
				//System.out.println(result.getString("FINYEAR"));
				stat1=conn.prepareStatement("select * from  movexposting where companycode=100 and divisioncode=? and finyear=? and trim(CUSTOMERSUPPLIERCODE)=? and  trim(INVOICENO)=? and flag=1");
				stat1.setString(1, DIVI);
				stat1.setString(2, result.getString("FINYEAR"));
				stat1.setString(3, result.getString("CUSTOMERSUPPLIERCODE"));
				stat1.setString(4, result.getString("INVOICENO").trim());
				result1 = stat1.executeQuery();
				if(result1.next()){
					dataTexSflPurchaseBean.setDCHK("OK");
				}
				if(stat1!=null){
					stat1.close();
				}
				if(result1!=null){
					result1.close();
				}

				stat2 = conn1.prepareStatement("select * from m3fdbprd.cidven where iicono=111 and iisers<>0 and iisuno=?");
				stat2.setString(1, result.getString("CUSTOMERSUPPLIERCODE"));
				result2 = stat2.executeQuery();
				if(result2.next()){
					dataTexSflPurchaseBean.setTCHK("OK");
				}
				if(stat2!=null){
					stat2.close();
				}
				if(result2!=null){
					result2.close();
				}

				stat2 = conn1.prepareStatement("select * from m3fdbprd.cidmas where idcono=111 and idstat='20' and idsuno=?");
				stat2.setString(1, result.getString("CUSTOMERSUPPLIERCODE"));
				result2 = stat2.executeQuery();
				if(result2.next()){
					dataTexSflPurchaseBean.setSCHK("OK");
				}
				if(stat2!=null){
					stat2.close();
				}
				if(result2!=null){
					result2.close();
				}

				detaillist.add(dataTexSflPurchaseBean);

			}
		} catch (Exception e) {
			System.out.println("1.DataTexPurchaseGstSflAction-execute() " + e);
			addActionError(e.getMessage());
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (conn1 != null) {
				conn1.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (result != null) {
				result.close();
			}
		}

		return SUCCESS;
	}
	public String save() throws Exception{
		Map session = ActionContext.getContext().getSession();
		String USER_ID = (String) session.get("sessUserId");
		System.out.println("List Size"+getDetaillist().size());
		if (USER_ID == null) {
			addActionError("Session Not Valid");
			return ERROR;
		}
		if(chkdel!=null && chkdel.size()>0){
			Connection conn = null;
			Connection conn1 = null;
			Connection conn2 = null;
			PreparedStatement stat = null;
			ResultSet resultSet = null;

			PreparedStatement statupd = null;

			PreparedStatement stat1 = null;
			ResultSet resultSet1 = null;

			PreparedStatement stat2 = null;
			ResultSet resultSet2 = null;

			PreparedStatement stat3 = null;
			ResultSet resultSet3 = null;

			PreparedStatement stat4 = null;
			ResultSet resultSet4 = null;

			PreparedStatement stat6 = null;
			ResultSet resultSet6 = null;
			try {
				conn = new connection().getConnection();
				conn.setAutoCommit(false);
				conn1 = new ConnectionDataTexSfl().getConnection();
				conn2 = new connectiondb2().getConnection();
			} catch (Exception e) {
				System.out.println(e.toString());
				addActionError(e.getMessage());
			}
			try{

				String path1 = getValue("reportPdfPath") + "PayChq"; 
				//   String path1 = "d:/epm/PayChq";
				String real_filename = path1 + "/"+"dninv"+DIVI+".txt";
				File aa = new File(path1 + "/"+"dninv"+DIVI+".txt");
				if (aa.exists()) {
					aa.delete();
				}

				String textfile = "";
				String filetext = ""; 
				String LOCT="";
				if(DIVI!=null && DIVI.equals("101")){
					LOCT="800";
				}
				else{
					LOCT="900";
				}
				FileOutputStream fos = new FileOutputStream(real_filename);
				PrintWriter pw = new PrintWriter(fos);
				int ctr=1;
				stat=conn.prepareStatement("SELECT datatexpv_SQ.NEXTVAL from DUAL");
				String SQLUNIQUE=null;
				resultSet=stat.executeQuery();
				if(resultSet.next()){
					SQLUNIQUE=resultSet.getString(1);
				}
				for(int i=0;i<chkdel.size();i++){					
					String SUPCODE=null;
					String SUPINV=null;
					String FINYEAR=null;
					if(chkdel.get(i).toString()!=null && chkdel.get(i).toString().length()>0){
						String arr[] =chkdel.get(i).toString().split("\\$\\$");
						if(arr.length==3){
							SUPCODE=arr[0];
							SUPINV=arr[1];
							FINYEAR=arr[2];
						}
						//System.out.println(SUPCODE+" "+SUPINV+" "+FINYEAR);
					}
					System.out.println("Non Tds Amount"+getDetaillist().get(i).getNontdsAmount());
					if(SUPCODE!=null && SUPINV!=null && FINYEAR!=null){
						stat = conn1.prepareStatement("SELECT * FROM MOVEXPOSTING WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? AND FINYEAR=? AND CUSTOMERSUPPLIERCODE=?  AND INVOICENO=?  AND eventcode=18 and Flag=1 ");
						stat.setString(1, DIVI);
						stat.setString(2, FINYEAR);
						stat.setString(3, SUPCODE);
						stat.setString(4, SUPINV);
						ResultSet resultSet5=stat.executeQuery();
						while(resultSet5.next()){


							stat = conn.prepareStatement("INSERT INTO SHAHIWEB.MOVEXPOSTGST(COMPANYCODE,ABSUNIQUEID ,ROWINDEXNUM,ROWINDEXSUBNUM ,DIVISIONCODE,PLANTCODE,FINYEAR,ACCOUNTINGDATE,INVOICENO,INVOICEDATE,EXPENSECODE,COSTCENTERCODE, "+                
									" PROFITCENTER,PRODUCTGROUPCODE,CUSTOMERSUPPLIERCODE ,CONO,COTYPE,NARRATION ,EVENT ,QUANTITY,CURRENCYCODE,CURRENCYCONVERSIONRATE  ,CURRENCYAMOUNT,INVAMOUTINR  ,PAYMENTTYPE  ,PAYMENTMETHOD ,GLCODE,  "+                       
									" GLAMOUNT,TAXFORM ,MINGRNDATE,MAXGRNDATE,USERID ,FLAG ,TRANSFERDATE,DUEDATE ,EVENTCODE,NUMCOL1 ,NUMCOL2 ,DATECOL1 ,DATECOL2  ,INVOICENO  ,CHARCOL2,   "+                     
									" REVONO,NUMCOL3,PLANTINVOICECODE,FROMSTATECODE,TOSTATECODE,HSNCODE,TAXTEMPLATE )  values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

							stat.setString( 1	,	resultSet5.getString("COMPANYCODE"));
							stat.setString(	2	,	resultSet5.getString("ABSUNIQUEID"));
							stat.setString(	3	,	resultSet5.getString("ROWINDEXNUM"));
							stat.setString(	4	,	resultSet5.getString("ROWINDEXSUBNUM"));
							stat.setString(	5	,	resultSet5.getString("DIVISIONCODE"));
							stat.setString(	6	,	resultSet5.getString("PLANTCODE"));
							stat.setString(	7	,	resultSet5.getString("FINYEAR"));
							stat.setString(	8	,	resultSet5.getString("ACCOUNTINGDATE"));
							stat.setString(	9	,	resultSet5.getString("INVOICENO"));
							stat.setString(	10	,	resultSet5.getString("INVOICEDATE"));
							stat.setString(	11	,	resultSet5.getString("EXPENSECODE"));
							stat.setString(	12	,	resultSet5.getString("COSTCENTERCODE"));
							stat.setString(	13	,	resultSet5.getString("PROFITCENTER"));
							stat.setString(	14	,	resultSet5.getString("PRODUCTGROUPCODE"));
							stat.setString(	15	,	resultSet5.getString("CUSTOMERSUPPLIERCODE"));
							stat.setString(	16	,	resultSet5.getString("CONO"));
							stat.setString(	17	,	resultSet5.getString("COTYPE"));
							stat.setString(	18	,	resultSet5.getString("NARRATION"));
							stat.setString(	19	,	resultSet5.getString("EVENT"));
							stat.setString(	20	,	resultSet5.getString("QUANTITY"));
							stat.setString(	21	,	resultSet5.getString("CURRENCYCODE"));
							stat.setString(	22	,	resultSet5.getString("CURRENCYCONVERSIONRATE"));
							stat.setString(	23	,	resultSet5.getString("CURRENCYAMOUNT"));
							stat.setString(	24	,	resultSet5.getString("INVAMOUTINR"));
							stat.setString(	25	,	resultSet5.getString("PAYMENTTYPE"));
							stat.setString(	26	,	resultSet5.getString("PAYMENTMETHOD"));
							stat.setString(	27	,	resultSet5.getString("GLCODE"));
							stat.setString(	28	,	resultSet5.getString("GLAMOUNT"));
							stat.setString(	29	,	resultSet5.getString("TAXFORM"));
							stat.setString(	30	,	resultSet5.getString("MINGRNDATE"));
							stat.setString(	31	,	resultSet5.getString("MAXGRNDATE"));
							stat.setString(	32	,	resultSet5.getString("USERID"));
							stat.setString(	33	,	resultSet5.getString("FLAG"));
							stat.setString(	34	,	resultSet5.getString("TRANSFERDATE").substring(0, 10));
							stat.setString(	35	,	resultSet5.getString("DUEDATE"));
							stat.setString(	36	,	resultSet5.getString("EVENTCODE"));
							stat.setString(	37	,	resultSet5.getString("NUMCOL1"));
							stat.setString(	38	,	resultSet5.getString("NUMCOL2"));
							stat.setString(	39	,	resultSet5.getString("DATECOL1"));
							stat.setString(	40	,	resultSet5.getString("DATECOL2"));
							stat.setString(	41	,	resultSet5.getString("INVOICENO"));
							stat.setString(	42	,	resultSet5.getString("CHARCOL2"));
							stat.setString(	43	,	resultSet5.getString("REVONO"));
							//stat.setString(	44	,	resultSet5.getString("NUMCOL3"));

							stat.setString(	44	,	getDetaillist().get(i).getNontdsAmount());
							stat.setString(	45	,	resultSet5.getString("PLANTINVOICECODE"));
							stat.setString(	46	,	resultSet5.getString("FROMSTATECODE"));
							stat.setString(	47	,	resultSet5.getString("TOSTATECODE"));
							stat.setString(	48	,	resultSet5.getString("HSNCODE"));
							stat.setString(	49	,	resultSet5.getString("TAXTEMPLATE"));

							stat.executeUpdate();	

							/*  stat = conn.prepareStatement("INSERT INTO SHAHIWEB.MOVEXPOST SELECT * FROM MOVEXPOSTING@NOWPROD WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? AND FINYEAR=? AND CUSTOMERSUPPLIERCODE=?  AND INVOICENO=?  AND eventcode=18 and Flag=1");
						stat.setString(1, DIVI);
						stat.setString(2, FINYEAR);
						stat.setString(3, SUPCODE);
						stat.setString(4, SUPINV);
						stat.executeUpdate();*/
							if(stat!=null){
								stat.close();
							}
						}
						conn.commit();

						stat1=conn.prepareStatement("select a.companycode,a.divisioncode,a.finyear,TO_CHAR(a.accountingdate,'yyyyMMdd') accountingdate,trim(a.INVOICENO) INVOICENO,TO_CHAR(a.invoicedate,'yyyyMMdd') invoicedate,trim(a.customersuppliercode) customersuppliercode,a.invamt,b.glamt,nvl"
								+" (a.invamt,0)+nvl(b.glamt,0) netamt,a.qty,TO_CHAR(a.duedate,'yyyyMMdd') duedate,a.currencycode,a.currencyconversionrate,trim(a.taxform) taxform,a.narration ,a.numcol3 from"
								+" (select companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode,currencycode,"
								+" currencyconversionrate,sum(QUANTITY) qty,decode(currencycode,'INR',sum(INVAMOUTINR),sum(CURRENCYAMOUNT)) invamt,max(duedate) duedate,max(taxform) taxform,max(narration) narration,max(numcol3) numcol3"
								+" from movexpostgst a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.EXPENSECODE)<>'9995' and trim(a.customersuppliercode)=? and trim(a.INVOICENO)=?  and a.eventcode=18 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0 and a.glcode is null"
								+" group by companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode,currencycode,"
								+" currencyconversionrate) a,"
								+" (select companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode,sum(glamount) glamt"
								+" from movexpostgst   where companycode=100 and divisioncode=? and finyear=?  and trim(customersuppliercode)=? and trim(INVOICENO)=? and eventcode=18 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0 and glcode is not null"
								+" group by companycode,divisioncode,finyear,accountingdate,INVOICENO,invoicedate,customersuppliercode) b"
								+" where a.companycode=b.companycode(+) and a.divisioncode=b.divisioncode(+) and a.finyear=b.finyear(+) and a.customersuppliercode=b.customersuppliercode(+) and a.INVOICENO=b.INVOICENO(+) order by a.accountingdate");
						stat1.setString(1, DIVI);
						stat1.setString(2, FINYEAR);
						stat1.setString(3, SUPCODE);
						stat1.setString(4, SUPINV);
						stat1.setString(5, DIVI);
						stat1.setString(6, FINYEAR);
						stat1.setString(7, SUPCODE);
						stat1.setString(8, SUPINV);							
						resultSet=stat1.executeQuery();
						while(resultSet.next()){								
							filetext="I1"+LeftPad(SQLUNIQUE,9)+RightPad(String.valueOf(ctr),8)+"550"+RightPad(resultSet.getString("customersuppliercode"),10)+RightPad(resultSet.getString("customersuppliercode"),10);

							filetext=filetext+RightPad(resultSet.getString("INVOICENO"),24)+resultSet.getString("invoicedate")+resultSet.getString("duedate")+LeftPad("-"+resultSet.getString("netamt"),17);
							filetext=filetext+RightPad(resultSet.getString("currencycode"),3)+LeftPad("1",3)+LeftPad(resultSet.getString("currencyconversionrate"),13)+resultSet.getString("accountingdate")+RightPad("AUDITGZB",10);

							double amttds=0;
							String VTDX="";
							String TDSGL="";
							/* stat1 = conn2.prepareStatement("select xasuno,t2xtdc,t1ait1,t1ait2,t2xtdr from mincdtprod.xidven,mincdtprod.xtdsrt,mincdtprod.xtdscd"
	        							+" where xacono=111 and xadivi='550'  and xaxtap=1 and xasuno=?"
	        							+" and xacono=t2cono and xadivi=t2divi and xaxtdc=t2xtdc and t2vfdt=20140401"
	        							+" and t2cono=t1cono and t2divi=t1divi and t2xtdc=t1xtdc and t2xtct=t1xtct and t1xtct=1");
							 */
							stat1 = conn2.prepareStatement("select iisers,hedivi,hefrdt,hetodt,hewhpo,cxait1 from m3fdbprd.cidven,cinfdbprd.zapwhp,CAEXEL"
									+" where iicono=hecono and hedivi='550'"
									+" and iisers=hesers and hecono=cxcono and hedivi=cxdivi and hesers=cxobv1"
									+" and cxeven='AP10' and cxacty='396' and cxobv2='CCC' and iisuno=?");
							stat1.setString(1, SUPCODE);
							resultSet1=stat1.executeQuery();
							if(resultSet1.next() && (CHKTDS.get(i)!=null && CHKTDS.get(i).equals("Y"))){	                            	
								if (resultSet1.getString("iisers")!=null && !resultSet1.getString("iisers").equals("17"))
								{
									VTDX=resultSet1.getString("iisers");
									TDSGL=resultSet1.getString("cxait1");
									amttds=Math.round(((resultSet.getDouble("netamt")-resultSet.getDouble("numcol3"))*resultSet1.getDouble("hewhpo"))/100);
									// filetext=filetext+ LeftPad("I",10)+RightPad(resultSet1.getString("iisers"),10);
									filetext=filetext+ LeftPad("I",20);
								}		                            
							}
							else
							{
								filetext=filetext+LeftPad(" ",20);
							}
							filetext=filetext+LeftPad(" ",32)+RightPad(resultSet.getString("narration"),40)+LeftPad("0.00",19)+LeftPad(" ",8)+LeftPad(" ",15)+RightPad("CHZ",3)+LeftPad(" ",8);
							pw.println(filetext);
							if(amttds>0){
								filetext="I3"+LeftPad(SQLUNIQUE,9)+RightPad(String.valueOf(ctr),8)+"550"+RightPad(resultSet.getString("customersuppliercode"),10)+RightPad(resultSet.getString("customersuppliercode"),10);
								filetext=filetext+RightPad(resultSet.getString("INVOICENO"),24)+resultSet.getString("invoicedate")+resultSet.getString("duedate")+LeftPad(String.valueOf(amttds),17);
								filetext=filetext+RightPad(resultSet.getString("currencycode"),3)+LeftPad("1",3)+LeftPad(resultSet.getString("currencyconversionrate"),13)+resultSet.getString("accountingdate")+RightPad("AUDITGZB",10);
								filetext=filetext+LeftPad("T",10)+LeftPad(VTDX,10);
								filetext=filetext+LeftPad(" ",32)+RightPad(resultSet.getString("narration"),40)+LeftPad("0.00",19)+LeftPad(" ",8)+LeftPad(" ",15)+RightPad("CHZ",3)+LeftPad(" ",8);
								filetext=filetext+LeftPad(resultSet.getString("numcol3"),17);
								//              System.out.println(filetext);
								pw.println(filetext);

								filetext="I2"+LeftPad(SQLUNIQUE,9)+RightPad(String.valueOf(ctr),8)+"550"+RightPad(resultSet.getString("customersuppliercode"),10)+RightPad(resultSet.getString("customersuppliercode"),10);
								filetext=filetext+RightPad(resultSet.getString("INVOICENO"),24)+resultSet.getString("invoicedate")+resultSet.getString("duedate")+LeftPad("-"+String.valueOf(amttds),17);
								filetext=filetext+RightPad(resultSet.getString("currencycode"),3)+LeftPad("1",3)+LeftPad(resultSet.getString("currencyconversionrate"),13)+resultSet.getString("accountingdate")+RightPad("AUDITGZB",10);
								filetext=filetext+ RightPad(" ",20)+RightPad(TDSGL,8)+RightPad("COR",8)+RightPad(LOCT,8)+RightPad(resultSet.getString("customersuppliercode"),8);
								filetext=filetext+RightPad(resultSet.getString("narration"),40)+LeftPad("0.00",19)+LeftPad(" ",8)+LeftPad(" ",15)+RightPad("CHZ",3)+LeftPad(" ",8);
								pw.println(filetext);
							}

							String newtaxform ="";
							String taxtp =" ";
							stat2=conn.prepareStatement("select 'I2'||lpad(max("+SQLUNIQUE+"),9)||rpad('"+ctr+"',8)||'550'||rpad(customersuppliercode,10)||rpad(customersuppliercode,10)||rpad(INVOICENO,24)||"
									+" to_char(max(invoicedate),'YYYYMMDD')||to_char(max(duedate),'YYYYMMDD')||LPAD(decode(currencycode,'INR',sum(INVAMOUTINR),sum(CURRENCYAMOUNT)),17)||rpad(currencycode,3)||lpad('1',3)||"
									+" lpad(currencyconversionrate,13)||to_char(max(accountingdate),'YYYYMMDD')||rpad('AUDITGZB',10) KK,rpad(EXPENSECODE,8)||rpad(decode(plantcode,'P21','SWD','P22','SPD','999'),8)||"  //rpad(PROFITCENTER,8)
									+" rpad(COSTCENTERCODE,8)||rpad(customersuppliercode,8)||rpad(max(narration),40)||rpad(' ',19)||rpad(PRODUCTGROUPCODE,8)||lpad(sum(QUANTITY),15)||'CHZ'||RPAD(substr(CONO,4,8),8) DD"
									+" from movexpostgst a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.EXPENSECODE)<>'9995'and trim(a.customersuppliercode)=? and trim(a.INVOICENO)=? and a.eventcode=18 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0"
									+" and a.glcode is null"
									+" group by  accountingdate,customersuppliercode,INVOICENO,currencycode,currencyconversionrate,EXPENSECODE,decode(plantcode,'P21','SWD','P22','SPD','999'),COSTCENTERCODE,PRODUCTGROUPCODE,CONO"); //PROFITCENTER
							stat2.setString(1, DIVI);
							stat2.setString(2, FINYEAR);
							stat2.setString(3, SUPCODE);
							stat2.setString(4, SUPINV);
							resultSet2=stat2.executeQuery();
							while(resultSet2.next()){
								filetext=resultSet2.getString("KK");

								String vatcnt ="";
								stat4=conn.prepareStatement("select count(*) kcnt from movexpostgst a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.EXPENSECODE)<>'9995'and trim(a.customersuppliercode)=? and trim(a.INVOICENO)=? and a.eventcode=18 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0"
										+" and a.glcode>16689 AND a.glcode<16693");
								//"=16603"); -- KULDEEP
								stat4.setString(1, DIVI);
								stat4.setString(2, FINYEAR);
								stat4.setString(3, SUPCODE);
								stat4.setString(4, SUPINV);
								resultSet4=stat4.executeQuery();
								if(resultSet4.next()){
									vatcnt=resultSet4.getString("kcnt");
								}
								PreparedStatement stat7 = null;
								ResultSet resultSet7 = null;

								stat7=conn.prepareStatement("select distinct ccnew from shahiweb.shmgccmap where dimtp=9 and ccold=?");
								stat7.setString(1, resultSet.getString("taxform".trim()));
								resultSet7=stat7.executeQuery();
								if(resultSet7.next()){
									newtaxform=resultSet7.getString("ccnew");
								}else
								{
									newtaxform="100200020";
								}

								stat6 = conn2.prepareStatement("select case z4natu when 1 then 'VAT' when 2 then 'CST' end tax  from cinfdbprd.ZGEOJU where z4cono=111 and z4geoc=?");
								stat6.setString(1,newtaxform);
								resultSet6 = stat6.executeQuery();
								if(resultSet6.next()){
									taxtp=resultSet6.getString("tax");
								}    

								//if(resultSet.getString("taxform")!=null && resultSet.getString("taxform").equals("100200020") && vatcnt.equals("0")){
								if(newtaxform!=null && newtaxform.equals("100200020") && vatcnt.equals("0")){
									filetext=filetext+LeftPad(" ", 20);
								}
								else{
									//filetext=filetext+ LeftPad("E",10)+RightPad(resultSet.getString("taxform"),10);
									filetext=filetext+LeftPad("E",1)+RightPad(newtaxform,9)+LeftPad(taxtp,3);
									filetext=filetext+LeftPad(" ", 7);
								}
								filetext=filetext+resultSet2.getString("DD");
								pw.println(filetext);
							}
							PreparedStatement statgeo = null;
							ResultSet resultSetgeo = null;
							//***** rpad(decode(glcode,16603,'S',' '),1) - 1826 CHANGE TO 16603 ON DATED 20170614 rpad(decode(glcode,16603,'S',' '),1)
							stat3=conn.prepareStatement("select 'I2'||lpad(max("+SQLUNIQUE+"),9)||rpad('"+ctr+"',8)||'550'||rpad(customersuppliercode,10)||rpad(customersuppliercode,10)||rpad(INVOICENO,24)||"
									+" to_char(max(invoicedate),'YYYYMMDD')||to_char(max(duedate),'YYYYMMDD')||LPAD(sum(glamount),17)||rpad(currencycode,3)||lpad('1',3)||"
									+" lpad(currencyconversionrate,13)||to_char(max(accountingdate),'YYYYMMDD')||rpad('AUDITGZB',10)||rpad('S',1)||rpad('"+newtaxform+"',9)||lpad('"+taxtp+"',3)||rpad(' ',7)||rpad(glcode,8)||rpad(decode(plantcode,'P21','SWD','P22','SPD','999'),8)||"
									+" rpad(COSTCENTERCODE,8)||rpad(customersuppliercode,8)||rpad(max(narration),40)||rpad(' ',19)||rpad(PRODUCTGROUPCODE,8)||lpad(sum(QUANTITY),15)||'CHZ'||RPAD(substr(CONO,4,8),8)||rpad(HSNCODE,16) KK,FROMSTATECODE,TAXTEMPLATE"
									+" from movexpostgst a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.customersuppliercode)=? and trim(a.INVOICENO)=? and a.eventcode=18 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0"
									+" and a.glcode is not null"
									+" group by  accountingdate,customersuppliercode,INVOICENO,currencycode,currencyconversionrate,glcode,decode(plantcode,'P21','SWD','P22','SPD','999'),COSTCENTERCODE,PRODUCTGROUPCODE,CONO,HSNCODE,FROMSTATECODE,TAXTEMPLATE order by HSNCODE"); //PROFITCENTER
							stat3.setString(1, DIVI);
							stat3.setString(2, FINYEAR);
							stat3.setString(3, SUPCODE);
							stat3.setString(4, SUPINV);
							resultSet3=stat3.executeQuery();
							while(resultSet3.next()){
								String taxc="";
								String geoc="";
								filetext=resultSet3.getString("KK");
								// pw.println(filetext);
								statgeo=conn2.prepareStatement("select distinct T0GEOC from m3fdbprd.CGEOJU where t0cono=111 and t0taj3<>'' and t0taj1=?");
								statgeo.setString(1, resultSet3.getString("FROMSTATECODE").trim());
								resultSetgeo=statgeo.executeQuery();
								if(resultSetgeo.next()){
									geoc=resultSetgeo.getString("T0GEOC").trim();
								}
								//    filetext=filetext+geoc;
								//    pw.println(filetext);
								if(statgeo!=null){
									statgeo.close();
									resultSetgeo.close();
								}

								statgeo=conn.prepareStatement("select distinct GSTCD from  shahiweb.GSTCDMAP where division=? and gstcds=?");
								statgeo.setString(1, DIVI);
								statgeo.setString(2, resultSet3.getString("TAXTEMPLATE").trim());
								resultSetgeo=statgeo.executeQuery();
								if(resultSetgeo.next()){
									taxc=resultSetgeo.getString("GSTCD");
								}
								filetext=filetext+RightPad(geoc,10)+taxc+"               ";
								statgeo.close();
								resultSetgeo.close();
								pw.println(filetext);

							}

						}
					}
					++ctr;
				}
				pw.close();
				fos.close();

				Ftpfileupload fileobj = new Ftpfileupload();
				String connst = getValue("MIIP");
				String cuser = getValue("UserNameDB2");
				String cpass = getValue("PasswordDB2");
				String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/M3BE1512/env/M3BE_15.1_PRD/transfer/GLS850MI/"+"dninv"+DIVI+".txt");
				if (ftpout.equals("OK")) {
					GLS850MI MI = new GLS850MI();
					MI.connect();
					String status = MI.DataUpload("111", "550", "AP-BILL-101", "dninv"+DIVI+".txt", "GLS850MI", "0");
					MI.destroyMI();
					MI = null;

					for(int i=0;i<chkdel.size();i++){  
						String SUPCODE=null;
						String SUPINV=null;
						String FINYEAR=null;
						if(chkdel.get(i).toString()!=null && chkdel.get(i).toString().length()>0){
							String arr[] =chkdel.get(i).toString().split("\\$\\$");
							if(arr.length==3){
								SUPCODE=arr[0];
								SUPINV=arr[1];
								FINYEAR=arr[2];
							}
							//System.out.println(SUPCODE+" "+SUPINV+" "+FINYEAR);
						}
						if(SUPCODE!=null && SUPINV!=null && FINYEAR!=null){
							statupd=conn1.prepareStatement("UPDATE MOVEXPOSTING SET FLAG=2 WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? and finyear=? and trim(customersuppliercode)=? and trim(INVOICENO)=? AND eventcode=18 and flag=1");
							statupd.setString(1, DIVI);
							statupd.setString(2, FINYEAR);
							statupd.setString(3, SUPCODE);
							statupd.setString(4, SUPINV);
							statupd.executeUpdate();	
							if(statupd!=null){
								statupd.close();
							}

							statupd=conn.prepareStatement("UPDATE SHAHIWEB.MOVEXPOSTGST SET FLAG=2 WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? and finyear=? and trim(customersuppliercode)=? and trim(INVOICENO)=? AND eventcode=18 and flag=1");
							statupd.setString(1, DIVI);
							statupd.setString(2, FINYEAR);
							statupd.setString(3, SUPCODE);
							statupd.setString(4, SUPINV);
							statupd.executeUpdate();	
							if(statupd!=null){
								statupd.close();
							}
						}
					}
				}
				conn.commit();
				MailProvider mailProvider = new MailProvider();
				String[] filenames = new String[1];
				filenames[0] = path1 + "/dninv"+DIVI+".txt";
				// mail status

				String ccAddress = null;
				String sehuser = null;

				String subjecttitle = "";
				String messageBodyText = " ";
				String frommail = "";
				String fromname = "";
				String[] tomail = new String[1];
				String toname = "";

				ccAddress = "kuldeep.anandsingh@shahi.co.in";

				//  tomail[1] = "rameshk.chauhan@shahi.co.in";
				PreparedStatement mailtoStat = conn.prepareStatement("select initcap(USER_NAME) USER_NAME,E_MAIL from seh_web_users where user_id=?");
				mailtoStat.setString(1, USER_ID.trim());
				ResultSet mailtoResult = mailtoStat.executeQuery();
				if (mailtoResult.next()) {
					tomail[0] = mailtoResult.getString("E_MAIL");
					sehuser = mailtoResult.getString("USER_NAME");
				}

				frommail = "movex@shahi.co.in";
				fromname = "SHAHI IT";

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				subjecttitle = "Purchase Voucher Posted From DataTex (SFL) on dated: " +format.format(new java.util.Date());
				messageBodyText = "<html>";
				messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
				messageBodyText += "<body bgcolor=#95b174>";
				messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
				messageBodyText += "This is to inform you that attached Purchase Voucher file uploaded in Movex. ";
				messageBodyText += "</br></br>";
				messageBodyText += "</font>";

				messageBodyText += "<table cellpadding='4' width='600'>";
				messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
				messageBodyText += "<tr style='font-size:14px;'><td>"+fromname+"</td></tr>";
				messageBodyText += "</table>";
				messageBodyText += "</body>";
				messageBodyText += "</html>";
				mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, filenames);

			} catch (Exception e) {
				conn.rollback();
				System.out.println("1.DataTexPurchaseGstSflAction-save() " + e);
				addActionError(e.getMessage());
			} finally {
				if (conn != null) {
					conn.close();
				}
				if (conn1 != null) {
					conn1.close();
				}
				if (conn2 != null) {
					conn2.close();
				}
				if (stat != null) {
					stat.close();
				}
			}
		}
		addActionError("Record Save Successfully");
		return SUCCESS;
	}
	private String LeftPad (String key, int n)
	{
		String rs=key;
		int slen = rs.length();
		for (int i=slen; i<n;i++)
		{
			rs =' '+rs;
		}
		return(rs);
	}
	private String RightPad (String key, int n)
	{
		String rs=key;
		int slen = rs.length();
		if(slen>n)
		{
			rs = rs.substring(0,n);
		}
		else
		{
			for (int i=slen; i<n;i++)
			{
				rs =rs+' ';
			}
		}
		return(rs);
	}  
	public String getAausrid() {
		return aausrid;
	}
	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}
	public String getDATEFROM() {
		return DATEFROM;
	}
	public void setDATEFROM(String dATEFROM) {
		DATEFROM = dATEFROM;
	}
	public String getDATETO() {
		return DATETO;
	}
	public void setDATETO(String dATETO) {
		DATETO = dATETO;
	}
	public String getDIVI() {
		return DIVI;
	}
	public void setDIVI(String dIVI) {
		DIVI = dIVI;
	}
	public List<DataTexSflPurchaseBean> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<DataTexSflPurchaseBean> detaillist) {
		this.detaillist = detaillist;
	}
	public List getChkdel() {
		return chkdel;
	}
	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}
	public List getCHKTDS() {
		return CHKTDS;
	}
	public void setCHKTDS(List CHKTDS) {
		this.CHKTDS = CHKTDS;
	}

}
