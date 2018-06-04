 
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
               if(document.getElementById('unitparam').value=='')
               {
                   alert('Please Enter Licence No');
                   return false;
               }
                document.agentfrm.action="prelicviewPREINVMVX.action";
                document.agentfrm.submit();
            }
         function addUnit(a,b,c,d,e,f,g,h,i,j,k,l,m)
        {    
            
   
        window.parent.copyFcTotal('<s:property value="%{PARAA}"/>') ;
        window.parent.document.getElementById('REF_NO<s:property value="%{PARAA}"/>').value=document.getElementById(a).value;
        window.parent.document.getElementById('REF_TYPE<s:property value="%{PARAA}"/>').value=document.getElementById(b).value;
        window.parent.document.getElementById('LITEM_DESC<s:property value="%{PARAA}"/>').value=document.getElementById(c).value;
        window.parent.document.getElementById('IO_NORMS<s:property value="%{PARAA}"/>').value=document.getElementById(d).value;
        window.parent.document.getElementById('LIC_DATE<s:property value="%{PARAA}"/>').value=document.getElementById(e).value;
        window.parent.document.getElementById('LIC_COMP<s:property value="%{PARAA}"/>').value=document.getElementById(f).value;
        window.parent.document.getElementById('LIC_LOCT<s:property value="%{PARAA}"/>').value=document.getElementById(g).value;
        window.parent.document.getElementById('EXP_CTRL_NO<s:property value="%{PARAA}"/>').value=document.getElementById(h).value;
        window.parent.document.getElementById('IMP_CTRL_NO<s:property value="%{PARAA}"/>').value=document.getElementById(i).value;
        window.parent.document.getElementById('LIC_AMT_OB<s:property value="%{PARAA}"/>').value=document.getElementById(j).value;
        window.parent.document.getElementById('LIC_AMT_ISSUE<s:property value="%{PARAA}"/>').value=document.getElementById(k).value;
        window.parent.document.getElementById('LIC_QTY_OB<s:property value="%{PARAA}"/>').value=document.getElementById(l).value;
        window.parent.document.getElementById('LIC_QTY_ISSUE<s:property value="%{PARAA}"/>').value=document.getElementById(m).value;
       
    
    
       
        window.parent.closediv('root');
     }

       
   </script>
          
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        
  
    
    </head>
    <body style="background-color:#f2f2f2">
      <form action="" onsubmit="prelicviewPREINVMVX.action"  method="post" name="agentfrm">
           
            <table align="center" width="100%">
                <tr>
                    <td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >

                      <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                        <tr>
                          <td width="100%" class="hd" style="text-align:center" colspan="6">Licence Details</td></tr>
                  
                      <tr><td>
                      </table>
                     
                    <table>                    
                       <tr><td class="label-1">
                            Licence# 
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
                              <th class="label-1" height="25px">Licence#</th>
                              <th class="label-1">Type</th>
                              <th class="label-1">Exp Item Desc</th>
                              <th class="label-1">IO Norms</th>
                              <th class="label-1"></th>
                            
                         </tr>
                          </thead>
                          <tbody>
                            <s:iterator value="LicenceList" status="st">
                            <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                              <td><s:property value="%{LIC_NO}"/></td>
                              <td><s:property value="%{LIC_TYPE}"/></td>
                              <td><s:property value="%{EXP_ITEM_DESC}"/></td>
                              <td><s:property value="%{IO_NORMS}"/></td>
                             
                              <td>  
                                 
                                  <s:hidden name="REF_NO" value="%{LIC_NO}" id="REF_NO%{#st.index}"/>
                                  <s:hidden name="REF_TYPE" value="%{LIC_TYPE}" id="REF_TYPE%{#st.index}"/>
                                  <s:hidden name="LITEM_DESC" value="%{EXP_ITEM_DESC}" id="LITEM_DESC%{#st.index}"/>
                                  <s:hidden name="IO_NORMS" value="%{IO_NORMS}" id="IO_NORMS%{#st.index}"/>
                                  <s:hidden name="LIC_DATE" value="%{LIC_DATE}" id="LIC_DATE%{#st.index}"/>
                                  <s:hidden name="LIC_COMP" value="%{LIC_COMP}" id="LIC_COMP%{#st.index}"/>
                                  <s:hidden name="LIC_LOCT" value="%{LIC_LOCT}" id="LIC_LOCT%{#st.index}"/>
                                  <s:hidden name="EXP_CTRL_NO" value="%{EXP_CTRL_NO}" id="EXP_CTRL_NO%{#st.index}"/>
                                  <s:hidden name="IMP_CTRL_NO" value="%{IMP_CTRL_NO}" id="IMP_CTRL_NO%{#st.index}"/>
                                  <s:hidden name="LIC_AMT_OB" value="%{LIC_AMT_OB}" id="LIC_AMT_OB%{#st.index}"/>
                                  <s:hidden name="LIC_AMT_ISSUE" value="%{LIC_AMT_ISSUE}" id="LIC_AMT_ISSUE%{#st.index}"/>
                                  <s:hidden name="LIC_QTY_OB" value="%{LIC_QTY_OB}" id="LIC_QTY_OB%{#st.index}"/>
                                  <s:hidden name="LIC_QTY_ISSUE" value="%{LIC_QTY_ISSUE}" id="LIC_QTY_ISSUE%{#st.index}"/>
                                
                                 
                              
                               </td>
                                 <td  aling="center" WIDTH="20">
                                     <img src="css/images/icons/silk/add.png" onclick="addUnit('REF_NO<s:property value="%{#st.index}"/>','REF_TYPE<s:property value="%{#st.index}"/>','LITEM_DESC<s:property value="%{#st.index}"/>','IO_NORMS<s:property value="%{#st.index}"/>','LIC_DATE<s:property value="%{#st.index}"/>','LIC_COMP<s:property value="%{#st.index}"/>','LIC_LOCT<s:property value="%{#st.index}"/>','EXP_CTRL_NO<s:property value="%{#st.index}"/>',
                                                                                               'IMP_CTRL_NO<s:property value="%{#st.index}"/>','LIC_AMT_OB<s:property value="%{#st.index}"/>','LIC_AMT_ISSUE<s:property value="%{#st.index}"/>','LIC_QTY_OB<s:property value="%{#st.index}"/>','LIC_QTY_ISSUE<s:property value="%{#st.index}"/>')" />
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
 