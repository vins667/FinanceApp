<%-- 
    Document   : InvLineDetails
    Created on : Mar 19, 2015, 10:50:59 AM
    Author     : Sanjeev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 30px ;
        
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);                     
                  }
                tbody {
                    height: auto;
                }
                tfoot{
                    background:#3383bb;
                    font-weight:bold;
                }
                .tr2 {
                     position:absolute;
                     bottom:expression(this.offsetParent.scrollTop);
                  }
            </style>
        <![endif]-->
    </head>
    <body>
          <table border="1" cellpadding="5" cellspacing="1" width="100%" bgcolor="#f2f2f2">
                      
                <tr>    
                    <td colspan="7"></td>
                      <td  style="font-size:8pt" align="center" > 
                            <s:textfield name="HSCODE_COPY" id="HSCODE_COPY"  theme="simple" cssClass="texts"  cssStyle="width:150px" tabindex="6"/>
                            <input type="checkbox" value="Y" name="hscp"   title="Copy to All Lines" onclick="copyHS()" />
                       </td> 
                        
               </tr>
           </table>
                              <div class="div1" style="width:100.0%; overflow:auto; height:320.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            <table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                <tr class="tr1">
                                                    <th style="font-size:8pt" align="left" height="25pt">CO No</th>
                                                    <th style="font-size:8pt" align="left"  height="25pt">Line</th>
                                                    <th style="font-size:8pt" align="left">Item No</th>
                                                    <th style="font-size:8pt" align="left">Unit</th>
                                                    <th style="font-size:8pt" align="right">Qnty </th>
                                                    <th style="font-size:8pt" align="left">Dbk SlNo</th>
                                                    <th style="font-size:8pt" align="left">Inv Desc</th>
                                                    <th style="font-size:8pt" align="left">Plan Desc</th>
                                                    <th style="font-size:8pt" align="left">HS Code</th>
                                                </tr>                                                
                                                <tbody>
                                                    <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="0"/>
                                                       <s:iterator value="InvLineList" status="listid">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="CO_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="CO_LINE"/></td>
                                                            <td style="font-size:8pt"><s:property value="ITEM_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="UNIT"/></td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="QTY_ENDORS"/> 
                                                                <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="%{#TOT_QTY_ENDORS+QTY_ENDORS}"/>
                                                            </td> 
                                                             <td style="font-size:8pt"><s:property value="DBK_SLNO"/></td>
                                                              <td style="font-size:8pt"><s:property value="INV_DESC"/></td>
                                                              <td style="font-size:8pt"><s:property value="PLAN_DESC"/></td>
                                                              <td style="font-size:8pt"><s:textfield name="HSCODE"  id="HSCODE%{#listid.index}" cssStyle="text-transform:uppercase;width:100pt;font-size:9pt;" theme="simple" value="%{TOKEN_NO}" maxLength="20" />
                                                                  <input type="checkbox" value="Y" name="cpselectHS"  title="Copy Selected Lines" onclick="copySelectedHS(<s:property value="%{#listid.index}"/>)" /> 
                                                                </td>
                                                                <s:hidden name="CO_NO_HS" value="%{CO_NO}"/>
                                                                <s:hidden name="CO_LINE_HS" value="%{CO_LINE}"/>
                                                                <s:hidden name="ITEM_NO_HS" value="%{ITEM_NO}"/>
                                                        </tr>
                                                    </s:iterator> 
                                                </tbody> 
                                                <tr bgcolor="#f2f9fb">
                                                    <td style="font-size:8pt" colspan="4" align="right" class="label-1">Total</td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_QTY_ENDORS}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                              
                                                </tr>
                                            </table>
                                        </div>                                                 
                                 
    </body>
</html>
