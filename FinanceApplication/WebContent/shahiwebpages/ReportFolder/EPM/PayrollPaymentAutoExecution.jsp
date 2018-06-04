<%-- 
    Document   : PayrollPaymentAutoExecution
    Created on : Mar 22, 2013, 4:28:01 PM
    Author     : Kuldeep Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="shahi.Action.ReportFolder.EPM.PayrollPaymentsAutoExecuteAction"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
       PayrollPaymentsAutoExecuteAction objtemp=new PayrollPaymentsAutoExecuteAction();
       objtemp.execute();
%>
    </body>
</html>
