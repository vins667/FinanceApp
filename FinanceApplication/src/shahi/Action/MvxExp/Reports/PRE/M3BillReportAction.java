package shahi.Action.MvxExp.Reports.PRE;

import shahi.Action.MvxExp.Reports.GVTINC.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import shahi.Action.database.connection;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.Map;
import shahi.Action.MvxExp.Reports.PRE.bean.B_SUB_TYPE_LISTBean;
import shahi.Action.MvxExp.Reports.PRE.bean.B_TYPE_LISTBean;
import shahi.Action.MvxExp.Reports.PRE.bean.DEPT_LISTBean;
import shahi.Action.MvxExp.Reports.PRE.bean.ProductListBean; 

public final class M3BillReportAction extends ActionSupport{

	private String date1;
	private String date2;
        private String BILL_TYPE; 
        private String DEPT;
       
	private String abc;
        private String aausrid;
      
       
        private List PORTLIST = new ArrayList();
	private List PCH_LIST = new ArrayList();
        private List Company_LIST = new ArrayList();
        private List Bank_List = new ArrayList();
        private List LOC_LIST=new ArrayList();
        private List BOS_LIST=new ArrayList();
        private List B_TYPE_LIST=new ArrayList();
        private List B_SUB_TYPE_LIST=new ArrayList();
        private List PRO_LIST=new ArrayList();
        private List PRO_GRPLIST=new ArrayList();
        private List DEPT_LIST=new ArrayList();
          
          
	public String execute() throws SQLException
	{
		
		abc="123";
		Connection con=null;
		PreparedStatement stat=null;
		ResultSet resultset=null;
                Map session = ActionContext.getContext().getSession();
              String LOCATION_CODE = (String) session.get("sessLocationCode");
            String usrid = (String) session.get("sessUserId");

                if(usrid==null)
                {
                   session.put("sessUserId",aausrid);
                   usrid=aausrid;
                }
                if (usrid == null) {
                    addActionMessage("Session Not Valid !!");
                    return ERROR;
                }
		try
		{  
                    con=new connection().getConnection();	
                      
                                stat=con.prepareStatement("select distinct port from ei_bos_mast  where port is not null order by 1");
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   PORTLIST.add(resultset.getString("port"));
                                }
                               stat=con.prepareStatement("select distinct pch from shahiweb.m3_bill_detail where pch is not null order by 1 ");
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   PCH_LIST.add(resultset.getString("pch"));
                                }
                                
                               stat=con.prepareStatement("select distinct BILL_WHLO from shahiweb.m3_bill_master where BILL_WHLO is not null order by 1");
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   LOC_LIST.add(resultset.getString("BILL_WHLO"));
                                }
                                
                                stat=con.prepareStatement("select distinct dept_sl_no exp_code,'EXPORT' EXP from shahiweb.m3_bill_type_master where dept_sl_no='26' order by 1");
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   DEPT_LIST.add(new DEPT_LISTBean(resultset.getString("exp_code"),resultset.getString("EXP")));
                                }
                                
                               stat=con.prepareStatement("select  type_desc||'-'||type_code type,sl_no from shahiweb.m3_bill_type_master where dept_sl_no=? order by 1");
                               stat.setString(1, DEPT);
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   B_TYPE_LIST.add(new B_TYPE_LISTBean(resultset.getString("type"),resultset.getString("sl_no")));
                                }
                                
                               stat=con.prepareStatement("select sub_type_desc||'-'||sub_type_code subtype_desc,sl_no from shahiweb.m3_bill_sub_type_master where type_sl_no=?  order by 1");
                               stat.setString(1, BILL_TYPE);
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   B_SUB_TYPE_LIST.add(new B_SUB_TYPE_LISTBean(resultset.getString("subtype_desc"),resultset.getString("sl_no")));
                                }
                                
                                
                                
                               
                                
                       
                          if(stat!=null)
                          stat.close();   
                          if(resultset!=null)
                           resultset.close();
		
                
                      stat=con.prepareStatement("select distinct COMPANY from ei_endors_mast");
                      resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   Company_LIST.add(resultset.getString("COMPANY"));
                                }
                             
                                
                      PreparedStatement stat1=con.prepareStatement("select distinct nvl(F_BANK,0) F_BANK from ei_shipment_mast");
                      ResultSet resultset1=stat1.executeQuery();
                                while(resultset1.next())
                                {
                                   Bank_List.add(resultset1.getString("F_BANK"));
                                }
                                
		}
	
		catch(SQLException se)
		{
			System.out.println("SQLExceptionEPTrackReport"+se);
		}
		catch(Exception e)
		{
			System.out.println("Exception" +e);
		}
		finally
		{
			if(con!=null)
			{
				con.close();
			}
		}
		return SUCCESS;
	}
        
        
        public String pch() throws SQLException
        {
            Connection con=null;
		PreparedStatement stat=null;
		ResultSet resultset=null;
                Map session = ActionContext.getContext().getSession();
              String LOCATION_CODE = (String) session.get("sessLocationCode");
            String usrid = (String) session.get("sessUserId");

                if(usrid==null)
                {
                   session.put("sessUserId",aausrid);
                   usrid=aausrid;
                }
                if (usrid == null) {
                    addActionMessage("Session Not Valid !!");
                    return ERROR;
                }
		try
		{  
                    con=new connection().getConnection();	
                      
                                 if(BILL_TYPE!=null && BILL_TYPE.length()>0){
                                    stat=con.prepareStatement("select a.product_desc||'-'||product_code product,a.sl_no sln from shahiweb.m3_bill_product_master a,shahiweb.m3_bill_type_master b,shahiweb.m3_bill_sub_type_master c where a.sub_type_sl_no=c.sl_no and  b.sl_no=c.type_sl_no and  b.dept_sl_no=? and  b.sl_no=?  order by 1");
                                    stat.setString(1, DEPT);
                                     stat.setString(2, BILL_TYPE);
                                    resultset=stat.executeQuery();
                                     while(resultset.next())
                                     {
                                        PRO_LIST.add(new ProductListBean(resultset.getString("product"),resultset.getString("sln")));
                                     }
                                }
            
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                finally{
                    resultset.close();
                    stat.close();
                     con.close();
                }
                
                execute();
                
                return "pch";
        }
	
        
	public String format()
	{
		
		return SUCCESS;
	}
	
	public String getDate1() {
		return date1;
	}
	
	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

  

	public String getAbc() {
		return abc;
	}


	public void setAbc(String abc) {
		this.abc = abc;
	}

  
    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

   

    public List getPORTLIST() {
        return PORTLIST;
    }

    public void setPORTLIST(List PORTLIST) {
        this.PORTLIST = PORTLIST;
    }

    public List getPCH_LIST() {
        return PCH_LIST;
    }

    public void setPCH_LIST(List PCH_LIST) {
        this.PCH_LIST = PCH_LIST;
    }

   

    public List getCompany_LIST() {
        return Company_LIST;
    }

    public void setCompany_LIST(List Company_LIST) {
        this.Company_LIST = Company_LIST;
    }

    public List getBank_List() {
        return Bank_List;
    }

    public void setBank_List(List Bank_List) {
        this.Bank_List = Bank_List;
    }

    public List getLOC_LIST() {
        return LOC_LIST;
    }

    public void setLOC_LIST(List LOC_LIST) {
        this.LOC_LIST = LOC_LIST;
    }

    public List getBOS_LIST() {
        return BOS_LIST;
    }

    public void setBOS_LIST(List BOS_LIST) {
        this.BOS_LIST = BOS_LIST;
    }

    public List getB_TYPE_LIST() {
        return B_TYPE_LIST;
    }

    public void setB_TYPE_LIST(List B_TYPE_LIST) {
        this.B_TYPE_LIST = B_TYPE_LIST;
    }

    public List getB_SUB_TYPE_LIST() {
        return B_SUB_TYPE_LIST;
    }

    public void setB_SUB_TYPE_LIST(List B_SUB_TYPE_LIST) {
        this.B_SUB_TYPE_LIST = B_SUB_TYPE_LIST;
    }

    public String getBILL_TYPE() {
        return BILL_TYPE;
    }

    public void setBILL_TYPE(String BILL_TYPE) {
        this.BILL_TYPE = BILL_TYPE;
    }

    public List getPRO_LIST() {
        return PRO_LIST;
    }

    public void setPRO_LIST(List PRO_LIST) {
        this.PRO_LIST = PRO_LIST;
    }

    public List getPRO_GRPLIST() {
        return PRO_GRPLIST;
    }

    public void setPRO_GRPLIST(List PRO_GRPLIST) {
        this.PRO_GRPLIST = PRO_GRPLIST;
    }

    public String getDEPT() {
        return DEPT;
    }

    public void setDEPT(String DEPT) {
        this.DEPT = DEPT;
    }

    public List getDEPT_LIST() {
        return DEPT_LIST;
    }

    public void setDEPT_LIST(List DEPT_LIST) {
        this.DEPT_LIST = DEPT_LIST;
    }

    

   

   

 
    


   
	  
}
    