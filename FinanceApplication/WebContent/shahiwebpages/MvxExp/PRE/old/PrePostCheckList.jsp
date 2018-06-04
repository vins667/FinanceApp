
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<link href="<s:url value="css/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/sexybuttons.css"/>
<script type="text/javascript" src="js/dom-drag.js"></script>
<script type="text/javascript" src="js/jquery.min-1.5.2.js"></script>
<html>
    <head>
    <sx:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shahi Exports Pvt Ltd</title>        
		<style type="text/css">
            th {
                font-size:11px;
                font-weight:bold;
                color:#006699;
                background-image:url('image/table-gradient.jpg');
            } 
             tbody {
                height: 500px;
                overflow-y: auto;
                overflow-x: hidden;

            }     
            .root
			{
				position:absolute;
				height:200px;
				width:900px;
				background-color:#F4F4F4;
				border:1px solid #2a6afe;
			    padding: 0px;
			    margin: 0px
			} 
			.root1
			{
				position:absolute;
				height:200px;
				width:900px;
				background-color:#F4F4F4;
				border:1px solid #2a6afe;
			    padding: 0px;
			    margin: 0px
			} 
			.handle
			{
				margin:0px;
				padding:0px;
				width: 100%;
				color:white;
			    font-weight: bold;
			    font-size: 12px
					
			}   
        </style>
        <script type="text/javascript">
            
    function selectall()
  {
   var a=document.frm.printrec;

   if(a.length>0)
   {
   if(document.frm.selall.checked==true)
   {  for (var i=0;i<a.length;i++)
      {a[i].checked=true;  }
    }else
    {
      for (var i=0;i<a.length;i++)
      {a[i].checked=false;  }
    }
   }
   else{
    if(document.frm.seleall.checked==true)
    {a.checked=true; }
    else
    {a.checked=false; }
   }
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
          {

        
      }
        return true;
  
    }
        function getDetail()
        {
          document.frm.action="PrePostRep.action?GetDtl=YES"; 
          document.frm.submit();  
          document.getElementById('prepage').style.visibility = '';
        }
    
	        function ongosearch(){
	        	$("#searchheadId").attr("disabled","disabled");
	        	$("#searchId").text("Please wait....");
	            $("#newheadId").attr("disabled","disabled");
	            $("#newId").text("Please wait....");
	            $("#clearheadId").attr("disabled","disabled");
	            $("#clearId").text("Please wait....");
	            
	        	document.frm.action="PrePostRep.action?SEARCH_SUBMIT=YES";
	        	document.frm.submit();
	        	document.getElementById('prepage').style.visibility = '';
	        }
	        
       function GetReport()
       {
           document.frm.action="PrePostRep.action?repFlag=YES"; 
           document.frm.submit();  
           document.getElementById('prepage').style.visibility = ''; 
           
       }

	          
        </script>
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
		            padding-top: 0px ;
		        }        
		         thead tr {
		             position: relative;
		             top: expression(this.offsetParent.scrollTop);
		        }
		        
		        tbody {
		            height: auto;
		        }
		         
		    </style>
		<![endif]-->
    </head>
    <body style="background-color:#f2f2f2">
    	<DIV align="center" id="prepage" class="lodingdiv" style="right:400px;position:absolute;z-index: 1;visibility: hidden;background: transparent;top:50px;" >
			<img src="image/pleaseWaitOverlay.gif" />			
		</DIV>
   		<form action="" onsubmit=""  method="post" name="frm"  >
      		<table align="center" width="100%">
            	<tr>
            		<td valign="top" style="border-width:1pt;border-color:black;border-style:solid;" >
		           		<table border="0" bgcolor="#f2f2f2" cellpadding="5" align="center" width="100%" >
		    	           	<tr><td width="100%"   class="hd" style="text-align:center">Pre & Post Docs CheckList</td></tr>
		                  	<tr>
		                  	<td width="100%">
		                  	<table style="width:100%;" cellpadding="1" cellspacing="1">
		                            <tr>
                                                <s:if  test="%{GetDtl==null && SEARCH_SUBMIT==null}"> 	
                                                   <td align="left"  class="label-1">
                                                        <input type="radio" name="REP_TYPE" value="POST" CHECKED>&nbsp;POST
                                                        <input type="radio" name="REP_TYPE" value="PRE">&nbsp;PRE Multiple
                                                        <input type="radio" name="REP_TYPE" value="PREINV">&nbsp;PRE Single
                                                         
                                                    </td>	      
		                          	    <td  class="label-1">Date from
			                           	    <sx:datetimepicker name="date1" 
			                                   		id="date1" 
                                                                        value="%{tdate}"
			                                        displayFormat="dd/MM/yyyy"
			                                        cssClass="texts"/>
		                             		    &nbsp;&nbsp;To
			                           	    <sx:datetimepicker name="date2" 
			                                   		id="date2" value="%{tdate}"
			                                               displayFormat="dd/MM/yyyy"
			                                        cssClass="texts"/>
                                                            &nbsp;&nbsp;
                                                            <button  id="searchheadId" class="sexybutton" onclick="getDetail()"><span><span><span class="search" id="searchId">Go</span></span></span></button>
								
		                             	      </td>
                                            </s:if>	 
                                            <s:if  test="%{GetDtl!=null && SEARCH_SUBMIT==null }"> 
                                                
			                            <td align="left"  style="color:red" class="label-1">
                                                         <input type="radio" name="REPCH1"  checked="true">
                                                         <s:property value="%{REP_TYPE}" /> 
                                                         <s:hidden name="REP_TYPE" value="%{REP_TYPE}" />
                                                    </td>	      
                                                    <td align="center" class="label-1">Date from : <s:property value="%{date1}" /></td>
                                                    <td align="center" class="label-1">Date TO : <s:property value="%{date2}" /></td>        
		                             		     
                                                            <s:hidden name="date1" value="%{date1}" />
                                                            <s:hidden name="date2" value="%{date2}" />
                                                            <s:hidden name="GetDtl" value="%{GetDtl}" />
                                                      <td class="label-1">User 
                                                                <s:select name="searchuser"   cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" theme="simple" list="%{userList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                                                        </td>
                                                        <td class="label-1">Buyer 
                                                           <s:select name="searchbuyer"   cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" theme="simple" list="%{buyerList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                                                        </td>
                                                        <td class="label-1">Destination  
                                                            <s:select name="searchdest"   cssStyle="text-transform:uppercase;width:70pt;font-size:9pt;" theme="simple" list="%{destList}" headerKey="" headerValue="" listKey="LIST_CODE" listValue="LIST_NAME" />
                                                         </td>
                                                        
		                          	       <td style="text-align: right;">	                          		
			                          			<button  id="searchheadId" class="sexybutton" onclick="ongosearch()"><span><span><span class="search" id="searchId">Search</span></span></span></button> &nbsp;
									<button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PrePostRep.action');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button>
						       </td>											
                                                  </s:if>  
                                                   
                                                  <s:if  test="%{SEARCH_SUBMIT!=null }"> 
                                                       <td align="left" style="color:red" class="label-1">
                                                         <input type="radio" name="REPCH1"  checked="true">
                                                         <s:property value="%{REP_TYPE}" /> 
                                                         <s:hidden name="REP_TYPE" value="%{REP_TYPE}" />
                                                       </td>
                                                       <td align="center" class="label-1">Date from : <s:property value="%{date1}" /></td>
                                                       <td align="center" class="label-1">Date TO : <s:property value="%{date2}" /></td>        
		                             		     
                                                            <s:hidden name="date1" value="%{date1}" />
                                                            <s:hidden name="date2" value="%{date2}" />
                                                            <s:hidden name="GetDtl" value="%{GetDtl}" />
                                                      
                                                            <td class="label-1">Buyer : <s:property value="searchbuyer" /></td>
                                                          
                                                        <td class="label-1">Destination : <s:property value="searchdest" /> </td>
                                                         
                                                        
                                                        <td class="label-1">User : <s:property value="searchuser" />   </td>
                                                        <td align="right">    <button  id="clearheadId" class="sexybutton" href="#" onclick="javascript:window.location.href('PrePostRep.action');"><span><span><span class="reload" id="clearId">Clear</span></span></span></button> 
                                                                              <button  id="clearheadId" class="sexybutton" href="#" onclick="GetReport()"><span><span><span class="print" id="clearId">Pdf</span></span></span></button></td>
                                       
                                                  </s:if>
                                               </tr>
                                       </table>
		              		    </td>
		              		</tr>
		           		</table>
					</td>
				</tr>
				<tr>
					<td style="width:100%">
						<table width="100%" cellpadding="0" cellspacing="0">
				  <tr>
					<td valign="top" style="border-width:1px;border-color:black;border-style:solid;" >
	                                <div  class="div1" style="width:100%;overflow:auto ;height:560px;">
	                                    <table   id="myTableID" border="0" bgcolor="#f2f2f2" cellpadding="1" cellspacing="1"  align="center" width="100%" >
	                                    	<thead class="hd" style="position: absolute; top: expression(this.offsetParent.scrollTop);">
                                                    <tr>
                                                        <th  style="height:25px">Sl#</th>
                                                         <th>Buyer</th>
                                                         <th>Destn</th>
                                                         <th>PCH</th>
                                                         <th>TO Date</th>
                                                         <th>A/C Holder</th>
                                                         <th>Inv No</th>
                                                         <th align="center" style="font-size:8pt" class="label-1" ><input title="Select All" type="CHECKBOX" name="selall" value="Y" onclick="selectall()"></th>
                
                                                    </tr>
	                                        </thead>
	                                        <tbody>	    
                                                      
	                                        	<s:iterator value="invList"  status="st1">
                                                           <tr bgcolor="white" onmouseover="style.color='black';style.backgroundColor='Lightgreen';"  onmouseout="style.backgroundColor='white';" style="cursor:hand" >
                                                                        <td style="font-size:8pt"><s:property value="%{#st1.index+1}"/></td>
                                                                     	<td style="font-size:8pt"><s:property value="BUYER"/></td>
                                                                        <td style="font-size:8pt"><s:property value="DESTI"/></td>
	                                        			<td style="font-size:8pt"><s:property value="PCH"/></td>
	                                        			<td style="font-size:8pt"><s:property value="TO_DATE"/></td>
	                                        			<td style="font-size:8pt"><s:property value="AC_HOLDER"/></td>
                                                                        <td style="font-size:8pt"><s:property value="EXCS_INV_NO"/></td>
                                                                        <td  style="font-size:8pt" align="center"> <input type="checkbox" name="printrec"  theme="simple" value="<s:property value="EXCS_INV_NO"/>#<s:property value="BUYER"/>!<s:property value="BUYER_GRP"/>"</td>
                        
	                                           		</tr>
	                                        	</s:iterator>                                     	
	                                        </tbody>
                                                <s:hidden name="Routput" value="PDF" />
                                                 
	                          </table>
	                                 </div>
                                 </td>
                        	</tr>                                   
				</table>
					</td>
				</tr>
                                 <tr>
                                        <td height="40px"  align="center" style="color:red;font-weight:bold;">

                                            <s:actionerror theme="simple" cssStyle="font-size:12px" />
                                            <s:fielderror theme="simple" cssStyle="font-size:12px"  />
                                            <s:actionmessage theme="simple" cssStyle="font-size:12px"  />



                                        </td>
                                    </tr>
			</table>
			 
		</form>		
    </body>
</html>

