
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


public class EbrcUploadAction extends ActionSupport {

    private List FileList = new ArrayList();
    private String aausrid ;
    private String saveflag;
    
    private String currentdate;
    private String txtFile;
    private File userFile;
    private String userFileContentType;
    private String userFileFileName;
    private List errorlist;
    private List PORT;
    private List SB_NO;
    private List SB_DATE;
    private List BRC_NO;
    private List BRC_DATE;
    private List RL_DATE;
    private List RL_AMT;
    private List CRNCY_CODE;
   
  
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
          
               ActionContext ac = ActionContext.getContext();
               ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
               File uploadDir = new File(sc.getRealPath("shahiwebpages/MvxExp/file"));
              
                 
               if (uploadDir.exists() == false) {
                    uploadDir.mkdirs();
                 }
                             stat1=conn.prepareStatement("select LOCATION_CODE from SEH_WEB_USERS where USER_ID=?");
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
            Date VSBDATE= null;
            String VBRC=null;
            Date VBRCDATE=null;
            double VRLAMT=0;
            Date VRLDATE=null;
            String VCRNCY=null;
            Date DATA1=null;
            String DATA8=null;
             
            Row row = rowIterator.next();
            
                   Cell cell0 = row.getCell(0);
                    VBRC=cell0.getStringCellValue();
                  
                  Cell cell1 = row.getCell(1);
                   
                  if(cell1.getCellType()==0){
                    if (DateUtil.isCellDateFormatted(cell1)){
                       VBRCDATE=cell1.getDateCellValue();
                     }
                    } 
                    if(cell1.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        VBRCDATE=format.parse(cell1.getStringCellValue());
                    }
                
                    Cell cell5 = row.getCell(5);
                   //VSBNO = cell5.getNumericCellValue();
                    VSBNO=String.valueOf((long)cell5.getNumericCellValue());
                    
                    Cell cell6 = row.getCell(6);
                    VPORT = cell6.getStringCellValue();
                       Cell cell7 = row.getCell(7);
                        if(cell7.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        VSBDATE=format.parse(cell7.getStringCellValue());
                    }
                
                    
                      Cell cell8 = row.getCell(8);
                      VRLAMT= cell8.getNumericCellValue();
                    
                      Cell cell9 = row.getCell(9);
                      VCRNCY=cell9.getStringCellValue();
                
                
                     Cell cell10 = row.getCell(10);
                    
                  if(cell10.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        VRLDATE=format.parse(cell10.getStringCellValue());
                    }
                     SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
                     String TSBDT=format.format(VSBDATE);
                     String TRLDT=format.format(VRLDATE);
                     String TBRCDT=format.format(VBRCDATE);
                     
                    
                   FileList.add(new EBRCBEAN(VPORT,VSBNO,TSBDT,VBRC,TBRCDT,VRLAMT,TRLDT,VCRNCY) );
                    
  
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
                        String vsbno=SB_NO.get(i).toString();
                        String vsbdt=SB_DATE.get(i).toString();
                        
                   if (vsbno.length()>0 && vsbno!=null)
                      {    
                      
                             stat1=conn.prepareStatement("select * from eis_ebrc_upload where sb_no=? and sb_date=?");
                             stat1.setString(1,vsbno);
                             stat1.setString(2,vsbdt);
                              result1 = stat1.executeQuery();
                             if (result1.next()==false)
                             {       
                                 if(SB_DATE.get(i).toString()!=null && SB_DATE.get(i).toString().length()>0)
                                 {
                                    stat = conn.prepareStatement("INSERT INTO eis_ebrc_upload (PORT,SB_NO,SB_DATE,BRC_NO,BRC_DATE,RL_VALUE,RL_DATE,CRNCY_CODE,USER_ID,LOCT_CODE,TDATE) VALUES (?,?,?,?,?,?,?,?,?,?,SYSDATE)  "  );
                                     
                                    stat.setString(1,PORT.get(i).toString());
                                    stat.setString(2,vsbno);
                                    stat.setString(3,vsbdt);
                                    stat.setString(4,BRC_NO.get(i).toString());
                                    stat.setString(5,BRC_DATE.get(i).toString());
                                    stat.setString(6,RL_AMT.get(i).toString());
                                    stat.setString(7,RL_DATE.get(i).toString());
                                    stat.setString(8,CRNCY_CODE.get(i).toString());
                                    stat.setString(9,usrid); 
                                    stat.setString(10,LOCATION_CODE); 
                                    result = stat.executeQuery();
                                    flag=1;
                                     
                                 }
                                 if (stat != null) { stat.close(); }
                                 if (result != null) { result.close(); }
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
                    System.out.print("File Name : EbrcUploadAction.java Exception in finally block");
                    addActionError(e.getMessage()+"EbrcUploadUpdateActopm.java ");
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

    public List getRL_DATE() {
        return RL_DATE;
    }

    public void setRL_DATE(List RL_DATE) {
        this.RL_DATE = RL_DATE;
    }

    public List getRL_AMT() {
        return RL_AMT;
    }

    public void setRL_AMT(List RL_AMT) {
        this.RL_AMT = RL_AMT;
    }

 
    public List getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(List CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }



  
    

    

}
