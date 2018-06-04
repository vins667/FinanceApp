<%-- 
    Document   : fabrlr
    Created on : Mar 04, 2014, 4:23:30 PM
    Author     : VIVEK
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
                /*
              if(document.getElementById('SSHIPBILL').value!="")  
                  {
                      flag=1;
                  }
                  if(document.getElementById('BOS').value!="")  
                  {
                      flag=1;
                  }
                  if(document.getElementById('SINV').value!="")  
                  {
                      flag=1;
                  }
                   if(document.getElementById('lcert_recv_no').value!="")  
                  {
                      flag=1;
                  }
                  */
                 if(document.getElementById('BOS').value!="")  
                  {
                      flag=1;
                  }
                if(flag==0)
                    {
                        
                        alert("Please Enter Detail For Search.");
                        document.getElementById('BOS').focus();
                        return false;
                        
                    }
                
                return true;
                
            }
            
            function invrecser()
            {
                if(validatesear()==true){
                  document.getElementById('formIdinv').action="newinvmbillentAction?savetest=Ser"  ;
                  document.getElementById('formIdinv').submit();
                }
                
            }
           
    function onsave()
    {
        if(validateinput()==true){
        if(confirm('Do you want to Save?')){
     document.getElementById('formIdinv').action="savefablrmbillentAction.action"  ;
     document.getElementById('formIdinv').submit(); 
        }else{
            return;
        }
        }
    }
    
    
    function validateinput()
    {    	
        return true;
    }
    function validatelrno(y){
    	if(document.frmname.T_LR_NO){
	    	a=document.frmname.T_LR_NO;
	    	if (a.length > 1)
	        {
	    		for(i=0;i<a.length;i++){
	    			if(y.value!='' && y.value==a[i].value){
	    				alert("LR No can't be duplicate");
	    				y.value="";
	    				y.focus();
	    				return false;
	    			}
	    		}
	        }
	    	else{
	    		if(y.value!='' && document.frmname.T_LR_NO.value==y.value){
	    			alert("LR No can't be duplicate");
					y.value="";
					y.focus();
					return false;
	    		}
	    	}
    	}
    	if(document.frmname.LR_NO){
    		a=document.frmname.LR_NO;
    		if (a.length > 1)
	        {
    			for(i=0;i<a.length;i++){
	    			if(y.value!='' && y.value==a[i].value && y!=a[i]){
	    				alert("LR No can't be duplicate");
	    				y.value="";
	    				y.focus();
	    				return false;
	    			}
	    		}
	        }    		
    	}
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
            	if (k!="" && !k.match(/^\d+$|^\d+\.\d{1,2}$/ ) )
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
            a = document.frmname.lrdtchk;
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
               
                saveflag(a,a.value);
            }
        }
             function AddRow(tableID)
             {   var table = document.getElementById(tableID);
                 var rowCount = table.rows.length;
                 var indexval=eval(rowCount)-1;
                 //alert(indexval);
                 var row = table.insertRow(rowCount);
                 var colCount = table.rows[indexval].cells.length;
                 for(var i=0; i<colCount; i++)
                 {   var newcell = row.insertCell(i);
                     newcell.innerHTML = table.rows[indexval].cells[i].innerHTML;
//                     alert(newcell.childNodes[0].type);
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
    <body style="background-color:#f2f2f2;padding: 0px;margin: 0px">
        <form method="POST" id="formIdinv" name="frmname" action="" style="margin: 0px;padding: 0px"> 
                     <s:hidden name="MAST_SL_NO" value="%{MAST_SL_NO}"/>                                    
            
          <table width="100%" cellpadding="0" cellspacing="0">
              <tr><td style="border-width:1pt;border-color:black;border-style:solid;">                           
           <div  class="div1" style="width:100%;overflow:auto ;<s:if test="%{FORWARD_DATE==null}">height:200px;</s:if><s:else>height:280px;</s:else>">
         <table id="tabtax" border="0" align="center" width="100%" cellpadding="1" bgcolor="#d0d7e5" cellspacing="1" >
         <thead>
       		<tr  class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);height:15pt" >
            	<th class="label-1">LR No. </th>
                <th class="label-1" style="text-align:right;">LR Weight</th>
                <th class="label-1" style="text-align: center;">Uom</th>
                <th class="label-1" style="text-align:right;">Qty.</th>
                <th class="label-1">Material TYPE</th>
           		<th style="width:70px;" class="label-1">
           			<s:if test="%{FORWARD_DATE==null}">
       					<input name="dchk" type="checkbox" onclick="CheckAllDelete()">&nbsp;Select
       				</s:if>
       			</th>
           		
           </tr>
        </thead>
         <tbody>
             <s:iterator value="fablrlist" status="fablrlistst">
                <tr style='background-color: #FFFFFF'>
                    <td style="width:100px;">
                    	<s:textfield name="T_LR_NO" value="%{LR_NO}" theme="simple" cssClass="textreadonly" readonly="true" cssStyle="width:100%"/>
                    </td>
                    <td style="width:150px;">
                    	<s:textfield name="T_LR_WEIGHT" value="%{LR_WEIGHT}" theme="simple"  cssClass="textreadonly" readonly="true" onblur="validatenumber(this)" cssStyle="width:100%;text-align:right;"/>
                    </td>
                    <td style="width:100px;">
                    	<s:textfield name="T_LR_WEIGHT_UOM" value="%{LR_WEIGHT_UOM}" theme="simple"  cssClass="textreadonly" readonly="true" cssStyle="width:100%;text-align:center;"/>                    	
                    </td>                   
                    <td style="width:100px;">
                    	<s:textfield name="T_LR_QTY" value="%{LR_QTY}" theme="simple" cssClass="textreadonly" readonly="true" onblur="validatenumber(this)" cssStyle="width:100%;text-align:right;"/>
                    </td>
                 	<td style="width:150px;">
                 		<s:textfield name="T_LR_MATERIAL_TYPE" value="%{LR_MATERIAL_TYPE}" theme="simple"  cssClass="textreadonly" readonly="true" cssStyle="width:100%"/>                 		
                 	</td>        
                 	<td>
                 		<s:if test="%{FORWARD_DATE==null}">
                    		<input type="checkbox" value="<s:property value="%{SL_NO}"/>" name="lrdtchk"/>
                    	</s:if>
                	</td>  
                </tr> 
             </s:iterator>
            <s:iterator begin="0" end="10">
                <tr style='background-color: #FFFFFF'>
                    <td style="width:100px;">
                    	<s:textfield name="LR_NO" theme="simple" onblur="validatelrno(this);" cssClass="texts" cssStyle="width:100%"/>
                    </td>
                    <td style="width:150px;">
                    	<s:textfield name="LR_WEIGHT" theme="simple" cssClass="texts" onblur="validatenumber(this)" cssStyle="width:100%;text-align:right;"/>
                    </td>
                    <td style="width:100px;">
                    	<s:select name="LR_WEIGHT_UOM" list="%{fablruomlist}" theme="simple" headerKey="" headerValue="Select" cssClass="texts" cssStyle="width:100%;text-align:center;"/>
                    </td>                   
                    <td style="width:100px;">
                    	<s:textfield name="LR_QTY" theme="simple" cssClass="texts" onblur="validatenumber(this)" cssStyle="width:100%;text-align:right;"/>
                    </td>
                 	<td style="width:150px;">
                 		<s:select name="LR_MATERIAL_TYPE" list="#{'':'SELECT','YARN':'YARN','FABRIC':'FABRIC','ACCESSORY':'ACCESSORY','GARMENT':'GARMENT','M/C':'M/C','CHEMICAL':'CHEMICAL'}" theme="simple" cssClass="texts" cssStyle="width:100%"/>
                 	</td>        
                 	<td style="width:100px;">
                 		&nbsp;
                 	</td>             
                </tr>
            </s:iterator>
          </tbody>
          </table>
          </div>
         </td>
         </tr>
        <s:if test="%{FORWARD_DATE==null}">
        <tr><td align="center">
                <table cellpadding="0" width="100%"><tr><td width="300px">
                        </td><td>
                <table cellpadding="0" cellspacing="0"><tr>
                        
                        <td>
                           
                <button class="sexybutton" onclick="onsave();"><span><span><span class="save">Save</span></span></span></button> 
                &nbsp;&nbsp;<button class="sexybutton" onclick="AddRow('tabtax');"><span><span><span class="add">Add Row</span></span></span></button>   
                </td>
                <td style="font-size:12px;color:red"  >  
                    <s:actionerror theme="simple" />
                    <s:fielderror theme="simple" />
                    <s:actionmessage theme="simple" />
                </td>
        </tr></table>
                        </td>
                </table>
            </td>
        </tr>
        </s:if>
                </table>
            
        </form>
    </body>
</html>
