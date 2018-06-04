package shahi.Action.MvxExp.PRE;
 
 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2; 
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.PRE.Beans.InvMovexQryBean;
import shahi.Action.MvxExp.PRE.Beans.MovexInvQryDelBean;
  

public class InvMovexQryNewAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
     
    private String searchinv;
    private String aausrid;
    private String excs_inv_no;
    private String plan_no;
    private String inv_date;
    private String inv_type;
    private Double inv_qty;
    private String cost_centre;
    private String buyer;
    private String mode_of_ship;
    private String tto_date;
    private String to_date;
    private String etd_date;
    private String pre_docs_sent;
    private String fin_date;
    private String fwd_custom;
    private String fwd_date;
    private String location;
    private String year;
    private String company;
    private String inv_no;
    private String buyer_addr;
    private String buyer_name;
    private String buyer_address;
    private String cons_addr;
    private String cons_name;
    private String cons_address;
    private String ac_holder;
    private String merchant;
    private String pprq_date;
    private String ship_type;
    private String ship_cancel;//invoice cancel
    private int ship_qty;
    private String awbdate;
    private String couom;
    private String basicuom;
    private double tinv_qty;
    private double tco_qty;
    private double tinv_salefob;
    private double tinv_netfob;
    private double tdel_qty;
    private double tdel_salefob;
    private double tdel_netfob;
    private double diff_salefob;
    private double diff_netfob; 
    
    private List InvList ;
    private List DelList = new ArrayList();
    
    
    @Override
    public String execute() {
      InvList=new ArrayList();
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
     //   aausrid = "227350";
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        try {

            Connection conn = null; 
            Connection conndb2 = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            try { 
                conndb2 = new connectiondb2().getConnection();
                conndb2.setAutoCommit(false);
               
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
            double co_qty; double co_rate ; double co_gr; double del_qty; double del_rate;  double del_gr; double del_salefob=0.0; double del_netfob=0.0;  
               stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                stat1.setString(1,usrid);
                result1=stat1.executeQuery();
                while (result1.next())
                {LOCATION_CODE=result1.getString("location_code");
                } 

                if (viewFlag != null ) 
                {  
                    stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(a.inv_date,'dd/mm/yyyy')inv_date,a.exp_type||' '||decode(a.self_tp,'N','Normal','F','Free Sample','S','Trade Sample',self_tp) inv_type,to_char(a.fwd_custom,'dd/mm/yyyy') fwd_custom,to_char(a.tto_date,'dd/mm/yyyy') tto_date,to_char(a.etd_date,'dd/mm/yyyy') etd_date,agent,fwd_code,"
                            + " to_char(a.t_o_date,'dd/mm/yyyy') to_date,to_char(a.doc_send,'dd/mm/yyyy') fwd_date,to_char(a.fin_date,'dd/mm/yyyy') fin_date,a.ac_holder,a.cost_centre,a.mode_of_ship,a.inv_qty,a.buyer,a.buyer_addr,a.cons_addr,a.year,a.company,a.inv_no,LOADING_port,LOADING CLR_port,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs_sent,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,MERCHANT_NAME,to_char(a.pprq_date,'dd/mm/yyyy') pprq_date,a.crncy_code,a.lcno,nvl(a.ship_qty,0) ship_qty,to_char(awbdate,'dd/mm/yyyy') awbdate,a.surrender_yn ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,a.tax_type,a.tax_percent,a.tax_code,a.tax_cal_per,"
                            +" a.ex_inv_slno,a.ex_inv_date,a.ci_no,a.CI_NO,to_char(a.CI_date,'dd/mm/yyyy') ci_date,a.sap_del_date from ei_endors_mast a  where  a.excs_inv_no=?   ");
                    stat1.setString(1, searchinv);
                //    stat1.setString(2,LOCATION_CODE);
                     result1 = stat1.executeQuery(); 
                    while (result1.next()) 
                    {  
                        year=result1.getString("year");
                        company=result1.getString("company");
                        inv_no=result1.getString("inv_no");
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
                        pre_docs_sent = result1.getString("pre_docs_sent");
                        inv_qty = result1.getDouble("inv_qty");
                        buyer = result1.getString("buyer");
                        buyer_addr = result1.getString("buyer_addr");
                        cons_addr = result1.getString("cons_addr");
                        ship_type = result1.getString("ship_type").trim();
                        ship_cancel = result1.getString("ship_cancel");
                        ship_qty = result1.getInt("ship_qty");
                        awbdate=result1.getString("awbdate");
                    } // Main invoice Query Closed   
                      stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and trim(opadid)=? ");
                      stat.setString(1, buyer);
                      stat.setString(2, buyer_addr);
                      result = stat.executeQuery();
                      if (result.next() == true) 
                      {
                        buyer_name = result.getString("opcunm");
                        buyer_address = result.getString("opadd");
                       }   
                     
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  ocusad b where OPCONO = 111 AND OPADRT=1 and trim(opcuno)=? and opadid=? ");
                       stat.setString(1, buyer);
                       stat.setString(2, cons_addr);
                       result = stat.executeQuery();
                       if (result.next() == true) 
                       {
                          cons_name = result.getString("opcunm");
                          cons_address = result.getString("opadd");
                       }
                      String err=""; String msg1=""; String msg=""; String movex_inv=""; String st1="";
                      String vch_type ="";String vch_no =""; String vch_year =""; double inv_salefob=0.0; double inv_netfob=0.0;  
                       stat=conn.prepareStatement("select co_no,co_line,item_no,unit,qty_endors,price_fc+nvl(price_misc,0)-nvl(adjust_fc,0) inv_rate,net_price,adjust_fc from ei_endors_dtls where year=? and company=? and inv_no=? order by co_no,co_line,item_no ");
                       stat.setString(1,year);
                       stat.setString(2,company);
                       stat.setString(3,inv_no);
                       result = stat.executeQuery();
                       while (result.next())
                       { 
                         co_qty=0; co_rate = 0 ; co_gr = 0; 
                           
                          stat1=conndb2.prepareStatement(" select mmunms from m3fdbprd.mitmas where mmcono = 111 and mmitno = ?");
                           stat1.setString(1,result.getString("item_no"));
                           result1=stat1.executeQuery();
                           if (result1.next())
                           {  
                               basicuom=result1.getString("mmunms");
                           }

                           stat1=conndb2.prepareStatement("select obdlqt,obsapr,obnepr, obalun from m3fdbprd.ooline where obcono=111 and oborno=? and obponr=? and obitno=? ");
                           stat1.setString(1,result.getString("co_no"));
                           stat1.setString(2,result.getString("co_line"));
                           stat1.setString(3,result.getString("item_no"));
                           result1=stat1.executeQuery();
                           if (result1.next())
                           {
                                      
                             co_qty=result1.getDouble("obdlqt");
                             co_rate=result1.getDouble("obsapr");
                             co_gr=result1.getDouble("obnepr");
                             couom=result1.getString("obalun");
                           
                           
                           }
                                 
                           
                           String del_no=""; Double set_pcs=1.0; del_rate=0; del_qty=0; del_gr=0;
                           stat1=conn.prepareStatement(" select del_numb,conv_factr,count(*) cnt from  seplweb.pr_ship_plan_detail a,seplweb.pr_ship_plan_master b where a.plan_numb=b.plan_numb and b.plan_numb=? and b.year=? and b.company_code=? and b.inv_no=? and co_numb=? and co_line=? and item_numb=? group by del_numb,conv_factr");
                           stat1.setString(1,plan_no);
                           stat1.setString(2,year);
                           stat1.setString(3,company);
                           stat1.setString(4,inv_no);
                           stat1.setString(5,result.getString("co_no"));
                           stat1.setString(6,result.getString("co_line"));
                           stat1.setString(7,result.getString("item_no"));
                           result1=stat1.executeQuery();
                           if (result1.next())
                           {
                                 if (result1.getInt("cnt")<1 )
                                {
                                  addActionMessage(" Invoice Not Linked With Planning !!");
                                  return ERROR;
                                }
                           }    
                           double pack_del_qty=0.0;  String CHALLAN_NO="";
                                       stat2=conn.prepareStatement(" select IR_NUMB,M3_DEL_NUMB,set_pcs,sum(pack_qty/nvl(set_pcs,1)) pqty  from  seplweb.pr_pack_box_ir_detail   where   pack_numb=? and co_numb=? and co_line=? and item_numb=? and M3_DEL_NUMB is not null group by IR_NUMB,m3_del_numb,set_pcs");
                                       stat2.setString(1,plan_no);
                                       stat2.setString(2,result.getString("co_no"));
                                       stat2.setString(3,result.getString("co_line"));
                                       stat2.setString(4,result.getString("item_no"));
                                       result2=stat2.executeQuery();
                                       while (result2.next())
                                       {
                                          del_no=result2.getString("M3_DEL_NUMB");
                                          set_pcs=result2.getDouble("set_pcs");
                                          CHALLAN_NO=result2.getString("IR_NUMB");
                                          
                                              stat1=conndb2.prepareStatement(" select trim(uaorst) uast,uaivno, uayea4 from m3fdbprd.odhead where uacono='111' and uaorno= ? and uadlix=?");
                                                        stat1.setString(1,result.getString("co_no"));
                                                        stat1.setString(2,del_no);
                                                        result1=stat1.executeQuery();
                                                        if (result1.next())
                                                        {  
                                                          st1=result1.getString("uast");
                                                        }
                                          if (result1 != null) {result1.close();}
                                          if (stat1 != null) {stat1.close();}
                                            del_rate = 0; del_gr =0;   del_qty=0.0; int v_auom=0; del_salefob = 0.0; del_netfob=0.0; 
                                            stat1=conndb2.prepareStatement("  select    ubsapr,ubdlqt+ubivqt ubdlqt,ubnepr from m3fdbprd.odline where ubcono=111 and uborno=? and ubponr=? and ubitno=? and ubdlix=? ");
                                            stat1.setString(1,result.getString("co_no"));
                                            stat1.setString(2,result.getString("co_line"));
                                            stat1.setString(3,result.getString("item_no"));
                                            stat1.setString(4,del_no);
                                            result1=stat1.executeQuery();
                                            if (result1.next())
                                            {  
                                                del_rate=result1.getDouble("ubsapr");
                                                del_gr=result1.getDouble("ubnepr");

                                                if (!couom.equals(basicuom))
                                                {
 
                                                         stat3=conndb2.prepareStatement("select mucofa, mudmcf, muaus2,muaus9  from m3fdbprd.mitaun where mucono = 111 and muitno = ? and MUAUTP = '1' and MUALUN = ?  ");
                                                         stat3.setString(1,result.getString("item_no"));
                                                         stat3.setString(2,result.getString("unit"));
                                                         result3=stat3.executeQuery();
                                                         if (result3.next())
                                                          { 
                                                               if (result3.getInt("muaus2")==1)
                                                                {  if (set_pcs!=result3.getDouble("mucofa")) 
                                                                   {  err="**";
                                                                      msg1="..... Set Pcs Qty  Mismatch ......";
                                                                   }

                                                                    del_qty=roundTwoDecimals(result1.getDouble("ubdlqt")/result3.getDouble("mucofa"));

                                                                }else
                                                                {
                                                                      err="**";
                                                                      msg1="..... Alternate UOM Not applicable in Movex ......";
                                                                      del_qty=roundTwoDecimals(result1.getDouble("ubdlqt"));

                                                                }
                                                              v_auom=result3.getInt("mudmcf");
                                                          }

                                                          if (v_auom==0) 
                                                          {
                                                           err="**";
                                                           msg1="..... .Alternate UOM Not Define ......";
                                                          }else if (v_auom!=1)
                                                          {
                                                           err="**";
                                                           msg1="..... . Alternate UOM Factor mismatch ......";
                                                          }

                                                       }else{
                                                            del_qty=roundTwoDecimals(result1.getDouble("ubdlqt")/set_pcs);
                                                           }
                                                  pack_del_qty=pack_del_qty+del_qty;
                                                        
                                                   if (Integer.parseInt(st1)<=79)
                                                          {
                                                               del_rate=co_rate; 
                                                               del_gr=co_gr;
                                                          }
                                                  
                                                           if (result.getDouble("inv_rate")!=del_rate)
                                                           {err="**";
                                                            msg1="... Sale Price Mismatch....";
                                                           }
                                                           if (result.getDouble("net_price")!=del_gr)
                                                           {err="**";
                                                               msg1="... Discount Price Mismatch....";
                                                           }
                                                 
                                                
                                                 } // Delv Qnty Checking closed
                                               

                                                        int st=0; movex_inv=""; st1="";

                                                        stat1=conndb2.prepareStatement(" select trim(uaorst) uast,uaivno, uayea4 from m3fdbprd.odhead where uacono='111' and uaorno= ? and uadlix=?");
                                                        stat1.setString(1,result.getString("co_no"));
                                                        stat1.setString(2,del_no);
                                                        result1=stat1.executeQuery();
                                                        if (result1.next())
                                                        {  

                                                           st1=result1.getString("uast");
                                                          movex_inv=result1.getString("uaivno");

                                                          vch_type ="";vch_no =""; vch_year ="";
                                                          stat3=conndb2.prepareStatement(" select uhvser,uhvono,uhyea4 from m3fdbprd.oinvoh where uhcono=111 and uhivno=? and uhyea4 = ?");
                                                          stat3.setString(1,result1.getString("uaivno"));
                                                          stat3.setString(2,result1.getString("uayea4"));
                                                          result3=stat3.executeQuery();

                                                          if (result3.next())
                                                          {  vch_type=result3.getString("uhvser");
                                                             vch_no=result3.getString("uhvono");
                                                             vch_year=result3.getString("uhyea4");
                                                          }

                                                       /*   if (Integer.parseInt(st1)<=79)
                                                          {
                                                               del_rate=co_rate; 
                                                               del_gr=co_gr;
                                                          }
                                                       */
                                                          
                                                        } 
                                                        if (set_pcs!=1 && couom == basicuom)
                                                        {err="*!";
                                                         msg1="... Set Pcs Should be 1....";
                                                        }
 

                                                       tdel_salefob=tdel_salefob+(del_qty*del_rate);
                                                       tdel_netfob=tdel_netfob+(del_qty*del_gr);
                                                       tdel_qty=tdel_qty+del_qty;
                                           
                                                             DelList.add(new InvMovexQryBean(result.getString("co_no"), result.getString("co_line"), result.getString("item_no"),co_qty,co_rate,co_gr,result.getDouble("qty_endors"),result.getDouble("inv_rate"), result.getDouble("net_price"),result.getDouble("adjust_fc"),inv_salefob,inv_netfob,del_no,movex_inv,del_qty,del_rate,del_gr,del_salefob,del_netfob,st1,err,msg1,vch_type,vch_no,vch_year,set_pcs,CHALLAN_NO));
                                           
                                                         del_no=null; CHALLAN_NO=null;
                                                       st1=null;
                                       }  /// Multiple Del Closed
                                  
                                                          if (result.getDouble("qty_endors")!=pack_del_qty)
                                                           {err="**";
                                                            msg1="... Quantity Mismatch....";
                                                           }
                            tco_qty=tco_qty+co_qty;
                            tinv_salefob=tinv_salefob+result.getDouble("qty_endors")*(result.getDouble("inv_rate"));
                            tinv_netfob=tinv_netfob+result.getDouble("qty_endors")*result.getDouble("net_price");
                            tinv_qty=tinv_qty+result.getDouble("qty_endors");
                             
                            InvList.add(new MovexInvQryDelBean(result.getString("co_no"), result.getString("co_line"), result.getString("item_no"),co_qty,co_rate,co_gr,result.getDouble("qty_endors"),result.getDouble("inv_rate"), result.getDouble("net_price"),result.getDouble("adjust_fc"),inv_salefob,inv_netfob,msg1));
                           err=null;msg1=null;
                           pack_del_qty=0.0;
                       
                       } // Invoice Detail closed
                          
                          tinv_salefob=roundTwoDecimals(tinv_salefob);
                          tinv_netfob=roundTwoDecimals(tinv_netfob);
                          tdel_salefob=roundTwoDecimals(tdel_salefob);
                          tdel_netfob=roundTwoDecimals(tdel_netfob);
                          diff_salefob=tinv_salefob-tdel_salefob;
                          diff_netfob=tinv_netfob-tdel_netfob;
                          co_qty=0; 
                          inv_qty=0.0;
                         flag=1;
                         
                } // View Flg Close       
           
                  if (result1 != null) {result1.close();}
                  if (result != null) {result.close();  }
                  if (result2 != null) {result2.close();}
                  if (result3 != null) {result3.close();}
                  if (stat1 != null) {stat1.close();}
                  if (stat != null) {stat.close();}
                    if (stat2 != null) {stat2.close();}
                                
                                  
                    
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : InvTrackAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : InvTrackAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
  
                    if (result1 != null) {
                        result1.close();
                    }
                   
                    if (result != null) {
                        result.close();
                    }
                  if (result2 != null) {
                        result2.close();
                    }
                   
                    if (result3 != null) {
                        result3.close();
                    }
                    if (stat1 != null) {
                        stat1.close();
                    }
                    
                    if (stat != null) {
                        stat.close();
                    }
                    if (stat2 != null) {
                        stat2.close();
                    }
                    if (conn != null) {
                        conn.close();
                    } if (conndb2 != null) {
                        conndb2.close();
                    }  
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    conn = null; 
                     
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : InvMovexQryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
            
           // searchinv=null;
           
           // addActionMessage("Records Save(s) !!");
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

    public Double getInv_qty() {
        return inv_qty;
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

   
    public String getShip_cancel() {
        return ship_cancel;
    }

    public void setShip_cancel(String ship_cancel) {
        this.ship_cancel = ship_cancel;
    }

    public int getShip_qty() {
        return ship_qty;
    }

    public void setShip_qty(int ship_qty) {
        this.ship_qty = ship_qty;
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
 
    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public List getInvList() {
        return InvList;
    }

    public void setInvList(List InvList) {
        this.InvList = InvList;
    }

    

    public String getCouom() {
        return couom;
    }

    public void setCouom(String couom) {
        this.couom = couom;
    }

    public String getBasicuom() {
        return basicuom;
    }

    public void setBasicuom(String basicuom) {
        this.basicuom = basicuom;
    }

    
  
 

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInv_no() {
        return inv_no;
    }

    public void setInv_no(String inv_no) {
        this.inv_no = inv_no;
    }
  

    public String getAwbdate() {
        return awbdate;
    }

    public void setAwbdate(String awbdate) {
        this.awbdate = awbdate;
    }

    public String getPre_docs_sent() {
        return pre_docs_sent;
    }

    public void setPre_docs_sent(String pre_docs_sent) {
        this.pre_docs_sent = pre_docs_sent;
    }

    public double getTinv_qty() {
        return tinv_qty;
    }

    public void setTinv_qty(double tinv_qty) {
        this.tinv_qty = tinv_qty;
    }

    public double getTco_qty() {
        return tco_qty;
    }

    public void setTco_qty(double tco_qty) {
        this.tco_qty = tco_qty;
    }

    public double getTinv_salefob() {
        return tinv_salefob;
    }

    public void setTinv_salefob(double tinv_salefob) {
        this.tinv_salefob = tinv_salefob;
    }

    public double getTinv_netfob() {
        return tinv_netfob;
    }

    public void setTinv_netfob(double tinv_netfob) {
        this.tinv_netfob = tinv_netfob;
    }

    public double getTdel_qty() {
        return tdel_qty;
    }

    public void setTdel_qty(double tdel_qty) {
        this.tdel_qty = tdel_qty;
    }

    public double getTdel_salefob() {
        return tdel_salefob;
    }

    public void setTdel_salefob(double tdel_salefob) {
        this.tdel_salefob = tdel_salefob;
    }

    public double getTdel_netfob() {
        return tdel_netfob;
    }

    public void setTdel_netfob(double tdel_netfob) {
        this.tdel_netfob = tdel_netfob;
    }

    public double getDiff_salefob() {
        return diff_salefob;
    }

    public void setDiff_salefob(double diff_salefob) {
        this.diff_salefob = diff_salefob;
    }

    public double getDiff_netfob() {
        return diff_netfob;
    }

    public void setDiff_netfob(double diff_netfob) {
        this.diff_netfob = diff_netfob;
    }

    public List getDelList() {
        return DelList;
    }

    public void setDelList(List DelList) {
        this.DelList = DelList;
    }
            
    
    
}
