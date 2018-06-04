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

.green{
 background-color:LightGreen ;
}
.yellow{
 background-color:yellow;
}
.red{
 background-color:red;
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
.txt-field {
   width:90% !important;
   font:tahoma !important;
   font-size:12px !important;
   border: 1px solid #BEBEBE !important;
   border-radius: 3px !important;
   text-transform:uppercase;
    margin-top:7px;
    
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
	font-size: 10px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

a {
	color: #000000;
	text-decoration: none;
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
	function validate(){
		var div=$('#division').val();
		if(div=='0'){
			alert('Please Select Division');
			return false;
		}
		return true;
	}
	function search() {
		if(validate()){
			document.payment.action = "searchPaymentTrackerAction.action";
			document.payment.submit();
			$('#btn-find').attr("disabled", "disabled");
			$('#btn-report').attr("disabled", "disabled");
			document.getElementById('prepage').style.visibility = '';
		}
	}
	function report() {
		if(validate()){
			document.payment.action = "reportPaymentTrackerAction.action";
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
					<h4>Payment Tracker</h4>
				</div>
				<br />
				<div class="row"> 
					<div class="col-xs-1" style="text-align: right;">
						<label for="division">Division</label>
					</div>
					<div class="col-xs-2">
						<s:select name="search.division" cssClass="txt-field" id="division"
							list="cmpList" listKey="code" listValue="desc"
							cssStyle="text-transform:uppercase;width:100%" theme="simple" />
					</div>
					<div class="col-xs-1" style="text-align: right;">
						<label for="payroll" style="text-align: right;">Payroll</label>
					</div>
					<div class="col-xs-2">
						<s:select name="search.payroll" cssClass="txt-field" id="payroll"
							list="payrollList" listKey="code" listValue="desc"
							cssStyle="text-transform:uppercase;width:100%" theme="simple" />
					</div>
					<div class="col-xs-1" style="text-align: right;">
						<label for="account" style="text-align: right;">Account</label>
					</div>
					<div class="col-xs-2">
						<s:select name="search.account" cssClass="txt-field" id="account"
							list="accountList" listKey="code" listValue="desc"
							cssStyle="text-transform:uppercase;width:100%" theme="simple" />
					</div>

				</div>
				
				<div class="row">
					<div class="col-xs-1" style="text-align: right;">
						<label for="status" style="text-align: right;">Payment&nbsp;Status</label>
					</div>
					<div class="col-xs-2">
						<s:select label="Type" cssClass="txt-field" name="search.paymentStatus"
							theme="simple" id="paymentStatus"
							list="#{'0':'Select Payment Status','1':'Yes','2':'No'}" />
					</div>
					<div class="col-xs-1" style="text-align: right;">
						<label for="age" style="text-align: right;">Age</label>
					</div>
					<div class="col-xs-2">
						<s:select label="Type" cssClass="txt-field" name="search.age"
							theme="simple" id="age"
							list="#{'0':'','0 - 7':'0 - 7 Days','8 - 14':'8 - 14 Days','15 - 21':'15 - 21 Days','22 - 28':'22 - 28 Days'}" />
					</div>
					<div class="col-xs-1" style="text-align: right;">
						<label for="supplier" style="text-align: right;">Supplier</label>
					</div>
					<div class="col-xs-2">
							<s:textfield name="search.supplier" id="supplier" theme="simple"
								value="%{search.supplier}" cssClass="txt-field" />
						</div>
					<div class="col-xs-1">
							<button onclick="search()" class="btn btn-info btn-xs" id="btn-find">Find</button>
						</div>
						<div class="col-xs-1">
							<button onclick="report()" class="btn btn-info btn-xs " id="btn-report">Report</button>
						</div>
				</div>
			
				<div class="row">
					<div class="col-xs-12">

						<div class="col-xs-1">
							<label for="fromDate">Req&nbsp;Date&nbsp;From</label>
						</div>
						<div class="col-xs-2">
							<s:textfield name="search.fromDate" id="fromDate" theme="simple"
								value="%{search.fromDate}" cssClass="txt-field" />
						</div>
						<div class="col-xs-1">
							<label for="toDate">Req&nbsp;Date&nbsp;To</label>
						</div>
						<div class="col-xs-2">
							<s:textfield name="search.toDate" id="toDate" theme="simple"
								value="%{search.toDate}" cssClass="txt-field" />
						</div>
						<div class="col-xs-1">
							<label for="reqstatus">Req&nbsp;Status</label>
						</div>
						<div class="col-xs-2">
							<s:select label="Type" cssClass="txt-field" theme="simple" name="search.reqStatus" id="reqstatus"
								list="#{'0':'ALL','1':'Status-1','2':'Status-2','3':'Status-3','4':'Status-4','5':'Status-5','6':'Status-6','7':'Status-7','8':'Status-8','9':'Status-9'}" />
						</div>
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
									<th class="label-1">Div</th>
									<th class="label-1" style="width: 200px">Party Name</th>
									<th class="label-1">Amount</th>
									<th class="label-1">Purpose</th>
									<th class="label-1">Req No</th>
									<th class="label-1">Sent Date</th>
									<th class="label-1">No Of Days</th>
									<th class="label-1">Status</th>
									<th class="label-1" style="background-color:LightGreen;">0-6 Days</th>
									<th class="label-1" style="background-color:yellow">6-15 Days</th>
									<th class="label-1" style="background-color:red">15 and Above</th>
									<th class="label-1">Payment Status</th>
									<th class="label-1">UTR#</th>
									<th class="label-1">UTR Date</th>
									<th class="label-1">Payment LeadTime</th>
									<th class="label-1">Age</th>
								</tr>
							</thead>
							<s:iterator value="paymentList" id="itr" status="st">
								<tr bgcolor="white">
									<td class="label-1"><s:property value="%{#st.index+1}" /></td>
									<td class="label-1"><s:property value="%{division}" /></td>
									<td class="label-1" style="width: 200px"><s:property
											value="%{partyName}" /></td>
									<td class="label-1"><s:property value="%{reqAmount}" /></td>
									<td class="label-1"><s:property value="%{reqPurpose}" /></td>
									<td class="label-1"><s:property value="%{reqNumber}" /></td>
									<td class="label-1"><s:property value="%{reqDate}" /></td>
									<td class="label-1"><s:property value="%{noOfDays}" /></td>
									<td class="label-1"><s:property value="%{reqStatus}" /></td>
									<s:if test="%{zeroToSixDays}">
										<td class="label-1 green"><s:property value="%{reqAmount}" /></td>
									</s:if>
									<s:else>
										<td></td>
									</s:else>
									<s:if test="%{sixToFifteenDays}">
										<td class="label-1 yellow"><s:property value="%{reqAmount}" /></td>
									</s:if>
									<s:else>
										<td></td>
									</s:else>
									<s:if test="%{fiteenAndAboveDays}">
										<td class="label-1 red"><s:property value="%{reqAmount}" /></td>
									</s:if>
									<s:else>
										<td></td>
									</s:else>
									<td class="label-1"><s:property value="%{paymentStatus}" /></td>
									<td class="label-1"><s:property value="%{chequeNo}" /></td>
									<td class="label-1"><s:property value="%{utrDate}" /></td>
									<td class="label-1"><s:property
											value="%{paymentLeadTimePending}" /></td>
									<td class="label-1"><s:property value="%{age}" /></td>

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

