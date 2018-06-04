package shahi.Action.compcal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import shahi.Action.compcal.bean.CompCalBean;
import shahi.Action.compcal.bean.ComplianceCalenderDetailBean;
import shahi.Action.compcal.bean.ComplianceCalenderDtBean;
import shahi.Action.database.connection;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ComplianceCalenderDetailAction extends ActionSupport{
	private String aausrid;
	private String SEARCH_ACTIVITY;
	private List COMP_CODE;
	private List COMP_NAME;
	private List DUE_DATE;
	private List SUBMISSION_DATE;
	private List ACTUAL_DATE;
	private List AMOUNT;
	private List CHALLAN_NO;
	private List BANK_CODE;
	private List REMARKS;
	private List COMPANY_NAME;
	private List LOCATION_NAME;
	private List DEPT_NAME;
	private List SUBDEPT_NAME;
	private List<ComplianceCalenderDetailBean> detaillist;
	private List<ComplianceCalenderDtBean> activitylist;
	private String TINDEX;
	private String E_ID;
	private String E_COMPANY_NAME;
	private String E_LOCATION_NAME;
	private String E_DEPT_NAME;
	private String E_SUBDEPT_NAME;	
	private String E_COMP_CODE;
	private String E_COMP_NAME;
	private String E_DUE_DATE;
	private String E_SUBMISSION_DATE;
	private String E_ACTUAL_DATE;
	private String E_AMOUNT;
	private String E_CHALLAN_NO;
	private String E_BANK_CODE;
	private String E_REMARKS;
	private List chkdel;
        private List CALENDER_TYPE;
	
	
	
	
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
	
	public String execute() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
        detaillist=new ArrayList<ComplianceCalenderDetailBean>();
        Connection conn=null;
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        PreparedStatement statinsert=null;
        ResultSet resultSet=null;
        ResultSet resultSet1=null;
        try {
		    conn = new connection().getConnection();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
        try{
        	String query=" ";
        	if(SEARCH_ACTIVITY!=null && SEARCH_ACTIVITY.length()>0){
        		query+=" AND CC_ACTIVITY_MASTER.ACTIVITY_NAME LIKE '"+SEARCH_ACTIVITY+"%' ";
        	}
        	stat=conn.prepareStatement("SELECT CC_COMPLIANCE_CALENDER_DETAIL.ID,CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_COMPLIANCE_CALENDER_DETAIL.COMPLIANCE_ID,to_char(CC_COMPLIANCE_CALENDER_DETAIL.DUE_DATE,'dd/MM/yyyy') DUE_DATE," 
        			+" to_char(CC_COMPLIANCE_CALENDER_DETAIL.SUBMISSION_DATE,'dd/MM/yyyy') SUBMISSION_DATE,"
					+" to_char(CC_COMPLIANCE_CALENDER_DETAIL.ACTUAL_DATE,'dd/MM/yyyy') ACTUAL_DATE,CC_COMPLIANCE_CALENDER_DETAIL.AMOUNT,CC_COMPLIANCE_CALENDER_DETAIL.CHALLAN_NO,CC_COMPLIANCE_CALENDER_DETAIL.BANK_CODE,"
					+" CC_COMPLIANCE_CALENDER_DETAIL.REMARKS,CC_COMPLIANCE_CALENDER_DETAIL.USER_ID,to_char(CC_COMPLIANCE_CALENDER_DETAIL.TDATE,'dd/MM/yyyy') TDATE,CC_COMPLIANCE_CALENDER_DETAIL.MUSER_ID,"
					+" to_char(CC_COMPLIANCE_CALENDER_DETAIL.MDATE,'dd/MM/yyyy') MDATE,CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID,CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID"
					+" FROM CC_COMPLIANCE_CALENDER_DETAIL,CC_COMPLIANCE_CALENDER,CC_ACTIVITY_MASTER"
					+" WHERE CC_COMPLIANCE_CALENDER_DETAIL.COMPLIANCE_ID=CC_COMPLIANCE_CALENDER.ID AND CC_COMPLIANCE_CALENDER.ACTIVITY_ID=CC_ACTIVITY_MASTER.ID "+query 
                                         +" and CC_ACTIVITY_MASTER.ID in(select ACT_ID from CC_ACTIVITY_ACCESS_MASTER where EMP_CODE=? and FLAG='Y')"
                                       );
                stat.setString(1, usrid);
        	resultSet=stat.executeQuery();
        	while(resultSet.next()){
        		String compname="";
        		stat1=conn.prepareStatement("SELECT NAME FROM CC_COMPANY_MASTER WHERE ID=?");
        		stat1.setString(1, resultSet.getString("COMPANY_ID"));
        		resultSet1=stat1.executeQuery();
        		if(resultSet1.next()){
        			compname=resultSet1.getString("NAME");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(resultSet1!=null){
        			resultSet1.close();
        		}
        		stat1=conn.prepareStatement("SELECT NAME FROM CC_DEPT_MASTER WHERE ID=?");
	    		stat1.setString(1, resultSet.getString("DEPT_ID"));
	    		resultSet1=stat1.executeQuery();
	    		String deptuser="";
	    		if(resultSet1.next()){
	    			deptuser=resultSet1.getString("NAME");
	    		}
	    		if(stat1!=null){
	    			stat1.close();
	    		}
	    		if(resultSet1!=null){
	    			resultSet1.close();
	    		}
	    		
	    		stat1=conn.prepareStatement("SELECT NAME FROM CC_SUB_DEPT_MASTER WHERE ID=?");
	    		stat1.setString(1, resultSet.getString("SUBDEPT_ID"));
	    		resultSet1=stat1.executeQuery();
	    		String subdeptuser="";
	    		if(resultSet1.next()){
	    			subdeptuser=resultSet1.getString("NAME");
	    		}
	    		if(stat1!=null){
	    			stat1.close();
	    		}
	    		if(resultSet1!=null){
	    			resultSet1.close();
	    		}
        		detaillist.add(new ComplianceCalenderDetailBean(resultSet.getLong("ID"),resultSet.getString("ACTIVITY_NAME"),resultSet.getLong("COMPLIANCE_ID"),resultSet.getString("DUE_DATE"),resultSet.getString("SUBMISSION_DATE"),resultSet.getString("ACTUAL_DATE"),resultSet.getString("AMOUNT"),resultSet.getString("CHALLAN_NO"),resultSet.getString("BANK_CODE"),resultSet.getString("REMARKS"),resultSet.getString("USER_ID"),resultSet.getString("TDATE"),resultSet.getString("MUSER_ID"),resultSet.getString("MDATE"),resultSet.getString("LOCATION_CODE"),deptuser,subdeptuser,compname));
        	}
        }
        catch(Exception e){
        	 System.out.println("UserMasterAction " + e);
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	    }
		return SUCCESS;
	}
	
	public String newco(){
		return "newco";
	}
	
	
	public String edit() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "editco";
        }
		if(E_ID!=null && E_ID.length()>0){
			 Connection conn=null;
		     PreparedStatement stat=null;
		     PreparedStatement stat1=null;
		     ResultSet resultSet=null;
		     ResultSet resultSet1=null;
		     try {
				    conn = new connection().getConnection();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		    try{
		    	stat=conn.prepareStatement("SELECT CC_COMPLIANCE_CALENDER_DETAIL.ID,CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_COMPLIANCE_CALENDER_DETAIL.COMPLIANCE_ID,to_char(CC_COMPLIANCE_CALENDER_DETAIL.DUE_DATE,'dd-MM-yyyy') DUE_DATE," 
						+" to_char(CC_COMPLIANCE_CALENDER_DETAIL.SUBMISSION_DATE,'dd-MM-yyyy') SUBMISSION_DATE,"
						+" to_char(CC_COMPLIANCE_CALENDER_DETAIL.ACTUAL_DATE,'dd-MM-yyyy') ACTUAL_DATE,CC_COMPLIANCE_CALENDER_DETAIL.AMOUNT,CC_COMPLIANCE_CALENDER_DETAIL.CHALLAN_NO,CC_COMPLIANCE_CALENDER_DETAIL.BANK_CODE,"
						+" CC_COMPLIANCE_CALENDER_DETAIL.REMARKS,CC_COMPLIANCE_CALENDER_DETAIL.USER_ID,to_char(CC_COMPLIANCE_CALENDER_DETAIL.TDATE,'dd/MM/yyyy') TDATE,CC_COMPLIANCE_CALENDER_DETAIL.MUSER_ID,"
						+" to_char(CC_COMPLIANCE_CALENDER_DETAIL.MDATE,'dd/MM/yyyy') MDATE,CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID,CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID"
						+" FROM CC_COMPLIANCE_CALENDER_DETAIL,CC_COMPLIANCE_CALENDER,CC_ACTIVITY_MASTER"
						+" WHERE CC_COMPLIANCE_CALENDER_DETAIL.COMPLIANCE_ID=CC_COMPLIANCE_CALENDER.ID"
						+" AND CC_COMPLIANCE_CALENDER.ACTIVITY_ID=CC_ACTIVITY_MASTER.ID AND CC_COMPLIANCE_CALENDER_DETAIL.ID=?");
		    	stat.setString(1, E_ID);
		    	resultSet=stat.executeQuery();
		    	if(resultSet.next()){
		    		E_COMP_CODE=resultSet.getString("COMPLIANCE_ID");
		    		E_COMP_NAME=resultSet.getString("ACTIVITY_NAME");
		    		E_DUE_DATE=resultSet.getString("DUE_DATE");
		    		E_SUBMISSION_DATE=resultSet.getString("SUBMISSION_DATE");
		    		E_ACTUAL_DATE=resultSet.getString("ACTUAL_DATE");
		    		E_AMOUNT=resultSet.getString("AMOUNT");
		    		E_CHALLAN_NO=resultSet.getString("CHALLAN_NO");
		    		E_BANK_CODE=resultSet.getString("BANK_CODE");
		    		E_REMARKS=resultSet.getString("REMARKS");
		    		E_LOCATION_NAME=resultSet.getString("LOCATION_CODE");
		    		
		    		
	        		stat1=conn.prepareStatement("SELECT NAME FROM CC_COMPANY_MASTER WHERE ID=?");
	        		stat1.setString(1, resultSet.getString("COMPANY_ID"));
	        		resultSet1=stat1.executeQuery();
	        		if(resultSet1.next()){
	        			E_COMPANY_NAME=resultSet1.getString("NAME");
	        		}
	        		if(stat1!=null){
	        			stat1.close();
	        		}
	        		if(resultSet1!=null){
	        			resultSet1.close();
	        		}
	        		stat1=conn.prepareStatement("SELECT NAME FROM CC_DEPT_MASTER WHERE ID=?");
		    		stat1.setString(1, resultSet.getString("DEPT_ID"));
		    		resultSet1=stat1.executeQuery();
		    		if(resultSet1.next()){
		    			E_DEPT_NAME=resultSet1.getString("NAME");
		    		}
		    		if(stat1!=null){
		    			stat1.close();
		    		}
		    		if(resultSet1!=null){
		    			resultSet1.close();
		    		}
		    		
		    		stat1=conn.prepareStatement("SELECT NAME FROM CC_SUB_DEPT_MASTER WHERE ID=?");
		    		stat1.setString(1, resultSet.getString("SUBDEPT_ID"));
		    		resultSet1=stat1.executeQuery();
		    		
		    		if(resultSet1.next()){
		    			E_SUBDEPT_NAME=resultSet1.getString("NAME");
		    		}
		    		if(stat1!=null){
		    			stat1.close();
		    		}
		    		if(resultSet1!=null){
		    			resultSet1.close();
		    		}
		    		
		    	}
		    }
	        catch(Exception e){
	        	 System.out.println("UserMasterAction " + e);
		    } finally {
		        if (conn != null) {
		            conn.close();
		        }
		    }
		}
		return "editco";
	}
	
	public String update() throws SQLException
	{
		Map session = ActionContext.getContext().getSession();
		String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "editco";
        }
		if(E_ID!=null && E_ID.length()>0){
			Connection conn=null;
		     PreparedStatement stat=null;
		     PreparedStatement stat1=null;
		     ResultSet resultSet=null;
		     ResultSet resultSet1=null;
		     try {
				    conn = new connection().getConnection();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		    try{
		    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd-MMM-yyyy");
		    	stat=conn.prepareStatement("UPDATE CC_COMPLIANCE_CALENDER_DETAIL SET SUBMISSION_DATE=?, ACTUAL_DATE=?, AMOUNT=?, CHALLAN_NO=?, BANK_CODE=?, REMARKS=?,MUSER_ID=?, MDATE=SYSDATE WHERE ID=?");
		    	stat.setString(1, simpleDateFormat1.format(simpleDateFormat.parse(E_SUBMISSION_DATE)));
		    	stat.setString(2, simpleDateFormat1.format(simpleDateFormat.parse(E_ACTUAL_DATE)));
		    	stat.setString(3, E_AMOUNT);
		    	stat.setString(4, E_CHALLAN_NO);
		    	stat.setString(5, E_BANK_CODE);
		    	stat.setString(6, E_REMARKS);
		    	stat.setString(7, usrid);
		    	stat.setString(8, E_ID);
		    	int ss=stat.executeUpdate();
		    	if(ss>0){
		    		addActionError("Record Update Successfully");
		    	}
		    	
		    	
		    }
		    catch(Exception e){
	        	 System.out.println("UserMasterAction " + e);
		    } finally {
		        if (conn != null) {
		            conn.close();
		        }
		    }
		}
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
        	addActionError("ComplianceCalenderDetail.update()"+e);
        	return SUCCESS;
        }
        PreparedStatement stat=null;
        try{
        	int ctr=0;
        	if(chkdel!=null && chkdel.size()>0){
        		for(int i=0;i<chkdel.size();i++){
        			stat=conn.prepareStatement("DELETE FROM CC_COMPLIANCE_CALENDER_DETAIL WHERE ID=?");
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
        	addActionError("ComplianceCalenderDetail.update()"+se);
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
	
	public String actdp() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
        Connection conn=null;
        PreparedStatement stat=null;
        ResultSet resultSet=null;
        PreparedStatement stat1=null;
        ResultSet resultSet1=null;
        try {
		    conn = new connection().getConnection();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
        try{
        	activitylist=new ArrayList<ComplianceCalenderDtBean>();
        	String qry=" ";
        	if(SEARCH_ACTIVITY!=null && SEARCH_ACTIVITY.length()>0){
        		qry+=" AND CC_ACTIVITY_MASTER.ACTIVITY_NAME LIKE '"+SEARCH_ACTIVITY+"'";
        	}
        	stat=conn.prepareStatement("SELECT min(CC_COMPLIANCE_CALENDER.ID) ID,CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_YEAR.ACTIVITY_YEAR,CALENDER_TYPE,"
					+" min(CALENDER_TYPE_CODE) CALENDER_TYPE_CODE,DUE_DAYS,CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID,"
					+" CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID"
					+" FROM CC_COMPLIANCE_CALENDER,CC_ACTIVITY_MASTER,CC_ACTIVITY_YEAR "
					+" WHERE CC_COMPLIANCE_CALENDER.ACTIVITY_ID=CC_ACTIVITY_MASTER.ID AND CC_ACTIVITY_MASTER.ID=CC_ACTIVITY_YEAR.ACTIVITY_ID  AND CC_ACTIVITY_YEAR.FLAG='Y' and act_flag is null "+qry
					+" and CC_ACTIVITY_MASTER.ID in(select ACT_ID from CC_ACTIVITY_ACCESS_MASTER where EMP_CODE=? and FLAG='Y')"
                                        +" GROUP BY CC_ACTIVITY_MASTER.ACTIVITY_NAME,CC_ACTIVITY_YEAR.ACTIVITY_YEAR,CALENDER_TYPE,DUE_DAYS,"
					+" CC_ACTIVITY_MASTER.LOCATION_CODE, CC_ACTIVITY_MASTER.DEPT_ID, CC_ACTIVITY_MASTER.SUBDEPT_ID,CC_COMPLIANCE_CALENDER.COMPANY_ID");
        	stat.setString(1, usrid);
                resultSet=stat.executeQuery();
        	while(resultSet.next()){
        		String compname="";
        		stat1=conn.prepareStatement("SELECT NAME FROM CC_COMPANY_MASTER WHERE ID=?");
        		stat1.setString(1, resultSet.getString("COMPANY_ID"));
        		resultSet1=stat1.executeQuery();
        		if(resultSet1.next()){
        			compname=resultSet1.getString("NAME");
        		}
        		if(stat1!=null){
        			stat1.close();
        		}
        		if(resultSet1!=null){
        			resultSet1.close();
        		}
        		stat1=conn.prepareStatement("SELECT NAME FROM CC_DEPT_MASTER WHERE ID=?");
	    		stat1.setString(1, resultSet.getString("DEPT_ID"));
	    		resultSet1=stat1.executeQuery();
	    		String deptuser="";
	    		if(resultSet1.next()){
	    			deptuser=resultSet1.getString("NAME");
	    		}
	    		if(stat1!=null){
	    			stat1.close();
	    		}
	    		if(resultSet1!=null){
	    			resultSet1.close();
	    		}
	    		
	    		stat1=conn.prepareStatement("SELECT NAME FROM CC_SUB_DEPT_MASTER WHERE ID=?");
	    		stat1.setString(1, resultSet.getString("SUBDEPT_ID"));
	    		resultSet1=stat1.executeQuery();
	    		String subdeptuser="";
	    		if(resultSet1.next()){
	    			subdeptuser=resultSet1.getString("NAME");
	    		}
	    		if(stat1!=null){
	    			stat1.close();
	    		}
	    		if(resultSet1!=null){
	    			resultSet1.close();
	    		}
        		Date duedate=duedate(resultSet.getString("CALENDER_TYPE"),resultSet.getInt("ACTIVITY_YEAR"),resultSet.getInt("CALENDER_TYPE_CODE"),resultSet.getInt("DUE_DAYS"));
        		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        		
                        
                        activitylist.add(new ComplianceCalenderDtBean(resultSet.getLong("ID"),resultSet.getString("ACTIVITY_NAME"),formatter.format(duedate),compname,resultSet.getString("LOCATION_CODE"),deptuser,subdeptuser,resultSet.getString("CALENDER_TYPE"),""));
        	}
        }
        catch(Exception e){
       	 System.out.println("UserMasterAction " + e);
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	        if(stat!=null){
	        	stat.close();
	        }
	        if(resultSet!=null){
	        	resultSet.close();
	        }
	    }
        return "actdp";
	}
	
	public String save() throws SQLException{
		Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if(usrid==null){
        	addActionError("Session not valid");
        	return "newco";
        }
        Connection conn=null;
        PreparedStatement stat=null;
        PreparedStatement stat1=null;
        PreparedStatement statinsert=null;
        PreparedStatement statupd=null;
        ResultSet resultSet=null;
        ResultSet resultSet1=null;
        
        try {
		    conn = new connection().getConnection();
		} catch (Exception e) {
		    System.out.println(e.toString());
		}
        try{
        	if(COMP_CODE!=null && COMP_CODE.size()>0){ 
        		int ctr=0;
        		for(int i=0;i<COMP_CODE.size();i++){
        			if(COMP_CODE.get(i).toString()!=null && COMP_CODE.get(i).toString().length()>0){
	        			stat=conn.prepareStatement("SELECT * FROM CC_COMPLIANCE_CALENDER_DETAIL WHERE COMPLIANCE_ID=?");
	        			stat.setString(1,COMP_CODE.get(i).toString());
	        			resultSet=stat.executeQuery();
	        			if(resultSet.next()){}
	        			else{
	        				long slno=0;
	        				stat1=conn.prepareStatement("SELECT NVL(MAX(ID),0)+1 SL_NO FROM CC_COMPLIANCE_CALENDER_DETAIL");
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
	        				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
	        				SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd-MMM-yyyy");
	        				
	        				statinsert=conn.prepareStatement("INSERT INTO CC_COMPLIANCE_CALENDER_DETAIL (ID,COMPLIANCE_ID,DUE_DATE,SUBMISSION_DATE,ACTUAL_DATE,AMOUNT,CHALLAN_NO,BANK_CODE,REMARKS,USER_ID,TDATE,MUSER_ID,MDATE,CALENDER_TYPE)"
	        						+" VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?)");
	        				statinsert.setLong(1, slno);
	        				statinsert.setString(2, COMP_CODE.get(i).toString());
	        				statinsert.setString(3, simpleDateFormat1.format(simpleDateFormat.parse(DUE_DATE.get(i).toString())));
	        				statinsert.setString(4, simpleDateFormat1.format(simpleDateFormat.parse(SUBMISSION_DATE.get(i).toString())));
	        				statinsert.setString(5, simpleDateFormat1.format(simpleDateFormat.parse(ACTUAL_DATE.get(i).toString())));
	        				statinsert.setString(6, AMOUNT.get(i).toString());
	        				statinsert.setString(7, CHALLAN_NO.get(i).toString());
	        				statinsert.setString(8, BANK_CODE.get(i).toString());
	        				statinsert.setString(9, REMARKS.get(i).toString().toUpperCase());
	        				statinsert.setString(10, usrid);
	        				statinsert.setString(11, usrid);
                                                statinsert.setString(12, CALENDER_TYPE.get(i).toString());
                                                
	        				int st=statinsert.executeUpdate();
	        				if(st>0){
	        					++ctr;
	        				}
	        				if(statinsert!=null){
	        					statinsert.close();
	        				}
	        				statupd=conn.prepareStatement("UPDATE CC_COMPLIANCE_CALENDER SET ACT_FLAG='Y' WHERE ID=?");
	        				statupd.setString(1, COMP_CODE.get(i).toString());
	        				statupd.executeUpdate();
	        				if(statupd!=null){
	        					statupd.close();
	        				}
	        			}
	        			if(stat!=null){
	        				stat.close();
	        			}
	        			if(resultSet!=null){
	        				resultSet.close();
	        			}
	        		}
        		}
        		if(ctr>0){
        			addActionError("Record Save Successfully!!!");
        		}
        	}
        }
        catch(Exception e){
        	 System.out.println("UserMasterAction " + e);
	    } finally {
	        if (conn != null) {
	            conn.close();
	        }
	    }
		return "newco";		
	}
	
	public Date duedate(String type,int year,int code,int duedate){
		int bb=0;
		if(type!=null && type.equals("M")){
			switch(code){
			case 1 : bb=3;
			break;
			case 2 : bb=4;
			 break;
			case 3 : bb=5;
			 break;
			case 4 : bb=6;
			 break;
			case 5 : bb=7;
			 break;
			case 6 : bb=8;
			 break;
			case 7 : bb=9;
			 break;
			case 8 : bb=10;
			 break;
			case 9 : bb=11;
			 break;
			case 10 : bb=12;
			 break;
			case 11 : bb=1;
			 break;
			case 12 : bb=2;
			 break;			 
			}
		}
		else if(type!=null && type.equals("Q")){
			switch(code){
			case 1 : bb=5;
			break;
			case 2 : bb=8;
			 break;
			case 3 : bb=11;
			 break;
			case 4 : bb=2;
			 break;
			}
		}
		else if(type!=null && type.equals("H")){
			switch(code){
			case 1 : bb=8;
			break;
			case 2 : bb=2;
			 break;
			}
		}
		else if(type!=null && type.equals("Y")){
			bb=2;
		}
		if(bb<3){
			year=year+1;
		}
		Date dt=getLastDateOfMonth(year,bb);
		Calendar cal=Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, duedate);
		dt=cal.getTime();
		return dt;
	}
	
	public static Date getLastDateOfMonth(int year, int month) {
	    Calendar calendar = new GregorianCalendar(year, month, Calendar.DAY_OF_MONTH);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return calendar.getTime();
	}

	
	public List getCOMP_CODE() {
		return COMP_CODE;
	}
	public void setCOMP_CODE(List cOMP_CODE) {
		COMP_CODE = cOMP_CODE;
	}
	public List getCOMP_NAME() {
		return COMP_NAME;
	}
	public void setCOMP_NAME(List cOMP_NAME) {
		COMP_NAME = cOMP_NAME;
	}
	public List getDUE_DATE() {
		return DUE_DATE;
	}
	public void setDUE_DATE(List dUE_DATE) {
		DUE_DATE = dUE_DATE;
	}
	public List getSUBMISSION_DATE() {
		return SUBMISSION_DATE;
	}
	public void setSUBMISSION_DATE(List sUBMISSION_DATE) {
		SUBMISSION_DATE = sUBMISSION_DATE;
	}
	public List getACTUAL_DATE() {
		return ACTUAL_DATE;
	}
	public void setACTUAL_DATE(List aCTUAL_DATE) {
		ACTUAL_DATE = aCTUAL_DATE;
	}
	public List getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(List aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public List getCHALLAN_NO() {
		return CHALLAN_NO;
	}
	public void setCHALLAN_NO(List cHALLAN_NO) {
		CHALLAN_NO = cHALLAN_NO;
	}
	public List getBANK_CODE() {
		return BANK_CODE;
	}
	public void setBANK_CODE(List bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}
	public List getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(List rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getAausrid() {
		return aausrid;
	}

	public void setAausrid(String aausrid) {
		this.aausrid = aausrid;
	}

	public String getSEARCH_ACTIVITY() {
		return SEARCH_ACTIVITY;
	}

	public void setSEARCH_ACTIVITY(String sEARCH_ACTIVITY) {
		SEARCH_ACTIVITY = sEARCH_ACTIVITY;
	}

	public List<ComplianceCalenderDetailBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<ComplianceCalenderDetailBean> detaillist) {
		this.detaillist = detaillist;
	}

	public List<ComplianceCalenderDtBean> getActivitylist() {
		return activitylist;
	}

	public void setActivitylist(List<ComplianceCalenderDtBean> activitylist) {
		this.activitylist = activitylist;
	}

	public String getTINDEX() {
		return TINDEX;
	}

	public void setTINDEX(String tINDEX) {
		TINDEX = tINDEX;
	}

	public List getCOMPANY_NAME() {
		return COMPANY_NAME;
	}

	public void setCOMPANY_NAME(List cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}

	public List getLOCATION_NAME() {
		return LOCATION_NAME;
	}

	public void setLOCATION_NAME(List lOCATION_NAME) {
		LOCATION_NAME = lOCATION_NAME;
	}

	public List getDEPT_NAME() {
		return DEPT_NAME;
	}

	public void setDEPT_NAME(List dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}

	public List getSUBDEPT_NAME() {
		return SUBDEPT_NAME;
	}

	public void setSUBDEPT_NAME(List sUBDEPT_NAME) {
		SUBDEPT_NAME = sUBDEPT_NAME;
	}

	public String getE_ID() {
		return E_ID;
	}

	public void setE_ID(String e_ID) {
		E_ID = e_ID;
	}

	public String getE_COMPANY_NAME() {
		return E_COMPANY_NAME;
	}

	public void setE_COMPANY_NAME(String e_COMPANY_NAME) {
		E_COMPANY_NAME = e_COMPANY_NAME;
	}

	public String getE_LOCATION_NAME() {
		return E_LOCATION_NAME;
	}

	public void setE_LOCATION_NAME(String e_LOCATION_NAME) {
		E_LOCATION_NAME = e_LOCATION_NAME;
	}

	public String getE_DEPT_NAME() {
		return E_DEPT_NAME;
	}

	public void setE_DEPT_NAME(String e_DEPT_NAME) {
		E_DEPT_NAME = e_DEPT_NAME;
	}

	public String getE_SUBDEPT_NAME() {
		return E_SUBDEPT_NAME;
	}

	public void setE_SUBDEPT_NAME(String e_SUBDEPT_NAME) {
		E_SUBDEPT_NAME = e_SUBDEPT_NAME;
	}

	public String getE_COMP_CODE() {
		return E_COMP_CODE;
	}

	public void setE_COMP_CODE(String e_COMP_CODE) {
		E_COMP_CODE = e_COMP_CODE;
	}

	public String getE_COMP_NAME() {
		return E_COMP_NAME;
	}

	public void setE_COMP_NAME(String e_COMP_NAME) {
		E_COMP_NAME = e_COMP_NAME;
	}

	public String getE_DUE_DATE() {
		return E_DUE_DATE;
	}

	public void setE_DUE_DATE(String e_DUE_DATE) {
		E_DUE_DATE = e_DUE_DATE;
	}

	public String getE_SUBMISSION_DATE() {
		return E_SUBMISSION_DATE;
	}

	public void setE_SUBMISSION_DATE(String e_SUBMISSION_DATE) {
		E_SUBMISSION_DATE = e_SUBMISSION_DATE;
	}

	public String getE_ACTUAL_DATE() {
		return E_ACTUAL_DATE;
	}

	public void setE_ACTUAL_DATE(String e_ACTUAL_DATE) {
		E_ACTUAL_DATE = e_ACTUAL_DATE;
	}

	public String getE_AMOUNT() {
		return E_AMOUNT;
	}

	public void setE_AMOUNT(String e_AMOUNT) {
		E_AMOUNT = e_AMOUNT;
	}

	public String getE_CHALLAN_NO() {
		return E_CHALLAN_NO;
	}

	public void setE_CHALLAN_NO(String e_CHALLAN_NO) {
		E_CHALLAN_NO = e_CHALLAN_NO;
	}

	public String getE_BANK_CODE() {
		return E_BANK_CODE;
	}

	public void setE_BANK_CODE(String e_BANK_CODE) {
		E_BANK_CODE = e_BANK_CODE;
	}

	public String getE_REMARKS() {
		return E_REMARKS;
	}

	public void setE_REMARKS(String e_REMARKS) {
		E_REMARKS = e_REMARKS;
	}

	public List getChkdel() {
		return chkdel;
	}

	public void setChkdel(List chkdel) {
		this.chkdel = chkdel;
	}

    public List getCALENDER_TYPE() {
        return CALENDER_TYPE;
    }

    public void setCALENDER_TYPE(List CALENDER_TYPE) {
        this.CALENDER_TYPE = CALENDER_TYPE;
    }
	
	
}
