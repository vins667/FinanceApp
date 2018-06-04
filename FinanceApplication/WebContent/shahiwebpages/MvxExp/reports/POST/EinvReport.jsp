 

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
    
   
 
                
            
            function tabEStyleTp(obj,e)
            {var e=(typeof event!='undefined')?window.event:e;// IE : Moz
                if(e.keyCode==13)
                {var ele = document.forms[0].elements;
                    for(var i=0;i<ele.length;i++)
                    {var q=(i==ele.length-1)?0:i+1;// if last element : if any other
                        if(obj==ele[i]){additemTyp();break}
                    }
                    return false;
                }
            }
            
            function tabE(obj, e) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for ( var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other
                        if (obj == ele[i]) { 
                            ele[q].focus();
                            break
                        }
                    }
                    return false;
                }
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
            String query = " ";  String p_based="";
            String p_field="";
            Map parameters = new HashMap();
            
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }                    
            JasperReport report = null;
            String filename="Report";
            
             String BUYR = request.getParameter("BUYR");
             
             if(BUYR!=null && BUYR.length()>0){
              query+=" and nvl(a.BUYER,'NA') like '"+BUYR+"%"+"'";
             
                
             }
     
             
              String rep_op = request.getParameter("repoption");
                if (rep_op.equals("PU"))
                {
                  query=query+" and nvl(a1.portal_app,'N')='Y'";
                }if (rep_op.equals("NPU"))
                {
                  query=query+" and nvl(a1.portal_app,'N')<>'Y'";
                }
              
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yy"); 
             String p_head="";
        //     parameters.put("date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
        //     parameters.put("date_to", dateformat1.format(dateformat.parse(request.getParameter("date2")))); 
            
             String REPORT_T=request.getParameter("selectedId");
             
             parameters.put("p_loct",request.getParameter("LOCT"));
             parameters.put("p_type",REPORT_T);
             
             
             if (REPORT_T.equals("TTO"))
             {
               query=query+" and a.t_o_date is  not null and a.tto_date is null";
               p_head=" TO done TTO not done";
               p_field=" to_number(trunc(sysdate)-trunc(t_o_date) )";
               p_based=" Delay days based on TO Date ";
             }
             else if (REPORT_T.equals("PRE"))
             {
               query=query+" and a.t_o_date is  not null and a.tto_date is not null and a.pre_docs_sent is null";
               p_head=" TO & TTO Done Pre Docs not sent";
               p_field="to_number(trunc(sysdate)-trunc(t_o_date) )";
               p_based=" Delay days based on TO Date ";
             }    
             else if (REPORT_T.equals("FBA"))
             {
               query=query+" and a.t_o_date is  not null and a.tto_date is not null and a.pre_docs_sent is not null and d.TR_DATE is null";
               p_head=" TO,TTO,Pre Docs Done, FBA not done";
                p_field="to_number(trunc(sysdate)-trunc(etd_date) )";
                p_based=" Delay days based on ETD Date ";
             }    
             else if (REPORT_T.equals("FTP"))
             {
               query=query+" and a.t_o_date is  not null and a.tto_date is not null and a.pre_docs_sent is not null and D.TR_DATE is not null and A.DOC_SEND is null";
               p_head=" TO,TTO,PreDocs,FBA Done FTP Not done";
                p_field="to_number(trunc(sysdate)-trunc(t_o_date) )";
                p_based=" Delay days based on TO Date ";
             }   
             else if (REPORT_T.equals("FIN"))
             {
               query=query+" and a.doc_send is  not null and A.FIN_DATE is null";
               p_head=" FTP Done 77 Not done";
                p_field="to_number(trunc(sysdate)-trunc(doc_send) )";
                p_based=" Delay days based on FTP Date ";
             } 
             else
             {
              query=query+" and a.t_o_date is  not null and c.tr_DATE is null";
               p_head=" TO Done PU Not done";
                p_field="to_number(trunc(sysdate)-trunc(t_o_date) )";
                p_based=" Delay days based on TO Date ";
             } 
             parameters.put("p_query",query);
             parameters.put("p_head",p_head);
             parameters.put("p_field",p_field);
             parameters.put("cal_based",p_based);
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/POST/InvoiceTrackReport.jasper"));
             filename="";
           
            // String date1 = request.getParameter("date1");
           //  String date2 = request.getParameter("date2");
             Collection list=null;
             
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
	<form name="create_pdf_form" id="create_pdf_form" action="EinvReport.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      e-Invoice Tracking Report
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white">Location</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="3">
                                <s:select name="LOCT" id="LOCT" list="# {'200':'200','100':'100','ALL':'%'}" cssStyle="text-transform:uppercase;width:150pt;font-weight:bold" theme="simple" />
                             	</td>
                                 <td class="label-1" style="background-color: white;">Buyer </td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="3">
                                         <s:textfield name="BUYR" id="BUYR" cssStyle="text-transform:uppercase;width:150pt" theme="simple" />
                             	</td>
                            </tr>
                             
                            <tr>
                                 
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Report Option
                             	</td>
                             	<td colspan="7" style="background-color: white;text-align:left;" class="label-1">
                           
                                  <s:iterator    value=" # {'TTO':'TO done TTO not done','PRE':'TO&TTO Done Pre Doc not sent','FBA':'TO,TTO,Pre Docs Done Forwarder Bill not received','FTP':'TO,TTO,Pre,FBA Done FTP Not done ','FIN':'FTP Done 77 Not done','PU':'TO Done PU Not done'}"  var="some"> 
                                         <s:radio  value="%{'TTO'}" key="selectedId" list="#some" listKey="key" listValue="value" theme="simple" /><br/>
                                  </s:iterator >
                                </td>
                             </tr> 
                             <tr>
                                 
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Portal Option
                             	</td>
                             	<td colspan="7" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'PU':'Portal Buyer','NPU':'Non Portal Buyer','ALL':'ALL'}" value="%{'ALL'}" name="repoption" id="repoption" theme="simple" ></s:radio>
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
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="EinvReport.jsp"'>&nbsp;&nbsp;&nbsp;&nbsp;
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