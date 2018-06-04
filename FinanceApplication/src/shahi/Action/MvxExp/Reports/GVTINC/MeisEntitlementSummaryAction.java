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
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public final class MeisEntitlementSummaryAction extends ActionSupport{

	private String date1;
	private String date2;
       
	private String abc;
        private String aausrid;
        
      
        private String cntry_orgin="IN";
        private List PORTLIST = new ArrayList();
        private List Company_LIST = new ArrayList();
        private List Cntry_Origin=new ArrayList();
        private List Exp_Type=new ArrayList();
        
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
                      
                    stat =con.prepareStatement("select distinct cntry_origin from ei_endors_mast where cntry_origin is not null order by 1");
                    resultset=stat.executeQuery();
                    while(resultset.next()){
                        
                        Cntry_Origin.add(resultset.getString("cntry_origin"));
                    }
                    
                 PreparedStatement stat1 =con.prepareStatement("select distinct exp_type from ei_endors_mast where exp_type is not null order by 1");
                 ResultSet resultset1=stat1.executeQuery();
                    while(resultset1.next()){
                        
                        Exp_Type.add(resultset1.getString("exp_type"));
                    }              
		}
	
		catch(Exception se)
		{
			System.out.println("SQLExceptionEPTrackReport"+se);
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


    public List getCompany_LIST() {
        return Company_LIST;
    }

    public void setCompany_LIST(List Company_LIST) {
        this.Company_LIST = Company_LIST;
    }

    public List getCntry_Origin() {
        return Cntry_Origin;
    }

    public void setCntry_Origin(List Cntry_Origin) {
        this.Cntry_Origin = Cntry_Origin;
    }

    public List getExp_Type() {
        return Exp_Type;
    } 

    public String getCntry_orgin() {
        return cntry_orgin;
    }

    public void setCntry_orgin(String cntry_orgin) {
        this.cntry_orgin = cntry_orgin;
    }

    public void setExp_Type(List Exp_Type) {
        this.Exp_Type = Exp_Type;
    }
 
   

   

   

   

 
    


   
	  
}
    