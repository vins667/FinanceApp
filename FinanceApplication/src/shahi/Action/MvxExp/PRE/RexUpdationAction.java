package shahi.Action.MvxExp.PRE;

import shahi.Action.MvxExp.PRE.Beans.RexDescDetailBean;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.PRE.Beans.RexDetailBean;

public class RexUpdationAction extends ActionSupport {

    private String currentdate;
    private String viewFlag;
    private String getFlag;
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
    private double nqty;
    private double nkgs;
    private List SRNO;
    private String ac_holder;
    private String merchant;
    private String awbdate;
    private String ship_type;
     private String MAXDATE;
    
    private String ship_cancel;//invoice cancel
    private int ship_qty;
    private String saveFlag;
    private List ShowDetail = new ArrayList();
    private List DBKLIST = new ArrayList();
    private List PRICELIST = new ArrayList();
    private int flag4;
    private List CONO;
    private List COLINE;
    private List ITEMNO;
    private List BPONO;
    private List STYLENO;
    private List CATEGORYNO;
    private List UOMNO;
    private List QTYENDORS;
    private List CRNCYCODE;
    private List NETRPICE;
    private List REXHSCODE;
    private List REXHSDESC;
    private List REXCODEDESC;
    private String AUTH_DATE;
    private List REX_TYPE  ;
    private String INVFLAG;
   

    @Override
    public String execute() {
        showList = new ArrayList();
        try {
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        } catch (Exception e) {
            System.out.println(e.toString());
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
                stat1 = conn.prepareStatement("select location_code,to_char(sysdate,'yyyy-mm-dd') maxdate from seh_web_users where user_id=?");
                stat1.setString(1, usrid);
                result1 = stat1.executeQuery();
                while (result1.next()) {
                    LOCATION_CODE = result1.getString("location_code");
                     MAXDATE=result1.getString("maxdate");
                }

   


                if ((viewFlag != null || (getFlag != null && getFlag.equals("Yes")))) {
                    REX_TYPE=null; 
                    AUTH_DATE=null;
                    stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.plan_no,to_char(a.inv_date,'dd/mm/yyyy')inv_date,a.exp_type||' '||decode(a.self_tp,'N','Normal','F','Free Sample','S','Trade Sample',self_tp) inv_type,to_char(a.fwd_custom,'dd/mm/yyyy') fwd_custom,to_char(a.tto_date,'dd/mm/yyyy') tto_date,to_char(a.etd_date,'dd/mm/yyyy') etd_date,agent,fwd_code,"
                            + " to_char(a.t_o_date,'dd/mm/yyyy') to_date,to_char(a.doc_send,'dd/mm/yyyy') fwd_date,to_char(a.fin_date,'dd/mm/yyyy') fin_date,a.ac_holder,a.cost_centre,a.mode_of_ship,a.inv_qty,a.buyer,a.buyer_addr,a.cons_addr,a.year,a.company,a.inv_no,LOADING_port,LOADING CLR_port,"
                            + " DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,MERCHANT_NAME,to_char(a.AWBdate,'dd/mm/yyyy') awbdate,a.crncy_code,a.lcno,nvl(a.ship_qty,0) ship_qty,a.surrender_yn ship_cancel,nvl(a.SHIP_DESC,' ') SHIP_DESC,a.manuf_state,a.tax_type,a.tax_percent,a.tax_code,a.tax_cal_per,"
                            + " a.ex_inv_slno,a.ex_inv_date,a.ci_no,a.CI_NO,to_char(a.CI_date,'dd/mm/yyyy') ci_date,a.sap_del_date from ei_endors_mast a  where  a.excs_inv_no=? and a.location=?");
                    stat1.setString(1, searchinv.trim());
                    stat1.setString(2, LOCATION_CODE.trim());
                    result1 = stat1.executeQuery();
                    if (result1.next()) {
                        year = result1.getString("year");
                        company = result1.getString("company");
                        inv_no = result1.getString("inv_no");
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
                        merchant = result1.getString("MERCHANT_NAME");
                        awbdate = result1.getString("awbdate");
                        cost_centre = result1.getString("cost_centre");
                        mode_of_ship = result1.getString("mode_of_ship");
                        inv_qty = result1.getString("inv_qty");
                        buyer = result1.getString("buyer");
                        ship_type = result1.getString("ship_type").trim();
                        ship_cancel = result1.getString("ship_cancel");
                        ship_qty = result1.getInt("ship_qty");



                        int x = 0;
                        stat2 = conn.prepareStatement("select c.co_no,c.co_line,c.item_no,b.pre_print_no bpo,b.token_no style,b.sr_no,b.category,b.unit,"
                                + "b.qty_endors,b.currency,b.net_price,c.REX_HS,c.REX_DESC,c.year,c.company,c.inv_no,to_char(c.auth_date,'yyyy-mm-dd') auth_date,rex_type from ei_endors_dtls b,ei_endors_rex c "
                                + "where c.year=b.year and c.company=b.company and c.inv_no=b.inv_no and c.CO_NO=b.CO_NO and c.CO_LINE=b.CO_LINE "
                                + "and c.ITEM_NO=b.ITEM_NO and c.year=? and c.company=? and c.inv_no=? order by 1,2,3");
                        stat2.setString(1, year);
                        stat2.setString(2, company);
                        stat2.setString(3, inv_no);
                        result2 = stat2.executeQuery();
                        while (result2.next()) {
                            AUTH_DATE=result2.getString("auth_date");
                            ShowDetail.add(new RexDescDetailBean(result2.getString("sr_no"), result2.getString("co_no"), result2.getString("co_line"),
                                    result2.getString("item_no"), result2.getString("bpo"), result2.getString("style"), result2.getString("category"),
                                    result2.getString("unit"), result2.getString("qty_endors"), result2.getString("currency"), result2.getString("net_price"),
                                    result2.getString("REX_HS"), result2.getString("REX_DESC"),result2.getString("year"),result2.getString("company"),result2.getString("inv_no"),result2.getString("REX_TYPE")));
                            ++x;
                        }
                        if (stat2 != null) {
                            stat2.close();
                        }
                        if (result2 != null) {
                            result2.close();
                        }


                        if (x == 0) {
                            stat2 = conn.prepareStatement("select co_no,co_line,item_no,pre_print_no bpo,token_no style,sr_no,category,unit,qty_endors,"
                                    + "currency,net_price,DESCRIPTION,HSCODE1,year,company,inv_no from ei_endors_dtls where year=? and company=? and inv_no=? order by 1,2,3");
                            stat2.setString(1, year);
                            stat2.setString(2, company);
                            stat2.setString(3, inv_no);
                            result2 = stat2.executeQuery();
                            while (result2.next()) {
                                INVFLAG="YES";
                                ShowDetail.add(new RexDetailBean(result2.getString("sr_no"), result2.getString("co_no"), result2.getString("co_line"), 
                                        result2.getString("item_no"), result2.getString("bpo"), result2.getString("style"), result2.getString("category"),
                                        result2.getString("unit"), result2.getString("qty_endors"), result2.getString("currency"), result2.getString("net_price"),
                                        result2.getString("DESCRIPTION"),result2.getString("HSCODE1"),result2.getString("year"),result2.getString("company"),result2.getString("inv_no")));
                                ++flag4;
                            }

                        }
                    }
                } // View Flg Close      







            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : InvKgsUpdAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : InvKgsUpdction.java" + e);

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
                    stat = null;
                    stat2 = null;
                    conn = null;
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : PreKgsUpdAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {

            searchinv = null;
            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");

            return ERROR;
        }
    }

    public String update1() throws SQLException {
        Connection conn = null;
         
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


        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
      //  LOCATION_CODE = "100";

        try {
            conn = new connection().getConnection();
            conn.setAutoCommit(false);

            int x = 0, y = 0, z = 0;
             
                stat5=conn.prepareStatement("select * from ei_endors_rex where year=? and company=? and inv_no=? ");
                stat5.setString(1,year);
                stat5.setString(2,company);
                stat5.setString(3,inv_no);
                result5=stat5.executeQuery();
              if (result5.next())        

             {
                for (int i = 0; i < CONO.size(); i++) {
                    if (CONO != null && CONO.get(i).toString().length() > 0) {
                        stat4 = conn.prepareStatement("update ei_endors_rex set REX_HS=?,REX_DESC=?,mod_date=sysdate,mod_USER=?,rex_type=?,AUTH_DATE=to_date(?,'yyyy-mm-dd') where year=? and company=? and inv_no=? and CO_NO=? and CO_LINE=? and ITEM_NO=?");
                        stat4.setString(1, REXHSCODE.get(i).toString().toUpperCase());
                        stat4.setString(2, REXHSDESC.get(i).toString().trim().toUpperCase());
                        stat4.setString(3, usrid);
                        stat4.setString(4, REX_TYPE.get(i).toString().toUpperCase());
                        if (AUTH_DATE.length()>8)
                            {  stat4.setString(5,AUTH_DATE.substring(0,10));}
                            else
                            {  stat4.setString(5,AUTH_DATE);}
                        stat4.setString(6, year);
                        stat4.setString(7, company);
                        stat4.setString(8, inv_no);
                        stat4.setString(9, CONO.get(i).toString());
                        stat4.setString(10, COLINE.get(i).toString());
                        stat4.setString(11, ITEMNO.get(i).toString());
                        z = stat4.executeUpdate();
                        if (z > 0) {
                            ++z;
                            conn.commit();
                        }
                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
               


                if (z > 0) {
                    addActionMessage("Record Updated succcessfully ");

                    REXHSCODE = null;
                    REXHSDESC = null;
                    REX_TYPE=null;
                    AUTH_DATE=null;
                }

               

            } else {

                for (int i = 0; i < CONO.size(); i++) {
                    if (CONO != null && CONO.get(i).toString().length() > 0) {
                        stat4 = conn.prepareStatement("insert into ei_endors_rex(year,company,inv_no,CO_NO,CO_LINE,ITEM_NO,REX_HS,REX_DESC,REX_TYPE,AUTH_DATE,TDATE,SEH_USER) values(?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),sysdate,?)");
                        stat4.setString(1, year);
                        stat4.setString(2,company);
                        stat4.setString(3, inv_no);
                        stat4.setString(4, CONO.get(i).toString());
                        stat4.setString(5, COLINE.get(i).toString());
                        stat4.setString(6, ITEMNO.get(i).toString());
                        stat4.setString(7, REXHSCODE.get(i).toString().toUpperCase());
                        stat4.setString(8, REXHSDESC.get(i).toString().toUpperCase());
                        stat4.setString(9, REX_TYPE.get(i).toString().toUpperCase());
                        if (AUTH_DATE.length()>8)
                            {  stat4.setString(10,AUTH_DATE.substring(0,10));}
                            else
                            {  stat4.setString(10,AUTH_DATE);}
                        stat4.setString(11, usrid);

                        z = stat4.executeUpdate();
                        if (z > 0) {
                            ++z;
                            conn.commit();
                        }


                    }
                }
                if (stat4 != null) {
                    stat4.close();
                }
              


                if (z > 0) {
                    addActionMessage("Record Inserted succcessfully ");

                    REXHSCODE = null;
                    REXHSDESC = null;
                }

               
              
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
                 if (stat4 != null) {
                    stat4.close();
                }
            }
        }


         //execute();

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

    public String getGetFlag() {
        return getFlag;
    }

    public void setGetFlag(String getFlag) {
        this.getFlag = getFlag;
    }

    

    public double getNqty() {
        return nqty;
    }

    public void setNqty(double nqty) {
        this.nqty = nqty;
    }

    public List getSRNO() {
        return SRNO;
    }

    public void setSRNO(List SRNO) {
        this.SRNO = SRNO;
    }

    public double getNkgs() {
        return nkgs;
    }

    public void setNkgs(double nkgs) {
        this.nkgs = nkgs;
    }

    public String getAwbdate() {
        return awbdate;
    }

    public void setAwbdate(String awbdate) {
        this.awbdate = awbdate;
    }

    public int getFlag4() {
        return flag4;
    }

    public void setFlag4(int flag4) {
        this.flag4 = flag4;
    }

    public List getREXHSCODE() {
        return REXHSCODE;
    }

    public void setREXHSCODE(List REXHSCODE) {
        this.REXHSCODE = REXHSCODE;
    }

    public List getREXHSDESC() {
        return REXHSDESC;
    }

    public void setREXHSDESC(List REXHSDESC) {
        this.REXHSDESC = REXHSDESC;
    }

    public List getREXCODEDESC() {
        return REXCODEDESC;
    }

    public void setREXCODEDESC(List REXCODEDESC) {
        this.REXCODEDESC = REXCODEDESC;
    }

    public List getCONO() {
        return CONO;
    }

    public void setCONO(List CONO) {
        this.CONO = CONO;
    }

    public List getCOLINE() {
        return COLINE;
    }

    public void setCOLINE(List COLINE) {
        this.COLINE = COLINE;
    }

    public List getITEMNO() {
        return ITEMNO;
    }

    public void setITEMNO(List ITEMNO) {
        this.ITEMNO = ITEMNO;
    }

    public List getBPONO() {
        return BPONO;
    }

    public void setBPONO(List BPONO) {
        this.BPONO = BPONO;
    }

    public List getSTYLENO() {
        return STYLENO;
    }

    public void setSTYLENO(List STYLENO) {
        this.STYLENO = STYLENO;
    }

    public List getCATEGORYNO() {
        return CATEGORYNO;
    }

    public void setCATEGORYNO(List CATEGORYNO) {
        this.CATEGORYNO = CATEGORYNO;
    }

    public List getUOMNO() {
        return UOMNO;
    }

    public void setUOMNO(List UOMNO) {
        this.UOMNO = UOMNO;
    }

    public List getQTYENDORS() {
        return QTYENDORS;
    }

    public void setQTYENDORS(List QTYENDORS) {
        this.QTYENDORS = QTYENDORS;
    }

    public List getCRNCYCODE() {
        return CRNCYCODE;
    }

    public void setCRNCYCODE(List CRNCYCODE) {
        this.CRNCYCODE = CRNCYCODE;
    }

    public List getNETRPICE() {
        return NETRPICE;
    }

    public void setNETRPICE(List NETRPICE) {
        this.NETRPICE = NETRPICE;
    }

    public String getAUTH_DATE() {
        return AUTH_DATE;
    }

    public void setAUTH_DATE(String AUTH_DATE) {
        this.AUTH_DATE = AUTH_DATE;
    }

    public List getREX_TYPE() {
        return REX_TYPE;
    }

    public void setREX_TYPE(List REX_TYPE) {
        this.REX_TYPE = REX_TYPE;
    }

    public String getMAXDATE() {
        return MAXDATE;
    }

    public void setMAXDATE(String MAXDATE) {
        this.MAXDATE = MAXDATE;
    }

    public String getINVFLAG() {
        return INVFLAG;
    }

    public void setINVFLAG(String INVFLAG) {
        this.INVFLAG = INVFLAG;
    }
 
    
    
    
}
