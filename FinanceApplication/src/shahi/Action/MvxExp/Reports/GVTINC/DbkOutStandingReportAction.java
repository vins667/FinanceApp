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
import shahi.Action.MvxExp.Reports.GVTINC.bean.DBKOUTBEAN;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Map; 
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DbkOutStandingReportAction extends ActionSupport{

	private String date1;
	private String date2;
       
	private String abc;
        private String bbb;
        private String aausrid;
        private String LOCT_CODE;
        private String self_tp;
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
	 
        
        public static List generateReport(String P_LOCT, String PARAMETER,String DATEFROM,String DATETO,String INVTYPE,String BASEDON,String LESSEXCESS,String INCTYPE,String P_AMT,String REPTYPE){
        Connection conn = null;
        PreparedStatement stat = null;
        PreparedStatement stat1 = null;
        ResultSet result = null;
        ResultSet result1 = null;
        
        List<DBKOUTBEAN> beans = new ArrayList<DBKOUTBEAN>();
        try {
            conn = new connection().getConnection();
        } catch (Exception e) {
            System.out.println("DbkOutStandingReportAction.class " + e);
        }
        try {    
             
            System.out.println("LOCT "+P_LOCT+" Parameter " + PARAMETER);
            System.out.println("DATE " + DATEFROM+" - "+DATETO+" INVTYPE "+INVTYPE+" BASED ON "+BASEDON+" LESS/EXCESS "+LESSEXCESS+" INCTYPE "+INCTYPE);
          if (REPTYPE.equals("DUE"))
          {
                    if (INCTYPE.equals("DBK") && LESSEXCESS.equals("Excess"))
                    {
                      PARAMETER=PARAMETER+" AND nvl(dbk_adjusted,0)=0 and nvl(A.dbk_received,0)=0 and (nvl(A.dbk_admisable,0) - nvl(A.dbk_misc_amount,0))>="+P_AMT;
                    }
                     if (INCTYPE.equals("DBK") && LESSEXCESS.equals("Less"))
                    {
                      PARAMETER=PARAMETER+" AND  nvl(dbk_adjusted,0)=0 and nvl(A.dbk_received,0)=0 and (nvl(A.dbk_admisable,0) - nvl(A.dbk_misc_amount,0))>="+P_AMT;
                    }
                    if (INCTYPE.equals("STR") && LESSEXCESS.equals("Excess"))
                    {
                      PARAMETER=PARAMETER+" AND  nvl(A.str_recv,0)=0 and (nvl(A.str_due,0)-nvl(a.str_woff,0))>="+P_AMT;
                    }
                    if (INCTYPE.equals("STR") && LESSEXCESS.equals("Less"))
                    {
                      PARAMETER=PARAMETER+" AND  nvl(A.str_recv,0)=0 and (nvl(A.str_due,0)-nvl(a.str_woff,0))>="+P_AMT;
                    }
          } 
           if (REPTYPE.equals("RECV"))
          {
                    if (INCTYPE.equals("DBK") && LESSEXCESS.equals("Excess"))
                    {
                      PARAMETER=PARAMETER+" AND  NVL(DBK_RECEIVED,0)>0  and ((NVL(DBK_RECEIVED,0)+NVL(DBK_SUPL_RECV,0)+NVL(DBK_ADJUSTED,0))-NVL(DBK_ADMISABLE,0))>="+P_AMT;
                    }
                     if (INCTYPE.equals("DBK") && LESSEXCESS.equals("Less"))
                    {
                      PARAMETER=PARAMETER+" AND  NVL(DBK_RECEIVED,0)>0  and (NVL(DBK_ADMISABLE,0)-(NVL(DBK_RECEIVED,0)+NVL(DBK_SUPL_RECV,0)+NVL(DBK_ADJUSTED,0)+nvl(woff_amt,0)))>="+P_AMT;
                    }
                    if (INCTYPE.equals("STR") && LESSEXCESS.equals("Excess"))
                    {
                      PARAMETER=PARAMETER+" AND  nvl(A.str_recv,0)>0 and ((nvl(str_recv,0)+nvl(a.str_woff,0))-nvl(A.str_due,0))>="+P_AMT;
                    }
                    if (INCTYPE.equals("STR") && LESSEXCESS.equals("Less"))
                    {
                      PARAMETER=PARAMETER+" AND  nvl(A.str_recv,0)>0 and (nvl(A.str_due,0)-(nvl(str_recv,0)+nvl(a.str_woff,0)))>"+P_AMT;
                    }
          }
           
            System.out.println("Parameter "+PARAMETER);
           double ddue=0.0,drecv=0.0,dsupl=0.0,dadjust=0.0,dwoff=0.0,sdue=0.0,srecv=0.0,swoff=0.0;
           String v_sbno="", v_sbdt=""; 
            stat = conn.prepareStatement(" select  A.shp_bill_no,to_char(A.shp_bill_date,'dd/mm/yyyy') shp_bill_date,nvl(A.dbk_admisable,0) dbkdue,str_due,str_woff,str_recv,dbk_supl_recv,dbk_received,dbk_adjusted,woff_amt, " +
                                        " E.awb_no,to_char(e.awb_date,'dd/mm/yyyy') awb_date,claim_port,buyer,excs_inv_no,trim(agent) agent,trim(fwd_code) fwd_code  " +
                                        " from ei_dbk_mast A,ei_shipment_dtls B,ei_endors_mast C,ei_shipment_mast E " +
                                        " where  A.shp_bill_no=B.shp_bill_no   " +
                                        "       and A.shp_bill_date=B.shp_bill_date " +
                                        "	and b.year = e.year " +
                                        "	and b.link_no = e.link_no " +
                                        "	and decode(?,'SB',A.shp_bill_date,E.awb_date) between ? and ? " +
                                        "	and B.year=C.year " +
                                        "	and B.company=C.company " +
                                        "	and B.inv_no=C.inv_no " +
                                        "	and c.location like ?   " +
                                        "	and nvl(C.self_tp,'N') like ? " +PARAMETER+"  order by 1,2  ");
                                stat.setString(1,BASEDON); 
                                stat.setString(2,DATEFROM);
                                stat.setString(3,DATETO);
                                stat.setString(4,P_LOCT);
                                stat.setString(5,INVTYPE);
                            
                                result=stat.executeQuery();   
            while (result.next()) { 
                DBKOUTBEAN  bean = new DBKOUTBEAN();  
                
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
                     if (v_sbno.equals(result.getString("SHP_BILL_NO")) )
                    {
                       ddue=0.0;
                       drecv=0.0;
                       dsupl=0.0;
                       dadjust=0.0;
                       dwoff=0.0;
                       sdue=0.0;
                       srecv=0.0;
                       swoff=0.0;
                    }else
                     {
                       ddue=result.getDouble("dbkdue");
                       drecv=result.getDouble("dbk_received");
                       dsupl=result.getDouble("dbk_supl_recv");
                       dadjust=result.getDouble("dbk_adjusted");
                       dwoff=result.getDouble("woff_amt");
                       sdue=result.getDouble("STR_DUE");
                       srecv=result.getDouble("STR_RECV");
                       swoff=result.getDouble("STR_WOFF");
                     
                     }
                        
                        stat1 = conn.prepareStatement(" select idcono,idsuno,idsunm   from cidmas_m4off where  idcono=111 and trim(idsuno)=? ");
                        stat1.setString(1,result.getString("agent"));
                        result1=stat1.executeQuery();
                        if (result1.next())
                        {
                         bean.setCHA_NAME(result1.getString("idsunm"));
                        }else{bean.setCHA_NAME(result.getString("agent"));}
                         if(result1!=null) result1.close();
                         if(stat1!=null) stat1.close();
                         
                        stat1 = conn.prepareStatement(" select idcono,idsuno,idsunm   from cidmas_m4off where  idcono=111 and trim(idsuno)=? ");
                        stat1.setString(1,result.getString("fwd_code"));
                        result1=stat1.executeQuery();
                        if (result1.next())
                        {
                         bean.setFWD_NAME(result1.getString("idsunm"));
                        }else{bean.setFWD_NAME(result.getString("fwd_code"));}
                         if(result1!=null) result1.close();
                         if(stat1!=null) stat1.close();
                         
                         String MCFS="";
                         
                        stat1 = conn.prepareStatement(" SELECT a.bos_no,cfs_code,type_desc from ei_bos_mast a,ei_bos_dtls b,ei_GRUP_type_dTLS c where a.bos_no=b.bos_no and a.bos_loct=b.bos_location  and cfs_code is not null  "+
                                                      "  and nvl(b.dispatch_yn,'N')='Y' and a.cfs_code=c.type_code and c.grup_type_code='CFS' AND b.excs_inv_no=?");
                        stat1.setString(1,result.getString("excs_inv_no"));
                        result1=stat1.executeQuery();
                        if (result1.next())
                        {
                         bean.setCFS_NAME(result1.getString("type_desc"));
                        } 
                         if(result1!=null) result1.close();
                         if(stat1!=null) stat1.close();
                         bean.setCFS_NAME(MCFS); 
                        bean.setSHP_BILL_NO(result.getString("SHP_BILL_NO"));
                        bean.setSHP_BILL_DATE(result.getString("SHP_BILL_DATE"));
                        bean.setCLAIM_PORT(result.getString("claim_port"));
                        bean.setAWB_NO(result.getString("awb_no"));
                        bean.setAWB_DATE(result.getString("awb_date"));
                        bean.setBUYER(result.getString("buyer"));
                        bean.setEXCS_INV_NO(result.getString("EXCS_INV_NO"));
                       
                        
                        bean.setDBK_DUE(ddue);
                        bean.setDBK_RECV(drecv);
                        bean.setDBK_SUPL(dsupl);
                        bean.setDBK_ADJUST(dadjust);
                        bean.setDBK_WOFF(dwoff);
                        bean.setSTR_DUE(sdue);
                        bean.setSTR_RECV(srecv);
                        bean.setSTR_WOFF(swoff); 
                        v_sbno=result.getString("SHP_BILL_NO");
                        v_sbdt=result.getString("SHP_BILL_DATE");
                  
                beans.add(bean);
            }
            if(result!=null) result.close();
            if(stat!=null) stat.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
            System.out.println("DbkOutStandingReportAction.class " + se);
        } catch (Exception e) {
            System.out.println("DbkOutStandingReportAction.class " + e);
        } finally {
            if (conn != null) {
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DbkOutStandingReportAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return beans;
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

    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bbb) {
        this.bbb = bbb;
    }

    public String getLOCT_CODE() {
        return LOCT_CODE;
    }

    public void setLOCT_CODE(String LOCT_CODE) {
        this.LOCT_CODE = LOCT_CODE;
    }

    public String getSelf_tp() {
        return self_tp;
    }

    public void setSelf_tp(String self_tp) {
        this.self_tp = self_tp;
    }

  
    


   
	  
}
    