/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.PRE;
   
import static com.opensymphony.xwork2.Action.ERROR;
import java.sql.*;
import java.util.*;  
import java.io.*; 
import com.opensymphony.xwork2.ActionContext;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.text.SimpleDateFormat;
import java.util.Date; 
import javax.servlet.ServletContext;
import org.apache.struts2.StrutsStatics;
import org.apache.commons.io.FileUtils;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.MailProvider.MailProvider;

import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.PRE.Beans.InvFollowupBean;


import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import static com.opensymphony.xwork2.Action.INPUT;

public class InvFollowupAction extends ActionSupport {

    private List FileList = new ArrayList();
  
    private String aausrid ;
    private String saveFlag;
    private String currentdate;
    private List errorlist;
  
    private String sac;
    private String stype;
    private String acholder;
    private List UPDCODE;
    
    private List EXCS_INV_NO;
    private List<Date> AWB_DATE;
    private String showFlag ;
   
    private String show1;
    private String showrec;
    private String GetType;
    private String GetDtl;
    private List TTO_DATE;
    private List TO_DATE;
    private List ETD_DATE;
    private List PLAN_NO;
    private List PCH;
    private List MOS;
    private List BUYER;
    private List PORT;
    private List PRE_DOCS;
    private List DESTI_CNTRY;
    private List INV_QTY;
   private List SAVEFLG;
    
    private List acList = new ArrayList();
    private List TypeList = new ArrayList();
    private List PreList = new ArrayList();
    private List invList = new ArrayList();
    ResourceBundle aResourcBundle = null;
 
   private String getValue(String key) {
        return aResourcBundle.getString(key);
    }


    @Override
   public String execute()
   {
       try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }
        
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       int flag = 0;
        if(usrid==null)
        {
           session.put("sessUserId",aausrid); 
           usrid=aausrid;
        }

        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
      
        try { 
            Connection conn = null,Odbcon=null;
            try {
                aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
               conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return INPUT;
            } // end catch

        try {
                Odbcon = new connection().getConnection();
                Odbcon.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("IBM Database Connection Not Valid !!");
                return INPUT;
            } // end catch


            PreparedStatement stat1=null,stat=null; 
            PreparedStatement stat2=null;
             ResultSet result2=null;
            ResultSet RTaccess = null,result1 =null,result=null;
         try
           { 
                 String ERRMSG=null;
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
          
             stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
             stat1.setString(1,usrid);
             result1=stat1.executeQuery();
             while (result1.next())
             {LOCATION_CODE=result1.getString("location_code");
             } 
              
             stat1 = conn.prepareStatement("select distinct ac_holder from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and doc_send is null AND location=? order by 1" );
             stat1.setString(1,LOCATION_CODE);
             result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 acList.add(new GetListBean(result1.getString("ac_holder"),result1.getString("ac_holder")));      
              }
            
              stat1 = conn.prepareStatement("select  type_desc||'-'||type_code type_DESC,type_code from  EI_GRUP_TYPE_DTLS WHERE GRUP_TYPE_CODE='IF' and close_date is null order by 1" );
        
              result1 = stat1.executeQuery();
              while(result1.next())
              {  
                  TypeList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
              }  
              
              if (showFlag!=null && showFlag.length()>0 )
              {
               if((stype.equals("P") || stype.equals("D") || stype.equals("S") || stype.equals("DAPR"))) //&& LOCATION_CODE.equals("200")) 
               {
                 showrec="YES";
                 
                
                   
                if (stype.equals("P"))
                    {    if(LOCATION_CODE.equals("100"))
                         {        stat1 = conn.prepareStatement(" select a.excs_inv_no,to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,to_char(fwd_custom,'dd/mm/yyyy') fwd_custom,buyer,cost_centre,mode_of_ship,loading,plan_no,desti_cntry,inv_qty,a.year,a.company,a.inv_no,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs,null fcr_date from ei_endors_mast a where  " +
                                                                  "  a.location=? and nvl(a.ac_holder,'N') like ?  and   a.tto_date>='01-apr-2014'  and doc_send is null order by a.tto_date " );
                            
                          }else
                            {stat1 = conn.prepareStatement(" select a.excs_inv_no,to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,to_char(fwd_custom,'dd/mm/yyyy') fwd_custom,buyer,cost_centre,mode_of_ship,loading,plan_no,desti_cntry,inv_qty,a.year,a.company,a.inv_no,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs,to_char(b.fcr_date,'dd/mm/yyyy') fcr_date from ei_endors_mast a ,ei_truckout_track b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no " +
                                                                  " and b.tr_type='E' and  a.location=? and nvl(a.ac_holder,'N') like ?  and   a.tto_date>='01-apr-2014'  and pre_docs_sent is not null and doc_send is null  order by a.tto_date " );
                            }  
                        stat1.setString(1,LOCATION_CODE); 
                        stat1.setString(2,sac);
                         
                     } 
                  if (stype.equals("D"))
                    {    
                        stat1 = conn.prepareStatement(" select a.excs_inv_no,to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,buyer,cost_centre,mode_of_ship,loading,plan_no,desti_cntry,inv_qty,a.year,a.company,a.inv_no,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs,null fcr_date from ei_endors_mast a  where pre_docs_sent is null and a.tto_date>='01-apr-2014' and  a.location=?  and nvl(a.ac_holder,'N') like ? " );
                                                      
                        stat1.setString(1,LOCATION_CODE);
                        stat1.setString(2,sac);
                    } 
                  if (stype.equals("DAPR"))
                    {    
                        stat1 = conn.prepareStatement(" select a.excs_inv_no,to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,buyer,cost_centre,mode_of_ship,loading,plan_no,desti_cntry,inv_qty,a.year,a.company,a.inv_no,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs,null fcr_date from ei_endors_mast a  where a.tto_date>='01-apr-2014' and  a.location=?  and nvl(a.ac_holder,'N') like ? AND   "+
                                                      "  NOT exists (select x.year from ei_truckout_track x WHERE x.year=a.year and x.company=a.company and x.inv_no=a.inv_no and x.tr_type='DAPR') ORDER BY A.TTO_DATE" );
                                                      
                        stat1.setString(1,LOCATION_CODE);
                        stat1.setString(2,sac);
                    } 
                    if (stype.equals("S"))
                    {   
                        stat1 = conn.prepareStatement(" select a.excs_inv_no,to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,buyer,cost_centre,mode_of_ship,loading,plan_no,desti_cntry,inv_qty,a.year,a.company,a.inv_no,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs,null fcr_date from ei_endors_mast a where a.tto_date>='01-apr-2014' and a.location=?  and nvl(a.ac_holder,'N') like ? " +
                                                      " and NOT exists (select x.year from ei_truckout_track x where x.year=a.year and x.company=a.company and x.inv_no=a.inv_no  and x.tr_type='S') order by a.tto_date " );
                        stat1.setString(1,LOCATION_CODE);
                        stat1.setString(2,sac);
                    }  
                  
                        result1 = stat1.executeQuery();
                        while(result1.next())
                        {     
                               if (stype.equals("P") || stype.equals("S") || stype.equals("D"))
                                 {
                                      stat = conn.prepareStatement("select exports.validate_inv_movex115(?,?,?) aa from dual ");
                                     stat.setString(1,result1.getString("year"));
                                     stat.setString(2,result1.getString("company"));
                                     stat.setString(3,result1.getString("inv_no"));
                                      result = stat.executeQuery();
                                     if (result.next())
                                      {   if (result.getString("aa")!=null)
                                          {  
                                             ERRMSG=result.getString("aa");
                                                }
                                      }  
                                 } 
                                 // Checking for POST 200
                                    if (LOCATION_CODE.equals("200") && stype.equals("P") )
                                     {   
                                          stat=conn.prepareStatement("select trunc(sysdate)-min(fcr_date) fcr from ei_Truckout_track where tr_type='E' and year=?  and company=? and inv_no=? ");
                                          stat.setString(1,result1.getString("year"));
                                          stat.setString(2,result1.getString("company"));
                                          stat.setString(3,result1.getString("inv_no"));
                                          result=stat.executeQuery();
                                          if (result.next())
                                          {
                                             if (result.getString("fcr")==null)
                                             {
                                               ERRMSG="FCR Date .. not updated !!!";
                                             }
                                            if (result.getInt("fcr")>6)
                                            {  stat2=conn.prepareStatement("select nvl(count(*),0) vfind from ei_docs_delay_dtls where year=?  and company=? and inv_no=? and grup_code='EDR'");
                                               stat2.setString(1,result1.getString("year"));
                                               stat2.setString(2,result1.getString("company"));
                                               stat2.setString(3,result1.getString("inv_no"));
                                               result2=stat2.executeQuery();
                                               if (result2.next())
                                               {   if (result2.getInt("vfind")==0)
                                                 { 
                                                     ERRMSG="Enter Delay Reason First More then 6 Days from FCR Date !!!";
                                                      
                                                 }
                                               }
                                            }
                                          }
                                     }
      
                               ///   Close check 200 post
                                   invList.add(new InvFollowupBean(result1.getString("EXCS_INV_NO"),result1.getString("TTO_DATE"),result1.getString("TO_DATE"),result1.getString("ETD_DATE"),result1.getString("cost_centre"),result1.getString("mode_of_ship"),result1.getString("BUYER"),result1.getString("loading"),result1.getString("PLAN_NO"),result1.getString("INV_QTY"),ERRMSG,result1.getString("fcr_date"),result1.getString("DESTI_CNTRY")));
                                    ERRMSG=null;
                        }  
   
                  }else{ showrec="NOT"; } //  Show Flag closed...
                 }
              ////// Get Detail Start
                  
                 if (GetDtl!=null && GetDtl.length()>0)
                     {
                          TTO_DATE=new ArrayList();
                          TO_DATE=new ArrayList();
                          ETD_DATE=new ArrayList();
                          PCH=new ArrayList();
                          MOS=new ArrayList();
                          BUYER=new ArrayList();
                          PORT=new ArrayList();
                          INV_QTY=new ArrayList();
                          PLAN_NO=new ArrayList();
                          PRE_DOCS=new ArrayList();
                          DESTI_CNTRY=new ArrayList();
                          AWB_DATE=null;
                        showrec="NOT";
                       
                      for(int i=0;i<EXCS_INV_NO.size();i++){
                          
                       stat1 = conn.prepareStatement("select to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,buyer,cost_centre,mode_of_ship,loading,plan_no,inv_qty,desti_cntry,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs from ei_endors_mast a where excs_inv_no=? "  );
                       stat1.setString(1,EXCS_INV_NO.get(i).toString());
                       result1 = stat1.executeQuery();
                        if(result1.next())
                        {  
                                TTO_DATE.add(result1.getString("TTO_DATE"));
                                TO_DATE.add(result1.getString("TO_DATE"));
                                ETD_DATE.add(result1.getString("ETD_DATE"));
                                PCH.add(result1.getString("cost_centre"));
                                MOS.add(result1.getString("mode_of_ship"));
                                PORT.add(result1.getString("loading"));
                                PLAN_NO.add(result1.getString("PLAN_NO"));
                                BUYER.add(result1.getString("BUYER"));
                                INV_QTY.add(result1.getString("INV_QTY"));
                                PRE_DOCS.add(result1.getString("PRE_DOCS"));
                                DESTI_CNTRY.add(result1.getString("DESTI_CNTRY"));
                                 
                         }
                         else{

                                TTO_DATE.add("");
                                TO_DATE.add("");
                                ETD_DATE.add("");
                                PCH.add("");
                                MOS.add("");
                                PORT.add("");
                                PLAN_NO.add("");
                                BUYER.add("");
                                INV_QTY.add("");
                                PRE_DOCS.add("");
                                DESTI_CNTRY.add("");
                         }
                     } 
                     }//// Get Detail Closed...
              
                              
                   String vexcs=""; String tto_date=""; String to_date=""; String etd_date="";String fwd_custom=null; 
                   String awbdate=""; String year=""; String company=""; String inv_no = ""; String srem=""; String loct_code="";
                   String pre_docs="";   String post_docs="";
                   System.out.println("ch=1");
                  if (saveFlag!=null && saveFlag.equals("YES"))
                    {  
                     if(SAVEFLG!=null && SAVEFLG.size()>0){
                       for(int i=0;i<SAVEFLG.size();i++)
                       {   
                           if(SAVEFLG.get(i).toString()!=null && SAVEFLG.get(i).toString().equals("Yes"))
                           {
                               vexcs= EXCS_INV_NO.get(i).toString();
                              
                         if(vexcs.length()>0 && vexcs!=null)
                         {     
                               stat1 = conn.prepareStatement("select year,company,inv_no,tto_date,t_o_date,etd_date,fwd_custom,awbdate,location,pre_docs_sent,post_docs_sent from ei_endors_mast where excs_inv_no=? " );
                               stat1.setString(1, vexcs);
                               result1 = stat1.executeQuery();
                                if(result1.next())
                                {  
                                    year=result1.getString("year");
                                    company=result1.getString("company");
                                    inv_no=result1.getString("inv_no");
                                    tto_date=result1.getString("tto_date");
                                    to_date=result1.getString("t_o_date");
                                    etd_date=result1.getString("etd_date");
                                    fwd_custom=result1.getString("fwd_custom");
                                    awbdate=result1.getString("awbdate");
                                    loct_code=result1.getString("location");
                                    pre_docs=result1.getString("pre_docs_sent");
                                    post_docs=result1.getString("post_docs_sent");
                                    
                                 }  
                                 if (stype.equals("E") && post_docs!=null)
                                 {
                                    addActionMessage("Type E already Exist... !!!");
                                   return  ERROR;
                                 }
                                if (stype.equals("P") || stype.equals("S") || stype.equals("D"))
                                 {
                                   if (to_date.length()==0 )
                                   {addActionMessage("Check TO  Date not updated !!!");

                                     return  ERROR;
                                   }    
                                   if (tto_date.length()==0  )
                                   {addActionMessage("Check TTO Date not updated !!!");

                                     return  ERROR;
                                   }
                                    if (etd_date.length()==0) 
                                   {addActionMessage("Check ETD Date not updated !!!");

                                     return  ERROR;
                                   }
                                   if (fwd_custom.length()==0) 
                                   {addActionMessage("Check Fwd to Custom not updated !!!");

                                        return  ERROR;
                                   } 
                                  }
                                
                                    if (awbdate!=null  && (stype.equals("P") || stype.equals("R")))
                                    {   addActionMessage("Post Shipment Entry Done. !!!");

                                         return  ERROR;
                                     } 
                        /// For 200 Checking            
                          if (loct_code.equals("200") && stype.equals("P") )
                            {    
                                if (pre_docs.length()==0) 
                                {   addActionMessage("Pre Docs .. not updated !!!");
                                     return  ERROR;
                                } 
                                 stat1=conn.prepareStatement("select trunc(sysdate)-min(fcr_date) fcr from ei_Truckout_track where tr_type='E' and all_no=? ");
                                 stat1.setString(1,vexcs);
                                 result1=stat1.executeQuery();
                                 if (result1.next())
                                 {
                                   if (result1.getString("fcr")==null)
                                   {
                                    addActionMessage("FCR Date .. not updated !!!");
                                    return  ERROR;
                                   }
                                   if (result1.getInt("fcr")>6)
                                   {  stat=conn.prepareStatement("select nvl(count(*),0) vfind from ei_docs_delay_dtls where all_no=? and grup_code='EDR'");
                                      stat.setString(1,vexcs);
                                      result=stat.executeQuery();
                                      if (result.next())
                                      {   if (result.getInt("vfind")==0)
                                        { 
                                            addActionMessage("Enter Delay Reason First....More then 6 Days from FCR Date !!!"+vexcs);
                                            return  ERROR;
                                        }
                                      }
                                   }
                                 }
                            }       
                         /// 200 Checking closed           
                         if (stype.equals("P") || stype.equals("S") || stype.equals("D"))
                                 {
                                     
                                     stat1 = conn.prepareStatement("select exports.validate_inv_movex115(?,?,?) aa from dual ");
                                    stat1.setString(1, year);
                                    stat1.setString(2,company);
                                    stat1.setString(3,inv_no);
                                     result1 = stat1.executeQuery();
                                     if (result1.next())
                                      {   if (result1.getString("aa")!=null)
                                          {
                                              addActionMessage(" Error :- "+result1.getString("aa")+" Invoice : "+vexcs);
                                              return  ERROR;
                                          }
                                      } 
                                 }
                                    int maxsrno=0;
                                    stat1 = conn.prepareStatement("select nvl(max(sr_no),0)+1 vno from ei_truckout_track  where year=? and company=? and inv_no=? ");
                                    stat1.setString(1, year);
                                    stat1.setString(2,company);
                                    stat1.setString(3,inv_no);
                                      result1 = stat1.executeQuery();
                                     while (result1.next())
                                      {   
                                        maxsrno=result1.getInt("vno");
                                       }
                                    String mydate=null;
                                     if(AWB_DATE.get(i)!=null && AWB_DATE.get(i).toString().length()>8)
                                     {
                                         SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                                         mydate=format.format(AWB_DATE.get(i));
                                     }
                                     
                                     stat1=conn.prepareStatement("insert into ei_truckout_track (year,company,inv_no,location,sr_no,tr_type,remark,all_no,fcr_date,tr_date,tdate,seh_user) values (?,?,?,?,?,?,?,?,?,sysdate,sysdate,?)");
                                            stat1.setString(1,year);
                                            stat1.setString(2,company);
                                            stat1.setString(3,inv_no);
                                            stat1.setString(4, loct_code);
                                            stat1.setInt(5,maxsrno);
                                            stat1.setString(6,stype);
                                            stat1.setString(7,srem);
                                            stat1.setString(8,vexcs);
                                            stat1.setString(9,mydate);
                                            stat1.setString(10,usrid);
                                            stat1.executeUpdate();
                                            flag=1;
                                       } 
                              } }
                        }   
                    }  /// Save Flag Closed...  
            
           }catch (Exception e) { 
            System.out.println(e.toString());
            addActionMessage(e+" - Error In  Statement !!");
            try{   conn.rollback();
            }catch(Exception ee) {System.out.println(ee.toString());}

            }finally {
                try 
                {
                    if (stat1 != null) { stat1.close(); }
                    if (stat != null) { stat.close(); }

                    if (RTaccess != null) { RTaccess.close(); }
                    if (result != null) { result.close(); }
                    if (result1 != null) { result1.close(); }

                    if (conn != null) { conn.close(); }
                    if  (Odbcon!=null) {Odbcon.close();}
                    RTaccess = null; 
                    conn = null; Odbcon=null;
                } catch (Exception e) {
                    System.out.print("File Name : InvFollowupAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  InvFollowupAction.java ");
                }
            } /// end Finally Block
         
         
        } 
        
         
        catch (Exception e) {
            addActionError(e.getMessage());
        }
             
           if (flag == 1) {
                 addActionMessage("Records Save(s) !!");
                 stype=null;
               return INPUT;
                }
            else {return ERROR;
           }
    }  // end excute

   
  
    
    public List getFileList() {
        return FileList;
    }

    public void setFileList(List FileList) {
        this.FileList = FileList;
    }

   
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }
 
    

    public String getCurrentdate() {
        return currentdate;
    } 

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public ResourceBundle getaResourcBundle() {
        return aResourcBundle;
    }

    public void setaResourcBundle(ResourceBundle aResourcBundle) {
        this.aResourcBundle = aResourcBundle;
    }

   
    public List getErrorlist() {
        return errorlist;
    }

    public void setErrorlist(List errorlist) {
        this.errorlist = errorlist;
    }

    public String getAcholder() {
        return acholder;
    }

    public void setAcholder(String acholder) {
        this.acholder = acholder;
    }

   
     

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

   

    public String getSac() {
        return sac;
    }

    public void setSac(String sac) {
        this.sac = sac;
    }


    public List getAcList() {
        return acList;
    }

    public void setAcList(List acList) {
        this.acList = acList;
    }

  
    public List getInvList() {
        return invList;
    }

    public void setInvList(List invList) {
        this.invList = invList;
    }

    public List getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(List EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

   

   

    public List getTypeList() {
        return TypeList;
    }

    public void setTypeList(List TypeList) {
        this.TypeList = TypeList;
    }

    public List getPreList() {
        return PreList;
    }

    public void setPreList(List PreList) {
        this.PreList = PreList;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public List<Date> getAWB_DATE() {
        return AWB_DATE;
    }

    public void setAWB_DATE(List<Date> AWB_DATE) {
        this.AWB_DATE = AWB_DATE;
    }
 
   

    public List getUPDCODE() {
        return UPDCODE;
    }

    public void setUPDCODE(List UPDCODE) {
        this.UPDCODE = UPDCODE;
    }

    public String getShow1() {
        return show1;
    }

    public void setShow1(String show1) {
        this.show1 = show1;
    }

    public String getGetType() {
        return GetType;
    }

    public void setGetType(String GetType) {
        this.GetType = GetType;
    }

    public List getTTO_DATE() {
        return TTO_DATE;
    }

    public void setTTO_DATE(List TTO_DATE) {
        this.TTO_DATE = TTO_DATE;
    }

    public List getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(List TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public List getETD_DATE() {
        return ETD_DATE;
    }

    public void setETD_DATE(List ETD_DATE) {
        this.ETD_DATE = ETD_DATE;
    }

    public List getPLAN_NO() {
        return PLAN_NO;
    }

    public void setPLAN_NO(List PLAN_NO) {
        this.PLAN_NO = PLAN_NO;
    }

    public List getPCH() {
        return PCH;
    }

    public void setPCH(List PCH) {
        this.PCH = PCH;
    }

    public List getMOS() {
        return MOS;
    }

    public void setMOS(List MOS) {
        this.MOS = MOS;
    }

    public List getBUYER() {
        return BUYER;
    }

    public void setBUYER(List BUYER) {
        this.BUYER = BUYER;
    }

    public List getPORT() {
        return PORT;
    }

    public void setPORT(List PORT) {
        this.PORT = PORT;
    }

    public List getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(List INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public String getShowrec() {
        return showrec;
    }

    public void setShowrec(String showrec) {
        this.showrec = showrec;
    }

    public String getGetDtl() {
        return GetDtl;
    }

    public void setGetDtl(String GetDtl) {
        this.GetDtl = GetDtl;
    }

    public List getSAVEFLG() {
        return SAVEFLG;
    }

    public void setSAVEFLG(List SAVEFLG) {
        this.SAVEFLG = SAVEFLG;
    }

    public List getPRE_DOCS() {
        return PRE_DOCS;
    }

    public void setPRE_DOCS(List PRE_DOCS) {
        this.PRE_DOCS = PRE_DOCS;
    }

    public List getDESTI_CNTRY() {
        return DESTI_CNTRY;
    }

    public void setDESTI_CNTRY(List DESTI_CNTRY) {
        this.DESTI_CNTRY = DESTI_CNTRY;
    }

    

}

 