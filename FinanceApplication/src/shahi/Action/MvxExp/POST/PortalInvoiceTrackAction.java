package shahi.Action.MvxExp.POST;

 
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
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.PRE.Beans.InvTrackBean;
  

public class PortalInvoiceTrackAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private List showList;
    private List actiontype;
    private List pretype;
    private List prerem;
    private List actionrem;
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
    private String pre_docs_sent;
    private String post_docs_sent;
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
    private String saveFlag;
       
    private String posttype;
  
    private List ShowDetail=new ArrayList();
    private List PreDRList=new ArrayList();
    private List PostDRList=new ArrayList();
    private List TypeList=new ArrayList();
    private List PreList=new ArrayList();
    private List PostList=new ArrayList();
    
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
        
        int flag = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
       // usrid = "227350"; 
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
                 stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                stat1.setString(1,usrid);
                result1=stat1.executeQuery();
                while (result1.next())
                {LOCATION_CODE=result1.getString("location_code");
                } 
                
                if (viewFlag != null ) 
                {  
                    stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(a.inv_date,'dd/mm/yyyy')inv_date,a.exp_type||' '||decode(a.self_tp,'N','Normal','F','Free Sample','S','Trade Sample',self_tp) inv_type,to_char(a.fwd_custom,'dd/mm/yyyy') fwd_custom,to_char(a.tto_date,'dd/mm/yyyy') tto_date,to_char(a.etd_date,'dd/mm/yyyy') etd_date,agent,fwd_code,"
                            + " to_char(a.t_o_date,'dd/mm/yyyy') to_date,to_char(a.doc_send,'dd/mm/yyyy') fwd_date,to_char(a.fin_date,'dd/mm/yyyy') fin_date,a.ac_holder,a.cost_centre,a.mode_of_ship,a.inv_qty,a.buyer,a.buyer_addr,a.cons_addr,a.year,a.company,a.inv_no,LOADING_port,LOADING CLR_port,to_char(pre_docs_sent,'dd/mm/yyyy') pre_docs_sent,to_char(post_docs_sent,'dd/mm/yyyy') post_docs_sent,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,MERCHANT_NAME,to_char(a.pprq_date,'dd/mm/yyyy') pprq_date,a.crncy_code,a.lcno,nvl(a.ship_qty,0) ship_qty,awbdate,a.surrender_yn ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,a.tax_type,a.tax_percent,a.tax_code,a.tax_cal_per,"
                            +" a.ex_inv_slno,a.ex_inv_date,a.ci_no,a.CI_NO,to_char(a.CI_date,'dd/mm/yyyy') ci_date,a.sap_del_date from ei_endors_mast a  where  a.excs_inv_no=? and a.location=? ");
                    stat1.setString(1, searchinv);
                    stat1.setString(2,LOCATION_CODE);
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
                        post_docs_sent = result1.getString("post_docs_sent");
                        inv_qty = result1.getString("inv_qty");
                        buyer = result1.getString("buyer");
                        buyer_addr = result1.getString("buyer_addr");
                        cons_addr = result1.getString("cons_addr");
                        ship_type = result1.getString("ship_type").trim();
                        ship_cancel = result1.getString("ship_cancel");
                        ship_qty = result1.getInt("ship_qty");
                        awbdate=result1.getString("awbdate");
                        stat2 = conn.prepareStatement("select to_char(tr_date,'dd/mm/yyyy') tr_date,type_desc||' - '||tr_type trdesc,tr_type,remark,a.seh_user from ei_truckout_track a,ei_grup_type_dtls b where a.tr_type=b.type_code and b.grup_type_code='PU' and year=? and company=? and inv_no=? order by a.sr_no");
                        stat2.setString(1,year);
                        stat2.setString(2,company);
                        stat2.setString(3,inv_no);
                        result2 = stat2.executeQuery();
                        while (result2.next()) 
                        {
                            ShowDetail.add(new InvTrackBean(result2.getString("tr_date"), result2.getString("tr_type"), result2.getString("trdesc"), result2.getString("remark"), result2.getString("seh_user")));
                        }         
                        
                         stat2 = conn.prepareStatement("select to_char(a.tdate,'dd/mm/yyyy') tr_date,type_desc trdesc,reason_code,a.remarks,a.grup_code,a.seh_user from ei_docs_delay_dtls a,ei_grup_type_dtls b where a.REASON_CODE=b.type_code and A.GRUP_CODE=b.grup_type_code AND A.GRUP_CODE='PUR' and year=? and company=? and inv_no=? order by 1");
                        stat2.setString(1,year);
                        stat2.setString(2,company);
                        stat2.setString(3,inv_no);
                        result2 = stat2.executeQuery();
                        while (result2.next()) 
                        {
                            PreDRList.add(new InvTrackBean(result2.getString("tr_date"), result2.getString("reason_code"), result2.getString("trdesc"), result2.getString("remarks"), result2.getString("seh_user")));
                        }  
                        
                    
                      stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from  M3FDBPRD.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                      stat.setString(1, buyer);
                      stat.setString(2, buyer_addr);
                       result = stat.executeQuery();
                      if (result.next() == true) 
                      {
                        buyer_name = result.getString("opcunm");
                        buyer_address = result.getString("opadd");
                       }   
                    
                       stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from M3FDBPRD.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                       stat.setString(1, buyer);
                       stat.setString(2, cons_addr);
                       result = stat.executeQuery();
                       if (result.next() == true) 
                       {
                          cons_name = result.getString("opcunm");
                          cons_address = result.getString("opadd");
                        }
                          stat1 = conn.prepareStatement("select  type_desc||'-'||type_code type_DESC,type_code from  EI_GRUP_TYPE_DTLS WHERE GRUP_TYPE_CODE='PU' and close_date is null order by 1" );
                       
                         result1 = stat1.executeQuery();
                         while(result1.next())
                         {  
                            TypeList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
                         }
                          
                        stat1 = conn.prepareStatement("select  type_desc||'-'||type_code type_DESC,type_code from  EI_GRUP_TYPE_DTLS WHERE  grup_type_code='PUR' order by 1" );
                           
                         result1 = stat1.executeQuery();
                         while(result1.next())
                         {   
                            PreList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
                         }  
                         
                     }  
                } // View Flg Close      
            
                if (saveFlag!=null && saveFlag.equals("YES"))
              {  String stype=""; String srem="";
                 if ((actiontype != null) && (actiontype.size() > 0))
                  {
                    for (int i = 0; i < actiontype.size(); i++)
                     {
                       stype=actiontype.get(i).toString();
                       srem=actionrem.get(i).toString();
                     if (stype!=null && stype.length()>0)  
                     {
                             
                            
           
                           int maxsrno=0;
                            stat1 = conn.prepareStatement("select nvl(max(sr_no),0)+1 vno from ei_truckout_track  where year=? and company=? and inv_no=? ");
                            stat1.setString(1, year);
                            stat1.setString(2,company);
                            stat1.setString(3,inv_no);
                              result1 = stat1.executeQuery();
                             while (result1.next())
                              {   
                                maxsrno=result1.getInt("vno");
                              }
                                stat1=conn.prepareStatement("insert into ei_truckout_track (year,company,inv_no,location,sr_no,tr_type,remark,all_no,tr_date,tdate,seh_user) values (?,?,?,?,?,?,?,?,sysdate,sysdate,?)");
                                stat1.setString(1,year);
                                stat1.setString(2,company);
                                stat1.setString(3,inv_no);
                                stat1.setString(4, location);
                                stat1.setInt(5,maxsrno);
                                stat1.setString(6,stype);
                                stat1.setString(7,srem.toUpperCase());
                                stat1.setString(8,searchinv);
                                stat1.setString(9,usrid);
                                stat1.executeUpdate();
                                flag=1;
                         }
                         }     
                     }// Action type list close
                     ///// Pre Delay insert Start
                               String grupcode="PUR";
                               String ptype="";
                        if (pretype!=null && pretype.size()>0) 
                          {
                          for (int i = 0; i < pretype.size(); i++)
                         {
                                ptype=pretype.get(i).toString();
                                
                            if (ptype!=null && ptype.length()>0)  
                             {
                                stat1=conn.prepareStatement("insert into EI_DOCS_DELAY_DTLS (year,company,inv_no,reason_code,grup_code,all_no,remarks,seh_user,tdate) values (?,?,?,?,?,?,?,?,sysdate)");
                                stat1.setString(1,year);
                                stat1.setString(2,company);
                                stat1.setString(3,inv_no);
                                stat1.setString(4, ptype);
                                stat1.setString(5,grupcode);
                                stat1.setString(6,searchinv);
                                stat1.setString(7,prerem.get(i).toString());
                                stat1.setString(8,usrid);
                                stat1.executeUpdate();
                                flag=1;
                          } // Pre Delay Closed
                         }
                     }
                    }  /// Close Save Flag
                         
                        
                
                            
                        
                                
                                
                    
                  
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
                    if (conndb2 != null) {
                        conndb2.close();
                    }
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    conn = null;
                    conndb2=null;
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : PreInvQueryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
            actiontype=null;
            actionrem=null;
            searchinv=null;
            prerem=null;
            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");
            actionrem=null;
            prerem=null;
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

    

   
    public String getPremast() {
        return premast;
    }

    public void setPremast(String premast) {
        this.premast = premast;
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

    public List getShowDetail() {
        return ShowDetail;
    }

    public void setShowDetail(List ShowDetail) {
        this.ShowDetail = ShowDetail;
    }

    public List getTypeList() {
        return TypeList;
    }

    public void setTypeList(List TypeList) {
        this.TypeList = TypeList;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
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

    public List getPreDRList() {
        return PreDRList;
    }

    public void setPreDRList(List PreDRList) {
        this.PreDRList = PreDRList;
    }

    public List getPostDRList() {
        return PostDRList;
    }

    public void setPostDRList(List PostDRList) {
        this.PostDRList = PostDRList;
    }

    public List getPostList() {
        return PostList;
    }

    public void setPostList(List PostList) {
        this.PostList = PostList;
    }

    public List getPreList() {
        return PreList;
    }

    public void setPreList(List PreList) {
        this.PreList = PreList;
    }

    public List getPretype() {
        return pretype;
    }

    public void setPretype(List pretype) {
        this.pretype = pretype;
    }

  
    

    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    public List getActiontype() {
        return actiontype;
    }

    public void setActiontype(List actiontype) {
        this.actiontype = actiontype;
    }

    public List getActionrem() {
        return actionrem;
    }

    public void setActionrem(List actionrem) {
        this.actionrem = actionrem;
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

    public String getPost_docs_sent() {
        return post_docs_sent;
    }

    public void setPost_docs_sent(String post_docs_sent) {
        this.post_docs_sent = post_docs_sent;
    }

    public List getPrerem() {
        return prerem;
    }

    public void setPrerem(List prerem) {
        this.prerem = prerem;
    }
     
            
      
}
