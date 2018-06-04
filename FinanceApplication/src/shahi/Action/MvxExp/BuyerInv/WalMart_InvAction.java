package shahi.Action.MvxExp.BuyerInv;

import static com.opensymphony.xwork2.Action.ERROR;
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
import shahi.Action.MvxExp.BuyerInv.Beans.ItemBrkupBean; 
import shahi.Action.MvxExp.BuyerInv.Beans.MaterialBrkupBean; 
import shahi.Action.MvxExp.BuyerInv.Beans.SearchListBean;
import shahi.Action.MvxExp.BuyerInv.Beans.WalMartSubLstBean;
import shahi.Action.MvxExp.BuyerInv.Beans.WalMartwalLstBean;
import shahi.Action.MvxExp.BuyerInv.Beans.WalMrtLstBean;
import shahi.Action.MvxExp.BuyerInv.Beans.WalmartBean;
import shahi.Action.MvxExp.BuyerInv.Beans.WalmartItmBean;
import shahi.Action.MvxExp.BuyerInv.Beans.WalmartListBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.MvxExp.Reports.PRE.bean.BEListBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionMovexBi;
import shahi.Action.database.connectiondb2;

public class WalMart_InvAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

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
    private String TO_DATE_s;
    private String INV_QTY;
    private String SHIP_QTY;
    private String INVNO;
    private String NET_WEIGHT;
    private String LOCATION;
    private String REPORTTYPE;
    private String NETWT;
    private List WalmartList = new ArrayList();
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
    
   
     private String VESELNO;
     private String CRGORCRDDT;
     private String FLGHTNO;
     private String ETDDT;
     private String SHIPCASE;
     private String MANUFCODE;
     private String TARRIFNO;
     private String LCOANO;
     private String LCDESC;
     private String SHIPNGLINE;
     private String POTYP;
     private String DEPTCODE;
     private String DUTY1;
     private String DUTY2;
     private String FROMPORT;
     private String DISCHARGEPORT;
     private String PCKRATIO;
     private String TEXTILE;
     private String MODE;
     private String BA_TYPE;
     private String PAYMNTTRM;
     private String FIBRCNTNT;
     private String MANUFADD;
     
     private List ITMID_L;
     private List ITMIDDESC_L;
     private List PCKGES_L;
     private List RATIO_L;
     private List PCKRATIO_L;
     private List RATE_L;
     private List QTY_L;
     private List NETWGHT_L;
     private List GROSSWGHT_L;
     private List BOX_L_L;
     private List BOX_W_L;
     private List BOX_H_L;
     private List BOX_VOL_L;
     private List TOT_NETWGHT_L;
     private List TOT_GROSSWGHT_L;
     
     private List MTRIALBRKUP;
     
     private List MTRIALBRKUPLIST=new ArrayList();
     private List ITEMBRKUPLIST=new ArrayList();
     
     private double TOT_ITMQTY1;
     private double TOTITEM_QTY;
     private double TOT_QTY;
     private double TOT_PACKG;
     
     private String ITMIDL;
     private double QTYID;
     private String invid;
     
     private List ITEM_NO;
     private List ITEM_ID;
     private List ITEM_RATIO;
     private List ITEM_PCKGS;
     private List ITEM_QTY;
     private double TOTITMQTY;
     private List SRN1;
     private String ITEMNOFIX;
     private String pricefc;
     
     
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
        //LOCATION_CODE = "100";
          if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
       
        try {
            if (FLAG1.equals("Yes") && FLAG1.length() > 0) {
                stat2 = conn.prepareStatement("select a.EXCS_INV_NO,to_char(a.INV_DATE,'yyyy-MM-dd') INV_DATE,a.COMPANY,a.PLAN_NO,a.BUYER,a.BUYER_ADDR,to_char(a.TTO_DATE,'yyyy-MM-dd') TTO_DATE,a.DESTI_CNTRY,to_char(a.T_O_DATE,'yyyy-MM-dd') T_O_DATE,a.INV_QTY,a.SHIP_QTY,a.BUYER_ADDR,a.year,a.LOCATION,b.REMARK,b.CREADIT_NO,b.SHIPMENT_TYPE,b.PACKAGE,b.GR_WT,b.NET_WT,b.NET_NET_WT,b.AGENT from ei_endors_mast a left outer join ei_other_inv b  on(a.year=b.year and a.INV_NO=b.INV_NO and a.company=b.company and a.EXCS_INV_NO=? ) where a.EXCS_INV_NO=?");
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
                    TO_DATE_s = result2.getString("T_O_DATE");
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
                
                                PreparedStatement stat3 = conn.prepareStatement("select min(PRICE_FC) PRICE_FC from ei_endors_dtls  where  all_no=?");
                                stat3.setString(1,INVOICE_S);
                                ResultSet result3 = stat3.executeQuery();
                                if (result3.next()) {
                                pricefc=result3.getString("PRICE_FC");
                                }
                                if(stat3!=null){
                                    stat3.close();
                                }
                                if(result3!=null){
                                    result3.close();
                                }
                                
                
                
                
                String manufadd="";
                stat2 = conn.prepareStatement("select a.VESSEL_NO,to_char(a.VESSEL_DATE,'dd-Mon-yyyy') VESSEL_DATE,a.FLIGHT_NO,to_char(a.ETA_DATE,'dd-Mon-yyyy') ETA_DATE,a.SHIP_CASE,a.MANUF_CODE,a.TARRIF_NO,a.LC_NO,a.REMARK,a.SHIP_LINE,a.PO_TYPE,a.DEPT,a.DUTY,a.DUTY1,a.PORT,a.BH_TYPE,a.DES_PORT,a.RATIO,a.COMMON_NAME,a.SHIPPING_MODE,a.PAYTERM,a.FIBER_CONTENT from EI_WALMART_INV a where a.EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                int q=0;
                if (result2.next()) {
                    ++q;        
                                if(result2.getString("MANUF_CODE")!=null){
                                stat3 = conn.prepareStatement("select VEND_ADDR from pr_vend_mast where vend_code=?");
                                stat3.setString(1,result2.getString("MANUF_CODE").trim());
                                result3 = stat3.executeQuery();
                                if (result3.next()) {
                                manufadd=result3.getString("VEND_ADDR");
                                }
                                }
                                
                    VESELNO = result2.getString("VESSEL_NO");
                    CRGORCRDDT = result2.getString("VESSEL_DATE");
                    FLGHTNO = result2.getString("FLIGHT_NO");
                    ETDDT = result2.getString("ETA_DATE");
                    SHIPCASE = result2.getString("SHIP_CASE");
                    MANUFCODE = result2.getString("MANUF_CODE");
                    TARRIFNO = result2.getString("TARRIF_NO");
                    LCOANO = result2.getString("LC_NO");
                    LCDESC = result2.getString("REMARK");
                    SHIPNGLINE = result2.getString("SHIP_LINE");
                    POTYP = result2.getString("PO_TYPE");
                    DEPTCODE = result2.getString("DEPT");
                    DUTY1 = result2.getString("DUTY");
                    DUTY2 = result2.getString("DUTY1");
                    FROMPORT = result2.getString("PORT");
                    BA_TYPE = result2.getString("BH_TYPE");
                    DISCHARGEPORT = result2.getString("DES_PORT");
                    PCKRATIO = result2.getString("RATIO");
                    TEXTILE = result2.getString("COMMON_NAME");
                    MODE = result2.getString("SHIPPING_MODE");
                    PAYMNTTRM = result2.getString("PAYTERM");
                    FIBRCNTNT = result2.getString("FIBER_CONTENT");
                    MANUFADD=manufadd;
                     
                }else{
                    VESELNO = null;
                    CRGORCRDDT = null;
                    FLGHTNO = null;
                    ETDDT = null;
                    SHIPCASE = null;
                    MANUFCODE = null;
                    TARRIFNO = null;
                    LCOANO = null;
                    LCDESC = null;
                    SHIPNGLINE = null;
                    POTYP = null;
                    DEPTCODE = null;
                    DUTY1 = null;
                    DUTY2 = null;
                    FROMPORT = null;
                    BA_TYPE = null;
                    DISCHARGEPORT = null;
                    PCKRATIO = null;
                    TEXTILE = null;
                    MODE = null;
                    PAYMNTTRM = null;
                    FIBRCNTNT = null;
                    MANUFADD=null;
                     }
                    

                
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }

                double T_NET1=0;
                double T_QNTY1=0;
                double TOT_NET1=0;
                double TOT_QNTY1=0;
                double T_QTY=0;
                double T_PACKG=0;
                
                stat2 = conn.prepareStatement("select a.ITEM_NO,a.ITEM_DESC,a.PKGS,a.RATIO,a.PCS_PER_BOX,a.ITEM_RATE,a.QTY,a.NET_WT,a.GR_WT,a.BOX_L,a.BOX_W,a.BOX_H,a.BOX_VOL,a.SR_NO from EI_WALMART_INV_ITEM_DTLS a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and b.EXCS_INV_NO =? order by a.SR_NO");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                int p=0;
                while (result2.next()) {
                    ++p;
                    T_NET1=roundTwoDecimals(result2.getDouble("PKGS")*result2.getDouble("NET_WT"));
                    T_QNTY1=roundTwoDecimals(result2.getDouble("PKGS")*result2.getDouble("GR_WT"));
                   
                    WalmartList.add(new WalmartListBean(result2.getString("ITEM_NO"), result2.getString("ITEM_DESC"), result2.getString("PKGS"), result2.getString("RATIO"), result2.getString("PCS_PER_BOX"), result2.getString("ITEM_RATE"), result2.getString("QTY"),result2.getString("NET_WT"), result2.getString("GR_WT"),result2.getString("BOX_L"),result2.getString("BOX_W"),result2.getString("BOX_H"),result2.getString("BOX_VOL"),T_NET1,T_QNTY1));  
                    
                     T_PACKG+=roundTwoDecimals(result2.getDouble("PKGS"));
                     T_QTY+=roundTwoDecimals(result2.getDouble("QTY"));
                    
                     TOT_NET1=TOT_NET1+T_NET1;
                     TOT_QNTY1=TOT_QNTY1+T_QNTY1;
                     
                }
                   if(p==0) {
                    ITMID_L = null;
                    ITMIDDESC_L = null;
                    PCKGES_L = null;
                    RATIO_L = null;
                    PCKRATIO_L = null;
                    RATE_L = null;
                    QTY_L = null;
                    NETWGHT_L = null;
                    GROSSWGHT_L = null;
                    BOX_L_L = null;
                    BOX_W_L = null;
                    BOX_H_L = null;
                    BOX_VOL_L = null;
                    TOT_NETWGHT_L = null;
                    TOT_GROSSWGHT_L = null;
                   }
            
                
                    TOT_PACKG=roundTwoDecimals(T_PACKG);
                    TOT_QTY=roundTwoDecimals(T_QTY);
                    TOT_CTN=roundTwoDecimals(TOT_NET1);
                    TOT_QNTY=roundTwoDecimals(TOT_QNTY1);
//                   
                
                    ITMID_L = null;
                    ITMIDDESC_L = null;
                    PCKGES_L = null;
                    RATIO_L = null;
                    PCKRATIO_L = null;
                    RATE_L = null;
                    QTY_L = null;
                    NETWGHT_L = null;
                    GROSSWGHT_L = null;
                    BOX_L_L = null;
                    BOX_W_L = null;
                    BOX_H_L = null;
                    BOX_VOL_L = null;
                    TOT_NETWGHT_L=null;
                    TOT_GROSSWGHT_L=null;
                
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
//                double TOT_QNTY2=0;
//                double TOT_VAL2=0;
//                String clr_desc="";
//                if (x == 0) {
//                    if(CNTRY.equals("CA")){
//                    stat2 = conn.prepareStatement("select a.pre_print_no PO_No,a.token_no Style,a.HSCODE1,a.DESCRIPTION,HMTY15 clrdesc,sum(a.qty_endors) eqty,a.currency,net_price-nvl(accr_price,0) rate,(sum(a.qty_endors)*((a.price_fc+nvl(a.price_misc,0))+sum(a.qty_endors))) fob from finacsys_ei_endors_dtls a,ei_endors_accr_dtls b,mitmah c,  ei_endors_mast d where d.excs_inv_no=? and a.year=d.year and a.company=d.company and a.inv_no=d.inv_no and a.year=b.year(+) and a.company=b.company(+) and a.inv_no=b.inv_no(+) and a.co_no=b.co_no(+) and a.co_line=b.co_line(+) and c.hmcono=111 and c.hmitno=a.item_no group by a.pre_print_no,a.token_no,a.HSCODE1,a.DESCRIPTION,HMTY15,a.currency,(net_price-nvl(accr_price,0)),(a.price_fc+nvl(a.price_misc,0))");
//                    stat2.setString(1, INVOICE_S.trim());
//                    result2 = stat2.executeQuery();
//                    while(result2.next()){
//                      WalmartList.add(new PvhListBean(result2.getString("PO_No"), result2.getString("Style"),result2.getString("HSCODE1")," ",result2.getString("clrdesc"),result2.getString("DESCRIPTION")," ",result2.getString("eqty"), result2.getString("currency"),result2.getString("rate"),result2.getString("fob")));
//                    
//                        TOT_QNTY2+=result2.getDouble("eqty");
//                        TOT_VAL2+=result2.getDouble("fob");
//                        ++flag4;
//                    }
//                    }
//                    else{
//                    stat2 = conn.prepareStatement("select a.pre_print_no PO_No,a.token_no Style,a.HSCODE1,a.DESCRIPTION,sum(a.qty_endors) eqty,a.currency,net_price-nvl(accr_price,0) rate,(sum(a.qty_endors)*((a.price_fc+nvl(a.price_misc,0))+sum(a.qty_endors))) fob from finacsys_ei_endors_dtls a,ei_endors_accr_dtls b,  ei_endors_mast c where c.excs_inv_no=? and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and a.year=b.year(+) and a.company=b.company(+) and a.inv_no=b.inv_no(+) and a.year=b.year(+) and a.company=b.company(+) and a.inv_no=b.inv_no(+) and a.co_no=b.co_no(+) and a.co_line=b.co_line(+)  group by a.pre_print_no,a.token_no,a.HSCODE1,a.DESCRIPTION,a.currency,(net_price-nvl(accr_price,0)),(a.price_fc+nvl(a.price_misc,0))");
//                    stat2.setString(1, INVOICE_S.trim());
//                    result2 = stat2.executeQuery();
//                    while (result2.next()) {
//                      WalmartList.add(new PvhListBean(result2.getString("PO_No"), result2.getString("Style"),result2.getString("HSCODE1")," "," ",result2.getString("DESCRIPTION")," ",result2.getString("eqty"), result2.getString("currency"),result2.getString("rate"),result2.getString("fob")));
//                    
//                            TOT_QNTY2+=result2.getDouble("eqty");
//                            TOT_VAL2+=result2.getDouble("fob");
//                        ++flag4;
//                        }  
//                    }
//                    
//                    TOT_QNTY=TOT_QNTY2;
//                    TOT_VAL=TOT_VAL2;
//                
//                    
//                    if (stat2 != null) {
//                        stat2.close();
//                    }
//                    if (result2 != null) {
//                        result2.close();
//                    }
//                    
//               }
                    
                   
                
                stat2 = conn.prepareStatement("select LINE1 from EI_WALMART_MATERIAL a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and b.EXCS_INV_NO=? order by idno");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                while (result2.next()) {
                  MTRIALBRKUPLIST.add(new MaterialBrkupBean(result2.getString("LINE1")));  
                }
                if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
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
    
    public String clear(){
        
                    INVOICE_S=null;
                    VESELNO = null;
                    CRGORCRDDT = null;
                    FLGHTNO = null;
                    ETDDT = null;
                    SHIPCASE = null;
                    MANUFCODE = null;
                    TARRIFNO = null;
                    LCOANO = null;
                    LCDESC = null;
                    SHIPNGLINE = null;
                    POTYP = null;
                    DEPTCODE = null;
                    DUTY1 = null;
                    DUTY2 = null;
                    FROMPORT = null;
                    BA_TYPE = null;
                    DISCHARGEPORT = null;
                    PCKRATIO = null;
                    TEXTILE = null;
                    MODE = null;
                    PAYMNTTRM = null;
                    FIBRCNTNT = null;
                    MANUFADD=null;
        
                    ITMID_L = null;
                    ITMIDDESC_L = null;
                    PCKGES_L = null;
                    RATIO_L = null;
                    PCKRATIO_L = null;
                    RATE_L = null;
                    QTY_L = null;
                    NETWGHT_L = null;
                    GROSSWGHT_L = null;
                    BOX_L_L = null;
                    BOX_W_L = null;
                    BOX_H_L = null;
                    BOX_VOL_L = null;
                    TOT_NETWGHT_L = null;
                    TOT_GROSSWGHT_L = null;
                   
                    TOT_PACKG=0;
                    TOT_QTY=0;
                    TOT_CTN=null;
                    TOT_QNTY=null;
                  
        return "clr";
    }
    public String brkup(){
        
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
        //LOCATION_CODE = "100";

        
        double itmqty=0.0;
        double tot_itmqty=0.0;
         String yr = null;
         String comp = null;
         String invn = null;
        try {
            
                stat2 = conn.prepareStatement("select YEAR,COMPANY,INV_NO from ei_endors_mast where EXCS_INV_NO =?");
                stat2.setString(1, invid.trim());
                result2 = stat2.executeQuery();
                if(result2.next()) {
                 yr= result2.getString("YEAR");
                 comp=result2.getString("COMPANY");
                 invn=result2.getString("INV_NO");
                }
                 if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
                
                stat2 = conn.prepareStatement("select a.ITEM_NO,a.ITEM_ID,a.PACK_RATIO,a.PACK_QTY from EI_WALMART_ITEM_ID_DTLS a where a.ITEM_NO=? and a.YEAR=? and a.COMPANY=? and a.INV_NO=?");
                stat2.setString(1, ITMIDL.trim());
                stat2.setString(2, yr);
                stat2.setString(3, comp);
                stat2.setString(4, invn);
                result2 = stat2.executeQuery();
                int r=0;
                while (result2.next()) {
                  itmqty=  roundTwoDecimals(Double.valueOf(result2.getString("PACK_RATIO"))*Double.valueOf(result2.getString("PACK_QTY")));
                  ITEMBRKUPLIST.add(new ItemBrkupBean(result2.getString("ITEM_NO"),result2.getString("ITEM_ID"),result2.getString("PACK_RATIO"),result2.getString("PACK_QTY"),itmqty));  
                  tot_itmqty=tot_itmqty+itmqty;
                  
                  ++r;
                }
            
                 TOT_ITMQTY1=roundTwoDecimals(tot_itmqty);
               
             if(r==0){
                if(ITMIDL!=null){
                stat2 = conn.prepareStatement("select ITEM_NO from EI_WALMART_INV_ITEM_DTLS a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and b.EXCS_INV_NO =? and a.ITEM_NO=?");
                stat2.setString(1, invid.trim());
                stat2.setString(2, ITMIDL.trim());
                result2 = stat2.executeQuery();
                while(result2.next()) {
                     ITEMBRKUPLIST.add(new ItemBrkupBean(result2.getString("ITEM_NO")));  
                }
                 }
             
        }
                 
                
                 if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        
        return "brakup";
    }
     
   public String brkupsave() throws SQLException{
     Connection conn = null;
        PreparedStatement stat2 = null;
                ResultSet result2 = null;
                ResultSet rs1 = null;
                PreparedStatement stat4=null,stat6=null;
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
       // usrid="227350";
    if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        
        double itmqty=0.0;
        double tot_itmqty=0.0;
        
        String yr = null;
         String comp = null;
         String invn = null;
         
        try {
            int z=0,x=0;
                stat2 = conn.prepareStatement("select YEAR,COMPANY,INV_NO from ei_endors_mast where EXCS_INV_NO =?");
                stat2.setString(1, invid.trim());
                result2 = stat2.executeQuery();
                if(result2.next()) {
                 yr= result2.getString("YEAR");
                 comp=result2.getString("COMPANY");
                 invn=result2.getString("INV_NO");
                }
                 if (stat2 != null) {
                    stat2.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                                        int srid=0;
                                        stat6 = conn.prepareStatement("select max(SR_NO) SR_NO from EI_WALMART_ITEM_ID_DTLS where INV_NO=? and year=? and company=?");
                                        stat6.setString(1, invn);
                                        stat6.setString(2, yr);
                                        stat6.setString(3, comp);
                                        rs1=stat6.executeQuery();
                                        while(rs1.next()){
                                           srid= rs1.getInt("SR_NO")+1;
                                        }
                                        if(stat6!=null){
                                            stat6.close();
                                        }
                                        if(rs1!=null){
                                            rs1.close();
                                        }
                                
                          //   for (int i = 0; i < ITEM_NO.size(); i++) {
                   System.out.println("sanjeev "+ITEMNOFIX);
                                stat6 = conn.prepareStatement("delete from  EI_WALMART_ITEM_ID_DTLS where year=? and inv_no=? and company=? and ITEM_NO=?");
                                stat6.setString(1, yr);
                                stat6.setString(2, invn);
                                stat6.setString(3, comp);
                                stat6.setString(4,ITEMNOFIX);
                            //    stat6.setString(4,ITEM_NO.get(i).toString());
                                 
                                stat6.executeUpdate();
                                 conn.commit();
                                if(stat6!=null){
                                    stat6.close();
                                   
                              //  }
                               }
                
            System.out.println("g"+TOTITEM_QTY+"k"+QTYID);
                 for (int i = 0; i < ITEM_ID.size(); i++) {
                     srid++;
                    if (ITEM_ID != null && ITEM_ID.get(i).toString().length() > 0) {
                        if(Double.valueOf(TOTITEM_QTY).equals(Double.valueOf(QTYID))){
                                
                                stat4 = conn.prepareStatement("insert into  EI_WALMART_ITEM_ID_DTLS(PACK_RATIO,PACK_QTY,ITEM_NO,ITEM_ID,TDATE,YEAR,COMPANY,INV_NO,SEH_USER,LOCATION,SR_NO) values(?,?,?,?,sysdate,?,?,?,?,?,?) ");
                                stat4.setString(1, ITEM_RATIO.get(i).toString());
                                stat4.setString(2, ITEM_PCKGS.get(i).toString());
                            //    stat4.setString(3, ITEM_NO.get(i).toString());
                              System.out.println("san "+ITEMNOFIX);  
                                stat4.setString(3, ITEMNOFIX);
                                stat4.setString(4, ITEM_ID.get(i).toString().toUpperCase());
                                stat4.setString(5, yr);
                                stat4.setString(6, comp);
                                stat4.setString(7, invn);
                                stat4.setString(8, usrid);
                                stat4.setString(9, LOCATION_CODE);
                                stat4.setInt(10, srid);
                                x = stat4.executeUpdate(); 
                                if (x > 0) {
                                    ++x;
                                    conn.commit();
                                }
                                }
                        else{
                            addActionError("Total Value not Matched");
                            ITEM_RATIO=null;
                            ITEM_PCKGS=null;
                            ITEM_NO=null;
                            ITEM_ID=null;
                            ITEM_QTY=null;
                        }
                        
                    }
                }
                 
                 if(x>0){
                    addActionError("Record Updated");
                            ITEM_RATIO=null;
                            ITEM_PCKGS=null;
                            ITEM_NO=null;
                            ITEM_ID=null;
                            ITEM_QTY=null;
                 }
                 
        }
        catch(Exception e){
          System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
                conn.close();
            }
        }
       
       return "brakupsave";
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
        //LOCATION_CODE = "100";
        //usrid="227350";
        
        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
    
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
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
            System.out.println("guddu value"+flagVAL);
            if (flag4 == flagVAL) {
                stat5 = conn.prepareStatement("select year,COMPANY,INV_NO from  ei_endors_mast where EXCS_INV_NO=?");
                stat5.setString(1, INVOICE_S);
                result5 = stat5.executeQuery();
                if (result5.next()) {
                    yr = result5.getString("year");
                    comp = result5.getString("COMPANY");
                    invn = result5.getString("INV_NO");
                }
                
                stat2 = conn.prepareStatement("select * from  EI_WALMART_INV where EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S);
                result2 = stat2.executeQuery();
                if (result2.next()) {
                    
                    stat4 = conn.prepareStatement("UPDATE  EI_WALMART_INV SET VESSEL_NO=?,VESSEL_DATE=?,FLIGHT_NO=?,ETA_DATE=?,SHIP_CASE=?,"
                            + "MANUF_CODE=?,TARRIF_NO=?,LC_NO=?,REMARK=?,SHIP_LINE=?,PO_TYPE=?,DEPT=?,DUTY=?,DUTY1=?,PORT=?,BH_TYPE=?,DES_PORT=?,RATIO=?,COMMON_NAME=?,"
                            + "SHIPPING_MODE=?,PAYTERM=?,FIBER_CONTENT=?,SEH_USER=?,LOCATION=? WHERE year=? and COMPANY=? and INV_NO=? and EXCS_INV_NO=?");
                    stat4.setString(1, VESELNO.toUpperCase());
                    stat4.setString(2, CRGORCRDDT!=null && CRGORCRDDT.length()>0?myFormat.format(fromUser.parse(CRGORCRDDT.trim())):"");
                    stat4.setString(3, FLGHTNO.trim());
                    stat4.setString(4, ETDDT!=null && ETDDT.length()>0?myFormat.format(fromUser.parse(ETDDT.trim())):"");
                    stat4.setString(5, SHIPCASE.trim());
                    stat4.setString(6, MANUFCODE.trim());
                    stat4.setString(7, TARRIFNO);
                    stat4.setString(8, LCOANO.toUpperCase());
                    stat4.setString(9, LCDESC.toUpperCase());
                    stat4.setString(10, SHIPNGLINE.trim());
                    stat4.setString(11, POTYP.trim());
                    stat4.setString(12, DEPTCODE.trim());
                    stat4.setString(13, DUTY1.trim());
                    stat4.setString(14, DUTY2);
                    stat4.setString(15, FROMPORT.toUpperCase());
                    stat4.setString(16, BA_TYPE);
                    stat4.setString(17, DISCHARGEPORT.toUpperCase());
                    stat4.setString(18, PCKRATIO);
                    stat4.setString(19, TEXTILE);
                    stat4.setString(20, MODE);
                    stat4.setString(21, PAYMNTTRM);
                    stat4.setString(22, FIBRCNTNT.toUpperCase());
                    stat4.setString(23, usrid);
                    stat4.setString(24, LOCATION_CODE);
                    stat4.setString(25, yr);
                    stat4.setString(26, comp);
                    stat4.setString(27, invn);
                    stat4.setString(28, INVOICE_S);
                    x = stat4.executeUpdate();
                    if (x > 0) {
                        ++x;
                        conn.commit();
                    }
                }
                else{
                    try{
                    stat4 = conn.prepareStatement("insert into  EI_WALMART_INV(VESSEL_NO,VESSEL_DATE,FLIGHT_NO,ETA_DATE,SHIP_CASE,MANUF_CODE,TARRIF_NO,LC_NO,REMARK,SHIP_LINE,PO_TYPE,DEPT,DUTY,DUTY1,PORT,BH_TYPE,DES_PORT,RATIO,COMMON_NAME,SHIPPING_MODE,PAYTERM,FIBER_CONTENT,TDATE,year,company,inv_no,EXCS_INV_NO,SEH_USER,LOCATION) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,trunc(sysdate),?,?,?,?,?,?)");
                    stat4.setString(1, VESELNO.toUpperCase());
                    stat4.setString(2, CRGORCRDDT!=null && CRGORCRDDT.length()>0?myFormat.format(fromUser.parse(CRGORCRDDT.trim())):"");
                    stat4.setString(3, FLGHTNO.trim());
                    stat4.setString(4, ETDDT!=null && ETDDT.length()>0?myFormat.format(fromUser.parse(ETDDT.trim())):"");
                    stat4.setString(5, SHIPCASE.trim());
                    stat4.setString(6, MANUFCODE.trim());
                    stat4.setString(7, TARRIFNO);
                    stat4.setString(8, LCOANO.toUpperCase());
                    stat4.setString(9, LCDESC.toUpperCase());
                    stat4.setString(10, SHIPNGLINE.trim());
                    stat4.setString(11, POTYP.trim());
                    stat4.setString(12, DEPTCODE.trim());
                    stat4.setString(13, DUTY1.trim());
                    stat4.setString(14, DUTY2);
                    stat4.setString(15, FROMPORT.toUpperCase());
                    stat4.setString(16, BA_TYPE);
                    stat4.setString(17, DISCHARGEPORT.toUpperCase());
                    stat4.setString(18, PCKRATIO);
                    stat4.setString(19, TEXTILE);
                    stat4.setString(20, MODE);
                    stat4.setString(21, PAYMNTTRM);
                    stat4.setString(22, FIBRCNTNT.toUpperCase());
                    stat4.setString(23, yr);
                    stat4.setString(24, comp);
                    stat4.setString(25, invn);
                    stat4.setString(26, INVOICE_S);
                    stat4.setString(27, usrid);
                    stat4.setString(28, LOCATION_CODE);
                    x = stat4.executeUpdate();
                    if (x > 0) {
                        ++x;
                        conn.commit();
                    }
                    }
                    catch(Exception e){
                       System.out.println(e.toString()); 
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
                if (result2 != null) {
                    result2.close();
                }
                
                

                stat6 = conn.prepareStatement("delete from  EI_WALMART_INV_ITEM_DTLS where INV_NO=? and year=? and company=?");
                stat6.setString(1, invn);
                stat6.setString(2, yr);
                stat6.setString(3, comp);
                stat6.executeUpdate();
                if(stat6!=null){
                    stat6.close();
                }

                int j=0;
                for (int i = 0; i < ITMID_L.size(); i++) {
                    ++j;
                    if (ITMID_L != null && ITMID_L.get(i).toString().length() > 0) {
                        stat = conn.prepareStatement("select * from  ei_endors_mast where EXCS_INV_NO=?");
                        stat.setString(1, INVOICE_S);
                        result = stat.executeQuery();
                        if (result.next()) {

                            stat4 = conn.prepareStatement("insert into  EI_WALMART_INV_ITEM_DTLS(ITEM_NO,ITEM_DESC,PKGS,RATIO,PCS_PER_BOX,ITEM_RATE,QTY,NET_WT,GR_WT,BOX_L,BOX_W,BOX_H,BOX_VOL,TDATE,inv_no,year,company,SR_NO,SEH_USER,LOCATION,DIMENSION) values(?,?,?,?,?,?,?,?,?,?,?,?,?,trunc(sysdate),?,?,?,?,?,?,?)");
                            stat4.setString(1, ITMID_L.get(i).toString());
                            stat4.setString(2, ITMIDDESC_L.get(i).toString().trim());
                            stat4.setString(3, PCKGES_L.get(i).toString().trim());
                            stat4.setString(4, RATIO_L.get(i).toString().trim());
                            stat4.setString(5, PCKRATIO_L.get(i).toString().trim());
                            stat4.setString(6, RATE_L.get(i).toString().trim());
                            stat4.setString(7, QTY_L.get(i).toString().trim());
                            stat4.setString(8, NETWGHT_L.get(i).toString().trim());
                            stat4.setString(9, GROSSWGHT_L.get(i).toString().trim());
                            stat4.setString(10, BOX_L_L.get(i).toString().trim());
                            stat4.setString(11, BOX_W_L.get(i).toString().trim());
                            stat4.setString(12, BOX_H_L.get(i).toString().trim());
                            stat4.setString(13, BOX_VOL_L.get(i).toString().trim());
                            stat4.setString(14, invn);
                            stat4.setString(15, yr);
                            stat4.setString(16, comp);
                            stat4.setString(17, SRN1.get(i).toString().trim());
                            stat4.setString(18, usrid);
                            stat4.setString(19, LOCATION_CODE);
                            stat4.setString(20, BOX_L_L.get(i).toString().trim()+"X"+BOX_W_L.get(i).toString().trim()+"X"+BOX_H_L.get(i).toString().trim());
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
                
                
                
                stat6 = conn.prepareStatement("delete from  EI_WALMART_MATERIAL where  year=? and company=? AND INV_NO=? ");
                stat6.setString(1, yr);
                stat6.setString(2, comp);
                stat6.setString(3, invn);
               
                stat6.executeUpdate();
                if(stat6!=null){
                    stat6.close();
                }
                
                 for (int i = 0; i < MTRIALBRKUP.size(); i++) {
                    if (MTRIALBRKUP != null && MTRIALBRKUP.get(i).toString().length() > 0) {
                            stat4 = conn.prepareStatement("insert into  EI_WALMART_MATERIAL(year,company,inv_no,LINE1,TDATE,SEH_USER,IDNO) values(?,?,?,?,sysdate,?,?)");
                             stat4.setString(1, yr);
                             stat4.setString(2, comp);
                             stat4.setString(3, invn);
                             stat4.setString(4, MTRIALBRKUP.get(i).toString());
                             stat4.setString(5, usrid);
                             stat4.setInt(6,i);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                                conn.commit();
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

                    ITMID_L = null;
                    ITMIDDESC_L = null;
                    PCKGES_L = null;
                    RATIO_L = null;
                    PCKRATIO_L = null;
                    RATE_L = null;
                    QTY_L = null;
                    NETWGHT_L = null;
                    GROSSWGHT_L = null;
                    BOX_L_L = null;
                    BOX_W_L = null;
                    BOX_H_L = null;
                    BOX_VOL_L = null;
                    TOT_NETWGHT_L = null;
                    TOT_GROSSWGHT_L = null;
                    //INVOICE_S = null;

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
                 
                stat5 = conn.prepareStatement("select year,COMPANY,INV_NO from  ei_endors_mast where EXCS_INV_NO=?");
                stat5.setString(1, INVOICE_S);
                result5 = stat5.executeQuery();
                if (result5.next()) {
                    yr = result5.getString("year");
                    comp = result5.getString("COMPANY");
                    invn = result5.getString("INV_NO");
                }

                stat2 = conn.prepareStatement("select * from  EI_WALMART_INV where EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S);
                result2 = stat2.executeQuery();
                if (result2.next()) {
                    
                    stat4 = conn.prepareStatement("UPDATE  EI_WALMART_INV SET VESSEL_NO=?,VESSEL_DATE=?,FLIGHT_NO=?,ETA_DATE=?,SHIP_CASE=?,"
                            + "MANUF_CODE=?,TARRIF_NO=?,LC_NO=?,REMARK=?,SHIP_LINE=?,PO_TYPE=?,DEPT=?,DUTY=?,DUTY1=?,PORT=?,BH_TYPE=?,DES_PORT=?,RATIO=?,COMMON_NAME=?,"
                            + "SHIPPING_MODE=?,PAYTERM=?,FIBER_CONTENT=?,SEH_USER=?,LOCATION=? WHERE year=? and COMPANY=? and INV_NO=? and EXCS_INV_NO=?");
                    stat4.setString(1, VESELNO.toUpperCase());
                    stat4.setString(2, CRGORCRDDT!=null && CRGORCRDDT.length()>0?myFormat.format(fromUser.parse(CRGORCRDDT.trim())):"");
                    stat4.setString(3, FLGHTNO.trim());
                    stat4.setString(4, ETDDT!=null && ETDDT.length()>0?myFormat.format(fromUser.parse(ETDDT.trim())):"");
                    stat4.setString(5, SHIPCASE.trim());
                    stat4.setString(6, MANUFCODE.trim());
                    stat4.setString(7, TARRIFNO);
                    stat4.setString(8, LCOANO.toUpperCase());
                    stat4.setString(9, LCDESC.toUpperCase());
                    stat4.setString(10, SHIPNGLINE.trim());
                    stat4.setString(11, POTYP.trim());
                    stat4.setString(12, DEPTCODE.trim());
                    stat4.setString(13, DUTY1.trim());
                    stat4.setString(14, DUTY2);
                    stat4.setString(15, FROMPORT.toUpperCase());
                    stat4.setString(16, BA_TYPE);
                    stat4.setString(17, DISCHARGEPORT.toUpperCase());
                    stat4.setString(18, PCKRATIO);
                    stat4.setString(19, TEXTILE);
                    stat4.setString(20, MODE);
                    stat4.setString(21, PAYMNTTRM);
                    stat4.setString(22, FIBRCNTNT.toUpperCase());
                    stat4.setString(23, usrid);
                    stat4.setString(24, LOCATION_CODE);
                    stat4.setString(25, yr);
                    stat4.setString(26, comp);
                    stat4.setString(27, invn);
                    stat4.setString(28, INVOICE_S);
                    x = stat4.executeUpdate();
                    if (x > 0) {
                        ++x;
                        conn.commit();
                    }
                } else {
                    
                    stat4 = conn.prepareStatement("insert into  EI_WALMART_INV(VESSEL_NO,VESSEL_DATE,FLIGHT_NO,ETA_DATE,SHIP_CASE,MANUF_CODE,TARRIF_NO,LC_NO,REMARK,SHIP_LINE,PO_TYPE,DEPT,DUTY,DUTY1,PORT,BH_TYPE,DES_PORT,RATIO,COMMON_NAME,SHIPPING_MODE,PAYTERM,FIBER_CONTENT,TDATE,year,company,inv_no,EXCS_INV_NO,SEH_USER,LOCATION) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,trunc(sysdate),?,?,?,?,?,?)");
                    stat4.setString(1, VESELNO.toUpperCase());
                    stat4.setString(2, CRGORCRDDT!=null && CRGORCRDDT.length()>0?myFormat.format(fromUser.parse(CRGORCRDDT.trim())):"");
                    stat4.setString(3, FLGHTNO.trim());
                    stat4.setString(4, ETDDT!=null && ETDDT.length()>0?myFormat.format(fromUser.parse(ETDDT.trim())):"");
                    stat4.setString(5, SHIPCASE.trim());
                    stat4.setString(6, MANUFCODE.trim());
                    stat4.setString(7, TARRIFNO);
                    stat4.setString(8, LCOANO.toUpperCase());
                    stat4.setString(9, LCDESC.toUpperCase());
                    stat4.setString(10, SHIPNGLINE.trim());
                    stat4.setString(11, POTYP.trim());
                    stat4.setString(12, DEPTCODE.trim());
                    stat4.setString(13, DUTY1.trim());
                    stat4.setString(14, DUTY2);
                    stat4.setString(15, FROMPORT.toUpperCase());
                    stat4.setString(16, BA_TYPE);
                    stat4.setString(17, DISCHARGEPORT.toUpperCase());
                    stat4.setString(18, PCKRATIO);
                    stat4.setString(19, TEXTILE);
                    stat4.setString(20, MODE);
                    stat4.setString(21, PAYMNTTRM);
                    stat4.setString(22, FIBRCNTNT.toUpperCase());
                    stat4.setString(23, yr);
                    stat4.setString(24, comp);
                    stat4.setString(25, invn);
                    stat4.setString(26, INVOICE_S);
                    stat4.setString(27, usrid);
                    stat4.setString(28, LOCATION_CODE);
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


                stat6 = conn.prepareStatement("delete from  EI_WALMART_INV_ITEM_DTLS where  year=? and company=? and INV_NO=? ");
               
                stat6.setString(1, yr);
                stat6.setString(2, comp);
                 stat6.setString(3, invn);
                stat6.executeUpdate();


                int j=0;
                for (int i = 0; i < ITMID_L.size(); i++) {
                    ++j;
                    if (ITMID_L != null && ITMID_L.get(i).toString().length() > 0) {
                        stat = conn.prepareStatement("select * from  ei_endors_mast where EXCS_INV_NO=?");
                        stat.setString(1, INVOICE_S);
                        result = stat.executeQuery();
                        if (result.next()) {

                            stat4 = conn.prepareStatement("insert into  EI_WALMART_INV_ITEM_DTLS(ITEM_NO,ITEM_DESC,PKGS,RATIO,PCS_PER_BOX,ITEM_RATE,QTY,NET_WT,GR_WT,BOX_L,BOX_W,BOX_H,BOX_VOL,TDATE,inv_no,year,company,SR_NO,SEH_USER,LOCATION,DIMENSION) values(?,?,?,?,?,?,?,?,?,?,?,?,?,trunc(sysdate),?,?,?,?,?,?,?)");
                            stat4.setString(1, ITMID_L.get(i).toString());
                            stat4.setString(2, ITMIDDESC_L.get(i).toString().trim());
                            stat4.setString(3, PCKGES_L.get(i).toString().trim());
                            stat4.setString(4, RATIO_L.get(i).toString().trim());
                            stat4.setString(5, PCKRATIO_L.get(i).toString().trim());
                            stat4.setString(6, RATE_L.get(i).toString().trim());
                            stat4.setString(7, QTY_L.get(i).toString().trim());
                            stat4.setString(8, NETWGHT_L.get(i).toString().trim());
                            stat4.setString(9, GROSSWGHT_L.get(i).toString().trim());
                            stat4.setString(10, BOX_L_L.get(i).toString().trim());
                            stat4.setString(11, BOX_W_L.get(i).toString().trim());
                            stat4.setString(12, BOX_H_L.get(i).toString().trim());
                            stat4.setString(13, BOX_VOL_L.get(i).toString().trim());
                            stat4.setString(14, invn);
                            stat4.setString(15, yr);
                            stat4.setString(16, comp);
                            stat4.setString(17, SRN1.get(i).toString().trim());
                            stat4.setString(18, usrid);
                            stat4.setString(19, LOCATION_CODE);
                            stat4.setString(20, BOX_L_L.get(i).toString().trim()+"X"+BOX_W_L.get(i).toString().trim()+"X"+BOX_H_L.get(i).toString().trim());
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
                
                
                stat6 = conn.prepareStatement("delete from  EI_WALMART_MATERIAL where year=? and company=? and INV_NO=? ");
              
                stat6.setString(1, yr);
                stat6.setString(2, comp);
                  stat6.setString(3, invn);
                stat6.executeUpdate();
                if(stat6!=null){
                    stat6.close();
                }
                
                 for (int i = 0; i < MTRIALBRKUP.size(); i++) {
                    if (MTRIALBRKUP != null && MTRIALBRKUP.get(i).toString().length() > 0) {
                            stat4 = conn.prepareStatement("insert into  EI_WALMART_MATERIAL(LINE1,inv_no,year,company,TDATE,SEH_USER,idno) values(?,?,?,?,trunc(sysdate),?,?)");
                            stat4.setString(1, MTRIALBRKUP.get(i).toString());
                            stat4.setString(2, invn);
                            stat4.setString(3, yr);
                            stat4.setString(4, comp);
                            stat4.setString(5, usrid);
                            stat4.setInt(6,i);
                            z = stat4.executeUpdate();
                            if (z > 0) {
                                ++z;
                                conn.commit();
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

                    ITMID_L = null;
                    ITMIDDESC_L = null;
                    PCKGES_L = null;
                    RATIO_L = null;
                    PCKRATIO_L = null;
                    RATE_L = null;
                    QTY_L = null;
                    NETWGHT_L = null;
                    GROSSWGHT_L = null;
                    BOX_L_L = null;
                    BOX_W_L = null;
                    BOX_H_L = null;
                    BOX_VOL_L = null;
                    TOT_NETWGHT_L = null;
                    TOT_GROSSWGHT_L = null;
                   // INVOICE_S = null;

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

       
         FLAG1="Yes";
         execute();

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
             connBI=new ConnectionMovexBi().getConnection();
             conndb2=new connectiondb2().getConnection();
             List WalmartBeanlist=new ArrayList();
                        
             
               //Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                    stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,"
                            + "to_char(a.inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(a.notify) notify,"
                            + "b.TARRIF_NO,a.ac_holder,b.VESSEL_NO,a.hs_code,TRIM(b.manuf_code) manuf_code,a.cost_centre,a.mode_of_ship,a.inv_qty,"
                            + "rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,a.LOADING_port,trim(a.LOADING) CLR_port,"
                            + "a.pre_carriage,a.PLACE,b.DES_PORT,b.COMMON_NAME,a.payment_term,a.ship_term,a.pay_term,b.REMARK,b.FLIGHT_NO,"
                            + "a.DESTI_CNTRY,a.CNTRY_ORIGIN,a.DISCHARGE,a.Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,"
                            + "b.LC_NO,b.PAYTERM,decode(b.payterm,'LC','LC AT SIGHT','OPEN ACCOUNT 90 DAYS') paytermdesc,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,"
                            + "a.manuf_state,a.tax_type,a.tax_percent,a.tax_code,b.DUTY,b.DUTY1,a.transport_cost,facility,CTNS,"
                            + "to_char(b.ETA_DATE,'dd/mm/yy') ETA_DATE,to_char(b.VESSEL_DATE,'dd/mm/yy') VESSEL_DATE,b.RATIO,b.PORT,b.SHIP_CASE,b.DEPT"
                            + " from  ei_endors_mast a, EI_WALMART_INV b  where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO "
                            + "and b.excs_inv_no=?");
                    stat1.setString(1, INVOICE_S);
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next())
                    {  
                        WalmartBean bean = new WalmartBean();
       
                        bean.setTINVQTY(result1.getString("paytermdesc"));
                      
                        bean.setLocation(result1.getString("location"));
                        bean.setExcs_inv_no(result1.getString("excs_inv_no"));
                        bean.setPlan_no(result1.getString("plan_no"));
                        bean.setInv_date(result1.getString("inv_date"));
                        bean.setExp_type(result1.getString("exp_type"));
                        bean.setHs_code(result1.getString("hs_code"));
                        bean.setLcno(result1.getString("LC_NO")); 
                        bean.setINVDATE(result1.getString("PAYTERM"));
                        bean.setCost_centre(result1.getString("cost_centre"));
                        bean.setMode_of_ship(result1.getString("mode_of_ship")); 
                        bean.setBuyer(result1.getString("buyer"));
                        bean.setBuyer_addr(result1.getString("buyer_addr").trim());
                        bean.setCons_addr(result1.getString("cons_addr").trim());
                        bean.setShip_term(result1.getString("ship_term"));
                        
                        bean.setAgent(result1.getString("TARRIF_NO"));
                        bean.setUpcharge_per(result1.getDouble("COMMON_NAME"));
                        bean.setCTNMNT(result1.getString("DES_PORT"));
                        bean.setFwd_code(result1.getString("VESSEL_NO"));
                        bean.setFwd_custom(result1.getString("FLIGHT_NO")); 
                        bean.setTAX_CAL_PER(result1.getDouble("DUTY"));
                        bean.setTAX_AMT(result1.getDouble("DUTY1"));
                        bean.setCATE(result1.getString("ETA_DATE"));
                        bean.setFACILITY(result1.getString("VESSEL_DATE"));
                        
                        bean.setMANUF_CODE(result1.getString("manuf_code"));
                        bean.setNotify(result1.getString("notify"));
                        bean.setPay_term(result1.getString("pay_term"));
                        bean.setTransport_cost(result1.getDouble("transport_cost"));
                        bean.setPayment_term(result1.getString("payment_term"));
                        bean.setLOADING_PORT(result1.getString("LOADING_PORT"));
                        bean.setComm_per(result1.getString("PORT"));
                        bean.setCLR_PORT(result1.getString("CLR_PORT"));
                        bean.setDESTI_CNTRY(result1.getString("DESTI_CNTRY"));
                        bean.setDIS_CNTRY(result1.getString("DIS_CNTRY"));
                        bean.setCNTRY_ORIGIN(result1.getString("CNTRY_ORIGIN"));
                        bean.setDISCHARGE(result1.getString("DISCHARGE"));
                        bean.setDESTI_CODE(result1.getString("DESTI_CODE"));
                        
                        bean.setCRGODT(result1.getString("REMARK"));
                        bean.setTFOB(result1.getString("SHIP_CASE"));
                        bean.setCREATIONNO(result1.getString("dept"));
                        bean.setCTNS(result1.getString("CTNS"));
                        
                        bean.setCRNCY_CODE(result1.getString("crncy_code"));
                        bean.setMANUF_STATE(result1.getString("manuf_state"));
                        bean.setTAX_TYPE(result1.getString("tax_type"));
                        bean.setTAX_PERCENT(result1.getDouble("tax_percent"));
                        
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
                        
                        bean.setSelf_tp(result1.getString("RATIO"));
                        
                        
                        
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
                          
//                          bn=new PreInvoiceDao().getUnitByName(result1.getString("manuf_code"));
//                          bean.setMANUF_DESC(bn.getUNIT_DESC());
//                          bean.setMANUF_ADDRESS(bn.getUNIT_ADDRESS());
                          
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
                       
                        
                           PreparedStatement stat3 = conn.prepareStatement("select BANK,BANK_ADDR from ei_lc_lic_mast where REF_NO=?");
                           stat3.setString(1,result1.getString("LC_NO"));
                           ResultSet rs3=stat3.executeQuery();
                           if(rs3.next()){
                             
                               PreparedStatement stat4 = conndb2.prepareStatement("SELECT BRBKBM from m3fdbprd.CBANBR where brcono=111 and brbkno=? and brbbrn=?");
                               stat4.setString(1,rs3.getString("BANK"));
                               stat4.setString(2,rs3.getString("BANK_ADDR"));
                               ResultSet rs4=stat4.executeQuery();
                               if(rs4.next()){
                                 bean.setTINR(rs4.getString("BRBKBM")); 
                               }
                               
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                        
                         
                           stat3 = conn.prepareStatement("select VEND_NAME,(VEND_ADDR||','||CITY||' '||STATE) Address from pr_vend_mast where vend_code=?");
                           stat3.setString(1,result1.getString("manuf_code"));
                           rs3=stat3.executeQuery();
                           if(rs3.next()){
                             bean.setMANUF_DESC(rs3.getString("VEND_NAME"));
                             bean.setMANUF_ADDRESS(rs3.getString("Address"));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                             
                           List linelist=new ArrayList();
                           stat3 = conn.prepareStatement("select line1  from EI_WALMART_MATERIAL where  year=? and company=? and inv_no=? order by idno");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                             linelist.add(new WalMartwalLstBean(rs3.getString("line1")));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                          bean.setHNGRLIST(linelist);
                            
                            
                        String mbpo="";
                        stat = conn.prepareStatement("select distinct nvl(GR_WT,0) GR_WT from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo=mbpo+result.getString("GR_WT")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo!=null){
                          mbpo=mbpo.substring(0, mbpo.length()-1);}
                         
                          bean.setMSG1(mbpo);
                          
                          String mbpo1="";
                        stat = conn.prepareStatement("select distinct nvl(NET_WT,0) NET_WT from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo1=mbpo1+result.getString("NET_WT")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo1!=null){
                          mbpo1=mbpo1.substring(0, mbpo1.length()-1);}
                         
                          bean.setMSG2(mbpo1);
                          
                           String mbpo2="";
                        stat = conn.prepareStatement("select distinct DIMENSION from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo2=mbpo2+result.getString("DIMENSION")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo2!=null){
                          mbpo2=mbpo2.substring(0, mbpo2.length()-1);}
                         
                          bean.setCLR_PORT(mbpo2);
                          
                          
                         
                            stat3 = conn.prepareStatement("select distinct CATEGORY,PRE_PRINT_NO,TOKEN_NO from ei_endors_dtls where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           if(rs3.next()){
                             bean.setNOTIFY_NAME(rs3.getString("CATEGORY"));
                             bean.setFIRSTQLITY(rs3.getString("PRE_PRINT_NO"));
                             bean.setERR_MSG(rs3.getString("TOKEN_NO"));
                           } 
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
                        List InvList = new ArrayList();
                        List InvList1 = new ArrayList();
                        List walInvList = new ArrayList();
                        List walInvList1 = new ArrayList();
                        List walInvList2 = new ArrayList();
                        
                        String po_no=""; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double inrconv=0.0;
                        double cartn=0.0;double cartn_tot=0.0; double netfc=0.0;  
                        BigDecimal g1 = new BigDecimal("0.00");double ttlqnty=0.0;double tnetpcs=0.0;
                        String pono="";String styleno="";String itemno1="";String vesletddt="";
                        String desc="";String pcs="";double rate=0.0;double totamt=0.0;
                       String itmno="";String itmdesc="";String outrpck="";double totcse=0.0;double totunit=0.0;double netpercse=0.0;
                       double nettot=0.0;double grospercse=0.0;double grostot=0.0;String dl="";String dw="";String dh="";
                       double totvol=0.0;String unitprice="";double amount=0.0;String amountval="";String rate1="";
                       String dimensn="";String bitmif="";String boxval="";double cbm=0.0;
                       
                       
                       
                           stat3 = conn.prepareStatement("select ITEM_NO,ITEM_DESC,nvl(QTY,0) QTY,nvl(PKGS,0) PKGS,nvl(PCS_PER_BOX,0) PCS_PER_BOX,nvl(NET_WT,0) NET_WT,nvl(PKGS*NET_WT,0) tot_NET_WT,nvl(GR_WT,0) GR_WT,"
                                   + "nvl(PKGS*GR_WT,0) tot_GR_WT ,nvl(BOX_L,0) BOX_L,nvl(BOX_W,0) BOX_W,nvl(BOX_H,0) BOX_H,nvl(BOX_VOL,0) BOX_VOL,nvl(ITEM_RATE,0) ITEM_RATE,nvl(QTY*ITEM_RATE,0) amt,DIMENSION,RATIO from  EI_WALMART_INV_ITEM_DTLS where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                            itmno= rs3.getString("ITEM_NO");
                            itmdesc= rs3.getString("ITEM_DESC");
                            
                           
                        
                            amountval=roundTwoDecimals(rs3.getDouble("amt"))+"";
                            rate1=roundFourDecimals(rs3.getDouble("ITEM_RATE"))+"";
                            boxval=roundFourDecimals(rs3.getDouble("BOX_VOL"))+"";
                            cbm=cbm+rs3.getDouble("BOX_VOL");
                                    
                            outrpck=outrpck+rs3.getString("QTY");
                            ttlqnty=Double.valueOf(rs3.getString("PCS_PER_BOX"))*Double.valueOf(rs3.getString("PKGS"));
                            totcse=totcse+Double.valueOf(rs3.getString("PKGS"));
                            totunit=totunit+Double.valueOf(rs3.getString("QTY"));
                            netpercse= netpercse+Double.valueOf(rs3.getString("tot_NET_WT"));
                            nettot= nettot+Double.valueOf(rs3.getString("NET_WT"));
                            grospercse= grospercse+Double.valueOf(rs3.getString("tot_GR_WT"));
                            grostot= grostot+Double.valueOf(rs3.getString("GR_WT"));
                            dl= dl+rs3.getString("BOX_L");
                            dw= dw+rs3.getString("BOX_W");
                            dh= dh+rs3.getString("BOX_H");
                            totvol= totvol+Double.valueOf(rs3.getString("BOX_VOL"));
                            unitprice= unitprice+rs3.getString("ITEM_RATE");
                            amount= amount+Double.valueOf(rs3.getString("amt"));
                           
                            
                            InvLineList.add(new WalMartSubLstBean(rs3.getString("ITEM_NO"),rs3.getString("ITEM_DESC"),rs3.getString("QTY"),
                                    rs3.getString("PKGS"),rs3.getString("PCS_PER_BOX"),rs3.getString("NET_WT"),rs3.getString("tot_NET_WT"),rs3.getString("GR_WT"),
                                    rs3.getString("tot_GR_WT"),rs3.getString("BOX_L"),rs3.getString("BOX_W"),rs3.getString("BOX_H"),boxval,rate1,amountval));
                           
                           
//                           walInvList.add(new WalMartwalLstBean(rs3.getString("ITEM_NO"),rs3.getString("PKGS"),rs3.getString("PCS_PER_BOX")+"X"+rs3.getString("PKGS"),
//                                   rs3.getString("tot_GR_WT"),rs3.getString("tot_NET_WT"),rs3.getString("PCS_PER_BOX"),rs3.getString("RATIO")));
                           walInvList1.add(new WalMartwalLstBean(rs3.getString("ITEM_NO")));
                           walInvList2.add(new WalMartwalLstBean(rs3.getString("PKGS")));
                          
                           
                           } 
                           if(stat3!=null){
                             stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                            
                          bean.setINV_FC(roundTwoDecimals(netpercse));
                          bean.setCOMM_AMT(roundTwoDecimals(grospercse));
                          
                        bean.setINV_RATE(roundFourDecimals(cbm));
                       
                        //bean.setITEMLINELIST(walInvList);
                        bean.setSTYLIST(walInvList1);
                        bean.setDBKLIST(walInvList2);
                        
                        netfc=roundTwoDecimals(amount);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTOTCASE(roundTwoDecimals(totcse));
                        bean.setTOTALUNIT(roundTwoDecimals(totunit));
                        bean.setNETTOTAL(roundTwoDecimals(nettot));
                        bean.setGORSTOTAL(roundTwoDecimals(grostot));
                        bean.setTOTALVAL(roundFourDecimals(totvol));
                        bean.setTOTAMOUNT(roundTwoDecimals(amount));
                        
                        
                        
                       
                        
                      
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
                       
                       
                      
                       
                       //DON'T DELETE BELOW COMMENT
                       
//                         stat=conn.prepareStatement("select sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  seplweb.cidadr_view115 where  sacono=111 and sasuno=? and SAADTE='1' and saadid='001'"); 
//                          stat.setString(1,result1.getString("notify"));
//                          result=stat.executeQuery();
//                          if (result.next() == true) 
//                        { 
//                          bean.setNOTIFY_NAME(result.getString("sasunm"));
//                          bean.setNOTIFY_ADDRESS(result.getString("noticity"));
//                        }
                       
                        
                        WalmartBeanlist.add(bean);
        
                    }
                    

                if (INVOICE_S!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/BuyerInv/");
                    Map param1 = new HashMap();

                   
                    InputStream input;
                    param1.put("SUBREPORT_DIR", path);
                    param1.put("connsepl", conn);
                  
                    input =new FileInputStream(new File(path+"WalMart_InvoicePackingList.jrxml"));
                   
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(WalmartBeanlist));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=WalMart_InvoicePackingList.pdf"); //attachment- use open new window and inline- use open in same window
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "WalMart_InvoicePackingList.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=WalMart_InvoicePackingList.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("Walmart_invAction" + e.toString());
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
         
        
        execute();
        
        return "prnt";
    }
    public String pckngprints() throws FileNotFoundException, JRException, IOException, SQLException {

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
             connBI=new ConnectionMovexBi().getConnection();
             conndb2=new connectiondb2().getConnection();
             List WalmartBeanlist=new ArrayList();
                        
             
               //Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                    stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,"
                            + "to_char(a.inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(a.notify) notify,"
                            + "b.TARRIF_NO,a.ac_holder,b.VESSEL_NO,a.hs_code,TRIM(b.manuf_code) manuf_code,a.cost_centre,a.mode_of_ship,a.inv_qty,"
                            + "rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,a.LOADING_port,trim(a.LOADING) CLR_port,"
                            + "a.pre_carriage,a.PLACE,b.DES_PORT,b.COMMON_NAME,a.payment_term,a.ship_term,a.pay_term,b.REMARK,b.FLIGHT_NO,"
                            + "a.DESTI_CNTRY,a.CNTRY_ORIGIN,a.DISCHARGE,a.Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,"
                            + "b.LC_NO,b.PAYTERM,decode(b.payterm,'LC','LC AT SIGHT','OPEN ACCOUNT 90 DAYS') paytermdesc,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,"
                            + "a.manuf_state,a.tax_type,a.tax_percent,a.tax_code,b.DUTY,b.DUTY1,a.transport_cost,facility,CTNS,"
                            + "to_char(b.ETA_DATE,'dd/mm/yy') ETA_DATE,to_char(b.VESSEL_DATE,'dd/mm/yy') VESSEL_DATE,b.RATIO,b.PORT,b.SHIP_CASE,b.DEPT"
                            + " from  ei_endors_mast a, EI_WALMART_INV b  where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO "
                            + "and b.excs_inv_no=?");
                    stat1.setString(1, INVOICE_S);
                    result1 = stat1.executeQuery(); 
                    String invq="";
                    if (result1.next())
                    {  
                        WalmartBean bean = new WalmartBean();
       
                        bean.setTINVQTY(result1.getString("paytermdesc"));
                        bean.setTMISCINR(result1.getString("ac_holder"));
                        bean.setLocation(result1.getString("location"));
                        bean.setExcs_inv_no(result1.getString("excs_inv_no"));
                        bean.setPlan_no(result1.getString("plan_no"));
                        bean.setInv_date(result1.getString("inv_date"));
                        bean.setExp_type(result1.getString("exp_type"));
                        bean.setHs_code(result1.getString("hs_code"));
                        bean.setLcno(result1.getString("LC_NO")); 
                        bean.setINVDATE(result1.getString("PAYTERM"));
                        bean.setCost_centre(result1.getString("cost_centre"));
                        bean.setMode_of_ship(result1.getString("mode_of_ship")); 
                        bean.setBuyer(result1.getString("buyer"));
                        bean.setBuyer_addr(result1.getString("buyer_addr").trim());
                        bean.setCons_addr(result1.getString("cons_addr").trim());
                        bean.setShip_term(result1.getString("ship_term"));
                        bean.setPre_carriage(result1.getString("pre_carriage"));
                        bean.setAgent(result1.getString("TARRIF_NO"));
                        bean.setUpcharge_per(result1.getDouble("COMMON_NAME"));
                        bean.setCTNMNT(result1.getString("DES_PORT"));
                        bean.setFwd_code(result1.getString("VESSEL_NO"));
                        bean.setFwd_custom(result1.getString("FLIGHT_NO")); 
                        bean.setTAX_CAL_PER(result1.getDouble("DUTY"));
                        bean.setCATE(result1.getString("ETA_DATE"));
                        bean.setFACILITY(result1.getString("VESSEL_DATE"));
                        
                        bean.setMANUF_CODE(result1.getString("manuf_code"));
                        bean.setNotify(result1.getString("notify"));
                        bean.setPay_term(result1.getString("pay_term"));
                        bean.setTransport_cost(result1.getDouble("transport_cost"));
                        bean.setPayment_term(result1.getString("payment_term"));
                        bean.setLOADING_PORT(result1.getString("LOADING_PORT"));
                        bean.setComm_per(result1.getString("PORT"));
                        bean.setCLR_PORT(result1.getString("CLR_PORT"));
                        bean.setDESTI_CNTRY(result1.getString("DESTI_CNTRY"));
                        bean.setDIS_CNTRY(result1.getString("DIS_CNTRY"));
                        bean.setCNTRY_ORIGIN(result1.getString("CNTRY_ORIGIN"));
                        bean.setDISCHARGE(result1.getString("DISCHARGE"));
                        bean.setDESTI_CODE(result1.getString("DESTI_CODE"));
                        
                        bean.setCRGODT(result1.getString("REMARK"));
                        bean.setTFOB(result1.getString("SHIP_CASE"));
                        bean.setCREATIONNO(result1.getString("dept"));
                        
                        
                            String ctnstot="";
                           PreparedStatement stat3 = conn.prepareStatement("select sum(PKGS) pkgs  from EI_WALMART_INV_ITEM_DTLS where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           ResultSet rs3=stat3.executeQuery();
                          if (rs3.next()){
                            ctnstot= rs3.getString("PKGS"); 
                            
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                        bean.setCTNS(ctnstot);
                        
                        
                        bean.setCRNCY_CODE(result1.getString("crncy_code"));
                        bean.setMANUF_STATE(result1.getString("manuf_state"));
                        bean.setTAX_TYPE(result1.getString("tax_type"));
                        bean.setTAX_PERCENT(result1.getDouble("tax_percent"));
                        
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
                        
                        bean.setSelf_tp(result1.getString("RATIO"));
                        
                        
                        
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
                          
//                          bn=new PreInvoiceDao().getUnitByName(result1.getString("manuf_code"));
//                          bean.setMANUF_DESC(bn.getUNIT_DESC());
//                          bean.setMANUF_ADDRESS(bn.getUNIT_ADDRESS());
                          
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
                       
                        
                           stat3 = conn.prepareStatement("select BANK,BANK_ADDR from ei_lc_lic_mast where REF_NO=?");
                           stat3.setString(1,result1.getString("LC_NO"));
                           rs3=stat3.executeQuery();
                           if(rs3.next()){
                             
                               PreparedStatement stat4 = conndb2.prepareStatement("SELECT BRBKBM from m3fdbprd.CBANBR where brcono=111 and brbkno=? and brbbrn=?");
                               stat4.setString(1,rs3.getString("BANK"));
                               stat4.setString(2,rs3.getString("BANK_ADDR"));
                               ResultSet rs4=stat4.executeQuery();
                               if(rs4.next()){
                                 bean.setTINR(rs4.getString("BRBKBM")); 
                               }
                               
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                        
                         
                           stat3 = conn.prepareStatement("select VEND_NAME,(VEND_ADDR||','||CITY||' '||STATE) Address from pr_vend_mast where vend_code=?");
                           stat3.setString(1,result1.getString("manuf_code"));
                           rs3=stat3.executeQuery();
                           if(rs3.next()){
                             bean.setMANUF_DESC(rs3.getString("VEND_NAME"));
                             bean.setMANUF_ADDRESS(rs3.getString("Address"));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                           List linelist=new ArrayList();
                           stat3 = conn.prepareStatement("select line1  from EI_WALMART_MATERIAL where  year=? and company=? and inv_no=? order by idno");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                             linelist.add(new WalMartwalLstBean(rs3.getString("line1")));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                          bean.setHNGRLIST(linelist);
                            
                            
                        String mbpo="";
                        stat = conn.prepareStatement("select distinct nvl(GR_WT,0) GR_WT from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo=mbpo+result.getString("GR_WT")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo!=null){
                          mbpo=mbpo.substring(0, mbpo.length()-1);}
                         
                          bean.setMSG1(mbpo);
                          
                          String mbpo1="";
                        stat = conn.prepareStatement("select distinct nvl(NET_WT,0) NET_WT from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo1=mbpo1+result.getString("NET_WT")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo1!=null){
                          mbpo1=mbpo1.substring(0, mbpo1.length()-1);}
                         
                          bean.setMSG2(mbpo1);
                          
                           String mbpo2="";
                        stat = conn.prepareStatement("select distinct DIMENSION from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo2=mbpo2+result.getString("DIMENSION")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo2!=null){
                          mbpo2=mbpo2.substring(0, mbpo2.length()-1);}
                         
                          bean.setCLR_PORT(mbpo2);
                          
                          
                         
                            stat3 = conn.prepareStatement("select distinct CATEGORY,PRE_PRINT_NO,TOKEN_NO from ei_endors_dtls where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           if(rs3.next()){
                             bean.setNOTIFY_NAME(rs3.getString("CATEGORY"));
                             bean.setFIRSTQLITY(rs3.getString("PRE_PRINT_NO"));
                             bean.setERR_MSG(rs3.getString("TOKEN_NO"));
                           } 
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
                        List InvList = new ArrayList();
                        List walInvList = new ArrayList();
                        List walInvList1 = new ArrayList();
                        List walInvList2 = new ArrayList();
                        
                        String po_no=""; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double inrconv=0.0;
                        double cartn=0.0;double cartn_tot=0.0; double netfc=0.0;  
                        BigDecimal g1 = new BigDecimal("0.00");double excise_d=0.0;double tnetpcs=0.0;
                        String pono="";String styleno="";String htsno="";String vesletddt="";
                        String desc="";String pcs="";double rate=0.0;double totamt=0.0;
                       String itmno="";String itmdesc="";double outrpck=0.0;double totcse=0.0;double totunit=0.0;double netpercse=0.0;
                       double nettot=0.0;double grospercse=0.0;double grostot=0.0;String dl="";String dw="";String dh="";
                       double totvol=0.0;String unitprice="";double amount=0.0;String amountval="";String rate1="";
                       String dimensn="";String bitmif="";String boxval="";double cbm=0.0;double ttlqnty=0.0;
                       
                       
                       
                          List InvList1 = new ArrayList();
                       
                           stat3 = conn.prepareStatement("select distinct a.ITEM_NO from EI_WALMART_ITEM_ID_DTLS a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and b.EXCS_INV_NO =? order by a.ITEM_NO");
                           stat3.setString(1,INVOICE_S);
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                               PreparedStatement stat4 = conn.prepareStatement("select a.ITEM_NO,a.ITEM_ID,a.PACK_QTY,a.PACK_RATIO,nvl(a.PACK_QTY*a.PACK_RATIO,0) total from EI_WALMART_ITEM_ID_DTLS a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and b.EXCS_INV_NO =? and a.ITEM_NO=? order by a.ITEM_NO");
                               stat4.setString(1,INVOICE_S);
                               stat4.setString(2,rs3.getString("ITEM_NO"));
                               ResultSet rs4=stat4.executeQuery();
                                while(rs4.next()){                                                                                
                                 InvList1.add(new WalmartItmBean(rs4.getString("ITEM_NO"),rs4.getString("ITEM_ID"),rs4.getString("PACK_QTY")+"X"+rs4.getString("PACK_RATIO"),rs4.getDouble("total")));
                                } 
                           }
                                if(stat3!=null){
                                   stat3.close();
                                }
                                 if(rs3!=null){
                                   rs3.close();
                                }
                            
                           bean.setSTRMISCLIST(InvList1);
                       
                       
                       
                           stat3 = conn.prepareStatement("select a.ITEM_NO,a.ITEM_ID from EI_WALMART_ITEM_ID_DTLS a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and b.EXCS_INV_NO =?");
                           stat3.setString(1,INVOICE_S);
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                            InvList.add(new WalMrtLstBean(rs3.getString("ITEM_NO"),rs3.getString("ITEM_ID")));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                       bean.setINVLLST(InvList);
                            
                            
                       
                           stat3 = conn.prepareStatement("select ITEM_NO,ITEM_DESC,nvl(QTY,0) QTY,nvl(PKGS,0) PKGS,nvl(PCS_PER_BOX,0) PCS_PER_BOX,nvl(NET_WT,0) NET_WT,nvl(PKGS*NET_WT,0) tot_NET_WT,nvl(GR_WT,0) GR_WT,"
                                   + "nvl(PKGS*GR_WT,0) tot_GR_WT ,nvl(BOX_L,0) BOX_L,nvl(BOX_W,0) BOX_W,nvl(BOX_H,0) BOX_H,nvl(BOX_VOL,0) BOX_VOL,nvl(ITEM_RATE,0) ITEM_RATE,nvl(QTY*ITEM_RATE,0) amt,DIMENSION,RATIO from  EI_WALMART_INV_ITEM_DTLS where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                            itmno= rs3.getString("ITEM_NO");
                            itmdesc= rs3.getString("ITEM_DESC");
                            
                           
                        
                            amountval=roundTwoDecimals(rs3.getDouble("amt"))+"";
                            rate1=roundFourDecimals(rs3.getDouble("ITEM_RATE"))+"";
                            boxval=roundFourDecimals(rs3.getDouble("BOX_VOL"))+"";
                            cbm=cbm+rs3.getDouble("BOX_VOL");
                                    
                            ttlqnty=Double.valueOf(rs3.getString("PCS_PER_BOX"))*Double.valueOf(rs3.getString("PKGS"));
                            outrpck=outrpck+Double.valueOf(ttlqnty);
                            
                            totcse=totcse+Double.valueOf(rs3.getString("PKGS"));
                            totunit=totunit+Double.valueOf(rs3.getString("QTY"));
                            
                            netpercse= netpercse+Double.valueOf(rs3.getString("tot_NET_WT"));
                            nettot= nettot+Double.valueOf(rs3.getString("NET_WT"));
                            grospercse= grospercse+Double.valueOf(rs3.getString("tot_GR_WT"));
                            
                            grostot= grostot+Double.valueOf(rs3.getString("GR_WT"));
                            dl= dl+rs3.getString("BOX_L");
                            dw= dw+rs3.getString("BOX_W");
                            dh= dh+rs3.getString("BOX_H");
                            totvol= totvol+Double.valueOf(rs3.getString("BOX_VOL"));
                            unitprice= unitprice+rs3.getString("ITEM_RATE");
                            amount= amount+Double.valueOf(rs3.getString("amt"));
                           
                            
                            InvLineList.add(new WalMartSubLstBean(rs3.getString("ITEM_NO"),rs3.getString("ITEM_DESC"),rs3.getString("QTY"),
                                    rs3.getString("PKGS"),rs3.getString("PCS_PER_BOX"),rs3.getString("NET_WT"),rs3.getString("tot_NET_WT"),rs3.getString("GR_WT"),
                                    rs3.getString("tot_GR_WT"),rs3.getString("BOX_L"),rs3.getString("BOX_W"),rs3.getString("BOX_H"),boxval,rate1,amountval));
                           
                          
                           walInvList.add(new WalMartwalLstBean(rs3.getString("ITEM_NO"),rs3.getString("PKGS"),rs3.getString("PCS_PER_BOX")+"X"+rs3.getString("PKGS"),
                                   rs3.getString("tot_NET_WT"),rs3.getString("tot_GR_WT"), String.valueOf(ttlqnty),rs3.getString("RATIO")));
                           walInvList1.add(new WalMartwalLstBean(rs3.getString("ITEM_NO")));
                           walInvList2.add(new WalMartwalLstBean(rs3.getString("PKGS")));
                          
                           
                           } 
                           if(stat3!=null){
                             stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                       bean.setINV_FC(roundTwoDecimals(netpercse));
                        bean.setCOMM_AMT(roundTwoDecimals(grospercse));
                            
                        bean.setINV_RATE(roundFourDecimals(cbm));
                       
                        bean.setITEMLINELIST(walInvList);
                        bean.setSTYLIST(walInvList1);
                        bean.setDBKLIST(walInvList2);
                        
                        netfc=roundTwoDecimals(amount);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTOTCASE(roundTwoDecimals(totcse));
                        bean.setTOTALUNIT(roundTwoDecimals(totunit));
                        bean.setNETTOTAL(roundTwoDecimals(nettot));
                        bean.setGORSTOTAL(roundTwoDecimals(grostot));
                        bean.setTOTALVAL(roundFourDecimals(totvol));
                        bean.setTOTAMOUNT(roundTwoDecimals(amount));
                        bean.setEXCISE_DUTY(roundTwoDecimals(outrpck));
                        
                        
                        
                       
                        
                      
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
                       
                       
                      
                       
                       //DON'T DELETE BELOW COMMENT
                       
//                         stat=conn.prepareStatement("select sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  seplweb.cidadr_view115 where  sacono=111 and sasuno=? and SAADTE='1' and saadid='001'"); 
//                          stat.setString(1,result1.getString("notify"));
//                          result=stat.executeQuery();
//                          if (result.next() == true) 
//                        { 
//                          bean.setNOTIFY_NAME(result.getString("sasunm"));
//                          bean.setNOTIFY_ADDRESS(result.getString("noticity"));
//                        }
                       
                        
                        WalmartBeanlist.add(bean);
        
                    }
                    

                if (INVOICE_S!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/BuyerInv/");
                    Map param1 = new HashMap();

                   
                    InputStream input;
                    param1.put("SUBREPORT_DIR", path);
                    param1.put("INVOICE_S", INVOICE_S);
                    param1.put("connsepl", conn);

                  
                    input =new FileInputStream(new File(path+"/WalMart_InPacking.jrxml"));
                   
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(WalmartBeanlist));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=WalMart_InPacking.pdf"); //attachment- use open new window and inline- use open in same window
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "WalMart_InPacking.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=WalMart_InPacking.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("WalMart_invAction" + e.toString());
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
         
        
        execute();
        
        return "pckng";
    }
    public String scdprints() throws FileNotFoundException, JRException, IOException, SQLException {

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
             connBI=new ConnectionMovexBi().getConnection();
             conndb2=new connectiondb2().getConnection();
             List WalmartBeanlist=new ArrayList();
                        
             
               //Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                    stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,"
                            + "to_char(a.inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(a.notify) notify,"
                            + "b.TARRIF_NO,a.ac_holder,b.VESSEL_NO,a.hs_code,TRIM(b.manuf_code) manuf_code,a.cost_centre,a.mode_of_ship,a.inv_qty,"
                            + "rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,a.LOADING_port,trim(a.LOADING) CLR_port,"
                            + "a.pre_carriage,a.PLACE,b.DES_PORT,b.COMMON_NAME,a.payment_term,a.ship_term,a.pay_term,b.REMARK,b.FLIGHT_NO,"
                            + "a.DESTI_CNTRY,a.CNTRY_ORIGIN,a.DISCHARGE,a.Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,"
                            + "b.LC_NO,b.PAYTERM,decode(b.payterm,'LC','LC AT SIGHT','OPEN ACCOUNT 90 DAYS') paytermdesc,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,"
                            + "a.manuf_state,a.tax_type,a.tax_percent,a.tax_code,b.DUTY,b.DUTY1,a.transport_cost,facility,CTNS,"
                            + "to_char(b.ETA_DATE,'dd/mm/yy') ETA_DATE,to_char(b.VESSEL_DATE,'dd/mm/yy') VESSEL_DATE,b.RATIO,b.PORT,b.SHIP_CASE,b.DEPT"
                            + " from  ei_endors_mast a, EI_WALMART_INV b  where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO "
                            + "and b.excs_inv_no=?");
                    stat1.setString(1, INVOICE_S);
                    result1 = stat1.executeQuery(); 
                    String invq="";
                    if (result1.next())
                    {  
                        WalmartBean bean = new WalmartBean();
       
                        bean.setTINVQTY(result1.getString("paytermdesc"));
                        bean.setTMISCINR(result1.getString("ac_holder"));
                        bean.setLocation(result1.getString("location"));
                        bean.setExcs_inv_no(result1.getString("excs_inv_no"));
                        bean.setPlan_no(result1.getString("plan_no"));
                        bean.setInv_date(result1.getString("inv_date"));
                        bean.setExp_type(result1.getString("exp_type"));
                        bean.setHs_code(result1.getString("hs_code"));
                        bean.setLcno(result1.getString("LC_NO")); 
                        bean.setINVDATE(result1.getString("PAYTERM"));
                        bean.setCost_centre(result1.getString("cost_centre"));
                        bean.setMode_of_ship(result1.getString("mode_of_ship")); 
                        bean.setBuyer(result1.getString("buyer"));
                        bean.setBuyer_addr(result1.getString("buyer_addr").trim());
                        bean.setCons_addr(result1.getString("cons_addr").trim());
                        bean.setShip_term(result1.getString("ship_term"));
                        bean.setPre_carriage(result1.getString("pre_carriage"));
                        bean.setAgent(result1.getString("TARRIF_NO"));
                        bean.setUpcharge_per(result1.getDouble("COMMON_NAME"));
                        bean.setCTNMNT(result1.getString("DES_PORT"));
                        bean.setFwd_code(result1.getString("VESSEL_NO"));
                        bean.setFwd_custom(result1.getString("FLIGHT_NO")); 
                        bean.setTAX_CAL_PER(result1.getDouble("DUTY"));
                        bean.setCATE(result1.getString("ETA_DATE"));
                        bean.setFACILITY(result1.getString("VESSEL_DATE"));
                        
                        bean.setMANUF_CODE(result1.getString("manuf_code"));
                        bean.setNotify(result1.getString("notify"));
                        bean.setPay_term(result1.getString("pay_term"));
                        bean.setTransport_cost(result1.getDouble("transport_cost"));
                        bean.setPayment_term(result1.getString("payment_term"));
                        bean.setLOADING_PORT(result1.getString("LOADING_PORT"));
                        bean.setComm_per(result1.getString("PORT"));
                        bean.setCLR_PORT(result1.getString("CLR_PORT"));
                        bean.setDESTI_CNTRY(result1.getString("DESTI_CNTRY"));
                        bean.setDIS_CNTRY(result1.getString("DIS_CNTRY"));
                        bean.setCNTRY_ORIGIN(result1.getString("CNTRY_ORIGIN"));
                        bean.setDISCHARGE(result1.getString("DISCHARGE"));
                        bean.setDESTI_CODE(result1.getString("DESTI_CODE"));
                        
                        bean.setCRGODT(result1.getString("REMARK"));
                        bean.setTFOB(result1.getString("RATIO"));
                        bean.setCREATIONNO(result1.getString("dept"));
                        
                        
                        bean.setCRNCY_CODE(result1.getString("crncy_code"));
                        bean.setMANUF_STATE(result1.getString("manuf_state"));
                        bean.setTAX_TYPE(result1.getString("tax_type"));
                        bean.setTAX_PERCENT(result1.getDouble("tax_percent"));
                        
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
                        
                        bean.setSelf_tp(result1.getString("RATIO"));
                        
                        
                        
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
                          
//                          bn=new PreInvoiceDao().getUnitByName(result1.getString("manuf_code"));
//                          bean.setMANUF_DESC(bn.getUNIT_DESC());
//                          bean.setMANUF_ADDRESS(bn.getUNIT_ADDRESS());
                          
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
                       
                        
                           PreparedStatement stat3 = conn.prepareStatement("select BANK,BANK_ADDR from ei_lc_lic_mast where REF_NO=?");
                           stat3.setString(1,result1.getString("LC_NO"));
                           ResultSet rs3=stat3.executeQuery();
                           if(rs3.next()){
                             
                               PreparedStatement stat4 = conndb2.prepareStatement("SELECT BRBKBM from m3fdbprd.CBANBR where brcono=111 and brbkno=? and brbbrn=?");
                               stat4.setString(1,rs3.getString("BANK"));
                               stat4.setString(2,rs3.getString("BANK_ADDR"));
                               ResultSet rs4=stat4.executeQuery();
                               if(rs4.next()){
                                 bean.setTINR(rs4.getString("BRBKBM")); 
                               }
                               
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                        
                         
                           stat3 = conn.prepareStatement("select VEND_NAME,(VEND_ADDR||','||CITY||' '||STATE) Address from pr_vend_mast where vend_code=?");
                           stat3.setString(1,result1.getString("manuf_code"));
                           rs3=stat3.executeQuery();
                           if(rs3.next()){
                             bean.setMANUF_DESC(rs3.getString("VEND_NAME"));
                             bean.setMANUF_ADDRESS(rs3.getString("Address"));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                           List linelist=new ArrayList();
                           stat3 = conn.prepareStatement("select line1  from EI_WALMART_MATERIAL where  year=? and company=? and inv_no=? order by idno");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                             linelist.add(new WalMartwalLstBean(rs3.getString("line1")));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                          bean.setHNGRLIST(linelist);
                            
                           String ctnstot="";
                           stat3 = conn.prepareStatement("select sum(PKGS) pkgs  from EI_WALMART_INV_ITEM_DTLS where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                          if (rs3.next()){
                            ctnstot= rs3.getString("PKGS"); 
                            
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                        bean.setCTNS(ctnstot); 
                        String mbpo="";
                        stat = conn.prepareStatement("select distinct nvl(GR_WT,0) GR_WT from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo=mbpo+result.getString("GR_WT")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo!=null){
                          mbpo=mbpo.substring(0, mbpo.length()-1);}
                         
                          bean.setMSG1(mbpo);
                          
                          String mbpo1="";
                        stat = conn.prepareStatement("select distinct nvl(NET_WT,0) NET_WT from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo1=mbpo1+result.getString("NET_WT")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo1!=null){
                          mbpo1=mbpo1.substring(0, mbpo1.length()-1);}
                         
                          bean.setMSG2(mbpo1);
                          
                           String mbpo2="";
                        stat = conn.prepareStatement("select distinct DIMENSION from  EI_WALMART_INV_ITEM_DTLS where year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo2=mbpo2+result.getString("DIMENSION")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                         if (mbpo2!=null){
                          mbpo2=mbpo2.substring(0, mbpo2.length()-1);}
                         
                          bean.setCLR_PORT(mbpo2);
                          
                          
                         
                            stat3 = conn.prepareStatement("select distinct CATEGORY,PRE_PRINT_NO,TOKEN_NO from ei_endors_dtls where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           if(rs3.next()){
                             bean.setNOTIFY_NAME(rs3.getString("CATEGORY"));
                             bean.setFIRSTQLITY(rs3.getString("PRE_PRINT_NO"));
                             bean.setERR_MSG(rs3.getString("TOKEN_NO"));
                           } 
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
                        List InvList = new ArrayList();
                        List walInvList = new ArrayList();
                        List walInvList1 = new ArrayList();
                        List walInvList2 = new ArrayList();
                        
                        String po_no=""; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double inrconv=0.0;
                        double cartn=0.0;double cartn_tot=0.0; double netfc=0.0;  
                        BigDecimal g1 = new BigDecimal("0.00");double excise_d=0.0;double tnetpcs=0.0;
                        String pono="";String styleno="";String htsno="";String vesletddt="";
                        String desc="";String pcs="";double rate=0.0;double totamt=0.0;
                       String itmno="";String itmdesc="";double outrpck=0.0;double totcse=0.0;double totunit=0.0;double netpercse=0.0;
                       double nettot=0.0;double grospercse=0.0;double grostot=0.0;String dl="";String dw="";String dh="";
                       double totvol=0.0;String unitprice="";double amount=0.0;String amountval="";String rate1="";
                       String dimensn="";String bitmif="";String boxval="";double cbm=0.0;double ttlqnty=0.0;
                       
                       
                       
                       
                           stat3 = conn.prepareStatement("select a.ITEM_NO,a.ITEM_ID from EI_WALMART_ITEM_ID_DTLS a,ei_endors_mast b where a.YEAR=b.YEAR and a.COMPANY=b.COMPANY and a.INV_NO=b.INV_NO and b.EXCS_INV_NO =?");
                           stat3.setString(1,INVOICE_S);
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                            InvList.add(new WalMrtLstBean(rs3.getString("ITEM_NO"),rs3.getString("ITEM_ID")));
                           } 
                           if(stat3!=null){
                              stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                       bean.setINVLLST(InvList);
                            
                            
                       
                           stat3 = conn.prepareStatement("select ITEM_NO,ITEM_DESC,nvl(QTY,0) QTY,nvl(PKGS,0) PKGS,nvl(PCS_PER_BOX,0) PCS_PER_BOX,nvl(NET_WT,0) NET_WT,nvl(PKGS*NET_WT,0) tot_NET_WT,nvl(GR_WT,0) GR_WT,"
                                   + "nvl(PKGS*GR_WT,0) tot_GR_WT ,nvl(BOX_L,0) BOX_L,nvl(BOX_W,0) BOX_W,nvl(BOX_H,0) BOX_H,nvl(BOX_VOL,0) BOX_VOL,nvl(ITEM_RATE,0) ITEM_RATE,nvl(QTY*ITEM_RATE,0) amt,DIMENSION,RATIO from  EI_WALMART_INV_ITEM_DTLS where  year=? and company=? and inv_no=?");
                           stat3.setString(1,result1.getString("YEAR"));
                           stat3.setString(2,result1.getString("COMPANY"));
                           stat3.setString(3,result1.getString("INV_NO"));
                           rs3=stat3.executeQuery();
                           while(rs3.next()){
                            itmno= rs3.getString("ITEM_NO");
                            itmdesc= rs3.getString("ITEM_DESC");
                            
                           
                        
                            amountval=roundTwoDecimals(rs3.getDouble("amt"))+"";
                            rate1=roundFourDecimals(rs3.getDouble("ITEM_RATE"))+"";
                            boxval=roundFourDecimals(rs3.getDouble("BOX_VOL"))+"";
                            cbm=cbm+rs3.getDouble("BOX_VOL");
                                    
                            ttlqnty=Double.valueOf(rs3.getString("PCS_PER_BOX"))*Double.valueOf(rs3.getString("PKGS"));
                            outrpck=outrpck+Double.valueOf(ttlqnty);
                            
                            totcse=totcse+Double.valueOf(rs3.getString("PKGS"));
                            totunit=totunit+Double.valueOf(rs3.getString("QTY"));
                            
                            netpercse= netpercse+Double.valueOf(rs3.getString("tot_NET_WT"));
                            nettot= nettot+Double.valueOf(rs3.getString("NET_WT"));
                            grospercse= grospercse+Double.valueOf(rs3.getString("tot_GR_WT"));
                            
                            grostot= grostot+Double.valueOf(rs3.getString("GR_WT"));
                            dl= dl+rs3.getString("BOX_L");
                            dw= dw+rs3.getString("BOX_W");
                            dh= dh+rs3.getString("BOX_H");
                            totvol= totvol+Double.valueOf(rs3.getString("BOX_VOL"));
                            unitprice= unitprice+rs3.getString("ITEM_RATE");
                            amount= amount+Double.valueOf(rs3.getString("amt"));
                           
                            
                            InvLineList.add(new WalMartSubLstBean(rs3.getString("ITEM_NO"),rs3.getString("ITEM_DESC"),rs3.getString("QTY"),
                                    rs3.getString("PKGS"),rs3.getString("PCS_PER_BOX"),rs3.getString("NET_WT"),rs3.getString("tot_NET_WT"),rs3.getString("GR_WT"),
                                    rs3.getString("tot_GR_WT"),rs3.getString("BOX_L"),rs3.getString("BOX_W"),rs3.getString("BOX_H"),boxval,rate1,amountval));
                           
                          
                           walInvList.add(new WalMartwalLstBean(rs3.getString("ITEM_NO"),rs3.getString("PKGS"),rs3.getString("PCS_PER_BOX")+"X"+rs3.getString("PKGS"),
                                   rs3.getString("tot_NET_WT"),rs3.getString("tot_GR_WT"), String.valueOf(ttlqnty),rs3.getString("RATIO")));
                           walInvList1.add(new WalMartwalLstBean(rs3.getString("ITEM_NO")));
                           walInvList2.add(new WalMartwalLstBean(rs3.getString("PKGS")));
                          
                           
                           } 
                           if(stat3!=null){
                             stat3.close();
                           }
                            if(rs3!=null){
                              rs3.close();
                           }
                            
                       bean.setINV_FC(roundTwoDecimals(netpercse));
                        bean.setCOMM_AMT(roundTwoDecimals(grospercse));
                            
                        bean.setINV_RATE(roundFourDecimals(cbm));
                       
                        bean.setITEMLINELIST(walInvList);
                        bean.setSTYLIST(walInvList1);
                        bean.setDBKLIST(walInvList2);
                        
                        netfc=roundTwoDecimals(amount);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTOTCASE(roundTwoDecimals(totcse));
                        bean.setTOTALUNIT(roundTwoDecimals(totunit));
                        bean.setNETTOTAL(roundTwoDecimals(nettot));
                        bean.setGORSTOTAL(roundTwoDecimals(grostot));
                        bean.setTOTALVAL(roundFourDecimals(totvol));
                        bean.setTOTAMOUNT(roundTwoDecimals(amount));
                        bean.setEXCISE_DUTY(roundTwoDecimals(outrpck));
                        
                        
                        
                       
                        
                      
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
                       
                       
                      
                       
                       //DON'T DELETE BELOW COMMENT
                       
//                         stat=conn.prepareStatement("select sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  seplweb.cidadr_view115 where  sacono=111 and sasuno=? and SAADTE='1' and saadid='001'"); 
//                          stat.setString(1,result1.getString("notify"));
//                          result=stat.executeQuery();
//                          if (result.next() == true) 
//                        { 
//                          bean.setNOTIFY_NAME(result.getString("sasunm"));
//                          bean.setNOTIFY_ADDRESS(result.getString("noticity"));
//                        }
                       
                        
                        WalmartBeanlist.add(bean);
        
                    }
                    

                if (INVOICE_S!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/BuyerInv/");
                    Map param1 = new HashMap();

                   
                    InputStream input;
                    param1.put("SUBREPORT_DIR", path);

                  
                    input =new FileInputStream(new File(path+"WalMart_Scd.jrxml"));
                   
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(WalmartBeanlist));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=WalMart_Scd.pdf"); //attachment- use open new window and inline- use open in same window
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "WalMart_Scd.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=WalMart_InPacking.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("Walmart_InvAction" + e.toString());
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
        
        
        execute();
        
        return "scd";
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
                stat = conn.prepareStatement("select REF_NO,REF_TYPE from ei_lc_lic_mast where REF_TYPE='LC' and REF_NO like ? order by REF_NO");
                stat.setString(1, "%" +SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("REF_NO"), result.getString("REF_TYPE")));
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
        

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn = new connection().getConnection();
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn.prepareStatement("select VEND_CODE,VEND_ADDR from pr_vend_mast where 1=1 and (VEND_CODE like ? or VEND_NAME like ?)");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    SearchList.add(new SearchListBean(result.getString("VEND_CODE"),result.getString("VEND_ADDR")));
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

        return "srch1";
    }

    
    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    double roundFourDecimals(double d) {
        DecimalFormat fourDForm = new DecimalFormat("#.####");
        return Double.valueOf(fourDForm.format(d));
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

    public String getTO_DATE_s() {
        return TO_DATE_s;
    }

    public void setTO_DATE_s(String TO_DATE_s) {
        this.TO_DATE_s = TO_DATE_s;
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

    public List getWalmartList() {
        return WalmartList;
    }

    public void setWalmartList(List WalmartList) {
        this.WalmartList = WalmartList;
    }

    public String getVESELNO() {
        return VESELNO;
    }

    public void setVESELNO(String VESELNO) {
        this.VESELNO = VESELNO;
    }

    public String getCRGORCRDDT() {
        return CRGORCRDDT;
    }

    public void setCRGORCRDDT(String CRGORCRDDT) {
        this.CRGORCRDDT = CRGORCRDDT;
    }

    public String getFLGHTNO() {
        return FLGHTNO;
    }

    public void setFLGHTNO(String FLGHTNO) {
        this.FLGHTNO = FLGHTNO;
    }

    public String getETDDT() {
        return ETDDT;
    }

    public void setETDDT(String ETDDT) {
        this.ETDDT = ETDDT;
    }

    public String getSHIPCASE() {
        return SHIPCASE;
    }

    public void setSHIPCASE(String SHIPCASE) {
        this.SHIPCASE = SHIPCASE;
    }

    public String getMANUFCODE() {
        return MANUFCODE;
    }

    public void setMANUFCODE(String MANUFCODE) {
        this.MANUFCODE = MANUFCODE;
    }

    public String getTARRIFNO() {
        return TARRIFNO;
    }

    public void setTARRIFNO(String TARRIFNO) {
        this.TARRIFNO = TARRIFNO;
    }

    public String getLCOANO() {
        return LCOANO;
    }

    public void setLCOANO(String LCOANO) {
        this.LCOANO = LCOANO;
    }

    public String getLCDESC() {
        return LCDESC;
    }

    public void setLCDESC(String LCDESC) {
        this.LCDESC = LCDESC;
    }

    public String getSHIPNGLINE() {
        return SHIPNGLINE;
    }

    public void setSHIPNGLINE(String SHIPNGLINE) {
        this.SHIPNGLINE = SHIPNGLINE;
    }

    public String getPOTYP() {
        return POTYP;
    }

    public void setPOTYP(String POTYP) {
        this.POTYP = POTYP;
    }

    public String getDEPTCODE() {
        return DEPTCODE;
    }

    public void setDEPTCODE(String DEPTCODE) {
        this.DEPTCODE = DEPTCODE;
    }

    public String getDUTY1() {
        return DUTY1;
    }

    public void setDUTY1(String DUTY1) {
        this.DUTY1 = DUTY1;
    }

    public String getDUTY2() {
        return DUTY2;
    }

    public void setDUTY2(String DUTY2) {
        this.DUTY2 = DUTY2;
    }

    public String getFROMPORT() {
        return FROMPORT;
    }

    public void setFROMPORT(String FROMPORT) {
        this.FROMPORT = FROMPORT;
    }

    public String getDISCHARGEPORT() {
        return DISCHARGEPORT;
    }

    public void setDISCHARGEPORT(String DISCHARGEPORT) {
        this.DISCHARGEPORT = DISCHARGEPORT;
    }

    public String getPCKRATIO() {
        return PCKRATIO;
    }

    public void setPCKRATIO(String PCKRATIO) {
        this.PCKRATIO = PCKRATIO;
    }

    public String getTEXTILE() {
        return TEXTILE;
    }

    public void setTEXTILE(String TEXTILE) {
        this.TEXTILE = TEXTILE;
    }

    public String getMODE() {
        return MODE;
    }

    public void setMODE(String MODE) {
        this.MODE = MODE;
    }

    public String getBA_TYPE() {
        return BA_TYPE;
    }

    public void setBA_TYPE(String BA_TYPE) {
        this.BA_TYPE = BA_TYPE;
    }

    public String getPAYMNTTRM() {
        return PAYMNTTRM;
    }

    public void setPAYMNTTRM(String PAYMNTTRM) {
        this.PAYMNTTRM = PAYMNTTRM;
    }

    public String getFIBRCNTNT() {
        return FIBRCNTNT;
    }

    public void setFIBRCNTNT(String FIBRCNTNT) {
        this.FIBRCNTNT = FIBRCNTNT;
    }

    public List getITMID_L() {
        return ITMID_L;
    }

    public void setITMID_L(List ITMID_L) {
        this.ITMID_L = ITMID_L;
    }

    public List getITMIDDESC_L() {
        return ITMIDDESC_L;
    }

    public void setITMIDDESC_L(List ITMIDDESC_L) {
        this.ITMIDDESC_L = ITMIDDESC_L;
    }

    public List getPCKGES_L() {
        return PCKGES_L;
    }

    public void setPCKGES_L(List PCKGES_L) {
        this.PCKGES_L = PCKGES_L;
    }

    public List getRATIO_L() {
        return RATIO_L;
    }

    public void setRATIO_L(List RATIO_L) {
        this.RATIO_L = RATIO_L;
    }

    public List getPCKRATIO_L() {
        return PCKRATIO_L;
    }

    public void setPCKRATIO_L(List PCKRATIO_L) {
        this.PCKRATIO_L = PCKRATIO_L;
    }

    public List getRATE_L() {
        return RATE_L;
    }

    public void setRATE_L(List RATE_L) {
        this.RATE_L = RATE_L;
    }

    public List getQTY_L() {
        return QTY_L;
    }

    public void setQTY_L(List QTY_L) {
        this.QTY_L = QTY_L;
    }

    public List getNETWGHT_L() {
        return NETWGHT_L;
    }

    public void setNETWGHT_L(List NETWGHT_L) {
        this.NETWGHT_L = NETWGHT_L;
    }

    public List getGROSSWGHT_L() {
        return GROSSWGHT_L;
    }

    public void setGROSSWGHT_L(List GROSSWGHT_L) {
        this.GROSSWGHT_L = GROSSWGHT_L;
    }

    public List getBOX_L_L() {
        return BOX_L_L;
    }

    public void setBOX_L_L(List BOX_L_L) {
        this.BOX_L_L = BOX_L_L;
    }

    public List getBOX_W_L() {
        return BOX_W_L;
    }

    public void setBOX_W_L(List BOX_W_L) {
        this.BOX_W_L = BOX_W_L;
    }

    public List getBOX_H_L() {
        return BOX_H_L;
    }

    public void setBOX_H_L(List BOX_H_L) {
        this.BOX_H_L = BOX_H_L;
    }

    public List getBOX_VOL_L() {
        return BOX_VOL_L;
    }

    public void setBOX_VOL_L(List BOX_VOL_L) {
        this.BOX_VOL_L = BOX_VOL_L;
    }

    public List getTOT_NETWGHT_L() {
        return TOT_NETWGHT_L;
    }

    public void setTOT_NETWGHT_L(List TOT_NETWGHT_L) {
        this.TOT_NETWGHT_L = TOT_NETWGHT_L;
    }

    public List getTOT_GROSSWGHT_L() {
        return TOT_GROSSWGHT_L;
    }

    public void setTOT_GROSSWGHT_L(List TOT_GROSSWGHT_L) {
        this.TOT_GROSSWGHT_L = TOT_GROSSWGHT_L;
    }

    public List getMTRIALBRKUPLIST() {
        return MTRIALBRKUPLIST;
    }

    public void setMTRIALBRKUPLIST(List MTRIALBRKUPLIST) {
        this.MTRIALBRKUPLIST = MTRIALBRKUPLIST;
    }

    public List getITEMBRKUPLIST() {
        return ITEMBRKUPLIST;
    }

    public void setITEMBRKUPLIST(List ITEMBRKUPLIST) {
        this.ITEMBRKUPLIST = ITEMBRKUPLIST;
    }

    public List getMTRIALBRKUP() {
        return MTRIALBRKUP;
    }

    public void setMTRIALBRKUP(List MTRIALBRKUP) {
        this.MTRIALBRKUP = MTRIALBRKUP;
    }

    public String getMANUFADD() {
        return MANUFADD;
    }

    public void setMANUFADD(String MANUFADD) {
        this.MANUFADD = MANUFADD;
    }

    public double getTOT_ITMQTY1() {
        return TOT_ITMQTY1;
    }

    public void setTOT_ITMQTY1(double TOT_ITMQTY1) {
        this.TOT_ITMQTY1 = TOT_ITMQTY1;
    }

    public double getTOT_QTY() {
        return TOT_QTY;
    }

    public void setTOT_QTY(double TOT_QTY) {
        this.TOT_QTY = TOT_QTY;
    }

    public double getTOT_PACKG() {
        return TOT_PACKG;
    }

    public void setTOT_PACKG(double TOT_PACKG) {
        this.TOT_PACKG = TOT_PACKG;
    }

    public String getITMIDL() {
        return ITMIDL;
    }

    public void setITMIDL(String ITMIDL) {
        this.ITMIDL = ITMIDL;
    }

    public double getTOTITMQTY() {
        return TOTITMQTY;
    }

    public void setTOTITMQTY(double TOTITMQTY) {
        this.TOTITMQTY = TOTITMQTY;
    }

    public List getITEM_NO() {
        return ITEM_NO;
    }

    public void setITEM_NO(List ITEM_NO) {
        this.ITEM_NO = ITEM_NO;
    }

    public List getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(List ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public List getITEM_RATIO() {
        return ITEM_RATIO;
    }

    public void setITEM_RATIO(List ITEM_RATIO) {
        this.ITEM_RATIO = ITEM_RATIO;
    }

    public List getITEM_PCKGS() {
        return ITEM_PCKGS;
    }

    public void setITEM_PCKGS(List ITEM_PCKGS) {
        this.ITEM_PCKGS = ITEM_PCKGS;
    }

    public double getQTYID() {
        return QTYID;
    }

    public void setQTYID(double QTYID) {
        this.QTYID = QTYID;
    }

    public String getInvid() {
        return invid;
    }

    public void setInvid(String invid) {
        this.invid = invid;
    }

    public List getITEM_QTY() {
        return ITEM_QTY;
    }

    public void setITEM_QTY(List ITEM_QTY) {
        this.ITEM_QTY = ITEM_QTY;
    }

    public double getTOTITEM_QTY() {
        return TOTITEM_QTY;
    }

    public void setTOTITEM_QTY(double TOTITEM_QTY) {
        this.TOTITEM_QTY = TOTITEM_QTY;
    }

    public List getSRN1() {
        return SRN1;
    }

    public void setSRN1(List SRN1) {
        this.SRN1 = SRN1;
    }

    public String getITEMNOFIX() {
        return ITEMNOFIX;
    }

    public void setITEMNOFIX(String ITEMNOFIX) {
        this.ITEMNOFIX = ITEMNOFIX;
    }

    public String getPricefc() {
        return pricefc;
    }

    public void setPricefc(String pricefc) {
        this.pricefc = pricefc;
    }

   
    
  

    

   
}