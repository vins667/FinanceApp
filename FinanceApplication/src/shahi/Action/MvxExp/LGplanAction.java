package shahi.Action.MvxExp;
 

import shahi.Action.database.connection;
import java.sql.*;
import java.util.*; 
import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.export.oasis.CellStyle;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import shahi.Action.MailProvider.MailProvider;


public class LGplanAction {
//

    public void LGplanAction() {


    String aausrid=null;
    String textFlag="YES";
    HttpServletRequest servletRequest = null;
    HttpServletResponse response;
        
         int falg = 0;
 
        try {

            Connection conn = null;

            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch


            PreparedStatement stat1 = null;
            PreparedStatement stat = null;
            ResultSet result1 = null;
            ResultSet result = null;
            String txtstatus=null;
            try
            {

                     double saveamt=0;

 
///  ----------------------- Text File and Mail
                String mailyn=null;
                if  (textFlag!=null && textFlag.length()>0 )
                {    
                   
                   // String path = servletRequest.getRealPath("/")+"shahiwebpages/MvxExp/TextFile/";
                    String path="/home/tommy/apache-tomcat-7.0.27/webapps/ShahiApplication/shahiwebpages/MvxExp/file/";
                   //  String path="d:/zip/";
                         File directory = new File(path);
                         File[] files = directory.listFiles();
                        for (File file : files) 
                        {  
                           if (!file.delete())
                           {  
                               // Failed to delete file
                               System.out.println("Failed to delete "+file);
                           }
                        } 
                   
                  String vloct=null;
                  stat = conn.prepareStatement(" select distinct location  from lg_to_plan a,ei_endors_mast b where a.excs_inv_no=b.excs_inv_no and  a.fwd_date is not null and b.tto_date is null and  trunc(sysdate)-trunc(ex_fy_date)>decode(to_char(sysdate,'Day'),'Sunday',4,next_day(trunc(sysdate,'mm')-1,'Saturday')+7,5,3) order by 1 " );
                  result= stat.executeQuery();
                  if (result.next()==true)
                 {      
  //------ Excel Heading -----------------
                       vloct=result.getString("location");
                       String filename=path+"LGPLAN-"+vloct+".xls" ;
                       HSSFWorkbook hwb=new HSSFWorkbook();
                       HSSFSheet sheet =  hwb.createSheet("new sheet");

                       CellStyle style=null;
                       HSSFDataFormat format = hwb.createDataFormat();
                       HSSFCell c = null; 
                       format = hwb.createDataFormat();
                       HSSFCellStyle  style1 = hwb.createCellStyle();
                       style1.setDataFormat(format.getFormat("###0.00"));

                       HSSFCellStyle StyleDate = hwb.createCellStyle();
                       StyleDate.setDataFormat(format.getFormat("dd/mm/yyyy"));
                    //   sheet.autoSizeColumn((short)5);
                          //sheet.setColumnWidth(1, 100);
                       HSSFRow rowhead=null;
                        rowhead=   sheet.createRow((short)0);
                               rowhead.createCell((short) 0).setCellValue("AC Holder");
                                sheet.setColumnWidth((short) 0, 8000);
                                rowhead.createCell((short) 1).setCellValue("Buyer");
                                sheet.setColumnWidth((short) 1, 3000);
                                rowhead.createCell((short) 2).setCellValue("Invoice No.");
                                sheet.setColumnWidth((short) 2, 3000);
                                rowhead.createCell((short) 3).setCellValue("Inv Qty");
                                sheet.setColumnWidth((short) 3, 3000);
                                rowhead.createCell((short) 4).setCellValue("Fwd Date");
                                sheet.setColumnWidth((short) 5, 3000);
                                rowhead.createCell((short) 5).setCellValue("Ex-Fy Date");
                                sheet.setColumnWidth((short) 5, 3000);
                                rowhead.createCell((short) 6).setCellValue("Cutoff Date");
                                sheet.setColumnWidth((short) 6, 3000);
                                
                                 
                               short k=0;
 
 //------ Excel Heading Close----------
  //---- Excel File Start ---------- 
                              
                                List tomail1 = new ArrayList();
                       stat1 = conn.prepareStatement("select a.user_id, ac_holder,buyer,a.excs_inv_no,a.fwd_date,ex_fy_date,del_date cutoff_date,inv_qty,trunc(sysdate)-trunc(fwd_date) delay,c.e_mail from lg_to_plan a,ei_endors_mast b,seh_web_users c  where a.excs_inv_no=b.excs_inv_no "+
                                                     " and  a.fwd_date is not null and b.tto_date is null and a.user_id=c.user_id and trunc(sysdate)-trunc(ex_fy_date)>decode(to_char(sysdate,'Day'),'Sunday',4,next_day(trunc(sysdate,'mm')-1,'Saturday')+7,5,3)  AND B.LOCATION=? order by 2,3 " );
            
                       stat1.setString(1,vloct);
                       result1= stat1.executeQuery();        
                       while(result1.next())
                       {   
                                 HSSFRow row=   sheet.createRow(++k);
                                 row.createCell((short) 0).setCellValue(result1.getString("ac_holder"));
                                 row.createCell((short) 1).setCellValue(result1.getString("buyer"));
                                 row.createCell((short) 2).setCellValue(result1.getString("excs_inv_no"));
                                 row.createCell((short) 3).setCellValue(result1.getString("inv_qty"));
                                 row.createCell((short) 4).setCellValue(result1.getString("fwd_date"));
                                 row.createCell((short) 5).setCellValue(result1.getString("ex_fy_date"));
                                 row.createCell((short) 6).setCellValue(result1.getString("cutoff_date"));
                                 tomail1.add(result1.getString("E_MAIL").toString());
                       }  
                         FileOutputStream fileOut =  new FileOutputStream(filename);
                         hwb.write(fileOut);
                         fileOut.close();

                            
                           MailProvider mailProvider = new MailProvider();

                           String frommail1="shahi.it@shahi.co.in";
                           String subjecttitle1="Expired Despatch Requests "+vloct;
                           String messagebody1 = "<div style='background-color:#f2f2f2;width:600pt;border-style:solid;border-width:1pt;border-color:blue'>";

                               messagebody1 += "<table border='0' bgcolor='#f2f2f2' width='100%' cellpadding='5'    >";
                               messagebody1+= "<tr><td style='color:#006699;font-weight:bold' colspan='2'>Hi,</td></tr>";
                               messagebody1+= "<tr><td style='color:#006699;font-weight:bold' colspan='2'>The despatch requests in the attached grid have been turned back due to delay and you may please recheck the revised ex-factory date and forward a new cargo despatch request to the logistics-executive.</td></tr>";
                               messagebody1+= "<tr><td style='color:red; colspan='2'> Please do not reply to this message. Replies to this message are routed to an unmonitored mailbox</td></tr>";
                               messagebody1 += "</table></div>";
  

                               String []attachments=new String[1];
                               attachments[0]=filename;
                             
                               stat1 = conn.prepareStatement("SELECT A.USER_ID,B.USER_NAME,B.E_MAIL FROM SEH_WEB_USERS B,PA_AUTH_MAST A WHERE A.USER_ID=B.USER_ID AND PROG_NAME='TOPLANMAIL' and a.loCt_code=?  " );
                               stat1.setString(1,vloct);
                               result1 = stat1.executeQuery();
                             while(result1.next())
                              { 
                                 tomail1.add(result1.getString("E_MAIL").toString());
                              }
                             
                             
                             
                            String arr1[] =   (String[]) tomail1.toArray(new String[tomail1.size()]);
                            mailProvider.postMail(arr1, subjecttitle1, messagebody1, frommail1,attachments);
                            
                            stat1 = conn.prepareStatement("update lg_to_plan a set fwd_date=null,ex_fy_date=null where a.fwd_date is not null and  trunc(sysdate)-trunc(ex_fy_date)>decode(to_char(sysdate,'Day'),'Sunday',4,next_day(trunc(sysdate,'mm')-1,'Saturday')+7,5,3) and excs_inv_no in (select excs_inv_no from ei_endors_mast where tto_date is null) " );
                            stat1.executeUpdate();     
                             
                 }
              }
            }
              catch (Exception e) {
                  System.out.println(e.toString());
                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();
                    
                } catch (Exception ee) {
                    System.out.print("1 file name : LGplanAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name :  LGplanAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                     if (result != null) {
                        result.close();
                    }

 
                    if (stat1 != null) {
                        stat1.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }

                    result1 = null;
                    stat1 = null;
                    conn = null;

                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name :  LGplanAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();

           // addActionError(e.getMessage());

          //  return INPUT;
 
        }

        

    }
 
    
}
