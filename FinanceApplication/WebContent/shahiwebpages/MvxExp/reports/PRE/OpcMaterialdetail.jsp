<%-- 
    Document   : OpcMaterialdetail
    Created on : Jun 20, 2017, 12:30:16 PM
    Author     : Guddu Kumar
--%>



<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
    };
    
    function validate(){  
        var tnl = document.getElementById("PCHList"); 
        for(i=0;i<tnl.length;i++){  
            tnl[i].selected = true;
        } 
        
        return true;
    }
    
        function onSubmit()
            {
                if(validate()){
                    alert("guddu");
                    document.getElementById('submitbtn').disabled=true;
                    document.getElementById('submitbtn').style.color="gray";
                    document.getElementById('submitbtn').style.bgcolor="gray";                     
                    document.create_pdf_form.action="OpcMaterialdetail.jsp"; 
                    document.create_pdf_form.submit();
                }
            }
            
            
            
   function additemTyp()
    {
    	var toSelect_Length = document.create_pdf_form.PCHList.options.length;
        while(document.create_pdf_form.FAB.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.FAB.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.PCHList.options[i].value ==document.create_pdf_form.FAB.options[index].value)
                {
                    alert(document.create_pdf_form.FAB.options[index].text +", " + " Already Exist !");
                    return false;
                }
            }
            document.create_pdf_form.PCHList.options[toSelect_Length] = new Option(document.create_pdf_form.FAB.options[index].text);
            document.create_pdf_form.PCHList.options[toSelect_Length].value =document.create_pdf_form.FAB.options[index].value;    
            document.create_pdf_form.FAB.options[index] = null;
            toSelect_Length++;    
        } 
    }
    

    function removeitemTyp()
    {
    	var toSelect_Length = document.create_pdf_form.FAB.options.length;
        while(document.create_pdf_form.PCHList.selectedIndex > -1 )
        {
            var index = document.create_pdf_form.PCHList.selectedIndex;
            for(i=0;i<toSelect_Length; i++)
            {
                if(document.create_pdf_form.FAB.options[i].value ==document.create_pdf_form.PCHList.options[index].value)
                {
                    alert(document.create_pdf_form.PCHList.options[index].text +", " + " Already Exist !")
                    return false;
                }
            }
            document.create_pdf_form.FAB.options[toSelect_Length] = new Option(document.create_pdf_form.PCHList.options[index].text);
            document.create_pdf_form.FAB.options[toSelect_Length].value =document.create_pdf_form.PCHList.options[index].value;    
            document.create_pdf_form.PCHList.options[index] = null;
            toSelect_Length++;    
        } 
    }
            
     //var index = document.create_pdf_form.PCH.selectedIndex;
            
    
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
                
                String STYLENO=request.getParameter("STYLENO");
                
                String FABlist[]=request.getParameterValues("PCHList");
                
                
		byte[] bytes = null;
                String query = " ";  
                Map parameters = new HashMap();
                
                 if(STYLENO!=null && STYLENO.length()>0)
                    {
                                query+= " and a.style_code LIKE '"+STYLENO+"'";
                    }
                
                 String scode2="";
                 String scode1 = "";
                        if(FABlist!=null && FABlist.length>0)
                            {
                                query+= " and mmprgp in (";
                                for(int i=0; i<FABlist.length; i++)
                                {
                                   scode1+="'"+FABlist[i]+"',";
                                   scode2+=FABlist[i]+",";
                                }
                                scode1=scode1.substring(0,(scode1.length()-1));
                                scode2=scode2.substring(0,(scode2.length()-1));
                                scode1+=") ";
                                query+= scode1;
                                parameters.put("fabrictrim", scode2);
                            }
                
            if (request.getParameter("filetype").equals("XLS")) {
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
            }  
            
            JasperReport report = null;
            String filename="Report";
           
            
             parameters.put("QRY_GK",query);
            // parameters.put("p_style",STYLENO);
            
             
             
             report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/PRE/opc_mat_report.jasper"));
             
             filename="opc_mat_report";
             
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
            <img alt=""  src="../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
    <form name="create_pdf_form" id="create_pdf_form" onsubmit="return validate();" method="POST">
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
   			<table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                      Opc Material Report
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            
                             <tr> 
                                <td valign="top" class="label-1" style="background-color: white;">Style No.</td>
                                <td colspan="3"  align="center" class="label-1" style="background-color: white;">
                                    <input type="textbox" id="STYLENO" name="STYLENO" class="texts" onkeypress="return tabE(this,event);" style="width:150px;text-transform:uppercase"/>
                                </td>
                            </tr>
                            <tr> 
                                <td valign="top" class="label-1" style="background-color: white;">Fabric/Trim</td>
                                <td colspan="0"  align="center" class="label-1" style="background-color: white;">
                                    <s:select id="FAB" multiple="true" name="FAB" list="# {'FAB':'Fabric','FFC':'Fabric FOC','TRM':'Trim','TFC':'Trim FOC'}" theme="simple" class="texts" style="width:150px" onkeypress="return tabEStyleTp(this,event);" ondblclick="additemTyp();"> 
                                    </s:select>
                                </td>
                                <td valign="top" style="background-color: white;width:85px">
                                    <a href="#" id="addButtonitemTyp" onclick="additemTyp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;<img src="../../images/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButtonitemTyp" onclick="removeitemTyp();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;<img src="../../images/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                                </td>
                                <td valign="top" class="label-1" style="background-color: white;"><select id="PCHList" multiple="multiple" name="PCHList" class="texts" style="width:150px" ondblclick="removeitemTyp();"> </select> </td>

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
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="OpcMaterialdetail.jsp"' >&nbsp;&nbsp;&nbsp;&nbsp;
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
