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
      
        <link href="<s:url value="../../css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/sexybuttons.css"/>

 
		<script language="javascript" type="text/javascript" src = "../../script/function.js" ></script>
   		<script type="text/javascript" src="../../script/jquery.1.4.2.js"></script>
		<script type="text/javascript" src="../../script/jquery_blockUI.js"></script>
		<script type="text/javascript" src="../../script/jquery_cookie.js"></script>
		<script type="text/javascript" src="../../script/hidediv.js"></script>     
       
        <link rel="stylesheet" type="text/css" media="all" href="../../style/jsDatePick_ltr.min.css" />
        <link href="../../style/style.css" rel="stylesheet" type="text/css"/>
        <link href="../../style/main.css" rel="stylesheet" type="text/css"/>
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
    
   
    function search()
    {
    	if(validate()==true)
    	{
    	document.create_pdf_form.action="BuyerPortalReportA";
    	document.create_pdf_form.submit();
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
		    	insertBuyer();
		    	return true;
			
		} 
    
    
    
    function addBuyer()
    {
    	var toSelect_Length = document.create_pdf_form.BUYERLIST.options.length;
        while(document.create_pdf_form.BUYER.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.BUYER.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.BUYERLIST.options[i].value ==document.create_pdf_form.BUYER.options[index].value)
                {
                    alert(document.create_pdf_form.BUYER.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.BUYERLIST.options[toSelect_Length] = new Option(document.create_pdf_form.BUYER.options[index].text);
            document.create_pdf_form.BUYERLIST.options[toSelect_Length].value =document.create_pdf_form.BUYER.options[index].value;    
            document.create_pdf_form.BUYER.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removeBuyer()
    {
    	var toSelect_Length = document.create_pdf_form.BUYER.options.length;
        while(document.create_pdf_form.BUYERLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.BUYERLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.BUYER.options[i].value ==document.create_pdf_form.BUYERLIST.options[index].value)
                {
                    alert(document.create_pdf_form.BUYERLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.BUYER.options[toSelect_Length] = new Option(document.create_pdf_form.BUYERLIST.options[index].text);
            document.create_pdf_form.BUYER.options[toSelect_Length].value =document.create_pdf_form.BUYERLIST.options[index].value;    
            document.create_pdf_form.BUYERLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertBuyer(){  
        var tnl = document.getElementById("BUYERLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
   
   
</script>
 
<s:if test="%{abc==null}">
<%
	
	if(request.getMethod()=="POST")
	{
		 Connection con = null;
                 con = new connection().getConnection();
                 Map parameters = new HashMap();
                 
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yy"); 
             parameters.put("date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
             parameters.put("date_to", dateformat1.format(dateformat.parse(request.getParameter("date2"))));
                 
		try
		{ 
                    String output=request.getParameter("filetype");
			String report_t=request.getParameter("report_t");
			byte[] bytes = null;
            String Qry = " ";
            
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }                    
            JasperReport report = null;
            String filename="Report";
            
            String Buyerlist[] = request.getParameterValues("BUYERLIST");
            String Str2 = "";
            String flag3="";
            
//            String LOC = request.getParameter("LOC");
//            if(LOC!=null && LOC.length()>0){
//               Qry+=" and a.LOCATION='"+LOC+"'";
//            }
            
//            String self_tp = request.getParameter("self_tp");
//            if(self_tp!=null && self_tp.length()>0){
//               Qry+=" and a.LOCATION='"+LOC+"'";
//            } 
           
            if(Buyerlist!=null && Buyerlist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(A.BUYER) in(";
            	 for(int i=0; i<Buyerlist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Buyerlist[i]+"'" ;                            	
                        	flag3=""+Buyerlist[i];
                        }else{
                        	cntr = cntr+",'"+Buyerlist[i]+"'" ;
                        	flag3 = flag3+", "+Buyerlist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/PRE/";
            if (report_t.equals("L") && report_t.length()>0) { 
             report = (JasperReport) JRLoader.loadObject(path +"BuyerPortalLogistcReport.jasper");
             filename="BuyerPortalLogistcReport";}
            else if(report_t.equals("A") && report_t.length()>0) {
               report = (JasperReport) JRLoader.loadObject(path +"BuyerPortalARReport.jasper");
               filename="BuyerPortalARReport";
            }
            else {
               report = (JasperReport) JRLoader.loadObject(path +"BuyerPortalPUReport.jasper");
               filename="BuyerPortalPUReport";
            }
             
             Collection list=null;
             
            parameters.put("SUBREPORT_DIR",path); 
            parameters.put("REPORT_CONNECTION", con);  
            
            
            parameters.put("p_query",Qry);
        
            parameters.put("LOC",request.getParameter("LOCT_CODE"));
            parameters.put("self_tp",request.getParameter("self_tp"));
            
           	//response.getWriter().write(Qry);
              
          	 
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
	<form name="create_pdf_form" id="create_pdf_form" action="BuyerPortalReport.jsp" method="POST">
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                       Buyer Portal Report
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 500px; background-color: #6699CC;">
                            <tr>
                                 <td class="label-1" style="background-color: white;">Location </td>
                                 <td class="label-1" style="background-color: white;" colspan="3">
                                     <s:select  headerValue="" list="# {'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:220px;font-weight:bold" theme="simple" name="LOCT_CODE" id="LOCT_CODE" value="%{LOCT_CODE}" /> 
                                 </td> 
                              <td valign="top" class="label-1" style="background-color: white;text-align:left">Buying&nbsp;House</td>
                              <td valign="top"class="label-1" style="background-color: white;text-align:left" colspan="3">
                                   <s:select theme="simple" list="%{PROC_LIST}" name="BUYING" id="BUYING" headerKey="" headerValue="------Select------" cssClass="texts" cssStyle="width:250px;">
                                   </s:select>
                                </td>
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:220px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:150px">Date To </td>
                                <td class="label-1" style="background-color: white;" colspan="2">
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:220px" ></s:textfield>
                                </td>
                                <td class="label-1" style="background-color: white;">
                                   <button  id="searchheadId" class="sexybutton" onclick="search();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                </td>
                            </tr>
                            <tr>
				<td valign="top" class="label-1" style="background-color: white;text-align:left">Buyer&nbsp;Code</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left" colspan="3">
                                   <s:select theme="simple" list="%{BUYER_LIST}" ondblclick="addBuyer();" name="BUYER" id="BUYER" multiple="true" cssClass="texts" cssStyle="width:100px;height: 80px">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addBuyer();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeBuyer();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;text-align: left" colspan="7" >                                   
                                    <select id="BUYERLIST" multiple="multiple" ondblclick="removeBuyer();" name="BUYERLIST" class="texts" style="width:100px;height: 80px;text-transform: uppercase"> 
                                	</select>
                               </td>
                                
                               	
                               </tr>
                             <tr>
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Output&nbsp;Format
                             	</td>
                             	<td colspan="3" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'PDF':'PDF','XLS':'Excel'}" value="%{'PDF'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                                <td style="background-color: white;" class="label-1">Report</td>
                                <td align="left"  style="background-color: white;" class="label-2" colspan="3" >
                                 <s:radio list="# {'L':'Pending Logistics','A':'Pending AR','P':'PU Pending'}" value="%{'L'}" name="report_t" id="report_t" theme="simple" ></s:radio>
                                    </td>
                             </tr> 
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick="window.location.href='BuyerPortalReportA.action'" >&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="submit" id="submitbtn" value="Finish" class="whitesubmitbutton">
                             	</td>
                             </tr>	
                        </table>
                     </td>
                 </tr>
             </table>  
 </form>
       <script type="text/javascript" language="javascript" src="js/CalendarControl.js"></script>
        <script type="text/javascript">
            function addgarmentItem(){
                var garmentItemInput=document.getElementById("garmentItem");
		
                if(garmentItemInput.value.length==0)
                {
                    alert("You have not entered any value");
                    return;
                }		
                var garmentItemList=document.getElementById("garmentItemList");
                addValue(garmentItemInput,garmentItemList);
            }
            function tabEgarmentItem(obj, e) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for ( var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other
                        if (obj == ele[i]) {
                            addgarmentItem();
                            break
                        }
                    }
                    return false;
                }
            }            
            function removegarmentItem(){
                var garmentItemList=document.getElementById("garmentItemList");
                removeValue(garmentItemList);
            }   
            
       </script>
        
  </body> 
</html> 