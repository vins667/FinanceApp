<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>

<head>
<title>New Invoice</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/shahicss/common.css">
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.0.min.js"></script>

</head>
<body>
	<br />
	<br />
	<br />
	<div class="container">

		<div class="panel panel-info">
			<div class="panel-heading text-center font-weight-bold text-white">
				<span> Pre Shipment Invoice[M4] </span>
			</div>
			<br />
			<div class="penel-body">
				<div class="row">
					<div class="col-sm-1 rightL">
						<label>Loct:</label>
					</div>
					<div class="col-sm-1">
						<input type="text" class="txt-field"></input>
					</div>
					<div class="col-sm-2 rightL">
						<label>Invoice No:</label>
					</div>
					<div class="col-sm-1">
						<input type="text" class="txt-field"></input>
					</div>
					<div class="col-sm-1 rightL">
						<label>Plan No:</label>
					</div>
					<div class="col-sm-1">
						<input type="text" class="txt-field"></input>
					</div>
					<div class="col-xs-3 rightL">
						<button type="button" class="btn btn-basic">Go</button>
						<button type="button" class="btn btn-warning">Clear</button>
						<button type="button" class="btn btn-success">Save</button>
					</div>
				</div>
				<table class="table table-stripped">
				 <tr>
				    <td>Plan No</td>
				    <td><input type="text" class="txt-field"></input></td>
				     <td>AC Holder</td>
				    <td><input type="text" class="txt-field"></input></td>
				     <td>PPRQ Date</td>
				    <td><input type="checkbox" class="checkbox-inline" style="margin-top:10px"></input></td>
				     <td>Ship Cnxl</td>
				    <td><input type="checkbox" class="checkbox-inline" style="margin-top:10px"></input></td>
				     <td>Place</td>
				     <td><input type="text" class="txt-field"></input></td>
				     <td><input type="text" class="txt-field"></input></td>
				 </tr>
				  <tr>
				    <td>Invoice No</td>
				    <td><input type="text" class="txt-field"></input></td>
				     <td>Merchant</td>
				    <td><input type="text" class="txt-field"></input></td>
				     <td>Fwd Custom</td>
				    <td><input type="checkbox" class="checkbox-inline" style="margin-top:10px"></input></td>
				     <td>Out House</td>
				    <td><input type="checkbox" class="checkbox-inline" style="margin-top:10px"></input></td>
				     <td>Clearing Port</td>
				     <td><input type="text" class="txt-field"></input></td>
				     <td><input type="text" class="txt-field"></input></td>
				 </tr>
				 <tr>
				    <td>Invoice Date</td>
				    <td><input type="text" class="txt-field"></input></td>
				     <td>LC No</td>
				    <td><input type="text" class="txt-field"></input></td>
				     <td>TTO Date</td>
				    <td><input type="checkbox" class="checkbox-inline" style="margin-top:10px"></input></td>
				     <td>Upcharge %</td>
				    <td><input type="text" class="txt-field"></input></td>
				     <td>Loading Port</td>
				     <td><input type="text" class="txt-field"></input></td>
				     <td><input type="text" class="txt-field"></input></td>
				 </tr>
				</table>
			</div>
		</div>

	</div>
</body>
</html>

