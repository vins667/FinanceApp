
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

 
<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="../js/dom-drag.js"></script>
<script type="text/javascript" src="script/jsDatePick.min.1.3.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="style/jsDatePick_ltr.min.css" />
<html> 
    <head>
        <s:head />
         <sj:head jqueryui="true"/>
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
            
  


          
             function seachdata()
            {               
                document.frm.action="PUQUERY.action?viewFlag='YES'";
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
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Portal Invoice Query</td></tr>
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
                                                <td  align="left" valign="top" class="label-1" style="width:200px" ">Loct: 
                                                    
                                                           <s:select  headerValue="" list="#{'200':'200', '100':'100'}"  cssStyle="font-size:10pt;width:40pt;font-weight:bold" theme="simple" name="SEARCH_LOCT" value="%{LOCT}" /> 
                                                    </td>  
                                                <td class="label-1" style="width:300px">Buyer &nbsp;<s:textfield name="BUYER_S" id="BUYER_S" value="%{BUYER_S}" cssStyle="text-transform:uppercase;width:80pt" theme="simple"  onKeyPress="tabE(this, event);"/>
                                                </td>
                                                <td class="label-1" style="width:300px">TO Date From &nbsp;
                                                                <sj:datepicker name="DATE_FROM" id="DATE_FROM" cssStyle="width:110px;"  value="%{DATE_FROM}" displayFormat="dd-M-yy" buttonImageOnly="true"/>
                                      
                                                </td>  
                                                 <td class="label-1" style="width:300px">TO Date To &nbsp;    
                                                     <sj:datepicker name="DATE_TO" id="DATE_TO" cssStyle="width:110px;"  value="%{DATE_TO}" displayFormat="dd-M-yy" buttonImageOnly="true"/>
                                      
                                                 </td>
                                                <td class="label-1" style="width:300px">Status &nbsp;
                                                            <s:select name="SEARCHTYPE" id="SEARCHTYPE" theme="simple"   list="#{'PEND':'Pending','APRV':'Approved','All':'ALL'}"   value="%{SEARCHTYPE}" cssStyle="width:65pt"/>
                                                </td>
                                                   

                                                <td align="right">  
                                                  <button  id="searchheadId" class="sexybutton" onclick="seachdata();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                                  <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PUQUERY.action?aausrid=<s:property value="%{aausrid}"/>&ACCESSTYPE=<s:property value="%{ACCESSTYPE}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                                  <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                               </td>
                                                </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        <div style="width:100%" class="toolTip" id="toolTipDiv"></div>
                        <div align="center" style="width:100%;">
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr   style="background-color: whitesmoke;height: 350pt;">
                                    <td>
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:350pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table id="mytableid" border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%">
                                                 
                                                   <tr class="div1" style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left">Buyer</th>
                                                    <th style="font-size:8pt" align="left">Calc</th>
                                                    <th style="font-size:8pt" align="left">Inv No.</th>
                                                    <th style="font-size:8pt" align="left">PCH</th>
                                                    <th style="font-size:8pt" align="leftt">Type</th>
                                                     <th style="font-size:8pt" align="leftt">Buyer PO#</th>
                                                    <th style="font-size:8pt" align="leftt">Qty</th>
                                                    <th style="font-size:8pt" align="leftt">Crncy</th>
                                                    <th style="font-size:8pt" align="leftt">Value</th>
                                                    <th style="font-size:8pt" align="left">TO Date</th>
                                                    <th style="font-size:8pt" align="left">ETD Date</th>
                                                    <th style="font-size:8pt" align="left">FTP Date</th>
                                                    <th style="font-size:8pt" align="left">PU Date</th>
                                                    <th style="font-size:8pt" align="left">Recv Date</th>
                                                    <th style="font-size:8pt" align="right">1st Sub Dt</th>
                                                    <th style="font-size:8pt" align="right">2nd Sub Dt</th>
                                                    <th style="font-size:8pt" align="right">Due Date<th>
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                       
                                                      <s:set id="ctn" name="ctn" value="0"/>  
                                                      <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUYER}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{DUE_CAL_DAYS}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{EXCS_INV_NO}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{PCH}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{INVTYPE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{PONO}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{INVQTY}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{CRNCY}" /></td>
                                                             <td style="font-size:8pt"><s:property value="%{INVFOB}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{TODATE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{ETDDATE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{FTPDATE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{PUDATE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{DOCSRECVDATE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{SUB1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{SUB2}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{DUEDATE}" /></td>
                                                            
                                                            
                                                         </tr>
                                                    </s:iterator>
                                                </tbody>
                                            </table>
                                        </div>  
                                      </td>
                                 </tr>
                                    </table> 
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
