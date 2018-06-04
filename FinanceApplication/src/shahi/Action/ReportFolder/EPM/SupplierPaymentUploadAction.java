package shahi.Action.ReportFolder.EPM;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.ReportFolder.EPM.beans.CbnacBean;
import java.util.Date;
import java.util.HashMap;
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
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.ReportFolder.EPM.beans.SupplierPaymentUploadBean;
import shahi.Action.database.connectionShahiPortal;
import shahi.Action.database.connectiondb2;
import shahi.Action.database.connectionMovexBi;
import shahi.Action.database.connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Vivek
 */
public class SupplierPaymentUploadAction extends ActionSupport {

    private String aausrid;
    private List bankcodelist;
    ResourceBundle aResourcBundle = null;
    //execute
    private String DIVI;
    private String YEAR;
    private String CKBKID;
    private String CKDTPRF;
    private String CKDTPRT;
    private String CKCHKNF;
    private String CKCHKNT;
    private List detaillist;
    private String[] chkdel;
    private String[] CHEQUE_NEW;
    private String[] REMARKS_NEW;

    public SupplierPaymentUploadAction() {
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
    }

    private String getValue(String key) {
        return aResourcBundle.getString(key);
    }

    private int getIntValue(String key) {
        return Integer.parseInt(getValue(key));
    }

    public String viewpage() throws Exception{
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String unit = (String) session.get("sessUnitCode");
        if (usrid == null && unit == null) {
            Connection conn=null;
            PreparedStatement stat = null;
            ResultSet result = null;
            try {
                conn = new connection().getConnection();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            try {
                stat = conn.prepareStatement("select emp_code,EMP_NAME,LOCATION_CODE,UNIT_CODE from shahi_connect_view where emp_code=?");
                stat.setString(1, aausrid);
                result = stat.executeQuery();
                if (result.next()) {
                    session.put("sessUserName", result.getString("EMP_NAME"));
                    session.put("sessUserId", result.getString("emp_code"));
                    session.put("sessLocationCode", result.getString("LOCATION_CODE"));
                    session.put("sessUnitCode", result.getString("UNIT_CODE"));
                }
            } catch (Exception e) {
                System.out.println("SupplierPaymentUploadAction " + e);
            }
            finally{
                if(conn!=null){
                    conn.close();
                }
            }
        }
        getAllBankCode();
        return SUCCESS;
    }
    public void getAllBankCode() throws Exception{
        chkdel = null;
        bankcodelist = new ArrayList();
        Connection connbi = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            connbi = new connectiondb2().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            stat = connbi.prepareStatement("select distinct BCBKID,BCBANA from m3fdbprd.CBANAC where BCBKTP=1 order by 1");
            result = stat.executeQuery();
            while (result.next()) {
                bankcodelist.add(new CbnacBean(result.getString("BCBKID"), result.getString("BCBANA")));
            }
        } catch (Exception e) {
            System.out.println("SupplierPaymentUploadAction " + e);
        }
        finally{
            if(connbi!=null){
                connbi.close();
            }
        }
    }
    @Override
    public String execute() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        String fromDate = null;
        String toDate = null;
        String fromchkno = null;
        String tochkno = null;
        String datesearch = "";
        String chknosearch = "";
        detaillist = new ArrayList();
        if (CKDTPRF != null && CKDTPRF.length() > 0) {
            Date date = dateFormat.parse(CKDTPRF);
            fromDate = dateFormat1.format(date);
        }
        if (CKDTPRT != null && CKDTPRT.length() > 0) {
            Date date = dateFormat.parse(CKDTPRT);
            toDate = dateFormat1.format(date);
        }
        if (fromDate != null && toDate != null) {
            datesearch = "  AND CKCKDT BETWEEN " + fromDate + " AND " + toDate;
        }
        if(CKCHKNF !=null && CKCHKNF.length()>0){
            fromchkno = CKCHKNF.trim();
            for(int i=CKCHKNF.length();i<15;i++){
                fromchkno = "0"+fromchkno;
            }
        }
        if(CKCHKNT !=null && CKCHKNT.length()>0){
            tochkno = CKCHKNT.trim();
            for(int i=CKCHKNT.length();i<15;i++){
                tochkno = "0"+tochkno;
            }
        }
        if (fromchkno != null && tochkno != null) {
            chknosearch = "  AND CKCHKN BETWEEN '" + fromchkno + "' AND '" + tochkno+"'";
        }
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        if (usrid == null) {
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        Connection connbi = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        try {
            conn = new connection().getConnection();
            connbi = new connectiondb2().getConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            //System.out.println("SELECT CKDIVI,CKYEA4,CKSPYN,CKSUNM,CKVSER,CKVONO,CKDTPR,CKCHKN FROM MVXCDTPROD.FAPCHK WHERE CKCONO='111' AND CKDIVI="+DIVI+" AND CKYEA4="+YEAR+" AND CKBKID="+CKBKID+" "+datesearch + chknosearch);
            stat = connbi.prepareStatement("SELECT a.CKDIVI,a.CKYEA4,a.CKSPYN,IDSUNM CKSUNM,a.CKVSER,a.CKVONO,a.CKCKDT,a.CKCHKN,b.CKCHKN FKCHKN,b.CKAIT1 FROM cinfdbprd.XAPCHK a,m3fdbprd.CIDMAS,m3fdbprd.FAPCHK B "
                    + " WHERE a.CKCONO='111' AND a.CKCONO=IDCONO AND a.CKSPYN=IDSUNO AND a.CKDIVI=? AND a.CKYEA4=?"
                    + " AND a.CKCONO=B.CKCONO AND a.CKDIVI=B.CKDIVI AND a.CKYEA4=B.CKYEA4 AND a.CKVSER=B.CKVSER AND a.CKVONO=B.CKVONO AND B.CKSTTS<3"
                    + " AND a.CKBKID=? AND B.CKVONO<>0 AND B.CKSPYN NOT LIKE 'COMM BONUS%'" + datesearch + chknosearch);
            stat.setString(1, DIVI);
            stat.setString(2, YEAR);
            stat.setString(3, CKBKID);
            result = stat.executeQuery();
            while (result.next()) {
               // stat1 = conn.prepareStatement("select * from shahiportal.sup_pay_mast where  DIVI=? and YEAR=? and VSER=? and VONO=?");
                stat1 = conn.prepareStatement("select * from finac.fa_bank_statement_dummy where comp_id=122 and YEAR=? and VSER=? and VONO=? and  DIVISION=? ");
                stat1.setString(1, result.getString("CKYEA4"));
                stat1.setString(2, result.getString("CKVSER"));
                stat1.setString(3, result.getString("CKVONO"));
                stat1.setString(4, result.getString("CKDIVI"));
                
                result1 = stat1.executeQuery();
                String exist = "";
                String vremark="";
                if (result1.next()) {
                    vremark=result1.getString("chq_desc");
                    exist = "OK";
                } else {
                    exist = "NOT-OK";
                }
                detaillist.add(new SupplierPaymentUploadBean(result.getString("CKDIVI"), result.getString("CKYEA4"), result.getString("CKSPYN"), result.getString("CKSUNM"), result.getString("CKVSER"), result.getString("CKVONO"), result.getString("CKCKDT"), result.getString("FKCHKN"), exist,result.getString("CKAIT1"),vremark));
            }
        } catch (Exception e) {
            System.out.println("SupplierPaymentUploadAction " + e);
        } finally {
            if (connbi != null) {
                connbi.close();
            }
            if(conn!=null){
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

    public String save() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
        String loct = (String) session.get("sessLocationCode");
        String unit = (String) session.get("sessUnitCode");
        PreparedStatement utrstat = null;
      //  ResultSet utrresult = null;
        
        if(usrid == null){
            addActionError("Session Not Valid!!!");
            return ERROR;
        }
        if (chkdel != null && chkdel.length > 0) {
            for (int i = 0; i < chkdel.length; i++) {
                if (chkdel[i] != null && !chkdel[0].equals("false")) {
                    Connection conn = null;
                    Connection connprodbi = null;
                    Connection connora = null;
                    Connection conn2 = null;
                    
                    try {
                        connprodbi = new connectionMovexBi().getConnection();
                        connora = new connection().getConnection();
                        conn = new connectiondb2().getConnection();
                        conn.setAutoCommit(false);
                        conn2 = new connectionShahiPortal().getConnection();
                        conn2.setAutoCommit(false);
                        
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    try {
                        String year = chkdel[i].substring(0, chkdel[i].lastIndexOf("-"));
                        String vser = chkdel[i].substring(chkdel[i].lastIndexOf("-") + 1, chkdel[i].lastIndexOf(":"));
                        String vono = chkdel[i].substring(chkdel[i].lastIndexOf(":") + 1, chkdel[i].lastIndexOf("%"));
                        String CHQ_NO = chkdel[i].substring(chkdel[i].lastIndexOf("%") + 1, chkdel[i].lastIndexOf("$"));
                        String division = chkdel[i].substring(chkdel[i].lastIndexOf("$") + 1, chkdel[i].lastIndexOf("@"));
                        String bkcode = chkdel[i].substring(chkdel[i].lastIndexOf("@") + 1, chkdel[i].length());
                        if (year.length() > 0 && vser.length() > 0 && vono.length() > 0 && CHQ_NO.length() > 0) {    
                            String status="0";
                            if(!CHQ_NO.trim().equals(CHEQUE_NEW[i])){
                            APZ300MI MI = new APZ300MI();
                            MI.connect();
                            status = MI.UpdateChkNoNew(CHEQUE_NEW[i], "111", division, year, vser, vono, CKBKID);  
                            
                            MI.destroyMI();
                            MI = null;
                            }
                            else{
                                status="1";
                            }
                            if (status.equals("1")) {
                                Thread.sleep(500);
                                /*PreparedStatement fetchstat1 = conn.prepareStatement("select a.EGCONO,a.EGDIVI,a.EGYEA4,a.EGJRNO,a.EGJSNO,a.EGAIT1,b.ERSTMN"
                                        + " from mvxcdtprod.fgledg a,mvxcdtprod.ferech b"
                                        + " where a.egcono=111 and a.egdivi=? and a.egyea4=? and a.egait1=? and a.egvser=? and a.egvono=?"
                                        + " and a.egcono=b.ercono and a.egdivi=b.erdivi and a.egait1=b.erait1");
                                fetchstat1.setString(1, division.trim());
                                fetchstat1.setString(2, year.trim());
                                fetchstat1.setString(3, bkcode.trim());
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
                                    addActionError("Error:- Create Bank Statement in GLS 181");
                                    return ERROR;
                                }*/
                              //  System.out.println(division+year+vser+vono+bkcode+CHQ_NO+usrid);
                                 int statusres = 0;
                                utrstat = conn2.prepareStatement("insert into finac.fa_bank_statement_dummy (comp_id,division,year,vser,vono,bank_code,chq_no,utr_flag,utr_date,seh_user,chq_desc) values (122,?,?,?,?,?,?,1,sysdate,?,?)");
                                utrstat.setString(1, division.trim());
                                utrstat.setString(2, year.trim());
                                utrstat.setString(3, vser.trim());
                                utrstat.setString(4, vono.trim());
                                utrstat.setString(5, bkcode.trim());
                                utrstat.setString(6, CHEQUE_NEW[i]);
                                utrstat.setString(7, usrid.trim());
                                utrstat.setString(8, REMARKS_NEW[i].trim());
                              
                                statusres = utrstat.executeUpdate();
                                if (statusres > 0) {
                                 addActionError("Check Updated 2 " + CHQ_NO);
                                }
                               /* callOracleStoredProcINParameter(division, year, vser, vono, CHEQUE_NEW[i],REMARKS_NEW[i]);
                                String path1 = getValue("reportPdfPath");
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
                                JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);

                                OutputStream output = new FileOutputStream(new File(path1 + "payments/" + mlocation + "/" + year.trim() + "/" + vser.trim() + vono.trim() + ".pdf"));
                                JasperExportManager.exportReportToPdfStream(print, output);
                                output.close();*/
                           // }
                            }
                            
                        }
                        conn.commit();
                        conn2.commit();
                        //*************
                        
                MailProvider mailProvider = new MailProvider();                
                // mail status
                PreparedStatement findutr_stat = null;
                PreparedStatement mailtoStat=null;
                ResultSet resultutr = null;
                //ResultSet result1 = null;
                ResultSet mailtoResult = null;
                String ccAddress = null;
               // String sehuser = null;
                conn = new connectiondb2().getConnection();
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
                findutr_stat = conn.prepareStatement("select ckspyn mssuno,ckchkn chq_utr_no,ckpycu amount,ckdivi divi,ckyea4 year1,ckvser vser,ckvono vono from m3fdbprd.fapchk where ckcono=111 and ckdivi=? and ckyea4=? and ckvser=? and ckvono=?");
               // findutr_stat = connora.prepareStatement("select mssuno,chq_utr_no,amount,divi,year,vser,vono from shahiportal.sup_pay_mast where divi=? and year=? and vser=? and vono=?");
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
               
                mailtoStat = connora.prepareStatement("select mssuno,idsunm,max(decode(length(nvl(mobile,idphno)),10,'0'||nvl(mobile,idphno),nvl(mobile,idphno))) mobile  from shahiportal.supplier a,shahiportal.SUPPLIER_PROFILE_USER_PAYMBAB8 b where a.idsuno=? and a.id=b.supplier_id(+) group by mssuno,idsunm"); 
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
                
                if (division.equals("500"))
                {
                 tomail[1]="pradeep.ramachandra@shahi.co.in";
                    }else if (division.equals("200"))
                    {
                    tomail[1]="vilas.sadarjoshi@shahi.co.in";    
                    }else
                    {tomail[1]="rashmi.batheja@shahi.co.in";}
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
                
                mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                
                                               
                    } catch (Exception e) {
                        conn.rollback();
                        System.out.println("SupplierPaymentUploadAction.java " + e);
                        addActionError("SupplierPaymentUploadAction.java " + e);
                    } finally {
                        if (conn != null) {
                            conn.close();
                        }  
                        if (conn2 != null) {
                            conn2.close();
                        } 
                        if (connora != null) {
                            connora.close();
                        }  
                        if (connprodbi != null) {
                            connprodbi.close();
                        }  
                    }
                }
            }
        }
        getAllBankCode();
        return SUCCESS;
    }

    private static void callOracleStoredProcINParameter(String DIVI, String YEAR, String VSER, String VONO, String CHNO,String REMARKS) throws SQLException {

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

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public List getBankcodelist() {
        return bankcodelist;
    }

    public void setBankcodelist(List bankcodelist) {
        this.bankcodelist = bankcodelist;
    }

    public String getCKBKID() {
        return CKBKID;
    }

    public void setCKBKID(String CKBKID) {
        this.CKBKID = CKBKID;
    }

    public String getCKCHKNF() {
        return CKCHKNF;
    }

    public void setCKCHKNF(String CKCHKNF) {
        this.CKCHKNF = CKCHKNF;
    }

    public String getCKCHKNT() {
        return CKCHKNT;
    }

    public void setCKCHKNT(String CKCHKNT) {
        this.CKCHKNT = CKCHKNT;
    }

    public String getCKDTPRF() {
        return CKDTPRF;
    }

    public void setCKDTPRF(String CKDTPRF) {
        this.CKDTPRF = CKDTPRF;
    }

    public String getCKDTPRT() {
        return CKDTPRT;
    }

    public void setCKDTPRT(String CKDTPRT) {
        this.CKDTPRT = CKDTPRT;
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

    public List getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(List detaillist) {
        this.detaillist = detaillist;
    }

    public String[] getChkdel() {
        return chkdel;
    }

    public void setChkdel(String[] chkdel) {
        this.chkdel = chkdel;
    }

    public String[] getCHEQUE_NEW() {
        return CHEQUE_NEW;
    }

    public void setCHEQUE_NEW(String[] CHEQUE_NEW) {
        this.CHEQUE_NEW = CHEQUE_NEW;
    }

    public String[] getREMARKS_NEW() {
        return REMARKS_NEW;
    }

    public void setREMARKS_NEW(String[] REMARKS_NEW) {
        this.REMARKS_NEW = REMARKS_NEW;
    }
}
