package shahi.Action.ReportFolder.EPM;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;

import shahi.Action.ReportFolder.EPM.beans.GnrtrefnoFbadiciciBeanNM;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;
import shahi.Action.database.ConnectionCrmsma;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


/**
*
* @author Shilpa
*/
public class GnrtrefnoSMAHdfcAction extends ActionSupport{
	    
	private String LOC;
	private String LETTER_TYPE;
        private String COMPID;
	private String REF_NO;
	private List YEAR;
	private List BANK;
	private List CHQ_NO;
	private List BOA;
	private List VCH_NO;
	private List STATUS;
	private List SUPPLIER;
	private List CHQ_AMT;
	private List PFSCT;
	private List SELECT_TYPE;
	private String aausrid;
	private List CHK;
	private List<File> attachfile;
	private List<String> attachfileContentType;
	private List<String> attachfileFileName;
	private String TINDEX;
	private String OLD_REF_NO;
	private String textid;
	
	private String T_BOA;
	private String T_VCH_NO;
	private String T_STATUS;
	private String T_SUPPLIER;
	private String T_CHQ_AMT;
	private String T_STAUS;
	private String T_PFSCT;
	
	private String XYEAR;
	private String XBANK;
	private String XCHQ_NO;
	private String ERRORMSG;
			
	private List<GnrtrefnoFbadiciciBeanNM> gnrtrefnoFbadiciciBeansList;
	  public String mksess() throws SQLException, ParseException{
          Map session = ActionContext.getContext().getSession();
          String usrid = (String) session.get("sessUserId");
          Connection conn = null;
           Connection connsma = null;
          PreparedStatement stat = null;
          ResultSet result = null;
          if (usrid != null){
              session.remove("sessUserName");
              session.remove("sessUserId");
              session.remove("sessLocationCode");
              session.remove("sessUnitCode");
              session.remove("sessDeptCode");
                            
              try {
                  conn = new connection().getConnection();
                  conn.setAutoCommit(false);
                  connsma = new ConnectionCrmsma().getConnection();
                  connsma.setAutoCommit(false);
              } catch (Exception e) {
                  System.out.println(e.toString());
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
                  System.out.println("GnrtrefnoSMAHdfcAction " + e);
              } finally {
                  if (conn != null) {
                      conn.close();
                  }
              }
          }
          else{
              try {
                  conn = new connection().getConnection();
                  conn.setAutoCommit(false);
              } catch (Exception e) {
                  System.out.println(e.toString());
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
                  System.out.println("GnrtrefnoSMAHdfcAction " + e);
              } finally {
                  if (conn != null) {
                      conn.close();
                  }
              }
          }
          String unitcode = (String) session.get("sessUnitCode");
          LOC=unitcode;
          refno();
          return "upload";
      }
   
	  public String clear() throws Exception 
	  {
                Map session = ActionContext.getContext().getSession();
	       String usrid = (String) session.get("sessUserId");
	       String unitcode = (String) session.get("sessUnitCode");
	       
	          LOC=unitcode;
	       refno();
	    return SUCCESS;   
		   
	  }
	 
	  public void refno() throws SQLException{

	   Map session = ActionContext.getContext().getSession();
           String usrid = (String) session.get("sessUserId");
           String unitcode = (String) session.get("sessUnitCode");
	   LOC="FBAD";
	   Connection con=null;
           Connection consma=null;
	   PreparedStatement stat=null;
	   ResultSet resultset=null;
	   PreparedStatement stat1=null;
	   ResultSet resultset1=null;
	   
	    int flag=0;
	    int flag1=0;
	    int chno=0;
	    int flag2=0;
	    int flag3=0;
	    int flag4=0;
	    int pmtflag=0;
	   
	   try
	   {
		        con=new connectiondb2().getConnection();
                        consma=new ConnectionCrmsma().getConnection();
			stat=consma.prepareStatement("select max(ifnull(crefno,0))+1 crefno from mvxcdtshah.chqref where cloccd=?");
			stat.setString(1,unitcode);
			resultset=stat.executeQuery();
			
			if(REF_NO!=null)
			{
				stat1=consma.prepareStatement("select cvser,cvono from mvxcdtshah.chqref where crefno=? and cloccd=?");
				stat1.setString(1,REF_NO);
				stat1.setString(2,unitcode);
				resultset1=stat1.executeQuery();
				if(resultset1.next())
				{
					flag2=1;
				}
			}			
			else if(resultset.next()){
				REF_NO=resultset.getString(1);
				OLD_REF_NO=resultset.getString(1);
			} 
	   }
	   
	   catch(Exception e)
	   {
		   System.out.println("RefnoException" +e);
	   }
	   finally
	   {
		   if(con!=null)
			   con.close();
		   if(stat!=null)
			   stat.close();
		  if(stat1!=null)
			  stat1.close();
		  if(resultset!=null)
			  resultset.close();
		  if(resultset1!=null)
			  resultset1.close();
		}
	 }

	  public String search() throws Exception
	  {
		  if(textid !=null && textid.length()>0 && XYEAR!=null && XBANK!=null && XCHQ_NO!=null){
		  int index = Integer.parseInt(textid);
		Map session = ActionContext.getContext().getSession();
                String usrid = (String) session.get("sessUserId");
                String unitcode = (String) session.get("sessUnitCode");
		Connection con=null;
                Connection consma=null;
		PreparedStatement stat=null;
		PreparedStatement stat1=null;
		PreparedStatement stat2=null;
		PreparedStatement stat3=null;
		PreparedStatement stat4=null;
		ResultSet resultset=null;
		ResultSet resultset1=null;
		ResultSet resultset2=null;
		ResultSet resultset3=null;
		ResultSet resultset4=null;
		
		OLD_REF_NO=null;
		
		int flag=0;
	    int flag1=0;
	    int chno=0;
	    int a=0;
	    int b=0;
	    int pmtflag=0;
	    
		try
		{
		con=new connectiondb2().getConnection();
		consma=new ConnectionCrmsma().getConnection();
		if((XYEAR !=null && XYEAR.length()>0)&&(XBANK !=null && XBANK.length()>0)&&(XCHQ_NO !=null && XCHQ_NO.length()>0))
		{
                      // and ckdivi='100' remove 02/08/16.
                    System.out.println(COMPID+XYEAR+XBANK);
			stat=consma.prepareStatement("select ckyea4,ckbkid,ckchkn,ckvser,ckvono,ckstts,ckpycu,xpxtda,0-bankamt bankamt,bankcrg,ckspyn"
					+ " from MVXCDTSHAH.FAPMAST_NEW where ckcono=777 and ckdivi=? and ckyea4=? and ckbkid=? and ckchkn=?");
                        stat.setString(1,COMPID);
                        stat.setString(2,XYEAR);
			stat.setString(3,XBANK.toUpperCase());
			stat.setString(4,XCHQ_NO);
			resultset = stat.executeQuery();
			if(resultset.next())
			{
				T_BOA=resultset.getString("ckvser");
				T_VCH_NO=resultset.getString("ckvono");
				T_STATUS=resultset.getString("ckstts");
				T_CHQ_AMT=resultset.getString("bankamt");
				T_SUPPLIER=resultset.getString("ckspyn");
                                stat4=consma.prepareStatement("select substr(ZICFR7,1,4) pymt from shacdtsma.zidmas where zicono=777 and zisuno=?");
				stat4.setString(1,T_SUPPLIER);
				resultset4=stat4.executeQuery();
				if (resultset4.next())
                                    {
                                      T_PFSCT=resultset4.getString("pymt").trim();
                                    }
				String msg="";
				if(resultset.getString("ckstts")!=null && resultset.getString("ckstts").equals("5")){
					addActionError(resultset.getString("ckchkn")+" Already Reversed in Movex...");
					T_STAUS="1";
				}
				else{
					stat1=consma.prepareStatement("select cvser,cvono,crefno from mvxcdtshah.chqref where cyear=? and cbank=? and cchqno=?");
					stat1.setString(1,resultset.getString("ckyea4"));
					stat1.setString(2,resultset.getString("ckbkid"));
					stat1.setString(3,resultset.getString("ckchkn"));
					resultset1=stat1.executeQuery();
					if(resultset.getString("ckstts")!=null && resultset.getString("ckstts").equals("1"))
					{
						addActionError(resultset.getString("ckchkn")+" Already Reversed in Movex...");
						T_STAUS="1";
					}
					else{
						stat2=consma.prepareStatement("SELECT ZICFR2 THTX40,ZICFR3 THKFLD  FROM shacdtsma.zidmas WHERE  zicono=777 AND zisuno=?");
						stat2.setString(1,resultset.getString("ckspyn"));
						resultset2=stat2.executeQuery();
						if(resultset2.next())
						 a=0;	
						
						else
							a=1;
						if(a==1 && LETTER_TYPE=="R")
						{
							addActionError("Supplier's RTGS Detail Not Found/Invalid RTGS/IFSC....!");
							T_STAUS="1";
						}
						else{
								stat3=consma.prepareStatement("SELECT ZICFR7 IDVRNO,ZICFR6 IDSUCM FROM shacdtsma.ZIDMAS WHERE ZICONO='777' AND "
										+ " ZICFR7<>'' AND ZICFR6<>'' AND ZICFR5<>'' and length(trim(ZICFR6))>6 and length(trim(ZICFR7))>10"
										+ " AND ZISUNO=?");
								stat3.setString(1,resultset.getString("ckspyn"));
								resultset3=stat3.executeQuery();
								if(resultset3.next())
									b=0;
								else
									b=1;
								
								if(b==1 && LETTER_TYPE=="R")
								{
									addActionError("Supplier's RTGS Detail Not Found/Invalid RTGS/IFSC....!");
									T_STAUS="1";
								}
							}

					}
					
				}
				
			}
			else{
				ERRORMSG="Record Not Found!!!";
			}
		}
		}
		catch(Exception e)
		{
			System.out.println("GnrtrefnoSMAHdfc" +e);
		}
		finally
		{
			if(con!=null)
				con.close();
			if(stat!=null)
				stat.close();
			if(resultset!=null)
				resultset.close();
			if(stat1!=null)
				stat1.close();
			if(stat2!=null)
				stat2.close();
			if(stat3!=null)
				stat3.close();
			if(stat4!=null)
				stat4.close();
			if(resultset1!=null)
				resultset1.close();
			if(resultset2!=null)
				resultset2.close();
			if(resultset3!=null)
				resultset3.close();
			if(resultset4!=null)
				resultset4.close();
		}
		  }
		return "searchdt";
	}
	  
	  
	  public String save() throws Exception
	  {
              Map session = ActionContext.getContext().getSession();
	      String usrid = (String) session.get("sessUserId");
	      String unitcode = (String) session.get("sessUnitCode");
		  Connection con=null;
                  Connection consma=null;
		  PreparedStatement stat=null;
		  ResultSet resultset=null;
		  
		  try
		  { 
			  con=new connectiondb2().getConnection();
			  con.setAutoCommit(false);
                          consma=new ConnectionCrmsma().getConnection();
			  consma.setAutoCommit(false);
			  ActionContext ac = ActionContext.getContext();
			  ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
			 // File uploadDir = new File(sc.getRealPath("/shahiwebpages/ReportFolder/EPM/payrefupd/"));
			 // if (uploadDir.exists() == false) {
		//		  uploadDir.mkdirs();
		//	  }
	            
			  for(int i=0; i<YEAR.size(); i++)
			  {
				  if(YEAR.get(i).toString()!=null && YEAR.get(i).toString().length()>0 && BANK.get(i).toString()!=null && BANK.get(i).toString().length()>0 && CHQ_NO.get(i).toString()!=null && CHQ_NO.get(i).toString().length()>0){
					 String filenametemp=null;
					 /*if(attachfile.get(i)!=null){		            	
						 String newfilename=YEAR.get(i).toString()+BANK.get(i).toString().toUpperCase()+CHQ_NO.get(i).toString();
						 String ext = this.attachfileFileName.get(i).substring(this.attachfileFileName.get(i).lastIndexOf("."));
						 filenametemp=newfilename+ext;
						 File fileToCreate = new File(uploadDir, filenametemp);
						 FileUtils.copyFile(attachfile.get(i), fileToCreate);
			         } */
                                      System.out.println(COMPID+YEAR+BANK+LOC+"-2");   
				      stat=consma.prepareStatement("insert into mvxcdtshah.chqref(CYEAR,CBANK,CCHQNO,CVSER,CVONO,CSTTS,CREFNO,CCHID,CRGDT,CRGTM,CSUNO,CREFTP,CLOCCD,CFILE,CPYMT,CCMPID) "
					  		+ " VALUES (?,?,?,?,?,?,?,?,current date,current time,?,?,?,?,?,?)");
					  stat.setString(1,YEAR.get(i).toString());
					  stat.setString(2,BANK.get(i).toString().toUpperCase());
					  stat.setString(3,CHQ_NO.get(i).toString());
					  stat.setString(4,BOA.get(i).toString());
					  stat.setString(5,VCH_NO.get(i).toString());
					  stat.setString(6,STATUS.get(i).toString());
					  stat.setString(7,REF_NO);
					  stat.setString(8,usrid);
					  stat.setString(9,SUPPLIER.get(i).toString());
					  stat.setString(10,LETTER_TYPE);
					  stat.setString(11,LOC);
					  stat.setString(12,filenametemp);
					  stat.setString(13, SELECT_TYPE.get(i).toString());
                                          stat.setInt(14,Integer.parseInt(COMPID));
					  stat.executeUpdate();
				  }				  
			  }		
			  con.commit();
                          consma.commit();
			  addActionError("Record Saved Successfully!!!");
		  }
		  catch(SQLException se)
		  {
			  con.rollback();
			  addActionError("Record Not Saved!!!-1");
			  System.out.println("GnrtrefnoSMAHdfcException" +se);
		  }
		  catch(Exception e)
		  {
			  con.rollback();
			  addActionError("Record Not Saved!!!-2");
			  System.out.println("Exception" +e);
		  }
		  finally
		  {
			  if(con!=null)
				  con.close();
			  if(stat!=null)
				  stat.close();
			  if(resultset!=null)
				  resultset.close();
		  }
		  return SUCCESS;
	  }
	
	public String searchdt(){
		return "searchdt";
	}
	
	public String getLOC() {
		return LOC;
	}
	public void setLOC(String lOC) {
		LOC = lOC;
	}
	public String getLETTER_TYPE() {
		return LETTER_TYPE;
	}
	public void setLETTER_TYPE(String lETTER_TYPE) {
		LETTER_TYPE = lETTER_TYPE;
	}
        public String getCOMPID() {
        return COMPID;
         }
        public void setCOMPID(String COMPID) {
        this.COMPID = COMPID;
        }
        
	public String getREF_NO() {
		return REF_NO;
	}
	public void setREF_NO(String rEF_NO) {
		REF_NO = rEF_NO;
	}
		public List getYEAR() {
		return YEAR;
	}


	public void setYEAR(List yEAR) {
		YEAR = yEAR;
	}


	public List getBANK() {
		return BANK;
	}


	public void setBANK(List bANK) {
		BANK = bANK;
	}


	public List getCHQ_NO() {
		return CHQ_NO;
	}


	public void setCHQ_NO(List cHQ_NO) {
		CHQ_NO = cHQ_NO;
	}


	public List getBOA() {
		return BOA;
	}


	public void setBOA(List bOA) {
		BOA = bOA;
	}


	public List getVCH_NO() {
		return VCH_NO;
	}


	public void setVCH_NO(List vCH_NO) {
		VCH_NO = vCH_NO;
	}


	public List getSTATUS() {
		return STATUS;
	}


	public void setSTATUS(List sTATUS) {
		STATUS = sTATUS;
	}


	public List getSUPPLIER() {
		return SUPPLIER;
	}


	public void setSUPPLIER(List sUPPLIER) {
		SUPPLIER = sUPPLIER;
	}


	public List getCHQ_AMT() {
		return CHQ_AMT;
	}


	public void setCHQ_AMT(List cHQ_AMT) {
		CHQ_AMT = cHQ_AMT;
	}


	public List getSELECT_TYPE() {
		return SELECT_TYPE;
	}


	public void setSELECT_TYPE(List sELECT_TYPE) {
		SELECT_TYPE = sELECT_TYPE;
	}


	public String getTextid() {
		return textid;
	}


	public void setTextid(String textid) {
		this.textid = textid;
	}


	public String getT_BOA() {
		return T_BOA;
	}


	public void setT_BOA(String t_BOA) {
		T_BOA = t_BOA;
	}


	public String getT_VCH_NO() {
		return T_VCH_NO;
	}


	public void setT_VCH_NO(String t_VCH_NO) {
		T_VCH_NO = t_VCH_NO;
	}


	public String getT_STATUS() {
		return T_STATUS;
	}


	public void setT_STATUS(String t_STATUS) {
		T_STATUS = t_STATUS;
	}


	public String getT_SUPPLIER() {
		return T_SUPPLIER;
	}


	public void setT_SUPPLIER(String t_SUPPLIER) {
		T_SUPPLIER = t_SUPPLIER;
	}


	public String getT_CHQ_AMT() {
		return T_CHQ_AMT;
	}


	public void setT_CHQ_AMT(String t_CHQ_AMT) {
		T_CHQ_AMT = t_CHQ_AMT;
	}


	public String getT_STAUS() {
		return T_STAUS;
	}


	public void setT_STAUS(String t_STAUS) {
		T_STAUS = t_STAUS;
	}
	public List getCHK() {
		return CHK;
	}

	public void setCHK(List cHK) {
		CHK = cHK;
	}

	public List<File> getAttachfile() {
		return attachfile;
	}

	public void setAttachfile(List<File> attachfile) {
		this.attachfile = attachfile;
	}

	public List<String> getAttachfileContentType() {
		return attachfileContentType;
	}

	public void setAttachfileContentType(List<String> attachfileContentType) {
		this.attachfileContentType = attachfileContentType;
	}

	public List<String> getAttachfileFileName() {
		return attachfileFileName;
	}

	public void setAttachfileFileName(List<String> attachfileFileName) {
		this.attachfileFileName = attachfileFileName;
	}


	public String getAausrid() {
		return aausrid;
	}


	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}


	public String getTINDEX() {
		return TINDEX;
	}


	public void setTINDEX(String tINDEX) {
		TINDEX = tINDEX;
	}


	public List<GnrtrefnoFbadiciciBeanNM> getGnrtrefnoBlrFbadBeansList() {
		return gnrtrefnoFbadiciciBeansList;
	}


	public void setGnrtrefnoBlrFbadBeansList(
			List<GnrtrefnoFbadiciciBeanNM> gnrtrefnoFbadiciciBeansList) {
		this.gnrtrefnoFbadiciciBeansList = gnrtrefnoFbadiciciBeansList;
	}

	public String getOLD_REF_NO() {
		return OLD_REF_NO;
	}
	public void setOLD_REF_NO(String oLD_REF_NO) {
		OLD_REF_NO = oLD_REF_NO;
	}


	public List getPFSCT() {
		return PFSCT;
	}


	public void setPFSCT(List pFSCT) {
		PFSCT = pFSCT;
	}


	public String getT_PFSCT() {
		return T_PFSCT;
	}


	public void setT_PFSCT(String t_PFSCT) {
		T_PFSCT = t_PFSCT;
	}

	public String getXYEAR() {
		return XYEAR;
	}

	public void setXYEAR(String xYEAR) {
		XYEAR = xYEAR;
	}

	public String getXBANK() {
		return XBANK;
	}

	public void setXBANK(String xBANK) {
		XBANK = xBANK;
	}

	public String getXCHQ_NO() {
		return XCHQ_NO;
	}

	public void setXCHQ_NO(String xCHQ_NO) {
		XCHQ_NO = xCHQ_NO;
	}

	public String getERRORMSG() {
		return ERRORMSG;
	}

	public void setERRORMSG(String eRRORMSG) {
		ERRORMSG = eRRORMSG;
	}
	
}
