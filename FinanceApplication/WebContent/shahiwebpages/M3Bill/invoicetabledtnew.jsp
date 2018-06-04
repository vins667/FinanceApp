<%-- 
    Document   : invoicetabledt
    Created on : Sep 28, 2013, 4:23:30 PM
    Author     : RANJEET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="css/style.css"> 
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sepl</title>
                  
    </head>
    <body style="background-color:#f2f2f2;padding: 0px;margin: 0px">
        
        <table width="100%" cellpadding="0" cellspacing="0"><tr><td style="border-width:1pt;border-color:black;border-style:solid;">                           
        <div  class="div1" style="width:100%;overflow:auto ;height:260px;">
         <table border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
         <thead >
       <tr  class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);height:15pt" >
         <th class="label-1">Invoice</th><th class="label-1">Weight</th></tr>
        </thead>
         <tbody>
             <s:set id="ctnsave" name="ctnsave" value="0"/>
             <s:iterator value="invsavelist" status="invsavelistst">
                <tr  style='background-color: #ffffce'>
                    <td><s:textfield name="INV_NONEW" id="INV_NO" theme="simple" cssClass="textreadonly" cssStyle="width:260px" readonly="true" value="%{EAAITM}"/>
                    </td>
                <td><s:textfield name="INV_WEIGHTNEW" theme="simple" cssClass="texts" onblur="validatenumber(this)" cssStyle="width:260px"  value="%{EATX40}"/></td>
                <td>
                    <input type="checkbox" checked="true" name="dtchkNEW" onclick="saveflagnew(this,'<s:property value="%{EAAITM}"/>id<s:property value="%{INVSLTXT}"/>')"/>
                    <s:hidden name="INV_SAVENEW" value="Yes" id="%{EAAITM}id%{INVSLTXT}"/>
                </td>
                </tr> 
                <s:set id="ctnsave" name="ctnsave" value="%{#ctnsave+1}"/>
             </s:iterator>
            <s:iterator value="invlist" status="invlistst">
                <tr style='background-color: #FFFFFF'>
                    <td><s:textfield name="INV_NONEW" id="INV_NO" theme="simple" cssClass="textreadonly" cssStyle="width:260px" readonly="true" value="%{EAAITM}"/>
                    </td>
                    <td><s:textfield name="INV_WEIGHTNEW" theme="simple" cssClass="texts" onblur="validatenumber(this)" cssStyle="width:260px"  value=""/></td>
                  <td>
                      <input type="checkbox" name="dtchkNEW" checked="true" onclick="saveflagnew(this,'<s:property value="%{EAAITM}"/>id<s:property value="%{INVSLTXT}"/>')"/>
                    <s:hidden name="INV_SAVENEW"  value="Yes" id="%{EAAITM}id%{INVSLTXT}"/>
                </td>
                </tr>
                 <s:set id="ctnsave" name="ctnsave" value="%{#ctnsave+1}"/>
            </s:iterator>
          </tbody>
          </table>
          </div>
         </td></tr>
       
        <tr><td>
                <table>
                             <tr>
                                                <td><div style="width:25px;height:20px;background-color:#ffffce;border-width:1pt;border-color:#ffffce;border-style:solid;"></div></td><td class="label-1">Saved Entry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:#FFFFFF;border-width:1pt;border-color:#FFFFFF;border-style:solid;"></div></td><td class="label-1">New Entry</td>
                                            </tr>
                                            </table> 
                <s:hidden theme="simple" name="INVSLTXTPAGE" value="%{INVSLTXT}"/>
                <s:hidden theme="simple" name="INVSLTXTCTN" value="%{invlist.size()+invsavelist.size()}"/>
                
            </td>
                </table>
            
       
    </body>
</html>
