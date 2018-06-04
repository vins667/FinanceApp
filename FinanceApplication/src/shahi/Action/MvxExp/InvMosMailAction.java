package shahi.Action.MvxExp;
 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*; 
import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import com.opensymphony.xwork2.ActionContext;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.export.oasis.CellStyle;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;

import shahi.Action.PayPis.Beans.LoctBean;
import shahi.Action.PayPis.Beans.ErrorBean;
import shahi.Action.PayPis.Beans.SalSumBean;
import shahi.Action.MailProvider.MailProvider;
import static shahi.Action.PayPis.SalaryRmbFileAction.generateRandom;
import shahi.Action.zipprotect.ZipProtector;

public class InvMosMailAction {
//

    public void InvMosMailAction() {


    String aausrid=null;
    String textFlag="YES";
    HttpServletRequest servletRequest = null;
    HttpServletResponse response;
        
         int falg = 0;
 //      Map session = ActionContext.getContext().getSession();
  //      String LOCATION_CODE = (String) session.get("sessLocationCode");
  //      String usrid = (String) session.get("sessUserId");

  /*       if(usrid==null)
        {
           session.put("sessUserId",aausrid);
           usrid=aausrid;
        }
    */
         String usrid="227350";
        if (usrid == null) {
            //addActionMessage("Session Not Valid !!");
         //   return ERROR;
         }


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
            try {

                     double saveamt=0;

 
///  ----------------------- Text File and Mail
                String mailyn=null;
                if  (textFlag!=null && textFlag.length()>0 )
                {     
                   // String path = servletRequest.getRealPath("/")+"shahiwebpages/MvxExp/file/";
                    String path="/home/tommy/apache-tomcat-7.0.27/webapps/ShahiApplication/shahiwebpages/MvxExp/file/";
                    // String path="d:/zip/";
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
                  stat = conn.prepareStatement("select distinct location from ei_truckout_track where tr_type='PPRQ' and trunc(tdate)>=trunc(sysdate)-1 " );
                  result= stat.executeQuery();
                  if (result.next()==true)
                 {  vloct=result.getString("location");
  //------ Excel Heading -----------------
          
                       String filename=path+"AIRINV-"+vloct+".xls" ;
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
                               rowhead.createCell((short) 0).setCellValue("Inv No  ");
                                sheet.setColumnWidth((short) 0, 3000);
                                rowhead.createCell((short) 1).setCellValue("Inv Date");
                                sheet.setColumnWidth((short) 1, 4000);
                                rowhead.createCell((short) 2).setCellValue("AC Holder");
                                sheet.setColumnWidth((short) 2, 8000);
                                rowhead.createCell((short) 3).setCellValue("Buyer");
                                sheet.setColumnWidth((short) 3, 3000);
                                rowhead.createCell((short) 4).setCellValue("Inv Qnty");
                                sheet.setColumnWidth((short) 4, 3000);
                                rowhead.createCell((short) 5).setCellValue("Cntry");
                                sheet.setColumnWidth((short) 5, 3000);
                                
                               short k=0;
 
 //------ Excel Heading Close----------
  //---- Excel File Start ----------
                       stat1 = conn.prepareStatement("select a.excs_inv_no,a.ac_holder,a.buyer,a.desti_cntry,a.inv_qty,to_char(a.inv_date,'dd/mm/yyyy') inv_date  from ei_endors_mast a,ei_truckout_track b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.location=? and tr_type='PPRQ' and trunc(B.tr_date)>=trunc(sysdate)-1 order by ac_holder");
                       stat1.setString(1,vloct);
                       result1= stat1.executeQuery();        
                       while(result1.next())
                       {  
                                 HSSFRow row=   sheet.createRow(++k);
                                 row.createCell((short) 0).setCellValue(result1.getString("excs_inv_no"));
                                 row.createCell((short) 1).setCellValue(result1.getString("inv_date"));
                                 row.createCell((short) 2).setCellValue(result1.getString("ac_holder"));
                                 row.createCell((short) 3).setCellValue(result1.getString("buyer"));
                                 row.createCell((short) 4).setCellValue(result1.getString("inv_qty"));
                                 row.createCell((short) 5).setCellValue(result1.getString("desti_cntry"));
                                 
                       }  
                         FileOutputStream fileOut =  new FileOutputStream(filename);
                         hwb.write(fileOut);
                         fileOut.close();

                            
                           MailProvider mailProvider = new MailProvider();

                           String frommail1="shahi.it@shahi.co.in";
                           String subjecttitle1="AIR Freight Approval Required  "+vloct;
                           String messagebody1 = "<div style='background-color:#f2f2f2;width:600pt;border-style:solid;border-width:1pt;border-color:blue'>";

                               messagebody1 += "<table border='0' bgcolor='#f2f2f2' width='100%' cellpadding='5'    >";
                               messagebody1+= "<tr><td style='color:#006699;font-weight:bold' colspan='2'>Hi,</td></tr>";
                               messagebody1+= "<tr><td style='color:#006699;font-weight:bold' colspan='2'>Please find enclosed herewith Excel File </td></tr>";
                               messagebody1+= "<tr><td style='color:red; colspan='2'> Please do not reply to this message. Replies to this message are routed to an unmonitored mailbox</td></tr>";
                               messagebody1 += "</table></div>";
  

                               String []attachments=new String[1];
                               attachments[0]=filename;
                             
                               List tomail1 = new ArrayList();
                         
                               stat1 = conn.prepareStatement("SELECT A.USER_ID,B.USER_NAME,B.E_MAIL FROM SEH_WEB_USERS B,PA_AUTH_MAST A WHERE A.USER_ID=B.USER_ID AND PROG_NAME='PPRQ' and a.loCt_code=?  " );
                               stat1.setString(1,vloct);
                               result1 = stat1.executeQuery();
                             while(result1.next())
                              {
                                 tomail1.add(result1.getString("E_MAIL").toString());
                              }
                            String arr1[] =   (String[]) tomail1.toArray(new String[tomail1.size()]);
                            mailProvider.postMail(arr1, subjecttitle1, messagebody1, frommail1,attachments);
                      
                          
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
                    System.out.print("1 file name : InvMosMailAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name :  InvMosMailAction.java" + e);

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
                    System.out.print("File Name :  InvMosMailAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }



        } catch (Exception e) {

            e.printStackTrace();
           // addActionError(e.getMessage());
          //  return INPUT;

        }

        if (falg == 1) {

          // addActionMessage("Records Save(s) !!");
          //  return SUCCESS;
        }

        else {

           // addActionMessage("Records Not Save(s) !!");
           //return ERROR;
        }


    }
 
  
}
