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
               var x1 = document.frm.CRNCY_CODE;
               var x2 = document.frm.LC_NO;
               
                for (var i = 0; i < x.length; i++) {
                    for (j = i + 1; j < x.length; j++) {
                        if (x[i].value > 0 && x[j].value>0 ) {
                            
                            if (x[i].value != x[j].value) {
                                alert(" Multiple Invoice Year..");
                                 return false;
                            }
                        }
                    }
                }
                  for (var i = 0; i < x1.length; i++) {
                    for (j = i + 1; j < x1.length; j++) {
                     
                        if (x1[i].value !=null && x1[j].value!=null ) {
                             
                            if (x1[i].value != x1[j].value) {
                                alert(" MultiplE Crncy Code ..");
                                 return false;
                            }
                        }
                    }
                }
                   for (var i = 0; i < x2.length; i++) {
                    for (j = i + 1; j < x2.length; j++) {
                        if (x2[i].value !=null && x2[j].value!=null ) {
                            
                            if (x2[i].value != x2[j].value) {
                                alert(" MultiplE LC No ..");
                                 return false;
                            }
                        }
                    }
                }
                 
                return true;
            }
      
            function searchdetail()
            {                
                document.frm.action="PostDocsFin.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
             
            { 
          //      if (validateinput() && validateYEAR())
               if ( validateYEAR())
               
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
                   dojo.widget.byId("APRV_DATE"+index).setValue(b[3]);
                   document.getElementById('YEAR'+index).value=b[4];
                   document.getElementById('LINK_NO'+index).value=b[5];
                    
                   getchargesdt_test(b[4],b[5]);
                   
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
                                        
                                       <div  style="width:90%; overflow:auto; height:200pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                           <table bgcolor="#7b97e0" style="margin-top:0pt;" align="left" width="100%" cellpadding="0" cellspacing="0" border="1">
                                            <tr>
                                                <th align="left">Awb NO</th>
                                                <th align="left">AWb Date</th>
                                                <th align="left">H/AwbNo</th>
                                                <th align="left">Aprv Date</th>
                                            </tr>
                                            <tbody > 
                                                  <s:set id="ctn" name="ctn" value="0"/>  
                                                  <s:iterator begin="0" end="20"  status="st1">

                                                    <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="AWB_NO%{#ctn}"  name="AWB_NO" value="%{AWB_NO.get(#st1.index)}" cssStyle="width:110pt;text-align:left" theme="simple" onblur="if(this.value!='') SearchAWB(this,'AWB_NO%{#ctn}','%{#ctn}') "/>
                                                                <a href="AwbviewPostDocsFin.action?indexname=<s:property value="%{#ctn}"/>" class="search"  target="addapprofrm"  onclick="openpop('approveraddid');" ><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                            </td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="AWB_DATE%{#ctn}" cssClass="textreadonly"  readonly="true" name="AWB_DATE" value="%{AWB_DATE.get(#st1.index)}" cssStyle="width:50pt;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="HAWB_NO%{#ctn}" cssClass="textreadonly"  readonly="true" name="HAWB_NO" value="%{HAWB_NO.get(#st1.index)}" cssStyle="width:70pt;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><sx:datetimepicker id="APRV_DATE%{#ctn}" name="APRV_DATE" value="%{APRV_DATE.get(#st1.index)}" endDate="%{MAXDATE}"  cssStyle="text-transform:uppercase;width:50pt"  displayFormat="dd/MM/yyyy"/></td>
                                                            <s:if test="%{LINK_NO!=null && LINK_NO.size()>0}">
                                                                <s:if test="%{LINK_NO.get(#ctn)!=null && LINK_NO.get(#ctn).length()>0}">
                                                                    <s:hidden name="YEAR" value="%{YEAR.get(#ctn)}" id="YEAR%{#ctn}"/> 
                                                                    <s:hidden name="LINK_NO" value="%{LINK_NO.get(#ctn)}" id="LINK_NO%{#ctn}"/> 
                                                                    <s:hidden name="SAVETEXT" value="%{YEAR.get(#ctn)+'--'+LINK_NO.get(#ctn)+'--'+APRV_DATE.get(#ctn)}" /> 
                                                                </s:if>
                                                            </s:if>
                                                            <s:else>
                                                                    <s:hidden name="YEAR" value="" id="YEAR%{#ctn}"/> 
                                                                    <s:hidden name="LINK_NO" value="" id="LINK_NO%{#ctn}"/> 
                                                           
                                                             </s:else>
                                                          </tr> 
                                                      <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                   </s:iterator>
                                             </tbody>
                                            
                                            
                                        </table>
                                        </div>
                                      </td>
                                       <td> 
                                           <div  style="width:100%; overflow:auto; height:200pt;border-width:1pt;border-color:whitesmoke; border-style:none">
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
                                               <s:set id="C_DBK" name="C_DBK" value="0"/> 
                                               <s:set id="C_STR" name="C_STR" value="0"/>
                                               <s:set id="C_ROSL" name="C_ROSL" value="0"/>
                                               <s:set id="C_TAX" name="C_TAX" value="0"/>
                                                <s:iterator value="%{CHRGLIST}"  status="st2">
                                                    <tr bgcolor="#f2f9fb">
                                                          <s:hidden  name="EXCS_INV_NO" theme="simple"/>
                                                          <s:hidden  name="CO_NO" theme="simple"/>
                                                          <s:hidden  name="DEL_NO" theme="simple"/>
                                                          <s:hidden  name="CO_NO" theme="simple"/>
                                                          <s:hidden  name="PORT" theme="simple"/>
                                                          <s:hidden  name="TYPE" theme="simple"/>
                                                          <s:hidden  name="SB_NO" theme="simple"/>
                                                          <s:hidden  name="DBK_AMT" theme="simple"/>
                                                          <s:hidden  name="STR_AMT" theme="simple"/>
                                                          <s:hidden  name="ROSL_AMT" theme="simple"/>
                                                           <s:hidden  name="TAX_AMT" theme="simple"/>
                                                           <s:hidden  name="TAX_PER" theme="simple"/>
                                                          
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
                                                    <s:set id="C2_DBK" name="C2_DBK" value="0"/> 
                                                    <s:set id="C2_STR" name="C2_STR" value="0"/>
                                                    <s:set id="C2_ROSL" name="C2_ROSL" value="0"/>
                                                    <s:set id="C2_TAX" name="C2_TAX" value="0"/>
                                                    <s:iterator value="%{EXCS_INV_NO}"  status="st4">
                                                    <tr bgcolor="#f2f9fb">
                                                          <s:hidden  name="EXCS_INV_NO" value="%{EXCS_INV_NO[#st4.index]}" theme="simple" />
                                                          <s:hidden  name="CO_NO" value="%{CO_NO[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="DEL_NO" value="%{DEL_NO[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="PORT" value="%{PORT[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="TYPE" value="%{TYPE[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="SB_NO" value="%{SB_NO[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="DBK_AMT" value="%{DBK_AMT[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="STR_AMT" value="%{STR_AMT[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="ROSL_AMT" value="%{ROSL_AMT[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="TAX_AMT" value="%{TAX_AMT[#st4.index]}" theme="simple"/>
                                                          <s:hidden  name="TAX_PER" value="%{TAX_PER[#st4.index]}" theme="simple"/>
                                                            <td style="font-size:8pt"><s:property value="%{EXCS_INV_NO[#st4.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{CO_NO[#st4.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{DEL_NO[#st4.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{PORT[#st4.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{TYPE[#st4.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{SB_NO[#st4.index]}"/></td>                              
                                                             <td style="font-size:8pt;text-align: right"><s:property value="%{DBK_AMT[#st4.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{STR_AMT[#st4.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{ROSL_AMT[#st4.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{TAX_AMT[#st4.index]}"/></td>      
                                                            <td style="font-size:8pt;text-align: center"><s:property value="%{TAX_PER[#st4.index]}"/></td>    
                                                            
                                                             <s:set name="C2_DBK" id="C2_DBK" value="%{#C2_DBK+@java.lang.Double@parseDouble(DBK_AMT[#st4.index])}"/>
                                                             <s:set name="C2_STR" id="C2_STR" value="%{#C2_STR+@java.lang.Double@parseDouble(STR_AMT[#st4.index])}"/>
                                                             <s:set name="C2_ROSL" id="C2_ROSL" value="%{#C2_ROSL+@java.lang.Double@parseDouble(ROSL_AMT[#st4.index])}"/>
                                                             <s:set name="C2_TAX" id="C2_TAX" value="%{#C2_TAX+@java.lang.Double@parseDouble(TAX_AMT[#st4.index])}"/>
                                                    
                                                    </tr> 
                                                     
                                                   </s:iterator>
                                             </tbody>
                                             <tr> 
                                                 <td ></td><td style="font-size:8pt;text-align: right;color:#FDFEFE" colspan="5">Total#</td>
                                                   <s:text name="product.qty" id="cdbk" >
                                                       <s:param name="value" value="%{#C_DBK + #C2_DBK}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE  ;font-weight: bold; " align="right"><s:property value="%{#cdbk}"/></td>
                                                    <s:text name="product.qty" id="cstr" >
                                                       <s:param name="value" value="%{#C_STR + #C2_STR}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#cstr}"/></td>
                                                    <s:text name="product.qty" id="crosl" >
                                                       <s:param name="value" value="%{#C_ROSL + #C2_ROSL}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#crosl}"/></td>
                                                     <s:text name="product.qty" id="ctax" >
                                                       <s:param name="value" value="%{#C_TAX + #C2_TAX}"/>
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
                                <td   align="center" style="color:yellow;font-weight:bold">
                                     
                                        <s:property value="%{ERRMSG}"/>
                                        <s:if test="%{ERRMSG==null}">   
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
                                                          <s:hidden  name="INVOICENO" theme="simple"/>
                                                          <s:hidden  name="BUYER" theme="simple"/>
                                                          <s:hidden  name="BUYER_ADDR" theme="simple"/>
                                                          <s:hidden  name="CONS_ADDR" theme="simple"/>
                                                          <s:hidden  name="BUYER_GLCODE" theme="simple"/>
                                                          <s:hidden  name="CRNCY_CODE" theme="simple"/>
                                                          <s:hidden  name="CNTRY" theme="simple"/>
                                                          <s:hidden  name="FOB_AMT" theme="simple"/>
                                                          <s:hidden  name="GR_DECL" theme="simple"/>
                                                          <s:hidden  name="DISC_AMT" theme="simple"/>
                                                          <s:hidden  name="TAX_PERCENT" theme="simple"/>
                                                          <s:hidden  name="LC_NO" theme="simple"/>
                                                          <s:hidden  name="XSTEPY" theme="simple"/>
                                                          <s:hidden  name="DUEDESC" theme="simple"/>
                                                          <s:hidden  name="XSNODY" theme="simple"/>
                                                          
                                                          
                                                            <td style="font-size:8pt"><s:property value="INVOICENO"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER_ADDR"/></td>
                                                            <td style="font-size:8pt"><s:property value="BUYER_GLCODE"/></td>
                                                            <td style="font-size:8pt"><s:property value="CRNCY_CODE"/></td>
                                                            <td style="font-size:8pt"><s:property value="CNTRY"/></td>                              
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
                                                    </tr> 
                                                     
                                                   </s:iterator>
                                                     <s:set id="T2FOB" name="T2FOB" value="0"/> 
                                                    <s:set id="T2GR" name="T2GR" value="0"/>
                                                     <s:set id="T2DISC" name="T2DISC" value="0"/>
                                                    <s:iterator value="%{INVOICENO}"  status="st5">
                                                    <tr bgcolor="#f2f9fb">
                                                          <s:hidden  name="INVOICENO" value="%{INVOICENO[#st5.index]}" theme="simple" />
                                                          <s:hidden  name="BUYER" value="%{BUYER[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="BUYER_ADDR" value="%{BUYER_ADDR[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="BUYER_GLCODE" value="%{BUYER_GLCODE[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="CRNCY_CODE" value="%{CRNCY_CODE[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="CNTRY" value="%{CNTRY[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="FOB_AMT" value="%{FOB_AMT[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="GR_DECL" value="%{GR_DECL[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="DISC_AMT" value="%{DISC_AMT[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="TAX_PERCENT" value="%{TAX_PERCENT[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="LC_NO" value="%{LC_NO[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="XSTEPY" value="%{XSTEPY[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="DUEDESC" value="%{DUEDESC[#st5.index]}" theme="simple"/>
                                                          <s:hidden  name="XSNODY" value="%{XSNODY[#st5.index]}" theme="simple"/>
                                                          
                                                            <td style="font-size:8pt"><s:property value="%{INVOICENO[#st5.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUYER[#st5.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUYER_ADDR[#st5.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUYER_GLCODE[#st5.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{CRNCY_CODE[#st5.index]}"/></td>
                                                            <td style="font-size:8pt"><s:property value="%{CNTRY[#st5.index]}"/></td>                              
                                                             <td style="font-size:8pt;text-align: right"><s:property value="%{FOB_AMT[#st5.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{GR_DECL[#st5.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{DISC_AMT[#st5.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{TAX_PERCENT[#st5.index]}"/></td>      
                                                            <td style="font-size:8pt;text-align: center"><s:property value="%{LC_NO[#st5.index]}"/></td> 
                                                             <td style="font-size:8pt;text-align: center"><s:property value="%{XSTEPY[#st5.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: center"><s:property value="%{DUEDESC[#st5.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: center"><s:property value="%{XSNODY[#st5.index]}"/></td>
                                                             <s:set name="T2FOB" id="T2FOB" value="%{#T2FOB+@java.lang.Double@parseDouble(FOB_AMT[#st5.index])}"/>
                                                             <s:set name="T2GR" id="T2GR" value="%{#T2GR+@java.lang.Double@parseDouble(GR_DECL[#st5.index])}"/>
                                                             <s:set name="T2DISC" id="T2DISC" value="%{#T2DISC+@java.lang.Double@parseDouble(DISC_AMT[#st5.index])}"/>
                                             
                                                    </tr> 
                                                     
                                                   </s:iterator>
                                             </tbody>
                                             <td></td><td style="font-size:8pt;text-align: right;color:#FDFEFE;" colspan="5">Total#</td>
                                                   <s:text name="product.qty" id="tfob" >
                                                       <s:param name="value" value="%{#TFOB + #T2FOB}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tfob}"/></td>
                                                    <s:text name="product.qty" id="tgr" >
                                                       <s:param name="value" value="%{#TGR + #T2GR}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tgr}"/></td>
                                                    <s:text name="product.qty" id="tdisc" >
                                                       <s:param name="value" value="%{#TDISC + #T2DISC}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tdisc}"/></td>
                        
                                             
                                        </table>
                                        </div>
                                    </td>
                                    <td>
                                       <div  style="width:100%; overflow:auto; height:170pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                           <table bgcolor="#7b97e0" style="margin-top:0pt;" align="right" width="100%" cellpadding="0" cellspacing="0" border="1">
                                               <tr>
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
                                                          <s:hidden  name="SHP_BILL_NO" theme="simple"/>
                                                          <s:hidden  name="SHP_BILL_DATE" theme="simple"/>
                                                          <s:hidden  name="DBK_DUE" theme="simple"/>
                                                          <s:hidden  name="STR_DUE" theme="simple"/>
                                                          <s:hidden  name="ROSL_DUE" theme="simple"/>
                                                         
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
                                                   <s:set name="DBKTOT" id="DBKTOT" value="0"/>
                                                   <s:set name="STRTOT" id="STRTOT" value="0"/>
                                                   <s:set name="ROSLTOT" id="ROSLTOT" value="0"/>
                                                 <s:iterator value="%{SHP_BILL_NO}"  status="st7">
                                                     <tr bgcolor="#B4E3A9">
                                                          <s:hidden  name="SHP_BILL_NO" value="%{SHP_BILL_NO[#st7.index]}" theme="simple"/>
                                                          <s:hidden  name="SHP_BILL_DATE" value="%{SHP_BILL_DATE[#st7.index]}" theme="simple"/>
                                                          <s:hidden  name="DBK_DUE" value="%{DBK_DUE[#st7.index]}" theme="simple"/>
                                                          <s:hidden  name="STR_DUE" value="%{STR_DUE[#st7.index]}" theme="simple"/>
                                                          <s:hidden  name="ROSL_DUE" value="%{ROSL_DUE[#st7.index]}" theme="simple"/>
                                                            <td style="font-size:8pt;text-align: left"><s:property value="%{SHP_BILL_NO[#st7.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: left"><s:property value="%{SHP_BILL_DATE[#st7.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{DBK_DUE[#st7.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{STR_DUE[#st7.index]}"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="%{ROSL_DUE[#st7.index]}"/>
                                                         </td>
                                                             
                                                             <s:set name="DBKTOT" id="DBKTOT" value="%{#DBKTOT+@java.lang.Double@parseDouble(DBK_DUE[#st7.index])}"/>
                                                             <s:set name="STRTOT" id="STRTOT" value="%{#STRTOT+@java.lang.Double@parseDouble(STR_DUE[#st7.index])}"/>
                                                             <s:set name="ROSLTOT" id="ROSLTOT" value="%{#ROSLTOT+@java.lang.Double@parseDouble(ROSL_DUE[#st7.index])}"/>
                                                   
                                                       </tr> 
                                                </s:iterator>     
                                            <tbody>
                                           
                                           
                                                  <td></td><td style="font-size:8pt;text-align: right;color:#FDFEFE;">Total#</td>
                                                   <s:text name="product.qty" id="tdbk" >
                                                       <s:param name="value" value="%{#TOT_DBKDUE + #DBKTOT}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tdbk}"/></td>
                                                    <s:text name="product.qty" id="tstr" >
                                                       <s:param name="value" value="%{#TOT_STRDUE + #STRTOT}"/>
                                                   </s:text>
                                                  <td style="font-size:8pt;color:#FDFEFE;font-weight: bold; " align="right"><s:property value="%{#tstr}"/></td>
                                                    <s:text name="product.qty" id="trosl" >
                                                       <s:param name="value" value="%{#TOT_ROSLDUE + #ROSLTOT}"/>
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
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="1">
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

