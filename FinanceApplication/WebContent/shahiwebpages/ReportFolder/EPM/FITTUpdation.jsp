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
                $( "#FSFTDT" ).datepicker({
                    dateFormat: 'dd/mm/yy',
                    closeText: 'X'
                });  
               /*  $( "#DATETO" ).datepicker({
                    dateFormat: 'dd/mm/yy',
                    closeText: 'X'
                });  */
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
            function query(){                
            	    var fittno=document.getElementById("FSFTNO").value;
            	    if(fittno==''){
            	    	alert('Please Enter FSFTNO');
            	    	return false;
            	    }
                    document.balconf.action="FITTUpdationAction";
                    document.balconf.submit();
                    document.getElementById('prepage').style.visibility='';
            }   
            function saverec()
            {           
            	var FSCURT=document.getElementById("FSCURT").value;
            	var FSFTDT=document.getElementById("FSFTDT").value;
            	if(confirm('Do you want to update')){
            		if(FSCURT=='' && FSFTDT==''){
            			alert('Please Enter Rate or  Date');
            			return false;
            		}
                    document.balconf.action="updateFITTUpdationAction";
                    document.balconf.submit();
                    document.getElementById("btnsave").disabled=true;
                    document.getElementById('prepage').style.visibility='';
            	}
            }   
            
           
             </script>

    <body bgcolor="Gray" onLoad="waitPreloadPage();">
    	<DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv">
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form name="balconf" method="POST" action="">
            <input type="hidden" value="<%=request.getParameter("aausrid")%>" name="aausrid"/>
            <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#60C8F2">
                <tr>
                    <td align="center" style="width:90.0%; font-size:12.0pt; font-weight:bold; font-family:Garamond; color:#2633A8;">
                        FITT Updation 
                    </td>
                </tr>
            </table>
            <table style="width: 100%;" border="0">
                <tr>
                    <td>
                        <table width="100%" border="0">
                            <tr>                                
                                <td class="label-1" style="width: 80px">FITT No&nbsp;</td>
                                <td style="width:120px;">
                                    <s:textfield name="searchBean.FSFTNO" cssClass="txtField" id="FSFTNO"  cssStyle="text-transform:uppercase;width:100%;" theme="simple" />
                                 </td>
                                <td class="label-1" style="width: 80px">FITT Rate</td>
                               	<td>
                                	<s:textfield name="searchBean.FSCURT"  cssClass="txtField" id="FSCURT" style="width:90px;" theme="simple"/>
                                </td>
                                <td class="label-1" style="width: 80px">Date</td>
                               	<td>
                                	<s:textfield name="searchBean.FSFTDT"  cssClass="txtField" id="FSFTDT" style="width:90px;" theme="simple"/>
                                </td>
                                <td class="label-1" colspan="5" style="text-align:right;">
                                    <input type="button" class="submitbutton" value="Query" onclick="query()" style="width:75px;"/>&nbsp;&nbsp;
                                    <input type="reset" class="submitbutton" onclick="window.location.href='clearFITTUpdationAction.action'" value="Clear" style="width:55px"/>&nbsp;&nbsp;	
                                    <input type="Button" id="btnsave" class="submitbutton" value="Save" onclick="saverec();" style="width:75px;"/>			    
                                   <input type="Button" class="submitbutton" value="Exit" onclick="self.close();" style="width:50px;"/></td>
                            </tr>
                        </table>
                     </td>
           		</tr>
           		<tr>
                    <td>
                        <div class="div1" style="width:100.0%; overflow:auto; height:450.0px;border-style: solid;border-width: 1px;border-color: gray;">
                            <table id="tablea"  cellspacing="1" cellpadding="0" style="width:100%;">
                                <thead>
                                    <tr class="tr1">
                                    	<th align="left">Customer No</th> 
                                    	<th align="left">Invoice No</th> 
                                    	<th align="left">Excise No</th> 
                                    	<th align="left">Doc No</th> 
                                    	<th align="left">FSFTNO</th> 
                                    	<th align="right">FSFT Date</th> 
                                    	<th align="right">Rate</th>  
                                    	<!-- <th align="left" style="width: 80px">
                                            <input type="checkbox" onclick="CheckAllDelete()" name="dchk" value="Y"/>&nbsp;Upload
                                        </th>   -->                               	
                                   	</tr>
                           		</thead>
                           		
                                <tbody>
                                	<s:iterator value="list" status="status">
	                                <s:hidden name="list[%{#status.index}].FSCONO" value="%{FSCONO}" id="FSCONO%{#status.index}"/>
	                                <s:hidden name="list[%{#status.index}].FSDIVI" value="%{FSDIVI}" id="FSDIVI%{#status.index}"/>
	                                <s:hidden name="list[%{#status.index}].FSIVNO" value="%{FSIVNO}" id="FSIVNO%{#status.index}"/>
	                                <s:hidden name="list[%{#status.index}].FSYEA4" value="%{FSYEA4}" id="FSYEA4%{#status.index}"/>
	                                <s:hidden name="list[%{#status.index}].FSRENP" value="%{FSRENP}" id="FSRENP%{#status.index}"/>
                               		<tr>	
                                			 <td align="left"><s:property value="FSPYNO"/></td> 
                                			 <td align="left"><s:property value="FSIVNO"/></td> 
                                			 <td align="left"><s:property value="FSEXIV"/></td> 
                                			 <td align="left"><s:property value="FSRENP"/></td> 
                                			 <td align="left"><s:property value="FSFTNO"/></td>
                                			 <td align="right"><s:property value="FSFTDT"/></td> 
                                			 <td align="right"><s:property value="FSCURT"/></td> 
                                		</tr>
                                	</s:iterator>
                               	</tbody>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                   <!--  <td class="label-1">
                        <font color="red">Don't Upload More than 100 records.</font>
                    </td>   -->                  
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