
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
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
            
      function validateinput(){
        var a=document.frm.selall;   
        if(document.frm.ndbk.value=="")
        {
           alert("Please Select DBK Sl No. ")
           a.checked=false;
           document.frm.ndbk.focus();
           return false;
        }
         if(document.frm.nprice.value=="")
        {
           alert("Please Select Price  ")
           a.checked=false;
           document.frm.nprice.focus();
           return false;
        }
        
          if(document.frm.nkgs.value=="")
        {
           alert("Please Select Total Weight ")
           a.checked=false;
           document.frm.nkgs.focus();
           return false;
        }
           return true;
      }
     

            
            function searchdetail()
            {                
                document.frm.action="INVKGSUPD.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
            {               
                document.frm.action="INVKGSUPD.action?saveFlag=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             
             function getDetail()
            {      
                if(validateinput()){
                document.frm.action="INVKGSUPD.action?getFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
                }
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
            <img alt=""  src="../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Invoice KGS Qty Update</td></tr>
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
                                            <td class="label-1" style="width:200px">Invoice No : <s:textfield name="searchinv" cssStyle="text-transform:uppercase;width:80pt" theme="simple" maxLength="10"/>
                                            </td>     
                                            <td align="left">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                            </td>
                                             <td align="right" ><button  id="saveheadId"  class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
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
                                                <th align="left">Post Fwd </th>
                                                 <th align="left">Awb Date</th>
                                                <th align="left">77 Date</th>
                                                <th align="left">Cnxl</th>
                                                <th align="left">A/C Holder</th>
                                                <th align="left">Merchant</th>
                                            </tr>
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt"><s:property value="location"/></td>
                                                <td style="font-size:8pt"><s:property value="excs_inv_no"/></td>
                                                <td style="font-size:8pt"><s:property value="inv_date"/></td>
                                                <td style="font-size:8pt"><s:property value="plan_no"/></td>
                                                <td style="font-size:8pt"><s:property value="cost_centre"/></td>
                                                <td style="font-size:8pt"><s:property value="mode_of_ship"/></td>
                                                <td style="font-size:8pt"><s:property value="inv_qty"/></td>
                                                <td style="font-size:8pt"><s:property value="inv_type"/></td>
                                                <td style="font-size:8pt"><s:property value="fwd_custom"/></td>
                                                <td style="font-size:8pt"><s:property value="tto_date"/></td>
                                                  <td style="font-size:8pt"><s:property value="to_date"/></td>
                                                  <td style="font-size:8pt"><s:property value="etd_date"/></td>
                                                  <td style="font-size:8pt"><s:property value="awbdate"/></td>
                                                  <td style="font-size:8pt"><s:property value="fwd_date"/></td>
                                                <td style="font-size:8pt"><s:property value="fin_date"/></td>
                                                <td style="font-size:8pt" align="center"> 
                                                                <s:if test='%{ship_cancel=="Y"}'> 
                                                                    <input type="checkbox" name="ship_cancel" disabled="true" checked="true" style="height: 9pt"/>
                                                                </s:if>
                                                                <s:else>
                                                                    <input type="checkbox" name="ship_cancel" disabled="true" style="height: 9pt"/>
                                                                </s:else>
                                                </td>                  
                                                <td style="font-size:8pt"><s:property value="ac_holder"/></td>
                                                <td style="font-size:8pt"><s:property value="merchant"/></td>
                                                
                                            </tr> 
                                           
                                              <s:hidden name="year" value="%{year}"/>
                                            <s:hidden name="company" value="%{company}"/>
                                            <s:hidden name="inv_no" value="%{inv_no}"/>
                                            
                                        </table>
                                    </td></tr> 

                                
                                <tr   style="background-color: whitesmoke;height: 350pt;">
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:350.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                             <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                 <td  align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke" > DBK Slno :
                                                       <s:select name="ndbk"   cssStyle="text-transform:uppercase;width:100pt" theme="simple" list="%{DBKLIST}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                                                 </td> 
                                                  <td  align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke" > Price :
                                                       <s:select name="nprice"  cssStyle="text-align:right;width:70pt;" theme="simple" list="%{PRICELIST}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                                                 </td>
                                                   <td  align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke" > Total KGS Qty :
                                                        <s:textfield name="nkgs" cssStyle="text-align:right;width:60pt;" theme="simple" maxLength="10"/>
                                                  </td> 
                                                  <s:if test='%{awbdate==null}'> 
                                                    <td  align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke" >Update :
                                                       <input title="Update KGS Qty" type="CHECKBOX" name="selall" value="Y" onclick="getDetail()">
                                                    </td>
                                                  </s:if>  
                                                    <td style="font-size:8pt;font-weight:bold;color: whitesmoke" ><s:property value="nqty"/></td>
                                                          
                                             </table>
                                               <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                            
                                                 
                                                 <tr>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left"  >CO No</th>
                                                    <th style="font-size:8pt" align="left">Line#</th>
                                                    <th style="font-size:8pt" align="left">Item #</th>
                                                    <th style="font-size:8pt" align="left">Buyer PO#</th>
                                                    <th style="font-size:8pt" align="left">Style</th>
                                                    <th style="font-size:8pt" align="left">Catg</th>
                                                    <th style="font-size:8pt" align="left">UOM</th>
                                                    <th style="font-size:8pt" align="right">Qnty</th>
                                                    <th style="font-size:8pt" align="right">Kgs Qty</th>
                                                    <th style="font-size:8pt" align="left">Crncy</th>
                                                    <th style="font-size:8pt" align="right">Sale Price</th>
                                                    <th style="font-size:8pt" align="right">Misc</th>
                                                    <th style="font-size:8pt" align="right">Net Price</th>
                                                    <th style="font-size:8pt" align="right">GR Disc</th>
                                                    <th style="font-size:8pt" align="left">DBK Sl#</th>
                                                    <th style="font-size:8pt" align="left">STR Sl#</th>
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                    <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="CO_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="CO_LINE"/></td>
                                                            <td style="font-size:8pt"><s:property value="ITEM_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="BPO"/></td>
                                                            <td style="font-size:8pt"><s:property value="STYLE"/></td>
                                                            <td style="font-size:8pt"><s:property value="CATEGORY"/></td>
                                                            <td style="font-size:8pt"><s:property value="UOM"/></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="QTY_ENDORS"/></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="%{QTY_KGS}"/></td>
                                                            <td style="font-size:8pt"><s:property value="CRNCY_CODE"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="PRICE_FC"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="PRICE_MISC"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="NET_RPICE"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="GR_DECL_AMT"/></td>
                                                            <td style="font-size:8pt"><s:property value="DBK_SLNO"/></td>
                                                            <td style="font-size:8pt"><s:property value="STR_SLNO"/></td>
                                                            <s:hidden name="SRNO" value="%{SR_NO}"/>
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                                         </td>
                                   </tr>
                                                
                                            </table>
                                           
                                        </div>  
                                      </td>
                                 </tr>
                              </table>  
                              </tr>
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

