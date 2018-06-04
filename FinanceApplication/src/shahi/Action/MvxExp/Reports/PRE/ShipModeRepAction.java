
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

public  class ShipModeRepAction extends ActionSupport{

	private String date1;
	private String date2;
        private String LOCT_CODE;
        private String loct;
	private String filetype;
	private String abc;
        private String aausrid;
        private String repFlag;
	private List pchList=new ArrayList();;
	private List buyerList=new ArrayList();;
        private List cntryList = new ArrayList();
	private List expList = new ArrayList();
        private List crncyList = new ArrayList();
        private List merchantList = new ArrayList();
        private List mosList = new ArrayList();
        private List delvList = new ArrayList();
        private List DestiList = new ArrayList();
        private List DischargeList = new ArrayList();
        
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
	       if(date1!=null && date1.length()>0 && date2!=null && date2.length()>0)
		{  
		         if (repFlag.equals("MOS")) 
                         {
                               
                               stat=con.prepareStatement("select distinct cost_centre from ei_endors_mast a where cost_centre is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    
                                    pchList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("cost_centre"),resultset.getString("cost_centre")));
                                }
                                stat=con.prepareStatement("select distinct buyer from ei_endors_mast a where awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    buyerList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("buyer"),resultset.getString("buyer")));
                                }
                                stat=con.prepareStatement("select distinct exp_type from ei_endors_mast a where exp_type is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    expList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("exp_type"),resultset.getString("exp_type")));
                         
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
                                
                                stat=con.prepareStatement("select distinct crncy_code from ei_endors_mast a where crncy_code is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   crncyList.add(resultset.getString("crncy_code"));
                                   }
                                stat=con.prepareStatement("select distinct MERCHANT_NAME from ei_endors_mast a where merchant_name is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                      merchantList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("MERCHANT_NAME"),resultset.getString("MERCHANT_NAME")));
                               }
                                stat=con.prepareStatement("select distinct mode_of_ship from ei_endors_mast a where mode_of_ship is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    mosList.add(resultset.getString("mode_of_ship"));
                                }
                                stat=con.prepareStatement("select distinct desti_cntry from ei_endors_mast a where desti_cntry is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                     
                                    delvList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("desti_cntry"),resultset.getString("desti_cntry")));
                         
                             
                                }
                                stat=con.prepareStatement("select distinct desti_code from ei_endors_mast a where desti_code is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    DestiList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("desti_code"),resultset.getString("desti_code")));
                         
                                }
                                
                                 stat=con.prepareStatement("select distinct discharge from ei_endors_mast a where discharge is not null and awbdate between to_date(?,'dd/MM/yyyy') and to_date(?,'dd/MM/yyyy') AND location LIKE ?  order by 1");
                                stat.setString(1,date1);
                                stat.setString(2,date2);
                                stat.setString(3,LOCT_CODE);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {   
                                    DischargeList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("discharge"),resultset.getString("discharge")));
                         
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
			System.out.println("SQLExceptionShipModeRepAction"+se);
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

    public List getCntryList() {
        return cntryList;
    }

    public void setCntryList(List cntryList) {
        this.cntryList = cntryList;
    }

    public List getExpList() {
        return expList;
    }

    public void setExpList(List expList) {
        this.expList = expList;
    }

    public List getCrncyList() {
        return crncyList;
    }

    public void setCrncyList(List crncyList) {
        this.crncyList = crncyList;
    }

    public List getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List merchantList) {
        this.merchantList = merchantList;
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

    public List getDelvList() {
        return delvList;
    }

    public void setDelvList(List delvList) {
        this.delvList = delvList;
    }

    public String getLoct() {
        return loct;
    }

    public void setLoct(String loct) {
        this.loct = loct;
    }

    public List getDestiList() {
        return DestiList;
    }

    public void setDestiList(List DestiList) {
        this.DestiList = DestiList;
    }

    public List getDischargeList() {
        return DischargeList;
    }

    public void setDischargeList(List DischargeList) {
        this.DischargeList = DischargeList;
    }
    
     
}
    