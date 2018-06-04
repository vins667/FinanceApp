<%-- 
    Document   : ScrapSaleInvoice
    Created on : Sep 22, 2016, 12:11:48 AM
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
            
      function validateinput(){
       
          if(document.frm.WAREHOUSENO.value=="")
        {
           alert("Please Select WareHouse  ")
            document.frm.WAREHOUSENO.focus();
           return false;
        }
        if(document.frm.FACTORYCODE.value=="")
        {
           alert("Please Select Factory. ")
             document.frm.FACTORYCODE.focus();
           return false;
        }
         if(document.frm.BUYER.value=="")
        {
           alert("Please Select Buyer  ")
            document.frm.BUYER.focus();
           return false;
        }
        
        if(document.frm.PAYMENTMETHOD.value=="")
        {
           alert("Please Select Payment Method ");
           document.frm.PAYMENTMETHOD.focus();
           return false;
        }
         
         if(document.frm.CURRENCYCODE.value=="")
        {
           alert("Please Select Currency ");
           document.frm.CURRENCYCODE.focus();
           return false;
        }
         if(document.frm.SALEGLCODE.value=="")
        {
           alert("Please Select Sale GL Code ");
           document.frm.SALEGLCODE.focus();
           return false;
        }
        if (document.frm.CTN_TOTALvalue="" || document.frm.CTN_TOTAL.value==0)
           {
             alert("Please Enter Line Detail ");
             document.frm.SALEGLCODE.focus();
             return false;  
                
           }
           return true;
      }
     
         function validateLine()
    {  
       var ITEM_DESC=document.frm.ITEM_DESC_L;
       var PCH=document.frm.PCH_L; 
       var QNTY=document.frm.QNTY_L;
       var RATE=document.frm.RATE_L; 
       var UOM=document.frm.UOM_L; 
       var TAX_TYPE=document.frm.HSN_CODE_L; 
//       var TAX_GL=document.frm.CGST_PER_L; 
//       var TCSGL_CODE=document.frm.SGST_PER_L; 
//       var TCS=document.frm.IGST_PER_L;
      
        if(ITEM_DESC!=null)
            {     
                for(i=0; i<ITEM_DESC.length; i++)
                    { 
                     if(ITEM_DESC[i].value!="") 
                    {   
                      if(PCH[i].value=="")
                      {
                       alert("Please Select PCH ....");
                        return false;
                      }
                      if(QNTY[i].value=="")
                      {
                       alert("Please Enter Qnty....");
                        return false;
                      }   
                       h1=parseFloat(QNTY[i].value) ; 
                        
                       if (h1==0)
                       {   
                          alert("Please Enter Qnty ....");
                          return false;  
                       }
                        h1=parseFloat(RATE[i].value) ; 
                       if (h1==0)
                       { 
                          alert("Please Enter RATE ....");
                          return false;  
                       }
                      if(UOM[i].value=="")
                      {
                       alert("Please Enter UOM ....");
                        return false;
                      }  
                      if(RATE[i].value=="")
                      {
                       alert("Please Enter Rate ....");
                        return false;
                      }   
                    
                     
                      if (TAX_TYPE[i].value=="") 
                      { 
                          alert("Check Select HSN Code ");
                           return false;
                      }
//                      if (TAX_GL[i].value=="") 
//                      { 
//                          alert("Please Enter CGST .... ");
//                           return false;
//                      }TCS
//                      if (TCS[i].value!="" &&  TCSGL_CODE[i].value=="")
//                      {
//                          alert("Please Enter SGST and IGST ....");
//                           return false;  
//                      } 
                     }
                    } 
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
             function updtFactory(){
               WH=document.getElementById("WAREHOUSENO").value;
               url='newentryScrapSaleGSTA.action?Flg=YES&WHOUSE='+WH ;
               document.frm.action=url;
               document.frm.submit();
               //document.getElementById('prepage').style.visibility=''; 
             }
             
           function GstFun(){
               WH=document.getElementById("WAREHOUSENO").value;
               url='newentryScrapSaleGSTA.action?Flg=YES&F_CODE='+document.getElementById("FACTORYCODE").value+'&WHOUSE='+WH;
               document.frm.action=url;
               document.frm.submit();
           }
           
          
     
          
               function openHsn(frame, url, a, id, b,i)
               {
                                WAREHOUSENO1=document.getElementById("WAREHOUSENO").value;
                                FACTCODE1=document.getElementById("FACTCODE").value;
                                STATE_DESC1=document.getElementById("STATE_DESC").value;
                                url=url+'?indexname='+i+'&WAREHO1='+WAREHOUSENO1+'&FACTC1='+FACTCODE1+'&STATE1='+STATE_DESC1;
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

     function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
            		return false;
            	}
                totalctnqty();
            	return true;
                
            }
            
            
            
            
            
     function totalctnqty()
    {
   
    var PLAN_QNTY=document.frm.QNTY_L;
    
    var t=0; var t1=0; var t2=0; var t3=0; var t4=0; var t5=0;
    
    if(document.frm.QNTY_L)
        {  
          if(PLAN_QNTY.length>0)  
              {  
                  for(m=0; m<PLAN_QNTY.length; m++)
                      { 
                          if(!isNaN(eval(PLAN_QNTY[m].value))){
                            t=t+eval(PLAN_QNTY[m].value) 
                          
                           
                          }
                      }
              }else{
                   t=t+eval(PLAN_QNTY.value) 
                  
                   
              }
        } 
         document.getElementById('CTN_TOTAL').value=t.toFixed();
 }
 function totalfobqty()
{
   
    var PLAN_QNTY=document.frm.FOB_L;
    var t=0;
    if(document.frm.FOB_L)
        {
          if(PLAN_QNTY.length>0)  
              {
                  for(m=0; m<PLAN_QNTY.length; m++)
                      { 
                          if(!isNaN(eval(PLAN_QNTY[m].value)))
                          {
                         t=t+eval(PLAN_QNTY[m].value) ;
                          }
                      }
              }else{
                   t=t+eval(PLAN_QNTY.value) 
              }
        }
        
        document.getElementById('FOB_TOTAL').value=t.toFixed(2);
}

            
        function getdescbuyer(a)  
       {  
           document.getElementById('DISPLAY_ITEM_DESC').value=document.getElementById('ITEM_DESC'+a).value;
           document.getElementById('DISPLAY_BUYER').value=document.getElementById('BUYER_NAME'+a).value;
       }     
         function xmlhttpreqtax(id)
         {
             //alert(id);
            //var TAXC_L=dojo.widget.byId('AWBDATE').getValue();
            TAX_TYPE_L=document.getElementById('TAX_TYPE_L'+id).value;
           // BILLDATE=dojo.widget.byId('BILL_DATE'+id).getValue();
           
            
            var xmlHttpReq = false; 
            var self = this;
            if (window.XMLHttpRequest) { 
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {  
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            }
            self.xmlHttpReq.open('POST', 'ajaxInvScrapSaleGSTA', false); 
            self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            self.xmlHttpReq.onreadystatechange = function() {
                if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
                { 
                      var a = self.xmlHttpReq.responseText;
                      var b= new Array();
                      b=a.split('#');
                      if(b.length>3)
                      {
                            document.getElementById('TAX_PER_L'+id).value=b[0];
                           
                      }
                      else
                      {
                        if(b[0]=='Data Not Found')
                        {   
                            objsrc.value='';
                            document.getElementById('TAX_PER_L'+id).value='';
                        }
                      }
             }
            }
            self.xmlHttpReq.send("INVOICE_NO_S="+encodeURIComponent(INVOICE_NO)+"& AWBDATE_AJ="+encodeURIComponent(AWBDATE_L)+"& BILL_DATE_AJ="+encodeURIComponent(BILLDATE)+"&time="+(new Date()).getTime());
         }
         
              function fobtot(){
               var x = document.frm.QNTY_L;
               var y = document.frm.RATE_L;
               var z = document.frm.FOB_L;
                 if(x.length>0){
                    for (var i = 0; i < x.length; i++) {
                        
                        if(x[i].value>0 || y[i].value>0){
                          z[i].value= eval(x[i].value)*eval(y[i].value);
                            
                        } 
                        else{
                            
                        }
                    }
                 }
                 else{
                      if(x.value>0 || y.value>0){
                        z.value= eval(x.value)*eval(y.value);
                 }
                     }
                    totalfobqty();  
              }
              
                 function taxamttot(){
                    var x = document.frm.TAX_PER_L;
                    var y = document.frm.TAX_AMT_L;
                    var z = document.frm.FOB_L;
                 
                    if(z.length>0){
                    for (var i = 0; i < z.length; i++) {
                        if(x[i].value>0 || z[i].value>0){
                          y[i].value= eval(z[i].value)*eval(x[i].value)/100;
                        } 
                        else{
                        }
                    }
                    }
                    else{
                        if(x.value>0 || z.value>0){
                          y.value= eval(z.value)*eval(x.value)/100;
                    }
                    }  
                 }
                 
                 function tcsamttot(){
                    var x = document.frm.TCS_PER_L;
                    var y = document.frm.TCS_AMT_L;
                    var z = document.frm.FOB_L;
                    var z1=document.frm.TAX_AMT_L;
                  
                    if(z.length>0){
                    for (var i = 0; i < z.length; i++) {
                        if(x[i].value>0 || z[i].value>0){
                          y[i].value= (eval(z[i].value)+eval(z1[i].value))*eval(x[i].value)/100;
                        } 
                        else{
                        }
                    }
                    }
                    else{
                        if(x.value>0 || z.value>0){
                          y.value= (eval(z.value)+eval(z1[i].value))*eval(x.value)/100;
                    }
                    }  
                 }
              
              
          function delrec(){
                   if (confirm('Do You Want to Delete Record ?')) {
                        document.frm.action = "delScrapSaleGSTA.action";
                        document.frm.submit();
                    } 
         }

         
             function saverec()
               { 
                if(validateinput() && validateLine()){
                   
                    var CGST_PER_L=document.frm.CGST_PER_L; 
                    var SGST_PER_L=document.frm.SGST_PER_L; 
                    var IGST_PER_L=document.frm.IGST_PER_L;
                    var ITEM_DESC=document.frm.ITEM_DESC_L;
                          
                          
              if(document.getElementById("WAREHOUSENO").value==document.getElementById("STATE_DESC").value){
                          
                if(ITEM_DESC!=null)
                {     
                 for(i=0; i<ITEM_DESC.length; i++)
                    { 
                     if(ITEM_DESC[i].value!="") 
                    {   
                      if(CGST_PER_L[i].value=="" && SGST_PER_L[i].value=="")
                      {
                       alert("Please Select CGST and SGST ....");
                        return false;
                      }
                      else{
                        if(confirm("Do you want to update/save Records")){
                        document.frm.action="update1ScrapSaleGSTA.action?";
                        document.frm.submit();
                        document.getElementById('prepage').style.visibility='';
                        }  
                      }
                     }
                     }
                    }
                return true;
                }
             else{
               if(ITEM_DESC!=null)
                {     
                 for(i=0; i<ITEM_DESC.length; i++)
                    { 
                     if(ITEM_DESC[i].value!="") 
                    { 
                        if(IGST_PER_L[i].value=="")
                          {
                           alert("Please Select IGST ....");
                            return false;
                          }
                          else{
                            if(confirm("Do you want to update/save Records")){
                            document.frm.action="update1ScrapSaleGSTA.action?";
                            document.frm.submit();
                            document.getElementById('prepage').style.visibility='';
                            } 
                            return true;
                          }

                   }
                }
             }
             }
                }
               }
                
         function GstFun1(objsrc)
                { 
                    
                   var xmlHttpReq = false; 
                   var self = this; 

                   if (window.XMLHttpRequest) { 
                       self.xmlHttpReq = new XMLHttpRequest();   
                   } else if (window.ActiveXObject) {  
                       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                   }

                   self.xmlHttpReq.open('POST', 'ajaxGSTCODEAjaxAction', false); 
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
<!--        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>-->
        <form action=""   method="post" name="frm" >
            
            <s:hidden id="FINAL_P" name="FINAL_P" value="%{FINAL_P}"/>
            <s:hidden id="pchlist" name="pchlist" value="%{pchlist}"/>
            <s:hidden id="" name="" value=""/>
            
            
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Scrap Sale GST</td></tr>
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
                                                   <s:if test="%{FLAG2=='TXT'}">
                                                       <s:textfield name="WAREHOUSENO" id="WAREHOUSENO"  value="%{WAREHOUSENO}" readonly="true" cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                   </s:if>
                                                   <s:else>
                                                       <s:select name="WAREHOUSENO" id="WAREHOUSENO"   headerValue=""  headerKey=""  value="%{WAREHOUSENO}" list="%{whouselist}"  cssStyle="text-transform:uppercase;width:80pt" theme="simple" onchange="updtFactory();"/>
                                                   </s:else>
                                                       
                                               </td>   
                                                
                                                  <td class="label-1">Factory</td><td>
                                                         <s:select name="FACTORYCODE" id="FACTORYCODE" value="%{FACTORYCODE}"  headerValue=""  headerKey=""  listKey="CODE" listValue="DESC" list="%{unitlist}" cssStyle="text-transform:uppercase;width:200px" theme="simple" onchange="GstFun1(this);"/>
                                                         <s:textfield name="FACTCODE" id="FACTCODE"  value="%{FACTCODE}" readonly="true" cssClass="textreadonly" cssStyle="text-transform:uppercase;width:60pt" theme="simple" />
                                                         </td>  
                                                <td></td>
                                                 
                                                <td align="right" >
                                                     <button  id="searchheadId" class="sexybutton" onclick="onbackedit('ScrapSaleGSTA.action')"><span><span><span class="undo" id="searchId">Back</span></span></span></button>
                                                    
                                                        <s:if test="%{FINAL_P!=null && FINAL_P!=''}">
                                                           <button  id="saveheadId"  class="sexybutton" onclick="saverec();" disabled="true" ><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                        </s:if>
                                                        <s:else>
                                                         <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                        </s:else>
                                                   
                                                     <button  id="saveheadId"  class="sexybutton" onclick=""><span><span><span class="delete" id="saveId" >Delete</span></span></span></button>
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
                                         
                                           theme="simple" onblur="if(this.value!='') xmlhttpreqbuyer(this,'BUYER_DESC')" 
                                           tabindex="1"/><a href="buyersearchGST.jsp" target="addapprofrm" onclick="openpop1('approveraddid')"><img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                        </td>
                                      
                                        
                                        <td class="label-1">Buyer Name</td>
                                  <td colspan="2">
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
                                       <input type="button" class="texts" style="width:30px;" onclick="alert(document.getElementById('address').value)" name="addressno" id="addressno" value="<s:property value="%{BUYER_ADDR}"/>"/>
                                       <input type="hidden" name="address" value="<s:property value="%{BUYER_ADDRESS_NAME}"/>" id="address"/>  
                                  
                                  </td>
                                   
                                     <s:hidden name="YEAR" id="YEAR" value="%{YEAR}"/>      
                                     <s:hidden name="COMPANY" id="COMPANY" value="%{COMPANY}"/>      
                                     <s:hidden name="INV_NO" id="INV_NO" value="%{INV_NO}"/>      
                                     <s:hidden name="BUYER_ADDR" id="BUYER_ADDR" value="%{BUYER_ADDR}"/>      
                                                
 
                                                </tr>
                                               <tr> 
                                                   <td class="label-1">Currency</td><td>
                                                            <s:textfield name="CURRENCYCODE" id="CURRENCYCODE" value="INR" readonly="true" cssStyle="text-transform:uppercase;width:80pt" cssClass="textreadonly" theme="simple" onKeyPress="tabE(this, event);" />
                                                       
                                                   </td> 
                                               <td class="label-1">Payment Method</td>
                                                <td><s:select name="PAYMENTMETHOD" id="PAYMENTMETHOD" value="%{PAYMENTMETHOD}" headerKey="" headerValue="Select" listKey="CODE" listValue="DESC" list="%{paymentmethodlist}" cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/><br/>
                                               </td>
                                                
                                                <td class="label-1">Sale Gl Code</td><td>
                                                             <s:select  headerValue="" list="# {'':'','32100':'32100','32200':'32200','32300':'32300','35308':'35308','32400':'32400','32600':'32600','35304':'35304'}"  cssStyle="font-size:10pt;width:200px;font-weight:bold; " theme="simple" name="SALEGLCODE" id="SALEGLCODE" value="%{SALEGLCODE}" /> 
                                                </td> 
                                                <td></td> 
                                                <td class="label-1">Final Print
                                                  
                                                    <s:if test="%{FINAL_P!=null && FINAL_P!=''}">
                                                        <s:checkbox name="FINAL_CHK" id="FINAL_CHK" checked="true"  theme="simple" disabled="true" />  
                                                        <s:property value="%{FINAL_P}"/>
                                                    </s:if>
                                                    <s:else>
                                                           
                                                            <input type="checkbox" name="FINAL_CHK" value="Y"/>
                                                    </s:else>
                                                </td>
                                                 
                                                </tr>
                                                <tr>
                                                    <td class="label-1">Remarks </td><td colspan="3">
                                                            <s:textfield name="REM" id="REM" value="%{REM}"  cssStyle="text-transform:uppercase;width:335pt"   theme="simple" onKeyPress="tabE(this, event);" />
                                                       
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
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr   style="background-color: whitesmoke;height: 300pt;">
                                    <td>
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:300pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left">Item Desc</th>
                                                    <th style="font-size:8pt" align="left">PCH</th>
                                                    <th style="font-size:8pt" align="right">Qnty</th>
                                                    <th style="font-size:8pt" align="center">UOM</th>
                                                    <th style="font-size:8pt" align="right">Rate</th>
                                                    <th style="font-size:8pt" align="right">FOB</th>
                                                    <th style="font-size:8pt" align="left" colspan="">HSN Code &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                                    <th style="font-size:8pt" align="left" colspan=""> %</th>
                                                    <th style="font-size:8pt" align="right">CGST %</th>
                                                    <th style="font-size:8pt" align="left">CGST GL</th>
                                                    <th style="font-size:8pt" align="right">SGST %</th>
                                                    <th style="font-size:8pt" align="left">SGST GL</th>
                                                    <th style="font-size:8pt" align="right">IGST %</th>
                                                    <th style="font-size:8pt" align="left">IGST GL</th>
                                                    <th style="font-size:8pt" align="left">TCS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                                    <th style="font-size:8pt" align="left">TCS GL</th>
                                                    <th style="font-size:8pt" align="right">%</th>
                                                    
                                                   
                                                  </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                              
                                                           
                                                      <s:set id="ctn" name="ctn" value="0"/>      
                                                      <s:iterator value="%{listdata}" status="st1">
                                                      <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#ctn}" /></td>
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="ITEM_DESC_L%{#ctn}" name="ITEM_DESC_L" value="%{ITEMDESC}" cssStyle="width:200px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:select id="PCH_L%{#ctn}" name="PCH_L" value="%{PCH}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{pchlist}" cssStyle="width:100%;text-align:left" theme="simple" /></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="QNTY_L%{#ctn}" name="QNTY_L" value="%{QNTY}" cssStyle="width:100%;text-align:right" theme="simple"  onblur="validatenumber(this) " /></td>
                                                            <td style="font-size:8pt;text-align: right"><s:select id="UOM_L%{#ctn}" name="UOM_L" value="%{UOM}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{uomlist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="RATE_L%{#ctn}" name="RATE_L" value="%{RATE}" cssStyle="width:100%;text-align:left" theme="simple" onblur="fobtot();" /></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="FOB_L%{#ctn}" name="FOB_L" value="%{FOB}" readonly="true" cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left"><s:textfield id="HSN_CODE_L%{#ctn}" name="HSN_CODE_L" value="%{HSCODE}" cssStyle="width:80%;text-align:left" theme="simple" /> 
                                                                
                                                                <a href="#" onclick="openHsn('addapprofrm2','taxsearchScrapSaleGSTA.action','approveraddid2','handlediv','Hsn Detail','<s:property value="%{#ctn}"/>');"><img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                  
                                                            </td>
                                                            <td style="font-size:8pt;text-align:left"><s:textfield id="HSN_CODE_PER_L%{#ctn}" name="HSN_CODE_PER_L" value="%{HSNPER}" cssStyle="width:100%;text-align:left" theme="simple" readonly="true"/> 
                                                           <td style="font-size:8pt;text-align: right"><s:textfield id="CGST_PER_L%{#ctn}" name="CGST_PER_L" value="%{BCGST_PER}" cssStyle="width:100%;text-align:right;" theme="simple" /></td>
                                                           <td style="font-size:8pt;text-align: right"><s:select id="CGST_CODE_L%{#ctn}" name="CGST_CODE_L" value="%{BCGST_GL}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{CGSTgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right"><s:textfield id="SGST_PER_L%{#ctn}" name="SGST_PER_L" value="%{BSGST_PER}" cssStyle="width:100%;text-align:right" theme="simple" onclick="taxamttot();" /></td>
                                                           <td style="font-size:8pt;text-align: right"><s:select id="SGST_CODE_L%{#ctn}" name="SGST_CODE_L" value="%{BSGST_GL}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{SGSTgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right"><s:textfield id="IGST_PER_L%{#ctn}" name="IGST_PER_L" value="%{BIGST_PER}" cssStyle="width:100%;text-align:left" theme="simple" /></td>
                                                           <td style="font-size:8pt;text-align: right"><s:select id="IGST_CODE_L%{#ctn}" name="IGST_CODE_L" value="%{BIGST_GL}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{IGSTgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: left"><s:textfield id="TCS_L%{#ctn}" name="TCS_L" value="%{TCS}" cssStyle="width:70%;text-align:left" theme="simple"/> 
                                                          
                                                                    <a href="tcssearchScrapSaleGSTA.action?indexname=<s:property value="%{#ctn}"/>" class="search"  target="addapprofrm2"  onclick="openpop('approveraddid2');" ><img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                
                                                            </td> 
                                                            <td style="font-size:8pt;text-align: right"><s:select id="TCSGL_CODE_L%{#ctn}" name="TCSGL_CODE_L" value="%{TCSGL}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{TCSgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right"><s:textfield id="TCS_PER_L%{#ctn}" name="TCS_PER_L" value="%{TCSPER}" cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                           
                                                           <s:hidden id="INVNO" name="INVNO" value="%{INVNO}"/>
                                                              
                                                          </tr>
                                                           <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                       </s:iterator>
                                                
                                                    
                                                        
                                                        <s:iterator begin="%{#ctn}" end="30" status="st2">      
                                                         <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#ctn}" /></td>
                                                            <td style="font-size:8pt;text-align: right;width:200px;"><s:textfield id="ITEM_DESC_L%{#ctn}" name="ITEM_DESC_L" value="%{ITEM_DESC_L}" cssStyle="width:200px;text-align:left;text-transform: uppercase" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right;width:60px;"><s:select id="PCH_L%{#ctn}" name="PCH_L" value="%{PCH_L}"  headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{pchlist}" cssStyle="width:100%;text-align:left" theme="simple" /></td>
                                                            <td style="font-size:8pt;text-align: right;width:60px;"><s:textfield id="QNTY_L%{#ctn}" name="QNTY_L" value="%{QNTY_L}"  cssStyle="width:100%;text-align:right" theme="simple" onblur="totalctnqty()"/></td>
                                                            <td style="font-size:8pt;text-align: right;width:60px;"><s:select id="UOM_L%{#ctn}" name="UOM_L" value="%{UOM_L}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{uomlist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right;width:60px;"><s:textfield id="RATE_L%{#ctn}" name="RATE_L" value="%{RATE_L}" cssStyle="width:100%;text-align:right" theme="simple" onblur="fobtot();"/></td>
                                                            <td style="font-size:8pt;text-align: right;width:60px;"><s:textfield id="FOB_L%{#ctn}" name="FOB_L" value="%{FOB_L}" cssStyle="width:100%;text-align:right" theme="simple" readonly="true"/> </td>
                                                           <td style="font-size:8pt;text-align: left"><s:textfield id="HSN_CODE_L%{#ctn}" name="HSN_CODE_L" value="%{HSN_CODE_L}" cssStyle="width:80%;text-align:left" theme="simple" /> 
                                                             
                                                             <a href="#" onclick="openHsn('addapprofrm2','taxsearchScrapSaleGSTA.action','approveraddid2','handlediv','Hsn Detail','<s:property value="%{#ctn}"/>');"><img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                 
                                                            </td>
                                                            <td style="font-size:8pt;text-align: left"><s:textfield id="HSN_CODE_PER_L%{#ctn}" name="HSN_CODE_PER_L" value="%{HSN_CODE_PER_L}" cssStyle="width:100%;text-align:left" theme="simple" readonly="true"/> 
                                                             <td style="font-size:8pt;text-align: right"><s:textfield id="CGST_PER_L%{#ctn}" name="CGST_PER_L" value="%{CGST_PER_L}" cssStyle="width:100%;text-align:right;" theme="simple" /></td>
                                                             <td style="font-size:8pt;text-align: right"><s:select id="CGST_CODE_L%{#ctn}" name="CGST_CODE_L" value="%{CGST_CODE_L}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{CGSTgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right"><s:textfield id="SGST_PER_L%{#ctn}" name="SGST_PER_L" value="%{SGST_PER_L}" cssStyle="width:100%;text-align:right" theme="simple" onclick="taxamttot();" /></td>
                                                           <td style="font-size:8pt;text-align: right"><s:select id="SGST_CODE_L%{#ctn}" name="SGST_CODE_L" value="%{SGST_CODE_L}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{SGSTgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right"><s:textfield id="IGST_PER_L%{#ctn}" name="IGST_PER_L" value="%{IGST_PER_L}" cssStyle="width:100%;text-align:left" theme="simple" /></td>
                                                           <td style="font-size:8pt;text-align: right"><s:select id="IGST_CODE_L%{#ctn}" name="IGST_CODE_L" value="%{IGST_CODE_L}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{IGSTgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: left"><s:textfield id="TCS_L%{#ctn}" name="TCS_L" value="%{TCS_L}" cssStyle="width:70%;text-align:left" theme="simple"/> 
                                                          
                                                                    <a href="tcssearchScrapSaleGSTA.action?indexname=<s:property value="%{#ctn}"/>" class="search"  target="addapprofrm2"  onclick="openpop('approveraddid2');" ><img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                
                                                            </td>
                                                            <td style="font-size:8pt;text-align: right"><s:select id="TCSGL_CODE_L%{#ctn}" name="TCSGL_CODE_L" value="%{TCSGL_CODE_L}" headerKey="" headerValue="" listKey="CODE" listValue="DESC" list="%{TCSgllist}" cssStyle="width:100%;text-align:left" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right"><s:textfield id="TCS_PER_L%{#ctn}" name="TCS_PER_L" value="%{TCS_PER_L}" cssStyle="width:100%;text-align:right" theme="simple"/></td>
                                                           
                                                         </tr>
                                                          <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>    
                                                       </s:iterator>
                                                       
                                                </tbody>
                                            </table>
                                        </div> 
                            <table cellpadding="0" cellspacing="1">
                          
                            <tr  style="background-color: #cccccc;text-align: left" >
                               <th class="label-1"   style="width:100px"></th>
                               <th class="label-1"   style="width:100px"></th>
                               <th class="label-1"   style="width:75px;text-align:right">Total Qty#</th>
                               <th class="label-1" style="height:25px;width: 70px" >
                                  <s:textfield name="CTN_TOTAL" 
                                           id="CTN_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{CTN_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>    
                                </th> 
                                 <th class="label-1"   style="width:100px;text-align:right">Total Fob #</th>
                                 <th class="label-1" style="height:25px;width: 80px" >
                                  <s:textfield name="FOB_TOTAL" 
                                           id="FOB_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FOB_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>  
                                </th>
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
        
        <div id="approveraddid2" class="root1" style="left:50px; top:100px;display:none">
            <table cellpadding="0" cellspacing="0">
                <tr bgcolor="#000080">
                    <td width="100%">
                        <div id="handlediv" class="handle1" style="cursor: move">HSN Code List</div>
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
            
                var approveraddid1 = document.getElementById("approveraddid");
                var report = document.getElementById('Report');
                Drag.init(report, approveraddid1);
                
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