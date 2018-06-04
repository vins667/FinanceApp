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
<Style>
.table-fixed {
	width: 100%; tbody { height : 200px;
	overflow-y: auto;
	width: 100%;
}

thead, tbody, tr, td, th {
	display: block;
}

tbody {td { float:left;
	
}

}
thead {tr { th{ float:left;
	background-color: #f39c12;
	border-color: #e67e22;
}
}
}
}
</Style>

<script type="text/javascript">
function waitPreloadPage() { //DOM
    if (document.getElementById){
        document.getElementById('prepage').style.visibility='hidden';
    }else{
        if (document.layers){ //NS4
            document.prepage.visibility = 'hidden';
        }
        else { //IE4
            document.all.prepage.style.visibility = 'hidden';
        }
    }
}
    $(document).ready(function(){
    	$('#uploadGST').tableHeadFixer();
    });
	function Upload() {
		var supplierGSTN=$('#company').val();
		var shahiGSTN=$('#shahiGSTN').val();
		var file=$('#upload-file').val();
		if(supplierGSTN=='0'){
			alert('Please Select Company');
			return false;
		}
		if(shahiGSTN=='-1'){
			alert('Please Select Shahi GSTN');
			return false;
			
		}
		if(file.length==0){
			alert('Please select an excel file');
			return false;
		}

		document.gst.action = "uploadGSTReconciliationAction.action";
		document.gst.submit();
		$('#btn-upload').attr("disabled","disabled");
		document.getElementById('prepage').style.visibility='';
	}
	
	
	function populateShahiGSTN(selectedvalue){
		$.ajax({
			url:'loadAjaxPopulateGSTN?division='+selectedvalue,
			type:'post',
			datatype:'json',
			success:function(data){
				var code=JSON.parse(data);
				var fileList=$('#shahiGSTN');
				fileList.find('option').remove();
				fileList.append("<option>Select Shahi GSTN</option>");
				for(var i=0;i<code.length;i++){
					$('#shahiGSTN').append(
	                '<option value=' + code[i].desc + '>' +code[i].desc + '</option>');
				}
			},
			error:function(data){
				var code=JSON.parse(data);
				alert(code);
			}
		});
	}
</script>
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
	<div class="container-fluid">
		<form name="gst" method="post" enctype="multipart/form-data">
			<s:hidden name="userId" value="%{userId}" />
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>GST Reconciliation</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-2">
							<label for="company">Company:</label>
						</div>
						<div class="col-xs-2">
							<s:select name="company" cssClass="form-control" id="company"
								list="companyList" listKey="code" listValue="desc"
								onchange="populateShahiGSTN(this.value)"
								cssStyle="text-transform:uppercase;width:100%" theme="simple" />
						</div>
						<div class="col-xs-4">
							<div class="col-xs-4">
								<label for="gstn">Shahi GSTN:</label>
							</div>
							<div class="col-xs-8">
								<s:select name="shahiGSTN" cssClass="form-control"
									id="shahiGSTN" list="gstnList" listKey="code" listValue="desc"
									cssStyle="text-transform:uppercase;width:100%" theme="simple" />
							</div>
						</div>
					</div>
					<br />
					<%-- <div class="row">
					  <div class="col-xs-1">
					     <label for="fromDate">From Date</label>
					  </div>
					  <div class="col-xs-2">
					     <s:textfield name="fromDate" id="fromDate" theme="simple" value="%{fromDate}" cssClass="form-control"/>
					  </div>
					  <div class="col-xs-4">
						   <div class="col-xs-4">
						     <label for="toDate">To Date</label>
						  </div>
						  <div class="col-xs-8">
						     <s:textfield name="toDate" id="toDate" theme="simple" value="%{toDate}" cssClass="form-control"/>
						  </div>
					  </div>
					</div> --%>
					<br />
					<br />
					<div class="row">
						<div class="col-xs-2">
							<s:file type="file" name="file" cssClass="form-control" id="upload-file"
								theme="simple" />
						</div>
						<div class="col-xs-2">
							<button class="btn btn-info btn-sm" onclick="Upload()" id="btn-upload">Upload</button>
						</div>
						<!-- <div class="col-xs-2">
							<button class="btn btn-info btn-sm" onclick="reconcil()">Reconciliation </button>
						</div>
						<div class="col-xs-2">
							<button class="btn btn-info btn-sm" onclick="report()">Report</button>
						</div> -->
					</div>

					<br /> <br />
					<div class="table-responsive"
						style="overflow-y: auto; height: 600px;">
						<table class=" table table-fixed table-bordered table-condensed"
							id="uploadGST">
							<thead>
								<tr class="info">
									<th>S.No</th>
									<th>Supplier GSTIN</th>
									<th>Counter Party Status</th>
									<th>Shahi GSTN</th>
									<th>Invoice Number</th>
									<th>Invoice Date</th>
									<th>Line Number</th>
									<th>Taxable Value</th>
									<th>Tax Value</th>
									<th>Rate</th>
									<!-- 								<th>SGST</th>
								<th>CGST</th>
								<th>IGST</th>
								<th>Cess</th>
 -->
									<th>Invoice Value</th>
									<th>Invoice Type</th>
									<th>POS</th>
									<th>Reverse Charge</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="list" status="incr">
									<tr>
										<td><s:property value="%{#incr.index+1}" /></td>
										<td><s:property value="SUPLGSTN" /></td>
										<td><s:property value="CNTPTYST" /></td>
										<td><s:property value="SHAHIGSTN" /></td>
										<td><s:property value="INVOICENO" /></td>
										<td><s:property value="INVOICEDT" /></td>
										<td><s:property value="LINE" /></td>
										<td><s:property value="lineItemAmount" /></td>
										<td><s:property value="TAXVALUE" /></td>
										<td><s:property value="TAXRATE" /></td>

										<%-- 									<td><s:property value="CGST" /></td>
									<td><s:property value="SGST" /></td>
									<td><s:property value="IGST" /></td>
									<td><s:property value="CESS" /></td>
 --%>
										<td><s:property value="INVVALUE" /></td>
										<td><s:property value="INVTYPE" /></td>
										<td><s:property value="POS" /></td>
										<td><s:property value="REVCHG" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
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
