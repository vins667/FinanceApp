<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
     
</head>
<body style="margin: 0px;padding: 0px">
    <s:if test="%{DIS==null}">
        <s:select id="SUB_TYPE_SL_NO" name="SUB_TYPE_SL_NO" value="%{SUB_TYPE_SL_NO}" onchange="show_details2()" theme="simple" listKey="SL_NO" cssStyle="width:280px" headerKey="" headerValue="Select Bill Sub Type" cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="subtypelist"/>
    
     </s:if>
    <s:else>
      <s:select id="SUB_TYPE_SL_NO" name="SUB_TYPE_SL_NO" value="%{SUB_TYPE_SL_NO}"  theme="simple" listKey="SL_NO" cssStyle="width:280px" cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="subtypelist"/>
      
    </s:else>
    </body>
</html>


