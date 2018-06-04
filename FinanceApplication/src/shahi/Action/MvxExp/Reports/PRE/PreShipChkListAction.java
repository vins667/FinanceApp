package shahi.Action.MvxExp.Reports.PRE;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import shahi.Action.Master.Beans.GetListBean;
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.Reports.PRE.bean.TOSumBean;
import javax.servlet.*;
import java.util.HashMap;
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
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;



public class PreShipChkListAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

    private String aausrid;
    private String currentdate;
    private String LOCT_CODE;
    private String BUYER;
    private Date date_from;
    private Date date_to;
    private String ac_holder;
    private String basedon="TO";
    private String loct;
    
     
    private HttpServletRequest servletRequest;
    private HttpServletResponse response;

    private List acList = new ArrayList();
    
   
    private String Routput;
    private String printFlag;
    
    
    
      
    public String execute()
        
    {
       try{
            EisUtil pisdate = new EisUtil();
            currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        }

       int falg = 0;  // 0 error , 1 = input, 2=save , 3-view
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
  
        try {
            Connection conn = null;
            try {
                conn = new connection().getConnection();
                conn.setAutoCommit(false);
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
            } // end catch

            PreparedStatement stat1=null,stat=null;
            ResultSet RTaccess = null,result1 =null,result=null;
            try
            {
              
                      stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                      stat1.setString(1,usrid);
                      result1=stat1.executeQuery();
                      while (result1.next())
                        {  loct=result1.getString("location_code");
                        }   
               
              if (printFlag!= null)
              {
                   
                 
               
                  
                /// Print Start  
                   Statement refreshStatement =  null;
                   refreshStatement =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

                    Map parameters = new HashMap();
                    if(Routput.equals("XLS")){
                        parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                    }  
                 
                     if (ac_holder==null){ac_holder="%";}
                     if (BUYER==null){BUYER="%";}else{BUYER=BUYER.toUpperCase().trim()+"%";}
                    String v_cond=" and 1=1 ";
                    String v_hd=""; String v_hd1="";
                    if (date_from!=null && date_to!=null)
                    {    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                         SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
                        //formatter1.format(date_from)
                       if (basedon.equals("INV"))
                       {
                           v_cond=" and  trunc(INV_DATE) between '"+formatter1.format(date_from)+"' and '"+formatter1.format(date_to)+"'";
                           v_hd="INV Date From : "+formatter1.format(date_from)+" To :"+formatter1.format(date_to);
                        }
                         if (basedon.equals("ETD"))
                       {
                           v_cond=" and  trunc(ETD_DATE) between '"+formatter1.format(date_from)+"' and '"+formatter1.format(date_to)+"'";
                           v_hd="ETD Date From : "+formatter1.format(date_from)+" To :"+formatter1.format(date_to);
                        }if (basedon.equals("TO")){
                                v_cond=" and TO_DATE between '"+formatter1.format(date_from)+"' and '"+formatter1.format(date_to)+"'";
                                v_hd=" TO Date From "+formatter1.format(date_from)+" To :"+formatter1.format(date_to);
                         }
                       if (basedon.equals("FTP")){
                                v_cond="   and trunc(A.DOC_SEND) between '"+formatter1.format(date_from)+"' and '"+formatter1.format(date_to)+"'";
                                v_hd=" FTP From "+formatter1.format(date_from)+" To :"+formatter1.format(date_to);
                             
                       }if (basedon.equals("PDS")){
                                v_cond="   and trunc(A.PRE_DOCS_SENT) between '"+formatter1.format(date_from)+"' and '"+formatter1.format(date_to)+"'";
                                v_hd=" PDS From "+formatter1.format(date_from)+" To :"+formatter1.format(date_to);
                        }
                        if (basedon.equals("EDATE")){
                                v_cond="   and trunc(A.TDATE) between '"+formatter1.format(date_from)+"' and '"+formatter1.format(date_to)+"'";
                                v_hd=" E/Date From "+formatter1.format(date_from)+" To :"+formatter1.format(date_to);
                        }
                    }
                    
                      parameters.put("p_loct",LOCT_CODE);
                      parameters.put("p_buyer",BUYER);
                  System.out.println("buyer"+BUYER);       
                      parameters.put("p_ac",ac_holder); 
                      parameters.put("rep_hd",v_hd1);
                      parameters.put("p_head",v_hd);
                      parameters.put("p_cond",v_cond);
                    
                     parameters.put("REPORT_CONNECTION", conn); //Connection object used for sub-report
                     parameters.put("SUBREPORT_DIR",servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"));
                   String REPNAME=null;
                    
                       REPNAME="pre_ship_chklist";
                      
                    
                  
                    JasperReport report = (JasperReport) JRLoader.loadObject(servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"+REPNAME+".jasper"));
                    JasperPrint print = JasperFillManager.fillReport(report,parameters, conn);
                
                    if(Routput.equals("PDF"))
                    { 
                        ServletOutputStream out1 = response.getOutputStream();
                        response.reset();
                        response.setHeader("Content-Disposition","attachment; filename=sprint.pdf");
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "preshipchklist.xls");
                        exporter.exportReport();
                        bytes = xlsReport.toByteArray();
                        ServletOutputStream ouputStream = response.getOutputStream();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "inline; filename=preshipchklist.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        ouputStream.write(bytes, 0, bytes.length);
                        ouputStream.flush();
                        ouputStream.close();
                        response.reset();
                    }  //Print closed..
                    
                     
                } // end showflag

               stat=conn.prepareStatement("select type_Desc,type_code from ei_grup_type_dtls where grup_type_code='AHN' and close_date is null ORDER BY 1");
               result=stat.executeQuery();
               while(result.next())
               { acList.add(new GetListBean(result.getString("type_Desc"),result.getString("type_Desc")));       }
  
                
                                 
              
            }
            catch (Exception e) {
            System.out.println(e.toString());
            addActionMessage("Error In  Statement !!");
            try{
                   conn.rollback();
            }catch(Exception ee) {System.out.println(ee.toString());}

            }finally {
                try
                {
                    if (stat1 != null) { stat1.close(); }
                    if (stat != null) { stat.close(); }

                    if (RTaccess != null) { RTaccess.close(); }
                    if (result != null) { result.close(); }
                    if (result1 != null) { result1.close(); }

                    if (conn != null) { conn.close(); }
                    RTaccess = null;
                    conn = null;
                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : PreShipChkListAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : PreShipChkListAction.java Exception in finally block");
                    return ERROR;
                }
             } /// end Finally Block 
           }
           catch (Exception e) {
            addActionError(e.getMessage());
            return ERROR;
           }
       return SUCCESS;

    }//end Execute

 

    public String getRoutput() {
        return Routput;
    }

    public void setRoutput(String Routput) {
        this.Routput = Routput;
    }

    public String getLOCT_CODE() {
        return LOCT_CODE;
    }

    public void setLOCT_CODE(String LOCT_CODE) {
        this.LOCT_CODE = LOCT_CODE;
    }

    public List getAcList() {
        return acList;
    }

    public void setAcList(List acList) {
        this.acList = acList;
    }

    public String getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

   
    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
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
 
    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

   

    public String getAc_holder() {
        return ac_holder;
    }

    public void setAc_holder(String ac_holder) {
        this.ac_holder = ac_holder;
    }

    public String getBasedon() {
        return basedon;
    }

    public void setBasedon(String basedon) {
        this.basedon = basedon;
    }

    public String getLoct() {
        return loct;
    }

    public void setLoct(String loct) {
        this.loct = loct;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }
 
        
  
}
  