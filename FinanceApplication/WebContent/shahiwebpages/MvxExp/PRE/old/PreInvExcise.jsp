 
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
                    <s:if test='searchloct.equals("200")'>  
                            <s:if test='%{EXCISE_UNIT==null}'>
                                <td style="font-size:8pt" align="center" colspan="7">Excise Unit :
                                 <s:select name="EXCISE_UNIT" id="EXCISE_UNIT" value="%{EXCISE_UNIT}" list="%{EXCISE_UNIT_LIST}"  theme="simple"  headerKey=""  cssClass="texts" cssStyle="width:70px;text-align:right"  headerValue=""  /> 
                              </td>
                            </s:if>  
                            <s:else>
                                <td style="font-size:9pt;font-weight: bold" align="center" colspan="7">Excise Unit :
                                <s:property value="EXCISE_UNIT"/> -
                                <s:property value="ex_inv_slno"/>- 
                                <s:property value="ex_inv_date"/>  </td>
                            </s:else> 
                    </s:if>
                    <s:else><s:hidden name="EXCISE_UNIT" value="%{EXCISE_UNIT}"/></s:else>    
                    <td  style="font-size:8pt" align="center" > MRP Price :
                            <s:textfield name="MRPRATE_COPY" id="MRPRATE_COPY"  theme="simple" cssClass="texts"  cssStyle="width:100px;text-align:right" tabindex="6"/>
                            <input type="checkbox" value="Y" name="mrpcp"   title="Copy to All Lines" onclick="copyMRP()" />
                   </td> 
                        
                        <s:if test='%{EX_GEN_ALLOW.equals("YES") }'> 
                           <td align="center">
                                <button  id="exciseId" class="sexybutton" onclick="generateExcise()"><span><span><span class="search" id="saveId">Generate Exise Inv</span></span></span></button>
                            </td>   
                        </s:if>  
                           
               </tr>
           </table> 
                              <div class="div1" style="width:100.0%; overflow:auto; height:320.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            <table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                <tr class="tr1">
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left" height="25pt">CO No</th>
                                                    <th style="font-size:8pt" align="left"  height="25pt">Line</th>
                                                    <th style="font-size:8pt" align="left">Item No</th>
                                                    <th style="font-size:8pt" align="left">Unit</th>
                                                    <th style="font-size:8pt" align="left">Inv Desc</th>
                                                    <th style="font-size:8pt" align="right">Qnty </th>
                                                    <th style="font-size:8pt" align="left">Price FC</th>
                                                    <th style="font-size:8pt" align="left">FOB FC</th>
                                                    <th style="font-size:8pt" align="left">Price MRP</th>
                                                      
                                                </tr>                                                
                                                <tbody>
                                                    <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="0"/>
                                                    <s:set id="TOT_FOB" name="TOT_FOB" value="0"/>
                                                       <s:iterator value="InvLineList" status="listid">
                                                        <tr bgcolor="#f2f9fb">
                                                             <td style="font-size:8pt"><s:property  value="%{#listid.index+1}"/></td>
                                                         
                                                            <td style="font-size:8pt"><s:property value="CO_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="CO_LINE"/></td>
                                                            <td style="font-size:8pt"><s:property value="ITEM_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="UNIT"/></td>
                                                           <td style="font-size:8pt"><s:property value="INV_DESC"/></td>
                                                           <td style="font-size:8pt" align="right">
                                                                <s:property value="QTY_ENDORS"/> 
                                                                <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="%{#TOT_QTY_ENDORS+QTY_ENDORS}"/>
                                                            </td> 
                                                             <td style="font-size:8pt" align="right">
                                                                <s:property value="PRICE_FC"/> 
                                                             </td> 
                                                              <td style="font-size:8pt" align="right">
                                                                <s:property value="FOB_FC"/> 
                                                                <s:set id="TOT_FOB" name="TOT_FOB" value="%{#TOT_FOB+FOB_FC}"/>
                                                       
                                                             </td>
                                                            
                                                                <td style="font-size:8pt"><s:textfield name="MRP_RATE"  id="MRP_RATE%{#listid.index}" cssStyle="text-align:right;width:100pt;font-size:9pt;" theme="simple" value="%{QTY_KGS}" maxLength="10" />
                                                                       <input type="checkbox" value="Y" name="cpselectMRP"  title="Copy Selected Lines" onclick="copySelectedMRP(<s:property value="%{#listid.index}"/>)" /> 
                                                                </td>
                                                                <s:hidden name="CO_NO_EXCISE" value="%{CO_NO}"/>
                                                                <s:hidden name="CO_LINE_EXCISE" value="%{CO_LINE}"/>
                                                                                       </tr>
                                                    </s:iterator> 
                                                                
                                                </tbody>  
                                                <tr bgcolor="#f2f9fb">
                                                    <td style="font-size:8pt" colspan="6" align="right" class="label-1">Total</td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_QTY_ENDORS}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_FOB}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                </tr>
                                            </table>
                                  </div>                                                 
                                   
    </body>
</html>
