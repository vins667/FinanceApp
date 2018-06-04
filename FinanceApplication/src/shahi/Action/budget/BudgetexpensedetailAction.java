/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.budget;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import shahi.Action.Mis.Beans.ShahiInformationBean;
import shahi.Action.Mis.MisFunction;
import shahi.Action.Mis.ShahiInformationList;
import shahi.Action.budget.Beans.BudgetexpenseBean;
import shahi.Action.budget.Beans.BudgetmasterBean;
import shahi.Action.database.ConnectionShahiHris;

/**
 *
 * @author Ranjeet Singh
 */
public class BudgetexpensedetailAction extends ActionSupport {
    
    private String SEARCH_LOCATION_CODE;
    private String SEARCH_FIN_YEAR;
    
    private String  SEARCH_DEPT_CODE;
    private String SEARCH_SUB_DEPT_CODE;
    
    
    private List errorlist=new ArrayList();
    private List expenselist=new ArrayList();
    private String deleteflag;
    private List chkdel;
   
    
    private List deptlist=new ArrayList();
    private List subdeptlist=new ArrayList();
    private String DEPT_CODE;
    private String LOCATION_CODE;
    private String FIN_YEAR;
    private String SUB_DEPT_CODE;
    private double BUDGET_AMOUNT=0;
    private List EXPENSE_HEAD_LIST;
    
    private List QTR1_BDGT_AMNT;
    private List QTR1_ACT_AMNT;
    
    private List QTR2_BDGT_AMNT;
    private List QTR2_ACT_AMNT;
    
    private List QTR3_BDGT_AMNT;
    private List QTR3_ACT_AMNT;
    
    private List QTR4_BDGT_AMNT;
    private List QTR4_ACT_AMNT;
    
    private double QTR1_BDGT_AMNT_TOTAL=0;
    private double QTR1_ACT_AMNT_TOTAL=0;
    
    private double QTR2_BDGT_AMNT_TOTAL=0;
    private double QTR2_ACT_AMNT_TOTAL=0;
    
    private double QTR3_BDGT_AMNT_TOTAL=0;
    private double QTR3_ACT_AMNT_TOTAL=0;
    
    private double QTR4_BDGT_AMNT_TOTAL=0;
    private double QTR4_ACT_AMNT_TOTAL=0;
    
    private double QTR1_ACT_AMNT_VARIATION=0;
    private double QTR2_ACT_AMNT_VARIATION=0;
    private double QTR3_ACT_AMNT_VARIATION=0;
    private double QTR4_ACT_AMNT_VARIATION=0;
    
    private double TOTAL_ENTERED_BUDGET_AMOUNT=0;
    
    private List budgetexpensedetaillist=new ArrayList();
     
    private String UPDATE_FLAG="NO";
    
    private List finyearlist=new ArrayList();
    
    
    
  
  public String execute() throws Exception {
    Connection conn = null;
        int flag = 0;
        try {
            conn = new ConnectionShahiHris().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
       Map session = ActionContext.getContext().getSession();
       String usrid = (String) session.get("sessUserId");
       
       String sqlstr="";
       
       if(SEARCH_FIN_YEAR!=null && SEARCH_FIN_YEAR.length()>0)
       {
      
       sqlstr=" and  FIN_YEAR='"+SEARCH_FIN_YEAR+"'";
       
       }
       
     
       
      
       
       try{
           
           if(deleteflag!=null && deleteflag.equals("Yes") && chkdel!=null && chkdel.size()>0)
           {
           for(int i=0; i<chkdel.size(); i++)
           {
               if(chkdel.get(i).toString()!=null && chkdel.get(i).toString().length()>0 )
               {
               //stat1=conn.prepareStatement("delete from BUDGET_EXPENSE_MASTER where sl_no=?");
               //stat1.setString(1, chkdel.get(i).toString());
               //flag=stat1.executeUpdate();
               
               }
           
           
           }
           
           }
           
            stat1=conn.prepareStatement("select DIVISION,FIN_YEAR,DEPT_CODE, nvl(SUB_DEPT_CODE,' ')SUB_DEPT_CODE,BUDGET_AMOUNT from BUDGET_MASTER where DIVISION=? "+sqlstr+" order by 1,2,3");
            stat1.setString(1, SEARCH_LOCATION_CODE);
            result=stat1.executeQuery();
            while(result.next())
            {  
                 ShahiInformationList obj=new ShahiInformationList();
                String dept_desc=((ShahiInformationBean)(obj.departmentListBYcode(result.getString("DEPT_CODE"))).get(0)).getWHNM().toString();
                  String sub_dept_desc="";
                  if(result.getString("SUB_DEPT_CODE").trim().length()>0){
                         sub_dept_desc= ((ShahiInformationBean)(obj.subdepartmentListBYcodeandsubcode(result.getString("DEPT_CODE"),result.getString("SUB_DEPT_CODE"))).get(0)).getWHNM().toString();
                  }
               expenselist.add(new BudgetexpenseBean(result.getString("DIVISION"),result.getString("FIN_YEAR"),result.getString("DEPT_CODE"),result.getString("SUB_DEPT_CODE"),result.getString("BUDGET_AMOUNT"),dept_desc,sub_dept_desc));
            
            }
           
           
         } catch (Exception e) {  
             System.out.print("2 File Name :execute BudgetexpensemasterAction.java" + e);
            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (result != null) {
                    result.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (conn != null) {
                    conn.close();
                }

                result1 = null;
                stat1 = null;
                conn = null;


            } catch (Exception e) {
                System.out.print("File Name :execute BudgetexpensemasterAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
         if (deleteflag != null && deleteflag.equals("Yes")) {
            if (flag == 1) {
                addActionMessage("Record  Deleted...");
            }
            if (flag == 0) {
                addActionMessage("Record  Not Deleted...");
            }
        } else {
        }
       return SUCCESS;
    }

   public String newexecute() throws Exception {
     
       ShahiInformationList obj=new ShahiInformationList();
       deptlist=obj.departmentListBYcode(null);
       expenselist.addAll(budgetexpenselistbyheadcode(null));
       getfinanceyear(null);
        
    return "newinput";
    }
   
   
 void getfinanceyear(String year)  
 {
       Connection conn = null;
        int flag = 0;
        try {
            conn = new ConnectionShahiHris().getConnection();
           

        } catch (Exception e) {
            System.out.println(e.toString());
        }
 
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
       Map session = ActionContext.getContext().getSession();
       String usrid = (String) session.get("sessUserId");
       
       try{
           if(year!=null && year.length()>0){
              stat1=conn.prepareStatement("select to_char(yr) aa,yr||'-'||yr1 fin_year from (select ? yr,?+1 yr1 from dual)");
              stat1.setString(1, year);
              stat1.setString(2, year);
           
           }else{
           stat1=conn.prepareStatement("select to_char(yr) aa,yr||'-'||yr1 fin_year from (select to_char(sysdate,'yyyy')-1 yr,to_char(sysdate,'yyyy') yr1 from dual) union select to_char(yr) aa,yr||'-'||yr1 fin_year from (select to_char(sysdate,'yyyy') yr,to_char(sysdate,'yyyy')+1 yr1 from dual) union select to_char(yr) aa,yr||'-'||yr1 fin_year from (select to_char(sysdate,'yyyy')+1 yr,to_char(sysdate,'yyyy')+2 yr1 from dual)");
          }
           result=stat1.executeQuery();
           while(result.next())
           {
           finyearlist.add(new BudgetexpenseBean(result.getString(1),result.getString(2)) );
           }
           
        } catch (Exception e) {
              
            System.out.print("2 File Name : BudgetexpensedetailAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (result != null) {
                    result.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (conn != null) {
                    conn.close();
                }

                result1 = null;
                stat1 = null;
                conn = null;


            } catch (Exception e) {
                System.out.print("File Name : BudgetexpensedetailAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
 
 }
   
   
   public String subdeptexecute() throws Exception {
     if(DEPT_CODE!=null && DEPT_CODE.length()>0){
       ShahiInformationList obj=new ShahiInformationList();
       if(SUB_DEPT_CODE!=null && SUB_DEPT_CODE.length()>0){
        subdeptlist=obj.subdepartmentListBYcodeandsubcode(DEPT_CODE, SUB_DEPT_CODE);
       }else{
       subdeptlist=obj.subdepartmentListBYcodeandsubcode(DEPT_CODE, null);
       }
       
       
     } 
    return "subdept";
    }
       public List budgetexpenselistbyheadcode(String headcode) throws SQLException
	{
           Connection con=null;
	   ResultSet rs=null;
	   String sqlScript;
	   ConnectionShahiHris connectionShahiHris=new ConnectionShahiHris();
		if(headcode!=null && headcode.length()>0)
                {
                headcode="where EXPENSE_HEAD='"+headcode+"'";
                }else{
                headcode=" ";
                }
                
               
		List list=new ArrayList();
		sqlScript="select SL_NO,EXPENSE_HEAD,EXPENSE_DESC||' - '||EXPENSE_HEAD EXPENSE_DESC from BUDGET_EXPENSE_MASTER  "+headcode+" order by EXPENSE_DESC";
		try
		{
			con=connectionShahiHris.getConnection();
			
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery(sqlScript);
			while(rs.next())
			{
			    list.add(new BudgetmasterBean(rs.getString("SL_NO"),rs.getString("EXPENSE_HEAD"),rs.getString("EXPENSE_DESC")));
            
			}
		}
		catch(SQLException se)
		{
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			con.close();
			rs.close();
		}
		return list;
	}
   
    public String saveexecute() throws Exception {
         
         Connection conn = null;
        int flag = 0;
        try {
            conn = new ConnectionShahiHris().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
       Map session = ActionContext.getContext().getSession();
       String usrid = (String) session.get("sessUserId");
       
       try{
           String sqlstr="";
           if(SUB_DEPT_CODE!=null && !SUB_DEPT_CODE.equals("") && SUB_DEPT_CODE.length()>0)
           {
           sqlstr= "and SUB_DEPT_CODE='"+SUB_DEPT_CODE+"'";
           }
           stat1=conn.prepareStatement("select  BUDGET_AMOUNT from  BUDGET_MASTER where DIVISION=? and FIN_YEAR=? and DEPT_CODE=? "+sqlstr);
           stat1.setString(1, LOCATION_CODE);
           stat1.setString(2, FIN_YEAR);
           stat1.setString(3, DEPT_CODE);
           result=stat1.executeQuery();
            if(result.next()==false){
           stat1=conn.prepareStatement("insert into BUDGET_MASTER(DIVISION,FIN_YEAR,DEPT_CODE,SUB_DEPT_CODE,BUDGET_AMOUNT,USER_ID,TDATE) values(?,?,?,?,?,?,sysdate)");
           stat1.setString(1, LOCATION_CODE);
           stat1.setString(2, FIN_YEAR);
           stat1.setString(3, DEPT_CODE);
           stat1.setString(4, SUB_DEPT_CODE);
           stat1.setDouble(5, BUDGET_AMOUNT);
           stat1.setString(6, usrid);
           flag=stat1.executeUpdate();
           if(flag>0)
           {
            if(EXPENSE_HEAD_LIST!=null && EXPENSE_HEAD_LIST.size()>0)
            {
             for(int i=0; i<EXPENSE_HEAD_LIST.size(); i++)
             { 
                 if(EXPENSE_HEAD_LIST.get(i).toString()!=null && !EXPENSE_HEAD_LIST.get(i).toString().equals("") && EXPENSE_HEAD_LIST.get(i).toString().length()>0 )
                 {   int sl_no=0;
                     stat1=conn.prepareStatement("select nvl(max(SL_NO),0)+1 sl_no from BUDGET_DETAIL");
                     result1=stat1.executeQuery();
                     if(result1.next())
                     {
                     sl_no=result1.getInt(1);
                     }    
                     if(sl_no>0){
                         stat1 = conn.prepareStatement("insert into BUDGET_DETAIL(SL_NO,DIVISION,FIN_YEAR,DEPT_CODE,SUB_DEPT_CODE,EXPENSE_HEAD,QTR1_BDGT_AMNT,QTR1_ACT_AMNT,QTR2_BDGT_AMNT,QTR2_ACT_AMNT,QTR3_BDGT_AMNT,QTR3_ACT_AMNT,QTR4_BDGT_AMNT,QTR4_ACT_AMNT,USER_ID,TDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)");
                         stat1.setInt(1, sl_no);
                         stat1.setString(2, LOCATION_CODE);
                         stat1.setString(3, FIN_YEAR);
                         stat1.setString(4, DEPT_CODE);
                         stat1.setString(5, SUB_DEPT_CODE);
                         stat1.setString(6, EXPENSE_HEAD_LIST.get(i).toString());
                         stat1.setString(7, QTR1_BDGT_AMNT.get(i).toString());
                         stat1.setString(8, QTR1_ACT_AMNT.get(i).toString());
                         stat1.setString(9, QTR2_BDGT_AMNT.get(i).toString());
                         stat1.setString(10, QTR2_ACT_AMNT.get(i).toString());
                         stat1.setString(11, QTR3_BDGT_AMNT.get(i).toString());
                         stat1.setString(12, QTR3_ACT_AMNT.get(i).toString());
                         stat1.setString(13, QTR4_BDGT_AMNT.get(i).toString());
                         stat1.setString(14, QTR4_ACT_AMNT.get(i).toString());
                         stat1.setString(15, usrid);
                         flag=stat1.executeUpdate();
                    }
                 }
             
             }
            
            }
           
           
           }
           
           if (flag > 0) {
               addActionMessage("Record  Saved...");
             

           } else {

               addActionError("Record Not Saved...");
           }
       }else{
            
            addActionError("Record Already Exists.");
            }
           LOCATION_CODE = null;
           FIN_YEAR = null;
           DEPT_CODE = null;
           SUB_DEPT_CODE = null;
           EXPENSE_HEAD_LIST = null;
           QTR1_BDGT_AMNT = null;
           QTR1_ACT_AMNT = null;
           QTR2_BDGT_AMNT = null;
           QTR2_ACT_AMNT = null;
           QTR3_BDGT_AMNT = null;
           QTR3_ACT_AMNT = null;
           QTR4_BDGT_AMNT = null;
           QTR4_ACT_AMNT = null;
           BUDGET_AMOUNT = 0.0;
           QTR1_BDGT_AMNT_TOTAL = 0;
           QTR1_ACT_AMNT_TOTAL = 0;

           QTR2_BDGT_AMNT_TOTAL = 0;
           QTR2_ACT_AMNT_TOTAL = 0;

           QTR3_BDGT_AMNT_TOTAL = 0;
           QTR3_ACT_AMNT_TOTAL = 0;

           QTR4_BDGT_AMNT_TOTAL = 0;
           QTR4_ACT_AMNT_TOTAL = 0;

           QTR1_ACT_AMNT_VARIATION = 0;
           QTR2_ACT_AMNT_VARIATION = 0;
           QTR3_ACT_AMNT_VARIATION = 0;
           QTR4_ACT_AMNT_VARIATION = 0;
           TOTAL_ENTERED_BUDGET_AMOUNT=0;
           
           
           
               conn.commit();
               
        ShahiInformationList obj=new ShahiInformationList();
       deptlist=obj.departmentListBYcode(null);
       expenselist.addAll(budgetexpenselistbyheadcode(null));
       getfinanceyear(null);

        } catch (Exception e) {
               flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : BudgetexpensedetailAction.java" + ee);

                    System.out.println(ee.toString());
                }
            System.out.print("2 File Name : BudgetexpensedetailAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (result != null) {
                    result.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (conn != null) {
                    conn.close();
                }

                result1 = null;
                stat1 = null;
                conn = null;


            } catch (Exception e) {
                System.out.print("File Name : BudgetexpensedetailAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
    return "newinput";
    }
   
   public String updateexecute() throws Exception {
         
         Connection conn = null;
        int flag = 0;
        try {
            conn = new ConnectionShahiHris().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
       Map session = ActionContext.getContext().getSession();
       String usrid = (String) session.get("sessUserId");
       
       try{
           String updstr="";
           if(SUB_DEPT_CODE!=null && SUB_DEPT_CODE.length()>0)
           {
           updstr =" and SUB_DEPT_CODE='"+SUB_DEPT_CODE+"'";
           
           }
           
           stat1=conn.prepareStatement("update BUDGET_MASTER set BUDGET_AMOUNT=?,USER_ID=?,TDATE=sysdate where  DIVISION=? and FIN_YEAR=? and DEPT_CODE=? "+updstr);
           stat1.setDouble(1, BUDGET_AMOUNT);
           stat1.setString(2, usrid);
           stat1.setString(3, LOCATION_CODE);
           stat1.setString(4, FIN_YEAR);
           stat1.setString(5, DEPT_CODE);
          flag=stat1.executeUpdate();
           if(flag>0)
           {
            if(EXPENSE_HEAD_LIST!=null && EXPENSE_HEAD_LIST.size()>0)
            {
                stat1=conn.prepareStatement("delete from   BUDGET_DETAIL where  DIVISION=? and FIN_YEAR=? and DEPT_CODE=? "+updstr);
                stat1.setString(1, LOCATION_CODE);
                stat1.setString(2, FIN_YEAR);
                stat1.setString(3, DEPT_CODE);
                stat1.executeUpdate();
                
             for(int i=0; i<EXPENSE_HEAD_LIST.size(); i++)
             { 
                 if(EXPENSE_HEAD_LIST.get(i).toString()!=null && !EXPENSE_HEAD_LIST.get(i).toString().equals("") && EXPENSE_HEAD_LIST.get(i).toString().length()>0 )
                 {   int sl_no=0;
                     stat1=conn.prepareStatement("select nvl(max(SL_NO),0)+1 sl_no from BUDGET_DETAIL");
                     result1=stat1.executeQuery();
                     if(result1.next())
                     {
                     sl_no=result1.getInt(1);
                     }    
                     if(sl_no>0){
                         stat1 = conn.prepareStatement("insert into BUDGET_DETAIL(SL_NO,DIVISION,FIN_YEAR,DEPT_CODE,SUB_DEPT_CODE,EXPENSE_HEAD,QTR1_BDGT_AMNT,QTR1_ACT_AMNT,QTR2_BDGT_AMNT,QTR2_ACT_AMNT,QTR3_BDGT_AMNT,QTR3_ACT_AMNT,QTR4_BDGT_AMNT,QTR4_ACT_AMNT,USER_ID,TDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)");
                         stat1.setInt(1, sl_no);
                         stat1.setString(2, LOCATION_CODE);
                         stat1.setString(3, FIN_YEAR);
                         stat1.setString(4, DEPT_CODE);
                         stat1.setString(5, SUB_DEPT_CODE);
                         stat1.setString(6, EXPENSE_HEAD_LIST.get(i).toString());
                         stat1.setString(7, QTR1_BDGT_AMNT.get(i).toString());
                         stat1.setString(8, QTR1_ACT_AMNT.get(i).toString());
                         stat1.setString(9, QTR2_BDGT_AMNT.get(i).toString());
                         stat1.setString(10, QTR2_ACT_AMNT.get(i).toString());
                         stat1.setString(11, QTR3_BDGT_AMNT.get(i).toString());
                         stat1.setString(12, QTR3_ACT_AMNT.get(i).toString());
                         stat1.setString(13, QTR4_BDGT_AMNT.get(i).toString());
                         stat1.setString(14, QTR4_ACT_AMNT.get(i).toString());
                         stat1.setString(15, usrid);
                         flag=stat1.executeUpdate();
                    }
                 }
             
             }
            
            }
           
           
           }
           
           if (flag > 0) {
               addActionMessage("Record  Saved...");
             

           } else {

               addActionError("Record Not Saved...");
           }
           
          
           EXPENSE_HEAD_LIST = null;
           QTR1_BDGT_AMNT = null;
           QTR1_ACT_AMNT = null;
           QTR2_BDGT_AMNT = null;
           QTR2_ACT_AMNT = null;
           QTR3_BDGT_AMNT = null;
           QTR3_ACT_AMNT = null;
           QTR4_BDGT_AMNT = null;
           QTR4_ACT_AMNT = null;
           BUDGET_AMOUNT = 0.0;
           QTR1_BDGT_AMNT_TOTAL = 0;
           QTR1_ACT_AMNT_TOTAL = 0;

           QTR2_BDGT_AMNT_TOTAL = 0;
           QTR2_ACT_AMNT_TOTAL = 0;

           QTR3_BDGT_AMNT_TOTAL = 0;
           QTR3_ACT_AMNT_TOTAL = 0;

           QTR4_BDGT_AMNT_TOTAL = 0;
           QTR4_ACT_AMNT_TOTAL = 0;

           QTR1_ACT_AMNT_VARIATION = 0;
           QTR2_ACT_AMNT_VARIATION = 0;
           QTR3_ACT_AMNT_VARIATION = 0;
           QTR4_ACT_AMNT_VARIATION = 0;
           TOTAL_ENTERED_BUDGET_AMOUNT=0;
            conn.commit();
           expensedetail();
           
        

        } catch (Exception e) {
               flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : BudgetexpensedetailAction.java" + ee);

                    System.out.println(ee.toString());
                }
            System.out.print("2 File Name : BudgetexpensedetailAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (result != null) {
                    result.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (conn != null) {
                    conn.close();
                }

                result1 = null;
                stat1 = null;
                conn = null;


            } catch (Exception e) {
                System.out.print("File Name : BudgetexpensedetailAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
    return "newinput";
    }  
    
    
  public String updexecute() throws Exception {
       
      expensedetail();
  return "newinput";
  }  
    
    

  void expensedetail()
  {
    Connection conn = null;
        int flag = 0;
        try {
            conn = new ConnectionShahiHris().getConnection();
            

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result = null;
       Map session = ActionContext.getContext().getSession();
       String usrid = (String) session.get("sessUserId");
       
       String sqlstr="";
       
       if(SUB_DEPT_CODE==null && SUB_DEPT_CODE.length()>0 )
       {
       sqlstr= " and SUB_DEPT_CODE='"+SUB_DEPT_CODE+"'";
       }
       
       try{
        ShahiInformationList obj=new ShahiInformationList();
       deptlist=obj.departmentListBYcode(DEPT_CODE);
       expenselist.addAll(budgetexpenselistbyheadcode(null));   
           
       stat1 = conn.prepareStatement("select DIVISION,FIN_YEAR,DEPT_CODE,SUB_DEPT_CODE,BUDGET_AMOUNT from BUDGET_MASTER where DIVISION=? and FIN_YEAR=? and DEPT_CODE=?  " +sqlstr); 
       stat1.setString(1,LOCATION_CODE);
       stat1.setString(2,FIN_YEAR);
       stat1.setString(3,DEPT_CODE);
       result=stat1.executeQuery();
       if(result.next())
       {
       BUDGET_AMOUNT=result.getDouble("BUDGET_AMOUNT");
       TOTAL_ENTERED_BUDGET_AMOUNT=result.getDouble("BUDGET_AMOUNT");
       stat1 = conn.prepareStatement("select SL_NO,DIVISION,FIN_YEAR,DEPT_CODE,SUB_DEPT_CODE,EXPENSE_HEAD,QTR1_BDGT_AMNT,QTR1_ACT_AMNT,QTR2_BDGT_AMNT,QTR2_ACT_AMNT,QTR3_BDGT_AMNT,QTR3_ACT_AMNT,QTR4_BDGT_AMNT,QTR4_ACT_AMNT from BUDGET_DETAIL where DIVISION=? and FIN_YEAR=? and DEPT_CODE=?  " +sqlstr +" order by  SL_NO "); 
       stat1.setString(1,LOCATION_CODE);
       stat1.setString(2,FIN_YEAR);
       stat1.setString(3,DEPT_CODE);
       result1=stat1.executeQuery();
       while(result1.next())
       {
       budgetexpensedetaillist.add(new BudgetexpenseBean(result1.getString("DIVISION"),result1.getString("FIN_YEAR"),result1.getString("DEPT_CODE"),result1.getString("SUB_DEPT_CODE"),"",result1.getString("EXPENSE_HEAD"),result1.getString("QTR1_BDGT_AMNT"),result1.getString("QTR1_ACT_AMNT"),result1.getString("QTR2_BDGT_AMNT"),result1.getString("QTR2_ACT_AMNT"),result1.getString("QTR3_BDGT_AMNT"),result1.getString("QTR3_ACT_AMNT"),result1.getString("QTR4_BDGT_AMNT"),result1.getString("QTR4_ACT_AMNT"), result1.getString("SL_NO")));
       QTR1_BDGT_AMNT_TOTAL=QTR1_BDGT_AMNT_TOTAL+result1.getDouble("QTR1_BDGT_AMNT");
       QTR1_ACT_AMNT_TOTAL=QTR1_ACT_AMNT_TOTAL+result1.getDouble("QTR1_ACT_AMNT");
       
       QTR2_BDGT_AMNT_TOTAL=QTR2_BDGT_AMNT_TOTAL+result1.getDouble("QTR2_BDGT_AMNT");
       QTR2_ACT_AMNT_TOTAL=QTR2_ACT_AMNT_TOTAL+result1.getDouble("QTR2_ACT_AMNT");
       
       QTR3_BDGT_AMNT_TOTAL=QTR3_BDGT_AMNT_TOTAL+result1.getDouble("QTR3_BDGT_AMNT");
       QTR3_ACT_AMNT_TOTAL=QTR3_ACT_AMNT_TOTAL+result1.getDouble("QTR3_ACT_AMNT");
       
       QTR4_BDGT_AMNT_TOTAL=QTR4_BDGT_AMNT_TOTAL+result1.getDouble("QTR4_BDGT_AMNT");
       QTR4_ACT_AMNT_TOTAL=QTR4_ACT_AMNT_TOTAL+result1.getDouble("QTR4_ACT_AMNT");
       
       }
       MisFunction misfun=new MisFunction();
           QTR1_ACT_AMNT_VARIATION =misfun.roundToDecimals((QTR1_BDGT_AMNT_TOTAL - QTR1_ACT_AMNT_TOTAL) / QTR1_BDGT_AMNT_TOTAL,2);
           QTR2_ACT_AMNT_VARIATION =misfun.roundToDecimals( (QTR2_BDGT_AMNT_TOTAL - QTR2_ACT_AMNT_TOTAL) / QTR2_BDGT_AMNT_TOTAL,2);
           QTR3_ACT_AMNT_VARIATION =misfun.roundToDecimals( (QTR3_BDGT_AMNT_TOTAL - QTR3_ACT_AMNT_TOTAL) / QTR3_BDGT_AMNT_TOTAL,2);
           QTR4_ACT_AMNT_VARIATION =misfun.roundToDecimals( (QTR4_BDGT_AMNT_TOTAL - QTR4_ACT_AMNT_TOTAL) / QTR4_BDGT_AMNT_TOTAL,2);
       }           
           
        } catch (Exception e) {
               flag = 0;
              
            System.out.print("2 File Name : BudgetexpensedetailAction.java" + e);

            System.out.println(e.toString());
        } finally {
            try {
                if (result1 != null) {
                    result1.close();
                }
                if (result != null) {
                    result.close();
                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (conn != null) {
                    conn.close();
                }

                result1 = null;
                stat1 = null;
                conn = null;


            } catch (Exception e) {
                System.out.print("File Name : BudgetexpensedetailAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
  getfinanceyear(FIN_YEAR);
  
  }

    public List getExpenselist() {
        return expenselist;
    }

    public void setExpenselist(List expenselist) {
        this.expenselist = expenselist;
    }

   

  

    public List getErrorlist() {
        return errorlist;
    }

    public void setErrorlist(List errorlist) {
        this.errorlist = errorlist;
    }

    public String getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
    }

    public List getDeptlist() {
        return deptlist;
    }

    public void setDeptlist(List deptlist) {
        this.deptlist = deptlist;
    }

    public List getSubdeptlist() {
        return subdeptlist;
    }

    public void setSubdeptlist(List subdeptlist) {
        this.subdeptlist = subdeptlist;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public double getBUDGET_AMOUNT() {
        return BUDGET_AMOUNT;
    }

    public void setBUDGET_AMOUNT(double BUDGET_AMOUNT) {
        this.BUDGET_AMOUNT = BUDGET_AMOUNT;
    }

    public List getEXPENSE_HEAD_LIST() {
        return EXPENSE_HEAD_LIST;
    }

    public void setEXPENSE_HEAD_LIST(List EXPENSE_HEAD_LIST) {
        this.EXPENSE_HEAD_LIST = EXPENSE_HEAD_LIST;
    }

    public String getFIN_YEAR() {
        return FIN_YEAR;
    }

    public void setFIN_YEAR(String FIN_YEAR) {
        this.FIN_YEAR = FIN_YEAR;
    }

    public String getLOCATION_CODE() {
        return LOCATION_CODE;
    }

    public void setLOCATION_CODE(String LOCATION_CODE) {
        this.LOCATION_CODE = LOCATION_CODE;
    }

    public List getQTR1_ACT_AMNT() {
        return QTR1_ACT_AMNT;
    }

    public void setQTR1_ACT_AMNT(List QTR1_ACT_AMNT) {
        this.QTR1_ACT_AMNT = QTR1_ACT_AMNT;
    }

    public double getQTR1_ACT_AMNT_TOTAL() {
        return QTR1_ACT_AMNT_TOTAL;
    }

    public void setQTR1_ACT_AMNT_TOTAL(double QTR1_ACT_AMNT_TOTAL) {
        this.QTR1_ACT_AMNT_TOTAL = QTR1_ACT_AMNT_TOTAL;
    }

    public double getQTR1_ACT_AMNT_VARIATION() {
        return QTR1_ACT_AMNT_VARIATION;
    }

    public void setQTR1_ACT_AMNT_VARIATION(double QTR1_ACT_AMNT_VARIATION) {
        this.QTR1_ACT_AMNT_VARIATION = QTR1_ACT_AMNT_VARIATION;
    }

    public List getQTR1_BDGT_AMNT() {
        return QTR1_BDGT_AMNT;
    }

    public void setQTR1_BDGT_AMNT(List QTR1_BDGT_AMNT) {
        this.QTR1_BDGT_AMNT = QTR1_BDGT_AMNT;
    }

    public double getQTR1_BDGT_AMNT_TOTAL() {
        return QTR1_BDGT_AMNT_TOTAL;
    }

    public void setQTR1_BDGT_AMNT_TOTAL(double QTR1_BDGT_AMNT_TOTAL) {
        this.QTR1_BDGT_AMNT_TOTAL = QTR1_BDGT_AMNT_TOTAL;
    }

    public List getQTR2_ACT_AMNT() {
        return QTR2_ACT_AMNT;
    }

    public void setQTR2_ACT_AMNT(List QTR2_ACT_AMNT) {
        this.QTR2_ACT_AMNT = QTR2_ACT_AMNT;
    }

    public double getQTR2_ACT_AMNT_TOTAL() {
        return QTR2_ACT_AMNT_TOTAL;
    }

    public void setQTR2_ACT_AMNT_TOTAL(double QTR2_ACT_AMNT_TOTAL) {
        this.QTR2_ACT_AMNT_TOTAL = QTR2_ACT_AMNT_TOTAL;
    }

    public double getQTR2_ACT_AMNT_VARIATION() {
        return QTR2_ACT_AMNT_VARIATION;
    }

    public void setQTR2_ACT_AMNT_VARIATION(double QTR2_ACT_AMNT_VARIATION) {
        this.QTR2_ACT_AMNT_VARIATION = QTR2_ACT_AMNT_VARIATION;
    }

    public List getQTR2_BDGT_AMNT() {
        return QTR2_BDGT_AMNT;
    }

    public void setQTR2_BDGT_AMNT(List QTR2_BDGT_AMNT) {
        this.QTR2_BDGT_AMNT = QTR2_BDGT_AMNT;
    }

    public double getQTR2_BDGT_AMNT_TOTAL() {
        return QTR2_BDGT_AMNT_TOTAL;
    }

    public void setQTR2_BDGT_AMNT_TOTAL(double QTR2_BDGT_AMNT_TOTAL) {
        this.QTR2_BDGT_AMNT_TOTAL = QTR2_BDGT_AMNT_TOTAL;
    }

    public List getQTR3_ACT_AMNT() {
        return QTR3_ACT_AMNT;
    }

    public void setQTR3_ACT_AMNT(List QTR3_ACT_AMNT) {
        this.QTR3_ACT_AMNT = QTR3_ACT_AMNT;
    }

    public double getQTR3_ACT_AMNT_TOTAL() {
        return QTR3_ACT_AMNT_TOTAL;
    }

    public void setQTR3_ACT_AMNT_TOTAL(double QTR3_ACT_AMNT_TOTAL) {
        this.QTR3_ACT_AMNT_TOTAL = QTR3_ACT_AMNT_TOTAL;
    }

    public double getQTR3_ACT_AMNT_VARIATION() {
        return QTR3_ACT_AMNT_VARIATION;
    }

    public void setQTR3_ACT_AMNT_VARIATION(double QTR3_ACT_AMNT_VARIATION) {
        this.QTR3_ACT_AMNT_VARIATION = QTR3_ACT_AMNT_VARIATION;
    }

    public List getQTR3_BDGT_AMNT() {
        return QTR3_BDGT_AMNT;
    }

    public void setQTR3_BDGT_AMNT(List QTR3_BDGT_AMNT) {
        this.QTR3_BDGT_AMNT = QTR3_BDGT_AMNT;
    }

    public double getQTR3_BDGT_AMNT_TOTAL() {
        return QTR3_BDGT_AMNT_TOTAL;
    }

    public void setQTR3_BDGT_AMNT_TOTAL(double QTR3_BDGT_AMNT_TOTAL) {
        this.QTR3_BDGT_AMNT_TOTAL = QTR3_BDGT_AMNT_TOTAL;
    }

    public List getQTR4_ACT_AMNT() {
        return QTR4_ACT_AMNT;
    }

    public void setQTR4_ACT_AMNT(List QTR4_ACT_AMNT) {
        this.QTR4_ACT_AMNT = QTR4_ACT_AMNT;
    }

    public double getQTR4_ACT_AMNT_TOTAL() {
        return QTR4_ACT_AMNT_TOTAL;
    }

    public void setQTR4_ACT_AMNT_TOTAL(double QTR4_ACT_AMNT_TOTAL) {
        this.QTR4_ACT_AMNT_TOTAL = QTR4_ACT_AMNT_TOTAL;
    }

    public double getQTR4_ACT_AMNT_VARIATION() {
        return QTR4_ACT_AMNT_VARIATION;
    }

    public void setQTR4_ACT_AMNT_VARIATION(double QTR4_ACT_AMNT_VARIATION) {
        this.QTR4_ACT_AMNT_VARIATION = QTR4_ACT_AMNT_VARIATION;
    }

    public List getQTR4_BDGT_AMNT() {
        return QTR4_BDGT_AMNT;
    }

    public void setQTR4_BDGT_AMNT(List QTR4_BDGT_AMNT) {
        this.QTR4_BDGT_AMNT = QTR4_BDGT_AMNT;
    }

    public double getQTR4_BDGT_AMNT_TOTAL() {
        return QTR4_BDGT_AMNT_TOTAL;
    }

    public void setQTR4_BDGT_AMNT_TOTAL(double QTR4_BDGT_AMNT_TOTAL) {
        this.QTR4_BDGT_AMNT_TOTAL = QTR4_BDGT_AMNT_TOTAL;
    }

    public String getSUB_DEPT_CODE() {
        return SUB_DEPT_CODE;
    }

    public void setSUB_DEPT_CODE(String SUB_DEPT_CODE) {
        this.SUB_DEPT_CODE = SUB_DEPT_CODE;
    }

    public String getSEARCH_DEPT_CODE() {
        return SEARCH_DEPT_CODE;
    }

    public void setSEARCH_DEPT_CODE(String SEARCH_DEPT_CODE) {
        this.SEARCH_DEPT_CODE = SEARCH_DEPT_CODE;
    }

    public String getSEARCH_FIN_YEAR() {
        return SEARCH_FIN_YEAR;
    }

    public void setSEARCH_FIN_YEAR(String SEARCH_FIN_YEAR) {
        this.SEARCH_FIN_YEAR = SEARCH_FIN_YEAR;
    }

    public String getSEARCH_LOCATION_CODE() {
        return SEARCH_LOCATION_CODE;
    }

    public void setSEARCH_LOCATION_CODE(String SEARCH_LOCATION_CODE) {
        this.SEARCH_LOCATION_CODE = SEARCH_LOCATION_CODE;
    }

    public String getSEARCH_SUB_DEPT_CODE() {
        return SEARCH_SUB_DEPT_CODE;
    }

    public void setSEARCH_SUB_DEPT_CODE(String SEARCH_SUB_DEPT_CODE) {
        this.SEARCH_SUB_DEPT_CODE = SEARCH_SUB_DEPT_CODE;
    }

    public double getTOTAL_ENTERED_BUDGET_AMOUNT() {
        return TOTAL_ENTERED_BUDGET_AMOUNT;
    }

    public void setTOTAL_ENTERED_BUDGET_AMOUNT(double TOTAL_ENTERED_BUDGET_AMOUNT) {
        this.TOTAL_ENTERED_BUDGET_AMOUNT = TOTAL_ENTERED_BUDGET_AMOUNT;
    }

    public List getBudgetexpensedetaillist() {
        return budgetexpensedetaillist;
    }

    public void setBudgetexpensedetaillist(List budgetexpensedetaillist) {
        this.budgetexpensedetaillist = budgetexpensedetaillist;
    }

    public String getUPDATE_FLAG() {
        return UPDATE_FLAG;
    }

    public void setUPDATE_FLAG(String UPDATE_FLAG) {
        this.UPDATE_FLAG = UPDATE_FLAG;
    }

    public List getFinyearlist() {
        return finyearlist;
    }

    public void setFinyearlist(List finyearlist) {
        this.finyearlist = finyearlist;
    }
    
   
   
    
    
   
}
