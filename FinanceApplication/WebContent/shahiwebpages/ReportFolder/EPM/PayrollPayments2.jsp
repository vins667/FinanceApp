<%-- 
    Document   : PayrollPayments
    Created on : Sep 24, 2012, 4:09:26 PM
    Author     : Vivek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
            function query()
            {
                if(validatequery()){
                    document.getElementById('prepage').style.visibility='';
                    document.balconf.action="PayrollPaymentsAction";
                    document.balconf.submit();
                }
            }        
	      
            function validatequery(){
                if(document.getElementById('TMLOCT').value=='')
                {
                    alert('Location cannot be empty..');
                    document.getElementById('TMLOCT').focus();
                    return false;
                }
                if(document.getElementById('TMPRID').value=='')
                {
                    alert('Pros ID cannot be empty..');
                    document.getElementById('TMPRID').focus();
                    return false;
                }
                return true;
            }   
            
            function enabledtextbox(a)
            {
                id='chkdel_id'+a;
                id1='CHEQUE_NEW_ID'+a
                id2='REMARKS_NEW_ID'+a;
                id3='CHK_DATE_ID'+a;
                id4='CHK_BK_CODE_ID'+a;
                if(document.getElementById(id).checked==true){
                    document.getElementById(id1).disabled=false;
                    document.getElementById(id2).disabled=false;
                    document.getElementById(id3).disabled=false;                    
                    document.getElementById(id4).disabled=false;    
                }
                else{
                    document.getElementById(id1).disabled=true;
                    document.getElementById(id2).disabled=true;
                    document.getElementById(id3).disabled=true;
                    document.getElementById(id3).disabled=true;    
                }

            }
            function validate(){
               // alert('aaaa')
                count = 0;
                chkbx = document.balconf.CHEQUE_NEW;
                chkbxdt = document.balconf.CHK_DATE;  
                chkbkcode = document.balconf.CHK_BK_CODE;
                if(chkbx.length>1){
                    for(i=0;i<chkbx.length;i++){
                        if(!chkbx[i].disabled){
                            count++;
                            if(chkbx[i].value==''){
                                alert('Cheque cannot be empty');
                                chkbx[i].focus();
                                return false;
                            }
                            if(eval(chkbx[i].value)==0){
                                alert('Cheque cannot be zero empty');
                                chkbx[i].focus();
                                return false;
                            }
                            if(chkbxdt[i].value==''){
                                alert('Cheque Date cannot be empty');
                                chkbxdt[i].focus();
                                return false;
                            }
                            if(chkbkcode[i].value==''){
                                alert('Bank Code cannot be empty');
                                chkbkcode[i].focus();
                                return false;
                            }
                        }	
                    }
                }
                else{
                    if(!chkbx.disabled){
                        count++;
                        if(chkbx.value==''){
                            alert('Cheque cannot be empty');
                            chkbx.focus();
                            return false;
                        }
                        if(eval(chkbx.value)==0){
                                alert('Cheque cannot be zero empty');
                                chkbx[i].focus();
                                return false;
                            }
                        if(chkbxdt.value==''){
                            alert('Cheque Date cannot be empty');
                            chkbxdt.focus();
                            return false;
                        }
                        if(chkbkcode.value==''){
                                alert('Bank Code cannot be empty');
                                chkbkcode.focus();
                                return false;
                        }
                    }	
                }
                //  alert(count)
                if(eval(count)==0){
                    alert('Cannot be choose any records..');
                    return false;
                }
                return true;
            }
            function updateChkno(){                  
                totamt=0;
                chkrangefrom=document.getElementById("CKCHKNF");
                chkrangeto=document.getElementById("CKCHKNT");
                chkdt=document.getElementById("CKDTPRF");
                chkbx = document.balconf.CHEQUE_NEW;
                chkbxdt = document.balconf.CHK_DATE;
                chkbkcode = document.balconf.CHK_BK_CODE;
                chkamtdt = document.balconf.CHK_AMT;
		cknodt = document.balconf.CKNO;
                ckldate = document.balconf.cldate;
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
                if(chkdt.value>ckldate.value){
                                alert('Cheque Date can not be after current date');
                                chkdt.focus();
                                return false;
                 }
                i=0;
                a=chkrangefrom.value;
                b=chkrangeto.value;
                newcount=0;
                count=0;     
                
                if(chkbx.length>1){
                    while(a<=b){
                        //alert(count)
                        if(!chkbx[i].disabled){
                            //alert(chkbx[i].value)
                            ++count;
				chkbxdt[i].value=document.getElementById("CKDTPRF").value;
				if(chkbx[i].value=='' || cknodt[i].value==chkbx[i].value){
                           	  chkbx[i].value=a;                              
                              chkbkcode[i].value=document.getElementById("CKBKID").value;
                              len=chkbx[i].value.length;
                              for(j=0;j<eval(15-eval(len));j++)
                              {
                                  chkbx[i].value = '0'+chkbx[i].value;
                              }
                              //alert(totamt)                              
				}
				totamt=eval(totamt)+eval(chkamtdt[i].value);
                            document.getElementById("refreshamt").value=totamt;
                            a++;
                        }
                        else{
                           // alert('aaa')
                            newcount++;
                        }                                                
                        i++;
                        //if(newcount==i){eval(a)=eval(b)+1;}
                    }                    
                }
                else{
                    if(!chkbx.disabled){
                        ++count;		
			   chkbxdt.value=document.getElementById("CKDTPRF").value;
			   if(chkbx.value==''|| cknodt.value==chkbx.value){
                          chkbx.value=a;                          
                          chkbkcode.value=document.getElementById("CKBKID").value;
                          len=chkbx.value.length;
                          for(j=0;j<eval(15-eval(len));j++)
                          {
                              chkbx.value = '0'+chkbx.value;
                          }                          
			  }
			  totamt=eval(totamt)+eval(chkamtdt.value);
                       document.getElementById("refreshamt").value=totamt;
                    }                    
                }                
              //  alert(count)
                if(eval(count)==0){
                    alert('Cannot be choose any records..');
                    return false;
                }                
                
            }
            function fillzerovalue(a)
            {		
                if (!document.getElementById(a).value=="" &&!document.getElementById(a).value.match(/^\d+$/))
                {
                    alert('Invalid Input for Cheque Number Only Numbers Allowed');
                    document.getElementById(a).value="";
                    document.getElementById(a).focus();
                    return false;
                }
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
                j=document.balconf.CHK_BK_CODE;
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
                                j[i].disabled=false;
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
                                j[i].disabled=true;
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
                            j.disabled=false;
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
                            j.disabled=true;
                        }
                    }
                }
            }
            function Save(){
          
                if(validate()==true){
                    if(confirm('Do you want to update Cheque No.')){
                        document.getElementById('prepage').style.visibility='';
                        document.balconf.action="savePayrollPaymentsAction";
                        document.balconf.submit();
                    }
                    else{
                        
                    }
                }
            }

        </script>
<%Date sysdate=new Date();
               SimpleDateFormat fd=new SimpleDateFormat("dd/MM/yyyy"); 
                String cldate=fd.format(sysdate); 
%>
    <body bgcolor="Gray" onLoad="waitPreloadPage();">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form name="balconf" method="POST" action="">
            <input type="hidden" value="<%=request.getParameter("aausrid")%>" name="aausrid"/>
             <input type="hidden" value="<%=cldate%>" name="cldate"/>
            <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#60C8F2">
                <tr>
                    <td align="center" style="width:90.0%; font-size:12.0pt; font-weight:bold; font-family:Garamond; color:#2633A8;">
                        Payroll Payment Upload (M4)
                    </td>
                </tr>
            </table>
            <table style="width: 100%;" border="0">
                <tr>
                    <td>
                        <table width="100%">
                            <tr>                                
                                <td class="label-1" style="width: 100px">Facility:&nbsp;</td>
                                <td style="width:100px;">
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="DIVI" cssClass="txtField" id="DIVI"  cssStyle="text-transform:uppercase;width:100%;" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select name="DIVI" cssClass="txtField" id="DIVI" list="#{'100':'SEPL','300':'SAPL','500':'SHMG'}" cssStyle="text-transform:uppercase;width:100%" theme="simple"/>
                                    </s:else>
                                </td>
                                <td class="label-1" style="text-align: right;">Company&nbsp;</td>
                                <td colspan="3">
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="TMLOCT" cssClass="txtField" id="TMLOCT"  cssStyle="text-transform:uppercase;width:350px" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select cssClass="txtField" onkeypress="return tabE(this,event)"  id="TMLOCT"  name="TMLOCT" theme="simple" list="complist" listKey="CODE" listValue="CODE+' - '+DESC" headerKey="ALL" headerValue="ALL" cssStyle="width:350px;"/>                                    
                                    </s:else>
                                </td>
                                <td class="label-1" style="text-align: right;">Loction.&nbsp;</td>
                                <td colspan="3">
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="LOCATION_CODE" cssClass="txtField" id="LOCATION_CODE"  cssStyle="text-transform:uppercase;width:100%" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select cssClass="txtField" onkeypress="return tabE(this,event)"  id="LOCATION_CODE"  name="LOCATION_CODE" theme="simple" list="LOC_LIST" listKey="CODE" listValue="DESC+' ('+CODE+') '" headerKey="ALL" headerValue="ALL" cssStyle="width:100%;"/>                                    
                                    </s:else>
                                </td>
                                <td class="label-1" style="text-align: right;">Dept.&nbsp;</td>
                                <td colspan="3">
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="TMDEPT" cssClass="txtField" id="TMDEPT"  cssStyle="text-transform:uppercase;width:100%" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select cssClass="txtField" onkeypress="return tabE(this,event)"  id="TMDEPT"  name="TMDEPT" theme="simple" list="deptlist" listKey="CODE" listValue="DESC+' ('+CODE+') '" headerKey="ALL" headerValue="ALL" cssStyle="width:100%;"/>                                    
                                    </s:else>
                                </td>                        
                            </tr>
                            <tr>
                                <td align="left" class="label-1">Emp&nbsp;Type:</td>
                                <td>
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="ETYPE" cssClass="txtField" id="ETYPE"  cssStyle="text-transform:uppercase;width:90px" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select name="ETYPE" cssClass="txtField" id="ETYPE" list="#{'ALL':'ALL','S':'Staff','NWK':'Worker'}" cssStyle="text-transform:uppercase;width:90px" theme="simple"/>
                                    </s:else>
                                </td>   
                                <td align="left" class="label-1" style="width: 100px">Vch&nbsp;Type:</td>
                                <td>
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="VTYPE" cssClass="txtField" id="VTYPE"  cssStyle="text-transform:uppercase;width:130px" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                    <s:select name="VTYPE" cssClass="txtField" id="VTYPE" list="#{'ALL':'ALL','FNF':'FULL AND FINAL','OT':'OVERTIME','SALOT':'SALARY+Wages+OT','SALARY':'Salary/Wage','OTINC':'OT INCENTIVE','BONUS':'BONUS'}" cssStyle="text-transform:uppercase;width:130px" theme="simple"/>
                                    </s:else>
                                </td>   
                                <td align="left" class="label-1" style="width: 100px">State :</td>
                                <td>
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="VSTATE" cssClass="txtField" id="VSTATE"  cssStyle="text-transform:uppercase;width:130px" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                    <s:select name="VSTATE" cssClass="txtField" id="VSTATE" list="#{'HR':'HARYANA','UP':'UP','DL':'DELHI','OR':'ORDISHA','TS':'HYDERABAD','MP':'MP','AP':'AP'}" cssStyle="text-transform:uppercase;width:130px" theme="simple"/>
                                    </s:else>
                                </td>  
                                <td align="left" class="label-1">Pros Id:</td>
                                <td colspan="2">  
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="TMPRID" cssClass="txtField" id="TMPRID"  cssStyle="text-transform:uppercase;width:80px" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                    <s:textfield cssClass="txtField" onkeypress="return tabE(this,event)"  id="TMPRID"  name="TMPRID" theme="simple"  cssStyle="width:80px;"/>                                   
                                    </s:else>
                                </td>
                                <td class="label-1">
                                    <input type="button" class="submitbutton" value="Query" onclick="query()" style="width:75px;"/>
                                </td>                                
                                <td class="label-1"><input type="reset" class="submitbutton" onclick="window.location.href='viewPayrollPaymentsAction.action'" value="Clear" style="width:55px"/></td>
                            </tr>
                            <tr>
                                <td align="left" class="label-1" style="width: 100px">Bank Id:</td>
                                <td colspan="3">
                                    <s:if test="%{disble=='true'}">
                                        <s:select cssClass="txtField" onkeypress="return tabE(this,event)"  id="CKBKID"  name="CKBKID" theme="simple" list="bankcodelist" listKey="CODE" listValue="'('+CODE+') '+DESC" cssStyle="width:400px;" disabled="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select cssClass="txtField" onkeypress="return tabE(this,event)"  id="CKBKID"  name="CKBKID" theme="simple" list="bankcodelist" listKey="CODE" listValue="'('+CODE+') '+DESC" cssStyle="width:400px;"/>
                                    </s:else>
                                </td>
                                <td class="label-1">
                                    Cheque&nbsp;No&nbsp;From:</td>
                                <td>
                                    <s:if test="%{disble=='true'}">
                                        <s:textfield name="CKCHKNF" cssClass="txtField" id="CKCHKNF" style="width:150px;" theme="simple" maxlength="15" disabled="true" onblur="numbonly_chk(this,'Check No From');"/>
                                    </s:if>
                                    <s:else>
                                        <s:textfield name="CKCHKNF" cssClass="txtField" id="CKCHKNF" style="width:150px;" theme="simple" maxlength="15" onblur="numbonly_chk(this,'Check No From');"/>
                                    </s:else>
                                </td>
                                <td class="label-1">
                                    To:</td>
                                <td>
                                    <s:if test="%{disble=='true'}">
                                        <s:textfield name="CKCHKNT" cssClass="txtField" id="CKCHKNT" cssStyle="width:90px;" theme="simple" maxlength="15" disabled="true" onblur="numbonly_chk(this,'Check No To');"/>
                                    </s:if>
                                    <s:else>
                                        <s:textfield name="CKCHKNT" cssClass="txtField" id="CKCHKNT" cssStyle="width:90px;" theme="simple" maxlength="15" onblur="numbonly_chk(this,'Check No To');"/>
                                    </s:else>
                                </td>
                                <td class="label-1">Cheque&nbsp;Date:</td>
                                <td colspan="2">
                                    <s:textfield name="CKDTPRF" readonly="true" cssClass="txtField" id="CKDTPRF" style="width:90px;" theme="simple"/>
                                </td>                                
                                <td class="label-1">
                                    <s:if test="%{disble=='true'}">
                                        <input type="Button" class="submitbutton" value="Chk Update" onclick="updateChkno();" style="width:75px" disabled="true"/>
                                    </s:if>
                                    <s:else>
                                        <input type="Button" class="submitbutton" value="Chk Update" onclick="updateChkno();" style="width:75px"/>
                                    </s:else>
                                </td>
                                <td class="label-1">
                                    <s:if test="%{disble=='true'}">
                                        <input type="Button" class="submitbutton" value="Save" onclick="Save();" style="width:75px;" disabled="true"/>
                                    </s:if>
                                    <s:else>
                                        <input type="Button" class="submitbutton" value="Save" onclick="Save();" style="width:75px;"/>
                                    </s:else>
                                </td>
                                <td class="label-1"><input type="Button" class="submitbutton" value="Exit" onclick="self.close();" style="width:50px;"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="div1" style="width:100.0%; overflow:auto; height:450.0px;border-style: solid;border-width: 1px;border-color: gray;">
                            <table id="tablea"  cellspacing="1" cellpadding="0" style="width:100%;background-color: gray">
                                <thead>
                                    <tr class="tr1">
                                        <th align="left">Sr No</th>   
                                        <th align="left" height="15px">Facility</th>  
                                        <th align="left">Bank Name</th>
                                        <th align="left">Bank&nbsp;Code</th>
                                        <th align="left">Cheque.No.</th>
                                        <th align="left">Remarks</th>
                                        <th align="left">Year</th>
                                        <th align="left">Vch Type</th>
                                        <th align="left">Vch.No.</th>
                                        <th align="left">Emp Code</th>
                                        <th align="left">Emp Name</th>
                                        <th align="left" style="width:80px;text-align: right;">Cheque Amt&nbsp;&nbsp;</th>
                                        <th align="left">Date</th>                                        
                                        <th align="left" style="width: 80px">
                                            <input type="checkbox" onclick="CheckAllDelete()" name="dchk" value="Y"/>
                                            &nbsp;Upload</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="detaillist" status="detailstatus">
                                        <tr bgcolor="f2f2f2">
                                            <td> <s:property value="%{#detailstatus.index+1}"/> </td>
                                            <td> <s:property value="CKDIVI"/> </td>
                                            <td><s:property value="CHBANKNM"/></td>
                                            <td>
                                                <s:select name="CHK_BK_CODE" id="CHK_BK_CODE_ID%{#detailstatus.index}" list="bankcodelist" listKey="CODE" listValue="'('+CODE+') '+DESC" value="%{CHK_BK_CODE}" theme="simple" disabled="true" cssStyle="width:100%;height:100%" cssClass="texts"/>                                                
                                            </td>
                                            <td style="width:150px;">
						      <s:hidden name="CKNO" id="CKNO_ID%{#detailstatus.index}" value="%{CKCHKN}"/>
                                                <s:textfield name="CHEQUE_NEW" id="CHEQUE_NEW_ID%{#detailstatus.index}" value="%{CKCHKN}" theme="simple" disabled="true" cssStyle="width:150px;height:100%" cssClass="texts" onblur="fillzerovalue('CHEQUE_NEW_ID%{#detailstatus.index}');"/>                                                
                                            </td>
                                            <td style="width:150px;">
                                                <s:textfield name="REMARKS_NEW" id="REMARKS_NEW_ID%{#detailstatus.index}" value="%{REMARKS}" theme="simple" disabled="true" cssStyle="width:150px;height:100%" cssClass="texts" maxLength="200"/>                                                
                                            </td>
                                            <td> <s:property value="CKYEA4"/> </td>
                                            <td> <s:property value="CKVSER"/> </td>
                                            <td> <s:property value="CKVONO"/> </td>
                                            <td> <s:property value="CKSPYN"/> </td>
                                            <td> <s:property value="CKSUNM"/> </td>
                                            <td style="width:80px;text-align: right;">
                                                <s:text name="product.cost"><s:param value="CKPAYAMT"/></s:text>&nbsp;&nbsp;
                                                <s:hidden name="CHK_AMT" id="CHK_AMT_ID%{#detailstatus.index}" value="%{CKPAYAMT}" theme="simple"/>
                                            </td>
                                            <td>
                                                <s:textfield name="CHK_DATE" id="CHK_DATE_ID%{#detailstatus.index}" value="%{CHK_DATE}" theme="simple" disabled="true" cssStyle="width:100%;height:100%" cssClass="texts" maxLength="200" readonly="true"/>                                                
                                            </td>                                            
                                            <td>
                                                <s:checkbox name="chkdel"  id="chkdel_id%{#detailstatus.index}" theme="simple" fieldValue="%{CKPAYI}" onclick="enabledtextbox('%{#detailstatus.index}');"/>                                                
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" style="font-size: 12px;font-weight: bold;">Total Amount: &nbsp;<s:text name="product.cost"><s:param value="CHQ_AMT"/></s:text></td>
                </tr>
                <tr>
                <td align="right" style="font-size: 12px;font-weight: bold;">Choose Amount: &nbsp;<input type="text" name="refreshamt" id="refreshamt" value="0" readonly="true" style="width:50px;text-align: right;"/></td>
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
                                <td height="1pt"  align="center" style="color:red;font-weight:bold;font-size: 15px;">
                                    <div style="height:10pt">
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
