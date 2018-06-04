
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
            
 
     

            
            function searchdetail()
            {                
                document.frm.action="RexUpdationA.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
            {               
                document.frm.action="update1RexUpdationA.action";
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
            
               function copyREX()
            {
              if((document.frm.REXHS_COPY.value=="" && document.frm.REXDESC_COPY.value=="" ) )
                   {
                      alert("Please Enter REX DESC & HSCODE TO COPY ")
                      document.frm.REXDESC_COPY.focus();
                      return false;
                   }  
                     var REXHSCODE=document.frm.REXHSCODE;
                     var REXHSDESC=document.frm.REXHSDESC;
                     var REX_TYPE=document.frm.REX_TYPE;
                  if(document.getElementById("REXDESC_COPY").value!="" && document.getElementById("REXHS_COPY").value!="")
                    { 
                    for(i=0; i<REXHSCODE.length; i++) 
                    {
                      REXHSCODE[i].value=  document.getElementById("REXHS_COPY").value;
                      REXHSDESC[i].value=  document.getElementById("REXDESC_COPY").value;
                      REX_TYPE[i].value=  document.getElementById("REXTYPE_COPY").value;
                    }
                   }
               } 
            
                function copySelectedREX(z) 
                {     
                  
                if(document.getElementById("REXHS_COPY").value!="")
                 {   
                     document.getElementById('REXHSCODE'+z).value=document.getElementById("REXHS_COPY").value;
                 } 
                 if(document.getElementById("REXDESC_COPY").value!="")
                 {   
                     document.getElementById('REXHSDESC'+z).value=document.getElementById("REXDESC_COPY").value;
                 } 
                  if(document.getElementById("REXTYPE_COPY").value!="")
                 {   
                     document.getElementById('REX_TYPE'+z).value=document.getElementById("REXTYPE_COPY").value;
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
            <s:hidden id="flag4" name="flag4" value="%{flag4}" />
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Invoice Rex Updation</td></tr>
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
                                            
                                             <td align="right" >
                                                 <s:if test="%{AUTH_DATE==null}">
                                                   <button  id="saveheadId"  class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                 
                                                 </s:if>  
                                                 <s:else>
                                                      <button  id="saveheadId" disabled="true" class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                  
                                                 </s:else>
                                                  
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
                                                 <td  align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke" > Rex Desc:
                                                       <s:textfield name="REXDESC_COPY" id="REXDESC_COPY" value="%{REXDESC_COPY}"  cssStyle="text-transform:uppercase;width:300pt" theme="simple" />
                                                 </td> 
                                                  <td  align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke" >Rex HS :
                                                       <s:textfield name="REXHS_COPY" id="REXHS_COPY" value="%{REXHS_COPY}"  cssStyle="text-transform:uppercase;width:80pt;" theme="simple" />
                                                        <input type="checkbox" value="Y" name="hsrex"   title="Copy to All Lines" onclick="copyREX()" /> 
                                                 </td>
                                                 <td  align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke" > Rex Type:
                                                        <s:select type="text" name="REXTYPE_COPY" id="REXTYPE_COPY" list="# {'W':'W','P':'P'}"  cssStyle="width:60pt" class="texts" theme="simple" />
                       
                                                 </td> 
                                                   <td align="left" style="font-size:10pt;font-weight:bold;color: whitesmoke">Auth Date :<sx:datetimepicker id="AUTH_DATE" name="AUTH_DATE" value="%{AUTH_DATE}" endDate="%{MAXDATE}"  cssStyle="text-transform:uppercase;width:80pt"  displayFormat="dd/MM/yyyy"/>

                                                </td> 
                                               </table>
                                               <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                            
                                                 
                                                 <tr>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left" >CO No</th>
                                                    <th style="font-size:8pt" align="left">Line#</th>
                                                    <th style="font-size:8pt" align="left">Item #</th>
                                                    <th style="font-size:8pt" align="left">Buyer PO#</th>
                                                    <th style="font-size:8pt" align="left">Style</th>
                                                    <th style="font-size:8pt" align="left">Catg</th>
                                                    <th style="font-size:8pt" align="left">UOM</th>
                                                    <th style="font-size:8pt" align="right">Qnty</th>
                                                    <th style="font-size:8pt" align="left">Crncy</th>
                                                    <th style="font-size:8pt" align="right">Net Price</th>
                                                    <th style="font-size:8pt" align="Left">Rex HS Code</th>
                                                    <th style="font-size:8pt" align="left">Rex HS Desc</th>
                                                    <th style="font-size:8pt" align="left">Rex Type</th>
                                                    <th style="font-size:8pt" align="left">Copy</th>
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                    <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:textfield name="CONO" id="CONO%{#st1.index+1}" value="%{CO_NO}" readonly="true" cssStyle="text-align:right;width:70pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt"><s:textfield name="COLINE" id="COLINE%{#st1.index+1}" value="%{CO_LINE}" readonly="true" cssStyle="text-align:right;width:40pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt"><s:textfield name="ITEMNO" id="ITEMNO%{#st1.index+1}" value="%{ITEM_NO}" readonly="true" cssStyle="text-align:right;width:60pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt"><s:textfield name="BPONO" id="BPONO%{#st1.index+1}" value="%{BPO}" readonly="true" cssStyle="text-align:right;width:60pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt"><s:textfield name="STYLENO" id="STYLENO%{#st1.index+1}" value="%{STYLE}" readonly="true" cssStyle="text-align:right;width:40pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt"><s:textfield name="CATEGORYNO" id="CATEGORYNO%{#st1.index+1}" value="%{CATEGORY}" readonly="true" cssStyle="text-align:right;width:40pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt"><s:textfield name="UOMNO" id="UOMNO%{#st1.index+1}" value="%{UOM}" readonly="true" cssStyle="text-align:right;width:40pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt"><s:textfield name="QTYENDORS" id="QTYENDORS%{#st1.index+1}" value="%{QTY_ENDORS}" readonly="true" cssStyle="text-align:right;width:40pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align:right"><s:textfield name="CRNCYCODE" id="CRNCYCODE%{#st1.index+1}" value="%{CRNCY_CODE}" readonly="true" cssStyle="text-align:right;width:40pt;" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield name="NETRPICE" id="NETRPICE%{#st1.index+1}" value="%{NET_RPICE}" readonly="true" cssStyle="text-align:right;width:40pt;" theme="simple"/></td>
                                                            <s:if test="%{INVFLAG!=null}">
                                                                <td style="font-size:8pt"><s:textfield name="REXHSCODE" id="REXHSCODE%{#st1.index+1}" value="%{INV_HSCODE}" cssStyle="text-transform:uppercase;width:80pt;background-color:#F9F5A6;" theme="simple" /></td>
                                                                <td style="font-size:8pt"><s:textfield name="REXHSDESC" id="REXHSDESC%{#st1.index+1}" value="%{INV_DESC}" cssStyle="text-transform:uppercase;width:320pt;background-color:#F9F5A6;"  theme="simple" /></td>
                                                            </s:if> 
                                                            <s:else>
                                                                  <td style="font-size:8pt"><s:textfield name="REXHSCODE" id="REXHSCODE%{#st1.index+1}" value="%{REX_CODE}" cssStyle="text-transform:uppercase;width:80pt;" theme="simple" /></td>
                                                                  <td style="font-size:8pt"><s:textfield name="REXHSDESC" id="REXHSDESC%{#st1.index+1}" value="%{REX_DESC}" cssStyle="text-transform:uppercase;width:320pt;" theme="simple" /></td>
                                                            </s:else>
                                                               
                                                            <td style="font-size:8pt"><s:textfield name="REX_TYPE" id="REX_TYPE%{#st1.index+1}" value="%{REX_TYPE}" cssStyle="text-transform:uppercase;width:50pt;" theme="simple" /></td>
                                                          
                                                            <td style="font-size:8pt"><input type="checkbox" value="Y" name="cpselectREX"  title="Copy Selected Lines" onclick="copySelectedREX(<s:property value="%{#st1.index+1}"/>)" /> </td>
                                                            <s:hidden id="SRNO" name="SRNO" value="%{SR_NO}"/>
                                                            <s:hidden  id="YEAR1" name="YEAR1" value="%{YEAR}"/>
                                                            <s:hidden id="COMPANY1" name="COMPANY1" value="%{COMPANY}"/>
                                                            <s:hidden id="INVNO1" name="INVNO1" value="%{INVNO}"/>
                                                          </tr>
                                                    </s:iterator>
                                                               
                                                </tbody>
                                    
                                                
                                            </table> 
                                                   
                                                    <s:textfield name="text1"   value="Invoice Detail" cssStyle=" width:90pt;background-color:#F9F5A6;" theme="simple" />
                                                    <br>
                                                    <s:textfield name="text2"   value="Rex Detail" cssStyle=" width:90pt;" theme="simple" />                                                   
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

