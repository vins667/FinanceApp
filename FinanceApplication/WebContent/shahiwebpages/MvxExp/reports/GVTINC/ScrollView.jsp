

<%@page import="shahi.Action.MvxExp.Reports.PRE.PreInvPrintParameterAction"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="shahi.Action.MvxExp.Reports.PRE.PreInvPrintPDFAction"%>
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
      <link href="<s:url value="../../css/main.css"/>" rel="stylesheet" type="text/css"/>
      <link rel="stylesheet" href="../../css/sexybuttons.css"/>
      <script language="javascript" type="text/javascript" src = "../../script/function.js" ></script>
      <script type="text/javascript" src="../../script/jquery.1.4.2.js"></script>
      <script type="text/javascript" src="../../script/jquery_blockUI.js"></script>
      <script type="text/javascript" src="../../script/jquery_cookie.js"></script>
      <script type="text/javascript" src="../../script/hidediv.js"></script>     
       
      <link href="style/style.css" rel="stylesheet" type="text/css"/>
      <link href="style/main.css" rel="stylesheet" type="text/css"/>
      <script type="text/javascript" language="javascript" >     
   
    function addInvoice(){
                var InvoiceInput=document.getElementById("INV_NO");
		
                if(InvoiceInput.value.length==0)
                {
                    alert("You have not entered any value");
                    return;
                }		
                var InvList=document.getElementById("INV_LIST");
                addValue(InvoiceInput,InvList);
            }
            function tabEInvoice(obj, e) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for ( var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other
                        if (obj == ele[i]) {
                            addInvoice();
                            break
                        }
                    }
                    return false;
                }
            }            
            function removeInvoice(){
                var InvList=document.getElementById("INV_LIST");
                removeValue(InvList);
            }  
   
   function validate()
   {
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
    <% 
       if(request.getMethod()=="POST"){
              Connection con = null;
              con = new connection().getConnection();
       try{
            byte[] bytes = null;  
            Map parameters = new HashMap();
            
          
            String file_name=request.getParameter("INV_NO");
            String excs_inv_no[] = request.getParameterValues("INV_LIST");
            String pInv = "";
            String str1="";
            if (excs_inv_no != null && excs_inv_no.length > 0) 
            {
               String faciQry = " ";
               for (int i = 0; i < excs_inv_no.length; i++) 
               {
                    faciQry += "'" + excs_inv_no[i] + "',";
               }
                   faciQry = faciQry.substring(0, (faciQry.length() - 1));
                   pInv = faciQry;
                    parameters.put(" trim(a.scroll_no)", faciQry.replaceAll("'", ""));
                   str1 += " and trim(a.scroll_no) in (" + pInv + ")";
            }
        
             JasperReport report = null; 
             
            report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/MvxExp/reports/GVTINC/ScrollView.jasper"));
             
           String filename="Scroll";
            parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/MvxExp/reports/GVTINC")); 
            parameters.put("REPORT_CONNECTION", con); 
            parameters.put("p_scroll", str1);
           
            
          	 
            JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
            response.reset();
            ServletOutputStream out1 = response.getOutputStream();
            //response.reset();
            response.addCookie(new Cookie("fileDownloadToken", request.getParameter("download_token_value_id")));
        
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
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			response.getWriter().write(e.getMessage());
		}
}
%> 
<body> 
      <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
    <form name="create_pdf_form" id="create_pdf_form" action="ScrollView.jsp" method="POST">
      <input type="hidden" name="pag" id="pag" value="P"/>
      <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
            <table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                     Scroll Report
                    </td>
                </tr>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="10" style="width: 650px; background-color: #6699CC;">
                            <tr>
			       <td valign="top" class="label-1" style="background-color: white;text-align:left">Scroll No</td>
                                <td valign="top"class="label-1" style="background-color: white;text-align:left">
                                    <input type="text" name="INV_NO" id="INV_NO" onkeypress="return tabEInvoice(this,event)"/>
                                </td> 
                                 <td valign="top" style="background-color: white;width:85px">
                                      <a href="#" id="addgarmentItemButton" onclick="addInvoice();" 
                                         style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">Insert&nbsp;<img src="../../images/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                      <a href="#"  id="removeButtongarmentItem" onclick="removeInvoice();" style="text-decoration:none;"><div width="80px" height="20px" class="whitesubmiticon">&nbsp;<img src="../../images/arrow-left.png" width="14px" height="10px" style="border-width:0px"/>&nbsp;Remove</div></a>
                      
                                 </td>
                               	<td valign="top" class="label-1" style="background-color: white;">                                   
                                    <select id="INV_LIST" multiple="multiple" name="INV_LIST" class="texts" 
                                            style="width:160px;height: 160px;text-transform: uppercase" 
                                            ondblclick="removeInvoice();"> 
                                    </select>
                               </td>
                            </tr>
                             
                            
                             <tr>
                             	<td colspan="8" class="label-1" style="background-color: white;text-align: center;">
                             		<input type="button" class="whitesubmitbutton" value="Cancel" onclick="window.open('', '_self', '');window.close();">&nbsp;&nbsp;&nbsp;&nbsp;
                             		<input type="button" class="whitesubmitbutton" value="Reset" onclick='javascript:window.location.href="ScrollView.jsp"' >&nbsp;&nbsp;&nbsp;&nbsp;
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
 