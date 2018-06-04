
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
import shahi.Action.MvxExp.Beans.IOBdbkBean; 
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;


public class UploadIOBdbkAction extends ActionSupport {

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
    private List SCROLL_NO;
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
              
                 stat1 = conn.prepareStatement("select bank_code,bank_desc||' - '||bank_code bank_desc from eis_dbk_bank where close_date is null order by 1" );
              result1 = stat1.executeQuery();
             while(result1.next())
             {  
                 BankList.add(new GetListBean(result1.getString("bank_code"),result1.getString("bank_desc")));      
              }
            
               if (uploadDir.exists() == false) {
                    uploadDir.mkdirs();
                 }
             
             if (userFile != null && txtFile!=null && txtFile.equals("YES") && sbank.equals("IOBT105")) 
             {
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
                             String linetext[] =part.split("\\,");
                                FileList.add(new IOBdbkBean(linetext[0],linetext[2]," "," ",linetext[3],linetext[4],linetext[5]) );
                              }
                          }
                    }
                } catch (FileNotFoundException e) {
                    conn.rollback();
                    addActionError(e.getMessage());
                    e.printStackTrace();
                } 
             }
             if (userFile != null && txtFile!=null && txtFile.equals("YES") && !sbank.equals("IOBT105") )
             {
                 
            	 String ext = this.userFileFileName.substring(this.userFileFileName.lastIndexOf("."));
                 this.userFileFileName=sbank+usrid+ext;
                 File fileToCreate = new File(uploadDir, this.userFileFileName.toUpperCase());
                 FileUtils.copyFile(this.userFile, fileToCreate);
           
                 try {  
                    int counter = 0;
                 	
FileInputStream file = new FileInputStream(this.userFile);
        //FileInputStream file = new FileInputStream("C:\\Users\\VIVEK\\Desktop\\LATEST SBI BANGALORE SAPL.xls");
            
 
        HSSFWorkbook workbook = new HSSFWorkbook(file);
 
        //Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
 
        //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();
 
        int ctr=0;
        while(rowIterator.hasNext()) {
            Date DATA1=null;
           
            String DATA2=null;
            String DATA2A=null;
            String DATA2B=null;
            String DATA3=null;
            double DATA4=0;
            Row row = rowIterator.next();
            
            Cell cell0 = row.getCell(0);
            //System.out.println("vivek"+cell0.getCellType());
          if (sbank.substring(0,3).equals("PNB"))
             {  
                 
                 Cell cell1 = row.getCell(1);
               
                 if(cell1.getCellType()==0){
                    if (DateUtil.isCellDateFormatted(cell1)){
                       DATA1=cell1.getDateCellValue();
                     }
                    } 
                    if(cell1.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        DATA1=format.parse(cell1.getStringCellValue());
                    }
                    Cell cell2 = row.getCell(2);
                
                    if(cell2.getStringCellValue().indexOf("SBNO")!=-1){
                           DATA2 = cell2.getStringCellValue().substring(cell2.getStringCellValue().indexOf("SBNO")+4);
                      }
                  
                     if(cell2.getStringCellValue().indexOf("SCRNO")!=-1){
                           DATA2B = cell2.getStringCellValue().substring(cell2.getStringCellValue().indexOf("SCRNO")+5,cell2.getStringCellValue().indexOf("/")-0);
                     }    
                   Cell cell6 = row.getCell(9);
                   if(cell6!=null && cell6.getCellType()==0){
                      DATA4=cell6.getNumericCellValue();
                      
                    }              
                  else{
                     if(cell6.getStringCellValue().trim().length()>0){
                        
                        DATA4=Double.parseDouble(cell6.getStringCellValue().replaceAll("[^a-zA-Z0-9.]+",""));
                     }
                   }
                     
                     
             }
          else if (sbank.substring(0,4).equals("CBNP"))
             {   
                
                 Cell cell1 = row.getCell(8);
                 if(cell1.getCellType()==0){
                    if (DateUtil.isCellDateFormatted(cell1)){
                       DATA1=cell1.getDateCellValue();
                     }
                    } 
                    if(cell1.getCellType()==1){
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        DATA1=format.parse(cell1.getStringCellValue());
                    } 
                 
                       Cell cell2 = row.getCell(2);
                       DATA2 =String.valueOf((long)cell2.getNumericCellValue());
                       Cell cell2A = row.getCell(3);
                       if(cell2A.getCellType()==0){
                        if (DateUtil.isCellDateFormatted(cell2A)){
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            DATA2A=format.format(cell2A.getDateCellValue());
                          }
                        } 
                       if(cell2A.getCellType()==1){
                           DATA2A=cell2A.getStringCellValue();
                        }
                       Cell cell3 = row.getCell(4);
                        DATA2B=cell3.getStringCellValue();
                       
                        Cell cell5 = row.getCell(1);
                          DATA3=cell5.getStringCellValue();
                       
                  
                       Cell cell6 = row.getCell(7);
                        DATA4=cell6.getNumericCellValue();
                                       
                     
             }
          else{  
                 
            if(cell0.getCellType()==0){
                if (DateUtil.isCellDateFormatted(cell0)){
                    DATA1=cell0.getDateCellValue();
                }
            }
            else if(cell0.getCellType()==1){
                SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
                if(cell0.getStringCellValue().trim().length()>0){
                    DATA1=formatter.parse(cell0.getStringCellValue());
                }
            }
           
            Cell cell2 = row.getCell(2);
            if(cell2.getStringCellValue().indexOf("SB.No:")!=-1){
                DATA2 = cell2.getStringCellValue().substring(cell2.getStringCellValue().indexOf("SB.No:")+6,cell2.getStringCellValue().indexOf("Date:")-1);
              }
              if(cell2.getStringCellValue().indexOf("Date:")!=-1){
                DATA2A = cell2.getStringCellValue().substring(cell2.getStringCellValue().indexOf("Date:")+5,cell2.getStringCellValue().indexOf("Scroll:")-1);
              }    
              if(cell2.getStringCellValue().indexOf("Scroll:")!=-1){
                DATA2B = cell2.getStringCellValue().substring(cell2.getStringCellValue().indexOf("Scroll:")+7,cell2.getStringCellValue().indexOf("--")-0);
              }    
              
              Cell cell3 = row.getCell(3);              
              if(DATA2==null){
                  if(cell3.getStringCellValue().indexOf("Bill")!=-1){
                      DATA2 = cell3.getStringCellValue().substring(cell3.getStringCellValue().indexOf("Bill")+4,cell3.getStringCellValue().indexOf(":")-1);
                      if(cell3.getStringCellValue().indexOf(":")!=-1){
                          DATA2A = cell3.getStringCellValue().substring(cell3.getStringCellValue().indexOf(":")+2,cell3.getStringCellValue().length());
                          SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yy");
                          SimpleDateFormat formatter1= new SimpleDateFormat("ddMMyyyy");
                          DATA2A=formatter1.format(formatter.parse(DATA2A));
                            
                        }
                  }
              }
            
              Cell cell4 = row.getCell(4);
              if (cell4!=null && cell4.getCellType()==0) 
              {
              DATA3=String.valueOf((int)cell4.getNumericCellValue());
           
              }       
              
              Cell cell6 = row.getCell(6);
              if(cell6.getCellType()==0){
                  DATA4=cell6.getNumericCellValue();
              }              
              else{
                  //System.out.println(cell6.getCellType()+" "+cell6.getStringCellValue().length());
                  if(cell6.getStringCellValue().trim().length()>0){
                      DATA4=Double.parseDouble(cell6.getStringCellValue().replaceAll("[^a-zA-Z0-9.]+",""));
                  }
              }
          }
              if(DATA2!=null){
                  SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
                  String TEMPDT=format.format(DATA1);
                 
                  //System.out.println(++ctr+" "+TEMPDT+" "+DATA2+" "+DATA2A+" "+DATA2B+" "+DATA3+" "+DATA4);
                 // 1=TRDATE, 2 = SB_NO, 3 = SB_DATE, 4 = SCROLL_NO , 5 =REMAKS , 6 =AMT_DR, 7 =AMT_CR 
                  FileList.add(new IOBdbkBean(TEMPDT,DATA2+"",DATA2A,DATA2B,DATA3+"",null,DATA4+"") );
                 
              }
 
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
                        String vdesc=SB_NO.get(i).toString();
                        String vdr=AMT_DR.get(i).toString(); 
                   if (vdesc.length()>0 && vdesc!=null)
                      {    
                        if(vdesc.length()>10)
                        {vdesc=SB_NO.get(i).toString().substring(0,19).trim();
                         vdr="1";
                        }  
                             stat1=conn.prepareStatement("select * from eis_dbkchq_upload where sb_no=? ");
                             stat1.setString(1,vdesc);
                              result1 = stat1.executeQuery();
                             if (result1.next()==false)
                             {       
                                 if(SB_DATE.get(i).toString()!=null && SB_DATE.get(i).toString().length()>2){
                                    stat = conn.prepareStatement("INSERT INTO eis_dbkchq_upload (TR_DATE,SB_NO,AMT_DR,AMT_CR,TDATE,USER_ID,BANK_CODE,BRANCH_CODE,SB_DATE,SCROLL_NO) VALUES (?,?,?,?,sysdate,?,?,?,to_date(?,'ddmmyyyy'),?)  "  );
                                    stat.setString(1,TRDATE.get(i).toString());
                                    stat.setString(2,vdesc);
                                    stat.setString(3,vdr); 
                                    stat.setString(4,AMT_CR.get(i).toString());
                                    stat.setString(5,usrid); 
                                    stat.setString(6,dbkbank);
                                    stat.setString(7,REMAKS.get(i).toString());                                    
                                    if(dbkbank.substring(0,4).equals("CBNP")){
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                        SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMyyyy");
                                        stat.setString(8,formatter1.format(formatter.parse(SB_DATE.get(i).toString())));
                                    }
                                    else{
                                    stat.setString(8,SB_DATE.get(i).toString());
                                    }
                                    stat.setString(9,SCROLL_NO.get(i).toString());                                    
                                    }
                                    else{
                                    stat = conn.prepareStatement("INSERT INTO eis_dbkchq_upload (TR_DATE,SB_NO,AMT_DR,AMT_CR,TDATE,USER_ID,BANK_CODE,BRANCH_CODE,SCROLL_NO) VALUES (?,?,?,?,sysdate,?,?,?,?)  "  );
                                    stat.setString(1,TRDATE.get(i).toString());
                                    stat.setString(2,vdesc);
                                    stat.setString(3,vdr); 
                                    stat.setString(4,AMT_CR.get(i).toString());
                                    stat.setString(5,usrid); 
                                    stat.setString(6,dbkbank);
                                    stat.setString(7,REMAKS.get(i).toString());
                                    stat.setString(8,SCROLL_NO.get(i).toString());
                                    }
                                    result = stat.executeQuery();
                                    
                                     stat1 = conn.prepareStatement("delete from eis_dbkchq_upload where (nvl(amt_dr,0)>0 or nvl(amt_cr,0)=0) "  );
                                     result1 = stat1.executeQuery();
                                    
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
