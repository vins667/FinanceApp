<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script>
  
</script>
</head>
<body style="margin: 0px;padding: 0px">
	
	<table style="width: 100%;">
		<tr>
                    <td style="width: 100%" colspan="2">
			<table>
				
                                    <s:set id="ctrlc" value="0"/>
                                      <s:iterator begin="%{ctrlc}" end="10">
                                                                    
                                     <tr style="background-color: #FFFFFF;">
                                        <td><s:textfield name="TYPE_DESC" maxlength="30" theme="simple" cssClass="texts" size="40"/> </td> 
                                        <td><s:textfield name="TYPE_CODE"  id="TYPE_CODE%{#ctrlc}" maxlength="10" theme="simple" cssClass="texts" size="15"/> 
                                        </td>
                                        <td>    
                                            <a href="searchpagembillAction.action?SEARCH_TYPE=1&TXTID=TYPE_CODE<s:property value="%{#ctrlc}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Department")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        </td>   
                                      
                                    </tr>
                                    <s:set id="ctrlc" value="%{#ctrlc+1}"/>
                                    </s:iterator>
				
			</table>
			</td>
                </tr><tr>
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


