/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.cashbill;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import shahi.Action.Common.Ftpfileupload;
import shahi.Action.MI.GLS850MI;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connectionShahiHris;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author Vivek
 */
public class CashBillTxtGenerator {
    public static ResourceBundle aResourcBundle = null;
    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }
    public CashBillTxtGenerator() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }
    public String txtGenerator(List<String> srNo,String userId){
        Connection conn = null;
        Connection connhris = null;
        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        PreparedStatement stat1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement stat2 = null;
        ResultSet resultSet2 = null;        
        PreparedStatement stat3 = null;
        ResultSet resultSet3 = null;
        try{
            String SEQ = "";
            String PAY_TYPE="";
            conn = new ConnectionSeplWeb().getConnection();
            connhris = new connectionShahiHris().getConnection();
            conndb2 = new connectiondb2().getConnection();
            String FILE_NAME=null;
            String MINTE=null;
            String DIVI = null;
            String REALFILENAME = "/home/tommy/apache-tomcat-8.0.24/webapps/ShahiApplication/shahiwebpages/cashbill/txt/";//
            BufferedWriter bw = null;
            stat = conn.prepareStatement("SELECT M4_BILL_GLS_MASTER_SQ.NEXTVAL FROM DUAL");
            resultSet = stat.executeQuery();
            if(resultSet.next()){
                SEQ = resultSet.getString(1);
            }
            if(resultSet!=null) resultSet.close();
            if(stat!=null) stat.close();
            for (int i = 0; i < srNo.size(); i++) {
                stat = conn.prepareStatement("SELECT SL_NO, DEPT_SL_NO, BILL_NO, TO_CHAR(BILL_DATE,'yyyyMMdd') BILL_DATE, SUPPLIER_CODE, BILL_AMOUNT, TDATE, USER_ID, GROSS_AMOUNT, BILL_WHLO, BILL_YEAR, FORWARD_USER, FORWARD_DATE, ACCOUNT_USER, TO_CHAR(ACCOUNT_DATE,'yyyyMMdd') ACCOUNT_DATE,TO_CHAR(VCH_DATE,'yyyyMMdd') VCH_DATE, REMARKS, REPORT_NO, REC_ACC_USER, REC_ACC_DATE, REVERSE_SRVTAX, REVERSE_SRVTAX_RATE, REJ_USER, REJ_DATE, OLD_REPORT_NO, SRVTAX_GL_CODE, REVERSE_SRVTAX_CODE, NON_SRVTAX_AMOUNT, SASUNM, SAADR1, SAADR2, SAADR3, SAADR4, SATOWN, SAECAR, SAPONO, SACSCD, HSN_CODE, NON_GST_AMOUNT, SAADID, SASTDT, SACONM,PAY_TYPE FROM M4_BILL_MASTER WHERE SL_NO=?");
                stat.setString(1, srNo.get(i));
                resultSet = stat.executeQuery();
                while(resultSet.next()){
                    if(FILE_NAME==null){
                        PAY_TYPE = resultSet.getString("PAY_TYPE");
                        FILE_NAME=resultSet.getString("PAY_TYPE")+resultSet.getString("BILL_WHLO").trim()+".txt";
                        MINTE=resultSet.getString("PAY_TYPE")+"-BILL-"+resultSet.getString("BILL_WHLO").trim();
                        REALFILENAME+=FILE_NAME;
                        bw=new BufferedWriter(new FileWriter(REALFILENAME));
                    }
                    bw.write("I1");
                    bw.write(StringUtils.leftPad(SEQ, 9," "));
                    bw.write(StringUtils.rightPad(resultSet.getString("SL_NO"), 8," "));
                    String DIVITEMP = "";
                    String GEOCTEMP = "";
                    stat1 = conn.prepareStatement("SELECT M4DIVI,M4GEOC from M4_WHLO_MASTER WHERE M4WHLO=?");
                    stat1.setString(1, resultSet.getString("BILL_WHLO"));
                    resultSet1 = stat1.executeQuery();
                    if(resultSet1.next()){
                        if(DIVI==null){
                            DIVI = resultSet1.getString("M4DIVI");
                        }
                        DIVITEMP = resultSet1.getString("M4DIVI");   
                        GEOCTEMP = resultSet1.getString("M4GEOC");
                    }
                    if(resultSet1!=null) resultSet1.close();
                    if(stat1!=null) stat1.close();
                    bw.write(DIVITEMP);
                    bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10," "));
                    bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10," "));
                    bw.write(StringUtils.rightPad(resultSet.getString("BILL_NO"), 24," "));
                    bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8," "));
                    bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8," "));
                    bw.write(StringUtils.leftPad("-"+resultSet.getString("BILL_AMOUNT"), 17," "));
                    bw.write("INR");
                    bw.write(StringUtils.leftPad("1",3," "));
                    bw.write(StringUtils.leftPad("1",13," "));
                    bw.write(StringUtils.rightPad(resultSet.getString("VCH_DATE"), 8," "));
                    bw.write(StringUtils.rightPad("AUDITSHMG",10," "));
                    bw.write(StringUtils.rightPad("I",94," "));
                    bw.write(StringUtils.leftPad("0.00",17," "));
                    bw.write(StringUtils.rightPad("",8," "));
                    bw.write(StringUtils.leftPad("1",15," "));
                    bw.write(StringUtils.rightPad("CHF",3," "));
                    bw.write(StringUtils.rightPad(resultSet.getString("REPORT_NO"),8," "));
                    bw.write(StringUtils.rightPad("",45," "));
                    bw.write("\n");
                    stat1 = conn.prepareStatement("SELECT SL_NO, BILL_SL_NO, CC_CODE, TYPE_SL_NO, SUB_TYPE_SL_NO, PRODUCT_SL_NO, PRODUCT_AMOUNT, TDATE, USER_ID, PCH, BILL_DATE1, BILL_DATE2, REMARKS, TAXABLE, NON_GST_AMOUNT, HSN_CODE, NVL(PARTNER_DESC,' ') PARTNER_DESC, NVL(VOUCHER_DESC,' ') VOUCHER_DESC,NVL(PRODUCT_QUANTITY,'0') PRODUCT_QUANTITY,NVL(UOM,' ') UOM FROM M4_BILL_DETAIL WHERE BILL_SL_NO=?");
                    stat1.setString(1, resultSet.getString("SL_NO"));
                    resultSet1 = stat1.executeQuery();
                    while(resultSet1.next()){
                        bw.write("I2");
                        bw.write(StringUtils.leftPad(SEQ, 9, " "));
                        bw.write(StringUtils.rightPad(resultSet.getString("SL_NO"), 8, " "));
                        bw.write(DIVITEMP);
                        bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10, " "));
                        bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10, " "));
                        bw.write(StringUtils.rightPad(resultSet.getString("BILL_NO"), 24, " "));
                        bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8, " "));
                        bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8, " "));
                        bw.write(StringUtils.leftPad((resultSet1.getDouble("PRODUCT_AMOUNT")+resultSet1.getDouble("NON_GST_AMOUNT"))+"",17," "));
                        bw.write("INR");
                        bw.write(StringUtils.leftPad("1",3," "));
                        bw.write(StringUtils.leftPad("1",13," "));
                        bw.write(StringUtils.rightPad(resultSet.getString("VCH_DATE"), 8," "));
                        bw.write(StringUtils.rightPad("AUDITSHMG",10," "));
                        bw.write(StringUtils.rightPad("",20," "));
                        
                        stat2 = conn.prepareStatement("SELECT SUB_TYPE_CODE FROM M4_BILL_SUB_TYPE_MASTER WHERE SL_NO=?");
                        stat2.setString(1, resultSet1.getString("SUB_TYPE_SL_NO"));
                        resultSet2 = stat2.executeQuery();
                        if(resultSet2.next()){
                            bw.write(StringUtils.rightPad(resultSet2.getString("SUB_TYPE_CODE"),8," "));
                        }
                        if(resultSet2!=null) resultSet2.close();
                        if(stat2!=null) stat2.close();
                        
                        bw.write(StringUtils.rightPad(resultSet1.getString("PCH"),8," "));
                        
                        stat2 = connhris.prepareStatement("SELECT EAAITM FROM SEPLWEB.M4_CC_MASTER@IBM.WORLD@IBM A,PRODBI.FCHACC B WHERE EACONO=111 AND A.CC_CODE=B.EAAITM AND EAAITP=3 AND DEPT_SL_NO='93' AND EAAT12=0 AND SL_NO=?");
                        stat2.setString(1, resultSet1.getString("CC_CODE"));
                        resultSet2 = stat2.executeQuery();
                        if(resultSet2.next()){
                            bw.write(StringUtils.rightPad(resultSet2.getString("EAAITM"),8," "));
                        }
                        if(resultSet2!=null) resultSet2.close();
                        if(stat2!=null) stat2.close();
                        bw.write(StringUtils.rightPad(resultSet1.getString("PARTNER_DESC"), 8," "));
                        bw.write(StringUtils.rightPad(resultSet1.getString("VOUCHER_DESC"), 40," "));
                        bw.write(StringUtils.leftPad("0.00",19," "));
                        
                        stat2 = conn.prepareStatement("SELECT PRODUCT_CODE FROM M4_BILL_PRODUCT_MASTER WHERE SL_NO=?");
                        stat2.setString(1, resultSet1.getString("PRODUCT_SL_NO"));
                        resultSet2 = stat2.executeQuery();
                        if(resultSet2.next()){
                            bw.write(StringUtils.rightPad(resultSet2.getString("PRODUCT_CODE"),8," "));
                        }
                        if(resultSet2!=null) resultSet2.close();
                        if(stat2!=null) stat2.close();                        
                        bw.write(StringUtils.leftPad(resultSet1.getString("PRODUCT_QUANTITY"),15," "));
                        bw.write(StringUtils.rightPad("CHF",3," "));
                        bw.write(StringUtils.rightPad(resultSet.getString("REPORT_NO"),8," "));
                        bw.write(StringUtils.rightPad("",45," "));
                        bw.write(StringUtils.rightPad(resultSet1.getString("UOM"),3," "));
                        bw.write("\n");
                        stat2 = conn.prepareStatement("SELECT SL_NO, COST_ELEMENT, AMT, FORM_TYPE, TDATE, USER_ID, BILL_SL_NO, FORM_WHLO, GST_PER, TAX_CODE, LINE_SL_NO"
                                +" FROM M4_BILL_BREAKUP_DETAIL WHERE BILL_SL_NO=? AND LINE_SL_NO=?");
                        stat2.setString(1, resultSet1.getString("BILL_SL_NO"));
                        stat2.setString(2, resultSet1.getString("SL_NO"));
                        resultSet2 = stat2.executeQuery();
                        while(resultSet2.next()){
                            bw.write("I2");
                            bw.write(StringUtils.leftPad(SEQ, 9, " "));
                            bw.write(StringUtils.rightPad(resultSet.getString("SL_NO"), 8, " "));
                            bw.write(DIVITEMP);
                            bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10, " "));
                            bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10, " "));
                            bw.write(StringUtils.rightPad(resultSet.getString("BILL_NO"), 24, " "));
                            bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8, " "));
                            bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8, " "));
                            bw.write(StringUtils.leftPad(resultSet2.getDouble("AMT") + "", 17, " "));
                            bw.write("INR");
                            bw.write(StringUtils.leftPad("1", 3, " "));
                            bw.write(StringUtils.leftPad("1", 13, " "));
                            bw.write(StringUtils.rightPad(resultSet.getString("VCH_DATE"), 8, " "));
                            bw.write(StringUtils.rightPad("AUDITSHMG", 10, " "));
                            bw.write(StringUtils.rightPad("S"+GEOCTEMP, 20, " "));
                            
                            bw.write(StringUtils.rightPad(resultSet2.getString("COST_ELEMENT"),8," "));                            

                            bw.write(StringUtils.rightPad(resultSet1.getString("PCH"),8," "));

                            stat3 = connhris.prepareStatement("SELECT EAAITM FROM SEPLWEB.M4_CC_MASTER@IBM.WORLD@IBM A,PRODBI.FCHACC B WHERE EACONO=111 AND A.CC_CODE=B.EAAITM AND EAAITP=3 AND DEPT_SL_NO='93' AND EAAT12=0 AND SL_NO=?");
                            stat3.setString(1, resultSet1.getString("CC_CODE"));
                            resultSet3 = stat3.executeQuery();
                            if(resultSet3.next()){
                                bw.write(StringUtils.rightPad(resultSet3.getString("EAAITM"),8," "));
                            }
                            if(resultSet3!=null) resultSet3.close();
                            if(stat3!=null) stat3.close();
                            bw.write(StringUtils.rightPad(resultSet1.getString("PARTNER_DESC"), 8," "));
                            bw.write(StringUtils.rightPad(resultSet1.getString("VOUCHER_DESC"), 40," "));
                            bw.write(StringUtils.leftPad("0.00",19," "));

                            stat3 = conn.prepareStatement("SELECT PRODUCT_CODE FROM M4_BILL_PRODUCT_MASTER WHERE SL_NO=?");
                            stat3.setString(1, resultSet1.getString("PRODUCT_SL_NO"));
                            resultSet3 = stat3.executeQuery();
                            if(resultSet3.next()){
                                bw.write(StringUtils.rightPad(resultSet3.getString("PRODUCT_CODE"),8," "));
                            }
                            if(resultSet3!=null) resultSet3.close();
                            if(stat3!=null) stat3.close();                        
                            bw.write(StringUtils.leftPad(resultSet1.getString("PRODUCT_QUANTITY"),15," "));
                            bw.write(StringUtils.rightPad("CHF",3," "));
                            bw.write(StringUtils.rightPad(resultSet.getString("REPORT_NO"),8," "));
                            bw.write(StringUtils.rightPad(resultSet1.getString("HSN_CODE"),16," "));
                            stat3 = conndb2.prepareStatement("Select T0GEOC from m3fdbprd.CGEOJU where t0cono=111 and t0taj3<>'' and t0taj1=?");
                            stat3.setString(1, resultSet.getString("SAECAR"));
                            resultSet3 = stat3.executeQuery();
                            if(resultSet3.next()){
                                bw.write(StringUtils.rightPad(resultSet3.getString("T0GEOC"),10," "));
                            }
                            if(resultSet3!=null) resultSet3.close();
                            if(stat3!=null) stat3.close(); 
                            bw.write(StringUtils.rightPad(resultSet2.getString("TAX_CODE"),3," "));
                            bw.write(StringUtils.leftPad("",16," "));
                            bw.write(StringUtils.rightPad(resultSet1.getString("UOM"),3," "));
                            bw.write("\n");
                            if (resultSet.getString("REVERSE_SRVTAX") != null && resultSet.getString("REVERSE_SRVTAX").equalsIgnoreCase("1")) {
                                bw.write("I2");
                                bw.write(StringUtils.leftPad(SEQ, 9, " "));
                                bw.write(StringUtils.rightPad(resultSet.getString("SL_NO"), 8, " "));
                                bw.write(DIVITEMP);
                                bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10, " "));
                                bw.write(StringUtils.rightPad(resultSet.getString("SUPPLIER_CODE"), 10, " "));
                                bw.write(StringUtils.rightPad(resultSet.getString("BILL_NO"), 24, " "));
                                bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8, " "));
                                bw.write(StringUtils.rightPad(resultSet.getString("BILL_DATE"), 8, " "));
                                bw.write(StringUtils.leftPad("-"+resultSet2.getDouble("AMT") + "", 17, " "));
                                bw.write("INR");
                                bw.write(StringUtils.leftPad("1", 3, " "));
                                bw.write(StringUtils.leftPad("1", 13, " "));
                                bw.write(StringUtils.rightPad(resultSet.getString("VCH_DATE"), 8, " "));
                                bw.write(StringUtils.rightPad("AUDITSHMG", 10, " "));
                                bw.write(StringUtils.rightPad(" " + GEOCTEMP, 20, " "));

                                stat3 = conn.prepareStatement("select REV_GL_CODE from M4_BILL_HSN_GL where flag='Y' and GL_CODE=? and REV_GL_CODE is not null");
                                stat3.setString(1, resultSet2.getString("COST_ELEMENT"));
                                resultSet3 = stat3.executeQuery();
                                if(resultSet3.next()){
                                    bw.write(StringUtils.rightPad(resultSet3.getString("REV_GL_CODE"), 8, " "));
                                }
                                if (resultSet3 != null) {
                                    resultSet3.close();
                                }
                                if (stat3 != null) {
                                    stat3.close();
                                }

                                bw.write(StringUtils.rightPad(resultSet1.getString("PCH"), 8, " "));

                                stat3 = connhris.prepareStatement("SELECT EAAITM FROM SEPLWEB.M4_CC_MASTER@IBM.WORLD@IBM A,PRODBI.FCHACC B WHERE EACONO=111 AND A.CC_CODE=B.EAAITM AND EAAITP=3 AND DEPT_SL_NO='93' AND EAAT12=0 AND SL_NO=?");
                                stat3.setString(1, resultSet1.getString("CC_CODE"));
                                resultSet3 = stat3.executeQuery();
                                if (resultSet3.next()) {
                                    bw.write(StringUtils.rightPad(resultSet3.getString("EAAITM"), 8, " "));
                                }
                                if (resultSet3 != null) {
                                    resultSet3.close();
                                }
                                if (stat3 != null) {
                                    stat3.close();
                                }
                                bw.write(StringUtils.rightPad(resultSet1.getString("PARTNER_DESC"), 8, " "));
                                bw.write(StringUtils.rightPad(resultSet1.getString("VOUCHER_DESC"), 40, " "));
                                bw.write(StringUtils.leftPad("0.00", 19, " "));

                                stat3 = conn.prepareStatement("SELECT PRODUCT_CODE FROM M4_BILL_PRODUCT_MASTER WHERE SL_NO=?");
                                stat3.setString(1, resultSet1.getString("PRODUCT_SL_NO"));
                                resultSet3 = stat3.executeQuery();
                                if (resultSet3.next()) {
                                    bw.write(StringUtils.rightPad(resultSet3.getString("PRODUCT_CODE"), 8, " "));
                                }
                                if (resultSet3 != null) {
                                    resultSet3.close();
                                }
                                if (stat3 != null) {
                                    stat3.close();
                                }
                                bw.write(StringUtils.leftPad(resultSet1.getString("PRODUCT_QUANTITY"), 15, " "));
                                bw.write(StringUtils.rightPad("CHF", 3, " "));
                                bw.write(StringUtils.rightPad(resultSet.getString("REPORT_NO"),8," "));
                                bw.write(StringUtils.rightPad(resultSet1.getString("HSN_CODE"), 16, " "));
                                stat3 = conndb2.prepareStatement("Select T0GEOC from m3fdbprd.CGEOJU where t0cono=111 and t0taj3<>'' and t0taj1=?");
                                stat3.setString(1, resultSet.getString("SAECAR"));
                                resultSet3 = stat3.executeQuery();
                                if (resultSet3.next()) {
                                    bw.write(StringUtils.rightPad(resultSet3.getString("T0GEOC"), 10, " "));
                                }
                                if (resultSet3 != null) {
                                    resultSet3.close();
                                }
                                if (stat3 != null) {
                                    stat3.close();
                                }
                                bw.write(StringUtils.rightPad(resultSet2.getString("TAX_CODE"), 3, " "));
                                bw.write(StringUtils.leftPad("", 16, " "));
                                bw.write(StringUtils.rightPad(resultSet1.getString("UOM"),3," "));
                                bw.write("\n");
                            }
                        }
                        if(resultSet2!=null) resultSet2.close();
                        if(stat2!=null) stat2.close(); 
                    }
                    if(resultSet1!=null) resultSet1.close();
                    if(stat1!=null) stat1.close();
                }
                if(resultSet!=null) resultSet.close();
                if(stat!=null) stat.close();
            }
            if(bw!=null) bw.close();
            if(new File(REALFILENAME).exists()){
                Ftpfileupload fileobj = new Ftpfileupload();
                String connst = getValue("MIIP");
                String cuser = getValue("UserNameDB2");
                String cpass = getValue("PasswordDB2");
                String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, REALFILENAME, "/M3BE1512/env/M3BE_15.1_PRD/transfer/GLS850MI/" + FILE_NAME);
                //   String ftpout = fileobj.FtpFileCopy(connst, cuser, cpass, real_filename, "/m3be/env/PROD/MvxTransfer/" + filename+".txt");
                if (ftpout.equals("OK")) {
                    //  System.out.println("FTP Ok...");
                    GLS850MI MI = new GLS850MI();
                    MI.connect();
                    // status = MI.DataUpload("111", DIVI, minte, filename+".txt", "/M3BE/env/PROD/MvxTransfer", "1");
                    MI.DataUpload("111", DIVI, MINTE, FILE_NAME, "GLS850MI", "1");
                    MI.destroyMI();
                    MI = null;

                }
            }
            
            MailProvider mailProvider = new MailProvider();
            String[] filenames = new String[1];
            filenames[0] = REALFILENAME;
            // mail status

            String ccAddress = null;
            String sehuser = null;

            String subjecttitle = "";
            String messageBodyText = " ";
            String frommail = "";
            String fromname = "";
            String[] tomail = new String[2];

            tomail[0] = "kuldeep.anandsingh@shahi.co.in";            
            //  tomail[1] = "rameshk.chauhan@shahi.co.in";
            PreparedStatement mailtoStat = connhris.prepareStatement("select SHAHI_ID,FULL_NAME from hrempmst where emp_code=?");
            mailtoStat.setString(1, userId);
            ResultSet mailtoResult = mailtoStat.executeQuery();
            if (mailtoResult.next()) {
                tomail[1] = mailtoResult.getString("SHAHI_ID");
                sehuser = mailtoResult.getString("FULL_NAME");
            }
            //tomail[1]="kuldeep.anandsingh@shahi.co.in";
            frommail = "shahi.it@shahi.co.in";
            fromname = "SHAHI IT";

            subjecttitle = PAY_TYPE+" Bill (M4) :-" + FILE_NAME;
            messageBodyText = "<html>";
            messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
            messageBodyText += "<body bgcolor=#95b174>";
            messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
            messageBodyText += "This is to inform you that below mentioned details cash bill has been generated in Movex. ";
            messageBodyText += "</br></br>";

            messageBodyText += "</font>";
            messageBodyText += "<table cellpadding='4' width='600'>";
            messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
            messageBodyText += "<tr style='font-size:14px;'><td>ShahiIT</td></tr>";
            messageBodyText += "</table>";
            messageBodyText += "</body>";
            messageBodyText += "</html>";
            mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, filenames);
         }catch(IOException e){
            System.out.println("CashBillTxtGenerator.class TxtGeneration"+e.getMessage());
        }catch(Exception e){
            System.out.println("CashBillTxtGenerator.class TxtGeneration"+e.getMessage());
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CashBillEntryAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connhris!=null){
                try {
                    connhris.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CashBillTxtGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(conndb2!=null) try {
                conndb2.close();
            } catch (SQLException ex) {
                Logger.getLogger(CashBillTxtGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "OK";
    }
}
