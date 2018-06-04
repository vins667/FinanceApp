<%-- 
    Document   : ScrapSaleInvoice
    Created on : Sep 22, 2016, 12:11:48 AM
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
            .toolTip {
                background-color: white;

                border: solid 1px;
                font-family: Arial;
                font-size: 12px;
                font-style: normal;
                font-variant: normal;
                font-weight: normal;
                left: 0;
                padding: 0px;
                position: absolute;
                text-align: left;
                top: 0;
                visibility: hidden;
                z-index: 2;
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
                document.frm.action="DecathlaneA.action?FLAG1=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
            
            function openpop(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv(a)
            {
                document.getElementById(a).style.display = 'none';
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
     

                function backmethod(){
                        document.frm.action="backDecathlaneA.action?";
                        document.frm.submit();
                        document.getElementById('prepage').style.visibility='';
                }
                
                function saverec()
                   {  
                       document.frm.action="update1DecathlaneA.action";
                       document.frm.submit();
                       document.getElementById('prepage').style.visibility='';
                   }
                   
                   function printrec()
                   {  
                       document.frm.action="printsDecathlaneA.action?REPORTTYPE=PDF";
                       document.frm.submit();
                       document.getElementById('prepage').style.visibility='';
                   }
                
         

             function onbackedit(url) {
                    document.frm.action = url;
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility = '';
                }
            function waitPreloadPage() { //DOM
                if (document.getElementById){
                    document.getElementById('prepage').style.visibility='hidden';                    
                }else{
                    if (document.layers){ //NS4
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
                                <th align="center" style="font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:#231819;">
                                    Decathlon Invoice</th>
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
                                            <td class="label-1" style="width:200px">Invoice No: <s:textfield name="INVOICE_S" id="INVOICE_S" cssStyle="text-transform:uppercase;width:80pt" theme="simple" maxLength="10"/>
                                            </td>
                                            <td align="left">
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                            </td>
                                        </tr>
                                        
                                    </table>
                                </td>
                            </tr>
                        </table> 
                    </td></tr>
                <tr><td>
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                                <th align="left">Inv No</th>
                                                <th align="left">Plan No</th>
                                                <th align="left">Inv date</th>
                                                <th align="left">Buyer Code</th>
                                                <th align="left">Buyer Addr#</th>
                                                <th align="left">TTO Date</th>
                                                <th align="left">TO Date</th>
                                                <th align="left">ETD Date</th>
                                                <th align="left">Awb Date</th>
                                                <th align="left">Fin. Date</th>
                                                <th align="right">Inv Qty</th>
                                                   
                                                
                                            </tr>
                                            <tr bgcolor="#f7f7f7">
                                                <td style="font-size:8pt"><s:property value="INVOICENO"/></td>
                                                <td style="font-size:8pt"><s:property value="PLANNO_N"/></td>
                                                 <td style="font-size:8pt"><s:property value="INVOICEDATE"/></td>
                                                <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                                <td style="font-size:8pt"><s:property value="BUYER_DESC"/></td>
                                                <td style="font-size:8pt"><s:property value="TTO_DATE"/></td>
                                                <td style="font-size:8pt"><s:property value="TO_DATE"/></td>
                                                <td style="font-size:8pt"><s:property value="ETD_DATE"/></td>
                                                <td style="font-size:8pt"><s:property value="AWB_DATE"/></td>
                                               <td style="font-size:8pt"><s:property value="FIN_DATE"/></td>
                                                <td style="font-size:8pt;text-align:right" ><s:property value="INV_QTY"/></td>
                                                 
                                                
                                            </tr> 
                                            
                                            
                                        </table>
                                    </td></tr>
                
                <tr><td> 
                        
                        <div align="center" style="width:100%;">
                            <table align="center" width="50%" border="0" cellpadding="0" cellspacing="0">
                                <tr   style="height: 300pt;">
                                  <td>
                                  <table  bgcolor="#f7f7f7"  align="center" width="60%" cellpadding="3" cellspacing="1" >
                                    <tr>
                                    <td>
                                    <table bgcolor="#f7f7f7" align="center" width="60%" cellspacing="1" cellpadding="3">
                                        <tr>
                                            <td class="label-1">PO&nbsp;Desc</td><td>
                                                  
                                                       <s:textfield name="PO_DESC" id="PO_DESC"  value="%{PO_DESC}"  cssStyle="text-transform:uppercase;width:180pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                               </td> 
                                            </TR>
                                            <tr>
                                                <td class="label-1">Criterion&nbsp;No.</td><td>
                                                   
                                                        <s:textfield name="CRITERION_NO" id="CRITERION_NO" value="%{CRITERION_NO}"  cssStyle="text-transform:uppercase;width:180pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                    
                                                </td> 
                                            </TR>
                                            <TR>
                                                <td class="label-1" style="width:100px">Model&nbsp;No.</td>
                                                <td><s:textfield name="MODEL_NO" id="MODEL_NO" value="%{MODEL_NO}" cssStyle="text-transform:uppercase;width:180pt" theme="simple" onKeyPress="tabE(this, event);"  />
                                                </td> 
                                            </tr>
                                            <tr>
                                                <td class="label-1" style="width:100px">Total&nbsp;Cartns</td>
                                                <td><s:textfield id="TOTAL_CRTNS" name="TOTAL_CRTNS" value="%{TOTAL_CRTNS}" cssStyle="text-transform:uppercase;width:180pt" theme="simple" />
                                                </td> 
                                          </tr>
                                          <tr>
                                                <td class="label-1" style="width:100px">Gross&nbsp;Weight</td>
                                                <td><s:textfield id="GROSS_WT" name="GROSS_WT" value="%{GROSS_WT}" cssStyle="text-transform:uppercase;width:180pt" theme="simple"  />
                                                </td> 
                                          </tr>
                                           <tr>
                                                <td class="label-1" style="width:100px">Net&nbsp;Weight</td>
                                                <td><s:textfield id="NET_WEIGHT" name="NET_WEIGHT" value="%{NET_WEIGHT}" cssStyle="text-transform:uppercase;width:180pt" theme="simple"   />
                                                </td> 
                                          </tr>
                                           <tr>
                                                <td class="label-1" style="width:100px">CBM</td>
                                                <td><s:textfield id="CBM" name="CBM" value="%{CBM}" cssStyle="text-transform:uppercase;width:180pt" theme="simple"  />
                                                </td> 
                                           </tr>
                                           <tr>
                                                <td class="label-1" style="width:100px">Ship&nbsp;To</td>
                                                <td><s:hidden id="SHIP_TO1" name="SHIP_TO1" value="%{SHIP_TO1}" cssStyle="text-transform:uppercase;width:120pt;background-color:#e0dede;" theme="simple" readonly="true" />
                                                    <s:textfield id="SHIP_TO" name="SHIP_TO" value="%{SHIP_TO}" cssStyle="text-transform:uppercase;width:180pt" theme="simple"/><a href="searchcodedescDecathlaneA.action" class="search"  target="addapprofrm"  onclick="openpop('approveraddid');" ><img style="border: 0px" src="../../css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                                </td> 
                                          </tr>
                                          <tr></tr>
                                          <tr></tr>
                                                 <TR>
                                                     <td align="center" colspan="2" >
                                                      <s:if test="%{FIN_DATE==null}">
                                                          <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                                     </s:if> 
                                                    <s:else> 
                                                            <button  id="saveheadId"  class="sexybutton" disabled="true" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                                     </s:else>  <button id="printheadId" class="sexybutton" onclick="printrec();"><span><span><span class="print" id="printId">Pdf</span></span></span></button>
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
                                         <td height="1pt"  align="center" style="color:red;font-weight:bold">
                                             <div style="height:5pt">
                                                 <s:actionerror />
                                                 <s:fielderror />
                                                 <s:actionmessage />
                                               </div>
                                          </td>
                                         </tr>                         
                             </table>
                        </div>
                    </td></tr>
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