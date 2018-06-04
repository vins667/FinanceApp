/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.M4bill.Beans.M3BILLBean;
import shahi.Action.Mis.ShahiInformationList;
import shahi.Action.database.ConnectionSeplWeb;

import shahi.Action.database.ConnectionShahiHris;
import shahi.Action.database.connection;

/**
 *
 * @author RANJEET
 */
public class M3BillUserAction  extends ActionSupport {
    
    private List warehouselist = new ArrayList();
    private List savewarehouselist = new ArrayList();
    private List deptlist = new ArrayList();
    private List savedeptlist = new ArrayList();
    private List<M3BILLBean> typelist = new ArrayList<M3BILLBean>();
     private List<M3BILLBean> typedeptlist = new ArrayList<M3BILLBean>();
     private List<M3BILLBean> savetypelist = new ArrayList<M3BILLBean>();
     private List<M3BILLBean> userlist = new ArrayList<M3BILLBean>();
    private List DEPT_SL_NO;
    private List savemastlist=new ArrayList();
    private String EMP_CODE;
    private String EMP_NAME;
    private List WAREHOUSE;
    private List BILLTYPECODE;
    private String ACCESS_FLAG;
    private String ACTIVE_FLAG;
    
    private String S_EMP;
    private String S_WHLO;
    private String S_DEPT_DESC;
    
   
    
    
     public String execute() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        if(EMP_CODE!=null && EMP_CODE.length()>0)
        {
           Connection conn = null;

            try {
                conn = new connection().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
               stat1=conn.prepareStatement("select * from seh_web_users where user_id=?");
               stat1.setString(1,EMP_CODE);
               result1=stat1.executeQuery();
               if(result1.next())
               {
                 EMP_NAME=result1.getString("USER_NAME")  ;   
                 flag=1;
               }
                
             } catch (Exception e) {

                System.out.print("1 file name : M3billAction.java" + e);

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
                  
                    System.out.print("File Name : M3billUserAction.java Exception in finally block");
                    e.printStackTrace();
                }
            } 
        
            
            if(flag==0)
            {
              addActionMessage("Record Not Found(s)");
            }
        }
        if(EMP_NAME!=null && EMP_NAME.length()>0)
        {
        warehouselist=getwarehouselist();
        deptlist=getdeptlist();
        }
        return "newpage";
     }
  
   public String exenew() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
       EMP_CODE=null;
       EMP_NAME=null;
       ACTIVE_FLAG=null;
       ACCESS_FLAG=null;
        return "newpage";
     }  
     
     
 public String save() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        if(EMP_CODE!=null && EMP_CODE.length()>0)
        {
           Connection conn = null;
            Connection conn1 = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                 conn1 = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
               stat1=conn1.prepareStatement("select * from seh_web_users where user_id=?");
               stat1.setString(1,EMP_CODE);
               result1=stat1.executeQuery();
               if(result1.next())
               {
                 EMP_NAME=result1.getString("USER_NAME")  ;   
                 
               }
               String tempwarehouse=null;
               if(WAREHOUSE!=null &&  WAREHOUSE.size()>0)
               {
                   for(int i=0; i<WAREHOUSE.size(); i++)
                   {
                        if(tempwarehouse==null)
                        {
                            tempwarehouse=WAREHOUSE.get(i).toString();
                        }else{
                        tempwarehouse=tempwarehouse +","+WAREHOUSE.get(i).toString();
                        }
                   }
               
               }
              
              stat1=conn.prepareStatement("delete from  m4_bill_user_master where EMP_CODE=?");
              stat1.setString(1,EMP_CODE);
              stat1.executeUpdate();
              
               
               if(DEPT_SL_NO!=null && DEPT_SL_NO.size()>0 )
               {
                   for(int j=0; j<DEPT_SL_NO.size(); j++)
                   {
                        String temptypecode=null;
                        if(BILLTYPECODE!=null && BILLTYPECODE.size()>0)
                        {
                             for(int i=0; i<BILLTYPECODE.size(); i++)
                             {
                                 if(BILLTYPECODE.get(i).toString().startsWith(DEPT_SL_NO.get(j).toString()+":"))
                                 {
                                    
                                     if(temptypecode==null)
                                     {
                                      temptypecode=BILLTYPECODE.get(i).toString().substring(BILLTYPECODE.get(i).toString().indexOf(":")+1,BILLTYPECODE.get(i).toString().length());
                                     }else{
                                      temptypecode=temptypecode+","+BILLTYPECODE.get(i).toString().substring(BILLTYPECODE.get(i).toString().indexOf(":")+1,BILLTYPECODE.get(i).toString().length());
                                     }
                                 }
                             }
                        }
                  
                   
                      
                       stat1=conn.prepareStatement("insert into m4_bill_user_master (EMP_CODE,DEPT_SL_NO,FLAG,TDATE,USER_ID,WAREHOUSE,BILL_TYPE,ACCESS_FLAG) values(?,?,?,sysdate,?,?,?,?)");
                       stat1.setString(1, EMP_CODE);
                       stat1.setString(2, DEPT_SL_NO.get(j).toString());
                       stat1.setString(3, ACTIVE_FLAG);
                       stat1.setString(4, usrid);
                       stat1.setString(5, tempwarehouse);
                       stat1.setString(6, temptypecode);
                       stat1.setString(7, ACCESS_FLAG);
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
                    System.out.print("1 file name : M3BilluserAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : save M3BilluserAction.java" + e);

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
                     if (conn1 != null) {
                        conn1.close();
                    }
                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                  
                    System.out.print("File Name : save m3billuseraction.java Exception in finally block");
                    e.printStackTrace();
                }
            } 
        
            
            if(flag==0)
            {
              addActionMessage("Record Not saved(s)");
            }else{
            
            addActionMessage("Record  saved(s)");
            }
        }
        if(EMP_NAME!=null && EMP_NAME.length()>0)
        {
        warehouselist=getwarehouselist();
        deptlist=getdeptlist();
        }
        return "newpage";
     }    
     
     
    
     public String exelist() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        

       
            Connection conn = null;
            Connection conn1 = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn1 = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;
            
            String sqlstr=" ";
            
            if(S_EMP!=null && S_EMP.length()>0)
            {
               
                  sqlstr="where emp_code='"+S_EMP+"'";
                
            }
            
            
            

            try {
                stat1=conn.prepareStatement("select distinct EMP_CODE,decode(FLAG,'Y','Yes','No') FLAG,WAREHOUSE,decode(ACCESS_FLAG,'Y','Yes','No') ACCESS_FLAG from m4_bill_user_master "+sqlstr+" order by EMP_CODE");
                result1=stat1.executeQuery();
                while(result1.next())
                {
                String name="";
                stat1=conn1.prepareStatement("select FULL_NAME from hrempmst where emp_code=?");
                stat1.setString(1, result1.getString(1));
                result=stat1.executeQuery();
                if(result.next())
                {
                 name=result.getString(1);
                }
                if(result!=null)
                {
                result.close();
                }
                 if(stat1!=null)
                {
                stat1.close();
                }
                
                userlist.add(new M3BILLBean(result1.getString("EMP_CODE"),name, result1.getString("FLAG"), result1.getString("WAREHOUSE"), result1.getString("ACCESS_FLAG")));
                
              }
             } catch (Exception e) {

                System.out.print("1 file name : M3billAction.java" + e);

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
                  
                    System.out.print("File Name : M3billUserAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
      
        
        return SUCCESS;
     } 
  
  private List getwarehouselist()
 {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        List mastlist=new ArrayList();
  

            Connection conn = null;
            Connection conn1 = null;

            try {
                conn = new ConnectionSeplWeb().getConnection();
                conn1 = new ConnectionShahiHris().getConnection();
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            ResultSet result1 = null;
            ResultSet result = null;

            try {
                     String locttemp="99";
                    stat1=conn.prepareStatement("select distinct WAREHOUSE  from m4_bill_user_master where emp_code=?");
                    stat1.setString(1, EMP_CODE);
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                        locttemp=result.getString(1);
                    }
                     String list[] = null;
                        String aa = null;
                        if (locttemp != null && locttemp.length() > 0) {
                            if (locttemp.indexOf(",") != -1) {
                                list = locttemp.split(",");
                                if (list != null && list.length > 0) {
                                    for (int i = 0; i < list.length; i++) {
                                        if (aa == null) {
                                            aa = "'" + list[i] + "'";
                                        } else {
                                            aa = aa + ",'" + list[i] + "'";
                                        }
                                    }
                                }
                            } else {
                                aa = "'" + locttemp + "'";
                            }
                        }
                    
                    stat1=conn1.prepareStatement("select MWWHLO,MWWHNM||' - '||MWWHLO MWWHNM  from prodbi.mitwhl SQL1 where MWCONO='111'  and MWWHLO not in ("+aa+")  order by 1");
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                       
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    
                    }
                   stat1=conn1.prepareStatement("select MWWHLO,MWWHNM||' - '||MWWHLO MWWHNM  from prodbi.mitwhl SQL1 where MWCONO='111' and MWWHLO  in ("+aa+")  order by 1");
                   result=stat1.executeQuery();
                    while(result.next())
                    {
                    savewarehouselist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    }
                    
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    //addActionMessage("Record Not Found(s)");
                    }
               
            }catch (Exception e) {

               
                System.out.print("1 file name : M3billAction.java" + e);

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
                    if (conn1 != null) {
                        conn1.close();
                    }
                    result1 = null;
                    stat1 = null;
                    conn = null;
                    conn1 = null;

                } catch (Exception e) {
                   
                    System.out.print("File Name : M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }   
     
    public String newtype() throws Exception {
        if (DEPT_SL_NO != null && DEPT_SL_NO.size() > 0) {
            typelist = gettypemst();
        }
        return "newusbill";
    }
 
 
private List gettypemst() throws Exception {
 Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
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
                
                     
                        
                 if(DEPT_SL_NO!=null && DEPT_SL_NO.size()>0)   {  
                     for(int i=0; i<DEPT_SL_NO.size(); i++)
                     {
                    stat1=conn.prepareStatement("select DEPT_CODE,DEPT_DESC from m4_bill_dept_master where DEPT_CODE=?");
                    stat1.setString(1, DEPT_SL_NO.get(i).toString());
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                       typedeptlist.add(new M3BILLBean(result.getString(1),result.getString(2)));
                    }
                         
                     String locttemp="0";
                    stat1=conn.prepareStatement("select distinct nvl(BILL_TYPE,'0')  from m4_bill_user_master where emp_code=? and DEPT_SL_NO=?");
                    stat1.setString(1, EMP_CODE);
                    stat1.setString(2, DEPT_SL_NO.get(i).toString());
                    
                    result=stat1.executeQuery();
                    if(result.next())
                    {
                        locttemp=result.getString(1);
                        //System.out.println(locttemp);
                    }
                         
                         
                        stat1=conn.prepareStatement("select SL_NO,DEPT_SL_NO,TYPE_CODE,TYPE_DESC,FLAG from M4_BILL_type_MASTER where DEPT_SL_NO in(select SL_NO from m4_bill_dept_master where DEPT_CODE=? and flag='Y') and FLAG='Y' and TYPE_CODE in ("+locttemp+")  order by  TYPE_DESC");
                        stat1.setString(1, DEPT_SL_NO.get(i).toString());
                        result=stat1.executeQuery();
                         while(result.next())
                            {
                               savetypelist.add(new M3BILLBean(result.getString("SL_NO"), DEPT_SL_NO.get(i).toString(), result.getString("TYPE_CODE"), result.getString("TYPE_DESC"), result.getString("FLAG"),""));
                              
                            }
                         
                        stat1=conn.prepareStatement("select SL_NO,DEPT_SL_NO,TYPE_CODE,TYPE_DESC,FLAG from M4_BILL_type_MASTER where DEPT_SL_NO in(select SL_NO from m4_bill_dept_master where DEPT_CODE=? and flag='Y') and FLAG='Y' and TYPE_CODE not in ("+locttemp+")  order by  TYPE_DESC");
                        stat1.setString(1, DEPT_SL_NO.get(i).toString());
                        result=stat1.executeQuery();
                         while(result.next())
                            {
                               listmst.add(new M3BILLBean(result.getString("SL_NO"), DEPT_SL_NO.get(i).toString(), result.getString("TYPE_CODE"), result.getString("TYPE_DESC"), result.getString("FLAG"),""));
                              
                            }
                     }
                 } 
                   

            } catch (Exception e) {

                System.out.print("1 file name : M3billAction.java" + e);

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
                  
                    System.out.print("File Name : M3billAction.java Exception in finally block");
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
 
  private List getdeptlist()
 {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
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
            
                
                    stat1=conn.prepareStatement("select distinct DEPT_CODE,DEPT_DESC from M4_BILL_DEPT_MASTER  where FLAG='Y' and DEPT_CODE not in(select DEPT_SL_NO from  m4_bill_user_master where emp_code=?)  order by DEPT_DESC");
                    stat1.setString(1, EMP_CODE);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    }
                   
                    stat1=conn.prepareStatement("select distinct DEPT_CODE,DEPT_DESC from M4_BILL_DEPT_MASTER  where  DEPT_CODE  in (select DEPT_SL_NO from  m4_bill_user_master where emp_code=?)  order by DEPT_DESC");
                    stat1.setString(1, EMP_CODE);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    savedeptlist.add(new M3BILLBean(result.getString(1), result.getString(2).toUpperCase()));
                    }
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    //addActionMessage("Record Not Found(s)");
                    }
               
            }catch (Exception e) {

               
                System.out.print("1 file name : M3billAction.java" + e);

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
                   
                    System.out.print("File Name : M3billAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
 
   return mastlist;
 }

    public List getWarehouselist() {
        return warehouselist;
    }

    public void setWarehouselist(List warehouselist) {
        this.warehouselist = warehouselist;
    }

    public List<M3BILLBean> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<M3BILLBean> typelist) {
        this.typelist = typelist;
    }

    public List getSavewarehouselist() {
        return savewarehouselist;
    }

    public void setSavewarehouselist(List savewarehouselist) {
        this.savewarehouselist = savewarehouselist;
    }

    public List getDeptlist() {
        return deptlist;
    }

    public void setDeptlist(List deptlist) {
        this.deptlist = deptlist;
    }

    public List getSavedeptlist() {
        return savedeptlist;
    }

    public void setSavedeptlist(List savedeptlist) {
        this.savedeptlist = savedeptlist;
    }

    public List getDEPT_SL_NO() {
        return DEPT_SL_NO;
    }

    public void setDEPT_SL_NO(List DEPT_SL_NO) {
        this.DEPT_SL_NO = DEPT_SL_NO;
    }

    public List getSavemastlist() {
        return savemastlist;
    }

    public void setSavemastlist(List savemastlist) {
        this.savemastlist = savemastlist;
    }

    public String getEMP_CODE() {
        return EMP_CODE;
    }

    public void setEMP_CODE(String EMP_CODE) {
        this.EMP_CODE = EMP_CODE;
    }

    public String getEMP_NAME() {
        return EMP_NAME;
    }

    public void setEMP_NAME(String EMP_NAME) {
        this.EMP_NAME = EMP_NAME;
    }

    public List<M3BILLBean> getTypedeptlist() {
        return typedeptlist;
    }

    public void setTypedeptlist(List<M3BILLBean> typedeptlist) {
        this.typedeptlist = typedeptlist;
    }

    public List<M3BILLBean> getSavetypelist() {
        return savetypelist;
    }

    public void setSavetypelist(List<M3BILLBean> savetypelist) {
        this.savetypelist = savetypelist;
    }

    public List getWAREHOUSE() {
        return WAREHOUSE;
    }

    public void setWAREHOUSE(List WAREHOUSE) {
        this.WAREHOUSE = WAREHOUSE;
    }

    public List getBILLTYPECODE() {
        return BILLTYPECODE;
    }

    public void setBILLTYPECODE(List BILLTYPECODE) {
        this.BILLTYPECODE = BILLTYPECODE;
    }

    public String getACCESS_FLAG() {
        return ACCESS_FLAG;
    }

    public void setACCESS_FLAG(String ACCESS_FLAG) {
        this.ACCESS_FLAG = ACCESS_FLAG;
    }

    public String getACTIVE_FLAG() {
        return ACTIVE_FLAG;
    }

    public void setACTIVE_FLAG(String ACTIVE_FLAG) {
        this.ACTIVE_FLAG = ACTIVE_FLAG;
    }

    public List<M3BILLBean> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<M3BILLBean> userlist) {
        this.userlist = userlist;
    }

    public String getS_EMP() {
        return S_EMP;
    }

    public void setS_EMP(String S_EMP) {
        this.S_EMP = S_EMP;
    }

    public String getS_WHLO() {
        return S_WHLO;
    }

    public void setS_WHLO(String S_WHLO) {
        this.S_WHLO = S_WHLO;
    }

    public String getS_DEPT_DESC() {
        return S_DEPT_DESC;
    }

    public void setS_DEPT_DESC(String S_DEPT_DESC) {
        this.S_DEPT_DESC = S_DEPT_DESC;
    }


  
  


}
