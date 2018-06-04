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
import shahi.Action.MvxExp.PRE.Beans.TOPlanBean;
import shahi.Action.MvxExp.PRE.Beans.InvCbmBean;

import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import static com.opensymphony.xwork2.Action.INPUT;

public class TOPlanAction extends ActionSupport {

    private List FileList = new ArrayList();
    private List invCBMList = new ArrayList();
    private String aausrid ;
    private String saveFlag;
    private String currentdate;
    private List errorlist;
    private String updFlag;
    private String remove_fwd;
    private String sac;
    private String sport;
    private String sbuyer;
    private String acholder;

    private String[] UPDCODE;
    private String VINV;
    private String FY_CODE;
    private String CFS_CODE;
    private String DEL_DATE;
    private String EX_FY_DATE;
    private String FWD_DATE;
    private String authuser;
    private String GRWT;
    private String PKGS;
     
   private List CBM;
   private List CFT;
   private List BOX_VOL;
   private List INV_PKGS;
   private List BOX_LN;
   private List BOX_WD;
   private List BOX_HT;
   private List CT_UOM;
   
   private Double TBOX=0.0;
   private Double TCFT=0.0;
   private Double TCBM=0.0;
   private Double TVOL=0.0;
   
    private String showFlag;

    private String[] saverec;
    
    private List acList = new ArrayList();
    private List portList = new ArrayList();
    private List buyerList = new ArrayList();
    private List invList   = new ArrayList();
    private List cfsList   = new ArrayList();
    private List fyList   = new ArrayList();
    private List minv     = new ArrayList();
    private List INVCH    = new ArrayList();
    private List INVCHOLD    = new ArrayList();
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
            ResultSet RTaccess = null,result1 =null,result=null;
         try
           { 
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
           
             stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
             stat1.setString(1,usrid);
             result1=stat1.executeQuery();
             while (result1.next())
             {LOCATION_CODE=result1.getString("location_code");
             } 
             stat1= conn.prepareStatement("select * from pa_auth_mast where prog_name='TOPLAN' and user_id=?") ;
             stat1.setString(1,usrid);
   
              result1=stat1.executeQuery();
             while (result1.next())
             {  
                 authuser="YES";
             } 
             stat1 = conn.prepareStatement("select distinct ac_holder from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and TTO_DATE IS NULL AND location=? order by 1" );
             stat1.setString(1,LOCATION_CODE);
             result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 acList.add(new GetListBean(result1.getString("ac_holder"),result1.getString("ac_holder")));      
              }
              if (sac!=null )  ///&& showFlag==null
                 {
                 stat1 = conn.prepareStatement("select distinct LOADING from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and TTO_DATE IS NULL and ac_holder=? order by 1" );
                 stat1.setString(1,sac);
                  result1 = stat1.executeQuery();
                 while(result1.next())
                {   
                     portList.add(new GetListBean(result1.getString("LOADING"),result1.getString("LOADING")));      
                 }   
               if (sport.length()==0){sport="%";} 
                 stat1 = conn.prepareStatement("select distinct buyer from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and TTO_DATE IS NULL and ac_holder=? and loading like ? order by 1" );
                 stat1.setString(1,sac);
                 stat1.setString(2,sport);
                  result1 = stat1.executeQuery();
                 while(result1.next())
                 {    
                     buyerList.add(new GetListBean(result1.getString("buyer"),result1.getString("buyer")));      
                 }   
              
              } 
             
             
            
              if (showFlag!=null && showFlag.length()>0)
             {  
              
                 String sqlstr=" ";
                if(VINV!=null && VINV.length()>0 )
                {
                  sqlstr=sqlstr+" and a.excs_inv_no like '%"+VINV.toUpperCase()+"%'";
                } 
                if (sbuyer.length()==0){sbuyer="%";}  
                
              
                stat1 = conn.prepareStatement(" select a.excs_inv_no,to_char(inv_date,'dd/mm/yyyy') inv_date,loading,buyer,IDSUNM ,cfs_code,substr(oaconm,23,30) fyname,to_char(del_date,'dd/mm/yyyy') del_date,to_char(ex_fy_date,'dd/mm/yyyy') ex_fy_date,c.inv_cbm,to_char(FWD_DATE,'dd/mm/yyyy') fwd_date,inv_qty iqty,c.pkgs,c.inv_cft  from ei_endors_mast a,lg_to_plan c,SEPLWEB.CIDMAS_VIEW115 D,  " +
                                              " CIADDR E WHERE OACONO(+)=111 AND OAADTH(+)=4 and oaadk1(+)=fy_code and  inv_date>='01-jan-2014' and a.agent=d.idsuno(+) and d.idcono(+)=111 and a.excs_inv_no=c.excs_inv_no(+) and nvl(surrender_yn,'N')='N' and tto_date is null  "+sqlstr +" and a.location=?  and ac_holder LIKE ? and loading like ? and buyer like ? order by 1,2,3 " );
                stat1.setString(1,LOCATION_CODE);
                stat1.setString(2,sac);
                
                stat1.setString(3,sport);
                stat1.setString(4,sbuyer); 
                result1 = stat1.executeQuery();
               while(result1.next())
               {    
                        invList.add(new TOPlanBean(result1.getString("excs_inv_no"),result1.getString("inv_date"),result1.getString("loading"),result1.getString("buyer"),result1.getString("idsunm"),result1.getString("pkgs"),result1.getString("inv_cft"),result1.getString("cfs_code"),result1.getString("fyname"),result1.getString("del_date"),result1.getString("ex_fy_date"),result1.getString("inv_cbm"),result1.getString("iqty"),result1.getString("FWD_DATE")));
                  
               } // end while

             }
           
                
                  if  (saverec!=null && updFlag!=null && updFlag.length()>0 )
                 {
                    for(int i=0; i<saverec.length; i++)
                   {
                      if(saverec[i].length()!=0)
                       {          stat=conn.prepareStatement(" update lg_to_plan set fwd_date=sysdate,fwd_user=? where excs_inv_no=? ");
                                  stat.setString(1,usrid);
                                  stat.setString(2,saverec[i]);
                                  stat.executeUpdate();
                                  flag=1;
                       }
                   }
                 } 
                if (saveFlag!=null && saveFlag.length()>0)
                {
                 
                    for(int r=0; r<UPDCODE.length; r++)
                   {
                      if(UPDCODE[r].length()!=0)
                       {  
                          if (remove_fwd!=null && remove_fwd.length()>0)
                          {
                              stat=conn.prepareStatement("update lg_to_plan set fwd_date=null where excs_inv_no=?");
                              stat.setString(1,UPDCODE[r]);
                              stat.executeUpdate();
                              flag=1;   
                          }
                           
                    stat=conn.prepareStatement(" select * from  lg_to_plan  where excs_inv_no=? ");
                    stat.setString(1,UPDCODE[r]);
                    result = stat.executeQuery();
                    if (result.next())
                    {
                                  stat=conn.prepareStatement("update lg_to_plan set cfs_code=?,FY_CODE=?,del_date=to_date(?,'yyyy-mm-dd'),ex_fy_date=to_date(?,'yyyy-mm-dd'),grwt=?,user_id=?,tdate=sysdate where excs_inv_no=? ");
                                  stat.setString(1,CFS_CODE.trim());
                                  stat.setString(2,FY_CODE.trim());
                                  stat.setString(3,DEL_DATE.substring(0,10));
                                  stat.setString(4,EX_FY_DATE.substring(0,10));
                                  stat.setString(5,GRWT);
                                  stat.setString(6,usrid);
                                  stat.setString(7,UPDCODE[r]);
                                  stat.executeUpdate();
                                  flag=1;
                     
                    }else
                    {
                     stat=conn.prepareStatement("insert into lg_to_plan (excs_inv_no,cfs_code,fy_code,del_date,ex_fy_date,GRWT,user_id,tdate) values (?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,sysdate)");
                         
                                  stat.setString(1,UPDCODE[r]);
                                  stat.setString(2,CFS_CODE.trim());
                                  stat.setString(3,FY_CODE.trim());
                                  stat.setString(4,DEL_DATE.substring(0,10));
                                  stat.setString(5,EX_FY_DATE.substring(0,10));
                                  stat.setString(6,GRWT);
                                  stat.setString(7,usrid);
                                  stat.executeUpdate();
                                  flag=1;
                    
                    
                    }
                     if(BOX_LN!=null && BOX_LN.size()>0 )
                      {       for(int i=0; i<BOX_LN.size(); i++)
                              {
                              if(BOX_LN.get(i).toString()!=null && BOX_LN.get(i).toString().length()>0)
                                { 
                                      
                                    stat1=conn.prepareStatement("select * from  lg_inv_cbm  where excs_inv_no=? ");
                                    stat1.setString(1,UPDCODE[r].trim());
                                    result1 = stat1.executeQuery();
                               if (result1.next())
                                 {
                                       stat=conn.prepareStatement("update lg_inv_cbm set box_ln=?,box_wd=?,box_ht=?,inv_pkgs=?,ct_uom=?,cbm=?,cft=?,box_vol=?,user_id=?,tdate=sysdate where excs_inv_no=?");
                                        stat.setString(1,BOX_LN.get(i).toString());
                                        stat.setString(2,BOX_WD.get(i).toString());
                                        stat.setString(3,BOX_HT.get(i).toString());
                                        stat.setString(4,INV_PKGS.get(i).toString());
                                        stat.setString(5,CT_UOM.get(i).toString());
                                        stat.setString(6,CBM.get(i).toString());
                                        stat.setString(7,CFT.get(i).toString());
                                        stat.setString(8,BOX_VOL.get(i).toString());
                                         stat.setString(9,usrid);
                                         stat.setString(10,UPDCODE[r]);
                                        stat.executeUpdate();
                                        flag=1;
                                     
                                     
                                   }else{
                                        stat=conn.prepareStatement("insert into lg_inv_cbm (excs_inv_no,box_ln,box_wd,box_ht,inv_pkgs,ct_uom,cbm,cft,box_vol,user_id,tdate) values (?,?,?,?,?,?,?,?,?,?,sysdate)");
                                        stat.setString(1,UPDCODE[r]);
                                        stat.setString(2,BOX_LN.get(i).toString());
                                        stat.setString(3,BOX_WD.get(i).toString());
                                        stat.setString(4,BOX_HT.get(i).toString());
                                        stat.setString(5,INV_PKGS.get(i).toString());
                                        stat.setString(6,CT_UOM.get(i).toString());
                                        stat.setString(7,CBM.get(i).toString());
                                        stat.setString(8,CFT.get(i).toString());
                                        stat.setString(9,BOX_VOL.get(i).toString());
                                         stat.setString(10,usrid);
                                        stat.executeUpdate();
                                         
                                        flag=1;
                                   }   }
                                 }
                              }
                      }
                   } 
                }
                   
                   
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
                    System.out.print("File Name : TOPlanAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  TOPlanAction.java ");
                }
            } /// end Finally Block
         
         
        } 
        
         
        catch (Exception e) {
            addActionError(e.getMessage());
        }
            
           if (flag == 1) {
                 addActionMessage("Records Save(s) !!");
                 GRWT=null;
               return INPUT;
                }
            else {return ERROR;
           }
    }  // end excute

   
       public String CallMstUpdate()
    {
      try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();

 
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }

       try {
            Connection conn = null;
            PreparedStatement stat1=null;
            ResultSet result1 =null;
            EisUtil pisutil = new EisUtil();

               if (result1 != null) { result1.close(); }
         try{
              try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
                } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
               } //
               
          
             stat1 = conn.prepareStatement("select trim(oaadK1) oaadk1,oaconm FROM CIADDR WHERE OACONO=111 AND OAADTH=4 and oaadk1 like '2%' order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next()) 
             {  
                 fyList.add(new GetListBean(result1.getString("oaadK1"),result1.getString("oaconm")));      
              }
                   
             stat1 = conn.prepareStatement("select type_desc,type_code  from ei_Grup_type_dtls where grup_type_code='CFS' order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next()) 
             {  
                 cfsList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
              }
              if (minv!=null && minv.size()>0)
               {
                for(int i=0; i<minv.size(); i++)
                   {
                    INVCH.add(new GetListBean(minv.get(i).toString(),minv.get(i).toString()));
                      
                   }
               }
          

               if (UPDCODE!=null && UPDCODE.length>0 && minv.size()==0)
               {
                 for(int j=0; j<UPDCODE.length; j++)
                   {
                  if(UPDCODE[j].length()!=0)
                 {  
                             
               stat1 = conn.prepareStatement("select fy_code,cfs_code,TO_CHAR(del_DATE,'yyyy-mm-dd') del_date,TO_CHAR(ex_fy_DATE,'yyyy-mm-dd')ex_fy_date,to_char(fwd_DATE,'dd/mm/yyyy') fwd_date,PKGS,INV_CBM,GRWT from lg_to_plan where excs_inv_no=? " );
               stat1.setString(1,UPDCODE[j]);
               result1 = stat1.executeQuery();
               while(result1.next())
               { 
                    FY_CODE = result1.getString("FY_CODE");
                    CFS_CODE = result1.getString("CFS_CODE");
                    DEL_DATE = result1.getString("DEL_DATE");
                    EX_FY_DATE = result1.getString("EX_FY_DATE"); 
                    FWD_DATE = result1.getString("FWD_DATE");
                    GRWT = result1.getString("GRWT");
                    PKGS = result1.getString("PKGS");
                   
                } // end while 
               
                stat1 = conn.prepareStatement("select BOX_LN,BOX_WD,BOX_HT,INV_PKGS,CT_UOM,ROUND(BOX_VOL,3) BOX_VOL,ROUND(CBM,3) CBM,ROUND(CFT,3) CFT from LG_INV_CBM where excs_inv_no=? " );
                stat1.setString(1,UPDCODE[j]);
                result1 = stat1.executeQuery();
               while(result1.next())
               { 
                   TBOX=TBOX+ result1.getDouble("INV_PKGS");
                   TCFT=TCFT+ result1.getDouble("CFT");
                   TCBM=TCBM+ result1.getDouble("CBM");
                   TVOL=TVOL+ result1.getDouble("BOX_VOL");
                  invCBMList.add(new InvCbmBean(result1.getString("BOX_LN"),result1.getString("BOX_WD"),result1.getString("BOX_HT"),result1.getString("CT_UOM"),result1.getDouble("CBM"),result1.getDouble("CFT"),result1.getDouble("BOX_VOL"),result1.getDouble("INV_PKGS")));      
                  
               }
              
             }
             }     
            } 
               
         } 
         
         catch (Exception e) {
              e.printStackTrace();
              addActionError(e.getMessage());
             return ERROR;
            } finally {
                try
                {
                    if (stat1 != null) { stat1.close(); }
                    if (result1 != null) { result1.close(); }
                    if (conn != null) { conn.close(); }
                    conn = null; pisutil.closeConnection();
                } catch (Exception e) {
                    System.out.print("File Name : TOPlanAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : TOPlanAction.java Exception in finally block");
                    return ERROR;
                }
             } /// end Fina

        } catch (Exception e1) {
            e1.printStackTrace();
            addActionError(e1.getMessage());
            return ERROR;
        }

       return "NEW";
    }
    
       public String callsaveBox()
    {
      try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();

 
        }
        catch (Exception e)
        { System.out.println(e.toString());
        } Map session = ActionContext.getContext().getSession();
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
            Connection conn = null;
            PreparedStatement stat=null;
            PreparedStatement stat1=null;
            ResultSet result1 =null;
            ResultSet result = null;
            EisUtil pisutil = new EisUtil();
             
               if (result1 != null) { result1.close(); }
         try{
              try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
                } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
               } //
             
             
            
             stat1 = conn.prepareStatement("select trim(oaadK1) oaadk1,oaconm FROM seplweb.CIADDR_VIEW115 WHERE OACONO=111 AND OAADTH=4 and oaadk1 like '2%' order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next()) 
             {  
                 fyList.add(new GetListBean(result1.getString("oaadK1"),result1.getString("oaconm")));      
              }
                   
             stat1 = conn.prepareStatement("select type_desc,type_code  from ei_Grup_type_dtls where grup_type_code='CFS' order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next()) 
             {  
                 cfsList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
              }
             

                if (saveFlag!=null && saveFlag.length()>0)
                {
                 
                    for(int r=0; r<UPDCODE.length; r++)
                   {
                 if(UPDCODE[r].length()!=0)
                 {   
                    stat=conn.prepareStatement(" select * from  lg_to_plan  where excs_inv_no=? ");
                    stat.setString(1,UPDCODE[r]);
                    result = stat.executeQuery();
                    if (result.next())
                    {
                                  stat=conn.prepareStatement("update lg_to_plan set cfs_code=?,FY_CODE=?,del_date=to_date(?,'yyyy-mm-dd'),ex_fy_date=to_date(?,'yyyy-mm-dd'),grwt=?,user_id=?,tdate=sysdate where excs_inv_no=? ");
                                  stat.setString(1,CFS_CODE.trim());
                                  stat.setString(2,FY_CODE.trim());
                                    if (DEL_DATE!=null && DEL_DATE.length()>8)
                                    {  stat.setString(3,DEL_DATE.substring(0,10));}
                                    else
                                    {stat.setString(3,DEL_DATE);
                                    }   
                                     if (EX_FY_DATE!=null && DEL_DATE.length()>8)
                                    {   stat.setString(4,EX_FY_DATE.substring(0,10));}
                                    else
                                    {stat.setString(4,EX_FY_DATE);
                                    } 
                                   stat.setString(5,GRWT);
                                  stat.setString(6,usrid);
                                  stat.setString(7,UPDCODE[r]);
                                  stat.executeUpdate();
                                  flag=1;
                    
                    }else
                    {
                     stat=conn.prepareStatement("insert into lg_to_plan (excs_inv_no,cfs_code,fy_code,del_date,ex_fy_date,GRWT,user_id,tdate) values (?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,sysdate)");
                         
                                  stat.setString(1,UPDCODE[r]);
                                  stat.setString(2,CFS_CODE.trim());
                                  stat.setString(3,FY_CODE.trim());
                                    if (DEL_DATE!=null && DEL_DATE.length()>8)
                                    {  stat.setString(4,DEL_DATE.substring(0,10));}
                                    else
                                    {stat.setString(4,DEL_DATE);
                                    }   
                                     if (EX_FY_DATE!=null && DEL_DATE.length()>8)
                                    {   stat.setString(5,EX_FY_DATE.substring(0,10));}
                                    else
                                    {stat.setString(5,EX_FY_DATE);
                                    } 
                                  stat.setString(6,GRWT);
                                  stat.setString(7,usrid);
                                  stat.executeUpdate();
                                  flag=1;
                    
                    
                    }
                    
                              stat=conn.prepareStatement("delete from  lg_inv_cbm  where excs_inv_no=? ");
                              stat.setString(1,UPDCODE[r]);
                              stat.executeUpdate();
                    
                               if(BOX_LN!=null && BOX_LN.size()>0 )
                              { for(int i=0; i<BOX_LN.size(); i++)
                              {
                              if(BOX_LN.get(i).toString()!=null && BOX_LN.get(i).toString().length()>0)
                                { 
                                        stat=conn.prepareStatement("insert into lg_inv_cbm (excs_inv_no,box_ln,box_wd,box_ht,inv_pkgs,ct_uom,cbm,cft,box_vol,user_id,tdate) values (?,?,?,?,?,?,?,?,?,?,sysdate)");
                                        stat.setString(1,UPDCODE[r]);
                                        stat.setString(2,BOX_LN.get(i).toString());
                                        stat.setString(3,BOX_WD.get(i).toString());
                                        stat.setString(4,BOX_HT.get(i).toString());
                                        stat.setString(5,INV_PKGS.get(i).toString());
                                        stat.setString(6,CT_UOM.get(i).toString());
                                        stat.setString(7,CBM.get(i).toString());
                                        stat.setString(8,CFT.get(i).toString());
                                        stat.setString(9,BOX_VOL.get(i).toString());
                                         stat.setString(10,usrid);
                                        stat.executeUpdate();
                                        flag=1;
                                        
                                        
                                       
                                           
                                           
                                          
                                        
                                   }   
                                 }
                              }
                      }
                        
                   }
                    INVCH=new ArrayList();
                                  if (minv!=null && minv.size()>0)
                                          {
                                            for(int s=0; s<minv.size(); s++)
                                            {
                                                //INVCHOLD.add(new GetListBean(minv.get(s).toString(),minv.get(s).toString()));
                                                boolean m=true;
                                                for(int x=0;x<UPDCODE.length;x++){ 
                                                    
                                                    if(minv.get(s).toString().equals(UPDCODE[x]))
                                                    {
                                                         m=false;
                                                    }
                                                }
                                                if(m){
                                                    INVCH.add(new GetListBean(minv.get(s).toString(),minv.get(s).toString()));
                                                }
                                            }
                                          }    
                }
                 
            } 
       
         catch (Exception e) {
              e.printStackTrace();
              addActionError(e.getMessage());
             return ERROR;
            } finally {
                try
                {
                    if (stat1 != null) { stat1.close(); }
                    if (result1 != null) { result1.close(); }
                    if (conn != null) { conn.close(); }
                    conn = null; pisutil.closeConnection();
                } catch (Exception e) {
                    System.out.print("File Name : TOPlanAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : TOPlanAction.java Exception in finally block");
                    return ERROR;
                }
             } /// end Fina

        } catch (Exception e1) {
            e1.printStackTrace();
            addActionError(e1.getMessage());
            return ERROR;
        }
             BOX_LN=null;
             BOX_WD=null;
             BOX_HT=null;
             INV_PKGS=null;
             CBM=null;
             CFT=null;
             BOX_VOL=null;
             UPDCODE=null;
             GRWT=null;
       return "NEW";
       
    }
    
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

    public String[] getSaverec() {
        return saverec;
    }

    public void setSaverec(String[] saverec) {
        this.saverec = saverec;
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

    public String[] getUPDCODE() {
        return UPDCODE;
    }

    public void setUPDCODE(String[] UPDCODE) {
        this.UPDCODE = UPDCODE;
    }

       

    public String getFY_CODE() {
        return FY_CODE;
    }

    public void setFY_CODE(String FY_CODE) {
        this.FY_CODE = FY_CODE;
    } 

    public String getCFS_CODE() {
        return CFS_CODE;
    }

    public void setCFS_CODE(String CFS_CODE) {
        this.CFS_CODE = CFS_CODE;
    }

    public String getDEL_DATE() {
        return DEL_DATE;
    }

    public void setDEL_DATE(String DEL_DATE) {
        this.DEL_DATE = DEL_DATE;
    }

    public String getEX_FY_DATE() {
        return EX_FY_DATE;
    }

    public void setEX_FY_DATE(String EX_FY_DATE) {
        this.EX_FY_DATE = EX_FY_DATE;
    }

    public String getFWD_DATE() {
        return FWD_DATE;
    }

    public void setFWD_DATE(String FWD_DATE) {
        this.FWD_DATE = FWD_DATE;
    }

    
    public String getSac() {
        return sac;
    }

    public void setSac(String sac) {
        this.sac = sac;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSbuyer() {
        return sbuyer;
    }

    public void setSbuyer(String sbuyer) {
        this.sbuyer = sbuyer;
    }

    public List getAcList() {
        return acList;
    }

    public void setAcList(List acList) {
        this.acList = acList;
    }

    public List getPortList() {
        return portList;
    }

    public void setPortList(List portList) {
        this.portList = portList;
    }

    public List getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List buyerList) {
        this.buyerList = buyerList;
    }

    public List getInvList() {
        return invList;
    }

    public void setInvList(List invList) {
        this.invList = invList;
    }

    

    public String getGRWT() {
        return GRWT;
    }

    public void setGRWT(String GRWT) {
        this.GRWT = GRWT;
    }

    public String getPKGS() {
        return PKGS;
    }

    public void setPKGS(String PKGS) {
        this.PKGS = PKGS;
    }

    public List getCfsList() {
        return cfsList;
    }

    public void setCfsList(List cfsList) {
        this.cfsList = cfsList;
    }

    public List getFyList() {
        return fyList;
    }

    public void setFyList(List fyList) {
        this.fyList = fyList;
    }

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getVINV() {
        return VINV;
    }

    public void setVINV(String VINV) {
        this.VINV = VINV;
    }

    public List getCBM() {
        return CBM;
    }

    public void setCBM(List CBM) {
        this.CBM = CBM;
    }

    public List getCFT() {
        return CFT;
    }

    public void setCFT(List CFT) {
        this.CFT = CFT;
    }

    public List getBOX_VOL() {
        return BOX_VOL;
    }

    public void setBOX_VOL(List BOX_VOL) {
        this.BOX_VOL = BOX_VOL;
    }

    public List getINV_PKGS() {
        return INV_PKGS;
    }

    public void setINV_PKGS(List INV_PKGS) {
        this.INV_PKGS = INV_PKGS;
    }

    public List getBOX_LN() {
        return BOX_LN;
    }

    public void setBOX_LN(List BOX_LN) {
        this.BOX_LN = BOX_LN;
    }

    public List getBOX_WD() {
        return BOX_WD;
    }

    public void setBOX_WD(List BOX_WD) {
        this.BOX_WD = BOX_WD;
    }

    public List getBOX_HT() {
        return BOX_HT;
    }

    public void setBOX_HT(List BOX_HT) {
        this.BOX_HT = BOX_HT;
    }

    public List getCT_UOM() {
        return CT_UOM;
    }

    public void setCT_UOM(List CT_UOM) {
        this.CT_UOM = CT_UOM;
    }

    public List getInvCBMList() {
        return invCBMList;
    }

    public void setInvCBMList(List invCBMList) {
        this.invCBMList = invCBMList;
    }

    public Double getTBOX() {
        return TBOX;
    }

    public void setTBOX(Double TBOX) {
        this.TBOX = TBOX;
    }

    public Double getTCFT() {
        return TCFT;
    }

    public void setTCFT(Double TCFT) {
        this.TCFT = TCFT;
    }

    public Double getTCBM() {
        return TCBM;
    }

    public void setTCBM(Double TCBM) {
        this.TCBM = TCBM;
    }

    public Double getTVOL() {
        return TVOL;
    }

    public void setTVOL(Double TVOL) {
        this.TVOL = TVOL;
    }

    public List getMinv() {
        return minv;
    }

    public void setMinv(List minv) {
        this.minv = minv;
    }

    public List getINVCH() {
        return INVCH;
    }

    public void setINVCH(List INVCH) {
        this.INVCH = INVCH;
    }

    public List getINVCHOLD() {
        return INVCHOLD;
    }

    public void setINVCHOLD(List INVCHOLD) {
        this.INVCHOLD = INVCHOLD;
    }

    

    public String getRemove_fwd() {
        return remove_fwd;
    }

    public void setRemove_fwd(String remove_fwd) {
        this.remove_fwd = remove_fwd;
    }

    public String getAuthuser() {
        return authuser;
    }

    public void setAuthuser(String authuser) {
        this.authuser = authuser;
    }
  
    

}

