
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
      
      function tax_srh()
      {
          
                    document.frmbuy.action = "taxsearchMeisLicenceSale.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      } 
      
      function addUnit(a,b,c)
      {   
           
           window.parent.document.getElementById('TAX_CODE').value=document.getElementById(a).value;
           window.parent.document.getElementById('TAX_TYPE').value=document.getElementById(b).value;
           window.parent.document.getElementById('TAX_PER').value=document.getElementById(c).value;;
       
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
                    tax_srh();
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
            padding-top: 0;  
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
         <img src="image/loading37.gif" />       
    </DIV>
    
    <s:hidden name="indexname" id="indexname" value="%{indexname}" />
    
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%">
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;">
       <table width="100%">
           <tr><td class="label-1">Tax Type:</td><td><s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/></td>
    </tr><tr>
        <td colspan="2">
            
                <div  class="div1" style="width:100%;overflow:auto ;height:245px;">
                                    <table  style="background-color: #7a7" border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                                        <thead>
                                      
                                                <tr>
                                                     <th align="left">Code</th>
                                                    <th align="left">Description</th>
                                                    <th align="left">%</th>
                                                    <th style="width:20px;">Select</th>
                                                </tr>
                                            
					 
				           </thead>
                                <tbody> 
                                    <s:iterator value="Taxtypelist" status="st">
                                        <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                                     
                                                        <td>
                                                            <s:property value="TAXTYPE_CODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="TAXTYPE_DESC"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="TAX_PERCENT"/>
                                                        </td>
                                                             
                                                            
                                                        <s:hidden name="TaxCode" value="%{TAXTYPE_CODE}" id="TaxCode%{#st.index}"/>
                                                        <s:hidden name="TaxDesc" value="%{TAXTYPE_DESC}" id="TaxDesc%{#st.index}"/>
                                                        <s:hidden name="TaxPers" value="%{TAX_PERCENT}" id="TaxPers%{#st.index}"/>

                                                        <td> 
                                                            <img src="../images/add.png" onclick="addUnit('TaxCode<s:property value="%{#st.index}"/>','TaxDesc<s:property value="%{#st.index}"/>','TaxPers<s:property value="%{#st.index}"/>')" />
                                                           
                                                             
                                                        </td>
                                                </tr> 
                                                
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
       
