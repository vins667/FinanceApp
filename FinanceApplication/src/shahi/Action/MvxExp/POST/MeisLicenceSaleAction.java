package shahi.Action.MvxExp.POST;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import shahi.Action.MvxExp.POST.Beans.CodeDescBean;
import shahi.Action.MvxExp.POST.Beans.InvMeisBean;
import shahi.Action.MvxExp.PRE.Beans.BuyersearchBean;
import shahi.Action.MvxExp.POST.Beans.MeisLicBean;
import shahi.Action.MvxExp.POST.Beans.LicSearchBean;
import shahi.Action.MvxExp.PRE.Beans.TaxtypeBean;
import shahi.Action.MvxExp.POST.Beans.MeisInvListBeans;
import shahi.Action.MvxExp.PRE.Beans.ScrapSaleListBean;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connectiondb2;

public class MeisLicenceSaleAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private String WHO_S;
    private String INVOICE_S;
    private String BUYER_S;
    private String WAREHOUSENO;
    private String COMPANY;
    private String INVOICENO;
    private String INVOICEDATE;
    private String BUYER;
    private String REM;
    private String BUYER_DESC;
    private String BUYER_ADDR;
    private String INV_NO;
    private String PARAA;
    private List ITEM_DESC_L;
    private String FINAL_P;
    private String FINAL_CHK;
    private String TAX_TYPE;
    private String TAX_PER;
    private String TAX_CODE;
    private List LIC_NO;
    private List SALE_AMT;
    private List LIC_AMT;
    private List PORT_CODE;
    private List PRCT;
    private List buyeraddlist = new ArrayList();
    private List Taxtypelist = new ArrayList();
    private List whouselist = new ArrayList();
    private List LicSearch = new ArrayList();
    private String updFlag;
    private String saveFlag;
    private double TOTAL_SALE_AMT;
    private double TOTAL_LIC_AMT;
    private String FACTORYCODE;
    private String FACTCODE;
    private String STATE_DESC;
    private List unitlist = new ArrayList();
    private String currentdate;
    private List showList;
    private String aausrid;
    private List ShowDetail = new ArrayList();
    private ByteArrayInputStream inputStream;
    private List<String> BUYER_L;
    private String SEARCH_DATE1;
    private String SEARCH_DATE2;
    private List linkno = new ArrayList();
    private List yer = new ArrayList();
    private List listdata = new ArrayList();
    private String SEARCH_CODE;
    private String LICDATE;
    private String LICPORT;
    private String LICAMT;
    private String LICPRCT;
    private String SALEAMT;
    private String indexname;
    private List HSN_CODE_L;
    private List HSN_CODE_PER_L;
    private String WHOUSE;
    private String F_CODE;
    private String Flg;
    @Override
    public String execute() {

        try {
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }

        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }


        Connection conn = null;
        Connection connDB2 = null;
        PreparedStatement stat2 = null;
        PreparedStatement stat1 = null;
        ResultSet result2 = null;
        ResultSet result1 = null;
        try {
            conn = new connection().getConnection();
            connDB2 = new connectiondb2().getConnection();

            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch  


        try {

            Date sysdate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            currentdate = sdf.format(sysdate);

            stat2 = conn.prepareStatement("select location_code from seh_web_users where user_id=?");
            stat2.setString(1, usrid);
            result2 = stat2.executeQuery();
            while (result2.next()) {
                LOCATION_CODE = result2.getString("location_code");
            }
 
            stat2 = conn.prepareStatement("select distinct STATE_CODE from EI_WAREHOUSE where loct_code=? order by 1");
            stat2.setString(1, LOCATION_CODE);
            result2 = stat2.executeQuery();
            while (result2.next()) {
                whouselist.add(result2.getString("STATE_CODE"));
            }
             whouselist.add(LOCATION_CODE);

            String q1 = " and 1=1";
            if (WHO_S != null && WHO_S.length() > 0) {
                q1 += " AND facility ='" + WHO_S + "'";
            }
            if (INVOICE_S != null && INVOICE_S.length() > 0) {
                q1 += " and EXCS_INV_NO='" + INVOICE_S + "'";
            }

            if (BUYER_S != null && BUYER_S.length() > 0) {
                q1 += " and BUYER like '" + BUYER_S.toUpperCase().trim() + "%'";
            }



            if (SEARCH_DATE1 != null && SEARCH_DATE1.length() > 0) {

                stat2 = conn.prepareStatement("select facility,COMPANY,EXCS_INV_NO,to_char(INV_DATE,'dd/mm/yyyy') INV_DATE,BUYER,To_char(FINAL_PRINT,'yyyy-MM-dd') FINAL_PRINT,BUYER_ADDR,TAX_TYPE,TAX_CODE,TAX_PERCENT,REMARK from  EI_LOCALINV_SERIES where trunc(INV_DATE) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')  " + q1 + "order by 1");
                stat2.setString(1, SEARCH_DATE1.substring(0, 10));
                stat2.setString(2, SEARCH_DATE2.substring(0, 10));
                result2 = stat2.executeQuery();
                while (result2.next()) {

                    stat1 = connDB2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                    stat1.setString(1, result2.getString("BUYER").trim());
                    stat1.setString(2, result2.getString("buyer_addr").trim());
                    result1 = stat1.executeQuery();
                    if (result1.next() == true) {
                        BUYER_DESC = result1.getString("opcunm");

                    }
                    BUYER_DESC = result2.getString("BUYER") + "-" + BUYER_DESC;

                    ShowDetail.add(new MeisLicBean(result2.getString("facility"), result2.getString("EXCS_INV_NO"), BUYER_DESC,
                            result2.getString("INV_DATE"), result2.getString("REMARK"), result2.getString("TAX_CODE"), result2.getString("TAX_TYPE"), result2.getString("TAX_PERCENT"), result2.getString("FINAL_PRINT"), result2.getString("COMPANY")));
                    BUYER_DESC = null;
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }






        return SUCCESS;
    }

    public String prints() throws FileNotFoundException, JRException, IOException {

        Connection conn = null;
        Connection connDB2 = null;
        PreparedStatement stat2 = null;
        PreparedStatement stat1 = null;
        ResultSet result2 = null;
        ResultSet result1 = null;
        try {
            conn = new connection().getConnection();
            connDB2 = new connectiondb2().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch
        try {

            List MeisListBeans = new ArrayList();

            if (INVOICENO != null) {
                String BuyerName = "";
                String BuyerAddress = "";
                String BuyerTin = "";
                String BuyerState="";
                String Name = "";
                double taxp = 0.0;
                double taxtot = 0.0;
                String taxtype = "";
                String UTIN = "";
                String UName = "";
                double netfc = 0.0;
                double nettotalp = 0.0;

                stat1 = conn.prepareStatement("select c.LIC_NO,c.LIC_DATE,c.PORT_CODE,c.LIC_AMT,c.SALE_AMT,trim(a.BUYER) BUYER,trim(a.BUYER_ADDR) BUYER_ADDR,a.FACILITY,to_char(a.INV_DATE,'dd-Mon-yyyy') INV_DATE,a.TAX_TYPE,nvl(a.TAX_PERCENT,0) tax_percent,unit_code from ei_localinv_series a,ei_mlfs_sale b,ei_mlfs_mast c where a.EXCS_INV_NO=b.EXCS_INV_NO and b.LIC_NO=c.LIC_NO and a.EXCS_INV_NO=? order by LIC_NO");
                stat1.setString(1, INVOICENO.trim());
                result1 = stat1.executeQuery();
                if (result1.next()) {
                    MeisInvListBeans bean = new MeisInvListBeans();

                    bean.setINVOICE_DATE(result1.getString("INV_DATE"));
                    taxp = Double.valueOf(result1.getString("TAX_PERCENT"));
                    taxtype = result1.getString("TAX_TYPE");
 
 
                    List InvLineList = new ArrayList();
                    PreparedStatement stat3 = null;
                    stat3 = conn.prepareStatement("select c.LIC_NO,c.LIC_DATE,c.PORT_CODE,c.LIC_AMT,c.SALE_AMT,trim(a.BUYER) BUYER,trim(a.BUYER_ADDR) BUYER_ADDR,a.FACILITY from ei_localinv_series a,ei_mlfs_sale b,ei_mlfs_mast c where a.EXCS_INV_NO=b.EXCS_INV_NO and b.LIC_NO=c.LIC_NO and a.EXCS_INV_NO=? order by LIC_NO");
                    stat3.setString(1, INVOICENO.trim());
                    ResultSet result3 = stat3.executeQuery();

                    double trcost = 0.0;
                    double iqty = 0.0;
                    double tqty = 0;
                    double tfob = 0.0;
                    String chkuom = "";
                    double tdbkinr = 0;
                    double tmiscinr = 0.0;
                    double tinr = 0.0;
                    double inrconv = 0.0;
                    double excise_d = 0.0;
                    double tnetwt = 0.0;
                    double tot_inv = 0.0;
                    double rate_inv = 0.0;

                    while (result3.next()) {


                        tfob = tfob + result3.getDouble("LIC_AMT");
                        tnetwt = tnetwt + result3.getDouble("SALE_AMT");



                        InvLineList.add(new InvMeisBean(result3.getString("LIC_NO"), result3.getString("LIC_DATE"), result3.getString("PORT_CODE"), result3.getBigDecimal("LIC_AMT"), result3.getBigDecimal("SALE_AMT")));
                    }

                    if (taxp > 0) {
                        taxtot = tnetwt * Double.valueOf(taxp) / 100;
                        nettotalp = tnetwt + taxtot;

                        bean.setTAXPERCNT(taxtot);
                        bean.setTOTAL_SAL_AMT_PER(roundTwoDecimals(nettotalp));
                        bean.setTAXTYPE(taxtype);
                    }

                    bean.setINVLINELST(InvLineList);
                    bean.setTOTAL_AMT(tfob);
                    bean.setTOTAL_SAL_AMT(tnetwt);











                  //  stat2 = conn.prepareStatement("select OAADTH,oaadK1 u_code,trim(oaconm) u_name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3)||' '||TRIM(OAADR4) U_ADDR ,XRXCSN cst,XRXLSN tin from ciaddr_VIEW a,xiaddr_view b where oacono=111 and oaadth=1 and oaadth=XRADTH and oacono=xrcono and oaadk2=xradk2 and trim(OAADK2)=trim(?)");
                      stat2=conn.prepareStatement("select  OAADK2,OACONM,trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,OATAXC,OAECAR ,aRXCSN cst,aRXLSN tin,arxlcn gstin,arfre1 statecode  from seplweb.ciaddr_VIEW115 a,seplweb.xinddr_view115 b where oacono=111 and oaadth=1 and oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and oaadk3=?");
                     stat2.setString(1, result1.getString("unit_code"));

                    result2 = stat2.executeQuery();
                    if (result2.next()) {
                        bean.setCOMP_NAME(result2.getString("OACONM").trim());
                        bean.setCOMP_ADDR(result2.getString("addr").trim());
                        bean.setCOMP_CST(result2.getString("STATECODE").trim());
                        bean.setCOMP_TIN(result2.getString("gstin").trim());
                        
                    }


                    //netfc=result1.getDouble("FOBAMT");
                  //  stat2 = connDB2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd,OPVRNO  from  ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                      stat2 = connDB2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3) opadd,OPVRNO,OPECAR  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                                      
                    stat2.setString(1, result1.getString("BUYER"));
                    stat2.setString(2, result1.getString("BUYER_ADDR").trim());
                    result2 = stat2.executeQuery();
                    if (result2.next() == true) {
                        BuyerName = result2.getString("opcunm");
                        BuyerAddress = result2.getString("opadd");
                         BuyerTin=result2.getString("OPVRNO");
                         BuyerState=result2.getString("OPECAR");
                    }
                    bean.setCONS_NAME(BuyerName);
                    bean.setCONS_ADDR(BuyerAddress);
                    bean.setCONS_TIN(BuyerTin);
                    bean.setBUYER_STATE(BuyerState);

                    String wordfc = "";

                    stat1 = conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                    stat1.setDouble(1, roundTwoDecimals(nettotalp));
                    stat1.setDouble(2, roundTwoDecimals(nettotalp));
                    stat1.setDouble(3, roundTwoDecimals(nettotalp));
                    result1 = stat1.executeQuery();
                    if (result1.next()) {
                        wordfc = result1.getString("aa");

                        if (result1.getString("aa1") != null) {
                            wordfc = wordfc + "  And  " + result1.getString("aa1") + " paise  Only.";
                        } else {
                            wordfc = wordfc + " Only.";
                        }

                    }

                    bean.setWORDAMT(wordfc);




                    MeisListBeans.add(bean);

                }



                ActionContext ac = ActionContext.getContext();
                ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/POST");
                Map param1 = new HashMap();
                param1.put("conndb2", connDB2);
                param1.put("INVOICENO", INVOICENO);
 
                InputStream input;
                param1.put("SUBREPORT_DIR", path);
                input = new FileInputStream(new File(path + "/MEISInvoice.jrxml"));


                JasperDesign design = JRXmlLoader.load(input);
                JasperReport rep = JasperCompileManager.compileReport(design);


                JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(MeisListBeans));


                ServletOutputStream out1 = response.getOutputStream();
                response.reset();
                byte[] bytes = null;


                response.setHeader("Content-Disposition", "attachment;filename=MeisInvoice.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return "prnt";
    }

    public String update() throws SQLException {
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement stat2 = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;

        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch   


        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String date1 = null;
        String date2 = null;

        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");


        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0;

            stat = conn.prepareStatement("select location_code from seh_web_users where user_id=?");
            stat.setString(1, usrid);
            result = stat.executeQuery();
            while (result.next()) {
                LOCATION_CODE = result.getString("location_code");
            }
            result.close();
            stat.close();
            if (updFlag != null && updFlag.length() > 0) {

                stat = conn.prepareStatement("UPDATE  exports.EI_LOCALINV_SERIES  SET REMARK=upper(?),BUYER=?,BUYER_ADDR=?,TAX_CODE=upper(?),TAX_TYPE=upper(?),TAX_percent=?,"
                        + " FINAL_PRINT=decode(?,'Y',sysdate,null),tdate=sysdate,seh_user=?,BUYER_STATE=? WHERE EXCS_INV_NO=?");
 
                stat.setString(1, REM);
                stat.setString(2, BUYER);
                stat.setString(3, BUYER_ADDR);
                stat.setString(4, TAX_CODE);
                stat.setString(5, TAX_TYPE);
                stat.setString(6, TAX_PER);
                stat.setString(7, FINAL_CHK);
                stat.setString(8, usrid);
                stat.setString(9,STATE_DESC);
                stat.setString(10, INVOICENO);
                stat.executeUpdate();
 
                stat2 = conn.prepareStatement("delete from exports.ei_mlfs_sale where excs_inv_no=?");
                stat2.setString(1, INVOICENO);
                stat2.executeUpdate();

                String vlic = "";  
                if (LIC_NO != null && LIC_NO.size() > 0 && !LIC_NO.isEmpty()) {
                    for (int i = 0; i < LIC_NO.size(); i++) {

                        if (LIC_NO.get(i) != null && !LIC_NO.get(i).toString().isEmpty()) {
                            double vamt = 0.0;
                            double vlic_amt = 0.0;
                            double v_percent = 0.0;
                            double v1 = 0.0;
                            double v_hsnper=0.0;
                            double v_tax=0.0; String vhsn="";
                            vlic = LIC_NO.get(i).toString().trim();
                            
                            vlic_amt = Double.parseDouble(LIC_AMT.get(i).toString());
                            v_percent = Double.parseDouble(PRCT.get(i).toString());
                           if (!HSN_CODE_PER_L.get(i).toString().isEmpty())
                           {   System.out.println("percent "+HSN_CODE_PER_L.get(i).toString());  
                               v_hsnper=Double.parseDouble(HSN_CODE_PER_L.get(i).toString().trim());
                               vhsn=HSN_CODE_L.get(i).toString().trim();
                            }
                           if (!TAX_PER.isEmpty())
                           {
                               v_tax=0;
                           }
                            vamt = vlic_amt * v_percent / 100;
                            v1 = 100 + v_tax;
                            vamt = vamt / v1 * 100;

                            stat1 = conn.prepareStatement(" SELECT * from  exports.ei_mlfs_sale where lic_no=? ");
                            stat1.setString(1, vlic);
                            result1 = stat1.executeQuery();
                            if (result1.next()) {
                                addActionMessage("Licence No already Exist... !!!" + vlic);
                                return ERROR;
                            } else {
                                if (vamt > 0) {
                                    stat1 = conn.prepareStatement("insert into exports.ei_mlfs_sale (excs_inv_no,lic_no,sale_amt,prct,portcode,lic_amt,hsn_code,hsn_percent,seh_user,tdate) values (?,?,?,?,?,upper(?),?,?,?,sysdate)");
                                    stat1.setString(1, INVOICENO);
                                    stat1.setString(2, vlic);
                                    stat1.setDouble(3, vamt);
                                    stat1.setDouble(4, v_percent);
                                    stat1.setString(5, PORT_CODE.get(i).toString());
                                    stat1.setDouble(6, vlic_amt);
                                    stat1.setString(7,vhsn);
                                    stat1.setDouble(8,v_hsnper);
                                    stat1.setString(9, usrid);
                                    stat1.executeUpdate();
                                    y = 1;
                                }
                            }

                        }
                    }



                }


                if (y == 1) {
                    conn.commit();
                    INVOICENO = "";
                    INVOICE_S = "";
                    LIC_NO = null;
                    SALE_AMT = null;
                    LIC_AMT = null;
                    PORT_CODE = null;
                    PRCT = null;
                    listdata = null;
                    TOTAL_LIC_AMT = 0;
                    TOTAL_SALE_AMT = 0;
                    INVOICEDATE = null;
                    FINAL_P = null;
                    REM = null;
                    BUYER = null;
                    BUYER_ADDR = null;
                    TAX_PER = null;
                    TAX_TYPE = null;
                    TAX_CODE = null;
                    WAREHOUSENO = null;
                    HSN_CODE_PER_L=null;
                    HSN_CODE_L=null;
                     STATE_DESC=null;
                    BUYER_DESC=null;
                    BUYER_ADDR=null;
                    addActionMessage("Record Updated succcessfully ");

                }
            }
            String newno = "";
            String FINYR = "";
            String vex = "";
            if (saveFlag != null && saveFlag.length() > 0) {
                String stcode = "";
                stat = conn.prepareStatement("select GST_STATE_CODE from seplvportal.state_master where COUNTRY_MASTER_ID=35 AND state_code=?");
                stat.setString(1, WAREHOUSENO);
                result = stat.executeQuery();
                if (result.next() == true) {
                    stcode = result.getString("GST_STATE_CODE");
                }

                result.close();
                stat.close();
                if (stcode == null) {
                    addActionMessage("Error in State Code......");
                    return ERROR;
                }

                stat = conn.prepareStatement("select vou_numb+1 vno ,lpad(nvl(vou_numb,0)+1,4,'0') newex4,lpad(nvl(vou_numb,0)+1,5,'0') newex5,SUBSTR(FIN_YEAR,3,2) FIN_YEAR from ei_vou_numb_mast where location_code=? and fin_year=pay_fin_year(sysdate) and  VOU_TYPE='LG' AND SUB_TYPE='DOM' for update NOWAIT");
                stat.setString(1, WAREHOUSENO);
                result = stat.executeQuery();
                if (result.next() == true) {
                    newno = result.getString("vno");
                    FINYR = result.getString("FIN_YEAR");
                    //   INVOICENO=WAREHOUSENO.substring(0,2)+FINYR+result.getString("newex5");
                    INVOICENO = "1" + stcode + FINYR + result.getString("newex5");
                }
                result.close();
                stat.close();


                if (newno == null) {
                    addActionMessage("Error in Invoice Series......");
                    return ERROR;
                }


                stat = conn.prepareStatement("insert into  EI_localinv_series (company,facility,EXCS_INV_NO,REMARK,fin_year,BUYER,buyer_addr,tax_type,tax_code,tax_percent,inv_date,final_print,loct_code,seh_user,BUYER_STATE,unit_code,tdate) "
                        + " values('111',?,?,upper(?),pay_fin_year(sysdate),?,?,UPPER(?),UPPER(?),?,trunc(sysdate),decode(?,'Y',sysdate,null),?,?,?,?,sysdate)");
                stat.setString(1, WAREHOUSENO);
                stat.setString(2, INVOICENO);
                stat.setString(3, REM);
                stat.setString(4, BUYER);
                stat.setString(5, BUYER_ADDR);
                stat.setString(6, TAX_TYPE);
                stat.setString(7, TAX_CODE);
                stat.setString(8, TAX_PER); 
                stat.setString(9, FINAL_CHK);
                stat.setString(10, LOCATION_CODE.trim());
 
                stat.setString(11, usrid);
                stat.setString(12,STATE_DESC);
                stat.setString(13,FACTORYCODE);
                stat.executeUpdate();

                String vlic = ""; 
                if (LIC_NO != null && LIC_NO.size() > 0 && !LIC_NO.isEmpty()) {
                    for (int i = 0; i < LIC_NO.size(); i++) {

                        if (LIC_NO.get(i) != null && !LIC_NO.get(i).toString().isEmpty()) {

                            vlic = LIC_NO.get(i).toString().trim();
                            double vamt = 0.0;
                            double vlic_amt = 0.0;
                            double v1 = 0.0;
                            double v_percent = 0.0;
                            double v_hsnper=0.0;
                            double v_tax=0.0;String vhsn="";
                            vlic = LIC_NO.get(i).toString().trim();
                            vlic_amt = Double.parseDouble(LIC_AMT.get(i).toString());
                            v_percent = Double.parseDouble(PRCT.get(i).toString());
                           
                            if (!HSN_CODE_PER_L.get(i).toString().isEmpty())
                                
                           {   System.out.println("percent "+HSN_CODE_PER_L.get(i).toString().contains(""));  
                               v_hsnper=Double.parseDouble(HSN_CODE_PER_L.get(i).toString().trim());
                               vhsn=HSN_CODE_L.get(i).toString().trim();
                            }
                             if (!TAX_PER.isEmpty())
                           {
                               v_tax=0;
                           }
                            vamt = vlic_amt * v_percent / 100;
                            v1 = 100 + v_tax;
                            vamt = vamt / v1 * 100;

                            stat1 = conn.prepareStatement(" SELECT * from  exports.ei_mlfs_sale where lic_no=? ");
                            stat1.setString(1, vlic);
                            result1 = stat1.executeQuery();
                            if (result1.next()) {
                                addActionMessage("Licence No already Exist... !!!" + vlic);
                                return ERROR;
                            } else {
                                if (vamt > 0) {

                                    stat1 = conn.prepareStatement("insert into exports.ei_mlfs_sale (excs_inv_no,lic_no,sale_amt,prct,portcode,lic_amt,hsn_code,hsn_percent,seh_user,tdate) values (?,?,?,?,upper(?),?,?,?,?,sysdate)");
                                    stat1.setString(1, INVOICENO);
                                    stat1.setString(2, vlic);
                                    stat1.setDouble(3, vamt);
                                    stat1.setDouble(4, v_percent);
                                    stat1.setString(5, PORT_CODE.get(i).toString());
                                    stat1.setDouble(6, vlic_amt);
                                    stat1.setString(7,vhsn);
                                    stat1.setDouble(8,v_hsnper);
                                    stat1.setString(9, usrid);
                                    stat1.executeUpdate();
                                    y = 1;
                                }
                            }

                        }
                    }

                }
                if (stat1 != null) {
                    stat1.close();
                }
                if (y > 0) {
                    stat2 = conn.prepareStatement("update ei_vou_numb_mast set vou_numb=?  where location_code=? and fin_year=pay_fin_year(sysdate) and  VOU_TYPE='LG' AND SUB_TYPE='DOM' ");
                    stat2.setString(1, newno);
                    stat2.setString(2, WAREHOUSENO);
                    stat2.executeUpdate();

                    conn.commit();
                    if (stat2 != null) {
                        stat2.close();
                    }


                    INVOICE_S = "";
                    LIC_NO = null;
                    LIC_AMT=null;
                    SALE_AMT = null;
                    PORT_CODE = null;
                    PRCT = null;
                    listdata = null;
                    TOTAL_LIC_AMT = 0;
                    TOTAL_SALE_AMT = 0;
                    INVOICEDATE = null;
                    FINAL_P = null;
                    REM = null;
                    BUYER = null;
                    BUYER_ADDR = null;
                    TAX_PER = null;
                    TAX_TYPE = null;
                    TAX_CODE = null;
                    WAREHOUSENO = null;
                    HSN_CODE_L=null;
                    HSN_CODE_PER_L=null;
                    STATE_DESC=null;
                    BUYER_DESC=null;
                    BUYER_ADDR=null;
                    addActionMessage("Record Insert succcessfully " + INVOICENO);
                    INVOICENO = "";
                }



            }

            if (result1 != null) {
                result1.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (stat2 != null) {
                stat2.close();
            }

            if (result != null) {
                result.close();
            }
            if (stat != null) {
                stat.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MeisLicence" + e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (result != null) {
                result.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result1 != null) {
                result1.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (stat2 != null) {
                stat2.close();
            }
        }





        return "updte";
    }

    public String edit() throws SQLException {

        Connection conn = null;
        Connection conndb2 = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        try {
            conn = new connection().getConnection();
            conndb2 = new connectiondb2().getConnection();
            conn.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch  

        PreparedStatement stat = null;
        ResultSet result = null;

        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");


        try {


            stat2 = conn.prepareStatement("select company,to_char(inv_date,'dd/mm/yyyy') inv_date,facility,remark,buyer,buyer_addr,to_char(final_print,'dd/mm/yyyy') final_print,mvx_date text_id ,tax_type,tax_code,tax_percent,buyer_state,unit_code from ei_localinv_series where EXCS_INV_NO=?");
            stat2.setString(1, INVOICENO);
            result2 = stat2.executeQuery();
            while (result2.next()) { 

                WAREHOUSENO = result2.getString("facility");
                INVOICEDATE = result2.getString("inv_date");
                FINAL_P = result2.getString("final_print");
                REM = result2.getString("remark");
                BUYER = result2.getString("buyer");
                BUYER_ADDR = result2.getString("BUYER_ADDR");
                COMPANY = result2.getString("COMPANY");
                TAX_PER = result2.getString("tax_percent");
                TAX_TYPE = result2.getString("tax_type");
                TAX_CODE = result2.getString("tax_code");
                STATE_DESC=result2.getString("buyer_state");
                FACTORYCODE=result2.getString("unit_code");
                stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                stat.setString(1, BUYER.trim());
                stat.setString(2, BUYER_ADDR.trim());
                result = stat.executeQuery();
                if (result.next() == true) {
                    BUYER_DESC = result.getString("opcunm");

                }

            }
            stat2 = conn.prepareStatement("select excs_inv_no,a.lic_no,a.sale_amt,a.prct,b.port_code,to_char(lic_date,'dd/mm/yyyy') lic_date,b.lic_amt,hsn_code,hsn_percent  from ei_mlfs_sale a,ei_mlfs_mast b where a.lic_no=b.lic_no and a.EXCS_INV_NO=? ");
            stat2.setString(1, INVOICENO);
            result2 = stat2.executeQuery();
            while (result2.next()) {
                TOTAL_LIC_AMT = TOTAL_LIC_AMT + result2.getFloat("lic_amt");
                TOTAL_SALE_AMT = TOTAL_SALE_AMT + result2.getFloat("sale_amt");
                listdata.add(new MeisLicBean(result2.getString("lic_no"), result2.getString("lic_date"), result2.getString("port_code"), result2.getString("lic_amt"), result2.getString("prct"), result2.getString("sale_amt"),result2.getString("hsn_code"), result2.getString("hsn_percent"), "", ""));
            }
            if (stat2 != null) {
                stat2.close();
            }
            if (result2 != null) {
                result2.close();
            }


            stat2 = conn.prepareStatement("select trim(oaadK1) oaadk1,oaconm||'-'||TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM seplweb.CIADDR_VIEW115 WHERE OACONO=111 AND OAADTH=4");
            result2 = stat2.executeQuery();
            while (result2.next()) {
                //String detial=result2.getString("oaconm");

                unitlist.add(new CodeDescBean(result2.getString("oaadK1"), result2.getString("UADDR")));

            }
            if (stat2 != null) {
                stat2.close();
            }
            if (result2 != null) {
                result2.close();
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {


            if (stat2 != null) {
                stat2.close();
            }
            if (result2 != null) {
                result2.close();
                //   BUYER_DESC=BUYER_DESC;

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }





        return "edit";
    }

    public String buyersearch() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        if (usrid == null && loc == null) {
            addActionError("Session not valid!!!");
            return SUCCESS;
        }

        Connection conn1 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conn1 = new connectiondb2().getConnection();

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = conn1.prepareStatement("select opcuno,opadid,opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd,OPECAR  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and (OPCUNO like ? or OPCUNM like ?) ");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    buyeraddlist.add(new BuyersearchBean(result.getString("OpCUNO"), result.getString("OpCUNM"), result.getString("OpADID"), result.getString("opadd"),result.getString("opecar")));
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn1 != null) {
                conn1.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return "buyersrch";
    }

    public String taxsearch() throws SQLException {

        Connection consepl = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;

        try {
            consepl = new connectiondb2().getConnection();
            conn = new connection().getConnection();
            double percent = 0.0;
 
            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = consepl.prepareStatement("select CKCSNO,CKSPFA from m3fdbprd.csycsn where CKCSNO like ?");
                 stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    Taxtypelist.add(new CodeDescBean(result.getString("CKCSNO"), result.getString("CKSPFA")));
                }
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (consepl != null) {
                consepl.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return "taxsrch";
    }

    public String licsearch() throws SQLException {


        Connection consepl = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;

        try {
            consepl = new connection().getConnection();
            double percent = 0.0;

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = consepl.prepareStatement(" select lic_no,to_char(LIC_date,'dd/mm/yyyy') lic_date,lic_amt,PORT_CODE,97.5 prct,round((lic_amt*97.5)/100,2) sale_amt from  ei_mlfs_mast where  nvl(sale_amt,0)=0 and fwd_ac is not null and lic_no like ?");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {

                    LicSearch.add(new LicSearchBean(result.getString("lic_no"), result.getString("lic_date"), result.getString("PORT_CODE"), result.getString("prct"), result.getString("lic_amt"), result.getString("sale_amt")));
                }
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (consepl != null) {
                consepl.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return "licsrch";
    }

    public String searchcode() throws SQLException {
        Connection consepl = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        try {
            consepl = new connection().getConnection();
            double percent = 0.0;

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat = consepl.prepareStatement(" select lic_no,to_char(LIC_date,'dd/mm/yyyy') lic_date,lic_amt,fwd_port,97.5 prct,round((lic_amt*97.5)/100,2) sale_amt from  ei_mlfs_mast where  nvl(sale_amt,0)=0 and fwd_ac is not null and trim(lic_no)=?");
                stat.setString(1, SEARCH_CODE.trim());
                result = stat.executeQuery();
                if (result.next()) {
                    LICDATE = result.getString("lic_date");
                    LICPORT = result.getString("fwd_port");
                    LICAMT = result.getString("lic_amt");
                    LICPRCT = result.getString("prct");
                    SALEAMT = result.getString("sale_amt");

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (consepl != null) {
                consepl.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }
        }

        return SUCCESS;
    }

    public String newentry() {
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


        try {

            stat2 = conn.prepareStatement("select location_code from seh_web_users where user_id=?");
            stat2.setString(1, usrid);
            result2 = stat2.executeQuery();
            while (result2.next()) {
                LOCATION_CODE = result2.getString("location_code");
            }
            if (stat2 != null) {
                stat2.close();
            }
            if (result2 != null) {
                result2.close();
            }

            stat2 = conn.prepareStatement(" select distinct STATE_CODE from EI_WAREHOUSE where loct_code=? order by 1");
            stat2.setString(1, LOCATION_CODE);
            result2 = stat2.executeQuery();
            while (result2.next()) {
                whouselist.add(result2.getString("STATE_CODE"));

            }
            if (stat2 != null) {
                stat2.close();
            }
            if (result2 != null) {
                result2.close();
            }
            System.out.println("flag "+Flg+" Warehouse "+WHOUSE);
                       if(Flg!=null){
                        if(Flg.equals("YES")){ 
                        
                        stat2 = conn.prepareStatement("select oaadK1,oaconm||'-'||TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM CIADDR_m4off WHERE OACONO=111 AND OAADTH=4 and OAECAR=?");
                        stat2.setString(1, WHOUSE.trim());
                        result2 = stat2.executeQuery();
                        while (result2.next()) 
                         { 
                             unitlist.add( new ScrapSaleListBean(result2.getString("oaadK1"),result2.getString("UADDR")));
                                
                          }   
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                        result2.close();
                      }
                          }
            }
            if (stat2 != null) {
                stat2.close();
            }
            if (result2 != null) {
                result2.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

                    LIC_NO = null;
                    LIC_AMT=null;
                    SALE_AMT = null;
                    PORT_CODE = null;
                    PRCT = null;
                    listdata = null;
                    TOTAL_LIC_AMT = 0;
                    TOTAL_SALE_AMT = 0;
                    INVOICEDATE = null;
                    
                    HSN_CODE_L=null;
                    HSN_CODE_PER_L=null;
                    

        return "new";
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

    public List getShowList() {
        return showList;
    }

    public void setShowList(List showList) {
        this.showList = showList;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public List getShowDetail() {
        return ShowDetail;
    }

    public void setShowDetail(List ShowDetail) {
        this.ShowDetail = ShowDetail;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List getLinkno() {
        return linkno;
    }

    public void setLinkno(List linkno) {
        this.linkno = linkno;
    }

    public List getYer() {
        return yer;
    }

    public void setYer(List yer) {
        this.yer = yer;
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

    public List<String> getBUYER_L() {
        return BUYER_L;
    }

    public void setBUYER_L(List<String> BUYER_L) {
        this.BUYER_L = BUYER_L;
    }

    public String getWHO_S() {
        return WHO_S;
    }

    public void setWHO_S(String WHO_S) {
        this.WHO_S = WHO_S;
    }

    public String getINVOICE_S() {
        return INVOICE_S;
    }

    public void setINVOICE_S(String INVOICE_S) {
        this.INVOICE_S = INVOICE_S;
    }

    public String getBUYER_S() {
        return BUYER_S;
    }

    public void setBUYER_S(String BUYER_S) {
        this.BUYER_S = BUYER_S;
    }

    public String getWAREHOUSENO() {
        return WAREHOUSENO;
    }

    public void setWAREHOUSENO(String WAREHOUSENO) {
        this.WAREHOUSENO = WAREHOUSENO;
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

    public List getBuyeraddlist() {
        return buyeraddlist;
    }

    public void setBuyeraddlist(List buyeraddlist) {
        this.buyeraddlist = buyeraddlist;
    }

    public List getTaxtypelist() {
        return Taxtypelist;
    }

    public void setTaxtypelist(List Taxtypelist) {
        this.Taxtypelist = Taxtypelist;
    }

    public List getWhouselist() {
        return whouselist;
    }

    public void setWhouselist(List whouselist) {
        this.whouselist = whouselist;
    }

    public List getLicSearch() {
        return LicSearch;
    }

    public void setLicSearch(List LicSearch) {
        this.LicSearch = LicSearch;
    }

    public List getITEM_DESC_L() {
        return ITEM_DESC_L;
    }

    public void setITEM_DESC_L(List ITEM_DESC_L) {
        this.ITEM_DESC_L = ITEM_DESC_L;
    }

    public String getFINAL_P() {
        return FINAL_P;
    }

    public void setFINAL_P(String FINAL_P) {
        this.FINAL_P = FINAL_P;
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

    public String getSEARCH_DATE1() {
        return SEARCH_DATE1;
    }

    public void setSEARCH_DATE1(String SEARCH_DATE1) {
        this.SEARCH_DATE1 = SEARCH_DATE1;
    }

    public String getSEARCH_DATE2() {
        return SEARCH_DATE2;
    }

    public void setSEARCH_DATE2(String SEARCH_DATE2) {
        this.SEARCH_DATE2 = SEARCH_DATE2;
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

    public String getFINAL_CHK() {
        return FINAL_CHK;
    }

    public void setFINAL_CHK(String FINAL_CHK) {
        this.FINAL_CHK = FINAL_CHK;
    }

    public String getREM() {
        return REM;
    }

    public void setREM(String REM) {
        this.REM = REM;
    }

    public String getTAX_TYPE() {
        return TAX_TYPE;
    }

    public void setTAX_TYPE(String TAX_TYPE) {
        this.TAX_TYPE = TAX_TYPE;
    }

    public String getTAX_PER() {
        return TAX_PER;
    }

    public void setTAX_PER(String TAX_PER) {
        this.TAX_PER = TAX_PER;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public List getLIC_NO() {
        return LIC_NO;
    }

    public void setLIC_NO(List LIC_NO) {
        this.LIC_NO = LIC_NO;
    }

    public List getSALE_AMT() {
        return SALE_AMT;
    }

    public void setSALE_AMT(List SALE_AMT) {
        this.SALE_AMT = SALE_AMT;
    }

    public List getPORT_CODE() {
        return PORT_CODE;
    }

    public void setPORT_CODE(List PORT_CODE) {
        this.PORT_CODE = PORT_CODE;
    }

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public void setTOTAL_LIC_AMT(float TOTAL_LIC_AMT) {
        this.TOTAL_LIC_AMT = TOTAL_LIC_AMT;
    }

    public double getTOTAL_LIC_AMT() {
        return TOTAL_LIC_AMT;
    }

    public void setTOTAL_LIC_AMT(double TOTAL_LIC_AMT) {
        this.TOTAL_LIC_AMT = TOTAL_LIC_AMT;
    }

    public void setTOTAL_SALE_AMT(float TOTAL_SALE_AMT) {
        this.TOTAL_SALE_AMT = TOTAL_SALE_AMT;
    }

    public double getTOTAL_SALE_AMT() {
        return TOTAL_SALE_AMT;
    }

    public void setTOTAL_SALE_AMT(double TOTAL_SALE_AMT) {
        this.TOTAL_SALE_AMT = TOTAL_SALE_AMT;
    }

    public List getPRCT() {
        return PRCT;
    }

    public void setPRCT(List PRCT) {
        this.PRCT = PRCT;
    }

    public String getLICDATE() {
        return LICDATE;
    }

    public void setLICDATE(String LICDATE) {
        this.LICDATE = LICDATE;
    }

    public String getLICPORT() {
        return LICPORT;
    }

    public void setLICPORT(String LICPORT) {
        this.LICPORT = LICPORT;
    }

    public String getLICAMT() {
        return LICAMT;
    }

    public void setLICAMT(String LICAMT) {
        this.LICAMT = LICAMT;
    }

    public String getLICPRCT() {
        return LICPRCT;
    }

    public void setLICPRCT(String LICPRCT) {
        this.LICPRCT = LICPRCT;
    }

    public String getSALEAMT() {
        return SALEAMT;
    }

    public void setSALEAMT(String SALEAMT) {
        this.SALEAMT = SALEAMT;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public String getPARAA() {
        return PARAA;
    }

    public void setPARAA(String PARAA) {
        this.PARAA = PARAA;
    }

    public List getLIC_AMT() {
        return LIC_AMT;
    }

    public void setLIC_AMT(List LIC_AMT) {
        this.LIC_AMT = LIC_AMT;
    }

    public String getFACTORYCODE() {
        return FACTORYCODE;
    }

    public void setFACTORYCODE(String FACTORYCODE) {
        this.FACTORYCODE = FACTORYCODE;
    }

    public String getFACTCODE() {
        return FACTCODE;
    }

    public void setFACTCODE(String FACTCODE) {
        this.FACTCODE = FACTCODE;
    }

    public String getSTATE_DESC() {
        return STATE_DESC;
    }

    public void setSTATE_DESC(String STATE_DESC) {
        this.STATE_DESC = STATE_DESC;
    }

    public List getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List unitlist) {
        this.unitlist = unitlist;
    }

    public List getHSN_CODE_L() {
        return HSN_CODE_L;
    }

    public void setHSN_CODE_L(List HSN_CODE_L) {
        this.HSN_CODE_L = HSN_CODE_L;
    }

    public List getHSN_CODE_PER_L() {
        return HSN_CODE_PER_L;
    }

    public void setHSN_CODE_PER_L(List HSN_CODE_PER_L) {
        this.HSN_CODE_PER_L = HSN_CODE_PER_L;
    }

    public String getWHOUSE() {
        return WHOUSE;
    }

    public void setWHOUSE(String WHOUSE) {
        this.WHOUSE = WHOUSE;
    }

    public String getF_CODE() {
        return F_CODE;
    }

    public void setF_CODE(String F_CODE) {
        this.F_CODE = F_CODE;
    }

    public String getFlg() {
        return Flg;
    }

    public void setFlg(String Flg) {
        this.Flg = Flg;
    }
    
    
}
 