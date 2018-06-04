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
        <link id="themes" href="../css/theme-chayam.css" rel="stylesheet">
        <!--[if IE 7]>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/ie/ie7.css" />
        <![endif]-->
        <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/ie/ie8.css" />
        <![endif]-->
        <!--[if IE 9]>
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/ie/ie9.css" />
        <![endif]-->
    </head>
    <BODY style="padding: 0px;">
        <DIV align="center" id="prepage" class="centered" style="visibility:  hidden;">
            <img src="../bootstrap/img/loading.gif"/><br/><span style="font-weight: bold;color:#4D4D4D;font-size: 30px;">&nbsp;&nbsp;Please Wait...</span>			
        </DIV>

        <FORM action=# id="formId">
            <s:hidden name="COMPANY" id="COMPANY"/>
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
                                                        Customer Code/Desc<span class="main-mendad">*</span> <s:textfield name="SUPPLIER_QUERY" id="SUPPLIER_QUERY" cssClass="input-medium" theme="simple"/>
                                                    </td>
                                                    <td>
                                                        <a class="sexybutton" href="#" onclick="onSearch();"><span><span><span class="search">Search</span></span></span></a>

                                                    </td>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <TABLE id=dataTable class=mt width="100%">
                                    <TBODY>
                                        <TR>
                                            <TH>Sl.No</TH>
                                            <TH>Supplier</TH>
                                            <TH>Supplier Name</TH>
                                            <TH>Address</TH>
                                            <TH style="width:30px;"></TH>				    
                                        </TR>
                                        <s:iterator value="SUPPLIER_LIST" status="status">					  	
                                            <s:if test="%{#status.index%2==0}">
                                                <TR style="background-color: #ffffff;">	
                                                </s:if>
                                                <s:else>
                                                <TR style="background-color: #FAFAFA;">
                                                </s:else>
                                                <TD><s:property value="%{#status.index+1}"/></TD>
                                                <TD style="width:100px;"><s:property value="%{SUPPLIER_CODE}"/></TD>
                                                <TD style="width:200px;"><s:property value="%{SUPPLIER_DESC}"/></TD>
                                                <TD style="width:350px;">
                                                    <s:property value="%{supplierAddressBean.ADR1+','+supplierAddressBean.ADR2+','+supplierAddressBean.ADR3}"/>
                                                    <s:hidden name="SUPPLIER_CODE" id="SUPPLIER_CODE_ID%{#status.index}" value="%{SUPPLIER_CODE}"/>'
                                                    <s:hidden name="SUPPLIER_DESC" id="SUPPLIER_DESC_ID%{#status.index}" value="%{SUPPLIER_DESC}"/>
                                                    <s:hidden name="GSTF" id="GSTF_ID%{#status.index}" value="%{GSTF}"/>
                                                    <s:hidden name="GSTD" id="GSTD_ID%{#status.index}" value="%{GSTD}"/>
                                                    <s:hidden name="CURR" id="CURR_ID%{#status.index}" value="%{CURR}"/>

                                                    <s:hidden name="RGDT" id="RGDT_ID%{#status.index}" value="%{supplierAddressBean.RGDT}"/>
                                                    <s:hidden name="ADID" id="ADID_ID%{#status.index}" value="%{supplierAddressBean.ADID}"/>
                                                    <s:hidden name="CONM" id="CONM_ID%{#status.index}" value="%{supplierAddressBean.CONM}"/>
                                                    <s:hidden name="ADR1" id="ADR1_ID%{#status.index}" value="%{supplierAddressBean.ADR1}"/>
                                                    <s:hidden name="ADR2" id="ADR2_ID%{#status.index}" value="%{supplierAddressBean.ADR2}"/>
                                                    <s:hidden name="ADR3" id="ADR3_ID%{#status.index}" value="%{supplierAddressBean.ADR3}"/>
                                                    <s:hidden name="ADR4" id="ADR4_ID%{#status.index}" value="%{supplierAddressBean.ADR4}"/>
                                                    <s:hidden name="TOWN" id="TOWN_ID%{#status.index}" value="%{supplierAddressBean.TOWN}"/>
                                                    <s:hidden name="ECAR" id="ECAR_ID%{#status.index}" value="%{supplierAddressBean.ECAR}"/>
                                                    <s:hidden name="PONO" id="PONO_ID%{#status.index}" value="%{supplierAddressBean.PONO}"/>
                                                    <s:hidden name="CSCD" id="CSCD_ID%{#status.index}" value="%{supplierAddressBean.CSCD}"/>
                                                    <s:hidden name="GSTN" id="GSTN_ID%{#status.index}" value="%{supplierAddressBean.GSTN}"/>
                                                </TD>
                                                <TD><img src="../bootstrap/img/add_icon.png" style="width:25px;cursor: pointer;"  onclick="addBuyer('<s:property value="%{#status.index}"/>');"/></TD>
                                            </TR>						
                                        </s:iterator>					  
                                    </TBODY>
                                </TABLE>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">				
                        <div class="span12">
                            <s:if test="%{SUPPLIER_QUERY!=null && SUPPLIER_LIST.size()==0}">
                                <div class="alert alert-danger">Supplier not found!!!</div>
                            </s:if>
                        </div>
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
            fxheaderInit('dataTable', 320);
        </SCRIPT>
        <script type="text/javascript">
            function validate() {
                if ($('#SUPPLIER_QUERY').val() == '') {
                    alert("Supplier can't be empty!!!");
                    $('#SUPPLIER_QUERY').focus();
                    return false;
                }
                if ($('#SUPPLIER_QUERY').val().length < 4) {
                    alert('Please Enter atleast 4 character!!!');
                    $('#SUPPLIER_QUERY').focus();
                    return false;
                }
                return true;
            }

            function onSearch() {
                if (validate()) {
                    $('#formId').attr('action', 'supCashInvoiceEntryAction');
                    $("#formId").submit();
                    document.getElementById('prepage').style.visibility = '';
                }
            }

            function addBuyer(TINDEX)
            {
                var break_exist = 0;
                window.parent.$('.BILL_TYPE_SLNO_CLASS').each(function (index, value) { 
                     if(window.parent.$('#BILL_TYPE_ID'+index).val()!=''){
                         ++break_exist;
                     }
                });
                if(break_exist>0){
                    alert('Supplier cannot change. Breakup already exist!!!');                    
                    window.parent.approveraddidClose();
                    return false;
                }
                id = "SUPPLIER_CODE_ID" + TINDEX;
                id1 = "SUPPLIER_DESC_ID" + TINDEX;
                id2 = "RGDT_ID" + TINDEX;
                id3 = "ADID_ID" + TINDEX;
                id4 = "CONM_ID" + TINDEX;
                id5 = "ADR1_ID" + TINDEX;
                id6 = "ADR2_ID" + TINDEX;
                id7 = "ADR3_ID" + TINDEX;
                id8 = "ADR4_ID" + TINDEX;
                id9 = "TOWN_ID" + TINDEX;
                id10 = "ECAR_ID" + TINDEX;
                id11 = "PONO_ID" + TINDEX;
                id12 = "CSCD_ID" + TINDEX;

                id13 = "GSTF_ID" + TINDEX;
                id14 = "GSTD_ID" + TINDEX;
                id15 = "GSTN_ID" + TINDEX;                
                id16 = "CURR_ID" + TINDEX;

                buyerName = document.getElementById(id).value;
                buyerAdd = document.getElementById(id1).value;
                RGDT = document.getElementById(id2).value;
                ADID = document.getElementById(id3).value;
                CONM = document.getElementById(id4).value;
                ADR1 = document.getElementById(id5).value;
                ADR2 = document.getElementById(id6).value;
                ADR3 = document.getElementById(id7).value;
                ADR4 = document.getElementById(id8).value;
                TOWN = document.getElementById(id9).value;
                ECAR = document.getElementById(id10).value;
                PONO = document.getElementById(id11).value;
                CSCD = document.getElementById(id12).value;

                GSTF = document.getElementById(id13).value;
                GSTD = document.getElementById(id14).value;
                GSTN = document.getElementById(id15).value;
                
                CURR = document.getElementById(id16).value;

                var SUPPLIER = window.parent.document.getElementById('SUPPLIER');
                var SUPPLIER_DESC = window.parent.document.getElementById('SUPPLIER_DESC');
                SUPPLIER.value = buyerName;
                SUPPLIER_DESC.value = buyerAdd;
                if (window.parent.document.getElementById('ADID')) {
                    var SADID = window.parent.document.getElementById('ADID');
                    var SRGDT = window.parent.document.getElementById('RGDT');
                    var SCONM = window.parent.document.getElementById('CONM');
                    var SADR1 = window.parent.document.getElementById('ADR1');
                    var SADR2 = window.parent.document.getElementById('ADR2');
                    var SADR3 = window.parent.document.getElementById('ADR3');
                    var SADR4 = window.parent.document.getElementById('ADR4');
                    var STOWN = window.parent.document.getElementById('TOWN');
                    var SECAR = window.parent.document.getElementById('ECAR');
                    var SPONO = window.parent.document.getElementById('PONO');
                    var SCSCD = window.parent.document.getElementById('CSCD');
                    var SUPPLIER_STATE = window.parent.document.getElementById('SUPPLIER_STATE');


                    var SGSTF = window.parent.document.getElementById('GSTF');
                    var SGSTD = window.parent.document.getElementById('GSTD');
                    var SGSTN = window.parent.document.getElementById('GSTN');
                    
                    var SCURR = window.parent.document.getElementById('CURRENCY');

                    SADID.value = ADID;
                    SRGDT.value = RGDT;
                    SCONM.value = CONM;
                    SADR1.value = ADR1;
                    SADR2.value = ADR2;
                    SADR3.value = ADR3;
                    SADR4.value = ADR4;
                    STOWN.value = TOWN;
                    SECAR.value = ECAR;
                    SPONO.value = PONO
                    SCSCD.value = CSCD;
                    SUPPLIER_STATE.value = ECAR;
                    SCURR.value = CURR;
                    if($.trim(CSCD)!='IN'){
                        window.parent.$('#REVERSE_SRVTAX').attr('checked',true);
                    }else{
                        window.parent.$('#REVERSE_SRVTAX').attr('checked',false);
                    }

                    SGSTF.value = GSTF;
                    SGSTD.value = GSTD;
                    SGSTN.value = GSTN;
                }
                window.parent.approveraddidClose();
            }


        </script>
    </BODY>
</HTML>
