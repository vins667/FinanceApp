
function savedetail(){
	var planNo=document.getElementById('dt_plan_no').value;
	alert('Plan No'+planNo);
	document.detail.action = "saveInvoicePREINVMVX.action?plan_no="+planNo;
	document.detail.method="post";
	document.detail.submit();
	document.getElementById('prepage').style.visibility = '';
}

function GetMovexData() {
	document.frm.action = "PREINVMVX.action?viewFlag=YES&GETREFRESH=YES";
	document.frm.submit();
	document.getElementById('prepage').style.visibility = '';
}	

function copyCatg() {
	var PRICE_FC_MOVEX = document.getElementsByName("PRICE_FC_MOVEX");
	var CATG_CODE = document.getElementsByName("CATG_CODE");
	var INV_DESC = document.getElementsByName("INV_DESC");
	var DBK_SLNO = document.getElementsByName("DBK_SLNO");
	var ROSL_SLNO = document.getElementsByName("ROSL_SLNO");
	var SCHEME_CODE = document.getElementsByName("SCHEME_CODE");
	var HSCODE1 = document.getElementsByName("HSCODE1");
	var STR_SLNO = document.getElementsByName("STR_SLNO");
	var STR_MISC = document.getElementsByName("STR_MISC");
	var HNGR_COST = document.getElementsByName("HNGR_COST");
	var TAG_COST = document.getElementsByName("TAG_COST");
	var PRICE_FC_E = document.getElementsByName("PRICE_FC_E");
	var MADE_FOR = document.getElementsByName("MADE_FOR");
	var ADJUST_FC = document.getElementsByName("ADJUST_FC");

	/*alert('CATG_CODE.length'+CATG_CODE.length);
			alert('copyValue'+document.getElementById("CATG_DESC_COPY").value);
			alert('ship type'+document.getElementById("SHIP_TYPE_CODE").value);
			alert('scheme type'+document.getElementById("SCHEME_CODE_COPY").value);*/

	if (CATG_CODE.length > 0) {
		if (document.getElementById("CATG_CODE_COPY").value != "") {

			for (i = 0; i < CATG_CODE.length; i++) {
				CATG_CODE[i].value = document.getElementById("CATG_CODE_COPY").value;
				DBK_SLNO[i].value = document.getElementById("DBK_SLNO_COPY").value;
				ROSL_SLNO[i].value = document.getElementById("ROSL_SLNO_COPY").value;
				STR_SLNO[i].value = document.getElementById("STR_SLNO_COPY").value;
				STR_MISC[i].value = document.getElementById("STR_MISC_COPY").value;
				HSCODE1[i].value = document.getElementById("HS_CODE_COPY").value;
				SCHEME_CODE[i].value = document.getElementById("SCHEME_CODE_COPY").value;
				HNGR_COST[i].value = document.getElementById("PRICE_MISC_COPY").value;
				TAG_COST[i].value = document.getElementById("PRICE_TAG_COPY").value;

				//    PRICE_FC_E[i].value=parseFloat(eval(document.getElementById("PRICE_FC_E"+i).value)) - eval(document.getElementById("PRICE_MISC_COPY").value);
				PRICE_FC_E[i].value = parseFloat(eval(document.getElementById("PRICE_FC_MOVEX" + i).value)) - (eval(document.getElementById("PRICE_MISC_COPY").value) + eval(document.getElementById("PRICE_TAG_COPY").value));
				MADE_FOR[i].value = document.getElementById("SHIP_TYPE_CODE").value;


				ADJUST_FC[i].value = document.getElementById("ADJUST_FC_COPY").value;

			}
		}

		if (document.getElementById("CATG_DESC_COPY").value != "") {

			for (i = 0; i < CATG_CODE.length; i++) {
				INV_DESC[i].value = document.getElementById("CATG_DESC_COPY").value;

			}
		}

	} else {

		if (document.getElementById("CATG_CODE_COPY").value != "") {
			CATG_CODE.value = document.getElementById("CATG_CODE_COPY").value;
			DBK_SLNO.value = document.getElementById("DBK_SLNO_COPY").value;
			ROSL_SLNO.value = document.getElementById("ROSL_SLNO_COPY").value;
			STR_SLNO.value = document.getElementById("STR_SLNO_COPY").value;
			STR_MISC.value = document.getElementById("STR_MISC_COPY").value;
			HNGR_COST.value = document.getElementById("PRICE_MISC_COPY").value;
			TAG_COST.value = document.getElementById("TAG_MISC_COPY").value;
			HSCODE1.value = document.getElementById("HS_CODE_COPY").value;
			SCHEME_CODE.value = document.getElementById("SCHEME_CODE_COPY").value;
		}

		if (document.getElementById("CATG_DESC_COPY").value != "") {

			INV_DESC.value = document.getElementById("CATG_DESC_COPY").value;
		}
	}
}
function copySelected(x) {
	if (document.getElementById("CATG_DESC_COPY").value != "") {
		document.getElementById('INV_DESC' + x).value = document.getElementById("CATG_DESC_COPY").value;
	}
	if (document.getElementById("CATG_CODE_COPY").value != "") {
		document.getElementById('CATG_CODE' + x).value = document.getElementById("CATG_CODE_COPY").value;
	}
	if (document.getElementById("DBK_SLNO_COPY").value != "") {
		document.getElementById('DBK_SLNO' + x).value = document.getElementById("DBK_SLNO_COPY").value;
	}
	if (document.getElementById("ROSL_SLNO_COPY").value != "") {
		document.getElementById('ROSL_SLNO' + x).value = document.getElementById("ROSL_SLNO_COPY").value;
	}
	if (document.getElementById("STR_SLNO_COPY").value != "") {
		document.getElementById('STR_SLNO' + x).value = document.getElementById("STR_SLNO_COPY").value;
	}
	if (document.getElementById("STR_MISC_COPY").value != "") {
		document.getElementById('STR_MISC' + x).value = document.getElementById("STR_MISC_COPY").value;
	}
	if (document.getElementById("PRICE_MISC_COPY").value != "0.0") {
		document.getElementById('HNGR_COST' + x).value = document.getElementById("PRICE_MISC_COPY").value;
	}
	if (document.getElementById("PRICE_TAG_COPY").value != "0.0") {
		document.getElementById('TAG_COST' + x).value = document.getElementById("PRICE_TAG_COPY").value;
	}
	if (document.getElementById("HS_CODE_COPY").value != "") {
		document.getElementById('HSCODE1' + x).value = document.getElementById("HS_CODE_COPY").value;
	}

	if (document.getElementById("SCHEME_CODE_COPY").value != "") {
		document.getElementById('SCHEME_CODE' + x).value = document.getElementById("SCHEME_CODE_COPY").value;
	}
	if (document.getElementById("SHIP_TYPE_COPY").value != "") {
		document.getElementById('MADE_FOR' + x).value = document.getElementById("SHIP_TYPE_COPY").value;
	}
}
function validateSTR() {
	var PRICE_FC_MOVEX = document.frm.PRICE_FC_MOVEX;
	var STR_SLNO = document.frm.STR_SLNO;
	var SCHEME_CODE = document.frm.SCHEME_CODE;
	var STR_MISC = document.frm.STR_MISC;
	var CATG_CODE = document.frm.CATG_CODE;
	var PRICE_FC_E = document.frm.PRICE_FC_E;
	var HNGR_COST = document.frm.HNGR_COST;
	var TAG_COST = document.frm.TAG_COST;
	var ADJUST_FC = document.frm.ADJUST_FC;
	if (PRICE_FC_MOVEX.length > 0) {
		t1 = 0;
		h1 = 0;
		t2 = 0;
		h2 = 0;
		for (i = 0; i < PRICE_FC_MOVEX.length; i++) {
			if (document.frm.CNTRY_ORIGIN.value == "") {
				alert("Please Enter Origin Cntry !!");
				document.frm.CNTRY_ORIGIN.focus();
				return false;
			}
			if (CATG_CODE[i].value == "") {
				alert("Please Enter Category ....");
				return false;
			}

			if (SCHEME_CODE[i].value == "") {
				alert("Please Enter SCHEME_CODE....");
				return false;
			}
			h1 = parseFloat(HNGR_COST[i].value) ;
			h2 = parseFloat(TAG_COST[i].value) ;


			h1 = parseFloat(HNGR_COST[i].value) + parseFloat(TAG_COST[i].value)

			if (ADJUST_FC[i].value >= .01) {
				alert("Check Adjust FC ");
				return false;
			}


			/*
					  if(STR_SLNO[i].value=="")
					  {
					   alert("Please Enter STR SLNO....");
					    return false;
					  }   

					  if (STR_MISC[i].value=="" && h1!=0)
					  { 
					      alert("STR Misc required for Price Misc ");
					       return false; 
					  } 

					  if (STR_MISC[i].value!="" &&  h1==0)
					  {
					      alert("Price Misc required for STR Misc. ");
					       return false;  
					  } 
			 */


			t1 = parseFloat(PRICE_FC_E[i].value) ;
			t2 = parseFloat(HNGR_COST[i].value);
			t3 = parseFloat(TAG_COST[i].value);

			var t = parseFloat(eval(PRICE_FC_E[i].value) + eval(HNGR_COST[i].value) + eval(TAG_COST[i].value)).toFixed(4);

			if (t > parseFloat(PRICE_FC_MOVEX[i].value).toFixed(4)) {
				alert("Please Check Price FC");
				PRICE_FC_E[i].value = PRICE_FC_MOVEX[i].value;
				HNGR_COST[i].value = "0.0";
				TAG_COST[i].value = "0.0";
				return false;
			}
		}
	}

	return true;
}

function validateLIC() {
	var REF_NO = document.frm.REF_NO;
	var REF_TYPE = document.frm.REF_TYPE;
	var IO_NORMS = document.frm.IO_NORMS;
	var QTY_SQM = document.frm.QTY_SQM;
	var BE_NO = document.frm.BE_NO;
	var ADJS_QTY = document.frm.ADJS_QTY;
	var FOB_AMT = document.frm.FOB_AMT;
	var LIC_AMT_OB = document.frm.LIC_AMT_OB;
	var LIC_AMT_ISSUE = document.frm.LIC_AMT_ISSUE;
	var LIC_QTY_OB = document.frm.LIC_QTY_OB;
	var LIC_QTY_ISSUE = document.frm.LIC_QTY_ISSUE;

	if (REF_NO != null) {
		t1 = 0;
		t2 = 0;
		for (i = 0; i < REF_NO.length; i++) {
			if (REF_NO[i].value != "") {

				if (IO_NORMS[i].value == "" && REF_TYPE[i].value != "EPCG") {
					alert("Please Enter Licence IO Norms ....");
					return false;
				}
				if (QTY_SQM[i].value == "" && REF_TYPE[i].value != "EPCG") {
					alert("Please Enter SQM QTY....");
					return false;
				}
				if ( (BE_NO[i].value == "" && REF_TYPE[i].value != "EPCG") ) {
					alert("Please Select BE No.... ");
					return false;
				}
				t1 = eval(eval(LIC_AMT_OB[i].value));
				t2 = eval(FOB_AMT[i].value) + eval(LIC_AMT_ISSUE[i].value);

				if (t1 > t2) {
					alert('Check Licence Export Obligation Value --> ' + t1 + '  Utilize Value -->' + t2)
				}
			}
		}
	}

	return true;
}
function openpop(a) {
	document.getElementById(a).style.display = '';
}
function closediv(a) {
	document.getElementById(a).style.display = 'none';
}

function callcatg(a1, a, b) {
	document.getElementById(a1).href = "catgViewPREINVMVX?PARAA=" + a + "&PARAB=" + b + "&DESTI_CNTRY=" + document.getElementById('DESTI_CNTRY').value;

}
function calllic(a1, a) {
	document.getElementById(a1).href = "prelicviewPREINVMVX?PARAA=" + a;
}
function calldbk(a1, a) {
	document.getElementById(a1).href = "predbkslviewPREINVMVX?PARAA=" + a + "&INVDATE=" + document.getElementById('inv_date').value;
}
function callrosl(a1, a) {
	document.getElementById(a1).href = "preroslslviewPREINVMVX?PARAA=" + a + "&INVDATE=" + document.getElementById('inv_date').value;
}
function callstr(a1, a, b) {
	document.getElementById(a1).href = "prestrslviewPREINVMVX?PARAA=" + a + "&PARAB=STR" + "&dbkslnocopy=" + document.getElementById('DBK_SLNO_COPY').value + "&INVDATE=" + document.getElementById('inv_date').value;
}
function callstrmisc(a1, a, b) {
	document.getElementById(a1).href = "prestrslviewPREINVMVX?PARAA=" + a + "&PARAB=STRMISC" + "&INVDATE=" + document.getElementById('inv_date').value;
}
function callBE(a1, a) {
	document.getElementById(a1).href = "prebeviewPREINVMVX?PARAA=" + a + "&LIC_TYPE=" + document.getElementById('REF_TYPE' + a).value + "&LIC_NO=" + document.getElementById('REF_NO' + a).value;
}