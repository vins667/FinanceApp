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
import shahi.Action.compcal.bean.UserBean;
import shahi.Action.compcal.bean.UserListBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserMasterAction extends ActionSupport{
	
	private String aausrid;
	private String SEARCH_EMP;
	private String SLNO;
	private String EMPLOYEE_CODE;
	private String EMPLOYEE_NAME;
	private String STATUS_FLAG;
	private String MODIFIED_BY;
	private String TINDEX;
	
	private List<UserBean> detaillist;
	private List EMP_CODE;
	private List EMP_NAME;
	private List chkdel;
	private List userlist;
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
                System.out.println("UserMasterAction " + e);
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
                System.out.println("UserMasterAction " + e);
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
        	addActionError("Compliance UserMasterAction.execute()"+e);
        	return ERROR;
        }

        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        PreparedStatement stat2=null;
        ResultSet result2=null;
        try{
        	detaillist = new ArrayList<UserBean>();
        	String query=" ";
        	if(SEARCH_EMP!=null && SEARCH_EMP.length()>0){
        		query=" WHERE EMP_CODE LIKE '"+SEARCH_EMP.toUpperCase()+"%'";
        	}
        	stat=conn.prepareStatement("SELECT ID, EMP_CODE, FLAG, USER_ID, TO_CHAR(TDATE,'dd/MM/yyyy') TDATE, MUSER_ID, TO_CHAR(MDATE,'dd/MM/yyyy') MDATE FROM CC_USER_MASTER "+query);
        	result = stat.executeQuery();
        	while(result.next()){
        		stat1=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
        		stat1.setString(1, result.getString("USER_ID"));
        		result1=stat1.executeQuery();
        		String tuser="";
        		if(result1.next()){
        			tuser=result1.getString("FULL_NAME");
        		}
        		stat2=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
        		stat2.setString(1, result.getString("EMP_CODE"));
        		result2=stat2.executeQuery();
        		String wuser="";
        		if(result2.next()){
        			wuser=result2.getString("FULL_NAME");
        		}
        		detaillist.add(new UserBean(result.getLong("ID"), result.getString("EMP_CODE")+"-"+wuser, result.getString("FLAG"), tuser, result.getString("TDATE"), result.getString("MUSER_ID"), result.getString("MDATE")));
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance UserMasterAction.execute()"+se);
        	return ERROR;
        }
        catch(Exception e){
        	addActionError("Compliance UserMasterAction.execute()"+e);
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
        	addActionError("Compliance UserMasterAction.save()"+e);
        	return "newco";
        }

        PreparedStatement stat=null;
        PreparedStatement stats=null;
        PreparedStatement stati=null;
        ResultSet result=null;
        ResultSet results=null;
        try{
	        if(EMP_CODE!=null && EMP_CODE.size()>0){
	        	int ctr=0;
	        	for(int i=0;i<EMP_CODE.size();i++){
	        		if(EMP_CODE.get(i).toString()!=null && EMP_CODE.get(i).toString().length()>0){
	        			stat=conn.prepareStatement("SELECT * FROM CC_USER_MASTER WHERE EMP_CODE=?");
	        			stat.setString(1,EMP_CODE.get(i).toString().trim().toUpperCase());
	        			result=stat.executeQuery();
	        			if(result.next()){
	        				addActionError("User '"+EMP_NAME.get(i).toString().trim().toUpperCase()+"' is already exist.");
	        			}
	        			else{
	        				int id=0;
	        				stats=conn.prepareStatement("SELECT nvl(max(ID),0)+1 ID FROM CC_USER_MASTER");
	        				results=stats.executeQuery();
	        				if(results.next()){
	        					id=results.getInt("ID");
	        				}
		        			stati=conn.prepareStatement("INSERT INTO CC_USER_MASTER (ID, EMP_CODE, FLAG, USER_ID, TDATE, MUSER_ID, MDATE) VALUES (?,?,'Y',?,sysdate,?,sysdate)");
		        			stati.setInt(1, id);
		        			stati.setString(2, EMP_CODE.get(i).toString().trim().toUpperCase());
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
	        		addActionError("Record save Successfully");
	        	}
	        	else{
	        		addActionError("Record not save");
	        	}
	        }
        }
        catch(SQLException se){
        	addActionError("Compliance UserMasterAction.save()"+se);
        	return "newco";
        }
        catch(Exception e){
        	addActionError("Compliance UserMasterAction.save()"+e);
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
        	addActionError("Compliance UserMasterAction.save()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        PreparedStatement stat2=null;
        ResultSet result=null;
        ResultSet result1=null;
        ResultSet result2=null;
        try{
        	stat=conn.prepareStatement("SELECT ID, EMP_CODE, FLAG,MUSER_ID FROM CC_USER_MASTER WHERE ID=?");
        	stat.setString(1,SLNO);
        	result=stat.executeQuery();
			if(result.next()){
				EMPLOYEE_CODE=result.getString("EMP_CODE");
				STATUS_FLAG=result.getString("FLAG");
				stat1=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
        		stat1.setString(1, result.getString("MUSER_ID"));
        		result1=stat1.executeQuery();
        		if(result1.next()){
        			MODIFIED_BY=result1.getString("FULL_NAME");
        		}
        		stat2=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
        		stat2.setString(1, result.getString("EMP_CODE"));
        		result2=stat2.executeQuery();
        		if(result2.next()){
        			EMPLOYEE_NAME=result2.getString("FULL_NAME");
        		}        		
			}
        }
        catch(SQLException se){
        	addActionError("Compliance UserMasterAction.edit()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance UserMasterAction.edit()"+e);
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
        	if(stat1!=null){
        		stat1.close();
        	}
        	if(result1!=null){
        		result1.close();
        	}
        	if(stat2!=null){
        		stat2.close();
        	}
        	if(result2!=null){
        		result2.close();
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
        	addActionError("Compliance UserMasterAction.update()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        try{
        	stat=conn.prepareStatement("SELECT * FROM CC_USER_MASTER WHERE ID<>? AND EMP_CODE=?");
        	stat.setString(1, SLNO);
        	stat.setString(2, EMPLOYEE_CODE.trim().toUpperCase());
        	result=stat.executeQuery();
        	if(result.next()){
        		addActionError("Company Name alredy exist!!! Record Not Updated");
        	}
        	else{
        		stat1=conn.prepareStatement("UPDATE CC_USER_MASTER SET EMP_CODE=?, FLAG=?,MUSER_ID=?,MDATE=SYSDATE WHERE ID=?");
        		stat1.setString(1, EMPLOYEE_CODE.trim().toUpperCase());
        		stat1.setString(2, STATUS_FLAG);
        		stat1.setString(3, usrid);
        		stat1.setString(4, SLNO);
        		int st=stat1.executeUpdate();
        		if(st>0){
        			addActionError("Record Update Successfully!!!");
        		}
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance UserMasterAction.update()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance UserMasterAction.update()"+e);
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
        	addActionError("Compliance UserMasterAction.update()"+e);
        	return SUCCESS;
        }
        PreparedStatement stat=null;
        try{
        	int ctr=0;
        	if(chkdel!=null && chkdel.size()>0){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_USER_MASTER WHERE ID=?");
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
        	addActionError("Compliance UserMasterAction.update()"+se);
        	return SUCCESS;
        }
        catch(Exception e){
        	addActionError("Compliance UserMasterAction.update()"+e);
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
	
	public String usermethod() throws Exception {

        int flag = 0;
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

            if (SEARCH_EMP != null) {
                str =SEARCH_EMP.toUpperCase().trim() + "%";
            }
            userlist=new ArrayList();
            stat1 = con.prepareStatement("select EMP_CODE,FULL_NAME from hrempmst where (EMP_CODE like ? OR FULL_NAME like ?) AND DATE_OF_LEAVING IS NULL");
            stat1.setString(1, str);
            stat1.setString(2, str);
            result1 = stat1.executeQuery();
            while (result1.next()) {
            	userlist.add(new UserListBean(result1.getString(1), result1.getString(2)));
                flag = 1;
            }


        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ee) {
                System.out.print("1 File Name : samplingmasterjavaAction.java" + ee);
                System.out.println(ee.toString());
            }
            System.out.print("2 File Name : samplingmasterjavaAction.java" + e);

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
                System.out.print("File Name : samplingmasterjavaAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }

        if (flag == 0 && str != null) {
            addActionMessage("Record Not Found !!");
        }
        return "userlst";
    }

	public String getAausrid() {
		return aausrid;
	}

	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}

	public String getSEARCH_EMP() {
		return SEARCH_EMP;
	}

	public void setSEARCH_EMP(String sEARCH_EMP) {
		SEARCH_EMP = sEARCH_EMP;
	}

	public String getSLNO() {
		return SLNO;
	}

	public void setSLNO(String sLNO) {
		SLNO = sLNO;
	}

	public String getEMPLOYEE_CODE() {
		return EMPLOYEE_CODE;
	}

	public void setEMPLOYEE_CODE(String eMPLOYEE_CODE) {
		EMPLOYEE_CODE = eMPLOYEE_CODE;
	}

	public String getEMPLOYEE_NAME() {
		return EMPLOYEE_NAME;
	}

	public void setEMPLOYEE_NAME(String eMPLOYEE_NAME) {
		EMPLOYEE_NAME = eMPLOYEE_NAME;
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

	public List<UserBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<UserBean> detaillist) {
		this.detaillist = detaillist;
	}

	public List getEMP_CODE() {
		return EMP_CODE;
	}

	public void setEMP_CODE(List eMP_CODE) {
		EMP_CODE = eMP_CODE;
	}

	public List getEMP_NAME() {
		return EMP_NAME;
	}

	public void setEMP_NAME(List eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}

	public List getChkdel() {
		return chkdel;
	}

	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}

	public List getUserlist() {
		return userlist;
	}

	public void setUserlist(List userlist) {
		this.userlist = userlist;
	}

	public String getTINDEX() {
		return TINDEX;
	}

	public void setTINDEX(String tINDEX) {
		TINDEX = tINDEX;
	}	
}
