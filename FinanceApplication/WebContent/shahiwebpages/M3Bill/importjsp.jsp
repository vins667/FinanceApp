<%-- 
    Document   : importjsp
    Created on : Oct 24, 2013, 3:17:28 PM
    Author     : RANJEET
--%>

<%-- 
    Document   : invmast
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
         <th class="label-1">AWB No</th></tr>
        </thead>
         <tbody>
             <s:set id="ctnsave" name="ctnsave" value="0"/>
            
            <s:iterator value="invlist" status="invlistst">
                <tr style='background-color: #FFFFFF'>
                    <td><s:textfield name="INV_NONEWMASTER" id="INV_NO" theme="simple" cssClass="textreadonly" cssStyle="width:250px" readonly="true" value="%{EAAITM}"/>
                    </td>
                    <td>
                      <input type="checkbox" name="dtchkNEW" checked="true" onclick="saveflagnew(this,'INVMASTER<s:property value="%{#ctnsave}"/>')"/>
                      <s:hidden name="INV_SAVENEWMASTER"  value="Yes" id="INVMASTER%{#ctnsave}"/>
                </td>
                </tr>
                 <s:set id="ctnsave" name="ctnsave" value="%{#ctnsave+1}"/>
            </s:iterator>
          </tbody>
          </table>
          </div>
         </td></tr>
       
        <tr><td>
                <table cellpadding="0" width="100%"><tr><td width="300px">
                <table>
                             <tr>
                                                <td><div style="width:25px;height:20px;background-color:#ffffce;border-width:1pt;border-color:#ffffce;border-style:solid;"></div></td><td class="label-1">Saved Entry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:#FFFFFF;border-width:1pt;border-color:#FFFFFF;border-style:solid;"></div></td><td class="label-1">New Entry</td>
                                            </tr>
                                            </table> 
                            </td>
                            <td>
                                 <button class="sexybutton" onclick='document.getElementById("IMPORTSDIV").style.display = "none";'><span><span><span class="save">Save</span></span></span></button> 
                        
               
                            </td>
                    </tr></table>
               
                
            </td>
        </tr>
                </table>
            
       
    </body>
</html>
