

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
    	document.create_pdf_form.action="BuyerWiseSale";
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
                 con = new connection().getConnection();
                 
                 SimpleDateFormat userformat=new SimpleDateFormat("dd/MM/yyyy");
                 SimpleDateFormat myformat=new SimpleDateFormat("dd-MMM-yyyy");

		try
		{ 
		String output=request.getParameter("filetype");
                
                String facility=request.getParameter("FACI");
                String byr=request.getParameter("BUYR");
                String cntry=request.getParameter("CNTRY");
                String dat1=myformat.format(userformat.parse(request.getParameter("date1")));
                String dat2=myformat.format(userformat.parse(request.getParameter("date2")));
                
		byte[] bytes = null;
                String query = " ";  
                Map parameters = new HashMap();
                
                 
                
            //Qry += " and FACILITY='"+FACILITY+"'"; 
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }  
            
            JasperReport report = null;
            String filename="Report";
           
             
             parameters.put("p_date_from",dat1);
             parameters.put("p_date_to",dat2);
             parameters.put("p_loct",facility);
             parameters.put("p_buyer",byr);
             parameters.put("p_pch",request.getParameter("pch"));
             parameters.put("p_invtype",request.getParameter("INV_TYP"));
             parameters.put("p_sum",request.getParameter("Report_T"));
             
             parameters.put("p_ord",request.getParameter("Report_ORDR"));
            if (request.getParameter("Report_ORDR").equals("PCH"))
            {
             parameters.put("p_head","PCH");
             } else{parameters.put("p_head","CNTRY"); }
            
             
             
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/POST/BuyerWiseSale.jasper"));
             
             filename="BuyerWiseSale";
             
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/POST")); 
            parameters.put("REPORT_CONNECTION", con);  
            
          	 
            JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
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
	<form name="create_pdf_form" id="create_pdf_form" action="BuyerWiseSale.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      Buyer Wise Sale Report
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
                                <td class="label-1" style="background-color: white;">Facility</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="0">
                                  <s:select type="text" name="FACI" id="FACI" list="# {'%':'All','100':'100','200':'200'}"  cssStyle="width:100pt" class="texts" theme="simple" />
                                </td>
                                <td class="label-1" style="background-color: white;">Inv Type</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="0">
                          	 <s:select name="INV_TYP" id="INV_TYP" list="# {'N':'Normal','S':'Trade Sample','F':'Free Sample','%':'ALL'}" cssStyle="font-size:8pt;text-transform:uppercase;width:100pt;font-weight:bold" theme="simple" />
                                </td>
                                
                            </tr>
                            <tr>
                                <td class="label-1" style="background-color: white;">PCH</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="0">
                                <input type="text" name="PCH" id="PCH"  cssStyle="width:250pt" class="texts" theme="simple" />
                                </td>
                                <td class="label-1" style="background-color: white;">Buyer</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="0">
                                <input type="text" name="BUYR" id="BUYR"  cssStyle="text-transform:uppercase;width:250pt" class="texts" theme="simple" />
                                </td>
                            </tr>
                            
                            <tr>
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Report
                             	</td>
                             	<td colspan="7" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'DETAIL':'With S/B Detail','SUMMARY':'Summary Only'}" value="%{'DETAIL'}" name="Report_T" id="Report_T" theme="simple" ></s:radio>
                             	</td>
                             </tr> 
                           <tr>
                               <td class="label-1" style="background-color: white;text-align: left;" valign="top">Report Order
                             	</td>
                             	<td colspan="0" style="background-color: white;text-align:left;" class="label-1">
                             		<s:select list="# {'PCH':'PCH','CNTRY':'CNTRY'}" value="%{'PCH'}" name="Report_ORDR" id="Report_ORDR" theme="simple" />
                             	</td>
                                 
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Output&nbsp;Format
                             	</td>
                             	<td colspan="0" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'PDF':'PDF','XLS':'Excel'}" value="%{'PDF'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                             </tr> 
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="BuyerWiseSale.jsp"' >&nbsp;&nbsp;&nbsp;&nbsp;
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
