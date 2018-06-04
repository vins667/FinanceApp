<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<script type="text/javascript" src="js/dom-drag.js"></script>

 
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
                background-image:url('css/image/table-gradient.jpg');
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
                    
                    return false;
                }

             }
    
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
                document.frm.action="DBKMovexCharge.action?viewFlag=Yes";
                document.frm.submit();
                document.getElementById('prepage').style.visibility='';
            }
             function saverec()
            {               
                document.frm.action="DBKMovexCharge.action?saveFlag=YES";
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
            
           function xmlhttpreqsb(objsrc,objtrg)
         {
                 
            var xmlHttpReq = false; 
            var self = this;   
            if (window.XMLHttpRequest) { 
                self.xmlHttpReq = new XMLHttpRequest();   
            } else if (window.ActiveXObject) {   
                self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } 
                                       
            self.xmlHttpReq.open('POST', 'sbAJAXEPTRACKAJAX.action', false); 
            self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            self.xmlHttpReq.onreadystatechange = function() {
               	if (self.xmlHttpReq.readyState == 4 && self.xmlHttpReq.status == 200)
                { 
                    
                    var a = self.xmlHttpReq.responseText;
                      var b= new Array();
                      b=a.split('#');
                   
                      if(b.length>2)
                      { 
                    	  //alert(b);
                          document.getElementById('handlefrm').src="sbViewDBKMovexCharge.action?unitparam="+objsrc.value+"&PARAA=SB_NO&PARAB=SB_DATE";
                          openpop('root');
                          objsrc.value='';
                          document.getElementById(objtrg).value='';
                      }
                      else
                      { 
                        if(b[0]=='Data Not Found')
                        {   
                          objsrc.value='';
                          document.getElementById(objtrg).value='';
                          alert(b[0]);
                          document.getElementById('handlefrm').src="sbsearch.jsp";
                          openpop('root');
                       }
                        else
                        {
                            objsrc.value=b[0];
                            document.getElementById(objtrg).value=b[1];
                        }
                      }
            	}
            }
            param=objsrc.value;
            self.xmlHttpReq.send("unitparam="+encodeURIComponent(param)+"&PARAA=SB_NO&PARAB=SB_DATE"+"&time="+(new Date()).getTime());
         }
         
         
        function calldbk(a1,a)
        {   
            document.getElementById(a1).href="predbkslviewPREINVMVX?PARAA="+a+"&INVDATE="+document.getElementById('SB_DATE').value; 
        }
         function callrosl(a1,a)
        {   
            document.getElementById(a1).href="preroslslviewPREINVMVX?PARAA="+a+"&INVDATE="+document.getElementById('SB_DATE').value; 
        }
         function callstr(a1,a,b)
        {   
            document.getElementById(a1).href="prestrslviewPREINVMVX?PARAA="+a+"&PARAB=STR"+"&dbkslnocopy="+document.getElementById('DBK_SLNO').value+"&INVDATE="+document.getElementById('SB_DATE').value; 
        } 
         function callstrmisc(a1,a,b)
        {      
            document.getElementById(a1).href="prestrslviewPREINVMVX?PARAA="+a+"&PARAB=STRMISC"+"&INVDATE="+document.getElementById('SB_DATE').value; 
        } 
            function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,4}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
                    
            		return false;
            	}
            }
        </script>
 
    </head> 

    <body onLoad="waitPreloadPage();" style="width:100%;height:100%;overflow: hidden;">
        <DIV align="center" id="prepage" style="z-index:1;position:absolute; top:50px; left:350px;background-color:transparent" class="lodingdiv" >
            <img alt=""  src="../images/loading37.gif" width="100px" height="100px"/>
            <br/>
            <font style="color:blue;font-weight: bold;text-align: center;">Loading ... ...</font>
        </DIV>
        <form action=""   method="post" name="frm" >
            <table border=0 cellpadding="0" width="100%" cellspacing="0">
                <tr><td>
                        <table border="0" cellpadding="5" cellspacing="1" width="100%" bgcolor="#7b97e0">
                            <tr>
                                <td align="center" style="width:90.0%; font-size:18.0pt; font-weight:bold; font-family:Garamond; font-style:italic; color:rgb(255,255,255);">
                                    Movex DrawBack Updation </td></tr>
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
                                                                                  <a href="sbViewDBKMovexCharge.action?PARAA=SB_NO&PARAB=SB_DATE" target="handlefrm" onclick="openpop('root')">
                                                                                  <img width="14px" border=0 height="14px" src="../images/Search-icon-big.png" tabindex="-1"/></a>
                                                                                  <s:textfield id="SB_DATE" name="SB_DATE"  readonly="true"  cssClass="textreadonly" cssStyle="width:70pt;font-size:9pt;" theme="simple" value="%{SB_DATE}"/>
                                                                     
                                            </td> 
                                            <td align="left">  
                                                <button  id="searchheadId" class="sexybutton" onclick="searchdetail()" onkeypress="if(event.keyCode==13)searchdetail()" ><span><span><span class="search" id="searchId">Go</span></span></span></button>
                                             
                                            </td> 
                                            <td align="right" >
                                                    <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('DBKMovexCharge.action?aausrid=<s:property value="%{aausrid}"/>');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
                                                    <button  id="deleteheadId"  class="sexybutton" onclick="javascript:window.close()"><span><span><span class="delete" id="deleteId" >Exit</span></span></span></button> 
                                                    <s:if test="%{FIN_DATE==null}">  
                                                              <button  id="saveheadId"  class="sexybutton" onclick="saverec()" ><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                    </s:if>
                                                    <s:else>
                                                                  <button  id="saveheadId" disabled="true" class="sexybutton" onclick="saverec()" ><span><span><span class="save" id="saveId" >Save</span></span></span></button> 
                                                    </s:else>
                                            </td>
                                        </tr>
                                          
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        <%---<div style="width:200pt;" class="toolTip" id="toolTipDiv"></div>---%>
                        <%---<div align="center" style="width:100.0%;">---%>
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr><td>
                                        <table bgcolor="#7b97e0" style="margin-top:0pt;" align="center" width="100%" cellpadding="3" cellspacing="1" >
                                            <tr>
                                                <th align="left">SB NO</th>
                                                <th align="left">SB Date</th>
                                                <th align="left">Port</th>
                                                <th align="right">DBK Due</th>
                                                <th align="right">DBK Recv</th>
                                                <th align="right">SUPL Recv</th>
                                                <th align="right">DBK Woff</th>
                                                <th align="right">STR Due</th>
                                                <th align="right">STR Recv</th>
                                                <th align="right">STR W/off</th>
                                                <th align="right">ROSL Due</th>
                                                <th align="right">ROSL Recv</th>
                                                
                                             </tr>
                                             <tbody> 
                                        <s:iterator value="sbDetail" status="st1">
                                                         
                                            <tr bgcolor="#f2f9fb">
                                                <td style="font-size:8pt"><s:property value="SB_NO"/></td>
                                                <td style="font-size:8pt"><s:property value="SB_DATE"/></td>
                                                <td style="font-size:8pt"><s:property value="CLAIM_PORT"/></td>
                                                <td style="font-size:8pt" align="right"><s:property value="DBK_DUE"/></td>
                                                <td style="font-size:8pt" align="right"><s:property value="DBK_RECV"/></td>
                                                <td style="font-size:8pt"align="right"><s:property value="SUPL_RECV"/></td>
                                                <td style="font-size:8pt"align="right"><s:property value="DBK_WOFF"/></td>
                                                <td style="font-size:8pt"align="right"><s:property value="STR_DUE"/></td>
                                                <td style="font-size:8pt"align="right"><s:property value="STR_RECV"/></td>
                                                <td style="font-size:8pt"align="right"><s:property value="STR_WOFF"/></td>
                                                <td style="font-size:8pt"align="right"><s:property value="ROSL_DUE"/></td>
                                                <td style="font-size:8pt"align="right"><s:property value="ROSL_RECV"/></td>
                                                
                                             </tr> 
                                           </s:iterator>
                                             </tbody>
                                             <s:hidden name="year" value="%{year}"/>
                                            <s:hidden name="company" value="%{company}"/>
                                            <s:hidden name="inv_no" value="%{inv_no}"/>
                                            
                                        </table>
                                    </td></tr> 

                                
                                <tr   style="background-color: whitesmoke;height: 350pt;">
                                 <td>   
                                  <table  bgcolor="#f2f9fb"  align="center" width="100%" cellpadding="3" cellspacing="1" >
                                   <tr>
                                       <td valign="top" >
                                            <div  style="width:100%; overflow:auto; height:350.0pt;border-width:1pt;border-color:whitesmoke; border-style:none">
                                            
                                               <table border="0" align="center" bgcolor="#7b97e0" cellspacing="1" cellpadding="2" width="100%"  id="tablea">
                                            
                                                 
                                                 <tr>
                                                    <th style="font-size:8pt" align="left">Sl#</th>
                                                     <th style="font-size:8pt" align="left">Invoice No</th>
                                                    <th style="font-size:8pt" align="left"  >CO No</th>
                                                    <th style="font-size:8pt" align="left">Line</th>
                                                    <th style="font-size:8pt" align="left">Item</th>
                                                    <th style="font-size:8pt" align="right">Qnty</th>
                                                    <th style="font-size:8pt" align="right">KGS Qty</th>
                                                    <th style="font-size:8pt" align="center">Crncy</th>
                                                    <th style="font-size:8pt" align="center">UOM</th>
                                                    <th style="font-size:8pt" align="right">Price FC</th>
                                                    <th style="font-size:8pt" align="right">Misc Pr</th>
                                                    <th style="font-size:8pt" align="right">GR Decl#</th>
                                                    <th style="font-size:8pt" align="center">DBKConv</th>
                                                    <th style="font-size:8pt" align="center">Scheme</th>
                                                    <th style="font-size:8pt" align="left">DBK Sl#</th>
                                                    <th style="font-size:8pt" align="left">STR#</th>
                                                    <th style="font-size:8pt" align="left">STR Misc</th>
                                                    <th style="font-size:8pt" align="left">ROSL Sl#</th>
                                                    <th style="font-size:8pt" align="left">Misc Code</th>
                                                    <th style="font-size:8pt" align="left">Misc Amt</th>
                                                     
                                                  </tr>                                                
                                               
                                                  <tbody> 
                                                    <s:iterator value="ShowDetail" status="st1">
                                                        <tr bgcolor="#f2f9fb">
                                                              <td style="font-size:8pt"><s:textfield name="SL" id="SL" cssStyle="width:30px"  readonly="true" value="%{#st1.index+1}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="INVOICE_NO" id="INVOICE_NO" cssStyle="width:70px"  readonly="true" value="%{INVOICE_NO}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="CO_NO" id="CO_NO" cssStyle="width:70px"  readonly="true" value="%{CO_NO}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="CO_LINE" id="CO_LINE" cssStyle="width:20px"  readonly="true" value="%{CO_LINE}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="ITEM_NO" id="ITEM_NO" cssStyle="width:40px"  readonly="true" value="%{ITEM_NO}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="QTY_ENDORS" id="QTY_ENDORS" cssStyle="width:40px;text-align:right"  readonly="true" value="%{QTY_ENDORS}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="QTY_KGS" id="QTY_KGS" cssStyle="width:50px;text-align:right"  readonly="true" value="%{QTY_KGS}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="CRNCY_CODE" id="CRNCY_CODE" cssStyle="width:40px;text-align:center"  readonly="true" value="%{CRNCY_CODE}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="UOM" id="UOM" cssStyle="width:40px;text-align:center"  readonly="true" value="%{UOM}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="PRICE_FC" id="PRICE_FC" cssStyle="width:60px;text-align:right"  readonly="true" value="%{PRICE_FC}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="PRICE_MISC" id="PRICE_MISC" cssStyle="width:60px;text-align:right"  readonly="true" value="%{PRICE_MISC}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="GR_DECL" id="GR_DECL" cssStyle="width:60px;text-align:right"  readonly="true" value="%{GR_DECL}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="DBK_CONV" id="DBK_CONV" cssStyle="width:60px;text-align:right"  readonly="true" value="%{DBK_CONV}" cssClass="textreadonly" theme="simple"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="SCHEME_CODE" id="SCHEME_CODE" cssStyle="width:60px"  readonly="true" value="%{SCHEME_CODE}" cssClass="textreadonly" theme="simple"/></td> 
                                                            
                                                              <td style="font-size:8pt"><s:textfield name="DBK_SLNO" id="DBK_SLNO%{#st1.index}" cssStyle="width:80px"   value="%{DBK_SLNO}" cssClass="texts" theme="simple"/> 
                                                                     <a href="#" target="handlefrm"  id="dbkslhref<s:property value="%{#st1.index}"/>" onclick="calldbk('dbkslhref<s:property value="%{#st1.index}"/>','DBK_SLNO<s:property value="%{#st1.index}"/>');openpop('root')">
                                                                     <img width="14px" border=0 height="14px" src="../images/Search-icon-big.png"/>
                                                                  </a>
                                                              </td>  
                                                              <td style="font-size:8pt"><s:textfield name="STR_SLNO" id="STR_SLNO%{#st1.index}" cssStyle="width:40px"    value="%{STR_SLNO}" cssClass="texts" theme="simple"/>
                                                                   <a href="#" target="handlefrm"  id="strslhref<s:property value="%{#st1.index}"/>" onclick="callstr('strslhref<s:property value="%{#st1.index}"/>','STR_SLNO<s:property value="%{#st1.index}"/>');openpop('root')">
                                                                     <img width="14px" border=0 height="14px" src="../images/Search-icon-big.png"/>
                                                                  </a> 
                                                              </td>        
                                                              <td style="font-size:8pt"><s:textfield name="STR_MISC" id="STR_MISC%{#st1.index}" cssStyle="width:60px"    value="%{STR_MISC}" cssClass="texts" theme="simple"/>
                                                                   <a href="#" target="handlefrm"  id="strmischref<s:property value="%{#listid.index}"/>" onclick="callstrmisc('strmischref<s:property value="%{#listid.index}"/>','STR_MISC<s:property value="%{#listid.index}"/>');openpop('root')">
                                                                       <img width="14px" border=0 height="14px" src="../images/Search-icon-big.png"/>
                                                                   </a>  
                                                              </td>          
                                                              <td style="font-size:8pt"><s:textfield name="ROSL_SLNO" id="ROSL_SLNO%{#st1.index}" cssStyle="width:80px" value="%{ROSL_SLNO}" cssClass="texts" theme="simple"/> 
                                                                  <a href="#" target="handlefrm"  id="roslslhref<s:property value="%{#st1.index}"/>" onclick="callrosl('roslslhref<s:property value="%{#st1.index}"/>','ROSL_SLNO<s:property value="%{#st1.index}"/>');openpop('root')">
                                                                     <img width="14px" border=0 height="14px" src="../images/Search-icon-big.png"/>
                                                                  </a>
                                                              </td>         
                                                              <td style="font-size:8pt"><s:select name="MISC_CODE" id="MISC_CODE" cssStyle="width:80px" value="%{MISC_CODE}" cssClass="texts" theme="simple" list="%{TypeList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME"/></td> 
                                                              <td style="font-size:8pt"><s:textfield name="MISC_AMOUNT" id="MISC_AMOUNT" cssStyle="width:80px;text-align:right"  value="%{MISC_AMOUNT}" onblur="validatenumber(this)" cssClass="texts" theme="simple"/></td> 
                                                              <s:hidden name="SR_NO" value="%{SR_NO}"/>
                                                              <s:hidden name="YEAR" value="%{YEAR}"/>
                                                              <s:hidden name="COMPANY" value="%{COMPANY}"/>
                                                              <s:hidden name="INV_NO" value="%{INV_NO}"/>
                                                          </tr>
                                                    </s:iterator>
                                                </tbody>
                               
                                    </table>
                                           
                                        </div>  
                                      </td>
                                 </tr>
                              </table>  
                                 </td>
                              </tr>
                             
                               <tr>
                                <td height="2pt"  align="center" style="color:red;font-weight:bold">
                                    <div style="height:5pt">
                                        <s:actionerror />
                                        <s:fielderror />
                                        <s:actionmessage />
                                      </div>

 
                                 </td>
                                </tr>                               
                                                              
                                                              
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

