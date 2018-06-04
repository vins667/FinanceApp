
<%@page contentType="text/html;charset=windows-1252"%>
<%@page language="java"%>
<%@page import="com.ibm.as400.access.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*" %>
<%@page import="java.lang.*"%>
<%@page import="java.lang.Object"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.HashMap"%> 
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="javax.servlet.*" %>
<%@page import="javax.naming.InitialContext.*"%>
<%@page import="javax.naming.*"%>
<%@page import="javax.activation.DataHandler"%>
<%@page import="javax.activation.DataSource"%>
<%@page import="javax.activation.FileDataSource"%>
<%@page import="net.sf.jasperreports.engine.fill.*"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="net.sf.jasperreports.engine.design.JasperDesign"%>
<%@page import="net.sf.jasperreports.engine.xml.JRXmlLoader"%>
<%@page import="net.sf.jasperreports.engine.export.*"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperPrintManager"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="net.sf.jasperreports.engine.export.JExcelApiExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRCsvExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRRtfExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.oasis.JROdtExporter"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="org.apache.commons.collections.*"%>
<%@page import="org.apache.commons.logging.*"%>
<%@page import="org.apache.commons.beanutils.*"%>
<%@page import="net.sf.jasperreports.charts.*"%>
 

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Shahi Exports Pvt Ltd.</title>
    <script src="../js/datetimepicker_css.js"></script>
    <style>
    
    td{
     font-size:12pt;
    font-name:Times New Roman;
    }
  .button{
  width:80pt;
  }
    </style>
    <script language="javascript">

       function dateCompare() 
            { 
                var fromdt=document.production.date_from;
                var todt=document.production.date_to;  
                var dt1 = new Date(fromdt.value.replace(/[-]/g," "));
                var dt2 = new Date(todt.value.replace(/[-]/g," "));
                if (dt1>dt2)
                {alert("Date From "+fromdt.value+" is greater then Date To "+todt.value);    
                    fromdt.value=todt.value;
                    fromdt.focus();
                    return false;    
                }      
                return true;    
            }


 function submitform()
 { 
  {document.hrs.action="TOPlanReport.jsp"
  document.hrs.target="_blank";
   document.hrs.submit();
  }
 }
 

  </script>
    </head>
<%
    
        Connection conn = null;
        ResourceBundle aResourcBundle = null;
        aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
        String URL = aResourcBundle.getString("URL");
        String UserNameA = aResourcBundle.getString("UserName");
        String PasswordA = aResourcBundle.getString("Password");
        String IP = aResourcBundle.getString("IP");
        String driverA = aResourcBundle.getString("driver");
        Class.forName(driverA);
        conn = DriverManager.getConnection(URL, UserNameA, PasswordA);

     
        Date todate = new Date();
        
        
        SimpleDateFormat forat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        SimpleDateFormat forat1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat forat2 = new SimpleDateFormat("dd-MMM-yyyy");
    
        String fdt = request.getParameter("date_from");
        String tdt = request.getParameter("date_to");

        String output = request.getParameter("output");
        if (request.getMethod() == "POST") {
            String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/PRE/";

            Map parameters = new HashMap();
          
           // parameters.put("date_from", forat2.format(forat1.parse(fdt)));
           // parameters.put("date_to", forat2.format(forat1.parse(tdt)));

             parameters.put("SUBREPORT_DIR",path);
           // parameters.put("SUBREPORT_DIR",request.getRealPath("shahiwebpages/MvxExp/GVTINC/"));

              parameters.put("REPORT_CONNECTION", conn);  
              
        
              JasperReport report = (JasperReport) JRLoader.loadObject(path + "TOPLAN.jasper");
              JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);

            byte[] bytes = null;
            if (output != null && output.equals("PDF")) {
                response.setContentType("application/pdf"); 
                response.setHeader("Content-Disposition", "attachment;filename=TOPLAN.pdf;");
                bytes = JasperRunManager.runReportToPdf(report, parameters, conn);
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close(); 
            } else if (output != null && output.equals("Excel")) {
                JRXlsExporter exporter = new JRXlsExporter();
                ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, "C:\\JSP\\");
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "TOPLAN.xls");
                exporter.exportReport();
                bytes = xlsReport.toByteArray();
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=TOPLAN.xls;");
                response.setContentLength(bytes.length);
                xlsReport.close();
                OutputStream ouputStream = response.getOutputStream();
                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
   }
   else
   {%>
   <script language="javascript">
   self.close();
   </script>
  <% }
   } 
 %>
 <body bgcolor="#95b174" >
  <form name="hrs" method="POST" >
  <table cellpadding="12" width="70%" cellspacing="1" align="center">
  <tr bgcolor="White">
  <td colspan="5"><h5 style="font-size:15pt" align="center">Dispatch Planning Chart</h5></td>
  </tr>
   
  
  

  <tr bgcolor="White">
    <td class="label-1">View In  </td>
    <td  colspan="4">
    <input type="RADIO" checked="checked" name="output" value="PDF">PDF&nbsp;
    <input type="RADIO"  name="output" value="Excel">Excel&nbsp;
    <%conn.close();%>
    </td>
  </tr>
  <tr bgcolor="White">
    <td colspan="5" align="center">
    <input type="button" class="button"  onclick="self.close()"  name="button" value="Cancel">
    <input type="button" class="button" onclick="submitform()" name="button" value="Finish">
    </td>
  </tr> 
  </table>
 </form>

 </body>
</html>
 