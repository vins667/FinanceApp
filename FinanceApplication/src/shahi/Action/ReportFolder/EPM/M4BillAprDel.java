package shahi.Action.ReportFolder.EPM;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import shahi.Action.database.connectiondb2;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2O;
import shahi.Action.database.ConnectionShahiHrisNew;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import shahi.Action.ReportFolder.EPM.beans.M4BInvBeans;

/**
 *
 * @author Vivek
 */
public class M4BillAprDel  extends ActionSupport {
        
    private List showList;
    private String aausrid;
    private String searchfrom;
    private String searchcode;
    private String searchinv;
    private String SUNO;
    private String INVNO;
    private String INVDATE;
    private String DIVI;
    private String WHLO;
    private String USID;
    private String REPNO;
    
    public String execute() throws Exception {
        SimpleDateFormat ts= new SimpleDateFormat("MM/dd/yyyy");
        showList = new ArrayList<M4BInvBeans>();
        int falg = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");
        //usrid="237519";
        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        try {

            Connection conn = null;

            try {
                conn = new connectiondb2().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch

            PreparedStatement stat = null;
            ResultSet result = null;
            String extendedQuery="";
            //System.out.println(searchfrom);
            Date frmDate = new Date(ts.parse(searchfrom).getTime());
            //System.out.println(frmDate);
            try { 
                    String MDIVI=null;
                    if (DIVI.equals("100") || DIVI.equals("200") || DIVI.equals("210"))
                    {
                    MDIVI="100";
                    }
                     else if (DIVI.equals("800"))
                     {MDIVI="500";
                    }  else 
                    if (DIVI.equals("520"))
                     {MDIVI="520";
                    }
                    else if (DIVI.equals("530"))
                     {MDIVI="530";
                    }else if (DIVI.equals("400"))
                      { MDIVI="400";}
                    else
                    {MDIVI=DIVI;}
                   stat = conn.prepareStatement("select APCONO, APSUNO,APSINO,APPYST,APIVDT,APCUAM,APCUCD,APRGTM,APDIVI,APWHLO,APUSID,APBTNO from cinfdbprd.supinv where APCONO='111' AND  APDIVI=? and APSUNO= ? and APSINO=? and APIVDT=to_char(?,'yyyymmdd')");
                   stat.setString(1,MDIVI);
                   stat.setString(2,searchcode);
                   stat.setString(3,searchinv);
                   stat.setDate(4, frmDate);
                   result=stat.executeQuery();
                   while(result.next()){                      
                       showList.add(new M4BInvBeans(result.getString("APSUNO").trim(), result.getString("APSINO").trim(), result.getString("APPYST").trim(), result.getString("APIVDT").trim(), result.getString("APCUAM").trim(), result.getString("APCUCD").trim(), result.getString("APRGTM").trim(),result.getString("APCONO"),result.getString("APDIVI"),result.getString("APWHLO"),result.getString("APUSID"),result.getString("APBTNO")));                       
                   }
            }
            catch (Exception e) {
                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M4BillAprDel.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M4BillAprDel.java" + e);

                System.out.println(e.toString());
            } finally {

                try {
                    if (result != null) {
                        result.close();
                    }
                    if (stat != null) {
                        stat.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }                   

                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : M4BillAprDel.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }
        return SUCCESS;
    }
    public String delete() {
        SimpleDateFormat ts = new SimpleDateFormat("yyyyMMdd");
        showList = new ArrayList<M4BInvBeans>();
        int falg = 0;
        Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        
        String usrid = (String) session.get("sessUserId");
        //usrid="237519";
        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
        try {
            Connection conn = null; 
            Connection connora = null;
            Connection connoldmvx = null;
            Connection connams = null;

            try {
                conn = new connectiondb2().getConnection();
                conn.setAutoCommit(false);
                connora = new connection().getConnection();
                connams = new ConnectionShahiHrisNew().getConnection();
                connoldmvx =new connectiondb2O().getConnection();
                                               
            } catch (Exception e) {
                System.out.println(e.toString());
            } // end catch

            PreparedStatement stato = null;
            PreparedStatement stat = null;
            PreparedStatement stat1 = null;  
            PreparedStatement stat2 = null;       
            PreparedStatement stat3 = null;
            PreparedStatement stat4 = null;
            PreparedStatement stat5 = null;
            
            int status=0;
            int status1=0;
            int status2=0;
            int status3=0;
            Date invDate = new Date(ts.parse(INVDATE).getTime());
            java.util.Date dateNow = new java.util.Date ();
            StringBuilder ddnow = new StringBuilder(ts.format(dateNow));
            
            try {   
                    String MDIVI=null;
                    if (DIVI.equals("100") || DIVI.equals("200") || DIVI.equals("210"))
                    {
                    MDIVI="100";
                    }
                    else if (DIVI.equals("800") || DIVI.equals("500") )
                     {MDIVI="500";
                    } else if (DIVI.equals("520"))
                     {MDIVI="520";
                    } else if (DIVI.equals("530"))
                     {MDIVI="530";
                    } else if (DIVI.equals("540"))
                     {MDIVI="540";
                    }else if (DIVI.equals("400"))
                      { MDIVI="400";}
                    else
                    {MDIVI=DIVI;}
                    
                    String MWHLO=null;
                     if (WHLO.equals("520") || WHLO.equals("530") )
                     {   MWHLO="100";
                     
                     }else
                     { MWHLO=WHLO;                             
                     }
                 int kk=0; 
                 int m4=0;
                 int delflg=0;
                String mon = "0";
                String year2 = "0";
                String year1 = "0";
                if (INVDATE != null && INVDATE.length() > 7) {
                    String ss =INVDATE;
                    mon = ss.substring(4,6);
                    year2 = ss.substring(0,4);
                }
                 if (mon != null && (mon.equals("01") || mon.equals("02") || mon.equals("03"))) {
                    year1 = (Integer.parseInt(year2) - 1) + "";
                } else {
                    year1 = year2;
                }
               //   System.out.println(MDIVI+"-"+INVNO+" - "+INVDATE+"-"+SUNO+" - "+mon+"-"+year1);
                  stat = conn.prepareStatement("select * from cinfdbprd.supinv where APCONO='111' and APDIVI=? and APSUNO=? and APSINO=? and APIVDT=? and appyst<>'00'");
                   // stat.setString(1, ddnow.toString());
                    stat.setString(1, MDIVI);
                    stat.setString(2, SUNO);
                    stat.setString(3, INVNO);
                    stat.setInt(4,Integer.valueOf(INVDATE));
                  //  (Integer.parseInt(year2) 
                    //stat.setDate(4,invDate);
                    ResultSet resulto = stat.executeQuery();
                   if(resulto.next()){
                      addActionError("Check Bill status in APZ100.");
                     }
                    else
                     {
                       m4=1;
                      }

            
              if (usrid.trim().equals("227352") || usrid.trim().equals("904622") || usrid.trim().equals("246385")|| usrid.trim().equals("229442"))
              {
                delflg=1; 
              }
              else
              {
                stat5 = connora.prepareStatement("select * from seplweb.M4_AUTH_DEL where trim(EMPCODE)=? and trim(DLUSID)=?");
                stat5.setString(1, usrid.trim());
                stat5.setString(2, USID.trim());
                ResultSet resulto5 = stat5.executeQuery();
                if(resulto5.next()){
                   delflg=1;                     
                }
                else
                {
                addActionError("Not Authorize to delete this invoice. ");
                }
              }                 
              //  System.out.println(usrid+"-"+USID+" - "+delflg);
                if ( m4==1 && delflg==1)
                {                                                    
                 //  System.out.print("Delete XACDIM "+MDIVI+SUNO+INVNO+REPNO);     
                    stat = conn.prepareStatement("update cinfdbprd.xacdim set xacono=999 where XACONO='111' and XADIVI=? and xayea4=? and XASUNO=? and XASINO=? ");
                   // stat.setString(1, ddnow.toString());
                    stat.setString(1, MDIVI);
                    stat.setString(2,year1 );
                    stat.setString(3, SUNO);                                           
                    stat.setString(4,INVNO);
                    status=stat.executeUpdate();
                    if(status!=0)
                    {
                         stato = connora.prepareStatement("update seplweb.m4_bill_master set account_user=null,account_date=null where  supplier_code=? and bill_no=? and to_char(bill_Date,'yyyymmdd')=to_char(?,'yyyymmdd')");            
                         stato.setString(1, SUNO);
                         stato.setString(2, INVNO);
                         stato.setDate(3, invDate);
                         status3 = stato.executeUpdate();
                        // System.out.print("Delete shahi_invoice "+MWHLO);
                        stat1 = connora.prepareStatement("delete seplweb.m4_Bill_Detail_breakup where xacono=111 and xadivi=? and xayea4=? and xasuno=? and xasino=? and report_no=?");   //to_char(xaivdt,'yyyymmdd')=to_char(?,'yyyymmdd')         
                        stat1.setString(1, MWHLO);
                        stat1.setString(2, year1);
                        stat1.setString(3, SUNO);
                        stat1.setString(4, INVNO);
                        stat1.setString(5, REPNO);
                        //stat1.setDate(4, invDate);
                        status1=stat1.executeUpdate();
                                                      
                        //   System.out.print("Deleted "+MDIVI+SUNO+INVNO+REPNO);
                     
                    }
                }
                conn.commit(); 
                connora.commit();
                connoldmvx.commit();
            } catch (Exception e) {                
                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : M4BillAprDel.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : M4BillAprDel.java" + e);

                System.out.println(e.toString());
            } finally {

                try {                   
                    if (stat != null) {
                        stat.close();
                    }
                    if (stat1 != null) {
                        stat1.close();
                    }
                    if (stat2 != null) {
                        stat2.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (connora != null) {
                        connora.close();
                    }
                    if (connoldmvx != null) {
                        connoldmvx.close();
                    }
                    if (connams != null) {
                        connams.close();
                    }
                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : M4BillAprDel.java Exception in finally block");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

            addActionError(e.getMessage());

            return INPUT;

        }
        return "delete";
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public String getSearchcode() {
        return searchcode;
    }

    public void setSearchcode(String searchcode) {
        this.searchcode = searchcode;
    }

    public String getSearchfrom() {
        return searchfrom;
    }

    public void setSearchfrom(String searchfrom) {
        this.searchfrom = searchfrom;
    }

    public String getSearchinv() {
        return searchinv;
    }

    public void setSearchinv(String searchinv) {
        this.searchinv = searchinv;
    }

    public List getShowList() {
        return showList;
    }

    public void setShowList(List showList) {
        this.showList = showList;
    }

    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getSUNO() {
        return SUNO;
    }

    public void setSUNO(String SUNO) {
        this.SUNO = SUNO;
    }

    public String getINVDATE() {
        return INVDATE;
    }

    public void setINVDATE(String INVDATE) {
        this.INVDATE = INVDATE;
    }

    public String getDIVI() {
        return DIVI;
    }

    public void setDIVI(String DIVI) {
        this.DIVI = DIVI;
    }
	/**
	 * @return the wHLO
	 */
	public String getWHLO() {
		return WHLO;
	}
	/**
	 * @param wHLO the wHLO to set
	 */
	public void setWHLO(String wHLO) {
		WHLO = wHLO;
	}
        	
        public String getUSID() {
		return USID;
	}
	
	public void setUSID(String USID) {
		this.USID = USID;
	}
        
        public String getREPNO() {
		return REPNO;
	}
	
	public void setREPNO(String REPNO) {
		this.REPNO = REPNO;
	}
}
