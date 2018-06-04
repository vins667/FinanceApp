<%-- 
    Document   : userbilltype
    Created on : Dec 4, 2013, 3:53:22 PM
    Author     : RANJEET
--%>

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
    <h1 class="label-1">Bill Type</h1>
    <table  cellspacing="0" cellpadding="0"><tr><td style="border-width:1pt;border-color:#808080;border-style:solid;">
     <div style="width:400px;height: 200px; overflow: auto">
        <table cellspacing="1" cellpadding="2" style="width:100%">
            <s:iterator value="typedeptlist" status="typedeptlistst" id="typedeptlisid">
                <tr><td style="background-color: #daa520;color:white" class="label-1"><s:property value="%{EATX40}"/> - <s:property value="%{EAAITM}"/></td>
                    <td style="background-color: #daa520;color:white" >
                        <%--
                        <input name="chk" type="checkbox" onclick="CheckAll(document.searchsubs.BILLTYPECODE,this)"/>
                        --%>                     
</td>
                </tr>
          
           <s:iterator value="savetypelist.{?#this.TYPE_SL_NO==#typedeptlisid.EAAITM}" status="savetypelistst">
            <tr style="background-color: #FFFFFF">
                <td style="font-size: 9px"><s:property value="%{SUB_TYPE_DESC}"/></td> 
                <td><input name="BILLTYPECODE" checked="true" type="checkbox" value="<s:property value="%{TYPE_SL_NO}"/>:<s:property value="%{SUB_TYPE_CODE}"/>" /></td>
           </tr>
        </s:iterator>
                
                
        <s:iterator value="typelist.{?#this.TYPE_SL_NO==#typedeptlisid.EAAITM}" status="typelistst">
            <tr style="background-color: #FFFFFF">
                <td style="font-size: 9px"><s:property value="%{SUB_TYPE_DESC}"/></td> 
                <td><input name="BILLTYPECODE" type="checkbox" value="<s:property value="%{TYPE_SL_NO}"/>:<s:property value="%{SUB_TYPE_CODE}"/>" /></td>
           </tr>
        </s:iterator>
          </s:iterator>
    </table>
  </div>
    </td></tr></table>
    </body>
</html>
