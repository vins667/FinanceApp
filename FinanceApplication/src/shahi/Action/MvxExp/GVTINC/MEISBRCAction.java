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

  

public class MEISBRCAction extends ActionSupport
{
    private String MAXDATE;
   
    private String currentdate;
    private String SEARCH_CODE;
    private String aausrid;
    private String saveFlag;
    private String updFlag;
    private String indexname; 
    private List SBSearch = new ArrayList();
    private List BILL_NO;
    private List BILL_DATE;
   
    private List EP_FROM_HO;
     private List LCERT_APPL;
    private List LCERT_RECV;
    private List BRC_APPL;
    private List BRC_RECV;
    
 
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
             
            
             try{
                 
                    stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                    stat1.setString(1,usrid);
                    result1=stat1.executeQuery();
                    while (result1.next())
                    {LOCATION_CODE=result1.getString("location_code");
                    } 
                    int x=0;
                    if (saveFlag!=null && saveFlag.length()>0)
                    {
                         if(BILL_NO!=null )
                        {
                          for (int i = 0; i < BILL_NO.size(); i++) 
                         {
                      
                            if(BILL_NO.get(i)!=null && !BILL_NO.get(i).toString().isEmpty())
                                {   int rec=0;
                                    stat2=conn.prepareStatement("select distinct loading,buyer,desti_cntry from ei_endors_mast a,ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and trim(b.shp_bill_no)=? and shp_Bill_date=?");
                                    stat2.setString(1,BILL_NO.get(i).toString().toUpperCase());
                                    stat2.setString(2,BILL_DATE.get(i).toString().toUpperCase());
                                    result2=stat2.executeQuery();
                                    while (result2.next())
                                    {
                                       rec=rec+1;
                                    }
                                    if (rec>1){
                                        addActionMessage("More then one Port/Buyer/Cntry Found against S/B "+BILL_NO.get(i).toString().toUpperCase());
                                        return ERROR;
                                    }
                                    if (result2!=null){result2.close();}
                                    if (stat2 != null) {stat2.close(); }
                                    rec=0;
                                    stat2=conn.prepareStatement("select distinct agent,fwd_code from ei_endors_mast a,ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and trim(b.shp_bill_no)=? and shp_Bill_date=?");
                                    stat2.setString(1,BILL_NO.get(i).toString().toUpperCase());
                                    stat2.setString(2,BILL_DATE.get(i).toString().toUpperCase());
                                    result2=stat2.executeQuery();
                                    while (result2.next())
                                    {
                                       rec=rec+1;
                                    }
                                    if (rec>1){
                                        addActionMessage("More then one CHA/FWd Found against S/B "+BILL_NO.get(i).toString().toUpperCase());
                                        return ERROR;
                                    }
                                     if (result2!=null){result2.close();}
                                    if (stat2 != null) {stat2.close(); }
                                    
                                   stat1=conn.prepareStatement(" update ei_sbill_master set ep_from_ho=to_date(?,'dd/mm/yyyy'),lcert_appl=to_date(?,'dd/mm/yyyy'),lcert_recv=to_date(?,'dd/mm/yyyy'),brc_appl=to_date(?,'dd/mm/yyyy'),brc_date=to_date(?,'dd/mm/yyyy'),mod_user=?,mod_date=sysdate where shp_bill_no=? and shp_bill_date=? ");
                                   stat1.setString(1,EP_FROM_HO.get(i).toString());
                                   stat1.setString(2,LCERT_APPL.get(i).toString());
                                   stat1.setString(3,LCERT_RECV.get(i).toString());
                                   stat1.setString(4,BRC_APPL.get(i).toString());
                                   stat1.setString(5,BRC_RECV.get(i).toString());
                                   stat1.setString(6,usrid);
                                   stat1.setString(7,BILL_NO.get(i).toString().toUpperCase());
                                   stat1.setString(8,BILL_DATE.get(i).toString().toUpperCase());
                                   x=stat1.executeUpdate();
                                }
                                 if (stat1 != null) {stat1.close(); }
                           }
                        }
                    
                    
                    }
                    if (x>0)
                    {
                     conn.commit();
                    addActionMessage("Records Save...!");
                    EP_FROM_HO=null;
                    LCERT_APPL=null;
                    LCERT_RECV=null;
                    BRC_APPL=null;
                    BRC_RECV=null;
                    
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
            if (stat1 != null) {stat1.close(); }
        }
      
        return SUCCESS;
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

 
        
    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
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
   

    public String getMAXDATE() {
        return MAXDATE;
    }

    public void setMAXDATE(String MAXDATE) {
        this.MAXDATE = MAXDATE;
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

    

    public List getEP_FROM_HO() {
        return EP_FROM_HO;
    }

    public void setEP_FROM_HO(List EP_FROM_HO) {
        this.EP_FROM_HO = EP_FROM_HO;
    }

   

    public List getLCERT_APPL() {
        return LCERT_APPL;
    }

    public void setLCERT_APPL(List LCERT_APPL) {
        this.LCERT_APPL = LCERT_APPL;
    }

    public List getLCERT_RECV() {
        return LCERT_RECV;
    }

    public void setLCERT_RECV(List LCERT_RECV) {
        this.LCERT_RECV = LCERT_RECV;
    }

    public List getBRC_APPL() {
        return BRC_APPL;
    }

    public void setBRC_APPL(List BRC_APPL) {
        this.BRC_APPL = BRC_APPL;
    }

    public List getBRC_RECV() {
        return BRC_RECV;
    }

    public void setBRC_RECV(List BRC_RECV) {
        this.BRC_RECV = BRC_RECV;
    }
 
 
    
       
}
 