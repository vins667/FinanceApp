

<%@page import="shahi.Action.MvxExp.Reports.GVTINC.DbkOutStandingReportAction"%>  
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
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
        <link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
 
 
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
    	document.create_pdf_form.action="DbkOutStandingRprt";
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
 
     function addPrcGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PRC_GRPLIST.options.length;
        while(document.create_pdf_form.PRC_GRP.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PRC_GRP.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PRC_GRPLIST.options[i].value ==document.create_pdf_form.PRC_GRP.options[index].value)
                {
                    alert(document.create_pdf_form.PRC_GRP.options[index].text +", " + " Already Exist !");
                    return false;
                }
            }
            document.create_pdf_form.PRC_GRPLIST.options[toSelect_Length] = new Option(document.create_pdf_form.PRC_GRP.options[index].text);
            document.create_pdf_form.PRC_GRPLIST.options[toSelect_Length].value =document.create_pdf_form.PRC_GRP.options[index].value;    
            document.create_pdf_form.PRC_GRP.options[index] = null;
            toSelect_Length++;    
        } 
    }
    

    function removePrcGrp()
    {
    	var toSelect_Length = document.create_pdf_form.PRC_GRP.options.length;
        while(document.create_pdf_form.PRC_GRPLIST.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PRC_GRPLIST.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PRC_GRP.options[i].value ==document.create_pdf_form.PRC_GRPLIST.options[index].value)
                {
                    alert(document.create_pdf_form.PRC_GRPLIST.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.PRC_GRP.options[toSelect_Length] = new Option(document.create_pdf_form.PRC_GRPLIST.options[index].text);
            document.create_pdf_form.PRC_GRP.options[toSelect_Length].value =document.create_pdf_form.PRC_GRPLIST.options[index].value;    
            document.create_pdf_form.PRC_GRPLIST.options[index] = null;
            toSelect_Length++;    
        } 
    }
    
    function insertPrcGrp(){  
        var tnl = document.getElementById("PRC_GRPLIST");          
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
            String query = " ";  
            Map parameters = new HashMap();
            
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }                    
            JasperReport report = null;
            String filename="Report";
            String saleorderstr = "";
            String saleorderflag="";
               String Prclist[] = request.getParameterValues("PRC_GRPLIST");
            
           
            if(Prclist!=null && Prclist.length>0)
            {  
            	String cntr=null; 
            	query+=" and C.EXP_TYPE in(";
            	 for(int i=0; i<Prclist.length; i++)
                 { 
                        if(cntr==null)
                        {
                        	cntr = "'"+Prclist[i]+"'" ;                            	
                            saleorderflag=""+Prclist[i];
                        }else{
                        	cntr = cntr+",'"+Prclist[i]+"'" ;
                        	saleorderflag = saleorderflag+", "+Prclist[i]+"" ;
                        }
                 } 
            	 query+=cntr+" )";
            } 
              String Portlist[] = request.getParameterValues("PORT_LIST");
             String str = "";
            String flag="";
            if(Portlist!=null && Portlist.length>0)
            {
            	String cntr=null;
            	query+=" and c.loading in(";
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
             String reptype="";
             SimpleDateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy"); 
             SimpleDateFormat dateformat1= new SimpleDateFormat("dd-MMM-yyyy"); 
             String datefrom= dateformat1.format(dateformat.parse(request.getParameter("date1")));
             String dateto=dateformat1.format(dateformat.parse(request.getParameter("date2")));
             parameters.put("date_from", datefrom);
             parameters.put("date_to", dateto); 
             parameters.put("p_query",query);
             parameters.put("p_inv",request.getParameter("self_tp"));
             parameters.put("based_on",request.getParameter("BASED_ON"));
             parameters.put("amt",request.getParameter("AMT_LE"));
             parameters.put("loc",request.getParameter("LOCT_CODE"));
             parameters.put("P_LE",request.getParameter("SUMCH"));
             parameters.put("P_INC",request.getParameter("REPTYPE")); 
             if (request.getParameter("DUE_RECV").equals("DUE"))
             {  reptype="OutStanding ";}else{reptype=" Received  ";}
             parameters.put("P_REPORT",reptype); 
              
            
           	//response.getWriter().write(query);
             
             //String path = getServletContext().getRealPath("\\") + "shahiwebpages\\MvxExp\\reports\\GVTINC\\";
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/GVTINC/";
             
                  report = (JasperReport) JRLoader.loadObject(path +"DbkOutStandingReport.jasper");
             
             filename="DbkOutStandingReport";
           
             String date1 = request.getParameter("date1");
             String date2 = request.getParameter("date2");
             Collection list=null;
            
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/GVTINC")); 
            parameters.put("REPORT_CONNECTION", con);  
              list = new DbkOutStandingReportAction().generateReport(request.getParameter("LOCT_CODE"),query,datefrom,dateto,request.getParameter("self_tp"),request.getParameter("BASED_ON"),request.getParameter("SUMCH"),request.getParameter("REPTYPE"),request.getParameter("AMT_LE"),request.getParameter("DUE_RECV")); 
               
              JasperPrint     print = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(list));

          	 
          //  JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
            
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
            <img alt=""  src="../../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
	<form name="create_pdf_form" id="create_pdf_form" action="DbkOutStandingReport.jsp" method="POST">
             
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      DBK OutStanding Report
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
                                     <s:select  headerValue="" list="# {'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:220px;font-weight:bold" theme="simple" name="LOCT_CODE" value="%{LOCT_CODE}" /> 
                                 </td> 
                                
                                <td class="label-1" style="background-color: white;" >Invoice&nbsp;Type</td>
                                <td align="left" class="label-1" style="background-color: white;"colspan="7">
                                <s:select name="self_tp" label="Invoice Type" cssStyle="font-size:10pt;width:200px;font-size:9pt;" theme="simple"  value="%{self_tp}"  list="# {'N':'Normal','S':'Trade Sample','F':'Free Sample','%':'ALL'}" /></td>
                                 
                                 
                            </tr>
                            <tr> 
                                <td class="label-1" style="background-color: white;">Date From</td>
                                <td class="label-1" style="background-color: white;" colspan="3">
                                	<s:textfield id="date1" name="date1" value="%{date1}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px"></s:textfield>
                               	</td>
                                <td class="label-1" style="background-color: white;width:150px">Date To </td>
                                <td class="label-1" style="background-color: white;" colspan="2">
                                	<s:textfield id="date2" name="date2" value="%{date2}" readonly="readonly" cssClass="texts" theme="simple" cssStyle="width:200px" ></s:textfield>
                                </td>
                                <td   class="label-1" style="background-color: white;">
                                   <button  id="searchheadId" class="sexybutton" onclick="search();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                </td>
                            </tr>
                              
                            <tr>
			       <td valign="top" class="label-1" style="background-color: white;text-align:left">Port</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                   <s:select theme="simple" name="PORT_CODE" id="PORT_CODE" multiple="true" list="%{PORTLIST}" ondblclick="addPort();" cssClass="texts" cssStyle="font-size:9pt;width:100px;height: 100px">
                                   </s:select>
                                </td>
                                 <td valign="top" style="background-color: white;width:85px" >
                                    <a href="#" id="addButton" onclick="addPort();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;
                                    	<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButton" onclick="removePort();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;
                                    <img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                 </td>
                               
                                 <td valign="top" class="label-1" style="background-color: white;" colspan="0">                                   
                                    <select id="PORT_LIST" multiple="multiple" name="PORT_LIST" class="texts" style="width:100px;height: 100px;text-transform: uppercase" ondblclick="removePort();"> 
                                	</select>
                               </td>
                           
                               <td valign="top" class="label-1" style="background-color: white;text-align:left" colspan="4"></td>
                                
                            </tr> 
                            <tr> 
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top">Date Based
                             	</td>
                                <td colspan="2" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'AWB':'AWB','SB':'S/B'}" value="%{'AWB'}" name="BASED_ON" id="BASED_ON" theme="simple" ></s:radio>
                                </td> 
                                <td class="label-1" style="background-color: white;text-align: left;" valign="top">Amt :<s:textfield name="AMT_LE" id="AMT_LE" value="0" cssStyle="text-transform:uppercase;width:50pt" theme="simple" />
                             	</td>
                                <td colspan="2" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'Excess':'Excess','Less':'Less'}" value="%{'Excess'}" name="SUMCH" id="SUMCH" theme="simple" ></s:radio>
                             	</td> 
                                <td colspan="2" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'DUE':'OutStanding','RECV':'Received'}" value="'DUE'" name="DUE_RECV" id="DUE_RECV" theme="simple" ></s:radio>
                             	</td>
                            </tr>
                             <tr>  
                                  
                             	<td class="label-1" style="background-color: white;text-align: left;" valign="top">Incentive&nbsp;Type</td>
                                <td colspan="3" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'DBK':'DBK','STR':'STR'}" value="'DBK'" name="REPTYPE" id="REPTYPE" theme="simple" ></s:radio>
                             	</td>
                                <td colspan="2" class="label-1" style="background-color: white;text-align: right;" valign="top">Output&nbsp;Format</td>
                             	<td colspan="2" style="background-color: white;text-align:left;" class="label-1">
                             		<s:radio list="# {'PDF':'PDF','XLS':'Excel'}" value="%{'XLS'}" name="filetype" id="filetype" theme="simple" ></s:radio>
                             	</td>
                                 
                             </tr> 
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="DbkOutStandingRprt"' >&nbsp;&nbsp;&nbsp;&nbsp;
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
 