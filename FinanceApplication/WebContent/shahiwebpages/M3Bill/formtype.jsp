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
     <s:select id="TYPE_SL_NO" theme="simple" onchange="show_details1();show_details2()" name="TYPE_SL_NO" listKey="SL_NO" value="%{TYPE_SL_NO}" cssStyle="width:300px" headerKey="" headerValue="Select Bill Type" cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="typelist"/>
       
    </s:if>
    <s:else>
       <s:select id="TYPE_SL_NO" theme="simple"  name="TYPE_SL_NO" listKey="SL_NO" value="%{TYPE_SL_NO}" cssStyle="width:300px"  cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="typelist"/>
       
    </s:else>
          
    </body>
</html>


