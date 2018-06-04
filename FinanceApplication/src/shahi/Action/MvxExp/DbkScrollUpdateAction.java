
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
import shahi.Action.MvxExp.Beans.DBKSCROLLBEAN; 
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil; 
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class DbkScrollUpdateAction extends ActionSupport {

    private List FileList = new ArrayList();
    private String aausrid ;
    private String saveflag;
    private String currentdate;
    private String txtFile;
    private File userFile;
    private String userFileContentType;
    private String userFileFileName;
    private List errorlist;
    private List SR_NO;
    private List PORT;
    private List SB_NO;
    private List SB_DATE;
    private List SCROLL_NO;
    private List SCROLL_DATE;
    private List AMOUNT;
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
          
            String DATA1=null;
            String DATA2=null;
            String DATA3=null;
            String DATA4=null;
            String DATA5=null;
            String DATA6=null; 
            double DATA7=0.00;
             
            Row row = rowIterator.next();
            
                   Cell cell0 = row.getCell(0);
                   //DATA1=String.format("%s",cell0.getNumericCellValue());
               
                  if(cell0.getCellType()==1){
                           DATA1=cell0.getStringCellValue();
                       } 
                    else{
                           DATA1=String.valueOf((long)cell0.getNumericCellValue());
                       }
                 
                    Cell cell1 = row.getCell(1);
                    DATA2 = cell1.getStringCellValue();
                    
                    Cell cell2 = row.getCell(2);
                    
                     if(cell2.getCellType()==1){
                           DATA3=cell2.getStringCellValue();
                       } 
                    else{
                           DATA3=String.valueOf((long)cell2.getNumericCellValue());
                       }
                    
                   
                
                
                    Cell cell3 = row.getCell(3);
                     if(cell3.getCellType()==0){
                        if (DateUtil.isCellDateFormatted(cell3)){
                            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                            DATA4=format.format(cell3.getDateCellValue());
                          }
                        }  
                   
                    if(cell3.getCellType()==1){
                           DATA4=cell3.getStringCellValue();
                         }
                    Cell cell4 = row.getCell(4);
               
                    DATA5 = cell4.getStringCellValue();
                  
                    Cell cell5 = row.getCell(5);
                   
                        if(cell5.getCellType()==1){
                           DATA6=cell5.getStringCellValue();
                         }
                        if(cell5.getCellType()==0){
                        if (DateUtil.isCellDateFormatted(cell5)){
                            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                            DATA6=format.format(cell5.getDateCellValue());
                          }
                        } 
                     
                     Cell cell6 = row.getCell(6);
                    
                    if(cell6.getCellType()==1){
                       String xxx=(cell6.getStringCellValue()).toString().replaceAll(" ", "");
                       
                     DATA7= Integer.parseInt(xxx);
                      
                       
                   }else{
                   
                       DATA7= cell6.getNumericCellValue();
                   }
                 
                   FileList.add(new DBKSCROLLBEAN(DATA1,DATA2,DATA3,DATA4,DATA5,DATA6.substring(0,11),DATA7) );
                    
  
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
                      
                             stat1=conn.prepareStatement("select * from eis_dbk_scroll where sb_no=? and sb_date=?");
                             stat1.setString(1,vsbno);
                             stat1.setString(2,vsbdt);
                              result1 = stat1.executeQuery();
                             if (result1.next()==false)
                             {       
                                 if(SB_DATE.get(i).toString()!=null && SB_DATE.get(i).toString().length()>0)
                                 {
                                    stat = conn.prepareStatement("INSERT INTO eis_dbk_scroll (PORT,SB_NO,SB_DATE,SCROLL_NO,SCROLL_DATE,AMT,USER_ID,TDATE) VALUES (?,?,?,?,?,?,?,SYSDATE)  "  );
                                    stat.setString(1,PORT.get(i).toString());
                                    stat.setString(2,vsbno);
                                    stat.setString(3,vsbdt);
                                    stat.setString(4,SCROLL_NO.get(i).toString());
                                    stat.setString(5,SCROLL_DATE.get(i).toString());
                                    stat.setString(6,AMOUNT.get(i).toString());
                                    stat.setString(7,usrid); 
                                    stat.executeUpdate();
                                    flag=1;
                                 }
                                   if (stat != null) { stat.close(); }
                                 
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
                    System.out.print("File Name : DbkScrollUpdateAction.java Exception in finally block");
                    addActionError(e.getMessage()+"DbkScrollUpdateActopm.java ");
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
 

    public List getBankList() {
        return BankList;
    }

    public void setBankList(List BankList) {
        this.BankList = BankList;
    }

    public List getSR_NO() {
        return SR_NO;
    }

    public void setSR_NO(List SR_NO) {
        this.SR_NO = SR_NO;
    }

    public List getPORT() {
        return PORT;
    }

    public void setPORT(List PORT) {
        this.PORT = PORT;
    }

    public List getSCROLL_DATE() {
        return SCROLL_DATE;
    }

    public void setSCROLL_DATE(List SCROLL_DATE) {
        this.SCROLL_DATE = SCROLL_DATE;
    }

    public List getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(List AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

   
    public List getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(List SB_DATE) {
        this.SB_DATE = SB_DATE;
    }

    public List getSCROLL_NO() {
        return SCROLL_NO;
    }

    public void setSCROLL_NO(List SCROLL_NO) {
        this.SCROLL_NO = SCROLL_NO;
    }

  
   

    

}
