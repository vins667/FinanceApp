<%-- 
    Document   : ScrapSaleInvoiceView
    Created on : Sep 22, 2016, 11:00:54 PM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<html> 
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('../../image/table-gradient.jpg');
            }
            tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }

            .toolTip {
                background-color: white;

                border: solid 1px;
                font-family: Arial;
                font-size: 12px;
                font-style: normal;
                font-variant: normal;
                font-weight: normal;
                left: 0;
                padding: 0px;
                position: absolute;
                text-align: left;
                top: 0;
                visibility: hidden;
                z-index: 2;
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
                    padding-top: 30px ;
        
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);                     
                  }
                tbody {
                    height: auto;
                }
                tfoot{
                    background:#3383bb;
                    font-weight:bold;
                }
                .tr2 {
                     position:absolute;
                     bottom:expression(this.offsetParent.scrollTop);
                  }
            </style>
        <![endif]-->
        <script language="javascript">
            
  


          
             function seachdata()
            {               
                document.frm.action="MeisLicenceSale.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
            function newform()
            {               
                document.frm.action="newentryMeisLicenceSale.action?";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             
             

            
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
        </script>

    </head>
 
    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    MEIS Licence Sale</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="3">
                                            <tr>
                                                <td class="label-1" style="width:300px">Facility &nbsp;&nbsp;&nbsp;&nbsp; <s:select name="WHO_S" id="WHO_S"  headerValue=""  headerKey=""  value="%{WHO}" list="%{whouselist}"  cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);"/>
                                                </td> 
                                                <td class="label-1" style="width:300px">Invoice &nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="INVOICE_S" id="INVOICE_S" value="%{INVOICE_S}" cssStyle="text-transform:uppercase;width:80pt" theme="simple" onKeyPress="tabE(this, event);" />
                                                </td> 
                                                <td class="label-1" style="width:300px">Buyer &nbsp;<s:textfield name="BUYER_S" id="BUYER_S" value="%{BUYER_S}" cssStyle="text-transform:uppercase;width:80pt" theme="simple"  onKeyPress="tabE(this, event);"/>
                                                </td>
                                                <td class="label-1" style="width:300px">InvDate From &nbsp;
                                                             <sx:datetimepicker name="SEARCH_DATE1" 
			                                   		id="SEARCH_DATE1"    value="currentdate"
			                                        displayFormat='dd/MM/yyyy'
			                                        cssClass="texts"/>
                                                </td>  
                                                 <td class="label-1" style="width:300px">InvDate To &nbsp;  <sx:datetimepicker name="SEARCH_DATE2" 
			                                   		id="SEARCH_DATE2"  value="currentdate"
                                                                          displayFormat="dd/MM/yyyy" 
                                                                             cssClass="texts"/>
                                                 </td>
                                              
                                               
                                        
                                                  

                                                <td align="right">  
                                                  <button  id="searchheadId" class="sexybutton" onclick="seachdata();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                                  <button  id="saveheadId"  class="sexybutton" onclick="newform();"><span><span><span class="add" id="addId" >New</span></span></span></button> 
                                                 <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                               </td>
                                                </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        <div style="width:100%" class="toolTip" id="toolTipDiv"></div>
                        <div align="center" style="width:100%;">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr   style="background-color: whitesmoke;height: 350pt;">
                                    <td>
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:350pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%">
                                                 
                                                   <tr class="div1" style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left">Facility</th>
                                                    <th style="font-size:8pt" align="left">Invoice No.</th>
                                                    <th style="font-size:8pt" align="left">Invoice Date</th>
                                                     <th style="font-size:8pt" align="leftt">Buyer</th>
                                                    <th style="font-size:8pt" align="leftt">Remarks</th>
                                                    <th style="font-size:8pt" align="left">TaxCode</th>
                                                    <th style="font-size:8pt" align="left">Tax Type</th>
                                                    <th style="font-size:8pt" align="left">Tax %</th>
                                                    <th style="font-size:8pt" align="left">Final Print</th>
                                                    <th style="font-size:8pt" align="right">Edit</th>
                                                    <th style="font-size:8pt" align="right">Print</th>
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                      
                                                      <s:set id="ctn" name="ctn" value="0"/>  
                                                      <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{FACILITY}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{EXCS_INV_NO}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{INV_DATE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUYER}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{REMARK}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{TAX_CODE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{TAX_TYPE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{TAX_PERCENT}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{FINAL_PRINT}" /></td>
                                                            
                                                            <td style="text-align: center;"><s:a href="editMeisLicenceSale.action?INVOICENO=%{EXCS_INV_NO}"><img src="../images/editicon.png" style="border-width:0px;"/></s:a></td>
                                                            
                                                            <td style="text-align: center;"><s:a href="printsMeisLicenceSale.action?INVOICENO=%{EXCS_INV_NO}"><img src="../images/pdf1.png" style="border-width:0px;"></s:a></td>  
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                                            </table>
                                        </div>  
                                      </td>
                                 </tr>
                                    </table> 
                                 </td>
                              </tr> 
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
                        </div>
                    </td></tr>
            </table>  
        </form> 
    </body>
</html>
