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
       
            document.frmbuy.action="searchpagemccentAction.action";
            document.frmbuy.submit();
   }
 }
 
 function callme()
  {
   var index=document.frmbuy.listname.selectedIndex;
    <s:if test="%{SEARCH_TYPE==4}">
    var CODE   = window.parent.document.frmname.DEPT_CODE;
    var NAME   = window.parent.document.frmname.DEPT_DESC;
    CODE.value=document.frmbuy.listname.options[index].value;
    var tcode = document.frmbuy.listname.options[index].text;
    //tccc=tcode.substring(tcode.indexOf("-")+1,tcode.length);
    NAME.value=tcode;
    window.parent.document.frmname.action="newmstmccentAction.action";
    window.parent.document.frmname.submit();
   
    
    </s:if>
   
    <s:if test="%{SEARCH_TYPE==3 }">
    var CODE   = window.parent.document.frmname.TYPE_CODE<s:property value="TXTID"/>;
    var DESC   = window.parent.document.frmname.TYPE_DESC<s:property value="TXTID"/>;
      
    CODE.value=document.frmbuy.listname.options[index].value;
    var tcode = document.frmbuy.listname.options[index].text;
    tccc=tcode.substring(tcode.indexOf("-")+1,tcode.length);
    DESC.value=tccc;
    </s:if>
        
      <s:if test="%{SEARCH_TYPE==5 }">
    var CODE   = window.parent.document.frmname.TYPE_CODE<s:property value="TXTID"/>;
    var DESC   = window.parent.document.frmname.TYPE_DESC<s:property value="TXTID"/>;
      
    CODE.value=document.frmbuy.listname.options[index].value;
    var tcode = document.frmbuy.listname.options[index].text;
    tccc=tcode.substring(tcode.indexOf("-")+1,tcode.length);
    DESC.value=tccc;
    window.parent.document.frmname.document.getElementById('invoicefrm').src="newmstccmccentAction.action?DEPT_CODE=<s:property value="%{DEPT_CODE}"/>&TYPE_SL_NO="+document.frmbuy.listname.options[index].value;
    </s:if>   
        
    window.parent.approveraddidClose();
  }
       </script>
    </head>
    <body class="body1" onload="document.getElementById('SEARCH_CODE').focus()" style="background-color: #f3f3f3" >
   
<form action=""  method="post" name="frmbuy" >
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
       <table width="100%">
           <tr><td class="label-1">Search:</td><td><s:textfield name="SEARCH_CODE" id="SEARCH_CODE" theme="simple" onkeypress="ent_key()" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/></td>
    </tr><tr>
        <td colspan="2">
            <%--EAAITM+'-'+ --%>
            <s:if test="%{SEARCH_TYPE==4}">
            <s:select id="listid" size="20" ondblclick="callme();" onkeypress="callme();" cssStyle="width:350px" name="listname" value="" list="mastlist" listKey="EAAITM" listValue="%{EATX40}"  theme="simple" />
          </s:if>
            <s:else>
                <s:select id="listid" size="20" ondblclick="callme();" onkeypress="callme();" cssStyle="width:350px" name="listname" value="" list="mastlist" listKey="EAAITM" listValue="%{EAAITM+'-'+EATX40}"  theme="simple" />
          
            </s:else>
            <s:hidden name="SEARCH_TYPE" id="SEARCH_TYPE" value="%{SEARCH_TYPE}"/>
            <s:hidden name="TXTID" id="TXTID" value="%{TXTID}"/>
            <s:if test="%{SEARCH_TYPE==5 }">
               <s:hidden name="DEPT_CODE" id="DEPT_CODE" value="%{DEPT_CODE}"/> 
            </s:if>
            
        </td>
   </tr>

                        
                        
                        </table>
            </td></tr>
           <tr>
            <td height="1pt"  align="center" style="color:red;font-weight:bold">
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
