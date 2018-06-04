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



public class KraRepAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

    private String aausrid;
    private String currentdate;
    private String LOCT_CODE;
   
    private String self_tp;
    private Date date_from;
    private Date date_to;
    private String kra_ind;
    private String kra_oth;
    private String ac_holder;
    private String basedon="TO";
    private String rep_op;
    private String ordby;
    private String rep_ord="LOCT";
    private String unitparam;
    private List unitlist;
    private String loct;
   
  
    private HttpServletRequest servletRequest;
    private HttpServletResponse response;

    
   
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
                   
                  
                    
                  String hd1=""; String hd2=""; String hd3="";
                  String dis1=""; String dis2=""; String dis3="";
                /// Print Start  
                   Statement refreshStatement =  null;
                   refreshStatement =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

                    Map parameters = new HashMap();
                    if(Routput.equals("XLS")){
                        parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                    }  
                 
                    if (rep_ord.equals("PCH"))
                       {hd1="COST_CENTRE"; hd2="A.LOCATION"; hd3="MODE_OF_SHIP";
                        dis1="PCH"; dis2="LOCT"; dis3="MOS";}
                     if (rep_ord.equals("LOCT"))
                       {hd1="A.LOCATION"; hd2="A.MODE_OF_SHIP"; hd3="A.COST_CENTRE";
                        dis1="LOCT"; dis2="MOS"; dis3="PCH";}
                     if (rep_ord.equals("BUYER"))
                       {hd1="A.BUYER"; hd2="A.LOCATION"; hd3="A.COST_CENTRE";
                        dis1="BUYER";dis2="LOCT";dis3="PCH";}
                    if (rep_ord.equals("AHN"))
                       {hd1="A.AC_HOLDER"; hd2="A.BUYER"; hd3="A.COST_CENTRE";
                        dis1="AHN"; dis2="BUYER"; dis3="PCH";}
                   
                    if (date_from!=null && date_to!=null)
                    {    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                         SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
                        String REPNAME=null;
                                
                     if (rep_op.equals("PRE")){  REPNAME="prekra";}
                     if (rep_op.equals("POST")){  REPNAME="postkra";}
                      if (rep_op.equals("DEPT")){  REPNAME="deptkra";}
                     if (rep_op.equals("ACINV")){REPNAME="pre_ac_kra";}

                   System.out.println("loct-"+LOCT_CODE+"INV-"+self_tp+"kra "+kra_ind+" oth "+kra_oth+" based "+basedon+" date from "+formatter1.format(date_from)+" To - "+formatter1.format(date_to));                     
                  System.out.println("hd1-"+hd1+" h2-"+hd2+" hd3 "+hd3);                     
                  
                    parameters.put("p_loct",LOCT_CODE);
                    parameters.put("p_inv",self_tp);
                    parameters.put("p_days_ind",kra_ind);
                    parameters.put("p_days_oth",kra_oth);
                    parameters.put("p_basedon",basedon);
                    parameters.put("p_date_from",formatter1.format(date_from));
                    parameters.put("p_date_to",formatter1.format(date_to));
                    parameters.put("p_hd1",hd1);
                    parameters.put("p_hd2",hd2);
                    parameters.put("p_hd3",hd3);
                    parameters.put("p_dis1",dis1);
                    parameters.put("p_dis2",dis2);
                    parameters.put("p_dis3",dis3);
                    
                     parameters.put("REPORT_CONNECTION", conn); //Connection object used for sub-report
                     parameters.put("SUBREPORT_DIR",servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"));
             
                    JasperReport report = (JasperReport) JRLoader.loadObject(servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"+REPNAME+".jasper"));
                    JasperPrint print = JasperFillManager.fillReport(report,parameters, conn);
                
                    if(Routput.equals("PDF"))
                    {
                        ServletOutputStream out1 = response.getOutputStream();
                        response.reset();
                        response.setHeader("Content-Disposition","attachment; filename=PREKRA.pdf");
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "PREKRA.xls");
                        exporter.exportReport();
                        bytes = xlsReport.toByteArray();
                        ServletOutputStream ouputStream = response.getOutputStream();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "inline; filename=salesreport.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        ouputStream.write(bytes, 0, bytes.length);
                        ouputStream.flush();
                        ouputStream.close();
                        response.reset();
                    }  //Print closed..
                    }
                    
                } // end showflag

              
              
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
                    System.out.print("File Name : KraRepAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : KraRepAction.java Exception in finally block");
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

    public String getSelf_tp() {
        return self_tp;
    }

    public void setSelf_tp(String self_tp) {
        this.self_tp = self_tp;
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

    public String getUnitparam() {
        return unitparam;
    }

    public void setUnitparam(String unitparam) {
        this.unitparam = unitparam;
    }

   


    public List getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List unitlist) {
        this.unitlist = unitlist;
    }


    public String getOrdby() {
        return ordby;
    }

    public void setOrdby(String ordby) {
        this.ordby = ordby;
    }

   

    public String getLoct() {
        return loct;
    }

    public void setLoct(String loct) {
        this.loct = loct;
    }

    public String getKra_ind() {
        return kra_ind;
    }

    public void setKra_ind(String kra_ind) {
        this.kra_ind = kra_ind;
    }

    public String getKra_oth() {
        return kra_oth;
    }

    public void setKra_oth(String kra_oth) {
        this.kra_oth = kra_oth;
    }

    

    public String getRep_op() {
        return rep_op;
    }

    public void setRep_op(String rep_op) {
        this.rep_op = rep_op;
    }

    public String getRep_ord() {
        return rep_ord;
    }

    public void setRep_ord(String rep_ord) {
        this.rep_ord = rep_ord;
    }

     
  
}
  