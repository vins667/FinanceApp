

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#006699;
                background-image:url('image/table-gradient.jpg');
            }
            tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }
        </style>
        <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 22px ;
        }
        .tr1 {
             position:absolute;
             top: expression(this.offsetParent.scrollTop);
          }
        tbody {
            height: auto;
        }
    </style>
<![endif]-->

 <script language="javascript">

  function callme()
  {
            var index=document.frm2.account_code.selectedIndex;
            var acode   = window.opener.document.frm.account_code;
            var aname   = window.opener.document.frm.account_name;
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
                    document.frm2.action="VendAcListaction.action?vendview="+pp;
                    document.frm2.submit();
                }
            }
  function ent_key(e)
  {
      
   if(!e)
   {e = window.event;   }
   
   if(e.keyCode == 13)
   {
     //  alert(e.value);
        pp=document.frm2.party.value;
       // alert("document.frm2.party.value");
        document.frm2.action="VendAcListaction.action?vendview="+pp;
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
                  <s:if test="%{FinAcList!=null}" >
                      <s:select
                          name="account_code" id="account_code"
                          headerKey=""
                          headerValue="Select "
                          list="FinAcList"
                          theme="simple"
                          cssStyle="width:410.0pt"
                          listKey="VEND_TYPECODE"
                          listValue="VEND_TYPEDESC"
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
