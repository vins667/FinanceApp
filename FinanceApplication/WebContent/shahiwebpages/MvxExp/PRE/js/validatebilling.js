function savedetail(){
	document.detail.action = "saveInvoicePREINVMVX.action";
	document.detail.submit();
	document.getElementById('prepage').style.visibility = '';
}

function validateSTR() {
			var PRICE_FC_MOVEX = document.PRICE_FC_MOVEX;
			var STR_SLNO = document.STR_SLNO;
			var SCHEME_CODE = document.SCHEME_CODE;
			var STR_MISC = document.STR_MISC;
			var CATG_CODE = document.CATG_CODE;
			var PRICE_FC_E = document.PRICE_FC_E;
			var HNGR_COST = document.HNGR_COST;
			var TAG_COST = document.TAG_COST;
			var ADJUST_FC = document.ADJUST_FC;
			if (PRICE_FC_MOVEX.length > 0) {
				t1 = 0;
				h1 = 0;
				t2 = 0;
				h2 = 0;
				for (i = 0; i < PRICE_FC_MOVEX.length; i++) {
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