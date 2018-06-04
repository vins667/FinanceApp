/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.POST;
   
import static com.opensymphony.xwork2.Action.ERROR;
import java.sql.*;
import java.util.*;
import java.io.*; 
import com.opensymphony.xwork2.ActionContext;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.text.SimpleDateFormat;
import java.util.Date; 

import com.opensymphony.xwork2.ActionSupport;
 

import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.PRE.Beans.InvETDBean;


import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import static com.opensymphony.xwork2.Action.INPUT;

public class PortalUpdateAction extends ActionSupport {

    private List FileList = new ArrayList();
  
    private String aausrid ;
    private String saveFlag;
    private String currentdate;
    private List errorlist;
    private String SEARCH_BH;
   private String SEARCH_BUYER;
   
 
    private List UPDCODE;

    private String updFlag;
     
    private String showFlag;
    private String sortFlag;
    private String[] saverec;
    
    private List acList = new ArrayList();
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
            Connection conn = null;
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
                 
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("IBM Database Connection Not Valid !!");
                return INPUT;
            } // end catch


            PreparedStatement stat1=null,stat=null;
            ResultSet result1 =null,result=null;
         try
           {  
              
               if(updFlag!=null && updFlag.equals("YES")){
                   if(UPDCODE!=null && UPDCODE.size()>0){
                       for(int i=0;i<UPDCODE.size();i++){
                            int srno=1;
                             stat=conn.prepareStatement(" select year,company,inv_no,location,excs_inv_no from ei_endors_mast where excs_inv_no=?") ;
                            stat.setString(1,UPDCODE.get(i).toString().trim());
                            result=stat.executeQuery();
                            if (result.next())
                            {
                                    stat1=conn.prepareStatement(" select  nvl(max(sr_no),0)+1 v_no from ei_truckout_track where all_no=?");
                                    stat1.setString(1,UPDCODE.get(i).toString().trim());
                                    result1=stat1.executeQuery();
                                    if (result1.next())
                                    {
                                       srno=result1.getInt("v_no");
                                    }
                                    if (result1 != null) { result1.close(); }
                                    if (stat1 != null) { stat1.close(); }

                                    stat1=conn.prepareStatement("insert into ei_truckout_track(year,company,inv_no,all_no,location,sr_no,seh_user,tr_type,tr_date,tdate,remark) "+
                                                                " values (?,?,?,?,?,?,?,'PU',sysdate,sysdate,'Portal Updated') ");
                                    stat1.setString(1,result.getString("year"));
                                    stat1.setString(2,result.getString("company"));
                                    stat1.setString(3,result.getString("inv_no"));
                                    stat1.setString(4,result.getString("excs_inv_no"));
                                    stat1.setString(5,result.getString("location"));
                                    stat1.setInt(6,srno);
                                    stat1.setString(7,usrid);
                                    stat1.executeUpdate();
                                    flag=1;
                                    
                            }
                                    if (result1 != null) { result1.close(); }
                                    if (stat1 != null) { stat1.close(); }
                                    if (result != null) { result.close(); }
                                    if (stat != null) { stat.close(); }
                       }
                   }
               }
               
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
               
             stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
             stat1.setString(1,usrid);
             result1=stat1.executeQuery();
             while (result1.next())
             {LOCATION_CODE=result1.getString("location_code");
             } 
              
             stat1 = conn.prepareStatement("select distinct buyer_grp from ei_buyer_req_mast where nvl(portal_app,'N')='Y' and buyer_grp is not null order by 1" );
             result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 acList.add(new GetListBean(result1.getString("buyer_grp"),result1.getString("buyer_grp")));      
              }
                if (result1 != null) { result1.close(); }
                if (stat1 != null) { stat1.close(); } 
                   
                
            if (sortFlag!=null && sortFlag.length()>0)
                 if (SEARCH_BH==null){SEARCH_BH="%";} 
                 { 
               invList=null;  
                 stat1 = conn.prepareStatement("select buyer from ei_buyer_req_mast where portal_app='Y' and nvl(buyer_grp,'N') like ?  order by 1" );
                 stat1.setString(1,SEARCH_BH);
                 result1 = stat1.executeQuery();
                 while(result1.next())
                 {    
                     buyerList.add(new GetListBean(result1.getString("buyer"),result1.getString("buyer")));      
                 }   
               }
               if (result1 != null) { result1.close(); }
               if (stat1 != null) { stat1.close(); }
           
            
              if (showFlag!=null && showFlag.length()>0)
             {  
              
                 String sqlstr=" ";
                 if(SEARCH_BH!=null && SEARCH_BH.length()>0 )
                {
                  sqlstr=sqlstr+" and nvl(buyer_grp,'N') like '%"+SEARCH_BH.toUpperCase()+"%'";
                } 
                if(SEARCH_BUYER!=null && SEARCH_BUYER.length()>0 )
                {
                  sqlstr=sqlstr+" and a.BUYER like '%"+SEARCH_BUYER.toUpperCase()+"%'";
                } 
                 
                if (SEARCH_BH.length()==0){SEARCH_BH="%";}  
             
                stat1 = conn.prepareStatement("   select excs_inv_no,to_char(inv_date,'dd/mm/yyyy') INV_DATE,to_char(t_o_date,'dd/mm/yyyy') to_date,A.buyer,to_char(doc_send,'dd/mm/yyyy') ftp_date,to_char(etd_date,'dd/mm/yyyy') etd_date,cost_centre,to_char(awbdate,'dd/mm/yyyy') awbdate,mode_of_ship,CRNCY_CODE,DESTI_CNTRY from ei_endors_mast a,ei_buyer_req_mast b WHERE trim(A.buyer)=trim(b.buyer) and fin_date is null  " +sqlstr+
                                              "   and t_o_date is not null  and a.location=?   order by a.excs_inv_no" );
                stat1.setString(1,LOCATION_CODE);
                
                
                
                result1 = stat1.executeQuery();
               while(result1.next())
               {    
                        invList.add(new InvETDBean(result1.getString("excs_inv_no"),result1.getString("ftp_date"),result1.getString("buyer"),result1.getString("inv_date"),result1.getString("awbdate"),result1.getString("to_date"),result1.getString("cost_centre"),result1.getString("mode_of_ship"),result1.getString("mode_of_ship"),result1.getString("cost_centre"),result1.getString("CRNCY_CODE"),result1.getString("DESTI_CNTRY"),result1.getString("mode_of_ship"),result1.getString("ETD_DATE")));
                  
               } // end while

                if (result1 != null) { result1.close(); }
                if (stat1 != null) { stat1.close(); }
               
              }
            
                 
                   
           }catch (Exception e) {
            System.out.println(e.toString());
            addActionMessage(e+" - Error In  Statement !!");
            try{   conn.rollback();
            }catch(Exception ee) {System.out.println(ee.toString());}

            }finally {
                try
                {
                   

                    
                    if (result != null) { result.close(); }
                    if (result1 != null) { result1.close(); }
                    if (stat1 != null) { stat1.close(); }
                    if (stat != null) { stat.close(); }
                    if (conn != null) { conn.close(); }
           
                    
                    conn = null; 
                } catch (Exception e) {
                    System.out.print("File Name : PortalUpdateAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  PortalUpdateAction.java ");
                }
            } /// end Finally Block
         
         
        } 
        
         
        catch (Exception e) {
            addActionError(e.getMessage());
        }
            
           if (flag == 1) {
               UPDCODE=null;
               SEARCH_BH=null;
               SEARCH_BUYER=null;
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

   

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
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


    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getSEARCH_BH() {
        return SEARCH_BH;
    }

    public void setSEARCH_BH(String SEARCH_BH) {
        this.SEARCH_BH = SEARCH_BH;
    }

    public String getSEARCH_BUYER() {
        return SEARCH_BUYER;
    }

    public void setSEARCH_BUYER(String SEARCH_BUYER) {
        this.SEARCH_BUYER = SEARCH_BUYER;
    }

    public List getAcList() {
        return acList;
    }

    public void setAcList(List acList) {
        this.acList = acList;
    }

   

    public String getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(String sortFlag) {
        this.sortFlag = sortFlag;
    }

    public List getUPDCODE() {
        return UPDCODE;
    }

    public void setUPDCODE(List UPDCODE) {
        this.UPDCODE = UPDCODE;
    }
   
   

}

