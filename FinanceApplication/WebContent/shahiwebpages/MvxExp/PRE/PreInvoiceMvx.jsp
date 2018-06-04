<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<sj:head jqueryui="true"></sj:head>
<%-- <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/shahicss/common.css" /> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/css/sexybuttons.css" />
<style type="text/css">
th {
	font-size: 9pt;
	font-weight: bold;
	color: #0066aa;
	background-image: url('../../image/table-gradient.jpg');
}

.root {
	position: absolute;
	height: 200px;
	width: 800px;
	background-color: #F4F4F4;
	border: 1px solid #2a6afe;
	padding: 0px;
	margin: 0px
}

.handle {
	margin: 0px;
	padding: 0px;
	width: 100%;
	color: white;
	font-weight: bold;
	font-size: 12px
}

.ui-tabs .ui-tabs-panel {
	display: block;
	border-width: 0;
	padding: 0.5em 0.5em;
	background: none;
}
</style>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/dom-drag.js"></script>
 <script src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/jquery.js"></script>
 --%>
<script
	src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/jquery.table.addrow.js"></script>
<script
	src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/mvxexp.js"></script>

	<script
	src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/js/validatelic.js"></script>
</head>
<body>
	<div align="center" id="prepage" class="lodingdiv"
		style="right: 400px; position: absolute; z-index: 1; visibility: hidden; background: transparent; top: 50px;">
		<img
			src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/PRE/images/pleaseWaitOverlay.gif" />
	</div>
	<table border="1" cellpadding="5" cellspacing="1" width="100%">
		<tr bgcolor="#6a5acd">
			<td align="center"
				style="width: 90.0%; font-size: 18.0pt; font-weight: bold; font-family: Garamond; font-style: italic; color: rgb(255, 255, 255)">
				Pre Shipment Invoice [M4] <input type="hidden" name="rownum"
				id="rownum" value="0" />
			</td>
		</tr>
	</table>
	<sj:tabbedpanel id="localtabs" selectedTab="0" show="true"
		hide="'fade'" collapsible="false" sortable="false" cssClass="list">
		<sj:tab id="tabbar0" target="tab0" label="Header" title="Header" />
		<div title="" id="tab0" style="overflow: hidden;">
			<form action="" method="post" name="frm">
				<table width="100%" cellpadding="0" cellspacing="0">

					<tr>
						<td>
							<table align="left" width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table border="1" align="center" bgcolor="#cccccc"
											cellspacing="1" cellpadding="2" width="100%" id="tablea">
											<tr bgcolor="#c0c0c0">
												<td class="label-1">Loct : <s:textfield
														name="searchloct" readonly="true"
														cssStyle="text-transform:uppercase;width:40pt"
														value="%{searchloct}" theme="simple" maxLength="10" /></td>
												<td class="label-1">CI No<s:textfield name="ciNo"
														cssStyle="text-transform:uppercase;width:80pt"
														value="%{ciNo}" theme="simple" maxLength="10" /></td>
												<td class="label-1">Invoice No : <s:textfield
														name="searchinv"
														cssStyle="text-transform:uppercase;width:80pt"
														theme="simple" maxLength="10" /></td>
												<td class="label-1">Plan No : <s:textfield
														name="searchplan" id="searchplan"
														cssStyle="text-transform:uppercase;width:70pt"
														theme="simple"
														onblur="if(this.value!='') return xmlhttpreqGSTCMP(this,'PLACE_DESC')"
														maxLength="10" />
												</td>
												<td align="right" style="width: 400px">
													<button id="searchheadId" class="sexybutton"
														onclick="searchdetail()">
														<span><span><span class="search"
																id="searchId">Go</span></span></span>
													</button>
													<button id="clearheadId" class="sexybutton" href="#"
														onclick="javascript:window.location.href('PREINVMVX.action?aausrid=<s:property value="%{aausrid}"/>');">
														<span><span><span class="reload"
																id="clearId">Clear</span></span></span>
													</button>
													<button id="deleteheadId" class="sexybutton"
														onclick="javascript:window.close()">
														<span><span><span class="delete"
																id="deleteId">Exit</span></span></span>
													</button> <s:if 
														test='%{ship_cancel.equals("YES") || fwd_date!=null}'>
														<button id="saveheadId" disabled="true" class="sexybutton"
															onclick="saverec()">
															<span><span><span class="save" id="saveId">Save</span></span></span>
														</button>
													</s:if> <s:else>
														<button id="saveheadId" class="sexybutton" 
															onclick="saverec()">
															<span><span><span class="save" id="saveId">Save</span></span></span>
														</button>
													</s:else> <s:if test='%{CRNCY_CODE!=null }'>
														<button id="printheadId" class="sexybutton"
															onclick="javascript:window.location.href('printPreInv.jsp?P_INV=<s:property value="%{searchinv}"/>&CRNCY=<s:property value="%{CRNCY_CODE}"/>&P_DATE=<s:property value="%{sdate}"/>&LUT=<s:property value="%{LUT_IGST}"/>');">
															<span><span><span class="print"
																	id="printId">Pdf</span></span></span>
														</button>
													</s:if>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table bgcolor="#f2f2f2" style="margin-top: 0pt;"
											align="center" width="100%" cellpadding="0" cellspacing="0">
											<tr>
												<td style="font-size: 8pt" align="left" class="label-1">Plan
													No</td>
												<td style="font-size: 8pt" align="left"><s:textfield
														name="header.planNo" readonly="true" cssClass="textreadonly"
														cssStyle="width:60pt;text-align:right; font-size:9pt;"
														theme="simple" value="%{header.planNo}" /> <s:if
														test='%{GetDataFlag.equals("NO") && fwd_custom==null}'>
														<input type="button" name="bnt" style="width: 50pt"
															onclick="GetMovexData()" value="Refresh"
															class="submitbutton">
													</s:if></td>
												<td style="font-size: 8pt" align="left" class="label-1">AC
													Holder</td>
												<td class="label-1" style="font-size: 8pt" align="left">
													<s:textfield id="ac_holder" name="header.accountHolder"
														cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;"
														theme="simple" value="%{header.accountHolder}"
														onblur="if(this.value!='') return xmlhttpreqacholder(this)"
														tabindex="3" /> <a
													href="GruptypeViewPREINVMVX.action?PARAA=ac_holder&PARAB=ac_holder&TYPE_CODE=AHN"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a>
												</td>
												<td style="font-size: 8pt" align="left" class="label-1">PPRQ
													Date</td>
												<td class="label-1" style="font-size: 9pt" align="left">
													<s:if test='%{header.pprqDate==null}'>
														<input type="checkbox" value="Y" name="header.pprqDate" />
													</s:if> <s:else>
														<s:checkbox name="pprq1" disabled="true" checked="true"
															theme="simple" />
														<s:property value="pprq_date" />
													</s:else>
												</td>
												<td align="left" class="label-1">Ship Cnxl</td>
												<td style="font-size: 8pt" align="left"><s:if
														test='%{header.shipCnxl.equals("YES") }'>
														<input type="checkbox" name="shipCnxl" disabled="true"
															checked="true" style="height: 9pt" />
													</s:if> <s:elseif test='%{customfwd_auth.equals("YES")}'>
														<input type="checkbox" value="Y" name="header.shipCnxl"
															id="ship_cancel" />
													</s:elseif> <s:else>
														<input type="checkbox" name="header.shipCnxl" disabled="true"
															style="height: 9pt" />
													</s:else></td>
												<td style="font-size: 8pt" class="label-1" align="left">Place</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.place" id="PLACE"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.place}"
														onblur="if(this.value!='') return xmlhttpreqplace(this,'PLACE_DESC')"
														tabindex="19" /> <a
													href="CsytabViewPREINVMVX.action?PARAA=PLACE&PARAB=PLACE_DESC&TYPE_CODE=EDES"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a> <s:textfield id="PLACE_DESC"
														name="PLACE_DESC" readonly="true" cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{PLACE_DESC}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Invoice
													No</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.invoiceNo" readonly="true" cssClass="textreadonly" id="invoiceNo"
														cssStyle="width:60pt;font-size:9pt;text-align:right;"
														theme="simple" value="%{header.invoiceNo}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Merchant</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.merchant"
														cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;"
														theme="simple" value="%{header.merchant}"
														onblur="if(this.value!='') return xmlhttpreqmerchent(this)"
														tabindex="4" /> <a
													href="GruptypeViewPREINVMVX.action?PARAA=merchant&PARAB=merchant&TYPE_CODE=MER"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" /></a></td>


												<td style="font-size: 8pt" class="label-1" align="left">Fwd
													Custom</td>
												<td class="label-1" style="font-size: 8pt" align="left">
												    <s:hidden name="YEAR" value="%{YEAR}" id="YEAR" />
												     <s:hidden name="COMPANY" value="%{COMPANY}" id="COMPANY"/>
													<s:if test='%{header.fwdToCustom==null}'>
														<input type="checkbox" value="Y" name="header.fwdToCustom"
															id="fwd_custom"
															onclick="return xmlhttpreqCOAPRV('<s:property value="%{YEAR}"/>','<s:property value="%{COMPANY}"/>','<s:property value="%{INV_NO}"/>')" />
													</s:if> <s:elseif test='%{customfwd_auth.equals("YES")}'>
														<input type="checkbox" checked="true" disabled="true" value="Y"
															 id="fwd_custom" />
															 <s:hidden name="header.fwdToCustomDate"/>
															 <s:hidden name="header.fwdToCustom"/>
														<s:property value="header.fwdToCustomDate" />& Removed :<input
															type="checkbox" value="NN" name="header.removed" />
													</s:elseif> <s:else>
														<s:checkbox name="fwd1" disabled="true" checked="true"
															theme="simple" />
														<s:property value="header.fwdToCustomDate`" />
													</s:else>

												</td>
												<td align="left" class="label-1">Out House</td>
												<td style="font-size: 8pt" align="left"><s:if
														test='%{outhouse!=null}'>

														<input type="checkbox" checked="true" value="Y"
															disabled="true" style="height: 9pt" />
														<s:hidden name="header.outHouse" value="%{header.outHouse}" />
													</s:if> <s:else>
														<input type="checkbox" value="Y" name="header.outHouse"
															style="height: 9pt" />
													</s:else></td>
												<td style="font-size: 8pt" class="label-1" align="left">Clearing
													Port</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="CLR_PORT" name="header.clearingPort"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.clearingPort}"
														onblur="if(this.value!='') return xmlhttpreqclearingport(this,'CLR_PORT_DESC')"
														tabindex="20" /> <a
													href="CsytabViewPREINVMVX.action?PARAA=clearingPort&PARAB=CLR_PORT_DESC&TYPE_CODE=HAFE"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a> <s:textfield id="CLR_PORT_DESC"
														name="CLR_PORT_DESC" readonly="true"
														cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{CLR_PORT_DESC}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Invoice
													Date</td>
												<td class="label-1" style="font-size: 9pt;"><s:textfield
														name="header.invoiceDate" readonly="true" id="inv_date"
														cssClass="textreadonly"
														cssStyle="width:60pt;font-size:9pt;" theme="simple"
														value="%{header.invoiceDate}" /> <s:textfield name="header.invoiceState"
														readonly="true" id="inv_state" cssClass="textreadonly"
														cssStyle="width:20pt;font-size:9pt;" theme="simple"
														value="%{header.invoiceState}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">LC
													No</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.lcNo"
														cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;"
														theme="simple" value="%{header.lcNo}"
														onblur="if(this.value!='') return xmlhttpreqlcview(this)"
														tabindex="5" /> <a
													href="lcViewPREINVMVX.action?PARAA=lcNo&PARAB=lcNo&TYPE_CODE=lcNo"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a></td>
												<td style="font-size: 8pt" class="label-1" align="left">TTO
													Date</td>
												<td class="label-1" style="font-size: 9pt" align="left">
													<s:if test='%{tto_date==null}'>
														<input type="checkbox" value="Y" name="header.ttoDate"
															id="tto_date" />

													</s:if> <s:else>
														<s:checkbox name="tt1" disabled="true" checked="true"
															theme="simple" />
														<s:property value="header.ttoDate" />
													</s:else>
												</td>
												<td style="font-size: 8pt" class="label-1" align="left">Upcharge
													%</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														name="header.upcharge"
														cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;"
														theme="simple" value="%{header.upcharge}" tabindex="15" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Loading
													Port</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="LOADING_PORT" name="header.loadingPort"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.loadingPort}"
														onblur="if(this.value!='')  return xmlhttpreqloadingport(this,'LOADING_PORT_DESC')"
														tabindex="22" /> <a
													href="CsytabViewPREINVMVX.action?PARAA=loadingPort&PARAB=LOADING_PORT_DESC&TYPE_CODE=HAFE"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a> <s:textfield id="LOADING_PORT_DESC"
														name="LOADING_PORT_DESC" readonly="true"
														cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{LOADING_PORT_DESC}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Invoice
													Qnty</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														name="header.invoiceQty" readonly="true" cssClass="textreadonly"
														cssStyle="width:60pt;text-align:right;font-size:9pt;"
														theme="simple" value="%{header.invoiceQty}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Notify</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="notify" name="header.notify"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.notify}"
														onblur="if(this.value!='') return notifysearch(this,'NOTIFY_NAME')"
														tabindex="6" /> <s:textfield id="NOTIFY_NAME"
														name="NOTIFY_NAME" readonly="true" cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{NOTIFY_NAME}" /> <a
													href="chaViewPREINVMVX.action?PARAA=notify&PARAB=NOTIFY_NAME"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a></td>

												<td style="font-size: 8pt" class="label-1" align="left">TO
													Date</td>
												<td class="label-1" style="font-size: 8pt" align="left">
													<sj:datepicker name="header.toDate" id="to_date" theme="simple"
														displayFormat="dd-mm-yy" cssClass="txtd;font-size:9pt;"
														value="%{header.toDate}" tabindex="11" />
												</td>
												<td style="font-size: 8pt" class="label-1" align="left">Commision
													%</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.commision"
														cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;"
														theme="simple" value="%{header.commision}" tabindex="16" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Discharge</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="DISCHARGE" name="header.discharge"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.discharge}"
														onblur="if(this.value!='') return xmlhttpreqdischarge(this,'DISCHARGE_DESC')"
														tabindex="22" /> <a
													href="CsytabViewPREINVMVX.action?PARAA=discharge&PARAB=DISCHARGE_DESC&TYPE_CODE=SDST"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a> <s:textfield id="DISCHARGE_DESC"
														name="DISCHARGE_DESC" readonly="true"
														cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{DISCHARGE_DESC}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Invoice
													Type</td>
												<td class="label-1" style="font-size: 9pt"><s:select
														name="header.invoiceType" label="Invoice Type"
														cssStyle="font-size:10pt;width=60pt;font-size:9pt;"
														theme="simple" value="%{header.invoiceType}"
														list="#{'N':'Normal','S':'Trade Sample','F':'Free Sample'}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">CHA</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="agent" name="header.agent"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.agent}"
														onblur="if(this.value!='') return search_cha(this,'CHA_NAME')"
														tabindex="7" /> <s:textfield id="CHA_NAME"
														name="CHA_NAME" readonly="true" cssClass="textreadonly"
														cssStyle="width:100pt;font-size:8pt;" theme="simple"
														value="%{CHA_NAME}" /> <a
													href="chaViewPREINVMVX.action?PARAA=agent&PARAB=CHA_NAME"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a></td>
												<td style="font-size: 8pt" class="label-1" align="left">ETD
													Date</td>
												<td class="label-1" style="font-size: 9pt" align="left"><sj:datepicker
														name="header.etdDate" cssClass="txtd;font-size:9pt;"
														theme="simple" displayFormat="dd-mm-yy"
														value="%{header.etdDate}" tabindex="12" /></td>

												<td style="font-size: 8pt" class="label-1" align="left">Transport
													Rate</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.transportRate"
														cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;"
														theme="simple" value="%{header.transportRate}" tabindex="16" /></td>

												<td style="font-size: 8pt" class="label-1" align="left">Dischr
													Cntry</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="DIS_CNTRY" name="header.dischargeCntry"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.dischargeCntry}"
														onblur="if(this.value!='') return xmlhttpreqdiscntry(this,'DIS_CNTRY_DESC')"
														tabindex="23" /> <a
													href="CsytabViewPREINVMVX.action?PARAA=dischargeCntry&PARAB=DIS_CNTRY_DESC&TYPE_CODE=CSCD"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a> <s:textfield id="DIS_CNTRY_DESC"
														name="DIS_CNTRY_DESC" readonly="true"
														cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{DIS_CNTRY_DESC}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Ship
													Mode</td>
												<td class="label-1" style="font-size: 8pt"><s:select
														name="header.shipMode"
														cssStyle="text-transform:uppercase;width:60pt;font-size:9pt;"
														tabindex="1" value="%{header.shipMode}" theme="simple"
														list="%{modlList}" headerKey="" headerValue=""
														listKey="LIST_CODE" listValue="LIST_NAME" /></td>

												<td style="font-size: 8pt" class="label-1" align="left">Forwarder</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="fwd_code" name="header.forwarder"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.forwarder}"
														onblur="if(this.value!='') return xmlhttpreqfwd(this,'FWD_NAME')"
														tabindex="8" /> <s:textfield id="FWD_NAME"
														name="FWD_NAME" readonly="true" cssClass="textreadonly"
														cssStyle="width:100pt;font-size:8pt;" theme="simple"
														value="%{FWD_NAME}" /> <a
													href="chaViewPREINVMVX.action?PARAA=forwarder&PARAB=FWD_NAME"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" />
												</a></td>
												<td style="font-size: 8pt" class="label-1" align="left">Tax
													Code</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														name="header.taxCode" id="TAX_CODE"
														cssStyle="text-transform:uppercase;width:70pt;"
														cssClass="textreadonly" theme="simple" value="%{header.taxCode}"
														onblur="if(this.value!='') return xmlhttpreqtax(this,'TAX_TYPE','TAX_PERCENT')"
														tabindex="13" /> <!--  <a href="taxcodeViewPREINVMVX.action?PARAA=taxCode&PARAB=TAX_TYPE&TYPE_CODE=TAX_PERCENT" target="handlefrm" onclick="openpop('root')">
		                                                                 <img width="14px" border=0 height="14px" src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png" tabindex="-1"/></a>
		                                                              --></td>
												<td style="font-size: 8pt" class="label-1" align="left">Tax
													Calc Fob%</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														id="header.taxCalFob" name="TAX_CAL_PER"
														cssStyle="width:30pt;font-size:9pt;" theme="simple"
														value="%{header.taxCalFob}" /></td>


												<td style="font-size: 8pt" class="label-1" align="left">Origin
													Cntry</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="CNTRY_ORIGIN" name="header.originCntry"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.originCntry}"
														onblur="if(this.value!='') return xmlhttpreqdiscntryoriginal(this,'CNTRY_ORIGIN_DESC')"
														tabindex="23" /> <a
													href="CsytabViewPREINVMVX.action?PARAA=originCntry&PARAB=CNTRY_ORIGIN_DESC&TYPE_CODE=CSCD"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" /></a> <s:textfield id="CNTRY_ORIGIN_DESC"
														name="CNTRY_ORIGIN_DESC" readonly="true"
														cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{CNTRY_ORIGIN_DESC}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Pre
													Carriage</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="pre_carriage" name="header.preCarriage"
														cssStyle="text-transform:uppercase;width:60pt;font-size:9pt;"
														theme="simple" value="%{header.preCarriage}" tabindex="2" /></td>


												<td style="font-size: 8pt" class="label-1" align="left">Manufacturer</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="MANUF_CODE" name="header.manufacturer" readonly="true"
														cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"
														theme="simple" value="%{header.manufacturer}"
														onblur=" if(this.value!='') return xmlhttpreqmanuf(this,'MANUF_DESC')"
														tabindex="9" /> <s:textfield id="MANUF_DESC"
														name="MANUF_DESC" readonly="true" cssClass="textreadonly"
														cssStyle="width:100pt;font-size:9pt;" theme="simple"
														value="%{MANUF_DESC}" /> <a
													href="unitViewPREINVMVX.action?PARAA=manufacturer&PARAB=MANUF_DESC&TYPE_CODE=<s:property value="%{inv_state}"/>"
													target="handlefrm" onclick="openpop('root')"> <img
														width="14px" border=0 height="14px"
														src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
														tabindex="-1" />
												</a></td>
												<td style="font-size: 8pt" class="label-1" align="left">Tax
													Desc</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														id="TAX_TYPE" name="header.taxDesc" readonly="true"
														cssClass="textreadonly"
														cssStyle="width:70pt;font-size:9pt;" theme="simple"
														value="%{header.taxDesc}" /> <s:textfield id="TAX_PERCENT"
														name="taxPer" readonly="true" cssClass="textreadonly"
														cssStyle="text-transform:uppercase;width:20pt;font-size:9pt;"
														theme="simple" value="%{taxPer}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Total
													Cartons</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.totalCartons"
														cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;"
														theme="simple" value="%{header.totalCartons}" tabindex="17" /></td>


												<td style="font-size: 8pt" class="label-1" align="left">Destination</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.destination" readonly="true"
														cssStyle="width:50pt;font-size:9pt;background-color:#fea"
														theme="simple" value="%{header.destination}" /> <img width="14px"
													border=0 height="14px"
													src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
													tabindex="-1" /> <s:textfield name="DESTI_CODE_DESC"
														readonly="true"
														cssStyle="width:100pt;font-size:9pt;background-color:#fea;"
														theme="simple" value="%{DESTI_CODE_DESC}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">PCH</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														name="header.pch" readonly="true"
														cssStyle="width:60pt;font-size:9pt;background-color:#fea"
														theme="simple" value="%{header.pch}" /></td>

												<td style="font-size: 8pt" class="label-1" align="left">CO
													PayTerm</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.coPayterm" readonly="true"
														cssStyle="width:50pt;font-size:9pt;background-color:#fea"
															theme="simple" value="%{header.coPayterm}" /> <s:textfield
														name="pay_term_desc" readonly="true"
														cssStyle="width:100pt;font-size:9pt;background-color:#fea"
														theme="simple" value="%{pay_term_desc}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">HS
													Code</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														name="header.hsCode" cssClass="textreadonly"
														cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;"
														theme="simple" value="%{header.hsCode}" tabindex="14" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Total
													GrWt.</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.totalGrossWeight"
														cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;"
														theme="simple" value="%{header.totalGrossWeight}" tabindex="17" /></td>

												<td style="font-size: 8pt" class="label-1" align="left">Destn
													Cntry</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.destinationCntry" id="DESTI_CNTRY" readonly="true"
														cssStyle="width:50pt;font-size:9pt;background-color:#fea"
														theme="simple" value="%{header.destinationCntry}" /> <img
													width="14px" border=0 height="14px"
													src="<%=request.getContextPath()%>/shahiwebpages/MvxExp/images/Search-icon-big.png"
													tabindex="-1" /> <s:textfield name="DESTI_CNTRY_DESC"
														readonly="true"
														cssStyle="width:100pt;font-size:9pt;background-color:#fea;"
														theme="simple" value="%{DESTI_CNTRY_DESC}" /></td>

											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Currency</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														name="header.currency" readonly="true"
														cssStyle="width:60pt;font-size:9pt;background-color:#fea"
														theme="simple" value="%{header.currency}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Delivery
													Term</td>
												<td class="label-1" style="font-size: 9pt"><s:select
														name="header.deliveryTerms"
														cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;background-color:#fea"
														tabindex="11" value="%{header.deliveryTerms}" theme="simple"
														list="%{tedlList}" headerKey="" headerValue=""
														listKey="LIST_CODE" listValue="LIST_NAME" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Fwd
													to Post</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.fowardDate" readonly="true" cssClass="textreadonly"
														cssStyle="width:70pt;font-size:10pt;font-weight:bold;color:blue"
														theme="simple" value="%{header.fowardDate}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Total
													NetWt.</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														name="header.totalNetWeight"
														cssStyle="text-transform:uppercase;width:30pt;font-size:9pt;"
														theme="simple" value="%{header.totalNetWeight}" tabindex="18" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Fob
													FC [<s:property value="%{CRNCY_CODE}" />]
												</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														readonly="true"
														cssStyle="width:50pt;font-size:9pt;text-align:right;background-color:yellow;color:red;font-weight:bold"
														theme="simple" value="%{TOT_FOB}" /> &nbsp;
													&nbsp;&nbsp;&nbsp;GR Decl:&nbsp; <s:textfield
														readonly="true"
														cssStyle="width:49pt;font-size:9pt;align:right;background-color:yellow;text-align:right"
														theme="simple" value="%{TOT_GR}" /></td>
											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Exp
													Type</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														name="header.expType." readonly="true"
														cssStyle="width:60pt;font-size:9pt;background-color:#fea"
														theme="simple" value="%{header.expType}" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Payment
													Term</td>
												<td class="label-1" style="font-size: 8pt"><s:select
														name="header.paymentTerm"
														cssStyle="text-transform:uppercase;width:154pt;font-size:9pt;"
														tabindex="11" value="%{header.paymentTerm}" theme="simple"
														list="%{PaymenttermList}" headerKey="" headerValue=""
														listKey="LIST_CODE" listValue="LIST_NAME" /></td>

												<td style="font-size: 8pt" class="label-1" align="left">Remarks</td>
												<td style="font-size: 8pt" class="label-1" colspan="3"
													class="label-1"><s:textfield name="header.remarks"
														cssStyle="text-transform:uppercase;width:260pt;font-size:9pt;"
														theme="simple" value="%{header.remarks}" tabindex="23" /></td>
												<td style="font-size: 8pt" class="label-1" align="left">Net
													Fob [<s:property value="%{CRNCY_CODE}" />]
												</td>
												<td class="label-1" style="font-size: 9pt"><s:textfield
														readonly="true"
														cssStyle="width:50pt;font-size:9pt;align:right;background-color:yellow;text-align:right;font-weight:bold;color:green"
														theme="simple" value="%{NET_FOB}" /></td>

											</tr>
											<tr>
												<td style="font-size: 8pt" class="label-1" align="left">Lut/IGST</td>
												<td class="label-1" style="font-size: 9pt"><s:select
														name="header.lutIGST" label="LUT/IGST"
														cssStyle="font-size:10pt;width=60pt;font-size:9pt;"
														theme="simple" value="%{header.lutIGST}"
														list="#{' ':' ','LUT':'LUT','IGST':'IGST'}" /> <s:hidden
														name="INV_GEO" id="INV_GEO" value="%{INV_GEO}" /> <s:hidden
														name="INV_GSTIN" id="INV_GSTIN" value="%{INV_GSTIN}" /></td>

												<td style="font-size: 8pt" class="label-1" align="left">CI No/Date</td>
												<td class="label-1" style="font-size: 8pt"><s:textfield
														id="CINO" name="header.ciNo" readonly="true"
														cssStyle="text-transform:uppercase;width:100pt;font-size:9pt;"
														theme="simple" value="%{header.ciNo}"
														 />
														 <s:textfield
														id="CIDATE" name="header.ciDate" readonly="true"
														cssStyle="text-transform:uppercase;width:54pt;font-size:9pt;"
														theme="simple" value="%{header.ciDate}"
														 />
											</tr>
											
										</table>
									</td>
								</tr>
								<tr></tr>
								<tr style="background-color: #f2f9fb;">
									<td>
										<table bgcolor="#7b97e0" style="margin-top: -2pt" border="1"
											align="center" width="100%" cellpadding="3" cellspacing="1">
											<tr>
												<th align="left">Type</th>
												<th align="left">Code</th>
												<th align="left">Add No.</th>
												<th align="left">Name</th>
												<th align="left">Address</th>
											</tr>
											<tr bgcolor="#f2f9fb">
												<td style="font-size: 8pt">Buyer</td>
												<td style="font-size: 8pt"><s:property value="header.buyer.BUYER" /></td>
												<td style="font-size: 8pt"><s:property
														value="header.buyer.addressId" /></td>
												<td style="font-size: 8pt"><s:property
														value="header.buyer.buyerName" /></td>
												<td style="font-size: 8pt"><s:property
														value="header.buyer.BUYER_ADDRESS" />&nbsp;
													&nbsp;&nbsp;&nbsp;State:&nbsp; &nbsp;&nbsp;&nbsp;<s:property
														value="header.buyer.destinationState" /></td>
											</tr>
											<tr bgcolor="#f2f9fb">
												<td style="font-size: 8pt">Consignee</td>
												<td style="font-size: 8pt"><s:property value="header.buyer.BUYER" /></td>
												<td style="font-size: 8pt"><s:property
														value="header.consignee.addressId" /></td>
												<td style="font-size: 8pt"><s:property
														value="header.consignee.buyerName" /></td>
												<td style="font-size: 8pt"><s:property
														value="header.consignee.BUYER_ADDRESS" />
														<s:hidden name="header.buyer.BUYER"
														value="%{header.buyer.BUYER}" />
														 <s:hidden name="header.buyer.buyerName"
														value="%{header.buyer.buyerName}" />
														 <s:hidden name="header.consignee.buyerName"
														value="%{header.consignee.buyerName}" />
														<s:hidden name="header.buyer.BUYER_ADDRESS" value="%{header.buyer.BUYER_ADDRESS}"/>
														<s:hidden name="header.consignee.BUYER_ADDRESS" value="%{header.consignee.BUYER_ADDRESS}"/>
														 <s:hidden name="sdate"
														value="%{sdate}" /> <s:hidden name="header.buyer.addressId"
														value="%{header.buyer.addressId}" /> <s:hidden name="header.consignee.addressId"
														value="%{header.consignee.addressId}" /> <s:hidden name="header.facility"
														value="%{header.facility}" /> <s:hidden name="to_inr_conv"
														value="%{to_inr_conv}" /> <s:hidden name="header.itemGroup"
														value="%{header.itemGroup}" /> <s:hidden name="header.gstInState"
														value="%{header.gstInState}" /> <s:hidden name="header.buyer.destinationState"
														value="%{header.buyer.destinationState}" /> <s:hidden name="DESTI_GEO"
														value="%{DESTI_GEO}" /></td>
											</tr>
										</table>
							</table>
						</td>
					</tr>
				</table>
				<table align="center" width="100%">
				    <s:hidden name="header.company" value="%{header.company}"/>
				    <s:hidden name="header.year" value="%{header.year}"/>
				    <s:hidden name="header.invNo" value="%{header.invNo}"/>
				    
					<tr bgcolor="#f2f2f2">
						<td
							style="border-width: 1pt; border-color: black; border-style: solid;">
							<table align="center" width="100%">
								<tr>
									<td align="left">Date : <s:property value="%{currentdate}" />
									</td>
									<s:hidden value="%{currentdate}" name="CRDATE" id="CRDATE" />
									<td valign="bottom" align="center"
										style="color: red; font-weight: bold">
										<div style="height: 3pt">
											<s:actionerror />
											<s:fielderror />
											<s:actionmessage />
										</div>
									</td>
									<td align="right">User : <s:property value="%{aausrid}" />
									</td>
								</tr>
								<input type="hidden" name="aausrid"
									value="<s:property value="%{aausrid}"/>">
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
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
		<%-- <script language="javascript">
            var theHandle = document.getElementById("handle");
            var theRoot   = document.getElementById("root");
            Drag.init(theHandle, theRoot);
           </script> --%>
		<sj:tab id="tabbar1" target="tab1" label="Billing Details"
			title="Billing Details" />
		<div title="" id="tab1" style="overflow: hidden;">
			<iframe name="tabframe1" id="tabframe1"
				src="prelinepricePREINVMVX?company=<s:property value='%{COMPANY}'/>&year=<s:property value='%{YEAR}'/>&invNo=<s:property value='%{INV_NO}'/>&
				desti_cntry=<s:property value='DESTI_CNTRY'/>&inv_date=<s:property value='%{inv_date}'/>&plan_no=<s:property value='%{plan_no}'/>"
				scrolling="no" frameborder="0" style="overflow: hidden;"
				width="100%" height="600px"> </iframe>
		</div>
		<sj:tab id="tabbar2" target="tab2" label="Accessories Details"
			title="Accessories Details" />
		<div title="" id="tab2" style="overflow: hidden;">
			<iframe name="tabframe2" id="tabframe2"
				src="preinvaccrPREINVMVX?company=<s:property value='%{COMPANY}'/>&year=<s:property value='%{YEAR}'/>&plan_no=<s:property value='%{plan_no}'/>&GetDataFlag=<s:property value='%{GetDataFlag}'/>&invNo=<s:property value='%{INV_NO}'/>&inv_date=<s:property value='%{inv_date}'/>"
				scrolling="no" frameborder="0" style="overflow: hidden;"
				width="100%" height="600px"> </iframe>
		</div>
		<sj:tab id="tabbar3" target="tab3" label="Licence Details"
			title="Licence Details" />
		<div title="" id="tab3" style="overflow: hidden;">
			<iframe name="tabframe3" id="tabframe3"
				src="prelicencePREINVMVX?company=<s:property value='%{COMPANY}'/>&year=<s:property value='%{YEAR}'/>&plan_no=<s:property value='%{plan_no}'/>&GetDataFlag=<s:property value='%{GetDataFlag}'/>&invNo=<s:property value='%{INV_NO}'/>"
				scrolling="no" frameborder="0" style="overflow: hidden;"
				width="100%" height="600px"> </iframe>
		</div>
		<sj:tab id="tabbar4" target="tab4" label="COLine/GST"
			title="COLine/GST" />
		<div title="" id="tab4" style="overflow: hidden;">
			<iframe name="tabframe4" id="tabframe4"
				src="preexcisePREINVMVX?company=<s:property value='%{COMPANY}'/>&year=<s:property value='%{YEAR}'/>&plan_no=<s:property value='%{plan_no}'/>&GetDataFlag=<s:property value='%{GetDataFlag}'/>&invNo=<s:property value='%{INV_NO}'/>"
				scrolling="no" frameborder="0" style="overflow: hidden;"
				width="100%" height="600px"> </iframe>
		</div>

	</sj:tabbedpanel>

	<%-- <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script> --%>
	<script language="javascript">
	
		if (typeof window.event != 'undefined') // IE
			document.onkeydown = function() // IE
			{
	
				if (event.keyCode == 13) {
					event.keyCode = 9
				}
	
	
				var t = event.srcElement.type;
				var kc = event.keyCode;
	
	
				return ((kc != 8 && kc != 13) || (t == 'text' && kc != 13) ||
					(t == 'textarea') || (t == 'submit' && kc == 13))
		}
		else
			document.onkeypress = function(e) // FireFox/Others
			{
				return tabOnEnter(e.target, e)
	
				aaaa = e.keyCode;
				if (aaaa == 13) {
	
	
				}
				return true;
	
		}
		function searchdetail() {
			if( (document.frm.searchinv.value == "" && document.frm.searchplan.value == "") ) {
				alert("Please Enter Plan No or Invoice No or CI No ")
				document.frm.searchplan.focus();
				return false;
	
			}
			document.frm.action = "PREINVMVX.action?viewFlag=YES";
			document.method="post";
			document.frm.submit();
			document.getElementById('prepage').style.visibility = '';
		}
		function GetMovexData() {
			document.frm.action = "PREINVMVX.action?viewFlag=YES&GETREFRESH=YES";
			document.frm.submit();
			document.getElementById('prepage').style.visibility = '';
		}
		function COAPRV() {
			document.frm.action = "PREINVMVX.action?CHECK_APRV=YES";
			document.frm.submit();
			document.getElementById('prepage').style.visibility = '';
		}
		/* function validaterec() {
			var to_date=document.getElementById('to_date').value;
			
			//var aa = dojo.widget.byId("to_date").getValue().substring(0, 4) + dojo.widget.byId("to_date").getValue().substring(5, 7) + dojo.widget.byId("to_date").getValue().substring(8, 10);
			var aa=to_date.substring(0, 4)+to_date.substring(5, 7)+to_date.substring(8, 10);
			var bb = document.frm.CRDATE.value.substring(6, 10) + document.frm.CRDATE.value.substring(3, 5) + document.frm.CRDATE.value.substring(0, 2);
	
			if (aa != null && aa > bb) {
				alert("T/O Date Can not be future date...")
				return false;
			}
			if (document.frm.cost_centre.value == "") {
				alert("Please Enter PCH !!");
				document.frm.cost_centre.focus();
				return false;
			}
	
			if (document.frm.MANUF_CODE.value == "" && document.frm.sdate.value > 20170701) {
				alert("Please Enter Manufacturer Code !!");
				document.frm.MANUF_CODE.focus();
				return false;
			}
			if (document.frm.mode_of_ship.value == "") {
				alert("Please Enter Mode of Ship !!");
				document.frm.mode_of_ship.focus();
				return false;
			}
			if (document.frm.ac_holder.value == "") {
				alert("Please Enter AC Holder !!");
				document.frm.ac_holder.focus();
				return false;
			}
	
			if (document.frm.mode_of_ship.value == "") {
				alert("Please Enter Mode of Ship !!");
				document.frm.mode_of_ship.focus();
				return false;
			}
			if (document.frm.PLACE.value == "") {
				alert("Please Enter Place !!");
				document.frm.PLACE.focus();
				return false;
			}
			if (document.frm.CLR_PORT.value == "") {
				alert("Please Enter Clearing Port !!");
				document.frm.CLR_PORT.focus();
				return false;
			}
			if (document.frm.LOADING_PORT.value == "") {
				alert("Please Enter LOADING_PORT !!");
				document.frm.LOADING_PORT.focus();
				return false;
			}
			if (document.frm.DISCHARGE.value == "") {
				alert("Please Enter DISCHARGE !!");
				document.frm.DISCHARGE.focus();
				return false;
			}
			if (document.frm.DIS_CNTRY.value == "") {
				alert("Please Enter Discharge Cntry !!");
				document.frm.DIS_CNTRY.focus();
				return false;
			}
			if (document.frm.DISCHARGE.value == "") {
				alert("Please Enter Discharge  !!");
				document.frm.DISCHARGE.focus();
				return false;
			}
			if (document.frm.CNTRY_ORIGIN.value == "") {
				alert("Please Enter Origin Cntry !!");
				document.frm.CNTRY_ORIGIN.focus();
				return false;
			}
			if (document.frm.self_tp.value == "N" && (document.frm.exp_type.value == "YAN" || document.frm.exp_type.value == "FAN" || document.frm.exp_type.value == "TRN" || document.frm.exp_type.value == "GMN")) {
				alert("Check Invoice Type.... Can not be Normal") ;
				return false;
	
			}
			return true;
	
		} */
	
	
		function copyMRP() {
			if( (document.frm.MRPRATE_COPY.value == "") ) {
				alert("Please Enter MRP RATE TO COPY ")
				document.frm.MRPRATE_COPY.focus();
				return false;
			}
			var MRP_RATE = document.frm.MRP_RATE;
			if (document.getElementById("MRPRATE_COPY").value != "") {
				for (i = 0; i < MRP_RATE.length; i++) {
					MRP_RATE[i].value = document.getElementById("MRPRATE_COPY").value;
				}
			}
		}
		function copySelectedMRP(z) {
			if (document.getElementById("MRPRATE_COPY").value != "") {
				document.getElementById('MRP_RATE' + z).value = document.getElementById("MRPRATE_COPY").value;
			}
	
		}
	
		function copyACCR() {
			if( (document.frm.ACCR_RATE_COPY.value == "") ) {
				alert("Please Enter ACCR Price ")
				document.frm.ACCR_RATE_COPY.focus();
				return false;
	
			}
			document.frm.action = "PREINVMVX.action?accrFlag=Yes";
			document.frm.submit();
			document.getElementById('prepage').style.visibility = '';
		}
		function openpop(a) {
			document.getElementById(a).style.display = '';
		}
		function closediv(a) {
			document.getElementById(a).style.display = 'none';
		}
	
		function callcatg(a1, a, b) {
			document.getElementById(a1).href = "catgViewPREINVMVX?PARAA=" + a + "&PARAB=" + b + "&DESTI_CNTRY=" + document.getElementById('DESTI_CNTRY').value;
	
		}
		function calllic(a1, a) {
			document.getElementById(a1).href = "prelicviewPREINVMVX?PARAA=" + a;
		}
		/* function calldbk(a1, a) {
			document.getElementById(a1).href = "predbkslviewPREINVMVX?PARAA=" + a + "&INVDATE=" + document.getElementById('inv_date').value;
		} */
		function callrosl(a1, a) {
			document.getElementById(a1).href = "preroslslviewPREINVMVX?PARAA=" + a + "&INVDATE=" + document.getElementById('inv_date').value;
		}
		function callstr(a1, a, b) {
			document.getElementById(a1).href = "prestrslviewPREINVMVX?PARAA=" + a + "&PARAB=STR" + "&dbkslnocopy=" + document.getElementById('DBK_SLNO_COPY').value + "&INVDATE=" + document.getElementById('inv_date').value;
		}
		
		function callBE(a1, a) {
			document.getElementById(a1).href = "prebeviewPREINVMVX?PARAA=" + a + "&LIC_TYPE=" + document.getElementById('REF_TYPE' + a).value + "&LIC_NO=" + document.getElementById('REF_NO' + a).value;
		}
		/* function copyCatg() {
			var PRICE_FC_MOVEX = document.frm.PRICE_FC_MOVEX;
			var CATG_CODE = document.frm.CATG_CODE;
			var INV_DESC = document.frm.INV_DESC;
			var DBK_SLNO = document.frm.DBK_SLNO;
			var ROSL_SLNO = document.frm.ROSL_SLNO;
			var SCHEME_CODE = document.frm.SCHEME_CODE;
			var HSCODE1 = document.frm.HSCODE1;
			var STR_SLNO = document.frm.STR_SLNO;
			var STR_MISC = document.frm.STR_MISC;
			var HNGR_COST = document.frm.HNGR_COST;
			var TAG_COST = document.frm.TAG_COST;
			var PRICE_FC_E = document.frm.PRICE_FC_E;
			var MADE_FOR = document.frm.MADE_FOR;
			var ADJUST_FC = document.frm.ADJUST_FC;
			if (CATG_CODE.length > 0) {
				if (document.getElementById("CATG_CODE_COPY").value != "") {
					for (i = 0; i < CATG_CODE.length; i++) {
						CATG_CODE[i].value = document.getElementById("CATG_CODE_COPY").value;
						DBK_SLNO[i].value = document.getElementById("DBK_SLNO_COPY").value;
						ROSL_SLNO[i].value = document.getElementById("ROSL_SLNO_COPY").value;
						STR_SLNO[i].value = document.getElementById("STR_SLNO_COPY").value;
						STR_MISC[i].value = document.getElementById("STR_MISC_COPY").value;
						HSCODE1[i].value = document.getElementById("HS_CODE_COPY").value;
						SCHEME_CODE[i].value = document.getElementById("SCHEME_CODE_COPY").value;
						HNGR_COST[i].value = document.getElementById("PRICE_MISC_COPY").value;
						TAG_COST[i].value = document.getElementById("PRICE_TAG_COPY").value;
	
						//    PRICE_FC_E[i].value=parseFloat(eval(document.getElementById("PRICE_FC_E"+i).value)) - eval(document.getElementById("PRICE_MISC_COPY").value);
						PRICE_FC_E[i].value = parseFloat(eval(document.getElementById("PRICE_FC_MOVEX" + i).value)) - (eval(document.getElementById("PRICE_MISC_COPY").value) + eval(document.getElementById("PRICE_TAG_COPY").value));
						MADE_FOR[i].value = document.getElementById("SHIP_TYPE_COPY").value;
						ADJUST_FC[i].value = document.getElementById("ADJUST_FC_COPY").value;
	
					}
				}
	
				if (document.getElementById("CATG_DESC_COPY").value != "") {
					for (i = 0; i < CATG_CODE.length; i++) {
						INV_DESC[i].value = document.getElementById("CATG_DESC_COPY").value;
	
					}
				}
	
			} else {
	
				if (document.getElementById("CATG_CODE_COPY").value != "") {
					CATG_CODE.value = document.getElementById("CATG_CODE_COPY").value;
					DBK_SLNO.value = document.getElementById("DBK_SLNO_COPY").value;
					ROSL_SLNO.value = document.getElementById("ROSL_SLNO_COPY").value;
					STR_SLNO.value = document.getElementById("STR_SLNO_COPY").value;
					STR_MISC.value = document.getElementById("STR_MISC_COPY").value;
					HNGR_COST.value = document.getElementById("PRICE_MISC_COPY").value;
					TAG_COST.value = document.getElementById("TAG_MISC_COPY").value;
					HSCODE1.value = document.getElementById("HS_CODE_COPY").value;
					SCHEME_CODE.value = document.getElementById("SCHEME_CODE_COPY").value;
				}
	
				if (document.getElementById("CATG_DESC_COPY").value != "") {
	
					INV_DESC.value = document.getElementById("CATG_DESC_COPY").value;
				}
			}
		} 
		function copySelected(x) {
			if (document.getElementById("CATG_DESC_COPY").value != "") {
				document.getElementById('INV_DESC' + x).value = document.getElementById("CATG_DESC_COPY").value;
			}
			if (document.getElementById("CATG_CODE_COPY").value != "") {
				document.getElementById('CATG_CODE' + x).value = document.getElementById("CATG_CODE_COPY").value;
			}
			if (document.getElementById("DBK_SLNO_COPY").value != "") {
				document.getElementById('DBK_SLNO' + x).value = document.getElementById("DBK_SLNO_COPY").value;
			}
			if (document.getElementById("ROSL_SLNO_COPY").value != "") {
				document.getElementById('ROSL_SLNO' + x).value = document.getElementById("ROSL_SLNO_COPY").value;
			}
			if (document.getElementById("STR_SLNO_COPY").value != "") {
				document.getElementById('STR_SLNO' + x).value = document.getElementById("STR_SLNO_COPY").value;
			}
			if (document.getElementById("STR_MISC_COPY").value != "") {
				document.getElementById('STR_MISC' + x).value = document.getElementById("STR_MISC_COPY").value;
			}
			if (document.getElementById("PRICE_MISC_COPY").value != "0.0") {
				document.getElementById('HNGR_COST' + x).value = document.getElementById("PRICE_MISC_COPY").value;
			}
			if (document.getElementById("PRICE_TAG_COPY").value != "0.0") {
				document.getElementById('TAG_COST' + x).value = document.getElementById("PRICE_TAG_COPY").value;
			}
			if (document.getElementById("HS_CODE_COPY").value != "") {
				document.getElementById('HSCODE1' + x).value = document.getElementById("HS_CODE_COPY").value;
			}
	
			if (document.getElementById("SCHEME_CODE_COPY").value != "") {
				document.getElementById('SCHEME_CODE' + x).value = document.getElementById("SCHEME_CODE_COPY").value;
			}
			if (document.getElementById("SHIP_TYPE_COPY").value != "") {
				document.getElementById('MADE_FOR' + x).value = document.getElementById("SHIP_TYPE_COPY").value;
			}
		}
		 function validateSTR() {
			var PRICE_FC_MOVEX = document.frm.PRICE_FC_MOVEX;
			var STR_SLNO = document.frm.STR_SLNO;
			var SCHEME_CODE = document.frm.SCHEME_CODE;
			var STR_MISC = document.frm.STR_MISC;
			var CATG_CODE = document.frm.CATG_CODE;
			var PRICE_FC_E = document.frm.PRICE_FC_E;
			var HNGR_COST = document.frm.HNGR_COST;
			var TAG_COST = document.frm.TAG_COST;
			var ADJUST_FC = document.frm.ADJUST_FC;
			if (PRICE_FC_MOVEX.length > 0) {
				t1 = 0;
				h1 = 0;
				t2 = 0;
				h2 = 0;
				for (i = 0; i < PRICE_FC_MOVEX.length; i++) {
					if (document.frm.CNTRY_ORIGIN.value == "") {
						alert("Please Enter Origin Cntry !!");
						document.frm.CNTRY_ORIGIN.focus();
						return false;
					}
					if (CATG_CODE[i].value == "") {
						alert("Please Enter Category ....");
						return false;
					}
	
					if (SCHEME_CODE[i].value == "") {
						alert("Please Enter SCHEME_CODE....");
						return false;
					}
					h1 = parseFloat(HNGR_COST[i].value) ;
					h2 = parseFloat(TAG_COST[i].value) ;
	
	
					h1 = parseFloat(HNGR_COST[i].value) + parseFloat(TAG_COST[i].value)
	
					if (ADJUST_FC[i].value >= .01) {
						alert("Check Adjust FC ");
						return false;
					}
	
	
					/*
					  if(STR_SLNO[i].value=="")
					  {
					   alert("Please Enter STR SLNO....");
					    return false;
					  }   
	                
					  if (STR_MISC[i].value=="" && h1!=0)
					  { 
					      alert("STR Misc required for Price Misc ");
					       return false; 
					  } 
	                 
					  if (STR_MISC[i].value!="" &&  h1==0)
					  {
					      alert("Price Misc required for STR Misc. ");
					       return false;  
					  } 
					  
	
	
					t1 = parseFloat(PRICE_FC_E[i].value) ;
					t2 = parseFloat(HNGR_COST[i].value);
					t3 = parseFloat(TAG_COST[i].value);
	
					var t = parseFloat(eval(PRICE_FC_E[i].value) + eval(HNGR_COST[i].value) + eval(TAG_COST[i].value)).toFixed(4);
	
					if (t > parseFloat(PRICE_FC_MOVEX[i].value).toFixed(4)) {
						alert("Please Check Price FC");
						PRICE_FC_E[i].value = PRICE_FC_MOVEX[i].value;
						HNGR_COST[i].value = "0.0";
						TAG_COST[i].value = "0.0";
						return false;
					}
				}
			}
	
			return true;
		} function validateLIC() {
			var REF_NO = document.frm.REF_NO;
			var REF_TYPE = document.frm.REF_TYPE;
			var IO_NORMS = document.frm.IO_NORMS;
			var QTY_SQM = document.frm.QTY_SQM;
			var BE_NO = document.frm.BE_NO;
			var ADJS_QTY = document.frm.ADJS_QTY;
			var FOB_AMT = document.frm.FOB_AMT;
			var LIC_AMT_OB = document.frm.LIC_AMT_OB;
			var LIC_AMT_ISSUE = document.frm.LIC_AMT_ISSUE;
			var LIC_QTY_OB = document.frm.LIC_QTY_OB;
			var LIC_QTY_ISSUE = document.frm.LIC_QTY_ISSUE;
	
			if (REF_NO != null) {
				t1 = 0;
				t2 = 0;
				for (i = 0; i < REF_NO.length; i++) {
					if (REF_NO[i].value != "") {
	
						if (IO_NORMS[i].value == "" && REF_TYPE[i].value != "EPCG") {
							alert("Please Enter Licence IO Norms ....");
							return false;
						}
						if (QTY_SQM[i].value == "" && REF_TYPE[i].value != "EPCG") {
							alert("Please Enter SQM QTY....");
							return false;
						}
						if ( (BE_NO[i].value == "" && REF_TYPE[i].value != "EPCG") ) {
							alert("Please Select BE No.... ");
							return false;
						}
						t1 = eval(eval(LIC_AMT_OB[i].value));
						t2 = eval(FOB_AMT[i].value) + eval(LIC_AMT_ISSUE[i].value);
	
						if (t1 > t2) {
							alert('Check Licence Export Obligation Value --> ' + t1 + '  Utilize Value -->' + t2)
						}
					}
				}
			}
	
			return true;
		} */
	
	
	
		function rosltest(objsrc, objtrg) {
			var xmlHttpReq = false;
			var self = this;
			if (window.XMLHttpRequest) {
				self.xmlHttpReq = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			self.xmlHttpReq.open('POST', 'ajxroslslnoViewAJXPREAction', false);
			self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
				if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200) {
					var a = self.xmlHttpReq.responseText;
	
					var b = new Array();
					b = a.split('#');
	
					if (b.length > 2) {
						document.getElementById('handlefrm').src = "preroslslviewPREINVMVX.action?unitparam=" + objsrc.value + "&PARAA=ROSL_SLNO_COPY&INVDATE=" + document.getElementById("INV_DATE").value;
						openpop('root');
						objsrc.value = '';
						document.getElementById(objtrg).value = '';
					} else {
						if (b[0] == 'Data Not Found') {
	
							alert(b[0]);
	
							document.getElementById('handlefrm').src = "preroslslviewPREINVMVX.action?unitparam=" + objsrc.value + "&PARAA=ROSL_SLNO_COPY&INVDATE=" + document.getElementById("INV_DATE").value;
							objsrc.value = '';
							document.getElementById(objtrg).value = '';
							openpop('root');
						} else {
							objsrc.value = b[0];
							document.getElementById(objtrg).value = b[1];
						}
					}
				}
			}
			param = objsrc.value;
			self.xmlHttpReq.send("unitparam=" + encodeURIComponent(param) + "&PARAA=ROSL_SLNO_COPY&INVDATE=" + document.getElementById("INV_DATE").value + "&time=" + (new Date()).getTime());
		}
	
	
		function isNumber(evt) {
			evt = (evt) ? evt : window.event;
			var charCode = (evt.which) ? evt.which : evt.keyCode;
			if (charCode > 31 && (charCode < 48 || charCode > 57)) {
				alert('Invalid Input, Only Numbers Allowed');
				return false;
			}
			return true;
		}
	
		function validatenumber(a) {
			k = a.value;
			if (k != "" && !k.match(/^\d+$|^\d+\.\d{1,4}$/)) {
				alert('Invalid Input, Only Numbers Allowed');
				a.value = "";
				a.focus();
	
				return false;
			}
	
			return true;
		}
	
		function deletetablerow(x) {
			document.getElementById('REF_NO' + x).value = "";
			document.getElementById('REF_TYPE' + x).value = "";
			document.getElementById('QTY' + x).value = "";
			document.getElementById('ADJS_QTY' + x).value = "";
			document.getElementById('FOB_AMT' + x).value = "";
			document.getElementById('IO_NORMS' + x).value = "";
			document.getElementById('QTY_SQM' + x).value = "";
			document.getElementById('BE_NO' + x).value = "";
			document.getElementById('BE_DESC' + x).value = "";
	
		}
		function validatenumber1(a, b) {
			k = a.value;
			//	if (k!="" && !k.match(/^\d+$|^\d+\$/ ) )
			if (k != "" && !k.match(/^\d+$|^\d+\.\d{1,4}$/)) {
				alert('Invalid Input, Only Numbers Allowed');
				a.value = "0";
				LicSqmCal(b);
				a.focus();
				return false;
			}
			LicSqmCal(b);
			return true;
		}
	
		function copyFcTotal(id) {
			var t = 0;
	
			document.getElementById('FOB_AMT' + id).value = document.getElementById('FCTOTAL').value ;
			document.getElementById('QTY' + id).value = document.getElementById('QTYTOTAL').value ;
			document.getElementById('ADJS_QTY' + id).value = document.getElementById('QTYTOTAL').value ;
	
		}
	
	
		function LicSqmCal(a) {
			var t = 0;
			if (document.getElementById('QTY' + a).value != "") {
				document.getElementById('ADJS_QTY' + a).value = document.getElementById('QTY' + a).value;
				t = eval(document.getElementById('QTY' + a).value) * eval(document.getElementById('IO_NORMS' + a).value);
			}
	
			document.getElementById('QTY_SQM' + a).value = t.toFixed(2);
	
	
		}
		function saverec() {
			/* if (validaterec() ) {
				
				document.frm.action = "saveinvPREINVMVX.action?saveFlag=YES";
				document.frm.submit();
				document.getElementById('prepage').style.visibility = '';
			} */
			
			document.frm.action = "saveinvPREINVMVX.action";
			document.frm.submit();
			document.getElementById('prepage').style.visibility = '';
		}
	
	
		/* function deletetablerowaccr(x) {
			//var id1=document.getElementById(x);   
			//alert('aa'+x);
			//document.getElementById('tableaaccr').deleteRow(x);
			$('#' + x).remove();
		} */
		
		function xmlhttpreqCOAPRV(YEAR, COMPANY, INV_NO) {
			var xmlHttpReq = false;
			var self = this;
			if (window.XMLHttpRequest) {
				self.xmlHttpReq = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			self.xmlHttpReq.open('POST', 'coapprAJXPREAction', false);
			self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
				if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200) {
					var a = self.xmlHttpReq.responseText;
					var b = new Array();
					b = a.split('#');
	
	
					if (b[0] == 'Data Not Found') {
	
					} else {
						document.getElementById('fwd_custom').checked = false;
						alert(b[0]);
					}
	
				}
			}
	
			self.xmlHttpReq.send("YEAR=" + encodeURIComponent(YEAR) + "&COMPANY=" + encodeURIComponent(COMPANY) + "&INV_NO=" + encodeURIComponent(INV_NO) + "&time=" + (new Date()).getTime());
		}
	
		function xmlhttpreqGSTCMP(objsrc, objtrg) {
			var xmlHttpReq = false;
			var self = this;
			if (window.XMLHttpRequest) {
				self.xmlHttpReq = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			self.xmlHttpReq.open('POST', 'GSTCMPAJXPREAction', false);
			self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			self.xmlHttpReq.onreadystatechange = function() {
				if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200) {
					var a = self.xmlHttpReq.responseText;
					var b = new Array();
					b = a.split('#');
	
					if (b.length > 5) {
	
						document.getElementById('handlefrm').src = "gstcmpViewPREINVMVX.action?unitparam=" + objsrc.value + "&PARAA=MANUF_CODE&PARAB=MANUF_DESC";
						openpop('root');
						objsrc.value = '';
						document.getElementById(objtrg).value = '';
					} else {
						if (b[0] == 'Data Not Found') {
							objsrc.value = '';
							document.getElementById(objtrg).value = '';
							alert(b[0]);
							document.getElementById('handlefrm').src = "GSTCMPSearch.jsp";
							openpop('root');
						} else {
							objsrc.value = b[0];
							//  document.getElementById(objtrg).value=b[7];
	
							document.getElementById('MANUF_CODE').value = b[0];
							document.getElementById('MANUF_DESC').value = b[1];
							document.getElementById('INV_GEO').value = b[2];
							document.getElementById('INV_GSTIN').value = b[3];
							document.getElementById("searchplan").value = b[4];
	
						}
					}
				}
			}
			param = objsrc.value;
			self.xmlHttpReq.send("INV_COMP=" + encodeURIComponent(param) + "&time=" + (new Date()).getTime());
		}
		function tabE(obj, e) {
			var e = (typeof event != 'undefined') ? window.event : e; // IE : Moz
			if (e.keyCode == 13) {
				var ele = document.forms[0].elements;
				for (var i = 0; i < ele.length; i++) {
					var q = (i == ele.length - 1) ? 0 : i + 1; // if last element : if any other
					if (obj == ele[i]) {
						alert(document.getElementById("pre_carriage").value());
	
						if (obj == document.getElementById("mode_of_ship")) {
							document.getElementById("pre_carriage").focus();
							break;
						} else if (obj == document.getElementById("pre_carriage")) {
							document.getElementById("ac_holder").focus();
							break;
						}
	
	
	
					}
				}
				return false;
			}
		}
	</script>
</body>

</html>

