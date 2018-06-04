package shahi.Action.MvxExp.POST;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import shahi.Action.MvxExp.BuyerInv.Beans.SearchListBean;
import shahi.Action.MvxExp.PRE.Beans.BuyersearchBean;
import shahi.Action.database.connectiondb2; 

public class LcMasterAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private String FLG1;
    private ByteArrayInputStream inputStream;
    private List listdata = new ArrayList();
    private String SEARCH_CODE;
    private List SearchList = new ArrayList();
    private List SearchList1 = new ArrayList();
    private List SearchList2 = new ArrayList();
    private List buyeraddlist = new ArrayList();
    private String aausrid;
    private String FLAG1;
    private String FLG3;
    private String FLG4;
    private String DOCTYPE;
    private String LCTYPE;
    private String REFTYPECODE; 
    private String REFTYPEDESC;
    private String REFNO;
    private String REFDATE;
    private String DESCRIPTION;
    private String COMPANY;
    private String COMPANYDESC;
    private String FBANK;
    private String FBANK_NAME;
    private String BENIFICRYBNK;
    private String BENIFICRYBNKDESC;
    private String BUYER;
    private String BUYERDESC;
    private String CURRNCY;
    private String CURRNCYDESC;
    private String VALIDITY;
    private String EXPORTVALIDITY=null;
    private String IMPORTVALIDITY;
    private String MODESHPNT;
    private String PMTTRM;
    private String PMTTRMDESC;
    private String SHPMNTTRM;
    private double PRSNTAION;
    private double PERCNT;
    private double VALUE;
    private double FC_VAL;
    private double INR_VAL;
    
    private double EXPORT_FC;
    private double EXPORT_INR;
    private double IMPORT_FC;
    private double IMPORT_INR;
    
    private String FBANK_ADDR;
    private String BENIFICRYBNK_ADDR;

    @Override
    public String execute() throws SQLException {
        Connection conn = null;
        Connection conndb2=null;
        PreparedStatement stat2 = null,stat=null;
        ResultSet result2 = null,result=null;
        try {
            conn = new connection().getConnection();
            conndb2 = new connectiondb2().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch  
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
      //  LOCATION_CODE = "100";
     if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }         
        
        String bnknm="";
        String bnknumbr="";
        String bnkBenif="";
        String bnkBenifnumbr="";
        String bnkByr="";
        String bnkCrncy="";
        String bnkPytrm="";

        try {
            
            stat2 = conn.prepareStatement("select REF_TYPE,REF_NO,to_char(REF_DATE,'dd-Mon-yyyy') REF_DATE,COMPANY,BANK,CURRENCY,BENEF_BANK,to_char(VALIDITY,'dd-Mon-yyyy') VALIDITY,VALUE_FC,VALUE_INR,"
                    + "BUYER,PAY_TERM shiptrm,PAYMENT_TERM,MODE_OF_SHIP,REF_DESC,VALIDITY_EXP,VALID_DAYS,PERCENT,VALIDITY_IMP,EXP_OB_FC,EXP_OB_INR,IMP_OB_FC,IMP_OB_INR from  ei_lc_lic_mast where REF_TYPE=? and REF_NO=?");
            stat2.setString(1, REFTYPECODE.trim());
            stat2.setString(2, REFNO.trim());
            result2 = stat2.executeQuery();
            if (result2.next()) {
                
                
                if(result2.getString("BANK")!=null){
                stat = conndb2.prepareStatement("SELECT trim(BRBKBM) brbkbm,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn  from m3fdbprd.cbanbr where brcono=111 and  (BRBKNO like ? or BRBKBM like ?) ");
                stat.setString(1, "%" + result2.getString("BANK").toUpperCase() + "%");
                stat.setString(2, "%" + result2.getString("BANK").toUpperCase() + "%");
                result = stat.executeQuery();
                if (result.next()) {
                   bnknm=result.getString("BRBKBM");
                   bnknumbr=result.getString("brbbrn");
                }
                }
                if(result2.getString("BENEF_BANK")!=null){
                stat = conndb2.prepareStatement("SELECT trim(BRBKBM) brbkbm,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn  from m3fdbprd.cbanbr where brcono=111 and  (BRBKNO like ? or BRBKBM like ?) ");
                stat.setString(1, "%" + result2.getString("BENEF_BANK").toUpperCase() + "%");
                stat.setString(2, "%" + result2.getString("BENEF_BANK").toUpperCase() + "%");
                result = stat.executeQuery();
                if (result.next()) {
                   bnkBenif=result.getString("BRBKBM");
                   bnkBenifnumbr=result.getString("brbbrn");
                }
                }

                if(result2.getString("BUYER")!=null){
                stat = conndb2.prepareStatement("select OKCUNO,OKCUNM from m3fdbprd.ocusma where OKCUNO like ?");
                stat.setString(1, "%" + result2.getString("BUYER").toUpperCase().trim() + "%");
                result = stat.executeQuery();
                if(result.next()) {
                    bnkByr=result.getString("OKCUNM");
                }
                }
                
                if(result2.getString("CURRENCY")!=null){
                stat = conndb2.prepareStatement("select distinct CTSTKY,CTTX40 from m3fdbprd.csytab where CTSTCO='CUCD' and CTSTKY like ?");
                stat.setString(1, "%" + result2.getString("CURRENCY").trim() + "%");
                result = stat.executeQuery();
                if (result.next()) {
                    bnkCrncy= result.getString("CTTX40");
                }
                }
                if(result2.getString("PAYMENT_TERM")!=null){
                stat = conn.prepareStatement("select type_code,type_desc from ei_grup_type_dtls where grup_type_code='IMP' and TYPE_CODE like ? order by type_desc");
                stat.setString(1, "%" + result2.getString("PAYMENT_TERM").trim() + "%");
                result = stat.executeQuery();
                if (result.next()) {
                    bnkPytrm=result.getString("type_desc");
                }
                }
                 
                
                
                REFTYPECODE = result2.getString("REF_TYPE");
                REFNO = result2.getString("REF_NO");
                COMPANY = result2.getString("COMPANY");
                REFDATE = result2.getString("REF_DATE");
                DESCRIPTION = result2.getString("REF_DESC");
                FBANK = result2.getString("BANK");
                FBANK_NAME=bnknm;
                FBANK_ADDR=bnknumbr;
                BENIFICRYBNK = result2.getString("BENEF_BANK");
                BENIFICRYBNKDESC=bnkBenif;
                BENIFICRYBNK_ADDR=bnkBenifnumbr;
                BUYER = result2.getString("BUYER");
                BUYERDESC=bnkByr;
                CURRNCY = result2.getString("CURRENCY");
                CURRNCYDESC=bnkCrncy;
                VALIDITY = result2.getString("VALIDITY");
                EXPORTVALIDITY = result2.getString("VALIDITY_EXP");
                IMPORTVALIDITY = result2.getString("VALIDITY_IMP");
                MODESHPNT = result2.getString("MODE_OF_SHIP");
                PMTTRM = result2.getString("PAYMENT_TERM");
                PMTTRMDESC=bnkPytrm;
                SHPMNTTRM = result2.getString("shiptrm");
                PRSNTAION = result2.getDouble("VALID_DAYS");
                PERCNT = result2.getDouble("PERCENT");
                VALUE = result2.getDouble("VALUE_FC");
                FC_VAL = result2.getDouble("VALUE_FC");
                INR_VAL = result2.getDouble("VALUE_INR");
                
                EXPORT_FC = result2.getDouble("EXP_OB_FC");
                EXPORT_INR = result2.getDouble("EXP_OB_INR");
                IMPORT_FC = result2.getDouble("IMP_OB_FC");
                IMPORT_INR = result2.getDouble("IMP_OB_INR");
            }
            if (stat2 != null) {
                stat2.close();
            }
            if (result2 != null) {
                result2.close();
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
 

        return SUCCESS;
    }

    public String clear1() {
        LCTYPE = null;
        REFNO = null;
        COMPANY = null;
        REFDATE = null;
        DESCRIPTION = null;
        COMPANY = null;
        FBANK = null;
        FBANK_ADDR=null;
        FBANK_NAME=null;
        BENIFICRYBNK = null;
        BENIFICRYBNK_ADDR=null;
        BENIFICRYBNKDESC=null;
        PMTTRMDESC = null;
        BUYER = null;
        CURRNCY = null;
        CURRNCYDESC=null;
        VALIDITY = null;
        EXPORTVALIDITY = null;
        IMPORTVALIDITY=null;
        MODESHPNT = null;
        PMTTRM = null;
        SHPMNTTRM = null;
        PRSNTAION = 0.0;
        PERCNT = 0.0;
        VALUE = 0.0;
        INR_VAL = 0.0;

        return "clr";
    }

    public String update1() throws SQLException {
        Connection conn = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        PreparedStatement stat4 = null;
        ResultSet result4 = null;
        PreparedStatement stat5 = null;
        ResultSet result5 = null;
        PreparedStatement stat6 = null;
        ResultSet result6 = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch   


        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
      // LOCATION_CODE = "100";
      //  usrid = "227350";
        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        } 
        
                
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0, z = 0;
            String newno = "";
            String FINYR = "";
            String vex = "";
            String t1 = "";
            String t2 = "";
            String yr = null;
            String comp = null;
            String invn = null;
            String byr = null;
            String byradd = null;
            String loc = null;

            
               stat2= conn.prepareStatement("select location_code   from seh_web_users where user_id=?") ;
                    stat2.setString(1,usrid.trim());
                    result2=stat2.executeQuery();
                    while (result2.next())
                    {LOCATION_CODE=result2.getString("location_code");
                    }  
                    
            stat2 = conn.prepareStatement("select * from  ei_lc_lic_mast  where REF_NO=?");
            stat2.setString(1, REFNO);
            result2 = stat2.executeQuery();
            if (result2.next()) {
 
                stat4 = conn.prepareStatement("UPDATE  ei_lc_lic_mast  SET REF_DATE=?,BANK=?,CURRENCY=?,"
                        + "BENEF_BANK=?,VALIDITY=?,VALUE_FC=?,VALUE_INR=?,BUYER=?,PAY_TERM=?,PAYMENT_TERM=?,MODE_OF_SHIP=?,REF_DESC=?,"
                        + "VALIDITY_EXP=?,PERCENT=?,SEH_USER=?,VALID_DAYS=?,VALIDITY_IMP=?,BANK_ADDR=?,BB_ADDR=?,EXP_OB_FC=?,EXP_OB_INR=?,IMP_OB_FC=?,IMP_OB_INR=?,TDATE=sysdate WHERE REF_TYPE=? and REF_NO=?");
               
                stat4.setString(1, REFDATE!=null && REFDATE.length()>0?myFormat.format(fromUser.parse(REFDATE)):"");
                stat4.setString(2, FBANK);
                stat4.setString(3, CURRNCY.trim());
                stat4.setString(4, BENIFICRYBNK);
                stat4.setString(5, VALIDITY!=null && VALIDITY.length()>0?myFormat.format(fromUser.parse(VALIDITY)):"");
                stat4.setDouble(6, VALUE);
                stat4.setDouble(7, INR_VAL);
                stat4.setString(8, BUYER);
                stat4.setString(9, SHPMNTTRM);
                stat4.setString(10, PMTTRM);
                stat4.setString(11, MODESHPNT.trim());
                stat4.setString(12, DESCRIPTION.trim());
                stat4.setString(13, EXPORTVALIDITY!=null && EXPORTVALIDITY.length()>0?myFormat.format(fromUser.parse(EXPORTVALIDITY)):"");
                stat4.setDouble(14, PERCNT);
                stat4.setString(15, usrid);
                stat4.setDouble(16, PRSNTAION);
                stat4.setString(17, IMPORTVALIDITY!=null && IMPORTVALIDITY.length()>0?myFormat.format(fromUser.parse(IMPORTVALIDITY)):"");
                stat4.setString(18, FBANK_ADDR);
                stat4.setString(19, BENIFICRYBNK_ADDR);
                stat4.setDouble(20, EXPORT_FC);
                stat4.setDouble(21, EXPORT_INR);
                stat4.setDouble(22, IMPORT_FC);
                stat4.setDouble(23, IMPORT_INR);
                stat4.setString(24, REFTYPECODE);
                stat4.setString(25, REFNO);
                x = stat4.executeUpdate();
                if (x > 0) {
                    ++x; 
                    conn.commit();
                }
            } else {
                stat4 = conn.prepareStatement("insert into  ei_lc_lic_mast (REF_TYPE,REF_NO,REF_DATE,COMPANY,BANK,CURRENCY,"
                        + "BENEF_BANK,VALIDITY,VALUE_FC,VALUE_INR,BUYER,PAY_TERM,PAYMENT_TERM,MODE_OF_SHIP,REF_DESC,VALIDITY_EXP,"
                        + "PERCENT,SEH_USER,VALID_DAYS,VALIDITY_IMP,BANK_ADDR,BB_ADDR,EXP_OB_FC,EXP_OB_INR,IMP_OB_FC,IMP_OB_INR,location,TDATE) "
                        + "values(?,?,?,'111',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)");
                stat4.setString(1, REFTYPECODE!=null && REFTYPECODE.length()>0?REFTYPECODE:"");
                stat4.setString(2, REFNO!=null && REFNO.length()>0?REFNO.toUpperCase():"");
                stat4.setString(3, REFDATE!=null && REFDATE.length()>0?myFormat.format(fromUser.parse(REFDATE)):"");
                stat4.setString(4, FBANK!=null && FBANK.length()>0?FBANK:"");
                stat4.setString(5, CURRNCY!=null && CURRNCY.length()>0?CURRNCY.trim():"");
                stat4.setString(6, BENIFICRYBNK!=null && BENIFICRYBNK.length()>0?BENIFICRYBNK:"");
                stat4.setString(7, VALIDITY!=null && VALIDITY.length()>0?myFormat.format(fromUser.parse(VALIDITY)):"");
                stat4.setDouble(8, VALUE!=0 && VALUE>0?VALUE:0);
                stat4.setDouble(9,INR_VAL!=0 && INR_VAL>0?INR_VAL:0 );
                stat4.setString(10, BUYER!=null && BUYER.length()>0?BUYER:"");
                stat4.setString(11, SHPMNTTRM!=null && SHPMNTTRM.length()>0?SHPMNTTRM:"");
                stat4.setString(12, PMTTRM!=null && PMTTRM.length()>0?PMTTRM:"");
                stat4.setString(13, MODESHPNT!=null && MODESHPNT.length()>0?MODESHPNT:"");
                stat4.setString(14, DESCRIPTION!=null && DESCRIPTION.length()>0?DESCRIPTION:"");
                stat4.setString(15, EXPORTVALIDITY!=null && EXPORTVALIDITY.length()>0?myFormat.format(fromUser.parse(EXPORTVALIDITY)):"");
                stat4.setDouble(16, PERCNT!=0&& PERCNT>0?PERCNT:0);
                stat4.setString(17, usrid);
                stat4.setDouble(18, PRSNTAION!=0&& PRSNTAION>0?PRSNTAION:0);
                stat4.setString(19, IMPORTVALIDITY!=null && IMPORTVALIDITY.length()>0?myFormat.format(fromUser.parse(IMPORTVALIDITY)):"");
                stat4.setString(20, FBANK_ADDR!=null && FBANK_ADDR.length()>0?FBANK_ADDR:"");
                stat4.setString(21, BENIFICRYBNK_ADDR!=null && BENIFICRYBNK_ADDR.length()>0?BENIFICRYBNK_ADDR:"");
                stat4.setDouble(22, EXPORT_FC!=0&& EXPORT_FC>0?EXPORT_FC:0);
                stat4.setDouble(23, EXPORT_INR!=0&& EXPORT_INR>0?EXPORT_INR:0);
                stat4.setDouble(24, IMPORT_FC!=0&& IMPORT_FC>0?IMPORT_FC:0);
                stat4.setDouble(25, IMPORT_INR!=0&& IMPORT_INR>0?IMPORT_INR:0);
                stat4.setString(26,LOCATION_CODE);
                x = stat4.executeUpdate();
                if (x > 0) {
                    ++x;
                    conn.commit();
                }
            }


            if (x > 0) {
                addActionMessage("Record Updated succcessfully ");



            }


        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stat4 != null) {
                stat4.close();
            }
            if (result4 != null) {
                result4.close();
            }
        }

        if (stat2 != null) {
            stat2.close();
        }
        if (stat4 != null) {
            stat4.close();
        }
        if (result2 != null) {
            result2.close();
        }
        if (result4 != null) {
            result4.close();
        }



        execute();

        return "updte";
    }

    public String bankview() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
//        if (usrid == null && loc == null) {
//            addActionError("Session not valid!!!");
//            return SUCCESS;
//        }

        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conndb2 = new connectiondb2().getConnection();

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conndb2.prepareStatement("SELECT trim(BRBKBM) brbkbm,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn  from m3fdbprd.cbanbr where brcono=111 and  (BRBKNO like ? or BRBKBM like ?) ");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    buyeraddlist.add(new BuyersearchBean(result.getString("BRBKNO"), result.getString("BRBBRN"), result.getString("BRBKBM"), result.getString("bankaddr"),""));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conndb2 != null) {
                conndb2.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return "bankview";
    }

    public String bankview1() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
//        if (usrid == null && loc == null) {
//            addActionError("Session not valid!!!");
//            return SUCCESS;
//        }

        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conndb2 = new connectiondb2().getConnection();

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conndb2.prepareStatement("SELECT trim(BRBKBM) brbkbm,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn  from m3fdbprd.cbanbr where brcono=111 and  (BRBKNO like ? or BRBKBM like ?) ");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    buyeraddlist.add(new BuyersearchBean(result.getString("BRBKNO"), result.getString("BRBBRN"), result.getString("BRBKBM"), result.getString("bankaddr"),""));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conndb2 != null) {
                conndb2.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return "bankview1";
    }

    public String searchBuyr() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");


        Connection conn = null;
        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connection().getConnection();
            conndb2 = new connectiondb2().getConnection();
            System.out.println("guddu1" + SEARCH_CODE);
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conndb2.prepareStatement("select OKCUNO,OKCUNM from m3fdbprd.ocusma where OKCUNO like ?");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase().trim() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    System.out.println("guddu");
                    SearchList.add(new SearchListBean(result.getString("OKCUNO"), result.getString("OKCUNM")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "lcbyr";
    }

    public String searchCrncy() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");


        Connection conn = null;
        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connection().getConnection();
            conndb2 = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conndb2.prepareStatement("select distinct CTSTKY,CTTX40 from m3fdbprd.csytab where CTSTCO='CUCD' and CTSTKY like ?");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase().trim() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("CTSTKY"), result.getString("CTTX40")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "lccrncy";
    }

    public String searchPaytrm() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");


        Connection conn = null;
        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connection().getConnection();
            conndb2 = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn.prepareStatement("select type_code,type_desc from ei_grup_type_dtls where grup_type_code='IMP' and TYPE_CODE like ? order by type_desc");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase().trim() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("type_code"), result.getString("type_desc")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

        return "lcpaytrm";
    }

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List getListdata() {
        return listdata;
    }

    public void setListdata(List listdata) {
        this.listdata = listdata;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {

        this.servletRequest = servletRequest;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public List getSearchList() {
        return SearchList;
    }

    public void setSearchList(List SearchList) {
        this.SearchList = SearchList;
    }

    public List getSearchList1() {
        return SearchList1;
    }

    public void setSearchList1(List SearchList1) {
        this.SearchList1 = SearchList1;
    }

    public List getSearchList2() {
        return SearchList2;
    }

    public void setSearchList2(List SearchList2) {
        this.SearchList2 = SearchList2;
    }

    public List getBuyeraddlist() {
        return buyeraddlist;
    }

    public void setBuyeraddlist(List buyeraddlist) {
        this.buyeraddlist = buyeraddlist;
    }

    public String getFLG1() {
        return FLG1;
    }

    public void setFLG1(String FLG1) {
        this.FLG1 = FLG1;
    }

    public String getFLAG1() {
        return FLAG1;
    }

    public void setFLAG1(String FLAG1) {
        this.FLAG1 = FLAG1;
    }

    public String getFLG3() {
        return FLG3;
    }

    public void setFLG3(String FLG3) {
        this.FLG3 = FLG3;
    }

    public String getFLG4() {
        return FLG4;
    }

    public void setFLG4(String FLG4) {
        this.FLG4 = FLG4;
    }

    public String getDOCTYPE() {
        return DOCTYPE;
    }

    public void setDOCTYPE(String DOCTYPE) {
        this.DOCTYPE = DOCTYPE;
    }

    public String getLCTYPE() {
        return LCTYPE;
    }

    public void setLCTYPE(String LCTYPE) {
        this.LCTYPE = LCTYPE;
    }

    public String getREFDATE() {
        return REFDATE;
    }

    public void setREFDATE(String REFDATE) {
        this.REFDATE = REFDATE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getFBANK() {
        return FBANK;
    }

    public void setFBANK(String FBANK) {
        this.FBANK = FBANK;
    }

    public String getBENIFICRYBNK() {
        return BENIFICRYBNK;
    }

    public void setBENIFICRYBNK(String BENIFICRYBNK) {
        this.BENIFICRYBNK = BENIFICRYBNK;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getCURRNCY() {
        return CURRNCY;
    }

    public void setCURRNCY(String CURRNCY) {
        this.CURRNCY = CURRNCY;
    }

    public String getVALIDITY() {
        return VALIDITY;
    }

    public void setVALIDITY(String VALIDITY) {
        this.VALIDITY = VALIDITY;
    }

    public String getEXPORTVALIDITY() {
        return EXPORTVALIDITY;
    }

    public void setEXPORTVALIDITY(String EXPORTVALIDITY) {
        this.EXPORTVALIDITY = EXPORTVALIDITY;
    }

    public String getMODESHPNT() {
        return MODESHPNT;
    }

    public void setMODESHPNT(String MODESHPNT) {
        this.MODESHPNT = MODESHPNT;
    }

    public String getPMTTRM() {
        return PMTTRM;
    }

    public void setPMTTRM(String PMTTRM) {
        this.PMTTRM = PMTTRM;
    }

    public String getSHPMNTTRM() {
        return SHPMNTTRM;
    }

    public void setSHPMNTTRM(String SHPMNTTRM) {
        this.SHPMNTTRM = SHPMNTTRM;
    }

    public double getPRSNTAION() {
        return PRSNTAION;
    }

    public void setPRSNTAION(double PRSNTAION) {
        this.PRSNTAION = PRSNTAION;
    }

    public double getFC_VAL() {
        return FC_VAL;
    }

    public void setFC_VAL(double FC_VAL) {
        this.FC_VAL = FC_VAL;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getREFTYPECODE() {
        return REFTYPECODE;
    }

    public void setREFTYPECODE(String REFTYPECODE) {
        this.REFTYPECODE = REFTYPECODE;
    }

    public String getREFTYPEDESC() {
        return REFTYPEDESC;
    }

    public void setREFTYPEDESC(String REFTYPEDESC) {
        this.REFTYPEDESC = REFTYPEDESC;
    }

    public String getREFNO() {
        return REFNO;
    }

    public void setREFNO(String REFNO) {
        this.REFNO = REFNO;
    }

    public String getCOMPANYDESC() {
        return COMPANYDESC;
    }

    public void setCOMPANYDESC(String COMPANYDESC) {
        this.COMPANYDESC = COMPANYDESC;
    }

    public String getFBANK_NAME() {
        return FBANK_NAME;
    }

    public void setFBANK_NAME(String FBANK_NAME) {
        this.FBANK_NAME = FBANK_NAME;
    }

    public double getPERCNT() {
        return PERCNT;
    }

    public void setPERCNT(double PERCNT) {
        this.PERCNT = PERCNT;
    }

    public double getVALUE() {
        return VALUE;
    }

    public void setVALUE(double VALUE) {
        this.VALUE = VALUE;
    }

    public double getINR_VAL() {
        return INR_VAL;
    }

    public void setINR_VAL(double INR_VAL) {
        this.INR_VAL = INR_VAL;
    }

    public String getBENIFICRYBNKDESC() {
        return BENIFICRYBNKDESC;
    }

    public void setBENIFICRYBNKDESC(String BENIFICRYBNKDESC) {
        this.BENIFICRYBNKDESC = BENIFICRYBNKDESC;
    }

    public String getBUYERDESC() {
        return BUYERDESC;
    }

    public void setBUYERDESC(String BUYERDESC) {
        this.BUYERDESC = BUYERDESC;
    }

    public String getCURRNCYDESC() {
        return CURRNCYDESC;
    }

    public void setCURRNCYDESC(String CURRNCYDESC) {
        this.CURRNCYDESC = CURRNCYDESC;
    }

    public String getPMTTRMDESC() {
        return PMTTRMDESC;
    }

    public void setPMTTRMDESC(String PMTTRMDESC) {
        this.PMTTRMDESC = PMTTRMDESC;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public String getIMPORTVALIDITY() {
        return IMPORTVALIDITY;
    }

    public void setIMPORTVALIDITY(String IMPORTVALIDITY) {
        this.IMPORTVALIDITY = IMPORTVALIDITY;
    }

    public double getEXPORT_FC() {
        return EXPORT_FC;
    }

    public void setEXPORT_FC(double EXPORT_FC) {
        this.EXPORT_FC = EXPORT_FC;
    }

    public double getEXPORT_INR() {
        return EXPORT_INR;
    }

    public void setEXPORT_INR(double EXPORT_INR) {
        this.EXPORT_INR = EXPORT_INR;
    }

    public double getIMPORT_FC() {
        return IMPORT_FC;
    }

    public void setIMPORT_FC(double IMPORT_FC) {
        this.IMPORT_FC = IMPORT_FC;
    }

    public double getIMPORT_INR() {
        return IMPORT_INR;
    }

    public void setIMPORT_INR(double IMPORT_INR) {
        this.IMPORT_INR = IMPORT_INR;
    }

    public String getFBANK_ADDR() {
        return FBANK_ADDR;
    }

    public void setFBANK_ADDR(String FBANK_ADDR) {
        this.FBANK_ADDR = FBANK_ADDR;
    }

    public String getBENIFICRYBNK_ADDR() {
        return BENIFICRYBNK_ADDR;
    }

    public void setBENIFICRYBNK_ADDR(String BENIFICRYBNK_ADDR) {
        this.BENIFICRYBNK_ADDR = BENIFICRYBNK_ADDR;
    }
    
    
}