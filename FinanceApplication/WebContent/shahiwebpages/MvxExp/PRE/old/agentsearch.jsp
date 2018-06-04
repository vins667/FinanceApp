<%-- 
    Document   : agentsearch
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
       
       function FocusOnInput()
        {
            document.getElementById("unitparam").focus();
        }
       
       function onSearch(){
               if(document.getElementById('unitparam').value=='')
               {
                   alert('Please Enter Agent Code or Agent Name');
                   return false;
               }
                document.agentfrm.action="agentViewbillofsalesAction.action";
                document.agentfrm.submit();
            }
         function addUnit(a,b,c,d)
            {
       <s:if test="%{PARAA==null || PARAA==''}">
               var ADD_NO   = window.parent.document.getElementById('CHA_ADDR');
               var BUYER_NAME   = window.parent.document.getElementById('CHA');
               var BUYER_NAME_ADD1   = window.parent.document.getElementById('CHA_DESC');
               
               ADD_NO.value=document.getElementById(a).value;
               BUYER_NAME.value=document.getElementById(b).value;
               BUYER_NAME_ADD1.value=document.getElementById(c).value;
               window.parent.document.getElementById('cha_address').value=document.getElementById(d).value;
               window.parent.document.getElementById('cha_addressno').value=document.getElementById(a).value;
               
               
        </s:if>
       <s:else>
           
           window.parent.document.getElementById('<s:property value="%{PARAA}"/>').value=document.getElementById(b).value;
           window.parent.document.getElementById('<s:property value="%{PARAB}"/>').value=document.getElementById(c).value;
          
       </s:else>   
           window.parent.closediv('root');
            }

       
   </script>
          
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        
  
   
    </head>
    <body style="background-color:#f2f2f2">
      <form action="" onsubmit="agentViewbillofsalesAction.action"  method="post" name="agentfrm">
           
            <table align="center" width="100%">
                <tr>
                    <td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >

                      <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                        <tr>
                          <td width="100%" class="hd" style="text-align:center" colspan="6">Agent Details</td></tr>
                  
                      <tr><td>
                      </table>
                    
                    <table>                   
                       <tr><td class="label-1">
                            Name/Id# 
                           </td><td>
                                  <s:textfield name="unitparam" 
                                           id="unitparam" 
                                           maxlength="50"
                                           cssStyle="width:200px;text-transform:uppercase" 
                                           cssClass="texts" 
                                           theme="simple" onblur="onSearch();" onkeydown="Javascript:if(event.keyCode==13) onSearch();"/>
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
                              <th class="label-1" height="25px">Agent Name</th>
                              <th class="label-1">Address#</th>
                              <th class="label-1">Code</th>
                              <th class="label-1">Vend ID</th>
                            
                         </tr>
                          </thead>
                          <tbody>
                            <s:iterator value="agentList" status="st">
                            <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                                                    
                              <td >
                                  <s:property value="%{agentName}"/>
                                 </td>
                                <td> 
                                     <s:property value="%{agentAdd}"/>
                                  
                              </td>
                              <td> 
                                   <s:property value="%{agentCode}"/>
                                 </td>
                               <td> 
                                    <s:property value="%{vendId}"/>
                                    <s:hidden name="UNIT_CODE" value="%{agentCode}" id="UNIT_CODE%{#st.index}"/>
                                    <s:hidden name="UNIT_DESC" value="%{agentName}" id="UNIT_DESC%{#st.index}"/>
                                     <s:hidden name="UNIT_ADDRESS" value="%{vendId}" id="UNIT_ADDRESS%{#st.index}"/>
                                     <s:hidden name="UNIT_ADDRESSA" value="%{agentAdd}" id="UNIT_ADDRESSA%{#st.index}"/>
                                                         
                                  </td>
                            
                            
                              
                              <td  aling="center" WIDTH="20">
                                  
                                  <img src="css/images/icons/silk/add.png" onclick="addUnit('UNIT_ADDRESS<s:property value="%{#st.index}"/>','UNIT_CODE<s:property value="%{#st.index}"/>','UNIT_DESC<s:property value="%{#st.index}"/>','UNIT_ADDRESSA<s:property value="%{#st.index}"/>')" />
                                </td>
                                  
                              </tr>
                            </s:iterator>
                          </tbody>
                             
                       </table>
                         </div>
                   </td></tr>      

            
            
            
            
    
            </table>
                                  <s:hidden name="PARAA" value="%{PARAA}"/>
                                   <s:hidden name="PARAB" value="%{PARAB}"/>
                     </form>           
              </body>
</html>
