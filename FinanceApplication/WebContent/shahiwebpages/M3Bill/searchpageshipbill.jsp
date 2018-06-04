<%-- 
    Document   : searchpage
    Created on : Aug 6, 2013, 10:53:37 AM
    Author     : RANJEET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="css/style.css">  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sepl</title>
         <script language="javascript">
            function ent_key(e)
 {if(!e)
  {e = window.event;
  }
   if(e.keyCode == 13)
   {
       
            document.frmbuy.action="shipbillsearchmbillentAction.action";
   
    document.frmbuy.submit();
   }
 }
 
 function callme()
  {
     var index=document.frmbuy.invidsearch.selectedIndex;
     var fromstr=document.frmbuy.invidsearch.options[index].value;
    
     window.parent.document.frmname.SSHIPBILL<s:property value="TXTID"/>.value=fromstr.substring(0,fromstr.indexOf(":"));
     window.parent.document.frmname.SSHIPDATE<s:property value="TXTID"/>.value=fromstr.substring(fromstr.indexOf(":")+1,fromstr.length);
   
    
    window.parent.invoicesearchClose();
  }
       </script>
    </head>
    <body class="body1" onload="document.getElementById('SEARCH_CODE').focus()" style="background-color: #f3f3f3;padding: 0px;margin: 0px" >
   
        <form action="" style="padding: 0px;margin: 0px"  method="post" name="frmbuy" >
 <table align="center" width="100%"><tr><td valign="top"  >

              <table border="0" bgcolor="#f2f2f2" cellpadding="1" align="center" width="100%" >
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top"  >
       <table width="100%">
           <tr><td class="label-1">Shipping Bill:</td><td><s:textfield name="SEARCH_CODE" id="SEARCH_CODE" theme="simple" onkeypress="ent_key()" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/></td>
    </tr><tr>
        <td colspan="2">
            <s:select id="invidsearch" size="8" ondblclick="callme();" onkeypress="callme();" cssStyle="width:305px" name="invidsearch" value="" list="shipbilldatelist"   theme="simple" />
       
            <s:hidden name="PCH" id="PCH" value="%{PCH}"/>
           <s:hidden name="TXTID" id="TXTID" value="%{TXTID}"/>
            
        </td>
   </tr>

                        
                        
                        </table>
            </td></tr>
           <tr>
            <td height="1pt"  align="center" style="color:red;font-size: 10px">
                <div style="height:5pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />
                   
                </div>


            </td>
        </tr>
                  </table>

      

                  </td></tr></table>
                    

  </form>


      
    </body>
</html>
