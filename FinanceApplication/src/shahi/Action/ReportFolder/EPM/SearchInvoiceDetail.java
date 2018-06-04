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
import shahi.Action.ReportFolder.EPM.beans.SupInvBeans;


/**
 *
 * @author Vivek
 */
public class SearchInvoiceDetail  extends ActionSupport {
    
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
    public String execute() throws Exception {
        SimpleDateFormat ts= new SimpleDateFormat("MM/dd/yyyy");
        showList = new ArrayList<SupInvBeans>();
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
                   stat = conn.prepareStatement("select APCONO, APSUNO,APSINO,APPYST,APIVDT,APCUAM,APCUCD,APRGTM,APDIVI,APWHLO,APUSID from cinfdbprd.supinv where APCONO='111' AND  APDIVI=? and APSUNO= ? and APSINO=? and APIVDT=to_char(?,'yyyymmdd')");
                   stat.setString(1,MDIVI);
                   stat.setString(2,searchcode);
                   stat.setString(3,searchinv);
                   stat.setDate(4, frmDate);
                   result=stat.executeQuery();
                   while(result.next()){                                       
                       showList.add(new SupInvBeans(result.getString("APSUNO").trim(), result.getString("APSINO").trim(), result.getString("APPYST").trim(), result.getString("APIVDT").trim(), result.getString("APCUAM").trim(), result.getString("APCUCD").trim(), result.getString("APRGTM").trim(),result.getString("APCONO"),result.getString("APDIVI"),result.getString("APWHLO"),result.getString("APUSID")));                       
                   }
            }
            catch (Exception e) {
                falg = 0;
                try {
                    falg = 0;
                    conn.rollback();

                } catch (Exception ee) {
                    System.out.print("1 file name : SearchInvoiceDetail.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : SearchInvoiceDetail.java" + e);

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
                    System.out.print("File Name : SearchInvoiceDetail.java Exception in finally block");
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
        showList = new ArrayList<SupInvBeans>();
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
                stato = connora.prepareStatement("select * from shahiweb.m3_bill_master where  supplier_code=? and bill_no=? and to_char(bill_Date,'yyyymmdd')=to_char(?,'yyyymmdd')");
               // stat.setString(1, ddnow.toString());
              //  stat.setString(2, MDIVI);
            
                stato.setString(1, SUNO);
                stato.setString(2, INVNO);
                stato.setDate(3, invDate);
                ResultSet resulto = stato.executeQuery();
                if(resulto.next()){
                    addActionError("Control No already generated in M3, First delete Invoice. ");
                }
                else
                {
                kk=1;
                }
                stat4 = connora.prepareStatement("select * from seplweb.m4_bill_master where  supplier_code=? and bill_no=? and to_char(bill_Date,'yyyymmdd')=to_char(?,'yyyymmdd')");
                stat4.setString(1, SUNO);
                stat4.setString(2, INVNO);
                stat4.setDate(3, invDate);
                ResultSet resulto1 = stat4.executeQuery();
                if(resulto1.next()){
                    addActionError("Control No already generated in M4, First delete Invoice. ");
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
                if (kk==1 && m4==1 && delflg==1)
                {                                 
                    String pcono=null;
                    String pdivi=null;
                    String psuno=null;
                    String psino=null;
                    String pivdt=null;
                    String pcuam=null;
                    String pusid=null;
                    String pchid=null;
                    String prgdt=null;
                    String prgtm=null;
                    String plmdt=null;
                    String pyea4=null; 
                    stat3 = conn.prepareStatement("select apcono,apdivi,apyea4,apsuno,apsino,apivdt,apcuam,apusid,apchid,aprgdt,aprgtm,aplmdt from cinfdbprd.supinv where APCONO='111' and APDIVI=? and APSUNO=? and APSINO=? and APIVDT=to_char(?,'yyyyMMdd')");
                   // stat3.setString(1, ddnow.toString());
                    stat3.setString(1, MDIVI);
                    stat3.setString(2, SUNO);
                    stat3.setString(3, INVNO);
                    stat3.setDate(4, invDate);
                    ResultSet result3 = stat3.executeQuery();
                    if(result3.next()){
                        pcono=result3.getString("apcono");
                        pdivi=result3.getString("apdivi");
                        psuno=result3.getString("apsuno").trim();
                        psino=result3.getString("apsino").trim();
                        pivdt=result3.getString("apivdt");
                        pcuam=result3.getString("apcuam");
                        pusid=result3.getString("apusid");
                        pchid=result3.getString("apchid");
                        prgdt=result3.getString("aprgdt");
                        prgtm=result3.getString("aprgtm");
                        plmdt=result3.getString("aplmdt");
                        pyea4=result3.getString("apyea4");
                    
                    }
                  //  System.out.print("Delete supinv "+MDIVI);
                   // stat = conn.prepareStatement("update shacdtprod.supinv set APCONO='999',APLMDT=? where APCONO='111' and APDIVI=? and APSUNO=? and APSINO=? and APIVDT=to_char(?,'yyyyMMdd')");
                    stat = conn.prepareStatement("delete from cinfdbprd.supinv where APCONO='111' and APDIVI=? and APSUNO=? and APSINO=? and APIVDT=to_char(?,'yyyyMMdd')");
                   // stat.setString(1, ddnow.toString());
                    stat.setString(1, MDIVI);
                    stat.setString(2, SUNO);
                    stat.setString(3, INVNO);
                    stat.setDate(4,invDate);
                    status=stat.executeUpdate();
                    if(status!=0)
                    {
                        // System.out.print("Delete shahi_invoice "+MWHLO);
                        stat1 = connoldmvx.prepareStatement("delete from mvxcdtshah.shahi_invoice where WAREHOUSE=? and MVXSUNO=? and INVOICENUMBER=? and DATE1=to_char(?,'dd/MM/yyyy')");
                      //  stat1.setString(1, WHLO);
                        stat1.setString(1, MWHLO);
                        stat1.setString(2, SUNO);
                        stat1.setString(3, INVNO);
                        stat1.setDate(4, invDate);
                        status1=stat1.executeUpdate();
                                                      
                   //    System.out.print("insert data supinvdelrec "+MDIVI+SUNO+INVNO+invDate);
                       stat2 = connora.prepareStatement("insert into shahiweb.supinvdelrec (apcono,apdivi,apsuno,apsino,apivdt,apcuam,apusid,apchid,aprgdt,aprgtm,aplmdt,dlchid,apyea4,dldate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)"); 
                       stat2.setString(1, pcono);
                       stat2.setString(2, pdivi);
                       stat2.setString(3, psuno);
                       stat2.setString(4, psino);
                       stat2.setString(5, pivdt);
                       stat2.setString(6, pcuam);
                       stat2.setString(7, pusid);
                       stat2.setString(8, pchid);
                       stat2.setString(9, prgdt);
                       stat2.setString(10, prgtm);
                       stat2.setString(11, plmdt);
                       stat2.setString(12, usrid);
                       stat2.setString(13, pyea4);
                     //  stat2.setString(12, ddnow.toString());
                     //  stat2.setDate(4,invDate);
                       status2=stat2.executeUpdate();      
                 // System.out.print("insert completed supinvdelrec.");
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
                    System.out.print("1 file name : SearchInvoiceDetail.java" + ee);

                    System.out.println(ee.toString());
                }
                System.out.print("1 file name : SearchInvoiceDetail.java" + e);

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
                    System.out.print("File Name : SearchInvoiceDetail.java Exception in finally block");
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
}
