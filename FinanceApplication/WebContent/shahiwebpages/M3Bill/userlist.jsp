<%-- 
    Document   : userlist
    Created on : Dec 2, 2013, 12:12:45 PM
    Author     : RANJEET
--%>


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
            document.getElementById('formId').action='exelistmbilluserAction.action';
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
          
            document.getElementById('formId').action='exenewmbilluserAction.action';
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
  
 <DIV align="center" id="prepage" class="lodingdiv" style="position:absolute;" >
<img src="css/image/progress.gif" >
<br>
<B >Loading ... ... Please wait ...</B>
</DIV>        
                       
    <form method="POST" id="formId" name="searchsubs" action=""> 
        <table style="background-color: #f2f2f2;width: 100%;" cellpadding="1" cellspacing="0">    
            <tr>
                <td style="width:100%;height: 25px;text-align: center" colspan="10" class="hd" > User List   </td></tr>
            <tr style="background-color: #b2cecf;height:35px;">
               
                <td class="label-1">Emp Code <s:textfield  name="S_EMP" id="S_EMP" theme="simple"   cssClass="texts" onblur="validatenumber(this)"  onkeypress="return tabE(this, event)" cssStyle="width:50px;text-transform:uppercase" /></td>
               <%-- <td class="label-1">Whlo <s:textfield  name="S_WHLO" id="S_WHLO" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:50px;text-transform:uppercase" /></td>
                 <td class="label-1">Dept <s:textfield  name="S_DEPT_DESC" id="S_DEPT_DESC" theme="simple"  cssClass="texts" onkeypress="return tabE(this, event)" cssStyle="width:120px;text-transform:uppercase" /></td>
                --%>
                <td style="text-align: right;">
                    <button onclick="ongosearch();" class="sexybutton"><span><span><span class="search">Search</span></span></span></button>
                    &nbsp;<button onclick="ongonew();" class="sexybutton"><span><span><span class="edit">New</span></span></span></button>
                   <%-- &nbsp;<button onclick="ongodelete()" class="sexybutton"><span><span><span class="cancel">Delete</span></span></span></button>
                    --%>
                     &nbsp;<button onclick="self.close();" class="sexybutton"><span><span><img src="css/ShahiButtons/images/icons/silk/bin_closed.png" alt="" />Exit</span></span></button>
                </td> 
            </tr>
            <tr>
                <td style="width:100%;height:560px" colspan="10" valign="top" >                    
                        <div  class="div1" style="width:100%;overflow:auto ;height:550px;">
                            <table border="0" align="center" width="100%" cellpadding="1" style="background-color: #d0d7e5"  cellspacing="1" >
                                        <thead>
                                      
                                         <tr style="position: relative; top: expression(this.offsetParent.scrollTop);height:20pt">
                                          <th class="label-1"  style="background-color:#f5f6f8 ;"  align="left">Sl No</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;"  align="left">Employee</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Whlo</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Active</th>
                                          <th class="label-1"  style="background-color:#f5f6f8 ;" align="left">Access</th>
                                          <th class="label-1" align="center"  style="background-color:#f5f6f8 ;">Action</th>
                                        
                                         
                                </tr>
                            </thead>
                            <tbody>	
                                
                                <s:iterator value="%{userlist}" status="st">
                                    
                                         <tr style="font-size:11px;background-color: #FFFFFF">
                                         <td style="height:20px;"><s:property value="%{#st.index+1}"/></td>			
                                        <td><s:property value="%{SL_NO}"/>-<s:property value="%{TYPE_SL_NO}"/></td>
                                         <td><s:property value="%{SUB_TYPE_DESC}"/></td>	
                                        <td><s:property value="%{SUB_TYPE_CODE}"/></td>	
                                        <td><s:property value="%{FLAG}"/></td>
                                        
                                        <td style="width:70px;" align="center">
                                         <s:url id="url" action="mbilluserAction.action">
                                             <s:param name="EMP_CODE" value="%{SL_NO}"/>
                                             <s:param name="S_EMP" value="%{S_EMP}"/>
                                             
                                           </s:url>
                                         <s:a href="%{#url}" ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail"/></s:a>
                                         </td>
                                        
                                     
                                 </tr>						
                            </s:iterator>	
                            </tbody>
                        </table>  
                        </div>
                </td>
            </tr>
           
            <tr>
                <td style="text-align: center;color:red;font-size: 15px;width:90%;background-color: #FFFFFF;" colspan="10">  <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage /></td>
            </tr>
        </table>
           
    </form>
  
</body>
</html>