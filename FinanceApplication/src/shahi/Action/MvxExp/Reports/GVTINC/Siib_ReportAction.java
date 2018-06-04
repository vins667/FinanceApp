package shahi.Action.MvxExp.Reports.GVTINC;

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
import shahi.Action.MvxExp.Admin.Beans.GetListBean;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public final class Siib_ReportAction extends ActionSupport{

	private String date1;
	private String date2;
       
	private String abc;
        private String aausrid;
      
       
        private List PORTLIST = new ArrayList();
	private List EXPTYPE_LIST = new ArrayList();
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
                      
	       if(date1!=null && date1.length()>0 && date2!=null && date2.length()>0)
		{  
	  
                                stat=con.prepareStatement("select distinct loading from ei_endors_mast a,ei_shipment_dtls c where loading is not null and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and  trunc(c.shp_bill_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy')  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   PORTLIST.add(resultset.getString("loading"));
                                }
                               stat=con.prepareStatement("select distinct exp_type from ei_endors_mast a,ei_shipment_dtls c where  a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and  trunc(c.shp_bill_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy')  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   EXPTYPE_LIST.add(resultset.getString("exp_type"));
                                }
                     
                        
                       
                          if(stat!=null)
                          stat.close();   
                          if(resultset!=null)
                           resultset.close();
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

    public List getEXPTYPE_LIST() {
        return EXPTYPE_LIST;
    }

    public void setEXPTYPE_LIST(List EXPTYPE_LIST) {
        this.EXPTYPE_LIST = EXPTYPE_LIST;
    }

 
    


   
	  
}
    