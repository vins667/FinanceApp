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
import shahi.Action.compcal.bean.SubDeptBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SubDeptMasterAction extends ActionSupport{
	
	private String aausrid;
	private String SEARCH_SUBDEPT;
	private String SLNO;
	private String SUBDEPARTMENT_NAME;
	private String DEPARTMENT_ID;
	private String STATUS_FLAG;
	private String MODIFIED_BY;
	private String DCHK;
	
	private List<SubDeptBean> detaillist;
	private List SUBDEPT_NAME;
	private List DEPT_ID;
	private List chkdel;
	private List<DeptBean> deptlist;
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

	
        public void getdept(String flg) throws SQLException{
		deptlist=new ArrayList<DeptBean>();
        Connection conn=null;
        try
        {
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance SubDeptMasterAction.execute()"+e);
        }
        PreparedStatement stat=null;
        ResultSet result=null;
        try{
        	if(flg!=null){
        		stat=conn.prepareStatement("SELECT ID,NAME FROM CC_DEPT_MASTER WHERE (ID='"+flg+"' OR FLAG='Y')");
        	}
        	else{
        		stat=conn.prepareStatement("SELECT ID,NAME FROM CC_DEPT_MASTER WHERE FLAG='Y'");
        	}
        	result = stat.executeQuery();
        	while(result.next()){
        		DeptBean bean=new DeptBean();
        		bean.setID(result.getLong("ID"));
        		bean.setNAME(result.getString("NAME"));
        		deptlist.add(bean);
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance SubDeptMasterAction.execute()"+se);
        }
        catch(Exception e){
        	addActionError("Compliance SubDeptMasterAction.execute()"+e);
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        }
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
        	addActionError("Compliance SubDeptMasterAction.execute()"+e);
        	return ERROR;
        }

        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        PreparedStatement stat2=null;
        ResultSet result2=null;
        try{
        	detaillist = new ArrayList<SubDeptBean>();
        	String query=" ";
        	if(SEARCH_SUBDEPT!=null && SEARCH_SUBDEPT.length()>0){
        		query=" WHERE NAME LIKE '"+SEARCH_SUBDEPT.toUpperCase()+"%'";
        	}
        	stat=conn.prepareStatement("SELECT ID,DEPT_ID,NAME, FLAG, USER_ID, TO_CHAR(TDATE,'dd/MM/yyyy') TDATE, MUSER_ID, TO_CHAR(MDATE,'dd/MM/yyyy') MDATE FROM CC_SUB_DEPT_MASTER "+query);
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
        		stat1=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE SUBDEPT_ID=?");
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
        		
        		stat2=conn.prepareStatement("SELECT NAME FROM CC_DEPT_MASTER WHERE ID=?");
        		stat2.setString(1, result.getString("DEPT_ID"));
        		result2=stat2.executeQuery();
        		String tdept="";
        		if(result2.next()){
        			tdept=result2.getString("NAME");
        		}
        		if(stat2!=null){
            		stat2.close();
            	}
        		if(result2!=null){
            		result2.close();
            	}
        		
        		detaillist.add(new SubDeptBean(result.getLong("ID"),result.getLong("DEPT_ID"),tdept, result.getString("NAME"), result.getString("FLAG"), tuser, result.getString("TDATE"), result.getString("MUSER_ID"), result.getString("MDATE"),exst));
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance SubDeptMasterAction.execute()"+se);
        	return ERROR;
        }
        catch(Exception e){
        	addActionError("Compliance SubDeptMasterAction.execute()"+e);
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
	
	public String newco() throws SQLException{
		getdept(null);
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
        	addActionError("Compliance SubDeptMasterAction.save()"+e);
        	return "newco";
        }

        PreparedStatement stat=null;
        PreparedStatement stats=null;
        PreparedStatement stati=null;
        ResultSet result=null;
        ResultSet results=null;
        try{
	        if(SUBDEPT_NAME!=null && SUBDEPT_NAME.size()>0){
	        	int ctr=0;
	        	for(int i=0;i<SUBDEPT_NAME.size();i++){
	        		if(SUBDEPT_NAME.get(i).toString()!=null && SUBDEPT_NAME.get(i).toString().length()>0){
	        			stat=conn.prepareStatement("SELECT * FROM CC_SUB_DEPT_MASTER WHERE NAME=? AND DEPT_ID=?");
	        			stat.setString(1,SUBDEPT_NAME.get(i).toString().trim().toUpperCase());
	        			stat.setString(2,DEPT_ID.get(i).toString().trim().toUpperCase());
	        			result=stat.executeQuery();
	        			if(result.next()){
	        				addActionError("Sub Department Name '"+SUBDEPT_NAME.get(i).toString().trim().toUpperCase()+"' is already exist.");
	        			}
	        			else{
	        				int id=0;
	        				stats=conn.prepareStatement("SELECT nvl(max(ID),0)+1 ID FROM CC_SUB_DEPT_MASTER");
	        				results=stats.executeQuery();
	        				if(results.next()){
	        					id=results.getInt("ID");
	        				}
		        			stati=conn.prepareStatement("INSERT INTO CC_SUB_DEPT_MASTER (ID,DEPT_ID, NAME, FLAG, USER_ID, TDATE, MUSER_ID, MDATE) VALUES (?,?,?,'Y',?,sysdate,?,sysdate)");
		        			stati.setInt(1, id);
		        			stati.setString(2, DEPT_ID.get(i).toString().trim());
		        			stati.setString(3, SUBDEPT_NAME.get(i).toString().trim().toUpperCase());		        			
		        			stati.setString(4, usrid);
		        			stati.setString(5,usrid);
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
        	addActionError("Compliance SubDeptMasterAction.save()"+se);
        	return "newco";
        }
        catch(Exception e){
        	addActionError("Compliance SubDeptMasterAction.save()"+e);
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
        getdept(null);
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
        	addActionError("Compliance SubDeptMasterAction.save()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        ResultSet result1=null;
        try{
        	stat=conn.prepareStatement("SELECT ID,DEPT_ID, NAME, FLAG,MUSER_ID FROM CC_SUB_DEPT_MASTER WHERE ID=?");
        	stat.setString(1,SLNO);
        	result=stat.executeQuery();
			if(result.next()){
				SUBDEPARTMENT_NAME=result.getString("NAME");
				DEPARTMENT_ID=result.getString("DEPT_ID");
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
        		stat1=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE SUBDEPT_ID=?");
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
        		getdept(result.getString("DEPT_ID"));
			}
        }
        catch(SQLException se){
        	addActionError("Compliance SubDeptMasterAction.edit()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance SubDeptMasterAction.edit()"+e);
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
        	addActionError("Compliance SubDeptMasterAction.update()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        try{
        	stat=conn.prepareStatement("SELECT * FROM CC_SUB_DEPT_MASTER WHERE ID<>? AND NAME=? AND DEPT_ID=?");
        	stat.setString(1, SLNO);
        	stat.setString(2, SUBDEPARTMENT_NAME.trim().toUpperCase());
        	stat.setString(3, DEPARTMENT_ID);
        	result=stat.executeQuery();
        	if(result.next()){
        		addActionError("Company Name alredy exist!!! Record Not Updated");
        	}
        	else{
        		stat1=conn.prepareStatement("UPDATE CC_SUB_DEPT_MASTER SET NAME=?,DEPT_ID=?, FLAG=?,MUSER_ID=?,MDATE=SYSDATE WHERE ID=?");
        		stat1.setString(1, SUBDEPARTMENT_NAME.trim().toUpperCase());
        		stat1.setString(2, DEPARTMENT_ID);
        		stat1.setString(3, STATUS_FLAG);
        		stat1.setString(4, usrid);
        		stat1.setString(5, SLNO);
        		int st=stat1.executeUpdate();
        		if(st>0){
        			addActionError("Record Updated Successfully!!!");
        		}
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance SubDeptMasterAction.update()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance SubDeptMasterAction.update()"+e);
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
        	addActionError("Compliance SubDeptMasterAction.update()"+e);
        	return SUCCESS;
        }
        PreparedStatement stat=null;
        try{
        	int ctr=0;
        	if(chkdel!=null && chkdel.size()>0){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_SUB_DEPT_MASTER WHERE ID=?");
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
        	addActionError("Compliance SubDeptMasterAction.update()"+se);
        	return SUCCESS;
        }
        catch(Exception e){
        	addActionError("Compliance SubDeptMasterAction.update()"+e);
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

	public String getSEARCH_SUBDEPT() {
		return SEARCH_SUBDEPT;
	}

	public void setSEARCH_SUBDEPT(String sEARCH_SUBDEPT) {
		SEARCH_SUBDEPT = sEARCH_SUBDEPT;
	}

	public String getSLNO() {
		return SLNO;
	}

	public void setSLNO(String sLNO) {
		SLNO = sLNO;
	}

	public String getSUBDEPARTMENT_NAME() {
		return SUBDEPARTMENT_NAME;
	}

	public void setSUBDEPARTMENT_NAME(String sUBDEPARTMENT_NAME) {
		SUBDEPARTMENT_NAME = sUBDEPARTMENT_NAME;
	}

	public String getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}

	public void setDEPARTMENT_ID(String dEPARTMENT_ID) {
		DEPARTMENT_ID = dEPARTMENT_ID;
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

	public List<SubDeptBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<SubDeptBean> detaillist) {
		this.detaillist = detaillist;
	}

	public List getSUBDEPT_NAME() {
		return SUBDEPT_NAME;
	}

	public void setSUBDEPT_NAME(List sUBDEPT_NAME) {
		SUBDEPT_NAME = sUBDEPT_NAME;
	}

	public List getDEPT_ID() {
		return DEPT_ID;
	}

	public void setDEPT_ID(List dEPT_ID) {
		DEPT_ID = dEPT_ID;
	}

	public List getChkdel() {
		return chkdel;
	}

	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}

	public List<DeptBean> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<DeptBean> deptlist) {
		this.deptlist = deptlist;
	}

	public String getDCHK() {
		return DCHK;
	}

	public void setDCHK(String dCHK) {
		DCHK = dCHK;
	}
	
}
