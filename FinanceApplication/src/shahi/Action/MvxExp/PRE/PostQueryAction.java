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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.MvxExp.PRE.Beans.DBKQueryBean; 
import shahi.Action.MvxExp.PRE.Beans.PostQueryBean;
import shahi.Action.MvxExp.PRE.Beans.InvQueryBean;
 
 
   

public class PostQueryAction extends ActionSupport {
   
    private String currentdate;
    private String aausrid;
    private String searchitem;
    private String searchtype="INV";
    private String viewFlag;
    private List InvList = new ArrayList();
    private List DBKLIST = new ArrayList();
    private List INVDETAIL= new ArrayList();
 
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
          
            ResultSet result = null;
            ResultSet result1 = null;
           
            try {
               Date todaysDate = new Date();
               SimpleDateFormat sdfm = new SimpleDateFormat("dd/MM/yyyy");
           
              
               String sqlstr="";
                if (viewFlag.equals("Yes")) 
                {   
                    if (searchtype.equals("INV"))
                     { sqlstr=" trim(ALL_NO) like  '"+searchitem.toUpperCase().trim()+"'";}
                    if (searchtype.equals("SB"))
                     { sqlstr=" trim(SHP_BILL_NO) like  '"+searchitem.toUpperCase().trim()+"'";}
                    if (searchtype.equals("AWB"))
                     { sqlstr=" TRIM(AWB_NO) like  '"+searchitem.toUpperCase().trim()+"'";} 
                    if (searchtype.equals("HAWB"))
                     { sqlstr=" TRIM(H_AWB_NO) like  '"+searchitem.toUpperCase().trim()+"'";} 
                     
                       stat=conn.prepareStatement("select A.AWB_NO,a.h_awb_no,to_char(awb_date,'dd/mm/yyyy') awb_date,to_char(ac_send_date,'dd/mm/yyyy') fin_date,b.shp_bill_no,to_char(b.shp_bill_date,'dd/mm/yyyy') shp_bill_date ,to_char(b.let_exp_date,'dd/mm/yyyy') let_exp_date,all_no,ac_send_term,"+
                                                 " ship_qnty,currency,fob_amt,gr_disc,inr_conv,dbk_conv,buyer,loading,desti_cntry,FOLDER_NUMB from ei_shipment_mast a,ei_shipment_dtls b ,ei_endors_mast c where a.year=b.year and a.link_no=b.link_no and "+
                                                 "  b.year=c.year and b.company=c.company and b.inv_no=c.inv_no AND "+sqlstr+"  order by 1 ");
                       
                       result = stat.executeQuery(); 
                       while (result.next()) 
                        {
                            
                            stat1=conn.prepareStatement("select all_no,sr_no,co_no,co_line,substr(item_no,1,4) item,pre_print_no,token_no,qty_endors,qty_kgs,unit,currency,price_fc,price_misc,net_price,dbk_slno,str_slno,description from ei_endors_dtls where ALL_NO=? order by 1,2");
                            stat1.setString(1,result.getString("ALL_NO"));
                             result1=stat1.executeQuery();
                            while(result1.next())
                            {    
                               INVDETAIL.add(new InvQueryBean(result1.getString("all_no"),result1.getString("co_no"),result1.getString("co_line"),result1.getString("item"),result1.getString("pre_print_no"),result1.getString("token_no"),result1.getString("qty_endors"),result1.getString("qty_kgs"),result1.getString("unit"),result1.getString("currency"),result1.getString("price_fc"),result1.getString("price_misc"),result1.getString("net_price"),result1.getString("dbk_slno"),result1.getString("str_slno"),result1.getString("description")));
                            }
                            stat1.close();
                            result1.close();
                            
                            
                            stat1=conn.prepareStatement("select shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date,claim_port,dbk_admisable,dbk_received,DBK_SUPL_RECV,WOFF_AMT,str_due,str_recv,str_woff from ei_dbk_mast where shp_bill_no=? and to_char(shp_bill_date,'dd/mm/yyyy')=?");
                            stat1.setString(1,result.getString("shp_bill_no"));
                            stat1.setString(2,result.getString("shp_bill_date"));
                            result1=stat1.executeQuery();
                            while(result1.next())
                            {   
                               DBKLIST.add(new DBKQueryBean(result1.getString("shp_bill_no"),result1.getString("shp_bill_date"),result1.getString("claim_port"),result1.getString("dbk_admisable"),result1.getString("dbk_received"),result1.getString("DBK_SUPL_RECV"),result1.getString("WOFF_AMT"),result1.getString("str_due"),result1.getString("str_recv"),result1.getString("str_woff")));
                            }
                            stat1.close();
                            result1.close();
                            
                            
                            InvList.add(new PostQueryBean(result.getString("awb_no"), result.getString("h_awb_no"), result.getString("awb_date"),result.getString("fin_date"),result.getString("shp_bill_no"),result.getString("shp_bill_date"), result.getString("let_exp_date"),result.getString("all_no"),result.getString("ship_qnty"),result.getString("currency"),result.getString("fob_amt"),result.getString("gr_disc"),result.getString("inr_conv"),result.getString("dbk_conv"),result.getString("buyer"),result.getString("loading"),result.getString("desti_cntry"),result.getString("AC_SEND_TERM"),result.getString("FOLDER_NUMB")));
                        
                        
                        }
                       
                        
               
                   
                       
                
                
                } // View Flg Close      
 

           
                     
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : PostQueryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : PostQueryAction.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
 
                    if (result1 != null) {
                        result1.close();
                    }
                    

                    if (stat1 != null) {
                        stat1.close();
                    }
                     
                    if (stat != null) {
                        stat.close();
                    }
                    
                    if (conn != null) {
                        conn.close(); 
                    }
                     
                    result1 = null;
                    stat1 = null;
                    stat =null;
                   
                    
                } catch (Exception e) {
                    flag = 0;
                    System.out.print("File Name : PostQueryAction.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }

        if (flag == 1) {
            
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

    

    public List getInvList() {
        return InvList;
    }

    public void setInvList(List InvList) {
        this.InvList = InvList;
    }

    

    public String getSearchitem() {
        return searchitem;
    }

    public void setSearchitem(String searchitem) {
        this.searchitem = searchitem;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }

    public List getDBKLIST() {
        return DBKLIST;
    }

    public void setDBKLIST(List DBKLIST) {
        this.DBKLIST = DBKLIST;
    }

    public List getINVDETAIL() {
        return INVDETAIL;
    }

    public void setINVDETAIL(List INVDETAIL) {
        this.INVDETAIL = INVDETAIL;
    }

     
      
} 
