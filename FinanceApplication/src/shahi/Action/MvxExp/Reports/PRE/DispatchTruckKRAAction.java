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

public final class DispatchTruckKRAAction extends ActionSupport{

	private String date1;
	private String date2;
       
	private String abc;
        private String aausrid;
      
       
        private List PORTLIST = new ArrayList();
	private List PCH_LIST = new ArrayList();
        private List Company_LIST = new ArrayList();
        private List Bank_List = new ArrayList();
        private List LOC_LIST=new ArrayList();
        private List BOS_LIST=new ArrayList();
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
                               stat=con.prepareStatement("SELECT distinct cost_centre FROM ei_endors_mast where cost_centre is not null order by 1");
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   PCH_LIST.add(resultset.getString("cost_centre"));
                                }
                                
                                stat=con.prepareStatement("select distinct bos_loct from ei_bos_mast order by 1");
                               resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   LOC_LIST.add(resultset.getString("bos_loct"));
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

    

   

   

 
    


   
	  
}
    