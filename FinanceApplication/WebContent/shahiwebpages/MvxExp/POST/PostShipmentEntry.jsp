

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
  
 
    </script>

        <script language="javascript">
            
            

     
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
                    
                    return false;
                }

             }
     
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
     
      
     
     function openpop(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv(a)
            {
                document.getElementById(a).style.display = 'none';
            }
     
 
        function validateinput(){
         if(document.frm.AWBNO.value=="")
        {
           alert("Please Enter Awb No  ")
            document.frm.AWBNO.focus();
           return false;
        }
      
           if(dojo.widget.byId("AWBDATE").getValue()=="")
        {
           alert("Please enter  Awb Date  ")
            document.frm.AWBDATE.focus();
           return false;
        }
          var aa=dojo.widget.byId("AWBDATE").getValue().substring(0,4)+dojo.widget.byId("AWBDATE").getValue().substring(5,7)+dojo.widget.byId("AWBDATE").getValue().substring(8,10);
          var bb=document.frm.CRDATE.value.substring(6,10)+document.frm.CRDATE.value.substring(3,5)+document.frm.CRDATE.value.substring(0,2);
          
         if (aa!=null && aa>bb)
            {
               alert("Awb Date Can not be future date...") 
                return false;
            } 
       
          if (aa<document.frm.SALE_LOCK.value)
         {
            alert(" Sale Lock upto   "+document.frm.SALE_LOCK.value)
            document.frm.AWBDATE.focus();
           return false;  
         }  
          if (aa<document.frm.AWB_ALLOW.value)
         {
            alert(" Awb Date Lock upto   "+document.frm.AWB_ALLOW.value)
            document.frm.AWBDATE.focus();
           return false;  
         }  
          if(document.frm.TOTALPCKGE.value=="" || document.frm.TOTALPCKGE.value=="0")
        {
           alert("Please enter Total  Packages  ")
            document.frm.TOTALPCKGE.focus();
           return false;
        }
         if(document.frm.GROSSWT.value=="" ||document.frm.GROSSWT.value=="0")
        {
           alert("Please enter Total Gross Weight  ")
            document.frm.GROSSWT.focus();
           return false;
        }
          if(document.frm.CBM.value=="" ||document.frm.CBM.value=="0")
        {
           alert("Please enter Total CBM  ")
            document.frm.CBM.focus();
           return false;
        }
          if(parseFloat(document.frm.GROSSWT.value)!=parseFloat(document.frm.TOTGRWT.value))
        {
           alert("Gross Weight Mismatch ..... ")
            document.frm.GROSSWT.focus();
           return false;
        }
          if(document.frm.TOTALPCKGE.value!=document.frm.TOTPKGS.value)
        {
           alert("Packages Mismatch ..... ")
            document.frm.TOTALPCKGE.focus();
           return false;
        }
        
           return true;
      }
      

          function onbackedit(url) {
                    document.frm.action = url;
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility = '';
                }
             function saverec()
             
            {  
              //  if (validateinput() && validateYEAR())
              if (validateinput() )
               { 
                document.frm.action="update1POSTSHIPMENT.action?saveFlag=Yes";
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
             function validatenumber1(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
                        a.focus();
            		return false;
            	}
              totalval();
            	return true;
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
                totalval();
            	return true;
                
            }
     function totalval()
    {
       
     var GRWT=document.frm.GROSS_WT;
     var PKGS=document.frm.PKGS;
     var FOB=document.frm.FOBAMT;
     var GR=document.frm.GR_DISC;
     var SQTY=document.frm.QTY;

     var t=0; var t1=0;  t2=0; t3=0; t4=0;
         if(document.frm.GROSS_WT)
        {    
          if(GRWT.length>0)  
              {    
                  for(m=0; m<GRWT.length; m++)
                      {  
                          if(!isNaN(eval(GRWT[m].value))){
                            t=t+eval(GRWT[m].value) ;
                            t1=t1+eval(PKGS[m].value);
                            t2=t2+eval(FOB[m].value);
                            t3=t3+eval(GR[m].value);
                            t4=t4+eval(SQTY[m].value);
                         
                          
                       }
                      }
              }else{
                   
                             
                   t=t+eval(GRWT.value) ;
                   t1=t1+eval(PKGS.value);
                   t2=t2+eval(FOB.value) ;
                   t3=t3+eval(GR.value) ;
                   t4=t4+eval(SQTY.value) ;
                   
                   }
        }  
         document.getElementById('TOTGRWT').value=t.toFixed(4);
         document.getElementById('TOTPKGS').value=t1.toFixed();
         document.getElementById('TOTFOB').value=t2.toFixed();
         document.getElementById('TOTGR').value=t3.toFixed();
         document.getElementById('TOTQTY').value=t4.toFixed();
       }
     
         function validateYEAR()
         {   
       
               var x = document.frm.INV_YEAR;
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
                 
                   for (var i=1; i<x.length; i++)
                      {  
                         if ((dojo.widget.byId("BILL_DATE"+[i]).getValue().substring(0,4)+dojo.widget.byId("BILL_DATE"+[i]).getValue().substring(5,7)+dojo.widget.byId("BILL_DATE"+[i]).getValue().substring(8,10))  >  
                                (dojo.widget.byId("LET_EXP_DT"+[i]).getValue().substring(0,4)+dojo.widget.byId("LET_EXP_DT"+[i]).getValue().substring(5,7)+dojo.widget.byId("LET_EXP_DT"+[i]).getValue().substring(8,10)))   
                              { alert('Check Let exp Date can not be less then S/B Date...');
                                return false;
                              }  
                          
                      }
           
            
                           var x = document.frm.INVOICE_NO;
                       
                            for (var i = 0; i < x.length; i++) {
                            
                            for (j = i + 1; j < x.length; j++) {
                              
                            if (x[i].value > 0 && x[j].value>0 ) {
                            
                             if (x[i].value==x[j].value) {
                                alert(" Duplicate Invoice .."+x[i].value);
                                 return false;
                                   }
                                   }
                                   } 
                                  }
           
            
                return true;
            }
        
    function validateLIC()
    {   
       var REF_NO=document.frm.LLC_NO;
       var FOB_AMT=document.frm.FOBAMT; 
       var LIC_AMT_OB=document.frm.LC_VALUE; 
       var LIC_AMT_ISSUE=document.frm.EXP_UTIL; 
       var LIC_PERCENT=document.frm.LC_PERCENT;
       var GR_DISC=document.frm.GR_DISC;
       if(REF_NO!=null && REF_NO.length()>5)
            {    t1=0; p1=0; t2=0;t3=0;
                for(i=0; i<REF_NO.length; i++)
                    {     
                    if(REF_NO[i].value!="") 
                    {
                           t1=eval(eval(LIC_AMT_OB[i].value));
                          
                           p1=eval(t1*(eval(LIC_PERCENT[i].value)/100));
                           t2=eval(FOB_AMT[i].value)-eval(GR_DISC[i].value) ;
                           t3=eval(LIC_AMT_ISSUE[i].value);
                          
                           if ((t1+p1+t2)>t3)
                            { alert('Check LC Value  --> '+(t1+p1)+" Invoice Vakye"+t2+'  Utilize Value -->'+t3)
                             return false;
                    
                            }   
                  }
                } 
              }
             
                 return true;
         }
      

   
     
         function validateInv(objsrc,objtrg,index)
         {
            var awbdate=dojo.widget.byId("AWBDATE").getValue().substring(0,4)+dojo.widget.byId("AWBDATE").getValue().substring(5,7)+dojo.widget.byId("AWBDATE").getValue().substring(8,10);
            var sbno=document.getElementById("BILL_NO"+index).value;
            var sbdate=dojo.widget.byId("BILL_DATE"+index).getValue().substring(0,4)+dojo.widget.byId("BILL_DATE"+index).getValue().substring(5,7)+dojo.widget.byId("BILL_DATE"+index).getValue().substring(8,10);
            var letexp=dojo.widget.byId("BILL_DATE"+index).getValue().substring(0,10);
            
            var xmlHttpReq = false; 
            var self = this;
            if (window.XMLHttpRequest) {  
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {  
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            }
            self.xmlHttpReq.open('POST', 'ajaxValidateInvoicePOSTAJAX', false); 
            self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            self.xmlHttpReq.onreadystatechange = function() {
               	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
                {   
                      var a = self.xmlHttpReq.responseText;
                      var b= new Array();
                      b=a.split('#');
                      
                      if(b[0]=="NOERROR")
                        {    
                           document.getElementById('CRNCY_ID'+index).value=b[1];
                           document.getElementById('MOS_ID'+index).value=b[2];
                           document.getElementById('PORT_ID'+index).value=b[3];
                           document.getElementById('BUYER_ID'+index).value=b[4];
                           document.getElementById('CHA_ID'+index).value=b[5];
                           document.getElementById('DESTNCNTRY_ID'+index).value=b[6];
                           document.getElementById('QTY_ID'+index).value=b[7];
                           document.getElementById('FOBAMT_ID'+index).value=b[8];
                           document.getElementById('GR_DISC_ID'+index).value=b[9];
                           document.getElementById('XSTEPY'+index).value=b[10];
                           document.getElementById('XSNODY'+index).value=b[11];
                           document.getElementById('INR_C_ID'+index).value=b[12];
                           document.getElementById('DBK_C_ID'+index).value=b[13];
                            if (b[14]<document.frm.SB_ALLOW.value)
                            {  document.getElementById(objtrg).value=""; 
                               alert('Check S/B Allow Date...') 
                            }
                            dojo.widget.byId("LET_EXP_DT"+index).setValue(b[15]);
                             dojo.widget.byId("LET_EXP_DT"+index).startDate.value=b[15];
                            document.getElementById('INV_YEAR'+index).value=b[16];
                            document.getElementById('EI_YEAR').value=b[16];
                            document.getElementById('INV_NO'+index).value=b[17];
                            
                            
                        }
                        else 
                        {
                            
                          // document.getElementById(objtrg).value=""; 
                           alert('Invoice Detail Not Found ...') 
                           alert(b[0]);
                         }
             	}
            }
           
           param=objsrc.value;
          
           self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&AWBDATE_AJ="+awbdate+"&SBNO_AJ="+sbno+"&SBDATE_AJ="+sbdate+"&LETEXP_AJ="+letexp+"&time="+(new Date()).getTime());
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
   
   self.xmlHttpReq.open('POST', 'ajaxLCPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
             b=a.split('#');
            
             if(b.length>4)
             {    
                
                 document.getElementById('addapprofrm').src="LCsearchPOSTSHIPMENT.action?SEARCH_CODE="+objsrc.value+"&indexname="+index;
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
                 document.getElementById('addapprofrm').src="LCsearchPOSTSHIPMENT.action?indexname="+index;
                 openpop('approveraddid');
              }
               else
               {
                   objsrc.value=b[0];
                 //  document.getElementById(objtrg).value=b[7];
                    
                   document.getElementById('LLC_NO'+index).value=b[0];
                   document.getElementById('LC_VALUE'+index).value=b[1];
                   document.getElementById('EXP_UTIL'+index).value=b[2];
                   document.getElementById('LC_PERCENT'+index).value=b[3];
                      
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
    
}   

$(document).on('keypress','your-element',function(e){
    if(e.keyCode == 37 || e.keyCode == 38 || e.keyCode == 39 || e.keyCode == 40)
    console.log("Arrow key pressed");
})
        </script>

    </head>  
 
    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
         <input type="hidden" id="download_token_value_id" name="download_token_value_id"/>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Post Shipment Entry</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>  
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="0">
                                            <tr>
                                                <td class="label-1" style="width:300px">Awb No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield name="AWBNO" id="AWBNO" cssStyle="text-transform:uppercase;width:120pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                </td> 
                                                <td class="label-1" style="width:300px">Awb Date &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<sx:datetimepicker id="AWBDATE" name="AWBDATE" value="%{AWB_DATE}" endDate="%{MAXDATE}"  cssStyle="text-transform:uppercase;width:80pt"  displayFormat="dd/MM/yyyy"/>

                                                </td> 
                                                 <td class="label-1" style="width:300px">H/Awb No. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="HAWBNO" id="HAWBNO" cssStyle="text-transform:uppercase;width:100pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                </td> 
                                                 <td class="label-1" style="width:300px">Pre Carriage &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="PRECRIGE" id="PRECRIGE" cssStyle="text-transform:uppercase;width:70pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                 </td> 
                                         
                                                    
                                                </tr>
                                                <tr>
                                                 <td class="label-1" style="width:300px">Gross Wt. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield name="GROSSWT" id="GROSSWT" cssStyle="text-transform:uppercase;width:120pt;text-align:right" theme="simple" onblur="validatenumber(this)" onKeyPress="tabE(this, event);"/>
                                                </td>
                                                 <td class="label-1" style="width:300px">Charable Wt &nbsp;&nbsp;&nbsp;&nbsp;&nbsp<s:textfield name="CHARABLEWT" id="CHARABLEWT" cssStyle="text-transform:uppercase;width:70pt;text-align:right" theme="simple" onblur="validatenumber(this)" onKeyPress="tabE(this, event);"/>
                                                </td>
                                                 <td class="label-1" style="width:300px">Total Pkgs. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="TOTALPCKGE" id="TOTALPCKGE" cssStyle="text-transform:uppercase;width:100pt;text-align:right" theme="simple" onblur="validatenumber1(this)" onKeyPress="tabE(this, event);"/>
                                                </td> 
                                              
                                               
                                               
                                               
                                                <td class="label-1" style="width:300px">Total CBM &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="CBM" id="CBM" value="%{CBM}" cssStyle="text-transform:uppercase;width:70pt;text-align:right" theme="simple" onblur="validatenumber(this)" onKeyPress="tabE(this, event);"/>
                                                </td>
                                                </tr>
                                               
                                                <tr>
                                                <td class="label-1"   colspan="2">Buying  House <s:select name="BH_HOUSE" id="BUYHOUSE" list="%{BUYHOUSE}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  value="%{BUYING_HOUSE}" cssStyle="text-transform:uppercase;width:120pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                </td>
                                                <td class="label-1" >Buying Person &nbsp;&nbsp;<s:select name="BH_PERSON" id="BUYPERSON" list="%{BUYPERSON}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  value="%{BUYING_PERSON}" cssStyle="text-transform:uppercase;width:100pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                </td>
                                                <td class="label-1">Awb Date Lock &nbsp;<s:textfield name="AWB_ALLOW" value="%{AWB_ALLOW}" theme="simple" readonly="true" cssStyle="text-transform:uppercase;width:70pt;" cssClass="textreadonly" tabindex="-1" />
                                                 &nbsp;&nbsp;&nbsp;
                                                 S/B Dt Lock&nbsp;&nbsp;&nbsp;<s:textfield name="SB_ALLOW" value="%{SB_ALLOW}" theme="simple" readonly="true" cssStyle="text-transform:uppercase;width:70pt; " cssClass="textreadonly" tabindex="-1" />
                                                </td>
                                                  </tr>
                                                 <s:hidden value="%{currentdate}" name="CRDATE" id="CRDATE" />
                                                 <s:hidden name="SALE_LOCK" value="%{SALE_LOCK}" />
                                                 <s:hidden name="EI_YEAR" value="%{YEAR}" id="EI_YEAR" />
                                                 <s:hidden name="LINK_NO" value="%{LINK_NO}" id="LINK_NO" />
                                                
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
                                               <table id="mytableid" border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="1" width="100%">
                                                   <thead>
                                                   <tr style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left"  >S/B No.</th>
                                                    <th style="font-size:8pt" align="left">S/B Date</th>
                                                     <th style="font-size:8pt" align="left" >Invoice&nbsp;#&nbsp;&nbsp;&nbsp;</th>
                                                    <th style="font-size:8pt" align="right">Grs Wt</th>
                                                    <th style="font-size:8pt" align="right">Pkgs</th>
                                                    <th style="font-size:8pt" align="left" colspan="2">L/C No.</th>
                                                    <th style="font-size:8pt" align="left">Let Exp Dt.</th>
                                                 
                                                    <th style="font-size:8pt" align="right">InrConv</th>
                                                    <th style="font-size:8pt" align="right">DbkConv</th>
                                                    <th style="font-size:8pt" align="center">Crncy</th>
                                                    <th style="font-size:8pt" align="right">Fob Amt.</th>
                                                    <th style="font-size:8pt" align="right">GR Decl</th>
                                                    <th style="font-size:8pt" align="right">Qty.</th>
                                                    <th style="font-size:8pt" align="lefy">MOS</th>
                                                    <th style="font-size:8pt" align="left">Port</th>
                                                    <th style="font-size:8pt" align="left">Buyer</th>
                                                     <th style="font-size:8pt" align="left">Cntry</th>
                                                   </tr>                                                
                                                   </thead>
                                                  <tbody> 
                                                 <s:set id="ctn" name="ctn" value="1"/>      
                                                  <s:iterator value="listdata" status="st1">
                                                      <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#ctn}" /></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="BILL_NO%{#ctn}" name="BILL_NO" value="%{BILL_NO_B}" cssStyle="text-transform:uppercase;width:80px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><sx:datetimepicker id="BILL_DATE%{#ctn}" name="BILL_DATE" value="%{BILL_DATE_B}"    endDate="%{MAXDATE}"   cssStyle="width:70px;text-align:left" displayFormat="dd/MM/yyyy"/></td>
                                                            <td style="font-size:8pt;text-align: left"><s:textfield id="INVOICE_NO%{#ctn}" name="INVOICE_NO" readonly="true" value="%{INVOICE_NO_B}" cssStyle="width:80px;text-align:left" cssClass="textreadonly" theme="simple" /></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="GROSS_WT%{#ctn}" name="GROSS_WT" value="%{GROSS_WT_B}" cssStyle="width:60px;text-align:right" onblur="validatenumber(this);totalval();" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="PKGS%{#ctn}" name="PKGS" value="%{PKGS_B}" cssStyle="width:40px;text-align:right" onblur="validatenumber1(this);totalval();" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="LLC_NO%{#ctn}" name="LLC_NO" value="%{LLC_NO_B}" readonly="true" cssStyle="width:100px;text-align:left" theme="simple"/></td>
                                                             <td>
                                                                  <a href="LCsearchPOSTSHIPMENT.action?indexname=<s:property value="%{#ctn}"/>" class="search"  target="addapprofrm"  onclick="openpop('approveraddid');" ><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                                   
                                                            </td>
                                                            <td style="font-size:8pt;text-align: left;"><sx:datetimepicker id="LET_EXP_DT%{#ctn}" name="LET_EXP_DT" value="%{LET_EXP_DT_B}" endDate="%{MAXDATE}"   cssStyle="width:70px;text-align:left" displayFormat="dd/MM/yyyy"/></td>
                                                          
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="INR_C_ID%{#ctn}" name="INR_C" value="%{INR_C_B}" cssStyle="width:60px;text-align:right;" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="DBK_C_ID%{#ctn}" name="DBK_C" value="%{DBK_C_B}" cssStyle="width:60px;text-align:right"  readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="CRNCY_ID%{#ctn}" name="CRNCY_CODE" value="%{CRNCY_CODE}" cssStyle="width:40px;text-align:center"  readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                          
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="FOBAMT_ID%{#ctn}" name="FOBAMT" value="%{FOBAMT_B}" cssStyle="width:70px;text-align:right" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="GR_DISC_ID%{#ctn}" name="GR_DISC" value="%{GR_DISC}" cssStyle="width:60px;text-align:right" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="QTY_ID%{#ctn}" name="QTY" value="%{QTY_B}" cssStyle="width:50px;text-align:right" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           
                                                           <td style="font-size:8pt;text-align: left;"><s:textfield id="MOS_ID%{#ctn}" name="MOS" value="%{MOS_B}" cssStyle="width:40px;text-align:center" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: left;"><s:textfield id="PORT_ID%{#ctn}" name="PORT_L" value="%{PORT_B}" cssStyle="width:60px;text-align:left" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: left;"><s:textfield id="BUYER_ID%{#ctn}" name="BUYER_L" value="%{BUYER_B}" cssStyle="width:80px;text-align:left" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                           <td style="font-size:8pt;text-align: left;"><s:textfield id="DESTNCNTRY_ID%{#ctn}" name="DESTNCNTRY_L" value="%{DESTNCNTRY_B}" cssStyle="width:40px;text-align:left" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                        
                                                           <s:hidden name="LC_VALUE" value="%{LC_VALUE}" id="LC_VALUE%{#ctn}"/> 
                                                           <s:hidden name="EXP_UTIL" value="%{EXP_UTIL}" id="EXP_UTIL%{#ctn}"/> 
                                                           <s:hidden name="LC_PERCENT" value="%{LC_PERCENT}" id="LC_PERCENT%{#ctn}"/> 
                                                           <s:hidden name="XSTEPY" value="%{XSTEPY}" id="XSTEPY%{#ctn}"/> 
                                                           <s:hidden name="XSNODY" value="%{XSNODY}" id="XSNODY%{#ctn}"/> 
                                                           <s:hidden name="INV_YEAR" value="%{YEAR}" id="INV_YEAR%{#ctn}"/> 
                                                           <s:hidden name="INV_NO" value="%{INV_NO}" id="INV_NO%{#ctn}"/> 
                                                            <s:hidden name="CHA_L" value="%{CHA_B}" id="CHA_ID%{#ctn}"/> 
                                                      
                                                         </tr>
                                                            <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                                                       </s:iterator>
                                                      
                                                             
                                                      <s:iterator begin="%{#ctn}" end="%{#ctn+10}" status="st2">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#ctn}" /></td>
                                                            <td style="font-size:8pt;text-align: left"><s:textfield id="BILL_NO%{#ctn}" name="BILL_NO" value="%{BILL_NO}" cssStyle="text-transform:uppercase;width:80px;text-align:left" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: left"><sx:datetimepicker id="BILL_DATE%{#ctn}"  name="BILL_DATE"   value="%{BILL_DATE}"   endDate="%{MAXDATE}"   cssStyle="width:70px;text-align:left" displayFormat="dd/MM/yyyy"/></td>
                                                             <td style="font-size:8pt;text-align: left; width: 80px"><s:textfield id="INVOICE_NO%{#ctn}" name="INVOICE_NO" value="%{INVOICE_NO}" cssStyle="width:80px;text-align:left" theme="simple" onblur="if(this.value!='') validateInv(this,'INVOICE_NO%{#ctn}','%{#ctn}')"/>
                                                            </td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="GROSS_WT%{#ctn}" name="GROSS_WT" value="%{GROSS_WT}" cssStyle="width:60px;text-align:right" onblur="validatenumber(this); " theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="PKGS%{#ctn}" name="PKGS" value="%{PKGS}" cssStyle="width:40px;text-align:right" onblur="validatenumber1(this); " theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="LLC_NO%{#ctn}" name="LLC_NO" value="%{LLC_NO}" cssStyle="width:100px;text-align:left" onblur="if(this.value!='') searchlic(this,'LLC_NO%{#ctn}','%{#ctn}');validateLIC()" theme="simple"/></td>
                                                            <td>
                                                                   <a href="LCsearchPOSTSHIPMENT.action?indexname=<s:property value="%{#ctn}"/>" class="search"  target="addapprofrm"  onclick="openpop('approveraddid');" ><img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                            </td>
                                                              <td style="font-size:8pt;text-align: left"><sx:datetimepicker id="LET_EXP_DT%{#ctn}" name="LET_EXP_DT" value="%{LET_EXP_DT}"   endDate="%{MAXDATE}"  cssStyle="width:70px;text-align:left" displayFormat="dd/MM/yyyy"/></td>
                                                         
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="INR_C_ID%{#ctn}" name="INR_C" value="%{INR_CONV}" cssStyle="width:60px;text-align:right"  readonly="true" cssClass="textreadonly" theme="simple"  tabindex="-1"/></td>
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="DBK_C_ID%{#ctn}" name="DBK_C" value="%{DBK_CONV}" cssStyle="width:60px;text-align:right"  readonly="true" cssClass="textreadonly" theme="simple"  tabindex="-1"/></td>
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="CRNCY_ID%{#ctn}" name="CRNCY_CODE" value="%{CRNCY_CODE}" cssStyle="width:40px;text-align:center"  readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                         
                                                           <td style="font-size:8pt;text-align: right;"><s:textfield id="FOBAMT_ID%{#ctn}" name="FOBAMT" value="%{FOB_AMT}" cssStyle="width:70px;text-align:right" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="GR_DISC_ID%{#ctn}" name="GR_DISC" value="%{GR_DISC}" cssStyle="width:60px;text-align:right" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                        
                                                            <td style="font-size:8pt;text-align: right;"><s:textfield id="QTY_ID%{#ctn}" name="QTY" value="%{QTY}" cssStyle="width:50px;text-align:right" readonly="right" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                           
                                                           <td style="font-size:8pt;text-align:left;"><s:textfield id="MOS_ID%{#ctn}" name="MOS" value="%{MOS_ID}" cssStyle="width:40px;text-align:center"  readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                           <td style="font-size:8pt;text-align: left;"><s:textfield id="PORT_ID%{#ctn}" name="PORT_L" value="%{PORT_L}" cssStyle="width:60px;text-align:left" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                           <td style="font-size:8pt;text-align: left;"><s:textfield id="BUYER_ID%{#ctn}" name="BUYER_L" value="%{BUYER_L}" cssStyle="width:80px;text-align:left" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                            <td style="font-size:8pt;text-align: left;"><s:textfield id="DESTNCNTRY_ID%{#ctn}" name="DESTNCNTRY_L" value="%{DESTNCNTRY_L}" cssStyle="width:40px;text-align:left" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1"/></td>
                                                           
                                                           <s:hidden name="LC_VALUE" value="%{LC_VALUE}" id="LC_VALUE%{#ctn}"/> 
                                                           <s:hidden name="EXP_UTIL" value="%{EXP_UTIL}" id="EXP_UTIL%{#ctn}"/> 
                                                           <s:hidden name="LC_PERCENT" value="%{LC_PERCENT}" id="LC_PERCENT%{#ctn}"/> 
                                                           <s:hidden name="XSTEPY" value="%{XSTEPY}" id="XSTEPY%{#ctn}"/> 
                                                           <s:hidden name="XSNODY" value="%{XSNODY}" id="XSNODY%{#ctn}"/> 
                                                            <s:hidden name="INV_YEAR" value="%{YEAR}" id="INV_YEAR%{#ctn}"/> 
                                                           <s:hidden name="INV_NO" value="%{INV_NO}" id="INV_NO%{#ctn}"/> 
                                                            <s:hidden name="CHA_L" value="%{CHA_B}" id="CHA_ID%{#ctn}"/> 
                                                          </tr>
                                                          <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>   
                                                    </s:iterator>
                                                 
                                                </tbody>
                                                <tfooter>
                                                    <td></td><td></td><td></td> <td class="label-1" align="right">#</td> 
                                                       <td style="font-size:8pt;text-align: right;"><s:textfield  name="TOTGRWT1" id="TOTGRWT" value="%{TOTGRWT}" cssStyle="font-size:8pt;width:60px;text-align:right;font-weight:bold;color:red;background-color:yellow;" readonly="true"   theme="simple"/></td>
                                                       <td style="font-size:8pt;text-align: left;"colspan="7"><s:textfield  name="TOTPKGS1" id="TOTPKGS" value="%{TOTPKGS}" cssStyle="font-size:8pt;width:40px;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;" readonly="true"   theme="simple"/></td>
                                                       <td style="font-size:8pt;text-align: left;"  ><s:textfield  name="TOTFOB1" id="TOTFOB" value="%{TOTFOB}" cssStyle="width:70px;text-align:right;font-weight:bold;color:red;background-color:yellow;" readonly="true" cssClass="textreadonly" theme="simple"/></td>
                                                      <td style="font-size:8pt;text-align: left;"><s:textfield  name="TOTGR1" id="TOTGR" value="%{TOTGR}" cssStyle="width:60px;text-align:right;font-weight:bold;color:red;background-color:yellow;" readonly="true"  theme="simple"/></td>
                                                      <td style="font-size:8pt;text-align: left;"><s:textfield  name="TOTQTY1" id="TOTQTY" value="%{TOTQTY}" cssStyle="width:50px;text-align:right;font-weight:bold;color:red;background-color:yellow;" readonly="true"  theme="simple"/></td>
                                                       
                                                    
                                                </tfooter>
                                                
                                            </table>
                                              
                                        </div>  
                                      </td>
                                 </tr>
                                    </table> 
                                <tr><td> 
                                   <table bgcolor="#f1f1f0"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                       <tr>
                                                    <td align="center" >
                                                            <button  id="searchheadId" class="sexybutton" onclick="onbackedit('POSTSHIPMENT.action')"><span><span><span class="undo" id="searchId">Back</span></span></span></button>
                                                             <s:if test="%{FIN_DATE==null}">     
                                                               <button  id="saveheadId"  class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" tabindex="-1" >Save</span></span></span></button> 
                                                           </s:if>
                                                           <s:else>
                                                                   <button  id="saveheadId"  class="sexybutton"  disabled="true"><span><span><span class="save" id="saveId" tabindex="-1" >Save</span></span></span></button> 
                                                        
                                                           </s:else>
                                                              <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('newentryPOSTSHIPMENT.action');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                                               <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" tabindex="-1" >Exit</span></span></span></button> 
                                                      </td> 
                                                  </tr>    
                                   </table>
                                    </td>
                                </tr>
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