<%-- 
    Document   : FundPoDetails
    Created on : Aug 4, 2012, 1:10:18 PM
    Author     : RANJEET SINGH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sepl</title>
    </head>
    <body>
        <table><tr>
                <s:if test="%{ponumb!=null && rpoamttemp==0}">
                </s:if>
                <td align="right" style="width:100px;">
                    
                    <s:textfield  readonly="true" value="%{reqratetemp}" name="reqrate" id="reqrate%{textid}"  cssClass="textreadonly"   theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/>
                </td>
                <td style="width:100px;"><s:textfield  readonly="true" value="%{reqpodttemp}" name="reqpodt"  cssClass="textreadonly"   theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                <td align="right" style="width:100px;"><s:textfield  readonly="true" value="%{rpoamttemp}" name="rpoamt" id="rpoamt%{textid}"  cssClass="textreadonly"   theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                <td align="right" style="width:100px;"><s:textfield  value="%{advamttemp}" readonly="true" name="advamt"  id="advamt%{textid}"  cssClass="textreadonly"  theme="simple" cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                <s:if test="%{reqpoamttemp!=null && reqpoamttemp.length()>0}">
                    <td align="right" style="width:100px;"><s:textfield name="reqpoamt" value="%{reqpoamttemp}" theme="simple" id="reqpoamt%{textid}" onkeyup="checkbalamt(this,%{textid});addReqAmt()" cssClass="texts"  cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                </s:if>
                <s:else>
                    <td align="right" style="width:100px;"><s:textfield name="reqpoamt" value="0" theme="simple" id="reqpoamt%{textid}" onkeyup="checkbalamt(this,%{textid});addReqAmt()" cssClass="texts"  cssStyle="width:90pt;text-align:right;font-size:9pt"/></td>
                </s:else>
                <td align="right" style="width:100px;">
                    <table>
                        <tr>
                            <td><s:hidden name="CHGPCT" id="CHGPCT%{textid}"/>
                                <input type="checkbox" name="srvtaxapp" id="srvtaxapp<s:property value="%{textid}"/>" onclick="calculateservice(this,'<s:property value="%{textid}"/>');" disabled="true"/>
                            </td>
                            <td><s:textfield  value="%{srvtaxtemp}"  name="srvtax"  id="srvtax%{textid}"  cssClass="texts"  theme="simple" cssStyle="width:80px;text-align:right;font-size:9pt"/></td>
                        </tr>
                    </table>
                </td>    
                <td></td>
            </tr>
        </table>                
    </body>
</html>
