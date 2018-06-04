<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<html>
    <head>
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

  <script language="javascript">
 

  function selectall()
  {
   var a=document.frm.saverec;

   if(a.length>0)
   {
   if(document.frm.selall.checked==true)
   {  for (var i=0;i<a.length;i++)
      {a[i].checked=true;  }
    }else
    {
      for (var i=0;i<a.length;i++)
      {a[i].checked=false;  }
    }
   }
   else{
    if(document.frm.seleall.checked==true)
    {a.checked=true; }
    else
    {a.checked=false; }
   }
  }
  
  function validateinput(){
 
     if(document.frm.stype.value=="")
        {
           alert("Please Select Action Type ")
           document.frm.stype.focus();
           return false;
        }
        return true;
       }
  

         function GetDetail()
        {
              document.frm.action="INVDELAYREASON.action";
              document.frm.submit();
              document.getElementById('prepage').style.visibility='';
              
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
      function GetSearch()
        {if(validateinput()){ 
              document.frm.action="INVDELAYREASON.action?showFlag=Yes";
              document.frm.submit();
              document.getElementById("showeid").style.display = 'none';
              document.getElementById('prepage').style.visibility='';
       //  document.getElementById("sbank2").disabled=true;
        }}
    function recsave()
    {if(validateinput()){ 
          if(confirm('You Want to Forward Records ? ')){
         
            document.frm.action="INVDELAYREASON.action?updFlag=YES";
            document.frm.submit();
            document.getElementById("saveid").style.display = 'none';
            document.getElementById('prepage').style.visibility='';
        }}
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
 
<body class="body1" oncontextmenu="return true;" >
        <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>

 <form  name="frm" id="frmid"  method="post" enctype="multipart/form-data"> 
     <table height="96%" bgcolor="#f2f2f2"  align="center" width="100%">
         <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
          <table border="0"  cellpadding="1" align="center" width="100%" >
              <tr><td valign="top"  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;">Invoice Delay Reason
             </td></tr>
              
          </table> 

            <table width="75%"  align="center"  cellpadding="0" cellspacing="0">
                   <tr  valign="center"><td align="right" >
                      <td  align="left" class="label-1"  style="padding-right:5pt;" >AC Holder :
                     
                          <s:select name="sac" value="%{sac}" cssStyle="text-transform:uppercase;width:180pt" theme="simple" list="%{acList}" headerKey="%" headerValue="Select AC Holder" listKey="LIST_CODE" listValue="LIST_NAME"  />
                      </td>
                        <td  align="left" class="label-1"  style="padding-right:5pt;" >Action Type:
                            <s:select name="stype" cssStyle="text-transform:uppercase;width:250pt" theme="simple" list="%{TypeList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                        </td>
                         
                       
                      <td align="right" >
                            <button  id="searchheadId" class="sexybutton" onclick="GetSearch()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                              
                           <button  id="saveheadId"  class="sexybutton" onclick="recsave()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                        </td>   
                     
                </tr>
             </table>
             <table  width="75%"  align="center"  cellpadding="0" cellspacing="0">
                <tr><td  valign="top" style="border-width:0pt;border-color:#2680b5;border-style:solid;" >
                 <div  style="position:relative;width:100%;overflow:auto ;height:400pt;">
                     <table align="center" width="100%" cellpadding="0" bgcolor="#d0d7e5" cellspacing="0" >
                     <thead >
                       <tr class="hd" style="position:relative; top: expression(this.offsetParent.scrollTop);height:20pt" >
                          <th class="label-1" style="width:5%" >Sl# </th> 
                          <th class="label-1" align="center" style="width:10%" >Invoice No</th>
                          <th class="label-1" style="width:30%">Reason</th>
                          <th class="label-1"  style="width:8%">TTO Date</th>
                          <th class="label-1"  style="width:8%">TO Date</th>
                          <th class="label-1"  style="width:8%">ETD Date</th>
                          <th class="label-1" style="width:8%">Buyer</th>
                          <th class="label-1" style="width:10%">AC Holder</th>
                          
                      
                        </tr>
                      </thead>      
                         <s:if test="%{invList!=null  }">
                           <s:iterator value="invList" status="st1" >
                               <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                <td style="font-size:8pt"><s:textfield name="SR_NO" readonly="true" cssStyle="width:20pt;text-align:right" theme="simple"  value="%{#st1.index+1}"/></td>
                                <td style="font-size:8pt"><s:textfield name="EXCS_INV_NO" readonly="true" cssStyle="width:70pt;" theme="simple"  value="%{EXCS_INV_NO}"/></td>
                                <td style="font-size:8pt"><s:select name="SREASON" cssStyle="text-transform:uppercase;width:280pt" theme="simple" list="%{PreList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" /> </td>
                                <td  style="font-size:8pt"><s:textfield name="TTO_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{TTO_DATE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="TO_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{TO_DATE}"/></td>
                                 <td style="font-size:8pt"><s:textfield name="ETD_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{ETD_DATE}"/></td>
                                 <td style="font-size:8pt"><s:textfield name="BUYER" readonly="true" cssStyle="width:80pt;" theme="simple" value="%{BUYER}"/></td>
                                 <td style="font-size:8pt"><s:textfield name="AC_HOLDER" readonly="true" cssStyle="width:120pt;" theme="simple" value="%{AC_HOLDER}"/></td>
                                            <s:hidden name="YEAR" value="%{YEAR}"/>
                                            <s:hidden name="COMPANY" value="%{COMPANY}"/>
                                            <s:hidden name="INV_NO" value="%{INV_NO}"/>
                                 
                             
                              </tr> 
                            </s:iterator>
                         </s:if>
                      
                       </tbody>
                       </table>
                 </div>
                <td>
             </tr>
             </table>
           </td></tr>
                <td align="center" class="label-1">Delay : For P (P Date-Awb Date>4, For D (D Date-Awb Date>3</td>
              
             <tr style="height:5%" bgcolor="#f2f2f2" >
              <td style="border-width:1pt;border-color:black;border-style:solid;" >
                  <table   align="center" width="100%" >
                      <tr><td align="left" > Date : <s:property value="%{currentdate}" /> </td>
                          <td valign="bootom" align="center" >
                    <div style="height:3pt">
                        <s:actionerror />
                        <s:fielderror />
                        <s:actionmessage />
                    </div>
                    </td>
                    <td align="right" > User : <s:property value="%{#session.sessUserId}"/> </td></tr>
                   <input type="hidden" name="aausrid" value="<s:property value="%{aausrid}"/>" >
                    <input type="hidden" name="authuser" value="<s:property value="%{authuser}"/>" >
                  </table>
               </td>
             </tr> 
     </table>
 </form>
</body>
</html>

 