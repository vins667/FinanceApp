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
 
import shahi.Action.database.connection;

import shahi.Action.database.connectiondb2;
import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.MvxExp.Reports.PRE.bean.InvTempBean;

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
import shahi.Action.MvxExp.PRE.Beans.UnitBean;
import shahi.Action.MvxExp.PRE.dao.PreInvoiceDao;



public class GSPPrintAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

    private String aausrid;
    private String currentdate;
    private String LOCT_CODE;
    private String searchinv;
    private int CTNS;
    private String BOXTYPE="PKGS.";
    private String BOXDESC="";
    private String REM1="CARDBOARD BOXES CONTAINING";
    private String REM2="READY MADE GARMENTS";
    private String PLACE="";
    private String getFlag;
    private String printFlag;
    private String YEAR;
    private String COMPANY;
    private String MANUF_CODE;
    private String INV_NO;
    private List INV_DESC;
    private List INV_QTY;
    private List UNIT;
    private String MUNIT;
    private String withbpo;
    private String withstyle;
    private List invDetail=new ArrayList();
  
    private HttpServletRequest servletRequest;
    private HttpServletResponse response;

  
    private String REPCH;
    private String Routput;
   
    
    
    
      
    public String execute()
        
    { int flag=0;
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
           
            Connection conndb2 = null;
            try {
                conn = new connection().getConnection();
                conndb2 = new connectiondb2().getConnection();        
            } catch (Exception e) {
                System.out.println(e.toString());
                addActionMessage("Database Connection Not Valid !!");
                return ERROR;
            } // end catch
            String mdbk=""; String mstr="";     String mbpo=""; String mstyle="";
            PreparedStatement stat1=null,stat=null;
            ResultSet RTaccess = null,result1 =null,result=null;
            try
            {
              
                      stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                      stat1.setString(1,usrid);
                      result1=stat1.executeQuery();
                      while (result1.next())
                        {  LOCT_CODE=result1.getString("location_code");
                        }   
                    
             
               if (getFlag!=null)     
               { 
                       stat=conn.prepareStatement("select a.year,a.company,a.inv_no,unit,description,currency,CTNS,exp_type,manuf_code,sum(qty_endors) invqty from ei_endors_mast a,ei_endors_dtls b where a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and a.excs_inv_no=? group by a.year,a.company,a.inv_no,unit,description,currency,CTNS,exp_type,manuf_code");
                       stat.setString(1,searchinv);
                       result=stat.executeQuery();
                   while (result.next())
                    {CTNS=result.getInt("CTNS");
                     YEAR=result.getString("YEAR");
                     COMPANY=result.getString("COMPANY");
                     INV_NO=result.getString("inv_no");
                     MANUF_CODE=result.getString("MANUF_CODE");
                     REM2=result.getString("exp_type"); 
                     MUNIT=result.getString("UNIT");
                     invDetail.add(new InvTempBean(result.getString("UNIT"),result.getString("description"),result.getString("invqty"))); 
                     }  
                          
                          if (REM2.equals("GMT"))
                          {REM2="READY MADE GARMENTS ";}
                          else{
                              UnitBean bn=new  PreInvoiceDao().getCsytabBeanByName(REM2,"PRGP");
                              REM2=bn.getUNIT_DESC();}
                            
                          
                     
               if (LOCT_CODE.equals("100")) 
                    {PLACE="FARIDABAD";}else
                    { PLACE="BANGALORE";}
               
               } 
              if (printFlag!= null)
              {  /// Print Start  
                
                  stat=conn.prepareStatement("delete from ei_inv_temp where seh_user=? and rem3=? ");
                  stat.setString(1,usrid);
                  stat.setString(2,REPCH.trim());
                  stat.executeQuery();
                    
                    if(INV_DESC!=null && INV_DESC.size()>0){
                       for(int i=0;i<INV_DESC.size();i++){
                           if(INV_DESC.size()>0 && INV_DESC!=null){
                                stat1=conn.prepareStatement("insert into EI_INV_TEMP (year,company,inv_no,seh_user,inv_desc,qty,rem1,rem2,rem3) values (?,?,?,?,?,?,?,?,?)");
                                stat1.setString(1,YEAR);
                                stat1.setString(2,COMPANY);
                                stat1.setString(3,INV_NO);
                                stat1.setString(4,usrid);
                                stat1.setString(5, INV_DESC.get(i).toString().toUpperCase());
                                stat1.setString(6,INV_QTY.get(i).toString());
                                stat1.setString(7,UNIT.get(i).toString());
                                stat1.setString(8,searchinv);
                                stat1.setString(9,REPCH.trim());
                                stat1.executeUpdate();
                                    
                           }   
                       }
                     
                      if (REPCH.equals("SHIPPING INSTRUCTION"))
                       {
                  
                        stat = conn.prepareStatement("select distinct dbk_slno from ei_endors_dtls where dbk_slno is not null and all_no=? " );
                        stat.setString(1,searchinv);
                        result = stat.executeQuery();
                        while(result.next())
                        {  
                            mdbk=mdbk+result.getString("dbk_slno")+",";
                        }
                        if (mdbk.length()>2){
                         mdbk=mdbk.substring(0, mdbk.length()-1);}
                           System.out.println("mdbk "+mdbk);     
                        stat = conn.prepareStatement("select distinct str_slno from ei_endors_dtls where str_slno is not null and all_no=? " );
                        stat.setString(1,searchinv);
                        result = stat.executeQuery();
                        while(result.next())
                        {  
                            mstr=mstr+result.getString("str_slno")+",";
                        }
                        if (mstr.length()>2){
                         mstr=mstr.substring(0, mstr.length()-1);}
                           System.out.println("str "+mstr);
                       }
                       stat = conn.prepareStatement("select distinct trim(pre_print_no) pre_print_no from ei_endors_dtls where pre_print_no is not null and all_no=? " );
                        stat.setString(1,searchinv);
                        result = stat.executeQuery();
                        while(result.next())
                        {  
                            mbpo=mbpo+result.getString("pre_print_no")+",";
                   
                        }
                        
                        if (mbpo!=null){
                         mbpo=mbpo.substring(0, mbpo.length()-1);}
                       
                        stat = conn.prepareStatement("select distinct trim(token_no) token_no from ei_endors_dtls where token_no is not null and all_no=? " );
                        stat.setString(1,searchinv);
                        result = stat.executeQuery();
                        while(result.next())
                        {  
                            mstyle=mstyle+result.getString("token_no")+",";
                        }
                        if (mstyle!=null){
                         mstyle=mstyle.substring(0, mstyle.length()-1);}
                       
                   }  
                    String comp_name=""; String comp_addr="";  String iec ="0588085481"; String cmp_gstin=""; String cmp_state="";
                   /*  stat=conn.prepareStatement("select vend_code,vend_name, vend_addr||' '||rtrim(city)||' '||rtrim(state) comp_addr,iec_no from pr_vend_mast where vend_code=?");
                     stat.setString(1,COMPANY);
                     result=stat.executeQuery();
                     if (result.next())
                     {comp_name=result.getString("vend_name");
                      comp_addr=result.getString("comp_addr");
                      iec=result.getString("iec_no");
                     } 
                     */
                   
                             stat=conndb2.prepareStatement("select  OAADK2,OACONM,trim(OAADR2)||' '||trim(OAADR3)||' '||trim(OAADR4) addr,OATAXC, OAECAR ,aRXCSN cst,aRXLSN tin,arxlcn gstin,arfre1 statecode  from m3fdbprd.ciaddr a,minfdbprd.xinddr b where oacono=111 and oaadth=1 and oaadth=aRADTH and oacono=arcono and oaadk2=aradk2 and oaadk3=aradk3 and trim(oaadk3)=? ");
                             stat.setString(1,MANUF_CODE.trim());
                             result=stat.executeQuery();
                             if (result.next())
                             {    comp_name=result.getString("OACONM");
                                  comp_addr=result.getString("addr");
                                  cmp_gstin=result.getString("gstin");
                                  cmp_state=result.getString("statecode");
                                 
                             }   
                     
                  Statement refreshStatement =  null;
                   refreshStatement =  conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

                    Map parameters = new HashMap(); 
                    if(Routput.equals("XLS")){
                        parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                    }  
                 String REPNAME=null;   
                 if (REPCH.equals("GSP"))
                   {REPNAME="GSPPRINT";}
                   if (REPCH.equals("COO"))
                   {REPNAME="COOPRINT";}
                   if (REPCH.equals("QBAL"))
                   {REPNAME="QBALDECL";}
                   if (REPCH.equals("SHIPPING INSTRUCTION"))
                   {REPNAME="SHIPINSTR";
                       
                       parameters.put("p_dbk",mdbk);
                       parameters.put("p_str",mstr);
                       parameters.put("p_unit",MUNIT);
                   }
                   if (REPCH.equals("FISME"))
                     {  if (LOCT_CODE.equals("100"))
                       {REPNAME="FISME_100";}else{REPNAME="FISME_200";}
                       parameters.put("p_iec",iec);
                    
                       parameters.put("p_buyerpo",mbpo);
                       parameters.put("p_styledesc",mstyle); 
                          
                       parameters.put("realPath",servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"));
                      }
                              
                    parameters.put("p_inv",searchinv);
                    parameters.put("p_bpo",withbpo);
                    parameters.put("p_style",withstyle);
                    parameters.put("p_compname",comp_name);
                    parameters.put("p_compaddr",comp_addr);
                    parameters.put("cmp_gstin",cmp_gstin);
                    parameters.put("cmp_state",cmp_state); 
     
                    parameters.put("p_userid",usrid);
                    parameters.put("P_CTNS",CTNS);
                    parameters.put("p_boxtype",BOXTYPE.toUpperCase());
                    parameters.put("p_rem1",REM1.toUpperCase());
                     parameters.put("p_rem2",REM2.toUpperCase());
                    parameters.put("p_boxdesc",BOXDESC.toUpperCase());
                    parameters.put("p_place",PLACE.toUpperCase());
                    parameters.put("p_report",REPCH);
                     parameters.put("REPORT_CONNECTION", conn); //Connection object used for sub-report
                     parameters.put("SUBREPORT_DIR",servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"));
                  
                    JasperReport report = (JasperReport) JRLoader.loadObject(servletRequest.getRealPath("/shahiwebpages/MvxExp/reports/PRE/"+REPNAME+".jasper"));
                   
                    JasperPrint print = JasperFillManager.fillReport(report,parameters, conn);
                    String filename=searchinv+REPCH;
                    if(Routput.equals("PDF"))
                    {
                        ServletOutputStream out1 = response.getOutputStream();
                        response.reset();
                        response.setHeader("Content-Disposition","attachment; filename="+filename+".pdf");
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
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filename+".xls");
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
                    System.out.print("File Name : GSPPrintAction.java Exception in finally block");
                    e.printStackTrace();
                    addActionMessage("File Name : GSPPrintAction.java Exception in finally block");
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

    public String getREPCH() {
        return REPCH;
    }

    public void setREPCH(String REPCH) {
        this.REPCH = REPCH;
    }

    public String getGetFlag() {
        return getFlag;
    }

    public void setGetFlag(String getFlag) {
        this.getFlag = getFlag;
    }

    public List getInvDetail() {
        return invDetail;
    }

    public void setInvDetail(List invDetail) {
        this.invDetail = invDetail;
    }

    public String getSearchinv() {
        return searchinv;
    }

    public void setSearchinv(String searchinv) {
        this.searchinv = searchinv;
    }

    public int getCTNS() {
        return CTNS;
    }

    public void setCTNS(int CTNS) {
        this.CTNS = CTNS;
    }

    public String getBOXTYPE() {
        return BOXTYPE;
    }

    public void setBOXTYPE(String BOXTYPE) {
        this.BOXTYPE = BOXTYPE;
    }

     

    public String getREM1() {
        return REM1;
    }

    public void setREM1(String REM1) {
        this.REM1 = REM1;
    }

    public String getREM2() {
        return REM2;
    }

    public void setREM2(String REM2) {
        this.REM2 = REM2;
    }

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getINV_NO() {
        return INV_NO;
    }

    public void setINV_NO(String INV_NO) {
        this.INV_NO = INV_NO;
    }

    public List getINV_DESC() {
        return INV_DESC;
    }

    public void setINV_DESC(List INV_DESC) {
        this.INV_DESC = INV_DESC;
    }

    public List getINV_QTY() {
        return INV_QTY;
    }

    public void setINV_QTY(List INV_QTY) {
        this.INV_QTY = INV_QTY;
    }

    public List getUNIT() {
        return UNIT;
    }

    public void setUNIT(List UNIT) {
        this.UNIT = UNIT;
    }

    public String getWithbpo() {
        return withbpo;
    }

    public void setWithbpo(String withbpo) {
        this.withbpo = withbpo;
    }

    public String getBOXDESC() {
        return BOXDESC;
    }

    public void setBOXDESC(String BOXDESC) {
        this.BOXDESC = BOXDESC;
    }

    public String getMUNIT() {
        return MUNIT;
    }

    public void setMUNIT(String MUNIT) {
        this.MUNIT = MUNIT;
    }

    public String getWithstyle() {
        return withstyle;
    }

    public void setWithstyle(String withstyle) {
        this.withstyle = withstyle;
    }

    public String getMANUF_CODE() {
        return MANUF_CODE;
    }

    public void setMANUF_CODE(String MANUF_CODE) {
        this.MANUF_CODE = MANUF_CODE;
    }

    

     
    
  
}
  