<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet"   type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
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
  
  

         function GetDetail()
        {
              document.frm.action="PUUPDATE.action";
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
            if(document.frm.SEARCH_BUYER.value=="" && document.frm.SEARCH_BH.value=="" )
            {
               alert("Please Select Buyer or Bying House ")
                document.frm.SEARCH_BH.focus();
               return false;
            }
              document.frm.action="PUUPDATE.action?showFlag=Yes";
              document.frm.submit();
              document.getElementById("showeid").style.display = 'none';
              document.getElementById('prepage').style.visibility='';
       //  document.getElementById("sbank2").disabled=true;
        }
         
    function recsave()
    {
          if(confirm('You Want to Forward Records ? ')){
         
            document.frm.action="PUUPDATE.action?updFlag=YES";
            document.frm.submit();
            
        }
     } 
     
     function GetSort()
        {
              document.frm.action="PUUPDATE.action?sortFlag=Yes";
              document.frm.submit();
              document.getElementById("saveheadId").style.display = 'none';
              document.getElementById('prepage').style.visibility='';
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
    function searchinv(objsrc,objtrg,index)
{ 
     
   
   var xmlHttpReq = false; 
   var self = this; 
  
   if (window.XMLHttpRequest) { 
       self.xmlHttpReq = new XMLHttpRequest();   
   } else if (window.ActiveXObject) {  
       self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
   }
   
   self.xmlHttpReq.open('POST', 'ajaxPortalInvPOSTAJAX', false); 
   self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
   self.xmlHttpReq.onreadystatechange = function() {
    
      	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
       {  
             var a = self.xmlHttpReq.responseText;
             var b= new Array();
            
             b=a.split('#');
             
             if(b.length>10)
             {    
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
              }
               else
               {
                 //  alert(b[1]);
                 //  alert(objsrc.value=b[0]);
                
                  
                   document.getElementById('BUYER'+index).value=b[0];
                   document.getElementById('PCH'+index).value=b[1];
                   document.getElementById('INV_DATE'+index).value=b[2];
                   document.getElementById('TO_DATE'+index).value=b[3];
                   document.getElementById('ETD_DATE'+index).value=b[4];
                   document.getElementById('FTP_DATE'+index).value=b[5];
                   document.getElementById('AWB_DATE'+index).value=b[6];
                   document.getElementById('CRNCY_CODE'+index).value=b[7];
                   document.getElementById('MOS'+index).value=b[8];
                   document.getElementById('CNTRY'+index).value=b[9];
                   
                     
                  
               }
             }
   	}
   }
         param=objsrc.value;
         self.xmlHttpReq.send("INVOICE_NO_S="+objsrc.value+"&time="+(new Date()).getTime());
    
}
 </script>
</head> 
 
<body class="body1" oncontextmenu="return true;" >
        <DIV align="center" id="prepage" style="z-index:10;position:absolute; top:80px; left:350px;background-color:transparent;visibility: hidden;" class="lodingdiv" >
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>

 <form  name="frm" id="frmid"  method="post" enctype="multipart/form-data"> 
     <table height="96%" bgcolor="#f2f2f2"  align="center" width="100%">
         <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
          <table border="0"  cellpadding="1" align="center" width="100%" >
              <tr><td valign="top"  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;">Approval Date Upation
             </td></tr>
              <tr>
                    <td  align="center" class="label-1">EDI/Creadit Card/Portal Updation/Approved Date</td> 
                </tr>
         
          </table> 

            <table width="100%"  align="center"  cellpadding="0" cellspacing="0">
                   <tr  valign="center"> 
                      <td  align="left" class="label-1"  style="padding-right:5pt;" >Buying House :
                     
                          <s:select name="SEARCH_BH" value="%{SEARCH_BH}" cssStyle="text-transform:uppercase;width:180pt" theme="simple" list="%{acList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" onblur="GetSort()"  />
                      </td>
                        
                        <td  align="left" class="label-1"  style="padding-right:5pt;" >Buyer:
                              <s:select name="SEARCH_BUYER"  cssStyle="text-transform:uppercase;width:90pt" theme="simple" list="%{buyerList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" value="%{SEARCH_BUYER}"/>
                         </td>
                         <td align="center">
                         <button  id="searchheadId" class="sexybutton" onclick="GetSearch()"><span><span><span class="search" id="searchId">All Pending Invoice</span></span></span></button>&nbsp;&nbsp;&nbsp;
                         </td>
                      <td align="right" >
                              <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PUUPDATE.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                                     
                          <button  id="saveheadId"  class="sexybutton" onclick="recsave()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                              
                        </td>   
                        
                     
                </tr>
               
             </table>
             <table  width="100%"  align="center"  cellpadding="0" cellspacing="0">
                <tr><td  valign="top" style="border-width:0pt;border-color:#2680b5;border-style:solid;" >
                 <div  style="position:relative;width:100%;overflow:auto ;height:400pt;">
                     <table align="center" width="100%" cellpadding="0" bgcolor="#d0d7e5" cellspacing="0" >
                     <thead >
                       <tr class="hd" style="position:relative; top: expression(this.offsetParent.scrollTop);height:20pt" >
                          <th class="label-1"  >Sl# </th> 
                          <th class="label-1"  >Invoice No</th>
                          <th class="label-1"  >
                              <input title="Select All Invoice " type="CHECKBOX" name="selinv"   onclick="selectinv()"></th>
                          <th class="label-1" >Buyer</th>
                          <th class="label-1">PCH</th>
                          <th class="label-1" >Inv Date</th>
                          <th class="label-1" >TO Date</th>
                          <th class="label-1" >ETD Date</th>
                          <th class="label-1" >FTP Date</th>
                          <th class="label-1"  >Awb Date</th>
                          <th class="label-1"  >CrncyCode</th>
                          <th class="label-1"  >MOS</th>
                          <th class="label-1"  >Cntry</th>
                        </tr>
                      </thead>      
                      <s:if test="%{invList!=null && invList.size()>0}">
                               
                           <s:iterator value="invList" status="st" >
                               <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                <td style="font-size:8pt"><s:textfield name="SR_NO" readonly="true" cssStyle="width:20pt;text-align:right" theme="simple" cssClass="textreadonly" value="%{#st.index+1}"/></td>
                                <td style="font-size:8pt"><s:textfield name="EXCS_INV_NO"  id="EXCS_INV_NO%{#st.index+1}" theme="simple"  cssStyle="width:80pt;" value="%{EXCS_INV_NO}"  />
                                <td>
                                        <s:checkbox  name="UPDCODE" id="UPDCODE%{#st.index}" fieldValue="%{EXCS_INV_NO}" theme="simple" cssStyle="width:20px;"/>
                                </td>
                                <td style="font-size:8pt"><s:textfield name="BUYER" id="BUYER%{#st.index+1}" readonly="true" cssClass="textreadonly" theme="simple" value="%{BUYER}"/></td>
                                <td style="font-size:8pt"><s:textfield name="PCH" id="PCH%{#st.index+1}" readonly="true"  cssClass="textreadonly" theme="simple" value="%{PCH}"/></td>
                                <td style="font-size:8pt"><s:textfield name="INV_DATE" id="INV_DATE%{#st.index+1}" readonly="true" cssClass="textreadonly" theme="simple" value="%{INV_DATE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="TO_DATE" id="TO_DATE%{#st.index+1}" readonly="true" cssClass="textreadonly" theme="simple" value="%{TO_DATE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="ETD_DATE" id="ETD_DATE%{#st.index+1}" readonly="true" cssClass="textreadonly" theme="simple" value="%{ETD_DATE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="FTP_DATE" id="FTP_DATE%{#st.index+1}" readonly="true" cssClass="textreadonly" theme="simple" value="%{PORT}"/></td>
                                <td style="font-size:8pt"><s:textfield name="AWB_DATE" id="AWB_DATE%{#st.index+1}" readonly="true" cssClass="textreadonly" theme="simple" value="%{TTO_DATE}"/></td>
                                <td style="font-size:8pt"><s:textfield name="CRNCY_CODE" id="CRNCY_CODE%{#st.index+1}" readonly="true" cssClass="textreadonly" cssStyle="width:80px;"  theme="simple" value="%{CRNCY}"/></td>
                                <td style="font-size:8pt"><s:textfield name="MOS" id="MOS%{#st.index+1}" readonly="true" cssClass="textreadonly" cssStyle="width:70px;"  theme="simple" value="%{MOS}"/></td>
                               <td style="font-size:8pt"><s:textfield name="CNTRY" id="CNTRY%{#st.index+1}" readonly="true" cssClass="textreadonly"  cssStyle="width:70px;" theme="simple" value="%{CNTRY}"/></td>
                               </tr> 
                            </s:iterator>
                         </s:if>
                         <s:else>
                          <s:iterator begin="0" end="15"   status="st1" >
                               <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                <td style="font-size:8pt"><s:textfield name="SR_NO" readonly="true" cssStyle="width:20pt;text-align:right" theme="simple" cssClass="textreadonly" value="%{#st1.index+1}"/></td>
                                <td style="font-size:8pt"><s:textfield name="UPDCODE"  id="EXCS_INV_NO%{#st1.index+1}" theme="simple"  cssStyle="width:80pt;"  onblur="if(this.value!='') searchinv(this,'EXCS_INV_NO%{#st1.index+1}','%{#st1.index+1}')" />
                                <td>
                                  </td> 
                                <td style="font-size:8pt"><s:textfield name="BUYER" id="BUYER%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1" /></td>
                                <td style="font-size:8pt"><s:textfield name="PCH" id="PCH%{#st1.index+1}" readonly="true"  cssClass="textreadonly" theme="simple"  tabindex="-1" /></td>
                                <td style="font-size:8pt"><s:textfield name="INV_DATE" id="INV_DATE%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1" /></td>
                                <td style="font-size:8pt"><s:textfield name="TO_DATE" id="TO_DATE%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple"  tabindex="-1"/></td>
                                <td style="font-size:8pt"><s:textfield name="ETD_DATE" id="ETD_DATE%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1" /></td>
                                <td style="font-size:8pt"><s:textfield name="FTP_DATE" id="FTP_DATE%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1" /></td>
                                <td style="font-size:8pt"><s:textfield name="AWB_DATE" id="AWB_DATE%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple" tabindex="-1" /></td>
                                <td style="font-size:8pt"><s:textfield name="CRNCY_CODE" id="CRNCY_CODE%{#st1.index+1}" readonly="true" cssClass="textreadonly" cssStyle="width:80px;"  theme="simple"  tabindex="-1"/></td>
                                <td style="font-size:8pt"><s:textfield name="MOS" id="MOS%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple" cssStyle="width:70px;"  tabindex="-1" /></td>
                               <td style="font-size:8pt"><s:textfield name="CNTRY" id="CNTRY%{#st1.index+1}" readonly="true" cssClass="textreadonly" theme="simple" cssStyle="width:70px;"  tabindex="-1" /></td>
                               </tr> 
                            </s:iterator>
                             
                             
                         </s:else>    
                             
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

 