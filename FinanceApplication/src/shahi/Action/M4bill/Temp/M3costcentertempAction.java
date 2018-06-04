/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.M4bill.Temp;


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
import shahi.Action.M3bill.Beans.M3BILLBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionShahiHris;

/**
 *
 * @author RANJEET
 */
public class M3costcentertempAction extends ActionSupport{
    private List<M3BILLBean> deptmastlist=new ArrayList<M3BILLBean>();
     private List<M3BILLBean> mastlist=new ArrayList<M3BILLBean>();
     private List<M3BILLBean> subtypelist=new ArrayList<M3BILLBean>();
      private List<M3BILLBean> typelist=new ArrayList<M3BILLBean>();
    private String delflg;
    private List chkdel;
    private String S_DEPT_CODE;
    private String S_DEPT_DESC;
    private String SEARCH_TYPE;
    private String SEARCH_CODE;
    private String TXTID;
    
    private String DEPT_CODE;
    private String DEPT_DESC;
    
    private List TYPE_CODE;
    private List TYPEFLAG;
    private String MAST_SL_NO;
   
    private List UP_TYPE_CODE;
    private List UP_TYPE_DESC;
    private List UP_TYPEFLAG;
    private List SL_NO;
    private String delslno;
    private String TYPE_SL_NO;
    private String BILL_TYPE_DESC;
    
    
  
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
                    
                        stat1 = conn.prepareStatement("delete from  bal_cc where DEPT_SL_NO=?");
                        stat1.setString(1, chkdel.get(i).toString());
                        int a = stat1.executeUpdate();

                      
                }
                }
                }
                
                String sqlstr=null;
                
                 if(S_DEPT_CODE!=null && S_DEPT_CODE.length()>0)
                {
                 if(sqlstr==null)
                 {
                  sqlstr=" and b.DEPT_CODE like '"+S_DEPT_CODE.toUpperCase()+"%'";
                 }else{
                  sqlstr=sqlstr +" b.and DEPT_CODE like '"+S_DEPT_CODE.toUpperCase()+"%'";
                 }
                }
                
                if(S_DEPT_DESC!=null && S_DEPT_DESC.length()>0)
                {
                 if(sqlstr==null)
                 {
                  sqlstr=" and b.DEPT_DESC like '"+S_DEPT_DESC.toUpperCase()+"%'";
                 }else{
                  sqlstr=sqlstr +" and b.DEPT_DESC like '"+S_DEPT_DESC.toUpperCase()+"%'";
                 }
                }
                
                if(sqlstr==null)
                {
                sqlstr=" ";
                }
                
                stat1=conn.prepareStatement("select distinct a.DEPT_SL_NO,b.DEPT_CODE,b.DEPT_DESC from  bal_cc a,M4_BILL_DEPT_MASTER b where a.DEPT_SL_NO=b.SL_NO "+sqlstr+" order by b.DEPT_DESC");
                result1=stat1.executeQuery();
                while(result1.next())
                {
                    String deptflag=null;
                   
                    String EXIST=null;
                    stat1=conn.prepareStatement("select DEPT_SL_NO from m4_bill_master where DEPT_SL_NO=?");
                    stat1.setString(1, result1.getString("DEPT_SL_NO"));
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
                    
                deptmastlist.add(new M3BILLBean(result1.getString("DEPT_SL_NO"),result1.getString("DEPT_CODE"),result1.getString("DEPT_DESC"),deptflag,EXIST));
                flag=1;
                }
                

                     

            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M3billAction.java" + ee);

                    System.out.println(ee.toString());
                }
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
                    flag = 0;
                    System.out.print("File Name : M3billAction.java Exception in finally block");
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
   if(DEPT_CODE!=null && DEPT_CODE.length()>0)
  {
         getdetail(DEPT_CODE);
   }
     return "newpage";
  }
  
    public String newmstrec() throws Exception {
  
     return "newpage";
  }  
   public String newmstcc() throws Exception {
   if(DEPT_CODE!=null && DEPT_CODE.length()>0 && TYPE_SL_NO!=null && TYPE_SL_NO.length()>0 )
  {
         getdetailcc(DEPT_CODE);
   }
     return "newpagecc";
  }   
    
    
    
     public String searchpage() throws Exception {
  if(SEARCH_TYPE!=null && SEARCH_TYPE.equals("4"))
  {
      mastlist=getdeptlist(SEARCH_CODE);
  }else if(SEARCH_TYPE!=null && SEARCH_TYPE.equals("5"))
  {
  mastlist=gettypelist(SEARCH_CODE);
  }
  else{
    mastlist=getmasterlist(SEARCH_CODE,SEARCH_TYPE);
  } 
  
  
  return "searchpage";
  }
  
 
     public String save() throws Exception {
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
             return "savepage";
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
                
                if(delslno!=null && delslno.length()>0)
                
                {
                stat1=conn.prepareStatement("delete from  bal_cc where sl_no=?");
                stat1.setString(1,delslno);
                flag=stat1.executeUpdate();
                }
                
               if(UP_TYPE_CODE!=null && UP_TYPE_CODE.size()>0 )
               {
               for(int i=0; i<UP_TYPE_CODE.size(); i++)
                     {
                            stat1=conn.prepareStatement("update  bal_cc set CC_CODE=?,TDATE=sysdate,USER_ID=?,FLAG=? where sl_no=?");
                            stat1.setString(1, UP_TYPE_CODE.get(i).toString());
                            stat1.setString(2, usrid);
                            stat1.setString(3, UP_TYPEFLAG.get(i).toString());
                            stat1.setString(4, SL_NO.get(i).toString());
                            flag=stat1.executeUpdate();
                     }
               
               }
               
                 if(TYPE_CODE!=null && TYPE_CODE.size()>0) 
                 {
                     for(int i=0; i<TYPE_CODE.size(); i++)
                     {
                         if(TYPE_CODE.get(i).toString()!=null && TYPE_CODE.get(i).toString().length()>0)
                         {    String slno=null;
                               stat1=conn.prepareStatement("select  bal_cc_sq.nextval from dual");
                                result1=stat1.executeQuery();
                                if(result1.next())
                                {
                                  slno=result1.getString(1);
                                }
                             
                            stat1=conn.prepareStatement("insert into  bal_cc(SL_NO,DEPT_SL_NO,CC_CODE,TDATE,USER_ID,FLAG,TYPE_SL_NO) values(?,?,?,sysdate,?,?,?)");
                            stat1.setString(1, slno);
                            stat1.setString(2, DEPT_CODE);
                            stat1.setString(3, TYPE_CODE.get(i).toString());
                            stat1.setString(4, usrid);
                            stat1.setString(5, TYPEFLAG.get(i).toString());
                            stat1.setString(6, TYPE_SL_NO);
                            
                            flag=stat1.executeUpdate();
                         }
                     }
                }
             
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M3BillEntryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M3BillEntryAction.java" + e);

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
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return "savepage";

        }
    getdetailcc(DEPT_CODE);
        if (flag == 1) {
           
            addActionMessage("Records  saved(s) !!");
            return "savepage";
        } else {

            addActionMessage("Records Not save(s) !!");
            return "savepage";
        }
    
     
   }    
     
 
   public void getdetail(String slno)
   {
    

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
                //delete by sl no
                 if(delflg!=null && delflg.equals("Yes"))
                {
                if(chkdel!=null && chkdel.size()>0)
                {
                    for(int i=0; i<chkdel.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("delete from  bal_cc where SL_NO=?");
                        stat1.setString(1, chkdel.get(i).toString());
                        int a = stat1.executeUpdate();

                      
                }
                }}
                
                
                stat1=conn.prepareStatement("select distinct a.DEPT_SL_NO,b.DEPT_CODE,b.DEPT_DESC from  bal_cc a,M4_BILL_DEPT_MASTER b where a.DEPT_SL_NO=b.SL_NO and  DEPT_SL_NO=?");
                stat1.setString(1, slno);
               
                result=stat1.executeQuery();
                if(result.next())
                {
                  DEPT_CODE=result.getString("DEPT_SL_NO");
                  DEPT_DESC=result.getString("DEPT_DESC");
                                  
                }
                
                 
                
                
                    stat1=conn.prepareStatement("select distinct TYPE_SL_NO,FLAG from  bal_cc where DEPT_SL_NO=? and TYPE_SL_NO is not null order by TYPE_SL_NO");
                    stat1.setString(1, slno);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                        
                        String desccc=null;
                    stat1=conn.prepareStatement("select TYPE_DESC from M4_BILL_TYPE_MASTER where SL_NO=?");
                    stat1.setString(1, result.getString("TYPE_SL_NO"));
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                        
                        desccc=result1.getString(1);
                    
                    }
                    if(result1!=null)
                    {
                    result1.close();
                    }
                    if(stat1!=null)
                    {
                    stat1.close();
                    }
                    /*
                     String EXIST=null;
                      stat1=conn.prepareStatement("select CC_CODE from m4_bill_detail where CC_CODE=?");
                       stat1.setString(1, result.getString("SL_NO"));
                       result1=stat1.executeQuery();
                      
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
                        */
                      subtypelist.add(new M3BILLBean(result.getString("TYPE_SL_NO"), desccc, result.getString("FLAG")));
                    
                    }
                    
                   
                
               
                }catch (Exception e) {
                System.out.println(e.toString());
            }  finally {

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
                    
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
   
   
   }    
 

 

   public void getdetailcc(String slno)
   {
    

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
                //delete by sl no
                 if(delflg!=null && delflg.equals("Yes"))
                {
                if(chkdel!=null && chkdel.size()>0)
                {
                    for(int i=0; i<chkdel.size(); i++)
                    {
                    
                        stat1 = conn.prepareStatement("delete from  bal_cc where SL_NO=?");
                        stat1.setString(1, chkdel.get(i).toString());
                        int a = stat1.executeUpdate();

                      
                }
                }}
                
                
                stat1=conn.prepareStatement("select distinct a.DEPT_SL_NO,b.DEPT_CODE,b.DEPT_DESC from  bal_cc a,M4_BILL_DEPT_MASTER b where a.DEPT_SL_NO=b.SL_NO and  DEPT_SL_NO=?");
                stat1.setString(1, slno);
               
                result=stat1.executeQuery();
                if(result.next())
                {
                  DEPT_CODE=result.getString("DEPT_SL_NO");
                  DEPT_DESC=result.getString("DEPT_DESC");
                                  
                }
                   stat1=conn.prepareStatement("select TYPE_DESC from M4_BILL_TYPE_MASTER where SL_NO=?");
                    stat1.setString(1, TYPE_SL_NO);
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                        
                        BILL_TYPE_DESC=result1.getString(1);
                    
                    }
                    if(result1!=null)
                    {
                    result1.close();
                    }
                    if(stat1!=null)
                    {
                    stat1.close();
                    }
                
                
                    stat1=conn.prepareStatement("select SL_NO,DEPT_SL_NO,CC_CODE,FLAG from  bal_cc  where DEPT_SL_NO=? and TYPE_SL_NO=?");
                    stat1.setString(1, slno);
                    stat1.setString(2, TYPE_SL_NO);
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                        
                    String desccc=null;
                    stat1=connbi.prepareStatement("select EAAITM,EATX40 from prodbi.fchacc where EACONO=111 and EAAITP=3 and eaat12=0 and EAAITM=?");
                    stat1.setString(1, result.getString("CC_CODE"));
                    result1=stat1.executeQuery();
                    if(result1.next())
                    {
                        
                        desccc=result1.getString(2);
                    
                    }
                    if(result1!=null)
                    {
                    result1.close();
                    }
                    if(stat1!=null)
                    {
                    stat1.close();
                    }
                     String EXIST=null;
                      stat1=conn.prepareStatement("select CC_CODE from m4_bill_detail where CC_CODE=?");
                       stat1.setString(1, result.getString("SL_NO"));
                       result1=stat1.executeQuery();
                      
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
                        
                      subtypelist.add(new M3BILLBean(result.getString("SL_NO"), result.getString("DEPT_SL_NO"), result.getString("CC_CODE"), desccc, result.getString("FLAG"),EXIST));
                    
                    }
                    
                   
                
               
                }catch (Exception e) {
                System.out.println(e.toString());
            }  finally {

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
                    
                    System.out.print("File Name : M3BillEntryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
   
   
   }     
   
private List getdeptlist(String desc)
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
                    desc=" and upper(DEPT_DESC) like '"+desc.toUpperCase()+"%'" ;
                }
                if(desc==null)
                {
                desc=" ";
                }
                    stat1=conn.prepareStatement("select SL_NO,DEPT_CODE,DEPT_DESC from M4_BILL_DEPT_MASTER where FLAG='Y' "+desc+"  order by DEPT_DESC");
                    result=stat1.executeQuery();
                    while(result.next())
                    {
                    mastlist.add(new M3BILLBean(result.getString(1), result.getString(3).toUpperCase()));
                    }
                    
                    if(mastlist==null || mastlist!=null && mastlist.size()==0)
                    {
                    addActionMessage("Record Not Found(s)");
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
 
private List gettypelist(String desc)
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
                    desc=" and upper(TYPE_DESC) like '"+desc.toUpperCase()+"%'" ;
                }
                if(desc==null)
                {
                desc=" ";
                }
                    stat1=conn.prepareStatement("select SL_NO,TYPE_DESC from M4_BILL_TYPE_MASTER where DEPT_SL_NO=? and flag='Y'  and SL_NO not in ( select distinct TYPE_SL_NO from  bal_cc where DEPT_SL_NO=? and TYPE_SL_NO is not null) "+desc+" order by 2");
                    stat1.setString(1, DEPT_CODE);
                    stat1.setString(2, DEPT_CODE);
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

               
                System.out.print("1 file name : typelist .java" + e);

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
                    stat1=conn.prepareStatement("select EAAITM,EATX40 from prodbi.fchacc where EACONO=111 and EAAITP=? and eaat12=0 and (upper(EATX40) like '"+desc+"'  or upper(EAAITM) like '"+desc+"') order by 2");
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

    public String getSEARCH_TYPE() {
        return SEARCH_TYPE;
    }

    public void setSEARCH_TYPE(String SEARCH_TYPE) {
        this.SEARCH_TYPE = SEARCH_TYPE;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
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

    public List getTYPE_CODE() {
        return TYPE_CODE;
    }

    public void setTYPE_CODE(List TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public List<M3BILLBean> getSubtypelist() {
        return subtypelist;
    }

    public void setSubtypelist(List<M3BILLBean> subtypelist) {
        this.subtypelist = subtypelist;
    }

    public String getMAST_SL_NO() {
        return MAST_SL_NO;
    }

    public void setMAST_SL_NO(String MAST_SL_NO) {
        this.MAST_SL_NO = MAST_SL_NO;
    }

    public List getTYPEFLAG() {
        return TYPEFLAG;
    }

    public void setTYPEFLAG(List TYPEFLAG) {
        this.TYPEFLAG = TYPEFLAG;
    }

    public List getUP_TYPE_CODE() {
        return UP_TYPE_CODE;
    }

    public void setUP_TYPE_CODE(List UP_TYPE_CODE) {
        this.UP_TYPE_CODE = UP_TYPE_CODE;
    }

    public List getUP_TYPE_DESC() {
        return UP_TYPE_DESC;
    }

    public void setUP_TYPE_DESC(List UP_TYPE_DESC) {
        this.UP_TYPE_DESC = UP_TYPE_DESC;
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

    public List<M3BILLBean> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<M3BILLBean> typelist) {
        this.typelist = typelist;
    }

    public String getTYPE_SL_NO() {
        return TYPE_SL_NO;
    }

    public void setTYPE_SL_NO(String TYPE_SL_NO) {
        this.TYPE_SL_NO = TYPE_SL_NO;
    }

    public String getBILL_TYPE_DESC() {
        return BILL_TYPE_DESC;
    }

    public void setBILL_TYPE_DESC(String BILL_TYPE_DESC) {
        this.BILL_TYPE_DESC = BILL_TYPE_DESC;
    }
 




}
