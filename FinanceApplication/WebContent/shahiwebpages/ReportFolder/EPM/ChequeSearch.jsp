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
		document.cheque.action="ChequeSearchAction.action";
		document.cheque.submit();
	}
	</script>
</head>
<body>
<div class="container-fluid">
	<form name="cheque">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Cheque Query</h4>
			</div>
			<div class="panel-body">
				<div class="row">

					<div class="col-xs-1">
						<label for="searchBy">Search:</label>
					</div>
					<div class="col-xs-2">
						<s:select name="query.searchKey" cssClass="form-control" id="searchBy"
							list="#{'1':'Narration','2':'Amount','3':'Supplier Code'}"
							cssStyle="text-transform:uppercase;width:100%" theme="simple" />
					</div>
					<div class="col-xs-2">
						<s:textfield name="query.searchValue" cssClass="form-control"
							id="searchValue" cssStyle="text-transform:uppercase;width:100%"
							theme="simple" />
					</div>
					<div class="col-xs-2">
						<button class="btn btn-info btn-sm" onclick="query()">Search</button>
					</div>

				</div>

				<br /> <br />
				<table class="table table-bordered table-condensed">
					<tr class="info">
						<th>Company</th>
						<th>Division</th>
						<th>Year</th>
						<th>Book Of Account</th>
						<th>Voucher No</th>
						<th>BanK</th>
						<th>Cheque No</th>
						<th>Supplier Code</th>
						<th>Narration</th>
						<th>Cheque Amount</th>
						<th>Cheque Date</th>
						<th>GL Code</th>
					</tr>
					<s:iterator value="checkList">
						<tr>
							<td><s:property value="ckcono" /></td>
							<td><s:property value="ckdivi" /></td>
							<td><s:property value="ckyea4" /></td>
							<td><s:property value="ckvser" /></td>
							<td><s:property value="ckvono" /></td>
							<td><s:property value="ckbkid" /></td>
							<td><s:property value="ckchkn" /></td>
							<td><s:property value="ckspyn" /></td>
							<td><s:property value="cksunm" /></td>
							<td><s:property value="ckpycu" /></td>
							<td><s:property value="ckdtpr" /></td>
							<td><s:property value="ckait1" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="panel-footer">
				 <s:actionerror />
	             <s:fielderror />
			</div>
		</div>
	</form>
	</div>
</body>
</html>
