

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="../js/dom-drag.js"></script>
<html> 
    <head>
        <s:head />
        <sx:head />
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
            

   function validateinput()
            {   
               if(document.frm.FITT_NO.value=="" )
               {
                     alert("Please enter FITT Number ")
                     document.frm.FITT_NO.focus();
                    return false;
                } 
                if(document.frm.FITT_DATE.value=="" )
               {
                     alert("Please enter FITT_DATE  ")
                     document.frm.FITT_DATE.focus();
                    return false;
                } 
              
                  if(document.frm.BUYER.value=="" )
                 {
                     alert("Please enter BUYER  ")
                     document.frm.BUYER.focus();
                    return false;
                } 
               if(document.frm.FOB_AMT.value=="" )
               {
                     alert("Please enter Fob Amt  ")
                     document.frm.FOB_AMT.focus();
                    return false;
                } 
                 if(document.frm.CRNCY_CODE.value=="" )
               {
                     alert("Please enter Fob CRNCY_CODE  ")
                     document.frm.CRNCY_CODE.focus();
                    return false;
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
     
     function openpop(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv(a)
            {
                document.getElementById(a).style.display = 'none';
            }
  
     
  
function searchinv(objsrc,objtrg,index)
{ 
     
   
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxFittInvPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
             if(b.length>12)
             {    
                 document.getElementById('handlefrm').src="viewInvbillofsalesAction.action?unitparam="+objsrc.value+"&PARAA="+index;
                 openpop('root');
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
                 document.getElementById('handlefrm').src="viewInvbillofsalesAction.action?PARAA="+index;
                 openpop('root');
              }
               else
               {
                   //objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                   document.getElementById('INVBUYER'+index).value=b[0];
                   document.getElementById('PCH'+index).value=b[1];
                   document.getElementById('INV_DATE'+index).value=b[2];
                   document.getElementById('TO_DATE'+index).value=b[3];
                   document.getElementById('ETD_DATE'+index).value=b[4];
                   document.getElementById('FTP_DATE'+index).value=b[5];
                   document.getElementById('AWB_DATE'+index).value=b[6];
                   document.getElementById('FIN_DATE'+index).value=b[7];
                   document.getElementById('CRNCY'+index).value=b[8];
                   document.getElementById('INV_QTY'+index).value=b[9];
                   document.getElementById('FOB_FC'+index).value=b[10];
                   document.getElementById('GR_DECL'+index).value=b[11];
                   document.getElementById('NET_FOB'+index).value=b[10]-b[11];
                  
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
    
}
    
   function totalval()
    {
     var FOB=document.frm.FOB_FC;
     var GR=document.frm.GR_DECL;
     var SQTY=document.frm.INV_QTY;
     var NET=document.frm.NET_FOB;

     var t=0; var t1=0;  t2=0; t3=0; t4=0;
         if(document.frm.FOB_FC)
        {    
          if(FOB.length>0)  
              {    
                  for(m=0; m<FOB.length; m++)
                      {  
                          if(!isNaN(eval(FOB[m].value))){
                            t=t+eval(FOB[m].value) ;
                            t1=t1+eval(GR[m].value);
                            t2=t2+eval(NET[m].value);
                            t3=t3+eval(SQTY[m].value);
                            
                        }
                      }
              }else{
                   
                             
                   t=t+eval(FOB.value) ;
                   t1=t1+eval(GR.value);
                   t2=t2+eval(NET.value) ;
                   t3=t3+eval(SQTY.value) ;
               }
        }  
         document.getElementById('TOTFOB').value=t.toFixed(2);
         document.getElementById('TOTGR').value=t1.toFixed(2);
         document.getElementById('TOTNET').value=t2.toFixed(2);
         document.getElementById('TOTQTY').value=t3.toFixed();
          
       }
          
             function saverec()
            {   
                if (validateinput())
               {
                document.frm.action="FITTENTRY.action?saveFlag='Yes'";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
               }
            }
             function updrec()
            {   
                if (validateinput())
               {
                document.frm.action="FITTENTRY.action?updFlag='Yes'";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
               }
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
             function onbackedit(url) {
                    document.frm.action = url;
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility = '';
                }
        </script>

    </head>

    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
        
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    FIIT Entry</td></tr>
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
                                                <s:if test="%{UPDFITT==null}">
                                                     <td class="label-1" style="width:300px" align="left">Fitt No : &nbsp;
                                                         <s:textfield name="FITT_NO" value="%{UPDFITT}" cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                     </td> 
                                                     <td class="label-1" align="left" style="width:300px">Fitt Date: 
                                                         <sj:datepicker name="FITT_DATE" id="FIIT_DATE"  value="%{MIN_DATE}"  maxDate="%{MIN_DATE}" cssStyle="width:100px;" displayFormat="dd.M.yy" buttonImageOnly="true"></sj:datepicker>
                                                     </td> 
                                                     <td class="label-1"  align="left" style="width:300px" >Buyer: 
                                                              <s:textfield name="BUYER"  cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                     </td> 
                                                     <td class="label-1" style="width:300px">Fob Amt: <s:textfield name="FOB_AMT" value="%{FOB_AMT}" cssStyle="width:70pt" theme="simple"   onblur="validatenumber(this) " />
                                                     </td> 
                                                     <td class="label-1" style="width:300px">Adv Amt: <s:textfield name="ADV_AMT" value="%{ADV_AMT}" id="ADV_AMT" cssStyle="width:70pt" theme="simple" onKeyPress="tabE(this, event);" onblur="validatenumber(this) "/>
                                                     </td>
                                                     <td></td>
                                                </s:if>
                                                <s:else>
                                                     <td class="label-1" style="width:300px" align="left">Fitt No : &nbsp;
                                                         <s:textfield name="FITT_NO" value="%{UPDFITT}" readonly="true"  cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                     </td> 
                                                     <td class="label-1" align="left" style="width:300px">Fitt Date: 
                                                         <s:textfield name="FITT_DATE" value="%{FITT_DATE}" readonly="true"  cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                      </td> 
                                                     <td class="label-1"  align="left" style="width:300px" >Buyer: 
                                                              <s:textfield name="BUYER" readonly="true" value="%{BUYER1}" cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                     </td> 
                                                     <td class="label-1" style="width:300px">Fob Amt: <s:textfield name="FOB_AMT" value="%{FOB_AMT}" readonly="true" cssStyle="width:70pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                     </td> 
                                                     <td class="label-1" style="width:300px">Adv Amt: <s:textfield name="ADV_AMT" value="%{ADV_AMT}" readonly="true"  cssStyle="width:70pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                     </td>
                                                     <td></td>
                                               </s:else>
                                            </tr>
                                            <tr> 
                                                <s:if test="%{UPDFITT==null}"> 
                                                      <td class="label-1" style="width:300px">Currency:<s:select name="CRNCY_CODE" id="CRNCY_CODE" list="%{CURLIST}" headerKey="" headerValue="" cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                      </td>      
                                                      <td class="label-1" colspan="5"  >Remarks: <s:textfield name="REM1" value="%{REM1}" cssStyle="text-transform:uppercase;width:410pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                      </td>    
                                              </s:if>
                                              <s:else>
                                                  <s:hidden name="UPDFITT" value="%{UPDFITT}" />
                                                       <td class="label-1" style="width:300px">Currency:<s:textfield name="CRNCY_CODE" readonly="true" value="%{CRNCY_CODE1}" cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                      </td>      
                                                      <td class="label-1" colspan="5"  >Remarks: <s:textfield name="REM1" value="%{REM1}" readonly="true" cssStyle="text-transform:uppercase;width:410pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                      </td> 
                                              </s:else>
                                                <td align="right">  
                                                  <button  id="searchheadId" class="sexybutton" onclick="onbackedit('FITTENTRY.action')"><span><span><span class="undo" id="searchId">Back</span></span></span></button>
                                                  <s:if test="%{UPDFITT==null}">    
                                                    <button  id="saveheadId"  class="sexybutton" onclick="saverec();"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                  </s:if>
                                                  <s:else>
                                                     <button  id="saveheadId"  class="sexybutton" onclick="updrec();"><span><span><span class="save" id="saveId" >Update</span></span></span></button> 
                                                  </s:else>
                                                      <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
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
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="0" cellspacing="0" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:300pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="1" align="center" bgcolor="#646D7E" cellspacing="0" cellpadding="1" width="100%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                    <th style="font-size:8pt" align="left" >Sl#</th>
                                                    <th style="font-size:8pt" align="left">Invoice No.</th>
                                                    <th style="font-size:8pt" align="left">Buyer</th>
                                                    <th style="font-size:8pt" align="left">PCH</th>
                                                    <th style="font-size:8pt" align="left">Inv Date</th>
                                                    <th style="font-size:8pt" align="left">TO Date</th>
                                                    <th style="font-size:8pt" align="left">ETD Date</th>
                                                    <th style="font-size:8pt" align="left">FTP Date</th>
                                                    <th style="font-size:8pt" align="left">Awb Date</th>
                                                    <th style="font-size:8pt" align="left">FIN Date</th>
                                                    <th style="font-size:8pt" align="right">Inv Qnty</th>
                                                    <th style="font-size:8pt" align="left">Currency</th>
                                                    <th style="font-size:8pt" align="right">Fob Amt</th>
                                                    <th style="font-size:8pt" align="right">GR Decl</th>
                                                    <th style="font-size:8pt" align="right">Net Fob</th>
                                                     
                                                    </tr>                                                
                                                   </thead>
                                                  <tbody>  
                                                      <s:set id="ctn" name="ctn" value="0"/> 
                                                      <s:iterator value="%{INVLST}" status="st1">
                                                      <tr bgcolor="#f2f9fb"> 
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index}" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="EXCS_INV_NO%{#ctn}" readonly="true" name="EXCS_INV_NO" value="%{EXCS_INV_NO}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="INVBUYER%{#ctn}" cssClass="textreadonly"  readonly="true" name="INVBUYER" value="%{BUYER}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="PCH%{#ctn}" cssClass="textreadonly"  readonly="true" name="PCH" value="%{PCH}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="INV_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="INV_DATE" value="%{INV_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="TO_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="TO_DATE" value="%{TO_DATE}" cssStyle="width:100%;text-align:left" theme="simple"     tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="ETD_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="ETD_DATE" value="%{ETD_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="FTP_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="FTP_DATE" value="%{FTP_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="AWB_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="AWB_DATE" value="%{AWB_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="FIN_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="FIN_DATE" value="%{FIN_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="INV_QTY%{#ctn}" cssClass="textreadonly"  readonly="true" name="INV_QTY" value="%{INV_QTY}" cssStyle="width:100%;text-align:right" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="CRNCY%{#ctn}" cssClass="textreadonly"  readonly="true" name="CRNCY" value="%{CRNCY_CODE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="FOB_FC%{#ctn}" cssClass="textreadonly"  readonly="true" name="FOB_FC" value="%{FOB_FC}" cssStyle="width:100%;text-align:right" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="GR_DECL%{#ctn}" cssClass="textreadonly"  readonly="true" name="GR_DECL" value="%{GR_DECL}" cssStyle="width:100%;text-align:right" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="NET_FOB%{#ctn}" cssClass="textreadonly"  readonly="true" name="NET_FOB" value="%{NET_FOB}" cssStyle="width:100%;text-align:right" theme="simple"  tabindex="-1" /></td>
                                                         
                                                         </tr>
                                                         <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                       </s:iterator>
                                                       <s:iterator begin="%{#ctn}" end="100" status="st">
                                                           <tr style="background-color: #FFFFFF">
                                                            <td style="font-size:8pt"><s:property value="%{#ctn}" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="EXCS_INV_NO%{#ctn}"  name="EXCS_INV_NO" value="%{EXCS_INV_NO}" cssStyle="width:100%;text-align:left" theme="simple" onblur="if(this.value!='') searchinv(this,'EXCS_INV_NO%{#ctn}','%{#ctn}');totalval();"  tabindex="1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="INVBUYER%{#ctn}" cssClass="textreadonly"  readonly="true" name="INVBUYER" value="%{BUYER}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="PCH%{#ctn}" cssClass="textreadonly"  readonly="true" name="PCH" value="%{PCH}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="INV_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="INV_DATE" value="%{INV_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="TO_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="TO_DATE" value="%{TO_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="ETD_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="ETD_DATE" value="%{ETD_DATE}" cssStyle="width:100%;text-align:left" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="FTP_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="FTP_DATE" value="%{FTP_DATE}" cssStyle="width:100%;text-align:left" theme="simple" tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="AWB_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="AWB_DATE" value="%{AWB_DATE}" cssStyle="width:100%;text-align:left" theme="simple" tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="FIN_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="FIN_DATE" value="%{FIN_DATE}" cssStyle="width:100%;text-align:left" theme="simple" tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="INV_QTY%{#ctn}" cssClass="textreadonly"  readonly="true" name="INV_QTY" value="%{INV_QTY}" cssStyle="width:100%;text-align:right" theme="simple" tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="CRNCY%{#ctn}" cssClass="textreadonly"  readonly="true" name="CRNCY" value="%{CRNCY_CODE}" cssStyle="width:100%;text-align:left" theme="simple" tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="FOB_FC%{#ctn}" cssClass="textreadonly"  readonly="true" name="FOB_FC" value="%{FOB_FC}" cssStyle="width:100%;text-align:right" theme="simple"  tabindex="-1" /></td>
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="GR_DECL%{#ctn}" cssClass="textreadonly"  readonly="true" name="GR_DECL" value="%{GR_DECL}" cssStyle="width:100%;text-align:right" theme="simple"  tabindex="-1" /></td>
                                                             <td style="font-size:8pt;text-align: right;"><s:textfield id="NET_FOB%{#ctn}" cssClass="textreadonly"  readonly="true" name="NET_FOB" value="%{NET_FOB}" cssStyle="width:100%;text-align:right" theme="simple"  tabindex="-1" /></td>
                                                         
                                                           </tr>
                                                            <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>   
                                                             
                                                         </s:iterator>

                                                </tbody>
                                            </table>
                                        </div>  
                                      </td>
                                 </tr>
                                    </table>  
                                  <table border="1" align="center" bgcolor="#646D7E" cellspacing="0" cellpadding="1" width="100%">
                                      <tr>
                                          <td width="20px"></td><td width="200px"></td><td width="200px"></td><td width="200px"></td ><td width="200px"></td ><td width="200px"> </td><td width="200px"></td><td width="200px"></td><td width="200px"></td>
                                                   <s:text name="product.cost" id="tqty" >
                                                       <s:param  name="value" value="%{TOTQTY}"/>
                                                   </s:text>
                                                        <td style="font-size:8pt;text-align: left;" ><s:textfield  name="TOTQTY1" id="TOTQTY" value="%{tqty}" cssStyle="width:100px;text-align:right;font-weight:bold;color:red;background-color:yellow;" readonly="true"  theme="simple"/></td>
                                                        <td width="100px"></td >
                                                        <s:text name="product.cost" id="tfob" >
                                                              <s:param  name="value" value="%{TOTFOB}"/>
                                                        </s:text>
                                                          <s:text name="product.cost" id="tgr" >
                                                              <s:param  name="value" value="%{TOTGR}"/>
                                                        </s:text>
                                                       <s:text name="product.cost" id="tnet" >
                                                              <s:param  name="value" value="%{TOTNET}"/>
                                                        </s:text>
                                                
                                                       <td style="font-size:8pt;text-align: right;"  ><s:textfield  name="TOTFOB1" id="TOTFOB" value="%{tfob}" cssStyle="width:100px;text-align:right;font-weight:bold;color:red;background-color:yellow;" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                      <td style="font-size:8pt;text-align: right;"><s:textfield  name="TOTGR1" id="TOTGR" value="%{tgr}" cssStyle="width:80px;text-align:right;font-weight:bold;color:red;background-color:yellow;" readonly="true"  theme="simple"/></td>
                                                      <td style="font-size:8pt;text-align: right;"><s:textfield  name="TOTNET1" id="TOTNET" value="%{tnet}" cssStyle="font-size:8pt;width:100px;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;" readonly="true"   theme="simple"/></td>
                                                   <td width="10px"></td>
                                          
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
                        <div id="Report" class="handle1" style="cursor: move">Invoice List</div>
                    </td>
                    <td style="10px"><a href="#" onclick="closediv('approveraddid')"><img border="0" width="18px" height="18px" src="../../css/image/divclose.gif"/></a>
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
                        </div>
                    </td></tr>
            </table>  
        </form> 
    </body>
</html>