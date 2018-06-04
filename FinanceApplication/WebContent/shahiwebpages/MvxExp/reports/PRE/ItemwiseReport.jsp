<%-- 
    Document   : PostDocKraReport
    Created on : Feb 16, 2017, 03:50:16 PM
    Author     : Guddu Kumar
--%>



<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="shahi.Action.MvxExp.Reports.PRE.bean.ItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Collection"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="net.sf.jasperreports.engine.JRParameter"%> 
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="shahi.Action.database.connection"%>
<%@page import="shahi.Action.database.connectiondb2"%>
<%@page import="java.sql.Connection"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shahi Exports Pvt Ltd</title>
</head>
  
      
        <link href="<s:url value="../../../css/main.css"/>" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="../../../css/ShahiButtons/shahibuttons.css"/>

 
		<script language="javascript" type="text/javascript" src = "../../script/function.js" ></script>
   		<script type="text/javascript" src="../../script/jquery.1.4.2.js"></script>
		<script type="text/javascript" src="../../script/jquery_blockUI.js"></script>
		<script type="text/javascript" src="../../script/jquery_cookie.js"></script>
		<script type="text/javascript" src="../../script/hidediv.js"></script>     
       
        <link rel="stylesheet" type="text/css" media="all" href="../../style/jsDatePick_ltr.min.css" />
        <link href="../../style/style.css" rel="stylesheet" type="text/css"/>
        <link href="../../../css/main.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="../../script/jsDatePick.min.1.3.js"></script>
			        
   <script type="text/javascript" language="javascript" >     
        window.onload = function()
	  {
        new JsDatePick({ 
            useMode:2,
            target:"date1",
            dateFormat:"%d/%m/%Y",
            imgPath:"/img/"
        });
        new JsDatePick({
            useMode:2,
            target:"date2",
            dateFormat:"%d/%m/%Y",
            imgPath:"/img/"
        });
    }
    function search()
    {
    	if(validate()==true)
    	{
    	document.create_pdf_form.action="ItemwiseReport";
    	document.create_pdf_form.submit();
        document.getElementById('prepage').style.visibility='';
    	}
    	return true;
    }
     
		function validate() 
		{
			DATE_FROM=document.getElementById("date1");
		        DATE_TO=document.getElementById("date2");
		    	
		    	if(DATE_FROM.value==''){
		    		alert("'From' Date cannot be empty");
		    		return false;
		    	}
		    	if(DATE_TO.value==''){
		    		alert("'To' Date cannot be empty");
		    		return false;
		    	}
		    	
		    	return true;
			
		} 
   
 function waitPreloadPage() { //DOM
            if (document.getElementById){
                document.getElementById('prepage').style.visibility='hidden';
            }else{
                if (document.layers){ //NS4
                    document.prepage.visibility = 'hidden';
                }
                else { //IE4
                    document.all.prepage.style.visibility = 'hidden';
                }
            }
        }
    
</script>

<s:if test="%{abc==null}">
<%
	 
	if(request.getMethod()=="POST")
	{
		 Connection con = null;
                 Connection condb2=null;
                 con = new connection().getConnection();
                 condb2 = new connectiondb2().getConnection();  
                 
                 SimpleDateFormat userformat=new SimpleDateFormat("dd/MM/yyyy");
                 SimpleDateFormat myformat=new SimpleDateFormat("dd-MMM-yyyy");

		try
		{ 
		String output=request.getParameter("filetype");
                String toawbdate=request.getParameter("toawbdate");
                String facility=request.getParameter("FACI");
                String pch=request.getParameter("PCH");
                String dat1=myformat.format(userformat.parse(request.getParameter("date1")));
                String dat2=myformat.format(userformat.parse(request.getParameter("date2")));
                
		byte[] bytes = null;
                String query = " ";  
                Map parameters = new HashMap();
                
                if(facility!=null && facility.length()>0){
                   query+=" and a.location LIKE '"+facility+"'";
                }
                if(pch!=null && pch.length()>0){
                   query+=" and a.cost_centre like '"+"%"+pch.toUpperCase()+"%"+"'";
                }
                
                 
            //Qry += " and FACILITY='"+FACILITY+"'"; 
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }  
            
            JasperReport report = null;
            String filename="Report";
           
             parameters.put("facility",facility);
             parameters.put("dat1",dat1);
             parameters.put("dat2",dat2);
             
             
             
             
             List ItemBeanlist=new ArrayList();
             
                 
                  
                   String ordr="";
                   String inv="";
                   double ordrqty=0.0;
                   double dlvqty=0.0;
                   double invqty=0.0;
                   double shipqty=0.0;
                   double perorqty=0.0;
                   double fobqty=0.0;
                   
                  String ptype="t_o_date";
                  
                    PreparedStatement stat2 = con.prepareStatement("select distinct  a.cost_centre,substr(b.item_no,1,4) item, buyer from ei_endors_mast a,ei_endors_dtls b  where NVL(t_mod,'NA')='LGM4' and nvl(surrender_yn,'N')<>'Y' AND a.year=b.year and a.company=b.company  and a.inv_no=b.inv_no and  "+toawbdate+" between '"+dat1+"' and '"+dat2+"' "+query+" order by substr(b.item_no,1,4)");
                    ResultSet result2 = stat2.executeQuery();
                        while (result2.next())
                        {  
                            ItemBean bean = new ItemBean();
                            
                            bean.setPCH(result2.getString("cost_centre"));
                            bean.setITM(result2.getString("item"));
                            bean.setBUYER(result2.getString("buyer"));
                            //InvLineList.add(new Itmlistbean(result2.getString("cost_centre"), result2.getString("item"), result2.getString("buyer")));
                        
                       PreparedStatement stat3 = condb2.prepareStatement("select obcono,obhdpr,sum(oborqt) qty,sum((obdlqt+obivqt)) dlqty  from ooline where obcono=111 and oborst between 22 and 79 and obhdpr=? group by obcono,obhdpr");
                       stat3.setString(1,result2.getString("item"));
                       ResultSet result3 = stat3.executeQuery();
                        while(result3.next())
                        {  
                            ordrqty=ordrqty+Double.valueOf(result3.getString("qty"));
                            dlvqty=dlvqty+Double.valueOf(result3.getString("dlqty"));
                            
                            ordr=result3.getString("qty");
                            
                            bean.setORDERQTY(result3.getString("qty"));
                            bean.setDLVQTY(result3.getString("dlqty"));
                            
                            bean.setTOT_ORDRQTY(ordrqty);
                            bean.setTOT_DLVQTY(dlvqty);
                        }
                         if(stat3!=null){
                              stat3.close();
                           }
                            if(result3!=null){
                              result3.close();
                           }
                         
                      PreparedStatement stat4 = con.prepareStatement("select a.cost_centre pch,substr(item_no,1,4) itemno,sum(b.qty_endors) invqty,sum(decode(awbdate,null,0,b.qty_endors)) shipqty,sum((b.qty_endors*(price_fc+nvl(price_misc,0)))) fobamt from ei_endors_mast a,ei_endors_dtls b where NVL(a.t_mod,'NA')='LGM4' and a.year=b.year and a.company=b.company and a.inv_no=b.inv_no and nvl(surrender_yn,'N')<>'Y' and "+toawbdate+" >='"+dat1+"' and "+toawbdate+" <='"+dat2+"' and substr(b.item_no,1,4)=? "+query+" group by cost_centre,substr(item_no,1,4)");
                      stat4.setString(1,result2.getString("item"));
                       ResultSet result4 = stat4.executeQuery();
                        while(result4.next())
                        {  
                            
                            invqty=invqty+Double.valueOf(result4.getString("invqty"));
                            shipqty=shipqty+Double.valueOf(result4.getString("shipqty"));
                            fobqty=fobqty+Double.valueOf(result4.getString("fobamt"));
                            
                            inv=result4.getString("invqty");
                            
                            bean.setINVQTY(result4.getString("invqty"));
                            bean.setSHIPQTY(result4.getString("shipqty"));
                            bean.setFOBAMT(result4.getString("fobamt"));
                            
                            bean.setTOT_INVQTY(invqty);
                            bean.setTOT_SHIPQTY(shipqty);
                            bean.setTOT_FOBQTY(fobqty);
                        }
                           if(stat4!=null){
                              stat4.close();
                           }
                            if(result4!=null){
                              result4.close();
                           }
                        
                            // bean.setINVLINELIST(InvLineList);
                         
                         
                         double orderper=Double.valueOf(inv)/Double.valueOf(ordr)*100;
                         
                         perorqty=perorqty+orderper;
                         
                         bean.setORDERPER(orderper);
                         bean.setTOT_PERORDRQTY(perorqty);
                    
                         
                          
                         
                            ItemBeanlist.add(bean);
                    }
                           
             
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/PRE/ItemwiseReport.jasper"));
             
             filename="ItemwiseReport";
             
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/PRE")); 
            parameters.put("REPORT_CONNECTION", con);  
            
          	 
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(ItemBeanlist));
            response.reset();
            ServletOutputStream out1 = response.getOutputStream();
            //response.reset();
            response.addCookie(new Cookie("fileDownloadToken", request.getParameter("download_token_value_id")));
       		if (output.equals("PDF")) {
                response.setHeader("Content-Disposition", "attachment; filename="+filename+".pdf");
                response.setHeader("cache-control", "no-cache");
                response.setDateHeader("Last-Modified", 123);
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(print, out1);
                out1.flush();
                out1.close();
            } else {  
                JRXlsExporter exporter = new JRXlsExporter();
                ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                //exporter.setParameter(JRExporterParameter.OUTPUT_FILE, "C:\\JSP\\");
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filename+".xls");
                exporter.exportReport();
                bytes = xlsReport.toByteArray();
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename="+filename+".xls;");
                response.setContentLength(bytes.length);
                xlsReport.close();
                OutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			response.getWriter().write(e.getMessage());
		}
	}
         
%>
 </s:if> 
<body> 
      <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="../../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
	<form name="create_pdf_form" id="create_pdf_form" action="ItemwiseReport.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      Item Wise Order Qty vs Ship Qty
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            
                             <tr> 
                                <td valign="top" class="label-1" style="background-color: white;">Date From<span class="manded">*</span></td>
                                <td colspan="0"  align="center" class="label-1" style="background-color: white;">
                                    <input type="text" id="date1" name="date1" readonly="readonly" class="texts" onkeypress="return tabE(this,event);"/>
                                </td>
                                <td valign="top" class="label-1" style="background-color: white;">Date To<span class="manded">*</span></td>
                                <td colspan="0"  align="center" class="label-1" style="background-color: white;">
                                    <input type="text" id="date2" name="date2" readonly="readonly" class="texts" onkeypress="return tabE(this,event);"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label-1" style="background-color: white;">Location</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="0">
                                 <s:select    list="#{'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:100pt;font-weight:bold" theme="simple" name="FACI"  /> 
                            
                                </td>
                                <td class="label-1" style="background-color: white;">Pch</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="0">
                                <input type="text" name="PCH" id="PCH"  cssStyle="text-transform:uppercase;width:250pt" class="texts" theme="simple" />
                                </td>
                            </tr>

                            <tr>
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Date
                             	</td>
                             	<td colspan="7" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'T_O_DATE':'TO Date','awbdate':'Awb Date'}" value="%{'T_O_DATE'}" name="toawbdate" id="toawbdate" theme="simple" ></s:radio>
                             	</td>
                             </tr> 
                           <tr>
                                 
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Output&nbsp;Format
                             	</td>
                             	<td colspan="7" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'PDF':'PDF','XLS':'Excel'}" value="%{'PDF'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                             </tr> 
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="ItemwiseReport.jsp"' >&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="submit" id="submitbtn" value="Finish" class="whitesubmitbutton">
                             	</td>
                             </tr>	
                        </table>
                     </td>
                 </tr>
             </table> 
                  
 </form>
</body> 
</html> 
