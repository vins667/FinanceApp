/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.PRE;
 
import com.opensymphony.xwork2.ActionSupport;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;
import shahi.Action.MvxExp.Reports.PRE.bean.BEListBean;
import shahi.Action.MvxExp.Reports.PRE.bean.InvLineBean;
import shahi.Action.MvxExp.Reports.PRE.bean.InvLineBeanGST;
import shahi.Action.MvxExp.Reports.PRE.bean.PreInvPrintPDFBean;
import shahi.Action.MvxExp.Reports.PRE.bean.MISCBEAN;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionMovexBi;
import shahi.Action.database.connectiondb2;

/**
 * 
 * @author Guddu 
 */
public class PreInvPrintParameterAction extends ActionSupport{
     public List<PreInvPrintPDFBean> getRecord(String str1,String RepType) { 
         List<PreInvPrintPDFBean> preInvPrintBeans = new ArrayList<PreInvPrintPDFBean>();
        try{
            EisUtil pisdate = new EisUtil();
            //currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        } 
         
        int flag = 0;
    //    Map session = ActionContext.getContext().getSession();
//        String LOCATION_CODE = (String) session.get("sessLocationCode");
  //      String usrid = (String) session.get("sessUserId");
             Connection conn = null;
            Connection connBI = null;
             Connection conndb2 = null;
            try {
                conn = new connection().getConnection();
                conndb2 = new connectiondb2().getConnection();     
            //    conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            try {
                connBI = new ConnectionMovexBi().getConnection();
               // connBI.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
        
        try { 

            
            PreparedStatement stat = null;
            PreparedStatement stat1 = null;
            PreparedStatement stat2 = null;
            PreparedStatement stat3 = null;
            
            ResultSet result = null;
            ResultSet result1 = null; 
            ResultSet result2 = null;
            ResultSet result3 = null;
            
            try { 
               Date todaysDate = new Date();
               int a=0; 
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("str "+str1);
                      stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,"
                              + "to_char(inv_date,'dd/mm/yyyy') inv_date,to_char(inv_date,'yyyymmdd') idate,a.exp_type,nvl(a.self_tp,'N') self_tp,"
                              + "trim(notify) notify,agent,fwd_code,hs_code,TRIM(manuf_code) manuf_code,"
                            + " a.cost_centre,a.mode_of_ship,a.inv_qty,a.buyer,trim(a.buyer_addr) buyer_addr,trim(a.cons_addr) cons_addr,LOADING_port,"
                              + "trim(LOADING) CLR_port,pre_carriage,upcharge_per,comm_per,payment_term,ship_term,pay_term,"
                              + "place,fwd_custom,DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,"
                              + "nvl(a.first_sale,'N') ship_type,a.crncy_code,a.lcno,a.oh_work outhouse,mmitgr,"
                              + "DECODE(A.surrender_yn,'Y','YES','NO') ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,"
                              + "a.manuf_state,manuf_code,a.tax_type,a.tax_percent,a.tax_code,nvl(a.tax_cal_per,100) "
                              + "tax_cal_per,a.transport_cost,facility,CTNS,netwt,LUT_IGST  from ei_endors_mast a  where  1=1"+str1);
                          //    + "a.excs_inv_no =? ");
                          // stat1.setString(1,excs_inv_no);
                    result1 = stat1.executeQuery();   
                    while(result1.next()) 
                    {  
                      
                        PreInvPrintPDFBean bean = new PreInvPrintPDFBean();
        
                        bean.setLocation(result1.getString("location"));
                        bean.setExcs_inv_no(result1.getString("excs_inv_no"));
                        bean.setPlan_no(result1.getString("plan_no"));
                        bean.setInv_date(result1.getString("inv_date"));
                        bean.setINVYR(result1.getInt("idate"));
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
                        bean.setNETWT(result1.getDouble("NETWT"));
                        bean.setFACILITY(result1.getString("facility"));
                        bean.setLUT_IGST(result1.getString("LUT_IGST"));
                        bean.setYEAR(result1.getString("year"));
                        bean.setCOMPANY(result1.getString("company"));
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
                          //  bean.setLOADING_PORT_DESC(bn.getUNIT_ADDRESS()); 
                            bean.setLOADING_PORT_DESC(bn.getUNIT_DESC());  
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("CLR_PORT"),"HAFE");
                          //bean.setCLR_PORT_DESC(bn.getUNIT_ADDRESS());
                          bean.setLOADING_PORT_DESC(bn.getUNIT_DESC()); 
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("DISCHARGE"),"SDST");
                          bean.setDISCHARGE_DESC(bn.getUNIT_DESC());
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("PLACE"),"EDES");
                          bean.setPLACE_DESC(bn.getUNIT_DESC());
                          
                          bn=new  PreInvoiceDao().getCsytabBeanByName(result1.getString("exp_type"),"PRGP");
                         
                           if (result1.getString("exp_type").equals("GMT") && result1.getString("mmitgr").equals("GAOG0005"))
                            {bean.setExp_type_desc("MADE-UPS");
                            }
                          else if  (result1.getString("exp_type").equals("GMT") && !result1.getString("mmitgr").equals("GAOG0005"))
                            {bean.setExp_type_desc("READY MADE GARMENTS");
                            }
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
                          
                           stat=conndb2.prepareStatement("select '0'||okagbg  okagbg from m3fdbprd.ocusma where okcono=111 and okagbg>0 and okcuno=?");
                          stat.setString(1,result1.getString("BUYER").trim());
                          result=stat.executeQuery();
                          if(result.next())
                          {  
                            bean.setAD_CODE(result.getString("okagbg"));
                          }
                              
                          
                          stat=conn.prepareStatement("select type_desc from ei_Grup_type_dtls where grup_type_code='SHT' and type_code=?");
                          stat.setString(1,result1.getString("payment_term"));
                          result=stat.executeQuery();
                          if(result.next())
                          {
                            bean.setPayment_term(result.getString("type_desc"));
                          }
                          
                          
//                          if (result1.getString("crncy_code").equals("INR"))
//                          {
                            
                          //  stat=conn.prepareStatement("select  OAADK2,OACONM,trim(OAADR1)||' '||trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,OATAXC, OAECAR ,aRXCSN cst,aRXLSN tin,arxlcn gstin,arfre1 statecode  from seplweb.ciaddr_VIEW115 a,seplweb.xinddr_view115 b where oacono=111 and oaadth=1 and oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and oaadk3=? ");
                          //trim(OAADR1)||' '||   
                          stat=conndb2.prepareStatement("select  OAADK2,OACONM,trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,OATAXC, OAECAR ,aRXCSN cst,aRXLSN tin,arxlcn gstin,arfre1 statecode  from m3fdbprd.ciaddr a,minfdbprd.xinddr b where oacono=111 and oaadth=1 and oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and trim(oaadk3)=? ");
                             stat.setString(1,result1.getString("manuf_code"));
                             result=stat.executeQuery();
                             if (result.next())
                             {  
                                bean.setMANUF_DESC(result.getString("OACONM"));
                                bean.setMANUF_ADDRESS(result.getString("addr"));
                                bean.setMANUF_CST(result.getString("cst"));
                                bean.setMANUF_TIN(result.getString("tin"));
                                bean.setMANUF_GSTIN(result.getString("gstin"));
                                bean.setMANUF_STATE(result.getString("statecode"));
                             }  
                            
                          //    stat=conn.prepareStatement("select  opcunm cons_name,rtrim(OPCUA1)||'  '||rtrim(OpCUA2)||'  '||rtrim(OpCUA3)||' '||rtrim(OpCUA4)  c_address ,OPVRNO , trim(CAXLSN) ctin, trim(CAXCSN) ccst from  ocusad_view115 b, xcusad_view d where OPCONO = 111 AND OPADRT=1 and   OPCONO = CACONO(+)  AND OPCUNO  = CACUNO (+)  AND OPADRT = CAADRT(+)  AND OPADID = CAADID(+) and OPCUNO=? and ltrim(rtrim(opadid))=?  ");
                           //    stat=connBI.prepareStatement("select  opcunm cons_name,rtrim(OPCUA1)||'  '||rtrim(OpCUA2)||'  '||rtrim(OpCUA3)||' '||rtrim(OpCUA4)  c_address ,OPVRNO from  prodbi.ocusad b where OPCONO = 111 AND OPADRT=1 and  OPCUNO=? and ltrim(rtrim(opadid))=?  ");
                               stat=conndb2.prepareStatement("select  opcunm cons_name,rtrim(OPCUA1)||'  '||rtrim(OpCUA2)||'  '||rtrim(OpCUA3)||' '||rtrim(OpCUA4)  c_address ,OPVRNO from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and  OPCUNO=? and ltrim(rtrim(opadid))=?  ");
                              
                             stat.setString(1,result1.getString("buyer"));
                             stat.setString(2,result1.getString("cons_addr"));
                             result=stat.executeQuery();
                 
                             if (result.next())
                             {
                                bean.setCons_name(result.getString("cons_name"));
                                bean.setCons_address(result.getString("c_address"));
                             //   bean.setCon_cst(result.getString("ccst")); 
                             //   bean.setCon_tin(result.getString("ctin"));
                             }
                             
                          //} 
              
                            
                          List InvLineList = new ArrayList();
                          stat2 = conn.prepareStatement(" select p.unit,p.description||' '||decode(hscode1,null,'','HS Code-'||hscode1) description,hsn_code,exp_rate,sum(p.qty_endors) endqty,sum(p.qty_kgs)  kgsqty,nvl(p.price_fc,0) -nvl(transport_cost,0) tprice," +
                                                        " nvl(mrp_rate,0) mrp_rate,nvl(p.price_fc,0) price_fc,nvl(price_misc,0) price_misc,nvl(transport_cost,0) transport_cost," +
                                                        " (sum(p.qty_endors) * (nvl(p.price_fc,0)-nvl(transport_cost,0))) fob_fc, " +
                                                        "(sum(p.qty_endors) * (nvl(p.price_fc,0)+nvl(price_misc,0)-nvl(transport_cost,0)))* exp_rate fob_inr," +
                                                        " sum((p.qty_endors * p.price_fc)-nvl(gr_decl_amt,0))*exp_rate inr_dbk,sum(p.qty_endors*nvl(price_misc,0))*exp_rate inr_misc,sum(gr_decl_amt) gr_decl "+
                                                        " from ei_endors_dtls p , ei_endors_mast p3,ei_exchange_rate_mast ex  where p.year = p3.year and p.type = p3.type and p.company = p3.company and  p.inv_no=p3.inv_no and "+
                                                        " p.currency=ex.currency and nvl(trunc(fwd_custom),inv_date) between begin_date and end_date and p.type = 'E' and p.year=? and p.company=? and p.inv_no=?  "+
                                                        " group by p.unit,p.description||' '||decode(hscode1,null,'','HS Code-'||hscode1),nvl(mrp_rate,0),hsn_code,exp_rate,nvl(p.price_fc,0) -nvl(transport_cost,0) ,p.price_fc,p.price_misc ,nvl(transport_cost,0) order by 1,2");
                        stat2.setString(1, result1.getString("year"));
                        stat2.setString(2, result1.getString("company"));
                        stat2.setString(3, result1.getString("inv_no")); 
                        result2 = stat2.executeQuery();  
                     
                        double trcost=0.0;double tnetwt=0.0;double miscpr=0.0; double tqty=0; double tfob=0.0; String chkuom="";double tdbkinr=0; double tmiscinr=0.0; double tinr=0.0; double inrconv=0.0;
                        double tgrdecl=0.0; double netfc=0.0;  double up=0.0; double tax_a =0.0; double tax_v=0.0; double comm_amt=0.0; String mrp_desc="";
                        BigDecimal g1 = new BigDecimal("0.00");
                        
                        while (result2.next()) {  
                            chkuom=result2.getString("unit");
                            if (chkuom.equals(result2.getString("unit")))
                                    { tqty=tqty+result2.getDouble("endqty");}
                            else{tqty=0;}
                            trcost=trcost+result2.getDouble("endqty")*result2.getDouble("transport_cost");
                            miscpr=miscpr+result2.getDouble("endqty")*result2.getDouble("price_misc");
                            tfob=tfob+result2.getDouble("FOB_FC");
                            tnetwt=tnetwt+result2.getDouble("kgsqty");
                            tdbkinr=tdbkinr+result2.getDouble("inr_dbk");
                            
                            tmiscinr=tmiscinr+result2.getDouble("inr_misc");
                            tinr=tinr+result2.getDouble("fob_inr");
                            
                            g1=g1.add(result2.getBigDecimal("gr_decl"));
                            
                            tgrdecl=tgrdecl+result2.getDouble("gr_decl");
                            inrconv=result2.getDouble("exp_rate");  
                           /* if (result1.getString("crncy_code").equals("INR"))
                            { 
                            
                                if (result2.getDouble("mrp_rate")>1000.00) 
                                {  
                                    mrp_desc="Exise Duty applicable please generate Excise Invoice Rquired...." ;
                                 }else {mrp_desc="Excise Duty not Applicable for below MRP 1000 Rs.";}
                            }
                            */
                             String hsn=result2.getString("HSN_CODE");
                           
                            if (hsn!=null){
                                 String [] arr=hsn.split("-");
                                hsn= arr[0];}
                             InvLineList.add(new InvLineBeanGST(result2.getString("unit"),result2.getString("description"),result2.getString("endqty"),result2.getDouble("kgsqty"), result2.getDouble("tprice"),roundTwoDecimals(result2.getDouble("FOB_FC")),0,0.0,0.0,0.0,hsn,0.0,0.0,0.0,0.0));
                        }    
                        netfc=roundTwoDecimals((tfob+miscpr)-tgrdecl);
                        bean.setINVLINELIST(InvLineList);
                        bean.setTFOB(roundTwoDecimals(tfob+miscpr)); 
                        
                        up=netfc*result1.getDouble("upcharge_per")/100;
                             
                        tax_v=((up+netfc))*result1.getDouble("tax_cal_per")/100;
                        
                        tax_a=tax_v*result1.getDouble("tax_percent")/100;
                        bean.setUP_AMT(roundTwoDecimals(up));
                        bean.setTAXABLE_VALUE(roundTwoDecimals(tax_v));
                        bean.setTAX_AMT(roundTwoDecimals(tax_a));
                         
                        bean.setTNETWT(tnetwt);   
                        bean.setTINVQTY(tqty);          
                         
                        bean.setTINR(tinr-(tgrdecl*inrconv)+up);
                        bean.setTGRDECL(tgrdecl);       
                        bean.setGRPER(roundTwoDecimals(tgrdecl/(tfob+miscpr)*100));
                        bean.setINV_FC(netfc); 
                        comm_amt=netfc* result1.getDouble("comm_per")/100;
                        bean.setCOMM_AMT(roundTwoDecimals(comm_amt));
                        
                      if (result1.getString("crncy_code").equals("INR"))
                      {netfc=roundTwoDecimals(tax_a+tax_v+trcost);
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
                          bean.setBPO(mbpo);
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
                        bean.setSTYLE(msty); 
                        StyList.add(new GetListBean(msty,msty));
                         bean.setSTYLIST(StyList);
                           bean.setSTYLE(msty);   
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
                         bean.setPDBK(mdbk);  
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
                        String mrosl="";
                        stat = conn.prepareStatement("select distinct trim(rosl_slno) rosl_slno from ei_endors_dtls where rosl_slno is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mrosl=mrosl+result.getString("rosl_slno")+",";
                        } 
                                  
                        if (mrosl!=""){
                          mrosl=mrosl.substring(0, mrosl.length()-1);}
                    
                        bean.setROSL(mrosl); 
                         
                        String mscheme="";
                        stat = conn.prepareStatement("select distinct trim(scheme_code) scheme from ei_endors_dtls where scheme_code is not null and year=? and company=? and inv_no=? " );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  mscheme=mscheme+result.getString("scheme")+",";
                        } 
                        if (mscheme!=""){
                          mscheme=mscheme.substring(0, mscheme.length()-1);}
                         bean.setSCHEME_CODE(mscheme); 
                         
                        
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
                        bean.setPSTR(mstr);
                                 
                        List HngrList = new ArrayList();
                      //  stat = conn.prepareStatement("select  sum(qty_endors)||'  HANGERS  @ ' ||RTRIM(NVL(PRICE_MISC,0)||'0   Per Pcs.')  hh from ei_endors_dtls where  year=? and company = ?  and inv_no =?  and nvl(price_misc,0)<>0 group by price_misc" );
                          stat = conn.prepareStatement("select  'HANGERS   '  hngr_desc,nvl(hngr_cost,0) hngr_cost,sum(qty_endors) hngr_qty,sum(qty_endors*nvl(hngr_cost,0)) hngr_fob from ei_endors_dtls where  year=? and company = ?  and inv_no =?  and nvl(hngr_cost,0)<>0 group by hngr_cost" );
                          stat.setString(1, result1.getString("year"));
                            stat.setString(2, result1.getString("company"));
                            stat.setString(3, result1.getString("inv_no"));
                            result = stat.executeQuery();
                            while(result.next())
                            {  
                               HngrList.add(new MISCBEAN(result.getString("hngr_desc"),result.getString("hngr_qty"),result.getDouble("hngr_cost"),result.getDouble("hngr_fob")));      
                            }
                           stat = conn.prepareStatement("select  ' TAGS   '  tag_desc,nvl(tag_cost,0) tag_cost,sum(qty_endors) tag_qty,sum(qty_endors*nvl(tag_cost,0)) tag_fob from ei_endors_dtls where  year=? and company = ?  and inv_no =?  and nvl(tag_cost,0)<>0 group by tag_cost" );
                            stat.setString(1, result1.getString("year"));
                            stat.setString(2, result1.getString("company"));
                            stat.setString(3, result1.getString("inv_no"));
                            result = stat.executeQuery();
                            while(result.next())
                            {    
                                 HngrList.add(new MISCBEAN(result.getString("tag_desc"),result.getString("tag_qty"),result.getDouble("tag_cost"),result.getDouble("tag_fob")));      
                         }
                        
                        List AccrList = new ArrayList(); 
                        double accrfob=0.0;   int chkdbkaccr=0;
                        stat = conn.prepareStatement("select sum(ACCR_QTY)||' '||TRIM(ACCR_DESC)||' @ ' ||LTRIM(TO_CHAR(ACCR_PRICE,'99.99')||' Per Pcs.')  hh,sum(accr_qty*accr_price) accr_fob from ei_endors_ACCR_dtls where  year =? and company =? and inv_no =?  and nvl(ACCR_PRICE,0)<>0  group by year,company,inv_no,ACCR_PRICE,ACCR_DESC order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  accrfob=accrfob+result.getDouble("accr_fob");
                          
                           HngrList.add(new MISCBEAN(result.getString("hh"),"",0.0,0.0));  
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
                      // System.out.println("test-14");            
                    
                        accrfob=accrfob*inrconv;
                        accrdbkfob=accrdbkfob*inrconv;
                        tmiscinr=tmiscinr+accrfob-accrdbkfob;
                        bean.setHNGRLIST(HngrList);
                        bean.setTMISCINR(tmiscinr);  
                          
                        tdbkinr=tdbkinr-tmiscinr; 
                        
                         bean.setTDBKINR(tdbkinr);  
                        bean.setCHKDBKACCR(chkdbkaccr);
                        
                        List AccrdbkList = new ArrayList();
                        String accrdbk="";
                        stat = conn.prepareStatement("select  distinct accr_desc,accr_dbkslno from ei_endors_ACCR_dtls where year=? and company = ? and inv_no =?  and accr_dbkslno is not null order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {
                            accrdbk=accrdbk+result.getString("accr_dbkslno")+",";
                           AccrdbkList.add(new GetListBean(result.getString("accr_desc"),result.getString("accr_dbkslno")));      
                        }
                         if (accrdbk!=""){   
                        accrdbk=accrdbk.substring(0, accrdbk.length()-1);}
                    
                         bean.setPACCRDBK(accrdbk);;
                         bean.setACCRDBKLIST(AccrdbkList);
                         
                                
                         List AccrstrList = new ArrayList(); int chkstraccr=0; String accrstr="";
                        stat = conn.prepareStatement("select  distinct accr_desc,accr_strslno from ei_endors_ACCR_dtls where year=? and company = ? and inv_no =?  and accr_strslno is not null order by accr_desc" );
                        stat.setString(1, result1.getString("year"));
                        stat.setString(2, result1.getString("company"));
                        stat.setString(3, result1.getString("inv_no"));
                        result = stat.executeQuery();
                        while(result.next())
                        {  chkstraccr=chkstraccr+1;
                           accrstr=accrstr+result.getString("accr_strslno")+",";
                           AccrstrList.add(new GetListBean(result.getString("accr_desc"),result.getString("accr_strslno")));      
                        }
                        if (accrstr!=""){ 
                            accrstr=accrstr.substring(0, accrstr.length()-1);}
                    
                         bean.setPACCRSTR(accrstr);
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
                           
                                
                        stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                        stat.setString(1, result1.getString("BUYER"));
                        stat.setString(2, result1.getString("BUYER_ADDR"));
                        result = stat.executeQuery();
                        if (result.next() == true) {
                          bean.setBuyer_name(result.getString("opcunm"));
                          bean.setBuyer_address(result.getString("opadd"));
                         }   
              
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1  and opcuno=? and opadid=? ");
                       stat.setString(1, result1.getString("BUYER"));
                       stat.setString(2, result1.getString("CONS_ADDR"));
                       result = stat.executeQuery();
                       if (result.next() == true) {
                            
                           bean.setCons_name(result.getString("opcunm"));
                           bean.setCons_address(result.getString("opadd"));
                        }
                        
                        
                         EisUtil eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("EDES",result1.getString("DESTI_CODE"));
                         while(result.next())
                         { 
                             bean.setDESTI_CODE_DESC(result.getString("cttx40"));} 
                         eisutil.closeConnection();
                          eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("CSCD",result1.getString("DESTI_CNTRY"));
                         while(result.next())
                         { bean.setDESTI_CNTRY_DESC(result.getString("cttx40"));}  
                           eisutil.closeConnection(); 
                            
                         stat=conndb2.prepareStatement("select sasunm ,trim(saadr1)||' '||rtrim(saadr2)||' '||rtrim(saadr3)||' '||rtrim(saadr4) noticity from  m3fdbprd.cidadr where  sacono=111 and trim(sasuno)=? and SAADTE='1' and saadid IN ('001','01') "); 
                          stat.setString(1,result1.getString("notify"));
                          result=stat.executeQuery();
                          if (result.next() == true) 
                        {  
                          bean.setNOTIFY_NAME(result.getString("sasunm"));
                          bean.setNOTIFY_ADDRESS(result.getString("noticity"));
                        }
                           stat = conn.prepareStatement("select DECL_TEXT from EI_LICENCE_DECL where year=? and company=? and inv_no=? " );
                           stat.setString(1, result1.getString("year"));
                           stat.setString(2, result1.getString("company"));
                           stat.setString(3, result1.getString("inv_no"));
                           result = stat.executeQuery();
                        if (result.next() == true) 
                        {  
                        bean.setLIC_DECL_TEXT(result.getString("DECL_TEXT"));  
                        }
                          String ERR_MSG1="";  String msg1="";  String msg2="";
                          if (result1.getString("ship_type").trim().equals("F"))
                          { ERR_MSG1=" Check invoice Company... First Sale invoice.... Not Good for Printout... ";
                          } 
                       /*   stat = conn.prepareStatement("select * from EI_country_mast where country=?   " );
                          stat.setString(1, result1.getString("desti_cntry").trim());
                          result = stat.executeQuery();
                          if (result.next() == false) 
                          {
                             ERR_MSG1="Destination Contry Not Link with Oralce Cntry Master "+result1.getString("desti_cntry");
                          }
                          stat = conn.prepareStatement("select * from EI_country_mast where country=?   " );
                          stat.setString(1, result1.getString("dis_cntry").trim());
                          result = stat.executeQuery();
                          if (result.next() == false) 
                          {
                             ERR_MSG1=ERR_MSG1+"Discharge Contry Not Link with Oralce Cntry Master"+result1.getString("dis_cntry");
                          }
                       */ 
                          stat = conn.prepareStatement("select port_code from ei_bos_transit where port_code=?   " );
                          stat.setString(1, result1.getString("LOADING_PORT").trim());
                          result = stat.executeQuery();
                          if (result.next() == false) 
                          {
                            ERR_MSG1=ERR_MSG1+"Port of Loading Not Link with Oralce Port Master ->"+result1.getString("LOADING_PORT");
                          }
                          bean.setERR_MSG(ERR_MSG1);
                          if (result1.getString("self_tp").equals("F"))
                          {
                            msg1="SAMPLES OF NO COMMMERCIAL VALUE. VALUE DECLARED FOR CUSTOMS PURPOSE ONLY. THIS IS NOT MEANT FOR SALE";
                          }
                           if (result1.getString("self_tp").equals("S")){  
                                    msg1="SAMPLE SHIPMENT";
                           }
                           
                           msg1=msg1+mrp_desc;
                           bean.setMSG1(msg1); 
                        
                       /*    if (result1.getString("buyer").substring(0,5).equals("ESPRI"))
                           {   
                              if (result1.getString("CLR_PORT").trim().equals("NDL-ACF") ||  result1.getString("CLR_PORT").trim().equals("PIYALA"))
                              {   
                                   msg2="WE HAVE USED OUR BEST EFFORTS TO ENSURE THAT NO  CHILD LABOUR  IS INVOLVED AND TO THE BEST OF MY KNOWLEDGE," +
                                "INFORMATION AND BELIEF CONFIRMS THAT NO CHILD LABOUR WAS INVOLVED IN THE MANUFACTURER,ASSEMBLING AND " +
                                "DISTRIBUTION OF ANY OF THE MERCHANDISE AS DESCRIBED IN THIS INVOICE." +
                                "(1) THE REFUND OF SERVICE TAX PAID ON THE SPECIFIED SERVICES IS CLAIMED AS A PERCENTAGE OF THE DECLARED FOB " +
                                "VALUE OF THE GOODS ON THE BASIS OF RATE SPECIFIED IN THE SCHEDULE TO NOTIFICATION NO.41/2012ST DT 29.6.2012 " +
                                "(2) NO FURTHER REFUND SHALL BE CLAIMED IN RESPECT OF THE SPECIFIED SERVICES UNDER PROCEDURE SPECIFIED IN PARA 3 " +
                                "OF NOTIFICATION NO.41/2012 S T DT 29.6.2012 OR IN ANY ANOTHER MANNER INCLUDING ON THEGROUND THAT REFUND " +
                                "OBTAINED IS LESS THAN THE SERVICE TAX PAID IN THE SERVICES.";
                                      }else{
                              
                                    msg2="WE HAVE USED OUR BEST EFFORTS TO ENSURE THAT NO  CHILD LABOUR  IS INVOLVED AND TO THE BEST OF MY KNOWLEDGE,INFORMATION AND BELIEF CONFIRMS THAT NO CHILD LABOUR WAS INVOLVED IN THE MANUFACTURER, ASSEMBLING AND DISTRIBUTION OF ANY OF THE MERCHANDISE AS DESCRIBED IN THIS INVOICE.";
                              }
                               
                           }      
                    */
                           
                           bean.setMSG2(msg2); 
                           preInvPrintBeans.add(bean);
                    
                    }
                     
       
               
            } catch (Exception e) {

                flag = 0; 
                try { 
                    flag = 0; 
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : PreInvPrintAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : PreInvPrintAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {

                    if (result1 != null) {
                        result1.close();
                    }
                    if (result2 != null) {
                        result2.close();
                    }
                    if (result3 != null) {
                        result3.close();
                    }
                    if (result != null) {
                        result.close();
                    }
                     if (stat1 != null) {
                        stat1.close();
                    }
                    if (stat2 != null) {
                        stat2.close();
                    }
                    if (stat3 != null) {
                        stat3.close();
                    }
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (connBI != null) {
                        connBI.close();
                    }
                    if (conndb2 != null) {
                        conndb2.close();
                    }
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    conn = null;
                    connBI=null; 
                    conndb2=null;
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : PreInvoiceMvxAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

           
 
        }

         return preInvPrintBeans;
    } 

 double roundFourDecimals(double d) {
            DecimalFormat fourDForm = new DecimalFormat("#######.####");
        return Double.valueOf(fourDForm.format(d));
}   
  
      
       
     
    double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
}
}
   