package shahi.Action.MvxExp.GVTINC;

  
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
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.POST.Beans.FittMastBean;
import shahi.Action.MvxExp.GVTINC.Beans.DBKREFUNDBEAN;

  
 
public class DBKREFUNDAction extends ActionSupport {
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
   
    private String SEARCH_CODE;
    private String FITT_DATE;
    private String FITT_NO;
    private String REFUND_AMT;
    private String CALC_PER;
    private String CALC_DATE;
    private String CRNCY_CODE1;
    private List BILL_NO;
    private List BILL_DATE;
    private List  CHALLAN_NO;
    private List CHALLAN_DATE;
    private List BANK;
    private List AMT;
    private String indexname;
    private List ShowDetail=new ArrayList();
    private List INVLST=new ArrayList();
    private List SBSearch= new ArrayList();
    private Double TOTDBKDUE=0.0;
    private Double TOTDBKRECV=0.0;
    private Double TOTSTRDUE=0.0;
    private Double TOTSTRRECV=0.0;
    private Double TOTAMT=0.0;
    
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
                            q1 += " and B.SB_NO = '"+ SEARCHINV + "'";
                        }

                 
                        if(viewFlag!=null &&  viewFlag.length()>0)
                        {
                               stat2 = conn.prepareStatement("select distinct to_char(FITT_date,'dd-Mon-yyyy') FITT_date,a.FITT_NO,refund_amt,to_char(calc_date,'dd-Mon-yyyy') calc_date,calc_int from "
                                                            + " ei_dbk_refund_mast a,ei_dbk_refund_dtls b where a.fitt_no=b.fitt_no  "+q1+" order by 1,2,3");
                                result2 = stat2.executeQuery();
                                while (result2.next()) 
                                   {  
                                       ShowDetail.add(new FittMastBean(result2.getString("FITT_NO"),result2.getString("FITT_DATE"),result2.getString("refund_amt"),result2.getString("calc_date"),result2.getString("calc_int"),"",""));
                                   } 
                                result2.close();
                                stat2.close();
                       } 
                       

                      
                     
                  } 
          catch(Exception e){
              System.out.println(e.toString());
          }
        finally{
         try {   
             conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(DBKREFUNDAction.class.getName()).log(Level.SEVERE, null, ex);
         }
             }
        
        return SUCCESS;
 }   
       public String save() throws SQLException{
        
                  Connection conn = null;
                 PreparedStatement stat2=null;
                 ResultSet result2=null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
            
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
        
            
             PreparedStatement stat1 = null;
       
             ResultSet result1 = null;
      
      
            try{
                
                       if(saveFlag!=null &&  saveFlag.length()>0 )
                        {
                                        stat1=conn.prepareStatement(" SELECT * from ei_dbk_refund_mast  where  fitt_no=?");
                                        stat1.setString(1,FITT_NO.toUpperCase().trim());
                                        result1 = stat1.executeQuery();
                                        if (result1.next())
                                        { addActionMessage("Fitt already Exist... !!!"+FITT_NO);
                                          return  ERROR;
                                         } else{
                                        
                                             stat1=conn.prepareStatement("insert into ei_dbk_refund_mast (fitt_no,fitt_date,REFUND_AMT,CALC_DATE,CALC_INT,LOCT_CODE,SEH_USER,TDATE) values (?,?,?,?,?,?,?,sysdate)");
                                             stat1.setString(1,FITT_NO.toUpperCase().trim());
                                             stat1.setString(2,FITT_DATE);
                                             stat1.setString(3,REFUND_AMT);
                                             stat1.setString(4,CALC_DATE);
                                             stat1.setString(5,CALC_PER);
                                             stat1.setString(6,LOCATION_CODE);
                                             stat1.setString(7,usrid);
                                             stat1.executeUpdate();
                                        } 
                            
                                     String vsbno=""; String vsbdate="";
                                        if(BILL_NO!=null && BILL_NO.size()>0)
                                        {
                                           for(int i=0;i<BILL_NO.size();i++)
                                         {
                                             
                                         vsbno=BILL_NO.get(i).toString().trim();
                                         vsbdate=BILL_DATE.get(i).toString();
                           
                                        if(vsbno!=null && vsbno.length()>4)
                                         { 
                                            stat2=conn.prepareStatement(" SELECT * from EI_DBK_REFUND_DTLS  where  SB_NO=? AND SB_DATE=? ");
                                            stat2.setString(1,vsbno);
                                            stat2.setString(2,vsbdate);
                                            result2 = stat2.executeQuery();
                                            if (result2.next())
                                            {  
                                                addActionMessage("S/B already Exist... !!!"+BILL_NO.get(i).toString()+"  Fitt No "+result2.getString("fitt_no"));
                                                return  ERROR;
                                            }else
                                            {       
                                                       
                                                         stat2=conn.prepareStatement(" insert into EI_DBK_REFUND_DTLS (fitt_no,sb_no,sb_date,amt,CHALLAN_NO,CHALLAN_date,bank_code,seh_user,tdate) values (?,?,?,?,upper(?),?,upper(?),?,sysdate)");
                                                         stat2.setString(1,FITT_NO.toUpperCase());
                                                         stat2.setString(2,vsbno);
                                                         stat2.setString(3,vsbdate);
                                                         stat2.setString(4,AMT.get(i).toString());
                                                         stat2.setString(5,CHALLAN_NO.get(i).toString());
                                                         stat2.setString(6,CHALLAN_DATE.get(i).toString());
                                                         stat2.setString(7,BANK.get(i).toString());
                                                         stat2.setString(8,usrid);
                                                         stat2.executeUpdate();
                                                         flag=1;

                                                    

                                                }
                                             }
                                           }
                                       }
                        }
                  System.out.println("updFlag  "+updFlag);
                
                        if(updFlag!=null &&  updFlag.length()>0 )
                        { 
                                       
                            
                                             stat1=conn.prepareStatement("update ei_dbk_refund_mast set fitt_date=?,refund_amt=?,CALC_DATE=?,CALC_INT=? where fitt_no=?");
                                       System.out.println("fittdate "+FITT_DATE);      
                                             stat1.setString(1,FITT_DATE);
                                             stat1.setString(2,REFUND_AMT);
                                             stat1.setString(3,CALC_DATE);
                                             stat1.setString(4,CALC_PER);
                                             stat1.setString(5,UPDFITT.trim());
                                             stat1.executeUpdate();
                                             
                                             stat1=conn.prepareStatement("delete from ei_Dbk_refund_dtls where fitt_no=?");
                                             stat1.setString(1,UPDFITT.trim());
                                             stat1.executeUpdate();
                                             
                                             
                                     String vsbno=""; String vsbdate="";  String vchdate="";
                                        if(BILL_NO!=null && BILL_NO.size()>0)
                                        {
                                           for(int i=0;i<BILL_NO.size();i++)
                                         {
                                             
                                         vsbno=BILL_NO.get(i).toString().trim();
                                         vsbdate=BILL_DATE.get(i).toString();
                                         vchdate=CHALLAN_DATE.get(i).toString();
                                        if(vsbno!=null && vsbno.length()>4)
                                         { 
                                            stat2=conn.prepareStatement(" SELECT * from EI_DBK_REFUND_DTLS  where  SB_NO=? AND SB_DATE=? ");
                                            stat2.setString(1,vsbno);
                                       System.out.println("sbdate "+vsbdate);     
                                            stat2.setString(2,vsbdate);
                                            result2 = stat2.executeQuery();
                                            if (result2.next())
                                            {  
                                                addActionMessage("S/B already Exist... !!!"+BILL_NO.get(i).toString()+"  Fitt No "+result2.getString("fitt_no"));
                                                return  ERROR;
                                            }else
                                            {        
                                                       
                                                         stat2=conn.prepareStatement("insert into EI_DBK_REFUND_DTLS (fitt_no,sb_no,sb_date,amt,CHALLAN_NO,CHALLAN_date,bank_code,seh_user,tdate) values (?,?,?,?,upper(?),?,upper(?),?,sysdate)");
                                                         stat2.setString(1,UPDFITT.toUpperCase());
                                                         stat2.setString(2,vsbno);
                                                         stat2.setString(3,vsbdate);
                                                         stat2.setString(4,AMT.get(i).toString());
                                                         stat2.setString(5,CHALLAN_NO.get(i).toString());
                                                  System.out.println("challadate "+CHALLAN_DATE);       
                                                         stat2.setString(6,vchdate);
                                                         stat2.setString(7,BANK.get(i).toString());
                                                         stat2.setString(8,usrid);
                                                         stat2.executeUpdate();
                                                         flag=1;

                                                    

                                                }
                                             }
                                           }
                                       }
                            
                                }
                  
                         if (flag == 1) {   
                              conn.commit(); 
                               FITT_NO=null;FITT_DATE=null;REFUND_AMT=null;CALC_DATE=null;CALC_PER=null;
                               BILL_NO=null;BILL_DATE=null;CHALLAN_NO=null;CHALLAN_DATE=null;BANK=null;
                               AMT=null;
                               
                                addActionMessage("Records Save(s) !!");
                                return "save";
                           }
                                else {
                                FITT_NO=null;FITT_DATE=null;REFUND_AMT=null;CALC_DATE=null;CALC_PER=null;
                                 BILL_NO=null;BILL_DATE=null;CHALLAN_NO=null;CHALLAN_DATE=null;BANK=null;
                                 AMT=null;
                           
                             return "save";
                        }  
                        
            }catch(Exception e){
                System.out.println(e.toString());
            }
            finally {
            if (conn != null) {
                conn.close();
            }
        }
        if (stat1 != null) {
            stat1.close();
        }
        
        return "save";
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
                
                stat2=conn.prepareStatement("select to_char(fitt_date,'dd-Mon-yyyy') fitt_date,REFUND_AMT,CALC_INT,to_char(calc_date,'dd-Mon-yyyy') calc_date from ei_dbk_refund_mast where fitt_no=?");
                stat2.setString(1,UPDFITT);
                result2=stat2.executeQuery();
                while(result2.next()){
                     FITT_DATE=result2.getString("fitt_date");         
                     REFUND_AMT=result2.getString("REFUND_AMT");
                     CALC_DATE=result2.getString("calc_date");
                     CALC_PER=result2.getString("calc_int");
                     
                }
                result2.close();
                stat2.close();
               
                stat2=conn.prepareStatement("select sb_no,to_char(sb_date,'dd-Mon-yyyy') sb_date,amt,dbk_admisable,dbk_Received,str_due,str_recv,CLAIM_PORT,  "+
                                          " CHALLAN_NO,TO_CHAR(CHALLAN_DATE,'DD-Mon-YYYY') CHALLAN_DATE,BANK_CODE from  ei_dbk_mast a,ei_dbk_refund_dtls c where a.shp_bill_no=c.sb_no and a.shp_bill_date=c.sb_date  and c.fitt_no=? ");
                 stat2.setString(1,UPDFITT);
                result2=stat2.executeQuery();
                while(result2.next()){
                
                    
                   TOTDBKDUE=TOTDBKDUE+result2.getDouble("dbk_admisable");
                   TOTDBKRECV=TOTDBKRECV+result2.getDouble("dbk_Received");
                   TOTSTRDUE=TOTSTRDUE+result2.getDouble("STR_DUE");
                   TOTSTRRECV=TOTSTRRECV+result2.getDouble("STR_RECV");
                   TOTAMT=TOTAMT+result2.getDouble("AMT");
                   
                                 INVLST.add(new DBKREFUNDBEAN(result2.getString("sb_no"),result2.getString("sb_date"),result2.getString("CLAIM_PORT"),result2.getString("dbk_admisable"),result2.getString("dbk_Received"),result2.getString("str_due"),result2.getString("str_recv"),
                                                            result2.getString("CHALLAN_NO"),result2.getString("CHALLAN_DATE"),result2.getString("BANK_CODE"),result2.getString("AMT")));
                            
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
                        
                   
                        
                      }catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBKREFUNDAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        return "new";
    } 
     
    
      public String SBview() throws SQLException {
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
                       
                        stat = conn.prepareStatement(" select shp_bill_no,to_char(shp_bill_date,'dd-Mon-yyyy') sb_date,CLAIM_PORT,nvl(DBK_ADMISABLE,0) dbk_admisable,nvl(DBK_RECEIVED,0) dbk_received,nvl(STR_DUE,0) str_due,nvl(STR_RECV,0) str_recv  from ei_dbk_mast where  shp_bill_no like ?  ");
                        stat.setString(1,SEARCH_CODE.toUpperCase() + "%");
                        result = stat.executeQuery();
                        while (result.next()) {
                           
                            SBSearch.add(new DBKREFUNDBEAN(result.getString("shp_bill_no"),result.getString("sb_date"),result.getString("CLAIM_PORT"),result.getString("dbk_admisable"),result.getString("dbk_received"),result.getString("str_due"),result.getString("str_recv"),"","","",""));
                             
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

        return "SBview";
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

    public List getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(List BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    public List getBILL_DATE() {
        return BILL_DATE;
    }

    public void setBILL_DATE(List BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
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

    public Double getTOTDBKDUE() {
        return TOTDBKDUE;
    }

    public void setTOTDBKDUE(Double TOTDBKDUE) {
        this.TOTDBKDUE = TOTDBKDUE;
    }

    public Double getTOTDBKRECV() {
        return TOTDBKRECV;
    }

    public void setTOTDBKRECV(Double TOTDBKRECV) {
        this.TOTDBKRECV = TOTDBKRECV;
    }

    public Double getTOTSTRDUE() {
        return TOTSTRDUE;
    }

    public void setTOTSTRDUE(Double TOTSTRDUE) {
        this.TOTSTRDUE = TOTSTRDUE;
    }

    public Double getTOTSTRRECV() {
        return TOTSTRRECV;
    }

    public void setTOTSTRRECV(Double TOTSTRRECV) {
        this.TOTSTRRECV = TOTSTRRECV;
    }

   

    public String getREFUND_AMT() {
        return REFUND_AMT;
    }

    public void setREFUND_AMT(String REFUND_AMT) {
        this.REFUND_AMT = REFUND_AMT;
    }

    public String getCALC_PER() {
        return CALC_PER;
    }

    public void setCALC_PER(String CALC_PER) {
        this.CALC_PER = CALC_PER;
    }

    public String getCALC_DATE() {
        return CALC_DATE;
    }

    public void setCALC_DATE(String CALC_DATE) {
        this.CALC_DATE = CALC_DATE;
    }

    

    public String getCRNCY_CODE1() {
        return CRNCY_CODE1;
    }

    public void setCRNCY_CODE1(String CRNCY_CODE1) {
        this.CRNCY_CODE1 = CRNCY_CODE1;
    }

    public Double getTOTAMT() {
        return TOTAMT;
    }

    public void setTOTAMT(Double TOTAMT) {
        this.TOTAMT = TOTAMT;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }

    public List getSBSearch() {
        return SBSearch;
    }

    public void setSBSearch(List SBSearch) {
        this.SBSearch = SBSearch;
    }

    public List getCHALLAN_NO() {
        return CHALLAN_NO;
    }

    public void setCHALLAN_NO(List CHALLAN_NO) {
        this.CHALLAN_NO = CHALLAN_NO;
    }

    public List getCHALLAN_DATE() {
        return CHALLAN_DATE;
    }

    public void setCHALLAN_DATE(List CHALLAN_DATE) {
        this.CHALLAN_DATE = CHALLAN_DATE;
    }

    public List getBANK() {
        return BANK;
    }

    public void setBANK(List BANK) {
        this.BANK = BANK;
    }

    public List getAMT() {
        return AMT;
    }

    public void setAMT(List AMT) {
        this.AMT = AMT;
    }

   

        
  
       
}
 