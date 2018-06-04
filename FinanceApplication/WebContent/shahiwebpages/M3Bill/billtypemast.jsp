<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script>
    
    <s:if test="%{savesubflag!=null}">
     </s:if>
    <s:else>
   showdetailslist(document.getElementById('SL_NO0').value,document.getElementById('SUB_TYPE_DESC0').value);
</s:else>

$(document).ready(function(){
    // make the header fixed on scroll
    $("#detailclaim").chromatable({
        width: "100%", // specify 100%, auto, or a fixed pixel amount
        height: "420px",
        scrolling: "yes" // must have the jquery-1.3.2.min.js script installed to use
    });
});
</script>
</head>
<body style="margin: 0px;padding: 0px">
	
	<table style="width: 100%;">
		<tr>
                    <td style="width: 100%"  colspan="2">
                        <div style="height: 390px;overflow: auto">
                        <table id="detailclaim"   class="table table-bordered table-striped table-fixed-header">
				<thead>
					<tr>
						<th style="text-align: left;" class="label-1">Description</th>	
                                                <th style="text-align: left;" class="label-1">Code</th>
                                                <%--<th>&nbsp;</th>--%>
						<th style="text-align: left;width:80px;" class="label-1">Flag</th>		
						<c:if test="%{typelist!=null && typelist.size()>0}">
						<th style="text-align: left;" class="label-1">Action</th>
						</c:if>				
					</tr>
				</thead>
				<tbody>
                                    <s:set id="ctrlc" value="0"/>
                                    <s:iterator value="typelist" status="st">
                                       <tr style="background-color: #FFFFFF;">
                                        <td>
                                            
                                            <s:hidden name="SL_NO" value="%{SL_NO}" id="SL_NO%{#ctrlc}"  theme="simple" cssClass="texts" />
                                            <s:textfield name="UP_TYPE_DESC" maxlength="30" id="SUB_TYPE_DESC%{#ctrlc}" value="%{SUB_TYPE_DESC}" theme="simple" cssClass="texts" size="40"/> </td> 
                                        <td>
                                            
                                            <s:textfield name="UP_TYPE_CODE"  value="%{SUB_TYPE_CODE}" id="TYPE_CODE%{#ctrlc}" maxlength="10" theme="simple" cssClass="texts" size="15"/> 
                                        </td>
                                        <%--<td>    
                                            <a href="searchpagembillAction.action?SEARCH_TYPE=1&TXTID=TYPE_CODE<s:property value="%{#ctrlc}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("GL Code")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        </td>   --%>   
                                        <td> <s:select name="UP_TYPEFLAG" list="# {'Y':'Active','N':'In Active'}" value="%{FLAG}" theme="simple" cssClass="selecttext" id="TYPEFLAG"/></td>
                                        <td style="width:80px;" class="texts"  valign="top">
                                            &nbsp;<a href="#" onclick="showdetailslist('<s:property value="%{SL_NO}"/>','<s:property value="%{SUB_TYPE_DESC}"/>')"><img src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail" title="View Detail" style="border-width: 0px;"/></a>
                                                &nbsp;<s:if test="%{DELFLAG=='EXIST'}"><img src="css/ShahiButtons/images/icons/silk/lock_delete.png" alt="Detail" title="Delete Lock" style="border-width: 0px;"/></s:if><s:else>
                                                    <a href="#" onclick="deletesubstratdt('<s:property value="%{SL_NO}"/>','<s:property value="%{MAST_SL_NO}"/>','<s:property value="%{SUB_TYPE_CODE}"/>')"><img src="css/ShahiButtons/images/icons/silk/delete.png" alt="Detail" title="Delete" style="border-width: 0px;"/></a>
                                                    </s:else>
						</td>
                                    </tr>
                                    <s:set id="ctrlc" value="%{#ctrlc+1}"/> 
                                    </s:iterator>
                                    
                                    <s:iterator begin="%{ctrlc}" end="10">
                                                                     
                                    <tr style="background-color: #FFFFFF;">
                                        <td><s:textfield name="TYPE_DESC" maxlength="30" theme="simple" cssClass="texts" size="40"/> </td> 
                                        <td>
                                            
                                            <s:textfield name="TYPE_CODE"  id="TYPE_CODE%{#ctrlc}" maxlength="10" theme="simple" cssClass="texts" size="15"/> 
                                        </td>
                                        <%--<td>    
                                            <a href="searchpagembillAction.action?SEARCH_TYPE=1&TXTID=TYPE_CODE<s:property value="%{#ctrlc}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("GL Code")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        </td>  --%>    
                                        <td> <s:select name="TYPEFLAG" list="# {'Y':'Active','N':'In Active'}" theme="simple" cssClass="selecttext" id="TYPEFLAG"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <s:set id="ctrlc" value="%{#ctrlc+1}"/>
                                    </s:iterator>
				</tbody>
			</table>
                                    </div>
			</td>
                </tr><tr>
<tr><td colspan='2' height='33px'>&nbsp;</td></tr>

                    <td>
                          <s:if test="%{MAST_SL_NO!=null}">
                                      <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button>                                      
                                     </s:if>
                    </td>
			<td style="vertical-align: bottom" align="right">
				<a href="#" class="sexybutton" onclick="addrow();"><span><span><span class="add">Add Row</span></span></span></a>
                                <s:hidden name="rwcthid" id="rwcthid" value="%{#ctrlc}"/>
			</td>		
		</tr>
	</table>
</body>
</html>


