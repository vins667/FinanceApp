package shahi.Action.MvxExp.PRE;
 

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
import java.util.Map;
import shahi.Action.MvxExp.PRE.Beans.BuyerPortalMasterBean;

public final class BuyerPortalMasterAction extends ActionSupport{

	private String date1;
	private String date2;
	private String filetype;
        private String SEARCH_EDI;
        private String SEARCH_PORTAL;
        private String SEARCH_IC;
	private String abc;
        private String aausrid;
        private String LOCT_CODE;
        private String self_tp; 
        private String Flag;
        private String BUYR;
        private String BUYR1;
        private String BYRGRUP;
        private String BUYR_NEW;
        private String BYRGRUP_NEW;
        private String EDIAPP;
        private String EDIAPP1;
        private String PORTLAPP;
        private String PORTLAPP1;
        private String DUE_DAYS;
        private String ICAPP;
        private String ICAPP1;
	List PROC_LIST=new ArrayList();
	List BUYERPORTALLIST=new ArrayList();
        private String BEDI_APP1;
        private String BPORTAL_APP1;
        private String BIC_APP1;
	
        private String BUYING;
        
	public String execute() throws SQLException
	{
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
                      
                              
                                
                                String query="";
                                if(BUYR!=null && BUYR.length()>0){
                                  query+=" and BUYER like '"+"%"+BUYR.toUpperCase()+"%"+"'";
                                }
                                
                                 
                                  query+=" and nvl(PORTAL_APP,'N') like '"+SEARCH_PORTAL+"' "; 
                                  query+=" and nvl(IC_APP,'N') like '"+SEARCH_IC+"' "; 
                                  query+=" and nvl(EDI_APP,'N') like '"+SEARCH_EDI+"' "; 
                     
                             if(Flag!=null && Flag.length()>0){
                                stat=con.prepareStatement("select BUYER,BUYER_GRP,EDI_APP,PORTAL_APP,IC_APP,DUE_CAL_DAYS from ei_buyer_req_mast where 1=1 "+query+" ");
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        BUYERPORTALLIST.add(new BuyerPortalMasterBean(resultset.getString("BUYER"),resultset.getString("BUYER_GRP"),resultset.getString("EDI_APP"),resultset.getString("PORTAL_APP"),resultset.getString("IC_APP"),resultset.getString("DUE_CAL_DAYS")));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                   resultset.close();
                            }
                    
		}                 
	 
		catch(SQLException se) 
		{
			System.out.println("BuyerPortalMasterAction"+se);
		}
		catch(Exception e)
		{
			System.out.println("Exception" +e);
		}
		finally{
                  if(con!=null)
                  con.close();
                if(stat!=null)
                  stat.close();
                if(resultset!=null)
                  resultset.close();
                }
                
                
		return SUCCESS;
	}
        
        public String newentry() throws SQLException{
            
		Connection con=null;
		PreparedStatement stat=null;
		ResultSet resultset=null;
                Map session = ActionContext.getContext().getSession();
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
                      
                                stat=con.prepareStatement("select distinct buyer_grp from ei_buyer_req_mast where buyer_grp is not null and nvl(portal_app,'N')='Y' order by 1");
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        PROC_LIST.add(resultset.getString("buyer_grp"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                   resultset.close();
                }
                catch(Exception e){
                   System.out.println(e.toString()); 
                }
                finally{
                  if(con!=null)
                  con.close();
                if(stat!=null)
                  stat.close();
                if(resultset!=null)
                  resultset.close();
                }
                
                
            return "new";
        }
         public String save() throws SQLException{
            
            Connection con=null;
		PreparedStatement stat=null;
		ResultSet resultset=null;
                Map session = ActionContext.getContext().getSession();
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
                    
                    
                    
                    stat=con.prepareStatement("select distinct buyer_grp from ei_buyer_req_mast where buyer_grp is not null and nvl(portal_app,'N')='Y' order by 1");
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        PROC_LIST.add(resultset.getString("buyer_grp"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                   resultset.close();
                    
                    
                    int x=0;
                    int y=0;
                              PreparedStatement stat1=con.prepareStatement("select * from ei_buyer_req_mast where BUYER=?");
                                stat1.setString(1, BUYR_NEW.trim());
                                resultset=stat1.executeQuery();
                                    if(resultset.next()){
                                    stat=con.prepareStatement("update ei_buyer_req_mast set EDI_APP=?,PORTAL_APP=?,IC_APP=?,DUE_CAL_DAYS=?,TDATE=sysdate,SEH_USER=? where BUYER=?");
                                      if(EDIAPP.equals("false") && EDIAPP.length()>0){
                                    stat.setString(1, EDIAPP1);
                                    }
                                    else{
                                      stat.setString(1, EDIAPP);  
                                    }
                                    if(PORTLAPP.equals("false") && PORTLAPP.length()>0){
                                    stat.setString(2, PORTLAPP1);
                                    }
                                    else{
                                     stat.setString(2, PORTLAPP);   
                                    }
                                    if(ICAPP.equals("false") && ICAPP.length()>0){
                                    stat.setString(3, ICAPP1);
                                    }
                                    else{
                                      stat.setString(3, ICAPP);  
                                    }
                                    stat.setString(4,DUE_DAYS);
                                    stat.setString(5, usrid);
                                    stat.setString(6, BUYR_NEW);
                                    
                                    y=stat.executeUpdate();
                                    if(y>0){
                                        ++y;
                                    }
                                    
                                    }
                                    else{
                                    
                                    stat=con.prepareStatement("insert into ei_buyer_req_mast(BUYER,EDI_APP,PORTAL_APP,IC_APP,DUE_CAL_DAYS,TDATE,SEH_USER) values(?,?,?,?,?,sysdate,?)");
                                    stat.setString(1, BUYR_NEW.toUpperCase().trim());
                                   
                                    if(EDIAPP.equals("false") && EDIAPP.length()>0){
                                    stat.setString(2, EDIAPP1);
                                    }
                                    else{
                                      stat.setString(2, EDIAPP);  
                                    }
                                    if(PORTLAPP.equals("false") && PORTLAPP.length()>0){
                                    stat.setString(3, PORTLAPP1);
                                    }
                                    else{
                                     stat.setString(3, PORTLAPP);   
                                    }
                                    if(ICAPP.equals("false") && ICAPP.length()>0){
                                    stat.setString(4, ICAPP1);
                                    }
                                    else{
                                      stat.setString(4, ICAPP);  
                                    }
                                    stat.setString(5,DUE_DAYS);
                                    stat.setString(6, usrid);
                                     x=stat.executeUpdate();
                                    if(x>0){
                                        ++x;
                                       
                                    }   
                                    }
                                    
                                    
                                    if(x>0){
                                        
                                      addActionMessage("Data Inserted Successfully !!");
                                       con.commit();
                                       DUE_DAYS=null;
                                       ICAPP=null;
                                       PORTLAPP=null;
                                       EDIAPP=null;    
                                       BUYR_NEW=null;
                                    }
                                    if(y>0){
                                        
                                     addActionMessage("Data Updated Successfully !!");   
                                      con.commit();
                                      DUE_DAYS=null;
                                       ICAPP=null;
                                       PORTLAPP=null;
                                       EDIAPP=null;    
                                       BUYR_NEW=null;
                                    }
                                    
                                    
                                    
                                    
                                    
                               
                }
                catch(Exception e){
                   System.out.println(e.toString()); 
                }
                finally{
                  if(con!=null)
                  con.close();
                if(stat!=null)
                  stat.close();
                if(resultset!=null)
                  resultset.close();
                }
            
            return "save";
        }
        public String editVal() throws SQLException{
            
             Connection con=null;
		PreparedStatement stat=null;
		ResultSet resultset=null;
                Map session = ActionContext.getContext().getSession();
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
                    
                    
                     stat=con.prepareStatement("select distinct buyer_grp from ei_buyer_req_mast where buyer_grp is not null and nvl(portal_app,'N')='Y' order by 1");
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                {
                                        PROC_LIST.add(resultset.getString("buyer_grp"));
                                }
                                if(stat!=null)
                                        stat.close();
                                if(resultset!=null)
                                   resultset.close();
                    
                                
                    
                    int x=0;
                    int y=0;
                    System.out.println("ok1"+BUYR1.trim());
                              PreparedStatement stat1=con.prepareStatement("select BUYER,BUYER_GRP,EDI_APP,PORTAL_APP,IC_APP,DUE_CAL_DAYS from ei_buyer_req_mast where BUYER=?");
                                stat1.setString(1, BUYR1.trim());
                                resultset=stat1.executeQuery();
                                    if(resultset.next()){
                                    BUYR_NEW=resultset.getString("BUYER");
                                    BYRGRUP_NEW=resultset.getString("BUYER_GRP");
                                    EDIAPP=resultset.getString("EDI_APP");
                                    PORTLAPP=resultset.getString("PORTAL_APP");
                                    ICAPP=resultset.getString("IC_APP");
                                    DUE_DAYS=resultset.getString("DUE_CAL_DAYS");
                                    }
                }
                catch(Exception e){
                  System.out.println(e.toString());   
                }
                finally{
                  if(con!=null)
                  con.close();
                if(stat!=null)
                  stat.close();
                if(resultset!=null)
                  resultset.close();
                }
                
            
            return "edit";
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

    public List getBUYERPORTALLIST() {
        return BUYERPORTALLIST;
    }

    public void setBUYERPORTALLIST(List BUYERPORTALLIST) {
        this.BUYERPORTALLIST = BUYERPORTALLIST;
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

   

    public String getSelf_tp() {
        return self_tp;
    }

    public void setSelf_tp(String self_tp) {
        this.self_tp = self_tp;
    }

    public String getBUYING() {
        return BUYING;
    }

    public void setBUYING(String BUYING) {
        this.BUYING = BUYING;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public String getBUYR() {
        return BUYR;
    }

    public void setBUYR(String BUYR) {
        this.BUYR = BUYR;
    }

    public String getBYRGRUP() {
        return BYRGRUP;
    }

    public void setBYRGRUP(String BYRGRUP) {
        this.BYRGRUP = BYRGRUP;
    }

    public String getBUYR1() {
        return BUYR1;
    }

    public void setBUYR1(String BUYR1) {
        this.BUYR1 = BUYR1;
    }

    public String getBUYR_NEW() {
        return BUYR_NEW;
    }

    public void setBUYR_NEW(String BUYR_NEW) {
        this.BUYR_NEW = BUYR_NEW;
    }

    public String getBYRGRUP_NEW() {
        return BYRGRUP_NEW;
    }

    public void setBYRGRUP_NEW(String BYRGRUP_NEW) {
        this.BYRGRUP_NEW = BYRGRUP_NEW;
    }

    public String getEDIAPP() {
        return EDIAPP;
    } 

    public void setEDIAPP(String EDIAPP) {
        this.EDIAPP = EDIAPP;
    }

    public String getPORTLAPP() {
        return PORTLAPP;
    }

    public void setPORTLAPP(String PORTLAPP) {
        this.PORTLAPP = PORTLAPP;
    }

    public String getICAPP() {
        return ICAPP;
    }

    public void setICAPP(String ICAPP) {
        this.ICAPP = ICAPP;
    }

    public String getEDIAPP1() {
        return EDIAPP1;
    }

    public void setEDIAPP1(String EDIAPP1) {
        this.EDIAPP1 = EDIAPP1;
    }

    public String getPORTLAPP1() {
        return PORTLAPP1;
    }

    public void setPORTLAPP1(String PORTLAPP1) {
        this.PORTLAPP1 = PORTLAPP1;
    }

    public String getICAPP1() {
        return ICAPP1;
    }

    public void setICAPP1(String ICAPP1) {
        this.ICAPP1 = ICAPP1;
    }

    public String getBEDI_APP1() {
        return BEDI_APP1;
    }

    public void setBEDI_APP1(String BEDI_APP1) {
        this.BEDI_APP1 = BEDI_APP1;
    }

    public String getBPORTAL_APP1() {
        return BPORTAL_APP1;
    }

    public void setBPORTAL_APP1(String BPORTAL_APP1) {
        this.BPORTAL_APP1 = BPORTAL_APP1;
    }

    public String getBIC_APP1() {
        return BIC_APP1;
    }

    public void setBIC_APP1(String BIC_APP1) {
        this.BIC_APP1 = BIC_APP1;
    }

    public String getDUE_DAYS() {
        return DUE_DAYS;
    }

    public void setDUE_DAYS(String DUE_DAYS) {
        this.DUE_DAYS = DUE_DAYS;
    }

    public String getSEARCH_EDI() {
        return SEARCH_EDI;
    }

    public void setSEARCH_EDI(String SEARCH_EDI) {
        this.SEARCH_EDI = SEARCH_EDI;
    }

    public String getSEARCH_PORTAL() {
        return SEARCH_PORTAL;
    }

    public void setSEARCH_PORTAL(String SEARCH_PORTAL) {
        this.SEARCH_PORTAL = SEARCH_PORTAL;
    }

    public String getSEARCH_IC() {
        return SEARCH_IC;
    }

    public void setSEARCH_IC(String SEARCH_IC) {
        this.SEARCH_IC = SEARCH_IC;
    }

   

    
 	 
 
    
    
}
  