<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="../css/main.css"/>" rel="stylesheet"   type="text/css"/>
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
   

         function GetDetail()
        {
              document.frm.action="TOPLAN.action";
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
        { 
                 document.frm.action="TOPLANSUM.action?showFlag=Yes";
              document.frm.submit();
              document.getElementById("showeid").style.display = 'none';
              document.getElementById('prepage').style.visibility='';
     }
    
      
      
    
 </script>
</head> 

<body class="body1" oncontextmenu="return true;" >
        <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>

 <form  name="frm" id="frmid"  method="post" enctype="multipart/form-data"> 
     <table height="96%" bgcolor="#f2f2f2"  align="center" width="100%">
         <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
          <table border="0"  cellpadding="1" align="center" width="100%" >
              <tr><td valign="top"  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;">Dispatch Planned Summary
             </td></tr>
              
          </table> 

            <table width="90%"  align="center"  cellpadding="0" cellspacing="0">
                   <tr  valign="center"><td align="right" >
                      <td  align="left" class="label-1"  style="padding-right:5pt;" ><LABEL style="color:rgb(255,0,0); font-size:medium;">*</LABEL>AC Holder :
                     
                          <s:select name="sac" value="%{sac}" cssStyle="text-transform:uppercase;width:180pt" theme="simple" list="%{acList}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  />
                      </td>
                        <td  align="left" class="label-1"  style="padding-right:5pt;" >Port:
                            <s:select name="sport" cssStyle="text-transform:uppercase;width:90pt" theme="simple" list="%{portList}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                        </td>
                        <td  align="left" class="label-1"  style="padding-right:5pt;" >Buyer:
                              <s:select name="sbuyer"  cssStyle="text-transform:uppercase;width:90pt" theme="simple" list="%{buyerList}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                         </td>
                        <td align="right" >
                           <input type="button" name="btnGo" id="showeid" style="width:50pt" value="Show" onclick="GetSearch()"  class="submitbutton1">
                      </td>   
                     
                </tr>
             </table>
             <table  width="100%"  align="center"  cellpadding="0" cellspacing="0">
                <tr><td  valign="top" style="border-width:0pt;border-color:#2680b5;border-style:solid;" >
                 <div  style="position:relative;width:100%;overflow:auto ;height:400pt;">
                     <table align="center" width="100%" cellpadding="0" bgcolor="#d0d7e5" cellspacing="0" >
                     <thead >
                       <tr class="hd" style="position:relative; top: expression(this.offsetParent.scrollTop);height:20pt" >
                          <th class="label-1" style="width:4%" >Sl# </th> 
                           <th class="label-1" style="width:12%" >Ac Holder</th>
                          <th class="label-1" style="width:8%" >Fwd Date</th>
                          <th class="label-1" style="width:8%">Ex-Fy Date</th>
                          <th class="label-1" style="width:8%">Cuttoff</th>
                          <th class="label-1" style="width:8%">Port</th>
                          <th class="label-1"  style="width:8%">Buyer</th>
                          <th class="label-1"  style="width:8%">Destn Cntry</th>
                          <th class="label-1"  style="width:8%">Invoice No.</th>
                          <th class="label-1"  style="width:8%">Factory</th>
                          <th class="label-1" align="right" style="width:5%">InvQty</th>
                          <th class="label-1" align="right" style="width:5%">Ctns</th>
                          <th class="label-1" align="right" style="width:5%">CBM</th>
                          <th class="label-1" align="right" style="width:5%">CFT</th>
                       </tr> 
                      </thead>     
                         <s:if test="%{invList!=null  }">
                           <s:iterator value="invList" status="st1" >
                             
                               <tr style="height:15pt;cursor:hand" bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';"  >
                                         <td style="font-size:8pt"><s:property value="%{#st1.index+1}"/></td>
                                         <td style="font-size:8pt"><s:property value="AC_HOLDER"/></td>
                                         <td style="font-size:8pt"><s:property value="FWD_DATE"/></td>
                                         <td style="font-size:8pt"><s:property value="EX_FY_DATE"/></td>
                                         <td style="font-size:8pt"><s:property value="DEL_DATE"/></td>
                                         <td style="font-size:8pt"><s:property value="PORT"/></td>
                                         <td style="font-size:8pt"><s:property value="BUYER"/></td>
                                         <td style="font-size:8pt"><s:property value="CNTRY"/></td>
                                         <td style="font-size:8pt"><s:property value="INV_NO"/></td>
                                         <td style="font-size:8pt"><s:property value="FACTORY"/></td>
                                         <td align="right" style="font-size:8pt"><s:property value="INVQTY"/></td>
                                         <td align="right" style="font-size:8pt"><s:property value="CTNS"/></td>
                                         <td align="right" style="font-size:8pt"><s:property value="CBM"/></td>
                                         <td align="right" style="font-size:8pt"><s:property value="CFT"/></td>
                               </tr>
                            </s:iterator>
                                 
                         </s:if>
                         <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                         <td align="right" style="text-align:right;font-size:8pt;font-weight:bold;color:red;background-color:yellow"><s:property value="TQTY"/></td>
                         <td align="right" style="text-align:right;font-size:8pt;font-weight:bold;color:red;background-color:yellow"><s:property value="TBOX"/></td>
                         <td align="right" style="text-align:right;font-size:8pt;font-weight:bold;color:red;background-color:yellow"><s:property value="TCBM"/></td>
                         <td align="right" style="text-align:right;font-size:8pt;font-weight:bold;color:red;background-color:yellow"><s:property value="TCFT"/></td>
                        
                       </tbody>
                      
                     </table>
                 </div>
                <td>
             </tr>
             </table>
           </td></tr>
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

 