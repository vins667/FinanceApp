<%-- 
    Document   : UTRUpdation
    Created on : Feb 20, 2012, 3:05:11 PM
    Author     : Vivek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css">
<!DOCTYPE HTML>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            td
            {
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 12pt;
                font-weight:bold;
            }
            INPUT {
                font-family: arial;
                font-size: 10pt;
                color: black;
            }

            .txtField {
                background-color: #FCF8F8;
                border: 1px solid #993333;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                color: #993333;
                font-size: 12px;
                width:60pt;
                font-weight: normal;
                text-transform:uppercase;
            }
            .txtdesc{
                background-color: #FCF8F8;
                border: 1px solid #993333;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                color: #993333;
                font-size: 12px;
                width:95pt;
                font-weight: normal;
                text-transform:uppercase;

            }
            .txtdesc1{
                background-color: #FCF8F8;
                border: 1px solid #993333;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                color: #993333;
                font-size: 12px;
                width:332pt;
                font-weight: normal;
                text-transform:uppercase;

            }
            .title
            {
                width:85pt;
                font-family: arial;
                font-size: 11pt;
                color: black;
                font-weight: bold;
                font-style:italic;
                border-style:groove;
            }
            th {
                font-size:11pt;
                font-weight:bold;
                color:#006699;
                background-image:url('image/glossyback.gif');
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
        <script language="javascript" type="text/javascript">
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
            function tabE(obj,e)
            {var e=(typeof event!='undefined')?window.event:e;// IE : Moz
                if(e.keyCode==13)
                {var ele = document.forms[0].elements;
                    for(var i=0;i<ele.length;i++)
                    {var q=(i==ele.length-1)?0:i+1;// if last element : if any other
                        if(obj==ele[i]){ele[q].focus();break}
                    }
                    return false;
                }
            } 
            function CheckAllDelete()
            {
                a=document.balconf.chkdel;
                if(a.length>0)
                {
                    for (var i=0;i<a.length;i++)
                    {
                        if(document.balconf.dchk.checked)
                        {
                            e=a[i];
                            if(!e.disabled )
                            {e.checked=true;  }
                        }
                        else
                        {
                            e=a[i];
                            if (!e.disabled )
                            {e.checked=false;  }
                        }
                    }
                }
                else
                {
                    if(document.balconf.dchk.checked)
                    {
                        if (!a.disabled )
                        {a.checked=true;  }
                    }
                    else
                    {
                        if (!a.disabled )
                        {a.checked=false;  }
                    }
                }
            }

            function query()
            {if (document.balconf.pyear.value=="")
                {alert("Invalid Year...!");
                    document.balconf.pyear.focus();
                    return false;
                }else
                {
                    document.balconf.action="UtrUpdationAction";
                    document.balconf.submit();
                }
            }

            function Save()
            {
                if (document.balconf.pyear.value=="")
                {
                    alert("Invalid Year...!");
                    document.balconf.pyear.focus();
                    return false;
                }
                else
                {
                    falg=0;
                    bssrno=document.balconf.chkdel;
                    if(bssrno.length>1){
                    for(i=0; i<bssrno.length; i++)
                        {
                            if(bssrno[i].checked==true)
                            {
                                falg=1;
                            }
                        }
                    }
                    else{
                        if(document.balconf.chkdel.checked==true){
                            falg=1;
                        }
                    }
                    if(falg==1)
                    {
                        document.getElementById('prepage').style.visibility='';
                        document.balconf.action="updatemiUtrUpdationAction";
                        document.balconf.submit();
                    }else{
                        alert("Select Record...!");
                        return false;
                    }

                }
            }
        </script>

    <body bgcolor="Gray" onLoad="waitPreloadPage();">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <script type="text/javascript" language="javascript" src="dlcalendar3.js"></script>
        <form name="balconf" method="POST" action="">
            <input type="hidden" value="<%=request.getParameter("aausrid")%>" name="aausrid"/>
            <table border="0"  cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td class="submitbutton3" height="25pt" style="text-align:center; font-size:medium">
                        UTR No.Updation Status (M4)
                    </td>
                </tr>
            </table>
            <table style="width: 100%;height: 100%;">
                <tr>
                    <td>
                        <table width="100%">
                            <tr>
                                 <td class="label-1">Divi:
                                    <s:select name="DIVI" cssClass="txtField" id="DIVI" list="#{'100':'SEPL','300':'SAPL'}" cssStyle="text-transform:uppercase;" theme="simple"/></td>
                                <td align="left">Fin Year
                                    <s:textfield cssClass="txtField" onkeypress="return tabE(this,event)" name="pyear" id="pyear" theme="simple"/>
                                </td>
                                <td align="left">Cheque No.
                                    <s:textfield cssClass="txtField" onkeypress="return tabE(this,event)"  id="BSUNO"  name="BSUNO" theme="simple"/>
                                </td>
                                <td>
                                    <input type="button" class="title" value="Query" onclick="query()" />
                                </td>

                                <td>
                                    <input type="Button" class="title" value="Save" onclick="Save();"/>
                                </td>
                                <td><input type="reset" class="title" onclick="window.location.href='UTRUpdation.jsp'" value="Clear"/></td>
                                <td><input type="Button" class="title" value="Exit" onclick="self.close();"/></td>

                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;height: 100%;">
                        <div class="div1" style="width:100%;overflow:auto ;height:385pt;">
                            <table cellpadding="1" border="0" style="margin-right:0pt;margin-top:0pt;margin-bottom:0pt; border-left:0pt;border-top:0pt;border-bottom:0pt;width:100%;background-color: #006699;" cellspacing="1" cellspacing="1">
                                <thead>
                                    <tr class="tr1" style="background-color: #006699;">
                                        <th align="left" height="15px">Facility </th>
                                        <th align="left">Cheque.No.</th>
                                        <th align="left">Year</th>
                                        <th align="left">Vch Type</th>
                                        <th align="left">Vch.No.</th>
                                        <th align="left" style="width: 80px">
                                            <input type="checkbox" onclick="CheckAllDelete()" name="dchk" value="Y"/>
                                            &nbsp;Lock</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="DETAIL_LIST" status="detailstatus">
                                        <tr bgcolor="f2f2f2">
                                            <td> <s:property value="DIVISION"/> </td>
                                            <td> <s:property value="CHQ_NO"/> </td>
                                            <td> <s:property value="YEAR"/> </td>
                                            <td> <s:property value="VSER"/> </td>
                                            <td> <s:property value="VONO"/> </td>
                                            <td> <s:checkbox name="chkdel" theme="simple" fieldValue="%{YEAR+'-'+VSER+':'+VONO+'%'+CHQ_NO+'$'+DIVISION}"/></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td>
                        <table border="0" align="center" width="100%" >

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
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
