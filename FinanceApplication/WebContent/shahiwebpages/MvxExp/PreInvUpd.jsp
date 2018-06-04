<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="../css/main.css"/>" rel="stylesheet"   type="text/css"/>

<html>
    <head>
         <s:head />
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd - Logistics</title>

        <script language="javascript">
           function invlist()
            {
                window.open("INVHELP.action?Eview=YES", "empList", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=600m,height=400, top=192,left=367");
            }
      
       function GetSearch()
        { 
              document.frm.action="PREINVUPD.action?showFlag=YES";
              document.frm.submit()
        }
        function GetUpdate()
        {
              document.frm.action="PREINVUPD.action?updFlag=YES";
              document.frm.submit()
        }
         
         
       </script>
 
    </head>
    <body class="body1" oncontextmenu="return false;">
        <form action=""   method="post" name="frm"  >
        <table height="86%"  border="0" bgcolor="#f2f2f2" cellpadding="0" align="center" width="100%" >
          <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
               <table align="center"  bgcolor="#424242" width="100%" cellpadding="5">
               <tr><td  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;">GAP ASN DETAIL</td></tr>
               </table>
            
               <table align="center"  width="90%" cellpadding="1">
               <tr><td valign="top" align="center"  >
                   <table width="100%" cellpadding="0">
                   <tr> 
                     <td align="left" class="label-1"> Invoice No. : <s:textfield name="SINVNO" cssStyle="text-transform:uppercase;width:100pt" theme="simple" value="%{SINVNO}" maxLength="10"/>
                        <input type="button" name="INV"  value="Help"  onclick="invlist()" readonly="true" onkeypress="return tabE(this, event)" cssStyle="width:20pt">
                    </td>
                    
                    
                     <td align="right" > <input  type="button" name="bnt" style="width:50pt" onclick="GetSearch()" value="Go" class="submitbutton1">
                     <td align="right" > <input  type="button" name="bnt" style="width:50pt" onclick="GetUpdate()" value="Save" class="submitbutton1">
                      
                       
                     </td>
                  </tr>
                  </table>
              </td></tr>

              <tr><td height="280pt" valign="top" style="border-width:1pt;border-color:#2680b5;border-style:solid;" >
                 <div  style="position:relative;width:100%;overflow:auto ;height:320pt;">
                     <table align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                     <thead >
                       <tr class="hd" style="position:relative; top: expression(this.offsetParent.scrollTop);height:20pt" >
                         <th class="label-1" style="width:8%;text-align: center" >Loct</th>
                          <th class="label-1" style="width:8%;text-align: center" >A/C Holder</th>
                           <th class="label-1" style="width:8%;text-align: center" >Inv Date</th>
                          <th class="label-1" style="width:8%;text-align: right" >Buyer</th>
                          <th class="label-1" style="width:8%;text-align: center" >Qty</th>
                          <th class="label-1" style="width:8%;text-align: center" >Ship Mode</th>
                          <th class="label-1" style="width:8%;text-align: center" >TO Date</th>
                          <th class="label-1" style="width:8%;text-align: right" >ETD Date</th>
                          <th class="label-1" style="width:8%;text-align: right" >77 Date</th>
                     </tr>
                      </thead> 
                       <tbody>
                           <s:if test="%{INVList!=null}">
                           <s:iterator value="INVList" status="st1" id="itr">
                              <s:if test="%{((Aupdate!=null && Aupdate=='YES') || (Aview!=null && Aview=='YES')) }">
                                     <a  href="javascript:window.location.href='CallMstUpdateASNVIEW.action?UPDCODE=<s:property value="ASN_NO"/>'" >
                              </s:if>
                               <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                <td style="font-size:8pt;text-align: center"><s:property value="LOCT_CODE"/></td>
                                <td style="font-size:8pt;text-align: center"><s:property value="PO_NO"/></td>
                                <td style="font-size:8pt;text-align: right"><s:property value="ASN_NO"/></td>
                                <td style="font-size:8pt;text-align: center"><s:property value="ASN_DATE"/></td>
                                <td style="font-size:8pt;text-align: center"><s:property value="FY_OUT_DATE"/></td>
                                <td style="font-size:8pt;text-align: center"><s:property value="ETA_DATE"/></td>
                                <td style="font-size:8pt;text-align: right"><s:property value="TOTAL_QTY"/></td>
                                <td style="font-size:8pt;text-align: right"><s:property value="TOTAL_PKGS"/></td>
                                <td style="font-size:8pt;text-align: center"><s:property value="SHIP_MODE"/></td>
                                <td style="font-size:8pt;text-align: right"><s:property value="INV_NO"/></td>
                                <td style="font-size:8pt;text-align: center"><s:property value="UPLOAD_DATE"/></td>
                                <td align="center" style="font-size:9pt">
                                <a  href="ASNVIEW.action?printFlag=Yes&UPDCODE=<s:property value="ASN_NO"/>" ><img style="border:0" height="20pt" width="20pt" src="../image/pdf.png" alt="PDF" title="PDF Report" ></a>
                              </td>
                              </tr> 
                          </s:iterator> 
                          </s:if>
                              
                      </tbody>
                   </table>
                </div>
            </td></tr>
          </table>

          </td></tr>
  
          <tr style="height:5%" bgcolor="#D8D8D8" ><td style="border-width:1pt;border-color:black;border-style:solid;" >
              <table   align="center" width="100%" >
                  <tr><td align="left" > Date : <s:property value="%{currentdate}" /> </td>
                      <td valign="bootom" align="center" >
                <div style="height:3pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />
                </div>
                </td>

                <td align="right" > User : <s:property value="%{#session.sessUserId}"/> </td></tr>
              </table>
          </td></tr>
        </table>
            <input type="hidden" name="aausrid" value="<s:property value="%{#session.sessUserId}"/>" >
             


        </form>
    </body>
</html>


