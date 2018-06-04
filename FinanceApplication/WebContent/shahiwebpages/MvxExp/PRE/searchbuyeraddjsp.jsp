<%-- 
    Document   : searchcodtforplanjsp
    Created on : Feb 12, 2015, 12:14:52 PM
    Author     : Ranjeet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/footable.js" type="text/javascript"></script>
        <title>Shahi Exports Pvt Ltd</title>        
        <style type="text/css">
            th {
                font-size:11px;
                font-weight:bold;
                color:#006699;
                background-image:url('image/table-gradient.jpg');
            } 
             tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
 
            }     
            .root
            {
                position:absolute;
                height:200px;
                width:900px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .handle
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px
                    
            }     
        </style>
        <script type="text/javascript">
            function onSearch(){
                // $("#goid").attr("disabled","disabled").val("Please wait....");
                document.unitfrm.action="getbuyeraddbillofsalesAction.action";
                document.unitfrm.submit();
            }
            function addUnit(a,b,c,d)
            {
               var ADD_NO   = window.parent.document.getElementById('BUYER_ADDR');
               var BUYER_NAME   = window.parent.document.getElementById('BUYER');
               var BUYER_NAME_ADD1   = window.parent.document.getElementById('BUYER_DESC');
               
               ADD_NO.value=document.getElementById(a).value;
               BUYER_NAME.value=document.getElementById(b).value;
               BUYER_NAME_ADD1.value=document.getElementById(c).value;
               window.parent.document.getElementById('address').value=document.getElementById(d).value;
               window.parent.document.getElementById('addressno').value=document.getElementById(a).value;
               
               window.parent.closediv('root')
            }
            
           
         
        </script>
        <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    height: 510px;
                    width: 700px;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 0px ;
                }        
                 thead tr {
                     position: relative;
                     top: expression(this.offsetParent.scrollTop);
                }
                
                tbody {
                    height: auto;
                }
                 
            </style>
        <![endif]-->
    </head>
    <body style="background-color:#f2f2f2">
           <form action="" onsubmit=""  method="post" name="unitfrm" >
              <table align="center" width="100%">
                  <tr>
                    <td style="width:100%">
                        <table width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td valign="top">
                                       <table width="100%">
                                          <tr>
                                              <td class="label-1"  > Buyer Name/Code
                                                  <s:textfield name="S_BUYER_CODE" 
                                                    id="S_BUYER_CODE" 
                                                    cssStyle="width:150px;text-transform:uppercase;"
                                                    value="%{S_BUYER}" 
                                                    cssClass="texts"                                            
                                                    theme="simple"/>
                                              
                                                 <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" onclick="onSearch();" style="cursor: hand;"/>
                                             </td>
                                         </tr>
                                     </table>
                                </td>
                             </tr>                                          
                            <tr>
                                <td valign="top" style="border-width:1px;border-color:black;border-style:solid;">
                                    <div  class="div1" style="width:100%;overflow-y:auto;overflow-x:hidden;height:250px;">
                                        <table  style="background-color: #d0d7e5;" cellspacing="1" cellpadding="2" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th align="left" style="height:20px">Buyer</th>
                                                    <th align="left">Name</th>
                                                     <th align="left">Address&nbsp;No.</th>
                                                    <th align="left">Address</th>
                                                    <th style="width:20px;">Select</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <s:iterator value="buyeraddlist" status="st">
                                                    <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                                                        <td>
                                                            <s:property value="BUYER_CODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="BUYER"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="BUYER_ID"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="BUYER_ADDRESS"/>
                                                            <s:hidden name="UNIT_CODE" value="%{BUYER_CODE}" id="UNIT_CODE%{#st.index}"/>
                                                            <s:hidden name="UNIT_DESC" value="%{BUYER}" id="UNIT_DESC%{#st.index}"/>
                                                            <s:hidden name="UNIT_ADDRESS" value="%{BUYER_ID}" id="UNIT_ADDRESS%{#st.index}"/>
                                                            <s:hidden name="UNIT_ADDRESSA" value="%{BUYER_ADDRESS}" id="UNIT_ADDRESSA%{#st.index}"/>
                                                            
                                                        </td>
                                                        
                                                        <td> 
                                                            <img src="css/images/icons/silk/add.png" onclick="addUnit('UNIT_ADDRESS<s:property value="%{#st.index}"/>','UNIT_CODE<s:property value="%{#st.index}"/>','UNIT_DESC<s:property value="%{#st.index}"/>','UNIT_ADDRESSA<s:property value="%{#st.index}"/>')" />
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
            </table>
                                            
        </form>
                                              
  
    </body>
</html>
 