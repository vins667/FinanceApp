
package shahi.Action.MvxExp;
   
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.MvxExp.Beans.EBRCBEAN; 
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class EbrcUpdateAction extends ActionSupport {

    private List FileList = new ArrayList();
    private String aausrid ;
    
    private String updFlag;
    private String showFlag;
    private String currentdate;
   
    
    private List errorlist;
    private List PORT;
    private List SB_NO;
    private List SB_DATE;
    private List BRC_NO;
    private List BRC_DATE;
     
    
   
  
    ResourceBundle aResourcBundle = null;

   private String getValue(String key) {
        return aResourcBundle.getString(key);
    }


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
          
        
             

            
             if (showFlag!=null && showFlag.equals("YES"))
              {
                 stat1 = conn.prepareStatement("select a.sb_no,to_char(a.sb_date,'dd-Mon-yyyy') sb_date,a.port,a.brc_no,to_char(a.brc_date,'dd-Mon-yyyy') brc_date,to_char(a.tdate,'dd-Mon-yyyy') tdate,b.location from eis_ebrc_upload a,ei_sbill_master b where b.shp_bill_no=trim(a.sb_no) and b.shp_bill_date=a.sb_date and b.brc_date is null and upd_date is null ");
                 result1 = stat1.executeQuery();
                while(result1.next())
               {    
                  FileList.add(new EBRCBEAN(result1.getString("port"),result1.getString("sb_no"),result1.getString("sb_date"),result1.getString("brc_no"),result1.getString("brc_date"),0,result1.getString("tdate"),result1.getString("location")) );
               }
                  
              }
              

         
              if (updFlag!=null && updFlag.equals("YES"))
              {
              for(int i=0; i<SB_NO.size(); i++)
                  {   
                        String vsbno=SB_NO.get(i).toString().trim();
                        String vsbdt=SB_DATE.get(i).toString();
                        
                   if (vsbno.length()>0 && vsbno!=null)
                      {    
                             stat1=conn.prepareStatement("select * from EI_SBILL_MASTER where shp_bill_no=? and shp_bill_date=?");
                             stat1.setString(1,vsbno);
                             stat1.setString(2,vsbdt);
                              result1 = stat1.executeQuery();
                             if (result1.next()==true)
                             {       
                                 stat=conn.prepareStatement("update EI_SBILL_MASTER SET BRC_DATE=SYSDATE,BRC_APPL=SYSDATE WHERE brc_date is null and shp_bill_no=? and shp_bill_date=? "); 
                                 stat.setString(1,vsbno);
                                 stat.setString(2,vsbdt);
                                 stat.executeUpdate();
                                 
                                        stat1 = conn.prepareStatement("update eis_ebrc_upload set upd_date=sysdate,upd_user=? where trim(sb_no)=? and sb_date=? and upd_date is null   " );
                                        stat1.setString(1,usrid);
                                        stat1.setString(2,vsbno);
                                        stat1.setString(3,vsbdt);
                                        stat1.executeUpdate();
                                 flag=1;
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
                    System.out.print("File Name : EbrcUpdateAction.java Exception in finally block");
                    addActionError(e.getMessage()+"EbrcUpdateUpdateActopm.java ");
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

    public List getSB_NO() {
        return SB_NO;
    }

    public void setSB_NO(List SB_NO) { 
        this.SB_NO = SB_NO;
    }
 
 
    public List getPORT() {
        return PORT;
    }

    public void setPORT(List PORT) {
        this.PORT = PORT;
    }

    
    public List getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(List SB_DATE) {
        this.SB_DATE = SB_DATE;
    }

    public List getBRC_NO() {
        return BRC_NO;
    }

    public void setBRC_NO(List BRC_NO) {
        this.BRC_NO = BRC_NO;
    }

    public List getBRC_DATE() {
        return BRC_DATE;
    }

    public void setBRC_DATE(List BRC_DATE) {
        this.BRC_DATE = BRC_DATE;
    }

   
    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }
 

  
   

    

}
