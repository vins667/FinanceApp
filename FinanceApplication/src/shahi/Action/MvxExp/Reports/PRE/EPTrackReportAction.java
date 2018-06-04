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

public final class EPTrackReportAction extends ActionSupport{

	private String date1;
	private String date2;
        private String LOCT_CODE;
	private String filetype;
	private String abc;
        private String aausrid;
        private String repFlag;
	private List AC_LIST=new ArrayList();;
	private List TR_LIST=new ArrayList();;
        private List PORTLIST = new ArrayList();
	private List AGENT_LIST = new ArrayList();
        private List LICLIST = new ArrayList();
        private List cntryList = new ArrayList();
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
		         if (repFlag.equals("EPTRACK")) 
                         {
                                stat=con.prepareStatement("select distinct ac_holder from ei_endors_mast a,ei_sbill_track b ,ei_shipment_dtls c ,ei_bos_transit D where ac_holder is not null and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and c.shp_bill_no=b.shp_bill_no and c.shp_bill_Date=c.shp_bill_date and a.loading=d.port_code and trunc(b.tr_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND mlfs_loct LIKE ? and ac_holder is not null  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                     AC_LIST.add(resultset.getString("ac_holder"));
                                }
                                
                              
                                stat=con.prepareStatement("select distinct TYPE_DESC||'-'||A.TR_TYPE typedesc,A.tr_type  from ei_sbill_track a,ei_grup_type_Dtls b,ei_shipment_dtls c,ei_endors_mast d,ei_bos_transit E"+
                                                         " where a.tr_type=b.type_code and b.grup_type_code='SBF' and c.year=d.year and c.company=d.company and c.inv_no=d.inv_no and a.shp_bill_no=c.shp_bill_no and a.shp_bill_date=c.shp_bill_Date and d.loading=E.port_code and trunc(A.tr_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND e.mlfs_loct LIKE ? order by 1");
                                stat.setString(1, date1);
                                stat.setString(2, date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                       TR_LIST.add(new GetListBean(resultset.getString("tr_type"),resultset.getString("typedesc")));      
                                }
                                
                          }   
                         if (repFlag.equals("EPREP")) 
                         {
                             
                                stat=con.prepareStatement("select distinct ac_holder from ei_endors_mast a,ei_shipment_dtls c ,ei_bos_transit D where ac_holder is not null and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and  a.loading=d.port_code and trunc(c.shp_bill_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND mlfs_loct LIKE ? and ac_holder is not null order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        AC_LIST.add(resultset.getString("ac_holder"));
                                }
                         
                                stat=con.prepareStatement("select distinct loading from ei_endors_mast a,ei_shipment_dtls c ,ei_bos_transit D where loading is not null and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and  a.loading=d.port_code and trunc(c.shp_bill_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND mlfs_loct LIKE ? order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        PORTLIST.add(resultset.getString("loading"));
                                }
                            
                                stat=con.prepareStatement("select distinct IDSUNO||' - '||IDSUNM AGENTDESC, agent from ei_endors_mast a,seplweb.CIDMAS_view115 A1,ei_shipment_dtls c ,ei_bos_transit D where a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and  a.loading=d.port_code and trunc(c.shp_bill_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND mlfs_loct LIKE ? AND A1.IDCONO=111 AND A.AGENT=A1.IDSUNO   order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                      AGENT_LIST.add(new GetListBean(resultset.getString("agent"),resultset.getString("AGENTDESC")));      
                         
                                }
                            
                                stat=con.prepareStatement("select distinct ref_no from ei_endors_lc_lic_dtls a,ei_endors_mast b,ei_shipment_dtls c ,ei_bos_transit D where ref_no is not null and a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.year=c.year and a.company=c.company and a.inv_no=c.inv_no and  "
                                                      + "  b.loading=d.port_code and trunc(c.shp_bill_date) between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND mlfs_loct LIKE ? order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        LICLIST.add(resultset.getString("ref_no"));
                                } 
                                 stat=con.prepareStatement("select distinct CNTRY_ORIGIN from ei_endors_mast a where CNTRY_ORIGIN is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                      cntryList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("CNTRY_ORIGIN"),resultset.getString("CNTRY_ORIGIN")));
                         
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

    public List getAC_LIST() {
        return AC_LIST;
    }

    public void setAC_LIST(List AC_LIST) {
        this.AC_LIST = AC_LIST;
    }

    public List getTR_LIST() {
        return TR_LIST;
    }

    public void setTR_LIST(List TR_LIST) {
        this.TR_LIST = TR_LIST;
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

    public List getPORTLIST() {
        return PORTLIST;
    }

    public void setPORTLIST(List PORTLIST) {
        this.PORTLIST = PORTLIST;
    }

    public List getAGENT_LIST() {
        return AGENT_LIST;
    }

    public void setAGENT_LIST(List AGENT_LIST) {
        this.AGENT_LIST = AGENT_LIST;
    }

     

    public List getLICLIST() {
        return LICLIST;
    }

    public void setLICLIST(List LICLIST) {
        this.LICLIST = LICLIST;
    }

    public List getCntryList() {
        return cntryList;
    }

    public void setCntryList(List cntryList) {
        this.cntryList = cntryList;
    }
	
	  
}
   