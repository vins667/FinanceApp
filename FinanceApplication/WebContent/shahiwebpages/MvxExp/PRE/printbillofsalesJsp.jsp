


<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="shahi.Action.database.connection"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Connection con = null;
            con = new connection().getConnection();
            byte[] bytes = null;  
            Map parameters = new HashMap();
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/PRE/";
             parameters.put("SUBREPORT_DIR",path);
             parameters.put("REPORT_CONNECTION", con);  
              parameters.put("p_loct", request.getParameter("LOCATION_CODE")); 
              parameters.put("p_bos_from", request.getParameter("BOS_NO"));
              parameters.put("p_bos_to", request.getParameter("BOS_NO"));
              parameters.put("date_from", request.getParameter("BOS_DATE"));
              parameters.put("date_to", request.getParameter("BOS_DATE"));
              JasperReport report = null;
              String loctcode=request.getParameter("LOCATION_CODE");
              if (loctcode.substring(0,1).equals("1"))
              {
                report = (JasperReport) JRLoader.loadObject(path +"bos_prn_100.jasper");
              }
              if (loctcode.substring(0,1).equals("2"))
              {
                  report = (JasperReport) JRLoader.loadObject(path +"bos_prn_200.jasper");
              }   
             JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
              
            OutputStream out1 = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "inline; filename=pcd.pdf");
            response.setHeader("cache-control", "no-cache");
            response.setDateHeader("Last-Modified", 123);
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(print, out1);
            out1.flush();
            out1.close();  		
%>
    </body>
</html>
