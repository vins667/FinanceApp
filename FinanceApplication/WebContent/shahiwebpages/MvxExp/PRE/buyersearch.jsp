<%-- 
    Document   : buyersearch
    Created on : Oct 16, 2015, 5:40:24 PM
    Author     : User
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<link type="text/css" rel="stylesheet" href="../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="../../css/main.css">  

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Export Pvt Ltd.</title>


  <script language="javascript">
      
      function byer_srh()
      {
          
                    document.frmbuy.action = "buyersearchScrapSaleInvoiceA.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      }
      
      function putValue(val)
      {
          var barname=val.substring(0,val.indexOf("-"));
          var barcode=val.substring(val.indexOf("-")+1,val.length);
         
           
         window.parent.document.getElementById("BUYER").value=barcode;
         window.parent.document.getElementById("BUYER_DESC").value=barname;
         window.parent.closediv('approveraddid');
         //window.parent.document.getElementById
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
               
               window.parent.closediv('approveraddid')
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
                    byer_srh();
                    return false;
                }
 
            }
      
        </script>
        <style type="text/css">
          th {
        font-size:9pt;
        font-weight:bold;
        color:white;
        background-image:url('../css/image/table-gradient.jpg');
    }
      tbody {
        height: 0px;
        overflow-y: auto;
        overflow-x: hidden;

     }
        </style>
        <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: relative;
            height:0px; 
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
           <%--  padding-top: 28px ;  --%>
        }

         }
        thead tr {

        }
        tbody {
            height: auto;
        }
          }
    </style>
<![endif]-->
    </head>
    <body onload="waitPreloadPage();document.getElementById('SEARCH_CODE').focus();" style="background-color: #f3f3f3" >
   
<form action=""  method="post" name="frmbuy" >
     <DIV align="center" id="prepage" class="lodingdiv" style="right:450px;position:absolute;z-index: 1;visibility: hidden;background: transparent;top:50px;" >
         <img src="../../css/img/loading.gif" />       
    </DIV>
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%">
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;">
       <table width="100%">
           <tr><td class="label-1">Select Buyer:</td><td><s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/></td>
    </tr><tr>
        <td colspan="2"> 
            
                <div  class="div1" style="width:100%;overflow:auto ;height:245px;">
                                   <table  style="background-color: #d0d7e5;" cellspacing="1" cellpadding="2" style="width:100%">
                                        <thead>
                                                <tr>
                                                    <th align="left" style="height:20px">Buyer</th>
                                                    <th align="left">Name</th>
                                                     <th align="left">Add&nbsp;No.</th>
                                                    <th align="left">Address</th>
                                                    <th style="width:20px;">Select</th>
                                                </tr>
                                            </thead>
                                <tbody>  
                <s:iterator value="buyeraddlist" status="st">
                         <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                                                        <td>
                                                            <s:property value="BUYERCODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="BUYERNAME"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="BUYER_ADD_NO"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="BUYER_ADDRESS"/>
                                                            <s:hidden name="UNIT_CODE" value="%{BUYERCODE}" id="UNIT_CODE%{#st.index}"/>
                                                            <s:hidden name="UNIT_DESC" value="%{BUYERNAME}" id="UNIT_DESC%{#st.index}"/>
                                                            <s:hidden name="UNIT_ADDRESS" value="%{BUYER_ADD_NO}" id="UNIT_ADDRESS%{#st.index}"/>
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
            </td></tr>
           <tr>
            <td height="1pt"  align="center" style="color:red;font-weight:bold;font-size: 14px">
                <div style="height:5pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />
                   
                </div>


            </td>
            </tr>
                  </table>

      

                  </td></tr></table>
                    

  </form>


      
    </body>
</html>
       
