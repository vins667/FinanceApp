
<%-- 
    Document   : LabelsInsMastJsp
    Created on : Jul 7, 2014, 5:47:05 PM
    Author     : Ranjeet
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
    <head>
        <sx:head/>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content='width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;' name='viewport' />
    <meta name="viewport" content="width=device-width" />

        <title>Shahi Exports Pvt Ltd</title>
        
<script type="text/javascript">

function clear1()
    {
        inputobg=document.frm.inputobg.value
        document.getElementById(inputobg).value="";
        
        
              DEFECT_TOTAL_FUN();
              QTY_TOTAL_FUN();
    }

function selectobj(a)
{

document.frm.inputobg.value=a;

}


  var MAXLENGTH=10;
    var Current="";
    function AddDigit(dig)         
      {     
     inputobg=document.frm.inputobg.value     
     //Current=document.forms['frm'].elements[inputobg].value;   
     Current=document.getElementById(inputobg).value;
     if(dig==".")
     {
     if(Current.indexOf(".")==-1)
     {
     Current=Current+dig;
     }
     }else{
     Current=Current+dig;
     }    
     document.getElementById(inputobg).value=Current;     
       
        PKT_SIZE=document.getElementById("PKT_SIZE").value;
         document.getElementById("PKT_SIZE_PER").value=""; 
         if(!isNaN(eval(PKT_SIZE)))
         {
              
                     p=eval(PKT_SIZE)*0.10;
                     if(eval(p) < 3)
                         {
                             p=3;
                         }
                     document.getElementById("PKT_SIZE_PER").value=parseFloat(p).toFixed(0);; 
         }
       
       DEFECT_TOTAL_FUN();
       QTY_TOTAL_FUN();
    }

function DEFECT_TOTAL_FUN(){
    a=document.getElementsByName("DEFECT_PCS");
    document.getElementById("PCS_SIZE_PER").value="";
    var b=0;
    if(a.length){
    
    for(i=0; i<a.length; i++)
        {
            if(!isNaN(eval(a[i].value)))
                 {
                  b=b+eval(a[i].value);  
                 }
        }
    }else{
        
         if(!isNaN(eval(a.value)))
                 {
                   b=eval(a.value);
                 }
    }
   document.getElementById('DEFECT_TOTAL').value=b;
   c=eval(document.getElementById('PCS_SIZE').value);
   d=(c * eval(document.getElementById('TOLERANCE').value))/100;  
  
   document.getElementById("PCS_SIZE_PER").value=parseFloat(d).toFixed(0);
   
      
   
   if(d < b)
       {
         document.getElementById('DEFECT_STATUS').value='Fail';
       }else{
           document.getElementById('DEFECT_STATUS').value='Pass';
       }
       
     //  blinkColor('DEFECT_STATUS');
}



function QTY_TOTAL_FUN(){
    SAM_PCS=document.getElementById("SAM_PCS").value;
    SAM_WEIGHT=document.getElementById("SAM_WEIGHT").value;
    TOTAL_WEIGHT=document.getElementById("TOTAL_WEIGHT").value;
    SUPLIER_PCS=document.getElementById("SUPLIER_PCS").value;
    PKT_TOTAL=document.getElementById("PKT_TOTAL").value;
    document.getElementById("PKT_TOTAL_PER").value="";
    document.getElementById("ACTUAL_PCS").value="";
    document.getElementById("SHORT_PCS").value="";
    if(!isNaN(eval(PKT_TOTAL)))
    {
        a=eval(PKT_TOTAL)*0.10;
        if(a>10)
            {
                a=10;
            }
       document.getElementById("PKT_TOTAL_PER").value=parseFloat(a).toFixed(0);
    }

if(!isNaN(eval(SAM_PCS)) && !isNaN(eval(SAM_WEIGHT)) && !isNaN(eval(TOTAL_WEIGHT)) )
                 {
                  var a= (eval(SAM_PCS)/eval(SAM_WEIGHT))*eval(TOTAL_WEIGHT)
                  document.getElementById("ACTUAL_PCS").value=parseFloat(a).toFixed(0);
                 
                 if(!isNaN(eval(SUPLIER_PCS)))
               {
                   document.getElementById("SHORT_PCS").value=parseFloat(a).toFixed(0) - eval(SUPLIER_PCS);
                  
               }
         }

   
  
}
 </script>
      
 <script language="javascript">
   function searchgrn() 
           {
              if(document.getElementById('RECEIPT_NO_S').value=="" ||  (document.getElementById('RECEIPT_NO_S').value).length==0)
               {
                 alert("Please Enter Grn...");
                 return flase;
                   
               }else{
                   if((document.getElementById('RECEIPT_NO_S').value).length!=10)
                       {
                           alert("Grn Is Not Valid...");
                            return flase;
                       }
               }
               
               document.frm.action='trimgrnquery.action'
               document.frm.submit();
               
           }
           
           function show_details() {
                dojo.event.topic.publish("show_detail");
                show_detailssdt()
            }
            
 function updatetolerance(a)
 {
   if(a.value=='M')  
       {
           document.getElementById('TOLERANCE').value=1;
       }
        if(a.value=='F')  
       {
           document.getElementById('TOLERANCE').value=2;
       }
         if(a.value=='S')  
       {
           document.getElementById('TOLERANCE').value=3;
       }
 }
            
      
 function blinkColor(a)
  {
   if(document.getElementById(a).value=='Fail'){
   document.getElementById(a).style.background="red"
   }
   setTimeout("setblinkColor('"+a+"')",500)
   
  }  
  
  function setblinkColor(a)
{
  if(document.getElementById(a).value=='Fail'){
  document.getElementById(a).style.background="#e6e6e6"
  }
   setTimeout("blinkColor('"+a+"')",500)
 
}

    
function onsave()
{
    if(confirm("Do You Want To Save Record(s)"))
        {
               document.frm.action='savesltdbtngrnquery.action'
               document.frm.submit();
        }else{
            return;
        }
    
}


</script>
        <style>
    .btn
    {
    font-size:25.0px;
    font-family:Arial Black;
    width:60px;
    background-color: #f3f3ee;
   
   
    }
    
     .textstab{
        font-family: Arial, Sans-Serif;
        font-size: 20px;
        border: solid 1px #677788;
        text-align: center;
       
    }
    .textreadonlytab{
        font-family: Arial, Sans-Serif;
        font-size: 20px;
        border: solid 1px #677788;
        background-color:#e6e6e6;
        text-align: center;
    }
    
.labeltab{
margin: 10px;
color:#006699;
font-size:20px;
font-weight:bold;

}

.labeltabin{
margin: 10px;
font-size:12px;
color:#666666;
font-weight:bold;
}
        </style>
       <LINK href="../../css/main.css" rel="stylesheet"	type="text/css">
           <LINK href="../css/style.css" rel="stylesheet"	type="text/css">
    </head>

    <body   onload="" style="background-color:#f2f2f2;margin: 0px;padding: 0px" >

        <form action="" onsubmit="" id="frm" style="margin: 0px;padding: 1px"  method="post" name="frm" >
         <table border="0" bgcolor="#f2f2f2" cellpadding="0" align="center" width="100%" >
                  
                  <tr><td>
                         <table width="100%" border="1" cellpadding="0" cellspacing="0">
                            <tr>
                             <td  height="23" class="hdtb">Grn Details </td>
                              </tr>
                            <tr>
                                <td>
                         
                              <table cellpadding="1" cellspacing="3"> 
                              <tr><td class="label-1">Grn</td>
                                  <td><s:textfield name="RECEIPT_NO_S"  theme="simple" value="%{RECEIPT_NO_S}" id="RECEIPT_NO_S" maxlength="10" readonly="true" cssClass="textreadonly" cssStyle="width:100px;" />
                                      
                                 </td>
                                  <td class="label-1">Buyer Name</td>
                                  <td>
                                      
                                      <s:hidden name="SAVEFLG" value="%{SAVEFLG}" theme="simple"/>
                                      <s:hidden name="RECEIPT_NO"  theme="simple" value="%{trimInsMastBean.RECEIPT_NO}" id="RECEIPT_NO" maxlength="10"  />
                                       <s:hidden name="LOCATION_CODE"  theme="simple" value="%{trimInsMastBean.LOCATION_CODE}" id="LOCATION_CODE" maxlength="10"  />
                                     <s:hidden name="USER_ID"  theme="simple" value="%{trimInsMastBean.USER_ID}" id="USER_ID" maxlength="10"  />
                                      <s:textfield name="BUYER_CODE"  theme="simple" value="%{trimInsMastBean.BUYER_CODE}" id="BUYER_CODE" readonly="true"  cssClass="textreadonly" cssStyle="width:75px;" />
                                  <s:textfield name="BUYER_NAME"  theme="simple" value="%{trimInsMastBean.BUYER_NAME}" id="BUYER_NAME"  readonly="true" cssClass="textreadonly" cssStyle="width:250px;" />
                                  </td>
                                    <td class="label-1">Color</td>
                                  <td><s:textfield name="COLOR"  theme="simple" value="%{trimInsMastBean.COLOR}" id="COLOR"  readonly="true" cssClass="textreadonly" cssStyle="width:100px;" />
                                  <td>
                                       <td class="label-1">Size</td>
                                  <td><s:textfield name="SIZES"  theme="simple" value="%{trimInsMastBean.SIZES}" id="SIZES"  readonly="true" cssClass="textreadonly" cssStyle="width:100px;" />
                                  </td>
                              </tr>
                              <tr><td class="label-1">Po No</td>
                                  <td><s:textfield name="PO_NO"  theme="simple" value="%{trimInsMastBean.PO_NO}" id="PO_NO"  readonly="true" cssClass="textreadonly" cssStyle="width:100px;" />
                                   </td>
                                  <td class="label-1">Supplier</td>
                                  <td><s:textfield name="SUPPLIER_CODE"  theme="simple" value="%{trimInsMastBean.SUPPLIER_CODE}" id="SUPPLIER_CODE" readonly="true"  cssClass="textreadonly" cssStyle="width:75px;" />
                                  <s:textfield name="SUPPLIER_NAME"  theme="simple" value="%{trimInsMastBean.SUPPLIER_NAME}" id="SUPPLIER_NAME"  readonly="true" cssClass="textreadonly" cssStyle="width:250px;" />
                                  </td>
                                    <td class="label-1">UOM</td>
                                  <td><s:textfield name="BUOM"  theme="simple" value="%{trimInsMastBean.BUOM}" id="BUOM"  readonly="true" cssClass="textreadonly" cssStyle="width:100px;" />
                                  <td>
                                      <td class="label-1">Grn Qty</td>
                                  <td><s:textfield name="GRN_QTY"  theme="simple" value="%{trimInsMastBean.GRN_QTY}" id="GRN_QTY"  readonly="true" cssClass="textreadonly" cssStyle="width:100px;" />
                                    </td>
                              </tr>
                              <tr><td class="label-1">Invoice No</td>
                                  <td><s:textfield name="INVOICE_NO"  theme="simple" value="%{trimInsMastBean.INVOICE_NO}" id="INVOICE_NO"  readonly="true" cssClass="textreadonly" cssStyle="width:100px;" />
                                    </td>
                                  <td class="label-1">Item</td>
                                  <td><s:textfield name="ITEM_CODE"  theme="simple" value="%{trimInsMastBean.ITEM_CODE}" id="ITEM_CODE" readonly="true"  cssClass="textreadonly" cssStyle="width:75px;" />
                                  <s:textfield name="ITEM_DESC"  theme="simple" value="%{trimInsMastBean.ITEM_DESC}" id="ITEM_DESC"  readonly="true" cssClass="textreadonly" cssStyle="width:250px;" />
                                  <s:hidden name="ITEM_TYPE" value="%{trimInsMastBean.ITEM_TYPE}"/>
                                  
                                  </td>
                                    <td class="label-1">Tolerance(%)</td>
                                  <td class="label-1"><s:textfield name="TOLERANCE"  theme="simple" value="%{trimInsMastBean.TOLERANCE}" id="TOLERANCE" readonly="true"  cssClass="textreadonly" cssStyle="width:100px;" /></td>
                                    <td class="label-1"></td>
                                  <td><td>
                              </tr>
                              </table>
                                </td></tr></table>
                      </td>
                  </tr><tr><td>
                          <table width="100%" border="1" cellpadding="0" cellspacing="0">
                            <tr>
                             <td  height="30" class="hdtb">
                                 <table cellpadding="0" cellspacing="0" > 
                                     <tr><td style="width:400px;font-size: 20px" >
                                         Buttons - Visual Audit
                                      </td>
                              <td class="label-1">Grn Type</td>
                              
                              <td> 
                                  <s:hidden name="GRN_TYPE" value="%{'S'}" id="GRN_TYPE"/>
                                  <s:if test='%{INS_TYPE=="M"}'>
                                   <input type="radio" value="M"   checked="true"  size="20"  name="INS_TYPE">  Metal 
                                     
                                  </s:if>
                                   <s:elseif test='%{INS_TYPE=="F"}'>
                                       <input type="radio"  value="F" checked="true"   name="INS_TYPE">  Fabric
                                   </s:elseif>
                                        <s:elseif test='%{INS_TYPE=="S"}'>
                                       <input type="radio"  value="F" checked="true"   name="INS_TYPE">  Fabric
                                   </s:elseif>
                                       <s:else>
                                           <input type="radio" value="M" onclick="updatetolerance(this)" checked="true"  size="20"  name="INS_TYPE">  Metal
                                            <input type="radio" onclick="updatetolerance(this)" value="F"  name="INS_TYPE">  Fabric
                                            <input type="radio" onclick="updatetolerance(this)" value="S"  name="INS_TYPE">  Shell and other Beads
                                       </s:else>
                                  
                                          
                                      </td>
                                      
                              </tr></table>
                             </td>
                              </tr>
                            <tr>
                                <td>
                              <table cellpadding="0" cellspacing="0" width="100%">
                                  <tr><td valign="top" style="height:180px">
               <table align="center" cellpadding="3" width="100%" cellspacing="1" >
           <col width="4%"/>
            <col width="4%"/>
             <col width="4%"/>
             <s:iterator value="trimqualmaster" status="trimqualmasterst">
                <col  width="4%"/>
            </s:iterator>
              <col width="4%"/>
               <col width="4%"/>
               <col width="2%"/>
                    <thead>
                        <tr style="background-color: #FFFFCC" >
                            <th  rowspan="2"   align="center" valign="top">
                           <table  align="center" >
                               <tr><td class="labeltab">Sample Size</td></tr>
                                <tr><td class="labeltabin">(Pcs)</td></tr>
                              <tr><td class="labeltabin">(1% of Received Qty)</td></tr>
                              <tr><td class="labeltabin">(Min 100 Pcs and Max 1000 Pcs)</td></tr>
                              
                          </table>
                          </th>
                          <th  rowspan="2"   align="center" valign="top">
                           <table  align="center">
                               <tr><td class="labeltab">No Of Pkts Received</td></tr>
                              
                            </table>
                          </th>
                             <th  rowspan="2"   align="center"  valign="top">
                           <table  align="center">
                               <tr><td class="labeltab">Sample Size</td></tr>
                                 <tr><td class="labeltabin">(Pkts)</td></tr>
                              <tr><td class="labeltabin">(10% Pkts)</td></tr>
                              <tr><td class="labeltabin">(Min 3)</td></tr>
                              
                          </table>
                          </th>
                          
                         
                          <th align="center"  valign="top"
                              colspan="<s:property value="%{trimqualmaster.size()}"/>">
                          <table align="center" >
                              <tr><td class="labeltab">Non Conformance Quantity</td></tr>
                              <tr><td class="labeltabin">(No. of Pcs)</td></tr>
                          </table>
                                      </th>
                             <th   rowspan="2" valign="top" align="center"> 
                             
                                       <table  align="center">
                               <tr><td class="labeltab">Total Defectives</td></tr>
                                 <tr><td class="labeltabin">(Pcs)</td></tr>
                              
                              
                          </table>
                                     
                           </th> 
                           <th   rowspan="2" align="center" valign="top"> 
                           <table align="center">
                              <tr><td  class="labeltab">% Defectives</td></tr>
                          </table>
                           </th>
                          <th   align="center" valign="top"> 
                           <table align="center">
                              <tr><td  class="labeltab">Result</td></tr>
                            </table>
                           </th>
                          
                      </tr>
                      
                      <tr style="background-color: #FFFFCC"> 
                          <s:iterator value="trimqualmaster" status="trimqualmasterst">
                                      
                              <th class="labeltab" style="font-size: 15px"><s:property value="%{PARAMETER_NAME}"/></th>
                        
                        </s:iterator>
                              <s:if test="%{trimqualmaster.size()==0}">
                                  <th></th>
                              </s:if>
                          
                          <th  class="labeltabin">Pass/Fail</th>
                      </tr>
                      </thead>
                       <tbody>
                          <tr><td>
                                  
                                       <s:textfield name="PCS_SIZE"  
                                                    id="PCS_SIZE" theme="simple" cssStyle="width:100%"  
                                                    cssClass="textstab"
                                                     onfocus="selectobj(this.id)" 
                                                    value="%{trimInsMastBean.PCS_SIZE}"/>
                                       <s:hidden name="MAST_SL_NO" id="MAST_SL_NO" value="%{trimInsMastBean.MAST_SL_NO}"/>
                                      
                                   </td>
                                   <td>
                                  
                                       <s:textfield name="PKT_SIZE"  
                                                    id="PKT_SIZE" theme="simple" cssStyle="width:100%"  
                                                    cssClass="textstab"
                                                     onfocus="selectobj(this.id)" 
                                                    value="%{trimInsMastBean.PKT_SIZE}"/>
                                      </td>
                                      <td>
                                  
                                       <s:textfield name="PKT_SIZE_PER"  
                                                    id="PKT_SIZE_PER" theme="simple" cssStyle="width:100%"  
                                                    cssClass="textreadonlytab"
                                                    readonly="true"
                                                    value="%{trimInsMastBean.PKT_SIZE_PER}"/>
                                      </td>
                                   
                                 
                                   
                                   <s:iterator value="trimqualmaster" status="trimqualmasterst">
                                       <s:set id="slid" name="slid" value="%{SL_NO}"/>
                                       <s:set id="fflag" name="fflag" value="%{'No'}"/>
                                       <s:iterator value="defectpcs.{?#this.SL_NO==#slid}" status="trimqualmasterstsave">
                                           <td>
                                       <s:hidden name="DEFECT_CODE" value="%{#slid}"/>
                                       <s:textfield name="DEFECT_PCS" readonly="true" 
                                                    id="DEFECT_PCS%{#trimqualmasterst.index}" 
                                                    onblur="DEFECT_TOTAL_FUN()" 
                                                   onfocus="selectobj(this.id)"  
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textstab"
                                                    value="%{PARAMETER_NAME}"/>
                                        <s:set id="fflag" name="fflag" value="%{'Yes'}"/>
                                   </td>
                                       </s:iterator>
                                    <s:if test="%{#fflag=='No'}">
                                  <td>
                                     
                                      <s:hidden name="DEFECT_CODE" value="%{SL_NO}"/>
                                       <s:textfield name="DEFECT_PCS" readonly="true" 
                                                    id="DEFECT_PCS%{#trimqualmasterst.index}" 
                                                    onblur="DEFECT_TOTAL_FUN()" 
                                                   onfocus="selectobj(this.id)"  
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textstab"
                                                    value="%{DEFECT_PCS}"/>
                                   </td>
                                   </s:if>
                                   </s:iterator>
                                    <s:if test="%{trimqualmaster.size()==0}">
                                  <td> <s:textfield name="DEFECT_TOTALnotused" readonly="true" 
                                                    id="DEFECT_TOTALnotused"  theme="simple" cssStyle="width:100%" 
                                                    cssClass="textreadonlytab"
                                                    value="%{DEFECT_TOTALnotused}"/></td>
                              </s:if>
                                   <td>
                                       <s:textfield name="DEFECT_TOTAL" readonly="true" 
                                                    id="DEFECT_TOTAL"  theme="simple" cssStyle="width:100%" 
                                                    cssClass="textreadonlytab"
                                                    value="%{trimInsMastBean.DEFECT_TOTAL}"/>
                                   </td>
                                   <td>
                                       <s:textfield name="PCS_SIZE_PER" readonly="true" 
                                                    id="PCS_SIZE_PER"  theme="simple" cssStyle="width:100%" 
                                                    cssClass="textreadonlytab"
                                                    value="%{trimInsMastBean.PCS_SIZE_PER}"/>
                                   </td>
                                   
                                    <td>
                                       <s:textfield name="DEFECT_STATUS" readonly="true" 
                                                    id="DEFECT_STATUS"  theme="simple" cssStyle="width:100%" 
                                                    cssClass="textreadonlytab"
                                                    value="%{trimInsMastBean.DEFECT_STATUS}"/>
                                   </td>
                               </tr>
                           
                      </tbody>
                </table>
                                          
                                  </td></tr>
                                 
                              </table>
                                </td></tr></table>
                        

    </td></tr>
               <tr><td> <table width="100%" border="1" cellpadding="0" cellspacing="0">
                            <tr>
                                <td  height="30"  class="hdtb" style="font-size:20px;" >Buttons - Quantity Audit</td>
                              </tr>
                            <tr>
                                <td valign="top" style="height: 60px">
                        
                      <table align="center" width="100%" cellpadding="3"   cellspacing="1" >
                           <col width="4%"/>
                            <col width="4%"/>
                             <col width="4%"/>
                              <col width="4%"/>
                               <col width="4%"/>
                               <col width="4%"/>
                               <col width="4%"/>
                    <thead>
                        <tr style="background-color: #FFFFCC" >
                              <th  align='center' valign="top">
                                <table  align="center">
                               <tr><td class="labeltab">Total Pkt Received</td></tr>
                                                          
                          </table>
                            </th>
                            <th  align='center'  valign="top">
                                <table  align="center">
                               <tr><td class="labeltab">Sample Size</td></tr>
                              <tr><td class="labeltabin">(Pcs)</td></tr>
                             
                          </table>
                            </th>
                              <th  align='center'  valign="top">
                                <table  align="center">
                               <tr><td class="labeltab">Sample size of Pkts</td></tr>
                              <tr><td class="labeltabin">(10% or Max 10)</td></tr>
                                                          
                          </table>
                            </th>
                             <th  align='center'  valign="top">
                                <table  align="center">
                               <tr><td class="labeltab">Sample Size Weight</td></tr>
                              <tr><td class="labeltabin">(Gms)</td></tr>
                             
                          </table>
                            </th>
                             <th  align='center'  valign="top">
                                <table  align="center">
                               <tr><td class="labeltab">Total Net Weight</td></tr>
                              <tr><td class="labeltabin">(Gms)</td></tr>
                             
                          </table>
                             </th>
                             <th  align='center'  valign="top">
                                <table  align="center">
                               <tr><td class="labeltab">Total No Of Pcs</td></tr>
                              <tr><td class="labeltabin">(Weighing Method)</td></tr>
                             
                          </table>
                            </th>
                           
                            
                             <th class="labeltab" align='center' valign="top">
                                   <table  align="center">
                               <tr><td class="labeltab">Shortage</td><td class="labeltab">/</td><td class="labeltab">Excess</td></tr>
                                <tr><td class="labeltabin">(-)</td><td></td><td class="labeltabin">(+)</td></tr>
                                                          
                          </table>
                              
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                             <td><s:textfield name="PKT_TOTAL" readonly="true" 
                                                    id="PKT_TOTAL" 
                                                    onblur="" 
                                                    onfocus="selectobj(this.id)"  
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textstab"
                                                    value="%{PKT_TOTAL}"/>
                            </td> 
                            <td><s:textfield name="SAM_PCS" readonly="true" 
                                                    id="SAM_PCS" 
                                                    onblur="" 
                                                    onfocus="selectobj(this.id)"  
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textstab"
                                                    value="%{SAM_PCS}"/>
                            </td> 
                              <td><s:textfield name="PKT_TOTAL_PER" readonly="true" 
                                                    id="PKT_TOTAL_PER" 
                                                    onblur="" 
                                                     
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textreadonlytab"
                                                    value="%{PKT_TOTAL_PER}"/>
                              </td>
                                  
                            <td><s:textfield name="SAM_WEIGHT" readonly="true" 
                                                    id="SAM_WEIGHT" 
                                                    onblur="" 
                                                    onfocus="selectobj(this.id)"  
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textstab"
                                                    value="%{SAM_WEIGHT}"/>
                            </td> 
                            <td><s:textfield name="TOTAL_WEIGHT" readonly="true" 
                                                    id="TOTAL_WEIGHT" 
                                                    onblur="" 
                                                    onfocus="selectobj(this.id)"  
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textstab"
                                                    value="%{TOTAL_WEIGHT}"/>
                            </td> 
                           
                          
                            <td><s:textfield name="ACTUAL_PCS" readonly="true" 
                                                    id="ACTUAL_PCS" 
                                                    onblur="" 
                                                     
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textreadonlytab"
                                                    value="%{ACTUAL_PCS}"/>
                                <s:hidden name="SUPLIER_PCS" readonly="true" 
                                                    id="SUPLIER_PCS" 
                                                    onblur="" 
                                                   
                                                    theme="simple" 
                                                    cssClass="textstab"
                                                    value="%{SUPLIER_PCS}"/>
                            </td> 
                            
                          
                            <td><s:textfield name="SHORT_PCS" readonly="true" 
                                                    id="SHORT_PCS" 
                                                    onblur="" 
                                                    
                                                    theme="simple" cssStyle="width:100%" 
                                                    cssClass="textreadonlytab"
                                                    value="%{SHORT_PCS}"/>
                            </td> 
                        </tr>
                    </tbody>
                         </table>
                                </td></tr></table>
                   </td></tr>
      <tr><td align="center">
    <table>
    <tr>
     <td><input type="BUTTON" name="." value="." class="btn" onclick="AddDigit('.')"></td>
    
    <td><input type="BUTTON" name="0" value="0" class="btn" onclick="AddDigit(0)"></td>
    <td><input type="BUTTON" name="1" value="1" class="btn" onclick="AddDigit(1)"></td>
    <td><input type="BUTTON" name="2" value="2" class="btn" onclick="AddDigit(2)"></td>
     <td><input type="BUTTON" name="3" value="3" class="btn" onclick="AddDigit(3)"></td>
  
    
   
    <td><input type="BUTTON" name="4" value="4" class="btn" onclick="AddDigit(4)"></td>
    <td><input type="BUTTON" name="5" value="5" class="btn" onclick="AddDigit(5)"></td>
    <td><input type="BUTTON" name="6" value="6" class="btn" onclick="AddDigit(6)"></td>
    
   
    
    
    <td><input type="BUTTON" name="7" value="7" class="btn" onclick="AddDigit(7)"></td>
    <td><input type="BUTTON" name="8" value="8" class="btn" onclick="AddDigit(8)"></td>
    <td><input type="BUTTON" name="9" value="9" class="btn" onclick="AddDigit(9)"></td>
    
    <td><input type="BUTTON" name="clear" style="background-color:red;width:65pt" value="Clear" class="btn" onclick="clear1();" ></td>
   
    
    </tr>
    
   
        <tr> <%--
            <td><input type="BUTTON" name="S" value="S" class="btn" onclick="AddDigit('S')"></td>
    <td><input type="BUTTON" name="M" value="M" class="btn" onclick="AddDigit('M')"></td>
    <td><input type="BUTTON" name="L" value="L" class="btn" onclick="AddDigit('L')"></td>
    <td><input type="BUTTON" name="X" value="X" class="btn" onclick="AddDigit('X')"></td>
        --%>
        <td colspan="3" align="right"></td>
    <td colspan="6" >
  <input type="BUTTON" class="btn"  style="width:150px;font-weight:bold;background-color:#8f2e00;color:white" name="btn"  onClick="window.location.href='../Grnno.jsp'"  value="New">
     
  <input type="BUTTON" class="btn"  style="width:150px;font-weight:bold;background-color:green;color:white" name="btn"  onclick="onsave()"   value="Save">
       </td>
        </tr></table>
      <input type="HIDDEN" name="inputobg" value="00">
      </td></tr>
         </table>
 <table cellpadding="2" cellspacing="1" width="100%"><tr><td class="label-1">Machine IP - <%=request.getRemoteAddr() %></td></tr></table>
      
  </form>

    </body>

</html>
