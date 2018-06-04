package shahi.Action.MvxExp.Reports.PRE;

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
import shahi.Action.MvxExp.PRE.dao.BillOfSalesMasterDao;

public final class BOSPrintAction extends ActionSupport{

	private String date1;
	private String date2;
        private String LOCT_CODE;
        private String loctcode;
	private String filetype;
	private String abc;
        private String aausrid;
        private String repFlag;
        private String BOS_FROM;
        private String BOS_TO;
	private List warehouselist = new ArrayList();
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
                    
                       stat= con.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                       stat.setString(1,usrid);
                       resultset=stat.executeQuery();
                       while (resultset.next())
                        {  loctcode=resultset.getString("location_code");
                        }
             
                
                    warehouselist = new BillOfSalesMasterDao().getWareHouseList(usrid);
                   
                  if(date1!=null && date1.length()>0 && date2!=null && date2.length()>0)
		{  
		         if (repFlag.equals("BOS")) 
                         {
                                stat=con.prepareStatement("select min(bos_no) bos_no_from ,max(bos_no) bos_no_to from ei_bos_mast a where trunc(a.bos_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND bos_loct LIKE ? ");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                      BOS_FROM=resultset.getString("bos_no_from");
                                      BOS_TO=resultset.getString("bos_no_to");
                                }
                                
                        
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

    public String getBOS_FROM() {
        return BOS_FROM;
    }

    public void setBOS_FROM(String BOS_FROM) {
        this.BOS_FROM = BOS_FROM;
    }

    public String getBOS_TO() {
        return BOS_TO;
    }

    public void setBOS_TO(String BOS_TO) {
        this.BOS_TO = BOS_TO;
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

    public String getRepFlag() {
        return repFlag;
    }

    public void setRepFlag(String repFlag) {
        this.repFlag = repFlag;
    }

   

    public String getLoctcode() {
        return loctcode;
    }

    public void setLoctcode(String loctcode) {
        this.loctcode = loctcode;
    }

    public List getWarehouselist() {
        return warehouselist;
    }

    public void setWarehouselist(List warehouselist) {
        this.warehouselist = warehouselist;
    }
	
	    
}
     