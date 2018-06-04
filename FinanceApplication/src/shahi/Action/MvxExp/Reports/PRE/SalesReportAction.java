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

public final class SalesReportAction extends ActionSupport{

	private String date1;
	private String date2;
	private String filetype;
	private String abc;
        private String aausrid;
        private String LOCT_CODE;
        private String self_tp; 
       
	List PROC_LIST; 
	List COUNTRY_LIST;
	List CURRENCY_LIST; 
        List DESTN_CNTRY_LIST;
	List MODE_LIST;
	List BUYER_LIST;
	List PCH_LIST = new ArrayList();
	public String execute() throws SQLException
	{
		PROC_LIST=new ArrayList<String>();
		COUNTRY_LIST=new ArrayList();
                DESTN_CNTRY_LIST= new ArrayList();
		CURRENCY_LIST=new ArrayList();
		MODE_LIST=new ArrayList();
		BUYER_LIST=new ArrayList();
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
		         
                                stat=con.prepareStatement("select distinct exp_type from ei_endors_mast where exp_type is not null and trunc(t_o_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND LOCATION LIKE ? order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        PROC_LIST.add(resultset.getString("exp_type"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                   resultset.close();
                           
                                stat=con.prepareStatement("select distinct CNTRY_ORIGIN from ei_endors_mast where cntry_origin is not null and  trunc(t_o_date) between  to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND LOCATION LIKE ? order by 1");
                                stat.setString(1, date1);
                                stat.setString(2, date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        COUNTRY_LIST.add(resultset.getString("CNTRY_ORIGIN"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                        resultset.close(); 
                         
                                    stat=con.prepareStatement("select distinct desti_cntry from ei_endors_mast where desti_cntry is not null and  trunc(t_o_date) between  to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND LOCATION LIKE ? order by 1");
                                stat.setString(1, date1);
                                stat.setString(2, date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                       DESTN_CNTRY_LIST.add(resultset.getString("desti_cntry"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                        resultset.close(); 
                            
                                stat=con.prepareStatement("select distinct crncy_code from ei_endors_mast where crncy_code is not null and trunc(t_o_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND LOCATION LIKE ? order by 1");
                                stat.setString(1, date1);
                                stat.setString(2, date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                           
                                while(resultset.next())
                                {
                                        CURRENCY_LIST.add(resultset.getString("crncy_code"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                        resultset.close();
 
                                stat=con.prepareStatement("select distinct mode_of_ship from ei_endors_mast where mode_of_ship is not null and trunc(t_o_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND LOCATION LIKE ? order by 1");
                                stat.setString(1, date1);
                                stat.setString(2, date2);
                                stat.setString(3,LOCT_CODE);
                             
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        MODE_LIST.add(resultset.getString("mode_of_ship"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                        resultset.close();
                                stat=con.prepareStatement("select distinct buyer from ei_endors_mast where buyer is not null and trunc(t_o_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND LOCATION LIKE ? order by 1");
                                stat.setString(1, date1);
                                stat.setString(2, date2);
                                stat.setString(3,LOCT_CODE);
                          
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        BUYER_LIST.add(resultset.getString("buyer"));
                                }
                                 
                                stat=con.prepareStatement("select distinct cost_centre from ei_endors_mast where cost_centre is not null and trunc(t_o_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND LOCATION LIKE ? order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                   
                                while(resultset.next()) 
                                {
                                        PCH_LIST.add(resultset.getString("cost_centre"));
                                }
                                 
		}          
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


	public List getPROC_LIST() {
		return PROC_LIST;
	}
	public void setPROC_LIST(List pROC_LIST) {
		PROC_LIST = pROC_LIST;
	}
	public List getCOUNTRY_LIST() {
		return COUNTRY_LIST;
	}
	public void setCOUNTRY_LIST(List cOUNTRY_LIST) {
		COUNTRY_LIST = cOUNTRY_LIST;
	}
	public List getCURRENCY_LIST() {
		return CURRENCY_LIST;
	}
	public void setCURRENCY_LIST(List cURRENCY_LIST) {
		CURRENCY_LIST = cURRENCY_LIST;
	}
	public List getMODE_LIST() {
		return MODE_LIST;
	}
	public void setMODE_LIST(List mODE_LIST) {
		MODE_LIST = mODE_LIST;
	}
	public List getBUYER_LIST() {
		return BUYER_LIST;
	}
	public void setBUYER_LIST(List bUYER_LIST) {
		BUYER_LIST = bUYER_LIST;
	}


	public String getFiletype() {
		return filetype;
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

    public List getPCH_LIST() {
        return PCH_LIST;
    }

    public void setPCH_LIST(List PCH_LIST) {
        this.PCH_LIST = PCH_LIST;
    }

    public String getSelf_tp() {
        return self_tp;
    }

    public void setSelf_tp(String self_tp) {
        this.self_tp = self_tp;
    }

    public List getDESTN_CNTRY_LIST() {
        return DESTN_CNTRY_LIST;
    }

    public void setDESTN_CNTRY_LIST(List DESTN_CNTRY_LIST) {
        this.DESTN_CNTRY_LIST = DESTN_CNTRY_LIST;
    }

    
 	 
 
    
    
}
  