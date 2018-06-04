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
          
                    document.frmbuy.action = "searchcodedescDecathlaneA.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      }
      
      function putValue(val)
      {
          var name=val.substring(0,val.indexOf("-"));
          var code=val.substring(val.indexOf("-")+1,val.length);
           
         window.parent.document.getElementById("SHIP_TO1").value=name;
         window.parent.document.getElementById("SHIP_TO").value=code;
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
    
    
    <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#eafbfc" cellpadding="5" align="center" width="100%">
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;">
       <table width="100%">
           <tr><td class="label-1">Enter Code:</td><td>
                      <s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/>
               </td>
    </tr><tr>
        <td colspan="2">
            
                <div  class="div1" style="width:100%;overflow:auto ;height:245px;">
                                    <table border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                                        <thead>
                                      <tr  class="hd" style="position: relative; top: expression(this.offsetParent.scrollTop);height:25px" >
                                            	<th style="text-align: left;" class="label-1">Code/Desc</th>
					</tr>
				</thead>
                                <tbody> 
                <s:iterator value="SearchList" status="st">
                    <tr style="background-color: #FFFFFF;font-size: 9px">
                        <td>
                            <s:hidden name="CODE" id="CODE" value="%{CODE}"/>
                            <a href="#" style="text-decoration: none" onclick="putValue('<s:property value="%{DESC}" />');"><s:property value="%{DESC}" /></a>
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
       
