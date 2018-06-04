<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shahi Exports Pvt Ltd</title>
<style type="text/css">
.datelabel {
	font-size: 8pt;
	height: 13pt;
	width: 100pt;
	text-transform: uppercase
}

.txt-field {
   width:90% !important;
   font:tahoma !important;
   font-size:12px !important;
   border: 1px solid #BEBEBE !important;
   border-radius: 3px !important;
   text-transform:uppercase;
    margin-top:7px;
}
.green {
	background-color: LightGreen;
}

.yellow {
	background-color: yellow;
}

.red {
	background-color: red;
}

th {
	font-size: 9pt;
	font-weight: bold;
	color: #006699;
	background-image: url('image/table-gradient.jpg');
}

tbody {
	height: 500px;
	overflow-y: auto;
	overflow-x: hidden;
}

.label-1 {
	left: 0px;
	top: 15px;
	margin: 0px;
	padding: 0px;
	cursor: pointer;
	height: 11px;
	font-weight: bold;
	text-align: center;
	color: #223546;
	font-size: 9px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

a {
	color: #000000;
	text-decoration: none;
}

th {
    text-align: right !important;
    vertical-align: middle !important;
    margin-top:7px;
}

</style>
<!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height: 480px;
            width: 600px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 28px ;
        }

         }
        thead tr {

        }
        tbody {
            height: auto;
        }
          }
    </style>
<![endif]-->

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.7.3.custom.min.js"></script>
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script> --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableHeadFixer.js"></script>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" />

<link type="text/css"
	href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.7.3.custom.css"
	rel="stylesheet" />
<script language="javascript">

	$(document).ready(function() {
		$('#payment').tableHeadFixer();
	});
	$(function() {
		$("#fromDate").datepicker({
			dateFormat : 'dd/mm/yy',
			closeText : 'X'
		});
		$("#toDate").datepicker({
			dateFormat : 'dd/mm/yy',
			closeText : 'X'
		});
	});
	function validate() {
		var div = $('#division').val();
		if (div == '0') {
			alert('Please Select Division');
			return false;
		}
		return true;
	}
	function search() {
		if (validate()) {
			document.payment.action = "dataTexPaymentTrackerAction.action";
			document.payment.submit();
			$('#btn-find').attr("disabled", "disabled");
			$('#btn-report').attr("disabled", "disabled");
			document.getElementById('prepage').style.visibility = '';
		}
	}
	function report() {
		if (validate()) {
			document.payment.action = "dataTexReportPaymentTrackerAction.action";
			document.payment.submit();
			$('#btn-report').attr("disabled", "disabled");
			$('#btn-find').attr("disabled", "disabled");
		}
	}
</script>
</head>

</head>
</head>

<body>
	<DIV align="center" id="prepage"
		style="z-index: 1; position: absolute; top: 50px; left: 500px; background-color: transparent; visibility: hidden"
		class="lodingdiv">
		<img alt=""
			src="<%=request.getContextPath()%>/shahiwebpages/image/loading37.gif"
			width="200px" height="200px" /> <br /> <font
			style="color: blue; font-weight: bold; text-align: center;">Loading
			... ...</font>
	</DIV>
	<form action="" name="payment">
		<div class="flex-container">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Invoice Process Tracker</h4>
				</div>
				<br />
				<div class="row">
					<div class="col-xs-9">
						<table width="120%">
				   <tr>
				   		<th style="padding-top: 8px;">Division &nbsp;</th>
				   		<td>
				   			<s:select label="Type" cssClass="txt-field" theme="simple"
							name="search.division" id="division"
							list="#{'0':'Select Division','101':'KPD-Shimoga','105':'WPD - Shimoga'}" />
				   		</td>
				   		<th style="padding-top: 8px;">Status &nbsp;</th>
				   		<td>
				   			<s:select label="Type" cssClass="txt-field" theme="simple"
							name="search.paymentStatus" id="paymentStatus"
							list="#{'0':'Select Status','1':'Success','2':'Pending'}" />
				   		</td>
				   		
				   		<th style="padding-top: 8px;">Gate &nbsp;Entry&nbsp;Date&nbsp;From &nbsp;</th>
				   		<td>
				   			<s:textfield name="search.fromDate" id="fromDate" theme="simple"
							value="%{search.fromDate}" cssClass="txt-field" />
				   		</td>
				   		<th style="padding-top: 8px;">Gate &nbsp;Entry&nbsp;Date&nbsp;To &nbsp;</th>
				   		<td>
				   			<s:textfield name="search.toDate" id="toDate" theme="simple"
							value="%{search.toDate}" cssClass="txt-field" />
				   		</td>
				   		<th style="padding-top: 8px;">Responsibe&nbsp;</th>
				   		<td>
				   			<s:textfield cssClass="txt-field" theme="simple"
							name="search.responsible" id="responsible"
							value="%{search.responsible}" />
				   		</td>
				   		<td style="padding-top: 8px;">
				   			<button onclick="search()" class="btn btn-info btn-xs"
							id="btn-find">Find</button>
				   		</td>
				   		<td style="padding-top: 8px;">
				   			<button onclick="report()" class="btn btn-info btn-xs"
							id="btn-report">Report</button>
				   		</td>
				   </tr>
				</table>
					</div>
				</div>
				
				<div class="panel-body">
					<div class="table-responsive"
						style="overflow-x: auto; overflow-y: auto; height: 450px;">
						<table class="table table-fixed table-bordered table-condensed"
							id="payment">
							<thead>
								<tr class="info">
									<th class="label-1">S.No</th>
									<th class="label-1">Division</th>
									<th class="label-1">Status</th>
									<th class="label-1" style="width: 250px">Gate&nbsp;Date</th>
									<th class="label-1" style="width: 200px">MRNDATE</th>
									<th class="label-1">Received&nbsp;At&nbsp;Account</th>
									<th class="label-1">PI&nbsp;CREATION</th>
									<th class="label-1" style="width: 200px">Party &nbsp;Name</th>
									<th class="label-1">Invoice&nbsp;No</th>
									<th class="label-1">Invoice&nbsp;Date</th>
									<th class="label-1">PO&nbsp;No</th>
									<th class="label-1">Bill&nbsp;Currency</th>
									<th class="label-1">Bill&nbsp;Amt.</th>
									<th class="label-1">Remarks</th>
									<th class="label-1">Responsible</th>
								</tr>
							</thead>
							
							<s:iterator value="datatexPaymentList" id="itr" status="st">
								<tr bgcolor="white">
									<td class="label-1"><s:property value="%{#st.index+1}" /></td>
									<td class="label-1"><s:property value="%{divisionCode}" /></td>
									<td class="label-1"><s:property value="%{status}" /></td>
									<td class="label-1" style="width: 250px"><s:property
											value="%{gateDate}" /></td>
									<td class="label-1" style="width: 200px"><s:property
											value="%{mrnDate}" /></td>
									<td class="label-1"><s:property value="%{recvAtAcct}" /></td>
									<td class="label-1" style="width: 200px"><s:property
											value="%{piCreation}" /></td>
									<td class="label-1" style="width: 200px"><s:property
											value="%{partyName}" /></td>
									<td class="label-1"><s:property value="%{piInvno}" /></td>
									<td class="label-1 " style="width: 200px"><s:property
											value="%{piInvDate}" /></td>
									<td class="label-1"><s:property
											value="%{purchaseOrderCode}" /></td>
									<td class="label-1"><s:property
											value="%{invoiceCurrencyCode}" /></td>
									<td class="label-1"><s:property value="%{invoiceAmount}" /></td>
									<td class="label-1"><s:property value="%{remarks}" /></td>
									<td class="label-1"><s:property value="%{responsible}" /></td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
				<div class="panel-footer">
					<s:actionerror cssClass="text-danger" />
				</div>
			</div>
		</div>
	</form>
</body>

</html>

