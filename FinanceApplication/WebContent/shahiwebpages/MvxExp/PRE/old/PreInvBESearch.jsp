 
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
            width: 00px;
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
          
        document.agentfrm.action="prebeviewPREINVMVX.action?REF_TYPE="+document.agentfrm.lic_type.value+"&REF_NO="+document.agentfrm.lic_no.value
        document.agentfrm.submit();
       }
         function addUnit(a,b,c)
        {    
            
        window.parent.document.getElementById('BE_NO<s:property value="%{PARAA}"/>').value=document.getElementById(a).value;
        window.parent.document.getElementById('BE_DESC<s:property value="%{PARAA}"/>').value=document.getElementById(b).value;
        window.parent.document.getElementById('IMP_CTRL_NO<s:property value="%{PARAA}"/>').value=document.getElementById(c).value;
          
     
       
        window.parent.closediv('root');
     }

       
   </script>
          
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        
  
    
    </head>
    <body style="background-color:#f2f2f2">
        <form   method="post" name="agentfrm">
           
            <table align="center" width="100%">
                <tr>
                    <td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >

                      <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                        <tr>
                          <td width="100%" class="hd" style="text-align:center" colspan="6">Bill of Entry Details</td></tr>
                  
                      <tr><td>
                      </table>
                     
                    <table>                    
                       <tr><td class="label-1">
                            B/E# 
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
                              <td class="label-1">Licence #<s:property value="REF_TYPE" /> - <s:property value="REF_NO" /></td>
                              <td colspan="3" align="left">
                                 
                              <td>   
                                        
                          </tr>
                     </table>
              </td></tr>
                  
                   <s:hidden name="lic_no" value="%{REF_NO}" />           
                   <s:hidden name="lic_type" value="%{REF_TYPE}" />    
                 <tr><td style="border-width:1px;border-color:black;border-style:solid;" >
                         <div  class="div1" style="width:100%;overflow:auto ;height:225px;">
                       <table border="0" bgcolor="#f2f2f2" cellpadding="1" cellspacing="1" align="center" width="100%" >
                      <thead>
                          <tr class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);" >
                              <th class="label-1" height="25px">B/E#</th>
                              <th class="label-1">B/E Desc</th>
                              <th class="label-1">Qty</th>
                              <th class="label-1"></th>
                            
                         </tr>
                          </thead>
                          <tbody>
                            <s:iterator value="BEList" status="st">
                            <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                              <td><s:property value="%{BE_NO}"/></td>
                              <td><s:property value="%{BE_DESC}"/></td>
                              <td><s:property value="%{BE_QTY}"/></td>
                             
                             
                                 <td>  
                                    <s:hidden name="BE_NO" value="%{BE_NO}" id="BE_NO%{#st.index}"/>
                                    <s:hidden name="BE_DESC" value="%{BE_DESC}" id="BE_DESC%{#st.index}"/>
                                    <s:hidden name="IMP_CTRL_NO" value="%{IMP_CTRL_NO}" id="IMP_CTRL_NO%{#st.index}"/>

                                 </td> 
                                 <td  aling="center" WIDTH="20">
                                     <img src="css/images/icons/silk/add.png" onclick="addUnit('BE_NO<s:property value="%{#st.index}"/>','BE_DESC<s:property value="%{#st.index}"/>','IMP_CTRL_NO<s:property value="%{#st.index}"/>')" />
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
 