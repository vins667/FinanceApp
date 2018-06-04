
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

    <head>
        <s:head/>
        <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />         
    <title>SEPL</title>
<link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>

    <script>
        
        function ongosearch() {
            document.getElementById('prepage').style.visibility='';
            document.getElementById('formId').action='mbillentAction.action';
            document.getElementById('formId').submit();
        }
        function ongodelete() {
            //if(validatechk()==true){
            if (confirm('Do you want to delete??')) {
                
                 document.getElementById('formId').action='mbillentAction.action?delflg=Yes';
                 document.getElementById('formId').submit();
               
            }
       // }
        }
        
        function ongoac() {
            if(validatechkac()==true){
            if (confirm('Do you want to Forward??')) {
                
                 document.getElementById('formId').action='mbillentAction.action?actflg=Yes';
                 document.getElementById('formId').submit();
               
            }}
        }
        
        function ongonew() {
          
             document.getElementById('formId').action='newmstmbillentAction.action';
            document.getElementById('formId').submit();
        }
        function CheckAllDelete()
        {
            a = document.searchsubs.chkdelmaster;
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    if (document.searchsubs.dchk.checked)
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = true;
                        }
                    }
                    else
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = false;
                        }
                    }
                }
            }
            else
            {
                if (document.searchsubs.dchk.checked)
                {
                    if (!a.disabled)
                    {
                        a.checked = true;
                    }
                }
                else
                {
                    if (!a.disabled)
                    {
                        a.checked = false;
                    }
                }
            }
        }
        
        function validatechk() 
       {
         var flag=0;
            a = document.searchsubs.chkdelmaster;
            if(a){
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    e = a[i];
                        if (e.checked == true)
                        {
                           flag=1;
                        }
                   
                }
            }
            else
            {
                
                    if (a.checked == true)
                    {
                       flag=1;
                    }
                
               
            }
            }
            if(flag==0)
                {
                    
                    alert("Please Select  Record");
                    return false;
                }
            
            return true;
       }
       
       
        function CheckAllAC()
        {
            a = document.searchsubs.accchkmaster;
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    if (document.searchsubs.acchk.checked)
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = true;
                        }
                    }
                    else
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = false;
                        }
                    }
                }
            }
            else
            {
                if (document.searchsubs.acchk.checked)
                {
                    if (!a.disabled)
                    {
                        a.checked = true;
                    }
                }
                else
                {
                    if (!a.disabled)
                    {
                        a.checked = false;
                    }
                }
            }
        }
        
         function validatechkac() 
       {
         var flag=0;
            a = document.searchsubs.accchkmaster;
            if(a){
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    e = a[i];
                        if (e.checked == true)
                        {
                           flag=1;
                        }
                   
                }
            }
            else
            {
                
                    if (a.checked == true)
                    {
                       flag=1;
                    }
                
               
            }
            }
            if(flag==0)
                {
                    
                    alert("Please Select  Record");
                    return false;
                }
            
            return true;
       }
       
    function CheckAll(a,b)
        {
           // a = document.searchsubs.accchkmaster;
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    if (b.checked)
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = true;
                        }
                    }
                    else
                    {
                        e = a[i];
                        if (!e.disabled)
                        {
                            e.checked = false;
                        }
                    }
                }
            }
            else
            {
                if (b.checked)
                {
                    if (!a.disabled)
                    {
                        a.checked = true;
                    }
                }
                else
                {
                    if (!a.disabled)
                    {
                        a.checked = false;
                    }
                }
            }
        }   
       
     function validatechkin(a) 
       {
         
         var flag=0;
            
            if(a){
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    e = a[i];
                        if (e.checked == true)
                        {
                           flag=1;
                        }
                   
                }
            }
            else
            {
                
                    if (a.checked == true)
                    {
                       flag=1;
                    }
                
               
            }
            }
            if(flag==0)
                {
                    
                    alert("Please Select  Record");
                    return false;
                }
            
            return true;
       }  
       
       
         function ongoacctn() {
            if(validatechkin(document.searchsubs.REPORT_NO)==true ){
            if (confirm('Do you want to Generate Control No??')) {
                
                 document.getElementById('formId').action='mbillentAction.action?ctnflg=Yes';
                 document.getElementById('formId').submit();
               
            }
        }
        }
        
        function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
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
        		  {}
        		  return true;
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
    
    
   <style>
       
       .lodingdiv{
position:absolute;
font-family:arial;
font-size:25;
left:300px;
top:200px;
layer-background-color:blue; height:80pt; width:300pt;

}
           .textreadonly{
        font-family: Arial, Sans-Serif;
        font-size: 11px;
        border: solid 1px #677788;
        background-color:#e6e6e6;
        text-transform: uppercase;
    }
.texts{
        font-family: Arial, Sans-Serif;
        font-size: 11px;
        border: solid 1px #677788;
        text-transform: uppercase;
    }
    .texts1{
        font-family: Arial, Sans-Serif;
        font-size: 11px;
        border: solid 1px #677788;
       
    }
    .selecttext{
        font-family: Arial, Sans-Serif;
        font-size: 11px;
        border: solid 1px #677788;
        text-transform: uppercase;
    }
    
    
        </style>
         <style type="text/css">
      
      tbody {
        height: 500px;
        overflow-y: auto;
        overflow-x: hidden;

     }
        </style>
        <!--[if IE]>
    <style type="text/css">
        .div1 {
            position: absolute;
            height: 510px;
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 0px ;
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
<body style="text-align:center;margin:0px;background-color: #f2f2f2;" onload="waitPreloadPage();">
  <script type="text/javascript" src="wz_tooltip/wz_tooltip.js"></script>
<script type="text/javascript" src="wz_tooltip/tip_balloon.js"></script>
<script type="text/javascript" src="wz_tooltip/tip_drag.js"></script>
 <DIV align="center" id="prepage" class="lodingdiv" style="position:absolute;" >
<img src="css/image/progress.gif" >
<br>
<B >Loading ... ... Please wait ...</B>
</DIV>        
                       
    <form method="POST" id="formId" name="searchsubs" action=""> 
        <table style="background-color: #f2f2f2;width: 100%;" cellpadding="1" cellspacing="0">    
            <tr>
                <td style="width:100%;height: 25px;text-align: center" colspan="10" class="hd" > Bill Entry Query   </td></tr>
            <tr style="background-color: #b2cecf;height:35px;">
                <%--<td class="label-1" style="width:10%;">Department Code</td>
                <td style="width:100px;"><s:textfield name="S_DEPT_CODE" id="S_DEPT_CODE" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" style="width:100%;text-transform:uppercase" /></td>
                --%>
                <td class="label-1">Emp Code <s:if test="%{ACCESS_FLAG=='No'}"><s:textfield  name="S_EMP" id="S_EMP" theme="simple" readonly="true" value="%{#session.sessUserId}"   cssClass="textreadonly" onblur="validatenumber(this)"  onkeypress="return tabE(this, event)" cssStyle="width:50px;text-transform:uppercase" /></s:if><s:else><s:textfield  name="S_EMP" id="S_EMP" theme="simple"   cssClass="texts" onblur="validatenumber(this)"  onkeypress="return tabE(this, event)" cssStyle="width:50px;text-transform:uppercase" /></s:else></td>
                <td class="label-1">Whlo <s:textfield  name="S_WHLO" id="S_WHLO" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:50px;text-transform:uppercase" /></td>
                 <td class="label-1">Dept <s:textfield  name="S_DEPT_DESC" id="S_DEPT_DESC" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:100px;text-transform:uppercase" /></td>
                 <td class="label-1">Control No <s:textfield  name="S_CONTROL" id="S_CONTROL"  theme="simple" onblur="validatenumber(this)"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:50px;text-transform:uppercase" /></td>
                 <td class="label-1">Bill No <s:textfield  name="S_Bill_NO" id="S_Bill_NO" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:70px;text-transform:uppercase" /></td>
               
                <td class="label-1">Bill From Date <sx:datetimepicker  name="S_Bill_FROM" id="S_Bill_FROM" displayFormat="dd/MM/yyyy"      cssClass="texts" />
                    &nbsp;&nbsp;To <sx:datetimepicker  name="S_Bill_TO" id="S_Bill_TO" displayFormat="dd/MM/yyyy"     cssClass="texts" /></td>
                 <td class="label-1">Supplier Code <s:textfield  name="S_SUPPLIER" id="S_SUPPLIER" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:100px;text-transform:uppercase" /></td>
                  <td class="label-1">Status <s:select name="STATUS" theme="simple" cssClass="texts1" list="# {'P':'Pending','F':'Forwarded(A/Cs)','A':'All'}" value="%{STATUS}"/> </td>
          
            </tr>
            <tr style="background-color: #b2cecf;height:35px;">
                <td style="text-align: center;"  colspan="10">
                    <button onclick="ongosearch();" class="sexybutton"><span><span><span class="search">Search</span></span></span></button>
                    &nbsp;<button onclick="ongonew();" class="sexybutton"><span><span><span class="edit">New</span></span></span></button>
                    &nbsp;<button onclick="ongoacctn()" class="sexybutton"><span><span><span class="forward">Control No</span></span></span></button>
                    &nbsp;<button onclick="ongoac()" class="sexybutton"><span><span><span class="forward">Forward</span></span></span></button>
                    &nbsp;<button onclick="ongodelete()" class="sexybutton"><span><span><span class="cancel">Delete</span></span></span></button>
                    &nbsp;<button onclick="self.close();" class="sexybutton"><span><span><img src="css/ShahiButtons/images/icons/silk/bin_closed.png" alt="" />Exit</span></span></button>
                </td> 
            </tr>
            <tr>
                <td style="width:100%;height:560px" colspan="10" valign="top" >                    
                        <div  class="div1" style="width:100%;overflow:auto ;height:550px;">
                            <table border="0" align="center" width="100%" cellpadding="1" style="background-color: #d0d7e5"  cellspacing="1" >
                                        <thead>
                                      

                                          <tr style="position: relative; top: expression(this.offsetParent.scrollTop);height:20pt;" >
                                          <th class="label-1" colspan="12"  style="background-color:#f5f6f8 ;"  ></th>
                                          <th class="label-1" align="center" colspan="2"  valign="top" style="background-color:#f5f6f8 ;">Control No</th>
                                          <th class="label-1" colspan="2"  style="background-color:#f5f6f8 ;"></th>
                                         </tr>
                                         <tr style="position: relative; top: expression(this.offsetParent.scrollTop);height:20pt">
                                          <th class="label-1"  style="background-color:#f5f6f8 ;"  align="left">Sl No</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Whlo</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Dept</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Bill No</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Bill Date</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Supplier</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="right">Bill Amt.</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="right">Gross Amt.</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="right">Product Amt.</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="right">Debit Amt.</th>
                                          
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="center">Status</th>
                                          
                                          <th class="label-1" align="center"  style="background-color:#f5f6f8 ;">Action</th>
                                          <th class="label-1" style="background-color: #f5f6f8" align="left"><input name="acchkR" type="checkbox" onclick="CheckAll(document.searchsubs.REPORT_NO,this)">Create
                                          </th>
                                          <th style="background-color: #f5f6f8"  class="label-1" align="left"><input name="acchkD" type="checkbox" onclick="CheckAll(document.searchsubs.UP_REPORT_NO,this)">Delete
                                          </th>
                                          <th class="label-1" style="width:70px;" style="background-color:#f5f6f8 ;" align="left"><input name="acchk" type="checkbox" onclick="CheckAllAC()">A/Cs.</th>
                                          <th style="width:70px;" class="label-1"  style="background-color:#f5f6f8 ;" align="left"><input name="dchk" type="checkbox" onclick="CheckAllDelete()">&nbsp;Delete</th>
                                    
                                         
                                </tr>
                            </thead>
                            <tbody>	
                                
                                <s:iterator value="%{deptmastlist}" status="st">
                                    <s:if test="%{FLAG=='EXIST'}">
                                       <tr style="font-size:11px;background-color: #D8BFD8;"> 
                                    </s:if>
                                    <s:else>
                                         <tr style="font-size:11px;background-color: #FFFFFF">
                                    </s:else>
                                        <td style="height:20px;"><s:property value="%{#st.index+1}"/></td>			
                                        <td><s:property value="%{BILL_WHLO}"/></td>
                                         <td><s:property value="%{DEPT_SL_NO}"/></td>	
                                        <td><s:property value="%{BILL_NO}"/></td>	
                                        <td><s:property value="%{BILL_DATE}"/></td>
                                         <td><s:property value="%{SUPPLIER_CODE}"/></td>
                                          <td align="right">
                                              <s:text name="product.req">
                                                  <s:param name="value" value="%{BILL_AMOUNT}"/> 
                                              </s:text>
                                              
                                          </td>
                                          <td  align="right">
                                              <s:text name="product.req">
                                                  <s:param name="value" value="%{GROSS_AMT}"/> 
                                              </s:text>
                                             
                                          </td>
                                          <td  align="right">
                                              <s:text name="product.req">
                                                  <s:param name="value" value="%{PRODUCT_AMOUNT}"/> 
                                              </s:text>
                                              
                                          </td>
                                            <td  align="right">
                                                <s:set id="debitamt" value="@java.lang.Double@parseDouble(DEBIT_AMOUNT)"/>
                                                <s:if test="%{#debitamt>0}">
                                                <s:text name="product.req">
                                                  <s:param name="value" value="%{#debitamt}"/> 
                                              </s:text>
                                                </s:if>
                                              
                                          </td>
                                          
                                          <td width="50px" <s:if test="%{GROSS_AMT==PRODUCT_AMOUNT}">style="background-color:green "</s:if>  <s:else>style="background-color:red "</s:else>   align="center">
                                              &nbsp;
                                          </td>
                                        <td style="width:70px;" align="center">
                                         <s:url id="url" action="newmstmbillentAction.action">
                                             <s:param name="MAST_SL_NO" value="%{SL_NO}"/>
                                            <s:param name="S_DEPT_DESC" value="%{S_DEPT_DESC}"/> 
                                            <s:param  name="S_CONTROL" value="%{S_CONTROL}"/> 
                                            <s:param  name="S_Bill_NO" value="%{S_Bill_NO}"/> 
                                            <s:param  name="S_Bill_FROM" value="%{S_Bill_FROM}"/> 
                                            <s:param  name="S_Bill_TO" value="%{S_Bill_TO}"/> 
                                            <s:param  name="S_SUPPLIER" value="%{S_SUPPLIER}"/> 
                                            <s:param name="S_WHLO" value="%{S_WHLO}"/>
                                            <s:param name="S_EMP" value="%{S_EMP}"/>
                                            <s:param name="EMPTYPE" value="%{'ENT'}"/>
                                            
                                        </s:url>
                                            
                                            
                                        <s:a href="%{#url}" ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail"/></s:a>
                                         </td>
                                         
                                              <s:if test="%{GROSS_AMT==PRODUCT_AMOUNT}">
                                                  <s:if test="%{REPORT_NO==null}">
                                                  <td>
                                                       <input type="checkbox" name="REPORT_NO" value='<s:property value="%{SL_NO}"/>'/> 
                                                  </td>
                                                  <td></td>
                                                  </s:if>
                                                  <s:else>
                                                      <td>
                                                      <s:property value="%{REPORT_NO}"/>
                                                      </td>
                                                      <td>
                                                          <s:if test="%{ACC_DATE==null}">
                                                           <input type="checkbox" name="UP_REPORT_NO" value='<s:property value="%{SL_NO}"/>'/> 
                                                         </s:if>
                                                          </td>
                                                  </s:else>
                                                 
                                                  </s:if>
                                                  <s:else>
                                                      <td>
                                                      
                                                      </td>
                                                      <td></td>
                                                  </s:else>
                                         
                                          <td>
                                              
                                              
                                              <s:if test="%{ACC_DATE==null}">
                                                  <s:if test="%{GROSS_AMT==PRODUCT_AMOUNT && REPORT_NO!=null}">
                                                      <s:if test="%{ACREMARK.size()>0}">
                                                          <s:set id="strid" value="" name="strid"/>
                                                          <s:set id="stridctn" value="1" name="stridctn"/>
                                                            <s:iterator value="ACREMARK" status="ACREMARKST">
                                                                <s:if test="%{#ACREMARKST.index==0}">
                                                                      <s:set id="strid" value="%{#stridctn +'.'+ACREMARK[#ACREMARKST.index]}" name="strid"/>
                                                              
                                                                </s:if>
                                                                <s:else>
                                                                <s:set id="strid" value="%{#strid+'</br>'+ #stridctn +'.'+ACREMARK[#ACREMARKST.index]}" name="strid"/>
                                                               </s:else>
                                                                <s:set id="stridctn" value="%{#stridctn+1}" name="stridctn"/>
                                                            </s:iterator>
                                                             <a href="javascript:void(0);" onmouseover="Tip('<s:property value="%{#strid}"/>', TITLE, '<img src=css/ShahiButtons/images/icons/silk/error.png >', BGCOLOR, '#f5f5f5', FONTCOLOR, '#101010', FONTSIZE, '9pt', FONTFACE, 'Courier New, Courier, mono', BORDERCOLOR, '#c00000')" onmouseout="UnTip()">
                                                                 <img style="border:0px" src="css/ShahiButtons/images/icons/silk/error.png"/>
                                                             </a>
                                                             
                                                      </s:if>
                                                      <s:else>
                                                    <input type="checkbox" name="accchkmaster" value='<s:property value="%{SL_NO}"/>'/> 
                                                  </s:else>
                                                  </s:if>
                                                  <s:else>
                                                      
                                                  </s:else>
                                              </s:if>
                                              <s:else>
                                                  <s:property value="%{ACC_DATE}"/>
                                              </s:else>
                                               
                                          </td>
                                       <td>
                                       <s:if test="%{ACC_DATE!=null}">
                                     
                                    </s:if>
                                    <s:else>
                                        <input type="checkbox" name="chkdelmaster" value='<s:property value="%{SL_NO}"/>'/> 
                                    </s:else>
                                       </td>
                                     
                                 </tr>						
                            </s:iterator>	
                            </tbody>
                        </table>  
                        </div>
                </td>
            </tr>
            <tr><td colspan="10">
                    <table style="position: relative;">
                                            <tr>
                                                <td><div style="width:25px;height:20px;background-color:green;border-width:1pt;border-color:green;border-style:solid;"></div></td><td class="label-1">Gross Amount = Product Amount &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:red;border-width:1pt;border-color:red;border-style:solid;"></div></td><td class="label-1">Gross Amount != Product Amount </td>
                                            </tr>
                                            </table>
                </td></tr>
            <tr>
                <td style="text-align: center;color:red;font-size: 15px;width:90%;background-color: #FFFFFF;" colspan="10">  <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage /></td>
            </tr>
        </table>
           
    </form>
  
</body>
</html>