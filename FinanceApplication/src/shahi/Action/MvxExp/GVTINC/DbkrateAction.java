
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.MvxExp.GVTINC.Beans.DBKRATEBEAN; 
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class DbkrateAction extends ActionSupport {

    private List FileList = new ArrayList();
    private String aausrid ;
    private String saveflag;
    private String currentdate;
    private String txtFile;
    private File userFile;
    private String userFileContentType;
    private String userFileFileName;
    private List errorlist;
    private List DBK_RATE;
    private List EFF_DATE;
    private List END_DATE;
    private List DBK_SLNO;
    private List DBK_CAPVAL;
    private List DBK_UOM;
    private List DBK_SEGMENT;
    
   
  
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


            PreparedStatement stat1=null,stat=null,stat2=null;
            ResultSet RTaccess = null,result1 =null,result=null,result2=null;
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
          
             String VDBKSLNO=null;
             Date VEFFDATE=null;
             Date VENDDATE=null;
             String VSEGMENT=null;
             String VUOM=null;
             String VCAPVAL=null;
             String VRATE=null;
         
            Row row = rowIterator.next();
            
                   Cell cell0 = row.getCell(0);
                   VDBKSLNO=cell0.getStringCellValue();
                  Cell cell1 = row.getCell(1);
            if(cell1.getCellType()==0){
                    if (DateUtil.isCellDateFormatted(cell1)){
                       VEFFDATE=cell1.getDateCellValue();
                     }
                    } 
                    if(cell1.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        VEFFDATE=format.parse(cell1.getStringCellValue());
                    }
                   Cell cell2 = row.getCell(2);
                  if(cell2.getCellType()==0){
                    if (DateUtil.isCellDateFormatted(cell1)){
                       VENDDATE=cell2.getDateCellValue();
                     }
                    } 
                    if(cell2.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        VENDDATE=format.parse(cell2.getStringCellValue());
                    }
                      Cell cell3 = row.getCell(3);
                     VSEGMENT=cell3.getStringCellValue();
                       Cell cell4 = row.getCell(4);
                     VUOM=cell4.getStringCellValue();
                     
                    Cell cell5 = row.getCell(5);
                    
                    VRATE=String.valueOf((double)cell5.getNumericCellValue());
                      Cell cell6 = row.getCell(6);
                     VCAPVAL = String.valueOf((double)cell6.getNumericCellValue());
                    
                      SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy"); 
                     String EFFDT=format.format(VEFFDATE);
                     String ENDDT=format.format(VENDDATE);
        
                     FileList.add(new DBKRATEBEAN(EFFDT,ENDDT,VDBKSLNO,VRATE,VCAPVAL,VUOM,VSEGMENT) );
                    
  
        }
 
        file.close();                 }
                 catch (FileNotFoundException e) {
                     conn.rollback();
                     addActionError(e.getMessage());
                     e.printStackTrace();
                 } 
            	 
             }
             
           
              
               if (saveflag!=null && saveflag.equals("YES"))
               {   for(int i=0; i<DBK_SLNO.size(); i++)
                  {   
                     if(DBK_SLNO.get(i).toString()!=null && DBK_SLNO.get(i).toString().length()>0)
                        {
                             stat1=conn.prepareStatement("select * from exports.ei_dbk_rate_mast where dbk_slno=? and dbk_begin_date=?");
                             stat1.setString(1,DBK_SLNO.get(i).toString());
                             stat1.setString(2,EFF_DATE.get(i).toString());
                             result1 = stat1.executeQuery();
                             if (result1.next())
                             { 
                               addActionMessage("Records Already Exist  !! Sl No"+DBK_SLNO.get(i).toString()+"   Start Date "+EFF_DATE.get(i).toString());
                               return ERROR;
                             
                             }             
                           }
                                 if (stat1 != null) { stat1.close(); }
                                 if (result1 != null) { result1.close(); }
                          }
                  for(int j=0; j<DBK_SLNO.size(); j++)
                  {   
                     if(DBK_SLNO.get(j).toString()!=null && DBK_SLNO.get(j).toString().length()>0)
                        {
                             stat1=conn.prepareStatement("select * from exports.ei_dbk_rate_mast where dbk_slno=? and ? between dbk_begin_date and dbk_end_date");
                             stat1.setString(1,DBK_SLNO.get(j).toString());
                             stat1.setString(2,EFF_DATE.get(j).toString());
                              result1 = stat1.executeQuery();
                             if (result1.next())
                             {
                                     stat= conn.prepareStatement(" update exports.ei_dbk_rate_mast set dbk_end_date=to_date(?)-1 where dbk_slno=? and ? between  dbk_begin_date and dbk_end_date "  );
                                      stat.setString(1,EFF_DATE.get(j).toString());
                                    
                                      stat.setString(2,DBK_SLNO.get(j).toString());
                                      stat.setString(3,EFF_DATE.get(j).toString());
                                       stat.executeUpdate();
                              } 
                                   stat2=conn.prepareStatement("insert into exports.ei_dbk_rate_mast (dbk_slno,dbk_begin_date,dbk_end_date,dbk_rate,dbk_celling,dbk_segment,dbk_unit,custom_rate,seh_user,DBK_TYPE,tdate) values (?,?,?,?,?,?,?,?,?,'D',sysdate)");
                                 stat2.setString(1,DBK_SLNO.get(j).toString());
                                 stat2.setString(2,EFF_DATE.get(j).toString());
                                 stat2.setString(3,END_DATE.get(j).toString());
                                 stat2.setString(4,DBK_RATE.get(j).toString());
                                 stat2.setString(5,DBK_CAPVAL.get(j).toString());
                                 stat2.setString(6,DBK_SEGMENT.get(j).toString());
                                 stat2.setString(7,DBK_UOM.get(j).toString());
                                 stat2.setString(8,DBK_RATE.get(j).toString());
                                 stat2.setString(9,usrid);
                                 stat2.executeUpdate();
                                    flag=1;
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
                    if (stat2 != null) { stat2.close(); }
                    if (stat != null) { stat.close(); }
 
                    if (RTaccess != null) { RTaccess.close(); }
                    if (result != null) { result.close(); }
                    if (result1 != null) { result1.close(); }
                    if (result2 != null) { result2.close(); }
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

    public List getDBK_RATE() {
        return DBK_RATE;
    }

    public void setDBK_RATE(List DBK_RATE) {
        this.DBK_RATE = DBK_RATE;
    }

    public List getEFF_DATE() {
        return EFF_DATE;
    }

    public void setEFF_DATE(List EFF_DATE) {
        this.EFF_DATE = EFF_DATE;
    }

    public List getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(List END_DATE) {
        this.END_DATE = END_DATE;
    }

    public List getDBK_SLNO() {
        return DBK_SLNO;
    }

    public void setDBK_SLNO(List DBK_SLNO) {
        this.DBK_SLNO = DBK_SLNO;
    }

    public List getDBK_CAPVAL() {
        return DBK_CAPVAL;
    }

    public void setDBK_CAPVAL(List DBK_CAPVAL) {
        this.DBK_CAPVAL = DBK_CAPVAL;
    }

    public List getDBK_UOM() {
        return DBK_UOM;
    }

    public void setDBK_UOM(List DBK_UOM) {
        this.DBK_UOM = DBK_UOM;
    }

    public List getDBK_SEGMENT() {
        return DBK_SEGMENT;
    }

    public void setDBK_SEGMENT(List DBK_SEGMENT) {
        this.DBK_SEGMENT = DBK_SEGMENT;
    }

  
   
    

    

}
