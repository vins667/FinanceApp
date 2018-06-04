<%-- 
    Document   : WalMart_Inv
    Created on : Feb 08, 2017, 12:21:48 PM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="../../js/dom-drag.js"></script>
<html> 
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('../../image/table-gradient.jpg');
            }
            tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }
            .root1
            {
                position:absolute;
                height:280px;
                width:600px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            }

            .handle1
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px

            }

        </style>
        <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 30px ;
        
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);                     
                  }
                tbody {
                    height: auto;
                }
                tfoot{
                    background:#3383bb;
                    font-weight:bold;
                }
                .tr2 {
                     position:absolute;
                     bottom:expression(this.offsetParent.scrollTop);
                  }
            </style>
        <![endif]-->
        <script language="javascript">
            
   function SearchManuf(objsrc,objtrg)
    { 
     
     
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxManufBUYERAjaxA', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {     
                
                 document.getElementById('addapprofrm').src="searchcodedesc1WalMart_InvA.action?SEARCH_CODE="+objsrc.value;
                 openpop('approveraddid');
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
             }
             else
             { 
               if(b[0]=='Data Not Found')
               {    
                 objsrc.value='';
                 document.getElementById(objtrg).value='';
                 alert(b[0]);
                 document.getElementById('addapprofrm').src="searchcodedesc1WalMart_InvA.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                  // document.getElementById('FBANK').value=b[0];
                   document.getElementById('MANUFCODE').value=b[0];
                   document.getElementById('MANUFADD').value=b[1];
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}

            function searchdetail()
            {
               
                document.frm.action = "WalMart_InvA.action?FLAG1=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
                
            }
            
            function clearrec()
            {
               
                document.frm.action = "clearWalMart_InvA.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
                
            }
            
            function openpop(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv(a)
            {
                document.getElementById(a).style.display = 'none';
            }
            
               
            function openpage(frame, url, a, id, b,fid,qid)
            {
                     //alert(url+"?ITMIDL="+document.getElementById(fid).value+"&QTYID="+document.getElementById(qid).value+"& invid="+document.getElementById('INVOICE_S').value);
                        document.getElementById(frame).src = url+"?ITMIDL="+document.getElementById(fid).value+"&QTYID="+document.getElementById(qid).value+"&invid="+document.getElementById('INVOICE_S').value;
                        document.getElementById(a).style.display = '';
                        document.getElementById(id).innerHTML = b;
            }
            
            function tabEs(obj, e,frame, url, a, id, b,fid,qid) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13){
                    openpage(frame, url, a, id, b,fid,qid);
                    
                    return false;
                }
            }
            
                      
            function valtot()
            {
                 PONO=document.frm.PONO;
                 QNTY=document.frm.QNTY;
                 RATE=document.frm.RATE;
                 HNGRRATE=document.frm.HNGRRATE;
                 VALU=document.frm.VALU;
                
                    if(PONO.length>0){
                    for (var i = 0; i < PONO.length; i++) {
                        if(QNTY[i].value>0 || RATE[i].value>0 || HNGRRATE.value>0){
                         VALU[i].value= (eval(QNTY[i].value)*(eval(RATE[i].value)+eval(HNGRRATE[i].value))).toFixed(2);
                        } 
                        else{
                           
                        }
                    }
                    
                    }
                    else{
                        if(QNTY.value>0 || RATE.value>0 ||HNGRRATE.value>0){
                          VALU.value= (eval(QNTY.value)*(eval(RATE.value)+eval(HNGRRATE.value))).toFixed(2);;
                    }
                    } 
            }
            
            function totpckgs()
            {
                var totpck=0.0;
                 TOTPACKG=document.frm.TOTPACKG;
                 PCKGES_L=document.frm.PCKGES_L;
                  if(PCKGES_L.length>0){
                    for (var i = 0; i < PCKGES_L.length; i++) {
                        if(PCKGES_L[i].value>0){
                         totpck=totpck+eval(PCKGES_L[i].value);
                         document.frm.TOTPACKG.value=totpck;
                        } 
                        else{
                        }
                    }
                    }
                    else{
                        if(PCKGES_L.value>0){
                         totpck=totpck+eval(QTY_L.value);
                         document.frm.TOTPACKG.value=totpck;
                    }
                    } 
            }
       function pckratioQty()
            {
                var totpck=0.0;
                var totqty=0.0;
                 ITMID_L=document.frm.ITMID_L;//this is for loop
                 TOTQTY=document.frm.TOTQTY;
                 PCKGES_L=document.frm.PCKGES_L;
                 PCKRATIO_L=document.frm.PCKRATIO_L;
                 QTY_L=document.frm.QTY_L;
                 
                  if(ITMID_L.length>0){
                    for (var i = 0; i < ITMID_L.length; i++) {
                        if(PCKRATIO_L[i].value>0){
                        QTY_L[i].value=eval(PCKGES_L[i].value)*eval(PCKRATIO_L[i].value);
                         totpck=totpck+eval(QTY_L[i].value);
                         document.frm.TOTQTY.value=totpck;
                        }
                        else{
                            
                        }
                    }
                    }
                    else{
                        if(PCKRATIO_L.value>0){
                         QTY_L.value=eval(PCKGES_L.value)*eval(PCKRATIO_L.value);
                       
                         totpck=totpck+eval(QTY_L.value);
                         document.frm.TOTQTY.value=totpck;
                        }
                    } 
            }
      
            
            function volQty()
            {
                  var totpck=0.0;
                  var val=1000000;
                  
                 ITMID_L=document.frm.ITMID_L;//this is for loop
                 PCKGES_L=document.frm.PCKGES_L;
                 BOX_L_L=document.frm.BOX_L_L;
                 BOX_W_L=document.frm.BOX_W_L;
                 BOX_H_L=document.frm.BOX_H_L;
                 BOX_VOL_L=document.frm.BOX_VOL_L;
                 
                  if(ITMID_L.length>0){
                    for (var i = 0; i < ITMID_L.length; i++) {
                        if(BOX_H_L[i].value>0){
                         BOX_VOL_L[i].value=((eval(PCKGES_L[i].value)*eval(BOX_L_L[i].value)*eval(BOX_W_L[i].value)*eval(BOX_H_L[i].value))/val).toFixed(3);
                        // totpck=totpck+eval(BOX_VOL_L[i].value);
                        // document.frm.TOTVALNET.value=totpck;
                        }else{
                            
                        }
                    }
                    }
                    else{
                        if(BOX_H_L.value>0){
                         BOX_VOL_L.value=((eval(PCKGES_L.value)*eval(BOX_L_L.value)*eval(BOX_W_L.value)*eval(BOX_H_L.value))/val).toFixed(3);
                         //totpck=totpck+eval(TOT_NETWGHT_L.value);
                         //document.frm.TOTVALNET.value=totpck;
                        }
                    } 
            }
            function pcknetQty()
            {
                var totpck=0.0;
                var totqty=0.0;
                 ITMID_L=document.frm.ITMID_L;//this is for loop
                 TOTVALNET=document.frm.TOTVALNET;
                 PCKGES_L=document.frm.PCKGES_L;
                 NETWGHT_L=document.frm.NETWGHT_L;
                 TOT_NETWGHT_L=document.frm.TOT_NETWGHT_L;
                 
                  if(ITMID_L.length>0){
                    for (var i = 0; i < ITMID_L.length; i++) {
                        if(NETWGHT_L[i].value>0){
                        TOT_NETWGHT_L[i].value=eval(PCKGES_L[i].value)*eval(NETWGHT_L[i].value);
                         totpck=totpck+eval(TOT_NETWGHT_L[i].value);
                         document.frm.TOTVALNET.value=totpck;
                        }else{
                            
                        }
                    }
                    }
                    else{
                        if(NETWGHT_L.value>0){
                         TOT_NETWGHT_L.value=eval(PCKGES_L.value)*eval(NETWGHT_L.value);
                       
                         totpck=totpck+eval(TOT_NETWGHT_L.value);
                         document.frm.TOTVALNET.value=totpck;
                        }
                    } 
            }
            
            function pckGrossQty()
            {
                var totpck=0.0;
                var totqty=0.0;
                 ITMID_L=document.frm.ITMID_L;//this is for loop
                 TOTVALGROSS=document.frm.TOTVALGROSS;
                 PCKGES_L=document.frm.PCKGES_L;
                 GROSSWGHT_L=document.frm.GROSSWGHT_L;
                 TOT_GROSSWGHT_L=document.frm.TOT_GROSSWGHT_L;
                 
                  if(ITMID_L.length>0){
                    for (var i = 0; i < ITMID_L.length; i++) {
                        if(GROSSWGHT_L[i].value>0){
                        TOT_GROSSWGHT_L[i].value=(eval(PCKGES_L[i].value)*eval(GROSSWGHT_L[i].value)).toFixed(2);
                         totpck=totpck+eval(TOT_GROSSWGHT_L[i].value);
                         document.frm.TOTVALGROSS.value=totpck.toFixed(2);
                        }else{
                            
                        }
                    }
                    }
                    else{
                        if(GROSSWGHT_L.value>0){
                         TOT_GROSSWGHT_L.value=eval(PCKGES_L.value)*eval(GROSSWGHT_L.value);
                       
                         totpck=totpck+eval(TOT_GROSSWGHT_L.value);
                         document.frm.TOTVALGROSS.value=totpck;
                        }
                    } 
            }

            function tabE(obj, e) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for (var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other

                        if (obj == ele[i])
                        {
                            ele[q].focus();
                            break
                        }
                    }
                    return false;
                }

            }
            
    
    
   
           function tabE_Lst1(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;
                    if (e.keyCode == 40) {
                    document.getElementById("ITMID_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("ITMID_L"+y).focus();
                    }
            }
            function tabE_Lst2(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("ITMIDDESC_L"+x).focus();
                    }
                     if (e.keyCode == 38) {
                    document.getElementById("ITMIDDESC_L"+y).focus();
                    }
            }
            function tabE_Lst3(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("PCKGES_L"+x).focus();
                }
                if (e.keyCode == 38) {
                    document.getElementById("PCKGES_L"+y).focus();
                    }
            }
             function tabE_Lst4(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                    if (e.keyCode == 40) {
                    document.getElementById("RATIO_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("RATIO_L"+y).focus();
                    }
            }
            function tabE_Lst5(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                    if (e.keyCode == 40) {
                    document.getElementById("PCKRATIO_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("PCKRATIO_L"+y).focus();
                    }
            }
            function tabE_Lst6(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                    if (e.keyCode == 40) {
                    document.getElementById("RATE_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("RATE_L"+y).focus();
                    }
            }
            function tabE_Lst7(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("NETWGHT_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("NETWGHT_L"+y).focus();
                    }
            }
            function tabE_Lst8(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                    if (e.keyCode == 40) {
                    document.getElementById("GROSSWGHT_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("GROSSWGHT_L"+y).focus();
                    }
            }
            function tabE_Lst9(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("BOX_L_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("BOX_L_L"+y).focus();
                    }
            }
            function tabE_Lst10(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("BOX_W_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("BOX_W_L"+y).focus();
                    }
            }
            function tabE_Lst11(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("BOX_H_L"+x).focus();
                    }
                     if (e.keyCode == 38) {
                    document.getElementById("BOX_H_L"+y).focus();
                    }
            }
            function tabE_Lst12(obj, e,id) {
                x=eval(id)+1;
                 y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("BOX_VOL_L"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("BOX_VOL_L"+y).focus();
                    }
            }
            
            function tabE_Brk(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 40) {
                    document.getElementById("MTRIALBRKUP"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("MTRIALBRKUP"+y).focus();
                    }
            }
    
    
               


            function has_duplicates() {
                var x = document.frm.BILL_NO;
                for (var i = 0; i < x.length; i++) {
                    for (j = i + 1; j < x.length; j++) {
                        if (x[i].value > 0) {
                            if (x[i].value == x[j].value) {
                                alert("This is the Duplicate Value");
                                x[i].value = '';
                                return false;
                            }
                        }
                    }
                }
                return true;
            }


            function saverec()
            {
                document.frm.action = "update1WalMart_InvA.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
            }

            function printrec()
            {
                document.frm.action = "printsWalMart_InvA.action?REPORTTYPE=PDF&FLAG1=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
            }
            function pckngprintrec()
            {
                document.frm.action = "pckngprintsWalMart_InvA.action?REPORTTYPE=PDF&FLAG1=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
            }
            function scdprintrec()
            {
                document.frm.action = "scdprintsWalMart_InvA.action?REPORTTYPE=PDF&FLAG1=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
            }
            

            function validatenumber(a)
            {
                if (!a.value == "" && !a.value.match(/^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$/))
                {
                    alert('Invalid Input, Only Numbers Allowed');
                    a.value = "";
                    a.focus();
                    return false;
                }
            }

            
            function waitPreloadPage() { //DOM
                if (document.getElementById) {
                    document.getElementById('prepage').style.visibility = 'hidden';
                } else {
                    if (document.layers) { //NS4
                        document.prepage.visibility = 'hidden';
                    }
                    else { //IE4
                        document.all.prepage.style.visibility = 'hidden';
                    }
                }
            }
        </script>

    </head>

    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
<!--        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>-->
        <form action=""   method="post" name="frm" >

            <s:hidden id="flagVAL" name="flagVAL" value="%{flag4}"/>
            <s:hidden id="pchlist" name="pchlist" value="%{pchlist}"/>
            <s:hidden id="CNTRY" name="CNTRY" value="%{CNTRY}"/>
            


            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="##231819">
                            <tr>
                                <th align="center" style="font-size:14.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:#231819;">
                                    Walmart Invoice Detail</th>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f7f7f7" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                            <td style="font-size:8pt;font-weight:bold" style="width:200px">Invoice No: <s:textfield name="INVOICE_S" id="INVOICE_S" cssStyle="text-transform:uppercase;width:80pt" theme="simple" maxLength="10"/>
                                            </td>
                                            <td align="left">
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             </td>
                                             <td style="width:200px"></td>
                                             <td align="center" >
                                                    <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                                     <button  id="saveheadId"  class="sexybutton" onclick="clearrec();" ><span><span><span class="undo" id="saveId" >Clear</span></span></span></button>
                                                    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                             </td>
                                             <td align="right">
                                                    <button  id="saveheadId1"  class="sexybutton" onclick="printrec();" ><span><span><span class="print" id="printId">Inv</span></span></span></button> 
                                                    <button  id="saveheadId1"  class="sexybutton" onclick="pckngprintrec();" ><span><span><span class="print" id="printId">PL</span></span></span></button> 
                                                    <button  id="saveheadId1"  class="sexybutton" onclick="scdprintrec();" ><span><span><span class="print" id="printId">SCD</span></span></span></button> 
                                                  
                                             </td>
                                              <s:hidden name="aausrid" vaue="%{aausrid}"/>
                                        </tr>

                                    </table>
                                </td>
                            </tr>
                        </table> 
                    </td>
                </tr>
                <tr><td>
                        <table bgcolor="#f7f7f7" style="margin-top:0pt;" align="center" width="100%" cellpadding="0" cellspacing="0" >
                            <tr>
                                <th align="left">Inv No</th>
                                <th align="left">Plan No</th>
                                <th align="left">Inv date</th>
                                <th align="left">Buyer Code</th>
                                <th align="left">Buyer No.</th>
                                <th align="left">TTO Date</th>
                                <th align="left">Country</th>
                                <th align="left">TO Date</th>
                                <th align="left">77 Date</th>
                                <th align="left">Inv Qty</th>
                                <th align="left">Ship Qty</th>
                            </tr>
                            <tr bgcolor="#23DA87">
                                <td style="font-size:8pt"><s:property value="INVOICENO"/></td>
                                <td style="font-size:8pt"><s:property value="PLANNO_N"/></td>
                                <td style="font-size:8pt"><s:property value="INVOICEDATE"/></td>
                                <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                <td style="font-size:8pt"><s:property value="BUYER_DESC"/></td>
                                <td style="font-size:8pt"><s:property value="TTO_DATE"/></td>
                                <td style="font-size:8pt"><s:property value="CNTRY"/></td>
                                <td style="font-size:8pt"><s:property value="TO_DATE"/></td>
                                <td style="font-size:8pt"><s:property value="TO_DATE_s"/></td>
                                <td style="font-size:8pt"><s:property value="INV_QTY"/></td>
                                <td style="font-size:8pt"><s:property value="SHIP_QTY"/></td>
                            </tr> 
                        </TABLE>
                    </td>
                </tr>

                <tr>
                    <td>
                        <table bgcolor="#f2f2f2" align="center" width="100%" cellspacing="0" cellpadding="0" border="0">
                            <tr>
                                <td style="font-size:8pt;font-weight:bold">Vessel&nbsp;No.</td>
                                <td>
                                   <s:textfield id="VESELNO" name="VESELNO" value="%{VESELNO}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="1" />
                                </td>
                                <td style="font-size:8pt;font-weight:bold">L/C-OA&nbsp;No.</td>
                                <td>
                                   <s:textfield id="LCOANO" name="LCOANO" value="%{LCOANO}" theme="simple" readonly="true" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="6"/>
                                   <a href="searchcodedescWalMart_InvA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                </td>
                               <td style="font-size:8pt;font-weight:bold">Shipping&nbsp;Line</td>
                                <td>
                                   <s:textfield id="SHIPNGLINE" name="SHIPNGLINE" value="%{SHIPNGLINE}" theme="simple" cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" tabindex="11"/>
                                </td>
                               
                                <td style="font-size:8pt;font-weight:bold">Ship&nbsp;Case</td>
                                <td>
                                   <s:textfield id="SHIPCASE" name="SHIPCASE" value="%{SHIPCASE}" theme="simple" cssStyle="text-transform:uppercase" tabindex="15"/>
                                </td> 
                                  <td style="font-size:8pt;font-weight:bold">Pay Term</td>
                                <td>
                                   <s:select id="PAYMNTTRM" name="PAYMNTTRM" value="%{PAYMNTTRM}" list="# {'':'Select','LC':'LC','OA':'OA'}" theme="simple" cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" tabindex="18" />
                                </td>
                            </tr>
                             <tr>
                                 <td style="font-size:8pt;font-weight:bold">Flight&nbsp;No.</td>
                                <td>
                                   <s:textfield id="FLGHTNO" name="FLGHTNO" value="%{FLGHTNO}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="4"/>
                                </td>
                                 
                                 
                                <td style="font-size:8pt;font-weight:bold">L/C Desc</td>
                                <td  colspan="3"> 
                                   <s:textfield id="LCDESC" name="LCDESC" value="%{LCDESC}" theme="simple" cssStyle="text-transform:uppercase;width:275pt;font-size:9pt;" tabindex="7"/>
                                </td> 
                                
                                  <td style="font-size:8pt;font-weight:bold">Tarrif&nbsp;Net&nbsp;No.</td>
                                <td>
                                   <s:textfield id="TARRIFNO" name="TARRIFNO" value="%{TARRIFNO}" theme="simple" cssStyle="text-transform:uppercase" tabindex="16" />
                                </td> 
                                   <td style="font-size:8pt;font-weight:bold">Mode</td>
                                <td>
                                  <s:select id="MODE" name="MODE" value="%{MODE}" list="# {'':'Select','CYCY':'CYCY','CFSCY':'CFSCY','CYCFS':'CYCFS','CFSCFS':'CFSCFS'}" theme="simple" readonly="true" cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" tabindex="19"/>
                                </td>
                               
                            </tr>
                             <tr>
                                 <td style="font-size:8pt;font-weight:bold">Cargo&nbsp;Recv&nbsp;Date</td>
                                <td>
                                  <sx:datetimepicker id="CRGORCRDDT" name="CRGORCRDDT" value="%{CRGORCRDDT}" displayFormat="dd-MMM-yyyy" tabindex="2"/>
                                </td> 
                                 
                                 
                                  <td style="font-size:8pt;font-weight:bold">From&nbsp;Port</td>
                                <td>
                                   <s:textfield id="FROMPORT" name="FROMPORT" value="%{FROMPORT}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="8"/>
                                </td> 
                                 
                                <td style="font-size:8pt;font-weight:bold">N/N&nbsp;WT/GMT</td>
                                <td>
                                   <s:textfield id="TEXTILE" name="TEXTILE" value="%{TEXTILE}" theme="simple" cssStyle="text-transform:uppercase;width:70pt;font-size:9pt" tabindex="12"/>
                                </td> 
                                
                                <td style="font-size:8pt;font-weight:bold">Fiber&nbsp;Cntnt&nbsp;for&nbsp;CA</td>
                                <td>
                                   <s:textfield id="FIBRCNTNT" name="FIBRCNTNT" value="%{FIBRCNTNT}" theme="simple" cssStyle="text-transform:uppercase" tabindex="16"/>
                                </td>
                                  <td style="font-size:8pt;font-weight:bold">Dept</td>
                                <td>
                                  <s:textfield id="DEPTCODE" name="DEPTCODE" value="%{DEPTCODE}" theme="simple" cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;" tabindex="20" />
                                </td>
                            </tr>
                            <tr>
                                
                                <td style="font-size:8pt;font-weight:bold">ETD&nbsp;Date</td> 
                                <td>
                                  <sx:datetimepicker id="ETDDT" name="ETDDT" value="%{ETDDT}" displayFormat="dd-MMM-yyyy" tabindex="3" />
                                </td>
                                
                                
                                <td style="font-size:8pt;font-weight:bold">Discharge Port</td>
                                <td>
                                   <s:textfield id="DISCHARGEPORT" name="DISCHARGEPORT" value="%{DISCHARGEPORT}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="9"/>
                                </td> 
                                
                                
                                <td style="font-size:8pt;font-weight:bold">BA&nbsp;Type</td>
                                <td style="font-size:9px;font-weight: bold;">
                                    <s:radio id="BA_TYPE" name="BA_TYPE" value="%{'WGP'}" list="# {'WGP' : 'WGP', 'DSG' : 'DSG'}" theme="simple" tabindex="13" />
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">Pack&nbsp;Ratio</td>
                                <td>
                                   <s:textfield id="PCKRATIO" name="PCKRATIO" value="%{PCKRATIO}" theme="simple" cssStyle="text-transform:uppercase" tabindex="17" />
                                </td>
                                
                                
                                 <td style="font-size:8pt;font-weight:bold">PO&nbsp;Type</td>
                                <td>
                                   <s:textfield id="POTYP" name="POTYP" value="%{POTYP}" theme="simple" cssStyle="text-transform:uppercase;width:50pt;font-size:9pt;"  tabindex="21"/>
                                </td> 
                            </tr>
                            <tr> 
                                <td style="font-size:8pt;font-weight:bold">Duty 1</td>
                                <td>
                                    <s:textfield id="DUTY1" name="DUTY1" value="%{DUTY1}" theme="simple" onblur="validatenumber(this);" cssStyle="width:120pt;font-size:9pt;" tabindex="5" />
                                </td>
                                  
                                
                                <td style="font-size:8pt;font-weight:bold">Duty 2</td>
                                <td>
                                   <s:textfield id="DUTY2" name="DUTY2" value="%{DUTY2}" theme="simple" onblur="validatenumber(this);" cssStyle="width:120pt;font-size:9pt;" tabindex="10"/>
                                </td>
                                
                               <td style="font-size:8pt;font-weight:bold">Manuf&nbsp;Code</td>
                                <td>
                                  <s:textfield id="MANUFCODE" name="MANUFCODE" value="%{MANUFCODE}" theme="simple"  cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" onblur="if(this.value!='') return SearchManuf(this,'MANUF1')" tabindex="14" />
                                  <a href="searchcodedesc1WalMart_InvA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                </td>
                                <td colspan="4">
                                  <s:textfield id="MANUFADD" name="MANUFADD" value="%{MANUFADD}" theme="simple" style="width:590px"  cssClass="textreadonly" readonly="true"/>
                                </td>  
                            </tr>
                            <tr style="height:15pt;">
                                <td colspan="5"></td>
                            </tr>
                            
                           
                        </table>
                    </td>
                    </tr>
                             <tr>
                                <td>
                                  <div  style="width:100%; overflow:auto; height:180pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#B1CCD9" cellspacing="0" cellpadding="0" width="100%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);">
                                                    <th style="font-size:8pt;" align="left">Item No</th>
                                                    <th style="font-size:8pt;" align="left">Item ID Description</th>
                                                    <th style="font-size:8pt;" align="right">Pkgs</th>
                                                    <th style="font-size:8pt;" align="left">Ratio</th>
                                                    <th style="font-size:8pt;" align="left">Pck Ratio</th>
                                                    <th style="font-size:8pt;" align="left">Rate</th>
                                                    <th style="font-size:8pt;" align="right">Qty</th>
                                                    <th style="font-size:8pt;" align="right">Net</th>
                                                    <th style="font-size:8pt;" align="right">Gross</th>
                                                    <th style="font-size:8pt;" align="right">L</th>
                                                    <th style="font-size:8pt;" align="right">W</th>
                                                    <th style="font-size:8pt;" align="right">H</th>
                                                    <th style="font-size:8pt;" align="right">Vol</th>
                                                    <th style="font-size:8pt;" align="right">Net</th>
                                                    <th style="font-size:8pt;" align="right">Gross</th>
                                                    
                                                  </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                                      <s:set id="cnt" name="cnt" value="%{0}"/>
                                                      <s:if test="%{INVOICE_S!=null}">
                                                         <s:if test="%{WalmartList.size()>0}">
                                                         <s:iterator value="WalmartList" status="st1"> 
                                                         <tr bgcolor="#f7f7f7">
                                                             <s:hidden id="SRN1" name="SRN1" value="%{#cnt+1}"/> 
                                                            <td style="font-size:8pt;"><s:textfield id="ITMID_L%{#cnt+1}" name="ITMID_L" value="%{BITMID_L}" cssStyle="width:120px;text-align:left;text-transform:uppercase;" theme="simple" onkeydown="return tabE_Lst1(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ITMIDDESC_L%{#cnt+1}" name="ITMIDDESC_L" value="%{BITMIDDESC_L}" cssStyle="width:240px;text-align:left" theme="simple" onkeydown="return tabE_Lst2(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKGES_L%{#cnt+1}" name="PCKGES_L" value="%{BPCKGES_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="totpckgs();pcknetQty();pckratioQty();pckGrossQty();volQty();" onkeydown="return tabE_Lst3(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATIO_L%{#cnt+1}" name="RATIO_L" value="%{BRATIO_L}" cssStyle="width:70px;text-align:left" theme="simple"  onkeydown="return tabE_Lst4(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKRATIO_L%{#cnt+1}" name="PCKRATIO_L" value="%{BPCKRATIO_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckratioQty();" onkeydown="return tabE_Lst5(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATE_L%{#cnt+1}" name="RATE_L" value="%{BRATE_L}" cssStyle="width:70px;text-align:right" theme="simple"  onkeypress="return tabEs(this,event,'addapprofrm','brkupWalMart_InvA.action', 'approveraddid','Report','Item Breakup','ITMID_L%{#cnt+1}','QTY_L%{#cnt+1}');" onkeydown="return tabE_Lst6(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QTY_L%{#cnt+1}" name="QTY_L" value="%{BQTY_L}" cssStyle="width:70px;text-align:right; " theme="simple" readonly="true"  cssClass="textreadonly" onblur="totqnty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="NETWGHT_L%{#cnt+1}" name="NETWGHT_L" value="%{BNETWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pcknetQty();" onkeydown="return tabE_Lst7(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="GROSSWGHT_L%{#cnt+1}" name="GROSSWGHT_L" value="%{BGROSSWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckGrossQty();" onkeydown="return tabE_Lst8(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_L_L%{#cnt+1}" name="BOX_L_L" value="%{BBOX_L_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst9(this,event,'%{#cnt+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_W_L%{#cnt+1}" name="BOX_W_L" value="%{BBOX_W_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst10(this,event,'%{#cnt+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_H_L%{#cnt+1}" name="BOX_H_L" value="%{BBOX_H_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst11(this,event,'%{#cnt+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_VOL_L%{#cnt+1}" name="BOX_VOL_L" value="%{BBOX_VOL_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst12(this,event,'%{#cnt+1}');" cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_NETWGHT_L%{#cnt+1}" name="TOT_NETWGHT_L" value="%{BTOT_NETWGHT_L}" cssStyle="width:80px;text-align:right; " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_GROSSWGHT_L%{#cnt+1}" name="TOT_GROSSWGHT_L" value="%{BTOT_GROSSWGHT_L}" cssStyle="width:80px;text-align:right; " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>  
                                                         </tr>
                                                          </s:iterator>
                                                          <s:iterator begin="cnt" end="30" status="st1">
                                                             <tr>
                                                                 <s:hidden id="SRN1" name="SRN1" value="%{#cnt+1}"/> 
                                                            <td style="font-size:8pt;"><s:textfield id="ITMID_L%{#cnt+1}" name="ITMID_L" value="%{ITMID_L}" cssStyle="width:120px;text-align:left;text-transform:uppercase;" theme="simple" onkeydown="return tabE_Lst1(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ITMIDDESC_L%{#cnt+1}" name="ITMIDDESC_L" value="%{ITMIDDESC_L}" cssStyle="width:240px;text-align:left" theme="simple" onkeydown="return tabE_Lst2(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKGES_L%{#cnt+1}" name="PCKGES_L" value="%{PCKGES_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="totpckgs();pcknetQty();pckratioQty();pckGrossQty();" onkeydown="return tabE_Lst3(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATIO_L%{#cnt+1}" name="RATIO_L" value="%{RATIO_L}" cssStyle="width:70px;text-align:left" theme="simple" onkeydown="return tabE_Lst4(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKRATIO_L%{#cnt+1}" name="PCKRATIO_L" value="%{PCKRATIO_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckratioQty();" onkeydown="return tabE_Lst5(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATE_L%{#cnt+1}" name="RATE_L" value="%{RATE_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeypress="return tabEs(this,event,'addapprofrm','brkupWalMart_InvA.action', 'approveraddid','Report','Item Breakup','ITMID_L%{#cnt+1}','QTY_L%{#cnt+1}');"  onkeydown="return tabE_Lst6(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QTY_L%{#cnt+1}" name="QTY_L" value="%{QTY_L}" cssStyle="width:70px;text-align:right " theme="simple" readonly="true"  cssClass="textreadonly" onblur="totqnty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="NETWGHT_L%{#cnt+1}" name="NETWGHT_L" value="%{NETWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pcknetQty();" onkeydown="return tabE_Lst7(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="GROSSWGHT_L%{#cnt+1}" name="GROSSWGHT_L" value="%{GROSSWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckGrossQty();" onkeydown="return tabE_Lst8(this,event,'%{#cnt+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_L_L%{#cnt+1}" name="BOX_L_L" value="%{BOX_L_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst9(this,event,'%{#cnt+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_W_L%{#cnt+1}" name="BOX_W_L" value="%{BOX_W_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst10(this,event,'%{#cnt+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_H_L%{#cnt+1}" name="BOX_H_L" value="%{BOX_H_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst11(this,event,'%{#cnt+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_VOL_L%{#cnt+1}" name="BOX_VOL_L" value="%{BOX_VOL_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst12(this,event,'%{#cnt+1}');" cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_NETWGHT_L%{#cnt+1}" name="TOT_NETWGHT_L" value="%{TOT_NETWGHT_L}" cssStyle="width:80px;text-align:right " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_GROSSWGHT_L%{#cnt+1}" name="TOT_GROSSWGHT_L" value="%{TOT_GROSSWGHT_L}" cssStyle="width:80px;text-align:right; " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                            </tr>
                                                       </s:iterator>
                                                                
                                                            </s:if>
                                                             <s:else>
                                                                            <s:iterator begin="cnt" end="30" status="st1">
                                                                            <tr bgcolor="#f7f7f7">
                                                                                 <s:hidden id="SRN1" name="SRN1" value="%{#st1.index+1}"/>
                                                            <td style="font-size:8pt;"><s:textfield id="ITMID_L%{#st1.index+1}" name="ITMID_L" value="%{ITMID_L}" cssStyle="width:120px;text-align:left;text-transform:uppercase;" theme="simple" onkeydown="return tabE_Lst1(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ITMIDDESC_L%{#st1.index+1}" name="ITMIDDESC_L" value="%{ITMIDDESC_L}" cssStyle="width:240px;text-align:left" theme="simple" onkeydown="return tabE_Lst2(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKGES_L%{#st1.index+1}" name="PCKGES_L" value="%{PCKGES_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="totpckgs();pcknetQty();pckratioQty();pckGrossQty();" onkeydown="return tabE_Lst3(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATIO_L%{#st1.index+1}" name="RATIO_L" value="%{RATIO_L}" cssStyle="width:70px;text-align:left" theme="simple" onkeydown="return tabE_Lst4(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKRATIO_L%{#st1.index+1}" name="PCKRATIO_L" value="%{PCKRATIO_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckratioQty();" onkeydown="return tabE_Lst5(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATE_L%{#st1.index+1}" name="RATE_L" value="%{RATE_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeypress="return tabEs(this,event,'addapprofrm','brkupWalMart_InvA.action', 'approveraddid','Report','Item Breakup','ITMID_L%{#cnt+1}','QTY_L%{#cnt+1}');" onkeydown="return tabE_Lst6(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QTY_L%{#st1.index+1}" name="QTY_L" value="%{QTY_L}" cssStyle="width:70px;text-align:right; " theme="simple"  cssClass="textreadonly" readonly="true" onblur="totqnty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="NETWGHT_L%{#st1.index+1}" name="NETWGHT_L" value="%{NETWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pcknetQty();" onkeydown="return tabE_Lst7(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="GROSSWGHT_L%{#st1.index+1}" name="GROSSWGHT_L" value="%{GROSSWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckGrossQty();" onkeydown="return tabE_Lst8(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_L_L%{#st1.index+1}" name="BOX_L_L" value="%{BOX_L_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst9(this,event,'%{#st1.index+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_W_L%{#st1.index+1}" name="BOX_W_L" value="%{BOX_W_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst10(this,event,'%{#st1.index+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_H_L%{#st1.index+1}" name="BOX_H_L" value="%{BOX_H_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst11(this,event,'%{#st1.index+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_VOL_L%{#st1.index+1}" name="BOX_VOL_L" value="%{BOX_VOL_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst12(this,event,'%{#st1.index+1}');" cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_NETWGHT_L%{#st1.index+1}" name="TOT_NETWGHT_L" value="%{TOT_NETWGHT_L}" cssStyle="width:80px;text-align:right " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_GROSSWGHT_L%{#st1.index+1}" name="TOT_GROSSWGHT_L" value="%{TOT_GROSSWGHT_L}" cssStyle="width:80px;text-align:right " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            </tr>
                                                                        </s:iterator> 
                                                             </s:else>
                                                     </s:if>
                                                     <s:else>
                                                         <s:iterator begin="cnt" end="30" status="st1">
                                                                         <tr bgcolor="#f7f7f7">
                                                                              <s:hidden id="SRN1" name="SRN1" value="%{#st1.index+1}"/>
                                                            <td style="font-size:8pt;"><s:textfield id="ITMID_L%{#st1.index+1}" name="ITMID_L" value="%{ITMID_L}" cssStyle="width:120px;text-align:left;text-transform:uppercase;" theme="simple" onkeydown="return tabE_Lst1(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ITMIDDESC_L%{#st1.index+1}" name="ITMIDDESC_L" value="%{ITMIDDESC_L}" cssStyle="width:240px;text-align:left" theme="simple" onkeydown="return tabE_Lst2(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKGES_L%{#st1.index+1}" name="PCKGES_L" value="%{PCKGES_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="totpckgs();pcknetQty();pckratioQty();pckGrossQty();" onkeydown="return tabE_Lst3(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATIO_L%{#st1.index+1}" name="RATIO_L" value="%{RATIO_L}" cssStyle="width:70px;text-align:left" theme="simple" onkeydown="return tabE_Lst4(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="PCKRATIO_L%{#st1.index+1}" name="PCKRATIO_L" value="%{PCKRATIO_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckratioQty();" onkeydown="return tabE_Lst5(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATE_L%{#st1.index+1}" name="RATE_L" value="%{RATE_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeypress="return tabEs(this,event,'addapprofrm','brkupWalMart_InvA.action', 'approveraddid','Report','Item Breakup','ITMID_L%{#cnt+1}','QTY_L%{#cnt+1}');" onkeydown="return tabE_Lst6(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QTY_L%{#st1.index+1}" name="QTY_L" value="%{QTY_L}" cssStyle="width:70px;text-align:right " theme="simple" readonly="true"  cssClass="textreadonly" onblur="totqnty();" /></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="NETWGHT_L%{#st1.index+1}" name="NETWGHT_L" value="%{NETWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pcknetQty();" onkeydown="return tabE_Lst7(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="GROSSWGHT_L%{#st1.index+1}" name="GROSSWGHT_L" value="%{GROSSWGHT_L}" cssStyle="width:70px;text-align:right" theme="simple" onblur="pckGrossQty();" onkeydown="return tabE_Lst8(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_L_L%{#st1.index+1}" name="BOX_L_L" value="%{BOX_L_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst9(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_W_L%{#st1.index+1}" name="BOX_W_L" value="%{BOX_W_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst10(this,event,'%{#st1.index+1}');"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_H_L%{#st1.index+1}" name="BOX_H_L" value="%{BOX_H_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst11(this,event,'%{#st1.index+1}');" onblur="volQty();"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="BOX_VOL_L%{#st1.index+1}" name="BOX_VOL_L" value="%{BOX_VOL_L}" cssStyle="width:70px;text-align:right" theme="simple" onkeydown="return tabE_Lst12(this,event,'%{#st1.index+1}');" cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_NETWGHT_L%{#st1.index+1}" name="TOT_NETWGHT_L" value="%{TOT_NETWGHT_L}" cssStyle="width:80px;text-align:right " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="TOT_GROSSWGHT_L%{#st1.index+1}" name="TOT_GROSSWGHT_L" value="%{TOT_GROSSWGHT_L}" cssStyle="width:80px;text-align:right " theme="simple"  cssClass="textreadonly" readonly="true"/></td>
                                                            </tr>
                                                        </s:iterator> 
                                                            </s:else>
                                                </tbody>
                                            </table>
                                        </div>  
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table bgcolor="#B1CCD9" width="100%">
                                        <tr>
                                            <td style="width:275pt"></td>
                                            <td style="font-size:8pt;font-weight:bold;">
                                               <s:textfield id="TOTPACKG" name="TOTPACKG" value="%{TOT_PACKG}" cssStyle="width:70px;text-align:right"   cssClass="textreadonly" theme="simple" readonly="true"/> 
                                            </td>
                                            <td style="width:130pt"></td>
                                            <td>
                                                <s:textfield id="TOTQTY" name="TOTQTY" value="%{TOT_QTY}" cssStyle="width:70px;text-align:right "  cssClass="textreadonly" theme="simple" readonly="true"/> 
                                            </td>
                                            <td style="width:340pt"></td>
                                            <td>
                                                <s:textfield id="TOTVALNET" name="TOTVALNET" value="%{TOT_CTN}" cssStyle="width:80px;text-align:right "  cssClass="textreadonly" theme="simple" readonly="true"/> 
                                            </td>
                                            
                                            <td style="width:0pt"></td>
                                            <td>
                                                <s:textfield id="TOTVALGROSS" name="TOTVALGROSS" value="%{TOT_QNTY}" cssStyle="width:80px;text-align:right "  cssClass="textreadonly" theme="simple" readonly="true"/> 
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                            <td>
                                    <div  style="width:525px; overflow:auto; height:80pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="left" bgcolor="#646D7E" cellspacing="0" cellpadding="0" width="500px">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);">
                                                       <th style="font-size:8pt;width:100px;" align="left">Material&nbsp;Break&nbsp;Down</th>
                                                  </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                                      <s:if test="%{INVOICE_S!=null}">
                                                          <s:if test="%{MTRIALBRKUPLIST.size()>0}">
                                                              <s:set id="cnt" name="cnt" value="%{0}"/>
                                                        <s:iterator value="MTRIALBRKUPLIST" status="st1">
                                                         <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:6pt;text-align: left;"><s:textfield id="MTRIALBRKUP%{#cnt+1}" name="MTRIALBRKUP" value="%{MATRIALBRKUP}" cssStyle="width:500px;text-align:left" theme="simple" onkeydown="return tabE_Brk(this,event,'%{#cnt+1}');"/></td>
                                                          <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>
                                                         </tr>
                                                       </s:iterator>
                                                         <s:iterator begin="cnt" end="50" status="st1">
                                                            <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:6pt;text-align: left;"><s:textfield id="MTRIALBRKUP%{#cnt+1}" name="MTRIALBRKUP" value="%{MATRIALBRKUP}" cssStyle="width:500px;text-align:left" theme="simple" onkeydown="return tabE_Brk(this,event,'%{#cnt+1}');"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                            </tr>
                                                       </s:iterator>
                                                          </s:if>
                                                            <s:else>
                                                                            <s:iterator begin="cnt" end="50" status="st1">
                                                                             <tr bgcolor="#f7f7f7">
                                                                             <td style="font-size:6pt;text-align: left;"><s:textfield id="MTRIALBRKUP%{#st1.index+1}" name="MTRIALBRKUP" value="%{MATRIALBRKUP}" cssStyle="width:500px;text-align:left" theme="simple" onkeydown="return tabE_Brk(this,event,'%{#st1.index+1}');"/></td>
                                                                              </tr>
                                                                        </s:iterator> 
                                                             </s:else>
                                                          </s:if>
                                                                              <s:else>
                                                         <s:iterator begin="cnt" end="50" status="st1">
                                                                        <tr bgcolor="#f7f7f7">
                                                                            <td style="font-size:6pt;text-align: left;"><s:textfield id="MTRIALBRKUP%{#st1.index+1}" name="MTRIALBRKUP" value="%{MATRIALBRKUP}" cssStyle="width:500px;text-align:left" theme="simple" onkeydown="return tabE_Brk(this,event,'%{#st1.index+1}');"/></td>
                                                                           </tr>
                                                                      </s:iterator> 
                                                            </s:else>
                                                </tbody>
                                            </table>
                                        </div> 
                            </td> 
                            </tr>  
                               
                               
                         
                            </td>
                                 </tr>
                                 <tr>
                                     <td height="1pt"  align="" style="color:red;font-weight:bold">
                                <div style="height:5pt">
                                <s:actionerror />
                                <s:fielderror />
                                <s:actionmessage />
                               </div>
                              </td>
                                 </tr>
                                </TABLE>
                    

<div id="approveraddid" class="root1" style="right:50px; top:100px;display:none">
    <table cellpadding="0" cellspacing="0">
        <tr bgcolor="#000080">
            <td width="100%">
                <div id="Report" class="handle1" style="cursor: move">Search List</div>
            </td>
            <td style="10px"><a href="#" onclick="closediv('approveraddid')"><img border="0" width="18px" height="18px" src="css/image/divclose.gif"/></a>
            </td>
        </tr> 
        <tr>
            <td colspan="2">
                <iframe name="addapprofrm" id="addapprofrm" src="" scrolling="no" frameborder="0"  width="100%" height="280px"></iframe>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    var approveraddid1 = document.getElementById("approveraddid");
    var report = document.getElementById('Report');
    Drag.init(report, approveraddid1);
</script>


</form> 
</body>
</html>