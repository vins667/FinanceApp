package shahi.Action.MvxExp.PRE;

 
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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import shahi.Action.MvxExp.PRE.Beans.EPTrackBean;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.Beans.InvTrackBean;
  

public class EPCopyActtypeAction extends ActionSupport {
    private String currentdate;
    private String viewFlag;
   
    private String unitparam;
    private List showList;
    private List unitList=new ArrayList<UnitBean>();
    private String PARAA;
    private String PARAB;
    private String SB_NO;
    private String SB_DATE;
    private String aausrid;
    private String stype;
    private String srem;
    private String location;
    private String year;
    private String company;
    private String inv_no;
    private String saveFlag;
   
  
    private List ShowDetail=new ArrayList();
    private List sbDetail=new ArrayList();
    private List TypeList=new ArrayList();
    private InputStream inputStream;
     
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
                   stat1 = conn.prepareStatement("select  type_desc||'-'||type_code type_DESC,type_code from  EI_GRUP_TYPE_DTLS WHERE GRUP_TYPE_CODE='SBF' and close_date is null order by 1" );
                         result1 = stat1.executeQuery();
                         while(result1.next())
                         {  
                            TypeList.add(new GetListBean(result1.getString("type_code"),result1.getString("type_desc")));      
                         }
                  
                 
              
                if (viewFlag != null  && viewFlag.equals("Yes")) 
                {
                     stat1 = conn.prepareStatement("select a.year,a.company,a.inv_no,a.location,a.excs_inv_no,a.exp_type||' '||decode(a.self_tp,'N','Normal','F','Free Sample','S','Trade Sample',self_tp) inv_type,to_char(a.fwd_custom,'dd/mm/yyyy') fwd_custom,to_char(a.etd_date,'dd/mm/yyyy') etd_date,"
                            + " to_char(a.t_o_date,'dd/mm/yyyy') to_date,to_char(a.doc_send,'dd/mm/yyyy') fwd_post,to_char(a.fin_date,'dd/mm/yyyy') fin_date,a.ac_holder,a.cost_centre,a.mode_of_ship,a.inv_qty,a.buyer,a.buyer_addr,a.cons_addr,a.year,a.company,a.inv_no,LOADING_port,LOADING CLR_port,"
                            +" DESTI_CNTRY,CNTRY_ORIGIN,DISCHARGE,Desti_code,a.Dis_CNTRY,nvl(a.first_sale,'N') ship_type,to_char(a.AWBdate,'dd/mm/yyyy') awbdate,a.crncy_code,a.lcno,nvl(a.ship_qty,0) ship_qty"
                            +"  from ei_endors_mast a, ei_shipment_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and b.shp_bill_no=? and b.shp_bill_date=? order by excs_inv_no");
                     stat1.setString(1, SB_NO);
                     stat1.setString(2,SB_DATE);
                     result1 = stat1.executeQuery(); 
                    while (result1.next()) 
                    { 
                        year=result1.getString("year");
                        company=result1.getString("company");
                        inv_no=result1.getString("inv_no");
                         String mlic="";
                         stat3 = conn.prepareStatement("select distinct ref_type||'-'||ref_no lic_no from ei_endors_lc_lic_dtls where year=? and company=? and inv_no=?");
                         stat3.setString(1, year);
                         stat3.setString(2, company);
                         stat3.setString(3, inv_no);
                         result3 = stat3.executeQuery();
                     
                        while (result3.next()) 
                        {mlic=result3.getString("lic_no");}
                        
                        ShowDetail.add(new EPTrackBean(result1.getString("location"),result1.getString("excs_inv_no"),result1.getString("cost_centre"),result1.getString("buyer"),result1.getString("mode_of_ship"),result1.getString("clr_port"),result1.getString("desti_cntry"),result1.getString("ship_qty"),result1.getString("inv_type"),result1.getString("fwd_custom"),result1.getString("to_date"),result1.getString("etd_date"),result1.getString("fwd_post"),result1.getString("awbdate"),result1.getString("fin_date"),result1.getString("ac_holder"),mlic));
                     }
                      stat3=conn.prepareStatement("select to_char(a.tr_date,'dd/mm/yyyy hh24:mi') tr_date,a.tr_type,b.type_desc,a.remarks,a.seh_user from ei_sbill_track a,ei_grup_type_dtls b where b.grup_type_code='SBF' and b.type_code=a.tr_type  and a.shp_bill_no=? and a.shp_bill_date=? order by 1");
                      stat3.setString(1, SB_NO);
                      stat3.setString(2, SB_DATE);
                      result3 = stat3.executeQuery();
                      while (result3.next()) 
                      {  System.out.println("test");
                           sbDetail.add(new InvTrackBean(result3.getString("tr_date"), result3.getString("tr_type"), result3.getString("type_desc"), result3.getString("remarks"), result3.getString("seh_user")));
                        
                      }
                      
                         
                } // View Flg Close      
                  if (saveFlag!=null && saveFlag.equals("YES"))
                    {
                             if (stype!=null)
                             {
                                  stat1=conn.prepareStatement("insert into EI_SBILL_TRACK (SHP_BILL_NO,SHP_BILL_DATE,TR_TYPE,REMARKS ,TR_DATE , TDATE,SEH_USER) values (?,?,?,?,sysdate,sysdate,?)");
                                  stat1.setString(1,SB_NO.trim());
                                  stat1.setString(2,SB_DATE);
                                  stat1.setString(3,stype);
                                  stat1.setString(4,srem.toUpperCase());
                                  stat1.setString(5,usrid);
                                  stat1.executeUpdate();
                                  flag=1;
                             }
                    } 
                           
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : EPCopyTrackAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : EPCopyTrackAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                    
                    if (stat1 != null) {
                        stat1.close();
                    }
                      if (stat3 != null) {
                        stat3.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    
                    result1 = null;
                    result3 = null;
                    stat1 = null;
                    conn = null;
                     
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : EPCopyTrackAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
           
            SB_NO=null;
            SB_DATE=null;
            addActionMessage("Records Save(s) !!");
            return SUCCESS;
        } else {

            // addActionMessage("Records Not Save(s) !!");
             
            return ERROR;
        }
    }

        public String sbView() throws SQLException {
        Map<String, Object> session = ActionContext.getContext().getSession();
        String usrid = (String) session.get("sessUserId");
          
        if (usrid == null) {
            addActionError("Session is not Available");

            return ERROR;
        }
      
       if(unitparam!=null && unitparam.length()>0 ){
        shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
         unitList = dao.getSBList(unitparam);
        }
        return "sbview";
    } 
        
     public String sbAJAX()  throws SQLException, ParseException, UnsupportedEncodingException
    	{
             
    	    Map session = ActionContext.getContext().getSession();
    	    String usrid = ((String)session.get("sessUserId"));
    	    if (usrid == null)
    	    {
    	      addActionError("Session is not Available");
    	      return "error";
    	    }
           
    	    shahi.Action.MvxExp.PRE.dao.PreInvoiceDao dao = new shahi.Action.MvxExp.PRE.dao.PreInvoiceDao();
            
            
    	    String placedesc = dao.getSBByNameAjax(unitparam);
    	    
    	    if ((placedesc!= null) && (!placedesc.equals(""))) {
    	      this.inputStream = new ByteArrayInputStream(placedesc.getBytes("UTF-8"));
    	    } else {
    	      this.inputStream = new ByteArrayInputStream("Data Not Found#".getBytes("UTF-8"));
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

    public String getSB_NO() {
        return SB_NO;
    }

    public void setSB_NO(String SB_NO) {
        this.SB_NO = SB_NO;
    }

    public String getSB_DATE() {
        return SB_DATE;
    }

    public void setSB_DATE(String SB_DATE) {
        this.SB_DATE = SB_DATE;
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
    

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    
    public String getUnitparam() {
        return unitparam;
    }

    public void setUnitparam(String unitparam) {
        this.unitparam = unitparam;
    }

    public List getUnitList() {
        return unitList;
    }

    public void setUnitList(List unitList) {
        this.unitList = unitList;
    }

    public List getSbDetail() {
        return sbDetail;
    }

    public void setSbDetail(List sbDetail) {
        this.sbDetail = sbDetail;
    }

    public String getPARAA() {
        return PARAA;
    }

    public void setPARAA(String PARAA) { 
        this.PARAA = PARAA;
    }

    public String getPARAB() {
        return PARAB;
    }

    public void setPARAB(String PARAB) {
        this.PARAB = PARAB;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List getTypeList() {
        return TypeList;
    }

    public void setTypeList(List TypeList) {
        this.TypeList = TypeList;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getSrem() {
        return srem;
    }

    public void setSrem(String srem) {
        this.srem = srem;
    }
 
       
}
