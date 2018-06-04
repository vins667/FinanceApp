<%-- 
    Document   : CashBillQueru
    Created on : 2 Aug, 2017, 5:56:34 PM
    Author     : Vivek
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <sj:head jqueryui="true"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="../bootstrap/css/ShahiButtons/shahibuttons.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/style-reset.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/datatables/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="../bootstrap/datatables/FixedColumns/css/fixedColumns.dataTables.min.css"/>
        <link rel="stylesheet" href="../bootstrap/js/1.11.4/themes/smoothness/jquery-ui.css">
        <style type="text/css">
            table.dataTable thead .sorting, 
            table.dataTable thead .sorting_asc, 
            table.dataTable thead .sorting_desc {
                background : none;
                background-color: #CECECE;
            }
            .DTFC_RightBodyLiner{
                overflow-x: hidden;
            }
            .DTFC_LeftBodyLiner{
                overflow-x: hidden;
            }
            table.dataTable thead th {
                border-bottom: 0;
            }
            table.dataTable tfoot th {
                border-top: 0;
            }
            table.dataTable tbody th, table.dataTable tbody td {
                padding: 4px 5px;
            }
            table.dataTable thead th, table.dataTable thead td {
                padding: 5px 9px;                
            }
            .dataTables_scrollBody{
                overflow-x: hidden !important;
            }
            input[type='text'] {
                display: inline-block;
                height: 20px;
                padding: 2px 3px;
                margin-bottom: 2px;
                font-size: 11px;
                line-height: 20px;
                color: #555555;
                -webkit-border-radius: 0px;
                -moz-border-radius: 0px;
                border-radius: 0px;
            }
            input[type="radio"], input[type="checkbox"] {
                margin: 0px 0 0;
                margin-top: 1px \9;
                line-height: normal;
                cursor: pointer;
            }

        </style> 
        <link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" />
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
    <body style="padding: 0px;">
    <DIV align="center" id="prepage" class="centered" style="display: none;">
        <img src="../bootstrap/img/loading.gif"/><br/><span style="font-weight: bold;color:#4D4D4D;font-size: 30px;">&nbsp;&nbsp;Please Wait...</span>
    </DIV>
    <s:form action="#" name="frm" id="formId" method="post" theme="simple">
        <s:hidden name="FIN_STATUS" id="FIN_STATUS"/>
        <div id="main-content">
            <div class="container-fluid">
                <div class="row-fluid">				
                    <div class="span12" style="text-align:center;font-size: 18px;background-color: #f2f2f2;font-weight: bold;padding: 2px;border-width: 1px;border-color:grey;border-style: solid;">
                        AR Debit/Credit Entry
                    </div>
                </div>
                <div class="row-fluid">				
                    <div class="span12" style="text-align:center;margin-top: 10px;">
                        <div class="widget-block">
                            <div class="widget-content">
				<div class="widget-box">
                                    <div style="display:inline;vertical-align: middle;">
					<table style="width:100%;" align="center">
                                            <tr>
                                                <td style="width:80px;">
                                                    Emp Code<span class="main-mendad">*</span>
                                                </td>
                                                <td style="width:80px;">
                                                    <s:textfield name="USER_QUERY" id="USER_QUERY" value="%{#session.sessUserId}" cssStyle="text-transform:uppercase;width:90%;" theme="simple" readonly="true"/>
                                                </td>
                                                <td style="width:100px;text-align: right;">Warehouse</td>
                                                <td style="width:100px;">
                                                    <s:select name="WAREHOUSE_QUERY" id="WAREHOUSE_QUERY" list="WAREHOUSE_LIST" theme="simple" cssStyle="width:98%;" headerKey="" headerValue="Select Warehouse"/>
                                                </td>
                                                <td style="width:100px;text-align: right;">Control No</td>
                                                <td style="width:100px;">
                                                    <s:textfield name="CONTROL_QUERY" id="CONTROL_QUERY" cssStyle="text-transform:uppercase;width:90%;" theme="simple"/>
                                                </td>
                                                <td style="width:100px;text-align: right;">Bill From</td>
                                                <td style="width:120px;">
                                                    <sj:datepicker name="BILL_FROM_DATE" id="BILL_FROM_DATE" theme="simple" cssStyle="width:70%;"  displayFormat="dd/mm/yy"/>
                                                </td>
                                                <td style="width:50px;text-align: right;">To</td>
                                                <td style="width:120px;">
                                                    <sj:datepicker name="BILL_TO_DATE" id="BILL_TO_DATE" theme="simple" cssStyle="width:70%;"  displayFormat="dd/mm/yy"/>
                                                </td>
                                                <td style="width:100px;text-align: right;">Supplier Code</td>
                                                <td style="width:120px;">
                                                    <s:textfield name="SUPPLIER_QUERY" id="SUPPLIER_QUERY" theme="simple" cssStyle="width:90%;" />
                                                </td>
                                                <td style="width:60px;text-align: right;">Status</td>
                                                <td style="width:120px;">
                                                    <s:if test="%{FIN_STATUS!=null && FIN_STATUS=='YES'}">
                                                        <s:select id="STATUS" name="STATUS" theme="simple" cssStyle="text-transform:uppercase;width:90%;" list="# {'P':'Pending','A':'All'}"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:select id="STATUS" name="STATUS" theme="simple" cssStyle="text-transform:uppercase;width:90%;" list="# {'P':'Pending','F':'Forwarded(A/Cs)','A':'All'}"/>
                                                    </s:else>
                                                </td>
                                                <td style="width:100px;text-align: right;">Voucher Date</td>
                                                <td style="width:120px;"><sj:datepicker name="VCH_DATE" id="VCH_DATE" theme="simple" cssStyle="width:70%;"  displayFormat="dd/mm/yy"/></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12" style="text-align: center">
                        <a onclick="ongosearch();" class="sexybutton" href="#"><span><span><span class="search">Search</span></span></span></a> &nbsp;
                        <s:if test="%{FIN_STATUS!=null && FIN_STATUS=='YES'}">                           
                            <a onclick="ongoapp();" class="sexybutton" href="#"><span><span><span class="forward">Approve/Reject</span></span></span></a> &nbsp;
                        </s:if>
                        <s:else>
                            <a onclick="ongonew();" class="sexybutton" href="#"><span><span><span class="edit">New</span></span></span></a> &nbsp;
                            <a onclick="ongoacctn()" class="sexybutton" href="#"><span><span><span class="forward">Control No</span></span></span></a> &nbsp;
                            <a onclick="ongoac()" class="sexybutton" href="#"><span><span><span class="forward">Forward</span></span></span></a> &nbsp;
                            <a onclick="ongodelete()" class="sexybutton" href="#"><span><span><span class="cancel">Delete</span></span></span></a>
                        </s:else>
                        
                    </div>                    
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <table id="fabricnobom" class="table table-bordered table-striped" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <s:if test="%{FIN_STATUS==null ||(FIN_STATUS!=null && FIN_STATUS.length()==0)}">
                                    <th colspan="2" style="text-align: center;">Control No</th>
                                    <th></th>
                                    <th></th>
                                    </s:if>
                                    <s:if test="%{FIN_STATUS!=null && FIN_STATUS=='YES'}">
                                    <th>&nbsp;</th>
                                    <th></th>
                                    <th></th>
                                    </s:if>
                                </tr>
                                <tr>
                                    <th>Sl No</th>
                                    <th>Whlo</th>
                                    <th>Dept</th>
                                    <th>Bill No</th>
                                    <th>Bill Date</th>
                                    <th>Supplier</th>
                                    <th style="text-align: right;width:70px;">Bill Amt.</th>
                                    <th style="width:40px;text-align: center;">Edit</th>
                                    <th style="width:40px;text-align: center">Print</th>
                                    <s:if test="%{FIN_STATUS==null ||(FIN_STATUS!=null && FIN_STATUS.length()==0)}">
                                    <th style="width:70px;"><input type="checkbox" id="HEAD_CREATE_CONTROL" onclick="CheckAll(document.frm.CONTROL_NO_LIST,this);">&nbsp;Create</th>
                                    <th style="width:70px;"><input type="checkbox" id="HEAD_DELETE_CONTROL" onclick="CheckAll(document.frm.DEL_CONTROL_NO_LIST,this);">&nbsp;Delete</th>
                                    <th style="width:90px;"><input type="checkbox" id="HEAD_FROWARD" onclick="CheckAll(document.frm.FORWARD_USER_LIST,this);">&nbsp;A/Cs.</th>
                                    <th style="width:70px;"><input type="checkbox" id="HEAD_DETETE" onclick="CheckAll(document.frm.DELETE_BILL_LIST,this);">&nbsp;Delete</th>
                                    </s:if>
                                    <s:if test="%{FIN_STATUS!=null && FIN_STATUS=='YES'}">
                                        <th style="width:70px;">Control No</th>
                                        <th style="width:70px;"><input type="checkbox" id="HEAD_APPROVE" onclick="CheckAll(document.frm.APPROVE_BILL_LIST,this);">&nbsp;Approve</th>
                                        <th style="width:70px;"><input type="checkbox" id="HEAD_UNAPPROVE" onclick="CheckAll(document.frm.UNAPPROVE_BILL_LIST,this);">&nbsp;Reject</th>
                                    </s:if>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="QUERY_LIST" status="status">
                                    <tr>
                                        <td><s:property value="%{#status.index+1}"/></td>
                                        <td><s:property value="%{BILL_WHLO}"/></td>
                                        <td><s:property value="%{DEPT_DESC}"/></td>
                                        <td><s:property value="%{BILL_NO}"/></td>
                                        <td><s:property value="%{BILL_DATE}"/></td>
                                        <td><s:property value="%{SUPPLIER_DESC+'-'+SUPPLIER_CODE}"/></td>
                                        <td style="text-align: right;width:70px;"><s:property value="%{BILL_AMOUNT}"/></td>
                                        <td style="width:40px;text-align: center;"><s:a href="editCashInvoiceEntryAction.action?MAST_SL_NO=%{SL_NO}&FIN_STATUS=%{FIN_STATUS}"><img src="../bootstrap/img/edit_icon.png"/></s:a></td>
                                        <td style="width:40px;text-align: center;"><a href="#" onclick="pdfval('<s:property value="%{SL_NO}"/>');"><img src="../bootstrap/img/icon_pdf.gif"/></a></td>
                                        <s:if test="%{FIN_STATUS==null ||(FIN_STATUS!=null && FIN_STATUS.length()==0)}">
                                        <td>
                                            &nbsp;
                                            <s:if test="%{REPORT_NO!=null && REPORT_NO.length()>0}">
                                                <s:checkbox name="CONTROL_NO_LIST" id="CONTROL_NO_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" checked="true" disabled="true"/>
                                            </s:if>
                                            <s:else>
                                                <s:checkbox name="CONTROL_NO_LIST" id="CONTROL_NO_LIST_ID%{#status.index}" fieldValue="%{SL_NO}"/>
                                            </s:else>
                                            &nbsp<s:property value="%{REPORT_NO}"/>                                        
                                        </td>
                                        <td>
                                            &nbsp;
                                            <s:if test="%{REPORT_NO!=null && REPORT_NO.length()>0 && FORWARD_DATE!=null && FORWARD_DATE.length()>0}">
                                                <s:checkbox name="DEL_CONTROL_NO_LIST" id="DEL_CONTROL_NO_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" disabled="true"/>
                                            </s:if>
                                            <s:elseif test="%{REPORT_NO!=null && REPORT_NO.length()>0}">
                                                <s:checkbox name="DEL_CONTROL_NO_LIST" id="DEL_CONTROL_NO_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" />
                                            </s:elseif>
                                            <s:else>
                                                <s:checkbox name="DEL_CONTROL_NO_LIST" id="DEL_CONTROL_NO_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" disabled="true"/>
                                            </s:else>
                                        </td>
                                        <td>
                                            &nbsp;
                                            <s:if test="%{REPORT_NO!=null && REPORT_NO.length()>0 && FORWARD_DATE!=null && FORWARD_DATE.length()>0}">
                                                <s:checkbox name="FORWARD_USER_LIST" id="FORWARD_USER_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" checked="true" disabled="true"/>
                                                &nbsp;<s:property value="%{FORWARD_DATE}"/>
                                            </s:if>
                                            <s:elseif test="%{REPORT_NO!=null && REPORT_NO.length()>0}">
                                                <s:checkbox name="FORWARD_USER_LIST" id="FORWARD_USER_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" />
                                            </s:elseif>
                                            <s:else>
                                                <s:checkbox name="FORWARD_USER_LIST" id="FORWARD_USER_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" disabled="true"/>
                                            </s:else>
                                        </td>
                                        <td>
                                            &nbsp;
                                            <s:if test="%{REPORT_NO!=null && REPORT_NO.length()>0}">
                                                <s:checkbox name="DELETE_BILL_LIST" id="DELETE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" disabled="true"/>
                                            </s:if>
                                            <s:else>
                                                <s:checkbox name="DELETE_BILL_LIST" id="DELETE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}"/>
                                            </s:else>
                                        </td>
                                        </s:if>
                                        <s:if test="%{FIN_STATUS!=null && FIN_STATUS=='YES'}">
                                        <td>
                                            &nbsp<s:property value="%{REPORT_NO}"/>                                        
                                        </td>
                                        <td>
                                            &nbsp;
                                            <s:if test="%{ACCOUNT_DATE!=null && ACCOUNT_DATE.length()>0}">
                                                <s:checkbox name="APPROVE_BILL_LIST" id="APPROVE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" checked="true" disabled="true"/>
                                            </s:if>
                                            <s:elseif test="%{REPORT_NO!=null && REPORT_NO.length()>0 && FORWARD_DATE!=null && FORWARD_DATE.length()>0}">
                                                <s:checkbox name="APPROVE_BILL_LIST" id="APPROVE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}"/>
                                            </s:elseif>
                                            <s:else>
                                                <s:checkbox name="APPROVE_BILL_LIST" id="APPROVE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" disabled="true"/>
                                            </s:else>
                                        </td>
                                        <td>
                                            &nbsp;
                                            <s:if test="%{ACCOUNT_DATE!=null && ACCOUNT_DATE.length()>0}">
                                                <s:checkbox name="UNAPPROVE_BILL_LIST" id="UNAPPROVE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" disabled="true"/>
                                            </s:if>
                                            <s:elseif test="%{REPORT_NO!=null && REPORT_NO.length()>0 && FORWARD_DATE!=null && FORWARD_DATE.length()>0}">
                                                <s:checkbox name="UNAPPROVE_BILL_LIST" id="UNAPPROVE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}"/>
                                            </s:elseif>
                                            <s:else>
                                                <s:checkbox name="UNAPPROVE_BILL_LIST" id="UNAPPROVE_BILL_LIST_ID%{#status.index}" fieldValue="%{SL_NO}" disabled="true"/>
                                            </s:else>
                                        </td>
                                        </s:if>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row-fluid">				
                    <div class="span12" style="text-align: center;color:red;font-weight: bold;">
                        <s:actionerror/>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
    <!-- Placed at the end of the document so the pages load faster -->
        <!-- html5.js for IE less than 9 -->
        <!--[if lt IE 9]>
            <script src="../js/html5.js"></script>
        <![endif]-->
        <script src="../bootstrap/js/1.11.4/jquery-ui.js"></script>
        <script src="../bootstrap/datatables/js/jquery.dataTables.min.js"></script>
        <script src="../bootstrap/datatables/plugins/date-uk.js"></script>        
        <script src="../bootstrap/js/jquery_blockUI_new.js"></script>
        <script src="../bootstrap/js/jquery_cookie.js"></script>
        <script type="text/javascript">   
            function pdfval(obno){
                 aa = window.open("CashInvoiceReportAuto.jsp?REPORT_TYPE=PDF&SL_NO="+obno, "printpage", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=non,resizable=no,fullscreen=no,copyhistory=yes,width=400px,height=400px,left=650px,top=130px");aa.focus();
             }
            function ongosearch() {
                $('#formId').attr('action', 'CashInvoiceEntryAction.action');
                $("#formId").submit();
                $('#prepage').show();
            }
            function ongoac() {
                $('#formId').attr('action', 'CashInvoiceEntryAction.action?ACT_FLAG=FORWARD');
                $("#formId").submit();
                $('#prepage').show();
            }
            function ongoacctn() {
                $('#formId').attr('action', 'CashInvoiceEntryAction.action?ACT_FLAG=CONTROL');
                $("#formId").submit();
                $('#prepage').show();
            }
            function ongoapp() {
                var ctrr = 0;
                a=document.frm.APPROVE_BILL_LIST;
                if (a.length > 0)
                {
                    for (var i = 0; i < a.length; i++)
                    {
                        e = a[i];
                        if (!e.disabled && e.checked==true)
                        {
                            ++ctrr;
                        }                       
                    }
                }
                else
                {
                    if (!a.disabled && a.checked==true)
                    {
                        ++ctrr;
                    }                 
                }
                if(ctrr>0){
                    if($('#VCH_DATE').val()==''){
                        alert('Voucher Date cannot be blanked!!!');
                        return false;
                    }
                }
                $('#formId').attr('action', 'CashInvoiceEntryAction.action?ACT_FLAG=APPROVE');
                $("#formId").submit();
                $('#prepage').show();
            }
            function ongodelete() {
                if (confirm('Do you want to delete??')) {
                    $('#formId').attr('action', 'CashInvoiceEntryAction.action?ACT_FLAG=DELETE');
                    $("#formId").submit();
                    $('#prepage').show();
                }
            }
            function ongonew() {
                $('#formId').attr('action', 'newpageCashInvoiceEntryAction.action');
                $("#formId").submit();                
                $('#prepage').show();
            }

            if (typeof window.event != 'undefined') // IE
                document.onkeydown = function() // IE
                {
                    if (event.keyCode == 13)
                    {
                        event.keyCode = 9
                    }
                    var t = event.srcElement.type;
                    var kc = event.keyCode;
                    return ((kc != 8 && kc != 13) || (t == 'text' && kc != 13) ||
                            (t == 'textarea') || (t == 'submit' && kc == 13))
                }
            else
                document.onkeypress = function(e)  // FireFox/Others
                {
                    return tabOnEnter(e.target, e)
                    aaaa = e.keyCode;
                    if (aaaa == 13)
                    {
                    }
                    return true;
                }
        </script>
    
        <script type="text/javascript">
            function validate(){
                if($('#WAREHOUSE_QUERY').val()==''){
                    alert('Please choose Warehouse!!!');
                    $('#WAREHOUSE_QUERY').focus();
                    return false;
                }
                if($('#REQUISITION_QUERY').val()==''){
                    if($('#ORDERTYPE_QUERY').val()==''){
                        if($('#PONO_QUERY').val()==''){
                            alert('Please choose Buyer or Order Type or PO Number!!!');
                            $('#REQUISITION_QUERY').focus();
                            return false;
                        }
                    }
                }
                if($('#LS_QUERY').val()==''){
                    alert('Please enter Lowest Status!!!');
                    $('#LS_QUERY').focus();
                    return false;
                }
                if($('#HS_QUERY').val()==''){
                    alert('Please enter Highest Status!!!');
                    $('#HS_QUERY').focus();
                    return false;
                }
                return true;
            }

            function onSearch(){
                if(validate()){
                    $('#formId').attr('action', 'queryPurchaseOrderAction');
                    $( "#formId" ).submit();
                    document.getElementById('searchbtn').disabled=true;
                    document.getElementById('prepage').style.visibility = '';
                }
            }
            function onEditPO(url){
                $(location).attr('href', url);
                document.getElementById('searchbtn').disabled=true;
                document.getElementById('prepage').style.visibility = '';
            }

            function checkAll(a) {
                if(a.checked) { // check select status
                    $('.checkbox1').each(function() { //loop through each checkbox
                        this.checked = true;  //select all checkboxes with class "checkbox1"               
                });
                }else{
                    $('.checkbox1').each(function() { //loop through each checkbox
                        this.checked = false; //deselect all checkboxes with class "checkbox1"                       
                    }); 
                }
            }
            function CheckAll(a,b)
            {
                if (a.length > 0)
                {
                    for (var i = 0; i < a.length; i++)
                    {
                        if (b.checked)
                        {
                            e = a[i];
                            if (!e.disabled)
                            {
                                e.checked = true;
                            }
                        }
                        else
                        {
                            e = a[i];
                            if (!e.disabled)
                            {
                                e.checked = false;
                            }
                        }
                    }
                }
                else
                {
                    if (b.checked)
                    {
                        if (!a.disabled)
                        {
                            a.checked = true;
                        }
                    }
                    else
                    {
                        if (!a.disabled)
                        {
                            a.checked = false;
                        }
                    }
                }
            }
        </script>        
        <script>
            (function( $ ) {
            $.widget( "custom.combobox", {
              _create: function() {
                this.wrapper = $( "<span>" )
                  .addClass( "custom-combobox" )
                  .insertAfter( this.element );

                this.element.hide();
                this._createAutocomplete();
                //this._createShowAllButton();
              },

              _createAutocomplete: function() {
                var selected = this.element.children( ":selected" ),
                  value = selected.val() ? selected.text() : "";

                this.input = $( "<input type='text'>" )
                  .appendTo( this.wrapper )
                  .val( value )
                  .attr( "id", this.element.attr('id')+"AUTO")
                  .attr( "title", "" )
                  .attr("style", "font-size:10px;")
                  .addClass( "input-medium" )
                  .autocomplete({
                    delay: 0,
                    minLength: 0,
                    source: $.proxy( this, "_source" )
                  })
                  .tooltip({
                    tooltipClass: "ui-state-highlight"
                  });
                  if(this.element.attr('id')=="REQUISITION_QUERY" || this.element.attr('id')=="ORDERTYPE_QUERY"){
                    this.input.css('width','150px');
                  }
                  if(this.element.attr('id')=="ORDERTYPE_QUERY"){
                    this.input.css('width','150px');
                  }

                this._on( this.input, {
                  autocompleteselect: function( event, ui ) {
                    ui.item.option.selected = true;
                    this._trigger( "select", event, {
                      item: ui.item.option
                    });
                  },

                  autocompletechange: "_removeIfInvalid"
                });
              },

              _createShowAllButton: function() {
                var input = this.input,
                  wasOpen = false;

                $( "<a>" )
                  .attr( "tabIndex", -1 )
                  .attr( "title", "Show All Items" )
                  .tooltip()
                  .appendTo( this.wrapper )
                  .button({
                    icons: {
                      primary: "ui-icon-triangle-1-s"
                    },
                    text: false
                  })
                  .removeClass( "ui-corner-all" )
                  .addClass( "custom-combobox-toggle ui-corner-right" )
                  .mousedown(function() {
                    wasOpen = input.autocomplete( "widget" ).is( ":visible" );
                  })
                  .click(function() {
                    input.focus();

                    // Close if already visible
                    if ( wasOpen ) {
                      return;
                    }

                    // Pass empty string as value to search for, displaying all results
                    input.autocomplete( "search", "" );
                  });
              },

              _source: function( request, response ) {
                var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
                response( this.element.children( "option" ).map(function() {
                  var text = $( this ).text();
                  if ( this.value && ( !request.term || matcher.test(text) ) )
                    return {
                      label: text,
                      value: text,
                      option: this
                    };
                }) );
              },

              _removeIfInvalid: function( event, ui ) {

                // Selected an item, nothing to do
                if ( ui.item ) {
                  return;
                }

                // Search for a match (case-insensitive)
                var value = this.input.val(),
                  valueLowerCase = value.toLowerCase(),
                  valid = false;
                this.element.children( "option" ).each(function() {
                  if ( $( this ).text().toLowerCase() === valueLowerCase ) {
                    this.selected = valid = true;
                    return false;
                  }
                });

                // Found a match, nothing to do
                if ( valid ) {
                  return;
                }

                // Remove invalid value
                this.input
                  .val( "" )
                  .attr( "title", "No match found for '"+value + "'" )
                  .tooltip( "open" );
                this.element.val( "" );
                this._delay(function() {
                  this.input.tooltip( "close" ).attr( "title", "" );
                }, 2500 );
                this.input.autocomplete( "instance" ).term = "";
              },

              _destroy: function() {
                this.wrapper.remove();
                this.element.show();
              }
            });
          })( jQuery );

          $(function() {
            $( "#REQUISITION_QUERY" ).combobox();  
            $( "#ORDERTYPE_QUERY" ).combobox();  
          });
        </script>
        <script type="text/javascript">
            document.attachEvent("onkeydown", my_onkeydown_handler)
            function my_onkeydown_handler()
            {
            if(event.keyCode == 116)
            {
            event.keyCode = 0;
            event.returnValue = false;
            }
            }
            $(document).ready(function(){
                $(document).on("contextmenu",function(e){
                    if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA")
                         e.preventDefault();
                 });
             });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#fabricnobom').DataTable( {
                    scrollY: '500px',
                    scrollX: '100%',
                    scrollCollapse: true,
                    paging: false,
                    bFilter: false,
                    bInfo: false,
                    columnDefs: [                        
                        { type: 'date-uk', targets: 5 }                    
                    ]
                } );
            } );
        </script>
    </body>
</html>