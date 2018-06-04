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
                     position:relative;
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
         
                              
                                <div class="div1" style="width:100.0%; overflow:auto; height:250.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            <table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                
                                                <tr class="tr1">
                                                    <th></th>
                                                    <th style="font-size:8pt" align="left"  height="25pt">Licence #</th>
                                                     <th style="font-size:8pt" align="left" height="25pt">Type</th>
                                                     <th style="font-size:8pt" align="right">Lic Qty</th>
                                                     <th style="font-size:8pt" align="right">Inv Qty</th>
                                                     <th style="font-size:8pt" align="right">Fob Amt</th>
                                                     <th style="font-size:8pt" align="right">IO Norms</th>
                                                     <th style="font-size:8pt" align="right">Sqm Qty</th>
                                                     <th style="font-size:8pt" align="left">B/E No</th>
                                                     <th style="font-size:8pt" align="left">B/E Desc</th>
                                                     <th style="font-size:8pt" align="left">Item Desc</th>
                                                     
                                                </tr>                                                
                                                <tbody>
                                                    <s:set id="ctn" name="ctn" value="0"/>
                                                    <s:set id="TOT_QTY" name="TOT_QTY" value="0"/>
                                                    <s:set id="TOT_SQM" name="TOT_SQM" value="0"/>                                                    
                                                    <s:set id="TOT_FOB" name="TOT_FOB" value="0"/>
                                                    <s:set id="TOT_INVQTY" name="TOT_INVQTY" value="0"/>
                                                     
                                           <s:if  test="%{InvLicenceList!=null}">
                                                   
                                                    <s:iterator value="InvLicenceList" status="listid">
                                                        <tr bgcolor="#f2f9fb">
                                                             <td style="width:12px" class="label-1">	
                                                                    <input type="button" class="texts"  tabindex="-1" style="font-size: 10px;margin: 0px;padding: 0px" name="btn" onclick="deletetablerow('<s:property value="%{#ctn}"/>')" value="X">
                                                             </td>
                                                             <td style="font-size:8pt"><s:textfield name="REF_NO" readonly="true" id="REF_NO%{#ctn}" cssStyle="width:80px"  value="%{REF_NO}" cssClass="texts" theme="simple"/></td>
                                                             <td style="font-size:8pt"><s:textfield name="REF_TYPE" readonly="true" id="REF_TYPE%{#ctn}" cssStyle="width:50px"  value="%{REF_TYPE}" cssClass="texts" theme="simple"/></td>
                                                           
                                                             <td style="font-size:8pt" align="right">
                                                                <s:textfield name="QTY" readonly="true" id="QTY%{#ctn}" cssStyle="width:60px;text-align:right"  value="%{QTY}" cssClass="texts" theme="simple"  onblur="validatenumber1(this,%{#ctn});"/>
                                                                <s:set id="TOT_QTY" name="TOT_QTY" value="%{#TOT_QTY+QTY}"/>
                                                             </td>
                                                            <td style="font-size:8pt" align="right">
                                                                   <s:textfield name="ADJS_QTY" readonly="true" id="ADJS_QTY%{#ctn}" cssStyle="width:60px;text-align:right"  value="%{ADJS_QTY}" cssClass="texts" theme="simple"/>
                                                                   <s:set id="TOT_INVQTY" name="TOT_INVQTY" value="%{#TOT_INVQTY+ADJS_QTY}"/>
                                                            </td> 
                                                            <td style="font-size:8pt" align="right">
                                                                  <s:textfield name="FOB_AMT" readonly="true" id="FOB_AMT%{#ctn}" cssStyle="width:60px;text-align:right"  value="%{FOB_AMT}" cssClass="texts" theme="simple"/>
                                                                  <s:set id="TOT_FOB" name="TOT_FOB" value="%{#TOT_FOB+FOB_AMT}"/>
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:textfield name="IO_NORMS" readonly="true" id="IO_NORMS%{#ctn}" cssStyle="width:60px;text-align:right"  value="%{IO_NORMS}" cssClass="texts" theme="simple"/>
                                                            </td>
                                                            <td style="font-size:8pt" align="right"> 
                                                                  <s:textfield name="QTY_SQM" readonly="true" id="QTY_SQM%{#ctn}" cssStyle="width:60px;text-align:right"  value="%{QTY_SQM}" cssClass="texts" theme="simple"/>
                                                                  <s:set id="TOT_SQM" name="TOT_SQM" value="%{#TOT_SQM+QTY_SQM}"/>
                                                            </td>
                                                             <td style="font-size:8pt" >
                                                                  <s:textfield name="BE_NO" readonly="true" id="BE_NO%{#ctn}" cssStyle="width:60px"  value="%{BE_NO}" cssClass="texts" theme="simple"/>
                                                             </td>
                                                             <td style="font-size:8pt"> 
                                                                  <s:textfield name="BE_DESC" readonly="true" id="BE_DESC%{#ctn}" cssStyle="width:400px"  value="%{BE_DESC}" cssClass="texts" theme="simple"/>
                                                         
                                                             </td>
                                                             <td style="font-size:8pt"> 
                                                              
                                                                 <s:textfield name="LITEM_DESC" readonly="true" id="LITEM_DESC%{#ctn}" cssStyle="width:300px"  value="%{LITEM_DESC}" cssClass="texts" theme="simple"/>
                                                                 
                                                             </td>
                                                             <s:hidden name="LIC_DATE" value="%{LIC_DATE}" id="LIC_DATE%{#ctn}"/>
                                                             <s:hidden name="LIC_COMP" value="%{LIC_COMP}" id="LIC_COMP%{#ctn}"/>
                                                             <s:hidden name="LIC_LOCT" value="%{LIC_LOCT}" id="LIC_LOCT%{#ctn}"/>
                                                             <s:hidden name="EXP_CTRL_NO" value="%{EXP_REF_CTRL_NO}" id="EXP_CTRL_NO%{#ctn}"/>    
                                                             <s:hidden name="IMP_CTRL_NO" value="%{IMP_REF_CTRL_NO}" id="IMP_CTRL_NO%{#ctn}"/>  
                                                              <s:hidden name="LIC_AMT_OB" value="%{LIC_AMT_OB}" id="LIC_AMT_OB%{#ctn}"/>  
                                                             <s:hidden name="LIC_AMT_ISSUE" value="%{LIC_AMT_ISSUE}" id="LIC_AMT_ISSUE%{#ctn}"/> 
                                                             <s:hidden name="LIC_QTY_OB" value="%{LIC_QTY_OB}" id="LIC_QTY_OB%{#ctn}"/> 
                                                             <s:hidden name="LIC_QTY_ISSUE" value="%{LIC_QTY_ISSUE}" id="LIC_QTY_ISSUE%{#ctn}"/> 
                                                     
                                                             <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                           
                                                        </tr>
                                                    </s:iterator>
                                                </tbody>
                                                
                                             
                                                
                                         
                                      </s:if>   
                                   
                                           <s:iterator begin="%{#ctn}" end="5" status="st">
                                               <tr bgcolor="#f2f9fb">
                                                     <td style="width:12px" class="label-1" >	
                                                                    <input type="button" class="texts"  tabindex="-1" style="font-size: 10px;margin: 0px;padding: 0px" name="btn" onclick="deletetablerow('<s:property value="%{#ctn}"/>')" value="X">
                                                     </td>
                                                    <td class="label-1">
                                                                   <s:textfield name="REF_NO" value="%{REF_NO}" readonly="true" theme="simple" cssClass="texts" id="REF_NO%{#ctn}" cssStyle="width:80px"/>
                                                                   <a href="#" target="handlefrm"  id="licref<s:property value="%{#ctn}"/>" onclick="calllic('licref<s:property value="%{#ctn}"/>','<s:property value="%{#ctn}"/>');openpop('root')">
                                                                        <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                   </a>
                                                    </td>  
                                                    <td class="label-1">
                                                         <s:textfield name="REF_TYPE" value="%{REF_TYPE}" readonly="true" theme="simple" cssClass="texts" id="REF_TYPE%{#ctn}" cssStyle="width:50px"/>
                                                    </td> 
                                                    <td class="label-1" align:right><s:textfield name="QTY"  id="QTY%{#ctn}" cssStyle="width:60px;text-align:right"  cssClass="texts" theme="simple"  onblur="validatenumber1(this,%{#ctn});"/> </td>
                                                     <td class="label-1" align:right><s:textfield name="ADJS_QTY"  id="ADJS_QTY%{#ctn}" cssStyle="width:60px;text-align:right"  cssClass="texts" theme="simple"/> </td>
                                                    <td class="label-1" align:right><s:textfield name="FOB_AMT"   id="FOB_AMT%{#ctn}"   cssStyle="width:60px;text-align:right"  cssClass="texts" theme="simple"/> </td>
                                                    <td class="label-1" align:right><s:textfield name="IO_NORMS"  id="IO_NORMS%{#ctn}"  cssStyle="width:60px;text-align:right"  cssClass="texts" theme="simple" /> </td>
                                                    <td class="label-1" align:right><s:textfield name="QTY_SQM"  id="QTY_SQM%{#ctn}" cssStyle="width:60px;text-align:right"  cssClass="texts" theme="simple" onblur="validatenumber1(this,%{#ctn});"/> </td>
                                                   
                                                    <td style="font-size:8pt" > 
                                                                  <s:textfield name="BE_NO" readonly="true" id="BE_NO%{#ctn}" cssStyle="width:60px"  value="%{BE_NO}" cssClass="texts" theme="simple"/>
                                                                    
                                                                    <a href="#" target="handlefrm"  id="beref<s:property value="%{#ctn}"/>" onclick="callBE('beref<s:property value="%{#ctn}"/>','<s:property value="%{#ctn}"/>');openpop('root')">
                                                                        <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                   </a>
                                                    </td> 
                                                    <td style="font-size:8pt"> 
                                                                  <s:textfield name="BE_DESC" readonly="true" id="BE_DESC%{#ctn}" cssStyle="width:400px"  value="%{BE_DESC}" cssClass="texts" theme="simple" />
                                                    </td>
                                                    <td class="label-1">
                                                          <s:textfield name="LITEM_DESC" value="%{LITEM_DESC}" readonly="true" theme="simple" cssClass="texts" id="LITEM_DESC%{#ctn}" cssStyle="width:300px"/>
                                                    </td>
                                                     
                                                      <s:hidden name="LIC_DATE" value="%{LIC_DATE}" id="LIC_DATE%{#ctn}"/>
                                                      <s:hidden name="LIC_COMP" value="%{LIC_COMP}" id="LIC_COMP%{#ctn}"/>
                                                      <s:hidden name="LIC_LOCT" value="%{LIC_LOCT}" id="LIC_LOCT%{#ctn}"/>
                                                      <s:hidden name="EXP_CTRL_NO" value="%{EXP_CTRL_NO}" id="EXP_CTRL_NO%{#ctn}"/>    
                                                      <s:hidden name="IMP_CTRL_NO" value="%{IMP_CTRL_NO}" id="IMP_CTRL_NO%{#ctn}"/>  
                                                       <s:hidden name="LIC_AMT_OB" value="%{LIC_AMT_OB}" id="LIC_AMT_OB%{#ctn}"/>  
                                                      <s:hidden name="LIC_AMT_ISSUE" value="%{LIC_AMT_ISSUE}" id="LIC_AMT_ISSUE%{#ctn}"/> 
                                                      <s:hidden name="LIC_QTY_OB" value="%{LIC_QTY_OB}" id="LIC_QTY_OB%{#ctn}"/> 
                                                      <s:hidden name="LIC_QTY_ISSUE" value="%{LIC_QTY_ISSUE}" id="LIC_QTY_ISSUE%{#ctn}"/> 
                                                      
                                              </tr>
                                                 <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                          </s:iterator>
                                        
                                         <tr bgcolor="#f2f9fb">
                                                    <td></td> 
                                                    <td style="font-size:8pt" align="right"></td>
                                                    <td style="font-size:8pt" align="right" class="label-1">Total</td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_QTY}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_INVQTY}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_FOB}"/></td>
                                                    <td style="font-size:8pt" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_SQM}"/></td>
                                                    <td style="font-size:8pt"  ></td>
                                                    <td style="font-size:8pt" ></td>
                                                    <td style="font-size:8pt" ></td>
                                                
                                                </tr>
                                         
                                  </table>
                            </div>                                                 
                                 
    </body>
</html> 
 