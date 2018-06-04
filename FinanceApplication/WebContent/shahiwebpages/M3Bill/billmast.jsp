<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="css/style.css">  
<script src="js/fixedheader/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="js/fixedheader/table-fixed-header.css">
<script src="js/fixedheader/jquery-ui-1.7.2.custom.min.js"></script>  
<script src="js/fixedheader/jquery.chromatable.js"></script>  
        
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
        
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>  
<html>
    <head>
    	<title>Shahi Exports Pvt Ltd</title>
        <style>
           .textreadonly{
        font-family: Arial, Sans-Serif;
        font-size: 9px;
        border: solid 1px #677788;
        background-color:#e6e6e6;
        text-transform: uppercase;
    }
.texts{
        font-family: Arial, Sans-Serif;
        font-size: 9px;
        border: solid 1px #677788;
        text-transform: uppercase;
    }
    
    .selecttext{
        font-family: Arial, Sans-Serif;
        font-size: 9px;
        border: solid 1px #677788;
        text-transform: uppercase;
    }
    
    
        </style>
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
	        function showdetails(){
               
	        	var d = new Date();
                       
	        	if(document.getElementById('MAST_SL_NO') &&  $("#MAST_SL_NO").val() != ""){
	                $('#itemdt').load('newtypembillAction.action?MAST_SL_NO='+$("#MAST_SL_NO").val()+'&did=' + d.getTime());
	        	 
                          }
	        	else{
                            
	    		$('#itemdt').load('newtypembillAction.action?did=' + d.getTime());
	    		//?FITT_NO='+document.getElementById("FITT_NO").value+'&
	        	}
	    	}
                
                   function showdetailssub(a){
                     
	        	var d = new Date();
                        if(document.getElementById('MAST_SL_NO') &&  $("#MAST_SL_NO").val() != ""){
	                $('#itemdt').load('newtypembillAction.action?MAST_SL_NO='+$("#MAST_SL_NO").val()+'&savesubflag='+a+'&did=' + d.getTime());
	        	 
                          }
                       // $('#itemdt').load('newtypembillAction.action?MAST_SL_NO='+a+'&did=' + d.getTime());
	        	 
                       
	    	}
                
                
	        function showdetailslist(a,b){	  
	        	   
	        		//$("#frameID").attr("src", "");
                                $("#subtypehead").html("( "+b+" )");
	    			$('#itemdts').attr("src",'subtypembillAction.action?TYPESLNO='+a);
	    			        	
	    	}
                
                
                 function showdetailslistbyprod(a,b){	  
	        	   //alert(a);
	        		//$("#frameID").attr("src", "");
                               // $("#subtypehead").html("( "+b+" )");
	    			$('#itemdts').attr("src",'subtypembillAction.action?TYPESLNO='+a+"&saveprodflag="+b);
	    			        	
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
            function ongosearch(){
            	
            	if($("#FITT_NO").val() != ""){
            		$('#formId').attr('action', 'BuyerClaimsListAction.action');
            		$("#formId").submit();            		
            	}
            }
            function onsave(){   
            	if(validate()==true){
	            	if(confirm('Do you want to Save?')){
	            		$('#formId').attr('action', 'savemstmbillAction.action');
	            		$("#formId").submit();
	            	}            	
            	}
            }
            
             function oncopyrec(){
            
            	if(validate()==true && validatecopy()==true){
	            	if(confirm('Do you want to Copy?')){
	            		$('#formId').attr('action', 'copymstmbillAction.action');
	            		$("#formId").submit();
	            	}            	
            	}
            }
            
            function validatecopy(){
            	
            
            if($('#COPY_DEPT_CODE').val()==''){
            		alert('Copy Department  cannot be blank');
            		$('#COPY_DEPT_CODE').focus();
            		return false;
            	}
            return true;
    }
            
            function validate(){
            	if($('#DEPT_CODE').val()==''){
            		alert('Department  cannot be blank');
            		$('#DEPT_CODE').focus();
            		return false;
            	}
               /*
        if(document.getElementById('MAST_SL_NO'))
             {   
              var UP_TYPE_DESC=document.frmname.UP_TYPE_DESC;
              
              var UP_TYPE_CODE=document.frmname.UP_TYPE_CODE;
              if(UP_TYPE_DESC.length){
              for(i=0; i<UP_TYPE_DESC.length; i++)
                  {
                   if(UP_TYPE_DESC[i].value!="")   
                       {
                        if(UP_TYPE_CODE[i].value=="" || (UP_TYPE_CODE[i].value!="" && (UP_TYPE_CODE[i].value).length==0))
                            {
                               alert("Please Enter Code") ;
                               UP_TYPE_CODE[i].focus();
                               return false;
                            }
                       
                       }
                      
                  }
              }
             }
                
              var TYPE_DESC=document.frmname.TYPE_DESC;
              var TYPE_CODE=document.frmname.TYPE_CODE;
              for(i=0; i<TYPE_DESC.length; i++)
                  {
                   if(TYPE_DESC[i].value!="")   
                       {
                        if(TYPE_CODE[i].value=="" || (TYPE_CODE[i].value!="" && (TYPE_CODE[i].value).length==0))
                            {
                               alert("Please Enter Code") ;
                               TYPE_CODE[i].focus();
                               return false;
                            }
                       
                       }
                      
                  }
                */
            
            	return true;
            }
            function ongoclear(){
            	$('#formId').attr('action', 'newmstmbillAction.action');
        		$("#formId").submit();
            }
            function ongoback(){
            	$('#formId').attr('action', 'mbillAction.action');
                $("#formId").submit();
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
                                +'<td><s:textfield name="TYPE_CODE" maxlength="10" id="TYPE_CODE'+id1+'" value=""  theme="simple" cssClass="texts" size="15"/>'
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
                
                $(document).ready(function() { 
                window.history.pushState("","", location.href); 
            });

         </script>         
      </head>
      <body class="body1" onLoad="showdetails();waitPreloadPage();" style="text-align:center;margin: 0px;padding: 0px">
          <div id='approveraddid' name='approveraddid' style='width: 420px; height: 420px; display:none; position: absolute; top: 100px; left:50px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
                    <table width='420px' cellpadding='0' bgcolor="#f2f2f2" cellspacing='1' border='1'>
                        <tr bgcolor="#f2f2f2">
                            <td bgcolor="#2a6afe" height='23'  width=400px'  style="font-size:12px;color:white;font-weight: bold" id="headid" align="center"></td>
                            <td><a href='javascript:approveraddidClose();'><img height='23' width='24' src='css/images/divclose.gif' border='0'/></a></td>
                        </tr>
                        <tr bgcolor="#f2f2f2"><td colspan="2"   valign="top">
                                <table width="100%" cellspacing="0" cellpadding='0'>
                                    <tr>
                                        <td>
                                            <iframe name="addapprofrm" id="addapprofrm" scrolling="no" frameborder="0" width="419px" height="410px"></iframe>
                                            
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
                                  <td>M4 Bill Master</td>
                                  <td style="text-align: right;height:30px;" colspan="2"> 
                                      <s:if test="%{COPYFLAG=='Yes'}">
                                         <button class="sexybutton" onclick="oncopyrec();"><span><span><span class="copy">Copy</span></span></span></button>                                      
                                      
                                      </s:if>
                                      
                                      <s:if test="%{MAST_SL_NO==null}">
                                      <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button>                                      
                                     </s:if>
                                      
                                       <a href="#" onclick="window.location.href='billmast.jsp'" class="sexybutton"><span><span><span class="edit">New</span></span></span></a>
                                       <%--
                                      <a href="#" onclick="ongoclear();" class="sexybutton"><span><span><span class="reload">Clear</span></span></span></a>
                                       --%>
                                      <a href="#" onclick="ongoback();" class="sexybutton"><span><span><span class="undo">Back</span></span></span></a>                                                                        
                                  </td>
                              </tr>
                             
                            <tr>
                                <td align="left" class="label-1"> Department
                                    <s:if test="%{MAST_SL_NO!=null && MAST_SL_NO.length()>0}">
                                        <s:hidden name="MAST_SL_NO" id="MAST_SL_NO" value="%{MAST_SL_NO}"/>
                                    </s:if>
                                    <s:hidden name="DEPT_CODE" value="%{DEPT_CODE}"  id="DEPT_CODE"  />
                                    <s:textfield name="DEPT_DESC" theme="simple" id="DEPT_DESC" readonly="true" cssClass="textreadonly" size="70"/>
                                    <s:if test="%{COPYFLAG=='Yes'}">
                                    </s:if><s:else>
                                    <a href="searchpagembillAction.action?SEARCH_TYPE=3" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Department")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                    </s:else>
                                    </td>
                                <td  align="left" class="label-1">
                                    <s:if test="%{COPYFLAG=='Yes'}">
                                     Copy Department
                                     <s:hidden name="COPY_DEPT_CODE" value="%{COPY_DEPT_CODE}"  id="COPY_DEPT_CODE"  />
                                     <s:textfield name="COPY_DEPT_DESC" theme="simple" id="COPY_DEPT_DESC" readonly="true" cssClass="textreadonly" size="70"/>
                                     <a href="searchpagembillAction.action?SEARCH_TYPE=10" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Department")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                     <s:hidden name="COPYFLAG" value="%{COPYFLAG}"/>
                                    </s:if>
                                    
                                </td>
                                <td align="left" class="label-1">
                                    Flag
                                    <s:select name="MASTFLAG" list="# {'Y':'Active','N':'In Active'}" theme="simple" cssClass="selecttext"	id="FLAG"/>
                                </td>
                                
                            </tr>
                            <tr>
                                <td colspan="3">
                                	<table style="width:100%;height:450px;" cellpadding="3"  cellspacing="0" >
                                            <tr  style="height: 20px">
			                     <td style="background-color: #b2cecf;" class="label-1">
                                                 
				              Bill Type
                                             </td>
                                             <td style="background-color: #b2cecf;">
                                                   <table><tr><td class="label-1">
				                         Bill Sub Type
                                                         </td>
                                                         <td id="subtypehead" class="label-1" style="color:white"></td>
                                                     </tr></table>
				              
                                             </td>
                                             <td style="background-color: #b2cecf;">
                                                 <table><tr><td class="label-1">
				                        Product Group
                                                         </td>
                                                         <td id="producthead" class="label-1" style="color:white"></td>
                                                     </tr></table>
				              
                                             </td>
		                             </tr>
                                		<tr>
                                			<td style="width:33%;border-style: solid;border-width: 1px;border-color:#7B96DE;">
                                			<div id="itemdt" style="width:100%;height: 450px;margin: 0px;padding: 0px">
                                        
                                    			</div>	
                                                            
                                			</td>
                                			<td style="width:33%;border-style: solid;border-width: 1px;border-color:#7B96DE;" valign='top'>
                                                            <iframe id="itemdts" src=""  frameborder="0" style="width:100%;height: 455px;overflow: none;"  scrolling='no'></iframe>
                                			</td>
                                                        <td style="width:33%;border-style: solid;border-width: 1px;border-color:#7B96DE;" valign='top'>
                                                            <iframe id="itemdts1"  name="itemdts1" src="" frameborder="0" style="width:100%;height: 455px;overflow: none;" scrolling="no"></iframe>
                                                                                                       
                                                        </td>
                                                        
                                		</tr>
                                                <tr><td style="color:red;">     
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage /></td><td></td><td></td></tr>
                                	</table>                                    
                                </td>
                            </tr>
                          </table>
                            
                </td>
            </tr>
          </table>
                                         <s:token name="token"></s:token>
                          </form>                       
                       
                       
          
                  
      </body>
</html>

      