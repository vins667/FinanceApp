<%-- 
    Document   : ExchangeRateMaster
    Created on : Mar 28, 2017, 6:21:54 PM
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
                document.frm.action="BuyerPortalMasterA.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function seachdata()
            {               
                document.frm.action="BuyerPortalMasterA.action?Flag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function delRec()
            {     
                document.frm.action="BuyerPortalMasterA.action?delFlag=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
            function newform()
            {               
                document.frm.action="newentryBuyerPortalMasterA.action";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
            function updte1()
            {    
                date1=dojo.widget.byId("close_date").getValue();
                if(date1!=""){
                document.frm.action="update1BuyerPortalMasterA.action";
                document.frm.submit();
                
                document.getElementsByName("chqdate").checked=false;
                }
                else{
                    alert("Please Select Close Date For Update....");
                }
//                document.getElementById('prepage').style.visibility='';
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
            
            function UncheckAll(){ 
                        var w = document.getElementsByName("chqdate"); 
                        for(var i = 0; i < w.length; i++){ 
                          if(w[i].checked){ 
                            w[i].checked = false; 
                          }
                        }
                    } 
            
            

            
        </script>

    </head>

    <body style="width:100%;height:100%;overflow: hidden;" onload="UncheckAll();">
<!--        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>-->
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr ><td  class="hd" style="text-align:center">Buyer Portal Master</td></tr>
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
                                                <td class="label-1" style="width:300px">Buyer &nbsp;</TD>
                                                <td class="label-1" style="width:300px">
                                                    <s:textfield name="BUYR" id="BUYR" value="%{BUYR}" cssStyle="text-transform:uppercase;width:150pt" theme="simple"  />
                                                </td> 
                                                <td class="label-1" style="width:300px">EDI&nbsp;&nbsp;
                                                     <s:select  headerValue="" list="# {'%':'','Y':'Y','N':'N'}"  cssStyle="font-size:10pt;width:100px;font-weight:bold" theme="simple" name="SEARCH_EDI"  /> 
                                                 </td>
                                                 <td class="label-1" style="width:300px">Portal&nbsp;&nbsp;
                                                     <s:select  headerValue="" list="# {'%':'','Y':'Y','N':'N'}"  cssStyle="font-size:10pt;width:100px;font-weight:bold" theme="simple" name="SEARCH_PORTAL"  /> 
                                                 </td>
                                                 <td class="label-1" style="width:300px">IC&nbsp;&nbsp;
                                                     <s:select  headerValue="" list="# {'%':'','Y':'Y','N':'N'}"  cssStyle="font-size:10pt;width:100px;font-weight:bold" theme="simple" name="SEARCH_IC"  /> 
                                                 </td>
                                                
                                                 <td class="label-1" style="width:300px"></td>
                                                 

                                                <td align="right">  
                                                   <button  id="searchheadId" class="sexybutton" onclick="seachdata();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                                   <button  id="saveheadId"  class="sexybutton" onclick="newform();"><span><span><span class="add" id="addId" >New</span></span></span></button> 
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
                                                        <th style="font-size:8pt" align="left">Sr.No.</th>
                                                        <th style="font-size:8pt" align="left">Buyer</th>
                                                        <th style="font-size:8pt" align="left">EDI App</th>
                                                        <th style="font-size:8pt" align="left">Portal App</th>
                                                        <th style="font-size:8pt" align="left">IC App</th>
                                                        <th style="font-size:8pt" align="left">DueDays</th>
                                                        <th style="font-size:8pt" align="center">Edit</th>
                                                    
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                      <s:iterator value="BUYERPORTALLIST" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{BUYR}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{BEDI_APP}" /></td>
                                                            <td style="font-size:8pt"><s:property value="%{BPORTAL_APP}" /></td>
                                                            <td style="font-size:8pt;"><s:property value="%{BIC_APP}" /></td>
                                                            <td style="font-size:8pt;"><s:property value="%{DUE_DAYS}" /></td>
                                                           
                                                            <td style="text-align: center;">
                                                                <a  href="editValBuyerPortalMasterA.action?BUYR1=<s:property value="%{BUYR}"/>&BEDI_APP1=<s:property value="%{BEDI_APP}"/>&BPORTAL_APP1=<s:property value="%{BPORTAL_APP}"/>&BIC_APP1=<s:property value="%{BIC_APP}"/>"><img style="border:0" height="14pt" src="../../css/image/EditIcon-new.png" alt="Modify" title="Click to Modify" ></a>
                                                            </td>
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
