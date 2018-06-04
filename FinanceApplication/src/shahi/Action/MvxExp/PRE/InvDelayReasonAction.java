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
import shahi.Action.MvxExp.PRE.Beans.InvDelayBean;


import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import static com.opensymphony.xwork2.Action.INPUT;

public class InvDelayReasonAction extends ActionSupport {

    private List FileList = new ArrayList();
  
    private String aausrid ;
    private String saveFlag;
    private String currentdate;
    private List errorlist;
  
    private String sac;
    private String stype;
    private String acholder;
    
    
    private List EXCS_INV_NO;
    private List SREASON;
    private List YEAR;
    private List COMPANY;
    private List INV_NO;
    private String updFlag;
     
   
  
   
    private String showFlag;

    private String[] saverec;
    
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
            ResultSet RTaccess = null,result1 =null,result=null;
         try
           { 
               if(updFlag!=null && updFlag.equals("YES")){
                   if(EXCS_INV_NO!=null && EXCS_INV_NO.size()>0){
                       for(int i=0;i<EXCS_INV_NO.size();i++){
                         String vtype= SREASON.get(i).toString();
                         
                        if(vtype.length()>0 && vtype!=null){
                            String grupcode="";      
                            stat1 = conn.prepareStatement("select grup_type_code from ei_grup_type_dtls where type_code=? ");
                            stat1.setString(1,vtype);
                            result1 = stat1.executeQuery();
                            while (result1.next())
                            {    
                                grupcode=result1.getString("grup_type_code");
                             }
                               
                               
                                stat1=conn.prepareStatement("insert into EI_DOCS_DELAY_DTLS (year,company,inv_no,reason_code,grup_code,all_no,seh_user,tdate) values (?,?,?,?,?,?,?,sysdate)");
                                stat1.setString(1,YEAR.get(i).toString());
                                stat1.setString(2,COMPANY.get(i).toString());
                                stat1.setString(3,INV_NO.get(i).toString());
                                stat1.setString(4, vtype);
                                stat1.setString(5,grupcode);
                                stat1.setString(6,EXCS_INV_NO.get(i).toString());
                                stat1.setString(7,usrid);
                                stat1.executeUpdate();
                                flag=1;
                               
                           
                           }
                       }
                   }
               }
               else{
                   EXCS_INV_NO=null;
                   SREASON=null;
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
              stat1 = conn.prepareStatement("select  type_desc||'-'||type_code type_DESC,type_code from  EI_GRUP_TYPE_DTLS WHERE GRUP_TYPE_CODE='IF' and type_code in ('P','D') order by 1" );
              result1 = stat1.executeQuery();
              while(result1.next())
              {  
                  TypeList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
              }  
             
              if (showFlag!=null && showFlag.length()>0)
             {  
                    if (stype.equals("P"))
                    {  stat = conn.prepareStatement("select  type_desc||'-'||type_code type_DESC,type_code,grup_type_code from  EI_GRUP_TYPE_DTLS WHERE  grup_type_code='EDR' order by 3,1" );
                        result = stat.executeQuery();
                        while(result.next())
                        {  
                            PreList.add(new GetListBean(result.getString("type_code"),result.getString("type_desc")));      
                        }  
                        stat1 = conn.prepareStatement(" select a.year,a.company,a.inv_no,a.excs_inv_no,to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,BUYER,ac_holder from ei_endors_mast a ,ei_truckout_track b,ei_truckout_track C  where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no " +
                                                      "	and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and c.tr_type='P' and  a.location=? and nvl(a.ac_holder,'N') like ?  and   a.tto_date>='01-apr-2014' " +
                                                      " and b.tr_type='E' and trunc(c.tr_date)-b.fcr_date>4  and (a.year,a.company,a.inv_no) not in (select x.year,x.company,x.inv_no from ei_docs_delay_dtls x where x.year is not null and  grup_code='EDR') order by a.tto_date " );
                        stat1.setString(1,LOCATION_CODE);
                        stat1.setString(2,sac);
                    }
                  if (stype.equals("D"))
                    {  stat = conn.prepareStatement("select  type_desc||'-'||type_code type_DESC,type_code,grup_type_code from  EI_GRUP_TYPE_DTLS WHERE  grup_type_code='ADR' order by 3,1" );
                        result = stat.executeQuery();
                        while(result.next())
                        {   
                            PreList.add(new GetListBean(result.getString("type_code"),result.getString("type_desc")));      
                        }  
                        stat1 = conn.prepareStatement(" select a.year,a.company,a.inv_no,a.excs_inv_no,to_char(tto_date,'dd/mm/yyyy') tto_date,to_char(t_o_date,'dd/mm/yyyy') to_date,to_char(etd_date,'dd/mm/yyyy') etd_date,buyer,ac_holder from ei_endors_mast a ,ei_truckout_track C where  " +
                                                      " a.tto_date>='01-apr-2014' and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and c.tr_type='D' and trunc(C.tr_date)-tto_date>3 and a.location=?  and nvl(a.ac_holder,'N') like ? " +
                                                      " and (a.year,a.company,a.inv_no) not in (select x.year,x.company,x.inv_no from ei_docs_delay_dtls x where x.year is not null and grup_code='ADR') order by a.tto_date " );
                        stat1.setString(1,LOCATION_CODE);
                        stat1.setString(2,sac);
                    } 
             
                
                  result1 = stat1.executeQuery();
                  while(result1.next())
                  {    
                       invList.add(new InvDelayBean(result1.getString("year"),result1.getString("company"),result1.getString("inv_no"),result1.getString("excs_inv_no"),result1.getString("tto_date"),result1.getString("to_date"),result1.getString("etd_date"),result1.getString("buyer"),result1.getString("ac_holder")));
                
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

   

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public List getSREASON() {
        return SREASON;
    }

    public void setSREASON(List SREASON) {
        this.SREASON = SREASON;
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

    public List getYEAR() {
        return YEAR;
    }

    public void setYEAR(List YEAR) {
        this.YEAR = YEAR;
    }

    public List getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(List COMPANY) {
        this.COMPANY = COMPANY;
    }

    public List getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(List INV_NO) {
        this.INV_NO = INV_NO;
    }
   
 

}

