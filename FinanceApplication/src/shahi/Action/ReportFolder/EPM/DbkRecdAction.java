/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import shahi.Action.ReportFolder.EPM.beans.DbkBean;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;
import shahi.Action.Common.Ftpfileupload;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DbkRecdAction extends ActionSupport {
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
    	
	public DbkRecdAction() {
		aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
	}


	private List<DbkBean> detaillist;
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
            System.out.println("1.DbkRecdAction " + e);
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
		detaillist = new ArrayList<DbkBean>();
		Connection conn = null;
		Connection conn1=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        try {
             conn = new connection().getConnection();
            conn1 = new connectiondb2().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
        	String qry=" ";
        	if((DATEFROM!=null && DATEFROM.length()>0) && (DATETO!=null && DATETO.length()>0)){
        		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        		String datefrom=simpleDateFormat.format(simpleDateFormat.parse(DATEFROM));
        		String dateto=simpleDateFormat.format(simpleDateFormat.parse(DATETO));
        		qry+=" and chq_date between '"+datefrom+"' and '"+dateto+"'";
        	}
              
                
                stat1 = conn.prepareStatement("select a.pay_id,a.chq_no,a.dbk_amt from finac.fa_dbk_mast a,(select pay_id,sum(acc_amt) acc_amt from finac.fa_dbk_dtls where pay_id is not null and acc_Code is null "+ 
                        " group by pay_id) b where a.division=? and a.pay_id=b.pay_id and fin_date is null and (a.dbk_amt-b.acc_amt)<>0"+qry);           
                stat1.setString(1, DIVI);
                //stat1.setString(2, DIVI);
        	result1 = stat1.executeQuery();
                if (result1.next()) {
               // disble = "true";
                addActionError(result1.getString("pay_id") + "-" +result1.getString("chq_no") + "-" + result1.getString("dbk_amt") + " Invalid record.");
                 
                return SUCCESS;
                }
                
        	stat = conn.prepareStatement("select division,year,acc_data_desc,dbk_amt,srv_amt,chq_no,to_char(chq_date,'dd-mm-yyyy') chq_date from finac.fa_dbk_mast where division=? and fin_date is null and chq_date>'31-MAR-15' "+qry+" order by chq_date");  	
                stat.setString(1, DIVI);
        	//stat.setString(2, DIVI);
        	result = stat.executeQuery();
        	while(result.next()){
        		DbkBean dbkBean = new DbkBean();
        		//DbkBean.setABSUNIQUEID(result.getString("ABSUNIQUEID"));
        		//dbkBean.setCOMPANYCODE(result.getString("COMPANYCODE"));
        		dbkBean.setDIVISIONCODE(result.getString("division"));
        		dbkBean.setFINYEAR(result.getString("year"));
        		dbkBean.setACCOUNTINGDATE(result.getString("chq_date"));
        		dbkBean.setINVOICENO(result.getString("chq_no"));
        		dbkBean.setINVOICEDATE(result.getString("chq_date"));
        		//dbkBean.setCUSTOMERSUPPLIERCODE(result.getString("CUSTOMERSUPPLIERCODE"));
        		//dbkBean.setTAXFORM(result.getString("TAXFORM"));
        		dbkBean.setINVAMT(result.getString("dbk_amt"));
        		dbkBean.setGLAMT(result.getString("srv_amt"));
        		//dbkBean.setNETAMT(result.getString("NETAMT"));
        		dbkBean.setNARRATION(result.getString("acc_data_desc"));
        		//System.out.println(result.getString("FINYEAR"));
        	/*	stat1=conn.prepareStatement("select * from  movexposting where companycode=100 and divisioncode=? and finyear=? and trim(CUSTOMERSUPPLIERCODE)=? and  trim(INVOICENO)=? and flag=1");
        		stat1.setString(1, DIVI);
        		stat1.setString(2, result.getString("FINYEAR"));
        		stat1.setString(3, result.getString("CUSTOMERSUPPLIERCODE"));
        		stat1.setString(4, result.getString("INVOICENO").trim()+"-DN");
        		result1 = stat1.executeQuery();
        		if(result1.next()){
        			dbkBean.setDCHK("OK");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
        		
        		stat2 = conn1.prepareStatement("select * from mincdtprod.xidven where xacono=111 and xadivi=500 and xaxtap=1 and xasuno=?");
        		stat2.setString(1, result.getString("CUSTOMERSUPPLIERCODE"));
        		result2 = stat2.executeQuery();
        		if(result2.next()){
        			dbkBean.setTCHK("OK");
        		} */
        		detaillist.add(dbkBean);

        	}
        } catch (Exception e) {
            System.out.println("1.DbkRecdAction-execute() " + e);
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
            
            if (stat1 != null) {
                stat1.close();
            }
            if (result1 != null) {
                result1.close();
            }
        }
        
		return SUCCESS;
	}
	public String save() throws Exception{
		 Map session = ActionContext.getContext().getSession();
	        String USER_ID = (String) session.get("sessUserId");
	        if (USER_ID == null) {
	        	addActionError("Session Not Valid -Save");
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
	        
			try {
	            conn = new connection().getConnection();
	            conn.setAutoCommit(false);
	        //    conn1 = new ConnectionDataTex().getConnection();
	            conn2 = new connectiondb2().getConnection();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            addActionError(e.getMessage());
	        }
		try{
                    String path1 = getValue("reportPdfPath") + "PayChq";
	          // String path1 = "d:/epm/PayChq";
	            String real_filename = path1 + "/"+"dbk"+DIVI+".txt";
	            File aa = new File(path1 + "/"+"dbk"+DIVI+".txt");
	            if (aa.exists()) {
	                aa.delete();
	            }
	            String textfile = "";
	            String filetext = ""; 
	            String LOCT="";
                if(DIVI!=null && DIVI.equals("100")){
              	  LOCT="100";
                }
                else{
              	  LOCT="300";
                }
	            FileOutputStream fos = new FileOutputStream(real_filename);
	            PrintWriter pw = new PrintWriter(fos);
	            int ctr=1;
	            stat=conn.prepareStatement("SELECT finac.dbksrno_SQ.NEXTVAL from DUAL");
				String SQLUNIQUE=null;
				resultSet=stat.executeQuery();
				if(resultSet.next()){
					SQLUNIQUE=resultSet.getString(1);
				} 
				for(int i=0;i<chkdel.size();i++){					
					String division=null;
					String chqno=null;
					String pyear=null;
                                        
					if(chkdel.get(i).toString()!=null && chkdel.get(i).toString().length()>0){
						String arr[] =chkdel.get(i).toString().split("\\$\\$");
						if(arr.length==3){
							division=arr[0];
							chqno=arr[1];
							pyear=arr[2];
                                                      
						}
						//System.out.println("kuld....."+DIVI+" "+chqno+" "+pyear); 
					}
					if(division!=null && chqno!=null && pyear!=null){
						stat = conn.prepareStatement("INSERT INTO finacsys.fa_dbk_temp SELECT * FROM finac.fa_dbk_mast WHERE division=? AND YEAR=? AND chq_no=? and fin_date is null");
						stat.setString(1, DIVI);
						stat.setString(2, pyear);
						stat.setString(3, chqno);
						//stat.setString(4, SUPINV);
						stat.executeUpdate();
						if(stat!=null){
							stat.close();
						}
                                                
						conn.commit();
						//rpad('"+SQLUNIQUE+"',9)
						stat1=conn.prepareStatement("select 'I1'||lpad('"+SQLUNIQUE+"',9)||lpad(a.pay_id,8)||"+DIVI+"||'0'||lpad(rtrim(ltrim(to_char(nvl(b.acc_code,'99995'),'99999'))),5,'0')|| "+
                                                    "rpad(nvl(REMK,' '),3,' ')||rpad(nvl(b.PCH,'COR'),8,' ')||rpad(nvl(b.movex_code,' '),8,' ')||rpad(nvl(PRODUCT_GROUP,' '),8,' ')||rpad(nvl(to_char(b.PARTNER),' '),8,' ')||rpad(nvl(substr(chq_no,5,8),' '),8,' ')||rpad(nvl(event,' '),8,' ')||rpad(nvl(REMK,' '),16,' ')|| "+ 
                                                    "'0INR 1'||rpad(nvl(REMK,' '),12,' ')||'1'||lpad(rtrim(ltrim(to_char(acc_amt,'99999999999999.99'))),17,' ')||lpad(to_char(chq_date,'YYYYMMDD'),'8','0')||' 0'||rpad(nvl(substr(trim(acc_data_desc)||' '||trim(chq_no),1,36),' '),36,' ') flatfile "+
                                                    "from finac.fa_dbk_mast a,finac.fa_dbk_dtls b where  a.division=? AND a.YEAR=? AND a.chq_no=?  and a.pay_id=b.pay_id order by a.pay_id,b.id");
							stat1.setString(1, DIVI);
							stat1.setString(2, pyear);
							stat1.setString(3, chqno);
                                                     //     stat.setString(2, chkdel.get(i).toString());
							resultSet=stat1.executeQuery();
							while(resultSet.next()){								
                                                             textfile = resultSet.getString("flatfile").trim();
                                                             //System.out.println(textfile);
                                                             pw.println(textfile);
								
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
             //   String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/m3be/env/PROD/MvxTransfer/"+"dbk"+DIVI+".txt");
                String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/M3BE1512/env/M3BE_15.1_PRD/transfer/GLS850MI/"+"dbk"+DIVI+".txt");
                if (ftpout.equals("OK")) {
                	GLS850MI MI = new GLS850MI();
                    MI.connect();
                    String status = MI.DataUpload("111",DIVI,"DBK-RECD-"+DIVI, "dbk"+DIVI+".txt", "GLS850MI", "1");
                   //    status = MI.DataUpload("111", DIVI, minte, filename+".txt", "GLS850MI", "1");
                    MI.destroyMI();
                    MI = null;
                    
                    for(int i=0;i<chkdel.size();i++){  
					String division=null;
					String chqno=null;
					String pyear=null;
    					if(chkdel.get(i).toString()!=null && chkdel.get(i).toString().length()>0){
    						String arr[] =chkdel.get(i).toString().split("\\$\\$");
    						if(arr.length==3){
							division=arr[0];
							chqno=arr[1];
							pyear=arr[2];
    						}
    						//System.out.println(SUPCODE+" "+SUPINV+" "+FINYEAR);
    					}
    					if(division!=null && chqno!=null && pyear!=null){
	    					statupd=conn.prepareStatement("UPDATE FINAC.FA_DBK_MAST SET FIN_DATE=SYSDATE WHERE DIVISION=? and YEAR=? and CHQ_NO=? and fin_Date is null");
							statupd.setString(1, division);
							statupd.setString(2, pyear);
							statupd.setString(3, chqno);
							//statupd.setString(4, SUPINV);
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
                filenames[0] = path1 + "/dbk"+DIVI+".txt";
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
                subjecttitle = "DBK Voucher Posted on dated: " +format.format(new java.util.Date());
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
	            System.out.println("1.DbkRecdAction-save() " + e);
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
	public List<DbkBean> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<DbkBean> detaillist) {
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
