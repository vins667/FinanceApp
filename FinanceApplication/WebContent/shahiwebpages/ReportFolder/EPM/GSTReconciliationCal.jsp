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
	src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	 --%>
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
		 $('#GSTTABLE').tableHeadFixer();
	});
	$(function() {
	    $( "#fromDate" ).datepicker({
	        dateFormat: 'dd/mm/yy',
	        closeText: 'X'
	    });  
	    $( "#toDate" ).datepicker({
	        dateFormat: 'dd/mm/yy',
	        closeText: 'X'
	    }); 
	});

	function validateInput(){
		var supplierGSTN=$('#company').val();
		var shahiGSTN=$('#gstn').val();
		
		var toDate=$('#toDate').val();
		var fromDate=$('#fromDate').val();
		var currentDate=new Date();
		
		var date=toDate.split('/');
		var date1=fromDate.split('/');
		var newDate=new Date(date[2],parseInt(date[1])-1,date[0]);
		var newFromDate=new Date(date1[2],parseInt(date1[1])-1,date1[0]);
		
		if(supplierGSTN=='0'){
			alert('Please Select Company');
			return false;
		}
		//alert('shahi'+shahiGSTN);
		if(shahiGSTN=='-1'){
			alert('Please Select Shahi GSTN');
			return false;
			
		}
		/* if(toDate==''){
			alert('Please Enter to Date');
			return false;
		} */
		if(Date.parse(newFromDate)>Date.parse(newDate)){
		    alert("From Date Can't be greater than To Date.");
		    return false;
		}
		if(Date.parse(newDate)>Date.parse(currentDate)){
		    alert("To Date Can't be greater than current Date.");
		    return false;
		}
		return true;
	}
	function reconcil(){
		if(validateInput()){
			document.gst.action = "reconciliationGSTReconciliationAction.action";
			document.gst.submit();
			$('#btn-reco').attr("disabled","disabled");
			document.getElementById('prepage').style.visibility='';
			return true;
		}
		return false;
	}
	function report(){
		//alert('report Method');
		if(validateInput()){
			//alert('validated Input');
			document.gst.action = "downloadGSTReconciliationAction.action";
			document.gst.submit();
			$('#btn-report').attr("disabled","disabled");
			document.getElementById('prepage').style.visibility='';
			return true;
		}
		return false;
	}
	function populateShahiGSTN(selectedvalue){
		$.ajax({
			url:'loadAjaxPopulateGSTN?division='+selectedvalue,
			type:'post',
			datatype:'json',
			success:function(data){
				var code=JSON.parse(data);
				var fileList=$('#gstn');
				fileList.find('option').remove();
				fileList.append("<option>Select Shahi GSTN</option>");
				for(var i=0;i<code.length;i++){
					$('#gstn').append(
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
		style="z-index: 1; position: absolute; top: 50px; left: 500px; background-color: transparent;visibility:hidden"
		class="lodingdiv">
		<img alt="" src="<%=request.getContextPath()%>/shahiwebpages/image/loading37.gif" width="200px" height="200px" />
		<br /> <font
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
						<div class="col-xs-1">
							<label for="company">Company:</label>
						</div>
						<div class="progress" style="display:none;z-index:999;">Please Wait</div>
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
								<s:select name="shahiGSTN" cssClass="form-control" id="gstn"
									list="gstnList" listKey="code" listValue="desc"
									cssStyle="text-transform:uppercase;width:100%" theme="simple" />
							</div>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-1">
							<label for="fromDate">From Date</label>
						</div>
						<div class="col-xs-2">
							<s:textfield name="fromDate" id="fromDate" theme="simple"
								value="%{fromDate}" cssClass="form-control" />
						</div>
						<div class="col-xs-4">
							<div class="col-xs-4">
								<label for="toDate">To Date</label>
							</div>
							<div class="col-xs-8">
								<s:textfield name="toDate" id="toDate" theme="simple"
									value="%{toDate}" cssClass="form-control" />
							</div>
						</div>
						<div class="col-xs-2">
							<button class="btn btn-info btn-sm" onclick="reconcil()" id="btn-reco">Reconciliation
							</button>
						</div>
						<div class="col-xs-2">
							<button class="btn btn-info btn-sm" onclick="report()" id="btn-report">Report</button>
						</div>
					</div>
					<br />
					<%-- <div class="panel panel-info">
						<div class="panel-heading">
							<h4>Search</h4>
						</div>
						<br />
						<div class="penel-body">
							<div class="row">
								<div class="col-xs-4">
									<div class="col-xs-4">
										<label for="supplierGSTN">Supplier GSTN:</label>
									</div>
									<div class="col-xs-8">
										<s:select name="vendorGSTN" cssClass="form-control" id="company"
											list="vendotGSTNList" listKey="code" listValue="desc"
											cssStyle="text-transform:uppercase;width:100%" theme="simple" />
									</div>
								</div>
								<div class="col-xs-4">
									<div class="col-xs-4">
										<label for="annexure">Annexure</label>
									</div>
									<div class="col-xs-8">
										<s:select name="annexure" cssClass="form-control"
											id="annexure"
											list="#{'0':'ALL','1':'Annexure1','2':'Annexure2'}"
											cssStyle="text-transform:uppercase;width:100%" theme="simple" />
									</div>
								</div>
								<div class="col-xs-2">
								  <button class="btn btn-info btn-sm" onclick="search()">Find
							</button>
								</div>
							</div>
							<br />
						</div>
					</div> --%>
					<!-- 	<div class="row">
						<div class="col-xs-2">
							<button class="btn btn-info btn-sm" onclick="reconcil()">Reconciliation </button>
						</div>
						<div class="col-xs-2">
							<button class="btn btn-info btn-sm" onclick="report()">Report</button>
						</div>
					</div> -->

					<div class="table-responsive"
						style="overflow-y: auto; height: 600px;">
						<table class=" table table-fixed table-bordered table-condensed"
							id="GSTTABLE">
							<thead>
								<tr class="info">
									<th>S.No</th>
									<th>GSTIN</th>
									<th>Shahi GSTN</th>
									<th>Invoice Number</th>
									<th>Invoice Date</th>
									<th>Line Number</th>
									<th>Taxable Value</th>
									<!-- 								<th>Rate</th>
								<th>SGST</th>
								<th>CGST</th>
								<th>IGST</th>
								<th>Cess</th> -->
									<th>Invoice Value</th>
									<th>Invoice Type</th>
									<th>POS</th>
									<th>Mismatch At</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="shahiGSTNList" status="incr">
									<tr>
										<td><s:property value="%{#incr.index+1}" /></td>
										<td><s:property value="SUPLGSTN" /></td>
										<td><s:property value="SHAHIGSTN" /></td>
										<td><s:property value="INVOICENO" /></td>
										<td><s:property value="INVOICEDT" /></td>
										<td><s:property value="LINE" /></td>
										<td><s:property value="TAXVALUE" /></td>
										<%--									<td><s:property value="TAXRATE" /></td>
 									<td><s:property value="CGST" /></td>
									<td><s:property value="SGST" /></td>
									<td><s:property value="IGST" /></td>
									<td><s:property value="CESS" /></td>
 --%>
										<td><s:property value="INVVALUE" /></td>
										<td><s:property value="INVTYPE" /></td>
										<td><s:property value="POS" /></td>
										<s:if test='%{ANX_TYPE=="1" && DOC_TYPE=="S"}'>
											<td>Invoice Missing At Shahi End</td>
										</s:if>
										<s:if test='%{ANX_TYPE=="2" && DOC_TYPE=="S"}'>
											<td>GST Tax is Mismatch</td>
										</s:if>
										<s:if test='%{ANX_TYPE=="1" && DOC_TYPE=="C"}'>
											<td>Invoice Missing At Vendors End</td>
										</s:if>
										<s:if test='%{ANX_TYPE=="2" && DOC_TYPE=="C"}'>
											<td>GST Tax is Mismatch</td>
										</s:if>
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
