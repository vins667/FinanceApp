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
                    document.frm.action="BPOQuery.action?viewFlag=Yes";
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
                                   BuyerPO/Style/Item Query</td></tr>
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
                                             <td class="label-1" align="center" ><s:textfield name="searchitem"   cssStyle="text-transform:uppercase;width:80pt" theme="simple"  />&nbsp;
                                                        <input type="radio" name="searchtype" value="BPO" >&nbsp;BPO
                                                        <input type="radio" name="searchtype" value="STYLE">&nbsp;Style
                                                        <input type="radio" name="searchtype" value="ITEM">&nbsp;Item&nbsp;&nbsp;
                                                         <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                              </td>
                                           
                                         
                                           
                                            <td align="right">
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
                                        <div style="width:100%; height:400pt; overflow:auto; position:relative; "  >
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="1" cellspacing="1" >
                                             
                                                <tr>   
                                                 <th align="left">Sl #</th>
                                                 <th align="left">Loct</th>
                                                 <th align="left">Invoice No.</th>
                                                 <th align="left">Plan No.</th>
                                                 <th align="left">PCH</th>
                                                 <th align="left">Buyer</th>
                                                 <th align="left">MOS</th>
                                                 <th align="left">Port</th>
                                                 <th align="left">Destn.</th>
                                                 <th align="right">InvQty</th>
                                                 <th align="left">TO Date</th>
                                                 <th align="left">ETD Date</th>
                                                 <th align="left"> FTP Date</th>
                                                 <th align="left">Awb Date</th>
                                                 <th align="left">AC Holder</th>
                                                 <th align="left">Crncy</th>
                                                 <th align="right">Fob FC</th>
                                                 <th align="right">GR Decl</th>
                                                 
                                            </tr>
                                           
                                              <s:iterator value="InvList" id="itr" status="st">
                                                <tr bgcolor="white">
                                                 <td style="font-size:8pt"><s:property  value="%{#st.index+1}"/></td>
                                                 <td style="font-size:8pt"><s:property value="loct_code" /></td>   
                                                 <td style="font-size:8pt"><s:property value="excs_inv_no" /></td> 
                                                 <td style="font-size:8pt"><s:property value="inv_type" /></td> 
                                                 <td style="font-size:8pt"><s:property value="pch" /></td> 
                                                 <td style="font-size:8pt"><s:property value="buyer" /></td> 
                                                 <td style="font-size:8pt"><s:property value="mode_of_ship" /></td> 
                                                 <td style="font-size:8pt"><s:property value="clr_port" /></td> 
                                                 <td style="font-size:8pt"><s:property value="desti_cntry" /></td> 
                                                 <td style="font-size:8pt" align="right"><s:property value="inv_qty" /></td> 
                                                 <td style="font-size:8pt"><s:property value="to_date" /></td> 
                                                 <td style="font-size:8pt"><s:property value="etd_date" /></td> 
                                                 <td style="font-size:8pt"><s:property value="fwd_post" /></td> 
                                                 <td style="font-size:8pt"><s:property value="awb_date" /></td> 
                                                 <td style="font-size:8pt"><s:property value="fin_date" /></td> 
                                                 <td style="font-size:8pt"><s:property value="custom_fwd" /></td> 
                                                 <td style="font-size:8pt" align="right"><s:property value="ac_holder" /></td> 
                                                 <td style="font-size:8pt" align="right"><s:property value="lic_no" /></td> 
                                                 
                                                </tr>
                                                </s:iterator> 
                                               </table>
                                        </div>
                                    </td></tr> 
                                  
                              
                    
                             </table>
                        </div>
                    </td></tr>
            </table>

   
   
                                                     
        </form>                    


    </body>
 
</html>

