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

import shahi.Action.MvxExp.Reports.PRE.bean.PrePostBean;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


public class PrePostCheckListAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private String GetDtl;
        private String date1;
	private String date2;
        private String tdate;
        private String loctcode;
	private String searchuser;
        private String searchbuyer;
        private String searchdest;
        private String SEARCH_SUBMIT;
        private String aausrid;
        private String repFlag;
         
        private String REP_TYPE;
	private String[] printrec;
        private String Routput="PDF";
        private List buyerList = new ArrayList();
        private List destList  = new ArrayList();
        private List userList  = new ArrayList();
        private List invList   = new ArrayList();
        
        private HttpServletRequest servletRequest;
        private HttpServletResponse response;
    
	public String execute() throws SQLException
	{
		
		
		Connection con=null;
		PreparedStatement stat=null;
                PreparedStatement stat1=null;
		ResultSet resultset=null;
                ResultSet result1=null;
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
                    
                       stat= con.prepareStatement("select location_code,to_char(sysdate,'yyyy-mm-dd') tdate from seh_web_users where user_id=?") ;
                       stat.setString(1,usrid);
                       resultset=stat.executeQuery();
                       while (resultset.next())
                        {  loctcode=resultset.getString("location_code");
                            tdate=resultset.getString("tdate");
                        }
               String trtype="";
               if (REP_TYPE.equals("POST"))
                           { trtype="P";}else{trtype="D";}
             if (SEARCH_SUBMIT==null)
             { 
                if(date1!=null && date1.length()>0 && date2!=null && date2.length()>0 && GetDtl.equals("YES"))
		{         
                          date1=date1.substring(0,10);
                           date2=date2.substring(0,10); 
                         
                                
                                stat=con.prepareStatement("select distinct buyer from ei_endors_mast where location=? and  (year,company,inv_no) in (select year,company,inv_no from ei_truckout_track where tr_type=? and trunc(tr_date) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')) order by 1  ");
                                stat.setString(1,loctcode);
                                stat.setString(2,trtype);
                                stat.setString(3,date1);
                                stat.setString(4,date2);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                { 
                                    buyerList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("buyer"),resultset.getString("buyer")));
                                }
                                
                                stat=con.prepareStatement("select distinct desti_cntry from ei_endors_mast where location=? and  (year,company,inv_no) in (select year,company,inv_no from ei_truckout_track where tr_type=? and trunc(tr_date) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')) order by 1  ");
                                stat.setString(1,loctcode);
                                stat.setString(2,trtype);
                                stat.setString(3,date1);
                                stat.setString(4,date2);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                { 
                                   
                                    destList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("desti_cntry"),resultset.getString("desti_cntry")));
                             
                                }
                               
                                stat=con.prepareStatement("select distinct seh_user from ei_truckout_track where location=? and tr_type=? and trunc(tr_date) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd') order by 1  ");
                                stat.setString(1,loctcode);
                                stat.setString(2,trtype);
                                stat.setString(3,date1);
                                stat.setString(4,date2);
                                resultset=stat.executeQuery();
                                while(resultset.next())
                                { 
                                    
                                    userList.add(new shahi.Action.Master.Beans.GetListBean(resultset.getString("seh_user"),resultset.getString("seh_user")));
                            
                                }
                          if(stat!=null)
                          stat.close();   
                          if(resultset!=null)
                           resultset.close();
                 }
             }else{
                            String buyer_name="";  String buyer_grp="";
                                stat=con.prepareStatement("select buyer,desti_cntry,cost_centre,excs_inv_no,to_char(t_o_date,'dd/mm/yyyy') to_date,cost_centre,ac_holder,cons_addr from ei_endors_mast where location=? and  buyer like nvl(?,'%') and desti_cntry like nvl(?,'%') and (year,company,inv_no) in (select year,company,inv_no from ei_truckout_track where seh_user like nvl(?,'%') and tr_type=? and trunc(tr_date) between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')) order by 1,2,3,4  ");
                                stat.setString(1,loctcode);
                                stat.setString(2,searchbuyer);
                                stat.setString(3,searchdest);
                                stat.setString(4,searchuser);
                                stat.setString(5,trtype);
                                stat.setString(6,date1);
                                stat.setString(7,date2);
                                resultset=stat.executeQuery();
                                while(resultset.next()) 
                                {   stat1 = con.prepareStatement("select opcuno,rtrim(okcucl) okcucl,opcunm,ltrim(rtrim(opadid)) caddr from ocusma_m4off, ocusad_m4off b  where okcono=111 and opcono=111 and okcuno=opcuno and  OPADRT=1 and opcuno=? and opadid=? ");
                                    stat1.setString(1, resultset.getString("buyer"));
                                    stat1.setString(2,resultset.getString("cons_addr"));
                                     result1 = stat1.executeQuery();
                                    if (result1.next() == true) {
                                      buyer_name = result1.getString("opcunm");
                                      buyer_grp =result1.getString("okcucl");
                                    }  
                                    
                                    invList.add(new PrePostBean(buyer_name,resultset.getString("desti_cntry"),resultset.getString("cost_centre"),resultset.getString("to_date"),resultset.getString("ac_holder"),resultset.getString("excs_inv_no"),buyer_grp));
                                }
                 
                         }    
             String compname="SHAHI EXPORTS PVT LTD";
             
             if (repFlag.equals("YES"))
             {
                  stat=con.prepareStatement("delete from ei_inv_temp where seh_user=? and rem3=? ");
                  stat.setString(1,usrid);
                  stat.setString(2,REP_TYPE);
                  stat.executeUpdate();
                  
                    if(printrec!=null && printrec.length>0){
                       for(int i=0; i<printrec.length; i++)
                        {
                         if(printrec[i].length()!=0)
                            {
                                 int delay=0;
                                 int in1= printrec[i].indexOf("#");
                                 String va1=printrec[i].substring(0,in1);
                                 int in2= printrec[i].indexOf("!");
                                 String va2=printrec[i].substring(in1+1,in2);
                                 String va3=printrec[i].substring(in2+1);
                                stat1=con.prepareStatement(" select nvl(count(*),0) cnt from ei_docs_delay_Dtls where grup_code='ADR' AND all_no=? ");
                                stat1.setString(1,va1);
                                result1 = stat1.executeQuery();
                               if (result1.next() == true) 
                                 { 
                                      delay=result1.getInt("cnt");
                                 }
                                stat=con.prepareStatement("insert into EI_INV_TEMP ( excs_inv,inv_desc,seh_user,rem3,qty,company) values (?,?,?,?,?,?)");
                                stat.setString(1,va1);
                                stat.setString(2,va2);
                                stat.setString(3,usrid);
                                stat.setString(4,REP_TYPE);
                                stat.setInt(5,delay);
                                stat.setString(6,va3);
                                stat.executeUpdate();
                                    
                           }   
                       }
                       }
                 
                  Map parameters = new HashMap();
                    if(Routput.equals("XLS")){
                        parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                    } 
                
                      parameters.put("p_user",usrid);
                      parameters.put("p_comp",compname); 
                      
               
                     parameters.put("REPORT_CONNECTION", con); //Connection object used for sub-report
                     parameters.put("SUBREPORT_DIR",servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"));
              
                     String REPNAME=null;
                   if (REP_TYPE.equals("POST"))
                   {REPNAME="PostDocsList";}
                   if (REP_TYPE.equals("PRE"))
                   {REPNAME="PreDocsList";}
                   if (REP_TYPE.equals("PREINV"))
                   { 
                       REPNAME="PreDocsInv";} 
                 
                    JasperReport report = (JasperReport) JRLoader.loadObject(servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"+REPNAME+".jasper"));
                    JasperPrint print = JasperFillManager.fillReport(report,parameters, con);
                    if(Routput.equals("PDF"))
                    { 
                        ServletOutputStream out1 = response.getOutputStream();
                        response.reset();
                        response.setHeader("Content-Disposition","attachment; filename=DOCSCHECKLIST.pdf");
                        response.setHeader("cache-control", "no-cache");
                        response.setDateHeader("Last-Modified", 123);
                        response.setContentType("application/pdf");
                        JasperExportManager.exportReportToPdfStream(print, out1);
                        out1.flush();
                        out1.close();
                   
                    }
                    else   // xls
                    {
                        byte[] bytes = null;
                        JRXlsExporter exporter = new JRXlsExporter();
                        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "sprint.xls");
                        exporter.exportReport();
                        bytes = xlsReport.toByteArray();
                        ServletOutputStream ouputStream = response.getOutputStream();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "inline; filename=sdprint.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        ouputStream.write(bytes, 0, bytes.length);
                        ouputStream.flush();
                        ouputStream.close();
                        response.reset();
                    }  //Print closed..
                    
             
             }
             
          
		}
	
		catch(SQLException se)
		{
			System.out.println("PrePostCheckListAction"+se);
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

    public String getREP_TYPE() {
        return REP_TYPE;
    }

    public void setREP_TYPE(String REP_TYPE) {
        this.REP_TYPE = REP_TYPE;
    }

    public List getBuyerList() {
        return buyerList;
    }

    public void setBuyerList(List buyerList) {
        this.buyerList = buyerList;
    }

    public List getDestList() {
        return destList;
    }

    public void setDestList(List destList) {
        this.destList = destList;
    }

    public List getUserList() {
        return userList;
    }

    public void setUserList(List userList) {
        this.userList = userList;
    }

    

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getGetDtl() {
        return GetDtl;
    }

    public void setGetDtl(String GetDtl) {
        this.GetDtl = GetDtl;
    }

    public String getSearchuser() {
        return searchuser;
    }

    public void setSearchuser(String searchuser) {
        this.searchuser = searchuser;
    }

    public String getSearchbuyer() {
        return searchbuyer;
    }

    public void setSearchbuyer(String searchbuyer) {
        this.searchbuyer = searchbuyer;
    }

    public String getSearchdest() {
        return searchdest;
    }

    public void setSearchdest(String searchdest) {
        this.searchdest = searchdest;
    }

    public String getSEARCH_SUBMIT() {
        return SEARCH_SUBMIT;
    }

    public void setSEARCH_SUBMIT(String SEARCH_SUBMIT) {
        this.SEARCH_SUBMIT = SEARCH_SUBMIT;
    }

    public List getInvList() {
        return invList;
    }

    public void setInvList(List invList) {
        this.invList = invList;
    }

    public String[] getPrintrec() {
        return printrec;
    }

    public void setPrintrec(String[] printrec) {
        this.printrec = printrec;
    }

   
 public void setServletRequest(HttpServletRequest servletRequest) {

        this.servletRequest = servletRequest;

    }

    public void setServletResponse(HttpServletResponse response) {
           this.response = response;
    }

   
    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }	

    public String getRoutput() {
        return Routput;
    }

    public void setRoutput(String Routput) {
        this.Routput = Routput;
    }
	   
}
      