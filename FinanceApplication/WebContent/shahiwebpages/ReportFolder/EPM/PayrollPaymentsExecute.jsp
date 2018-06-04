<%-- 
    Document   : PayrollPaymentsExecute
    Created on : Oct 01, 2012, 3:31:26 PM
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
            $(function() {
                $( "#CKDTPRF" ).datepicker({
                    dateFormat: 'dd/mm/yy',
                    closeText: 'X'
                });                
            });
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
            function numbonly_chk(a,fname)
            {
                if (!a.value=="" &&!a.value.match(/^\d+$/))
                {
                    alert('Invalid Input for '+fname+' Only Numbers Allowed');
                    a.value="";
                    a.focus();
                    return false;
                }
            }
            function validatequery(){
                TMID=document.getElementById("TMPRID");
                if(TMID.value==''){
                    alert('Batch Id cannot be blanked');
                    TMID.focus();
                    return false;
                }
                return true;
            }
            function query()
            {
                if(validatequery()==true){
                    document.getElementById('prepage').style.visibility='';
                    document.balconf.action="PayrollPaymentsExecuteAction";
                    document.balconf.submit();
                }
            }           
            
            function enabledtextbox(a)
            {
                id='chkdel_id'+a;
                id1='CHEQUE_NEW_ID'+a
                id2='REMARKS_NEW_ID'+a;
                id3='CHK_DATE_ID'+a;
                if(document.getElementById(id).checked==true){
                    document.getElementById(id1).disabled=false;
                    document.getElementById(id2).disabled=false;
                    document.getElementById(id3).disabled=false;
                }
                else{
                    document.getElementById(id1).disabled=true;
                    document.getElementById(id2).disabled=true;
                    document.getElementById(id3).disabled=true;
                }

            }
            function updateChkno(){                
                chkrangefrom=document.getElementById("CKCHKNF");
                chkrangeto=document.getElementById("CKCHKNT");
                chkdt=document.getElementById("CKDTPRF");
                chkbx = document.balconf.CHEQUE_NEW;
                chkbxdt = document.balconf.CHK_DATE;
                if(chkrangefrom.value=='' || chkrangeto.value==''){
                    alert('Cheque Range cannot be empty...');
                    chkrangefrom.focus();
                    return false;
                }
                if(eval(chkrangefrom.value)>eval(chkrangeto.value)){
                    alert('Invalid Cheque series...');
                    chkrangefrom.focus();
                    return false;
                }
                if(chkdt.value==''){
                    alert('Cheque Date cannot be empty...');
                    chkdt.focus();
                    return false;
                }
                i=0;
                a=chkrangefrom.value;
                b=chkrangeto.value; 
                if(chkbx.length>1){
                    while(a<=b){
                        if(!chkbx[i].disabled){
                            chkbx[i].value=a;
                            chkbxdt[i].value=document.getElementById("CKDTPRF").value;
                            len=chkbx[i].value.length;
                            for(j=0;j<eval(15-eval(len));j++)
                            {
                                chkbx[i].value = '0'+chkbx[i].value;
                            }
                            a++;
                        }                    
                        i++;
                    }
                }
                else{
                    chkbx.value=a;
                    chkbxdt.value=document.getElementById("CKDTPRF").value;
                    len=chkbx.value.length;
                    for(j=0;j<eval(15-eval(len));j++)
                    {
                        chkbx.value = '0'+chkbx.value;
                    }
                }
            }
            function fillzerovalue(a)
            {				
                len = (document.getElementById(a).value).length;
                for(i=0;i<eval(15-eval(len));i++)
                {
                    document.getElementById(a).value = '0'+document.getElementById(a).value;
                }
            }
            function CheckAllDelete()
            {
                a=document.balconf.chkdel;
                b=document.balconf.CHEQUE_NEW;
                c=document.balconf.REMARKS_NEW;
                d=document.balconf.CHK_DATE;
                if(a.length>0)
                {
                    for (var i=0;i<a.length;i++)
                    {
                        if(document.balconf.dchk.checked)
                        {
                            e=a[i];
                            f=b[i];
                            g=c[i];
                            h=d[i];
                            if(!e.disabled )
                            {
                                e.checked=true;
                                f.disabled=false;
                                g.disabled=false;
                                h.disabled=false;
                            }
                        }
                        else
                        {
                            e=a[i];
                            f=b[i];
                            g=c[i];
                            h=d[i];
                            if (!e.disabled )
                            {
                                e.checked=false;
                                f.disabled=true;
                                g.disabled=true;
                                h.disabled=true;
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
                            c.disabled=false;
                            d.disabled=false;
                        }
                    }
                    else
                    {
                        if (!a.disabled )
                        {
                            a.checked=false;
                            b.disable=true;
                            c.disabled=true;
                            d.disabled=true;
                        }
                    }
                }
            }
            function Save(){
                if(confirm('Do you want to update Cheque No.')){
                    document.getElementById('prepage').style.visibility='';
                    document.balconf.action="savePayrollPaymentsExecuteAction";
                    document.balconf.submit();
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
                        Payroll Batch Process
                    </td>
                </tr>
            </table>
            <table style="width: 100%;" border="0">
                <tr>
                    <td>
                        <table width="100%">
                            <tr>      
                                <td class="label-1" style="width:70px">Facility:&nbsp;</td>
                                <td style="width:100px">
                                    <s:select name="DIVI" cssClass="txtField" id="DIVI" list="#{'100':'SEPL','300':'SAPL'}" cssStyle="text-transform:uppercase;width:110px" theme="simple"/>
                                </td>
                                <td align="left" class="label-1">Year:&nbsp;</td>
                                <td>
                                    <s:select name="YEAR" cssClass="txtField" id="YEAR" list="#{'2011':'2011','2012':'2012','2013':'2013','2014':'2014','2015':'2015','2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020'}" cssStyle="text-transform:uppercase;" theme="simple"/>
                                </td>
                                <td align="left" class="label-1">Bank Id:</td>
                                <td>
                                    <s:select cssClass="txtField" onkeypress="return tabE(this,event)"  id="CKBKID"  name="CKBKID" theme="simple" list="bankcodelist" listKey="CODE" listValue="'('+CODE+') '+DESC" cssStyle="width:320px;"/>
                                </td>
                                <td align="left" class="label-1">Batch No:</td>
                                <td>                                    
                                    <s:textfield cssClass="txtField" onkeypress="return tabE(this,event)"  id="TMPRID"  name="TMPRID" theme="simple"  cssStyle="width:80px;"/>                                   
                                </td>                                
                                <td class="label-1">
                                    <input type="button" class="submitbutton" value="Query" onclick="query()" style="width:75px;"/>
                                </td>                                                                
                                <td>                                    
                                    <s:if test="%{detaillist!=null}">
                                        <input type="Button" class="submitbutton" value="Save" onclick="Save();" style="width:55px;"/>
                                    </s:if>
                                    <s:else>
                                        <input type="Button" class="submitbutton" value="Save" onclick="Save();" style="width:55px;" disabled="true"/>
                                    </s:else>
                                </td>
                                <td class="label-1"><input type="Button" class="submitbutton" value="Exit" onclick="self.close();" style="width:50px;"/></td>
                            </tr>                            
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="div1" style="width:100.0%; overflow:auto; height:490.0px;border-style: solid;border-width: 1px;border-color: gray;">
                            <table id="tablea"  cellspacing="1" cellpadding="0" style="width:100%;background-color: gray">
                                <thead>
                                    <tr class="tr1">
                                        <th align="left" height="18px" style="width: 50px">Sr.No.</th>
                                        <th align="left" height="18px">Facility</th>                                        
                                        <th align="left">Year</th>
                                        <th align="left">Batch.No.</th>
                                        <th align="left">Bank.Code.</th>
                                        <th align="left">Cheque.No.</th>
                                        <th style="width:80px;text-align: right;">Cheque.Amt</th>                                        
                                        <th align="left">Vch Type</th>
                                        <th align="left">Vch.No.</th>
                                        <th align="left">Supplier Code</th>
                                        <th align="left">Supplier Name</th>
                                        <th align="left">Date</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="detaillist" status="detailstatus">
                                        <tr bgcolor="f2f2f2">
                                            <td> 
                                                <s:hidden name="EGAIT1" id="EGAIT1" value="%{EGAIT1}"/>
                                                <s:property value="%{#detailstatus.index+1}"/> 
                                            </td>
                                            <td> <s:property value="DIVI"/> </td>
                                            <td> <s:property value="YEAR"/> </td>
                                            <td><s:property value="BATCH_NO"/></td>
                                            <td><s:property value="EGAIT1"/></td>
                                            <td> <s:property value="CHQ_NO"/> </td>
                                            <td style="width:80px;text-align: right;"> <s:text name="product.cost"><s:param value="CHQ_AMT"/></s:text>&nbsp;&nbsp; </td>                                            
                                            <td> <s:property value="VSER"/> </td>
                                            <td> <s:property value="VONO"/> </td>
                                            <td> <s:property value="EMP_CODE"/> </td>
                                            <td><s:property value="ACC_DATA_DESC"/>
                                            <td><s:property value="CHQ_DATE"/>                                                
                                            </td>
                                            
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="font-size: 12px;font-weight: bold;">Total Amount: &nbsp;<s:text name="product.cost"><s:param value="TOTAL_CHQ"/></s:text></td>
                </tr>
                <tr>
                    <td class="label-1">
                        <font color="red">Don't Upload More than 100 records.</font>
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
