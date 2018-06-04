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
		document.cheque.action="MovexServerFileUploadAction.action";
		document.cheque.submit();
	}
	function populateFileType(selectedvalue){
		
		$.ajax({
			url:'populateFileTypeAjaxAction?division='+selectedvalue,
			type:'post',
			datatype:'json',
			success:function(data){
				var code=JSON.parse(data);
				var fileList=$('#fileList');
				fileList.find('option').remove();
				fileList.append("<option>Select Upload File For</option>");
				for(var i=0;i<code.length;i++){
					$('#fileList').append(
	                '<option value=' + code[i].code + '>' +code[i].desc + '</option>');
				}
			}
			
		});
	}
	function upload(){
		document.cheque.action="uploadFileMovexServerFileUploadAction";
		document.cheque.submit();
	}
	</script>
</head>
<body>
   <form name="cheque" method="post" enctype="multipart/form-data">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Movex Server File Upload(GLS850)</h4>
			</div>
			<div class="panel-body">

				<div class="row">
					<div class="col-xs-2 col-xs-offset-3">
						<label for="cmpList">Company</label>
					</div>
					<div class="col-xs-2 ">
						<s:select cssClass="dropdown" onkeypress="return tabE(this,event)"
							id="cmpList" name="division" theme="simple" list="cmpList"
							listKey="code" listValue="code+' - '+desc" headerKey="ALL" onchange="populateFileType(this.value)"
							headerValue="ALL" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2 col-xs-offset-3">
						<label for="cmpList">Upload File For</label>
					</div>
					<div class="col-xs-2 ">
						<s:select cssClass="dropdown" onkeypress="return tabE(this,event)"
							id="fileList" name="fileType" theme="simple" list="fileList"
							listKey="code" listValue="desc" headerKey="ALL"
							headerValue="ALL" />
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2 col-xs-offset-3">
						<label>Upload File From</label>
					</div>
					<div class="col-xs-2 ">
						<s:file type="file" cssClass="btn btn-primary btn-xs"  theme="simple" name="unfile"/>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2 col-xs-offset-3">
						<label for="cmpList">Upload File To</label>
					</div>
					<div class="col-xs-2 ">
						<p class="font-weight-bold">Movex Server(GLS850)</p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2 col-xs-offset-3">
						<label class="font-weight-bold text-danger">Note</label>
					</div>
					<div class="col-xs-3">
						<label class="font-weight-bold text-primary">System Auto Run GLS850 in M4, When you press File Upload Button</label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2 col-xs-offset-3">
						<label class="font-weight-bold text-danger">&nbsp;</label>
					</div>
					<div class="col-xs-1">
						<button  type="button" class="btn btn-success btn-xs" onclick="upload()">File Upload</button>
					</div>
					<div class="col-xs-1">
						<button  class="btn btn-warning btn-xs" onclick="javascript:window.close()">Exit</button>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<s:actionerror />
				<s:fielderror />
			</div>
		</div>
	</form>
</body>
</html>
