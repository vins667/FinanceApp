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

            function searchdetail()
            {
               
                document.frm.action = "ByrPrtlDlyUpdtionA.action?FLAG1=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
                
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

            

            function saverec()
            {
                document.frm.action = "update1ByrPrtlDlyUpdtionA.action";
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
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >

           

            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="##231819">
                            <tr>
                                <th align="center" style="font-size:14.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:#231819;">
                                    Buyer Portal Delay Reason Updation </th>
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
                                             <td align="right"  >
                                                    <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                                    <button  id="saveheadId1"  class="sexybutton" onclick="javascript:window.location.href('ByrPrtlDlyUpdtionA.action?aausrid=<s:property value="%{aausrid}"/>');" ><span><span><span class="undo" id="printId" >Clear</span></span></span></button> 
                                                    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                             </td>
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
                                <td style="font-size:8pt"><s:property value="FIN_DATE"/></td>
                                <td style="font-size:8pt"><s:property value="INV_QTY"/></td>
                                <td style="font-size:8pt"><s:property value="SHIP_QTY"/></td>
                            </tr> 
                        </TABLE>
                    </td>
                </tr>
               <tr>
                                <td>
                                  <div align="center" style="width:100%; overflow:auto; height:250pt;border-width:1pt;border-color:whitesmoke; border-style:none;">
                                               <table id="mytableid" border="0" align="center" bgcolor="#B1CCD9" cellspacing="0" cellpadding="0" width="50%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);">
                                                    <th style="font-size:8pt;" align="left">Reason</th>
                                                    <th style="font-size:8pt;" align="left">Date</th>
                                                    <th style="font-size:8pt;" align="left">User</th>
                                                  </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                                          <s:if test="%{ByrPortalList.size()>0}">
                                                           <s:set id="cnt" name="cnt" value="%{0}"/>
                                                         <s:iterator value="ByrPortalList" status="st1">
                                                         <tr bgcolor="#f1f2f2">
                                                            <td style="font-size:8pt;"><s:select id="REASON_L1%{#cnt+1}" name="REASON_L1" value="%{BREASON_L}" list="%{SearchList}" listKey="LIST_CODE" listValue="LIST_NAME" headerKey="" headerValue="" cssStyle="width:500px;text-align:left;text-transform:uppercase;" readonly="true" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ENDDATE_L1%{#cnt+1}" name="ENDDATE_L1" value="%{BENDDATE_L}"  cssStyle="width:140px;text-align:left" theme="simple" readonly="true"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ENDUSER_L1%{#cnt+1}" name="ENDUSER_L1" value="%{BENDUSER_L}" cssStyle="width:100px;text-align:right" theme="simple" readonly="true"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>
                                                         </tr>
                                                          </s:iterator>
                                                          <s:iterator begin="cnt" end="30" status="st1">
                                                             <tr>
                                                            <td style="font-size:8pt;"><s:select id="REASON_L%{#cnt+1}" name="REASON_L" value="%{REASON_L}" list="%{SearchList}" listKey="LIST_CODE" listValue="LIST_NAME" headerKey="" headerValue="" cssStyle="width:500px;" theme="simple"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ENDDATE_L%{#cnt+1}" name="ENDDATE_L" value="%{ENDDATE_L}" cssStyle="width:140px;text-align:left" theme="simple" readonly="true"/></td>
                                                            <td style="font-size:8pt;"><s:textfield id="ENDUSER_L%{#cnt+1}" name="ENDUSER_L" value="%{ENDUSER_L}" cssStyle="width:100px;text-align:right" theme="simple" readonly="true"/></td>
                                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}"/>   
                                                            </tr>
                                                            </s:iterator> 
                                                                
                                                            </s:if>
                                                             <s:else>
                                                                        <s:iterator begin="0" end="30" status="st1">
                                                                            <tr >
                                                                            <td style="font-size:8pt;"><s:select id="REASON_L%{#cnt+1}" name="REASON_L" value="%{REASON_L}" list="%{SearchList}" listKey="LIST_CODE" listValue="LIST_NAME" headerKey="" headerValue="" cssStyle="width:500px;" theme="simple"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="ENDDATE_L%{#cnt+1}" name="ENDDATE_L" value="%{ENDDATE_L}" cssStyle="width:140px;text-align:left" theme="simple" readonly="true"/></td>
                                                                            <td style="font-size:8pt;"><s:textfield id="ENDUSER_L%{#cnt+1}" name="ENDUSER_L" value="%{ENDUSER_L}" cssStyle="width:100px;text-align:right" theme="simple" readonly="true"/></td>
                                                                            </tr>
                                                                        </s:iterator> 
                                                             </s:else>
                                                    
                                                </tbody>
                                            </table>
                                        </div>  
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