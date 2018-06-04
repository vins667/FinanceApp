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
    
    <s:if test="%{srvtaxmasterlist.size()==1}">
        <s:select id="SRVTAX_GL_CODE" theme="simple"   name="SRVTAX_GL_CODE"  listKey="EAAITM" value="%{SRVTAX_GL_CODE}" cssStyle="width:190px"  cssClass="selecttext" listValue="%{EATX40+'-'+EAAITM}" list="srvtaxmasterlist"/>
    </s:if>
    <s:else>
    <s:select id="SRVTAX_GL_CODE" theme="simple"  name="SRVTAX_GL_CODE" headerKey="" headerValue="Select GL Code" listKey="EAAITM" value="%{SRVTAX_GL_CODE}" cssStyle="width:190px"  cssClass="selecttext" listValue="%{EATX40+'-'+EAAITM}" list="srvtaxmasterlist"/>
   </s:else>
    <s:hidden name="REVERSE_SRVTAX_RATE"  value="%{REVERSE_SRVTAX_RATE}"/>
    </body>
</html>


