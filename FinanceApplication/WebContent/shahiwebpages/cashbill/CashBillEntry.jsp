<%-- 
    Document   : CashBillEntry
    Created on : 2 Aug, 2017, 5:37:23 PM
    Author     : Vivek
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <sj:head jqueryui="true"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="Cache-Control" content="no-store" />
        <title>Cash Bill Entry</title>
        <!-- styles -->
        <link href="../bootstrap/css/bootstrap.css" rel="stylesheet"/>
        <link href="../bootstrap/css/ShahiButtons/shahibuttons.css" rel="stylesheet"/>
        <link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet"/>
        <link href="../bootstrap/css/icons-sprite.css" rel="stylesheet"/>
        <link id="themes" href="../bootstrap/css/theme-chayam.css" rel="stylesheet"/>
        <link id="themes" href="../bootstrap/css/style-reset.css" rel="stylesheet"/>
        <link rel="stylesheet" href="../bootstrap/js/1.11.4/themes/smoothness/jquery-ui.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/datatables/css/jquery.dataTables.min.css"/>

        <style type="text/css">
            input[type="text"]{
                font-size:10px;
            }
            .rootsupp
            {
                position:absolute;
                height:380px;
                width:700px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .rootaddress
            {
                position:absolute;
                height:255px;
                width:550px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .rootaddline
            {
                position:fixed;
                height:150px;
                width:450px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .rootcomment
            {
                position:absolute;
                height:290px;
                width:550px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .rootbreakup
            {
                position:fixed;
                height:200px;
                width:250px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px;
                top:150px;
            } 
            .handlebreakup
            {
                position:relative;
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px					
            } 
            .handle
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px					
            }     
            .rootline
            {
                position:absolute;
                height:400px;
                width:1000px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .handleline
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px					
            }

            h4 {
                font-size: 14px !important;
                line-height: 16px !important;
                margin: 5px 0 !important;
            }
            .dataTables_scrollBody{
                height:280px;
            }
            table.dataTable tbody th, table.dataTable tbody td {
                padding: 2px 3px;
            }
            .dataTables_scrollBody{
                overflow-x: hidden !important;
                height:400px;
            }
            table.dataTable thead th, table.dataTable thead td {
                padding: 5px 3px;
                border-bottom: 0px solid #111;
            }
        </style> 
        <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    height: 510px;
                    width: 700px;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 28px ;
                }
                thead tr {

                }
                tbody {
                    height: auto;
                }
            </style>
        <![endif]-->

        <!--[if IE 7]>
        <link rel="stylesheet" type="text/css" href="../css/ie/ie7.css" />
        <![endif]-->
        <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" href="../css/ie/ie8.css" />
        <![endif]-->
        <!--[if IE 9]>
        <link rel="stylesheet" type="text/css" href="../css/ie/ie9.css" />
        <![endif]-->


    </head>
    <body style="padding: 0px;background-color: #fff;">
        <div align="center" id="prepage" class="centered" style="display:  none;">
            <img src="../bootstrap/img/loading.gif"/><br/><span style="font-weight: bold;color:#4D4D4D;font-size: 25px;">&nbsp;&nbsp;Please Wait...</span>		
        </div>
        <form  action=# method="post" id="formId">
            <s:hidden name="MAST_SL_NO" id="MAST_SL_NO"/>
            <s:hidden name="UPD_MAST" id="UPD_MAST" value="%{MAST_SL_NO}"/>
            <s:hidden name="FIN_STATUS" id="FIN_STATUS"/>
            <div id="main-content">
                <div class="container-fluid">
                    <div class="row-fluid">				
                        <div class="span12" style="text-align:center;font-size: 18px;background-color: #f2f2f2;font-weight: bold;padding: 2px;border-width: 1px;border-color:grey;border-style: solid;">
                            Cash Entry
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span12">
                            <fieldset>
                                <table style="width:100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered">
                                    <tr style="height:25px;">
                                        <th style="width:7%;">
                                            Warehouse
                                        </th>
                                        <th style="width:6%;">
                                            Pay.Type
                                        </th>
                                        <th style="width:10%;">
                                            Bill No
                                        </th>
                                        <th style="width:10%;">
                                            Date
                                        </th>
                                        <th style="width:30%;">
                                            Supplier
                                        </th>
                                        <th style="width:7%;">
                                            State
                                        </th>
                                        <th style="width:10%;text-align: right;">
                                            Bill Amount
                                        </th>
                                        <th style="width:15%;">
                                            &nbsp;
                                        </th>
                                        <th style="width:5%;">
                                            
                                        </th>
                                    </tr>
                                    <tr>
                                        <td style="width:7%;">
                                            <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0 && WAREHOUSE!=null}">
                                                <s:textfield name="WAREHOUSE" id="WAREHOUSE" theme="simple" cssStyle="width:95%;" readonly="true"/>
                                            </s:if>
                                            <s:else>
                                                <s:select name="WAREHOUSE" id="WAREHOUSE" list="WAREHOUSE_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select Warehouse" onchange="reloadnewpage();"/>
                                            </s:else>                                            
                                        </td>
                                        <td style="width:6%;">
                                            <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0 && WAREHOUSE!=null}">                                                
                                                <s:textfield name="PAY_TYPE" id="PAY_TYPE" theme="simple" cssStyle="width:95%;" readonly="true"/>
                                            </s:if>
                                            <s:else>
                                                <s:select name="PAY_TYPE" id="PAY_TYPE" list="# {'CASH':'CASH','BANK':'BANK'}" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select Payment Type"/>
                                            </s:else>
                                        </td>
                                        <td style="width:10%;">
                                            <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                                <s:textfield name="BILL_NO" id="BILL_NO" theme="simple" cssStyle="width:95%;" readonly="true"/>
                                            </s:if>
                                            <s:else>
                                                <s:textfield name="BILL_NO" id="BILL_NO" theme="simple" cssStyle="width:95%;" maxlength="24"/>
                                            </s:else>
                                        </td>
                                        <td style="width:10%;">
                                            <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                                <s:textfield name="BILL_DATE" id="BILL_DATE" theme="simple" cssStyle="width:70%;" readonly="true"/>
                                            </s:if>
                                            <s:else>
                                                <sj:datepicker name="BILL_DATE" id="BILL_DATE" theme="simple" cssStyle="width:70%;" displayFormat="dd/mm/yy"/>
                                            </s:else>                                            
                                        </td>
                                        <td style="width:30%;">
                                            <s:textfield name="SUPPLIER" id="SUPPLIER" theme="simple" cssStyle="width:20%;" readonly="true"/>
                                            <s:textfield name="SUPPLIER_DESC" id="SUPPLIER_DESC" theme="simple" cssStyle="width:60%;" readonly="true"/>
                                            <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                            </s:if>
                                            <s:else>
                                                <a href="supCashBillEntryAction.action" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Supplier")' >
                                                    <img src="../bootstrap/img/search-icon.png" title="Search Supplier"/>
                                                </a>
                                            </s:else>
                                            <a href="#" onclick="openpopadd('rootaddress');">
                                                <img src="../bootstrap/img/detailicon.png" title="Supplier Details"/>
                                            </a>
                                            <!-- Address Tab -->
                                            <div id="rootaddress" class="rootaddress" style="right:50px; top:10px;display:none;z-index: 999">
                                                <table cellpadding="0" cellspacing="0" style="position: relative;">
                                                    <tr bgcolor="#f2f2f2">
                                                        <td bgcolor="#2a6afe" height='23'  width=680px'  style="font-size:12px;color:white;font-weight: bold" align="center"><div id="handleaddress" class="handle"  style="cursor: move">&nbsp;Address</div></td>
                                                        <td><a href="#" onclick="closediv('rootaddress')" ><img height='23' width='24' src='../bootstrap/img/divclose.gif' border='0'/></a></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <div class="box-tab" style="width:100%;">
                                                                <table style="width:100%;">                                                                                                                                    
                                                                    <tr>
                                                                        <td>
                                                                            <table>
                                                                                <tr>
                                                                                    <td>
                                                                                        <s:hidden id="ADID" name="ADID" title="ADID"/>
                                                                                        <s:hidden id="RGDT" name="RGDT" title="RGDT"/>
                                                                                        <s:textfield cssClass="disabled" readonly="true" id="CONM" name="CONM" title="CONM" theme="simple" cssStyle="width:480px;"/>
                                                                                    </td>                                                                                    
                                                                                </tr>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><s:textfield cssClass="disabled" readonly="true" id="ADR1" name="ADR1" title="ADR1" theme="simple" cssStyle="width:480px;"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><s:textfield cssClass="disabled" readonly="true" id="ADR2" name="ADR2" title="ADR2" theme="simple" cssStyle="width:480px;"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><s:textfield cssClass="disabled" readonly="true" id="ADR3" name="ADR3" title="ADR3" theme="simple" cssStyle="width:480px;"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><s:textfield cssClass="disabled" readonly="true" id="ADR4" name="ADR4" title="ADR4" theme="simple" cssStyle="width:480px;"/></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <table>
                                                                                <tr>
                                                                                    <td style="padding-right: 5px;"><s:textfield cssClass="disabled" readonly="true" id="TOWN" name="TOWN" title="TOWN" theme="simple" cssStyle="width:178px;"/></td>
                                                                                    <td style="padding-right: 5px;"><s:textfield cssClass="disabled" readonly="true" id="ECAR" name="ECAR" title="ECAR" theme="simple" cssStyle="width:80px;"/></td>
                                                                                    <td style="padding-right: 5px;"><s:textfield cssClass="disabled" readonly="true" id="PONO" name="PONO" title="PONO" theme="simple" cssStyle="width:100px;"/></td>
                                                                                    <td><s:textfield cssClass="disabled" readonly="true" id="CSCD" name="CSCD" title="CSCD" theme="simple" cssStyle="width:75px;"/></td>
                                                                                </tr>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <table>
                                                                                <tr>
                                                                                    <td style="padding-right: 5px;">Status</td>
                                                                                    <td style="padding-right: 5px;">
                                                                                        <s:hidden name="GSTF" id="GSTF"/>
                                                                                        <s:textfield cssClass="disabled" readonly="true" id="GSTD" name="GSTD" title="GSTD" theme="simple" cssStyle="width:120px;"/>
                                                                                    </td>
                                                                                    <td style="padding-right: 5px;">GSTIN</td>
                                                                                    <td><s:textfield cssClass="disabled" readonly="true" id="GSTN" name="GSTN" title="GSTN" theme="simple" cssStyle="width:120px;"/></td>
                                                                                </tr>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                        <td style="width:7%;">
                                            <s:textfield name="SUPPLIER_STATE" id="SUPPLIER_STATE" value="%{ECAR}" theme="simple" cssStyle="width:95%;" readonly="true"/>
                                        </td>
                                        <td style="width:10%;">
                                            <s:textfield name="BILL_AMOUNT" id="BILL_AMOUNT" theme="simple" cssStyle="width:95%;text-align:right;" onblur="validatenumber(this);"/>
                                        </td>
                                        <td>
                                           Reverse Service Tax  
                                           <s:if test='%{REVERSE_SRVTAX!=null && REVERSE_SRVTAX.length()>0 && REVERSE_SRVTAX=="1"}'>
                                               <input type="checkbox" name="REVERSE_SRVTAX" id="REVERSE_SRVTAX" value="1" checked="true" onclick="srvrevrsechk();"/>
                                           </s:if>
                                           <s:else>
                                               <input type="checkbox" name="REVERSE_SRVTAX" id="REVERSE_SRVTAX" value="1" onclick="srvrevrsechk();"/>
                                           </s:else>
                                        </td>
                                        <td>
                                           <a href="#" onclick="openpopadd('rootcomment');"><img  height='18' width='18' styl="border:0;padding-bottom:-4px;" src="../bootstrap/img/icon_comment.png"/></a>
                                           <div id="rootcomment" class="rootaddress" style="right:50px; top:10px;display:none;z-index: 999">
                                                <table cellpadding="0" cellspacing="0" style="position: relative;">
                                                    <tr bgcolor="#f2f2f2">
                                                        <td bgcolor="#2a6afe" height='23'  width=780px'  style="font-size:12px;color:white;font-weight: bold" align="center"><div id="handleaddress" class="handle"  style="cursor: move">&nbsp;Comment</div></td>
                                                        <td><a href="#" onclick="closediv('rootcomment')" ><img height='23' width='24' src='../bootstrap/img/divclose.gif' border='0'/></a></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <s:textarea name="MAST_REMARKS" id="MAST_REMARKS" theme="simple" cssStyle="width:535px;height:180px;font-size:18px;" maxlength="200"></s:textarea>
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="2">                                                        
                                                         <span id="chars">200</span> characters remaining
                                                        </td>
                                                    </tr>
                                                </table>
                                           </div>
                                        </td>
                                    </tr>
                                </table>   
                            </fieldset>
                        </div>
                    </div>
                    <%--div class="row-fluid">
                        <div class="span12" style="height: 230px;">
                            <table id='fabricnobomview' class="table table-bordered table-striped" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th style="width:15%;">Bill Type</th>
                                        <th style="width:15%;">Bill Sub Type</th>
                                        <th>PCH</th>
                                        <th style="width:15%;">Cost Center</th>
                                        <th style="width:15%;">Product Group</th>
                                        <th style="text-align: right;">Amount</th>
                                        <th style="text-align: right;">N.G.Amount</th>
                                        <th>HSN/SAC</th>
                                        <th style="vertical-align: middle;"><input type="checkbox" id="checkall" name="checkall" style="margin-top: -2px;"/> &nbsp; Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:iterator value="DETAIL_LIST" status="st">
                                        <tr>
                                            <td style="width:15%;"><s:property value="%{TYPE_SL_NO_DESC}"/></td>
                                            <td style="width:15%;"><s:property value="%{SUB_TYPE_SL_NO_DESC}"/></td>
                                            <td style="width:10%;"><s:property value="%{PCH}"/></td>
                                            <td style="width:15%;"><s:property value="%{CC_CODE_DESC}"/></td>
                                            <td style="width:15%;"><s:property value="%{PRODUCT_SL_NO_DESC}"/></td>
                                            <td style="width:7%;text-align: right;"><s:property value="%{PRODUCT_AMOUNT}"/></td>
                                            <td style="width:7%;text-align: right;"><s:property value="%{NON_GST_AMOUNT}"/></td>
                                            <td style="width:10%"><s:property value="%{HSN_CODE}"/></td>
                                            <td><input type="checkbox" id="chkrow_id<s:property value="%{#st.index}"/>" name="DELETE_LINE" value="<s:property value="%{SL_NO}"/>" style="margin-top: -2px;"/></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </div--%>
                    <div class="row-fluid">
                        <div class="span12" style="text-align: center;">
                            <s:if test="%{REPORT_NO!=null && REPORT_NO.length()>0}">
                                <a href='#' class='sexybutton disabled'><span><span><span class='save' style="color:red;">Save</span></span></span></a> &nbsp;
                            </s:if>
                            <s:else>
                                <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0 && USER_ID!=null && USER_ID==#session.sessUserId}">
                                    <a onclick='savedata();' href='#' class='sexybutton'><span><span><span class='save'>Save</span></span></span></a> &nbsp;
                                </s:if>
                                <s:elseif test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                    <a href='#' class='sexybutton disabled'><span><span><span class='save' style="color:red;">Save</span></span></span></a> &nbsp;
                                </s:elseif>
                                <s:else>
                                   <a onclick='savedata();' href='#' class='sexybutton'><span><span><span class='save'>Save</span></span></span></a> &nbsp;     
                                </s:else>
                            </s:else> 
                            <s:if test="%{FIN_STATUS!=null && FIN_STATUS.length()>0}">
                            </s:if>
                            <s:else>
                                <a onclick='ongonew();' href='#' class='sexybutton'><span><span><span class='edit'>New</span></span></span></a> &nbsp;
                            </s:else>
                            <a onclick='ongosearch();' href='#' class='sexybutton'><span><span><span class='undo'>Back</span></span></span></a>
                        </div>
                    </div>
                    <div class="row-fluid" style="margin-top: 15px;height: 450px;">
                        <div class="span12" style="height: 200px;border-width: 1px; border-style: solid;border-color:#dddddd;">
                            <table id="fabricnobom" class="table table-bordered table-striped" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th style="width:9%;">Bill Type</th>
                                        <th style="width:9%;">Bill Sub Type</th>
                                        <th style="width:6%">PCH</th>
                                        <th style="width:9%;">Cost Center</th>
                                        <th style="width:9%;">Product Group</th>
                                        <th style="width:6%;">Partner</th>
                                        <th style="width:11%;">Description</th>
                                        <th style="text-align: right;">Qty</th>
                                        <th style="text-align: center;">UOM</th>
                                        <th style="text-align: right;">Amount</th>
                                        <th style="text-align: right;">N.G.Amount</th>
                                        <th>HSN/SAC</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:set name="ndx" id="ndx" value="0"/>
                                    <s:iterator value="DETAIL_LIST" status="st">
                                        <tr>
                                            <td style="width:9%;">
                                                <s:select name="BILL_TYPE_SLNO" id="BILL_TYPE_ID%{#ndx}" cssClass="BILL_TYPE_SLNO_CLASS" value="%{TYPE_SL_NO}" list="BILLTYPE_LIST" theme="simple" cssStyle="width:98%;" listKey="SL_NO" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" headerKey="" headerValue="Select BILL TYPE" onchange="refreshbilltype('%{#ndx}');"/>
                                            </td>
                                            <td style="width:9%;">
                                                <s:select name="BILL_SUBTYPE_SLNO" id="BILL_SUBTYPE_ID%{#ndx}" value="%{SUB_TYPE_SL_NO}" list="BILLSUBTYPE_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select BILL SUB TYPE" listKey="SL_NO" listValue="SUB_TYPE_DESC+'-'+SUB_TYPE_CODE" onchange="refreshbillsubtype('%{#ndx}');"/>
                                            </td>
                                            <td style="width:6%">
                                                <s:select name="PCH" id="PCH_ID%{#ndx}" list="PCH_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select PCH"/>
                                            </td>
                                            <td style="width:9%;">
                                                <s:select name="COST_CENTER" id="COST_CENTER_ID%{#ndx}" value="%{CC_CODE}" list="BILLCC_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select COST CENTER" listKey="EAAITM" listValue="EATX40"/>
                                            </td>
                                            <td style="width:9%;">
                                                <s:select name="PRODUCT_GROUP" id="PRODUCT_GROUP_ID%{#ndx}" value="%{PRODUCT_SL_NO}" list="BILLPRODUCT_TYPE_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select PRODUCT GROUP" listKey="SL_NO" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}"/>
                                            </td>
                                            <td style="width:6%;">
                                                <s:textfield name="PARTNER_DESC" id="PARTNER_DESC_ID%{#ndx}" value="%{PARTNER_DESC}" theme="simple" maxlength="8" cssStyle="width:95%;text-transform:uppercase;"/>
                                            </td>
                                            <td style="width:11%;">
                                                <s:textfield name="VOUCHER_DESC" id="VOUCHER_DESC_ID%{#ndx}" value="%{VOUCHER_DESC}" theme="simple" maxlength="40" cssStyle="width:95%;text-transform:uppercase;"/>
                                            </td>
                                            <td style="width:6%">
                                                <s:textfield name="BILL_AMOUNT_QUANTITY" id="BILL_AMOUNT_QUANTITY_ID%{#ndx}" value="%{PRODUCT_QUANTITY}" theme="simple" cssStyle="width:90%;text-align:right;" onblur="validatenumber(this);callgstcal('%{#ndx}');"/>
                                            </td>
                                            <td style="width:5%;">
                                                <s:select name="BILL_AMOUNT_UOM" id="BILL_AMOUNT_UOM_ID%{#ndx}" value="%{UOM}" list="UOM_LIST" theme="simple" cssStyle="width:98%;" listKey="EAAITM" listValue="%{EAAITM+'-'+EATX40}" headerKey="" headerValue="Select UOM"/>
                                            </td>
                                            <td style="width:7%">
                                                <s:textfield name="BILL_AMOUNT_BREAKUP" id="BILL_AMOUNT_BREAKUP_ID%{#ndx}" value="%{PRODUCT_AMOUNT}" theme="simple" cssStyle="width:90%;text-align:right;" onblur="validatenumber(this);callgstcal('%{#ndx}');"/>
                                            </td>
                                            <td style="width:7%">
                                                <s:textfield name="BILL_AMOUNT_NON_BREAKUP" id="BILL_AMOUNT_NON_BREAKUP_ID%{#ndx}" value="%{NON_GST_AMOUNT}" theme="simple" cssStyle="width:90%;text-align:right;" onblur="validatenumber(this);"/>
                                                <s:hidden name="REMARKS" id="REMARKS_ID%{#ndx}"/>
                                            </td>
                                            <td style="width:10%">
                                                <s:textfield name="BILL_AMOUNT_HSN" id="BILL_AMOUNT_HSN_ID%{#ndx}" value="%{HSN_CODE}" theme="simple" readonly="true" cssStyle="width:65%;"/>
                                                <s:hidden name="BREAKUP_COUNT" id="BREAKUP_COUNT_ID%{#ndx}" value="%{GST_LIST.size()}"/>
                                                <a href="searchhsnCashBillEntryAction.action?T_INDEX=<s:property value="%{#ndx}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("HSN/SAC");' ><img src="../bootstrap/img/search-icon.png"  title="Search HSN/SAC"/></a>
                                            </td>
                                            <td style="width:4%;">
                                                <a href="#" onclick="openpopadd('rootgstncal<s:property value="%{#ndx}"/>');"><img src="../bootstrap/img/detailicon.png"  title="Amount Breakup"/></a>
                                                <div id="rootgstncal<s:property value="%{#ndx}"/>" class="rootaddline" style="right:50px; top:150px;display:none;z-index: 999">
                                                    <table cellpadding="0" cellspacing="0" style="position: relative;">
                                                        <tr bgcolor="#f2f2f2">
                                                            <td bgcolor="#2a6afe" height='23'  width=680px'  style="font-size:12px;color:white;font-weight: bold;background-color:#2a6afe;" align="center"><div id="handleaddress" class="handle"  style="cursor: move">&nbsp;Breakup</div></td>
                                                            <td><a href="#" onclick="closediv('rootgstncal<s:property value="%{#ndx}"/>');" ><img height='23' width='24' src='../bootstrap/img/divclose.gif' border='0'/></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">
                                                                <div class="box-tab" style="width:100%;">
                                                                    <table style="width:100%;" id="rootgstntable<s:property value="%{#ndx}"/>" class="table table-bordered table-striped">
                                                                        <s:iterator value="GST_LIST" status="gstst">
                                                                            <tr>
                                                                                <td>
                                                                                    <input type='hidden' name='COST_ELEMENT' id='COST_ELEMENT<s:property value="%{#ndx}"/>LINE<s:property value="%{#gstst.index}"/>' value='<s:property value="%{TAX2}"/>'/>
                                                                                    <s:property value="%{TAXC}"/>
                                                                                </td>
                                                                                <td>
                                                                                    <input type='hidden' name='COST_BREAKUP_TAX' id='COST_BREAKUP_TAX<s:property value="%{#ndx}"/>LINE<s:property value="%{#gstst.index}"/>' value='<s:property value="%{TAX1}"/>'/>
                                                                                    <input type='hidden' name='COST_BREAKUP_TAX_CODE' id='COST_BREAKUP_TAX_CODE<s:property value="%{#ndx}"/>LINE<s:property value="%{#gstst.index}"/>' value='<s:property value="%{TAX_CODE}"/>'/>
                                                                                    <s:property value="%{TAX1}"/>
                                                                                </td>
                                                                                <td>
                                                                                    <input type='hidden' name='COST_BREAKUP_AMT' id='COST_BREAKUP_AMT<s:property value="%{#ndx}"/>LINE<s:property value="%{#gstst.index}"/>' value='<s:property value="%{CALVAL}"/>'/>
                                                                                    <s:property value="%{CALVAL}"/>
                                                                                </td>
                                                                            </tr>
                                                                        </s:iterator>                        
                                                                    </table>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>
                                        <s:set name="ndx" id="ndx" value="%{#ndx+1}"/>
                                    </s:iterator>
                                    <s:iterator begin="%{#ndx}" end="20" status="st">
                                        <tr>
                                            <td style="width:9%;">
                                                <s:select name="BILL_TYPE_SLNO" id="BILL_TYPE_ID%{#ndx}" cssClass="BILL_TYPE_SLNO_CLASS" list="BILLTYPE_LIST" theme="simple" cssStyle="width:98%;" listKey="SL_NO" listValue="%{SUB_TYPE_DESC+'-'+SUB_TYPE_CODE}" headerKey="" headerValue="Select BILL TYPE" onchange="refreshbilltype('%{#ndx}');"/>
                                            </td>
                                            <td style="width:9%;">
                                                <s:select name="BILL_SUBTYPE_SLNO" id="BILL_SUBTYPE_ID%{#ndx}" list="BILLSUBTYPE_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select BILL SUB TYPE" listKey="SL_NO" listValue="SUB_TYPE_DESC+'-'+SUB_TYPE_CODE" onchange="refreshbillsubtype('%{#ndx}');"/>
                                            </td>
                                            <td style="width:6%">
                                                <s:select name="PCH" id="PCH_ID%{#ndx}" list="PCH_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select PCH"/>
                                            </td>
                                            <td style="width:9%;">
                                                <s:select name="COST_CENTER" id="COST_CENTER_ID%{#ndx}" list="BILLCC_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select COST CENTER"/>
                                            </td>
                                            <td style="width:9%;">
                                                <s:select name="PRODUCT_GROUP" id="PRODUCT_GROUP_ID%{#ndx}" list="BILLPRODUCT_TYPE_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select PRODUCT GROUP"/>
                                            </td>
                                            <td style="width:6%;">
                                                <s:textfield name="PARTNER_DESC" id="PARTNER_DESC_ID%{#ndx}" theme="simple" maxlength="8" cssStyle="width:95%;text-transform:uppercase;"/>
                                            </td>
                                            <td style="width:11%;">
                                                <s:textfield name="VOUCHER_DESC" id="VOUCHER_DESC_ID%{#ndx}" theme="simple" maxlength="40" cssStyle="width:95%;text-transform:uppercase;"/>
                                            </td>
                                            <td style="width:6%">
                                                <s:textfield name="BILL_AMOUNT_QUANTITY" id="BILL_AMOUNT_QUANTITY_ID%{#ndx}" theme="simple" cssStyle="width:90%;text-align:right;" onblur="validatenumber(this);"/>
                                            </td>                                            
                                            <td style="width:5%;">
                                                <s:select name="BILL_AMOUNT_UOM" id="BILL_AMOUNT_UOM_ID%{#ndx}" list="UOM_LIST" theme="simple" cssStyle="width:98%;" listKey="EAAITM" listValue="%{EAAITM+'-'+EATX40}" headerKey="" headerValue="Select UOM"/>
                                            </td>
                                            <td style="width:7%">
                                                <s:textfield name="BILL_AMOUNT_BREAKUP" id="BILL_AMOUNT_BREAKUP_ID%{#ndx}" theme="simple" cssStyle="width:95%;text-align:right;" onblur="validatenumber(this);callgstcal('%{#ndx}');"/>
                                            </td>
                                            <td style="width:7%">
                                                <s:textfield name="BILL_AMOUNT_NON_BREAKUP" id="BILL_AMOUNT_NON_BREAKUP_ID%{#ndx}" theme="simple" cssStyle="width:95%;text-align:right;" onblur="validatenumber(this);"/>
                                                <s:hidden name="REMARKS" id="REMARKS_ID%{#ndx}"/>
                                            </td>
                                            <td style="width:10%">
                                                <s:textfield name="BILL_AMOUNT_HSN" id="BILL_AMOUNT_HSN_ID%{#ndx}" theme="simple" readonly="true" cssStyle="width:65%;"/>
                                                <s:hidden name="BREAKUP_COUNT" id="BREAKUP_COUNT_ID%{#ndx}"/>
                                                <a href="searchhsnCashBillEntryAction.action?T_INDEX=<s:property value="%{#ndx}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("HSN/SAC");' ><img src="../bootstrap/img/search-icon.png"  title="Search HSN/SAC"/></a>
                                            </td>
                                            <td style="width:4%;">
                                                <a href="#" onclick="openpopadd('rootgstncal<s:property value="%{#ndx}"/>');"><img src="../bootstrap/img/detailicon.png"  title="Amount Breakup"/></a>
                                                <div id="rootgstncal<s:property value="%{#ndx}"/>" class="rootaddline" style="right:50px; top:150px;display:none;z-index: 999">
                                                    <table cellpadding="0" cellspacing="0" style="position: relative;">
                                                        <tr bgcolor="#f2f2f2">
                                                            <td bgcolor="#2a6afe" height='23'  width=680px'  style="font-size:12px;color:white;font-weight: bold;background-color:#2a6afe;" align="center"><div id="handleaddress" class="handle"  style="cursor: move">&nbsp;Breakup</div></td>
                                                            <td><a href="#" onclick="closediv('rootgstncal<s:property value="%{#ndx}"/>');" ><img height='23' width='24' src='../bootstrap/img/divclose.gif' border='0'/></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2">
                                                                <div class="box-tab" style="width:100%;">
                                                                    <table style="width:100%;" id="rootgstntable<s:property value="%{#ndx}"/>">
                                                                    </table>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>                                    
                                        <s:set name="ndx" id="ndx" value="%{#ndx+1}"/>
                                    </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    </div>     
                    <div class="row-fluid">
                        <div class="span12" style="text-align: center;color:red;font-weight: bold;">
                            <s:actionerror/>
                            <s:fielderror/>
                            <s:actionmessage/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script src="../bootstrap/datatables/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript">
            function reloadnewpage(){
                $('#formId').attr('action','newpageCashBillEntryAction');
                $('#formId').submit();
                $('#prepage').show();
            }
            function refreshbilltype(index){
                if($.trim($('#BILL_NO').val())==''){
                    alert('Please Enter Bill No!!!');
                    $('#BILL_TYPE_ID'+index).val('');
                    $('#BILL_NO').focus();
                    return false;
                } else if($.trim($('#SUPPLIER').val())==''){
                    alert('Please Choose Supplier!!!');
                    $('#BILL_TYPE_ID'+index).val('');
                    $('#SUPPLIER').focus();
                    return false;
                } else if($.trim($('#BILL_AMOUNT').val())==''){
                    alert('Please Enter Bill Amount!!!');
                    $('#BILL_TYPE_ID'+index).val('');
                    $('#BILL_AMOUNT').focus();
                    return false;
                }
                
                var BILL_TYPE = $('#BILL_TYPE_ID'+index).val();
                return $.ajax({
                    async: true,
                    cache: false,
                    type: 'GET',
                    url: 'ajaxsubtypeCashBillAjaxAction.action?BILL_TYPE='+BILL_TYPE,
                    success : function(data){
                        var select = $('#BILL_SUBTYPE_ID'+index);
                        select.find('option').remove();
                        $('<option>').val('').text('Select BILL SUB TYPE').appendTo(select);
                        for (var i = 0; i < data.BILLSUBTYPE_LIST.length; i++) {
                            $('<option>').val(data.BILLSUBTYPE_LIST[i].SL_NO).text(data.BILLSUBTYPE_LIST[i].SUB_TYPE_DESC+'-'+data.BILLSUBTYPE_LIST[i].SUB_TYPE_CODE).appendTo(select);
                        }

                        var select = $('#COST_CENTER_ID'+index);
                        select.find('option').remove();
                        $('<option>').val('').text('Select COST CENTER').appendTo(select);
                        for (var i = 0; i < data.BILLCC_LIST.length; i++) {
                            $('<option>').val(data.BILLCC_LIST[i].EAAITM).text(data.BILLCC_LIST[i].EATX40).appendTo(select);
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
            }
            function refreshbillsubtype(index){
                var BILL_TYPE = $('#BILL_SUBTYPE_ID'+index).val();
                return $.ajax({
                    async: true,
                    cache: false,
                    type: 'GET',
                    url: 'ajaxprodtypeCashBillAjaxAction.action?BILL_SUBTYPE='+BILL_TYPE,
                    success : function(data){
                        var select = $('#PRODUCT_GROUP_ID'+index);
                        select.find('option').remove();
                        $('<option>').val('').text('Select PRODUCT GROUP').appendTo(select);
                        for (var i = 0; i < data.BILLPRODUCT_TYPE_LIST.length; i++) {
                            $('<option>').val(data.BILLPRODUCT_TYPE_LIST[i].SL_NO).text(data.BILLPRODUCT_TYPE_LIST[i].SUB_TYPE_DESC+'-'+data.BILLPRODUCT_TYPE_LIST[i].SUB_TYPE_CODE).appendTo(select);
                        }
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });
            }
            
            function callgstcal(index){
                if($('#BILL_AMOUNT_HSN_ID'+index).val()!='' && $('#BILL_AMOUNT_BREAKUP_ID'+index).val()!=''){
                    var WAREHOUSE = $('#WAREHOUSE').val();
                    var CSCD = $('#CSCD').val();
                    var ECAR = $('#ECAR').val();
                    var HSN_CODE = $('#BILL_AMOUNT_HSN_ID'+index).val();
                    var BREAK_AMOUNT = $('#BILL_AMOUNT_BREAKUP_ID'+index).val();
                    $('#prepage').show();
                    return $.ajax({
                        async: true,
                        cache: false,
                        type: 'POST',
                        url: 'gstcalcCashBillAjaxAction.action',
                        data: {WAREHOUSE:WAREHOUSE,CSCD:CSCD,ECAR:ECAR,HSN_CODE:HSN_CODE,BREAK_AMOUNT:BREAK_AMOUNT},
                        success : function(data){
                            var html = "";
                            for (var i = 0; i < data.GST_LIST.length; i++) {
                                if(i%2==0){
                                    html+="<tr><td style='background-color:#fff;width:30%'><input type='hidden' name='COST_ELEMENT' id='COST_ELEMENT"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX2+"'/><input type='text' name='DISTAXC' id='DISTAXC"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAXC+"' style='width:80%' readonly='true'/></td><td style='background-color:#fff;width:30%;'><input type='hidden' name='COST_BREAKUP_TAX' id='COST_BREAKUP_TAX"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX1+"'/><input type='hidden' name='COST_BREAKUP_TAX_CODE' id='COST_BREAKUP_TAX_CODE"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX_CODE+"'/><input type='text' name='DISTAXPER' id='DISTAXPER"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX1+"' style='width:80%' readonly='true'/></td><td style='background-color:#fff;width:30%;'><input type='hidden' name='COST_BREAKUP_AMT' id='COST_BREAKUP_AMT"+index+"LINE"+i+"' value='"+data.GST_LIST[i].CALVAL+"'/><input type='text' name='DISTAXPERVAL' id='DISTAXPERVAL"+index+"LINE"+i+"' value='"+eval(data.GST_LIST[i].CALVAL).toFixed(2)+"' style='width:80%' readonly='true'/></td><td><input type='checkbox' name='ADJUST_CALC"+index+"LINE"+i+"' onclick='adjustvalue(\""+index+"\",\""+i+"\");' title='Adjust Amount'></td></tr>\n";
                                }else{
                                    html+="<tr><td style='background-color:#b3ccff;width:30%'><input type='hidden' name='COST_ELEMENT' id='COST_ELEMENT"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX2+"'/><input type='text' name='DISTAXC' id='DISTAXC"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAXC+"' style='width:80%' readonly='true'/></td><td style='background-color:#b3ccff;width:30%;'><input type='hidden' name='COST_BREAKUP_TAX' id='COST_BREAKUP_TAX"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX1+"'/><input type='hidden' name='COST_BREAKUP_TAX_CODE' id='COST_BREAKUP_TAX_CODE"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX_CODE+"'/><input type='text' name='DISTAXPER' id='DISTAXPER"+index+"LINE"+i+"' value='"+data.GST_LIST[i].TAX1+"' style='width:80%' readonly='true'/></td><td style='background-color:#b3ccff;width:30%;'><input type='hidden' name='COST_BREAKUP_AMT' id='COST_BREAKUP_AMT"+index+"LINE"+i+"' value='"+data.GST_LIST[i].CALVAL+"'/><input type='text' name='DISTAXPERVAL' id='DISTAXPERVAL"+index+"LINE"+i+"' value='"+eval(data.GST_LIST[i].CALVAL).toFixed(2)+"' style='width:80%' readonly='true'/></td><td><input type='checkbox' name='ADJUST_CALC"+index+"LINE"+i+"' onclick='adjustvalue(\'"+index+"\",\""+i+"\");' title='Adjust Amount'></td></tr>\n";
                                }
                            }
                            $('#rootgstntable'+index).html(html);
                            $('#BREAKUP_COUNT_ID'+index).val(data.GST_LIST.length);
                            $('#prepage').hide();
                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            alert(xhr.status);
                            alert(thrownError);
                        }
                    });
                }else{
                    $('#rootgstntable'+index).html('');
                    $('#BREAKUP_COUNT_ID'+index).val(0);
                }
            }
            
            function approveraddidClose() {
                document.getElementById("approveraddid").style.display = "none";
            }
            function addhead(a){
                var td = document.getElementById('headid');
                td.innerHTML =a;
            }
            function openpopadd(a)
            {	
                document.getElementById(a).style.display='';
            }
            function closediv(a)
            {
                document.getElementById(a).style.display='none';
            }
            function srvrevrsechk(){
                if($.trim($('#CSCD').val())!='IN'){
                    $('#REVERSE_SRVTAX').attr('checked',true);
                }
            }
            function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
            		return false;
            	}
            	return true;
            }
            function validate(){
                if($.trim($('#BILL_NO').val())==''){
                    alert('Please Enter Bill No!!!');
                    $('#BILL_NO').focus();
                    return false;
                }  else if($.trim($('#PAY_TYPE').val())==''){
                    alert('Please Choose Payment Type!!!');
                    $('#PAY_TYPE').focus();
                    return false;
                } else if($.trim($('#SUPPLIER').val())==''){
                    alert('Please Choose Supplier!!!');
                    $('#SUPPLIER').focus();
                    return false;
                } else if($.trim($('#BILL_AMOUNT').val())==''){
                    alert('Please Enter Bill Amount!!!');
                    $('#BILL_AMOUNT').focus();
                    return false;
                }
                var break_exist=0;
                var error=0;
                var totbreakup=0;
                $('.BILL_TYPE_SLNO_CLASS').each(function (index, value) { 
                     if($('#BILL_TYPE_ID'+index).val()!=''){
                         ++break_exist;
                        if($('#BILL_SUBTYPE_ID'+index).val()==''){
                            ++error;
                            alert('Bill Sub Type can not be blank!!!');
                            $('#BILL_SUBTYPE_ID'+index).focus();
                            return false;
                        }
                        if($('#PCH_ID'+index).val()==''){
                            ++error;
                            alert('PCH can not be blank!!!');
                            $('#PCH_ID'+index).focus();
                            return false;
                        }
                        if($('#COST_CENTER_ID'+index).val()==''){
                            ++error;
                            alert('Cost Center can not be blank!!!');
                            $('#COST_CENTER_ID'+index).focus();
                            return false;
                        }
                        if($('#PRODUCT_GROUP_ID'+index).val()==''){
                            ++error;
                            alert('Product Group can not be blank!!!');
                            $('#PRODUCT_GROUP_ID'+index).focus();
                            return false;
                        }
                        if($.trim($('#VOUCHER_DESC_ID'+index).val())==''){
                            ++error;
                            alert('Voucher Desc can not be blank!!!');
                            $('#VOUCHER_DESC_ID'+index).focus();
                            return false;
                        }
                        if($('#BILL_AMOUNT_QUANTITY_ID'+index).val()==''){
                            ++error;
                            alert('Quantity can not be blank!!!');
                            $('#BILL_AMOUNT_QUANTITY_ID'+index).focus();
                            return false;
                        }
                        if($('#BILL_AMOUNT_UOM_ID'+index).val()==''){
                            ++error;
                            alert('UOM can not be blank!!!');
                            $('#BILL_AMOUNT_UOM_ID'+index).focus();
                            return false;
                        }
                        if($('#BILL_AMOUNT_BREAKUP_ID'+index).val()==''){
                            ++error;
                            alert('Amount can not be blank!!!');
                            $('#BILL_AMOUNT_BREAKUP_ID'+index).focus();
                            return false;
                        }
                        if($('#BILL_AMOUNT_HSN_ID'+index).val()==''){
                            ++error;
                            alert('HSN/SAC can not be blank!!!');
                            $('#BILL_AMOUNT_HSN_ID'+index).focus();
                            return false;
                        }
                        if($('#REVERSE_SRVTAX').attr('checked')){
                            totbreakup+=eval($('#BILL_AMOUNT_BREAKUP_ID'+index).val());
                            if($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val()!=''){
                                totbreakup+=eval($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val());
                            }
                        } else{
                            totbreakup+=eval($('#BILL_AMOUNT_BREAKUP_ID'+index).val());
                            if($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val()!=''){
                                totbreakup+=eval($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val());
                            }
                            if($('#BREAKUP_COUNT_ID'+index).val()!='' && eval($('#BREAKUP_COUNT_ID'+index).val())>0){
                                for(x=0;x<eval($('#BREAKUP_COUNT_ID'+index).val());x++){
                                    totbreakup+=eval($('#COST_BREAKUP_AMT'+index+'LINE'+x).val());
                                }
                            }
                        }
                     }
                });
                if(break_exist==0){
                    alert('No break up exist!!!');
                    return;
                }
                if(error>0){
                    return false;
                }
                if(eval(totbreakup).toFixed(2)!=eval($('#BILL_AMOUNT').val()).toFixed(2)){
                    alert('Bill Amount '+eval($('#BILL_AMOUNT').val()).toFixed(2)+' and Breakup Amount '+eval(totbreakup).toFixed(2)+' is not matched!!!');
                    return false;
                }
                return true;
            }
            function adjustvalue(head,line){
                var totbreakup=0;
                $('.BILL_TYPE_SLNO_CLASS').each(function (index, value) { 
                     if($('#BILL_TYPE_ID'+index).val()!=''){                         
                        if($('#REVERSE_SRVTAX').attr('checked')){
                            totbreakup+=eval($('#BILL_AMOUNT_BREAKUP_ID'+index).val());
                            if($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val()!=''){
                                totbreakup+=eval($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val());
                            }
                        } else{
                            totbreakup+=eval($('#BILL_AMOUNT_BREAKUP_ID'+index).val());
                            if($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val()!=''){
                                totbreakup+=eval($('#BILL_AMOUNT_NON_BREAKUP_ID'+index).val());
                            }
                            if($('#BREAKUP_COUNT_ID'+index).val()!='' && eval($('#BREAKUP_COUNT_ID'+index).val())>0){
                                for(x=0;x<eval($('#BREAKUP_COUNT_ID'+index).val());x++){
                                    totbreakup+=eval($('#COST_BREAKUP_AMT'+index+'LINE'+x).val());
                                }
                            }
                        }
                     }
                });
                if((eval($('#BILL_AMOUNT').val()-eval(totbreakup).toFixed(2)).toFixed(2))<=1 && (eval($('#BILL_AMOUNT').val()).toFixed(2)-eval(totbreakup).toFixed(2))>=-1){
                    var a = $('#DISTAXPERVAL'+head+'LINE'+line).val();
                    a = eval(a)+(eval($('#BILL_AMOUNT').val())-eval(totbreakup).toFixed(2));
                    $('#COST_BREAKUP_AMT'+head+'LINE'+line).val(a.toFixed(2));
                    $('#DISTAXPERVAL'+head+'LINE'+line).val(a.toFixed(2));
                }else{
                    alert('Adjustment not allowed more than 1.00');
                    $('#ADJUST_CALC'+head+'LINE'+line).attr('checked',false);
                    return false;
                }
            }
            function savedata(){
                if(validate()){
                    if(confirm('Do you want to save?')){
                        $('#formId').attr('action','saveCashBillEntryAction.action');
                        $('#formId').submit();
                        $('#prepage').show();
                    }
                }
            }
            function ongonew(){
                $(location).attr('href', 'newpageCashBillEntryAction.action?FIN_STATUS=<s:property value="%{FIN_STATUS}"/>');                                
                $('#prepage').show();
            }
            function ongosearch() {
                $('#formId').attr('action', 'CashBillEntryAction.action');
                $("#formId").submit();                
                $('#prepage').show();
            }
        </script>
        <script type="text/javascript">
        $(document).ready(function() {
            var maxLength = 200;
            $('#MAST_REMARKS').keyup(function() {
              var length = $(this).val().length;
              var length = maxLength-length;
              $('#chars').text(length);
            });
            $('#fabricnobom').DataTable( {
                scrollY: '400px',
                scrollX: '100%',
                scrollCollapse: true,
                paging: false,
                bFilter: false,
                bInfo: false,
                ordering: false
            } );
        } );
    </script>
    </body>
    <div id='approveraddid' name='approveraddid' style='width: 700px; height: 437px; display:none; position: absolute; top: 100px; left:50px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
        <table width='700px' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='1'>
            <tr bgcolor="#f2f2f2">
                <td bgcolor="#2a6afe" height='23'  width=680px'  style="font-size:12px;color:white;font-weight: bold" id="headid" align="center"></td>
                <td><a href='javascript:approveraddidClose();'><img height='23' width='24' src='../bootstrap/img/divclose.gif' border='0'/></a></td>
            </tr>
            <tr bgcolor="#f2f2f2"><td colspan="2"   valign="top">
                    <table width="100%" cellspacing="0" cellpadding='0'>
                        <tr>
                            <td>
                                <iframe name="addapprofrm" id="addapprofrm" scrolling="no" frameborder="0" width="700px" height="410px"></iframe>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</html>