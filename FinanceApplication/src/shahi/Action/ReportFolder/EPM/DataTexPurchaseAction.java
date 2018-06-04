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
import shahi.Action.ReportFolder.EPM.beans.DataTexPurchaseBean;
import shahi.Action.database.ConnectionDataTex;
import shahi.Action.database.ConnectionDataTextst;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;
import shahi.Action.Common.Ftpfileupload;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataTexPurchaseAction extends ActionSupport {
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
	
	public static ResourceBundle aResourcBundle = null;

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
    
    
	
	public DataTexPurchaseAction() {
		aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
	}



	private List<DataTexPurchaseBean> detaillist;
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
		chkdel=null;
		detaillist = new ArrayList<DataTexPurchaseBean>();
		Connection conn = null;
		Connection conn1=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        try {
            conn = new ConnectionDataTextst().getConnection();
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
        		qry+="  and finyear>2015 and accountingdate between '"+datefrom+"' and '"+dateto+"'";
        	}
        	stat = conn.prepareStatement("select a.companycode,a.divisioncode,a.finyear,a.accountingdate,trim(a.charcol1) INVOICENO,a.invoicedate,trim(a.customersuppliercode) customersuppliercode,a.invamt,b.glamt,nvl"
							+" (a.invamt,0)+nvl(b.glamt,0) netamt,a.qty,a.duedate,a.currencycode,a.currencyconversionrate,a.taxform,a.narration  from"
							+" (select companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode,currencycode,"
							+" currencyconversionrate,sum(QUANTITY) qty,decode(currencycode,'INR',sum(INVAMOUTINR),sum(CURRENCYAMOUNT)) invamt,max(duedate) duedate,max(taxform) taxform,max(narration) narration"
							+" from movexposting a where a.companycode=100 and a.divisioncode=? "+qry+" and a.eventcode=4 and flag=1 and a.charcol1 is not null  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0 and a.glcode is null"
							+" group by companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode,currencycode,"
							+" currencyconversionrate) a left outer join "
							+" (select companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode,sum(glamount) glamt"
							+" from movexposting   where companycode=100 and divisioncode=? "+qry+" and eventcode=4 and flag=1 and charcol1 is not null  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0 and glcode is not null"
							+" group by companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode) b"
							+" on a.companycode=b.companycode and a.divisioncode=b.divisioncode and a.finyear=b.finyear and a.customersuppliercode=b.customersuppliercode and a.charcol1=b.charcol1 where a.companycode='100' order by a.accountingdate");
        	stat.setString(1, DIVI);
        	stat.setString(2, DIVI);
        	result = stat.executeQuery();
        	while(result.next()){
        		DataTexPurchaseBean dataTexPurchaseBean = new DataTexPurchaseBean();
        		//dataTexPurchaseBean.setABSUNIQUEID(result.getString("ABSUNIQUEID"));
        		dataTexPurchaseBean.setCOMPANYCODE(result.getString("COMPANYCODE"));
        		dataTexPurchaseBean.setDIVISIONCODE(result.getString("DIVISIONCODE"));
        		dataTexPurchaseBean.setFINYEAR(result.getString("FINYEAR"));
        		dataTexPurchaseBean.setACCOUNTINGDATE(result.getString("ACCOUNTINGDATE"));
        		dataTexPurchaseBean.setINVOICENO(result.getString("INVOICENO"));
        		dataTexPurchaseBean.setINVOICEDATE(result.getString("INVOICEDATE"));
        		dataTexPurchaseBean.setCUSTOMERSUPPLIERCODE(result.getString("CUSTOMERSUPPLIERCODE"));
        		dataTexPurchaseBean.setTAXFORM(result.getString("TAXFORM"));
        		dataTexPurchaseBean.setINVAMT(result.getString("INVAMT"));
        		dataTexPurchaseBean.setGLAMT(result.getString("GLAMT"));
        		dataTexPurchaseBean.setNETAMT(result.getString("NETAMT"));
        		dataTexPurchaseBean.setQTY(result.getString("QTY"));
        		dataTexPurchaseBean.setDUEDATE(result.getString("DUEDATE"));
        		dataTexPurchaseBean.setCURRENCYCODE(result.getString("CURRENCYCODE"));
        		dataTexPurchaseBean.setCURRENCYCONVERSIONRATE(result.getString("CURRENCYCONVERSIONRATE"));
        		dataTexPurchaseBean.setNARRATION(result.getString("NARRATION"));
        		//System.out.println(result.getString("FINYEAR"));
        		stat1=conn.prepareStatement("select * from  movexposting where companycode=100 and divisioncode=? and finyear=? and trim(CUSTOMERSUPPLIERCODE)=? and  trim(INVOICENO)=? and flag=1");
        		stat1.setString(1, DIVI);
        		stat1.setString(2, result.getString("FINYEAR"));
        		stat1.setString(3, result.getString("CUSTOMERSUPPLIERCODE"));
        		stat1.setString(4, result.getString("INVOICENO").trim()+"-DN");
        		result1 = stat1.executeQuery();
        		if(result1.next()){
        			dataTexPurchaseBean.setDCHK("OK");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
        		
        		stat2 = conn1.prepareStatement("select * from m3fdbprd.cidven where iicono=111 and iisuno=?");
        		stat2.setString(1, result.getString("CUSTOMERSUPPLIERCODE"));
        		result2 = stat2.executeQuery();
        		if(result2.next()){
        			dataTexPurchaseBean.setTCHK("OK");
        		}
        		detaillist.add(dataTexPurchaseBean);

        	}
        } catch (Exception e) {
            System.out.println("1.DataTexPurchaseAction-execute() " + e);
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
	            conn1 = new ConnectionDataTextst().getConnection();
	            conn2 = new connectiondb2().getConnection();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            addActionError(e.getMessage());
	        }
			try{
		 //   String path1 = getValue("reportPdfPath") + "PayChq"; -- KULDEEP
	            String path1 = "d:/epm/PayChq";
	            String real_filename = path1 + "/"+"dinv"+DIVI+".txt";
	            File aa = new File(path1 + "/"+"dinv"+DIVI+".txt");
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
					if(SUPCODE!=null && SUPINV!=null && FINYEAR!=null){
					stat = conn1.prepareStatement("SELECT * FROM MOVEXPOSTING WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? AND FINYEAR=? AND CUSTOMERSUPPLIERCODE=?  AND CHARCOL1=?  AND EVENTCODE=4 and Flag=1 ");
                        		stat.setString(1, DIVI);
                                        stat.setString(2, FINYEAR);
					stat.setString(3, SUPCODE);
					stat.setString(4, SUPINV);
					ResultSet resultSet5=stat.executeQuery();
					while(resultSet5.next()){
                                            
                                            
   stat = conn.prepareStatement("INSERT INTO SHAHIWEB.MOVEXPOST(COMPANYCODE,ABSUNIQUEID ,ROWINDEXNUM,ROWINDEXSUBNUM ,DIVISIONCODE,PLANTCODE,FINYEAR,ACCOUNTINGDATE,INVOICENO,INVOICEDATE,EXPENSECODE,COSTCENTERCODE, "+                
" PROFITCENTER,PRODUCTGROUPCODE,CUSTOMERSUPPLIERCODE ,CONO,COTYPE,NARRATION ,EVENT ,QUANTITY,CURRENCYCODE,CURRENCYCONVERSIONRATE  ,CURRENCYAMOUNT,INVAMOUTINR  ,PAYMENTTYPE  ,PAYMENTMETHOD ,GLCODE,  "+                       
" GLAMOUNT,TAXFORM ,MINGRNDATE,MAXGRNDATE,USERID ,FLAG ,TRANSFERDATE,DUEDATE ,EVENTCODE,NUMCOL1 ,NUMCOL2 ,DATECOL1 ,DATECOL2  ,CHARCOL1  ,CHARCOL2,   "+                     
" REVONO,NUMCOL3,PLANTINVOICECODE )  values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?)");
  
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
stat.setString(	41	,	resultSet5.getString("CHARCOL1"));
stat.setString(	42	,	resultSet5.getString("CHARCOL2"));
stat.setString(	43	,	resultSet5.getString("REVONO"));
stat.setString(	44	,	resultSet5.getString("NUMCOL3"));
stat.setString(	45	,	resultSet5.getString("PLANTINVOICECODE"));

									
					stat.executeUpdate();	
                                                                                     
                                              /*  stat = conn.prepareStatement("INSERT INTO SHAHIWEB.MOVEXPOST SELECT * FROM MOVEXPOSTING@NOWPROD WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? AND FINYEAR=? AND CUSTOMERSUPPLIERCODE=?  AND CHARCOL1=?  AND EVENTCODE=4 and Flag=1");
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
                                        
						stat1=conn.prepareStatement("select a.companycode,a.divisioncode,a.finyear,TO_CHAR(a.accountingdate,'yyyyMMdd') accountingdate,trim(a.charcol1) INVOICENO,TO_CHAR(a.invoicedate,'yyyyMMdd') invoicedate,trim(a.customersuppliercode) customersuppliercode,a.invamt,b.glamt,nvl"
							+" (a.invamt,0)+nvl(b.glamt,0) netamt,a.qty,TO_CHAR(a.duedate,'yyyyMMdd') duedate,a.currencycode,a.currencyconversionrate,trim(a.taxform) taxform,a.narration  from"
							+" (select companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode,currencycode,"
							+" currencyconversionrate,sum(QUANTITY) qty,decode(currencycode,'INR',sum(INVAMOUTINR),sum(CURRENCYAMOUNT)) invamt,max(duedate) duedate,max(taxform) taxform,max(narration) narration"
							+" from movexpost a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.EXPENSECODE)<>'9995' and trim(a.customersuppliercode)=? and trim(a.charcol1)=?  and a.eventcode=4 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0 and a.glcode is null"
							+" group by companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode,currencycode,"
							+" currencyconversionrate) a,"
							+" (select companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode,sum(glamount) glamt"
							+" from movexpost   where companycode=100 and divisioncode=? and finyear=?  and trim(customersuppliercode)=? and trim(charcol1)=? and eventcode=4 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0 and glcode is not null"
							+" group by companycode,divisioncode,finyear,accountingdate,charcol1,invoicedate,customersuppliercode) b"
							+" where a.companycode=b.companycode(+) and a.divisioncode=b.divisioncode(+) and a.finyear=b.finyear(+) and a.customersuppliercode=b.customersuppliercode(+) and a.charcol1=b.charcol1(+) order by a.accountingdate");
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
								filetext="I1"+LeftPad(SQLUNIQUE,9)+RightPad(String.valueOf(ctr),8)+"500"+RightPad(resultSet.getString("customersuppliercode"),10)+RightPad(resultSet.getString("customersuppliercode"),10);
																
	                            filetext=filetext+RightPad(resultSet.getString("INVOICENO"),24)+resultSet.getString("invoicedate")+resultSet.getString("duedate")+LeftPad("-"+resultSet.getString("netamt"),17);
	                            filetext=filetext+RightPad(resultSet.getString("currencycode"),3)+LeftPad("1",3)+LeftPad(resultSet.getString("currencyconversionrate"),13)+resultSet.getString("accountingdate")+RightPad("AUDITSHMG",10);

	                            double amttds=0;
	                            String VTDX="";
	                            String TDSGL="";
	                           /* stat1 = conn2.prepareStatement("select xasuno,t2xtdc,t1ait1,t1ait2,t2xtdr from mincdtprod.xidven,mincdtprod.xtdsrt,mincdtprod.xtdscd"
	        							+" where xacono=111 and xadivi='500'  and xaxtap=1 and xasuno=?"
	        							+" and xacono=t2cono and xadivi=t2divi and xaxtdc=t2xtdc and t2vfdt=20140401"
	        							+" and t2cono=t1cono and t2divi=t1divi and t2xtdc=t1xtdc and t2xtct=t1xtct and t1xtct=1");
                                    */
                                    stat1 = conn2.prepareStatement("select iisers,hedivi,hefrdt,hetodt,hewhpo,cxait1 from m3fdbprd.cidven,cinfdbprd.zapwhp,CAEXEL"
                                    +" where iicono=hecono and hedivi='500'"
                                    +" and iisers=hesers and hecono=cxcono and hedivi=cxdivi and hesers=cxobv1"
                                    +" and cxeven='AP10' and cxacty='396' and cxobv2='BLR' and iisuno=?");
	                            stat1.setString(1, SUPCODE);
	                            resultSet1=stat1.executeQuery();
	                            if(resultSet1.next() && (CHKTDS.get(i)!=null && CHKTDS.get(i).equals("Y"))){	                            	
		                            if (resultSet1.getString("iisers")!=null && !resultSet1.getString("iisers").equals("17"))
		                            {
		                            	VTDX=resultSet1.getString("iisers");
		                            	TDSGL=resultSet1.getString("cxait1");
		                            	amttds=Math.round((resultSet.getDouble("netamt")*resultSet1.getDouble("hewhpo"))/100);
		                              // filetext=filetext+ LeftPad("I",10)+RightPad(resultSet1.getString("iisers"),10);
                                               filetext=filetext+ LeftPad("I",20);
		                            }		                            
	                            }
	                            else
	                            {
	                               filetext=filetext+LeftPad(" ",20);
	                            }
	                            filetext=filetext+LeftPad(" ",32)+RightPad(resultSet.getString("narration"),40)+LeftPad("0.00",19)+LeftPad(" ",8)+LeftPad(" ",15)+RightPad("CHK",3)+LeftPad(" ",8);
	                            pw.println(filetext);
	                            if(amttds>0){
	                            	  filetext="I3"+LeftPad(SQLUNIQUE,9)+RightPad(String.valueOf(ctr),8)+"500"+RightPad(resultSet.getString("customersuppliercode"),10)+RightPad(resultSet.getString("customersuppliercode"),10);
	                                  filetext=filetext+RightPad(resultSet.getString("INVOICENO"),24)+resultSet.getString("invoicedate")+resultSet.getString("duedate")+LeftPad(String.valueOf(amttds),17);
	                                  filetext=filetext+RightPad(resultSet.getString("currencycode"),3)+LeftPad("1",3)+LeftPad(resultSet.getString("currencyconversionrate"),13)+resultSet.getString("accountingdate")+RightPad("AUDITSHMG",10);
	                                  filetext=filetext+LeftPad("T",10)+LeftPad(VTDX,10);
	                                  filetext=filetext+LeftPad(" ",32)+RightPad(resultSet.getString("narration"),40)+LeftPad("0.00",19)+LeftPad(" ",8)+LeftPad(" ",15)+RightPad("CHK",3)+LeftPad(" ",8);
	       
	                                  //              System.out.println(filetext);
	                                  pw.println(filetext);
	                                  
	                                  filetext="I2"+LeftPad(SQLUNIQUE,9)+RightPad(String.valueOf(ctr),8)+"500"+RightPad(resultSet.getString("customersuppliercode"),10)+RightPad(resultSet.getString("customersuppliercode"),10);
	                                  filetext=filetext+RightPad(resultSet.getString("INVOICENO"),24)+resultSet.getString("invoicedate")+resultSet.getString("duedate")+LeftPad("-"+String.valueOf(amttds),17);
	                                  filetext=filetext+RightPad(resultSet.getString("currencycode"),3)+LeftPad("1",3)+LeftPad(resultSet.getString("currencyconversionrate"),13)+resultSet.getString("accountingdate")+RightPad("AUDITSHMG",10);
	                                  filetext=filetext+ RightPad(" ",20)+RightPad(TDSGL,8)+RightPad("COR",8)+RightPad(LOCT,8)+RightPad(resultSet.getString("customersuppliercode"),8);
	                                  filetext=filetext+RightPad(resultSet.getString("narration"),40)+LeftPad("0.00",19)+LeftPad(" ",8)+LeftPad(" ",15)+RightPad("CHK",3)+LeftPad(" ",8);
	                                   pw.println(filetext);
	                            }
                                    
                                    String newtaxform ="";
                                    String taxtp ="";
	                            stat2=conn.prepareStatement("select 'I2'||lpad(max("+SQLUNIQUE+"),9)||rpad('"+ctr+"',8)||'500'||rpad(customersuppliercode,10)||rpad(customersuppliercode,10)||rpad(charcol1,24)||"
											 +" to_char(max(invoicedate),'YYYYMMDD')||to_char(max(duedate),'YYYYMMDD')||LPAD(decode(currencycode,'INR',sum(INVAMOUTINR),sum(CURRENCYAMOUNT)),17)||rpad(currencycode,3)||lpad('1',3)||"
											 +" lpad(currencyconversionrate,13)||to_char(max(accountingdate),'YYYYMMDD')||rpad('AUDITSHMG',10) KK,rpad(EXPENSECODE,8)||rpad(PROFITCENTER,8)||"
											 +" rpad(COSTCENTERCODE,8)||rpad(customersuppliercode,8)||rpad(max(narration),40)||rpad(' ',19)||rpad(PRODUCTGROUPCODE,8)||lpad(sum(QUANTITY),15)||'CHK'||RPAD(CONO,8) DD"
											 +" from movexpost a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.EXPENSECODE)<>'9995'and trim(a.customersuppliercode)=? and trim(a.charcol1)=? and a.eventcode=4 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0"
											 +" and a.glcode is null"
											 +" group by  accountingdate,customersuppliercode,charcol1,currencycode,currencyconversionrate,EXPENSECODE,PROFITCENTER,COSTCENTERCODE,PRODUCTGROUPCODE,CONO");
                                                                stat2.setString(1, DIVI);
								stat2.setString(2, FINYEAR);
								stat2.setString(3, SUPCODE);
								stat2.setString(4, SUPINV);
								resultSet2=stat2.executeQuery();
								while(resultSet2.next()){
									filetext=resultSet2.getString("KK");
                                                                        
                                                                        String vatcnt ="";
                                                                        stat4=conn.prepareStatement("select count(*) kcnt from movexpost a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.EXPENSECODE)<>'9995'and trim(a.customersuppliercode)=? and trim(a.charcol1)=? and a.eventcode=4 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0"
											 +" and a.glcode>16689 AND a.glcode<16692");
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
								//***** rpad(decode(glcode,16603,'S',' '),1) - 1826 CHANGE TO 16603 ON DATED 20170614 rpad(decode(glcode,16603,'S',' '),1)
								stat3=conn.prepareStatement("select 'I2'||lpad(max("+SQLUNIQUE+"),9)||rpad('"+ctr+"',8)||'500'||rpad(customersuppliercode,10)||rpad(customersuppliercode,10)||rpad(charcol1,24)||"
											 +" to_char(max(invoicedate),'YYYYMMDD')||to_char(max(duedate),'YYYYMMDD')||LPAD(sum(glamount),17)||rpad(currencycode,3)||lpad('1',3)||"
											 +" lpad(currencyconversionrate,13)||to_char(max(accountingdate),'YYYYMMDD')||rpad('AUDITSHMG',10)||rpad(decode(taxtp,null,' ','S'),1)||rpad('"+newtaxform+"',9)||lpad('"+taxtp+"',3)||rpad(' ',7)||rpad(glcode,8)||rpad(PROFITCENTER,8)||"
											 +" rpad(COSTCENTERCODE,8)||rpad(customersuppliercode,8)||rpad(max(narration),40)||rpad(' ',19)||rpad(PRODUCTGROUPCODE,8)||lpad(sum(QUANTITY),15)||'CHK'||RPAD(CONO,8) KK"
											 +" from movexpost a where a.companycode=100 and a.divisioncode=? and a.finyear=? and trim(a.customersuppliercode)=? and trim(a.charcol1)=? and a.eventcode=4 and flag=1  and instr(invoiceno,'-DN')=0 and nvl(numcol2,0)=0"
											 +" and a.glcode is not null"
											 +" group by  accountingdate,customersuppliercode,charcol1,currencycode,currencyconversionrate,glcode,PROFITCENTER,COSTCENTERCODE,PRODUCTGROUPCODE,CONO");
								 stat3.setString(1, DIVI);
								 stat3.setString(2, FINYEAR);
								 stat3.setString(3, SUPCODE);
								 stat3.setString(4, SUPINV);
								 resultSet3=stat3.executeQuery();
								 while(resultSet3.next()){
									 filetext=resultSet3.getString("KK");
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
                String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/M3BE1512/env/M3BE_15.1_PRD/transfer/GLS850MI/"+"dinv"+DIVI+".txt");
                if (ftpout.equals("OK")) {
                    GLS850MI MI = new GLS850MI();
                    MI.connect();
                    String status = MI.DataUpload("111", "500", "AP-BILL-"+DIVI, "dinv"+DIVI+".txt", "GLS850MI", "0");
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
	    					statupd=conn1.prepareStatement("UPDATE MOVEXPOSTING SET FLAG=2 WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? and finyear=? and trim(customersuppliercode)=? and trim(charcol1)=? AND EVENTCODE=4 and flag=1");
							statupd.setString(1, DIVI);
							statupd.setString(2, FINYEAR);
							statupd.setString(3, SUPCODE);
							statupd.setString(4, SUPINV);
							statupd.executeUpdate();	
							if(statupd!=null){
								statupd.close();
							}
		                    
		                    statupd=conn.prepareStatement("UPDATE SHAHIWEB.MOVEXPOST SET FLAG=2 WHERE COMPANYCODE='100' and instr(invoiceno,'-DN')=0 AND DIVISIONCODE=? and finyear=? and trim(customersuppliercode)=? and trim(charcol1)=? AND EVENTCODE=4 and flag=1");
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
	/*	MailProvider mailProvider = new MailProvider();
                String[] filenames = new String[1];
                filenames[0] = path1 + "/dinv"+DIVI+".txt";
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
                subjecttitle = "Purchase Voucher Posted From DataTex on dated: " +format.format(new java.util.Date());
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
                */
			} catch (Exception e) {
				conn.rollback();
	            System.out.println("1.DataTexPurchaseAction-save() " + e);
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
	public List<DataTexPurchaseBean> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<DataTexPurchaseBean> detaillist) {
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
