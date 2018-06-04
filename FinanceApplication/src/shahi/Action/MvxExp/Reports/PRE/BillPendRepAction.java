
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

public  class BillPendRepAction extends ActionSupport{

	private String date1;
	private String date2;
        private String LOCT_CODE;
        private String loct;
	private String filetype;
	private String abc;
        private String aausrid;
        private String repFlag;
        private String P_CH;
	private List pchList=new ArrayList();;
	private List buyerList=new ArrayList();;
        private List chaList = new ArrayList();
	private List fwdList = new ArrayList();
        private List acList = new ArrayList();
        private List mosList = new ArrayList();
        
        
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
              stat = con.prepareStatement("select  location_code from seh_web_users where user_id=? ");
              stat.setString(1, usrid);
              resultset= stat.executeQuery();
              while (resultset.next())
              {
                    loct = resultset.getString(1);
                 }
              String sqlstr=" ";
	       if(date1!=null && date1.length()>0 && date2!=null && date2.length()>0)
		{  
		         if (repFlag.equals("YES")) 
                         {
                             if (P_CH.equals("PRE"))
                             {  sqlstr=" and pre_docs_sent is null ";}
                             else{sqlstr=" and doc_send is null ";}
                             
                               stat=con.prepareStatement("select distinct cost_centre from ei_endors_mast a where cost_centre is not null and etd_date between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ? "+sqlstr+" order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    
                                    pchList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("cost_centre"),resultset.getString("cost_centre")));
                                } 
                                stat=con.prepareStatement("select distinct buyer from ei_endors_mast a where etd_date between  to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ? "+sqlstr+" order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    buyerList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("buyer"),resultset.getString("buyer")));
                                }
                                stat=con.prepareStatement("select distinct ac_holder from ei_endors_mast a where ac_holder is not null and  etd_date between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ? "+sqlstr+" order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    acList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("ac_holder"),resultset.getString("ac_holder")));
                         
                                }
                                stat=con.prepareStatement("select distinct agent,idsunm||' - '||agent agentdesc from ei_endors_mast a,cidmas_m4 b where agent is not null and b.idcono=111 and b.idsuno=a.agent and  etd_date between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?   "+sqlstr+" order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {    
                                      chaList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("agent"),resultset.getString("agentdesc")));
                         
                                }
                                
                               
                                stat=con.prepareStatement("select distinct fwd_code,idsunm||' - '||fwd_code fwddesc from ei_endors_mast a ,cidmas_m4 b where fwd_code is not null and b.idcono=111 and b.idsuno=a.fwd_code and etd_date between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ? "+sqlstr+" order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                     fwdList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("fwd_code"),resultset.getString("fwddesc")));
                               } 
                                stat=con.prepareStatement("select distinct loading from ei_endors_mast a where loading is not null and etd_date between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  "+sqlstr+" order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE); 
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    mosList.add(resultset.getString("loading"));
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
			System.out.println("SQLExceptionBillPendRepAction"+se);
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

    public List getPchList() {
        return pchList;
    }

    public void setPchList(List pchList) {
        this.pchList = pchList;
    }

    public List getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List buyerList) {
        this.buyerList = buyerList;
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

    public List getMosList() {
        return mosList;
    }

    public void setMosList(List mosList) {
        this.mosList = mosList;
    }

    

    public String getLoct() {
        return loct;
    }

    public void setLoct(String loct) {
        this.loct = loct;
    }

   

    public String getP_CH() {
        return P_CH;
    }

    public void setP_CH(String P_CH) {
        this.P_CH = P_CH;
    }

    public List getChaList() {
        return chaList;
    }

    public void setChaList(List chaList) {
        this.chaList = chaList;
    }

    public List getFwdList() {
        return fwdList;
    }

    public void setFwdList(List fwdList) {
        this.fwdList = fwdList;
    }

    public List getAcList() {
        return acList;
    }

    public void setAcList(List acList) {
        this.acList = acList;
    }
     
     
}
    