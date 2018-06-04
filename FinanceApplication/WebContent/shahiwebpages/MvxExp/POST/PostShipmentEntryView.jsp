<%-- 
    Document   : PostShipmentEntryView
    Created on : Jun 8, 2016, 3:21:54 PM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
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
            .root1
            {
                position:absolute;
                height:100px;
                width:300px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            }

            .handle1
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px

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
            
 function openpop(a)
            {
                document.getElementById(a).style.display = '';
            }

            function closediv(a)
            {
                document.getElementById(a).style.display = 'none';
            }
               
            function openpage(frame, url, a, id, b)
            {
//                alert("hi"+document.getElementById('INVOICE_S').value);
                        document.getElementById(frame).src = url;
                        document.getElementById(a).style.display = '';
                        document.getElementById(id).innerHTML = b;
                      
            }
     
     function has_duplicates() {
                var x = document.frm.BILL_NO;
                for (var i = 0; i < x.length; i++) {
                    for (j = i + 1; j < x.length; j++) {
                        if (x[i].value > 0) {
                            if (x[i].value == x[j].value) {
                                alert("This is the Duplicate Value");
                                x[i].value = '';
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
     

     

    
            function tabE(obj, e) {
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                if (e.keyCode == 13) {
                    var ele = document.forms[0].elements;
                    for (var i = 0; i < ele.length; i++) {
                        var q = (i == ele.length - 1) ? 0 : i + 1;// if last element : if any other

                        if (obj == ele[i])
                        {
                            ele[q].focus();
                            break
                        }
                    }
                    newentry();
                    return false;
                }

             }
            
            


             function newrec()
            {               
                document.frm.action="POSTSHIPMENT.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function seachdata()
            {               
                document.frm.action="POSTSHIPMENT.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function delRec()
            {     
                document.frm.action="POSTSHIPMENT.action?delFlag=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
            function newform()
            {               
                document.frm.action="newentryPOSTSHIPMENT.action";
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
            <img alt=""  src="../../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Post Shipment Entry</td></tr>
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
                                                <td class="label-1" style="width:300px">Awb No &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield name="AWBNO" id="AWBNO" cssStyle="text-transform:uppercase;width:150pt" theme="simple" onKeyPress="tabE(this, event);" />
                                                </td> 
                                                <td class="label-1" style="width:300px">Awb Date &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<sx:datetimepicker id="AWBDATE" name="AWBDATE" value="%{AWBDATE}" cssStyle="text-transform:uppercase;width:80pt"  displayFormat="dd/MM/yyyy" />

                                                </td> 
                                                 <td class="label-1" style="width:300px">H/Awb No &nbsp;<s:textfield name="HAWBNO" id="HAWBNO" cssStyle="text-transform:uppercase;width:80pt" theme="simple"  onKeyPress="tabE(this, event);"/>
                                                </td> 
                                                <td>
                                                </td>
                                                <td></td>
                                                

                                                <td align="right">  
                                                    <button  id="searchheadId" class="sexybutton" onclick="seachdata();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                                    <button  id="saveheadId"  class="sexybutton" onclick="newform();"><span><span><span class="add" id="addId" >New</span></span></span></button> 
                                                    <button  id="deleteheadId" class="sexybutton" onclick="openpage('addapprofrm', 'printInvPOSTSHIPMENT.action', 'approveraddid','Report','Invoice');"><span><span><span class="print" id="Inv" >Invoice</span></span></span></button>
                                                    <button  id="deleteheadId" class="sexybutton" onclick="delRec();"><span><span><span class="cut" id="deleteId" >Delete</span></span></span></button>
                                                    <button  id="exitheadId" class="sexybutton" onclick="javascript:window.close();"><span><span><span class="cancel" id="exitId" >Exit</span></span></span></button>
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
                                               <table id="mytableid" border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="1" width="100%">
                                                 
                                                   <tr class="div1" style="position: relative;text-align: left; top: expression(this.offsetParent.scrollTop);height:20pt">
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left"  >Awb No.</th>
                                                    <th style="font-size:8pt" align="left">Awb Date</th>
                                                    <th style="font-size:8pt" align="left">H/Awb No.</th>
                                                    <th style="font-size:8pt" align="left">Fin Date</th>
                                                    <th style="font-size:8pt" align="right">Term</th>
                                                    <th style="font-size:8pt" align="left">Fin Bank</th>
                                                    <th style="font-size:8pt" align="left">Drawn Bank</th>
                                                    <th style="font-size:8pt" align="left">Pre Carriage</th>
                                                    <th style="font-size:8pt" align="right">Ctns</th>
                                                    <th style="font-size:8pt" align="right">CBM</th>
                                                    <th style="font-size:8pt" align="right">Gross Wt</th>
                                                    <th style="font-size:8pt" align="right">Charable Wt</th>
                                                    <th style="font-size:8pt" align="left">Buying House</th>
                                                     <th style="font-size:8pt" align="left">Buying Person</th>
                                                    <th style="font-size:8pt" align="right">Edit</th>
                                                    <th style="font-size:8pt" align="right">Print</th>
                                                    <th style="font-size:8pt" align="center">Delete</th>
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                       <s:hidden id="linkno" name="linkno" value="" />  
                                                      <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{AWB_NO}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{AWB_DATE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{H_AWB_NO}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{AC_SEND_DATE}" /></td>
                                                            <td style="font-size:8pt;text-align:center"><s:property value="%{AC_SEND_TERM}" /></td>
                                                             <td style="font-size:8pt"><s:property value="%{FIN_BANK}" /></td>
                                                             <td style="font-size:8pt"><s:property value="%{DRAWN_BANK}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{PRE_CARRIAGE}" /></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="%{TOTAL_PACKAGE}" /></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="%{CBM}" /></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="%{GROSS_WT}" /></td>
                                                            <td style="font-size:8pt;text-align:right"><s:property value="%{CHARABLE_WT}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUY_HOUSE}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUY_PERSON}" /></td>
                                                            <s:hidden id="linkno" name="linkno" value="%{LINK_NO}" />  
                                                            <s:hidden id="year" name="year" value="%{YEAR" />
                                                           <td style="text-align: center;"><s:a href="editPOSTSHIPMENT.action?YEAR=%{YEAR}&LINK_NO=%{LINK_NO}"><img src="../images/editicon.png" style="border-width: 0px;"/></s:a></td>
                                                           <td style="text-align: center;"><s:a href="printsPOSTSHIPMENT.action?YEAR=%{YEAR}&LINK_NO=%{LINK_NO}"><img src="../images/pdf1.png" style="border-width: 0px;"/></s:a></td>
                                                       <s:if test="AC_SEND_DATE!=null || DBKRECV=='YES'">
                                                            <td style="text-align: center;padding-right:15px">
                                                           </td>
                                                        </s:if> 
                                                        <s:else>
                                                           <td style="text-align: center;padding-right:15px">
                                                          <s:checkbox name="SHIP_DEL" id="SHIP_DEL%{#st1.index}" fieldValue="%{YEAR+'--'+LINK_NO}" theme="simple" cssStyle="width:20px;"/>
                                                        </td> 
                                                        </s:else>
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
                                               
   <div id="approveraddid" class="root1" style="right:50px; top:100px;display:none">
    <table cellpadding="0" cellspacing="0">
        <tr bgcolor="#000080">
            <td width="100%">
                <div id="Report" class="handle1" style="cursor: move">Search Invoice</div>
            </td>
            <td style="10px"><a href="#" onclick="closediv('approveraddid')"><img border="0" width="18px" height="18px" src="css/image/divclose.gif"/></a>
            </td>
        </tr> 
        <tr>
            <td colspan="2">
                <iframe name="addapprofrm" id="addapprofrm" src="" scrolling="no" frameborder="0"  width="300px" height="100px"></iframe>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    var approveraddid1 = document.getElementById("approveraddid");
    var report = document.getElementById('Report');
    Drag.init(report, approveraddid1);
</script>
        </form> 
    </body>
</html>
