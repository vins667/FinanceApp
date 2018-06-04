<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>

<link href="<s:url value="../css/main.css"/>" rel="stylesheet"
      type="text/css"/>
<html>
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            .datelabel{font-size:8pt;height:13pt;width:100pt;text-transform: uppercase}
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

            .Btn {

                background-color: #BDC7CE;
                background-image: URL(../image/glossyback.gif);
                background-repeat:repeat-x;
                border: 1px solid #677788;
                padding-top:2px;
                padding-bottom:4px;
                padding-left:10px;
                padding-right:10px;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 10px;
                color: #223546;
                cursor: hand;
                width: 130px;
                height:10pt;
                text-align:center;



            }

            .stylelabel {

                left: 0px;
                top: 15px;
                margin: 0px;
                padding: 0px;
                cursor: pointer;
                height: 11px;
                font-weight:bold;
                text-align: center;
                color: #223546;
                font-size: 12px;
                font-family: Verdana, Arial, Helvetica, sans-serif;
            }

            a {
                color: #000000;

                text-decoration: none;
            }

        </style>
        <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height: 480px;
            width: 600px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 28px ;
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
        <script language="javascript">



            function selectall()
            {
                var a=document.frm.fwdrec;

                if(a.length>0)
                {
                    if(document.frm.selall.checked==true)
                    {  for (var i=0;i<a.length;i++)
                        {a[i].checked=true;  }
                    }else
                    {
                        for (var i=0;i<a.length;i++)
                        {a[i].checked=false;  }
                    }
                }
                else{
                    if(document.frm.seleall.checked==true)
                    {a.checked=true; }
                    else
                    {a.checked=false; }
                }
            }

            function recfwd()
            {
                if(confirm('You Want to Accept ? '))
                {
                    document.frm.action="FundRequest.action?acFlag=Yes"
                    document.frm.submit()
                }
            } 
            function searchrec()
            {
                document.frm.action="FundRequestAction.action";
                document.frm.submit();
            }

        </script>
    </head>

</head>
</head>

<body class="body1" >

    <form action=""   method="post" name="frm" >
        <table align="center" width="100%">
            <tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >
                    <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                        <tr><td  class="hd" style=" font-size:18px; text-align:center">Fund Request Detail</td></tr>
                        <tr><td>
                                <table align="center" width="100%">
                                    <tr><td>
                                            <table align="center" width="100%">
                                                <tr><td>
                                                        <table width="100%" align="center">
                                                            <tr><td height="30pt" width="100%" valign="top" style="border-width:0pt;border-color:#2680b5;border-style:solid;" >
                                                                    <table  align="center" width="85%" cellpadding="3" border="0" >
                                                                        <tr>
                                                                            <td class="label-1">Req No:<s:textfield  cssClass="inputtxt" name="vreq" theme="simple" cssStyle="width:70pt;font-size:9pt" /></td>
                                                                            <td class="label-1">Req Date From:<sx:datetimepicker name="start_date1"  cssClass="txtd" displayFormat="dd/MM/yyyy"  cssStyle="width:40pt;font-size:9pt" /></td>
                                                                            <td class="label-1">Req Date To :<sx:datetimepicker name="end_date1"  cssClass="txtd" displayFormat="dd/MM/yyyy"  cssStyle="width:40pt;font-size:9pt" /></td>
                                                                            <td class="label-1">Party  :<s:textfield  cssClass="inputtxt" name="vparty" theme="simple" cssStyle="width:70pt;font-size:9pt" /></td>
                                                                            <td class="label-1">Req Type :<s:select label="Type"   theme="simple"  name="reqtype" list="reqtyplist" listKey="REQ_CODE" listValue="REQ_NAME" headerKey="" headerValue="All" /></td>

                                                                        </tr>
                                                                        <tr>

                                                                            <td class="label-1">Status   :<s:select label="Type"  cssStyle="width:70pt;font-size:9pt" theme="simple"  name="reqstatus" list="#{'':'ALL','1':'Request Made','2':'Accepted by A/C','3':'Under Process','4':'Cheque Delivered'}" /></td>
                                                                            <td class="label-1">Requested To : <s:select label="Type" cssStyle="width:70pt;font-size:9pt"  theme="simple"  name="payloct"  list="payloctlist" listKey="REQ_CODE" listValue="REQ_NAME" headerKey="" headerValue="All" /></td>
																			<td class="label-1">Requested By : <s:textfield cssStyle="width:70pt;font-size:9pt"  theme="simple"  name="reqbyid" title="Enter Employee Code"/></td>
																			<td class="label-1">PO No :<s:textfield  cssClass="inputtxt" name="vpono" theme="simple" cssStyle="width:70pt;font-size:9pt" /></td>
                                                                            <td align="right"> <input  type="button" name="bnt" style="width:50pt" onclick="searchrec()" value="Go" class="submitbutton1">
                                                                                <input  type="button" name="newbnt" style="width:50pt" onclick="window.location.href='FundRequestNew.action'" value="New" class="submitbutton1">
                                                                                <s:if test='%{acauth=="YES"}'>
                                                                                    <input  type="button" name="fwdbnt" style="width:50pt" onclick="recfwd()" value="Accept AC" class="submitbutton1">
                                                                                </s:if>
                                                                                <input type="button" name="exitbnt" style="width:50pt" onclick="self.close();" value="Exit" class="submitbutton1">
                                                                            </td>

                                                                        </tr>
                                                                    </table>
                                                                </td></tr>
                                                        </table>
                                                    </td></tr>
                                                <tr>
                                                    <td height="100pt" valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
                                                        <div  class="div1" style="width:100%;overflow:auto ;">
                                                            <table    width="100%"  cellpadding="1" bgcolor="#d0d7e5" cellspacing="1"  >
                                                                <thead>
                                                                    <tr style="background-color: #6699cc;position: absolute; top: expression(this.offsetParent.scrollTop);height:20pt" >
                                                                        <th class="label-1 ">Sl#</th>
                                                                        <th class="label-1 ">Req No</th>
                                                                        <th class="label-1" >Req Date</th>
                                                                        <th class="label-1" >Party</th>
                                                                        <th class="label-1" >Req Type</th>
                                                                        <th class="label-1" >Pymt Text</th>
                                                                        <th class="label-1" >Del.Place</th>
                                                                        <th class="label-1" >Status</th>
                                                                        <th class="label-1" >Pay Loct</th>                                                                        
                                                                    </tr> 
                                                                </thead>                                                                
                                                                <s:iterator value="detaillst" id="itr" status="st">

                                                                    <tr bgcolor="white">
                                                                        <td class="label-1"><s:property value="%{#st.index+1}"/></td>
                                                                        <td  align="center" style="font-size:9pt;font-weight:bold;">
                                                                            <s:url var="url" action="editFundRequestNew.action">
                                                                                <s:param name="reqno"><s:property value="%{REQNO}"/></s:param>
                                                                            </s:url>
                                                                            <s:a href="%{url}" theme="simple"><u><s:property value="%{REQNO}"/></u></s:a>
                                                                        </td>
                                                                        <td class="label-1"><s:property value="%{REQDT}"/></td>
                                                                    <td class="label-1"><s:property value="%{REQSUNO}"/></td>
                                                                    <td class="label-1"><s:property value="%{REQTYP}"/></td>
                                                                    <td class="label-1"><s:property value="%{REQTXT}"/></td>
                                                                    <td class="label-1"><s:property value="%{DLVPLC}"/></td>
                                                                    <td class="label-1"><s:property value="%{REQSTS}"/></td>
                                                                    <td class="label-1"><s:property value="%{PAYLOCT}"/></td>
                                                                    </tr>
                                                                </s:iterator>                                                                
                                                            </table>
                                                        </div>

                                                    </td></tr>

                                            </table>
                                        </td></tr>
                                </table>
                            </td></tr>
                    </table>
                </td></tr>
        </table>
    </form>
</body>

</html>

