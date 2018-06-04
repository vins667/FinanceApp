package shahi.Action.MvxExp.GVTINC;

  
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.GVTINC.Beans.MEISAPPBEAN;
import shahi.Action.MvxExp.GVTINC.Beans.MeisSBBean;
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

 

import shahi.Action.database.connectiondb2;
  

public class MEISAPPAction extends ActionSupport
{
    private String MAXDATE;
    private String SEARCH_APP;
    private Date SEARCH_DATE;
    private String SEARCH_LIC;
    private String SEARCH_CODE;
    private String currentdate;
    private String viewFlag;
    private String delFlag;
    private List SHIP_DEL;
    private List showList;
    private String UPD_APP;
    private String aausrid;
    private String saveFlag;
    private String updFlag;
    private String indexname; 
    
    private String APP_NO;
    private String APP_DATE;
    private String APP_TYPE;
    private String LIC_NO;
    private String LIC_DATE;
    private String LIC_AMT;
    private String LOADING_PORT;
    private String FWD_PORT;
    private String FWD_AC;
    private Double TFOB=0.0;;
    private Double TINR=0.0;
    private Double TREL=0.0;
    private List BILL_NO;
    private List BILL_DATE;
    private List FOB_FC;
    private List FOB_INR;
    
    private List PORT_CODE;
    
    private List ShowDetail=new ArrayList();
    private List SBLIST = new ArrayList();
    private List SBSearch = new ArrayList();
 
    private ByteArrayInputStream inputStream;
   
 
    
    @Override
    public String execute() throws SQLException {
         
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
                 PreparedStatement stat2=null;
                 ResultSet result1=null;
                 ResultSet result2=null;
                 
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch  
             
             SimpleDateFormat simpledateformate1 = new SimpleDateFormat("dd-MMM-yy");
             String date1=null;
            if (SEARCH_DATE != null ) {
                
              date1=  simpledateformate1.format(SEARCH_DATE);
            }
            
             try{
                 
                    stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                    stat1.setString(1,usrid);
                    result1=stat1.executeQuery();
                    while (result1.next())
                    {LOCATION_CODE=result1.getString("location_code");
                    } 
                    
                    String q1 = " ";
                     int x=0; int y=0;
                     if(delFlag!=null && delFlag.equals("YES") && SHIP_DEL!=null && SHIP_DEL.size()>0)
                       {
                         for (int i = 0; i < SHIP_DEL.size(); i++){
                              String[] arr = SHIP_DEL.get(i).toString().split("--"); 
                                 
                              stat2=conn.prepareStatement("delete from ei_mlfs_dtls where app_no= ? ");
                              stat2.setString(1,arr[0].trim());
                              x=stat2.executeUpdate(); 
                           
                              stat1=conn.prepareStatement("delete from ei_mlfs_mast where app_no= ? ");
                              stat1.setString(1,arr[0].trim());
                              x=stat1.executeUpdate();
                              if(stat2!=null){stat2.close();} 
                              if(stat1!=null){stat1.close();} 
                           
                         }
                          if (x>0)
                          {
                           addActionMessage("Records Deleted...!");
                           return SUCCESS;
                          }
                         }
                     if(stat2!=null){stat2.close();}  
                    if (viewFlag!=null)
                    { 
                        if (SEARCH_APP != null && SEARCH_APP.length()> 0) {
                            q1 += " and APP_NO like '%"+SEARCH_APP.toUpperCase().trim()+"%'";             
                        }
                        if (SEARCH_DATE != null) {               
                            q1 += " and APP_DATE='"+ date1 + "'";
                        }
                        if (SEARCH_LIC != null && SEARCH_LIC.length()>0) {
                            q1 += " and LIC_NO = '"+ SEARCH_LIC.toUpperCase() + "'";
                        }

                         

                        
                   stat2 = conn.prepareStatement("select app_no,to_char(app_date,'dd/mm/yyyy') app_date,lic_no,to_char(lic_date,'dd/mm/yyyy') lic_date,lic_type,lic_amt,port_code,to_char(fwd_port,'dd/mm/yyyy') fwd_port,to_char(fwd_ac,'dd/mm/yyyy') fwd_ac,to_char(sale_date,'dd/mm/yyyy') sale_date,sale_amt     "
                           + " from ei_mlfs_mast where 1=1   "+q1+" order by 1,2,3");
                       
                   result2 = stat2.executeQuery();
                      while (result2.next())  
                         {    
                                
                             ShowDetail.add(new MEISAPPBEAN(result2.getString("app_no"),result2.getString("app_date"),result2.getString("port_code"),result2.getString("lic_type"),result2.getString("lic_no"),result2.getString("lic_date"),result2.getString("lic_amt"),result2.getString("fwd_port"),result2.getString("fwd_ac"),result2.getString("sale_amt"),
                                                                           result2.getString("sale_date")  ));
                                  
                          } 
                     
                    }
                 
                  if (updFlag!=null && updFlag.length()>0)
                  {
                       stat2=conn.prepareStatement("update ei_mlfs_mast set LIC_TYPE=?,lic_no=?,lic_amt=?,lic_date=to_date(?,'yyyy-mm-dd'),PORT_CODE=?,FWD_PORT=to_date(?,'dd/mm/yyyy'),FWD_AC=to_date(?,'dd/mm/yyyy'),seh_user=?,tdate=sysdate where app_no=? ");
                       stat2.setString(1,APP_TYPE.trim());
                       stat2.setString(2,LIC_NO);
                       stat2.setString(3,LIC_AMT);
                       if (LIC_DATE.length()>8)
                            {  stat2.setString(4,LIC_DATE.substring(0,10));}
                            else
                            {  stat2.setString(4,LIC_DATE);}
                        
                       stat2.setString(5,LOADING_PORT);
                       stat2.setString(6,FWD_PORT);
                       stat2.setString(7,FWD_AC);
                       stat2.setString(8,usrid);
                       stat2.setString(9,APP_NO.trim().toUpperCase());
                       stat2.executeUpdate();
                       
                       
                       
                        if(BILL_NO!=null )
                        {
                          for (int i = 0; i < BILL_NO.size(); i++) 
                         {
                     
                            if(BILL_NO.get(i)!=null && !BILL_NO.get(i).toString().isEmpty())
                                {   
                                    stat1=conn.prepareStatement("select * from ei_mlfs_dtls where shp_bill_no=? and shp_bill_date=? ");
                                    stat1.setString(1,BILL_NO.get(i).toString().toUpperCase());
                                    stat1.setString(2,BILL_DATE.get(i).toString().toUpperCase());
                                    result1=stat1.executeQuery();
                                    if (result1.next())
                                    {  }else
                                    
                                    {
                                        stat2=conn.prepareStatement("insert into ei_mlfs_dtls (app_no,shp_bill_no,shp_bill_date,port_code,fob_fc,fob_inr,seh_user,loct_code,tdate) values (?,?,?,?,?,?,?,?,sysdate)");
                                        stat2.setString(1,APP_NO.trim().toUpperCase());
                                        stat2.setString(2,BILL_NO.get(i).toString().toUpperCase());
                                        stat2.setString(3,BILL_DATE.get(i).toString().toUpperCase());
                                        stat2.setString(4,PORT_CODE.get(i).toString().toUpperCase());
                                        stat2.setString(5,FOB_FC.get(i).toString().toUpperCase());
                                        stat2.setString(6,FOB_INR.get(i).toString().toUpperCase());
                                        stat2.setString(7,usrid);
                                        stat2.setString(8,LOCATION_CODE);
                                        x=stat2.executeUpdate();
                                   }
                                }
                            }
                         }
                       
                       
                      
                  }
                   if (x>0)
                          { conn.commit();
                           addActionMessage("Records Updated...!");
                           return SUCCESS;
                          }
                 if (saveFlag!=null && saveFlag.length()>0)
                 {
                    
                    stat1=conn.prepareStatement("select * from ei_mlfs_mast where app_no=?");
                    stat1.setString(1,APP_NO.trim().toUpperCase());
                    result1=stat1.executeQuery();
                    if (result1.next())
                    {
                      addActionMessage("Record Alredy Exists !! "+APP_NO);
                      return ERROR;
                    }else
                    { 
                       stat1=conn.prepareStatement("insert into ei_mlfs_mast (app_no,app_date,lic_type,lic_no,lic_amt,lic_date,port_code,fwd_port,fwd_ac,location,seh_user,tdate) values (?,trunc(sysdate),?,?,?,to_date(?,'yyyy-mm-dd'),?,to_date(?,'dd/mm/yyyy'),to_date(?,'dd/mm/yyyy'),?,?,sysdate)   ");
                    
                       stat1.setString(1,APP_NO.trim().toUpperCase());
                       stat1.setString(2,APP_TYPE.trim());
                       stat1.setString(3,LIC_NO);
                       stat1.setString(4,LIC_AMT);
                        if (LIC_DATE.length()>8)
                        {  stat1.setString(5,LIC_DATE.substring(0,10));}
                        else
                        {  stat1.setString(5,LIC_DATE);}
                       stat1.setString(6,LOADING_PORT);
                       stat1.setString(7,FWD_PORT);
                       stat1.setString(8,FWD_AC);
                       stat1.setString(9,LOCATION_CODE);
                       stat1.setString(10,usrid);
                       stat1.executeUpdate();
                       
                        if(BILL_NO!=null )
                        {
                          for (int i = 0; i < BILL_NO.size(); i++) 
                         {
                     
                            if(BILL_NO.get(i)!=null && !BILL_NO.get(i).toString().isEmpty())
                                {   
                                    stat1=conn.prepareStatement("select * from ei_mlfs_dtls where shp_bill_no=? and shp_bill_date=? ");
                                    stat1.setString(1,BILL_NO.get(i).toString().toUpperCase());
                                    stat1.setString(2,BILL_DATE.get(i).toString().toUpperCase());
                                    result1=stat1.executeQuery();
                                    if (result1.next())
                                    {
                                       addActionMessage("Record Alredy Exists !! "+BILL_NO.get(i).toString().toUpperCase());
                                       return ERROR;
                                        
                                    }else
                                    
                                    {
                                        stat2=conn.prepareStatement("insert into ei_mlfs_dtls (app_no,shp_bill_no,shp_bill_date,port_code,fob_fc,fob_inr,seh_user,loct_code,tdate) values (?,?,?,?,?,?,?,?,sysdate)");
                                        stat2.setString(1,APP_NO.trim().toUpperCase());
                                        stat2.setString(2,BILL_NO.get(i).toString().toUpperCase());
                                        stat2.setString(3,BILL_DATE.get(i).toString().toUpperCase());
                                        stat2.setString(4,PORT_CODE.get(i).toString().toUpperCase());
                                        stat2.setString(5,FOB_FC.get(i).toString().toUpperCase());
                                        stat2.setString(6,FOB_INR.get(i).toString().toUpperCase());
                                        stat2.setString(7,usrid);
                                        stat2.setString(8,LOCATION_CODE);
                                        y=stat2.executeUpdate();
                                   }
                                }
                            }
                         }
                       
                      
                    }
                    
                   
                 
                 }/// Save Flag Closed...
                    
                  if (y>0)
                          { conn.commit();
                           addActionMessage("Records Save...!");
                           return SUCCESS;
                          }
                  } 
          catch(Exception e){
              conn.rollback();
              addActionMessage(" Error...!"+e);
              System.out.println(e.toString());
          }
      
             finally {
            if (conn != null) {conn.close();}
            if (result2!=null){result2.close();}
            if (stat2 != null) {stat2.close(); }
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
      
        try {
             EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
             
        } catch (Exception e) {
            System.out.println(e.toString());
        }
            
            
            try{
                
                stat2=conn.prepareStatement("select app_no,to_char(app_date,'dd/mm/yyyy') app_date,lic_no,to_char(lic_date,'yyyy-mm-dd') lic_date,lic_type,lic_amt,port_code,to_char(fwd_port,'dd/mm/yyyy') fwd_port,to_char(fwd_ac,'dd/mm/yyyy') fwd_ac,to_char(sale_date,'dd/mm/yyyy') sale_date,sale_amt,     "
                                          + " to_char(sysdate,'yyyy-mm-dd') maxdate from ei_mlfs_mast WHERE trim(app_no)=?");
                
                stat2.setString(1,UPD_APP.trim());
                result2=stat2.executeQuery();
                while(result2.next()){ 
                                           APP_DATE=result2.getString("app_date");
                                           APP_TYPE=result2.getString("lic_type");
                                           LOADING_PORT=result2.getString("port_code");
                                           LIC_NO=result2.getString("lic_no");
                                           LIC_DATE=result2.getString("lic_date");
                                           LIC_AMT=result2.getString("lic_amt");
                                           FWD_PORT=result2.getString("FWD_PORT");
                                           FWD_AC=result2.getString("fwd_ac");
                                           MAXDATE=result2.getString("maxdate");
                     
                                stat=conn.prepareStatement("select shp_bill_no,to_char(shp_bill_date,'dd-Mon-yyyy') sb_date,port_code,fob_fc,fob_inr,nvl(rels_amt,0) rels_amt,loct_code from ei_mlfs_dtls where trim(app_no)=? ");
                            stat.setString(1,UPD_APP.trim());
                            result=stat.executeQuery();
                            while (result.next())
                            {
                             TFOB=TFOB+result.getFloat("fob_fc");
                             TINR=TINR+result.getFloat("fob_INR");
                             TREL=TREL+result.getFloat("rels_amt");
                            
                             SBLIST.add(new MeisSBBean(result.getString("shp_bill_no"),result.getString("sb_date"),result.getString("port_code"),result.getString("loct_code"),result.getBigDecimal("fob_fc"),result.getBigDecimal("fob_inr"),result.getBigDecimal("rels_amt") ));
                         
                            }
                            
                            
                         }
                
                
                
                  
            }catch(Exception e){
                 addActionMessage(" Error...!"+e);
                System.out.println(e.toString());
            }
            finally {
            if (conn != null) {conn.close();}
            if (result != null) {result.close(); }
            if (stat != null) {stat.close(); }
            if (result2 != null) {result2.close(); }
            if (stat2 != null) {stat2.close(); }
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
                 
                 EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection(); 
                 
                         stat2=conn.prepareStatement("select to_char(sysdate,'yyyy-mm-dd') maxdate from dual");
                        result2=stat2.executeQuery();
                        if (result2.next())
                        {
                          MAXDATE=result2.getString("maxdate");
                        }    
                       
                      }catch(Exception e){
                System.out.println(e.toString());
            }
        
        SEARCH_APP="";
        
        
      
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
                       
                        stat = conn.prepareStatement(" select shp_bill_no,to_char(shp_bill_date,'dd-MON-yyyy') sb_date,to_char(let_exp_date,'dd-Mon-yyyy') letexp  from ei_sbill_master where  shp_bill_no like ?  ");
                        stat.setString(1,SEARCH_CODE.toUpperCase() + "%");
                        result = stat.executeQuery();
                        while (result.next()) {
                           
                            SBSearch.add(new GetListBean(result.getString("shp_bill_no"),result.getString("sb_date"),result.getString("letexp")));
                             
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
 
 
    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }
 

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSEARCH_APP() {
        return SEARCH_APP;
    }

    public void setSEARCH_APP(String SEARCH_APP) {
        this.SEARCH_APP = SEARCH_APP;
    }

    public Date getSEARCH_DATE() {
        return SEARCH_DATE;
    }

    public void setSEARCH_DATE(Date SEARCH_DATE) {
        this.SEARCH_DATE = SEARCH_DATE;
    }

    
    public String getSEARCH_LIC() {
        return SEARCH_LIC;
    }

    public void setSEARCH_LIC(String SEARCH_LIC) {
        this.SEARCH_LIC = SEARCH_LIC;
    }

    public List getSHIP_DEL() {
        return SHIP_DEL;
    }

    public void setSHIP_DEL(List SHIP_DEL) {
        this.SHIP_DEL = SHIP_DEL;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUPD_APP() {
        return UPD_APP;
    }

    public void setUPD_APP(String UPD_APP) {
        this.UPD_APP = UPD_APP;
    }

    public String getAPP_NO() {
        return APP_NO;
    }

    public void setAPP_NO(String APP_NO) {
        this.APP_NO = APP_NO;
    }

    public String getAPP_DATE() {
        return APP_DATE;
    }

    public void setAPP_DATE(String APP_DATE) {
        this.APP_DATE = APP_DATE;
    }

    public String getAPP_TYPE() {
        return APP_TYPE;
    }

    public void setAPP_TYPE(String APP_TYPE) {
        this.APP_TYPE = APP_TYPE;
    }

    public String getLIC_NO() {
        return LIC_NO;
    }

    public void setLIC_NO(String LIC_NO) {
        this.LIC_NO = LIC_NO;
    }

    public String getLIC_DATE() {
        return LIC_DATE;
    }

    public void setLIC_DATE(String LIC_DATE) {
        this.LIC_DATE = LIC_DATE;
    }

    public String getLIC_AMT() {
        return LIC_AMT;
    }

    public void setLIC_AMT(String LIC_AMT) {
        this.LIC_AMT = LIC_AMT;
    }

    public String getLOADING_PORT() {
        return LOADING_PORT;
    }

    public void setLOADING_PORT(String LOADING_PORT) {
        this.LOADING_PORT = LOADING_PORT;
    }

    

    public String getFWD_PORT() {
        return FWD_PORT;
    }

    public void setFWD_PORT(String FWD_PORT) {
        this.FWD_PORT = FWD_PORT;
    }

    public String getFWD_AC() {
        return FWD_AC;
    }

    public void setFWD_AC(String FWD_AC) {
        this.FWD_AC = FWD_AC;
    }

    public String getMAXDATE() {
        return MAXDATE;
    }

    public void setMAXDATE(String MAXDATE) {
        this.MAXDATE = MAXDATE;
    }

    public List getSBLIST() {
        return SBLIST;
    }

    public void setSBLIST(List SBLIST) {
        this.SBLIST = SBLIST;
    }

    public Double getTFOB() {
        return TFOB;
    }

    public void setTFOB(Double TFOB) {
        this.TFOB = TFOB;
    }

    public Double getTINR() {
        return TINR;
    }

    public void setTINR(Double TINR) {
        this.TINR = TINR;
    }

    public Double getTREL() {
        return TREL;
    }

    public void setTREL(Double TREL) {
        this.TREL = TREL;
    }

    public String getSEARCH_CODE() {
        return SEARCH_CODE;
    }

    public void setSEARCH_CODE(String SEARCH_CODE) {
        this.SEARCH_CODE = SEARCH_CODE;
    }

    public List getSBSearch() {
        return SBSearch;
    }

    public void setSBSearch(List SBSearch) {
        this.SBSearch = SBSearch;
    }

    public String getUpdFlag() {
        return updFlag;
    }

    public void setUpdFlag(String updFlag) {
        this.updFlag = updFlag;
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

    public List getFOB_FC() {
        return FOB_FC;
    }

    public void setFOB_FC(List FOB_FC) {
        this.FOB_FC = FOB_FC;
    }

    public List getFOB_INR() {
        return FOB_INR;
    }

    public void setFOB_INR(List FOB_INR) {
        this.FOB_INR = FOB_INR;
    }

    public List getPORT_CODE() {
        return PORT_CODE;
    }

    public void setPORT_CODE(List PORT_CODE) {
        this.PORT_CODE = PORT_CODE;
    }
 
 
 
 
 
    
       
}
 