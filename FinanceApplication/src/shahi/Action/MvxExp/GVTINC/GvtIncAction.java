
package shahi.Action.MvxExp.GVTINC;
   
import java.sql.*;
import java.util.*; 
import java.io.*; 
import com.opensymphony.xwork2.ActionContext;
import static com.opensymphony.xwork2.Action.ERROR;
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
import shahi.Action.MvxExp.GVTINC.Beans.DBKRATEBEAN;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class GvtIncAction extends ActionSupport {

   
    private String aausrid ;
   
    private String currentdate;
    private List errorlist;
    private List typeList= new ArrayList();
    private String stype;
    private String showFlag;
    private List DetailList = new ArrayList();
  
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
           
            
              
                 stat1 = conn.prepareStatement("select distinct  inc_type  from ei_gvtinc_cntry order by 1" );
                 result1 = stat1.executeQuery();
                 while(result1.next())
                {   
                     typeList.add(new GetListBean(result1.getString("inc_type"),result1.getString("inc_type")));      
                 }   
                 
             
             
              double strrecv=0; double dbkwoff=0; double balamt=0;   
              if (showFlag!=null && showFlag.length()>0)
             {  
                stat1 = conn.prepareStatement(" select b.inc_type,a.COUNTRY_DESC,a.country,to_char(b.eff_date,'dd/mm/yyyy') eff_date,to_char(end_date,'dd/mm/yyyy') end_date from ei_country_mast a,ei_gvtinc_cntry b where a.country=b.cntry_code order by 1,2,3 " );
                result1 = stat1.executeQuery(); 
               while(result1.next())
               {    
                  
                       
                    DetailList.add(new DBKRATEBEAN(result1.getString("EFF_DATE"),result1.getString("END_DATE"),result1.getString("inc_type"),result1.getString("COUNTRY_DESC"),result1.getString("country"),null,null));
                    balamt=0; dbkwoff=0;
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
                    System.out.print("File Name : GvtIncAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  GvtIncActionAction.java ");
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

   

    public List getTypeList() {
        return typeList;
    }

    public void setTypeList(List typeList) {
        this.typeList = typeList;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public List getDetailList() {
        return DetailList;
    }

    public void setDetailList(List DetailList) {
        this.DetailList = DetailList;
    }

  

    
  
     

  
   

    

}
