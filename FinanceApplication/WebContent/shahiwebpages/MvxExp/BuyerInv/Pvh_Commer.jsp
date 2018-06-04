<%-- 
    Document   : Pvh_Commer
    Created on : Jan 09, 2017, 10:11:48 AM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="../js/dom-drag.js"></script>
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
                height:330px;
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

            function searchdetail()
            {
                document.frm.action = "PvhCommercialA.action?FLAG1=Yes";
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
            
            function totqnty()
            {
                 TOTQTY=document.frm.TOTQTY;
                 QNTY=document.frm.QNTY;
                
                    if(QNTY.length>0){
                    for (var i = 0; i < QNTY.length; i++) {
                        alert("guddu"+eval(QNTY[i].value));
                        if(QNTY[i].value>0){
                         TOTQTY+= eval(QNTY[i].value);
                        } 
                        else{
                           
                        }
                    }
                    
                    }
                    else{
                        if(QNTY.value>0){
                          TOTQTY+= eval(QNTY.value);
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


            function saverec()
            {
                document.frm.action = "update1PvhCommercialA.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
            }

            function printrecpvh()
            {
                
                document.frm.action = "printsPvhCommercialA.action?REPORTTYPE=PDF";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
               
            }
            function printrecizodckj()
            {
               
                document.frm.action = "printsIZOD_CKJPvhCommercialA.action?REPORTTYPE=PDF";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
                
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
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >

            <s:hidden id="flagVAL" name="flagVAL" value="%{flag4}"/>
            <s:hidden id="pchlist" name="pchlist" value="%{pchlist}"/>
            <s:hidden id="CNTRY" name="CNTRY" value="%{CNTRY}"/>


            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="##231819">
                            <tr>
                                <th align="center" style="font-size:14.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:#231819;">
                                    PVH Commercial</th>
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
                                                <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PvhCommercialA.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                  
                                            </td>
                                        </tr>

                                    </table>
                                </td>
                            </tr>
                        </table> 
                    </td>
                </tr>
                <tr><td>
                        <table bgcolor="#f7f7f7" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                            <tr>
                                <th align="left">Inv No</th>
                                <th align="left">Plan No</th>
                                <th align="left">Inv date</th>
                                <th align="left">Buyer Code</th>
                                <th align="left">Buyer Name</th>
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
                                <td style="font-size:8pt"><s:property value="FIN_DATE"/></td>
                                <td style="font-size:8pt"><s:property value="INV_QTY"/></td>
                                <td style="font-size:8pt"><s:property value="SHIP_QTY"/></td>
                            </tr> 
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>
                        <table bgcolor="#f7f7f7" align="center" width="100%" cellspacing="1" cellpadding="1">
                            <tr>
                                <td style="font-size:8pt;font-weight:bold">Shipment&nbsp;Authorization</td>
                                <td>
                                   <s:textfield id="SHIPAUTH" name="SHIPAUTH" value="%{SHIPAUTH}" maxlength="25"  theme="simple"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Quality</td>
                                <td>
                                   <s:textfield id="QUALTY" name="QUALTY" value="%{QUALTY}" maxlength="25"  theme="simple" />
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">PM&nbsp;No.</td>
                                <td>
                                   <s:textfield id="PMNO" name="PMNO" value="%{PMNO}" maxlength="25"  theme="simple"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Style&nbsp;Name</td>
                                <td>
                                  <s:textfield id="STYLENM" name="STYLENM" value="%{STYLENM}" maxlength="150"  theme="simple"/>
                                </td>
                               
                            </TR>
                             <tr>
                                  <td style="font-size:8pt;font-weight:bold">TH&nbsp;Division</td>
                                <td>
                                   <s:textfield id="THDIVI" name="THDIVI" value="%{THDIVI}" maxlength="10"  theme="simple"/>
                                </td> 
                                 <td style="font-size:8pt;font-weight:bold">Gross&nbsp;Weight</td>
                                <td>
                                  <s:textfield id="GROSSWT" name="GROSSWT" value="%{GROSSWT}" theme="simple" onblur="validatenumber(this);"/>
                                </td>
                                  <td style="font-size:8pt;font-weight:bold">Net&nbsp;Net&nbsp;Weight</td>
                                <td>
                                   <s:textfield id="NETNETWT" name="NETNETWT" value="%{NETNETWT}" theme="simple" onblur="validatenumber(this);"/>
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">Manuf&nbsp;Factory</td>
                                <td>
                                   <s:textfield id="MNUFFACT" name="MNUFFACT" value="%{MNUFFACT}" theme="simple" maxlength="10" readonly="true"/><a href="searchcodedescPvhCommercialA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                    
                                </td>
                               
                            </TR>
                             <tr>
                                  <td style="font-size:8pt;font-weight:bold">Season</td>
                                <td>
                                   <s:textfield id="SEASON" name="SEASON" value="%{SEASON}" maxlength="50"  theme="simple"/>
                                </td> 
                                  <td style="font-size:8pt;font-weight:bold">Division</td>
                                <td>
                                   <s:textfield id="DIVIS" name="DIVIS" value="%{DIVIS}"  maxlength="50" theme="simple"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Fabric&nbsp;Type</td>
                                <td>
                                   <s:textfield id="FABTYP" name="FABTYP" value="%{FABTYP}" maxlength="10" theme="simple" />
                                </td> 
                                  <td style="font-size:8pt;font-weight:bold">Licence&nbsp;Code</td>
                                <td>
                                  <s:textfield id="LICCODE" name="LICCODE" value="%{LICCODE}" maxlength="10" theme="simple" />
                                </td>
                            </TR>
                            <tr> 
                                <td style="font-size:8pt;font-weight:bold">Carge&nbsp;Hanover&nbsp;Date</td>
                                <td>
                                    <sx:datetimepicker id="CARGEDATE" name="CARGEDATE" value="%{CARGEDATE}" displayFormat="dd-MMM-yyyy"/>
                                </td> 
                              <td style="font-size:8pt;font-weight:bold">Box&nbsp;Measurement</td>
                                <td>
                                   <s:textfield id="BOXMSRMNT" name="BOXMSRMNT" value="%{BOXMSRMNT}" maxlength="190" theme="simple"/>
                                </td>
                                
                               <td style="font-size:8pt;font-weight:bold">Notify 1</td>
                                <td>
                                   <s:textfield id="NOTIFY1" name="NOTIFY1" value="%{NOTIFY1}" theme="simple" maxlength="10" readonly="true"/><a href="searchcodedesc1PvhCommercialA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                   
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Notify 2</td>
                                <td>
                                   <s:textfield id="NOTIFY2" name="NOTIFY2" value="%{NOTIFY2}" theme="simple" maxlength="10" readonly="true"/><a href="searchcodedesc2PvhCommercialA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                   
                                </td>
                            </TR>
                            <tr>
                             <td style="font-size:8pt;font-weight:bold">Inv&nbsp;Type</td>
                                <td style="font-size:11px;font-weight: bold;">
                                    <s:radio id="INV_TPE" name="INV_TPE"  value="%{INV_TPE}" list="# {'1' : 'Retail', '2' : 'WholeSale'}" theme="simple"/>
                                </td>  
                                
                                <td style="font-size:8pt;font-weight:bold">Signature&nbsp;</td>
                                <td style="font-size:11px;font-weight: bold;">
                                    <s:radio id="SIGN_REQ" name="SIGN_REQ"  value="%{SIGN_REQ}" list="# {'0' : 'NO', '1' : 'YES'}" theme="simple"/>
                                </td>  
                                   
                                <td style="font-size:8pt;font-weight:bold">Rex&nbsp;Decl</td>
                                <td style="font-size:11px;font-weight: bold;">
                                    <s:radio id="REX_REQ" name="REX_REQ"  value="%{REX_REQ}" list="# {'0' : 'NO', '1' : 'YES'}" theme="simple"/>
                                </td>  
                                <td style="font-size:8pt;font-weight:bold">Delv.Pay&nbsp;Term</td>
                                  <td class="label-1"><s:select  name="DEL_TERM" theme="simple"  list="delTermList"  value="%{DEL_TERM}"  cssStyle="width:120pt;font-size:8pt" /></td>
                                  
                            </tr> 
                            <tr> 
                                <td align="center" colspan="12" >
                                    <s:if test="%{FIN_DATE==null}">  
                                      <button  id="saveheadId" class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                    </s:if><s:else>
                                      <button  id="saveheadId" disabled="true" class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                    </s:else>
                                    <button  id="saveheadId1"  class="sexybutton" onclick="printrecpvh();" ><span><span><span class="print" id="saveId" >PVH Inv</span></span></span></button> 
                                    <button  id="saveheadId"  class="sexybutton" onclick="printrecizodckj();" ><span><span><span class="print" id="saveId" >IZOD/CKJ Inv</span></span></span></button> 
                                   <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                </td>
                            </tr>
                                 <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                
                      <tr>
                        <td height="1pt"  align="center" style="color:red;font-weight:bold">
                            <div style="height:5pt">
                                <s:actionerror />
                                <s:fielderror />
                                <s:actionmessage />
                            </div>
                        </td>
                        </tr>  
                        
                        </table>
                    </TD>
                </TR>
                             <tr>
                                <td>
                                  <div  style="width:100%; overflow:auto; height:200pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#f7f7f7" cellspacing="0" cellpadding="0" width="100%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);">
                                                    <th style="font-size:8pt;" align="left">PO No.</th>
                                                    <th style="font-size:8pt;" align="left">Style No.</th>
                                                    <th style="font-size:8pt;" align="left">HTS Code</th>
                                                    <th style="font-size:8pt;" align="left">Color Code</th>
                                                    <th style="font-size:8pt;" align="left">Color Desc</th>
                                                    <th style="font-size:8pt;" align="left">Inv Desc</th>
                                                    <th style="font-size:8pt;" align="right">Ctn</th>
                                                    <th style="font-size:8pt;" align="right">Qnty</th>
                                                    <th style="font-size:8pt;" align="right">Currency</th>
                                                    <th style="font-size:8pt;" align="right">Inv Rate</th>
                                                    <th style="font-size:8pt;" align="right">Fob Value</th>
                                                    
                                                  </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                                      <s:if test="%{INVOICE_S!=null}">
                                                         <s:if test="%{PVHList.size()>0}">
                                                           <s:set id="cnt" name="cnt" value="%{0}"/>
                                                         <s:iterator value="PVHList" status="st1">
                                                         <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt+1}" name="PONO" value="%{BPONO}" cssStyle="text-transform:uppercase;width:100px;text-align:left" theme="simple"  maxlength="20"/> </td>
                                                            <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt+1}" name="STYLENO" value="%{BSTYLENO}" cssStyle="text-transform:uppercase;width:100px;text-align:left" theme="simple" maxlength="30"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="HTSCODE%{#cnt+1}" name="HTSCODE" value="%{BHTSCODE}" cssStyle="text-transform:uppercase;width:100px;text-align:left" theme="simple" maxlength="20" /></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORCODE%{#cnt+1}" name="COLORCODE" value="%{BCOLORCODE}" cssStyle="text-transform:uppercase;width:70px;text-align:left" theme="simple" maxlength="20" /></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORDESC%{#cnt+1}" name="COLORDESC" value="%{BCOLORDESC}" cssStyle="text-transform:uppercase;width:150px;text-align:left" theme="simple" maxlength="40"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt+1}" name="INVDESC" value="%{BINVDESC}" cssStyle="text-transform:uppercase;width:440px;text-align:left" theme="simple" maxlength="250"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CTN%{#cnt+1}" name="CTN" value="%{BCTN}" cssStyle="width:50px;text-align:right" theme="simple" /></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QNTY%{#cnt+1}" name="QNTY" value="%{BQNTY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CURRENCY%{#cnt+1}" name="CURRENCY" value="%{BCURRENCY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="INVRATE%{#cnt+1}" name="INVRATE" value="%{BINVRATE}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="FOB%{#cnt+1}" name="FOB" value="%{BFOB}" cssStyle="width:70px;text-align:right" cssClass="textreadonly"  readonly="true"  theme="simple"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                         </tr>
                                                          </s:iterator>
                                                         <s:iterator begin="cnt" end="9" status="st1">
                                                            <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt+1}" name="PONO" value="%{BPONO}" cssStyle="text-transform:uppercase;width:100px;text-align:left" theme="simple" maxlength="20"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt+1}" name="STYLENO" value="%{BSTYLENO}" cssStyle="text-transform:uppercase;width:100px;text-align:left" theme="simple" maxlength="30"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="HTSCODE%{#cnt+1}" name="HTSCODE" value="%{BHTSCODE}" cssStyle="text-transform:uppercase;width:100px;text-align:left" theme="simple" maxlength="20"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORCODE%{#cnt+1}" name="COLORCODE" value="%{BCOLORCODE}" cssStyle="text-transform:uppercase;width:70px;text-align:left" theme="simple" maxlength="20" /></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORDESC%{#cnt+1}" name="COLORDESC" value="%{BCOLORDESC}" cssStyle="text-transform:uppercase;width:150px;text-align:left" theme="simple" maxlength="40"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt+1}" name="INVDESC" value="%{BINVDESC}" cssStyle="text-transform:uppercase;width:440px;text-align:left" theme="simple" maxlength="250"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CTN%{#cnt+1}" name="CTN" value="%{BCTN}" cssStyle="width:50px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QNTY%{#cnt+1}" name="QNTY" value="%{BQNTY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CURRENCY%{#cnt+1}" name="CURRENCY" value="%{BCURRENCY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="INVRATE%{#cnt+1}" name="INVRATE" value="%{BINVRATE}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="FOB%{#cnt+1}" name="FOB" value="%{BFOB}" cssStyle="width:70px;text-align:right" cssClass="textreadonly"  readonly="true" theme="simple"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                            </tr>
                                                       </s:iterator> 
                                                                
                                                            </s:if>
                                                             <s:else>
                                                                            <s:iterator begin="0" end="9" status="st1">
                                                                            <tr bgcolor="#f7f7f7">
                                                                            <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt+1}" name="PONO" value="%{BPONO}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt+1}" name="STYLENO" value="%{BSTYLENO}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="HTSCODE%{#cnt+1}" name="HTSCODE" value="%{BHTSCODE}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORCODE%{#cnt+1}" name="COLORCODE" value="%{BCOLORCODE}" cssStyle="width:70px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORDESC%{#cnt+1}" name="COLORDESC" value="%{BCOLORDESC}" cssStyle="width:150px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt+1}" name="INVDESC" value="%{BINVDESC}" cssStyle="width:440px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CTN%{#cnt+1}" name="CTN" value="%{BCTN}" cssStyle="width:50px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QNTY%{#cnt+1}" name="QNTY" value="%{BQNTY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CURRENCY%{#cnt+1}" name="CURRENCY" value="%{BCURRENCY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="INVRATE%{#cnt+1}" name="INVRATE" value="%{BINVRATE}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="FOB%{#cnt+1}" name="FOB" value="%{BFOB}" cssStyle="width:70px;text-align:right" cssClass="textreadonly"  readonly="true" theme="simple"/></td>
                                                            </tr>
                                                                        </s:iterator> 
                                                             </s:else>
                                                     </s:if>
                                                     <s:else>
                                                         <s:iterator begin="0" end="9" status="st1">
                                                                         <tr bgcolor="#f7f7f7">
                                                                           <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt+1}" name="PONO" value="%{BPONO}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt+1}" name="STYLENO" value="%{BSTYLENO}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="HTSCODE%{#cnt+1}" name="HTSCODE" value="%{BHTSCODE}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORCODE%{#cnt+1}" name="COLORCODE" value="%{BCOLORCODE}" cssStyle="width:70px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="COLORDESC%{#cnt+1}" name="COLORDESC" value="%{BCOLORDESC}" cssStyle="width:150px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt+1}" name="INVDESC" value="%{BINVDESC}" cssStyle="width:440px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CTN%{#cnt+1}" name="CTN" value="%{BCTN}" cssStyle="width:50px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="QNTY%{#cnt+1}" name="QNTY" value="%{BQNTY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="CURRENCY%{#cnt+1}" name="CURRENCY" value="%{BCURRENCY}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="INVRATE%{#cnt+1}" name="INVRATE" value="%{BINVRATE}" cssStyle="width:70px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right;"><s:textfield id="FOB%{#cnt+1}" name="FOB" value="%{BFOB}" cssStyle="width:70px;text-align:right" cssClass="textreadonly"  readonly="true" theme="simple"/></td>
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
                                    <table bgcolor="#f7f7f7" width="100%">
                                        <tr>
                                            <td style="width:1000pt"></td>
                                           
                                            <td style="font-size:8pt;font-weight:bold;">
                                                <s:textfield id="TOTCTN" name="TOTCTN" value="%{TOT_CTN}" cssStyle="width:50px;text-align:right" cssClass="textreadonly" readonly="true" theme="simple"/> 
                                            </td>
                                            <td>
                                                <s:textfield id="TOTQTY" name="TOTQTY" value="%{TOT_QNTY}" cssStyle="width:70px;text-align:right" cssClass="textreadonly" readonly="true"  theme="simple"/> 
                                            </td>
                                            <td style="width:130pt"></td>
                                            <td>
                                                           <s:set id="t_val" value="TOT_VAL"/>
							   <s:text name="product.cost" id="val_tot" >
                                                               <s:param name="value" value="%{#t_val}"/>
                                                           </s:text>
                                                           
                                                <s:textfield id="TOTVAL" name="TOTVAL" value="%{#val_tot}" cssStyle="width:70px;text-align:right" cssClass="textreadonly"  readonly="true" theme="simple"/> 
                                            </td>
                                            
                                        </tr> 
                                    </table>
                                </td>
                            </tr>
                              
                                </TABLE>
                    

<div id="approveraddid" class="root1" style="left:50px; top:100px;display:none">
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
                <iframe name="addapprofrm" id="addapprofrm" src="" scrolling="no" frameborder="0"  width="100%" height="300px"></iframe>
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