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
       
            document.frmbuy.action="searchpagembillAction.action";
   
    document.frmbuy.submit();
   }
 }
 
 function callme()
 {
    var index=document.frmbuy.listname.selectedIndex;
    <s:if test="%{SEARCH_TYPE==3}">
    var CODE   = window.parent.document.frmname.DEPT_CODE;
    var fromstr=document.frmbuy.listname.options[index].text;
    fromstr=fromstr.substring(0,fromstr.lastIndexOf("-"));
    var NAME   = window.parent.document.frmname.DEPT_DESC;
    CODE.value=document.frmbuy.listname.options[index].value;
    NAME.value=fromstr;
    window.parent.document.frmname.action="newmstmbillAction.action"
    window.parent.document.frmname.submit();
    </s:if>
    <s:if test="%{SEARCH_TYPE==133333 }">
        var CODE   = window.parent.document.frmname.<s:property value="TXTID"/>;
       
    CODE.value=document.frmbuy.listname.options[index].value;
    </s:if>
    <s:if test="%{SEARCH_TYPE==1 || SEARCH_TYPE==4 }">
    var CODE   = window.parent.document.frmsub.<s:property value="TXTID"/>;
     CODE.value=document.frmbuy.listname.options[index].value;
    CODE.title=document.frmbuy.listname.options[index].text
    </s:if>
      
     <s:if test="%{SEARCH_TYPE==10}">
         
    var CODE   = window.parent.document.frmname.COPY_DEPT_CODE;
    var fromstr=document.frmbuy.listname.options[index].text;
    fromstr=fromstr.substring(0,fromstr.lastIndexOf("-"));
    var NAME   = window.parent.document.frmname.COPY_DEPT_DESC;
    CODE.value=document.frmbuy.listname.options[index].value;
    NAME.value=fromstr;
    
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
            <s:select id="listid" size="20" ondblclick="callme();" onkeypress="callme();" cssStyle="width:350px" name="listname" value="" list="mastlist" listKey="EAAITM" listValue="%{EATX40+'-'+EAAITM}"  theme="simple" />
       
            <s:hidden name="SEARCH_TYPE" id="SEARCH_TYPE" value="%{SEARCH_TYPE}"/>
            <s:hidden name="TXTID" id="TXTID" value="%{TXTID}"/>
            
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
