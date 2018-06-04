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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CompanyMasterAction extends ActionSupport{
	
	private String aausrid;
	private String SEARCH_COMP;
	private String SLNO;
	private String COMPANY_NAME;
	private String ORDER_NUMBER;
	private String STATUS_FLAG;
	private String MODIFIED_BY;
	private String DCHK;
	
	private List<CompanyBean> detaillist;
	private List COMP_NAME;
	private List ORD_NUMB;
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
                System.out.println("CompanyMasterAction " + e);
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
                System.out.println("CompanyMasterAction " + e);
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
        	addActionError("Compliance CompanyMasterAction.execute()"+e);
        	return ERROR;
        }

        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        try{
        	detaillist = new ArrayList<CompanyBean>();
        	String query=" ";
        	if(SEARCH_COMP!=null && SEARCH_COMP.length()>0){
        		query=" WHERE NAME LIKE '"+SEARCH_COMP.toUpperCase()+"%'";
        	}
        	stat=conn.prepareStatement("SELECT ID, NAME, FLAG, ORDERBY, USER_ID, TO_CHAR(TDATE,'dd/MM/yyyy') TDATE, MUSER_ID, TO_CHAR(MDATE,'dd/MM/yyyy') MDATE FROM CC_COMPANY_MASTER"+query);
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
        		stat1=conn.prepareStatement("SELECT * FROM CC_COMPLIANCE_CALENDER WHERE COMPANY_ID=?");
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
        		detaillist.add(new CompanyBean(result.getLong("ID"), result.getString("NAME"), result.getString("FLAG"), result.getString("ORDERBY"), tuser, result.getString("TDATE"), result.getString("MUSER_ID"), result.getString("MDATE"),exst));
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance CompanyMasterAction.execute()"+se);
        	return ERROR;
        }
        catch(Exception e){
        	addActionError("Compliance CompanyMasterAction.execute()"+e);
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
        	addActionError("Compliance CompanyMasterAction.save()"+e);
        	return "newco";
        }

        PreparedStatement stat=null;
        PreparedStatement stats=null;
        PreparedStatement stati=null;
        ResultSet result=null;
        ResultSet results=null;
        try{
	        if(COMP_NAME!=null && COMP_NAME.size()>0){
	        	int ctr=0;
	        	for(int i=0;i<COMP_NAME.size();i++){
	        		if(COMP_NAME.get(i).toString()!=null && COMP_NAME.get(i).toString().length()>0){
	        			stat=conn.prepareStatement("SELECT * FROM CC_COMPANY_MASTER WHERE NAME=?");
	        			stat.setString(1,COMP_NAME.get(i).toString().trim().toUpperCase());
	        			result=stat.executeQuery();
	        			if(result.next()){
	        				addActionError("Company Name '"+COMP_NAME.get(i).toString().trim().toUpperCase()+"' is already exist.");
	        			}
	        			else{
	        				int id=0;
	        				stats=conn.prepareStatement("SELECT nvl(max(ID),0)+1 ID FROM CC_COMPANY_MASTER");
	        				results=stats.executeQuery();
	        				if(results.next()){
	        					id=results.getInt("ID");
	        				}
		        			stati=conn.prepareStatement("INSERT INTO CC_COMPANY_MASTER (ID, NAME, FLAG, ORDERBY, USER_ID, TDATE, MUSER_ID, MDATE) VALUES (?,?,'Y',?,?,sysdate,?,sysdate)");
		        			stati.setInt(1, id);
		        			stati.setString(2, COMP_NAME.get(i).toString().trim().toUpperCase());
		        			stati.setString(3, ORD_NUMB.get(i).toString());
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
	        		addActionError("Record save Successfully");
	        	}
	        	else{
	        		addActionError("Record not save");
	        	}
	        }
        }
        catch(SQLException se){
        	addActionError("Compliance CompanyMasterAction.save()"+se);
        	return "newco";
        }
        catch(Exception e){
        	addActionError("Compliance CompanyMasterAction.save()"+e);
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
        	addActionError("Compliance CompanyMasterAction.save()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        ResultSet result1=null;
        try{
        	stat=conn.prepareStatement("SELECT ID, NAME, FLAG, ORDERBY,MUSER_ID FROM CC_COMPANY_MASTER WHERE ID=?");
        	stat.setString(1,SLNO);
        	result=stat.executeQuery();
			if(result.next()){
				COMPANY_NAME=result.getString("NAME");
				ORDER_NUMBER=result.getString("ORDERBY");
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
        		stat1=conn.prepareStatement("SELECT * FROM CC_COMPLIANCE_CALENDER WHERE COMPANY_ID=?");
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
        	addActionError("Compliance CompanyMasterAction.edit()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance CompanyMasterAction.edit()"+e);
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
        	addActionError("Compliance CompanyMasterAction.update()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        try{
        	stat=conn.prepareStatement("SELECT * FROM CC_COMPANY_MASTER WHERE ID<>? AND NAME=?");
        	stat.setString(1, SLNO);
        	stat.setString(2, COMPANY_NAME.trim().toUpperCase());
        	result=stat.executeQuery();
        	if(result.next()){
        		addActionError("Company Name alredy exist!!! Record Not Updated");
        	}
        	else{
        		stat1=conn.prepareStatement("UPDATE CC_COMPANY_MASTER SET NAME=?, FLAG=?, ORDERBY=?,MUSER_ID=?,MDATE=SYSDATE WHERE ID=?");
        		stat1.setString(1, COMPANY_NAME.trim().toUpperCase());
        		stat1.setString(2, STATUS_FLAG);
        		stat1.setString(3, ORDER_NUMBER);
        		stat1.setString(4, usrid);
        		stat1.setString(5, SLNO);
        		int st=stat1.executeUpdate();
        		if(st>0){
        			addActionError("Record Update Successfully!!!");
        		}
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance CompanyMasterAction.update()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance CompanyMasterAction.update()"+e);
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
        	addActionError("Compliance CompanyMasterAction.update()"+e);
        	return SUCCESS;
        }
        PreparedStatement stat=null;
        try{
        	int ctr=0;
        	if(chkdel!=null && chkdel.size()>0){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_COMPANY_MASTER WHERE ID=?");
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
        	addActionError("Compliance CompanyMasterAction.update()"+se);
        	return SUCCESS;
        }
        catch(Exception e){
        	addActionError("Compliance CompanyMasterAction.update()"+e);
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

	public String getSEARCH_COMP() {
		return SEARCH_COMP;
	}

	public void setSEARCH_COMP(String sEARCH_COMP) {
		SEARCH_COMP = sEARCH_COMP;
	}

	public List<CompanyBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<CompanyBean> detaillist) {
		this.detaillist = detaillist;
	}

	public List getCOMP_NAME() {
		return COMP_NAME;
	}

	public void setCOMP_NAME(List cOMP_NAME) {
		COMP_NAME = cOMP_NAME;
	}

	public List getORD_NUMB() {
		return ORD_NUMB;
	}

	public void setORD_NUMB(List oRD_NUMB) {
		ORD_NUMB = oRD_NUMB;
	}

	public String getSLNO() {
		return SLNO;
	}

	public void setSLNO(String sLNO) {
		SLNO = sLNO;
	}

	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}

	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}

	public String getORDER_NUMBER() {
		return ORDER_NUMBER;
	}

	public void setORDER_NUMBER(String oRDER_NUMBER) {
		ORDER_NUMBER = oRDER_NUMBER;
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

	public List getChkdel() {
		return chkdel;
	}

	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}

	public String getDCHK() {
		return DCHK;
	}

	public void setDFLAG(String dCHK) {
		DCHK = dCHK;
	}
	
}
