    package shahi.Action.MvxExp.BuyerInv;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.BuyerInv.Beans.KohlsBean;
import shahi.Action.MvxExp.BuyerInv.Beans.PvhListBean;
import shahi.Action.MvxExp.BuyerInv.Beans.PvhSubLstBean;
import shahi.Action.MvxExp.BuyerInv.Beans.SearchListBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.MvxExp.Reports.PRE.bean.BEListBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connectiondb2;
 
public class PvhCommercialAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private String INVOICE_S;
    private String YEAR;
    private String COMPANY;
    private String INVOICENO;
    private String INVOICEDATE;
      private String BUYER;
    private String BUYER_DESC;
    private String BUYER_ADDR;
    private String FLAG1;
    private String FLAG2;
    private String aausrid;
     private Double TOTQTY = 0.0;
    private Double TOTFOB = 0.0;
    private String CBM;
    private ByteArrayInputStream inputStream;
    private List listdata = new ArrayList();
    private String SEARCH_CODE;
    private String PLANNO_N;
    private String TTO_DATE;
    private String CNTRY;
    private String TO_DATE;
    private String FIN_DATE;
    private String INV_QTY;
    private String SHIP_QTY;
    private String INVNO;
    private String NET_WEIGHT;
    private String LOCATION;
    private String REPORTTYPE;
    private String NETWT;
    private List PVHList = new ArrayList();
    private List delTermList = new ArrayList();
    private List PONO;
    private List STYLENO;
    private List HTSCODE;
    private List COLORCODE;
    private List COLORDESC;
    private List INVDESC;
    private List CTN;
    private List QNTY;
    private List CURRENCY;
    private List INVRATE;
    private List SEPLRATE;
    private List FOB;
    
    private int flag4 = 0;
    private int flagVAL;
    private Double TOT_QNTY;
    private Double TOT_VAL;
    private Double TOT_CTN;
    private List SearchList=new ArrayList();
    private List SearchList1=new ArrayList();
    private List SearchList2=new ArrayList();
    private List SearchList3=new ArrayList();
    
    private String FLG1;
    private String FLG2;
    private String FLG3;
    private String FLG4;
    
    private String SHIPAUTH;
    private String QUALTY;
    private String PMNO;
    private String STYLENM;
    private String THDIVI;
    private String GROSSWT;
    private String NETNETWT;
    private String MNUFFACT;
    private String SEASON;
    private String DIVIS;
    private String FABTYP;
    private String LICCODE;
    private String CARGEDATE;
    private String BOXMSRMNT;
    private String TOTCTN;
    private String INV_TPE;
    private String SIGN_REQ;
    private String REX_REQ;
    private String DEL_TERM;
    private  String NOTIFY1;
    private  String NOTIFY2;
  
    
    
    
    

    @Override
    public String execute() throws SQLException {

        Connection conn = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch  
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
      //  LOCATION_CODE = "100";
 
        try {
            
            
            delTermList.add("");
            delTermList.add("TT 45 DAY'S FROM DOCUMENTS ACCEPTED ON LF PORTAL");
            delTermList.add("TT 45 DAY'S FROM THE DATE OF DISPATCH");
            delTermList.add("TT 45 DAY'S FROM DOCUMENTS ACCEPTED ON TRADE CARD PORTAL");
            delTermList.add("TT 45 DAY'S UPON RECEIPT OF SHIPMENT DOCUMENTS");
            
            if (FLAG1.equals("Yes") && FLAG1.length() > 0) {
                stat2 = conn.prepareStatement("select a.EXCS_INV_NO,to_char(a.INV_DATE,'yyyy-MM-dd') INV_DATE,a.COMPANY,a.PLAN_NO,a.BUYER,a.BUYER_ADDR,to_char(a.TTO_DATE,'yyyy-MM-dd') TTO_DATE,to_char(a.fin_DATE,'yyyy-MM-dd') FIN_DATE,a.DESTI_CNTRY,to_char(a.T_O_DATE,'yyyy-MM-dd') T_O_DATE,a.INV_QTY,a.SHIP_QTY,a.BUYER_ADDR,a.year,a.LOCATION,b.REMARK,b.CREADIT_NO,b.SHIPMENT_TYPE,b.PACKAGE,b.GR_WT,b.NET_WT,b.NET_NET_WT,b.AGENT from ei_endors_mast a left outer join ei_other_inv b  on(a.year=b.year and a.INV_NO=b.INV_NO and a.company=b.company and a.EXCS_INV_NO=? ) where a.EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S.trim());
                stat2.setString(2, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                if (result2.next()) {

                    INVOICENO = result2.getString("EXCS_INV_NO");
                    INVOICEDATE = result2.getString("INV_DATE");
                    COMPANY = result2.getString("COMPANY");
                    PLANNO_N = result2.getString("PLAN_NO");
                    BUYER = result2.getString("BUYER"); 
                    BUYER_DESC = result2.getString("BUYER_ADDR");
                    TTO_DATE = result2.getString("TTO_DATE");
                    CNTRY = result2.getString("DESTI_CNTRY");
                    TO_DATE = result2.getString("T_O_DATE");
                    FIN_DATE = result2.getString("FIN_DATE");
                    INV_QTY = result2.getString("INV_QTY");
                    SHIP_QTY = result2.getString("SHIP_QTY");
                    YEAR = result2.getString("year");
                    LOCATION = result2.getString("LOCATION");
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }

                stat2 = conn.prepareStatement("select SHIPMENT_TYPE,QUALITY,CREADIT_NO,REMARK,PAYMENT_TERM,GR_WT,NET_WT,AGENT,AUTH_NAME,CONTAINER_NUMB,SEASON,DIVISION,SHIP_DATE,BOX_DIM,colour_code,DM_CODE,BT_CODE,nvl(ldp_rate,0) ldp_rate,nvl(ldp_fob,0) ldp_fob,colour from ei_other_inv where  EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                if (result2.next()) {

                    SHIPAUTH = result2.getString("SHIPMENT_TYPE");
                    QUALTY = result2.getString("QUALITY");
                    PMNO = result2.getString("CREADIT_NO");
                    STYLENM = result2.getString("REMARK");
                    THDIVI = result2.getString("PAYMENT_TERM");
                    GROSSWT = result2.getString("GR_WT");
                    NETNETWT = result2.getString("NET_WT");
                    MNUFFACT = result2.getString("AGENT");
                    SEASON = result2.getString("AUTH_NAME");
                    DIVIS = result2.getString("CONTAINER_NUMB");
                    FABTYP = result2.getString("SEASON");
                    LICCODE = result2.getString("DIVISION");
                    CARGEDATE = result2.getString("SHIP_DATE");
                    BOXMSRMNT = result2.getString("BOX_DIM");
                    INV_TPE = result2.getString("colour_code");
                    NOTIFY1= result2.getString("DM_CODE");
                    NOTIFY2= result2.getString("BT_CODE");
                    SIGN_REQ=result2.getString("ldp_rate");
                    REX_REQ=result2.getString("ldp_fob");
                    DEL_TERM=result2.getString("colour");

                }
                else{
                    SHIPAUTH = null;
                    QUALTY = null;
                    PMNO = null;
                    STYLENM = null;
                    THDIVI = null;
                    GROSSWT = null;
                    NETNETWT = null;
                    MNUFFACT = null;
                    SEASON = null;
                    DIVIS = null;
                    FABTYP = null;
                    LICCODE = null;
                    CARGEDATE = null;
                    BOXMSRMNT = null;
                    NOTIFY1=null;
                    NOTIFY2=null;
                    SIGN_REQ="0";
                    REX_REQ="0";
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }

                int x = 0;
                double TOT_CTN1=0;
                double TOT_QNTY1=0;
                double TOT_VAL1=0;
                stat2 = conn.prepareStatement("select a.PO_NO,a.STYLE_NO,a.HTS_CODE,a.LINE_CODE,a.COLR_DESC,a.INV_DESC,a.PKGS,a.INV_QTY,a.INV_CRNCY,a.INV_RATE,sum(nvl(a.INV_RATE,0)+nvl(a.HNGR_PRICE,0))*a.INV_QTY fob from  ei_matalan_inv a,ei_endors_mast b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.excs_inv_no=? group by a.PO_NO,a.STYLE_NO,a.HTS_CODE,a.LINE_CODE,a.COLR_DESC,a.INV_DESC,a.PKGS,a.INV_QTY,a.INV_CRNCY,a.INV_RATE,a.SAP_FC");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                while (result2.next()) {
                    
                    PVHList.add(new PvhListBean(result2.getString("PO_NO"), result2.getString("STYLE_NO"), result2.getString("HTS_CODE"), result2.getString("LINE_CODE"), result2.getString("COLR_DESC"), result2.getString("INV_DESC"), result2.getString("PKGS"),result2.getString("INV_QTY"), result2.getString("INV_CRNCY"),result2.getString("INV_RATE"),result2.getString("fob")));  
                    
                   TOT_CTN1+=result2.getDouble("PKGS");
                   TOT_QNTY1+=result2.getDouble("INV_QTY");
                   TOT_VAL1+=result2.getDouble("fob");
                    ++x;
                }
                    TOT_CTN=TOT_CTN1;
                    TOT_QNTY= TOT_QNTY1;
                    TOT_VAL=TOT_VAL1;
                
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
                double TOT_QNTY2=0; 
                double TOT_VAL2=0;
                String clr_desc="";
                if (x == 0) {
                  //  if(CNTRY.equals("CA")){
                    stat2 = conn.prepareStatement("select a.pre_print_no PO_No,a.token_no Style,a.HSCODE1,a.DESCRIPTION,HMTY15 clrdesc,sum(a.qty_endors) eqty,a.currency,net_price-nvl(accr_price,0) rate,(sum(a.qty_endors)*((a.price_fc+nvl(a.price_misc,0))+sum(a.qty_endors))) fob from ei_endors_dtls a,ei_endors_accr_dtls b,mitmah_M4 c,ei_endors_mast d where d.excs_inv_no=? and a.year=d.year and a.company=d.company and a.inv_no=d.inv_no and a.year=b.year(+) and a.company=b.company(+) and a.inv_no=b.inv_no(+) and a.co_no=b.co_no(+) and a.co_line=b.co_line(+) and c.hmcono=111 and c.hmitno=a.item_no group by a.pre_print_no,a.token_no,a.HSCODE1,a.DESCRIPTION,HMTY15,a.currency,(net_price-nvl(accr_price,0)),(a.price_fc+nvl(a.price_misc,0))");
                    stat2.setString(1, INVOICE_S.trim());
                    result2 = stat2.executeQuery();
                    while(result2.next()){
                      PVHList.add(new PvhListBean(result2.getString("PO_No"), result2.getString("Style"),result2.getString("HSCODE1")," ",result2.getString("clrdesc"),result2.getString("DESCRIPTION")," ",result2.getString("eqty"), result2.getString("currency"),result2.getString("rate"),result2.getString("fob")));
                    
                        TOT_QNTY2+=result2.getDouble("eqty");
                        TOT_VAL2+=result2.getDouble("fob");
                        ++flag4; 
                    } 
                  /*  }
                    else{
                    stat2 = conn.prepareStatement("select a.pre_print_no PO_No,a.token_no Style,a.HSCODE1,a.DESCRIPTION,sum(a.qty_endors) eqty,a.currency,net_price-nvl(accr_price,0) rate,(sum(a.qty_endors)*((a.price_fc+nvl(a.price_misc,0))+sum(a.qty_endors))) fob from ei_endors_dtls a,ei_endors_accr_dtls b,ei_endors_mast c where c.excs_inv_no=? and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and a.year=b.year(+) and a.company=b.company(+) and a.inv_no=b.inv_no(+) and a.year=b.year(+) and a.company=b.company(+) and a.inv_no=b.inv_no(+) and a.co_no=b.co_no(+) and a.co_line=b.co_line(+)  group by a.pre_print_no,a.token_no,a.HSCODE1,a.DESCRIPTION,a.currency,(net_price-nvl(accr_price,0)),(a.price_fc+nvl(a.price_misc,0))");
                    stat2.setString(1, INVOICE_S.trim());
                    result2 = stat2.executeQuery();
                    while (result2.next()) {
                      PVHList.add(new PvhListBean(result2.getString("PO_No"), result2.getString("Style"),result2.getString("HSCODE1")," "," ",result2.getString("DESCRIPTION")," ",result2.getString("eqty"), result2.getString("currency"),result2.getString("rate"),result2.getString("fob")));
                    
                            TOT_QNTY2+=result2.getDouble("eqty");
                            TOT_VAL2+=result2.getDouble("fob");
                        ++flag4;
                        }  
                    }
                   */ 
                    TOT_QNTY=TOT_QNTY2;
                    TOT_VAL=TOT_VAL2;
                
                    
                    if (stat2 != null) {
                        stat2.close();
                    }
                    if (result2 != null) {
                        result2.close();
                    }
                    
               }
                    
                    
                    
                }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
              conn.close();
            }
        }


        return SUCCESS;
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
        LOCATION_CODE = "100";
        
         SimpleDateFormat fromUser  = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat myFormat  = new SimpleDateFormat("dd-MMM-yyyy");

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
             
            delTermList.add("");
            delTermList.add("TT 45 DAY'S FROM DOCUMENTS ACCEPTED ON LF PORTAL");
            delTermList.add("TT 45 DAY'S FROM THE DATE OF DISPATCH");
            delTermList.add("TT 45 DAY'S FROM DOCUMENTS ACCEPTED ON TRADE CARD PORTAL");
            delTermList.add("TT 45 DAY'S UPON RECEIPT OF SHIPMENT DOCUMENTS");
            
            if (flag4 == flagVAL) {
                stat5 = conn.prepareStatement("select year,COMPANY,INV_NO from ei_endors_mast where EXCS_INV_NO=?");
                stat5.setString(1, INVOICE_S);
                result5 = stat5.executeQuery();
                if (result5.next()) {
                    yr = result5.getString("year");
                    comp = result5.getString("COMPANY");
                    invn = result5.getString("INV_NO");
                }

                stat2 = conn.prepareStatement("select * from ei_other_inv where EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S);
                result2 = stat2.executeQuery();
                if (result2.next()) {
                    
                    stat4 = conn.prepareStatement("UPDATE ei_other_inv SET SHIPMENT_TYPE=?,QUALITY=?,CREADIT_NO=?,REMARK=?,PAYMENT_TERM=?,GR_WT=?,NET_WT=?,AGENT=?,AUTH_NAME=?,CONTAINER_NUMB=?,SEASON=?,DIVISION=?,SHIP_DATE=?,"
                            + "BOX_DIM=?,COLOUR_CODE=?,DM_CODE=?,BT_CODE=?,LDP_RATE=?,LDP_FOB=?,colour=? WHERE EXCS_INV_NO=?");
                    stat4.setString(1, SHIPAUTH.toUpperCase());
                    stat4.setString(2, QUALTY.toUpperCase());
                    stat4.setString(3, PMNO.toUpperCase());
                    stat4.setString(4, STYLENM.toUpperCase());
                    stat4.setString(5, THDIVI.toUpperCase());
                    stat4.setString(6, GROSSWT);
                    stat4.setString(7, NETNETWT);
                    stat4.setString(8, MNUFFACT.toUpperCase());
                    stat4.setString(9, SEASON.toUpperCase());
                    stat4.setString(10, DIVIS.toUpperCase());
                    stat4.setString(11, FABTYP.toUpperCase());
                    stat4.setString(12, LICCODE.toUpperCase());
                    stat4.setString(13, CARGEDATE!=null && CARGEDATE.length()>0?myFormat.format(fromUser.parse(CARGEDATE.trim())):"");
                    stat4.setString(14, BOXMSRMNT);
                    stat4.setString(15, INV_TPE);
                    stat4.setString(16, NOTIFY1.toUpperCase());
                    stat4.setString(17, NOTIFY2.toUpperCase());
                    stat4.setString(18,SIGN_REQ);
                    stat4.setString(19,REX_REQ);
                    stat4.setString(20,DEL_TERM);
                    stat4.setString(21, INVOICE_S);
                    x = stat4.executeUpdate();
                    if (x > 0) {
                        ++x;
                        conn.commit();
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result2 != null) {
                    result2.close();
                }

                stat6 = conn.prepareStatement("delete from ei_matalan_inv where INV_NO=? and year=? and company=?");
                stat6.setString(1, invn);
                stat6.setString(2, yr);
                stat6.setString(3, comp);
                stat6.executeUpdate();
                if(stat6!=null){
                    stat6.close();
                }


                for (int i = 0; i < PONO.size(); i++) {
                    if (PONO != null && PONO.get(i).toString().length() > 0) {
                        stat = conn.prepareStatement("select * from ei_other_inv where EXCS_INV_NO=?");
                        stat.setString(1, INVOICE_S);
                        result = stat.executeQuery();
                        if (result.next()) {

                            stat4 = conn.prepareStatement("insert into ei_matalan_inv(PO_NO,STYLE_NO,HTS_CODE,LINE_CODE,COLR_DESC,INV_DESC,PKGS,INV_QTY,INV_CRNCY,INV_RATE,TDATE,inv_no,year,company) values(?,?,?,?,?,?,?,?,?,?,trunc(sysdate),?,?,?)");
                            stat4.setString(1, PONO.get(i).toString());
                            stat4.setString(2, STYLENO.get(i).toString().toUpperCase());
                            stat4.setString(3, HTSCODE.get(i).toString().toUpperCase());
                            stat4.setString(4, COLORCODE.get(i).toString().toUpperCase());
                            stat4.setString(5, COLORDESC.get(i).toString().toUpperCase());
                            stat4.setString(6, INVDESC.get(i).toString().toUpperCase());
                            stat4.setString(7, CTN.get(i).toString());
                            stat4.setString(8, QNTY.get(i).toString());
                            stat4.setString(9, CURRENCY.get(i).toString());
                            stat4.setString(10, INVRATE.get(i).toString());
                            stat4.setString(11, invn);
                            stat4.setString(12, yr);
                            stat4.setString(13, comp);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                                conn.commit();
                            }

                        }
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result != null) {
                    result.close();
                }


                if (x > 0 && z > 0) {
                    addActionMessage("Record Updated succcessfully ");

                    
                    SHIPAUTH= null;
                    QUALTY= null;
                    PMNO= null;
                    STYLENM= null;
                    THDIVI= null;
                    GROSSWT= null;
                    NETNETWT= null;
                    MNUFFACT= null;
                    SEASON= null;
                    DIVIS= null;
                    FABTYP= null;
                    LICCODE= null;
                    CARGEDATE= null;
                    BOXMSRMNT= null;
                    
                    
                    PONO = null;
                    STYLENO = null;
                    HTSCODE = null;
                    COLORCODE = null;
                    COLORDESC = null;
                    INVDESC = null;
                    CTN = null;
                    QNTY = null;
                    CURRENCY = null;
                    INVRATE = null;
                    SEPLRATE = null;
                    FOB = null;
                    INVOICE_S = null;
                    
                    
                    

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
                if (result5 != null) {
                    result5.close();
                }
                if (stat5 != null) {
                    stat5.close();
                }

            } else {

                stat5 = conn.prepareStatement("select year,COMPANY,INV_NO,BUYER from ei_endors_mast where EXCS_INV_NO=?");
                stat5.setString(1, INVOICE_S);
                result5 = stat5.executeQuery();
                if (result5.next()) {
                    yr = result5.getString("year");
                    comp = result5.getString("COMPANY");
                    invn = result5.getString("INV_NO");
                    byr = result5.getString("BUYER");
                }

                stat2 = conn.prepareStatement("select * from ei_other_inv where EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S);
                result2 = stat2.executeQuery();
                if (result2.next()) {
                    stat4 = conn.prepareStatement("UPDATE ei_other_inv SET SHIPMENT_TYPE=?,QUALITY=?,CREADIT_NO=?,REMARK=?,PAYMENT_TERM=?,GR_WT=?,NET_WT=?,AGENT=?,AUTH_NAME=?,CONTAINER_NUMB=?,SEASON=?,DIVISION=?,SHIP_DATE=?,"
                            + "BOX_DIM=?,COLOUR_CODE=?,DM_CODE=?,BT_CODE=?,ldp_rate=?,ldp_fob=?,COLOUR=? WHERE EXCS_INV_NO=?");
                    stat4.setString(1, SHIPAUTH.toUpperCase());
                    stat4.setString(2, QUALTY.toUpperCase());
                    stat4.setString(3, PMNO.toUpperCase());
                    stat4.setString(4, STYLENM.toUpperCase());
                    stat4.setString(5, THDIVI.toUpperCase());
                    stat4.setString(6, GROSSWT);
                    stat4.setString(7, NETNETWT);
                    stat4.setString(8, MNUFFACT.toUpperCase());
                    stat4.setString(9, SEASON.toUpperCase());
                    stat4.setString(10, DIVIS.toUpperCase());
                    stat4.setString(11, FABTYP.toUpperCase());
                    stat4.setString(12, LICCODE.toUpperCase());
                    stat4.setString(13, myFormat.format(fromUser.parse(CARGEDATE.trim())));
                    stat4.setString(14, BOXMSRMNT.toUpperCase());
                    stat4.setString(15, INV_TPE.toUpperCase());
                    stat4.setString(16, NOTIFY1.toUpperCase());
                    stat4.setString(17, NOTIFY2.toUpperCase());
                    stat4.setString(18,SIGN_REQ);
                    stat4.setString(19,REX_REQ);
                    stat4.setString(20,DEL_TERM);
                    stat4.setString(21, INVOICE_S);
                    
                    x = stat4.executeUpdate();
                    if (x > 0) {
                        ++x; 
                        conn.commit();
                    }
                    if (stat4 != null) {
                        stat4.close();
                    }
                    if (result2 != null) {
                        result2.close();
                    }

                } else {
                    
                    stat4 = conn.prepareStatement("insert into ei_other_inv(SHIPMENT_TYPE,QUALITY,CREADIT_NO,REMARK,PAYMENT_TERM,GR_WT,NET_WT,AGENT,AUTH_NAME,CONTAINER_NUMB,SEASON,DIVISION,SHIP_DATE,BOX_DIM,TDATE,inv_no,year,company,BUYER,EXCS_INV_NO,COLOUR_CODE,DM_CODE,BT_CODE,LDP_RATE,LDP_FOB,COLOUR) "+
                                                  " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,trunc(sysdate),?,?,?,?,?,?,?,?,?,?,?)");
                    stat4.setString(1, SHIPAUTH!=null && SHIPAUTH.length()>0?SHIPAUTH.trim():"");
                    stat4.setString(2, QUALTY!=null && QUALTY.length()>0?QUALTY.trim():"");
                    stat4.setString(3, PMNO!=null && PMNO.length()>0?PMNO.trim():"");
                    stat4.setString(4, STYLENM!=null && STYLENM.length()>0?STYLENM.trim():"");
                    stat4.setString(5, THDIVI!=null && THDIVI.length()>0?THDIVI.trim():"");
                    stat4.setString(6, GROSSWT!=null && GROSSWT.length()>0?GROSSWT.trim():"");
                    stat4.setString(7, NETNETWT!=null && NETNETWT.length()>0?NETNETWT:"");
                    stat4.setString(8, MNUFFACT!=null && MNUFFACT.length()>0?MNUFFACT.trim():"");
                    stat4.setString(9, SEASON!=null && SEASON.length()>0?SEASON.trim():"");
                    stat4.setString(10, DIVIS!=null && DIVIS.length()>0?DIVIS.trim():"");
                    stat4.setString(11, FABTYP!=null && FABTYP.length()>0?FABTYP.trim():"");
                    stat4.setString(12, LICCODE!=null && LICCODE.length()>0?LICCODE.trim():"");
                    stat4.setString(13, CARGEDATE!=null && CARGEDATE.length()>0?myFormat.format(fromUser.parse(CARGEDATE.trim())):"");
                    stat4.setString(14, BOXMSRMNT!=null && BOXMSRMNT.length()>0?BOXMSRMNT.trim():"");
                    stat4.setString(15, invn);
                            stat4.setString(16, yr);
                            stat4.setString(17, comp);
                            stat4.setString(18, byr);
                            stat4.setString(19, INVOICE_S.trim());
                            stat4.setString(20, INV_TPE!=null && INV_TPE.length()>0?INV_TPE.trim():"");
                            stat4.setString(21, NOTIFY1!=null && NOTIFY1.length()>0?NOTIFY1.trim():"");
                            stat4.setString(22, NOTIFY2!=null && NOTIFY2.length()>0?NOTIFY2.trim():"");
                            stat4.setString(23,SIGN_REQ);
                            stat4.setString(24,REX_REQ);
                            stat4.setString(25,DEL_TERM);
                    x = stat4.executeUpdate();
                    if (x > 0) {
                        ++x;
                        conn.commit();
                    }
                    if (stat4 != null) {
                        stat4.close();
                    }
                    if (result2 != null) {
                        result2.close();
                    }

                }


                stat6 = conn.prepareStatement("delete from ei_matalan_inv where INV_NO=? and year=? and company=?");
                stat6.setString(1, invn);
                stat6.setString(2, yr);
                stat6.setString(3, comp);
                stat6.executeUpdate();


                for (int i = 0; i < PONO.size(); i++) {

                    if (PONO != null && PONO.get(i).toString().length() > 0) {
                        stat = conn.prepareStatement("select * from ei_other_inv where EXCS_INV_NO=?");
                        stat.setString(1, INVOICE_S);
                        result = stat.executeQuery();
                        if (result.next()) {

                            stat4 = conn.prepareStatement("insert into ei_matalan_inv(PO_NO,STYLE_NO,HTS_CODE,LINE_CODE,COLR_DESC,INV_DESC,PKGS,INV_QTY,INV_CRNCY,INV_RATE,TDATE,inv_no,year,company) values(?,?,?,?,?,?,?,?,?,?,trunc(sysdate),?,?,?)");
                            stat4.setString(1, PONO.get(i).toString().trim());
                            stat4.setString(2, STYLENO.get(i).toString().trim());
                            stat4.setString(3, HTSCODE.get(i).toString().trim());
                            stat4.setString(4, COLORCODE.get(i).toString().trim());
                            stat4.setString(5, COLORDESC.get(i).toString().trim());
                            stat4.setString(6, INVDESC.get(i).toString().trim());
                            stat4.setString(7, CTN.get(i).toString().trim());
                            stat4.setString(8, QNTY.get(i).toString().trim());
                            stat4.setString(9, CURRENCY.get(i).toString().trim());
                            stat4.setString(10, INVRATE.get(i).toString().trim());
                            stat4.setString(11, invn);
                            stat4.setString(12, yr);
                            stat4.setString(13, comp);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                                conn.commit();
                            }
                    }
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result != null) {
                    result.close();
                }


                if (x > 0 && z > 0) {
                    addActionMessage("Record inserted succcessfully ");

                    SHIPAUTH= null;
                    QUALTY= null;
                    PMNO= null;
                    STYLENM= null;
                    THDIVI= null;
                    GROSSWT= null;
                    NETNETWT= null;
                    MNUFFACT= null;
                    SEASON= null;
                    DIVIS= null;
                    FABTYP= null;
                    LICCODE= null;
                    CARGEDATE= null;
                    BOXMSRMNT= null;
                    NOTIFY1=null;
                    NOTIFY2=null;
                    
                    
                    PONO = null;
                    STYLENO = null;
                    HTSCODE = null;
                    COLORCODE = null;
                    COLORDESC = null;
                    INVDESC = null;
                    CTN = null;
                    QNTY = null;
                    CURRENCY = null;
                    INVRATE = null;
                    SEPLRATE = null;
                    FOB = null;
                    INVOICE_S = null;
                    
            delTermList.add("");        
            delTermList.add("TT 45 DAY'S FROM DOCUMENTS ACCEPTED ON LF PORTAL");
            delTermList.add("TT 45 DAY'S FROM THE DATE OF DISPATCH");
            delTermList.add("TT 45 DAY'S FROM DOCUMENTS ACCEPTED ON TRADE CARD PORTAL");
            delTermList.add("TT 45 DAY'S UPON RECEIPT OF SHIPMENT DOCUMENTS");
            

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
                if (result5 != null) {
                    result5.close();
                }
                if (stat5 != null) {
                    stat5.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
              conn.close();
            }
        }


        // execute();

        return "updte";
    }
     
    
    public String prints() throws FileNotFoundException, JRException, IOException, SQLException {

        Connection conn = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch

        Connection connBI = null;
        Connection conndb2=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        
         
 
        try {
            
             conn=new ConnectionSeplWeb().getConnection();
            conndb2=new connectiondb2().getConnection();
             List KohlsBeanlist=new ArrayList();
             
               //Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(notify) notify,agent,fwd_code,hs_code,TRIM(manuf_code) manuf_code,"
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,LOADING_port,trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,place,fwd_custom,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,a.lcno,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) tax_cal_per,"
                            +" a.transport_cost,facility, CTNS from ei_endors_mast a  where  a.excs_inv_no=? ");
                    stat1.setString(1, INVOICE_S);
                     
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next()) 
                    {  
                        KohlsBean bean = new KohlsBean(); 
       
                        
                        bean.setLocation(result1.getString("location"));
                        bean.setExcs_inv_no(result1.getString("excs_inv_no"));
                        bean.setPlan_no(result1.getString("plan_no"));
                        bean.setInv_date(result1.getString("inv_date"));
                        bean.setExp_type(result1.getString("exp_type"));
                        bean.setHs_code(result1.getString("hs_code"));
                        bean.setLcno(result1.getString("lcno")); 
                        bean.setComm_per(result1.getString("comm_per"));
                        bean.setUpcharge_per(result1.getDouble("upcharge_per"));
                        bean.setPre_carriage(result1.getString("pre_carriage"));
                        bean.setCost_centre(result1.getString("cost_centre"));
                        bean.setMode_of_ship(result1.getString("mode_of_ship")); 
                        bean.setBuyer(result1.getString("buyer"));
                        bean.setBuyer_addr(result1.getString("buyer_addr").trim());
                        bean.setCons_addr(result1.getString("cons_addr").trim());
                        bean.setShip_term(result1.getString("ship_term"));
                        bean.setAgent(result1.getString("agent"));
                        bean.setFwd_code(result1.getString("fwd_code"));
                        bean.setFwd_custom(result1.getString("fwd_custom")); 
                        bean.setMANUF_CODE(result1.getString("manuf_code"));
                        bean.setNotify(result1.getString("notify"));
                        bean.setPay_term(result1.getString("pay_term"));
                        bean.setTransport_cost(result1.getDouble("transport_cost"));
                        bean.setPayment_term(result1.getString("payment_term"));
                        bean.setLOADING_PORT(result1.getString("LOADING_PORT"));
                        bean.setCLR_PORT(result1.getString("CLR_PORT"));
                        bean.setDESTI_CNTRY(result1.getString("DESTI_CNTRY"));
                        bean.setDIS_CNTRY(result1.getString("DIS_CNTRY"));
                        bean.setCNTRY_ORIGIN(result1.getString("CNTRY_ORIGIN"));
                        bean.setDISCHARGE(result1.getString("DISCHARGE"));
                        bean.setDESTI_CODE(result1.getString("DESTI_CODE"));
                        bean.setPLACE(result1.getString("place"));
                        bean.setCRNCY_CODE(result1.getString("crncy_code"));
                        bean.setMANUF_STATE(result1.getString("manuf_state"));
                        bean.setTAX_TYPE(result1.getString("tax_type"));
                        bean.setTAX_PERCENT(result1.getDouble("tax_percent"));
                        bean.setTAX_CODE(result1.getString("tax_code"));
                        bean.setTAX_CAL_PER(result1.getDouble("tax_cal_per"));
                        bean.setSHIP_DESC(result1.getString("SHIP_DESC"));
                        bean.setCTNS(result1.getString("CTNS"));
                        bean.setFACILITY(result1.getString("facility"));
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
                        
                        invq=invq+result1.getString("inv_qty"); 
                        bean.setInv_qty(invq);
                        
                        bean.setINV_NO(result1.getString("inv_no"));
                           UnitBean bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("MODE_OF_SHIP"),"MODL");
                            bean.setMode_of_ship(bn.getUNIT_ADDRESS());
                           
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DIS_CNTRY"),"CSCD");
                          bean.setDIS_CNTRY_DESC(bn.getUNIT_DESC());
                        
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("CNTRY_ORIGIN"),"CSCD");
                          bean.setCNTRY_ORIGIN_DESC(bn.getUNIT_DESC());
                           bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DIS_CNTRY"),"CSCD");
                          bean.setDESTI_CNTRY_DESC(bn.getUNIT_DESC());
                         
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("LOADING_PORT"),"HAFE");
                          //LOADING_PORT_DESC=bn.getUNIT_ADDRESS();
                            bean.setLOADING_PORT_DESC(bn.getUNIT_DESC().trim()); 
                             
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("CLR_PORT"),"HAFE");
                          bean.setCLR_PORT_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DISCHARGE"),"SDST");
                          bean.setDISCHARGE_DESC(bn.getUNIT_DESC());
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("PLACE"),"EDES");
                          bean.setPLACE_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("exp_type"),"PRGP");
                          if (result1.getString("exp_type").equals("GMT"))
                          {bean.setExp_type_desc("READY MADE GARMENTS");}
                          else {bean.setExp_type_desc(bn.getUNIT_DESC());}
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("PAY_TERM"),"TEPY");
                          bean.setPay_term_desc(bn.getUNIT_DESC());
                          
                           bn=new  PreInvoiceDao().getCHAName(result1.getString("agent"));
                          bean.setCHA_NAME(bn.getUNIT_DESC());
                          
                          bn=new PreInvoiceDao().getCHAName(result1.getString("fwd_code"));
                          bean.setFWD_NAME(bn.getUNIT_DESC());
                          
                          bn=new PreInvoiceDao().getUnitByName(result1.getString("manuf_code"));
                          bean.setMANUF_DESC(bn.getUNIT_DESC());
                          bean.setMANUF_ADDRESS(bn.getUNIT_ADDRESS());
                          stat=conn.prepareStatement("select type_desc from ei_Grup_type_dtls where grup_type_code='SHT' and type_code=?");
                          stat.setString(1,result1.getString("payment_term"));
                          result=stat.executeQuery();
                          if(result.next())
                          {
                          bean.setPayment_term(result.getString("type_desc"));
                          }
                          if (result1.getString("crncy_code").equals("INR"))
                          {
                             stat=conndb2.prepareStatement("select  opcunm cons_name,rtrim(OPCUA1)||'  '||rtrim(OpCUA2)||'  '||rtrim(OpCUA3)||' '||rtrim(OpCUA4)  c_address ,OPVRNO from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and  OPCUNO=? and ltrim(rtrim(opadid))=?  ");
                              
                             stat.setString(1,result1.getString("buyer"));
                             stat.setString(2,result1.getString("cons_addr"));
                             result=stat.executeQuery();
                 
                             if (result.next())
                             {
                                bean.setCons_name(result.getString("cons_name"));
                                bean.setCons_address(result.getString("c_address"));
                           //     bean.setCon_cst(result.getString("ccst")); 
                           //     bean.setCon_tin(result.getString("ctin"));
                             }
                             
                          }
                          
                          
                         SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yy");
                         SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy");
                       
                         
                         String fwd_nm="";
                           PreparedStatement stat3 = conn.prepareStatement("select IDSUNM from ei_endors_mast a,cidmas_M4 b where a.FWD_CODE=b.IDSUNO and a.EXCS_INV_NO=?");
                           stat3.setString(1,INVOICE_S);
                           ResultSet rs3=stat3.executeQuery();
                           if(rs3.next()){
                            fwd_nm= rs3.getString("IDSUNM");
                           } 
                           bean.setFWDCONSO(fwd_nm);
                           
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                         
                         
                           String vnd_nm="";String vnd_add="";
                           stat3 = conn.prepareStatement("select VEND_NAME,VEND_ADDR from ei_other_inv a,pr_vend_mast b where a.agent=b.vend_code and a.EXCS_INV_NO=?  ");
                           stat3.setString(1,INVOICE_S);
                           rs3=stat3.executeQuery();
                            if(rs3.next()){
                            vnd_nm= rs3.getString("VEND_NAME");
                            vnd_add= rs3.getString("VEND_ADDR");
                           } 
                            bean.setVENDNM(vnd_nm);
                            bean.setVENDADD(vnd_add);
                           if(stat3!=null){
                              stat3.close(); 
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                         
             
                         List InvLineList = new ArrayList();
                       String po_no="";double trcost=0.0;double iqty=0.0; double tqty=0; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double tinr=0.0; double inrconv=0.0;
                        int cartn=0;int cartn_tot=0; double netfc=0.0;  double up=0.0; double tax_a =0.0; double tax_v=0.0; double comm_amt=0.0;
                        BigDecimal g1 = new BigDecimal("0.00");double excise_d=0.0;double tnetpcs=0.0;double tot_inv=0.0;double rate_inv=0.0;
                        String pono="";String styleno="";String htsno="";String vesletddt="";
                        String desc="";String pcs="";double rate=0.0;double totamt=0.0;
                        String color_code="";String color_desc="";
                           
                           
                           stat3 = conn.prepareStatement("select b.LINE_CODE,b.COLR_DESC,b.PO_NO,b.STYLE_NO,b.HTS_CODE,b.INV_DESC,b.INV_QTY,b.INV_RATE,to_char(a.ETD_DATE,'dd-Mon-yyyy') ETD_DATE,nvl(b.PKGS,0) PKGS from ei_endors_mast a,ei_matalan_inv b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.EXCS_INV_NO=?");
                           stat3.setString(1,INVOICE_S);
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                            pono= rs3.getString("PO_NO");
                            styleno= rs3.getString("STYLE_NO");
                            htsno= rs3.getString("HTS_CODE");
                            desc= rs3.getString("INV_DESC");
                            pcs= rs3.getString("INV_QTY");
                            rate= rs3.getDouble("INV_RATE");
                            cartn=rs3.getInt("PKGS");
                            totamt=Double.valueOf(pcs)*rate;
                           
                            
                            tnetpcs=tnetpcs+Double.valueOf(pcs);
                            tfob=tfob+totamt;
                            vesletddt=rs3.getString("ETD_DATE");
                            cartn_tot=cartn_tot+cartn;
                            
                            InvLineList.add(new PvhSubLstBean(pono,styleno,htsno,desc,pcs,rate,totamt,cartn,rs3.getString("LINE_CODE"),rs3.getString("COLR_DESC")));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           } 
                       
                        
                        
                        netfc=roundTwoDecimals(tfob);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTFOB(netfc);
                        bean.setTINVQTY(tnetpcs);
                        bean.setLDPRT(vesletddt);
                        bean.setTOT_CRTN(String.valueOf(cartn_tot));
                        
                        
                      
                       String decimal_print="";
                        stat=conn.prepareStatement("select nvl(decimal_print,'Cents') dp from ei_currency_mast where currency=? ");
                        stat.setString(1,result1.getString("crncy_code").trim());
                        result=stat.executeQuery();
                        if (result.next())
                        {decimal_print=result.getString("dp");
                        }
                        
                        String wordfc="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,netfc);
                        stat.setDouble(2,netfc);
                        stat.setDouble(3,netfc); 
                        result=stat.executeQuery();
                        if (result.next())
                        {   wordfc=result.getString("aa");
                            
                                if (result.getString("aa1")!=null)
                                {wordfc=wordfc+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                }else{wordfc=wordfc+" Only.";}
                            bean.setAMT_IN_WORD(wordfc);
                        } 
                        
                        
                        String wordEX_DUTY="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,excise_d);
                        stat.setDouble(2,excise_d);
                        stat.setDouble(3,excise_d); 
                        result=stat.executeQuery();
                        if (result.next())
                        {   wordEX_DUTY=result.getString("aa");
                            
                                if (result.getString("aa1")!=null)
                                {wordEX_DUTY=wordEX_DUTY+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                }else{wordEX_DUTY=wordEX_DUTY+" Only.";}
                            bean.setAMT_IN_WORD2(wordEX_DUTY);
                        }  
                             
                        List BPOList = new ArrayList();   String mbpo="";
                        stat = conn.prepareStatement("select distinct trim(b.PO_NO) PO_NO from ei_endors_mast a,ei_matalan_inv b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.EXCS_INV_NO=?" );
                        stat.setString(1,INVOICE_S);
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo=mbpo+result.getString("PO_NO")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                        if (mbpo!=null){
                        mbpo=mbpo.substring(0, mbpo.length()-1);}
                       
                         BPOList.add(new GetListBean(mbpo,mbpo));
                         bean.setBPOLIST(BPOList);
                        List StyList = new ArrayList();   String msty="";
                        stat = conn.prepareStatement("select distinct trim(token_no) token_no from ei_endors_dtls where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  msty=msty+result.getString("token_no")+",";
                       //    StyList.add(new GetListBean(result.getString("token_no"),result.getString("token_no")));      
                        }
                        if (msty!=null){  
                                msty=msty.substring(0, msty.length()-1);}
                        StyList.add(new GetListBean(msty,msty));
                         bean.setSTYLIST(StyList);
                            
                        List DbkList = new ArrayList();  String mdbk="";  int chkdbk=0;
                        stat = conn.prepareStatement("select distinct trim(dbk_slno) dbk_slno from ei_endors_dtls where dbk_slno is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mdbk=mdbk+result.getString("dbk_slno")+",";
                           chkdbk=chkdbk+1;
                              // DbkList.add(new GetListBean(result.getString("dbk_slno"),result.getString("dbk_slno")));      
                        } 
                                  
                        if (mdbk!=""){
                          mdbk=mdbk.substring(0, mdbk.length()-1);}
                             
                          DbkList.add(new GetListBean(mdbk,mdbk));      
                    
                        bean.setDBKLIST(DbkList);  
                        bean.setCHKDBK(chkdbk);
                        List StrList = new ArrayList(); String mstr=""; 
                        stat = conn.prepareStatement("select distinct trim(str_slno) str_slno from ei_endors_dtls where str_slno is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {   mstr=mstr+result.getString("str_slno")+",";
                           
                           //StrList.add(new GetListBean(result.getString("str_slno"),result.getString("str_slno")));      
                        }
                        String mitype=""; 
                        stat = conn.prepareStatement(" select DISTINCT  decode(made_for,'SAMPLE','SM','DEEC','DC','DBKDEEC','DD','DBK','DB','DOMESTIC','DOM') SHIPTYPE from ei_endors_dtls where  made_for is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {     
                         mitype=mitype+result.getString("SHIPTYPE")+",";
                         }
                        if (mitype!=""){   
                           mitype=mitype.substring(0, mitype.length()-1);}
                           bean.setMITYPE(mitype);
                        
                        stat = conn.prepareStatement("select distinct str_misc from ei_endors_dtls where str_misc is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {    
                         mstr=mstr+result.getString("str_misc")+",";
                          // StrList.add(new GetListBean(result.getString("str_misc"),result.getString("str_misc")));      
                        }
                        if (mstr!=""){   
                        mstr=mstr.substring(0, mstr.length()-1);}
                          StrList.add(new GetListBean(mstr,mstr));
                       // bean.setSTRMISCLIST(StrMiscList);
                        bean.setSTRLIST(StrList);
          
                                 
                        List HngrList = new ArrayList();
                        stat = conn.prepareStatement("select  sum(qty_endors)||'  HANGERS  @ ' ||RTRIM(NVL(PRICE_MISC,0)||'0   Per Pcs.')  hh from ei_endors_dtls where  year=? and company = ?  and inv_no =?  and nvl(price_misc,0)<>0 group by price_misc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  
                           HngrList.add(new GetListBean(result.getString("hh"),result.getString("hh")));      
                        }
                       // bean.setHNGRLIST(HngrList);
                        
                        List AccrList = new ArrayList(); 
                        double accrfob=0.0;   int chkdbkaccr=0;
                        stat = conn.prepareStatement("select sum(ACCR_QTY)||' '||TRIM(ACCR_DESC)||' @ ' ||LTRIM(TO_CHAR(ACCR_PRICE,'99.99')||' Per Pcs.')  hh,sum(accr_qty*accr_price) accr_fob from ei_endors_ACCR_dtls where  year =? and company =? and inv_no =?  and nvl(ACCR_PRICE,0)<>0  group by year,company,inv_no,ACCR_PRICE,ACCR_DESC order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  accrfob=accrfob+result.getDouble("accr_fob");
                          
                           HngrList.add(new GetListBean(result.getString("hh"),result.getString("accr_fob")));      
                        }
                        double accrdbkfob=0.0;
                        stat = conn.prepareStatement("select sum(accr_qty*accr_price) accr_dbk from ei_endors_ACCR_dtls where ACCR_DBKSLNO IS NOT NULL and year =? and company =? and inv_no =?  and nvl(ACCR_PRICE,0)<>0  " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        if(result.next())
                        {  accrdbkfob=result.getDouble("accr_dbk");
                           chkdbkaccr=chkdbkaccr+1;   
                        }
                      
                        accrfob=accrfob*inrconv;
                        accrdbkfob=accrdbkfob*inrconv;
                        tmiscinr=tmiscinr+accrfob-accrdbkfob;
                        bean.setHNGRLIST(HngrList);
                        bean.setTMISCINR(tmiscinr);  
                          
                        tdbkinr=tdbkinr-tmiscinr; 
                        
                         bean.setTDBKINR(tdbkinr);  
                        bean.setCHKDBKACCR(chkdbkaccr);
                        
                        List AccrdbkList = new ArrayList();
                        stat = conn.prepareStatement("select  distinct accr_desc,accr_dbkslno from ei_endors_ACCR_dtls where year=? and company = ? and inv_no =?  and accr_dbkslno is not null order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {
                            
                           AccrdbkList.add(new GetListBean(result.getString("accr_desc"),result.getString("accr_dbkslno")));      
                        }
                        
                        bean.setACCRDBKLIST(AccrdbkList);
                                 
                         List AccrstrList = new ArrayList(); int chkstraccr=0;
                        stat = conn.prepareStatement("select  distinct accr_desc,accr_strslno from ei_endors_ACCR_dtls where year=? and company = ? and inv_no =?  and accr_strslno is not null order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  chkstraccr=chkstraccr+1;
                           AccrstrList.add(new GetListBean(result.getString("accr_desc"),result.getString("accr_strslno")));      
                        }
                       
                        bean.setACCRSTRLIST(AccrstrList);
                        bean.setCHKSTRACCR(chkstraccr);
                        
                        List BEList = new ArrayList();  int chkbe=0;
                        stat = conn.prepareStatement("select Distinct B.be_desc, F.QTY_SQM, to_char(G.BE_DATE,'dd-Mon-yyyy') be_date, F.B_E_NO,F.SR_NO from pi_imp_awbl_mast A, pi_imp_cinv_lic_dtls b, pi_imp_awbl_cinv c, pi_imp_boe_dtls d , pi_imp_lic_dtls e, ei_endors_lc_lic_dtls F , PI_IMP_BOE_MAST G  where a.ref_no=c.ref_no and b.ind_no = c.ind_no and b.cinv_no = c.cinv_no and d.ref_no = a.ref_No AND G.BE_NO = D.BE_NO  and e.ref_type = b.lic_type and e.ref_no = b.lic_no and e.item_no = b.item_no  AND D.be_no=F.b_e_no and   B.lic_TYPE = F.REF_TYPE AND B.lic_NO = F.REF_NO AND B.ITEM_NO=F.imp_ref_ctrl_no and f.year=? and f.company=? and f.inv_no=?" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {   chkbe=chkbe+1;
                              BEList.add(new BEListBean(result.getString("be_desc"),result.getString("B_E_NO"),result.getString("BE_DATE"),roundTwoDecimals(result.getDouble("QTY_SQM"))));      
                        } 
                        
                        bean.setBELIST(BEList);
                        bean.setCHKBE(chkbe);
                           
                                
                        stat3 = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                        stat3.setString(1, result1.getString("BUYER"));
                        stat3.setString(2, result1.getString("BUYER_ADDR"));
                        ResultSet result3 = stat3.executeQuery();
                        if (result3.next() == true) {
                          bean.setBuyer_name(result3.getString("opcunm"));
                          bean.setBuyer_address(result3.getString("opadd"));
                         }   
              
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER"));
                       stat.setString(2, result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setCons_name(result.getString("opcunm"));
                           bean.setCons_address(result.getString("opadd"));
                        }
                       if(result!=null){
                           result.close();
                       } 
                       if(stat!=null){
                           stat.close();
                       }
                       
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER"));
                       stat.setString(2, result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setCons_name(result.getString("opcunm"));
                           bean.setCons_address(result.getString("opadd"));
                        }
                       if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                       
                       String notifi1="";
                       String notifi2="";
                       stat = conn.prepareStatement("select NET_WT,GR_WT,NET_NET_WT,CREADIT_NO,nvl(LDP_RATE,0) ldp_rate,nvl(ldp_fob,0) ldp_fob,QUALITY,AUTH_NAME,REMARK,SEASON,DIVISION,to_char(SHIP_DATE,'dd-Mon-yyyy') SHIP_DATE,PAYMENT_TERM,BOX_DIM,SHIPMENT_TYPE,DM_CODE,BT_CODE,colour  from ei_other_inv where EXCS_INV_NO=?");
                       stat.setString(1,INVOICE_S);
                       result = stat.executeQuery();
                       if (result.next() == true) {
                           
                           notifi1=result.getString("DM_CODE");
                           notifi2=result.getString("BT_CODE");
                           
                           bean.setMODLNO(result.getString("SHIPMENT_TYPE"));
                            
                           bean.setNTWT(result.getString("NET_WT"));
                           bean.setGROSWT(result.getString("GR_WT"));
                           bean.setCBMVAL(result.getString("NET_NET_WT"));
                           bean.setCREATIONNO(result.getString("CREADIT_NO"));
                          
                           
                           bean.setFIRSTQLITY(result.getString("QUALITY"));
                           bean.setSESON(result.getString("AUTH_NAME"));
                           bean.setSTYLENM(result.getString("REMARK"));
                           bean.setTHDIVI(result.getString("PAYMENT_TERM"));
                           bean.setFABTYP(result.getString("SEASON"));
                           bean.setLICCODE(result.getString("DIVISION"));
                           bean.setCRGODT(result.getString("SHIP_DATE"));
                          // bean.setVESLDT(result.getString("LDP_RATE"));
                           bean.setCTNMNT(result.getString("BOX_DIM"));
                           bean.setSIGN_REQ(result.getString("ldp_rate"));
                           bean.setREX_REQ(result.getString("ldp_fob"));
                           bean.setDEL_TERM(result.getString("colour"));
                        }   
                       if(result!=null){ 
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                       
                       //DON'T DELETE BELOW COMMENT
                    if(notifi1!=null && notifi1.length()>0){   
                         stat=conndb2.prepareStatement("select sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  m3fdbprd.cidadr where  sacono=111 and sasuno=? and SAADTE='1' and saadid IN ('001','01')"); 
                          stat.setString(1,notifi1.trim());
                          result=stat.executeQuery();
                          if (result.next() == true) 
                        { 
                          bean.setNOTIFY_NAME(result.getString("sasunm"));
                          bean.setNOTIFY_ADDRESS(result.getString("noticity"));
                        }
                          if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                    }
                          
                       if(notifi2!=null && notifi2.length()>0){
                            stat=conndb2.prepareStatement("select sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  m3fdbprd.cidadr where  sacono=111 and sasuno=? and SAADTE='1' and saadid IN ('001','01') "); 
                            stat.setString(1,notifi2.trim());
                            result=stat.executeQuery();
                            if (result.next() == true) 
                          { 
                            bean.setNOTIFY_NAME1(result.getString("sasunm")); 
                            bean.setNOTIFY_ADDRESS1(result.getString("noticity")); 
                          }
                            if(result!=null){  
                             result.close();
                         }
                         if(stat!=null){
                             stat.close();
                         }
                       }
                       
                        
                        KohlsBeanlist.add(bean);
        
                    }
                    
 
                if (INVOICE_S!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/BuyerInv");
                    Map param1 = new HashMap();

                    
                    InputStream input;
                    param1.put("SUBREPORT_DIR", path);
 
                   if(CNTRY.equals("CA")){ 
                   input =new FileInputStream(new File(path+"/PvhCommercialInvoice_CA.jrxml"));
                   }
                   else{
                    input =new FileInputStream(new File(path+"/PvhCommercialInvoice.jrxml"));  
                   }
                     
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(KohlsBeanlist));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=PvhCommercialInvoice.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
                     out1.flush();
                     out1.close();
            }
            else
            {
                        JRXlsExporter exporter = new JRXlsExporter();
                        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, path + "\\");
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "PvhCommercialInvoice.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=PvhCommercialInvoice.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("PvhCommercialAction" + e.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DecathlaneAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DecathlaneAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DecathlaneAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        
        FLAG1="Yes";
        
        execute();

        return "prnt";
    }
    
    public String printsIZOD_CKJ() throws FileNotFoundException, JRException, IOException, SQLException {

        Connection conn = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch

        Connection connBI = null;
        Connection conndb2=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        
        

        try {
            
             conn=new ConnectionSeplWeb().getConnection();
              conndb2=new connectiondb2().getConnection();
             List KohlsBeanlist=new ArrayList();
             
               //Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(notify) notify,agent,fwd_code,hs_code,TRIM(manuf_code) manuf_code,"
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,LOADING_port,trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,place,fwd_custom,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,a.lcno,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) tax_cal_per,"
                            +" a.transport_cost,facility,CTNS from ei_endors_mast a  where  a.excs_inv_no=? ");
                    stat1.setString(1, INVOICE_S);
                     
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next()) 
                    {  
                        KohlsBean bean = new KohlsBean();
       
                        
                        bean.setLocation(result1.getString("location"));
                        bean.setExcs_inv_no(result1.getString("excs_inv_no"));
                        bean.setPlan_no(result1.getString("plan_no"));
                        bean.setInv_date(result1.getString("inv_date"));
                        bean.setExp_type(result1.getString("exp_type"));
                        bean.setHs_code(result1.getString("hs_code"));
                        bean.setLcno(result1.getString("lcno")); 
                        bean.setComm_per(result1.getString("comm_per"));
                        bean.setUpcharge_per(result1.getDouble("upcharge_per"));
                        bean.setPre_carriage(result1.getString("pre_carriage"));
                        bean.setCost_centre(result1.getString("cost_centre"));
                        bean.setMode_of_ship(result1.getString("mode_of_ship")); 
                        bean.setBuyer(result1.getString("buyer"));
                        bean.setBuyer_addr(result1.getString("buyer_addr").trim());
                        bean.setCons_addr(result1.getString("cons_addr").trim());
                        bean.setShip_term(result1.getString("ship_term"));
                        bean.setAgent(result1.getString("agent"));
                        bean.setFwd_code(result1.getString("fwd_code"));
                        bean.setFwd_custom(result1.getString("fwd_custom")); 
                        bean.setMANUF_CODE(result1.getString("manuf_code"));
                        bean.setNotify(result1.getString("notify"));
                        bean.setPay_term(result1.getString("pay_term"));
                        bean.setTransport_cost(result1.getDouble("transport_cost"));
                        bean.setPayment_term(result1.getString("payment_term"));
                        bean.setLOADING_PORT(result1.getString("LOADING_PORT"));
                        bean.setCLR_PORT(result1.getString("CLR_PORT"));
                        bean.setDESTI_CNTRY(result1.getString("DESTI_CNTRY"));
                        bean.setDIS_CNTRY(result1.getString("DIS_CNTRY"));
                        bean.setCNTRY_ORIGIN(result1.getString("CNTRY_ORIGIN"));
                        bean.setDISCHARGE(result1.getString("DISCHARGE"));
                        bean.setDESTI_CODE(result1.getString("DESTI_CODE"));
                        bean.setPLACE(result1.getString("place"));
                        bean.setCRNCY_CODE(result1.getString("crncy_code"));
                        bean.setMANUF_STATE(result1.getString("manuf_state"));
                        bean.setTAX_TYPE(result1.getString("tax_type"));
                        bean.setTAX_PERCENT(result1.getDouble("tax_percent"));
                        bean.setTAX_CODE(result1.getString("tax_code"));
                        bean.setTAX_CAL_PER(result1.getDouble("tax_cal_per"));
                        bean.setSHIP_DESC(result1.getString("SHIP_DESC"));
                        bean.setCTNS(result1.getString("CTNS"));
                        bean.setFACILITY(result1.getString("facility"));
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
                        
                        invq=invq+result1.getString("inv_qty"); 
                        bean.setInv_qty(invq);
                        
                        bean.setINV_NO(result1.getString("inv_no"));
                           UnitBean bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("MODE_OF_SHIP"),"MODL");
                            bean.setMode_of_ship(bn.getUNIT_ADDRESS());
                           
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DIS_CNTRY"),"CSCD");
                          bean.setDIS_CNTRY_DESC(bn.getUNIT_DESC());
                        
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("CNTRY_ORIGIN"),"CSCD");
                          bean.setCNTRY_ORIGIN_DESC(bn.getUNIT_DESC());
                           bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DIS_CNTRY"),"CSCD");
                          bean.setDESTI_CNTRY_DESC(bn.getUNIT_DESC());
                         
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("LOADING_PORT"),"HAFE");
                          //LOADING_PORT_DESC=bn.getUNIT_ADDRESS();
                            bean.setLOADING_PORT_DESC(bn.getUNIT_DESC()); 
                             
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("CLR_PORT"),"HAFE");
                          bean.setCLR_PORT_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DISCHARGE"),"SDST");
                          bean.setDISCHARGE_DESC(bn.getUNIT_DESC());
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("PLACE"),"EDES");
                          bean.setPLACE_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("exp_type"),"PRGP");
                          if (result1.getString("exp_type").equals("GMT"))
                          {bean.setExp_type_desc("READY MADE GARMENTS");}
                          else {bean.setExp_type_desc(bn.getUNIT_DESC());}
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("PAY_TERM"),"TEPY");
                          bean.setPay_term_desc(bn.getUNIT_DESC());
                          
                           bn=new  PreInvoiceDao().getCHAName(result1.getString("agent"));
                          bean.setCHA_NAME(bn.getUNIT_DESC());
                          
                          bn=new PreInvoiceDao().getCHAName(result1.getString("fwd_code"));
                          bean.setFWD_NAME(bn.getUNIT_DESC());
                          
                          bn=new PreInvoiceDao().getUnitByName(result1.getString("manuf_code"));
                          bean.setMANUF_DESC(bn.getUNIT_DESC());
                          bean.setMANUF_ADDRESS(bn.getUNIT_ADDRESS());
                          stat=conn.prepareStatement("select type_desc from ei_Grup_type_dtls where grup_type_code='SHT' and type_code=?");
                          stat.setString(1,result1.getString("payment_term"));
                          result=stat.executeQuery();
                          if(result.next())
                          {
                          bean.setPayment_term(result.getString("type_desc"));
                          }
                          if (result1.getString("crncy_code").equals("INR"))
                          {
                             stat=conndb2.prepareStatement("select  opcunm cons_name,rtrim(OPCUA1)||'  '||rtrim(OpCUA2)||'  '||rtrim(OpCUA3)||' '||rtrim(OpCUA4)  c_address ,OPVRNO from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and  OPCUNO=? and ltrim(rtrim(opadid))=?  ");
                              
                             stat.setString(1,result1.getString("buyer"));
                             stat.setString(2,result1.getString("cons_addr"));
                             result=stat.executeQuery();
                 
                             if (result.next())
                             {
                                bean.setCons_name(result.getString("cons_name"));
                                bean.setCons_address(result.getString("c_address"));
                           //     bean.setCon_cst(result.getString("ccst")); 
                           //     bean.setCon_tin(result.getString("ctin"));
                             }
                             
                          }
                          
                          
                         SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yy");
                         SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy");
                       
                         
                         String fwd_nm="";
                           PreparedStatement stat3 = conn.prepareStatement("select IDSUNM from ei_endors_mast a,cidmas b where a.FWD_CODE=b.IDSUNO and a.EXCS_INV_NO=?");
                           stat3.setString(1,INVOICE_S);
                           ResultSet rs3=stat3.executeQuery();
                           if(rs3.next()){
                            fwd_nm= rs3.getString("IDSUNM");
                           } 
                           bean.setFWDCONSO(fwd_nm);
                           
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                         
                         
                           String vnd_nm="";String vnd_add="";
                           stat3 = conn.prepareStatement("select VEND_NAME,VEND_ADDR from ei_other_inv a,pr_vend_mast b where a.agent=b.vend_code and a.EXCS_INV_NO=?  ");
                           stat3.setString(1,INVOICE_S);
                           rs3=stat3.executeQuery();
                            if(rs3.next()){
                            vnd_nm= rs3.getString("VEND_NAME");
                            vnd_add= rs3.getString("VEND_ADDR");
                           } 
                            bean.setVENDNM(vnd_nm);
                            bean.setVENDADD(vnd_add);
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                         
             
                         List InvLineList = new ArrayList();
                       String po_no="";double trcost=0.0;double iqty=0.0; double tqty=0; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double tinr=0.0; double inrconv=0.0;
                        int cartn=0;int cartn_tot=0; double netfc=0.0;  double up=0.0; double tax_a =0.0; double tax_v=0.0; double comm_amt=0.0;
                        BigDecimal g1 = new BigDecimal("0.00");double excise_d=0.0;double tnetpcs=0.0;double tot_inv=0.0;double rate_inv=0.0;
                        String pono="";String styleno="";String htsno="";String vesletddt="";
                        String invdesc="";String desc="";String pcs="";double rate=0.0;double totamt=0.0;
                     
                           
                           
                           stat3 = conn.prepareStatement("select b.LINE_CODE,b.COLR_DESC,b.PO_NO,b.STYLE_NO,b.HTS_CODE,b.INV_DESC,b.INV_QTY,b.INV_RATE,(b.LINE_CODE||'-'||b.COLR_DESC) code_color,to_char(a.ETD_DATE,'dd-Mon-yyyy') ETD_DATE,nvl(b.PKGS,0) PKGS from ei_endors_mast a,ei_matalan_inv b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.EXCS_INV_NO=?");
                           stat3.setString(1,INVOICE_S);
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                               
                              invdesc=rs3.getString("INV_DESC");
                                      
                            pono= rs3.getString("PO_NO");
                            styleno= rs3.getString("STYLE_NO");
                            htsno= rs3.getString("HTS_CODE");
                            desc= rs3.getString("code_color");
                            pcs= rs3.getString("INV_QTY");
                            rate= rs3.getDouble("INV_RATE");
                            vesletddt=rs3.getString("ETD_DATE");
                            cartn=rs3.getInt("PKGS");
                            totamt=Double.valueOf(pcs)*rate;
                            
                            tnetpcs=tnetpcs+Double.valueOf(pcs);
                            tfob=tfob+totamt;
                            cartn_tot=cartn_tot+cartn; 
                             
                            InvLineList.add(new PvhSubLstBean(pono,styleno,htsno,desc,pcs,rate,totamt,cartn,rs3.getString("LINE_CODE"),rs3.getString("COLR_DESC")));
                           } 
                           if(stat3!=null){
                              stat3.close(); 
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                       
                        bean.setComm_per(invdesc);
                        
                        netfc=roundTwoDecimals(tfob);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTFOB(netfc);
                        bean.setTINVQTY(tnetpcs);
                        bean.setLDPRT(vesletddt);
                        bean.setTOT_CRTN(String.valueOf(cartn_tot));
                        
                        
                      
                       String decimal_print="";
                        stat=conn.prepareStatement("select nvl(decimal_print,'Cents') dp from ei_currency_mast where currency=? ");
                        stat.setString(1,result1.getString("crncy_code").trim());
                        result=stat.executeQuery();
                        if (result.next())
                        {decimal_print=result.getString("dp");
                        }
                        
                        String wordfc="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,netfc);
                        stat.setDouble(2,netfc);
                        stat.setDouble(3,netfc); 
                        result=stat.executeQuery();
                        if (result.next())
                        {   wordfc=result.getString("aa");
                            
                                if (result.getString("aa1")!=null)
                                {wordfc=wordfc+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                }else{wordfc=wordfc+" Only.";}
                            bean.setAMT_IN_WORD(wordfc);
                        } 
                        
                        
                        String wordEX_DUTY="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,excise_d);
                        stat.setDouble(2,excise_d);
                        stat.setDouble(3,excise_d); 
                        result=stat.executeQuery();
                        if (result.next())
                        {   wordEX_DUTY=result.getString("aa");
                            
                                if (result.getString("aa1")!=null)
                                {wordEX_DUTY=wordEX_DUTY+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                }else{wordEX_DUTY=wordEX_DUTY+" Only.";}
                            bean.setAMT_IN_WORD2(wordEX_DUTY);
                        }  
                             
                        List BPOList = new ArrayList();   String mbpo="";
                        stat = conn.prepareStatement("select distinct trim(b.PO_NO) PO_NO from ei_endors_mast a,ei_matalan_inv b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.EXCS_INV_NO=?" );
                        stat.setString(1,INVOICE_S);
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo=mbpo+result.getString("PO_NO")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                        if (mbpo!=null){
                        mbpo=mbpo.substring(0, mbpo.length()-1);}
                       
                         BPOList.add(new GetListBean(mbpo,mbpo));
                         bean.setBPOLIST(BPOList);
                        List StyList = new ArrayList();   String msty="";
                        stat = conn.prepareStatement("select distinct trim(token_no) token_no from ei_endors_dtls where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  msty=msty+result.getString("token_no")+",";
                       //    StyList.add(new GetListBean(result.getString("token_no"),result.getString("token_no")));      
                        }
                        if (msty!=null){  
                                msty=msty.substring(0, msty.length()-1);}
                        StyList.add(new GetListBean(msty,msty));
                         bean.setSTYLIST(StyList);
                            
                        List DbkList = new ArrayList();  String mdbk="";  int chkdbk=0;
                        stat = conn.prepareStatement("select distinct trim(dbk_slno) dbk_slno from ei_endors_dtls where dbk_slno is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mdbk=mdbk+result.getString("dbk_slno")+",";
                           chkdbk=chkdbk+1;
                              // DbkList.add(new GetListBean(result.getString("dbk_slno"),result.getString("dbk_slno")));      
                        } 
                                  
                        if (mdbk!=""){
                          mdbk=mdbk.substring(0, mdbk.length()-1);}
                             
                          DbkList.add(new GetListBean(mdbk,mdbk));      
                    
                        bean.setDBKLIST(DbkList);  
                        bean.setCHKDBK(chkdbk);
                        List StrList = new ArrayList(); String mstr=""; 
                        stat = conn.prepareStatement("select distinct trim(str_slno) str_slno from ei_endors_dtls where str_slno is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {   mstr=mstr+result.getString("str_slno")+",";
                           
                           //StrList.add(new GetListBean(result.getString("str_slno"),result.getString("str_slno")));      
                        }
                        String mitype=""; 
                        stat = conn.prepareStatement(" select DISTINCT  decode(made_for,'SAMPLE','SM','DEEC','DC','DBKDEEC','DD','DBK','DB','DOMESTIC','DOM') SHIPTYPE from ei_endors_dtls where  made_for is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {     
                         mitype=mitype+result.getString("SHIPTYPE")+",";
                         }
                        if (mitype!=""){   
                           mitype=mitype.substring(0, mitype.length()-1);}
                           bean.setMITYPE(mitype);
                        
                        stat = conn.prepareStatement("select distinct str_misc from ei_endors_dtls where str_misc is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {    
                         mstr=mstr+result.getString("str_misc")+",";
                          // StrList.add(new GetListBean(result.getString("str_misc"),result.getString("str_misc")));      
                        }
                        if (mstr!=""){   
                        mstr=mstr.substring(0, mstr.length()-1);}
                          StrList.add(new GetListBean(mstr,mstr));
                       // bean.setSTRMISCLIST(StrMiscList);
                        bean.setSTRLIST(StrList);
          
                                 
                        List HngrList = new ArrayList();
                        stat = conn.prepareStatement("select  sum(qty_endors)||'  HANGERS  @ ' ||RTRIM(NVL(PRICE_MISC,0)||'0   Per Pcs.')  hh from ei_endors_dtls where  year=? and company = ?  and inv_no =?  and nvl(price_misc,0)<>0 group by price_misc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  
                           HngrList.add(new GetListBean(result.getString("hh"),result.getString("hh")));      
                        }
                       // bean.setHNGRLIST(HngrList);
                        
                        List AccrList = new ArrayList(); 
                        double accrfob=0.0;   int chkdbkaccr=0;
                        stat = conn.prepareStatement("select sum(ACCR_QTY)||' '||TRIM(ACCR_DESC)||' @ ' ||LTRIM(TO_CHAR(ACCR_PRICE,'99.99')||' Per Pcs.')  hh,sum(accr_qty*accr_price) accr_fob from ei_endors_ACCR_dtls where  year =? and company =? and inv_no =?  and nvl(ACCR_PRICE,0)<>0  group by year,company,inv_no,ACCR_PRICE,ACCR_DESC order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  accrfob=accrfob+result.getDouble("accr_fob");
                          
                           HngrList.add(new GetListBean(result.getString("hh"),result.getString("accr_fob")));      
                        }
                        double accrdbkfob=0.0;
                        stat = conn.prepareStatement("select sum(accr_qty*accr_price) accr_dbk from ei_endors_ACCR_dtls where ACCR_DBKSLNO IS NOT NULL and year =? and company =? and inv_no =?  and nvl(ACCR_PRICE,0)<>0  " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        if(result.next())
                        {  accrdbkfob=result.getDouble("accr_dbk");
                           chkdbkaccr=chkdbkaccr+1;   
                        }
                      
                        accrfob=accrfob*inrconv;
                        accrdbkfob=accrdbkfob*inrconv;
                        tmiscinr=tmiscinr+accrfob-accrdbkfob;
                        bean.setHNGRLIST(HngrList);
                        bean.setTMISCINR(tmiscinr);  
                          
                        tdbkinr=tdbkinr-tmiscinr; 
                        
                         bean.setTDBKINR(tdbkinr);  
                        bean.setCHKDBKACCR(chkdbkaccr);
                        
                        List AccrdbkList = new ArrayList();
                        stat = conn.prepareStatement("select  distinct accr_desc,accr_dbkslno from ei_endors_ACCR_dtls where year=? and company = ? and inv_no =?  and accr_dbkslno is not null order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {
                            
                           AccrdbkList.add(new GetListBean(result.getString("accr_desc"),result.getString("accr_dbkslno")));      
                        }
                        
                        bean.setACCRDBKLIST(AccrdbkList);
                                 
                         List AccrstrList = new ArrayList(); int chkstraccr=0;
                        stat = conn.prepareStatement("select  distinct accr_desc,accr_strslno from ei_endors_ACCR_dtls where year=? and company = ? and inv_no =?  and accr_strslno is not null order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  chkstraccr=chkstraccr+1;
                           AccrstrList.add(new GetListBean(result.getString("accr_desc"),result.getString("accr_strslno")));      
                        }
                       
                        bean.setACCRSTRLIST(AccrstrList);
                        bean.setCHKSTRACCR(chkstraccr);
                        
                        List BEList = new ArrayList();  int chkbe=0;
                        stat = conn.prepareStatement("select Distinct B.be_desc, F.QTY_SQM, to_char(G.BE_DATE,'dd-Mon-yyyy') be_date, F.B_E_NO,F.SR_NO from pi_imp_awbl_mast A, pi_imp_cinv_lic_dtls b, pi_imp_awbl_cinv c, pi_imp_boe_dtls d , pi_imp_lic_dtls e, ei_endors_lc_lic_dtls F , PI_IMP_BOE_MAST G  where a.ref_no=c.ref_no and b.ind_no = c.ind_no and b.cinv_no = c.cinv_no and d.ref_no = a.ref_No AND G.BE_NO = D.BE_NO  and e.ref_type = b.lic_type and e.ref_no = b.lic_no and e.item_no = b.item_no  AND D.be_no=F.b_e_no and   B.lic_TYPE = F.REF_TYPE AND B.lic_NO = F.REF_NO AND B.ITEM_NO=F.imp_ref_ctrl_no and f.year=? and f.company=? and f.inv_no=?" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {   chkbe=chkbe+1;
                              BEList.add(new BEListBean(result.getString("be_desc"),result.getString("B_E_NO"),result.getString("BE_DATE"),roundTwoDecimals(result.getDouble("QTY_SQM"))));      
                        } 
                        
                        bean.setBELIST(BEList);
                        bean.setCHKBE(chkbe);
                           
                                
                        stat3 = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                        stat3.setString(1, result1.getString("BUYER"));
                        stat3.setString(2, result1.getString("BUYER_ADDR"));
                        ResultSet result3 = stat3.executeQuery();
                        if (result3.next() == true) {
                          bean.setBuyer_name(result3.getString("opcunm"));
                          bean.setBuyer_address(result3.getString("opadd"));
                         }   
              
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER"));
                       stat.setString(2, result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setCons_name(result.getString("opcunm"));
                           bean.setCons_address(result.getString("opadd"));
                        }
                       if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                       
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER"));
                       stat.setString(2, result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setCons_name(result.getString("opcunm"));
                           bean.setCons_address(result.getString("opadd"));
                        }
                       if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                       
                      String notifi1="";
                       String notifi2="";
                       stat = conn.prepareStatement("select DM_CODE,BT_CODE,NET_WT,GR_WT,NET_NET_WT,CREADIT_NO,LDP_RATE,QUALITY,AUTH_NAME,REMARK,SEASON,DIVISION,to_char(SHIP_DATE,'dd-Mon-yyyy') SHIP_DATE,PAYMENT_TERM,BOX_DIM,SHIPMENT_TYPE,nvl(ldp_rate,0) ldp_rate,nvl(ldp_fob,0) ldp_fob,colour from ei_other_inv where EXCS_INV_NO=?");
                       stat.setString(1,INVOICE_S);
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            notifi1=result.getString("DM_CODE");
                            notifi2=result.getString("BT_CODE");
                           bean.setMODLNO(result.getString("SHIPMENT_TYPE"));
                            
                           bean.setNTWT(result.getString("NET_WT"));
                           bean.setGROSWT(result.getString("GR_WT"));
                           bean.setCBMVAL(result.getString("NET_NET_WT"));
                           bean.setCREATIONNO(result.getString("CREADIT_NO"));
                          
                           
                           bean.setFIRSTQLITY(result.getString("QUALITY"));
                           bean.setSESON(result.getString("AUTH_NAME"));
                           bean.setSTYLENM(result.getString("REMARK"));
                           bean.setTHDIVI(result.getString("PAYMENT_TERM"));
                           bean.setFABTYP(result.getString("SEASON"));
                           bean.setLICCODE(result.getString("DIVISION"));
                           bean.setCRGODT(result.getString("SHIP_DATE"));
                          // bean.setVESLDT(result.getString("LDP_RATE"));
                           bean.setCTNMNT(result.getString("BOX_DIM"));
                            bean.setSIGN_REQ(result.getString("ldp_rate"));
                           bean.setREX_REQ(result.getString("ldp_fob"));
                           bean.setDEL_TERM(result.getString("colour"));
                           
                        }   
                       if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                       
                    if(notifi1!=null && notifi1.length()>0){   
                         stat=conndb2.prepareStatement("select sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  m3fdbprd.cidadr where  sacono=111 and sasuno=? and SAADTE='1' and saadid IN ('001','01')"); 
                          stat.setString(1,notifi1.trim());
                          result=stat.executeQuery();
                          if (result.next() == true) 
                        { 
                          bean.setNOTIFY_NAME(result.getString("sasunm"));
                          bean.setNOTIFY_ADDRESS(result.getString("noticity"));
                        }
                          if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                    }
                        
                        KohlsBeanlist.add(bean);
        
                    }
                    

                if (INVOICE_S!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/BuyerInv");
                    Map param1 = new HashMap();

                    
                    InputStream input;
                    param1.put("SUBREPORT_DIR", path);
                   
                   input =new FileInputStream(new File(path+"/IzodCommercialInvoice.jrxml"));
                  
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(KohlsBeanlist));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=PvhCommercialInvoice.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
                     out1.flush();
                     out1.close();
            }
            else
            { 
                        JRXlsExporter exporter = new JRXlsExporter();
                        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, path + "\\");
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "PvhCommercialInvoice.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=PvhCommercialInvoice.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("PvhCommercialAction" + e.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DecathlaneAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DecathlaneAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DecathlaneAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
         FLAG1="Yes";
        
        execute();

        return "prnt";
    }
    
    
    public String searchcodedesc() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connection().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn.prepareStatement("select VEND_CODE,VEND_NAME from pr_vend_mast where 1=1 and (VEND_CODE like ? or VEND_NAME like ?)");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("VEND_CODE"), result.getString("VEND_NAME")+"***"+result.getString("VEND_CODE")));
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

        return "srch";
    }
    public String searchcodedesc1() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
         

        Connection db2con = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            db2con = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = db2con.prepareStatement("select sasuno,sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  m3fdbprd.cidadr where  sacono=111 and sasuno like ? and SAADTE='1' and saadid IN ('001','01')");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                     
                    SearchList.add(new SearchListBean(result.getString("sasuno"), result.getString("sasunm")+"***"+result.getString("sasuno")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (db2con != null) {
                db2con.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return "srch1";
    }
    public String searchcodedesc2() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        

        Connection db2con = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            db2con = new connectiondb2().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = db2con.prepareStatement("select sasuno,sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  m3fdbprd.cidadr where  sacono=111 and sasuno like ? and SAADTE='1' and saadid IN ('001','01') ");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("sasuno"), result.getString("sasunm")+"***"+result.getString("sasuno")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (db2con != null) {
                db2con.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return "srch2";
    }

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public Double getTOTQTY() {
        return TOTQTY;
    }

    public void setTOTQTY(Double TOTQTY) {
        this.TOTQTY = TOTQTY;
    }

    public Double getTOTFOB() {
        return TOTFOB;
    }

    public void setTOTFOB(Double TOTFOB) {
        this.TOTFOB = TOTFOB;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getCBM() {
        return CBM;
    }

    public void setCBM(String CBM) {
        this.CBM = CBM;
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

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public String getINVOICE_S() {
        return INVOICE_S;
    }

    public void setINVOICE_S(String INVOICE_S) {
        this.INVOICE_S = INVOICE_S;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getINVOICENO() {
        return INVOICENO;
    }

    public void setINVOICENO(String INVOICENO) {
        this.INVOICENO = INVOICENO;
    }

    public String getINVOICEDATE() {
        return INVOICEDATE;
    }

    public void setINVOICEDATE(String INVOICEDATE) {
        this.INVOICEDATE = INVOICEDATE;
    }

    public String getFLAG1() {
        return FLAG1;
    }

    public void setFLAG1(String FLAG1) {
        this.FLAG1 = FLAG1;
    }

    public String getFLAG2() {
        return FLAG2;
    }

    public void setFLAG2(String FLAG2) {
        this.FLAG2 = FLAG2;
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

    public String getBUYER_DESC() {
        return BUYER_DESC;
    }

    public void setBUYER_DESC(String BUYER_DESC) {
        this.BUYER_DESC = BUYER_DESC;
    }

    public String getBUYER_ADDR() {
        return BUYER_ADDR;
    }

    public void setBUYER_ADDR(String BUYER_ADDR) {
        this.BUYER_ADDR = BUYER_ADDR;
    }

    public String getPLANNO_N() {
        return PLANNO_N;
    }

    public void setPLANNO_N(String PLANNO_N) {
        this.PLANNO_N = PLANNO_N;
    }

    public String getTTO_DATE() {
        return TTO_DATE;
    }

    public void setTTO_DATE(String TTO_DATE) {
        this.TTO_DATE = TTO_DATE;
    }

    public String getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(String TO_DATE) {
        this.TO_DATE = TO_DATE;
    }

    public String getFIN_DATE() {
        return FIN_DATE;
    }

    public void setFIN_DATE(String FIN_DATE) {
        this.FIN_DATE = FIN_DATE;
    }

      

    public String getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(String INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public String getSHIP_QTY() {
        return SHIP_QTY;
    }

    public void setSHIP_QTY(String SHIP_QTY) {
        this.SHIP_QTY = SHIP_QTY;
    }

    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getNET_WEIGHT() {
        return NET_WEIGHT;
    }

    public void setNET_WEIGHT(String NET_WEIGHT) {
        this.NET_WEIGHT = NET_WEIGHT;
    }
    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getREPORTTYPE() {
        return REPORTTYPE;
    }

    public void setREPORTTYPE(String REPORTTYPE) {
        this.REPORTTYPE = REPORTTYPE;
    }

    public String getNETWT() {
        return NETWT;
    }

    public void setNETWT(String NETWT) {
        this.NETWT = NETWT;
    }

    public String getGROSSWT() {
        return GROSSWT;
    }

    public void setGROSSWT(String GROSSWT) {
        this.GROSSWT = GROSSWT;
    }

    public String getNETNETWT() {
        return NETNETWT;
    }

    public void setNETNETWT(String NETNETWT) {
        this.NETNETWT = NETNETWT;
    }

    public List getPVHList() {
        return PVHList;
    }

    public void setPVHList(List PVHList) {
        this.PVHList = PVHList;
    }

    

    public List getSTYLENO() {
        return STYLENO;
    }

    public void setSTYLENO(List STYLENO) {
        this.STYLENO = STYLENO;
    }

    public List getPONO() {
        return PONO;
    }

    public void setPONO(List PONO) {
        this.PONO = PONO;
    }

    public int getFlag4() {
        return flag4;
    }

    public void setFlag4(int flag4) {
        this.flag4 = flag4;
    }

    public Double getTOT_QNTY() {
        return TOT_QNTY;
    }

    public void setTOT_QNTY(Double TOT_QNTY) {
        this.TOT_QNTY = TOT_QNTY;
    }

    public Double getTOT_VAL() {
        return TOT_VAL;
    }

    public void setTOT_VAL(Double TOT_VAL) {
        this.TOT_VAL = TOT_VAL;
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

    public List getSearchList3() {
        return SearchList3;
    }

    public void setSearchList3(List SearchList3) {
        this.SearchList3 = SearchList3;
    }

    public String getFLG1() {
        return FLG1;
    }

    public void setFLG1(String FLG1) {
        this.FLG1 = FLG1;
    }

    public String getFLG2() {
        return FLG2;
    }

    public void setFLG2(String FLG2) {
        this.FLG2 = FLG2;
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

    public String getSHIPAUTH() {
        return SHIPAUTH;
    }

    public void setSHIPAUTH(String SHIPAUTH) {
        this.SHIPAUTH = SHIPAUTH;
    }

    public String getQUALTY() {
        return QUALTY;
    }

    public void setQUALTY(String QUALTY) {
        this.QUALTY = QUALTY;
    }

    public String getSTYLENM() {
        return STYLENM;
    }

    public void setSTYLENM(String STYLENM) {
        this.STYLENM = STYLENM;
    }

    public String getTHDIVI() {
        return THDIVI;
    }

    public void setTHDIVI(String THDIVI) {
        this.THDIVI = THDIVI;
    }

    public String getMNUFFACT() {
        return MNUFFACT;
    }

    public void setMNUFFACT(String MNUFFACT) {
        this.MNUFFACT = MNUFFACT;
    }

    public String getSEASON() {
        return SEASON;
    }

    public void setSEASON(String SEASON) {
        this.SEASON = SEASON;
    }

    public String getDIVIS() {
        return DIVIS;
    }

    public void setDIVIS(String DIVIS) {
        this.DIVIS = DIVIS;
    }

    public String getFABTYP() {
        return FABTYP;
    }

    public void setFABTYP(String FABTYP) {
        this.FABTYP = FABTYP;
    }

    public String getLICCODE() {
        return LICCODE;
    }

    public void setLICCODE(String LICCODE) {
        this.LICCODE = LICCODE;
    }

    public String getCARGEDATE() {
        return CARGEDATE;
    }

    public void setCARGEDATE(String CARGEDATE) {
        this.CARGEDATE = CARGEDATE;
    }

    public String getBOXMSRMNT() {
        return BOXMSRMNT;
    }

    public void setBOXMSRMNT(String BOXMSRMNT) {
        this.BOXMSRMNT = BOXMSRMNT;
    }

    public String getPMNO() {
        return PMNO;
    }

    public void setPMNO(String PMNO) {
        this.PMNO = PMNO;
    }

    public List getHTSCODE() {
        return HTSCODE;
    }

    public void setHTSCODE(List HTSCODE) {
        this.HTSCODE = HTSCODE;
    }

    public List getCOLORCODE() {
        return COLORCODE;
    }

    public void setCOLORCODE(List COLORCODE) {
        this.COLORCODE = COLORCODE;
    }

    public List getCOLORDESC() {
        return COLORDESC;
    }

    public void setCOLORDESC(List COLORDESC) {
        this.COLORDESC = COLORDESC;
    }

    public List getINVDESC() {
        return INVDESC;
    }

    public void setINVDESC(List INVDESC) {
        this.INVDESC = INVDESC;
    }

    public List getCTN() {
        return CTN;
    }

    public void setCTN(List CTN) {
        this.CTN = CTN;
    }

    public List getQNTY() {
        return QNTY;
    }

    public void setQNTY(List QNTY) {
        this.QNTY = QNTY;
    }

    public List getCURRENCY() {
        return CURRENCY;
    }

    public void setCURRENCY(List CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    public List getINVRATE() {
        return INVRATE;
    }

    public void setINVRATE(List INVRATE) {
        this.INVRATE = INVRATE;
    }

    public List getSEPLRATE() {
        return SEPLRATE;
    }

    public void setSEPLRATE(List SEPLRATE) {
        this.SEPLRATE = SEPLRATE;
    }

    public List getFOB() {
        return FOB;
    }

    public void setFOB(List FOB) {
        this.FOB = FOB;
    }

    public String getTOTCTN() {
        return TOTCTN;
    }

    public void setTOTCTN(String TOTCTN) {
        this.TOTCTN = TOTCTN;
    }

    public Double getTOT_CTN() {
        return TOT_CTN;
    }

    public void setTOT_CTN(Double TOT_CTN) {
        this.TOT_CTN = TOT_CTN;
    }

    public String getCNTRY() {
        return CNTRY;
    }

    public void setCNTRY(String CNTRY) {
        this.CNTRY = CNTRY;
    }

    public int getFlagVAL() {
        return flagVAL;
    }

    public void setFlagVAL(int flagVAL) {
        this.flagVAL = flagVAL;
    }

    public String getINV_TPE() {
        return INV_TPE;
    }

    public void setINV_TPE(String INV_TPE) {
        this.INV_TPE = INV_TPE;
    }

    public String getNOTIFY1() {
        return NOTIFY1;
    }

    public void setNOTIFY1(String NOTIFY1) {
        this.NOTIFY1 = NOTIFY1;
    }

    public String getNOTIFY2() {
        return NOTIFY2;
    }

    public void setNOTIFY2(String NOTIFY2) {
        this.NOTIFY2 = NOTIFY2;
    }

    public String getSIGN_REQ() {
        return SIGN_REQ;
    }

    public void setSIGN_REQ(String SIGN_REQ) {
        this.SIGN_REQ = SIGN_REQ;
    }

    public String getREX_REQ() {
        return REX_REQ;
    }

    public void setREX_REQ(String REX_REQ) {
        this.REX_REQ = REX_REQ;
    }

    public String getDEL_TERM() {
        return DEL_TERM;
    }

    public void setDEL_TERM(String DEL_TERM) {
        this.DEL_TERM = DEL_TERM;
    }

    public List getDelTermList() {
        return delTermList;
    }

    public void setDelTermList(List delTermList) {
        this.delTermList = delTermList;
    }
 
    
 
   
}