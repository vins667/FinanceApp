package shahi.Action.MvxExp.PRE;

 
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionShahiHrisNew;
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Beans.EI_ENDORS_DTLS_BEANS;
import shahi.Action.MvxExp.Beans.EI_ENDORS_LC_LIC_DTLS_BEANS;

public class PreInvQueryAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private List showList;
    private String searchplan;
    private String searchplan1;
    private String searchinv1;
    private String searchfrom;
    private String searchto;
    private String premast;
    private String searchinv;
    private String aausrid;
    private String excs_inv_no;
    private String plan_no;
    private String inv_date;
    private String inv_type;
    private String inv_qty;
    private String cost_centre;
    private String buyer;
    private String mode_of_ship;
    private String tto_date;
    private String to_date;
    private String etd_date;
    private String fin_date;
    private String fwd_custom;
    private String fwd_date;
    private String location;
    private String buyer_addr;
    private String buyer_name;
    private String buyer_address;
    private String cons_addr;
    private String cons_name;
    private String cons_address;
    private String ac_holder;
    private String merchant;
    private String pprq_date;
    private String CLR_PORT;
    private String LOADING_PORT;
    private String ship_type;
    private String CHA_NAME;
    private String FWD_NAME;
    private String tooltip1;
    private String tooltip2;
    private String SAP_BPO;
    private String SAP_DEL_DATE;
    private String SAP_BPO_DATE;
    private String DESTI_CNTRY;
    private String DIS_CNTRY;
    private String DISCHARGE;
    private String DESTI_CODE;
    private String CNTRY_ORIGIN;
    private String SHIP_DESC=null;
    private String CRNCY_CODE=null;
    private String MANUF_STATE=null;
    private String TAX_TYPE=null;
    private String TAX_PERCENT=null;
    private String TAX_CODE=null;
    private String TAX_CAL_PER=null;
    private String ship_cancel;//invoice cancel
    private String outhouse;
    private List<EI_ENDORS_DTLS_BEANS> EI_ENDORS_DTLS_LIST = new ArrayList<EI_ENDORS_DTLS_BEANS>();
    private List<EI_ENDORS_LC_LIC_DTLS_BEANS> EI_ENDORS_LC_LIC_DTLS_LIST = new ArrayList<EI_ENDORS_LC_LIC_DTLS_BEANS>();

    @Override
    public String execute() {
        showList = new ArrayList();
     try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }
        
        int falg = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
     //   aausrid = "227350";
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        try {

            Connection conn = null;
            Connection connBI = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            try {
                connBI = new ConnectionShahiHrisNew().getConnection();
                connBI.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch 
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
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
           
                if (viewFlag != null) {
                    stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(a.inv_date,'dd/mm/yyyy')inv_date,a.exp_type||' '||decode(a.self_tp,'N','Normal','F','Free Sample','S','Trade Sample',self_tp) inv_type,to_char(a.fwd_custom,'dd/mm/yyyy') fwd_custom,to_char(a.tto_date,'dd/mm/yyyy') tto_date,to_char(a.etd_date,'dd/mm/yyyy') etd_date,agent,fwd_code,"
                            + " to_char(a.t_o_date,'dd/mm/yyyy') to_date,to_char(a.doc_send,'dd/mm/yyyy') fwd_date,to_char(a.fin_date,'dd/mm/yyyy') fin_date,a.ac_holder,a.cost_centre,a.mode_of_ship,a.inv_qty,a.buyer,a.buyer_addr,a.cons_addr,a.year,a.company,a.inv_no,LOADING_port,LOADING CLR_port,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,MERCHANT_NAME,to_char(a.pprq_date,'dd/mm/yyyy') pprq_date,a.crncy_code,a.lcno,a.oh_work outhouse,a.surrender_yn ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,a.tax_type,a.tax_percent,a.tax_code,a.tax_cal_per,"
                            +" a.ex_inv_slno,a.ex_inv_date,a.ci_no,a.CI_NO,to_char(a.CI_date,'dd/mm/yyyy') ci_date,a.sap_del_date from ei_endors_mast a  where  a.excs_inv_no like nvl(?,'%') and  a.plan_no like nvl(?,'%') ");
                    stat1.setString(1, searchinv);
                    stat1.setString(2, searchplan); 
                    result1 = stat1.executeQuery(); 
                    while (result1.next()) {
                         
                        location = result1.getString("location");
                        excs_inv_no = result1.getString("excs_inv_no");
                        plan_no = result1.getString("plan_no");
                        inv_date = result1.getString("inv_date");
                        inv_type = result1.getString("inv_type");
                        fwd_custom = result1.getString("fwd_custom");
                        tto_date = result1.getString("tto_date");
                        etd_date = result1.getString("etd_date");
                        to_date = result1.getString("to_date");
                        fwd_date = result1.getString("fwd_date");
                        fin_date = result1.getString("fin_date");
                        ac_holder = result1.getString("ac_holder");
                        merchant=result1.getString("MERCHANT_NAME");
                        pprq_date=result1.getString("pprq_date");
                        cost_centre = result1.getString("cost_centre");
                        mode_of_ship = result1.getString("mode_of_ship");
                        inv_qty = result1.getString("inv_qty");
                        buyer = result1.getString("buyer");
                        buyer_addr = result1.getString("buyer_addr");
                        cons_addr = result1.getString("cons_addr");
                        ship_type = result1.getString("ship_type").trim();
                        SAP_BPO = result1.getString("CI_NO");
                        SAP_DEL_DATE = result1.getString("CI_date");
                        LOADING_PORT = result1.getString("LOADING_PORT");
                        CLR_PORT = result1.getString("CLR_PORT");
                        DESTI_CNTRY = result1.getString("DESTI_CNTRY");
                        DIS_CNTRY = result1.getString("DIS_CNTRY");
                        CNTRY_ORIGIN=result1.getString("CNTRY_ORIGIN");
                        DISCHARGE=result1.getString("DISCHARGE");
                        DESTI_CODE=result1.getString("DESTI_CODE");
                        CRNCY_CODE = result1.getString("crncy_code");
                        MANUF_STATE = result1.getString("manuf_state");
                        TAX_TYPE = result1.getString("tax_type");
                        TAX_PERCENT = result1.getString("tax_percent");
                        TAX_CODE = result1.getString("tax_code");
                        TAX_CAL_PER = result1.getString("tax_cal_per");
                        SHIP_DESC =  result1.getString("SHIP_DESC");
                        ship_cancel = result1.getString("ship_cancel");
                        outhouse = result1.getString("outhouse");
                        if (ship_type.equals("F")) {
                            tooltip1 = "<table bgcolor=#006699 width=100%  cellpadding=2 cellspacing=1><tr bgcolor=#81dd60>"
                                    + "<td class=label-1>CI NO : " + SAP_BPO
                                    + "</td><td class=label-1>CI DATE : " + SAP_DEL_DATE
                                     + "</td></tr></table>";
                        }
                        //tooltip2 = "<table bgcolor=#006699 width=100%  cellpadding=2 cellspacing=1><tr bgcolor=#81dd60>" + "<td class=label-1>Ship Desc : " + SHIP_DESC + "</td></tr></table>";
                         EisUtil eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("CSCD",DIS_CNTRY);
                         while(result.next())
                         { DIS_CNTRY=result.getString("cttx40");} 
                         eisutil.closeConnection();
                         eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("CSCD",CNTRY_ORIGIN);
                         while(result.next())
                         { CNTRY_ORIGIN=result.getString("cttx40");} 
                         eisutil.closeConnection();
                         
                         eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("CSCD",DESTI_CNTRY);
                         while(result.next())
                         { DESTI_CNTRY=result.getString("cttx40");} 
                         eisutil.closeConnection();
                         
                         eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("HAFE",LOADING_PORT);
                         while(result.next())
                         { LOADING_PORT=result.getString("cttx40");} 
                         eisutil.closeConnection();
                          
                         eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("HAFE",CLR_PORT);
                         while(result.next())
                         { CLR_PORT=result.getString("cttx40");} 
                         eisutil.closeConnection();
                         
                         eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("SDST",DISCHARGE);
                         while(result.next())
                         { DISCHARGE=result.getString("cttx40");} 
                        
                         eisutil.closeConnection();
                         eisutil = new EisUtil();
                         result  = eisutil.getCSYTAB("EDES",DESTI_CODE);
                         while(result.next())
                         { DESTI_CODE=result.getString("cttx40");} 
                         eisutil.closeConnection();
                         
                          stat = connBI.prepareStatement("select idsunm from PRODBI.cidmas where idcono=111 and idsuno=? ");
                          stat.setString(1,result1.getString("agent"));
                          result = stat.executeQuery();
                          if(result.next())
                          {
                            CHA_NAME=result.getString("idsunm");
                          }  
                     
                          stat = connBI.prepareStatement("select idsunm from PRODBI.cidmas where idcono=111 and idsuno=? ");
                          stat.setString(1,result1.getString("fwd_code"));
                          result = stat.executeQuery();
                          if(result.next())
                          {
                            FWD_NAME=result.getString("idsunm"); 
                          } 
                         
                        stat2 = conn.prepareStatement("select co_no,co_line,item_no,unit,qty_endors,qty_kgs,price_fc,price_misc,adjust_fc,net_price,"
                                + " qty_endors*(price_fc+nvl(price_misc,0)) fob_fc,gr_decl_amt,dbk_slno,mrp_rate,qty_endors*mrp_rate mrp_fob,pre_print_no,token_no,category,description "
                                + " from ei_endors_dtls where year=? and company=? and inv_no=? order by co_no,co_line");
                        stat2.setString(1, result1.getString("year"));
                        stat2.setString(2, result1.getString("company"));
                        stat2.setString(3, result1.getString("inv_no"));
                        result2 = stat2.executeQuery();
                        while (result2.next()) {
                            EI_ENDORS_DTLS_LIST.add(new EI_ENDORS_DTLS_BEANS(result2.getString("co_no"), result2.getString("co_line"), result2.getString("item_no"), result2.getString("unit"), result2.getInt("qty_endors"), result2.getInt("qty_kgs"), roundTwoDecimals(result2.getDouble("price_fc")), roundTwoDecimals(result2.getDouble("price_misc")), roundTwoDecimals(result2.getDouble("adjust_fc")), roundTwoDecimals(result2.getDouble("net_price")), roundTwoDecimals(result2.getDouble("fob_fc")), roundTwoDecimals(result2.getDouble("gr_decl_amt")), result2.getString("dbk_slno"), result2.getString("mrp_rate"), result2.getString("mrp_fob"), result2.getString("pre_print_no"), result2.getString("token_no"), result2.getString("category"), result2.getString("description")));
                        }                 
                      
                        stat3 = conn.prepareStatement("select distinct ref_type,ref_no from ei_endors_lc_lic_dtls where year=? and company=? and inv_no=?");
                         stat3.setString(1, result1.getString("year"));
                        stat3.setString(2, result1.getString("company"));
                        stat3.setString(3, result1.getString("inv_no"));
                        result3 = stat3.executeQuery();
                     
                        while (result3.next()) {
                          
                            EI_ENDORS_LC_LIC_DTLS_LIST.add(new EI_ENDORS_LC_LIC_DTLS_BEANS(result3.getString("ref_type"), result3.getString("ref_no")));
                        }    
                    
                      stat = connBI.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  PRODBI.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=rpad(?,10,' ') and opadid=rpad(?,6,' ') ");
                      stat.setString(1, buyer);
                      stat.setString(2, buyer_addr);
                       result = stat.executeQuery();
                      if (result.next() == true) {
                        buyer_name = result.getString("opcunm");
                        buyer_address = result.getString("opadd");
                       }   
                    
                       stat = connBI.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  PRODBI.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, buyer);
                       stat.setString(2, cons_addr);
                       result = stat.executeQuery();
                       if (result.next() == true) {
                          cons_name = result.getString("opcunm");
                          cons_address = result.getString("opadd");
                        }
                      
                    } 
                  
                   
                   
                   
                     
                }
            } catch (Exception e) {

                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : PreInvQueryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : PreInvQueryAction.java" + e);

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
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    conn = null;
                    connBI=null;
                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : PreInvQueryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (falg == 1) {

            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");
            return ERROR;
        }
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

    public String getSearchinv() {
        return searchinv;
    }

    public void setSearchinv(String searchinv) {
        this.searchinv = searchinv;
    }

    public String getSearchplan() {
        return searchplan;
    }

    public void setSearchplan(String searchplan) {
        this.searchplan = searchplan;
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

    public String getBuyer_address() {
        return buyer_address;
    }

    public void setBuyer_address(String buyer_address) {
        this.buyer_address = buyer_address;
    }

    public String getAc_holder() {
        return ac_holder;
    } 

    public void setAc_holder(String ac_holder) {
        this.ac_holder = ac_holder;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getCost_centre() {
        return cost_centre;
    }

    public void setCost_centre(String cost_centre) {
        this.cost_centre = cost_centre;
    }

    public String getEtd_date() {
        return etd_date;
    }

    public void setEtd_date(String etd_date) {
        this.etd_date = etd_date;
    }

    public String getExcs_inv_no() {
        return excs_inv_no;
    }

    public void setExcs_inv_no(String excs_inv_no) {
        this.excs_inv_no = excs_inv_no;
    }

    public String getFin_date() {
        return fin_date;
    }

    public void setFin_date(String fin_date) {
        this.fin_date = fin_date;
    }

    public String getFwd_custom() {
        return fwd_custom;
    }

    public void setFwd_custom(String fwd_custom) {
        this.fwd_custom = fwd_custom;
    }

    public String getFwd_date() {
        return fwd_date;
    }

    public void setFwd_date(String fwd_date) {
        this.fwd_date = fwd_date;
    }

    public String getInv_date() {
        return inv_date;
    }

    public void setInv_date(String inv_date) {
        this.inv_date = inv_date;
    }

    public String getInv_qty() {
        return inv_qty;
    }

    public void setInv_qty(String inv_qty) {
        this.inv_qty = inv_qty;
    }

    public String getInv_type() {
        return inv_type;
    }

    public void setInv_type(String inv_type) {
        this.inv_type = inv_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMode_of_ship() {
        return mode_of_ship;
    }

    public void setMode_of_ship(String mode_of_ship) {
        this.mode_of_ship = mode_of_ship;
    }

    public String getPlan_no() {
        return plan_no;
    }

    public void setPlan_no(String plan_no) {
        this.plan_no = plan_no;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTto_date() {
        return tto_date;
    }

    public void setTto_date(String tto_date) {
        this.tto_date = tto_date;
    }

    public String getBuyer_addr() {
        return buyer_addr;
    }

    public void setBuyer_addr(String buyer_addr) {
        this.buyer_addr = buyer_addr;
    }

    public String getCons_addr() {
        return cons_addr;
    }

    public void setCons_addr(String cons_addr) {
        this.cons_addr = cons_addr;
    }

    public String getCons_address() {
        return cons_address;
    }

    public void setCons_address(String cons_address) {
        this.cons_address = cons_address;
    }

    public String getCons_name() {
        return cons_name;
    }

    public void setCons_name(String cons_name) {
        this.cons_name = cons_name;
    }

  
    public String getShip_type() {
        return ship_type;
    }

    public void setShip_type(String ship_type) {
        this.ship_type = ship_type;
    }

    public String getTooltip1() {
        return tooltip1;
    }

    public void setTooltip1(String tooltip1) {
        this.tooltip1 = tooltip1;
    }

    public List<EI_ENDORS_DTLS_BEANS> getEI_ENDORS_DTLS_LIST() {
        return EI_ENDORS_DTLS_LIST;
    }

    public void setEI_ENDORS_DTLS_LIST(List<EI_ENDORS_DTLS_BEANS> EI_ENDORS_DTLS_LIST) {
        this.EI_ENDORS_DTLS_LIST = EI_ENDORS_DTLS_LIST;
    }

    public String getSAP_BPO() {
        return SAP_BPO;
    }

    public void setSAP_BPO(String SAP_BPO) {
        this.SAP_BPO = SAP_BPO;
    }

    public String getSAP_BPO_DATE() {
        return SAP_BPO_DATE;
    }

    public void setSAP_BPO_DATE(String SAP_BPO_DATE) {
        this.SAP_BPO_DATE = SAP_BPO_DATE;
    }

    public String getSAP_DEL_DATE() {
        return SAP_DEL_DATE;
    }

    public void setSAP_DEL_DATE(String SAP_DEL_DATE) {
        this.SAP_DEL_DATE = SAP_DEL_DATE;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public String getMANUF_STATE() {
        return MANUF_STATE;
    }

    public void setMANUF_STATE(String MANUF_STATE) {
        this.MANUF_STATE = MANUF_STATE;
    }

    public String getTAX_CAL_PER() {
        return TAX_CAL_PER;
    }

    public void setTAX_CAL_PER(String TAX_CAL_PER) {
        this.TAX_CAL_PER = TAX_CAL_PER;
    }

    public String getTAX_CODE() {
        return TAX_CODE;
    }

    public void setTAX_CODE(String TAX_CODE) {
        this.TAX_CODE = TAX_CODE;
    }

    public String getTAX_PERCENT() {
        return TAX_PERCENT;
    }

    public void setTAX_PERCENT(String TAX_PERCENT) {
        this.TAX_PERCENT = TAX_PERCENT;
    }

    public String getTAX_TYPE() {
        return TAX_TYPE;
    }

    public void setTAX_TYPE(String TAX_TYPE) {
        this.TAX_TYPE = TAX_TYPE;
    }

    public String getSHIP_DESC() {
        return SHIP_DESC;
    }

    public void setSHIP_DESC(String SHIP_DESC) {
        this.SHIP_DESC = SHIP_DESC;
    }

    public String getTooltip2() {
        return tooltip2;
    }

    public void setTooltip2(String tooltip2) {
        this.tooltip2 = tooltip2;
    }

    public String getShip_cancel() {
        return ship_cancel;
    }

    public void setShip_cancel(String ship_cancel) {
        this.ship_cancel = ship_cancel;
    }

    public String getOuthouse() {
        return outhouse;
    }

    public void setOuthouse(String outhouse) {
        this.outhouse = outhouse;
    }

    public List<EI_ENDORS_LC_LIC_DTLS_BEANS> getEI_ENDORS_LC_LIC_DTLS_LIST() {
        return EI_ENDORS_LC_LIC_DTLS_LIST;
    }

    public void setEI_ENDORS_LC_LIC_DTLS_LIST(List<EI_ENDORS_LC_LIC_DTLS_BEANS> EI_ENDORS_LC_LIC_DTLS_LIST) {
        this.EI_ENDORS_LC_LIC_DTLS_LIST = EI_ENDORS_LC_LIC_DTLS_LIST;
    }

    public String getPremast() {
        return premast;
    }

    public void setPremast(String premast) {
        this.premast = premast;
    }

    public String getSearchfrom() {
        return searchfrom;
    }

    public void setSearchfrom(String searchfrom) {
        this.searchfrom = searchfrom;
    }

    public String getSearchinv1() {
        return searchinv1;
    }

    public void setSearchinv1(String searchinv1) {
        this.searchinv1 = searchinv1;
    }

    public String getSearchplan1() {
        return searchplan1;
    }

    public void setSearchplan1(String searchplan1) {
        this.searchplan1 = searchplan1;
    }

    public String getSearchto() {
        return searchto;
    }

    public void setSearchto(String searchto) {
        this.searchto = searchto;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getPprq_date() {
        return pprq_date;
    }

    public void setPprq_date(String pprq_date) {
        this.pprq_date = pprq_date;
    }

    public String getCHA_NAME() {
        return CHA_NAME;
    }

    public void setCHA_NAME(String CHA_NAME) {
        this.CHA_NAME = CHA_NAME;
    }

    public String getFWD_NAME() {
        return FWD_NAME;
    }

    public void setFWD_NAME(String FWD_NAME) {
        this.FWD_NAME = FWD_NAME;
    }

    public String getCLR_PORT() {
        return CLR_PORT;
    }

    public void setCLR_PORT(String CLR_PORT) {
        this.CLR_PORT = CLR_PORT;
    }

    public String getLOADING_PORT() {
        return LOADING_PORT;
    }

    public void setLOADING_PORT(String LOADING_PORT) {
        this.LOADING_PORT = LOADING_PORT;
    }

    public String getDESTI_CNTRY() {
        return DESTI_CNTRY;
    }

    public void setDESTI_CNTRY(String DESTI_CNTRY) {
        this.DESTI_CNTRY = DESTI_CNTRY;
    }

    public String getCNTRY_ORIGIN() {
        return CNTRY_ORIGIN;
    }

    public void setCNTRY_ORIGIN(String CNTRY_ORIGIN) {
        this.CNTRY_ORIGIN = CNTRY_ORIGIN;
    }

    public String getDISCHARGE() {
        return DISCHARGE;
    }

    public void setDISCHARGE(String DISCHARGE) {
        this.DISCHARGE = DISCHARGE;
    }

    public String getDESTI_CODE() {
        return DESTI_CODE;
    }

    public void setDESTI_CODE(String DESTI_CODE) {
        this.DESTI_CODE = DESTI_CODE;
    }

    public String getDIS_CNTRY() {
        return DIS_CNTRY;
    }

    public void setDIS_CNTRY(String DIS_CNTRY) {
        this.DIS_CNTRY = DIS_CNTRY;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }
     
     
     
}
