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
    }
    
   function sumitform()
   {if(validate()==true)
    	{
        document.create_pdf_form.action="MEISDetail.jsp";
    	document.create_pdf_form.submit();
        document.getElementById('prepage').style.visibility='';
        }return true;
   }
    function search111()
    {
    	document.create_pdf_form.action="MEISDETAIL?repFlag=EPREP";
    	document.create_pdf_form.submit();
        document.getElementById('prepage').style.visibility='';
     
    	return true;
    }
     
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
		    	insertPort();
		    	insertAgent();
		    	insertBuyer();
		    	 
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
        var tnl = document.getElementById("BUYERLIST1");          
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
            String Qry = " "; 
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
            	Qry+=" and c.loading in(";
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
            	Qry+=" and c.fwd_code in(";
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
            

             
             
            
            
            String AClist[] = request.getParameterValues("BUYERLIST");
            String Str1 = "";
            String flag2="";
            if(AClist!=null && AClist.length>0)
            {
            	String cntr=null;
            	Qry+=" and BUYER in(";
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

        
          String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/GVTINC/";
          
           report = (JasperReport) JRLoader.loadObject(path +"meisdetail.jasper");
        
             filename="MEISDETAIL";
             String epst=" and 1=1 ";
             String date1 = request.getParameter("date1");
             String date2 = request.getParameter("date2");
             Collection list=null;
               
             
            parameters.put("SUBREPORT_DIR",path); 
            parameters.put("REPORT_CONNECTION", con);  
            
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yyyy"); 
             parameters.put("p_date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
             parameters.put("p_date_to", dateformat1.format(dateformat.parse(request.getParameter("date2")))); 
             parameters.put("p_query",Qry);
             parameters.put("p_origin",request.getParameter("origin_cntry"));
             parameters.put("p_exptype",request.getParameter("exp_type"));
             parameters.put("p_selftp",request.getParameter("self_tp"));
             parameters.put("p_loct",request.getParameter("LOCT_CODE"));
             parameters.put("p_type",request.getParameter("REPLIST"));
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
	<form name="create_pdf_form" id="create_pdf_form" action="MEISDetail.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      MEIS Detail - 
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 700px; background-color: #6699CC;">
                            <tr>
                                 <td class="label-1" style="background-color: white;">Location </td>
                                 <td class="label-1" style="background-color: white;" colspan="3">
                                     <s:select  headerValue="" list="#{'100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:220px;font-weight:bold" theme="simple" name="LOCT_CODE" value="%{LOCT_CODE}" /> 
                           
                                 </td> 
                                 <td class="label-1" style="background-color: white;" >Inv Type</td>
                                <td align="left" class="label-1" style="background-color: white;"colspan="3">
                                 <s:select name="self_tp" label="Invoice Type" cssStyle="font-size:10pt;width:200px;font-size:9pt;" theme="simple"  value="%{self_tp}"  list="#{'N':'Normal','S':'Trade Sample','F':'Free Sample','%':'ALL'}" /></td>
                                 
                                 
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">L/E Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:150px">L/E Date To </td>
                                <td class="label-1" style="background-color: white;" colspan="2">
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px" ></s:textfield>
                                </td>
                                <td class="label-1" style="background-color: white;">
                                   <button  id="searchheadId" class="sexybutton" onclick="search111()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                </td>
                            </tr> 
                             <tr> 
                                <td class="label-1" style="background-color: white;">Origin Cntry </td>
                                <td class="label-1" style="background-color: white;" colspan="3"  >
                                     <s:select  name="origin_cntry" list="%{cntryList}"  value="%{'IN'}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td> 
                                 <td class="label-1" style="background-color: white;">Exp Type </td>
                                 <td class="label-1" style="background-color: white; " colspan="3"   >
                                     <s:select  name="exp_type" list="%{EXPTYPE_LIST}"   headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td> 
                             </tr>
                            <tr>
			       <td valign="top" class="label-1" style="background-color: white;text-align:left">Port</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" name="PORT_CODE" id="PORT_CODE" multiple="true" list="%{PORTLIST}" ondblclick="addPort();" cssClass="texts" cssStyle="font-size:9pt;width:200px;height: 100px">
                                   </s:select>
                                </td> 
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addPort();" style="text-decoration:none;"><div width="200px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removePort();" style="text-decoration:none;"><div width="200px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="PORT_LIST" multiple="multiple" name="PORT_LIST" class="texts" style="width:200px;height: 100px;text-transform: uppercase" ondblclick="removePort();"> 
                                	</select>
                               </td>
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">Forwarder </td>
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
			     <td valign="top" class="label-1" style="background-color: white;text-align:left">Buyer</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                     <s:select theme="simple" list="%{BUYERLIST}" listKey="LIST_CODE" listValue="LIST_NAME"  name="BUYER" id="BUYER" multiple="true" cssClass="texts" cssStyle="font-size:9pt;width:200px;height: 100px" ondblclick="addBuyer();">
                                   </s:select>
                                  </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addBuyer();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeBuyer();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="BUYERLIST1" multiple="multiple" name="BUYERLIST" class="texts" style="width:200px;height: 100px;text-transform: uppercase" ondblclick="removeBuyer();"> 
                                	</select> 
                               </td>
                               <td valign="top" class="label-1" style="background-color: white;" </td>
                               <td valign="top"class="label-1" style="background-color: white;text-align:left"  colspan="3"></td>
                               
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
                                    <s:select  name="REPLIST" list="%{REPLIST}"  listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td>
                             </tr> 
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="MEISDETAIL"' >&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="button" class="whitesubmitbutton" value="Finish" onclick='sumitform()' >
                             		<%--<input type="submit" id="submitbtn" value="Finish" class="whitesubmitbutton">--%>
                             	</td>
                             </tr>	
                        </table>
                     </td>
                 </tr>
             </table> 
 </form>
</body> 
</html> 
  