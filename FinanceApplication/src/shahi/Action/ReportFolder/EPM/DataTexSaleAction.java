package shahi.Action.ReportFolder.EPM;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import shahi.Action.MI.GLS850MI;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.ReportFolder.EPM.beans.DataTexSaleBean;
import shahi.Action.database.ConnectionDataTex;


import shahi.Action.database.connection;
import shahi.Action.Common.Ftpfileupload;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataTexSaleAction extends ActionSupport {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aausrid;
	private String DATEFROM;
	private String DATETO;
	private String DIVI;
	private List chkdel;
	
	public static ResourceBundle aResourcBundle = null;

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
    
    
	
	public DataTexSaleAction() {
		aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
	}



	private List<DataTexSaleBean> detaillist;
	public String makesession() throws Exception {		
        Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        if (USER_ID != null) {
        	try{
        	session.remove("sessUserName");
        	session.remove("sessUserId");
        	session.remove("sessLocationCode");
        	session.remove("sessUnitCode");
        	session.remove("sessDeptCode");
        	}
        	catch(Exception e){}
        }
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
            stat = conn.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE,DEPT_CODE from EMPLOYEE_VIEW where emp_code=?");
            stat.setString(1, aausrid);
            result = stat.executeQuery();
            if (result.next()) {
                session.put("sessUserName", result.getString("EMP_NAME"));
                session.put("sessUserId", result.getString("emp_code"));
                session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                session.put("sessUnitCode", result.getString("UNIT_CODE"));
                session.put("sessDeptCode", result.getString("DEPT_CODE"));
            }
        } catch (Exception e) {
            System.out.println("1.PayrollRePaymentAction " + e);
            addActionError(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
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
	
	@Override
	public String execute() throws Exception {
		detaillist = new ArrayList<DataTexSaleBean>();
		Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new ConnectionDataTex().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
        	String qry=" ";
        	if((DATEFROM!=null && DATEFROM.length()>0) && (DATETO!=null && DATETO.length()>0)){
        		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        		String datefrom=simpleDateFormat1.format(simpleDateFormat.parse(DATEFROM));
        		String dateto=simpleDateFormat1.format(simpleDateFormat.parse(DATETO));
        		qry+=" and accountingdate between '"+datefrom+"' and '"+dateto+"'";
        	}
        	stat = conn.prepareStatement("select a.ABSUNIQUEID,a.companycode,a.divisioncode,a.finyear,VARCHAR_FORMAT(a.accountingdate,'DD-MM-YYYY') accountingdate,a.invoiceno,VARCHAR_FORMAT(a.invoicedate,'DD-MM-YYYY') invoicedate,a.customersuppliercode,a.taxform,a.invamt,b.glamt,nvl(a.invamt,0)+nvl(b.glamt,0) netamt,a.qty,VARCHAR_FORMAT(a.duedate,'DD-MM-YYYY') duedate,a.currencycode,a.currencyconversionrate,a.narration  from"
											+" (select ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,taxform,duedate,currencycode,"
											+" currencyconversionrate,narration ,sum(QUANTITY) qty,sum(INVAMOUTINR) invamt"
											+" from movexposting a where  trim(a.companycode)='100' and trim(a.divisioncode)=? "+qry+" and a.eventcode=5 and a.glcode is null and flag=1"
											+" group by ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,taxform,duedate,currencycode,"
											+" currencyconversionrate,narration ) a left outer join"
											+" (select ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,sum(glamount) glamt"
											+" from movexposting   where trim(companycode)='100' and trim(divisioncode)=? "+qry+" and eventcode=5 and glcode is not null and flag=1"
											+" group by ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode) b"
											+" on a.ABSUNIQUEID=b.ABSUNIQUEID order by a.accountingdate,a.invoiceno");
        	stat.setString(1, DIVI);
        	stat.setString(2, DIVI);
        	result = stat.executeQuery();
        	while(result.next()){
        		DataTexSaleBean dataTexSaleBean = new DataTexSaleBean();
        		dataTexSaleBean.setABSUNIQUEID(result.getString("ABSUNIQUEID"));
        		dataTexSaleBean.setCOMPANYCODE(result.getString("COMPANYCODE"));
        		dataTexSaleBean.setDIVISIONCODE(result.getString("DIVISIONCODE"));
        		dataTexSaleBean.setFINYEAR(result.getString("FINYEAR"));
        		dataTexSaleBean.setACCOUNTINGDATE(result.getString("ACCOUNTINGDATE"));
        		dataTexSaleBean.setINVOICENO(result.getString("INVOICENO"));
        		dataTexSaleBean.setINVOICEDATE(result.getString("INVOICEDATE"));
        		dataTexSaleBean.setCUSTOMERSUPPLIERCODE(result.getString("CUSTOMERSUPPLIERCODE"));
        		dataTexSaleBean.setTAXFORM(result.getString("TAXFORM"));
        		dataTexSaleBean.setINVAMT(result.getString("INVAMT"));
        		dataTexSaleBean.setGLAMT(result.getString("GLAMT"));
        		dataTexSaleBean.setNETAMT(result.getString("NETAMT"));
        		dataTexSaleBean.setQTY(result.getString("QTY"));
        		dataTexSaleBean.setDUEDATE(result.getString("DUEDATE"));
        		dataTexSaleBean.setCURRENCYCODE(result.getString("CURRENCYCODE"));
        		dataTexSaleBean.setCURRENCYCONVERSIONRATE(result.getString("CURRENCYCONVERSIONRATE"));
        		dataTexSaleBean.setNARRATION(result.getString("NARRATION"));
        		detaillist.add(dataTexSaleBean);

        	}
        } catch (Exception e) {
            System.out.println("1.DataTexSaleAction-execute() " + e);
            addActionError(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
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
	        if (USER_ID == null) {
	        	addActionError("Session Not Valid");
	        	return ERROR;
	        }
		if(chkdel!=null && chkdel.size()>0){
			Connection conn = null;
			Connection conn1 = null;
	        PreparedStatement stat = null;
	        
	        PreparedStatement statupd = null;
	        
                PreparedStatement statsq = null;
	        ResultSet resultSetsq = null;
                
	        PreparedStatement stat1 = null;
	        ResultSet resultSet1 = null;
	        
	        PreparedStatement stat2 = null;
	        ResultSet resultSet2 = null;
	        
	        PreparedStatement stat3 = null;
	        ResultSet resultSet3 = null;
	        
			try {
	            conn = new connection().getConnection();
	            conn.setAutoCommit(false);
	            conn1 = new ConnectionDataTex().getConnection();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            addActionError(e.getMessage());
	        }
			try{
                    String path1 = getValue("reportPdfPath") + "PayChq";
	           // String path1 = "d:/epm/PayChq";
	            String real_filename = path1 + "/"+"sale"+DIVI+".txt";
	            File aa = new File(path1 + "/"+"sale"+DIVI+".txt");
	            if (aa.exists()) {
	                aa.delete();
	            }
	            String textfile = "";
	            FileOutputStream fos = new FileOutputStream(real_filename);
	            PrintWriter pw = new PrintWriter(fos);
	            int ctr=1;
                    	        statsq=conn.prepareStatement("SELECT datatexsale_SQ.nextval from DUAL");
				String SQLUNIQUE=null;
				resultSetsq=statsq.executeQuery();
				if(resultSetsq.next()){
					SQLUNIQUE=resultSetsq.getString(1);
				}
				for(int i=0;i<chkdel.size();i++){
		                       
                                        stat = conn1.prepareStatement("SELECT * FROM MOVEXPOSTING WHERE COMPANYCODE='100' AND DIVISIONCODE=? AND EVENTCODE=5 AND FLAG=1 AND ABSUNIQUEID=? ");
                        		stat.setString(1, DIVI);
                                        stat.setString(2, chkdel.get(i).toString());
					ResultSet resultSet4=stat.executeQuery();
					while(resultSet4.next()){
                                            
                                            
   stat = conn.prepareStatement("INSERT INTO SHAHIWEB.MOVEXPOST(COMPANYCODE,ABSUNIQUEID ,ROWINDEXNUM,ROWINDEXSUBNUM ,DIVISIONCODE,PLANTCODE,FINYEAR,ACCOUNTINGDATE,INVOICENO,INVOICEDATE,EXPENSECODE,COSTCENTERCODE, "+                
" PROFITCENTER,PRODUCTGROUPCODE,CUSTOMERSUPPLIERCODE ,CONO,COTYPE,NARRATION ,EVENT ,QUANTITY,CURRENCYCODE,CURRENCYCONVERSIONRATE  ,CURRENCYAMOUNT,INVAMOUTINR  ,PAYMENTTYPE  ,PAYMENTMETHOD ,GLCODE,  "+                       
" GLAMOUNT,TAXFORM ,MINGRNDATE,MAXGRNDATE,USERID ,FLAG ,TRANSFERDATE,DUEDATE ,EVENTCODE,NUMCOL1 ,NUMCOL2 ,DATECOL1 ,DATECOL2  ,CHARCOL1  ,CHARCOL2,   "+                     
" REVONO,NUMCOL3,PLANTINVOICECODE )  values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?)");
  
stat.setString( 1	,	resultSet4.getString("COMPANYCODE"));
stat.setString(	2	,	resultSet4.getString("ABSUNIQUEID"));
stat.setString(	3	,	resultSet4.getString("ROWINDEXNUM"));
stat.setString(	4	,	resultSet4.getString("ROWINDEXSUBNUM"));
stat.setString(	5	,	resultSet4.getString("DIVISIONCODE"));
stat.setString(	6	,	resultSet4.getString("PLANTCODE"));
stat.setString(	7	,	resultSet4.getString("FINYEAR"));
stat.setString(	8	,	resultSet4.getString("ACCOUNTINGDATE"));
stat.setString(	9	,	resultSet4.getString("INVOICENO"));
stat.setString(	10	,	resultSet4.getString("INVOICEDATE"));
stat.setString(	11	,	resultSet4.getString("EXPENSECODE"));
stat.setString(	12	,	resultSet4.getString("COSTCENTERCODE"));
stat.setString(	13	,	resultSet4.getString("PROFITCENTER"));
stat.setString(	14	,	resultSet4.getString("PRODUCTGROUPCODE"));
stat.setString(	15	,	resultSet4.getString("CUSTOMERSUPPLIERCODE"));
stat.setString(	16	,	resultSet4.getString("CONO"));
stat.setString(	17	,	resultSet4.getString("COTYPE"));
stat.setString(	18	,	resultSet4.getString("NARRATION"));
stat.setString(	19	,	resultSet4.getString("EVENT"));
stat.setString(	20	,	resultSet4.getString("QUANTITY"));
stat.setString(	21	,	resultSet4.getString("CURRENCYCODE"));
stat.setString(	22	,	resultSet4.getString("CURRENCYCONVERSIONRATE"));
stat.setString(	23	,	resultSet4.getString("CURRENCYAMOUNT"));
stat.setString(	24	,	resultSet4.getString("INVAMOUTINR"));
stat.setString(	25	,	resultSet4.getString("PAYMENTTYPE"));
stat.setString(	26	,	resultSet4.getString("PAYMENTMETHOD"));
stat.setString(	27	,	resultSet4.getString("GLCODE"));
stat.setString(	28	,	resultSet4.getString("GLAMOUNT"));
stat.setString(	29	,	resultSet4.getString("TAXFORM"));
stat.setString(	30	,	resultSet4.getString("MINGRNDATE"));
stat.setString(	31	,	resultSet4.getString("MAXGRNDATE"));
stat.setString(	32	,	resultSet4.getString("USERID"));
stat.setString(	33	,	resultSet4.getString("FLAG"));
stat.setString(	34	,	resultSet4.getString("TRANSFERDATE").substring(0, 10));
stat.setString(	35	,	resultSet4.getString("DUEDATE"));
stat.setString(	36	,	resultSet4.getString("EVENTCODE"));
stat.setString(	37	,	resultSet4.getString("NUMCOL1"));
stat.setString(	38	,	resultSet4.getString("NUMCOL2"));
stat.setString(	39	,	resultSet4.getString("DATECOL1"));
stat.setString(	40	,	resultSet4.getString("DATECOL2"));
stat.setString(	41	,	resultSet4.getString("CHARCOL1"));
stat.setString(	42	,	resultSet4.getString("CHARCOL2"));
stat.setString(	43	,	resultSet4.getString("REVONO"));
stat.setString(	44	,	resultSet4.getString("NUMCOL3"));
stat.setString(	45	,	resultSet4.getString("PLANTINVOICECODE"));

									
					stat.executeUpdate();
					if(stat!=null){
						stat.close();
					}
                                        }
                                        if(stat!=null){
                                            
                                            
						stat.close();
					}
			                        
                                      /*  stat = conn.prepareStatement("INSERT INTO SHAHIWEB.MOVEXPOST SELECT * FROM MOVEXPOSTING@NOWPROD WHERE COMPANYCODE='100' AND DIVISIONCODE=? AND EVENTCODE=5 AND FLAG=1 AND ABSUNIQUEID=? ");				
					stat.setString(1, DIVI);
                                        stat.setString(2, chkdel.get(i).toString());
					stat.executeUpdate();
					if(stat!=null){
						stat.close();
					}
					*/
					stat1=conn.prepareStatement("select 'I1'||rpad('"+SQLUNIQUE+"',9)||rpad('"+ctr+"',8)||'500'||rpad(a.customersuppliercode,10)||rpad(a.customersuppliercode,10)||rpad(substr(a.invoiceno,4,10),10)||"
													+" to_char(a.invoicedate,'YYYYMMDD')||to_char(a.duedate,'YYYYMMDD')||lpad(nvl(a.invamt,0)+nvl(b.glamt,0),17)||lpad(' ',19)||rpad(CURRENCYCODE,3)||rpad('1',2)||"
													+" lpad(CURRENCYCONVERSIONRATE,13)||to_char(a.ACCOUNTINGDATE,'YYYYMMDD')||rpad(trim(a.CUSTOMERSUPPLIERCODE)||'  '||substr(a.invoiceno,4,10),40)||rpad('15102',8)||rpad('KPD',8)||rpad('KAK10000',8)||rpad(' ',8)||"
													+" rpad(a.CUSTOMERSUPPLIERCODE,8)||rpad(' ',8)||lpad('0',15)||'TT'||'TTE'||'003'||rpad(' ',8) kk"
													+" from"
													+" (select ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,taxform,duedate,currencycode,"
													+" currencyconversionrate,narration ,sum(QUANTITY) qty,sum(INVAMOUTINR) invamt"
													+" from movexpost a where  a.companycode=100 and a.divisioncode=? and a.ABSUNIQUEID=? and a.eventcode=5  and flag=1 and a.glcode is null"
													+" group by ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,taxform,duedate,currencycode,"
													+" currencyconversionrate,narration ) a,"
													+" (select ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode,sum(glamount) glamt"
													+" from movexpost   where companycode=100 and divisioncode=? and ABSUNIQUEID=? and eventcode=5 and flag=1 and glcode is not null"
													+" group by ABSUNIQUEID,companycode,divisioncode,finyear,accountingdate,invoiceno,invoicedate,customersuppliercode) b"
													+" where a.ABSUNIQUEID=b.ABSUNIQUEID(+) order by a.accountingdate");
					stat1.setString(1, DIVI);
					stat1.setString(2, chkdel.get(i).toString());
					stat1.setString(3, DIVI);
					stat1.setString(4, chkdel.get(i).toString());
					resultSet1=stat1.executeQuery();
					while(resultSet1.next()){
						textfile = resultSet1.getString("kk").trim();
		                //System.out.println(textfile);
                                  pw.println(textfile);
					}
					if(stat1!=null){
						stat1.close();
					}
					if(resultSet1!=null){
						resultSet1.close();
					}
					
					stat2=conn.prepareStatement("select 'I2'||rpad('"+SQLUNIQUE+"',9)||rpad('"+ctr+"',8)||'500'||rpad(CUSTOMERSUPPLIERCODE,10)||rpad(CUSTOMERSUPPLIERCODE,10)||rpad(substr(INVOICENO,4,10),10)||to_char(INVOICEDATE,'YYYYMMDD')||"
													+" to_char(DUEDATE,'YYYYMMDD')||lpad(0-INVAMOUTINR,17)||lpad(' ',19)||rpad(CURRENCYCODE,3)||rpad('1',2)||lpad(CURRENCYCONVERSIONRATE,13)||to_char(ACCOUNTINGDATE,'YYYYMMDD')||"
													+" rpad(NARRATION,40)||rpad(EXPENSECODE,8)||rpad(nvl(PROFITCENTER,' '),8)||rpad(nvl(COSTCENTERCODE,' '),8)||rpad(nvl(PRODUCTGROUPcode,'001'),8)||rpad(CUSTOMERSUPPLIERCODE,8)||rpad(nvl(cono,' '),8)||"
													+" lpad(QUANTITY,15)||'TT'||'TTE'||'003'||rpad(nvl(EVENT,' '),8) kk FROM shahiweb.movexpost where companycode=100 and divisioncode=? and eventcode=5 and ABSUNIQUEID=?  and flag=1 and glcode is null");
					stat2.setString(1, DIVI);
					stat2.setString(2, chkdel.get(i).toString());
					resultSet2=stat2.executeQuery();
					while(resultSet2.next()){
						textfile = resultSet2.getString("kk").trim();
		                //System.out.println(textfile);
		                pw.println(textfile);
					}
					if(stat2!=null){
						stat2.close();
					}
					if(resultSet2!=null){
						resultSet2.close();
					}
					
					stat3=conn.prepareStatement("select 'I2'||rpad('"+SQLUNIQUE+"',9)||rpad('"+ctr+"',8)||'500'||rpad(CUSTOMERSUPPLIERCODE,10)||rpad(CUSTOMERSUPPLIERCODE,10)||rpad(substr(INVOICENO,4,10),10)||to_char(INVOICEDATE,'YYYYMMDD')||"
													+" to_char(DUEDATE,'YYYYMMDD')||lpad(0-GLAMOUNT,17)||lpad(' ',19)||rpad(CURRENCYCODE,3)||rpad('1',2)||lpad(CURRENCYCONVERSIONRATE,13)||to_char(ACCOUNTINGDATE,'YYYYMMDD')||"
													+" rpad(NARRATION,40)||rpad(GLCODE,8)||rpad(nvl(PROFITCENTER,' '),8)||rpad(nvl(COSTCENTERCODE,' '),8)||rpad(nvl(PRODUCTGROUPcode,'001'),8)||rpad(CUSTOMERSUPPLIERCODE,8)||rpad(nvl(cono,' '),8)||"
													+" lpad('0',15)||'TT'||'TTE'||'003'||rpad(nvl(EVENT,' '),8) kk FROM shahiweb.movexpost where companycode=100 and divisioncode=? and eventcode=5 and ABSUNIQUEID=? and flag=1 and glcode is not null");
					stat3.setString(1, DIVI);
					stat3.setString(2, chkdel.get(i).toString());
					resultSet3=stat3.executeQuery();
					while(resultSet3.next()){
						textfile = resultSet3.getString("kk").trim();
                                                //System.out.println(textfile);
                                                pw.println(textfile);
					}
					if(stat3!=null){
						stat3.close();
					}
					if(resultSet3!=null){
						resultSet3.close();
					}
					++ctr;
				}
				pw.close();
                                fos.close();
	            
	            Ftpfileupload fileobj = new Ftpfileupload();
                String connst = getValue("MIIP");
                String cuser = getValue("UserNameDB2");
                String cpass = getValue("PasswordDB2");
                String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/M3BE1512/env/M3BE_15.1_PRD/transfer/GLS850MI/"+"sale"+DIVI+".txt");
                if (ftpout.equals("OK")) {
                	GLS850MI MI = new GLS850MI();
                    MI.connect();
                    String status = MI.DataUpload("111", "500", "AR-SALE-"+DIVI, "sale"+DIVI+".txt", "GLS850MI", "0");
                    MI.destroyMI();
                    MI = null;
                    
                    for(int i=0;i<chkdel.size();i++){                    
	                    statupd=conn1.prepareStatement("UPDATE MOVEXPOSTING SET FLAG=2 WHERE ABSUNIQUEID=? AND COMPANYCODE='100' AND DIVISIONCODE=? AND EVENTCODE=5");
						statupd.setString(1, chkdel.get(i).toString());
						statupd.setString(2, DIVI);
						statupd.executeUpdate();	
						if(statupd!=null){
							statupd.close();
						}
	                    
	                    statupd=conn.prepareStatement("UPDATE SHAHIWEB.MOVEXPOST SET FLAG=2 WHERE ABSUNIQUEID=? AND COMPANYCODE='100' AND DIVISIONCODE=? AND EVENTCODE=5");
						statupd.setString(1, chkdel.get(i).toString());
						statupd.setString(2, DIVI);
						statupd.executeUpdate();	
						if(statupd!=null){
							statupd.close();
						}
                    }
                }
				conn.commit();
				MailProvider mailProvider = new MailProvider();
                String[] filenames = new String[1];
                filenames[0] = path1 + "/sale"+DIVI+".txt";
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
                subjecttitle = "Sale Voucher Posted From DataTex on dated: " +format.format(new java.util.Date());
                messageBodyText = "<html>";
                messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                messageBodyText += "<body bgcolor=#95b174>";
                messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                messageBodyText += "This is to inform you that attached Sale Voucher file uploaded in Movex. ";
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
	            System.out.println("1.DataTexSaleAction-save() " + e);
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
	        }
		}
		addActionError("Record Save Successfully");
		return SUCCESS;
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
	public List<DataTexSaleBean> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<DataTexSaleBean> detaillist) {
		this.detaillist = detaillist;
	}
	public List getChkdel() {
		return chkdel;
	}
	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}
	
}
