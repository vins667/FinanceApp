<%-- 
    Document   : BillOfSalesEntryJsp
    Created on : Mar 2, 2015, 11:47:04 AM
    Author     : Ranjeet
--%>

 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="css/sexybuttons.css"/>" rel="stylesheet"  type="text/css"/>


<html>
    <head>
        <script type="text/javascript" src="js/dom-drag.js"></script>
         <script src="js/jquery-1.9.1.min.js"></script>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
         
<script src="js/datetimepicker_css.js"> </script>
<script src="js/mvxexpbos.js"></script>

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
    
            function openpop(a)
            {
                
                document.getElementById(a).style.display='';
            }
             function closediv(a)
            {
                document.getElementById(a).style.display='none';
            }



function deletetablerow(x){
               
        document.getElementById('PKGS'+x).value="";
        document.getElementById('EXCS_INV_NO'+x).value="";
        document.getElementById('INV_NO'+x).value="";
        document.getElementById('INV_DESC'+x).value="";
        document.getElementById('QNTY'+x).value="";
        document.getElementById('AVG_RATE'+x).value="";
        document.getElementById('CRNCY'+x).value="";
        document.getElementById('FOB'+x).value="";
        document.getElementById('INR'+x).value="";
        document.getElementById('INR_CONV'+x).value="";
        document.getElementById('CFT_PLAN'+x).value="";
        document.getElementById('CFT_ACTUAL'+x).value="";
        document.getElementById('GRWT'+x).value="";
        document.getElementById('YEAR'+x).value="";
        document.getElementById('COMPANY'+x).value="";
        document.getElementById('UOM'+x).value="";
        totalpalnqty();
        totalfobqty();
        totalinvqty(); 
        totalctnqty();
                 
    }


  function onsave()  
   {
                if(validatein())
                {
                	checkUncheck();
               
      		if(confirm('Do You Want to Save Record(s) ?'))
       		{    	 	
      		$("#backheadId").attr("disabled","disabled");
    		$("#backId").text("Please wait....");
    	        $("#saveheadId").attr("disabled","disabled");
    	        $("#saveId").text("Please wait....");
    	        $("#clearheadId").attr("disabled","disabled");
    	        $("#clearId").text("Please wait....");
    	        document.frm.action="savebillofsalesAction.action";
    	        document.frm.submit();
       		document.getElementById('prepage').style.visibility = '';
       		}
            }
       
   }
  
   
  function validatein() 
  {
      if(document.getElementById('BUYER').value=="") 
          {
             alert("Please Select Buyer!!");
              document.getElementById('BUYER').focus();
              return false;
              
          }
          if(document.getElementById('TRANSPORTER').value=="") 
          {
             alert("Please Select Transporter!!");
              document.getElementById('TRANSPORTER').focus();
              return false;
              
          }
           if(document.getElementById('PORT').value=="") 
          {
              alert("Please Select Port!!");
              document.getElementById('PORT').focus();
              return false;
              
          }
      
    
      var CO_NUMB=document.frm.EXCS_INV_NO;
     
      if(document.frm.INV_NO)
          {
             if(CO_NUMB.length>0)
                  {
                      for(i=0; i<CO_NUMB.length; i++)
                          {
                              for(j=i+1; j<CO_NUMB.length; j++)
                                  {
                                      if(CO_NUMB[i].value!=""){
                                      if(CO_NUMB[i].value == CO_NUMB[j].value) 
                                          {
                                              alert('Duplicate entry not allowed!!!');
                                                return false;
                                          }
                                      }
                                  }
                          }
                  }
          }else{
              alert("Please Enter Details !!");
              return false;
          }
      
      return true;
  }
   
   function validateDate(a){

    var dt = a.value;
    var pattern =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;
    if (dt!="" && !pattern.test(dt))
    {
       alert("The Date Format Should be :  dd/mm/yyyy")
       a.value="";
       a.focus();
        return false;
    }
    
    return true;
   }

    
    
   function validateSPChar(a)
   {    k=a.value;
       
        var pattern = new RegExp(/[~`!#$%\^&*+=\-\[\]\\';,/{}|\\":<>\?]/); //unacceptable chars
         if (pattern.test(k)) {
           alert("Please only use standard alphanumerics");
           a.focus();
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
                totalctnqty();
            	return true;
                
            }
            
             function validatenumber1(a,b)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="0";
                        fobcal(b);
                        a.focus();
            		return false;
            	}
              fobcal(b);
            	return true;
            }

function fobcal(a)
{
     var t=0; var t1=0;
    if(document.getElementById('QNTY'+a).value!="")
        {     
            t=eval(document.getElementById('QNTY'+a).value)*eval(document.getElementById('AVG_RATE'+a).value);
            t1=eval(document.getElementById('QNTY'+a).value)*eval(document.getElementById('AVG_RATE'+a).value)*eval(document.getElementById('INR_CONV'+a).value);
        }
        
         document.getElementById('FOB'+a).value=t.toFixed(2);
         document.getElementById('INR'+a).value=t1.toFixed(2);
       totalpalnqty(); 
       totalfobqty();
       totalinvqty();
       
} 

function totalpalnqty()
{
   
    var PLAN_QNTY=document.frm.QNTY;
    
    if(document.frm.QNTY)
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
    
    
        
        document.getElementById('QNTY_TOTAL').value=t.toFixed();
   
}
function totalctnqty()
{
   
    var PLAN_QNTY=document.frm.PKGS;
    var CBM_TOTAL=document.frm.CBM;
    var VOL_TOTAL=document.frm.VOL;
    var GRWT_TOTAL=document.frm.GRWT;
    var CFTPLAN_TOTAL=document.frm.CFT_PLAN;
    var CFTACT_TOTAL=document.frm.CFT_ACTUAL;
    
    var t=0; var t1=0; var t2=0; var t3=0; var t4=0; var t5=0;
    if(document.frm.PKGS)
        {
          if(PLAN_QNTY.length>0)  
              {
                  for(m=0; m<PLAN_QNTY.length; m++)
                      {
                          if(!isNaN(eval(PLAN_QNTY[m].value))){
                            t=t+eval(PLAN_QNTY[m].value) 
                            t1=t1+eval(CBM_TOTAL[m].value)
                            t2=t2+eval(VOL_TOTAL[m].value)
                            t3=t3+eval(GRWT_TOTAL[m].value)
                            t4=t4+eval(CFTPLAN_TOTAL[m].value)
                            t5=t5+eval(CFTACT_TOTAL[m].value)
                          }
                      }
              }else{
                   t=t+eval(PLAN_QNTY.value) 
                   t1=t1+eval(CBM_TOTAL.value) 
                   t2=t2+eval(VOL_TOTAL.value) 
                   t3=t3+eval(GRWT_TOTAL.value) 
                   t4=t4+eval(CFTPLAN_TOTAL.value)
                   t5=t5+eval(CFTACT_TOTAL.value)
                   
              }
        } 
         document.getElementById('CTN_TOTAL').value=t.toFixed();
         document.getElementById('CBM_TOTAL').value=t1.toFixed(2);
         document.getElementById('VOL_TOTAL').value=t2.toFixed(2);
         document.getElementById('GRWT_TOTAL').value=t3.toFixed(2);
         document.getElementById('CFTPLAN_TOTAL').value=t4.toFixed(2);
         document.getElementById('CFTACT_TOTAL').value=t5.toFixed(2);
    
}
function totalfobqty()
{
   
    var PLAN_QNTY=document.frm.FOB;
    var t=0;
    if(document.frm.FOB)
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

function totalinvqty()
{ 
    
    var PLAN_QNTY=document.frm.INR;
    var t=0;
    if(document.frm.INR)
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
        
        document.getElementById('INR_CONV_TOTAL').value=t.toFixed(2);
   
}

function printflag(a,b)
{
    if(a.checked)
        {
            document.getElementById('PRINT_DATE_FLAG'+b).value="Y";
        }else{
             document.getElementById('PRINT_DATE_FLAG'+b).value="N";
        }
}
function fycancel()
 {
      if(document.getElementById("CANCEL_DATE").checked)
        {    document.getElementById('CANCEL_DATE').value="Y";
             document.getElementById('FY_CANCEL').checked=true;
             document.getElementById('FY_CANCEL').value="Y";
        }else{
          
             document.getElementById('CANCEL_DATE').value="N";
             document.getElementById('FY_CANCEL').checked=false;
             document.getElementById('FY_CANCEL').value="N";
        }
 }
  function haltcal()
  {   date1= new Date();
      date2 = new Date();
      diff  = new Date();
         var dt1=document.getElementById('VCH_ARV_DATE').value;
         aa1=dt1.substring(0,2);
         bb1=dt1.substring(3,5);
         cc1=dt1.substring(6,16);
         dd1=bb1+'-'+aa1+'-'+cc1;
         var dt2=document.getElementById('VCH_DEP_DATE').value;
         aa2=dt2.substring(0,2);
         bb2=dt2.substring(3,5);
         cc2=dt2.substring(6,16);
         dd2=bb2+'-'+aa2+'-'+cc2
        date1temp = new Date(dd1);
        date1.setTime(date1temp.getTime());  
        date2temp = new Date(dd2);
        date2.setTime(date2temp.getTime());
        diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
        timediff = diff.getTime();
        diffHours = timediff / 3600000;
        days = Math.floor(timediff / (1000 * 60 * 60 )); 
        document.getElementById('HALT_HRS').value=diffHours;
        document.getElementById('FY_HALT').checked=true;;
  }
   

function disflag(a,b)
{
    if(a.checked)
        {
            document.getElementById('DISPATCH_YN'+b).value="Y";
            
        }else{
             document.getElementById('DISPATCH_YN'+b).value="N";
        }
}


 


             function isNumber(evt) {
                    evt = (evt) ? evt : window.event;
                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                     alert('Invalid Input, Only Numbers Allowed');
                    return false;
                    }
                    return true;
                }
             function clearpck(){
            	 document.getElementById("CONTROL_NUMB").value='';
             }
             
             
       function getdescbuyer(a)  
       {  
           document.getElementById('DISPLAY_ITEM_DESC').value=document.getElementById('ITEM_DESC'+a).value;
           document.getElementById('DISPLAY_BUYER').value=document.getElementById('BUYER_NAME'+a).value;
       }
       
       function fTestDateTime(field) {
            rx = /^((((31\/(0?[13578]|1[02]))|((29|30)\/(0?[1,3-9]|1[0-2])))\/(1[6-9]|[2-9]\d)?\d{2})|(29\/0?2\/(((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))|(0?[1-9]|1\d|2[0-8])\/((0?[1-9])|(1[0-2]))\/((1[6-9]|[2-9]\d)?\d{2})) (20|21|22|23|[0-1]?\d):[0-5]?\d$/
            r  = rx.test(field);

            return r;
            }

function validateDateTime(a) {
 
if(a.value!="" && fTestDateTime(a.value)==false)
    {
       alert("The Date Format Should be :  dd/mm/yyyy hh:mm")
       a.value="";
       a.focus();
        return false;
    }
    
    return true;
}


function checkUncheck()
{
        if(document.getElementById("GPRS_YN").checked)
        	{
        		document.getElementById("GPRS_YN").value="Y";
        	}
       	 else
        	{
       		  document.getElementById("GPRS_YN").value="N";
        	}
        
        if(document.getElementById("LEASE_YN").checked)
    	{
    		document.getElementById("LEASE_YN").value="Y";
    	}
   	   else
    	{
   		  document.getElementById("LEASE_YN").value="N";
    	}
        if(document.getElementById("CANCEL_DATE").checked)
        {     document.getElementById('CANCEL_DATE').value='Y';
              document.getElementById('FY_CANCEL').checked=true;
              document.getElementById('FY_CANCEL').value="Y";
        }else{
             document.getElementById('CANCEL_DATE').value="N";
             document.getElementById('FY_CANCEL').value="N";
        }
}

        </script>
        
        <style type="text/css">
            
          th {
        font-size:9pt;
        font-weight:bold;
        color:white;
        background-image:url('../image/style13.jpg');
    }
      tbody {
        height: 500px;
        overflow-y: auto;
        overflow-x: hidden;

     }
     
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
 
     
     
        </style>

  <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height: 510px;
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 28px ;
        }

         }
        thead tr {

        }
        tbody {
            height: auto;
        }
          }
    </style>
<![endif]-->
        
    </head>
    <body style="background-color:#f2f2f2;padding: 0px;margin: 0px" >
    <DIV align="center" id="prepage" class="lodingdiv" style="right:400px;position:absolute;z-index: 1;visibility: hidden;background: transparent;top:50px;" >
			<img src="image/pleaseWaitOverlay.gif" />			
	</DIV>
        <form action="" onsubmit="" style="padding: 0px;margin: 0px"  method="post" name="frm" >
           <s:hidden name="SEARCH_WAREHOUSE" id="SEARCH_WAREHOUSE"/>
           <s:hidden name="SERACH_PLAN_NUMB" id="SERACH_PLAN_NUMB"/>
            <s:hidden name="SEARCH_INVOICE" id="SEARCH_INVOICE"/>
           <s:hidden name="SEARCH_PLAN_DATE" id="SEARCH_PLAN_DATE"/>
           <s:hidden name="SEARCH_TOPLAN_DATE" id="SEARCH_TOPLAN_DATE"/>
           <s:hidden name="SEARCH_SUBMIT" id="SEARCH_SUBMIT"/>
            <table align="center" width="100%">
                <tr><td valign="top" style="border-width:1px;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr><td width="100%" colspan="2"   class="hd" style="text-align:center">Bill Of Sale Entry</td></tr>
                  <tr><td colspan="2">
                          <table border="0" cellpadding="0" cellspacing="1">
                              
                                <tr><td class="label-1">
                                 Type 
                              </td>
                              <td>
                              
                                  <s:if test="%{BOS_NO==null && INTERUNIT!=null && INTERUNIT.length()>0}">
                                      <b style="color:#006699"><s:property value="%{INTERUNIT_DIS}"/></b> 
                                      <s:hidden name="INTERUNIT" value="%{INTERUNIT}"/>
                                      <s:hidden name="INTERUNIT_DIS" value="%{INTERUNIT_DIS}"/>
                                       <%--  <s:textfield name="INTERUNIT_DIS" readonly="true" cssClass="textreadonly"  theme="simple" cssStyle="width:130px" value="%{INTERUNIT_DIS}"/> --%>
                                      <%--  <s:select name="INTERUNIT" 
                                           id="INTERUNIT" 
                                           list="# {'N':'INTER STATE','L':'LOCAL','Y':'INTER UNIT','C':'CONTAINER'}"
                                           cssStyle="width:130px" 
                                           cssClass="texts" 
                                           theme="simple"/> --%>
                                  </s:if>
                                  <s:else>
                                      <b style="color:#006699"><s:property value="%{INTERUNIT_DIS}"/></b> 
                                      <s:hidden name="INTERUNIT" value="%{INTERUNIT}"/>
                                      <s:hidden name="INTERUNIT_DIS" value="%{INTERUNIT_DIS}"/>
                                     <%--  <s:hidden name="INTERUNIT" value="%{INTERUNIT}"/>
                                      <s:textfield name="INTERUNIT_DIS" readonly="true" cssClass="textreadonly"  theme="simple" cssStyle="width:130px" value="%{INTERUNIT_DIS}"/> --%>
                                  </s:else>
                              </td>
                               <td class="label-1"  align="right"></td>
                               <td> 
                               </td>
                               <td style="width:100px"></td>
                               <td class="label-1">Driver Name</td>
                               <td colspan="3"><s:textfield name="DRIVER_NAME" 
                                           id="DRIVER_NAME" 
                                           maxlength="50"
                                           cssStyle="width:360px" 
                                           cssClass="texts"
                                           theme="simple" tabindex="13"/>
                               </td>
                               
                              <td class="label-1">GPRS&nbsp;&nbsp;
                           
                                  <s:if test='%{GPRS_YN=="Y"}'>
                                      <input type="checkbox" name="GPRS_YN" value="Y" checked="checked"/>
                                  </s:if>
                                  <s:else>
                                 	 <input type="checkbox" name="GPRS_YN" value="N"/>
                                  </s:else>
                                </td>
                               
                               </tr>
                          <tr><td class="label-1">
                         Location 
                              </td><td >
                                  <s:if test="%{BOS_NO==null}">
                                      <%--warehouselist --%>
                                  <s:select name="LOCATION_CODE" 
                                           id="LOCATION_CODE" 
                                           list="warehouselist"
                                           cssStyle="width:130px" 
                                           cssClass="texts" 
                                           theme="simple" tabindex="1"/>
                                  </s:if>
                                  <s:else>
                                      <s:textfield name="LOCATION_CODE" readonly="true" cssClass="textreadonly"  theme="simple" cssStyle="width:130px" value="%{LOCATION_CODE}" tabindex="-1"/>
                                  </s:else>
                              </td>
                                                                                          
                              <td class="label-1" style="width:103px" align="right">Ship Mode</td><td> 
                              	
                                  <s:textfield name="SHIP_MODE" 
                                           id="SHIP_MODE" 
                                           maxlength="10"
                                          value="%{SHIP_MODE}"
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           cssClass="texts"
                                           readonly="false" 
                                           theme="simple" tabindex="10" onblur="if(this.value!='') xmlhttpreqshipmode(this)" />
                                        <a href="ShipmodebillofsalesAction.action?PARAA=SHIP_MODE&PARAB=SHIP_MODE&TYPE_CODE=MODL" target="handlefrm" onclick="openpop('root')" tabindex="-1">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/> </a>
                              </td>
                               <td>
                              </td> 
                               <td class="label-1">Driver Mobile</td>
                               <td colspan="3"><s:textfield name="DRIVER_MOBILE" 
                                           id="DRIVER_MOBILE" 
                                           maxlength="25"
                                           cssStyle="width:360px" 
                                           cssClass="texts"
                                           theme="simple" tabindex="14"/>
                               </td>
                                <td class="label-1">Lease&nbsp;
                           
                                  <s:if test='%{LEASE_YN=="Y"}'>
                                      <input type="checkbox" name="LEASE_YN" value="Y" checked="checked"/>
                                  </s:if>
                                  <s:else>
                                 	 <input type="checkbox" name="LEASE_YN" value="N"/>
                                  </s:else>
                                </td>
                          </tr>
                           <tr><td class="label-1">
                             BOS Number 
                              </td><td><s:textfield name="BOS_NO" 
                                           id="BOS_NO" 
                                           cssStyle="width:130px" 
                                           readonly="true"
                                           value="%{BOS_NO}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/></td>
                           
                              <td class="label-1"  align="right">L R No.</td>
                              <td><s:textfield name="LR_NO" 
                                           id="LR_NO" 
                                           maxlength="20"
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           cssClass="texts"
                                            theme="simple" tabindex="11"/></td>
                              <td></td>
                               <td class="label-1">D/L No.</td>
                               <td colspan="3"> <s:textfield name="DL_NO" 
                                           id="DL_NO" 
                                           maxlength="25"
                                           cssStyle="width:360px;text-transform:uppercase" 
                                           cssClass="texts"
                                           theme="simple" tabindex="15"/>
                               </td>
                                <td class="label-1">Return
                           
                              	   <s:if test="%{RETURN_CARGO==null}">
                                      <input type="checkbox" value="Y" name="RETURN_YN" id="RETURN_YN" />
                                  </s:if>
                                      <s:else>
                                          <s:property value="%{RETURN_CARGO}"/>
                                      </s:else>
                                </td>
                          </tr>
                           <tr><td class="label-1">
                         BOS Date 
                              </td><td><s:textfield name="BOS_DATE" 
                                           id="BOS_DATE" 
                                           cssStyle="width:130px" 
                                           readonly="true"
                                           value="%{BOS_DATE}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/></td>
                          <td class="label-1"  align="right">
                         L R Date 
                              </td><td>
                                   
                                  <s:textfield name="LR_DATE" 
                                           id="LR_DATE" 
                                           cssStyle="width:130px" 
                                           onblur="validateDate(this)"
                                           value="%{LR_DATE}"
                                           cssClass="texts" 
                                           theme="simple" tabindex="12"/>
                                          
                              </td>
                              <td></td>
                               <td class="label-1">Vehicle Type</td>
                               <td colspan="3"><table cellpadding="1" cellspacing="0"><td><s:textfield name="VEHICLE_TYPE" 
                                           id="VEHICLE_TYPE" 
                                           cssStyle="width:50px;text-transform:uppercase" 
                                           value="%{VEHICLE_TYPE}"
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" tabindex="16" onblur="if(this.value!='') xmlhttpreqcfs(this,'VEHICLE_TYPE_DESC')" 
                                            />
                                           </td>
                                  <td><a href="cfsViewbillofsalesAction.action?PARAA=VEHICLE_TYPE&PARAB=VEHICLE_TYPE_DESC&TYPE_CODE=BOS" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                             </td>
                                  <td><s:textfield name="VEHICLE_TYPE_DESC" 
                                           id="VEHICLE_TYPE_DESC" 
                                           cssStyle="width:290px" 
                                           value="%{VEHICLE_TYPE_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/></td>
                                   </table>
                               </td>
                                
                              <td class="label-1">Cancel
                                  <s:if test="%{CANCEL_DATE==null}">
                                      <input type="checkbox"  id="CANCEL_DATE" name="CANCEL_DATE"  onclick="fycancel()" />
                                  </s:if>
                                      <s:else>
                                          <s:property value="%{CANCEL_DATE}"/>
                                          
                                      </s:else>
                              </td>
                          </tr> 
                          <tr><td class="label-1">
                         Buyer 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="BUYER" 
                                           id="BUYER" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="10"
                                           value="%{BUYER}"
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" onblur="if(this.value!='') xmlhttpreqbuyer(this,'BUYER_DESC')" 
                                           tabindex="1"/>
                                              <s:hidden name="BUYER_ADDR" id="BUYER_ADDR" value="%{BUYER_ADDR}"/>
                              </td><td>
                               <a href="getbuyeraddbillofsalesAction.action" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="BUYER_DESC" 
                                           id="BUYER_DESC" 
                                           cssStyle="width:235px" 
                                           readonly="true"
                                           value="%{BUYER_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/>
                              </td>
                              <td>
                                  <input type="button" class="texts" style="width:30px;" onclick="alert(document.getElementById('address').value)" name="addressno" id="addressno" value="<s:property value="%{BUYER_ADDR}"/>"/>
                                  <input type="hidden" name="address" value="<s:property value="%{BUYER_ADDRESS_NAME}"/>" id="address"/> </td>
                              
                              <td class="label-1">Vehicle No</td>
                              <td  style="width:100px">
                                <s:textfield name="VEHICLE_NO" 
                                           id="VEHICLE_NO" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="20"
                                           value="%{VEHICLE_NO}"
                                           onblur="validateSPChar(this)"
                                           cssClass="texts" 
                                           theme="simple" tabindex="17"/>  
                              </td>
                              
                         <td class="label-1">Seal No</td>
                              <td>
                                 <s:textfield name="SEAL_NO" 
                                           id="SEAL_NO" 
                                           cssStyle="width:120px;text-transform:uppercase" 
                                           maxlength="20"
                                           value="%{SEAL_NO}"
                                           cssClass="texts" 
                                          tabindex="18"
                                           theme="simple"/>  
                              </td>
                          </tr>
                          
                           <tr>
                               <td class="label-1">
                               CHA 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="CHA" 
                                           id="CHA" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="10"
                                           value="%{CHA}"
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" tabindex="2" onblur="if(this.value!='') xmlhttpreqagent(this,'CHA_DESC')" 
                                            />
                                             
                                        <s:hidden name="CHA_ADDR" id="CHA_ADDR" value="%{CHA_ADDR}" />
                              </td><td>
                               <a href="agentsearch.jsp" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="CHA_DESC" 
                                           id="CHA_DESC" 
                                           cssStyle="width:235px" 
                                           readonly="true"
                                           value="%{CHA_DESC}"
                                           cssClass="textreadonly"  
                                           theme="simple" tabindex="-1"/>
                                  
                              </td>
                              
                               <td>
                                   <input type="button" class="texts" style="width:30px;" onclick="alert(document.getElementById('cha_address').value)" name="cha_addressno" id="chaaddressno" value="<s:property value="%{CHA_ADDR}"/>"/>
                                   <input type="hidden" id="address" name="cha_address" value="<s:property value="%{CHA_ADDR}"/>" /> 
                               </td>
                                
                                   
                           
                              <td class="label-1">Fy In Date</td>
                                <td>  <input type="Text" name="VCH_ARV_DATE"  value="<s:property value="%{VCH_ARV_DATE}"/>" id="VCH_ARV_DATE"   maxlength="25" size="25" style="width:130px"  tabindex="18"/>
                                            <img src="image/cal.gif" onclick="javascript:NewCssCal('VCH_ARV_DATE','ddMMyyyy','dropdown',true,'24')" style="cursor:pointer"/>
                         
                               
                                    
                              <td class="label-1">Halting Hrs</td>
                              <td>
                                 <s:textfield name="HALT_HRS" 
                                           id="HALT_HRS" 
                                           cssStyle="width:120px" 
                                           maxlength="10"
                                           value="%{HALT_HRS}"
                                           cssClass="texts" 
                                          tabindex="24"
                                           theme="simple"/>  
                              </td>
                          </tr>
                           <tr><td class="label-1">
                         From Unit 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="UNIT" 
                                           id="UNIT" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="10"
                                           value="%{UNIT}"
                                           cssClass="texts" 
                                           readonly="flase"
                                           theme="simple" tabindex="3" onblur="if(this.value!='') xmlhttprequnit(this,'UNIT_DESC')" 
                                           />
                              </td><td>
                               <a href="unitViewbillofsalesAction.action?PARAA=UNIT&PARAB=UNIT_DESC" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="UNIT_DESC" 
                                           id="UNIT_DESC" 
                                           cssStyle="width:235px;text-transform:uppercase" 
                                           readonly="true"
                                           value="%{UNIT_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                              </td>
                              
                               <td></td>
                              <td class="label-1">Fy Out Date</td>
                              <td>  <input type="Text" name="VCH_DEP_DATE"  value="<s:property value="%{VCH_DEP_DATE}"/>" id="VCH_DEP_DATE"   maxlength="25" size="25" style="width:130px"  tabindex="19"   onblur="haltcal();"/>
                                            <img src="image/cal.gif" onclick="javascript:NewCssCal('VCH_DEP_DATE','ddMMyyyy','dropdown',true,'24')" style="cursor:pointer"/>
                               </td> 
                              </td>
                               <td class="label-1">Damage</td>
                              <td>
                                 <s:textfield name="DAMAGE" 
                                           id="DAMAGE" 
                                           cssStyle="width:120px" 
                                           maxlength="40"
                                           value="%{DAMAGE}"
                                           cssClass="texts" 
                                           theme="simple" tabindex="26"/>  
                              </td>
                          </tr>
                           <tr> 
                          <s:if test='%{INTERUNIT=="Y" || INTERUNIT=="C"}'> 
                             <td class="label-1">To Unit </td><td><table cellpadding="0" cellspacing="0"><tr><td>
                                              <s:textfield name="UNIT_TO" 
                                           id="UNIT_TO" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="10"
                                           value="%{UNIT_TO}" 
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" tabindex="4" onblur="if(this.value!='') xmlhttprequnit(this,'UNIT_TO_DESC')" 
                                           />
                              </td><td>
                               <a href="unitViewbillofsalesAction.action?PARAA=UNIT_TO&PARAB=UNIT_TO_DESC" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                             
                              <td colspan="2">
                                  <s:textfield name="UNIT_TO_DESC" 
                                           id="UNIT_TO_DESC" 
                                           cssStyle="width:235px;text-transform:uppercase" 
                                           readonly="true"
                                           value="%{UNIT_TO_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                              </td>
                               </s:if><s:else>
                                      <td></td><td></td> <td></td><td></td>
                                  </s:else>
                               <td></td>
                                
                              <td class="label-1">Reporting Date</td>
                              
                              <td>  <input type="Text" name="VCH_REP_DATE"  value="<s:property value="%{VCH_REP_DATE}"/>" id="VCH_REP_DATE"   maxlength="25" size="25" style="width:130px" tabindex="20"/>
                                            <img src="image/cal.gif" onclick="javascript:NewCssCal('VCH_REP_DATE','ddMMyyyy','dropdown',true,'24')" style="cursor:pointer"/>
                               </td>
                             
                              <td class="label-1">Container No</td><td> <s:textfield name="CONTAINER_NO" 
                                           id="CONTAINER_NO" 
                                           cssStyle="width:120px;text-transform:uppercase" 
                                           maxlength="50"
                                           value="%{CONTAINER_NO}"
                                           cssClass="texts" 
                                           
                                           theme="simple" tabindex="28"/></td>
                          </tr>
                          
                           <tr><td class="label-1">
                         Transporter 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td>
                                              <s:textfield name="TRANSPORTER" 
                                           id="TRANSPORTER" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="10"
                                           value="%{TRANSPORTER}"
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" tabindex="5" onblur="if(this.value!='') xmlhttpreqtpt(this,'DISPATCH_VIA')" 
                                            />
                              </td><td>
                                  
                               <a href="agentViewbillofsalesAction.action?PARAA=TRANSPORTER&PARAB=DISPATCH_VIA" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="DISPATCH_VIA" 
                                           id="DISPATCH_VIA" 
                                           cssStyle="width:235px;text-transform:uppercase" 
                                           readonly="true"
                                           value="%{DISPATCH_VIA}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                              </td>
                             
                               <td></td>
                               <td class="label-1">Cutoff Date</td>
                                 <td>  <input type="Text" name="CUTOFF_DATE"  value="<s:property value="%{CUTOFF_DATE}"/>" id="CUTOFF_DATE"   maxlength="25" size="25" style="width:130px" tabindex="21"/>
                                            <img src="image/cal.gif" onclick="javascript:NewCssCal('CUTOFF_DATE','ddMMyyyy','dropdown',true,'24')" style="cursor:pointer" tabindex="-1"/>
                                  </td> 
                                         
                              
                             
                             
                              <td class="label-1">Type</td><td> 
                                  <s:select name="CONTAINER_TYPE"
                                            id="CONTAINER_TYPE"
                                            cssStyle="width:120px"
                                            list="# {'CARTONS':'CARTONS','GOH':'GOH'}"
                                            value="%{CONTAINER_TYPE}"
                                            theme="simple"
                                             cssClass="texts" tabindex="29"
                                            />
                                            
                                 </td>
                          </tr>
                           <tr><td class="label-1">
                         From Port 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="PORT" 
                                           id="PORT" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="10"
                                           value="%{PORT}"
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" tabindex="6" onblur="if(this.value!='') xmlhttpreqport(this,'PORT_DESC')" 
                                            />
                              </td><td>
                               <a href="portViewbillofsalesAction.action?PARAA=PORT&PARAB=PORT_DESC&TYPE_CODE=HAFE" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="PORT_DESC" 
                                           id="PORT_DESC" 
                                           cssStyle="width:235px" 
                                           readonly="true"
                                           value="%{PORT_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                              </td>
                              <td></td>
                              <td class="label-1"> </td>
                              <td>
                                
                              </td>
                              <td class="label-1"> </td>
                              <td>
                                  
                              </td>
                          </tr>
                           <tr><td class="label-1">
                         To Destn 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="DESTINATION" 
                                           id="DESTINATION" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="10"
                                           value="%{DESTINATION}"
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" tabindex="7" onblur="if(this.value!='') xmlhttpreqport(this,'DESTINATION_DESC')" 
                                            />
                              </td><td>
                               <a href="portViewbillofsalesAction.action?PARAA=DESTINATION&PARAB=DESTINATION_DESC&TYPE_CODE=CSCD" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="DESTINATION_DESC" 
                                           id="DESTINATION_DESC" 
                                           cssStyle="width:235px" 
                                           readonly="true"
                                           value="%{DESTINATION_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                              </td>
                               <td></td>
                              
                              <td class="label-1">Truck Size</td>
                              <td>
                                 <s:textfield name="ACTUAL_SIZE" 
                                           id="ACTUAL_SIZE" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{ACTUAL_SIZE}"
                                           cssClass="texts" 
                                           theme="simple" tabindex="22"/>  
                              </td>
                              <td class="label-1"> </td>
                              <td>
                                 
                              </td>
                               <td class="label-1">Factory Halt</td>
                              <td>
                                  <s:if test='%{FY_HALT=="Y"}'>
                                      <input type="checkbox" checked="true" id="FY_HALT" name="FY_HALT" value="Y"/>
                                  </s:if>
                                  <s:else>
                                  <input type="checkbox" name="FY_HALT" id="FY_HALT" value="Y"/>
                                  </s:else>
                                
                              </td>
                          </tr>
                           <tr><td class="label-1">
                        Remarks 
                               </td><td colspan="3">
                                  <s:textfield name="REMARKS" 
                                           id="REMARKS" 
                                           cssStyle="width:380px;text-transform:uppercase" 
                                           maxlength="100"
                                           value="%{REMARKS}"
                                           cssClass="texts" 
                                           theme="simple" tabindex="9"/>
                                  
                              </td>
                               <td></td>
                              <td class="label-1">CFS NAME</td>
                              <td>
                                  <table cellpadding="0" cellspacing="0"><tr><td>
                                <s:textfield name="CFS_CODE" 
                                           id="CFS_CODE" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="20"
                                           value="%{CFS_CODE}"
                                           cssClass="texts" 
                                           readonly="false"
                                           theme="simple" tabindex="22" onblur="if(this.value!='') xmlhttpreqcfs(this,'CFS_DESC')" 
                                            /> 
                                </td><td>
                               <a href="cfsViewbillofsalesAction.action?PARAA=CFS_CODE&PARAB=CFS_DESC&TYPE_CODE=CFS" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                              </td>
                                      </tr></table>
                              </td>
                             
                              <td colspan="2">
                                 <s:textfield name="CFS_DESC" 
                                           id="CFS_DESC" 
                                           cssStyle="width:205px" 
                                           readonly="true"
                                           value="%{CFS_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>  
                              </td>
                              <td class="label-1">Factory Cancel</td>
                              <td>
                                  <s:if test='%{FY_CANCEL=="Y"}'>
                                      <input type="checkbox" id="FY_CANCEL" checked="true" name="FY_CANCEL" value="Y"/>
                                  </s:if>
                                  <s:else>
                                     <input type="checkbox" id="FY_CANCEL" name="FY_CANCEL" value="Y"/>
                                  </s:else>
                                
                              </td>
                          </tr>
                        
                          </table>
              </td> 
               
               </tr> 
                <tr><td align="center"  colspan="2">
                        <table cellpadding="0" cellspacing="0" width="100%">
                            <tr><td align="center">
                            <button id="backheadId" class="sexybutton" onclick="javascript:window.location.href('billofsalesAction.action?SEARCH_WAREHOUSE=<s:property value="%{SEARCH_WAREHOUSE}"/>&SERACH_PLAN_NUMB=<s:property value="%{SERACH_PLAN_NUMB}"/>&SEARCH_INVOICE=<s:property value="%{SEARCH_INVOICE}"/>&SSEARCH_PLAN_DATE=<s:property value="%{SEARCH_PLAN_DATE}"/>&SSEARCH_TOPLAN_DATE=<s:property value="%{SEARCH_TOPLAN_DATE}"/>&SEARCH_SUBMIT=<s:property value="%{SEARCH_SUBMIT}"/>');"><span><span><span class="undo" id="backId">Back</span></span></span></button> &nbsp;
                           
                            <s:if test="%{(CANCEL_DATE==null || CANCEL_DATE.length()==0) && DDOC_FWD==null }" >
                                <button id="saveheadId" class="sexybutton" onclick="onsave();"><span><span><span class="save" id="saveId">Save</span></span></span></button> &nbsp;
                            </s:if>
                            <button id="clearheadId" class="sexybutton" onclick="javascript:window.location.href('clearplanbillofsalesAction?INTERUNIT=<s:property value="%{INTERUNIT}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
		            &nbsp;
                            <s:if test="%{BOS_NO!=null && BOS_NO.length()>0}">
                               <button id="printheadId" class="sexybutton" onclick="javascript:window.location.href('printbillofsalesJsp.jsp?LOCATION_CODE=<s:property value="%{LOCATION_CODE}"/>&BOS_NO=<s:property value="%{BOS_NO}"/>&BOS_DATE=<s:property value="%{BOS_DATE}"/>');"><span><span><span class="print" id="printId">Pdf</span></span></span></button>
		           </s:if>						
												
                        
                                </td></tr></table>
                                </td></tr>
              </table>
                   </td></tr>                
                               
                <tr><td style="border-width:1px;border-color:black;border-style:solid;" >

                        <div style="height: 150px;width:1292px; overflow-y:scroll">
                        <table  cellpadding="0" cellspacing="1">
                                          <thead>
                          <tr  style="background-color: #cccccc;text-align: left" >
                               <th class="label-1"   style="width:12px"></th>
                               <th class="label-1" style="height:25px;width: 40px" >Ctns.</th>
                               <th class="label-1"  style="width:60px">Invoice#</th>
                               <th class="label-1"  style="width:15px"></th>
                               <th class="label-1" style="width:200px">Inv Description</th>
                               <th class="label-1" style="width:60px" align="right">Qnty</th>
                               <th class="label-1" style="width:60px"  align="right">Avg Rate</th>
                               <th class="label-1" style="width:50px">Crncy</th>
                               <th class="label-1"  style="width:60px"  align="right">FOB</th>
                               <th class="label-1"  style="width:70px"  align="right">INR</th>
                               <th class="label-1" style="width:60px"   align="right">Plan CFT</th>
                               <th class="label-1" style="width:60px"   align="right">Actual CFT</th>
                               <th class="label-1" style="width:60px"   align="right">CBM</th>
                               <th class="label-1" style="width:60px"   align="right">Volume</th>
                               <th class="label-1" style="width:60px"   align="right">GR.WT.</th>
                               <th class="label-1"  style="width:30px">Print</th>
                               <th class="label-1"  style="width:30px">T/O</th>
                               <th class="label-1"  style="width:60px">Fy Ctn</th>
                               <th class="label-1"  style="width:60px">Fy Qnty</th>
                               <th class="label-1"  style="width:60px">Destn.</th>
                               <th class="label-1"  style="width:60px">Port</th>
                               <th class="label-1"  style="width:70px">CHA</th>
                               <th class="label-1"  style="width:70px">Buyer</th>
                               <th class="label-1"  style="width:20px">Add</th>
                            </tr>
                        </thead>  
                            <s:set id="ctn" name="ctn" value="0"/>
                            <s:iterator  value="%{DETAILLIST}" status="savest">
                                  <tr style="background-color: #FFFFFF">
                                <td  class="label-1">	
                                    <input type="button" class="texts"  tabindex="-1" style="font-size: 10px;margin: 0px;padding: 0px" name="btn" onclick="deletetablerow('<s:property value="%{#ctn}"/>')" value="X">
                                 </td>
                                <td  class="label-1" align="right">
                                    <s:textfield name="PKGS" 
                                           id="PKGS%{#ctn}" 
                                           cssStyle="width:100%" 
                                           onblur="validatenumber(this) "
                                           maxlength="6"
                                           value="%{PKGS}"
                                           cssClass="texts" 
                                           theme="simple"  />
                                </td>
                                
                                <td  class="label-1">
                                   
                                	 <s:hidden name="INV_NO" 
                                           id="INV_NO%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                            value="%{INV_NO}"
                                            cssClass="textreadonly"  
                                           theme="simple" />
                                            
                                    <s:textfield name="EXCS_INV_NO" 
                                           id="EXCS_INV_NO%{#ctn}" 
                                           cssStyle="width:100%" 
                                            value="%{EXCS_INV_NO}"
                                            cssClass="texts"    
                                            theme="simple" onblur="if(this.value!='') xmlhttpreqinv(this,'EXCS_INV_NO%{#ctn}','%{#ctn}')"  />
                                </td> 
                                <td >
                                    <a href="viewInvbillofsalesAction.action?PARAA=<s:property value="%{#ctn}"/>" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a> 
                                </td>
                                <td>
                                    <s:textfield name="INV_DESC" 
                                           id="INV_DESC%{#ctn}" 
                                           cssStyle="width:200px" 
                                           readonly="true"
                                           maxlength="250"
                                           value="%{INV_DESC}"
                                           tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                 <td  align="right"> 
                                    <s:textfield name="QNTY" 
                                           id="QNTY%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           maxlength="10"
                                           value="%{QNTY}"
                                           onblur="validatenumber1(this,%{#ctn});"
                                           cssClass="texts"  tabindex="36"
                                           theme="simple"/>
                                </td>
                                <td align="right">
                                    <s:textfield name="AVG_RATE" 
                                           id="AVG_RATE%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{AVG_RATE}"
                                           tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                <td >
                                    <s:textfield name="CRNCY" 
                                           id="CRNCY%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                           maxlength="3"
                                           value="%{CRNCY}"
                                            tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                <td  align="right">
                                    <s:textfield name="FOB" 
                                           id="FOB%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="10"
                                           tabindex="-1" 
                                           cssClass="textreadonly" 
                                              theme="simple"/>
                                </td>
                                 <td  align="right">
                                   
                                     <s:hidden name="INR_CONV" 
                                           id="INR_CONV%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                            value="%{INR_CONV}"
                                            tabindex="-1" 
                                           cssClass="textreadonly"  
                                           theme="simple"/>
                                     
                                     <s:textfield name="INR" 
                                           id="INR%{#ctn}" 
                                           cssStyle="width:100%;text-align=right" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{INR}"
                                            tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                 <td >
                                    <s:textfield name="CFT_PLAN" 
                                           id="CFT_PLAN%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           onblur="validatenumber(this)"
                                           maxlength="12"
                                           value="%{CFT_PLAN}"
                                           cssClass="texts" 
                                           theme="simple" tabindex="37"/>
                                </td>
                                 <td >
                                    <s:textfield name="CFT_ACTUAL" 
                                           id="CFT_ACTUAL%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           onblur="validatenumber(this)"
                                           maxlength="12"
                                           value="%{CFT_ACTUAL}"
                                           cssClass="texts" 
                                           theme="simple" tabindex="38"/>
                                </td>
                                <td >
                                    <s:textfield name="CBM" 
                                           id="CBM%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="12"
                                           value="%{CBM}"
                                           tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple" />
                                </td>
                                <td >
                                    <s:textfield name="VOL" 
                                           id="VOL%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="12"
                                           value="%{VOL}"
                                            tabindex="-1" 
                                           cssClass="textreadonly"
                                           theme="simple"/>
                                </td>
                                 <td >
                                    <s:textfield name="GRWT" 
                                           id="GRWT%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                            onblur="validatenumber(this)"
                                           maxlength="12"
                                           value="%{GRWT}"
                                           cssClass="texts" 
                                           theme="simple" tabindex="40"/>
                                    
                                    <s:hidden name="YEAR" 
                                           id="YEAR%{#ctn}"
                                           value="%{YEAR}"
                                           cssClass="texts" 
                                           theme="simple"/>
                                    <s:hidden name="COMPANY" 
                                           id="COMPANY%{#ctn}" 
                                           value="%{COMPANY}"
                                           cssClass="texts" 
                                           theme="simple"/>
                                    <s:hidden name="UOM" 
                                           id="UOM%{#ctn}" 
                                           value="%{UOM}"
                                           cssClass="texts" 
                                           theme="simple"/>
                                </td>
                                <td  align="center">
                                   
                                     <s:if test="%{PRINT_DATE!=null}">
                                           <input type="checkbox" id="PRINT_DATE_DIS<s:property value="%{#ctn}"/>" onclick="printflag(this,<s:property value="%{#ctn}"/>)"   name="PRINT_DATE_DIS" checked="true" value="Y"/>
                                            <s:hidden name="PRINT_DATE_FLAG" id="PRINT_DATE_FLAG%{#ctn}" value="Y"/>
                                           <s:hidden name="PRINT_DATE" id="PRINT_DATE%{#ctn}" value="%{PRINT_DATE}"/>
                                    </s:if>
                                    <s:else>
                                        <input type="checkbox" id="PRINT_DATE_DIS<s:property value="%{#ctn}"/>" onclick="printflag(this,<s:property value="%{#ctn}"/>)" name="PRINT_DATE_DIS"  />
                                        <s:hidden name="PRINT_DATE" id="PRINT_DATE%{#ctn}" value=""/>
                                        <s:hidden name="PRINT_DATE_FLAG" id="PRINT_DATE_FLAG%{#ctn}" value="N"/>
                                    </s:else>
                                    
                                </td>
                                <td  align="center">
                                    <s:if test='%{DISPATCH_YN=="Y"}'>
                                        <input type="checkbox" id="DISPATCH_YN_DIS<s:property value="%{#ctn}"/>" onclick="disflag(this,<s:property value="%{#ctn}"/>)"  name="DISPATCH_YN_DIS" checked="true" value="Y"/>
                                        <s:hidden name="DISPATCH_YN" id="DISPATCH_YN%{#ctn}"  value="%{DISPATCH_YN}"/>
                                     </s:if>
                                    <s:else>
                                     <input type="checkbox" name="DISPATCH_YN_DIS" onclick="disflag(this,<s:property value="%{#ctn}"/>)"  value="Y"/>
                                     <s:hidden name="DISPATCH_YN" id="DISPATCH_YN%{#ctn}"  value="N"/>
                                    </s:else>
                                </td>
                                 <td  align="right">
                                    <s:textfield name="FY_PKGS" 
                                           id="FY_PKGS%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           maxlength="10"
                                           value="%{FY_PKGS}"
                                           onblur="validatenumber1(this,%{#ctn});"
                                           cssClass="textreadonly"  tabindex="-1"
                                           theme="simple"  
                                           readonly="true"/>
                                </td> 
                                <td align="right">
                                    <s:textfield name="FY_QNTY" 
                                           id="FY_QNTY%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           maxlength="10"
                                           value="%{FY_QNTY}"
                                           onblur="validatenumber1(this,%{#ctn});"
                                           cssClass="textreadonly"  tabindex="-1"
                                           theme="simple"  
                                           readonly="true"/>
                                  
                                    <s:hidden name="FY_USER" 
                                           id="FY_USER%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                            value="%{FY_USER}"
                                            cssClass="textreadonly"  
                                           theme="simple" />
                                 
                                  <s:hidden name="FY_TDATE" 
                                           id="FY_TDATE%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                            value="%{FY_TDATE}"
                                            cssClass="textreadonly"  
                                           theme="simple" />
                                    
                                </td>
                                 <td  >
                                    <s:textfield name="i_cha" 
                                           id="i_cha%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value="%{i_cha}"
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td  >
                                    <s:textfield name="i_port" 
                                           id="i_port%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value="%{i_port}"
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td >
                                    <s:textfield name="i_agent" 
                                           id="i_agent%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value="%{i_agent}"
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td  >
                                    <s:textfield name="i_buyer" 
                                           id="i_buyer%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value="%{i_buyer}"
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td >
                                     <s:textfield name="i_address" 
                                           id="i_address%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value="%{i_address}"
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                            
                            
                            </tr>
                             <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                            </s:iterator>
                         
                            <s:iterator begin="%{#ctn}" end="40" status="st">
                                  <tr style="background-color: #FFFFFF">
                                <td  class="label-1">	
                                    <input type="button" class="texts"  tabindex="-1" style="font-size: 10px;margin: 0px;padding: 0px" name="btn" onclick="deletetablerow('<s:property value="%{#ctn}"/>')" value="X">
                                </td>
                                <td class="label-1" align="right">
                                    <s:textfield name="PKGS" 
                                           id="PKGS%{#ctn}" 
                                           cssStyle="width:100%" 
                                          onblur="validatenumber(this);"
                                           maxlength="6"
                                           value="" 
                                           cssClass="texts" 
                                           theme="simple"/>
                                	
                                </td>
                                <td  class="label-1">
                                    <s:hidden name="INV_NO" 
                                           id="INV_NO%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                           value=""
                                            tabindex="-1" 
                                           cssClass="textreadonly"  
                                           theme="simple"/>
                                           
                                         <s:textfield name="EXCS_INV_NO" 
                                           id="EXCS_INV_NO%{#ctn}" 
                                           cssStyle="width:100%" 
                                            value=""
                                            cssClass="texts"  
                                           
                                           theme="simple" onblur="if(this.value!='') xmlhttpreqinv(this,'INV_DESC%{#ctn}','%{#ctn}')"  />
                                </td>
                                <td >
                                    <a href="viewInvbillofsalesAction.action?PARAA=<s:property value="%{#ctn}"/>" target="handlefrm" onclick="openpop('root')">
                                       <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a> 
                                </td>
                                <td >
                                    <s:textfield name="INV_DESC" 
                                           id="INV_DESC%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true" 
                                           maxlength="250"
                                           value=""
                                           tabindex="-1" 
                                           cssClass="textreadonly"  
                                           theme="simple"/>
                                </td>  
                                 <td  align="right">
                                    <s:textfield name="QNTY" 
                                           id="QNTY%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           maxlength="10"
                                           value=""
                                           onblur="validatenumber1(this,%{#ctn});"
                                           cssClass="texts" 
                                           theme="simple"/>
                                </td>
                                <td  align="right">
                                    <s:textfield name="AVG_RATE" 
                                           id="AVG_RATE%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="10"
                                           value=""
                                           tabindex="-1" 
                                           cssClass="textreadonly"  
                                           theme="simple"/>
                                </td>
                                <td >
                                    <s:textfield name="CRNCY" 
                                           id="CRNCY%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                           maxlength="3"
                                           value=""
                                           tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                <td  align="right">
                                    <s:textfield name="FOB" 
                                           id="FOB%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="10"
                                           value=""
                                            tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                 <td align="right">
                                    <s:hidden name="INR_CONV" 
                                           id="INR_CONV%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                           value=""
                                            tabindex="-1" 
                                           cssClass="textreadonly"  
                                           theme="simple"/>
                                     
                                     <s:textfield name="INR" 
                                           id="INR%{#ctn}" 
                                           cssStyle="width:100%;text-align=right" 
                                           readonly="true"
                                           maxlength="10"
                                           value=""
                                            tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                 <td >
                                    <s:textfield name="CFT_PLAN" 
                                           id="CFT_PLAN%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           onblur="validatenumber(this)"
                                           maxlength="12"
                                           value=""
                                           cssClass="texts" 
                                           theme="simple"/>
                                </td>
                                 <td >
                                    <s:textfield name="CFT_ACTUAL" 
                                           id="CFT_ACTUAL%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           onblur="validatenumber(this)"
                                           maxlength="12"
                                           value=""
                                           cssClass="texts" 
                                           theme="simple"/>
                                </td>
                                 <td >
                                    <s:textfield name="CBM" 
                                           id="CBM%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                          readonly="true"
                                           maxlength="12"
                                           value=""
                                           tabindex="-1" 
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                                </td>
                                <td >
                                    <s:textfield name="VOL" 
                                           id="VOL%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="12"
                                           value=""
                                            tabindex="-1" 
                                           cssClass="textreadonly"
                                           theme="simple"/>
                                </td>
                                 <td >
                                    <s:textfield name="GRWT" 
                                           id="GRWT%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                            onblur="validatenumber(this)"
                                           maxlength="12"
                                           value=""
                                           cssClass="texts" 
                                           theme="simple"/>
                                    
                                    <s:hidden name="YEAR" 
                                           id="YEAR%{#ctn}"
                                           value=""
                                           cssClass="texts" 
                                           theme="simple"/>
                                    <s:hidden name="COMPANY" 
                                           id="COMPANY%{#ctn}" 
                                           value=""
                                           cssClass="texts" 
                                           theme="simple"/>
                                    <s:hidden name="UOM" 
                                           id="UOM%{#ctn}" 
                                           value=""
                                           cssClass="texts" 
                                           theme="simple"/>
                                </td> 
                                <td  align="center">
                                    <input type="checkbox"   id="PRINT_DATE_DIS<s:property value="%{#ctn}"/>" onclick="printflag(this,<s:property value="%{#ctn}"/>)" name="PRINT_DATE_DIS" value="Y" checked="true" />
                                          <s:hidden name="PRINT_DATE" id="PRINT_DATE%{#ctn}" value=""/>
                                          <s:hidden name="PRINT_DATE_FLAG" id="PRINT_DATE_FLAG%{#ctn}" value="N"/>
                                </td> 
                                <td   align="center">
                                    <input type="checkbox" name="DISPATCH_YN_DIS%{#ctn}" onclick="disflag(this,<s:property value="%{#ctn}"/>)" value=""/>
                                    <s:hidden name="DISPATCH_YN" id="DISPATCH_YN%{#ctn}"  value="N"/>
                                </td>
                                <td  > 
                                    <s:textfield name="FY_PKGS" 
                                           id="FY_PKGS%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value=""
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td  >
                                    <s:textfield name="FY_QNTY" 
                                           id="FY_QNTY%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value=""
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                                        <s:hidden name="FY_USER" 
                                           id="FY_USER%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                            value="%{FY_USER}"
                                            cssClass="textreadonly"  
                                           theme="simple" />
                                 
                                  <s:hidden name="FY_TDATE" 
                                           id="FY_TDATE%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                            value="%{FY_TDATE}"
                                            cssClass="textreadonly"  
                                           theme="simple" />
                                    
                                </td> 
                                
                                <td >
                                    <s:textfield name="i_cha" 
                                           id="i_cha%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value=""
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td  >
                                    <s:textfield name="i_port" 
                                           id="i_port%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value=""
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td  >
                                    <s:textfield name="i_agent" 
                                           id="i_agent%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value=""
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td  >
                                    <s:textfield name="i_buyer" 
                                           id="i_buyer%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value=""
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                <td  >
                                     <s:textfield name="i_address" 
                                           id="i_address%{#ctn}" 
                                           cssStyle="width:100%;" 
                                           value=""
                                           readonly="true"
                                           cssClass="textreadonly" tabindex="-1"
                                           theme="simple"/>
                                </td>
                            
                            </tr> 
                              <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                            </s:iterator>
                        </table>
                        </div> 
                         <table cellpadding="0" cellspacing="1">
                          
                            <tr  style="background-color: #cccccc;text-align: left" >
                               <th class="label-1"   style="width:12px">
                                </th>
                               <th class="label-1" style="height:25px;width: 40px" >
                                  <s:textfield name="CTN_TOTAL" 
                                           id="CTN_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{CTN_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>  
                                    
                               </th>
                               <th class="label-1"  style="width:55px"></th>
                               <th class="label-1"  style="width:15px"></th>
                               <s:if test="%{FYQNTY_TOTAL==null}">
                                   <th class="label-1" style="width:172px"></th>
                               </s:if>
                               <s:else>
                                    <th class="label-1" style="width:200px"></th>
                               </s:else>
                               <th class="label-1" style="width:58px">
                                   <s:textfield name="QNTY_TOTAL" 
                                           id="QNTY_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{QNTY_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/></th>
                               <th class="label-1" style="width:48px"></th>
                               <th class="label-1" style="width:48px"></th>
                               <th class="label-1"  style="width:52px">
                                    <s:textfield name="FOB_TOTAL" 
                                           id="FOB_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FOB_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th>
                               <th class="label-1"  style="width:60px">
                                   <s:textfield name="INR_CONV_TOTAL" 
                                           id="INR_CONV_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{INR_CONV_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th>
                                <th class="label-1"  style="width:55px">
                                   <s:textfield name="CFTPLAN_OTAL" 
                                           id="CFTPLAN_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{CFTPLAN_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th>
                                  <th class="label-1"  style="width:53px">
                                   <s:textfield name="CFTACT_TOTAL" 
                                           id="CFTACT_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{CFTACT_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th>
                               <th class="label-1"  style="width:52px">
                                   <s:textfield name="CBM_TOTAL" 
                                           id="CBM_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{CBM_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th>
                              <th class="label-1"  style="width:55px">
                                   <s:textfield name="VOL_TOTAL" 
                                           id="VOL_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{VOL_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th>
                               <th class="label-1"  style="width:60px">
                                   <s:textfield name="GRWT_TOTAL" 
                                           id="GRWT_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{GRWT_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th>
                                   
                              
                               <th class="label-1"  style="width:30px"></th>
                               <th class="label-1"  style="width:25px"></th>
                               <th  class="label-1"  style="width:55px">
                                   <s:textfield name="FYCTN_TOTAL" 
                                           id="FYCTN_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FYCTN_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/> 
                                   
                               </th>
                                <th  class="label-1"  style="width:55px">
                                     <s:textfield name="FYQNTY_TOTAL" 
                                           id="FYQNTY_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FYQNTY_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                                    
                                    
                                </th>
                                <th  class="label-1"  style="width:60px"></th>
                                <th  class="label-1"  style="width:60px"></th>
                                <th  class="label-1"  style="width:70px"></th>
                                <th  class="label-1"  style="width:70px"></th>
                                <th  class="label-1"  style="width:20px"></th>
                            </tr>
                           
                        </table>                       
                    </td></tr>
                
                <tr> 
                    <td height="40px"  align="center" style="color:red;font-weight:bold;">
                        <s:actionerror theme="simple" cssStyle="font-size:12px" />
                        <s:fielderror theme="simple" cssStyle="font-size:12px"  />
                        <s:actionmessage theme="simple" cssStyle="font-size:12px"  />
                    </td>
                </tr>
            </table>
               <div id="root" class="root" style="left:50px; top:200px;display:none;width:900px">
               <table cellpadding="0" cellspacing="0">
                <tr bgcolor="#6699FF">
                    <td width="100%">
                        <div id="handle" class="handle"  style="cursor: move">Details</div>
                    </td>
                    <td style="10px"><a href="#" onclick="closediv('root')" ><img border="0" width="18px" height="18px" src="image/chrome_close_button.png" /></a>
                    </td>
                </tr> 
                <tr>
                    <td colspan="2">
                        <iframe name="handlefrm" id="handlefrm" src="" scrolling="no" frameborder="0"  width="100%" height="300px"></iframe>
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
  