
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.ReportFolder.EPM;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import shahi.Action.MI.APZ300MI;
//import shahi.Action.MI.GLS181MI;
//import shahi.Action.MailProvider.MailProvider;
import shahi.Action.database.connection;
import shahi.Action.ReportFolder.EPM.beans.BankUpdationBean;
import shahi.Action.database.connectionMovexBi;
//import shahi.Action.database.connectionShahiPortal;
import shahi.Action.database.connectiondb2;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**

 *
 * @author Vivek

 */
public class BankUpdationAction extends ActionSupport {

    private String aausrid;
    private String pyear;
    private String BSUNO;
    private String DIVI;
    private List DETAIL_LIST = new ArrayList();
    private String[] chkdel;
    ResourceBundle aResourcBundle = null;

    public BankUpdationAction() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }
    public void viewpage(String myusrid) throws Exception {
        Map session = ActionContext.getContext().getSession();
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            stat = conn.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE from shahi_connect_view where emp_code=?");
            stat.setString(1, myusrid);
            result = stat.executeQuery();
            if (result.next()) {
                session.put("sessUserName", result.getString("EMP_NAME"));
                session.put("sessUserId", result.getString("emp_code"));
                session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                session.put("sessUnitCode", result.getString("UNIT_CODE"));
            }
        } catch (Exception e) {
            System.out.println("BankUpdationAction " + e);
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
    }
    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    @Override
    public String execute() throws Exception {
        DETAIL_LIST = new ArrayList();
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        String unit = (String) session.get("sessUnitCode");
        if (usrid == null && unit == null) {
            usrid = aausrid;
            viewpage(usrid);
        }
        Connection conn1 = null;
        String bsoutemp = "";
        if (BSUNO == null && BSUNO.length() == 0) {
            bsoutemp = "%";
        } else {
            bsoutemp = BSUNO + "%";
        }
        try {
            conn1 = new connection().getConnection();
            PreparedStatement findrecord_stat = null;
            findrecord_stat = conn1.prepareStatement("select bank_code division,chq_no,year,chq_desc vser,chq_no vono,utr_flag ,chq_amount,chq_date from finac.fa_bank_statement where comp_id=117 and division=? and year=?  and chq_no is not null and chq_no like ? order by chq_no");
            findrecord_stat.setString(1, DIVI);
            findrecord_stat.setString(2, pyear);
            findrecord_stat.setString(3, bsoutemp);
            ResultSet result = findrecord_stat.executeQuery();
            while (result.next()) {
                DETAIL_LIST.add(new BankUpdationBean(result.getString("division"), result.getString("chq_no"), result.getString("year"), result.getString("vser"), result.getString("vono"), result.getString("utr_flag")));
            }
        } catch (SQLException se) {
            addActionError(se.getMessage());
        } catch (Exception e) {
            addActionError(e.getMessage());
        } finally {
            if (conn1 != null) {
                conn1.close();
            }
        }
        return SUCCESS;

    }

    public String updatemi() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String loct = (String) session.get("sessLocationCode");
        String unit = (String) session.get("sessUnitCode");
        if(usrid == null){
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection conn = null;
        Connection conn1 = null;
        Connection connora = null;  
        PreparedStatement stat = null;
        try {
            conn1 = new connectiondb2().getConnection();
        //    conn = new connectionShahiPortal().getConnection();
            conn.setAutoCommit(false);
            conn1.setAutoCommit(false);
            connora = new connection().getConnection();
            connora.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            if (chkdel != null && chkdel.length > 0) {
                for (int i = 0; i < chkdel.length; i++) {
                    if (chkdel[i] != null  && !chkdel[0].equals("false")) {
                        String year = chkdel[i].substring(0, chkdel[i].lastIndexOf("-"));
                        String vser = chkdel[i].substring(chkdel[i].lastIndexOf("-") + 1, chkdel[i].lastIndexOf(":"));
                        String vono = chkdel[i].substring(chkdel[i].lastIndexOf(":") + 1, chkdel[i].lastIndexOf("%"));
                        String CHQ_NO = chkdel[i].substring(chkdel[i].lastIndexOf("%") + 1, chkdel[i].lastIndexOf("$"));
                        String division = chkdel[i].substring(chkdel[i].lastIndexOf("$") + 1, chkdel[i].length());
                        if (year.length() > 0 && vser.length() > 0 && vono.length() > 0 && CHQ_NO.length() > 0) {
                            int chklen = CHQ_NO.trim().length();
                            for(int x=0;x<15-chklen;x++){
                                CHQ_NO = "0"+CHQ_NO.trim();
                            }
                             String bkid=null;
                             PreparedStatement findrecord_stat1 = null;
                             findrecord_stat1 = conn1.prepareStatement("select ckbkid from m3fdbprd.fapchk where ckcono=111 and ckdivi=? and ckyea4=? and ckvser=? and ckvono=? ");
                             findrecord_stat1.setString(1, DIVI);
                             findrecord_stat1.setString(2, pyear);
                             findrecord_stat1.setString(3, vser);
                             findrecord_stat1.setString(4, vono);
                             ResultSet result1 = findrecord_stat1.executeQuery();
                             if (result1.next()) {
                                bkid = result1.getString("ckbkid");
                            }  
                            String status;
                            APZ300MI MI = new APZ300MI();
                            MI.connect();
                            status = MI.UpdateChkNo(CHQ_NO.trim(), "111", division.trim(),bkid, year.trim(), vser.trim(), vono.trim());
                            MI.destroyMI();
                            MI = null;
                            if (!status.equals("1")) {
                                conn.rollback();
                                conn1.rollback();
                                addActionError(status);
                                return ERROR;
                            } else {
                                PreparedStatement fetchstat = connora.prepareStatement("select bank_code from finac.fa_bank_statement_dummy where comp_id=122 and division=? and year=? and vser=? and vono=?");
                                fetchstat.setString(1, division.trim());
                                fetchstat.setString(2, year.trim());
                                fetchstat.setString(3, vser.trim());
                                fetchstat.setString(4, vono.trim());
                                ResultSet fetchres=fetchstat.executeQuery();
                                String BANK_CODE="";
                                if(fetchres.next()){
                                    BANK_CODE = fetchres.getString("bank_code");
                                }
                                
                               /* PreparedStatement fetchstat1 = conn1.prepareStatement("select a.EGCONO,a.EGDIVI,a.EGYEA4,a.EGJRNO,a.EGJSNO,a.EGAIT1,b.ERSTMN"
                                        + " from mvxcdtprod.fgledg a,mvxcdtprod.ferech b"
                                        + " where a.egcono=111 and a.egdivi=? and a.egyea4=? and a.egait1=? and a.egvser=? and a.egvono=?"
                                        + " and a.egcono=b.ercono and a.egdivi=b.erdivi and a.egait1=b.erait1 and egacdt between b.erfrdt and b.ertodt");
                                fetchstat1.setString(1, division.trim());
                                fetchstat1.setString(2, year.trim());
                                fetchstat1.setString(3, BANK_CODE.trim());
                                fetchstat1.setString(4, vser.trim());
                                fetchstat1.setString(5, vono.trim());
                                ResultSet fetchres1=fetchstat1.executeQuery();
                                if(fetchres1.next()){
                                    GLS181MI MI181 = new GLS181MI();
                                    MI181.connect();
                                    MI181.Reconcile(fetchres1.getString("EGCONO"), fetchres1.getString("EGDIVI"), fetchres1.getString("EGYEA4"), fetchres1.getString("EGJRNO"), fetchres1.getString("EGJSNO"), fetchres1.getString("EGAIT1"), fetchres1.getString("ERSTMN"));
                                    MI181.destroyMI();
                                    MI181 = null;
                                }
                                else{
                                    conn.rollback();
                                    conn1.rollback();
                                    addActionError("Error:- Create Bank Statement in GLS 181");
                                    return ERROR;
                                }*/
                                Thread.sleep(1000);
                                int statusres = 0;
                                stat = connora.prepareStatement("update finac.fa_bank_statement_dummy set utr_flag=1,utr_date=sysdate,chq_desc='AUTO' where comp_id=122 and division=? and year=? and vser=? and vono=?");
                                stat.setString(1, division.trim());
                                stat.setString(2, year.trim());
                                stat.setString(3, vser.trim());
                                stat.setString(4, vono.trim());
                                statusres = stat.executeUpdate();
                                if (statusres > 0) {
                                    addActionError("Check Updated " + CHQ_NO);
                                   // callOracleStoredProcINParameter(division, year, vser, vono,CHQ_NO,"AUTO");
                                    /*String path1 = getValue("reportPdfPath");
                                    Map parameters = new HashMap();
                                    InputStream input = new FileInputStream(new File(path1 + "Bank_annexUpload.xml"));
                                    JasperDesign design = JRXmlLoader.load(input);
                                    JasperReport report = JasperCompileManager.compileReport(design);

                                    InputStream input1 = new FileInputStream(new File(path1 + "PymtAnnex_sub1.xml"));
                                    JasperDesign design1 = JRXmlLoader.load(input1);
                                    JasperReport report1 = JasperCompileManager.compileReport(design1);

                                    InputStream input2 = new FileInputStream(new File(path1 + "PymtAnnex_sub2.xml"));
                                    JasperDesign design2 = JRXmlLoader.load(input2);
                                    JasperReport report2 = JasperCompileManager.compileReport(design2);
                                    parameters.put("ReportTitle", "SHAHI EXPORTS PVT. LTD. IP-1,SEC-28(HFD) MAIN MATHURA ROAD, FARIDABAD-121003 HARYNA,INDIA");
                                    parameters.put("pdivi", division);
                                    parameters.put("pyear", year);
                                    parameters.put("pvser", vser);
                                    parameters.put("pvono", vono);
                                    parameters.put("PymtAnnex_sub1", report1);
                                    parameters.put("PymtAnnex_sub2", report2);
                                    String mlocation="";
                                     //if (unit.equals("FBAD")) {
                                     //   mlocation = "100";
                                    //} else if (unit.equals("7")) {
                                    //    mlocation = "200";
                                    //} else {
                                    //    mlocation = "210";
                                    //}

                                    if (vser.equals("FBP") || vser.equals("NBP") || vser.equals("DBP"))
                                        mlocation="100";
                                    else if(vser.equals("BPB"))
                                       mlocation="200";
                                    else if(vser.equals("TBP"))
                                       mlocation="210";
                                    else
                                      mlocation="999";

                                    InitialContext initialContext = new InitialContext();
                                    JasperPrint print = JasperFillManager.fillReport(report, parameters, conn1);

                                    OutputStream output = new FileOutputStream(new File(path1 + "payments/" + mlocation + "/" + year.trim() + "/" + vser.trim() + vono.trim() + ".pdf"));
                                    JasperExportManager.exportReportToPdfStream(print, output);
                                    output.close();*/
                                }
                            }
                        }
                        conn.commit();
                        conn1.commit();
                        connora.commit();
                //*************
                        
              /*  MailProvider mailProvider = new MailProvider();                
                // mail status
                PreparedStatement findutr_stat = null;
                PreparedStatement mailtoStat=null;
                ResultSet resultutr = null;
                //ResultSet result1 = null;
                ResultSet mailtoResult = null;
                String ccAddress = null;
               // String sehuser = null;
                
                String subjecttitle="";
                String messageBodyText=" ";
                String frommail="";
                String fromname="";
                String []tomail=new String[2];
                String toname="";

                if (unit.equals("FBAD")) {
                    ccAddress = "kuldeep.anandsingh@shahi.co.in";
                } else {
                    ccAddress = "kuldeep.anandsingh@shahi.co.in";
                }

                findutr_stat = connora.prepareStatement("select mssuno,chq_utr_no,amount,divi,year,vser,vono from shahiportal.sup_pay_mast where divi=? and year=? and vser=? and vono=?");
                findutr_stat.setString(1, division);
                findutr_stat.setString(2, year);
                findutr_stat.setString(3, vser);
                findutr_stat.setString(4, vono);
                resultutr = findutr_stat.executeQuery();
                String utrno = "";               
                double chqamt=0;    
                String suno="";
                if (resultutr.next()) {
                    suno=resultutr.getString("mssuno");
                    utrno=resultutr.getString("chq_utr_no");
                    chqamt=resultutr.getDouble("amount");
                }
             //   System.out.println(suno);
               
                mailtoStat = connora.prepareStatement("select mssuno,idsunm,max(decode(length(nvl(mobile,idphno)),10,'0'||nvl(mobile,idphno),nvl(mobile,idphno))) mobile  from shahiportal.supplier a,shahiportal.SUPPLIER_PROFILE_USER_PAYMBAB8 b where a.mssuno=? and a.id=b.supplier_id(+) group by mssuno,idsunm");
                mailtoStat.setString(1,suno.trim());
                mailtoResult = mailtoStat.executeQuery();
                String supname="";
                String supmob="";
                if (mailtoResult.next()) {
                    supname=mailtoResult.getString("idsunm");
                    supmob=mailtoResult.getString("mobile");
                    tomail[0] = mailtoResult.getString("mobile").trim()+".shahiit@smscountry.net";
                   // sehuser = mailtoResult.getString("FULL_NAME");
                }
                tomail[1]="manish.gattani@shahi.co.in";
              //  System.out.println(supname);
                NumberFormat formatter = new DecimalFormat("#0.00");
                //tomail[1]="kuldeep.anandsingh@shahi.co.in";
                frommail = "it.alerts@shahi.co.in";
                fromname = "AutoMail@Shahi";
             //   System.out.println(tomail+frommail+fromname);
                subjecttitle = " Payment Alert " + supname;
                messageBodyText += "** PAYMENT ALERT **" ;
                messageBodyText += "\n";
                messageBodyText += "Remitted to M/s "+supname+" Rs. "+formatter.format(chqamt)+" Vide UTR No. "+utrno+" A/c No.xxxxxxx IFSC xxxxxx" ;
                messageBodyText += "\n";
                messageBodyText += "Pls refer portal for details.";
                
                mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null); */

                } 

               } 
                                                   
            }
         //   conn.commit();
         //   conn1.commit();
            
            
        } catch (Exception e) {
            try {

                conn.rollback();
                conn1.rollback();

            } catch (Exception ee) {
                System.out.print("1 File Name : UtrUpdationAction.java" + ee);

                System.out.println(ee.toString());
            }
            System.out.print("1 File Name : UtrUpdationAction.java" + e);

            System.out.println(e.toString());
        } finally {

            try {

                if (stat != null) {
                    stat.close();
                }

                if (conn != null) {
                    conn.close();
                }
                if (conn1 != null) {
                    conn1.close();
                }
                if (connora != null) {
                    connora.close();
                } 
                conn = null;
                connora = null;
                conn1 = null;

            } catch (Exception e) {
                System.out.print("File Name : UtrUpdationAction.java Exception in finally block");
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }

    private static void callOracleStoredProcINParameter(String DIVI,String YEAR,String VSER,String VONO, String CHNO,String REMARKS) throws SQLException {

        Connection dbConnection = null;
        CallableStatement callableStatement = null;

        String insertStoreProc = "{call FINACBI.PORTALPYMT_UPLOAD(?,?,?,?,?,?)}";

        try {
            dbConnection = new connectionMovexBi().getConnection();
            callableStatement = dbConnection.prepareCall(insertStoreProc);

            callableStatement.setString(1, DIVI);
            callableStatement.setString(2, YEAR);
            callableStatement.setString(3, VSER);
            callableStatement.setString(4, VONO);
            callableStatement.setString(5, CHNO);
            callableStatement.setString(6, REMARKS);

            // execute insertDBUSER store procedure
            callableStatement.executeUpdate();

            //System.out.println("Record is inserted into DBUSER table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (callableStatement != null) {
                callableStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }



    public String getBSUNO() {
        return BSUNO;
    }

    public void setBSUNO(String BSUNO) {
        this.BSUNO = BSUNO;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getPyear() {
        return pyear;
    }

    public void setPyear(String pyear) {
        this.pyear = pyear;
    }

    public List getDETAIL_LIST() {
        return DETAIL_LIST;
    }

    public void setDETAIL_LIST(List DETAIL_LIST) {
        this.DETAIL_LIST = DETAIL_LIST;
    }

    public String[] getChkdel() {
        return chkdel;
    }

    public void setChkdel(String[] chkdel) {
        this.chkdel = chkdel;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }
}
