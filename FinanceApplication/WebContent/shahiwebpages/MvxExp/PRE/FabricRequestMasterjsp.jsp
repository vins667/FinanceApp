<%-- 
    Document   : FabricRequestMasterjsp
    Created on : Feb 21, 2012, 12:36:08 PM
    Author     : Ranjeet
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<link href="<s:url value="../../css/main.css"/>" rel="stylesheet" type="text/css" />
<html>
    <head>

        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <link rel="stylesheet" href="../jq/ei/style.css">
        <script language="javascript" type="text/javascript" src="js.js"></script>
         <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/jquery.table.addrow.js"></script>
        
        <script language="javascript">
           <%-- <s:select name="fabricuom"  value="" id="fabricuomid%{FABSLID}%{#ctnrow}"  theme="simple"   cssClass="texts"   list="uomlist" listKey="SAMPLE_TYPE"  listValue="SAMPE_TYPE_DESC"/> 
              --%>
                  

                  function addsubtitleq(a,v)
            {
                 
              
                var s=Math.floor((Math.random() * 10000) + 1); 
                var k=Math.floor((Math.random() * 100000) + 1);
                var str1 = '<tr class="titlecss" ><td style="border-width:1px;border-style: solid;border-color:black "><table cellpadding="1" cellspacing="0"><tr><td><input type="text" name="STYLE_CODE_LI"  id="STYLE_CODE_LI'+k+'" class="texts" maxlength="30" onblur="copytxt(this,\''+s+'_TITLE_DESC\')" style="width: 150px;"/></td><td> <a target="popupframe2" onclick="showbox22();" style="text-decoration: none;"  href="stimglistAction.action?stlid=STYLE_CODE_LI'+k+'&CPCLASS='+s+'_TITLE_DESC"><img width="15px" style="border: 0px" height="15px" src="../../css/image/Search-icon-big.png" /></a></td></tr></table></td><td style="border-width:1px;border-style: solid;border-color:black "><table id="'+s+'" cellpadding="1" cellspacing="0"><tr class="'+s+'">';
                str1 += '<td><s:textfield name="fabriccolor" value="%{COLOR_CODE}" id="fabriccolorid'+k+'" theme="simple" cssClass="texts"   maxlength="30" cssStyle="width:200px;text-transform:uppercase;background-color: #d9d5bd"  onkeypress="return onlyAlphabets(event,this);"  onblur="onlyAlphabetsBlur(this);"/></td>';
               
                var str2="'"+'ORDR_QTY'+k+"',"+"'"+'AVG_CONS'+k+"',"+"'"+'EXT_PER'+k+"',"+"'"+'fabricconsid'+k+"'";
               
               
               str1 += '<td><s:textfield name="ORDR_QTY" value="" onblur="validatefabqty('+str2+')"  id="ORDR_QTY'+k+'"  theme="simple"   cssClass="texts"  maxlength="10"   cssStyle="width:100px;text-transform:uppercase;"  /> </td>';
                str1 += '<td><s:textfield name="AVG_CONS"   value="" onblur="validatefabqty('+str2+')" id="AVG_CONS'+k+'"   theme="simple" cssClass="texts" maxlength="10" cssStyle="width:50px;text-transform:"  /></td>';
		str1 += '<td><s:textfield name="EXT_PER" value="" onblur="validatefabqty('+str2+')" id="EXT_PER'+k+'"  theme="simple"  cssClass="texts"  maxlength="10"   cssStyle="width:50px;text-transform:uppercase;"  /> </td>';
		str1 += '<td><s:textfield name="fabriccons"  onblur="validatenumber(this)"  value=""   id="fabricconsid'+k+'"  theme="simple"   cssClass="texts"   cssStyle="width:100px" /></td>';
                str1 += '<td><select name="fabricuom" id="fabricuomid" class="texts"><option value="MTR">MTR</option><option value="PCS">PCS</option><option value="SET">SET</option><option value="YRD">YRD</option><option value="KGS">KGS</option></select></td>';
		str1 += '<td><s:textfield name="fabricremarks" value=""   id="fabricremarksid'+k+'"  theme="simple"  cssClass="texts"   cssStyle="width:300px;text-transform:uppercase" /> </td>';
                str1 += '<td width="105px"><s:file name="uploads" theme="simple" cssClass="texts" cssStyle="width:50px" id="uploadsid'+k+'" /> <s:hidden name="uploadstemp" value="No" id="uploadstemp'+k+'" /></td>';
							
                str1 += '<td><s:hidden name="STYLE_CODE_E" cssClass="'+s+'_TITLE_DESC" value=""   theme="simple" /><input type="button" class="texts" style="font-size: 10px"     name="btn"  onclick="fabricviewclr('+k+')" value="X"><img src="js/add.png" onclick="addsubtitleq1('+s+',\''+v+'\')" /> ';
                str1 += '<s:hidden name="PLACEMENT" value=""  id="PLACEMENT_'+v+'" theme="simple" cssClass="PLACEMENT_'+v+'" />';
	       str1 += '<s:hidden name="fabricdesc" value=""  id="fabricdesc_'+v+'" theme="simple" cssClass="fabricdesc_'+v+'" />';
	        str1 += '<s:hidden name="WIDTH" value=""  id="WIDTH_'+v+'" theme="simple" cssClass="WIDTH_'+v+'" />';
	       str1 += '<s:hidden name="FABSLID_L" value=""  id="FABSLID_L_'+v+'" theme="simple" cssClass="FABSLID_L_'+v+'" />';
	     
               str1 += '</td><td></td></tr></table></td></tr>';
                $('#'+a+' tr.titlecss').last().after(str1);
                
                 newcopytxt("PLACEMENT_"+v);
                 //for fabric description
                 newcopytxt("fabricdesc_"+v);
                 
                 //for cuttable width
                 newcopytxt("WIDTH_"+v);
                 
                 //for Tab SL No
                 newcopytxt("FABSLID_L_"+v);
   
            }


            function addsubtitleq1(a,v)
            {
                  var k=Math.floor((Math.random() * 100000) + 1);
                 
               var str1 = '<tr class="'+a+'"><td><s:textfield name="fabriccolor" value="%{COLOR_CODE}" id="fabriccolorid'+k+'" theme="simple" cssClass="texts"   maxlength="30" cssStyle="width:200px;text-transform:uppercase;background-color: #d9d5bd"  onkeypress="return onlyAlphabets(event,this);"  onblur="onlyAlphabetsBlur(this);"/></td>';
                var str2="'"+'ORDR_QTY'+k+"',"+"'"+'AVG_CONS'+k+"',"+"'"+'EXT_PER'+k+"',"+"'"+'fabricconsid'+k+"'";
                str1 += '<td><s:textfield name="ORDR_QTY"  value="" onblur="validatefabqty('+str2+')"  id="ORDR_QTY'+k+'"  theme="simple"   cssClass="texts"  maxlength="10"   cssStyle="width:100px;text-transform:uppercase;"  /> </td>';
                
                str1 += '<td><s:textfield name="AVG_CONS"  onblur="validatefabqty('+str2+')"  value="" id="AVG_CONS'+k+'"   theme="simple" cssClass="texts" maxlength="10" cssStyle="width:50px;text-transform:"  /></td>';
		str1 += '<td><s:textfield name="EXT_PER" onblur="validatefabqty('+str2+')" value="" id="EXT_PER'+k+'"  theme="simple"  cssClass="texts"  maxlength="10"   cssStyle="width:50px;text-transform:uppercase;"  /> </td>';
		str1 += '<td><s:textfield name="fabriccons"  onblur="validatenumber(this)"  value=""   id="fabricconsid'+k+'"  theme="simple"   cssClass="texts"   cssStyle="width:100px" /></td>';
                str1 += '<td><select name="fabricuom" id="fabricuomid" class="texts"><option value="MTR">MTR</option><option value="PCS">PCS</option><option value="SET">SET</option><option value="YRD">YRD</option><option value="KGS">KGS</option></select></td>';
		str1 += '<td><s:textfield name="fabricremarks" value=""   id="fabricremarksid'+k+'"  theme="simple"  cssClass="texts"   cssStyle="width:300px;text-transform:uppercase" /> </td>';
                str1 += '<td><s:file name="uploads" theme="simple" cssClass="texts" cssStyle="width:50px" id="uploadsid'+k+'" /> <s:hidden name="uploadstemp" value="No" id="uploadstemp'+k+'" /></td>';
		
               str1 += '<td><s:hidden name="STYLE_CODE_E" cssClass="'+a+'_TITLE_DESC" value=""  theme="simple" /><input type="button" class="texts" style="font-size: 10px"     name="btn"  onclick="fabricviewclr('+k+')" value="X">';
               str1 += '<s:hidden name="PLACEMENT" value=""  id="PLACEMENT_'+v+'" theme="simple" cssClass="PLACEMENT_'+v+'" />';
	       str1 += '<s:hidden name="fabricdesc" value=""  id="fabricdesc_'+v+'" theme="simple" cssClass="fabricdesc_'+v+'" />';
	       str1 += '<s:hidden name="WIDTH" value=""  id="WIDTH_'+v+'" theme="simple" cssClass="WIDTH_'+v+'" />';
	       str1 += '<s:hidden name="FABSLID_L" value=""  id="FABSLID_L_'+v+'" theme="simple" cssClass="FABSLID_L_'+v+'" />';
	     
              str1 += '</td><td></td></tr>';						
             $('#'+a+' tr.'+a).last().after(str1);
             
                newcopytxt(a+"_TITLE_DESC");
                //for fabric description
                 newcopytxt("fabricdesc_"+v);
                 
                  newcopytxt("PLACEMENT_"+v);
                 //for cuttable width
                 newcopytxt("WIDTH_"+v);
                  //for Tab SL No
                 newcopytxt("FABSLID_L_"+v);
   
            }

            function copytxt(a,b)
            {
               
                $('input.'+b).val(a.value);   
            }
            
             function copytxtpop(a,b)
            {
              
                $('input.'+b).val(a);   
            }

         function newcopytxt(a)
            {
               
                $('input.'+a).val($('input.'+a).val());   
            }
            
            function filecomboaction(uploadsid,uploadstemp)
            {    
                var name=uploadsid.value;
    
                if(name!="" && name.length>0)
                {
                   
                    document.getElementById(uploadstemp).value='Yes';
                }else{
                    document.getElementById(uploadstemp).value='No'; 
                }
    
              
            }
            function filecomboactionup(uploadsid,uploadstemp)
            {	
                var name=uploadsid.value;
              
                
                if(name!="" && name.length>0)
                {
                    document.getElementById(uploadstemp).value='Yes';
                
                }else{
                    //document.getElementById(uploadstemp).value='No'; 
                }
	   
               
            }
          
          
            function validatefabqty(l,m,n,o)
            {
  			   
  		a=  document.getElementById(l);
            	k="0"+a.value;
              if (k!="" && !k.match(/^\d+$|^\d+\$/ ) )
            	
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="0";
                        calfabqty(l,m,n,o);
            		a.focus();
            		return false;
            	}
                b=  document.getElementById(m);
            	k="0"+b.value;
               if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		b.value="0.0";
                        calfabqty(l,m,n,o);
            		b.focus();
            		return false;
            	}
                c=  document.getElementById(n);
            	k="0"+c.value;
               if (k!="" && !k.match(/^\d+$|^\d+\$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		c.value="0";
                        calfabqty(l,m,n,o);
            		c.focus();
            		return false;
            	}
                calfabqty(l,m,n,o);
               
            	return true;
            } 
            
            
            function calfabqty(l,m,n,o)
            {
            /*    var flag=0
             if((document.getElementById(l).value!="" && document.getElementById(l).value>0)
                ||   (document.getElementById(l).value!="" && document.getElementById(l).value>0)
                || (document.getElementById(l).value!="" && document.getElementById(l).value>0)
                )
                 {
                     flag=1;
                     document.getElementById(o).readOnly = true;
                     document.getElementById(o).cssClass="textreadonly";
                  
                 }else{
                      flag=0;
                     document.getElementById(o).readOnly = false;
                     document.getElementById(o).cssClass="texts";
                 }
                alert(flag);
                 
              if(flag==0 && document.getElementById(o).value!="" && document.getElementById(o).value>0)
             {
                 document.getElementById(l).readOnly = true;
                 document.getElementById(l).cssClass="textreadonly";
                 document.getElementById(m).readOnly = true;
                 document.getElementById(m).cssClass="textreadonly";
                 document.getElementById(n).readOnly = true;
                  document.getElementById(n).cssClass="textreadonly";
             }else{
                 document.getElementById(l).readOnly = false;
                 document.getElementById(m).readOnly = false;
                 document.getElementById(n).readOnly = false;
                 document.getElementById(l).cssClass="texts";
                 document.getElementById(m).cssClass="texts";
                 document.getElementById(n).cssClass="texts";
                 
             }
             */
                var qty=0;
                 if(document.getElementById(l).value!="" && !isNaN(eval(document.getElementById(l).value))){
                   qty=eval(document.getElementById(l).value);
                 }
                  if(document.getElementById(m).value!="" && !isNaN(eval(document.getElementById(m).value))){
                   qty=qty * eval(document.getElementById(m).value);
                 }
                 
                  if(document.getElementById(n).value!="" && !isNaN(eval(document.getElementById(n).value))){
                   qty=qty + qty * ((eval(document.getElementById(n).value))/100);
                 }
                 document.getElementById(o).value =qty.toFixed(2);
            }
            
            
            function onlyAlphabets(e, t) {
                try {
                    if (window.event) {
                        var charCode = window.event.keyCode;
                    }
                    else if (e) {
                        var charCode = e.which;
                    }
                    else { return true; }
                    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58) || charCode==32)
                        return true;
                    else
                        return false;
                }
                catch (err) {
                    alert(err.Description);
                }
            }
            function onlyAlphabetsBlur(t){
                str = t.value;
                var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?";

                for (var i = 0; i < str.length; i++) {
                    if (iChars.indexOf(str.charAt(i)) != -1) {
                        alert ("Color has special characters ~`!#$%^&*+=-[]\\\';,/{}|\":<>? \nThese are not allowed\n");
                        t.value='';
                        t.focus();
                        return false;
                    }
                }		
            }
            function comboaction(tdid,uploadsid,styled_popup,a,uploadstemp)
            {    
                var name=document.getElementById(uploadsid).value;
    
                if(name!="" && name.length>0)
                {
                    name ="<img src='images/application_view_list.png'/>";//"COMBO - " +a; 
                    document.getElementById(uploadstemp).value='Yes';
                }else{
                    document.getElementById(uploadstemp).value='No'; 
                }
    
                document.getElementById(tdid).innerHTML=name;
                document.getElementById(styled_popup).style.display = "none";
            }
            function comboactionup(tdid,uploadsid,styled_popup,a,uploadstemp)
            {	
                var name=document.getElementById(uploadsid).value;
                if(name!="" && name.length>0)
                {
                    name ="<img src='images/application_view_list.png'/>";//"COMBO - " +a; 
                    document.getElementById(uploadstemp).value='Yes';
                }else{
                    //document.getElementById(uploadstemp).value='No'; 
                }
	   
                //document.getElementById(tdid).innerHTML=name;
                document.getElementById(styled_popup).style.display = "none";
            }
            function fabres_show_details() {
                dojo.event.topic.publish("fabresid_show_detail");
            }
        </script>
        <script language="JavaScript">
            function toggle(id) {
                var state = document.getElementById(id).style.display;
                if (state == 'block') {
                    document.getElementById(id).style.display = 'none';
                } else {
                    document.getElementById(id).style.display = 'block';
                }
            }
            function QTPopup() {
                var state = document.getElementById('QTPopup').style.display;
                if (state == 'block') {
                    document.getElementById('QTPopup').style.display = 'none';
                } else {
                    document.getElementById('QTPopup').style.display = 'block';
                }
            }
            function addtablerow(a) {
                ctr = document.getElementById("ctrid1").value;
                tbl = document.getElementById(a);
                var rowCount = tbl.rows.length;
                //alert(rowCount);
                var row = tbl.insertRow(rowCount);
                //alert(row);
                td1 = row.insertCell(0);
                td1.innerHTML = "<input type='text' name='ITEM_CODE' id='ITEM_CODE"+ctr+"' value='' style='width:130px' readonly='true' class='textreadonly'/>";
                td2 = row.insertCell(1);
                td2.innerHTML = "<input type='text' name='MSTYLEDESC' id='MSTYLEDESC"
                    + ctr
                    + "' value='' style='width:100%' readonly='true' class='textreadonly'/>";
                td3 = row.insertCell(2);
                td3.innerHTML = "<input type='button' class='texts' style='font-size:10px' name='btn' onclick='javascript:document.getElementById('ITEM_CODE"
                    + ctr
                    + "').value=;document.getElementById('MSTYLEDESC"
                    + ctr
                    + "').value=' value='X'>";
                td4 = row.insertCell(3);
                td4.innerHTML = "<a target='popupframe2' onclick='showbox22();' style='text-decoration: none;' class='texts' href='fabricmovexstylelistAction.action?TINDEX="
                    + ctr
                    + "'><img width='15px' style='border:0px;' height='17px' src='../../css/image/Search-icon-big.png' /></a>";
                document.getElementById("ctrid1").value = eval(ctr) + 1;

            }
            function addtablerowgre(a){
                ctr = document.getElementById("grectrid1").value;
                tbl = document.getElementById(a);
                var rowCount = tbl.rows.length;
                //alert(rowCount);
                var row = tbl.insertRow(rowCount);
                //alert(row);
                td1 = row.insertCell(0);
                td1.innerHTML = "<input type='text' name='GREIGE_REQ_ID' id='GREIGE_REQ_ID"+ctr+"' value='' style='width:100%' readonly='true' class='textreadonly'/>";
                td2 = row.insertCell(1);
                td2.innerHTML = "<input type='button' class='texts' style='font-size:10px' name='btn' onclick='javascript:document.getElementById('GREIGE_REQ_ID"
                    + ctr
                    + "').value=;' value='X'>";
                td3 = row.insertCell(2);
                td3.innerHTML = "<a target='popupframe2' onclick='showbox22();' style='text-decoration: none;' class='texts' href='fabricgreigestylelistAction.action?TINDEX="
                    + ctr
                    + "&STYLE_CODE="+document.getElementById("SAMPLE_ID").value
                    + "&LOCATION_CODE="+document.getElementById("LOCATION_CODE").value
                    +"'><img width='15px' style='border:0px;' height='17px' src='../../css/image/Search-icon-big.png' /></a>";				
                document.getElementById("grectrid1").value = eval(ctr) + 1;
            }
        
            function printcard()
            {
                document.getElementById('cardhre').href="http://172.17.3.57:8080/ShahiReportApplication/shahiwebpages/Fabric/FabricDevelopmentRequest/requestreport.jsp?REPTYPE="+document.getElementById("REPTYPE").value+"&REQ_ID="+document.getElementById("SAMPLE_ID").value
            }
        
        
            function recitemupdatesave()  
            {
                document.frm.action="fabsamitemupAction.action";
                document.frm.submit(); 
            }
        
        </script>
        <style type="text/css">
            .hiddenRow {
                display: none;
                visibility: hidden;
            }

            .visibleRow {
               
                visibility: visible;
            }
            
             th {
                font-size:11px;
                font-weight:bold;
                color:#006699;
                background-image:url('image/table-gradient.jpg');
            } 
             tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;

            }  
        </style>
         <!--[if IE]>
		    <style type="text/css">
		        .div1 {
		            position: relative;
		            height: 510px;
		            width: 700px;
		            overflow-y: scroll;
		            overflow-x: hidden;
		            border: solid #006699;
		            border-width: 0px 0px 0px 0px;
		            padding-top: 0px ;
		        }        
		         thead tr {
		             position: relative;
		             top: expression(this.offsetParent.scrollTop);
		        }
		        
		        tbody {
		            height: auto;
		        }
		         
		    </style>
		<![endif]-->
    </head>

    <body style="background-color: #f2f2f2; margin: 0px">

        <form action="" style="margin: 0px" onsubmit="return(validateinput())" id="frm" method="post" name="frm" enctype="multipart/form-data">
            <table align="center" width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" style="border-width: 1pt; border-color: black; border-style: solid;">
                        <table border="0" bgcolor="#f2f2f2" cellpadding="4" align="center" width="100%">
                            <tr>
                                <td class="hd" style="text-align: center">Fabric Development & Approval<s:if test="%{CLOSE_FLAG=='Yes'}">
                                        <span class="label-1" style="color: Red"> ( Canceled. ) </span>
                                    </s:if></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td height="100pt" valign="top" style="border-width: 1pt; border-color: #2680b5; border-style: solid;">
                                                <table width="100%" cellspacing="0px" border="0" cellpadding="1">
                                                    <tr>
                                                        <td class="label-1">Location</td>
                                                        <td><s:textfield name="LOCATION_CODE" id="LOCATION_CODE" theme="simple" value="%{LOCATION_CODE}" cssStyle="width:80px" readonly="true" cssClass="textreadonly" /></td>
                                                        <td class="label-1" style="width:80px">Request&nbsp;No</td>
                                                        <td align="right"><s:textfield name="SAMPLE_ID" id="SAMPLE_ID" theme="simple" value="%{SAMPLE_ID}" cssStyle="width:250px" readonly="true" cssClass="textreadonly" /></td>
                                                        <td class="label-1">User</td>
                                                        <td style="width:300px"><s:textfield name="USER" id="USER" theme="simple" cssStyle="width:100%" value="%{USER}" readonly="true" cssClass="textreadonly" /></td>
                                                        <td>&nbsp;</td>
                                                        <%--<td id="imgview" rowspan="4">
            <s:if test="%{FILE_NAME!=null}">
                <a title="Click to view Detail" style="cursor:url('image/cur/zoomin.cur')" onclick='showbox2("Zoom");zoomimg("sampleimage/<s:property value="%{FILE_NAME}"/>");' target="belowfram"><img src="sampleimage/<s:property value="%{FILE_NAME}"/>" style="width:120px;height: 90px"/></a>
                </s:if> 
        </td>--%>
                                                        <td rowspan="3" width="300px">
                                                            <table cellpadding="0" width="100%">
                                                                <tr>
                                                                    <td class="label-1">Remarks</td>
                                                                </tr>
                                                                <tr>
                                                                    <td><s:textarea name="REMARKS" value="%{REMARKS}" theme="simple" cssClass="texts" rows="4" cssStyle="width:100%;text-transform:uppercase;overflow:auto" /></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="label-1">PCH<span class="cssspan">*</span></td>
                                                        <td><s:select id="PCH" name="PCH" list="pchlist" onchange="fabres_show_details()" cssStyle="width:80px" headerKey="" theme="simple" headerValue="PCH" value="%{PCH}" /></td>
                                                             <td class="label-1">Buyer<span class="cssspan">*</span></td>
                                                            <td  align="right"><table cellpadding="1" cellspacing="0" ><tr><td ><s:hidden name="BUYER_CODE" id="BUYER_CODE" theme="simple" value="%{BUYER_CODE}" /> <s:textfield name="BUYER_NAME" id="BUYER_NAME" theme="simple" value="%{BUYER_NAME}" cssStyle="width:230px" readonly="true" cssClass="textreadonly" />
                                                                        </td><td> <a target="popupframe2" onclick="showbox22();" style="text-decoration: none;"  href="samplebuyerlistAction.action"><img width="15px" style="border: 0px" height="15px" src="../../css/image/Search-icon-big.png" /></a>
                                                                        </td></tr></table></td>
                                                 <td class="label-1">Light Source</td>
                                                 <td>
                                                     <table cellpadding="1" cellspacing="0" width="100%"><tr><td>
                                                                 <s:select id="LIGHT_SOURCE" name="LIGHT_SOURCE" value="%{LIGHT_SOURCE}" list="# {'D65':'D65','TL84':'TL84','CWF':'CWF','UV':'UV','U30':'U30','UL-35':'UL-35','F/A':'F/A'}" onchange="" cssStyle="width:100%" headerKey="" headerValue="Primary" theme="simple"  />
                                                             </td>
                                                             <td><s:select id="LIGHT_SOURCE_S" name="LIGHT_SOURCE_S" value="%{LIGHT_SOURCE_S}" list="# {'D65':'D65','TL84':'TL84','CWF':'CWF','UV':'UV','U30':'U30','UL-35':'UL-35','F/A':'F/A'}" onchange="" cssStyle="width:100%" headerKey="" headerValue="Secondary" theme="simple"  />
                                                               </td>
                                                               <td><s:select id="LIGHT_SOURCE_T" name="LIGHT_SOURCE_T" value="%{LIGHT_SOURCE_T}" list="# {'D65':'D65','TL84':'TL84','CWF':'CWF','UV':'UV','U30':'U30','UL-35':'UL-35','F/A':'F/A'}" onchange="" cssStyle="width:100%" headerKey="" headerValue="Tertiary" theme="simple"  />
                                                               </td>
                                                         </tr></table>    
                                                             </td>
                                                      

                                                    </tr>
                                                  <%--  <tr>
                                                        <td class="label-1">Style No<span class="cssspan">*</span></td>
                                                        <td>
                                                            <table cellpadding="1px" cellspacing="0px">
                                                                <tr>
                                                                    <td><s:textfield name="STYLE_CODE" id="STYLE_CODE" theme="simple" value="%{STYLE_CODE}" cssStyle="width:80px" readonly="true" cssClass="textreadonly" /></td>
                                                                    <td valign="bottom"><a target="popupframe2" onclick="showbox22();" style="text-decoration: none;" class="texts" href="fabricsamplestyleimglistAction.action"><img width="15px" style="border: 0px" height="16px" src="../../css/image/Search-icon-big.png" /></a>
                                                                </tr>
                                                            </table>

                                                        </td>
                                                        <td class="label-1">Description</td>
                                                        <td colspan="3"><s:textfield name="STYLEDESC" id="STYLEDESC" theme="simple" value="%{STYLEDESC}" cssStyle="width:100%" readonly="true" cssClass="textreadonly" /></td>
                                                        <td>&nbsp;</td>

                                                    </tr> --%>
                                                    <tr>
                                                          <td class="label-1">Type<span class="cssspan">*</span></td>
                                                        <td colspan="3"><s:select id="SAMPLE_TYPE" name="SAMPLE_TYPE" value="%{SAMPLE_TYPE}" list="sampletypelist" listKey="SAMPLE_TYPE" listValue="SAMPE_TYPE_DESC" headerKey="" theme="simple" headerValue="Sample Type" cssStyle="width:100%;" /></td>													
                                                 
                                                       <%-- <td class="label-1">Ref Cutting<span class="cssspan">*</span></td>
                                                        <td><s:select id="REF_CUT" name="REF_CUT" list="# {'Y':'Yes','N':'No'}" cssStyle="width:80px" headerKey="" theme="simple" value="%{REF_CUT}" headerValue="Select" /></td>
                                                       --%>
                                                        <td class="label-1">Fabric Resp.<span class="cssspan">*</span></td>
                                                        <td ><s:url id="fabresid_url" action="samfabricresAction.action">
                                                                <s:param name="FAB_RESPONSIBLE">
                                                                    <s:property value="%{FAB_RESPONSIBLE}" />
                                                                </s:param>
                                                            </s:url> <sx:div id="fabresid" href="%{fabresid_url}" listenTopics="fabresid_show_detail" formId="frm" showLoadingText="Loading ......"></sx:div>
                                                            </td>
                                                            </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>
                                                            <div class="submitbutton" style="height: 15pt; padding-top: 2pt; font-size: 11px; width: 100pt">
                                                                <a target="popupframe2" style="color: White; font-weight: bold; cursor: hand; text-decoration: none;" onclick="showbox22();" href="fabrfileUploadAction.action?LOCATION_CODE=<s:property value="%{LOCATION_CODE}"/>&SAMPLE_ID=<s:property value="%{SAMPLE_ID}"/>">Attach File</a>
                                                            </div>
                                                        </td>
                                                        <td><s:url var="searchurl" action="samplemastsearchListAction.action">
                                                                <s:param name="PCH_S">
                                                                    <s:property value="PCH_S" />
                                                                </s:param>
                                                                <s:param name="STYLE_CODE_S">
                                                                    <s:property value="STYLE_CODE_S" />
                                                                </s:param>
                                                                <s:param name="SAMPLE_ID_S">
                                                                    <s:property value="SAMPLE_ID_S" />
                                                                </s:param>

                                                            </s:url> <s:hidden name="PCH_S" value="%{PCH_S}" /> <s:hidden name="STYLE_CODE_S" value="%{STYLE_CODE_S}" /> <s:hidden name="SAMPLE_ID_S" value="%{SAMPLE_ID_S}" /> <input type="button" name="btn" class="submitbutton1" value="Back"
                                                                   onclick="window.location.href='getmaterlistmethodfabricrequestAction.action?PCH_S=<s:property value="%{PCH_S}"/>&SAMPLE_ID_S=<s:property value="%{SAMPLE_ID_S}"/>&STYLE_CODE_S=<s:property value="%{STYLE_CODE_S}"/>'" /></td>
                                                        
                                                        <td style="text-align: center;">
                                                            <div class="submitbutton" style="height: 15pt; padding-top: 2pt; font-size: 11px; width: 100pt">
                                                                <a target="popupframe2" style="color: White; font-weight: bold; cursor: hand; text-decoration: none;" href="#" onclick="toggle('greshowhide123');return false;">Link another CRM</a>
                                                            </div>
                                                            <div id="greshowhide123" style="background-color: #F7F3F7; width: 500px; margin-top: 0px; z-index: 10; position: absolute; display: none; left: 250px; border-color: #6262A9; border-style: solid; border-width: 2px;">
                                                                <table cellspacing="1" width="100%" cellpadding="0px">
                                                                    <tr>
                                                                        <td>
                                                                            <table style="background-color: #000084; height: 17px; width: 100%">
                                                                                <tr class="dragme">
                                                                                    <td style="cursor: hand; cursor: pointer; color: #FFFFFF; font-weight: bold; font-size: 13px; text-align: center;" width="100%"></td>
                                                                                    <td style="cursor: hand"><a onclick="toggle('greshowhide123');return false;" href="#"><img src="images/button_close.png" border=0 style="height: 20px;"></a></td>
                                                                                </tr>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <div style="width: 100%; overflow: auto; height: 130px;">
                                                                                <s:set name="tindexcgre" id="tindexcgre" value="0" />
                                                                                <table cellspacing="1" width="100%" cellpadding="0px" id="gretable123">
                                                                                    <tr>
                                                                                        <th class="label-1" style="text-align: left;" colspan="3">CRM Request No</th>																					
                                                                                    </tr>
                                                                                    <s:if test="%{GREIGE_REQ_ID!=null && GREIGE_REQ_ID.size()>0}">
                                                                                        <s:iterator value="%{GREIGE_REQ_ID}" status="st">
                                                                                            <tr>
                                                                                                <td style="width:100%;"><s:textfield name="GREIGE_REQ_ID" id="GREIGE_REQ_ID%{#tindexcgre}" value="%{GREIGE_REQ_ID[#st.index]}" theme="simple" cssStyle="width:100%" readonly="true" cssClass="textreadonly" /></td>
                                                                                                <td width="1px"><input type="button" class="texts" style="font-size: 10px" name="btn" onclick="javascript:document.getElementById('GREIGE_REQ_ID<s:property value="%{#tindexcgre}"/>').value='';"
                                                                                                                       value="X"></td>
                                                                                                <td valign="bottom"><a target="popupframe2" onclick="showbox22();" style="text-decoration: none;" class="texts" href="fabricgreigestylelistAction.action?TINDEX=<s:property value="%{#tindexcgre}"/>&STYLE_CODE=<s:property value="%{SAMPLE_ID}"/>&LOCATION_CODE=<s:property value="%{LOCATION_CODE}"/>"><img width="15px" style="border: 0px;" height="17px"
                                                                                                                                                                                                        src="../../css/image/Search-icon-big.png" /></a></td>
                                                                                            </tr>
                                                                                            <s:set name="tindexcgre" id="tindexcgre" value="%{#tindexcgre+1}" />
                                                                                        </s:iterator>
                                                                                    </s:if>
                                                                                    <s:else>
                                                                                        <s:iterator begin="0" end="4" status="stat">
                                                                                            <tr>
                                                                                                <td style="width:100%;"><s:textfield name="GREIGE_REQ_ID" id="GREIGE_REQ_ID%{#tindexcgre}" theme="simple" value="" cssStyle="width:100%" readonly="true" cssClass="textreadonly" /></td>
                                                                                                <td width="1px"><input type="button" class="texts" style="font-size: 10px" name="btn" onclick="javascript:document.getElementById('GREIGE_REQ_ID<s:property value="%{#tindexcgre}"/>').value='';"
                                                                                                                       value="X"></td>
                                                                                                <td valign="bottom"><a target="popupframe2" onclick="showbox22();" style="text-decoration: none;" class="texts" href="fabricgreigestylelistAction.action?TINDEX=<s:property value="%{#tindexcgre}"/>&STYLE_CODE=<s:property value="%{SAMPLE_ID}"/>&LOCATION_CODE=<s:property value="%{LOCATION_CODE}"/>"><img width="15px" style="border: 0px;" height="17px"
                                                                                                                                                                                                        src="../../css/image/Search-icon-big.png" /></a></td>
                                                                                            </tr>
                                                                                            <s:set name="tindexcgre" id="tindexcgre" value="%{#tindexcgre+1}" />
                                                                                        </s:iterator>
                                                                                    </s:else>
                                                                                </table>
                                                                                <s:hidden name="grectrid1" id="grectrid1" value="%{tindexcgre}" />
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="text-align: right;"><input title="Add Row" type="button" name="hidebtn1234" value="+" class="submitbutton1" onclick="addtablerowgre('gretable123');" style="width: 18px; font-size: 20px;" /></td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </td>
                                                        <td><s:if test="%{#session.sessUserId +'-'+#session.sessUserName==USER && CLOSE_FLAG!='Yes' && FABRIC_TNACHK!='YES'}">
                                                                <s:if test='%{SAMPLE_CHECK=="YES" || SAMPLE_CHECK=="SAMYES"}'>
                                                                    <input type="button" name="btn" class="submitbutton1" value="Save" onclick="recsave()" />
                                                                </s:if>
                                                                <s:else>
                                                                    <input type="button" name="btn" class="submitbutton1" value="Save" disabled="true" />
                                                                </s:else>
                                                            </s:if> 
                                                            <s:else>
                                                                <input type="button" name="btn" disabled="true" class="submitbutton1" value="Save" onclick="recsave()" />

                                                            </s:else>
                                                        </td>
                                                        <td colspan="2">
                                                            <table style="width:100%" cellpadding="1"><tr><td>
                                                                        <s:if test="%{#session.sessUserId +'-'+#session.sessUserName==USER}">
                                                                            <s:if test='%{SAMPLE_CHECK=="YES" || SAMPLE_CHECK=="SAMYES"}'>
                                                                                <input type="button" name="btn" class="submitbutton1" value="Cancel Request" onclick="cancelreq()" />
                                                                            </s:if>
                                                                            <s:else>
                                                                                <input type="button" name="btn" class="submitbutton1" value="Cancel Request" disabled="true" />
                                                                            </s:else>
                                                                        </s:if> 
                                                                        <s:else>
                                                                            <input type="button" name="btn" class="submitbutton1" value="Cancel Request" disabled="true" />
                                                                        </s:else>
                                                                    </td>
                                                                    <s:if test="%{SAMPLE_ID!=null}">
                                                                        <td align="right" class="label-1">
                                                                            Swatch Card
                                                                        </td><td>
                                                                            <table cellpadding="0" cellspacing="0"><tr><td>
                                                                                        <s:select 
                                                                                            name="REPTYPE"
                                                                                            list="reporttype"
                                                                                            listKey="SAMPLE_TYPE"
                                                                                            listValue="SAMPE_TYPE_DESC"
                                                                                            cssClass="texts"
                                                                                            id="REPTYPE"
                                                                                            theme="simple"
                                                                                            />
                                                                                    </td><td>

                                                                                        <a id="cardhre" onclick="printcard()" target="_blank" href="void()"><img style="border:0px" src="images/printer.png"/></a>
                                                                                    </td></tr></table>
                                                                        </td>
                                                                    </s:if>

                                                                </tr></table>

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
            </table>
            <div id="showimage22" style="position: absolute; width: 500px; left: 100pt; top: 102px; visibility: hidden; z-index: 10">
                <table border="0" width="320" bgcolor="#000080" cellspacing="0" cellpadding="2">
                    <tr>
                        <td width="100%">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0" height="36px">
                                <tr>
                                    <td>
                                        <table>
                                            <tr>
                                                <td id="dragbar" style="cursor: hand; cursor: pointer; color: White; font-weight: bold; font-size: 8pt" width="100%">Shahi Exports Pvt Ltd</td>
                                                <td style="cursor: hand"><a href="#" onClick="hidebox22n();return false"><img src="images/button_close.png" height="24px" border=0></a></td>
                                            </tr>
                                        </table>
                                    </td>
                                <tr>
                                    <td width="100%" bgcolor="#FFFFFF" style="padding: 0px"><iframe src="" name="popupframe2" width="500" frameborder="0" height="440pt" scrolling="no"></iframe></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <s:if test="%{SAMPLE_ID!=null || COPY_SAMPLE_ID!=null}">
                <s:set id="sampleidtemp" name="sampleidtemp" value="0"/>
                <s:if test="%{SAMPLE_ID!=null}">
                    <s:set id="sampleidtemp" name="sampleidtemp" value="%{SAMPLE_ID}"/>
                </s:if>
                <s:if test="%{COPY_SAMPLE_ID!=null}">
                    <s:set id="sampleidtemp" name="sampleidtemp" value="%{COPY_SAMPLE_ID}"/>
                </s:if>
                <table width="100%" border="0" cellpadding="0px" cellspacing="0px">
                    <tr>
                        <td>
                              
                                        <s:set id="redtabflag1" name="redtabflag1" value="No"/>
                                     <s:iterator value="redtablist.{?#this.SAMPE_TYPE_DESC ==2}" status="redtablistst1">
                                                 <s:set id="redtabflag1" name="redtabflag1" value="%{'Yes'}"/>
                                      </s:iterator>
                                    <s:set id="redtabflag2" name="redtabflag2" value="No"/>
                                     <s:iterator value="redtablist.{?#this.SAMPE_TYPE_DESC==3}" status="redtablistst">
                                                 <s:set id="redtabflag2" name="redtabflag2" value="%{'Yes'}"/>
                                      </s:iterator>
                                      <s:set id="redtabflag3" name="redtabflag3" value="No"/>
                                     <s:iterator value="redtablist.{?#this.SAMPE_TYPE_DESC==4}" status="redtablistst">
                                                 <s:set id="redtabflag3" name="redtabflag3" value="%{'Yes'}"/>
                                      </s:iterator>
                                      <s:set id="redtabflag4" name="redtabflag4" value="No"/>
                                     <s:iterator value="redtablist.{?this.SAMPE_TYPE_DESC==5}" status="redtablistst">
                                                 <s:set id="redtabflag4" name="redtabflag4" value="%{'Yes'}"/>
                                      </s:iterator>
                                      <s:set id="redtabflag5" name="redtabflag5" value="No"/>
                                     <s:iterator value="redtablist.{?this.SAMPE_TYPE_DESC==6}" status="redtablistst">
                                                 <s:set id="redtabflag5" name="redtabflag5" value="%{'Yes'}"/>
                                      </s:iterator>
                                     
                                     
                            <sx:tabbedpanel id="mainContainerdetails" cssClass="label-1" cssStyle="height:220px;z-index:1;width:1250px" doLayout="true">
                                <sx:div label="Quality 1" id="bodyfabricid1" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=1" closable="false" preload="true" executeScripts="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                   <s:if test="%{#redtabflag1=='Yes'}">
                                        <sx:div  cssStyle="color:red"  label="Quality 2*" id="bodyfabricid2" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=2" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                         loading...
                                        </sx:div>
                                   </s:if>
                                    <s:else>
                                        <sx:div   label="Quality 2" id="bodyfabricid2" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=2" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                         loading...
                                        </sx:div>
                                    </s:else>
                                        <s:if test="%{#redtabflag2=='Yes'}">
                                             <sx:div label="Quality 3*" id="bodyfabricid3" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=3" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                        </s:if>
                                        <s:else>
                                             <sx:div label="Quality 3" id="bodyfabricid3" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=3" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                        </s:else>
                                <s:if test="%{#redtabflag3=='Yes'}">
                                     <sx:div label="Quality 4*" id="bodyfabricid4" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=4" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                </s:if>
                                <s:else>
                                     <sx:div label="Quality 4" id="bodyfabricid4" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=4" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                </s:else>
                                <s:if test="%{#redtabflag4=='Yes'}">
                                    <sx:div label="Quality 5*" id="bodyfabricid5" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=5" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                </s:if>
                                <s:else>
                                    <sx:div label="Quality 5" id="bodyfabricid5" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=5" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                </s:else>
                                <s:if test="%{#redtabflag5=='Yes'}">
                                     <sx:div label="Quality 6*" id="bodyfabricid6" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=6" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                </s:if>
                                    <s:else>
                                          <sx:div label="Quality 6" id="bodyfabricid6" href="savefabricfabricrequestAction.action?SAMPLE_ID=%{#sampleidtemp}&FABSLID=6" closable="false" preload="true" showErrorTransportText="true" loadingText="Loading  tab..." showLoadingText="true">
                                    loading...
                                </sx:div>
                                    </s:else>
                               
                            </sx:tabbedpanel></td>
                    </tr>
                    <tr>
                        <td><sx:div label="Approval Process" id="Approvalprocessid"
                                href="shahiwebpages/Sampling/Fabricrequest/approvalprocessfabricrequestAction.action?SAMPLE_ID=%{SAMPLE_ID}" cssClass="label-1">
                                loading...
                            </sx:div></td>
                    </tr>
                </table>
            </s:if>
            <div id="showimage2" style="position: absolute; width: 200px; right: 50px; top: 30px; visibility: hidden; z-index: 1;">
                <table border="0" width="320" style="background-color: transparent;" cellspacing="0" cellpadding="2">
                    <tr>
                        <td width="100%">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0" height="36px">
                                <tr>
                                    <td>
                                        <table style="background-image: url('../image/glossyback.gif'); height: 0px">
                                            <tr class="dragme">

                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100%" style="padding: 0px" id="viewzoomimg"><iframe name="belowfram" id="belowfram" style="background-color: #F2F9FB; display: none; z-index: 1" allowTransparency="true" width="500px" height="0.0px" frameborder="0" src="" scrolling="no"> </iframe></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="showimage3" style="position: absolute; width: 800px; right: 50px; top: 50px; visibility: hidden; z-index: 1;">
                <table border="0" width="320" bgcolor="#a1d1f4" cellspacing="0" cellpadding="2">
                    <tr>
                        <td width="100%">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0" height="36px">
                                <tr>
                                    <td>
                                        <table style="background-image: url('../image/glossyback.gif');">
                                            <tr class="dragme">
                                                <td id="dragbar1" style="cursor: hand; cursor: pointer; color: White; font-weight: bold; font-size: 8pt" width="100%"></td>
                                                <td style="cursor: hand"><a onclick="hidebox3();" href="#"><img src="images/button_close.png" height="24px" border=0></a></td>
                                            </tr>
                                        </table>
                                    </td>
                                <tr>
                                    <td width="100%" style="padding: 0px"><iframe name="belowfram3" id="belowfram3" style="background-color: #F2F9FB; display: none; z-index: 1" allowTransparency="true" width="1000px" height="500.0px" frameborder="0" src="" scrolling="no"></iframe></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="showimage4" style="position: absolute; width: 800px; left: 130px; top: 250px; visibility: hidden; z-index: 1;">
                <table border="0" width="320" bgcolor="#a1d1f4" cellspacing="0" cellpadding="2">
                    <tr>
                        <td width="100%">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0" height="36px">
                                <tr>
                                    <td>
                                        <table style="background-image: url('../image/glossyback.gif');">
                                            <tr class="dragme">
                                                <td id="dragbar1" style="cursor: hand; cursor: pointer; color: White; font-weight: bold; font-size: 8pt" width="100%"></td>
                                                <td style="cursor: hand"><a onclick="hidebox4n();return false;" href="#"><img src="images/button_close.png" height="24px" border=0></a></td>
                                            </tr>
                                        </table>
                                    </td>
                                <tr>
                                    <td width="100%" style="padding: 0px"><iframe name="belowfram4" id="belowfram4" style="background-color: #F2F9FB; display: none; z-index: 1" allowTransparency="true" width="950px" height="170.0px" frameborder="0" src="" scrolling="no"></iframe></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="showimage5" style="position: absolute; width: 1215px; left: 10px; top: 2px; visibility: hidden; z-index: 2;">
                <table border="0" width="320" bgcolor="#a1d1f4" cellspacing="0" cellpadding="2">
                    <tr>
                        <td width="100%">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0" height="36px">
                                <tr>
                                    <td>
                                        <table style="background-color: #ADB6DE; height: 25px; width: 100%">
                                            <tr class="dragme">
                                                <td style="cursor: hand; cursor: pointer; color: #395194; font-weight: bold; font-size: 16px; text-align: center;" width="100%">Activity Details</td>
                                                <td style="cursor: hand"><a onclick="hidebox5();return false;" href="#"><img src="images/button_close.png" border=0 style="height: 24px;"></a></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100%" style="padding: 0px;"><iframe name="belowfram5" id="belowfram5" style="background-color: #F2F9FB; display: none; z-index: 10; margin-top: 0px; margin-left: 0px;" allowTransparency="true" width="1210px" height="650px" frameborder="0" src="" scrolling="no"></iframe></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
        <table style="width: 100%;" cellpadding="0">
            <tr>
                <td align="center">
                    <table width="100%" align="center" cellpadding="4">
                        <s:if test="%{SAMPLE_ID!=null && CALSHOW=='AVAIL'}">
                            <tr>
                                <td align="center">
                                    <iframe name="belowframcal" id="belowframcal" width="100%" height="150.0px" frameborder="0" src="http://172.17.3.57:8080/seplapplication/samfabr/samcalenderaction.htm?reqId=<s:property value="%{SAMPLE_ID}"/>&locationCode=<s:property value="%{LOCATION_CODE}"/>" scrolling="no">
                                </td>
                            </tr>
                        </s:if>
                        <tr>
                            <td align="center" style="color: red; font-weight: bold">
                                <div style="height: 5pt">
                                    <s:actionerror />
                                    <s:fielderror />
                                    <s:actionmessage />
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </body>



</html>