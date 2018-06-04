package shahi.Action.FundReq;

import shahi.Action.FundReq.Beans.FundReqDelvBean;

import com.opensymphony.xwork2.ActionSupport;

import shahi.Action.database.connectiondb2;

import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.opensymphony.xwork2.ActionContext;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import shahi.Action.FundReq.Beans.FaPayrollTypeMastBean;

import shahi.Action.FundReq.Beans.ForwardListBean;
import shahi.Action.FundReq.Beans.FundReqdtlBean;
import shahi.Action.FundReq.Beans.PoDetailBean;
import shahi.Action.FundReq.Beans.REQDELVBean;
import shahi.Action.FundReq.Beans.RemarkBean;
import shahi.Action.FundReq.Beans.SupplierBean;
import shahi.Action.MI.Beans.APS106MIGetBean;
import shahi.Action.MI.Beans.CRS620MIGetBasicDataBean;
import shahi.Action.MI.Beans.PPS200MILstHeadBean;
import shahi.Action.MI.CRS620MI;
import shahi.Action.MI.PPS200MI;
import shahi.Action.MailProvider.MailProvider;
import shahi.Action.database.ConnectionDB2HR;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.util.HelperConstantsFnl;

public class FundRequestNewAction extends ActionSupport {

    private String aausrid;
    private String saveFlag;
    private String acauth;
    private String reqno;
    private String reqdt;
    private String reqsuno;
    private String reqtxt;
    private String reqtyp;
    private String reqsts;
    private String payloct;
    private String dlvplc;
    private String reqpino;
    private String ponumb;
    private Date reqchqdt;
    private Date reqpdcdt;
    private Date podate;
    private String reqempcd;
    private String reqbkno;
    private String mvxpaid;
    private String reqdisc;
    private String reqchq;
    private String chqdelv;
    private String partyname;
    private String reqpototalamt;
    private List showReqdtl;
    private List showReqdlv;
    private String newinsert = "YES";
    private String buyer;
    private List buyerlist;
    private List podetaillst;
    private String IDPUNOS;
    private String SUP_CODE;
    private String locindex;
    private List reqpono;
    private List reqpodt;
    private List rpoamt;
    private List reqpoamt;
    private List delvdt;
    private List pcddt;
    private List delqty;
    private List REQDELV;
    private List reqpost;
    private List forwardlist;
    private String textid;
    private String reqpodttemp;
    private String rpoamttemp;
    private String advamttemp;
    private String reqpoamttemp;
    private String reqratetemp;
    private String srvtaxtemp;
    private String FORWARDTO;
    private String REMARKS;
    private String MESSAGE;
    private String RQCHID;
    private List REMARKS_LIST;
    private List<FaPayrollTypeMastBean> reqtyplist;
    private List<FaPayrollTypeMastBean> payloctlist;
    private List<FaPayrollTypeMastBean> divilist;
    private String ENTRYID;
    private String ENTRYUSER;
    private String ENTRYSRNO;
    private String REJECTSTATUS;
    private String CHQCHID;
    private File sketchPdf;
    private String sketchPdfContentType;
    private String sketchPdfFileName;
    private String chkpoatt;
    private String recupld;
    private String FILE_NAME;
    private String uploadid;
    private String reqbal;
    private List reqrate;
    private List srvtax;
    private Date MIN_DATE;
    private Date MAX_DATE;
    private double CHGPCT;
    private String paydivi;
    private String cancelreq;
    private String CANFLAG;

    public FundRequestNewAction() {
        super();
    }

    public String attach() throws IOException, SQLException {
        FILE_NAME = "";
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
        String path = sc.getRealPath("/shahiwebpages/FundReq/upload/");
        File uploadDir = new File(sc.getRealPath("/shahiwebpages/FundReq/upload/"));
        Connection connbi = null;

        PreparedStatement stat = null;
        ResultSet result = null;
        if (uploadDir.exists() == false) {
            uploadDir.mkdirs();
        }
        try {
            connbi = new ConnectionShahiHrisNew().getConnection();
            connbi.setAutoCommit(false);

            if (sketchPdf != null && recupld != null && recupld.equals("save") && usrid.equals(RQCHID) && reqsts != null && reqsts.equals("1")) {
                String ext = this.sketchPdfFileName.substring(this.sketchPdfFileName.lastIndexOf("."));
                this.sketchPdfFileName = reqno + "IMG" + chkpoatt + ext;
                File fileToCreate = new File(uploadDir, this.sketchPdfFileName.toUpperCase());
                FileUtils.copyFile(this.sketchPdf, fileToCreate);
                sketchPdfFileName = sketchPdfFileName.toUpperCase();

                stat = connbi.prepareStatement("Update finacbi.REQDTL set FILE_NAME=? where REQNO=? and  REQPONO=?");
                stat.setString(1, sketchPdfFileName.toUpperCase());
                stat.setString(2, reqno);
                stat.setString(3, chkpoatt);
                int status = stat.executeUpdate();
                if (status == 0) {
                    addActionError("File Not Upload");
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } else if (recupld != null && recupld.equals("save")) {
                addActionError("File Not Upload");
            }
            stat = connbi.prepareStatement("select FILE_NAME from finacbi.REQDTL where REQNO=? and  REQPONO=? and FILE_NAME is not null");
            stat.setString(1, reqno);
            stat.setString(2, chkpoatt);
            result = stat.executeQuery();
            if (result.next()) {
                FILE_NAME = "/ShahiApplication/shahiwebpages/FundReq/upload/" + result.getString("FILE_NAME");
            }
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
        } catch (Exception e) {
            addActionError("file name : FundRequestNewAction.java-Attach" + e.toString());
            System.out.print("file name : FundRequestNewAction.java-Attach" + e.toString());
        } finally {
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
            if (connbi != null) {
                connbi.close();
                connbi = null;
            }
        }
        return "attach";
    }

    public String attach1() throws IOException, SQLException {
        FILE_NAME = "";
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
        String path = sc.getRealPath("/shahiwebpages/FundReq/upload/");
        File uploadDir = new File(sc.getRealPath("/shahiwebpages/FundReq/upload/"));
        Connection connbi = null;

        PreparedStatement stat = null;
        ResultSet result = null;
        if (uploadDir.exists() == false) {
            uploadDir.mkdirs();
        }
        try {
            connbi = new ConnectionShahiHrisNew().getConnection();
            connbi.setAutoCommit(false);

            if (sketchPdf != null && recupld != null && recupld.equals("save") && usrid.equals(RQCHID) && reqsts != null && reqsts.equals("1")) {
                String ext = this.sketchPdfFileName.substring(this.sketchPdfFileName.lastIndexOf("."));
                this.sketchPdfFileName = reqno + "PI" + chkpoatt + ext;
                File fileToCreate = new File(uploadDir, this.sketchPdfFileName.toUpperCase());
                FileUtils.copyFile(this.sketchPdf, fileToCreate);
                sketchPdfFileName = sketchPdfFileName.toUpperCase();

                stat = connbi.prepareStatement("Update finacbi.REQDTL set PI_NAME=? where REQNO=? and  REQPONO=?");
                stat.setString(1, sketchPdfFileName.toUpperCase());
                stat.setString(2, reqno);
                stat.setString(3, chkpoatt);
                int status = stat.executeUpdate();
                if (status == 0) {
                    addActionError("File Not Upload");
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } else if (recupld != null && recupld.equals("save")) {
                addActionError("File Not Upload");
            }
            stat = connbi.prepareStatement("select PI_NAME from finacbi.REQDTL where REQNO=? and  REQPONO=? and PI_NAME is not null");
            stat.setString(1, reqno);
            stat.setString(2, chkpoatt);
            result = stat.executeQuery();
            if (result.next()) {
                FILE_NAME = "/ShahiApplication/shahiwebpages/FundReq/upload/" + result.getString("PI_NAME");
            }
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
        } catch (Exception e) {
            addActionError("file name : FundRequestNewAction.java-Attach" + e.toString());
            System.out.print("file name : FundRequestNewAction.java-Attach" + e.toString());
        } finally {
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
            if (connbi != null) {
                connbi.close();
                connbi = null;
            }
        }
        return "attach1";
    }

    public String attach2() throws IOException, SQLException {
        FILE_NAME = "";
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
        String path = sc.getRealPath("/shahiwebpages/FundReq/upload/");
        File uploadDir = new File(sc.getRealPath("/shahiwebpages/FundReq/upload/"));
        Connection connbi = null;

        PreparedStatement stat = null;
        ResultSet result = null;
        if (uploadDir.exists() == false) {
            uploadDir.mkdirs();
        }
        try {
            connbi = new ConnectionShahiHrisNew().getConnection();
            connbi.setAutoCommit(false);

            if (sketchPdf != null && recupld != null && recupld.equals("save") && reqsts != null && !reqsts.equals("1")) {
                String ext = this.sketchPdfFileName.substring(this.sketchPdfFileName.lastIndexOf("."));
                this.sketchPdfFileName = reqno + "FIN" + chkpoatt + ext;
                File fileToCreate = new File(uploadDir, this.sketchPdfFileName.toUpperCase());
                FileUtils.copyFile(this.sketchPdf, fileToCreate);
                sketchPdfFileName = sketchPdfFileName.toUpperCase();

                stat = connbi.prepareStatement("Update finacbi.REQDTL set FIN_NAME=? where REQNO=? and  REQPONO=?");
                stat.setString(1, sketchPdfFileName.toUpperCase());
                stat.setString(2, reqno);
                stat.setString(3, chkpoatt);
                int status = stat.executeUpdate();
                if (status == 0) {
                    addActionError("File Not Upload");
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } else if (recupld != null && recupld.equals("save")) {
                addActionError("File Not Upload");
            }
            stat = connbi.prepareStatement("select FIN_NAME from finacbi.REQDTL where REQNO=? and  REQPONO=? and FIN_NAME is not null");
            stat.setString(1, reqno);
            stat.setString(2, chkpoatt);
            result = stat.executeQuery();
            if (result.next()) {
                FILE_NAME = "/ShahiApplication/shahiwebpages/FundReq/upload/" + result.getString("FIN_NAME");
            }
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
        } catch (Exception e) {
            addActionError("file name : FundRequestNewAction.java-Attach" + e.toString());
            System.out.print("file name : FundRequestNewAction.java-Attach" + e.toString());
        } finally {
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
            if (connbi != null) {
                connbi.close();
                connbi = null;
            }
        }
        return "attach2";
    }

    @Override
    public String execute() {

        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        //usrid = "227350";
        //LOCATION_CODE = "100";
        int flag = 0;
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        Connection conn = null;
        Connection connbi = null;
        Connection conndb2 = null;

        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        PreparedStatement stat3 = null;
        PreparedStatement stat4 = null;
        ResultSet result1 = null;
        ResultSet result2 = null;
        ResultSet result3 = null;
        ResultSet result4 = null;
        ResultSet result = null;
        showReqdtl = new ArrayList();
        reqtyplist = new ArrayList<FaPayrollTypeMastBean>();
        payloctlist = new ArrayList<FaPayrollTypeMastBean>();
        divilist = new ArrayList<FaPayrollTypeMastBean>();

        try {
            conn = new ConnectionSeplWeb().getConnection();
            connbi = new ConnectionShahiHrisNew().getConnection();
            conndb2 = new connectiondb2().getConnection();
            if (reqpdcdt != null) {
                MIN_DATE = reqpdcdt;
                Calendar cal = Calendar.getInstance();
                cal.setTime(MIN_DATE);
                cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + 6));
                MAX_DATE = cal.getTime();
            } else {
                reqpdcdt = new Date();
                MIN_DATE = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(MIN_DATE);
                cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) + 6));
                MAX_DATE = cal.getTime();
            }
            if (reqempcd != null) {
            } else {
                reqempcd = usrid;
            }
            if (reqbkno != null) {
            } else {
                stat4 = conn.prepareStatement("select e_mail,MOBILE_NO from shahiweb.seh_Web_users where user_id=? ");
                stat4.setString(1, usrid);
                result4 = stat4.executeQuery();
                while (result4.next()) {
                    reqbkno = result4.getString("MOBILE_NO");
                }
                if (result4 != null) {
                    result4.close();
                    result4 = null;
                }
                if (stat4 != null) {
                    stat4.close();
                    stat4 = null;
                }

            }
            stat1 = conn.prepareStatement("select * from pa_auth_mast where prog_name='FUNDREQ' and user_id=? ");
            stat1.setString(1, usrid);
            result1 = stat1.executeQuery();
            while (result1.next()) {
                acauth = "YES";
            }
            if (result1 != null) {
                result1.close();
                result1 = null;
            }
            if (stat1 != null) {
                stat1.close();
                stat1 = null;
            }

            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTP' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                reqtyplist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }

            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTO' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                payloctlist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }

            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQDV' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                divilist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }

            if (reqno != null && reqno.length() > 0) {
                stat2 = connbi.prepareStatement("select reqno,to_char(reqdt,'dd/mm/yyyy') reqdt,reqsuno,reqtxt,dlvplc,decode(reqtyp,'M','Movex PO','G','General PO','B','BillWise','E','Employee',reqtyp) reqtyp,decode(reqsts,'1','Request Made','2','A/C Accepted','3','Under Process','4','Chq Delivered',REQSTS) reqsts,payloct,reqpino,to_char(reqchqdt,'dd/mm/yyyyy') reqchqdt,to_char(reqpdcdt,'dd/mm/yyyyy') reqpdcdt,rqbknm,mvxpaid,reqdisc,reqchq,chqdelv,reqempcd from finacbi.reqmst a where a.reqno=?");
                stat2.setString(1, reqno);
                result2 = stat2.executeQuery();
                while (result2.next()) {
                    stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                    stat3.setString(1, result2.getString("reqsuno"));
                    result3 = stat3.executeQuery();
                    if (result3.next()) {
                        partyname = result3.getString("idsunm");
                    }
                    if (result3 != null) {
                        result3.close();
                        result3 = null;
                    }
                    if (stat3 != null) {
                        stat3.close();
                        stat3 = null;
                    }

                    reqdt = result2.getString("reqdt");
                    reqsuno = result2.getString("reqsuno");
                    reqtxt = result2.getString("reqtxt");
                    reqsts = result2.getString("reqsts");
                    reqtyp = result2.getString("reqtyp");
                    dlvplc = result2.getString("dlvplc");
                    payloct = result2.getString("payloct");
                    reqpino = result2.getString("reqpino");
                    reqchq = result2.getString("reqchq");
                    reqchqdt = result2.getDate("reqchqdt");
                    reqpdcdt = result2.getDate("reqpdcdt");
                    reqbkno = result2.getString("rqbknm");
                    mvxpaid = result2.getString("mvxpaid");
                    reqdisc = result2.getString("reqdisc");
                    chqdelv = result2.getString("chqdelv");
                    reqempcd = result2.getString("reqempcd");
                }
                if (result2 != null) {
                    result2.close();
                    result2 = null;
                }
                if (stat2 != null) {
                    stat2.close();
                    stat2 = null;
                }

                double advamt = 0;
                stat1 = connbi.prepareStatement("select reqpost,reqpono,to_char(reqpodt,'dd/mm/yyyy') reqpodt,rpoamt,reqpoamt,srvtax from finacbi.REQDTL where reqno=? order by 2");
                stat1.setString(1, reqno);
                result1 = stat1.executeQuery();
                showReqdlv = new ArrayList();
                while (result1.next()) {
                    stat = connbi.prepareStatement("select sum(nvl(reqpoamt,0)) advpaid from finacbi.REQDTL where reqpono=? and reqno<>? ");
                    stat.setString(1, result1.getString("reqpono"));
                    stat.setString(2, reqno);
                    result = stat.executeQuery();
                    while (result.next()) {
                        advamt = result.getDouble("advpaid");
                    }
                    if (result != null) {
                        result.close();
                        result = null;
                    }
                    if (stat != null) {
                        stat.close();
                        stat = null;
                    }

                    stat2 = connbi.prepareStatement("select dlrqpo,delqty,to_char(delvdt,'dd/mm/yyyy') delvt,to_char(pcddt,'dd/mm/yyyy') pcddt from finacbi.reqdelv where dlrqno=? and dlrqpo=? order by 1");
                    stat2.setString(1, reqno);
                    stat2.setString(2, result1.getString("reqpono"));
                    result2 = stat2.executeQuery();
                    while (result2.next()) {
                        showReqdlv.add(new FundReqDelvBean(result2.getString("dlrqpo"), result2.getString("delqty"), result2.getString("delvt"), result2.getString("pcddt")));
                    }
                    if (result2 != null) {
                        result2.close();
                        result2 = null;
                    }
                    if (stat2 != null) {
                        stat2.close();
                        stat2 = null;
                    }
                    newinsert = "NO";
                    showReqdtl.add(new FundReqdtlBean(result1.getString("reqpost"), result1.getString("reqpono"), result1.getString("reqpodt"), result1.getDouble("rpoamt"), advamt, result1.getInt("reqpoamt"), result1.getDouble("srvtax")));
                }
                if (result1 != null) {
                    result1.close();
                    result1 = null;
                }
                if (stat1 != null) {
                    stat1.close();
                    stat1 = null;
                }

            }

            conn.commit();
            connbi.commit();

        } catch (Exception e) {
            flag = 0;
            try {
                flag = 0;
                conn.rollback();
                connbi.rollback();
            } catch (Exception ee) {
                addActionError("1 file name : FundRequestNewAction.java" + ee.toString());
                System.out.print("1 file name : FundRequestNewAction.java" + ee.toString());
            }
            addActionError("2 file name : FundRequestNewAction.java" + e.toString());
            System.out.print("2 file name : FundRequestNewAction.java" + e.toString());

            System.out.println(e.toString());
        } finally {
            try {
                if (result2 != null) {
                    result2.close();
                    result2 = null;
                }
                if (result1 != null) {
                    result1.close();
                    result1 = null;
                }
                if (stat2 != null) {
                    stat2.close();
                    stat2 = null;
                }
                if (stat1 != null) {
                    stat1.close();
                    stat1 = null;
                }

                if (conn != null) {
                    conn.close();
                    conn = null;
                }
                if (connbi != null) {
                    connbi.close();
                    connbi = null;
                }
                if (conndb2 != null) {
                    conndb2.close();
                    conndb2 = null;
                }

            } catch (Exception e) {
                flag = 0;
                addActionError("3 file name : FundRequestNewAction.java" + e.toString());
                System.out.print("3 file name : FundRequestNewAction.java" + e.toString());
            }
        }
        return SUCCESS;

    }

    public String save() throws SQLException, ParseException {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        //usrid = "237519";
        //LOCATION_CODE = "100";
        int flag = 0;
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        Connection connbi = null;

        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        PreparedStatement stat3 = null;
        ResultSet result = null;
        PreparedStatement stat0 = null;
        ResultSet result0 = null;

        showReqdtl = new ArrayList();

        int vreq = 0;
        try {
            connbi = new ConnectionShahiHrisNew().getConnection();
            connbi.setAutoCommit(false);

            /*  if (reqtyp.equals("M"))
             {
             SimpleDateFormat formatter11 = new SimpleDateFormat("dd-MM-yyyy");
             podate = new Date();
             stat0 = connbi.prepareStatement("select sysdate-90 pdate from dual ");
             result0 = stat.executeQuery();
             if(result0.next()){
             podate=result.getDate("pdate");
             }
             for(int i=0;i<reqpodt.size();i++){
             if(reqpodt.get(i).toString()!=null){
             Date dtt = formatter11.parse(reqpodt.get(i).toString());
             if (dtt.before(podate)) {
             addActionError("Check PO Date below 90 days....");
             }
             }
             }
             }*/
            stat = connbi.prepareStatement("select nvl(max(reqno),0)+1 vreq from  finacbi.reqmst ");
            result = stat.executeQuery();
            while (result.next()) {
                vreq = result.getInt("vreq");
            }
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }

            if (reqpdcdt != null) {
                stat1 = connbi.prepareStatement("insert into finacbi.reqmst(reqdivi,reqno,reqdt,reqtyp,reqsuno,reqtxt,reqchq,reqsts,chqdelv,rqchid,rqrgdt,rqlmdt,dlvplc,rqbknm,payloct,mvxpaid,reqdisc,reqempcd,reqpino,REQREJDT,REQPDCDT) values (?,?,sysdate,?,?,?,?,1,?,?,sysdate,sysdate,?,?,?,?,?,?,?,sysdate,?)");
                stat1.setString(1, paydivi);
                stat1.setInt(2, vreq);
                stat1.setString(3, reqtyp);
                stat1.setString(4, reqsuno);
                stat1.setString(5, reqtxt);
                stat1.setString(6, reqchq);
                stat1.setString(7, chqdelv);
                stat1.setString(8, usrid);
                stat1.setString(9, dlvplc);
                stat1.setString(10, reqbkno);
                stat1.setString(11, payloct);
                stat1.setString(12, mvxpaid);
                stat1.setString(13, reqdisc);
                stat1.setString(14, reqempcd);
                //stat1.setDate(14, new java.sql.Date(reqchqdt.getTime()));
                stat1.setString(15, reqpino);
                stat1.setDate(16, new java.sql.Date(reqpdcdt.getTime()));
                flag = stat1.executeUpdate();
                if (stat1 != null) {
                    stat1.close();
                    stat1 = null;
                }
            } else {
                stat1 = connbi.prepareStatement("insert into finacbi.reqmst(reqno,reqdt,reqtyp,reqsuno,reqtxt,reqchq,reqsts,chqdelv,rqchid,rqrgdt,rqlmdt,dlvplc,rqbknm,payloct,mvxpaid,reqdisc,reqempcd,reqpino,REQREJDT) values (?,sysdate,?,?,?,?,1,?,?,sysdate,sysdate,?,?,?,?,?,?,?,sysdate)");
                stat1.setInt(1, vreq);
                stat1.setString(2, reqtyp);
                stat1.setString(3, reqsuno);
                stat1.setString(4, reqtxt);
                stat1.setString(5, reqchq);
                stat1.setString(6, chqdelv);
                stat1.setString(7, usrid);
                stat1.setString(8, dlvplc);
                stat1.setString(9, reqbkno);
                stat1.setString(10, payloct);
                stat1.setString(11, mvxpaid);
                stat1.setString(12, reqdisc);
                stat1.setString(13, reqempcd);
                //stat1.setDate(14, new java.sql.Date(reqchqdt.getTime()));
                stat1.setString(14, reqpino);
                flag = stat1.executeUpdate();
                if (stat1 != null) {
                    stat1.close();
                    stat1 = null;
                }
            }
            int x = 0;
            if (reqpono != null && reqpono.size() > 0) {
                for (int i = 0; i < reqpono.size(); i++) {
                    if (reqpono.get(i).toString() != null && reqpono.get(i).toString().length() > 0) {
                        stat2 = connbi.prepareStatement("insert into finacbi.REQDTL(reqno,reqpono,reqpodt,RPOAMT,REQPOAMT,RQCHID,RQRGDT,RATEREM,SRVTAX) values (?,?,to_date(?,'dd/mm/yyyy'),?,?,?,sysdate,?,?)");
                        stat2.setInt(1, vreq);
                        stat2.setString(2, reqpono.get(i).toString());
                        stat2.setString(3, reqpodt.get(i).toString());
                        stat2.setString(4, rpoamt.get(i).toString());
                        stat2.setString(5, reqpoamt.get(i).toString());
                        stat2.setString(6, aausrid);
                        stat2.setString(7, reqrate.get(i).toString());
                        stat2.setString(8, srvtax.get(i).toString());
                        int statuc = stat2.executeUpdate();
                        for (int j = 0; j < 10; j++) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            if (delqty.get(x).toString() != null && delqty.get(x).toString().length() > 0 && (pcddt.get(x).toString() != null && pcddt.get(x).toString().length() > 0) && (delvdt.get(x).toString() != null && delvdt.get(x).toString().length() > 0)) {
                                stat3 = connbi.prepareStatement("insert into finacbi.reqdelv (DLRQNO,DLRQPO,DELQTY,DELVDT,PCDDT,RDLRGDT,RDLMDT) values(?,?,?,?,?,sysdate,sysdate)");
                                stat3.setInt(1, vreq);
                                stat3.setString(2, reqpono.get(i).toString());
                                stat3.setString(3, delqty.get(x).toString());
                                stat3.setDate(4, new java.sql.Date(formatter.parse(delvdt.get(x).toString()).getTime()));
                                stat3.setDate(5, new java.sql.Date(formatter.parse(pcddt.get(x).toString()).getTime()));
                                stat3.executeUpdate();
                                if (stat3 != null) {
                                    stat3.close();
                                    stat3 = null;
                                }
                                x++;
                            } else {
                                x++;
                            }
                        }
                        if (statuc > 0) {
                            /*SimpleDateFormat format = new SimpleDateFormat("yyyy");
                             CRS620MI crs620mi = new CRS620MI();
                             crs620mi.connect();
                             CRS620MIGetBasicDataBean basicDataBean=crs620mi.getGetBasicData(HelperConstantsFnl.COMPANY, reqsuno);
                             crs620mi.destroyMI();
                             crs620mi = null;
                            
                             PPS200MI pps200mi = new PPS200MI();
                             pps200mi.connect();
                             PPS200MILstHeadBean getLstHeadBean = pps200mi.LstHeadSingle(HelperConstantsFnl.COMPANY,paydivi, reqpono.get(i).toString(), "12", "99");
                             pps200mi.destroyMI();
                             pps200mi = null;
                            
                             SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                             APS106MI aps106mi = new APS106MI();
                             aps106mi.connect();                               
                             String recFlag=aps106mi.Add(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, vreq+reqsuno, vreq+"", format.format(new Date()), "M3USER", basicDataBean.getCRTP(), "0", basicDataBean.getCUCD(), dateFormat.format(reqpdcdt), getLstHeadBean.getPYME(), getLstHeadBean.getTEPY(), reqpono.get(i).toString(), dateFormat.format(new Date()), dateFormat.format(new Date()), "", "", reqpoamt.get(i).toString());
                             aps106mi.destroyMI();
                             if(recFlag!=null && recFlag.equals("OK")){}
                             else{
                             addActionError(recFlag);
                             connbi.rollback();
                             }*/
                        }
                    }

                }
            }

            connbi.commit();
            reqno = vreq + "";
        } catch (SQLException se) {
            addActionError("4 file name : FundRequestNewAction.java" + se.toString());
            System.out.print("4 file name : FundRequestNewAction.java" + se.toString());
            connbi.rollback();
        } catch (Exception e) {
            addActionError("5 file name : FundRequestNewAction.java" + e.toString());
            System.out.print("5 file name : FundRequestNewAction.java" + e.toString());
            connbi.rollback();
        } finally {
            if (result != null) {
                result.close();
                result = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
            if (stat1 != null) {
                stat1.close();
                stat1 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }
            if (stat3 != null) {
                stat3.close();
                stat3 = null;
            }
            if (connbi != null) {
                connbi.close();
                connbi = null;
            }
        }

        return "update";
    }

    public String update() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        //usrid = "237519";
        //LOCATION_CODE = "100";
        int flag = 0;
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        Connection connbi = null;
        Connection conndb2 = null;
        Connection conndb2hr = null;
        PreparedStatement msstat = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        PreparedStatement stat3 = null;
        PreparedStatement stat4 = null;
        PreparedStatement stat5 = null;
        PreparedStatement stat6 = null;
        PreparedStatement mailtoStat = null;
        PreparedStatement statmailcont = null;
        ResultSet result = null;
        ResultSet result3 = null;
        ResultSet mailtoResult = null;
        ResultSet resultmailcont = null;
        showReqdtl = new ArrayList();
        shahi.Action.MIM4.APZ106MI aps106mi = null;
        CRS620MI crs620mi = null;
        PPS200MI pps200mi = null;
        try {
            connbi = new ConnectionShahiHrisNew().getConnection();
            connbi.setAutoCommit(false);
            conndb2 = new connectiondb2().getConnection();
            conndb2hr = new ConnectionDB2HR().getConnection();

            int x = 0;
            if ((reqsts != null && reqsts.equals("1")) && (reqpono != null && reqpono.size() > 0)) {
                //master update
                if (reqpdcdt != null) {
                    msstat = connbi.prepareStatement("update finacbi.reqmst set REQPINO=?,REQEMPCD=?,MVXPAID=?,REQDISC=?,reqtxt=?,dlvplc=?,reqpdcdt=?  where REQNO=?");
                    msstat.setString(1, reqpino);
                    msstat.setString(2, reqempcd);
                    msstat.setString(3, mvxpaid);
                    msstat.setString(4, reqdisc);
                    msstat.setString(5, reqtxt);
                    msstat.setString(6, dlvplc);
                    msstat.setDate(7, new java.sql.Date(reqpdcdt.getTime()));
                    msstat.setString(8, reqno);
                    msstat.executeUpdate();
                    if (msstat != null) {
                        msstat.close();
                        msstat = null;
                    }
                } else {
                    msstat = connbi.prepareStatement("update finacbi.reqmst set REQPINO=?,REQEMPCD=?,MVXPAID=?,REQDISC=?,reqtxt=?,dlvplc=?  where REQNO=?");
                    msstat.setString(1, reqpino);
                    msstat.setString(2, reqempcd);
                    msstat.setString(3, mvxpaid);
                    msstat.setString(4, reqdisc);
                    msstat.setString(5, reqtxt);
                    msstat.setString(6, dlvplc);
                    msstat.setString(7, reqno);
                    msstat.executeUpdate();
                    if (msstat != null) {
                        msstat.close();
                        msstat = null;
                    }
                }

                //delete po and del detail
                /*stat = connbi.prepareStatement("delete finacbi.REQDTL where reqno=?");
                 stat.setString(1, reqno);
                 stat.executeUpdate();*/
                stat1 = connbi.prepareStatement("delete finacbi.reqdelv where DLRQNO=?");
                stat1.setString(1, reqno);
                stat1.executeUpdate();
                for (int i = 0; i < reqpono.size(); i++) {
                    if (reqpono.get(i).toString() != null && reqpono.get(i).toString().length() > 0) {
                        stat = connbi.prepareStatement("select * from finacbi.REQDTL where reqno=? and reqpono=?");
                        stat.setString(1, reqno);
                        stat.setString(2, reqpono.get(i).toString());
                        ResultSet chkres = stat.executeQuery();
                        if (chkres.next()) {
                            stat2 = connbi.prepareStatement("update finacbi.REQDTL set reqpodt=to_date(?,'dd/mm/yyyy'),RPOAMT=?,REQPOAMT=?,RQCHID=?,RQRGDT=sysdate,SRVTAX=? where reqno=? and reqpono=?");
                            stat2.setString(1, reqpodt.get(i).toString());
                            stat2.setString(2, rpoamt.get(i).toString());
                            stat2.setString(3, reqpoamt.get(i).toString());
                            stat2.setString(4, aausrid);
                            stat2.setString(5, srvtax.get(i).toString());
                            stat2.setString(6, reqno);
                            stat2.setString(7, reqpono.get(i).toString());
                            stat2.executeUpdate();
                            if (stat2 != null) {
                                stat2.close();
                                stat2 = null;
                            }
                        } //insert po and del detail
                        else {
                            stat2 = connbi.prepareStatement("insert into finacbi.REQDTL(reqno,reqpono,reqpodt,RPOAMT,REQPOAMT,RQCHID,RQRGDT,SRVTAX) values (?,?,to_date(?,'dd/mm/yyyy'),?,?,?,sysdate,?)");
                            stat2.setString(1, reqno);
                            stat2.setString(2, reqpono.get(i).toString());
                            stat2.setString(3, reqpodt.get(i).toString());
                            stat2.setString(4, rpoamt.get(i).toString());
                            stat2.setString(5, reqpoamt.get(i).toString());
                            stat2.setString(6, aausrid);
                            stat2.setString(7, srvtax.get(i).toString());
                            stat2.executeUpdate();
                            if (stat2 != null) {
                                stat2.close();
                                stat2 = null;
                            }
                        }
                        if (chkres != null) {
                            chkres.close();
                            chkres = null;
                        }
                        if (stat != null) {
                            stat.close();
                            stat = null;
                        }

                        for (int j = 0; j < 10; j++) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            if (delqty.get(x).toString() != null && delqty.get(x).toString().length() > 0 && (pcddt.get(x).toString() != null && pcddt.get(x).toString().length() > 0) && (delvdt.get(x).toString() != null && delvdt.get(x).toString().length() > 0)) {
                                stat3 = connbi.prepareStatement("insert into finacbi.reqdelv (DLRQNO,DLRQPO,DELQTY,DELVDT,PCDDT,RDLRGDT,RDLMDT) values(?,?,?,?,?,sysdate,sysdate)");
                                stat3.setString(1, reqno);
                                stat3.setString(2, reqpono.get(i).toString());
                                stat3.setString(3, delqty.get(x).toString());
                                stat3.setDate(4, new java.sql.Date(formatter.parse(delvdt.get(x).toString()).getTime()));
                                stat3.setDate(5, new java.sql.Date(formatter.parse(pcddt.get(x).toString()).getTime()));
                                stat3.executeUpdate();
                                if (stat3 != null) {
                                    stat3.close();
                                    stat3 = null;
                                }
                                x++;
                            } else {
                                x++;
                            }
                        }
                    }

                }
                if (reqpost != null && reqpost.size() > 0) {
                    for (int i = 0; i < reqpost.size(); i++) {
                        stat = connbi.prepareStatement("delete finacbi.REQDTL where reqno=? and reqpono=?");
                        stat.setString(1, reqno);
                        stat.setString(2, reqpost.get(i).toString());
                        stat.executeUpdate();
                        stat1 = connbi.prepareStatement("delete finacbi.reqdelv where DLRQNO=? and DLRQPO=?");
                        stat1.setString(1, reqno);
                        stat1.setString(2, reqpost.get(i).toString());
                        stat1.executeUpdate();
                        if (stat1 != null) {
                            stat1.close();
                            stat1 = null;
                        }
                    }
                }

                // Request Status 0 and Forward User is not null
                if (FORWARDTO != null && FORWARDTO.length() > 0 && reqsts != null && reqsts.equals("1")) {
                    String statuschk = "none";
                    PreparedStatement statchk = connbi.prepareStatement("select FILE_NAME from finacbi.reqdtl where REQNO=?");
                    statchk.setString(1, reqno);
                    ResultSet resultchk = statchk.executeQuery();
                    while (resultchk.next()) {
                        if (resultchk.getString("FILE_NAME") != null && resultchk.getString("FILE_NAME").length() > 0) {
                            statuschk = "yes";
                        } else {
                            statuschk = "none";
                        }
                    }
                    if (resultchk != null) {
                        resultchk.close();
                        resultchk = null;
                    }
                    if (statchk != null) {
                        statchk.close();
                        statchk = null;
                    }
                    if (statuschk != null && statuschk.equals("yes")) {
                        int srno = 0;
                        stat4 = connbi.prepareStatement("select nvl(max(SL_NO),0)+1 SL_NO from finacbi.fa_payroll_remarks where REQ_NO=?");
                        stat4.setString(1, reqno);
                        result = stat4.executeQuery();
                        if (result.next()) {
                            srno = result.getInt("SL_NO");
                        }
                        if (result != null) {
                            result.close();
                            result = null;
                        }
                        if (stat4 != null) {
                            stat4.close();
                            stat4 = null;
                        }
                        if (ENTRYSRNO != null && ENTRYSRNO.length() > 0) {
                            srno = Integer.parseInt(ENTRYSRNO);
                            stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where SL_NO=? and REQ_NO=?");
                            stat5.setString(1, FORWARDTO);
                            stat5.setString(2, REMARKS);
                            stat5.setInt(3, srno);
                            stat5.setString(4, reqno);
                            stat5.executeUpdate();
                            if (stat5 != null) {
                                stat5.close();
                                stat5 = null;
                            }
                        } else {
                            stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,FORWORDED_TO,FORWORDED_DATE,REMARK,TDATE) values (?,?,?,?,sysdate,?,sysdate)");
                            stat5.setInt(1, srno);
                            stat5.setString(2, reqno);
                            stat5.setString(3, usrid);
                            stat5.setString(4, FORWARDTO);
                            stat5.setString(5, REMARKS);
                            stat5.executeUpdate();
                            if (stat5 != null) {
                                stat5.close();
                                stat5 = null;
                            }
                        }
                        stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                        stat5.setInt(1, srno + 1);
                        stat5.setString(2, reqno);
                        stat5.setString(3, FORWARDTO);
                        stat5.executeUpdate();
                        if (stat5 != null) {
                            stat5.close();
                            stat5 = null;
                        }

                        stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='2' where REQNO=?");
                        stat6.setString(1, reqno);
                        stat6.executeUpdate();
                        if (stat6 != null) {
                            stat6.close();
                            stat6 = null;
                        }
                        MESSAGE = "Req No is " + reqno + " forwarded - 1.";

                        MailProvider mailProvider = new MailProvider();
                        // mail status

                        String ccAddress = null;
                        String sehuser = null;

                        String subjecttitle = "";
                        String messageBodyText = " ";
                        String frommail = "";
                        String fromname = "";
                        String[] tomail = new String[2];
                        String toname = "";

                        tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                        mailtoStat = connbi.prepareStatement("select EMP_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                        mailtoStat.setString(1, FORWARDTO.trim());
                        mailtoResult = mailtoStat.executeQuery();
                        if (mailtoResult.next()) {
                            tomail[1] = mailtoResult.getString("EMP_EMAIL");
                            sehuser = mailtoResult.getString("EMP_NAME");
                        }
                        if (mailtoResult != null) {
                            mailtoResult.close();
                            mailtoResult = null;
                        }
                        if (mailtoStat != null) {
                            mailtoStat.close();
                            mailtoStat = null;
                        }

                        //  mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                        //                                          +"union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                        mailtoStat = conndb2hr.prepareStatement("select USER_NAME,E_MAIL from "
                                + "(SELECT CARDNO,COLUMN1,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                                + "union "
                                + "select 998819 CARDNO ,998819 COLUMN1 ,'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1) "
                                + "WHERE (CARDNO=? OR COLUMN1=?)");
                        mailtoStat.setString(1, usrid);
                        mailtoStat.setString(2, usrid);
                        mailtoStat.setString(3, usrid);
                        mailtoStat.setString(4, usrid);
                        mailtoResult = mailtoStat.executeQuery();
                        if (mailtoResult.next()) {
                            frommail = mailtoResult.getString("E_MAIL");
                            fromname = mailtoResult.getString("USER_NAME");
                        }
                        if (mailtoResult != null) {
                            mailtoResult.close();
                            mailtoResult = null;
                        }
                        if (mailtoStat != null) {
                            mailtoStat.close();
                            mailtoStat = null;
                        }

                        statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                        statmailcont.setString(1, reqno);
                        resultmailcont = statmailcont.executeQuery();
                        NumberFormat formatter = new DecimalFormat("#0.00");
                        subjecttitle = "Fund Request of Req No  :-" + reqno;
                        messageBodyText = "<html>";
                        messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                        messageBodyText += "<body bgcolor=#95b174>";
                        messageBodyText += "Dear " + sehuser + ",<br/>";
                        messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                        messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                        messageBodyText += "</br></br>";

                        messageBodyText += "</font>";
                        messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                        messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                                + "</tr>";
                        while (resultmailcont.next()) {
                            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                            stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                            stat3.setString(1, resultmailcont.getString("reqsuno"));
                            result3 = stat3.executeQuery();
                            String sunm = "";
                            if (result3.next()) {
                                sunm = result3.getString("idsunm");
                            }
                            if (result3 != null) {
                                result3.close();
                                result3 = null;
                            }
                            if (stat3 != null) {
                                stat3.close();
                                stat3 = null;
                            }
                            messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";
                        }
                        if (resultmailcont != null) {
                            resultmailcont.close();
                            resultmailcont = null;
                        }
                        if (statmailcont != null) {
                            statmailcont.close();
                            statmailcont = null;
                        }
                        messageBodyText += "</table>";
                        messageBodyText += "<table cellpadding='4' width='600'>";
                        messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                        messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                        messageBodyText += "</table>";
                        messageBodyText += "</body>";
                        messageBodyText += "</html>";
                        try {
                            mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                        } catch (Exception e) {
                            System.out.println("FundRequestNewAction " + e.getMessage());
                        }
                    } else {
                        addActionError("File Not Attached");
                        MESSAGE = "File Not Attached";
                    }
                }
                if (cancelreq != null && cancelreq.equals("YES")) {
                    stat = connbi.prepareStatement("UPDATE finacbi.REQMST SET CANFLAG='Y',reqtxt=? WHERE REQNO=?");
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    stat.setString(1, "Cancelled by " + usrid + " on dated " + dateFormat1.format(new Date()));
                    stat.setString(2, reqno);
                    int st = stat.executeUpdate();
                    if (st > 0) {
                        stat1 = connbi.prepareStatement("UPDATE finacbi.REQDTL SET REQPOAMT=0 WHERE REQNO=?");
                        stat1.setString(1, reqno);
                        int st1 = stat1.executeUpdate();
                        if (st1 > 0) {
                            SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("yyyy");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                            //APS106MI aps106mi = new APS106MI();  -- removed on 18/02/2017
                            aps106mi = new shahi.Action.MIM4.APZ106MI();
                            aps106mi.connect(HelperConstantsFnl.COMPANY, paydivi);
                            //reqpono.get(i).toString() + reqno
                            aps106mi.Delete(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqsuno + reqno, reqno, format.format(formatreq.parse(reqdt)));
                            if (aps106mi != null) {
                                aps106mi.destroyMI();
                                aps106mi = null;
                            }
                        }
                    }
                }
            } else if (reqsts != null && reqsts.equals("2")) {//Request Status 2
                if (FORWARDTO != null && FORWARDTO.length() > 0 && reqsts != null && reqsts.equals("2")) {//Request Status 2 and forward user is not null
                    String statuschk = "none";
                    PreparedStatement statchk = connbi.prepareStatement("select FIN_NAME FILE_NAME from finacbi.reqdtl where REQNO=?");
                    statchk.setString(1, reqno);
                    ResultSet resultchk = statchk.executeQuery();
                    while (resultchk.next()) {
                        if (resultchk.getString("FILE_NAME") != null && resultchk.getString("FILE_NAME").length() > 0) {
                            statuschk = "yes";
                        } else {
                            statuschk = "none";
                        }
                    }
                    if (resultchk != null) {
                        resultchk.close();
                        resultchk = null;
                    }
                    if (statchk != null) {
                        statchk.close();
                        statchk = null;
                    }
                    if ((statuschk != null && statuschk.equals("yes")) || (reqtyp != null && !reqtyp.equals("M"))) {
                        if (reqpdcdt != null) {
                            msstat = connbi.prepareStatement("update finacbi.reqmst set reqpdcdt=?  where REQNO=?");
                            msstat.setDate(1, new java.sql.Date(reqpdcdt.getTime()));
                            msstat.setString(2, reqno);
                            msstat.executeUpdate();
                            if (msstat != null) {
                                msstat.close();
                                msstat = null;
                            }
                        }
                        stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                        stat5.setString(1, FORWARDTO);
                        stat5.setString(2, REMARKS);
                        stat5.setString(3, reqno);
                        stat5.setString(4, ENTRYSRNO);
                        stat5.executeUpdate();
                        if (stat5 != null) {
                            stat5.close();
                            stat5 = null;
                        }

                        stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                        stat5.setInt(1, Integer.parseInt(ENTRYSRNO) + 1);
                        stat5.setString(2, reqno);
                        stat5.setString(3, FORWARDTO);
                        stat5.executeUpdate();
                        if (stat5 != null) {
                            stat5.close();
                            stat5 = null;
                        }

                        stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='3',CHQCHID=? where REQNO=?");
                        stat6.setString(1, FORWARDTO);
                        stat6.setString(2, reqno);
                        stat6.executeUpdate();
                        if (stat6 != null) {
                            stat6.close();
                            stat6 = null;
                        }
                        MESSAGE = "1 Req No is " + reqno + " forwarded.";

                        MailProvider mailProvider = new MailProvider();
                        // mail status

                        String ccAddress = null;
                        String sehuser = null;

                        String subjecttitle = "";
                        String messageBodyText = " ";
                        String frommail = "";
                        String fromname = "";
                        String[] tomail = new String[2];
                        String toname = "";

                        tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                        mailtoStat = connbi.prepareStatement("select EMP_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                        mailtoStat.setString(1, FORWARDTO.trim());
                        mailtoResult = mailtoStat.executeQuery();
                        if (mailtoResult.next()) {
                            tomail[1] = mailtoResult.getString("EMP_EMAIL");
                            sehuser = mailtoResult.getString("EMP_NAME");
                        }
                        if (mailtoResult != null) {
                            mailtoResult.close();
                            mailtoResult = null;
                        }
                        if (mailtoStat != null) {
                            mailtoStat.close();
                            mailtoStat = null;
                        }

                        mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");
                        //            +" union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                        //     mailtoStat =conndb2hr.prepareStatement("select USER_NAME,E_MAIL from " +
                        //                     "(SELECT CARDNO,COLUMN1,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) " +
                        //                     "union " +
                        //                     "select 998819 CARDNO ,998819 COLUMN1 ,'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1) " +
                        //                      "WHERE (CARDNO=? OR COLUMN1=?)" );

                        mailtoStat.setString(1, usrid);
                        mailtoStat.setString(2, usrid);
                        //   mailtoStat.setString(3, usrid);
                        //   mailtoStat.setString(4, usrid);
                        mailtoResult = mailtoStat.executeQuery();
                        if (mailtoResult.next()) {
                            frommail = mailtoResult.getString("E_MAIL");
                            fromname = mailtoResult.getString("USER_NAME");
                        }
                        if (mailtoResult != null) {
                            mailtoResult.close();
                            mailtoResult = null;
                        }
                        if (mailtoStat != null) {
                            mailtoStat.close();
                            mailtoStat = null;
                        }

                        statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                        statmailcont.setString(1, reqno);
                        resultmailcont = statmailcont.executeQuery();
                        NumberFormat formatter = new DecimalFormat("#0.00");
                        subjecttitle = "Fund Request of Req No  :-" + reqno;
                        messageBodyText = "<html>";
                        messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                        messageBodyText += "<body bgcolor=#95b174>";
                        messageBodyText += "Dear " + sehuser + ",<br/>";
                        messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                        messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                        messageBodyText += "</br></br>";

                        messageBodyText += "</font>";
                        messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                        messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                                + "</tr>";
                        while (resultmailcont.next()) {
                            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                            stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                            stat3.setString(1, resultmailcont.getString("reqsuno"));
                            result3 = stat3.executeQuery();
                            String sunm = "";
                            if (result3.next()) {
                                sunm = result3.getString("idsunm");
                            }
                            if (result3 != null) {
                                result3.close();
                                result3 = null;
                            }
                            if (stat3 != null) {
                                stat3.close();
                                stat3 = null;
                            }
                            messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";
                        }
                        if (resultmailcont != null) {
                            resultmailcont.close();
                            resultmailcont = null;
                        }
                        if (statmailcont != null) {
                            statmailcont.close();
                            statmailcont = null;
                        }
                        messageBodyText += "</table>";
                        messageBodyText += "<table cellpadding='4' width='600'>";
                        messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                        messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                        messageBodyText += "</table>";
                        messageBodyText += "</body>";
                        messageBodyText += "</html>";
                        try {
                            mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                        } catch (Exception e) {
                            System.out.println("FundRequestNewAction " + e.getMessage());
                        }

                    } else {
                        addActionError("FI Not Attached");
                        MESSAGE = "FI Not Attached";
                    }
                } else if (REJECTSTATUS != null && REJECTSTATUS.equals("Y")) {
                    stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                    stat5.setString(1, RQCHID);
                    stat5.setString(2, REMARKS);
                    stat5.setString(3, reqno);
                    stat5.setString(4, ENTRYSRNO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    int srno = Integer.parseInt(ENTRYSRNO);
                    stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                    stat5.setInt(1, srno + 1);
                    stat5.setString(2, reqno);
                    stat5.setString(3, RQCHID);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                    //1
                    stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='1',REQREJDT=sysdate where REQNO=?");
                    stat6.setString(1, reqno);
                    stat6.executeUpdate();
                    if (stat6 != null) {
                        stat6.close();
                        stat6 = null;
                    }
                    MESSAGE = "Req No is " + reqno + " Rejected.";

                    SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                    /* APS106MI aps106mi = new APS106MI();
                     aps106mi.connect();
                     //reqpono.get(i).toString() +reqno
                     APS106MIGetBean bean=aps106mi.Get(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqsuno+reqno ,  reqno, format.format(formatreq.parse(reqdt)));
                     if(bean.getPPYS()!=null && Integer.parseInt(bean.getPPYS())<6){ 
                     //reqpono.get(i).toString() +reqno
                     aps106mi.Delete(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqsuno+reqno ,  reqno, format.format(formatreq.parse(reqdt)));
                     }
                     else{
                     addActionError("Request already paid!!!");
                     }
                     aps106mi.destroyMI();
                     aps106mi = null;
                     */
                    MailProvider mailProvider = new MailProvider();
                    // mail status

                    String ccAddress = null;
                    String sehuser = null;

                    String subjecttitle = "";
                    String messageBodyText = " ";
                    String frommail = "";
                    String fromname = "";
                    String[] tomail = new String[3];
                    String toname = "";

                    tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                    mailtoStat = connbi.prepareStatement("select EMP_EMAIL,NVL(CC_EMAIL,EMP_EMAIL) CC_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                    mailtoStat.setString(1, RQCHID.trim());
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        tomail[1] = mailtoResult.getString("EMP_EMAIL");
                        tomail[2] = mailtoResult.getString("CC_EMAIL");
                        sehuser = mailtoResult.getString("EMP_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    //   mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE  (CARDNO=? OR COLUMN1=?)"
                    //           + " union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                    mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE  (CARDNO=? OR COLUMN1=?)");

                    mailtoStat.setString(1, usrid);
                    mailtoStat.setString(2, usrid);
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        frommail = mailtoResult.getString("E_MAIL");
                        fromname = mailtoResult.getString("USER_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                    statmailcont.setString(1, reqno);
                    resultmailcont = statmailcont.executeQuery();
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subjecttitle = "Rejection of Fund Request for Req No  :-" + reqno;
                    messageBodyText = "<html>";
                    messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                    messageBodyText += "<body bgcolor=#95b174>";
                    messageBodyText += "Dear " + sehuser + ",<br/>";
                    messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                    messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                    messageBodyText += "</br></br>";

                    messageBodyText += "</font>";
                    messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                            + "</tr>";
                    while (resultmailcont.next()) {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                        stat3.setString(1, resultmailcont.getString("reqsuno"));
                        result3 = stat3.executeQuery();
                        String sunm = "";
                        if (result3.next()) {
                            sunm = result3.getString("idsunm");
                        }
                        if (result3 != null) {
                            result3.close();
                            result3 = null;
                        }
                        if (stat3 != null) {
                            stat3.close();
                            stat3 = null;
                        }
                        messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";
                    }
                    if (resultmailcont != null) {
                        resultmailcont.close();
                        resultmailcont = null;
                    }
                    if (statmailcont != null) {
                        statmailcont.close();
                        statmailcont = null;
                    }

                    messageBodyText += "</table>";
                    messageBodyText += "<table cellpadding='4' width='600'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                    messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                    messageBodyText += "</table>";
                    messageBodyText += "</body>";
                    messageBodyText += "</html>";
                    try {
                        mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                    } catch (Exception e) {
                        System.out.println("FundRequestNewAction " + e.getMessage());
                    }
                }

            } else if (reqsts != null && reqsts.equals("3")) {
                if (FORWARDTO != null && FORWARDTO.length() > 0 && reqsts != null && reqsts.equals("3")) {
                    stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                    stat5.setString(1, FORWARDTO);
                    stat5.setString(2, REMARKS);
                    stat5.setString(3, reqno);
                    stat5.setString(4, ENTRYSRNO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                    stat5.setInt(1, Integer.parseInt(ENTRYSRNO) + 1);
                    stat5.setString(2, reqno);
                    stat5.setString(3, FORWARDTO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='4',CHQCHID=? where REQNO=?");
                    stat6.setString(1, FORWARDTO);
                    stat6.setString(2, reqno);
                    stat6.executeUpdate();
                    if (stat6 != null) {
                        stat6.close();
                        stat6 = null;
                    }
                    MESSAGE = "4-Req No is " + reqno + " forwarded.";
                    if (reqtyp.equals("M")) {
                        if (reqpono != null && reqpono.size() > 0) {
                            for (int i = 0; i < reqpono.size(); i++) {
                                if (reqpono.get(i).toString() != null && reqpono.get(i).toString().length() > 0) {
                                    SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                                    SimpleDateFormat formatmm = new SimpleDateFormat("MM");
                                    //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                                    String mon = null;
                                    String finyear = null;
                                    finyear = format.format(formatreq.parse(reqdt));
                                    mon = formatmm.format(formatreq.parse(reqdt));
                                    if (mon.equals("01") || mon.equals("02") || mon.equals("03")) {

                                        finyear = (Integer.parseInt(finyear) - 1) + "";
                                    }
                                    //             System.out.print(mon+" "+finyear);
                                    // APS106MI aps106mi = new APS106MI(); -- removed on 18/02/17

                                    aps106mi = new shahi.Action.MIM4.APZ106MI();
                                    aps106mi.connect(HelperConstantsFnl.COMPANY, paydivi);
                                    // aps106mi.connect(); -- removed on 18/02/17
                                    //reqno + reqsuno
                                    aps106mi.Delete(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqno, reqpono.get(i).toString() + reqno, finyear);
                                    //format.format(formatreq.parse(reqdt))
                                    //aps106mi.destroyMI();
                                    //aps106mi=null;
//MESSAGE = "comming--1";
                                    crs620mi = new CRS620MI();
                                    crs620mi.connect();
                                    CRS620MIGetBasicDataBean basicDataBean = crs620mi.getGetBasicData(HelperConstantsFnl.COMPANY, reqsuno);
                                    if (crs620mi != null) {
                                        crs620mi.destroyMI();
                                        crs620mi = null;
                                    }

                                    pps200mi = new PPS200MI();
                                    pps200mi.connect();
                                    PPS200MILstHeadBean getLstHeadBean = pps200mi.LstHeadSingle(HelperConstantsFnl.COMPANY, paydivi, reqpono.get(i).toString(), "15", "99");
                                    if (pps200mi != null) {
                                        pps200mi.destroyMI();
                                        pps200mi = null;
                                    }

                                    if (reqpdcdt == null) {
                                        reqpdcdt = formatreq.parse(reqdt);
                                    }

                                    //  aps106mi = new APS106MI(); -- removed on 18/02/17
                                    //  aps106mi.connect();
                                    //  MESSAGE=reqno + reqsuno+" "+reqpono+" "+basicDataBean.getCRTP()+" "+basicDataBean.getCUCD()+" "+getLstHeadBean.getPYME();
                                    String recFlag = aps106mi.Add(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqno, reqpono.get(i).toString() + reqno, finyear, "AUDITFBD", basicDataBean.getCRTP(), "0", getLstHeadBean.getCUCD(), dateFormat.format(reqpdcdt), getLstHeadBean.getPYME(), getLstHeadBean.getTEPY(), reqpono.get(i).toString(), dateFormat.format(new Date()), dateFormat.format(new Date()), "", "", reqpoamt.get(i).toString(), srvtax.get(i).toString());
                                    //format.format(new Date()),  basicDataBean.getCUCD() 
                                    MESSAGE = recFlag;
                                    if (aps106mi != null) {
                                        aps106mi.destroyMI();
                                        aps106mi = null;
                                    }

                                    if (recFlag != null && recFlag.equals("OK")) {

                                    } else {
                                        addActionError(recFlag);
                                        connbi.rollback();
                                    }

                                }
                            }
                        }
                    }
                    /*   MailProvider mailProvider = new MailProvider();
                     // mail status

                     String ccAddress = null;
                     String sehuser = null;

                     String subjecttitle = "";
                     String messageBodyText = " ";
                     String frommail = "";
                     String fromname = "";
                     String[] tomail = new String[2];
                     String toname = "";



                     tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                     mailtoStat = connbi.prepareStatement("select EMP_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                     mailtoStat.setString(1, FORWARDTO.trim());
                     mailtoResult = mailtoStat.executeQuery();
                     if (mailtoResult.next()) {
                     tomail[1] = mailtoResult.getString("EMP_EMAIL");
                     sehuser = mailtoResult.getString("EMP_NAME");
                     }

                     //          mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)"
                     //                  +" union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                     mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");
                     mailtoStat.setString(1, usrid);
                     mailtoStat.setString(2, usrid);
                     mailtoResult = mailtoStat.executeQuery();
                     if (mailtoResult.next()) {
                     frommail = mailtoResult.getString("E_MAIL");
                     fromname = mailtoResult.getString("USER_NAME");
                     }


                     statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                     statmailcont.setString(1, reqno);
                     resultmailcont = statmailcont.executeQuery();
                     NumberFormat formatter = new DecimalFormat("#0.00");
                     subjecttitle = "Fund Request of Req No  :-" + reqno;
                     messageBodyText = "<html>";
                     messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                     messageBodyText += "<body bgcolor=#95b174>";
                     messageBodyText += "Dear " + sehuser + ",<br/>";
                     messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                     messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. " ;
                     messageBodyText += "</br></br>";

                     messageBodyText += "</font>";
                     messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                     messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                     + "</tr>";                    
                     while (resultmailcont.next()) {                        
                     SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                     stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                     stat3.setString(1, resultmailcont.getString("reqsuno"));
                     result3 = stat3.executeQuery();
                     String sunm = "";
                     if(result3.next()){
                     sunm = result3.getString("idsunm");
                     }
                     if(stat3!=null)
                     stat3.close();
                     if(result3!=null)
                     messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";
                     }
                     messageBodyText += "</table>";
                     messageBodyText += "<table cellpadding='4' width='600'>";
                     messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                     messageBodyText += "<tr style='font-size:14px;'><td>"+fromname+"</td></tr>";
                     messageBodyText += "</table>";
                     messageBodyText += "</body>";
                     messageBodyText += "</html>";
                     mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                 
                     */
                } else if (REJECTSTATUS != null && REJECTSTATUS.equals("Y")) {
                    stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                    stat5.setString(1, RQCHID);
                    stat5.setString(2, REMARKS);
                    stat5.setString(3, reqno);
                    stat5.setString(4, ENTRYSRNO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                    int srno = Integer.parseInt(ENTRYSRNO);
                    stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                    stat5.setInt(1, srno + 1);
                    stat5.setString(2, reqno);
                    stat5.setString(3, RQCHID);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                    //2
                    stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='1',REQREJDT=sysdate where REQNO=?");
                    stat6.setString(1, reqno);
                    stat6.executeUpdate();
                    if (stat6 != null) {
                        stat6.close();
                        stat6 = null;
                    }
                    MESSAGE = "Req No is " + reqno + " Rejected.";

                    MailProvider mailProvider = new MailProvider();
                    // mail status

                    String ccAddress = null;
                    String sehuser = null;

                    String subjecttitle = "";
                    String messageBodyText = " ";
                    String frommail = "";
                    String fromname = "";
                    String[] tomail = new String[3];
                    String toname = "";

                    tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                    mailtoStat = connbi.prepareStatement("select EMP_EMAIL,NVL(CC_EMAIL,EMP_EMAIL) CC_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                    mailtoStat.setString(1, RQCHID.trim());
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        tomail[1] = mailtoResult.getString("EMP_EMAIL");
                        tomail[2] = mailtoResult.getString("CC_EMAIL");
                        sehuser = mailtoResult.getString("EMP_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    //          mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                    //                  + "union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                    mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");

                    mailtoStat.setString(1, usrid);
                    mailtoStat.setString(2, usrid);
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        frommail = mailtoResult.getString("E_MAIL");
                        fromname = mailtoResult.getString("USER_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                    statmailcont.setString(1, reqno);
                    resultmailcont = statmailcont.executeQuery();
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subjecttitle = "Rejection of Fund Request for Req No  :-" + reqno;
                    messageBodyText = "<html>";
                    messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                    messageBodyText += "<body bgcolor=#95b174>";
                    messageBodyText += "Dear " + sehuser + ",<br/>";
                    messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                    messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                    messageBodyText += "</br></br>";

                    messageBodyText += "</font>";
                    messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                            + "</tr>";
                    while (resultmailcont.next()) {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                        stat3.setString(1, resultmailcont.getString("reqsuno"));
                        result3 = stat3.executeQuery();
                        String sunm = "";
                        if (result3.next()) {
                            sunm = result3.getString("idsunm");
                        }
                        if (result3 != null) {
                            result3.close();
                            result3 = null;
                        }
                        if (stat3 != null) {
                            stat3.close();
                            stat3 = null;
                        }
                        messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";
                    }
                    if (resultmailcont != null) {
                        resultmailcont.close();
                        resultmailcont = null;
                    }
                    if (statmailcont != null) {
                        statmailcont.close();
                        statmailcont = null;
                    }
                    messageBodyText += "</table>";
                    messageBodyText += "<table cellpadding='4' width='600'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                    messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                    messageBodyText += "</table>";
                    messageBodyText += "</body>";
                    messageBodyText += "</html>";
                    try {
                        mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                    } catch (Exception e) {
                        System.out.println("FundRequestNewAction " + e.getMessage());
                    }

                }

            } else if (reqsts != null && reqsts.equals("4")) {
                if (FORWARDTO != null && FORWARDTO.length() > 0 && reqsts != null && reqsts.equals("4")) {
                    stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                    stat5.setString(1, FORWARDTO);
                    stat5.setString(2, REMARKS);
                    stat5.setString(3, reqno);
                    stat5.setString(4, ENTRYSRNO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                    stat5.setInt(1, Integer.parseInt(ENTRYSRNO) + 1);
                    stat5.setString(2, reqno);
                    stat5.setString(3, FORWARDTO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='5',CHQCHID=? where REQNO=?");
                    stat6.setString(1, FORWARDTO);
                    stat6.setString(2, reqno);
                    stat6.executeUpdate();
                    if (stat6 != null) {
                        stat6.close();
                        stat6 = null;
                    }

                    MESSAGE = "Req No is " + reqno + " forwarded.";

                    MailProvider mailProvider = new MailProvider();
                    // mail status

                    String ccAddress = null;
                    String sehuser = null;

                    String subjecttitle = "";
                    String messageBodyText = " ";
                    String frommail = "";
                    String fromname = "";
                    String[] tomail = new String[2];
                    String toname = "";

                    tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                    mailtoStat = connbi.prepareStatement("select EMP_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                    mailtoStat.setString(1, FORWARDTO.trim());
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        tomail[1] = mailtoResult.getString("EMP_EMAIL");
                        sehuser = mailtoResult.getString("EMP_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    //       mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                    //                        + "union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                    mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");

                    mailtoStat.setString(1, usrid);
                    mailtoStat.setString(2, usrid);
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        frommail = mailtoResult.getString("E_MAIL");
                        fromname = mailtoResult.getString("USER_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                    statmailcont.setString(1, reqno);
                    resultmailcont = statmailcont.executeQuery();
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subjecttitle = "Fund Request of Req No  :-" + reqno;
                    messageBodyText = "<html>";
                    messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                    messageBodyText += "<body bgcolor=#95b174>";
                    messageBodyText += "Dear " + sehuser + ",<br/>";
                    messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                    messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                    messageBodyText += "</br></br>";

                    messageBodyText += "</font>";
                    messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                            + "</tr>";
                    while (resultmailcont.next()) {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                        stat3.setString(1, resultmailcont.getString("reqsuno"));
                        result3 = stat3.executeQuery();
                        String sunm = "";
                        if (result3.next()) {
                            sunm = result3.getString("idsunm");
                        }
                        if (result3 != null) {
                            result3.close();
                            result3 = null;
                        }
                        if (stat3 != null) {
                            stat3.close();
                            stat3 = null;
                        }
                        messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";

                    }
                    if (resultmailcont != null) {
                        resultmailcont.close();
                        resultmailcont = null;
                    }
                    if (statmailcont != null) {
                        statmailcont.close();
                        statmailcont = null;
                    }
                    messageBodyText += "</table>";
                    messageBodyText += "<table cellpadding='4' width='600'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                    messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                    messageBodyText += "</table>";
                    messageBodyText += "</body>";
                    messageBodyText += "</html>";
                    try {
                        mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                    } catch (Exception e) {
                        System.out.println("FundRequestNewAction " + e.getMessage());
                    }

                } else if (REJECTSTATUS != null && REJECTSTATUS.equals("Y")) {
                    stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                    stat5.setString(1, RQCHID);
                    stat5.setString(2, REMARKS);
                    stat5.setString(3, reqno);
                    stat5.setString(4, ENTRYSRNO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    int srno = Integer.parseInt(ENTRYSRNO);
                    stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                    stat5.setInt(1, srno + 1);
                    stat5.setString(2, reqno);
                    stat5.setString(3, RQCHID);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    //3
                    stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='1',REQREJDT=sysdate where REQNO=?");
                    stat6.setString(1, reqno);
                    stat6.executeUpdate();
                    if (stat6 != null) {
                        stat6.close();
                        stat6 = null;
                    }

                    MESSAGE = "Req No is " + reqno + " Rejected.";

                    if (reqpono != null && reqpono.size() > 0) {
                        for (int i = 0; i < reqpono.size(); i++) {
                            if (reqpono.get(i).toString() != null && reqpono.get(i).toString().length() > 0) {
                                SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                                SimpleDateFormat formatmm = new SimpleDateFormat("MM");
                                //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                                String mon = null;
                                String finyear = null;
                                finyear = format.format(formatreq.parse(reqdt));
                                mon = formatmm.format(formatreq.parse(reqdt));
                                if (mon.equals("01") || mon.equals("02") || mon.equals("03")) {
                                    finyear = (Integer.parseInt(finyear) - 1) + "";
                                }
                                //             System.out.print(mon+" "+finyear);
                                //    APS106MI aps106mi = new APS106MI(); --- removed on 18/02/17
                                //    aps106mi.connect();

                                aps106mi = new shahi.Action.MIM4.APZ106MI();
                                aps106mi.connect(HelperConstantsFnl.COMPANY, paydivi);

                                //reqpono.get(i).toString() +reqno
                                APS106MIGetBean bean = aps106mi.Get(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqno, reqpono.get(i).toString() + reqno, format.format(formatreq.parse(reqdt)));
                                if (bean.getPPYS() != null && Integer.parseInt(bean.getPPYS()) < 6) {
                                    //reqpono.get(i).toString() +reqno
                                    aps106mi.Delete(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqno, reqpono.get(i).toString() + reqno, finyear);
                                    //format.format(formatreq.parse(reqdt))
                                } else {
                                    addActionError("Request already paid!!!");
                                }
                                if (aps106mi != null) {
                                    aps106mi.destroyMI();
                                    aps106mi = null;
                                }
                            }
                        }
                    }
                    MailProvider mailProvider = new MailProvider();
                    // mail status

                    String ccAddress = null;
                    String sehuser = null;

                    String subjecttitle = "";
                    String messageBodyText = " ";
                    String frommail = "";
                    String fromname = "";
                    String[] tomail = new String[3];
                    String toname = "";

                    tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                    mailtoStat = connbi.prepareStatement("select EMP_EMAIL,NVL(CC_EMAIL,EMP_EMAIL) CC_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                    mailtoStat.setString(1, RQCHID.trim());
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        tomail[1] = mailtoResult.getString("EMP_EMAIL");
                        tomail[2] = mailtoResult.getString("CC_EMAIL");
                        sehuser = mailtoResult.getString("EMP_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    //      mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                    //                          + "union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                    mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");

                    mailtoStat.setString(1, usrid);
                    mailtoStat.setString(2, usrid);
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        frommail = mailtoResult.getString("E_MAIL");
                        fromname = mailtoResult.getString("USER_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                    statmailcont.setString(1, reqno);
                    resultmailcont = statmailcont.executeQuery();
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subjecttitle = "Rejection of Fund Request for Req No  :-" + reqno;
                    messageBodyText = "<html>";
                    messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                    messageBodyText += "<body bgcolor=#95b174>";
                    messageBodyText += "Dear " + sehuser + ",<br/>";
                    messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                    messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                    messageBodyText += "</br></br>";

                    messageBodyText += "</font>";
                    messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                            + "</tr>";
                    while (resultmailcont.next()) {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                        stat3.setString(1, resultmailcont.getString("reqsuno"));
                        result3 = stat3.executeQuery();
                        String sunm = "";
                        if (result3.next()) {
                            sunm = result3.getString("idsunm");
                        }
                        if (result3 != null) {
                            result3.close();
                            result3 = null;
                        }
                        if (stat3 != null) {
                            stat3.close();
                            stat3 = null;
                        }
                        messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";

                    }
                    if (resultmailcont != null) {
                        resultmailcont.close();
                        resultmailcont = null;
                    }
                    if (statmailcont != null) {
                        statmailcont.close();
                        statmailcont = null;
                    }

                    messageBodyText += "</table>";
                    messageBodyText += "<table cellpadding='4' width='600'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                    messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                    messageBodyText += "</table>";
                    messageBodyText += "</body>";
                    messageBodyText += "</html>";
                    try {
                        mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                    } catch (Exception e) {
                        System.out.println("FundRequestNewAction " + e.getMessage());
                    }

                }

            } else if (reqsts != null && reqsts.equals("5")) {
                if (FORWARDTO != null && FORWARDTO.length() > 0 && reqsts != null && reqsts.equals("5")) {
                    stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                    stat5.setString(1, FORWARDTO);
                    stat5.setString(2, REMARKS);
                    stat5.setString(3, reqno);
                    stat5.setString(4, ENTRYSRNO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                    stat5.setInt(1, Integer.parseInt(ENTRYSRNO) + 1);
                    stat5.setString(2, reqno);
                    stat5.setString(3, FORWARDTO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='6',CHQCHID=? where REQNO=?");
                    stat6.setString(1, FORWARDTO);
                    stat6.setString(2, reqno);
                    stat6.executeUpdate();
                    if (stat6 != null) {
                        stat6.close();
                        stat6 = null;
                    }

                    MESSAGE = "Req No.-6 is " + reqno + " forwarded.";

                    MailProvider mailProvider = new MailProvider();
                    // mail status

                    String ccAddress = null;
                    String sehuser = null;

                    String subjecttitle = "";
                    String messageBodyText = " ";
                    String frommail = "";
                    String fromname = "";
                    String[] tomail = new String[2];
                    String toname = "";

                    tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                    mailtoStat = connbi.prepareStatement("select EMP_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                    mailtoStat.setString(1, FORWARDTO.trim());
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        tomail[1] = mailtoResult.getString("EMP_EMAIL");
                        sehuser = mailtoResult.getString("EMP_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    //       mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                    //                        + "union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                    mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");

                    mailtoStat.setString(1, usrid);
                    mailtoStat.setString(2, usrid);
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        frommail = mailtoResult.getString("E_MAIL");
                        fromname = mailtoResult.getString("USER_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                    statmailcont.setString(1, reqno);
                    resultmailcont = statmailcont.executeQuery();
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subjecttitle = "Fund Request of Req No  :-" + reqno;
                    messageBodyText = "<html>";
                    messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                    messageBodyText += "<body bgcolor=#95b174>";
                    messageBodyText += "Dear " + sehuser + ",<br/>";
                    messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                    messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                    messageBodyText += "</br></br>";

                    messageBodyText += "</font>";
                    messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                            + "</tr>";
                    while (resultmailcont.next()) {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                        stat3.setString(1, resultmailcont.getString("reqsuno"));
                        result3 = stat3.executeQuery();
                        String sunm = "";
                        if (result3.next()) {
                            sunm = result3.getString("idsunm");
                        }
                        if (result3 != null) {
                            result3.close();
                            result3 = null;
                        }
                        if (stat3 != null) {
                            stat3.close();
                            stat3 = null;
                        }
                        messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";

                    }
                    if (resultmailcont != null) {
                        resultmailcont.close();
                        resultmailcont = null;
                    }
                    if (statmailcont != null) {
                        statmailcont.close();
                        statmailcont = null;
                    }
                    messageBodyText += "</table>";
                    messageBodyText += "<table cellpadding='4' width='600'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                    messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                    messageBodyText += "</table>";
                    messageBodyText += "</body>";
                    messageBodyText += "</html>";
                    try {
                        mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                    } catch (Exception e) {
                        System.out.println("FundRequestNewAction " + e.getMessage());
                    }

                } else if (REJECTSTATUS != null && REJECTSTATUS.equals("Y")) {
                    stat5 = connbi.prepareStatement("update finacbi.fa_payroll_remarks set FORWORDED_TO=?,FORWORDED_DATE=sysdate,REMARK=? where REQ_NO=? and SL_NO=?");
                    stat5.setString(1, RQCHID);
                    stat5.setString(2, REMARKS);
                    stat5.setString(3, reqno);
                    stat5.setString(4, ENTRYSRNO);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    int srno = Integer.parseInt(ENTRYSRNO);
                    stat5 = connbi.prepareStatement("insert into finacbi.fa_payroll_remarks (SL_NO,REQ_NO,USER_ID,TDATE) values (?,?,?,sysdate)");
                    stat5.setInt(1, srno + 1);
                    stat5.setString(2, reqno);
                    stat5.setString(3, RQCHID);
                    stat5.executeUpdate();
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }

                    //3
                    stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='1',REQREJDT=sysdate where REQNO=?");
                    stat6.setString(1, reqno);
                    stat6.executeUpdate();
                    if (stat6 != null) {
                        stat6.close();
                        stat6 = null;
                    }

                    MESSAGE = "Req No is " + reqno + " Rejected.";

                    if (reqpono != null && reqpono.size() > 0) {
                        for (int i = 0; i < reqpono.size(); i++) {
                            if (reqpono.get(i).toString() != null && reqpono.get(i).toString().length() > 0) {
                                SimpleDateFormat formatreq = new SimpleDateFormat("dd/MM/yyyy");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
                                SimpleDateFormat formatmm = new SimpleDateFormat("MM");
                                //    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                                String mon = null;
                                String finyear = null;
                                finyear = format.format(formatreq.parse(reqdt));
                                mon = formatmm.format(formatreq.parse(reqdt));
                                if (mon.equals("01") || mon.equals("02") || mon.equals("03")) {
                                    finyear = (Integer.parseInt(finyear) - 1) + "";
                                }
                                //             System.out.print(mon+" "+finyear);
                                aps106mi = new shahi.Action.MIM4.APZ106MI();
                                aps106mi.connect();
                                //reqpono.get(i).toString() +reqno
                                APS106MIGetBean bean = aps106mi.Get(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqno, reqpono.get(i).toString() + reqno, format.format(formatreq.parse(reqdt)));
                                if (bean.getPPYS() != null && Integer.parseInt(bean.getPPYS()) < 6) {
                                    //reqpono.get(i).toString() +reqno
                                    aps106mi.Delete(HelperConstantsFnl.COMPANY, paydivi, reqsuno, reqsuno, reqno, reqpono.get(i).toString() + reqno, finyear);
                                    //format.format(formatreq.parse(reqdt))
                                } else {
                                    addActionError("Request already paid!!!");
                                }
                                if (aps106mi != null) {
                                    aps106mi.destroyMI();
                                    aps106mi = null;
                                }
                            }
                        }
                    }
                    MailProvider mailProvider = new MailProvider();
                    // mail status

                    String ccAddress = null;
                    String sehuser = null;

                    String subjecttitle = "";
                    String messageBodyText = " ";
                    String frommail = "";
                    String fromname = "";
                    String[] tomail = new String[3];
                    String toname = "";

                    tomail[0] = "kuldeep.anandsingh@shahi.co.in";
                    mailtoStat = connbi.prepareStatement("select EMP_EMAIL,NVL(CC_EMAIL,EMP_EMAIL) CC_EMAIL,EMP_NAME from  finacbi.fa_payroll_mail where EMP_CODE=?");
                    mailtoStat.setString(1, RQCHID.trim());
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        tomail[1] = mailtoResult.getString("EMP_EMAIL");
                        tomail[2] = mailtoResult.getString("CC_EMAIL");
                        sehuser = mailtoResult.getString("EMP_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    //      mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                    //                          + "union  select 'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' EMAIL from  sysibm.sysdummy1");
                    mailtoStat = conndb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");

                    mailtoStat.setString(1, usrid);
                    mailtoStat.setString(2, usrid);
                    mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        frommail = mailtoResult.getString("E_MAIL");
                        fromname = mailtoResult.getString("USER_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }

                    statmailcont = connbi.prepareStatement("select a.reqno,a.reqdt,reqsuno,REQPINO,reqpono,rpoamt,reqpoamt,reqtxt from finacbi.reqmst a,finacbi.REQDTL b where a.reqno=b.reqno and a.reqno=?");
                    statmailcont.setString(1, reqno);
                    resultmailcont = statmailcont.executeQuery();
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    subjecttitle = "Rejection of Fund Request for Req No  :-" + reqno;
                    messageBodyText = "<html>";
                    messageBodyText += "<head><meta http-equiv=content-type content=\"text/html; charset=UTF-8\"></head>";
                    messageBodyText += "<body bgcolor=#95b174>";
                    messageBodyText += "Dear " + sehuser + ",<br/>";
                    messageBodyText += "<font style='font-weight: bold; font: 15px Arial, Helvetica, sans-serif;color:Blue'>";
                    messageBodyText += "This is to inform you that below mentioned details Fund request has been forworded. ";
                    messageBodyText += "</br></br>";

                    messageBodyText += "</font>";
                    messageBodyText += "<table cellpadding='10' bgcolor='#f7ad9d' cellspacing='1'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:13px;font-style:italic;font-weight:bold;color:white;background-color:#a11712;'><td style='width:30pt'>Req No</td><td style='width:70pt'>Req Date</td><td style='width:30pt'>Supplier Code</td><td style='width:200pt'>Supplier desc</td><td style='width:30pt'>P.I.</td><td style='width:30pt'>PO No</td><td style='width:90pt'>PO Amt</td><td style='width:90pt'>Req Amt</td><td style='width:90pt'>Pay Term</td>"
                            + "</tr>";
                    while (resultmailcont.next()) {
                        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                        stat3 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=?");
                        stat3.setString(1, resultmailcont.getString("reqsuno"));
                        result3 = stat3.executeQuery();
                        String sunm = "";
                        if (result3.next()) {
                            sunm = result3.getString("idsunm");
                        }
                        if (result3 != null) {
                            result3.close();
                            result3 = null;
                        }
                        if (stat3 != null) {
                            stat3.close();
                            stat3 = null;
                        }
                        messageBodyText += "<tr style='font-family:Arial;font-size:10px;background-color:rgb(245,245,243);'><td>" + resultmailcont.getString("reqno") + "</td><td>" + resultmailcont.getString("reqdt") + "</td><td>" + resultmailcont.getString("reqsuno") + "</td><td>" + sunm + "</td><td>" + resultmailcont.getString("REQPINO") + "</td><td>" + resultmailcont.getString("reqpono") + "</td><td>" + formatter.format(resultmailcont.getDouble("rpoamt")) + "</td><td>" + formatter.format(resultmailcont.getDouble("reqpoamt")) + "</td><td>" + resultmailcont.getString("reqtxt") + "</td></tr>";

                    }
                    messageBodyText += "</table>";
                    messageBodyText += "<table cellpadding='4' width='600'>";
                    messageBodyText += "<tr style='font-family:Arial;font-size:12px;font-style:italic;font-weight:bold;'><td>Sent By</td></tr>";
                    messageBodyText += "<tr style='font-size:14px;'><td>" + fromname + "</td></tr>";
                    messageBodyText += "</table>";
                    messageBodyText += "</body>";
                    messageBodyText += "</html>";
                    try {
                        mailProvider.postMail(tomail, subjecttitle, messageBodyText, frommail, null);
                    } catch (Exception e) {
                        System.out.println("FundRequestNewAction " + e.getMessage());
                    }

                }
                if (resultmailcont != null) {
                    resultmailcont.close();
                    resultmailcont = null;
                }
                if (statmailcont != null) {
                    statmailcont.close();
                    statmailcont = null;
                }
            } // --------- req end -------- 
            else if (reqsts != null && reqsts.equals("6")) {
                stat6 = connbi.prepareStatement("update finacbi.reqmst set  REQSTS='7',RQBKNM=?,REQCHQ=?,REQCHQDT=?,chqdelv=? where REQNO=?");
                stat6.setString(1, reqbkno);
                stat6.setString(2, reqchq);
                stat6.setDate(3, new java.sql.Date(reqchqdt.getTime()));
                stat6.setString(4, chqdelv);
                stat6.setString(5, reqno);
                stat6.executeUpdate();
                if (stat6 != null) {
                    stat6.close();
                    stat6 = null;
                }
            }

            connbi.commit();
            if (MESSAGE != null && MESSAGE.length() == 0) {
                MESSAGE = "Req no is updated.";
            }
        } catch (SQLException se) {
            addActionError("6 file name : FundRequestNewAction.java" + se.toString());
            System.out.print("6 file name : FundRequestNewAction.java" + se.toString());
            connbi.rollback();
        } catch (Exception e) {
            addActionError("7 file name : FundRequestNewAction.java" + e.toString());
            System.out.print("7 file name : FundRequestNewAction.java" + e.toString());
            connbi.rollback();

        } finally {
            if (stat != null) {
                stat.close();
                stat = null;
            }
            if (stat1 != null) {
                stat1.close();
                stat1 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }
            if (stat3 != null) {
                stat3.close();
                stat3 = null;
            }
            if (stat4 != null) {
                stat4.close();
                stat4 = null;
            }
            if (stat5 != null) {
                stat5.close();
                stat5 = null;
            }
            if (stat6 != null) {
                stat6.close();
                stat6 = null;
            }
            if (conndb2 != null) {
                conndb2.close();
                conndb2 = null;
            }
            if (conndb2hr != null) {
                conndb2hr.close();
                conndb2hr = null;
            }
            if (connbi != null) {
                connbi.close();
                connbi = null;
            }
            if(aps106mi!=null){
                aps106mi.destroyMI();
                aps106mi = null;
            }
            if(crs620mi!=null){
                crs620mi.destroyMI();
                crs620mi = null;
            }
            if(pps200mi!=null){
                pps200mi.destroyMI();
                pps200mi = null;
            }
        }

        return "update";
    }

    public String edit() throws SQLException {
        cancelreq = null;
        reqpono = new ArrayList();
        forwardlist = new ArrayList();
        reqtyplist = new ArrayList<FaPayrollTypeMastBean>();
        payloctlist = new ArrayList<FaPayrollTypeMastBean>();
        divilist = new ArrayList<FaPayrollTypeMastBean>();
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessUnitCode");
        String usrid = (String) session.get("sessUserCode");
        //usrid = "237519";
        //LOCATION_CODE = "100";
        int flag = 0;
        if (usrid == null) {
            session.put("sessUserCode", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }

        Connection connbi = null;
        Connection conndb2 = null;
        Connection condb2hr = null;

        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat2 = null;
        PreparedStatement stat3 = null;
        PreparedStatement stat4 = null;
        PreparedStatement stat5 = null;
        PreparedStatement stat6 = null;
        PreparedStatement stat7 = null;
        PreparedStatement stat8 = null;
        ResultSet result = null;
        ResultSet result1 = null;
        ResultSet result2 = null;
        ResultSet result3 = null;
        ResultSet result4 = null;
        ResultSet result5 = null;
        ResultSet result6 = null;
        ResultSet result7 = null;
        ResultSet result8 = null;
        showReqdtl = new ArrayList();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        int vreq = 0;
        try {
            connbi = new ConnectionShahiHrisNew().getConnection();
            connbi.setAutoCommit(false);
            conndb2 = new connectiondb2().getConnection();
            condb2hr = new ConnectionDB2HR().getConnection();

            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTP' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                reqtyplist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }

            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQTO' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                payloctlist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }

            stat2 = connbi.prepareStatement("SELECT REQ_CODE,REQ_NAME FROM FINACBI.FA_PAYROLL_TYPE_MAST WHERE REQ_TYPE='RQDV' AND FLAG='Y'");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                divilist.add(new FaPayrollTypeMastBean(result2.getString("REQ_CODE"), result2.getString("REQ_NAME")));
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }

            stat = connbi.prepareStatement("select CANFLAG,REQDIVI,REQNO,REQDT,REQTYP,REQSUNO,REQTXT,REQCHQ,REQSTS,CHQDELV,RQCHID,RQRGDT,RQLMDT,DLVPLC,RQBKNM,PAYLOCT,MVXPAID,REQDISC,REQEMPCD,REQCHQDT,REQPINO,CHQCHID,REQPDCDT from finacbi.reqmst where REQNO=?");
            stat.setString(1, reqno);
            result = stat.executeQuery();
            while (result.next()) {
                paydivi = result.getString("REQDIVI");
                reqno = result.getString("REQNO");
                reqdt = dateformat.format(result.getDate("REQDT"));
                reqtyp = result.getString("REQTYP");
                reqsuno = result.getString("REQSUNO");
                stat1 = conndb2.prepareStatement("select idsunm,idsuno from cidmas where idsuno=? order by 1");
                stat1.setString(1, reqsuno);
                result1 = stat1.executeQuery();
                if (result1.next()) {
                    partyname = result1.getString("idsunm");
                }
                if (result1 != null) {
                    result1.close();
                    result1 = null;
                }
                if (stat1 != null) {
                    stat1.close();
                    stat1 = null;
                }
                reqtxt = result.getString("REQTXT");
                dlvplc = result.getString("DLVPLC");
                reqsts = result.getString("REQSTS");
                payloct = result.getString("PAYLOCT");
                reqbkno = result.getString("RQBKNM");
                reqchq = result.getString("reqchq");
                RQCHID = result.getString("RQCHID");
                chqdelv = result.getString("CHQDELV");
                reqpino = result.getString("REQPINO");
                reqempcd = result.getString("REQEMPCD");
                reqdisc = result.getString("REQDISC");
                mvxpaid = result.getString("MVXPAID");
                CHQCHID = result.getString("CHQCHID");
                reqchqdt = result.getDate("reqchqdt");
                reqpdcdt = result.getDate("reqpdcdt");
                CANFLAG = result.getString("CANFLAG");
                //reqpono
                stat2 = connbi.prepareStatement("select REQPONO FROM finacbi.REQDTL WHERE REQNO=?");
                stat2.setString(1, reqno);
                result2 = stat2.executeQuery();
                while (result2.next()) {
                    reqpono.add(result2.getString("REQPONO"));
                }
                if (result2 != null) {
                    result2.close();
                    result2 = null;
                }
                if (stat2 != null) {
                    stat2.close();
                    stat2 = null;
                }
                REQDELV = new ArrayList();
                stat3 = connbi.prepareStatement("select DLRQNO,DLRQPO,DELQTY,to_char(DELVDT,'dd-MM-yyyy')DELVDT,to_char(PCDDT,'dd-MM-yyyy')PCDDT from finacbi.reqdelv where DLRQNO=? order by DLRQNO,DLRQPO");
                stat3.setString(1, reqno);
                result3 = stat3.executeQuery();
                while (result3.next()) {
                    REQDELV.add(new REQDELVBean(result3.getString("DLRQNO"), result3.getString("DLRQPO"), result3.getString("DELQTY"), result3.getString("DELVDT"), result3.getString("PCDDT")));
                }
                if (result3 != null) {
                    result3.close();
                    result3 = null;
                }
                if (stat3 != null) {
                    stat3.close();
                    stat3 = null;
                }

                stat4 = connbi.prepareStatement("select AUTH_STAT from finacbi.fa_payroll_mail where EMP_CODE=? and auth_stat=" + reqsts);
                stat4.setString(1, usrid);
                result4 = stat4.executeQuery();
                String USER_AUT = "";
                if (result4.next()) {
                    USER_AUT = result4.getString("AUTH_STAT");
                }
                if (result4 != null) {
                    result4.close();
                    result4 = null;
                }
                if (stat4 != null) {
                    stat4.close();
                    stat4 = null;
                }
                if (reqsts != null && reqsts.equals("2") && USER_AUT != null && USER_AUT.equals("2")) {
                    stat5 = connbi.prepareStatement("select EMP_CODE,EMP_NAME from finacbi.fa_payroll_mail where AUTH_STAT=3 and emp_code<>" + usrid);
                    result5 = stat5.executeQuery();
                    while (result5.next()) {
                        forwardlist.add(new ForwardListBean(result5.getString("EMP_CODE"), result5.getString("EMP_NAME")));
                    }
                    if (result5 != null) {
                        result5.close();
                        result5 = null;
                    }
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                } else if (reqsts != null && reqsts.equals("3") && USER_AUT != null && USER_AUT.equals("3")) {
                    stat5 = connbi.prepareStatement("select EMP_CODE,EMP_NAME from finacbi.fa_payroll_mail where AUTH_STAT=4 and emp_code<>" + usrid);
                    result5 = stat5.executeQuery();
                    while (result5.next()) {
                        forwardlist.add(new ForwardListBean(result5.getString("EMP_CODE"), result5.getString("EMP_NAME")));
                    }
                    if (result5 != null) {
                        result5.close();
                        result5 = null;
                    }
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                } else if (reqsts != null && reqsts.equals("4") && USER_AUT != null && USER_AUT.equals("4")) {
                    stat5 = connbi.prepareStatement("select EMP_CODE,EMP_NAME from finacbi.fa_payroll_mail where AUTH_STAT=5 and emp_code<>" + usrid);
                    result5 = stat5.executeQuery();
                    while (result5.next()) {
                        forwardlist.add(new ForwardListBean(result5.getString("EMP_CODE"), result5.getString("EMP_NAME")));
                    }
                    if (result5 != null) {
                        result5.close();
                        result5 = null;
                    }
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                } else if (reqsts != null && reqsts.equals("5") && USER_AUT != null && USER_AUT.equals("5")) {
                    stat5 = connbi.prepareStatement("select EMP_CODE,EMP_NAME from finacbi.fa_payroll_mail where AUTH_STAT=6 and emp_code<>" + usrid);
                    result5 = stat5.executeQuery();
                    while (result5.next()) {
                        forwardlist.add(new ForwardListBean(result5.getString("EMP_CODE"), result5.getString("EMP_NAME")));
                    }
                    if (result5 != null) {
                        result5.close();
                        result5 = null;
                    }
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                } else if (reqsts != null && reqsts.equals("1")) {
                    stat5 = connbi.prepareStatement("select EMP_CODE,EMP_NAME from finacbi.fa_payroll_mail where AUTH_STAT=2 and emp_code<>" + usrid);
                    result5 = stat5.executeQuery();
                    while (result5.next()) {
                        forwardlist.add(new ForwardListBean(result5.getString("EMP_CODE"), result5.getString("EMP_NAME")));
                    }
                    if (result5 != null) {
                        result5.close();
                        result5 = null;
                    }
                    if (stat5 != null) {
                        stat5.close();
                        stat5 = null;
                    }
                }
                stat6 = connbi.prepareStatement("select sum(REQPOAMT)REQPOAMT from finacbi.REQDTL where REQNO=?");
                stat6.setString(1, reqno);
                result6 = stat6.executeQuery();
                if (result6.next()) {
                    reqpototalamt = result6.getString("REQPOAMT");
                }
                if (result6 != null) {
                    result6.close();
                    result6 = null;
                }
                if (stat6 != null) {
                    stat6.close();
                    stat6 = null;
                }

                REMARKS_LIST = new ArrayList();
                stat7 = connbi.prepareStatement("select a.sl_no,a.USER_ID,a.FORWORDED_TO,a.REMARK,to_char(FORWORDED_DATE,'dd/MM/yyyy') FORWORDED_DATE from finacbi.fa_payroll_remarks a where REQ_NO=? and FORWORDED_TO is not null order by sl_no");
                stat7.setString(1, reqno);
                result7 = stat7.executeQuery();
                while (result7.next()) {
                    PreparedStatement statuser = condb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') FULL_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");
                    statuser.setString(1, result7.getString("USER_ID"));
                    statuser.setString(2, result7.getString("USER_ID"));
                    String username = "";
                    ResultSet resultuser = statuser.executeQuery();
                    if (resultuser.next()) {
                        username = resultuser.getString("FULL_NAME");
                    }
                    if (resultuser != null) {
                        resultuser.close();
                        resultuser = null;
                    }
                    if (statuser != null) {
                        statuser.close();
                        statuser = null;
                    }

                    statuser = condb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') FULL_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");
                    statuser.setString(1, result7.getString("FORWORDED_TO"));
                    statuser.setString(2, result7.getString("FORWORDED_TO"));
                    String fname = "";
                    resultuser = statuser.executeQuery();
                    if (resultuser.next()) {
                        fname = resultuser.getString("FULL_NAME");
                    }
                    if (resultuser != null) {
                        resultuser.close();
                        resultuser = null;
                    }
                    if (statuser != null) {
                        statuser.close();
                        statuser = null;
                    }

                    REMARKS_LIST.add(new RemarkBean(result7.getString("sl_no"), result7.getString("USER_ID"), username, result7.getString("FORWORDED_TO"), fname, result7.getString("REMARK"), result7.getString("FORWORDED_DATE")));
                }
                if (result7 != null) {
                    result7.close();
                    result7 = null;
                }
                if (stat7 != null) {
                    stat7.close();
                    stat7 = null;
                }
                stat8 = connbi.prepareStatement("select a.sl_no,a.USER_ID from finacbi.fa_payroll_remarks a where REQ_NO=? and FORWORDED_TO is null order by sl_no");
                stat8.setString(1, reqno);
                result8 = stat8.executeQuery();
                while (result8.next()) {
                    ENTRYSRNO = result8.getString("sl_no");
                    ENTRYID = result8.getString("USER_ID");
                    //      PreparedStatement mailtoStat = condb2hr.prepareStatement("SELECT NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?)");
                    PreparedStatement mailtoStat = condb2hr.prepareStatement("select USER_NAME,E_MAIL from "
                            + "(SELECT CARDNO,COLUMN1,NVL(TRIM(EMPLOYEE.FIRSTNAME),'') || ' ' || NVL(TRIM(EMPLOYEE.MIDDLENAME),'') || ' ' || NVL(TRIM(EMPLOYEE.LASTNAME),'') USER_NAME,EMAILID E_MAIL FROM EMPLOYEE WHERE (CARDNO=? OR COLUMN1=?) "
                            + "union "
                            + "select 998819 CARDNO ,998819 COLUMN1 ,'JAY PANDEY' USER_NAME,'jay.pandey@shahi.co.in' E_MAIL from  sysibm.sysdummy1) "
                            + "WHERE (CARDNO=? OR COLUMN1=?) ");
                    mailtoStat.setString(1, result8.getString("USER_ID"));
                    mailtoStat.setString(2, result8.getString("USER_ID"));
                    mailtoStat.setString(3, result8.getString("USER_ID"));
                    mailtoStat.setString(4, result8.getString("USER_ID"));
                    ResultSet mailtoResult = mailtoStat.executeQuery();
                    if (mailtoResult.next()) {
                        ENTRYUSER = mailtoResult.getString("USER_NAME");
                    }
                    if (mailtoResult != null) {
                        mailtoResult.close();
                        mailtoResult = null;
                    }
                    if (mailtoStat != null) {
                        mailtoStat.close();
                        mailtoStat = null;
                    }
                    //ENTRYUSER = result8.getString("user_name");
                }
                if (result8 != null) {
                    result8.close();
                    result8 = null;
                }
                if (stat8 != null) {
                    stat8.close();
                    stat8 = null;
                }
            }
            stat3 = connbi.prepareStatement("select nvl(sum(EOCUAM),0) reqbal from MVXFINAC.FPOPBL@SHAHINEW  where eosuno=?");
            stat3.setString(1, reqsuno);
            result3 = stat3.executeQuery();
            if (result3.next()) {
                reqbal = result3.getString("reqbal");
            }
            if (result3 != null) {
                result3.close();
                result3 = null;
            }
            if (stat3 != null) {
                stat3.close();
                stat3 = null;
            }
        } catch (SQLException se) {
            addActionError("8 file name : FundRequestNewAction.java" + se.toString());
            System.out.print("8 file name : FundRequestNewAction.java" + se.toString());
        } catch (Exception e) {
            addActionError("9 file name : FundRequestNewAction.java" + e.toString());
            System.out.print("9 file name : FundRequestNewAction.java" + e.toString());
        } finally {
            if (result != null) {
                result.close();
                result = null;
            }
            if (result1 != null) {
                result1.close();
                result1 = null;
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (result3 != null) {
                result3.close();
                result3 = null;
            }
            if (result4 != null) {
                result4.close();
                result4 = null;
            }
            if (result5 != null) {
                result5.close();
                result5 = null;
            }
            if (result6 != null) {
                result6.close();
                result6 = null;
            }
            if (result7 != null) {
                result7.close();
                result7 = null;
            }
            if (result8 != null) {
                result8.close();
                result8 = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
            if (stat1 != null) {
                stat1.close();
                stat1 = null;
            }
            if (stat2 != null) {
                stat2.close();
                stat2 = null;
            }
            if (stat3 != null) {
                stat3.close();
                stat3 = null;
            }
            if (stat4 != null) {
                stat4.close();
                stat4 = null;
            }
            if (stat5 != null) {
                stat5.close();
                stat5 = null;
            }
            if (stat6 != null) {
                stat6.close();
                stat6 = null;
            }
            if (stat7 != null) {
                stat7.close();
                stat7 = null;
            }
            if (stat8 != null) {
                stat8.close();
                stat8 = null;
            }
            if (connbi != null) {
                connbi.close();
                connbi = null;
            }
            if (conndb2 != null) {
                conndb2.close();
                conndb2 = null;
            }
        }
        return "view";
    }

    public String getpodt() {

        Connection con = null;
        Connection conora = null;
        Connection condb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        PreparedStatement stat1 = null;
        ResultSet result1 = null;

        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        try {
            conora = new connection().getConnection();
            con = new ConnectionShahiHrisNew().getConnection();
            condb2 = new connectiondb2().getConnection();
            con.setAutoCommit(false);
            ponumb = "";
            if (textid != null) {
                ponumb = reqpono.get(Integer.parseInt(textid)).toString();
                //System.out.println(ponumb + reqsuno);
                //3287943
                boolean recordexist = false;
                DecimalFormat df = new DecimalFormat("#.##");

                // Run if reqno is not null
                if (reqno != null) {
                    reqpoamttemp = "";
                    stat = con.prepareStatement("select to_char(REQPODT,'dd/MM/yyyy') REQPODT,RPOAMT,REQPOAMT,SRVTAX from finacbi.REQDTL where REQPONO=?  and REQNO=?");
                    stat.setString(1, ponumb);
                    stat.setString(2, reqno);
                    result = stat.executeQuery();
                    while (result.next()) {
                        reqpodttemp = result.getString("REQPODT");
                        rpoamttemp = df.format(result.getDouble("RPOAMT"));
                        reqpoamttemp = df.format(result.getDouble("REQPOAMT"));
                        srvtaxtemp = df.format(result.getDouble("SRVTAX"));
                        recordexist = true;
                    }
                    if (result != null) {
                        result.close();
                        result = null;
                    }
                    if (stat != null) {
                        stat.close();
                        stat = null;
                    }
                    if (recordexist == true) {
                        stat1 = con.prepareStatement("select nvl(sum(nvl(reqpoamt,0)),0) advpaid,max(RATEREM) raterem from finacbi.REQDTL where reqpono=?");
                        stat1.setString(1, ponumb);
                        result1 = stat1.executeQuery();
                        String advpaid = "";
                        while (result1.next()) {
                            advpaid = df.format(result1.getDouble("advpaid"));
                            reqratetemp = result1.getString("raterem");
                        }
                        if (result1 != null) {
                            result1.close();
                            result1 = null;
                        }
                        if (stat1 != null) {
                            stat1.close();
                            stat1 = null;
                        }
                        advamttemp = (df.format(Double.parseDouble(advpaid) - Double.parseDouble(reqpoamttemp))) + "";
                    }
                }

                //Fresh Data
                String raterefno = null;
                String iasuno = null;
                String iaorty = null;
                if (recordexist == true) {
                    stat = condb2.prepareStatement("select iapuno,iasuno,iaorty,iaourr,to_char(to_date(cast(iapudt as varchar(8)),'yyyymmdd'),'dd/mm/yyyy') iasldt from m3fdbprd.mphead where iapuno=?");
                    stat.setString(1, ponumb);
                    result = stat.executeQuery();
                    while (result.next()) {
                        reqpodttemp = result.getString("iasldt");
                        raterefno = result.getString("iaourr");
                        iasuno = result.getString("iasuno");
                        iaorty = result.getString("iaorty");
                    }
                    if (result != null) {
                        result.close();
                        result = null;
                    }
                    if (stat != null) {
                        stat.close();
                        stat = null;
                    }

                    if (iaorty != null && iaorty.startsWith("Y")) {
                        if (raterefno != null && StringUtils.isNumeric(raterefno.trim())) {
                            stat2 = conora.prepareStatement("SELECT 'Rate :'||trim(YARN_RATE)||' | Pay Term : '||trim(PAY_TERMS)||' | Ordr Qty.: '||trim(ORDR_QTY)||' | Width: '||trim(FABR_WDTH) kk"
                                    + " FROM SHAHIWEB.FABSRCDTBRKUP A,SHAHIWEB.FABSRCYRNDTBRKUP B WHERE A.QUOTE_NUMB=B.QUOTE_NUMB AND B.QUOTE_NUMB=? AND B.YARN_SUPPLIER=?");
                            stat2.setString(1, raterefno.trim());
                            stat2.setString(2, iasuno.trim());
                            result2 = stat2.executeQuery();
                            if (result2.next()) {
                                reqratetemp = result2.getString("kk");
                            }
                            if (result2 != null) {
                                result2.close();
                                result2 = null;
                            }
                            if (stat2 != null) {
                                stat2.close();
                                stat2 = null;
                            }
                        }
                    } else {
                        if (raterefno != null && StringUtils.isNumeric(raterefno.trim())) {
                            stat2 = conora.prepareStatement("SELECT 'Rate :'||(round(A.APPR_RATE-NVL(SUM(B.CONV_FACT*B.YARN_RATE),0),2))||' | Pay Term : '||TRIM(A.PAY_TERMS)||' | Ordr Qty.: '||A.ORDR_QTY||' | Width: '||A.FABR_WDTH kk"
                                    + " FROM SHAHIWEB.FABSRCDTBRKUP A,SHAHIWEB.FABSRCYRNDTBRKUP B"
                                    + " WHERE A.QUOTE_NUMB=B.QUOTE_NUMB(+) AND A.VEND_CODE=B.VEND_CODE(+) AND A.FABR_CODE=B.FABR_CODE(+) AND A.QUOTE_NUMB=? AND"
                                    + " A.VEND_CODE=? AND A.ORDR_QTY >0"
                                    + " GROUP BY A.APPR_RATE,A.PAY_TERMS,A.ORDR_QTY,A.FABR_WDTH");
                            stat2.setString(1, raterefno.trim());
                            stat2.setString(2, iasuno.trim());
                            result2 = stat2.executeQuery();
                            if (result2.next()) {
                                reqratetemp = result2.getString("kk");
                            }
                            if (result2 != null) {
                                result2.close();
                                result2 = null;
                            }
                            if (stat2 != null) {
                                stat2.close();
                                stat2 = null;
                            }
                        }
                    }
                    // logic change in R&S
                    /*stat2 = conora.prepareStatement("select 'Rate :'||trim(appr_rate)||' | Pay Term : '||trim(Pay_terms)||' | Ordr Qty.: '||trim(ordr_qty)||' | Width: '||trim(fabr_wdth) kk from fabsrcdtbrkup where  quote_numb=? and vend_code=? and ordr_Qty<>0");
                     stat2.setString(1, raterefno.trim());
                     stat2.setString(2, iasuno.trim());
                     result2 = stat2.executeQuery();
                     if (result2.next()) {
                     reqratetemp = result2.getString("kk");
                     }
                     if (result2 != null) {
                     result2.close();
                     result2 = null;
                     }
                     if (stat2 != null) {
                     stat2.close();
                     stat2 = null;
                     }*/
                    stat = condb2.prepareStatement("select sum(IBLNAM+IVCEVA) POAMT"
                            + " from m3fdbprd.mpline left outer join"
                            + " (select ivcono,ivpuno,ivpnli,sum(ivceva) ivceva from"
                            + " m3fdbprd.mpoexp where ivpuno=? and ivexty =2 and ivthpr=0"
                            + " group by ivcono,ivpuno,ivpnli ) a on"
                            + " (ibcono=a.ivcono and ibpuno=a.ivpuno and ibpnli=a.ivpnli)"
                            + " where ibcono=? and ibpust>12 and ibpuno=? and ibsuno=?");
                    stat.setString(1, ponumb);
                    stat.setString(2, HelperConstantsFnl.COMPANY);
                    stat.setString(3, ponumb);
                    stat.setString(4, reqsuno);
                    result = stat.executeQuery();
                    while (result.next()) {
                        String advpaid = "0";
                        if (result.getString("POAMT") != null && result.getString("POAMT").length() > 0) {
                            stat1 = con.prepareStatement("select nvl(sum(nvl(reqpoamt,0)),0) advpaid from finacbi.REQDTL where reqpono=?");
                            stat1.setString(1, ponumb);
                            result1 = stat1.executeQuery();
                            while (result1.next()) {
                                advpaid = df.format(result1.getDouble("advpaid"));
                            }
                            if (result1 != null) {
                                result1.close();
                                result1 = null;
                            }
                            if (stat1 != null) {
                                stat1.close();
                                stat1 = null;
                            }
                        }
                        rpoamttemp = df.format(result.getDouble("POAMT"));
                        advamttemp = advpaid;
                    }
                    if (result != null) {
                        result.close();
                        result = null;
                    }
                    if (stat != null) {
                        stat.close();
                        stat = null;
                    }

                }
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                String REQ_DT = format.format(new Date());
                CHGPCT = 0;
                stat = con.prepareStatement("SELECT CHGPCT CHGPCT FROM finacbi.REQCHG WHERE CHGLN=1 AND ? BETWEEN FMDT AND TODT");
                stat.setString(1, REQ_DT);
                result = stat.executeQuery();
                if (result.next()) {
                    CHGPCT = result.getDouble("CHGPCT");
                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ee) {
                addActionError("10 file name : FundRequestNewAction.java" + ee.toString());
                System.out.print("10 file name : FundRequestNewAction.java" + ee.toString());
            }
            addActionError("11 file name : FundRequestNewAction.java" + e.toString());
            System.out.print("11 file name : FundRequestNewAction.java" + e.toString());

        } finally {
            try {

                if (result != null) {
                    result.close();
                    result = null;
                }
                if (result1 != null) {
                    result1.close();
                    result1 = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
                if (stat1 != null) {
                    stat1.close();
                    stat1 = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }
                if (condb2 != null) {
                    condb2.close();
                    condb2 = null;
                }
                if (conora != null) {
                    conora.close();
                    conora = null;
                }
            } catch (Exception e) {
                addActionError("12 file name : FundRequestNewAction.java" + e.toString());
                System.out.print("12 file name : FundRequestNewAction.java" + e.toString());
            }
        }

        return "podetailsnew";
    }

    public String getpo() {
        podetaillst = new ArrayList();
        Connection con = null;
        Connection condb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        try {
            con = new ConnectionShahiHrisNew().getConnection();
            con.setAutoCommit(false);
            condb2 = new connectiondb2().getConnection();
            if (IDPUNOS != null) {
                String ponumb = IDPUNOS.toUpperCase();
                stat = condb2.prepareStatement("select sum(IBLNAM+IVCEVA) POAMT"
                        + " from mpline left outer join"
                        + " (select ivcono,ivpuno,ivpnli,sum(ivceva) ivceva from"
                        + " mpoexp where ivpuno=? and ivexty =2 and ivthpr=0"
                        + " group by ivcono,ivpuno,ivpnli ) a on"
                        + " (ibcono=a.ivcono and ibpuno=a.ivpuno and ibpnli=a.ivpnli)"
                        + " where ibcono=? and ibpust>12 and ibpuno=? and ibsuno=?");
                stat.setString(1, ponumb);
                stat.setString(2, HelperConstantsFnl.COMPANY);
                stat.setString(3, ponumb);
                //System.out.println("Suppler " + reqsuno + "  " + SUP_CODE);
                stat.setString(4, SUP_CODE);
                result = stat.executeQuery();
                while (result.next()) {
                    stat1 = con.prepareStatement("select nvl(sum(nvl(reqpoamt,0)),0) advpaid from finacbi.REQDTL where reqpono=?");
                    stat1.setString(1, ponumb);
                    result1 = stat1.executeQuery();
                    String advpaid = "";
                    while (result1.next()) {
                        advpaid = result1.getString("advpaid");
                    }
                    if (result1 != null) {
                        result1.close();
                        result1 = null;
                    }
                    if (stat1 != null) {
                        stat1.close();
                        stat1 = null;
                    }
                    //System.out.println("SS " + advpaid);
                    podetaillst.add(new PoDetailBean(IDPUNOS, result.getString("POAMT"), advpaid));

                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
                IDPUNOS = null;

            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ee) {
                addActionError("13 file name : FundRequestNewAction.java" + ee.toString());
                System.out.print("13 file name : FundRequestNewAction.java" + ee.toString());
            }
            addActionError("14 file name : FundRequestNewAction.java" + e.toString());
            System.out.print("14 file name : FundRequestNewAction.java" + e.toString());

            System.out.println(e.toString());
        } finally {
            try {

                if (result != null) {
                    result.close();
                    result = null;
                }
                if (result1 != null) {
                    result1.close();
                    result1 = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
                if (stat1 != null) {
                    stat1.close();
                    stat1 = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }
                if (condb2 != null) {
                    condb2.close();
                    condb2 = null;
                }

            } catch (Exception e) {
                System.out.print("File Name : BuyerListAction.java Exception in finally block");
                e.printStackTrace();
            }
        }

        return "podetails";
    }

    public String buyerlst() {

        buyerlist = new ArrayList();
        Connection con = null;
        try {

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            con = new connectiondb2().getConnection();
            con.setAutoCommit(false);
            if (buyer != null && buyer.length() > 0) {

                String pparty = buyer.toUpperCase() + "%";
                stat = con.prepareStatement("select idsunm,idsuno from cidmas where IDCONO=? and (idsunm like ? or idsuno like ? ) AND IDSTAT>10 AND IDSTAT<90 order by 1");
                stat.setString(1, HelperConstantsFnl.COMPANY);
                stat.setString(2, pparty);
                stat.setString(3, pparty);
                result = stat.executeQuery();
                while (result.next()) {
                    if (buyerlist.contains(result.getString("idsuno"))) {
                    } else {
                        buyerlist.add(new SupplierBean(result.getString("idsuno"), result.getString("idsunm")));
                    }
                }
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ee) {
                addActionError("15 file name : FundRequestNewAction.java" + ee.toString());
                System.out.print("15 file name : FundRequestNewAction.java" + ee.toString());
            }
            addActionError("16 file name : FundRequestNewAction.java" + e.toString());
            System.out.print("16 file name : FundRequestNewAction.java" + e.toString());
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }

            } catch (Exception e) {
                addActionError("17 file name : FundRequestNewAction.java" + e.toString());
                System.out.print("17 file name : FundRequestNewAction.java" + e.toString());
            }
        }
        return "buyerlst";
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getAcauth() {
        return acauth;
    }

    public void setAcauth(String acauth) {
        this.acauth = acauth;
    }

    public String getChqdelv() {
        return chqdelv;
    }

    public void setChqdelv(String chqdelv) {
        this.chqdelv = chqdelv;
    }

    public String getDlvplc() {
        return dlvplc;
    }

    public void setDlvplc(String dlvplc) {
        this.dlvplc = dlvplc;
    }

    public String getMvxpaid() {
        return mvxpaid;
    }

    public void setMvxpaid(String mvxpaid) {
        this.mvxpaid = mvxpaid;
    }

    public String getPayloct() {
        return payloct;
    }

    public void setPayloct(String payloct) {
        this.payloct = payloct;
    }

    public String getReqbkno() {
        return reqbkno;
    }

    public void setReqbkno(String reqbkno) {
        this.reqbkno = reqbkno;
    }

    public String getReqchq() {
        return reqchq;
    }

    public void setReqchq(String reqchq) {
        this.reqchq = reqchq;
    }

    public Date getReqchqdt() {
        return reqchqdt;
    }

    public void setReqchqdt(Date reqchqdt) {
        this.reqchqdt = reqchqdt;
    }

    public String getReqdisc() {
        return reqdisc;
    }

    public void setReqdisc(String reqdisc) {
        this.reqdisc = reqdisc;
    }

    public String getReqdt() {
        return reqdt;
    }

    public void setReqdt(String reqdt) {
        this.reqdt = reqdt;
    }

    public String getReqempcd() {
        return reqempcd;
    }

    public void setReqempcd(String reqempcd) {
        this.reqempcd = reqempcd;
    }

    public String getReqno() {
        return reqno;
    }

    public void setReqno(String reqno) {
        this.reqno = reqno;
    }

    public String getReqpino() {
        return reqpino;
    }

    public void setReqpino(String reqpino) {
        this.reqpino = reqpino;
    }

    public String getReqsts() {
        return reqsts;
    }

    public void setReqsts(String reqsts) {
        this.reqsts = reqsts;
    }

    public String getReqsuno() {
        return reqsuno;
    }

    public void setReqsuno(String reqsuno) {
        this.reqsuno = reqsuno;
    }

    public String getReqtxt() {
        return reqtxt;
    }

    public void setReqtxt(String reqtxt) {
        this.reqtxt = reqtxt;
    }

    public String getReqtyp() {
        return reqtyp;
    }

    public void setReqtyp(String reqtyp) {
        this.reqtyp = reqtyp;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public List getShowReqdtl() {
        return showReqdtl;
    }

    public void setShowReqdtl(List showReqdtl) {
        this.showReqdtl = showReqdtl;
    }

    public List getShowReqdlv() {
        return showReqdlv;
    }

    public void setShowReqdlv(List showReqdlv) {
        this.showReqdlv = showReqdlv;
    }

    public String getNewinsert() {
        return newinsert;
    }

    public void setNewinsert(String newinsert) {
        this.newinsert = newinsert;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public List getBuyerlist() {
        return buyerlist;
    }

    public void setBuyerlist(List buyerlist) {
        this.buyerlist = buyerlist;
    }

    public String getIDPUNOS() {
        return IDPUNOS;
    }

    public void setIDPUNOS(String IDPUNOS) {
        this.IDPUNOS = IDPUNOS;
    }

    public String getSUP_CODE() {
        return SUP_CODE;
    }

    public void setSUP_CODE(String SUP_CODE) {
        this.SUP_CODE = SUP_CODE;
    }

    public List getReqpoamt() {
        return reqpoamt;
    }

    public void setReqpoamt(List reqpoamt) {
        this.reqpoamt = reqpoamt;
    }

    public List getReqpodt() {
        return reqpodt;
    }

    public void setReqpodt(List reqpodt) {
        this.reqpodt = reqpodt;
    }

    public List getRpoamt() {
        return rpoamt;
    }

    public void setRpoamt(List rpoamt) {
        this.rpoamt = rpoamt;
    }

    public List getPodetaillst() {
        return podetaillst;
    }

    public void setPodetaillst(List podetaillst) {
        this.podetaillst = podetaillst;
    }

    public String getLocindex() {
        return locindex;
    }

    public void setLocindex(String locindex) {
        this.locindex = locindex;
    }

    public List getReqpono() {
        return reqpono;
    }

    public void setReqpono(List reqpono) {
        this.reqpono = reqpono;
    }

    public String getTextid() {
        return textid;
    }

    public void setTextid(String textid) {
        this.textid = textid;
    }

    public String getAdvamttemp() {
        return advamttemp;
    }

    public void setAdvamttemp(String advamttemp) {
        this.advamttemp = advamttemp;
    }

    public String getReqpoamttemp() {
        return reqpoamttemp;
    }

    public void setReqpoamttemp(String reqpoamttemp) {
        this.reqpoamttemp = reqpoamttemp;
    }

    public String getReqpodttemp() {
        return reqpodttemp;
    }

    public void setReqpodttemp(String reqpodttemp) {
        this.reqpodttemp = reqpodttemp;
    }

    public String getRpoamttemp() {
        return rpoamttemp;
    }

    public void setRpoamttemp(String rpoamttemp) {
        this.rpoamttemp = rpoamttemp;
    }

    public List getDelvdt() {
        return delvdt;
    }

    public void setDelvdt(List delvdt) {
        this.delvdt = delvdt;
    }

    public List getPcddt() {
        return pcddt;
    }

    public void setPcddt(List pcddt) {
        this.pcddt = pcddt;
    }

    public List getDelqty() {
        return delqty;
    }

    public void setDelqty(List delqty) {
        this.delqty = delqty;
    }

    public List getREQDELV() {
        return REQDELV;
    }

    public void setREQDELV(List REQDELV) {
        this.REQDELV = REQDELV;
    }

    public List getReqpost() {
        return reqpost;
    }

    public void setReqpost(List reqpost) {
        this.reqpost = reqpost;
    }

    public List getForwardlist() {
        return forwardlist;
    }

    public void setForwardlist(List forwardlist) {
        this.forwardlist = forwardlist;
    }

    public String getReqpototalamt() {
        return reqpototalamt;
    }

    public void setReqpototalamt(String reqpototalamt) {
        this.reqpototalamt = reqpototalamt;
    }

    public String getFORWARDTO() {
        return FORWARDTO;
    }

    public void setFORWARDTO(String FORWARDTO) {
        this.FORWARDTO = FORWARDTO;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public List getREMARKS_LIST() {
        return REMARKS_LIST;
    }

    public void setREMARKS_LIST(List REMARKS_LIST) {
        this.REMARKS_LIST = REMARKS_LIST;
    }

    public String getRQCHID() {
        return RQCHID;
    }

    public void setRQCHID(String RQCHID) {
        this.RQCHID = RQCHID;
    }

    public String getENTRYUSER() {
        return ENTRYUSER;
    }

    public void setENTRYUSER(String ENTRYUSER) {
        this.ENTRYUSER = ENTRYUSER;
    }

    public String getENTRYID() {
        return ENTRYID;
    }

    public void setENTRYID(String ENTRYID) {
        this.ENTRYID = ENTRYID;
    }

    public String getENTRYSRNO() {
        return ENTRYSRNO;
    }

    public void setENTRYSRNO(String ENTRYSRNO) {
        this.ENTRYSRNO = ENTRYSRNO;
    }

    public String getREJECTSTATUS() {
        return REJECTSTATUS;
    }

    public void setREJECTSTATUS(String REJECTSTATUS) {
        this.REJECTSTATUS = REJECTSTATUS;
    }

    public String getCHQCHID() {
        return CHQCHID;
    }

    public void setCHQCHID(String CHQCHID) {
        this.CHQCHID = CHQCHID;
    }

    public File getSketchPdf() {
        return sketchPdf;
    }

    public void setSketchPdf(File sketchPdf) {
        this.sketchPdf = sketchPdf;
    }

    public String getSketchPdfContentType() {
        return sketchPdfContentType;
    }

    public void setSketchPdfContentType(String sketchPdfContentType) {
        this.sketchPdfContentType = sketchPdfContentType;
    }

    public String getSketchPdfFileName() {
        return sketchPdfFileName;
    }

    public void setSketchPdfFileName(String sketchPdfFileName) {
        this.sketchPdfFileName = sketchPdfFileName;
    }

    public String getChkpoatt() {
        return chkpoatt;
    }

    public void setChkpoatt(String chkpoatt) {
        this.chkpoatt = chkpoatt;
    }

    public String getRecupld() {
        return recupld;
    }

    public void setRecupld(String recupld) {
        this.recupld = recupld;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getUploadid() {
        return uploadid;
    }

    public void setUploadid(String uploadid) {
        this.uploadid = uploadid;
    }

    public String getReqbal() {
        return reqbal;
    }

    public void setReqbal(String reqbal) {
        this.reqbal = reqbal;
    }

    public String getReqratetemp() {
        return reqratetemp;
    }

    public void setReqratetemp(String reqratetemp) {
        this.reqratetemp = reqratetemp;
    }

    public List getReqrate() {
        return reqrate;
    }

    public void setReqrate(List reqrate) {
        this.reqrate = reqrate;
    }

    public Date getReqpdcdt() {
        return reqpdcdt;
    }

    public void setReqpdcdt(Date reqpdcdt) {
        this.reqpdcdt = reqpdcdt;
    }

    public String getSrvtaxtemp() {
        return srvtaxtemp;
    }

    public void setSrvtaxtemp(String srvtaxtemp) {
        this.srvtaxtemp = srvtaxtemp;
    }

    public List getSrvtax() {
        return srvtax;
    }

    public void setSrvtax(List srvtax) {
        this.srvtax = srvtax;
    }

    public List<FaPayrollTypeMastBean> getReqtyplist() {
        return reqtyplist;
    }

    public void setReqtyplist(List<FaPayrollTypeMastBean> reqtyplist) {
        this.reqtyplist = reqtyplist;
    }

    public List<FaPayrollTypeMastBean> getPayloctlist() {
        return payloctlist;
    }

    public void setPayloctlist(List<FaPayrollTypeMastBean> payloctlist) {
        this.payloctlist = payloctlist;
    }

    public Date getMIN_DATE() {
        return MIN_DATE;
    }

    public void setMIN_DATE(Date MIN_DATE) {
        this.MIN_DATE = MIN_DATE;
    }

    public Date getMAX_DATE() {
        return MAX_DATE;
    }

    public void setMAX_DATE(Date MAX_DATE) {
        this.MAX_DATE = MAX_DATE;
    }

    public double getCHGPCT() {
        return CHGPCT;
    }

    public void setCHGPCT(double CHGPCT) {
        this.CHGPCT = CHGPCT;
    }

    public List<FaPayrollTypeMastBean> getDivilist() {
        return divilist;
    }

    public void setDivilist(List<FaPayrollTypeMastBean> divilist) {
        this.divilist = divilist;
    }

    public String getPaydivi() {
        return paydivi;
    }

    public void setPaydivi(String paydivi) {
        this.paydivi = paydivi;
    }

    public String getPonumb() {
        return ponumb;
    }

    public void setPonumb(String ponumb) {
        this.ponumb = ponumb;
    }

    public String getCancelreq() {
        return cancelreq;
    }

    public void setCancelreq(String cancelreq) {
        this.cancelreq = cancelreq;
    }

    public String getCANFLAG() {
        return CANFLAG;
    }

    public void setCANFLAG(String CANFLAG) {
        this.CANFLAG = CANFLAG;
    }

}
