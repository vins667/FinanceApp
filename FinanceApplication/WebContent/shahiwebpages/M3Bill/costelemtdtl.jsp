<%-- 
    Document   : costelemtdtl
    Created on : Sep 2, 2013, 7:07:26 PM
    Author     : RANJEET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link rel="stylesheet" href="css/style.css">  
     
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>  
<html>
    <head>
           <s:head />
          <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sepl</title>
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
        text-transform: uppercase;
        
    }
    
  
    .selecttext{
        font-family: Arial, Sans-Serif;
        font-size: 10px;
        border: solid 1px #677788;
        text-transform: uppercase;
    }
    </style>
    <script language="javascript"> 
      
            
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


function onsave(){   
            	if(validate()==true){
	            	if(confirm('Do you want to Save?')){
	            	document.getElementById('formId').action='saveelembillentAction.action?REFFLG=Yes';
	            	document.getElementById('formId').submit();
	            	}            	
            	}
            }


function validate(){
               var BREAK_CODE=document.frmname.BREAK_CODE;
                var BREAK_AMOUNT=document.frmname.BREAK_AMOUNT;
               
                
                
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
                             document.getElementById('FORM_TYPE'+i).focus();
                             return false;
                          }
                         } 
                     
                         if(BREAK_AMOUNT[i].value=="" || BREAK_AMOUNT[i].value==0)  
                          {
                             //alert("Cost Element Amount cannot be blank/0");
                             //BREAK_AMOUNT[i].focus();
                            // return false;
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
                  
                  var tempentgrossamt=document.getElementById('tempentgrossamt').value;
                  var entgrossamt=BREAK_AMOUNT[0].value;
                  if(eval(entgrossamt) < eval(tempentgrossamt))
                {
                  alert("Gross Amount can not be < " +tempentgrossamt);
                  //BREAK_AMOUNT[0].value=tempentgrossamt;
                  return false;
                }
                
                   if(eval(entgrossamt) < eval(document.getElementById('NON_SRVTAX_AMOUNT').value))
                {
                  alert("Non Service Tax Amount  can not be > Gross Amount ");
                          
                            return false;
                 
                }
                  
                   var BILL_AMOUNT=document.getElementById('tempbillamount').value;
                    var BRKTOTAL=document.getElementById('BRKTOTAL').value;
                  if(eval(BILL_AMOUNT)== eval(BRKTOTAL))
                {
                 
                }else{
                  alert("Total Amount should be equal to Bill Amount "+BILL_AMOUNT);
                  return false;  
                }
                
                if(document.getElementById('REVERSE_SRVTAX').value==1)
                    {
                        if(document.getElementById('REVERSE_SRVTAX_RATE').value=="")
                            {
                               alert("Rate cannot be blank");
                              
                              return false;
                            }else{
                                
                                if(document.getElementById('REVERSE_SRVTAX_RATE').value>100)
                                    {
                                       alert("Rate should be <= 100"); 
                                       document.getElementById('REVERSE_SRVTAX_RATE').focus();
                                       
                                       return false;
                                    }
                                    
                                     if(document.getElementById('SRVTAX_GL_CODE').value=="" && document.getElementById('REVERSE_SRVTAX_RATE').value>0)
                                        {
                                            alert("Please Select GL Code"); 
                                            document.getElementById('SRVTAX_GL_CODE').focus();
                                           
                                            return false;  
                                            
                                        }
                            }
                    }
                
                
                return true;
}
         </script> 
          <script language="javascript" type="text/javascript">
             
               function redirect_to_parent(){
                         <s:if test="%{ACC_FLAG==null}">
                                  parent.parent.window.location = "newmstmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>";
              
                         </s:if>
              <s:else>
                   parent.parent.window.location = "newmstmbillentAction.action?MAST_SL_NO=<s:property value="%{MAST_SL_NO}"/>&ACC_FLAG=Yes";
              
              </s:else>
              // parent.parent.GB_hide();
          }
          
              
              
  function getsrvtxtfun()
 {
     
     dojo.event.topic.publish("show_detailsrv1");
     
 }
          </script>
    </head>
    <body style="margin:0px;padding: 0px;background-color: #f2f2f2;" class="body1" onunload="redirect_to_parent()">
        <s:if test="%{REFFLG=='Yes'}">
          <script>
          redirect_to_parent()
          </script>
          </s:if>
          <form method="POST" id="formId" name="frmname" action="" style="margin: 0px;padding: 0px">
        
                                <table width="100%" cellspacing="0" border="0" cellpadding='0'>
                                    <tr>
                                        <td>
                                            <s:hidden name="MAST_SL_NO" id="MAST_SL_NO" value="%{MAST_SL_NO}"/>
                                            <table width="100%" cellspacing="1" cellpadding='2'  bgcolor="#d0d7e5">
                                                <tr class="hd"><td style="height:25px">Sl.No</td><td>Cost Element</td><td> Form Type</td><td>Amount</td></tr>
                                                <s:set id="ctnbrk" name="ctnbrk" value="0"/>
                                                <s:set id="setbrktotal" name="setbrktotal" value="0"/>
                                                
                                                <s:iterator value="savecostelement" status="savebrkst">
                                                    <s:if test="%{#ctnbrk==0}">
                                                    <tr style="background-color: #FFFFFF">
                                                        <td class="label-1"><s:property value="%{#ctnbrk+1}"/></td>
                                                        <td>
                                                            <s:select theme="simple"  value="%{BILL_NO}"  cssStyle="width:250px;" cssClass="selecttext" listKey="%{SL_NO}" listValue="%{TYPE_SL_NO +'-'+SUB_TYPE_CODE}"   list="%{grosselemlist}" name="BREAK_CODE" />
                                                            
                                                          
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
                                                        <td >
                                                             <s:text name="product.req" id="BREAK_AMOUNTdec" >
                                                            <s:param name="value" value="%{BILL_AMOUNT}"/> 
                                                             </s:text>
                                                             <s:if test="%{FORWARD_DATE==null}">
                                                             <s:textfield name="BREAK_AMOUNT" theme="simple" value="%{#BREAK_AMOUNTdec}" onblur="validatenumber(this);totalamountbrk();document.getElementById('GROSS_AMOUNT').value=this.value" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="texts" size="10"/>
                                                              </s:if>
                                                             <s:else>
                                                                 <s:textfield name="BREAK_AMOUNT" theme="simple" value="%{#BREAK_AMOUNTdec}" readonly="true" onblur="validatenumber(this);totalamountbrk();document.getElementById('GROSS_AMOUNT').value=this.value" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="textreadonly" size="10"/>
                                                              
                                                             </s:else>
                                                        </td>
                                                    </tr>
                                                    </s:if>
                                                    <s:else>
                                                     <tr style="background-color: #FFFFFF">
                                                        <td class="label-1"><s:property value="%{#ctnbrk+1}"/></td>
                                                        <td>
                                                            <s:select theme="simple" value="%{BILL_NO}"  onchange="totalamountbrk();addNewListItem(this,'FORM_TYPE%{#ctnbrk}','FORM_TYPE%{#ctnbrk}TXT');"  cssStyle="width:250px;" cssClass="selecttext" listKey="SL_NO" listValue="TYPE_SL_NO +'-'+SUB_TYPE_CODE"   list="costelement" name="BREAK_CODE" />
                                                          
                                                          
                                                        </td>
                                                        <td >
                                                            <s:if test="%{SUPPLIER_CODE==null}">
                                                            <s:hidden name="FORM_TYPE"   id="FORM_TYPE%{#ctnbrk}TXT"/>
                                                            <s:select theme="simple"  disabled="true"  headerKey="" headerValue="Select Form" id="FORM_TYPE%{#ctnbrk}"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                          </s:if>
                                                            <s:else>
                                                                <s:hidden name="FORM_TYPE"  disabled="true" id="FORM_TYPE%{#ctnbrk}TXT"/>
                                                                <s:select theme="simple" headerKey="" headerValue="Select Form"  value="%{SUPPLIER_CODE}"   id="FORM_TYPE%{#ctnbrk}"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                         
                                                            </s:else>
                                                            </td>
                                                        <td>
                                                             <s:text name="product.req" id="BREAK_AMOUNTdec" >
                                                            <s:param name="value" value="%{BILL_AMOUNT}"/> 
                                                             </s:text>
                                                            <s:textfield name="BREAK_AMOUNT" theme="simple"  value="%{#BREAK_AMOUNTdec}" onblur="validatenumber(this);totalamountbrk()" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="texts" size="10"/>
                                                        </td>
                                                    </tr>
                                                    </s:else>
                                                    <s:set id="ctnbrk" name="ctnbrk" value="%{#ctnbrk+1}"/>
                                                    <s:set id="setbrktotal" name="setbrktotal" value="%{#setbrktotal+BILL_AMOUNT}"/>
                                                </s:iterator>
                                                    
                                                    
                                                    
                                              
                                                <s:iterator begin="%{#ctnbrk}" end="6"  status="billitrst">
                                                    
                                                      <s:if test="%{savecostelement.size()==0 && #billitrst.index==0}">
                                                <tr style="background-color: #FFFFFF">
                                                        <td class="label-1"><s:property value="%{#ctnbrk+1}"/></td>
                                                        <td>
                                                            
                                                            <s:select theme="simple"   cssStyle="width:250px;" cssClass="selecttext" listKey="SL_NO" listValue="TYPE_SL_NO +'-'+SUB_TYPE_CODE"   list="grosselemlist" name="BREAK_CODE" />
                                                            
                                                          
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
                                                            <s:select theme="simple" value="" onchange="totalamountbrk();addNewListItem(this,'FORM_TYPE%{#ctnbrk}','FORM_TYPE%{#ctnbrk}TXT');" headerKey="" headerValue="Select Cost Element"  cssStyle="width:250px;" cssClass="selecttext" listKey="SL_NO" listValue="TYPE_SL_NO +'-'+SUB_TYPE_CODE"   list="costelement" name="BREAK_CODE" />
                                                            
                                                          
                                                        </td>
                                                        <td >
                                                            
                                                            <s:hidden name="FORM_TYPE"  value=""  disabled="true" id="FORM_TYPE%{#ctnbrk}TXT"/>
                                                            <s:select theme="simple"  value=""   headerKey="" headerValue="Select Form" id="FORM_TYPE%{#ctnbrk}"      cssStyle="width:100px;" cssClass="selecttext" listKey="%{EATX40}" listValue="%{EAAITM}"   list="%{costelementtype}" name="FORM_TYPE" /> 
                                                          
                                                            </td>
                                                        <td>
                                                            <s:textfield name="BREAK_AMOUNT"  theme="simple" value="" onblur="validatenumber(this);totalamountbrk()" onkeyup="totalamountbrk()"  cssStyle="font-weight:bold" id="BREAK_AMOUNT"  cssClass="texts" size="10"/>
                                                        </td>
                                                    </tr>
                                                    </s:else>
                                                    <s:set id="ctnbrk" name="ctnbrk" value="%{#ctnbrk+1}"/>
                                                </s:iterator>
                                                     
                                                    <s:text name="product.req" id="setbrktotaldec" >
                                                            <s:param name="value" value="%{#setbrktotal}"/> 
                                                             </s:text>
                                                    <tr  style="background-color: #FFFFFF" ><td colspan="3" class="label-1" align="right">Total</td><td><s:textfield name="BRKTOTAL" theme="simple" value="%{#setbrktotaldec}"  cssStyle="font-weight:bold" id="BRKTOTAL" readonly="true"  cssClass="textreadonly" size="10"/></td></tr>
                                                      <s:set id="amttemp" value="@java.lang.Double@parseDouble(NON_SRVTAX_AMOUNT)"/>
                                        <s:if test="%{#amttemp>0}">
                                        <s:text name="product.req" id="tempamt" >
                                        <s:param name="value" value="%{#amttemp}"/>
                                        </s:text>
                                        </s:if>
                                                   
                                                    <tr  style="background-color: #ebdbc3" ><td></td><td colspan="2" class="label-1" style="color:black">Non Service Tax Amount</td><td><s:textfield name="NON_SRVTAX_AMOUNT" theme="simple" value="%{#tempamt}"  cssStyle="font-weight:bold" id="NON_SRVTAX_AMOUNT" onblur="validatenumber(this);"  cssClass="texts" size="10"/></td></tr>
                                                   
                                                    <tr style="background-color: #ebdbc3"><td></td><td class="label-1" style="color:black">Reverse Service Tax <input type="checkbox" id="REVERSE_SRVTAXTEMP" <s:if test="%{REVERSE_SRVTAX==1}">checked="true"</s:if><s:else></s:else> disabled="true" value="1" name="REVERSE_SRVTAXTEMP"  />
                                                            <s:hidden name="REVERSE_SRVTAX" id="REVERSE_SRVTAX" value="%{REVERSE_SRVTAX}"/>
                                                        </td>
                                                        
                                                        <td align="right" class="label-1" style="color:black">Service Code</td><td>
                                                            <s:if test="%{REVERSE_SRVTAX==1}">
                                                                <s:textfield name="REVERSE_SRVTAX_CODE"  theme="simple" value="%{REVERSE_SRVTAX_CODE}" onblur="validatenumberfour(this);" onkeyup="getsrvtxtfun()"    id="REVERSE_SRVTAX_RATE" maxlength="8"  cssClass="texts" size="10"/>
                                                        </s:if>
                                                            <s:else>
                                                                <s:textfield name="REVERSE_SRVTAX_CODE"  theme="simple" value="" onblur="validatenumberfour(this);"  disabled="true"   id="REVERSE_SRVTAX_RATE" maxlength="8"  cssClass="texts" size="10"/>
                                                            </s:else>
                                                        </td>
                                                    </tr>
                                                      <tr style="background-color: #ebdbc3"><td></td>
                                                         <td class="label-1" style="color:black">
                                                             GL Code.
                                                        </td>
                                                        
                                                        <td  class="label-1" style="color:black" colspan="2">
                                                              <s:url id="srvurl1" action="getsertexdtmbillentAction.action" >
                                                                  <s:param name="SRVTAX_GL_CODE" value="%{SRVTAX_GL_CODE}"/>
                                                              </s:url>
                                                              <sx:div id="detailssrv1"  href="%{srvurl1}" listenTopics="show_detailsrv1" cssStyle="width:190px;" formId="formId" showLoadingText="Loading ......"></sx:div>

                                                        </td>
                                                    </tr>
                                            </table>
                                            
                                        </td>

                                </tr>  
                                 <tr style="background-color: #FFFFFF">
            <td height="1pt"  align="center" style="color:red;font-weight:bold">
                <div style="height:5pt">
                    <s:actionerror />
                    <s:fielderror />
                    <s:actionmessage />
                   
                </div>


            </td>
        </tr>
                                <tr style="background-color: #FFFFFF">  <td align="center"> 
                                        <s:if test="{ACC_FLAG==null}">
                                            
                                        </s:if>
                                        <s:else>
                                             <s:hidden name="ACC_FLAG" value="Yes"/>
                                        </s:else>
                                        <s:if test="%{FORWARD_DATE==null}">
                                      <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                                      </s:if>
                                      <s:if test="%{FORWARD_DATE!=null && ACC_FLAG!=null && ACCOUNTDATE==null}">
                                         <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                                       
                                      </s:if>
                                    </td></tr>
                            </table>
                       
                                            
                                                    <s:hidden name="TYPEFLA" id="TYPEFLA" value=""/>
                                                    
                                                    <s:iterator value="%{grosselemlist}"  status="tempstg" >
                                                           <s:hidden name="%{SL_NO}" id="%{SL_NO}TYPEFLA" value="%{SUB_TYPE_DESC}"/>
                                                   
                                                    </s:iterator>
                                                    <s:iterator value="%{costelement}"  status="tempst" >
                                                        <s:hidden name="%{SL_NO}" id="%{SL_NO}TYPEFLA" value="%{SUB_TYPE_DESC}"/>
                                                    </s:iterator>      
                                                    
                                                    <s:hidden name="tempbillamount" id="tempbillamount" value="%{tempbillamount}"/>
                                                    <s:hidden name="tempentgrossamt" id="tempentgrossamt" value="%{tempentgrossamt}"/>
                          </form> 
    </body>
</html>
