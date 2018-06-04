<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Shahi Exports Pvt Ltd</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.7.3.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.7.3.custom.css"
	rel="stylesheet" />
	<script type="text/javascript">
	
	function search(){
		document.cheque.action="PayrollChequesSearchAction.action";
		document.cheque.submit();
	}
	</script>
</head>
<body>
<div class="container-fluid">
	<form name="cheque">
	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Payroll Cheque Search</h4>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-1">
						<label for="batchNo">Batch No:</label>
					</div>
					<div class="col-xs-2">
						<s:textfield cssClass="form-control" name="batchNo"/>
					</div>
					<div class="col-xs-2">
						<button class="btn btn-info btn-sm" onclick="query()">Search</button>
					</div>

				</div>
				<br /> <br />
				
				<table class="table table-bordered table-condensed">
				 <!-- <caption>Entry in GLS037/GLS047</caption> -->
				   <tr class="info">
				     <td colspan="9"  class="lead text-center">Entry in GLS037/GLS047</td>
				   </tr>
					<tr class="info">
						<th>Division</th>
						<th>Year</th>
						<th>Book Of Account</th>
						<th>Voucher No</th>
						<th>Cheque Date</th>						
						<th>Cheque Amount</th>
						<th>GL Code</th>
						<th>BatchNo</th>
						
						<th>Cheque No</th>
					</tr>
					<s:iterator value="payrollCheque.chequeList">
						<tr>
							<td><s:property value="acdivi" /></td>
							<td><s:property value="acyea4" /></td>
							<td><s:property value="acvser" /></td>
							<td><s:property value="acvono" /></td>
							<td><s:property value="acacdt" /></td>
							<td><s:property value="acacam" /></td>
							<td><s:property value="acait1" /></td>
							<td><s:property value="acait6" /></td>
							<td><s:property value="acait7" /></td>
						</tr>
					</s:iterator>
				</table>
				<br /> <br />
				
				<table class="table table-bordered table-condensed">
				 <tr class="info">
				     <td colspan="9"  class="lead text-center">Entry in GLS200</td>
				   </tr>
				 
					<tr class="info">
						<th>Division</th>
						<th>Year</th>
						<th>Book Of Account</th>
						<th>Voucher No</th>
						<th>Cheque Date</th>						
						<th>Cheque Amount</th>
						<th>GL Code</th>
						<th>BatchNo</th>
						<th>Cheque No</th>
					</tr>
					<s:iterator value="payrollCheque.ledgerList">
						<tr>
							<td><s:property value="egdivi" /></td>
							<td><s:property value="egyea4" /></td>
							<td><s:property value="egvser" /></td>
							<td><s:property value="egvono" /></td>
							<td><s:property value="egacdt" /></td>
							<td><s:property value="egacam" /></td>
							<td><s:property value="egait1" /></td>
							<td><s:property value="egait6" /></td>
							<td><s:property value="egait7" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="panel-footer">
				 <s:actionerror cssClass="text-danger" />
	             <s:fielderror />
			</div>
		</div>
	</form>
	</div>
</body>
</html>
