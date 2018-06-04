<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<html>
    <head  
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         

        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('image/table-gradient.jpg');
            }
            tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;
            }

            .toolTip {
                background-color: white;

                border: solid 1px;
                font-family: Arial;
                font-size: 12px;
                font-style: normal;
                font-variant: normal;
                font-weight: normal;
                left: 0;
                padding: 0px;
                position: absolute;
                text-align: left;
                top: 0;
                visibility: hidden;
                z-index: 2;
            }


        </style>
        <!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 30px ;
        
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);                     
                  }
                tbody {
                    height: auto;
                }
                tfoot{
                    background:#3383bb;
                    font-weight:bold;
                }
                .tr2 {
                     position:absolute;
                     bottom:expression(this.offsetParent.scrollTop);
                  }
            </style>
        <![endif]--> 
        <script language="javascript">
            
      
            
            function searchdetail()
            {    
                    document.frm.action="MultipleInv.action?viewFlag=Yes";
                    document.frm.submit();
                    document.getElementById('prepage').style.visibility='';
                
            }
            
        
             
            

            
            function waitPreloadPage() { //DOM
                if (document.getElementById){
                    document.getElementById('prepage').style.visibility='hidden';                    
                }else{
                    if (document.layers){ //NS4
                        document.prepage.visibility = 'hidden';                        
                    }
                    else { //IE4
                        document.all.prepage.style.visibility = 'hidden';                        
                    }
                }
            }
        </script>

    </head> 
 
    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                   Multiple Invoice View</td></tr>
                        </table> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#D5DBDB" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                            <td class="label-1" align="left" >
                                                      <input type="radio" name="searchtype" value="INV"  checked="true"> &nbsp;Invoice No
                                                      <input type="radio" name="searchtype" value="PLAN">&nbsp;Plan No
                                                     
                                               </td>
                                                <td class="label-1" >** No : <s:textfield name="searchbos"   cssStyle="text-transform:uppercase;width:300pt" value="%{searchbos}" theme="simple" maxLength="300"/>
                                               ** Use , Separator for Multiple Inv/Plan.
                                              </td>
                                              
                                              <td align="right">
                                                   <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                                  <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                            </td>
                                        </tr>
                                          
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                         
                        <div  align="center" style="width:100.0%;">
                            <table width="100%" cellpadding="0" cellspacing="0"  border="1">
                                
                                   <tr><td>
                                        <div style="width:100%; height:100pt; overflow:auto; position:relative; "  >
                                            <table bgcolor="#85c1e9" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                <tr>
                                                     <th align="left">Sl #</th>    
                                                     <th align="left">Plan No</th>  
                                                     <th align="left">Invoice No</th>   
                                                     <th align="left">Inv Date</th> 
                                                     <th align="left">Custom Fwd</th> 
                                                      <th align="left">Discharge</th> 
                                                     <th align="right">Ctns</th> 
                                                     <th align="right">Gross Wt.</th>
                                                     <th align="right">Net Wt. </th>
                                                   </tr> 
                                                  
                                                  <s:iterator value="InvMast" id="itr" status="st1">
                                                     <tr bgcolor="white"> 
                                                            <td style="font-size:9pt"><s:property  value="%{#st1.index+1}"/></td>
                                                             <td style="font-size:9pt"><s:property value="PLAN_NO" /></td> 
                                                            <td style="font-size:9pt"><s:property value="EXCS_INV_NO" /></td> 
                                                            <td style="font-size:9pt"><s:property value="INV_DATE" /></td>
                                                            <td style="font-size:9pt"><s:property value="FWD_CUSTOM" /></td>
                                                            <td style="font-size:9pt"><s:property value="DISCHARGE" /></td>
                                                            <td style="font-size:9pt" align="right"><s:property value="CTNS" /></td> 
                                                            <td style="font-size:9pt" align="right"><s:property value="GRWT" /></td> 
                                                            <td style="font-size:9pt" align="right"><s:property value="NETWT" /></td> 
                                                              
                                                </tr>
                                                </s:iterator>    
                                               <td></td><td></td><td></td><td></td><td></td><td></td>
                                                 <td style="font-size:9pt" align="right">
                                                      <s:textfield name="ICTNS" id="ICTNS" value="%{ICTNS}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td style="font-size:9pt" align="right">
                                                      <s:textfield name="IGRWT" id="IGRWT" value="%{IGRWT}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                  
                                                <td style="font-size:9pt" align="right"  >
                                                      <s:textfield name="INETWT" id="INETWT" value="%{INETWT}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                
                                               
                                             </table>         
                                        </div>
                                      </td>
                                  </tr>
                                
                                <tr><td>
                                        <div style="width:100%; height:200pt; overflow:auto; position:relative; "  >
                                        <table bgcolor="#85c1e9" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                              
                                                <tr>   
                                                 <th align="left">Sl #</th>
                                                 <th align="left">Plan No.</th>
                                                 <th align="left">Invoice No.</th>
                                                 <th align="center">CrncyCode</th>
                                                 <th align="right" align="right">GR Decl</th>
                                                 <th align="right" align="right">FobFC</th>
                                                 <th align="left">HS Code</th>
                                                 <th align="left">Description</th>
                                                 <th align="right" align="right">Qnty</th>
                                                 <th align="right">Price FC</th>
                                                 <th align="right">Price Misc</th>
                                                 <th align="center">Scheme</th>
                                                 <th align="center">DbkSl#</th>
                                                 <th align="center">IGST%</th>
                                                 <th align="center">CGST%</th>
                                                 <th align="center">SGST%</th>
                                                  
                                            </tr>
                                           
                                              <s:iterator value="InvList" id="itr" status="st">
                                                <tr bgcolor="white"> 
                                                 <td style="font-size:9pt"><s:property  value="%{#st.index+1}"/></td>
                                                  <td style="font-size:9pt"><s:property value="PLAN_NO" /></td> 
                                                  <td style="font-size:9pt"><s:property value="EXCS_INV_NO" /></td> 
                                                   <td style="font-size:9pt" align="center"><s:property value="CRNCY_CODE" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="GR_DISC" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="FOB_AMT" /></td> 
                                                  <td style="font-size:9pt"><s:property value="HSCODE" /></td> 
                                                 <td style="font-size:9pt"><s:property value="DESCRIPTION" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="LINE_QTY" /></td> 
                                                  <td style="font-size:9pt" align="right"><s:property value="PRICE_FC" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="PRICE_MISC" /></td> 
                                                     <td style="font-size:9pt" align="left"><s:property value="SCHEME_CODE" /></td> 
                                                 <td style="font-size:9pt" align="left"><s:property value="DBK_SLNO" /></td> 
                                                 <td style="font-size:9pt" align="center"><s:property value="IGST_PER" /></td> 
                                                  <td style="font-size:9pt" align="center"><s:property value="CGST_PER" /></td> 
                                                   <td style="font-size:9pt" align="center"><s:property value="SGST_PER" /></td> 
                                                </tr>
                                                </s:iterator> 
                                                <td></td><td></td><td></td><td></td>
                                                 <td style="font-size:9pt" align="right">
                                                      <s:textfield name="TGR" id="TGR" value="%{TGR}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td style="font-size:9pt" align="right">
                                                      <s:textfield name="TFOB" id="TFOB" value="%{TFOB}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td></td></td><td></td> 
                                                
                                                <td style="font-size:9pt" align="right"  >
                                                      <s:textfield name="TQTY" id="TQTY" value="%{TQTY}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                
                                                <td></td> <td></td> <td></td><td></td>
                                               </table>
                                            
                                        </div> 
                                    </td></tr> 
              
                                  <tr><td>
                                        <div style="width:100%; height:100pt; overflow:auto; position:relative; "  >
                                            <table bgcolor="#85c1e9" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                                
                                                <s:if test="%{AccrList!=null && AccrList.size()>0}">
                                                     <th align="left">Sl #</th>    
                                                     <th align="left">Invoice No</th>  
                                                     <th align="left">Accr Desc</th>   
                                                     <th align="right">Accr Qnty</th> 
                                                     <th align="right">Accr Price</th>
                                                     <th align="right">Accr Fob </th>
                                                     <th align="left">Accr Dbk Sl#</th>
                                                     <th align="left">Accr Str Sl#</th> 
                                                </s:if>    
                                                  <s:iterator value="AccrList" id="itr" status="st1">
                                                     <tr bgcolor="white"> 
                                                            <td style="font-size:9pt"><s:property  value="%{#st1.index+1}"/></td>
                                                             <td style="font-size:9pt"><s:property value="EXCS_INV_NO" /></td> 
                                                            <td style="font-size:9pt"><s:property value="ACCR_DESC" /></td> 
                                                            <td style="font-size:9pt" align="right"><s:property value="ACCR_QTY" /></td> 
                                                            <td style="font-size:9pt" align="right"><s:property value="ACCR_PRICE" /></td> 
                                                            <td style="font-size:9pt" align="right"><s:property value="ACCR_FOB" /></td> 
                                                             <td style="font-size:9pt"><s:property value="ACCR_DBKSLNO" /></td> 
                                                            <td style="font-size:9pt"><s:property value="ACCR_STRSLNO" /></td> 
                                                          
                                                </tr>
                                                </s:iterator>    
                                              
                                             </table>         
                                        </div>
                                      </td>
                                  </tr>
                            
               
                            </table>
 
                        </div>
                    </td></tr>
            </table>

   
   
                                                     
        </form>                    


    </body>
 
</html>

