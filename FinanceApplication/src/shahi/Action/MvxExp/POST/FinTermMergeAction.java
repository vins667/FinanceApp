package shahi.Action.MvxExp.POST;

  
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
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.POST.Beans.FinTermBean;
import shahi.Action.MvxExp.POST.Beans.FinTermBean2;
  

public class FinTermMergeAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private String viewFlag2;
    
    private String searchdate;
    private String searchterm;
    private String searchdate2;
    private String searchterm2;  
    private String aausrid;
    private List DOC_NO;
    private List FOLDER_NO;
    private List INVOICE_NO;

    private Double TOTQTY=0.0;
    private Double TOTFOB=0.0;
    private Double TOTINR=0.0;
    private Double TOTGR=0.0;
    private Double TOTDISC=0.0;
    
    private Double TOTQTY2=0.0;
    private Double TOTFOB2=0.0;
    private Double TOTINR2=0.0;
    private Double TOTGR2=0.0;
    private Double TOTDISC2=0.0;
    
    private String saveFlag;
    private String NewFlag;
    private String MergeFlag;
    private String[] newrec;
  
    private List ShowDetail=new ArrayList();
    private List ShowDetail2=new ArrayList();
    private String TERM_NO;
    private Date SEARCH_DATE;
    private Date MDATE1;
    private Date MDATE2;
    private String MTERM2;
    private String TERM_NO2;
    private Date SEARCH_DATE2;
    private List SEL_CHECK = new ArrayList();
    private String year;
    private String linkno;
    
    @Override
    public String execute() {
        
        firstsrch();
        secondsrch();
        
        return SUCCESS;
        
    }
    
    public String secondsrch()
    {
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
                
                SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yyyy");
                String date1=null;
                if (SEARCH_DATE != null ) {
                   date1=  simpledateformate1.format(SEARCH_DATE);
                }

                
             
               
                SimpleDateFormat simpledateformate2 = new SimpleDateFormat("dd-MMM-yyyy");
                String date2=null;
                if (SEARCH_DATE2 != null ) {
                   date2=  simpledateformate2.format(SEARCH_DATE2);
                }

                       
                if ((viewFlag2 != null && viewFlag2.equals("Yes"))) 
                {         String q2 = " ";
                         if (SEARCH_DATE2 != null) {               
                            q2 += " and trunc(a.doc_send_date)='"+ date2 +"' ";
                         }
                        if (TERM_NO2 != null && TERM_NO2.length()> 0) {
                           q2 += " and a.ac_send_term= "+TERM_NO2;         
                        }
                    
                    if (SEARCH_DATE2!=null || TERM_NO2 != null )
                      { 
                               
                          stat2 = conn.prepareStatement("select to_char(ac_send_date,'dd/mm/yyyy') ac_send_date,ac_send_term,awb_no,to_char(awb_date,'dd/mm/yyyy') awb_date,h_awb_no,ship_advice,all_no,ship_qnty,fob_amt,currency,fob_amt*inr_conv fob_inr,c.desti_cntry,c.location,c.buyer,b.lc_no,a.year,a.link_no from ei_shipment_mast a,ei_shipment_dtls b,ei_endors_mast c where  a.year=b.year and a.link_no=b.link_no and b.year=c.year and b.company=c.company and b.inv_no=c.inv_no "+q2+" order by 1,2,3");
                        //  stat2.setString(1,LOCATION_CODE);
                          result2 = stat2.executeQuery();
                          while (result2.next()) 
                          { TOTQTY2=TOTQTY2+roundTwoDecimals(result2.getDouble("ship_qnty"));
                            TOTFOB2=roundTwoDecimals(TOTFOB2+result2.getDouble("FOB_AMT"));
                            TOTINR2=roundTwoDecimals(TOTINR2+result2.getDouble("FOB_INR"));
                            String grdecl=""; String disc="";
                            stat1=conn.prepareStatement("select sum(gr_decl_amt) grdecl,sum(discount_amt) disc from ei_endors_dtls where all_no=? ");
                            stat1.setString(1,result2.getString("all_no"));
                            result1=stat1.executeQuery();
                            if (result1.next())
                            {
                              TOTGR2=roundTwoDecimals(TOTGR2+result1.getDouble("grdecl"));
                              TOTDISC2=roundTwoDecimals(TOTDISC2+result1.getDouble("disc"));
                              grdecl=result1.getString("grdecl");
                              disc=result1.getString("disc");
                            }
                             ShowDetail2.add(new FinTermBean(result2.getString("awb_no"),result2.getString("awb_date"),result2.getString("ac_send_date"),result2.getString("ac_send_term"),result2.getString("h_Awb_no"),result2.getString("ship_advice"),result2.getString("all_no"),result2.getString("buyer"),result2.getString("desti_cntry"),result2.getString("currency"),result2.getString("FOB_AMT"),result2.getString("FOB_INR"),grdecl,disc,result2.getString("SHIP_QNTY"),result2.getString("location"),result2.getString("lc_no"),result2.getString("year"),result2.getString("link_no")));
                             
                          }         
                         
                      } 
                   
                } // View Flag2 Close 
                
                
              
            }    
                  
             catch (Exception e) {
                System.out.println(e.toString());
            }
            finally{
                 if (conn != null) {
                        conn.close();
                    }
                     
            }
        
            }
        catch(Exception e){
                System.out.println(e.toString());
            }

        
        return "ssrch";
    
}


    public String firstsrch(){
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
               
                SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yyyy");
                String date1=null;
                
                if (SEARCH_DATE != null ) {
                   date1=  simpledateformate1.format(SEARCH_DATE);
                }

                if ((viewFlag != null && viewFlag.equals("Yes"))) 
                {  
                    
                    String q1 = " ";
                         if (SEARCH_DATE != null) {               
                            q1 += " and trunc(a.doc_send_date)='"+ date1+"'";
                        }
                        if (TERM_NO != null && TERM_NO.length()> 0) {
                           q1 += " and a.ac_send_term="+TERM_NO;         
                        }
                 
                     if (SEARCH_DATE!=null || TERM_NO != null)
                      {     
                                  stat2 = conn.prepareStatement("select to_char(ac_send_date,'dd/mm/yyyy') ac_send_date,ac_send_term,awb_no,to_char(awb_date,'dd/mm/yyyy') awb_date,h_awb_no,ship_advice,all_no,ship_qnty,fob_amt,currency,fob_amt*inr_conv fob_inr,c.desti_cntry,c.location,c.buyer,b.lc_no,a.year,a.link_no from ei_shipment_mast a,ei_shipment_dtls b,ei_endors_mast c where  a.year=b.year and a.link_no=b.link_no and b.year=c.year and b.company=c.company and b.inv_no=c.inv_no "+q1+" order by 1,2,3");
                          //  stat2.setString(1,LOCATION_CODE);
                            result2 = stat2.executeQuery();
                         while (result2.next()) 
                          { TOTQTY=TOTQTY+roundTwoDecimals(result2.getDouble("ship_qnty"));
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
                               
                             ShowDetail.add(new FinTermBean2(result2.getString("awb_no"),result2.getString("awb_date"),result2.getString("ac_send_date"),result2.getString("ac_send_term"),result2.getString("h_Awb_no"),result2.getString("ship_advice"),result2.getString("all_no"),result2.getString("buyer"),result2.getString("desti_cntry"),result2.getString("currency"),result2.getString("FOB_AMT"),result2.getString("FOB_INR"),grdecl,disc,result2.getString("SHIP_QNTY"),result2.getString("location"),result2.getString("lc_no"),result2.getString("year"),result2.getString("link_no")));
                          
                          }         
                         
                      } 
                    
                } // View Flg Close      
                        
               
//              if (saveFlag.equals("YES"))
//              {   
//               if (newrec!=null && newrec.length>1)
//                for(int i=0; i<newrec.length; i++)
//                   {
//                      if(newrec[i].length()!=0)
//                       {
//                          int in1= newrec[i].indexOf("$");
//                          String va1=newrec[i].substring(0,in1);
//                          String va2=newrec[i].substring(in1+1);
//                           System.out.println("va1 "+va1+" va2 "+va2);
//                       }
//                   }
//                
//              }
                       
            }         
                  
             catch (Exception e) {
                System.out.println(e.toString());
            }
            finally{
                 if (conn != null) {
                        conn.close();
                    }
                    
            }
        
    }catch(Exception e){
        System.out.println();
    }
        
        
        return "fsrch";

    }
     
    public String update1(){
        
        
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
                
               int p=0;
                //if(saveFlag.equals("Yes")){
               int NEWTERM=0;
                if (SEL_CHECK != null && SEL_CHECK.size() > 0 && NewFlag!=null && NewFlag.length()>0) 
                {
                               SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yyyy");
                               SimpleDateFormat simpledateformate2 = new SimpleDateFormat("yyyyMMdd");
                                    String dat1=null; String yr=null; String mm=null; String dd=null;
                                    String dat2=null;
                                    if (MDATE1 != null ) {
                                       dat1=  simpledateformate1.format(MDATE1);
                                       dat2=  simpledateformate2.format(MDATE1);
                                    }
                                    yr=dat2.substring(0,4);
                                    mm=dat2.substring(4,6);
                                    dd=dat2.substring(6,8);
                                 
                                  
                                  stat=conn.prepareStatement("select to_number(vou_numb)+1 newterm from pr_vou_numb_mast where location_code = 'DOCF' and FIN_YEAR=? and VOU_TYPE=? and SUB_TYPE=? ");
                                  stat.setString(1,yr);
                                  stat.setString(2,mm);
                                  stat.setString(3,dd);
                                  result=stat.executeQuery();
                                  if (result.next())
                                  {
                                     NEWTERM=result.getInt("newterm");
                                      stat1=conn.prepareStatement("update pr_vou_numb_mast  set vou_numb =? where location_code = 'DOCF' and FIN_YEAR =?  and VOU_TYPE =? and  SUB_TYPE=? " )  ; 
                                     stat1.setInt(1,NEWTERM);
                                     stat1.setString(2,yr);
                                     stat1.setString(3,mm);
                                     stat1.setString(4,dd);
                                     stat1.executeUpdate();
                                     
                                  }else{
                                       stat1=conn.prepareStatement("select  max(ac_send_term)+1 newterm from ei_shipment_mast where ac_send_date=?");
                                       stat1.setString(1, dat1);
                                       result1=stat1.executeQuery();
                                       if (result1.next())
                                       {
                                        NEWTERM=result1.getInt("newterm");
                                       }
                                   
                                  }
                    for (int i = 0; i < SEL_CHECK.size(); i++) {
                        String a = null;
                            a = SEL_CHECK.get(i).toString();
                            String[] ar=a.split("-");
                            

                                 
                               if(ar!=null && ar.length>1 && dat1!=null && dat1.length()>0){
                                
                                stat = conn.prepareStatement("UPDATE ei_shipment_mast set ac_send_term=? where YEAR=? and LINK_NO=?");
                                stat.setInt(1, NEWTERM);
                                stat.setString(2, ar[0]);
                                stat.setString(3, ar[1]);
                               int x= stat.executeUpdate();
                               if(x>0){
                                   p=x;
                               }
                            }
                    }
                //}
                if(p>0){
                    addActionError("Record Updated New Term No : "+NEWTERM);
                    
                }
             }
                if (SEL_CHECK != null && SEL_CHECK.size() > 0 && MergeFlag!=null && MergeFlag.length()>0) 
                {

                    for (int i = 0; i < SEL_CHECK.size(); i++) {
                        String a = null;
                            a = SEL_CHECK.get(i).toString();
                            String[] ar=a.split("-");
                            
                               SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yyyy");
                               SimpleDateFormat simpledateformate2 = new SimpleDateFormat("yyyyMMdd");
                                    String dat1=null;  
                                    String dat2=null;
                                    if (MDATE2 != null ) {
                                       dat1=  simpledateformate1.format(MDATE1);
                                       dat2=  simpledateformate1.format(MDATE2);
                                    }
                                            
                               if(ar!=null && ar.length>1 && dat1!=null && dat1.length()>0){
                                
                                stat = conn.prepareStatement("update ei_shipment_mast set doc_send_date=?,ac_send_term=? where YEAR=? and LINK_NO=?");
                                stat.setString(1, dat2);
                                stat.setString(2, MTERM2);
                                stat.setString(3, ar[0]);
                                stat.setString(4, ar[1]);
                               int x= stat.executeUpdate();
                               if(x>0){
                                   p=x;
                               }
                            }
                    }
                //}
                if(p>0){
                    addActionError("Record Updated");
                    
                }
             }          
            }         
                  
             catch (Exception e) {
                System.out.println(e.toString());
            }
            finally{
                 if (conn != null) {
                        conn.close();
                    }
                     
            }
        
    }catch(Exception e){
        System.out.println();
    }
        
        
        
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

    
    public Double getTOTINR() {
        return TOTINR;
    }

    public void setTOTINR(Double TOTINR) {
        this.TOTINR = TOTINR;
    }

    public String getViewFlag2() {
        return viewFlag2;
    }

    public void setViewFlag2(String viewFlag2) {
        this.viewFlag2 = viewFlag2;
    }

    
    public String getSearchdate2() {
        return searchdate2;
    }

    public void setSearchdate2(String searchdate2) {
        this.searchdate2 = searchdate2;
    }

    public String getSearchterm2() {
        return searchterm2;
    }

    public void setSearchterm2(String searchterm2) {
        this.searchterm2 = searchterm2;
    }

    public Double getTOTQTY2() {
        return TOTQTY2;
    }

    public void setTOTQTY2(Double TOTQTY2) {
        this.TOTQTY2 = TOTQTY2;
    }

    public Double getTOTFOB2() {
        return TOTFOB2;
    }

    public void setTOTFOB2(Double TOTFOB2) {
        this.TOTFOB2 = TOTFOB2;
    }

    public Double getTOTINR2() {
        return TOTINR2;
    }

    public void setTOTINR2(Double TOTINR2) {
        this.TOTINR2 = TOTINR2;
    }

    public Double getTOTGR2() {
        return TOTGR2;
    }

    public void setTOTGR2(Double TOTGR2) {
        this.TOTGR2 = TOTGR2;
    }

    public Double getTOTDISC2() {
        return TOTDISC2;
    }

    public void setTOTDISC2(Double TOTDISC2) {
        this.TOTDISC2 = TOTDISC2;
    }

    public List getShowDetail2() {
        return ShowDetail2;
    }

    public void setShowDetail2(List ShowDetail2) {
        this.ShowDetail2 = ShowDetail2;
    }

    public String[] getNewrec() {
        return newrec;
    }

    public void setNewrec(String[] newrec) {
        this.newrec = newrec;
    }

    public String getTERM_NO() {
        return TERM_NO;
    }

    public void setTERM_NO(String TERM_NO) {
        this.TERM_NO = TERM_NO;
    }

    public Date getSEARCH_DATE() {
        return SEARCH_DATE;
    }

    public void setSEARCH_DATE(Date SEARCH_DATE) {
        this.SEARCH_DATE = SEARCH_DATE;
    }

    public String getTERM_NO2() {
        return TERM_NO2;
    }

    public void setTERM_NO2(String TERM_NO2) {
        this.TERM_NO2 = TERM_NO2;
    }

    public Date getSEARCH_DATE2() {
        return SEARCH_DATE2;
    }

    public void setSEARCH_DATE2(Date SEARCH_DATE2) {
        this.SEARCH_DATE2 = SEARCH_DATE2;
    }

    

   

    public List getSEL_CHECK() {
        return SEL_CHECK;
    }

    public void setSEL_CHECK(List SEL_CHECK) {
        this.SEL_CHECK = SEL_CHECK;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLinkno() {
        return linkno;
    }

    public void setLinkno(String linkno) {
        this.linkno = linkno;
    }

    public Date getMDATE1() {
        return MDATE1;
    }

    public void setMDATE1(Date MDATE1) {
        this.MDATE1 = MDATE1;
    }

    public String getNewFlag() {
        return NewFlag;
    }

    public void setNewFlag(String NewFlag) {
        this.NewFlag = NewFlag;
    }

    public String getMergeFlag() {
        return MergeFlag;
    }

    public void setMergeFlag(String MergeFlag) {
        this.MergeFlag = MergeFlag;
    }

    public Date getMDATE2() {
        return MDATE2;
    }

    public void setMDATE2(Date MDATE2) {
        this.MDATE2 = MDATE2;
    }

    public String getMTERM2() {
        return MTERM2;
    }

    public void setMTERM2(String MTERM2) {
        this.MTERM2 = MTERM2;
    }

    

     

   
  
     
    
    
}
 