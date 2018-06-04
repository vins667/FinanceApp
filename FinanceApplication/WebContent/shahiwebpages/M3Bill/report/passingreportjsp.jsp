
<%@page contentType="text/html;charset=windows-1252"%>
<%@page import="java.io.*,java.util.*,javax.naming.InitialContext,
        net.sf.jasperreports.engine.*,net.sf.jasperreports.engine.design.JasperDesign,
        net.sf.jasperreports.engine.xml.JRXmlLoader,net.sf.jasperreports.engine.export.*"%>
<%@page language="java"%>
<%@page import="java.sql.*" %>
<%@page import="java.lang.*"%>
<%@page import="java.lang.Object"%>	
<%@page import="java.util.Date"%>
<%@page import="java.util.ResourceBundle" %>
<%@page import="org.apache.commons.collections.*"%>
<%@page import="org.apache.commons.digester.Digester"%>
<%@page import="org.apache.commons.logging.*"%>
<%@page import="org.apache.commons.beanutils.*"%>
<%@page import="net.sf.jasperreports.*"%>
<%@page import="net.sf.jasperreports.engine.fill.*"%>
<%@page import="java.util.Properties"%> 
<%@page import="javax.naming.*"%>
<%@page import="javax.activation.DataHandler"%>
<%@page import="javax.activation.DataSource"%>
<%@page import="javax.activation.FileDataSource"%>
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
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="oracle.jdbc.driver.OracleDriver"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Shahi Exports Pvt Ltd.</title>

        <%  
	
                                       
                                            Connection con = null;
                                           
                                        try{	
                                            ResourceBundle aResourcBundle = null;
                                            aResourcBundle = ResourceBundle.getBundle("shahi.Action.database.app");
                                            String URL=aResourcBundle.getString("URLSEPL");
                                            String UserName=aResourcBundle.getString("UserNameSepl");
                                            String Password=aResourcBundle.getString("PasswordSepl");
                                            String driver=aResourcBundle.getString("driversepl");
                                            Class.forName(driver);
                                            con=DriverManager.getConnection(URL,UserName,Password);		   	   
                                           
                                            SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM,yyyy");
                                           
                                               
                                              
                                        String usrid=request.getParameter("usrid");
                                         String cno=request.getParameter("cno");
                                              // ITEM="V832" ;
                                        byte[] bytes = null;
	        		
                                        Map parameters = new HashMap();
                                        parameters.put("cno",cno);
	                                parameters.put("usrid",usrid);
                       
                                        JasperReport report = (JasperReport) JRLoader.loadObject(request.getRealPath("/shahiwebpages/M3Bill/report/billpassing.jasper"));
		  		 	parameters.put("SUBREPORT_DIR",request.getRealPath("/shahiwebpages/M3Bill/report/")+"/");
                                          
                              
	 	
                                
                        
                               
                                                       JasperPrint print = JasperFillManager.fillReport(report,parameters,con);
					                OutputStream out1 = response.getOutputStream();
                                                        response.reset();
                                                        response.setHeader("Content-Disposition","inline; filename="+cno+".pdf");
                                                        response.setHeader("cache-control", "no-cache");
                                                        response.setDateHeader("Last-Modified", 123);
                                                        response.setContentType("application/pdf");
                                                        JasperExportManager.exportReportToPdfStream(print, out1);
                                                        out1.flush();
                                                        out1.close();
                                              
					
                               
		
     
        
             //do not close the connection at any point on time 
                    
        
          }
      catch(Exception e){
        response.getWriter().write(e.getMessage());
      }  finally {
                    if (con != null) {
                        con.close();
                    }
                    con = null;

                   
            }

        %>



        
        <link href="../style/style.css" rel="stylesheet" type="text/css"/>
     
    </head>
    <body>
        
            <table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td  style="text-align:center; font-size:medium;height:25px">
                       Record not found....
                    </td>
                </tr>
            </table>
           
       
    </body>
</html>	