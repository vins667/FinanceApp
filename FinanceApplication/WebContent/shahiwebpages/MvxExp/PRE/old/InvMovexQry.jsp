
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
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
                background-image:url('image/table-gradient.jpg');
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
              
            function searchdetail()
            {                
                document.frm.action="InvMovexQry.action?viewFlag=Yes";
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
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Movex vs Invoice Query [M4]</td></tr>
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
                                            <td class="label-1" style="width:200px">Invoice No: <s:textfield name="searchinv" cssStyle="text-transform:uppercase;width:80pt" theme="simple" maxLength="10"/>
                                            </td>
                                            <td align="left">
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                            </td>
                                            <td align="right">
                                                    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                              </td>
                                        </tr>
                                        
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
                                                <th align="left">Loct</th>
                                                <th align="left">Inv No</th>
                                                <th align="left">Plan No</th>
                                                <th align="left">PCH</th>
                                                <th align="left">Mode</th>
                                                <th align="left">Qnty</th>
                                                <th align="left">Inv Type</th>
                                                <th align="left">Custom Fwd</th>
                                                <th align="left">TTO Date</th>
                                                <th align="left">TO Date</th>
                                                <th align="left">ETD Date</th>
                                                <th align="left">FTP Date</th>
                                                <th align="left">Awb Date </th>
                                                <th align="left">77 Date</th>
                                                <th align="left">A/C Holder</th>
                                                <th align="left">Merchant</th>
                                                <th align="left">Alt UOM</th>
                                                <th align="left">Basic UOM</th>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt"><s:property value="location"/></td>
                                                <td style="font-size:8pt"><s:property value="excs_inv_no"/></td>
                                                 <td style="font-size:8pt"><s:property value="plan_no"/></td>
                                                <td style="font-size:8pt"><s:property value="cost_centre"/></td>
                                                <td style="font-size:8pt"><s:property value="mode_of_ship"/></td>
                                                <td style="font-size:8pt"><s:property value="inv_qty"/></td>
                                                <td style="font-size:8pt"><s:property value="inv_type"/></td>
                                                <td style="font-size:8pt"><s:property value="fwd_custom"/></td>
                                                <td style="font-size:8pt"><s:property value="tto_date"/></td>
                                                <td style="font-size:8pt"><s:property value="to_date"/></td>
                                                <td style="font-size:8pt"><s:property value="etd_date"/></td>
                                                <td style="font-size:8pt"><s:property value="fwd_date"/></td>
                                                <td style="font-size:8pt"><s:property value="awbdate"/></td>
                                                <td style="font-size:8pt"><s:property value="fin_date"/></td>
                                                <td style="font-size:8pt"><s:property value="ac_holder"/></td>
                                                <td style="font-size:8pt"><s:property value="merchant"/></td>
                                                <td style="font-size:8pt"><s:property value="couom"/></td>
                                               <td style="font-size:8pt"><s:property value="basicuom"/></td>
                                                
                                            </tr> 
                                            <s:hidden name="year" value="%{year}"/>
                                            <s:hidden name="company" value="%{company}"/>
                                            <s:hidden name="inv_no" value="%{inv_no}"/>
                                            <s:hidden name="location" value="%{location}"/>
                                            
                                            
                                            
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
                                                <td style="font-size:8pt">Buyer</td>
                                                <td style="font-size:8pt"><s:property value="buyer"/></td>
                                                <td style="font-size:8pt"><s:property value="buyer_addr"/></td>
                                                <td style="font-size:8pt"><s:property value="buyer_name"/></td>
                                                <td style="font-size:8pt"><s:property value="buyer_address"/></td>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt">Consignee</td>
                                                <td style="font-size:8pt"><s:property value="buyer"/></td>
                                                <td style="font-size:8pt"><s:property value="cons_addr"/></td>
                                                <td style="font-size:8pt"><s:property value="cons_name"/></td>
                                                <td style="font-size:8pt"><s:property value="cons_address"/></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr> 
                                

                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="1" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:300.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                             <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="1" width="100%"  id="tablea">
                                             <thead>
                                                 <tr>
                                                 <td></td> <td></td> <td></td> <td></td>
                                                 <td  colspan="3" align="center" style="font-size:10pt;font-weight:bold;color: whitesmoke" >Customer Order</td>
                                                 <td  colspan="3" align="center" style="font-size:10pt;font-weight:bold;color: whitesmoke" >Invoice</td>
                                                 <td  colspan="5" align="center" style="font-size:10pt;font-weight:bold;color: whitesmoke" >Deliver Line</td>
                                                 <td  colspan="4" align="center" style="font-size:10pt;font-weight:bold;color: whitesmoke" >Fin.Voucher </td>
                                                 </tr>
                                                 <tr>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left"  >CO No</th>
                                                    <th style="font-size:8pt" align="left">Line</th>
                                                    <th style="font-size:8pt" align="left">Item No</th>
                                                   <th style="font-size:8pt" align="right">Qty</th>
                                                   <th style="font-size:8pt" align="right">S/PR</th>
                                                   <th style="font-size:8pt" align="right">N/PR</th>
                                                   <th style="font-size:8pt" align="right">Qty</th>
                                                   <th style="font-size:8pt" align="right">S/PR</th>
                                                   <th style="font-size:8pt" align="right">Adjst</th>
                                                   <th style="font-size:8pt" align="right">N/PR</th>
                                                   <th style="font-size:8pt" align="left">Delv No</th>
                                                   <th style="font-size:8pt" align="right">Qty</th>
                                                   <th style="font-size:8pt" align="right">S/PR</th>
                                                   <th style="font-size:8pt" align="right">N/PR</th> 
                                                    <th style="font-size:8pt" align="right">ST</th>
                                                   <th style="font-size:8pt" align="center">Sets</th> 
                                                   <th style="font-size:8pt" align="left">Mvx Inv</th>
                                                   <th style="font-size:8pt" align="left">VCH No.</th> 
                                                   <th style="font-size:8pt" align="left">Err Msg</th> 
                                                  </tr>                                                
                                             </thead>
                                                  <tbody> 
                                                    <s:iterator value="InvList" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="CO_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="CO_LINE"/></td>
                                                            <td style="font-size:8pt"><s:property value="ITEM_NO"/></td>
                                                            <td bgcolor="white" align="right" style="font-size:8pt"><s:property value="CO_QTY"/></td>
                                                            <td bgcolor="white" align="right" style="font-size:8pt"><s:property value="CO_RATE"/></td>
                                                            <td bgcolor="white" align="right" style="font-size:8pt"><s:property value="CO_GR"/></td>
                                                            <td align="right" style="font-size:8pt;"><s:property value="INV_QTY"/></td>
                                                            <td align="right" style="font-size:8pt;"><s:property value="INV_SALEPR"/></td>
                                                            <td align="right" style="font-size:8pt;"><s:property value="ADJUST_FC"/></td>
                                                            <td align="right" style="font-size:8pt;"><s:property value="INV_NETPR"/></td>
                                                               <td style="font-size:8pt;background: #DCDCDC"><s:property value="DEL_NO"/></td>
                                                            <td bgcolor="white" align="right" style="font-size:8pt;"><s:property value="DEL_QTY"/></td>
                                                            <td bgcolor="white" align="right" style="font-size:8pt;"><s:property value="DEL_RATE"/></td>
                                                            <td bgcolor="white" align="right" style="font-size:8pt;"><s:property value="DEL_GR"/></td>
                                                              <td align="right" style="font-size:8pt;background: #DCDCDC;color:blue"><s:property value="STATUS"/></td>
                                                            <td align="center" style="font-size:8pt"><s:property value="SET_PCS"/></td>
                                                             <td style="font-size:8pt"><s:property value="MOVEX_INV"/></td>
                                                         
                                                            <td style="font-size:8pt"><s:property value="VCH_TYPE"/>-<s:property value="VCH_NO"/>-<s:property value="VCH_YEAR"/>
                                                            <td style="font-size:8pt;background: yellow;color: red"><s:property value="MSG1"/></td>
                                                             
                                                            
                                                            </td>
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                                                 <tr> 
                                                     
                                                    <td  colspan="5" style="font-size:8pt;color:white;font-weight: bold;" align="right"><s:property value="%{tco_qty}"/></td>
                                                    <td  colspan="3" style="font-size:8pt;color:white;font-weight: bold;" align="right"><s:property value="%{tinv_qty}"/></td>
                                                    <td  colspan="3" style="font-size:8pt;color:white;font-weight: bold;" align="right">Sale Fob: <s:property value="%{tinv_salefob}"/></td>
                                                    <td  colspan="2" style="font-size:8pt;color:white;font-weight: bold;" align="right"><s:property value="%{tdel_qty}"/></td>
                                                    <td  colspan="3" style="font-size:8pt;color:white;font-weight: bold;" align="right">Sale Fob:<s:property value="%{tdel_salefob}"/></td>
                                                    <td  colspan="2" style="font-size:8pt;color:white;font-weight: bold;" align="right">Diff: <s:property value="%{diff_salefob}"/></td>
                                             
                                                </tr>
                                                <tr>
                                                    <td  colspan="11" style="font-size:8pt;color:white;font-weight: bold;" align="right">Net Fob: <s:property value="%{tinv_netfob}"/></td>
                                                    <td  colspan="5" style="font-size:8pt;color:white;font-weight: bold;" align="right">Net Fob: <s:property value="%{tdel_netfob}"/></td>
                                                    <td  colspan="2" style="font-size:8pt;color:white;font-weight: bold;" align="right">  <s:property value="%{diff_netfob}"/></td>
                                             
                                                </tr>
                                             
                                                 
                                                
                                            </table>   
                                            </div>
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

