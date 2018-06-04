
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
 
     if(document.frm.stype.value=="")
        {
           alert("Please Select Action Type ")
           document.frm.stype.focus();
           return false;
        }
        return true;
       }

            
            function searchdetail()
            {                
                document.frm.action="INVTRACK.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
            {               
                document.frm.action="INVTRACK.action?saveFlag=YES";
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
                                    Invoice Followup Detail </td></tr>
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
                                                <button  id="saveheadId"  class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" >Save</span></span></span></button>&nbsp;
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
                                                <th align="left">Pre Docs</th>
                                                <th align="left">Fwd To Post </th>
                                                <th align="left">77 Date</th>
                                              
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
                                                 <td style="font-size:8pt"><s:property value="pprq_date"/></td>
                                                 <td style="font-size:8pt"><s:property value="pre_docs_sent"/></td>
                                                 
                                                 <td style="font-size:8pt"><s:property value="fwd_date"/></td>
                                                <td style="font-size:8pt"><s:property value="fin_date"/></td>
                                                              
                                                <td style="font-size:8pt"><s:property value="ac_holder"/></td>
                                                <td style="font-size:8pt"><s:property value="merchant"/></td>
                                            </tr> 
                                            <s:hidden name="year" value="%{year}"/>
                                            <s:hidden name="company" value="%{company}"/>
                                            <s:hidden name="inv_no" value="%{inv_no}"/>
                                            <s:hidden name="location" value="%{location}"/>
                                            <s:hidden name="tto_date" value="%{tto_date}"/>
                                            <s:hidden name="to_date" value="%{to_date}"/>
                                            <s:hidden name="etd_date" value="%{etd_date}"/>
                                            <s:hidden name="fwd_custom" value="%{fwd_custom}"/>
                                            <s:hidden name="ship_qty" value="%{ship_qty}"/>
                                            <s:hidden name="pre_docs_sent" value="%{pre_docs_sent}"/>
                                            <s:hidden name="post_docs_sent" value="%{post_docs_sent}"/>
                                            <s:hidden name="awbdate" value="%{awbdate}"/>
                                             
                                             
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
                                
                                <tr   style="background-color: whitesmoke;height: 200pt;">
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="1" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:280.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                             <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="1" width="100%"  id="tablea">
                                                 <td  colspan="4" align="center" style="font-size:12pt;font-weight:bold;color: whitesmoke" > Action Details</td>
                                                 <tr>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left"  >Date</th>
                                                    <th style="font-size:8pt" align="left">Description</th>
                                                    <th style="font-size:8pt" align="left">Remark</th>
                                                    <th style="font-size:8pt" align="left">User</th>
                                                   
                                                  </tr>                                                
                                              
                                                  <tbody> 
                                                    <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="TR_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="TR_DESC"/></td>
                                                            <td style="font-size:8pt"><s:property value="REMARK"/></td>
                                                            <td style="font-size:8pt"><s:property value="USER_ID"/></td>
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                                               <%for(int i=0;i<4;i++){%>
                                                <tr>
                                                    <td></td>
                                                        <td colspan="2"><s:select name="actiontype"   cssStyle="text-transform:uppercase;width:280pt" theme="simple" list="%{TypeList}" headerKey="" headerValue="Select Action Type" listKey="LIST_CODE" listValue="LIST_NAME" /></td> 
                                                        <td colspan="2"> <s:textfield name="actionrem" cssStyle="text-transform:uppercase;width:200pt" theme="simple" maxLength="100"/></td>
                                                        
                                                </tr>
                                              <%}%>
                                      
                                        
                                     </table>
                                        </div>  
                                      </td>
                                       
                                 <td valign="top">
                                   
                                    <table bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                      
                                      <tr valign="top">  
                                      <td> 
                                              <div style="width:100%; overflow:auto; height:140.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                                       <table   border="0" align="center" bgcolor="#8e927e" cellspacing="1" cellpadding="1" width="100%"  id="tablea">
                                                           <td colspan="4" align="center" style="font-size:12pt;font-weight:bold;color:whitesmoke" >Pre Shipment Delay Reason</td>
                                                           <tr>
                                                              <th style="font-size:8pt" align="left">Sl#</th>
                                                              <th style="font-size:8pt" align="left" >Date</th>
                                                              <th style="font-size:8pt" align="left">Description</th>
                                                              <th style="font-size:8pt" align="left">Code</th>
                                                              <th style="font-size:8pt" align="left">User</th>
                                                           </tr>                                                
                                                          <tbody> 

                                                                <s:iterator value="PreDRList" status="st1">
                                                                   <tr bgcolor="#f2f9fb">
                                                                      <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                                      <td style="font-size:8pt"><s:property value="TR_DATE"/></td>
                                                                      <td style="font-size:8pt"><s:property value="TR_DESC"/></td>
                                                                      <td style="font-size:8pt"><s:property value="REMARK"/></td>
                                                                      <td style="font-size:8pt"><s:property value="USER_ID"/></td>
                                                                    </tr>
                                                              </s:iterator>
                                                          </tbody>
                                                          <tr>
                                                              <td  colspan="4"><s:select name="pretype"   cssStyle="text-transform:uppercase;width:380pt" theme="simple" list="%{PreList}" headerKey="" headerValue="Select Pre Delay Reason" listKey="LIST_CODE" listValue="LIST_NAME" /></td> 
                                                          </tr> 
                                                          
                                                          
                                                      </table>
                                              </div> 
                                      <tr>
                                          
                                          
                                          
                                      </tr>
                                          
                                    </td> 
                                    </tr>
                                    <tr>    
                                        <td valign="top">       
                                           <div   style="width:100%; overflow:auto; height:110.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                                       <table border="0" align="center" bgcolor="#8e927e" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                           <td colspan="4" align="center" style="font-size:12pt;font-weight:bold;color:whitesmoke" >Post Shipment Delay Reason</td>
                                                           <tr>
                                                              <th style="font-size:8pt" align="left">Sl#</th>
                                                              <th style="font-size:8pt" align="left" >Date</th>
                                                              <th style="font-size:8pt" align="left">Description</th>
                                                              <th style="font-size:8pt" align="left">Code</th>
                                                              <th style="font-size:8pt" align="left">User</th>

                                                            </tr>                                                
                                                          <tbody> 

                                                                <s:iterator value="PostDRList" status="st1">
                                                                  <tr bgcolor="#f2f9fb">
                                                                      <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                                      <td style="font-size:8pt"><s:property value="TR_DATE"/></td>
                                                                      <td style="font-size:8pt"><s:property value="TR_DESC"/></td>
                                                                      <td style="font-size:8pt"><s:property value="REMARK"/></td>
                                                                      <td style="font-size:8pt"><s:property value="USER_ID"/></td>
                                                                    </tr>
                                                              </s:iterator>
                                                          </tbody>
                                                         <tr>
                                                              <td  colspan="4"><s:select name="posttype"   cssStyle="text-transform:uppercase;width:380pt" theme="simple" list="%{PostList}" headerKey="" headerValue="Select Post Delay Reason" listKey="LIST_CODE" listValue="LIST_NAME" /></td> 
                                                           </tr>  
                                                      </table>
                                                  </div>    
                                               </td>  
                                            </tr> 
                                     </table>
                        
                                           
                                                
                                  </td>
                                 </tr>
                              </table>   

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

