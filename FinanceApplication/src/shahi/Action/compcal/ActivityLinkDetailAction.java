package shahi.Action.compcal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shahi.Action.compcal.bean.ActivityBean;
import shahi.Action.compcal.bean.AlertBean;
import shahi.Action.compcal.bean.CompanyBean;
import shahi.Action.compcal.bean.UpdFlagBean;
import shahi.Action.compcal.bean.UserLinkBean;
import shahi.Action.compcal.bean.UserLinkDetailLoctBean;
import shahi.Action.compcal.bean.YearBean;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHris;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ActivityLinkDetailAction  extends ActionSupport{
	private String aausrid;
	private String deptcode;
	
	private List<UserLinkDetailLoctBean> locationcodelist;
	private List<UserLinkDetailLoctBean> deptcodelist;
	private List<UserLinkDetailLoctBean> subdeptcodelist;
	
	private List<UpdFlagBean> ulocationcodelist;
	private List<UpdFlagBean> udeptcodelist;
	private List<UpdFlagBean> usubdeptcodelist;
	private List<YearBean> yearBeans;
	
	private String ACTIVITY_ID;
	private String ACTIVITY_NAME;
	private String ALERT_TYPE1;
	private String ALERT_TYPE2;
	private String ALERT_TYPE3;
	private List LOCATION;
	private List DEPARTMENT;
	private List SUBDEPARTMENT;
	private List ALERT_TYPE;
	private List ALERT_DAY;
	private List EMP_CODE;
	private List EMP_NAME;
	private List EMP_TYPE;
	private String UPD_FLAG;
	
	private List chkdel;
	private List<ActivityBean> detaillist;
	private List<AlertBean> actlist;
	private String SEARCH_ACTIVITY;
	private String SEARCH_LOCT;
	private String SEARCH_DEPT;
	private String SEARCH_SUBDEPT;
	private List<UserLinkBean> userlinklist;
	
	private String E_ACTIVITY_NAME;
	private String E_ALERT_TYPE1;
	private String E_ALERT_TYPE2;
	private String E_ALERT_TYPE3;
	private String E_DEPARTMENT;
	private String E_SUBDEPARTMENT;
	private String E_LOCATION;
	private String E_SLNO;
	private String U_SLNO;
	private String E_ACTIVITY_ID;
	private String MODIFIED_BY;
	private String STATUS_FLAG;	
	private String ALERT_TYPE_TAB;	
	private String E_ALERT_TYPE;
	private List ALERT_TYPE_DAY;
	
	private List<String> YEAR_CAL;
	private List<String> YEAR_STATUS;
        private List userlist=new ArrayList();
        private List ACC_ID;
        private List ACC_FLAG;
        private List ACC_TYPE;
        private List emplist;
        private String ACC_TYPE_USR; 
        private List delchkusr;
        
        
        private String COPY_ACTIVITY_ID;
        private String SEARCH_STATUS;
	
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
        	addActionError("Compliance ActivityLinkDetailAction.execute()"+e);
        	return ERROR;
        }

        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        try{
        	detaillist = new ArrayList<ActivityBean>();
        	String query=" WHERE 1=1 ";
        	if(SEARCH_ACTIVITY!=null && SEARCH_ACTIVITY.length()>0){
        		query+=" AND ACTIVITY_NAME LIKE '"+SEARCH_ACTIVITY.toUpperCase()+"%'";
        	}
        	if(SEARCH_LOCT!=null && SEARCH_LOCT.length()>0){
        		query+=" AND LOCATION_CODE LIKE '"+SEARCH_LOCT.toUpperCase()+"%'";
        	}
        	if(SEARCH_DEPT!=null && SEARCH_DEPT.length()>0){
        		query+=" AND DEPT_ID LIKE '"+SEARCH_DEPT.toUpperCase()+"%'";
        	}
        	if(SEARCH_SUBDEPT!=null && SEARCH_SUBDEPT.length()>0){
        		query+=" AND SUBDEPT_ID LIKE '"+SEARCH_SUBDEPT.toUpperCase()+"%'";
        	}
        	
        	if(SEARCH_STATUS!=null && SEARCH_STATUS.length()>0){
        		query+=" AND FLAG='"+SEARCH_STATUS.toUpperCase()+"'";
        	}
        	
        	stat=conn.prepareStatement("SELECT ID,ACTIVITY_ID, ACTIVITY_NAME,LOCATION_CODE,DEPT_ID,SUBDEPT_ID,ALERT_TYPE1,ALERT_TYPE2,ALERT_TYPE3,FLAG,USER_ID,"
        			+" TO_CHAR(TDATE,'dd/MM/yyyy') TDATE,MUSER_ID,TO_CHAR(MDATE,'dd/MM/yyyy') MDATE,UPD_FLAG,CAL_FLAG FROM CC_ACTIVITY_MASTER "+query+" ORDER BY LOCATION_CODE,ACTIVITY_NAME");
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
        		
        		stat1=conn.prepareStatement("SELECT NAME FROM CC_DEPT_MASTER WHERE ID=?");
        		stat1.setString(1, result.getString("DEPT_ID"));
        		result1=stat1.executeQuery();
        		String deptuser="";
        		if(result1.next()){
        			deptuser=result1.getString("NAME");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
        		
        		stat1=conn.prepareStatement("SELECT NAME FROM CC_SUB_DEPT_MASTER WHERE ID=?");
        		stat1.setString(1, result.getString("SUBDEPT_ID"));
        		result1=stat1.executeQuery();
        		String subdeptuser="";
        		if(result1.next()){
        			subdeptuser=result1.getString("NAME");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(result1!=null){
        			result1.close();
        		}
        		
        		detaillist.add(new ActivityBean(result.getLong("ID"),result.getLong("ACTIVITY_ID"),result.getString("ACTIVITY_NAME"),result.getString("LOCATION_CODE"),result.getString("DEPT_ID"),deptuser,result.getString("SUBDEPT_ID"),subdeptuser,result.getString("ALERT_TYPE1"),result.getString("ALERT_TYPE2"),result.getString("ALERT_TYPE3"),result.getString("FLAG"),tuser,result.getString("TDATE"),result.getString("MUSER_ID"),result.getString("MDATE"),result.getString("UPD_FLAG"),result.getString("CAL_FLAG")));
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.execute()"+se);
        	return ERROR;
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.execute()"+e);
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
	
	public String year() throws SQLException{
		Connection conn=null;
        try{
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.year()"+e);
        	return "yearco";
        }
       
        chkdel=null;
        YEAR_CAL=null;
        YEAR_STATUS=null;
        yearBeans=new ArrayList<YearBean>(); 
        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        PreparedStatement stat2=null;
        ResultSet result2=null;
        try{
        	actlist=new ArrayList<AlertBean>();
        	stat=conn.prepareStatement("SELECT ACTIVITY_NAME FROM CC_ACTIVITY_MASTER WHERE ID=?");
        	stat.setString(1,U_SLNO);
        	result=stat.executeQuery();
			if(result.next()){
				E_ACTIVITY_NAME=result.getString("ACTIVITY_NAME");
			}	
			stat1=conn.prepareStatement("SELECT ID, ACTIVITY_ID, ACTIVITY_YEAR, FLAG, USER_ID, TO_CHAR(TDATE,'dd/MM/yyyy') TDATE, M_USER_ID, TO_CHAR(MDATE,'dd/MM/yyyy') MDATE FROM CC_ACTIVITY_YEAR WHERE ACTIVITY_ID=? order by ACTIVITY_YEAR");
			stat1.setString(1, U_SLNO);
			result1=stat1.executeQuery();
			while(result1.next()){
				yearBeans.add(new YearBean(result1.getLong("ID"), result1.getString("ACTIVITY_ID"), result1.getString("ACTIVITY_YEAR"), result1.getString("FLAG"), result1.getString("USER_ID"), result1.getString("TDATE"), result1.getString("M_USER_ID"), result1.getString("MDATE")));
			}
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.year()"+se);
        	return "linkco";
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.year()"+e);
        	return "linkco";
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
		return "yearco";
	}
	
	public String yearsave() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "yearco";
        }
		Connection conn=null;
        try{
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.year()"+e);
        	return "yearco";
        }
        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        try{
        	int ctr=0;
        	for(int i=0;i<YEAR_CAL.size();i++){
        		if(YEAR_CAL.get(i)!=null && YEAR_CAL.get(i).length()>0){
	        		stat=conn.prepareStatement("SELECT ID FROM CC_ACTIVITY_YEAR WHERE ACTIVITY_ID=? AND ACTIVITY_YEAR=?");
	        		stat.setString(1, U_SLNO);
	        		stat.setString(2, YEAR_CAL.get(i));
	        		result=stat.executeQuery();
	        		if(result.next()){
	        			stat1=conn.prepareStatement("UPDATE CC_ACTIVITY_YEAR SET FLAG=?,M_USER_ID=?, MDATE=SYSDATE WHERE ACTIVITY_ID=? AND ACTIVITY_YEAR=?");
	        			stat1.setString(1, YEAR_STATUS.get(i));
	        			stat1.setString(2, usrid);
	        			stat1.setString(3, U_SLNO);
	            		stat1.setString(4, YEAR_CAL.get(i));
	            		int st=stat1.executeUpdate();
	            		if(st>0){
	            			++ctr;
	            		}
	        		}
	        		else{
	        			long slno=0;
	        			stat1=conn.prepareStatement("SELECT MAX(NVL(ID,0))+1 SLNO FROM CC_ACTIVITY_YEAR");
	        			result1=stat1.executeQuery();
	        			while(result1.next()){
	        				slno=result1.getLong("SLNO");
	        			}
	        			if(stat1!=null){
	        				stat1.close();
	        			}
	        			if(result1!=null){
	        				result1.close();
	        			}
	        			stat1=conn.prepareStatement("INSERT INTO CC_ACTIVITY_YEAR (ID, ACTIVITY_ID, ACTIVITY_YEAR, FLAG, USER_ID, TDATE, M_USER_ID, MDATE) VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)");
	        			stat1.setLong(1, slno);
	        			stat1.setString(2, U_SLNO);
	        			stat1.setString(3, YEAR_CAL.get(i));
	        			stat1.setString(4, YEAR_STATUS.get(i));
	        			stat1.setString(5, usrid);
	        			stat1.setString(6, usrid);
	        			int st=stat1.executeUpdate();
	            		if(st>0){
	            			++ctr;
	            		}
	        			
	        		}
        		}
        	}
        	if(ctr>0){
        		addActionError("Record Saved Successfully!!!");
        	}
        	 year();
             if(yearBeans!=null && yearBeans.size()>0){
             	stat=conn.prepareStatement("SELECT UPD_FLAG FROM CC_ACTIVITY_MASTER WHERE ID=?");
             	stat.setString(1,U_SLNO);
             	result=stat.executeQuery();
     			if(result.next()){
     				if(result.getString("UPD_FLAG")!=null && result.getString("UPD_FLAG").equals("Y")){}
     				else{
     					stat1=conn.prepareStatement("UPDATE CC_ACTIVITY_MASTER SET UPD_FLAG='Y' WHERE ID=?");
     					stat1.setString(1,U_SLNO);
     					stat1.executeUpdate();
     				}
     			}
             }
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.year()"+se);
        	return "yearco";
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.year()"+e);
        	return "yearco";
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
       
        return "yearco";
	}
	
	public String newco() throws SQLException{
		ACTIVITY_NAME=null;
		LOCATION=null;
		DEPARTMENT=null;
		SUBDEPARTMENT=null;
		locationcodelist=new ArrayList<UserLinkDetailLoctBean>();
		deptcodelist=new ArrayList<UserLinkDetailLoctBean>();
		Connection conn = null;
		Connection conn1 = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new ConnectionShahiHris().getConnection();
            conn1 = new connection().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
        	stat=conn.prepareStatement("select LOCATION_CODE,LOCATION_DESC from HRLOCT WHERE FLAG='A'");
        	result=stat.executeQuery();
        	while(result.next()){
        		locationcodelist.add(new UserLinkDetailLoctBean(result.getString("LOCATION_CODE"), result.getString("LOCATION_DESC")));
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        	
        	stat=conn1.prepareStatement("SELECT ID,NAME FROM CC_DEPT_MASTER WHERE FLAG='Y'");
        	result=stat.executeQuery();
        	while(result.next()){
        		deptcodelist.add(new UserLinkDetailLoctBean(result.getString("ID"), result.getString("NAME")));
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        }
		catch (Exception e) {
	        System.out.println("ActivityLinkDetailAction " + e);
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	        if (conn1 != null) {
	            conn1.close();
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
        LOCATION=null;
        DEPARTMENT=null;
        SUBDEPARTMENT=null;
        Connection conn=null;
        Connection conn1=null;
        try{
        	conn=new connection().getConnection();
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.save()"+e);
        	return "editco";
        }
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        ResultSet result=null;
        ResultSet result1=null;
        try{
        	ulocationcodelist=new ArrayList<UpdFlagBean>();
        	locationcodelist=new ArrayList<UserLinkDetailLoctBean>();
        	deptcodelist=new ArrayList<UserLinkDetailLoctBean>();
        	udeptcodelist=new ArrayList<UpdFlagBean>();
        
        	stat=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=? and UPD_FLAG='Y'");
        	stat.setString(1, E_ACTIVITY_ID);
        	result=stat.executeQuery();
        	if(result.next()){
        		UPD_FLAG="OK";
        	}
        	
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        	
        	stat=conn.prepareStatement("SELECT distinct ACTIVITY_NAME FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=?");
        	stat.setString(1, E_ACTIVITY_ID);
        	result=stat.executeQuery();
        	while(result.next()){
        		E_ACTIVITY_NAME=result.getString("ACTIVITY_NAME");
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        	
        	//USED
        	String temploct="";
        	stat=conn.prepareStatement("SELECT DISTINCT LOCATION_CODE FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=?");
        	stat.setString(1, E_ACTIVITY_ID);
        	result=stat.executeQuery();
        	while(result.next()){
        		stat1=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=? AND LOCATION_CODE=? AND UPD_FLAG='Y'");
        		stat1.setString(1, E_ACTIVITY_ID);
        		stat1.setString(2, result.getString("LOCATION_CODE"));
        		result1=stat1.executeQuery();
        		String flag=null;
        		if(result1.next()){
        			flag="Y";
        		}
        		if(stat1!=null){
	        		stat1.close();
	        	}
	        	if(result1!=null){
	        		result1.close();
	        	}
	        	
        		temploct+=result.getString("LOCATION_CODE")+",";
        		ulocationcodelist.add(new UpdFlagBean(result.getString("LOCATION_CODE"),flag));
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        	if(temploct.length()>0){
        		temploct=temploct.substring(0,temploct.lastIndexOf(","));
        		temploct="LOCATION_CODE IN("+temploct+") OR ";
        	}
        	else{
        		temploct=" ";
        	}
        	stat=conn1.prepareStatement("select LOCATION_CODE,LOCATION_DESC from HRLOCT WHERE ("+temploct+" FLAG='A')");
        	result=stat.executeQuery();
        	while(result.next()){
        		locationcodelist.add(new UserLinkDetailLoctBean(result.getString("LOCATION_CODE"), result.getString("LOCATION_DESC")));
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        	
        	String tempdept="";
        	stat=conn.prepareStatement("SELECT DISTINCT DEPT_ID FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=?");
        	stat.setString(1, E_ACTIVITY_ID);
        	result=stat.executeQuery();
        	while(result.next()){
        		stat1=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=? AND DEPT_ID=? AND UPD_FLAG='Y'");
        		stat1.setString(1, E_ACTIVITY_ID);
        		stat1.setString(2, result.getString("DEPT_ID"));
        		result1=stat1.executeQuery();
        		String flag=null;
        		if(result1.next()){
        			flag="Y";
        		}
        		if(stat1!=null){
	        		stat1.close();
	        	}
	        	if(result1!=null){
	        		result1.close();
	        	}
        		tempdept+=result.getString("DEPT_ID")+",";
        		udeptcodelist.add(new UpdFlagBean(result.getString("DEPT_ID"),flag));
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        	if(tempdept.length()>0){
        		tempdept=tempdept.substring(0,tempdept.lastIndexOf(","));
        		tempdept="ID IN("+tempdept+") OR ";
        	}
        	else{
        		tempdept=" ";
        	}
        	
        	stat=conn.prepareStatement("SELECT ID,NAME FROM CC_DEPT_MASTER WHERE ("+tempdept+" FLAG='Y') ORDER BY NAME");
        	result=stat.executeQuery();
        	while(result.next()){
        		deptcodelist.add(new UserLinkDetailLoctBean(result.getString("ID"), result.getString("NAME")));
        	}
        	if(stat!=null){
        		stat.close();
        	}
        	if(result!=null){
        		result.close();
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+e);
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
        	addActionError("Compliance ActivityLinkDetailAction.update()"+e);
        	return "editco";
        }
        
        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        PreparedStatement statd=null;
        ResultSet resultd=null;
        PreparedStatement statf=null;
        ResultSet resultf=null;
        PreparedStatement statm;
        ResultSet resultm=null;
        PreparedStatement stats=null;
        ResultSet results=null;
        PreparedStatement stat2=null;
        ResultSet result2=null;
        try{
        	statf=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID<>? AND ACTIVITY_NAME=?");
        	statf.setString(1, ACTIVITY_ID);
        	statf.setString(2, ACTIVITY_NAME);
        	resultf=statf.executeQuery();
        	if(resultf.next()){
        		addActionError("Activity Name already exist");
        		E_ACTIVITY_ID=ACTIVITY_ID;
        		edit();
        		return "editco";
        	}
        	else{
        		statd=conn.prepareStatement("DELETE FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=? AND UPD_FLAG IS NULL");
        		statd.setString(1, ACTIVITY_ID);
        		statd.executeUpdate();
        		if(statd!=null){
        			statd.close();
        		}
        		int x=0;
        		for(int i=0;i<LOCATION.size();i++){
					for(int j=0;j<DEPARTMENT.size();j++){
						for(int k=0;k<SUBDEPARTMENT.size();k++){
							stat=conn.prepareStatement("SELECT * FROM CC_SUB_DEPT_MASTER WHERE ID=? AND DEPT_ID=?");
							stat.setString(1, SUBDEPARTMENT.get(k).toString());
							stat.setString(2, DEPARTMENT.get(j).toString());
							result=stat.executeQuery();
							if(result.next()){
								stat1=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_NAME=? AND LOCATION_CODE=? AND DEPT_ID=? AND SUBDEPT_ID=?");
								stat1.setString(1, ACTIVITY_NAME.trim().toUpperCase());
								stat1.setString(2, LOCATION.get(i).toString());
								stat1.setString(3, DEPARTMENT.get(j).toString());
								stat1.setString(4, SUBDEPARTMENT.get(k).toString());								
								result1=stat1.executeQuery();
								if(result1.next()){
									//addActionError("Activity Name already Exist for same Location, Dept and Sub Dept..");
								}
								else{									
									long id=0;
			        				stats=conn.prepareStatement("SELECT nvl(max(ID),0)+1 ID FROM CC_ACTIVITY_MASTER");
			        				results=stats.executeQuery();
			        				if(results.next()){
			        					id=results.getLong("ID");
			        				}
									if(stats!=null){
										stats.close();
									}
									if(results!=null){
										results.close();
									}
									stat2=conn.prepareStatement("INSERT INTO CC_ACTIVITY_MASTER (ID,ACTIVITY_NAME,LOCATION_CODE,DEPT_ID,SUBDEPT_ID,FLAG,USER_ID,TDATE,MUSER_ID,MDATE,ACTIVITY_ID) VALUES(?,?,?,?,?,'Y',?,SYSDATE,?,SYSDATE,?)");
									stat2.setLong(1, id);
									stat2.setString(2, ACTIVITY_NAME.trim().toUpperCase());
									stat2.setString(3, LOCATION.get(i).toString());
									stat2.setString(4, DEPARTMENT.get(j).toString());
									stat2.setString(5, SUBDEPARTMENT.get(k).toString());
									stat2.setString(6, usrid);
									stat2.setString(7, usrid);
									stat2.setString(8, ACTIVITY_ID);
									int c=stat2.executeUpdate();
									if(c>0){
										++x;
									}
									if(stat2!=null){
										stat2.close();
									}
								}
							}
						}
					}
				}
				if(x>0){
					addActionError("Record Saved Successfully!!!");
				}
				if(statf!=null){
					statf.close();
				}
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.update()"+se);
        	return "editco";
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.update()"+e);
        	return "editco";
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        }
        E_ACTIVITY_ID=ACTIVITY_ID;
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
        	addActionError("Compliance ActivityLinkDetailAction.update()"+e);
        	return SUCCESS;
        }
        PreparedStatement stat=null;
        try{
        	int ctr=0;
        	if(chkdel!=null && chkdel.size()>0){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_ACTIVITY_MASTER WHERE ID=? and UPD_FLAG is null");
        			stat.setString(1, chkdel.get(i).toString());
        			int st=stat.executeUpdate();
        			if(st>0){
        				++ctr;
        			}
                                stat=conn.prepareStatement("DELETE FROM CC_ACTIVITY_ACCESS_MASTER WHERE ACT_ID=? ");
        			stat.setString(1, chkdel.get(i).toString());
        			st=stat.executeUpdate();
        			
                                stat=conn.prepareStatement("DELETE FROM CC_USER_LINK WHERE ACT_ID=?");
        			stat.setString(1, chkdel.get(i).toString());
        			st=stat.executeUpdate();
        			
        		}
                        
                        
        		if(ctr>0){
        			addActionError("Record Deleted Successfully");
        		}
        	}
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.update()"+se);
        	return SUCCESS;
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.update()"+e);
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
	
	public String link() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "linkco";
        }
        if(U_SLNO==null){
        	U_SLNO=E_SLNO;
        }
        Connection conn=null;
        Connection conn1=null;
        userlinklist = new ArrayList<UserLinkBean>();
        try{
        	conn=new connection().getConnection();
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.link()"+e);
        	return "linkco";
        }
        ALERT_TYPE=null;
        EMP_CODE=null;
        EMP_NAME=null;
        chkdel=null;
        
        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        PreparedStatement stat2=null;
        ResultSet result2=null;
        try{
        	actlist=new ArrayList<AlertBean>();
        	stat=conn.prepareStatement("SELECT ACTIVITY_NAME FROM CC_ACTIVITY_MASTER WHERE ID=?");
        	stat.setString(1,U_SLNO);
        	result=stat.executeQuery();
			if(result.next()){
				E_ACTIVITY_NAME=result.getString("ACTIVITY_NAME");
			}	
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+se);
        	return "linkco";
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+e);
        	return "linkco";
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
		return "linkco";
	}
	
	public String linkuser() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "linkuser";
        }
        userlinklist=new ArrayList<UserLinkBean>();
        Connection conn=null;
        Connection conn1=null;
        PreparedStatement stat=null;
        ResultSet resultSet=null;
        PreparedStatement stat1=null;
        ResultSet resultSet1=null;
        PreparedStatement stat2=null;
        ResultSet resultSet2=null;
        try{
        	conn=new connection().getConnection();     
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.link()"+e);
        	return "linkco";
        }
        try{
            
            
                     stat=conn.prepareStatement("select a.EMP_CODE,b.USER_ID,b.USER_NAME,b.USER_NAME||'-'||b.USER_ID FULL_NAME from CC_ACTIVITY_ACCESS_MASTER a,seh_web_users b where a.EMP_CODE=b.USER_ID and a.ACT_ID=? order by 3");
                     stat.setString(1, U_SLNO.trim());
                     resultSet=stat.executeQuery();
                     while(resultSet.next())
                     {
                     userlist.add(new UserLinkBean(resultSet.getString("EMP_CODE"),resultSet.getString("FULL_NAME")));
                     }
            
			if(ALERT_TYPE_TAB!=null && ALERT_TYPE_TAB.equals("T1")){
				stat=conn.prepareStatement("SELECT ALERT_TYPE1 ALERT_TYPE FROM CC_ACTIVITY_MASTER WHERE ID=?");
			}
			else if(ALERT_TYPE_TAB!=null && ALERT_TYPE_TAB.equals("T2")){
				stat=conn.prepareStatement("SELECT ALERT_TYPE2 ALERT_TYPE FROM CC_ACTIVITY_MASTER WHERE ID=?");
			}
			else if(ALERT_TYPE_TAB!=null && ALERT_TYPE_TAB.equals("T3")){
				stat=conn.prepareStatement("SELECT ALERT_TYPE3 ALERT_TYPE FROM CC_ACTIVITY_MASTER WHERE ID=?");
			}
			stat.setString(1, U_SLNO);
			resultSet=stat.executeQuery();
			if(resultSet.next()){
				E_ALERT_TYPE=resultSet.getString("ALERT_TYPE");
			}
			if(stat!=null){
				stat.close();
			}
			if(resultSet!=null){
				resultSet.close();
			}
			stat1=conn.prepareStatement("SELECT ID, ACT_ID, LINK_USER, ALERT_TYPE, USER_ID, TDATE FROM CC_USER_LINK WHERE ACT_ID=? AND ALERT_TYPE=? ORDER BY ALERT_TYPE");
			stat1.setString(1, U_SLNO);
			stat1.setString(2, ALERT_TYPE_TAB);
			resultSet1=stat1.executeQuery();
			while (resultSet1.next()) {
				String tuser="";
				stat2=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
				stat2.setString(1, resultSet1.getString("LINK_USER"));
				resultSet2=stat2.executeQuery();
        		if(resultSet2.next()){
        			tuser=resultSet2.getString("FULL_NAME");
        		}
        		if(stat2!=null){
        			stat2.close();
        		}
        		if(resultSet2!=null){
        			resultSet2.close();
        		}
        		userlinklist.add(new UserLinkBean(resultSet1.getString("ID"), resultSet1.getString("ALERT_TYPE"), resultSet1.getString("LINK_USER"), tuser));
			}
			if(stat1!=null){
				stat1.close();
			}
			if(resultSet1!=null){
				resultSet1.close();
			}
		}
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+e);
        	return "linkco";
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        }        
		return "linkuser";
	}
	
        
        public String usraccess() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "usracc";
        }
        userlist=new ArrayList();
        Connection conn=null;
        Connection conn1=null;
        PreparedStatement stat=null;
        ResultSet resultSet=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        PreparedStatement stat2=null;
        ResultSet resultSet2=null;
        int kk=0;
        try{
        	conn=new connection().getConnection();     
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.link()"+e);
        	return "usracc";
        }
        try{
        
            stat = conn.prepareStatement("SELECT ACTIVITY_NAME FROM CC_ACTIVITY_MASTER WHERE ID=?");
            stat.setString(1, U_SLNO);
            resultSet = stat.executeQuery();
            if (resultSet.next()) {
                E_ACTIVITY_NAME = resultSet.getString("ACTIVITY_NAME");
            }
            
            if(emplist!=null && emplist.size()>0)
            {
                for(int i=0; i< emplist.size(); i++)
                {
                  stat=conn.prepareStatement("insert into  CC_ACTIVITY_ACCESS_MASTER(ID,ACT_ID,EMP_CODE,FLAG,TDATE,USER_ID,ACC_TYPE) values(CC_ACTIVITY_ACCESS_MASTER_Sq.nextval,?,?,'Y',sysdate,?,?)");
                  stat.setString(1, U_SLNO.trim());
                  stat.setString(2, emplist.get(i).toString());
                  stat.setString(3,usrid);
                  stat.setString(4,ACC_TYPE_USR);
                  kk=stat.executeUpdate();
                }
            
            }
            
            
           if(delchkusr!=null && delchkusr.size()>0){
            for(int i=0; i<delchkusr.size(); i++){
              stat=conn.prepareStatement("delete from  CC_ACTIVITY_ACCESS_MASTER  where id=?");
               stat.setString(1, delchkusr.get(i).toString());
             kk=stat.executeUpdate();
            }
            }
            
            if(ACC_ID!=null && ACC_ID.size()>0){
            for(int i=0; i<ACC_ID.size(); i++){
              stat=conn.prepareStatement("update CC_ACTIVITY_ACCESS_MASTER set FLAG=?, ACC_TYPE=? where id=?");
              stat.setString(1, ACC_FLAG.get(i).toString());
              stat.setString(2, ACC_TYPE.get(i).toString());
             stat.setString(3, ACC_ID.get(i).toString());
             kk=stat.executeUpdate();
            }
            }
            
            
             stat=conn.prepareStatement("select ID,ACT_ID,EMP_CODE,FLAG,ACC_TYPE from CC_ACTIVITY_ACCESS_MASTER where ACT_ID=? order by 3");
             stat.setString(1, U_SLNO.trim());
             resultSet=stat.executeQuery();
             while(resultSet.next())
             {
                   stat1=conn1.prepareStatement("SELECT INITCAP(FULL_NAME) FULL_NAME FROM HREMPMST WHERE EMP_CODE=?");
        		stat1.setString(1, resultSet.getString("EMP_CODE"));
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
                     userlist.add(new UserLinkBean(resultSet.getString("ID"),resultSet.getString("EMP_CODE"),tuser,resultSet.getString("ACC_TYPE"),resultSet.getString("FLAG")));
             }
        
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+e);
        	return "usracc";
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        }    
        if((ACC_ID!=null && ACC_ID.size()>0) || (emplist!=null && emplist.size()>0) || (delchkusr!=null && delchkusr.size()>0)){
        if(kk>0)
        {
       addActionError("Record Saved Successfully");
        }
        }
        
		return "usracc";
	}
        
	public String linksave() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "linkco";
        }
        Connection conn=null;
        try{
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.link()"+e);
        	return "linkco";
        }
        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        PreparedStatement stats=null;
        ResultSet results=null;
        int ctr=0;
        try{        	
        	if(ALERT_TYPE!=null && ALERT_TYPE.size()>0){
        		for(int i=0;i<ALERT_TYPE.size();i++){
        			for(int j=0;j<EMP_CODE.size();j++){
	        			if(EMP_CODE.get(j).toString()!=null && EMP_CODE.get(j).toString().length()>0){
	        				if(EMP_TYPE.get(j).toString()!=null && EMP_TYPE.get(j).toString().equals(ALERT_TYPE.get(i).toString())){
		        				stat=conn.prepareStatement("SELECT * FROM CC_USER_LINK WHERE ACT_ID=? AND LINK_USER=? AND ALERT_TYPE=?");
		        				stat.setString(1, U_SLNO);
		        				stat.setString(2, EMP_CODE.get(j).toString());
		        				stat.setString(3, ALERT_TYPE.get(i).toString());
		        				result = stat.executeQuery();
		        				if(result.next()){
		        					addActionError("User already exist");
		        				}
		        				else{
		        					int id=0;
			        				stats=conn.prepareStatement("SELECT nvl(max(ID),0)+1 ID FROM CC_USER_LINK");
			        				results=stats.executeQuery();
			        				if(results.next()){
			        					id=results.getInt("ID");
			        				}
			        				if(stats!=null){
			        					stats.close();
			        				}
			        				if(results!=null){
			        					results.close();
			        				}
		        					stat1=conn.prepareStatement("INSERT INTO CC_USER_LINK(ID,ACT_ID,LINK_USER,ALERT_TYPE,USER_ID,TDATE) VALUES(?,?,?,?,?,SYSDATE)");
		        					stat1.setInt(1, id);
		        					stat1.setString(2, U_SLNO);
		        					stat1.setString(3, EMP_CODE.get(j).toString());
		        					stat1.setString(4, ALERT_TYPE.get(i).toString());
		        					stat1.setString(5, usrid);
		        					int st=stat1.executeUpdate();
		        					if(st>0){
		        						++ctr;
		        					}
		        					if(stat1!=null){
		        						stat1.close();
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
        			}
                                
                              //update alert days
                                
                               
                                
                                
        		}
        		String alerttype1=ALERT_TYPE.get(0).toString();
        		String alerttype2=ALERT_TYPE.get(1).toString();
        		String alerttype3=ALERT_TYPE.get(2).toString();
                        
        		 stat=conn.prepareStatement("update CC_ACTIVITY_MASTER set ALERT_TYPE1=?,ALERT_TYPE2=?,ALERT_TYPE3=?  WHERE ID=?");
                         stat.setString(1, ALERT_DAY.get(0).toString());
                         stat.setString(2, ALERT_DAY.get(1).toString());
                         stat.setString(3, ALERT_DAY.get(2).toString());
                         stat.setString(4, U_SLNO);
                         stat.executeUpdate();
                         
                         
        	}
        	if(chkdel!=null && chkdel.size()>0 && !chkdel.get(0).toString().equals("false")){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_USER_LINK WHERE ID=?");
        			stat.setString(1, chkdel.get(i).toString());
        			stat.executeUpdate();
        			if(stat!=null){
        				stat.close();
        			}
        		}
        	}
        	link();
            if(userlinklist!=null && userlinklist.size()>0){
            	stat=conn.prepareStatement("SELECT UPD_FLAG FROM CC_ACTIVITY_MASTER WHERE ID=?");
            	stat.setString(1,U_SLNO);
            	result=stat.executeQuery();
    			if(result.next()){
    				if(result.getString("UPD_FLAG")!=null && result.getString("UPD_FLAG").equals("Y")){}
    				else{
    					stat1=conn.prepareStatement("UPDATE CC_ACTIVITY_MASTER SET UPD_FLAG='Y' WHERE ID=?");
    					stat1.setString(1,U_SLNO);
    					stat1.executeUpdate();
    				}
    			}
            }
        }
        catch (Exception e) {
	        System.out.println("UserMasterAction " + e);
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	    }
        if(ctr>0){
        	addActionError("Record Saved Successfully");
        }        
        return "linkco";
	}
	
	public String save() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
		if(ACTIVITY_NAME!=null && ACTIVITY_NAME.length()>0){
			Connection conn = null;
			PreparedStatement stat = null;
	        ResultSet result = null;
	        PreparedStatement stat1 = null;
	        ResultSet result1 = null;
	        PreparedStatement stat2 = null;
	        PreparedStatement stats = null;
	        ResultSet results = null;
	        PreparedStatement statm = null;
	        ResultSet resultm = null;
	        try {
	            conn = new connection().getConnection();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            return "newco";
	        }
	        try {
	        	int x=0;
				for(int i=0;i<LOCATION.size();i++){
					for(int j=0;j<DEPARTMENT.size();j++){
						for(int k=0;k<SUBDEPARTMENT.size();k++){
							stat=conn.prepareStatement("SELECT * FROM CC_SUB_DEPT_MASTER WHERE ID=? AND DEPT_ID=?");
							stat.setString(1, SUBDEPARTMENT.get(k).toString());
							stat.setString(2, DEPARTMENT.get(j).toString());
							result=stat.executeQuery();
							if(result.next()){
								stat1=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_NAME=? AND LOCATION_CODE=? AND DEPT_ID=? AND SUBDEPT_ID=?");
								stat1.setString(1, ACTIVITY_NAME.trim().toUpperCase());
								stat1.setString(2, LOCATION.get(i).toString());
								stat1.setString(3, DEPARTMENT.get(j).toString());
								stat1.setString(4, SUBDEPARTMENT.get(k).toString());								
								result1=stat1.executeQuery();
								if(result1.next()){
									addActionError("Activity Name already Exist for same Location, Dept and Sub Dept..");
								}
								else{
									long actid=0;
									statm=conn.prepareStatement("SELECT ACTIVITY_ID FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_NAME=?");
									statm.setString(1, ACTIVITY_NAME.trim().toUpperCase());
			        				resultm=statm.executeQuery();
			        				if(resultm.next()){
			        					actid=resultm.getLong("ACTIVITY_ID");
			        				}
			        				else{
			        					stats=conn.prepareStatement("SELECT nvl(max(ACTIVITY_ID),0)+1 ACTIVITY_ID FROM CC_ACTIVITY_MASTER");
				        				results=stats.executeQuery();
				        				if(results.next()){
				        					actid=results.getLong("ACTIVITY_ID");
				        				}
				        				if(stats!=null){
											stats.close();
										}
										if(results!=null){
											results.close();
										}
			        				}
									if(statm!=null){
										statm.close();
									}
									if(resultm!=null){
										resultm.close();
									}
									long id=0;
			        				stats=conn.prepareStatement("SELECT nvl(max(ID),0)+1 ID FROM CC_ACTIVITY_MASTER");
			        				results=stats.executeQuery();
			        				if(results.next()){
			        					id=results.getLong("ID");
			        				}
									if(stats!=null){
										stats.close();
									}
									if(results!=null){
										results.close();
									}
									stat2=conn.prepareStatement("INSERT INTO CC_ACTIVITY_MASTER (ID,ACTIVITY_NAME,LOCATION_CODE,DEPT_ID,SUBDEPT_ID,FLAG,USER_ID,TDATE,MUSER_ID,MDATE,ACTIVITY_ID) VALUES(?,?,?,?,?,'Y',?,SYSDATE,?,SYSDATE,?)");
									stat2.setLong(1, id);
									stat2.setString(2, ACTIVITY_NAME.trim().toUpperCase());
									stat2.setString(3, LOCATION.get(i).toString());
									stat2.setString(4, DEPARTMENT.get(j).toString());
									stat2.setString(5, SUBDEPARTMENT.get(k).toString());
									stat2.setString(6, usrid);
									stat2.setString(7, usrid);
									stat2.setLong(8, actid);
									int c=stat2.executeUpdate();
									if(c>0){
										++x;
									}
									if(stat2!=null){
										stat2.close();
									}
								}
							}
						}
					}
				}
				if(x>0){
					addActionError("Record Saved Successfully!!!");
				}
	        }
			catch (Exception e) {
		        System.out.println("UserMasterAction " + e);
		    } finally {
		        if (conn != null) {
		            conn.close();
		        }
		    }
		}
		newco();
		return "newco";
	}
	public String subdpt() throws SQLException{
		subdeptcodelist=new ArrayList<UserLinkDetailLoctBean>();
		if(deptcode!=null && deptcode.length()>0){
			Connection conn = null;
			PreparedStatement stat = null;
	        ResultSet result = null;
	        try {
	            conn = new connection().getConnection();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	        try {
	        	String qpr=" WHERE  DEPT_ID IN("+deptcode+")";
	        	stat=conn.prepareStatement("SELECT ID, NAME FROM CC_SUB_DEPT_MASTER"+qpr +" ORDER BY NAME");
	        	result=stat.executeQuery();
	        	while(result.next()){
	        		subdeptcodelist.add(new UserLinkDetailLoctBean(result.getString("ID"), result.getString("NAME")));
	        	}
	        	if(stat!=null){
	        		stat.close();
	        	}
	        	if(result!=null){
	        		result.close();
	        	}
	        }
	        catch (Exception e) {
		        System.out.println("UserMasterAction " + e);
		    } finally {
		        if (conn != null) {
		            conn.close();
		        }
		    }
		}
		return "subdp";
	}
	
	public String subdpted() throws SQLException{
		subdeptcodelist=new ArrayList<UserLinkDetailLoctBean>();
		usubdeptcodelist=new ArrayList<UpdFlagBean>();
		if(deptcode!=null && deptcode.length()>0){
			Connection conn = null;
			PreparedStatement stat = null;
	        ResultSet result = null;
	        PreparedStatement stat1 = null;
	        ResultSet result1 = null;
	        try {
	            conn = new connection().getConnection();
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	        try {
	        	
	        	String tempsubdept="";
	        	stat=conn.prepareStatement("SELECT DISTINCT SUBDEPT_ID FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=?");
	        	stat.setString(1, E_ACTIVITY_ID);
	        	result=stat.executeQuery();
	        	while(result.next()){
	        		stat1=conn.prepareStatement("SELECT * FROM CC_ACTIVITY_MASTER WHERE ACTIVITY_ID=? AND SUBDEPT_ID=? AND UPD_FLAG='Y'");
	        		stat1.setString(1, E_ACTIVITY_ID);
	        		stat1.setString(2, result.getString("SUBDEPT_ID"));
	        		result1=stat1.executeQuery();
	        		String flag=null;
	        		if(result1.next()){
	        			flag="Y";
	        		}
	        		if(stat1!=null){
		        		stat1.close();
		        	}
		        	if(result1!=null){
		        		result1.close();
		        	}
	        		
	        		tempsubdept+=result.getString("SUBDEPT_ID")+",";
	        		usubdeptcodelist.add(new UpdFlagBean(result.getString("SUBDEPT_ID"),flag));
	        	}
	        	if(stat!=null){
	        		stat.close();
	        	}
	        	if(result!=null){
	        		result.close();
	        	}
	        	if(tempsubdept.length()>0){
	        		tempsubdept=tempsubdept.substring(0,tempsubdept.lastIndexOf(","));
	        		tempsubdept=" OR ID IN("+tempsubdept+")";
	        	}
	        	else{
	        		tempsubdept=" ";
	        	}
	        	
	        	String qpr=" WHERE  (DEPT_ID IN("+deptcode+")";
	        	stat=conn.prepareStatement("SELECT ID, NAME FROM CC_SUB_DEPT_MASTER"+qpr +") ORDER BY NAME");
	        	result=stat.executeQuery();
	        	while(result.next()){
	        		subdeptcodelist.add(new UserLinkDetailLoctBean(result.getString("ID"), result.getString("NAME")));
	        	}
	        	if(stat!=null){
	        		stat.close();
	        	}
	        	if(result!=null){
	        		result.close();
	        	}
	        }
	        catch (Exception e) {
		        System.out.println("UserMasterAction " + e);
		    } finally {
		        if (conn != null) {
		            conn.close();
		        }
		    }
		}
		return "subdpedit";
	}
	
        
   public String usrlink() throws SQLException{
	Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "usrdt";
        }
        if(U_SLNO==null){
        	U_SLNO=E_SLNO;
        }
        Connection conn=null;
        Connection conn1=null;
        userlinklist = new ArrayList<UserLinkBean>();
        try{
        	conn=new connection().getConnection();
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.link()"+e);
        	return "usrdt";
        }
        ALERT_TYPE=null;
        EMP_CODE=null;
        EMP_NAME=null;
        chkdel=null;
        
        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        PreparedStatement stat2=null;
        ResultSet result2=null;
        try{
        	actlist=new ArrayList<AlertBean>();
        	stat=conn.prepareStatement("SELECT ACTIVITY_NAME FROM CC_ACTIVITY_MASTER WHERE ID=?");
        	stat.setString(1,U_SLNO);
        	result=stat.executeQuery();
			if(result.next()){
				E_ACTIVITY_NAME=result.getString("ACTIVITY_NAME");
			}	
        }
        catch(SQLException se){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+se);
        	return "usrdt";
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.edit()"+e);
        	return "usrdt";
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
		return "usrdt";
	}    
   
   public String copy() throws Exception{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
       if(usrid==null){
       	addActionError("Session not valid");
       	  return mksess();
       }
       Connection conn=null;
       PreparedStatement stat2=null;
       PreparedStatement stat1=null;
       PreparedStatement statinsert=null;
       PreparedStatement statupd=null;
       ResultSet resultSet2=null;
       ResultSet resultSet1=null;
       
       try {
		    conn = new connection().getConnection();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
       try{
    	       // System.out.println("COPY_ACTIVITY_ID"+COPY_ACTIVITY_ID);
    	        
       			if(COPY_ACTIVITY_ID!=null && COPY_ACTIVITY_ID.length()>0 && !COPY_ACTIVITY_ID.equals(""))
       			{
	        				
	        				long slno=0;
	        				conn.setAutoCommit(false);
	        				
	        				int ctr=0;
	        				
	        				stat1=conn.prepareStatement("SELECT NVL(MAX(ID),0)+1 SL_NO FROM CC_ACTIVITY_MASTER");
	        				resultSet1=stat1.executeQuery();
	        				if(resultSet1.next()){
	        					slno=resultSet1.getLong("SL_NO");
	        				}
	        				if(stat1!=null){
	        					stat1.close();
	        				}
	        				if(resultSet1!=null){
	        					resultSet1.close();
	        				}
	        				
	        				statinsert=conn.prepareStatement("insert into CC_ACTIVITY_MASTER select "+slno+" ,ACTIVITY_NAME,LOCATION_CODE,DEPT_ID,SUBDEPT_ID,ALERT_TYPE1,ALERT_TYPE2,ALERT_TYPE3,FLAG,'"+usrid+"'"+",sysdate,'"+usrid+"'"+",sysdate, "+slno+",'Y',null FROM CC_ACTIVITY_MASTER where id='"+COPY_ACTIVITY_ID+"'");
	        				int st=statinsert.executeUpdate();
	        				
	        				statinsert=conn.prepareStatement("insert into  CC_ACTIVITY_ACCESS_MASTER SELECT  CC_ACTIVITY_ACCESS_MASTER_Sq.nextval,"+slno+",EMP_CODE,FLAG,SYSDATE,'"+usrid+"'"+",ACC_TYPE FROM CC_ACTIVITY_ACCESS_MASTER WHERE ACT_ID='"+COPY_ACTIVITY_ID+"'");
	        				st=statinsert.executeUpdate();
	        				
	        				
	        				stat1=conn.prepareStatement("select * from cc_user_link where ACT_ID='"+COPY_ACTIVITY_ID+"'");
	        				resultSet1=stat1.executeQuery();
	        				while(resultSet1.next())
	        				{
	        					long slno1=0;
		        				stat2=conn.prepareStatement("SELECT NVL(MAX(ID),0)+1 SL_NO FROM cc_user_link");
		        				resultSet2=stat2.executeQuery();
		        				if(resultSet2.next()){
		        					slno1=resultSet2.getLong("SL_NO");
		        				}
		        				
		        				stat2=conn.prepareStatement("INSERT INTO CC_USER_LINK(ID,ACT_ID,LINK_USER,ALERT_TYPE,USER_ID,TDATE) VALUES(?,?,?,?,?,SYSDATE)");
		        				stat2.setLong(1,slno1);
		        				stat2.setLong(2,slno);
		        				stat2.setString(3,resultSet1.getString("LINK_USER"));
		        				stat2.setString(4,resultSet1.getString("ALERT_TYPE"));
		        				stat2.setString(5,usrid);
		        				st=stat2.executeUpdate();
		        				
		        				
		        				if(stat2!=null){
		        					stat2.close();
		        				}
		        				if(resultSet2!=null){
		        					resultSet2.close();
		        				}
	        					
	        				}
	        				if(stat1!=null){
	        					stat1.close();
	        				}
	        				if(resultSet1!=null){
	        					resultSet1.close();
	        				}
	        				
	        				stat1=conn.prepareStatement("select * from CC_ACTIVITY_YEAR where FLAG='Y' and ACTIVITY_ID='"+COPY_ACTIVITY_ID+"'");
	        				resultSet1=stat1.executeQuery();
	        				while(resultSet1.next())
	        				{
	        					long slno1=0;
		        				stat2=conn.prepareStatement("SELECT NVL(MAX(ID),0)+1 SL_NO FROM CC_ACTIVITY_YEAR");
		        				resultSet2=stat2.executeQuery();
		        				if(resultSet2.next()){
		        					slno1=resultSet2.getLong("SL_NO");
		        				}
		        				stat2=conn.prepareStatement("INSERT INTO CC_ACTIVITY_YEAR(ID, ACTIVITY_ID, ACTIVITY_YEAR, FLAG, USER_ID, TDATE, M_USER_ID, MDATE) VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)");
		        				stat2.setLong(1,slno1);
		        				stat2.setLong(2,slno);
		        				stat2.setString(3,resultSet1.getString("ACTIVITY_YEAR"));
		        				stat2.setString(4,resultSet1.getString("FLAG"));
		        				stat2.setString(5,usrid);
		        				stat2.setString(6,usrid);
		        				st=stat2.executeUpdate();

		        				if(stat2!=null){
		        					stat2.close();
		        				}
		        				if(resultSet2!=null){
		        					resultSet2.close();
		        				}
		        				ctr++;
	        				}
	        				if(stat1!=null){
	        					stat1.close();
	        				}
	        				if(resultSet1!=null){
	        					resultSet1.close();
	        				}
	        				
	        				if(ctr>0 && st>0)
	           				{
	        				  conn.commit();	
	           				  addActionError("Record copied Successfully!!!");
	           		        }
	        				else
	        				{
	        					 conn.rollback();
	        					 addActionError("Record not copied Successfully!!!");
	        				}
       					}
       				}
       				catch(Exception e){
       					conn.rollback();
       					e.printStackTrace();
       					
       				} finally {
       					if (conn != null) {
       						conn.close();
       					}
       				}
       
			   return execute();		
			}
        
	public String getAausrid() {
		return aausrid;
	}
	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}
	public List<UserLinkDetailLoctBean> getLocationcodelist() {
		return locationcodelist;
	}
	public void setLocationcodelist(List<UserLinkDetailLoctBean> locationcodelist) {
		this.locationcodelist = locationcodelist;
	}
	public List<UserLinkDetailLoctBean> getDeptcodelist() {
		return deptcodelist;
	}
	public void setDeptcodelist(List<UserLinkDetailLoctBean> deptcodelist) {
		this.deptcodelist = deptcodelist;
	}
	public List<UserLinkDetailLoctBean> getSubdeptcodelist() {
		return subdeptcodelist;
	}
	public void setSubdeptcodelist(List<UserLinkDetailLoctBean> subdeptcodelist) {
		this.subdeptcodelist = subdeptcodelist;
	}
	public String getDeptcode() {
		return deptcode;
	}
	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}
	public String getACTIVITY_NAME() {
		return ACTIVITY_NAME;
	}
	public void setACTIVITY_NAME(String aCTIVITY_NAME) {
		ACTIVITY_NAME = aCTIVITY_NAME;
	}
	public String getALERT_TYPE1() {
		return ALERT_TYPE1;
	}
	public void setALERT_TYPE1(String aLERT_TYPE1) {
		ALERT_TYPE1 = aLERT_TYPE1;
	}
	public String getALERT_TYPE2() {
		return ALERT_TYPE2;
	}
	public void setALERT_TYPE2(String aLERT_TYPE2) {
		ALERT_TYPE2 = aLERT_TYPE2;
	}
	public String getALERT_TYPE3() {
		return ALERT_TYPE3;
	}
	public void setALERT_TYPE3(String aLERT_TYPE3) {
		ALERT_TYPE3 = aLERT_TYPE3;
	}
	public List getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(List lOCATION) {
		LOCATION = lOCATION;
	}
	public List getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(List dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	public List getSUBDEPARTMENT() {
		return SUBDEPARTMENT;
	}
	public void setSUBDEPARTMENT(List sUBDEPARTMENT) {
		SUBDEPARTMENT = sUBDEPARTMENT;
	}


	public List getChkdel() {
		return chkdel;
	}


	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}


	public List<ActivityBean> getDetaillist() {
		return detaillist;
	}


	public void setDetaillist(List<ActivityBean> detaillist) {
		this.detaillist = detaillist;
	}


	public String getSEARCH_ACTIVITY() {
		return SEARCH_ACTIVITY;
	}


	public void setSEARCH_ACTIVITY(String sEARCH_ACTIVITY) {
		SEARCH_ACTIVITY = sEARCH_ACTIVITY;
	}


	public String getSEARCH_LOCT() {
		return SEARCH_LOCT;
	}


	public void setSEARCH_LOCT(String sEARCH_LOCT) {
		SEARCH_LOCT = sEARCH_LOCT;
	}


	public String getSEARCH_DEPT() {
		return SEARCH_DEPT;
	}


	public void setSEARCH_DEPT(String sEARCH_DEPT) {
		SEARCH_DEPT = sEARCH_DEPT;
	}


	public String getSEARCH_SUBDEPT() {
		return SEARCH_SUBDEPT;
	}


	public void setSEARCH_SUBDEPT(String sEARCH_SUBDEPT) {
		SEARCH_SUBDEPT = sEARCH_SUBDEPT;
	}


	public String getE_ACTIVITY_NAME() {
		return E_ACTIVITY_NAME;
	}


	public void setE_ACTIVITY_NAME(String e_ACTIVITY_NAME) {
		E_ACTIVITY_NAME = e_ACTIVITY_NAME;
	}


	public String getE_ALERT_TYPE1() {
		return E_ALERT_TYPE1;
	}


	public void setE_ALERT_TYPE1(String e_ALERT_TYPE1) {
		E_ALERT_TYPE1 = e_ALERT_TYPE1;
	}


	public String getE_ALERT_TYPE2() {
		return E_ALERT_TYPE2;
	}


	public void setE_ALERT_TYPE2(String e_ALERT_TYPE2) {
		E_ALERT_TYPE2 = e_ALERT_TYPE2;
	}


	public String getE_ALERT_TYPE3() {
		return E_ALERT_TYPE3;
	}


	public void setE_ALERT_TYPE3(String e_ALERT_TYPE3) {
		E_ALERT_TYPE3 = e_ALERT_TYPE3;
	}


	public String getE_DEPARTMENT() {
		return E_DEPARTMENT;
	}


	public void setE_DEPARTMENT(String e_DEPARTMENT) {
		E_DEPARTMENT = e_DEPARTMENT;
	}


	public String getE_SUBDEPARTMENT() {
		return E_SUBDEPARTMENT;
	}


	public void setE_SUBDEPARTMENT(String e_SUBDEPARTMENT) {
		E_SUBDEPARTMENT = e_SUBDEPARTMENT;
	}


	public String getE_LOCATION() {
		return E_LOCATION;
	}


	public void setE_LOCATION(String e_LOCATION) {
		E_LOCATION = e_LOCATION;
	}


	public String getE_SLNO() {
		return E_SLNO;
	}


	public void setE_SLNO(String e_SLNO) {
		E_SLNO = e_SLNO;
	}


	public String getMODIFIED_BY() {
		return MODIFIED_BY;
	}


	public void setMODIFIED_BY(String mODIFIED_BY) {
		MODIFIED_BY = mODIFIED_BY;
	}


	public String getSTATUS_FLAG() {
		return STATUS_FLAG;
	}


	public void setSTATUS_FLAG(String sTATUS_FLAG) {
		STATUS_FLAG = sTATUS_FLAG;
	}

	public List<AlertBean> getActlist() {
		return actlist;
	}


	public void setActlist(List<AlertBean> actlist) {
		this.actlist = actlist;
	}


	public List getALERT_TYPE() {
		return ALERT_TYPE;
	}


	public void setALERT_TYPE(List aLERT_TYPE) {
		ALERT_TYPE = aLERT_TYPE;
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


	public List<UserLinkBean> getUserlinklist() {
		return userlinklist;
	}


	public void setUserlinklist(List<UserLinkBean> userlinklist) {
		this.userlinklist = userlinklist;
	}


	public List<UpdFlagBean> getUlocationcodelist() {
		return ulocationcodelist;
	}


	public void setUlocationcodelist(List<UpdFlagBean> ulocationcodelist) {
		this.ulocationcodelist = ulocationcodelist;
	}


	public List<UpdFlagBean> getUdeptcodelist() {
		return udeptcodelist;
	}


	public void setUdeptcodelist(List<UpdFlagBean> udeptcodelist) {
		this.udeptcodelist = udeptcodelist;
	}


	public List<UpdFlagBean> getUsubdeptcodelist() {
		return usubdeptcodelist;
	}


	public void setUsubdeptcodelist(List<UpdFlagBean> usubdeptcodelist) {
		this.usubdeptcodelist = usubdeptcodelist;
	}

	public String getE_ACTIVITY_ID() {
		return E_ACTIVITY_ID;
	}


	public void setE_ACTIVITY_ID(String e_ACTIVITY_ID) {
		E_ACTIVITY_ID = e_ACTIVITY_ID;
	}


	public String getACTIVITY_ID() {
		return ACTIVITY_ID;
	}


	public void setACTIVITY_ID(String aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}


	public String getUPD_FLAG() {
		return UPD_FLAG;
	}


	public void setUPD_FLAG(String uPD_FLAG) {
		UPD_FLAG = uPD_FLAG;
	}


	public String getALERT_TYPE_TAB() {
		return ALERT_TYPE_TAB;
	}


	public void setALERT_TYPE_TAB(String aLERT_TYPE_TAB) {
		ALERT_TYPE_TAB = aLERT_TYPE_TAB;
	}


	public List getALERT_DAY() {
		return ALERT_DAY;
	}


	public void setALERT_DAY(List aLERT_DAY) {
		ALERT_DAY = aLERT_DAY;
	}

	

	public List getALERT_TYPE_DAY() {
		return ALERT_TYPE_DAY;
	}


	public void setALERT_TYPE_DAY(List aLERT_TYPE_DAY) {
		ALERT_TYPE_DAY = aLERT_TYPE_DAY;
	}


	public List getEMP_TYPE() {
		return EMP_TYPE;
	}


	public void setEMP_TYPE(List eMP_TYPE) {
		EMP_TYPE = eMP_TYPE;
	}


	public String getE_ALERT_TYPE() {
		return E_ALERT_TYPE;
	}


	public void setE_ALERT_TYPE(String e_ALERT_TYPE) {
		E_ALERT_TYPE = e_ALERT_TYPE;
	}


	public String getU_SLNO() {
		return U_SLNO;
	}


	public void setU_SLNO(String u_SLNO) {
		U_SLNO = u_SLNO;
	}


	public List<String> getYEAR_CAL() {
		return YEAR_CAL;
	}


	public void setYEAR_CAL(List<String> yEAR_CAL) {
		YEAR_CAL = yEAR_CAL;
	}


	public List<String> getYEAR_STATUS() {
		return YEAR_STATUS;
	}


	public void setYEAR_STATUS(List<String> yEAR_STATUS) {
		YEAR_STATUS = yEAR_STATUS;
	}


	public List<YearBean> getYearBeans() {
		return yearBeans;
	}


	public void setYearBeans(List<YearBean> yearBeans) {
		this.yearBeans = yearBeans;
	}

    public List<UserLinkBean> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserLinkBean> userlist) {
        this.userlist = userlist;
    }

    public List getACC_FLAG() {
        return ACC_FLAG;
    }

    public void setACC_FLAG(List ACC_FLAG) {
        this.ACC_FLAG = ACC_FLAG;
    }

    public List getACC_ID() {
        return ACC_ID;
    }

    public void setACC_ID(List ACC_ID) {
        this.ACC_ID = ACC_ID;
    }

    public List getACC_TYPE() {
        return ACC_TYPE;
    }

    public void setACC_TYPE(List ACC_TYPE) {
        this.ACC_TYPE = ACC_TYPE;
    }

    public String getACC_TYPE_USR() {
        return ACC_TYPE_USR;
    }

    public void setACC_TYPE_USR(String ACC_TYPE_USR) {
        this.ACC_TYPE_USR = ACC_TYPE_USR;
    }

    public List getEmplist() {
        return emplist;
    }

    public void setEmplist(List emplist) {
        this.emplist = emplist;
    }

    public List getDelchkusr() {
        return delchkusr;
    }

    public void setDelchkusr(List delchkusr) {
        this.delchkusr = delchkusr;
    }


	public String getCOPY_ACTIVITY_ID() {
		return COPY_ACTIVITY_ID;
	}


	public void setCOPY_ACTIVITY_ID(String cOPY_ACTIVITY_ID) {
		COPY_ACTIVITY_ID = cOPY_ACTIVITY_ID;
	}


	public String getSEARCH_STATUS() {
		return SEARCH_STATUS;
	}


	public void setSEARCH_STATUS(String sEARCH_STATUS) {
		SEARCH_STATUS = sEARCH_STATUS;
	}

   
        
        
}
