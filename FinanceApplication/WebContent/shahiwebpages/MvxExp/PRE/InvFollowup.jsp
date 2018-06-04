<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
<html>
    <head>
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        

  <script language="javascript">
 

    function selectinv()
  {
   var a=document.frm.UPDCODE;

   if(a.length>0)
   {
   if(document.frm.selinv.checked==true)
   {  for (var i=0;i<a.length;i++)
      {a[i].checked=true;  
      checkedsave(a[i],a[i].value);
     }
    }else
    {
      for (var i=0;i<a.length;i++)
      {a[i].checked=false;  
      checkedsave(a[i],a[i].value);
   }
    }
   
   }
   else{
    if(document.frm.selinv.checked==true)
    {a.checked=true; }
    else
    {a.checked=false; }
   }
   checkedsave(a,a.value);
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
              document.frm.action="INVFOLLOWUP.action?GetDtl=YES";
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
              document.frm.action="INVFOLLOWUP.action?showFlag=YES";
              document.frm.submit();
              document.getElementById("showeid").style.display = 'none';
              document.getElementById('prepage').style.visibility='';
      
        }}
    function recsave()
    {if(validateinput()){ 
          if(confirm('You Want to Save Records ? ')){
         
            document.frm.action="INVFOLLOWUP.action?saveFlag=YES";
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
    
    
    function checkedsave(a,id)
    {
     if(a.checked) 
     {
        document.getElementById('SAVEFLG'+id).value='Yes' ;
         
     }else{
         document.getElementById('SAVEFLG'+id).value='' ;
     }
        
    }
 </script>
</head> 
 
<body class="body1" oncontextmenu="return true;" >
        <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>

 <form  name="frm" id="frmid"  method="post" enctype="multipart/form-data"> 
     <table height="96%" bgcolor="#f2f2f2"  align="center" width="100%">
         <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
          <table border="0"  cellpadding="1" align="center" width="100%" >
              <tr><td valign="top"  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;">Invoice Followup [Action Type]
             </td></tr>
              
          </table> 

            <table width="100%"  align="center"  cellpadding="0" cellspacing="0">
                   <tr  valign="center"><td align="right" >
                      
                      <td  align="left" class="label-1"  style="padding-right:5pt;" >AC Holder :
                           <s:select name="sac" value="%{sac}" cssStyle="text-transform:uppercase;width:180pt" theme="simple" list="%{acList}" headerKey="%" headerValue="Select AC Holder" listKey="LIST_CODE" listValue="LIST_NAME"  />
                       </td>
                       <td  align="left" class="label-1"  style="padding-right:5pt;" >Action Type:
                            <s:if test='%{showFlag=="YES"}'>
                                <s:select name="stype"  disabled="true" cssStyle="text-transform:uppercase;width:250pt" theme="simple" list="%{TypeList}"  value="%{stype}"  listKey="LIST_CODE" listValue="LIST_NAME" />
                                <s:hidden name="stype" value="%{stype}"/>
                            </s:if>
                            <s:else> 
                                <s:select name="stype" cssStyle="text-transform:uppercase;width:250pt" theme="simple" list="%{TypeList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"  onchange="GetSearch()" onblur="GetSearch()" />
                            </s:else>     
                       </td>
                        
                                <td align="right" >
                                      <button  id="saveheadId"  class="sexybutton" onclick="recsave()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                       <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('INVFOLLOWUP.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                       <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                                    
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
                          <th class="label-1" align="center" style="width:10%" >Invoice No.<input title="Select All Invoice " type="CHECKBOX" name="selinv" value="Y" onclick="selectinv()"></th>
                           <th class="label-1" style="width:5%">Awb Date</th>
                          <th class="label-1" style="width:5%">Plan No.</th>
                          <th class="label-1" style="width:5%">PCH</th>
                          <th class="label-1" style="width:5%">MOS</th>
                          <th class="label-1" style="width:5%">Port</th>
                           <th class="label-1"  style="width:5%">TTO Date</th>
                          <th class="label-1"  style="width:5%">TO Date</th>
                          <th class="label-1"  style="width:5%">ETD Date</th>
                           <th class="label-1"  style="width:5%">FCR</th>
                          <th class="label-1" style="width:5%">Buyer</th>
                          <th class="label-1" style="width:5%" >Qnty</th>
                          <th class="label-1" style="width:10%" >ErrMsg</th>
                      
                        </tr>
                      </thead>    
                      <tbody
                         <s:if test="%{invList!=null}">
                           <s:iterator value="invList" status="st" >
                                    <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                    <td style="font-size:8pt"><s:textfield name="SR_NO" readonly="true" cssStyle="width:20pt;text-align:right" theme="simple"  value="%{#st.index+1}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="EXCS_INV_NO" id="EXCS_INV_NO%{#st.index+1}" cssStyle="width:70pt;" theme="simple"  value="%{EXCS_INV_NO}"/>
                                            <s:if test='%{ERRMSG!=null}'>
                                                   <input type="checkbox" disabled="true"   theme="simple"   />
                                             
                                            </s:if>
                                            <s:else> 
                                                   <input type="checkbox"   name="UPDCODE" onclick="checkedsave(this,<s:property value="%{#st.index}"/>)"  title="Select Invoice" theme="simple" value="<s:property value="%{#st.index}"/>"/> 
                                      
                                            </s:else>            
                                      </td>
                                
                                    <td><sx:datetimepicker  name="AWB_DATE" id="AWB_DATE%{#st.index+1}"  displayFormat="dd/MM/yyyy" value=""  cssStyle="width:70pt;font-size:9pt"/> </td>
                                    <td  style="font-size:8pt"><s:textfield name="PLAN_NO" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{PLAN_NO}"/></td>
                                    <td  style="font-size:8pt"><s:textfield name="PCH" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{PCH}"/></td>
                                    <td  style="font-size:8pt"><s:textfield name="MOS" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{MOS}"/></td>
                                    <td  style="font-size:8pt"><s:textfield name="PORT" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{PORT}"/></td>
                                    <td  style="font-size:8pt"><s:textfield name="TTO_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{TTO_DATE}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="TO_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{TO_DATE}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="ETD_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{ETD_DATE}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="FCR" readonly="true" cssStyle="width:80pt;" theme="simple" value="%{PRE_DOCS}"/></td>
                                  
                                    <td style="font-size:8pt"><s:textfield name="BUYER" readonly="true" cssStyle="width:80pt;" theme="simple" value="%{BUYER}"/></td>
                                    <td style="font-size:8pt" ><s:textfield name="INV_QTY" readonly="true" cssStyle="width:70pt;;text-align:right" theme="simple" value="%{INV_QTY}"/>
                                    </td>
                                
                                    <td style="font-size:8pt" >
                                      <input type="hidden" name="SAVEFLG" id="SAVEFLG<s:property value="%{#st.index}"/>" value="" readonly="true" cssStyle="width:10pt;text-align:right" theme="simple"/>
                               
                                        <s:textfield name="ERRMSG" readonly="true" cssStyle="width:140pt;;text-align:left;color:red" theme="simple" value="%{ERRMSG}"/>
                                   
                                       
                              </tr> 
                            </s:iterator>
                         </s:if>
                            <s:if test='%{showrec=="NOT"}'>
                               
                                <s:iterator status="st" begin="0" end="20">
                            
                                <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                    <td style="font-size:8pt"><s:textfield name="SR_NO" readonly="true" cssStyle="width:20pt;text-align:right" theme="simple"  value="%{#st.index+1}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="EXCS_INV_NO" id="EXCS_INV_NO%{#st.index+1}" cssStyle="width:80pt;" theme="simple"  value="%{EXCS_INV_NO[#st.index]}"/>
                                            <input type="checkbox" name="UPDCODE" onclick="checkedsave(this,<s:property value="%{#st.index}"/>)"  title="Select Invoice" theme="simple" value="<s:property value="%{#st.index}"/>" 
                                    </td>
                                 
                                    <td><sx:datetimepicker  name="AWB_DATE"    displayFormat="dd/MM/yyyy"   cssStyle="width:70pt;font-size:9pt"/> </td>
                                    <td  style="font-size:8pt"><s:textfield name="PLAN_NO" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{PLAN_NO[#st.index]}"/></td>
                                    <td  style="font-size:8pt"><s:textfield name="PCH" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{PCH[#st.index]}"/></td> 
                                    <td  style="font-size:8pt"><s:textfield name="MOS" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{MOS[#st.index]}"/></td>
                                    <td  style="font-size:8pt"><s:textfield name="PORT" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{PORT[#st.index]}"/></td>
                                    <td  style="font-size:8pt"><s:textfield name="TTO_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{TTO_DATE[#st.index]}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="TO_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{TO_DATE[#st.index]}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="ETD_DATE" readonly="true" cssStyle="width:60pt;" theme="simple" value="%{ETD_DATE[#st.index]}"/></td>
                                    <td style="font-size:8pt"><s:textfield name="BUYER" readonly="true" cssStyle="width:70pt;" theme="simple" value="%{BUYER[#st.index]}"/></td>
                                    <td style="font-size:8pt" ><s:textfield name="INV_QTY" readonly="true" cssStyle="width:50pt;text-align:right" theme="simple" value="%{INV_QTY[#st.index]}"/>
                                        <input type="hidden" name="SAVEFLG" id="SAVEFLG<s:property value="%{#st.index}"/>" value="" readonly="true" cssStyle="width:50pt;text-align:left" theme="simple"/>
                                       
                                    
                                    </td>
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
         <s:if test='%{showrec=="NOT"}'>
                    <tr><td align="center"><input type="button" name="show"  value="Get Invoice Detail" onclick="GetDetail()"  cssStyle="width:50pt"></td></tr>     
         </s:if>
         <tr style="height:5%" bgcolor="#f2f2f2" >
              <td style="border-width:1pt;border-color:black;border-style:solid;" >
                  <table   align="center" width="100%" >
                      <tr><td align="left" > Date : <s:property value="%{currentdate}" /> </td>
                    <td valign="bootom" align="center"  style="color:red;font-weight:bold">
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

  