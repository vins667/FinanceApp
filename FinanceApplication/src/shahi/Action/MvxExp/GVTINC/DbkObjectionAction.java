
package shahi.Action.MvxExp.GVTINC;
    
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import java.sql.*;
import java.util.*; 
import java.io.*; 
import com.opensymphony.xwork2.ActionContext;
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
import shahi.Action.MvxExp.GVTINC.Beans.EBRCBEAN; 
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class DbkObjectionAction extends ActionSupport {

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
    private List SB_DATE;
    private List LEO_DATE;
    private List SB_AMT;
    private List OBJ_DESC;
    
   
  
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
usrid="227350";
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
          
               ActionContext ac = ActionContext.getContext();
               ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
               File uploadDir = new File(sc.getRealPath("shahiwebpages/MvxExp/file"));
              
                 
               if (uploadDir.exists() == false) {
                    uploadDir.mkdirs();
                 }
                             stat1=conn.prepareStatement("select LOCATION_CODE from SHAHIWEB.SEH_WEB_USERS where USER_ID=?");
                             stat1.setString(1,usrid);
                             result1 = stat1.executeQuery();
                             if (result1.next())
                             {
                             LOCATION_CODE=result1.getString("LOCATION_CODE");
                             }

             if (userFile != null && txtFile!=null && txtFile.equals("YES"))
             {
                 
            	 String ext = this.userFileFileName.substring(this.userFileFileName.lastIndexOf("."));
                 this.userFileFileName=usrid+ext;
                 File fileToCreate = new File(uploadDir, this.userFileFileName.toUpperCase());
                 FileUtils.copyFile(this.userFile, fileToCreate);
           
                 try {  
                    int counter = 0;
                 	
                   FileInputStream file = new FileInputStream(this.userFile);
       
            HSSFWorkbook workbook = new HSSFWorkbook(file);
 
        //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
 
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
 
        int ctr=0;
        while(rowIterator.hasNext()) {
          
            String VPORT=null;
            String VSBNO=null;
            String VSBDATE= null;
            String VBRC=null;
          
            double VSBAMT=0;
            Date VRLDATE=null;
            String VCRNCY=null;
            Date DATA1=null;
            Date DATA2=null;
            String DATA8=null;
             
            Row row = rowIterator.next();
 
                Cell cell1 = row.getCell(1);
                   VSBNO = cell1.getStringCellValue().trim().replace(" ","");
                  // VSBNO=String.valueOf((long)cell1.getNumericCellValue());
              
                   Cell cell2 = row.getCell(2);
                     if(cell2.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                        DATA1=format.parse(cell2.getStringCellValue());
                    }
                   Cell cell3 = row.getCell(3);
                     if(cell3.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                        DATA2=format.parse(cell3.getStringCellValue());
                    }
                    
                    Cell cell5 = row.getCell(5);
                     VCRNCY=cell5.getStringCellValue();
                  
            
                   Cell cell4 = row.getCell(4);
                   
                   if(cell4.getStringCellValue().trim().length()>0){
                        
                        VSBAMT=Double.parseDouble(cell4.getStringCellValue().trim().replaceAll("[^a-zA-Z0-9. ]+",""));
                     }
                  // VRLAMT=String.valueOf((long)cell4.getNumericCellValue()); 
                
                    SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                    VSBDATE=format.format(DATA1);
                    VBRC= format.format(DATA2);
                    
                   FileList.add(new EBRCBEAN(VPORT,VSBNO,VSBDATE,VBRC,VBRC,VSBAMT,VSBNO,VCRNCY) );
                    
   
        }
 
        file.close();                 }
                 catch (FileNotFoundException e) {
                     conn.rollback();
                     addActionError(e.getMessage());
                     e.printStackTrace();
                 } 
            	 
             }
             
           
              
               if (saveflag!=null && saveflag.equals("YES"))
               {   for(int i=0; i<SB_NO.size(); i++)
                  {   
                        String vsbno=SB_NO.get(i).toString().trim();
                        String vsbdt=SB_DATE.get(i).toString();
                        String leodt=LEO_DATE.get(i).toString();
                   if (vsbno.length()>0 && vsbno!=null)
                      {    
                               
                                 if(SB_DATE.get(i).toString()!=null && SB_DATE.get(i).toString().length()>0)
                                 {
                                      stat = conn.prepareStatement("INSERT INTO eis_dbkobj_upload (SB_NO,sb_date,LET_EXP_DATE,sb_amt,OBJ_DESC,USER_ID,LOCT_CODE,TDATE) VALUES (?,?,?,?,?,?,?,sysdate)  "  );
                                      stat.setString(1,vsbno);
                                      stat.setString(2,vsbdt.trim());
                                      stat.setString(3,leodt);
                                   
                                      stat.setString(4,SB_AMT.get(i).toString().trim());
                                      stat.setString(5,OBJ_DESC.get(i).toString().toUpperCase().trim());
                                       stat.setString(6,usrid); 
                                      
                                      stat.setString(7,LOCATION_CODE); 
                                      stat.executeUpdate();
                                    flag=1;
                                      
                                 }
                                 if (stat != null) { stat.close(); }
                                 if (result != null) { result.close(); }
                             
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
                    System.out.print("File Name : DbkObjectionAction.java Exception in finally block");
                    addActionError(e.getMessage()+"DbkObkectionUpdateActopm.java ");
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
 
  
    public List getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(List SB_DATE) {
        this.SB_DATE = SB_DATE;
    }

    public List getLEO_DATE() {
        return LEO_DATE;
    }

    public void setLEO_DATE(List LEO_DATE) {
        this.LEO_DATE = LEO_DATE;
    }

    public List getSB_AMT() {
        return SB_AMT;
    }

    public void setSB_AMT(List SB_AMT) {
        this.SB_AMT = SB_AMT;
    }

    public List getOBJ_DESC() {
        return OBJ_DESC;
    }

    public void setOBJ_DESC(List OBJ_DESC) {
        this.OBJ_DESC = OBJ_DESC;
    }

    

  

  
     

    

}
