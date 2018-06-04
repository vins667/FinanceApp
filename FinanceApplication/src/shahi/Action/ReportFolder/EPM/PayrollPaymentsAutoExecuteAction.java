/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.ReportFolder.EPM.beans.CbnacBean;
import shahi.Action.ReportFolder.EPM.beans.PayrollPaymentsExecuteBean;
//import shahi.Action.database.ConnectionDB2Movex;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connectionShahiHris;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author Vivek
 */
public class PayrollPaymentsAutoExecuteAction {

    private String aausrid;
    private String DIVI = "100";
    private String YEAR;
    private String CKBKID;
    private String TMPRID;
    private double TOTAL_CHQ;
    private List bankcodelist;
    private List detaillist;
    private List EGAIT1;
   
    public static ResourceBundle aResourcBundle = null;
  
    public PayrollPaymentsAutoExecuteAction() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }
    
   
    public String execute() throws SQLException {
        //Map session = ActionContext.getContext().getSession();
        //String USER_ID = (String) session.get("sessUserId");
        //String LOCATION = (String) session.get("sessLocationCode");
        String USER_ID="227352";
        String LOCATION="FBAD";
        Connection conn = null;
        Connection connora = null;
        Connection connhris = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        PreparedStatement stat3 = null;
        PreparedStatement stat4 = null;
        PreparedStatement stat5 = null;
        PreparedStatement findrecord_stat = null;
        PreparedStatement mailtoStat=null;
        ResultSet result = null;
        ResultSet result1 = null;
        ResultSet mailtoResult = null;
        try {
            conn = new connectiondb2().getConnection();
            connora = new ConnectionShahiHrisNew().getConnection();
            connhris = new connectionShahiHris().getConnection();
            conn.setAutoCommit(false);
            connora.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            
        }
        try {
            int MYEAR=0;
          // String MYEAR=null; 
            int mm=0;
            String DIVI=null;
            String EGAIT1=null;
            String TMPRID=null;
            stat1 = connora.prepareStatement("select distinct cono,divi,to_number(to_char(chq_date,'yyyy')) year,to_number(to_char(to_date(chq_date,'dd-mm-yyyy'),'mm')) month,batch_no,bank_Code from AMSNOW.fa_payroll_chq where pymt_date is not null"
                    +" and batch_no is not null and chk_upd is null and bank_code is not null and state_code not in ('KA','TN') order by batch_no");
            result1=stat1.executeQuery();
            while(result1.next())
            {
                 TMPRID=result1.getString("batch_no");
                 DIVI=result1.getString("divi");
                 EGAIT1=result1.getString("bank_code");
                 mm=result1.getInt("month"); 
                 MYEAR=result1.getInt("year");
                  if (mm==1 || mm==2 || mm==3)
                  { MYEAR=MYEAR-1;
                    
                  }
             
 //  System.out.println("1..."+DIVI+MYEAR+EGAIT1+TMPRID);
            if(EGAIT1!=null )
            {
      //System.out.println("1a..."+DIVI+MYEAR+EGAIT1+TMPRID);        
                    stat = conn.prepareStatement("insert into m3fdbprd.fapchk select egcono ckcono,egdivi ckdivi,bcbkid,repeat( '0', 15-cast(length(trim(egait7)) as integer))||trim(egait7)  ckchkn,0 ckckno,'COMM BONUS' ckspyn,egvtxt cksunm,egait7 ckadr1,egait3 ckadr2,'' ckadr3,'' ckadr4, "
                        + " 0-egacam ckpycu,'INR' CKCUCD,EGACDT CKDTPR,2 ckstts,'' ckosts,0 ckvccd,egait1 ckait1,'' ckait2,'' ckait3,'' ckait4,egait5 ckait5,egait6 ckait6,'' ckait7,'' ckstmn, "
                        + " egyea4 ckyea4,egvser ckvser,egvono ckvono,0 ckcada,'CHF' ckpyme,'AP20' ckfeid,'704'ckfncn,'' cktx01,'' cktx02,egrgdt ckrgdt,egrgtm ckrgtm,eglmdt cklmdt,0 ckchno,egchid ckchid,eglmts cklmts"
                        + " from m3fdbprd.fgledg,(select bccono,bcdivi,bcait1,max(bcbkid) bcbkid from m3fdbprd.cbanac where bccono=111 and bcdivi=?" 
                        + " and (bcbkid not like '%RT%' and bcbkid not like '%MN%' and bcbkid not like '%HR%' and bcbkid not like '%DD%'  and bcbkid not like '%RF%'  and bcbkid not like '%FF%') group by bccono,bcdivi,bcait1) b "
                        + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=?  and egait7<>'' "
                        + " and egcono=b.bccono and egdivi=b.bcdivi and egait1=b.bcait1");
                    stat.setString(1, DIVI);
                    stat.setString(2, DIVI);
                    stat.setString(3, MYEAR+"");
                    stat.setString(4, EGAIT1);
                    stat.setString(5, TMPRID);
                    stat.executeUpdate();
  
                    stat1 = conn.prepareStatement("insert into m3fdbprd.fgledx"
                            + " select egcono,egdivi,egyea4,egjrno,egjsno,'2' eggexn,ckbkid||ckchkn eggexi,1 eggexs,0 egtxid,egrgdt,egrgtm,eglmdt,egchno,egchid,eglmts,egmigi from m3fdbprd.fgledg,m3fdbprd.fapchk"
                            + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=?"
                            + " and egcono=ckcono and egdivi=ckdivi and egyea4=ckyea4 and egvser=ckvser and egvono=ckvono");
                    stat1.setString(1, DIVI);
                    stat1.setString(2, MYEAR+"");
                    stat1.setString(3, EGAIT1);
                    stat1.setString(4, TMPRID);
                    stat1.executeUpdate();
 // System.out.println("2..."+DIVI+MYEAR+EGAIT1+TMPRID);
                    stat2 = conn.prepareStatement("insert into m3fdbprd.fgledx"
                            + " select egcono,egdivi,egyea4,egjrno,egjsno,'15' eggexn,ltrim(egyea4) eggexi,1 eggexs,0 egtxid,egrgdt,egrgtm,eglmdt,egchno,egchid,eglmts,egmigi from m3fdbprd.fgledg,m3fdbprd.fapchk"
                            + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=?"
                            + " and egcono=ckcono and egdivi=ckdivi and egyea4=ckyea4 and egvser=ckvser and egvono=ckvono");
                    stat2.setString(1, DIVI);
                    stat2.setString(2, MYEAR+"");
                    stat2.setString(3, EGAIT1);
                    stat2.setString(4, TMPRID);
                    stat2.executeUpdate();
  //  System.out.println("3..."+DIVI+MYEAR+EGAIT1+TMPRID);
                    stat3 = conn.prepareStatement("insert into cinfdbprd.xapchk"
                            + " select egcono ckcono,egdivi ckdivi,bcbkid,repeat( '0', 15-cast(length(trim(egait7)) as integer))||trim(egait7) ckchkn,0 ckckno,'COMM BONUS' ckspyn,"
                            + " EGACDT CKDTPR,egyea4 ckyea4,egvser ckvser,egvono ckvono,egacdt ckckdt,'' cklcrf,egrgdt ckrgdt,egrgtm ckrgtm,eglmdt cklmdt,0 ckchno,egchid ckchid,'' ckzarn,0 ckzard,0 ckzno1,0 cklmts"
                            + " from m3fdbprd.fgledg,(select bccono,bcdivi,bcait1,max(bcbkid) bcbkid from m3fdbprd.cbanac where bccono=111 and bcdivi=?" 
                            + " and (bcbkid not like '%RT%' and bcbkid not like '%MN%' and bcbkid not like '%HR%' and bcbkid not like '%DD%'  and bcbkid not like '%RF%'  and bcbkid not like '%FF%') group by bccono,bcdivi,bcait1) b"
                            + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=?"
                            + " and egcono=b.bccono and egdivi=b.bcdivi and egait1=b.bcait1");
                    stat3.setString(1, DIVI);
                    stat3.setString(2, DIVI);
                    stat3.setString(3, MYEAR+"");
                    stat3.setString(4, EGAIT1);
                    stat3.setString(5, TMPRID);
                    stat3.executeUpdate();
   // System.out.println("4..."+DIVI+MYEAR+EGAIT1+TMPRID);
                    stat5 = conn.prepareStatement("update m3fdbprd.fgledg  set egtrcd='51' where egcono=111 and egdivi=? and egyea4=? and (egcono||egdivi||egyea4||egvono||egvser||egfeid||egfncn) in (select (egcono||egdivi||egyea4||egvono||egvser||egfeid||egfncn) from m3fdbprd.fgledg where egcono=111 and egdivi=? and egyea4=? and egait1=?  and egait6=?)");
                    stat5.setString(1, DIVI);
                    stat5.setString(2, MYEAR+"");
                    stat5.setString(3, DIVI);
                    stat5.setString(4, MYEAR+"");
                    stat5.setString(5, EGAIT1);
                    stat5.setString(6, TMPRID);
                    stat5.executeUpdate();                    
            }
   //   System.out.println("5..."+DIVI+MYEAR+EGAIT1+TMPRID);          
            stat4 = connora.prepareStatement("update AMSNOW.fa_payroll_chq set chk_upd=1 where  CONO=111 AND divi=? and batch_no=? and PYMT_DATE is not null ");
                    stat4.setString(1, DIVI);
                    stat4.setString(2, TMPRID);
                    stat4.executeUpdate();
         //  System.out.println("6..."+DIVI+MYEAR+EGAIT1+TMPRID);     
            
              MailProvider mailProvider = new MailProvider();                
                // mail status

                String ccAddress = null;
                String sehuser = null;
                
                String subjecttitle="";
                String messageBodyText=" ";
                String frommail="";
                String fromname="";
                String []tomail=new String[2];
                String toname="";

                if (LOCATION.equals("FBAD")) {
                    ccAddress = "kuldeep.anandsingh@shahi.co.in";
                } else {
                    ccAddress = "kuldeep.anandsingh@shahi.co.in";
                }

                findrecord_stat = connora.prepareStatement("select company_code,cat_code,bank_code,emp_code,acc_data_desc,chq_no, chq_date,chq_amount,vser,vono from AMSNOW.fa_payroll_chq where  CONO=111 AND batch_no=? order by chq_no");
                //findrecord_stat.setString(1, DIVI);
                //findrecord_stat.setString(2, YEAR);
                //findrecord_stat.setString(3, CKBKID);
                findrecord_stat.setString(1, TMPRID);
                result = findrecord_stat.executeQuery();
                tomail[0]="rameshk.chauhan@shahi.co.in";
                mailtoStat = connhris.prepareStatement("select SHAHI_ID,FULL_NAME from hrempmst where emp_code=?");
                mailtoStat.setString(1, USER_ID.trim());
                mailtoResult = mailtoStat.executeQuery();
                if (mailtoResult.next()) {
                    tomail[1] = mailtoResult.getString("SHAHI_ID");
                    sehuser = mailtoResult.getString("FULL_NAME");
                }
                NumberFormat formatter = new DecimalFormat("#0.00");
                //tomail[1]="kuldeep.anandsingh@shahi.co.in";
                frommail = "movex@shahi.co.in";
                fromname = "AutoMail@Shahi";
                
               subjecttitle = "Cheques uploaded in movex of batch No.(M4) :-" + TMPRID;
                messageBodyText = "<html>";
                messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                messageBodyText += "<body bgcolor=#95b174>";
                messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                messageBodyText += "This is to inform you that below mentioned details cheques has been posted in Movex. Batch No is " + TMPRID;
                messageBodyText += "</br></br>";

                messageBodyText += "</font>";
                messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Sr No</td><td style='width:70pt'>Location</td><td style='width:20pt'>Category</td><td style='width:20pt'>Bank</td><td style='width:50pt'>Emp.Code</td><td style='width:200pt'>Emp Name</td><td style='width:90pt'>Cheque No.</td><td style='width:90pt'>Cheque.Dt.</td>"
                        + "<td style='width:90pt;text-align:right;'>Amt.</td></tr>";
                double chqamttot=0;
                int counter=0;
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
                mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
             
               conn.commit();
               connora.commit();
            }
           // addActionError("Update Successfully ");
        } catch (Exception e) {
            System.out.println("4.PayrollPaymentsAutoExecuteAction " + e);
              conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if(connora!=null){
                connora.close();
            }
            if(connhris!=null){
                connhris.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (stat2 != null) {
                stat2.close();
            }
            if (stat3 != null) {
                stat3.close();
            }
            if (stat4 != null) {
                stat4.close();
            }
            if (stat5 != null) {
                stat5.close();
            }
            
        }
       // getAllBankCode();
        return "SUCCESS";
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getCKBKID() {
        return CKBKID;
    }

    public void setCKBKID(String CKBKID) {
        this.CKBKID = CKBKID;
    }

    public String getTMPRID() {
        return TMPRID;
    }

    public void setTMPRID(String TMPRID) {
        this.TMPRID = TMPRID;
    }

    public List getBankcodelist() {
        return bankcodelist;
    }

    public void setBankcodelist(List bankcodelist) {
        this.bankcodelist = bankcodelist;
    }

    public List getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(List detaillist) {
        this.detaillist = detaillist;
    }

    public double getTOTAL_CHQ() {
        return TOTAL_CHQ;
    }

    public void setTOTAL_CHQ(double TOTAL_CHQ) {
        this.TOTAL_CHQ = TOTAL_CHQ;
    }

    public List getEGAIT1() {
        return EGAIT1;
    }

    public void setEGAIT1(List EGAIT1) {
        this.EGAIT1 = EGAIT1;
    }
}
