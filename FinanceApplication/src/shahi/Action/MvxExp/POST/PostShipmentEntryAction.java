package shahi.Action.MvxExp.POST;

  
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.POST.Beans.LicSearchBean;
import shahi.Action.MvxExp.POST.Beans.PostShipmentEntryViewBean;
import shahi.Action.MvxExp.POST.Beans.PostShipmentEntryViewEditBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.MvxExp.Reports.POST.bean.PostShipmntInvoiceBean;
import shahi.Action.MvxExp.Reports.PRE.bean.BEListBean;
import shahi.Action.MvxExp.Reports.POST.bean.InvLineBean1;
import shahi.Action.database.ConnectionSeplWeb;   
import shahi.Action.database.connectiondb2;
            

public class PostShipmentEntryAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    
      private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    
    private String currentdate;
    private String viewFlag;
    private String delFlag;
    private List showList;
    private String searchdate;
    private String searchterm;
    private String searchawb;
    private String aausrid;
    private String BH_HOUSE;
    private String BH_PERSON;
    private List INVOICE_NO;
    private List SHIP_DEL;
    private Double TOTQTY=0.0;
    private Double TOTFOB=0.0;
    private Double TOTINR=0.0;
    private Double TOTGR=0.0;
    private Double TOTDISC=0.0;
    private Double TOTGRWT=0.0;
    private int TOTPKGS;
    private String upd_allow="NO";
    private String AWB_ALLOW;
    private String SB_ALLOW;
    private String SALE_LOCK;
    private String saveFlag;
    private String indexname; 
  
    private List ShowDetail=new ArrayList();
    private List LCLIST=new ArrayList();
    private List INVLST=new ArrayList();
    private String YEAR;
    private String EI_YEAR;
    private String LINK_NO;
    private String AWBNO;
    private String FIN_DATE;
    private Date AWBDATE;
    private String AWB_DATE;
    private String HAWBNO;
    private String CONTAINERNO;
    private String PRECRIGE;
    private int TOTALPCKGE;
    private Double CBM;
  
    private Double GROSSWT;
    private String BUYING_HOUSE;
    private String BUYING_PERSON;
    private String INR_CONV;
    private List BUYHOUSE=new ArrayList();
    private String CHARABLEWT;
    private List CRNCY_CODE;
    private List BUYPERSON=new ArrayList();
    private String DBKRECV="NO";  
    private String PORT;
    private String DISCHARGE;
    private String BUYER;
    private String DESTNCNTRY;
    private String TODATE;
    private String CHA;
    private String FORWARDER;
    private String INVDATE;
    private String PORTCLR;
    private ByteArrayInputStream inputStream;
    private String INVOICE_NO_S;
    private Date AWBDATE_AJ;
    private Date BILL_DATE_AJ;
    
    private List<String> BILL_NO;
    private List<Date> BILL_DATE;
    private List<Date> LET_EXP_DT;
    private String INVOICE_NO_INV;
    private List<String> DBK;
    private List<String> GROSS_WT;
    private List<String> PKGS;
    private List<String> AMT;
    private List<String> LLC_NO;
    
    private List<String> INR_C;
    private List<String> DBK_C;
    private List<String> MOS;
    private List<String> FOBAMT;
    private List<String> GR_DISC;
    
    private List<String> QTY;
    private List<String> PORT_L;
    private List<String> BUYER_L;
    private List<String> CHA_L;
    private List<String> DISCHARGE_L;
    private List<String> DESTNCNTRY_L;
    private List<String> FORWARDER_L;
    private List INV_NO;  
    private List XSNODY; 
    private List XSTEPY;
    private List INV_YEAR;
    private List linkno=new ArrayList();
    private List yer=new ArrayList();
    private List listdata=new ArrayList();
    
    private String SEARCH_CODE;
    private String MAXDATE;
   
 
    
    @Override
    public String execute() throws SQLException {
         
     try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
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
                 PreparedStatement stat1=null;
                 PreparedStatement stat2=null;
                 ResultSet result1=null;
                 ResultSet result2=null;
                 
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
            
             SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
             String date1=null;
            if (AWBDATE != null ) {
                
              date1=  simpledateformate1.format(AWBDATE);
            }
            
             try{
                 
                    stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                    stat1.setString(1,usrid);
                    result1=stat1.executeQuery();
                    while (result1.next())
                    {LOCATION_CODE=result1.getString("location_code");
                    } 
                    
                    String q1 = " ";
                     int x=0;
                     if(delFlag!=null && delFlag.equals("YES") && SHIP_DEL!=null && SHIP_DEL.size()>0)
                       {
                         for (int i = 0; i < SHIP_DEL.size(); i++){
                              String[] arr = SHIP_DEL.get(i).toString().split("--"); 
                                 
                              stat2=conn.prepareStatement("delete from ei_dbk_mast where (shp_bill_no,shp_bill_date) in (select shp_bill_no,shp_bill_date from ei_shipment_dtls where year=? and link_no=?) ");
                              stat2.setString(1,arr[0].trim());
                              stat2.setString(2,arr[1].trim());
                              x=stat2.executeUpdate(); 
                              if(stat2!=null){stat2.close();} 
                             
                              stat2=conn.prepareStatement("delete from ei_shipment_dtls where year=? and link_no=? ");
                              stat2.setString(1,arr[0].trim());
                              stat2.setString(2,arr[1].trim());
                              x=stat2.executeUpdate();  
                              if(stat2!=null){stat2.close();} 
                              
                              stat2=conn.prepareStatement("delete from ei_shipment_mast where year=? and link_no=? ");
                              stat2.setString(1,arr[0].trim());
                              stat2.setString(2,arr[1].trim());
                              x=stat2.executeUpdate(); 
                              if(stat2!=null){stat2.close();} 
                              
                           
                              
                              stat2=conn.prepareStatement(" delete from ei_ship_data where (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls " +
                                                          " where year=? and link_no=?)  and type_code in ('DBK','SVTAX','ROSL')");
                              stat2.setString(1,arr[0].trim());
                              stat2.setString(2,arr[1].trim());
                              stat2.executeUpdate(); 
                              if(stat2!=null){stat2.close();}      
                         }
                          if (x>0)
                          {
                           addActionMessage("Records Deleted...!");
                           return SUCCESS;
                          }
                         }
                     if(stat2!=null){stat2.close();}  
                    if (viewFlag!=null)
                    { 
                        if (AWBNO != null && AWBNO.length()> 0) {
                            q1 += " and a.AWB_NO like '"+AWBNO.toUpperCase().trim()+"%'";             
                        }
                        if (AWBDATE != null) {               
                            q1 += " and a.AWB_DATE='"+ date1 + "'";
                        }
                        if (HAWBNO != null && HAWBNO.length()>0) {
                            q1 += " and a.H_AWB_NO like  '"+ HAWBNO.toUpperCase().trim() + "%'";
                        }

                         

                        
                   stat2 = conn.prepareStatement("select to_char(awb_date,'dd/mm/yyyy') awb_date,AWB_NO,a.H_AWB_NO,to_char(ac_send_date,'dd/mm/yyyy') ac_send_date,PRE_CARRIAGE,ac_send_term,GROSS_WT,CHRG_WT,PACKAGE ,CBM,BUYING_HOUSE,BUYING_PERSON,      "
                           + "AC_SEND_TERM,YEAR,LINK_NO,F_BANK||'-'||F_BANK_ADDR FIN_BANK,DRAWN_BANK||'-'|| D_BANK_ADDR DRAWN_BANK  FROM EI_SHIPMENT_MAST A WHERE  (YEAR,LINK_NO) IN (SELECT YEAR,LINK_NO FROM EI_SHIPMENT_DTLS WHERE T_MOD='LGM4' AND LOCATION=? )  "+q1+" order by 1,2,3");
                   stat2.setString(1,LOCATION_CODE);
                       
                   result2 = stat2.executeQuery();
                      while (result2.next())  
                         {    
                               stat1=conn.prepareStatement("select * from ei_dbk_mast where  (nvl(dbk_received,0)>0 or nvl(str_recv,0)>0) and (shp_bill_no,shp_bill_date) in (select shp_bill_no,shp_bill_date from ei_shipment_dtls where year=? and link_no=?) ");
                               stat1.setString(1,result2.getString("year"));
                               stat1.setString(2,result2.getString("link_no"));
                               result1=stat1.executeQuery();
                               while (result1.next())
                               {
                                 DBKRECV="YES";
                               }
                             ShowDetail.add(new PostShipmentEntryViewBean(result2.getString("awb_no"),result2.getString("awb_date"),result2.getString("H_AWB_NO"),result2.getString("PRE_CARRIAGE"),result2.getString("PACKAGE"),result2.getString("CBM"),result2.getString("GROSS_WT"),result2.getString("BUYING_HOUSE"),result2.getString("CHRG_WT"),result2.getString("BUYING_PERSON"),
                                                                           result2.getString("ac_send_date"),result2.getString("ac_send_TERM"),result2.getString("YEAR"),result2.getString("LINK_NO"),result2.getString("FIN_BANK"),result2.getString("DRAWN_BANK"),DBKRECV));
                                
                          } 
                     
                    }
                    
                     stat2 = conn.prepareStatement("select type_code,type_desc   from  EI_GRUP_TYPE_DTLS where grup_type_code = 'BHS' order by type_desc");
                     result2 = stat2.executeQuery();
                      while (result2.next()) 
                         { 
                             
                              BUYHOUSE.add(new GetListBean(result2.getString("type_code"),result2.getString("type_desc"))); 
                                
                          } 
                      
                       
                       stat2 = conn.prepareStatement("select type_code,type_desc   from  EI_GRUP_TYPE_DTLS where grup_type_code = 'BHC' order by type_desc");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                              BUYPERSON.add(new GetListBean(result2.getString("type_code"),result2.getString("type_desc"))); 
                         
                                
                          } 
                    
                    
                  } 
          catch(Exception e){
              System.out.println(e.toString());
          }
      
             finally {
            if (conn != null) {conn.close();}
            if (result2!=null){result2.close();}
            if (stat2 != null) {stat2.close(); }
        }
      
        return SUCCESS;
 }   
    
    
    public String prints() throws FileNotFoundException, JRException, IOException, SQLException{
         
        try{
            EisUtil pisdate = new EisUtil();
            //currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        } 
        

        Connection conn = null;
    
        Connection conndb2=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        
        

        try {
            
             conn=new ConnectionSeplWeb().getConnection();
     
             conndb2=new connectiondb2().getConnection();
             List PostShipmntInvBeans=new ArrayList();
             
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
              PreparedStatement stat6=conn.prepareStatement("select a.awb_no,to_char(a.awb_date,'dd/mm/yyyy') awb_date,A.h_awb_no,shp_bill_no,to_char(shp_bill_date,'yyyy-mm-dd') shp_bill_date,to_char(let_exp_date,'yyyy-mm-dd') let_exp_date,"
                           + " B.all_no,B.gr_wt,B.package,B.dbk_conv,B.inr_conv,B.ship_qnty,B.fob_amt,B.gr_disc,B.currency,b.LC_NO,c.BUYER,c.DISCHARGE,c.DESTI_CNTRY,c.LOADING,c.MODE_OF_SHIP,c.agent, "
                           + " c.year,c.inv_no from  ei_shipment_mast a,ei_shipment_dtls  b, ei_endors_mast c where "
                           + " a.year=b.year and a.link_no=b.link_no and  b.year=c.year and b.company=c.company and b.inv_no=c.inv_no and b.year=?  and b.link_no=? order by 1,2,3");
                
               stat6.setString(1,YEAR);
               stat6.setString(2,LINK_NO);
               ResultSet result6=stat6.executeQuery();
               while(result6.next()){
               
                        
                   
                   
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(notify) notify,agent,fwd_code,hs_code,TRIM(manuf_code) manuf_code,"
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,LOADING_port,trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,place,fwd_custom,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,a.lcno,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) tax_cal_per,"
                            +" a.transport_cost,facility,CTNS from ei_endors_mast a  where  a.excs_inv_no=? ");
                    stat1.setString(1, result6.getString("all_no").trim());
                     
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next()) 
                    {  
                        PostShipmntInvoiceBean bean = new PostShipmntInvoiceBean();
       
                            bean.setAWBNO(result6.getString("AWB_NO"));
                            bean.setAWBDT(result6.getString("AWB_DATE"));
                            bean.setHAWBNO(result6.getString("H_AWB_NO"));
                            bean.setSBILNO(result6.getString("SHP_BILL_NO"));
                            bean.setSBILDT(result6.getString("SHP_BILL_DATE"));
                            
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
                          System.out.println("Facility "+result1.getString("facility"));   
                       
                       
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
                        
                        invq=invq+result1.getString("inv_qty"); 
                        bean.setInv_qty(invq);
                        
                        bean.setINV_NO(result1.getString("inv_no"));
                           UnitBean bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("MODE_OF_SHIP"),"MODL");
                            bean.setMode_of_ship(bn.getUNIT_ADDRESS());
                           
                  
                        
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("CNTRY_ORIGIN"),"CSCD");
                          bean.setCNTRY_ORIGIN_DESC(bn.getUNIT_DESC());
                           bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DESTI_CNTRY"),"CSCD");
                           bean.setDESTI_CNTRY_DESC(bn.getUNIT_DESC());
                         
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("LOADING_PORT"),"HAFE");
                          //LOADING_PORT_DESC=bn.getUNIT_ADDRESS();
                            bean.setLOADING_PORT_DESC(bn.getUNIT_DESC()); 
                             
                         
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DISCHARGE"),"SDST");
                          bean.setDISCHARGE_DESC(bn.getUNIT_DESC());
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("PLACE"),"EDES");
                          bean.setPLACE_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("exp_type"),"PRGP");
                          if (result1.getString("exp_type").equals("GMT"))
                          {bean.setExp_type_desc("READY MADE GARMENTS");}
                          else {bean.setExp_type_desc(bn.getUNIT_DESC());}
                          
                 
                          stat=conn.prepareStatement("select type_desc from ei_Grup_type_dtls where grup_type_code='SHT' and type_code=?");
                          stat.setString(1,result1.getString("payment_term"));
                          result=stat.executeQuery();
                          if(result.next())
                          {
                          bean.setPayment_term(result.getString("type_desc"));
                          }
                          if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
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
                             if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
                          }
                             
                          }
                          
                         
                          
                          
                          
                         SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
                         SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy");
                       
                         
                          List InvLineList = new ArrayList();
                          PreparedStatement stat2=null;
                           stat2 = conn.prepareStatement("select p.unit,p.description,exp_rate,decode(nvl(qty_kgs,0),0,unit,'KGS') uom,sum(qty_endors)*(price_fc+nvl(price_misc,0)) fob_amt,sum(decode(nvl(qty_kgs,0),0,qty_endors,qty_kgs)) iqty ,sum(gr_decl_amt)  grdecl,sum(qty_endors)*nvl(price_misc,0) miscfob,sum(p.qty_endors) endqty,sum(p.qty_kgs)  kgsqty,"
                                                        +" nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) tprice,nvl(p.price_fc,0) price_fc,nvl(price_misc,0) price_misc,nvl(transport_cost,0) transport_cost,(sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0))) fob_fc, (sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0)))* exp_rate fob_inr,"
                                                        +" sum((p.qty_endors * p.price_fc)-nvl(gr_decl_amt,0))*exp_rate inr_dbk,sum(p.qty_endors*nvl(price_misc,0))*exp_rate inr_misc,sum(gr_decl_amt) gr_decl,p3.CRNCY_CODE from ei_endors_dtls p ,ei_endors_mast p3,ei_exchange_rate_mast ex  where p.year = p3.year and p.type = p3.type and p.company = p3.company and  p.inv_no=p3.inv_no and  p.currency=ex.currency and "
                                                        +" p3.inv_date between begin_date and end_date and p.type = 'E' and p.year=? and p.company=? and p.inv_no=? group by p.unit,p.description,decode(nvl(qty_kgs,0),0,unit,'KGS'),exp_rate,nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) ,p.price_fc,p.price_misc ,nvl(transport_cost,0),p3.CRNCY_CODE order by 1,2");
                            stat2.setString(1, result1.getString("year"));
                            stat2.setString(2, result1.getString("company"));
                            stat2.setString(3, result1.getString("inv_no")); 
                       ResultSet  result2 = stat2.executeQuery(); 
                  
                        double trcost=0.0;double tnetwt=0.0; double tqty=0; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double tinr=0.0; double inrconv=0.0;
                        double tgrdecl=0.0; double netfc=0.0;double netfc1=0.0; double up=0.0; double tax_a =0.0; double tax_v=0.0; double comm_amt=0.0;
                        BigDecimal g1 = new BigDecimal("0.00");double excise_d=0.0;double tot_inv=0.0;double rate_inv=0.0;
                        double iqty=0.0;String crncy="";
                        while (result2.next()) {  
                            
                            crncy=result2.getString("CRNCY_CODE");
                            
                            chkuom=result2.getString("uom");
                            if (chkuom.equals(result2.getString("uom")))
                                    { tqty=tqty+result2.getDouble("endqty");}
                            else{tqty=0;}
                            trcost=trcost+result2.getDouble("endqty")*result2.getDouble("transport_cost");
                            //tfob=tfob+result2.getDouble("FOB_FC");
                            tnetwt=tnetwt+result2.getDouble("kgsqty");
                            tdbkinr=tdbkinr+result2.getDouble("inr_dbk");
                            
                            tmiscinr=tmiscinr+result2.getDouble("inr_misc");
                            tinr=tinr+result2.getDouble("fob_inr");
                       
                            g1=g1.add(result2.getBigDecimal("gr_decl"));
                             
                            tgrdecl=tgrdecl+result2.getDouble("gr_decl");
                            inrconv=result2.getDouble("exp_rate");  
                           
                            rate_inv=result2.getDouble("fob_amt")/result2.getDouble("iqty");
                           
                            tfob=tfob+result2.getDouble("fob_amt");
                            iqty=iqty+result2.getDouble("iqty");
                            InvLineList.add(new InvLineBean1(result2.getString("description"),result2.getString("iqty"),result2.getString("uom"),rate_inv,result2.getDouble("fob_amt")));
                        }
                         if(result2!=null){
                              result2.close();
                          }
                          if(stat2!=null){
                              stat2.close();
                          }
                        bean.setFWD_NAME(crncy);
                        netfc=roundTwoDecimals(tfob-tgrdecl);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTFOB(roundTwoDecimals(netfc)); 
                        bean.setTransport_cost(trcost);
                      
                        up=tfob*result1.getDouble("upcharge_per")/100;
                            
                        tax_v=((up+tfob)-tgrdecl)*result1.getDouble("tax_cal_per")/100;
                        
                        //excise_d=(Double.parseDouble(invq)*result4.getDouble("mrp_rate")*result4.getInt("cal_value")/100)*2/100;
                        
                        tax_a=tax_v*result1.getDouble("tax_percent")/100;
                        tot_inv=roundTwoDecimals(tfob+up+tax_a);
                        bean.setUP_AMT(roundTwoDecimals(up));
                        bean.setTAXABLE_VALUE(roundTwoDecimals(tax_v));
                        bean.setTAX_AMT(roundTwoDecimals(tax_a));
                   
                        bean.setTNETWT(tnetwt);   
                        bean.setTINVQTY(tqty);          
                        bean.setTINR(tinr);
                        bean.setTGRDECL(tgrdecl);       
                        bean.setGRPER(roundTwoDecimals(tgrdecl/tfob*100));
                        bean.setINV_FC(netfc); 
                       
                        //bean.setEXCISE_DUTY(excise_d);
                        comm_amt=netfc* result1.getDouble("comm_per")/100;
                        bean.setCOMM_AMT(roundTwoDecimals(comm_amt));
                    
                      if(result1.getString("crncy_code").equals("INR"))
                      {
                      netfc1=roundTwoDecimals(netfc+tax_a+tax_v);
                      //tot_inv=roundTwoDecimals(tfob+up+trcost);
                      }
                      else{
                       netfc1=roundTwoDecimals(netfc+up+trcost);
                      }
                      
                       bean.setTOTAL_INV(netfc1);
                      
                       
                        String decimal_print="";
                        stat=conn.prepareStatement("select nvl(decimal_print,'Cents') dp from ei_currency_mast where currency=? ");
                        stat.setString(1,result1.getString("crncy_code").trim());
                        result=stat.executeQuery();
                        if (result.next())
                        {decimal_print=result.getString("dp");
                        }
                        if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
                          }
                        
                        String wordfc="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,netfc1);
                        stat.setDouble(2,netfc1);
                        stat.setDouble(3,netfc1); 
                        result=stat.executeQuery();
                        if (result.next())
                        {   wordfc=result.getString("aa");
                            
                                if (result.getString("aa1")!=null)
                                {wordfc=wordfc+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                }else{wordfc=wordfc+" Only.";}
                            bean.setAMT_IN_WORD(wordfc);
                        }  
                        if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
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
                        if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
                          }
                          
                        if (mbpo!=null){
                        mbpo=mbpo.substring(0, mbpo.length()-1);}
                          bean.setPO_NO(mbpo);
                         BPOList.add(new GetListBean(mbpo,mbpo));
                         bean.setBPOLIST(BPOList);
                         bean.setPO_NO(mbpo);
                        
                       
                       
            
                      
                        
                        bean.setTMISCINR(tmiscinr);  
                          
                        tdbkinr=tdbkinr-tmiscinr; 
                        
                         bean.setTDBKINR(tdbkinr);  
                       
                        
                    
                                
                        PreparedStatement stat3 = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                        stat3.setString(1, result1.getString("BUYER"));
                        stat3.setString(2, result1.getString("BUYER_ADDR"));
                        ResultSet result3 = stat3.executeQuery();
                        if (result3.next() == true) {
                          bean.setBuyer_name(result3.getString("opcunm"));
                          bean.setBuyer_address(result3.getString("opadd"));
                         } 
                        if(result3!=null){
                              result3.close();
                          }
                          if(stat3!=null){
                              stat3.close();
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
                       
                        
                          
                          
                       
                        
                        PostShipmntInvBeans.add(bean);
        
                    }
               }

                //if (result6.getString("all_no").trim()!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/POST");
                    Map param1 = new HashMap();

                   
                    InputStream input=null;
                    param1.put("SUBREPORT_DIR", path);

                    
                    String crncy="";
                       stat = conn.prepareStatement("select crncy_code from  ei_shipment_dtls  b, ei_endors_mast c where b.year=c.year and b.company=c.company and b.inv_no=c.inv_no and b.year=?  and b.link_no=?");
                       stat.setString(1,YEAR);
                       stat.setString(2,LINK_NO);
                       result = stat.executeQuery();
                       if (result.next() == true) {
                        crncy  =result.getString("crncy_code");
                        }
                       if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
                          }
                           
                    if(crncy.equals("INR")){
                       input=new FileInputStream(new File(path+"/PostTaxInvoice1.jrxml")); 
                    }
                    else{
                        input=new FileInputStream(new File(path+"/PostshipmntEntryInvoice.jrxml"));
                    }
                     
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(PostShipmntInvBeans));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    //response.reset();
                    byte[] bytes = null;
                   
            
                response.setHeader("Content-Disposition", "attachment;filename=PostshipmntEntryInvoice.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
             
            
            //}
        } catch (Exception e) {
            System.out.println("PostShipmentEntry" + e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (conndb2 != null) {
                conndb2.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (result1 != null) {
                result1.close();
            }

        }
       
        return "prnt";
    }
     
    public String printInv() throws FileNotFoundException, JRException, IOException, SQLException{
         
        try{
            EisUtil pisdate = new EisUtil();
            //currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        } 
        

        Connection conn = null;
         
        Connection conndb2=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        
        

        try {
            
             conn=new ConnectionSeplWeb().getConnection();
             
             conndb2=new connectiondb2().getConnection();
             List PostShipmntInvBeans=new ArrayList();
             
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
              
               if(INVOICE_NO_INV!=null){
               
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(inv_date,'dd/mm/yyyy') inv_date,a.exp_type, "
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,LOADING_port,trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,place,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,a.crncy_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) tax_cal_per,"
                            +" a.transport_cost,facility,CTNS from ei_endors_mast a,ei_shipment_dtls b  where  a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.excs_inv_no=? ");
                    stat1.setString(1, INVOICE_NO_INV.trim());
                     
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next()) 
                    {  
                        PostShipmntInvoiceBean bean = new PostShipmntInvoiceBean();
       
                        
                        bean.setLocation(result1.getString("location"));
                        bean.setExcs_inv_no(result1.getString("excs_inv_no"));
                        bean.setPlan_no(result1.getString("plan_no"));
                        bean.setInv_date(result1.getString("inv_date"));
                        bean.setExp_type(result1.getString("exp_type"));
                        bean.setComm_per(result1.getString("comm_per"));
                        bean.setUpcharge_per(result1.getDouble("upcharge_per"));
                        bean.setPre_carriage(result1.getString("pre_carriage"));
                        bean.setCost_centre(result1.getString("cost_centre"));
                        bean.setMode_of_ship(result1.getString("mode_of_ship")); 
                        bean.setBuyer(result1.getString("buyer"));
                        bean.setBuyer_addr(result1.getString("buyer_addr").trim());
                        bean.setCons_addr(result1.getString("cons_addr").trim());
                        bean.setShip_term(result1.getString("ship_term"));
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
                        bean.setTAX_TYPE(result1.getString("tax_type"));
                        bean.setTAX_PERCENT(result1.getDouble("tax_percent"));
                        bean.setTAX_CODE(result1.getString("tax_code"));
                        bean.setTAX_CAL_PER(result1.getDouble("tax_cal_per"));
                        bean.setCTNS(result1.getString("CTNS"));
                        bean.setFACILITY(result1.getString("facility"));
                        System.out.println("Facility "+result1.getString("facility"));
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
                        
                        invq=invq+result1.getString("inv_qty"); 
                        bean.setInv_qty(invq);
                        
                        bean.setINV_NO(result1.getString("inv_no"));
                           UnitBean bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("MODE_OF_SHIP"),"MODL");
                            bean.setMode_of_ship(bn.getUNIT_ADDRESS());
                           
                        
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("CNTRY_ORIGIN"),"CSCD");
                          bean.setCNTRY_ORIGIN_DESC(bn.getUNIT_DESC());
                          
                           bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DESTI_CNTRY"),"CSCD");
                           bean.setDESTI_CNTRY_DESC(bn.getUNIT_DESC());
                         
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("LOADING_PORT"),"HAFE");
                          //LOADING_PORT_DESC=bn.getUNIT_ADDRESS();
                            bean.setLOADING_PORT_DESC(bn.getUNIT_DESC()); 
                             
                          
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DISCHARGE"),"SDST");
                          bean.setDISCHARGE_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("PLACE"),"EDES");
                          bean.setPLACE_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("exp_type"),"PRGP");
                          if (result1.getString("exp_type").equals("GMT"))
                          {bean.setExp_type_desc("READY MADE GARMENTS");}
                          else {bean.setExp_type_desc(bn.getUNIT_DESC());}
                          
                          
                         
                          stat=conn.prepareStatement("select type_desc from ei_Grup_type_dtls where grup_type_code='SHT' and type_code=?");
                          stat.setString(1,result1.getString("payment_term"));
                          result=stat.executeQuery();
                          if(result.next())
                          {
                          bean.setPayment_term(result.getString("type_desc"));
                          }
                          if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
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
                               // bean.setCon_cst(result.getString("ccst")); 
                               // bean.setCon_tin(result.getString("ctin"));
                             }
                             if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
                          }
                             
                          }
                          
                        String awbno="";String awbdt="";String hawbno="";String sbilno="";String sbildt="";
                        stat = conn.prepareStatement("select b.AWB_NO,to_char(b.AWB_DATE,'dd/mm/yyyy') AWB_DATE,b.H_AWB_NO,c.SHP_BILL_NO,to_char(c.SHP_BILL_DATE,'dd/mm/yyyy') SHP_BILL_DATE from ei_shipment_mast b,ei_shipment_dtls c where b.year=c.year and b.link_no=c.link_no and c.all_no=?");
                        stat.setString(1, INVOICE_NO_INV.trim());
                        result = stat.executeQuery();
                        if(result.next()){
                            bean.setAWBNO(result.getString("AWB_NO"));
                            bean.setAWBDT(result.getString("AWB_DATE"));
                            bean.setHAWBNO(result.getString("H_AWB_NO"));
                            bean.setSBILNO(result.getString("SHP_BILL_NO"));
                            bean.setSBILDT(result.getString("SHP_BILL_DATE"));
                            
                        }
                          
                          
                          
                         SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yy");
                         SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy");
                       
                        
                                   
                          List InvLineList = new ArrayList();
                          PreparedStatement stat2=null;
                           stat2 = conn.prepareStatement("select p.unit,p.description description,exp_rate,decode(nvl(qty_kgs,0),0,unit,'KGS') uom,sum(qty_endors)*(price_fc+nvl(price_misc,0)) fob_amt,sum(decode(nvl(qty_kgs,0),0,qty_endors,qty_kgs)) iqty ,sum(gr_decl_amt)  grdecl,sum(qty_endors)*nvl(price_misc,0) miscfob,sum(p.qty_endors) endqty,sum(p.qty_kgs)  kgsqty,nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) tprice,nvl(p.price_fc,0) price_fc,nvl(price_misc,0) price_misc,nvl(transport_cost,0) transport_cost,(sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0))) fob_fc, (sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0)))* exp_rate fob_inr,sum((p.qty_endors * p.price_fc)-nvl(gr_decl_amt,0))*exp_rate inr_dbk,sum(p.qty_endors*nvl(price_misc,0))*exp_rate inr_misc,sum(gr_decl_amt) gr_decl,p3.CRNCY_CODE from ei_endors_dtls p ,ei_endors_mast p3,ei_exchange_rate_mast ex  where p.year = p3.year and p.type = p3.type and p.company = p3.company and  p.inv_no=p3.inv_no and  p.currency=ex.currency and p3.inv_date between begin_date and end_date and p.type = 'E' and p.year=? and p.company=? and p.inv_no=? group by p.unit,p.description,decode(nvl(qty_kgs,0),0,unit,'KGS'),exp_rate,nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) ,p.price_fc,p.price_misc ,nvl(transport_cost,0),p3.CRNCY_CODE order by 1,2");
                            stat2.setString(1, result1.getString("year"));
                            stat2.setString(2, result1.getString("company"));
                            stat2.setString(3, result1.getString("inv_no")); 
                          ResultSet  result2 = stat2.executeQuery(); 
                  
                        double trcost=0.0;double tnetwt=0.0; double tqty=0; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double tinr=0.0; double inrconv=0.0;
                        double tgrdecl=0.0; double netfc=0.0;double netfc1=0.0;  double up=0.0; double tax_a =0.0; double tax_v=0.0; double comm_amt=0.0;
                        BigDecimal g1 = new BigDecimal("0.00");double excise_d=0.0;double tot_inv=0.0;double rate_inv=0.0;
                        double iqty=0.0;String crncy="";
                        while (result2.next()) {  
                            
                            crncy=result2.getString("CRNCY_CODE");
                            
                            chkuom=result2.getString("uom");
                            if (chkuom.equals(result2.getString("uom")))
                                    { tqty=tqty+result2.getDouble("endqty");}
                            else{tqty=0;}
                            trcost=trcost+result2.getDouble("endqty")*result2.getDouble("transport_cost");
                            //tfob=tfob+result2.getDouble("FOB_FC");
                            tnetwt=tnetwt+result2.getDouble("kgsqty");
                            tdbkinr=tdbkinr+result2.getDouble("inr_dbk");
                            
                            tmiscinr=tmiscinr+result2.getDouble("inr_misc");
                            tinr=tinr+result2.getDouble("fob_inr");
                       
                            g1=g1.add(result2.getBigDecimal("gr_decl"));
                             
                            tgrdecl=tgrdecl+result2.getDouble("gr_decl");
                            inrconv=result2.getDouble("exp_rate");  
                           
                            rate_inv=result2.getDouble("fob_amt")/result2.getDouble("iqty");
                           
                            tfob=tfob+result2.getDouble("fob_amt");
                            iqty=iqty+result2.getDouble("iqty");
                            InvLineList.add(new InvLineBean1(result2.getString("description"),result2.getString("iqty"),result2.getString("uom"),rate_inv,result2.getDouble("fob_amt")));
                        }
                         if(result2!=null){
                              result2.close();
                          }
                          if(stat2!=null){
                              stat2.close();
                          }
                        bean.setFWD_NAME(crncy);
                        netfc=roundTwoDecimals(tfob-tgrdecl);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTFOB(roundTwoDecimals(netfc)); 
                        bean.setTransport_cost(trcost);
                      
                        up=tfob*result1.getDouble("upcharge_per")/100;
                            
                        tax_v=((up+tfob)-tgrdecl)*result1.getDouble("tax_cal_per")/100;
                        
                        //excise_d=(Double.parseDouble(invq)*result4.getDouble("mrp_rate")*result4.getInt("cal_value")/100)*2/100;
                        
                        tax_a=tax_v*result1.getDouble("tax_percent")/100;
                        //tot_inv=roundTwoDecimals(tfob+up+tax_a);
                        bean.setUP_AMT(roundTwoDecimals(up));
                        bean.setTAXABLE_VALUE(roundTwoDecimals(tax_v));
                        bean.setTAX_AMT(roundTwoDecimals(tax_a));
                     
                        bean.setTNETWT(tnetwt);   
                        bean.setTINVQTY(tqty);          
                        bean.setTINR(tinr);
                        bean.setTGRDECL(tgrdecl);       
                        bean.setGRPER(roundTwoDecimals(tgrdecl/tfob*100));
                        bean.setINV_FC(netfc); 
                        //bean.setTOTAL_INV(tot_inv);
                        //bean.setEXCISE_DUTY(excise_d);
                        comm_amt=netfc* result1.getDouble("comm_per")/100;
                        bean.setCOMM_AMT(roundTwoDecimals(comm_amt));
                    
                      if(result1.getString("crncy_code").equals("INR"))
                      {
                      netfc1=roundTwoDecimals(netfc+tax_a+up+trcost);
                      //tot_inv=roundTwoDecimals(tfob+up+trcost);
                      }
                      else{
                       netfc1=roundTwoDecimals(netfc+up+trcost);
                      }
                      
                       bean.setTOTAL_INV(netfc1);
                      
                        String decimal_print="";
                        stat=conn.prepareStatement("select nvl(decimal_print,'Cents') dp from ei_currency_mast where currency=? ");
                        stat.setString(1,result1.getString("crncy_code").trim());
                        result=stat.executeQuery();
                        if (result.next())
                        {decimal_print=result.getString("dp");
                        }
                        if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
                          }
                        
                        String wordfc="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,netfc1);
                        stat.setDouble(2,netfc1);
                        stat.setDouble(3,netfc1); 
                        result=stat.executeQuery();
                        if (result.next())
                        {   wordfc=result.getString("aa");
                            
                                if (result.getString("aa1")!=null)
                                {wordfc=wordfc+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                }else{wordfc=wordfc+" Only.";}
                            bean.setAMT_IN_WORD(wordfc);
                        }  
                        if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
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
                        if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
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
                        if(result!=null){
                              result.close();
                          }
                          if(stat!=null){
                              stat.close();
                          }
                          
                        if (mbpo!=null){
                        mbpo=mbpo.substring(0, mbpo.length()-1);}
                         bean.setPO_NO(mbpo);
                         BPOList.add(new GetListBean(mbpo,mbpo));
                         bean.setBPOLIST(BPOList);
                        
                     
                          
                         tdbkinr=tdbkinr-tmiscinr; 
                        
                         bean.setTDBKINR(tdbkinr);  
                      
                     
 
                                
                        PreparedStatement stat3 = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                        stat3.setString(1, result1.getString("BUYER"));
                        stat3.setString(2, result1.getString("BUYER_ADDR"));
                        ResultSet result3 = stat3.executeQuery();
                        if (result3.next() == true) {
                          bean.setBuyer_name(result3.getString("opcunm"));
                          bean.setBuyer_address(result3.getString("opadd"));
                         } 
                        if(result3!=null){
                              result3.close();
                          }
                          if(stat3!=null){
                              stat3.close();
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
                      
                         
                        PostShipmntInvBeans.add(bean);
        
                    }
               }
 
                if (INVOICE_NO_INV!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/POST");
                    Map param1 = new HashMap();

                    
                    InputStream input=null;
                    param1.put("SUBREPORT_DIR", path);

                    
                    String crncy="";
                       stat = conn.prepareStatement("select crncy_code from ei_endors_mast a,ei_shipment_mast b,ei_shipment_dtls c where b.year=c.year and b.link_no=c.link_no and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and a.excs_inv_no=?");
                       stat.setString(1,INVOICE_NO_INV);
                       result = stat.executeQuery();
                       if (result.next() == true) {
                        crncy  =result.getString("crncy_code");
                        }
                       if(result!=null){
                              result.close();
                          }
                       if(stat!=null){
                              stat.close();
                          } 
                           
                    if(crncy.equals("INR")){
                       input=new FileInputStream(new File(path+"/PostTaxInvoice1.jrxml"));
                    }
                    else{
                        input=new FileInputStream(new File(path+"/PostshipmntEntryInvoice.jrxml"));
                    }
                     
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(PostShipmntInvBeans));
 
                     
                    ServletOutputStream out1 = response.getOutputStream();
                    //response.reset();
                    byte[] bytes = null;
                   
            
                response.setHeader("Content-Disposition", "attachment;filename=PostshipmntEntryInvoice.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
             
            
            }
        } catch (Exception e) {
            System.out.println("BabiesgarmentsInvoice" + e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (conndb2 != null) {
                conndb2.close();
            }
            if (stat1 != null) {
                stat1.close();
            }
            if (result1 != null) {
                result1.close();
            }

        }
       
        return "srch";
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
      
        try {
             EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
            
            stat=conn.prepareStatement("select to_char(lock_date,'yyyymmdd') lock_date,to_char(sb_date_allow,'yyyymmdd') sb_allow,to_char(awb_date_allow,'yyyymmdd') awb_allow from ei_sale_lock");
            result=stat.executeQuery();
            if (result.next())
            {
              SALE_LOCK=result.getString("lock_date");
              AWB_ALLOW=result.getString("awb_allow");
              SB_ALLOW=result.getString("sb_allow");
            
            }
            stat = conn.prepareStatement("select awb_no,to_char(awb_date,'yyyy-mm-dd') awb_date,h_awb_no,pre_carriage,package,cbm,gross_wt,chrg_wt,to_char(ac_send_date,'dd-Mon-yyyy') fin_date,BUYING_PERSON,BUYING_HOUSE from ei_shipment_mast where year=? and link_no=?");
            stat.setString(1, YEAR);
            stat.setString(2,LINK_NO);
            result = stat.executeQuery();
            while (result.next()) {
                AWBNO=result.getString("awb_no");
                AWB_DATE=result.getString("awb_date");
                 HAWBNO=result.getString("H_AWB_NO");
                PRECRIGE=result.getString("PRE_CARRIAGE");
                TOTALPCKGE=result.getInt("PACKAGE");
                CBM=result.getDouble("CBM");
                GROSSWT=result.getDouble("GROSS_WT");
                CHARABLEWT=result.getString("CHRG_WT");
                FIN_DATE=result.getString("fin_date");
                BUYING_HOUSE=result.getString("BUYING_HOUSE"); 
                BUYING_PERSON=result.getString("BUYING_PERSON");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
            
            
            try{
                
                stat2=conn.prepareStatement("select shp_bill_no,to_char(shp_bill_date,'yyyy-mm-dd') shp_bill_date,to_char(let_exp_date,'yyyy-mm-dd') let_exp_date,"
                           + " all_no,gr_wt,package,dbk_conv,inr_conv,ship_qnty,fob_amt,gr_disc,currency,b.LC_NO,c.BUYER,c.DISCHARGE,c.DESTI_CNTRY,c.LOADING,c.MODE_OF_SHIP,c.agent, "
                           + " c.year,c.inv_no from  ei_shipment_dtls  b, ei_endors_mast c where "
                           + " b.year=c.year and b.company=c.company and b.inv_no=c.inv_no and b.year=?  and b.link_no=? order by 1,2,3");
                
                stat2.setString(1,YEAR);
                stat2.setString(2,LINK_NO);
                result2=stat2.executeQuery();
                while(result2.next()){
                    
                   TOTPKGS=TOTPKGS+result2.getInt("PACKAGE");
                   TOTGRWT=TOTGRWT+result2.getDouble("GR_WT");
                   TOTFOB=TOTFOB+result2.getDouble("FOB_AMT");
                   TOTGR=TOTGR+result2.getDouble("GR_DISC");
                   TOTQTY=TOTQTY+result2.getDouble("SHIP_QNTY");
                   
                   listdata.add(new PostShipmentEntryViewEditBean(result2.getString("shp_bill_no"),result2.getString("shp_bill_date"),result2.getString("let_exp_date"),result2.getString("all_no"),
                                     result2.getString("GR_WT"),result2.getString("PACKAGE"),result2.getString("LC_NO"),result2.getString("INR_CONV"),result2.getString("DBK_CONV"),result2.getString("MODE_OF_SHIP"),
                                     result2.getString("FOB_AMT"),result2.getString("SHIP_QNTY"),result2.getString("LOADING"),result2.getString("BUYER"),
                                     result2.getString("AGENT"),result2.getString("DESTI_CNTRY"),result2.getString("currency"),result2.getString("GR_DISC"),result2.getString("YEAR"),result2.getString("INV_NO"))); 
                }
                
                
                //for buying house list 
                stat2 = conn.prepareStatement("select type_code,type_desc   from  EI_GRUP_TYPE_DTLS where grup_type_code = 'BHS' order by type_desc");
                     result2 = stat2.executeQuery();
                      while (result2.next()) 
                         { 
                              BUYHOUSE.add(new GetListBean(result2.getString("type_code"),result2.getString("type_desc"))); 
                         
                                
                          } 
                      
               //for buying person list       
               stat2 = conn.prepareStatement("select type_code,type_desc   from  EI_GRUP_TYPE_DTLS where grup_type_code = 'BHC' order by type_desc");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                             
                            BUYPERSON.add(new GetListBean(result2.getString("type_code"),result2.getString("type_desc"))); 
                           
                          }        
                  
            }catch(Exception e){
                System.out.println(e.toString());
            }
            finally {
            if (conn != null) {conn.close();}
            if (result != null) {result.close(); }
            if (stat != null) {stat.close(); }
            if (result2 != null) {result2.close(); }
            if (stat2 != null) {stat2.close(); }
        }
        
        
        return "edit";
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
            
            try{
                 
                 EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection(); 
              
                stat2=conn.prepareStatement("select to_char(lock_date,'yyyymmdd') lock_date,to_char(sb_date_allow,'yyyymmdd') sb_allow,to_char(awb_date_allow,'yyyymmdd') awb_allow,to_char(sysdate,'yyyy-mm-dd') maxdate from ei_sale_lock");
                result2=stat2.executeQuery();
                if (result2.next())
                {
                  SALE_LOCK=result2.getString("lock_date");
                  AWB_ALLOW=result2.getString("awb_allow");
                  SB_ALLOW=result2.getString("sb_allow");
                  MAXDATE=result2.getString("maxdate");
                }
                    //for buying house list       
                    stat2 = conn.prepareStatement("select type_code,type_desc   from  EI_GRUP_TYPE_DTLS where grup_type_code = 'BHS' order by type_desc");
                     result2 = stat2.executeQuery();
                      while (result2.next()) 
                         { 
                                BUYHOUSE.add(new GetListBean(result2.getString("type_code"),result2.getString("type_desc"))); 
                         
                                
                          } 
                      
                      
                       //for buying person list       
                        stat2 = conn.prepareStatement("select type_code,type_desc   from  EI_GRUP_TYPE_DTLS where grup_type_code = 'BHC' order by type_desc");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         { 
                               BUYPERSON.add(new GetListBean(result2.getString("type_code"),result2.getString("type_desc"))); 
                         
                                
                          }   
                      }catch(Exception e){
                System.out.println(e.toString());
            }
        
        AWBNO=null;
        AWBDATE=null;
        HAWBNO=null;
        CONTAINERNO=null;
        PRECRIGE="";
        CHARABLEWT=null;
        TOTALPCKGE=0;
        CBM=0.0;
        GROSSWT=0.0;
        BILL_NO=null;
        BILL_DATE=null;
        LET_EXP_DT=null;
        INVOICE_NO=null;
        DBK=null;
        GROSS_WT=null;
        PKGS=null;
        CRNCY_CODE=null;
        GR_DISC=null;
        LLC_NO=null;
        INR_C=null; 
        DBK_C=null;
        MOS=null;
        FOBAMT=null;
        QTY=null;
        PORT_L=null;
        BUYER_L=null;
        CHA_L=null;
        DISCHARGE_L=null;
        DESTNCNTRY_L=null;
        FORWARDER_L=null;
        EI_YEAR=null;
        LINK_NO=null;
        INV_YEAR=null;
      
        return "new";
    }
    
    
    
    public String back(){
          return "bck";
    }


    public String LCsearch() throws SQLException {

         
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;

        try {
            conn = new connection().getConnection();
                double percent=0.0;
           
                if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                        stat = conn.prepareStatement(" select ref_no,to_char(ref_date,'dd/mm/yyyy') ref_date,value_fc,exp_util_fc,nvl(Value_fc,0)-nvl(exp_util_fc,0) bal_amt,nvl(percent,0) percent from  ei_lc_lic_mast where ref_type='LC' and ref_no like ?");
                        stat.setString(1,SEARCH_CODE.toUpperCase() + "%");
                        result = stat.executeQuery();
                        while (result.next()) {
                             
                            LCLIST.add(new LicSearchBean(result.getString("ref_no"),result.getString("ref_date"),result.getString("bal_amt"),result.getString("percent"),result.getString("value_fc"),result.getString("exp_util_fc")));
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

        return "LCsearch";
    }
     
   
    public String update1() throws SQLException{
                 Connection conn = null;
                 Connection conndb2=null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
                 PreparedStatement stat3=null;
                 ResultSet result3=null;
                 PreparedStatement stat4=null;
                 ResultSet result4=null;
                  PreparedStatement stat5=null;
                 ResultSet result5=null;
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

            if (usrid == null) {
                session.put("sessUserId", aausrid);
                usrid = aausrid;
            }

            if (usrid == null) {
                addActionMessage("Session Not Valid !!");
                return ERROR;
            }
            
             SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
             SimpleDateFormat simpledateformate2 = new SimpleDateFormat("dd/mm/yyyy");
             SimpleDateFormat simpledateformate3 = new SimpleDateFormat("yyyyMMdd");
             String date1=null;
             String date2=null;
          try{
              
                    stat3= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                    stat3.setString(1,usrid.trim());
                    result3=stat3.executeQuery();
                    while (result3.next())
                    {LOCATION_CODE=result3.getString("location_code");
                    } 
                     
            if (saveFlag != null && saveFlag.length()>0 ) {
                
               
                    
                    conn = new connection().getConnection();
                    conn.setAutoCommit(false);
                    
                    String linkno1=null;
                     int x=0,y=0;
                     BigDecimal INRCONV= new BigDecimal("0.0");;  
                     BigDecimal DBKCONV= new BigDecimal("0.0");;
                      if(AWBNO!=null && AWBNO.length()>0)
                      {
                          
                          
                        stat3=conn.prepareStatement("select * from ei_shipment_mast where  YEAR=? AND LINK_NO=?");
                        stat3.setString(1, EI_YEAR);
                        stat3.setString(2,LINK_NO);
                        result3=stat3.executeQuery();
                        if(result3.next()){
                            
                                stat2 = conn.prepareStatement("UPDATE ei_shipment_mast  SET awb_no=?,awb_date=to_date(?,'dd-MON-yy'),h_awb_no=upper(?),PRE_CARRIAGE=upper(?),PACKAGE=?,CBM=?,GROSS_WT=?,BUYING_HOUSE=?,"
                                        +" CHRG_WT=?,BUYING_PERSON=? WHERE year=? and link_no=?");
                                stat2.setString(1, AWBNO.toUpperCase());
                                stat2.setString(2, simpledateformate1.format(AWBDATE));
                                stat2.setString(3, HAWBNO);
                                 stat2.setString(4, PRECRIGE);
                                stat2.setInt(5, TOTALPCKGE);
                                stat2.setDouble(6, CBM);
                                stat2.setDouble(7, GROSSWT);
                                stat2.setString(8, BH_HOUSE);
                                stat2.setString(9, CHARABLEWT);
                                stat2.setString(10, BH_PERSON);
                                stat2.setString(11, EI_YEAR);
                                stat2.setString(12,LINK_NO);
                                stat2.executeUpdate();
                               
                      }  
                        else{
                            
                                  
                            if(AWBNO!=null &&  AWBNO.length()>0)
                             {
                                          stat5 = conn.prepareStatement("select VOU_NUMB+1 linkno from ei_vou_numb_mast where  vou_type='EI' AND SUB_TYPE='PS' ");
                                          result5=stat5.executeQuery();
                                          if(result5.next()){
                                             linkno1 =result5.getString("linkno");
                                          }
                                           if (result5!=null){result5.close();}
                                           if (stat5 != null) {stat5.close();}
                                           
                                            stat5=conn.prepareStatement("update ei_vou_numb_mast set vou_numb=?  where  VOU_TYPE='EI' AND SUB_TYPE='PS' ");
                                            stat5.setString(1,linkno1);
                                            stat5.executeUpdate();
                                             
                                    if (result5!=null){result5.close();}
                                    if (stat5 != null) {stat5.close();}
                                
                                  
                                
                                
                               stat2 = conn.prepareStatement("insert into ei_shipment_mast(awb_no,awb_date,h_awb_no,PRE_CARRIAGE,PACKAGE,CBM,"
                                           + "GROSS_WT,BUYING_HOUSE,CHRG_WT,BUYING_PERSON,YEAR,LINK_NO,seh_user,tdate,t_mod,g_comp,frt_type) values(?,?,upper(?),upper(?),?,?,?,?,?,?,?,?,?,sysdate,'LGM4','111','C')");
                                stat2.setString(1, AWBNO.toUpperCase());
                                stat2.setString(2, simpledateformate1.format(AWBDATE));
                                stat2.setString(3, HAWBNO);
                                stat2.setString(4, PRECRIGE);
                                stat2.setInt(5, TOTALPCKGE);
                                stat2.setDouble(6,CBM);
                                stat2.setDouble(7, GROSSWT);
                                stat2.setString(8, BH_HOUSE);
                                stat2.setString(9,CHARABLEWT);
                                stat2.setString(10, BH_PERSON);
                                stat2.setString(11,EI_YEAR);
                                stat2.setString(12, linkno1);
                                stat2.setString(13, usrid);
                                
                                stat2.executeUpdate();
                                
                            }}
                    
                      
                    if(INVOICE_NO!=null){
                        for (int i = 0; i < INVOICE_NO.size(); i++) 
                      {
                          if(INVOICE_NO.get(i).toString().isEmpty())     {
                          break;
                      }              
                          else if (INVOICE_NO.get(i)!=null && !INVOICE_NO.get(i).toString().isEmpty())
                     {   
                           if (LINK_NO!=null && LINK_NO.length()!=0)
                                    {
                                    linkno1=LINK_NO;
                                    } 
                         
                           INRCONV=new BigDecimal("0.0");
                           DBKCONV=new BigDecimal("0.0"); int aa=0; int bb=0;
                              stat5=conndb2.prepareStatement(" select cuarat from m3fdbprd.ccurra c where c.cucutd = (select max(y.cucutd) from m3fdbprd.ccurra y where c.cucono = y.cucono and c.cucucd = y.cucucd "+
                                                         " and   c.cucrtp = y.cucrtp and c.culocd = y.culocd and CUCUTD <=? ) and c.cucono=111 and c.cucucd = ? and cucrtp = '1' and culocd = 'INR' ");
                             stat5.setString(1,simpledateformate3.format(AWBDATE));
                             stat5.setString(2,CRNCY_CODE.get(i).toString().trim());
                             result5 = stat5.executeQuery();
                              while (result5.next())
                             {    aa=result5.getInt("cuarat");
                                INRCONV=result5.getBigDecimal("cuarat");
                             } 
                           if (result5!=null){result5.close();}
                           if (stat5 != null) {stat5.close(); }
                           if (aa==0)
                           {
                             addActionError("Check Awb Date ... INR Conv not defined");
                             return ERROR;
                           }
                               
                               stat5=conn.prepareStatement(" SELECT exp_rate  FROM EI_EXCHANGE_RATE_MAST WHERE ? between to_char(begin_date,'yyyymmdd') and to_char(end_date,'yyyymmdd') and  CURRENCY= ? ");
                               stat5.setString(1,simpledateformate3.format(BILL_DATE.get(i)));
                               stat5.setString(2,CRNCY_CODE.get(i).toString().trim());
                               result5 = stat5.executeQuery();
                              if (result5.next())
                             {     
                                bb=result5.getInt("exp_rate");
                                DBKCONV=result5.getBigDecimal("exp_rate");
                             }
                             if (stat5 != null) {stat5.close();}
                             if (stat5 != null) {stat5.close();}
                             if (bb==0)
                             {
                              addActionError("Check SB Date ... INR Conv not defined");
                              return ERROR;
                             }
                         ///// Cheking duplicate S/B
                          /*     stat5=conn.prepareStatement(" SELECT  * FROM EI_shipment_dtls  WHERE  trim(shp_bill_no)=? and to_char(shp_bill_date,'yyyymmdd')=?  and link_no<>? ");
                               stat5.setString(1,BILL_NO.get(i).toString().toUpperCase());
                               stat5.setString(2,simpledateformate3.format(BILL_DATE.get(i)));
                               stat5.setString(3,linkno1);
                               result5 = stat5.executeQuery();
                              if (result5.next())
                             {     
                                addActionError("S/B Alreaady Exists....");
                                return ERROR;
                             }
                             if (stat5 != null) {stat5.close();}
                             if (stat5 != null) {stat5.close();}
                       */
                                                         
                             if (Integer.parseInt(simpledateformate3.format(BILL_DATE.get(i)))<Integer.parseInt(SB_ALLOW))
                             {
                                addActionError("Check S/B Allow Date...");
                                return ERROR;
                             }
                         
                        stat2=conn.prepareStatement("select * from ei_shipment_dtls where ALL_NO=? ");
                        stat2.setString(1, INVOICE_NO.get(i).toString());
                        result2=stat2.executeQuery();
                        if(result2.next()){
                             
                                stat4 = conn.prepareStatement("UPDATE ei_shipment_dtls SET shp_bill_no=?,shp_bill_date=to_date(?,'dd-MON-yy'),let_exp_date=to_date(?,'dd-MON-yy'), "
                                        +" GR_WT=?,PACKAGE=?, LC_NO=upper(?),dbk_conv=?,inr_conv=? WHERE all_no=?");
                                stat4.setString(1, BILL_NO.get(i).toString().toUpperCase());
                                stat4.setString(2, simpledateformate1.format(BILL_DATE.get(i)));
                                stat4.setString(3, simpledateformate1.format(LET_EXP_DT.get(i)));
                                stat4.setString(4, GROSS_WT.get(i).toString());
                                stat4.setInt(5, Integer.parseInt(PKGS.get(i).toString()));
                                stat4.setString(6,  LLC_NO.get(i).toString()); 
                                stat4.setBigDecimal(7,DBKCONV);
                                 stat4.setBigDecimal(8,INRCONV);
                                stat4.setString(9, INVOICE_NO.get(i).toString());
                                x=stat4.executeUpdate();
                            }  
                        else{ 
                             
                              
                         
                                stat4 = conn.prepareStatement("insert into ei_shipment_dtls(shp_bill_no,shp_bill_date,let_exp_date,all_no,DBK_SLNO,GR_WT,PACKAGE,LC_NO,DBK_CONV,INR_CONV,CURRENCY,"
                                                            + "FOB_AMT,GR_DISC,SHIP_QNTY,MEIS_CNTRY,YEAR,INV_NO,INV_YEAR,XSTEPY,XSNODY,COMPANY,T_MOD,COMP_GRP_CODE,LINK_NO,location,seh_user,tdate) "
                                                            +  " values(?,?,?,?,?,?,?,UPPER(?),?,?,?,?,?,?,?,?,?,?,?,?,'111','LGM4','111',?,?,?,sysdate)");
                                stat4.setString(1, BILL_NO.get(i).toString().toUpperCase());
                                stat4.setString(2, (BILL_DATE.get(i)!=null && simpledateformate1.format(BILL_DATE.get(i))!=null && simpledateformate1.format(BILL_DATE.get(i)).length()>0)?simpledateformate1.format(BILL_DATE.get(i)):"");
                                stat4.setString(3, (LET_EXP_DT.get(i)!=null && simpledateformate1.format(LET_EXP_DT.get(i))!=null && simpledateformate1.format(LET_EXP_DT.get(i)).length()>0)?simpledateformate1.format(LET_EXP_DT.get(i)):"");
                                stat4.setString(4, INVOICE_NO.get(i).toString());
                                stat4.setString(5,"Y");
                                stat4.setString(6, GROSS_WT.get(i).toString());
                                stat4.setString(7, PKGS.get(i).toString());
                                stat4.setString(8,  LLC_NO.get(i).toString());
                                stat4.setBigDecimal(9, DBKCONV);
                                stat4.setBigDecimal(10,INRCONV);
                                stat4.setString(11,CRNCY_CODE.get(i).toString());
                                stat4.setString(12,FOBAMT.get(i).toString());
                                stat4.setString(13,GR_DISC.get(i).toString());
                                stat4.setString(14,QTY.get(i).toString());
                                stat4.setString(15,DESTNCNTRY_L.get(i).toString());
                                stat4.setString(16,EI_YEAR);
                                stat4.setString(17,INV_NO.get(i).toString());
                                stat4.setString(18,INV_YEAR.get(i).toString());
                                stat4.setString(19,XSTEPY.get(i).toString());
                                stat4.setString(20,XSNODY.get(i).toString());
                                stat4.setString(21, linkno1);
                                stat4.setString(22,LOCATION_CODE);
                                stat4.setString(23,usrid);
                                
                                y=stat4.executeUpdate();
                             }
                        }}
                    }
                    if(x>0){
                        addActionError("Reocrd Updated");
                          x=0;y=0;
                          linkno1=null;
                        edit();
                    }
                     if(y>0){
                        conn.commit();
                        addActionMessage("Reocrd Save");
                        
                        AWBNO=null;
                        AWBDATE=null;
                        HAWBNO=null;
                        CONTAINERNO=null;
                        PRECRIGE=null;
                        CHARABLEWT=null;
                        TOTALPCKGE=0;
                        CBM=0.0;
                        GROSSWT=0.0;
                        BILL_NO=null;
                        BILL_DATE=null;
                        LET_EXP_DT=null;
                        INVOICE_NO=null;
                        DBK=null;
                        GROSS_WT=null;
                        PKGS=null;
                        CRNCY_CODE=null;
                        GR_DISC=null;
                        LLC_NO=null;
                        INR_C=null; 
                        DBK_C=null;
                        MOS=null;
                        FOBAMT=null;
                        QTY=null;
                        PORT_L=null;
                        BUYER_L=null;
                        CHA_L=null; 
                        DISCHARGE_L=null;
                        DESTNCNTRY_L=null;
                        FORWARDER_L=null;
                        EI_YEAR=null;
                        LINK_NO=null;
                        SALE_LOCK=null;
                        BH_HOUSE=null;
                        BH_PERSON=null;
                        AWB_ALLOW=null;
                        SB_ALLOW=null;
                        INV_YEAR=null;
                         x=0;y=0;
                         linkno1=null;
                        
                    }
                   
                    } 
                
                    
                  
                }
             }
                catch(Exception e){
                    System.out.println(e.toString());
                      addActionError("ERROR :"+e);
                      conn.rollback();
                    e.printStackTrace();
                }
             finally { 
            if (conn != null) {conn.close();}
            if (conndb2 != null) {conndb2.close();}
            if (result2!=null){result2.close();}
            if (stat2 != null) {stat2.close(); }
            if (result3!=null){result3.close();}
            if (stat3 != null) {stat3.close(); }
            if (result4!=null){result4.close();}
            if (stat4 != null) {stat4.close(); }
             if (result5!=null){result5.close();}
            if (stat5 != null) {stat5.close(); }
          }
             
            newentry();
            
            
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

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getLINK_NO() {
        return LINK_NO;
    }

    public void setLINK_NO(String LINK_NO) {
        this.LINK_NO = LINK_NO;
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

    public int getTOTALPCKGE() {
        return TOTALPCKGE;
    }

    public void setTOTALPCKGE(int TOTALPCKGE) {
        this.TOTALPCKGE = TOTALPCKGE;
    }

    public Double getCBM() {
        return CBM;
    }

    public void setCBM(Double CBM) {
        this.CBM = CBM;
    }

    public Double getGROSSWT() {
        return GROSSWT;
    }

    public void setGROSSWT(Double GROSSWT) {
        this.GROSSWT = GROSSWT;
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

    public String getINVDATE() {
        return INVDATE;
    }

    public void setINVDATE(String INVDATE) {
        this.INVDATE = INVDATE;
    }

    public String getPORTCLR() {
        return PORTCLR;
    }

    public void setPORTCLR(String PORTCLR) {
        this.PORTCLR = PORTCLR;
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

    public List<String> getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(List<String> BILL_NO) {
        this.BILL_NO = BILL_NO;
    }
    public List<String> getDBK() {
        return DBK;
    }

    public void setDBK(List<String> DBK) {
        this.DBK = DBK;
    }

    public List<String> getGROSS_WT() {
        return GROSS_WT;
    }

    public void setGROSS_WT(List<String> GROSS_WT) {
        this.GROSS_WT = GROSS_WT;
    }

    public List<String> getPKGS() {
        return PKGS;
    }

    public void setPKGS(List<String> PKGS) {
        this.PKGS = PKGS;
    }

    public List<String> getAMT() {
        return AMT;
    }

    public void setAMT(List<String> AMT) {
        this.AMT = AMT;
    }

    public List<String> getLLC_NO() {
        return LLC_NO;
    }

    public void setLLC_NO(List<String> LLC_NO) {
        this.LLC_NO = LLC_NO;
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

    public List<Date> getBILL_DATE() {
        return BILL_DATE;
    }

    public void setBILL_DATE(List<Date> BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
    }

    public List<Date> getLET_EXP_DT() {
        return LET_EXP_DT;
    }

    public void setLET_EXP_DT(List<Date> LET_EXP_DT) {
        this.LET_EXP_DT = LET_EXP_DT;
    }

    public List<String> getINR_C() {
        return INR_C;
    }

    public void setINR_C(List<String> INR_C) {
        this.INR_C = INR_C;
    }

    public List<String> getDBK_C() {
        return DBK_C;
    }

    public void setDBK_C(List<String> DBK_C) {
        this.DBK_C = DBK_C;
    }

    public List<String> getMOS() {
        return MOS;
    }

    public void setMOS(List<String> MOS) {
        this.MOS = MOS;
    }

    public List<String> getFOBAMT() {
        return FOBAMT;
    }

    public void setFOBAMT(List<String> FOBAMT) {
        this.FOBAMT = FOBAMT;
    }

    public List<String> getQTY() {
        return QTY;
    }

    public void setQTY(List<String> QTY) {
        this.QTY = QTY;
    }

    public List<String> getPORT_L() {
        return PORT_L;
    }

    public void setPORT_L(List<String> PORT_L) {
        this.PORT_L = PORT_L;
    }

    public List<String> getBUYER_L() {
        return BUYER_L;
    }

    public void setBUYER_L(List<String> BUYER_L) {
        this.BUYER_L = BUYER_L;
    }

    public List<String> getCHA_L() {
        return CHA_L;
    }

    public void setCHA_L(List<String> CHA_L) {
        this.CHA_L = CHA_L;
    }

    public List<String> getDISCHARGE_L() {
        return DISCHARGE_L;
    }

    public void setDISCHARGE_L(List<String> DISCHARGE_L) {
        this.DISCHARGE_L = DISCHARGE_L;
    }

    public List<String> getDESTNCNTRY_L() {
        return DESTNCNTRY_L;
    }

    public void setDESTNCNTRY_L(List<String> DESTNCNTRY_L) {
        this.DESTNCNTRY_L = DESTNCNTRY_L;
    }

    public List<String> getFORWARDER_L() {
        return FORWARDER_L;
    }

    public void setFORWARDER_L(List<String> FORWARDER_L) {
        this.FORWARDER_L = FORWARDER_L;
    }

    public Date getAWBDATE_AJ() {
        return AWBDATE_AJ;
    }

    public void setAWBDATE_AJ(Date AWBDATE_AJ) {
        this.AWBDATE_AJ = AWBDATE_AJ;
    }

    public Date getBILL_DATE_AJ() {
        return BILL_DATE_AJ;
    }

    public void setBILL_DATE_AJ(Date BILL_DATE_AJ) {
        this.BILL_DATE_AJ = BILL_DATE_AJ;
    }

    public String getFIN_DATE() {
        return FIN_DATE;
    }

    public void setFIN_DATE(String FIN_DATE) {
        this.FIN_DATE = FIN_DATE;
    }

    public String getAWB_DATE() {
        return AWB_DATE;
    }

    public void setAWB_DATE(String AWB_DATE) {
        this.AWB_DATE = AWB_DATE;
    }

    public Double getTOTGRWT() {
        return TOTGRWT;
    }

    public void setTOTGRWT(Double TOTGRWT) {
        this.TOTGRWT = TOTGRWT;
    }

    public int getTOTPKGS() {
        return TOTPKGS;
    }

    public void setTOTPKGS(int TOTPKGS) {
        this.TOTPKGS = TOTPKGS;
    }

    public String getBUYING_HOUSE() {
        return BUYING_HOUSE;
    }

    public void setBUYING_HOUSE(String BUYING_HOUSE) {
        this.BUYING_HOUSE = BUYING_HOUSE;
    }

    public String getBUYING_PERSON() {
        return BUYING_PERSON;
    }

    public void setBUYING_PERSON(String BUYING_PERSON) {
        this.BUYING_PERSON = BUYING_PERSON;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }

    public List getLCLIST() {
        return LCLIST;
    }

    public void setLCLIST(List LCLIST) {
        this.LCLIST = LCLIST;
    }

    public String getAWB_ALLOW() {
        return AWB_ALLOW;
    }

    public void setAWB_ALLOW(String AWB_ALLOW) {
        this.AWB_ALLOW = AWB_ALLOW;
    }

    public String getSB_ALLOW() {
        return SB_ALLOW;
    }

    public void setSB_ALLOW(String SB_ALLOW) {
        this.SB_ALLOW = SB_ALLOW;
    }

    public String getSALE_LOCK() {
        return SALE_LOCK;
    }

    public void setSALE_LOCK(String SALE_LOCK) {
        this.SALE_LOCK = SALE_LOCK;
    }

    public String getMAXDATE() {
        return MAXDATE;
    }

    public void setMAXDATE(String MAXDATE) {
        this.MAXDATE = MAXDATE;
    }

    public String getINR_CONV() {
        return INR_CONV;
    }

    public void setINR_CONV(String INR_CONV) {
        this.INR_CONV = INR_CONV;
    }

    public List getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(List CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public List<String> getGR_DISC() {
        return GR_DISC;
    }

    public void setGR_DISC(List<String> GR_DISC) {
        this.GR_DISC = GR_DISC;
    }

    public List getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(List INV_NO) {
        this.INV_NO = INV_NO;
    }

    public List getXSNODY() {
        return XSNODY;
    }

    public void setXSNODY(List XSNODY) {
        this.XSNODY = XSNODY;
    }

    public List getXSTEPY() {
        return XSTEPY;
    }

    public void setXSTEPY(List XSTEPY) {
        this.XSTEPY = XSTEPY;
    }

    public String getEI_YEAR() {
        return EI_YEAR;
    }

    public void setEI_YEAR(String EI_YEAR) {
        this.EI_YEAR = EI_YEAR;
    }

    public List getINV_YEAR() {
        return INV_YEAR;
    }

    public void setINV_YEAR(List INV_YEAR) {
        this.INV_YEAR = INV_YEAR;
    }

    public String getBH_HOUSE() {
        return BH_HOUSE;
    }

    public void setBH_HOUSE(String BH_HOUSE) {
        this.BH_HOUSE = BH_HOUSE;
    }

    public String getBH_PERSON() {
        return BH_PERSON;
    }

    public void setBH_PERSON(String BH_PERSON) {
        this.BH_PERSON = BH_PERSON;
    }

    public List getSHIP_DEL() {
        return SHIP_DEL;
    }

    public void setSHIP_DEL(List SHIP_DEL) {
        this.SHIP_DEL = SHIP_DEL;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDBKRECV() {
        return DBKRECV;
    }

    public void setDBKRECV(String DBKRECV) {
        this.DBKRECV = DBKRECV;
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

    public String getINVOICE_NO_INV() {
        return INVOICE_NO_INV;
    }

    public void setINVOICE_NO_INV(String INVOICE_NO_INV) {
        this.INVOICE_NO_INV = INVOICE_NO_INV;
    }
      
    
       
}
 