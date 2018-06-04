<%-- 
    Document   : WalMart_Inv
    Created on : Feb 08, 2017, 12:21:48 PM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="../js/dom-drag.js"></script>
<html> 
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            .root
            { 
                position:absolute;
                height:200px;
                width:800px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .handle
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px
                    
            }  
            
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('css/image/table-gradient.jpg');
            }
            tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
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
                document.frm.action = "LcMasterA.action";
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
                document.frm.action = "update1LcMasterA.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility = '';
            }
             function clearrec()
            {
                document.frm.action = "clear1LcMasterA.action";
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
            
 function SearchFinBank(objsrc,objtrg)
{ 
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxBankPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>4)
             {     
                
                 document.getElementById('addapprofrm').src="bankviewLcMasterA.action?SEARCH_CODE="+objsrc.value;
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
                 document.getElementById('addapprofrm').src="bankviewLcMasterA.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                  // document.getElementById('FBANK').value=b[0];
                   document.getElementById('FBANK_ADDR').value=b[1];
                   document.getElementById('FBANK_NAME').value=b[2];
                   //document.getElementById('FBANK_ADDRESS').value=b[3];
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}

function SearchBeniBank(objsrc,objtrg)
{ 
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxBankPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>4)
             {     
                
                 document.getElementById('addapprofrm').src="bankview1LcMasterA.action?SEARCH_CODE="+objsrc.value;
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
                 document.getElementById('addapprofrm').src="bankview1LcMasterA.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                  // document.getElementById('FBANK').value=b[0];
                   document.getElementById('BENIFICRYBNK_ADDR').value=b[1];
                   document.getElementById('BENIFICRYBNKDESC').value=b[2];
                   //document.getElementById('FBANK_ADDRESS').value=b[3];
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}

function SearchByr(objsrc,objtrg)
{ 
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
    self.xmlHttpReq.open('POST', 'ajaxBuyerPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {     
                
                 document.getElementById('addapprofrm').src="searchBuyrLcMasterA.action?SEARCH_CODE="+objsrc.value;
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
                 document.getElementById('addapprofrm').src="searchBuyrLcMasterA.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
            
                   document.getElementById('BUYER').value=b[0];
                   document.getElementById('BUYERDESC').value=b[1];
                 
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}

function SrchCrncy(objsrc,objtrg)
{ 
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxCrncyPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {     
                
                 document.getElementById('addapprofrm').src="searchCrncyLcMasterA.action?SEARCH_CODE="+objsrc.value;
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
                 document.getElementById('addapprofrm').src="searchCrncyLcMasterA.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
            
                   document.getElementById('CURRNCY').value=b[0];
                   document.getElementById('CURRNCYDESC').value=b[1];
                 
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}

function SrchPaytrm(objsrc,objtrg)
{ 
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxPaytrmPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>2)
             {     
                
                 document.getElementById('addapprofrm').src="searchPaytrmLcMasterA.action?SEARCH_CODE="+objsrc.value;
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
                 document.getElementById('addapprofrm').src="searchPaytrmLcMasterA.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
            
                   document.getElementById('PMTTRM').value=b[0];
                   document.getElementById('PMTTRMDESC').value=b[1];
                 
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
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

            <s:hidden id="flagVAL" name="flagVAL" value="%{flag4}"/>
            <s:hidden id="pchlist" name="pchlist" value="%{pchlist}"/>
            <s:hidden id="CNTRY" name="CNTRY" value="%{CNTRY}"/>
            


            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="0" cellspacing="1" width="100%" bgcolor="##231819">
                            <tr>
                                <th align="center" style="font-size:14.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:#231819;">
                                    LC Master</th>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>
                        <table bgcolor="#f2f2f2" align="center" width="100%" cellspacing="0" cellpadding="0" border="0">
                            <tr>
                               <td style="font-size:8pt;font-weight:bold">LC.&nbsp;Type</td>
                                <td>
                                   <s:select id="REFTYPECODE" name="REFTYPECODE" value="%{REFTYPECODE}" list="# {'LC':'LC'}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" cssClass="textreadonly" readonly="true" tabindex="1"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Doc&nbsp;Type</td>
                                <td>
                                   <s:select id="DOCTYPE" name="DOCTYPE" value="%{DOCTYPE}" list="# {'E':'Export'}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" cssClass="textreadonly" readonly="true" tabindex="2" />
                                </td>
                                <td style="font-size:8pt;font-weight:bold">Description</td>
                                <td>
                                  <s:textfield id="DESCRIPTION" name="DESCRIPTION" value="%{DESCRIPTION}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="3" />
                                </td>
                            </tr>
                             <tr>
                                  <td style="font-size:8pt;font-weight:bold">LC.&nbsp;No</td>
                                <td>
                                   <s:textfield id="REFNO" name="REFNO" value="%{REFNO}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="1" onblur="searchdetail();"/>
                                </td> 
                                 <td style="font-size:8pt;font-weight:bold">LC&nbsp;Type</td>
                                <td>
                                  <s:select id="LCTYPE" name="LCTYPE" value="%{LCTYPE}" list="# {'OTH':'Other'}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" cssClass="textreadonly" readonly="true" tabindex="2" />
                                </td> 
                                  <td style="font-size:8pt;font-weight:bold">Mode of Shipmnt</td>
                                <td>
                                   <s:select id="MODESHPNT" name="MODESHPNT" value="%{MODESHPNT}" list="# {'A':'AIR','S':'SEA','R':'ROAD','B':'AIR/SEA'}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="3"/>
                                </td>
                            </tr>
                             <tr> 
                                  <td style="font-size:8pt;font-weight:bold">LC&nbsp;Date</td>
                                <td>
                                    <sx:datetimepicker id="REFDATE" name="REFDATE" value="%{REFDATE}" displayFormat="dd-MMM-yyyy" tabindex="1"/>
                                </td>
                                 <td style="font-size:8pt;font-weight:bold">Buyer</td>
                                <td>
                                   <s:textfield id="BUYER" name="BUYER" value="%{BUYER}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="2" onblur="if(this.value!='') return SearchByr(this,'FBANK1')"/>
                                 <a href="searchBuyrLcMasterA.action" target="addapprofrm" onclick="openpop('approveraddid')"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                   <s:textfield id="BUYERDESC" name="BUYERDESC" value="%{BUYERDESC}" theme="simple" style="width:210px"  cssClass="textreadonly" readonly="true"/>
                                </td>
                                <td style="font-size:8pt;font-weight:bold">LC Validity</td>
                                <td>
                                   <sx:datetimepicker id="VALIDITY" name="VALIDITY" value="%{VALIDITY}" displayFormat="dd-MMM-yyyy" tabindex="3"/>
                                </td>
                                
                            </tr>
                            <tr>
                                <td style="font-size:8pt;font-weight:bold">Issue Bank</td>
                                <td>
                                   <s:textfield id="FBANK" name="FBANK" value="%{FBANK}" theme="simple" cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" tabindex="1" onblur="if(this.value!='') return SearchFinBank(this,'FBANK1')"/>
                                   <a href="bankviewLcMasterA.action" target="addapprofrm" onclick="openpop('approveraddid')"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                   <s:textfield id="FBANK_ADDR" name="FBANK_ADDR" value="%{FBANK_ADDR}" theme="simple" style="width:70px"  cssClass="textreadonly" readonly="true"/>
                                   <s:textfield id="FBANK_NAME" name="FBANK_NAME" value="%{FBANK_NAME}" theme="simple" style="width:210px"  cssClass="textreadonly" readonly="true"/>
                                   
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">Currency</td>
                                <td>
                                   <s:textfield id="CURRNCY" name="CURRNCY" value="%{CURRNCY}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="2" onblur="if(this.value!='') return SrchCrncy(this,'FBANK1')"/>
                                   <a href="searchCrncyLcMasterA.action" target="addapprofrm" onclick="openpop('approveraddid');"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                   <s:textfield id="CURRNCYDESC" name="CURRNCYDESC" value="%{CURRNCYDESC}" theme="simple" style="width:210px"  cssClass="textreadonly" readonly="true"/>
                                </td>
                                
                                <td style="font-size:8pt;font-weight:bold">Export&nbsp;Validity</td>
                                <td>
                                <sx:datetimepicker id="EXPORTVALIDITY" name="EXPORTVALIDITY" value="%{EXPORTVALIDITY}" displayFormat="dd-MMM-yyyy" tabindex="3"/>
                                </td>
                                
                            </tr>
                            <tr>
                                <td style="font-size:8pt;font-weight:bold">Benef Bank</td>
                                <td>
                                   <s:textfield id="BENIFICRYBNK" name="BENIFICRYBNK" value="%{BENIFICRYBNK}" theme="simple" cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" tabindex="1" onblur="if(this.value!='') return SearchBeniBank(this,'FBANK1')"/>
                                   <a href="bankview1LcMasterA.action" target="addapprofrm" onclick="openpop('approveraddid');"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                   <s:textfield id="BENIFICRYBNK_ADDR" name="BENIFICRYBNK_ADDR" value="%{BENIFICRYBNK_ADDR}" theme="simple" style="width:70px"  cssClass="textreadonly" readonly="true"/>
                                   <s:textfield id="BENIFICRYBNKDESC" name="BENIFICRYBNKDESC" value="%{BENIFICRYBNKDESC}" theme="simple" style="width:210px"  cssClass="textreadonly" readonly="true"/>
                                </td> 
                                
                                 <td style="font-size:8pt;font-weight:bold">Payment Term</td>
                                <td style="font-size:9px;font-weight: bold;">
                                    <s:textfield id="PMTTRM" name="PMTTRM" value="%{PMTTRM}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="2" onblur="if(this.value!='') return SrchPaytrm(this,'FBANK1')"/>&nbsp;
                                   <a href="searchPaytrmLcMasterA.action" target="addapprofrm" onclick="openpop('approveraddid')"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>&nbsp;
                                    <s:textfield id="PMTTRMDESC" name="PMTTRMDESC" value="%{PMTTRMDESC}" theme="simple" style="width:210px"  cssClass="textreadonly" readonly="true"/>
                                    
                                </td> 
                                
                               <td style="font-size:8pt;font-weight:bold">Import&nbsp;Validity</td>
                                <td>
                                <sx:datetimepicker id="IMPORTVALIDITY" name="IMPORTVALIDITY" value="%{IMPORTVALIDITY}" displayFormat="dd-MMM-yyyy" tabindex="3"/>
                                </td>
                            </tr>
                            <tr>
                                 <td style="font-size:8pt;font-weight:bold">Shipment Term</td>
                                <td>
                                   <s:select id="SHPMNTTRM" name="SHPMNTTRM" value="%{SHPMNTTRM}" list="# {'FOB':'FOB','CNF':'CNF','CIF':'CIF','EXW':'EXW'}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="1"/>
                                </td> 
                                
                                 <td style="font-size:8pt;font-weight:bold">Presentation</td>
                                <td style="font-size:9px;font-weight: bold;">
                                    <s:textfield id="PRSNTAION" name="PRSNTAION" value="%{PRSNTAION}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="2"/>&nbsp;
                                </td> 
                                
                            </tr>
                            <tr>
                                <td style="font-size:8pt;font-weight:bold">Value FC</td>
                                <td style="font-size:9px;font-weight: bold;">
                                    <s:textfield id="VALUE" name="VALUE" value="%{VALUE}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="1"/>&nbsp;
                                </td> 
                                <td style="font-size:8pt;font-weight:bold">Export FC</td>
                                <td>
                                   <s:textfield id="EXPORT_FC" name="EXPORT_FC" value="%{EXPORT_FC}" theme="simple" cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" tabindex="2"/>
                                </td> 
                                  <td style="font-size:8pt;font-weight:bold">Import FC</td>
                                <td>
                                <s:textfield id="IMPORT_FC" name="IMPORT_FC" value="%{IMPORT_FC}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="3"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:8pt;font-weight:bold">Value INR</td>
                                <td>
                                <s:textfield id="INR_VAL" name="INR_VAL" value="%{INR_VAL}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="1"/>
                                </td>
                                  <td style="font-size:8pt;font-weight:bold">Export INR</td>
                                <td style="font-size:9px;font-weight: bold;">
                                    <s:textfield id="EXPORT_INR" name="EXPORT_INR" value="%{EXPORT_INR}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="2"/>&nbsp;
                                </td> 
                                
                                <td style="font-size:8pt;font-weight:bold">Import INR</td>
                                <td>
                                   <s:textfield id="IMPORT_INR" name="IMPORT_INR" value="%{IMPORT_INR}" theme="simple" cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" tabindex="3"/>
                                </td> 
                            </tr>
                            <tr>
                               <td style="font-size:8pt;font-weight:bold">Percent %</td>
                                <td>
                                   <s:textfield id="PERCNT" name="PERCNT" value="%{PERCNT}" theme="simple" cssStyle="text-transform:uppercase;width:120pt;font-size:9pt;" tabindex="1"/>
                                </td>   
                            </tr>
                            <tr style="height:15pt;">
                                <td colspan="5"></td>
                            </tr>
                            
                    <tr>
                          <s:hidden id="aausrid" name="aausrid" value="%{aausrid}"/>
                        <td colspan="6">
                        <table align="center" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f7f7f7" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                             <td align="center"  >
                                                    <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                                                    <button  id="saveheadId"  class="sexybutton" onclick="clearrec();" ><span><span><span class="reload" id="saveId" >Clear</span></span></span></button>
                                                    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
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
                    

<div id="approveraddid" class="root" style="left:50px; top:100px;display:none">
                       <table cellpadding="0" cellspacing="0">
                           <tr bgcolor="#000080">
                               <td width="100%">
                                   <div id="Report" class="handle1" style="cursor: move">Buyer List</div>
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
             <div id="root" class="root" style="left:50px; top:200px;display:none;width:900px;z-index: 10000">
               <table cellpadding="0" cellspacing="0">
                <tr bgcolor="#6699FF">
                    <td width="100%">
                        <div id="handle" class="handle"  style="cursor: move">Details</div>
                    </td>
                    <td style="10px"><a href="#" onclick="closediv('root')" ><img border="0" width="18px" height="18px" src="image/chrome_close_button.png"/></a>
                    </td>
                </tr>
                
             </table>
           </div>
           <script language="javascript">
            var theHandle = document.getElementById("handle");
            var theRoot   = document.getElementById("root");
            Drag.init(theHandle, theRoot);
           </script> 


</form> 
</body>
</html>