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
      aa = document.getElementById("locindex").value;
      var index=document.frm2.party_to.selectedIndex;
      alert(document.getElementById("locindex").value);
      var reqpono   = window.opener.document.getElementById("reqpono"+aa);
      var rpoamt   = window.opener.document.getElementById("rpoamt"+aa);
      var advamt   = window.opener.document.getElementById("advamt"+aa);
      bb=document.frm2.party_to.options[index].value;
      aa=document.frm2.party_to.options[index].text;
      reqpo=aa.substring(0,aa.indexOf("-"));
      amt=bb.substring(0,bb.indexOf("-"));
      aamt=bb.substring(bb.indexOf("-")+1,bb.length)
      
      reqpono.value=reqpo;
      rpoamt.value=amt;       
      advamt.value=aamt; 
       window.close();
  }

  function ent_key(e)
  {
     
   if(!e)
   {e = window.event;   } 
   if(e.keyCode == 13){
        pp=document.frm2.IDPUNOS.value;
//alert(pp);
        document.frm2.action="getpoBuyerFundRequest.action";
      //document.frm2.submit();
  }
  }
  </script>
     </head>
    <body class="body1" action="" method="post" >
        <form name="frm2" action="" method="post" >
            <s:hidden name="locindex" id="locindex" value="%{locindex}"/>
            <s:hidden name="SUP_CODE" id="SUP_CODE" value="%{SUP_CODE}"/>
            <center>
            <table border="0" width="100%">
              <td style="text-align:center;font-family:Times New Roman; font-weight:bold;color:white;">
                  Search: <s:textfield name="IDPUNOS" theme="simple" onkeypress="ent_key(event)"  maxlength="36" cssStyle="width:100pt;text-transform:uppercase" />
              </td>
            </table>
            <table border="0" width="100%">
              <td align="center">
                  <s:if test="%{podetaillst!=null}" >
                      <s:select
                          name="party_to" id="party_to"
                          headerKey=""
                          headerValue="Select "
                          list="podetaillst"
                          theme="simple"
                          cssStyle="width:410.0pt"
                          listValue="PO_NO+'-'+PO_AMT"
                          listKey="PO_AMT+'-'+PO_ADV_AMT"
                          value="%{party_to}"
                          size = "20"
                          ondblclick="callme()"
                          onclick="callme()"
                          onkeypress="return tabE(this,event)"
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
