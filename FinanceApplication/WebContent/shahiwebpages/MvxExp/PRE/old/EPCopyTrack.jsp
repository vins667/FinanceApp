<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>


<script type="text/javascript" src="js/dom-drag.js"></script>
<script src="js/mvxexp.js"></script>

<LINK href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<link rel="stylesheet" href="<s:url value="css/stylishbuttons.css"/>" type="text/css" />
 
<html> 
    <head>
        <s:head /> 
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>
        <style type="text/css">
            .root
            {
                position:absolute;
                height:200px;
                width:800px;
                background-color:#F4F4F4;
                border:1px solid #2a6afe;
                padding: 0px;
                margin: 0px
            } 
            .handle
            {
                margin:0px;
                padding:0px;
                width: 100%;
                color:white;
                font-weight: bold;
                font-size: 12px
                    
            }  
            
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
            
      if (typeof window.event != 'undefined') // IE
    document.onkeydown = function() // IE
    {

       if(event.keyCode==13)
          {event.keyCode=9}


        var t=event.srcElement.type;
        var kc=event.keyCode;


        return ((kc != 8 && kc != 13) || ( t == 'text' &&  kc != 13 ) ||
            (t == 'textarea') || ( t == 'submit' &&  kc == 13))
    }
else
    document.onkeypress = function(e)  // FireFox/Others
    {
        return tabOnEnter(e.target,e)

        aaaa=e.keyCode;
       if(aaaa==13 )
          {

        
      }
        return true;
  
    }
            
            function searchdetail()
            {                
                document.frm.action="EPTRACK.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
            {               
                document.frm.action="EPTRACK.action?saveFlag=YES";
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
            
              function openpop(a)
            {    document.getElementById(a).style.display='';
            }
             function closediv(a)
            {
                document.getElementById(a).style.display='none';
            }
        </script>
 
    </head> 

    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
       
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    EP Copy Followup </td></tr>
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
                                            <td class="label-1" style="width:280px">S/B No :  <s:textfield name="SB_NO" id="SB_NO" cssStyle="text-transform:uppercase;width:75pt;font-size:9pt;" theme="simple" value="%{SB_NO}" onblur="if(this.value!='') return xmlhttpreqsb(this,'SB_DATE')"  />
                                                                                  <a href="sbViewEPTRACK.action?PARAA=SB_NO&PARAB=SB_DATE" target="handlefrm" onclick="openpop('root')">
                                                                                  <img width="14px" border=0 height="14px" src="image/Search-icon-big.png" tabindex="-1"/></a>
                                                                                  <s:textfield id="SB_DATE" name="SB_DATE"  readonly="true"  cssClass="textreadonly" cssStyle="width:70pt;font-size:9pt;" theme="simple" value="%{SB_DATE}"/>
                                                                     
                                            </td> 
                                            <td align="left">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                            </td>
                                            <td align="right" >
                                                    <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('EPTRACK.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                                    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                             
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
                                <tr><td>
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                                <th align="left">Facility</th>
                                                <th align="left">Inv No</th>
                                                <th align="left">PCH</th>
                                                <th align="left">Buyer</th>
                                                <th align="left">Mode</th>
                                                <th align="left">Clr Port</th>
                                                <th align="left">Destn</th>
                                                <th align="left">Qnty</th>
                                                <th align="left">Inv Type</th>
                                                <th align="left">Custom Fwd</th>
                                                <th align="left">TO Date</th>
                                                <th align="left">ETD Date</th>
                                                <th align="left">Post Fwd </th>
                                                <th align="left">Awb Date</th>
                                                <th align="left">77 Date</th>
                                                <th align="left">A/C Holder</th>
                                                <th align="left">Licence #</th>
                                             </tr>
                                             <tbody> 
                                        <s:iterator value="ShowDetail" status="st1">
                                                         
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt"><s:property value="loct_code"/></td>
                                                <td style="font-size:8pt"><s:property value="excs_inv_no"/></td>
                                                <td style="font-size:8pt"><s:property value="pch"/></td>
                                                <td style="font-size:8pt"><s:property value="buyer"/></td>
                                                <td style="font-size:8pt"><s:property value="mode_of_ship"/></td>
                                                <td style="font-size:8pt"><s:property value="clr_port"/></td>
                                                <td style="font-size:8pt"><s:property value="desti_cntry"/></td>
                                                <td style="font-size:8pt"><s:property value="inv_qty"/></td>
                                                <td style="font-size:8pt"><s:property value="inv_type"/></td>
                                                <td style="font-size:8pt"><s:property value="custom_fwd"/></td>
                                                <td style="font-size:8pt"><s:property value="to_date"/></td>
                                                <td style="font-size:8pt"><s:property value="etd_date"/></td>
                                                <td style="font-size:8pt"><s:property value="fwd_post"/></td>
                                                <td style="font-size:8pt"><s:property value="awb_date"/></td>
                                                <td style="font-size:8pt"><s:property value="fin_date"/></td>
                                                <td style="font-size:8pt"><s:property value="ac_holder"/></td>
                                                <td style="font-size:8pt"><s:property value="lic_no"/></td>
                                             </tr> 
                                           </s:iterator>
                                             </tbody>
                                             <s:hidden name="year" value="%{year}"/>
                                            <s:hidden name="company" value="%{company}"/>
                                            <s:hidden name="inv_no" value="%{inv_no}"/>
                                            
                                        </table>
                                    </td></tr> 

                                
                                <tr   style="background-color: whitesmoke;height: 350pt;">
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:350.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            
                                               <table border="0" align="center" bgcolor="#646D7E" cellspacing="1" cellpadding="2" width="70%"  id="tablea">
                                            
                                                 
                                                 <tr>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                    <th style="font-size:8pt" align="left">Action Desc</th>
                                                    <th style="font-size:8pt" align="left">Remarks</th>
                                                    <th style="font-size:8pt" align="left"  >Action Type</th>
                                                    <th style="font-size:8pt" align="left">UserID</th>
                                                    <th style="font-size:8pt" align="left">E/Date</th>
                                                    
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                    <s:iterator value="sbDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                             <td style="font-size:8pt"><s:property value="%{#st1.index+1}" /></td>
                                                             <td style="font-size:8pt"><s:property value="TR_DESC"/></td>
                                                             <td style="font-size:8pt"><s:property value="REMARK"/></td>
                                                             <td style="font-size:8pt"><s:property value="TR_TYPE"/></td>
                                                             <td style="font-size:8pt"><s:property value="USER_ID"/></td>
                                                             <td style="font-size:8pt"><s:property value="TR_DATE"/></td>
                                                             
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                                                <tr>
                                    
                                                         
                                                       <td colspan="2"><s:select name="stype"   cssStyle="text-transform:uppercase;width:270pt" theme="simple" list="%{TypeList}" headerKey="" headerValue="Select Action Type" listKey="LIST_CODE" listValue="LIST_NAME" /></td> 
                                                       <td colspan="2"> <s:textfield name="srem" cssStyle="text-transform:uppercase;width:270pt" theme="simple" maxLength="100"/></td>
                                                       <td  align="right" ><button  id="saveheadId"  class="sexybutton" onclick="saverec()"><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                            
                                                       <td></td>  
                                                </tr>
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

             <div id="root" class="root" style="left:50px; top:200px;display:none;width:900px;z-index: 10000">
               <table cellpadding="0" cellspacing="0">
                <tr bgcolor="#6699FF">
                    <td width="100%">
                        <div id="handle" class="handle"  style="cursor: move">Details</div>
                    </td>
                    <td style="10px"><a href="#" onclick="closediv('root')" ><img border="0" width="18px" height="18px" src="image/chrome_close_button.png"/></a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <iframe name="handlefrm" id="handlefrm" src="" scrolling="no" frameborder="0"  width="100%" height="300px"></iframe>
                    </td>
                 </tr>
             </table>
        </div>
           <script language="javascript">
            var theHandle = document.getElementById("handle");
            var theRoot   = document.getElementById("root");
            Drag.init(theHandle, theRoot);
           </script> 
   
                                                     
        </form>                    


    </body>
 
</html>

