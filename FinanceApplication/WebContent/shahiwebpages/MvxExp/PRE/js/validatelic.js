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