/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
if (typeof window.event != 'undefined') // IE
	document.onkeydown = function() // IE
	{
		if (event.keyCode == 13) {
			event.keyCode = 9
		}
		var t = event.srcElement.type;
		var kc = event.keyCode;
		return ((kc != 8 && kc != 13) || (t == 'text' && kc != 13)
				|| (t == 'textarea') || (t == 'submit' && kc == 13))
	}
else
	document.onkeypress = function(e) // FireFox/Others
	{
		return tabOnEnter(e.target, e)
		aaaa = e.keyCode;
		if (aaaa == 13) {

		}
		return true;
	}
function tabE(obj, e) {
	var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
	if (e.keyCode == 13) {
		var ele = document.forms[0].elements;
		for ( var i = 0; i < ele.length; i++) {
			var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if
														// any other
			if (obj == ele[i]) {
				ele[q].focus();
				break
			}
		}
		return false;
	}
}

var ns4 = document.layers
var ie4 = document.all
var ns6 = document.getElementById && !document.all

function showbox() {
	crossobj = ns6 ? document.getElementById("showimage22")
			: document.all.showimage22
	if (ie4 || ns6)
		crossobj.style.visibility = "visible"
	else if (ns4)
		document.showimage22.visibility = "show"

}
function hidebox() {
	crossobj = ns6 ? window.document.getElementById("showimage22")
			: window.document.all.showimage22
	if (ie4 || ns6)
		crossobj.style.visibility = "hidden"
	else if (ns4)
		window.document.showimage22.visibility = "hide"
}
function showbox2(a) {
	// var td = document.getElementById('dragbar1');
	// td.innerHTML =a;

	crossobj = ns6 ? document.getElementById("showimage2")
			: document.all.showimage2
	if (ie4 || ns6)
		crossobj.style.visibility = "visible"
	else if (ns4)
		document.showimage2.visibility = "show"
}
function hidebox2() {
	crossobj = ns6 ? document.getElementById("showimage2")
			: document.all.showimage2
	if (ie4 || ns6)
		crossobj.style.visibility = "hidden"
	else if (ns4)
		document.showimage2.visibility = "hide"
}
function showbox3() {
	crossobj = ns6 ? document.getElementById("showimage3")
			: document.all.showimage3
	if (ie4 || ns6)
		crossobj.style.visibility = "visible"
	else if (ns4)
		document.showimage3.visibility = "show"

}
function hidebox3() {
	crossobj = ns6 ? window.document.getElementById("showimage3")
			: window.document.all.showimage3
	if (ie4 || ns6)
		crossobj.style.visibility = "hidden"
	else if (ns4)
		window.document.showimage3.visibility = "hide"
}
function waitPreloadPage() { // DOM
	if (document.getElementById) {
		document.getElementById('prepage').style.visibility = 'hidden';
	} else {
		if (document.layers) { // NS4
			document.prepage.visibility = 'hidden';
		} else { // IE4
			document.all.prepage.style.visibility = 'hidden';
		}
	}
}
function onsave() {
	if (validate()) {
		if (confirm('Do you want to save???')) {
			document.balconf.action = "saveCadSampleRequisitionFormatAction.action";
			document.balconf.submit();
		}
	}
}
function validate() {
	if ($("#BODY_NUMB").val() == '') {
		alert('Body Number cannot be empty');
		$("#BODY_NUMB").focus();
		return false;
	}
	if ($("#ACCOUNT_CODE").val() == '') {
		alert('Account cannot be empty');
		$("#ACCOUNT_CODE").focus();
		return false;
	}
	if ($("#CATEGORY").val() == '') {
		alert('Category cannot be empty');
		$("#CATEGORY").focus();
		return false;
	}
	recd = 0;
	for (i = 0; i < 14; i++) {
		id = 'ENTRY_DATE_ID' + i;
		if (document.getElementById(id).value != '') {
			++recd;
		}
	}
	if (recd == 0) {
		alert('Details cannot be empty');
		return false;
	}
	return true;
}
function addimgp(a) {
	var newRow = window.parent.document.getElementById('imgview');
	newRow.innerHTML = '<a title="Click to view Detail" style="cursor:url(\'image/cur/zoomin.cur\')" onclick=\'showbox2("Zoom");zoomimgp("'
			+ a
			+ '");\' target="belowfram"><img src="'
			+ a
			+ '" style="width:120px;height: 90px"/></a>';

}
function zoomimgp(a) {
	var newRow1 = window.parent.document.getElementById('viewzoomimg');
	newRow1.innerHTML = '<a title="Click to Close" style="cursor: url(\'image/cur/zoomout.cur\');" onclick="hidebox2();"><img id="blah" src="'
			+ a
			+ '" width="500px" height="550.0px" alt="Upload Image" align="middle" title="Click to zoom" style=""/></a>';
}
function validatenondecnumber(a) {

	k = a.value;
	if (k != "" && !k.match(/^\d+$|^\d+\$/)) {
		alert('Invalid Input, Only Numbers Allowed');
		a.value = "";
		a.focus();
		return false;

	}
	return true;
}
function validatenumberthree(a) {	
	k = a.value;
	
	if (k != "" && !k.match(/^\d+$|^\d+\.\d{1,3}$/)) {
		alert('Invalid Input, Only Numbers Allowed');
		a.value = "";
		a.focus();
		return false;

	}
	return true;
}
function validatenumberfour(a) {

	k = a.value;
	if (k != "" && !k.match(/^\d+$|^\d+\.\d{1,4}$/)) {
		alert('Invalid Input, Only Numbers Allowed');
		a.value = "";
		a.focus();
		return false;

	}
	return true;
}
function totalsizeviewclr(a) {
	var QTYbj = document.balconf.QTY;
	if (a == 0) {
		id = 0;
	} else {
		id = a * 20;
	}	
	tot = 0;
	for (i = id; i < id + 20; i++) {
		if (QTYbj[i] && QTYbj[i].value != "" && (QTYbj[i].value).length > 0) {
			tot = tot + eval(QTYbj[i].value);
		}
	}
	if (tot == 0) {
		tot = "";
	}
	document.balconf.TOTAL[a].value = tot;
	
}
function totalsizeviewclrvert(a) {

	var QTYbj = document.frm.QTY;

	tot = 0;
	for (n = a; n < 420; n = n + 20) {

		if (QTYbj[n].value != "" && (QTYbj[n].value).length > 0) {
			tot = tot + eval(QTYbj[n].value);
		}
	}
	if (tot == 0) {
		tot = "";
	}
	document.frm.TOTALVER[a].value = tot;
	alltotal();
}