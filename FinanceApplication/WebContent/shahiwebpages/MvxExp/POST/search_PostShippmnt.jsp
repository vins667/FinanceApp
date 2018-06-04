<%-- 
    Document   : search
    Created on : JAN 12, 2017, 9:40:24 AM
    Author     : Guddu Kumar
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<link type="text/css" rel="stylesheet" href="../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="../css/main.css">  

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Export Pvt Ltd.</title>


        <script language="javascript">

            function srh()
            {

                document.frmbuy.action = "printInvPOSTSHIPMENT.action";
                document.frmbuy.submit();
                document.getElementById('prepage').style.visibility = '';

            }

            function putValue(val)
            {
                var code = val;
                //window.parent.document.getElementById("FORM_BUYER_CODE").value=barcode;
                window.parent.document.getElementById("LCOANO").value = code;
                window.parent.closediv('approveraddid');
                //window.parent.document.getElementById
            }


            function tabE(obj, e) {

                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for (var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other

                        if (obj == ele[i])
                        {
                            ele[q].focus();
                            break
                        }
                    }
                    srh();
                    return false;
                }

            }
        </script>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:white;
                background-image:url('../css/image/table-gradient.jpg');
            }
            tbody {
                height: 0px;
                overflow-y: auto;
                overflow-x: hidden;

            }
        </style>
        <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height:0px; 
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
        <%--  padding-top: 28px ;  --%>
     }

      }
     thead tr {

     }
     tbody {
         height: auto;
     }
       }
 </style>
<![endif]-->
    </head>
    <body onload="waitPreloadPage();
          document.getElementById('INVOICE_NO_INV').focus();" style="background-color: #eafbfc" >
        <form action=""  method="post" name="frmbuy" >
            <DIV align="center" id="prepage" class="lodingdiv" style="right:450px;position:absolute;z-index: 1;visibility: hidden;background: transparent;top:50px;" >
                <img src="../shahiwebpages/css/img/loading.gif" />       
            </DIV>

            <table width="100%">
                <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;">
                        <table width="100%">
                            <tr><td class="label-1">Enter Invoice No :
                                    <s:textfield name="INVOICE_NO_INV" id="INVOICE_NO_INV" onKeyPress="tabE(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/>&nbsp;&nbsp;<button  id="deleteheadId" class="sexybutton" onclick="srh();"><img src="../images/pdf1.png" style="border-width: 0px;"/></button>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr>
                    <td height="1pt"  align="center" style="color:red;font-weight:bold;font-size: 14px">
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

