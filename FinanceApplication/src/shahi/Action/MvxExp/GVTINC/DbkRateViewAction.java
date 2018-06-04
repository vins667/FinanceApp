
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


public class DbkRateViewAction extends ActionSupport {

   
    private String aausrid ;
   
    private String currentdate;
    private String searchtype;
    private String searchcode;
    private String SEARCH_FROM;
    private List errorlist;
    private String showFlag;
    private List DetailList = new ArrayList();
    private java.util.Date MIN_DATE;
  
    public String execute()
   {
       try{
            EisUtil pisdate = new EisUtil();
            this.MIN_DATE = new java.util.Date();
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
           
            
              
             
                
              if (showFlag!=null && showFlag.length()>0)
             {  
                if (searchtype.equals("DBK")) 
                {  
                    stat1 = conn.prepareStatement(" select DBK_SLNO,DBK_RATE,DBK_CELLING,DBK_SEGMENT,DBK_UNIT,to_char(DBK_BEGIN_date,'dd/mm/yyyy') eff_date,to_char(DBK_end_date,'dd/mm/yyyy') end_date from ei_dbk_rate_mast  where dbk_slno like ? and ? between trunc(dbk_begin_date) and trunc(dbk_end_date) " );
                    stat1.setString(1,searchcode+"%");
                    stat1.setString(2,SEARCH_FROM);
                    result1 = stat1.executeQuery(); 
                   while(result1.next())
                   {    
                        DetailList.add(new DBKRATEBEAN(result1.getString("EFF_DATE"),result1.getString("END_DATE"),result1.getString("DBK_SLNO"),result1.getString("DBK_RATE"),result1.getString("DBK_CELLING"),result1.getString("DBK_UNIT"),result1.getString("DBK_SEGMENT")));
                    
                   } // end while
                }
                if (searchtype.equals("STR")) 
                {  
                    stat1 = conn.prepareStatement(" select STR_SLNO,STR_RATE,to_char(BEGIN_date,'dd/mm/yyyy') eff_date,to_char(end_date,'dd/mm/yyyy') end_date from ei_STR_rate_mast  where STR_slno like ? and ? between trunc(begin_date) and trunc(end_date) " );
                    stat1.setString(1,searchcode+"%");
                    stat1.setString(2,SEARCH_FROM);
                    result1 = stat1.executeQuery(); 
                   while(result1.next())
                   {    
                        DetailList.add(new DBKRATEBEAN(result1.getString("EFF_DATE"),result1.getString("END_DATE"),result1.getString("STR_SLNO"),result1.getString("STR_RATE"),"","",""));
                    
                   } // end while
                }
                if (searchtype.equals("ROSL")) 
                {  
                    stat1 = conn.prepareStatement(" select ROSL_SLNO,ROSL_RATE,ROSL_CELLING,ROSL_SEGMENT,ROSL_UOM,to_char(BEGIN_date,'dd/mm/yyyy') eff_date,to_char(end_date,'dd/mm/yyyy') end_date from ei_ROSL_rate_mast  where ROSL_slno like ? and ? between trunc(begin_date) and trunc(end_date) " );
                    stat1.setString(1,searchcode+"%");
                    stat1.setString(2,SEARCH_FROM);
                    result1 = stat1.executeQuery(); 
                   while(result1.next())
                   {    
                        DetailList.add(new DBKRATEBEAN(result1.getString("EFF_DATE"),result1.getString("END_DATE"),result1.getString("ROSL_SLNO"),result1.getString("ROSL_RATE"),result1.getString("ROSL_CELLING"),result1.getString("ROSL_UOM"),result1.getString("ROSL_SEGMENT")));
                    
                   } // end while
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
                    System.out.print("File Name : DbkRateViewAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  GDbkRateViewAction.java ");
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

    public List getDetailList() {
        return DetailList;
    }

    public void setDetailList(List DetailList) {
        this.DetailList = DetailList;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public String getSearchcode() {
        return searchcode;
    }

    public void setSearchcode(String searchcode) {
        this.searchcode = searchcode;
    }

    public String getSEARCH_FROM() {
        return SEARCH_FROM;
    }

    public void setSEARCH_FROM(String SEARCH_FROM) {
        this.SEARCH_FROM = SEARCH_FROM;
    }

    public Date getMIN_DATE() {
        return MIN_DATE;
    }

    public void setMIN_DATE(Date MIN_DATE) {
        this.MIN_DATE = MIN_DATE;
    }

  

    
  
     

  
   

    

}
