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
	
	$(function() {
        $( "#shippingBillDate" ).datepicker({
            dateFormat: 'dd/mm/yy',
            closeText: 'X'
        });  
    });
	function query(){
		var invoiceno=document.getElementById("exciseInvoiceNo").value;
		if(invoiceno==''){
			alert('Enter Excise Invoice No');
			document.getElementById("exciseInvoiceNo").focus();
			return false;
		}
		document.bill.action="ShippingBillAction.action";
		document.bill.submit();
	}
	
	function update(){
		var billno=document.getElementById("shippingBillNo").value;
		var billDate=document.getElementById("shippingBillDate").value;
		var date=billDate.split('/');
		var newDate=new Date(date[2],parseInt(date[1])-1,date[0]);
		var oneDay = 24*60*60*1000;
		 var currentDate=new Date();
		
		 var diffDays = Math.round(Math.abs((currentDate.getTime() - newDate.getTime())/(oneDay)));
		//alert('diffDays'+diffDays);
		
		if(diffDays>365){
			alert("Bill Date can't be less than 365 Days");
			document.getElementById("shippingBillDate").focus();
			return false;
		}
		if(newDate>currentDate){
			alert("Bill Date can't be greater than current Date");
			document.getElementById("shippingBillDate").focus();
			return false;
		}
		if(billno=="" && billDate==""){
			alert("Enter either Shipping Bill No or Shipping Bill Date");
			document.getElementById("shippingBillNo").focus();
			return false;
		}
	    document.bill.action="updateShippingBillAction.action";
		document.bill.submit(); 
	}
	</script>
</head>
<body>
<div class="container-fluid">
	<form name="bill" action="">
	
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Shipping Bill Search</h4>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-xs-2">
						<label for="batchNo">Excise Invoice No:</label>
					</div>
					<div class="col-xs-2">
						<s:textfield cssClass="form-control" name="exciseInvoiceNo" id="exciseInvoiceNo"/>
					</div>
					<div class="col-xs-2">
						<button class="btn btn-info btn-sm" onclick="query()">Search</button>
					</div>
				</div>
				<br /> <br />
				<div class="row">
					<div class="col-xs-2">
						<label for="shippingBillNo">Shipping Bill No:</label>
					</div>
					<div class="col-xs-2">
						<s:textfield cssClass="form-control" name="shippingBillNo" id="shippingBillNo"/>
					</div>
					<div class="col-xs-3">
						<label for="shippingBillDate">Shipping Bill Date(MM/DD/YYYY):</label>
					</div>
					<div class="col-xs-2">
						<s:textfield cssClass="form-control" name="shippingBillDate" id="shippingBillDate"/>
					</div>
					<div class="col-xs-2">
						<button class="btn btn-info btn-sm" onclick="update()">Update</button>
					</div>

				</div>
				<br/><br/>
				<table class="table table-bordered table-condensed">
					<tr class="info">
						<th>Invoice No</th>
						<th>Buyer Code</th>
						<th>Excise Invoice No</th>
						<th>Shipping Bill No</th>
						<th>Shipping Bill Date</th>						
						<th>PORT</th>
						<th>AMOUNT(FC)</th>
						<th>CURRENCY CODE</th>
					</tr>
					<s:iterator value="billList">
						<tr>
							<td><s:property value="XSIVNO" /></td>
							<td><s:property value="XSCUNO" /></td>
							<td><s:property value="XSEXIV" /></td>
							<td><s:property value="XSSBNO" /></td>
							<td><s:property value="XSSBDT" /></td>
							<td><s:property value="XSREM2" /></td>
							<td><s:property value="XSCUAM" /></td>
							<td><s:property value="XSCUCD" /></td>
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
