<%-- 
    Document   : DataTex Purchase Order
    Created on : May 09, 2014, 03:34:26 PM
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
                $( "#DATEFROM" ).datepicker({
                    dateFormat: 'dd/mm/yy',
                    closeText: 'X'
                });  
                $( "#DATETO" ).datepicker({
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
                            {
                                e.checked=true;
                            }
                        }
                        else
                        {
                            e=a[i];
                            if (!e.disabled )
                            {
                                e.checked=false;
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
                        }
                    }
                    else
                    {
                        if (!a.disabled )
                        {
                            a.checked=false;
                        }
                    }
                }
            }
            function query()
            {                
                    document.balconf.action="DataTexPurchaseGstAction";
                    document.balconf.submit();
                    document.getElementById('prepage').style.visibility='';
            }   
            function saverec()
            {           
            	if(confirm('Do you want to upload')){
                    document.balconf.action="saveDataTexPurchaseGstAction";
                    document.balconf.submit();
                    document.getElementById("btnsave").disabled=true;
                    document.getElementById('prepage').style.visibility='';
            	}
            }   
            function enabledchkbx(a,b){
                id="CHKTDS_id"+a;
                if(b.checked){
                    document.getElementById(id).disabled=false;
                }
                else{
                    document.getElementById(id).disabled=true;   
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
                        DataTex Purchase
                    </td>
                </tr>
            </table>
            <table style="width: 100%;" border="0">
                <tr>
                    <td>
                        <table width="100%" border="0">
                            <tr>                                
                                <td class="label-1" style="width: 80px">Facility&nbsp;</td>
                                <td style="width:120px;">
                                    <s:if test="%{detaillist.size()>0}">
                                        <s:textfield name="DIVI" cssClass="txtField" id="DIVI"  cssStyle="text-transform:uppercase;width:100%;" theme="simple" readonly="true"/>
                                    </s:if>
                                    <s:else>
                                        <s:select name="DIVI" cssClass="txtField" id="DIVI" list="#{'101':'Shimoga','102':'SFL'}" cssStyle="text-transform:uppercase;width:100%" theme="simple"/>
                                    </s:else>
                                </td>
                                <td class="label-1" style="width: 80px">Date From&nbsp;</td>
                               	<td>
                                	<s:textfield name="DATEFROM" readonly="true" cssClass="txtField" id="DATEFROM" style="width:90px;" theme="simple"/>
                                </td>
                                <td class="label-1" style="width: 80px">To&nbsp;</td>
                               	<td>
                                	<s:textfield name="DATETO" readonly="true" cssClass="txtField" id="DATETO" style="width:90px;" theme="simple"/>
                                </td>
                                <td class="label-1" colspan="5" style="text-align:right;">
                                    <input type="button" class="submitbutton" value="Query" onclick="query()" style="width:75px;"/>&nbsp;&nbsp;
                                    <input type="reset" class="submitbutton" onclick="window.location.href='makesessionDataTexPurchaseGstAction.action'" value="Clear" style="width:55px"/>&nbsp;&nbsp;				    
                                    <s:if test="%{disble=='true'}">
                                        <input type="Button" class="submitbutton" value="Save" onclick="saverec()();" style="width:75px;" disabled="true"/>&nbsp;&nbsp;
                                    </s:if>
                                    <s:else>
                                        <input type="Button" id="btnsave" class="submitbutton" value="Save" onclick="saverec()();" style="width:75px;"/>
                                    </s:else>
                                   <input type="Button" class="submitbutton" value="Exit" onclick="self.close();" style="width:50px;"/></td>
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
                                    	<th align="left">Divi</th> 
                                    	<th align="left">Acc Date</th> 
                                    	<th align="left">Customer</th> 
                                    	<th align="left">Invoice No</th> 
                                    	<th align="left">Invoice Date</th> 
                                    	<th align="right">Invoice Amt</th> 
                                    	<th align="right">Tax Amt</th>  
                                    	<th align="right">Net Amt</th>
                                    	<th align="left">Curr</th> 
                                    	<th align="right">Qty</th> 
                                    	<th align="left">Narration</th> 
                                    	<th align="left">TDS</th>     
                                    	<th align="left" style="width: 80px">
                                            <input type="checkbox" onclick="CheckAllDelete()" name="dchk" value="Y"/>&nbsp;Upload
                                        </th>                                 	
                                   	</tr>
                           		</thead>
                           		</thead>
                                <tbody>
                                	<s:iterator value="detaillist" status="detailstatus">
                                		<s:if test="%{DCHK=='OK'}">
                                			<tr bgcolor="#FAEBD7" title="Debit Note Pending">
                                		</s:if>
                                		<s:else>
                                			<tr bgcolor="#f2f2f2">
                                		</s:else>
                                			<td align="left"><s:property value="DIVISIONCODE"/></td> 
	                                    	<td align="left"><s:property value="ACCOUNTINGDATE"/></td> 
	                                    	<td align="left"><s:property value="CUSTOMERSUPPLIERCODE"/></td> 
	                                    	<td align="left"><s:property value="INVOICENO"/></td> 
	                                    	<td align="left"><s:property value="INVOICEDATE"/></td> 
	                                    	<td align="right"><s:property value="INVAMT"/></td> 
	                                    	<td align="right"><s:property value="GLAMT"/></td>  
	                                    	<td align="right"><s:property value="NETAMT"/></td>
	                                    	<td align="left"><s:property value="CURRENCYCODE"/></td> 
	                                    	<td align="right"><s:property value="QTY"/></td> 
	                                    	<td align="left"><s:property value="NARRATION"/></td>  
	                                    	<td> 
	                                    			<select name="CHKTDS" id="CHKTDS_id<s:property value='%{#detailstatus.index}'/>" disabled="disabled">
	                                    				<s:if test="%{TCHK=='OK'}">
	                                    					<option value="Y">Yes</option>
	                                    					<option value="N">No</option>
	                                    				</s:if>
	                                    				<s:else>
		                                    				<option value="N">No</option>
	                                    				</s:else>
	                                    			</select>	                                    		
	                                    	</td>
	                                    	<td>
	                                    		<%--s:if test="%{DCHK=='OK'}">
	                                    			<s:checkbox name="chkdel"  id="chkdel_id%{#detailstatus.index}" theme="simple" fieldValue="%{CUSTOMERSUPPLIERCODE+'$$'+INVOICENO+'$$'+FINYEAR}" disabled="true"/>
	                                    		</s:if>
	                                    		<s:else--%>
                                                	<s:checkbox name="chkdel"  id="chkdel_id%{#detailstatus.index}" theme="simple" fieldValue="%{CUSTOMERSUPPLIERCODE+'$$'+INVOICENO+'$$'+FINYEAR}" onclick="enabledchkbx('%{#detailstatus.index}',this)"/>
                                                <%--/s:else--%>                                                
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