<%-- 
    Document   : KohlsStore
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
                document.frm.action = "KohlsStoreA.action?FLAG1=Yes";
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
            
            function valtot() //TOTVAL
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
            
            function totqnty()
            {
                 TOTQTY=document.frm.TOTQTY;
                 QNTY1=document.frm.QNTY;
                    if(QNTY1.length>0){
                    for (var i = 0; i < QNTY1.length; i++) {
                        //alert("guddu kumar"+eval(QNTY1[i].value));
                        if(eval(QNTY1[i].value)>0){
                           TOTQTY+= eval(QNTY1[i].value);  
                        }
                        else{
                           
                        }
                    }
                    }
                    else{
                        if(QNTY1.value>0){
                          TOTQTY+= eval(QNTY1.value);
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
                document.frm.action = "update1KohlsStoreA.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
            }

            function printrec()
            {
                document.frm.action = "printsKohlsStoreA.action?REPORTTYPE=PDF";
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
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >

            <s:hidden id="FINAL_P" name="FINAL_P" value="%{FINAL_P}"/>
            <s:hidden id="pchlist" name="pchlist" value="%{pchlist}"/>
            <s:hidden id="" name="" value=""/>


            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="##231819">
                            <tr>
                                <th align="center" style="font-size:14.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:#231819;">
                                    Kohl's Store</th>
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
                                        </tr>

                                    </table>
                                </td>
                            </tr>
                        </table> 
                    </td>
                </tr>
                <tr><td>
                        <table bgcolor="#f7f7f7" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                            <tr>
                                <th align="left">Inv No</th>
                                <th align="left">Plan No</th>
                                <th align="left">Inv date</th>
                                <th align="left">Buyer Code</th>
                                <th align="left">Buyer Name</th>
                                <th align="left">TTO Date</th>
                                <th align="left">TO Date</th>
                                <th align="left">77 Date</th>
                                <th align="left">Inv Qty</th>
                                <th align="left">Ship Qty</th>
                            </tr>
                            <tr bgcolor="#c4c2c2">
                                <td style="font-size:8pt"><s:property value="INVOICENO"/></td>
                                <td style="font-size:8pt"><s:property value="PLANNO_N"/></td>
                                <td style="font-size:8pt"><s:property value="INVOICEDATE"/></td>
                                <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                <td style="font-size:8pt"><s:property value="BUYER_DESC"/></td>
                                <td style="font-size:8pt"><s:property value="TTO_DATE"/></td>
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
                        <table bgcolor="#f7f7f7" align="center" width="100%" cellspacing="1" cellpadding="3">
                            <tr>
                                <td style="font-size:8pt;font-weight:bold">P/M&nbsp;No.</td>
                                <td>
                                   <s:textfield id="PMNO" name="PMNO" value="%{PMNO}" theme="simple"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Manufacturer</td>
                                <td>
                                   <s:textfield id="MNFD" name="MNFD" value="%{MNFD}" theme="simple" readonly="true"/>
                                   <a href="searchcodedescKohlsStoreA.action?FLG1=S1" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img1" name="img1" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">Total&nbsp;Crtns</td>
                                <td>
                                  <s:textfield id="TOTCRTNS" name="TOTCRTNS" value="%{TOTCRTNS}" theme="simple"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Net&nbsp;Weight</td>
                                <td>
                                   <s:textfield id="NETWT" name="NETWT" value="%{NETWT}" theme="simple"/>
                                </td> 
                            </TR>
                             <tr>
                                 <td style="font-size:8pt;font-weight:bold">Gross&nbsp;Weight</td>
                                <td>
                                  <s:textfield id="GROSSWT" name="GROSSWT" value="%{GROSSWT}" theme="simple"/>
                                </td>
                                  <td style="font-size:8pt;font-weight:bold">Net&nbsp;Net&nbsp;Weight</td>
                                <td>
                                   <s:textfield id="NETNETWT" name="NETNETWT" value="%{NETNETWT}" theme="simple"/>
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">Hunger&nbsp;Code</td>
                                <td>
                                   <s:textfield id="HNGRCODE" name="HNGRCODE" value="%{HNGRCODE}" theme="simple"/>
                                   
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Commission</td>
                                <td>
                                   <s:textfield id="COMISON" name="COMISON" value="%{COMISON}" theme="simple"/>
                                </td> 
                            </TR>
                             <tr>
                                <td style="font-size:8pt;font-weight:bold">H S&nbsp;Code</td>
                                <td>
                                   <s:textfield id="HSCODE" name="HSCODE" value="%{HSCODE}" theme="simple"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Hunger&nbsp;Supplier</td>
                                <td>
                                   <s:textfield id="HNGRSUPP" name="HNGRSUPP" value="%{HNGRSUPP}" theme="simple" readonly="true"/>
                                   <a href="searchcodedesc1KohlsStoreA.action?FLG2=S2" class="search"  target="addapprofrm"  onclick="openpop('approveraddid');" ><img style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                </td> 
                                  <td style="font-size:8pt;font-weight:bold">S/Tag&nbsp;Supplier</td>
                                <td>
                                  <s:textfield id="STAGSUPP" name="STAGSUPP" value="%{STAGSUPP}" theme="simple" readonly="true"/>
                                  <a href="searchcodedesc2KohlsStoreA.action?FLG3=S3" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img3" name="img3" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Security&nbsp;Tag&nbsp;Rate</td>
                                <td>
                                   <s:textfield id="SECURITYTGRT" name="SECURITYTGRT" value="%{SECURITYTGRT}" theme="simple" readonly="true"/>
                                   <a href="searchcodedesc3KohlsStoreA.action?FLG4=S4" class="search"  target="addapprofrm"  onclick="openpop('approveraddid')" ><img id="img4" name="img4" style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                </td> 
                            </TR>
                            <tr>
                              <td style="font-size:8pt;font-weight:bold">Stich&nbsp;Count&nbsp;Horizontal</td>
                                <td>
                                   <s:textfield id="STCHCNTHORI" name="STCHCNTHORI" value="%{STCHCNTHORI}" theme="simple"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Stich&nbsp;Count&nbsp;Vertical</td>
                                <td>
                                   <s:textfield id="STCHCNTVERT" name="STCHCNTVERT" value="%{STCHCNTVERT}" theme="simple"/>
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">UOM</td>
                                <td>
                                   <s:textfield id="UOM" name="UOM" value="%{UOM}" theme="simple"/>
                                </td>  
                            </TR>
                            <TR>
                                <td align="center" colspan="12" >
                                    <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                  
                                         <button id="printheadId" class="sexybutton" onclick="printrec();"><span><span><span class="print" id="printId">Pdf</span></span></span></button>
		                                    
                                    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                </td>
                            </tr>
                            
                        </table>
                                <TABLE>
                                    <tr>
                                
                                <td>
                                    <div  style="width:100%; overflow:auto; height:200pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: right; top: expression(this.offsetParent.scrollTop);">
                                                       <th style="font-size:8pt;width:100px;" align="right">Box&nbsp;Dimension</th>
                                                  </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                                      <s:if test="%{INVOICE_S!=null}">
                                                          <s:if test="%{BoxDimensionList.size()>0}">
                                                              <s:set id="cnt" name="cnt" value="%{0}"/>
                                                        <s:iterator value="BoxDimensionList" status="st1">
                                                         <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:6pt;text-align: right;"><s:textfield id="BOXDIMEN%{#cnt+1}" name="BOXDIMEN" value="%{BDIMENSION}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>
                                                         </tr>
                                                       </s:iterator>
                                                         <s:iterator begin="cnt" end="9" status="st1">
                                                            <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:6pt;text-align: right;"><s:textfield id="BOXDIMEN%{#cnt+1}" name="BOXDIMEN" value="%{BOXDIMEN}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                            </tr>
                                                       </s:iterator>
                                                          </s:if>
                                                            <s:else>
                                                                            <s:iterator begin="0" end="9" status="st1">
                                                                             <tr bgcolor="#f7f7f7">
                                                                             <td style="font-size:6pt;text-align: right;"><s:textfield id="BOXDIMEN%{#st1.index}" name="BOXDIMEN" value="%{BOXDIMEN}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                                              </tr>
                                                                        </s:iterator> 
                                                             </s:else>
                                                          </s:if>
                                                                              <s:else>
                                                         <s:iterator begin="0" end="9" status="st1">
                                                                        <tr bgcolor="#f7f7f7">
                                                                            <td style="font-size:6pt;text-align: right;"><s:textfield id="BOXDIMEN%{#st1.index}" name="BOXDIMEN" value="%{BOXDIMEN}" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                                           </tr>
                                                                      </s:iterator> 
                                                            </s:else>
                                                </tbody>
                                            </table>
                                        </div> 
                                </td>
                                <td style="width:30px"></td>
                                <td>
                                  <div  style="width:100%; overflow:auto; height:200pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#f7f7f7" cellspacing="1" cellpadding="2" width="100%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);">
                                                    <th style="font-size:8pt;width:110px;" align="left">PO No.</th>
                                                    <th style="font-size:8pt;width:110px;" align="left">Style No.</th>
                                                    <th style="font-size:8pt;width:110px;" align="left">Category</th>
                                                    <th style="font-size:8pt;width:140px;" align="left">Invoice Desc</th>
                                                    <th style="font-size:8pt;width:110px;" align="right">Qnty</th>
                                                    <th style="font-size:8pt;width:110px;" align="left">Currency</th>
                                                    <th style="font-size:8pt;width:110px;" align="right">Rate</th>
                                                    <th style="font-size:8pt;width:110px;" align="right">Hnger Rate</th>
                                                    <th style="font-size:8pt;width:110px;" align="right">Value</th>
                                                    <th style="font-size:8pt;width:110px;" align="right">CBM/PCS</th>
                                                    
                                                  </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                                      <s:if test="%{INVOICE_S!=null}">
                                                         <s:if test="%{kohlsList.size()>0}">
                                                           <s:set id="cnt" name="cnt" value="%{0}"/>
                                                         <s:iterator value="kohlsList" status="st1">
                                                         <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt+1}" name="PONO" value="%{BPONO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt+1}" name="STYLENO" value="%{BSTYLENO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="CATEGRY%{#cnt+1}" name="CATEGRY" value="%{BCATEGRY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt+1}" name="INVDESC" value="%{BINVDESC}" cssStyle="width:140px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="QNTY%{#cnt+1}" name="QNTY" value="%{BQNTY}" cssStyle="width:110px;text-align:right" theme="simple" onblur="totqnty();"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="CURNCY%{#cnt+1}" name="CURNCY" value="%{BCURNCY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATE%{#cnt+1}" name="RATE" value="%{BRATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="HNGRRATE%{#cnt+1}" name="HNGRRATE" value="%{BHNGRRATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="VALU%{#cnt+1}" name="VALU" value="%{BVALU}" cssStyle="width:110px;text-align:right" theme="simple" onblur="valtot();"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="CBMPCS%{#cnt+1}" name="CBMPCS" value="%{BCBMPCS}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                         </tr>
                                                          </s:iterator>
                                                         <s:iterator begin="cnt" end="9" status="st1">
                                                            <tr bgcolor="#f7f7f7">
                                                            <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt}" name="PONO" value="%{BPONO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt}" name="STYLENO" value="%{BSTYLENO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="CATEGRY%{#cnt}" name="CATEGRY" value="%{BCATEGRY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt}" name="INVDESC" value="%{BINVDESC}" cssStyle="width:140px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="QNTY%{#cnt}" name="QNTY" value="%{BQNTY}" cssStyle="width:110px;text-align:right" theme="simple" onblur="totqnty();"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="CURNCY%{#cnt}" name="CURNCY" value="%{BCURNCY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="RATE%{#cnt}" name="RATE" value="%{BRATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="HNGRRATE%{#cnt}" name="HNGRRATE" value="%{BHNGRRATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="VALU%{#cnt}" name="VALU" value="%{BVALU}" cssStyle="width:110px;text-align:right" theme="simple" onblur="valtot();"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="CBMPCS%{#cnt}" name="CBMPCS" value="%{BCBMPCS}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                            </tr>
                                                       </s:iterator> 
                                                                
                                                            </s:if>
                                                             <s:else>
                                                                            <s:iterator begin="0" end="9" status="st1">
                                                                             <tr bgcolor="#f7f7f7">
                                                                             <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt}" name="PONO" value="%{PONO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt}" name="STYLENO" value="%{STYLENO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="CATEGRY%{#cnt}" name="CATEGRY" value="%{CATEGRY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt}" name="INVDESC" value="%{INVDESC}" cssStyle="width:140px;text-align:left" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="QNTY%{#cnt}" name="QNTY" value="%{QNTY}" cssStyle="width:110px;text-align:right" theme="simple" onblur="totqnty();"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="CURNCY%{#cnt}" name="CURNCY" value="%{CURNCY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="RATE%{#cnt}" name="RATE" value="%{RATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="HNGRRATE%{#cnt}" name="HNGRRATE" value="%{HNGRRATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="VALU%{#cnt}" name="VALU" value="%{VALU}" cssStyle="width:110px;text-align:right" theme="simple" onblur="valtot();"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="CBMPCS%{#cnt}" name="CBMPCS" value="%{CBMPCS}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                                             </tr>
                                                                        </s:iterator> 
                                                             </s:else>
                                                     </s:if>
                                                     <s:else>
                                                         <s:iterator begin="0" end="9" status="st1">
                                                                        <tr bgcolor="#f7f7f7">
                                                                          <td style="font-size:8pt;"><s:textfield id="PONO%{#cnt}" name="PONO" value="%{PONO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="STYLENO%{#cnt}" name="STYLENO" value="%{STYLENO}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="CATEGRY%{#cnt}" name="CATEGRY" value="%{CATEGRY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="INVDESC%{#cnt}" name="INVDESC" value="%{INVDESC}" cssStyle="width:140px;text-align:left" theme="simple"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="QNTY%{#cnt}" name="QNTY" value="%{QNTY}" cssStyle="width:110px;text-align:right" theme="simple" onblur="totqnty();"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="CURNCY%{#cnt}" name="CURNCY" value="%{CURNCY}" cssStyle="width:110px;text-align:left" theme="simple"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="RATE%{#cnt}" name="RATE" value="%{RATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="HNGRRATE%{#cnt}" name="HNGRRATE" value="%{HNGRRATE}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="VALU%{#cnt}" name="VALU" value="%{VALU}" cssStyle="width:110px;text-align:right" theme="simple" onblur="valtot();"/></td>
                                                                        <td style="font-size:8pt;"><s:textfield id="CBMPCS%{#cnt}" name="CBMPCS" value="%{CBMPCS}" cssStyle="width:110px;text-align:right" theme="simple"/></td>
                                                                        </tr>
                                                                      </s:iterator> 
                                                            </s:else>
                                                </tbody>
                                            </table>
                                        </div>  
                                </td>
                            </tr>
                            <tr>
                                <td></td> <td></td>
                                <td>
                                    <table bgcolor="#f7f7f7">
                                        <tr>
                                            <td style="width:425px"></td>
                                            <td style="font-size:8pt;font-weight:bold">
                                                Total&nbsp;Qty
                                            </td>
                                            <td>
                                                <s:textfield id="TOTQTY" name="TOTQTY" value="%{TOT_QNTY}" cssStyle="width:110px;text-align:right" theme="simple"/> 
                                            </td>
                                            <td  style="width:265px"></td>
                                            <td style="font-size:8pt;font-weight:bold">
                                               Total Value 
                                            </td>
                                            <td>
                                                 
                                                           <s:set id="t_val" value="TOT_VAL"/>
							   <s:text name="product.cost" id="val_tot" >
                                                               <s:param name="value" value="%{TOT_VAL}"/>
                                                           </s:text>
                                                           
                                                <s:textfield id="TOTVAL" name="TOTVAL" value="%{val_tot}" cssStyle="width:110px;text-align:right" theme="simple"/> 
                                            </td>
                                            
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                              
                            
                           
                                </TABLE>
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
            </table>

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