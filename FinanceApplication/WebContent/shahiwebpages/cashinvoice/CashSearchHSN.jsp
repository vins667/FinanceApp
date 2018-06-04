<%-- 
    Document   : CashSearchHSN
    Created on : Aug 8, 2017, 01:28:28 PM
    Author     : Vivek
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Purchase Order Supplier</title>
        <STYLE type=text/css>
            BODY {
                PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma, sans-serif; FONT-SIZE: 0.9em; PADDING-TOP: 0px
            }
            .mt {
                PADDING-BOTTOM: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: Tahoma, sans-serif; FONT-SIZE: 0.9em; PADDING-TOP: 0px
            }
            BODY {
                MARGIN: 0px
            }
            TABLE.mt {
                BORDER-BOTTOM: #cfcfcf 1px solid; BORDER-LEFT: #cfcfcf 1px solid; BACKGROUND-COLOR: transparent; BORDER-SPACING: 0px; BORDER-COLLAPSE: collapse; BORDER-TOP: #cfcfcf 1px solid; BORDER-RIGHT: #cfcfcf 1px solid
            }
            TABLE.mt TH {
                BORDER-BOTTOM: #cfcfcf 1px solid; TEXT-ALIGN: left; BORDER-LEFT: #cfcfcf 1px solid; PADDING-BOTTOM: 1px; BACKGROUND-COLOR: #DFDFDF; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; WHITE-SPACE: normal; BORDER-TOP: #cfcfcf 1px solid; BORDER-RIGHT: #cfcfcf 1px solid; PADDING-TOP: 1px
            }
            TABLE.mt TD {
                BORDER-BOTTOM: #cfcfcf 1px solid; TEXT-ALIGN: left; BORDER-LEFT: #cfcfcf 1px solid; PADDING-BOTTOM: 1px; PADDING-LEFT: 1px; PADDING-RIGHT: 1px; WHITE-SPACE: nowrap; VERTICAL-ALIGN: middle; BORDER-TOP: #FAFAFA 1px solid; BORDER-RIGHT: #cfcfcf 1px solid; PADDING-TOP: 1px
            }
            div.centered {
                position:fixed;
                width:  250px;
                height: 100px;
                left: 50%;
                top:  50%;
                z-index: 999;
                margin-left: -125px; /* 100px/2 = 50px */
                margin-top:  -100px; /* ditto */
            }
        </STYLE>
        <!-- styles -->
        <link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="../bootstrap/css/ShahiButtons/shahibuttons.css" rel="stylesheet">
        <link href="../bootstrap/css/styles.css" rel="stylesheet">
        <link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="../bootstrap/css/icons-sprite.css" rel="stylesheet">
        <link id="themes" href="../bootstrap/css/theme-chayam.css" rel="stylesheet">
        <!--[if IE 7]>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/ie/ie7.css" />
        <![endif]-->
        <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/ie/ie8.css" />
        <![endif]-->
        <!--[if IE 9]>
        <link rel="stylesheet" type="text/css" href="../css/ie/ie9.css" />
        <![endif]-->
    </head>
    <BODY style="padding: 0px;">
        <DIV align="center" id="prepage" class="centered" style="visibility:  hidden;">
            <img src="../bootstrap/img/loading.gif"/><br/><span style="font-weight: bold;color:#4D4D4D;font-size: 30px;">&nbsp;&nbsp;Please Wait...</span>			
        </DIV>

        <FORM action=# method="post" id="formId">
            <s:hidden name="COMPANY" id="COMPANY"/>
            <s:hidden name="T_INDEX" id="T_INDEX" value="%{T_INDEX}"/>
            <div id="main-content">
                <div class="container-fluid">			
                    <div class="row-fluid">				
                        <div class="span12">
                            <div class="widget-block">
                                <div class="widget-content">
                                    <div class="widget-box">
                                        <div style="display:inline;vertical-align: middle;">
                                            <table style="width:100%;">
                                                <tr>
                                                    <td>
                                                        HSN Code<span class="main-mendad">*</span> <s:textfield name="HSN_CODE" id="HSN_CODE" cssClass="input-medium" theme="simple"/>
                                                    </td>
                                                    <TD>
                                                        <button id='searchbtn' class="sexybutton" href="#" onclick="onSearch();"><span><span><span class="search">Search</span></span></span></button>
                                                    </TD>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <TABLE id=dataTable class=mt width="100%">
                                    <TBODY>
                                        <TR>
                                            <TH>Sl.No</TH>
                                            <TH>HSN Code</TH>
                                            <TH>Desc</TH>
                                            <TH style="width:30px;"></TH>				    
                                        </TR>
                                        <s:iterator value="HSN_LIST" status="status">					  	
                                            <s:if test="%{#status.index%2==0}">
                                                <TR style="background-color: #ffffff;">	
                                                </s:if>
                                                <s:else>
                                                <TR style="background-color: #FAFAFA;">
                                                </s:else>
                                                <TD><s:property value="%{#status.index+1}"/></TD>
                                                <TD><s:property value="%{CSNO}"/></TD>
                                                <TD><s:property value="%{TX40}"/></TD>
                                                <TD><img src="../bootstrap/img/add_icon.png" style="width:25px;cursor: pointer;"  onclick="addhsn('<s:property value="CSNO"/>')"/></TD>
                                            </TR>						
                                        </s:iterator>					  
                                    </TBODY>
                                </TABLE>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">				

                    </div>
                </div>
            </div>
        </FORM>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../bootstrap/js/jquery.js"></script>
        <!-- html5.js for IE less than 9 -->
        <!--[if lt IE 9]>
                <script src="../bootstrap/js/html5.js"></script>
        <![endif]-->

        <SCRIPT type=text/javascript src="../bootstrap/js/fxheader.js"></SCRIPT>

        <SCRIPT type=text/javascript>
              fxheaderInit('dataTable', 250);
        </SCRIPT>
        <script type="text/javascript">
            function validate() 
            {
                if ($('#HSN_CODE').val() == '') {
                    alert("HSN Code can't be empty!!!");
                    $('#HSN_CODE').focus();
                    return false;
                }
                return true;
            }

            function onSearch() {
                if(validate())
                {
                    $('#formId').attr('action', 'searchhsnCashInvoiceEntryAction');
                    $("#formId").submit();
                    document.getElementById('prepage').style.visibility = '';
                }
            }

            function addhsn(buyerName)
            {
                var tindex=document.getElementById('T_INDEX');
                var SADID = window.parent.document.getElementById('BILL_AMOUNT_HSN_ID'+tindex.value);
                SADID.value = $.trim(buyerName);
                window.parent.approveraddidClose();
                if(SADID.value!=null && SADID.value.length>0){
                    window.parent.callgstcal(tindex.value);
                }
            }
     </script>
    </BODY>
</HTML>
