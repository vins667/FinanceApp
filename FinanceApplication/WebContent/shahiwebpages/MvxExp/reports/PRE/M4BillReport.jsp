

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
    }
   
    function search()
    {
    	if(validate()==true)
    	{
    	document.create_pdf_form.action="M4BillReportA";
    	document.create_pdf_form.submit();
        document.getElementById('prepage').style.visibility='';
    	}
    	return true;
    }
     
    function pchh()
    {
    	document.create_pdf_form.action="pchM4BillReportA";
    	document.create_pdf_form.submit();
        document.getElementById('prepage').style.visibility='';
    	
    	return true;
    }
     
		function validate() 
		{
			DATE_FROM=document.getElementById("date1");
		        DATE_TO=document.getElementById("date2");
		    	if (document.getElementById("DEPT").value=='')
                        {
                          alert("Select Department");
		    		return false;  
                        }
		    	if(DATE_FROM.value==''){
		    		alert("'From' Date cannot be empty");
		    		return false;
		    	}
		    	
		    	if(DATE_TO.value==''){
		    		alert("'To' Date cannot be empty");
		    		return false;
		    	}
                        if (document.getElementById("BILL_TYPE").value=='')
                        {
                          alert("Select Bill Type");
		    		return false;  
                        }
		    	//insertPort();
                        insertPch();
                        insertPro();
                      
		    	 
		    	return true;
			
		} 
                
                function additemTyp(){
                var PCHTypInput=document.getElementById("PCH"); 		
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
 
     function addPchGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PCH_GRPLIST.options.length;
        while(document.create_pdf_form.PCH.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PCH.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            { 
                if(document.create_pdf_form.PCH_GRPLIST.options[i].value ==document.create_pdf_form.PCH.options[index].value)
                {
                    alert(document.create_pdf_form.PCH.options[index].text +", " + " Already Exist !");
                    return false;
                }
            }
            document.create_pdf_form.PCH_GRPLIST.options[toSelect_Length] = new Option(document.create_pdf_form.PCH.options[index].text);
            document.create_pdf_form.PCH_GRPLIST.options[toSelect_Length].value =document.create_pdf_form.PCH.options[index].value;    
            document.create_pdf_form.PCH.options[index] = null;
            toSelect_Length++;    
        } 
    }
    

    function removePchGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PCH.options.length;
        while(document.create_pdf_form.PCH_GRPLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PCH_GRPLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PCH.options[i].value ==document.create_pdf_form.PCH_GRPLIST.options[index].value)
                {
                    alert(document.create_pdf_form.PCH_GRPLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PCH.options[toSelect_Length] = new Option(document.create_pdf_form.PCH_GRPLIST.options[index].text);
            document.create_pdf_form.PCH.options[toSelect_Length].value =document.create_pdf_form.PCH_GRPLIST.options[index].value;    
            document.create_pdf_form.PCH_GRPLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertPch(){  
        var tn2 = document.getElementById("PCH_GRPLIST");          
        for(i=0;i<tn2.length;i++){  
            tn2[i].selected = true;}  
    }
    
    
    
     function addProGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PRO_GRPLIST.options.length;
        while(document.create_pdf_form.PRO.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PRO.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PRO_GRPLIST.options[i].value ==document.create_pdf_form.PRO.options[index].value)
                {
                    alert(document.create_pdf_form.PRO.options[index].text +", " + " Already Exist !");
                    return false;
                }
            }
            document.create_pdf_form.PRO_GRPLIST.options[toSelect_Length] = new Option(document.create_pdf_form.PRO.options[index].text);
            document.create_pdf_form.PRO_GRPLIST.options[toSelect_Length].value =document.create_pdf_form.PRO.options[index].value;    
            document.create_pdf_form.PRO.options[index] = null;
            toSelect_Length++;    
        } 
    }
    

    function removeProGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PRO.options.length;
        while(document.create_pdf_form.PRO_GRPLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PRO_GRPLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PRO.options[i].value ==document.create_pdf_form.PRO_GRPLIST.options[index].value)
                {
                    alert(document.create_pdf_form.PRO_GRPLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PRO.options[toSelect_Length] = new Option(document.create_pdf_form.PRO_GRPLIST.options[index].text);
            document.create_pdf_form.PRO.options[toSelect_Length].value =document.create_pdf_form.PRO_GRPLIST.options[index].value;    
            document.create_pdf_form.PRO_GRPLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertPro(){  
        var tn2 = document.getElementById("PRO_GRPLIST");          
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
            
            
            parameters.put("dept",request.getParameter("DEPT"));
            parameters.put("loc",request.getParameter("BOS_LOCT"));
            //System.out.println(request.getParameter("BOS_LOCT"));
            
            String Pchlist[] = request.getParameterValues("PCH_GRPLIST");
            if(Pchlist!=null && Pchlist.length>0)
            {
               
            	String cntr=null;
            	query+=" and d.pch in(";
            	 for(int i=0; i<Pchlist.length; i++)
                 {
                        if(cntr==null)
                        {
                            cntr = "'"+Pchlist[i]+"'" ;                            	
                            saleorderflag=""+Pchlist[i];
                        }else{
                        	cntr = cntr+",'"+Pchlist[i]+"'" ;
                        	saleorderflag = saleorderflag+", "+Pchlist[i]+"" ;
                        }
                 }
            	 query+=cntr+" )";
            }
            
            
            String BILLTYPE = request.getParameter("BILL_TYPE");
            if(BILLTYPE!=null && BILLTYPE.length()>0){
              query+=" and a.sl_no like '"+BILLTYPE+"'";
            }
            
            String BILLSUBTYPE = request.getParameter("BILL_SUB_TYPE");
            if(BILLSUBTYPE!=null && BILLSUBTYPE.length()>0){
              query+=" and b.sl_no like '"+BILLSUBTYPE+"'";
                
            }
            
            String Prolist[] = request.getParameterValues("PRO_GRPLIST");
            if(Prolist!=null && Prolist.length>0)
            {
            	String cntr=null;
            	query+=" and b1.sl_no in(";
            	 for(int i=0; i<Prolist.length; i++)
                 {
                        if(cntr==null)
                        {
                            cntr = "'"+Prolist[i]+"'" ;                            	
                            saleorderflag=""+Prolist[i];
                        }else{
                        	cntr = cntr+",'"+Prolist[i]+"'" ;
                        	saleorderflag = saleorderflag+", "+Prolist[i]+"" ;
                        }
                 }
            	 query+=cntr+" )";
            }
            
            
            
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yy"); 
             parameters.put("date_from", dateformat1.format(dateformat.parse(request.getParameter("date1"))));
             parameters.put("date_to", dateformat1.format(dateformat.parse(request.getParameter("date2")))); 
             parameters.put("p_query",query);
         
             
             
             
             
             //String RPORTORDR = request.getParameter("RPORT_ORDER");
              
            
              
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/PRE/M4BillReport.jasper"));
              
             filename="M4BillReport";
           
             String date1 = request.getParameter("date1");
             String date2 = request.getParameter("date2");
             Collection list=null;
            
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/PRE")); 
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
	<form name="create_pdf_form" id="create_pdf_form" action="M4BillReport.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      M4Bill Report
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white">BOS Loct</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="3">
                                <s:select name="BOS_LOCT" id="BOS_LOCT" value="%{BOS_LOCT}" list="%{LOC_LIST}" cssStyle="text-transform:uppercase;width:150pt;font-weight:bold" theme="simple" />
                             	</td>
                                
                                <td valign="top" class="label-1" style="background-color: white">Dept</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="7">
                                    <s:select name="DEPT" id="DEPT" listKey="EXP_CODE" listValue="EXP" list="%{DEPT_LIST}" cssStyle="text-transform:uppercase;width:150pt;" theme="simple" />
                             	</td>
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:150px">Date To </td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px" ></s:textfield>
                                </td>
                            </tr>
                            
                              
                            <tr>
                               
                                <td valign="top" class="label-1" style="background-color: white;text-align:left" colspan="">PCH</td>
                               <td valign="top"class="label-1" style="background-color: white;text-align:left" colspan="3">
                                   <s:select theme="simple" name="PCH" id="PCH" multiple="true" list="%{PCH_LIST}" ondblclick="addPchGrp();" cssClass="texts" cssStyle="font-size:9pt;width:300px;height: 100px">
                                   </s:select>
                               </td>
                               <td valign="top" style="background-color: white;width:85px" colspan="">
                                    <a href="#" id="addButton" onclick="addPchGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="../../image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removePchGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                                <td valign="top" class="label-1" style="background-color: white;" colspan="3">                                   
                                    <select id="PCH_GRPLIST" multiple="multiple" name="PCH_GRPLIST" class="texts" style="width:300px;height: 100px;text-transform: uppercase" ondblclick="removePchGrp();"> 
                                    </select>
                                </td> 
                               
                            </tr> 
                             <tr>
                                  
                                <td class="label-1" style="background-color: white;">Bill Type </td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="3">
                                <s:select name="BILL_TYPE" id="BILL_TYPE" headerKey="" headerValue="Select" listKey="SLNO" listValue="BL_CODE" list="%{B_TYPE_LIST}"  cssStyle="text-transform:uppercase;width:150pt" theme="simple" onchange="pchh();" />
                             	</td>
                                
                                <td class="label-1" style="background-color: white;" >Bill&nbsp;Sub&nbsp;Type</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top" colspan="3">
                                    <s:select name="BILL_SUB_TYPE" id="BILL_SUB_TYPE" headerKey="%" headerValue="select" listKey="SLNO" listValue="SUBTYPE" list="%{B_SUB_TYPE_LIST}" cssStyle="font-size:9pt;text-transform:uppercase;width:150pt" theme="simple" />
                             	</td>
                            </tr>
                            
                            <tr>
                                 <td valign="top" class="label-1" style="background-color: white;text-align:left">Product</td>
                                 <td valign="top"class="label-1" style="background-color: white;text-align:left" colspan="3">
                                    <s:select theme="simple" name="PRO" id="PRO" multiple="true" listKey="SLNO" listValue="PRODUCT" list="%{PRO_LIST}" ondblclick="addProGrp();" cssClass="texts" cssStyle="font-size:9pt;width:300px;height: 100px">
                                   </s:select>
                                 </td>
                               
                               <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addProGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeProGrp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                                <td valign="top" class="label-1" style="background-color: white;" colspan="3">                                   
                                    <select id="PRO_GRPLIST" multiple="multiple" name="PRO_GRPLIST" class="texts" style="width:300px;height: 100px;text-transform: uppercase" ondblclick="removeProGrp();"> 
                                    </select>
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
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="M4BillReportA"' >&nbsp;&nbsp;&nbsp;&nbsp;
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