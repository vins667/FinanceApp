<%-- 
    Document   : chalansearch
    Created on : Feb 11, 2015, 5:51:57 PM
    Author     : Ravindra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<%@ taglib prefix="s" uri="/struts-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="css/sexybuttons.css"/>" rel="stylesheet" type="text/css"/>

  <style type="text/css">
          th {
        font-size:9pt;
        font-weight:bold;
        color:white;
        background-image:url('../image/style13.jpg');
    }
      tbody {
        height: 500px;
        overflow-y: auto;
        overflow-x: hidden;
 
     }
     

     
        </style>
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
            padding-top: 28px ;
        }
 
         }
        thead tr {
 
        }
        tbody {
            height: auto;
        }
          }
    </style>
<![endif] -->
        
   <script language="JavaScript">
       
       function onSearch(){
               if(document.getElementById('unitparam').value=='')
               {
                   alert('Please Enter  Code');
                   return false;
               }
                document.unitfrm.action="taxcodeViewPREINVMVX.action";
                document.unitfrm.submit();
            } 
        function addUnit(unitcode)
        {
               var UNIT_CODE   = window.parent.document.getElementById('<s:property value="%{PARAA}"/>');
               var UNIT_DESC   = window.parent.document.getElementById('<s:property value="%{PARAB}"/>');
               var UNIT_ADDRESS   = window.parent.document.getElementById('<s:property value="%{TYPE_CODE}"/>');
               
               UNIT_CODE.value=document.getElementById('UNIT_CODE'+unitcode).value;
               UNIT_DESC.value=document.getElementById('UNIT_DESC'+unitcode).value;
               UNIT_ADDRESS.value=document.getElementById('UNIT_ADDRESS'+unitcode).value;
               //window.parent.document.closediv('rootunit');
               window.parent.closediv('root');
        }

       function FocusOnInput()
        {
            document.getElementById("unitparam").focus();
        }
   </script>
           
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        


    
    </head>
    <body style="background-color:#f2f2f2">
      <form action="taxcodeViewPREINVMVX.action" onsubmit=""  method="post" name="unitfrm">
           
            <table align="center" width="100%">
                <tr> 
                    <td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >

                      <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                        <tr>
                          <td width="100%" class="hd" style="text-align:center" colspan="6">Tax Details</td></tr>
                  
                      <tr><td>
                      </table>
                    
                    <table>                   
                       <tr><td class="label-1">
                            TaxCode # 
                              </td><td>
                                  <s:textfield name="unitparam" 
                                           id="unitparam" 
                                           maxlength="5"
                                           cssStyle="width:130px;text-transform:uppercase" 
                                           cssClass="texts" 
                                           theme="simple" onblur="onSearch();" onkeydown="Javascript:if(event.keyCode==13) onSearch();" />
                                      <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" onclick="onSearch();" style="cursor: hand;"/>

                              </td>
                              <td> </td>
                              
                              <td colspan="4" align="left">
                                    
                              <td>   
                                        
                          </tr>
                     </table>
              </td></tr>
                  
                
                
                
                 <tr><td style="border-width:1px;border-color:black;border-style:solid;" >
                         <div  class="div1" style="width:100%;overflow:auto ;height:225px;">
                       <table border="0" bgcolor="#f2f2f2" cellpadding="1" cellspacing="1" align="center" width="100%" >
                      <thead>
                          <tr class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);" >
                              <th class="label-1" height="25px">Tax Code</th>
                              <th class="label-1">Tax Description</th>
                              <th class="label-1">Tax %</th>
                            
                         </tr>
                          </thead>
                          <tbody>
                            <s:iterator value="unitList" status="status">
                             <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                              <td>
                                  <s:property value="%{UNIT_CODE}"/>
                                  </td>
                              <td>
                                  <s:property value="%{UNIT_DESC}"/>
                              </td>
                              <td> 
                                  <s:property value="%{UNIT_ADDRESS}"/>
                              </td>
                             
                              
                              <td  aling="center" WIDTH="30">
                                  <s:hidden name="UNIT_CODE" id="UNIT_CODE%{#status.index+1}" value="%{UNIT_CODE}"/>
                                  <s:hidden name="UNIT_DESC" id="UNIT_DESC%{#status.index+1}" value="%{UNIT_DESC}"/>
                                  <s:hidden name="UNIT_ADDRESS" id="UNIT_ADDRESS%{#status.index+1}" value="%{UNIT_ADDRESS}"/>
                                  
                                <img src="css/images/icons/silk/add.png" style="cursor: hand;" 
                                     onclick="addUnit('<s:property value="%{#status.index+1}"/>');"/>

                              </td>
                                  
                              </tr>
                            </s:iterator>
                          </tbody>
                             
                       </table>
                         </div>
                   </td></tr>      

            
            
            
            
    
            </table>
                                  <s:hidden name="PARAA"  value="%{PARAA}" />
                                  <s:hidden name="PARAB"  value="%{PARAB}" />  
                                  <s:hidden name="TYPE_CODE"  value="%{TYPE_CODE}" />     
                     </form>           
              </body>
</html>