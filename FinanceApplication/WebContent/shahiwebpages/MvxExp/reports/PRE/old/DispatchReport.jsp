


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
                <script src="../../js/datetimepicker_css.js"></script>  
       
        <link rel="stylesheet" type="text/css" media="all" href="../../style/jsDatePick_ltr.min.css" />
        <link href="../../style/style.css" rel="stylesheet" type="text/css"/>
        <link href="../../../css/main.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="../../script/jsDatePick.min.1.3.js"></script>
			        
   <script type="text/javascript" language="javascript" >     
//        window.onload = function()
//	  {
//        new JsDatePick({ 
//            useMode:2,
//            target:"date1",
//            dateFormat:"%d/%m/%Y",
//            imgPath:"/img/"
//        });
//        new JsDatePick({
//            useMode:2,
//            target:"date2",
//            dateFormat:"%d/%m/%Y",
//            imgPath:"/img/"
//        });
//    }
    
    function search()
    {
    	if(validate()==true)
    	{
    	document.create_pdf_form.action="DispatchReportA";
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
                        insertPch();
                      
		    	 
		    	return true;
			
		} 
                
                function additemTyp(){
                var PCHTypInput=document.getElementById("CHA"); 		
                if(PCHTypInput.value.length==0)
                {
                    alert("You have not entered any value.");
                    return;
                }			
                var PCHTypList=document.getElementById("PCHList");
                addValue(PCHTypInput,PCHTypList);
            }
            function removeitemTyp(){
                var PCHList1=document.getElementById("PCHList");
                removeValue(PCHList1);
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
 
     function addChaGrp()
    {
    	var toSelect_Length = document.create_pdf_form.CHA_GRPLIST.options.length;
        while(document.create_pdf_form.CHA.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.CHA.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.CHA_GRPLIST.options[i].value ==document.create_pdf_form.CHA.options[index].value)
                {
                    alert(document.create_pdf_form.CHA.options[index].text +", " + " Already Exist !");
                    return false;
                }
            }
            document.create_pdf_form.CHA_GRPLIST.options[toSelect_Length] = new Option(document.create_pdf_form.CHA.options[index].text);
            document.create_pdf_form.CHA_GRPLIST.options[toSelect_Length].value =document.create_pdf_form.CHA.options[index].value;    
            document.create_pdf_form.CHA.options[index] = null;
            toSelect_Length++;    
        } 
    }
    

    function removeChaGrp()
    {
    	var toSelect_Length = document.create_pdf_form.CHA.options.length;
        while(document.create_pdf_form.CHA_GRPLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.CHA_GRPLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.CHA.options[i].value ==document.create_pdf_form.CHA_GRPLIST.options[index].value)
                {
                    alert(document.create_pdf_form.CHA_GRPLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.CHA.options[toSelect_Length] = new Option(document.create_pdf_form.CHA_GRPLIST.options[index].text);
            document.create_pdf_form.CHA.options[toSelect_Length].value =document.create_pdf_form.CHA_GRPLIST.options[index].value;    
            document.create_pdf_form.CHA_GRPLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertPch(){  
        var tn2 = document.getElementById("CHA_GRPLIST");          
        for(i=0;i<tn2.length;i++){  
            tn2[i].selected = true;}  
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
            
           
           
            String Portlist[] = request.getParameterValues("PORT_LIST");
            String str = "";
            String flag="";
            if(Portlist!=null && Portlist.length>0)
            {
            	String cntr=null;
            	query+=" and nvl(a.port,'NA') in(";
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
            	 query+=cntr+" )";
            }
            
            
            String Chalist[] = request.getParameterValues("CHA_GRPLIST");
            if(Chalist!=null && Chalist.length>0)
            {
               
            	String cntr=null;
            	query+=" and trim(a.cha) in(";
            	 for(int i=0; i<Chalist.length; i++)
                 {
                        if(cntr==null)
                        {
                            cntr = "'"+Chalist[i].trim()+"'" ;                            	
                            saleorderflag=""+Chalist[i];
                        }else{
                        	cntr = cntr+",'"+Chalist[i].trim()+"'" ;
                        	saleorderflag = saleorderflag+", "+Chalist[i]+"" ;
                        }
                 }
            	 query+=cntr+" )";
            }
            
            String vehicleno = request.getParameter("vehicle_no");
            if(vehicleno!=null && vehicleno.length()>0){
              query+=" and nvl(a.vehicle_no,'NA') like '"+vehicleno+"'";
                
            }
           
            String byr = request.getParameter("BUYER");
            if(byr!=null && byr.length()>0){
              query+=" and nvl(a.buyer,'NA') like '"+byr+"'";
                
            }
            
            String unit = request.getParameter("UNIT");
            if(unit!=null && unit.length()>0){
              query+=" and nvl(a.unit,'NA') like '"+unit+"'";
                
            } 
            
           String d1=request.getParameter("date1");
           String d2=request.getParameter("date2");
           String dt1= d1.substring(0, 10);
           String dt2= d2.substring(0, 10);
           
             
             SimpleDateFormat dateformat= new SimpleDateFormat("dd-MM-yyyy hh:mm"); 
            // SimpleDateFormat dateformat1= new SimpleDateFormat("dd/MMM/yyyy hh:mm"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yyyy");
            // parameters.put("date_from", dateformat1.format(dateformat.parse(d1)));
            // parameters.put("date_to", dateformat1.format(dateformat.parse(d2))); 
               
              parameters.put("date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
              parameters.put("date_to", dateformat1.format(dateformat.parse(request.getParameter("date2")))); 
             
         System.out.println("datefrom "+dateformat1.format(dateformat.parse(request.getParameter("date1"))) ) ; 
         System.out.println("datefrom1 "+request.getParameter("date1"));
          
             parameters.put("date_from1", request.getParameter("date1"));
             parameters.put("date_to1", request.getParameter("date2")); 
             
             parameters.put("p_query",query);
           
             
             parameters.put("loc",request.getParameter("BOS_LOCT"));
             String RPORTORDR = request.getParameter("RPORT_ORDER");
              
            
              if(RPORTORDR.equals("D") && RPORTORDR!=null){
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/PRE/DispatchReport.jasper"));
              }
              else if(RPORTORDR.equals("E") && RPORTORDR!=null){
                 report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/PRE/ExcelBosHeaderReport.jasper")); 
              }
              else{
                 report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/PRE/ExcelFileInvoiceDetail.jasper")); 
              }
             filename="Report";
           
             String date1 = request.getParameter("date1");
             String date2 = request.getParameter("date2");
             Collection list=null;
            
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/PRE/")); 
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
	<form name="create_pdf_form" id="create_pdf_form"  action="DispatchReport.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      Dispatch Report
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white">BOS Loct</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="7">
                                 <s:if test='%{date1==null}'>
                                       <s:select name="BOS_LOCT" id="BOS_LOCT" list="%{LOC_LIST}" value="%{LOCT}" cssStyle="text-transform:uppercase;width:150pt;font-weight:bold" theme="simple" />
         
                               	  </s:if>
                                 <s:else>
                                    <s:textfield name="BOS_LOCT" readonly="true" theme="simple" value="%{BOS_LOCT}" cssStyle="text-transform:uppercase;width:80pt;font-size:9pt;" />
                                 </s:else>
                                </td>
                           
                                
                            
                            
                            
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                    <input type="Text" readonly="readonly" cssClass="texts" theme="simple" name="date1"  value="<s:property value="%{date1}"/>" id="date1"   maxlength="25" size="25" style="width:130px" tabindex="18"/>
                                    <img src="../../../image/cal.gif" onclick="javascript:NewCssCal('date1','ddMMyyyy','dropdown',true,'24')" style="cursor:pointer" tabindex="-1"/>
                               </td> 
                                <td class="label-1" style="background-color: white;width:150px">Date To </td>
                                
                                                         
                                 <td class="label-1" style="background-color: white;" colspan="3">
                                      <input type="Text" name="date2"   id="date2"  value="<s:property value="%{date2}"/>"  maxlength="25" size="25" style="width:130px"  tabindex="18" onfocus="search();"/>
                                      <img src="../../../image/cal.gif" onclick="javascript:NewCssCal('date2','ddMMyyyy','dropdown',true,'24')" style="cursor:pointer"/>

                                 </td> 
                            </tr>
                             
                              
                            <tr>
			       <td valign="top" class="label-1" style="background-color: white;text-align:left">Port</td>
                               <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" name="PORT_CODE" id="PORT_CODE" multiple="true" list="%{PORTLIST}" ondblclick="addPort();" cssClass="texts" cssStyle="font-size:9pt;width:200px;height: 200px">
                                   </s:select>
                               </td>
                                 <td valign="top" style="background-color: white;width:85px" >
                                    <a href="#" id="addButton" onclick="addPort();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removePort();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    <img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                 </td>
                                 <td valign="top" class="label-1" style="background-color: white;" colspan="0">                                   
                                    <select id="PORT_LIST" multiple="multiple" name="PORT_LIST" class="texts" style="width:200px;height: 200px;text-transform: uppercase" ondblclick="removePort();"> 
                                	</select>
                               </td>
                               
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">CHA</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                    <s:select theme="simple" name="CHA" id="CHA" multiple="true" listKey="CODE" listValue="NAME" list="%{CHA_LIST}" ondblclick="addChaGrp();" cssClass="texts" cssStyle="font-size:9pt;width:200px;height: 200px">
                                   </s:select>
                               </td>
                                <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addChaGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeChaGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                                 <td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="CHA_GRPLIST" multiple="multiple" name="CHA_GRPLIST" class="texts" style="width:200px;height: 200px;text-transform: uppercase" ondblclick="removeChaGrp();"> 
                                    </select>
                               </td> 

                            </tr> 
                             <tr>
                                  
                                <td class="label-1" style="background-color: white;">Vehicle No</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="3">
                                     <s:select name="vehicle_no" id="vehicle_no" list="%{VCH_LIST}" value="%{vehicle_no}"  headerValue="" headerKey="%" cssStyle="text-transform:uppercase;width:150pt;font-weight:bold" theme="simple" />
                         	</td>
                                
                                 <td class="label-1" style="background-color: white;" >Unit</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="7">
                                    <s:select name="UNIT" id="UNIT" list="%{UNIT_LIST}" value="%{UNIT}"  headerValue="" headerKey="%" cssStyle="text-transform:uppercase;width:150pt;font-weight:bold" theme="simple" />
                         	</td>
                             </tr>
                            <tr>
                                  
                                <td class="label-1" style="background-color: white;">Buyer </td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="10">
                               
                                    <s:select name="BUYER" id="BUYER" list="%{BUYER_LIST}" value="%{BUYER}"  headerValue="" headerKey="%" cssStyle="text-transform:uppercase;width:150pt;font-weight:bold" theme="simple" />
            
                               	</td>
                                
                            </tr>
                            <tr>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top">Report&nbsp;Type
                             	</td>
                                <td colspan="10" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio name="RPORT_ORDER" id="RPORT_ORDER" list="# {'D':'Dispatched','E':'Excel BOS Header','B':'BOS Detail'}" value="%{'D'}"  theme="simple" ></s:radio>
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
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="DispatchReportA"' >&nbsp;&nbsp;&nbsp;&nbsp;
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