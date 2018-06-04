<%--
    Document   : SearchInvoiceDetail
    Created on : Dec 28, 2011, 3:49:07 PM
    Author     : Vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Shahi Exports Pvt Ltd</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:head />
        <sx:head />
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/stylishbuttons.css" type="text/css" />
        <link type="text/css" href="css/ui-lightness/jquery-ui-1.7.3.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.7.3.custom.min.js"></script>
        <style type="text/css">
            th {
                font-size:9pt;
                text-align: left;
                font-weight:bold;
                color:#0066aa;
                background-image:url('image/table-gradient.jpg');
            }
            tbody {
                height: 0px;
                overflow-y: auto;
                overflow-x: hidden;
            }
        </style>
        <script type="text/javascript">
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
            function currentDate(){
                var mydate=new Date()

                var year=mydate.getYear()

                if (year < 1000)
                    year+=1900

                var day=mydate.getDay()
                var month=mydate.getMonth()+1

                if (month<10)
                    month="0"+month

                var daym=mydate.getDate()
                if (daym<10)
                    daym="0"+daym

                var assigndate=month+"/"+daym+"/"+year;
                document.getElementById('searchfrom').value=assigndate;
            }
            function submitaction(){
                aa=document.getElementById("searchcode").value;
                bb=document.getElementById("searchinv").value;
                if(aa=="" && aa.length==0)
                {
                  alert("Supplier Code cann't be blanked");
                  document.getElementById("searchcode").focus();
                  return false;
                }
                if(bb=="" && bb.length==0)
                {
                  alert("Invoice No cann't be blanked");
                  document.getElementById("searchinv").focus();
                  return false;
                }
                document.getElementById('prepage').style.visibility='';
                document.frm.action="SearchInvoiceDetail.action";
                document.frm.submit();
            }
            $(function() {
                $( "#searchfrom" ).datepicker();
            });
        </script>
    </head>
    <s:if test='%{searchfrom!=null}'>
        <body class="body1" onLoad="waitPreloadPage();">
    </s:if>
    <s:else>
        <body class="body1" onLoad="currentDate();waitPreloadPage();">
    </s:else>

        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <input type="hidden" name="aausrid" value="<%=request.getParameter("aausrid")%>"/>
            <table border=0 cellpadding="0" width="100%" cellspacing="0"><tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#60C8F2">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:12.0pt; font-weight:bold; font-family:Garamond; color:#2633A8;">
                                    Supplier Invoice Detail (New)</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="100%">
                        <table align="left" style="width:100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td style="height: 35px">
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                            <td class="label-1" width="25%">Divi : <s:select name="DIVI" id="DIVI" list="# {'100':'SEPL-FBAD','200':'SEPL-BLR','210':'SEPL-TRP','300':'SAPL-FBAD','400':'SAPL-BLR','520':'AHP','530':'SHA','500':'SHMG','540':'AHPG'}" cssStyle="text-transform:uppercase;width:120pt" theme="simple"/></td>
                                            <td class="label-1" width="25%">Supplier Code : <s:textfield name="searchcode" id="searchcode" value="%{searchcode}" cssStyle="text-transform:uppercase;width:120pt" theme="simple" maxLength="15"/></td>
                                            <td class="label-1" width="25%">Invoice No : <s:textfield name="searchinv" id="searchinv" value="%{searchinv}" cssStyle="text-transform:uppercase;width:120pt" theme="simple" maxLength="25"/></td>
                                            <td class="label-1" width="25%"  style="height: 35px">Invoice Date : <s:textfield name="searchfrom" id="searchfrom" value="%{searchfrom}" style="width:120pt;" readonly="true" theme="simple"/></td>
                                            <td class="sexybutton">
                                                <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="submitaction();">
                                                    <span><span><img src="css/images/icons/silk/search.png"  alt="" style="border-width: 0pt;"/>Search</span></span>
                                                        </s:a>
                                            </td>
                                            <td class="sexybutton">
                                                <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="self.close();">
                                                    <span><span><img src="css/images/icons/silk/exit.png"  alt="" style="border-width: 0pt;"/>Close</span></span>
                                                </s:a></td>
                                        </tr></table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="width:100%;">
                        <table style="width: 100%" bgcolor="DarkSalmon" cellspacing="1" cellpadding="1" width="100%">
                                <thead>
                                    <tr>
                                    	<th>Warehouse</th>
                                        <th>Supplier Code</th>
                                        <th>Invoice No</th>
                                        <th>Invoice Date</th>
                                        <th>Invoice Amount</th>
                                        <th>Curr</th>
                                        <th>Status</th>
                                        <th>User</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                  
                                    <s:iterator value="showList" status="listid">
                                        <tr bgcolor="F5F5DC">
                                            <td><s:property value="APWHLO"/></td>
                                            <td><s:property value="APSUNO"/></td>
                                            <td><s:property value="APSINO"/></td>
                                            <td><s:property value="APIVDT"/></td>
                                            <td><s:property value="APCUAM"/></td>
                                            <td><s:property value="APCUCD"/></td>
                                            <td><s:property value="APPYST"/></td>
                                            <td><s:property value="APUSID"/></td>
                                                <s:url id="deleteURL" action="deleteSearchInvoiceDetail.action">
                                                    <s:param name="SUNO" value="%{APSUNO}"></s:param>
                                                    <s:param name="INVNO" value="%{APSINO}"></s:param>
                                                    <s:param name="INVDATE" value="%{APIVDT}"></s:param>
                                                    <s:param name="DIVI" value="%{APDIVI}"></s:param>
                                                    <s:param name="WHLO" value="%{APWHLO}"></s:param>
                                                    <s:param name="USID" value="%{APUSID}"></s:param>
                                                </s:url>
                                                <s:if test='%{APPYST=="00" && APCONO=="111"}'>
                                                <td align="center"  class="sexybutton"  style="width:60px">
                                                    <s:a href="%{deleteURL}" title="Want to delete? Click Button" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue">
                                                        <span><span><span class="cancel">Delete</span></span></span>
                                                    </s:a>
                                                </td>
                                                </s:if>
                                                <s:else>
                                                    <td align="center"  class="sexybutton disabled" disabled="disabled" style="width:100px">
                                                    <s:a href="#" title="You can't delete." cssStyle="text-decoration: none;cursor:hand;color:SlateBlue">
                                                        <span><span><img src="css/images/icons/silk/deletethick.png"  alt=""  style="border-width: 0pt;"/>Delete</span></span>
                                                    </s:a>
                                                </td>
                                                </s:else>
                                        </tr>
                                        <s:if test='%{APCONO=="111"}'>
                                            <s:if test='%{APPYST=="05"}'>
                                            <tr bgcolor="F5F5DC">
                                                <td colspan="8" style="font-size: 15px;text-align: center;color:rosybrown;font-weight: bold;">Grn Attached..</td>
                                            </tr>
                                            </s:if>
                                            <s:if test='%{APPYST=="90"}'>
                                            <tr bgcolor="F5F5DC">
                                                <td colspan="8" style="font-size: 15px;text-align: center;color:rosybrown;font-weight: bold;">Invoice Booked..</td>
                                            </tr>
                                            </s:if>
                                        </s:if>
                                            <s:elseif test='%{APCONO=="999"}'>
                                            <tr bgcolor="F5F5DC">
                                                <td colspan="8" style="font-size: 15px;text-align: center;color:rosybrown;font-weight: bold;">Record Removed..</td>
                                            </tr>
                                         </s:elseif>
                                    </s:iterator>
                                        <s:if test='%{showList.size()==0}'>
                                            <tr bgcolor="F5F5DC">
                                            <td colspan="8" style="font-size: 15px;text-align: center;color:rosybrown;font-weight: bold;">Record Not Found..</td>
                                            </tr>
                                        </s:if>
                             <tbody>
                            </table>
                    </td>
                </tr>
                <tr>
                    <td style="color: red;text-align: center;"><s:actionerror/></td>
                </tr>
            </table>
                                            
        </form>
    </body>
</html>
