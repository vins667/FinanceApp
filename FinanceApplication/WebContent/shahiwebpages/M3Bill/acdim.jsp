<%-- 
    Document   : acdim
    Created on : Oct 10, 2013, 6:29:53 PM
    Author     : RANJEET
--%>

<%@page import="shahi.Action.M3bill.M3BillBatchFunction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
      M3BillBatchFunction obj=new M3BillBatchFunction();
      obj.UpdateBatch();
      %>
    </body>
</html>
