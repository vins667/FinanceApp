
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
          
                    document.frmbuy.action = "taxsearchScrapSaleGSTA.action";
                    document.frmbuy.submit();
                    document.getElementById('prepage').style.visibility = '';
                
      } 
      
      function addUnit(a,b,c,d,e)
      {    
           window.parent.document.getElementById('HSN_CODE_L<s:property value="%{indexname}"/>').value=document.getElementById(a).value;
           window.parent.document.getElementById('HSN_CODE_PER_L<s:property value="%{indexname}"/>').value=document.getElementById(b).value;
           window.parent.document.getElementById('CGST_PER_L<s:property value="%{indexname}"/>').value=document.getElementById(c).value;
           window.parent.document.getElementById('SGST_PER_L<s:property value="%{indexname}"/>').value=document.getElementById(d).value;
           window.parent.document.getElementById('IGST_PER_L<s:property value="%{indexname}"/>').value=document.getElementById(e).value;
       
         window.parent.closediv('approveraddid2');
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
    <s:hidden name="FACTC1" id="FACTC1" value="%{FACTC1}" />
    <s:hidden name="WAREHO1" id="WAREHO1" value="%{WAREHO1}" />
    <s:hidden name="STATE1" id="STATE1" value="%{STATE1}" />
   
    
 <table align="center" width="100%"><tr><td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;" >

              <table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%">
                  <tr><td>
               
               <table width="100%">
                   <tr><td  valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;">
       <table width="100%">
           <tr><td class="label-1">Select Tax Type:</td><td><s:textfield name="SEARCH_CODE" id="SEARCH_CODE" onKeyPress="tabE(this, event);" theme="simple" value="%{SEARCH_CODE}" cssStyle="width:180px;text-transform:uppercase" cssClass="texts"/></td>
    </tr><tr>
        <td colspan="2">
            
                <div  class="div1" style="width:100%;overflow:auto ;height:245px;">
                                    <table border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                                        <thead>
                                      
                                                <tr>
                                                     <th align="left">Code</th>
                                                    <th align="left">%</th>
                                                    <th align="left">CGST</th>
                                                    <th align="left">SGST</th>
                                                    <th align="left">IGST</th>
                                                    <th style="width:20px;">Select</th>
                                                </tr>
                                            
					 
				           </thead>
                                <tbody> 
                                    <s:iterator value="Taxtypelist" status="st">
                                        <tr style="color:#000000;background-color: #fff;font-weight: normal;font-size: 9px">
                                     
                                                        <td>
                                                            <s:property value="HSN_CODE"/>
                                                        </td>
                                                         <td>
                                                            <s:property value="HSN_CODE_PER"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="BCGST_PER"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="BSGST_PER"/>
                                                        </td>
                                                        <td>
                                                            <s:property value="BIGST_PER"/>
                                                        </td>
                                                        <s:hidden name="TAXTYPE_CODE1" value="%{HSN_CODE}" id="TAXTYPE_CODE1%{#st.index}"/>
                                                        <s:hidden name="TAXTYPE_DESC1" value="%{HSN_CODE_PER}" id="TAXTYPE_DESC1%{#st.index}"/>
                                                        <s:hidden name="BCGST" value="%{BCGST_PER}" id="BCGST%{#st.index}"/>
                                                        <s:hidden name="BSGST" value="%{BSGST_PER}" id="BSGST%{#st.index}"/>
                                                        <s:hidden name="BIGST" value="%{BIGST_PER}" id="BIGST%{#st.index}"/>
                                                        
                                                        <td> 
                                                            <img src="css/images/icons/silk/add.png" onclick="addUnit('TAXTYPE_CODE1<s:property value="%{#st.index}"/>','TAXTYPE_DESC1<s:property value="%{#st.index}"/>','BCGST<s:property value="%{#st.index}"/>','BSGST<s:property value="%{#st.index}"/>','BIGST<s:property value="%{#st.index}"/>');" />
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
       
