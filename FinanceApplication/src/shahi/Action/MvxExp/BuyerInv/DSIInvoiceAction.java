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
import shahi.Action.MvxExp.BuyerInv.Beans.CommerBean;
import shahi.Action.MvxExp.BuyerInv.Beans.DimensionBean;

import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.MvxExp.Reports.PRE.bean.BEListBean;
import shahi.Action.MvxExp.BuyerInv.Beans.InvLineBeannew1;
import shahi.Action.MvxExp.Reports.PRE.bean.InvLineBeanGST;
import shahi.Action.MvxExp.Reports.PRE.bean.PreInvPrintPDFBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.ConnectionMovexBi;
import shahi.Action.database.connectiondb2;
 

public class DSIInvoiceAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    
    private String INVOICE_S;
     private String YEAR;
    private String COMPANY;
    private String INV_NO;
    private String INVOICENO;
    private String INVOICEDATE;
    
    private String BUYER;
    private String AWB_DATE;
    private String ETD_DATE;
    private String FIN_DATE;
    private String REMARK;
   
    private String BUYER_DESC;
    private String BUYER_ADDR;
    private String BUYR_ADD;
  
     
    private String FLAG1;
 
    private String currentdate;
    
    
    private String aausrid; 
    private List INVOICE_NO;
    private String saveFlag;
    
        
     
    private ByteArrayInputStream inputStream;
    
     
    
    private String PLANNO_N;
    private String TTO_DATE;
    private String TO_DATE;
 
    
    private String INV_QTY;
    private String SHIP_QTY;
    private String INVNO;
    private String E_COMP;
    private String E_YEAR;
    private String E_BUYER;
    private String LOCATION;
    private String REPORTTYPE;
    
    

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
                stat2 = conn.prepareStatement("select a.EXCS_INV_NO,to_char(a.INV_DATE,'dd/mm/yyyy') INV_DATE,a.COMPANY,a.PLAN_NO,a.BUYER,a.BUYER_ADDR,to_char(a.TTO_DATE,'dd/mm/yyyy') TTO_DATE,to_char(a.T_O_DATE,'dd/mm/yyyy') T_O_DATE,to_char(a.ETD_DATE,'dd/mm/yyyy') ETD_DATE,to_char(a.AWBDATE,'dd/mm/yyyy') AWB_DATE,to_char(a.FIN_DATE,'dd/mm/yyyy') FIN_DATE,a.INV_QTY,a.SHIP_QTY,a.BUYER_ADDR,a.year,a.LOCATION,b.REMARK from  ei_endors_mast   a left outer join  ei_other_inv   b  on(a.year=b.year and a.company=b.company and a.INV_NO=b.INV_NO  ) where a.EXCS_INV_NO=?");
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
                    REMARK=result2.getString("REMARK");
                       


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
        PreparedStatement stat3=null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        ResultSet result1 = null;
        ResultSet result3=null;
        

        try {
            
             conn=new ConnectionSeplWeb().getConnection();
             connBI=new ConnectionMovexBi().getConnection();
             conndb2=new connectiondb2().getConnection();
             List CommerBeanlist=new ArrayList();
             
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(notify) notify,agent,fwd_code,hs_code,TRIM(manuf_code) manuf_code,"
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,LOADING_port,trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,place,fwd_custom,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,a.lcno,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) tax_cal_per,"
                            +" a.transport_cost,facility,CTNS,inv_state from ei_endors_mast a  where  a.excs_inv_no=? ");
                    stat1.setString(1, INVOICE_S);
                     
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next()) 
                    {  
                        PreInvPrintPDFBean bean = new PreInvPrintPDFBean();
       
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
                        bean.setMANUF_CODE(result1.getString("MANUF_CODE"));
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
                          
                       /*    stat=conn.prepareStatement("select  OAADK2,OACONM,trim(OAADR1)||' '||trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) bsu_addr,OATAXC, OAECAR ,aRXCSN cst,aRXLSN tin,b.ARXLCN gstin  from ciaddr_M4OFF a,xinddr_M4OFF   b " +
                                                     " where oacono=111 and oaadth=4 and oaadth=aRADTH and oacono=arcono and oaadk1=aradk1 AND oaadk1=?");
                           stat.setString(1,result1.getString("manuf_code"));
                           result=stat.executeQuery();
                           if (result.next())
                           {
                            bean.setMANUF_DESC(result.getString("OACONM"));
                            bean.setMANUF_ADDRESS(result.getString("bsu_addr"));
                            bean.setMANUF_TIN(result.getString("gstin"));
                           }
                        */
                              stat=conndb2.prepareStatement("select  OAADK2,OACONM,trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,OATAXC, OAECAR ,aRXCSN cst,aRXLSN tin,arxlcn mgstin,arfre1 statecode  from m3fdbprd.ciaddr a,minfdbprd.xinddr b where oacono=111 and oaadth=1 and oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and oaadk3=? ");
                              
                          stat.setString(1,result1.getString("manuf_code"));
                             result=stat.executeQuery();
                             if (result.next())
                             {   
                                bean.setMANUF_DESC(result.getString("OACONM"));
                                bean.setMANUF_ADDRESS(result.getString("addr"));
                                bean.setMANUF_CST(result.getString("cst"));
                                bean.setMANUF_TIN(result.getString("tin"));
                                bean.setMANUF_GSTIN(result.getString("mgstin"));
                                bean.setMSTATE(result.getString("statecode")); 
                               if (result1.getString("crncy_code").equals("INR")) 
                                    
                               {  System.out.println("manus state"+result.getString("mgstin").substring(8,10));
                                   bean.setMANUF_STATE(result.getString("mgstin").substring(8,10));}
                               else{bean.setMANUF_STATE(result.getString("statecode"));}
                             }       
                          stat=conn.prepareStatement("select remark from ei_other_inv where excs_inv_no=? ");
                          stat.setString(1,INVOICE_S);
                          result=stat.executeQuery();
                          if (result.next())
                          {
                                 bean.setSHIP_DESC(result.getString("remark"));
                          }
                     
                          stat=conn.prepareStatement("select type_desc from ei_Grup_type_dtls where grup_type_code='SHT' and type_code=?");
                          stat.setString(1,result1.getString("payment_term"));
                          result=stat.executeQuery();
                          if(result.next())
                          {
                          bean.setPayment_term(result.getString("type_desc"));
                          }
                           
                             stat=conndb2.prepareStatement("select  opcunm cons_name,rtrim(OPCUA1)||'  '||rtrim(OpCUA2)||'  '||rtrim(OpCUA3)||' '||rtrim(OpCUA4)  c_address ,OPVRNO gstin,OPECAR from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and  OPCUNO=? and ltrim(rtrim(opadid))=?  ");
                              
                             stat.setString(1,result1.getString("buyer"));
                             stat.setString(2,result1.getString("cons_addr"));
                             result=stat.executeQuery();
                 
                             if (result.next())
                             {
                                bean.setCons_name(result.getString("cons_name"));
                                bean.setCons_address(result.getString("c_address"));
                                    bean.setCons_gstin(result.getString("gstin"));
                           bean.setCons_state(result.getString("gstin").substring(0,2));
                           
                            stat3=conn.prepareStatement("select state_name from seplvportal.state_master where  state_code=?");
                            stat3.setString(1,result.getString("OPECAR").trim());
                            result3=stat3.executeQuery();
                            if (result3.next())
                            {
                              bean.setCSTATE(result3.getString("state_name"));   
                            }
                                //bean.setCon_tin(result.getString("ctin"));
                             }
                              if(stat3!=null){stat3.close();}
                            if(result3!=null){result3.close();}
                           
                           
                          
                         SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yy");
                         SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy");
                       
                       
             
                       List InvLineList = new ArrayList();
                          stat2 = conn.prepareStatement(" select p.unit,p.description||' '||decode(hscode1,null,'','HS Code-'||hscode1) description,exp_rate,sum(p.qty_endors) endqty,sum(p.qty_kgs)  kgsqty,nvl(p.price_fc,0)  tprice," +
                                                        " nvl(mrp_rate,0) mrp_rate,nvl(p.price_fc,0) price_fc,nvl(price_misc,0) price_misc,nvl(transport_cost,0) transport_cost," +
                                                        " (sum(p.qty_endors) * (nvl(p.price_fc,0))) fob_fc,hsn_code,IGST_PER,CGST_PER,SGST_PER, " +
                                                        "(sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)))* exp_rate fob_inr," +
                                                        " sum((p.qty_endors * p.price_fc)-nvl(gr_decl_amt,0))*exp_rate inr_dbk,sum(p.qty_endors*nvl(price_misc,0))*exp_rate inr_misc,sum(gr_decl_amt) gr_decl "+
                                                        " from ei_endors_dtls p ,ei_endors_mast p3,ei_exchange_rate_mast ex  where p.year = p3.year and p.type = p3.type and p.company = p3.company and  p.inv_no=p3.inv_no and "+
                                                        " p.currency=ex.currency and p3.inv_date between begin_date and end_date and p.type = 'E' and p.year=? and p.company=? and p.inv_no=?  "+
                                                        " group by p.unit,p.description||' '||decode(hscode1,null,'','HS Code-'||hscode1),exp_rate,nvl(mrp_rate,0),nvl(p.price_fc,0) ,p.price_fc,p.price_misc ,nvl(transport_cost,0),hsn_code,IGST_PER,CGST_PER,SGST_PER order by 1,2");
                        stat2.setString(1, result1.getString("year")); 
                        stat2.setString(2, result1.getString("company"));
                        stat2.setString(3, result1.getString("inv_no")); 
                        result2 = stat2.executeQuery(); 
                      
                        double trcost=0.0; double miscpr=0.0;double tnetwt=0.0; double tqty=0; double tfob=0.0; double tfob1=0.0;String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double tinr=0.0; double inrconv=0.0;
                        double tgrdecl=0.0; double netfc=0.0;  double tax_a =0.0; double tax_v=0.0; double comm_amt=0.0;String mrp_desc="";
                        double tdiscnt=0.0; double line_upamt=0.0;  double tline_upamt=0.0;
                        double cgstamt_tot=0.0;    double cgstamt=0.0; double sgstamt=0.0; double igstamt=0.0;
                        double sgstamt_tot=0.0;    
                        double igstamt_tot=0.0;    
                        double xxper=0.0;  double transtax=0.0;
                         
                        
                        while (result2.next()) {  
                            
                            chkuom=result2.getString("unit");
                            if (chkuom.equals(result2.getString("unit")))
                                    { tqty=tqty+result2.getDouble("endqty");}
                            else{tqty=0;}
                            trcost=trcost+result2.getDouble("endqty")*result2.getDouble("transport_cost");
                            miscpr=miscpr+result2.getDouble("endqty")*result2.getDouble("price_misc");
                            
                            tfob=tfob+result2.getDouble("FOB_FC");
                           
                            tdiscnt=tdiscnt+result2.getDouble("gr_decl");
                            
                            tfob1=(result2.getDouble("FOB_FC")-result2.getDouble("gr_decl"));
                            line_upamt=(result2.getDouble("FOB_FC")*result1.getDouble("upcharge_per"))/100;
                            tline_upamt=tline_upamt+line_upamt;
                            tfob1=tfob1+line_upamt; 
                            
                            cgstamt=tfob1*result2.getDouble("CGST_PER")/100;
                            sgstamt=tfob1*result2.getDouble("SGST_PER")/100;
                            igstamt=tfob1*result2.getDouble("IGST_PER")/100;
                            
                            cgstamt_tot=cgstamt_tot+cgstamt ;
                            sgstamt_tot=sgstamt_tot+sgstamt;
                            igstamt_tot=igstamt_tot+igstamt;
                            
                            xxper=result2.getDouble("CGST_PER")+result2.getDouble("SGST_PER")+result2.getDouble("IGST_PER");
                            transtax=transtax+((result2.getDouble("endqty")*result2.getDouble("transport_cost"))*xxper/100);
                            
                            tnetwt=tnetwt+result2.getDouble("kgsqty");
                            tdbkinr=tdbkinr+result2.getDouble("inr_dbk");
                            
                            tmiscinr=tmiscinr+result2.getDouble("inr_misc");
                            tinr=tinr+result2.getDouble("fob_inr");
                        
                            tgrdecl=tgrdecl+result2.getDouble("gr_decl");
                            inrconv=result2.getDouble("exp_rate");  
                      
                            if (result1.getString("crncy_code").equals("INR"))
                            { 
                                if (result2.getDouble("mrp_rate")>1000.00) 
                                {  
                                    mrp_desc="Exise Duty applicable please generate Excise Invoice Rquired...." ;
                                 }else {mrp_desc="Excise Duty not Applicable for below MRP 1000 Rs.";}
                            }
                            String hsn=result2.getString("HSN_CODE");
                           
                            if (hsn!=null){
                                 String [] arr=hsn.split("-");
                                hsn= arr[0];}
                              
                         InvLineList.add(new InvLineBeanGST(result2.getString("unit"),result2.getString("description"),result2.getString("endqty"),result2.getDouble("kgsqty"), result2.getDouble("tprice"),roundTwoDecimals(result2.getDouble("FOB_FC")),roundTwoDecimals(result2.getDouble("gr_decl")),roundTwoDecimals(result2.getDouble("CGST_PER")),roundTwoDecimals(result2.getDouble("SGST_PER")),roundTwoDecimals(result2.getDouble("IGST_PER")),hsn,line_upamt,roundTwoDecimals(cgstamt),roundTwoDecimals(sgstamt),roundTwoDecimals(igstamt)));
                         cgstamt=0.0;sgstamt=0.0;igstamt=0.0;
                        }   
                        netfc=roundTwoDecimals((tfob+miscpr)-tgrdecl);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTFOB(roundTwoDecimals(tfob+miscpr)); 
                       
                        tax_v=((tline_upamt+netfc))*result1.getDouble("tax_cal_per")/100;
                         
                        tax_a=tax_v*result1.getDouble("tax_percent")/100;
                        bean.setUP_AMT(roundTwoDecimals(tline_upamt));
                        bean.setTAXABLE_VALUE(roundTwoDecimals(tax_v));
                        bean.setTAX_AMT(roundTwoDecimals(tax_a));
                          
                        bean.setTOT_FOB(roundTwoDecimals(tfob));
                        bean.setTOT_FOBDISCOUNT(roundTwoDecimals(tdiscnt));
                        bean.setTOT_AMT_CGST(roundTwoDecimals(cgstamt_tot));
                        bean.setTOT_AMT_SGST(roundTwoDecimals(sgstamt_tot));
                        bean.setTOT_AMT_IGST(roundTwoDecimals(igstamt_tot)); 
                        
                        bean.setTRANS_TAX(roundTwoDecimals(transtax));
                        bean.setTNETWT(tnetwt);   
                        bean.setTINVQTY(tqty);          
                        bean.setTINR(tinr);
                        bean.setTGRDECL(tgrdecl);       
                        bean.setGRPER(roundTwoDecimals(tgrdecl/(tfob+miscpr)*100));
                        bean.setINV_FC(netfc); 
                        comm_amt=netfc* result1.getDouble("comm_per")/100;
                        bean.setCOMM_AMT(roundTwoDecimals(comm_amt));
                           
                      if (result1.getString("crncy_code").equals("INR"))
                      {netfc=roundTwoDecimals(tax_a)+roundTwoDecimals(tax_v)+roundTwoDecimals(trcost)+roundTwoDecimals(transtax)+roundTwoDecimals(cgstamt_tot)+roundTwoDecimals(sgstamt_tot)+roundTwoDecimals(igstamt_tot);
                          System.out.println("netfc "+netfc);
                        bean.setINV_FC(roundTwoDecimals(netfc));
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
                       
                        // BPOList.add(new GetListBean(mbpo,mbpo)); 
                         //bean.setBPOLIST(BPOList);
                        bean.setBPO(mbpo);
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
                        
                 
                         stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd,OPVRNO gstin,OPECAR  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                         stat.setString(1, result1.getString("BUYER"));
                        stat.setString(2, result1.getString("BUYER_ADDR"));
                        result = stat.executeQuery();
                        if (result.next() == true) {
                           bean.setBuyer_name(result.getString("opcunm"));
                           bean.setBuyer_address(result.getString("opadd"));
                           bean.setBuyer_gstin(result.getString("gstin"));
                           bean.setBuyer_state(result.getString("gstin").substring(0,2));
                           
                            stat3=conn.prepareStatement("select state_name from seplvportal.state_master where  state_code=?");
                            stat3.setString(1,result.getString("OPECAR").trim());
                            result3=stat3.executeQuery();
                            if (result3.next())
                            {
                              bean.setBSTATE(result3.getString("state_name"));   
                            }
                            if(stat3!=null){stat3.close();}
                            if(result3!=null){result3.close();}
                           
                        //  bean.setBuyer_state(result.getString("OPECAR"));
                          
                         }   
              
                        
                       if(stat!=null){stat.close();}
                       if(result!=null){result.close();}
                        
                       
                        
                        CommerBeanlist.add(bean);
        
                    }
                    

                if (INVOICE_S!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/BuyerInv");
                    Map param1 = new HashMap();

                   
                    InputStream input;
                   
                    param1.put("SUBREPORT_DIR", path);

                    
                     input =new FileInputStream(new File(path+"/DSInvoicePrint.jrxml"));
                     
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(CommerBeanlist));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=FRLInv.pdf"); //attachment- use open new window and inline- use open in same window
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "FRLInv.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=CommerInv.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("FRLInvoice" + e.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommerInvAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommerInvAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommerInvAction.class.getName()).log(Level.SEVERE, null, ex);
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


                SimpleDateFormat fromUser  = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat myFormat  = new SimpleDateFormat("dd-MMM-yyyy");
        String date1 = null;
        String date2 = null;

        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
       

        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0;
            
            
            String yr=null;
            String comp=null;
            String invn=null;
            String byr=null;
            String byradd=null;
            String loc=null;
            
            stat5 = conn.prepareStatement("select year,COMPANY,BUYER,BUYER_ADDR,LOCATION,INV_NO from  ei_endors_mast   where EXCS_INV_NO=?");
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
            
          
            
           
            
            stat2 = conn.prepareStatement("select * from  ei_other_inv   where EXCS_INV_NO=?");
            stat2.setString(1, INVOICE_S);
            result2 = stat2.executeQuery();
            if (result2.next()) {
                stat4 = conn.prepareStatement("UPDATE  ei_other_inv set REMARK=?,tdate=sysdate WHERE EXCS_INV_NO=?");
                      stat4.setString(1,REMARK.toUpperCase());
                      stat4.setString(2,INVOICE_S);
               
                x=stat4.executeUpdate();
                 
            }
            else{
                stat4 = conn.prepareStatement("insert into  ei_other_inv  (YEAR,COMPANY,INV_NO,BUYER,BUYER_ADDR,EXCS_INV_NO,LOCATION,REMARK,TDATE) values(?,?,?,?,?,?,?,?,sysdate)");
 
                stat4.setString(1, yr);
                stat4.setString(2, comp);
                stat4.setString(3, invn);
                stat4.setString(4, byr);
                stat4.setString(5, byradd);
                stat4.setString(6, INVOICE_S);
                stat4.setString(7, loc);
                stat4.setString(8,REMARK.toUpperCase());
                y= stat4.executeUpdate();
                 
               }

                
                    
            if (x > 0) {
                 conn.commit();
                 REMARK=null;
                 
                addActionMessage("Record Updated succcessfully ");
                 
               
            }
            if (y > 0) {
                 conn.commit();
                 REMARK=null;
                 
                addActionMessage("Record Inserted succcessfully ");
              
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
             System.out.println("FRLInvoice" + e.toString());
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

    

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

   

    public List getINVOICE_NO() {
        return INVOICE_NO;
    }

    public void setINVOICE_NO(List INVOICE_NO) {
        this.INVOICE_NO = INVOICE_NO;
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

   
   

     
    public String getBUYR_ADD() {
        return BUYR_ADD;
    }

    public void setBUYR_ADD(String BUYR_ADD) {
        this.BUYR_ADD = BUYR_ADD;
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

    public String getETD_DATE() {
        return ETD_DATE;
    }

    public void setETD_DATE(String ETD_DATE) {
        this.ETD_DATE = ETD_DATE;
    }

     

    public String getFLAG1() {
        return FLAG1;
    }

    public void setFLAG1(String FLAG1) {
        this.FLAG1 = FLAG1;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
 
     
    
}