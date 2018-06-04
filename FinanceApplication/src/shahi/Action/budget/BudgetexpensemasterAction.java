/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.budget;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import shahi.Action.budget.Beans.BudgetmasterBean;
import shahi.Action.database.ConnectionShahiHris;

/**
 *
 * @author Ranjeet Singh
 */
public class BudgetexpensemasterAction extends ActionSupport {
    
    private String SEARCH_EXPENSE_HEAD;
    private String SEARCH_EXPENSE_DESC;
    
    private List EXPENSE_HEAD;
    private List EXPENSE_DESC;
    
    
    private List errorlist=new ArrayList();
     private List expenselist=new ArrayList();
     private String deleteflag;
     private List chkdel;
   
  
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
       
       String sqlstr=null;
       
       if(SEARCH_EXPENSE_HEAD!=null && SEARCH_EXPENSE_HEAD.length()>0)
       {
       if(sqlstr==null)
       {
       sqlstr=" where EXPENSE_HEAD='"+SEARCH_EXPENSE_HEAD+"'";
       }
       }
       
       if(SEARCH_EXPENSE_DESC!=null && SEARCH_EXPENSE_DESC.length()>0)
       {
       if(sqlstr==null)
       {
       sqlstr="where EXPENSE_DESC like '"+SEARCH_EXPENSE_DESC+"%'";
       }else{
        sqlstr=sqlstr+ " and EXPENSE_DESC like '"+SEARCH_EXPENSE_DESC+"%'";
       }
       }
       
       if(sqlstr==null)
       {
       sqlstr=" ";
       }
       
       try{
           
           if(deleteflag!=null && deleteflag.equals("Yes") && chkdel!=null && chkdel.size()>0)
           {
           for(int i=0; i<chkdel.size(); i++)
           {
               if(chkdel.get(i).toString()!=null && chkdel.get(i).toString().length()>0 )
               {
               stat1=conn.prepareStatement("delete from BUDGET_EXPENSE_MASTER where sl_no=?");
               stat1.setString(1, chkdel.get(i).toString());
               flag=stat1.executeUpdate();
               
               }
           
           
           }
           
           }
           
            stat1=conn.prepareStatement("select * from BUDGET_EXPENSE_MASTER "+sqlstr.toUpperCase() +" order by 1");
            result=stat1.executeQuery();
            while(result.next())
            {
                String st="No";
                stat1=conn.prepareStatement("select EXPENSE_HEAD from  BUDGET_DETAIL where EXPENSE_HEAD=?");
                stat1.setString(1, result.getString("EXPENSE_HEAD"));
                result1=stat1.executeQuery();
                if(result1.next())
                {
                 st="Yes";   
                }
                if(result1!=null)
                {
                result1.close();
                }
                if(stat1!=null)
                {
                stat1.close();
                }
                result1=null;
                stat1=null;
                expenselist.add(new BudgetmasterBean(result.getString("SL_NO"),result.getString("EXPENSE_HEAD"),result.getString("EXPENSE_DESC"),st));
            
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
         
        
    return "newinput";
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
           
           if(EXPENSE_HEAD!=null && EXPENSE_HEAD.size()>0)
           {
           for(int i=0; i<EXPENSE_HEAD.size(); i++)
           {
            if(EXPENSE_HEAD.get(i)!=null && EXPENSE_HEAD.get(i).toString().length()>0 && EXPENSE_DESC.get(i)!=null && EXPENSE_DESC.get(i).toString().length()>0 )
            {
              stat1=conn.prepareStatement("select EXPENSE_HEAD from BUDGET_EXPENSE_MASTER where EXPENSE_HEAD=?");
              stat1.setString(1,EXPENSE_HEAD.get(i).toString().toUpperCase());
              result=stat1.executeQuery();
              if(result.next())
              {
              errorlist.add(EXPENSE_HEAD.get(i).toString().toUpperCase());
                  
              }else{
              
                stat1=conn.prepareStatement("select  nvl(max(SL_NO),0)+1 from BUDGET_EXPENSE_MASTER");
                result1=stat1.executeQuery();
                if(result1.next())
                {
                stat1=conn.prepareStatement("insert into BUDGET_EXPENSE_MASTER(SL_NO,EXPENSE_HEAD,EXPENSE_DESC,USER_ID,TDATE) values(?,?,?,?,sysdate)");
                stat1.setString(1, result1.getString(1));
                stat1.setString(2,EXPENSE_HEAD.get(i).toString().toUpperCase());
                stat1.setString(3,EXPENSE_DESC.get(i).toString().toUpperCase());
                stat1.setString(4,usrid);
                flag=stat1.executeUpdate();
                }
             }
            }
           
           }
           
           }
           
           if (flag > 0) {
               addActionMessage("Record  Saved...");
               EXPENSE_HEAD = null;
               EXPENSE_DESC = null;

           } else {

               addActionError("Record Not Saved...");
           }
           
               conn.commit();

        } catch (Exception e) {
               flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : BudgetexpensemasterAction.java" + ee);

                    System.out.println(ee.toString());
                }
            System.out.print("2 File Name : BudgetexpensemasterAction.java" + e);

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
                System.out.print("File Name : BudgetexpensemasterAction.java Exception in finally block");
                System.out.println(e.toString());
            }
        }
    return "newinput";
    }

    public String getSEARCH_EXPENSE_DESC() {
        return SEARCH_EXPENSE_DESC;
    }

    public void setSEARCH_EXPENSE_DESC(String SEARCH_EXPENSE_DESC) {
        this.SEARCH_EXPENSE_DESC = SEARCH_EXPENSE_DESC;
    }

    public String getSEARCH_EXPENSE_HEAD() {
        return SEARCH_EXPENSE_HEAD;
    }

    public void setSEARCH_EXPENSE_HEAD(String SEARCH_EXPENSE_HEAD) {
        this.SEARCH_EXPENSE_HEAD = SEARCH_EXPENSE_HEAD;
    }

    public List getExpenselist() {
        return expenselist;
    }

    public void setExpenselist(List expenselist) {
        this.expenselist = expenselist;
    }

   

    public List getEXPENSE_DESC() {
        return EXPENSE_DESC;
    }

    public void setEXPENSE_DESC(List EXPENSE_DESC) {
        this.EXPENSE_DESC = EXPENSE_DESC;
    }

    public List getEXPENSE_HEAD() {
        return EXPENSE_HEAD;
    }

    public void setEXPENSE_HEAD(List EXPENSE_HEAD) {
        this.EXPENSE_HEAD = EXPENSE_HEAD;
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
    
   
   
   
}
