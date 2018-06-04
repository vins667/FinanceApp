package shahi.Action.MvxExp.PRE;

  
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
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
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import shahi.Action.MvxExp.PRE.Beans.BuyersearchBean;
import shahi.Action.MvxExp.PRE.Beans.ScrapSaleInvoiceBean;
import shahi.Action.MvxExp.PRE.Beans.ScrapSaleInvoiceEditBean;
import shahi.Action.MvxExp.PRE.Beans.ScrapSaleListBean;
import shahi.Action.MvxExp.PRE.Beans.TaxtypeBean; 
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.database.connectiondb2;
  

public class ScrapSaleInvoiceAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    
     private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    
    private String WHO_S;
    private String INVOICE_S;
    private String F_UNIT_S;
    private String BUYER_S; 
    
    private String WAREHOUSENO;
    private String YEAR;
    private String COMPANY;
    private String INV_NO;
    private String INVOICENO;
    private String INVOICEDATE;
    private String FACTORYCODE;
    private String BUYER;
    private String CURRENCYCODE;
    private String PAYMENTMETHOD;
    private String REM;
    private String SALEGLCODE;
    private String BUYER_DESC;
    private String BUYER_ADDR;
    private double CTN_TOTAL;
    private double FOB_TOTAL; 
    private String FINAL_P;
    private String BUYR_ADD;
    private String FINAL_CHK;
    private List buyeraddlist=new ArrayList();
    private List Taxtypelist=new ArrayList();
    private List whouselist=new ArrayList();
    private List companylist=new ArrayList();
    private List unitlist=new ArrayList();
    
    private List paymentmethodlist=new ArrayList();
    
    private List taxgllist=new ArrayList();
    private List TCSgllist= new ArrayList();
    private List uomlist=new ArrayList();
    private List pchlist=new ArrayList(); 
    private List unitList=new ArrayList<UnitBean>();
    private List ITEM_DESC_L=new ArrayList();
    private List PCH_L=new ArrayList();
    private List QNTY_L=new ArrayList();
    private List UOM_L=new ArrayList();
    private List RATE_L=new ArrayList();
    private List FOB_L=new ArrayList();
    
    private List TAX_TYPE_L=new ArrayList();
    private List TAX_PER_L=new ArrayList();
    private List TAX_AMT_L=new ArrayList();
    private List TAX_GL_L=new ArrayList();
    private List TCS_L=new ArrayList();
    private List TCSGL_CODE_L=new ArrayList();
    private List TCS_PER_L=new ArrayList();
    private List TCS_AMT_L=new ArrayList();
    private List TAX_CODE;
    private List TAX_CODE2;
    
    private String indexname;
    
    private String FLAG1;
    private String FLAG2;
   
    
    
    
    private String currentdate;
    private String viewFlag;
    private List showList;
    private String searchdate;
    private String searchterm;
    private String searchawb;
    private String aausrid;
   
    private List INVOICE_NO;

    private Double TOTQTY=0.0;
    private Double TOTFOB=0.0;
    private Double TOTINR=0.0;
    private Double TOTGR=0.0;
    private Double TOTDISC=0.0;
    private String upd_allow="NO";
   
    private String saveFlag;
   
  
    private List ShowDetail=new ArrayList();
    private List INVLST=new ArrayList();
    private String AWBNO;
    private Date AWBDATE;
    private String HAWBNO;
    private String CONTAINERNO;
    private String PRECRIGE;
    private String TOTALPCKGE;
    private String CBM;
    private String FRTTYPE;
    private String GROSSWT;
    private List BUYHOUSE=new ArrayList();
    private String FRTAMT;
    private String CHARABLEWT;
    private List BUYPERSON=new ArrayList();
         
    private String PORT;
    private String DISCHARGE;
 
    private String DESTNCNTRY;
    private String TODATE;
    private String CHA;
    private String FORWARDER;
    
    private String PORTCLR;
    private ByteArrayInputStream inputStream;
    private String INVOICE_NO_S;
   
 
  
     private List<String> BUYER_L;
    
          
    private String SEARCH_DATE1;
    private String SEARCH_DATE2;
    private List linkno=new ArrayList();
    private List yer=new ArrayList();
    private List listdata=new ArrayList();
    
    private String SEARCH_CODE;
    
   
 
    
    @Override
    public String execute() {
         
     try{
            
        }
        catch (Exception e)
        { System.out.println(e.toString());
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
                 PreparedStatement stat2=null;
                 PreparedStatement stat1=null;
                 ResultSet result2=null;
                 ResultSet result1=null;
            try {
                conn = new connection().getConnection();
                connDB2 = new connectiondb2().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
            
            
             try{
                        Date sysdate = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                         currentdate = sdf.format(sysdate);
                 
                         stat2= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                         stat2.setString(1,usrid);
                         result2=stat2.executeQuery();
                         while (result2.next())
                         {  LOCATION_CODE=result2.getString("location_code");
                          } 
                         
                         stat2 = conn.prepareStatement("select distinct WAREHOUSE from EI_SCRAP_MAST where loct_code=? order by 1");
                          System.out.println("loct "+LOCATION_CODE);   
                         stat2.setString(1,LOCATION_CODE);
                         result2 = stat2.executeQuery();
                         while (result2.next()) 
                         { 
                             whouselist.add(result2.getString("WAREHOUSE"));
                         } 
                       
                 
                    String q1 = " and 1=1";
                        if (WHO_S != null && WHO_S.length()> 0) {
                            q1 += " AND WAREHOUSE ='"+WHO_S+"'";             
                        }
                        if (INVOICE_S != null && INVOICE_S.length()> 0) {               
                            q1 += " and EXCS_INV_NO='"+ INVOICE_S + "'";
                        }
                       
                        if (BUYER_S != null && BUYER_S.length()>0) {
                            q1 += " and BUYER like '"+ BUYER_S.toUpperCase().trim()+"%'";
                        }
                          
                             
                   String PAYMATHOD_DESC="";
                    if(SEARCH_DATE1!=null &&  SEARCH_DATE1.length()>0){
                              
                   stat2 = conn.prepareStatement("select WAREHOUSE,COMPANY,EXCS_INV_NO,to_char(INV_DATE,'dd/mm/yyyy') INV_DATE,UNIT_CODE,BUYER,"
                           + "CRNCY_CODE,PAYMENT_METHOD,PAYMENT_TERM,SALE_GLCODE,to_char(FINAL_PRINT,'yyyy-MM-dd') FINAL_PRINT,BUYER_ADDR,REM from  EI_SCRAP_MAST where t_mod='LGM4' and trunc(INV_DATE) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')  "+q1+"order by 1");
                             stat2.setString(1,SEARCH_DATE1.substring(0,10));
                             stat2.setString(2,SEARCH_DATE2.substring(0,10));
                   result2 = stat2.executeQuery();
                      while (result2.next())  
                         {   
                             
                               UnitBean  bn=new  PreInvoiceDao().getCsytabBeanByName(result2.getString("PAYMENT_METHOD"),"PYCD");
                               PAYMATHOD_DESC=bn.getUNIT_DESC();
                               
                                stat1 = connDB2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                                stat1.setString(1, result2.getString("BUYER").trim());
                                stat1.setString(2, result2.getString("buyer_addr").trim());
                                 result1 = stat1.executeQuery();
                                if (result1.next() == true) {
                                  BUYER_DESC = result1.getString("opcunm");

                                 }   
                                 BUYER_DESC=result2.getString("BUYER")+"-"+BUYER_DESC;
                             ShowDetail.add(new ScrapSaleInvoiceBean(result2.getString("WAREHOUSE"),result2.getString("COMPANY"),result2.getString("EXCS_INV_NO"),
                                      result2.getString("INV_DATE"), result2.getString("UNIT_CODE"),BUYER_DESC,
                                      result2.getString("CRNCY_CODE"),PAYMATHOD_DESC,null, 
                                      result2.getString("SALE_GLCODE"),result2.getString("FINAL_PRINT"),result2.getString("BUYER_ADDR")));
                          
                             BUYER_DESC=null;
                             
                          } 
                        }
                    
                  } 
            
          catch(Exception e){
              System.out.println(e.toString());
          }
             finally
             {
         try {
             conn.close();
             connDB2.close();
               if(stat2!=null){
                  stat2.close();
                 }
                 if(result2!=null){
                    result2.close();
                  }
                   if(stat1!=null){
                          stat1.close();
                      }
                      if(result1!=null){
                          result1.close();
                      }
         } catch (SQLException ex) {
             Logger.getLogger(ScrapSaleInvoiceAction.class.getName()).log(Level.SEVERE, null, ex);
         }
             
             }
             
             
               
             
      
      
        return SUCCESS;
 }   
   
    public String prints() throws FileNotFoundException, JRException, IOException{
         
        Connection conn = null;
        Connection connDB2 = null;
                 PreparedStatement stat2=null;
                 PreparedStatement stat1=null;
                 ResultSet result2=null;
                 ResultSet result1=null;
            try {
                conn = new connection().getConnection();
                connDB2 = new connectiondb2().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
        try{
                if (INVOICENO!=null) {
                             String BuyerName=""; String BuyerAddress=""; String BuyerTin="";
                             String UnitName=""; String UnitAddress=""; String UCST=""; String UTIN="";
                             double netfc=0.0;
                             
                        stat1 = conn.prepareStatement("select rpad(buyer,10,' ') BUYER,trim(BUYER_ADDR) buyer_addr,unit_code,SUM(ITEM_FOB+NVL(TAX_AMT,0)+NVL(TAX_AMT2,0)) FOBAMT from  EI_SCRAP_MAST A,EI_SCRAP_DTLS B "+
                                                            "where A.YEAR=B.YEAR AND A.COMPANY=B.COMPANY AND A.INV_NO=B.INV_NO AND A.t_mod='LGM4' and excs_inv_no=? GROUP BY rpad(buyer,10,' '),trim(BUYER_ADDR),unit_code ");
                             stat1.setString(1,INVOICENO);
                              result1 = stat1.executeQuery();
                               if (result1.next())  
                                  {   
                                      
                                      
                                        netfc=result1.getDouble("FOBAMT");
                                       
                                       stat2 = connDB2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd,OPVRNO  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                                        stat2.setString(1, result1.getString("BUYER"));
                                        stat2.setString(2, result1.getString("BUYER_ADDR").trim());
                                         result2 = stat2.executeQuery();
                                         if (result2.next() == true) {
                                            BuyerName = result2.getString("opcunm");
                                            BuyerAddress=result2.getString("opadd");
                                            BuyerTin=result2.getString("OPVRNO");
                                         }   
                                       
                                       //  stat2 = connDB2.prepareStatement("select oaadK1 u_code,oaconm u_name,TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3)||' '||TRIM(OAADR4) U_ADDR ,aRXCSN u_cst,aRXLSN u_tin,trim(arxlcn) u_cin FROM m3fdbprd.CIADDR   a ,cinfdbprd.cinddr b " +
                                       //                                   " WHERE OACONO=111 AND OAADTH=4 and oacono=arcono and oaadth=aRADTH and oaadk1=aradk1 and oaadK1=?   ");
                                       
                                         stat2=conn.prepareStatement("select  OAADK2,OACONM,trim(OAADR1)||' '||trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,OATAXC, OAECAR ,aRXCSN cst,aRXLSN tin  from seplweb.ciaddr_VIEW115 a,seplweb.xinddr_view115 b where oacono=111 and oaadth=1 and oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and oaadk3=? ");
                                         stat2.setString(1, result1.getString("unit_code").trim());
                                        
                                         result2 = stat2.executeQuery();
                                         if (result2.next() == true) {
                                            UnitName = result2.getString("OACONM");
                                            UnitAddress=result2.getString("addr");
                                            UCST=result2.getString("cst");
                                            UTIN=result2.getString("tin");
                                         }  
                                  }
             
                        String wordfc="";        
                        stat1=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat1.setDouble(1,netfc);
                        stat1.setDouble(2,netfc);
                        stat1.setDouble(3,netfc); 
                        result1=stat1.executeQuery();
                        if (result1.next())
                        {   wordfc=result1.getString("aa");
                            
                                if (result1.getString("aa1")!=null)
                                {wordfc=wordfc+"  And  "+result1.getString("aa1")+" paise  Only.";
                                }else{wordfc=wordfc+" Only.";}
                            
                        }
                                
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/PRE/");
                    Map param1 = new HashMap();
                    param1.put("conndb2", connDB2);
                    param1.put("INVOICENO", INVOICENO);
                    param1.put("BuyerName", BuyerName);
                    param1.put("BuyerAddress", BuyerAddress);
                    param1.put("BuyerTin", BuyerTin);
                    param1.put("UnitName", UnitName);
                    param1.put("UnitAddress", UnitAddress);
                    param1.put("UCST", UCST);
                    param1.put("UTIN", UTIN); 
                    param1.put("WORDAMT",wordfc);
                    InputStream input;  
                    param1.put("SUBREPORT_DIR", path);
                        input =new FileInputStream(new File(path+"ScrapSaleInvoice.jrxml"));
                   
                      
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, conn);

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                    
            
                response.setHeader("Content-Disposition", "attachment;filename=ScrapSaleInvoice.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
            
         }
        }
          catch(Exception e){
              System.out.println(e.toString());
          }
        finally{
            try {
                conn.close();
                  if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
                        if(stat1!=null){
                          stat1.close();
                      }
                      if(result1!=null){
                          result1.close();
                      }
            } catch (SQLException ex) {
                Logger.getLogger(ScrapSaleInvoiceAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "prnt";
    }
   
    public String edit() throws SQLException{
        
                 Connection conn = null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
            
            PreparedStatement stat = null;
            ResultSet result = null;
            
               Map session = ActionContext.getContext().getSession();
                        String LOCATION_CODE = (String) session.get("sessLocationCode");
                        String usrid = (String) session.get("sessUserId");
               
            try{
                         stat2= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                         stat2.setString(1,usrid);
                         result2=stat2.executeQuery();
                         while (result2.next())
                         {  LOCATION_CODE=result2.getString("location_code");
                          } 
              
                  stat2=conn.prepareStatement("select year,company,inv_no,to_char(inv_date,'dd/mm/yyyy') inv_date,warehouse,trim(unit_code) unit_code,buyer,buyer_addr,port_code,crncy_code,trim(payment_term) payment_term,trim(payment_method) payment_method,sale_glcode,to_char(final_print,'dd/mm/yyyy') final_print,text_id,BUYER_ADDR,REM from ei_scrap_mast where EXCS_INV_NO=?");
                  stat2.setString(1,INVOICENO);
                  result2=stat2.executeQuery();
                while(result2.next()){
                
                      WAREHOUSENO=result2.getString("warehouse");
                      INVOICEDATE=result2.getString("inv_date");
                      FINAL_P=result2.getString("final_print");
                      FACTORYCODE=result2.getString("unit_code");
                      CURRENCYCODE=result2.getString("crncy_code");
                      PAYMENTMETHOD=result2.getString("payment_method");
                      REM=result2.getString("rem");
                      SALEGLCODE=result2.getString("sale_glcode");
                      BUYER=result2.getString("buyer");
                      BUYER_ADDR=result2.getString("BUYER_ADDR");
                      YEAR=result2.getString("YEAR");
                      COMPANY=result2.getString("COMPANY");
                      INV_NO=result2.getString("INV_NO");
                      
                      stat = conn.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                      stat.setString(1, BUYER.trim());
                      stat.setString(2, BUYER_ADDR.trim());
                       result = stat.executeQuery();
                      if (result.next() == true) {
                        BUYER_DESC = result.getString("opcunm");
                         
                       }   
                      
                }
                 stat2=conn.prepareStatement("select ITEM_DESC,trim(PCH) pch,ITEM_QTY,trim(UOM) uom,ITEM_RATE,ITEM_FOB,TAX_DESC,TAX_PERCENT,TAX_AMT,TAX_GLCODE,TAX_DESC2 tcs,TAX_GLCODE2 tcsglcode,TAX_PERCENT2,TAX_AMT2,a.INV_NO,tax_code,tax_code2 from EI_SCRAP_dtls  a,EI_SCRAP_MAST b where a.INV_NO=b.INV_NO and a.year=b.year and a.company=b.company and EXCS_INV_NO=? ");
                 stat2.setString(1,INVOICENO);
                result2=stat2.executeQuery();
                while(result2.next()){
                    CTN_TOTAL=CTN_TOTAL+result2.getDouble("ITEM_QTY");
                    FOB_TOTAL=FOB_TOTAL+result2.getDouble("ITEM_FOB");
                   listdata.add(new ScrapSaleInvoiceEditBean(result2.getString("ITEM_DESC"),result2.getString("PCH"),result2.getString("ITEM_QTY"),
                                     result2.getString("UOM"),result2.getString("ITEM_RATE"),result2.getString("ITEM_FOB"),result2.getString("TAX_DESC"),
                                     result2.getString("TAX_PERCENT"),result2.getString("TAX_AMT"),result2.getString("TAX_GLCODE"),result2.getString("tcs"),
                                     result2.getString("tcsglcode"),result2.getString("TAX_PERCENT2"),result2.getString("TAX_AMT2"),result2.getString("INV_NO"),result2.getString("TAX_CODE"),result2.getString("TAX_CODE2")));
                }
                
                
                 }catch(Exception e){
                System.out.println(e.toString());
            }
             try{
                  
                 
                        stat2 = conn.prepareStatement("select cttx15, cttx40, trim(ctstky) ctstky from csytab_m4off  where ctcono=111 and ctstco='PYCD' order by 2");
                        result2 = stat2.executeQuery();
                        while (result2.next()) 
                         { 
                             
                             paymentmethodlist.add(new ScrapSaleListBean(result2.getString("ctstky"),result2.getString("cttx40")));
                                
                          }   
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                   //   BUYER_DESC=BUYER_DESC;
                      
                      if (LOCATION_CODE.equals("100"))
                      {
                       taxgllist.add(new ScrapSaleListBean("26000","26000"));
                       TCSgllist.add(new ScrapSaleListBean("26310","26310"));
                      }else{
                        taxgllist.add(new ScrapSaleListBean("26050","26050"));
                        TCSgllist.add(new ScrapSaleListBean("26320","26320"));
                      }
                       
                          
                           
                       stat2 = conn.prepareStatement("select trim(oaadK1) oaadk1,oaconm||'-'||TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM seplweb.CIADDR_VIEW115 WHERE OACONO=111 AND OAADTH=4");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                             //String detial=result2.getString("oaconm");
                             
                             unitlist.add( new ScrapSaleListBean(result2.getString("oaadK1"),result2.getString("UADDR")));
                                
                          }   
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
            
                      
                      stat2 = conn.prepareStatement("SELECT CTTX15,trim(CTSTKY) ctstky  FROM csytab_m4off where CTCONO=111 AND ctstco='BUAR'");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                             pchlist.add(new ScrapSaleListBean(result2.getString("CTSTKY"),result2.getString("CTTX15")));
                                
                          } 
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
                      
                      stat2 = conn.prepareStatement("select cttx15 cttx40, trim(ctstky) ctstky from csytab_m4off where ctcono=111 and ctstco='UNIT' order by 1");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                             uomlist.add(new ScrapSaleListBean(result2.getString("ctstky"),result2.getString("cttx40")));
                                
                          } 
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
                       }  
                      
                      }catch(Exception e){
                System.out.println(e.toString());
            }
              finally {
            if (conn != null) {
                conn.close();
            }
        }
             
             
            ITEM_DESC_L=null;
        PCH_L=null;
        QNTY_L=null;
        UOM_L=null;
        RATE_L=null;
        FOB_L=null;
        TAX_TYPE_L=null;
        TAX_PER_L=null;
        TAX_AMT_L=null;
        TAX_GL_L=null;
        TCS_L=null;
        TCSGL_CODE_L=null;
        TCS_PER_L=null;
        TCS_AMT_L=null;
       
            
        
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

        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try { 
            conndb2 = new connectiondb2().getConnection();

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) { 
                    buyeraddlist.add(new BuyersearchBean(result.getString("OpCUNO"), result.getString("OpCUNM"),result.getString("OpADID"),result.getString("opadd"),"")); 
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
                double percent=0.0;

                if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                        stat = consepl.prepareStatement("select uscrd0 tax_desc,case when uscrme='1' then uscrfa*100 else uscram end taxpercent,uscrid tax_code from  m3fdbprd.odchrg where uscono=111  and uscucd='INR' and uscrme=0 and uscrid like ?");
                        stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                        result = stat.executeQuery();
                        while (result.next()) {

                            percent=result.getDouble("taxpercent");

                            Taxtypelist.add(new TaxtypeBean(result.getString("tax_code"),result.getString("tax_desc"),result.getString("taxpercent")));
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
     
      public String tcssearch() throws SQLException {

        Connection consepl = null;
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;

        try {
            consepl = new connectiondb2().getConnection();
            conn = new connection().getConnection();
                double percent=0.0;

                if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                        stat = consepl.prepareStatement("select uscrd0 tax_desc,case when uscrme='1' then uscrfa*100 else uscram end taxpercent,uscrid tax_code from  m3fdbprd.odchrg where uscono=111  and uscucd='INR' and uscrme=0 and uscrid like ?");
                        stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                        result = stat.executeQuery();
                        while (result.next()) {

                            percent=result.getDouble("taxpercent");

                            Taxtypelist.add(new TaxtypeBean(result.getString("tax_code"),result.getString("tax_desc"),result.getString("taxpercent")));
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

        return "tcssrch";
    }
     
     public String ajaxTax() throws SQLException, ParseException, UnsupportedEncodingException
     {
           Connection conn = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch   
          String taxc =null;
          
          SimpleDateFormat simpledateformate1 = new SimpleDateFormat("yyyymmdd");
             SimpleDateFormat simpledateformate2 = new SimpleDateFormat("dd-MMM-yy");
             String date1=null;
             String date2=null;
          
          try{
              
             
              
          PreparedStatement stat2 = conn.prepareStatement("select uscrd0 tax_desc,decode(uscrme,1,nvl(uscrfa,0)*100,nvl(uscram,0)) taxpercent,uscrid tax_code from  odchrg_view where uscono=111  and uscucd='INR' and uscrme=0");
          ResultSet result2 = stat2.executeQuery();
          if(result2.next())
          {
              taxc=result2.getString("tax_desc")+"#"+result2.getString("taxpercent");
          }
       
       if ((taxc != null) && (!taxc.equals(""))) {
         this.inputStream = new ByteArrayInputStream(taxc.getBytes("UTF-8"));
       } else {
         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
       }
       
          }
          catch(Exception e){
             System.out.println(e.toString());
          }
          finally{
              
            conn.close();
          }
          
       return "success";
    }

     
    public String newentry(){
        Connection conn = null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
                     Map session = ActionContext.getContext().getSession();
                        String LOCATION_CODE = (String) session.get("sessLocationCode");
                        String usrid = (String) session.get("sessUserId");
      
          
            try{
                  
                         stat2= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                         stat2.setString(1,usrid);
                         result2=stat2.executeQuery();
                         while (result2.next())
                         {  LOCATION_CODE=result2.getString("location_code");
                          } 
                
                    //for buying house list       
                    stat2 = conn.prepareStatement("select distinct WAREHOUSE from EI_SCRAP_MAST where loct_code=? order by 1");
                    stat2.setString(1,LOCATION_CODE);
                     result2 = stat2.executeQuery();
                      while (result2.next()) 
                         { 
                             whouselist.add(result2.getString("WAREHOUSE"));
                                
                          } 
                      if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
                      
                                      
                        stat2 = conn.prepareStatement("select oaadK1,oaconm||'-'||TRIM(OAADR1)||' '||TRIM(OAADR2)||' '||TRIM(OAADR3) UADDR FROM CIADDR_m4off WHERE OACONO=111 AND OAADTH=4");
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
                       
                        stat2 = conn.prepareStatement("select cttx15 cttx40, ctstky from csytab_m4off  where ctcono=111 and ctstco='PYCD' order by 1");
                        result2 = stat2.executeQuery();
                        while (result2.next()) 
                         { 
                             paymentmethodlist.add(new ScrapSaleListBean(result2.getString("ctstky"),result2.getString("cttx40")));
                                
                          }  
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
                      
                        if (LOCATION_CODE.equals("100"))
                      {
                       taxgllist.add(new ScrapSaleListBean("26000","26000"));
                       TCSgllist.add(new ScrapSaleListBean("26310","26310"));
                      }else{
                        taxgllist.add(new ScrapSaleListBean("26050","26050"));
                        TCSgllist.add(new ScrapSaleListBean("26320","26320"));
                      }
                       
                       
                     
                      
                       stat2 = conn.prepareStatement("SELECT CTTX15,CTSTKY  FROM csytab_m4off where CTCONO=111 AND ctstco='BUAR'");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                             pchlist.add(new ScrapSaleListBean(result2.getString("CTSTKY"),result2.getString("CTTX15")));
                                
                          } 
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
                      
                      stat2 = conn.prepareStatement("select cttx15 cttx40, ctstky from csytab_m4off  where ctcono=111 and ctstco='UNIT' order by 1");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                             uomlist.add(new ScrapSaleListBean(result2.getString("ctstky"),result2.getString("cttx40")));
                                
                          } 
                        if(stat2!=null){
                          stat2.close();
                      }
                      if(result2!=null){
                          result2.close();
                      }
                      
                       
                      }catch(Exception e){
                System.out.println(e.toString());
            }
        
            finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ScrapSaleInvoiceAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        CTN_TOTAL=0;
        FOB_TOTAL=0;
        ITEM_DESC_L=null;
        PCH_L=null;
        QNTY_L=null;
        UOM_L=null;
        RATE_L=null;
        FOB_L=null;
        TAX_TYPE_L=null;
        TAX_PER_L=null;
        TAX_AMT_L=null;
        TAX_GL_L=null;
        TCS_L=null;
        TCSGL_CODE_L=null;
        TCS_PER_L=null;
        TCS_AMT_L=null;
        
        
      
        return "new";
    }

    

    
    public String update1() throws SQLException{
                 Connection conn = null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
                 PreparedStatement stat4=null;
                 ResultSet result4=null;
                  PreparedStatement stat5=null;
                 ResultSet result5=null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
                       
    
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch   
            
      
             SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
             SimpleDateFormat simpledateformate2 = new SimpleDateFormat("dd/mm/yyyy");
             String date1=null;
             String date2=null;
            
                   Map session = ActionContext.getContext().getSession();
                        String LOCATION_CODE = (String) session.get("sessLocationCode");
                        String usrid = (String) session.get("sessUserId");
                   
                       
                 
            
            
                try{
                    conn = new connection().getConnection();
                    conn.setAutoCommit(false);
                      
                          stat2= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                         stat2.setString(1,usrid);
                         result2=stat2.executeQuery();
                         while (result2.next())
                         {  LOCATION_CODE=result2.getString("location_code");
                          } 
                    
                       int x=0,y=0,z=0;
                       String newno="";String FINYR="";String vex=""; String t1=""; String t2="";
                        double taxamt=0.0; double taxamt2=0.0; double taxper=0.0; double taxper2=0.0; double  fobamt=0.0;
                         stat2=conn.prepareStatement("select * from  EI_SCRAP_MAST where EXCS_INV_NO=?");
                        stat2.setString(1, INVOICENO);
                        result2=stat2.executeQuery();
                        if(result2.next()){
                            
                            
                                stat4 = conn.prepareStatement("UPDATE  EI_SCRAP_MAST  SET UNIT_CODE=?,BUYER=?,BUYER_ADDR=?,"
                                        + " CRNCY_CODE=?,PAYMENT_METHOD=?,SALE_GLCODE=?,FINAL_PRINT=decode(?,'Y',sysdate,null),rem=? WHERE EXCS_INV_NO=?");
                                
                                stat4.setString(1, FACTORYCODE.trim());
                                stat4.setString(2, BUYER.trim());
                                stat4.setString(3,BUYER_ADDR.trim());
                                stat4.setString(4, CURRENCYCODE.trim());
                                stat4.setString(5, PAYMENTMETHOD.trim());
                                 stat4.setString(6, SALEGLCODE);
                                stat4.setString(7,FINAL_CHK);
                                stat4.setString(8,REM);
                                stat4.setString(9, INVOICENO);
                                stat4.executeUpdate();
                            
                          
                                stat5=conn.prepareStatement("delete from  ei_scrap_DTLS where year=? and company=? and inv_no=?");
                                stat5.setString(1,YEAR);
                                stat5.setString(2,COMPANY);
                                stat5.setString(3,INV_NO);
                                stat5.executeUpdate();
                                
                         if(ITEM_DESC_L!=null && ITEM_DESC_L.size()>0)
                        {    
                          for(int i=0;i<ITEM_DESC_L.size();i++){
                              vex=ITEM_DESC_L.get(i).toString().trim();
                            if(vex!=null && vex.length()>2)
                                         {    
                                                t1=TAX_PER_L.get(i).toString().trim();
                                                if (t1!=null && t1.length()>0)
                                                {
                                                   taxper= Double.parseDouble(t1);
                                                   
                                                }
                                                t2=TCS_PER_L.get(i).toString().trim();
                                                if (t2!=null && t2.length()>0)
                                                {
                                                   taxper2= Double.parseDouble(t2);
                                                }
                                             
                                                   fobamt=Double.parseDouble(QNTY_L.get(i).toString())*Double.parseDouble(RATE_L.get(i).toString());
                                                   if (taxper>0){taxamt=(fobamt*taxper)/100;}
                                                   if (taxper2>0){taxamt2=((fobamt+taxamt)*taxper2)/100;}   
                                               
                                           stat2 = conn.prepareStatement("insert into  EI_SCRAP_dtls(ITEM_DESC,PCH,ITEM_QTY,UOM,ITEM_RATE,ITEM_FOB,TAX_DESC,TAX_PERCENT,"
                                                       + "TAX_AMT,TAX_GLCODE,TAX_DESC2,TAX_GLCODE2,TAX_PERCENT2,TAX_AMT2,YEAR,COMPANY,INV_NO,TAX_CODE,TAX_CODE2,SEH_USER,TDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)");
                                             stat2.setString(1, ITEM_DESC_L.get(i).toString().toUpperCase());
                                            stat2.setString(2, PCH_L.get(i).toString());
                                            stat2.setString(3, QNTY_L.get(i).toString());
                                            stat2.setString(4, UOM_L.get(i).toString());
                                            stat2.setString(5, RATE_L.get(i).toString()); 
                                            stat2.setDouble(6, fobamt);
                                            stat2.setString(7, TAX_TYPE_L.get(i).toString());
                                            stat2.setDouble(8, taxper);
                                            stat2.setDouble(9, taxamt);
                                            stat2.setString(10, TAX_GL_L.get(i).toString());
                                            stat2.setString(11, TCS_L.get(i).toString());
                                            stat2.setString(12, TCSGL_CODE_L.get(i).toString());
                                            stat2.setDouble(13, taxper2);
                                            stat2.setDouble(14, taxamt2);
                                            stat2.setString(15,YEAR);
                                            stat2.setString(16,COMPANY);
                                            stat2.setString(17,INV_NO);
                                            stat2.setString(18, TAX_CODE.get(i).toString());
                                            stat2.setString(19, TAX_CODE2.get(i).toString());
                                            stat2.setString(20,aausrid);
                                               
                                            y=stat2.executeUpdate();
                                            fobamt=0.0; taxamt=0.0; taxamt2=0.0; taxper=0.0; taxper2=0.0; t1=""; t2="";
                                            if(y>0){
                                                ++y;
                                                conn.commit();
                                            }  
                                     }
                                 } 
                             }
                                
                            
                        }
                        else{
                                        stat5=conn.prepareStatement("select vou_numb+1 vno ,lpad(nvl(vou_numb,0)+1,4,'0') newex4,lpad(nvl(vou_numb,0)+1,5,'0') newex5,SUBSTR(FIN_YEAR,3,2) FIN_YEAR from ei_vou_numb_mast where location_code=? and fin_year=pay_fin_year(sysdate) and  VOU_TYPE='EI' AND SUB_TYPE='INV' for update NOWAIT");
                                        stat5.setString(1,WAREHOUSENO);
                                         result5=stat5.executeQuery();
                                        if (result5.next() == true) { 
                                               newno=result5.getString("vno");
                                               FINYR=result5.getString("FIN_YEAR");
                                               INVOICENO=WAREHOUSENO.substring(0,2)+FINYR+result5.getString("newex5");
                                         } 
                                         result5.close();
                                         stat5.close();
                                     
                                           stat5=conn.prepareStatement("select nvl(vou_numb,0)+1 vno from ei_vou_numb_mast WHERE FIN_YEAR=pay_fin_year(sysdate) AND VOU_TYPE='EI' AND SUB_TYPE='111' for update NOWAIT ");
                                           result5=stat5.executeQuery();
                                           if (result5.next() == true) {
                                                INV_NO=result5.getString("vno"); 
                                             }
                                                if (newno==null ||  INV_NO==null)
                                                {      addActionMessage("Error in Invoice Series......");
                                                             return ERROR;
                                                } 
                                    
                                               result5.close();
                                               stat5.close();
                             
                                        stat4 = conn.prepareStatement("insert into  EI_SCRAP_MAST(WAREHOUSE,INV_NO,EXCS_INV_NO,company,year,INV_DATE,UNIT_CODE,BUYER,buyer_addr,CRNCY_CODE,PAYMENT_METHOD,"
                                                   + "PAYMENT_TERM,SALE_GLCODE,seh_user,LOCT_CODE,REM,tdate,t_mod) values(?,?,?,'111',pay_fin_year(sysdate),trunc(sysdate),?,?,?,?,?,'008',?,?,?,?,sysdate,'LGM4')");
                                        stat4.setString(1, WAREHOUSENO);
                                        stat4.setString(2, INV_NO);
                                        stat4.setString(3, INVOICENO);
                                        stat4.setString(4, FACTORYCODE.trim());
                                        stat4.setString(5, BUYER);
                                        stat4.setString(6, BUYER_ADDR);
                                        stat4.setString(7, CURRENCYCODE);
                                        stat4.setString(8, PAYMENTMETHOD.trim());
                                        stat4.setString(9, SALEGLCODE);
                                        stat4.setString(10, usrid);
                                        stat4.setString(11, LOCATION_CODE);
                                        stat4.setString(12, REM);
                                        z=stat4.executeUpdate();
                                         
                                if(ITEM_DESC_L!=null && ITEM_DESC_L.size()>0)
                                {    
                                  for(int i=0;i<ITEM_DESC_L.size();i++){
                                      vex=ITEM_DESC_L.get(i).toString().trim();
                                    if(vex!=null && vex.length()>2)
                                                 {   
                                                  t1=TAX_PER_L.get(i).toString().trim();
                                                if (t1!=null && t1.length()>0)
                                                {
                                                   taxper= Double.parseDouble(t1);
                                                }
                                                t2=TCS_PER_L.get(i).toString().trim();
                                                if (t2!=null && t2.length()>0)
                                                {
                                                   taxper2= Double.parseDouble(t2);
                                                }
                                                           
                                                   fobamt=Double.parseDouble(QNTY_L.get(i).toString())*Double.parseDouble(RATE_L.get(i).toString());
                                                   if (taxper>0){taxamt=(fobamt*taxper)/100;}
                                                   if (taxper2>0){taxamt2=((fobamt+taxamt)*taxper2)/100;}   
                                              
                                                   stat2 = conn.prepareStatement("insert into  EI_SCRAP_dtls(ITEM_DESC,PCH,ITEM_QTY,UOM,ITEM_RATE,ITEM_FOB,TAX_DESC,TAX_PERCENT,"
                                                               + "TAX_AMT,TAX_GLCODE,TAX_DESC2,TAX_GLCODE2,TAX_PERCENT2,TAX_AMT2,INV_NO,tax_code,tax_code2,SEH_USER,TDATE, COMPANY,year) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,'111',pay_fin_year(sysdate))");
                                                     stat2.setString(1, ITEM_DESC_L.get(i).toString().toUpperCase());
                                                    stat2.setString(2, PCH_L.get(i).toString());
                                                    stat2.setString(3, QNTY_L.get(i).toString());
                                                    stat2.setString(4, UOM_L.get(i).toString());
                                                    stat2.setString(5, RATE_L.get(i).toString());
                                                    stat2.setDouble(6, fobamt); 
                                                    stat2.setString(7, TAX_TYPE_L.get(i).toString());
                                                    stat2.setDouble(8, taxper);
                                                    stat2.setDouble(9, taxamt);
                                                    stat2.setString(10, TAX_GL_L.get(i).toString());
                                                    stat2.setString(11, TCS_L.get(i).toString());
                                                    stat2.setString(12, TCSGL_CODE_L.get(i).toString());
                                                    stat2.setDouble(13, taxper2);
                                                    stat2.setDouble(14, taxamt2);
                                                    stat2.setString(15,INV_NO);
                                                    stat2.setString(16, TAX_CODE.get(i).toString());
                                                    stat2.setString(17, TAX_CODE2.get(i).toString());
                                                    stat2.setString(18,usrid);
                                                    y=stat2.executeUpdate();
                                                    
                                                
                                             }
                                         } 
                                     }  
                                                     fobamt=0.0; taxamt=0.0; taxamt2=0.0; taxper=0.0; taxper2=0.0;
                                                  if(y>0){     
                                                    stat5=conn.prepareStatement("update ei_vou_numb_mast set vou_numb=?  where location_code=? and fin_year=pay_fin_year(sysdate) and  VOU_TYPE='EI' AND SUB_TYPE='INV' ");
                                                    stat5.setString(1,newno);
                                                    stat5.setString(2,WAREHOUSENO);
                                                     stat5.executeUpdate();
                                     
                                                    stat5=conn.prepareStatement("update ei_vou_numb_mast set vou_numb=?  where fin_year=pay_fin_year(sysdate) and VOU_TYPE='EI' AND SUB_TYPE='111' ");
                                                    stat5.setString(1,INV_NO);
                                                    stat5.executeUpdate();
                                                      
                                                        conn.commit();
                                                    }    
                                        
                                        
                           }
                        
                         
    
                         if(x>0){
                               addActionMessage("Record Updated succcessfully "+INVOICENO);
                            }
                            if(y>0){
                                addActionMessage("Record Inserted succcessfully "+INVOICENO); 
                                ITEM_DESC_L=null;
                                PCH_L=null;
                                QNTY_L=null;
                                UOM_L=null;
                                RATE_L=null;
                                FOB_L=null;
                                TAX_TYPE_L=null;
                                TAX_PER_L=null;
                                TAX_AMT_L=null;
                                TAX_GL_L=null;
                                TCS_L=null;
                                TCSGL_CODE_L=null;
                                TCS_PER_L=null;
                                TCS_AMT_L=null;
                                BUYER=null;
                                INVOICENO=null;
                                BUYER_DESC=null;
                                BUYER_ADDR=null;
                                SALEGLCODE=null;
                            }
                        
                        
                        
                        if(stat2!=null){
                            stat2.close();
                        }
                        if(stat4!=null){
                            stat4.close();
                        }
                        if(result2!=null){
                            result2.close();
                       }
                       if(result4!=null){
                            result4.close();
                       }
                       if(result5!=null){
                            result5.close();
                       }
                       if(stat5!=null){
                            stat5.close();
                        }
                }      
                    
          
                catch(Exception e){
                   System.out.println(e.toString());
                }
                
                
                
                try{
                    
           
                           
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
             
             finally{
                  conn.close();
                }
            
            return "updte";
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

    public String getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(String viewFlag) {
        this.viewFlag = viewFlag;
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

    
    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getSearchdate() {
        return searchdate;
    }

    public void setSearchdate(String searchdate) {
        this.searchdate = searchdate;
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String getSearchawb() {
        return searchawb;
    }

    public void setSearchawb(String searchawb) {
        this.searchawb = searchawb;
    }

  

    public List getINVOICE_NO() {
        return INVOICE_NO;
    }

    public void setINVOICE_NO(List INVOICE_NO) {
        this.INVOICE_NO = INVOICE_NO;
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

   

    public Double getTOTGR() {
        return TOTGR;
    }

    public void setTOTGR(Double TOTGR) {
        this.TOTGR = TOTGR;
    }

    public Double getTOTDISC() {
        return TOTDISC;
    }

    public void setTOTDISC(Double TOTDISC) {
        this.TOTDISC = TOTDISC;
    }

    public String getUpd_allow() {
        return upd_allow;
    }

    public void setUpd_allow(String upd_allow) {
        this.upd_allow = upd_allow;
    }

    public Double getTOTINR() {
        return TOTINR;
    }

    public void setTOTINR(Double TOTINR) {
        this.TOTINR = TOTINR;
    }

    public String getAWBNO() {
        return AWBNO;
    }

    public void setAWBNO(String AWBNO) {
        this.AWBNO = AWBNO;
    }

    public Date getAWBDATE() {
        return AWBDATE;
    }

    public void setAWBDATE(Date AWBDATE) {
        this.AWBDATE = AWBDATE;
    }

    public String getHAWBNO() {
        return HAWBNO;
    }

    public void setHAWBNO(String HAWBNO) {
        this.HAWBNO = HAWBNO;
    }

    public String getCONTAINERNO() {
        return CONTAINERNO;
    }

    public void setCONTAINERNO(String CONTAINERNO) {
        this.CONTAINERNO = CONTAINERNO;
    }

    

    public String getTOTALPCKGE() {
        return TOTALPCKGE;
    }

    public void setTOTALPCKGE(String TOTALPCKGE) {
        this.TOTALPCKGE = TOTALPCKGE;
    }

    public String getFRTTYPE() {
        return FRTTYPE;
    }

    public void setFRTTYPE(String FRTTYPE) {
        this.FRTTYPE = FRTTYPE;
    }

    public String getGROSSWT() {
        return GROSSWT;
    }

    public void setGROSSWT(String GROSSWT) {
        this.GROSSWT = GROSSWT;
    }

   

    public String getFRTAMT() {
        return FRTAMT;
    }

    public void setFRTAMT(String FRTAMT) {
        this.FRTAMT = FRTAMT;
    }

    public String getCHARABLEWT() {
        return CHARABLEWT;
    }

    public void setCHARABLEWT(String CHARABLEWT) {
        this.CHARABLEWT = CHARABLEWT;
    }

    public List getBUYHOUSE() {
        return BUYHOUSE;
    }

    public void setBUYHOUSE(List BUYHOUSE) {
        this.BUYHOUSE = BUYHOUSE;
    }

    public List getBUYPERSON() {
        return BUYPERSON;
    }

    public void setBUYPERSON(List BUYPERSON) {
        this.BUYPERSON = BUYPERSON;
    }

  
    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getDISCHARGE() {
        return DISCHARGE;
    }

    public void setDISCHARGE(String DISCHARGE) {
        this.DISCHARGE = DISCHARGE;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getDESTNCNTRY() {
        return DESTNCNTRY;
    }

    public void setDESTNCNTRY(String DESTNCNTRY) {
        this.DESTNCNTRY = DESTNCNTRY;
    }

    public String getTODATE() {
        return TODATE;
    }

    public void setTODATE(String TODATE) {
        this.TODATE = TODATE;
    }

    public String getCHA() {
        return CHA;
    }

    public void setCHA(String CHA) {
        this.CHA = CHA;
    }

    public String getFORWARDER() {
        return FORWARDER;
    }

    public void setFORWARDER(String FORWARDER) {
        this.FORWARDER = FORWARDER;
    }

     

    public String getPORTCLR() {
        return PORTCLR;
    }

    public void setPORTCLR(String PORTCLR) {
        this.PORTCLR = PORTCLR;
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

    public String getINVOICE_NO_S() {
        return INVOICE_NO_S;
    }

    public void setINVOICE_NO_S(String INVOICE_NO_S) {
        this.INVOICE_NO_S = INVOICE_NO_S;
    }

    public String getPRECRIGE() {
        return PRECRIGE;
    }

    public void setPRECRIGE(String PRECRIGE) {
        this.PRECRIGE = PRECRIGE;
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

    public List getINVLST() {
        return INVLST;
    }

    public void setINVLST(List INVLST) {
        this.INVLST = INVLST;
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

    public String getF_UNIT_S() {
        return F_UNIT_S;
    }

    public void setF_UNIT_S(String F_UNIT_S) {
        this.F_UNIT_S = F_UNIT_S;
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

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
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

   
   

    public String getFACTORYCODE() {
        return FACTORYCODE;
    }

    public void setFACTORYCODE(String FACTORYCODE) {
        this.FACTORYCODE = FACTORYCODE;
    }

   
    public String getCURRENCYCODE() {
        return CURRENCYCODE;
    }

    public void setCURRENCYCODE(String CURRENCYCODE) {
        this.CURRENCYCODE = CURRENCYCODE;
    }

    public String getPAYMENTMETHOD() {
        return PAYMENTMETHOD;
    }

    public void setPAYMENTMETHOD(String PAYMENTMETHOD) {
        this.PAYMENTMETHOD = PAYMENTMETHOD;
    }

    

    public String getSALEGLCODE() {
        return SALEGLCODE;
    }

    public void setSALEGLCODE(String SALEGLCODE) {
        this.SALEGLCODE = SALEGLCODE;
    }
  
    

    public String getBUYR_ADD() {
        return BUYR_ADD;
    }

    public void setBUYR_ADD(String BUYR_ADD) {
        this.BUYR_ADD = BUYR_ADD;
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

    public List getCompanylist() {
        return companylist;
    }

    public void setCompanylist(List companylist) {
        this.companylist = companylist;
    }

    public List getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List unitlist) {
        this.unitlist = unitlist;
    }

     

    public List getPaymentmethodlist() {
        return paymentmethodlist;
    }

    public void setPaymentmethodlist(List paymentmethodlist) {
        this.paymentmethodlist = paymentmethodlist;
    }

    

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }

    public List getTaxgllist() {
        return taxgllist;
    }

    public void setTaxgllist(List taxgllist) {
        this.taxgllist = taxgllist;
    }

    public List getITEM_DESC_L() {
        return ITEM_DESC_L;
    }

    public void setITEM_DESC_L(List ITEM_DESC_L) {
        this.ITEM_DESC_L = ITEM_DESC_L;
    }

    public List getPCH_L() {
        return PCH_L;
    }

    public void setPCH_L(List PCH_L) {
        this.PCH_L = PCH_L;
    }

    public List getQNTY_L() {
        return QNTY_L;
    }

    public void setQNTY_L(List QNTY_L) {
        this.QNTY_L = QNTY_L;
    }

    public List getUOM_L() {
        return UOM_L;
    }

    public void setUOM_L(List UOM_L) {
        this.UOM_L = UOM_L;
    }

    public List getRATE_L() {
        return RATE_L;
    }

    public void setRATE_L(List RATE_L) {
        this.RATE_L = RATE_L;
    }

    public List getFOB_L() {
        return FOB_L;
    }

    public void setFOB_L(List FOB_L) {
        this.FOB_L = FOB_L;
    }

    public List getTAX_TYPE_L() {
        return TAX_TYPE_L;
    }

    public void setTAX_TYPE_L(List TAX_TYPE_L) {
        this.TAX_TYPE_L = TAX_TYPE_L;
    }

    public List getTAX_PER_L() {
        return TAX_PER_L;
    }

    public void setTAX_PER_L(List TAX_PER_L) {
        this.TAX_PER_L = TAX_PER_L;
    }

    public List getTAX_AMT_L() {
        return TAX_AMT_L;
    }

    public void setTAX_AMT_L(List TAX_AMT_L) {
        this.TAX_AMT_L = TAX_AMT_L;
    }

    public List getTAX_GL_L() {
        return TAX_GL_L;
    }

    public void setTAX_GL_L(List TAX_GL_L) {
        this.TAX_GL_L = TAX_GL_L;
    }

    public List getTCS_L() {
        return TCS_L;
    }

    public void setTCS_L(List TCS_L) {
        this.TCS_L = TCS_L;
    }

    public List getTCSGL_CODE_L() {
        return TCSGL_CODE_L;
    }

    public void setTCSGL_CODE_L(List TCSGL_CODE_L) {
        this.TCSGL_CODE_L = TCSGL_CODE_L;
    }

    public List getTCS_PER_L() {
        return TCS_PER_L;
    }

    public void setTCS_PER_L(List TCS_PER_L) {
        this.TCS_PER_L = TCS_PER_L;
    }

    public List getTCS_AMT_L() {
        return TCS_AMT_L;
    }

    public void setTCS_AMT_L(List TCS_AMT_L) {
        this.TCS_AMT_L = TCS_AMT_L;
    }

    public String getFLAG1() {
        return FLAG1;
    }

    public void setFLAG1(String FLAG1) {
        this.FLAG1 = FLAG1;
    }

    public String getFINAL_P() {
        return FINAL_P;
    }

    public void setFINAL_P(String FINAL_P) {
        this.FINAL_P = FINAL_P;
    }

     

    public String getFLAG2() {
        return FLAG2;
    }

    public void setFLAG2(String FLAG2) {
        this.FLAG2 = FLAG2;
    }

    public List getUomlist() {
        return uomlist;
    }

    public void setUomlist(List uomlist) {
        this.uomlist = uomlist;
    }

    public List getPchlist() {
        return pchlist;
    }

    public void setPchlist(List pchlist) {
        this.pchlist = pchlist;
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

     
 
    public List getUnitList() {
        return unitList;
    }

    public void setUnitList(List unitList) {
        this.unitList = unitList;
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

    public List getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(List TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public List getTAX_CODE2() {
        return TAX_CODE2;
    }

    public void setTAX_CODE2(List TAX_CODE2) {
        this.TAX_CODE2 = TAX_CODE2;
    }

    public List getTCSgllist() {
        return TCSgllist;
    }

    public void setTCSgllist(List TCSgllist) {
        this.TCSgllist = TCSgllist;
    }

    public String getREM() {
        return REM;
    }

    public void setREM(String REM) {
        this.REM = REM;
    }

    public double getCTN_TOTAL() {
        return CTN_TOTAL;
    }

    public void setCTN_TOTAL(double CTN_TOTAL) {
        this.CTN_TOTAL = CTN_TOTAL;
    }

    public double getFOB_TOTAL() {
        return FOB_TOTAL;
    }

    public void setFOB_TOTAL(double FOB_TOTAL) {
        this.FOB_TOTAL = FOB_TOTAL;
    }

     

        
    
}
 