

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
                   alert('Please Enter Unit Code');
                   return false;
               }
                document.unitfrm.action="unitViewPREINVMVX.action";
                document.unitfrm.submit();
            }
        function addUnit(unitcode,unitdesc)
        {
            var UNIT_CODE   = window.parent.document.getElementById('<s:property value="%{PARAA}"/>');
               var UNIT_DESC   = window.parent.document.getElementById('<s:property value="%{PARAB}"/>');
               UNIT_CODE.value=unitcode;
               UNIT_DESC.value=unitdesc;
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
      <form action="unitViewPREINVMVX.action" onsubmit=""  method="post" name="unitfrm">
           
            <table align="center" width="100%">
                <tr>
                    <td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >

                      <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
                        <tr>
                          <td width="100%" class="hd" style="text-align:center" colspan="6">Unit Details</td></tr>
                  
                      <tr><td>
                      </table>
                    
                    <table>                   
                       <tr><td class="label-1">
                               Unit # <s:property value="%{TYPE_CODE}"/>
                              </td><td>
                                  <s:textfield name="unitparam" 
                                           id="unitparam" 
                                           maxlength="10"
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
                              <th class="label-1" height="25px">Unit Code</th>
                              <th class="label-1">Unit Name#</th>
                              <th class="label-1">Address#</th>
                            
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
                                  
                                <img src="css/images/icons/silk/add.png" style="cursor: hand;" 
                                     onclick="addUnit('<s:property value="UNIT_CODE"/>','<s:property value="UNIT_DESC"/>');"/>

                              </td>
                                  
                              </tr>
                            </s:iterator>
                          </tbody>
                             
                       </table>
                         </div>
                   </td></tr>      

            
            
             
            
    
            </table>
                                <s:hidden name="TYPE_CODE"  value="%{TYPE_CODE}" />
                                <s:hidden name="PARAA"  value="%{PARAA}" />
                                <s:hidden name="PARAB"  value="%{PARAB}" />  
                                      
                     </form>            
              </body>
</html>
