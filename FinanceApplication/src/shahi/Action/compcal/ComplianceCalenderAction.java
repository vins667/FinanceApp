package shahi.Action.compcal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shahi.Action.compcal.bean.ActivityBean;
import shahi.Action.compcal.bean.ActivityListBean;
import shahi.Action.compcal.bean.ActivityYearBean;
import shahi.Action.compcal.bean.CompCalBean;
import shahi.Action.compcal.bean.CompCaldetailBean;
import shahi.Action.compcal.bean.CompanyBean;
import shahi.Action.compcal.bean.ComplianceCalenderBean;
import shahi.Action.compcal.bean.UserLinkDetailLoctBean;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHris;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;

public class ComplianceCalenderAction extends ActionSupport{
	private List<ActivityYearBean> activitylist;
	private List<ComplianceCalenderBean> detaillist;
	private String PARA_TYPE;
	private List<CompCalBean> COLCOUNTER;
	private List<CompanyBean> COMP_LIST;
	private String LOCATION;
	private String LOCATION_NAME;
	private String DEPARTMENT;
	private String DEPARTMENT_NAME;
	private String SUBDEPARTMENT;
	private String SUBDEPARTMENT_NAME;
	private String ACTIVITY;
	private String ACTIVITY_NAME;
	private String SEARCH_ACTIVITY;
	private String aausrid;
	private List<String> chkdel;
	
	private String DUE_DAY;
	private String SEARCH_ACT;
	private String ACTIVITY_ID;
	private List<CompCaldetailBean> condetaillist;
        private String DIS_ACTIVITY;
        private List COMP_LIST_ADD=new ArrayList();
        private String NEW_ADD_COMP;
        private List<String> newchkdel;
        private List DUE_DAY_N;
        private List DUE_DAY_S=new ArrayList();
	
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
		chkdel=null;
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return ERROR;
        }
		Connection conn = null;
		Connection conn1 = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        try {
            conn = new connection().getConnection();
            conn1 = new ConnectionShahiHris().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {     
        	detaillist = new ArrayList<ComplianceCalenderBean>();
        	String query=" ";
	    	if(SEARCH_ACT!=null && SEARCH_ACT.length()>0){
	    		query+=" AND CC_ACTIVITY_MASTER.ACTIVITY_NAME LIKE '"+SEARCH_ACT.toUpperCase()+"%'";
	    	}  
	    	stat=conn.prepareStatement("SELECT distinct CC_COMPLIANCE_CALENDER.ACTIVITY_ID, CC_ACTIVITY_MASTER.ACTIVITY_NAME,"
					+" CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID, CC_ACTIVITY_MASTER.SUBDEPT_ID,"
					+" CASE WHEN CALENDER_TYPE='M' THEN 'Monthly' WHEN CALENDER_TYPE='Q' THEN 'Quarterly'" 
					+" WHEN CALENDER_TYPE='H' THEN 'Half Yearly' ELSE 'Yearly' END  CALENDER_TYPE,min(CC_COMPLIANCE_CALENDER.USER_ID) USER_ID"
					+" FROM CC_COMPLIANCE_CALENDER,CC_ACTIVITY_MASTER WHERE CC_COMPLIANCE_CALENDER.ACTIVITY_ID=CC_ACTIVITY_MASTER.ID" +query +" "
                                          + " group by CC_COMPLIANCE_CALENDER.ACTIVITY_ID, CC_ACTIVITY_MASTER.ACTIVITY_NAME, "
                                        +" CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID, CC_ACTIVITY_MASTER.SUBDEPT_ID,CALENDER_TYPE order by 1,2,3");
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
	    		String tempflag=null;
	    		stat1=conn.prepareStatement("SELECT * FROM CC_COMPLIANCE_CALENDER WHERE ACTIVITY_ID=? AND ACT_FLAG='Y'");
	    		stat1.setString(1, result.getString("ACTIVITY_ID"));
	    		result1=stat1.executeQuery();
	    		if(result1.next()){
	    			tempflag="Y";
	    		}
	    		if(stat1!=null){
	    			stat1.close();
	    		}
	    		if(result1!=null){
	    			result1.close();
	    		}
	    		
	    		detaillist.add(new ComplianceCalenderBean(result.getLong("ACTIVITY_ID"),result.getString("ACTIVITY_NAME"),result.getString("LOCATION_CODE"),result.getLong("DEPT_ID"),deptuser,result.getLong("SUBDEPT_ID"),subdeptuser,result.getString("CALENDER_TYPE"),result.getString("USER_ID"),tuser,tempflag));
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
		return SUCCESS;
	}
	
	public String edit() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "editco";
        }
        
        
        chkdel=null;
        if(ACTIVITY_ID!=null && ACTIVITY_ID.length()>0){
        	Connection conn=null;
                Connection conn1=null;
            try{
            	conn=new connection().getConnection();
            	conn1=new ConnectionShahiHris().getConnection();
            }
            catch(Exception e)
            {
            	addActionError("Compliance ActivityLinkDetailAction.execute()"+e);
            	return "editco";
            }
            PreparedStatement stat = null;
            ResultSet result = null;
            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            try{   

                  
	    		stat1=conn.prepareStatement("SELECT * FROM CC_COMPLIANCE_CALENDER WHERE ACTIVITY_ID=? AND ACT_FLAG='Y'");
	    		stat1.setString(1, ACTIVITY_ID);
	    		result1=stat1.executeQuery();
	    		if(result1.next()){
	    			DIS_ACTIVITY="Yes";
	    		}
	    		if(stat1!=null){
	    			stat1.close();
	    		}
	    		if(result1!=null){
	    			result1.close();
	    		}
                
                
    	    	stat=conn.prepareStatement("SELECT CC_ACTIVITY_MASTER.ID,CC_ACTIVITY_MASTER.ACTIVITY_ID, CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_MASTER.LOCATION_CODE, "+
            " CC_ACTIVITY_MASTER.DEPT_ID, CC_ACTIVITY_MASTER.SUBDEPT_ID,CALENDER_TYPE,CC_COMPLIANCE_CALENDER.USER_ID,min(DUE_DAYS) DUE_DAYS"+
            " FROM CC_COMPLIANCE_CALENDER,CC_ACTIVITY_MASTER WHERE CC_COMPLIANCE_CALENDER.ACTIVITY_ID=CC_ACTIVITY_MASTER.ID "+
            " AND CC_ACTIVITY_MASTER.ID=? group by CC_ACTIVITY_MASTER.ID,CC_ACTIVITY_MASTER.ACTIVITY_ID, CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_MASTER.LOCATION_CODE, "+
            " CC_ACTIVITY_MASTER.DEPT_ID, CC_ACTIVITY_MASTER.SUBDEPT_ID,CALENDER_TYPE,CC_COMPLIANCE_CALENDER.USER_ID");
    	    	stat.setString(1, ACTIVITY_ID);
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
        		
    	    		LOCATION=result.getString("LOCATION_CODE");
    	    		LOCATION_NAME=result.getString("LOCATION_CODE");
    	    		DEPARTMENT=result.getString("DEPT_ID");
    	    		DEPARTMENT_NAME=deptuser;
    	    		SUBDEPARTMENT=result.getString("SUBDEPT_ID");
    	    		SUBDEPARTMENT_NAME=subdeptuser;
    	    		ACTIVITY=result.getString("ACTIVITY_ID");
    	    		ACTIVITY_NAME=result.getString("ACTIVITY_NAME");
    	    		PARA_TYPE=result.getString("CALENDER_TYPE");
    	    		DUE_DAY=result.getString("DUE_DAYS");
    	        }
    	    	if(stat!=null){
    	    		stat.close();
    	    	}
    	    	if(result!=null){
    	    		result.close();
    	    	}
                
                stat=conn.prepareStatement("select calender_type_code,nvl(due_days,0) due_days,max(nvl(ACT_FLAG,'N'))  ACT_FLAG from CC_COMPLIANCE_CALENDER where activity_id=?  group by calender_type_code,due_days order by 1");
    	    	stat.setString(1, ACTIVITY_ID);
    	    	result = stat.executeQuery();
    	    	while(result.next()){
                    String ACTFLAG="false";
                    if(result.getString("ACT_FLAG")!=null && result.getString("ACT_FLAG").equals("Y"))
                    {
                    ACTFLAG="true";
                    }
    	    	DUE_DAY_S.add(new CompCalBean(result.getString("calender_type_code"), result.getString("due_days"),ACTFLAG));	
    	        }
    	    	if(stat!=null){
    	    		stat.close();
    	    	}
    	    	if(result!=null){
    	    		result.close();
    	    	}
                
                
                
    	    	COLCOUNTER=new ArrayList();
    			COMP_LIST=new ArrayList<CompanyBean>();
    			stat=conn.prepareStatement("SELECT DISTINCT CC_COMPLIANCE_CALENDER.COMPANY_ID,CC_COMPANY_MASTER.NAME,CC_COMPANY_MASTER.ORDERBY from CC_COMPLIANCE_CALENDER,CC_COMPANY_MASTER"
    					+" WHERE CC_COMPLIANCE_CALENDER.COMPANY_ID=CC_COMPANY_MASTER.ID AND CC_COMPLIANCE_CALENDER.ACTIVITY_ID=?  ORDER BY CC_COMPANY_MASTER.ORDERBY,CC_COMPANY_MASTER.NAME");
    			stat.setString(1, ACTIVITY_ID);
    	    	result = stat.executeQuery();
    	    	while(result.next()){
    	    		COMP_LIST.add(new CompanyBean(result.getLong("COMPANY_ID"), result.getString("NAME"), null, result.getString("ORDERBY"), null, null, null, null));
    	    	}
    	    	if(stat!=null){
    	    		stat.close();
    	    	}
    	    	if(result!=null){
    	    		result.close();
    	    	}
               
    	    	condetaillist=new ArrayList<CompCaldetailBean>();
    	    	stat=conn.prepareStatement("SELECT ACTIVITY_ID, COMPANY_ID, CALENDER_TYPE_CODE,ACT_FLAG FROM CC_COMPLIANCE_CALENDER WHERE ACTIVITY_ID=?");
    	    	stat.setString(1, ACTIVITY_ID);
    	    	result=stat.executeQuery();
    	    	while(result.next()){
    	    		condetaillist.add(new CompCaldetailBean(result.getString("ACTIVITY_ID"), result.getString("COMPANY_ID"), result.getString("CALENDER_TYPE_CODE"),result.getString("ACT_FLAG")));
    	    	}
    	    	
    			if(PARA_TYPE!=null && PARA_TYPE.equals("M")){
    				COLCOUNTER.add(new CompCalBean("1", "April"));	
    				COLCOUNTER.add(new CompCalBean("2", "May"));	
    				COLCOUNTER.add(new CompCalBean("3", "June"));	
    				COLCOUNTER.add(new CompCalBean("4", "July"));	
    				COLCOUNTER.add(new CompCalBean("5", "August"));	
    				COLCOUNTER.add(new CompCalBean("6", "September"));	
    				COLCOUNTER.add(new CompCalBean("7", "October"));	
    				COLCOUNTER.add(new CompCalBean("8", "November"));
    				COLCOUNTER.add(new CompCalBean("9", "December"));
    				COLCOUNTER.add(new CompCalBean("10", "January"));	
    				COLCOUNTER.add(new CompCalBean("11", "February"));	
    				COLCOUNTER.add(new CompCalBean("12", "March"));	
    			}
    			else if(PARA_TYPE!=null && PARA_TYPE.equals("Q")){
    				COLCOUNTER.add(new CompCalBean("1", "Quarter 1"));	
    				COLCOUNTER.add(new CompCalBean("2", "Quarter 2"));	
    				COLCOUNTER.add(new CompCalBean("3", "Quarter 3"));	
    				COLCOUNTER.add(new CompCalBean("4", "Quarter 4"));	
    			}
    			else if(PARA_TYPE!=null && PARA_TYPE.equals("H")){
    				COLCOUNTER.add(new CompCalBean("1", "Half Year 1"));	
    				COLCOUNTER.add(new CompCalBean("2", "Half Year 2"));	
    			}
    			else if(PARA_TYPE!=null && PARA_TYPE.equals("Y")){
    				COLCOUNTER.add(new CompCalBean("1", "Year"));
    			}
                        
                        
                stat=conn.prepareStatement("SELECT ID, NAME, FLAG, ORDERBY, USER_ID, TO_CHAR(TDATE,'dd/MM/yyyy') TDATE, MUSER_ID, TO_CHAR(MDATE,'dd/MM/yyyy') MDATE FROM CC_COMPANY_MASTER WHERE FLAG='Y' and id not in(SELECT DISTINCT COMPANY_ID from CC_COMPLIANCE_CALENDER where ACTIVITY_ID=?) ORDER BY ORDERBY,NAME");
	    	stat.setString(1, ACTIVITY_ID);
                result = stat.executeQuery();
	    	while(result.next()){
	    	COMP_LIST_ADD.add(new CompanyBean(result.getLong("ID"), result.getString("NAME"), result.getString("FLAG"), result.getString("ORDERBY"), result.getString("USER_ID"), result.getString("TDATE"), result.getString("MUSER_ID"), result.getString("MDATE")));
	    	}       
                        
            }
    	    catch (Exception e) {
    	    	System.out.println("UserMasterAction " + e);
    		} finally {
    			if (conn != null) {
    				conn.close();
    			}
    			if (conn1 != null) {
    				conn1.close();
    			}
    		}
        }
        
        return "editco";
	}
	
	public String act() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "actdp";
        }
		activitylist=new ArrayList<ActivityYearBean>();
		Connection conn=null;
        Connection conn1=null;
        try{
        	conn=new connection().getConnection();
        	conn1=new ConnectionShahiHris().getConnection();
        }
        catch(Exception e){
        	addActionError("Compliance ActivityLinkDetailAction.execute()"+e);
        	return "actdp";
        }

        PreparedStatement stat=null;
        ResultSet result=null;
        PreparedStatement stat1=null;
        ResultSet result1=null;
        try{
	    	String query=" 1=1 ";
	    	if(SEARCH_ACTIVITY!=null && SEARCH_ACTIVITY.length()>0){
	    		query+=" AND ACTIVITY_NAME LIKE '"+SEARCH_ACTIVITY.toUpperCase()+"%'";
	    	}    	
	    	stat=conn.prepareStatement("SELECT CC_ACTIVITY_MASTER.ID,CC_ACTIVITY_MASTER.ACTIVITY_ID, CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_MASTER.LOCATION_CODE,CC_ACTIVITY_MASTER.DEPT_ID," 
	    			+" CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_ACTIVITY_MASTER.ALERT_TYPE1,CC_ACTIVITY_MASTER.ALERT_TYPE2,CC_ACTIVITY_MASTER.ALERT_TYPE3,CC_ACTIVITY_MASTER.FLAG,CC_ACTIVITY_MASTER.USER_ID,"
	    			+" TO_CHAR(CC_ACTIVITY_MASTER.TDATE,'dd/MM/yyyy') TDATE,MUSER_ID,TO_CHAR(CC_ACTIVITY_MASTER.MDATE,'dd/MM/yyyy') MDATE,CC_ACTIVITY_MASTER.UPD_FLAG " 
	    			+" FROM CC_ACTIVITY_MASTER WHERE "+query +" and CC_ACTIVITY_MASTER.CAL_FLAG is null");
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
    		
	    		activitylist.add(new ActivityYearBean(result.getLong("ID"),result.getLong("ACTIVITY_ID"),result.getString("ACTIVITY_NAME"),result.getString("LOCATION_CODE"),result.getString("DEPT_ID"),deptuser,result.getString("SUBDEPT_ID"),subdeptuser,result.getString("ALERT_TYPE1"),result.getString("ALERT_TYPE2"),result.getString("ALERT_TYPE3"),result.getString("FLAG"),tuser,result.getString("TDATE"),result.getString("MUSER_ID"),result.getString("MDATE"),result.getString("UPD_FLAG")));
	        }
        }
	    catch (Exception e) {
	    	System.out.println("UserMasterAction " + e);
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (conn1 != null) {
				conn1.close();
			}
		}
		return "actdp";
	}
	
	public String newco(){
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
		return "newco";
	}
	public String search() throws Exception{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
		COLCOUNTER=new ArrayList();
		COMP_LIST=new ArrayList<CompanyBean>();
		if(PARA_TYPE!=null && PARA_TYPE.equals("M")){
			COLCOUNTER.add(new CompCalBean("1", "April"));	
			COLCOUNTER.add(new CompCalBean("2", "May"));	
			COLCOUNTER.add(new CompCalBean("3", "June"));	
			COLCOUNTER.add(new CompCalBean("4", "July"));	
			COLCOUNTER.add(new CompCalBean("5", "August"));	
			COLCOUNTER.add(new CompCalBean("6", "September"));	
			COLCOUNTER.add(new CompCalBean("7", "October"));	
			COLCOUNTER.add(new CompCalBean("8", "November"));
			COLCOUNTER.add(new CompCalBean("9", "December"));
			COLCOUNTER.add(new CompCalBean("10", "January"));	
			COLCOUNTER.add(new CompCalBean("11", "February"));	
			COLCOUNTER.add(new CompCalBean("12", "March"));	
		}
		else if(PARA_TYPE!=null && PARA_TYPE.equals("Q")){
			COLCOUNTER.add(new CompCalBean("1", "Quarter 1"));	
			COLCOUNTER.add(new CompCalBean("2", "Quarter 2"));	
			COLCOUNTER.add(new CompCalBean("3", "Quarter 3"));	
			COLCOUNTER.add(new CompCalBean("4", "Quarter 4"));	
		}
		else if(PARA_TYPE!=null && PARA_TYPE.equals("H")){
			COLCOUNTER.add(new CompCalBean("1", "Half Year 1"));	
			COLCOUNTER.add(new CompCalBean("2", "Half Year 2"));	
		}
		else if(PARA_TYPE!=null && PARA_TYPE.equals("Y")){
			COLCOUNTER.add(new CompCalBean("1", "Year"));
		}
		Connection conn = null;
		PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
                stat=conn.prepareStatement("SELECT ID, NAME, FLAG, ORDERBY, USER_ID, TO_CHAR(TDATE,'dd/MM/yyyy') TDATE, MUSER_ID, TO_CHAR(MDATE,'dd/MM/yyyy') MDATE FROM CC_COMPANY_MASTER WHERE FLAG='Y' ORDER BY ORDERBY,NAME");
	    	result = stat.executeQuery();
	    	while(result.next()){
	    		COMP_LIST.add(new CompanyBean(result.getLong("ID"), result.getString("NAME"), result.getString("FLAG"), result.getString("ORDERBY"), result.getString("USER_ID"), result.getString("TDATE"), result.getString("MUSER_ID"), result.getString("MDATE")));
	    	}
        }
    	catch (Exception e) {
	        System.out.println("UserMasterAction " + e);
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	    }
		return "newco";
	}
	
	public String save() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
		if(chkdel!=null && chkdel.size()>0){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		ResultSet result2 = null;
		try {
		    conn = new connection().getConnection();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
		 try {
			 int ctr=0;
			 stat=conn.prepareStatement("SELECT * FROM CC_COMPLIANCE_CALENDER WHERE ACTIVITY_ID=?");
			 stat.setString(1, ACTIVITY);
			 result=stat.executeQuery();
			 if(result.next()){
				 addActionError("Calender Already created!!!");
				 return "newco";
			 }
			 for(int i=0;i<chkdel.size();i++){
				 String[] lst = chkdel.get(i).split("--");
                                  
				 if(lst.length==2){					 
						 long id=0;
						 stat2=conn.prepareStatement("SELECT NVL(MAX(ID),0)+1 ID FROM CC_COMPLIANCE_CALENDER");
						 result2=stat2.executeQuery();
						 if(result2.next()){
							 id=result2.getLong("ID");
						 }
                                                 if(result2!=null)
                                                 {
                                                 result2.close();
                                                 }
                                                 if(stat2!=null)
                                                 {
                                                 stat2.close();
                                                 }
						 String DUE_DAY_temp="0";
                                                 for(int m=0; m < DUE_DAY_N.size(); m++)
                                                 {
                                                     
                                                     
                                                 String[] dlst = DUE_DAY_N.get(m).toString().split("--");
                                                 if(lst[0].equals(dlst[0]))
                                                 {
                                                 DUE_DAY_temp=dlst[1];
                                                
                                                 break;
                                                 }
                                                 }
                                                 
                                                // System.out.println(DUE_DAY_temp + "::"+lst[0]+"::"+lst[1]);
                                                 
						 stat1=conn.prepareStatement("INSERT INTO CC_COMPLIANCE_CALENDER (ID,ACTIVITY_ID,COMPANY_ID,CALENDER_TYPE,CALENDER_TYPE_CODE,DUE_DAYS,USER_ID,TDATE,MUSER_ID,MDATE)"
								 +" VALUES(?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)");
						 stat1.setLong(1, id);
						 stat1.setString(2, ACTIVITY);
						 stat1.setString(3, lst[1]);
						 stat1.setString(4, PARA_TYPE);
						 stat1.setString(5, lst[0]);
						// stat1.setString(6, DUE_DAY);
                                                 stat1.setString(6, DUE_DAY_temp);
						 stat1.setString(7, usrid);
						 stat1.setString(8, usrid);
						 int st=stat1.executeUpdate();
						 if(st>0){
							 ++ctr;
						 }
						 if(stat1!=null){
							 stat1.close();
						 }
				 }
			 }
			 if(ctr>0){
				 stat=conn.prepareStatement("UPDATE CC_ACTIVITY_MASTER SET CAL_FLAG='Y' WHERE ID=?");
				 stat.setString(1, ACTIVITY);
				 stat.executeUpdate();
				 if(stat!=null){
					 stat.close();
				 }
				 addActionError("Record Saved Successfully");
                                
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
		ACTIVITY_ID=ACTIVITY;
		edit();
		return "editco";
	}
	
	public String delete() throws SQLException, Exception{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return  execute();
        }
        Connection conn=null;
        try{
        	conn=new connection().getConnection();
        }
        catch(Exception e){
        	addActionError("ComplianceCalenderDetail.update()"+e);
        	return  execute();
        }
        PreparedStatement stat=null;
        try{
        	int ctr=0;
        	if(chkdel!=null && chkdel.size()>0){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_COMPLIANCE_CALENDER WHERE ACTIVITY_ID=?");
        			stat.setString(1, chkdel.get(i).toString());
        			int st=stat.executeUpdate();
        			if(st>0){
        				++ctr;
                                            stat=conn.prepareStatement("UPDATE CC_ACTIVITY_MASTER SET CAL_FLAG=null WHERE ID=?");
                                            stat.setString(1, chkdel.get(i).toString());
                                            stat.executeUpdate();
                                            if(stat!=null){
                                            stat.close();
                                            }
        			}
        		}
        		if(ctr>0){
        			addActionError("Record Deleted Successfully");
        		}
        	}
        }
        catch(SQLException se){
        	addActionError("ComplianceCalenderDetail.update()"+se);
        	return  execute();
        }
        catch(Exception e){
        	addActionError("Compliance CompanyMasterAction.update()"+e);
        	return  execute();
        }
        finally{
        	if(conn!=null){
        		conn.close();
        	}
        	if(stat!=null){
        		stat.close();
        	}
        }
        return execute();
	}
	
	public String update() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "editco";
        }
		if(chkdel!=null && chkdel.size()>0){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		ResultSet result2 = null;
		try {
		    conn = new connection().getConnection();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
		 try {
			 int ctr=0;
                         
			 stat=conn.prepareStatement("DELETE FROM CC_COMPLIANCE_CALENDER WHERE ACTIVITY_ID=? AND ACT_FLAG IS NULL");
			 stat.setString(1, ACTIVITY);
			 stat.executeUpdate();
			 for(int i=0;i<chkdel.size();i++){
				 String[] lst = chkdel.get(i).split("--");
				 if(lst.length==2){
                                     
                                                String DUE_DAY_temp="0";
                                                 for(int m=0; m < DUE_DAY_N.size(); m++)
                                                 {
                                                     
                                                     
                                                 String[] dlst = DUE_DAY_N.get(m).toString().split("--");
                                                 if(lst[0].equals(dlst[0]))
                                                 {
                                                 DUE_DAY_temp=dlst[1];
                                                
                                                 break;
                                                 }
                                                 }
                                     
						 long id=0;
						 stat2=conn.prepareStatement("SELECT NVL(MAX(ID),0)+1 ID FROM CC_COMPLIANCE_CALENDER");
						 result2=stat2.executeQuery();
						 if(result2.next()){
							 id=result2.getLong("ID");
						 }
						 
						 stat1=conn.prepareStatement("INSERT INTO CC_COMPLIANCE_CALENDER (ID,ACTIVITY_ID,COMPANY_ID,CALENDER_TYPE,CALENDER_TYPE_CODE,DUE_DAYS,USER_ID,TDATE,MUSER_ID,MDATE)"
								 +" VALUES(?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)");
						 stat1.setLong(1, id);
						 stat1.setString(2, ACTIVITY);
						 stat1.setString(3, lst[1]);
						 stat1.setString(4, PARA_TYPE);
						 stat1.setString(5, lst[0]);
						 stat1.setString(6, DUE_DAY_temp);
						 stat1.setString(7, usrid);
						 stat1.setString(8, usrid);
						 int st=stat1.executeUpdate();
						 if(st>0){
							 ++ctr;
						 }
						 if(stat1!=null){
							 stat1.close();
						 }
				 }
			 }
                         
                         if(NEW_ADD_COMP!=null && NEW_ADD_COMP.length()>0 && newchkdel!=null &&
                                 newchkdel.size()>0)
                         {
                             for(int i=0;i<newchkdel.size();i++){
                                 
                                 
                                                String DUE_DAY_temp="0";
                                                 for(int m=0; m < DUE_DAY_N.size(); m++)
                                                 {
                                                     
                                                     
                                                 String[] dlst = DUE_DAY_N.get(m).toString().split("--");
                                                 if(newchkdel.get(i).toString().equals(dlst[0]))
                                                 {
                                                 DUE_DAY_temp=dlst[1];
                                                
                                                 break;
                                                 }
                                                 }
                                 
                                                long id=0;
						 stat2=conn.prepareStatement("SELECT NVL(MAX(ID),0)+1 ID FROM CC_COMPLIANCE_CALENDER");
						 result2=stat2.executeQuery();
						 if(result2.next()){
							 id=result2.getLong("ID");
						 }
						 
						 stat1=conn.prepareStatement("INSERT INTO CC_COMPLIANCE_CALENDER (ID,ACTIVITY_ID,COMPANY_ID,CALENDER_TYPE,CALENDER_TYPE_CODE,DUE_DAYS,USER_ID,TDATE,MUSER_ID,MDATE)"
								 +" VALUES(?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)");
						 stat1.setLong(1, id);
						 stat1.setString(2, ACTIVITY);
						 stat1.setString(3, NEW_ADD_COMP);
						 stat1.setString(4, PARA_TYPE);
						 stat1.setString(5, newchkdel.get(i));
						 stat1.setString(6, DUE_DAY_temp);
						 stat1.setString(7, usrid);
						 stat1.setString(8, usrid);
						 int st=stat1.executeUpdate();
						 if(st>0){
							 ++ctr;
						 }
						 if(stat1!=null){
							 stat1.close();
						 }
                             }
                         }
                         
			 if(ctr>0){
				 stat=conn.prepareStatement("UPDATE CC_ACTIVITY_MASTER SET CAL_FLAG='Y' WHERE ID=?");
				 stat.setString(1, ACTIVITY);
				 stat.executeUpdate();
				 if(stat!=null){
					 stat.close();
				 }
				 addActionError("Record Updated Successfully");
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
                
		ACTIVITY_ID=ACTIVITY;
                
		edit();
		return "editco";
	}

	public List<ActivityYearBean> getActivitylist() {
		return activitylist;
	}

	public void setActivitylist(List<ActivityYearBean> activitylist) {
		this.activitylist = activitylist;
	}

	public String getPARA_TYPE() {
		return PARA_TYPE;
	}

	public void setPARA_TYPE(String pARA_TYPE) {
		PARA_TYPE = pARA_TYPE;
	}

	public List<CompCalBean> getCOLCOUNTER() {
		return COLCOUNTER;
	}

	public void setCOLCOUNTER(List<CompCalBean> cOLCOUNTER) {
		COLCOUNTER = cOLCOUNTER;
	}

	public List<CompanyBean> getCOMP_LIST() {
		return COMP_LIST;
	}

	public void setCOMP_LIST(List<CompanyBean> cOMP_LIST) {
		COMP_LIST = cOMP_LIST;
	}

	public String getLOCATION() {
		return LOCATION;
	}

	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}

	public String getLOCATION_NAME() {
		return LOCATION_NAME;
	}

	public void setLOCATION_NAME(String lOCATION_NAME) {
		LOCATION_NAME = lOCATION_NAME;
	}

	public String getDEPARTMENT() {
		return DEPARTMENT;
	}

	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}

	public String getDEPARTMENT_NAME() {
		return DEPARTMENT_NAME;
	}

	public void setDEPARTMENT_NAME(String dEPARTMENT_NAME) {
		DEPARTMENT_NAME = dEPARTMENT_NAME;
	}

	public String getSUBDEPARTMENT() {
		return SUBDEPARTMENT;
	}

	public void setSUBDEPARTMENT(String sUBDEPARTMENT) {
		SUBDEPARTMENT = sUBDEPARTMENT;
	}

	public String getSUBDEPARTMENT_NAME() {
		return SUBDEPARTMENT_NAME;
	}

	public void setSUBDEPARTMENT_NAME(String sUBDEPARTMENT_NAME) {
		SUBDEPARTMENT_NAME = sUBDEPARTMENT_NAME;
	}

	public String getACTIVITY() {
		return ACTIVITY;
	}

	public void setACTIVITY(String aCTIVITY) {
		ACTIVITY = aCTIVITY;
	}

	public String getACTIVITY_NAME() {
		return ACTIVITY_NAME;
	}

	public void setACTIVITY_NAME(String aCTIVITY_NAME) {
		ACTIVITY_NAME = aCTIVITY_NAME;
	}

	public String getSEARCH_ACTIVITY() {
		return SEARCH_ACTIVITY;
	}

	public void setSEARCH_ACTIVITY(String sEARCH_ACTIVITY) {
		SEARCH_ACTIVITY = sEARCH_ACTIVITY;
	}

	public String getAausrid() {
		return aausrid;
	}

	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}

	public List<String> getChkdel() {
		return chkdel;
	}

	public void setChkdel(List<String> chkdel) {
		this.chkdel = chkdel;
	}

	public String getDUE_DAY() {
		return DUE_DAY;
	}

	public void setDUE_DAY(String dUE_DAY) {
		DUE_DAY = dUE_DAY;
	}

	public List<ComplianceCalenderBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<ComplianceCalenderBean> detaillist) {
		this.detaillist = detaillist;
	}

	public String getSEARCH_ACT() {
		return SEARCH_ACT;
	}

	public void setSEARCH_ACT(String sEARCH_ACT) {
		SEARCH_ACT = sEARCH_ACT;
	}

	public String getACTIVITY_ID() {
		return ACTIVITY_ID;
	}

	public void setACTIVITY_ID(String aCTIVITY_ID) {
		ACTIVITY_ID = aCTIVITY_ID;
	}

	public List<CompCaldetailBean> getCondetaillist() {
		return condetaillist;
	}

	public void setCondetaillist(List<CompCaldetailBean> condetaillist) {
		this.condetaillist = condetaillist;
	}

    public String getDIS_ACTIVITY() {
        return DIS_ACTIVITY;
    }

    public void setDIS_ACTIVITY(String DIS_ACTIVITY) {
        this.DIS_ACTIVITY = DIS_ACTIVITY;
    }

    public List getCOMP_LIST_ADD() {
        return COMP_LIST_ADD;
    }

    public void setCOMP_LIST_ADD(List COMP_LIST_ADD) {
        this.COMP_LIST_ADD = COMP_LIST_ADD;
    }

    public String getNEW_ADD_COMP() {
        return NEW_ADD_COMP;
    }

    public void setNEW_ADD_COMP(String NEW_ADD_COMP) {
        this.NEW_ADD_COMP = NEW_ADD_COMP;
    }

    public List<String> getNewchkdel() {
        return newchkdel;
    }

    public void setNewchkdel(List<String> newchkdel) {
        this.newchkdel = newchkdel;
    }

    public List getDUE_DAY_N() {
        return DUE_DAY_N;
    }

    public void setDUE_DAY_N(List DUE_DAY_N) {
        this.DUE_DAY_N = DUE_DAY_N;
    }

    public List getDUE_DAY_S() {
        return DUE_DAY_S;
    }

    public void setDUE_DAY_S(List DUE_DAY_S) {
        this.DUE_DAY_S = DUE_DAY_S;
    }
    
    
        
	
}
