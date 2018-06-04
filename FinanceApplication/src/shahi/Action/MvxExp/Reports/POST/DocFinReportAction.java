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
import shahi.Action.MvxExp.Reports.POST.bean.DOCFINBEAN;

import shahi.Action.MvxExp.Admin.EisUtil;
import shahi.Action.database.ConnectionSeplWeb;
import shahi.Action.database.connection;
import shahi.Action.database.connectiondb2;

 
public class DocFinReportAction  {
      
 
    
    

    
    public List BOE_METHOD(String P_DATE,String TERM, String ReportT) throws SQLException{
        List<DOCFINBEAN> DocFinList =new ArrayList<DOCFINBEAN>();
		Connection con=null;
                Connection conndb2=null;
		PreparedStatement stat = null;
                ResultSet result = null;
                PreparedStatement stat1 = null;
                PreparedStatement stat3=null;
                ResultSet result1 = null;
                ResultSet result3 = null;
              
                 
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
                String dest_desc="";
                
                String rmitnce="";
                String findate="";
                String invdate="";
                
                String consiname="";
                String consiaddress="";
                 Double netval=0.0;
		try{
			 con=new connection().getConnection();
                         conndb2=new connectiondb2().getConnection();
		}
		catch(Exception e){
			System.out.println("DocFinReport"+e);
		} 
		try{
			
                                 Map session = ActionContext.getContext().getSession();
                                String LOCATION_CODE = (String) session.get("sessLocationCode");
                                String usrid = (String) session.get("sessUserId");

                               
                   
                    
                   if(ReportT.equals("BL") && ReportT!=null) 
                      {
                                  stat1=con.prepareStatement(" select lpad(?,10,'0') aa from dual");
                                  stat1.setString(1,TERM);
                                  result1=stat1.executeQuery();
                                  if (result1.next())
                                  {
                                    TERM=result1.getString("aa");
                                      
                                  }
                                  String xsexiv="";
                                  stat3=conndb2.prepareStatement("select distinct xsexiv from cinfdbprd.xshpin where  xsrenp=?");
                                  stat3.setString(1,TERM);
                                  result3=stat3.executeQuery();
                                  while(result3.next())
                                  {
                                     xsexiv = xsexiv+result3.getString("xsexiv")+",";
                                      
                                  }
                                     if (xsexiv!=null){
                                             xsexiv=xsexiv.substring(0, xsexiv.length()-1);
                                            }  
                                   stat1=con.prepareStatement("select DRAWN_BANK,D_BANK_ADDR,F_BANK,F_BANK_ADDR,LC_NO,BUYER,BUYER_ADDR,a.CURRENCY,ex.crncy_code,ex.MODE_OF_SHIP,ex.EXCS_INV_NO,nvl(a.fob_amt,0) fob,b.h_awb_no,b.awb_no,to_char(b.AWB_DATE,'dd/mm/yyyy') awb_date,ex.DESTI_CODE,to_char(ex.FIN_DATE,'dd-Mon-yyyy') FIN_DATE,ex.cons_addr,to_char(ex.inv_date,'dd/mm/yyyy') inv_date,tax_percent,upcharge_per from ei_shipment_dtls a, ei_shipment_mast b,ei_ENDORS_mast ex  where a.year = b.year and a.link_no = b.link_no and a.year=ex.year and a.company=ex.company and a.inv_no=ex.inv_no AND EXCS_INV_NO in ("+xsexiv+")");
                                }
                        else
                              {
                                    stat1=con.prepareStatement("select DRAWN_BANK,D_BANK_ADDR,F_BANK,F_BANK_ADDR,LC_NO,BUYER,BUYER_ADDR,a.CURRENCY,ex.crncy_code,ex.MODE_OF_SHIP,ex.EXCS_INV_NO,nvl(a.fob_amt,0) fob,b.h_awb_no,b.awb_no,to_char(b.AWB_DATE,'dd/mm/yyyy') awb_date ,ex.DESTI_CODE,to_char(ex.FIN_DATE,'dd-Mon-yyyy') FIN_DATE,ex.cons_addr,to_char(ex.inv_date,'dd/mm/yyyy') inv_date,tax_percent,upcharge_per from ei_shipment_mast b, ei_shipment_dtls a, ei_ENDORS_mast ex  where b.year = a.year and b.link_no = a.link_no and a.year=ex.year and a.company=ex.company and a.inv_no=ex.inv_no and trunc(doc_send_date)=? and ac_send_term=?");
                                    stat1.setString(1, P_DATE);
                                    stat1.setString(2, TERM);
                              }
                               ResultSet resultset1=stat1.executeQuery();
                                 while(resultset1.next())
                                 {
                                        Double mgramt=0.0; Double mdisc=0.0;  Double mbed=0.0; Double mfob=0.0; Double mtax=0.0; Double amt=0.0, migst=0.0,mcgst=0.0,msgst=0.0, xfob=0.0;

                                        stat=con.prepareStatement("select CURRENCY,sum(nvl(gr_decl_amt,0)) gr_decl,sum(nvl(discount_amt,0)) disc_amt,sum(qty_endors*(price_fc+nvl(price_misc,0))) fobfc,cgst_per,sgst_per,igst_per from ei_endors_dtls where all_no=? group by CURRENCY,cgst_per,sgst_per,igst_per ");
                                        stat.setString(1,resultset1.getString("EXCS_INV_NO"));
                                        result=stat.executeQuery();
                                        while (result.next())
                                        {  
                                           mgramt=mgramt+result.getDouble("gr_decl");
                                           mdisc=mdisc+result.getDouble("disc_amt");
                                          if (result.getString("currency").equals("INR"))
                                          {
                                           msgst=msgst+(result.getDouble("fobfc")-result.getDouble("gr_decl"))*result.getDouble("sgst_per")/100;
                                           mcgst=mcgst+(result.getDouble("fobfc")-result.getDouble("gr_decl"))*result.getDouble("cgst_per")/100;
                                           migst=migst+(result.getDouble("fobfc")-result.getDouble("gr_decl"))*result.getDouble("igst_per")/100;
                                          }
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
                                    stat=con.prepareStatement("select nvl(decimal_print,'Cents') dp from ei_currency_mast where currency=? ");
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
                                    stat=con.prepareStatement(" select conv_to_word(floor(?)) aa,conv_to_word(((?-floor(?))*100)) aa1 from dual");
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
                                 
                                 
                                 dest_desc = resultset1.getString("DESTI_CODE");
                                  
                                   
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
                                     PreparedStatement stat2=con.prepareStatement("select  b.REF_TYPE,b.REF_NO,to_char(b.ref_date,'dd/mm/yyyy') ref_date,trim(bank) bank,trim(benef_bank) benef_bank,trim(bank_addr) bank_addr,trim(bb_addr) bb_addr from ei_lc_lic_mast b where b.ref_type='LC' and ref_no=?"); 
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
			        DOCFINBEAN bean = new DOCFINBEAN();
                                
			
				bean.setCRNCY_CODE(crncy);
			        bean.setFOB_AMT(BigDecimal.valueOf(netfc)); 
                            if (ReportT.equals("OC"))
                                    {
				     bean.setFIN_DATE(invdate);
                                      bean.setBUYER_ADDR(byradd);
                                      bean.setBUYER_NAME(byrname);
                                      bean.setLCNO(lcno);
                                    }else{
                                          bean.setFIN_DATE(findate);
                                          bean.setBUYER_ADDR(consiaddress);
                                          bean.setBUYER_NAME(consiname);
                                     } 
                                bean.setFIN_BANK(f_name);
                                bean.setFIN_ADDR(f_address);
                                bean.setLC_BANK(lc_brbbrn);
                                bean.setLC_BANKADDR(lc_bankaddr);
                                bean.setDBANK(d_brbbrn);
                                bean.setDBANK_ADDR(d_bankaddr);
                                bean.setAMT_WORD(wordData);
                                
                                        if(ReportT.equals("BL"))
                                        {
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
                                            bean.setRMITNCE(rmitnce);
                                            bean.setPHNO(ph_no);
                                            bean.setBKTFNO(bkt_fno);
                                            bean.setLCNO(lcno) ;
                                            bean.setLCDATE(lc_date);
                                            if (invn!=null){
                                             invn=invn.substring(0, invn.length()-1);
                                            }   
                                             if (awbno!=null){
                                                awbno=awbno.substring(0, awbno.length()-1);
                                             }
                                             if (awbdate!=null){
                                                       awbdate=awbdate.substring(0, awbdate.length()-1);
                                              } 
                                              bean.setAWBNO(awbno);
                                              bean.setAWBDATE(awbdate);
                                                bean.setINVNO(invn);
                                                bean.setMOS(mos);
                                                bean.setDESTI_DESC (dest_desc);
                                          
                                              
                                         }  /// BL Type if closed... 
                                 
                         	DocFinList.add(bean);
                        
		}
		catch(SQLException se){System.out.println("DocFinReportAction "+se);}
		catch(Exception e){System.out.println("DocFinReportAction "+e);}
		finally{
			if(con!=null) con.close();
		}
                
		return DocFinList;
                
                  
                
	}
 
     double roundFourDecimals(double d) {
            DecimalFormat fourDForm = new DecimalFormat("#######.####");
        return Double.valueOf(fourDForm.format(d));
}   
  
    double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

     
    
}
