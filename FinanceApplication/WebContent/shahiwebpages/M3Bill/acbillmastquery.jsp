<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
    <head>
        <s:head/>
        <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />         
    <title>SEPL</title>
<link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>
<script type="text/javascript">
    var GB_ROOT_DIR = "./greybox/";
</script>
<script type="text/javascript" src="greybox/AJS.js"></script>
<script type="text/javascript" src="greybox/AJS_fx.js"></script>
<script type="text/javascript" src="greybox/gb_scripts.js"></script>
<link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" />

    <script>
            function printcard(a,c)
      {
                      document.getElementById(a+'id').href="report/passingreportjsp.jsp?cno="+a+"&usrid="+c ;
      }
        
        
        function ongosearch() {
            document.getElementById('formId').action='exeacmbillentAction.action';
            document.getElementById('formId').submit();
        }
       
        
        
        
        function ongoacrec() {
            if(validateREC()==true){
            if (confirm('Do you want to Received??')) {
                
                 document.getElementById('formId').action='exeacmbillentAction.action?recflg=Yes';
                 document.getElementById('formId').submit();
               
            }}
        }
        function ongoac() {
            if(validatechkac()==true){
            if (confirm('Do you want to Approved??')) {
                
                 document.getElementById('formId').action='exeacmbillentAction.action?actflg=Yes';
                 document.getElementById('formId').submit();
               
            }}
        }
        
      function ongoacR() {
            if(validatechkacR()==true){
            if (confirm('Do you want to Rejected??')) {
                
                 document.getElementById('formId').action='exeacmbillentAction.action?rejflg=Yes';
                 document.getElementById('formId').submit();
               
            }}
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
         function CheckAllACR()
        {
            a = document.searchsubs.accchkmasterR;
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    if (document.searchsubs.acchkR.checked)
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
                if (document.searchsubs.acchkR.checked)
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
       
        function validatechkacR() 
       {
         var flag=0;
            a = document.searchsubs.accchkmasterR;
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
            
            
          
     function CheckAllREC()
        {
            a = document.searchsubs.accrec;
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    if (document.searchsubs.acchkrec.checked)
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
                if (document.searchsubs.acchkrec.checked)
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
         function validateREC() 
       {
         var flag=0;
            a = document.searchsubs.accrec;
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
                          
         function itemdetailClose(){
  document.getElementById('itemdetail').style.display = "none";
}              
    </script>
   <style>
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
<body style="text-align:center;margin:0px;background-color: #f2f2f2;">
 <script type="text/javascript" src="wz_tooltip/wz_tooltip.js"></script>
<script type="text/javascript" src="wz_tooltip/tip_balloon.js"></script>
<script type="text/javascript" src="wz_tooltip/tip_drag.js"></script>
    <form method="POST" id="formId" name="searchsubs" action=""> 
        <table style="background-color: #f2f2f2;width: 100%;" cellpadding="0" cellspacing="0">    
            <tr>
                <td style="width:100%;height: 25px;text-align: center" colspan="10" class="hd" > Bill Entry Query(A/Cs.)   </td></tr>
            <tr style="background-color: #b2cecf;height:35px;">
                <%--<td class="label-1" style="width:10%;">Department Code</td>
                <td style="width:100px;"><s:textfield name="S_DEPT_CODE" id="S_DEPT_CODE" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" style="width:100%;text-transform:uppercase" /></td>
                --%>
                 <td class="label-1">Dept <s:textfield  name="S_DEPT_DESC" id="S_DEPT_DESC" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:120px;text-transform:uppercase" /></td>
                <td class="label-1">Control No <s:textfield  name="S_CONTROL" id="S_CONTROL"  theme="simple" onblur="validatenumber(this)"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:80px;text-transform:uppercase" /></td>
                 
                 <td class="label-1">Bill No <s:textfield  name="S_Bill_NO" id="S_Bill_NO" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:80px;text-transform:uppercase" /></td>
               
                <td class="label-1">Bill From Date <sx:datetimepicker  name="S_Bill_FROM" id="S_Bill_FROM" displayFormat="dd/MM/yyyy"      cssClass="texts" />
                    &nbsp;&nbsp;To <sx:datetimepicker  name="S_Bill_TO" id="S_Bill_TO" displayFormat="dd/MM/yyyy"     cssClass="texts" /></td>
                 <td class="label-1">Supplier <s:textfield  name="S_SUPPLIER" id="S_SUPPLIER" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:120px;text-transform:uppercase" /></td>
                 <td class="label-1">Status <s:select name="STATUS" theme="simple" cssClass="texts" list="# {'F':'Receiving Pending','L':'Approval Pending','R':'Received','P':'Approved','A':'All'}" value="%{STATUS}"/> </td>
            </tr>
            <tr style="background-color: #b2cecf;height:35px;">

               
                <td colspan="10" align="center">
                    <button onclick="ongosearch();" class="sexybutton"><span><span><span class="search">Search</span></span></span></button>
                    &nbsp;<button onclick="ongoacrec()" class="sexybutton"><span><span><span class="forward">Received</span></span></span></button>
                    &nbsp;<button onclick="ongoac()" class="sexybutton"><span><span><span class="forward">Approved</span></span></span></button>
                    &nbsp;<button onclick="ongoacR()" class="sexybutton"><span><span><span class="rewind">Rejected</span></span></span></button>
                    &nbsp;<button onclick="self.close();" class="sexybutton"><span><span><img src="css/ShahiButtons/images/icons/silk/bin_closed.png" alt="" />Exit</span></span></button>
                </td> 
            </tr>
            <tr>
                <td style="width:100%" colspan="10">                    
                        <div  class="div1" style="width:100%;overflow:auto ;height:550px;">
                                    <table border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                                        <thead>
                                      <tr  class="hd" style="position: relative; top: expression(this.offsetParent.scrollTop);height:20pt" >

                                          <th class="label-1" >Control No</th>
                                          
                                           <th class="label-1" >Dept</th>
                                         
                                          <th class="label-1" >Supplier</th>
                                          <th class="label-1" >Old Ctl. No</th>
                                          <th class="label-1">Whlo</th>
                                          <th class="label-1" >Bill No</th>
                                          <th class="label-1" >Bill Date</th>
                                          <th class="label-1" align="right" >Bill Amt.</th>
                                          <th class="label-1" align="right">Gross Amt.</th>
                                          <th class="label-1" align="right">Product Amt.</th>
                                          <th class="label-1" align="Right">Debit Amt.</th>
                                          
                                          <th class="label-1" align="center"  style="width:50px" >Action</th>
                                          <th class="label-1" align="center"  style="width:50px" >Print</th>
                                          <th class="label-1" style="width:90px;">Forwarded</th>
                                           <th class="label-1" style="width:50px;"><input name="acchkrec" type="checkbox" onclick="CheckAllREC()">Rec.</th>
                                         
                                         <th class="label-1" style="width:50px;"><input name="acchk" type="checkbox" onclick="CheckAllAC()">App.</th>
                                          <th class="label-1" style="width:50px;"><input name="acchkR" type="checkbox" onclick="CheckAllACR()">Rej.</th>
                                          
                                    
                                </tr>
                            </thead>
                            <tbody>	
                                
                                  <s:iterator value="accgrplist" status="st1">
                                             <s:set id="SEARCHDESCTEMP" value="%{TEMPSTR}"/>
                                             <s:if test="#st1.odd == true">
                                             <tr style="font-size:11px;background-color: #FFFFFF">
                                              </s:if>
                                              <s:else>
                                               <tr style="font-size:11px;background-color: #e6e9f5">
                                              </s:else>
                                                   <td><s:property value="%{REPORT_NO}"/></td>
                                                    <td><s:property value="%{DEPT_SL_NO}"/></td>
                                                   <td><s:property value="%{SUPPLIER_CODE}"/></td>
                                
                                
                                <s:iterator value="deptmastlist.{?#this.TEMPSTR==#SEARCHDESCTEMP}" status="st">
                                    <s:if test="%{#st.index==0}" >
                                        
                                        	<td><s:property value="%{REPORT_OLD}"/></td>		
                                          <td style="height:20px;"><s:property value="%{BILL_WHLO}"/></td>
                                          <td><s:property value="%{BILL_NO}"/></td>	
                                          <td><s:property value="%{BILL_DATE}"/></td>
                                         
                                          <td align="right">
                                              <s:text name="product.req">
                                                  <s:param name="value" value="%{BILL_AMOUNT}"/> 
                                              </s:text>
                                              
                                          </td>
                                          <td align="right">
                                              <s:text name="product.req">
                                                  <s:param name="value" value="%{GROSS_AMT}"/> 
                                              </s:text>
                                             
                                          </td>
                                          <td align="right">
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
                                          <%--
                                          <td width="50px" <s:if test="%{GROSS_AMT==PRODUCT_AMOUNT}">style="background-color:green "</s:if>  <s:else>style="background-color:red "</s:else>   align="center">
                                              &nbsp;
                                          </td> --%>
                                        <td align="center" >
                                            <s:url id="url" action="newmstmbillentAction.action">
                                            <s:param name="MAST_SL_NO" value="%{SL_NO}"/>
                                            <s:param name="S_DEPT_DESC" value="%{S_DEPT_DESC}"/> 
                                            <s:param  name="S_CONTROL" value="%{S_CONTROL}"/> 
                                            <s:param  name="S_Bill_NO" value="%{S_Bill_NO}"/> 
                                            <s:param  name="S_Bill_FROM" value="%{S_Bill_FROM}"/> 
                                            <s:param  name="S_Bill_TO" value="%{S_Bill_TO}"/> 
                                            <s:param  name="S_SUPPLIER" value="%{S_SUPPLIER}"/> 
                                            <s:param  name="STATUS" value="%{STATUS}"/> 
                                             <s:param  name="ACC_FLAG" value="%{'Yes'}"/> 
                                             <s:param name="EMPTYPE" value="%{'ACC'}"/>
                                        </s:url>
                                            <s:a href="%{#url}" target="itemdetailfrm"  onclick='document.getElementById("itemdetail").style.display="block";' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail"/></s:a>
                                        </td><td  align="center">
                                              <s:if test="%{REC_ACC_DATE!=null}">
                                        <a id="<s:property value="%{REPORT_NO}"/>id" onclick="printcard('<s:property value="%{REPORT_NO}"/>','<s:property value="%{#session.sessUserId}"/>')" target="_blank" href="void()"><img style="border:0px" src="../css/image/printer.png"/></a>
                                                   
                                          </s:if>
                                          </td>
                                         
                                           <td>
                                         <s:property value="%{ACC_DATE}"/>
                                       </td>
                                       
                                       <td>
                                              <s:if test="%{REC_ACC_DATE==null}">
                                                 
                                                  <input type="checkbox" name="accrec" value='<s:property value="%{SL_NO}"/>'/> 
                                                  
                                              </s:if>
                                              <s:else>
                                                  <s:property value="%{REC_ACC_DATE}"/>
                                              </s:else>
                                               
                                          </td>
                                       
                                          <td>
                                              <s:if test="%{ACC_RE_DATE==null}">
                                                  <s:if test="%{REC_ACC_DATE!=null}">
                                                      <s:if test="%{GL_FLAG=='No'}">
                                                         <a href="javascript:void(0);" onmouseover="Tip('Reverse Service Tax  GL Code   is Not Entered', TITLE, '<img src=css/ShahiButtons/images/icons/silk/error.png >', BGCOLOR, '#f5f5f5', FONTCOLOR, '#101010', FONTSIZE, '9pt', FONTFACE, 'Courier New, Courier, mono', BORDERCOLOR, '#c00000')" onmouseout="UnTip()">
                                                                 <img style="border:0px" src="css/ShahiButtons/images/icons/silk/error.png"/>
                                                             </a>
                                                      </s:if>
                                                      <s:else>
                                                  <input type="checkbox" name="accchkmaster" value='<s:property value="%{SL_NO}"/>'/> 
                                                 </s:else>
                                                  </s:if>
                                              </s:if>
                                              <s:else>
                                                  <s:property value="%{ACC_RE_DATE}"/>
                                              </s:else>
                                               
                                          </td>
                                          <td>
                                              <s:if test="%{ACC_RE_DATE==null}">
                                                 
                                                  <input type="checkbox" name="accchkmasterR" value='<s:property value="%{SL_NO}"/>'/> 
                                                  
                                              </s:if>
                                              
                                          </td>
                                      
                                     
                                 </tr>	
                                    </s:if>
                                 <s:else>
                                 <s:if test="#st1.odd == true">
                                             <tr style="font-size:11px;background-color: #FFFFFF">
                                              </s:if>
                                              <s:else>
                                               <tr style="font-size:11px;background-color: #e6e9f5">
                                              </s:else>
                                    <td colspan="3"></td>
                                        			
                                        	<td><s:property value="%{REPORT_OLD}"/></td>
                                          <td style="height:20px;"><s:property value="%{BILL_WHLO}"/></td>
                                          <td><s:property value="%{BILL_NO}"/></td>	
                                          <td><s:property value="%{BILL_DATE}"/></td>
                                          <td align="right">
                                              <s:text name="product.req">
                                                  <s:param name="value" value="%{BILL_AMOUNT}"/> 
                                              </s:text>
                                              
                                          </td>
                                          <td align="right">
                                              <s:text name="product.req">
                                                  <s:param name="value" value="%{GROSS_AMT}"/> 
                                              </s:text>
                                             
                                          </td>
                                          <td align="right">
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
                                          <%--
                                          <td width="50px" <s:if test="%{GROSS_AMT==PRODUCT_AMOUNT}">style="background-color:green "</s:if>  <s:else>style="background-color:red "</s:else>   align="center">
                                              &nbsp;
                                          </td> --%>
                                        <td  align="center">
                                             
                                        <s:url id="url" action="newmstmbillentAction.action">
                                            <s:param name="MAST_SL_NO" value="%{SL_NO}"/>
                                            <s:param name="S_DEPT_DESC" value="%{S_DEPT_DESC}"/> 
                                            <s:param  name="S_CONTROL" value="%{S_CONTROL}"/> 
                                            <s:param  name="S_Bill_NO" value="%{S_Bill_NO}"/> 
                                            <s:param  name="S_Bill_FROM" value="%{S_Bill_FROM}"/> 
                                            <s:param  name="S_Bill_TO" value="%{S_Bill_TO}"/> 
                                            <s:param  name="S_SUPPLIER" value="%{S_SUPPLIER}"/> 
                                            <s:param  name="STATUS" value="%{STATUS}"/> 
                                            <s:param  name="ACC_FLAG" value="%{'Yes'}"/>  
                                             <s:param name="EMPTYPE" value="%{'ACC'}"/>
                                        </s:url>
                                            <s:a href="%{#url}"  target="itemdetailfrm"  onclick='document.getElementById("itemdetail").style.display="block";'><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail"/></s:a>
                                        </td>
                                          <td></td>
                                         
                                           <td>
                                         <s:property value="%{ACC_DATE}"/>
                                       </td>
                                        <td>
                                            
                                              <s:if test="%{REC_ACC_DATE==null}">
                                                 
                                                  <input type="checkbox" name="accrec" value='<s:property value="%{SL_NO}"/>'/> 
                                                  
                                              </s:if>
                                              <s:else>
                                                  <s:property value="%{REC_ACC_DATE}"/>
                                              </s:else>
                                               
                                          </td>
                                          <td>
                                              
                                            
                                              <s:if test="%{ACC_RE_DATE==null}">
                                                  
                                                  <s:if test="%{REC_ACC_DATE!=null}">
                                                      <s:if test="%{GL_FLAG=='No'}">
                                                          <a href="javascript:void(0);" onmouseover="Tip('Reverse Service Tax  GL Code   is Not Entered', TITLE, '<img src=css/ShahiButtons/images/icons/silk/error.png >', BGCOLOR, '#f5f5f5', FONTCOLOR, '#101010', FONTSIZE, '9pt', FONTFACE, 'Courier New, Courier, mono', BORDERCOLOR, '#c00000')" onmouseout="UnTip()">
                                                                 <img style="border:0px" src="css/ShahiButtons/images/icons/silk/error.png"/>
                                                             </a>
                                                      </s:if>
                                                      <s:else>
                                                     <input type="checkbox" name="accchkmaster" value='<s:property value="%{SL_NO}"/>'/> 
                                                  </s:else>
                                                  </s:if>
                                              </s:if>
                                              <s:else>
                                                  <s:property value="%{ACC_RE_DATE}"/>
                                              </s:else>
                                               
                                          </td>
                                          
                                          <td>
                                              <s:if test="%{ACC_RE_DATE==null}">
                                                 
                                                  <input type="checkbox" name="accchkmasterR" value='<s:property value="%{SL_NO}"/>'/> 
                                                  
                                              </s:if>
                                              
                                          </td>
                                      
                                     
                                 </tr>	
                                 </s:else>
                            </s:iterator>	
                                  </s:iterator>
                            </tbody>
                        </table>  
                        </div>
                </td>
            </tr>
            <tr><td colspan="10">
                    <%--
                    <table>
                                            <tr>
                                                <td><div style="width:25px;height:20px;background-color:green;border-width:1pt;border-color:green;border-style:solid;"></div></td><td class="label-1">Gross Amount = Product Amount &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:red;border-width:1pt;border-color:red;border-style:solid;"></div></td><td class="label-1">Gross Amount != Product Amount </td>
                                            </tr>
                                            </table>
                    --%>
                </td></tr>
            <tr>
                <td style="text-align: center;color:red;font-size: 15px;width:90%;background-color: #FFFFFF;" colspan="10">  <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage /></td>
            </tr>
        </table>
            <div id='itemdetail' name='itemdetail' style='width: 1050px; height: 460px; display:none; position: absolute; top: 100px; left:50px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
                    <table width='100%' cellpadding='0' bgcolor="#f2f2f2" cellspacing='0' border='0'>
                        <tr  style="background-image: url('greybox/header_bg.gif')">
                            <td bgcolor="#2a6afe"    style="font-size:12px;color:white;font-weight: bold;height: 25px">&nbsp;</td>
                            <td align="right" ><a href='javascript:itemdetailClose();' style="text-align: left;
    color:black;
    white-space: nowrap;
    font-size: 12px;
    font-family: Arial, Verdana, sans-serif;"><img  src='greybox/w_close.gif'  border='0'/>Close</a></td>
                        </tr>
                        <tr bgcolor="#f2f2f2"><td colspan="2"   valign="top">
                                <table width="100%" cellspacing="0" cellpadding='0'>
                                    <tr>
                                        <td>
                                            <iframe name="itemdetailfrm" id="itemdetailfrm" scrolling="no" frameborder="0" width="1050px" height="460px"></iframe>
                                            
                                        </td>

                                </tr>   
                            </table>
                        </td>
                    </tr></table>
            </div>  
    </form>
   
</body>
</html>