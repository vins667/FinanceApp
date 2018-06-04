<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link rel="stylesheet" href="css/style.css">  
     
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>  
<link rel="stylesheet" type="text/css" href="js/monthyearpicker/css/StyleCalender.css"> 
<script type="text/javascript" language="javascript" src="js/monthyearpicker/js/CalendarControl.js"></script>
<script type="text/javascript">
    var GB_ROOT_DIR = "./greybox/";
</script>
<script type="text/javascript" src="greybox/AJS.js"></script>
<script type="text/javascript" src="greybox/AJS_fx.js"></script>
<script type="text/javascript" src="greybox/gb_scripts.js"></script>
<link href="greybox/gb_styles.css" rel="stylesheet" type="text/css" />
<SCRIPT TYPE="text/javascript" SRC="js/filterlist.js"></SCRIPT>
<html>
    <head>
         <s:head />
          <sx:head />
    	<title>Shahi Exports Pvt Ltd</title>
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
        font-size: 10px;
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
            position: relative;
            height: 510px;
            width: 700px;
            overflow-y: scroll;
            overflow-x: hidden;
            border: solid #006699;
            border-width: 0px 0px 0px 0px;
            padding-top: 23px ;
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
        
        
        <script language="JavaScript"> 

var version = navigator.appVersion; 

function showKeyCode(e) { 
var keycode = (window.event) ? event.keyCode : e.keyCode; 

if ((version.indexOf('MSIE') != -1)) { 
if (keycode == 116) { 
event.keyCode = 0; 
event.returnValue = false; 
return false; 
} 
} 
else { 
if (keycode == 116) { 
return false; 
} 
} 
} 

</script> 
        <script lang="javascript"> 
            
            
            
            
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
	       
	      
            function tabE(obj,e)
            {var e=(typeof event!='undefined')?window.event:e;// IE : Moz
                if(e.keyCode==13)
                {var ele = document.forms[0].elements;
                    for(var i=0;i<ele.length;i++)
                    {var q=(i==ele.length-1)?0:i+1;// if last element : if any other
                        if(obj==ele[i]){ele[q].focus();break}
                    }
                    return false;
                }
            } 
            function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
            		return false;
            	}
            	return true;
            }
            function validatenumberfour(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,4}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
            		return false;
            	}
            	return true;
            }
            
           
            function onsave(){   
            	if(validate()==true && validatetotal()==true <s:if test="%{DEPT_SL_NO==2911 && MAST_SL_NO==null}">&& validateawbno()==true</s:if>  ){
	            	if(confirm('Do you want to Save?')){
                            
                      
	            	document.getElementById('formId').action='savembillentAction.action'
	            	document.getElementById('formId').submit();
	            	}            	
            	}
            }
            function validate(){
            	if(document.getElementById('DEPT_SL_NO').value==""){
            		alert('Department  cannot be blank');
            		document.getElementById('DEPT_SL_NO').focus();
            		return false;
            	}
                if(document.getElementById('BILL_NO').value==""){
            		alert('Bill No  cannot be blank');
            		document.getElementById('BILL_NO').focus();
            		return false;
            	}
 if(document.getElementById('GROSS_AMOUNT').value=="" || document.getElementById('GROSS_AMOUNT').value==0)
                {
                  alert("Please Enter Cost Element Details") ;
                  document.getElementById("billbreakup").style.display="block";
                  return false;   
                }
                
                


            <s:if test="%{MAST_SL_NO==null}">
                    
                    if(eval(document.getElementById('GROSS_AMOUNT').value)< eval(document.getElementById('DEBIT_AMOUNT').value))
                    {
                        
                          alert("Debit Amount  can not be > Gross Amount ");
                          document.getElementById("debitdetaildiv").style.display="block";
                            return false;
                    }
                    
                var BREAK_CODE=document.frmname.BREAK_CODE;
                var BREAK_AMOUNT=document.frmname.BREAK_AMOUNT;
                var FORM_TYPE=document.frmname.FORM_TYPE;
               
                
                
                for(i=0; i<BREAK_CODE.length; i++)
                  {
                    if(BREAK_CODE[i].value!="" && BREAK_CODE[i].value>0) 
                    {   
                                        
                      var d=document.getElementById(BREAK_CODE[i].value+'TYPEFLA').value;
                     
                       if(d=='Y')
                        {
                         var formtype=document.getElementById('FORM_TYPE'+i).value;
                         
                         if(formtype=="")  
                          {
                             alert("Please Select Form Type");
                              document.getElementById("billbreakup").style.display="block";
                             document.getElementById('FORM_TYPE'+i).focus();
                             return false;
                          }
                         } 
                     
                         if(BREAK_AMOUNT[i].value=="" || BREAK_AMOUNT[i].value==0)  
                          {
                             alert("Cost Element Amount cannot be blank/0");
                              document.getElementById("billbreakup").style.display="block";
                             BREAK_AMOUNT[i].focus();
                             return false;
                          }
                    }
                  }  
            
            
            
            
                for(i=0; i<BREAK_CODE.length; i++)
                  { 
                    for(j=i+1; j<BREAK_CODE.length; j++)  
                    {    if(BREAK_CODE[j].value!="" && BREAK_CODE[j].value>0){
                         if(BREAK_CODE[i].value==BREAK_CODE[j].value)
                             {
                               alert("Duplicate  Entry Found")  ;
                               BREAK_CODE[j].focus();
                               return false;
                             }}
                    }
                      
                  }
                  
                   var BILL_AMOUNT=document.getElementById('BILL_AMOUNT').value;
                    var BRKTOTAL=document.getElementById('BRKTOTAL').value;
                  if(eval(BILL_AMOUNT)== eval(BRKTOTAL))
                {
                 
                }else{
                 alert("Total Amount should be equal to Bill Amount "+BILL_AMOUNT);
                  document.getElementById("billbreakup").style.display="block";
                  return false;  
                }
                
                if(document.getElementById('REVERSE_SRVTAX').value==1)
                    {
                        if(document.getElementById('REVERSE_SRVTAX_RATE').value=="" || document.getElementById('REVERSE_SRVTAX_RATE').value==0)
                            {
                               alert("Rate cannot be blank/0");
                              document.getElementById("billbreakup").style.display="block";
                              return false;
                            }else{
                                
                                if(document.getElementById('REVERSE_SRVTAX_RATE').value>100)
                                    {
                                       alert("Rate should be <= 100"); 
                                       document.getElementById('REVERSE_SRVTAX_RATE').focus();
                                       document.getElementById("billbreakup").style.display="block";
                                       return false;
                                    }
                            }
                    }
                
                </s:if>

if(document.getElementById('BILL_DATE1').value=="")
    {
      alert('From Month cannot be blank'); 
        document.getElementById('BILL_DATE1').focus();
            		return false;
    }
    if(document.getElementById('BILL_DATE2').value=="")
    {
      alert('To Month   cannot be blank'); 
      document.getElementById('BILL_DATE2').focus();
      return false;
        
    }
    var fromstr=document.getElementById('BILL_DATE1').value;
    var tostr=document.getElementById('BILL_DATE2').value;
   
  fromstr=fromstr.substring(3, fromstr.length)+fromstr.substring(0, 2);
  tostr=tostr.substring(3, tostr.length)+tostr.substring(0, 2);
 if(eval(tostr)<fromstr)
     {
       alert("To Month can not be < From Month");
       document.getElementById('BILL_DATE2').focus();
      return false;
     }


                if(document.getElementById('TYPE_SL_NO').value==""){
            		alert('Bill Type  cannot be blank');
            		document.getElementById('TYPE_SL_NO').focus();
            		return false;
            	}
                 if(document.getElementById('SUB_TYPE_SL_NO').value==""){
            		alert('Bill Sub Type  cannot be blank');
            		document.getElementById('SUB_TYPE_SL_NO').focus();
            		return false;
            	}
                 if(document.getElementById('CC_CODE').value==""){
            		alert('Cost Center  cannot be blank');
            		document.getElementById('CC_CODE').focus();
            		return false;
            	}
                 
               var flag=0;
        if(document.getElementById('UP_PRODUCT_SL_NO'))
             {   
              var UP_PRODUCT_SL_NO=document.frmname.UP_PRODUCT_SL_NO;
              var UP_PRODUCT_AMOUNT=document.frmname.UP_PRODUCT_AMOUNT;
             if(UP_PRODUCT_AMOUNT.length){
                 for(i=0; i<UP_PRODUCT_SL_NO.length; i++)
                  {
                    if(UP_PRODUCT_SL_NO[i].value!="" && UP_PRODUCT_SL_NO[i].value>0) 
                    {
                    flag=1;
                      if(UP_PRODUCT_AMOUNT[i].value=="" || UP_PRODUCT_AMOUNT[i].value==0)  
                          {
                             alert("Product Amount cannot be blank/0");
                             UP_PRODUCT_AMOUNT[i].focus();
                             return false;
                          }
                    }
                  }  
                 
             }else{
                if(UP_PRODUCT_SL_NO.value!="" && UP_PRODUCT_SL_NO.value>0) 
                    { flag=1;
                      if(UP_PRODUCT_AMOUNT.value=="" || UP_PRODUCT_AMOUNT.value==0)  
                          {
                             alert("Product Amount cannot be blank/0");
                             UP_PRODUCT_AMOUNT.focus();
                             return false;
                          }
                    } 
             }
             
             
             
             
              if(UP_PRODUCT_AMOUNT.length){
                  
            
                    for(i=0; i<UP_PRODUCT_SL_NO.length; i++)
                  {
                    for(j=i+1; j<UP_PRODUCT_SL_NO.length; j++)  
                    {     if(UP_PRODUCT_AMOUNT[j].value!="" && UP_PRODUCT_AMOUNT[j].value>0){
                         if(UP_PRODUCT_SL_NO[i].value==UP_PRODUCT_SL_NO[j].value)
                             {
                               alert("Duplicate  Entry Found")  ;
                               UP_PRODUCT_SL_NO[j].focus();
                               return false;
                             }}
                    }
                      
                  }}
            
             }
                
               if(document.getElementById('PRODUCT_SL_NO'))
             {   
              var UP_PRODUCT_SL_NO=document.frmname.PRODUCT_SL_NO;
               var UP_PRODUCT_AMOUNT=document.frmname.PRODUCT_AMOUNT;
               
                if(UP_PRODUCT_AMOUNT.length){
                 for(i=0; i<UP_PRODUCT_SL_NO.length; i++)
                  {
                    if(UP_PRODUCT_SL_NO[i].value!="" && UP_PRODUCT_SL_NO[i].value>0) 
                    {  flag=1;
                      if(UP_PRODUCT_AMOUNT[i].value=="" || UP_PRODUCT_AMOUNT[i].value==0)  
                          {
                             alert("Product Amount cannot be blank/0");
                             UP_PRODUCT_AMOUNT[i].focus();
                             return false;
                          }
                    }
                  }  
                 
             }else{
                if(UP_PRODUCT_SL_NO.value!="" && UP_PRODUCT_SL_NO.value>0) 
                    {  flag=1;
                      if(UP_PRODUCT_AMOUNT.value=="" || UP_PRODUCT_AMOUNT.value==0)  
                          {
                             alert("Product Amount cannot be blank/0");
                             UP_PRODUCT_AMOUNT.focus()
                             return false;
                          }
                    } 
             }
               
               
               
               if(UP_PRODUCT_AMOUNT.length){
              for(i=0; i<UP_PRODUCT_SL_NO.length; i++)
                  { 
                    for(j=i+1; j<UP_PRODUCT_SL_NO.length; j++)  
                    {    if(UP_PRODUCT_AMOUNT[j].value!="" && UP_PRODUCT_AMOUNT[j].value>0){
                         if(UP_PRODUCT_SL_NO[i].value==UP_PRODUCT_SL_NO[j].value)
                             {
                               alert("Duplicate  Entry Found")  ;
                               UP_PRODUCT_SL_NO[j].focus();
                               return false;
                             }}
                    }
                      
                  }
               }
             }
               
        if(flag==0)
            {
              alert("Please Select Product Group") ;
              return false;
            }
            
           
               
               
            
            	return true;
            }
            
         function totalamount()  
         {
         
         var t=0.0;
         var PRODUCT_SL_NO;
           if(document.getElementById('UP_PRODUCT_AMOUNT'))
             {   
              PRODUCT_SL_NO=document.frmname.UP_PRODUCT_AMOUNT;
             }
             if(document.getElementById('PRODUCT_AMOUNT'))
             {   
              PRODUCT_SL_NO=document.frmname.PRODUCT_AMOUNT;
             }
             
             if(PRODUCT_SL_NO.length)
                 {
                   for(m=0; m<PRODUCT_SL_NO.length; m++)  
                     {
                     if(PRODUCT_SL_NO[m].value!=""){
                       t=t+eval(PRODUCT_SL_NO[m].value); 
                     }
                     }
                 }else{
                     t=PRODUCT_SL_NO.value;
                 }
                 t=eval(t)
                 document.getElementById('TOTAL').value= t.toFixed(2) ;
         }
            
         
         
    function validatetotal()
    {
     var TOTALSAVE=document.getElementById('TOTALSAVE').value;
     var TOTAL=document.getElementById('TOTAL').value;
     var UPTOTAL=document.getElementById('UPTOTAL').value;
     var BILL_AMOUNT=document.getElementById('GROSS_AMOUNT').value;
     var tt=eval(TOTALSAVE)+eval(TOTAL)-eval(UPTOTAL) ;
     tt=tt.toFixed(2);
    
     
     if(eval(BILL_AMOUNT)< eval(tt))
         {
          alert("Total Amount is greater than Gross Amount ");
             return false;
         }
        return true;
    }
    
            function ongoclear(){
           
            	$('#formId').attr('action', 'newmstmbillentAction.action');
        		$("#formId").submit();
            }
            function ongoback(){
            document.getElementById('formId').action='mbillentAction.action';
            document.getElementById('formId').submit();
            }
             function ongonew() {
                 document.getElementById('PCH1').disabled=true;
                 document.getElementById('PCH').disabled='';
             //document.getElementById('CC_CODE').value="";  
            // document.getElementById('CC_DESC').value=""; 
              //document.getElementById('cccodeid').style.visibility='';
              dojo.widget.byId('details').href='newtypembillentAction.action';
                dojo.event.topic.publish("show_detail");
                
                dojo.widget.byId('details1').href='subtypembillentAction.action';
                dojo.event.topic.publish("show_detail1");
                
               // alert('okk');
                 dojo.widget.byId('details3').href='cctypembillentAction.action';
                 dojo.event.topic.publish("show_detail3");
                 // alert('okkkk');
                
                 dojo.widget.byId('details2').href='prodtypembillentAction.action';
                 dojo.event.topic.publish("show_detail2");
                 
        }
        
        function ongodelete() {
            if(validatechk()==true){
            if (confirm('Do you want to delete??')) {
                
              document.getElementById('formId').action='newmstmbillentAction.action?delflg=Yes';
              document.getElementById('formId').submit();
               
            }
            }
        }
        
        
       function validatechk() 
       {
         var flag=0;
            a = document.frmname.chkdel;
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
                    
                    alert("Please Select Record");
                    return false;
                }
            
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
            function approveraddidClose() {
    document.getElementById("approveraddid").style.display = "none";
}
  function invoicesearchClose() {
    document.getElementById("invoicesearch").style.display = "none";
}

   function billbreakupClose() {
    document.getElementById("billbreakup").style.display = "none";
}

function addhead(a){
var td = document.getElementById('headid');
td.innerHTML =a;
}
        	function addrow(){
        		id=document.getElementById("rwcthid").value;        		
        		id1=eval(id)+1;       
        		document.getElementById("rwcthid").value=id1;
        		$('#detailclaim > tbody:last').append('<tr style="background-color: #FFFFFF;"><td>'
	        		+'<s:textfield name="TYPE_DESC" maxlength="30" value="" theme="simple" cssClass="texts" size="40"/>'
	        		+'</td>'
                                +'<td><s:textfield name="TYPE_CODE" maxlength="10" id="TYPE_CODE'+id1+'" value=""  theme="simple" cssClass="textreadonly" size="5"/>'
                                +'</td>'
                       
	        		+'<td>'
					+'<select name="TYPEFLAG" id="TYPEFLAG" class="selecttext">'
					+'<option value="Y">Active</option>'
					+'<option value="N">In Active</option>'
					+'</select>'
					+'</td>'
                                 
                              +'<td></td></tr>');
                                
                          
        	}
        	
        	function deletesubstratdt(a,b,c){
        		if(confirm('Do you want to Delete Bill Type '+c+'?')){
        			var url = "savemstmbillAction.action?delslno="+a+"&MAST_SL_NO="+b;
        			$(location).attr('href',url);
        			//window.location.href();        			        			
        		}
        	}
        	
        	function activedeactive(a){
        		if(confirm('Do you want to Active/Deactive Substrat detail?')){        			
        			$(location).attr('href',a);
        			//window.location.href();        			        			
        		}
        	}
                
               

     function show_details() {
         
                dojo.widget.byId('details').href='newtypembillentAction.action';
                dojo.event.topic.publish("show_detail");
                
            }
            
             function show_details1() {
               dojo.widget.byId('details1').href='subtypembillentAction.action';
                dojo.event.topic.publish("show_detail1");
            }
              function show_details2() {
               
                
                dojo.widget.byId('details2').href='prodtypembillentAction.action';
                dojo.event.topic.publish("show_detail2");
            }
             function show_details3() {
               
                
                dojo.widget.byId('details3').href='cctypembillentAction.action';
                dojo.event.topic.publish("show_detail3");
            }
           
            
function addtxt(){
 document.getElementById('PCH').disabled=true;
var td = document.getElementById('pchtd');

td.innerHTML ='<s:hidden name="PCH" id="PCH1" theme="simple" value=""/>';
}

            
            function editrec(a,b,c,d,e,f)
            {
             //document.getElementById('CC_CODE').value=d;  
             //document.getElementById('CC_DESC').value=e; 
             document.getElementById('PCH').value=f;     
             addtxt();
             document.getElementById('PCH1').value=f;
             
            // document.getElementById('cccodeid').style.visibility='hidden';
             
             dojo.widget.byId('details').href='newtypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&PCH='+f;
             dojo.event.topic.publish("show_detail");
             dojo.widget.byId('details1').href='subtypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&PCH='+f;
             dojo.event.topic.publish("show_detail1");
              dojo.widget.byId('details3').href='cctypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&PCH='+f;
             dojo.event.topic.publish("show_detail3");
             dojo.widget.byId('details2').href='prodtypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&SAVETYPE=edit';
             dojo.event.topic.publish("show_detail2");
            
            
            }
            
             function newrec(a,b,c,d,e,f)
            {
            // document.getElementById('CC_CODE').value=d;  
            // document.getElementById('CC_DESC').value=e; 
              document.getElementById('PCH').value=f; 
             addtxt();
             document.getElementById('PCH1').value=f;
             //document.getElementById('cccodeid').style.visibility='hidden';
             
             dojo.widget.byId('details').href='newtypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&PCH='+f;
             dojo.event.topic.publish("show_detail");
             dojo.widget.byId('details1').href='subtypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&PCH='+f;
             dojo.event.topic.publish("show_detail1");
             dojo.widget.byId('details3').href='cctypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&PCH='+f;
             dojo.event.topic.publish("show_detail3");
             dojo.widget.byId('details2').href='prodtypembillentAction.action?DIS=Yes&TYPE_SL_NO='+b+'&SUB_TYPE_SL_NO='+c+'&CC_CODE='+d+'&SAVETYPE=newrec';
             dojo.event.topic.publish("show_detail2");
           
            
            }
            
            function CheckAllDelete()
        {
            a = document.frmname.chkdel;
            if (a.length > 0)
            {
                for (var i = 0; i < a.length; i++)
                {
                    if (document.frmname.dchk.checked)
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
                if (document.frmname.dchk.checked)
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
        
        
        function getdeptlist(a)
        {
            if(document.getElementById('DEPT_SL_NO').value=="")
                {
                    alert("Please Enter Department");
                    
                }else{
                    document.getElementById("approveraddid").style.display="block";
                    addhead("Cost Center");
                 // document.getElementById('cccodeid').href='searchpagembillentAction.action?SEARCH_TYPE=3&TXTID=&SDPT='+document.getElementById('DEPT_SL_NO').value;  
                }
            
        }
        
        
       
 function addNewListItem(a,b,c){
   
     var d=document.getElementById(a.value).value;
    
 if(d=='Y')
     {
      
      document.getElementById(b).disabled='';
      document.getElementById(c).disabled=true;
     }else{
         document.getElementById(b).value="";
         document.getElementById(b).disabled=true;
         document.getElementById(c).disabled='';
     }
 
 
}

   function totalamountbrk()  
         {
         
         var t=0.0;
         var PRODUCT_SL_NO;
             
              PRODUCT_SL_NO=document.frmname.BREAK_AMOUNT;
              var BREAK_CODE=document.frmname.BREAK_CODE;
            
             
             if(PRODUCT_SL_NO.length)
                 {
                   for(m=0; m<PRODUCT_SL_NO.length; m++)  
                     {
                     if(BREAK_CODE[m].value==""){
                         PRODUCT_SL_NO[m].value="";
                     }
                     if(PRODUCT_SL_NO[m].value!=""){
                       t=t+eval(PRODUCT_SL_NO[m].value); 
                     }
                     }
                 }else{
                     t=PRODUCT_SL_NO.value;
                 }
                 t=eval(t)
                 document.getElementById('BRKTOTAL').value= t.toFixed(2) ;
         }



  function invrec(a)
            {
            
            
             dojo.widget.byId('invdetails'+a).href='invsearchnewmbillentAction.action?INVSLTXT='+a;
             dojo.event.topic.publish("invshow_detail"+a);
            }   
            
            
          function  saveflagnew(a,b)
          {
               if(a.checked==true) 
           {
               document.getElementById(b).value="Yes";
               
               
           }else{
              document.getElementById(b).value="No"; 
           }
          }


function addheadinv(a,b,c){
     var index=b.selectedIndex;
//var td = document.getElementById(a);
var tdrem = document.getElementById(c);
if(b.value=="")
    {
        //td.innerHTML ="Invoice Details ";
        tdrem.innerHTML ="Remarks ";
    }else{
//td.innerHTML ="Invoice Details - "+b.options[index].text;
tdrem.innerHTML ="Remarks - "+b.options[index].text;
    }
}

function openpopinv(a,b,c)
{
  
    if(document.getElementById(b).value=="") 
       {
           alert("Please Select Product Group");
           document.getElementById(b).focus();
       }else 
       if(document.getElementById(c).value=="" || document.getElementById(c).value==0) 
       {
            alert("Product Amount cannot be blank/0");
             document.getElementById(c).focus();
       }else{
           
          document.getElementById(a).style.display="block"; 
       }
       
       
}
 
  function ongosearch() {
            document.getElementById('formId').action='mbillentAction.action';
            document.getElementById('formId').submit();
        }
        
          function ongosearchac() {
            document.getElementById('formId').action='exeacmbillentAction.action';
            document.getElementById('formId').submit();
        }

</script>
 <style type='text/css'> 
.dragme { cursor: move }
</style>
<script type='text/javascript'> 
var ie = document.all;
var nn6 = document.getElementById &&! document.all;
 
var isdrag = false;
var x, y;
var dobj;
 
function movemouse( e ) {
  if( isdrag ) {
    dobj.style.left = nn6 ? tx + e.clientX - x : tx + event.clientX - x;
    dobj.style.top  = nn6 ? ty + e.clientY - y : ty + event.clientY - y;
    return false;
  }
}
 
function selectmouse1( e ) {
  var fobj       = nn6 ? e.target : event.srcElement;
  var topelement = nn6 ? "HTML" : "BODY";
 
  while (fobj.tagName != topelement && fobj.className != "dragme1") {
    fobj = nn6 ? fobj.parentNode : fobj.parentElement;
  }
 
  if (fobj.className=="dragme1") {
      
    isdrag = true;
   
    dobj = document.getElementById(fobj.id+"_popup");
    
    tx = parseInt(dobj.style.left+0);
    ty = parseInt(dobj.style.top+0);
    x = nn6 ? e.clientX : event.clientX;
    y = nn6 ? e.clientY : event.clientY;
    document.onmousemove=movemouse;
    return false;
  }
}
 


function styledPopupCloseeditline(a) {
   
  document.getElementById(a).style.display = "none";
}

  function invrecMASTER()
            {
            
            
             dojo.widget.byId('invdetailsmast').href='invsearchmastmbillentAction.action';
             dojo.event.topic.publish("invshow_detailmaster");
           
            } 
function  chkflagnew(a,b)
          {
               if(a.checked==true) 
           {
               document.getElementById(b).value="Y";
               
               
           }else{
              document.getElementById(b).value="N"; 
           }
          }

//document.onmousedown=selectmouse;
//document.onmouseup=new Function("isdrag1=false");

function importgo()
            {
              dojo.widget.byId('importdetail').href='importsermbillentAction.action';
              dojo.event.topic.publish("show_importdetail");
           
            } 
            
            
            
       function validateawbno()   
       {
           var BREAK_CODE=document.frmname.INV_NONEWMASTER;
            for(i=0; i<BREAK_CODE.length; i++)
                  { 
                    for(j=i+1; j<BREAK_CODE.length; j++)  
                    {    if(BREAK_CODE[j].value!=""){
                           
                         if(BREAK_CODE[i].value==BREAK_CODE[j].value)
                             {
                               alert("Duplicate  Entry Found")  ;
                               document.getElementById("COURIERDIV").style.display = "";
                               BREAK_CODE[j].focus();
                               
                               return false;
                             }}
                    }
                      
                  }


           return true;
       }
           
   function getsuppbill() 
   {
       
     document.frmname.action="newmstbillmbillentAction.action"
     document.frmname.submit();
   }
   
   
 function selectbuyer()
{
var index = document.frmname.buyermyselect.selectedIndex;

document.getElementById('BUYERCODETEST').value=document.frmname.buyermyselect.options[index].value;
document.getElementById('BUYERNAMETEST').value=document.frmname.buyermyselect.options[index].text;
document.getElementById("BUYERDIV").style.display = "none";

}
 
 function AddRowTable(tableID)
    {   var table = document.getElementById(tableID);
       
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var colCount = table.rows[1].cells.length;
      
        for(var i=0; i<colCount; i++)
        {   var newcell = row.insertCell(i);
            newcell.innerHTML = table.rows[1].cells[i].innerHTML;
          
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
      }
 
 
</script>
</head>
      <body class="body1"   onkeydown="return showKeyCode(event)"   style="text-align:center;margin: 0px;padding: 0px;background-color:#f2f2f2">
          <div id='approveraddid' name='approveraddid' style='width: 660px; height: 420px; display:none; position: absolute; top: 100px; left:50px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
                    <table width='700px' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe" height='23'  width=680px'  style="font-size:12px;color:white;font-weight: bold" id="headid" align="center"></td>
                            <td><a href='javascript:approveraddidClose();'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                        </tr>
                        <tr bgcolor="#f2f2f2"><td colspan="2"   valign="top">
                                <table width="100%" cellspacing="0" cellpadding='0'>
                                    <tr>
                                        <td>
                                            <iframe name="addapprofrm" id="addapprofrm" scrolling="no" frameborder="0" width="700px" height="410px"></iframe>
                                            
                                        </td>

                                </tr>   
                            </table>
                        </td>
                    </tr></table>
            </div>  
        
          
          
                              
          <form method="POST" id="formId" name="frmname" action="" style="margin: 0px;padding: 0px"> 
            
              <table style="width: 100%;text-align: center;background-color: #f2f2f2;" border="0"  cellpadding="0" cellspacing="0">            	
              <tr>
                  <td>
                      <table  style="width: 100%;text-align: center;" border="0" cellpadding="1" cellspacing="0">
                        <tr style="background-color: #b2cecf;"> 
                            <td>M3 Bill Entry</td>

                        </tr>
                             
                            <tr>
                                <td colspan="2" style="height: 40px" align="left">
                                    <table cellpadding="0" border="0"  <s:if test="%{MAST_SL_NO!=null && billdetailgrp.size()>0}">width="100%"</s:if><s:else>width="100%"</s:else> cellspacing="1">
                                        <tr class="hd">
                                            <td align="left" class="label-1" style="height:23px">Department</td>
                                            <td align="left" class="label-1">Bill No</td>
                                            <td align="left" class="label-1">Date</td>
                                            <td align="left" class="label-1">Supplier</td>
                                            <td  class="label-1">Month</td>
                                            <td align="left" class="label-1">Bill Amount</td>
                                            <td class="label-1" >Gross Amount</td>
                                            <td class="label-1" colspan="2" >Add Info</td>
                                            
                                        </tr>
                                        <tr>
                                    <td>
                                        <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                            <s:hidden name="MAST_SL_NO" id="MAST_SL_NO" value="%{MAST_SL_NO}"/>
                                        </s:if>
                                        <s:if test="%{MAST_SL_NO==null}">
                                       <%-- <s:hidden name="DEPT_SL_NO" id="DEPT_SL_NO"  />
                                        <s:textfield name="DEPT_DESC" theme="simple" id="DEPT_DESC" cssStyle="font-weight:bold" readonly="true" cssClass="textreadonly" size="20"/>
                                        
                                            <a href="searchpagembillentAction.action?SEARCH_TYPE=10" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Department")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        
                                       --%>
                                       <s:select name="DEPT_SL_NO" id="DEPT_SL_NO" list="mastlist" onchange="getsuppbill()" listKey="EAAITM" listValue="EATX40" headerKey="" headerValue="Select Department" theme="simple"/>
                                   </s:if>
                                       <s:else>
                                       <s:hidden name="DEPT_SL_NO" id="DEPT_SL_NO"  />
                                        <s:textfield name="DEPT_DESC" theme="simple" id="DEPT_DESC" cssStyle="font-weight:bold" readonly="true" cssClass="textreadonly" size="20"/>
                                        
                                    </s:else>
                                    </td>
                                    <td> 
                                        <s:textfield name="BILL_NO" theme="simple" cssStyle="font-weight:bold" id="BILL_NO" readonly="true" cssClass="textreadonly" size="20"/>
                                    </td>
                                    <td>
                                       <s:textfield name="BILL_DATE" theme="simple" cssStyle="font-weight:bold" id="BILL_DATE" readonly="true" cssClass="textreadonly" size="8"/>
                                    </td>
                                    <td>
                                        <s:textfield name="SUPPLIER_CODE" cssStyle="font-weight:bold" theme="simple" id="SUPPLIER_CODE" readonly="true" cssClass="textreadonly" size="8"/>
                                        <s:textfield name="SUPPLIER_DESC" theme="simple" id="SUPPLIER_DESC" cssStyle="font-weight:bold" readonly="true" cssClass="textreadonly" size="50"/>
                                        <s:if test="%{MAST_SL_NO==null}"> <a href="searchpagembillentAction.action?SEARCH_TYPE=11&DEPT_SL_NO=<s:property value="%{DEPT_SL_NO}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Supplier")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                            </s:if>
                                        
                                        <s:set id="amttemp" value="@java.lang.Double@parseDouble(BILL_AMOUNT)"/>
                                        <s:if test="%{#amttemp>0}">
                                        <s:text name="product.req" id="tempamt" >
                                        <s:param name="value" value="%{#amttemp}"/>
                                        </s:text>
                                        </s:if>
                                        
                                        <s:hidden name="BILL_WHLO" id="BILL_WHLO" value="%{BILL_WHLO}"/>
                                        <s:hidden name="BILL_YEAR" id="BILL_YEAR" value="%{BILL_YEAR}"/>
                                        
                                        
                                    </td>
                                    <td>
                                       
                                       <table><tr><td>
                                                   <s:if test="%{MAST_SL_NO!=null && billdetailgrp.size()==0}">
                                                       
                                                         <s:textfield  theme="simple" name="BILL_DATE1"   readonly="true" cssClass="textreadonly" id="BILL_DATE1" value="%{BILL_DATE.substring(3, 10)}" cssStyle="width:50px;" />
                                              
                                                   </s:if>
                                                   <s:else>
                                                         <s:textfield  theme="simple" name="BILL_DATE1"   readonly="true" cssClass="textreadonly" id="BILL_DATE1" value="%{BILL_DATE1}" cssStyle="width:50px;" />
                                              
                                                   </s:else>
                                                  
                                                   
                                                   </td> <s:if test="%{MAST_SL_NO==null || billdetailgrp.size()==0}"><td>
                                       <img alt="Month/Year Picker" onclick="showCalendarControl('BILL_DATE1');" 
				src="js/monthyearpicker/images/datepicker.gif" />
                                               </td></s:if><td>&nbsp;-&nbsp;
                                               </td><td>
                                                   <s:if test="%{MAST_SL_NO!=null && billdetailgrp.size()==0}">
                                                      <s:textfield  id="BILL_DATE2" name="BILL_DATE2" theme="simple" readonly="true" cssClass="textreadonly"  value="%{BILL_DATE.substring(3, 10)}" cssStyle="width:50px;" />
                                               
                                                   </s:if>
                                                   <s:else> 
                                                       <s:textfield  id="BILL_DATE2" name="BILL_DATE2" theme="simple" readonly="true" cssClass="textreadonly"  value="%{BILL_DATE2}" cssStyle="width:50px;" />
                                              
                                                   </s:else>
                                                   </td><s:if test="%{MAST_SL_NO==null  || billdetailgrp.size()==0}"><td>
                                       <img alt="Month/Year Picker" onclick="showCalendarControl('BILL_DATE2');" 
				src="js/monthyearpicker/images/datepicker.gif" />
                                               </td></s:if></tr></table>
                                   </td>
                                  <td><table cellpading="0" cellpsacing="0" ><tr><td><s:textfield name="BILL_AMOUNT" theme="simple" value="%{#tempamt}"  cssStyle="font-weight:bold" id="BILL_AMOUNT" readonly="true" cssClass="textreadonly" size="10"/>
                                   </td>
                                   <td style="padding-top:8px;width:5px">
                                       
                                        <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                            <s:if test="%{ACC_FLAG==null}">
                                                <a href="getcostentdtmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&DEPT_SL_NO=<s:property value="%{DEPT_SL_NO}"/>" class="linkclass"   title="Cost Element Break-Up Details"  rel="gb_page_center[510, 310]">
                                            <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Break-Up"/>
                                                  </a> 
                                            </s:if>
                                            <s:else>
                                                <a href="getcostentdtmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&DEPT_SL_NO=<s:property value="%{DEPT_SL_NO}"/>&ACC_FLAG=Yes"  class="linkclass"  title="Cost Element Break-Up Details"  rel="gb_page_center[510, 310]">
                                            <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Break-Up"/>
                                                  </a> 
                                            </s:else>
                                            
                                        </s:if>
                                        <s:else>
                                        <a href="#" onclick='document.getElementById("billbreakup").style.display="block"'>
                                            <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Break-Up"/>
                                                  </a>
                                        </s:else>
                                       
                                       
                                             <s:set id="amttempg" value="@java.lang.Double@parseDouble(GROSS_AMOUNT)"/>
                                        <s:if test="%{#amttempg>0}">
                                        <s:text name="product.req" id="tempamtg" >
                                        <s:param name="value" value="%{#amttempg}"/>
                                        </s:text>
                                        </s:if>
                                   </td>
                                   <td style="padding-top:8px;width:5px">
                                       <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                            <s:if test="%{ACC_FLAG==null}">
                                                <a href="debitdtmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&DEPT_SL_NO=<s:property value="%{DEPT_SL_NO}"/>" class="linkclass"   title="Debit Detail"  rel="gb_page_center[450, 180]">
                                                    <s:if test="%{ctndebit==0}">                                                    
                                                    <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Debit Detail"/>
                                                  </s:if>
                                                  <s:else>
                                                       <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="Debit Detail"/>
                                                  
                                                  </s:else>
                                                    </a> 
                                            </s:if>
                                            <s:else>
                                                <a href="debitdtmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&DEPT_SL_NO=<s:property value="%{DEPT_SL_NO}"/>&ACC_FLAG=Yes"  class="linkclass"  title="Debit Detail"  rel="gb_page_center[450, 180]">
                                           <s:if test="%{ctndebit==0}">                                                     
                                                    <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Debit Detail"/>
                                                  </s:if>
                                                  <s:else>
                                                       <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="Debit Detail"/>
                                                  
                                                  </s:else>    
                                                </a> 
                                            </s:else>
                                            
                                        </s:if>
                                        <s:else>
                                        <a href="#" onclick='document.getElementById("debitdetaildiv").style.display="block"'>
                                            <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Debit Detail"/>
                                                  </a>
                                        </s:else>
                                   </td>
                                          </tr></table></td>
                                          <td ><s:textfield name="GROSS_AMOUNT" theme="simple" value="%{#tempamtg}"  cssStyle="font-weight:bold" id="GROSS_AMOUNT" readonly="true" cssClass="textreadonly" size="10"/>
                                   </td>
                                   
                                        <td>
                                            
                                            <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                            <s:if test="%{ACC_FLAG==null}">
                                            <a href="remheadmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>" style="text-decoration: none" class="linkclass"   title="Remarks"  rel="gb_page_center[375, 150]">
                                                <s:if test="%{MAST_REMARKS==null}">
                                                    <img style="border: 0px;" src="image/icon_comment.gif" alt="Remarks"/>
                                                 </s:if>
                                                    <s:else>
                                                         <img style="border: 0px;" src="image/comment-18.gif" alt="Remarks"/>
                                                 </s:else>
                                                
                                                </a>
                                           
                                            </s:if>
                                            <s:else>
                                            <a href="remheadmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes" class="linkclass" style="text-decoration: none"  title="Remarks"  rel="gb_page_center[375, 150]">
                                           <s:if test="%{MAST_REMARKS==null}">
                                                    <img style="border: 0px;" src="image/icon_comment.gif" alt="Remarks"/>
                                                 </s:if>
                                                    <s:else>
                                                         <img style="border: 0px;" src="image/comment-18.gif" alt="Remarks"/>
                                                 </s:else>
                                                 
                                                </a> 
                                           
                                            </s:else>
                                            
                                        </s:if>
                                        <s:else>
                                                    <a href="#" onclick='document.getElementById("headtd_popupdt").style.display="block"' style="text-decoration: none">
                                                      &nbsp; <img style="border: 0px" src="image/icon_comment.gif" alt="Remarks"/>
                                                       </a>
                                                   <div id='headtd_popupdt' name='headtd_popupdt' style='background-color: #f2f2f2; width: 382px; height: 180px; display:none; position: absolute; top:100px; right:148px;;border-width: 1px;border-style: solid;border-color: black;overflow: auto;z-index: 1'>
                                                                <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
                                                                    <tr bgcolor="#f2f2f2">
                                                                        <td bgcolor="#2a6afe" height='23' width='356' align="left"  style="font-size:12px;color:white;font-weight: bold">
                                                                                Remarks
                                                                         </td>
                                                                        <td><a href='javascript:styledPopupCloseeditline("headtd_popupdt")'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                                                                    </tr>
                                                                    <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                                                                            <table width="100%"><tr>
                                                                                    <td>
                                                                                        <textarea  class="texts"  name="MAST_REMARKS" style="width:370px;height:110px;overflow:auto"   ></textarea>
                                                                                        
                                                                                    </td>
                                                                                     <tr><td align='center'><button class="sexybutton" onclick='styledPopupCloseeditline("headtd_popupdt")'><span><span><span class="save">Save</span></span></span></button> </td></tr>
                                                                           
                                                                                    
                                                                                </tr>   
                                                                            </table>
                                                                        </td>
                                                                    </tr></table>
                                                            </div> 
                                                    </s:else>
                                                
                                   </td>
                                   <td>
                                       <%-- 26=EXPORTS--%>
                                     
                                       <s:if test="%{DEPT_SL_NO==26}">
                                       <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0  && SHOWFLAG=='INV'}">
                                            <s:if test="%{ACC_FLAG==null}">
                                            <a href="masterinvupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>" style="text-decoration: none" class="linkclass"   title="Invoice Details"  rel="gb_page_center[650, 300]">
                                            <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="Invoice Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Details"/>
                                               </s:else>
                                                </a>
                                           
                                            </s:if>
                                            <s:else>
                                            <a href="masterinvupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes" class="linkclass" style="text-decoration: none"  title="Invoice Details"  rel="gb_page_center[650, 300]">
                                           <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="Invoice Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Details"/>
                                               </s:else>   
                                                </a> 
                                           
                                            </s:else>
                                            
                                        </s:if>
                                       
                                        <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0  && (SHOWFLAG=='BOS' || SHOWFLAG=='SB' || SHOWFLAG=='LCERT')}">
                                            
                                            
                                            <s:if test="%{ACC_FLAG==null}">
                                                <a href="bosmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&SHOWFLAG=<s:property value="%{SHOWFLAG}"/>" style="text-decoration: none" class="linkclass"   title="<s:property value="%{DISPLAY_NAME}"/> Details"  rel="gb_page_center[650, 300]">
                                            <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                               </s:else>
                                                </a>
                                           
                                            </s:if>
                                            <s:else>
                                            <a href="bosmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes&SHOWFLAG=<s:property value="%{SHOWFLAG}"/>" class="linkclass" style="text-decoration: none"  title="<s:property value="%{DISPLAY_NAME}"/> Details"  rel="gb_page_center[650, 300]">
                                           <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                               </s:else>   
                                                </a> 
                                           
                                            </s:else>
                                            
                                        </s:if>
                                       <%--
                                        <s:else>
                                     <a href="#" onclick='document.getElementById("invdtidmaster").style.display="block"' style="text-decoration: none">
                                     <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Detail"/>
                                     </a> 
                                     </s:else>
                                       --%>
                                       </s:if>
                                       <%--clsoe27=IMPORTS--%>
                                       <s:if test="%{DEPT_SL_NO==27}">
                                        <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0  && (SHOWFLAG=='BOE' || SHOWFLAG=='AWBNO' || SHOWFLAG=='EXINV' || SHOWFLAG=='EXLIC')}">
                                            
                                           <s:if test="%{ACC_FLAG==null}">
                                                <a href="bosmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&SHOWFLAG=<s:property value="%{SHOWFLAG}"/>" style="text-decoration: none" class="linkclass"   title="<s:property value="%{DISPLAY_NAME}"/> Details"  rel="gb_page_center[650, 300]">
                                            <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                               </s:else>
                                                </a>
                                           
                                            </s:if>
                                            <s:else>
                                            <a href="bosmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes&SHOWFLAG=<s:property value="%{SHOWFLAG}"/>" class="linkclass" style="text-decoration: none"  title="<s:property value="%{DISPLAY_NAME}"/> Details"  rel="gb_page_center[650, 300]">
                                           <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="<s:property value="%{DISPLAY_NAME}"/> Details"/>
                                               </s:else>   
                                                </a> 
                                           
                                            </s:else>
                                            
                                        </s:if>
                                           
                                           
                                           <%--
                                             <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0 }">
                                            <s:if test="%{ACC_FLAG==null}">
                                            <a href="importupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>" style="text-decoration: none" class="linkclass"   title="AWB Details"  rel="gb_page_center[400, 285]">
                                            <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="AWB Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="AWB Details"/>
                                               </s:else>
                                                </a>
                                           
                                            </s:if>
                                            <s:else>
                                            <a href="importupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes" class="linkclass" style="text-decoration: none"  title="AWB Details"  rel="gb_page_center[400, 285]">
                                           <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="AWB Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="AWB Details"/>
                                               </s:else>   
                                                </a> 
                                           
                                            </s:else>
                                            
                                        </s:if>
                                        <s:else>
                                       <a href="#" onclick='document.getElementById("IMPORTSDIV").style.display="block"' style="text-decoration: none">
                                     <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="AWB Details"/>
                                     </a>  
                                       </s:else> --%>
                                        </s:if>
                                       <%--clsoe27=IMPORTS--%>
                                       
                                       <%--clsoe30=courier with ADMIN--%>
                                        <s:if test="%{DEPT_SL_NO==30 && SHOWFLAG=='Yes'}">
                                            
                                             <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                            <s:if test="%{ACC_FLAG==null}">
                                            <a href="courierupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>" style="text-decoration: none" class="linkclass"   title="AWB Details"  rel="gb_page_center[400, 285]">
                                            <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="AWB Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="AWB Details"/>
                                               </s:else>
                                                </a>
                                           
                                            </s:if>
                                            <s:else>
                                            <a href="courierupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes" class="linkclass" style="text-decoration: none"  title="AWB Details"  rel="gb_page_center[400, 485]">
                                           <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="AWB Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="AWB Details"/>
                                               </s:else>   
                                                </a> 
                                           
                                            </s:else>
                                            
                                        </s:if>
                                            <%--
                                        <s:else>
                                       <a href="#" onclick='document.getElementById("COURIERDIV").style.display="block"' style="text-decoration: none">
                                     <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="AWB Details"/>
                                     </a>  
                                       </s:else> --%>
                                        </s:if> 
                                       <%--clsoe29=courier--%>
                                       
                                       
                                       <%--clsoe35=TESTING with production --%>
                                        <s:if test="%{DEPT_SL_NO==32 && SHOWFLAG=='Yes'}">
                                            
                                             <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                            <s:if test="%{ACC_FLAG==null}">
                                            <a href="testingupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>" style="text-decoration: none" class="linkclass"   title="Report Details"  rel="gb_page_center[530, 360]">
                                            <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="Testing Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Testing Details"/>
                                               </s:else>
                                                </a>
                                           
                                            </s:if>
                                            <s:else>
                                            <a href="testingupmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes" class="linkclass" style="text-decoration: none"  title="Report Details"  rel="gb_page_center[530, 360]">
                                           <s:if test="%{ctninv>0}">
                                                         <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list_y.png" alt="Report Details"/>
                                             </s:if>
                                              <s:else>
                                                <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Report Details"/>
                                               </s:else>   
                                                </a> 
                                           
                                            </s:else>
                                            
                                        </s:if>
                                       <%--     
                                        <s:else>
                                       <a href="#" onclick='document.getElementById("TESTINGDIV").style.display="block"' style="text-decoration: none">
                                     <img style="border: 0px;" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Testing Details"/>
                                     </a>  
                                       </s:else> --%>
                                        </s:if> 
                                       <%--clsoe35=TESTING--%>
                                   </td>
               
                            </tr>
                            
                                    </table>
                                </td>
                            </tr>
                            <td colspan="2" style="border-width:1pt;border-color:black;border-style:solid;">
                                <div  class="div1" style="width:100%;overflow:auto ;<s:if test="%{FORWARD_DATE==null}">height:380px;</s:if><s:else>height:550px;</s:else>">
                                    <table border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
                                        <thead >
                                            <tr  class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);height:15pt" >
                                                <s:if test="%{FORWARD_DATE==null}">
                                                <th class="label-1" style="width:100px" >Action</th>
                                               
                                                </s:if>
                                                <th class="label-1">Bill Type</th>
                                                <th class="label-1">Bill Sub Type</th>
                                                <th class="label-1" >PCH</th>
                                                 <th class="label-1" >Cost Center</th>
                                                  
                                                <th class="label-1">Product Group</th>
                                                
                                                <th class="label-1" align="right">Amount</th>
                                                <th class="label-1"  style="width:5px">&nbsp;&nbsp;Chg</th>
                                               <th class="label-1"  style="width:5px"></th>
                                                <%--<th class="label-1" style="width:5px"></th>--%>
                                                <s:if test="%{FORWARD_DATE==null}">
                                                <th style="width:70px;" class="label-1">
                                                    <input name="dchk" type="checkbox" onclick="CheckAllDelete()">&nbsp;Delete</th>
                                                </s:if>
                               


                                            </tr>
                                        </thead>
                                        <tbody>
                                            <s:set id="totalamt" value="0.0"/>
                                            <s:iterator value="billdetailgrp" status="st1">
                                             <s:set id="SEARCHDESCTEMP" value="%{mastlisttemp[#st1.index]}"/>
                                             <s:if test="#st1.odd == true">
                                             <tr style="font-size:10px;background-color: #FFFFFF">
                                              </s:if>
                                              <s:else>
                                               <tr style="font-size:10px;background-color: #ffffce">
                                              </s:else>
                                              <s:if test="%{FORWARD_DATE==null}">
                                                   <td>
                                                       <a href="#" style="text-decoration: none" onclick="editrec(<s:property value="%{MAST_SL_NO}"/>,<s:property value="%{TYPE_SL_NO}"/>,<s:property value="%{SUB_TYPE_SL_NO}"/>,'<s:property value="%{CC_CODE}"/>','<s:property value="CC_DESC"/>','<s:property value="PCH"/>')">
                                                       <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_edit.png" alt="Edit"/>
                                                   </a>&nbsp;&nbsp;&nbsp;
                                                   <a href="#" onclick="newrec(<s:property value="%{MAST_SL_NO}"/>,<s:property value="%{TYPE_SL_NO}"/>,<s:property value="%{SUB_TYPE_SL_NO}"/>,'<s:property value="%{CC_CODE}"/>','<s:property value="CC_DESC"/>','<s:property value="PCH"/>')">
                                                      <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/add.png" alt="Add"/>
                                                  </a>
                                                 </td>
                                            </s:if>
                                             
                                                <td><s:property value="TYPE_DESC"/></td>
                                                <td><s:property value="SUB_TYPE_DESC"/></td>
                                                <td><s:property value="PCH"/></td>
                                               <td>
                                                   <s:property value="CC_DESC"/>-<s:property value="CC_CODE"/>
                                                  
                                              </td>
                                          <s:iterator value="billdetail.{?#this.SEARCHDESC==#SEARCHDESCTEMP}" status="st">
                                                
                                              <s:if test="%{#st.index==0}" >
                                                <td><s:property value="PRODUCT_DESC"/></td>
                                                
                                                <td align="right">
                                                     <s:text name="product.req" >
                                                      <s:param name="value" value="%{PRODUCT_AMOUNT}"/>
                                                      </s:text>
                                                   
                                                 
                                                </td>
                                                <td>
                                                    <s:if test="%{TAXABLEDESC=='Yes'}">
                                                        &nbsp;<input type="checkbox" disabled="true" name="dtchkchg" checked="true"/>
                                                    </s:if>
                                                    
                                                   </td>
                                                <td align="center">
                                                    
                                                    <a href="#" onclick='document.getElementById("<s:property value="%{SL_NO}"/>td_popupdt").style.display="block"' style="text-decoration: none">
                                                      &nbsp; <s:if test="%{REMARKS==null}">
                                                    <img style="border: 0px;" src="image/icon_comment.gif" alt="Remarks"/>
                                                 </s:if>
                                                    <s:else>
                                                         <img style="border: 0px;" src="image/comment-18.gif" alt="Remarks"/>
                                                 </s:else>
                                                       </a>
                                                     <div id='<s:property value="%{SL_NO}"/>td_popupdt' name='<s:property value="%{SL_NO}"/>td_popupdt' style='background-color: #f2f2f2; width: 382px; height: 150px; display:none; position: absolute; top:230px; right:48px;border-width: 1px;border-style: solid;border-color: black;overflow: auto'>
                                                                <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
                                                                    <tr bgcolor="#f2f2f2">
                                                                        <td bgcolor="#2a6afe" height='23' width='356' align="left" id="<s:property value="%{SL_NO}"/>tddt"  style="font-size:12px;color:white;font-weight: bold">
                                                                                Remarks - <s:property value="PRODUCT_DESC"/>
                                                                         </td>
                                                                        <td><a href='javascript:styledPopupCloseeditline("<s:property value="%{SL_NO}"/>td_popupdt")'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                                                                    </tr>
                                                                    <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                                                                            <table width="100%"><tr>
                                                                                    <td>
                                                                                         <s:textarea readonly="true" cssClass="textreadonly" cols="5" name="UP_REMARKSnot" value="%{REMARKS}" cssStyle="width:370px;height:110px;overflow:auto"  rows="5" />
                                                                                       
                                                                                    </td>
                                                                                    
                                                                                </tr>   
                                                                            </table>
                                                                        </td>
                                                                    </tr></table>
                                                            </div>
                                                    
                                                </td>
                                                <%--
                                                 <td  align="center">
                                                   <a href="invnewmbillentAction.action?BILL_SL_NO=<s:property value="%{MAST_SL_NO}"/>&BILL_DT_SL_NO=<s:property value="%{SL_NO}"/>&PCH=<s:property value="%{PCH}"/>&INV_CC_CODE=<s:property value="%{CC_CODE}"/>&INV_TYPE_SL_NO=<s:property value="%{TYPE_SL_NO}"/>&INV_SUB_TYPE_SL_NO=<s:property value="%{SUB_TYPE_SL_NO}"/>&INV_PRODUCT_SL_NO=<s:property value="%{PRODUCT_SL_NO}"/>"
                                                      class="linkclass"  title="Invoice Details - <s:property value="PRODUCT_DESC"/>"  rel="gb_page_center[650, 285]">
                                                       <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Detail"/>
                                                  </a>  
                                                      
                                                      
                                                  </td> --%>
                                                  <s:if test="%{FORWARD_DATE==null}">
                                                <td> <input type="checkbox" name="chkdel" value='<s:property value="%{SL_NO}"/>'/> 
                                   </td>
                                    </s:if>
                                            </tr>
                                            </s:if>
                                            <s:else>
                                                <s:if test="#st1.odd == true">
                                             <tr style="font-size:10px;background-color: #FFFFFF">
                                              </s:if>
                                              <s:else>
                                               <tr style="font-size:10px;background-color: #ffffce">
                                              </s:else>
                                                   <td <s:if test="%{FORWARD_DATE==null}">colspan="5"</s:if><s:else>colspan="4"</s:else>></td>
                                                 <td><s:property value="PRODUCT_DESC"/>
                                                 
                                                 
                                                 </td>
                                                  
                                                <td align="right"> <s:text name="product.req" >
                                                      <s:param name="value" value="%{PRODUCT_AMOUNT}"/>
                                                      </s:text></td>
                                                <td>
                                                    <s:if test="%{TAXABLEDESC=='Yes'}">
                                                        &nbsp;<input type="checkbox" disabled="true" name="dtchkchg" checked="true"/>
                                                    </s:if>
                                                    
                                                   </td>
                                               <td align="center">
                                                    <a href="#" onclick='document.getElementById("<s:property value="%{SL_NO}"/>td_popupdt").style.display="block"' style="text-decoration: none">
                                                      &nbsp; <s:if test="%{REMARKS==null}">
                                                    <img style="border: 0px;" src="image/icon_comment.gif" alt="Remarks"/>
                                                 </s:if>
                                                    <s:else>
                                                         <img style="border: 0px;" src="image/comment-18.gif" alt="Remarks"/>
                                                 </s:else>
                                                       </a>
                                                   <div id='<s:property value="%{SL_NO}"/>td_popupdt' name='<s:property value="%{SL_NO}"/>td_popupdt' style='background-color: #f2f2f2; width: 382px; height: 150px; display:none; position: absolute; top:230px; right:48px;;border-width: 1px;border-style: solid;border-color: black;overflow: auto'>
                                                                <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
                                                                    <tr bgcolor="#f2f2f2">
                                                                        <td bgcolor="#2a6afe" height='23' width='356' align="left" id="<s:property value="%{SL_NO}"/>tddt"  style="font-size:12px;color:white;font-weight: bold">
                                                                                Remarks - <s:property value="PRODUCT_DESC"/>
                                                                         </td>
                                                                        <td><a href='javascript:styledPopupCloseeditline("<s:property value="%{SL_NO}"/>td_popupdt")'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                                                                    </tr>
                                                                    <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                                                                            <table width="100%"><tr>
                                                                                    <td>
                                                                                        <s:textarea readonly="true" cssClass="textreadonly" cols="5" name="UP_REMARKSnot" value="%{REMARKS}" cssStyle="width:370px;height:110px;overflow:auto"  rows="5" />
                                                                                        
                                                                                    </td>
                                                                                    
                                                                                </tr>   
                                                                            </table>
                                                                        </td>
                                                                    </tr></table>
                                                            </div> 
                                                    
                                                </td>
                                                <%-- 
                                                <td align="center">
                                                     <a href="invnewmbillentAction.action?BILL_SL_NO=<s:property value="%{MAST_SL_NO}"/>&BILL_DT_SL_NO=<s:property value="%{SL_NO}"/>&PCH=<s:property value="%{PCH}"/>&INV_CC_CODE=<s:property value="%{CC_CODE}"/>&INV_TYPE_SL_NO=<s:property value="%{TYPE_SL_NO}"/>&INV_SUB_TYPE_SL_NO=<s:property value="%{SUB_TYPE_SL_NO}"/>&INV_PRODUCT_SL_NO=<s:property value="%{PRODUCT_SL_NO}"/>"
                                                      class="linkclass"  title="Invoice Details - <s:property value="PRODUCT_DESC"/>"  rel="gb_page_center[650, 285]">
                                                       <img style="border: 0px" src="css/ShahiButtons/images/icons/silk/application_side_list.png" alt="Invoice Detail"/>
                                                  </a> 
                                                      
                                                      
                                                  </td> --%>
                                                  <s:if test="%{FORWARD_DATE==null}">
                                                <td>    <input type="checkbox" name="chkdel"  value='<s:property value="%{SL_NO}"/>'/> 
                                              </td>
                                              </s:if>
                                            </tr>
                                            </s:else>
                                            <s:set id="totalamt" value="%{#totalamt+PRODUCT_AMOUNT}"/>
                                        </s:iterator>
                                        </s:iterator>
                                            <tr style="font-size:10px;background-color: #FFFFFF;font-weight: bold">
                                                <td  <s:if test="%{FORWARD_DATE==null}">colspan="6"</s:if><s:else>colspan="5"</s:else>  align="right" height="23px">Total</td>
                                                <td align="right">
                                                    
                                                    <s:text name="product.req" >
                                                       <s:param name="value" value="%{#totalamt}"/> 
                                                    </s:text>
                                                   
                                                </td>
                                                <s:if test="%{FORWARD_DATE==null}">
                                                    <td></td>
                                                </s:if>
                                            
                                                    <td></td><td></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td></tr>
                      <s:if test="%{FORWARD_DATE==null}">
                            <tr>
                                
                                <td colspan="2">
                                    <table width="100%"><tr><td style="border-width:1pt;border-color:black;border-style:solid;" border="1" >
                                	<table  border="0" cellpadding="1"  cellspacing="0" border="1"   width="100%">
                                            <tr>
                                           
			                     <td style="background-color: #b2cecf;" class="label-1">Bill Type</td>
                                             <td style="background-color: #b2cecf;" class="label-1">Bill Sub Type</td>
                                              <td style="background-color: #b2cecf;" class="label-1">PCH</td>
                                              <td style="background-color: #b2cecf;height: 20px" class="label-1">Cost Center</td>
                                             <td style="background-color: #b2cecf;" class="label-1" >Product Group</td>
                                             <td  style="background-color: #b2cecf;padding-right: 65px" class="label-1" align="right">Amount
                                             &nbsp;Chg</td>
                                             </tr>
                                             <tr style="background-color: #FFFFFF;">
                                                                
                                                                <td valign="top" style="width:280px">
                                                                    
                                                                    <s:url id="url"  action="newtypembillentAction.action" />
                                                                    <sx:div id="details"  href="%{url}"  cssStyle="width:280px" listenTopics="show_detail" formId="formId" showLoadingText="Loading ......"></sx:div>
                                              
                                                                    
                                                                </td>
                                                                 <td valign="top" style="width:280px">
                                                                    <s:url id="url1" action="subtypembillentAction.action" />
                                                                    <sx:div id="details1"  href="%{url1}"  listenTopics="show_detail1" cssStyle="width:280px"   formId="formId" showLoadingText="Loading ......"></sx:div>
                                                                 </td>
                                                                 <td valign="top" >
                                                                 <s:select name="PCH" id="PCH" onchange="show_details2()" cssClass="selecttext" theme="simple" value="" list="pchlist"/>
                                                                 </td>
                                                                 <td valign="top" style="width:250px">
                                                                     <s:url id="url3" action="cctypembillentAction.action" />
                                                                    <sx:div id="details3"  href="%{url3}"  listenTopics="show_detail3" cssStyle="width:250px"   formId="formId" showLoadingText="Loading ......"></sx:div>
                                                                 
                                                                     <%--
                                                                <table cellpadding="0" cellspacing="1"><tr>
                                                                        <td><s:textfield name="CC_DESC" readonly="true" value="" id="CC_DESC%{#ctrlc}" maxlength="30" theme="simple" cssClass="textreadonly" size="30"/> </td> 
                                                                        <td><s:textfield name="CC_CODE" readonly="true" value=""  id="CC_CODE%{#ctrlc}" maxlength="10" theme="simple" cssClass="textreadonly" size="10"/> 
                                                                        </td><td>
                                                                            <a id="cccodeid" href="javascript:void()" class="search"  target="addapprofrm"  onclick='getdeptlist(<s:property value="%{#ctrlc}"/>);' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                                                       </td> 
                                                                    </tr></table> --%>
                                                                     
                                                                </td>
                                                                  <td colspan="2" valign="top" style="width:370px">
                                                                    <s:url id="url2" action="prodtypembillentAction.action" />
                                                                    <sx:div id="details2"  href="%{url2}" listenTopics="show_detail2" cssStyle="width:370px;height: 140px;" formId="formId" showLoadingText="Loading ......"></sx:div>

                                                                  </td>
                                                                
                                                                

                                                            </tr>
                                                            
                                                           
                                                        
                                                
                                                            <tr><td style="color:red;" colspan="2">     
                  <%--  <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />--%></td><td></td><td></td></tr>
                                                
                                                <tr></tr>                          
                                	</table>  
                                            </td>
                                        </tr><tr>
                                            
                                            <td align="center"><table><tr>  <td> 
                                    
                                      <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                                     &nbsp;
                                       <button onclick="ongonew()" class="sexybutton"><span><span><span class="edit">New</span></span></span></button>
                                     &nbsp;
                                      <button onclick="ongodelete()" class="sexybutton"><span><span><span class="cancel">Delete</span></span></span></button>
                                     
                                       <%--
                                       
                                      <a href="#" onclick="ongoclear();" class="sexybutton"><span><span><span class="reload">Clear</span></span></span></a>
                                     --%>
                                       &nbsp;

                                  <button onclick="ongosearch()" class="sexybutton"><span><span><span class="undo">Back</span></span></span></button>
                                                                        
                                  </td></tr></table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            </s:if>
                            <s:else>
                                <tr><td colspan="2">
                                        
                                        <s:if test="%{ACC_FLAG==null}">
                                           <button onclick="ongosearch()" class="sexybutton"><span><span><span class="undo">Back</span></span></span></button>
                             
                                        </s:if>
                                <s:else>
                                    <s:hidden name="ACC_FLAG" value="Yes"/>
                                    <button onclick="ongosearchac()" class="sexybutton"><span><span><span class="undo">Back</span></span></span></button>
                              
                                </s:else>
                                        
                                        </s:else>
                                    </td></tr>
                          </table>
                            
                </td>
            </tr>
          </table>
              <table><tr><td id="pchtd"></td></tr></table>
                    <s:hidden name="S_DEPT_DESC" value="%{S_DEPT_DESC}"/> 
                    <s:hidden name="S_CONTROL" value="%{S_CONTROL}"/> 
                    <s:hidden name="S_Bill_NO" value="%{S_Bill_NO}"/> 
                    <s:hidden name="S_Bill_FROM" value="%{S_Bill_FROM}"/> 
                    <s:hidden name="S_Bill_TO" value="%{S_Bill_TO}"/> 
                    <s:hidden name="S_SUPPLIER" value="%{S_SUPPLIER}"/> 
                    <s:hidden name="S_WHLO" value="%{S_WHLO}"/> 
                    <s:hidden name="S_EMP" value="%{S_EMP}"/> 
              
              
              
              
                     <s:hidden name="TOTALSAVE"  value="%{#totalamt}" id="TOTALSAVE" />
                                       
                                       
                                       
                    <div id='billbreakup' name='billbreakup' style='width: 500px; height: 450x; display:none; position: absolute; top: 100px; left:250px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
                    <table width='500px' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe" height='23'  width=500px'  style="font-size:12px;color:white;font-weight: bold" id="sdsid" align="center">Cost Element Break-Up Details</td>
                            <td><a href='javascript:billbreakupClose();'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                        </tr>
                        <tr bgcolor="#f2f2f2"><td colspan="2"   valign="top">
                                <table width="100%" cellspacing="0" cellpadding='0'>
                                    <tr>
                                        <td>
                                            <table width="100%" cellspacing="1" cellpadding='2'  bgcolor="#d0d7e5">
                                                <tr class="hd"><td style="height:25px">Sl.No</td><td>Cost Element</td><td> Form Type</td><td>Amount</td></tr>
                                                <s:set id="ctnbrk" name="ctnbrk" value="0"/>
                                                <s:set id="setbrktotal" name="setbrktotal" value="0"/>
                                                
                                                <s:iterator value="savecostelement" status="savebrkst">
                                                    <s:if test="%{#ctnbrk==0}">
                                                    <tr style="background-color: #FFFFFF">
                                                        <td class="label-1"><s:property value="%{#ctnbrk+1}"/></td>
                                                        <td>
                                                            <s:select theme="simple"  value="%{BILL_NO}"  cssStyle="width:250px;" cssClass="selecttext" listKey="%{SL_NO}" listValue="%{SUB_TYPE_CODE+'-'+TYPE_SL_NO}"   list="%{grosselemlist}" id="BREAK_CODE_MAST" name="BREAK_CODE" />
                                                            
                                                        </td>
                                                        <td>
                                                            <s:if test="%{SUPPLIER_CODE==null}">
                                                            <s:hidden name="FORM_TYPE" value=""   />
                                                            <s:select theme="simple" value=""  disabled="true"  headerKey="" headerValue="Select Form" id="FORM_TYPEGRS"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                          </s:if>
                                                            <s:else>
                                                                <s:hidden name="FORM_TYPE" value="" disabled="true"   />
                                                                <s:select theme="simple" value=""   headerKey="" headerValue="Select Form" id="FORM_TYPEGRS"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                         
                                                            </s:else>
                                                            </td>
                                                        <td>
                                                             <s:text name="product.req" id="BREAK_AMOUNTdec" >
                                                            <s:param name="value" value="%{BILL_AMOUNT}"/> 
                                                             </s:text>
                                                             <s:textfield name="BREAK_AMOUNT" readonly="true" theme="simple" value="%{#BREAK_AMOUNTdec}" onblur="validatenumber(this);totalamountbrk();document.getElementById('GROSS_AMOUNT').value=this.value" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="texts" size="10"/>
                                                        </td>
                                                    </tr>
                                                    </s:if>
                                                    <s:else>
                                                     <tr style="background-color: #FFFFFF">
                                                        <td class="label-1"><s:property value="%{#ctnbrk+1}"/></td>
                                                        <td>
                                                            <s:select theme="simple" value="%{BILL_NO}"  headerKey="" headerValue="Select Cost Element"  cssStyle="width:250px;" cssClass="selecttext" listKey="SL_NO" listValue="%{SUB_TYPE_CODE+'-'+TYPE_SL_NO}"   list="costelement" name="BREAK_CODE" />
                                                            
                                                          
                                                        </td>
                                                        <td >
                                                            <s:if test="%{SUPPLIER_CODE==null}">
                                                            <s:hidden name="FORM_TYPE"   id="FORM_TYPE%{#billitrst.index}TXT"/>
                                                            <s:select theme="simple"  disabled="true"  headerKey="" headerValue="Select Form" id="FORM_TYPE%{#billitrst.index}"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                          </s:if>
                                                            <s:else>
                                                                <s:hidden name="FORM_TYPE"  disabled="true" id="FORM_TYPE%{#billitrst.index}TXT"/>
                                                                <s:select theme="simple"  value="%{SUPPLIER_CODE}"   id="FORM_TYPE%{#billitrst.index}"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                         
                                                            </s:else>
                                                            </td>
                                                        <td>
                                                             <s:text name="product.req" id="BREAK_AMOUNTdec" >
                                                            <s:param name="value" value="%{BILL_AMOUNT}"/> 
                                                             </s:text>
                                                            <s:textfield name="BREAK_AMOUNT" theme="simple" readonly="true" value="%{#BREAK_AMOUNTdec}" onblur="validatenumber(this);totalamountbrk()" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="texts" size="10"/>
                                                        </td>
                                                    </tr>
                                                    </s:else>
                                                    <s:set id="ctnbrk" name="ctnbrk" value="%{#ctnbrk+1}"/>
                                                    <s:set id="setbrktotal" name="setbrktotal" value="%{#setbrktotal+BILL_AMOUNT}"/>
                                                </s:iterator>
                                                    
                                                    
                                                    <s:if test="%{savecostelement.size()==0}">
                                              
                                                <s:iterator begin="%{#ctnbrk}" end="6"  status="billitrst">
                                                    
                                                      <s:if test="%{savecostelement.size()==0 && #billitrst.index==0}">
                                                <tr style="background-color: #FFFFFF">
                                                        <td class="label-1"><s:property value="%{#ctnbrk+1}"/></td>
                                                        <td>
                                                            <s:select theme="simple"   cssStyle="width:250px;" cssClass="selecttext" listKey="SL_NO" listValue="%{SUB_TYPE_CODE+'-'+TYPE_SL_NO}"   list="grosselemlist" name="BREAK_CODE" />
                                                            
                                                          
                                                        </td>
                                                        <td>
                                                            <s:hidden name="FORM_TYPE" id="FORM_TYPE" value=""   />
                                                            <s:select theme="simple"  disabled="true"  headerKey="" headerValue="Select Form" id="FORM_TYPEGRS"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                          
                                                            </td>
                                                        <td>
                                                            <s:textfield name="BREAK_AMOUNT" theme="simple" value="" onblur="validatenumber(this);totalamountbrk();document.getElementById('GROSS_AMOUNT').value=this.value" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="texts" size="10"/>
                                                       
                                                        </td>
                                                    </tr>
                                                    </s:if>
                                                    <s:else>
                                                    <tr style="background-color: #FFFFFF">
                                                        <td class="label-1"><s:property value="%{#ctnbrk+1}"/></td>
                                                        <td>
                                                            <s:select theme="simple" value="" onchange="totalamountbrk();addNewListItem(this,'FORM_TYPE%{#billitrst.index}','FORM_TYPE%{#billitrst.index}TXT');" headerKey="" headerValue="Select Cost Element"  cssStyle="width:250px;" cssClass="selecttext" listKey="SL_NO" listValue="%{SUB_TYPE_CODE+'-'+TYPE_SL_NO}"   list="costelement" name="BREAK_CODE" />
                                                            
                                                          
                                                        </td>
                                                        <td >
                                                            
                                                            <s:hidden name="FORM_TYPE"  value=""  disabled="true" id="FORM_TYPE%{#billitrst.index}TXT"/>
                                                            <s:select theme="simple"  value=""   headerKey="" headerValue="Select Form" id="FORM_TYPE%{#billitrst.index}"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                          
                                                            </td>
                                                        <td>
                                                            <s:textfield name="BREAK_AMOUNT"  theme="simple" value="" onblur="validatenumber(this);totalamountbrk()" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="texts" size="10"/>
                                                        </td>
                                                    </tr>
                                                    </s:else>
                                                    <s:set id="ctnbrk" name="ctnbrk" value="%{#ctnbrk+1}"/>
                                                </s:iterator>
                                                    </s:if>  
                                                    <s:text name="product.req" id="setbrktotaldec" >
                                                            <s:param name="value" value="%{#setbrktotal}"/> 
                                                             </s:text>
                                                    <tr  style="background-color: #FFFFFF" ><td colspan="3" class="label-1" align="right">Total</td><td><s:textfield name="BRKTOTAL" theme="simple" value="%{#setbrktotaldec}"  cssStyle="font-weight:bold" id="BRKTOTAL" readonly="true"  cssClass="textreadonly" size="10"/></td></tr>
                                                   
                                                   
                                                    
                                                    <tr style="background-color: #ebdbc3"><td></td><td class="label-1" style="color:black">Reverse Service Tax <input type="checkbox" id="REVERSE_SRVTAXTEMP"  name="REVERSE_SRVTAXTEMP"  />
                                                            <s:hidden name="REVERSE_SRVTAX" id="REVERSE_SRVTAX" value=""/>
                                                        </td>
                                                        
                                                        <td align="right" class="label-1" style="color:black">Rate</td><td><s:textfield name="REVERSE_SRVTAX_RATE"  theme="simple" value="" onblur="validatenumberfour(this);"    id="REVERSE_SRVTAX_RATE" maxlength="8"  cssClass="texts" size="10"/></td>
                                                    </tr>
                                                    
                                             <tr style="background-color: #FFFFFF">  <td align="center" colspan="4"> 
                                    
                                      <button class="sexybutton" onclick="billbreakupClose();"><span><span><span class="save">Save</span></span></span></button> 
                                    </td></tr>
                                            
                                            </table>
                                            
                                        </td>

                                </tr>   
                            </table>
                        </td>
                    </tr></table>
                                                   
            </div>
                                            
                                                    <s:hidden name="TYPEFLA" id="TYPEFLA" value=""/>
                                                    
                                                    <s:iterator value="%{grosselemlist}"  status="tempstg" >
                                                           <s:hidden name="%{SL_NO}" id="%{SL_NO}TYPEFLA" value="%{SUB_TYPE_DESC}"/>
                                                   
                                                    </s:iterator>
                                                    <s:iterator value="%{costelement}"  status="tempst" >
                                                        <s:hidden name="%{SL_NO}" id="%{SL_NO}TYPEFLA" value="%{SUB_TYPE_DESC}"/>
                                                    </s:iterator>          
                         
     <%--
             <s:if test="%{DEPT_SL_NO==26}">
                                          
                        <div id='invdtidmaster' name='invdtidmaster' style='width: 600px; height: 300x; display:none; position: absolute; top: 120px; left:150px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='600px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='580px'  style="font-size:12px;color:white;font-weight: bold">Invoice Details 
                            </td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("invdtidmaster").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        <tr><td colspan="2">
                                <table  width='100%'><tr><td class="label-1" valign="top">Shipping Bill</td><td valign="top">
                                            <s:textfield name="SSHIPBILLNEWMASTER" value="%{SSHIPBILLNEWMASTER}" cssStyle="width:70px" id="SSHIPBILLNEWMASTER"  theme="simple" cssClass="texts"/>
                                          
                                     </td>
                                        <td class="label-1" valign="top">BOS</td><td>
                                            <table cellpadding="0" cellspacing="0"><tr><td>
                                            <s:textfield name="BOSNEWMASTER" value="%{BOSNEWMASTER}" id="BOSNEWMASTER"  cssStyle="width:210px"  theme="simple" cssClass="texts"/>
                                      </td></tr><tr><td style="color:red;font-size: 9px;font-weight: bold">
                                            Enter multiple BOS seperated by commas
                                                    </td></tr></table>
                                        </td>
                                        
                                        
                                        <td class="label-1" valign="top">Lcert No</td><td valign="top">
                                            <s:textfield name="lcert_recv_no" value="%{lcert_recv_no}" id="lcert_recv_no"   cssStyle="width:70px" theme="simple" cssClass="texts"/>
                                        </td>
                                        
                                        <td  valign="top"><button onclick="invrecMASTER();" class="sexybutton"><span><span><span class="search"></span></span></span></button></td>
                                    </tr>
                                    <tr>
                                        <td class="label-1" valign="top">Invoice</td><td colspan="6">
                                            <table cellpadding="0" cellspacing="0"><tr><td>
                                            <s:textfield name="SINVNEWMASTER" value="%{SINVNEWMASTER}" id="SINVNEWMASTER"   cssStyle="width:460px" theme="simple" cssClass="texts"/>
                                                    </td></tr><tr><td style="color:red;font-size: 9px;font-weight: bold">
                                            Enter multiple invoice numbers seperated by commas
                                                    </td></tr></table>
                                        </td>
                                    </tr>
                                </table>
                        </td>
                        </tr>
                        <tr><td colspan="2" valign='top' style="height: 300px">
                               
                        <s:url id="invurlmaster"  action="masterinvnewnewmbillentAction.action">
                          
                        </s:url>
                        
                        <sx:div id="invdetailsmast"  href="%{#invurlmaster}"  cssStyle="width:600px" listenTopics="invshow_detailmaster" formId="formId" showLoadingText="Loading ......"></sx:div>
                                              
                       </td>
                    </tr>
                       
                       </table>
                                                   
                     </div>
          </s:if>
          --%>
          <%--
                       <s:if test="%{DEPT_SL_NO==27}">                              
                          <div id='IMPORTSDIV' name='IMPORTSDIV' style='width: 400px; height: 300x; display:none; position: absolute; top: 120px; left:150px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='400px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='380px'  style="font-size:12px;color:white;font-weight: bold">AWB Details 
                            </td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("IMPORTSDIV").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        <tr><td colspan="2">
                                <table><tr><td class="label-1">AWB/BOE No.</td><td>
                                  <s:textfield name="SSHIPBILLNEWMASTER" value="%{SSHIPBILLNEWMASTER}" cssStyle="width:180px" id="SSHIPBILLNEWMASTER"  theme="simple" cssClass="texts"/>
                                   </td>
                                       
                                        
                              <td><button onclick="importgo();" class="sexybutton"><span><span><span class="search"></span></span></span></button></td>
                                    </tr></table>
                        </td>
                        </tr>
                        <tr><td colspan="2" valign='top' style="height: 300px">
                               
                        <s:url id="importurl"  action="importnewmbillentAction.action">
                          
                        </s:url>
                        
                        <sx:div id="importdetail"  href="%{#importurl}"  cssStyle="width400px" listenTopics="show_importdetail" formId="formId" showLoadingText="Loading ......"></sx:div>
                                              
                       </td>
                    </tr>
                       
                       </table>
                                                   
                     </div>
                       </s:if>
                        --%>                            
                       <s:if test="%{DEPT_SL_NO==29}">                              
                          <div id='COURIERDIV' name='COURIERDIV' style='width: 400px; height: 300x; display:none; position: absolute; top: 120px; left:150px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='400px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='380px'  style="font-size:12px;color:white;font-weight: bold">AWB Details 
                            </td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("COURIERDIV").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        
                        <tr><td colspan="2" valign='top' style="height: 280px">
                               
                                    <table cellpadding='0' width="100%"  cellspacing='1'>
                                        <tr class="hd" style="height:15pt">
                                     <th class="label-1">AWB No</th>
                                        </tr>
                                    <s:iterator begin="0" end="10">
                                        <tr><td>
                                         <s:textfield name="INV_NONEWMASTER" id="INV_NO" theme="simple" maxlength="30" cssClass="texts" cssStyle="width:370px;text-transform: uppercase;"  value=""/>
                                      </td></tr>
                                    </s:iterator>
                                    </tr></table>
                                
                                                 
                       </td>
                    </tr>
                    <tr><td colspan="2" >
                <table cellpadding="0" width="100%"><tr><td width="300px">
                <table>
                             <tr>
                                                <td><div style="width:25px;height:20px;background-color:#ffffce;border-width:1pt;border-color:#ffffce;border-style:solid;"></div></td><td class="label-1">Saved Entry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:#FFFFFF;border-width:1pt;border-color:#FFFFFF;border-style:solid;"></div></td><td class="label-1">New Entry</td>
                                            </tr>
                                            </table> 
                            </td>
                            <td>
                                 <button class="sexybutton" onclick='document.getElementById("COURIERDIV").style.display = "none";'><span><span><span class="save">Save</span></span></span></button> 
                        
               
                            </td>
                    </tr></table>
               
                
            </td>
        </tr>
                       
                       </table>
                                                   
                     </div>
                       </s:if>
                        
          
                <s:if test="%{DEPT_SL_NO==35}">                              
                          <div id='TESTINGDIV' name='TESTINGDIV' style='width: 500px; height: 300x; display:none; position: absolute; top: 120px; left:150px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='500px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='480px'  style="font-size:12px;color:white;font-weight: bold">Testing Details 
                            </td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("TESTINGDIV").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        
                        <tr><td colspan="2" valign='top' style="height: 280px">
                               
                                    <table cellpadding='1' width="100%"  cellspacing='1'>
                                        <tr><td>
                                           <s:textfield name="BUYERCODETEST" id="BUYERCODETEST" readonly="true" theme="simple" maxlength="10" cssClass="textreadonly" cssStyle="width:100px;text-transform: uppercase;"  value=""/>
                                           <s:textfield name="BUYERNAMETEST" id="BUYERNAMETEST" readonly="true" theme="simple"  cssClass="textreadonly" cssStyle="width:355px;text-transform: uppercase;"  value=""/>
                                           
                                           <a href="#" class="search"  onclick='document.getElementById("BUYERDIV").style.display="block";' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                            
                                          </td></tr>
                                      <tr class="hd" style="height:15pt">
                                          <th class="label-1">Report No</th>
                                      </tr>
                                        <tr>
                                        <td>
                                         <div style="width:220px;overflow: auto;height:250px;">
                                         <table id="tableaddid">
                                        <s:iterator begin="0" end="9" status="stbegin">
                                          <tr><td>
                                           <s:textfield name="INV_REPORT" id="INV_REPORT" theme="simple" maxlength="20" cssClass="texts" cssStyle="width:200px;text-transform: uppercase;"  value=""/>
                                           </td>
                                              </tr>
                                              </s:iterator>
                                          </table>
                                            </div> 
                                      </td>
                                     
                                       </tr>
                                    </table>
                                
                                                 
                       </td>
                    </tr>
                    <tr><td colspan="2" >
                <table cellpadding="0" width="100%"><tr><td width="300px">
                <table>
                             <tr>
                                                <td><div style="width:25px;height:20px;background-color:#ffffce;border-width:1pt;border-color:#ffffce;border-style:solid;"></div></td><td class="label-1">Saved Entry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:#FFFFFF;border-width:1pt;border-color:#FFFFFF;border-style:solid;"></div></td><td class="label-1">New Entry</td>
                                            </tr>
                                            </table> 
                            </td>
                            <td>
                                 <button class="sexybutton" onclick="AddRowTable('tableaddid')"><span><span><span class="add">Add Row</span></span></span></button> 
                                 &nbsp;
                                <button class="sexybutton" onclick='document.getElementById("TESTINGDIV").style.display = "none";'><span><span><span class="save">Save</span></span></span></button> 
                        
               
                            </td>
                    </tr></table>
               
                
            </td>
        </tr>
                       
                       </table>
                                                   
                     </div>
                          
                 
                          
                        <div id='BUYERDIV' name='BUYERDIV' style='width: 300px; height: 200x; display:none; position: absolute; top: 140px; left:300px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 1'>
                       <table  cellpadding='0' width='320px' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe"   width='300px'  style="font-size:12px;color:white;font-weight: bold">Buyer Details 
                            </td>
                            <td><a href='#' style="text-decoration: none" onclick='document.getElementById("BUYERDIV").style.display = "none";'><img src="css/images/divclose.gif"/></a></td>
                        </tr>
                        
                        <tr><td colspan="2" valign='top' >
                               
                                    <table cellpadding='0' width="100%"  cellspacing='1'>
                                        <tr><td> Search 
                    <input type="text" id="regexp" name="regexp" class="texts" onKeyUp="myfilter.set(this.value)"/>
                    
                                            </td>
                                    </tr>
                                        
                                        <tr><td>
                                                <s:select name="buyermyselect" theme="simple" id="buyermyselect" cssClass="texts"
                                                          list="buyerlist"
                                                          listKey="WHLO"
                                                          listValue="WHNM"
                                                          ondblclick="selectbuyer()"
                                                          size="15"
                                                          cssStyle="width:300px"
                                                          />
                                          </td></tr>
                                   
                                    </tr></table>
                                
                                                 
                       </td>
                    </tr>
                  
                       
                       </table>
                                                   
                     </div>
                          <SCRIPT TYPE="text/javascript">

var myfilter = new filterlist(document.getElementById('buyermyselect'));

</SCRIPT>
                          
                       </s:if>
                                                    
<div id='debitdetaildiv' name='debitdetaildiv' style='background-color: #f2f2f2; width: 382px; height: 180px; display:none; position: absolute; top:100px; right:148px;;border-width: 1px;border-style: solid;border-color: black;overflow: auto;z-index: 1'>
    <table width='380' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='0'>
        <tr bgcolor="#f2f2f2">
            <td bgcolor="#2a6afe" height='23' width='356' align="left"  style="font-size:12px;color:white;font-weight: bold">
                Debit Details
            </td>
            <td><a href='javascript:styledPopupCloseeditline("debitdetaildiv")'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
        </tr>
        <tr bgcolor="#f2f2f2"><td colspan='2' style='background: url("images/x11_body.gif") no-repeat top left; width: 380px; height: 100px;' valign="top">
                <table width="100%">
                    <tr><td class="label-1">Amount <s:textfield name="DEBIT_AMOUNT" maxlength="15" onblur="validatenumber(this)" cssStyle="width:200px" cssClass="texts" theme="simple" /></td> </tr>
                    <tr><td class="label-1">Remarks </td></tr>
                    <tr><td><textarea  class="texts"  name="DEBIT_REASON" style="width:370px;height:80px;overflow:auto"   ></textarea>
                        </td>
                    </tr>
                    <tr><td align='center'><button class="sexybutton" onclick='styledPopupCloseeditline("debitdetaildiv")'><span><span><span class="save">Save</span></span></span></button> </td></tr>


        </tr>   
    </table>
    </td>
    </tr></table>
</div> 
                          
                                                    
          </form>                       
                       
                       
          
                  
      </body>
</html>

      