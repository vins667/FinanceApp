<%-- 
    Document   : InvLineDetails
    Created on : Mar 19, 2015, 10:50:59 AM
    Author     : Sanjeev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<script src="js/mvxexp.js"></script> 

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
        
        <table>
            <tr>
                <td style="font-size:8pt" align="left" height="25pt" class="label-1">Catg/Desc</td>
                                                    <td style="font-size:8pt" align="left"  height="25pt">
                                                                                                              
                                                         <s:textfield name="CATG_CODE_COPY" id="CATG_CODE_COPY" 
                                                                      value="%{CATG_CODE_COPY}"
                                                                              theme="simple" cssClass="texts"
                                                                            onblur="if(this.value!='') return xmlhttpreqcatg(this,'CATG_DESC_COPY')"
                                                                                 tabindex="1"
                                                                               cssStyle="width:30px"/>
                                                         <a href="#" target="handlefrm"  id="catghref" onclick="callcatg('catghref','CATG_CODE_COPY','CATG_DESC_COPY');openpop('root')"  >
                                                                 <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                               </a>
                                                        
                                                    </td>
                                                    
                                                    <td style="font-size:8pt" align="left"><s:textfield name="CATG_DESC_COPY" id="CATG_DESC_COPY" 
                                                                      value="%{CATG_DESC_COPY}"
                                                                              theme="simple" cssClass="texts" 
                                                                              cssStyle="width:300px;text-transform:uppercase" tabindex="2"/>
                                                    </td>
                                                    <td class="label-1">
                                                        ShipType
                                                    </td>
                                                     <td style="font-size:8pt"><s:select name="SHIP_TYPE_COPY" value="%{SHIP_TYPE_COPY}"
                                                                      list="%{SHIP_TYPE_LIST}"
                                                                      theme="simple"  cssStyle="width:60px"
                                                                      headerKey=""
                                                                      cssClass="texts"
                                                                      headerValue="Type" tabindex="3"
                                                                      /></td>
                                                     <td class="label-1">
                                                         DBK #
                                                     </td>
                                                            <td style="font-size:8pt">
                                                              
                                                                 <s:textfield name="DBK_SLNO_COPY"  value="%{DBK_SLNO_COPY}"
                                                                              theme="simple" cssClass="texts"
                                                                              cssStyle="width:60px"
                                                                              onblur="if(this.value!='') return xmlhttpreqdbkslno(this,'DBK_SLNO_COPY')" tabindex="4"/>
                                                                 
                                                                    <a href="#" target="handlefrm"  id="dbkslhref" onclick="calldbk('dbkslhref','DBK_SLNO_COPY');openpop('root')">
                                                                        <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                   </a>
                                                            </td>
                                                             <td class="label-1">
                                                               ROSL# 
                                                         </td>
                                                            <td style="font-size:8pt">
                                                              
                                                                 <s:textfield name="ROSL_SLNO_COPY"  id="ROSL_SLNO_COPY" value="%{ROSL_SLNO_COPY}"
                                                                              theme="simple" cssClass="texts"
                                                                              cssStyle="width:60px" 
                                                                              onblur="if(this.value!='') return xmlhttpreqroslslno(this,'ROSL_SLNO_COPY')" tabindex="4"/>
                                                                 
                                                                    <a href="#" target="handlefrm"  id="roslslhref" onclick="callrosl('roslslhref','ROSL_SLNO_COPY');openpop('root')">
                                                                        <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                   </a>
                                                            </td>
                                                            <td class="label-1">STR#</td>
                                                            <td style="font-size:8pt">
                                                                
                                                                 <s:textfield name="STR_SLNO_COPY"  value="%{STR_SLNO_COPY}"
                                                                              theme="simple" cssClass="texts"
                                                                              cssStyle="width:40px;text-transform:uppercase"
                                                                              onblur="if(this.value!='') return xmlhttpreqstrslno(this,'STR_SLNO_COPY')" tabindex="5" />
                                                                  <a href="#" target="handlefrm"  id="strslhref" onclick="callstr('strslhref','STR_SLNO_COPY');openpop('root')">
                                                                        <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                   </a>
                                                            </td>  
                                                            <td class="label-1">Hngr Str</td>
                                                             <td style="font-size:8pt">
                                                                 
                                                                 <s:textfield name="STR_MISC_COPY" value="%{STR_MISC_COPY}"
                                                                              theme="simple" cssClass="texts"
                                                                              cssStyle="width:40px"
                                                                             onblur="if(this.value!='') return xmlhttpreqstrmisc(this,'STR_MISC_COPY')" tabindex="6"/>
                                                                 
                                                                 <a href="#" target="handlefrm"  id="strmischref" onclick="callstrmisc('strmischref','STR_MISC_COPY');openpop('root')">
                                                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                 </a>
                                                            </td> 
                                                         <td class="label-1">
                                                                 Pr Misc 
                                                          </td>
                                                            <td style="font-size:8pt"> 
                                                              
                                                                 <s:textfield name="PRICE_MISC_COPY" id="PRICE_MISC_COPY" value="0.0"
                                                                              theme="simple" cssClass="texts" onblur="validatenumber(this)"
                                                                              cssStyle="width:40px" tabindex="6"/>
                                                             </td>
                                                             <td> 
                                                                 <input type="button" name="btn" onclick="copyCatg()" value="CP All" />
                                                             </td>
                                                             <td style="font-size:8pt" class="label-1"> Adjst FC
                                                                  <s:textfield name="ADJUST_FC_COPY" id="ADJUST_FC_COPY" value="0.0"
                                                                              theme="simple" cssClass="texts" onblur="validatenumber(this)"
                                                                              cssStyle="width:40px" />
                                                             </td>
                                                </tr> 
                                            </table>                         
                                        <div class="div1" style="width:100.0%; overflow:auto; height:320.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            <table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                
                                                <tr class="tr1">
                                                    <th style="font-size:8pt" align="left" height="25pt">Sl#</th>
                                                    <th style="font-size:8pt" align="left" height="25pt">Item No.</th> 
                                                    <th style="font-size:8pt" align="left">Unit</th>
                                                    <th style="font-size:8pt" align="right">Inv Qty</th>
                                                    <th style="font-size:8pt" align="right">Qty Kgs</th>
                                                    <th style="font-size:8pt" align="right">Movex Price</th>
                                                     <th style="font-size:8pt" align="right">Price FC</th>
                                                    <th style="font-size:8pt" align="right">Price Misc</th>
                                                    <th style="font-size:8pt" align="right">Adjust Fc</th>
                                                    <th style="font-size:8pt" align="right">Net Price</th>
                                                    <th style="font-size:8pt" align="right">Fob Fc</th>
                                                    <th style="font-size:8pt" align="right">Gr Decl </th>
                                                    <th style="font-size:8pt" align="left">ShipType</th>
                                                    <th style="font-size:8pt" align="left">Dbk Sl#</th>
                                                    <th style="font-size:8pt" align="left">Rosl Sl#</th>
                                                    <th style="font-size:8pt" align="left">Str#</th>
                                                    <th style="font-size:8pt" align="left">MiscStr</th>
                                                    <th style="font-size:8pt" align="left">Catg</th>
                                                    <th style="font-size:8pt" align="left">CP</th> 
                                                    <th style="font-size:8pt" align="left">Invoice Desc</th>
                                                    <th style="font-size:8pt" align="left">Plan Desc</th>
                                                    
                                                </tr>                                                
                                                <tbody>
                                                    <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="0"/>
                                                    <s:set id="TOT_QTY_KGS" name="TOT_QTY_KGS" value="0"/>                                                    
                                                    <s:set id="TOT_FOB_FC" name="TOT_FOB_FC" value="0"/>
                                                    <s:set id="TOT_GR_DECL_AMT" name="TOT_GR_DECL_AMT" value="0"/>
                                                     <s:hidden name="YEAR" value="%{YEAR}"/>
                                                    <s:hidden name="COMPANY" value="%{COMPANY}"/>
                                                    <s:hidden name="INV_NO" value="%{INV_NO}"/>
                                                     
                                                    <s:iterator value="InvLineList" status="listid">
                                                   
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property  value="%{#listid.index+1}"/></td>
                                                             <td style="font-size:8pt;width: 40px"><s:textfield  name="item" theme="simple"   cssClass="textreadonly" cssStyle="width:40pt;font-size:9pt;" value="%{ITEM_NO}"/></td>
                                                             
                                                                <s:hidden name="CO_NO_E" value="%{CO_NO}"/>
                                                                <s:hidden name="SR_NO" value="%{SR_NO}"/>
                                                                <s:hidden name="CO_LINE_E" value="%{CO_LINE}"/>
                                                                <s:hidden name="ITEM_NO_E" value="%{ITEM_NO}"/>
                                                                <s:hidden name="QTY_ENDORS_E" value="%{QTY_ENDORS}"/>
                                                                <s:hidden name="CO_UOM_E" value="%{UNIT}"/> 
                                                                <s:hidden name="PRE_PRINT_NO" value="%{PRE_PRINT_NO}"/> 
                                                                <s:hidden name="TOKEN_NO" value="%{TOKEN_NO}"/>
                                                                <s:hidden name="GR_DECL_PER" value="%{GR_DECL_PER}"/>
                                                                <s:hidden name="TEMP_CAT" value="%{TEMP_CAT}"/>
                                                                
                                                                 
                                                              <td style="font-size:8pt;width: 30px"><s:property value="UNIT"/></td>
                                                              <td style="font-size:8pt;width: 40px;" align="right">
                                                                <s:property value="QTY_ENDORS" />
                                                                <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="%{#TOT_QTY_ENDORS+QTY_ENDORS}"/>
                                                                 
                                                            </td> 
                                                            <td style="font-size:8pt;width: 40px" align="right">
                                                                <s:textfield name="QTY_KGS"  value="%{QTY_KGS}" theme="simple" cssStyle="width:40px;text-align:right" cssClass="texts" id="QTY_KGS%{#listid.index}"/>
                                                                <s:set id="TOT_QTY_KGS" name="TOT_QTY_KGS" value="%{#TOT_QTY_KGS+QTY_KGS}"/>
                                                            </td>
                                                            <td style="font-size:8pt;width: 40px" align="right">
                                                                <s:property value="MOVEX_PRICE"/>  
                                                                <s:hidden name="PRICE_FC_MOVEX" id="PRICE_FC_MOVEX%{#listid.index}" value="%{MOVEX_PRICE}"/>
                                                            </td>  
                                                             <td style="font-size:8pt;width:40px" align="right">
                                                                 <s:if test="%{PRICE_FC_E==null}">
                                                                       <s:textfield name="PRICE_FC_E" value="%{PRICE_FC}"
                                                                              theme="simple" cssClass="texts"
                                                                              id="PRICE_FC_E%{#listid.index}"
                                                                              onblur="validatenumber(this)"
                                                                              cssStyle="width:40px;text-align:right"/>
                                                                 </s:if>
                                                                 <s:else>    
                                                                    
                                                                      <s:textfield name="PRICE_FC_E" value="%{PRICE_FC_E}"
                                                                              theme="simple" cssClass="texts"
                                                                              id="PRICE_FC_E%{#listid.index}"
                                                                               onblur="validatenumber(this)"
                                                                              cssStyle="width:40px;text-align:right"/> 
                                                                 </s:else>
                                                                 
                                                             </td>
                                                            <td style="font-size:8pt;width: 40px" align="right">
                                                                <s:textfield name="PRICE_MISC" value="%{PRICE_MISC}"
                                                                              theme="simple" cssClass="texts"
                                                                               id="PRICE_MISC%{#listid.index}"
                                                                               onblur="validatenumber(this)"
                                                                              cssStyle="width:40px;text-align:right"/>
                                                                                                                               
                                                            </td>
                                                            <td style="font-size:8pt;width: 40px" align="right">
                                                                 <s:textfield name="ADJUST_FC" id="ADJUST_FC%{#listid.index}" value="%{ADJUST_FC}"
                                                                              theme="simple" cssClass="texts"
                                                                              cssStyle="width:40px;text-align:right"/>
                                                                                                                               
                                                            </td>
                                                            <td style="font-size:8pt;width: 40px" align="right">
                                                                
                                                                <s:property value="NET_PRICE"/>     
                                                                <s:hidden name="NET_PRICE_E" value="%{NET_PRICE}"/>
                                                            </td>
                                                            <td style="font-size:8pt;width: 60px" align="right">
                                                                <s:textfield name="FOB_FC" value="%{FOB_FC}"
                                                                              theme="simple" cssClass="texts"
                                                                              cssStyle="width:60px;text-align:right"/>
                                                              
                                                                <s:set id="TOT_FOB_FC" name="TOT_FOB_FC" value="%{#TOT_FOB_FC+FOB_FC}"/>
                                                            </td>
                                                            <td style="font-size:8pt;width: 50px" align="right">
                                                                <s:property value="GR_DECL_AMT"/>
                                                                <s:hidden name="GR_DECL_AMT_E" value="%{GR_DECL_AMT}"/>
                                                                <s:set id="TOT_GR_DECL_AMT" name="TOT_GR_DECL_AMT" value="%{#TOT_GR_DECL_AMT+GR_DECL_AMT}"/>
                                                            </td> 
                                                            <td style="font-size:8pt;width: 30px"><s:select name="MADE_FOR" value="%{MADE_FOR}"
                                                                      list="%{SHIP_TYPE_LIST}"
                                                                      theme="simple"
                                                                      headerKey=""
                                                                      cssClass="texts" 
                                                                      headerValue="Type" cssStyle="width:50px"
                                                                      /> 
                                                            </td> 
                                                            <td style="font-size:8pt;width: 80px" >
                                                                 <s:textfield name="DBK_SLNO" value="%{DBK_SLNO}" 
                                                                              theme="simple" cssClass="texts"
                                                                              id="DBK_SLNO%{#listid.index}" readonly="true"
                                                                              cssStyle="width:60px"/>
                                                                               
                                                                  <a href="#" target="handlefrm"  id="dbkslhref<s:property value="%{#listid.index}"/>" onclick="calldbk('dbkslhref<s:property value="%{#listid.index}"/>','DBK_SLNO<s:property value="%{#listid.index}"/>');openpop('root')">
                                                                     <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                  </a>
                                                            </td>
                                                             <td style="font-size:8pt;width: 80px" >
                                                                 <s:textfield name="ROSL_SLNO" value="%{ROSL_SLNO}" 
                                                                              theme="simple" cssClass="texts"
                                                                              id="ROSL_SLNO%{#listid.index}" readonly="true"
                                                                              cssStyle="width:60px"/>
                                                                               
                                                                  <a href="#" target="handlefrm"  id="roslslhref<s:property value="%{#listid.index}"/>" onclick="callrosl('roslslhref<s:property value="%{#listid.index}"/>','ROSL_SLNO<s:property value="%{#listid.index}"/>');openpop('root')">
                                                                     <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                  </a>
                                                            </td>
                                                            <td style="font-size:8pt;width: 40px">
                                                                
                                                                 <s:textfield name="STR_SLNO" value="%{STR_SLNO}" readonly="true"
                                                                              theme="simple" cssClass="texts"
                                                                              id="STR_SLNO%{#listid.index}"
                                                                              cssStyle="width:20px"/>
                                                                   <a href="#" target="handlefrm"  id="strslhref<s:property value="%{#listid.index}"/>" onclick="callstr('strslhref<s:property value="%{#listid.index}"/>','STR_SLNO<s:property value="%{#listid.index}"/>');openpop('root')">
                                                                     <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                  </a>  
                                                            </td> 
                                                             <td style="font-size:8pt;width: 60px">
                                                                
                                                                 <s:textfield name="STR_MISC" value="%{STR_MISC}"
                                                                              theme="simple" cssClass="texts"
                                                                              id="STR_MISC%{#listid.index}"
                                                                              cssStyle="width:40px"/>
                                                                   <a href="#" target="handlefrm"  id="strmischref<s:property value="%{#listid.index}"/>" onclick="callstrmisc('strmischref<s:property value="%{#listid.index}"/>','STR_MISC<s:property value="%{#listid.index}"/>');openpop('root')">
                                                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                                   </a>     
                                                            </td>  
                                                            <td style="font-size:8pt;width:40px">
                                                                 <s:textfield name="CATG_CODE" value="%{CATG_CODE}"
                                                                              theme="simple" cssClass="texts"
                                                                              id="CATG_CODE%{#listid.index}"
                                                                              cssStyle="width:20px"/>
                                                                 <a href="#" target="handlefrm"  id="catghref<s:property value="%{#listid.index}"/>" onclick="callcatg('catghref<s:property value="%{#listid.index}"/>','CATG_CODE<s:property value="%{#listid.index}"/>','INV_DESC<s:property value="%{#listid.index}"/>');openpop('root')">
                                                                      <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                                                               </a>
                                                            </td>
                                                            <td style="font-size:8pt;width:20px"><input type="checkbox" value="Y" name="cpselect"   onclick="copySelected(<s:property value="%{#listid.index}"/>)" /></td>
                                                            <td style="width:250px">
                                                                    <s:textfield name="INV_DESC" value="%{INV_DESC}"
                                                                               theme="simple" cssClass="texts"
                                                                               id="INV_DESC%{#listid.index}"
                                                                               cssStyle="width:250px;text-transform:uppercase"/>
                                                            </td>
                                                            <td style="width:110px">
                                                                    <s:textfield name="PLAN_DESC" value="%{PLAN_DESC}"
                                                                               theme="simple" cssClass="texts"
                                                                               id="INV_DESC%{#listid.index}"
                                                                               cssStyle="width:110px;text-transform:uppercase"/>
                                                            </td>
                                                        </tr>
                                                    </s:iterator> 
                                                         
                                                </tbody>
                                                <tr bgcolor="#f2f9fb">
                                                    <td style="font-size:8pt" colspan="3" align="right" class="label-1">Total</td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;width: 40px;" align="right"><s:property value="%{#TOT_QTY_ENDORS}"/>
                                                    <s:hidden name="QTYTOTAL" id="QTYTOTAL" value="%{#TOT_QTY_ENDORS}"/>
                                                    </td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_QTY_KGS}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
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
                                                    <td style="font-size:7pt;color:#9400D3;font-weight: bold;width: 60px" align="right"><s:property value="%{#fcvalue}"/>
                                                        <s:hidden name="FCTOTAL" id="FCTOTAL" value="%{#fcvalue}"/>
                                                    
                                                    </td>
                                                    <td style="font-size:7pt;color:#9400D3;font-weight: bold;width: 50px" align="right"><s:property value="%{#grdecl}"/></td>
                                                    <td style="font-size:8pt"></td>
                                                    <td style="font-size:8pt" align="right"></td>
                                                    <td style="font-size:8pt"></td>
                                                    <td style="font-size:8pt"></td>
                                                    <td style="font-size:8pt"></td>
                                                    <td style="font-size:8pt"></td> 
                                                     <td style="font-size:8pt"></td> 
                                                      <td style="font-size:8pt"></td> 
                                                       <td style="font-size:8pt"></td> 
                                                </tr>
                                            </table>
                                        </div>                                                 
                                 
    </body>
</html>
