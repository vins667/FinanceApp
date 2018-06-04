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
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.POST.Beans.PostChargeBean;


  

public class PostAjaxAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private String INVOICE_NO_S;
    private String LIC_NO;
    private String AWBDATE_AJ;
    private String SBDATE_AJ;
    private String LETEXP_AJ;
    private String SBNO_AJ;
    private String FACT_S;
    private List   CHRGLIST = new ArrayList();
    
    private String searchdate;
    private String searchterm;
    private String searchawb;
    private String aausrid;
    private ByteArrayInputStream inputStream;
    private String P_YEAR;
    private String P_LINK_NO;
  
    @Override
    public String execute() {
         
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
        try {

            Connection conn = null;
            Connection connBI = null;
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
               
               stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
               stat1.setString(1,usrid);
               result1=stat1.executeQuery();
                while (result1.next())
                {LOCATION_CODE=result1.getString("location_code");
                } 
                
                                   
                      
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : PostAjaxAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : PostAjaxAction.java" + e);

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
                    flag = 0;
                    System.out.print("File Name : PostAjaxAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
           
            searchawb=null;
            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");
             
            return ERROR;
        }
    } 
      public String ajaxPortalInv() throws SQLException {
        String ls = "";
       
         
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                    //System.out.println(str);
                    conn = new connection().getConnection();
                    st = conn.prepareStatement(" select buyer,cost_centre,to_char(inv_date,'dd/mm/yyyy') inv_date,to_char(t_o_date,'dd/mm/yyyy') t_o_date,to_char(etd_date,'dd/mm/yyyy') etd_date,to_char(doc_send,'dd/mm/yyyy') doc_send,to_char(awbdate,'dd/mm/yyyy') awbdate,crncy_code,mode_of_ship,desti_cntry "
                                             + " from ei_endors_mast a where nvl(surrender_yn,'N')='N' and fin_date is null and t_o_date is not null and excs_inv_no not in (select all_no from ei_truckout_track where tr_type='PU') and excs_inv_no=?  ");
                    st.setString(1, INVOICE_NO_S);
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls+=rs.getString("buyer")+"#"+rs.getString("cost_centre")+"#"+rs.getString("inv_date")+"#"+rs.getString("t_o_date")+"#"+rs.getString("etd_date")+"#"+rs.getString("doc_send")+"#"+rs.getString("awbdate")+"#"+rs.getString("crncy_code")+"#"+rs.getString("mode_of_ship")+"#"+rs.getString("desti_cntry");
                           
                    }  
                     
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }
      public String ajaxFittInv() throws SQLException {
        String ls = "";
       
         
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                    //System.out.println(str);
                    conn = new connection().getConnection();
                    st = conn.prepareStatement(" select buyer,cost_centre,to_char(inv_date,'dd/mm/yyyy') inv_date,to_char(t_o_date,'dd/mm/yyyy') t_o_date,to_char(etd_date,'dd/mm/yyyy') etd_date,to_char(doc_send,'dd/mm/yyyy') doc_send,to_char(awbdate,'dd/mm/yyyy') awbdate,to_char(fin_date,'dd/mm/yyyy') fin_date,crncy_code,sum(b.qty_endors) inv_qty,sum(qty_endors*(price_fc+nvl(price_misc,0))) fobfc,sum(gr_decl_amt) grdecl "
                                             + " from ei_endors_mast a, ei_endors_dtls b where nvl(surrender_yn,'N')='N' and a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and   excs_inv_no=? group by buyer,cost_centre,inv_date,t_o_date,etd_date,doc_send,awbdate,fin_date,crncy_code   ");
                    st.setString(1, INVOICE_NO_S);
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls+=rs.getString("buyer")+"#"+rs.getString("cost_centre")+"#"+rs.getString("inv_date")+"#"+rs.getString("t_o_date")+"#"+rs.getString("etd_date")+"#"+rs.getString("doc_send")+"#"+rs.getString("awbdate")+"#"+rs.getString("fin_date")+"#"+rs.getString("crncy_code")+"#"+rs.getString("inv_qty")+"#"+rs.getString("fobfc")+"#"+rs.getString("grdecl");
                           
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }
     
      public String ajaxMeisLic() throws SQLException {
        String ls = "";
         
          
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                  
                    conn = new connection().getConnection();
                    st = conn.prepareStatement(" select lic_no,to_char(LIC_date,'dd/mm/yyyy') lic_date,lic_amt,PORT_CODE,0 prct,0 sale_amt from  ei_mlfs_mast where  nvl(sale_amt,0)=0 and fwd_ac is not null and lic_no like ? order by 1   ");
                    st.setString(1,INVOICE_NO_S.toUpperCase() + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                      
                          ls+=rs.getString("lic_no")+"#"+rs.getString("lic_date")+"#"+rs.getString("PORT_CODE")+"#"+rs.getString("lic_amt")+"#"+rs.getString("prct")+"#"+rs.getString("sale_amt");
                            
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }
   
      ////  FOR 77 DOCS 
       public String ajaxAWBFIN() throws SQLException {
        String ls = "";
         
          
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null;
              PreparedStatement st1=null;
              ResultSet rs=null;
              ResultSet rs1=null;
           
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                  String pu_date=null;
                    conn = new connection().getConnection();
                    st = conn.prepareStatement("select awb_no,to_char(awb_date,'dd-Mon-yyyy') awb_date,h_awb_no,year,link_no from ei_shipment_mast where ac_send_date is  null and t_mod='LGM4' AND awb_no like ? order by 1   ");
                    st.setString(1,INVOICE_NO_S.toUpperCase() + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                       st1=conn.prepareStatement("select to_char(min(tr_date),'yyyy-mm-dd') pudate from ei_truckout_track where TR_TYPE='PU' AND (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls where year=? and link_no=?)");
                       st1.setString(1,rs.getString("year"));
                       st1.setString(2,rs.getString("link_no"));
                       rs1=st1.executeQuery();
                       if (rs1.next())
                       {
                         pu_date=rs1.getString("pudate");
                       }
                     
                          ls+=rs.getString("awb_no")+"#"+rs.getString("awb_date")+"#"+rs.getString("h_awb_no")+"#"+pu_date+"#"+rs.getString("year")+"#"+rs.getString("link_no");
                        
                       //CHRGLIST = DocFinCharges(rs.getString("year"),rs.getString("link_no"));   
                          
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {rs.close(); }
                if (st != null) {st.close(); }
                if (rs1 != null) {rs1.close(); }
                if (st1 != null) {st1.close(); }
                if (conn != null){conn.close();}
 
            }
        }
        return "success";
    }     
       
            public String ajaxSBChq() throws SQLException {
        String ls = "";
         
          
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null;
              PreparedStatement st1=null;
              ResultSet rs=null;
              ResultSet rs1=null;
           
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                  String pu_date=null;
                    conn = new connection().getConnection();
                    st = conn.prepareStatement("select shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') sb_date,claim_port,nvl(dbk_admisable,0) dbk_admisable,nvl(dbk_received,0) dbk_received,nvl(dbk_adjusted,0) dbk_adjusted,"
                            + "                 nvl(DBK_SUPL_RECV,0) dbk_supl_recv,nvl(woff_amt,0) dbk_woff,nvl(str_recv,0) str_recv,nvl(str_due,0) str_due,nvl(str_woff,0) str_woff,nvl(rosl_due,0) rosl_due,nvl(rosl_recv,0) rosl_recv from ei_dbk_mast where shp_bill_no like ? order by 1   ");
                    st.setString(1,INVOICE_NO_S.toUpperCase() + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                              
                        ls+=rs.getString("shp_bill_no")+"#"+rs.getString("sb_date")+"#"+rs.getString("claim_port")+"#"+rs.getString("dbk_admisable")+"#"+rs.getString("dbk_received")+"#"+rs.getString("DBK_SUPL_RECV")+"#"+rs.getString("dbk_adjusted")+"#"+rs.getString("dbk_woff")+"#"+rs.getString("str_due")+"#"+rs.getString("str_recv")+"#"+rs.getString("str_woff")+"#"+rs.getString("rosl_due")+"#"+rs.getString("rosl_recv");
                           
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {rs.close(); }
                if (st != null) {st.close(); }
                if (rs1 != null) {rs1.close(); }
                if (st1 != null) {st1.close(); }
                if (conn != null){conn.close();}
 
            }
        }
        return "success";
    }       
   
       
       
       
    /*  public List DocFinCharges( String P_YEAR,String P_LINK_NO) throws SQLException {
       
         
          
        if (P_YEAR != null && P_LINK_NO!=null) {
              Connection conn = null;
              Connection conndb2=null;
              PreparedStatement st=null;
              PreparedStatement st1=null;
              ResultSet rs=null;
              ResultSet rs1=null;
            try {
                System.out.println(" p_year "+P_YEAR+" LINK "+P_LINK_NO);
                if (P_YEAR != null && P_YEAR !=null) {
                    conn = new connection().getConnection();
                    conndb2 = new connectiondb2().getConnection();
                    st = conn.prepareStatement("select distinct ex_track_no,co_numb,del_numb from ei_ship_Data a,ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.year=? and b.link_no=?   ");
                    st.setString(1,P_YEAR);
                    st.setString(2,P_LINK_NO);
                    rs = st.executeQuery();
                    while (rs.next()) {
                        System.out.println("sanjeev-1 "+rs.getString("co_numb"));
                          double dbkamt=0.0; double stramt=0.0; double roslamt=0.0; double taxamt=0.0;
                          st1=conndb2.prepareStatement(" SELECT  OEORNO ,oedlix,oecrid,oeacrf,OECRD0,OECRAM,oecrfa from m3fdbprd.oochrg where oecono='111' and oeorno=? and oedlix=? ");
                          st1.setString(1,rs.getString("co_numb"));
                          st1.setString(2,rs.getString("del_numb"));
                          rs1=st1.executeQuery();
                          while (rs1.next())
                          { System.out.println("sanjeev-2 "+rs.getString("ex_track_no"));
                            CHRGLIST.add(new PostChargeBean(rs.getString("ex_track_no"),rs1.getString("OEORNO"),rs1.getString("oedlix"),rs1.getString("oecrid"),rs1.getString("oeacrf"),rs1.getString("OECRAM"),dbkamt,stramt,roslamt,taxamt,rs1.getString("oecrfa")));
                               
                          } 
                    } 
                    
                   
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {rs.close();}
                if (st != null) {st.close();}
                if (conn != null){conn.close();}
                if (rs1 != null) {rs1.close();}
                if (st1 != null) {st1.close();}
                if (conndb2 != null){conndb2.close();}
            }
        }
        return CHRGLIST;
    }*/
       
      
      
      
      
      public String ajaxLC() throws SQLException {
        String ls = "";
         
          
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                
                    conn = new connection().getConnection();
                    st = conn.prepareStatement(" select ref_no,value_fc,exp_util_fc,nvl(percent,0) percent from  ei_lc_lic_mast where ref_type='LC' and ref_no like ? order by 1   ");
                    st.setString(1,INVOICE_NO_S.toUpperCase() + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                      
                          ls+=rs.getString("ref_no")+"#"+rs.getString("value_fc")+"#"+rs.getString("exp_util_fc")+"#"+rs.getString("percent");
                            
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }
          
                
          public String ajaxBank() throws SQLException {
        String ls = "";
         
              
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conndb2 = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                 
                    conndb2 = new connectiondb2().getConnection();
                    st = conndb2.prepareStatement(" SELECT trim(BRBKBM) brbkbm,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn  from m3fdbprd.cbanbr where brcono=111 and  trim(BRBKNO) like ? order by 1   ");
                    st.setString(1,INVOICE_NO_S.toUpperCase()+ "%");
                
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls+=rs.getString("BRBKNO")+"#"+rs.getString("BRBBRN")+"#"+rs.getString("BRBKBM")+"#"+rs.getString("bankaddr");
                        
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conndb2 != null) {
                    conndb2.close();
                }
 
            }
        }
        return "success";
    } 
    ///////////////// Check Post Shipment Entry
          
        public String ajaxValidateInvoice() throws SQLException {
        String ls = "";
         
          
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              Connection conndb2=null;
              PreparedStatement st=null;
              PreparedStatement st1=null;
              ResultSet rs=null;
              ResultSet rs1=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                   
                     String errmsg="";
                    conn = new connection().getConnection();
                    conndb2 = new connectiondb2().getConnection();  
                     String xstepy=""; String xsnody="";  BigDecimal INRCONV= new BigDecimal("0.0"); BigDecimal DBKCONV=new BigDecimal("0.0");
                     int aa=0; int bb=0;
                        st = conn.prepareStatement("select * from ei_shipment_dtls where all_no=?   ");
                        st.setString(1,INVOICE_NO_S.trim());
                        rs = st.executeQuery();
                        if (rs.next()) {
                             errmsg="Post Shipment Already Exist... " ;
                         } 
                         if (rs != null) {rs.close();}
                         if (st!=null){st.close();}
                        
                      /*  st = conn.prepareStatement("select * from ei_shipment_dtls where trIM(shp_bill_no)=? and to_char(shp_bill_date,'yyyymmdd')=?   ");
                        st.setString(1,SBNO_AJ.trim());
                         st.setString(2,SBDATE_AJ);
                        rs = st.executeQuery();
                        if (rs.next()) {
                             errmsg="S/B already exists.. " ;
                         } 
                       */
                        int inv=0;
                        st = conn.prepareStatement("select year,company,inv_no,count(*) aa from ei_endors_dtls where all_no=? group by year,company,inv_no having count(*)>1 ");
                        st.setString(1,INVOICE_NO_S.trim());
                        rs = st.executeQuery();
                        while (rs.next()) {
                            inv=inv+1;
                         }  
                        if (inv>1)
                        {errmsg=errmsg+" More then one Record Found ...  " ;}
                         if (rs != null) {rs.close();}
                         if (st!=null){st.close();}
                          
                        
                        st = conn.prepareStatement(" select  A.year,A.inv_no,a.plan_no,t_o_date,tto_date,doc_send,excise_unit,crncy_code,surrender_yn,buyer,buyer_addr,a.location,to_char(inv_date,'yyyymmdd') invdt,mode_of_ship,"+
                                                   " loading,agent,desti_cntry,round(sum(b.qty_endors),0) iqty,ROUND(sum(gr_decl_amt),2) igr,ROUND(sum(nvl(qty_endors,0)*(nvl(price_fc,0)+nvl(price_misc,0))),2) ifob "+
                                                  " from ei_endors_mast A,ei_endors_dtls B where A.T_MOD='LGM4' AND A.YEAR=B.YEAR AND A.COMPANY=B.COMPANY AND A.INV_NO=B.INV_NO AND A.excs_inv_no=? "+
                                                   " group by A.year,A.inv_no,a.plan_no,t_o_date,tto_date,doc_send,excise_unit,crncy_code,surrender_yn,buyer,buyer_addr,a.location,to_char(inv_date,'yyyymmdd'),mode_of_ship,loading,agent,desti_cntry");
                        st.setString(1,INVOICE_NO_S.trim());
                        rs = st.executeQuery();
                        if (rs.next()) {
                            
                            if (rs.getString("t_o_date")==null || rs.getString("t_o_date").length()==0 )
                            {
                              errmsg=errmsg+" T/O Date Not Updated ...   " ;
                            }
                             if (rs.getString("tto_date")==null || rs.getString("tto_date").length()==0 )
                            {
                              errmsg=errmsg+" TTO Date Not Updated ...   " ;
                            }
                             if (rs.getString("doc_send")==null || rs.getString("doc_send").length()==0 )
                            {
                              errmsg=errmsg+" Document Not Fwd to Post ... " ;
                            }
                            if (rs.getString("surrender_yn")!=null  )
                            {
                              errmsg=errmsg+" Shipment Cancelled ...   ";
                            }
                            if (rs.getString("crncy_code").equals("INR") && (rs.getString("excise_unit")!=null) )
                            {
                                st1 = conn.prepareStatement("select * from ei_endors_dtls where nvl(mrp_rate,0)=0 and nvl(price_fc,0)>0 and  all_no=? ");
                                st1.setString(1,INVOICE_NO_S.trim());
                                rs1 = st1.executeQuery();
                                if (rs1.next()) {
                                     errmsg=errmsg+" MRP Price Not Updated  ...   ";
                                 }
                             }
                             if (rs1 != null) {rs1.close();}
                             if (st1 != null) {st1.close();}
                            if (rs.getString("LOCATION").equals("200") )
                            {
                                st1 = conn.prepareStatement("select * from ei_buyer_req_mast where trim(buyer)=? ");
                                st1.setString(1,rs.getString("BUYER").trim());
                                rs1 = st1.executeQuery();
                                if (rs1.next()==false) {
                                     errmsg=errmsg+" Buyer Master Not Found for EDI Checking  ...   ";
                                 }
                             }
                             if (rs1 != null) {rs1.close();}
                             if (st1 != null) {st1.close();}
                         
                             st1=conndb2.prepareStatement("select trim(zatepy) zatepy,zanody from cinfdbprd.zadtrm  where zacono=111 and zaadrt=1 and trim(zacuno)=? and trim(zaadid)=? and ? between zafrdt and zatodt");
                             st1.setString(1,rs.getString("BUYER").trim());
                             st1.setString(2,rs.getString("BUYER_ADDR").trim());
                             st1.setString(3,rs.getString("invdt"));
                             rs1 = st1.executeQuery();
                             while (rs1.next())
                             {   
                                xstepy=rs1.getString("zatepy");
                                xsnody=rs1.getString("zanody");
                             }
                                   
                             if (xstepy==null || xstepy.length()==0)
                             { 
                               errmsg=errmsg+"Buyer Payment Term not found for Due Date Calc, Buyer:"+rs.getString("BUYER")+" :AddrNo:"+rs.getString("BUYER_ADDR");
                             }
                             if (rs1 != null) {rs1.close();}
                             if (st1 != null) {st1.close();}
                             st1=conndb2.prepareStatement(" select cuarat from m3fdbprd.ccurra c where c.cucutd = (select max(y.cucutd) from m3fdbprd.ccurra y where c.cucono = y.cucono and c.cucucd = y.cucucd "+
                                                         " and   c.cucrtp = y.cucrtp and c.culocd = y.culocd and CUCUTD <=? ) and c.cucono=111 and c.cucucd = ? and cucrtp = '1' and culocd = 'INR' ");
                             st1.setString(1,AWBDATE_AJ);
                             st1.setString(2,rs.getString("crncy_code"));
                              rs1 = st1.executeQuery();
                              while (rs1.next())
                             {  aa=rs1.getInt("cuarat");
                                INRCONV=rs1.getBigDecimal("cuarat");
                             }
                             if (rs1 != null) {rs1.close();}
                             if (st1 != null) {st1.close();}
                             if (aa==0)
                             {
                              errmsg=errmsg+"Check InrConv AwbDate---";
                             }
                             st1=conn.prepareStatement(" SELECT EXP_RATE  FROM EI_EXCHANGE_RATE_MAST WHERE CURRENCY= ? and ? between to_char(begin_date,'yyyymmdd') and to_char(end_date,'yyyymmdd') ");
                               st1.setString(1,rs.getString("crncy_code"));
                               st1.setString(2,SBDATE_AJ);
                               rs1 = st1.executeQuery();
                              if (rs1.next())
                             {  bb=rs1.getInt("exp_rate");
                                DBKCONV=rs1.getBigDecimal("EXP_RATE");
                             }
                             if (rs1 != null) {rs1.close();}
                             if (st1 != null) {st1.close();}
                             if (bb==0)
                             {
                              errmsg=errmsg+"Check DBK Conv SBDate---";
                             }
                              
                            ////////////////////////
                           //  CHECKING MOVEX VALIDATION  
                             
                              String  p_error = MVXDATA_VALIDATE(rs.getString("plan_no"),rs.getDouble("iqty"),conn,conndb2);
                              
                             
                          ///////////////   
                             errmsg=errmsg+p_error;
                             
                            if (errmsg!=null && errmsg.length()>5)
                             {errmsg=errmsg+" Against Invoice No:-"+INVOICE_NO_S+"'#'";}
                            else{errmsg="NOERROR";}
                            
                             ls+=errmsg+"#"+rs.getString("crncy_code")+"#"+rs.getString("mode_of_ship")+"#"+rs.getString("loading")+"#"+rs.getString("buyer")+"#"+rs.getString("agent")+"#"+rs.getString("desti_cntry")+"#"+rs.getString("iqty")+"#"+rs.getString("ifob")+"#"+rs.getString("igr")+"#"+xstepy+"#"+xsnody+"#"+INRCONV+"#"+DBKCONV+"#"+SBDATE_AJ+"#"+LETEXP_AJ+'#'+rs.getString("year")+'#'+rs.getString("inv_no");
                 
                            
                         } /// rs next closed
                         
                        
                        if ((ls != null) && (!ls.equals(""))) {
                              this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                        } else {
                             this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                         }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {rs.close();}
                if (st != null) {st.close();}
                if (rs1 != null) {rs1.close();}
                if (st1 != null) {st1.close();}
                if (conn != null) {conn.close();}
                if (conndb2 != null) {conndb2.close();}
            }
        }
        return "success";
    }    
        
        
        
     public String MVXDATA_VALIDATE(String P_PLAN,Double P_INVQTY,Connection conn,Connection conndb2)
    {
                        PreparedStatement pst2=null;  
                        PreparedStatement pst3=null; 
                        PreparedStatement pst1=null;
                        PreparedStatement pst4=null; 
                        PreparedStatement stat1=null;
                        ResultSet result1=null;
                        ResultSet rs1=null;
                        ResultSet rs2=null; 
                        ResultSet rs3=null;
                        ResultSet rs4=null;
                        String p_error=""; 
                        
                            
                          try{
                                   int v_status=0;    
                                 String Sql1=" select co_numb,m3_del_numb,sum(round(pack_qty,0)) pqty  from seplweb.pr_pack_box_ir_detail a " 
                                            +" where  a.pack_numb=? group by co_numb,m3_del_numb";
                                        pst1=conn.prepareStatement(Sql1);
                                        pst1.setString(1,P_PLAN);
                                        rs1=pst1.executeQuery();
                                        while(rs1.next())
                                        {
                                              String Sql2=" select  sum(round(ubdlqt+ubivqt,0)) dqty from  m3fdbprd.odLINE where uBcono=111 and uborno=? and ubdlix=? ";
                                              pst2=conndb2.prepareStatement(Sql2);
                                              pst2.setString(1,rs1.getString("co_numb"));
                                              pst2.setString(2,rs1.getString("m3_del_numb"));
                                              rs2=pst2.executeQuery();
                                              while (rs2.next())
                                              {  if (rs1.getDouble("pqty")!=rs2.getDouble("dqty"))
                                                 {
                                                    p_error="Delivery Qnty Mismatch.. Del No "+rs1.getString("m3_del_numb")+"- DelQty "+rs2.getDouble("dqty")+" InvQty- "+rs1.getDouble("pqty");
                                                    return p_error;
                                                 }
                                              }
                                              if(rs2!=null){rs2.close();}
                                              if(pst2!=null){pst2.close();}
                                              
                                              Sql2=" select uaorst from m3fdbprd.odhead where uacono   ='111' and  uaorno=? and uadlix=?"; 
                                              pst2=conndb2.prepareStatement(Sql2);
                                              pst2.setString(1,rs1.getString("co_numb"));
                                              pst2.setString(2,rs1.getString("m3_del_numb"));
                                              rs2=pst2.executeQuery();
                                              while (rs2.next())
                                              {
                                                 v_status=rs2.getInt("uaorst");
                                              }
                                              if(rs2!=null){rs2.close();}
                                              if(pst2!=null){pst2.close();}
                                              if (v_status==0)
                                              {
                                                 p_error="Create Delivery Line at Movex....";
                                                 return p_error;
                                              }
                                              if (v_status!=60)
                                              {
                                                 p_error="Check Movex Status ..."+v_status;
                                                 return p_error;
                                              }
                                            
                                        }  /// While rs1 closed
                                             if(rs1!=null){rs1.close();}
                                              if(pst1!=null){pst1.close();}
                                              
                                         int dfound=0;  Double d_qty=0.0;
                                         Sql1=" select co_numb,co_line,m3_del_numb,set_pcs,sum(round(pack_qty,0))/NVL(set_pcs,1) pqty from seplweb.pr_pack_box_ir_detail where pack_numb=? group by  co_numb,co_line,m3_del_numb,set_pcs ";
                                         pst1=conn.prepareStatement(Sql1);
                                         pst1.setString(1,P_PLAN);
                                        rs1=pst1.executeQuery();
                                        while(rs1.next()) 
                                        { 
                                        String Sql2=" select round(sum(ubdlqt+ubivqt)) del_qty from m3fdbprd.odline where ubcono = 111 and uborno =? and ubponr=? and ubdlix=?  "; 
                                                pst2=conndb2.prepareStatement(Sql2);
                                                pst2.setString(1,rs1.getString("co_numb"));
                                                pst2.setString(2,rs1.getString("co_line"));
                                                pst2.setString(3,rs1.getString("m3_del_numb"));
                                                rs2=pst2.executeQuery();
                                                while (rs2.next())
                                                {
                                                    dfound=1; 
                                                    d_qty=d_qty+rs2.getDouble("del_qty")/(rs1.getDouble("set_pcs"));
                                                    
                                                }
                                          }  
                                        if(rs1!=null){rs1.close();}
                                        if(pst1!=null){pst1.close() ;}    
                                        
                                          if (dfound==0){p_error="Delivery Entry Not Found ....";}
                                          double diffqty=P_INVQTY-d_qty;
                                          
                                          if (diffqty>2 || diffqty<-2 ){p_error=p_error+"Invoice Total Qnty Misatch...InvQty "+P_INVQTY+" Delv Total Qty "+d_qty;}
                                           
                                          
                                        int pack_no=0;
                                         Sql1=" select co_numb,a.co_line,m3_del_numb,rpad(a.item_numb,15,' ') item_numb,set_pcs,round(pack_qty,0) pqty,round((nvl(price_fc,0)+nvl(price_misc,0))-nvl(adjust_fc,0),6) invrate,nvl(net_price,0) net_price "
                                                 + "from seplweb.pr_pack_box_ir_detail A,ei_endors_mast b,ei_endors_dtls c where pack_numb=?  and a.pack_numb=b.plan_no and b.year=c.year and b.company=c.company and b.inv_no=c.inv_no and  "
                                                 +" a.co_numb=c.co_no and a.co_line=c.co_line and a.item_numb=c.item_no ";
                                         pst1=conn.prepareStatement(Sql1);
                                         pst1.setString(1,P_PLAN);
                                        rs1=pst1.executeQuery();
                                        while(rs1.next())
                                        {    pack_no=1; 
                                             int check_no=0;
                                             String Sql2=" select  round(ubsapr,5) ubsapr,round(ubdlqt,0) ubdlqt,round(ubnepr,5) ubnepr,obsapr,obnepr obnepr from m3fdbprd.odline a,m3fdbprd.ooline b "
                                                       + " where ubcono=obcono and uborno=oborno and ubponr=obponr and ubitno=obitno and ubcono=111 and uborno=? and ubponr=? and ubdlix=? and ubitno=? "; 
                                                pst2=conndb2.prepareStatement(Sql2);
                                                pst2.setString(1,rs1.getString("co_numb"));
                                                pst2.setString(2,rs1.getString("co_line"));
                                                pst2.setString(3,rs1.getString("m3_del_numb"));
                                                pst2.setString(4,rs1.getString("item_numb"));
                                                rs2=pst2.executeQuery();
                                                while (rs2.next())
                                                {
                                                  check_no=1;
                                                  
                                               //  if (rs1.getDouble("invrate")!=rs2.getDouble("ubsapr"));
                                                 int retval = Double.compare(rs1.getDouble("invrate"), rs2.getDouble("obsapr"));
                                                 if(retval!= 0)
                                                  {p_error="Check Invoice Rate "+rs1.getDouble("invrate")+" Movex CO Line Sale Price...."+rs2.getDouble("obsapr");} 
                                                   
                                               //   if (rs1.getDouble("pqty")!=rs2.getDouble("ubdlqt"));
                                                  int retval1 = Double.compare(rs1.getDouble("pqty"), rs2.getDouble("ubdlqt"));
                                                if(retval1!= 0)
                                                  {p_error="Check Invoice Qty "+rs1.getDouble("pqty")+"  Movex CO Line Qty...."+rs2.getDouble("ubdlqt");} 
                                                  
                                                     
                                                 Double v_diff=roundTwoDecimals((rs1.getDouble("net_price"))-roundTwoDecimals(rs2.getDouble("obnepr")));
                                                  
                                                 if (roundTwoDecimals(v_diff)>0.01 || roundTwoDecimals(v_diff)<-0.01)
                                                 { 
                                                    
                                                   int retval2 = Double.compare(rs1.getDouble("net_price"), rs2.getDouble("obnepr"));  
                                                   if (retval2!=0)
                                                   {
                                                     p_error="Check Invoice GR Discount "+rs1.getDouble("net_price")+" & Movex CO Line Net Price...."+rs2.getDouble("obnepr");
                                                   }
                                                 }
                                                 
                                                }
                                               if(rs2!=null){rs2.close();}
                                              if(pst2!=null){pst2.close();}
                                                 if (check_no==0)
                                                   {
                                                     p_error="Create Delivery Details...at Movex For CO No."+rs1.getString("co_numb")+" Co Line "+rs1.getString("co_line");
                                                   }
                                        
                                        }
                                           if(rs1!=null){rs1.close();}
                                           if(pst1!=null){pst1.close();}
                                        if (pack_no==0)
                                         {
                                             p_error="Invoice Not Linked with Shipment Planning";
                                         }      
                                              
                          }
                           
                          catch(Exception e)
                          {
                              System.out.println(e.toString());
                          }
                          finally
                          { 
                              try{
                              if(rs2!=null)rs2.close(); if(rs1!=null){rs1.close();}  
                              if(pst2!=null)pst2.close();  if(pst1!=null){pst1.close();}   
                              
                             
                              }catch(Exception ee){}
                          }
                                    
                 return p_error;
    
    }
    
        
     public String ajaxMeisAPP() throws SQLException {
        String ls = "";
         
            
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null,st1=null,st2=null;
              ResultSet rs=null, rs1=null, rs2=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                  
                    conn = new connection().getConnection();
                    String errmsg="";
                    st = conn.prepareStatement(" select SHP_BILL_NO,TO_char(shp_bill_date,'dd-Mon-yyyy') sb_date,to_char(let_exp_date,'yyyymmdd') let_exp ,sum(fob_amt-nvl(gr_disc,0)) fob_val,sum(fob_amt-nvl(gr_disc,0))*dbk_conv inr_amt  from ei_shipment_dtls  where shp_bill_no like ? group by SHP_BILL_NO,TO_char(shp_bill_date,'dd-Mon-yyyy'),to_char(let_exp_date,'yyyymmdd'),dbk_conv  order by 1   ");
                    st.setString(1,INVOICE_NO_S.toUpperCase() + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                         int rec=0;  String mport="";
                         st1=conn.prepareStatement("select distinct loading from ei_endors_mast a,ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and trim(b.shp_bill_no)=? and shp_Bill_date=?");
                         st1.setString(1,rs.getString("shp_bill_no").trim());
                         st1.setString(2,rs.getString("sb_date"));
                         rs1=st1.executeQuery();
                         while (rs1.next())
                         {
                            rec=rec+1;
                            mport=rs1.getString("loading");
                         }
                            if(rs1!=null){rs1.close();}
                            if(st1!=null){st1.close();}
                         if (rec>1){errmsg="More Then One Port Found...All invoices should be same Port !!!!";}
                         if (rs.getInt("let_exp")<20160504){
                             
                               st1=conn.prepareStatement("select * from ei_sbill_master where shp_bill_no=? and shp_bill_date=? and flight_date is null ");
                               st1.setString(1,rs.getString("shp_bill_no").trim());
                               st1.setString(2,rs.getString("sb_date"));
                               rs1=st1.executeQuery();
                               if (rs1.next())
                               {  
                                   
                                  st2=conn.prepareStatement("select * from ei_sbill_master where shp_bill_no=? and shp_bill_date=? and ep_from_ho is null ");
                                  st2.setString(1,rs.getString("shp_bill_no").trim());
                                  st2.setString(2,rs.getString("sb_date"));
                                  rs2=st2.executeQuery();
                                  if (rs2.next())
                                  { 
                                      errmsg="EP Copy Not Received.... !!!!";
                                  }
                                        if(rs2!=null){rs2.close();}
                                        if(st2!=null){st2.close() ;}    
                               }
                               if(rs1!=null){rs1.close();}
                               if(st1!=null){st1.close() ;}   
                                  st2=conn.prepareStatement("select * from ei_sbill_master where shp_bill_no=? and shp_bill_date=? and lcert_recv is null ");
                                  st2.setString(1,rs.getString("shp_bill_no").trim());
                                  st2.setString(2,rs.getString("sb_date"));
                                  rs2=st2.executeQuery();
                                  if (rs2.next())
                                  { 
                                      errmsg="Landing Certificate Not Received.... !!!!";
                                  }
                                 if(rs2!=null){rs2.close();}
                                 if(st2!=null){st2.close() ;}    
                                  
                              } /// letexp date check if close
                         
                                  st2=conn.prepareStatement("select * from ei_sbill_master where shp_bill_no=? and shp_bill_date=? and brc_date is null ");
                                  st2.setString(1,rs.getString("shp_bill_no").trim());
                                  st2.setString(2,rs.getString("sb_date"));
                                  rs2=st2.executeQuery();
                                  if (rs2.next())
                                  { 
                                      errmsg="BRC Not Received.... !!!!";
                                  }
                                 if(rs2!=null){rs2.close();}
                                 if(st2!=null){st2.close() ;}    
                                 
                                  st2=conn.prepareStatement("select * from ei_mlfs_dtls where shp_bill_no=? and shp_bill_date=?  ");
                                  st2.setString(1,rs.getString("shp_bill_no").trim());
                                  st2.setString(2,rs.getString("sb_date"));
                                  rs2=st2.executeQuery();
                                  if (rs2.next())
                                  { 
                                      errmsg="S/B Already Exist.... !!!!";
                                  }
                                   if(rs2!=null){rs2.close();}
                                   if(st2!=null){st2.close() ;}    
                                  
                                  if (errmsg!=null && errmsg.length()>5)
                                 {errmsg=errmsg+" Against S/B No:-"+rs.getString("SHP_BILL_NO");}
                                 else{errmsg="NOERROR";}
                               
                             ls+=errmsg+"#"+rs.getString("SHP_BILL_NO")+"#"+rs.getString("sb_date")+"#"+rs.getString("fob_val")+"#"+rs.getString("inr_amt")+"#"+mport;
                          
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }
       public String ajaxMeisBRC() throws SQLException {
        String ls = "";
         
            
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null,st1=null,st2=null;
              ResultSet rs=null, rs1=null, rs2=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                  
                    conn = new connection().getConnection();
                    String errmsg="";
                    st = conn.prepareStatement(" select SHP_BILL_NO,TO_char(shp_bill_date,'dd-Mon-yyyy') sb_date,to_char(let_exp_date,'dd-Mon-yyyy') let_exp ,to_char(FLIGHT_DATE,'dd-Mon-yyyy') FLIGHT_DATE,to_char(EP_FWD_DATE,'dd-Mon-yyyy') EP_FWD_DATE,"
                            + "                  to_char(EP_FROM_HO,'dd-Mon-yyyy') EP_FROM_HO,to_char(LCERT_APPL,'dd-Mon-yyyy') LCERT_APPL,to_char(LCERT_RECV,'dd-Mon-yyyy') LCERT_RECV,nvl(to_char(BRC_APPL,'dd-Mon-yyyy'),'') BRC_APPL,to_char(BRC_date,'dd-Mon-yyyy') BRC_RECV from ei_sbill_master  where shp_bill_no like ?  order by 1   ");
                    st.setString(1,INVOICE_NO_S.toUpperCase() + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                      
                                    
                             ls+=rs.getString("SHP_BILL_NO")+"#"+rs.getString("sb_date")+"#"+rs.getString("let_exp")+"#"+rs.getString("FLIGHT_DATE")+"#"+rs.getString("EP_FWD_DATE")+"#"+rs.getString("EP_FROM_HO")+"#"+rs.getString("LCERT_APPL")+"#"+rs.getString("LCERT_RECV")+"#"+rs.getString("BRC_APPL")+"#"+rs.getString("BRC_RECV");
                          System.out.println("ls "+ls);
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    } 
     
       
       public String ajaxSBNOCHQ() throws SQLException {
        String ls = "";
       
         
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                    //System.out.println(str);
                    conn = new connection().getConnection();
                    st = conn.prepareStatement("select a.shp_bill_no,to_char(a.shp_bill_date,'dd/mm/yyyy') sb_date,a.claim_port,"
                            + "nvl(a.dbk_admisable,0) dbk_admisable,nvl(a.dbk_received,0) dbk_received,nvl(a.dbk_adjusted,0) dbk_adjusted,"
                            + "nvl(a.DBK_SUPL_RECV,0) DBK_SUPL_RECV,nvl(a.woff_amt,0) dbk_woff,nvl(a.str_recv,0) str_recv,nvl(a.str_due,0) str_due,"
                            + "nvl(a.str_woff,0) str_woff,nvl(a.rosl_due,0) rosl_due,b.PAY_TYPE,nvl(b.AMOUNT,0) AMOUNT,nvl(b.BANK_CR,0) BANK_CR,"
                            + "nvl(b.STR_AMT,0) STR_AMT,nvl(b.W_OFF,0) W_OFF from ei_dbk_mast a,finacsys.ei_dbk_chq_dtls_test b "
                            + "where a.shp_bill_no=b.shp_bill_no and a.shp_bill_date=b.shp_bill_date and a.shp_bill_no like ? order by 1");
                    st.setString(1, INVOICE_NO_S);
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls+=rs.getString("shp_bill_no")+"#"+rs.getString("sb_date")+"#"+rs.getString("claim_port")+"#"+rs.getString("dbk_admisable")
                            +"#"+rs.getString("dbk_received")+"#"+rs.getString("dbk_adjusted")+"#"+rs.getString("DBK_SUPL_RECV")+"#"
                            +rs.getString("dbk_woff")+"#"+rs.getString("str_due")+"#"+rs.getString("str_recv")+"#"+rs.getString("str_woff")+"#"+rs.getString("rosl_due")+"#"
                            +rs.getString("PAY_TYPE")+"#"+rs.getString("AMOUNT")+"#"+rs.getString("BANK_CR")+"#"+rs.getString("STR_AMT")+"#"
                            +rs.getString("STR_AMT")+"#"+rs.getString("W_OFF");
                           
                    }  
                     
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }

      public String ajaxDbkRefundSB() throws SQLException {
        String ls = "";
          
             
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conn = null;
              PreparedStatement st=null,st1=null,st2=null;
              ResultSet rs=null, rs1=null, rs2=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                       
                  
                    conn = new connection().getConnection();
                    String errmsg="";
                    st = conn.prepareStatement(" select SHP_BILL_NO,TO_char(shp_bill_date,'dd-Mon-yyyy') sb_date,CLAIM_PORT,nvl(DBK_ADMISABLE,0) dbk_admisable,nvl(DBK_RECEIVED,0) dbk_received,nvl(STR_DUE,0) str_due,nvl(STR_RECV,0) str_recv  from ei_DBK_MAST  where shp_bill_no like ?    ");
                    st.setString(1,INVOICE_NO_S.toUpperCase() + "%");
                    rs = st.executeQuery();
                    while (rs.next()) {
                        System.out.println("coming....");
                         int rec=0;  String mport="";
                         st1=conn.prepareStatement("select distinct loading from ei_endors_mast a,ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and trim(b.shp_bill_no)=? and shp_Bill_date=?");
                         st1.setString(1,rs.getString("shp_bill_no").trim());
                         st1.setString(2,rs.getString("sb_date"));
                         rs1=st1.executeQuery();
                         while (rs1.next()) 
                         {
                            rec=rec+1;
                            mport=rs1.getString("loading").trim();
                         }
                            if(rs1!=null){rs1.close();}
                            if(st1!=null){st1.close();}
                           if (rec>1){errmsg="More Then One Port Found...All invoices should be same Port !!!!";}
                                      
                                 
                                  st2=conn.prepareStatement("select * from ei_dbk_refund_dtls where sb_no=? and sb_date=?  ");
                                  st2.setString(1,rs.getString("shp_bill_no").trim());
                                  st2.setString(2,rs.getString("sb_date"));
                                  rs2=st2.executeQuery();
                                  if (rs2.next())
                                  { 
                                      errmsg="S/B Already Exist.... !!!!";
                                  }
                                   if(rs2!=null){rs2.close();}
                                   if(st2!=null){st2.close() ;}    
                                  
                                  if (errmsg!=null && errmsg.length()>5)
                                 {
                                     errmsg=errmsg+" Against S/B No:-"+rs.getString("shp_bill_no");
                                 }
                                 else{errmsg="NOERROR";}
                               
                             ls+=errmsg+"#"+rs.getString("SHP_BILL_NO")+"#"+rs.getString("sb_date")+"#"+rs.getString("CLAIM_PORT")+"#"+rs.getString("dbk_admisable")+"#"+rs.getString("dbk_received")+"#"+rs.getString("str_due")+"#"+rs.getString("str_recv");
                           
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }    
       
        ///////////////// Check Byer
          
          public String ajaxBuyer() throws SQLException {
   
        String ls = "";
         
              
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conndb2 = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                 
                    conndb2 = new connectiondb2().getConnection();
                    st = conndb2.prepareStatement("select OKCUNO,OKCUNM from m3fdbprd.ocusma where OKCUNO like ?");
                    st.setString(1,INVOICE_NO_S.toUpperCase()+ "%");
                
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls+=rs.getString("OKCUNO")+"#"+rs.getString("OKCUNM");
                        
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conndb2 != null) {
                    conndb2.close();
                }
 
            }
        }
        return "success";
    }
          
          
          ///////////////// Check currency
           public String ajaxCrncy() throws SQLException {
        String ls = "";
         
              
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conndb2 = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    
                 
                    conndb2 = new connectiondb2().getConnection();
                    st = conndb2.prepareStatement("select distinct CTSTKY,CTTX40 from m3fdbprd.csytab where CTSTCO='CUCD' and CTSTKY like ?");
                    st.setString(1,INVOICE_NO_S.toUpperCase()+ "%");
                
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls+=rs.getString("CTSTKY")+"#"+rs.getString("CTTX40");
                        
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conndb2 != null) {
                    conndb2.close();
                }
 
            }
        }
        return "success";
    }
           
           ///////////////// Check Payment Term
  public String ajaxPaytrm() throws SQLException {
        String ls = "";
         
        if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
              Connection conndb2 = null;
              Connection conn = null;
              PreparedStatement st=null;
              ResultSet rs=null;
            try {
                
                if (INVOICE_NO_S != null && INVOICE_NO_S.length() > 0) {
                    conn = new connection().getConnection();
                    conndb2 = new connectiondb2().getConnection();
                    st = conn.prepareStatement("select type_code,type_desc from ei_grup_type_dtls where grup_type_code='IMP' and TYPE_CODE like ? order by type_desc");
                    st.setString(1,INVOICE_NO_S.toUpperCase()+ "%");
                
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls+=rs.getString("type_code")+"#"+rs.getString("type_desc");
                        
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conndb2 != null) {
                    conndb2.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        }
        return "success";
    }
       
  
            ///////////////// Check GSTCode
        public String ajaxGSTCODE() throws SQLException {
        String ls = "";
         
              Connection conn = null;
              PreparedStatement st=null,stat2=null;
              ResultSet rs=null,result2=null;
            try {
                 
                     
                
                if (FACT_S != null && FACT_S.length() > 0) {
                    
                    conn = new connection().getConnection();
                    st = conn.prepareStatement("select OAGEOC,oaadK1 FROM CIADDR_m4off WHERE OACONO=111 AND OAADTH=4 and oaadK1=?");
                    st.setString(1,FACT_S);
                
                    rs = st.executeQuery();
                    while (rs.next()) {
                       
                          ls=rs.getString("OAGEOC")+"#"+rs.getString("oaadK1");
                          System.out.println(ls+"good"+FACT_S);  
                    } 
                    
                    if ((ls != null) && (!ls.equals(""))) {
                          this.inputStream = new ByteArrayInputStream(ls.getBytes("UTF-8"));
                    } else {
                         this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
                     }
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
 
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
 
            }
        
        return "success";
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

    public List getCHRGLIST() {
        return CHRGLIST;
    }

    public void setCHRGLIST(List CHRGLIST) {
        this.CHRGLIST = CHRGLIST;
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

    public String getINVOICE_NO_S() {
        return INVOICE_NO_S;
    }

    public void setINVOICE_NO_S(String INVOICE_NO_S) {
        this.INVOICE_NO_S = INVOICE_NO_S;
    }

    public String getAWBDATE_AJ() {
        return AWBDATE_AJ;
    }

    public void setAWBDATE_AJ(String AWBDATE_AJ) {
        this.AWBDATE_AJ = AWBDATE_AJ;
    }

    

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getLIC_NO() {
        return LIC_NO;
    }

    public void setLIC_NO(String LIC_NO) {
        this.LIC_NO = LIC_NO;
    }

    public String getSBDATE_AJ() {
        return SBDATE_AJ;
    }

    public void setSBDATE_AJ(String SBDATE_AJ) {
        this.SBDATE_AJ = SBDATE_AJ;
    }

    public String getSBNO_AJ() {
        return SBNO_AJ;
    }

    public void setSBNO_AJ(String SBNO_AJ) {
        this.SBNO_AJ = SBNO_AJ;
    }

    public String getLETEXP_AJ() {
        return LETEXP_AJ;
    }

    public void setLETEXP_AJ(String LETEXP_AJ) {
        this.LETEXP_AJ = LETEXP_AJ;
    }

    public String getP_YEAR() {
        return P_YEAR;
    }

    public void setP_YEAR(String P_YEAR) {
        this.P_YEAR = P_YEAR;
    }

    public String getP_LINK_NO() {
        return P_LINK_NO;
    }

    public void setP_LINK_NO(String P_LINK_NO) {
        this.P_LINK_NO = P_LINK_NO;
    }

    public String getFACT_S() {
        return FACT_S;
    }

    public void setFACT_S(String FACT_S) {
        this.FACT_S = FACT_S;
    }
     
      
}
  