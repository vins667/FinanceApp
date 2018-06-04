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
                   alert('Please Enter Invoice No');
                   return false;
               }
                document.agentfrm.action="viewInvbillofsalesAction.action";
                document.agentfrm.submit();
            }
         function addUnit(a,b,c,d,e,f,g,h,i,j,k,a1,a2,a3,a4,a5,a6 )
            {
            
        window.parent.document.getElementById('INV_NO<s:property value="%{PARAA}"/>').value=document.getElementById(a).value;
        window.parent.document.getElementById('INV_DESC<s:property value="%{PARAA}"/>').value=document.getElementById(b).value;
        window.parent.document.getElementById('QNTY<s:property value="%{PARAA}"/>').value=document.getElementById(c).value;
        window.parent.document.getElementById('AVG_RATE<s:property value="%{PARAA}"/>').value=document.getElementById(d).value;
        window.parent.document.getElementById('CRNCY<s:property value="%{PARAA}"/>').value=document.getElementById(e).value;
        window.parent.document.getElementById('INR_CONV<s:property value="%{PARAA}"/>').value=document.getElementById(f).value;
        window.parent.document.getElementById('FOB<s:property value="%{PARAA}"/>').value=document.getElementById(h).value;
        
        window.parent.document.getElementById('UOM<s:property value="%{PARAA}"/>').value=document.getElementById(g).value;
        window.parent.document.getElementById('YEAR<s:property value="%{PARAA}"/>').value=document.getElementById(i).value;
        window.parent.document.getElementById('COMPANY<s:property value="%{PARAA}"/>').value=document.getElementById(j).value;
        window.parent.document.getElementById('EXCS_INV_NO<s:property value="%{PARAA}"/>').value=document.getElementById(k).value;
      
      window.parent.document.getElementById('i_cha<s:property value="%{PARAA}"/>').value=document.getElementById(a1).value;
      window.parent.document.getElementById('i_port<s:property value="%{PARAA}"/>').value=document.getElementById(a2).value;
      window.parent.document.getElementById('i_agent<s:property value="%{PARAA}"/>').value=document.getElementById(a3).value;
      window.parent.document.getElementById('i_buyer<s:property value="%{PARAA}"/>').value=document.getElementById(a4).value;
      window.parent.document.getElementById('i_address<s:property value="%{PARAA}"/>').value=document.getElementById(a5).value;
      
      window.parent.document.getElementById('BUYER').value=document.getElementById(a4).value;
      window.parent.document.getElementById('CHA').value=document.getElementById(a3).value;
       
      window.parent.document.getElementById('PORT').value=document.getElementById(a2).value;
      window.parent.document.getElementById('BUYER_ADDR').value=document.getElementById(a5).value;
      window.parent.document.getElementById('addressno').value=document.getElementById(a5).value;
      
      window.parent.document.getElementById('DESTINATION').value=document.getElementById(a1).value;
      window.parent.document.getElementById('SHIP_MODE').value=document.getElementById(a6).value;
      
       
      
      
        
        window.parent.totalpalnqty();
        window.parent.totalfobqty();
        window.parent.totalinvqty();
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
                          <td width="100%" class="hd" style="text-align:center" colspan="6">Invoice Details</td></tr>
                  
                      <tr><td>
                      </table>
                    
                    <table>                   
                       <tr><td class="label-1">
                            Invoice# 
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
                              <th class="label-1" height="25px">Invoice#</th>
                              <th class="label-1">Inv Description</th>
                              <th class="label-1">Qnty</th>
                              <th class="label-1">Avg Rate</th>
                              <th class="label-1"></th>
                            
                         </tr>
                          </thead>
                          <tbody>
                            <s:iterator value="invList" status="st">
                            <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                              <td><s:property value="%{EXCS_INV_NO}"/></td>
                              <td><s:property value="%{INV_DESC}"/></td>
                              <td><s:property value="%{QNTY}"/></td>
                              <td> 
                                  <s:property value="%{AVG_RATE}"/>
                                  <s:hidden name="INV_NO" value="%{INV_NO}" id="INV_NO%{#st.index}"/>
                                  <s:hidden name="INV_DESC" value="%{INV_DESC}" id="INV_DESC%{#st.index}"/>
                                  <s:hidden name="QNTY" value="%{QNTY}" id="QNTY%{#st.index}"/>
                                  <s:hidden name="AVG_RATE" value="%{AVG_RATE}" id="AVG_RATE%{#st.index}"/>
                                  <s:hidden name="CRNCY" value="%{CRNCY}" id="CRNCY%{#st.index}"/>
                                  <s:hidden name="INR_CONV" value="%{INR_CONV}" id="INR_CONV%{#st.index}"/>
                                  <s:hidden name="UOM" value="%{UOM}" id="UOM%{#st.index}"/>
                                  <s:hidden name="FOB" value="%{FOB}" id="FOB%{#st.index}"/>
                                  <s:hidden name="YEAR" value="%{YEAR}" id="YEAR%{#st.index}"/>
                                  <s:hidden name="COMPANY" value="%{COMPANY}" id="COMPANY%{#st.index}"/>
                                  <s:hidden name="EXCS_INV_NO" value="%{EXCS_INV_NO}" id="EXCS_INV_NO%{#st.index}"/>
                                  <s:hidden name="i_cha" value="%{i_cha}" id="i_cha%{#st.index}"/>
                                  <s:hidden name="i_port" value="%{i_port}" id="i_port%{#st.index}"/>
                                  <s:hidden name="i_agent" value="%{i_agent}" id="i_agent%{#st.index}"/>
                                  <s:hidden name="i_buyer" value="%{i_buyer}" id="i_buyer%{#st.index}"/>
                                  <s:hidden name="i_address" value="%{i_address}" id="i_address%{#st.index}"/>
                                  <s:hidden name="i_mos" value="%{i_mos}" id="i_mos%{#st.index}"/>
                                  
                                 </td>
                                 <td  aling="center" WIDTH="20">
                                     <img src="css/images/icons/silk/add.png" onclick="addUnit('INV_NO<s:property value="%{#st.index}"/>','INV_DESC<s:property value="%{#st.index}"/>','QNTY<s:property value="%{#st.index}"/>','AVG_RATE<s:property value="%{#st.index}"/>','CRNCY<s:property value="%{#st.index}"/>','INR_CONV<s:property value="%{#st.index}"/>','UOM<s:property value="%{#st.index}"/>','FOB<s:property value="%{#st.index}"/>',
                                         'YEAR<s:property value="%{#st.index}"/>','COMPANY<s:property value="%{#st.index}"/>','EXCS_INV_NO<s:property value="%{#st.index}"/>'
                                         ,'i_cha<s:property value="%{#st.index}"/>','i_port<s:property value="%{#st.index}"/>','i_agent<s:property value="%{#st.index}"/>','i_buyer<s:property value="%{#st.index}"/>','i_address<s:property value="%{#st.index}"/>','i_mos<s:property value="%{#st.index}"/>')" />
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
