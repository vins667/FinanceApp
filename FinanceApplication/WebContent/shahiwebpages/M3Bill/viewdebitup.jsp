<%-- 
    Document   : remmastup
    Created on : Oct 15, 2013, 2:27:23 PM
    Author     : RANJEET
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="css/style.css"> 
<link rel="stylesheet" href="css/ShahiButtons/shahibuttons.css"/>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sepl</title>
         <style>
            
         

            
            
           .textreadonly{
        font-family: Arial, Sans-Serif;
        font-size: 11px;
        border: solid 1px #677788;
        background-color:#e6e6e6;
       
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
       
        
        <script language="javascript">
            
           
    function onsave()
    {
               if(eval(document.getElementById('GROSS_AMOUNT').value)< eval(document.getElementById('DEBIT_AMOUNT').value))
                    {
                        
                          alert("Debit Amount  can not be > Gross Amount ");
                          document.getElementById('DEBIT_AMOUNT').focus();
                          return false;
                    }else{
        if(confirm('Do you want to Save?')){
     document.getElementById('formIdinv').action="debitdtmbillentAction.action?actflg=Yes"  ;
     document.getElementById('formIdinv').submit(); 
        }else{
            return;
        }
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
    
            </script>
    </head>
    <body style="background-color:#f2f2f2;padding: 0px;margin: 0px">
        <form method="POST" id="formIdinv" name="frmname" action="" style="margin: 0px;padding: 0px"> 
         <s:hidden name="MAST_SL_NO" value="%{MAST_SL_NO}"/>
         <s:hidden name="GROSS_AMOUNT" id="GROSS_AMOUNT" value="%{GROSS_AMOUNT}"/>
         <s:set id="ACC_READ" name="ACC_READ" value="true"/>
         <s:set id="USER_READ" name="USER_READ" value="true"/>
         
         <s:set id="ACC_CLASS" name="ACC_CLASS" value="%{'textreadonly'}"/>
         <s:set id="USER_CLASS" name="USER_CLASS" value="%{'textreadonly'}"/>
         <s:if test="%{EMPTYPE=='ENT'}">
             <s:set id="USER_READ" name="USER_READ" value="false"/>
              <s:set id="USER_CLASS" name="USER_CLASS" value="%{'texts'}"/>
         </s:if>
         <s:if test="%{EMPTYPE=='ACC'}">
              <s:set id="ACC_READ" name="ACC_READ" value="false"/>
              <s:set id="ACC_CLASS" name="ACC_CLASS" value="%{'texts'}"/>
         </s:if>
              
         <table>
             <tr><td colspan="2">
                      <s:set id="amttemp" value="@java.lang.Double@parseDouble(DEBIT_AMOUNT)"/>
                                        <s:if test="%{#amttemp>0}">
                                        <s:text name="product.req" id="tempamt" >
                                        <s:param name="value" value="%{#amttemp}"/>
                                        </s:text>
                                        </s:if>
                      <fieldset>
                          <legend>User</legend>               
                          <table>
                              
                              <tr><td class="label-1">Amount</td><td> <s:textfield name="DEBIT_AMOUNT"  maxlength="15" onblur="validatenumber(this)" value="%{#tempamt}" cssStyle="width:150px" readonly="%{USER_READ}" cssClass="%{USER_CLASS}" theme="simple" /></td> </tr>
                              <tr><td class="label-1">Remarks <td>
                                      <textarea   class="<s:property value="%{USER_CLASS}"/>"    name="DEBIT_REASON" style="width:370px;height:80px;overflow:auto"   ><s:property value="%{DEBIT_REASON}"/></textarea>
                                  </td> </tr>
                              <tr><td class="label-1">Name</td><td >
                                      <s:textfield name="ENT_USER_ID_NOT" readonly="true" maxlength="15"  value="%{ENT_USER_ID}" cssStyle="width:370px" cssClass="textreadonly" theme="simple" />
                                  </td></tr>
                          </table>
                      </fieldset> 
                      <fieldset>
                          <legend>Accounts</legend> 
                          <table>

                              <tr><td class="label-1">Amount</td><td>
                                       <s:set id="accamttemp" value="@java.lang.Double@parseDouble(ACC_DEBIT_AMOUNT)"/>
                                        <s:if test="%{#accamttemp>0}">
                                        <s:text name="product.req" id="tempamtacc" >
                                        <s:param name="value" value="%{#accamttemp}"/>
                                        </s:text>
                                        </s:if>
                                      
                                       <s:textfield name="ACC_DEBIT_AMOUNT" maxlength="15" onblur="validatenumber(this)" value="%{#tempamtacc}" cssStyle="width:150px" readonly="%{ACC_READ}" cssClass="%{ACC_CLASS}" theme="simple" /></td> </tr>
                              <tr><td class="label-1">Remarks <td>
                                      <textarea   class="<s:property value="%{ACC_CLASS}"/>"   name="ACC_DEBIT_REASON"  style="width:370px;height:80px;overflow:auto"   ><s:property value="%{ACC_DEBIT_REASON}"/></textarea>
                                  </td> </tr>
                              <tr><td class="label-1">Name</td><td >
                                      <s:textfield name="ENT_USER_ID_NOT" readonly="true" maxlength="15"  value="%{ACC_USER_ID}" cssStyle="width:370px" cssClass="textreadonly" theme="simple" />
                                  </td></tr>
                          </table>
                      </fieldset>
                 </td></tr>
           
        
         </table>
                    <s:hidden name="EMPTYPE" value="%{EMPTYPE}"/>
        </form>
    </body>
</html>
