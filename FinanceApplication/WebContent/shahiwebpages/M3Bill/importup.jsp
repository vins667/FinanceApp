<%-- 
    Document   : invmast
    Created on : Sep 28, 2013, 4:23:30 PM
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
        
        <script language="javascript">
            
            function validatesear()
            {
                var flag=0;
              if(document.getElementById('SSHIPBILL').value!="")  
                  {
                      flag=1;
                  }
                 
                
                if(flag==0)
                    {
                        
                        alert("Please Enter Detail For Search.");
                        document.getElementById('SSHIPBILL').focus();
                        return false;
                        
                    }
                
                return true;
                
            }
            
            function invrecser()
            {
                if(validatesear()==true){
              document.getElementById('formIdinv').action="importserupmbillentAction"  ;
              document.getElementById('formIdinv').submit();
                }
                
            }
           
    function onsave()
    {
        if(validateinput()==true){
        if(confirm('Do you want to Save?')){
     document.getElementById('formIdinv').action="importsavembillentAction"  ;
     document.getElementById('formIdinv').submit(); 
        }else{
            return;
        }
        }
    }
    
    
    function validateinput()
    {
     
    if(document.getElementById('formIdinv').INV_NO)
        {}else{
            alert("Record Not Found For Save...");
            return false;
        }
        
        return true;
    }
    function saveflag(a,b)
    {
       if(a.checked==true) 
           {
               document.getElementById(b).value="Yes";
               
               
           }else{
              document.getElementById(b).value="No"; 
           }
        
    }
     function validatenumber(a)
            {
            	k=a.value;
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,3}$/ ) )
            	{
            		alert('Invalid Input, Only Numbers Allowed');
            		a.value="";
            		a.focus();
            		return false;
            	}
            	return true;
            }
            
             function CheckAllDelete()
        {
            a = document.frmname.dtchk;
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
                    
                    saveflag(e,e.value);
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
                saveflag(e,e.value);
            }
        }
            
            </script>
    </head>
    <body style="background-color:#f2f2f2;padding: 0px;margin: 0px">
        <form method="POST" id="formIdinv" name="frmname" action="" style="margin: 0px;padding: 0px"> 
                     <s:hidden name="MAST_SL_NO" value="%{MAST_SL_NO}"/>
                     
            <s:if test="%{FORWARD_DATE==null}">
         <table ><tr><td class="label-1">AWB/BOE No</td><td>
                    
                     
                                            <s:textfield name="SSHIPBILL" value="%{SSHIPBILL}" cssStyle="width:180px" id="SSHIPBILL"  theme="simple" cssClass="texts"/>
                                            
                                              </td>
                                        
                                        
                                        <td><button onclick="invrecser();" class="sexybutton"><span><span><span class="search">Go</span></span></span></button></td>
                                    </tr></table>
                                        </s:if>
                                        <table width="100%" cellpadding="0" cellspacing="0"><tr><td style="border-width:1pt;border-color:black;border-style:solid;">                           
           <div  class="div1" style="width:100%;overflow:auto ;<s:if test="%{FORWARD_DATE==null}">height:190px;</s:if><s:else>height:270px;</s:else>">
         <table border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
         <thead >
       <tr  class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);height:15pt" >
           <th class="label-1">AWB No</th>
           <s:if test="%{FORWARD_DATE==null}">
       <th style="width:70px;" class="label-1">
       <input name="dchk" type="checkbox" onclick="CheckAllDelete()">&nbsp;Select</th>
           </s:if>
           </tr>
        </thead>
         <tbody>
             <s:iterator value="invsavelist" status="invsavelistst">
                <tr style='background-color: #ffffce'>
                    <td><s:textfield name="INV_NO" id="INV_NO" theme="simple" cssClass="textreadonly" cssStyle="width:250px" readonly="true" value="%{EAAITM}"/>
                    
                    </td>
                     <td>
                    <input type="checkbox" checked="true" value="<s:property value="%{EAAITM}"/>id" name="dtchk" onclick="saveflag(this,'<s:property value="%{EAAITM}"/>id')"/>
                    <s:hidden name="INV_SAVE" value="Yes" id="%{EAAITM}id"/>
                </td>
                </tr> 
             </s:iterator>
            <s:iterator value="invlist" status="invlistst">
                <tr style='background-color: #FFFFFF'>
                    <td><s:textfield name="INV_NO" id="INV_NO" theme="simple" cssClass="textreadonly" cssStyle="width:250px" readonly="true" value="%{EAAITM}"/>
                    </td>
                     <s:if test="%{FORWARD_DATE==null}">
                    <td>
                        <input type="checkbox" name="dtchk" checked="true" value="<s:property value="%{EAAITM}"/>id" onclick="saveflag(this,'<s:property value="%{EAAITM}"/>id')"/>
                      <s:hidden name="INV_SAVE"  value="Yes" id="%{EAAITM}id"/>
                </td>
                </s:if>
                </tr>
            </s:iterator>
          </tbody>
          </table>
          </div>
         </td></tr>
        <s:if test="%{FORWARD_DATE==null}">
        
        <tr><td align="center">
                <table cellpadding="0" width="100%"><tr><td width="300px">
                           <table>
                             <tr>
                                                <td><div style="width:25px;height:20px;background-color:#ffffce;border-width:1pt;border-color:#ffffce;border-style:solid;"></div></td><td class="label-1">Saved Entry&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td><div style="width:25px;height:20px;background-color:#FFFFFF;border-width:1pt;border-color:#FFFFFF;border-style:solid;"></div></td><td class="label-1">New Entry</td>
                                            </tr>
                                            </table> 
                            
                        </td><td>
                <table cellpadding="0" cellspacing="0"><tr>
                        
                        <td>
                           
                <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                        
                </td>
                <td style="font-size:12px;color:red"  >  
                   
                </td>
        </tr></table>
                        </td>
                </table>
            </td>
        </tr>
        <tr> <td  align="center" style="font-size:12px;color:red"  >  
                    <s:actionerror theme="simple" />
                    <s:fielderror theme="simple" />
                    <s:actionmessage theme="simple" />
                </td></tr>
        </s:if>
                </table>
            
        </form>
    </body>
</html>