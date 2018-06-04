/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import shahi.Action.MI.GLS850MI;
//import SMA.Action.Common.Ftpfileupload;
import shahi.Action.Common.Ftpfileupload;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import shahi.Action.MailProvider.MailProvider;
//import shahi.Action.database.ConnectionDB2Movex;
import shahi.Action.database.connectiondb2;
import shahi.Action.database.connection;
import shahi.Action.database.connectionShahiHris;
import shahi.Action.database.ConnectionShahiHrisNew;

/**
 *
 * @author Vivek
 */
public class PayrollPmtTxt {

    public static ResourceBundle aResourcBundle = null;

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    public PayrollPmtTxt() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }

    public String UpdateTxt(String USER_ID, String LOCATION, String DIVI, String TMLOCT, String ETYPE, String BATCH_ID, String TMPRID, String VSTATE) throws Exception {
        Connection conn = null;
        Connection connora = null;
        Connection connhris = null;
        PreparedStatement stat = null;
        PreparedStatement authrecord_stat = null;
        PreparedStatement suppl = null;
        PreparedStatement findrecord_stat = null;
        PreparedStatement mailtoStat = null;
        ResultSet authresult = null;
        ResultSet suppl_result = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet stat1_result = null;
        ResultSet mailtoResult = null;
        boolean saveflag = false;
        try {
            conn = new connectiondb2().getConnection();
            connora = new ConnectionShahiHrisNew().getConnection();
            connhris = new connectionShahiHris().getConnection();
            conn.setAutoCommit(false);
            connora.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            return e.getMessage();
        }
        try {
            String filename="";
            filename = "paychq"+VSTATE.trim();
          /*  if (TMLOCT == "SEHYD") {                
                filename = "hpaychq";
            } else {                
                filename = "paychq";
            }*/
            authrecord_stat = connhris.prepareStatement("select UNIT_CODE,AUTH_TYPE,EMP_CODE from hrauthms where emp_code=?");
            authrecord_stat.setString(1, USER_ID);
            authresult = authrecord_stat.executeQuery();
            int k = 0;
            /*String pdivi = "100";
             * String pcomp = "SEHA5";
             * String pcate="W";
             * String pbatch="2";
             * String ppros="09/2012";*/
            String authtype;
            if (authresult.next()) {
                authtype = authresult.getString("AUTH_TYPE");
            }
            String TMDEPTALL="";
	      if(!TMLOCT.equals("ALL")){
		TMDEPTALL+="and  company_code= '"+TMLOCT+"' ";
	     }
	     else{
                TMDEPTALL+=" ";
            }
	     if(!ETYPE.equals("ALL")){
		TMDEPTALL+="and  nvl(cat_code,'S')= '"+ETYPE+"' ";
	     }
	     else{
                TMDEPTALL+=" ";
            }

          //  String path1 = getValue("reportPdfPath") + "PayChq";
            String path1 = "d:/epm/PayChq";
            String real_filename = path1 + "/"+filename+ ".txt";
            File aa = new File(path1 + "/"+filename+ ".txt");
            if (aa.exists()) {
                aa.delete();
            }
            FileOutputStream fos = new FileOutputStream(real_filename);
            PrintWriter pw = new PrintWriter(fos);
            String textfile = "";
            String annxfile = "";
            String pdsuco = null;
            String SupAddress = null;
            String SupAddr1 = null;
            String SupAddr2 = null;
            String SupAddr3 = null;
            String SupAddr4 = null;
            String SupAddr5 = null;
            System.out.println(DIVI+TMLOCT+ETYPE+BATCH_ID+TMPRID+VSTATE);

            suppl = connora.prepareStatement("select 'I1'||rpad(batch_no,9)||lpad(a.pay_id,8)||'1000'||lpad(rtrim(ltrim(to_char(acc_code,'99999'))),5,'0')|| "
                    + " rpad(nvl(REMK,' '),3,' ')||rpad(nvl(PCH,' '),8,' ')||rpad(nvl(b.movex_code,' '),8,' ')||rpad(nvl(b.cost_cent,' '),8,' ')||rpad(nvl(to_char(a.emp_code),' '),8,' ')||rpad(nvl(to_char(batch_no),' '),8,' ')||rpad(nvl(substr(chq_no,10,6),' '),8,' ')||rpad(nvl(REMK,' '),16,' ')|| "
                    + " '0INR 1'||rpad(nvl(REMK,' '),12,' ')||'1'||lpad(rtrim(ltrim(to_char(acc_amt,'99999999999999.99'))),17,' ')||lpad(to_char(pymt_date,'YYYYMMDD'),'8','0')||' 0'||rpad(nvl(acc_data_desc,' '),36,' ') flatfile "
                    + " from AMSNOW.fa_payroll_chq a,AMSNOW.fa_payroll_chq_dtls b where  CONO=111 AND divi=? " +TMDEPTALL+ " AND to_char(pros_date,'MM/YYYY')=? and batch_no=? and PYMT_DATE is not null and fin_upd is null and a.pay_id=b.pay_id and nvl(b.acc_Amt,0)<>0 order by a.pay_id,b.acc_code,acc_data_desc ");
            suppl.setString(1, DIVI);
            //suppl.setString(2, TMLOCT);
            //suppl.setString(3, ETYPE);
            suppl.setString(2, TMPRID);
            suppl.setString(3, BATCH_ID);
            suppl_result = suppl.executeQuery();
            Date todate = new Date();
            SimpleDateFormat forat = new SimpleDateFormat("dd/MM/yyyy");
            String systime = forat.format(todate);
            while (suppl_result.next()) {
                int kk = 0;

                textfile = suppl_result.getString("flatfile").trim();
                //System.out.println(textfile);
                pw.println(textfile);
            }//close while
            pw.close();
            fos.close();
            File a = new File(path1 + "/"+filename+ ".txt");
            a.setReadOnly();

            if (TMPRID != null) {
                stat = connora.prepareStatement("update AMSNOW.fa_payroll_chq set fin_upd=1,fin_date=sysdate,fin_user=? where  CONO=111 AND divi=? " +TMDEPTALL+ " AND to_char(pros_date,'MM/YYYY')=? and batch_no=? and PYMT_DATE is not null  ");
                stat.setString(1, USER_ID);
                stat.setString(2, DIVI);
                //stat.setString(3, TMLOCT);
                //stat.setString(4, ETYPE);
                stat.setString(3, TMPRID);
                stat.setString(4, BATCH_ID);
                k = stat.executeUpdate();
                
            }
            if (k > 0) {

                if (a.exists()) {
                    String status = "0";
                    String minte = null;                    

                  /*  if (TMLOCT == "SEHYD") {
                        minte = "HYD-PAYROLL CHQ";                        
                    } else {
                        minte = "FBD-PAYROLL CHQ";                        
                    }*/
                stat1 = connora.prepareStatement("select minte from amsnow.fa_payroll_map where state=? and mdivi=? ");
                stat1.setString(1, VSTATE);
                stat1.setString(2, DIVI);
                stat1_result = stat1.executeQuery();
                 if (stat1_result.next()) {
                     minte=stat1_result.getString("minte");
                     }   
                    
                    //      System.out.println("FTP Start"+real_filename);
                    Ftpfileupload fileobj = new Ftpfileupload();
                    String connst = getValue("MIIP");
                   // String cuser = getValue("UserNameMI");
                   // String cpass = getValue("PassworMI");
                    String cuser = getValue("UserNameDB2");
                    String cpass = getValue("PasswordDB2");
                       String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/M3BE1512/env/M3BE_15.1_PRD/transfer/GLS850MI/"+filename+".txt");
                  //   String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/m3be/env/PROD/MvxTransfer/" + filename+".txt");
                    if (ftpout.equals("OK")) {
                      //  System.out.println("FTP Ok...");
                        if (minte.trim() != null) {
                            GLS850MI MI = new GLS850MI();
                            MI.connect();
                           // status = MI.DataUpload("111", DIVI, minte, filename+".txt", "/M3BE/env/PROD/MvxTransfer", "1");
                             status = MI.DataUpload("111", DIVI, minte, filename+".txt", "GLS850MI", "1");
                            MI.destroyMI();
                            MI = null;
                        }
                        MailProvider mailProvider = new MailProvider();
                        String[] filenames = new String[1];
                        filenames[0] = path1 + "/"+filename+ ".txt";
                        // mail status

                        String ccAddress = null;
                        String sehuser = null;

                        String subjecttitle = "";
                        String messageBodyText = " ";
                        String frommail = "";
                        String fromname = "";
                        String[] tomail = new String[2];
                        String toname = "";

                        if (LOCATION.equals("FBAD")) {
                            ccAddress = "kuldeep.anandsingh@shahi.co.in";
                            toname = "rameshk.chauhan@shahi.co.in";
                        } else {
                            ccAddress = "kuldeep.anandsingh@shahi.co.in";
                            toname = "nagashayana.talkad@shahi.co.in";
                        }

                        findrecord_stat = connora.prepareStatement("select company_code,cat_code,bank_code,emp_code,acc_data_desc,chq_no, chq_date,chq_amount from AMSNOW.fa_payroll_chq where  CONO=111 AND divi=? " +TMDEPTALL+ " AND to_char(pros_date,'MM/YYYY')=? and batch_no=? order by chq_no");
                        findrecord_stat.setString(1, DIVI);
                        //findrecord_stat.setString(2, TMLOCT);
                        //findrecord_stat.setString(3, ETYPE);
                        findrecord_stat.setString(2, TMPRID);
                        findrecord_stat.setString(3, BATCH_ID);
                        result = findrecord_stat.executeQuery();

                        tomail[0] = toname;
                      //  tomail[1] = "rameshk.chauhan@shahi.co.in";
                        mailtoStat = connhris.prepareStatement("select SHAHI_ID,FULL_NAME from hrempmst where emp_code=?");
                        mailtoStat.setString(1, USER_ID.trim());
                        mailtoResult = mailtoStat.executeQuery();
                        if (mailtoResult.next()) {
                            tomail[1] = mailtoResult.getString("SHAHI_ID");
                            sehuser = mailtoResult.getString("FULL_NAME");
                        }
                        NumberFormat formatter = new DecimalFormat("#0.00");
                        //tomail[1]="kuldeep.anandsingh@shahi.co.in";
                        frommail = "shahi.it@shahi.co.in";
                        fromname = "SHAHI IT";

                        subjecttitle = "Payroll Cheques request (M4) :-" + TMLOCT + ETYPE + TMPRID;
                        messageBodyText = "<html>";
                        messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                        messageBodyText += "<body bgcolor=#95b174>";
                        messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                        messageBodyText += "This is to inform you that below mentioned details cheques has been generated in Movex. " + TMLOCT + "/" + ETYPE + TMPRID;
                        messageBodyText += "</br></br>";

                        messageBodyText += "</font>";
                        messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                        messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Sr No</td><td style='width:70pt'>Location</td><td style='width:20pt'>Category</td><td style='width:20pt'>Bank</td><td style='width:50pt'>Emp.Code</td><td style='width:200pt'>Emp Name</td><td style='width:90pt'>Cheque No.</td><td style='width:90pt'>Cheque.Dt.</td>"
                                + "<td style='width:90pt;text-align:right;'>Amt.</td></tr>";
                        double chqamttot = 0;
                        int counter = 0;
                        while (result.next()) {
                            chqamttot += result.getDouble("chq_amount");
                            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                            messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + ++counter + "</td><td>" + result.getString("company_code") + "</td><td>" + result.getString("cat_code") + "</td><td>" + result.getString("bank_code") + "</td><td>" + result.getString("emp_code") + "</td><td>" + result.getString("acc_data_desc") + "</td><td>" + result.getString("chq_no") + "</td><td>" + dateformat.format(result.getDate("chq_date")) + "</td><td style='text-align:right;'>" + formatter.format(result.getDouble("chq_amount")) + "</td></tr>";
                        }
                        messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td style='text-align:right;text-weight:bold;' colspan='8'>**Total**</td><td style='text-align:right;;text-weight:bold;'>" + formatter.format(chqamttot) + "</td></tr>";
                        messageBodyText += "</table>";
                        messageBodyText += "<table cellpadding='4' width='600'>";
                        messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                        messageBodyText += "<tr style='font-size:14px;'><td>ShahiIT</td></tr>";
                        messageBodyText += "</table>";
                        messageBodyText += "</body>";
                        messageBodyText += "</html>";
                        mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, filenames);
                  
                        conn.commit();
                        connora.commit();
                        
                    }
                    else{
                        return "FTP Not Ok. Batch No: " + BATCH_ID;
                    }
                }

            }
        } catch (SQLException se) {
            System.out.println("1.PaymentPmtTxt: " + se.getMessage());
            se.printStackTrace();
            conn.rollback();
            connora.rollback();
            return se.getMessage();
        } catch (FileNotFoundException fne) {
            System.out.println("2.PaymentPmtTxt: " + fne.getMessage());
            fne.printStackTrace();           
            conn.rollback();
            connora.rollback();
            return fne.getMessage();
        } catch (IOException io) {
            System.out.println("3.PaymentPmtTxt: " + io.getMessage());
                       io.printStackTrace();
            conn.rollback();
            connora.rollback();
            return io.getMessage();
        } catch (AddressException ae) {
            System.out.println("4.PaymentPmtTxt: " + ae.getMessage());
            conn.rollback();
                       ae.printStackTrace();
            connora.rollback();
            return ae.getMessage();
        } catch (MessagingException me) {
            System.out.println("5.PaymentPmtTxt: " + me.getMessage());
                       me.printStackTrace();
            conn.rollback();
            connora.rollback();
            return me.getMessage();
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (connora != null) {
                connora.close();
            }
            if (connhris != null) {
                connhris.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (authrecord_stat != null) {
                authrecord_stat.close();
            }

            if (suppl != null) {
                suppl.close();
            }

            if (findrecord_stat != null) {
                findrecord_stat.close();
            }

            if (mailtoStat != null) {
                mailtoStat.close();
            }

            if (authresult != null) {
                authresult.close();
            }

            if (suppl_result != null) {
                suppl_result.close();
            }

            if (result != null) {
                result.close();
            }

            if (mailtoResult != null) {
                mailtoResult.close();
            }
            if (stat1_result != null) {
                stat1_result.close();
            }
        }
        return "Successfully Done. Batch No: " + BATCH_ID;
    }
}
