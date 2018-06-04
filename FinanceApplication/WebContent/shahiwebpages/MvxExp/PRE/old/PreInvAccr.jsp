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
        
       <table border="0" align="center" bgcolor="#f2f2f2" >
                                          
            <tr>
                    <td class="label-1">Accr Type</td>
                    <td style="font-size:8pt"><s:select name="ACCRTYPE" id="ACCRTYPE" value="%{ACCR_TYPE_LIST}" list="%{ACCR_TYPE_LIST}" theme="simple" headerKey="" cssClass="texts"  cssStyle="width:100px" headerValue="Type" /></td>
                    <td class="label-1">Dbk SlNo</td>
                    <td style="font-size:8pt">
                        <s:textfield name="DBK_ACCR_COPY"  id="DBK_ACCR_COPY"  value="%{DBK_ACCR_COPY}" theme="simple" cssClass="texts" cssStyle="width:80px"/>
                      <a href="#" target="handlefrm"  id="dbkaccrhref" onclick="calldbk('dbkaccrhref','DBK_ACCR_COPY','DBK_ACCR_COPY');openpop('root')">
                           <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                     </a>
                    </td>
                    <td class="label-1">Str SlNo</td>
                    <td style="font-size:8pt">
                            <s:textfield name="STR_ACCR_COPY" id="STR_ACCR_COPY" value="%{STR_ACCR_COPY}" theme="simple" cssClass="texts" cssStyle="width:80px"/>
                           <a href="#" target="handlefrm"  id="straccrhref" onclick="callstrmisc('straccrhref','STR_ACCR_COPY');openpop('root')">
                                <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/>
                           </a>
                           </td>
                   <td class="label-1">Accr Price</td>
                   <td style="font-size:8pt"><s:textfield name="ACCR_RATE_COPY" id="ACCR_RATE_COPY" theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>
                   
                   <td align="center">
                       <input type="button" name="btn" onclick="addsubtitleq1('tableaaccr')" value="Copy"/>
                   </td>
                   </tr> 
                   </table>                         
                   <div class="div1" style="width:100.0%; overflow:auto; height:300.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                       <table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="50%"  id="tableaaccr">
                                                
                                              <tr class="tr1">
                                                    <th style="font-size:8pt" align="left" height="25pt">CO No</th>
                                                    <th style="font-size:8pt" align="left"  height="25pt">Line</th>
                                                    <th style="font-size:8pt" align="left">Item No</th>
                                                    <th style="font-size:8pt" align="left">Accr Desc</th>
                                                    <th style="font-size:8pt" align="left">Dbk Sl#</th>
                                                    <th style="font-size:8pt" align="left">Str Sl#</th>
                                                    <th style="font-size:8pt" align="right">Qnty</th>
                                                    <th style="font-size:8pt" align="right">Price</th>
                                                    <th></th>
                                                </tr>                                                
                                                <tbody>
                                                    <s:set id="ctn" name="ctn" value="0"/>
                                                    <s:iterator value="AccrLineList" status="listid">
                                                        <tr id="<s:property value="%{#ctn}"/>" bgcolor="#FFFFFF">
                                                                <td style="font-size:8pt"><s:textfield name="ACCR_CO_NO" readonly="true"  value="%{ACCR_CO_NO}"  id="ACCR_CO_NO%{#ctn}" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>
                                                                <td style="font-size:8pt"><s:textfield name="ACCR_CO_LINE" readonly="true" value="%{ACCR_CO_LINE}" id="ACCR_CO_LINE%{#ctn}" theme="simple" cssClass="texts" cssStyle="width:60px"/></td>
                                                                <td style="font-size:8pt"><s:textfield name="ACCR_ITEM_NO" readonly="true" value="%{ACCR_ITEM_NO}" id="ACCR_ITEM_NO%{#ctn}" theme="simple" cssClass="texts" cssStyle="width:80px"/></td>
                                                                <td style="font-size:8pt"><s:textfield name="ACCR_TYPE" readonly="true" value="%{ACCR_TYPE}" id="ACCR_TYPE%{#ctn}" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>
                                                                <td style="font-size:8pt"><s:textfield name="ACCR_DBKSLNO"   value="%{ACCR_DBKSLNO}" id="ACCR_DBKSLNO%{#ctn}" theme="simple" cssClass="texts" cssStyle="width:80px"/></td>
                                                                <td style="font-size:8pt"><s:textfield name="ACCR_STRSLNO"   value="%{ACCR_STRSLNO}" id="ACCR_STRSLNO%{#ctn}" theme="simple" cssClass="texts" cssStyle="width:80px"/></td>
                                                                <td style="font-size:8pt" align="right">
                                                                    <s:textfield name="ACCR_QTY" readonly="true" value="%{ACCR_QTY}" theme="simple" cssClass="texts" id="ACCR_QTY%{#ctn}" cssStyle="width:60px;text-align:right"/>
                                                                </td>
                                                                <td style="font-size:8pt" align="right">
                                                                    <s:textfield name="ACCR_PRICE" readonly="true" value="%{ACCR_PRICE}" theme="simple" cssClass="texts" id="ACCR_PRICE%{#ctn}" cssStyle="width:60px;text-align:right"/>
                                                                </td>
                                                                   
                                                                  <td style="width:12px" class="label-1">	
                                                                    <input type="button" id="<s:property value="%{#ctn}"/>" class="texts"  tabindex="-1" style="font-size: 10px;margin: 0px;padding: 0px" name="btn" onclick="deletetablerowaccr(this.id)" value="X">
                                                                  
                                                                   
                                                                  </td> 
                                                       
                                                             <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                             
                                                    </s:iterator>
                                                           
                                                                  <s:hidden  id="accrctn"  name="accrctn" value="%{#ctn}"/>
                                                         
                                                </tbody>

                                            </table>
                                 </div>                                                 
                                 
    </body>
</html>
