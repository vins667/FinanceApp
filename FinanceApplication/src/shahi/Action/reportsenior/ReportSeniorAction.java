package shahi.Action.reportsenior;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shahi.Action.Sampling.Beans.sampletypemasterBean;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHris;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReportSeniorAction  extends ActionSupport {	
	private static final long serialVersionUID = 1L;
	private String aausrid;
	private String REPORT_CODE;
	private String OLD_REPORT_CODE;
	private String REPORT_NAME;
	private String USER;
	private String SEARCH_USERCODE;
	private List USER_LIST;
	/**
	 * Method make session
	 * @return SUCCESS
	 * @throws Exception 
	 */
	public String makesession() throws Exception{
        Map<String, Object> session = ActionContext.getContext().getSession();        
        Connection conn=null;
        PreparedStatement stat = null;
        ResultSet result = null;
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
            System.out.println("ReportSeniorAction " + e);
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
        execute();
        return SUCCESS;
    }
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		REPORT_CODE = null;
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String deptcode = (String) session.get("sessDeptCode");
        if(usrid==null && deptcode==null){
            addActionError("Session is not Available");
            return ERROR;
        }
        if(usrid!=null){
        	Connection conn=null;
            PreparedStatement stat = null;
            ResultSet result = null;
            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            try {
                conn = new ConnectionShahiHris().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        	try {
				stat = conn.prepareStatement("SELECT EMP_CODE ||' - '||INITCAP(FULL_NAME) USER_NAME FROM HREMPMST WHERE EMP_CODE=?");
				stat.setString(1, usrid);
				result = stat.executeQuery();
				if(result.next()){
					USER=result.getString("USER_NAME");
				}
				stat1 = conn.prepareStatement("SELECT A.FUN_CODE USER_CODE,B.EMP_CODE ||' - '||INITCAP(B.FULL_NAME) USER_NAME FROM HREMPDTL A,HREMPMST B WHERE A.FUN_CODE=B.EMP_CODE AND A.END_DATE IS NULL AND A.EMP_CODE=?");
				stat1.setString(1, usrid);
				result1 = stat1.executeQuery();
				if(result1.next()){
					OLD_REPORT_CODE=result1.getString("USER_CODE");
					REPORT_NAME=result1.getString("USER_NAME");
				}
			} catch (Exception e) {
				System.out.println("ReportSeniorAction " + e);
			}
        	finally{
        		if(conn!=null){
        			conn.close();
        		}
        		conn = null;
        		
        		if(stat!=null){
        			stat.close();
        		}
        		stat = null;
        		
        		if(result!=null){
        			result.close();
        		}
        		result = null;
        		
        		if(stat1!=null){
        			stat1.close();
        		}
        		stat1 = null;
        		
        		if(result1!=null){
        			result1.close();
        		}
        		result1 = null;
        	}
        }
        return SUCCESS;
	}
	
	public String user() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String deptcode = (String) session.get("sessDeptCode");
        if(usrid==null && deptcode==null){
            addActionError("Session is not Available");
            return ERROR;
        }
		USER_LIST = new ArrayList();				
		int flag=0;
        Connection con = null;
        try {
            con = new ConnectionShahiHris().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        String str = null;
        try {

            if (SEARCH_USERCODE != null) {
                str =SEARCH_USERCODE.toUpperCase().trim() + "%";
            }

            stat1 = con.prepareStatement("SELECT EMP_CODE,EMP_NAME FULL_NAME FROM EMPINFVIEW WHERE EMP_CODE LIKE ? OR EMP_NAME LIKE ? AND EMP_CODE<>? ORDER BY 2");
            stat1.setString(1, str);
            stat1.setString(2, str);
            stat1.setString(3, usrid);
            result1 = stat1.executeQuery();
            while (result1.next()) {
            	USER_LIST.add(new sampletypemasterBean(result1.getString(1), result1.getString(1) + " - " + result1.getString(2)));
            	flag=1;
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : ReportSeniorAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : ReportSeniorAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (con != null) {
                    con.close();
                }                
                result1 = null;
                stat1 = null;
                con = null;


            } catch (Exception e) {
                System.out.print("File Name : ReportSeniorAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }

        if (flag == 0 && str != null) {
            addActionError("Record Not Found !!");
        }
        return "user";
    }
	public String save() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String deptcode = (String) session.get("sessDeptCode");
        if(usrid==null && deptcode==null){
            addActionError("Session is not Available");
            return ERROR;
        }
        if(REPORT_CODE!=null && !REPORT_CODE.equals(OLD_REPORT_CODE)){
        	Connection conn=null;
        	Connection connhris=null;
            PreparedStatement stat = null;            
            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
                connhris = new ConnectionShahiHris().getConnection();
                connhris.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        	try {
        		int status=0;
				stat = conn.prepareStatement("UPDATE EMP_DTLS_INF SET MGR_CODE=?,FUN_CODE=? WHERE EMP_CODE=?");
				stat.setString(1, REPORT_CODE);
				stat.setString(2, REPORT_CODE);
				stat.setString(3, usrid);
				status = stat.executeUpdate();
				if(status>0){
					status=0;
					stat1 = connhris.prepareStatement("UPDATE HREMPDTL SET END_DATE=SYSDATE WHERE EMP_CODE=? AND END_DATE IS NULL");
					stat1.setString(1, usrid);
					status=stat1.executeUpdate();
					if(status>0){
						status=0;
						stat2=connhris.prepareStatement("INSERT INTO HREMPDTL SELECT EMP_CODE,TRIM(SYSDATE),DESIG_CODE,SUB_DEPT_CODE,UNIT_CODE,?,?,EMP_STATUS,PCH_CODE,COMPANY_CODE,COMP_EMP_CODE,NULL,?,SYSDATE,SYSDATE,PF_NO,PF_JOIN_DATE,ESI_NO,ESI_DISP,STRU_CODE,GROSS_AMT,CTC_AMT FROM HREMPDTL WHERE EMP_CODE=? AND END_DATE=(SELECT MAX(END_DATE) FROM HREMPDTL WHERE EMP_CODE=?)");
						stat2.setString(1, REPORT_CODE);
						stat2.setString(2, REPORT_CODE);
						stat2.setString(3, usrid);
						stat2.setString(4, usrid);
						stat2.setString(5, usrid);
						status=stat2.executeUpdate();
						if(status>0){
							conn.commit();
							connhris.commit();
							addActionError("Record Updated Successfully");
						}
						else{
							conn.rollback();
							connhris.rollback();
							addActionError("Record Not Updated");
						}
					}
					else{
						conn.rollback();
						connhris.rollback();
						addActionError("Record Not Updated");
					}					
				}
				else{
					conn.rollback();
					connhris.rollback();
					addActionError("Record Not Updated");
				}
        	} catch (Exception e) {
        		conn.rollback();
				connhris.rollback();
				addActionError("Record Not Updated");
				System.out.println("ReportSeniorAction " + e);
			}
        	finally{        		
        		if(conn!=null){
        			conn.close();
        		}
        		conn = null;
        		
        		if(connhris!=null){
        			connhris.close();
        		}
        		connhris = null;
        		
        		if(stat!=null){
        			stat.close();
        		}
        		stat = null;
        		
        		if(stat1!=null){
        			stat1.close();
        		}
        		stat1 = null;
        		
        		if(stat2!=null){
        			stat2.close();
        		}
        		stat2 = null;
        	}        	
        }
        execute();
		return SUCCESS;
	}
	public String getAausrid() {
		return aausrid;
	}
	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}
	public String getREPORT_CODE() {
		return REPORT_CODE;
	}
	public void setREPORT_CODE(String rEPORT_CODE) {
		REPORT_CODE = rEPORT_CODE;
	}
	public String getREPORT_NAME() {
		return REPORT_NAME;
	}
	public void setREPORT_NAME(String rEPORT_NAME) {
		REPORT_NAME = rEPORT_NAME;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getSEARCH_USERCODE() {
		return SEARCH_USERCODE;
	}
	public void setSEARCH_USERCODE(String sEARCH_USERCODE) {
		SEARCH_USERCODE = sEARCH_USERCODE;
	}
	public List getUSER_LIST() {
		return USER_LIST;
	}
	public void setUSER_LIST(List uSER_LIST) {
		USER_LIST = uSER_LIST;
	}

}
