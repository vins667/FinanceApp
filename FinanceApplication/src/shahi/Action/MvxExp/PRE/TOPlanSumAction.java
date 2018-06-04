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
import shahi.Action.MvxExp.PRE.Beans.TOPlanSumBean;


import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import static com.opensymphony.xwork2.Action.INPUT;

public class TOPlanSumAction extends ActionSupport {

   
   private List invList = new ArrayList();
    private String aausrid ;
    private String currentdate;
    private String sac;
    private String sport;
    private String sbuyer;
    private String acholder;

   
   private int TBOX=0;
   private int TCFT=0;
   private int TCBM=0;
   private Double TVOL=0.0;
   private int TQTY=0;
    private String showFlag;
 
    private List acList = new ArrayList();
    private List portList = new ArrayList();
    private List buyerList = new ArrayList();
    private ResourceBundle aResourcBundle;
  

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
           // LOCATION_CODE="200";
             stat1 = conn.prepareStatement("select distinct ac_holder from ei_endors_mast a,LG_TO_PLAN B where A.EXCS_INV_NO=B.EXCS_INV_NO AND nvl(surrender_yn,'N')='N' and TTO_DATE IS NULL AND location=? order by 1" );
             stat1.setString(1,LOCATION_CODE);
             result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 acList.add(new GetListBean(result1.getString("ac_holder"),result1.getString("ac_holder")));      
              }
             
                 stat1 = conn.prepareStatement("select distinct LOADING from ei_endors_mast A,LG_TO_PLAN B where a.excs_inv_no=b.excs_inv_no and nvl(surrender_yn,'N')='N' and TTO_DATE IS NULL AND LOCATION=?  order by 1" );
                stat1.setString(1,LOCATION_CODE);
                 result1 = stat1.executeQuery();
                 while(result1.next())
                {   
                     portList.add(new GetListBean(result1.getString("LOADING"),result1.getString("LOADING")));      
                 }   
              
                 stat1 = conn.prepareStatement("select distinct buyer from ei_endors_mast A,LG_TO_PLAN B where  a.excs_inv_no=b.excs_inv_no and nvl(surrender_yn,'N')='N' and TTO_DATE IS NULL AND LOCATION=?  order by 1" );
                stat1.setString(1,LOCATION_CODE);
               
                  result1 = stat1.executeQuery();
                 while(result1.next())
                 {    
                     buyerList.add(new GetListBean(result1.getString("buyer"),result1.getString("buyer")));      
                 }   
              
             
             
              
            
              if (showFlag!=null && showFlag.length()>0)
             {  
                stat1 = conn.prepareStatement(" select ac_holder,to_char(FWD_DATE,'dd/mm/yyyy') fwd_date,to_char(ex_fy_date,'dd/mm/yyyy') ex_fy_date,to_char(del_date,'dd/mm/yyyy') del_date,loading,buyer,cfs_code,substr(oaconm,23,30) fyname,desti_cntry,a.excs_inv_no,inv_qty iqty,c.pkgs,c.inv_cft,c.inv_cbm  from ei_endors_mast a,lg_to_plan c,  " +
                                              " seplweb.CIADDR_view115 E WHERE OACONO=111 AND OAADTH=4 and oaadk1=fy_code and  a.excs_inv_no=c.excs_inv_no(+) and nvl(surrender_yn,'N')='N' and tto_date is null  and a.location=?  and ac_holder LIKE ? and loading like ? and buyer like ? order by 1,2,3,4,5 " );
                stat1.setString(1,LOCATION_CODE);
                stat1.setString(2,sac);
                 stat1.setString(3,sport);
                stat1.setString(4,sbuyer); 
                result1 = stat1.executeQuery();
               while(result1.next())
               {    
                   
                   TBOX=TBOX+ result1.getInt("pkgs");
                   TQTY=TQTY+ result1.getInt("iqty");
                   TCBM=TCBM+ result1.getInt("INV_CBM");
                   TCFT=TCFT+ result1.getInt("INV_CFT");
                   invList.add(new TOPlanSumBean(result1.getString("fwd_date"),result1.getString("ex_fy_date"),result1.getString("del_date"),result1.getString("loading"),result1.getString("buyer"),result1.getString("desti_cntry"),result1.getString("excs_inv_no"),result1.getString("fyname"),result1.getString("iqty"),result1.getString("pkgs"),result1.getDouble("inv_cbm"),result1.getDouble("inv_cft"),result1.getString("ac_holder")));
                  
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
               return INPUT;
                }
            else {return ERROR;
           }
    }  // end excute

    
  
   
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }
 
 
    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
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

    public int getTCFT() {
        return TCFT;
    }

    public void setTCFT(int TCFT) {
        this.TCFT = TCFT;
    }

    public int getTCBM() {
        return TCBM;
    }

    public void setTCBM(int TCBM) {
        this.TCBM = TCBM;
    }

    
 
     

    public Double getTVOL() {
        return TVOL;
    }

    public void setTVOL(Double TVOL) {
        this.TVOL = TVOL;
    }

    public List getInvList() {
        return invList;
    }

    public void setInvList(List invList) {
        this.invList = invList;
    }

    public ResourceBundle getaResourcBundle() {
        return aResourcBundle;
    }

    public void setaResourcBundle(ResourceBundle aResourcBundle) {
        this.aResourcBundle = aResourcBundle;
    }

    public int getTBOX() {
        return TBOX;
    }

    public void setTBOX(int TBOX) {
        this.TBOX = TBOX;
    }

    public int getTQTY() {
        return TQTY;
    }

    public void setTQTY(int TQTY) {
        this.TQTY = TQTY;
    }
 
 

}

