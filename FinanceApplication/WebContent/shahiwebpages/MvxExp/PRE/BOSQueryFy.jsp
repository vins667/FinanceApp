
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
    	        document.frm.action="updatebillofsalesAction.action";
    	        document.frm.submit();
       		document.getElementById('prepage').style.visibility = '';
       		}
            }
       
   }
  
   
  function validatein()
  {
          if(document.getElementById('LR_NO').value=="") 
          {
             alert("Please Enter LR No!!");
              document.getElementById('LR_NO').focus();
              return false;
           }
           if(document.getElementById('LR_DATE').value=="") 
          {
             alert("Please Enter LR Date!!");
              document.getElementById('LR_DATE').focus();
              return false;
           }
            if(document.getElementById('DRIVER_NAME').value=="") 
          {
              alert("Please Enter DRIVER Name!!");
              document.getElementById('DRIVER_NAME').focus();
              return false;
           }
            if(document.getElementById('DRIVER_MOBILE').value=="") 
          {
              alert("Please Enter DRIVER MOBILE No!!");
              document.getElementById('DRIVER_MOBILE').focus();
              return false;
           }
          if(document.getElementById('DL_NO').value=="") 
          {
             alert("Please Enter DL Number!!");
              document.getElementById('DL_NO').focus();
              return false;
              
          }
           if(document.getElementById('SEAL_NO').value=="") 
          {
              alert("Please Enter Seal No!!");
              document.getElementById('SEAL_NO').focus();
              return false;
              
          }
          if(document.getElementById('VEHICLE_NO').value=="") 
          {
              alert("Please Enter Vehicle No!!");
              document.getElementById('VEHICLE_NO').focus();
              return false;
           }
          if(document.getElementById('VCH_ARV_DATE').value=="") 
          {
             alert("Please Enter FY IN Date!!");
              document.getElementById('VCH_ARV_DATE').focus();
              return false;
           }
           if(document.getElementById('VCH_DEP_DATE').value=="") 
          {
             alert("Please Enter FY Out Date!!");
              document.getElementById('VCH_DEP_DATE').focus();
              return false;
           } 
           if(document.getElementById('FYCTN_TOTAL').value=="0") 
          {
             alert("Please Enter Cartons!!");
              document.getElementById('FY_PKGS').focus();
              return false;
           }
           if(document.getElementById('FYQNTY_TOTAL').value=="0") 
          {
             alert("Please Enter Qnty !!");
              document.getElementById('FYQNTY_TOTAL').focus();
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
  
   
   function ongosearch()
   {
        $("#saveid").attr("disabled","disabled").text("Please wait....");
          $("#backid").attr("disabled","disabled").text("Please wait....");
        document.frm.action="shipmentPlanAction.action";
       document.frm.submit();
       document.getElementById('prepage').style.visibility = '';
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
    var FYPLAN_QNTY=document.frm.FY_QNTY;
     var t=0; 
    if(document.frm.FY_QNTY)
        {
          if(FYPLAN_QNTY.length>0)  
              {
                  for(m=0; m<FYPLAN_QNTY.length; m++)
                      {
                          if(!isNaN(eval(FYPLAN_QNTY[m].value))){ 
                            t=t+eval(FYPLAN_QNTY[m].value) 
                             
                          }
                      }
              }else{
                   t=t+eval(FYPLAN_QNTY.value) 
                   
              }  
        }
     
          document.getElementById('FYQNTY_TOTAL').value=t.toFixed();
   
} 
function totalctnqty()
{
   
    var PLAN_QNTY=document.frm.PKGS;
    var FYPLAN_QNTY=document.frm.FY_PKGS;
    var t=0;  
    if(document.frm.FY_PKGS)
        {
          if(FYPLAN_QNTY.length>0)  
              {
                  for(m=0; m<FYPLAN_QNTY.length; m++)
                      {
                          if(!isNaN(eval(FYPLAN_QNTY[m].value))){
                            t=t+eval(FYPLAN_QNTY[m].value) 
                             
                          }
                      }
              }else{
                   t=t+eval(FYPLAN_QNTY.value) 
                    
              }
        }
          document.getElementById('FYCTN_TOTAL').value=t.toFixed(); 
   
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
           <s:hidden name="SEARCH_PLAN_DATE" id="SEARCH_PLAN_DATE"/>
           <s:hidden name="SEARCH_TOPLAN_DATE" id="SEARCH_TOPLAN_DATE"/>
           <s:hidden name="SEARCH_SUBMIT" id="SEARCH_SUBMIT"/>
          
            <table align="center" width="100%">
                <tr><td valign="top" style="border-width:1px;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr><td width="100%" colspan="2"   class="hd" style="text-align:center">Bill Of Sale (FY)</td></tr>
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
                                           cssStyle="width:360px;text-transform:uppercase" 
                                           cssClass="textreadonly"
                                           readonly="true" 
                                           theme="simple" tabindex="3"/>
                               </td>
                               
                              <td class="label-1">GPRS&nbsp;&nbsp;
                                  <input  disabled="true" type="checkbox" name="GPRS_YN" value="GPRS_YN"/>
                                  
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
                                           theme="simple" tabindex="-1"/>
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
                                           cssStyle="width:130px" 
                                           cssClass="textreadonly"
                                           readonly="true" 
                                           theme="simple"  tabindex="-1" />
                                  </td>
                               <td>
                              </td> 
                               <td class="label-1">Driver Mobile</td>
                               <td colspan="3"><s:textfield name="DRIVER_MOBILE" 
                                           id="DRIVER_MOBILE" 
                                           maxlength="25"
                                           cssStyle="width:360px" 
                                            cssClass="textreadonly"
                                           readonly="true" 
                                           theme="simple" tabindex="4"/>
                               </td>
                                <td class="label-1">Lease&nbsp;
                                     <input  disabled="true" type="checkbox" name="LEASE_YN" value="LEASE_YN"/>
                                    
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
                                         
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="1"/></td>
                              <td></td>
                               <td class="label-1">D/L No.</td>
                               <td colspan="3"> <s:textfield name="DL_NO" 
                                           id="DL_NO" 
                                           maxlength="25"
                                           cssStyle="width:360px;text-transform:uppercase" 
                                            cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="5"/>
                               </td>
                                <td class="label-1">Return
                                       <input  disabled="true" type="checkbox" name="RETURN_CARGO" value="RETURN_CARGO"/>
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
                                           readonly="true"
                                           value="%{LR_DATE}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/>
                              
                              </td> 
                              <td>  </td>
                               <td class="label-1">Vehicle Type</td>
                               <td colspan="3"><table cellpadding="1" cellspacing="0"><td><s:textfield name="VEHICLE_TYPE" 
                                           id="VEHICLE_TYPE" 
                                           cssStyle="width:50px" 
                                           value="%{VEHICLE_TYPE}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" 
                                            />
                                           </td>
                                  <td> 
                                </td>
                                  <td><s:textfield name="VEHICLE_TYPE_DESC" 
                                           id="VEHICLE_TYPE_DESC" 
                                           cssStyle="width:290px" 
                                           value="%{VEHICLE_TYPE_DESC}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"/></td>
                                   </table>
                               </td>
                               
                              <td class="label-1">Cancel
                                   <input  disabled="true" type="checkbox" name="CANCEL_DATE" value="%{CANCEL_DATE}"/>
                                         
                             </td>
                          </tr> 
                          <tr><td class="label-1">
                         Buyer 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="BUYER" 
                                           id="BUYER" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{BUYER}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple"  
                                           tabindex="-1"/>
                                              <s:hidden name="BUYER_ADDR" id="BUYER_ADDR" value="%{BUYER_ADDR}"/>
                              </td><td>
                                
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
                                            cssClass="textreadonly"
                                           readonly="true" 
                                           theme="simple" tabindex="6"/>  
                              </td>
                              
                          </tr>
                          
                           <tr><td class="label-1">
                         CHA 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="CHA" 
                                           id="CHA" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{CHA}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" 
                                            />
                                             
                                        <s:hidden name="CHA_ADDR" id="CHA_ADDR" value="%{CHA_ADDR}" />
                              </td><td>
                               
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
                                
                                   
                               </td> 
                              <td class="label-1">Fy In Date</td>
                              <td> 
                                       <s:textfield name="VCH_ARV_DATE" 
                                           id="VCH_ARV_DATE" 
                                           cssStyle="width:130px" 
                                           readonly="true"
                                           value="%{VCH_ARV_DATE}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/>
                                 
                              </td> 
                             
                                
                              <td class="label-1">Halting Hrs</td>
                              <td>
                                 <s:textfield name="HALT_HRS" 
                                           id="HALT_HRS" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{HALT_HRS}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                          tabindex="-1"
                                           theme="simple"/>  
                              </td>
                          </tr>
                           <tr><td class="label-1">
                         From Unit 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="UNIT" 
                                           id="UNIT" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{UNIT}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" 
                                           />
                              </td><td>
                               
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="UNIT_DESC" 
                                           id="UNIT_DESC" 
                                           cssStyle="width:235px" 
                                           cssClass="textreadonly" 
                                           value="%{UNIT_DESC}"
                                           theme="simple"/>
                              </td>
                              
                               <td></td>
                              <td class="label-1">Fy Out Date</td>
                               <td>  
                                     <s:textfield name="VCH_DEP_DATE" 
                                           id="VCH_DEP_DATE" 
                                           cssStyle="width:130px" 
                                           readonly="true"
                                           value="%{VCH_DEP_DATE}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/>
                                 
                                   
                                                   </td> 
                      
                              <td class="label-1">Seal No</td>
                              <td>
                                 <s:textfield name="SEAL_NO" 
                                           id="SEAL_NO" 
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           maxlength="20"
                                           value="%{SEAL_NO}"
                                            cssClass="textreadonly"
                                           readonly="true" 
                                           tabindex="9"
                                           theme="simple"/>  
                              </td>  
                          </tr>
                           <tr><td class="label-1">
                         To Unit 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td>
                                              <s:textfield name="UNIT_TO" 
                                           id="UNIT_TO" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{UNIT_TO}"
                                          cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" 
                                           />
                              </td><td>
                                
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="UNIT_TO_DESC" 
                                           id="UNIT_TO_DESC" 
                                           cssStyle="width:235px" 
                                           readonly="true"
                                           value="%{UNIT_TO_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>
                              </td>
                               <td></td>
                              <td class="label-1">Reporting Date</td>
                              
                              <td>  <s:textfield name="VCH_REP_DATE" cssClass="textreadonly"  readonly="true"   value="%{VCH_REP_DATE}"  theme="simple" maxlength="25" size="25" style="width:130px" tabindex="-1"/>
                               
                              </td>
                              <td class="label-1">Gross Wt</td>
                              <td>
                                 <s:textfield name="GR_WT" 
                                           id="GR_WT" 
                                           cssStyle="width:130px" 
                                           maxlength="12"
                                           readonly="true"
                                           value="%{GR_WT}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>  
                              </td><td class="label-1">Container No</td><td> <s:textfield name="CONTAINER_NO" 
                                           id="CONTAINER_NO" 
                                           cssStyle="width:100px" 
                                           maxlength="50"
                                           value="%{CONTAINER_NO}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"/></td>
                          </tr>
                          
                           <tr><td class="label-1">
                         Transporter 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td>
                                              <s:textfield name="TRANSPORTER" 
                                           id="TRANSPORTER" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{TRANSPORTER}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"  
                                            />
                              </td><td>
                                  
                               
                              </td>
                                      </tr></table>
                              </td>
                              <td colspan="2">
                                  <s:textfield name="DISPATCH_VIA" 
                                           id="DISPATCH_VIA" 
                                           cssStyle="width:235px" 
                                           readonly="true"
                                           value="%{DISPATCH_VIA}"
                                           cssClass="textreadonly" 
                                          
                                           theme="simple"/>
                              </td>
                              <td colspan="3"> </td>
                               <td class="label-1">Damage</td>
                              <td>
                                 <s:textfield name="DAMAGE" 
                                           id="DAMAGE" 
                                           cssStyle="width:130px" 
                                           maxlength="40"
                                           value="%{DAMAGE}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"/>  
                              </td>
                              <td class="label-1">Type</td><td> 
                                  <s:textfield name="CONTAINER_TYPE" 
                                           id="DAMAGE" 
                                           cssStyle="width:130px" 
                                           maxlength="40"
                                           value="%{CONTAINER_TYPE}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"/> 
                                  
                                            
                                 </td>
                          </tr>
                           <tr><td class="label-1">
                         From Port 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="PORT" 
                                           id="PORT" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{PORT}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" 
                                            />
                              </td><td>
                               
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
                              <td class="label-1">Plan CFT</td>
                              <td>
                                <s:textfield name="PLAN_CFT" 
                                           id="PLAN_CFT" 
                                           cssStyle="width:130px" 
                                           maxlength="12"
                                           value="%{PLAN_CFT}"
                                            readonly="true"
                                           cssClass="textreadonly" 
                                          
                                           theme="simple"/>  
                              </td>
                              <td class="label-1">Truck Size</td>
                              <td> 
                                 <s:textfield name="PLAN_SIZE" 
                                           id="PLAN_SIZE" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                            readonly="true"
                                           value="%{PLAN_SIZE}"
                                           cssClass="textreadonly" 
                                           theme="simple"/>  
                              </td>
                          </tr>
                           <tr><td class="label-1">
                         To Destn 
                              </td><td><table cellpadding="0" cellspacing="0"><tr><td><s:textfield name="DESTINATION" 
                                           id="DESTINATION" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{DESTINATION}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" 
                                            />
                              </td><td>
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
                              <td class="label-1">Actual CFT</td>
                              <td>
                                <s:textfield name="ACTUAL_CFT" 
                                           id="ACTUAL_CFT" 
                                           cssStyle="width:130px" 
                                           maxlength="12"
                                           value="%{ACTUAL_CFT}"
                                         
                                          cssClass="textreadonly" 
                                         readonly="true"
                                           theme="simple"/>  
                              </td>
                              <td class="label-1">Truck Size</td>
                              <td>
                                 <s:textfield name="ACTUAL_SIZE" 
                                           id="ACTUAL_SIZE" 
                                           cssStyle="width:130px" 
                                           maxlength="10"
                                           value="%{ACTUAL_SIZE}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"/>  
                              </td>
                               <td class="label-1">Factory Halt</td>
                              <td>
                                  
                                  <input type="checkbox" disabled="true" name="FY_HALT" value="Y"/>
                                  
                                
                              </td>
                          </tr>
                           <tr><td class="label-1">
                        Remarks 
                               </td><td colspan="3">
                                  <s:textfield name="REMARKS" 
                                           id="REMARKS" 
                                           cssStyle="width:380px" 
                                           maxlength="100"
                                           value="%{REMARKS}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"/>
                                  
                              </td>
                               <td></td>
                              <td class="label-1">CFS NAME</td>
                              <td>
                                  <table cellpadding="0" cellspacing="0"><tr><td>
                                <s:textfield name="CFS_CODE" 
                                           id="CFS_CODE" 
                                           cssStyle="width:130px" 
                                           maxlength="20"
                                           value="%{CFS_CODE}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" 
                                            /> 
                                </td><td>
                               
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
                                  
                                  <input type="checkbox" disabled="true" name="FY_CANCEL" value="Y"/>
                                  
                              </td>
                          </tr>
                          <tr>
                              <td class="label-1">Total Plan Ctns</td>
                                         <td> <s:textfield name="CTN_TOTAL" 
                                           id="CTN_TOTAL" 
                                           cssStyle="width:100px    ;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{CTN_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>  </td>
                             <td class="label-1">Total Challan Ctns</td>                                         
                                 <s:if test="%{CTN_TOTAL.equals(FYCTN_TOTAL)}">
                                     
                                          <td> <s:textfield name="FYCTN_TOTAL" 
                                           id="FYCTN_TOTAL" 
                                           cssStyle="width:100px;text-align:right;font-weight:bold;" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FYCTN_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>  </td>   
                                         
                                         
                                </s:if>
                                <s:else>
                                   
                                          <td> <s:textfield name="CHALLAN_CTN" 
                                           id="CHALLAN_CTN" 
                                           cssStyle="width:100px;text-align:right;font-weight:bold;color:red;background-color:yellow;" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{CHALLAN_CTN}"
                                           cssClass="texts" 
                                           theme="simple"/>  </td>
                                </s:else>
                               
                              <td></td>
                              <td class="label-1">Cutoff Date</td>
                              <td>
                                           <s:textfield name="CUTOFF_DATE" 
                                           id="CUTOFF_DATE" 
                                           cssStyle="width:130px" 
                                           maxlength="20"
                                           value="%{CUTOFF_DATE}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1"/>  
                              </td>
                             
                          </tr>
                          <tr>
                              
                               <td class="label-1">Total Plan Qnty</td>
                                <td> <s:textfield name="QNTY_TOTAL" 
                                           id="QNTY_TOTAL" 
                                           cssStyle="width:100px    ;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{QNTY_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>  </td>
                                <td class="label-1">Total Challan Qnty</td>
                               <s:if test="%{QNTY_TOTAL.equals(FYQNTY_TOTAL)}">
                                   <td> <s:textfield name="FRQNTY_TOTAL" 
                                           id="FYQNTY_TOTAL" 
                                           cssStyle="width:100px    ;text-align:right;font-weight:bold;" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FYQNTY_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>  
                                   </td>
                               </s:if>
                                   <s:else>
                                       <td> <s:textfield name="FYQNTY_TOTAL" 
                                           id="FYQNTY_TOTAL" 
                                           cssStyle="width:100px    ;text-align:right;font-weight:bold;color:red;background-color:yellow;" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FYQNTY_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>  </td>
                                       
                                       
                                   </s:else>   
                               
                                          
                              <td></td>
                          </tr>
                          </table>
              </td>
               
               </tr>
                <tr><td align="center"  colspan="2">
                        <table cellpadding="0" cellspacing="0" width="100%">
                            <tr><td align="center">
                             <button id="backheadId" class="sexybutton" onclick="javascript:window.location.href('BOSQUERY.action');"><span><span><span class="undo" id="backId">Back</span></span></span></button> &nbsp;
              
                                                    						
			 			 						
                        
                                </td></tr></table>
                                </td></tr>
              </table>
                   </td></tr>                
                                
                <tr><td style="border-width:1px;border-color:black;border-style:solid;" >
                         <div style="height: 180px;width:1292px; overflow-y:scroll">
                        <table cellpadding="0" cellspacing="1">
                            <thead> 
                            <tr  style="background-color: #cccccc;text-align: left" >
                         
                               <th class="label-1"  style="width:15px">Sl#</th>
                               <th class="label-1"  style="width:80px">Invoice#</th>
                               <th class="label-1" style="width:400px">Inv Description</th>
                               <th class="label-1" style="height:40px;width: 40px" align="right" >Plan Ctns.</th>
                               <th class="label-1" style="width:60px" align="right">Plan Qnty</th>
                               <th class="label-1" style="width:60px" align="right">Fy Qnty</th>
                               <th class="label-1" style="width:60px"  align="right">Avg Rate</th>
                               <th class="label-1" style="width:50px">Crncy</th>
                               <th class="label-1"  style="width:80px"  align="right">FOB</th>
                               <th class="label-1"  style="width:80px"  align="right">INR</th>
                                <th class="label-1" style="width:70px">Fy Date</th>
                               <th class="label-1" style="width:60px"   align="right">CFT Plan</th>
                               <th class="label-1" style="width:60px"   align="right">Actual</th>
                               <th class="label-1" style="width:60px"   align="right">CBM</th>
                               <th class="label-1" style="width:60px"   align="right">Volume</th>
                               <th class="label-1" style="width:60px"   align="right">GR.WT.</th>
                              
                          </tr>
                            </thead>  
                   
                       
                       
                            <s:set id="ctn" name="ctn" value="0"/>
                            <s:iterator  value="%{DETAILLIST}" status="savest">
                                  <tr style="background-color: #FFFFFF">
                               <td style="width:15px" class="label-1"><s:property value="%{#savest.index+1}"/></td>
                                <td style="width:80px" class="label-1">
                                   
                                	 <s:hidden name="INV_NO" 
                                           id="INV_NO%{#ctn}" 
                                           cssStyle="width:100%" 
                                           readonly="true"
                                            value="%{INV_NO}"
                                           cssClass="textreadonly"  
                                           theme="simple" 
                                            />
                                           
                                    <s:textfield name="EXCS_INV_NO" 
                                           id="EXCS_INV_NO%{#ctn}" 
                                            cssStyle="width:100%" 
                                            value="%{EXCS_INV_NO}"
                                            cssClass="textreadonly"   
                                            readonly="true"
                                            theme="simple" tabindex="-1" />
                                </td> 
                                 
                                <td style="width:400px">
                                    <s:textfield name="INV_DESC" 
                                           id="INV_DESC%{#ctn}" 
                                           cssStyle="width:400px" 
                                           readonly="true"
                                           maxlength="250"
                                           value="%{INV_DESC}"
                                           cssClass="textreadonly" 
                                           theme="simple" tabindex="-1"/>
                                </td>
                                  <td style="width:40px" class="label-1" align="right">
                                    <s:textfield name="PKGS" 
                                           id="PKGS%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           onblur="validatenumber(this) "
                                           maxlength="6"
                                           value="%{PKGS}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="-1" />
                                </td>
                                
                                 <td style="width:60px"  align="right">
                                    <s:textfield name="QNTY" 
                                           id="QNTY%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           maxlength="10"
                                           value="%{QNTY}"
                                           onblur="validatenumber1(this,%{#ctn});"
                                           cssClass="textreadonly"  
                                           readonly="true" tabindex="-1"
                                           theme="simple"/>
                                </td>
                                  
                                  
                                 <td style="width:60px"  align="right">
                                    <s:textfield name="FY_QNTY" 
                                           id="FY_QNTY%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           maxlength="10"
                                           value="%{FY_QNTY}"
                                           onblur="validatenumber1(this,%{#ctn});"
                                           cssClass="texts"  
                                            tabindex="2"
                                           theme="simple"/>
                                </td>
                                <td style="width:60px"  align="right">
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
                                <td style="width:50px">
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
                                <td style="width:80px"  align="right">
                                    <s:textfield name="FOB" 
                                           id="FOB%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           readonly="true"
                                           maxlength="10"
                                           tabindex="-1" 
                                           cssClass="textreadonly" 
                                              theme="simple"/>
                                </td>
                                 <td style="width:80px"  align="right">
                                   
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
                                 <td style="width:70px">
                                    <s:textfield name="FY_TDATE" 
                                           id="FY_TDATE%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                            maxlength="12"
                                           value="%{FY_TDATE}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="38"/>
                                </td>
                                   <td style="width:60px">
                                    <s:textfield name="CFT_PLAN" 
                                           id="CFT_PLAN%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           onblur="validatenumber(this)"
                                           maxlength="12"
                                           value="%{CFT_PLAN}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="37"/>
                                </td> 
                                
                                 <td style="width:60px">
                                    <s:textfield name="CFT_ACTUAL" 
                                           id="CFT_ACTUAL%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                           onblur="validatenumber(this)"
                                           maxlength="12"
                                           value="%{CFT_ACTUAL}"
                                           cssClass="textreadonly" 
                                           readonly="true"
                                           theme="simple" tabindex="38"/>
                                </td>
                                <td style="width:60px">
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
                                <td style="width:60px">
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
                                 <td style="width:60px">
                                    <s:textfield name="GRWT" 
                                           id="GRWT%{#ctn}" 
                                           cssStyle="width:100%;text-align:right" 
                                            onblur="validatenumber(this)"
                                           maxlength="12"
                                           value="%{GRWT}"
                                           cssClass="textreadonly" 
                                           readonly="true"
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
                                 
                              
                             
                            </tr>
                             <s:set id="ctn" name="ctn" value="%{#ctn+1}"/>
                            </s:iterator>
                            
       
                        </table>
                        </div>
                         <table cellpadding="0" cellspacing="1">
                          
                            <tr  style="background-color: #cccccc;text-align: left" >
                                  <th class="label-1"   style="width:15px">
                                 <th class="label-1"   style="width:80px">
                                </th>
                                 
                                 <th class="label-1" style="width:400px"></th>
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
                             
                               <th class="label-1" style="width:60px">
                                   <s:textfield name="QNTY_TOTAL" 
                                           id="QNTY_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{QNTY_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/></th>
                                
                              
                               <th class="label-1" style="width:60px">
                                   <s:textfield name="FYQNTY_TOTAL" 
                                           id="FYQNTY_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FYQNTY_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/></th>
                               <th class="label-1" style="width:60px"></th>
                               <th class="label-1" style="width:50px"></th>
                               <th class="label-1"  style="width:80px">
                                    <s:textfield name="FOB_TOTAL" 
                                           id="FOB_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{FOB_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th> 
                               <th class="label-1"  style="width:80px">
                                   <s:textfield name="INR_CONV_TOTAL" 
                                           id="INR_CONV_TOTAL" 
                                           cssStyle="width:100%;text-align:right;font-weight:bold" 
                                           readonly="true"
                                           maxlength="10"
                                           value="%{INR_CONV_TOTAL}"
                                           cssClass="texts" 
                                           theme="simple"/>
                               </th> 
                                <th class="label-1" style="width:70px"></th>
                                <th class="label-1" style="width:60px"></th>
                               <th class="label-1" style="width:60px"></th>
                                <th class="label-1" style="width:60px"></th>
                               <th class="label-1" style="width:60px"></th>
                                
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
 