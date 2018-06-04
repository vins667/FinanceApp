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
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Shahi Exports Pvt Ltd.</title>
      
        <link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>

 
		<script language="javascript" type="text/javascript" src = "script/function.js" ></script>
   		<script type="text/javascript" src="script/jquery.1.4.2.js"></script>
		<script type="text/javascript" src="script/jquery_blockUI.js"></script>
		<script type="text/javascript" src="script/jquery_cookie.js"></script>
		<script type="text/javascript" src="script/hidediv.js"></script>     
       
        <link rel="stylesheet" type="text/css" media="all" href="style/jsDatePick_ltr.min.css" />
        <link href="style/style.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="script/jsDatePick.min.1.3.js"></script>
			        
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
    
   
    function search()
    {
    	if(validate()==true)
    	{
    	document.create_pdf_form.action="BOSPrintRep?repFlag=BOS";
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
            byte[] bytes = null;  
            Map parameters = new HashMap();
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/PRE/";
             
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yyyy");
              parameters.put("SUBREPORT_DIR",path);
              parameters.put("REPORT_CONNECTION", con);  
              parameters.put("p_loct", request.getParameter("LOCT_CODE")); 
              parameters.put("p_bos_from", request.getParameter("BOS_FROM"));
              parameters.put("p_bos_to", request.getParameter("BOS_TO"));
              parameters.put("date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
              parameters.put("date_to", dateformat1.format(dateformat.parse(request.getParameter("date2")))); 
              JasperReport report = null;
              String facility=request.getParameter("LOCT_CODE");
        
              if (facility.substring(0,1).equals("1"))
              { 
                report = (JasperReport) JRLoader.loadObject(path +"bos_prn_100.jasper");
              }
              if (facility.substring(0,1).equals("2"))
              {
                  report = (JasperReport) JRLoader.loadObject(path +"bos_prn_200.jasper");
              }   
             JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
              
            OutputStream out1 = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "inline; filename=bos.pdf");
            response.setHeader("cache-control", "no-cache");
            response.setDateHeader("Last-Modified", 123);
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(print, out1);
            out1.flush();
            out1.close();  	
	}
%>
 </s:if>  
<body>  
     <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
	<form name="create_pdf_form" id="create_pdf_form" action="BosPrint.jsp" method="POST">
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr> 
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                       Bill of Sale Print
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            <tr>
                                 <td class="label-1" style="background-color: white;width:250px">BOS Loct : </td>
                                 
                                  <td class="label-1" style="background-color: white;" colspan="3">
                                    <s:if test='%{date1==null}'>
                                         <s:select name="LOCT_CODE" list="warehouselist" cssStyle="text-transform:uppercase;width:80pt;font-size:9pt;" theme="simple" value="%{loctcode}"/>
                                    </s:if>
                                      <s:else>
                                          <s:textfield name="LOCT_CODE" readonly="true" theme="simple" value="%{LOCT_CODE}" cssStyle="text-transform:uppercase;width:80pt;font-size:9pt;" />
                                      </s:else>
                                  
                                  </td> 
                               
                               
                            </tr> 
                            <tr> 
                                <td class="label-1" style="background-color: white;width:250px">Date From</td>
                                <td class="label-1" style="background-color: white;" >
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:100px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:500px">Date To </td>
                                <td class="label-1" style="background-color: white;" >
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:100px" ></s:textfield>
                                        <button  id="searchheadId" class="sexybutton" onclick="search()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                   </td>           
                          
                            </tr>
                            <tr> 
                             <td class="label-1" style="background-color: white;width:200px">BOS No.From :</td>
                                <td class="label-1" style="background-color: white;" >
                                   <s:textfield name="BOS_FROM"  cssStyle="text-transform:uppercase;width:80pt;font-size:9pt;" theme="simple" value="%{BOS_FROM}"/>
                                </td>
                           
                               <td class="label-1" style="background-color: white;width:500px">BOS No.T0:</td>
                                <td class="label-1" style="background-color: white;" >
                                   <s:textfield name="BOS_TO"  cssStyle="text-transform:uppercase;width:80pt;font-size:9pt;" theme="simple" value="%{BOS_TO}"/>
                                </td>
                            </tr>
                              
                             <tr>
                             	<td colspan="4" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="BosPrintRep"' >&nbsp;&nbsp;&nbsp;&nbsp;
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
