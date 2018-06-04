

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>

 <script language="javascript">

  function callme()
  {

            var index=document.frm2.account_code.selectedIndex;
            var acode   = window.opener.document.frm.SINVNO;
            var aname   = window.opener.document.frm.SINVNO;
            acode.value=document.frm2.account_code.options[index].value;
            aname.value=document.frm2.account_code.options[index].text;
            acode.focus();
            window.close();
 
  }
  function tabE(obj,e)
            {var e=(typeof event!='undefined')?window.event:e;// IE : Moz
                if(e.keyCode==13)
                {
                     //alert(e.value);
                    pp=document.frm2.party.value;
                    document.frm2.action="INVHELP.action?empview="+pp;
                    document.frm2.submit();
                }
            }
  function ent_key(e)
  {

   if(!e)
   {e = window.event;   }

   if(e.keyCode == 13)
   {

        pp=document.frm2.party.value;

        document.frm2.action="INVHELP.action?empview="+pp;
      //  document.frm2.submit();
   }
  }
  </script>
     </head>
    <body class="body1" action="" method="post" onload="document.frm2.party.focus();">
        <form name="frm2" action="" method="post" >
            <center>
            <table border="0" width="100%">
              <td style="text-align:center;font-family:Times New Roman; font-weight:bold;color:white;">
                  Search: <s:textfield name="party" theme="simple" onkeypress="return tabE(this,event)"  maxlength="36" cssStyle="width:100pt;text-transform:uppercase" />
              </td>
            </table>
            <table border="0" width="100%">
              <td align="center">
                  <s:if test="%{empList!=null}" >
                      <s:select
                          name="account_code"
                          id="account_code"
                          headerKey=""
                          headerValue="Select"
                          list="empList"
                          theme="simple"
                          cssStyle="width:350.0pt"
                          listKey="LIST_CODE"
                          listValue="LIST_NAME"
                          value=""
                          size = "20"
                          ondblclick="callme()"

                          />
                  </s:if>
              </td>
            </table>
            <input type="Button" name="btnselect" value="Select" onclick="callme();" class="submitbutton1" style="width:40pt;color:black;">
            <input type="button" name="btn" value="Close" class="submitbutton1" style="width:40pt;color:black;" onclick="javascript:self.close()">
            </center>
        </form>
    </body>
</html>
