package shahi.Action.MvxExp.Reports.PRE;

import static com.opensymphony.xwork2.Action.ERROR;
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

public final class ToUpdatedAction extends ActionSupport{

	private String date1;
	private String date2;
	private String filetype;
	private String abc;
        private String aausrid;
        private String LOCT_CODE;
	private List PROC_LIST= new ArrayList();
	private List COUNTRY_LIST =new ArrayList();
	private List CURRENCY_LIST= new ArrayList();
	private List MODE_LIST= new ArrayList();
        private List COSTCENTER_LIST= new ArrayList();
	
	public String execute() throws SQLException
	{
		PROC_LIST=new ArrayList<String>();
		COUNTRY_LIST=new ArrayList();
		CURRENCY_LIST=new ArrayList();
		MODE_LIST=new ArrayList();
		 
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
                      
			//if(date1!=null && date1.length()>0 && date2!=null && date2.length()>0)
			//{
		
                                stat=con.prepareStatement("select distinct exp_type from ei_endors_mast where LOCATION LIKE ? order by 1");
                                stat.setString(1,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        PROC_LIST.add(resultset.getString("exp_type"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                   resultset.close();
                           
                                stat=con.prepareStatement("select distinct CNTRY_ORIGIN from ei_endors_mast where cntry_origin is not null AND LOCATION LIKE ? order by 1");
                                stat.setString(1,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        COUNTRY_LIST.add(resultset.getString("CNTRY_ORIGIN"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                        resultset.close();
                        
                                stat=con.prepareStatement("select distinct crncy_code from ei_endors_mast where LOCATION LIKE ? order by 1");
                                stat.setString(1,LOCT_CODE);
                                resultset=stat.executeQuery();
                           
                                while(resultset.next())
                                {
                                        CURRENCY_LIST.add(resultset.getString("crncy_code"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                        resultset.close();

                                stat=con.prepareStatement("select distinct mode_of_ship from ei_endors_mast where mode_of_ship is not null and LOCATION LIKE ? order by 1");
                                stat.setString(1,LOCT_CODE);
                             
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        MODE_LIST.add(resultset.getString("mode_of_ship"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                        resultset.close();
                                                               
                                 
                                
                              PreparedStatement  stat2=con.prepareStatement("select distinct COST_CENTRE from ei_endors_mast where LOCATION LIKE ? order by 1");
                                stat2.setString(1,LOCT_CODE);
                               ResultSet resultset2=stat2.executeQuery();
                                while(resultset2.next())
                                {
                                        COSTCENTER_LIST.add(resultset2.getString("COST_CENTRE"));
                                }
                                if(stat2!=null)
                                        stat2.close();
                                if(resultset2!=null)
                                        resultset2.close();
                                
                                
		//}          
		}                
	  
		catch(SQLException se) 
		{
			System.out.println("SQLExceptionMvxSalesReport"+se);
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


	  


	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}


	public String getAbc() {
		return abc;
	}


	public void setAbc(String abc) {
		this.abc = abc;
	}

    public String getLOCT_CODE() {
        return LOCT_CODE;
    }

    public void setLOCT_CODE(String LOCT_CODE) {
        this.LOCT_CODE = LOCT_CODE;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public List getPROC_LIST() {
        return PROC_LIST;
    }

    public void setPROC_LIST(List PROC_LIST) {
        this.PROC_LIST = PROC_LIST;
    }

    public List getCOUNTRY_LIST() {
        return COUNTRY_LIST;
    }

    public void setCOUNTRY_LIST(List COUNTRY_LIST) {
        this.COUNTRY_LIST = COUNTRY_LIST;
    }

    public List getCURRENCY_LIST() {
        return CURRENCY_LIST;
    }

    public void setCURRENCY_LIST(List CURRENCY_LIST) {
        this.CURRENCY_LIST = CURRENCY_LIST;
    }

    public List getMODE_LIST() {
        return MODE_LIST;
    }

    public void setMODE_LIST(List MODE_LIST) {
        this.MODE_LIST = MODE_LIST;
    }

    public List getCOSTCENTER_LIST() {
        return COSTCENTER_LIST;
    }

    public void setCOSTCENTER_LIST(List COSTCENTER_LIST) {
        this.COSTCENTER_LIST = COSTCENTER_LIST;
    }

     
	  
}
  