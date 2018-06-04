/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill;


import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.Fabric.Beans.FabricContentMasterBean;
import shahi.Action.M4bill.Beans.M3BILLBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHris;

/**
 *
 * @author RANJEET
 */
public class M3billAction extends ActionSupport{
    private List<M3BILLBean> deptmastlist=new ArrayList<M3BILLBean>();
    private List<M3BILLBean> mastlist=new ArrayList<M3BILLBean>();
    private String SEARCH_CODE;
    private String SEARCH_TYPE;
    private String TXTID;
    
    
    private String DEPT_CODE;
    private String DEPT_DESC;
    private String MASTFLAG;
    
  
    
    //New
    private List TYPE_DESC;
    private List TYPE_CODE;
    private List TYPEFLAG;
      //update
    private List UP_TYPE_DESC;
    private List UP_TYPE_CODE;
    private List UP_TYPEFLAG;
    private List SL_NO;
    
    
    private String MAST_SL_NO;
    private String TYPESLNO;
    private String SUBTYPESLNO;
    private String delslno;
    private String delflg;
    
    private List chkdel;
    private String S_DEPT_CODE;
     private String S_DEPT_DESC;
    
    
    
     private List<M3BILLBean> typelist=new ArrayList<M3BILLBean>();
     private List<M3BILLBean> subtypelist=new ArrayList<M3BILLBean>();
     private List<M3BILLBean> productlist=new ArrayList<M3BILLBean>();
    
    private String savesubflag;
    private String saveprodflag;
    
    private String COPYFLAG;
    
    private String COPY_DEPT_CODE;
    private String COPY_DEPT_DESC;
    
    
     public String execute() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                if(delflg!=null && delflg.equals("Yes"))
                {
                if(chkdel!=null && chkdel.size()>0)
                {
                    for(int i=0; i<chkdel.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("delete from M4_BILL_PRODUCT_MASTER where SUB_TYPE_SL_NO in(select SL_NO from M4_BILL_SUB_TYPE_MASTER where TYPE_SL_NO "
                                + "in(select SL_NO from M4_BILL_TYPE_MASTER where DEPT_SL_NO=?))");
                        stat1.setString(1, chkdel.get(i).toString());
                        int a = stat1.executeUpdate();

                        stat1 = conn.prepareStatement("delete from M4_BILL_SUB_TYPE_MASTER where TYPE_SL_NO in(select SL_NO from M4_BILL_TYPE_MASTER where DEPT_SL_NO=?)");
                        stat1.setString(1, chkdel.get(i).toString());
                        a = stat1.executeUpdate();

                        stat1 = conn.prepareStatement("delete from M4_BILL_TYPE_MASTER where DEPT_SL_NO=?");
                        stat1.setString(1, chkdel.get(i).toString());
                        a = stat1.executeUpdate();
                        
                        stat1 = conn.prepareStatement("delete from  M4_BILL_DEPT_MASTER where sl_no=?");
                        stat1.setString(1, chkdel.get(i).toString());
                        a = stat1.executeUpdate();
                   
                }
                }
                }
                
                String sqlstr=null;
                
                 if(S_DEPT_CODE!=null && S_DEPT_CODE.length()>0)
                {
                 if(sqlstr==null)
                 {
                  sqlstr=" where DEPT_CODE like '"+S_DEPT_CODE.toUpperCase()+"%'";
                 }else{
                  sqlstr=sqlstr +" and DEPT_CODE like '"+S_DEPT_CODE.toUpperCase()+"%'";
                 }
                }
                
                if(S_DEPT_DESC!=null && S_DEPT_DESC.length()>0)
                {
                 if(sqlstr==null)
                 {
                  sqlstr=" where DEPT_DESC like '"+S_DEPT_DESC.toUpperCase()+"%'";
                 }else{
                  sqlstr=sqlstr +" and DEPT_DESC like '"+S_DEPT_DESC.toUpperCase()+"%'";
                 }
                }
                
                if(sqlstr==null)
                {
                sqlstr=" ";
                }
                
                stat1=conn.prepareStatement("select SL_NO,DEPT_CODE,DEPT_DESC,FLAG,TDATE,USER_ID from M4_BILL_DEPT_MASTER "+sqlstr+" order by DEPT_DESC");
                result1=stat1.executeQuery();
                while(result1.next())
                {
                    String deptflag=null;
                    if(result1.getString("FLAG")!=null && result1.getString("FLAG").equals("Y"))
                    {
                    deptflag="Active";
                    }else{
                    deptflag="In Active";
                    }
                    String EXIST=null;
                    stat1=conn.prepareStatement("select sl_no from m4_bill_master where DEPT_SL_NO=?");
                    stat1.setString(1, result1.getString("SL_NO"));
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                     EXIST="EXIST";
                    }
                    if(result!=null)
                    {
                    result.close();
                    }
                     if(stat1!=null)
                    {
                    stat1.close();
                    }
                    
                deptmastlist.add(new M3BILLBean(result1.getString("SL_NO"),result1.getString("DEPT_CODE"),result1.getString("DEPT_DESC"),deptflag,EXIST));
                flag=1;
                }
                

                     

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name :execute M3billAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name :execute M3billAction.java" + e);

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
                    flag = 0;
                    System.out.print("File Name :execute M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
           
           
            return SUCCESS;
        } else {

            addActionMessage("Records Not Found(s) !!");
            return ERROR;
        }
     
     }
     
     
public String newmst() throws Exception {

  if((DEPT_CODE!=null && DEPT_CODE.length()>0) ||   (MAST_SL_NO!=null && MAST_SL_NO.length()>0))
  {
  getdeptmst(MAST_SL_NO);
  }
  return "newpage";
  }




  
public String newtype() throws Exception {
 if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
  {
 typelist=gettypemst(MAST_SL_NO);
  }
  
  return "newtypepage";
  }


public String subtype() throws Exception {
 if(TYPESLNO!=null && TYPESLNO.length()>0)
  {
  subtypelist=getsubtypemst(TYPESLNO);
  }
  
  return "subtypepage";
  }
public String prodtype() throws Exception {
 if(SUBTYPESLNO!=null && SUBTYPESLNO.length()>0)
  {
 productlist=getproductmst(SUBTYPESLNO);
  }
  
  return "prodtypepage";
  }

public String copymst() throws Exception {

  if(COPY_DEPT_CODE!=null && COPY_DEPT_CODE.length()>0 && (MAST_SL_NO!=null && MAST_SL_NO.length()>0))  
  {
      addmst();
  
  }
    
  if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
     {  
        getdeptmst(MAST_SL_NO);
        
     }
  return "newpage";
  }



void addmst() throws Exception{

  int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       String NEW_MAST_SL_NO=null;
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            
        }
//String slno=MAST_SL_NO;
        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
             
 
            try {
               
                stat1=conn.prepareStatement("select SL_NO from M4_BILL_DEPT_MASTER where  dept_code=?");
                stat1.setString(1, COPY_DEPT_CODE);
                result1=stat1.executeQuery();
                if(result1.next())
                {
                    NEW_MAST_SL_NO=result1.getString(1);
                    
                }else {
                    stat1=conn.prepareStatement("select M4_BILL_DEPT_MASTER_Sq.nextval from dual");
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                      NEW_MAST_SL_NO=result1.getString(1);
                    }
                    
                    stat1=conn.prepareStatement("insert into M4_BILL_DEPT_MASTER(SL_NO,DEPT_CODE,DEPT_DESC,FLAG,TDATE,USER_ID) values(?,?,?,?,sysdate,?)");
                    stat1.setString(1, NEW_MAST_SL_NO);
                    stat1.setString(2, COPY_DEPT_CODE);
                    stat1.setString(3, COPY_DEPT_DESC);
                    stat1.setString(4, MASTFLAG);
                    stat1.setString(5, usrid);
                    flag=stat1.executeUpdate();
                
                }
                
                if(NEW_MAST_SL_NO!=null && NEW_MAST_SL_NO.length()>0)
                {
                            
                    stat1=conn.prepareStatement("select * from  M4_BILL_TYPE_MASTER where DEPT_SL_NO=?");
                    stat1.setString(1, MAST_SL_NO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    
                                    String typeslno="1";
                                    stat1=conn.prepareStatement("select M4_BILL_TYPE_MASTER_Sq.nextval from dual");
                                    result1=stat1.executeQuery();
                                    if(result1.next())
                                    {
                                        typeslno=result1.getString(1);
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
                                     
                                    
                                stat1=conn.prepareStatement("insert into M4_BILL_TYPE_MASTER(SL_NO,DEPT_SL_NO,TYPE_CODE,TYPE_DESC,FLAG,TDATE,USER_ID) values(?,?,upper(?),?,?,sysdate,?)");
                                stat1.setString(1, typeslno);
                                stat1.setString(2, NEW_MAST_SL_NO);
                                stat1.setString(3, result.getString("TYPE_CODE"));
                                stat1.setString(4, result.getString("TYPE_DESC"));
                                stat1.setString(5, result.getString("FLAG"));
                                stat1.setString(6, usrid);
                                flag=stat1.executeUpdate();
                                
                                if(flag>0)
                                {
                                                     PreparedStatement   stat=conn.prepareStatement("select * from M4_BILL_SUB_TYPE_MASTER where TYPE_SL_NO=?");
                                                     stat.setString(1, result.getString("SL_NO"));
                                                     ResultSet result2=stat.executeQuery();
                                                    while(result2.next())
                                                    {         String subtypeslno=null;
                                                             PreparedStatement  stat3=conn.prepareStatement("select M4_BILL_SUB_TYPE_MASTER_Sq.nextval from dual");
                                                              result1=stat3.executeQuery();
                                                              if(result1.next())
                                                              {
                                                                  subtypeslno=result1.getString(1);
                                                              }
                                                              
                                                              if(result1!=null)
                                                            {
                                                            result1.close();
                                                            }
                                                             if(stat3!=null)
                                                            {
                                                            stat3.close();
                                                            }
                                                             result1=null;
                                                             stat1=null;
                                                              
                                                          PreparedStatement   stat4=conn.prepareStatement("insert into M4_BILL_SUB_TYPE_MASTER(SL_NO,TYPE_SL_NO,SUB_TYPE_CODE,SUB_TYPE_DESC,FLAG,TDATE,USER_ID) values(?,?,?,?,?,sysdate,?)");
                                                          stat4.setString(1, subtypeslno);
                                                          stat4.setString(2, typeslno);
                                                          stat4.setString(3,result2.getString("SUB_TYPE_CODE"));
                                                          stat4.setString(4, result2.getString("SUB_TYPE_DESC"));
                                                          stat4.setString(5, result2.getString("FLAG"));
                                                          stat4.setString(6, usrid);
                                                          flag=stat4.executeUpdate();
                                                          
                                                          if(flag>0)
                                                          {
                                                               
                                                              
                                                                         PreparedStatement   stat5=conn.prepareStatement("select * from M4_BILL_PRODUCT_MASTER where SUB_TYPE_SL_NO=?");
                                                                         stat5.setString(1, result2.getString("SL_NO"));
                                                                         ResultSet result3=stat5.executeQuery();
                                                                        while(result3.next())
                                                                        {  
                                                                         String prodslno=null;
                                                                         stat3=conn.prepareStatement("select M4_BILL_PRODUCT_MASTER_Sq.nextval from dual");
                                                                         result1=stat3.executeQuery();
                                                                         if(result1.next())
                                                                         {
                                                                             prodslno=result1.getString(1);
                                                                         }
                                                                          if(result1!=null)
                                                                        {
                                                                        result1.close();
                                                                        }
                                                                         if(stat3!=null)
                                                                        {
                                                                        stat3.close();
                                                                        }
                                                                         result1=null;
                                                                         stat1=null;
                                                                         
                                                                     stat1=conn.prepareStatement("insert into M4_BILL_PRODUCT_MASTER(SL_NO,SUB_TYPE_SL_NO,PRODUCT_CODE,PRODUCT_DESC,FLAG,TDATE,USER_ID) values(?,?,upper(?),?,?,sysdate,?)");
                                                                     stat1.setString(1, prodslno);
                                                                     stat1.setString(2, subtypeslno);
                                                                     stat1.setString(3, result3.getString("PRODUCT_CODE"));
                                                                     stat1.setString(4, result3.getString("PRODUCT_DESC"));
                                                                     stat1.setString(5, result3.getString("FLAG"));
                                                                     stat1.setString(6, usrid);
                                                                     flag=stat1.executeUpdate();
                                                               }
                                                          }
                                                    }
                                }
                                
                    }
                
                }
                
                
              conn.commit();

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name :addmst() copy.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name :addmst() copy.java" + e);

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
                    flag = 0;
                    System.out.print("File Name :addmst() copy.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

          

        }

        if (flag > 0) {
            MAST_SL_NO=NEW_MAST_SL_NO;
            DEPT_CODE=COPY_DEPT_CODE;
            COPYFLAG="";
            addActionMessage("Records  Save(s) !!");
            //return SUCCESS;
        } else {

            addActionMessage("Records Not Save(s) !!");
            //return ERROR;
        }
        
 //  if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
    // {  
       // getdeptmst(MAST_SL_NO);
        
    // }
    
}



public String searchpage() throws Exception {
  if(SEARCH_TYPE!=null && SEARCH_TYPE.equals("3"))
  {
      mastlist=getdeptlist(SEARCH_CODE,SEARCH_TYPE);
      
  }else if (SEARCH_TYPE!=null && SEARCH_TYPE.equals("10"))
  {
  mastlist=getdeptlistcopy(SEARCH_CODE,SEARCH_TYPE);
  }
  else{
    mastlist=getmasterlist(SEARCH_CODE,SEARCH_TYPE);
  }
  
  return "searchpage";
  }



public String savemst() throws Exception {

       int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
//String slno=MAST_SL_NO;
        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
 
            try {
                if(DEPT_CODE!=null && DEPT_CODE.length()>0)
                {
                   
                    if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
                    {
                        
                        
                        
                    stat1=conn.prepareStatement("update M4_BILL_DEPT_MASTER set DEPT_CODE=?,DEPT_DESC=?,FLAG=?,TDATE=sysdate,USER_ID=? where sl_no=?");
                    stat1.setString(1, DEPT_CODE);
                    stat1.setString(2, DEPT_DESC);
                    stat1.setString(3, MASTFLAG);
                    stat1.setString(4, usrid);
                    stat1.setString(5, MAST_SL_NO);
                    flag=stat1.executeUpdate();
                    
                    
                    }else{
                   
                    stat1=conn.prepareStatement("select M4_BILL_DEPT_MASTER_Sq.nextval from dual");
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                      MAST_SL_NO=result1.getString(1);
                    }
                    
                    stat1=conn.prepareStatement("insert into M4_BILL_DEPT_MASTER(SL_NO,DEPT_CODE,DEPT_DESC,FLAG,TDATE,USER_ID) values(?,?,?,?,sysdate,?)");
                    stat1.setString(1, MAST_SL_NO);
                    stat1.setString(2, DEPT_CODE);
                    stat1.setString(3, DEPT_DESC);
                    stat1.setString(4, MASTFLAG);
                    stat1.setString(5, usrid);
                    flag=stat1.executeUpdate();
                    }
                    
                    if(flag>0)
                    {
                      
                        if(UP_TYPE_CODE!=null && UP_TYPE_CODE.size()>0)
                        {
                        for(int i=0; i<UP_TYPE_CODE.size(); i++)
                        {
                            if(UP_TYPE_DESC.get(i).toString()!=null && UP_TYPE_DESC.get(i).toString().length()>0)
                            {
                                   
                                stat1=conn.prepareStatement("update  M4_BILL_TYPE_MASTER set  TYPE_CODE=upper(?),TYPE_DESC=?,FLAG=?,TDATE=sysdate,USER_ID=? where SL_NO=? and DEPT_SL_NO=?");
                                
                                stat1.setString(1, UP_TYPE_CODE.get(i).toString());
                                stat1.setString(2, UP_TYPE_DESC.get(i).toString().toUpperCase());
                                stat1.setString(3, UP_TYPEFLAG.get(i).toString());
                                stat1.setString(4, usrid);
                                stat1.setString(5, SL_NO.get(i).toString());
                                stat1.setString(6, MAST_SL_NO);
                                flag=stat1.executeUpdate();
                            }
                        }
                    }
                        if(TYPE_CODE!=null && TYPE_CODE.size()>0)
                        {
                        for(int i=0; i<TYPE_CODE.size(); i++)
                        {
                            if(TYPE_DESC.get(i).toString()!=null && TYPE_DESC.get(i).toString().length()>0 )
                            {
                                    String typeslno="1";
                                    stat1=conn.prepareStatement("select M4_BILL_TYPE_MASTER_Sq.nextval from dual");
                                    result1=stat1.executeQuery();
                                    if(result1.next())
                                    {
                                        typeslno=result1.getString(1);
                                    }
                                stat1=conn.prepareStatement("insert into M4_BILL_TYPE_MASTER(SL_NO,DEPT_SL_NO,TYPE_CODE,TYPE_DESC,FLAG,TDATE,USER_ID) values(?,?,upper(?),?,?,sysdate,?)");
                                stat1.setString(1, typeslno);
                                stat1.setString(2, MAST_SL_NO);
                                stat1.setString(3, TYPE_CODE.get(i).toString());
                                stat1.setString(4, TYPE_DESC.get(i).toString().toUpperCase());
                                stat1.setString(5, TYPEFLAG.get(i).toString());
                                stat1.setString(6, usrid);
                                flag=stat1.executeUpdate();
                            }
                        }}
                    
                    }
                  
                }
                
                if(delslno!=null && delslno.length()>0)
                {
                stat1=conn.prepareStatement("delete from  M4_BILL_TYPE_MASTER where sl_no=?");
                stat1.setString(1, delslno);
                flag=stat1.executeUpdate();
                }
              conn.commit();

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : savemst() M3billAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : savemst() M3billAction.java" + e);

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
                    flag = 0;
                    System.out.print("File Name : savemst() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return "newpage";

        }

        if (flag == 1) {
           DEPT_CODE="";
           DEPT_DESC="";
           addActionMessage("Records  Save(s) !!");
            //return SUCCESS;
        } else {

            addActionMessage("Records Not Save(s) !!");
            //return ERROR;
        }
        
   if(MAST_SL_NO!=null && MAST_SL_NO.length()>0)
     {  
        getdeptmst(MAST_SL_NO);
        
     }
   return "newpage";

  }





        public String savesub() throws Exception {

       int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                if(TYPESLNO!=null && TYPESLNO.length()>0)
                {
                    
                    if(UP_TYPE_CODE!=null && UP_TYPE_CODE.size()>0)
                        {
                        for(int i=0; i<UP_TYPE_CODE.size(); i++)
                        {
                            if(UP_TYPE_DESC.get(i).toString()!=null && UP_TYPE_DESC.get(i).toString().length()>0)
                            {
                                   
                                stat1=conn.prepareStatement("update  M4_BILL_SUB_TYPE_MASTER set  SUB_TYPE_CODE=upper(?),SUB_TYPE_DESC=?,FLAG=?,TDATE=sysdate,USER_ID=? where SL_NO=? and TYPE_SL_NO=?");
                                
                                stat1.setString(1, UP_TYPE_CODE.get(i).toString());
                                stat1.setString(2, UP_TYPE_DESC.get(i).toString().toUpperCase());
                                stat1.setString(3, UP_TYPEFLAG.get(i).toString());
                                stat1.setString(4, usrid);
                                stat1.setString(5, SL_NO.get(i).toString());
                                stat1.setString(6, TYPESLNO);
                                flag=stat1.executeUpdate();
                            }
                        }
                    }
                    
                    
                    
                    if(TYPE_CODE!=null && TYPE_CODE.size()>0)
                    {
                        for(int i=0; i<TYPE_CODE.size(); i++)
                        {
                            if(TYPE_DESC.get(i).toString()!=null && TYPE_DESC.get(i).toString().length()>0)
                            {
                                    String typeslno="1";
                                    stat1=conn.prepareStatement("select M4_BILL_SUB_TYPE_MASTER_Sq.nextval from dual");
                                    result1=stat1.executeQuery();
                                    if(result1.next())
                                    {
                                        typeslno=result1.getString(1);
                                    }
                                stat1=conn.prepareStatement("insert into M4_BILL_SUB_TYPE_MASTER(SL_NO,TYPE_SL_NO,SUB_TYPE_CODE,SUB_TYPE_DESC,FLAG,TDATE,USER_ID) values(?,?,?,?,?,sysdate,?)");
                                stat1.setString(1, typeslno);
                                stat1.setString(2, TYPESLNO);
                                stat1.setString(3, TYPE_CODE.get(i).toString());
                                stat1.setString(4, TYPE_DESC.get(i).toString().toUpperCase());
                                stat1.setString(5, TYPEFLAG.get(i).toString());
                                stat1.setString(6, usrid);
                                flag=stat1.executeUpdate();
                            }
                        }
                }
                    if(delslno!=null && delslno.length()>0)
                {
                stat1=conn.prepareStatement("delete from  M4_BILL_SUB_TYPE_MASTER where sl_no=?");
                stat1.setString(1, delslno);
                flag=stat1.executeUpdate();
                }
                  
                }
                
                
              conn.commit();

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name :savesub M3billAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name :savesub M3billAction.java" + e);

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
                    flag = 0;
                    System.out.print("File Name :savesub M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return "subtypepage";

        }

        if (flag == 1) {
        
           addActionMessage("Records  Save(s) !!");
            //return SUCCESS;
        } else {

            addActionMessage("Records Not Save(s) !!");
            //return ERROR;
        }
     
   if(TYPESLNO!=null && TYPESLNO.length()>0)
  {
  subtypelist=getsubtypemst(TYPESLNO);
  }
  savesubflag=TYPESLNO;
   return "subtypepage";
  
   
  }

  public String saveprod() throws Exception {

       int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                if(SUBTYPESLNO!=null && SUBTYPESLNO.length()>0)
                {
                    
                    
                     if(UP_TYPE_CODE!=null && UP_TYPE_CODE.size()>0)
                        {
                        for(int i=0; i<UP_TYPE_CODE.size(); i++)
                        {
                            if(UP_TYPE_DESC.get(i).toString()!=null && UP_TYPE_DESC.get(i).toString().length()>0)
                            {
                                   
                                stat1=conn.prepareStatement("update  M4_BILL_PRODUCT_MASTER set  PRODUCT_CODE=upper(?),PRODUCT_DESC=?,FLAG=?,TDATE=sysdate,USER_ID=? where SL_NO=? and SUB_TYPE_SL_NO=?");
                                
                                stat1.setString(1, UP_TYPE_CODE.get(i).toString());
                                stat1.setString(2, UP_TYPE_DESC.get(i).toString().toUpperCase());
                                stat1.setString(3, UP_TYPEFLAG.get(i).toString());
                                stat1.setString(4, usrid);
                                stat1.setString(5, SL_NO.get(i).toString());
                                stat1.setString(6, SUBTYPESLNO);
                                flag=stat1.executeUpdate();
                            }
                        }
                    }
                     if(TYPE_CODE!=null && TYPE_CODE.size()>0)
                     {
                        for(int i=0; i<TYPE_CODE.size(); i++)
                        {
                            if(TYPE_DESC.get(i).toString()!=null && TYPE_DESC.get(i).toString().length()>0 )
                            {
                                    String typeslno="1";
                                    stat1=conn.prepareStatement("select M4_BILL_PRODUCT_MASTER_Sq.nextval from dual");
                                    result1=stat1.executeQuery();
                                    if(result1.next())
                                    {
                                        typeslno=result1.getString(1);
                                    }
                                stat1=conn.prepareStatement("insert into M4_BILL_PRODUCT_MASTER(SL_NO,SUB_TYPE_SL_NO,PRODUCT_CODE,PRODUCT_DESC,FLAG,TDATE,USER_ID) values(?,?,upper(?),?,?,sysdate,?)");
                                stat1.setString(1, typeslno);
                                stat1.setString(2, SUBTYPESLNO);
                                stat1.setString(3, TYPE_CODE.get(i).toString());
                                stat1.setString(4, TYPE_DESC.get(i).toString().toUpperCase());
                                stat1.setString(5, TYPEFLAG.get(i).toString());
                                stat1.setString(6, usrid);
                                flag=stat1.executeUpdate();
                            }
                        }
                }
                       if(delslno!=null && delslno.length()>0)
                {
                stat1=conn.prepareStatement("delete from  M4_BILL_PRODUCT_MASTER where sl_no=?");
                stat1.setString(1, delslno);
                flag=stat1.executeUpdate();
                } 
                  
                }
              conn.commit();

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name :saveprod() M3billAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name :saveprod() M3billAction.java" + e);

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
                    flag = 0;
                    System.out.print("File Name :saveprod() M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return "prodtypepage";

        }

        if (flag == 1) {
        
           addActionMessage("Records  Save(s) !!");
            //return SUCCESS;
        } else {

            addActionMessage("Records Not Save(s) !!");
            //return ERROR;
        }
     saveprodflag=SUBTYPESLNO;
     if(SUBTYPESLNO!=null && SUBTYPESLNO.length()>0)
  {
   productlist=getproductmst(SUBTYPESLNO);
  }
  
   return "prodtypepage";
  
   
  }       
        

  void getdeptmst(String MASTSLNO) throws Exception {

       

        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                if(DEPT_CODE!=null && DEPT_CODE.length()>0)
                {
                
                    stat1=conn.prepareStatement("select * from  M4_BILL_DEPT_MASTER where DEPT_CODE=?");
                    stat1.setString(1, DEPT_CODE);
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                        DEPT_CODE=result.getString("DEPT_CODE");
                        DEPT_DESC=result.getString("DEPT_DESC");
                        MASTFLAG=result.getString("FLAG");
                        MAST_SL_NO=result.getString("SL_NO");
                    }
                }else{
                
                    stat1=conn.prepareStatement("select * from  M4_BILL_DEPT_MASTER where SL_NO=?");
                    stat1.setString(1, MASTSLNO);
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                        DEPT_CODE=result.getString("DEPT_CODE");
                        DEPT_DESC=result.getString("DEPT_DESC");
                        MASTFLAG=result.getString("FLAG");
                        MAST_SL_NO=result.getString("SL_NO");
                    }
                }
                   
                   

            } catch (Exception e) {

                System.out.print("1 file name :getdeptmst M3billAction.java" + e);

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
                  
                    System.out.print("File Name :getdeptmst M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      
  }

private List gettypemst(String SLNO) throws Exception {

       List listmst=new ArrayList();

        try {

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    stat1=conn.prepareStatement("select SL_NO,DEPT_SL_NO,TYPE_CODE,TYPE_DESC,FLAG from M4_BILL_type_MASTER where DEPT_SL_NO=? order by  SL_NO");
                    stat1.setString(1, SLNO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                       stat1=conn.prepareStatement("select * from M4_BILL_SUB_TYPE_MASTER where TYPE_SL_NO=?");
                       stat1.setString(1, result.getString("SL_NO"));
                       result1=stat1.executeQuery();
                       String EXIST=null;
                       if(result1.next())
                       {
                       EXIST="EXIST";
                       }
                       if(result1!=null)
                       {
                       result1.close();
                       }
                       if(stat1!=null)
                       {
                       stat1.close();
                       }
                        
                      listmst.add(new M3BILLBean(result.getString("SL_NO"), result.getString("DEPT_SL_NO"), result.getString("TYPE_CODE"), result.getString("TYPE_DESC"), result.getString("FLAG"),EXIST));
                    }
                    
                   
                   

            } catch (Exception e) {

                System.out.print("1 file name :gettypemst M3billAction.java" + e);

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
                  
                    System.out.print("File Name :gettypemst M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      return listmst;
  }

private List getsubtypemst(String SLNO) throws Exception {

       List listmst=new ArrayList();

        try {

            Connection conn = null;
             Connection connbi = null;

            try {
                
                conn = new ConnectionSeplWeb().getConnection();
                
                connbi = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    stat1=conn.prepareStatement("select SL_NO,TYPE_SL_NO,SUB_TYPE_CODE,SUB_TYPE_DESC,FLAG from M4_BILL_SUB_TYPE_MASTER where TYPE_SL_NO=? order by  SL_NO");
                    stat1.setString(1, SLNO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                      
                       stat1=connbi.prepareStatement("select EAAITM,EATX40 from prodbi.fchacc where EACONO=111 and EAAITP=1 and eaat12=0 and EAAITM=?");
                       stat1.setString(1, result.getString("SUB_TYPE_CODE"));
                       result1=stat1.executeQuery();
                       String DESC=null;
                       if(result1.next())
                       {
                       DESC=result1.getString(2);
                       }
                       if(result1!=null)
                       {
                       result1.close();
                       }
                       if(stat1!=null)
                       {
                       stat1.close();
                       } 
                        
                        
                        
                       stat1=conn.prepareStatement("select * from M4_BILL_PRODUCT_MASTER where SUB_TYPE_SL_NO=?");
                       stat1.setString(1, result.getString("SL_NO"));
                       result1=stat1.executeQuery();
                       String EXIST=null;
                       if(result1.next())
                       {
                       EXIST="EXIST";
                       }
                       if(result1!=null)
                       {
                       result1.close();
                       }
                       if(stat1!=null)
                       {
                       stat1.close();
                       }
                        
                      listmst.add(new M3BILLBean(result.getString("SL_NO"), result.getString("TYPE_SL_NO"), result.getString("SUB_TYPE_CODE"), result.getString("SUB_TYPE_DESC"), result.getString("FLAG"),EXIST,DESC));
                    }
                    
                   
                   

            } catch (Exception e) {

                System.out.print("1 file name :getsubtypemst M3billAction.java" + e);

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
                     if (connbi != null) {
                        connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;
                    connbi=null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :getsubtypemst M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      return listmst;
  }

private List getproductmst(String SLNO) throws Exception {

       List listmst=new ArrayList();

        try {

            Connection conn = null;
             Connection connbi = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                connbi = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                
                    stat1=conn.prepareStatement("select SL_NO,SUB_TYPE_SL_NO,PRODUCT_CODE,PRODUCT_DESC,FLAG from m4_BILL_PRODUCT_MASTER where SUB_TYPE_SL_NO=? order by  SL_NO");
                    stat1.setString(1, SLNO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                       stat1=connbi.prepareStatement("select EAAITM,EATX40 from prodbi.fchacc where EACONO=111 and EAAITP=4 and eaat12=0 and EAAITM=?");
                       stat1.setString(1, result.getString("PRODUCT_CODE"));
                       result1=stat1.executeQuery();
                       String DESC=null;
                       if(result1.next())
                       {
                       DESC=result1.getString(2);
                       }
                       
                       if(result1!=null)
                       {
                       result1.close();
                       }
                       if(stat1!=null)
                       {
                       stat1.close();
                       } 
                        
                      listmst.add(new M3BILLBean(result.getString("SL_NO"), result.getString("SUB_TYPE_SL_NO"), result.getString("PRODUCT_CODE"), result.getString("PRODUCT_DESC"), result.getString("FLAG"),DESC));
                    }
                    
                   
                   

            } catch (Exception e) {

                System.out.print("1 file name :getproductmst M3billAction.java" + e);

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
                    if(connbi!=null)
                    {
                    connbi.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name :getproductmst M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            //return "newpage";

        }

      return listmst;
  }


 private List getmasterlist(String desc,String masttype)
 {
   List mastlist=new ArrayList();
  
            Connection conn = null;

            try {
                conn = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
            
                if(desc!=null && desc.length()>0 && masttype!=null && masttype.length()>0)
                {
                    desc="%"+desc.toUpperCase()+"%";
                    stat1=conn.prepareStatement("select EAAITM,EATX40 from prodbi.fchacc where EACONO=111 and EAAITP=? and ealccd=0 and (upper(EATX40) like '"+desc+"' or EAAITM  like '"+desc+"' ) order by 2");
                    stat1.setString(1, masttype);
                   
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    }
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    addActionMessage("Record Not Found(s)");
                    }
                }
            }catch (Exception e) {

               
                System.out.print("1 file name :getmasterlist M3billAction.java" + e);

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
                   
                    System.out.print("File Name :getmasterlist M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }
 
 
 
 private List getdeptlist(String desc,String masttype)
 {
   List mastlist=new ArrayList();
  

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
            
                if(desc!=null && desc.length()>0)
                {
                    desc="where upper(DEPT_NAME) like '%"+desc.toUpperCase()+"%'" ;
                }
                if(desc==null)
                {
                desc=" ";
                }
                  stat1=conn.prepareStatement("select SL_NO,DEPT_NAME from m4_dept_master "+desc+" order by DEPT_NAME");
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    }
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    addActionMessage("Record Not Found(s)");
                    }
                
            }catch (Exception e) {

               
                System.out.print("1 file name :getdeptlist M3billAction.java" + e);

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
                   
                    System.out.print("File Name :getdeptlist M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }
    
 
 
 
 private List getdeptlistcopy(String desc,String masttype)
 {
   List mastlist=new ArrayList();
  

            Connection conn = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
            
                if(desc!=null && desc.length()>0)
                {
                    desc="where upper(DEPT_NAME) like '%"+desc.toUpperCase()+"%'" ;
                }
                if(desc==null)
                {
                desc=" ";
                }
                  stat1=conn.prepareStatement("select SL_NO,DEPT_NAME from m4_dept_master "+desc+" order by DEPT_NAME");
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    }
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    addActionMessage("Record Not Found(s)");
                    }
                
            }catch (Exception e) {

               
                System.out.print("1 file name :getdeptlistcopy M3billAction.java" + e);

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
                   
                    System.out.print("File Name :getdeptlistcopy M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }
    public List<M3BILLBean> getDeptmastlist() {
        return deptmastlist;
    }

    public void setDeptmastlist(List<M3BILLBean> deptmastlist) {
        this.deptmastlist = deptmastlist;
    }

    public List<M3BILLBean> getMastlist() {
        return mastlist;
    }

    public void setMastlist(List<M3BILLBean> mastlist) {
        this.mastlist = mastlist;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public String getSEARCH_TYPE() {
        return SEARCH_TYPE;
    }

    public void setSEARCH_TYPE(String SEARCH_TYPE) {
        this.SEARCH_TYPE = SEARCH_TYPE;
    }

    public String getTXTID() {
        return TXTID;
    }

    public void setTXTID(String TXTID) {
        this.TXTID = TXTID;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public String getDEPT_DESC() {
        return DEPT_DESC;
    }

    public void setDEPT_DESC(String DEPT_DESC) {
        this.DEPT_DESC = DEPT_DESC;
    }

    public String getMASTFLAG() {
        return MASTFLAG;
    }

    public void setMASTFLAG(String MASTFLAG) {
        this.MASTFLAG = MASTFLAG;
    }

    public List getTYPE_DESC() {
        return TYPE_DESC;
    }

    public void setTYPE_DESC(List TYPE_DESC) {
        this.TYPE_DESC = TYPE_DESC;
    }

    public List getTYPE_CODE() {
        return TYPE_CODE;
    }

    public void setTYPE_CODE(List TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public List getTYPEFLAG() {
        return TYPEFLAG;
    }

    public void setTYPEFLAG(List TYPEFLAG) {
        this.TYPEFLAG = TYPEFLAG;
    }

    public String getMAST_SL_NO() {
        return MAST_SL_NO;
    }

    public void setMAST_SL_NO(String MAST_SL_NO) {
        this.MAST_SL_NO = MAST_SL_NO;
    }

    public List<M3BILLBean> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<M3BILLBean> typelist) {
        this.typelist = typelist;
    }

    public List<M3BILLBean> getSubtypelist() {
        return subtypelist;
    }

    public void setSubtypelist(List<M3BILLBean> subtypelist) {
        this.subtypelist = subtypelist;
    }

    public List<M3BILLBean> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<M3BILLBean> productlist) {
        this.productlist = productlist;
    }

    public String getTYPESLNO() {
        return TYPESLNO;
    }

    public void setTYPESLNO(String TYPESLNO) {
        this.TYPESLNO = TYPESLNO;
    }

    public String getSUBTYPESLNO() {
        return SUBTYPESLNO;
    }

    public void setSUBTYPESLNO(String SUBTYPESLNO) {
        this.SUBTYPESLNO = SUBTYPESLNO;
    }

    public List getUP_TYPE_DESC() {
        return UP_TYPE_DESC;
    }

    public void setUP_TYPE_DESC(List UP_TYPE_DESC) {
        this.UP_TYPE_DESC = UP_TYPE_DESC;
    }

    public List getUP_TYPE_CODE() {
        return UP_TYPE_CODE;
    }

    public void setUP_TYPE_CODE(List UP_TYPE_CODE) {
        this.UP_TYPE_CODE = UP_TYPE_CODE;
    }

    public List getUP_TYPEFLAG() {
        return UP_TYPEFLAG;
    }

    public void setUP_TYPEFLAG(List UP_TYPEFLAG) {
        this.UP_TYPEFLAG = UP_TYPEFLAG;
    }

   

    public List getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(List SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getDelslno() {
        return delslno;
    }

    public void setDelslno(String delslno) {
        this.delslno = delslno;
    }

    public String getDelflg() {
        return delflg;
    }

    public void setDelflg(String delflg) {
        this.delflg = delflg;
    }

    public List getChkdel() {
        return chkdel;
    }

    public void setChkdel(List chkdel) {
        this.chkdel = chkdel;
    }

    public String getS_DEPT_CODE() {
        return S_DEPT_CODE;
    }

    public void setS_DEPT_CODE(String S_DEPT_CODE) {
        this.S_DEPT_CODE = S_DEPT_CODE;
    }

    public String getS_DEPT_DESC() {
        return S_DEPT_DESC;
    }

    public void setS_DEPT_DESC(String S_DEPT_DESC) {
        this.S_DEPT_DESC = S_DEPT_DESC;
    }

    public String getSavesubflag() {
        return savesubflag;
    }

    public void setSavesubflag(String savesubflag) {
        this.savesubflag = savesubflag;
    }

    public String getSaveprodflag() {
        return saveprodflag;
    }

    public void setSaveprodflag(String saveprodflag) {
        this.saveprodflag = saveprodflag;
    }

    public String getCOPYFLAG() {
        return COPYFLAG;
    }

    public void setCOPYFLAG(String COPYFLAG) {
        this.COPYFLAG = COPYFLAG;
    }

    public String getCOPY_DEPT_CODE() {
        return COPY_DEPT_CODE;
    }

    public void setCOPY_DEPT_CODE(String COPY_DEPT_CODE) {
        this.COPY_DEPT_CODE = COPY_DEPT_CODE;
    }

    public String getCOPY_DEPT_DESC() {
        return COPY_DEPT_DESC;
    }

    public void setCOPY_DEPT_DESC(String COPY_DEPT_DESC) {
        this.COPY_DEPT_DESC = COPY_DEPT_DESC;
    }

    
     
    
}
