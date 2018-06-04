<%-- 
    Document   : Walmart_ItemIdBrkup
    Created on : Feb 8, 2017, 5:24:44 PM
    Author     : Guddu Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<script type="text/javascript" src="../js/dom-drag.js"></script>
<html>
    <head>
        <s:head/>
        <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Export Pvt Ltd.</title>
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
            .root1
            {
                position:absolute;
                height:330px;
                width:700px;
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
        
        
<!--<style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:black;
                background-image:url('../image/style13.jpg');
            }
            tbody 
            {
                height: 0px;
                overflow-y: auto;
                overflow-x: auto;
            }
</style>-->
        <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height: 200px;
            width: 400px;
            overflow-y: auto;
            overflow-x: hidden;
            border:  solid #006699; 
            border-width: 0px 0px 0px 0px;
            padding-top: 28px ;
        }
        thead tr {

        }
        tbody {
            height: auto;
        }
    </style>
<![endif]-->
        
        <script language="javascript">
            
            function tabE_Lst1(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                    if (e.keyCode == 40) {
                    document.getElementById("ITEM_ID"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("ITEM_ID"+y).focus();
                    }
            }
            function tabE_Lst2(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                     if (e.keyCode == 40) {
                    document.getElementById("ITEM_RATIO"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("ITEM_RATIO"+y).focus();
                    }
            }
            function tabE_Lst3(obj, e,id) {
                x=eval(id)+1;
                y=eval(id)-1;
                var e = (typeof event != 'undefined') ? window.event : e;// IE : Moz
                    if (e.keyCode == 40) {
                    document.getElementById("ITEM_PCKGS"+x).focus();
                    }
                    if (e.keyCode == 38) {
                    document.getElementById("ITEM_PCKGS"+y).focus();
                    }
            }
            
            function saverec()
            {
                x=document.form1.TOTITEM_QTY.value;
                y=document.form1.QTYID.value;
                if(eval(x)==eval(y)){
                document.form1.action = "brkupsaveWalMart_InvA.action";
                document.form1.submit();
                document.getElementById('prepage').style.visibility = '';
                }
                else{
                alert("Total Value not Matched");    
                }
                
            }
            function itmidlst()
            {
                var totqty=0;
                 ITEM_ID=document.form1.ITEM_ID;//this is for loop
                 ITEMNOFIX=document.form1.ITEMNOFIX;
                 ITEM_NO=document.form1.ITEMNOFIX;
                  if(ITEM_ID.length>0)
                  {
                    for (var i = 0; i < ITEM_NO.length; i++) 
                    {
                            if(ITEM_NO[i].value=='')
                            {
                                ITEM_NO[i].value=totqty;
                            }
                            else
                              {
                                   totqty=ITEMNOFIX.value;
                                   ITEM_NO[i].value=totqty;
                              }
                        }
                  }
                    else{
                         totqty=ITEMNOFIX.value;
                         ITEM_NO.value=totqty;
                        
                    } 
            }
            function ratiopckQty()
            {
                var totpck=0.0;
                var totqty=0.0;
                 ITEM_ID=document.form1.ITEM_ID;//this is for loop
                 TOTITEM_QTY=document.form1.TOTITEM_QTY;
                 ITEM_RATIO=document.form1.ITEM_RATIO;
                 ITEM_PCKGS=document.form1.ITEM_PCKGS;
                 ITEM_QTY=document.form1.ITEM_QTY;
                  if(ITEM_ID.length>0){
                    for (var i = 0; i < ITEM_ID.length; i++) {
                        if(ITEM_ID[i].value>''){
                        ITEM_QTY[i].value=eval(ITEM_RATIO[i].value)*eval(ITEM_PCKGS[i].value);
                         totpck=totpck+eval(ITEM_QTY[i].value);
                         document.form1.TOTITEM_QTY.value=totpck;
                        }else{
                            
                        }
                    }
                    }
                    else{
                        if(ITEM_ID.value>''){
                         ITEM_QTY.value=eval(ITEM_RATIO.value)*eval(ITEM_PCKGS.value);
                         totpck=totpck+eval(ITEM_QTY.value);
                         document.form1.TOTITEM_QTY.value=totpck;
                        }
                    } 
            }
            
        </script>
    </head>
    <body>


        <form action="" method="POST" id="form1" name="form1">


          
            <%--   guddu<s:property value="%{QTYID}"/>   --%>
 

            <s:hidden id="invid" name="invid" value="%{invid}" />
            <s:hidden id="QTYID" name="QTYID" value="%{QTYID}" />
           
            <s:hidden id="TOT_ITMQTY1" name="TOT_ITMQTY1" value="%{TOT_ITMQTY1}" />
            <table align="center">
                <tr style="text-align: center">
                  <td align="right">
                  <button  id="saveheadId"  class="sexybutton" onclick="saverec();" ><span><span><span class="save" id="saveId" >Save</span></span></span></button>
                  </td> 
                  </tr>
             </table>     
            <div   style="overflow:auto;height:190px;" >
                            <table border="0" width="100%" align="center" bgcolor="#d0d7e5" cellpadding="2" cellspacing="1" >
                                <thead >
                                    <tr style="position: relative;text-align: left;height:20px;  top: expression(this.offsetParent.scrollTop);">
                                        <th>Item No</th>
                                        <th>Item Id</th>
                                        <th style="text-align: right;">Ratio</th>
                                        <th style="text-align: right;">Packages</th>
                                        <th style="text-align: right;padding-right:27px">Qty</th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <s:hidden name="aausrid" vaue="%{aausrid}"/>
                                     <s:set id="cnt" name="cnt" value="%{0}" />
                                    <s:if test="%{ITEMBRKUPLIST.size()>0}">
                                         <s:hidden id="ITEMNOFIX" name="ITEMNOFIX" value="%{ITMIDL}" />
                                    <s:iterator value="ITEMBRKUPLIST" status="stat">
                                       
                                        <tr bgcolor="white" width="100%">
                                            <td><s:textfield id="ITEM_NO%{#cnt+1}" name="ITEM_NO" theme="simple" value="%{ITEMNO}" style="width:100px;background:#c4c2c2;" readonly="true"/></td>
                                            <td><s:textfield id="ITEM_ID%{#cnt+1}" name="ITEM_ID" theme="simple" value="%{ITEMID}" style="width:150px;text-transform:uppercase;" onblur="itmidlst();" onkeydown="return tabE_Lst1(this,event,'%{#cnt+1}');"/></td>
                                            <td><s:textfield id="ITEM_RATIO%{#cnt+1}" name="ITEM_RATIO" theme="simple" value="%{ITEMRATIO}" style="width:80px;text-align: right;" onkeydown="return tabE_Lst2(this,event,'%{#cnt+1}');" maxlength="3"/></td>
                                            <td><s:textfield id="ITEM_PCKGS%{#cnt+1}" name="ITEM_PCKGS" theme="simple" value="%{ITEMPCKGS}" style="width:80px;text-align: right;" onblur="ratiopckQty();" onkeydown="return tabE_Lst3(this,event,'%{#cnt+1}');"/></td>
                                            <td style="text-align: right;padding-right:30px"><s:textfield id="ITEM_QTY%{#st.index+1}" name="ITEM_QTY" theme="simple" value="%{ITEMQTY}" style="width:100px;text-align: right;background:#c4c2c2;" readonly="true"/></td>
                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}" />
                                        </tr>
                                    </s:iterator>
                                     <s:iterator begin="cnt" end="50" status="st">
                                        <tr bgcolor="white" width="100%">
                                            <td><s:textfield id="ITEM_NO%{#cnt+1}" name="ITEM_NO" theme="simple" value="%{ITEM_NO}" style="width:100px;background:#c4c2c2;" readonly="true" /></td>
                                            <td><s:textfield id="ITEM_ID%{#cnt+1}" name="ITEM_ID" theme="simple" value="%{ITEM_ID}" style="width:150px;text-transform:uppercase;" onblur="itmidlst();" onkeydown="return tabE_Lst1(this,event,'%{#cnt+1}');"/></td>
                                            <td><s:textfield id="ITEM_RATIO%{#cnt+1}" name="ITEM_RATIO" theme="simple" value="%{ITEM_RATIO}" style="width:80px;text-align: right;" onkeydown="return tabE_Lst2(this,event,'%{#cnt+1}');" maxlength="3"/></td>
                                            <td><s:textfield id="ITEM_PCKGS%{#cnt+1}" name="ITEM_PCKGS" theme="simple" value="%{ITEM_PCKGS}" style="width:80px;text-align: right;" onblur="ratiopckQty();" onkeydown="return tabE_Lst3(this,event,'%{#cnt+1}');"/></td>
                                            <td style="text-align: right;padding-right:30px"><s:textfield id="ITEM_QTY%{#st.index+1}" name="ITEM_QTY" theme="simple" value="%{ITEM_QTY}" style="width:100px;text-align: right;background:#c4c2c2;" readonly="true"/></td>
                                            <s:set id="cnt" name="cnt" value="%{#cnt+1}" />
                                        </tr>
                                    </s:iterator> 
                                       </s:if>
                                    <s:else>
                                        <s:hidden id="ITEMNOFIX" name="ITEMNOFIX" value="%{ITEMNO}" /> 
                                        <s:iterator begin="cnt" end="50" status="st">
                                        <tr bgcolor="white" width="100%">
                                            <td><s:textfield id="ITEM_NO%{#st.index+1}" name="ITEM_NO" theme="simple" value="%{ITMIDL}" style="width:100px;background:#c4c2c2;" readonly="true"/></td>
                                            <td><s:textfield id="ITEM_ID%{#st.index+1}" name="ITEM_ID" theme="simple" value="%{ITEM_ID}" style="width:150px;text-transform:uppercase;" onblur="itmidlst();" onkeydown="return tabE_Lst1(this,event,'%{#st.index+1}');"/></td>
                                            <td><s:textfield id="ITEM_RATIO%{#st.index+1}" name="ITEM_RATIO" theme="simple" value="%{ITEM_RATIO}" style="width:80px;text-align: right;" onkeydown="return tabE_Lst2(this,event,'%{#st.index+1}');" maxlength="3"/></td>
                                            <td><s:textfield id="ITEM_PCKGS%{#st.index+1}" name="ITEM_PCKGS" theme="simple" value="%{ITEM_PCKGS}" style="width:80px;text-align: right;" onblur="ratiopckQty();" onkeydown="return tabE_Lst3(this,event,'%{#st.index+1}');"/></td>
                                            <td style="text-align: right;padding-right:30px"><s:textfield id="ITEM_QTY%{#st.index+1}" name="ITEM_QTY" theme="simple" value="%{ITEM_QTY}" style="width:100px;text-align: right;background:#c4c2c2;" readonly="true"/></td>
                                        </tr>
                                    </s:iterator> 
                                    </s:else>
                                   
                                </tbody>
                            </table>
                        </div>
                                 <div style="overflow:auto;text-align:right;">
                                        <table>
                                            <tr>
                                                
                                             <td height="0pt"  align="left" style="color:red;font-weight:bold">
                                            <s:actionerror />
                                            <s:fielderror />
                                            <s:actionmessage />
                                            </td>
                                            
                                        <td style="text-align: right">Total Qty</td>
                                        <td style="text-align: right; padding-right:30px"><s:textfield id="TOTITEM_QTY" name="TOTITEM_QTY" theme="simple"  value="%{TOT_ITMQTY1}" style="width:100px;text-align:right;background:#c4c2c2;" readonly="true"/></td>  
                                        </tr>
                                        </table>
                                
                                    </div>     
                                    
                                   
                            </form>
                            </body>
                            </html>

