<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
     

</head>
<body style="margin: 0px;padding: 0px" onload="">
    <s:set id="saveamt" value="0.0"/>
 
    <div style="height: 105px;margin: 0px;overflow: auto;padding: 0px" >
    <table cellpadding="0" border="0" cellspacing="0">
        <s:iterator value="saveprod" status="saveprst">
            <tr><td>
                     <s:set id="saveamt" value="%{#saveamt+PRODUCT_AMOUNT}"/>
                    <s:hidden name="PRODUCT_MAST_SL_NO"  value="%{SL_NO}"/>
                    <s:select id="UP_PRODUCT_SL_NO" onchange="addheadinv('invdtid%{#saveprst.index}td',this,'%{#saveprst.index}td')" value="%{PRODUCT_SL_NO}" theme="simple" name="UP_PRODUCT_SL_NO" listKey="SL_NO" cssStyle="width:300px"  cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="productlist"/>
            </td><td> 
                &nbsp;
                 <s:text name="product.req" id="PRODUCT_AMOUNTdec" >
                <s:param name="value" value="%{PRODUCT_AMOUNT}"/> 
                 </s:text>
                <s:textfield name="UP_PRODUCT_AMOUNT" id="UP_PRODUCT_AMOUNT" onblur="validatenumber(this);totalamount()" onkeyup="totalamount()" value="%{#PRODUCT_AMOUNTdec}" cssStyle="width:80px" theme="simple" cssClass="texts"/>
              </td>
              <td>
                  <s:if test="%{TAXABLEDESC=='Yes'}">
                      <input type="checkbox" checked="true" name="CHK" onclick="chkflagnew(this,'UP_CHKINPUT<s:property value="%{#saveprst.index}"/>')" value="Y"/>
                  <s:hidden name="UP_CHKINPUT" id="UP_CHKINPUT%{#saveprst.index}" value="Y"/>
                
                  </s:if>
                  <s:else>
                      <input type="checkbox" name="CHK" onclick="chkflagnew(this,'UP_CHKINPUT<s:property value="%{#saveprst.index}"/>')" value="Y"/>
                  <s:hidden name="UP_CHKINPUT" id="UP_CHKINPUT%{#saveprst.index}" value="N"/>
                
                  </s:else>
                    
              </td>
               <td>
                  
                  <a href="#" onclick='document.getElementById("<s:property value="%{#saveprst.index}"/>td_popup").style.display="block"' style="text-decoration: none">
                  &nbsp; <s:if test="%{REMARKS==null}">
                                                    <img style="border: 0px;" src="image/icon_comment.gif" alt="Remarks"/>
                                                 </s:if>
                                                    <s:else>
                                                         <img style="border: 0px;" src="image/comment-18.gif" alt="Remarks"/>
                                                 </s:else>
                      </a>
                                                           
              </td>
              <%--
             <td> 
                    <a href="#" onclick='document.getElementById("invdtid<s:property value="%{#saveprst.index}"/>").style.display="block"' style="text-decoration: none">
                  &nbsp; <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Detail"/>
                      </a> 
             
              
              </td>--%>
            </tr>
        </s:iterator>
        <s:if test="%{saveprod.size()==0}">
            <s:if test="%{productlist.size()<6}">
        <s:iterator value="%{productlist}"   status="sss">
        <tr><td>
                              
                <s:select id="PRODUCT_SL_NO%{#sss.index}" value="" onchange="addheadinv('invdtid%{#sss.index}td',this)" theme="simple" name="PRODUCT_SL_NO" listKey="SL_NO" cssStyle="width:300px" headerKey="" headerValue="Select product" cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="productlist"/>
            </td><td> 
                &nbsp;
                <s:textfield name="PRODUCT_AMOUNT" id="PRODUCT_AMOUNT%{#sss.index}" onblur="validatenumber(this);totalamount()" onkeyup="totalamount()" value="" cssStyle="width:80px" theme="simple" cssClass="texts"/>
              </td>
              <td>
                  <input type="checkbox" name="CHK" checked="true" onclick="chkflagnew(this,'CHKINPUT<s:property value="%{#sss.index}"/>')" value="Y"/>
                  <s:hidden name="CHKINPUT" id="CHKINPUT%{#sss.index}" value="Y"/>
              </td>
                <td>
                  
                  <a href="#" onclick='openpopinv("<s:property value="%{#sss.index}"/>td_popup","PRODUCT_SL_NO<s:property value="%{#sss.index}"/>","PRODUCT_AMOUNT<s:property value="%{#sss.index}"/>")' style="text-decoration: none">
                  &nbsp; <img style="border: 0px" src="image/icon_comment.gif" alt="Remarks"/>
                      </a>
                                                           
              </td>
              <%--
             <td> 
                   <a href="#" onclick='openpopinv("invdtid<s:property value="%{#sss.index}"/>","PRODUCT_SL_NO<s:property value="%{#sss.index}"/>","PRODUCT_AMOUNT<s:property value="%{#sss.index}"/>")' style="text-decoration: none">
                  &nbsp; <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Detail"/>
                      </a> 
              
                
              
              
              </td>--%>
        </tr>
            </s:iterator>  
            </s:if>
            <s:else>
             <s:iterator begin="0" end="4"    status="sss1">
        <tr><td>
                              
                <s:select id="PRODUCT_SL_NO%{#sss1.index}" value="" theme="simple" onchange="addheadinv('invdtid%{#sss1.index}td',this,'%{#sss1.index}td');" name="PRODUCT_SL_NO" listKey="SL_NO" cssStyle="width:300px" headerKey="" headerValue="Select product" cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="productlist"/>
            </td><td> 
                &nbsp;
                <s:textfield name="PRODUCT_AMOUNT" id="PRODUCT_AMOUNT%{#sss1.index}" onblur="validatenumber(this);totalamount()" onkeyup="totalamount()" value="" cssStyle="width:80px" theme="simple" cssClass="texts"/>
              </td>
        <td>
                  <input type="checkbox" name="CHK" checked="true" onclick="chkflagnew(this,'CHKINPUT<s:property value="%{#sss1.index}"/>')" value="Y"/>
                  <s:hidden name="CHKINPUT" id="CHKINPUT%{#sss1.index}" value="Y"/>
              </td>
              <td>
                  
                  <a href="#" onclick='openpopinv("<s:property value="%{#sss1.index}"/>td_popup","PRODUCT_SL_NO<s:property value="%{#sss1.index}"/>","PRODUCT_AMOUNT<s:property value="%{#sss1.index}"/>")' style="text-decoration: none">
                  &nbsp; <img style="border: 0px" src="image/icon_comment.gif" alt="Remarks"/>
                      </a>
                                                           
              </td>
              <%--
              <td> 
                  <a href="#" onclick='openpopinv("invdtid<s:property value="%{#sss1.index}"/>","PRODUCT_SL_NO<s:property value="%{#sss1.index}"/>","PRODUCT_AMOUNT<s:property value="%{#sss1.index}"/>")' style="text-decoration: none">
                  &nbsp; <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Detail"/>
                      </a> 
              
                
              
              
              </td>
        --%>
        </tr>
            </s:iterator>  
            </s:else>
        </s:if>
        
         <s:if test="%{saveprod.size()==0 && productlist.size()==0}">
             <s:iterator begin="0" end="4"    status="sss1">
        <tr><td>
                              
                <s:select id="PRODUCT_SL_NO" value="%{SL_NO}" onchange="addheadinv('invdtid%{#sss1.index}td',this)" theme="simple" name="PRODUCT_SL_NO" listKey="SL_NO" cssStyle="width:300px" headerKey="" headerValue="Select product" cssClass="selecttext" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" list="productlist"/>
            </td><td> 
                &nbsp;
                
                <s:textfield name="PRODUCT_AMOUNT" id="PRODUCT_AMOUNT" value="" onblur="validatenumber(this);totalamount()" onkeyup="totalamount()" cssStyle="width:80px" theme="simple" cssClass="texts"/>
              </td>
              <td>
                  <input type="checkbox" name="CHK" checked="true" onclick="chkflagnew(this,'CHKINPUT<s:property value="%{#sss1.index}"/>')" value="Y"/>
                  <s:hidden name="CHKINPUT" id="CHKINPUT%{#sss1.index}" value="Y"/>
              </td>
             <td> 
                 <%--
                  <a href="#" onclick='document.getElementById("invdtid<s:property value="%{#sss1.index}"/>").style.display="block"' style="text-decoration: none">
                  &nbsp; <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Break-Up"/>
                      </a> 
              
                --%>
              
              
              </td>
        </tr>
            </s:iterator>    
        </s:if>
    </table>
        </div>
    <table style="width:390px" border="0" cellpadding="0px" cellspacing="0px"><tr>
            <td class="label-1">Total</td><td align="right" > 
                <s:text name="product.req" id="saveamtdec" >
                <s:param name="value" value="%{#saveamt}"/> 
                 </s:text>
                <s:textfield name="TOTAL" theme="simple" value="%{#saveamtdec}" id="TOTAL" readonly="true" cssClass="textreadonly" cssStyle="width:80px"/>
                                   </td>
        </tr></table>
                                   
           
                <s:hidden name="UPTOTAL"  value="%{#saveamt}" id="UPTOTAL" />
            
                 
                <s:iterator value="saveprod" status="sss1">
                     
                    <div id='<s:property value="%{#sss1.index}"/>td_popup' name='<s:property value="%{#sss1.index}"/>td_popup' style='background-color: #f2f2f2; width: 382px; height: 150px; display:none; position: absolute; top:310px; right:50px;border-width: 1px;border-style: solid;border-color: black;overflow: auto'>
                                                                <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
                                                                    <tr bgcolor="#f2f2f2">
                                                                        <td bgcolor="#2a6afe" height='23' width='356' align="left" id="<s:property value="%{#sss1.index}"/>td" class='dragme' style="font-size:12px;color:white;font-weight: bold">
                                                                                 <s:set id="settemp1" name="settemp1" value="%{PRODUCT_SL_NO}"/>
                                
                                <s:iterator value="productlist.{?#this.SL_NO ==#settemp1 }">
                                 Remarks - <s:property value="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}"/>
                                </s:iterator>
                                                                            </td>
                                                                        <td><a href='javascript:styledPopupCloseeditline("<s:property value="%{#sss1.index}"/>td_popup")'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                                                                    </tr>
                                                                    <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                                                                            <table width="100%"><tr>
                                                                                    <td>
                                                                                        <s:textarea cols="5" name="UP_REMARKS" value="%{REMARKS}" cssStyle="width:370px;height:110px;overflow:auto"  rows="5" />
                                                                                        
                                                                                    </td>
                                                                                    
                                                                                </tr>   
                                                                            </table>
                                                                        </td>
                                                                    </tr></table>
                                                            </div>
                    
                    
                    
                    
                    <%--
                    
                    
                    <div id='invdtid<s:property value="%{#sss1.index}"/>' name='invdtid<s:property value="%{#sss1.index}"/>' style='width: 600px; height: 300x; display:none; position: absolute; top: 120px; left:150px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='600px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='580px'  style="font-size:12px;color:white;font-weight: bold"   id='invdtid<s:property value="%{#sss1.index}"/>td'>
                                
                                <s:set id="settemp1" name="settemp1" value="%{PRODUCT_SL_NO}"/>
                                
                                <s:iterator value="productlist.{?#this.SL_NO ==#settemp1 }">
                                 Invoice Details - <s:property value="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}"/>
                                </s:iterator>
                                
                            
                            </td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("invdtid<s:property value="%{#sss1.index}"/>").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        <tr><td colspan="2">
                                <table  width='100%'><tr><td class="label-1">Shipping Bill</td><td>
                                            <s:textfield name="SSHIPBILLNEW" value="%{SSHIPBILL}" cssStyle="width:80px" id="SSHIPBILL%{#sss1.index}"  theme="simple" cssClass="texts"/>
                                          
                                     </td>
                                        <td class="label-1">BOS</td><td>
                                            <s:textfield name="BOSNEW" value="%{BOS}" id="BOS"  cssStyle="width:80px"  theme="simple" cssClass="texts"/>
                                        </td>
                                        <td class="label-1">Invoice</td><td>
                                            <s:textfield name="SINVNEW" value="%{SINV}" id="SINV"   cssStyle="width:80px" theme="simple" cssClass="texts"/>
                                        </td>
                                        
                                        <td><button onclick="invrec('<s:property value="%{#sss1.index}"/>');" class="sexybutton"><span><span><span class="search"></span></span></span></button></td>
                                    </tr></table>
                        </td>
                        </tr>
                        <tr><td colspan="2" valign='top' style="height: 300px">
                                <s:hidden name="BILL_DT_SL_NONEW" value="%{SL_NO}"/>
                        <s:url id="invurl"  action="invnewnewmbillentAction.action">
                            <s:param name="INVSLTXT" value="%{#sss1.index}"/>
                        </s:url>
                        
                        <sx:div id="invdetails%{#sss1.index}"  href="%{#invurl}"  cssStyle="width:600px" listenTopics="invshow_detail%{#sss1.index}" formId="formId" showLoadingText="Loading ......"></sx:div>
                                              
                       </td>
                    </tr></table>
                                                   
                     </div> --%>
                </s:iterator>
                
            
                 <s:if test="%{saveprod.size()==0}">
                 <s:if test="%{productlist.size()<6}">
                 <s:iterator value="%{productlist}"   status="sss1">
                     
                     <div id='<s:property value="%{#sss1.index}"/>td_popup' name='<s:property value="%{#sss1.index}"/>td_popup' style='background-color: #f2f2f2; width: 382px; height: 150px; display:none; position: absolute; top:310px; right:50px;border-width: 1px;border-style: solid;border-color: black;overflow: auto'>
                                                                <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
                                                                    <tr bgcolor="#f2f2f2">
                                                                        <td bgcolor="#2a6afe" height='23' width='356' align="left" id="<s:property value="%{#sss1.index}"/>td" class='dragme' style="font-size:12px;color:white;font-weight: bold">Remarks</td>
                                                                        <td><a href='javascript:styledPopupCloseeditline("<s:property value="%{#sss1.index}"/>td_popup")'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                                                                    </tr>
                                                                    <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                                                                            <table width="100%"><tr>
                                                                                    <td>
                                                                                        <s:textarea cols="5" value="" name="REMARKS" cssStyle="width:370px;height:110px;overflow:auto"  rows="5" />
                                                                                        
                                                                                    </td>
                                                                                    
                                                                                </tr>   
                                                                            </table>
                                                                        </td>
                                                                    </tr></table>
                                                            </div>
                     
                     
                     <%--
                       <div id='invdtid<s:property value="%{#sss1.index}"/>' name='invdtid<s:property value="%{#sss1.index}"/>' style='width: 600px; height: 300x; display:none; position: absolute; top: 120px; left:150px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='600px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='580px'  style="font-size:12px;color:white;font-weight: bold"   id='invdtid<s:property value="%{#sss1.index}"/>td'>Invoice Details</td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("invdtid<s:property value="%{#sss1.index}"/>").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        <tr><td colspan="2">
                                <table  width='100%'><tr><td class="label-1">Shipping Bill</td><td>
                                            <s:textfield name="SSHIPBILLNEW" value="%{SSHIPBILL}" cssStyle="width:80px" id="SSHIPBILL%{#sss1.index}"  theme="simple" cssClass="texts"/>
                                           
                                     </td>
                                        <td class="label-1">BOS</td><td>
                                            <s:textfield name="BOSNEW" value="%{BOS}" id="BOS"  cssStyle="width:80px"  theme="simple" cssClass="texts"/>
                                        </td>
                                        <td class="label-1">Invoice</td><td>
                                            <s:textfield name="SINVNEW" value="%{SINV}" id="SINV"   cssStyle="width:80px" theme="simple" cssClass="texts"/>
                                        </td>
                                        
                                        <td><button onclick="invrec('<s:property value="%{#sss1.index}"/>');" class="sexybutton"><span><span><span class="search"></span></span></span></button></td>
                                    </tr></table>
                        </td>
                        </tr>
                        <tr><td colspan="2" valign='top' style="height: 300px">
                       
                         <s:url id="invurl"  action="invnewnewmbillentAction.action">
                            <s:param name="INVSLTXT" value="%{#sss1.index}"/>
                        </s:url>
                        <sx:div id="invdetails%{#sss1.index}"  href="%{#invurl}"  cssStyle="width:600px" listenTopics="invshow_detail%{#sss1.index}" formId="formId" showLoadingText="Loading ......"></sx:div>
                                              
                       </td>
                    </tr></table>
                                                   
                     </div> --%>
                 </s:iterator>  
                 </s:if>
                     <s:else>
                         <s:iterator begin="0" end="4"    status="sss1">
                              <div id='<s:property value="%{#sss1.index}"/>td_popup' name='<s:property value="%{#sss1.index}"/>td_popup' style='background-color: #f2f2f2; width: 382px; height: 150px; display:none; position: absolute; top:310px; right:50px;border-width: 1px;border-style: solid;border-color: black;overflow: auto'>
                                                                <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
                                                                    <tr bgcolor="#f2f2f2">
                                                                        <td bgcolor="#2a6afe" height='23' width='356' align="left" id="<s:property value="%{#sss1.index}"/>td" class='dragme' style="font-size:12px;color:white;font-weight: bold">Remarks</td>
                                                                        <td><a href='javascript:styledPopupCloseeditline("<s:property value="%{#sss1.index}"/>td_popup")'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                                                                    </tr>
                                                                    <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                                                                            <table width="100%"><tr>
                                                                                    <td>
                                                                                        <s:textarea cols="5" value="" name="REMARKS" cssStyle="width:370px;height:110px;overflow:auto"  rows="5" />
                                                                                        
                                                                                    </td>
                                                                                    
                                                                                </tr>   
                                                                            </table>
                                                                        </td>
                                                                    </tr></table>
                                                            </div>
                             
                             
                             
                             <%--
                             
                             <div id='invdtid<s:property value="%{#sss1.index}"/>' name='invdtid<s:property value="%{#sss1.index}"/>' style='width: 600px; height: 300x; display:none; position: absolute; top: 120px; left:150px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='600px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='580px'  style="font-size:12px;color:white;font-weight: bold"  id='invdtid<s:property value="%{#sss1.index}"/>td'>Invoice Details</td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("invdtid<s:property value="%{#sss1.index}"/>").style.display = "none";'><img height='23' border='0' width='24' src="css/images/divclose.gif"/></a></td>
                        </tr>
                        <tr><td colspan="2">
                                <table  width='100%'><tr><td class="label-1">Shipping Bill</td><td>
                                            <s:textfield name="SSHIPBILLNEW" value="%{SSHIPBILL}" cssStyle="width:80px" id="SSHIPBILL%{#sss1.index}"  theme="simple" cssClass="texts"/>
                                            
                                     </td>
                                        <td class="label-1">BOS</td><td>
                                            <s:textfield name="BOSNEW" value="%{BOS}" id="BOS"  cssStyle="width:80px"  theme="simple" cssClass="texts"/>
                                        </td>
                                        <td class="label-1">Invoice</td><td>
                                            <s:textfield name="SINVNEW" value="%{SINV}" id="SINV"   cssStyle="width:80px" theme="simple" cssClass="texts"/>
                                        </td>
                                        
                                        <td><button onclick="invrec('<s:property value="%{#sss1.index}"/>');" class="sexybutton"><span><span><span class="search"></span></span></span></button></td>
                                    </tr></table>
                        </td>
                        </tr>
                        <tr><td colspan="2" valign='top' style="height: 300px">
                         <s:url id="invurl"  action="invnewnewmbillentAction.action">
                            <s:param name="INVSLTXT" value="%{#sss1.index}"/>
                        </s:url>
                        <sx:div id="invdetails%{#sss1.index}"  href="%{#invurl}"  cssStyle="width:600px" listenTopics="invshow_detail%{#sss1.index}" formId="formId" showLoadingText="Loading ......"></sx:div>
                                              
                       </td>
                    </tr></table>
                                                   
                     </div>
                             --%>
                         </s:iterator>
                     </s:else>
                 
                 </s:if>
            
               
                
                
                <div id='invoicesearch' name='invoicesearch' style='width: 320px; height: 200px; display:none; position: absolute; top: 190px; left:200px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
                    <table width='320px' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe" height='23'  width=300px'  style="font-size:12px;color:white;font-weight: bold" align="center">Shipping Bill Details</td>
                            <td><a href='javascript:invoicesearchClose();'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                        </tr>
                        <tr bgcolor="#f2f2f2"><td colspan="2"   valign="top">
                                <table width="100%" cellspacing="0" cellpadding='0'>
                                    <tr>
                                        <td>
                                            <iframe name="invoicefrm" id="invoicefrm" scrolling="no" frameborder="0" style="margin: 0px;padding: 0px" width="319px" height="180px"></iframe>
                                            
                                        </td>

                                </tr>   
                            </table>
                        </td>
                    </tr></table>
            </div>  
    </body>
</html>


