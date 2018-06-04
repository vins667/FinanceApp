<%-- 
    Document   : newuser
    Created on : Dec 2, 2013, 1:03:09 PM
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
            if(document.getElementById('EMP_CODE').value!=""){
            document.getElementById('prepage').style.visibility='';
            document.getElementById('formId').action='mbilluserAction.action';
            document.getElementById('formId').submit();
            }else{
                alert("Please Enter Emp Code.");
                document.getElementById('EMP_CODE').focus();
            }
        }
        
         function onsave() {
          
               allselect('WAREHOUSE');
               allselect('DEPT_SL_NO');
              
          
              document.getElementById('formId').action='savembilluserAction.action';
              document.getElementById('formId').submit();
        }
        
        function ongoback() {
           
                
                 document.getElementById('formId').action='exelistmbilluserAction.action';
                 document.getElementById('formId').submit();
         
        }
        
      
        function ongonew() {
          
             document.getElementById('formId').action='exenewmbilluserAction.action';
             document.getElementById('formId').submit();
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

function allselect(item){
                		
                var toSelect_Length = document.getElementById(item).options.length;
               for(i=0; i<toSelect_Length; i++)
                   {
                       document.getElementById(item).options[i].selected=true;
                       
                   }
}


 function addselect(from, to){
                var pchInput=document.getElementById(from);		
                var toSelect_Length = document.getElementById(to).options.length;
               while(pchInput.selectedIndex > -1 )
               {
	 	  var index = pchInput.selectedIndex;

                if(pchInput.value.length==0)
                {
                    alert("You have not entered any value");
                    return;
                }
		document.getElementById(to).options[toSelect_Length] = new Option(document.getElementById(from).options[index].text);
                document.getElementById(to).options[toSelect_Length].value =document.getElementById(from).options[index].value;    
                document.getElementById(from).options[index] = null;
                toSelect_Length++;
                }                
                //onSelectPch();
            } 
              function onSelectPch(){
                  insertFaci();                 
                  insertPch();
                  document.PcdPerformanceReport.action="PcdPerformanceReport.jsp?pag=P"
                  document.PcdPerformanceReport.submit();
              }
            function removeselect(selectbox)
            {var i;
             for(i=selectbox.options.length-1;i>=0;i--)
             {if(selectbox.options[i].selected)
              selectbox.remove(i);
             }
            }

function show_details() {
         
              allselect('DEPT_SL_NO');
                dojo.event.topic.publish("show_detail");
                
            }

    </script>
    
    
   <style>
       .whitesubmiticon{
    color:#333333;
    font-size:12px;
    font-weight:bold;
    width:65pt;
    height:14pt;
    background-color: whitesmoke;
    border-color: #6699CC;
	vertical-align:middle;
	padding-top:3px;
    border-style: solid;
    border-width: 1px;
    text-align: center;
    cursor: hand;
}
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
<body style="text-align:center;margin:0px;background-color: #f2f2f2;" onload="show_details();waitPreloadPage();">
 
 <DIV align="center" id="prepage" class="lodingdiv" style="position:absolute;" >
<img src="css/image/progress.gif" >
<br>
<B >Loading ... ... Please wait ...</B>
</DIV>        
                       
    <form method="POST" id="formId" name="searchsubs" action=""> 
        <table style="background-color: #f2f2f2;width: 100%;" cellpadding="1" cellspacing="0">    
            <tr>
                <td style="width:100%;height: 25px;text-align: center" colspan="10" class="hd" > User Access Detail   </td></tr>
              <tr style="background-color: #b2cecf;height:35px;">
                  <td>
                    
                      <table><tr>
                   <s:if test="%{EMP_CODE==null}">
                <td class="label-1">Emp Code <s:textfield  name="EMP_CODE" id="EMP_CODE" theme="simple"   cssClass="texts" onblur="validatenumber(this)"  onkeypress="return tabE(this, event)" cssStyle="width:100px;text-transform:uppercase" /></td>
                 <td>
                    <button onclick="ongosearch();" class="sexybutton"><span><span><span class="search">Go</span></span></span></button>
                   </td> 
                   </s:if>
                   <s:else>
                       <td class="label-1">Emp Code <s:textfield  name="EMP_CODE" id="EMP_CODE" theme="simple" readonly="true"    cssClass="textreadonly" onblur="validatenumber(this)"  onkeypress="return tabE(this, event)" cssStyle="width:100px;text-transform:uppercase" /></td>
                 
                   </s:else>
                   
                   <td class="label-1">Name <s:textfield  name="EMP_NAME" id="EMP_NAME" theme="simple" readonly="true"  cssClass="textreadonly" onkeypress="return tabE(this, event)" cssStyle="width:350px;text-transform:uppercase" /></td>
                          </tr></table>
                          
                </td>
            </tr>
            <tr>
                <td style="width:100%;height:560px"  valign="top" >                    
                    <table>
                        <tr><td class="label-1">Available Warehouse</td><td>&nbsp;</td><td class="label-1">Assigned Warehouse</td></tr>
                        <tr><td>
                                <s:select name="warehouses"
                                          id="warehouses"   
                                          list="warehouselist"
                                          listKey="EAAITM"
                                          listValue="EATX40"
                                          size="8"
                                          theme="simple"
                                          cssClass="tests"
                                          cssStyle="width:400px"
                                          multiple="true"
                                          />
                                
                            </td>
                                
                            <td  style="width:50px" align="center">
                                    <a href="#" id="addButtonProcGrp" onclick="addselect('warehouses','WAREHOUSE');" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButtonProcGrp" onclick="removeselect(document.getElementById('WAREHOUSE'));" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                            <td>
                                 <s:select name="WAREHOUSE"
                                          id="WAREHOUSE"   
                                          list="savewarehouselist"
                                          listKey="EAAITM"
                                          listValue="EATX40"
                                          size="8"
                                          theme="simple"
                                          cssStyle="width:400px"
                                          cssClass="tests"
                                          multiple="true"
                                          />
                                
                                
                            </td></tr>
                        
                         <tr><td class="label-1">Available Department</td><td>&nbsp;</td><td class="label-1">Assigned Department</td></tr>
                        <tr><td>
                                <s:select name="deptlistinput"
                                          id="deptlistinput"   
                                          list="deptlist"
                                          listKey="EAAITM"
                                          listValue="EATX40"
                                          size="8"
                                          theme="simple"
                                          cssClass="tests"
                                          cssStyle="width:400px"
                                          multiple="true"
                                          />
                                
                            </td>
                                
                            <td  style="width:50px" align="center">
                                    <a href="#" id="addButtonProcGrp" onclick="addselect('deptlistinput','DEPT_SL_NO');show_details()" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow_right.png" width="14px" height="10px" style="border-width:0px"/></div></a><br/><br/>
                                    <a href="#"  id="removeButtonProcGrp" onclick="removeselect(document.getElementById('DEPT_SL_NO'));show_details()" style="text-decoration:none;"><div width="80px" height="20px" >&nbsp;<img src="image/arrow-left.png" width="14px" height="10px" style="border-width:0px"/></div></a>
                                </td>
                            <td>
                                 <s:select name="DEPT_SL_NO"
                                          id="DEPT_SL_NO"   
                                          list="savedeptlist"
                                          listKey="EAAITM"
                                          listValue="EATX40"
                                          size="8"
                                          theme="simple"
                                          cssStyle="width:400px"
                                          cssClass="tests"
                                          multiple="true"
                                        
                                          />
                                
                                
                            </td></tr>
                        <tr><td colspan="2" class="label-1" valign="top">
                                <p>&nbsp;</p>
                               
                                 Active Flag :  <s:select name="ACTIVE_FLAG" id="ACTIVE_FLAG" theme="simple" value="%{ACTIVE_FLAG}" cssClass="tests" list="# {'Y':'Yes','N':'No'}"/>
                                <p></p>
                                Access Flag :   <s:select name="ACCESS_FLAG" id="ACCESS_FLAG" theme="simple" cssClass="tests" value="%{ACCESS_FLAG}" list="# {'N':'No','Y':'Yes'}"/>
                            </td><td>
                                <s:url id="url"  action="newtypembilluserAction.action" />
                                <sx:div href="%{#url}" listenTopics="show_detail" formId="formId" showLoadingText="Loading ......"  cssStyle="width:100%;height:250px;"></sx:div>
                                
                            </td></tr>
                        <tr><td align="center" colspan="3">
                          <table><tr>  <td> 
                                  <s:hidden name="S_EMP" id="S_EMP" value="%{S_EMP}"/>
                                      <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                                     &nbsp;
                                       <button onclick="ongonew()" class="sexybutton"><span><span><span class="edit">New</span></span></span></button>
                                   &nbsp;
                                       <button onclick="ongoback()" class="sexybutton"><span><span><span class="edit">Back</span></span></span></button>
                                       
                                  </td></tr>
                          </table>
                </td></tr>
                    </table>   
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