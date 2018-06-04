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
    }
    
   
    function search()
    {
    	if(validate()==true)
    	{
    	document.create_pdf_form.action="EPCopyRep?repFlag=EPREP";
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
		    	insertPort();
		    	insertAgent();
		    	insertLicence();
		    	insertAC();
		    	 
		    	return true;
			
		} 
    
    function addPort()
    {
    	var toSelect_Length = document.create_pdf_form.PORT_LIST.options.length;
        while(document.create_pdf_form.PORT_CODE.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PORT_CODE.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PORT_LIST.options[i].value ==document.create_pdf_form.PORT_CODE.options[index].value)
                {
                    alert(document.create_pdf_form.PORT_CODE.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PORT_LIST.options[toSelect_Length] = new Option(document.create_pdf_form.PORT_CODE.options[index].text);
            document.create_pdf_form.PORT_LIST.options[toSelect_Length].value =document.create_pdf_form.PORT_CODE.options[index].value;    
            document.create_pdf_form.PORT_CODE.options[index] = null;
            toSelect_Length++;    
        } 
    }
    

    function removePort()
    {
    	var toSelect_Length = document.create_pdf_form.PORT_CODE.options.length;
        while(document.create_pdf_form.PORT_LIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PORT_LIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PORT_CODE.options[i].value ==document.create_pdf_form.PORT_LIST.options[index].value)
                {
                    alert(document.create_pdf_form.PORT_LIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PORT_CODE.options[toSelect_Length] = new Option(document.create_pdf_form.PORT_LIST.options[index].text);
            document.create_pdf_form.PORT_CODE.options[toSelect_Length].value =document.create_pdf_form.PORT_LIST.options[index].value;    
            document.create_pdf_form.PORT_LIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertPort(){  
        var tnl = document.getElementById("PORT_LIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
    
    
    function addAgent()
    {
    	var toSelect_Length = document.create_pdf_form.AGENTLIST.options.length;
        while(document.create_pdf_form.AGENT.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.AGENT.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.AGENTLIST.options[i].value ==document.create_pdf_form.AGENT.options[index].value)
                {
                    alert(document.create_pdf_form.AGENT.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.AGENTLIST.options[toSelect_Length] = new Option(document.create_pdf_form.AGENT.options[index].text);
            document.create_pdf_form.AGENTLIST.options[toSelect_Length].value =document.create_pdf_form.AGENT.options[index].value;    
            document.create_pdf_form.AGENT.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removeAgent()
    {
    	var toSelect_Length = document.create_pdf_form.AGENT.options.length;
        while(document.create_pdf_form.AGENTLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.AGENTLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.AGENT.options[i].value ==document.create_pdf_form.AGENTLIST.options[index].value)
                {
                    alert(document.create_pdf_form.AGENTLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.AGENT.options[toSelect_Length] = new Option(document.create_pdf_form.AGENTLIST.options[index].text);
            document.create_pdf_form.AGENT.options[toSelect_Length].value =document.create_pdf_form.AGENTLIST.options[index].value;    
            document.create_pdf_form.AGENTLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }

    function insertAgent(){  
        var tnl = document.getElementById("AGENTLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
    
    function addLicence()
    {
    	var toSelect_Length = document.create_pdf_form.LICENCELIST.options.length;
        while(document.create_pdf_form.Licence.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.Licence.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.LICENCELIST.options[i].value ==document.create_pdf_form.Licence.options[index].value)
                {
                    alert(document.create_pdf_form.Licence.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.LICENCELIST.options[toSelect_Length] = new Option(document.create_pdf_form.Licence.options[index].text);
            document.create_pdf_form.LICENCELIST.options[toSelect_Length].value =document.create_pdf_form.Licence.options[index].value;    
            document.create_pdf_form.Licence.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    
    function removeLicence()
    {
    	var toSelect_Length = document.create_pdf_form.Licence.options.length;
        while(document.create_pdf_form.LICENCELIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.LICENCELIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.Licence.options[i].value ==document.create_pdf_form.LICENCELIST.options[index].value)
                {
                    alert(document.create_pdf_form.LICENCELIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.Licence.options[toSelect_Length] = new Option(document.create_pdf_form.LICENCELIST.options[index].text);
            document.create_pdf_form.Licence.options[toSelect_Length].value =document.create_pdf_form.LICENCELIST.options[index].value;    
            document.create_pdf_form.LICENCELIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
     
    function insertLicence(){  
        var tnl = document.getElementById("LICENCELIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
  
    function addAC()
    { 
    	var toSelect_Length = document.create_pdf_form.ACLIST.options.length;
        while(document.create_pdf_form.AC.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.AC.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.ACLIST.options[i].value ==document.create_pdf_form.AC.options[index].value)
                {
                    alert(document.create_pdf_form.AC.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.ACLIST.options[toSelect_Length] = new Option(document.create_pdf_form.AC.options[index].text);
            document.create_pdf_form.ACLIST.options[toSelect_Length].value =document.create_pdf_form.AC.options[index].value;    
            document.create_pdf_form.AC.options[index] = null;
            toSelect_Length++;    
        }  
    }
    
    function removeAC()
    {
    	var toSelect_Length = document.create_pdf_form.AC.options.length;
        while(document.create_pdf_form.ACLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.ACLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.AC.options[i].value ==document.create_pdf_form.ACLIST.options[index].value)
                {
                    alert(document.create_pdf_form.ACLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.AC.options[toSelect_Length] = new Option(document.create_pdf_form.ACLIST.options[index].text);
            document.create_pdf_form.AC.options[toSelect_Length].value =document.create_pdf_form.ACLIST.options[index].value;    
            document.create_pdf_form.ACLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertAC(){  
        var tnl = document.getElementById("ACLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
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
            String Qry = " "; String LicQry=" ";
            Map parameters = new HashMap();
            
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }                    
            JasperReport report = null;
            String filename="Report";
            
            String Portlist[] = request.getParameterValues("PORT_LIST");
            String saleorderstr = "";
            String saleorderflag="";
            if(Portlist!=null && Portlist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(e.loading) in(";
            	 for(int i=0; i<Portlist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Portlist[i]+"'" ;                            	
                            saleorderflag=""+Portlist[i];
                        }else{
                        	cntr = cntr+",'"+Portlist[i]+"'" ;
                        	saleorderflag = saleorderflag+", "+Portlist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            String Agentlist[] = request.getParameterValues("AGENTLIST");
            String str = "";
            String flag="";
            if(Agentlist!=null && Agentlist.length>0)
            {
            	String cntr=null;
            	Qry+=" and trim(e.agent) in(";
            	 for(int i=0; i<Agentlist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Agentlist[i]+"'" ;                            	
                        	flag=""+Agentlist[i];
                        }else{
                        	cntr = cntr+",'"+Agentlist[i]+"'" ;
                        	flag = flag+", "+Agentlist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
            String Licencelist[] = request.getParameterValues("LICENCELIST");
            String Str = "";  String LicStat=" and 1=1";
            String flag1="";
            if(Licencelist!=null && Licencelist.length>0)
            {
            	String Liccntr=null;
            	LicQry+=" and ref_no in(";
            	 for(int i=0; i<Licencelist.length; i++)
                 {
                        if(Liccntr==null)
                        {
                        	Liccntr = "'"+Licencelist[i]+"'" ;                            	
                        	flag1=""+Licencelist[i];
                        }else{
                        	Liccntr = Liccntr+",'"+Licencelist[i]+"'" ;
                        	flag1 = flag1+", "+Licencelist[i]+"" ;
                        }
                 }
            	 LicQry+=Liccntr+" )";
                 LicStat=" and (a.year,a.company,a.inv_no) in (select distinct year,company,inv_no from ei_endors_lc_lic_dtls where ref_no is not null   "+LicQry+")";
           
            }
             
             
            
            
            String AClist[] = request.getParameterValues("ACLIST");
            String Str1 = "";
            String flag2="";
            if(AClist!=null && AClist.length>0)
            {
            	String cntr=null;
            	Qry+=" and AC_HOLDER in(";
            	 for(int i=0; i<AClist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+AClist[i]+"'" ;                            	
                        	flag2=""+AClist[i];
                        }else{
                        	cntr = cntr+",'"+AClist[i]+"'" ;
                        	flag2 = flag2+", "+AClist[i]+"" ;
                        } 
                 }
            	 Qry+=cntr+" )";
            }
            
             
            
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/PRE/";
          if (output.equals("PDF")) {   
             report = (JasperReport) JRLoader.loadObject(path +"epcopy_report.jasper");
          }else{
           report = (JasperReport) JRLoader.loadObject(path +"epcopy_report_excel.jasper");
          } 
             filename="EPCopyReport";
             String epst=" and 1=1 ";
             String date1 = request.getParameter("date1");
             String date2 = request.getParameter("date2");
             Collection list=null;
             if (request.getParameter("SUMCH").equals("PEND"))
             { epst=" and S.flight_date is null ";
                parameters.put("p_head","PENDING");
             } else if (request.getParameter("SUMCH").equals("RECV"))
             { epst=" and S.flight_date is not null ";
               parameters.put("p_head","RECEIVED");
             }else{
                 epst=" and 1=1 "; 
                 parameters.put("p_head","ALL");
             }
             
            parameters.put("SUBREPORT_DIR",path); 
            parameters.put("REPORT_CONNECTION", con);  
            
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yyyy"); 
             parameters.put("p_date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
             parameters.put("p_date_to", dateformat1.format(dateformat.parse(request.getParameter("date2")))); 
             parameters.put("p_query",Qry);
             parameters.put("p_epst",epst);
            parameters.put("p_origin",request.getParameter("origin_cntry"));
             parameters.put("p_selftp",request.getParameter("self_tp"));
             parameters.put("p_licquery",LicQry);
             parameters.put("p_licstat",LicStat);
             parameters.put("p_loct",request.getParameter("LOCT_CODE"));
           
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
      <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
	<form name="create_pdf_form" id="create_pdf_form" action="EPCopyReport.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      EP Copy Report
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            <tr>
                                 <td class="label-1" style="background-color: white;">Location </td>
                                 <td class="label-1" style="background-color: white;" colspan="3">
                                     <s:select  headerValue="" list="#{'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:220px;font-weight:bold" theme="simple" name="LOCT_CODE" value="%{LOCT_CODE}" /> 
                           
                                 </td> 
                                 <td class="label-1" style="background-color: white;" >Inv Type</td>
                                <td align="left" class="label-1" style="background-color: white;"colspan="3">
                                 <s:select name="self_tp" label="Invoice Type" cssStyle="font-size:10pt;width:200px;font-size:9pt;" theme="simple"  value="%{self_tp}"  list="#{'N':'Normal','S':'Trade Sample','F':'Free Sample','%':'ALL'}" /></td>
                                 
                                 
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">S/B Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:150px">S/B Date To </td>
                                <td class="label-1" style="background-color: white;" colspan="2">
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px" ></s:textfield>
                                </td>
                                <td class="label-1" style="background-color: white;">
                                   <button  id="searchheadId" class="sexybutton" onclick="search()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                </td>
                            </tr>
                             <tr> 
                                <td class="label-1" style="background-color: white;">Origin Cntry </td>
                                <td class="label-1" style="background-color: white;"  colspan="7" >
                                     <s:select  name="origin_cntry" list="%{cntryList}"   headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td> 
                             </tr>
                            <tr>
			       <td valign="top" class="label-1" style="background-color: white;text-align:left">Port</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" name="PORT_CODE" id="PORT_CODE" multiple="true" list="%{PORTLIST}" ondblclick="addPort();" cssClass="texts" cssStyle="font-size:9pt;width:100px;height: 100px">
                                   </s:select>
                                </td> 
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addPort();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removePort();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td> 
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="PORT_LIST" multiple="multiple" name="PORT_LIST" class="texts" style="width:100px;height: 100px;text-transform: uppercase" ondblclick="removePort();"> 
                                	</select>
                               </td>
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">Agent</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" list="%{AGENT_LIST}" listKey="LIST_CODE" listValue="LIST_NAME"  name="AGENT" id="AGENT" multiple="true" cssClass="texts" cssStyle="font-size:9pt;width:200px;height: 100px" ondblclick="addAgent();">
                                   </s:select>
                                </td> 
                                 
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addAgent();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeAgent();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="AGENTLIST" multiple="multiple" ondblclick="removeAgent();" name="AGENTLIST" class="texts" style="width:200px;height: 100px;text-transform: uppercase"> 
                                	</select>
                               </td> 
                            </tr>
                            <tr> 
			     <td valign="top" class="label-1" style="background-color: white;text-align:left">Licence</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" list="%{LICLIST}" name="Licence" id="Licence" multiple="true" cssClass="texts" cssStyle="font-size:9pt;width:100px;height: 100px" ondblclick="addLicence();">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addLicence();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeLicence();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="LICENCELIST" multiple="multiple" name="LICENCELIST" class="texts" style="width:100px;height: 100px;text-transform: uppercase" ondblclick="removeLicence();"> 
                                	</select> 
                               </td>
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">A/C</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                 <s:select theme="simple" name="AC" id="AC" multiple="true" list="%{AC_LIST}"  ondblclick="addAC();" cssClass="texts" cssStyle="width:200px;height: 100px">
                                 </s:select>  
                                 
                               
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" onclick="addAC();" id="addButton" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeAC();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="ACLIST" multiple="multiple" name="ACLIST" ondblclick="removeAC();" class="texts" style="width:200px;height: 100px;text-transform: uppercase"> 
                                </select>
                               </td>
                            </tr>
                            
                             <tr>
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Output&nbsp;Format
                             	</td>
                             	<td colspan="3" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="#{'PDF':'PDF','XLS':'Excel'}" value="%{'PDF'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top">Status 
                             	</td>
                                <td colspan="4" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="#{'ALL':'ALL','PEND':'PENDING','RECV':'RECEIVED'}" value="%{'PEND'}" name="SUMCH" id="SUMCH" theme="simple" ></s:radio>
                             	</td>
                             </tr> 
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="EPCopyRep"' >&nbsp;&nbsp;&nbsp;&nbsp;
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
