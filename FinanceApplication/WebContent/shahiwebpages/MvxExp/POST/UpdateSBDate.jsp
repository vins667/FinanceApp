<%-- 
    Document   : WalMart_Inv
    Created on : Feb 08, 2017, 12:21:48 PM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

 
<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="../js/dom-drag.js"></script>
<script type="text/javascript" src="script/jsDatePick.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="style/jsDatePick_ltr.min.css" />
<html> 
    <head>
        <s:head />
        <sj:head jqueryui="true"/>
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
                height:350px;
                width:700px;
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
            
            window.onload = function()
	  {
        new JsDatePick({ 
            useMode:2,
            target:"date1",
            dateFormat:"%m/%d/%Y",
            imgPath:"/img/"
        });
        new JsDatePick({
            useMode:2,
            target:"date2",
            dateFormat:"%d/%m/%Y",
            imgPath:"/img/"
        });
    }


            
            function searchdetail()
            {
                a=document.getElementById("SHIPBILDATE").value;
                b=document.getElementById("INVOICE_S").value;
                if(a!=="" && b!==""){
                document.frm.action = "UpdateSBDateA.action?FLAG1=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = ''; 
                }
                else{
                 alert('Please Enter Ship Bill No and Shiping Date');
                }
            }

            function openpop(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv(a)
            {
                document.getElementById(a).style.display = 'none';
            }

            function openpage(frame, url, a, id, b, fid, qid)
            {
//                alert("hi"+document.getElementById('INVOICE_S').value);
                document.getElementById(frame).src = url + "?ITMIDL=" + document.getElementById(fid).value + "&QTYID=" + document.getElementById(qid).value + "&invid=" + document.getElementById('INVOICE_S').value;
                document.getElementById(a).style.display = '';
                document.getElementById(id).innerHTML = b;

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
                    newentry();
                    return false;
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

//            function  validate(){
//                a=document.getElementById("SHIPBILNO").value;
//                c=document.getElementById("MODESHIP").value;
//                d=document.getElementById("CHACODE").value;
//                e=document.getElementById("FRWRDCODE").value;
//                f=document.getElementById("LODINGCODE").value;
//                if(a==""){
//                   alert("Ship Bill No. can't Blank ");
//                   return false;
//                }
//                if(c==""){
//                   alert("Mode of Ship can't Blank ");
//                   return false;
//                }
//                if(d==""){
//                   alert("Cha Code can't Blank ");
//                   return false;
//                }
//                if(e==""){
//                   alert("Fwd Code can't Blank ");
//                   return false;
//                }
//                if(f==""){
//                   alert("Loading Port can't Blank ");
//                   return false;
//                }
//                
//                return true;
//            }
            

            function saverec()
            {
                document.frm.action = "modUpdateSBDateA.action";
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
        <form action="" method="post" name="frm" >

            <s:hidden id="ACCESSTYPE" name="ACCESSTYPE" value="%{ACCESSTYPE}"/>
             
            <s:hidden id="aausrid" name="aausrid" value="%{aausrid}"/>



            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="##231819">
                            <tr>
                                <th align="center" style="font-size:14.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:#231819;">
                                    Update S/B Detail</th>
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
                                        <td style="font-size:8pt;font-weight:bold" >S/B No: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield name="INVOICE_S" id="INVOICE_S" cssStyle="text-transform:uppercase;width:80pt" readonly="true" cssClass="textreadonly" theme="simple" maxLength="10"/>
                                      <s:property value="%{ACCESSTYPE}"/>
                                      
                                            <a href="shipbillcodedescUpdateSBDateA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        
                                              <s:textfield name="SHIPBILDATE" id="SHIPBILDATE" cssStyle="text-transform:uppercase;width:60pt" readonly="true"  cssClass="textreadonly" theme="simple" maxLength="10"/>
                                              &nbsp;&nbsp; <button  id="searchheadId" class="sexybutton" onclick="searchdetail();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                            </td>
                                            <td align="right" >
                                                <s:if test="%{FLAG2.equals('YES')}">
                                                <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                                </s:if>
                                                <s:else>
                                                  <button  id="saveheadId"  disabled="true"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>  
                                                </s:else>
                                                  
                                                <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('UpdateSBDateA.action?aausrid=<s:property value="%{aausrid}"/>&ACCESSTYPE=<s:property value="%{ACCESSTYPE}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                                <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                            </td>
                                        </tr>

                                    </table>
                                </td>
                            </tr>
                        </table> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <table bgcolor="#f2f2f2" align="center" width="100%" cellspacing="0" cellpadding="0" border="0">
                           <s:if test="%{ACCESSTYPE=='SBNO'}">      
                            <tr>
                               
                                <td style="font-size:8pt;font-weight:bold">New S/B No</td>
                                <td>
                                    <s:textfield id="SHIPBILNO" name="SHIPBILNO" value="%{SHIPBILNO}" theme="simple" cssStyle="text-transform:uppercase;width:80pt;font-size:9pt;" tabindex="1" />
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Clearance&nbsp;Port</td>
                                <td>
                                    <s:textfield id="CLRANCODE" name="CLRANCODE" value="%{CLRANCODE}"  theme="simple" readonly="true"  cssClass="textreadonly"  cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;"/>
                                    <a href="searchcodedesc3UpdateSBDateA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                        
                                    <s:textfield id="CLRANCEPORT" name="CLRANCEPORT" readonly="true" value="%{CLRANCEPORT}"  theme="simple" tabindex="3"  cssStyle="text-transform:uppercase;width:180pt;font-size:9pt;"/>
                                  </td> 
                                <td style="font-size:8pt;font-weight:bold">Loading&nbsp;Port</td>
                                <td>
                                    <s:textfield id="LODINGCODE" name="LODINGCODE" value="%{LODINGCODE}"  theme="simple" readonly="true"  cssClass="textreadonly"  cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;"/>
                                    <a href="searchcodedesc1UpdateSBDateA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                              
                                    <s:textfield id="LODINGPORT" name="LODINGPORT" readonly="true" value="%{LODINGPORT}"  theme="simple" tabindex="5" cssStyle="text-transform:uppercase;width:180pt;font-size:9pt;"/>
                                   </td> 
                            
                            </tr>
                              
                            <tr>
                                <td style="font-size:8pt;font-weight:bold"></td>
                                <td>
                                 </td>
                                <td style="font-size:8pt;font-weight:bold">CHA</td>
                                <td>
                                    <s:textfield id="CHACODE" name="CHACODE" value="%{CHACODE}"  theme="simple" readonly="true"  cssClass="textreadonly"  cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;"/>
                                      <a href="searchcodedescUpdateSBDateA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                           
                                    <s:textfield id="CHA" name="CHA" value="%{CHA}" theme="simple" readonly="true"  cssStyle="text-transform:uppercase;width:180pt;font-size:9pt;" tabindex="4"/>
                                 </td>
                                <td style="font-size:8pt;font-weight:bold">Forward</td>
                                <td>
                                    <s:textfield id="FRWRDCODE" name="FRWRDCODE" value="%{FRWRDCODE}"  theme="simple" readonly="true"  cssClass="textreadonly"  cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;"/>
                                    <a href="searchcodedesc2UpdateSBDateA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                    <s:textfield id="FRWRD" name="FRWRD" value="%{FRWRD}" theme="simple" readonly="true"   cssStyle="text-transform:uppercase;width:180pt;font-size:9pt;" tabindex="4"/>
                                </td>
                            </tr>
                            </s:if>
                               <s:else>
                                   <tr>
                                       
                                           <td style="font-size:8pt;font-weight:bold" >New S/B Date:  
                                         
                                            <sj:datepicker name="NEWSB_DATE" id="NEWSB_DATE" cssStyle="width:110px;"  value="%{NEWSB_DATE}" displayFormat="dd-M-yy" buttonImageOnly="true"/>
                                           </td>
                                 
                                   </tr>
                                  </s:else>
                            <tr style="height:15pt;">
                                <td colspan="5"></td>
                            </tr>


                        </table>
                    </td>
                </tr>
                <tr><td>
                        <table bgcolor="#f7f7f7" style="margin-top:0pt; height:15px" align="center" width="100%" cellpadding="1" cellspacing="1"  >
                            <tr>
                                <th align="left">Claim Date</th>
                                <th align="left">Port</th>
                                <th align="left">Admisable</th>
                                <th align="left">Received</th>
                                <th align="left">Supl Recvd.</th>
                                <th align="left">Adjusted</th>
                                <th align="left">Misc Amt</th>
                                <th align="left">Dbk W/Off</th>
                                <th align="left">STR Due</th>
                                <th align="left">STR Recv</th>
                                <th align="left">W/Off</th>
                            </tr>
                            <tr bgcolor="#F9F5A6"  style="margin-top:1pt; height:15pt">
                                <td style="font-size:8pt;font-weight:bold"><s:property value="CLMDATE"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="PORT1"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="ADMISABLE"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="RECECD"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="SUPPLYRCVD"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="ADJSTD"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="MISCAMT"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="DBKWOFF"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="STRDUE"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="STRRECV"/></td>
                                <td style="font-size:8pt;font-weight:bold"><s:property value="WOFF"/></td>
                            </tr> 
                        </TABLE>
                    </td>
                </tr>
                <tr>
                <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="1" cellspacing="1" >
                    <tr>
                        <td valign="top" >
                            <div  style="width:100%; overflow:auto; height:135pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                <table border="0" align="center" bgcolor="#8e927e" cellspacing="0" cellpadding="0" width="100%"  id="tablea">
                                    <thead>
                                    <th style="font-size:8pt" align="left">Invoice#</th>
                                    <th style="font-size:8pt" align="left">Crncy</th>
                                    <th style="font-size:8pt" align="left">Fob Amt</th>
                                    <th style="font-size:8pt" align="left">Qnty</th>
                                    <th style="font-size:8pt" align="left">INR Conv</th>
                                    <th style="font-size:8pt" align="left">Dbk Conv</th>
                                    <th style="font-size:8pt" align="left">LetExp Date</th>
                                    <th style="font-size:8pt" align="left">Awb Date</th>
                                    <th style="font-size:8pt" align="left">Fin Date</th>
                                    <th style="font-size:8pt" align="left">Mode</th>
                                    <th style="font-size:8pt" align="left" >CHA</th>
                                    <th style="font-size:8pt" align="left">Fwd</th>
                                    <th style="font-size:8pt" align="left">Clr Port</th>
                                    <th style="font-size:8pt" align="left">Load Port</th>
                                    </thead>                                                
                                    <tbody> 
                                        <s:if test="%{INVOICE_S!=null}">
                                          <s:if test="%{updatedatalist.size()>0}">
                                            <s:set id="cnt" name="cnt" value="%{0}" />
                                        <s:iterator value="updatedatalist" status="st1">
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt"><s:textfield id="INV_L%{#cnt+1}" name="INV_L" value="%{BINV_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CRNCY_L%{#cnt+1}" name="CRNCY_L" value="%{BCRNCY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FOBAMT_L%{#cnt+1}" name="FOBAMT_L" value="%{BFOBAMT_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="QNTY_L%{#cnt+1}" name="QNTY_L" value="%{BQNTY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                               <td style="font-size:8pt"><s:textfield id="INVCONV_L%{#cnt+1}" name="INVCONV_L" value="%{BINVCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="DBKCONV_L%{#cnt+1}" name="DBKCONV_L" value="%{BDBKCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                               
                                                <td style="font-size:8pt"><s:textfield id="LEEDATE_L%{#cnt+1}" name="LEEDATE_L" value="%{BLEEDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="AWBDATE_L%{#cnt+1}" name="AWBDATE_L" value="%{BAWBDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FINDATE_L%{#cnt+1}" name="FINDATE_L" value="%{FINDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                 <td style="font-size:8pt"><s:textfield id="MODE_L%{#cnt+1}" name="MODE_L" value="%{BMODE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CHA_L%{#cnt+1}" name="CHA_L" value="%{BCHA_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FWD_L%{#cnt+1}" name="FWD_L" value="%{BFWD_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CLRPORT_L%{#cnt+1}" name="CLRPORT_L" value="%{BCLRPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="LOADPORT_L%{#cnt+1}" name="LOADPORT_L" value="%{BLOADPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>

                                                <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>  
                                            </tr>
                                        </s:iterator>
                                            <s:iterator begin="cnt" end="10" status="st1">
                                            <tr bgcolor="#f2f9fb">
                                                 <td style="font-size:8pt"><s:textfield id="INV_L%{#cnt+1}" name="INV_L" value="%{INV_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CRNCY_L%{#cnt+1}" name="CRNCY_L" value="%{CRNCY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true" /></td>
                                                <td style="font-size:8pt"><s:textfield id="FOBAMT_L%{#cnt+1}" name="FOBAMT_L" value="%{FOBAMT_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true" /></td>
                                                <td style="font-size:8pt"><s:textfield id="QNTY_L%{#cnt+1}" name="QNTY_L" value="%{QNTY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true" /></td>
                                                 <td style="font-size:8pt"><s:textfield id="INVCONV_L%{#cnt+1}" name="INVCONV_L" value="%{INVCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="DBKCONV_L%{#cnt+1}" name="DBKCONV_L" value="%{DBKCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="LEEDATE_L%{#cnt+1}" name="LEEDATE_L" value="%{LEEDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="AWBDATE_L%{#cnt+1}" name="AWBDATE_L" value="%{AWBDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FINDATE_L%{#cnt+1}" name="FINDATE_L" value="%{FINDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="MODE_L%{#cnt+1}" name="MODE_L" value="%{MODE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CHA_L%{#cnt+1}" name="CHA_L" value="%{CHA_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FWD_L%{#cnt+1}" name="FWD_L" value="%{FWD_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CLRPORT_L%{#cnt+1}" name="CLRPORT_L" value="%{CLRPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="LOADPORT_L%{#cnt+1}" name="LOADPORT_L" value="%{LOADPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>

                                                <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>  
                                            </tr>
                                        </s:iterator>
                                         </s:if> 
                                        <s:else>
                                            <s:iterator begin="0" end="5" status="st1">
                                            <tr bgcolor="#f2f9fb">
                                                 <td style="font-size:8pt"><s:textfield id="INV_L%{#cnt+1}" name="INV_L" value="%{INV_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CRNCY_L%{#cnt+1}" name="CRNCY_L" value="%{CRNCY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FOBAMT_L%{#cnt+1}" name="FOBAMT_L" value="%{FOBAMT_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="QNTY_L%{#cnt+1}" name="QNTY_L" value="%{QNTY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                 <td style="font-size:8pt"><s:textfield id="INVCONV_L%{#cnt+1}" name="INVCONV_L" value="%{INVCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="DBKCONV_L%{#cnt+1}" name="DBKCONV_L" value="%{DBKCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="LEEDATE_L%{#cnt+1}" name="LEEDATE_L" value="%{LEEDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="AWBDATE_L%{#cnt+1}" name="AWBDATE_L" value="%{AWBDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FINDATE_L%{#cnt+1}" name="FINDATE_L" value="%{FINDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="MODE_L%{#cnt+1}" name="MODE_L" value="%{MODE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CHA_L%{#cnt+1}" name="CHA_L" value="%{CHA_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FWD_L%{#cnt+1}" name="FWD_L" value="%{FWD_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CLRPORT_L%{#cnt+1}" name="CLRPORT_L" value="%{CLRPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="LOADPORT_L%{#cnt+1}" name="LOADPORT_L" value="%{LOADPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>

                                            </tr>
                                        </s:iterator>
                                        </s:else>
                                      </s:if> 
                                        <s:else>
                                            <s:iterator begin="0" end="5" status="st1">
                                            <tr bgcolor="#f2f9fb">
                                                 <td style="font-size:8pt"><s:textfield id="INV_L%{#cnt+1}" name="INV_L" value="%{INV_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CRNCY_L%{#cnt+1}" name="CRNCY_L" value="%{CRNCY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FOBAMT_L%{#cnt+1}" name="FOBAMT_L" value="%{FOBAMT_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="QNTY_L%{#cnt+1}" name="QNTY_L" value="%{QNTY_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                 <td style="font-size:8pt"><s:textfield id="INVCONV_L%{#cnt+1}" name="INVCONV_L" value="%{INVCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="DBKCONV_L%{#cnt+1}" name="DBKCONV_L" value="%{DBKCONV_L}" cssStyle="width:87px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="LEEDATE_L%{#cnt+1}" name="LEEDATE_L" value="%{LEEDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="AWBDATE_L%{#cnt+1}" name="AWBDATE_L" value="%{AWBDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FINDATE_L%{#cnt+1}" name="FINDATE_L" value="%{FINDATE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="MODE_L%{#cnt+1}" name="MODE_L" value="%{MODE_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CHA_L%{#cnt+1}" name="CHA_L" value="%{CHA_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="FWD_L%{#cnt+1}" name="FWD_L" value="%{FWD_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="CLRPORT_L%{#cnt+1}" name="CLRPORT_L" value="%{CLRPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                <td style="font-size:8pt"><s:textfield id="LOADPORT_L%{#cnt+1}" name="LOADPORT_L" value="%{LOADPORT_L}" cssStyle="width:100px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>

                                            </tr>
                                        </s:iterator>
                                        </s:else>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
                </tr>
                <tr>
                    <td>
                        <table bgcolor="#f2f2f2" cellspacing="0" cellpadding="0" width="70%">
                            <tr>
                                <th align="left">Updation History </th>
                                
                                 <th align="left" >Old Value </th>
                                   <th align="right">New Value </th>
                            </tr>
                        </table>
                    </td> 
                    <td>
                        <div  style="width:100%; overflow:auto; height:150pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                            <table id="mytableid" border="0" align="center" bgcolor="#8e927e" cellspacing="0" cellpadding="0" width="100%">
                                <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);">
                                    <th style="font-size:8pt;" align="left">Inv #</th>
                                    <th style="font-size:8pt;" align="left">S/B No.</th>
                                    <th style="font-size:8pt;" align="left">S/B Date</th>
                                    <th style="font-size:8pt;" align="left">Clr Port</th>
                                    <th style="font-size:8pt;" align="left">Loading Port</th>
                                    <th style="font-size:8pt;" align="left">Cha</th>
                                    <th style="font-size:8pt;" align="left">Fwd</th>
                                    <th style="font-size:8pt;" align="left">Mode</th>
                                    <th style="font-size:8pt;" align="left">User</th>
                                    <th style="font-size:8pt;" align="left">Date</th>
                                    <th style="font-size:8pt;" align="left">S/B No.</th>
                                    <th style="font-size:8pt;" align="left">S/B Date</th>
                                    <th style="font-size:8pt;" align="left">Clr Port</th>
                                    <th style="font-size:8pt;" align="left">Loading Port</th>
                                    <th style="font-size:8pt;" align="left">Cha</th>
                                    <th style="font-size:8pt;" align="left">Fwd</th>
                                    <th style="font-size:8pt;" align="left">Mode</th>

                                </tr>                                                
                                <tbody> 
                                    <s:if test="%{INVOICE_S!=null}">
                                        <s:if test="%{updatehistoryList.size()>0}">
                                            <s:set id="cnt" name="cnt" value="%{0}"/>
                                            <s:iterator value="updatehistoryList" status="st1">
                                                <tr bgcolor="#f7f7f7">
                                                    <td style="font-size:8pt;"><s:textfield id="H_INVNO_L%{#cnt+1}" name="H_INVNO_L" value="%{BH_INVNO_L}" cssStyle="width:80px;text-align:left;text-transform:uppercase;" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="H_SBNO_L%{#cnt+1}" name="H_SBNO_L" value="%{BH_SBNO_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="H_SBDTL%{#cnt+1}" name="H_SBDT_L" value="%{OLD_SBDATE}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="H_PORT_L%{#cnt+1}" name="H_PORT_L" value="%{BH_PORT_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true" /></td>
                                                     <td style="font-size:8pt;"><s:textfield id="H_PORT_LOAD_L%{#cnt+1}" name="H_PORT_LOAD_L" value="%{BH_PORT_LOAD_L}" cssStyle="width:100px;text-align:left" theme="simple" readonly="true" /></td>
                                                     <td style="font-size:8pt;"><s:textfield id="H_CHA_L%{#cnt+1}" name="H_CHA_L" value="%{BH_CHA_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="H_FWD_L%{#cnt+1}" name="H_FWD_L" value="%{BH_FWD_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"  /></td>
                                                    <td style="font-size:8pt;"><s:textfield id="H_MODE_L%{#cnt+1}" name="H_MODE_L" value="%{BH_MODE_L}" cssStyle="width:50px;text-align:left" theme="simple" readonly="true" /></td>
                                                  
                                                    <td style="font-size:8pt;"><s:textfield id="H_USER_L%{#cnt+1}" name="H_USER_L" value="%{BH_USER_L}" cssStyle="width:70px;text-align:left; " theme="simple" readonly="true"  cssClass="textreadonly" /></td>
                                                    <td style="font-size:8pt;"><s:textfield id="H_DATE_L%{#cnt+1}" name="H_DATE_L" value="%{BH_DATE_L}" cssStyle="width:100px;text-align:left"  theme="simple" readonly="true" cssClass="textreadonly" /></td>
                                                    
                                                    <td style="font-size:8pt;"><s:textfield id="HH_SBNO_L%{#cnt+1}" name="HH_SBNO_L" value="%{BHH_SBNO_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="HH_SBDT_L%{#cnt+1}" name="HH_SBDT_L" value="%{NEW_SBDATE}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="HH_PORT_L%{#cnt+1}" name="HH_PORT_L" value="%{BHH_PORT_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="HH_PORT_LOAD_L%{#cnt+1}" name="HH_PORT_LOAD_L" value="%{BHH_PORT_LOAD_L}" cssStyle="width:100px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="HH_CHA_L%{#cnt+1}" name="HH_CHA_L" value="%{BHH_CHA_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="HH_FWD_L%{#cnt+1}" name="HH_FWD_L" value="%{BHH_FWD_L}" cssStyle="width:80px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <td style="font-size:8pt;"><s:textfield id="HH_MODE_L%{#cnt+1}" name="HH_MODE_L" value="%{BHH_MODE_L}" cssStyle="width:50px;text-align:left" theme="simple" readonly="true"/></td>
                                                    <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                </tr>
                                            </s:iterator>
                                           
                                        </s:if>
                                       
                                    </s:if>
                                    
                                </tbody>
                            </table>
                        </div>  
                    </td>
                </tr>
                </td>
                </tr>
                <tr>
                    <td height="1pt"  align="center" style="color:red;font-weight:bold">
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
                            <iframe name="addapprofrm" id="addapprofrm" src="" scrolling="no" frameborder="0"  width="100%" height="350px"></iframe>
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