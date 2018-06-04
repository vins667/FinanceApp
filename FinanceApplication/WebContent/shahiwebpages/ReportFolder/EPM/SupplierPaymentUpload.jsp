<%--
    Document   : SupplierPaymentUpload
    Created on : Apr 9, 2012, 11:41:23 AM
    Author     : Vivek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<link href="<s:url value="css/style.css"/>" rel="stylesheet" type="text/css">
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Shahi Exports Pvt Ltd</title>
        <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.7.3.custom.min.js"></script>
        <link type="text/css" href="css/ui-lightness/jquery-ui-1.7.3.custom.css" rel="stylesheet" />
        <style type="text/css">
            .txtField {
                background-color: #FCF8F8;
                border: 1px solid #993333;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                color: #993333;
                font-size: 12px;
                width:60px;
                font-weight: normal;
                text-transform:uppercase;
            }
            .texts{
                font-family: Arial, Sans-Serif;
                font-size: 12px;
                border: solid 1px #677788;

            }
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
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
                    border: solid #F8F8FF;
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
                b=document.balconf.CHEQUE_NEW;
                if(a.length>0)
                {
                    for (var i=0;i<a.length;i++)
                    {
                        if(document.balconf.dchk.checked)
                        {
                            e=a[i];
                            f=b[i];
                            if(!e.disabled )
                            {
                                e.checked=true;
                                f.disabled=false;
                            }
                        }
                        else
                        {
                            e=a[i];
                            f=b[i];
                            if (!e.disabled )
                            {
                                e.checked=false;
                                f.disabled=true;
                            }
                        }
                    }
                }
                else
                {
                    if(document.balconf.dchk.checked)
                    {
                        if (!a.disabled )
                        {
                            a.checked=true;
                            b.disable=false;
                        }
                    }
                    else
                    {
                        if (!a.disabled )
                        {
                            a.checked=false;
                            b.disable=true;
                        }
                    }
                }
            }

            function query()
            {
                if(validatequery()==true){
                    document.getElementById('prepage').style.visibility='';
                    document.balconf.action="SupplierPaymentUploadAction";
                    document.balconf.submit();
                }
            }
            function validatequery(){
                var res=0;
                var res1=0;
                if(document.getElementById('CKCHKNF').value.length>0){
                    if(document.getElementById('CKCHKNT').value.length==0){
                        alert('Please provide Cheque No Range To...');
                        document.getElementById('CKCHKNT').focus();
                        return false;
                    }
                    else{
                        res=1;
                    }
                }
                else if(document.getElementById('CKCHKNT').value.length>0){
                    if(document.getElementById('CKCHKNF').value.length==0){
                        alert('Please provide Cheque No Range From...');
                        document.getElementById('CKCHKNF').focus();
                        return false;
                    }
                    else{
                        res=1;
                    }
                }
                else{
                    if(document.getElementById('CKCHKNF').value.length==0 && document.getElementById('CKCHKNT').value.length==0){
                        res=1;
                    }
                }
                if(document.getElementById('CKDTPRF').value.length>0){
                    if(document.getElementById('CKDTPRT').value.length==0){
                        alert('Please provide Cheque Date Range To...');
                        document.getElementById('CKDTPRT').focus();
                        return false;
                    }
                    else{
                        res1=1;
                    }
                }
                else if(document.getElementById('CKDTPRT').value.length>0){
                    if(document.getElementById('CKDTPRF').value.length==0){
                        alert('Please provide Cheque date Range From...');
                        document.getElementById('CKDTPRF').focus();
                        return false;
                    }
                    else{
                        res1=1;
                    }
                }
                else{
                   if(document.getElementById('CKDTPRF').value.length==0 && document.getElementById('CKDTPRT').value.length==0){
                        res1=1;
                    }
                }
                if((document.getElementById('CKCHKNF').value.length==0 && document.getElementById('CKCHKNT').value.length==0)&& (document.getElementById('CKDTPRF').value.length==0 && document.getElementById('CKDTPRT').value.length==0))
                {
                    alert('Please provide Cheque date Range...');
                    document.getElementById('CKDTPRF').focus();
                    return false;
                }
                if(res==1 && res1==1){
                    return true;
                }
            }
            function Save()
            {
                    falg=0;
                    bssrno=document.balconf.chkdel;
                    bsremrks=document.balconf.REMARKS_NEW;
                    if(bssrno.length>1){
                        for(i=0; i<bssrno.length; i++)
                        {
                            if(bssrno[i].checked==true)
                            {
                                if(bsremrks[i].value==''){
                                    alert('Remarks cannot be empty?');
                                    bsremrks[i].focus();
                                    return false;
                                }
                                falg=1;
                            }
                        }
                    }
                    else{
                        if(document.balconf.chkdel.checked==true){
                            if(document.balconf.REMARKS_NEW.value==''){
                                    alert('Remarks cannot be empty?');
                                    document.balconf.REMARKS_NEW.focus();
                                    return false;
                                }
                            falg=1;
                        }
                    }
                    if(falg==1)
                    {
                        document.getElementById('prepage').style.visibility='';
                        document.balconf.action="saveSupplierPaymentUploadAction";
                        document.balconf.submit();
                    }else{
                        alert("Select Record...!");
                        return false;
                    }

            }
            $(function() {
                $( "#CKDTPRF" ).datepicker({
                    dateFormat: 'dd/mm/yy',
                    closeText: 'X'
                });
                $( "#CKDTPRT" ).datepicker({
                    dateFormat: 'dd/mm/yy',
                    closeText: 'X'
                });
            });
            function enabledtextbox(a)
            {
                id='chkdel_id'+a;
                id1='CHEQUE_NEW_ID'+a
                id2='REMARKS_NEW_ID'+a;
                if(document.getElementById(id).checked==true){
                    document.getElementById(id1).disabled=false;
                    document.getElementById(id2).disabled=false;
                }
                else{
                    document.getElementById(id1).disabled=true;
                    document.getElementById(id2).disabled=true;
                }

            }
			function fillzerovalue(a)
			{			
				if(eval(document.getElementById(a).value)==0){
					alert('Cheque Number cannot be zero..');
					document.getElementById(a).value='';
					document.getElementById(a).focus();
					return false;
				}	
				len = (document.getElementById(a).value).length;
				for(i=0;i<eval(15-eval(len));i++)
				{
					document.getElementById(a).value = '0'+document.getElementById(a).value;
				}
			}
        </script>

    <body bgcolor="Gray" onLoad="waitPreloadPage();">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form name="balconf" method="POST" action="">
            <input type="hidden" value="<%=request.getParameter("aausrid")%>" name="aausrid"/>
            <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#60C8F2">
                <tr>
                    <td align="center" style="width:90.0%; font-size:12.0pt; font-weight:bold; font-family:Garamond; color:#2633A8;">
                        Supplier Payment Upload
                    </td>
                </tr>
            </table>
            <table style="width: 100%;" border="0">
                <tr>
                    <td>
                        <table width="100%">
                            <tr>
                                <td class="label-1">Divi:
                                    <s:select name="DIVI" cssClass="txtField" id="DIVI" list="#{'100':'SEPL','300':'SAPL','500':'SHMG'}" cssStyle="text-transform:uppercase;" theme="simple"/></td>                  
                                <td align="left" class="label-1">Year:
                                    <s:select name="YEAR" cssClass="txtField" id="YEAR" list="#{'2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020'}" cssStyle="text-transform:uppercase;" theme="simple"/>
                                </td>
                                <td align="left" class="label-1">Bank Id:
                                    <s:select cssClass="txtField" onkeypress="return tabE(this,event)"  id="CKBKID"  name="CKBKID" theme="simple" list="bankcodelist" listKey="CODE" listValue="'('+CODE+') '+DESC" cssStyle="width:320px;"/>
                                </td>
                                <td class="label-1">
                                    Check Date From:
                                    <s:textfield name="CKDTPRF" readonly="true" cssClass="txtField" id="CKDTPRF" style="width:70pt;" theme="simple"/>
                                </td>
                                <td class="label-1">
                                    To:
                                    <s:textfield name="CKDTPRT" readonly="true" cssClass="txtField" id="CKDTPRT" cssStyle="width:70pt" theme="simple"/>
                                </td>
                                <td class="label-1">
                                    Check No From:
                                    <s:textfield name="CKCHKNF" cssClass="txtField" id="CKCHKNF" style="width:95pt;" theme="simple" maxlength="15"/>
                                </td>
                                <td class="label-1">
                                    To:
                                    <s:textfield name="CKCHKNT" cssClass="txtField" id="CKCHKNT" cssStyle="width:95pt" theme="simple" maxlength="15"/>
                                </td>
                                <td class="label-1">
                                    <input type="button" class="submitbutton" value="Query" onclick="query()" style="width:60px"/>
                                </td>
                                <td class="label-1">
                                    <input type="Button" class="submitbutton" value="Save" onclick="Save();" style="width:60px"/>
                                </td>
                                <td class="label-1"><input type="reset" class="submitbutton" onclick="window.location.href='viewpageSupplierPaymentUploadAction.action'" value="Clear" style="width:60px"/></td>
                                <td class="label-1"><input type="Button" class="submitbutton" value="Exit" onclick="self.close();" style="width:60px"/></td>

                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <div class="div1" style="width:100.0%; overflow:auto; height:490.0px;border-style: solid;border-width: 1px;border-color: gray;">
                            <table id="tablea"  cellspacing="1" cellpadding="0" style="width:100%;background-color: gray">
                                <thead>
                                    <tr class="tr1">
                                        <th align="left" height="15px">Facility</th>
                                        <th align="left">Cheque.No.</th>
                                        <th align="left">Cheque.No.New</th>
                                        <th align="left">Remarks</th>
                                        <th align="left">Year</th>
                                        <th align="left">Vch Type</th>
                                        <th align="left">Vch.No.</th>
                                        <th align="left">Supplier Code</th>
                                        <th align="left">Supplier Name</th>
                                        <th align="left">Date</th>
                                        <th align="left" style="width: 80px">
                                            <input type="checkbox" onclick="CheckAllDelete()" name="dchk" value="Y"/>
                                            &nbsp;Upload</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="detaillist" status="detailstatus">
                                        <tr bgcolor="f2f2f2">
                                            <td> <s:property value="CKDIVI"/> </td>
                                            <td> <s:property value="CKCHKN"/> </td>
                                            <td><s:if test='%{CKEXIT=="OK"}'>
                                                    <s:textfield name="CHEQUE_NEW" id="CHEQUE_NEW_ID%{#detailstatus.index}" value="%{CKCHKN}" theme="simple" disabled="true" cssStyle="width:100%;height:100%;font-family: Arial, Sans-Serif;" cssClass="txtField" onblur="fillzerovalue('CHEQUE_NEW_ID%{#detailstatus.index}');"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield name="CHEQUE_NEW" id="CHEQUE_NEW_ID%{#detailstatus.index}" value="%{CKCHKN}" theme="simple" disabled="true" cssStyle="width:100%;height:100%" cssClass="texts" onblur="fillzerovalue('CHEQUE_NEW_ID%{#detailstatus.index}');"/>
                                                </s:else>
                                            </td>
                                            <td style="width:200px"><s:if test='%{CKEXIT=="OK"}'>
                                                    <s:textfield name="REMARKS_NEW" id="REMARKS_NEW_ID%{#detailstatus.index}" value="%{REMARKS}" theme="simple" disabled="true" cssStyle="width:100%;height:100%;font-family: Arial, Sans-Serif;" cssClass="txtField" maxLength="200"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield name="REMARKS_NEW" id="REMARKS_NEW_ID%{#detailstatus.index}" value="%{REMARKS}" theme="simple" disabled="true" cssStyle="width:100%;height:100%" cssClass="texts" maxLength="200"/>
                                                </s:else>
                                            </td>
                                            <td> <s:property value="CKYEA4"/> </td>
                                            <td> <s:property value="CKVSER"/> </td>
                                            <td> <s:property value="CKVONO"/> </td>
                                            <td> <s:property value="CKSPYN"/> </td>
                                            <td> <s:property value="CKSUNM"/> </td>
                                            <td> <s:property value="CKDTPR"/> </td>
                                            <td>
                                                <s:if test='%{CKEXIT=="OK"}'>
                                                    <s:checkbox name="chkdel"  id="chkdel_id%{#detailstatus.index}" theme="simple" cssStyle="border:1px solid #F00;" disabled="true"/>
                                                    <font color="red">Uploaded</font>
                                                </s:if>
                                                <s:else>
                                                    <s:checkbox name="chkdel"  id="chkdel_id%{#detailstatus.index}" theme="simple" fieldValue="%{CKYEA4+'-'+CKVSER+':'+CKVONO+'%'+CKCHKN+'$'+CKDIVI+'@'+CKAIT1}" onclick="enabledtextbox('%{#detailstatus.index}');"/>
                                                </s:else>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="label-1">
                        <font color="red">Don't Upload More than 4 records.</font>
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
