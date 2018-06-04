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
  
    function selectinv()
  {
   var a=document.frm.minv;

   if(a.length>0)
   {
   if(document.frm.selinv.checked==true)
   {  for (var i=0;i<a.length;i++)
      {a[i].checked=true;  }
    }else
    {
      for (var i=0;i<a.length;i++)
      {a[i].checked=false;  }
    }
   }
   else{
    if(document.frm.selinv.checked==true)
    {a.checked=true; }
    else
    {a.checked=false; }
   }
  }
   function validateinput(){
 
     if(document.frm.sac.value=="")
        {
           alert("Please Select AC Holder ")
           document.frm.sac.focus();
           return false;
        }
        
           return true;
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
        {if(validateinput()){
               
              
              document.frm.action="TOPLAN.action?showFlag=Yes";
              document.frm.submit();
              document.getElementById("showeid").style.display = 'none';
              document.getElementById('prepage').style.visibility='';
 

             //  document.getElementById("sbank2").disabled=true;
        }}
    function recsave()
    {
          if(confirm('You Want to Forward Records ? '))
        {if(validateinput()){
            //document.getElementById("saveid").disabled=true;
            document.frm.action="TOPLAN.action?updFlag=YES";
            document.frm.submit();
            document.getElementById("saveid").style.display = 'none';
            document.getElementById('prepage').style.visibility='';
        }
        }else{
            document.getElementById("saveid").disabled=false;
            return ;}
     }
     
     function GetNew()
    {
         
            document.frm.action="CallMstUpdateTOPLAN.action";
            document.frm.submit();
     }
     function EditRec(a,b,c,d)
     {
       document.frm.action="CallMstUpdateTOPLAN.action?UPDCODE="+a+"&sac="+b+"&sbuyer="+c+"&sport="+d ;
        document.frm.submit();  
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
            <img alt=""  src="../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>

 <form  name="frm" id="frmid"  method="post" enctype="multipart/form-data"> 
     <table height="96%" bgcolor="#f2f2f2"  align="center" width="100%">
         <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
          <table border="0"  cellpadding="1" align="center" width="100%" >
              <tr><td valign="top"  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;">Dispatch Planning
             </td></tr>
              
          </table> 
 
            <table width="100%"  align="center"  cellpadding="0" cellspacing="0">
                   <tr  valign="center"><td align="right" >
                      <td  align="left" class="label-1"  style="padding-right:5pt;" ><LABEL style="color:rgb(255,0,0); font-size:medium;">*</LABEL>AC Holder :
                     
                          <s:select name="sac" value="%{sac}" cssStyle="text-transform:uppercase;width:180pt" theme="simple" list="%{acList}" headerKey="%" headerValue="Select AC Holder" listKey="LIST_CODE" listValue="LIST_NAME" onchange="GetDetail()" />
                      </td>
                        <td  align="left" class="label-1"  style="padding-right:5pt;" >Port:
                            <s:select name="sport" cssStyle="text-transform:uppercase;width:90pt" theme="simple" list="%{portList}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                        </td>
                        <td  align="left" class="label-1"  style="padding-right:5pt;" >Buyer:
                              <s:select name="sbuyer"  cssStyle="text-transform:uppercase;width:90pt" theme="simple" list="%{buyerList}" headerKey="%" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                         </td>
                          <td align="center" class="label-1"> Inv No: <s:textfield name="VINV" cssStyle="text-transform:uppercase;width:60pt" theme="simple" maxLength="10"/></td>
                  
                      <td align="right" >
                           <input type="button" name="btnGo" id="showeid" style="width:50pt" value="Show" onclick="GetSearch()"  class="submitbutton1">
                           <input type="button" name="btn" id="showeid" style="width:50pt"  value="New" onclick="GetNew()"    class="submitbutton1">
                          <input type="button" name="btn" id="saveid"  style="width:50pt"  value="Forward" onclick="recsave()"  class="submitbutton1">
                      </td>   
                     
                </tr>
             </table>
             <table  width="100%"  align="center"  cellpadding="0" cellspacing="0">
                <tr><td  valign="top" style="border-width:0pt;border-color:#2680b5;border-style:solid;" >
                 <div  style="position:relative;width:100%;overflow:auto ;height:400pt;">
                     <table align="center" width="100%" cellpadding="0" bgcolor="#d0d7e5" cellspacing="0" >
                     <thead >
                       <tr class="hd" style="position:relative; top: expression(this.offsetParent.scrollTop);height:20pt" >
                          <th class="label-1" style="width:2%" >Sl# </th> 
                          <th class="label-1" align="center" style="width:8%" >Invoice No.<input title="Select All " type="CHECKBOX" name="selinv" value="Y" onclick="selectinv()"></th>
                          <th class="label-1" style="width:8%">Inv Date</th>
                          <th class="label-1" style="width:5%">Port</th>
                          <th class="label-1" style="width:5%">Buyer</th>
                          <th class="label-1"  style="width:15%">CHA</th>
                          <th class="label-1"  style="width:8%">Factory</th>
                          <th class="label-1"  style="width:8%">Cuttoff</th>
                          <th class="label-1"  style="width:8%">Ex-Fy Date</th>
                          <th class="label-1" align="right" style="width:5%">Ctns</th>
                          <th class="label-1" align="right" style="width:5%">CFT</th>
                          <th class="label-1" align="right" style="width:5%">InvQty</th>
                          <th class="label-1" align="center" style="width:5%"></th>
                          <th align="right" class="label-1" style="width:6%"><input title="Select All for Forward to Logistics" type="CHECKBOX" name="selall" value="Y" onclick="selectall()"></th>
 
                        </tr>
                      </thead>      
                         <s:if test="%{invList!=null  }">
                           <s:iterator value="invList" status="st1" >
                               <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                <td style="font-size:8pt"><s:textfield name="SR_NO" readonly="true" cssStyle="width:20pt;text-align:right" theme="simple"  value="%{#st1.index+1}"/></td>
                                <td style="font-size:8pt"><s:textfield name="EXCS_INV_NO" readonly="true" cssStyle="width:60pt;" theme="simple"  value="%{EXCS_INV_NO}"/>
                                    <s:if test='%{FWD_DATE==null}'> 
                                    <input type="checkbox"   name="minv"  theme="simple" value="<s:property value="%{EXCS_INV_NO}"/>"</td>
                                </s:if>
                                    <td style="font-size:8pt"><s:textfield name="INV_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{INV_DATE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="PORT" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{PORT}"/></td>
                                 <td style="font-size:8pt"><s:textfield name="BUYER" readonly="true" cssStyle="width:80pt;" theme="simple" value="%{BUYER}"/></td>
                                <td style="font-size:8pt"><s:textfield name="CHA" readonly="true" cssStyle="width:160pt;" theme="simple" value="%{CHA}"/></td>
                                 <td  style="font-size:8pt"><s:textfield name="FY_CODE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{FY_CODE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="DEL_DATE1" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{DEL_DATE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="EX_FY_DATE1" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{EX_FY_DATE}"/></td>
                                 <td style="font-size:8pt"><s:textfield name="BPO" readonly="true" cssStyle="width:60pt;text-align:right" theme="simple" value="%{BPO}"/></td>
                                <td style="font-size:8pt"><s:textfield name="STYLE" readonly="true" cssStyle="width:60pt;text-align:right" theme="simple" value="%{STYLE}"/></td>
                              
                                 <td  style="font-size:8pt"><s:textfield name="INVQTY" readonly="true" cssStyle="width:60pt;text-align:right" theme="simple" value="%{INVQTY}"/></td>
                                 
                                <td> <input  type="button" name="bntEdit" style="width:20pt" onclick="EditRec('<s:property value="EXCS_INV_NO"/>','<s:property value="sac"/>','<s:property value="sbuyer"/>','<s:property value="sport"/>')" value="Edit" class="submitbutton1"></td>
                                 
                               <s:if test='%{FWD_DATE==null}'>       
                                 <td  align="right"> <input type="checkbox"  title="Forward to Logistics" name="saverec"  theme="simple" value="<s:property value="%{EXCS_INV_NO}"/>"</td>
                               </s:if>    
                                <s:else>
                                     <td style="font-size:8pt"><s:textfield name="FWD_DATE1" readonly="true" cssStyle="width:50pt;" theme="simple" value="%{FWD_DATE}"/></td>
                                
                                </s:else>
                             
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

 