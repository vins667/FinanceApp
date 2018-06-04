<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shahi Exports Pvt Ltd.</title>
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
<script type="text/javascript" language="javascript">

$(document).ready(function (){
	$('#sourcevendorList').dblclick(function (){
	    var selectedOpts = $('#sourcevendorList option:selected');
		$('#targetvendorList').append($(selectedOpts).clone());
        $(selectedOpts).remove();
	});
	$('#targetvendorList').dblclick(function (){
	    var deleteOpts=$('#targetvendorList option:selected');
       $(deleteOpts).remove();
	});
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

	function populateDivision(selectedvalue) {
		$.ajax({
			url : 'populateDivisionAjaxAction?company=' + selectedvalue,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				var code = JSON.parse(data);
				var fileList = $('#division');
				fileList.find('option').remove();
				fileList.append("<option>Select Division</option>");
				for (var i = 0; i < code.length; i++) {
					$('#division').append(
						'<option value=' + code[i].code + '>' + code[i].desc + '</option>');
				}
			},
			error : function(data) {
				var code = JSON.parse(data);
				alert(code);
			}
		});
	}
	function populateYear(selectedvalue) {
		$.ajax({
			url : 'populateYearAjaxAction?division=' + selectedvalue,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				var code = JSON.parse(data);
				var fileList = $('#year');
				fileList.find('option').remove();
				for (var i = 0; i < code.length; i++) {
					$('#year').append(
						'<option value=' + code[i].code + '>' + code[i].desc + '</option>');
				}
			},
			error : function(data) {
				var code = JSON.parse(data);
				alert(code);
			}
		});
		
		populateVoucher();
	}
	function populateVoucher(){
		var division=$("#division").val();
		$.ajax({
			url : 'populateVoucherTypeAjaxAction?division=' + division,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				var code = JSON.parse(data);
				//var fileList = $('#voucherType');
				var table=$('#voucherType');
				for (var i = 0; i < code.length; i++) {
					var row="<tr> <td style='width: 15px;'> <input type='checkbox' name='vchtype' value='"+code[i].code+"'/></td>"
					         +"<td style='vertical-align: middle;'>"+code[i].code+'-'+code[i].desc+"</td></tr>"; 
					         table.append(row);
				}
			},
			error : function(data) {
				var code = JSON.parse(data);
				alert(code);
			}
		});
	}
	function search(){
		var customer=$('#supplierCode').val();
		$.ajax({
			url : 'searchVendorAjaxAction?customer=' + customer,
			type : 'get',
			datatype : 'json',
			success : function(data) {
				var code = JSON.parse(data);
				var fileList = $('#sourcevendorList');
				for (var i = 0; i < code.length; i++) {
					$('#sourcevendorList').append(
						'<option value=' + code[i].code + '>' + code[i].desc + '</option>');
				}
			},
			error : function(data) {
				var code = JSON.parse(data);
				alert(code);
			}
		});
	}
	
    function save(){
    	  setVendorCodes();
    	  setVoucherCodes();
    	  $('#GeneralLedgerReport').attr('action','generateTextFileCustomerTextFileGeneration.action');
          $('#GeneralLedgerReport').submit();
    } 
    
    function setVoucherCodes(){
    	var checkboxes=$("[name=vchtype]");
    	var vtypes='';
    	$("input:checked").each(function () {
    		vtypes+=$(this).val()+"#";
        });
      	$('#voucherCodes').val(vtypes);
    }
    function setVendorCodes(){
    	 var selectedOpts = $('#targetvendorList option');
    	 var vendorCodes='';
    	 $('#targetvendorList  > option').each(function() {
    		    vendorCodes+=$(this).val()+"#";
    		});
    	 $('#vendorCodes').val(vendorCodes);
   }
	function onSelectOptions() {
		document.GeneralLedgerReport.action = "ARLedgerReport.jsp";
		document.GeneralLedgerReport.submit();
	}

	function removeOptions() {
		var existglcode = document.getElementById("existglcode");
		removeValue(existglcode);
	}
	function validate() {
		company = document.getElementById("company");
		division = document.getElementById("division");
		year = document.getElementById("year");
		fromdate = document.getElementById("fromdate");
		date1 = document.getElementById("date1");
		vchtype = document.GeneralLedgerReport.vchtype;

		if (company.value == '') {
			alert('Company cannot be blanked..');
			company.focus();
			return false;
		}
		if (division.value == '') {
			alert('Division cannot be blanked..');
			division.focus();
			return false;
		}
		if (year.value == '') {
			alert('Year cannot be blanked..');
			year.focus();
			return false;
		}

		if (fromdate.value == '') {
			alert('From Date cannot be blanked..');
			fromdate.focus();
			return false;
		}
		if (date1.value == '') {
			alert('To Date cannot be blanked..');
			date1.focus();
			return false;
		}
		check = false;
		for (i = 0; i < vchtype.length; i++) {
			if (vchtype[i].checked == true) {
				check = true;
			}
		}
		if (check == false) {
			alert('Choose atleast 1 voucher Type');
			return false;
		}
		return true;
	}

	function VchChange(a) {
		vchtype = document.GeneralLedgerReport.vchtype;
		for (i = 0; i < vchtype.length; i++) {
			if (a == "40") {
				if (vchtype[i].value.substr(0, 2) == "40" || vchtype[i].value.substr(0, 2) == "42" ||
					vchtype[i].value.substr(0, 2) == "44") {
					vchtype[i].checked = true;
				}
			} else if (a == "10") {
				if (vchtype[i].value.substr(0, 2) == "10" || vchtype[i].value.substr(0, 1) == "J" ||
					vchtype[i].value.substr(0, 2) == "12" || vchtype[i].value.substr(0, 1) == "Z" ||
					vchtype[i].value.substr(0, 2) == "14" || vchtype[i].value.substr(0, 1) == "S" ||
					vchtype[i].value.substr(0, 2) == "20" || vchtype[i].value.substr(0, 1) == "O" ||
					vchtype[i].value.substr(0, 2) == "18" || vchtype[i].value.substr(0, 1) == "D") {
					vchtype[i].checked = true;
				}
			} else {
				if (vchtype[i].value.substr(0, 2) == a) {
					vchtype[i].checked = true;
				}
			}
		}
	}
	function onSubmit() {
		if (validate() == true) {
			var toSelect_Length = document.GeneralLedgerReport.existglcode.options.length;
			for (var i = 0; i < toSelect_Length; i++) {
				document.GeneralLedgerReport.existglcode.options[i].selected = true;
			}
			document.getElementById('submitbtn').disabled = true;
			document.getElementById('submitbtn').value = 'Please Wait...';
			document.getElementById('submitbtn').style.backgroundColor = "lightgray";
			document.getElementById('submitbtn').style.color = "white";
			document.GeneralLedgerReport.action = "ARLedgerReport.jsp?generate=exist";
			document.GeneralLedgerReport.submit();
		}
	}

	function selectAllVchType() {
		svchtype = document.GeneralLedgerReport.vchtype;
		for (i = 0; i < svchtype.length; i++) {
			svchtype[i].checked = true;
		}
	}
	function deselectAllVchType() {
		svchtype = document.GeneralLedgerReport.vchtype;
		for (i = 0; i < svchtype.length; i++) {
			svchtype[i].checked = false;
		}
	}
	
	
	
</script>
<style>
.txt-field {
	width: 90% !important;
	font: tahoma !important;
	font-size: 12px !important;
	border: 1px solid #BEBEBE !important;
	border-radius: 3px !important;
	text-transform: uppercase;
	margin-top: 7px;
}
</style>
</head>
<body>
	<div class="container">
		<form name="GeneralLedgerReport" method="post" action=""
			id="GeneralLedgerReport">
			<div class="panel panel-primary">

				<div class="panel-heading">
					<h3>Parameter For Customer Text File Generation</h3>
				</div>
				<div class="panel-body">
					<table class="table table-bordered table-condensed">

						<tr>
							<th>Company<span class="manded">*</span></th>
							<td><select id="company" class="txt-field" name="company"
								style="width: 350px;" class="texts"
								onChange="populateDivision(this.value)">

									<option value="0">Select Company</option>
									<option value="111">Shahi Group of Company - 111</option>
							</select></td>

							<th>Division<span class="manded">*</span></th>
							<td><s:select id="division" theme="simple"
									cssClass="txt-field" name="division" list="divisionList"
									listKey="code" listValue="desc"
									onchange="populateYear(this.value);" /></td>
						</tr>
						<tr>
							<th>Year<span class="manded">*</span></th>
							<td><s:select id="year" cssClass="txt-field" name="year"
									theme="simple" cssStyle="width: 350px;" list="yearList"
									listKey="code" listValue="desc" /></td>
							<td colspan="2">
								<button class="btn btn-warning btn-xs">Cancel</button>
								<button class="btn btn-danger btn-xs">Clear</button>
								<button class="btn btn-success btn-xs" onclick="save()">Save</button>
							</td>
						</tr>
						<tr>
							<th>From Date<span class="manded">*</span></th>
							<td><s:textfield id="fromDate" readonly="readonly"
									cssClass="txt-field" name="fromDate" theme="simple"
									onkeypress="return tabE(this,event)" /></td>
							<th>To Date<span class="manded">*</span></th>
							<td><s:textfield id="toDate" readonly="readonly"
									cssClass="txt-field" name="toDate" theme="simple" /></td>
						</tr>
						<tr>
							<th>Voucher Type<span class="manded">*</span></th>
							<td colspan="3">
							  <s:hidden name="voucherType" id="voucherCodes"/>
								<div
									style="width: 350px; height: 100px; overflow-y: auto; text-align: left">
									<table id="voucherType">
									<%-- 	<s:iterator value="voucherList" status="ct">
											<tr>
												<td style="width: 15px;"><s:checkbox value="false"
														name="vchtype" id="vchtype%{#ct.index}" theme="simple"
														fieldValue="%{code}" /></td>
												<td style="vertical-align: middle;"><s:property
														value="%{code}" />-<s:property value="%{desc}" /></td>
											</tr>
										</s:iterator> --%>
									</table>
								</div> <br /> <a href="#" onclick="VchChange('40')"
								class="btn btn-info btn-xs">BLR</a> &nbsp; <a href="#"
								onclick="VchChange('42')" class="btn btn-info btn-xs">TPUR</a>
								&nbsp; <a href="#" onclick="VchChange('44')"
								class="btn btn-info btn-xs">SHMG</a> &nbsp; <a href="#"
								onclick="VchChange('10')" class="btn btn-info btn-xs">FBAD</a>
								&nbsp; <a href="#" onclick="VchChange('16')"
								class="btn btn-info btn-xs">HBAD</a> &nbsp; <a href="#"
								onclick="selectAllVchType()" class="btn btn-info btn-xs">All</a>
								&nbsp; <a href="#" onclick="deselectAllVchType()"
								class="btn btn-info btn-xs">Clear</a>
							</td>
						</tr>
						<tr>
							<th>Customer &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<td colspan="3">
								<table width="100%">
									<tr>
										<td>Type one or more keywords separated by spaces.</td>
									</tr>
									<tr>
										<td style="width: 40%"><s:textfield theme="simple"
												name="supplierCode" id="supplierCode" cssClass="txt-field"
												cssStyle="width:10px" /></td>
										<td style="width: 40%"><input type="button"
											class="btn btn-info btn-xs" value="Search" onclick="search()" /></td>
									</tr>
									<tr>
										<td><select id="sourcevendorList" class="txt-field"
											multiple="multiple"
											style="width: 320px; height: 175px; margin-top: 7pt;"
											size="10"></select></td>
											<s:hidden name="vendorCodes" id="vendorCodes"/>
										<td><s:select id="targetvendorList" theme="simple" name="selectedVendors"
												cssClass="txt-field" multiple="true"
												cssStyle="width:320px;height:175px;margin-top:7pt;"
												size="10" list="vendorList" listKey="code" listValue="desc"
												onkeypress="return tabE(this,event)" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>