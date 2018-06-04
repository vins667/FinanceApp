
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<link href="<s:url value="/css/main.css"/>" rel="stylesheet"type="text/css"/>

<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<link href="<s:url value="css/sexybuttons.css"/>" rel="stylesheet"  type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
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
            if (typeof window.event != 'undefined') // IE
    document.onkeydown = function() // IE
    {

       if(event.keyCode==13)
          {event.keyCode=9}


        var t=event.srcElement.type;
        var kc=event.keyCode;


        return ((kc != 8 && kc != 13) || ( t == 'text' &&  kc != 13 ) ||
            (t == 'textarea') || ( t == 'submit' &&  kc == 13))
    }
else
    document.onkeypress = function(e)  // FireFox/Others
    {
        return tabOnEnter(e.target,e)

        aaaa=e.keyCode;
       if(aaaa==13 )
          {

        
      }
        return true;
  
    }
                  
            
           
             
             function getDetail()
            {      
                 
                document.frm.action="INVGSPPRINT.action?getFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
                
            }
            function GetReport()
              {
                   if (document.frm.REPCH.value==null)
                     {alert ("Please select Report Choice...");
                     return false;}
                    document.frm.action="INVGSPPRINT.action?printFlag=Yes";
                    document.frm.submit();
                  
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
          <table height="86%"  border="0" bgcolor="#f2f2f2" cellpadding="0" align="center" width="100%" >
          <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
         
            <table border=1 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Supporting Document Print</td></tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td> 
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="0" cellpadding="3">
                                        <tr>
                                           <s:if  test="%{REPCH==null}">  
                                                    <th align="left"  align="center" style="background-color: white;" class="label-1">Report :-
                                                         <input type="radio" name="REPCH" value="GSP">&nbsp;GSP
                                                         <input type="radio" name="REPCH" value="COO">&nbsp;COO
                                                         <input type="radio" name="REPCH" value="QBAL">&nbsp;QBAL Decl
                                                         <input type="radio" name="REPCH" value="FISME">&nbsp;FISME 
                                                         <input type="radio" name="REPCH" value="SHIPPING INSTRUCTION">&nbsp;Shipping Instruction
                                                     </th>  
                                           </s:if>
                                           <s:else>
                                                <td align="center"   style="background-color: white;color:red;font-weight:bold" class="label-1">
                                                         <input type="radio" name="REPCH1"  checked="true">
                                                         <s:property value="%{REPCH}" /> 
                                                         <s:hidden name="REPCH" value="%{REPCH}" />
                                                </td>
                                           </s:else>
                                        </tr>  
                                    </table>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="0" cellpadding="3">
                                        <tr>
                                            <th class="label-1"  style="width:190px">Invoice No : <s:textfield name="searchinv" cssStyle="text-transform:uppercase;width:80pt" theme="simple" maxLength="10"/>
                                            </th>
                                            <th align="left">
                                               <button  id="searchheadId" class="sexybutton" onclick="getDetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>&nbsp;&nbsp;
                                               <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('INVGSPPRINT.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>&nbsp;&nbsp;
                                                  <s:if  test="%{REPCH!=null && YEAR!=null}">  
                                                      <button id="printheadId" class="sexybutton" onclick="GetReport();"><span><span><span class="print" id="printId">Pdf</span></span></span></button>
                                                 </s:if>
                                            </th>
                                             
                                                   
                                                 <th align="right" >   
                                                        <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                                </th>
                                        </tr>
                                          
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
              <tr><td>                                          
                   <table align="center" border=0 cellpadding="0" width="55%" cellspacing="0">  
                         <tr>
                                 <th style="font-size:10pt" align="left"  height="25pt">Invoice Description</th>
                                 <th style="font-size:10pt" align="center" height="25pt">Unit</th>
                                 <th style="font-size:10pt" align="right">Inv Qty</th>
                                                             
                          </tr>      
                            
                            <s:if  test="%{invDetail!=null}">
                                 
                            <s:iterator value="invDetail" status="listid">
                            <tr bgcolor="#f2f9fb">
                              
                                 <td style="font-size:10pt"><s:textfield name="INV_DESC"  cssStyle="width:600px;text-transform:uppercase;"  value="%{INV_DESC}" cssClass="texts" theme="simple"/></td>
                                 <td style="font-size:10pt" align="center"><s:textfield name="UNIT" readonly="true"  cssStyle="width:60px;text-transform:uppercase;"  value="%{UNIT}" cssClass="texts" theme="simple"/></td>
                                 <td style="font-size:10pt"><s:textfield name="INV_QTY" readonly="true"  cssStyle="width:70px;text-align:right"  value="%{INV_QTY}" cssClass="texts" theme="simple"/></td>
                         
                            </tr>
                            </s:iterator>
                            </s:if>   
                             
                   </table>  
                      <s:hidden name="YEAR"    value="%{YEAR}"/>
                      <s:hidden name="MUNIT"    value="%{MUNIT}"/>
                      <s:hidden name="COMPANY" value="%{COMPANY}"/> 
                      <s:hidden name="INV_NO"  value="%{INV_NO}"/>    
                   <table align="center" border=0 cellpadding="0" width="55%" cellspacing="0">  
                          <s:if  test="%{REPCH=='GSP' || REPCH=='COO'}">
                              <tr>
                                <th style="font-size:10pt;width:12%" class="label-1" align="left">Total Carton :</th><td align="left"><s:textfield name="CTNS" cssStyle="text-align:right;width:50pt" value="%{CTNS}" theme="simple" maxLength="10"/>-<s:textfield name="BOXTYPE" cssStyle="text-transform:uppercase;width:50pt" value="%{BOXTYPE}" theme="simple" maxLength="10"/></td>
                              </tr> 
                              <tr>
                                   <th style="font-size:10pt;width:12%" class="label-1" align="left">Box Details :</th><td align="left"><s:textfield name="BOXDESC" cssStyle="text-transform:uppercase;width:200pt" value="%{BOXDESC}" theme="simple" maxLength="100"/></td>
                              </tr>    
                              <tr>
                                   <th style="font-size:10pt;width:12%" class="label-1" align="left"></th><td align="left"><s:textfield name="REM1" cssStyle="text-transform:uppercase;width:200pt" value="%{REM1}" theme="simple" maxLength="100"/></td>
                              </tr>
                              <tr>
                                   <th style="font-size:10pt;width:12%" class="label-1" align="left"></th><td align="left"><s:textfield name="REM2" cssStyle="text-transform:uppercase;width:200pt" value="%{REM2}" theme="simple" maxLength="100"/></td>
                              </tr>
                              <tr>
                                   <th style="font-size:10pt;width:12%" class="label-1" align="left">Place :</th><td align="left"><s:textfield name="PLACE" cssStyle="text-transform:uppercase;width:200pt" value="%{PLACE}" theme="simple" maxLength="100"/></td>
                                   <td style="font-size:10pt;width:12%" class="label-1" align="left">With BPO :<input type="checkbox" value="YES" name="withbpo" style="height: 9pt"/><td>
                                   <td style="font-size:10pt;width:12%" class="label-1" align="left">With Style :<input type="checkbox" value="YES" name="withstyle" style="height: 9pt"/><td> 
                              </tr>
                          </s:if>    
                       </table>   
                       <tr>
                        <td align="center">
                        <table border="0" align="center" cellspacing="1" cellpadding="6" style="background-color: #6699CC;">
                            
                                  
                           <tr>  
                                <td style="background-color: white;width:14%" class="label-1">Output&nbsp;Format</td>
                                <td  align="left" style="background-color: white;"	class="label-1">
                                    <input type="radio" name="Routput" value="PDF" CHECKED>&nbsp;&nbsp;PDF
                                    <input type="radio" name="Routput" value="XLS">&nbsp;&nbsp;Excel
                                </td>
                            </tr>    
                        </table>
                        </td>
                       </tr>
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
         
                                                     
        </form>                    


    </body>
 
</html>

