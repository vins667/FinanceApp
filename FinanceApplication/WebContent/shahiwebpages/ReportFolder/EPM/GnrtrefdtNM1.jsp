<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="<s:url value="css/main_gn.css"/>" rel="stylesheet" type="text/css"/>
<title>Shahi Exports Pvt Ltd</title>
</head>
<s:if test="%{ERRORMSG!=null && ERRORMSG.length()>0}">
	<script type="text/javascript">
		alert("<s:property value='%{ERRORMSG}'/>")
	</script>
</s:if>
<body onload="addAmount();">
	<table>
		<tr>
			<td style="text-align: left;width:130px">
				<s:textfield name="BOA" id="BOA_ID%{textid}" value="%{T_BOA}" theme="simple" cssStyle="width:126px;" cssClass="textreadonly"  readonly="true"></s:textfield>
			</td>
			<td style="text-align: left;width:130px">
				<s:textfield name="VCH_NO" id="VCH_NO_ID%{textid}" value="%{T_VCH_NO}" theme="simple" cssStyle="width:126px;" cssClass="textreadonly"  readonly="true"></s:textfield>
			</td>
			<td style="text-align: left;width:130px">
				<s:textfield name="STATUS" id="STATUS_ID%{textid}" value="%{T_STATUS}" theme="simple" cssStyle="width:126px;" cssClass="textreadonly"  readonly="true"></s:textfield>
			</td>
			<td style="text-align: left;width:130px">
				<s:textfield name="SUPPLIER" id="SUPPLIER_ID%{textid}" value="%{T_SUPPLIER}" theme="simple" cssStyle="width:126px;" cssClass="textreadonly"  readonly="true"></s:textfield>
				<s:hidden id="PFSCT_ID%{textid}"  name="PFSCT" value="%{T_PFSCT}" cssStyle="width:126px;" onkeypress="return tabE(this,event)" style="text-align:right" readonly="readonly"/>
			</td>
			<td style="text-align: left;width:130px">
				<s:textfield name="CHQ_AMT" id="CHQ_AMT_ID%{textid}" value="%{T_CHQ_AMT}" theme="simple" cssStyle="width:126px;"  cssClass="textreadonly"  readonly="true"></s:textfield>
			</td>
			<td style="text-align: left;width:130px">
				<s:select  list="#{'':'SELECT', 'RT':'RTGS', 'NE':'NEFT', 'CC':'CORP. CHQ.','DD':'DD','FT':'BANK TO BANK'}" name="SELECT_TYPE" id="SELECT_TYPE_ID%{textid}" cssStyle="width:126px;" theme="simple" onchange="addAmount();validatecpumt('%{textid}',this.value,this)"></s:select>
			</td>
			
			
		</tr>
	</table>
</body>
</html>