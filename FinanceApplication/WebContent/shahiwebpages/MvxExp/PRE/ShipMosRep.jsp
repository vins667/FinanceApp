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
    	document.create_pdf_form.action="ShipModeReport?repFlag=MOS";
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
		    	insertMOS();
		    	insertDELTERM();
		    	return true;
			
		} 
  function insertMOS(){  
        var tnl = document.getElementById("MOSLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    }
    
function addMOS()
    {
    	var toSelect_Length = document.create_pdf_form.MOSLIST.options.length;
        while(document.create_pdf_form.MOS.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.MOS.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.MOSLIST.options[i].value ==document.create_pdf_form.MOS.options[index].value)
                {
                    alert(document.create_pdf_form.MOS.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.MOSLIST.options[toSelect_Length] = new Option(document.create_pdf_form.MOS.options[index].text);
            document.create_pdf_form.MOSLIST.options[toSelect_Length].value =document.create_pdf_form.MOS.options[index].value;    
            document.create_pdf_form.MOS.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removeMOS()
    {
    	var toSelect_Length = document.create_pdf_form.MOS.options.length;
        while(document.create_pdf_form.MOSLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.MOSLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.MOS.options[i].value ==document.create_pdf_form.MOSLIST.options[index].value)
                {
                    alert(document.create_pdf_form.MOSLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.MOS.options[toSelect_Length] = new Option(document.create_pdf_form.MOSLIST.options[index].text);
            document.create_pdf_form.MOS.options[toSelect_Length].value =document.create_pdf_form.MOSLIST.options[index].value;    
            document.create_pdf_form.MOSLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }


       function insertDELTERM(){  
        var tnl = document.getElementById("DELTERMLIST");          
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;}  
    } 
    
   
function addDELTERM()
    {
    	var toSelect_Length = document.create_pdf_form.DELTERMLIST.options.length;
        while(document.create_pdf_form.DELTERM.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.DELTERM.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.DELTERMLIST.options[i].value ==document.create_pdf_form.DELTERM.options[index].value)
                {
                    alert(document.create_pdf_form.DELTERM.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.DELTERMLIST.options[toSelect_Length] = new Option(document.create_pdf_form.DELTERM.options[index].text);
            document.create_pdf_form.DELTERMLIST.options[toSelect_Length].value =document.create_pdf_form.DELTERM.options[index].value;    
            document.create_pdf_form.DELTERM.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function removeDELTERM()
    {
    	var toSelect_Length = document.create_pdf_form.DELTERM.options.length;
        while(document.create_pdf_form.DELTERMLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.DELTERMLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.DELTERM.options[i].value ==document.create_pdf_form.DELTERMLIST.options[index].value)
                {
                    alert(document.create_pdf_form.DELTERMLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.DELTERM.options[toSelect_Length] = new Option(document.create_pdf_form.DELTERMLIST.options[index].text);
            document.create_pdf_form.DELTERM.options[toSelect_Length].value =document.create_pdf_form.DELTERMLIST.options[index].value;    
            document.create_pdf_form.DELTERMLIST.options[index] = null;
            toSelect_Length++;    
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
            String Qry = " ";
            Map parameters = new HashMap();
            
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }                    
            JasperReport report = null;
            String filename="Report";
            
            String MOSlist[] = request.getParameterValues("MOSLIST");
          
            String saleorderstr = "";
            String saleorderflag="";
            if(MOSlist!=null && MOSlist.length>0)
            {
            	String cntr=null;
            	Qry+=" and A.MODE_OF_SHIP in(";
             
            	 for(int i=0; i<MOSlist.length; i++)
                 {
                        if(cntr==null)
                        {  
                        	cntr = "'"+MOSlist[i]+"'" ;                            	
                            saleorderflag=""+MOSlist[i];
                        }else{
                            
                        	cntr = cntr+",'"+MOSlist[i]+"'" ;
                              
                        	saleorderflag = saleorderflag+", "+MOSlist[i]+"" ;
                        }
                 } 
            	 Qry+=cntr+" )"; 
            }
         
            String Deltermlist[] = request.getParameterValues("DELTERMLIST");
            String str = "";
            String flag="";
            if(Deltermlist!=null && Deltermlist.length>0)
            {
            	String cntr=null;
            	Qry+=" and A.crncy_code in(";
            	 for(int i=0; i<Deltermlist.length; i++)
                 {
                        if(cntr==null)
                        {
                        	cntr = "'"+Deltermlist[i]+"'" ;                            	
                        	flag=""+Deltermlist[i];
                        }else{
                        	cntr = cntr+",'"+Deltermlist[i]+"'" ;
                        	flag = flag+", "+Deltermlist[i]+"" ;
                        }
                 }
            	 Qry+=cntr+" )";
            }
            
         
            
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/PRE/";
             report = (JasperReport) JRLoader.loadObject(path +"ShipModeRep.jasper");
             filename="ShipmentReport"; 
           
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
            parameters.put("p_loct",request.getParameter("LOCT_CODE"));
            parameters.put("p_buyer",request.getParameter("buyer"));
            parameters.put("p_pch",request.getParameter("pch"));
       
            parameters.put("p_origin",request.getParameter("origin_cntry"));
            parameters.put("p_exptype",request.getParameter("exptype"));
            parameters.put("p_merchant",request.getParameter("merchant"));
            parameters.put("p_discharge",request.getParameter("discharge"));
            parameters.put("p_desti",request.getParameter("desti_code"));
            parameters.put("p_cntry",request.getParameter("desti_cntry"));
            parameters.put("detail_yn",request.getParameter("P_CH"));
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
            <img alt=""  src="../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
	<form name="create_pdf_form" id="create_pdf_form" action="ShipMosRep.jsp" method="POST">
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   	<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr> 
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                       Shipment Report [ MOS Wise]
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 800px; background-color: #6699CC;">
                            <tr>
                                  <td class="label-1" style="background-color: white;">Location </td>
                                  <td class="label-1" style="background-color: white;"  colspan="4">
                                     <s:select  headerValue="" list="#{'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:200px;font-weight:bold; " theme="simple" name="LOCT_CODE" value="%{loct}" /> 
                           
                                 </td> 
                                 
                            </tr> 
                            <tr>  
                                <td class="label-1" style="background-color: white;">TO Date From</td>
                                <td class="label-1" style="background-color: white;" >
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;">TO Date To </td>
                                <td class="label-1" style="background-color: white;   colspan="2">
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px" ></s:textfield>
                                        &nbsp;<button  id="searchheadId" class="sexybutton" onclick="search()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                </td>           
                           
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">PCH </td>
                                     <td class="label-1" style="background-color: white;"  >
                                     <s:select  name="pch" list="%{pchList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td> 
                                 <td class="label-1" style="background-color: white;">Buyer </td>
                                 <td class="label-1" style="background-color: white;"    >
                                     <s:select  name="buyer" list="%{buyerList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                 </td> 
                                
                            </tr>
                            
                            <tr> 
                                <td class="label-1" style="background-color: white;">Exp Type </td>
                                     <td class="label-1" style="background-color: white;"  >
                                     <s:select  name="exptype" list="%{expList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td> 
                                 <td class="label-1" style="background-color: white;">Merchant </td>
                                 <td class="label-1" style="background-color: white;"    >
                                     <s:select  name="merchant" list="%{merchantList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                 </td> 
                                
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Destination </td>
                                     <td class="label-1" style="background-color: white;"  >
                                     <s:select  name="desti_code" list="%{DestiList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td> 
                                 <td class="label-1" style="background-color: white;">Discharge </td>
                                 <td class="label-1" style="background-color: white;"    >
                                     <s:select  name="discharge" list="%{DischargeList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                 </td> 
                                
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Origin Cntry </td>
                                     <td class="label-1" style="background-color: white;"  >
                                     <s:select  name="origin_cntry" list="%{cntryList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                </td> 
                                 <td class="label-1" style="background-color: white;">Destn. Cntry </td>
                                 <td class="label-1" style="background-color: white;"    >
                                     <s:select  name="desti_cntry" list="%{delvList}"  headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  cssStyle="font-size:10pt;width:200px;color:blue" theme="simple"   /> 
                                 </td> 
                                
                            </tr>
                            <tr> 
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">Currency</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                  <s:select theme="simple" name="DELTERM" id="DELTERM" multiple="true" list="%{crncyList}"  ondblclick="addDELTERM();" cssClass="texts" cssStyle="width:200px;height: 70px">
                                 </s:select>  
                                 
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addDELTERM();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeDELTERM();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="DELTERMLIST" multiple="multiple" ondblclick="removeDELTERM();" name="DELTERMLIST" class="texts" style="width:200px;height: 70px;text-transform: uppercase"> 
                                	</select>
                               </td>
                            </tr> 
                            <tr> 
                               <td valign="top" class="label-1" style="background-color: white;text-align:left">Mode of Ship</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                  <s:select theme="simple" name="MOS" id="MOS" multiple="true" list="%{mosList}"  ondblclick="addMOS();" cssClass="texts" cssStyle="width:200px;height: 100px">
                                 </s:select>  
                                 
                                </td>
                                 <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButton" onclick="addMOS();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removeMOS();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    	<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select name="MOSLIST" id="MOSLIST" multiple="multiple" ondblclick="removeMOS();"  class="texts" style="width:200px;height: 100px;text-transform: uppercase"> 
                                	</select>
                               </td>
                            </tr>

                            <tr>
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Output&nbsp;Format
                             	</td>
                             	<td  style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="#{'PDF':'PDF','XLS':'Excel'}" value="%{'PDF'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top">Detail
                             	</td>
                                 <td  style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="#{'YES':'YES','NO':'NO'}" value="%{'YES'}" name="P_CH" id="filetype" theme="simple" ></s:radio>
                             	</td>
                             </tr>  
                             <tr>
                             	<td colspan="5" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="ShipModeReport"' >&nbsp;&nbsp;&nbsp;&nbsp;
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
 