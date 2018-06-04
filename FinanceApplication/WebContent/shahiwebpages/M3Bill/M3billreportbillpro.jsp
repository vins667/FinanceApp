<%-- 
    Document   : M3billreportbilltype
    Created on : Jan 2, 2014, 3:24:43 PM
    Author     : RANJEET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sepl</title>
    </head>
    <body style="margin: 0px;padding: 0px">
     <s:select name="leftproduct"
                            id="leftproduct"
                            list="#{}"
                            theme="simple"
                            cssClass="selecttext"
                            cssStyle="width:300px"
                            size="8"
                            multiple="true">
         <s:iterator value="m3productlistgrp"  status="m3productlistgrpst">
             <s:set id="aa" name="aa" value="%{EAAITM}"/>
             <s:set id="aadesc" name="aadesc" value="%{EATX40}"/>
             <s:optgroup label="%{#aadesc}"  
                                         list="m3productlist.{?#this.DESC==#aa}"
                                         listKey="EAAITM"
                                         listValue="EATX40"
                                         />
         </s:iterator>
     </s:select>
                            
    </body>
</html>
