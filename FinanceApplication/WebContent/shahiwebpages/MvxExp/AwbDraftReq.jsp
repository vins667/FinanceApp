<%-- 
    Document   : callinvmos
    Created on : May 9, 2013, 9:47:38 AM
    Author     : Sanjeev
--%>

<%@page import="shahi.Action.MvxExp.AwbDraftReqAction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> 
        <%
           AwbDraftReqAction mvx002=new AwbDraftReqAction(); 
           mvx002.AwbDraftReqAction();
        %>
    </body>
</html>
  