<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<script type="text/javascript" src="js/dom-drag.js"></script>

 
<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
 
<html> 
    <head> 
        
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
            
   if (typeof window.event != 'undefined') // IE
    document.onkeydown = function() // IE
    {

       if(event.keyCode==13)
          {event.keyCode=9}


        var t=event.srcElement.type;
        var kc=event.keyCode;


        return ((kc != 8 && kc != 13) || ( t == 'text' &&  kc != 13 ) ||
            (t == 'textarea') || ( t == 'submit' &&  kc == 13))
    }
else
    document.onkeypress = function(e)  // FireFox/Others
    {
        return tabOnEnter(e.target,e)

        aaaa=e.keyCode;
       if(aaaa==13 )
          {

        
      }
        return true;
  
    }
                 
     
        function validateinput(){
            
            
            if ((document.getElementById("SDBK").value-document.getElementById("CDBK").value)>200 || (document.getElementById("SDBK").value-document.getElementById("CDBK").value)<-200)
              { alert("Check DBK Value & Charge Dbk  Mismatch  ")
               return false;
              }
           
              if ((document.getElementById("SSTR").value-document.getElementById("CSTR").value)>200 || (document.getElementById("SSTR").value-document.getElementById("CSTR").value)<-200)
              { alert("Check STR Value & Charge STR  Mismatch  ")
               return false;
              }  
             
           if ((document.getElementById("SROSL").value-document.getElementById("CROSL").value)>200 || (document.getElementById("SROSL").value-document.getElementById("CROSL").value)<-200)
              { alert("Check ROSL Value & Charge ROSL  Mismatch  ")
               return false;
              }   
         
         if(document.frm.FBANK.value=="" || document.frm.FBANK_ADDNO.value=="")
        {
           alert("Please Enter Fin.Bank  ")
            document.frm.FBANK.focus();
           return false;
        }
       
       if(document.frm.PAYTERM.value=="")
        {
           alert("Please Select Payment Term  ")
            document.frm.PAYTERM.focus();
           return false;
        }
         
           return true;
      }
         function validateYEAR()
         {  
               var x = document.frm.YEAR;
               var x1 = document.frm.CRNCY;
               var x2 = document.frm.CHKLC;
               var x3 = document.frm.LINK_NO;
              /*  for (var i = 0; i < x.length; i++) {
                    for (j = i + 1; j < x.length; j++) {
                        if (x[i].value > 0 && x[j].value>0 ) {
                            
                            if (x[i].value != x[j].value) {
                                alert(" Multiple Invoice Year..");
                                 return false;
                            }
                        }
                    }
                }
                */   
                for (var i = 0; i < x3.length; i++) {
                    for (j = i + 1; j < x3.length; j++) {
                        if (x3[i].value !='' && x3[j].value!='' ) {
                             if (x3[i].value==x3[j].value) {
                                alert(" Duplicate Awb..");
                                 return false;
                            }
                        }
                    }
                }
            
                  for (var i = 0; i < x1.length; i++) {
                    for (j = i + 1; j < x1.length; j++) {
                        if (x1[i].value !='' && x1[j].value!='' ) {
                             
                            if (x1[i].value != x1[j].value) {
                                alert(" MultiplE Crncy Code ..");
                                 return false;
                            }
                        }
                    }
                } 
               
                   for (var i = 0; i < x2.length; i++) {
                    for (j = i + 1; j < x2.length; j++) {
                        
                        if (x2[i].value !='' && x2[j].value!='' ) {
                           
                            if (x2[i].value != x2[j].value) {
                                alert(" MultiplE LC No ..");
                                 return false;
                            }
                        }
                    }
                }
                
                return true;
            }
      
           function checkLink()
            {
               var x1 = document.getElementsByName('LINK_NO'); 
               var x2=document.getElementsByName('POSTLINK1');
               var len=x1.length;
               var len2=x2.length;
              
               for (i=0;i<len;i++)
               { 
                   var tempvar=x1[i].value;
                   var flag=0;
                  if(x1[i].value!='')
                  {
                        for(j=0;j<len2;j++)
                        {

                               if(tempvar==x2[j].value)
                                {
                                  flag=1;
                                  break;
                                }
                                else
                                 {
                                    flag=0;    
                                 }

                           }
                            if(flag==0)
                            { 
                                alert('Invoice Detail Not Found For ID '+tempvar);
                                return false;
                            }
                      
                     }
               }
                  return true;
               } 
           
            function GetData()
            {                
                document.frm.action="editPostDocsFin.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
             
            {  
            if (validateinput() && validateYEAR() && checkLink())
               {            
                document.frm.action="savePostDocsFin.action?saveFlag=YES";
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
            
              function openpop(a)
            {    document.getElementById(a).style.display='';
            }
             function closediv(a)
            {
                document.getElementById(a).style.display='none';
            }
            
 
            function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,4}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
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
                
                 document.getElementById('addapprofrm').src="bankviewPostDocsFin.action?SEARCH_CODE="+objsrc.value;
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
                 document.getElementById('addapprofrm').src="bankviewPostDocsFin.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                  // document.getElementById('FBANK').value=b[0];
                   document.getElementById('FBANK_ADDNO').value=b[1];
                   document.getElementById('FBANK_NAME').value=b[2];
                   document.getElementById('FBANK_ADDRESS').value=b[3];
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}
 function SearchDrawnBank(objsrc,objtrg)
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
                
                 document.getElementById('addapprofrm').src="DbankviewPostDocsFin.action?SEARCH_CODE="+objsrc.value;
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
                 document.getElementById('addapprofrm').src="DbankviewPostDocsFin.action";
                 openpop('approveraddid');
              }
               else
               {  
                   objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                  // document.getElementById('FBANK').value=b[0];
                   document.getElementById('DBANK_ADDNO').value=b[1];
                   document.getElementById('DBANK_NAME').value=b[2];
                   document.getElementById('DBANK_ADDRESS').value=b[3];
                           
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
}
function SearchAWB(objsrc,objtrg,index)
{ 
    
    
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxAWBFINPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
           
             if(b.length>6)
             {     
                
                 document.getElementById('addapprofrm').src="AwbviewPostDocsFin.action?SEARCH_CODE="+objsrc.value+"&indexname="+index;
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
                      document.getElementById('addapprofrm').src="AwbviewPostDocsFin.action?indexname="+index;
                      openpop('approveraddid');
                   }
               else
               {
                   objsrc.value=b[0];
                  //document.getElementById(objtrg).value=b[7];
                   
                   document.getElementById('AWB_NO'+index).value=b[0];
                   document.getElementById('AWB_DATE'+index).value=b[1];
                   document.getElementById('HAWB_NO'+index).value=b[2];
                    
                 if (b[3]!=null && b[3].length>6){  
                   dojo.widget.byId("APRV_DATE"+index).setValue(b[3]);
                    }
              
                   document.getElementById('YEAR'+index).value=b[4];
                   document.getElementById('LINK_NO'+index).value=b[5];
                    
                 //  getchargesdt_test(b[4],b[5]);
                   
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
     
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
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Post Documents 77 </td></tr>
                        </table>
                    </td>
                </tr>
                <tr>  
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">

                <tr><td>
                        <%---<div style="width:200pt;" class="toolTip" id="toolTipDiv"></div>---%>
                        <%---<div align="center" style="width:100.0%;">---%>
                            <table width="100%" cellpadding="0" cellspacing="0">
                            <tr><td>
                               <table bgcolor="#f0f0f0" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                              
                                <tr><td>
                                        
                                       <div  style="width:90%; overflow:auto; height:180pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                           <table bgcolor="#7b97e0" style="margin-top:0pt;" align="left" width="100%" cellpadding="0" cellspacing="0" border="1">
                                            <tr>
                                                <th align="left">Awb NO</th>
                                                <th align="left">AWb Date</th>
                                                <th align="left">H/AwbNo</th>
                                                <th align="left">Aprv Date</th>
                                                <th align="left">ID#</th>
                                            </tr>
                                            <tbody > 
                                                  <s:set id="ctn" name="ctn" value="0"/>  
                                                  <s:iterator begin="0" end="20"  status="st1">

                                                    <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="AWB_NO%{#ctn}"  name="AWB_NO" value="%{AWB_NO.get(#st1.index)}" cssStyle="text-transform:uppercase;width:100pt;text-align:left" theme="simple" onblur="if(this.value!='') SearchAWB(this,'AWB_NO%{#ctn}','%{#ctn}') "/>
                                                                <a href="AwbviewPostDocsFin.action?indexname=<s:property value="%{#ctn}"/>" class="search"  target="addapprofrm"  onclick="openpop('approveraddid');" ><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                            </td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="AWB_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="AWB_DATE" value="%{AWB_DATE.get(#st1.index)}" cssStyle="width:50pt;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="HAWB_NO%{#ctn}" cssClass="textreadonly"  readonly="true" name="HAWB_NO" value="%{HAWB_NO.get(#st1.index)}" cssStyle="width:70pt;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><sx:datetimepicker id="APRV_DATE%{#ctn}" name="APRV_DATE" value="%{APRV_DATE.get(#st1.index)}" endDate="%{MAXDATE}"  cssStyle="text-transform:uppercase;width:50pt"  displayFormat="dd/MM/yyyy"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="LINK_NO%{#ctn}" cssClass="textreadonly"  readonly="true" name="LINK_NO" value="%{LINK_NO.get(#st1.index)}" cssStyle="width:30pt;text-align:left" theme="simple"/></td>
                                                          
                                                             <s:hidden name="YEAR" id="YEAR%{#ctn}" value="%{YEAR.get(#st1.index)}"/>
                                                             
                                                          </tr> 
                                                      <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                   </s:iterator>
                                             </tbody>
                                            
                                            
                                        </table>
                                        </div>
                                      </td>
                                       <td> 
                                           <div  style="width:100%; overflow:auto; height:180pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                           <table bgcolor="#7b97e0" style="margin-top:0pt;" align="right" width="100%" cellpadding="0" cellspacing="0" border="1">
                                            <tr>
                                                <th align="left">Invoice No</th>
                                                <th align="left">Co No.</th>
                                                <th align="left">Delv No</th>
                                                <th align="left">Port</th>
                                                <th align="left">Type</th>
                                                <th align="left">SB/Inv</th>
                                                <th align="right">Dbk Amt</th>
                                                <th align="right">Str Amt</th>
                                                <th align="right">Rosl Amt</th>
                                                <th align="right">Tax Amt</th>
                                                <th align="center">Tax%</th>
                                            </tr>
                                            <tbody > 
                                                <s:hidden name="aausrid" vaue="%{aausrid}"/>
                                               <s:set id="C_DBK" name="C_DBK" value="0"/> 
                                               <s:set id="C_STR" name="C_STR" value="0"/>
                                               <s:set id="C_ROSL" name="C_ROSL" value="0"/>
                                               <s:set id="C_TAX" name="C_TAX" value="0"/>
                                                <s:iterator value="%{CHRGLIST}"  status="st2">
                                                    <tr bgcolor="#f2f9fb">
                                                           
                                                            <td style="font-size:8pt"><s:property value="EXCS_INV_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="CO_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="DEL_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="PORT"/></td>
                                                            <td style="font-size:8pt"><s:property value="TYPE"/></td>
                                                            <td style="font-size:8pt"><s:property value="SB_NO"/></td>                              
                                                             <td style="font-size:8pt;text-align: right"><s:property value="DBK_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="STR_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="ROSL_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="TAX_AMT"/></td>      
                                                            <td style="font-size:8pt;text-align: center"><s:property value="TAX_PER"/></td>  
                                                               <s:set  name="C_DBK" value="%{#C_DBK+DBK_AMT}"/>
                                                               <s:set  name="C_STR" value="%{#C_STR+STR_AMT}"/>
                                                               <s:set  name="C_ROSL" value="%{#C_ROSL+ROSL_AMT}"/>
                                                               <s:set  name="C_TAX" value="%{#C_TAX+TAX_AMT}"/>
                                                    </tr> 
                                                </s:iterator>
                                                
                                                     <s:hidden name="CDBK" value="%{#C_DBK}"/>
                                                     <s:hidden name="CSTR" value="%{#C_STR}"/>
                                                     <s:hidden name="CROSL" value="%{#C_ROSL}"/> 
                                             </tbody>
                                             <tr> 
                                                 <hidden name="charge_dbk" value="%{#C_DBK}"
                                                 <td ></td><td style="font-size:8pt;text-align: right;color:#FDFEFE" colspan="6">Total#</td>
                                                   <s:text name="product.qty" id="cdbk" >
                                                       <s:param name="value" value="%{#C_DBK}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE  ;font-weight: bold; " align="right"><s:property value="%{#cdbk}"/></td>
                                                    <s:text name="product.qty" id="cstr" >
                                                       <s:param name="value" value="%{#C_STR}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#cstr}"/></td>
                                                    <s:text name="product.qty" id="crosl" >
                                                       <s:param name="value" value="%{#C_ROSL }"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#crosl}"/></td>
                                                     <s:text name="product.qty" id="ctax" >
                                                       <s:param name="value" value="%{#C_TAX}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#ctax}"/></td>
                                             </tr>
                                             
                                        </table>
                                        </div>
                                          
                                          
                                      </td>
                                   </tr> 
                               </table>
                                </td>
                            </tr>
                             <tr style=" background-color: #7a7">
                                      <s:property value="%{ERRMSG}"/>
                              <td   align="center" style="color:yellow;font-weight:bold">
                                  <%--          <s:if test="viewFlag==null"> 
                                       <button  id="searchheadId" class="sexybutton" onclick="GetData();"><span><span><span class="search" id="searchId">Get Data</span></span></span></button>
                                    </s:if>    
                                  --%> 
                                          <button  id="searchheadId" class="sexybutton" onclick="GetData();"><span><span><span class="search" id="searchId">Get Data</span></span></span></button>
                                
                                         <s:if test="%{ERRMSG==null && viewFlag!=null}">   
                                         <button  id="saveheadId"  class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" tabindex="-1" >Save</span></span></span></button> 
                                        </s:if>
                                        <s:else>
                                             <button  id="saveheadId"  class="sexybutton" disabled="true" onclick="saverec()"><span><span><span class="save" id="saveId" tabindex="-1" >Save</span></span></span></button> 
                                        </s:else>
                                       <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PostDocsFin.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                      <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                  
 
                                 </td>
                                   
                                                                                                     
                                  
                                </tr>    
                            <tr><td>
                            <table bgcolor="#f0f0f0" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                <tr><td>       
                                       <div  style="width:100%; overflow:auto; height:170pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                           <table bgcolor="#7b97e0" style="margin-top:0pt;" align="right" width="100%" cellpadding="0" cellspacing="0" border="1">
                                            <tr>
                                                <th align="left">Sl#</th>
                                                <th align="left">ID#</th>
                                                <th align="left">Invoice No</th>
                                                <th align="left">Buyer</th>
                                                <th align="left">Addr#</th>
                                                <th align="left">Buyer GL</th>
                                                 <th align="left">Cntry</th>
                                                 <th align="left">Crncy</th>
                                                 <th align="right">Fob Amt</th>
                                                <th align="right">GR Disc</th>
                                                <th align="right">Disc Amt</th>
                                                <th align="center">Tax%</th>
                                                <th align="left">LC No</th>
                                                <th align="left">Tepy</th>
                                                <th align="left">DueDtBase</th>
                                                <th align="left">Days</th>
                                            </tr>
                                            <tbody > 
                                              <s:set id="TFOB" name="TFOB" value="0"/> 
                                              <s:set id="TGR" name="TGR" value="0"/>
                                              <s:set id="TDISC" name="TDISC" value="0"/>
                                                <s:iterator value="%{INVLIST}"  status="st3">
                                                    <tr bgcolor="#f2f9fb">
                                                          
                                                         
                                                            <td style="font-size:8pt"><s:property value="%{#st3.index+1}" /></td>   
                                                            <td style="font-size:8pt"><s:property value="POSTLINK"/></td> 
                                                            <td style="font-size:8pt"><s:property value="INVOICENO"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER_ADDR"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER_GLCODE"/></td>
                                                             <td style="font-size:8pt"><s:property value="CNTRY"/></td>    
                                                              <td style="font-size:8pt"><s:property value="CRNCY_CODE"/></td>
                                                          
                                                             <td style="font-size:8pt;text-align: right"><s:property value="FOB_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="GR_DECL"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="DISC_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="TAX_PERCENT"/></td>      
                                                            <td style="font-size:8pt;text-align: center"><s:property value="LC_NO"/></td> 
                                                            <td style="font-size:8pt;text-align: center"><s:property value="XSTEPY"/></td> 
                                                             <td style="font-size:8pt;text-align: center"><s:property value="DUEDESC"/></td> 
                                                            <td style="font-size:8pt;text-align: center"><s:property value="XSNODY"/></td> 
                                                             <s:set  name="TFOB" value="%{#TFOB+FOB_AMT}"/>
                                                             <s:set  name="TGR" value="%{#TGR+GR_DECL}"/>
                                                             <s:set  name="TDISC" value="%{#TDISC+DISC_AMT}"/>
                                                             <s:hidden name="CRNCY" value="%{CRNCY_CODE}"/>
                                                             <s:hidden name="CHKLC" value="%{LC_NO}"/>
                                                             <s:hidden name="POSTLINK1" id="POSTLINK1" value="%{POSTLINK}"/>
                                                    </tr> 
                                                     
                                                   </s:iterator>

                                             </tbody>
                                             <td></td><td style="font-size:8pt;text-align: right;color:#FDFEFE;" colspan="6">Total#</td>
                                                   <s:text name="product.qty" id="tfob" >
                                                       <s:param name="value" value="%{#TFOB }"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tfob}"/></td>
                                                    <s:text name="product.qty" id="tgr" >
                                                       <s:param name="value" value="%{#TGR}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tgr}"/></td>
                                                    <s:text name="product.qty" id="tdisc" >
                                                       <s:param name="value" value="%{#TDISC}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tdisc}"/></td>
                        
                                             
                                        </table>
                                        </div>
                                    </td>
                                    <td>
                                       <div  style="width:100%; overflow:auto; height:170pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                           <table bgcolor="#7b97e0" style="margin-top:0pt;" align="right" width="100%" cellpadding="0" cellspacing="0" border="1">
                                               <tr>
                                                 <th align="left">Sl#</th>   
                                                <th align="left">S/B No</th>
                                                <th align="left">S/B Date</th>
                                                <th align="right">Dbk Due</th>
                                                <th align="right">Str Due</th>
                                                <th align="right">Rosl Due</th>
                                            </tr>
                                            <tbody> 
                                            
                                             <s:set id="TOT_DBKDUE" name="TOT_DBKDUE" value="0"/> 
                                             <s:set id="TOT_STRUE" name="TOT_STRDUE" value="0"/>
                                             <s:set id="TOT_ROSLDUE" name="TOT_ROSLDUE" value="0"/>
                                               <s:iterator value="%{SBLIST}"  status="st6">
                                                   <tr bgcolor="#B4E3A9">
                                                          
                                                             <td style="font-size:8pt"><s:property value="%{#st6.index+1}" /></td>  
                                                            <td style="font-size:8pt"><s:property value="SHP_BILL_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="SHP_BILL_DATE"/></td>
                                                            <td style="font-size:8pt;text-align: right "><s:property value="DBK_DUE"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="STR_DUE"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="ROSL_DUE"/></td>
                                                           <s:set  name="TOT_DBKDUE" value="%{#TOT_DBKDUE+DBK_DUE}"/>
                                                           <s:set  name="TOT_STRDUE" value="%{#TOT_STRDUE+STR_DUE}"/>
                                                           <s:set  name="TOT_ROSLDUE" value="%{#TOT_ROSLDUE+ROSL_DUE}"/>
                                                            
                                                   </tr>
                                                </s:iterator>
                                                 
                                            <tbody>
                                           
                                                <s:hidden name="SDBK" value="%{#TOT_DBKDUE}"/>
                                                <s:hidden name="SSTR" value="%{#TOT_STRDUE}"/>
                                                <s:hidden name="SROSL" value="%{#TOT_ROSLDUE}"/>
                                                <td></td>
                                                  <td></td><td style="font-size:8pt;text-align: right;color:#FDFEFE;">Total#</td>
                                                   <s:text name="product.qty" id="tdbk" >
                                                       <s:param  name="value" value="%{#TOT_DBKDUE }"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tdbk}"/></td>
                                                    <s:text name="product.qty" id="tstr" >
                                                       <s:param name="value" value="%{#TOT_STRDUE}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tstr}"/></td>
                                                    <s:text name="product.qty" id="trosl" >
                                                       <s:param name="value" value="%{#TOT_ROSLDUE}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#trosl}"/></td>
                                           </tbody>
                                           </table>
                                       </div>
                                    </td>
                                </tr>
                                 
                            </table>      
                            </td></tr>
                             <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="0">
                                        <tr>
                                            <td class="label-1" style="width:280px">Fin. Bank :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<s:textfield name="FBANK" id="FBANK" cssStyle="text-transform:uppercase;width:75pt;font-size:9pt;" theme="simple" value="%{FBANK}" onblur="if(this.value!='') return SearchFinBank(this,'FBANK1')"  />
                                                                                   <a href="banksearch.jsp" target="addapprofrm" onclick="openpop('approveraddid')"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                
                                                              <s:textfield name="FBANK_ADDNO" 
                                                                id="FBANK_ADDNO" 
                                                                cssStyle="width:50px" 
                                                                readonly="true"
                                                                value="%{FBANK_ADDNO}"
                                                                cssClass="textreadonly" 
                                                                theme="simple" tabindex="-1"/> 
                                            
                                            </td> 
                                             <td class="label-1"> Name 
                                                         <s:textfield name="FBANK_NAME" 
                                                                id="FBANK_NAME" 
                                                                cssStyle="width:200px" 
                                                                readonly="true"
                                                                value="%{FBANK_NAME}"
                                                                cssClass="textreadonly" 
                                                                theme="simple" tabindex="-1"/> 
                                            
                                                         <s:textfield name="FBANK_ADDRESS" 
                                                                id="FBANK_ADDRESS" 
                                                                cssStyle="width:400px" 
                                                                readonly="true"
                                                                value="%{FBANK_ADDRESS}"
                                                                cssClass="textreadonly" 
                                                                theme="simple" tabindex="-1"/> 
                                             </td>      
                                             
                                               <td class="label-1" style="text-align: left;">Pay Term
                                                 <s:select  headerValue="" list="# {'':'','COL':'COL','DA':'DA','DP':'DP','FOP':'FOP','LC':'LC','TT':'TT'}"  cssStyle="font-size:10pt;width:60px;font-weight:bold" theme="simple" name="PAYTERM"  /> 
                                              </td>         
                                              
                                              
                                        </tr>
                                        <tr>
                                             <td class="label-1" style="width:280px">Drawn Bank :  <s:textfield name="DBANK" id="DBANK" cssStyle="text-transform:uppercase;width:75pt;font-size:9pt;" theme="simple" value="%{DBANK}" onblur="if(this.value!='') return SearchDrawnBank(this,'DBANK1')"   />
                                                        <a href="Dbanksearch.jsp" target="addapprofrm" onclick="openpop('approveraddid')"><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                         <s:textfield name="DBANK_ADDNO" 
                                                                id="DBANK_ADDNO" 
                                                                cssStyle="width:50px" 
                                                                readonly="true"
                                                                value="%{DBANK_ADDNO}"
                                                                cssClass="textreadonly" 
                                                                theme="simple" tabindex="-1"/>                                                
                                            </td> 
                                             <td class="label-1"> Name 
                                                         <s:textfield name="DBANK_NAME" 
                                                                id="DBANK_NAME" 
                                                                cssStyle="width:200px" 
                                                                readonly="true"
                                                                value="%{DBANK_NAME}"
                                                                cssClass="textreadonly" 
                                                                theme="simple" tabindex="-1"/> 
                                            
                                                         <s:textfield name="DBANK_ADDRESS" 
                                                                id="DBANK_ADDRESS" 
                                                                cssStyle="width:400px" 
                                                                readonly="true"
                                                                value="%{DBANK_ADDRESS}"
                                                                cssClass="textreadonly" 
                                                                theme="simple" tabindex="-1"/> 
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
                    </td></tr>                            
                                                              
                                                               
                             </table>
                    
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

<script>
    
     
 
         
         function getchargesdt_test(P_YEAR,P_LINK_NO)
         {    
                    //alert('detailAwbPostDocsFin?P_YEAR='+P_YEAR+'&P_LINK_NO='+P_LINK_NO);
                    document.frm.action = "detailAwbPostDocsFin?P_YEAR="+P_YEAR+"&P_LINK_NO="+P_LINK_NO;
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility = '';
                     
         }
         
         
         
 
</script>
    </body>
 
</html>

