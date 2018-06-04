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
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.SimpleFormatter;
import shahi.Action.MvxExp.Reports.PRE.bean.CHA_LISTBean;

public final class DispatchReportAction extends ActionSupport{

	private String date1;
	private String date2;
       
	private String abc;
        private String aausrid;
       
       
        private List PORTLIST = new ArrayList();
	private List PCH_LIST = new ArrayList();
        private List CHA_LIST=new ArrayList();
        private List Company_LIST = new ArrayList();
        private List Bank_List = new ArrayList();
        private List LOC_LIST = new ArrayList();
        private List BOS_LIST=new ArrayList();
        private List BUYER_LIST = new ArrayList();
        private List UNIT_LIST=new ArrayList();
        private List VCH_LIST=new ArrayList();
        private String LOCT;
        private String BOS_LOCT;
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
                    LOCT=LOCATION_CODE;
                    stat=con.prepareStatement("select distinct bos_loct from ei_bos_mast order by 1");
                    resultset=stat.executeQuery();
                    while(resultset.next())
                     {
                        LOC_LIST.add(resultset.getString("bos_loct"));
                      }
                                
                                
                      if(date1!=null && date1.length()>0 && date2!=null && date2.length()>0)
                        {  
                      
                            String d1=date1;
                            String d2=date2;
                            String dt1= d1.substring(0, 10);
                            String dt2= d2.substring(0, 10);
                            SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-yyyy"); 
                            SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yy");
                            

                                stat=con.prepareStatement("select distinct port from ei_bos_mast where port is not null and vch_dep_date between ? and ? and bos_loct=? order by 1");
                                stat.setString(1,dateformat1.format(dateformat.parse(dt1)));
                                stat.setString(2,dateformat1.format(dateformat.parse(dt2)));
                                stat.setString(3,BOS_LOCT);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                   PORTLIST.add(resultset.getString("port"));
                                }
                                
                               stat=con.prepareStatement("select distinct sasuno,sasunm from CIDADR_M4OFF c ,ei_bos_mast b where trim(cha)=sasuno and vch_dep_date between ? and ? and bos_loct=? and C.sacono=111 and c.saadte=1 order by 2 ");
                               stat.setString(1,dateformat1.format(dateformat.parse(dt1)));
                               stat.setString(2,dateformat1.format(dateformat.parse(dt2)));
                               stat.setString(3,BOS_LOCT);
                               resultset=stat.executeQuery();
                               while(resultset.next())
                                {
                                   CHA_LIST.add(new CHA_LISTBean(resultset.getString("sasunm"),resultset.getString("sasuno")));
                                }
                 
                               stat=con.prepareStatement("select distinct buyer from  ei_bos_mast b where BUYER IS NOT NULL AND vch_dep_date between ? and ? and bos_loct=? order by 1 ");
                               stat.setString(1,dateformat1.format(dateformat.parse(dt1)));
                               stat.setString(2,dateformat1.format(dateformat.parse(dt2)));
                               stat.setString(3,BOS_LOCT);
                               resultset=stat.executeQuery();
                               while(resultset.next())
                                {
                                    BUYER_LIST.add(resultset.getString("buyer"));
                                 }
                                stat=con.prepareStatement("select distinct unit from  ei_bos_mast b where UNIT IS NOT NULL AND vch_dep_date between ? and ? and bos_loct=? order by 1 ");
                               stat.setString(1,dateformat1.format(dateformat.parse(dt1)));
                               stat.setString(2,dateformat1.format(dateformat.parse(dt2)));
                               stat.setString(3,BOS_LOCT);
                               resultset=stat.executeQuery();
                               while(resultset.next())
                                {
                                    UNIT_LIST.add(resultset.getString("unit"));
                                 }
                              stat=con.prepareStatement("select distinct VEHICLE_NO from  ei_bos_mast b where VEHICLE_NO IS NOT NULL AND vch_dep_date between ? and ? and bos_loct=? order by 1 ");
                               stat.setString(1,dateformat1.format(dateformat.parse(dt1)));
                               stat.setString(2,dateformat1.format(dateformat.parse(dt2)));
                               stat.setString(3,BOS_LOCT);
                               resultset=stat.executeQuery();
                               while(resultset.next())
                                {
                                    VCH_LIST.add(resultset.getString("VEHICLE_NO"));
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

    public List getCHA_LIST() {
        return CHA_LIST;
    }

    public void setCHA_LIST(List CHA_LIST) {
        this.CHA_LIST = CHA_LIST;
    }

    public String getBOS_LOCT() {
        return BOS_LOCT;
    }

    public void setBOS_LOCT(String BOS_LOCT) {
        this.BOS_LOCT = BOS_LOCT;
    }

    public List getBUYER_LIST() {
        return BUYER_LIST;
    }

    public void setBUYER_LIST(List BUYER_LIST) {
        this.BUYER_LIST = BUYER_LIST;
    }

    public List getUNIT_LIST() {
        return UNIT_LIST;
    }

    public void setUNIT_LIST(List UNIT_LIST) {
        this.UNIT_LIST = UNIT_LIST;
    }

    public List getVCH_LIST() {
        return VCH_LIST;
    }

    public void setVCH_LIST(List VCH_LIST) {
        this.VCH_LIST = VCH_LIST;
    }

    public String getLOCT() {
        return LOCT;
    }

    public void setLOCT(String LOCT) {
        this.LOCT = LOCT;
    }

    

   

    

 
    


   
	  
}
    