<%-- 
    Document   : InvLineDetails
    Created on : Mar 19, 2015, 10:50:59 AM
    Author     : Sanjeev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/css/sexybuttons.css" />
<title>JSP Page</title>
<script
	src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/validatebilling.js"></script>
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

	<s:hidden name="DESTI_CNTRY" id="DESTI_CNTRY" value="%{DESTI_CNTRY}" />
	<s:hidden name="inv_date" id="inv_date" value="%{inv_date}" />
	<s:hidden name="dt_plan_no" id="dt_plan_no" value="%{plan_no}" />
	
	<form name="detail">
		<table>
			<tr>
				<td style="font-size: 8pt" align="left" height="25pt"
					class="label-1">Catg/Desc</td>
				<td style="font-size: 8pt" align="left" height="25pt"><s:textfield
						name="CATG_CODE_COPY" id="CATG_CODE_COPY"
						value="%{CATG_CODE_COPY}" theme="simple" cssClass="texts"
						onblur="if(this.value!='') return xmlhttpreqcatg(this,'CATG_DESC_COPY','%{DESTI_CNTRY}')"
						tabindex="1" cssStyle="width:30px" /> <a href="#"
					target="handlefrm" id="catghref"
					onclick="callcatg('catghref','CATG_CODE_COPY','CATG_DESC_COPY');openpop('root')">
						<img width="14px" border=0 height="14px"
						src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
				</a></td>

				<td style="font-size: 8pt" align="left"><s:textfield
						name="CATG_DESC_COPY" id="CATG_DESC_COPY"
						value="%{CATG_DESC_COPY}" theme="simple" cssClass="texts"
						cssStyle="width:300px;text-transform:uppercase" tabindex="2" /></td>
				<td class="label-1">ShipType</td>
				<td style="font-size: 8pt"><s:select name="SHIP_TYPE_COPY"
						value="%{SHIP_TYPE_COPY}" list="%{SHIP_TYPE_LIST}"
						id="SHIP_TYPE_CODE" theme="simple" cssStyle="width:60px"
						headerKey="" cssClass="texts" headerValue="Type" tabindex="3" /></td>
				<td class="label-1">Scheme</td>
				<td style="font-size: 8pt"><s:select name="SCHEME_CODE_COPY"
						id="SCHEME_CODE_COPY" value="%{SCHEME_CODE_COPY}"
						list="%{SCHEME_CODE_LIST}" theme="simple" cssStyle="width:60px"
						headerKey="" headerValue="Scheme" listKey="LIST_CODE"
						listValue="LIST_NAME" cssClass="texts" tabindex="4" /></td>
				<td class="label-1">DBK #</td>
				<td style="font-size: 8pt"><s:textfield name="DBK_SLNO_COPY"
						value="%{DBK_SLNO_COPY}" theme="simple" cssClass="texts"
						cssStyle="width:80px"
						onblur="if(this.value!='') return xmlhttpreqdbkslno(this,'DBK_SLNO_COPY')"
						tabindex="5" /> <a href="#" target="handlefrm" id="dbkslhref"
					onclick="calldbk('dbkslhref','DBK_SLNO_COPY');openpop('root')">
						<img width="14px" border=0 height="14px"
						src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
				</a></td>
				<td class="label-1">ROSL#</td>
				<td style="font-size: 8pt"><s:textfield name="ROSL_SLNO_COPY"
						id="ROSL_SLNO_COPY" value="%{ROSL_SLNO_COPY}" theme="simple"
						cssClass="texts" cssStyle="width:80px"
						onblur="if(this.value!='') return rosltest(this,'ROSL_SLNO_COPY')"
						tabindex="6" /> <a href="#" target="handlefrm" id="roslslhref"
					onclick="callrosl('roslslhref','ROSL_SLNO_COPY');openpop('root')">
						<img width="14px" border=0 height="14px"
						src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
				</a></td>
				<td class="label-1">STR#</td>
				<td style="font-size: 8pt"><s:textfield name="STR_SLNO_COPY"
						value="%{STR_SLNO_COPY}" theme="simple" cssClass="texts"
						cssStyle="width:40px;text-transform:uppercase"
						onblur="if(this.value!='') return xmlhttpreqstrslno(this,'STR_SLNO_COPY')"
						tabindex="7" /> <a href="#" target="handlefrm" id="strslhref"
					onclick="callstr('strslhref','STR_SLNO_COPY');openpop('root')">
						<img width="14px" border=0 height="14px"
						src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
				</a></td>

				<td class="label-1">Misc Str</td>
				<td style="font-size: 8pt"><s:textfield name="STR_MISC_COPY"
						value="%{STR_MISC_COPY}" theme="simple" cssClass="texts"
						cssStyle="width:40px"
						onblur="if(this.value!='') return xmlhttpreqstrmisc(this,'STR_MISC_COPY')"
						tabindex="7" /> <a href="#" target="handlefrm" id="strmischref"
					onclick="callstrmisc('strmischref','STR_MISC_COPY');openpop('root')">
						<img width="14px" border=0 height="14px"
						src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
				</a></td>


				<td><input type="button" name="btn" onclick="copyCatg()"
					value="CP All" /></td>
				<td>
				    <s:if test="%{#session.isDisabled}">
						<button id="saveheadId" class="sexybutton"  disabled="true">
							<span><span><span class="save" id="saveId">Save </span></span></span>
						</button>
					</s:if>
					<s:else>
					<button id="saveheadId" class="sexybutton" onclick="savedetail()">
							<span><span><span class="save" id="saveId">Save </span></span></span>
						</button>
					</s:else>
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td class="label-1">Hngr Cost</td>
				<td style="font-size: 8pt"><s:textfield name="PRICE_MISC_COPY"
						id="PRICE_MISC_COPY" value="0.0" theme="simple" cssClass="texts"
						onblur="validatenumber(this)" cssStyle="width:60px" tabindex="8" />
				</td>
				<td class="label-1">Tag Cost</td>
				<td style="font-size: 8pt"><s:textfield name="PRICE_TAG_COPY"
						id="PRICE_TAG_COPY" value="0.0" theme="simple" cssClass="texts"
						onblur="validatenumber(this)" cssStyle="width:60px" tabindex="8" />
				</td>

				<td style="font-size: 8pt" class="label-1">Adjst FC</td>
				<td><s:textfield name="ADJUST_FC_COPY" id="ADJUST_FC_COPY"
						value="0.0" theme="simple" cssClass="texts"
						onblur="validatenumber(this)" cssStyle="width:80px" /></td>
				<td class="label-1">HS #</td>
				<td style="font-size: 8pt"><s:textfield name="HS_CODE_COPY"
						value="%{HS_CODE_COPY}" theme="simple" cssClass="texts"
						cssStyle="text-transform:uppercase;width:80px" tabindex="5" /></td>
			</tr>

		</table>
	</form>
	<div class="div1"
		style="width: 100.0%; overflow: auto; height: 320.0pt; border-width: 1pt; border-color: whitesmoke; border-style: none">

		<s:hidden name="YEAR" value="%{YEAR}" />
		<s:hidden name="COMPANY" value="%{COMPANY}" />
		<s:hidden name="INV_NO" value="%{INV_NO}" />
		<table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1"
			cellpadding="1" width="100%" id="tablea">

			<tr class="tr1">
				<th style="font-size: 8pt" align="left" height="25pt">Sl#</th>
				<th style="font-size: 8pt" align="left" height="25pt">Item No.</th>
				<th style="font-size: 8pt" align="left">Unit</th>
				<th style="font-size: 8pt" align="right">Inv Qty</th>
				<th style="font-size: 8pt" align="right">Qty Kgs</th>
				<th style="font-size: 8pt" align="right">Movex Price</th>
				<th style="font-size: 8pt" align="right">Price FC</th>
				<th style="font-size: 8pt" align="right">Hngr</th>
				<th style="font-size: 8pt" align="right">Tag</th>
				<th style="font-size: 8pt" align="right">Adjust Fc</th>
				<th style="font-size: 8pt" align="right">Net Price</th>
				<th style="font-size: 8pt" align="right">Fob Fc</th>
				<th style="font-size: 8pt" align="right">Gr Decl</th>
				<th style="font-size: 8pt" align="left">ShipType</th>
				<th style="font-size: 8pt" align="left">Dbk Sl#</th>
				<th style="font-size: 8pt" align="left">Rosl Sl#</th>
				<th style="font-size: 8pt" align="left">Str#</th>
				<th style="font-size: 8pt" align="left">MiscStr</th>
				<th style="font-size: 8pt" align="left">Catg</th>
				<th style="font-size: 8pt" align="left">CP</th>
				<th style="font-size: 8pt" align="left">Invoice Desc</th>
				<th style="font-size: 8pt" align="left">HS Code</th>
				<th style="font-size: 8pt" align="left">Scheme</th>
				<th style="font-size: 8pt" align="left">Plan Desc</th>
			</tr>
			<tbody>
				<s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="0" />
				<s:set id="TOT_QTY_KGS" name="TOT_QTY_KGS" value="0" />
				<s:set id="TOT_FOB_FC" name="TOT_FOB_FC" value="0" />
				<s:set id="TOT_GR_DECL_AMT" name="TOT_GR_DECL_AMT" value="0" />


				<s:iterator value="InvLineList" status="listid">

					<tr bgcolor="#f2f9fb">
						<td style="font-size: 8pt"><s:property
								value="%{#listid.index+1}" /></td>
						<td style="font-size: 8pt; width: 40px"><s:textfield
								name="item" theme="simple" cssClass="textreadonly"
								cssStyle="width:40pt;font-size:9pt;" value="%{ITEM_NO}" /></td>

						<s:hidden name="CO_NO_E" value="%{CO_NO}" />
						<s:hidden name="SR_NO" value="%{SR_NO}" />
						<s:hidden name="CO_LINE_E" value="%{CO_LINE}" />
						<s:hidden name="ITEM_NO_E" value="%{ITEM_NO}" />
						<s:hidden name="QTY_ENDORS_E" value="%{QTY_ENDORS}" />
						<s:hidden name="CO_UOM_E" value="%{UNIT}" />
						<s:hidden name="PRE_PRINT_NO" value="%{PRE_PRINT_NO}" />
						<s:hidden name="TOKEN_NO" value="%{TOKEN_NO}" />
						<s:hidden name="GR_DECL_PER" value="%{GR_DECL_PER}" />
						<s:hidden name="TEMP_CAT" value="%{TEMP_CAT}" />
						<s:hidden name="HSN_CODE_E" value="%{HSN_CODE}" />
						<s:hidden name="IGST_PER_E" value="%{IGST_PER}" />
						<s:hidden name="CGST_PER_E" value="%{CGST_PER}" />
						<s:hidden name="SGST_PER_E" value="%{SGST_PER}" />
						<td style="font-size: 8pt; width: 30px"><s:property
								value="UNIT" /></td>
						<td style="font-size: 8pt; width: 40px;" align="right"><s:property
								value="QTY_ENDORS" /> <s:set id="TOT_QTY_ENDORS"
								name="TOT_QTY_ENDORS" value="%{#TOT_QTY_ENDORS+QTY_ENDORS}" /></td>
						<td style="font-size: 8pt; width: 40px" align="right"><s:textfield
								name="QTY_KGS" value="%{QTY_KGS}" theme="simple"
								cssStyle="width:40px;text-align:right" cssClass="texts"
								id="QTY_KGS%{#listid.index}" /> <s:set id="TOT_QTY_KGS"
								name="TOT_QTY_KGS" value="%{#TOT_QTY_KGS+QTY_KGS}" /></td>
						<td style="font-size: 8pt; width: 40px" align="right"><s:property
								value="MOVEX_PRICE" /> <s:hidden name="PRICE_FC_MOVEX"
								id="PRICE_FC_MOVEX%{#listid.index}" value="%{MOVEX_PRICE}" /></td>
						<td style="font-size: 8pt; width: 40px" align="right"><s:if
								test="%{PRICE_FC_E==null}">
								<s:textfield name="PRICE_FC_E" value="%{PRICE_FC}"
									theme="simple" cssClass="texts" id="PRICE_FC_E%{#listid.index}"
									onblur="validatenumber(this)"
									cssStyle="width:40px;text-align:right" />
							</s:if> <s:else>

								<s:textfield name="PRICE_FC_E" value="%{PRICE_FC_E}"
									theme="simple" cssClass="texts" id="PRICE_FC_E%{#listid.index}"
									onblur="validatenumber(this)"
									cssStyle="width:40px;text-align:right" />
							</s:else></td>
						<td style="font-size: 8pt; width: 40px" align="right"><s:textfield
								name="HNGR_COST" value="%{HNGR_COST}" theme="simple"
								cssClass="texts" id="HNGR_COST%{#listid.index}"
								onblur="validatenumber(this)"
								cssStyle="width:40px;text-align:right" /></td>
						<td style="font-size: 8pt; width: 40px" align="right"><s:textfield
								name="TAG_COST" value="%{TAG_COST}" theme="simple"
								cssClass="texts" id="TAG_COST%{#listid.index}"
								onblur="validatenumber(this)"
								cssStyle="width:40px;text-align:right" /></td>
						<td style="font-size: 8pt; width: 40px" align="right"><s:textfield
								name="ADJUST_FC" id="ADJUST_FC%{#listid.index}"
								value="%{ADJUST_FC}" theme="simple" cssClass="texts"
								cssStyle="width:40px;text-align:right" /></td>
						<td style="font-size: 8pt; width: 40px" align="right"><s:property
								value="NET_PRICE" /> <s:hidden name="NET_PRICE_E"
								value="%{NET_PRICE}" /></td>
						<td style="font-size: 8pt; width: 60px" align="right"><s:textfield
								name="FOB_FC" value="%{FOB_FC}" theme="simple" cssClass="texts"
								cssStyle="width:60px;text-align:right" /> <s:set
								id="TOT_FOB_FC" name="TOT_FOB_FC" value="%{#TOT_FOB_FC+FOB_FC}" /></td>
						<td style="font-size: 8pt; width: 50px" align="right"><s:property
								value="GR_DECL_AMT" /> <s:hidden name="GR_DECL_AMT_E"
								value="%{GR_DECL_AMT}" /> <s:set id="TOT_GR_DECL_AMT"
								name="TOT_GR_DECL_AMT" value="%{#TOT_GR_DECL_AMT+GR_DECL_AMT}" />
						</td>
						<td style="font-size: 8pt; width: 30px"><s:select
								name="MADE_FOR" value="%{MADE_FOR}" list="%{SHIP_TYPE_LIST}"
								id="MADE_FOR%{#listid.index}" theme="simple" headerKey=""
								cssClass="texts" headerValue="Type" cssStyle="width:50px" /></td>
						<td style="font-size: 8pt; width: 80px"><s:textfield
								name="DBK_SLNO" value="%{DBK_SLNO}" theme="simple"
								cssClass="texts" id="DBK_SLNO%{#listid.index}" readonly="true"
								cssStyle="width:60px" /> <a href="#" target="handlefrm"
							id="dbkslhref<s:property value="%{#listid.index}"/>"
							onclick="calldbk('dbkslhref<s:property value="%{#listid.index}"/>','DBK_SLNO<s:property value="%{#listid.index}"/>');openpop('root')">
								<img width="14px" border=0 height="14px"
								src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
						</a></td>
						<td style="font-size: 8pt; width: 80px"><s:textfield
								name="ROSL_SLNO" value="%{ROSL_SLNO}" theme="simple"
								cssClass="texts" id="ROSL_SLNO%{#listid.index}" readonly="true"
								cssStyle="width:60px" /> <a href="#" target="handlefrm"
							id="roslslhref<s:property value="%{#listid.index}"/>"
							onclick="callrosl('roslslhref<s:property value="%{#listid.index}"/>','ROSL_SLNO<s:property value="%{#listid.index}"/>');openpop('root')">
								<img width="14px" border=0 height="14px"
								src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
						</a></td>
						<td style="font-size: 8pt; width: 40px"><s:textfield
								name="STR_SLNO" value="%{STR_SLNO}" readonly="true"
								theme="simple" cssClass="texts" id="STR_SLNO%{#listid.index}"
								cssStyle="width:20px" /> <a href="#" target="handlefrm"
							id="strslhref<s:property value="%{#listid.index}"/>"
							onclick="callstr('strslhref<s:property value="%{#listid.index}"/>','STR_SLNO<s:property value="%{#listid.index}"/>');openpop('root')">
								<img width="14px" border=0 height="14px"
								src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
						</a></td>
						<td style="font-size: 8pt; width: 60px"><s:textfield
								name="STR_MISC" value="%{STR_MISC}" theme="simple"
								cssClass="texts" id="STR_MISC%{#listid.index}"
								cssStyle="width:40px" /> <a href="#" target="handlefrm"
							id="strmischref<s:property value="%{#listid.index}"/>"
							onclick="callstrmisc('strmischref<s:property value="%{#listid.index}"/>','STR_MISC<s:property value="%{#listid.index}"/>');openpop('root')">
								<img width="14px" border=0 height="14px"
								src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
						</a></td>
						<td style="font-size: 8pt; width: 40px"><s:textfield
								name="CATG_CODE" value="%{CATG_CODE}" theme="simple"
								cssClass="texts" id="CATG_CODE%{#listid.index}"
								cssStyle="width:20px" /> <a href="#" target="handlefrm"
							id="catghref<s:property value="%{#listid.index}"/>"
							onclick="callcatg('catghref<s:property value="%{#listid.index}"/>','CATG_CODE<s:property value="%{#listid.index}"/>','INV_DESC<s:property value="%{#listid.index}"/>');openpop('root')">
								<img width="14px" border=0 height="14px"
								src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" />
						</a></td>
						<td style="font-size: 8pt; width: 20px"><input
							type="checkbox" value="Y" name="cpselect"
							onclick="copySelected(<s:property value="%{#listid.index}"/>)" /></td>
						<td style="width: 200px"><s:textfield name="INV_DESC"
								value="%{INV_DESC}" theme="simple" cssClass="texts"
								id="INV_DESC%{#listid.index}"
								cssStyle="width:200px;text-transform:uppercase" /></td>
						<td style="font-size: 8pt; width: 60px"><s:textfield
								name="HSCODE1" value="%{HSCODE1}" theme="simple"
								cssClass="texts" id="HSCODE1%{#listid.index}" readonly="true"
								cssStyle="text-transform:uppercase;width:60px" /></td>
						<td style="width: 60px"><s:select name="SCHEME_CODE"
								id="SCHEME_CODE%{#listid.index}" value="%{SCHEME_CODE}"
								list="%{SCHEME_CODE_LIST}" theme="simple" headerKey=""
								headerValue="Scheme" listKey="LIST_CODE" listValue="LIST_NAME"
								cssClass="texts" cssStyle="width:60px" /></td>
						<td style="width: 80px"><s:textfield name="PLAN_DESC"
								value="%{PLAN_DESC}" theme="simple" cssClass="texts"
								id="INV_DESC%{#listid.index}"
								cssStyle="width:80px;text-transform:uppercase" /></td>
					</tr>
				</s:iterator>

			</tbody>
			<tr bgcolor="#f2f9fb">
				<td style="font-size: 8pt" colspan="3" align="right" class="label-1">Total</td>
				<td
					style="font-size: 8pt; color: #9400D3; font-weight: bold; width: 40px;"
					align="right"><s:property value="%{#TOT_QTY_ENDORS}" /> <s:hidden
						name="QTYTOTAL" id="QTYTOTAL" value="%{#TOT_QTY_ENDORS}" /></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"><s:property value="%{#TOT_QTY_KGS}" /></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<s:text name="product.eff" var="fcvalue">
					<s:param name="fcvalue" value="%{TOT_FOB_FC}" />
				</s:text>
				<s:text name="product.eff" var="grdecl">
					<s:param name="grvalue" value="%{TOT_GR_DECL_AMT}" />
				</s:text>
				<td
					style="font-size: 7pt; color: #9400D3; font-weight: bold; width: 60px"
					align="right"><s:property value="%{#fcvalue}" /> <s:hidden
						name="FCTOTAL" id="FCTOTAL" value="%{#fcvalue}" /></td>
				<td
					style="font-size: 7pt; color: #9400D3; font-weight: bold; width: 50px"
					align="right"><s:property value="%{#grdecl}" /></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt" align="right"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
				<td style="font-size: 8pt"></td>
			</tr>
		</table>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/shahiwebpages/js/dom-drag.js"></script>
	<%-- <script
	src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/jquery.table.addrow.js"></script> --%>
	<script
		src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/mvxexp.js"></script>
	<script
		src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/copyall.js"></script>
	<script language="javascript">
		var theHandle = document.getElementById("handle");
		var theRoot = document.getElementById("root");
		Drag.init(theHandle, theRoot);
	</script>
</body>
</html>
