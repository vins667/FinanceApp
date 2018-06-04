<%--
    Document   : GroceryStaffRequestEntry
    Created on : Dec 20, 2011, 02:10:53 PM
    Author     : Vivek
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<LINK href="../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<s:url value="../css/stylishbuttons.css"/>" type="text/css" />
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
                background-image:url('../image/table-gradient.jpg');
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
            function frmsubmit()
            {
                var a=document.getElementById("searchplan1");
                var b=document.getElementById("searchinv1");
                var c=document.getElementById("searchfrom");
                var d=document.getElementById("searchto");                
                window.location.href="PreShipPlanMsAction.action?searchplan="+a.value+"&searchinv="+b.value+"&searchfrom="+c.value+"&searchto="+d.value;
                //document.frm.action="PreShipPlanMsAction.action";
                //document.frm.submit();
            }
            function searchdetail()
            {                
                document.frm.action="PreInvDetail.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
            //tool Tip Start
            function showToolTip(text) {
            
                var tt = document.getElementById('toolTipDiv');
                tt.style.visibility = 'visible';
                if (mouseX>700)
                {mouseX=mouseX-600;    }
                if (mouseY>10)
                {mouseY=mouseY-60;    }
                tt.style.top = (mouseY + 40) + "px";
                tt.style.left = (mouseX + 10)+"px";
                tt.innerHTML = unescape(text);
            }
            function hideToolTip() {
                document.getElementById('toolTipDiv').style.visibility = 'hidden';
            }

            var mouseX = 0;
            var mouseY = 0;

            function mouseXY(e) {
                if (IE) {
                    mouseX = event.clientX + document.body.scrollLeft 
                    mouseY = event.clientY + document.body.scrollTop
                } else {
                    mouseX = e.pageX;
                    mouseY = e.pageY;
                }
            }
            // set up mouse movement capture for tool tip placement
            var IE = document.all?true:false
            document.onmousemove=mouseXY;
            //Close Tool Tips
            
            var ns4=document.layers
            var ie4=document.all
            var ns6=document.getElementById&&!document.all
            function hidebox(){
                crossobj=ns6? document.getElementById("showimage") : document.all.showimage
                if (ie4||ns6)
                    crossobj.style.visibility="hidden"
                else if (ns4)
                    document.showimage.visibility="hide"                
            }

            function showbox(){
                crossobj=ns6? document.getElementById("showimage") : document.all.showimage
                if (ie4||ns6)
                    crossobj.style.visibility="visible"
                else if (ns4)
                    document.showimage.visibility="show"
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
            <img alt=""  src="../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Pre Invoice Query</td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="3">
                                        <s:if test='%{premast!=null && premast=="avail"}'>
                                            <tr>
                                                <td class="label-1" >Invoice No : <s:textfield name="searchinv" value="%{excs_inv_no}" cssStyle="text-transform:uppercase;width:100pt" theme="simple" readonly="true" maxLength="10"/></td>
                                                <td class="label-1" >Plan No : <s:textfield name="searchplan" value="%{plan_no}" cssStyle="text-transform:uppercase;width:100pt" theme="simple" readonly="true" maxLength="10"/></td>
                                                <td align="right" width="5%"><input type="button" value="Back" onclick='frmsubmit();' class="submitbutton"/></td>
                                        </s:if>
                                        <s:else>
                                        <tr>
                                            <td class="label-1" width="30%">Invoice No : <s:textfield name="searchinv" cssStyle="text-transform:uppercase;width:150pt" theme="simple" maxLength="10"/></td>
                                            <td class="label-1" width="30%">Plan No : <s:textfield name="searchplan" cssStyle="text-transform:uppercase;width:150pt" theme="simple" maxLength="10"/></td>

                                            <td width="5%"> <input  type="button" name="bnt" style="width:50pt" onclick="searchdetail()" value="Go" class="submitbutton"></td>
                                            <td  width="5%"><input type="button" name="bnt" style="width:50pt" onclick="self.close();" value="Exit" class="submitbutton"></td>
                                        </tr>
                                        </s:else>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        <div style="width:200pt;" class="toolTip" id="toolTipDiv"></div>
                        <div align="center" style="width:100.0%;">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr><td>
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                                <th align="left">Facility</th>
                                                <th align="left">Inv No</th>
                                                <th align="left">Inv Date</th>
                                                <th align="left">Plan No</th>
                                                <th align="left">PCH</th>
                                                <th align="left">Mode</th>
                                                <th align="left">Qnty</th>
                                                <th align="left">Inv Type</th>
                                                <th align="left">Custom Fwd</th>
                                                <th align="left">TTO Date</th>
                                                <th align="left">TO Date</th>
                                                <th align="left">ETD Date</th>
                                                <th align="left">PPRQ</th>
                                                <th align="left">Post Fwd </th>
                                                <th align="left">77 Date</th>
                                                <th align="left">Cnxl</th>
                                                <th align="left">A/C Holder</th>
                                                <th align="left">Merchant</th>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:9pt"><s:property value="location"/></td>
                                                <td style="font-size:9pt"><s:property value="excs_inv_no"/></td>
                                                <td style="font-size:9pt"><s:property value="inv_date"/></td>
                                                <td style="font-size:9pt"><s:property value="plan_no"/></td>
                                                <td style="font-size:9pt"><s:property value="cost_centre"/></td>
                                                <td style="font-size:9pt"><s:property value="mode_of_ship"/></td>
                                                <td style="font-size:9pt"><s:property value="inv_qty"/></td>
                                                <td style="font-size:9pt"><s:property value="inv_type"/></td>
                                                <td style="font-size:9pt"><s:property value="fwd_custom"/></td>
                                                <td style="font-size:9pt"><s:property value="tto_date"/></td>
                                                  <td style="font-size:9pt"><s:property value="to_date"/></td>
                                                  <td style="font-size:9pt"><s:property value="etd_date"/></td>
                                                 <td style="font-size:9pt"><s:property value="pprq_date"/></td>
                                                 <td style="font-size:9pt"><s:property value="fwd_date"/></td>
                                                <td style="font-size:9pt"><s:property value="fin_date"/></td>
                                                <td style="font-size:9pt" align="center"> 
                                                                <s:if test='%{ship_cancel=="Y"}'> 
                                                                    <input type="checkbox" name="ship_cancel" disabled="true" checked="true" style="height: 9pt"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <input type="checkbox" name="ship_cancel" disabled="true" style="height: 9pt"/>
                                                                </s:else>
                                                </td>                  
                                                <td style="font-size:9pt"><s:property value="ac_holder"/></td>
                                                <td style="font-size:9pt"><s:property value="merchant"/></td>
                                            </tr> 
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:9pt" colspan="17" class="label-1">Ship Desc: &nbsp;<font style="color: #000000"><s:property value="SHIP_DESC"/></font></td>
                                                <td><table style="width: 100%">
                                                        <tr>
                                                            <td style="width:50px;" class="sexybutton">
                                                                <s:a href="#" cssStyle="text-decoration: none;cursor:hand;color:SlateBlue" onclick="showbox();">
                                                                    <span><span><img src="../image/modify.jpg"  alt="" />View&nbsp;License</span></span>
                                                                        </s:a>
                                                            </td>
                                                        </tr></table></td>
                                            </tr>
                                        </table>
                                    </td></tr>
                                <tr style="background-color: #f2f9fb;">
                                    <td>
                                        <table bgcolor="#7b97e0" style="margin-top: -2pt" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                                <th align="left">Type</th>
                                                <th align="left">Code</th>
                                                <th align="left">Addres No.</th>
                                                <th align="left">Name</th>
                                                <th align="left">Address</th>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:9pt">Buyer</td>
                                                <td style="font-size:9pt"><s:property value="buyer"/></td>
                                                <td style="font-size:9pt"><s:property value="buyer_addr"/></td>
                                                <td style="font-size:9pt"><s:property value="buyer_name"/></td>
                                                <td style="font-size:9pt"><s:property value="buyer_address"/></td>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:9pt">Consignee</td>
                                                <td style="font-size:9pt"><s:property value="buyer"/></td>
                                                <td style="font-size:9pt"><s:property value="cons_addr"/></td>
                                                <td style="font-size:9pt"><s:property value="cons_name"/></td>
                                                <td style="font-size:9pt"><s:property value="cons_address"/></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr> 
                                <tr style="background-color:#006699;">
                                    <td style="width: 100%">
                                        <table cellpadding="0" cellspacing="0" align="center" width="100%">
                                            <tr >
                                                <td width="50%" style="vertical-align: top">
                                                    <table border="0" width="100%" style="background-color:#006699;" cellspacing="1" cellpadding="2">
                                                        <s:if test='%{ship_type=="F"}'>                                                            
                                                            <tr  style="background-color: #f2f9fb"  align="center"  onmouseover="showToolTip('<s:property value="%{tooltip1}"/>');" onmouseout="hideToolTip();">
                                                                <td colspan="11" id="blinkingblink" class="label-1">
                                                                    First Sale
                                                                </td>
                                                            </tr>
                                                        </s:if> 
                                                        <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >CHA Agent:</td><td colspan="10" style="background-color: #f2f9fb;font-size:9pt; " ><s:property value="CHA_NAME"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Forwarder:</td><td colspan="10" style="background-color: #f2f9fb;font-size:9pt; " ><s:property value="FWD_NAME"/></td>
                                                        </tr>
                                                          <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Clearance Port:</td><td colspan="10" style="background-color: #f2f9fb;font-size:9pt; " > <s:property value="CLR_PORT"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Loading Port:</td><td colspan="10" style="background-color: #f2f9fb;font-size:9pt; " > <s:property value="LOADING_PORT"/></td>
                                                        </tr>
                                                  
                                                        <s:if test="%{CRNCY_CODE=='INR' && MANUF_STATE!=null}">
                                                            <tr style="background-color: #f2f9fb">
                                                                <td class="label-1"  style="vertical-align: top;">Currency:</td><td style="vertical-align: top;"><font style="color: #000000">&nbsp;<s:property value="CRNCY_CODE"/></font></td>
                                                                <td class="label-1"  style="vertical-align: top;">Tax&nbsp;Type:</td><td style="vertical-align: top;"><font style="color: #000000">&nbsp;<s:property value="TAX_TYPE"/></font></td>
                                                                <td class="label-1"  style="vertical-align: top;">Tax&nbsp;Percent:</td><td style="vertical-align: top;"><font style="color: #000000">&nbsp;<s:property value="TAX_PERCENT"/></font></td>
                                                                <td class="label-1"  style="vertical-align: top;">Tax&nbsp;Code:</td><td style="vertical-align: top;"><font style="color: #000000">&nbsp;<s:property value="TAX_CODE"/></font></td>
                                                                <td class="label-1"  style="vertical-align: top;">Tax&nbsp;Cal&nbsp;Per:</td><td style="vertical-align: top;"><font style="color: #000000">&nbsp;<s:property value="TAX_CAL_PER"/></font></td>
                                                            </tr> 
                                                        </s:if>
                                                        
                                                            <tr style="background-color: #f2f9fb"><td></td><td colspan="10" class="label-1">&nbsp;</td></tr>
                                                    </table>
                                                </td>
                                                <td width="50%"  style="vertical-align: top">
                                                    <table border="0" width="100%" style="background-color:#006699;" cellspacing="1" cellpadding="2">
                                                         <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Discharge:</td><td colspan="10" style="background-color: #f2f9fb;font-size:9pt" ><s:property value="DISCHARGE"/></font></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Destination:</td><td  colspan="10" style="background-color: #f2f9fb;font-size:9pt" ><s:property value="DESTI_CODE"/></font></td>
                                                        </tr>
                                                     
                                                        <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Origin Cntry:</td><td  colspan="10" style="background-color: #f2f9fb;font-size:9pt" ><s:property value="CNTRY_ORIGIN"/></font></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Destination Cntry:</td><td colspan="10"  style="background-color: #f2f9fb;font-size:9pt" ><s:property value="DESTI_CNTRY"/></font></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" style="background-color: #B0C4DE;width:120px;"  class="label-1"  >Discharge Cntry:</td><td colspan="10"  style="background-color: #f2f9fb;font-size:9pt" ><s:property value="DIS_CNTRY"/></font></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr style="background-color: whitesmoke;height: 240pt;">
                                    <td>
                                        <div class="div1" style="width:100.0%; overflow:auto; height:240.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            <table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                <tr class="tr1">
                                                    <th style="font-size:8pt" align="left" height="25pt">CO No</th>
                                                    <th style="font-size:8pt" align="left"  height="25pt">Line</th>
                                                    <th style="font-size:8pt" align="left">Item No</th>
                                                    <th style="font-size:8pt" align="left">Unit</th>
                                                    <th style="font-size:8pt" align="right">Qty Endors</th>
                                                    <th style="font-size:8pt" align="right">Qty Kgs</th>
                                                    <th style="font-size:8pt" align="right">Price Fc</th>
                                                    <th style="font-size:8pt" align="right">Price Misc</th>
                                                    <th style="font-size:8pt" align="right">Adjust Fc</th>
                                                    <th style="font-size:8pt" align="right">Net Price</th>
                                                    <th style="font-size:8pt" align="right">Fob Fc</th>
                                                    <th style="font-size:8pt" align="right">Gr Decl Amt</th>
                                                    <th style="font-size:8pt" align="left">Dbk SlNo</th>
                                                     <th style="font-size:8pt" align="left">Buyer PO#</th>
                                                    <th style="font-size:8pt" align="left">Buyer Style</th>
                                                    <th style="font-size:8pt" align="left">Description</th>
                                                </tr>                                                
                                                <tbody>
                                                    <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="0"/>
                                                    <s:set id="TOT_QTY_KGS" name="TOT_QTY_KGS" value="0"/>                                                    
                                                    <s:set id="TOT_FOB_FC" name="TOT_FOB_FC" value="0"/>
                                                    <s:set id="TOT_GR_DECL_AMT" name="TOT_GR_DECL_AMT" value="0"/>
                                                    <s:iterator value="EI_ENDORS_DTLS_LIST" status="listid">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="CO_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="CO_LINE"/></td>
                                                            <td style="font-size:8pt"><s:property value="ITEM_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="UNIT"/></td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="QTY_ENDORS"/>
                                                                <s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="%{#TOT_QTY_ENDORS+QTY_ENDORS}"/>
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="QTY_KGS"/>
                                                                <s:set id="TOT_QTY_KGS" name="TOT_QTY_KGS" value="%{#TOT_QTY_KGS+QTY_KGS}"/>
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="PRICE_FC"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="PRICE_MISC"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="ADJUST_FC"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="NET_PRICE"/>                                                                
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="FOB_FC"/>
                                                                <s:set id="TOT_FOB_FC" name="TOT_FOB_FC" value="%{#TOT_FOB_FC+FOB_FC}"/>
                                                            </td>
                                                            <td style="font-size:8pt" align="right">
                                                                <s:property value="GR_DECL_AMT"/>
                                                                <s:set id="TOT_GR_DECL_AMT" name="TOT_GR_DECL_AMT" value="%{#TOT_GR_DECL_AMT+GR_DECL_AMT}"/>
                                                            </td>
                                                            <td style="font-size:8pt"><s:property value="DBK_SLNO"/></td>
                                                             <td style="font-size:8pt"><s:property value="PRE_PRINT_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="TOKEN_NO"/></td>
                                                             <td style="font-size:8pt"><s:property value="DESCRIPTION"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                </tbody>
                                                <tr bgcolor="#f2f9fb">
                                                    <td style="font-size:8pt" colspan="4" align="right" class="label-1">Total</td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_QTY_ENDORS}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#TOT_QTY_KGS}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"></td>
                                                     <s:text name="product.eff" var="fcvalue" >
                                                                 <s:param name="fcvalue" value="%{TOT_FOB_FC}"/>
                                                    </s:text> 
                                                     <s:text name="product.eff" var="grdecl" >
                                                                 <s:param name="grvalue" value="%{TOT_GR_DECL_AMT}"/>
                                                    </s:text> 
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#fcvalue}"/></td>
                                                    <td style="font-size:8pt;color:#9400D3;font-weight: bold;" align="right"><s:property value="%{#grdecl}"/></td>
                                                    <td style="font-size:8pt"></td>
                                                    <td style="font-size:8pt" align="right"></td>
                                                    <td style="font-size:8pt"></td>
                                                    <td style="font-size:8pt"></td>
                                                  
                                                </tr>
                                            </table>
                                        </div>                                                 
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td></tr>
            </table>
            <div id="showimage" style="position:absolute;width:300px; height:100px;right:10px;top:140px;visibility:hidden;">

                <table border="0" bgcolor="#000080" cellspacing="0" cellpadding="2">
                    <tr> 
                        <td width="100%">
                            <table border="0" width="100%" cellspacing="0" cellpadding="0"  height="100%">
                                <tr>
                                    <td id="dragbar" style="cursor:hand; cursor:pointer" width="100%" ><ilayer width="100%" ><layer width="100%"  ><font face="Verdana"
                                            color="#FFFFFF">&nbsp;<strong><small>License Detail</small></strong></font></layer></ilayer></td>
                        <td style="cursor:hand"><a href="#" onClick="hidebox();return false"><img src="../image/x11_close_1.gif"  width="15px"  height="15px" border=0></a></td>
                    </tr>
                    <tr>
                        <td width="100%" bgcolor="#FFFFFF" style="padding:0px;text-align: center;font-size: 12pt;" colspan="2">
                            <!--Start Div-->
                            <div class="div1" style="width:100.0%; overflow:auto; height:100.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                <table border="0"   align="center" bgcolor="DarkSalmon" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                    <tr class="tr1">
                                        <th style="font-size:8pt;width: 100px;" align="left" height="25pt">Ref Type</th>
                                        <th style="font-size:8pt;width: 150px;" align="left"  height="25pt">Ref No</th>
                                    </tr>
                                    <tbody>
                                        <s:if test='%{EI_ENDORS_LC_LIC_DTLS_LIST.size()==0}'>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt;color:red;" colspan="2" align="center">No record found</td>
                                            </tr>
                                        </s:if>
                                        <s:else>
                                            <s:iterator value="EI_ENDORS_LC_LIC_DTLS_LIST" status="listid1">
                                                <tr bgcolor="#f2f9fb">
                                                    <td style="font-size:8pt"><s:property value="REF_TYPE"/></td>
                                                    <td style="font-size:8pt"><s:property value="REF_NO"/></td>
                                                </tr>
                                            </s:iterator>
                                        </s:else>
                                    </tbody>
                                </table>
                            </div>
                            <!--end div-->
                            <!--iframe src="" name="popupframe" width="860px" frameborder="1" height="440px" scrolling="1"  ></iframe-->

                        </td>
                    </tr>
                </table>
                </td>
                </tr>
                </table>
            </div>
    <s:hidden name="searchplan1" id="searchplan1" value="%{searchplan1}"/>
    <s:hidden name="searchinv1" id="searchinv1" value="%{searchinv1}"/>
    <s:hidden name="searchfrom" id="searchfrom" value="%{searchfrom}"/>
    <s:hidden name="searchto" id="searchto" value="%{searchto}"/>
            <script type="text/javascript">
                // From: http://www.webdeveloper.com/forum/showthread.php?p=1067491#post1067491

                var interval = setInterval(one_second,1000);
                var color_one = '#F8F8FF';
                var color_two = '#E9967A';
                var color_flag = true;
                function one_second() {
                    if (color_flag) { document.getElementById('blinkingblink').style.color=color_one;
                        document.getElementById('blinkingblink').style.backgroundColor=color_two;}
                    else { document.getElementById('blinkingblink').style.color=color_two;
                        document.getElementById('blinkingblink').style.backgroundColor=color_one;}
                    color_flag = !color_flag;
                }
            </script>                                                    
        </form>                    


    </body>

</html>

