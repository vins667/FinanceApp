<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<LINK href="../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />

<html>
    <head  
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
                    document.frm.action="PostQuery.action?viewFlag=Yes";
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
                                    Post Shipment Query</td></tr>
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
                                             <td class="label-1" align="left" ><s:textfield name="searchitem"   cssStyle="text-transform:uppercase;width:100pt" theme="simple"  />&nbsp;&nbsp;&nbsp;&nbsp;
                                                 <input type="radio" name="searchtype" value="INV"  checked="true"> &nbsp;Invoice No
                                                        <input type="radio" name="searchtype" value="SB">&nbsp;S/B No
                                                        <input type="radio" name="searchtype" value="AWB">&nbsp;Awb No&nbsp;&nbsp;
                                                        <input type="radio" name="searchtype" value="HAWB">&nbsp;H/Awb No&nbsp;&nbsp;
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
                        
                        <div  align="center" style="width:100.0%;">
                            <table width="100%" cellpadding="0" cellspacing="0"  border="1">
                                <tr><td>
                                     <div style="width:100%; height:100pt; overflow:auto; position:relative; "  >
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                             
                                                <tr>   
                                                 <th align="left">Sl #</th>
                                                 <th align="left">Awb No</th>
                                                 <th align="left">H/Awb No</th>
                                                 <th align="left">Awb Date</th>
                                                 <th align="left">77 Date</th>
                                                 <th align="left">Term#</th>
                                                 <th align="left">S/B No</th>
                                                 <th align="left">S/B Date</th>
                                                 <th align="left">Let Exp Dt.</th>
                                                 <th align="left">Invoice No</th>
                                                 <th align="left">Buyer</th>
                                                 <th align="left">Port</th>
                                                 <th align="left">Destn</th>
                                                 <th align="right">Qnty</th>
                                                 <th align="left">Currency</th>
                                                 <th align="right">Fob Amt</th>
                                                 <th align="right">GR Decl</th>
                                                 <th align="right">INR Conv</th>
                                                 <th align="right">DBK Conv</th>
                                                 <th align="right">Folder#</th>
                                                 
                                            </tr>
                                           
                                              <s:iterator value="InvList" id="itr" status="st">
                                                <tr bgcolor="white">
                                                 <td style="font-size:9pt"><s:property  value="%{#st.index+1}"/></td>
                                                 <td style="font-size:9pt"><s:property value="AWB_NO" /></td>   
                                                 <td style="font-size:9pt"><s:property value="HAWB_NO" /></td> 
                                                 <td style="font-size:9pt"><s:property value="AWB_DATE" /></td> 
                                                 <td style="font-size:9pt"><s:property value="FIN_DATE" /></td> 
                                                  <td style="font-size:9pt"><s:property value="TERM_NO" /></td> 
                                                 <td style="font-size:9pt"><s:property value="SB_NO" /></td> 
                                                 <td style="font-size:9pt"><s:property value="SB_DATE" /></td> 
                                                 <td style="font-size:9pt"><s:property value="LET_EXP_DATE" /></td> 
                                                 <td style="font-size:9pt"><s:property value="INVOICE_NO" /></td> 
                                                 <td style="font-size:9pt"><s:property value="BUYER" /></td> 
                                                 <td style="font-size:9pt"><s:property value="PORT" /></td> 
                                                 <td style="font-size:9pt"><s:property value="DESTI_CNTRY" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="QNTY" /></td> 
                                                 <td style="font-size:9pt"><s:property value="CRNCY" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="FOB_AMT" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="GR_DECL" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="INR_CONV" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="DBK_CONV" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="FOLDER_NUMB" /></td> 
                                                  
                                                </tr>
                                                </s:iterator> 
                                               </table>
                                        </div>
                                    </td></tr> 
                                 <tr>     
                                 <td style="font-size:10pt; font-weight: bold">Invoice Details :-
                                    <div style="width:100%; height:200pt; overflow:auto; position:relative; "  >   
                                       <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                               <th align="left">Sl #</th>
                                                <th align="left">Invoice No.</th>
                                                <th align="left">CO No.</th>
                                                <th align="left">CO  Line</th>
                                                <th align="left">Item</th>
                                                <th align="left">Buyer PO</th>
                                                <th align="left">Buyer Style</th>
                                                <th align="left">UOM</th>
                                                <th align="right">Qty</th>
                                                <th align="right">KGS</th>
                                                <th align="left">Crncy</th>
                                                <th align="right">Price FC</th>
                                                <th align="right">Price Misc</th>
                                                <th align="right">Price Net</th>
                                                <th align="left">Dbk Sl#</th>  
                                                <th align="left">Str Sl#</th>
                                                <th align="left">Inv Desc</th>
                                                
                                             </tr>
                                            
                                                <s:iterator value="INVDETAIL" status="st" >
                                                     <tr bgcolor="#f2f9fb">
                                                       
                                                            <td style="font-size:8pt"><s:property value="%{#st.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="ALL_NO"/></td>
                                                            <td style="font-size:8pt "><s:property value="CO_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="CO_LINE"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="ITEM_NO"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="BPO"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="STYLE"/></td>
                                                            <td style="font-size:8pt" align="left"><s:property value="UOM"/></td>
                                                          
                                                            <td style="font-size:8pt" align="right"><s:property value="QTY_ENDORS"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="QTY_KGS"/></td>
                                                             <td style="font-size:8pt" align="left"><s:property value="CRNCY_CODE"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="PRICE_FC"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="PRICE_MISC"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="NET_RPICE"/></td>
                                                            <td style="font-size:8pt" align="left"><s:property value="DBK_SLNO"/></td>
                                                            <td style="font-size:8pt" align="left"><s:property value="STR_SLNO"/></td>
                                                            <td style="font-size:8pt" align="left"><s:property value="INV_DESC"/></td>
                                                     </tr> 
                                                </s:iterator>
                                                
                                               </table>
                                        </div>
                                    </td>
                                </tr>
                                 <tr>     
                                 <td style="font-size:10pt; font-weight: bold">DBK/STR Details :-
                                    <div style="width:100%; height:100pt; overflow:auto; position:relative; "  >   
                                       <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                               <th align="left">Sl #</th>
                                                <th align="left">S/B No</th>
                                                <th align="left">S/B Date</th>
                                                <th align="left">Claim Port</th>
                                                <th align="right">Dbk Due</th>
                                                <th align="right">Dbk Recv</th>
                                                <th align="right">Dbk Supl</th>
                                                <th align="right">Dbk W/Off</th>
                                                <th align="right">Str Due</th>
                                                <th align="right">Str Recv</th>
                                                <th align="right">Str W/Off</th>
                                             </tr>
                                           
                                                <s:iterator value="DBKLIST" status="st" >
                                                     <tr bgcolor="#f2f9fb">
                                                       
                                                            <td style="font-size:8pt"><s:property value="%{#st.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="SB_NO"/></td>
                                                            <td style="font-size:8pt "><s:property value="SB_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="CLAIM_PORT"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="DBK_DUE"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="DBK_RECV"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="DBK_SUPL"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="DBK_WOFF"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="STR_DUE"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="STR_RECV"/></td>
                                                            <td style="font-size:8pt" align="right"><s:property value="STR_WOFF"/></td>
                                                     </tr> 
                                                </s:iterator>
                                                
                                               </table>
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

