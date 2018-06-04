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
 <script src="../js/datetimepicker_css.js"></script>
 <script language="javascript">
 
 function validatenumber(a)
            {
                k = a.value;
                if (k != "" && !k.match(/^\d+$|^\d+\.\d{1,3}$/) && !k.match(/^.\d{1,2}$/))
                {
                    alert('Invalid Input, Only Numbers Allowed');
                    a.value = "";
                    a.focus();
                    return false;

                }
                return true;
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
 function GetAmt(a)
    {
        var b = document.frm.BOX_LN[a].value;
        var c = document.frm.BOX_WD[a].value;
        var d = document.frm.BOX_HT[a].value;
        var e = document.frm.INV_PKGS[a].value;
        var UM = document.frm.CT_UOM[a].value;
        if (UM=="CM")
          {  var cbm1=(b*c*d*e)/1000000;
             var cft1=cbm1*35.314;
             var vol1=(b*c*d*e)/6000;
          }
          else{ 
               var cbm1=(b*c*d*e)/61025;
               var cft1=cbm1*35.314;
               var vol1=(b*c*d*e)/366;
              }
       
        document.frm.CBM[a].value= (cbm1.toFixed(3));
        document.frm.CFT[a].value=(cft1.toFixed(3));
        document.frm.BOX_VOL[a].value=(vol1.toFixed(3));
    }
     
      
    function Gettot()
    {
        var sbox=0; scbm=0; scft=0; svol=0
        var inc =0;
        for (i=0; i<document.frm.INV_PKGS.length; i++)
        {
            inc = document.frm.INV_PKGS[i].value;
            if(inc!="")
            {  n =  +(inc);
               sbox += n ;
             }

            inc = document.frm.CBM[i].value;
            if(inc!="")
            {  n =  +(inc);
               scbm += n ;
             }
            inc = document.frm.CFT[i].value;
            if(inc!="")
            {  n =  +(inc);
               scft += n ;
             }
             inc = document.frm.BOX_VOL[i].value;
            if(inc!="")
            {  n =  +(inc);
               svol += n ;
             }
        }
        document.frm.RBOX.value=sbox;
        document.frm.RCBM.value=scbm.toFixed(3);
        document.frm.RCFT.value=scft.toFixed(3);
        document.frm.RVOL.value=svol.toFixed(3);
        
    }
    
    function AddRow(tableID)
    {   var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var colCount = table.rows[1].cells.length;
        for(var i=0; i<colCount; i++)
        {   var newcell = row.insertCell(i);
            newcell.innerHTML = table.rows[1].cells[i].innerHTML;
//            alert(newcell.childNodes[0].type);
             switch(newcell.childNodes[0].type) {
                 case "text":
                     newcell.childNodes[0].value = "";
                     break;
                  case "checkbox":
                      newcell.childNodes[0].checked = false;
                      break;
                  case "select-one":
                      newcell.childNodes[0].selectedIndex = 0;
                      break;
              }
          }
          document.frm.BOX_LN[rowCount-1].value ="";
          document.frm.BOX_WD[rowCount-1].value ="";
          document.frm.BOX_HT[rowCount-1].value ="";
          document.frm.CT_UOM[rowCount-1].value ="";
          document.frm.INV_PKGS[rowCount-1].value ="";
          document.frm.CBM[rowCount-1].value ="";
          document.frm.CFT[rowCount-1].value ="";
          document.frm.BOX_VOL[rowCount-1].value ="";
          resetbtnmethod(rowCount);
      }

    
    
    
   function validateinput(){
       if(document.frm.FY_CODE.value=="")
        {
           alert("Please Enter Factroy")
           document.frm.FY_CODE.focus();
           return false;
        }
       if(document.frm.CFS_CODE.value=="") 
        {
           alert("Please Enter CFS Code")
           document.frm.CFS_CODE.focus();
           return false;
        } 
        
          if (dojo.widget.byId("DEL_DATE").getValue()=="")
          {
               alert("Please Enter Cutoff Date ")
               document.frm.DEL_DATE.focus();
               return false;
           }
           if (dojo.widget.byId("EX_FY_DATE").getValue()=="")
          {
               alert("Please Enter EX-Factory    Date ")
               document.frm.EX_FY_DATE.focus();
               return false;
           }
        if(document.frm.GRWT.value=="")
         {
            alert("Please Enter Gross Weight !!!");
            document.frm.GRWT.focus()
            return false;
         }
         
    return true;
    }
       function GetSearch()
        { 
               document.frm.action="TOPLAN.action?showFlag=Yes";
               document.frm.submit();
           
        }

    function recsave()
    {
       if(validateinput()){
         if(confirm('You Want to save Records ? '))
        {
            document.getElementById("saveid").disabled=true;
            document.frm.action="TOPLAN.action?saveFlag=Yes";
            document.frm.submit();
        }
        }else{
            document.getElementById("saveid").disabled=false;
            return ;}
     }
 function BoxSave()
    {
        
         if(confirm('You Want to save Records ? '))
        {
            document.getElementById("saveid1").disabled=true;
            document.frm.action="callsaveBoxTOPLAN.action?saveFlag=Yes";
            document.frm.submit();
        }
        else{
            document.getElementById("saveid1").disabled=false;
            return ;}
     }
    
 </script>
         <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: absolute;
            height: 550px;
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            
        }
        thead tr {
            position: relative;
        }
        tbody {  
            height: auto;
        }

          
    </style>
<![endif]-->
</head>

<body class="body1" onload="document.frm.FY_CODE.focus();" >
 <form action=""  method="post" name="frm" >
     <table height="96%" bgcolor="#f2f2f2"  align="center" width="100%">
         <tr><td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
                
               
          <table border="0"  cellpadding="5" align="center" width="100%" >
             <tr><td  class="hd" style=" font-size:18px; text-align:center; font-weight:bold; color:green;"> Dispatch Planning Detail
             </td></tr>
             <tr><td align="center" >
             <table width="590pt" cellpadding="0" cellspacing="0">
                 <tr><td align="right" >
                         <input type="button" name="bnt"  onclick="GetSearch()" value="Back" class="submitbutton1">
                <s:if test="%{FWD_DATE==null}">
                     <input type="button" name="btn" id="saveid" value="Save" onclick="recsave()"  class="submitbutton1">
                </s:if>
                <s:if test="%{FWD_DATE!=null && authuser=='YES'}">
                         <input type="button" name="btn" id="saveid" value="Save" onclick="recsave()"  class="submitbutton1">
                 </s:if>
                      <input  id="addid" type="button" name="bnt" style="width:50pt" onclick="AddRow('tabtax');" value="Add Row" class="submitbutton1">
             
                 </td>
             </tr>
             </table>
             </td>
            </tr>
          </table>
            <table   align="center" cellpadding="1"  cellspacing="1" >  
                <tr>
                    <td style="vertical-align: top;" rowspan="2" border-color:blue>
                        <div style="height: 350px;overflow-y: auto;">
                        <table border="1"  cellpadding="0" cellspacing="0" align="center" width="100%" color="#06999">
                            <s:if test="%{INVCH!=null  }">
                                <td class="label-1">Selected Invoice</td>
                            <s:iterator value="INVCH" status="st1">
                              <tr>
                                  <td style="font-size:8pt"><s:textfield name="INVCH1" readonly="true" cssStyle="width:58pt;text-align:right;background:lightgray" theme="simple"  value="%{LIST_CODE}"/>
                                  <input type="checkbox"  name="UPDCODE"  theme="simple" value="<s:property value="%{LIST_CODE}"/>"</td>
                                  <input type="hidden" name="minv" value="<s:property value="%{LIST_CODE}"/>" >
                              </tr>
                              </s:iterator>
                           </s:if>
                        </table>
                        </div>
                    </td>
                    <td style="vertical-align: top;">
                        <table>
                            <tr> <td valign="middle" style="border-width:1pt;border-color:black;border-style:solid;"  >
              <table width="590pt" align="center" cellpadding="1" cellspacing="1" >
                    
                  <tr> 
                          <td class="label-1" align="right" style="width:100pt;padding-right:5pt">Invoice :  </td>
                          <td class="label-1" style="color: red"><s:textfield name="UPDCODE" value="%{UPDCODE}" cssStyle="background-color:yellow;width:70pt" theme="simple"/>  </td>
                        </tr>
                 
                        <tr> 
                          <td class="label-1" align="right" style="width:100pt;padding-right:5pt">Factory :  </td>
                          <td ><s:select name="FY_CODE" cssStyle="text-transform:uppercase;width:250pt" theme="simple" list="%{fyList}"   headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" alue="%{FY_CODE}" />  </td>
                        </tr>
                        <tr> 
                          <td class="label-1" align="right" style="width:100pt;padding-right:5pt">CFS Code :  </td>
                          <td ><s:select name="CFS_CODE" cssStyle="text-transform:uppercase;width:250pt" theme="simple" list="%{cfsList}"   headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" value="%{CFS_CODE}" />  </td>
                        </tr>
                        <tr>
                            <td class="label-1" align="right" style="width:100pt"> Cutoff Date :  </td>
                           <td><sx:datetimepicker  name="DEL_DATE" id="DEL_DATE" displayFormat="dd/MM/yyyy"  value="%{DEL_DATE}" cssStyle="width:70pt;font-size:9pt"/> </td>
                       </tr>
                        <tr>
                            <td class="label-1" align="right" style="width:100pt"> Ex-Factory Date : 
                            </td>
                           <td><sx:datetimepicker  name="EX_FY_DATE" id="EX_FY_DATE" displayFormat="dd/MM/yyyy"  value="%{EX_FY_DATE}" cssStyle="width:70pt;font-size:9pt"/> </td>
                       </tr>
                        <tr>
                             <td class="label-1" align="right" style="width:100pt">Gross Weight :  </td>
                             <td class="label-1"> <s:textfield name="GRWT" value="%{GRWT}" theme="simple"  onblur="validatenumber(this);" cssStyle="width:70pt"  maxLength="10" /> </td>
                       </tr>
                       
                         <s:if test="%{FWD_DATE!=null && authuser=='YES'}">
                             <tr><td class="label-1" align="right" style="width:100pt">Remove Fwd Date :  </td>
                               <td class="label-1"><s:textfield name="FWD_DATE" readonly="true" value="%{FWD_DATE}" theme="simple"   cssStyle="width:70pt"  /> <input title="Remove Forward Date" type="CHECKBOX" name="remove_fwd"  value="Y"   ></td>
                             </tr>
                             </s:if>
                           
                             
                     </table>
                  </td></tr>
                        </table>
                    </td>
                </tr>   
                <tr >
                   <td valign="top" height="290pt"  style="border-width:1pt;border-color:black;border-style:solid;"  >
                   <div  style="position:absolute;width:100%;overflow:auto ;height:200pt;">      
                      <table id="tabtax" width="100pt" align="center" cellpadding="0" cellspacing="0" >
                      
                      <thead >
                       <tr class="hd" style="position:relative; top: expression(this.offsetParent.scrollTop);height:17pt" >
                          <th class="label-1" style="width:8%"align="right" >Length </th>
                          <th class="label-1" style="width:8%" align="right" >Width</th>
                          <th class="label-1" style="width:8%" align="right">Height</th>
                          <th class="label-1" style="width:8%" align="center">UOM</th>
                          <th class="label-1" style="width:8%" align="right">Packages</th>
                          <th class="label-1" style="width:8%" align="right">CBM</th>
                           <th class="label-1" style="width:8%" align="right">CFT</th>
                          <th class="label-1" style="width:8%" align="right"  >Volume</th>
                        
                         </tr>
                      </thead> 
                      <tbody> 
                              
                               <s:set name="ndx" id="ndx" value="0"/>
                                    <s:if test="%{invCBMList!=null}">
                                     <s:iterator status="st1" value="invCBMList" >
                                      <s:set name="ndx" id="ndx" value="%{#ndx+1}"/>
                                         <tr>
                                          <td> <s:textfield name="BOX_LN" value="%{BOX_LN}" maxlength="10" theme="simple"  onkeypress="return onlyNumbers();"  onblur="GetAmt('%{#st1.index}');Gettot();" cssStyle="width:70px;text-align:right;"/> </td>
                                         <td> <s:textfield name="BOX_WD" value="%{BOX_WD}" maxlength="10" theme="simple"  onkeypress="return onlyNumbers();"  onblur="GetAmt('%{#st1.index}');Gettot();" cssStyle="width:70px;text-align:right;"/> </td>
                                         <td> <s:textfield name="BOX_HT" value="%{BOX_HT}" maxlength="10" theme="simple" onkeypress="return onlyNumbers();"  onblur="GetAmt('%{#st1.index}');Gettot();" cssStyle="width:70px;text-align:right;" /> </td>
                                         <td > <s:select   list="#{'CM':'CM', 'INCH':'INCH'}"  theme="simple" name="CT_UOM"  value="%{CT_UOM}" cssStyle="width:70px;text-align:center;" /></td>
                                         <td> <s:textfield name="INV_PKGS"   theme="simple" onkeypress="return onlyNumbers();" value="%{INV_PKGS}" onblur="GetAmt('%{#st1.index}');Gettot();" cssStyle="width:70px;text-align:right;"  /> </td>
                                         <td> <s:textfield name="CBM" readonly="true"  value="%{CBM}" theme="simple"  cssStyle="width:70px;text-align:right;background:lightgray;"  /> </td>
                                         <td> <s:textfield name="CFT" readonly="true"  value="%{CFT}" theme="simple"  cssStyle="width:70px;text-align:right;background:lightgray;"  /> </td>
                                         <td> <s:textfield name="BOX_VOL" readonly="true" value="%{BOX_VOL}"  theme="simple"  cssStyle="width:70px;text-align:right;background:lightgray;"  /> </td>
                                     
                                         </tr>
                                     </s:iterator>
                                     </s:if>
                             
                                  <s:iterator  begin="%{#ndx}" end="5" step="1"  status="st1" >
                                     <tr>
                                            
                                         <td> <s:textfield name="BOX_LN" maxlength="10" theme="simple"  onkeypress="return onlyNumbers();"  onblur="GetAmt('%{#ndx}');Gettot();" cssStyle="width:70px;text-align:right;"/> </td>
                                         <td> <s:textfield name="BOX_WD" maxlength="10" theme="simple"  onkeypress="return onlyNumbers();"  onblur="GetAmt('%{#ndx}');Gettot();" cssStyle="width:70px;text-align:right;"/> </td>
                                         <td> <s:textfield name="BOX_HT" maxlength="10" theme="simple" onkeypress="return onlyNumbers();"  onblur="GetAmt('%{#ndx}');Gettot();" cssStyle="width:70px;text-align:right;" /> </td>
                                         <td > <s:select   list="#{'CM':'CM', 'INCH':'INCH'}"  theme="simple" name="CT_UOM"  cssStyle="width:70px;text-align:center;" /></td>
                                         <td> <s:textfield name="INV_PKGS"   theme="simple" onkeypress="return onlyNumbers();" onblur="GetAmt('%{#ndx}');Gettot();" cssStyle="width:70px;text-align:right;"  /> </td>
                                         <td> <s:textfield name="CBM" readonly="true"   theme="simple"  cssStyle="width:70px;text-align:right;background:lightgray;"  /> </td>
                                         <td> <s:textfield name="CFT" readonly="true"   theme="simple"  cssStyle="width:70px;text-align:right;background:lightgray;"  /> </td>
                                         <td> <s:textfield name="BOX_VOL" readonly="true"   theme="simple"  cssStyle="width:70px;text-align:right;background:lightgray;"  /> </td>
                                         <s:set name="ndx" id="ndx" value="%{#ndx+1}"/>
                                         
                                     </tr> 
                                 </s:iterator> 
                             <s:if test="%{FWD_DATE==null}">
                                    <input type="button" name="btn5" id="saveid1" value="Save Box Detail" onclick="BoxSave()"  class="submitbutton1">
                             </s:if>
                      </tbody>
                                     
                  </table>
                 </div>                    
               </td>
                </tr>
               </table>
                </td></tr>      
         <tr><td valign="top" colspan="2">
                    <table align="center" width="85%" border="0" align>
                      <tr class="hd" >
                                          <td align="right" width="70%"> <s:textfield   cssClass="inputtxt" name="RBOX"  readonly="true"  value="%{TBOX}"  theme="simple" cssStyle="width:55pt;text-align:right;font-weight:bold;color:red;background-color:yellow"/></td>
                                          <td align="left" > <s:textfield   cssClass="inputtxt" name="RCBM"  readonly="true"  value="%{TCBM}"  theme="simple" cssStyle="width:55pt;text-align:right;font-weight:bold;color:red;background-color:yellow"/></td>
                                          <td align="left"  > <s:textfield   cssClass="inputtxt" name="RCFT"  readonly="true"  value="%{TCFT}"  theme="simple" cssStyle="width:55pt;text-align:right;font-weight:bold;color:red;background-color:yellow"/></td>
                                          <td align="left" width="30%" > <s:textfield   cssClass="inputtxt" name="RVOL"  readonly="true"  value="%{TVOL}"  theme="simple" cssStyle="width:55pt;text-align:right;font-weight:bold;color:red;background-color:yellow"/></td>
                        </tr>  
                      
                               
                    </table>
             </td>
                      </tr>
         
         
         
         <tr style="height:5%" bgcolor="#D8D8D8" >
         <td style="border-width:1pt;border-color:black;border-style:solid;" >
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
               
              <input type="hidden" value="<s:property value="%{#session.sessUserId}"/>" name="aausrid">
             
                    <input type="hidden" name="sbuyer" value="<s:property value="%{sbuyer}"/>" >
                    <input type="hidden" name="sac"    value="<s:property value="%{sac}"/>" >
                    <input type="hidden" name="sport" value="<s:property value="%{sport}"/>" >
 </form>
</body>
</html>

