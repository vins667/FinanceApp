package shahi.Action.compcal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHris;
import shahi.Action.compcal.bean.CompanyBean;
import shahi.Action.compcal.bean.DeptBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeptMasterAction extends ActionSupport{
	
	private String aausrid;
	private String SEARCH_DEPT;
	private String SLNO;
	private String DEPARTMENT_NAME;
	private String STATUS_FLAG;
	private String MODIFIED_BY;
	private String DCHK;
	
	private List<DeptBean> detaillist;
	private List DEPT_NAME;
	private List chkdel;
	//Session
	
	public String mksess() throws SQLException, ParseException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        Connection conn = null;
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
                System.out.println("DeptMasterAction " + e);
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
                System.out.println("DeptMasterAction " + e);
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }     
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return ERROR;
        }
        chkdel=null;
        Connection conn=null;
        Connection conn1=null;
        try{
        	conn=new connection().getConnection();
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.execute()"+e);
        	return ERROR;
        }

        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        try{
        	detaillist = new ArrayList<DeptBean>();
        	String query=" ";
        	if(SEARCH_DEPT!=null && SEARCH_DEPT.length()>0){
        		query=" WHERE NAME LIKE '"+SEARCH_DEPT.toUpperCase()+"%'";
        	}
        	stat=conn.prepareStatement("SELECT ID, NAME, FLAG, USER_ID, TO_CHAR(TDATE,'dd/MM/yyyy') TDATE, MUSER_ID, TO_CHAR(MDATE,'dd/MM/yyyy') MDATE FROM CC_DEPT_MASTER "+query);
        	result = stat.executeQuery();
        	while(result.next()){
        		stat1=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
        		stat1.setString(1, result.getString("USER_ID"));
        		result1=stat1.executeQuery();
        		String tuser="";
        		if(result1.next()){
        			tuser=result1.getString("FULL_NAME");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
        		String exst=null;
        		stat1=conn.prepareStatement("SELECT * FROM CC_SUB_DEPT_MASTER WHERE DEPT_ID=?");
        		stat1.setString(1, result.getString("ID"));
        		result1=stat1.executeQuery();
        		if(result1.next()){
        			exst="OK";
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
        		detaillist.add(new DeptBean(result.getLong("ID"), result.getString("NAME"), result.getString("FLAG"), tuser, result.getString("TDATE"), result.getString("MUSER_ID"), result.getString("MDATE"),exst));
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance DeptMasterAction.execute()"+se);
        	return ERROR;
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.execute()"+e);
        	return ERROR;
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(conn1!=null){
        		conn1.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(stat1!=null){
        		stat1.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        	if(result1!=null){
        		result1.close();
        	}
        }
		return SUCCESS;
	}
	
	public String newco(){
		return "newco";
	}
	
	public String save() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
        Connection conn=null;
        try{
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.save()"+e);
        	return "newco";
        }

        PreparedStatement stat=null;
        PreparedStatement stats=null;
        PreparedStatement stati=null;
        ResultSet result=null;
        ResultSet results=null;
        try{
	        if(DEPT_NAME!=null && DEPT_NAME.size()>0){
	        	int ctr=0;
	        	for(int i=0;i<DEPT_NAME.size();i++){
	        		if(DEPT_NAME.get(i).toString()!=null && DEPT_NAME.get(i).toString().length()>0){
	        			stat=conn.prepareStatement("SELECT * FROM CC_DEPT_MASTER WHERE NAME=?");
	        			stat.setString(1,DEPT_NAME.get(i).toString().trim().toUpperCase());
	        			result=stat.executeQuery();
	        			if(result.next()){
	        				addActionError("Department Name '"+DEPT_NAME.get(i).toString().trim().toUpperCase()+"' is already exist.");
	        			}
	        			else{
	        				int id=0;
	        				stats=conn.prepareStatement("SELECT nvl(max(ID),0)+1 ID FROM CC_DEPT_MASTER");
	        				results=stats.executeQuery();
	        				if(results.next()){
	        					id=results.getInt("ID");
	        				}
		        			stati=conn.prepareStatement("INSERT INTO CC_DEPT_MASTER (ID, NAME, FLAG, USER_ID, TDATE, MUSER_ID, MDATE) VALUES (?,?,'Y',?,sysdate,?,sysdate)");
		        			stati.setInt(1, id);
		        			stati.setString(2, DEPT_NAME.get(i).toString().trim().toUpperCase());
		        			stati.setString(3, usrid);
		        			stati.setString(4,usrid);
		        			int st=stati.executeUpdate();
		        			if(st>0){
		        				++ctr;
		        			}
		        			if(stati!=null){
		                		stati.close();
		                	}
		        			
		        			if(stats!=null){
		        				stats.close();
		        			}
		        			if(results!=null){
		        				results.close();
		        			}
	        			}
	        			if(stat!=null){
	        				stat.close();
	        			}
	        			if(result!=null){
	        				result.close();
	        			}
	        		}
	        	}
	        	if(ctr>0){
	        		addActionError("Record saved Successfully");
	        	}
	        	else{
	        		addActionError("Record not saved");
	        	}
	        }
        }
        catch(SQLException se){
        	addActionError("Compliance DeptMasterAction.save()"+se);
        	return "newco";
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.save()"+e);
        	return "newco";
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(stati!=null){
        		stati.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        }
		return "newco";
	}
	
	public String edit() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "editco";
        }
        Connection conn=null;
        Connection conn1=null;
        try{
        	conn=new connection().getConnection();
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.save()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        ResultSet result1=null;
        try{
        	stat=conn.prepareStatement("SELECT ID, NAME, FLAG,MUSER_ID FROM CC_DEPT_MASTER WHERE ID=?");
        	stat.setString(1,SLNO);
        	result=stat.executeQuery();
			if(result.next()){
				DEPARTMENT_NAME=result.getString("NAME");
				STATUS_FLAG=result.getString("FLAG");
				stat1=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
        		stat1.setString(1, result.getString("MUSER_ID"));
        		result1=stat1.executeQuery();
        		if(result1.next()){
        			MODIFIED_BY=result1.getString("FULL_NAME");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
        		String exst=null;
        		stat1=conn.prepareStatement("SELECT * FROM CC_SUB_DEPT_MASTER WHERE DEPT_ID=?");
        		stat1.setString(1, result.getString("ID"));
        		result1=stat1.executeQuery();
        		if(result1.next()){
        			DCHK="OK";
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
			}
        }
        catch(SQLException se){
        	addActionError("Compliance DeptMasterAction.edit()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.edit()"+e);
        	return "editco";
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(conn1!=null){
        		conn1.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        }
		return "editco";
	}
	public String update() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "editco";
        }
        Connection conn=null;
        try{
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.update()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        try{
        	stat=conn.prepareStatement("SELECT * FROM CC_DEPT_MASTER WHERE ID<>? AND NAME=?");
        	stat.setString(1, SLNO);
        	stat.setString(2, DEPARTMENT_NAME.trim().toUpperCase());
        	result=stat.executeQuery();
        	if(result.next()){
        		addActionError("Company Name alredy exist!!! Record Not Updated");
        	}
        	else{
        		stat1=conn.prepareStatement("UPDATE CC_DEPT_MASTER SET NAME=?, FLAG=?,MUSER_ID=?,MDATE=SYSDATE WHERE ID=?");
        		stat1.setString(1, DEPARTMENT_NAME.trim().toUpperCase());
        		stat1.setString(2, STATUS_FLAG);
        		stat1.setString(3, usrid);
        		stat1.setString(4, SLNO);
        		int st=stat1.executeUpdate();
        		if(st>0){
        			addActionError("Record Updated Successfully!!!");
        		}
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance DeptMasterAction.update()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.update()"+e);
        	return "editco";
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(stat1!=null){
        		stat1.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        }
        edit();
		return "editco";
	}
	
	public String delete() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return SUCCESS;
        }
        Connection conn=null;
        try{
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.update()"+e);
        	return SUCCESS;
        }
        PreparedStatement stat=null;
        try{
        	int ctr=0;
        	if(chkdel!=null && chkdel.size()>0){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_DEPT_MASTER WHERE ID=?");
        			stat.setString(1, chkdel.get(i).toString());
        			int st=stat.executeUpdate();
        			if(st>0){
        				++ctr;
        			}
        		}
        		if(ctr>0){
        			addActionError("Record Deleted Successfully");
        		}
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance DeptMasterAction.update()"+se);
        	return SUCCESS;
        }
        catch(Exception e){
        	addActionError("Compliance DeptMasterAction.update()"+e);
        	return SUCCESS;
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        }
        return SUCCESS;
	}

	public String getAausrid() {
		return aausrid;
	}

	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}

	public String getSEARCH_DEPT() {
		return SEARCH_DEPT;
	}

	public void setSEARCH_DEPT(String sEARCH_DEPT) {
		SEARCH_DEPT = sEARCH_DEPT;
	}

	public String getSLNO() {
		return SLNO;
	}

	public void setSLNO(String sLNO) {
		SLNO = sLNO;
	}

	public String getDEPARTMENT_NAME() {
		return DEPARTMENT_NAME;
	}

	public void setDEPARTMENT_NAME(String dEPARTMENT_NAME) {
		DEPARTMENT_NAME = dEPARTMENT_NAME;
	}

	public String getSTATUS_FLAG() {
		return STATUS_FLAG;
	}

	public void setSTATUS_FLAG(String sTATUS_FLAG) {
		STATUS_FLAG = sTATUS_FLAG;
	}

	public String getMODIFIED_BY() {
		return MODIFIED_BY;
	}

	public void setMODIFIED_BY(String mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}

	public List<DeptBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<DeptBean> detaillist) {
		this.detaillist = detaillist;
	}

	public List getDEPT_NAME() {
		return DEPT_NAME;
	}

	public void setDEPT_NAME(List dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}

	public List getChkdel() {
		return chkdel;
	}

	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}

	public String getDCHK() {
		return DCHK;
	}

	public void setDCHK(String dCHK) {
		DCHK = dCHK;
	}	
	
	
}
