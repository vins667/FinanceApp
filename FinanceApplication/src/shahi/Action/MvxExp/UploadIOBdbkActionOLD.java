
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
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.MvxExp.Beans.IOBdbkBean; 
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.Master.Beans.GetListBean;


public class UploadIOBdbkActionOLD extends ActionSupport {

    private List FileList = new ArrayList();
    private String aausrid ;
    private String saveflag;
    private String currentdate;
    private String txtFile;
    private File userFile;
    private String userFileContentType;
    private String userFileFileName;
    private List errorlist;
    private List SB_NO;
    private List TRDATE;
    private List AMT_DR;
    private List AMT_CR;
    private String sbank;
    private String dbkbank;
    private List REMAKS;
    private List BankList = new ArrayList();
  
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
 System.out.println("ok-a-1");  
        if(usrid==null)
        {
           session.put("sessUserId",aausrid); 
           usrid=aausrid;
        }

        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
       System.out.println("ok-99");  
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
 System.out.println("ok99 --- a");  
        try {
                Odbcon = new connection().getConnection();
                Odbcon.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("IBM Database Connection Not Valid !!");
                return INPUT;
            } // end catch

System.out.println("ok99 --- b");  
            PreparedStatement stat1=null,stat=null;
            ResultSet RTaccess = null,result1 =null,result=null;
         try
           { 
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
          System.out.println("ok99 --- c");   
               ActionContext ac = ActionContext.getContext();
               ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
               File uploadDir = new File(sc.getRealPath("EIS/GVTINC/TextFile"));
              
                 stat1 = conn.prepareStatement("select bank_code,bank_desc from eis_dbk_bank  order by 1" );
                result1 = stat1.executeQuery();
                while(result1.next())
               {  
                 BankList.add(new GetListBean(result1.getString("bank_code"),result1.getString("bank_desc")));      
               }
               System.out.println("ok-1");   
               if (uploadDir.exists() == false) {
                    uploadDir.mkdirs();
                 }
            System.out.println("ok-2");  
             if (userFile != null && txtFile!=null && txtFile.equals("YES")) {
                 String ext = this.userFileFileName.substring(this.userFileFileName.lastIndexOf("."));
                 this.userFileFileName=sbank+usrid+ext;
                 System.out.println("file "+userFileFileName);
                 File fileToCreate = new File(uploadDir, this.userFileFileName.toUpperCase());
                 FileUtils.copyFile(this.userFile, fileToCreate);
            
                 try { 
                    int counter = 0;
                    Scanner scanner = new Scanner(this.userFile);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("");
                           while (lineScanner.hasNext()) {
                            ++counter;
                            String part = lineScanner.nextLine();
                           
                            if (part!=null && part.length()> 0)
                            {  
                            
                              if (sbank.equals("IOBT")) 
                              { String linetext[] =part.split("\\,");
                                FileList.add(new IOBdbkBean(linetext[0],linetext[2],linetext[3],linetext[4],linetext[5],"","") );
                              }  
                              else{
 
                            
                            String DATA1 = line.substring(0,line.indexOf(","));
                            String DATA2=null;
                            if(line.indexOf("SB.No:")!=-1){
                                DATA2 = line.substring(line.indexOf("SB.No:")+6,line.indexOf("Date:")-1);
                            }
                            String DATA3=null;
                            if(line.indexOf(",\" / \",")!=-1){
                                String DATATEMP=line.substring(line.indexOf(",\" / \",")+7);
                                DATA3=DATATEMP.substring(0,DATATEMP.indexOf(","));
                            }
                            String DATA4=null;
                            if(line.indexOf(",\" \",")!=-1)
                            {
                                String DATATEMP=line.substring(line.indexOf(",\" \",")+5);
                                if(DATATEMP.indexOf("\",\"")!=-1){
                                    DATA4=DATATEMP.substring(1,DATATEMP.indexOf("\",\""));
                                }
                                else{
                                    DATA4=DATATEMP.substring(0,DATATEMP.indexOf(","));
                                }
                                System.out.println("data 4 "+DATA4); 
                                }
                                
                                 DATA4=DATA4.replaceAll("[^a-zA-Z0-9.]+","");
                                FileList.add(new IOBdbkBean(DATA1,DATA2,DATA3,null,DATA4,"","") );
                              
                              }
                           }
                          }
                    }
                } catch (FileNotFoundException e) {
                    conn.rollback();
                    addActionError(e.getMessage());
                    e.printStackTrace();
                } 
             }
               if (saveflag!=null && saveflag.equals("YES"))
             {   for(int i=0; i<SB_NO.size(); i++)
                  {   
                        String vdesc=SB_NO.get(i).toString();
                        String vdr=AMT_DR.get(i).toString();
                   if (vdesc.length()>0 && vdesc!=null)
                      {    
                        if(vdesc.length()>10)
                        {vdesc=SB_NO.get(i).toString().substring(0,19);
                         vdr="1";
                        }
                             stat1=conn.prepareStatement("select * from eis_dbkchq_upload where sb_no=? ");
                             stat1.setString(1,vdesc);
                              result1 = stat1.executeQuery();
                             if (result1.next()==false)
                             {      
                                    stat = conn.prepareStatement("INSERT INTO eis_dbkchq_upload (TR_DATE,SB_NO,AMT_DR,AMT_CR,TDATE,USER_ID,BANK_CODE,BRANCH_CODE) VALUES (?,?,?,?,sysdate,?,?,?)  "  );
                                    stat.setString(1,TRDATE.get(i).toString());
                                    stat.setString(2,vdesc);
                                    stat.setString(3,vdr); 
                                    stat.setString(4,AMT_CR.get(i).toString());
                                    stat.setString(5,usrid); 
                                    stat.setString(6,dbkbank);
                                    stat.setString(7,REMAKS.get(i).toString());
                                    result = stat.executeQuery();
                                    
                                     stat1 = conn.prepareStatement("delete from eis_dbkchq_upload where nvl(amt_dr,0)>0  "  );
                                     result1 = stat1.executeQuery();
                                    
                                    flag=1;
                             }
                  }
             
                  }
                  
             
             }
           System.out.println("ok-3");    
               
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
                    System.out.print("File Name : UploadIOBdbkAction.java Exception in finally block");
                    addActionError(e.getMessage()+"  UploadIOBdbkAction.java ");
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

        

    public String getSaveflag() {
        return saveflag;
    }

    public void setSaveflag(String saveflag) {
        this.saveflag = saveflag;
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

    public String getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(String txtFile) {
        this.txtFile = txtFile;
    }

    public File getUserFile() {
        return userFile;
    }

    public void setUserFile(File userFile) {
        this.userFile = userFile;
    }

    public String getUserFileContentType() {
        return userFileContentType;
    }

    public void setUserFileContentType(String userFileContentType) {
        this.userFileContentType = userFileContentType;
    }

    public String getUserFileFileName() {
        return userFileFileName;
    }

    public void setUserFileFileName(String userFileFileName) {
        this.userFileFileName = userFileFileName;
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

    public List getTRDATE() {
        return TRDATE;
    }

    public void setTRDATE(List TRDATE) {
        this.TRDATE = TRDATE;
    }

    public List getAMT_DR() {
        return AMT_DR;
    }

    public void setAMT_DR(List AMT_DR) {
        this.AMT_DR = AMT_DR;
    }

    public List getAMT_CR() {
        return AMT_CR;
    }

    public void setAMT_CR(List AMT_CR) {
        this.AMT_CR = AMT_CR;
    }

    public List getBankList() {
        return BankList;
    }

    public void setBankList(List BankList) {
        this.BankList = BankList;
    }

    public String getSbank() {
        return sbank;
    }

    public void setSbank(String sbank) {
        this.sbank = sbank;
    }

    public String getDbkbank() {
        return dbkbank;
    }

    public void setDbkbank(String dbkbank) {
        this.dbkbank = dbkbank;
    }

    public List getREMAKS() {
        return REMAKS;
    }

    public void setREMAKS(List REMAKS) {
        this.REMAKS = REMAKS;
    }

  
   

    

}
