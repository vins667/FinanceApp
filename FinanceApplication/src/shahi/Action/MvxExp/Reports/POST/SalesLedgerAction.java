package shahi.Action.MvxExp.Reports.POST;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.*; 
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import shahi.Action.Master.Beans.GetListBean;
import shahi.Action.database.connection;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.Reports.POST.bean.SalesLedgerBean;
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
import shahi.Action.database.ConnectionMovexBi;
  



public class SalesLedgerAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

    private String aausrid;
    private String currentdate;
    private String LOCT_CODE;
    private String BUYER;
    private String PCH;
    private String self_tp;
    private Date date_from;
    private Date date_to;
   
    private String unitparam;
    private String EXP_DOM;
    private List unitlist; 
    private String loct;
    private String PARAA; 
    private String PARAB;
    private List unitList=new ArrayList<UnitBean>();
    private HttpServletRequest servletRequest;
    private HttpServletResponse response;

    private List acList = new ArrayList();
    private List pchList = new ArrayList();
    private List ReportList= new ArrayList();
   
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
            Connection connBI=null;
            try {
                conn = new connection().getConnection();
                connBI = new ConnectionMovexBi().getConnection();
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
                List salesledgerbean=new ArrayList();
               
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
                
                        
                    
                   String v_cond=" and 1=1 ";
                    BUYER=BUYER+"%";
                    PCH=PCH+"%";
                            
                  
                    
                    if (date_from!=null && date_to!=null)
                    {    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                         SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
                        
                       
                           v_cond=" and awb_date between '"+formatter1.format(date_from)+"' and '"+formatter1.format(date_to)+"'";
                        
                     }
                    else{ v_cond=" and 1=1 ";} 
                    if (EXP_DOM.equals("DOM"))
                    {
                      v_cond=v_cond+" and crncy_code='INR' ";
                    }
                    if (EXP_DOM.equals("EXP"))
                    {
                      v_cond=v_cond+" and crncy_code<>'INR' "; 
                    } 
                    String condsql = null;  
                    String mlic=null; 
                    String invdesc=null;
                 
                        stat=conn.prepareStatement("SELECT a.excs_inv_no,to_char(inv_date,'dd/mm/yyyy') inv_date,OPCUNM,rtrim(OPCUA1)||' '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) cons_address,awb_no,to_char(awb_date,'dd/mm/yyyy') awb_date,a.year,a.company,a.inv_no, "+
                                                  " shp_bill_no,to_char(shp_bill_date,'dd/mm/yyyy') shp_bill_date,cntry_origin,desti_cntry,ship_qnty,fob_amt,gr_disc,crncy_code,(nvl(fob_amt,0)-nvl(gr_disc,0)) netfob,(nvl(fob_amt,0)-nvl(gr_disc,0))*D.EXP_RATE net_fobinr,agent,A.loading_port,D.EXP_RATE "+
                                                  " FROM EI_ENDORS_MAST A,EI_SHIPMENT_MAST B,EI_SHIPMENT_DTLS C,EI_EXCHANGE_RATe_MAST D,OCUSAD_M4oFF G  WHERE a.t_mod='LGM4' AND B.YEAR=C.YEAR AND B.LINK_NO=C.LINK_NO AND A.YEAR=C.YEAR AND A.COMPANY=C.COMPANY AND A.INV_NO=C.INV_NO AND "+
                                                  " A.CRNCY_CODE=D.CURRENCY AND C.SHP_BILL_DATE BETWEEN D.BEGIN_DATE AND D.END_DATE AND  "+
                                                  " opcono=111 and opadrt=1 and trim(opcuno)=TRIM(a.buyer) and trim(opadid)=TRIM(cons_addr) "+
                                                  " AND a.cost_centre like '"+PCH+"' AND a.location like '"+LOCT_CODE+"' and a.buyer like '"+BUYER+"'  and a.self_tp like '"+self_tp+"'"+v_cond+
                                                 " order by 1,2,4");
                 
                    result=stat.executeQuery();
                   while (result.next()) 
                   { 
                        
                        SalesLedgerBean bean=new SalesLedgerBean();
                       
                        stat1=connBI.prepareStatement("select idsunm from prodbi.cidmas where idcono=111 and trim(idsuno)=trim(?) ");
                        stat1.setString(1,result.getString("agent"));
                        result1=stat1.executeQuery();
                        if (result1.next())
                        { bean.setAGENT_NAME(result1.getString("idsunm"));
                        }
                        if (result1 != null) { result1.close(); }
                        if (stat1 !=null){stat1.close();}
                      
                       bean.setEXCS_INV_NO(result.getString("excs_inv_no"));
                       bean.setINV_DATE(result.getString("inv_date"));
                       bean.setCONS_NAME(result.getString("OPCUNM"));
                       bean.setCONS_ADDRESS(result.getString("cons_address"));
                       bean.setAWB_NO(result.getString("AWB_NO"));
                       bean.setAWB_DATE(result.getString("AWB_DATE"));
                       bean.setAWB_NO(result.getString("AWB_NO"));
                       bean.setSHP_BILL_NO(result.getString("SHP_BILL_NO"));
                       bean.setSHP_BILL_DATE(result.getString("SHP_BILL_DATE"));
                       bean.setLOADING_PORT(result.getString("loading_port"));
                       bean.setORIGN_CNTRY(result.getString("cntry_origin"));
                       bean.setDESTN_CNTRY(result.getString("desti_cntry"));
                       bean.setCRNCY_CODE(result.getString("CRNCY_CODE"));
                       bean.setQNTY(result.getBigDecimal("ship_qnty"));
                       bean.setFOB_FC(result.getBigDecimal("fob_amt"));
                       bean.setGR_DISC(result.getBigDecimal("gr_disc"));
                       bean.setNET_FOB(result.getBigDecimal("netfob"));
                       bean.setNET_INR(result.getBigDecimal("net_fobinr"));
                       bean.setEXP_RATE(result.getBigDecimal("EXP_RATE"));
                       
                        mlic="";
                        stat1 = conn.prepareStatement("select distinct ref_type||'-'||ref_no licno from ei_endors_lc_lic_dtls  where ref_no is not null and year=? and company=? and inv_no=? " );
                        stat1.setString(1, result.getString("year"));
                        stat1.setString(2, result.getString("company"));
                        stat1.setString(3, result.getString("inv_no"));
                        result1 = stat1.executeQuery();
                        while(result1.next())
                        {  mlic=mlic+result1.getString("licno")+",";
                         }
                       
                        if (mlic.length()>5){
                           mlic=mlic.substring(0, mlic.length()-1);}
                        
                         bean.setLICENCE(mlic); 
                       
                        if (result1 != null) { result1.close(); }
                        if (stat1 !=null){stat1.close();}
                        
                         invdesc="";
                        stat1 = conn.prepareStatement(" select distinct description from ei_endors_dtls  where description is not null and year=? and company=? and inv_no=? " );
                        stat1.setString(1, result.getString("year"));
                        stat1.setString(2, result.getString("company"));
                        stat1.setString(3, result.getString("inv_no"));
                        result1 = stat1.executeQuery();
                        while(result1.next())
                        {  invdesc=invdesc+result1.getString("description")+",";
                         }
                        if (invdesc.length()>5){
                            
                         invdesc=invdesc.substring(0, invdesc.length()-1);}
                       
                         bean.setINV_DESC(invdesc); 
                          
                        if (result1 != null) { result1.close(); }
                        if (stat1 !=null){stat1.close();}
                       
                       //ReportList.add(new SalesLedgerBean(result.getString("excs_inv_no"),result.getString("inv_date"),result.getString("OPCUNM"),result.getString("cons_address"),result.getString("AWB_NO"),result.getString("AWB_DATE"),result.getString("SHP_BILL_NO"),result.getString("SHP_BILL_DATE"),result.getString("LOADING_PORT"),result.getString("cntry_origin"),result.getString("desti_cntry"),result.getString("CRNCY_CODE"),result.getDouble("ship_qnty"),result.getDouble("fob_amt"),result.getDouble("gr_disc"),result.getDouble("netfob"),result.getDouble("net_fobinr"),result.getString("agent"),result.getString("agent"),result.getString("agent"))); 
                       
                       
                         
                       
                       salesledgerbean.add(bean);
                      
                   }   
                   
                   
                    
                    parameters.put("ReportList",new JRBeanCollectionDataSource(ReportList)); 
                    
                    parameters.put("P_cond",v_cond);                    
                    parameters.put("p_loct",LOCT_CODE);
                    parameters.put("p_tp",self_tp);
                    parameters.put("p_buyer",BUYER);
                     parameters.put("p_pch",PCH); 
                    parameters.put("p_expdom",EXP_DOM);
                  
                    parameters.put("p_date_from",date_from);
                    parameters.put("p_date_to",date_to);
                    
                     parameters.put("REPORT_CONNECTION", conn); //Connection object used for sub-report
                     parameters.put("SUBREPORT_DIR",servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/POST/"));
                   String REPNAME=null;
                   
                    REPNAME="SALESLEDGER";
                  
                    JasperReport report = (JasperReport) JRLoader.loadObject(servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/POST/"+REPNAME+".jasper"));
                    JasperPrint print = JasperFillManager.fillReport(report,parameters, new JRBeanCollectionDataSource(salesledgerbean));
                
                    if(Routput.equals("PDF"))
                    {
                        ServletOutputStream out1 = response.getOutputStream();
                        response.reset();
                        response.setHeader("Content-Disposition","attachment; filename=SalesLedger.pdf");
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "SalesLedger.xls");
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
                    
                    
                } // end showflag

                
                stat=conn.prepareStatement("select distinct cost_centre from ei_endors_mast where t_mod='LGM4' AND company='111' and awbdate is null order by 1");
               result=stat.executeQuery();
               while(result.next())
               { pchList.add(new GetListBean(result.getString("cost_centre"),result.getString("cost_centre")));       }
  
                 
                  
              
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
                    if (connBI != null) { connBI.close(); }
                    RTaccess = null;
                    conn = null;
                } catch (Exception e) {
                    falg = 0;
                    System.out.print("File Name : SalesLedgerAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : SalesLedgerAction.java Exception in finally block");
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

 

    public String getUnitparam() {
        return unitparam;
    }

    public void setUnitparam(String unitparam) {
        this.unitparam = unitparam;
    }

    public String getPARAA() {
        return PARAA;
    }

    public void setPARAA(String PARAA) {
        this.PARAA = PARAA;
    }

    public String getPARAB() {
        return PARAB;
    }

    public void setPARAB(String PARAB) {
        this.PARAB = PARAB;
    }

    public List getPchList() {
        return pchList;
    }

    public void setPchList(List pchList) {
        this.pchList = pchList;
    }

    

    public List getUnitList() {
        return unitList;
    }

    public void setUnitList(List unitList) {
        this.unitList = unitList;
    }

    public String getBUYER() {
        return BUYER;
    }

    public void setBUYER(String BUYER) {
        this.BUYER = BUYER;
    }

    public List getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List unitlist) {
        this.unitlist = unitlist;
    }

    public List getReportList() {
        return ReportList;
    }

    public void setReportList(List ReportList) {
        this.ReportList = ReportList;
    }



    

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getLoct() {
        return loct;
    }

    public void setLoct(String loct) {
        this.loct = loct;
    }

   

    public String getEXP_DOM() {
        return EXP_DOM;
    }

    public void setEXP_DOM(String EXP_DOM) {
        this.EXP_DOM = EXP_DOM;
    }

     double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
}     
  
}
  