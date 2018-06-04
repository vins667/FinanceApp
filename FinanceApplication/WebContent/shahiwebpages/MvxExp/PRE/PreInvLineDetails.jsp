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
        
                              <div class="div1" style="width:100.0%; overflow:auto; height:320.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            <table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                <tr class="tr1">
                                                    <th style="font-size:8pt" align="left" height="25pt">CO No</th>
                                                    <th style="font-size:8pt" align="left"  height="25pt">Line</th>
                                                    <th style="font-size:8pt" align="left">Item No</th>
                                                    <th style="font-size:8pt" align="left">Unit</th>
                                                    <th style="font-size:8pt" align="right">Qnty </th>
                                                    <th style="font-size:8pt" align="right">Price Fc</th>
                                                    <th style="font-size:8pt" align="right">Price Misc</th>
                                                    <th style="font-size:8pt" align="right">Adjust Fc</th>
                                                    <th style="font-size:8pt" align="right">Net Price</th>
                                                    <th style="font-size:8pt" align="right">Fob Fc</th>
                                                    <th style="font-size:8pt" align="right">Gr Decl Amt</th>
                                                     <th style="font-size:8pt" align="right">Gr Decl %</th>
                                                    <th style="font-size:8pt" align="left">Dbk SlNo</th>
                                                     <th style="font-size:8pt" align="left">Buyer PO#</th>
                                                    <th style="font-size:8pt" align="left">Buyer Style</th>
                                                    <th style="font-size:8pt" align="left">Inv Desc</th>
                                                    <th style="font-size:8pt" align="left">Plan Desc</th>
                                                </tr>                                                
                                                <tbody>
                                                    <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="0"/>
                                                    <s:set id="TOT_QTY_KGS" name="TOT_QTY_KGS" value="0"/>                                                    
                                                    <s:set id="TOT_FOB_FC" name="TOT_FOB_FC" value="0"/>
                                                    <s:set id="TOT_GR_DECL_AMT" name="TOT_GR_DECL_AMT" value="0"/>
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
                                                           <td style="font-size:8pt" align="right">
                                                                <s:property value="PRICE_FC"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="PRICE_MISC"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="ADJUST_FC"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="NET_PRICE"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="FOB_FC"/>
                                                                <s:set id="TOT_FOB_FC" name="TOT_FOB_FC" value="%{#TOT_FOB_FC+FOB_FC}"/>
                                                            </td>
                                                            <td style="font-size:8pt" align="right"> 
                                                                <s:property value="GR_DECL_AMT"/>
                                                                <s:set id="TOT_GR_DECL_AMT" name="TOT_GR_DECL_AMT" value="%{#TOT_GR_DECL_AMT+GR_DECL_AMT}"/>
                                                            </td>
                                                            <td style="font-size:8pt" align="right"> 
                                                                <s:property value="GR_DECL_PERK"/>
                                                           </td>
                                                            <td style="font-size:8pt"><s:property value="DBK_SLNO"/></td>
                                                             <td style="font-size:8pt"><s:property value="PRE_PRINT_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="TOKEN_NO"/></td>
                                                             <td style="font-size:8pt"><s:property value="INV_DESC"/></td>
                                                             <td style="font-size:8pt"><s:property value="PLAN_DESC"/></td>
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
                                                      <s:text name="product.eff" var="fcvalue" >
                                                                 <s:param name="fcvalue" value="%{TOT_FOB_FC}"/>
                                                      </s:text> 
                                                     <s:text name="product.eff" var="grdecl" >
                                                                 <s:param name="grvalue" value="%{TOT_GR_DECL_AMT}"/>
                                                    </s:text> 
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#fcvalue}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#grdecl}"/></td>
                                                    <td style="font-size:8pt"></td>
                                                    <td style="font-size:8pt" align="right"></td>
                                                    <td style="font-size:8pt"></td>
                                                   <td style="font-size:8pt"></td>
                                                  <td style="font-size:8pt"></td>
                                                  <td style="font-size:8pt"></td>
                                                </tr>
                                            </table>
                                        </div>                                                 
                                 
    </body>
</html>
