<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    
   <head>	
       <s:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />         
    <title>SEPL</title>
<link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>
<script src="js/fixedheader/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="js/fixedheader/table-fixed-header.css">
<script src="js/fixedheader/jquery-ui-1.7.2.custom.min.js"></script> 
<script src="js/fixedheader/jquery.chromatable.js"></script>
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
<script>

$(document).ready(function(){
    // make the header fixed on scroll
    $("#subyupetable").chromatable({
        width: "100%", // specify 100%, auto, or a fixed pixel amount
        height: "420px",
        scrolling: "yes" // must have the jquery-1.3.2.min.js script installed to use
    });
});

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


function addrow(){  
        		id=document.getElementById("rwcthid").value;        		
        		id1=eval(id)+1;       
        		document.getElementById("rwcthid").value=id1;
                        
        		$('#subyupetable > tbody:last').append('<tr style="background-color: #FFFFFF;"><td>'
	        		+'<s:textfield name="TYPE_DESC" maxlength="30" value="" theme="simple" cssClass="texts" size="40"/>'
	        		+'</td>'
                                +'<td><s:textfield name="TYPE_CODE" maxlength="10" id="TYPE_CODE'+id1+'" value=""  theme="simple" cssClass="textreadonly" size="5"/>'
                                +'</td>'
                        +'<td><a href="searchpagembillAction.action?SEARCH_TYPE=1&TXTID=TYPE_CODE'+id1+'" class="search"  target="addapprofrm"  onclick=\'document.getElementById("approveraddid").style.display="block";addhead("GL Code")\' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>'
                                     +'</td>'
	        		+'<td>'
					+'<select name="TYPEFLAG" id="TYPEFLAG" class="selecttext">'
					+'<option value="Y">Active</option>'
					+'<option value="N">In Active</option>'
					+'</select>'
					+'</td>'
                                 
                              +'<td></td></tr>');
                                
                          
        	}
                
                function onsave(){   
            	if(validate()==true){
	            	if(confirm('Do you want to Save?')){
	            		$('#frmsubid').attr('action', 'savesubmbillAction.action');
	            		$("#frmsubid").submit();
	            	}            	
            	}
            }
            function validate(){
            	if($('#TYPESLNO').val()==''){
            		alert('Sub Type  cannot be blank');
            		$('#TYPESLNO').focus();
            		return false;
            	}
                /*
                 if(document.getElementById('SL_NO0'))
             {   
              var UP_TYPE_DESC=document.frmsub.UP_TYPE_DESC;
              
              var UP_TYPE_CODE=document.frmsub.UP_TYPE_CODE;
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
                
              var TYPE_DESC=document.frmsub.TYPE_DESC;
              var TYPE_CODE=document.frmsub.TYPE_CODE;
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
            
              function approveraddidClose() {
    document.getElementById("approveraddid").style.display = "none";
}
 function showdetailslist(a,b,c){	  
	        	
	        		
                               id=window.parent.document.getElementById('producthead');
                                id.innerHTML="( "+b +" )";
                                window.parent.frames['itemdts1'].location='prodtypembillAction.action?SUBTYPESLNO='+a+"&TYPESLNO="+c;
 }

function showdetailslistdef(){	

	       if(document.getElementById('SL_NO0'))
               {                    <s:if test="%{saveprodflag==null}">
	        		id=window.parent.document.getElementById('producthead');
                                id.innerHTML="( "+document.getElementById('SUB_TYPE_DESC0').value +" )";
                                window.parent.frames['itemdts1'].location='prodtypembillAction.action?SUBTYPESLNO='+document.getElementById('SL_NO0').value+"&TYPESLNO="+document.getElementById('TYPESLNO').value;
                             </s:if>
                                }else{
                                   
                                id=window.parent.document.getElementById('producthead');
                                id.innerHTML="(  )";
	    			 window.parent.frames['itemdts1'].location='prodtypembillAction.action?SUBTYPESLNO=0&TYPESLNO='+document.getElementById('TYPESLNO').value;
                 
        
        }
                                <s:if test="%{savesubflag!=null}">
                                       
                                        window.parent.showdetailssub('<s:property value="%{savesubflag}"/>');
                                </s:if>
      }


function deletesubstratdt(a,b,c){
        		if(confirm('Do you want to Delete Bill Sub Type '+c+'?')){
        			var url = "savesubmbillAction?delslno="+a+"&TYPESLNO="+b;
        			$(location).attr('href',url);
        			//window.location.href();        			        			
        		}
        	}
</script>
</head>
<body onload="showdetailslistdef()" style="margin: 0px;padding: 0px;background-color: #f2f2f2" >
    <s:form name="frmsub" id="frmsubid" method="POST" action="">
	<table style="width: 100%;">
		<tr>
                    <td style="width: 100%" colspan="2">
                        <s:hidden name="TYPESLNO" value="%{TYPESLNO}" id="TYPESLNO"  theme="simple" cssClass="texts" />
			<table id="subyupetable" class="table table-bordered table-striped table-fixed-header">
				<thead>
					<tr>
						<th style="text-align: left;" class="label-1">Description</th>	
                                                <th style="text-align: left;" class="label-1">Code</th>
                                                <th>&nbsp;</th>
						<th style="text-align: left;width:80px;" class="label-1">Flag</th>		
						<th style="text-align: left;" class="label-1">Action</th>
									
					</tr>
				</thead>
				<tbody>
                                    <s:set id="ctrlc" value="0"/>
                                    <s:iterator value="subtypelist" status="st">
                                       <tr style="background-color: #FFFFFF;">
                                        <td >
                                            
                                            <s:hidden name="SL_NO" value="%{SL_NO}" id="SL_NO%{#ctrlc}"  theme="simple" cssClass="texts" />
                                            <s:textfield name="UP_TYPE_DESC" maxlength="30"  id="SUB_TYPE_DESC%{#ctrlc}"  value="%{SUB_TYPE_DESC}" theme="simple" cssClass="texts" size="40"/> </td> 
                                        <td>
                                            
                                            <s:textfield name="UP_TYPE_CODE" readonly="true" title="%{DESC}" value="%{SUB_TYPE_CODE}" id="TYPE_CODE%{#ctrlc}" maxlength="10" theme="simple" cssClass="textreadonly" size="5"/> 
                                        </td><td>    
                                            <a href="searchpagembillAction.action?SEARCH_TYPE=1&TXTID=TYPE_CODE<s:property value="%{#ctrlc}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("PCH")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        </td>      
                                        <td> <s:select name="UP_TYPEFLAG" list="# {'Y':'Active','N':'In Active'}" value="%{FLAG}" theme="simple" cssClass="selecttext" id="TYPEFLAG"/></td>
                                        <td style="width:80px;" class="texts">
                                           
						&nbsp;&nbsp;<a href="#" onclick="showdetailslist('<s:property value="%{SL_NO}"/>','<s:property value="%{SUB_TYPE_DESC}"/>','<s:property value="%{TYPESLNO}"/>')"><img src="css/ShahiButtons/images/icons/silk/application_view_list.png" alt="Detail" title="View Detail" style="border-width: 0px;"/></a>
                                                &nbsp; &nbsp;<s:if test="%{DELFLAG=='EXIST'}"><img src="css/ShahiButtons/images/icons/silk/lock_delete.png" alt="Detail" title="Delete Lock" style="border-width: 0px;"/></s:if><s:else>
                                                    <a href="#" onclick="deletesubstratdt('<s:property value="%{SL_NO}"/>','<s:property value="%{TYPESLNO}"/>','<s:property value="%{SUB_TYPE_CODE}"/>')"><img src="css/ShahiButtons/images/icons/silk/delete.png" alt="Detail" title="Delete" style="border-width: 0px;"/></a>
                                                    </s:else>
						</td>
                                    </tr>
                                    <s:set id="ctrlc" value="%{#ctrlc+1}"/> 
                                    </s:iterator>
                                    
                                    <s:iterator begin="%{ctrlc}" end="10">
                                                                     
                                    <tr style="background-color: #FFFFFF;">
                                        <td><s:textfield name="TYPE_DESC" maxlength="30"  tooltip="sdfsfsdf" theme="simple" value="" cssClass="texts" size="40"/> </td> 
                                        <td>
                                            
                                            <s:textfield name="TYPE_CODE" readonly="true" id="TYPE_CODE%{#ctrlc}" value="" maxlength="10" theme="simple" cssClass="textreadonly" size="5"/> 
                                        </td><td>    
                                            <a href="searchpagembillAction.action?SEARCH_TYPE=1&TXTID=TYPE_CODE<s:property value="%{#ctrlc}"/>" class="search"  target="addapprofrm"  onclick='document.getElementById("approveraddid").style.display="block";addhead("PCH")' ><img style="border: 0px" src="css/ShahiButtons/images/icons/silk/magnifier.png"/></a>
                                        </td>      
                                        <td> <s:select name="TYPEFLAG" list="# {'Y':'Active','N':'In Active'}" theme="simple" cssClass="selecttext" id="TYPEFLAG"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <s:set id="ctrlc" value="%{#ctrlc+1}"/>
                                    </s:iterator>
				</tbody>
			</table>
			</td>
                </tr><tr>
                    <td>
                        <s:if test="%{TYPESLNO!=null}">
                       <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button>  
                        </s:if>
                        </td>
			<td style="vertical-align: bottom" align="right">
				<a href="# " class="sexybutton" onclick="addrow();"><span><span><span class="add">Add Row</span></span></span></a>
                                
                                <s:hidden name="rwcthid" id="rwcthid" value="%{#ctrlc}"/>
			</td>		
		</tr>
	</table>
      </s:form> 
                        
                         <div id='approveraddid' name='approveraddid' style='width: 420px; height: 420px; display:none; position: absolute; top: 10px; left:10px;border-width: 1px;border-style: solid;border-color: black;background-color: #f2f2f2;z-index: 5'>
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
</body>
</html>


