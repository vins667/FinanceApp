/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shahi.Action.MvxExp.Reports.POST;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;

/**
 *
 * @author User
 */
public class FinanceChklstAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
      private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private String REPORTTYPE;
    private String aausrid;
    private String LOCT;
    private List LOCLIST = new ArrayList();
    private String INVNO;
    private String ReportT;
    
    private String BOS_LOCT;
    private String TERM;
    private String date1;
    private String REMRK;
    private String EDATE;
   
      
    @Override
   
    public String execute() throws SQLException {
        
        try{
            EisUtil pisdate = new EisUtil();
            //currentdate = pisdate.GetDate();
            pisdate.closeConnection();
        }
        catch (Exception e)
        { System.out.println(e.toString());
        } 
         
        Connection con = null;
        Connection conn = null;
        Connection connBI = null;
        Connection conndb2=null;
        PreparedStatement stat = null;
        ResultSet result = null;
        PreparedStatement stat1 = null;
        PreparedStatement stat3=null;
        ResultSet result1 = null;
        ResultSet result3 = null;
        

        try {
            
            
            
                
            
             con=new connection().getConnection();
             conndb2=new connectiondb2().getConnection();
             conn=new ConnectionSeplWeb().getConnection();
             
        
                String f_name="";
                String f_bank="";
                String f_adr="";
                String f_address="";
                String ph_no="";
                String bkt_fno="";
                
                String lc_brbbrn="";
                String lc_bankaddr="";
                String lc_date="";
                String d_brbbrn="";
                String d_bankaddr="";
                
                String lcno="";
                String query="";
                
                String byrname="";
                String byradd="";
                String invn="";
                String mos="";
                
                double netfc=0.0;
                String wordData="";
                String crncy="";
                String awbno="";
                String hawbno="";
                String awbdate="";
                String destination="";
                
                String rmitnce="";
                String findate="";
                String invdate="";
                
                String consiname="";
                String consiaddress="";
                 Map session = ActionContext.getContext().getSession();
        String LOCATION_CODE = (String) session.get("sessLocationCode");
        String usrid = (String) session.get("sessUserId");

        if (usrid == null) {
            session.put("sessUserId", aausrid);
            usrid = aausrid;
        }
     
        if (usrid == null) {
            addActionMessage("Session Not Valid !!");
            return ERROR;
        }
                 
          stat1= conn.prepareStatement("select location_code from seh_web_users where user_id=?") ;
                      stat1.setString(1,usrid.trim());
                      result1=stat1.executeQuery();
                      while (result1.next())
                        {  LOCT=result1.getString("location_code");
                             
                        }    
               
                 stat1=con.prepareStatement("select to_char(sysdate,'dd-mm-yyyy') crdate from dual");
                 result1=stat1.executeQuery();
                 if (result1.next())
                 {
                   EDATE=result1.getString("crdate");
                 }
                     SimpleDateFormat dateformat2= new SimpleDateFormat("yyyy-MM-dd"); 
                     SimpleDateFormat dateformat3= new SimpleDateFormat("dd-MMM-yy"); 
                               Double netval=0.0;
                    System.out.println("reporttype "+ReportT);
                        if(ReportT.equals("FFNEW") && ReportT!=null) 
                          { 
                              
                              String pdate=dateformat3.format(dateformat2.parse(date1.substring(0, 10)));
                              FFNEWMETHOD(pdate,TERM,conn,conndb2);
                          
                          }                
                          if(!ReportT.equals("FF") && ReportT!=null) 
                          {  
                              if(ReportT.equals("BL") && ReportT!=null) 
                              {
                                  stat1=con.prepareStatement("delete from ei_inv_temp where seh_user=? and rem1='BOECBMB' ");
                                  stat1.setString(1,usrid);
                                  stat1.executeUpdate();
                                  
                                  stat1=con.prepareStatement(" select lpad(?,10,'0') aa from dual");
                                  stat1.setString(1,TERM);
                                  result1=stat1.executeQuery();
                                  if (result1.next())
                                  {
                                    TERM=result1.getString("aa");
                                      
                                  }
                                  stat3=conndb2.prepareStatement("select distinct xsexiv from cinfdbprd.xshpin where  xsrenp=?");
                                  stat3.setString(1,TERM);
                                  result3=stat3.executeQuery();
                                  while(result3.next())
                                  {
                                       stat1=conn.prepareStatement(" insert into ei_inv_temp (excs_inv,seh_user,rem1) values (?,?,'BOECBMB') ");
                                       stat1.setString(1,result3.getString("xsexiv"));
                                       stat1.setString(2,usrid);
                                       stat1.executeUpdate();
                                       conn.commit();
                                  }
                                  
                                   stat1=con.prepareStatement("select DRAWN_BANK,D_BANK_ADDR,F_BANK,F_BANK_ADDR,LC_NO,BUYER,BUYER_ADDR,a.CURRENCY,ex.crncy_code,ex.MODE_OF_SHIP,ex.EXCS_INV_NO,nvl(a.fob_amt,0) fob,b.h_awb_no,b.awb_no,to_char(b.AWB_DATE,'dd/mm/yyyy') awb_date,ex.DESTI_CODE,to_char(ex.FIN_DATE,'dd-Mon-yyyy') FIN_DATE,ex.cons_addr,to_char(ex.inv_date,'dd/mm/yyyy') inv_date,tax_percent,upcharge_per from ei_shipment_dtls a, ei_shipment_mast b,ei_ENDORS_mast ex,EI_INV_TEMP C  where a.year = b.year and a.link_no = b.link_no and a.year=ex.year and a.company=ex.company and a.inv_no=ex.inv_no AND EXCS_INV_NO=C.EXCS_INV AND C.SEH_USER=? and c.rem1='BOECBMB' ");
                                   stat1.setString(1,usrid);
                              }else
                              {
                                    stat1=con.prepareStatement("select DRAWN_BANK,D_BANK_ADDR,F_BANK,F_BANK_ADDR,LC_NO,BUYER,BUYER_ADDR,a.CURRENCY,ex.crncy_code,ex.MODE_OF_SHIP,ex.EXCS_INV_NO,nvl(a.fob_amt,0) fob,b.h_awb_no,b.awb_no,to_char(b.AWB_DATE,'dd/mm/yyyy') awb_date ,ex.DESTI_CODE,to_char(ex.FIN_DATE,'dd-Mon-yyyy') FIN_DATE,ex.cons_addr,to_char(ex.inv_date,'dd/mm/yyyy') inv_date,tax_percent,upcharge_per from ei_shipment_mast b, ei_shipment_dtls a, ei_ENDORS_mast ex  where b.year = a.year and b.link_no = a.link_no and a.year=ex.year and a.company=ex.company and a.inv_no=ex.inv_no and trunc(doc_send_date)=? and ac_send_term=?");
                                    stat1.setString(1, dateformat3.format(dateformat2.parse(date1.substring(0, 10))));
                                    stat1.setString(2, TERM);
                              }
                               ResultSet resultset1=stat1.executeQuery();
                                 while(resultset1.next()){
                                  Double mgramt=0.0; Double mdisc=0.0;  Double mbed=0.0; Double mfob=0.0; Double mtax=0.0; Double amt=0.0, migst=0.0,mcgst=0.0,msgst=0.0, xfob=0.0;
                              
                                  stat=con.prepareStatement("select sum(nvl(gr_decl_amt,0)) gr_decl,sum(nvl(discount_amt,0)) disc_amt,sum(qty_endors*(price_fc+nvl(price_misc,0))) fobfc,cgst_per,sgst_per,igst_per from ei_endors_dtls where all_no=? group by cgst_per,sgst_per,igst_per ");
                                  stat.setString(1,resultset1.getString("EXCS_INV_NO"));
                                  result=stat.executeQuery();
                                  while (result.next())
                                  { 
                                     mgramt=mgramt+result.getDouble("gr_decl");
                                     mdisc=mdisc+result.getDouble("disc_amt");
                                     
                                     msgst=msgst+(result.getDouble("fobfc")-result.getDouble("gr_decl"))*result.getDouble("sgst_per")/100;
                                     mcgst=mcgst+(result.getDouble("fobfc")-result.getDouble("gr_decl"))*result.getDouble("cgst_per")/100;
                                     migst=migst+(result.getDouble("fobfc")-result.getDouble("gr_decl"))*result.getDouble("igst_per")/100;
                                  }
                                  
                                    
                                  stat=con.prepareStatement("select sum(nvl(amt,0)) bed_amt from ei_ship_data where type_code='BED' and ex_track_no=? ");
                                  stat.setString(1,resultset1.getString("EXCS_INV_NO"));
                                  result=stat.executeQuery();
                                  if (result.next())
                                  {
                                     mbed=result.getDouble("bed_amt");
                                    
                                  }
                                 lcno = resultset1.getString("LC_NO");
                                 
                                 invn = invn+resultset1.getString("EXCS_INV_NO")+",";
                                     
                                      
                                 mos = resultset1.getString("MODE_OF_SHIP");
                                 
                                  amt=resultset1.getDouble("fob");
                                  mtax=((amt+mbed-mgramt)*resultset1.getDouble("tax_percent"))/100;
                                  double upamt=0.0;
                                  if (resultset1.getDouble("UPCHARGE_PER")>0)
                                  {
                                    upamt=amt*resultset1.getDouble("UPCHARGE_PER")/100;
                                 }
                                  amt=amt+roundTwoDecimals(mbed+mdisc+mtax-mgramt)+roundTwoDecimals(mcgst+msgst+migst);
                               
                                  netval=(netval+amt+upamt);
                                  findate=resultset1.getString("FIN_DATE");
                                  invdate=resultset1.getString("INV_DATE");
                                  
                                  netfc=roundTwoDecimals(netval);
                                     
                                    String decimal_print="";
                                    stat=conn.prepareStatement("select nvl(decimal_print,'Cents') dp from ei_currency_mast where currency=? ");
                                    stat.setString(1,resultset1.getString("crncy_code").trim());
                                    result=stat.executeQuery();
                                    if (result.next())
                                    {decimal_print=result.getString("dp");
                                    }
                                    if(stat!=null){
                                        stat.close();
                                    }
                                    if(result!=null){
                                        result.close();
                                    }

                                    String wordfc="";        
                                    stat=conn.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
                                    stat.setDouble(1,netfc);
                                    stat.setDouble(2,netfc);
                                    stat.setDouble(3,netfc); 
                                    result=stat.executeQuery();
                                    if (result.next())
                                    {   wordfc=result.getString("aa");

                                            if (result.getString("aa1")!=null)
                                            {wordfc=wordfc+"  And  "+result.getString("aa1")+" "+decimal_print+" Only.";
                                            }else{wordfc=wordfc+" Only.";}
                                            
                                        wordData=wordfc;
                                    }  
                                    if(stat!=null){
                                        stat.close();
                                    }
                                    if(result!=null){
                                        result.close();
                                    }
                                    
                                 
                                 crncy = resultset1.getString("crncy_code");
                                     
                                 awbno = awbno+resultset1.getString("awb_no")+",";
                                  
                                 awbdate = awbdate+resultset1.getString("AWB_DATE")+",";
                                 
                                  
                                 hawbno = hawbno+resultset1.getString("h_awb_no")+",";
                                 
                                 
                                 destination = resultset1.getString("DESTI_CODE");
                                 
                                   
                                    stat=conndb2.prepareStatement("SELECT BRBKBM f_name,trim(BRBKNO) f_bank,trim(BRBBRN) f_adr,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) f_address,BRPHNO ph_no,brtfno bktfno from m3fdbprd.cbanbr where brcono=111 and trim(BRBKNO)=? and trim(BRBBRN)=?");
                                    stat.setString(1, resultset1.getString("F_BANK").trim());
                                    stat.setString(2, resultset1.getString("F_BANK_ADDR"));
                                    ResultSet resultset=stat.executeQuery();
                                     if(resultset.next())
                                     {
                                        f_name=resultset.getString("f_name");
                                        f_bank=resultset.getString("f_bank");
                                        f_adr=resultset.getString("f_adr");
                                        f_address=resultset.getString("f_address");
                                        ph_no=resultset.getString("ph_no");
                                        bkt_fno=resultset.getString("bktfno");
                                            
                                     }
                                     if(stat!=null){
                                        stat.close();
                                    }
                                    if(resultset!=null){
                                        resultset.close();
                                    }
                                     
                               if(resultset1.getString("LC_NO")!=null){
                                     PreparedStatement stat2=conn.prepareStatement("select  b.REF_TYPE,b.REF_NO,to_char(b.ref_date,'dd/mm/yyyy') ref_date,trim(bank) bank,trim(benef_bank) benef_bank,trim(bank_addr) bank_addr,trim(bb_addr) bb_addr from ei_lc_lic_mast b where b.ref_type='LC' and ref_no=?"); 
                                     stat2.setString(1, resultset1.getString("LC_NO"));
                                     ResultSet rs2=stat2.executeQuery();
                                     if(rs2.next()){
                                             lc_date=rs2.getString("ref_date");
                                            stat3=conndb2.prepareStatement("SELECT BRBKBM,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr from m3fdbprd.cbanbr where brcono=111 and trim(BRBKNO)=? and trim(BRBBRN)=?"); 
                                            stat3.setString(1, rs2.getString("bank"));
                                            stat3.setString(2, rs2.getString("bank_addr"));
                                            ResultSet rs3=stat3.executeQuery();
                                            if(rs3.next()){
                                            lc_brbbrn=rs3.getString("BRBKBM");
                                            lc_bankaddr=rs3.getString("bankaddr");}
                                           
                                            if (resultset1.getString("D_BANK_ADDR")!=null && resultset1.getString("D_BANK_ADDR").length()>0)
                                            {
                                            PreparedStatement stat4=conndb2.prepareStatement("SELECT BRBKBM,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr from m3fdbprd.cbanbr where brcono=111 and trim(BRBKNO)=? and trim(BRBBRN)=?"); 
                                            stat4.setString(1, resultset1.getString("DRAWN_BANK"));
                                            stat4.setString(2, resultset1.getString("D_BANK_ADDR").trim());
                                            ResultSet rs4=stat4.executeQuery();
                                            if(rs4.next()){
                                                d_brbbrn=rs4.getString("BRBKBM");   
                                                d_bankaddr=rs4.getString("bankaddr");
                                                
                                            } 
                                          }
                                     }
                                     } 
                                     else{
                                        if (resultset1.getString("D_BANK_ADDR")!=null && resultset1.getString("D_BANK_ADDR").length()>0)
                                            {
                                           PreparedStatement stat4=conndb2.prepareStatement("SELECT BRBKBM,trim(BRBKNO) brbkno,trim(BRBBRN) brbbrn,trim(BRBKA1)||' '||trim(BRBKA2)||' '||trim(BRBKA3)||' '||trim(BRBKA4) bankaddr from m3fdbprd.cbanbr where brcono=111 and trim(BRBKNO)=? and trim(BRBBRN)=?"); 
                                            stat4.setString(1, resultset1.getString("DRAWN_BANK").trim());
                                            stat4.setString(2, resultset1.getString("D_BANK_ADDR").trim());
                                            ResultSet rs4=stat4.executeQuery();
                                            if(rs4.next()){   
                                                
                                                d_brbbrn=rs4.getString("BRBKBM");   
                                                d_bankaddr=rs4.getString("bankaddr");
                                                lc_bankaddr=rs4.getString("bankaddr");
                                            }
                                     }
                               }
                                     
                                            PreparedStatement stat5=conndb2.prepareStatement("select OPCUNO vend_code , rtrim(opadid) baddr,opcunm buyer_name,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4)  buyer_addr  from  m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and trim(OPCUNO)=? and  trim(opadid)=?"); 
                                            stat5.setString(1, resultset1.getString("BUYER"));
                                            stat5.setString(2, resultset1.getString("BUYER_ADDR"));
                                            ResultSet rs5=stat5.executeQuery();
                                            if(rs5.next()){
                                                byrname=rs5.getString("buyer_name");
                                                byradd=rs5.getString("buyer_addr");
                                            }
                                            if(stat5!=null){
                                            stat5.close();
                                            }
                                            if(rs5!=null){
                                                rs5.close();
                                            }
                                            
                                            
                                            stat = conndb2.prepareStatement("select opcunm,rtrim(OPCUA1)||'  '||rtrim(OPCUA2)||'  '||rtrim(OPCUA3)||' '||rtrim(OPCUA4) opadd  from m3fdbprd.ocusad b where OPCONO = 111 AND OPADRT=1 and opcuno=? and opadid=? ");
                                            stat.setString(1, resultset1.getString("BUYER"));
                                            stat.setString(2, resultset1.getString("CONS_ADDR"));
                                            result = stat.executeQuery();
                                            if (result.next() == true) {
                                                consiname=result.getString("opcunm");
                                                consiaddress=result.getString("opadd");
                                             }
                                            if(stat!=null){
                                            stat.close();
                                            }
                                            if(result!=null){
                                                result.close();
                                            }
                                            
                               
                                              
                               
                               }
                               
                          }  
                      
            
            if (date1!=null) {
                    
          
                    ActionContext ac = ActionContext.getContext();
                    ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
                    String path = sc.getRealPath("/shahiwebpages/MvxExp/reports/POST");
                    Map param1 = new HashMap();
                    param1.put("SUBREPORT_DIR", path);
                    param1.put("TRM", TERM);
              
                 if (!ReportT.equals("BL"))
                 {
                    param1.put("date_from", dateformat3.format(dateformat2.parse(date1.substring(0, 10))));
                 }
                  
                    param1.put("p_query", query);
               
                    param1.put("loc", LOCT);
                
                    param1.put("P_REM", REMRK);
                    InputStream input;
                    if(ReportT.equals("FC") && ReportT!=null){
                        input =new FileInputStream(new File(path+"/FinanceCopy.jrxml"));
                    }
                    else if(ReportT.equals("FF") && ReportT!=null)
                    {
                       
                        input=new FileInputStream(new File(path+"/FinanceList.jrxml"));
                    }
                    else if(ReportT.equals("BC") && ReportT!=null)
                    {
                        input=new FileInputStream(new File(path+"/BoeCopy.jrxml"));
                    }
                    else if(ReportT.equals("DD") && ReportT!=null)
                    {
                        input=new FileInputStream(new File(path+"/DA+DP[Copy].jrxml"));
                    }
                    else if(ReportT.equals("OC") && ReportT!=null)
                    {
                        input=new FileInputStream(new File(path+"/OtherCoveringReport.jrxml"));
                    }
                    else if(ReportT.equals("BM") && ReportT!=null)
                    {
                        input=new FileInputStream(new File(path+"/BOE[Movex].jrxml"));
                    }
                    else if(ReportT.equals("DM") && ReportT!=null)
                    {
                        input=new FileInputStream(new File(path+"/DA+DP[Movex].jrxml"));
                    }
                    else
                    {
                        input=new FileInputStream(new File(path+"/BankLetter[Movex].jrxml"));
                    }
                    
                    JasperDesign design=JRXmlLoader.load(input);
                    JasperReport rep = JasperCompileManager.compileReport(design);
               if(!ReportT.equals("FF") && ReportT!=null) 
               {
                    param1.put("lc_no", lcno);
                    param1.put("fname", f_name);
                    param1.put("fbank", f_bank);
                    param1.put("fadr", f_adr);
                    param1.put("faddress", f_address);
                    param1.put("phno", ph_no);
                    param1.put("bktfno", bkt_fno);
                    
                    param1.put("lc_brbbrn", lc_brbbrn);
                    param1.put("lc_bankaddr", lc_bankaddr);
                    param1.put("d_brbbrn", d_brbbrn);
                    param1.put("d_bankaddr",d_bankaddr);
                    
                    param1.put("byrname", byrname); 
                    param1.put("byradd",byradd);
                    
                    param1.put("consiname", consiname);
                    param1.put("consiaddress",consiaddress);
                    
                  if (invn!=null){
                    invn=invn.substring(0, invn.length()-1);}   
                    param1.put("invn",invn);
                    param1.put("mos", mos);
                     param1.put("amt",BigDecimal.valueOf(netfc));
                    param1.put("findt",findate);
                    param1.put("invdate",invdate);
                    param1.put("wordData",wordData);
                    
                    param1.put("crncy",crncy);
                        if (awbno!=null){
                                       awbno=awbno.substring(0, awbno.length()-1);}
                    
                    param1.put("awbno", awbno);
                    if (awbdate!=null){
                                       awbdate=awbdate.substring(0, awbdate.length()-1);} 
                    param1.put("awbdate", awbdate);
                    param1.put("destination",destination);
                    param1.put("lc_date",lc_date);
                    
                    param1.put("condb2",conndb2);
                    
                 if (f_bank.equals("YESBF"))
                 {
                    if(crncy.equals("GBP") && crncy!=null)
                    {
                        rmitnce="  Credit YES Bank Limited Account No.1254419901 with STANDARD CHARTERED BANK, LONDON, Swift code : SCBLGB2L UNDER SWIFT ADVICE TO US. Our Swift code : YESBINBBDEL DULY QUOTING OUR ABOVE REFERENCE.";
                    }
                    else if(crncy.equals("EUR") && crncy!=null){
                        rmitnce=" Credit YES Bank Limited ID DELHI Account No. 100953543600 with DEUTSCHE BANK, FRANKFURT Swift code : DEUTDEFF UNDER SWIFT ADVICE TO US. Our Swift code : YESBINBBDEL DULY QUOTING OUR ABOVE REFERENCE.";
                    }
                    else if(crncy.equals("USD") && crncy!=null){
                        rmitnce=" Credit YES Bank Limited ID DELHI Account No.765902317 with JP MORGAN CHASE NEWYORK. USA SWIFT CHASUS33XXX  UNDER SWIFT ADVICE TO US. Our Swift code : YESBINBBDEL DULY QUOTING OUR ABOVE REFERENCE.";
                    }
                    else{
                    rmitnce=" ";
                    }
                 }
                 else if (f_bank.equals("HDFCG")){
                 if(crncy.equals("GBP") && crncy!=null)
                    {
                        rmitnce="   ID MUMBAI Account No.0001-120001-001 with  UNDER SWIFT ADVICE TO US. Our Swift code :";
                    }
                    else if(crncy.equals("EUR") && crncy!=null){
                        rmitnce=" FD DELHI(M) Account No. 500057705 with STANDARD CHARTERED BANK, FRANKFURT Swift code : SCBLDEFX UNDER SWIFT ADVICE TO US. Our Swift code :  DULY QUOTING OUR ABOVE REFERENCE.";
                    }
                    else if(crncy.equals("USD") && crncy!=null){
                        rmitnce=" Credit HDFC BANK LIMITED Account No.001-1-406717 with JP MORGAN CHASE NEWYORK. USA SWIFT CHASUS33XXX UNDER SWIFT ADVICE TO US. Our Swift code : CNRBINBBCBD DULY QUOTING OUR ABOVE REFERENCE.";
                    }
                    else{
                    rmitnce=" ";
                    }
                 
                 
                 }
                 else{
                 
                  if(crncy.equals("GBP") && crncy!=null)
                    {
                        rmitnce="ID MUMBAI Account No.0001-120001-001 with CANARA BANK LONDON, Swift code : CNRBGB2L UNDER SWIFT ADVICE TO US. Our Swift code : CNRBINBBCBD DULY QUOTING OUR ABOVE REFERENCE";
                    }
                    else if(crncy.equals("USD") && crncy!=null){
                        rmitnce="Credit Canara Bank ID MUMBAI Account No.6550791917 with BANK OF AMERICA NEWYORK. USA SWIFT BOFAUS3N UNDER SWIFT ADVICE TO US. Our Swift code : CNRBINBBCBD DULY QUOTING OUR ABOVE REFERENCE";
                    }
                    else if (crncy.equals("EUR") && crncy!=null){
                    rmitnce="FD DELHI(M) Account No. 500057705 with STANDARD CHARTERED BANK, FRANKFURT Swift code : SCBLDEFX UNDER SWIFT ADVICE TO US. Our Swift code : CNRBINBBCBD DULY QUOTING OUR ABOVE REFERENCE";
                    }
                    
                    else{
                    rmitnce="";
                    }
                  
                  
                 }
                     param1.put("rmitnce",rmitnce);
               }
                    JasperPrint print = JasperFillManager.fillReport(rep, param1, con);
                    ServletOutputStream out1 = response.getOutputStream();
                    response.reset();
                    byte[] bytes = null;
                   
            if(REPORTTYPE != null && REPORTTYPE.equals("PDF"))
            {
                response.setHeader("Content-Disposition", "attachment;filename=Financereport.pdf"); //attachment- use open new window and inline- use open in same window
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
             
            }
            else
            {
                        JRXlsExporter exporter = new JRXlsExporter();
                        ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, path + "\\");
                        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "Financereport.xls");
                        exporter.exportReport();
                        
                        bytes = xlsReport.toByteArray();
                        response.setContentType("application/vnd.ms-excel");
                        response.setHeader("Content-Disposition", "attachment; filename=Financereport.xls;");
                        response.setContentLength(bytes.length);
                        xlsReport.close();
                        out1.write(bytes, 0, bytes.length);
            
                }
            }

        } catch (Exception e) {
            System.out.println("FinancereportAction" + e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (conndb2 != null) {
                conndb2.close();
            }
            if (stat != null) {
                stat.close();
            }
            if (result != null) {
                result.close();
            }

        }
        return SUCCESS;
    }
    
    public String FFNEWMETHOD(String P_DATE,String P_TERM,Connection conn,Connection conndb2)
    {
        try{
        System.out.println("sanjeev-new method");
        
        } catch(Exception e)
                          {
                              System.out.println(e.toString());
                          }
                          finally
                          {
                              try{
                                 
                             
                              }catch(Exception ee){}
                          }
     return SUCCESS;
    }
    
    
     double roundFourDecimals(double d) {
            DecimalFormat fourDForm = new DecimalFormat("#######.####");
        return Double.valueOf(fourDForm.format(d));
}   
  
    double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public String getLOCT() {
        return LOCT;
    }

    public void setLOCT(String LOCT) {
        this.LOCT = LOCT;
    }
   
     

    public String getAausrid() {
        return aausrid;
    }

    public void setAausrid(String aausrid) {
        this.aausrid = aausrid;
    }

    public List getLOCLIST() {
        return LOCLIST;
    }

    public void setLOCLIST(List LOCLIST) {
        this.LOCLIST = LOCLIST;
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

    public String getREPORTTYPE() {
        return REPORTTYPE;
    }

    public void setREPORTTYPE(String REPORTTYPE) {
        this.REPORTTYPE = REPORTTYPE;
    }

    public String getINVNO() {
        return INVNO;
    }

    public void setINVNO(String INVNO) {
        this.INVNO = INVNO;
    }

    public String getReportT() {
        return ReportT;
    }

    public void setReportT(String ReportT) {
        this.ReportT = ReportT;
    }

    public String getBOS_LOCT() {
        return BOS_LOCT;
    }

    public void setBOS_LOCT(String BOS_LOCT) {
        this.BOS_LOCT = BOS_LOCT;
    }

    public String getTERM() {
        return TERM;
    }

    public void setTERM(String TERM) {
        this.TERM = TERM;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getREMRK() {
        return REMRK;
    }

    public void setREMRK(String REMRK) {
        this.REMRK = REMRK;
    }

    public String getEDATE() {
        return EDATE;
    }

    public void setEDATE(String EDATE) {
        this.EDATE = EDATE;
    }

    
    
    
    
}
