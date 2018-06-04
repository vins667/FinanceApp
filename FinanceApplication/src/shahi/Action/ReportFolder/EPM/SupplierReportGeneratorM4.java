package shahi.Action.ReportFolder.EPM;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.naming.InitialContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import shahi.Action.database.connection;
import shahi.Action.database.Connectiondb2New;
import shahi.Action.MailProvider.MailProvider ;

/**

 *

 * @author Vivek

 */

public class SupplierReportGeneratorM4 extends ActionSupport {

    public static ResourceBundle aResourcBundle = null;

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    public SupplierReportGeneratorM4() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }

    @Override
    public String execute() throws SQLException {
        Connection conn = null;
        Connection conn1 = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        ResultSet result = null;
        String party[]=new String[3000];
        String putrno[]=new String[3000];
        String pyear[]=new String[3000];
        String pamt[]=new String[3000];
       // String status[]=new String[3000];
       // String emailid[]=new String[3000];
        String prefno[]=new String[3000]; 
        String pname[]=new String[3000]; 
        String ploct[]=new String[3000];                             
        try {
            conn = new connection().getConnection();
            conn1 = new Connectiondb2New().getConnection();
                       
            conn.setAutoCommit(false);
            conn1.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            int k=0;
            stat = conn.prepareStatement("select * from seplvportal.sup_pay_mast a,cidmas b where PDF_FLG is null and substr(vser,3,1)='6' and a.idsuno=to_char(b.idsuno) order by year,vser,vono");
            result = stat.executeQuery();
            while (result.next()) {
                String division = result.getString("DIVI");
                String year = result.getString("YEAR");
                String vser = result.getString("VSER");
                String vono = result.getString("VONO");
                String path1 = getValue("reportPdfPath");
                //String path1 ="D:\\JasperReport";
                Map parameters = new HashMap();
                InputStream input = new FileInputStream(new File(path1 + "Bank_annexUploadM4.jrxml"));
                JasperDesign design = JRXmlLoader.load(input);
                JasperReport report = JasperCompileManager.compileReport(design);

                InputStream input1 = new FileInputStream(new File(path1 + "PymtAnnex_sub1M4.jrxml"));
                JasperDesign design1 = JRXmlLoader.load(input1);
                JasperReport report1 = JasperCompileManager.compileReport(design1);

                InputStream input2 = new FileInputStream(new File(path1 + "PymtAnnex_sub2M4.jrxml"));
                JasperDesign design2 = JRXmlLoader.load(input2);
                JasperReport report2 = JasperCompileManager.compileReport(design2);
                parameters.put("SUBREPORT_DIR",path1);
                parameters.put("ReportTitle", "SHAHI EXPORTS PVT. LTD. IP-1,SEC-28(HFD) MAIN MATHURA ROAD, FARIDABAD-121003 HARYNA,INDIA");
                parameters.put("pdivi", division);
                parameters.put("pyear", year);
                parameters.put("pvser", vser);
                parameters.put("pvono", vono);
                parameters.put("PymtAnnex_sub1M4", report1);
                parameters.put("PymtAnnex_sub2M4", report2);
                String mlocation = "";
                /*if (unit.equals("FBAD")) {
                mlocation = "100";
                } else if (unit.equals("7")) {
                mlocation = "200";
                } else {
                mlocation = "210";
                }*/

                if (vser.equals("106") || vser.equals("126") || vser.equals("146") || vser.equals("166") || vser.equals("206") || vser.equals("186")) {
                    mlocation = "100";
                } else if (vser.equals("406") || vser.equals("446")) {
                    mlocation = "200";
                } else if (vser.equals("426")) {
                    mlocation = "210";
                } else {
                    mlocation = "999";
                }


                InitialContext initialContext = new InitialContext();
                JasperPrint print = JasperFillManager.fillReport(report, parameters, conn1);
            //    path1="D:\\";
                OutputStream output = new FileOutputStream(new File(path1 + "payments/" + mlocation + "/" + year.trim() + "/" + vser.trim() + vono.trim() + ".pdf"));
                JasperExportManager.exportReportToPdfStream(print, output);
                output.close();
              //  shahiportal.sup_pay_mast removed on 16/06/17
                stat1 = conn.prepareStatement("Update seplvportal.sup_pay_mast set PDF_FLG='Y' where  DIVI=?  and YEAR=? and VSER=? and VONO=?");
                stat1.setString(1, division);
                stat1.setString(2, year);
                stat1.setString(3, vser);
                stat1.setString(4, vono);
                stat1.executeUpdate();
                
                party[k]=result.getString("idsuno");
                putrno[k]=result.getString("chq_utr_no");
                pyear[k]=result.getString("year");
                pamt[k]=result.getString("amount");
                ploct[k]=result.getString("location");
                pname[k]=result.getString("idsunm");
                prefno[k]=result.getString("vser")+result.getString("vono");
                k++;
            }
            
             if(k>0)
             {


                String fileName=null;
                String ffname=null;
                String fromAddress=null;
                String toAddress=null;
        
                String messageBodyText = "<html>";
                messageBodyText  += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                messageBodyText  += "<body bgcolor=#95b174>";
                messageBodyText  += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                messageBodyText  +="Dear Sir/Madam,";
                messageBodyText +="</br></br>";
                messageBodyText  +="Below mentioned are the status of Suppliers payment upload on dated ";
                messageBodyText  += "</font>";

                messageBodyText +="</br></br>";
                messageBodyText  +="<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                messageBodyText  +="<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td>Sl.No.</td><td style='width:70pt'>Supplier Code</td><td style='width:350pt'>Supplier Name</td><td style='width:200pt'>UTR No.</td><td style='width:70pt'>Amount</td><td style='width:70pt'>Vch.No.</td><td>Year</td></tr>";
                int mm=1;
                for(int i=0; i<k; i++)
                {
                    messageBodyText  +="<tr style='font-family:Arial;font-size:11px;background-color:rgb(245,245,243);'><td>"+mm+"</td><td>"+party[i]+"</td><td>"+pname[i]+"</td><td>"+putrno[i]+"</td><td>"+pamt[i]+"</td><td>"+prefno[i]+"</td><td>"+pyear[i]+"</td></tr>";
                    mm++;
                }
                messageBodyText  +="</table>";
                messageBodyText  += "</body>";
                messageBodyText  += "</html>";

                String emailFromAddress ="ap.fbad@shahi.co.in";
                String emailSubjectTxt="Supplier payments PDF Upload in Portal (seplvportal)- M4.";
                String[] emailList ={"rashmi.batheja@shahi.co.in","kavita.mehndiratta@shahi.co.in","nikhilkumar.agarwal@shahi.co.in","vilas.sadarjoshi@shahi.co.in","lakshminarayana.barki@shahi.co.in","guru.prasad@shahi.co.in","kuldeep.anandsingh@shahi.co.in"};
               //  String[] emailList = {toAddress};
                try{
                      MailProvider mail=new MailProvider();
                      mail.postMail( emailList, emailSubjectTxt, messageBodyText, emailFromAddress,null);
                   }catch(Exception ee)
                     {
                          System.out.println(ee.toString());
                     }

                }
               
            conn.commit();
            conn1.commit();
            addActionError("Successfully Uploaded");
        } catch (Exception e) {
            conn.rollback();
            conn1.rollback();
            addActionError("Error:" + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (conn1 != null) {
                conn1.close();
            }
        }
        return SUCCESS;
    }
}
