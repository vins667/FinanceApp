package shahi.Action.MvxExp.POST;

  
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import java.sql.*;
import java.util.*;
import java.util.Date; 
import shahi.Action.MvxExp.Admin.EisUtil;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import shahi.Action.HelperConstantsFnl;

import shahi.Action.MvxExp.POST.Beans.LicSearchBean;
import shahi.Action.MvxExp.POST.Beans.PostChargeBean;
import shahi.Action.MvxExp.PRE.Beans.BuyersearchBean;
import shahi.Action.MvxExp.POST.Beans.PostFinInvBean;
import shahi.Action.MvxExp.POST.Beans.PostFinSBBean;
import shahi.Action.database.connectiondb2;

  

public class PostDocsFinAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private String saveFlag;
    private String updFlag;
    private List showList;
     private String indexname;
    private List buyeraddlist= new ArrayList();
    private List LicSearch= new ArrayList();
    private String SEARCH_CODE;
    private String aausrid;
    private String MAXDATE;
    private List ShowDetail=new ArrayList();
    private List INVLIST=new ArrayList();
    private List SBLIST=new ArrayList();  
    
    private String P_YEAR;
    private String P_LINK_NO;
    private List SAVETEXT;
    private List CHRGLIST=new ArrayList();
    private Double TDBK_DUE;
    private String FBANK;
    private String FBANK_ADDNO;
    private String DBANK;
    private String DBANK_ADDNO;
    private String PAYTERM;
    private List YEAR;
    private List LINK_NO;
      
  
      private List AWB_NO;
      private List AWB_DATE;
      private List HAWB_NO;
      private List APRV_DATE;
      private String ERRMSG;
    @Override
    public String execute() throws SQLException{
         
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
                 ResultSet result1=null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
            
             SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yyyy");
           
            
             try{
                    String q1 = " ";
                    
                       stat1= conn.prepareStatement("select location_code,to_char(sysdate,'yyyy-mm-dd') maxdate  from seh_web_users where user_id=?") ;
                    stat1.setString(1,usrid);
                    result1=stat1.executeQuery();
                    while (result1.next())
                    {LOCATION_CODE=result1.getString("location_code");
                     MAXDATE=result2.getString("maxdate");
                    }  

             
                    
                       if (flag == 1) {   
                              conn.commit(); 
                              
                                addActionMessage("Records Save(s) !!");
                                return SUCCESS;
                           }
                                else {return ERROR;
                        } 
                     
                  } 
          catch(Exception e){
              System.out.println(e.toString());
          }
        finally{
         try {   
             conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(FITTEntryAction.class.getName()).log(Level.SEVERE, null, ex);
         }
             }
        
        return SUCCESS;
 }   
           
           public String edit() throws SQLException{
                 Connection conn = null;
                 Connection conndb2=null;
                  
                 PreparedStatement stat3=null;
                 ResultSet result3=null;
                 
                PreparedStatement st=null;
                PreparedStatement st1=null;
                PreparedStatement st2=null;
                PreparedStatement st3=null;
                ResultSet rs=null;
                ResultSet rs1=null;
                ResultSet rs2=null;
                ResultSet rs3=null;
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
          
           
          try{      conn = new connection().getConnection();
                    conn.setAutoCommit(false);
              
                    stat3= conn.prepareStatement("select location_code from seh_web_users where trim(user_id)=?") ;
                    stat3.setString(1,usrid.trim());
                    result3=stat3.executeQuery();
                    while (result3.next())
                    { 
                        LOCATION_CODE=result3.getString("location_code");
                    }
                     if (result3 != null) {result3.close();}
                     if (stat3 != null) {stat3.close();}
                    String P_APRVDT="";   String DBKLINK=""; String DBKYEAR="";
                    
                   if (viewFlag!=null && viewFlag.length()>0)
                    {
                       if (LINK_NO != null && LINK_NO.size() > 0 )
                        {  
                            for (int i = 0; i < LINK_NO.size(); i++)
                            {      P_YEAR=YEAR.get(i).toString().trim();
                                   P_LINK_NO=LINK_NO.get(i).toString().trim();
                                      if (P_LINK_NO!=null && P_LINK_NO.length()>0)
                                      {        DBKYEAR=P_YEAR;
                                         
                                               DBKLINK=DBKLINK+P_LINK_NO+",";
                                         
                                                        st = conn.prepareStatement("select distinct ex_track_no,co_numb,del_numb from ei_ship_Data a,ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.year=? and b.link_no=?   ");
                                                        st.setString(1,P_YEAR);
                                                        st.setString(2,P_LINK_NO);
                                                        rs = st.executeQuery();
                                                        while (rs.next()) {
                           
                                                        double dbkamt=0.0; double stramt=0.0; double roslamt=0.0; double taxamt=0.0;
                                                        st1=conndb2.prepareStatement(" SELECT  OEORNO ,oedlix,oecrid,oeacrf,OECRD0,OECRAM,oecrfa from m3fdbprd.oochrg where oecono='111' and oeorno=? and oedlix=? ");
                                                        st1.setString(1,rs.getString("co_numb"));
                                                        st1.setString(2,rs.getString("del_numb"));
                                                         
                                                        rs1=st1.executeQuery();
                                                        while (rs1.next())
                                                        { 
                                                         
                                                           if (rs1.getString("oecrid").trim().equals("DBK")){ roslamt=0.0;stramt=0.0;taxamt=0.0;dbkamt=rs1.getDouble("OECRAM");}
                                                           if (rs1.getString("oecrid").trim().equals("SVTAX")){ dbkamt=0.0;roslamt=0.0;taxamt=0.0;stramt=rs1.getDouble("OECRAM");}
                                                           if (rs1.getString("oecrid").trim().equals("ROSL")){ dbkamt=0.0;stramt=0.0;taxamt=0.0;roslamt=rs1.getDouble("OECRAM");}
                                                           if (!rs1.getString("oecrid").trim().equals("ROSL") && !rs1.getString("oecrid").trim().equals("SVTAX") && !rs1.getString("oecrid").trim().equals("DBK")){ dbkamt=0.0;stramt=0.0;roslamt=0.0;taxamt=rs1.getDouble("OECRAM");}

                                                          CHRGLIST.add(new PostChargeBean(rs.getString("ex_track_no"),rs1.getString("OEORNO"),rs1.getString("oedlix"),rs1.getString("oeacrf"),rs1.getString("oecrid"),rs1.getString("OECRD0"),dbkamt,stramt,roslamt,taxamt,rs1.getString("oecrfa")));
                                                    
                                                        } 
                             
                                                     } 
                                                        if (rs != null) {rs.close();}
                                                       if (st != null) {st.close();} 
                                                       String duedesc="";  String buyergl="";
                                        
                                             st = conn.prepareStatement("select excs_inv_no,a.currency,c.desti_cntry,buyer,buyer_addr,cons_addr,lc_no,xstepy,xsnody,t_o_date,edi_date,plan_no,fob_amt,tax_percent,sum(gr_decl_amt) gr_decl,sum(nvl(discount_amt,0)) inv_disc "+
                                                                        " from ei_shipment_dtls a,ei_endors_dtls b,ei_endors_mast c where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.year=c.year and b.company=c.company and b.inv_no=c.inv_no and  a.year=? and a.link_no=? "+
                                                                        " group by excs_inv_no,a.currency,c.desti_cntry,buyer,buyer_addr,cons_addr,lc_no,xstepy,xsnody,t_o_date,edi_date,plan_no,fob_amt,tax_percent  ");
                                            st.setString(1,P_YEAR);
                                            st.setString(2,P_LINK_NO);
                                            rs = st.executeQuery(); 
                                            while (rs.next()) {
                                                 if (rs.getString("XSTEPY").trim().equals("01") || rs.getString("XSTEPY").trim().equals("07") || rs.getString("XSTEPY").trim().equals("10"))
                                                 {duedesc="Awb Date";
                                                 }
                                                 if (rs.getString("XSTEPY").trim().equals("02"))
                                                  {duedesc="SB Date";
                                                  }
                                                 if (rs.getString("XSTEPY").trim().equals("04"))
                                                  {duedesc="77 Date";
                                                  }
                                                 if (rs.getString("XSTEPY").trim().equals("05"))
                                                  {duedesc="Aprv Date";
                                                   P_APRVDT=APRV_DATE.get(i).toString().trim();
                                                   if (P_APRVDT==null ){
                                                         ERRMSG="Enter Approval Date " ;
                                                         addActionError("Enter Approval Date "+rs.getString("excs_inv_no"));
                                           
                                                         return "error";
                                                   }
                                                  }if (rs.getString("XSTEPY").trim().equals("06"))
                                                  {duedesc="TO Date";
                                                  }
                                                  st1=conndb2.prepareStatement(" SELECT  opcunm,optel2 from m3fdbprd.ocusad where opcono='111' and trim(opcuno)=? and trim(opadid)=? ");
                                                  st1.setString(1,rs.getString("buyer").trim());
                                                  st1.setString(2,rs.getString("buyer_addr").trim());
                                                  rs1=st1.executeQuery();
                                                  if (rs1.next())
                                                  {  buyergl=rs1.getString("optel2");

                                                  }else{
                                                     ERRMSG="Buyer GL Code Not Mapped for Invoice No "+rs.getString("excs_inv_no");

                                                      addActionError("Buyer GL Code Not Mapped for Invoice No "+rs.getString("excs_inv_no"));
                                                     return "error";
                                                  }
                                                    if (rs1 != null) {rs1.close();}
                                                    if (st1 != null) {st1.close();}


                                                    INVLIST.add(new PostFinInvBean(rs.getString("excs_inv_no"),rs.getString("buyer"),rs.getString("buyer_addr"),rs.getString("cons_addr"),buyergl,rs.getString("currency"),rs.getString("desti_cntry"),rs.getString("lc_no"),rs.getString("lc_no"),rs.getString("XSTEPY"),rs.getString("XSNODY"),rs.getDouble("fob_amt"),rs.getDouble("gr_decl"),rs.getDouble("inv_disc"),rs.getDouble("tax_percent"),duedesc,P_YEAR,P_LINK_NO));
                          

                                            }
                                            if (rs != null) {rs.close();}
                                            if (st != null) {st.close();}
                                           int sbflag=0;  String SB="";
                                           
                                       /*   st = conn.prepareStatement("select  shp_bill_no,to_char(shp_bill_Date,'dd/mm/yyyy') shp_bill_date,dbk_admisable,nvl(str_due,0)-nvl(str_woff,0) str_due,rosl_due from ei_dbk_mast where (shp_bill_no,shp_bill_date) in (select distinct shp_bill_no,shp_bill_Date from ei_shipment_dtls  where year=? and link_no=? )  ");
                                            st.setString(1,P_YEAR);
                                            st.setString(2,P_LINK_NO);
                                            rs = st.executeQuery();
                                            while (rs.next()) {  
                                                 if (SB==rs.getString("shp_bill_no").trim())
                                                 {
                                                     System.out.println("sanjeev");
                                                    sbflag=1;
                                                 } 
                                                   if (SBCHECK != null && SBCHECK.size() > 0 )
                                                      {  
                                                        for (int S = 0; S < SBCHECK.size(); S++)
                                                         { 
                                                             if (rs.getString("shp_bill_no").trim().equals(SBCHECK.get(i).toString().trim()))
                                                             {
                                                               
                                                               sbflag=1;
                                                             }
                                                         } 
                                                      }
                                                      if (sbflag==0){
                                                        SBLIST.add(new PostFinSBBean(rs.getString("shp_bill_no"),rs.getString("shp_bill_date"),rs.getDouble("dbk_admisable"),rs.getDouble("str_due"),rs.getDouble("rosl_due")));
                                                      }
                                            }
                                       
                                            if (rs != null) {rs.close();}
                                            if (st != null) {st.close();}
                                         */
                                                ///////////// LC Status Check

                                                         st=conn.prepareStatement("select all_no from ei_shipment_dtls where year=? and link_no=? ");
                                                         st.setString(1,P_YEAR);
                                                         st.setString(2,P_LINK_NO);
                                                         rs=st.executeQuery();
                                                         while (rs.next())
                                                         {   
                                                            String v_payterm=""; 
                                                             st3=conn.prepareStatement("select distinct co_no from ei_endors_dtls where all_no=?");
                                                             st3.setString(1,rs.getString("all_no"));
                                                             rs3=st3.executeQuery();
                                                             while (rs3.next())
                                                          {
                                                             st1=conndb2.prepareStatement("select  oapycd,oatepy  from m3fdbprd.oohead  where  oacono = 111 and oadivi = '100' and  oaorno=? ");
                                                             st1.setString(1,rs3.getString("co_no").trim());
                                                             rs1=st1.executeQuery();
                                                             while (rs1.next())
                                                             { 

                                                                if (v_payterm!=null && v_payterm.length()>1 && rs1.getString("oatepy")!=null)
                                                                {
                                                                   if (!v_payterm.equals(rs1.getString("oatepy").trim()))
                                                                   {
                                                                      ERRMSG="All CO Must be same Payment Term Check [OIS300]  "+rs3.getString("co_no");

                                                                      addActionError("All CO Must be same Paymter Term Check [OIS300]  "+rs3.getString("co_no"));
                                                                       return "error";  
                                                                   }
                                                                }
                                                                else 
                                                                {
                                                                   v_payterm=rs1.getString("oatepy").trim(); 
                                                                }
                                                                if (rs1.getString("oatepy").trim().equals("ILC") || rs1.getString("oatepy").trim().equals("FLC") )
                                                                {
                                                                         st2=conndb2.prepareStatement("select lestat from m3fdbprd.clomas a,m3fdbprd.cloord where lecono = orcono and ledivi = ordivi and leltpe  = orltpe and lelecr = orlecr and leltpe = 2 and orcono = 111 and ordivi = '100' and orridn =?");
                                                                         st2.setString(1,rs.getString("co_no").trim());
                                                                         rs2=st2.executeQuery();
                                                                         while (rs2.next())
                                                                         {
                                                                             if (rs2.getInt("lestat")<40)
                                                                             {
                                                                               ERRMSG="Check LC Status  "+rs.getString("lestat")+"  , this should be 40";
                                                                              addActionError(" Check Lc Status  "+rs.getString("lestat")+"  , this should be 40");
                                                                               return "error";  
                                                                             }
                                                                         }
                                                                           if (rs2 != null) {rs.close();}
                                                                           if (st != null) {st.close();}
                                                                    }
                                                                }
                                                                           if (rs1 != null) {rs1.close();}
                                                                           if (st1 != null) {st1.close();}
                                                                }

                                                                           if (rs3 != null) {rs3.close();}
                                                                           if (st3 != null) {st3.close();}
                                                         }

                                                                           if (rs != null) {rs.close();}
                                                                           if (st != null) {st.close();}
                                                            /// LC Status Check point End... 
                                                                String p_error="";

                                                                st =conn.prepareStatement(" select  exports.validate_movex_data77(?,?) aa from dual ");
                                                                st.setString(1,P_YEAR);
                                                                st.setString(2,P_LINK_NO);
                                                                
                                                                 rs=st.executeQuery();
                                                               if (rs.next())
                                                                {    
                                                                        if (rs.getString("aa")!=null)
                                                                        {   ERRMSG="Error: "+rs.getString("aa");
                                                                            addActionError(" Error: "+rs.getString("aa"));
                                                                             return   "error"; 
                                                                        } 
                                                                 }
                                           }
                            }
                            //--------------- New SB------------
                                      if (DBKLINK!=null)
                                      {
                                           DBKLINK=DBKLINK.substring(0, DBKLINK.length()-1);
                                       }
                           
                                            st = conn.prepareStatement("select  shp_bill_no,to_char(shp_bill_Date,'dd/mm/yyyy') shp_bill_date,dbk_admisable,nvl(str_due,0)-nvl(str_woff,0) str_due,rosl_due from ei_dbk_mast where (shp_bill_no,shp_bill_date) in (select distinct shp_bill_no,shp_bill_Date from ei_shipment_dtls  where link_no in ("+DBKLINK+") )  ");
                                           // st.setString(1,DBKYEAR);
                                         
                                            rs = st.executeQuery();
                                            while (rs.next()) {  
                                              
                                                        SBLIST.add(new PostFinSBBean(rs.getString("shp_bill_no"),rs.getString("shp_bill_date"),rs.getDouble("dbk_admisable"),rs.getDouble("str_due"),rs.getDouble("rosl_due")));
                                              }
                            
                        }
                       
                    }
                   
              
             }
                catch(Exception e){
                    System.out.println(e.toString());
                    e.printStackTrace();
                }
             finally { 
          
                if (result3!=null){result3.close();}
                if (stat3 != null) {stat3.close(); }
                if (rs != null) {rs.close();}
                if (st != null) {st.close();}
                if (conn != null){conn.close();}
                if (rs1 != null) {rs1.close();}
                if (st1 != null) {st1.close();}
                if (rs3 != null) {rs3.close();}
                if (st3 != null) {st3.close();}
                if (rs2 != null) {rs2.close();}
                if (st2 != null) {st2.close();}
                if (conndb2 != null){conndb2.close();}
                  if (conn != null) {conn.close();}
          
            
          
          }
             
            
            
            
            return "edit";
 }
     public String save() throws SQLException{
                 Connection conn = null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
                 PreparedStatement stat3=null;
                 ResultSet result3=null;
                 PreparedStatement stat4=null;
                 ResultSet result4=null;
                 
            try {
                conn = new connection().getConnection();
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
             
              SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
               SimpleDateFormat formatter1 = new SimpleDateFormat("dd.MMM.yyyy");
             String date1=null;
             String date2=null;
          try{      conn = new connection().getConnection();
                    conn.setAutoCommit(false);
              
                    stat3= conn.prepareStatement("select location_code from seh_web_users where trim(user_id)=?") ;
                    stat3.setString(1,usrid.trim());
                    result3=stat3.executeQuery();
                    while (result3.next())
                    {
                        LOCATION_CODE=result3.getString("location_code");
                    } 
                    if (result3!=null){result3.close();}
                    if (stat3 != null) {stat3.close(); }
                   int x=0,y=0, NEWTERM=1;   
             
                  
                     String P_APRVDT="";   String awbdate=""; 
                     Date apdate=null;
                     if (saveFlag!=null && saveFlag.length()>0)
                     {
                         
                         
                      if (LINK_NO != null && LINK_NO.size() > 0 )
                        {  
                                           stat3=conn.prepareStatement("select to_number(vou_numb)+1 newterm from pr_vou_numb_mast where location_code = 'DOCF' and FIN_YEAR=to_char(sysdate,'yyyy')  and VOU_TYPE=to_char(sysdate,'mm') and SUB_TYPE=to_char(sysdate,'dd') ");
                                            result3=stat3.executeQuery();
                                            if (result3.next())
                                            {
                                               NEWTERM=result3.getInt("newterm");
                                               
                                               stat2=conn.prepareStatement("update pr_vou_numb_mast set vou_numb=? where location_code = 'DOCF' and FIN_YEAR=to_char(sysdate,'yyyy')  and VOU_TYPE=to_char(sysdate,'mm') and SUB_TYPE=to_char(sysdate,'dd') ");
                                               stat2.setInt(1,NEWTERM);
                                               stat2.executeUpdate();
                                           }else{
                                                stat2=conn.prepareStatement("update pr_vou_numb_mast set vou_numb=1 ,FIN_YEAR=to_char(sysdate,'yyyy'),VOU_TYPE=to_char(sysdate,'mm'), SUB_TYPE=to_char(sysdate,'dd') where location_code = 'DOCF'  ");
                                                stat2.executeUpdate();
                                            
                                            }  
                                        if (result3!=null){result3.close();}
                                        if (stat3 != null) {stat3.close(); }
                            for (int i = 0; i < LINK_NO.size(); i++)
                            {      P_YEAR=YEAR.get(i).toString().trim();
                                   P_LINK_NO=LINK_NO.get(i).toString().trim();
                                  
                                   if (P_LINK_NO!=null && P_LINK_NO.length()>0)
                                { 
                                    P_APRVDT=APRV_DATE.get(i).toString().trim();
                                    String duedt="";
                                    String DATE_FORMAT = "dd-MMM-yyyy";
                                    String DATE_FORMAT1 = "yyyy-mm-dd";
                                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
                                    java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(DATE_FORMAT1);
                                    
                                   if (P_APRVDT!=null && P_APRVDT.length()>6){
                                       P_APRVDT=P_APRVDT.substring(0,10);
                                       apdate = (Date)sdf1.parse(P_APRVDT);
                                   }
                                   
                                     awbdate=AWB_DATE.get(i).toString();
                                  
                                    
                                    Date awbDate = (Date)sdf.parse(awbdate);
                                     
                                   
                                  
                                   stat3=conn.prepareStatement("select all_no,to_char(shp_bill_date,'dd-Mon-yyyy') sbdate,to_char(t_o_date,'dd-Mon-yyyy') todate,xstepy,xsnody,to_char(sysdate,'dd-Mon-yyyy') edate from ei_shipment_dtls a,ei_endors_mast b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.year=? and a.link_no=?  ");
                                   stat3.setString(1,P_YEAR);
                                   stat3.setString(2,P_LINK_NO);
                                    result3=stat3.executeQuery();
                                    while (result3.next()) 
                                    {
                                          if (result3.getString("XSTEPY").trim().equals("01") || result3.getString("XSTEPY").trim().equals("07") || result3.getString("XSTEPY").trim().equals("10"))
                                              {     Calendar c = Calendar.getInstance();    
                                                    c.setTime(awbDate);
                                                    c.add(Calendar.DATE,result3.getInt("xsnody") );
                                                    String cdate=sdf.format(c.getTime());
                                                    duedt=cdate;
                                                    System.out.println(" awb "+duedt);
                                               }
                                                if (result3.getString("XSTEPY").trim().equals("02"))
                                              
                                                 { 
                                                     Calendar c = Calendar.getInstance();    
                                                     c.setTime((Date)sdf.parse(result3.getString("sbdate")));
                                                     c.add(Calendar.DATE,result3.getInt("xsnody") );
                                                     String cdate=sdf.format(c.getTime());
                                                     duedt=cdate;
                                                      System.out.println(" sb "+duedt);
                                                 }
                                                if (result3.getString("XSTEPY").trim().equals("04"))
                                                 { 
                                                     Calendar c = Calendar.getInstance();    
                                                     c.setTime((Date)sdf.parse(result3.getString("edate")));
                                                     c.add(Calendar.DATE,result3.getInt("xsnody") );
                                                     String cdate=sdf.format(c.getTime());
                                                     duedt=cdate;
                                                      System.out.println(" edate "+duedt);
                                                 } 
                                                if (result3.getString("XSTEPY").trim().equals("05"))
                                                 {  
                                                     if (apdate==null){
                                                          
                                                           ERRMSG="Enter Approval Date " ;
                                                          addActionError("Enter Approval Date");
                                                        
                                                         return "error";
                                                   }
                                                    Calendar c = Calendar.getInstance();    
                                                     c.setTime(apdate);
                                                     c.add(Calendar.DATE,result3.getInt("xsnody") );
                                                     String cdate=sdf.format(c.getTime());
                                                     duedt=cdate;
                                                      System.out.println(" ap "+duedt);
                                                 }
                                                if (result3.getString("XSTEPY").trim().equals("06"))
                                                
                                                 { Calendar c = Calendar.getInstance();    
                                                     c.setTime((Date)sdf.parse(result3.getString("todate")));
                                                     c.add(Calendar.DATE,result3.getInt("xsnody") );
                                                     String cdate=sdf.format(c.getTime());
                                                     duedt=cdate;
                                                      System.out.println(" to "+duedt);
                                                 } 
                                            
                                                 stat4=conn.prepareStatement("update ei_shipment_dtls set xsdudt=? where all_no=? ");
                                                 stat4.setString(1,duedt);
                                                 stat4.setString(2,result3.getString("all_no"));
                                                 stat4.executeUpdate();
                                     }  /// invoice  loop end
                                           if (result4!=null){result4.close();}
                                           if (stat4 != null) {stat4.close(); }
                                           if (result3!=null){result3.close();}
                                           if (stat3 != null) {stat3.close(); }
                                           
                                               
                                            stat4=conn.prepareStatement("update ei_shipment_mast set f_bank=upper(?),f_bank_addr=upper(?),drawn_bank=upper(?),d_bank_addr=upper(?),SHIP_ADVICE=upper(?),edi_Date=to_date(?,'yyyy-mm-dd'),fuser=?,doc_send_date=sysdate,AC_SEND_DATE=trunc(sysdate),ac_send_term=?  where year=? and link_no=? ") ;     
                                            stat4.setString(1,FBANK);
                                            stat4.setString(2,FBANK_ADDNO);
                                            stat4.setString(3,DBANK);
                                            stat4.setString(4,DBANK_ADDNO);
                                            stat4.setString(5,PAYTERM);
                                            stat4.setString(6,P_APRVDT);
                                            stat4.setString(7,usrid);
                                            stat4.setInt(8,NEWTERM);
                                            stat4.setString(9,P_YEAR);
                                            stat4.setString(10,P_LINK_NO);
                                          
                                            x=stat4.executeUpdate();
                                }                 
                          
                          
                          
                        }
                      
                     }
                  }
                  if (x>0)
                  {    conn.commit();
                      FBANK=null;
                      DBANK=null;
                      FBANK_ADDNO=null;
                      DBANK_ADDNO=null;
                      PAYTERM=null;
                      AWB_NO=null;
                      AWB_DATE=null;
                      HAWB_NO=null;
                      APRV_DATE=null;
                      YEAR=null;
                      LINK_NO=null;
                      P_YEAR=null;
                      P_LINK_NO=null;   
                    
                      addActionError("Reocrd Save !!! Term No "+NEWTERM);
                          
                 }else{ conn.rollback();}
             }
                catch(Exception e){
                    System.out.println(e.toString());
                     addActionError("ERROR :"+e);
                    e.printStackTrace();
                }
             finally { 
            if (conn != null) {conn.close();}
          
            if (result2!=null){result2.close();}
            if (stat2 != null) {stat2.close(); }
            if (result3!=null){result3.close();}
            if (stat3 != null) {stat3.close(); }
            if (result4!=null){result4.close();}
            if (stat4 != null) {stat4.close(); }
          
          }
              
             
             
            
            return "save";
 }
    
    public String bankview() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        if (usrid == null && loc == null) {
            addActionError("Session not valid!!!");
            return SUCCESS;
        }

        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conndb2 = new connectiondb2().getConnection();

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat=conndb2.prepareStatement("SELECT trim(BRBKBM) brbkbm,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn  from m3fdbprd.cbanbr where brcono=111 and  (BRBKNO like ? or BRBKBM like ?) ");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    buyeraddlist.add(new BuyersearchBean(result.getString("BRBKNO"), result.getString("BRBBRN"),result.getString("BRBKBM"),result.getString("bankaddr"),"")); 
                }
            }
  
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
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

        return "bankview";
    }
      
   
    public String Dbankview() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        if (usrid == null && loc == null) {
            addActionError("Session not valid!!!");
            return SUCCESS;
        } 

        Connection conndb2 = null;
        PreparedStatement stat = null;
        ResultSet result = null;

        try {
            conndb2 = new connectiondb2().getConnection();

            if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                stat=conndb2.prepareStatement("SELECT trim(BRBKBM) brbkbm,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn  from m3fdbprd.cbanbr where brcono=111 and  (BRBKNO like ? or BRBKBM like ?) ");
                stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                stat.setString(2, "%" + SEARCH_CODE.toUpperCase() + "%");
                result = stat.executeQuery();
                while (result.next()) {
                    buyeraddlist.add(new BuyersearchBean(result.getString("BRBKNO"), result.getString("BRBBRN"),result.getString("BRBKBM"),result.getString("bankaddr"),"")); 
                }
            }
 
        } catch (Exception e) { 
            System.out.println(e.toString());
        } finally {
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

        return "Dbankview";
    }
    
   public String Awbview() throws SQLException {
        Map session = ActionContext.getContext().getSession();
        String loc = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        if (usrid == null && loc == null) {
            addActionError("Session not valid!!!");
            return SUCCESS;
        }  

        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        ResultSet result = null;
        ResultSet result1 = null;
        try {
            conn = new connection().getConnection();

             if (SEARCH_CODE != null && SEARCH_CODE.length() > 0) {
                       String APRV_DATE="";
                        stat = conn.prepareStatement(" select awb_no,to_char(awb_date,'dd-MON-yyyy') AWB_date,h_awb_no,YEAR,LINK_NO from  ei_shipment_mast where  t_mod='LGM4' AND AC_SEND_DATE is  null and AWB_NO like ?");
                        stat.setString(1, "%" + SEARCH_CODE.toUpperCase() + "%");
                        result = stat.executeQuery();
                        while (result.next()) {
                            stat1=conn.prepareStatement("select to_char(min(tr_date),'yyyy-mm-dd') pudate from ei_truckout_track where TR_TYPE='PU' AND  (year,company,inv_no) in (select year,company,inv_no from ei_shipment_dtls where year=? and link_no=?)");
                             stat1.setString(1,result.getString("year"));
                             stat1.setString(2,result.getString("link_no"));
                             result1=stat1.executeQuery();
                             if (result1.next())
                             {
                                     APRV_DATE=result1.getString("pudate");
                              }
                            
                            LicSearch.add(new LicSearchBean(result.getString("awb_no"),result.getString("AWB_date"),result.getString("h_awb_no"),APRV_DATE,result.getString("year"),result.getString("link_no")));
                             
                        }
                    }
 
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn!= null) {
                conn.close();
            }
            if (stat != null) {stat.close();}
            if (result != null){result.close();}
             if (stat1 != null) {stat1.close();}
            if (result1 != null){result1.close();}
        }

        return "Awbview";
    } 
   
     public String detailAwb() throws SQLException {
       
        if (P_YEAR != null && P_LINK_NO!=null) {
              Connection conn = null;
              Connection conndb2=null;
              PreparedStatement st=null;
              PreparedStatement st1=null;
              PreparedStatement st2=null;
               PreparedStatement st3=null;
              ResultSet rs=null;
              ResultSet rs1=null;
              ResultSet rs2=null;
               ResultSet rs3=null;
            try {
                
                if (P_YEAR != null && P_YEAR !=null) {
                    conn = new connection().getConnection();
                    conndb2 = new connectiondb2().getConnection();
                    
                    st = conn.prepareStatement("select distinct ex_track_no,co_numb,del_numb from ei_ship_Data a,ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.year=? and b.link_no=?   ");
                    st.setString(1,P_YEAR);
                    st.setString(2,P_LINK_NO);
                    rs = st.executeQuery();
                    while (rs.next()) {
                         
                          double dbkamt=0.0; double stramt=0.0; double roslamt=0.0; double taxamt=0.0;
                          st1=conndb2.prepareStatement(" SELECT  OEORNO ,oedlix,oecrid,oeacrf,OECRD0,OECRAM,oecrfa from m3fdbprd.oochrg where oecono='111' and oeorno=? and oedlix=? ");
                          st1.setString(1,rs.getString("co_numb"));
                          st1.setString(2,rs.getString("del_numb"));
                          rs1=st1.executeQuery();
                          while (rs1.next())
                          {   
                             if (rs1.getString("oecrid").trim().equals("DBK")){ roslamt=0.0;stramt=0.0;taxamt=0.0;dbkamt=rs1.getDouble("OECRAM");}
                             if (rs1.getString("oecrid").trim().equals("SVTAX")){ dbkamt=0.0;roslamt=0.0;taxamt=0.0;stramt=rs1.getDouble("OECRAM");}
                             if (rs1.getString("oecrid").trim().equals("ROSL")){ dbkamt=0.0;stramt=0.0;taxamt=0.0;roslamt=rs1.getDouble("OECRAM");}
                             if (!rs1.getString("oecrid").trim().equals("ROSL") && !rs1.getString("oecrid").trim().equals("SVTAX") && !rs1.getString("oecrid").trim().equals("DBK")){ dbkamt=0.0;stramt=0.0;roslamt=0.0;taxamt=rs1.getDouble("OECRAM");}
                          
                            CHRGLIST.add(new PostChargeBean(rs.getString("ex_track_no"),rs1.getString("OEORNO"),rs1.getString("oedlix"),rs1.getString("oeacrf"),rs1.getString("oecrid"),rs1.getString("OECRD0"),dbkamt,stramt,roslamt,taxamt,rs1.getString("oecrfa")));
                          } 
                         
                    } 
                    if (rs != null) {rs.close();}
                   if (st != null) {st.close();} 
                   String duedesc="";  String buyergl="";
                     st = conn.prepareStatement("select excs_inv_no,a.currency,c.desti_cntry,buyer,buyer_addr,cons_addr,lc_no,xstepy,xsnody,t_o_date,edi_date,plan_no,fob_amt,tax_percent,sum(gr_decl_amt) gr_decl,sum(nvl(discount_amt,0)) inv_disc "+
                                                " from ei_shipment_dtls a,ei_endors_dtls b,ei_endors_mast c where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.year=c.year and b.company=c.company and b.inv_no=c.inv_no and  a.year=? and a.link_no=? "+
                                                " group by excs_inv_no,a.currency,c.desti_cntry,buyer,buyer_addr,cons_addr,lc_no,xstepy,xsnody,t_o_date,edi_date,plan_no,fob_amt,tax_percent  ");
                    st.setString(1,P_YEAR);
                    st.setString(2,P_LINK_NO);
                    rs = st.executeQuery(); 
                    while (rs.next()) {
                         if (rs.getString("XSTEPY").trim().equals("01") || rs.getString("XSTEPY").trim().equals("07") || rs.getString("XSTEPY").trim().equals("10"))
                         {duedesc="Awb Date";
                         }
                         if (rs.getString("XSTEPY").trim().equals("02"))
                          {duedesc="SB Date";
                          }
                         if (rs.getString("XSTEPY").trim().equals("04"))
                          {duedesc="77 Date";
                          }
                         if (rs.getString("XSTEPY").trim().equals("05"))
                          {duedesc="Aprv Date";
                          }if (rs.getString("XSTEPY").trim().equals("06"))
                          {duedesc="TO Date";
                          }
                          st1=conndb2.prepareStatement(" SELECT  opcunm,optel2 from m3fdbprd.ocusad where opcono='111' and trim(opcuno)=? and trim(opadid)=? ");
                          st1.setString(1,rs.getString("buyer").trim());
                          st1.setString(2,rs.getString("buyer_addr").trim());
                          rs1=st1.executeQuery();
                          if (rs1.next())
                          {  buyergl=rs1.getString("optel2");
                                                        
                          }else{
                             ERRMSG="Buyer GL Code Not Mapped for Invoice No "+rs.getString("excs_inv_no");
                          
                              addActionError("Buyer GL Code Not Mapped for Invoice No "+rs.getString("excs_inv_no"));
                             return "error";
                          }
                            if (rs1 != null) {rs1.close();}
                            if (st1 != null) {st1.close();}
                          
                           
                            INVLIST.add(new PostFinInvBean(rs.getString("excs_inv_no"),rs.getString("buyer"),rs.getString("buyer_addr"),rs.getString("cons_addr"),buyergl,rs.getString("currency"),rs.getString("desti_cntry"),rs.getString("lc_no"),rs.getString("lc_no"),rs.getString("XSTEPY"),rs.getString("XSNODY"),rs.getDouble("fob_amt"),rs.getDouble("gr_decl"),rs.getDouble("inv_disc"),rs.getDouble("tax_percent"),duedesc,P_YEAR,P_LINK_NO));
                     
                     
                    }
                    if (rs != null) {rs.close();}
                    if (st != null) {st.close();}
                    
                     st = conn.prepareStatement("select  shp_bill_no,to_char(shp_bill_Date,'dd/mm/yyyy') shp_bill_date,dbk_admisable,nvl(str_due,0)-nvl(str_woff,0) str_due,rosl_due from ei_dbk_mast where (shp_bill_no,shp_bill_date) in (select distinct shp_bill_no,shp_bill_Date from ei_shipment_dtls  where year=? and link_no=? )  ");
                     st.setString(1,P_YEAR);
                     st.setString(2,P_LINK_NO);
                     rs = st.executeQuery();
                     while (rs.next()) { 
                         
                         SBLIST.add(new PostFinSBBean(rs.getString("shp_bill_no"),rs.getString("shp_bill_date"),rs.getDouble("dbk_admisable"),rs.getDouble("str_due"),rs.getDouble("rosl_due")));
                    }
                     if (rs != null) {rs.close();}
                     if (st != null) {st.close();}
                   ///////////// LC Status Check
                           
                            st=conn.prepareStatement("select all_no from ei_shipment_dtls where year=? and link_no=? ");
                            st.setString(1,P_YEAR);
                            st.setString(2,P_LINK_NO);
                            rs=st.executeQuery();
                            while (rs.next())
                            {   
                               String v_payterm=""; 
                                st3=conn.prepareStatement("select distinct co_no from ei_endors_dtls where all_no=?");
                                st3.setString(1,rs.getString("all_no"));
                                rs3=st3.executeQuery();
                                while (rs3.next())
                             {
                                st1=conndb2.prepareStatement("select  oapycd,oatepy  from m3fdbprd.oohead  where  oacono = 111 and oadivi = '100' and  oaorno=? ");
                                st1.setString(1,rs3.getString("co_no").trim());
                                rs1=st1.executeQuery();
                                while (rs1.next())
                                {
                                   
                                   if (v_payterm!=null && v_payterm.length()>1 && rs1.getString("oatepy")!=null)
                                   {
                                      if (v_payterm!=rs1.getString("oatepy"))
                                      {
                                         ERRMSG="All CO Must be same Paymter Term Check [OIS300]  "+rs.getString("co_no");
                                          
                                         addActionError("All CO Must be same Paymter Term Check [OIS300]  "+rs.getString("co_no"));
                                          return "error";  
                                      }
                                   }
                                   else
                                   {
                                      v_payterm=rs1.getString("oatepy"); 
                                   }
                                   if (rs1.getString("oatepy").trim().equals("ILC") || rs1.getString("oatepy").trim().equals("FLC") )
                                   {
                                            st2=conndb2.prepareStatement("select lestat from m3fdbprd.clomas a,m3fdbprd.cloord where lecono = orcono and ledivi = ordivi and leltpe  = orltpe and lelecr = orlecr and leltpe = 2 and orcono = 111 and ordivi = '100' and orridn =?");
                                            st2.setString(1,rs.getString("co_no").trim());
                                            rs2=st2.executeQuery();
                                            while (rs2.next())
                                            {
                                                if (rs2.getInt("lestat")<40)
                                                {
                                                  ERRMSG="Check LC Status  "+rs.getString("lestat")+"  , this should be 40";
                                                 addActionError(" Check Lc Status  "+rs.getString("lestat")+"  , this should be 40");
                                                  return "error";  
                                                }
                                            }
                                              if (rs2 != null) {rs.close();}
                                              if (st != null) {st.close();}
                                       }
                                   }
                                              if (rs1 != null) {rs1.close();}
                                              if (st1 != null) {st1.close();}
                                   }
                                
                                              if (rs3 != null) {rs3.close();}
                                              if (st3 != null) {st3.close();}
                            }
                                        
                                              if (rs != null) {rs.close();}
                                              if (st != null) {st.close();}
                   /// LC Status Check point End... 
                                        String p_error="";
                                     
                                        st =conn.prepareStatement(" select  exports.validate_movex_data77(?,?) aa from dual ");
                                        st.setString(1,P_YEAR);
                                        st.setString(2,P_LINK_NO);
                                         rs=st.executeQuery();
                                       if (rs.next())
                                        {    
                                                if (rs.getString("aa")!=null)
                                                {   ERRMSG="Error: "+rs.getString("aa");
                                                    addActionError(" Error: "+rs.getString("aa"));
                                                     return   "error"; 
                                                } 
                                         }
                             
                }
            } catch (Exception ee) {
                System.out.println(ee.toString());
                ee.printStackTrace();
            } finally {
                if (rs != null) {rs.close();}
                if (st != null) {st.close();}
                if (conn != null){conn.close();}
                if (rs1 != null) {rs1.close();}
                if (st1 != null) {st1.close();}
                if (rs3 != null) {rs3.close();}
                if (st3 != null) {st3.close();}
                if (rs2 != null) {rs2.close();}
                if (st2 != null) {st2.close();}
                if (conndb2 != null){conndb2.close();}
            }
        }
        return SUCCESS;
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

    public List getINVLIST() {
        return INVLIST;
    }

    public void setINVLIST(List INVLIST) {
        this.INVLIST = INVLIST;
    }


    

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

    public List getBuyeraddlist() {
        return buyeraddlist;
    }

    public void setBuyeraddlist(List buyeraddlist) {
        this.buyeraddlist = buyeraddlist;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public String getMAXDATE() {
        return MAXDATE;
    }

    public void setMAXDATE(String MAXDATE) {
        this.MAXDATE = MAXDATE;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }

    public List getLicSearch() {
        return LicSearch;
    }

    public void setLicSearch(List LicSearch) {
        this.LicSearch = LicSearch;
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

    public List getCHRGLIST() {
        return CHRGLIST;
    }

    public void setCHRGLIST(List CHRGLIST) {
        this.CHRGLIST = CHRGLIST;
    }

    public List getAWB_NO() {
        return AWB_NO;
    }

    public void setAWB_NO(List AWB_NO) {
        this.AWB_NO = AWB_NO;
    }

    public List getAWB_DATE() {
        return AWB_DATE;
    }

    public void setAWB_DATE(List AWB_DATE) {
        this.AWB_DATE = AWB_DATE;
    }

    public List getHAWB_NO() {
        return HAWB_NO;
    }

    public void setHAWB_NO(List HAWB_NO) {
        this.HAWB_NO = HAWB_NO;
    }

    public List getAPRV_DATE() {
        return APRV_DATE;
    }

    public void setAPRV_DATE(List APRV_DATE) {
        this.APRV_DATE = APRV_DATE;
    }
   
 
    public List getSBLIST() {
        return SBLIST;
    }

    public void setSBLIST(List SBLIST) {
        this.SBLIST = SBLIST;
    }

    public Double getTDBK_DUE() {
        return TDBK_DUE;
    }

    public void setTDBK_DUE(Double TDBK_DUE) {
        this.TDBK_DUE = TDBK_DUE;
    }

    public List getSAVETEXT() {
        return SAVETEXT;
    }

    public void setSAVETEXT(List SAVETEXT) {
        this.SAVETEXT = SAVETEXT;
    }

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }
 
    public List getYEAR() {
        return YEAR;
    }

    public void setYEAR(List YEAR) {
        this.YEAR = YEAR;
    }

    public List getLINK_NO() {
        return LINK_NO;
    }

    public void setLINK_NO(List LINK_NO) {
        this.LINK_NO = LINK_NO;
    }

    public static com.opensymphony.xwork2.util.logging.Logger getLOG() {
        return LOG;
    }

    public static void setLOG(com.opensymphony.xwork2.util.logging.Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public String getFBANK() {
        return FBANK;
    }

    public void setFBANK(String FBANK) {
        this.FBANK = FBANK;
    }

    public String getFBANK_ADDNO() {
        return FBANK_ADDNO;
    }

    public void setFBANK_ADDNO(String FBANK_ADDNO) {
        this.FBANK_ADDNO = FBANK_ADDNO;
    }

    public String getDBANK() {
        return DBANK;
    }

    public void setDBANK(String DBANK) {
        this.DBANK = DBANK;
    }

    public String getDBANK_ADDNO() {
        return DBANK_ADDNO;
    }

    public void setDBANK_ADDNO(String DBANK_ADDNO) {
        this.DBANK_ADDNO = DBANK_ADDNO;
    }

    public String getPAYTERM() {
        return PAYTERM;
    }

    public void setPAYTERM(String PAYTERM) {
        this.PAYTERM = PAYTERM;
    }

    

   

     

      
     

 

    
    
     
}
 