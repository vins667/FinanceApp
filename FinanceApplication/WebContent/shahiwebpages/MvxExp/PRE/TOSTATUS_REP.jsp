<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<script type="text/javascript" src="js/dom-drag.js"></script>

<link href="<s:url value="../css/main.css"/>" rel="stylesheet"   type="text/css"/>

<html>
    <head>
         <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd </title>
        <style>
             
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

    function validatenumber(a)
   {
      if (!a.value=="0" && !a.value.match(/^-{0,1}\d*\$/ ) )
      {
        alert('Invalid Input, Only Numbers Allowed ');
        a.value="";
        a.focus();
        return false;
      }
   }

    function onlyNumbers(a)
    {
       k=a.value;
       if (k!="" && !k.match(/^\d+$|^\d/) )
        {
          alert('Invalid Input, Only Numbers Allowed');
          a.value="";
          a.focus();
          return false;
        }
        return true;
    }

    function GetReport()
    {
           document.getElementById('submitbtn').disabled=true;
          document.frm.action="TOSTATUSREP.action?printFlag=Yes";
          document.frm.submit();
    }
              function openpop(a)
            {
                
                document.getElementById(a).style.display='';
            }
             function closediv(a)
            {
                document.getElementById(a).style.display='none';
            }
 
   </script>
  
    </head> 
    <body class="body1" oncontextmenu="return false;">
        <form action=""   method="post" name="frm"  >
        <table height="86%"  border="0" bgcolor="#f2f2f2" cellpadding="0" align="center" width="100%" >
          <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
                <table border="0" bgcolor="#f2f2f2" cellpadding="0" cellspacing="0" width="100%" >
                <tr>
                    <td class="hd" style="text-align:center; font-size:medium;height:25px">
                       Document Pending with Agent - M4
                    </td>
                </tr>
                
            </table>
        
             
            <table width="100%" cellpadding="0" cellspacing="0"  >
                <tr>
                    <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="6" style="background-color: #6699CC;">
                           
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white;">Loct</td>
                                <td align="left" class="label-1" style="background-color: white;">
                                       <s:select  headerValue="" list="#{'%':'ALL', '100':'100', '200':'200'}"  cssStyle="font-size:10pt;width:95px;font-weight:bold" theme="simple" name="LOCT_CODE" value="%{loct}" /> 
                                </td>  
                               
                            </tr>
                      
                             <tr>
                                <td valign="top" class="label-1" style="background-color: white;">Inv Type</td>
                                <td align="left" class="label-1" style="background-color: white;">
                                 <s:select name="self_tp" label="Invoice Type" cssStyle="font-size:10pt;width=70pt;font-size:9pt;" theme="simple"  value="%{self_tp}"  list="#{'%':'ALL','N':'Normal','S':'Trade Sample','F':'Free Sample'}" /></td>
                              
                              </tr>
                              <tr>
                                <td valign="top" class="label-1" style="background-color: white;">Buyer</td>
                                <td align="left" class="label-1" style="background-color: white;">
                                         
                                           <s:textfield name="BUYER"  cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" theme="simple" value="%{BUYER}"/>
                                             <s:hidden name="BUYER_DESC" id="BUYER_DESC" value=""/>
                                            <s:hidden name="BUYER_ADDR" id="BUYER_ADDR" value=""/>
                                            <a href="getbuyeraddbillofsalesAction.action" target="handlefrm" onclick="openpop('root')">
                                            <img width="14px" border=0 height="14px" src="image/Search-icon-big.png"/></a>
                                         
                                  </td>
                                   
                            </tr> 
                            <tr> 
                                <td valign="top" class="label-1" style="background-color: white;">AC Holder </td>
                                <td align="left" class="label-1" style="background-color: white;">
                                          <s:select name="ac_holder"   cssStyle="text-transform:uppercase;width:150pt;font-size:9pt;" value="%{ac_holder}" theme="simple" list="%{acList}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                                 </td>
                            </tr>
                            <tr> 
                                <td valign="top" class="label-1" style="background-color: white;">PCH  </td>
                                <td align="left" class="label-1" style="background-color: white;">
                                          <s:select name="PCH"   cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" value="%{PCH}" theme="simple" list="%{pchList}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                                 </td>
                            </tr>
                             <tr>
                                <td valign="top" class="label-1" style="background-color: white;">ETD Delay >= </td>
                                <td align="left" class="label-1" style="background-color: white;">
                                    <s:textfield name="etd_days"  cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" theme="simple" value="%{etd_days}" onblur="onlyNumbers()"/>
                               </td> 
                                 
                            </tr>
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white;">Date From</td>
                                <td align="left" class="label-1" style="background-color: white;">
                                 <sx:datetimepicker  name="date_from" id="date_from" displayFormat="dd/MM/yyyy"  value="%{date_from}" cssStyle="width:70pt;font-size:9pt"/>
                                </td> 
                                 
                            </tr>
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white;">Date TO</td>
                                <td align="left" class="label-1" style="background-color: white;">
                                 <sx:datetimepicker  name="date_to" id="date_to" displayFormat="dd/MM/yyyy"  value="%{date_to}" cssStyle="width:70pt;font-size:9pt"/>
                                </td> 
                         
                            </tr>
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white;">Exp/Dom</td>
                                <td align="left" class="label-1" style="background-color: white;">
                                       <s:select  headerValue="" list="#{'%':'ALL', 'EXP':'EXPORT', 'DOM':'DOMESTIC'}"  cssStyle="font-size:10pt;width:95px;font-weight:bold" theme="simple" name="EXP_DOM"  /> 
                                      
                                </td>    
                            </tr> 
                            <tr>
                                <td valign="top" class="label-1" style="background-color: white;">Report Order</td>
                                <td align="left" class="label-1" style="background-color: white;">
                                 <s:select name="ordby" label="order" cssStyle="font-size:10pt;width=95pt;font-size:9pt;" theme="simple"  value="%{ordby}"  list="#{'MOS':'MOS','PCH':'PCH','BUY':'Buyer','ACH':'AC Holder','PORT':'Port','CUR':'Currency','CHA':'CHA','FWD':'Forwarder'}" /></td>
                           
                            </tr> 
                            <tr>  
                              <td style="background-color: white;" class="label-1">Date Based on</td>
                               <td align="left"  align="center" style="background-color: white;" class="label-2">
                                    <input type="radio" name="basedon" value="TO" CHECKED>&nbsp;TO
                                    <input type="radio" name="basedon" value="TTO">&nbsp;TTO
                                    <input type="radio" name="basedon" value="INV">&nbsp;INV
                                    <input type="radio" name="basedon" value="ETD">&nbsp;ETD 
                                </td>
                          
                            </tr>
                            <tr>  
                                <td style="background-color: white;" class="label-1">Output&nbsp;Format</td>
                                <td  align="left" style="background-color: white;"	class="label-2">
                                    <input type="radio" name="Routput" value="PDF" CHECKED>&nbsp;&nbsp;PDF
                                    <input type="radio" name="Routput" value="XLS">&nbsp;&nbsp;Excel
                                </td>
                                 <td></td>
                            </tr>
 
                            <tr>  
                                <td colspan="2"  align="center" style="background-color: white;">
                                    <input type="button" id="Reset" value="Reset" onclick="window.location.href='TOSTATUSREP.action'"  class="whitesubmitbutton">
                                    &nbsp;&nbsp;
                                    <input type="button" id="submitbtn" value="Run Report" class="whitesubmitbutton" onclick="GetReport();">
                                </td>
                               
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
         
          </td></tr>

          <tr style="height:5%" bgcolor="#D8D8D8" ><td style="border-width:1pt;border-color:black;border-style:solid;" >
              <table   align="center" width="100%" >
                  <tr><td align="left" > Date : <s:property value="%{currentdate}" /> </td>
 
                <td align="right" > User : <s:property value="%{#session.sessUserId}"/> </td></tr>
              </table>
          </td></tr> 
        </table>
            <input type="hidden" name="aausrid" value="<s:property value="%{#session.sessUserId}"/>" >
          <div id="root" class="root" style="left:50px; top:200px;display:none;width:900px;z-index: 10000">
               <table cellpadding="0" cellspacing="0">
                <tr bgcolor="#6699FF">
                    <td width="100%">
                        <div id="handle" class="handle"  style="cursor: move">Details</div>
                    </td>
                    <td style="10px"><a href="#" onclick="closediv('root')" ><img border="0" width="18px" height="18px" src="image/chrome_close_button.png"/></a>
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
 

