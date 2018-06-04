<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<LINK href="../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
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
                    document.frm.action="BosBillBreakup.action?viewFlag=Yes";
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
                                   Dispatched BOS PCH Amount Breakup</td></tr>
                        </table> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" width="100%" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <table bgcolor="#f2f9fb" width="100%" cellspacing="1" cellpadding="3">
                                        <tr>
                                               <td class="label-1" >Loct : <s:textfield name="searchloct"    cssStyle="text-transform:uppercase;width:40pt" value="%{userloct}" theme="simple" maxLength="10"/></td>
                                               <td class="label-1" >Amount : <s:textfield name="searchamount"  cssStyle="text-transform:uppercase;width:70pt" value="%{searchamount}" theme="simple" maxLength="10"/><td>
                                               <td class="label-1" >**Bos No : <s:textfield name="searchbos"   cssStyle="text-transform:uppercase;width:200pt" value="%{searchbos}" theme="simple" maxLength="200"/>
                                               ** Use , Separator for Multiple BOS.
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
                                        <div style="width:100%; height:280pt; overflow:auto; position:relative; "  >
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                             
                                                <tr>   
                                                 <th align="left">Sl #</th>
                                                 <th align="left">Loct</th>
                                                 <th align="left">Invoice No.</th>
                                                 <th align="left">Bos No.</th>
                                                 <th align="left">Bos Date</th>
                                                 <th align="left">Unit</th>
                                                 <th align="left">Port</th>
                                                 <th align="left">Transporter</th>
                                                 <th align="left">Dispatch Via</th>
                                                 <th align="right"  >Plan CFT</th>
                                                 <th align="right" align="right">Qnty</th>
                                                 <th align="right" align="right">Ctns</th>
                                                 <th align="center">PCH</th>
                                            </tr>
                                           
                                              <s:iterator value="InvList" id="itr" status="st">
                                                <tr bgcolor="white">
                                                 <td style="font-size:9pt"><s:property  value="%{#st.index+1}"/></td>
                                                 <td style="font-size:9pt"><s:property value="LOCT" /></td>   
                                                 <td style="font-size:9pt"><s:property value="EXCS_INV_NO" /></td> 
                                                 <td style="font-size:9pt"><s:property value="BOS_NO" /></td> 
                                                 <td style="font-size:9pt"><s:property value="BOS_DATE" /></td> 
                                                 <td style="font-size:9pt"><s:property value="UNIT" /></td> 
                                                 <td style="font-size:9pt"><s:property value="PORT" /></td> 
                                                 <td style="font-size:9pt"><s:property value="TRANSPORTER" /></td> 
                                                 <td style="font-size:9pt"><s:property value="DISPATCH_VIA" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="PLANCFT" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="QNTY" /></td> 
                                                 <td style="font-size:9pt" align="right"><s:property value="CTNS" /></td> 
                                                 <td style="font-size:9pt" align="center"><s:property value="PCH" /></td> 
                                                  
                                                 
                                                </tr>
                                                </s:iterator> 
                                                <td></td><td></td><td></td><td></td><td></td></td><td></td><td></td><td></td><td></td>
                                                <td style="font-size:9pt" align="right">
                                                      <s:textfield name="TCFT" id="TCFT" value="%{TCFT}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td style="font-size:9pt" align="right"  >
                                                      <s:textfield name="TQTY" id="TQTY" value="%{TQTY}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td style="font-size:9pt" align="right">
                                                      <s:textfield name="TCTNS" id="TCTNS" value="%{TCTNS}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td></td>
                                               </table>
                                            
                                        </div>
                                    </td></tr> 
                                 <tr>     
                                 <td style="font-size:10pt; font-weight: bold"> 
                                    <div style="width:100%; height:100pt; overflow:auto; position:relative; "  >   
                                       <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="40%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                               <th align="left">Sl #</th>
                                                <th align="left">Loct</th>
                                                <th align="left">PCH</th>
                                                <th align="right">Plan CFT</th>
                                                <th align="right">Qnty </th>
                                                <th align="right">Ctns</th>
                                                  
                                             </tr>
                                           
                                                <s:iterator value="PchList" status="st" >
                                                     <tr bgcolor="#f2f9fb">
                                                       
                                                            <td style="font-size:9pt"><s:property value="%{#st.index+1}" /></td>
                                                             <td style="font-size:9pt"><s:property value="LOCT"/></td>
                                                             <td style="font-size:9pt"><s:property value="PCH"/></td>
                                                             <td style="font-size:9pt" align="right"><s:property value="PLAN_CFT"/></td>
                                                             <td style="font-size:9pt" align="right"><s:property value="QTY"/></td>
                                                             <td style="font-size:9pt " align="right"><s:property value="CTNS"/></td>
                                                            
                                                     </tr> 
                                                </s:iterator>
                                                
                                                <td></td> <td></td><td>Total #</td>
                                                <td style="font-size:9pt" align="right">
                                                      <s:textfield name="PCFT" id="PCFT" value="%{PCFT}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td style="font-size:9pt" align="right"  >
                                                      <s:textfield name="PQTY" id="PQTY" value="%{PQTY}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                <td style="font-size:9pt" align="right">
                                                      <s:textfield name="TPTNS" id="PCTNS" value="%{PCTNS}" readonly="readonly" theme="simple" cssStyle="width:70pt;text-align:right;font-weight:bold;color:red;background-color:yellow;overflow: auto;"   />  
                                                </td>
                                                
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

