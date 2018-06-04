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

import shahi.Action.MvxExp.PRE.Beans.EPTrackBean;
 
 
  

public class BPOQueryAction extends ActionSupport {
   
    private String currentdate;
    private String aausrid;
    private String searchitem;
    private String searchtype;
    private String viewFlag;
    private List InvList = new ArrayList();
    
    
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
                    if (searchtype.equals("BPO"))
                     { sqlstr=" trim(pre_print_no) like ? ";}
                    if (searchtype.equals("STYLE"))
                     { sqlstr=" trim(TOKEN_NO) like ? ";}
                    if (searchtype.equals("ITEM"))
                     { sqlstr=" substr(item_no,1,4) like ? ";} 
                    System.out.println("sqlstr"+sqlstr);
                       stat=conn.prepareStatement("select a.excs_inv_no,buyer,plan_no,desti_cntry,to_char(t_o_date,'dd/mm/yyyy') t_o_date,to_char(awbdate,'dd/mm/yyyy') awbdate,to_char(etd_date,'dd/mm/yyyy') etd_date,to_char(doc_send,'dd/mm/yyyy') doc_send,crncy_code,cost_centre,a.location,a.mode_of_ship,loading,ac_holder,sum(qty_endors) invqty,sum(qty_endors*(price_fc+nvl(price_misc,0))) fob_fc,sum(gr_decl_amt) grdecl  "+
                                                  "from ei_endors_mast a, ei_endors_Dtls b where "+sqlstr+" and a.company = b.company and a.year = b.year and a.inv_no = b.inv_no group by a.excs_inv_no,buyer,plan_no,desti_cntry,to_char(t_o_date,'dd/mm/yyyy'),to_char(awbdate,'dd/mm/yyyy'),to_char(etd_date,'dd/mm/yyyy'),to_char(doc_send,'dd/mm/yyyy'),crncy_code,cost_centre,a.location,a.mode_of_ship,loading,ac_holder order by 1 ");
                       stat.setString(1,searchitem.toUpperCase().trim());
                       result = stat.executeQuery(); 
                       while (result.next()) 
                        {
                             
                           InvList.add(new EPTrackBean(result.getString("location"), result.getString("excs_inv_no"), result.getString("cost_centre"),result.getString("buyer"),result.getString("mode_of_ship"),result.getString("loading"), result.getString("desti_cntry"),result.getString("invqty"),result.getString("plan_no"),result.getString("crncy_code"),result.getString("t_o_date"),result.getString("etd_date"),result.getString("doc_send"),result.getString("awbdate"),result.getString("ac_holder"),result.getString("fob_fc"),result.getString("grdecl")));
                        }
                       
                        
               
                   
                       
                
                
                } // View Flg Close      
 

           
                     
                  
            } catch (Exception e) {

                flag = 0;
                try {
                    flag = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : DeecQueryAction.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : DeecQueryAction.java" + e);

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
                    System.out.print("File Name : BPOQueryAction.java Exception in finally block");
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

   
      
} 
