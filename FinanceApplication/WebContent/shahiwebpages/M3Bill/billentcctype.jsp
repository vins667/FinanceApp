<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
      <s:head />
          <sx:head />
<script>
  
</script>
</head>
<body style="margin: 0px;padding: 0px">
    <s:if test="%{DIS==null}">
     <s:select id="CC_CODE" theme="simple" onchange=""
               name="CC_CODE"  value="%{CC_CODE}" 
               cssStyle="width:250px" headerKey=""
               headerValue="Select Cost Center" 
               cssClass="selecttext"
               listKey="EAAITM" listValue="EATX40"
               list="cclist"/>
       
    </s:if>
    <s:else>
        <s:select id="CC_CODE" theme="simple" 
               name="CC_CODE"  value="%{CC_CODE}" 
               cssStyle="width:250px"
               cssClass="selecttext"
               listKey="EAAITM" listValue="EATX40"
               list="cclist"/>  
    </s:else>
          
    </body>
</html>


