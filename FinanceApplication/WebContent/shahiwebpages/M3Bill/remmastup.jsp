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
       
        
        <script language="javascript">
            
           
    function onsave()
    {
       
        if(confirm('Do you want to Save?')){
     document.getElementById('formIdinv').action="remheadmbillentAction.action?actflg=Yes"  ;
     document.getElementById('formIdinv').submit(); 
        }else{
            return;
        }
        
    }
    
    
            </script>
    </head>
    <body style="background-color:#f2f2f2;padding: 0px;margin: 0px">
        <form method="POST" id="formIdinv" name="frmname" action="" style="margin: 0px;padding: 0px"> 
         <s:hidden name="MAST_SL_NO" value="%{MAST_SL_NO}"/>
              
         <table>
             <tr><td colspan="2">
                     
                     <textarea  class="texts"  name="MAST_REMARKS"  style="width:370px;height:110px;overflow:auto"   ><s:property value="%{MAST_REMARKS}"/></textarea>
                                                                                    
                 </td></tr>
             <s:if test="%{FORWARD_DATE==null}">
             <tr><td align="center">
                
                           
                <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                        
                </td>
                <td style="font-size:12px;color:red"  >  
                    <s:actionerror theme="simple" />
                    <s:fielderror theme="simple" />
                    <s:actionmessage theme="simple" />
                </td>
        </tr>
        </s:if>
         </table>
            
        </form>
    </body>
</html>
