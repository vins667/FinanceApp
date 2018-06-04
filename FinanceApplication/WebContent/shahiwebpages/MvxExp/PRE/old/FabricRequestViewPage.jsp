<%--
    Document   : FabricRequestViewPage
    Created on : Feb 14, 2012, 11:09:20 AM
    Author     : Ranjeet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>



<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shahi Exports Pvt Ltd</title>
<style type="text/css">
.tb2 {
	background-color: #FFFFFF;
	border: 1px solid #000000;
	border-style: solid;
	width: 230px;
}

.tb10 {
	background-color: #CCCCCC;
	background-repeat: repeat-x;
	border: 1px solid #000000;
	width: 230px;
}

th {
	font-size: 9pt;
	font-weight: bold;
	color: #0066aa;
	background-image: url('../image/table-gradient.jpg');
}

tbody {
	
	overflow-y: auto;
	overflow-x: hidden;
}
</style>
<!--[if IE]>
    <style type="text/css">
        .div1 {
            scrollbar-arrow-color:#000;
            scrollbar-track-color:#fff;
            scrollbar-face-color:#0066AA;
            scrollbar-highlight-color:#fff;
            scrollbar-3dlight-color:#fff;
            scrollbar-darkshadow-color:#fff;
            scrollbar-shadow-color:#fff;

            position: relative;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 20px ;
        }
        .tr1 {
             position:absolute;
             top: expression(this.offsetParent.scrollTop);
          }
        tbody {
            height: auto;
        }
    </style>
<![endif]-->

</head>
<body style="background-color: #f2f2f2; margin: 0px">
	<table width="100%" border="0" cellpadding="0px" cellspacing=0px">
		<tr>
			<td valign="top">
				<table width="100%" cellpadding="0px" cellspacing=0px" >
					<tr>
						<td>
							<table width="100%" cellspacing="1" cellpadding="1">
								<tr>
                                                                     <td  class="label-1" width="100px">Placement</td>
                                                                     <td colspan="3"><s:textfield name="PLACEMENT_DIS"  onblur="copytxt(this,'PLACEMENT_%{FABSLID}')" value="%{PLACEMENT_DIS}"  id="PLACEMENT_DIS%{FABSLID}" theme="simple" cssClass="texts" cssStyle="width:100%"  /></td>
                                                                </tr> 
                                                            
								<tr>
                                                                     <td  class="label-1" width="100px">Width</td>
                                                                        <td width="100px"><s:textfield name="WIDTH_DIS" onblur="copytxt(this,'WIDTH_%{FABSLID}')" value="%{CUTABLWITH}"  id="WIDTH_DIS%{FABSLID}" theme="simple" cssClass="texts" cssStyle="width:150px"  /></td>
									
                                                                        <td class="label-1"  width="150px">&nbsp;Fabric&nbsp;Description</td>
									<td><s:textfield name="fabricdesc_dis"  onblur="copytxt(this,'fabricdesc_%{FABSLID}')" value="%{fabricdesc_dis}"  id="fabricdesc_dis%{FABSLID}" theme="simple" cssClass="texts" cssStyle="width:100%"  /></td>
                                                                       
								</tr>
                                                               
								<tr>
                                                                    <td class="label-1">Fabric Code</td>
                                                                    <td><s:textfield name="fabriccode" value="%{fabriccode}" id="fabriccodeid%{FABSLID}" theme="simple" cssClass="textreadonly" cssStyle="width:150px;" readonly="true" /></td>
                                                                    <td class="label-1">&nbsp;Description</td>
                                                                        
                                                                    <td><s:textfield name="fabricdesc_DESC"  value="%{desc_dis}"  cssClass="textreadonly" readonly="true"  theme="simple"  cssStyle="width:100%"  /></td>
									
                                                                        <%--<td width="1px"><input type="button" class="texts" style="font-size: 10px" name="btn" onclick="fabricviewclrtemp('<s:property value="%{FABSLID}"/>')" value="X"></td>
                                                                        --%>
								</tr>
							</table>
						</td>
					</tr>

					<tr>

						<td valign="top">
                                                    
                                           <div style="width: 100.0%; overflow: auto; height: 150px;">
	                        
                                        <table id="titleid<s:property value="%{FABSLID}"/>">
                                            
                                           <thead class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);">
                                        
						<tr class="titlecss" >
											<th align="left"  width="100px">Style No
                                                                                         <img src="js/add.png" onclick="addsubtitleq('titleid<s:property value="%{FABSLID}"/>','<s:property value="%{FABSLID}"/>')" />
                                                                                        </th>	
                                                                                        <th>
                                            <table  cellpadding="1" cellspacing="0"><tr class="label-1">
											<td align="left" width="203px">Color</td>
                                                                                        <td align="left" width="100px">Gmt&nbsp;Qty</td>
                                                                                        <td align="left" width="50px">YY</td>
                                                                                        <td align="left" width="50px">&nbsp;X%</td>
                                                                                        <td align="left" width="100px">Fab&nbsp;Qty</td>
                                                                                        <td align="left" width="50px">Uom</td>
											<td align="left" width="300px">Remarks</td>
											<td width="1px" width="105px">File</td>
											<td width="1px" width="50px">&nbsp;</td>
											<td width="1px" width="50px">&nbsp;</td>
                                                                                      
                                                                                        </tr></table>
                                              </th>
						</tr>
						</thead>
						<tbody>
                                          <%-- save --%>
                                          <s:set id="headctn" name="headctn" value="100"/>
                                          
                                          <s:set id="linectn" name="linectn" value="100"/>
                                          
                                                 <s:iterator value="%{STYLECODE}" status="savestatussty">
                                            <tr  class="titlecss"> 
                                                <td style="border-width:1px;border-style: solid;border-color:black ">
                                                    <table cellpadding="1" cellspacing="0"><tr><td>
                                                    <input type="text" name="STYLE_CODE_LI" id="<s:property value="%{#headctn}"/>STYLE_CODE_LI<s:property value="%{FABSLID}"/>" class="texts" readonly="true" 
                                                           maxlength="100" 
                                                           value="<s:property value="%{STYLECODE[#savestatussty.index]}"/>"
                                                           onblur="copytxt(this,'subtitle<s:property value="%{FABSLID}"/>_TITLE_DESC')" style="width:150px;"/>
                                                            </td><td><a target="popupframe2" onclick="showbox22();" style="text-decoration: none;"  href="stimglistAction.action?stlid=<s:property value="%{#headctn}"/>STYLE_CODE_LI<s:property value="%{FABSLID}"/>&CPCLASS=<s:property value="%{#headctn}"/>subtitle<s:property value="%{FABSLID}"/>_TITLE_DESC"><img width="15px" style="border: 0px" height="15px" src="../../css/image/Search-icon-big.png" /></a>
                                                            </td>
                                                            <td><a target="popupframe2" onclick="showbox22();" style="text-decoration: none;"  href="mitemAction.action?SAMPLE_ID=<s:property value="%{SAMPLE_ID}"/>&STYLE_CODE=<s:property value="%{STYLECODE[#savestatussty.index]}"/>&MAST_SL_NO=<s:property value="%{FABSLID}"/>"><img width="15px" style="border: 0px" height="15px" src="images/M3.png" /></a>
                                                            </td>
                                                        
                                                        </tr></table>
                                                </td>
                                                <td style="border-width:1px;border-style: solid;border-color:black ">
                                                    <table  id="<s:property value="%{#headctn}"/>subtitle<s:property value="%{FABSLID}"/>" cellpadding="1" cellspacing="0">
                                                        <s:set id="savestylecode" name="savestylecode" value="%{STYLECODE[#savestatussty.index]}"/>
                                                        <s:iterator value="datalist.{?#this.STYLE_CODE==#savestylecode}" status="savestatussubline">
                                                        <tr class="<s:property value="%{#headctn}"/>subtitle<s:property value="%{FABSLID}"/>">
                                                          
                                                            <td><s:textfield name="fabriccolor" 
                                                                         value="%{COLOR_CODE}" 
                                                                         id="fabriccolorid%{FABSLID}_%{#linectn}" 
                                                                         theme="simple" cssClass="texts"
                                                                         maxlength="30" cssStyle="width:200px;text-transform:uppercase;background-color: #d9d5bd"  
                                                                         onkeypress="return onlyAlphabets(event,this);"  
                                                                         onblur="onlyAlphabetsBlur(this);"/>
												
                                                            </td><td>  
                                                                <s:textfield name="ORDR_QTY" 
                                                                             value="%{ORDR_QTY}" 
                                                                             id="ORDR_QTY%{FABSLID}_%{#linectn}" 
                                                                             theme="simple" 
                                                                             onblur="validatefabqty('ORDR_QTY%{FABSLID}_%{#linectn}','AVG_CONS%{FABSLID}_%{#linectn}','EXT_PER%{FABSLID}_%{#linectn}','fabricconsid%{FABSLID}_%{#linectn}')"
                                                                             cssClass="texts" 
                                                                             maxlength="10" 
                                                                             cssStyle="width:100px;text-transform:uppercase;"  />
                                                                 </td>
                                                               <td>  
                                                                   <s:textfield name="AVG_CONS" 
                                                                                value="%{AVG_CONS}" id="AVG_CONS%{FABSLID}_%{#linectn}" 
                                                                                  onblur="validatefabqty('ORDR_QTY%{FABSLID}_%{#linectn}','AVG_CONS%{FABSLID}_%{#linectn}','EXT_PER%{FABSLID}_%{#linectn}','fabricconsid%{FABSLID}_%{#linectn}')"
                                                                           
                                                                                theme="simple" cssClass="texts" maxlength="10" 
                                                                                cssStyle="width:50px;text-transform:"  />
                                                                </td>
							 <td>  
                                                             <s:textfield name="EXT_PER" 
                                                                      value="%{EXT_PER}" id="EXT_PER%{FABSLID}_%{#linectn}" 
                                                                      theme="simple" 
                                                                        onblur="validatefabqty('ORDR_QTY%{FABSLID}_%{#linectn}','AVG_CONS%{FABSLID}_%{#linectn}','EXT_PER%{FABSLID}_%{#linectn}','fabricconsid%{FABSLID}_%{#linectn}')"
                                                                           
                                                                      cssClass="texts" 
                                                                      maxlength="10" 
                                                                      cssStyle="width:50px;text-transform:uppercase;"  />
                                                  </td>
											
						<td><s:textfield name="fabriccons" 
                                                             onblur="validatenumber(this)" 
                                                             value="%{QTY}" 
                                                             id="fabricconsid%{FABSLID}_%{#linectn}" 
                                                             theme="simple" 
                                                             cssClass="texts" 
                                                             cssStyle="width:100px;" />
                                                </td>
                                              <td>
                                                  <s:select name="fabricuom" 
                                                  value="%{UOM}" id="fabricuomid%{FABSLID}_%{#linectn}"
                                                  cssStyle="width:50px"
                                                  theme="simple" 
                                                  cssClass="texts"
                                                  list="uomlist"
                                                  listKey="SAMPLE_TYPE"
                                                  listValue="SAMPE_TYPE_DESC"
                                                  />                                          
                                              </td>
						 <td>
                                                     <s:textfield name="fabricremarks" 
                                                                  value="%{REMARKS}" 
                                                                  id="fabricremarksid%{FABSLID}_%{#linectn}" 
                                                                  theme="simple" 
                                                                  cssClass="texts" 
                                                                  cssStyle="width:300px;text-transform:uppercase" />
                                                 </td>
												
							<td valign="bottom" width="105px">
                                                            <table cellpadding="1" cellspacing="1"><tr><td>
                                                           <s:file name="uploads" theme="simple" cssClass="texts" onchange="filecomboactionup(this,'uploadstemp%{FABSLID}_%{#linectn}')"  cssStyle="width:50px" id="uploadsid%{FABSLID}_%{#linectn}" /> 
                                                           <s:hidden name="uploadstemp" value="%{COMBO_FILE_NAME}" id="uploadstemp%{FABSLID}_%{#linectn}" />
                                                                    </td><td>
                                                           <s:if test="%{COMBO_FILE_NAME!=null}">
												<a title="Click to view Detail" style="cursor: hand; text-decoration: none; padding: 0px"
													href="/ShahiApplication/shahiwebpages/Sampling/Fabricrequest/combofiles/<s:property value="COMBO_FILE_NAME"/>" onclick='document.getElementById("belowfram3").style.display="block";showbox3("File - <s:property value="%{#ctnrow+1}"/>");' target="belowfram3">
													 <img src="images/application_view_list.png" style="border-width:0px;"/><!-- COMBO - <s:property value="%{#ctnrow+1}" /> -->
												</a></s:if>
                                                                    </td></tr></table>
							</td>												
							<td width="1px">
                                                            <input type="button" class="texts" 
                                                                   style="font-size: 12px" 
                                                                   name="btn" 
                                                                   onclick="fabricviewclr('<s:property value="%{FABSLID}"/>_<s:property value="%{#linectn}"/>')" value="X">
                                                        </td>
							
                                                          
                                                            <td>
                                                               
                                                                <s:hidden name="PLACEMENT" value="%{PLACM}"  id="PLACEMENT_%{FABSLID}" theme="simple" cssClass="PLACEMENT_%{FABSLID}" />
							        <s:hidden name="STYLE_CODE_E" cssClass="%{#headctn}subtitle%{FABSLID}_TITLE_DESC" value="%{STYLE_CODE}"  theme="simple" />
                                                                <s:hidden name="fabricdesc" value="%{FABR_DESC}" title="%{fabricdesc}" id="fabricdesc_%{FABSLID}" theme="simple" cssClass="fabricdesc_%{FABSLID}" />
							        <s:hidden name="WIDTH" value="%{WIDTH}"  id="WIDTH_%{FABSLID}" theme="simple" cssClass="WIDTH_%{FABSLID}" />
                                                                <s:hidden name="FABSLID_L" cssClass="FABSLID_L_%{FABSLID}" value="%{FABSLID}" />	 
                                                                <s:if test="%{#savestatussubline.index==0}">
                                                                <img src="js/add.png"  onclick="addsubtitleq1('<s:property value="%{#headctn}"/>subtitle<s:property value="%{FABSLID}"/>','<s:property value="%{FABSLID}"/>')" />
                                                              </s:if>
                                                            </td>
                                                        </tr>
                                                        <s:set id="linectn" name="linectn" value="%{#linectn+1}"/>
                                                        </s:iterator>
                                                    </table>

                                                </td>
                                            </tr>
                                            
                                             <s:set id="headctn" name="headctn" value="%{#headctn+1}"/>
                                            </s:iterator>
                                                <%-- Close --%>
                                                    
                                                    
                                                    
                                                    
                                                <s:set id="ctnnewentid" name="ctnnewentid" value="0"/>
                                            <s:iterator begin="0" end="1" status="statussty">
                                            <tr  class="titlecss"> 
                                                <td style="border-width:1px;border-style: solid;border-color:black ">
                                                    <table cellpadding="1" cellspacing="0"><tr><td>
                                                    <input type="text" name="STYLE_CODE_LI" id="<s:property value="%{#statussty.index}"/>STYLE_CODE_LI<s:property value="%{FABSLID}"/>" class="texts" readonly="true" 
                                                           maxlength="100" 
                                                           onblur="copytxt(this,'subtitle<s:property value="%{FABSLID}"/>_TITLE_DESC')" style="width:150px;"/>
                                                            </td><td><a target="popupframe2" onclick="showbox22();" style="text-decoration: none;"  href="stimglistAction.action?stlid=<s:property value="%{#statussty.index}"/>STYLE_CODE_LI<s:property value="%{FABSLID}"/>&CPCLASS=<s:property value="%{#statussty.index}"/>subtitle<s:property value="%{FABSLID}"/>_TITLE_DESC"><img width="15px" style="border: 0px" height="15px" src="../../css/image/Search-icon-big.png" /></a>
                                                            </td></tr></table>
                                                </td>
                                                <td style="border-width:1px;border-style: solid;border-color:black ">
                                                    <table  id="<s:property value="%{#statussty.index}"/>subtitle<s:property value="%{FABSLID}"/>" cellpadding="1" cellspacing="0">
                                                        <s:iterator begin="0" end="1" status="statussubline">
                                                        <tr class="<s:property value="%{#statussty.index}"/>subtitle<s:property value="%{FABSLID}"/>">
                                                          
                                                            <td><s:textfield name="fabriccolor" 
                                                                         value="%{COLOR_CODE}" 
                                                                         id="fabriccolorid%{FABSLID}_%{#ctnnewentid}" 
                                                                         theme="simple" cssClass="texts"
                                                                         maxlength="30" cssStyle="width:200px;text-transform:uppercase;background-color: #d9d5bd"  
                                                                         onkeypress="return onlyAlphabets(event,this);"  
                                                                         onblur="onlyAlphabetsBlur(this);"/>
												
                                                            </td><td>  
                                                                <s:textfield name="ORDR_QTY" 
                                                                             value="" 
                                                                              onblur="validatefabqty('ORDR_QTY%{FABSLID}_%{#ctnnewentid}','AVG_CONS%{FABSLID}_%{#ctnnewentid}','EXT_PER%{FABSLID}_%{#ctnnewentid}','fabricconsid%{FABSLID}_%{#ctnnewentid}')"
                                                                        
                                                                             id="ORDR_QTY%{FABSLID}_%{#ctnnewentid}" 
                                                                             theme="simple" 
                                                                             cssClass="texts" 
                                                                             maxlength="10" 
                                                                             cssStyle="width:100px;text-transform:uppercase;"  />
                                                                 </td>
                                                               <td>  
                                                                   <s:textfield name="AVG_CONS" 
                                                                                value="" id="AVG_CONS%{FABSLID}_%{#ctnnewentid}" 
                                                                                theme="simple" cssClass="texts" maxlength="10" 
                                                                                  onblur="validatefabqty('ORDR_QTY%{FABSLID}_%{#ctnnewentid}','AVG_CONS%{FABSLID}_%{#ctnnewentid}','EXT_PER%{FABSLID}_%{#ctnnewentid}','fabricconsid%{FABSLID}_%{#ctnnewentid}')"
                                                                        
                                                                                cssStyle="width:50px;text-transform:"  />
                                                                </td>
							 <td>  
                                                             <s:textfield name="EXT_PER" 
                                                                      value="" id="EXT_PER%{FABSLID}_%{#ctnnewentid}" 
                                                                      theme="simple" 
                                                                      cssClass="texts" 
                                                                        onblur="validatefabqty('ORDR_QTY%{FABSLID}_%{#ctnnewentid}','AVG_CONS%{FABSLID}_%{#ctnnewentid}','EXT_PER%{FABSLID}_%{#ctnnewentid}','fabricconsid%{FABSLID}_%{#ctnnewentid}')"
                                                                          
                                                                      maxlength="10" 
                                                                      cssStyle="width:50px;text-transform:uppercase;"  />
                                                  </td>
											
						<td><s:textfield name="fabriccons" 
                                                             onblur="validatenumber(this)" 
                                                             value="" 
                                                             id="fabricconsid%{FABSLID}_%{#ctnnewentid}" 
                                                             theme="simple" 
                                                             cssClass="texts" 
                                                             cssStyle="width:100px;" />
                                                </td>
                                              <td>
                                                  <s:select name="fabricuom" 
                                                  value="" id="fabricuomid%{FABSLID}_%{#ctnnewentid}"
                                                  cssStyle="width:50px"
                                                  theme="simple" 
                                                  cssClass="texts"
                                                  list="uomlist"
                                                  listKey="SAMPLE_TYPE"
                                                  listValue="SAMPE_TYPE_DESC"
                                                  />                                          
                                              </td>
						 <td>
                                                     <s:textfield name="fabricremarks" 
                                                                  value="" 
                                                                  id="fabricremarksid%{FABSLID}_%{#ctnnewentid}" 
                                                                  theme="simple" 
                                                                  cssClass="texts" 
                                                                  cssStyle="width:300px;text-transform:uppercase" />
                                                 </td>
												
							<td valign="bottom" valign="bottom" width="105px">
                                                           
                                                            <s:file name="uploads" theme="simple" onchange="filecomboaction(this,'uploadstemp%{FABSLID}_%{#ctnnewentid}')" cssClass="texts"  cssStyle="width:50px" id="uploadsid%{FABSLID}_%{#ctnnewentid}" /> 
                                                           <s:hidden name="uploadstemp" value="No" id="uploadstemp%{FABSLID}_%{#ctnnewentid}" />
							</td>												
							<td width="1px">
                                                            <input type="button" class="texts" 
                                                                   style="font-size: 12px" 
                                                                   name="btn" 
                                                                   onclick="fabricviewclr('<s:property value="%{FABSLID}"/>_<s:property value="%{#ctnnewentid}"/>')" value="X">
                                                        </td>
							
                                                          
                                                            <td>
                                                                <s:hidden name="PLACEMENT" value=""  id="PLACEMENT_%{FABSLID}" theme="simple" cssClass="PLACEMENT_%{FABSLID}" />
							        <s:hidden name="STYLE_CODE_E" cssClass="%{#statussty.index}subtitle%{FABSLID}_TITLE_DESC" value=""  theme="simple" />
                                                                <s:hidden name="fabricdesc" value=""  id="fabricdesc_%{FABSLID}" theme="simple" cssClass="fabricdesc_%{FABSLID}" />
							        <s:hidden name="WIDTH" value="" title="%{WIDTH}" id="WIDTH_%{FABSLID}" theme="simple" cssClass="WIDTH_%{FABSLID}" />
                                                                <s:hidden name="FABSLID_L" cssClass="FABSLID_L_%{FABSLID}" value="%{FABSLID}" />	 
                                                                <s:if test="%{#statussubline.index==0}">
                                                                <img src="js/add.png"  onclick="addsubtitleq1('<s:property value="%{#statussty.index}"/>subtitle<s:property value="%{FABSLID}"/>','<s:property value="%{FABSLID}"/>')" />
                                                              </s:if>
                                                            </td>
                                                        </tr>
                                                         <s:set id="ctnnewentid" name="ctnnewentid" value="%{#ctnnewentid+1}"/>
                                                        </s:iterator>
                                                    </table>

                                                </td>
                                            </tr>
                                           
                                            </s:iterator>
                                            </tbody>
                                        </table>
                                         </div>
                                                    
                                                    
                                                 
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<s:hidden name="FABSLID" value="%{FABSLID}" />
</body>
</html>

