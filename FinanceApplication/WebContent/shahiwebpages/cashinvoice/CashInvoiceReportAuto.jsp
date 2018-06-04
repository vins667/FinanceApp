<%-- 
    Document   : CashInvoiceReportAuto
    Created on : Sep 12, 2017, 02:30:33 PM
    Author     : Vivek
--%>

<%@page import="shahi.Action.database.ConnectionSeplWeb"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
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


<%@page import="net.sf.jasperreports.charts.*"%>

<%
         Connection conn = null;
             try {
                 conn = new ConnectionSeplWeb().getConnection();
                 String SL_NO = request.getParameter("SL_NO");
                 String REPORT_TYPE = request.getParameter("REPORT_TYPE");
                 Map parameters = new HashMap();
                 //System.out.println(qry);
                 parameters.put("SL_NO", SL_NO);
                 InitialContext initialContext = new InitialContext();
                 
                 String filename="CashInvoiceReport";
                 byte[] bytes = null;
                 JasperReport report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/cashinvoice/CashInvoiceReport.jasper"));
                 parameters.put("SUBREPORT_DIR", request.getRealPath("/report"));
                 
                 JasperPrint print = null;
                 print=JasperFillManager.fillReport(report, parameters, conn);
                 if (REPORT_TYPE.equals("XLS")) {
                     parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                 }

                 if (REPORT_TYPE != null && REPORT_TYPE.equals("PDF")) {
                     response.setContentType("application/pdf");
                     response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".pdf;");
                     ServletOutputStream ouputStream = response.getOutputStream();
                     JasperExportManager.exportReportToPdfStream(print, ouputStream);
                     ouputStream.flush();
                     ouputStream.close();
                 } else {
                     JRXlsExporter exporter = new JRXlsExporter();
                     ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
                     exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
                     exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                     exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                     exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                     exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                     exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filename + ".xls");
                     exporter.exportReport();
                     bytes = xlsReport.toByteArray();
                     response.setContentType("application/vnd.ms-excel");
                     response.setHeader("Content-Disposition", "attachment; filename=" + "OB_NO_" + filename + ".xls;");
                     response.setContentLength(bytes.length);
                     xlsReport.close();
                     OutputStream ouputStream = response.getOutputStream();
                     ouputStream.write(bytes, 0, bytes.length);
                     ouputStream.flush();
                     ouputStream.close();
                 }
             } catch (Exception e) {
                 System.out.println("LayerDetailReport.jsp " + e.getMessage());
             } finally {
                 if (conn != null) {
                     conn.close();
                 }
             }
 %>
