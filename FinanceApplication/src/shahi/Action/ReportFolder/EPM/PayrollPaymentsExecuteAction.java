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
import shahi.Action.database.connection;
import shahi.Action.database.connectionShahiHris;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author Vivek
 */

public class PayrollPaymentsExecuteAction extends ActionSupport {

    private String aausrid;
    private String DIVI = "100";
    private String YEAR;
    private String CKBKID;
    private String TMPRID;
    private double TOTAL_CHQ;
    private List bankcodelist;
    private List detaillist;
    private List EGAIT1;
   
    
    public String view() throws SQLException, Exception {
        Map session = ActionContext.getContext().getSession();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
            stat = conn.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE,DEPT_CODE from EMPLOYEE_VIEW where emp_code=?");
            stat.setString(1, aausrid);
            result = stat.executeQuery();
            if (result.next()) {
                session.put("sessUserName", result.getString("EMP_NAME"));
                session.put("sessUserId", result.getString("emp_code"));
                session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                session.put("sessUnitCode", result.getString("UNIT_CODE"));
                session.put("sessDeptCode", result.getString("DEPT_CODE"));
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println("1.PayrollPaymentsExecuteAction " + e);
            addActionError(e.getMessage());
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }
        getAllBankCode();
        return SUCCESS;
    }

    public void getAllBankCode() throws Exception {
        bankcodelist = new ArrayList();
        if (DIVI != null && DIVI.length() > 0) {
            Connection connbi = null;
            PreparedStatement stat = null;
            ResultSet result = null;
            try {
                connbi = new connectiondb2().getConnection();
                connbi.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionError(e.getMessage());
            }
            try {
                stat = connbi.prepareStatement("select distinct BCAIT1 BCBKID,BCBANA from mvxcdtprod.CBANAC where BCCONO=111 and BCDIVI=? and BCBKTP=1 order by 1");
                stat.setString(1, DIVI);
                result = stat.executeQuery();
                while (result.next()) {
                    bankcodelist.add(new CbnacBean(result.getString("BCBKID"), result.getString("BCBANA")));
                }
                connbi.commit();
            } catch (Exception e) {
                System.out.println("2.PayrollPaymentsExecuteAction " + e);
                addActionError(e.getMessage());
                connbi.rollback();
            } finally {
                if (connbi != null) {
                    connbi.close();
                }
                if (stat != null) {
                    stat.close();
                }
                if (result != null) {
                    result.close();
                }
            }
        }
    }

    @Override
    public String execute() throws Exception {
        double paybttot=0;
        double mvxbttot=0;
        detaillist = new ArrayList();
        Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        String LOCATION = (String) session.get("sessLocationCode");
        if (USER_ID == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection conn = null;
        Connection connora = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        PreparedStatement statpayCHK = null;
        ResultSet resultpayCHK = null;
        PreparedStatement statmvxCHK = null;
        ResultSet resultmvxCHK = null;
        try {
            conn = new connectiondb2().getConnection();
            conn.setAutoCommit(false);
            connora = new connection().getConnection();
            connora.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
            stat1 = connora.prepareStatement("select chk_upd from finac.fa_payroll_chq where  CONO=111 AND divi=? and batch_no=? and PYMT_DATE is not null and chk_upd is not null");
            stat1.setString(1, DIVI);
            stat1.setString(2, TMPRID);
            result1=stat1.executeQuery();
            if(result1.next()){
                addActionError("Payroll Batch Process is alredy Done.");
            }
            else{
                statpayCHK = connora.prepareStatement("select sum(chq_amount) chq_amount from finac.fa_payroll_chq a "
                                +" where cono=111 and divi=? and batch_no=? ");
                                
                statpayCHK.setString(1, DIVI);
                //statpayCHK.setString(2, CKBKID);
                statpayCHK.setString(2, TMPRID);
                resultpayCHK = statpayCHK.executeQuery();
                if (resultpayCHK.next()) {   
                    paybttot = resultpayCHK.getDouble("chq_amount");                    
                }
                
                statmvxCHK=conn.prepareStatement("select sum(egacam)egacam from mvxcdtprod.fgledg,mvxcdtprod.fchacc where egcono=111 and egdivi=? and egyea4=? and egait6=? and egait7<>'' and egcono=eacono and egait1=eaaitm and eaat12='1' and egreco<>9");
                statmvxCHK.setString(1, DIVI);
                statmvxCHK.setString(2, YEAR);
                //statmvxCHK.setString(3, CKBKID);
                statmvxCHK.setString(3, TMPRID);
                resultmvxCHK = statmvxCHK.executeQuery();
                if (resultmvxCHK.next()) {   
                    mvxbttot = resultmvxCHK.getDouble("egacam");                    
                }
                //System.out.println(paybttot+mvxbttot);
                if((paybttot+mvxbttot)==0){
                    
                }
                else{
                addActionError("Payroll and Movex Amount not matched for given batch no "+TMPRID);
                    getAllBankCode();
                    return SUCCESS;
                }
                
                stat = conn.prepareStatement("select egcono,egdivi,egyea4,egacdt,egvser,egvono,egait1,egvtxt,egait5,egait6,egait7,egacam from mvxcdtprod.fgledg,mvxcdtprod.fchacc "
                        + " where egcono=111 and egdivi=? and egyea4=? and egait6=? and egait7<>'' and egcono=eacono and egait1=eaaitm and eaat12='1' order by egait7");
                stat.setString(1, DIVI);
                stat.setString(2, YEAR);
                //stat.setString(3, CKBKID);
                stat.setString(3, TMPRID);
                result = stat.executeQuery();
                TOTAL_CHQ=0;
                while (result.next()) {
                    TOTAL_CHQ+=result.getDouble("egacam");
                    detaillist.add(new PayrollPaymentsExecuteBean(result.getString("egdivi"), result.getString("egait6"), result.getString("egait7"), result.getString("egacam"), result.getString("egyea4"), result.getString("egvser"), result.getString("egvono"), result.getString("egait5"), result.getString("egvtxt"), result.getString("egacdt"), result.getDouble("egacam"),result.getString("egait1")));
                }
            }
            connora.commit();
            conn.commit();
        } catch (Exception e) {
            System.out.println("3.PayrollPaymentsExecuteAction " + e);
            addActionError(e.getMessage());
            conn.rollback();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if(connora!=null){
                connora.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (result1 != null) {
                result1.close();
            }
        }
        getAllBankCode();
        return SUCCESS;
    }

    public String save() throws SQLException, Exception {
        Map session = ActionContext.getContext().getSession();
        String USER_ID = (String) session.get("sessUserId");
        String LOCATION = (String) session.get("sessLocationCode");
        if (USER_ID == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
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
        ResultSet mailtoResult = null;
        try {
            conn = new connectiondb2().getConnection();
            connora = new connection().getConnection();
            connhris = new connectionShahiHris().getConnection();
            conn.setAutoCommit(false);
            connora.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
            addActionError(e.getMessage());
        }
        try {
            if(EGAIT1!=null && EGAIT1.size()>0){
                for(int i=0;i<EGAIT1.size();i++){
                    stat = conn.prepareStatement("insert into mvxcdtprod.fapchk select egcono ckcono,egdivi ckdivi,bcbkid,repeat( '0', 15-cast(length(trim(egait7)) as integer))||trim(egait7)  ckchkn,0 ckckno,'COMM BONUS' ckspyn,egvtxt cksunm,egait7 ckadr1,egait3 ckadr2,'' ckadr3,'' ckadr4, "
                        + " 0-egacam ckpycu,'INR' CKCUCD,EGACDT CKDTPR,2 ckstts,'' ckosts,0 ckvccd,egait1 ckait1,'' ckait2,'' ckait3,'' ckait4,egait5 ckait5,egait6 ckait6,'' ckait7,'' ckstmn, "
                        + " egyea4 ckyea4,egvser ckvser,egvono ckvono,0 ckcada,'CHF' ckpyme,'AP20' ckfeid,'704'ckfncn,'' cktx01,'' cktx02,egrgdt ckrgdt,egrgtm ckrgtm,eglmdt cklmdt,0 ckchno,egchid ckchid "
                        + " from mvxcdtprod.fgledg,mvxcdtprod.cbanac "
                        + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=? and egait7<>''"
                        + " and egcono=bccono and egdivi=bcdivi and egait1=bcait1");
                    stat.setString(1, DIVI);
                    stat.setString(2, YEAR);
                    stat.setString(3, EGAIT1.get(i).toString());
                    stat.setString(4, TMPRID);
                    stat.executeUpdate();
                    stat1 = conn.prepareStatement("insert into mvxcdtprod.fgledx"
                            + " select egcono,egdivi,egyea4,egjrno,egjsno,'2' eggexn,ckbkid||ckchkn eggexi,1 eggexs,0 egtxid,egrgdt,egrgtm,eglmdt,egchno,egchid,eglmts,egmigi from mvxcdtprod.fgledg,mvxcdtprod.fapchk"
                            + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=? and egait7<>''"
                            + " and egcono=ckcono and egdivi=ckdivi and egyea4=ckyea4 and egvser=ckvser and egvono=ckvono");
                    stat1.setString(1, DIVI);
                    stat1.setString(2, YEAR);
                    stat1.setString(3, EGAIT1.get(i).toString());
                    stat1.setString(4, TMPRID);
                    stat1.executeUpdate();

                    stat2 = conn.prepareStatement("insert into mvxcdtprod.fgledx"
                            + " select egcono,egdivi,egyea4,egjrno,egjsno,'15' eggexn,ltrim(egyea4) eggexi,1 eggexs,0 egtxid,egrgdt,egrgtm,eglmdt,egchno,egchid,eglmts,egmigi from mvxcdtprod.fgledg,mvxcdtprod.fapchk"
                            + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=? and egait7<>''"
                            + " and egcono=ckcono and egdivi=ckdivi and egyea4=ckyea4 and egvser=ckvser and egvono=ckvono");
                    stat2.setString(1, DIVI);
                    stat2.setString(2, YEAR);
                    stat2.setString(3, EGAIT1.get(i).toString());
                    stat2.setString(4, TMPRID);
                    stat2.executeUpdate();

                    stat3 = conn.prepareStatement("insert into shacdtprod.xapchk"
                            + " select egcono ckcono,egdivi ckdivi,bcbkid,repeat( '0', 15-cast(length(trim(egait7)) as integer))||trim(egait7) ckchkn,0 ckckno,'COMM BONUS' ckspyn,"
                            + " EGACDT CKDTPR,egyea4 ckyea4,egvser ckvser,egvono ckvono,egacdt ckckdt,egrgdt ckrgdt,egrgtm ckrgtm,eglmdt cklmdt,0 ckchno,egchid ckchid"
                            + " from mvxcdtprod.fgledg,mvxcdtprod.cbanac"
                            + " where egcono=111 and egdivi=? and egyea4=? and egait1=? and egait6=? and egait7<>''"
                            + " and egcono=bccono and egdivi=bcdivi and egait1=bcait1");
                    stat3.setString(1, DIVI);
                    stat3.setString(2, YEAR);
                    stat3.setString(3, EGAIT1.get(i).toString());
                    stat3.setString(4, TMPRID);
                    stat3.executeUpdate();

                    stat5 = conn.prepareStatement("update mvxcdtprod.fgledg  set egtrcd='51' where egcono=111 and egdivi=? and egyea4=? and (egcono||egdivi||egyea4||egvono||egvser||egfeid||egfncn) in (select (egcono||egdivi||egyea4||egvono||egvser||egfeid||egfncn) from mvxcdtprod.fgledg where egcono=111 and egdivi=? and egyea4=? and egait1=?  and egait6=? and egait7<>'')");
                    stat5.setString(1, DIVI);
                    stat5.setString(2, YEAR);
                    stat5.setString(3, DIVI);
                    stat5.setString(4, YEAR);
                    stat5.setString(5, EGAIT1.get(i).toString());
                    stat5.setString(6, TMPRID);
                    stat5.executeUpdate();                    
                }
            }
            stat4 = connora.prepareStatement("update finac.fa_payroll_chq set chk_upd=1 where  CONO=111 AND divi=? and batch_no=? and PYMT_DATE is not null ");
                    stat4.setString(1, DIVI);
                    stat4.setString(2, TMPRID);
                    stat4.executeUpdate();
            
            
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

                findrecord_stat = connora.prepareStatement("select company_code,cat_code,bank_code,emp_code,acc_data_desc,chq_no, chq_date,chq_amount,vser,vono from finac.fa_payroll_chq where  CONO=111 AND batch_no=? order by chq_no");
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
                frommail = "shahi.it@shahi.co.in";
                fromname = "SHAHI IT";
                
               subjecttitle = "Cheques uploaded in movex of batch No  :-" + TMPRID;
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
            addActionError("Update Successfully ");
        } catch (Exception e) {
            System.out.println("4.PayrollPaymentsExecuteAction " + e);
            addActionError(e.getMessage());
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
        getAllBankCode();
        return SUCCESS;
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
