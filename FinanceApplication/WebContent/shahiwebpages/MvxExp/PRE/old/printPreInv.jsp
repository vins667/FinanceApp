


<%@page import="shahi.Action.MvxExp.Reports.PRE.PreInvPrintPDFAction"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="java.util.Collection"%>
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
        <%
           // if(request.getMethod()=="POST"){
           // Connection con = null;
            //con = new connection().getConnection();
                try{
            byte[] bytes = null;  
            Map parameters = new HashMap();
             String path = getServletContext().getRealPath("/") + "shahiwebpages/MvxExp/reports/PRE/";
              parameters.put("SUBREPORT_DIR",path);
             //parameters.put("REPORT_CONNECTION", con);    
             // parameters.put("p_inv", request.getParameter("P_INV")); 
              String file_name=request.getParameter("P_INV");
              Collection list = new PreInvPrintPDFAction().getRecord(request.getParameter("P_INV"));
              JasperReport report = null; 
            if (request.getParameter("CRNCY").equals("INR"))
            {report = (JasperReport) JRLoader.loadObject(path +"PreInvDOM.jasper"); 
            }else{
             report = (JasperReport) JRLoader.loadObject(path +"PreInvPrint.jasper"); 
            }
            
             //   report = (JasperReport) JRLoader.loadObject("D:\\SHAHIPROJECT\\ShahiApplication\\build\\web\\shahiwebpages\\MvxExp\\reports\PREmyjasper.jasper"); 
             JasperPrint print = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(list));
             
            OutputStream out1 = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename="+file_name+".pdf");
            response.setHeader("cache-control", "no-cache");
            response.setDateHeader("Last-Modified", 123);
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(print, out1);
            out1.flush();
            out1.close();  
                }
                catch(Exception e){System.out.println(e.getMessage());}
//}
%> 