<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="<s:url value="../css/main.css"/>" rel="stylesheet"
	type="text/css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shahi Exports Pvt Ltd</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.7.3.custom.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableHeadFixer.js"></script>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" />
<link type="text/css"
	href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.7.3.custom.css"
	rel="stylesheet" />
<style type="text/css">
.datelabel {
	font-size: 8pt;
	height: 13pt;
	width: 100pt;
	text-transform: uppercase
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

.Btn {
	background-color: #BDC7CE;
	background-image: URL(../image/glossyback.gif);
	background-repeat: repeat-x;
	border: 1px solid #677788;
	padding-top: 2px;
	padding-bottom: 4px;
	padding-left: 10px;
	padding-right: 10px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #223546;
	cursor: hand;
	width: 130px;
	height: 10pt;
	text-align: center;
}

.stylelabel {
	left: 0px;
	top: 15px;
	margin: 0px;
	padding: 0px;
	cursor: pointer;
	height: 11px;
	font-weight: bold;
	text-align: center;
	color: #223546;
	font-size: 12px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

.txt-field {
	width: 90% !important;
	font: tahoma !important;
	font-size: 12px !important;
	border: 1px solid #BEBEBE !important;
	border-radius: 3px !important;
	text-transform: uppercase;
	margin-top: 7px;
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
<script language="javascript">

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

       
            function selectall()
            {
                var a=document.frm.fwdrec;

                if(a.length>0)
                {
                    if(document.frm.selall.checked==true)
                    {  for (var i=0;i<a.length;i++)
                        {a[i].checked=true;  }
                    }else
                    {
                        for (var i=0;i<a.length;i++)
                        {a[i].checked=false;  }
                    }
                }
                else{
                    if(document.frm.seleall.checked==true)
                    {a.checked=true; }
                    else
                    {a.checked=false; }
                }
            }

            function recfwd()
            {
                if(confirm('You Want to Accept ? '))
                {
                    document.frm.action="FundRequest.action?acFlag=Yes"
                    document.frm.submit()
                }
            } 
            function searchrec(){
                document.frm.action="queryUTRNOUpdation.action";
                document.frm.submit();
            }
            
            function doSave(){ 
          	   $('#frm').attr('action','updateChequeUTRNOUpdation.action');
               $('#frm').submit();
           }
            function toggleControls(index,value){
            	var id=index.split("#")[1];
            	if(value.checked){
            		$('#votype'+id).removeAttr("disabled");
            		$('#vono'+id).removeAttr("disabled");
            		$('#'+id).removeAttr("disabled");
            		$('#chq'+id).removeAttr("disabled");
            		$('#chqDt'+id).removeAttr("disabled");
            	}
            	if(!value.checked){
            		$('#votype'+id).attr("disabled","disabled");
            		$('#vono'+id).attr("disabled","disabled");
            		$('#'+id).attr("disabled","disabled");
            		$('#chq'+id).attr("disabled","disabled");
            		$('#chqDt'+id).attr("disabled","disabled");
            	}
            }
            function populateCalender(id){
            	$('#'+id).datepicker({
        			dateFormat : 'dd/mm/yy',
        			closeText : 'X'
        		});
            }
            function validateVoucher(index,value){
            	var vtype=$('#votype'+index).val();
            	var vono=$('#vono'+index).val();
            	var year=value;
            	
            	$.ajax({
        			url : 'validateVoucherAjaxAction?votype=' + vtype+"&vono="+vono+"&year="+year,
        			type : 'post',
        			datatype : 'json',
        			success : function(data) {
         				var result=data.split(":");
        				if(result[1].substring(0,7)=='"false"'){
        					alert('Vocuher No :'+vono+' is not valid');
        				}
        			},
        			error : function(data) {
        				var code = JSON.parse(data);
        				alert(code);
        			}
        		});
            }
        </script>

</head>

<body class="body1">
	<form action="#" method="post" name="frm" id="frm">
		<table align="center" width="100%">
			<tr>
				<td valign="middle"
					style="border-width: 1pt; border-color: black; border-style: solid;">
					<table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center"
						width="100%">
						<tr>
							<td class="hd" style="font-size: 18px; text-align: center">Manually
								Update Utr No</td>
						</tr>
						<tr>
							<td>
								<table align="center" width="100%">
									<tr>
										<td>
											<table align="center" width="100%">
												<tr>
													<td>
														<table width="100%" align="center">
															<tr>
																<td height="30pt" width="100%" valign="top"
																	style="border-width: 0pt; border-color: #2680b5; border-style: solid;">
																	<table align="center" width="85%" cellpadding="3"
																		border="0">
																		<s:hidden name="userId" value="%{userId}" />
																		<tr>
																			<td class="label-1">Req No:<s:textfield
																					cssClass="txt-field" name="query.reqNo"
																					theme="simple" cssStyle="width:70pt;font-size:9pt" /></td>
																			<td class="label-1">Req Date From:<s:textfield
																					name="fromDate" cssClass="txt-field"
																					theme="simple" cssStyle="width:70pt;font-size:9pt"
																					id="fromDate" /></td>
																			<td class="label-1">Req Date To :<s:textfield
																					name="toDate" cssClass="txt-field" theme="simple"
																					cssStyle="width:70pt;font-size:9pt" id="toDate" /></td>
																			<td class="label-1">Party :<s:textfield
																					cssClass="txt-field" name="query.party"
																					theme="simple" cssStyle="width:70pt;font-size:9pt" /></td>
																			<td class="label-1">Req Type :<s:select  
																					label="Type" theme="simple" name="query.reqType" cssClass="txt-field"
																					list="reqtyplist" listKey="REQ_CODE"
																					listValue="REQ_NAME" headerKey="" headerValue="All" /></td>

																		</tr>
																		<tr>

																			<td class="label-1">Status :<s:select
																					label="Type" cssStyle="width:70pt;font-size:9pt" cssClass="txt-field"
																					theme="simple" name="query.status"
																					list="#{'':'ALL','1':'Request Made','2':'Accepted by A/C','3':'Under Process','4':'Cheque Delivered'}" /></td>
																			<td class="label-1">Requested To : <s:select
																					label="Type" cssStyle="width:70pt;font-size:9pt" cssClass="txt-field"
																					theme="simple" name="query.requestedTo"
																					list="payloctlist" listKey="REQ_CODE"
																					listValue="REQ_NAME" headerKey="" headerValue="All" /></td>
																			<td class="label-1">Requested By : <s:textfield cssClass="txt-field"
																					cssStyle="width:70pt;font-size:9pt" theme="simple"
																					name="query.requestedBy"
																					title="Enter Employee Code" /></td>
																			<td class="label-1">PO No :<s:textfield cssClass="txt-field"
																					 name="query.poNo"
																					theme="simple" cssStyle="width:70pt;font-size:9pt" /></td>
																			<td align="right"><input type="button"
																				name="bnt" style="width: 50pt" onclick="searchrec()"
																				value="Search" class="submitbutton1" /> <input
																				type="button" name="newbnt" style="width: 50pt"
																				value="Save" onclick="doSave()"
																				class="submitbutton1" /> <input type="button"
																				name="exitbnt" style="width: 50pt"
																				onclick="self.close();" value="Exit"
																				class="submitbutton1" /></td>

																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="100pt" valign="top"
														style="border-width: 1pt; border-color: #2680b5; border-style: solid;">
														<div class="div1" style="width: 100%; overflow: auto;">
															<table width="100%" cellpadding="1" bgcolor="#d0d7e5" 
																cellspacing="1">
																<thead>
																	<tr
																		style="background-color: #6699cc; position: absolute; top: expression(this.offsetParent.scrollTop); height: 20pt">
																		<th class="label-1 ">S.No#</th>
																		<th class="label-1 ">Req No</th>
																		<th class="label-1">Req Date</th>
																		<th class="label-1">Party</th>
																		 <th class="label-1" >Pymt Text</th>
																		<th width="10%">UTR No</th>
																		 <th  width="10%">UTR Date</th>
																		<th width="10%">VO Type</th>
																		<th width="10%">VONO</th>
																		<th width="10%">VO Year</th>
																		<th class="label-1">Status</th>
																		<th class="label-1">Save</th>
																	</tr>
																</thead>
																
																<s:iterator value="detaillst" id="itr" status="st">
																	<s:hidden name="detaillst[%{#st.index}].REQNO"
																		id="REQNO%{#st.index}" value="%{REQNO}" />
																		<s:hidden name="detaillst[%{#st.index}].REQSTS"
																		id="REQSTS%{#st.index}" value="%{REQSTS}" />
																	<tr bgcolor="white">
																		<td class="label-1"><s:property
																				value="%{#st.index+1}" /></td>
																		<td align="center"
																			style="font-size: 9pt; font-weight: bold;"><s:property
																				value="%{REQNO}" /></td>
																		<td class="label-1"><s:property value="%{REQDT}" /></td>
																		<td class="label-1"><s:property
																				value="%{REQSUNO}" /></td>
																		<td class="label-1"><s:property value="%{REQTXT}" /></td>
																		<td class="label-1" width="5%"><s:textfield
																				name="detaillst[%{#st.index}].REQCHQ" disabled="true"
																				value="%{REQCHQ}" theme="simple"
																				id="chq%{#st.index}" /></td>
																		<td width="5%"><s:textfield name="detaillst[%{#st.index}].REQCHQDT"  disabled="true" cssClass="txt-field" id="chqDt%{#st.index}" onfocus="populateCalender(this.id)" theme="simple" cssStyle="width:40pt;font-size:9pt"  /></td>
																		<td width="5%"><s:select
																				name="detaillst[%{#st.index}].VOTYPE" disabled="true"
																				list="%{VOTYPES}" listKey="code" listValue="desc"
																				id="votype%{#st.index}" theme="simple" /></td>
																		<td width="5%"><s:textfield disabled="true" name="detaillst[%{#st.index}].REQVONO" theme="simple"
																			id="vono%{#st.index}"/></td>
																		<td width="5%"><input type="text" name="voDate" disabled="disabled"
																			onblur="validateVoucher(this.id,this.value)"
																			id="<s:property value='%{#st.index}'/>" /></td>
																		<td class="label-1"><s:property value="%{REQSTS}" /></td>
																		<td class="label-1"><s:checkbox
																				fieldValue="%{#st.index}" name="saveList"
																				theme="simple" value="false"
																				onclick="toggleControls(this.id,this)"
																				id="checkbox#%{#st.index}"></s:checkbox></td>
																	</tr>
																</s:iterator>
																
															</table>
														</div>

													</td>
												</tr>

											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<s:iterator value="exceptionList">
				<tr>
					<td>
						<table border="0" align="center" width="100%">
							<tr>
								<td height="1pt" align="center"
									style="color: red; font-weight: bold; font-size: 15px;">
									<div style="height: 10pt">
										<s:property value="%{message}"/>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</s:iterator>
		</table>
     
	</form>
</body>

</html>

