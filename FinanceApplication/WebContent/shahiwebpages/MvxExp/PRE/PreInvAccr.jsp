<%-- 
    Document   : InvLineDetails
    Created on : Mar 19, 2015, 10:50:59 AM
    Author     : Sanjeev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


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
<script
	src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/accessoriesdetail.js"></script>

</head>
<body>
	<div id="root" class="root"
		style="left: 50px; top: 200px; display: none; width: 900px; z-index: 10000">
		<table cellpadding="0" cellspacing="0">
			<tr bgcolor="#6699FF">
				<td width="100%">
					<div id="handle" class="handle" style="cursor: move">Details</div>
				</td>
				<td style=""><a href="#" onclick="closediv('root')"><img
						border="0" width="18px" height="18px"
						src="image/chrome_close_button.png" /></a></td>
			</tr>
			<tr>
				<td colspan="2"><iframe name="handlefrm" id="handlefrm" src=""
						scrolling="no" frameborder="0" width="100%" height="300px"></iframe>
				</td>
			</tr>
		</table>
	</div>
	<s:hidden name="inv_date" id="inv_date" value="%{inv_date}" />
	<s:hidden name="planNo" id="planNo" value="%{plan_no}" />
	<s:hidden name="YEAR" id="YEAR" value="%{YEAR}" />
	<s:hidden name="COMPANY" id="COMPANY" value="%{COMPANY}" />
	<s:hidden name="INV_NO" id="INV_NO" value="%{INV_NO}" />
	<s:hidden name="GetDataFlag" id="GetDataFlag" value="%{GetDataFlag}" />
	
  <form name="acc" >
	<table border="0" align="center" bgcolor="#f2f2f2">

		<tr>
			<td class="label-1">Accr Type</td>
			<td style="font-size: 8pt"><s:select 
					id="ACCRTYPE" value="%{ACCR_TYPE_LIST}" list="%{ACCR_TYPE_LIST}"
					theme="simple" headerKey="" cssClass="texts" cssStyle="width:100px"
					headerValue="Type" name="searchBean.accType"/></td>
			<td class="label-1">Dbk SlNo</td>
			<td style="font-size: 8pt"><s:textfield 
					id="DBK_ACCR_COPY" value="%{DBK_ACCR_COPY}" theme="simple" name="searchBean.dbkSlNo"
					cssClass="texts" cssStyle="width:80px" /> <a href="#"
				target="handlefrm" id="dbkaccrhref"
				onclick="calldbk('dbkaccrhref','DBK_ACCR_COPY','DBK_ACCR_COPY');openpop('root')">
					<img width="14px" border=0 height="14px"
					src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
			</a></td>
			<td class="label-1">Str SlNo</td>
			<td style="font-size: 8pt"><s:textfield  name="searchBean.StrSlNo"
					id="STR_ACCR_COPY" value="%{STR_ACCR_COPY}" theme="simple"
					cssClass="texts" cssStyle="width:80px" /> <a href="#"
				target="handlefrm" id="straccrhref"
				onclick="callstrmisc('straccrhref','STR_ACCR_COPY');openpop('root')">
					<img width="14px" border=0 height="14px"
					src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
			</a></td>
			<td class="label-1">Accr Price</td>
			<td style="font-size: 8pt"><s:textfield  name="searchBean.price"
					id="ACCR_RATE_COPY" theme="simple" cssClass="texts"
					cssStyle="width:60px;text-align:right" /></td>

			<td align="center"><input type="submit" name="btn"
				onclick="loadAllLineItems()" value="Copy" /></td>
				<td>
					<s:if test="%{#session.isDisabled}">
						<button id="saveheadId" class="sexybutton"  disabled="true">
							<span><span><span class="save" id="saveId">Save </span></span></span>
						</button>
					</s:if>
					<s:else>
					<button id="saveheadId" class="sexybutton" onclick="saveItems()">
							<span><span><span class="save" id="saveId">Save </span></span></span>
						</button>
					</s:else>		
					</td>	
				
		</tr>
	</table>
	</form>
	<div class="div1"
		style="width: 100.0%; overflow: auto; height: 300.0pt; border-width: 1pt; border-color: whitesmoke; border-style: none">
		
		<table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1"
			cellpadding="2" width="50%" id="tableaaccr">

			<tr class="tr1">
				<th style="font-size: 8pt" align="left" height="25pt">CO No</th>
				<th style="font-size: 8pt" align="left" height="25pt">Line</th>
				<th style="font-size: 8pt" align="left">Item No</th>
				<th style="font-size: 8pt" align="left">Accr Desc</th>
				<th style="font-size: 8pt" align="left">Dbk Sl#</th>
				<th style="font-size: 8pt" align="left">Str Sl#</th>
				<th style="font-size: 8pt" align="right">Qnty</th>
				<th style="font-size: 8pt" align="right">Price</th>
				<th></th>
			</tr>
			<tbody>
				<%-- <s:set id="ctn" name="ctn" value="0" /> --%>
<%-- 				<s:hidden name="aausrid" value="%{#session['sessUserId']}"/>
 --%>				<s:iterator status="cnt" value="map">
				<s:iterator value="value" status="listid">
				   <s:if test="%{ACCR_CO_NO!=null}">
					<tr id="row<s:property value="%{#listid.index}"/>" bgcolor="#FFFFFF">
						<td style="font-size: 8pt"><s:textfield name="AccrLineList[%{#listid.index}].ACCR_CO_NO"
								readonly="true" value="%{ACCR_CO_NO}" id="ACCR_CO_NO%{#ctn}"
								theme="simple" cssClass="texts" cssStyle="width:100px" /></td>
						<td style="font-size: 8pt"><s:textfield name="AccrLineList[%{#listid.index}].ACCR_CO_LINE"
								readonly="true" value="%{ACCR_CO_LINE}" id="ACCR_CO_LINE%{#listid.index}"
								theme="simple" cssClass="texts" cssStyle="width:60px" /></td>
						<td style="font-size: 8pt"><s:textfield name="AccrLineList[%{#listid.index}].ACCR_ITEM_NO"
								readonly="true" value="%{ACCR_ITEM_NO}" id="ACCR_ITEM_NO%{#listid.index}"
								theme="simple" cssClass="texts" cssStyle="width:80px" /></td>
						<td style="font-size: 8pt"><s:textfield name="AccrLineList[%{#listid.index}].ACCR_TYPE"
								readonly="true" value="%{ACCR_TYPE}" id="ACCR_TYPE%{#listid.index}"
								theme="simple" cssClass="texts" cssStyle="width:100px" /></td>
						<td style="font-size: 8pt"><s:textfield name="AccrLineList[%{#listid.index}].ACCR_DBKSLNO"
								value="%{ACCR_DBKSLNO}" id="ACCR_DBKSLNO%{#listid.index}" theme="simple"
								cssClass="texts" cssStyle="width:80px" /></td>
						<td style="font-size: 8pt"><s:textfield name="AccrLineList[%{#listid.index}].ACCR_STRSLNO"
								value="%{ACCR_STRSLNO}" id="ACCR_STRSLNO%{#listid.index}" theme="simple"
								cssClass="texts" cssStyle="width:80px" /></td>
						<td style="font-size: 8pt" align="right"><s:textfield
								name="AccrLineList[%{#listid.index}].ACCR_QTY" readonly="true" value="%{ACCR_QTY}"
								theme="simple" cssClass="texts" id="ACCR_QTY%{#listid.index}"
								cssStyle="width:60px;text-align:right" /></td>
						<td style="font-size: 8pt" align="right"><s:textfield
								name="AccrLineList[%{#listid.index}].ACCR_PRICE" readonly="true" value="%{ACCR_PRICE}"
								theme="simple" cssClass="texts" id="ACCR_PRICE%{#listid.index}"
								cssStyle="width:60px;text-align:right" /></td>

						<td style="width: 12px" class="label-1"><input type="button"
							id="<s:property value="%{#listid.index}"/>" class="texts" tabindex="-1"
							style="font-size: 10px; margin: 0px; padding: 0px" name="btn"
							onclick="deletetablerowaccr('<s:property value="%{ACCR_TYPE}"/>','<s:property value="%{#listid.index}"/>')" value="X"></td>

					
						</tr>
						</s:if>
				</s:iterator>
                </s:iterator>
				<s:hidden id="accrctn" name="accrctn" value="%{#ctn}" />

			</tbody>

		</table>
		<s:actionerror/>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/shahiwebpages/js/dom-drag.js"></script>
	<script language="javascript">
	var theHandle = document.getElementById("handle");
	var theRoot = document.getElementById("root");
	Drag.init(theHandle, theRoot);
</script>
</body>
</html>
