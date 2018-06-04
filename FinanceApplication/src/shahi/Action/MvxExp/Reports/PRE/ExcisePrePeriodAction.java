/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.MvxExp.Reports.PRE.bean.BEListBean;
import shahi.Action.MvxExp.Reports.PRE.bean.ExcisePrePeriodBean;
import shahi.Action.MvxExp.Reports.PRE.bean.InvLineBean;
import shahi.Action.MvxExp.Reports.PRE.bean.PreInvPrintPDFBean;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionMovexBi;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author User
 */ 
public class ExcisePrePeriodAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
      private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private String REPORTTYPE;
    private String aausrid;
    private String LOCATIONNO;
    private List LOCLIST = new ArrayList();
    private String INVNO;
    private String ReportT="2";
    
   
     
    @Override
   
    public String execute() throws SQLException {
        
        try{
            EisUtil pisdate = new EisUtil();
            //currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        } 
        

        Connection conn = null;
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
             List ExcisePrePeriodBeans=new ArrayList();
             
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(inv_date,'dd/mm/yyyy') inv_date,a.exp_type,nvl(a.self_tp,'N') self_tp,trim(notify) notify,agent,fwd_code,hs_code,TRIM(manuf_code) manuf_code,"
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,rpad(a.buyer,10,' ') buyer,rpad(a.buyer_addr,6,' ') buyer_addr,a.cons_addr,LOADING_port,trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,place,fwd_custom,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,a.crncy_code,a.lcno,a.oh_work outhouse,DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) tax_cal_per,"
                            +" a.transport_cost,facility,CTNS from ei_endors_mast a  where  a.excs_inv_no=? ");
                    stat1.setString(1, INVNO);
                     
                    result1 = stat1.executeQuery();
                    String invq="";
                    if (result1.next()) 
                    {  
                        ExcisePrePeriodBean bean = new ExcisePrePeriodBean();
       
                        
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
                        bean.setTRANS_COST(result1.getDouble("transport_cost"));
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
                          
                            stat=conn.prepareStatement("select to_char(ship_date,'dd/mm/yyyy') lc_date,quality lc_no,dm_code inbond_no from ei_other_inv where excs_inv_no=? ");
                            stat.setString(1,INVNO);
                            result=stat.executeQuery();
                            if (result.next())
                            {  
                                bean.setLCNO(result.getString("LC_NO"));  
                                bean.setLCDATE(result.getString("LC_DATE"));  
                                bean.setINBOND(result.getString("INBOND_NO"));   
                            }
                           
                          
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
                           
                         List EXCISEList = new ArrayList(); 
                         SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yy");
                         SimpleDateFormat dateFormat2=new SimpleDateFormat("dd/MM/yyyy");
                        double mrp_rate=0.0;double cal_amt=0.0; double ex_qty=0; double bed_amt=0.0;
                         PreparedStatement   stat4 = conn.prepareStatement("select mrp_rate,sum(qty_endors) qty,((sum(qty_endors)*mrp_rate)*cal_value)/100 cal_amt,(((sum(qty_endors)*mrp_rate)*cal_value)/100)*cenvat/100 bed from ei_endors_dtls a,ei_excise_duty_mast b where nvl(qty_endors,0)>0 and ? between date_from and date_to and a.all_no=? and mrp_rate>=1000 group by mrp_rate,cal_value,cenvat");
                         stat4.setString(1, dateFormat.format(dateFormat2.parse(result1.getString("inv_date"))));
                         stat4.setString(2, INVNO);
                         ResultSet result4 = stat4.executeQuery();
                         while (result4.next() == true) {
                               
                            cal_amt=cal_amt+result4.getDouble("cal_amt"); 
                            bed_amt=bed_amt+result4.getDouble("bed");
                               
                           EXCISEList.add(new InvLineBean(result4.getString("qty"),result4.getString("mrp_rate"),result4.getString("mrp_rate"),result4.getDouble("qty"), result4.getDouble("cal_amt"),roundTwoDecimals(result4.getDouble("bed")) )); 
                      
                         /*  bean.setMRP_AMT(result4.getDouble("mrp_rate"));
                           bean.setCAL_VAL(result4.getInt("cal_value"));
                           bean.setINV_RATE(result4.getDouble("inv_rate"));
                           bean.setINV_DESC(result4.getString("inv_desc"));
                           bean.setUNIT(result4.getString("unit"));
                          */ 
                             
                         }
                        bean.setEXCISELIST(EXCISEList);
             
                          List InvLineList = new ArrayList();
                          PreparedStatement stat2=null;
                             stat2 = conn.prepareStatement("select p.unit,p.description||' '||p1.hscode description,exp_rate,sum(p.qty_endors) endqty,sum(p.qty_kgs) kgsqty,nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) tprice," +
                                                        " nvl(p.price_fc,0) price_fc,nvl(price_misc,0) price_misc,nvl(transport_cost,0) transport_cost," +
                                                        " (sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0))) fob_fc, " +
                                                        "(sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0)))* exp_rate fob_inr," +
                                                        " sum((p.qty_endors * p.price_fc)-nvl(gr_decl_amt,0))*exp_rate inr_dbk,sum(p.qty_endors*nvl(price_misc,0))*exp_rate inr_misc,sum(gr_decl_amt) gr_decl "+
                                                        " from ei_endors_dtls p ,ei_endors_hs p1,ei_endors_mast p3,ei_exchange_rate_mast ex  where p.year = p3.year and p.type = p3.type and p.company = p3.company and  p.inv_no=p3.inv_no and "+
                                                        " p.year=p1.year(+) and p.company=p1.company(+) and p.inv_no=p1.inv_no(+) and  p.co_no=p1.co_no(+) and p.co_line=p1.co_line(+) and "+
                                                        " p.currency=ex.currency and p3.inv_date between begin_date and end_date and p.type = 'E' and p.year=? and p.company=? and p.inv_no=?  "+
                                                        " group by p.unit,p.description||' '||p1.hscode,exp_rate,nvl(p.price_fc,0) + nvl(p.price_misc,0)-nvl(transport_cost,0) ,p.price_fc,p.price_misc ,nvl(transport_cost,0) order by 1,2");
                          
                        stat2.setString(1, result1.getString("year"));
                        stat2.setString(2, result1.getString("company"));
                        stat2.setString(3, result1.getString("inv_no")); 
                   
                       ResultSet  result2 = stat2.executeQuery(); 
                  
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
                        
                            //excise_d=((Double.parseDouble(invq)*result4.getDouble("mrp_rate"))*result4.getInt("cal_value")/100)*2/100;
                          excise_d=roundTwoDecimals(bed_amt);
                        tax_v=((up+tfob+excise_d)-tgrdecl)*result1.getDouble("tax_cal_per")/100;
                           
                        tax_a=(tax_v+trcost)*result1.getDouble("tax_percent")/100;
                        tot_inv=roundTwoDecimals(tfob+up+excise_d+tax_a);
                        bean.setUP_AMT(roundTwoDecimals(up));
                        bean.setTAXABLE_VALUE(roundTwoDecimals(tax_v));
                        bean.setTAX_AMT(roundTwoDecimals(tax_a));
                         bean.setTNETWT(tnetwt);   
                        bean.setTINVQTY(tqty);          
                        bean.setTINR(tinr);  
                        bean.setTGRDECL(tgrdecl);       
                        bean.setGRPER(roundTwoDecimals(tgrdecl/tfob*100));
                        bean.setINV_FC(netfc); 
                        bean.setTOTAL_INV(tot_inv);
                        bean.setEXCISE_DUTY(excise_d);
                        comm_amt=netfc* result1.getDouble("comm_per")/100;
                        bean.setCOMM_AMT(roundTwoDecimals(comm_amt));
                        bean.setTR_COST(trcost);
                      if (result1.getString("crncy_code").equals("INR"))
                      {netfc=roundTwoDecimals(tax_a+tax_v+trcost);
                      tot_inv=roundTwoDecimals(tfob+up+excise_d+tax_a+trcost);
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
                                { 
                                    wordfc=wordfc+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                    
                                }else{wordfc=wordfc+" Only.";}
                                    
                            bean.setAMT_IN_WORD(wordfc);
                       
                        
                
                        String wordEX_DUTY="";        
                        stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                        stat.setDouble(1,excise_d);
                        stat.setDouble(2,excise_d);
                        stat.setDouble(3,excise_d); 
                        result=stat.executeQuery();
                        if (result.next())
                        {   wordEX_DUTY=result.getString("aa");
                             
                                if (result.getString("aa1")!=null)
                                {  
                                    wordEX_DUTY=wordEX_DUTY+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                }
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
              
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER"));
                       stat.setString(2, result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setCons_name(result.getString("opcunm"));
                           bean.setCons_address(result.getString("opadd"));
                        }
                       
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER"));
                       stat.setString(2, result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setCons_name(result.getString("opcunm"));
                           bean.setCons_address(result.getString("opadd"));
                        }
                       
                       
                        
                        ExcisePrePeriodBeans.add(bean);
        
                    }
                    

                if (INVNO!=null) {
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/PRE");
                    Map param1 = new HashMap();

                   
                    InputStream input;
                    param1.put("SUBREPORT_DIR", path); 

                    if(ReportT.equals("1") && ReportT!=null){
                        input =new FileInputStream(new File(path+"/ExcisePrePeriod.jrxml"));
                    }
                    else if(ReportT.equals("2") && ReportT!=null){
                        input=new FileInputStream(new File(path+"/CommercialInvoice.jrxml"));
                    }
                    else
                    {
                        input=new FileInputStream(new File(path+"/BabiesgarmentsInvoice.jrxml"));
                    }
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);


                    JasperPrint print = JasperFillManager.fillReport(rep, param1, new JRBeanCollectionDataSource(ExcisePrePeriodBeans));

                    
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=ExcisePrePeriod.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
             
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "ExcisePrePeriod.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=ExcisePrePeriod.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }
                    

        } catch (Exception e) {
            System.out.println("ExcisePrePeriodAction" + e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (connBI != null) {
                connBI.close();
            }
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
        return SUCCESS;
    }
    
    
    public String clear(){
        
        INVNO="";
        
        return "clr";
    }
    
     double roundFourDecimals(double d) {
            DecimalFormat fourDForm = new DecimalFormat("#######.####");
        return Double.valueOf(fourDForm.format(d));
}   
  
    double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
   
    public String getLOCATIONNO() {
        return LOCATIONNO;
    }

    public void setLOCATIONNO(String LOCATIONNO) {
        this.LOCATIONNO = LOCATIONNO;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public List getLOCLIST() {
        return LOCLIST;
    }

    public void setLOCLIST(List LOCLIST) {
        this.LOCLIST = LOCLIST;
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

    public String getREPORTTYPE() {
        return REPORTTYPE;
    }

    public void setREPORTTYPE(String REPORTTYPE) {
        this.REPORTTYPE = REPORTTYPE;
    }

    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getReportT() {
        return ReportT;
    }

    public void setReportT(String ReportT) {
        this.ReportT = ReportT;
    }
    
    
    
}
