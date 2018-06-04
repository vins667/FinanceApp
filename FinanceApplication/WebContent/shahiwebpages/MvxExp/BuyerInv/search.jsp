<%-- 
    Document   : search
    Created on : JAN 12, 2017, 9:40:24 AM
    Author     : Guddu Kumar
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<link type="text/css" rel="stylesheet" href="../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="../css/main.css">  

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Export Pvt Ltd.</title>


  <script language="javascript">
      
      function srh()
      {
          
                    document.frmbuy.action = "searchcodedescKohlsStoreA.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      }
      
      function putValue(a)
      {
         window.parent.document.getElementById("MNFD").value=document.getElementById(a).value;
         window.parent.closediv('approveraddid');
         //window.parent.document.getElementById
      }
      function srh1()
      {
          
                    document.frmbuy.action = "searchcodedesc1KohlsStoreA.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      }
      
      function putValue1(a)
      {
//          var name=val.substring(0,val.indexOf("-"));
//          var code=val.substring(val.indexOf("-")+1,val.length);
           
         //window.parent.document.getElementById("FORM_BUYER_CODE").value=barcode;
         window.parent.document.getElementById("HNGRSUPP").value=document.getElementById(a).value;
         window.parent.closediv('approveraddid');
         //window.parent.document.getElementById
      }
      function srh2()
      {
          
                    document.frmbuy.action = "searchcodedesc2KohlsStoreA.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      }
      
      function putValue2(a)
      {
         window.parent.document.getElementById("STAGSUPP").value=document.getElementById(a).value;
         window.parent.closediv('approveraddid');
         //window.parent.document.getElementById
      }
      function srh3()
      {
          
                    document.frmbuy.action = "searchcodedesc3KohlsStoreA.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      }
      
      function putValue3(a)
      {
         window.parent.document.getElementById("SECURITYTGRT").value=document.getElementById(a).value;
         window.parent.closediv('approveraddid');
         //window.parent.document.getElementById
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
                    srh();
                    return false;
                }

            }
            function tabE1(obj, e) {
          
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
                    srh1();
                    return false;
                }

            }
            function tabE2(obj, e) {
          
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
                    srh2();
                    return false;
                }

            }
            function tabE3(obj, e) {
          
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
                    srh3();
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
    <body onload="waitPreloadPage();document.getElementById('SEARCH_CODE').focus();" style="background-color: #eafbfc" >
   
<form action=""  method="post" name="frmbuy" >
     <DIV align="center" id="prepage" class="lodingdiv" style="right:450px;position:absolute;z-index: 1;visibility: hidden;background: transparent;top:50px;" >
         <img src="../shahiwebpages/css/img/loading.gif" />       
    </DIV>
    <s:hidden id="FLG1" name="FLG1" value="%{FLG1}" />
    <s:hidden id="FLG2" name="FLG2" value="%{FLG2}" />
    <s:hidden id="FLG3" name="FLG3" value="%{FLG3}" />
    <s:hidden id="FLG4" name="FLG4" value="%{FLG4}" />
    
    
    <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#eafbfc" cellpadding="5" align="center" width="100%">
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;">
       <table width="100%">
                <tr><td class="label-1">Enter Code:</td><td>
                   <s:if test="%{FLG1=='S1'}">
                      <s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/>
                   </s:if>
                     <s:if test="%{FLG2=='S2'}">
                      <s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE1(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/>
                   </s:if>
                     <s:if test="%{FLG3=='S3'}">
                      <s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE2(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/>
                   </s:if>
                     <s:if test="%{FLG4=='S4'}">
                      <s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE3(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/>
                   </s:if>
               </td>
    </tr><tr>
        <td colspan="2">
            
            <div  class="div1" style="width:100%;overflow:auto ;height:245px;">
                                    <table border="0" align="center" width="100%" cellpadding="1" style="background-color: #7a7" cellspacing="1" >
                                        <thead>
                                      <tr  class="hd" style="position: relative; top: expression(this.offsetParent.scrollTop);height:25px" >
                                            	<th style="text-align: left;" class="label-1">Code</th>
                                                <th style="text-align: left;" class="label-1">Desc</th>
                                                <th style="width:20px;">Select</th>
					</tr>
				</thead>
                                <tbody> 
                    <s:iterator value="SearchList" status="st">
                    <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 11px">
                                                        <td>
                                                            <s:property value="CODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="DESC"/>
                                                        
                        <s:hidden name="CODE1" id="CODE1%{#st.index}" value="%{CODE}"/>
                        <s:hidden name="DESC1" id="DESC1%{#st.index}" value="%{DESC}"/>
                        </td> 
                        <td>
                            <img src="../../css/ShahiButtons/images/icons/silk/add.png" onclick="putValue('CODE1<s:property value="%{#st.index}"/>');" />
                        </td>
                    </tr>   
                </s:iterator>
                    <s:iterator value="SearchList1" status="st">
                    <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 11px">
                                                        <td>
                                                            <s:property value="CODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="DESC"/>
                                                        
                        <s:hidden name="CODE1" id="CODE1%{#st.index}" value="%{CODE}"/>
                        <s:hidden name="DESC1" id="DESC1%{#st.index}" value="%{DESC}"/>
                        </td> 
                        <td>
                            <img src="../../css/ShahiButtons/images/icons/silk/add.png" onclick="putValue1('CODE1<s:property value="%{#st.index}"/>');" />
                        </td>
                    </tr>   
                </s:iterator>
                    <s:iterator value="SearchList2" status="st">
                    <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 11px">
                                                        <td>
                                                            <s:property value="CODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="DESC"/>
                                                        
                        <s:hidden name="CODE1" id="CODE1%{#st.index}" value="%{CODE}"/>
                        <s:hidden name="DESC1" id="DESC1%{#st.index}" value="%{DESC}"/>
                        </td> 
                        <td>
                            <img src="../../css/ShahiButtons/images/icons/silk/add.png" onclick="putValue2('CODE1<s:property value="%{#st.index}"/>');" />
                        </td>
                    </tr>   
                </s:iterator>
                    <s:iterator value="SearchList3" status="st">
                    <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 11px">
                                                        <td>
                                                            <s:property value="CODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="DESC"/>
                                                        
                        <s:hidden name="CODE1" id="CODE1%{#st.index}" value="%{CODE}"/>
                        <s:hidden name="DESC1" id="DESC1%{#st.index}" value="%{DESC}"/>
                        </td> 
                        <td>
                            <img src="../../css/ShahiButtons/images/icons/silk/add.png" onclick="putValue3('CODE1<s:property value="%{#st.index}"/>');" />
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
       
