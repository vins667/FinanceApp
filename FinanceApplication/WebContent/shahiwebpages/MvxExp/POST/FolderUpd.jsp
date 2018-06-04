<%-- 
    Document   : FolderUpd
    Created on : May 31, 2016, 4:56:34 PM
    Author     : Guddu Kumar
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<LINK href="../../css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="../../css/ShahiButtons/shahibuttons.css"/>
<link rel="stylesheet" href="<s:url value="../../css/stylishbuttons.css"/>" type="text/css" />
<html> 
    <head>
        <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            th {
                font-size:9pt;
                font-weight:bold;
                color:#0066aa;
                background-image:url('../../image/table-gradient.jpg');
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
                document.frm.action="FOLDERUPD.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
            {               
                document.frm.action="FOLDERUPD.action?saveFlag=YES";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             
                       function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
                    
            		return false;
            	} 
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
            <img alt=""  src="../../image/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Folder/Doc Number Entry</td></tr>
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
                                            <td class="label-1" style="width:200px">Awb No <s:textfield name="search_awb" cssStyle="text-transform:uppercase;width:100pt" theme="simple" maxLength="15"/>
                                            </td> 
                                            <td class="label-1" style="width:200px">Awb Date <sx:datetimepicker id="search_date" name="search_date" value="%{search_date}" />
                                                 
                                            </td> 
                                             <td class="label-1" style="width:200px">H/Awb No <s:textfield name="search_hawb" cssStyle="text-transform:uppercase;width:60pt" theme="simple" maxLength="15"/>
                                            </td> 
                                             <td class="label-1" style="width:200px">Fin Date <sx:datetimepicker id="search_findate" name="search_findate" value="%{search_findate}" />
                                            </td> 
                                             <td class="label-1" style="width:200px"># <s:textfield name="searchi" cssStyle="text-transform:uppercase;width:40pt" theme="simple" maxLength="15"/>
                                            </td> 
                                            
                                            <td align="right">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail();"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                            <button  id="saveheadId"  class="sexybutton" onclick="saverec();"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                             <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close();"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                           </td>
                                        </tr>
                                          
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        <div style="width:200pt;" class="toolTip" id="toolTipDiv"></div>
                        <div align="center" style="width:100.0%;">
                            <table width="100%" cellpadding="0" cellspacing="0">
                              
                                <tr   style="background-color: whitesmoke;height: 350pt;">
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:350.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                               <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                                 
                                                   <tr>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left"  >Awb No</th>
                                                    <th style="font-size:8pt" align="left"  >Awb Date</th>
                                                    <th style="font-size:8pt" align="left"  >Fin Date</th>
                                                    <th style="font-size:8pt" align="left"  >Term #</th>
                                                    <th style="font-size:8pt" align="left"  >S/Bill No</th>
                                                    <th style="font-size:8pt" align="left">S/B Date</th>
                                                    <th style="font-size:8pt" align="left">Let Exp Date</th>
                                                    <th style="font-size:8pt" align="left">Crncy</th>
                                                     <th style="font-size:8pt" align="right">Fob FC</th> 
                                                      <th style="font-size:8pt" align="right">GR Decl</th>
                                                      <th style="font-size:8pt" align="right">DBK Conv</th>
                                                      <th style="font-size:8pt" align="right">INR Conv</th>
                                                     <th style="font-size:8pt" align="right">Qnty</th> 
                                                      
                                                     <th style="font-size:8pt" align="left">Invoice #</th>
                                                   
                                                    <th style="font-size:8pt" align="right">Folder #</th>
                                                    <th style="font-size:8pt" align="right">Doc No.</th>
                                                    
                                                  </tr>                                                
                                                
                                                  <tbody> 
                                                    <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb"> 
                                                            <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                            
                                                            <td style="font-size:8pt"><s:property value="AWB_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="AWB_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="FIN_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="FIN_TERM"/></td>
                                                            <td style="font-size:8pt"><s:property value="SB_NO"/></td>
                                                            <td style="font-size:8pt"><s:property value="SB_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="LET_EXP_DATE"/></td>
                                                            <td style="font-size:8pt"><s:property value="CRNCY_CODE"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="FOB_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="GR_DECL_AMT"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="DBK_CONV"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="INR_CONV"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:property value="SHIP_QTY"/></td>
                                                            <td style="font-size:8pt"><s:property value="INVOICE_NO"/></td>
                                                             <td style="font-size:8pt;text-align: right"><s:textfield id="FOLDER_NO" name="FOLDER_NO" value="%{FOLDER_NO}" onblur="validatenumber(this)" cssStyle="width:90px;text-align:right" theme="simple"/></td>
                                                            <td style="font-size:8pt;text-align: right"><s:textfield id="DOC_NO" name="DOC_NO" value="%{DOC_NO}" cssStyle="width:90px;text-align:right" theme="simple"/></td>
                                                           <s:hidden name="INVOICE_NO" value="%{INVOICE_NO}"/>
                                                          </tr>
                                                    </s:iterator> 
                                                </tbody>
                                         </td>
                                   </tr>
                                               
                                            </table>
                                           
                                        </div>  
                                      </td>
                                 </tr>
                              </table>  
                              </tr>
                            </tr>
                               <tr>
                                <td height="1pt"  align="center" style="color:red;font-weight:bold">
                                    <div style="height:5pt">
                                        <s:actionerror />
                                        <s:fielderror />
                                        <s:actionmessage />
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