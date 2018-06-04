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
	            		$('#formId').attr('action', 'savemccentAction.action');
	            		$("#formId").submit();
	            	}            	
            	}
            }
            function validate(){
            	if($('#DEPT_CODE').val()==''){
            		alert('Department  cannot be blank');
            		$('#DEPT_CODE').focus();
            		return false;
            	}
              var flag=0;
               var UP_TYPE_CODE =document.frmname.UP_TYPE_CODE;
               if(UP_TYPE_CODE)
                   {
                     flag=1;  
                   }
              var TYPE_CODE =document.frmname.TYPE_CODE;
               if(flag==0 && TYPE_CODE[0].value=="")
                   {
                    alert("Please Enter Cost Center Detail") ;
                    return false;
                   }
                   
                  BREAK_CODE= document.frmname.TYPE_CODE
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
        		$('#subyupetable > tbody:last').append('<tr style="background-color: #FFFFFF;"><td>'
	        		+'<s:textfield name="TYPE_CODE" maxlength="10" id="TYPE_CODE'+id1+'" value="" readonly="true"  theme="simple" cssClass="textreadonly" size="30"/>'
	        		+'</td>'
                               +'<td style="padding-top:8px"><a href="searchpagemccentAction.action?SEARCH_TYPE=5&TXTID='+id1+'&DEPT_CODE=<s:property value="%{DEPT_CODE}"/>" class="search"  target="addapprofrm"  onclick=\'document.getElementById("approveraddid").style.display="block";addhead("Bill Type")\' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>'
                                     +'</td>'
                                +'<td><s:textfield name="TYPE_DESC" maxlength="30" value=""  readonly="true" id="TYPE_DESC'+id1+'" theme="simple" cssClass="textreadonly" size="50"/>'
                                +'</td>'
                       
	        		//+'<td>'
					//+'<select name="TYPEFLAG" id="TYPEFLAG" class="selecttext">'
					///+'<option value="Y">Active</option>'
					//+'<option value="N">In Active</option>'
					//+'</select>'
					//+'</td>'
                                 
                              +'<td></td></tr>');
                                
                          
        	}
        	
        	function deletesubstratdt(a,b){
        		if(confirm('Do you want to Delete')){
        			var url = "savemccentAction.action?delslno="+a+"&DEPT_CODE="+b;
        			$(location).attr('href',url);
        			//window.location.href();        			        			
        		}
        	}
        	
        	
      
    
    
     function copyonload(a) 
       {
           
         window.location.href='newmstmccentAction.action?DEPT_CODE='+a;
       }
               
$(document).ready(function(){
    // make the header fixed on scroll
    $("#subyupetable").chromatable({
        width: "100%", // specify 100%, auto, or a fixed pixel amount
        height: "456px",
        scrolling: "yes" // must have the jquery-1.3.2.min.js script installed to use
    });
});
         </script>         
      </head>
      <body class="body1" onLoad="" style="text-align:center;margin: 0px;padding: 0px">
          <div id='approveraddid' name='approveraddid' style='width: 420px; height: 420px; display:none; position: absolute; top: 90px; left:350px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
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
               <table style="background-color: #f2f2f2;" width="100%" cellpadding="0" cellspacing="0">            	
              <tr>
                  <td>
                      <table  border="0" cellpadding="3" cellspacing="0" width="100%">
                         
                              <tr style="background-color: #b2cecf;"> 
                                  <td aling="center">Cost Center Link</td>
                                  <td style="text-align: right;height:30px;"> 
                                    
                                       <a href="#" onclick="window.location.href='ccentrymast.jsp'" class="sexybutton"><span><span><span class="edit">New</span></span></span></a>
                                      <a href="#" onclick="window.location.href='costcenterquery.jsp'" class="sexybutton"><span><span><span class="undo">Back</span></span></span></a>                                                                        
                                  </td>
                              </tr>
                             
                            <tr>
                                <td align="left" class="label-1" colspan="2">
                                    <table cellpadding="0" cellspacing="0"><tr><td class="label-1" >
                                    Department&nbsp;
                                            </td><td>
                                              <s:hidden name="DEPT_CODE"  id="DEPT_CODE" />
                                              <s:textfield name="DEPT_DESC" theme="simple" id="DEPT_DESC" value="%{DEPT_DESC}" readonly="true" cssClass="textreadonly" size="70"/>
                                            </td>
                                        <td style="width:25px;padding-top: 7px" align="center">
                                    <s:if test="%{DEPT_CODE==null}">
                                    <a href="searchpagemccentAction.action?SEARCH_TYPE=4" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Department")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                   </s:if>
                                   <s:else>
                                   &nbsp;&nbsp;&nbsp;&nbsp;
                                   </s:else>
                                            </td>
                                            
                                        
                                        </tr>
                                    </table>
                                </td>
                                
                                
                            </tr>
                           
                            <tr>
                                <td colspan="2">
                                	<table  cellpadding="3"  cellspacing="0" >
                                            <tr><td colspan="2" class="label-1">Bill Type</td><td class="label-1">Cost Center</td></tr>
                                		<tr>
                                                    <td colspan="2" style="border-style: solid;border-width: 1px;border-color:#7B96DE;" valign="top">
                                                        <table cellspacing="1" cellpadding="1"><tr><td>
                                			<table id="subyupetable" class="table table-bordered table-striped table-fixed-header">
				<thead>
					<tr>
							
                                                <th style="text-align: left;" class="label-1">Code</th>
                                                 <th>&nbsp;</th>
                                                <th style="text-align: left;" class="label-1">Description</th>
                                              <%-- <th style="text-align: left;width:80px;" class="label-1">Flag</th>	
--%>
						<th style="text-align: left;" class="label-1">Action</th>
									
					</tr>
				</thead>
				<tbody>
                                    <s:set id="ctrlc" value="0"/>
                                    <s:iterator value="subtypelist" status="st">
                                       <tr style="background-color: #FFFFFF;">
                                         
                                           <td>
                                               <s:if test="%{#st.index==0}">
                                                   <s:set id="tempcode" name="tempcode" value="%{EAAITM}"/>
                                               </s:if>
                                            
                                            <s:textfield name="UP_TYPE_CODE" readonly="true"  value="%{EAAITM}" id="TYPE_CODE%{#ctrlc}" maxlength="10" theme="simple" cssClass="textreadonly" size="30"/> 
                                        </td><td style="padding-top:8px">    
                                          </td>  
                                         <td>
                                            
                                            <s:hidden name="SL_NO" value="%{SL_NO}" id="SL_NO%{#ctrlc}"  theme="simple" cssClass="texts" />
                                            <s:textfield name="UP_TYPE_DESC" maxlength="30"  id="TYPE_DESC%{#ctrlc}"  value="%{EATX40}" readonly="true"  theme="simple" cssClass="textreadonly" size="50"/> 
                                         </td> 
                                        <%--
                                        <td> <s:select name="UP_TYPEFLAG" list="# {'Y':'Active','N':'In Active'}" value="%{DESC}" theme="simple" cssClass="selecttext" id="TYPEFLAG"/></td>
                                         --%>
                                        
                                        <td style="width:80px;" class="texts">
                                            &nbsp;&nbsp;<a href="#" onclick="document.getElementById('invoicefrm').src='newmstccmccentAction.action?DEPT_CODE=<s:property value="%{DEPT_CODE}"/>&TYPE_SL_NO=<s:property value="%{EAAITM}"/>'"><img src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail" title="View Detail" style="border-width: 0px;"/></a>
                                            &nbsp;
                                            <a href="#" onclick="document.getElementById('invoicefrm').src='newmstccmccentAction.action?DEPT_CODE=<s:property value="%{DEPT_CODE}"/>&TYPE_SL_NO=<s:property value="%{EAAITM}"/>&COPYFLAG=Yes'"><img src="css/ShahiButtons/images/icons/silk/page_copy.png" alt="Copy" title="Copy" style="border-width: 0px;"/></a>
                                           
                                            <%--  &nbsp; &nbsp;
                                            
                                            <s:if test="%{DELFLAG=='EXIST'}">
                                                <img src="css/ShahiButtons/images/icons/silk/lock_delete.png" alt="Detail" title="Delete Lock" style="border-width: 0px;"/></s:if>
                                            <s:else>
                                                    <a href="#" onclick="deletesubstratdt('<s:property value="%{SL_NO}"/>','<s:property value="%{DEPT_CODE}"/>')"><img src="css/ShahiButtons/images/icons/silk/delete.png" alt="Detail" title="Delete" style="border-width: 0px;"/></a>
                                                    </s:else> --%>
						</td>
                                    </tr>
                                    <s:set id="ctrlc" value="%{#ctrlc+1}"/> 
                                    </s:iterator>
                                    
                                    <s:iterator begin="%{ctrlc}" end="10">
                                                                     
                                    <tr style="background-color: #FFFFFF;">
                                       <td>
                                            
                                            <s:textfield name="TYPE_CODE" readonly="true" id="TYPE_CODE%{#ctrlc}" value="" maxlength="10" theme="simple" cssClass="textreadonly" size="30"/> 
                                        </td><td style="padding-top:8px">    
                                            <a href="searchpagemccentAction.action?SEARCH_TYPE=5&TXTID=<s:property value="%{#ctrlc}"/>&DEPT_CODE=<s:property value="%{DEPT_CODE}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("Bill Type")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        </td> 
                                        <td><s:textfield name="TYPE_DESC" maxlength="30"  id="TYPE_DESC%{#ctrlc}" readonly="true" theme="simple" value="" cssClass="textreadonly" size="50"/> 
                                        </td> 
                                        <%--
                                        <td> <s:select name="TYPEFLAG" list="# {'Y':'Active','N':'In Active'}" theme="simple" cssClass="selecttext" id="TYPEFLAG"/></td>
                                        --%>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <s:set id="ctrlc" value="%{#ctrlc+1}"/>
                                    </s:iterator>
				</tbody>
			</table>
                                                                </td></tr><td align="right">
				<a href="# " class="sexybutton" onclick="addrow();"><span><span><span class="add">Add Row</span></span></span></a>
                                
                                <s:hidden name="rwcthid" id="rwcthid" value="%{#ctrlc}"/>
                                                            </td></tr></table>
			</td>
                        <td valign="top" style="border-style: solid;border-width: 1px;border-color:#7B96DE;">
                              <table width="100%" cellspacing="0" cellpadding='0'>
                                    <tr>
                                        <td>
                                            <s:if test="%{#tempcode>0}">
                                                 <iframe name="invoicefrm" src='newmstccmccentAction.action?DEPT_CODE=<s:property value="%{DEPT_CODE}"/>&TYPE_SL_NO=<s:property value="%{#tempcode}"/>' id="invoicefrm" scrolling="no" frameborder="0" style="margin: 0px;padding: 0px" width="600px" height="510px"></iframe>
                                          
                                            </s:if>
                                            <s:else>
                                            <iframe name="invoicefrm" src="" id="invoicefrm" scrolling="no" frameborder="0" style="margin: 0px;padding: 0px" width="600px" height="510px"></iframe>
                                            </s:else>
                                        </td>

                                </tr>   
                            </table>
                            
                        </td>
                </tr><tr>
                    <td>
                       
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

      