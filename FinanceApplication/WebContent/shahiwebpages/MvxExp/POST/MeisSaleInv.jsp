

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="js/dom-drag.js"></script>
<script type="text/javascript" src="../js/jquery.1.4.2.js"/>
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
            
function GstFun1(objsrc)
                { 
                   var xmlHttpReq = false; 
                   var self = this; 

                   if (window.XMLHttpRequest) { 
                       self.xmlHttpReq = new XMLHttpRequest();   
                   } else if (window.ActiveXObject) {  
                       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                   }

                   self.xmlHttpReq.open('POST', 'ajaxGSTCODEPOSTAJAX', false); 
                   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
                   self.xmlHttpReq.onreadystatechange = function() {

                        if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
                       {  
                             var a = self.xmlHttpReq.responseText;
                             var b= new Array();
                             b=a.split('#');
                             if(b.length>2)
                             {     
                                 
                             }
                             else
                             { 
                                   //objsrc.value=b[0];
                                   document.getElementById('FACTCODE').value=b[0];

                             }
                        }
                   }
                         param=objsrc.value;
                         self.xmlHttpReq.send("FACT_S="+objsrc.value);

          } 
            
      function validateinput(){
       
       
          if(document.frm.WAREHOUSENO.value=="")
        {
           alert("Please Select Facility  ")
            document.frm.WAREHOUSENO.focus();
           return false;
        }
         if(document.frm.BUYER.value=="")
        {
           alert("Please Select Buyer  ")
            document.frm.BUYER.focus();
           return false;
        }
         
           return true;
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

            function openHsn(frame, url, a, id, b,i)
               {
                                url=url+'?indexname='+i;
                                document.getElementById(frame).src =url;
                                document.getElementById(a).style.display = '';
                                document.getElementById(id).innerHTML =b;
                }  
     function openpop(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv(a)
            {
                document.getElementById(a).style.display = 'none';
            }
            
            
            function openpop1(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv1(a)
            {
                document.getElementById(a).style.display = 'none';
            }
     
     
     
      

            
        function getdescbuyer(a)  
       {  
           document.getElementById('DISPLAY_ITEM_DESC').value=document.getElementById('ITEM_DESC'+a).value;
           document.getElementById('DISPLAY_BUYER').value=document.getElementById('BUYER_NAME'+a).value;
       }     

         
              function fobtot(a){
               var x = document.frm.LIC_AMT;
               var y = document.frm.PRCT;
               var z = document.frm.SALE_AMT;
               var t= document.frm.TAX_PER;
                if(x.length>0){
                    for (var i = 0; i < x.length; i++) {
                        
                        if(x[i].value>0 || y[i].value>0){
                          z[i].value= (eval(x[i].value)*eval(y[i].value))/(100+t[i].value);
                          
                        } 
                        else{
                            
                        }
                    }
                 }
              
                 else{
                      if(x.value>0 || y.value>0){
                        z.value= (eval(x.value)*eval(y.value))/(100+t.value);
                         
                 }
                     }
                      
              }
function fobcal(a)
{              
       var t=0; var t1=0;  
       var a1=100;
    if(document.getElementById('LIC_AMT'+a).value!="")
        {    
            t=(eval(document.getElementById('LIC_AMT'+a).value)*eval(document.getElementById('PRCT'+a).value))/100;
     
            t1=eval(a1)+eval(document.getElementById('TAX_PER').value);
       
            t=(t/t1*100);
              
        }
         
         document.getElementById('SALE_AMT'+a).value=t.toFixed(2);
}
    function totalsalamt(a)
    {
       
     var SALE_AMT=document.frm.SALE_AMT;
     var LIC_AMT=document.frm.LIC_AMT;
     var t=0; var t1=0;  
    
    if(document.frm.LIC_AMT)
        {  
          if(LIC_AMT.length>0)  
              {     
                  for(m=0; m<LIC_AMT.length; m++)
                      {  
                          if(!isNaN(eval(SALE_AMT[m].value))){
                            t=t+eval(SALE_AMT[m].value) 
                            t1=t1+eval(LIC_AMT[m].value)
                       }
                      }
              }else{
                   t=t+eval(SALE_AMT.value) 
                   t1=t1+eval(LIC_AMT.value)
                   }
        } 
         document.getElementById('TOTAL_SALE_AMT').value=t.toFixed();
         document.getElementById('TOTAL_LIC_AMT').value=t1.toFixed();
       }
 
              function validatenumber(a,b)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,4}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
                    
            		return false;
            	}
                 fobcal(b);
                totalsalamt(a);
               
            	return true;
            } 
         
function searchlic(objsrc,objtrg,index)
{ 
    
    
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxMeisLicPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             
             if(b.length>5)
             {     
                
                 document.getElementById('addapprofrm2').src="licsearchMeisLicenceSale.action?SEARCH_CODE="+objsrc.value+"&indexname="+index;
                 openpop('approveraddid2');
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
                 document.getElementById('addapprofrm2').src="licsearchMeisLicenceSale.action?indexname="+index;
                 openpop('approveraddid2');
              }
               else
               {
                   objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                   document.getElementById('LIC_NO'+index).value=b[0];
                   document.getElementById('LIC_DATE'+index).value=b[1];
                   document.getElementById('PORT_CODE'+index).value=b[2];
                   document.getElementById('LIC_AMT'+index).value=b[3];
                   document.getElementById('PRCT'+index).value=b[4];
                   document.getElementById('SALE_AMT'+index).value=b[5];        
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}
              function saverec()
               {  
                if(validateinput()){
                   
                    document.frm.action="updateMeisLicenceSale.action?saveFlag='YES'";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
                    }
               
                }
               function updaterec()
               {  
                if(validateinput()){
                   
                    document.frm.action="updateMeisLicenceSale.action?updFlag='Yes'";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
                    }
               
                }
            function onbackedit(url) {
                    document.frm.action = url;
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility = '';
                }
 
          function updtFactory(){
               WH=document.getElementById("WAREHOUSENO").value;
               url='newentryMeisLicenceSale.action?Flg=YES&WHOUSE='+WH ;
               document.frm.action=url;
               document.frm.submit();
               //document.getElementById('prepage').style.visibility=''; 
             }
             
           function GstFun(){
               WH=document.getElementById("WAREHOUSENO").value;
               url='newentrySMeisLicenceSale.action?Flg=YES&F_CODE='+document.getElementById("FACTORYCODE").value+'&WHOUSE='+WH;
               document.frm.action=url;
               document.frm.submit();
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
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            
            <s:hidden id="FINAL_P" name="FINAL_P" value="%{FINAL_P}"/>
            <s:hidden id="pchlist" name="pchlist" value="%{pchlist}"/>
            <s:hidden id="" name="" value=""/>
            
            <s:hidden id="LICDATE" name="LICDATE" value="%{LICDATE}"/>
            <s:hidden id="LICPORT" name="LICPORT" value="%{LICPORT}"/>
            <s:hidden id="LICAMT" name="LICAMT" value="%{LICAMT}"/>
            <s:hidden id="LICPRCT" name="LICPRCT" value="%{LICPRCT}"/>
            <s:hidden id="SALEAMT" name="SALEAMT" value="%{SALEAMT}"/>
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    MEIS Licence Sale Invoice</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="3">
                                            <tr>
                                               
                                                
                                                <td class="label-1" style="width:100px">Invoice No.</td><td><s:textfield name="INVOICENO" id="INVOICENO" value="%{INVOICENO}" cssStyle="text-transform:uppercase;width:80pt" cssClass="textreadonly" theme="simple" onKeyPress="tabE(this, event);" readonly="true" />
                                                </td> 
                                                
                                                <td class="label-1">W/House</td><td>
                                                   <s:if test="%{INVOICENO!=null}">
                                                       <s:textfield name="WAREHOUSENO" id="WAREHOUSENO"  value="%{WAREHOUSENO}" readonly="true" cssStyle="text-transform:uppercase;width:80pt"  cssClass="textreadonly" theme="simple" onKeyPress="tabE(this, event);"/>
                                                   </s:if>
                                                   <s:else>
                                                       <s:select name="WAREHOUSENO" id="WAREHOUSENO"  value="%{WAREHOUSENO}" headerValue=""  headerKey="" list="%{whouselist}"  cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);" onchange="updtFactory();"/>
                                                   </s:else>
                                               </td>   
                                                <td class="label-1">Factory&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; 
                                                         <s:select name="FACTORYCODE" id="FACTORYCODE" value="%{FACTORYCODE}"  headerValue=""  headerKey=""  listKey="CODE" listValue="DESC" list="%{unitlist}" cssStyle="text-transform:uppercase;width:200px" theme="simple" onchange="GstFun1(this);"/>
                                                         <s:textfield name="FACTCODE" id="FACTCODE"  value="%{FACTCODE}" readonly="true" cssClass="textreadonly" cssStyle="text-transform:uppercase;width:60pt" theme="simple" />
                                                         </td> 
                                               
                                                <td align="right" >
                                                      
                                                        <button  id="searchheadId" class="sexybutton" onclick="onbackedit('MeisLicenceSale.action')"><span><span><span class="undo" id="searchId">Back</span></span></span></button>
                                                    
                                                        <s:if test="%{FINAL_P!=null && FINAL_P!=''}">
                                                           <button  id="saveheadId"  class="sexybutton" onclick="saverec();" disabled="true" ><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                        </s:if>
                                                        <s:else>
                                                            <s:if test="%{INVOICENO.length()>5}">
                                                                  <button  id="saveheadId"  class="sexybutton" onclick="updaterec();" ><span><span><span class="save" id="saveId" >Update</span></span></span></button> 
                                                             </s:if>
                                                             <s:else>
                                                                  <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                             
                                                             </s:else>
                                                           </s:else>
                                                   
                                                  </td>
                                                
                                                </tr>
                                                <tr> 
                                                         <td class="label-1" style="width:100px">Invoice&nbsp;Date</td><td><s:textfield id="INVOICEDATE" name="INVOICEDATE" value="%{INVOICEDATE}" cssStyle="text-transform:uppercase;width:80pt" cssClass="textreadonly" theme="simple"  readonly="true" />
                                                </td> 
                                                  
                                                     
                                                <td class="label-1">
                                                    Buyer Code
                                                </td>
                                       <td><s:textfield name="BUYER" 
                                           id="BUYER" 
                                           cssStyle="width:80pt" 
                                           maxlength="10"
                                           value="%{BUYER}"
                                           cssClass="texts" 
                                           readonly="true"
                                           theme="simple" onblur="if(this.value!='') xmlhttpreqbuyer(this,'BUYER_DESC')" 
                                         />
                                           <a href="buyersearchGST.jsp" target="addapprofrm" onclick="openpop1('approveraddid')"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                        </td>
                                      
                                          
                                        <td class="label-1">Buyer Name 
                                       <s:textfield name="BUYER_DESC" 
                                           id="BUYER_DESC" 
                                           cssStyle="width:200px" 
                                           readonly="true"
                                           value="%{BUYER_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/>
                                       <s:textfield name="STATE_DESC" 
                                           id="STATE_DESC" 
                                           cssStyle="width:50px" 
                                           readonly="true"
                                           value="%{STATE_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/>
                                       <input type="button" class="texts" style="width:50px;" onclick="alert(document.getElementById('address').value)" name="addressno" id="addressno" value="<s:property value="%{BUYER_ADDR}"/>"/>
                                       <input type="hidden" name="address" value="<s:property value="%{BUYER_ADDRESS_NAME}"/>" id="address"/>  
                                  
                                  </td>
                                   
                                     <s:hidden name="YEAR" id="YEAR" value="%{YEAR}"/>      
                                     <s:hidden name="COMPANY" id="COMPANY" value="%{COMPANY}"/>      
                                     <s:hidden name="INV_NO" id="INV_NO" value="%{INV_NO}"/>      
                                     <s:hidden name="BUYER_ADDR" id="BUYER_ADDR" value="%{BUYER_ADDR}"/>      
                                                
 
                                   </tr>
                         
                                                <tr>
                                                    <td class="label-1">Remarks </td><td colspan="3">
                                                            <s:textfield name="REM" id="REM" value="%{REM}"  cssStyle="text-transform:uppercase;width:350pt"   theme="simple" onKeyPress="tabE(this, event);" />
                                                       
                                                      </td> 
                                                      <td class="label-1">Tax Code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      
                                                                <s:textfield id="TAX_TYPE" name="TAX_TYPE" value="%{TAX_TYPE}" readonly="true" cssStyle="width:200px;text-align:left" theme="simple"/> 
                                                                <a href="TaxTypeSearch.jsp" target="addapprofrm" onclick="openpop1('approveraddid')"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                     
                                                             <s:textfield id="TAX_CODE" name="TAX_CODE" value="%{TAX_CODE}"    readonly="true" cssStyle="width:50px;text-align:left;" theme="simple"/>
                                                             <s:textfield id="TAX_PER" name="TAX_PER" value="%{TAX_PER}" cssClass="textreadonly"  readonly="true" cssStyle="width:50px;text-align:right;" theme="simple"/>
                                                      </td>
                                                      
                                                 <td class="label-1">Final Print &nbsp; 
                                                    <s:if test="%{FINAL_P!=null && FINAL_P!=''}">
                                                        <s:checkbox name="FINAL_CHK" id="FINAL_CHK" checked="true"  theme="simple" disabled="true" />  
                                                        <s:property value="%{FINAL_P}"/>
                                                    </s:if>
                                                    <s:else>
                                                           
                                                            <input type="checkbox" name="FINAL_CHK" value="Y"/>
                                                    </s:else>
                                                </td>
                                               
                                                </tr>
                                                
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td> 
                        <div style="width:100%" class="toolTip" id="toolTipDiv"></div>
                        <div align="center" style="width:100%;">
                            <table width="100%" cellpadding="0" cellspacing="0" border="1">
                                <tr   style="background-color: whitesmoke;height: 300pt;">
                                    <td>  
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="0" cellspacing="0" >
                                   <tr> 
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:300pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="1" width="70%">
                                                   <thead>
                                                        <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                         <th style="font-size:9pt" align="left">Sl#</th>
                                                         <th style="font-size:9pt" align="left">Licence No.</th>
                                                         <th style="font-size:9pt" align="left">Licence Date</th>
                                                         <th style="font-size:9pt" align="left">Regd Port</th>
                                                         <th style="font-size:9pt" align="right">Licence Amt</th>
                                                         <th style="font-size:9pt" align="right">Percent</th>
                                                         <th style="font-size:9pt" align="left" colspan="">HSN Code &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                                         <th style="font-size:9pt" align="right" colspan=""> % &nbsp;&nbsp;&nbsp;</th> 
                                                         <th style="font-size:9pt" align="right">Sale Amt</th>
                                                         
                                                      </tr>                                                
                                                </thead> 
                                                 <tbody>  
                                                      <s:set id="ctn" name="ctn" value="0"/>  
                                                      <s:iterator value="%{listdata}" status="st1">
                                                      <tr bgcolor="#f2f9fb"> 
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index}" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="LIC_NO%{#ctn}" readonly="true" name="LIC_NO" value="%{FACILITY}" cssStyle="width:100%;text-align:left" theme="simple" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="LIC_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="LIC_DATE" value="%{EXCS_INV_NO}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="PORT_CODE%{#ctn}" cssClass="textreadonly"  readonly="true" name="PORT_CODE" value="%{BUYER}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="LIC_AMT%{#ctn}" cssClass="textreadonly"  readonly="true" name="LIC_AMT" value="%{INV_DATE}" cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="PRCT%{#ctn}"  name="PRCT" value="%{REMARK}" cssStyle="width:100%;text-align:right" theme="simple"   onblur="validatenumber(this,%{#ctn});"  tabindex="2" /></td>
                                                            <td style="font-size:8pt;text-align: left"><s:textfield id="HSN_CODE_L%{#ctn}" name="HSN_CODE_L" value="%{TAX_CODE}" cssStyle="width:80%;text-align:left" theme="simple" /> 
                                                                
                                                                <a href="#" onclick="openHsn('addapprofrm2','taxsearchMeisLicenceSale.action','approveraddid2','handlediv','Hsn Detail','<s:property value="%{#ctn}"/>');"><img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                  
                                                            </td>
                                                            <td style="font-size:8pt;text-align:left"><s:textfield id="HSN_CODE_PER_L%{#ctn}" name="HSN_CODE_PER_L" value="%{TAX_TYPE}" cssStyle="width:70%;text-align:left" theme="simple" readonly="true"/> 
                                                           <td style="font-size:8pt;text-align: left;"><s:textfield id="SALE_AMT%{#ctn}" cssClass="textreadonly"  readonly="true" name="SALE_AMT" value="%{TAX_CODE}" cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                            </tr>
                                                         <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                       </s:iterator>
                                                       <s:iterator begin="%{#ctn}" end="20" status="st">
                                                           <tr style="background-color: #FFFFFF">
                                                            <td style="font-size:8pt"><s:property value="%{#ctn}" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="LIC_NO%{#ctn}"  name="LIC_NO"  cssStyle="width:80%;text-align:left" theme="simple" onblur="if(this.value!='') searchlic(this,'LIC_NO%{#ctn}','%{#ctn}');fobtot()" />
                                                                <a href="licsearchMeisLicenceSale.action?indexname=<s:property value="%{#ctn}"/>" class="search"  target="addapprofrm2"  onclick="openpop('approveraddid2');" ><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                        
                                                            </td> 
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="LIC_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="LIC_DATE"  cssStyle="width:100%;text-align:left" theme="simple" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="PORT_CODE%{#ctn}" cssClass="textreadonly"  readonly="true" name="PORT_CODE"  cssStyle="width:100%;text-align:left" theme="simple"  /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="LIC_AMT%{#ctn}" cssClass="textreadonly"  readonly="true" name="LIC_AMT" value="%{LIC_AMT}" cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="PRCT%{#ctn}"   name="PRCT"  onblur=" validatenumber(this,%{#ctn});" onKeyPress=""  cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left"><s:textfield id="HSN_CODE_L%{#ctn}" name="HSN_CODE_L" value="%{HSCODE}" cssStyle="width:80%;text-align:left" theme="simple" /> 
                                                                
                                                                <a href="#" onclick="openHsn('addapprofrm2','taxsearchMeisLicenceSale.action','approveraddid2','handlediv','Hsn Detail','<s:property value="%{#ctn}"/>');"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                                  
                                                            </td>
                                                            <td style="font-size:8pt;text-align:left"><s:textfield id="HSN_CODE_PER_L%{#ctn}" name="HSN_CODE_PER_L" value="%{HSNPER}" cssStyle="width:100%;text-align:left" theme="simple" readonly="true"/> </td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="SALE_AMT%{#ctn}" cssClass="textreadonly"  readonly="true" name="SALE_AMT" value="%{SALE_AMT}" cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                               
                                                           </tr>
                                                            <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>   
                                                   
                                                         </s:iterator>

                                                </tbody>
                                            </table>
                                        </div>  
                                      </td>
                                 </tr>

                                 </table> 
                                 </td> 
                                 </tr>
                                   <table  bgcolor="#f2f9fb"  align="center" width="70%" cellpadding="0" cellspacing="0" border="1" >
                      
                                 <tr>
                                     <td width="10%"></td> 
                                   <th class="lable-1" align="right" width="35%">Total Lic Amt#</th>   
                                              <td  style="text-align: right;">
                                                      <s:set id="amtpay1" value="%{TOTAL_LIC_AMT}"/>    
                                                        <s:if test="%{#amtpay1>0}">
                                                              <s:text name="product.cost" id="tamt">
                                                                   <s:param name="value" value="%{#amtpay1}"/>
                                                               </s:text>
                                                               <s:textfield name="TOTAL_LIC_AMT" id="TOTAL_LIC_AMT" value="%{#tamt}" readonly="readonly" theme="simple" cssStyle="width:80pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />
                                                      </s:if>
                                                      <s:else>
                                                              <s:textfield name="TOTAL_LIC_AMT" id="TOTAL_LIC_AMT" value="0.00" readonly="readonly" theme="simple" cssStyle="width:80pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                      </s:else>    
                                                </td> 
                                
                                <th class="lable-1" align="right">Total Sale Amt#</th>
                                <td class="label-1" style="height:25px;width: 70px">
                                               <s:set id="amtpay2" value="%{TOTAL_SALE_AMT}"/>    
                                                        <s:if test="%{#amtpay2>0}">
                                                              <s:text name="product.cost" id="samt">
                                                                   <s:param name="value" value="%{#amtpay2}"/>
                                                               </s:text>
                                                               <s:textfield name="TOTAL_SALE_AMT" id="TOTAL_SALE_AMT" value="%{#samt}" readonly="readonly" theme="simple" cssStyle="width:80pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />
                                                      </s:if>
                                                      <s:else>
                                                              <s:textfield name="TOTAL_SALE_AMT" id="TOTAL_SALE_AMT" value="0.00" readonly="readonly" theme="simple" cssStyle="width:80pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                      </s:else>    
                                </td>   
                                   <td></td><td></td>  
                                 </tr>  
                                     
                                 </table>
                              
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
                                               
        <script type="text/javascript">
                var approveraddid1 = document.getElementById("approveraddid");
                var report = document.getElementById('Report');
                Drag.init(report, approveraddid1);
        </script>
        
        
        <div id="approveraddid2" class="root1" style="left:50px; top:100px;display:none">
            <table cellpadding="0" cellspacing="0">
                <tr bgcolor="#000080">
                    <td width="100%">
                        <div id="Report2" class="handle1" style="cursor: move">Tax Type List</div>
                    </td>
                    <td style="10px"><a href="#" onclick="closediv('approveraddid2')"><img border="0" width="18px" height="18px" src="css/image/divclose.gif"/></a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <iframe name="addapprofrm2" id="addapprofrm2" src="" scrolling="no" frameborder="0"  width="100%" height="300px"></iframe>
                    </td>
                </tr>
            </table>
        </div>
                                               
        <script type="text/javascript">
                var approveraddid3 = document.getElementById("approveraddid2");
                var report2 = document.getElementById('Report2');
                Drag.init(report2, approveraddid3);
        </script>
                        </div>
                    </td></tr>
            </table>  
        </form> 
    </body>
</html>