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

public class AwbDraftReqAction { 
//  

    public void AwbDraftReqAction() {


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
                  stat = conn.prepareStatement("select distinct location from ei_endors_mast where location='200' and trunc(sysdate)-trunc(tto_date)>4 " );
                  result= stat.executeQuery();
                  if (result.next()==true)
                 {  vloct=result.getString("location");
  //------ Excel Heading -----------------
          
                       String filename=path+"DAPR-"+vloct+".xls" ;
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
                               rowhead.createCell((short) 0).setCellValue("Account Holder  ");
                                sheet.setColumnWidth((short) 0, 6000);
                                rowhead.createCell((short) 1).setCellValue("Port of Loading");
                                sheet.setColumnWidth((short) 1, 4000);
                                rowhead.createCell((short) 2).setCellValue("Forwarder");
                                sheet.setColumnWidth((short) 2, 12000);
                                rowhead.createCell((short) 3).setCellValue("Buyer");
                                sheet.setColumnWidth((short) 3, 3000);
                                rowhead.createCell((short) 4).setCellValue("ETD Date");
                                sheet.setColumnWidth((short) 4, 3500);
                                rowhead.createCell((short) 5).setCellValue("Destination");
                                sheet.setColumnWidth((short) 5, 3000);
                                rowhead.createCell((short) 6).setCellValue("Invoice No");
                                sheet.setColumnWidth((short) 6, 3000);
                                rowhead.createCell((short) 7).setCellValue("Ship Mode");
                                sheet.setColumnWidth((short) 7, 2000);
                                rowhead.createCell((short) 8).setCellValue(" Clearance Port");
                                sheet.setColumnWidth((short) 8, 3000);
                                rowhead.createCell((short) 9).setCellValue(" Discharge");
                                sheet.setColumnWidth((short) 9, 8000);
                                 rowhead.createCell((short) 10).setCellValue(" Inv Qty");
                                sheet.setColumnWidth((short) 10, 3000);
                                rowhead.createCell((short) 11).setCellValue("Crncy ");
                                sheet.setColumnWidth((short) 11, 2000);
                                rowhead.createCell((short) 12).setCellValue("Fob FC");
                                sheet.setColumnWidth((short) 12, 3000);
                                rowhead.createCell((short) 13).setCellValue(" Inr Amt");
                                sheet.setColumnWidth((short) 13, 3000);
                                 rowhead.createCell((short) 14).setCellValue("Days From TTO");
                                sheet.setColumnWidth((short) 14, 3000);
                               short k=0;
 
 //------ Excel Heading Close----------
  //---- Excel File Start ----------
                       stat1 = conn.prepareStatement("select a.ac_holder,a.loading_port,E.IDSUNM FWD_NAME,a.buyer,to_char(a.etd_date,'dd/mm/yyyy') etd_date,a.loading clr_port,a.desti_cntry,a.excs_inv_no,a.mode_of_ship,cttx40 discharge,inv_qty,a.crncy_code,sum(qty_endors*price_fc+nvl(price_misc,0)) fob,sum(qty_endors*price_fc+nvl(price_misc,0))*exp_rate fobinr,trunc(sysdate)-trunc(tto_date) days " +
                                                     " from ei_endors_mast a,ei_endors_dtls b,ei_exchange_rate_mast c,csytab_view d,production.CIDMAS_VIEW E where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.crncy_code=c.currency and a.inv_date between begin_date and end_date " +
                                                     " and  d.ctcono=111 and ctstco=rpad('SDST',10,' ') AND ctstky=rpad(discharge,10,' ') AND e.idcono=111 and idsuno=rpad(fwd_code,10,' ') and (a.year,a.company,a.inv_no) not in (select year,company,inv_no from ei_truckout_track where tr_type='DAPR') "+
                                                     " and a.location=? and trunc(sysdate)-trunc(tto_date)>4 and tto_date>='22-jul-2013' group by a.ac_holder,a.loading_port,a.loading,IDSUNM,a.buyer,to_char(a.etd_date,'dd/mm/yyyy'),a.desti_cntry,a.excs_inv_no,a.mode_of_ship,d.cttx40,inv_qty,a.crncy_code,exp_rate,trunc(sysdate)-trunc(tto_date) order by 1,2,3,4,5");
                       stat1.setString(1,vloct);
                       result1= stat1.executeQuery();        
                       while(result1.next())
                       {  
                                 HSSFRow row=   sheet.createRow(++k);
                                 row.createCell((short) 0).setCellValue(result1.getString("ac_holder"));
                                 row.createCell((short) 1).setCellValue(result1.getString("loading_port"));
                                 row.createCell((short) 2).setCellValue(result1.getString("FWD_NAME"));
                                 row.createCell((short) 3).setCellValue(result1.getString("buyer"));
                                 row.createCell((short) 4).setCellValue(result1.getString("etd_date"));
                                 row.createCell((short) 5).setCellValue(result1.getString("desti_cntry"));
                                 row.createCell((short) 6).setCellValue(result1.getString("excs_inv_no"));
                                 row.createCell((short) 7).setCellValue(result1.getString("mode_of_ship"));
                                 row.createCell((short) 8).setCellValue(result1.getString("clr_port"));
                                 row.createCell((short) 9).setCellValue(result1.getString("discharge"));
                                 row.createCell((short) 10).setCellValue(result1.getInt("inv_qty"));
                                 row.createCell((short) 11).setCellValue(result1.getString("crncy_code"));
                                 row.createCell((short) 12).setCellValue(result1.getInt("fob"));
                                 row.createCell((short) 13).setCellValue(result1.getInt("fobinr"));
                                 row.createCell((short) 14).setCellValue(result1.getInt("days"));
                        
                       }  
                         FileOutputStream fileOut =  new FileOutputStream(filename);
                         hwb.write(fileOut);
                         fileOut.close();

                            
                           MailProvider mailProvider = new MailProvider();

                           String frommail1="shahi.it@shahi.co.in";
                           String subjecttitle1="Awb/Fcr Draft Approval Pending - (TTO>4)  ";
                           String messagebody1 = "<div style='background-color:#f2f2f2;width:600pt;border-style:solid;border-width:1pt;border-color:blue'>";

                               messagebody1 += "<table border='0' bgcolor='#f2f2f2' width='100%' cellpadding='5'    >";
                               messagebody1+= "<tr><td style='color:#006699;font-weight:bold' colspan='2'>Hi,</td></tr>";
                               messagebody1+= "<tr><td style='color:#006699;font-weight:bold' colspan='2'>Please find enclosed herewith Excel File </td></tr>";
                               messagebody1+= "<tr><td style='color:red; colspan='2'> Please do not reply to this message. Replies to this message are routed to an unmonitored mailbox</td></tr>";
                               messagebody1 += "</table></div>";
   

                               String []attachments=new String[1];
                               attachments[0]=filename;
                             
                               List tomail1 = new ArrayList();
                               stat1 = conn.prepareStatement("SELECT A.USER_ID,B.USER_NAME,B.E_MAIL FROM SEH_WEB_USERS B,PA_AUTH_MAST A WHERE  A.USER_ID=B.USER_ID AND PROG_NAME='PPRQ' and a.loct_code=? " +
                                                             " union SELECT distinct A.empcode user_id,B.USER_NAME,B.E_MAIL FROM ei_grup_type_dtls a,SEH_WEB_USERS B,ei_endors_mast C WHERE  A.empcode=B.USER_ID and c.location=? and c.ac_holder=substr(a.type_desc,1,20) and " +
                                                             " trunc(sysdate)-trunc(tto_date)>4 and tto_date>='22-jul-2013' and (c.year,c.company,c.inv_no) not in (select year,company,inv_no from ei_truckout_track where tr_type='DAPR')" );
                               stat1.setString(1,vloct);
                               stat1.setString(2,vloct);
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
                    System.out.print("1 file name : AwbDraftReqAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name :  AwbDraftReqAction.java" + e);

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
                    System.out.print("File Name :  AwbDraftReqAction.java Exception in finally block");
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
