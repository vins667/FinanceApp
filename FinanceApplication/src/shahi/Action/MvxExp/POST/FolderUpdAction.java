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
import shahi.Action.MvxExp.POST.Beans.FolderUpdBean;
  

public class FolderUpdAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    
    private List showList;
    
    private String searchdate;
    private String searchterm;
    private String searchawb;
    private String aausrid;
    private List DOC_NO;
    private List FOLDER_NO;
    private List INVOICE_NO;

    private Double TOTQTY=0.0;
    private Double TOTFOB=0.0;
    private Double TOTINR=0.0;
    private Double TOTGR=0.0;
    private Double TOTDISC=0.0;
    private String upd_allow="NO";
   
    private String saveFlag;
   
  
    private List ShowDetail=new ArrayList();
    
    private String search_awb;
    private Date search_date;
    private String search_hawb;
    private Date search_findate;
    private String searchi;
    private String search_bank;
    private List inv_no=new ArrayList();
   
 
    
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
                stat1= conn.prepareStatement("select * from pa_auth_mast where user_id=? and prog_name='FOLDERUPD' ") ;
                stat1.setString(1,usrid);
                result1=stat1.executeQuery();
                if (result1.next())
                { upd_allow= "YES";
                } 
                    if (saveFlag!=null && saveFlag.equals("YES"))
                    { 
                  
                        
                      for(int i=0;i<INVOICE_NO.size();i++)
                        {
                           stat2 = conn.prepareStatement("UPDATE ei_shipment_dtls set doc_no=?,folder_numb=?  where all_no=?");
                            stat2.setString(1,DOC_NO.get(i).toString());
                             stat2.setString(2,FOLDER_NO.get(i).toString());
                            stat2.setString(3,INVOICE_NO.get(i).toString());
                             stat2.executeUpdate();
                           flag=1;
                       }
                      
                    }  
                    
             SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
             String date1=null;
             String date2=null;
            if (search_date != null ) {
                
              date1=  simpledateformate1.format(search_date);
            }
            if(search_findate != null){
               date2=  simpledateformate1.format(search_findate);
            }
                    
              
              
                     
                    
                if ((viewFlag != null && viewFlag.equals("Yes"))) 
                {  
                    
                    String q1 = " ";
                        if (search_awb != null && search_awb.length()> 0) {
                            q1 += " and a.AWB_NO like nvl('"+search_awb+"','%')";             
                        }
                        if (search_date != null) {               
                            q1 += " and a.AWB_DATE='"+ date1 + "'";
                        }
                        if (search_hawb != null && search_hawb.length()>0) {
                            q1 += " and a.H_AWB_NO like '%" + search_hawb + "%'";
                        }

                        if (search_findate != null) {
                            q1 += " and a.ac_send_date='"+ date2 +"' ";
                        }
                         if (searchi != null && searchi.length()>0) {
                            q1 += " and a.ac_send_term like nvl('"+searchi+"','%')";
                        }
                         

                    if (search_findate!=null )
                      { 
                           
                            stat2 = conn.prepareStatement("select to_char(ac_send_date,'dd/mm/yyyy') ac_send_date,ac_send_term,awb_no,to_char(awb_date,'dd/mm/yyyy') awb_date,b.COMPANY,b.LOCATION,shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date,to_char(let_exp_date,'dd/mm/yyyy') let_exp_date,all_no,dbk_conv,inr_conv,b.folder_numb,doc_no,ship_qnty,fob_amt,currency,fob_amt*inr_conv fob_inr from ei_shipment_mast a,ei_shipment_dtls b where a.year=b.year and a.link_no=b.link_no and b.location=? "+q1+" order by 1,2,3");
                           
                            
                       }  
                       if (search_findate==null && search_awb!=null) 
                       {
                            stat2 = conn.prepareStatement("select to_char(ac_send_date,'dd/mm/yyyy') ac_send_date,ac_send_term,awb_no,to_char(awb_date,'dd/mm/yyyy') awb_date,b.COMPANY,b.LOCATION,shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date,to_char(let_exp_date,'dd/mm/yyyy') let_exp_date,all_no,dbk_conv,inr_conv,b.folder_numb,doc_no,ship_qnty,fob_amt,currency,fob_amt*inr_conv fob_inr from ei_shipment_mast a,ei_shipment_dtls b where a.year=b.year and a.link_no=b.link_no and b.location=? "+q1+"   order by 1,2,3");
                       }
                         stat2.setString(1,LOCATION_CODE);
                         result2 = stat2.executeQuery();
                         while (result2.next()) 
                          { 
                            
                            TOTQTY=TOTQTY+roundTwoDecimals(result2.getDouble("ship_qnty"));
                            TOTFOB=roundTwoDecimals(TOTFOB+result2.getDouble("FOB_AMT"));
                            TOTINR=roundTwoDecimals(TOTINR+result2.getDouble("FOB_INR"));
                            String grdecl=""; String disc="";
                            stat1=conn.prepareStatement("select sum(gr_decl_amt) grdecl,sum(discount_amt) disc from ei_endors_dtls where all_no=? ");
                            stat1.setString(1,result2.getString("all_no"));
                            result1=stat1.executeQuery();
                            if (result1.next())
                            {
                              TOTGR=roundTwoDecimals(TOTGR+result1.getDouble("grdecl"));
                              TOTDISC=roundTwoDecimals(TOTDISC+result1.getDouble("disc"));
                              grdecl=result1.getString("grdecl");
                              disc=result1.getString("disc");
                            }
                                
                             ShowDetail.add(new FolderUpdBean(result2.getString("awb_no"),result2.getString("awb_date"),result2.getString("ac_send_date"),result2.getString("ac_send_term"),result2.getString("shp_bill_no"),result2.getString("shp_bill_date"),result2.getString("shp_bill_date"),result2.getString("let_exp_date"),result2.getString("all_no"),result2.getString("DBK_CONV"),result2.getString("DBK_CONV"),result2.getString("INR_CONV"),result2.getString("currency"),result2.getString("FOB_AMT"),result2.getString("FOB_INR"),grdecl,disc,result2.getString("SHIP_QNTY"),result2.getString("DOC_NO"),result2.getString("FOLDER_Numb")));
                             
                          }         
                        
                           
                          
                           
                  
                } // View Flg Close      
          
                
                
            
                       
                      
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : FolderUpdAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : FolderUpdAction.java" + e);

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
           
            searchawb=null;
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

    public List getDOC_NO() {
        return DOC_NO;
    }

    public void setDOC_NO(List DOC_NO) {
        this.DOC_NO = DOC_NO;
    }

    public List getFOLDER_NO() {
        return FOLDER_NO;
    }

    public void setFOLDER_NO(List FOLDER_NO) {
        this.FOLDER_NO = FOLDER_NO;
    }

    public List getINVOICE_NO() {
        return INVOICE_NO;
    }

    public void setINVOICE_NO(List INVOICE_NO) {
        this.INVOICE_NO = INVOICE_NO;
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

    public String getUpd_allow() {
        return upd_allow;
    }

    public void setUpd_allow(String upd_allow) {
        this.upd_allow = upd_allow;
    }

    public Double getTOTINR() {
        return TOTINR;
    }

    public void setTOTINR(Double TOTINR) {
        this.TOTINR = TOTINR;
    }

    public String getSearch_awb() {
        return search_awb;
    }

    public void setSearch_awb(String search_awb) {
        this.search_awb = search_awb;
    }

    public Date getSearch_date() {
        return search_date;
    }

    public void setSearch_date(Date search_date) {
        this.search_date = search_date;
    }

    public Date getSearch_findate() {
        return search_findate;
    }

    public void setSearch_findate(Date search_findate) {
        this.search_findate = search_findate;
    }

    public String getSearch_hawb() {
        return search_hawb;
    }

    public void setSearch_hawb(String search_hawb) {
        this.search_hawb = search_hawb;
    }
    public String getSearchi() {
        return searchi;
    }

    public void setSearchi(String searchi) {
        this.searchi = searchi;
    }

    public String getSearch_bank() {
        return search_bank;
    }

    public void setSearch_bank(String search_bank) {
        this.search_bank = search_bank;
    }

    public List getInv_no() {
        return inv_no;
    }

    public void setInv_no(List inv_no) {
        this.inv_no = inv_no;
    }

   
   

   

   
 
       
}
 