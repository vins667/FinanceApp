function loadAllLineItems(){
	var planNo=document.getElementById('planNo').value;
	var company=document.getElementById('COMPANY').value;
	var year=document.getElementById('YEAR').value;
	var invNo=document.getElementById('INV_NO').value;
	var flag=document.getElementById('GetDataFlag').value;
	var invDate=document.getElementById('inv_date').value;
	var accType=document.getElementById('ACCR_TYPE0');
	if(accType!=undefined){
		accType=document.getElementById('ACCR_TYPE0').value;
	}
	document.acc.action="loadAllAccessoriesItemsPREINVMVX.action?plan_no="+planNo+"&company="+company+"&year="+year+"&invNo="+invNo+"&GetDataFlag="+flag+"&accType="+accType+"&inv_date="+invDate;
	document.acc.method="post";
	document.acc.submit();
}
function saveItems(){
	var planNo=document.getElementById('planNo').value;
	var company=document.getElementById('COMPANY').value;
	var year=document.getElementById('YEAR').value;
	var invNo=document.getElementById('INV_NO').value;
	var invDate=document.getElementById('inv_date').value;
	document.acc.action="saveAccessoriesItemPREINVMVX.action?plan_no="+planNo+"&company="+company+"&year="+year+"&invNo="+invNo+"&inv_date="+invDate;;
	document.acc.method="post";
	document.acc.submit();
}
function deletetablerowaccr(accType,index) {
	var planNo=document.getElementById('planNo').value;
	var company=document.getElementById('COMPANY').value;
	var year=document.getElementById('YEAR').value;
	var invNo=document.getElementById('INV_NO').value;
	var invDate=document.getElementById('inv_date').value;
	document.acc.action="removeItemPREINVMVX.action?plan_no="+planNo+"&company="+company+"&year="+year+"&invNo="+invNo+"&inv_date="+invDate+"&accType="+accType+"&row="+index;
	document.acc.method="post";
	document.acc.submit();
}
/*function addsubtitleq1(a) {
			var CO_NO_E = document.frm.CO_NO_E;
			var CO_LINE_E = document.frm.CO_LINE_E;
			var ITEM_NO_E = document.frm.ITEM_NO_E;
			var QTY_ENDORS_E = document.frm.QTY_ENDORS_E;
			var ACCRTYPE = document.getElementById("ACCRTYPE").value;
			var DBK_ACCR_COPY = document.getElementById("DBK_ACCR_COPY").value;
			var STR_ACCR_COPY = document.getElementById("STR_ACCR_COPY").value;
			var ACCR_RATE_COPY = document.getElementById("ACCR_RATE_COPY").value;
	
			if (typeof CO_NO_E.length == "undefined") {
	
				var k = eval(document.getElementById('rownum').value) + eval(document.getElementById('accrctn').value);
				var str1 = '<tr bgcolor="#FFFFFF" id=' + k + '><td><s:textfield name="ACCR_CO_NO" readonly="true"  value="' + CO_NO_E.value + '" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
				str1 += '<td><s:textfield name="ACCR_CO_LINE" readonly="true" value="' + CO_LINE_E.value + '" theme="simple" cssClass="texts" cssStyle="width:60px"/> </td>';
				str1 += '<td><s:textfield name="ACCR_ITEM_NO" readonly="true" value="' + ITEM_NO_E.value + '"  theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
				str1 += '<td><s:textfield name="ACCR_TYPE" readonly="true" value="' + ACCRTYPE + '" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
				str1 += '<td><s:textfield name="ACCR_DBKSLNO" readonly="true" value="' + DBK_ACCR_COPY + '" theme="simple" cssClass="texts" cssStyle="width:80px"/> </td>';
				str1 += '<td><s:textfield name="ACCR_STRSLNO" readonly="true" value="' + STR_ACCR_COPY + '" theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
				str1 += '<td><s:textfield name="ACCR_QTY" readonly="true" value="' + QTY_ENDORS_E.value + '"  theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
				str1 += '<td><s:textfield name="ACCR_PRICE"   value="' + ACCR_RATE_COPY + '" theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
				str1 += '<td> <input type="button" name="btn" id="' + k + '" value="X" onclick="deletetablerowaccr(this.id)" class="texts"   theme="simple"  cssStyle="width:30px;text-align:right"/></td>';
	
				str1 += '</tr>';
				// alert(str1);
				$('#' + a + ' tr').last().after(str1);
				k = eval(k) + 1;
				document.getElementById('rownum').value = eval(document.getElementById('rownum').value) + eval(CO_NO_E.length);
			} else {
	
				var k = eval(document.getElementById('rownum').value) + eval(document.getElementById('accrctn').value);
				for (i = 0; i < CO_NO_E.length; i++) {
					var str1 = '<tr bgcolor="#FFFFFF" id=' + k + '><td><s:textfield name="ACCR_CO_NO" readonly="true"  value="' + CO_NO_E[i].value + '" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
					str1 += '<td><s:textfield name="ACCR_CO_LINE" readonly="true" value="' + CO_LINE_E[i].value + '" theme="simple" cssClass="texts" cssStyle="width:60px"/> </td>';
					str1 += '<td><s:textfield name="ACCR_ITEM_NO" readonly="true" value="' + ITEM_NO_E[i].value + '"  theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
					str1 += '<td><s:textfield name="ACCR_TYPE" readonly="true" value="' + ACCRTYPE + '" theme="simple" cssClass="texts" cssStyle="width:100px"/></td>';
					str1 += '<td><s:textfield name="ACCR_DBKSLNO" readonly="true" value="' + DBK_ACCR_COPY + '" theme="simple" cssClass="texts" cssStyle="width:80px"/> </td>';
					str1 += '<td><s:textfield name="ACCR_STRSLNO" readonly="true" value="' + STR_ACCR_COPY + '" theme="simple" cssClass="texts" cssStyle="width:80px"/></td>';
					str1 += '<td><s:textfield name="ACCR_QTY" readonly="true" value="' + QTY_ENDORS_E[i].value + '"  theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
					str1 += '<td><s:textfield name="ACCR_PRICE"   value="' + ACCR_RATE_COPY + '" theme="simple" cssClass="texts" cssStyle="width:60px;text-align:right"/></td>';
	
					str1 += '<td><input type="button" name="btn" id="' + k + '" value="X" onclick="deletetablerowaccr(this.id)" class="texts"  theme="simple"   cssStyle="width:30px;text-align:right"/></td>';
					str1 += '</tr>';
					// alert(str1);
					$('#' + a + ' tr').last().after(str1);
					k = eval(k) + 1;
				}
				document.getElementById('rownum').value = eval(document.getElementById('rownum').value) + eval(CO_NO_E.length);
			}
	
		}*/

function calldbk(a1, a) {
	document.getElementById(a1).href = "predbkslviewPREINVMVX?PARAA=" + a + "&INVDATE=" + document.getElementById('inv_date').value;
}

function callstrmisc(a1, a, b) {
	document.getElementById(a1).href = "prestrslviewPREINVMVX?PARAA=" + a + "&PARAB=STRMISC" + "&INVDATE=" + document.getElementById('inv_date').value;
}

function openpop(a) {
	document.getElementById(a).style.display = '';
}
function closediv(a) {
	document.getElementById(a).style.display = 'none';
}