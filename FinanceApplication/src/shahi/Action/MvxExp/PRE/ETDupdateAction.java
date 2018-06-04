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
import shahi.Action.MvxExp.PRE.Beans.InvETDBean;


import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import static com.opensymphony.xwork2.Action.INPUT;

public class ETDupdateAction extends ActionSupport {

    private List FileList = new ArrayList();
  
    private String aausrid ;
    private String saveFlag;
    private String currentdate;
    private List errorlist;
    private String vsort="TTO_DATE";
    private String sac;
    private String sport;
    private String sbuyer;
    private String acholder;
    private String stype;
    private String VINV;
   
    
    private List EXCS_INV_NO;
    private List<Date> NEW_ETD;
    private String updFlag;
     
    private String showFlag;
    private String sortFlag;
    private String[] saverec;
    
    private List acList = new ArrayList();
    private List portList = new ArrayList();
    private List buyerList = new ArrayList();
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
            ResultSet RTaccess = null,result1 =null,result=null;
         try
           { 
               if(updFlag!=null && updFlag.equals("YES")){
                   if(EXCS_INV_NO!=null && EXCS_INV_NO.size()>0){
                       for(int i=0;i<EXCS_INV_NO.size();i++){
                           if(NEW_ETD.get(i)!=null){
                              // System.out.println(EXCS_INV_NO.get(i).toString());
                               SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                               String mydate=format.format(NEW_ETD.get(i));
                              // System.out.println(mydate);
                               stat1=conn.prepareStatement(" update ei_endors_mast set etd_date=? where excs_inv_no=?");
                                stat1.setString(1,mydate);
                                stat1.setString(2,EXCS_INV_NO.get(i).toString());
                                 stat1.executeUpdate();
                                flag=1;
                           
                           }
                       }
                   }
               }
               else{
                   EXCS_INV_NO=null;
                   NEW_ETD=null;
               }
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
                if (sac==null){sac="%";} 
                 stat1 = conn.prepareStatement("select distinct LOADING from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and doc_send is null and location=? and ac_holder like ? order by 1" );
            
                 stat1.setString(1,LOCATION_CODE);
                 stat1.setString(2,sac);
                  result1 = stat1.executeQuery();
                 while(result1.next())
                {   
                     portList.add(new GetListBean(result1.getString("LOADING"),result1.getString("LOADING")));      
                 }  
                 if (sport==null){sport="%";} 
                 stat1 = conn.prepareStatement("select distinct buyer from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and doc_send is null and ac_holder like ? and loading like ? and location=? order by 1" );
                 stat1.setString(1,sac);
                 stat1.setString(2,sport);
                 stat1.setString(3,LOCATION_CODE);
                  result1 = stat1.executeQuery();
                 while(result1.next())
                 {    
                     buyerList.add(new GetListBean(result1.getString("buyer"),result1.getString("buyer")));      
                 }   
            if (sortFlag!=null && sortFlag.length()>0)
                 if (sac==null){sac="%";} 
                 { if (sport==null){sport="%";} 
                 stat1 = conn.prepareStatement("select distinct LOADING from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and doc_send is null and location=? and ac_holder like ? order by 1" );
                 stat1.setString(1,LOCATION_CODE);
                 stat1.setString(2,sac);
                  result1 = stat1.executeQuery();
                 while(result1.next())
                {   
                     portList.add(new GetListBean(result1.getString("LOADING"),result1.getString("LOADING")));      
                 }
                      
                 stat1 = conn.prepareStatement("select distinct buyer from ei_endors_mast where inv_date>='01-jan-2014' and nvl(surrender_yn,'N')='N' and doc_send is null and ac_holder like ? and loading like ? and location=? order by 1" );
                 stat1.setString(1,sac);
                 stat1.setString(2,sport);
                 stat1.setString(3,LOCATION_CODE);
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
                
             
                stat1 = conn.prepareStatement(" select a.excs_inv_no,loading,buyer,to_char(inv_date,'dd/mm/yyyy') inv_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(tto_date,'dd/mm/yyyy') tto_date,cost_centre,mode_of_ship,plan_no,ac_holder,crncy_code,desti_cntry,inv_qty,to_char(etd_date,'dd/mm/yyyy') etd_date  from ei_endors_mast a  " +
                                              "   WHERE   inv_date>='01-jan-2014'  and nvl(surrender_yn,'N')='N' and   doc_send is null "+sqlstr +" and a.location=?  and ac_holder LIKE ? and loading like ? and buyer like ? order by "+vsort+",a.excs_inv_no" );
                stat1.setString(1,LOCATION_CODE);
                stat1.setString(2,sac);
                
                stat1.setString(3,sport);
                stat1.setString(4,sbuyer); 
                result1 = stat1.executeQuery();
               while(result1.next())
               {    
                        invList.add(new InvETDBean(result1.getString("excs_inv_no"),result1.getString("loading"),result1.getString("buyer"),result1.getString("INV_DATE"),result1.getString("tto_date"),result1.getString("to_date"),result1.getString("cost_centre"),result1.getString("mode_of_ship"),result1.getString("plan_no"),result1.getString("ac_holder"),result1.getString("crncy_code"),result1.getString("desti_cntry"),result1.getString("inv_qty"),result1.getString("ETD_DATE")));
                  
               } // end while

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
                    System.out.print("File Name : ETDupdateAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  ETDupdateAction.java ");
                }
            } /// end Finally Block
         
         
        } 
        
         
        catch (Exception e) {
            addActionError(e.getMessage());
        }
            
           if (flag == 1) {
                 addActionMessage("Records Save(s) !!");
                 
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

    public String getVINV() {
        return VINV;
    }

    public void setVINV(String VINV) {
        this.VINV = VINV;
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

    public List<Date> getNEW_ETD() {
        return NEW_ETD;
    }

    public void setNEW_ETD(List<Date> NEW_ETD) {
        this.NEW_ETD = NEW_ETD;
    }

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getVsort() {
        return vsort;
    }

    public void setVsort(String vsort) {
        this.vsort = vsort;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }
   
 

}

