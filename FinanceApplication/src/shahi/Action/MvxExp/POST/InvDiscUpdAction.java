package shahi.Action.MvxExp.POST;

 
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import shahi.Action.database.ConnectionMovexBi;
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date; 
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.POST.Beans.InvDiscBean;
  

public class InvDiscUpdAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    
    private List showList;
    
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
    private String year;
    private String company;
    private String inv_no;
    private List SRNO;
    private List DISCOUNT_AMT;
    private List ADJUST_QTY;
    private Double TOTQTY=0.0;
    private Double TOTFOB=0.0;
    private Double TOTKGS=0.0;
    private Double TOTGR=0.0;
    private Double TOTDISC=0.0;
    private String upd_allow="NO";
    
    private String ac_holder;
  
    private String awbdate;
    private int ship_qty;
    private String saveFlag;
   
  
    private List ShowDetail=new ArrayList();
    private List DBKLIST = new ArrayList();
    private List PRICELIST = new ArrayList();
 
    
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
     //   aausrid = "227350";
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        try {

            Connection conn = null;
           
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch
            try {
                
               
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
                 
                stat1= conn.prepareStatement("select * from pa_auth_mast where user_id=? and prog_name='INVDISCUPD' ") ;
                stat1.setString(1,usrid.trim());
                result1=stat1.executeQuery();
                if (result1.next())
                { upd_allow= "YES";
                } 
                 
                    if (saveFlag!=null && saveFlag.equals("YES"))
                    {
                  
                     if ((DISCOUNT_AMT != null) && (DISCOUNT_AMT.size() > 0))
                     {
                      for(int i=0;i<SRNO.size();i++)
                        {
                      if(DISCOUNT_AMT.get(i)!=null && DISCOUNT_AMT.get(i).toString()!=null && DISCOUNT_AMT.get(i).toString().length()>0 )
                      {   
                          stat2 = conn.prepareStatement("UPDATE ei_endors_dtls set DISCOUNT_AMT=?  where year=? and company=? and inv_no=?  and sr_no=? ");
                          stat2.setString(1,DISCOUNT_AMT.get(i).toString());
                          stat2.setString(2,year);
                          stat2.setString(3,company);
                          stat2.setString(4,inv_no);
                          stat2.setString(5,SRNO.get(i).toString());
                          stat2.executeUpdate();
                          
                          stat1= conn.prepareStatement("insert into ei_inv_disc_hist (year,company,inv_no,sr_no,DISC_AMT,TDATE,SEH_USER) values (?,?,?,?,?,sysdate,?)");
                          stat1.setString(1,year);
                          stat1.setString(2,company);
                          stat1.setString(3,inv_no);
                          stat1.setString(4,SRNO.get(i).toString());
                          stat1.setString(5,DISCOUNT_AMT.get(i).toString());
                          stat1.setString(6,usrid);
                          stat1.executeQuery();
                          flag=1;
                          
                          
                        } 
                        }
                     }
                      
                    }  
                  
              
                if ((viewFlag != null && viewFlag.equals("Yes"))) 
                {  
                     stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(a.inv_date,'dd/mm/yyyy')inv_date,a.exp_type||' '||decode(a.self_tp,'N','Normal','F','Free Sample','S','Trade Sample',self_tp) inv_type,to_char(a.fwd_custom,'dd/mm/yyyy') fwd_custom,to_char(a.tto_date,'dd/mm/yyyy') tto_date,to_char(a.etd_date,'dd/mm/yyyy') etd_date,agent,fwd_code,"
                            + " to_char(a.t_o_date,'dd/mm/yyyy') to_date,to_char(a.doc_send,'dd/mm/yyyy') fwd_date,to_char(a.fin_date,'dd/mm/yyyy') fin_date,a.ac_holder,a.cost_centre,a.mode_of_ship,a.inv_qty,a.buyer,a.buyer_addr,a.cons_addr,a.year,a.company,a.inv_no,LOADING_port,LOADING CLR_port,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,to_char(a.AWBdate,'dd/mm/yyyy') awbdate,a.crncy_code,nvl(a.ship_qty,0) ship_qty from ei_endors_mast a  where  a.excs_inv_no=?  ");
                    stat1.setString(1, searchinv);
                 //   stat1.setString(2,LOCATION_CODE);
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
                        awbdate=result1.getString("awbdate");
                        cost_centre = result1.getString("cost_centre");
                        mode_of_ship = result1.getString("mode_of_ship");
                        inv_qty = result1.getString("inv_qty");
                        buyer = result1.getString("buyer");
                        ship_qty = result1.getInt("ship_qty");
      
                        stat2 = conn.prepareStatement("select co_no,co_line,item_no,pre_print_no bpo,token_no style,sr_no,category,unit,qty_endors,qty_kgs,currency,price_fc,price_misc,net_price,gr_decl_amt,dbk_slno,str_slno,qty_endors*(price_fc+nvl(price_misc,0)) fob_amt,discount_amt,adjust_qty from ei_endors_dtls a where year=? and company=? and inv_no=? order by 1,2,3");
                        stat2.setString(1,year);
                        stat2.setString(2,company);
                        stat2.setString(3,inv_no);
                          result2 = stat2.executeQuery();
                         while (result2.next()) 
                          { TOTQTY=TOTQTY+roundTwoDecimals(result2.getDouble("qty_endors"));
                            TOTKGS=TOTKGS+result2.getDouble("qty_KGS");
                            TOTFOB=roundTwoDecimals(TOTFOB+result2.getDouble("FOB_AMT"));
                            TOTGR=roundTwoDecimals(TOTGR+result2.getDouble("GR_DECL_AMT"));
                            TOTDISC=roundTwoDecimals(TOTDISC+result2.getDouble("DISCOUNT_AMT"));
                        
                            ShowDetail.add(new InvDiscBean(result2.getString("sr_no"),result2.getString("co_no"),result2.getString("co_line"),result2.getString("item_no"),result2.getString("bpo"),result2.getString("style"),result2.getString("unit"),result2.getString("qty_endors"),result2.getString("qty_kgs"),result2.getString("currency"),result2.getString("price_fc"),result2.getString("price_misc"),result2.getString("fob_amt"),result2.getString("net_price"),result2.getString("gr_decl_amt"),result2.getString("dbk_slno"),result2.getString("str_slno"),result2.getString("adjust_qty"),result2.getString("discount_amt")));
                          }         
                        
                           
                          
                           
                    }
                } // View Flg Close      
          
                
                
            
                       
                      
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : InvDiscUpdAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : InvDiscUpdction.java" + e);

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
                     
                    result1 = null;
                    stat1 = null;
                    stat =null;
                    stat2=null;
                    conn = null;
                   
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : PreDiscUpdAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
           
            searchinv=null;
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

   

  
    

    public int getShip_qty() {
        return ship_qty;
    }

    public void setShip_qty(int ship_qty) {
        this.ship_qty = ship_qty;
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
  
    public List getSRNO() {
        return SRNO;
    }

    public void setSRNO(List SRNO) {
        this.SRNO = SRNO;
    }

   
    public String getAwbdate() {
        return awbdate;
    }

    public void setAwbdate(String awbdate) {
        this.awbdate = awbdate;
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

    public Double getTOTKGS() {
        return TOTKGS;
    }

    public void setTOTKGS(Double TOTKGS) {
        this.TOTKGS = TOTKGS;
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

    public List getDISCOUNT_AMT() {
        return DISCOUNT_AMT;
    }

    public void setDISCOUNT_AMT(List DISCOUNT_AMT) {
        this.DISCOUNT_AMT = DISCOUNT_AMT;
    }

    public List getADJUST_QTY() {
        return ADJUST_QTY;
    }

    public void setADJUST_QTY(List ADJUST_QTY) {
        this.ADJUST_QTY = ADJUST_QTY;
    }

    public String getUpd_allow() {
        return upd_allow;
    }

    public void setUpd_allow(String upd_allow) {
        this.upd_allow = upd_allow;
    }

    public List getDBKLIST() {
        return DBKLIST;
    }

    public void setDBKLIST(List DBKLIST) {
        this.DBKLIST = DBKLIST;
    }

    public List getPRICELIST() {
        return PRICELIST;
    }

    public void setPRICELIST(List PRICELIST) {
        this.PRICELIST = PRICELIST;
    }
 
       
}
 