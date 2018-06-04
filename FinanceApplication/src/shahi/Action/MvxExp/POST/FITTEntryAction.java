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
import shahi.Action.MvxExp.Admin.EisUtil;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import shahi.Action.HelperConstantsFnl;
import shahi.Action.MvxExp.POST.Beans.FittMastBean;
import shahi.Action.MvxExp.POST.Beans.FittInvBean;

  

public class FITTEntryAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
    private String saveFlag;
    private String updFlag;
    private List showList;
    private String SEARCHFITT;
    private String SEARCHDATE;
    private String SEARCHINV;
    private String UPDFITT;
    private String aausrid;
    private java.util.Date MIN_DATE;
   
    private List CURLIST=new ArrayList();
    private String FITT_DATE;
    private String FITT_NO;
    private String FOB_AMT;
    private String ADV_AMT;
    private String REM1;
    private String BUYER;
    private String CRNCY_CODE;
    private String BUYER1;
    private String CRNCY_CODE1;
    private List EXCS_INV_NO;
    
    private List ShowDetail=new ArrayList();
    private List INVLST=new ArrayList();
   
    private Double TOTQTY=0.0;
    private Double TOTFOB=0.0;
    private Double TOTNET=0.0;
    private Double TOTGR=0.0;
 
    
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
            
             SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
             this.MIN_DATE = new java.util.Date();
            
             String date1=null;
             
            
             try{
                    String q1 = " ";
                        if (SEARCHFITT != null && SEARCHFITT.length()> 0) {
                            q1 += " and a.FITT_NO ='"+SEARCHFITT.toUpperCase()+"'";             
                        }
                        
                        if (SEARCHDATE != null && SEARCHDATE.length()> 0) {               
                            q1 += " and a.FITT_DATE='"+ SEARCHDATE + "'";
                        }
                        if (SEARCHINV != null && SEARCHINV.length()>0) {
                            q1 += " and B.EXCS_INV_NO = '"+ SEARCHINV + "'";
                        }

                 
                        if(viewFlag!=null &&  viewFlag.length()>0)
                        {
                               stat2 = conn.prepareStatement("select distinct to_char(FITT_date,'dd/mm/yyyy') FITT_date,a.FITT_NO,CRNCY_CODE,FOB_AMT,BUYER,REM1,ADV_AMT from "
                                                            + " ei_fitt_mast a,ei_fitt_dtls b where a.fitt_no=b.fitt_no  "+q1+" order by 1,2,3");
                                result2 = stat2.executeQuery();
                                while (result2.next()) 
                                   {  
                                       ShowDetail.add(new FittMastBean(result2.getString("FITT_NO"),result2.getString("FITT_DATE"),result2.getString("BUYER"),result2.getString("CRNCY_CODE"),result2.getString("FOB_AMT"),result2.getString("ADV_AMT"),result2.getString("REM1")));
                                                
                                    } 
                                result2.close();
                                stat2.close();
                       }
                      
                        if(saveFlag!=null &&  saveFlag.length()>0 )
                        {
                                        stat1=conn.prepareStatement(" SELECT * from ei_fitt_mast  where  fitt_no=?");
                                        stat1.setString(1,FITT_NO);
                                        result1 = stat1.executeQuery();
                                        if (result1.next())
                                        { addActionMessage("Fitt already Exist... !!!"+FITT_NO);
                                          return  ERROR;
                                         } else{
                                        
                                             stat1=conn.prepareStatement("insert into ei_fitt_mast (fitt_no,fitt_date,buyer,loct_code,crncy_code,fob_amt,adv_amt,rem1,seh_user,tdate) values (?,?,upper(?),?,?,?,?,upper(?),?,sysdate)");
                                             stat1.setString(1,FITT_NO.toUpperCase());
                                             stat1.setString(2,FITT_DATE);
                                             stat1.setString(3,BUYER);
                                             stat1.setString(4,LOCATION_CODE);
                                             stat1.setString(5,CRNCY_CODE);
                                             stat1.setString(6,FOB_AMT);
                                             stat1.setString(7,ADV_AMT);
                                             stat1.setString(8,REM1);
                                             stat1.setString(9,usrid);
                                             stat1.executeUpdate();
                                        } 
                            
                                     String vex="";
                                        if(EXCS_INV_NO!=null && EXCS_INV_NO.size()>0)
                                        {
                                           for(int i=0;i<EXCS_INV_NO.size();i++)
                                         {
                                             
                                         vex=EXCS_INV_NO.get(i).toString().trim();
                                        if(vex!=null && vex.length()>7)
                                         { 
                                            stat2=conn.prepareStatement(" SELECT * from EI_FITT_DTLS  where  excs_inv_no=?");
                                            stat2.setString(1,EXCS_INV_NO.get(i).toString());
                                            result2 = stat2.executeQuery();
                                            if (result2.next())
                                            {  
                                                addActionMessage("Invoice already Exist... !!!"+EXCS_INV_NO.get(i).toString()+"  Fitt No "+result2.getString("fitt_no"));
                                                return  ERROR;
                                            }else
                                            {       
                                                    stat1=conn.prepareStatement(" SELECT year,company,inv_no from ei_endors_mast  where  excs_inv_no=?");
                                                    stat1.setString(1,EXCS_INV_NO.get(i).toString());
                                                    result1 = stat1.executeQuery();
                                                    if (result1.next())
                                                    {   
                                                         stat2=conn.prepareStatement(" insert into ei_fitt_dtls (year,company,inv_no,excs_inv_no,fitt_no,seh_user,tdate) values (?,?,?,?,?,?,sysdate)");
                                                         stat2.setString(1,result1.getString("year"));
                                                         stat2.setString(2,result1.getString("company"));
                                                         stat2.setString(3,result1.getString("inv_no"));
                                                         stat2.setString(4,vex );
                                                         stat2.setString(5,FITT_NO.toUpperCase());
                                                         stat2.setString(6,usrid);
                                                         stat2.executeUpdate();
                                                         flag=1;

                                                    }

                                                }
                                             }
                                           }
                                       }
                        }
                        if(updFlag!=null &&  updFlag.length()>0 )
                        {
                                        String vex="";
                                        if(EXCS_INV_NO!=null && EXCS_INV_NO.size()>0)
                                        {
                                           for(int i=0;i<EXCS_INV_NO.size();i++)
                                         {
                                             
                                         vex=EXCS_INV_NO.get(i).toString().trim();
                                       
                                        if(vex!=null && vex.length()>7)
                                         { 
                                            stat2=conn.prepareStatement(" SELECT * from EI_FITT_DTLS  where  excs_inv_no=? and fitt_no<>?");
                                            stat2.setString(1,vex);
                                            stat2.setString(2,UPDFITT.trim());
                                            result2 = stat2.executeQuery();
                                            if (result2.next())
                                            {  
                                                addActionMessage("Invoice already Exist... !!!"+EXCS_INV_NO.get(i).toString()+" Fitt No "+result2.getString("fitt_no"));
                                                return  ERROR;
                                            }else
                                            {       
                                                    stat1=conn.prepareStatement(" select * from ei_fitt_DTLS where excs_inv_no=? and fitt_no=?");
                                                    stat1.setString(1,vex);
                                                    stat1.setString(2,UPDFITT.trim());
                                                    result1 = stat1.executeQuery();
                                                    if (result1.next())
                                                    {  
                                                        System.out.println("already exists "+vex);
                                                    }else
                                                    {
                                                        stat1=conn.prepareStatement(" SELECT year,company,inv_no from ei_endors_mast  where  excs_inv_no=?");
                                                        stat1.setString(1,EXCS_INV_NO.get(i).toString());
                                                        result1 = stat1.executeQuery();
                                                        if (result1.next())
                                                        {   
                                                             stat2=conn.prepareStatement(" insert into ei_fitt_dtls (year,company,inv_no,excs_inv_no,fitt_no,seh_user,tdate) values (?,?,?,?,?,?,sysdate)");
                                                             stat2.setString(1,result1.getString("year"));
                                                             stat2.setString(2,result1.getString("company"));
                                                             stat2.setString(3,result1.getString("inv_no"));
                                                             stat2.setString(4,vex );
                                                             stat2.setString(5,FITT_NO.toUpperCase());
                                                             stat2.setString(6,usrid);
                                                             stat2.executeUpdate();
                                                             flag=1;

                                                          }
                                                    }
                                                }
                                             }
                                           }
                                       }
                            
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
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
            
            
            
             PreparedStatement stat = null;
       
             ResultSet result = null;
      
      
            try{
                
                stat2=conn.prepareStatement("select to_char(fitt_date,'dd-Mon-yy') fitt_date,fob_amt,buyer,rem1,adv_amt,crncy_code from ei_fitt_mast where fitt_no=?");
                stat2.setString(1,UPDFITT);
                result2=stat2.executeQuery();
                while(result2.next()){
                     FITT_DATE=result2.getString("fitt_date");         
                     FOB_AMT=result2.getString("fob_amt");
                     ADV_AMT=result2.getString("adv_amt");
                     REM1=result2.getString("rem1");
                     BUYER1=result2.getString("buyer");
                     CRNCY_CODE1=result2.getString("crncy_code");
                }
                result2.close();
                stat2.close();
               
                stat2=conn.prepareStatement("select a.excs_inv_no,a.buyer,a.cost_centre,to_char(a.inv_date,'dd-Mon-yyyy') inv_date,to_char(a.t_o_date,'dd-Mon-yyyy') t_o_date,to_char(a.etd_date,'dd-Mon-yyyy') etd_date,to_char(a.doc_send,'dd-Mon-yyyy') doc_send,to_char(a.awbdate,'dd-Mon-yyyy') awbdate,to_char(a.fin_date,'dd-Mon-yyyy') fin_date,a.crncy_code,sum(b.qty_endors) inv_qty,sum(inv_qty*price_fc+nvl(price_misc,0)) fob_amt,sum(gr_decl_amt) gr_decl,sum(inv_qty*price_fc+nvl(price_misc,0))-sum(gr_decl_amt) NET_FOB "+
                                          " from ei_endors_mast a,ei_endors_dtls b ,ei_fitt_dtls c where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.excs_inv_no=c.excs_inv_no and c.fitt_no=? "+
                                          " group by a.excs_inv_no,a.buyer,a.cost_centre,a.inv_date,a.t_o_date,a.etd_date,a.doc_send,a.awbdate,a.fin_date,a.crncy_code order by 1");
                 stat2.setString(1,UPDFITT);
                result2=stat2.executeQuery();
                while(result2.next()){
                
                    
                   TOTFOB=TOTFOB+result2.getDouble("fob_amt");
                   TOTGR=TOTGR+result2.getDouble("gr_decl");
                   TOTQTY=TOTQTY+result2.getDouble("inv_qty");
                   TOTNET=TOTNET+result2.getDouble("NET_FOB");
                                 INVLST.add(new FittInvBean(result2.getString("excs_inv_no"),result2.getString("cost_centre"),result2.getString("buyer"),result2.getString("inv_date"),result2.getString("t_o_date"),result2.getString("etd_date"),result2.getString("doc_send"),
                                                            result2.getString("awbdate"),result2.getString("fin_date"),result2.getString("crncy_code"),result2.getString("inv_qty"),result2.getString("fob_amt"),result2.getString("gr_decl"),result2.getString("NET_FOB")));
                                 
                }
                  
            }catch(Exception e){
                System.out.println(e.toString());
            }
            finally {
            if (conn != null) {
                conn.close();
            }
        }
        if (stat != null) {
            stat.close();
        }
        
        return "edit";
    }
    
    public String newentry(){
        Connection conn = null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
            
            try{
                         SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
                        this.MIN_DATE = new java.util.Date();
                        
                   
                       stat2 = conn.prepareStatement("select currency from ei_currency_mast order by 1");
                       result2 = stat2.executeQuery();
                       while (result2.next()) 
                         {   
                             CURLIST.add(result2.getString("currency"));
                                
                          }   
                      }catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(FITTEntryAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        return "new";
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


    public List getINVLST() {
        return INVLST;
    }

    public void setINVLST(List INVLST) {
        this.INVLST = INVLST;
    }

    public String getSEARCHFITT() {
        return SEARCHFITT;
    }

    public void setSEARCHFITT(String SEARCHFITT) {
        this.SEARCHFITT = SEARCHFITT;
    }

    public String getSEARCHDATE() {
        return SEARCHDATE;
    }

    public void setSEARCHDATE(String SEARCHDATE) {
        this.SEARCHDATE = SEARCHDATE;
    }

    public String getSEARCHINV() {
        return SEARCHINV;
    }

    public void setSEARCHINV(String SEARCHINV) {
        this.SEARCHINV = SEARCHINV;
    }

    public String getUPDFITT() {
        return UPDFITT;
    }

    public void setUPDFITT(String UPDFITT) {
        this.UPDFITT = UPDFITT;
    }

    public String getFITT_DATE() {
        return FITT_DATE;
    }

    public void setFITT_DATE(String FITT_DATE) {
        this.FITT_DATE = FITT_DATE;
    }

    public String getFITT_NO() {
        return FITT_NO;
    }

    public void setFITT_NO(String FITT_NO) {
        this.FITT_NO = FITT_NO;
    }

    public String getFOB_AMT() {
        return FOB_AMT;
    }
 
    public void setFOB_AMT(String FOB_AMT) {
        this.FOB_AMT = FOB_AMT;
    }

    public String getADV_AMT() {
        return ADV_AMT;
    }

    public void setADV_AMT(String ADV_AMT) {
        this.ADV_AMT = ADV_AMT;
    }

    public String getREM1() {
        return REM1;
    }

    public void setREM1(String REM1) {
        this.REM1 = REM1;
    }

    public String getBUYER1() {
        return BUYER1;
    }

    public void setBUYER1(String BUYER1) {
        this.BUYER1 = BUYER1;
    }

    public String getCRNCY_CODE1() {
        return CRNCY_CODE1;
    }

    public void setCRNCY_CODE1(String CRNCY_CODE1) {
        this.CRNCY_CODE1 = CRNCY_CODE1;
    }

    public List getCURLIST() {
        return CURLIST;
    }

    public void setCURLIST(List CURLIST) {
        this.CURLIST = CURLIST;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public String getCRNCY_CODE() {
        return CRNCY_CODE;
    }

    public void setCRNCY_CODE(String CRNCY_CODE) {
        this.CRNCY_CODE = CRNCY_CODE;
    }

    public List getEXCS_INV_NO() {
        return EXCS_INV_NO;
    }

    public void setEXCS_INV_NO(List EXCS_INV_NO) {
        this.EXCS_INV_NO = EXCS_INV_NO;
    }

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
    }

  public java.util.Date getMIN_DATE()
  {
    return this.MIN_DATE;
  }
  
  public void setMIN_DATE(java.util.Date MIN_DATE)
  {
    this.MIN_DATE = MIN_DATE;
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

    public Double getTOTNET() {
        return TOTNET;
    }

    public void setTOTNET(Double TOTNET) {
        this.TOTNET = TOTNET;
    }

    public Double getTOTGR() {
        return TOTGR;
    }

    public void setTOTGR(Double TOTGR) {
        this.TOTGR = TOTGR;
    }

   

     
  
       
}
 