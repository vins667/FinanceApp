
<%@page import="java.util.Date"%>
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
 <%@page import="java.util.Date" %> 
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
 
       <%
       String sessionId = session.getId(); 
       Map session1 = ActionContext.getContext().getSession();
       String usrid = (String) session1.get("sessUserId");   
         
       %> 
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
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yy"); 
             
             
            
                  String usr = request.getParameter("userid");
                  
                  String dt1 = dateformat1.format(dateformat.parse(request.getParameter("date1")));
                  String dt2 = dateformat1.format(dateformat.parse(request.getParameter("date2")));
                   String port = request.getParameter("PORT_CODE");
                  String p_option = request.getParameter("TYPE_ON");
                  String invt = request.getParameter("inv_tp");
                  
                  String p_cond="";
                  String p_head="";
                 if (p_option.equals("FWP"))
                 {
                    p_cond=" and a.fwd_port is null ";
                    p_head="  FWD To Port Pending ";
                 }else if (p_option.equals("LICP"))
                 {
                    p_cond=" and a.lic_no is null ";
                    p_head=" Licence Awaitted ";
                 }else if (p_option.equals("FWA"))
                 {
                    p_cond=" and a.fwd_ac is null ";
                    p_head=" FWD To A/C Pending ";
                 }else if (p_option.equals("ACF"))
                 {
                    p_cond=" and a.fwd_ac is not null ";
                    p_head=" FWD To A/C ";
                 }else{
                    p_cond=" and 1=1 ";
                    p_head=" ALL ";
                 }
                   
                    String x=null;
                    if(usrid!=null){
                      x =usrid;
                    }
                    else{
                        x=sessionId;
                    }
                  
            
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/GVTINC/")); 
            parameters.put("REPORT_CONNECTION", con); 
            parameters.put("p_loct",request.getParameter("LOCT_CODE"));
            parameters.put("p_query",p_cond);
            parameters.put("p_head",p_head);
            parameters.put("p_date_from",dt1);
            parameters.put("p_date_to",dt2);
            
            
            
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/GVTINC/meis_status.jasper"));
             
             filename="MEIS_STATUS";
             
             
              
          	   
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
	<form name="create_pdf_form" id="create_pdf_form" action="MeisStatusReport.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
      <s:hidden id="userid" name="userid" value="%{#session.sessUserId}" theme="simple"/>
       
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      MEIS Status Report
                    </td>
                </tr>
            </table> 
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 670px; background-color: #6699CC;">
                            <tr>
                               
                                  
                                <td class="label-1" style="background-color: white;">Location </td>
                                 <td class="label-1" style="background-color: white;" colspan="7">
                                     <s:select  headerValue="" list="# {'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:100px;font-weight:bold" theme="simple" id="LOCT_CODE" name="LOCT_CODE"  /> 
                                 </td> 
                            </tr>
                             
                            <tr> 
                                <td class="label-1" style="background-color: white;width:110px">App Date From</td>
                                <td class="label-1" style="background-color: white;"  >
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:100px;"></s:textfield>
                               	</td>
                             
                                <td class="label-1" style="background-color: white;width:100px">App Date To </td>
                                <td class="label-1" style="background-color: white;"  >
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:100px" ></s:textfield>
                                </td>
                                   
                            </tr>
                                
                            <tr>  
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top">Option:
                             	</td>
                                <td colspan="7" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'%':'ALL','LICP':'Licence Awaited','FWP':' Fwd To Port Pending','FWA':' Fwd To A/C Pending','ACF':' Fwd To A/C'}"   value="%{'LICP'}" name="TYPE_ON" id="TYPE_ON" theme="simple" ></s:radio>
                                </td> 
                            </tr>
                             <tr> 
                                  
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Output&nbsp;Format
                             	</td>
                             	<td colspan="7" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'XLS':'Excel','PDF':'PDF'}" value="%{'PDF'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                             </tr> 
                             
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="MeisStatusReport.jsp"' >&nbsp;&nbsp;&nbsp;&nbsp;
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