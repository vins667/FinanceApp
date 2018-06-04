<%-- 
    Document   : callinvmos
    Created on : May 9, 2013, 9:47:38 AM
    Author     : Sanjeev
--%>

<%@page import="shahi.Action.MvxExp.InvMosMailAction"%>
<%@page import="shahi.Action.MvxExp.LGplanAction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           InvMosMailAction mvx001=new InvMosMailAction(); 
           mvx001.InvMosMailAction();
           
           LGplanAction mvx002=new LGplanAction(); 
           mvx002.LGplanAction(); 
           
        %> 
    </body>
</html>
