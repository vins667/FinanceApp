
<%@page import="java.sql.CallableStatement"%>
<%@page import="org.apache.struts2.components.Hidden"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="oracle.sql.DATE"%>
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
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
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
        
    };
    
   
    
     
		function validate() 
		{
			DATE_FROM=document.getElementById("date1");
		        DATE_TO=document.getElementById("date2");
                        aa =DATE_FROM.value;
                        bb=aa.substring(0,2);
                        cc=aa.substring(3,5);
                        dd=aa.substring(6,10);
                       
		     	if(DATE_FROM.value==''){
		    		alert("'From' Date cannot be empty");
		    		return false;
		    	}
                     
		    	if ((dd+cc+bb)<20160504 )
                          {
                            alert("From Date cannot be Before 04-May-2016");
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

		try
		{ 
                    
		String output=request.getParameter("filetype");
		byte[] bytes = null;
            String query = " ";  
            Map parameters = new HashMap();
            
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }                    
            JasperReport report = null;
            String filename="Report";
            String saleorderstr = "";
            String saleorderflag="";
            
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yyyy"); 
            
             String usr = request.getParameter("userid"); 
             String loc = request.getParameter("LOCT_CODE");
              String dt1 = dateformat1.format(dateformat.parse(request.getParameter("date1")));
               String dt2 = dateformat1.format(dateformat.parse(request.getParameter("date2")));
               
                 String cntryorgin = request.getParameter("cntry_orgin");
                 if(cntryorgin!=null && cntryorgin.length()>0)
                 {  
            	 query+=" and nvl(b.cntry_origin,'IN') like '"+cntryorgin+"'";
                 } 
                 
                 String exptyp = request.getParameter("exp_typ");
                 if(exptyp!=null && exptyp.length()>0)
                 {  
            	 query+=" and b.exp_type like '"+exptyp+"'";
                 }
                 
                  String invt = request.getParameter("inv_tp");
                if(invt!=null && invt.length()>0)
                 {  
            	 query+=" and nvl(b.self_tp,'N') like '"+invt+"'";
                 }
               
             
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/GVTINC/Meis2016Summary.jasper"));
             
             filename="MEIS_Summary";
        System.out.println("loct "+loc+" origin "+cntryorgin+" selftp "+invt+" exp "+exptyp+" datefrom "+dt1+" - "+dt2);  
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/GVTINC")); 
            parameters.put("REPORT_CONNECTION", con); 
            parameters.put("loc", loc);
            parameters.put("p_origin",cntryorgin);
            parameters.put("p_selftp",invt);
            parameters.put("p_exptype",exptyp);
            parameters.put("DateFrom", dt1);
            parameters.put("DateTo", dt2);
           // parameters.put("g_query", query);
           
            
          	 
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
	<form name="create_pdf_form" id="create_pdf_form" action="MeisEntitlementSummary.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
      <s:hidden id="userid" name="userid" value="%{#session.sessUserId}" theme="simple"/>
       
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      Meis Entitlement Summary
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            <tr>
                               
                                  
                                <td class="label-1" style="background-color: white;">Location </td>
                                 <td class="label-1" style="background-color: white;" colspan="7">
                                     <s:select  headerValue="" list="# {'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:220px;font-weight:bold" theme="simple" id="LOCT_CODE" name="LOCT_CODE" value="%{LOCT_CODE}" /> 
                                 </td> 
                                
                                
                                 
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                    <s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px;"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:150px">Date To </td>
                                <td class="label-1" style="background-color: white;" colspan="2">
                                <s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px" ></s:textfield>
                                </td>
                            </tr>
                             <tr>
                                 
                                <td class="label-1" style="background-color: white;" >Country of Origin</td>
                                <td align="left" class="label-1" style="background-color: white;"colspan="3">
                                <s:select id="cntry_orgin" name="cntry_orgin" list="%{Cntry_Origin}" value="%{cntry_orgin}" cssStyle="font-size:10pt;width:200px;font-size:9pt;" theme="simple" /></td>
                                 
                                
                                <td class="label-1" style="background-color: white;">Exp Type</td>
                                <td class="label-1" style="background-color: white;" colspan="2">
                                    <s:select id="exp_typ" name="exp_typ" list="%{Exp_Type}" headerKey="%" headerValue="ALL" cssStyle="font-size:10pt;width:200px;font-size:9pt;" theme="simple" />
                               	</td>
                            </tr>
                            <tr>
                                
                                <td class="label-1" style="background-color: white;" >Inv Type</td>
                                <td align="left" class="label-1" style="background-color: white;"colspan="10">
                                <s:select id="inv_tp" name="inv_tp" label="Invoice Type" cssStyle="font-size:10pt;width:200px;font-size:9pt;" theme="simple"  value="%{inv_tp}"  list="# {'N':'Normal','S':'Trade Sample','F':'Free Sample','%':'ALL'}" /></td>
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
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="MeisEntitlementSummaryA"' >&nbsp;&nbsp;&nbsp;&nbsp;
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