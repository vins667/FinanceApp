package shahi.Action.MvxExp.BuyerInv;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;

import java.sql.*;
import java.util.*;
import java.util.Date;
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
import shahi.Action.MvxExp.BuyerInv.Beans.DecathlneBean;
import shahi.Action.MvxExp.BuyerInv.Beans.SearchListBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.MvxExp.Reports.PRE.bean.BEListBean;
import shahi.Action.MvxExp.Reports.PRE.bean.InvLineBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionMovexBi;
import shahi.Action.database.connectiondb2;


public class DecathlaneAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

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
    private String SALEGLCODE;
    private String BUYER_DESC;
    private String BUYER_ADDR;
    private String FINAL_P;
    private String BUYR_ADD;
    private String FINAL_CHK;
    private List buyeraddlist = new ArrayList();
    private List Taxtypelist = new ArrayList();
    private List whouselist = new ArrayList();
    private List companylist = new ArrayList();
    private List unitlist = new ArrayList();
    private List paymentmethodlist = new ArrayList();
    private List taxgllist = new ArrayList();
    private List TCSgllist = new ArrayList();
    private List uomlist = new ArrayList();
    private List pchlist = new ArrayList();
    private List unitList = new ArrayList<UnitBean>();
    private List ITEM_DESC_L = new ArrayList();
    private List PCH_L = new ArrayList();
    private List QNTY_L = new ArrayList();
    private List UOM_L = new ArrayList();
    private List RATE_L = new ArrayList();
    private List FOB_L = new ArrayList();
    private List TAX_TYPE_L = new ArrayList();
    private List TAX_PER_L = new ArrayList();
    private List TAX_AMT_L = new ArrayList();
    private List TAX_GL_L = new ArrayList();
    private List TCS_L = new ArrayList();
    private List TCSGL_CODE_L = new ArrayList();
    private List TCS_PER_L = new ArrayList();
    private List TCS_AMT_L = new ArrayList();
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
    private Double TOTQTY = 0.0;
    private Double TOTFOB = 0.0;
    private Double TOTINR = 0.0;
    private Double TOTGR = 0.0;
    private Double TOTDISC = 0.0;
    private String upd_allow = "NO";
    private String saveFlag;
    private List ShowDetail = new ArrayList();
    private List INVLST = new ArrayList();
    private String AWBNO;

    private String HAWBNO;
    private String CONTAINERNO;
    private String PRECRIGE;
    private String TOTALPCKGE;
    private String CBM;
    private String FRTTYPE;
    private String GROSSWT;
    private List BUYHOUSE = new ArrayList();
    private String FRTAMT;
    private String CHARABLEWT;
    private List BUYPERSON = new ArrayList();
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
    private List listdata = new ArrayList();
    private String SEARCH_CODE;
    private String PLANNO_N;
    private String TTO_DATE;
    private String TO_DATE;
    private String AWB_DATE;
    private String ETD_DATE;
    private String FIN_DATE;
    private String INV_QTY;
    private String SHIP_QTY;
    private String INVNO;
    private String PO_DESC;
    private String CRITERION_NO;
    private String MODEL_NO;
    private String TOTAL_CRTNS;
    private String GROSS_WT;
    private String NET_WEIGHT;
    private String SHIP_TO;
    private String UPDCODE;
    private String E_COMP;
    private String E_YEAR;
    private String E_BUYER;
    private String LOCATION;
    private String REPORTTYPE;
    
    private List SearchList=new ArrayList();
    
   
    
    

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
        LOCATION_CODE = "100";

        try {
                if (FLAG1.equals("Yes") && FLAG1.length() > 0) {
                stat2 = conn.prepareStatement("select a.EXCS_INV_NO,to_char(a.INV_DATE,'yyyy-MM-dd') INV_DATE,a.COMPANY,a.PLAN_NO,a.BUYER,a.BUYER_ADDR,to_char(a.TTO_DATE,'yyyy-MM-dd') TTO_DATE,to_char(a.T_O_DATE,'yyyy-MM-dd') T_O_DATE,to_char(a.ETD_DATE,'yyyy-MM-dd') ETD_DATE,to_char(a.AWBDATE,'yyyy-MM-dd') AWB_DATE,to_char(a.FIN_DATE,'yyyy-MM-dd') FIN_DATE,"
                                            + " A.INV_QTY,a.SHIP_QTY,a.BUYER_ADDR,a.year,a.LOCATION,b.REMARK,b.CREADIT_NO,b.SHIPMENT_TYPE,b.PACKAGE,b.GR_WT,b.NET_WT,b.NET_NET_WT,b.AGENT from ei_endors_mast   a left outer join ei_other_inv   b  on(a.year=b.year AND  a.company=b.company and a.INV_NO=b.INV_NO  ) where a.EXCS_INV_NO=?");
                stat2.setString(1, INVOICE_S.trim());
                result2 = stat2.executeQuery();
                if (result2.next()) {

                    INVOICENO = result2.getString("EXCS_INV_NO");
                    INVOICEDATE = result2.getString("INV_DATE");
                    COMPANY = result2.getString("COMPANY");
                    PLANNO_N = result2.getString("PLAN_NO");
                    BUYER = result2.getString("BUYER");
                    BUYER_DESC = result2.getString("BUYER_ADDR");
                    TTO_DATE = result2.getString("TTO_DATE");
                    TO_DATE = result2.getString("T_O_DATE");
                    ETD_DATE = result2.getString("ETD_DATE");
                    AWB_DATE = result2.getString("AWB_DATE");
                    FIN_DATE = result2.getString("FIN_DATE");
                    INV_QTY = result2.getString("INV_QTY");
                    SHIP_QTY = result2.getString("SHIP_QTY");
                    YEAR = result2.getString("year");
                    LOCATION=result2.getString("LOCATION");
                    
                    PO_DESC = result2.getString("REMARK");
                    CRITERION_NO = result2.getString("CREADIT_NO");
                    MODEL_NO = result2.getString("SHIPMENT_TYPE");
                    TOTAL_CRTNS = result2.getString("PACKAGE");
                    GROSS_WT = result2.getString("GR_WT");
                    NET_WEIGHT = result2.getString("NET_WT");
                    CBM = result2.getString("NET_NET_WT");
                    SHIP_TO = result2.getString("AGENT");


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
                    SearchList.add(new SearchListBean(result.getString("VEND_CODE"), result.getString("VEND_NAME")+" - "+result.getString("VEND_CODE")));
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

    public String prints() throws FileNotFoundException, JRException, IOException {

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
             List DecathlneBeanlist=new ArrayList();
             
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(notify) notify,agent,fwd_code,hs_code,TRIM(manuf_code) manuf_code,"
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,trim(a.cons_addr) cons_addr,LOADING_port,trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,place,fwd_custom,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,a.lcno,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) tax_cal_per,"
                            +" a.transport_cost,facility,CTNS from ei_endors_mast a  where  a.excs_inv_no=? ");
                    stat1.setString(1, INVOICE_S);
                     
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next()) 
                    {  
                        DecathlneBean bean = new DecathlneBean();
       
                        
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
                       
                       
             
                          List InvLineList = new ArrayList();
                             stat2 = conn.prepareStatement("select p.unit,p.description||' '||hscode1 description,exp_rate,sum(p.qty_endors) endqty,sum(p.qty_kgs) kgsqty,nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) tprice," +
                                                        " nvl(p.price_fc,0) price_fc,nvl(price_misc,0) price_misc,nvl(transport_cost,0) transport_cost," +
                                                        " (sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0))) fob_fc, " +
                                                        "(sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0)))* exp_rate fob_inr," +
                                                        " sum((p.qty_endors * p.price_fc)-nvl(gr_decl_amt,0))*exp_rate inr_dbk,sum(p.qty_endors*nvl(price_misc,0))*exp_rate inr_misc,sum(gr_decl_amt) gr_decl "+
                                                        " from ei_endors_dtls p ,ei_endors_mast p3,ei_exchange_rate_mast ex  where p.year = p3.year and p.type = p3.type and p.company = p3.company and  p.inv_no=p3.inv_no and "+
                                                        " p.currency=ex.currency and p3.inv_date between begin_date and end_date and p.type = 'E' and p.year=? and p.company=? and p.inv_no=?  "+
                                                        " group by p.unit,p.description||' '||hscode1,exp_rate,nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) ,p.price_fc,p.price_misc ,nvl(transport_cost,0) order by 1,2");
                          
                        stat2.setString(1, result1.getString("year"));
                        stat2.setString(2, result1.getString("company"));
                        stat2.setString(3, result1.getString("inv_no")); 
                   
                       result2 = stat2.executeQuery(); 
                  
                       double trcost=0.0;double iqty=0.0; double tqty=0; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double tinr=0.0; double inrconv=0.0;
                        double tgrdecl=0.0; double netfc=0.0;  double up=0.0; double tax_a =0.0; double tax_v=0.0; double comm_amt=0.0;
                        BigDecimal g1 = new BigDecimal("0.00");double excise_d=0.0;double tnetwt=0.0;double tot_inv=0.0;double rate_inv=0.0;
                        
                       while (result2.next()) {  
                           
                            chkuom=result2.getString("unit");
                            if (chkuom.equals(result2.getString("unit")))
                                    { tqty=tqty+result2.getDouble("endqty");}
                            else{tqty=0;}
                            trcost=trcost+result2.getDouble("endqty")*result2.getDouble("transport_cost");
                            tfob=tfob+result2.getDouble("FOB_FC");
                            tnetwt=tnetwt+result2.getDouble("kgsqty");
                            tdbkinr=tdbkinr+result2.getDouble("inr_dbk");
                            
                            tmiscinr=tmiscinr+result2.getDouble("inr_misc");
                            tinr=tinr+result2.getDouble("fob_inr");
                       
                            g1=g1.add(result2.getBigDecimal("gr_decl"));
                             
                            tgrdecl=tgrdecl+result2.getDouble("gr_decl");
                            inrconv=result2.getDouble("exp_rate");  
                            InvLineList.add(new InvLineBean(result2.getString("unit"),result2.getString("description"),result2.getString("endqty"),result2.getDouble("kgsqty"), result2.getDouble("tprice"),roundTwoDecimals(result2.getDouble("FOB_FC")) ));
                        }   
                        
                        netfc=roundTwoDecimals(tfob-tgrdecl);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTFOB(roundTwoDecimals(tfob)); 
                      
                        up=netfc*result1.getDouble("upcharge_per")/100;
                            
                        tax_v=((up+tfob)-tgrdecl)*result1.getDouble("tax_cal_per")/100;
                        
                        
                        
                        tax_a=tax_v*result1.getDouble("tax_percent")/100;
                        tot_inv=roundTwoDecimals(tfob+up+excise_d+tax_a);
                        bean.setUP_AMT(roundTwoDecimals(up));
                        bean.setTAXABLE_VALUE(roundTwoDecimals(tax_v));
                        bean.setTAX_AMT(roundTwoDecimals(tax_a));
                        System.out.println("ok-3");        
                        bean.setTNETWT(tnetwt);   
                        bean.setTINVQTY(tqty);          
                        bean.setTINR(tinr);
                        bean.setTGRDECL(tgrdecl);       
                        bean.setGRPER(roundTwoDecimals(tgrdecl/tfob*100));
                        bean.setINV_FC(netfc); 
                        bean.setTOTAL_INV(tot_inv);
                       
                        comm_amt=netfc* result1.getDouble("comm_per")/100;
                        bean.setCOMM_AMT(roundTwoDecimals(comm_amt));
                    
                      if (result1.getString("crncy_code").equals("INR"))
                      {netfc=roundTwoDecimals(tax_a+tax_v+trcost);
                      tot_inv=roundTwoDecimals(tfob+up+excise_d+tax_a);
                      } 
                        String decimal_print="";
                        stat=conn.prepareStatement("select nvl(decimal_print,'Cents') dp from ei_currency_mast where currency=? ");
                        stat.setString(1,result1.getString("crncy_code").trim());
                        result=stat.executeQuery();
                        if (result.next())
                        {decimal_print=result.getString("dp");
                        }
                        
                        String wordfc="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,tot_inv);
                        stat.setDouble(2,tot_inv);
                        stat.setDouble(3,tot_inv); 
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
                        stat = conn.prepareStatement("select distinct trim(pre_print_no) pre_print_no from ei_endors_dtls where pre_print_no is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mbpo=mbpo+result.getString("pre_print_no")+",";
                           //BPOList.add(new GetListBean(result.getString("pre_print_no"),result.getString("pre_print_no")));      
                        }
                        if (mbpo!=null){
                        mbpo=mbpo.substring(0, mbpo.length()-1);}
                       
                         BPOList.add(new GetListBean(mbpo,mbpo));
                         bean.setBPOLIST(BPOList);
                         bean.setPO_NO(mbpo);
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
                           
                                
                        PreparedStatement stat3 = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                        stat3.setString(1, result1.getString("BUYER"));
                        stat3.setString(2, result1.getString("BUYER_ADDR"));
                        ResultSet result3 = stat3.executeQuery();
                        if (result3.next() == true) {
                          bean.setBuyer_name(result3.getString("opcunm"));
                          bean.setBuyer_address(result3.getString("opadd"));
                         }   
               
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER").trim());
                       stat.setString(2, result1.getString("CONS_ADDR"));
          System.out.println("buyer"+result1.getString("BUYER"));
          System.out.println("Addr"+result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) { 
                           System.out.println("sanjeev----");  
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
                       
                       String mshipto="";
                       stat = conn.prepareStatement("select NET_WT,GR_WT,NET_NET_WT,CREADIT_NO,SHIPMENT_TYPE,agent from ei_other_inv   where EXCS_INV_NO=?");
                       stat.setString(1,INVOICE_S);
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setNTWT(result.getString("NET_WT"));
                           bean.setGROSWT(result.getString("GR_WT"));
                           bean.setCBMVAL(result.getString("NET_NET_WT"));
                           bean.setCREATIONNO(result.getString("CREADIT_NO"));
                           bean.setMODLNO(result.getString("SHIPMENT_TYPE"));
                           mshipto=result.getString("agent");
                        }
                       if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                          stat=conn.prepareStatement("select vend_code unit_code,vend_name vname,rtrim(vend_addr)||' '||rtrim(state)||' '||rtrim(city) vaddr,pin_code from pr_vend_mast where vend_code=? ");
                          stat.setString(1,mshipto);
                          result=stat.executeQuery();
                          if (result.next())
                                  {
                                      bean.setSHIPTO_NAME(result.getString("vname"));
                                      bean.setSHIPTO_DESC(result.getString("vaddr"));
                                  }
                           
                        if(result!=null){
                           result.close();
                       }
                       if(stat!=null){
                           stat.close();
                       }
                       
                        
                        DecathlneBeanlist.add(bean);
        
                    }
                    

                if (INVOICE_S!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/BuyerInv");
                    Map param1 = new HashMap();

                   
                    InputStream input;
                    param1.put("SUBREPORT_DIR", path);

                     
                     input =new FileInputStream(new File(path+"/DecathlaneInvoice.jrxml"));
                    
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);

 
                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(DecathlneBeanlist));

                     
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=DecathlaneInvoice.pdf"); //attachment- use open new window and inline- use open in same window
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "DecathlaneInvoice.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=DecathlaneInvoice.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("ExcisePrePeriodAction" + e.toString());
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

        return "prnt";
    }

    public String update1() throws SQLException {
        Connection conn = null;
        PreparedStatement stat2 = null;
        ResultSet result2 = null;
        PreparedStatement stat4 = null;
        ResultSet result4 = null;
        PreparedStatement stat5 = null;
        ResultSet result5 = null;
        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);



        } catch (Exception e) {
            System.out.println(e.toString());
        } // end catch   


        SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
        SimpleDateFormat simpledateformate2 = new SimpleDateFormat("dd/mm/yyyy");
        String date1 = null;
        String date2 = null;

        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        LOCATION_CODE = "100";

        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0;
            String newno = "";
            String FINYR = "";
            String vex = "";
            String t1 = "";
            String t2 = "";
            String yr=null;
            String comp=null;
            String invn=null;
            String byr=null;
            String byradd=null;
            String loc=null;
            
            stat5 = conn.prepareStatement("select year,COMPANY,BUYER,BUYER_ADDR,LOCATION,INV_NO from ei_endors_mast   where EXCS_INV_NO=?");
            stat5.setString(1, INVOICE_S);
            result5 = stat5.executeQuery();
            if(result5.next()){
                
                yr=result5.getString("year");
                comp=result5.getString("COMPANY");
                invn=result5.getString("INV_NO");
                byr=result5.getString("BUYER");
                byradd=result5.getString("BUYER_ADDR");
                loc=result5.getString("LOCATION");
            }
            
            
            stat2 = conn.prepareStatement("select * from ei_other_inv   where EXCS_INV_NO=?");
            stat2.setString(1, INVOICE_S);
            result2 = stat2.executeQuery();
            if (result2.next()) {
                stat4 = conn.prepareStatement("UPDATE ei_other_inv   SET REMARK=?,CREADIT_NO=?,SHIPMENT_TYPE=?,PACKAGE=?,GR_Wt=?,Net_Wt=?,"
                        + "Net_net_wt=?,Agent=upper(?) WHERE EXCS_INV_NO=?");
                stat4.setString(1, PO_DESC);
                stat4.setString(2, CRITERION_NO.trim());
                stat4.setString(3, MODEL_NO.trim());
                stat4.setString(4, TOTAL_CRTNS.trim());
                stat4.setString(5, GROSS_WT.trim());
                stat4.setString(6, NET_WEIGHT.trim());
                stat4.setString(7, CBM);
                stat4.setString(8, SHIP_TO);
                stat4.setString(9, INVOICE_S);
                x=stat4.executeUpdate();
                if(x>0){
                    ++x;
                    conn.commit();
                }
 
            }
            else{ 
                
                stat4 = conn.prepareStatement("insert into ei_other_inv  (YEAR,COMPANY,INV_NO,BUYER,BUYER_ADDR,EXCS_INV_NO,LOCATION,REMARK,CREADIT_NO,SHIPMENT_TYPE,PACKAGE,GR_Wt,Net_Wt,Net_net_wt,Agent,TDATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(sysdate,'dd-MON-yy'))");

                stat4.setString(1, yr);
                stat4.setString(2, comp);
                stat4.setString(3, invn);
                stat4.setString(4, byr);
                stat4.setString(5, byradd);
                stat4.setString(6, INVOICE_S);
                stat4.setString(7, loc);
                stat4.setString(8, PO_DESC);
                stat4.setString(9, CRITERION_NO.trim());
                stat4.setString(10, MODEL_NO.trim());
                stat4.setString(11, TOTAL_CRTNS.trim());
                stat4.setString(12, GROSS_WT.trim());
                stat4.setString(13, NET_WEIGHT.trim());
                stat4.setString(14, CBM);
                stat4.setString(15, SHIP_TO);
                y= stat4.executeUpdate();
                if(y>0){
                    ++y;
                    conn.commit();
                }


            }


            if (x > 0) {
                addActionMessage("Record Updated succcessfully ");
                 
                PO_DESC=null;
                CRITERION_NO=null;
                MODEL_NO=null;
                TOTAL_CRTNS=null;
                GROSS_WT=null;
                NET_WEIGHT=null;
                CBM=null;
                SHIP_TO=null;
            }
            if (y > 0) {
                addActionMessage("Record Inserted succcessfully ");
                
                PO_DESC=null;
                CRITERION_NO=null;
                MODEL_NO=null;
                TOTAL_CRTNS=null;
                GROSS_WT=null;
                NET_WEIGHT=null;
                CBM=null;
                SHIP_TO=null;
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
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        finally{
            if(conn!=null){
              conn.close();
            }
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

    public String getPO_DESC() {
        return PO_DESC;
    }

    public void setPO_DESC(String PO_DESC) {
        this.PO_DESC = PO_DESC;
    }

    public String getCRITERION_NO() {
        return CRITERION_NO;
    }

    public void setCRITERION_NO(String CRITERION_NO) {
        this.CRITERION_NO = CRITERION_NO;
    }

    public String getMODEL_NO() {
        return MODEL_NO;
    }

    public void setMODEL_NO(String MODEL_NO) {
        this.MODEL_NO = MODEL_NO;
    }

    public String getTOTAL_CRTNS() {
        return TOTAL_CRTNS;
    }

    public void setTOTAL_CRTNS(String TOTAL_CRTNS) {
        this.TOTAL_CRTNS = TOTAL_CRTNS;
    }

    public String getGROSS_WT() {
        return GROSS_WT;
    }

    public void setGROSS_WT(String GROSS_WT) {
        this.GROSS_WT = GROSS_WT;
    }

    public String getNET_WEIGHT() {
        return NET_WEIGHT;
    }

    public void setNET_WEIGHT(String NET_WEIGHT) {
        this.NET_WEIGHT = NET_WEIGHT;
    }

    public String getSHIP_TO() {
        return SHIP_TO;
    }

    public void setSHIP_TO(String SHIP_TO) {
        this.SHIP_TO = SHIP_TO;
    }

    public String getUPDCODE() {
        return UPDCODE;
    }

    public void setUPDCODE(String UPDCODE) {
        this.UPDCODE = UPDCODE;
    }

    public String getE_COMP() {
        return E_COMP;
    }

    public void setE_COMP(String E_COMP) {
        this.E_COMP = E_COMP;
    }

    public String getE_YEAR() {
        return E_YEAR;
    }

    public void setE_YEAR(String E_YEAR) {
        this.E_YEAR = E_YEAR;
    }

    public String getE_BUYER() {
        return E_BUYER;
    }

    public void setE_BUYER(String E_BUYER) {
        this.E_BUYER = E_BUYER;
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

    public String getAWB_DATE() {
        return AWB_DATE;
    }

    public void setAWB_DATE(String AWB_DATE) {
        this.AWB_DATE = AWB_DATE;
    }

    public String getETD_DATE() {
        return ETD_DATE;
    }

    public void setETD_DATE(String ETD_DATE) {
        this.ETD_DATE = ETD_DATE;
    }

    public String getFIN_DATE() {
        return FIN_DATE;
    }

    public void setFIN_DATE(String FIN_DATE) {
        this.FIN_DATE = FIN_DATE;
    }

    public List getSearchList() {
        return SearchList;
    }

    public void setSearchList(List SearchList) {
        this.SearchList = SearchList;
    }
     
    
}