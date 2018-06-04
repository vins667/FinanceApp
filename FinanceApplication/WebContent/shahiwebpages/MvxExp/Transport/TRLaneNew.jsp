<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
<html>
    <head>
          <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd - Transport Module</title>
 <script src="../js/datetimepicker_css.js"></script>
 <script language="javascript">
     
  
    function onlyNumbers(evt)
    {      if(evt.value!="" && !evt.value.match( /^\d+$|^\d+\.\d{2}$/) )
            {   alert("Please Enter 4 Decimal Numbers Only !!!");
                    evt.focus();
                    evt.value="";
                return false; }
            return true;
    }
  
   function validateinput(){
 
      
 
     if(document.frm.STATE_CODE.value=="")
        {
           alert("Please Select  State List")
           document.frm.STATE_CODE.focus();
           return false;
        }
         if(document.frm.TYPE_CODE.value=="")
        {
           alert("Please Select Fuel type")
           document.frm.CT_LN.focus();
           return false;
        }
         if(document.frm.PRICE.value=="")
        {
           alert("Please Enter PRICE")
           document.frm.PRICE.focus();
           return false;
        }
         
         
    return true;
    }
 

    function recsave()
    {
       if(validateinput()){
         if(confirm('You Want to save Records ? '))
        {
            document.getElementById("saveid").disabled=true;
           document.frm.action="TRFUELMAST.action?saveFlag=Yes";
            document.frm.submit();
        }
        }else{
            document.getElementById("saveid").disabled=false;
            return ;}
     }
 
     
 </script>
</head>

<body class="body1" onload="document.frm.CT_CODE.focus();" oncontextmenu="return false;">
 <form action=""  method="post" name="frm" >
     <table height="86%" bgcolor="#f2f2f2"  align="center" width="100%">
         <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
                
          <table border="0"  cellpadding="5" align="center" width="100%" >
             <tr><td  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;">  OutBound Transporter Lane/Route Master
             </td></tr>
             <tr><td align="center" >
             <table width="590pt" cellpadding="0" cellspacing="0">
                 <tr><td align="right" >
                      <button id="backheadId" class="sexybutton" onclick="javascript:window.location.href('TRLANEMAST.action');"><span><span><span class="undo" id="backId">Back</span></span></span></button> &nbsp;
                      <button id="saveheadId" class="sexybutton" onclick="recsave();"><span><span><span class="save" id="saveId">Save</span></span></span></button> &nbsp;
                           
                </td>
             </tr>
             </table>
             </td>
            </tr>
          </table>
            <table   align="center" cellpadding="1"  cellspacing="1" >
               <tr> <td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;"  >
              <table width="590pt" align="center" cellpadding="1" cellspacing="1" >
                
                
                       <tr>
                           <td>Coming Soon...................</td>
                       </tr>
                               
                    
                     
                   
                       
                </table>
              </td></tr>
            </table>

            <div style="height:20pt">
                <table align="center" cellpadding="0"  cellspacing="1">
                <tr >
                  <td>
                    <s:iterator value="errorList" id="id">
                        <table bgcolor="red" >
                    <tr><td>
                      <s:set id="setid" name="setid" value="%{'Yes'}"/>
                    </td></tr>
                     </table>
                    </s:iterator>

                 </td> </tr> 
 <!--               <tr> <td><s:if test="%{#setid=='Yes'}">
                        Code Already Exist
                    </s:if>
                   </td>
                 </tr>  -->
                 </table>
            </div>
        </td></tr>
         <tr style="height:5%" bgcolor="#D8D8D8" >
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
              </table>
          </td></tr>
     </table>
            <input type="hidden" value="<s:property value="%{#session.sessUserId}"/>" name="aausrid">
            

 </form>
</body>
</html>

